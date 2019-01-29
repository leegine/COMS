head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.29.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleSendQParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.slebase.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/** 
 * sle_send_qテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link SleSendQRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link SleSendQParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link SleSendQParams}が{@@link SleSendQRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see SleSendQPK 
 * @@see SleSendQRow 
 */
public class SleSendQParams extends Params implements SleSendQRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "sle_send_q";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = SleSendQRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return SleSendQRow.TYPE;
  }


  /** 
   * <em>queue_id</em>コラムの値 
   */
  public  long  queue_id;

  /** 
   * <em>product_type</em>コラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>market_code</em>コラムの値 
   */
  public  String  market_code;

  /** 
   * <em>broker_name</em>コラムの値 
   */
  public  String  broker_name;

  /** 
   * <em>institution_code</em>コラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>コラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>product_code</em>コラムの値 
   */
  public  String  product_code;

  /** 
   * <em>order_id</em>コラムの値 
   */
  public  Long  order_id;

  /** 
   * <em>order_unit_id</em>コラムの値 
   */
  public  Long  order_unit_id;

  /** 
   * <em>biz_date</em>コラムの値 
   */
  public  String  biz_date;

  /** 
   * <em>op_type</em>コラムの値 
   */
  public  webbroker3.slebase.enums.SleSendqOpTypeEnum  op_type;

  /** 
   * <em>order_type</em>コラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum  order_type;

  /** 
   * <em>limit_price</em>コラムの値 
   */
  public  Double  limit_price;

  /** 
   * <em>quantity</em>コラムの値 
   */
  public  Double  quantity;

  /** 
   * <em>change_quantity</em>コラムの値 
   */
  public  Double  change_quantity;

  /** 
   * <em>change_limit_price</em>コラムの値 
   */
  public  Double  change_limit_price;

  /** 
   * <em>already_execd_quantity</em>コラムの値 
   */
  public  Double  already_execd_quantity;

  /** 
   * <em>account_id</em>コラムの値 
   */
  public  long  account_id;

  /** 
   * <em>account_code</em>コラムの値 
   */
  public  String  account_code;

  /** 
   * <em>sub_account_id</em>コラムの値 
   */
  public  long  sub_account_id;

  /** 
   * <em>status</em>コラムの値 
   */
  public  webbroker3.slebase.enums.SleSendqProcStatusEnum  status;

  /** 
   * <em>confirmed_by_sle_rcvd_q</em>コラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  confirmed_by_sle_rcvd_q;

  /** 
   * <em>order_emp_code</em>コラムの値 
   */
  public  String  order_emp_code;

  /** 
   * <em>order_request_number</em>コラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>send_process_date_time</em>コラムの値 
   */
  public  java.sql.Timestamp  send_process_date_time;

  /** 
   * <em>created_timestamp</em>コラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>コラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean queue_id_is_set;
  private boolean product_type_is_set;
  private boolean market_code_is_set;
  private boolean broker_name_is_set;
  private boolean institution_code_is_set;
  private boolean branch_code_is_set;
  private boolean biz_date_is_set;
  private boolean op_type_is_set;
  private boolean order_type_is_set;
  private boolean account_id_is_set;
  private boolean account_code_is_set;
  private boolean sub_account_id_is_set;
  private boolean status_is_set;
  private boolean confirmed_by_sle_rcvd_q_is_set;
  private boolean order_emp_code_is_set;
  private boolean order_request_number_is_set;
  private boolean created_timestamp_is_set;
  private boolean last_updated_timestamp_is_set;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "queue_id=" + queue_id
      + "," + "product_type=" +product_type
      + "," + "market_code=" +market_code
      + "," + "broker_name=" +broker_name
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "product_code=" +product_code
      + "," + "order_id=" +order_id
      + "," + "order_unit_id=" +order_unit_id
      + "," + "biz_date=" +biz_date
      + "," + "op_type=" +op_type
      + "," + "order_type=" +order_type
      + "," + "limit_price=" +limit_price
      + "," + "quantity=" +quantity
      + "," + "change_quantity=" +change_quantity
      + "," + "change_limit_price=" +change_limit_price
      + "," + "already_execd_quantity=" +already_execd_quantity
      + "," + "account_id=" +account_id
      + "," + "account_code=" +account_code
      + "," + "sub_account_id=" +sub_account_id
      + "," + "status=" +status
      + "," + "confirmed_by_sle_rcvd_q=" +confirmed_by_sle_rcvd_q
      + "," + "order_emp_code=" +order_emp_code
      + "," + "order_request_number=" +order_request_number
      + "," + "send_process_date_time=" +send_process_date_time
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のSleSendQParamsオブジェクトを作成します。 
   */
  public SleSendQParams() {
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のSleSendQRowオブジェクトの値を利用してSleSendQParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するSleSendQRowオブジェクト 
   */
  public SleSendQParams( SleSendQRow p_row )
  {
    queue_id = p_row.getQueueId();
    queue_id_is_set = p_row.getQueueIdIsSet();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    broker_name = p_row.getBrokerName();
    broker_name_is_set = p_row.getBrokerNameIsSet();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    product_code = p_row.getProductCode();
    if ( !p_row.getOrderIdIsNull() )
      order_id = new Long( p_row.getOrderId() );
    if ( !p_row.getOrderUnitIdIsNull() )
      order_unit_id = new Long( p_row.getOrderUnitId() );
    biz_date = p_row.getBizDate();
    biz_date_is_set = p_row.getBizDateIsSet();
    op_type = p_row.getOpType();
    op_type_is_set = p_row.getOpTypeIsSet();
    order_type = p_row.getOrderType();
    order_type_is_set = p_row.getOrderTypeIsSet();
    if ( !p_row.getLimitPriceIsNull() )
      limit_price = new Double( p_row.getLimitPrice() );
    if ( !p_row.getQuantityIsNull() )
      quantity = new Double( p_row.getQuantity() );
    if ( !p_row.getChangeQuantityIsNull() )
      change_quantity = new Double( p_row.getChangeQuantity() );
    if ( !p_row.getChangeLimitPriceIsNull() )
      change_limit_price = new Double( p_row.getChangeLimitPrice() );
    if ( !p_row.getAlreadyExecdQuantityIsNull() )
      already_execd_quantity = new Double( p_row.getAlreadyExecdQuantity() );
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    sub_account_id = p_row.getSubAccountId();
    sub_account_id_is_set = p_row.getSubAccountIdIsSet();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    confirmed_by_sle_rcvd_q = p_row.getConfirmedBySleRcvdQ();
    confirmed_by_sle_rcvd_q_is_set = p_row.getConfirmedBySleRcvdQIsSet();
    order_emp_code = p_row.getOrderEmpCode();
    order_emp_code_is_set = p_row.getOrderEmpCodeIsSet();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    send_process_date_time = p_row.getSendProcessDateTime();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      product_type_is_set = true;
      market_code_is_set = true;
      broker_name_is_set = true;
      institution_code_is_set = true;
      branch_code_is_set = true;
      biz_date_is_set = true;
      op_type_is_set = true;
      order_type_is_set = true;
      account_id_is_set = true;
      account_code_is_set = true;
      sub_account_id_is_set = true;
      status_is_set = true;
      confirmed_by_sle_rcvd_q_is_set = true;
      order_emp_code_is_set = true;
      order_request_number_is_set = true;
      created_timestamp_is_set = true;
      last_updated_timestamp_is_set = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof SleSendQRow ) )
       return false;
    return fieldsEqual( (SleSendQRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のSleSendQRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( SleSendQRow row )
  {
    if ( queue_id != row.getQueueId() )
      return false;
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( broker_name == null ) {
      if ( row.getBrokerName() != null )
        return false;
    } else if ( !broker_name.equals( row.getBrokerName() ) ) {
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
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( order_id == null ) {
      if ( !row.getOrderIdIsNull() )
        return false;
    } else if ( row.getOrderIdIsNull() || ( order_id.longValue() != row.getOrderId() ) ) {
        return false;
    }
    if ( order_unit_id == null ) {
      if ( !row.getOrderUnitIdIsNull() )
        return false;
    } else if ( row.getOrderUnitIdIsNull() || ( order_unit_id.longValue() != row.getOrderUnitId() ) ) {
        return false;
    }
    if ( biz_date == null ) {
      if ( row.getBizDate() != null )
        return false;
    } else if ( !biz_date.equals( row.getBizDate() ) ) {
        return false;
    }
    if ( op_type == null ) {
      if ( row.getOpType() != null )
        return false;
    } else if ( !op_type.equals( row.getOpType() ) ) {
        return false;
    }
    if ( order_type == null ) {
      if ( row.getOrderType() != null )
        return false;
    } else if ( !order_type.equals( row.getOrderType() ) ) {
        return false;
    }
    if ( limit_price == null ) {
      if ( !row.getLimitPriceIsNull() )
        return false;
    } else if ( row.getLimitPriceIsNull() || ( limit_price.doubleValue() != row.getLimitPrice() ) ) {
        return false;
    }
    if ( quantity == null ) {
      if ( !row.getQuantityIsNull() )
        return false;
    } else if ( row.getQuantityIsNull() || ( quantity.doubleValue() != row.getQuantity() ) ) {
        return false;
    }
    if ( change_quantity == null ) {
      if ( !row.getChangeQuantityIsNull() )
        return false;
    } else if ( row.getChangeQuantityIsNull() || ( change_quantity.doubleValue() != row.getChangeQuantity() ) ) {
        return false;
    }
    if ( change_limit_price == null ) {
      if ( !row.getChangeLimitPriceIsNull() )
        return false;
    } else if ( row.getChangeLimitPriceIsNull() || ( change_limit_price.doubleValue() != row.getChangeLimitPrice() ) ) {
        return false;
    }
    if ( already_execd_quantity == null ) {
      if ( !row.getAlreadyExecdQuantityIsNull() )
        return false;
    } else if ( row.getAlreadyExecdQuantityIsNull() || ( already_execd_quantity.doubleValue() != row.getAlreadyExecdQuantity() ) ) {
        return false;
    }
    if ( account_id != row.getAccountId() )
      return false;
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( sub_account_id != row.getSubAccountId() )
      return false;
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( confirmed_by_sle_rcvd_q == null ) {
      if ( row.getConfirmedBySleRcvdQ() != null )
        return false;
    } else if ( !confirmed_by_sle_rcvd_q.equals( row.getConfirmedBySleRcvdQ() ) ) {
        return false;
    }
    if ( order_emp_code == null ) {
      if ( row.getOrderEmpCode() != null )
        return false;
    } else if ( !order_emp_code.equals( row.getOrderEmpCode() ) ) {
        return false;
    }
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( send_process_date_time == null ) {
      if ( row.getSendProcessDateTime() != null )
        return false;
    } else if ( !send_process_date_time.equals( row.getSendProcessDateTime() ) ) {
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
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) queue_id)
        + (product_type!=null? product_type.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (broker_name!=null? broker_name.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (order_id!=null? order_id.hashCode(): 0) 
        + (order_unit_id!=null? order_unit_id.hashCode(): 0) 
        + (biz_date!=null? biz_date.hashCode(): 0) 
        + (op_type!=null? op_type.hashCode(): 0) 
        + (order_type!=null? order_type.hashCode(): 0) 
        + (limit_price!=null? limit_price.hashCode(): 0) 
        + (quantity!=null? quantity.hashCode(): 0) 
        + (change_quantity!=null? change_quantity.hashCode(): 0) 
        + (change_limit_price!=null? change_limit_price.hashCode(): 0) 
        + (already_execd_quantity!=null? already_execd_quantity.hashCode(): 0) 
        + ((int) account_id)
        + (account_code!=null? account_code.hashCode(): 0) 
        + ((int) sub_account_id)
        + (status!=null? status.hashCode(): 0) 
        + (confirmed_by_sle_rcvd_q!=null? confirmed_by_sle_rcvd_q.hashCode(): 0) 
        + (order_emp_code!=null? order_emp_code.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (send_process_date_time!=null? send_process_date_time.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !product_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_type' must be set before inserting.");
		if ( !market_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_code' must be set before inserting.");
		if ( !broker_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'broker_name' must be set before inserting.");
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !biz_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'biz_date' must be set before inserting.");
		if ( !op_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'op_type' must be set before inserting.");
		if ( !order_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_type' must be set before inserting.");
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !sub_account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'sub_account_id' must be set before inserting.");
		if ( !status_is_set )
			throw new IllegalArgumentException("non-nullable field 'status' must be set before inserting.");
		if ( !confirmed_by_sle_rcvd_q_is_set )
			throw new IllegalArgumentException("non-nullable field 'confirmed_by_sle_rcvd_q' must be set before inserting.");
		if ( !order_emp_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_emp_code' must be set before inserting.");
		if ( !order_request_number_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_request_number' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("queue_id",new Long(queue_id));
		map.put("product_type",product_type);
		map.put("market_code",market_code);
		map.put("broker_name",broker_name);
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( order_id != null )
			map.put("order_id",order_id);
		if ( order_unit_id != null )
			map.put("order_unit_id",order_unit_id);
		map.put("biz_date",biz_date);
		map.put("op_type",op_type);
		map.put("order_type",order_type);
		if ( limit_price != null )
			map.put("limit_price",limit_price);
		if ( quantity != null )
			map.put("quantity",quantity);
		if ( change_quantity != null )
			map.put("change_quantity",change_quantity);
		if ( change_limit_price != null )
			map.put("change_limit_price",change_limit_price);
		if ( already_execd_quantity != null )
			map.put("already_execd_quantity",already_execd_quantity);
		map.put("account_id",new Long(account_id));
		map.put("account_code",account_code);
		map.put("sub_account_id",new Long(sub_account_id));
		map.put("status",status);
		map.put("confirmed_by_sle_rcvd_q",confirmed_by_sle_rcvd_q);
		map.put("order_emp_code",order_emp_code);
		map.put("order_request_number",order_request_number);
		if ( send_process_date_time != null )
			map.put("send_process_date_time",send_process_date_time);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		map.remove("rowid");
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( product_type_is_set )
			map.put("product_type",product_type);
		if ( market_code_is_set )
			map.put("market_code",market_code);
		if ( broker_name_is_set )
			map.put("broker_name",broker_name);
		if ( institution_code_is_set )
			map.put("institution_code",institution_code);
		if ( branch_code_is_set )
			map.put("branch_code",branch_code);
		map.put("product_code",product_code);
		map.put("order_id",order_id);
		map.put("order_unit_id",order_unit_id);
		if ( biz_date_is_set )
			map.put("biz_date",biz_date);
		if ( op_type_is_set )
			map.put("op_type",op_type);
		if ( order_type_is_set )
			map.put("order_type",order_type);
		map.put("limit_price",limit_price);
		map.put("quantity",quantity);
		map.put("change_quantity",change_quantity);
		map.put("change_limit_price",change_limit_price);
		map.put("already_execd_quantity",already_execd_quantity);
		if ( account_id_is_set )
			map.put("account_id",new Long(account_id));
		if ( account_code_is_set )
			map.put("account_code",account_code);
		if ( sub_account_id_is_set )
			map.put("sub_account_id",new Long(sub_account_id));
		if ( status_is_set )
			map.put("status",status);
		if ( confirmed_by_sle_rcvd_q_is_set )
			map.put("confirmed_by_sle_rcvd_q",confirmed_by_sle_rcvd_q);
		if ( order_emp_code_is_set )
			map.put("order_emp_code",order_emp_code);
		if ( order_request_number_is_set )
			map.put("order_request_number",order_request_number);
		map.put("send_process_date_time",send_process_date_time);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * <em>queue_id</em>コラムの値を取得します。
   * @@return longの値 
   */
  public final long getQueueId()
  {
    return queue_id;
  }


  /** 
   * <em>queue_id</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQueueIdIsSet() {
    return queue_id_is_set;
  }


  /** 
   * <em>product_type</em>コラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType()
  {
    return product_type;
  }


  /** 
   * <em>product_type</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductTypeIsSet() {
    return product_type_is_set;
  }


  /** 
   * <em>market_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarketCode()
  {
    return market_code;
  }


  /** 
   * <em>market_code</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketCodeIsSet() {
    return market_code_is_set;
  }


  /** 
   * <em>broker_name</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBrokerName()
  {
    return broker_name;
  }


  /** 
   * <em>broker_name</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBrokerNameIsSet() {
    return broker_name_is_set;
  }


  /** 
   * <em>institution_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>branch_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>product_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductCode()
  {
    return product_code;
  }


  /** 
   * <em>order_id</em>コラムの値を取得します。
   * @@return longの値 
   */
  public final long getOrderId()
  {
    return ( order_id==null? ((long)0): order_id.longValue() );
  }


  /** 
   * <em>order_id</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getOrderIdIsNull()
  {
    return order_id == null;
  }


  /** 
   * <em>order_unit_id</em>コラムの値を取得します。
   * @@return longの値 
   */
  public final long getOrderUnitId()
  {
    return ( order_unit_id==null? ((long)0): order_unit_id.longValue() );
  }


  /** 
   * <em>order_unit_id</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getOrderUnitIdIsNull()
  {
    return order_unit_id == null;
  }


  /** 
   * <em>biz_date</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBizDate()
  {
    return biz_date;
  }


  /** 
   * <em>biz_date</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBizDateIsSet() {
    return biz_date_is_set;
  }


  /** 
   * <em>op_type</em>コラムの値を取得します。
   * @@return webbroker3.slebase.enums.SleSendqOpTypeEnumの値 
   */
  public final webbroker3.slebase.enums.SleSendqOpTypeEnum getOpType()
  {
    return op_type;
  }


  /** 
   * <em>op_type</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpTypeIsSet() {
    return op_type_is_set;
  }


  /** 
   * <em>order_type</em>コラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum getOrderType()
  {
    return order_type;
  }


  /** 
   * <em>order_type</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderTypeIsSet() {
    return order_type_is_set;
  }


  /** 
   * <em>limit_price</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLimitPrice()
  {
    return ( limit_price==null? ((double)0): limit_price.doubleValue() );
  }


  /** 
   * <em>limit_price</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getLimitPriceIsNull()
  {
    return limit_price == null;
  }


  /** 
   * <em>quantity</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getQuantity()
  {
    return ( quantity==null? ((double)0): quantity.doubleValue() );
  }


  /** 
   * <em>quantity</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getQuantityIsNull()
  {
    return quantity == null;
  }


  /** 
   * <em>change_quantity</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getChangeQuantity()
  {
    return ( change_quantity==null? ((double)0): change_quantity.doubleValue() );
  }


  /** 
   * <em>change_quantity</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getChangeQuantityIsNull()
  {
    return change_quantity == null;
  }


  /** 
   * <em>change_limit_price</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getChangeLimitPrice()
  {
    return ( change_limit_price==null? ((double)0): change_limit_price.doubleValue() );
  }


  /** 
   * <em>change_limit_price</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getChangeLimitPriceIsNull()
  {
    return change_limit_price == null;
  }


  /** 
   * <em>already_execd_quantity</em>コラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAlreadyExecdQuantity()
  {
    return ( already_execd_quantity==null? ((double)0): already_execd_quantity.doubleValue() );
  }


  /** 
   * <em>already_execd_quantity</em>コラムの値がnullかどうか調べます。
   * @@return 対象のデータベースコラムの値がnullの場合はtrue 
   */
  public final boolean getAlreadyExecdQuantityIsNull()
  {
    return already_execd_quantity == null;
  }


  /** 
   * <em>account_id</em>コラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountId()
  {
    return account_id;
  }


  /** 
   * <em>account_id</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>account_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountCode()
  {
    return account_code;
  }


  /** 
   * <em>account_code</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsSet() {
    return account_code_is_set;
  }


  /** 
   * <em>sub_account_id</em>コラムの値を取得します。
   * @@return longの値 
   */
  public final long getSubAccountId()
  {
    return sub_account_id;
  }


  /** 
   * <em>sub_account_id</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubAccountIdIsSet() {
    return sub_account_id_is_set;
  }


  /** 
   * <em>status</em>コラムの値を取得します。
   * @@return webbroker3.slebase.enums.SleSendqProcStatusEnumの値 
   */
  public final webbroker3.slebase.enums.SleSendqProcStatusEnum getStatus()
  {
    return status;
  }


  /** 
   * <em>status</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatusIsSet() {
    return status_is_set;
  }


  /** 
   * <em>confirmed_by_sle_rcvd_q</em>コラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getConfirmedBySleRcvdQ()
  {
    return confirmed_by_sle_rcvd_q;
  }


  /** 
   * <em>confirmed_by_sle_rcvd_q</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmedBySleRcvdQIsSet() {
    return confirmed_by_sle_rcvd_q_is_set;
  }


  /** 
   * <em>order_emp_code</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderEmpCode()
  {
    return order_emp_code;
  }


  /** 
   * <em>order_emp_code</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderEmpCodeIsSet() {
    return order_emp_code_is_set;
  }


  /** 
   * <em>order_request_number</em>コラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderRequestNumber()
  {
    return order_request_number;
  }


  /** 
   * <em>order_request_number</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRequestNumberIsSet() {
    return order_request_number_is_set;
  }


  /** 
   * <em>send_process_date_time</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getSendProcessDateTime()
  {
    return send_process_date_time;
  }


  /** 
   * <em>created_timestamp</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>コラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>コラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new SleSendQPK(queue_id);
  }


  /** 
   * <em>queue_id</em>コラムの値を設定します。 
   *
   * @@param p_queueId <em>queue_id</em>コラムの値をあらわす18桁以下のlong値 
   */
  public final void setQueueId( long p_queueId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    queue_id = p_queueId;
    queue_id_is_set = true;
  }


  /** 
   * <em>product_type</em>コラムの値を設定します。 
   *
   * @@param p_productType <em>product_type</em>コラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   */
  public final void setProductType( com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_type = p_productType;
    product_type_is_set = true;
  }


  /** 
   * <em>market_code</em>コラムの値を設定します。 
   *
   * @@param p_marketCode <em>market_code</em>コラムの値をあらわす20桁以下のString値 
   */
  public final void setMarketCode( String p_marketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_code = p_marketCode;
    market_code_is_set = true;
  }


  /** 
   * <em>broker_name</em>コラムの値を設定します。 
   *
   * @@param p_brokerName <em>broker_name</em>コラムの値をあらわす50桁以下のString値 
   */
  public final void setBrokerName( String p_brokerName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    broker_name = p_brokerName;
    broker_name_is_set = true;
  }


  /** 
   * <em>institution_code</em>コラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>コラムの値をあらわす3桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
  }


  /** 
   * <em>branch_code</em>コラムの値を設定します。 
   *
   * @@param p_branchCode <em>branch_code</em>コラムの値をあらわす3桁以下のString値 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
  }


  /** 
   * <em>product_code</em>コラムの値を設定します。 
   *
   * @@param p_productCode <em>product_code</em>コラムの値をあらわす20桁以下のString値 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
  }


  /** 
   * <em>order_id</em>コラムの値を設定します。 
   *
   * @@param p_orderId <em>order_id</em>コラムの値をあらわす18桁以下のlong値 
   */
  public final void setOrderId( long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = new Long(p_orderId);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>order_id</em>コラムに値を設定します。 
   */
  public final void setOrderId( Long p_orderId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_id = p_orderId;
  }


  /** 
   * <em>order_unit_id</em>コラムの値を設定します。 
   *
   * @@param p_orderUnitId <em>order_unit_id</em>コラムの値をあらわす18桁以下のlong値 
   */
  public final void setOrderUnitId( long p_orderUnitId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_unit_id = new Long(p_orderUnitId);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>order_unit_id</em>コラムに値を設定します。 
   */
  public final void setOrderUnitId( Long p_orderUnitId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_unit_id = p_orderUnitId;
  }


  /** 
   * <em>biz_date</em>コラムの値を設定します。 
   *
   * @@param p_bizDate <em>biz_date</em>コラムの値をあらわす8桁以下のString値 
   */
  public final void setBizDate( String p_bizDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    biz_date = p_bizDate;
    biz_date_is_set = true;
  }


  /** 
   * <em>op_type</em>コラムの値を設定します。 
   *
   * @@param p_opType <em>op_type</em>コラムの値をあらわす6桁以下のwebbroker3.slebase.enums.SleSendqOpTypeEnum値 
   */
  public final void setOpType( webbroker3.slebase.enums.SleSendqOpTypeEnum p_opType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    op_type = p_opType;
    op_type_is_set = true;
  }


  /** 
   * <em>order_type</em>コラムの値を設定します。 
   *
   * @@param p_orderType <em>order_type</em>コラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum値 
   */
  public final void setOrderType( com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum p_orderType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_type = p_orderType;
    order_type_is_set = true;
  }


  /** 
   * <em>limit_price</em>コラムの値を設定します。 
   *
   * @@param p_limitPrice <em>limit_price</em>コラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLimitPrice( double p_limitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    limit_price = new Double(p_limitPrice);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>limit_price</em>コラムに値を設定します。 
   */
  public final void setLimitPrice( Double p_limitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    limit_price = p_limitPrice;
  }


  /** 
   * <em>quantity</em>コラムの値を設定します。 
   *
   * @@param p_quantity <em>quantity</em>コラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setQuantity( double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = new Double(p_quantity);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>quantity</em>コラムに値を設定します。 
   */
  public final void setQuantity( Double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
  }


  /** 
   * <em>change_quantity</em>コラムの値を設定します。 
   *
   * @@param p_changeQuantity <em>change_quantity</em>コラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setChangeQuantity( double p_changeQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change_quantity = new Double(p_changeQuantity);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>change_quantity</em>コラムに値を設定します。 
   */
  public final void setChangeQuantity( Double p_changeQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    change_quantity = p_changeQuantity;
  }


  /** 
   * <em>change_limit_price</em>コラムの値を設定します。 
   *
   * @@param p_changeLimitPrice <em>change_limit_price</em>コラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setChangeLimitPrice( double p_changeLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change_limit_price = new Double(p_changeLimitPrice);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>change_limit_price</em>コラムに値を設定します。 
   */
  public final void setChangeLimitPrice( Double p_changeLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    change_limit_price = p_changeLimitPrice;
  }


  /** 
   * <em>already_execd_quantity</em>コラムの値を設定します。 
   *
   * @@param p_alreadyExecdQuantity <em>already_execd_quantity</em>コラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAlreadyExecdQuantity( double p_alreadyExecdQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    already_execd_quantity = new Double(p_alreadyExecdQuantity);
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>already_execd_quantity</em>コラムに値を設定します。 
   */
  public final void setAlreadyExecdQuantity( Double p_alreadyExecdQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    already_execd_quantity = p_alreadyExecdQuantity;
  }


  /** 
   * <em>account_id</em>コラムの値を設定します。 
   *
   * @@param p_accountId <em>account_id</em>コラムの値をあらわす18桁以下のlong値 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
  }


  /** 
   * <em>account_code</em>コラムの値を設定します。 
   *
   * @@param p_accountCode <em>account_code</em>コラムの値をあらわす20桁以下のString値 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
  }


  /** 
   * <em>sub_account_id</em>コラムの値を設定します。 
   *
   * @@param p_subAccountId <em>sub_account_id</em>コラムの値をあらわす18桁以下のlong値 
   */
  public final void setSubAccountId( long p_subAccountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_account_id = p_subAccountId;
    sub_account_id_is_set = true;
  }


  /** 
   * <em>status</em>コラムの値を設定します。 
   *
   * @@param p_status <em>status</em>コラムの値をあらわす6桁以下のwebbroker3.slebase.enums.SleSendqProcStatusEnum値 
   */
  public final void setStatus( webbroker3.slebase.enums.SleSendqProcStatusEnum p_status )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    status = p_status;
    status_is_set = true;
  }


  /** 
   * <em>confirmed_by_sle_rcvd_q</em>コラムの値を設定します。 
   *
   * @@param p_confirmedBySleRcvdQ <em>confirmed_by_sle_rcvd_q</em>コラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setConfirmedBySleRcvdQ( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_confirmedBySleRcvdQ )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirmed_by_sle_rcvd_q = p_confirmedBySleRcvdQ;
    confirmed_by_sle_rcvd_q_is_set = true;
  }


  /** 
   * <em>order_emp_code</em>コラムの値を設定します。 
   *
   * @@param p_orderEmpCode <em>order_emp_code</em>コラムの値をあらわす7桁以下のString値 
   */
  public final void setOrderEmpCode( String p_orderEmpCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_emp_code = p_orderEmpCode;
    order_emp_code_is_set = true;
  }


  /** 
   * <em>order_request_number</em>コラムの値を設定します。 
   *
   * @@param p_orderRequestNumber <em>order_request_number</em>コラムの値をあらわす9桁以下のString値 
   */
  public final void setOrderRequestNumber( String p_orderRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_request_number = p_orderRequestNumber;
    order_request_number_is_set = true;
  }


  /** 
   * <em>send_process_date_time</em>コラムの値を設定します。 
   *
   * @@param p_sendProcessDateTime <em>send_process_date_time</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setSendProcessDateTime( java.sql.Timestamp p_sendProcessDateTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    send_process_date_time = p_sendProcessDateTime;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>send_process_date_time</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setSendProcessDateTime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    send_process_date_time = (date==null? null: new java.sql.Timestamp( date.getTime() ));
  }


  /** 
   * <em>created_timestamp</em>コラムの値を設定します。 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>created_timestamp</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
  }


  /** 
   * <em>last_updated_timestamp</em>コラムの値を設定します。 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>コラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_timestamp</em>コラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("already_execd_quantity") ) {
                    return this.already_execd_quantity;
                }
                else if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                else if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                break;
            case 'b':
                if ( name.equals("broker_name") ) {
                    return this.broker_name;
                }
                else if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("biz_date") ) {
                    return this.biz_date;
                }
                break;
            case 'c':
                if ( name.equals("change_quantity") ) {
                    return this.change_quantity;
                }
                else if ( name.equals("change_limit_price") ) {
                    return this.change_limit_price;
                }
                else if ( name.equals("confirmed_by_sle_rcvd_q") ) {
                    return this.confirmed_by_sle_rcvd_q;
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
                if ( name.equals("limit_price") ) {
                    return this.limit_price;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                break;
            case 'o':
                if ( name.equals("order_id") ) {
                    return this.order_id;
                }
                else if ( name.equals("order_unit_id") ) {
                    return this.order_unit_id;
                }
                else if ( name.equals("op_type") ) {
                    return this.op_type;
                }
                else if ( name.equals("order_type") ) {
                    return this.order_type;
                }
                else if ( name.equals("order_emp_code") ) {
                    return this.order_emp_code;
                }
                else if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                else if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                break;
            case 'q':
                if ( name.equals("queue_id") ) {
                    return new Long( queue_id );
                }
                else if ( name.equals("quantity") ) {
                    return this.quantity;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    return new Long( sub_account_id );
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                else if ( name.equals("send_process_date_time") ) {
                    return this.send_process_date_time;
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
                if ( name.equals("already_execd_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'already_execd_quantity' must be Double: '"+value+"' is not." );
                    this.already_execd_quantity = (Double) value;
                    return;
                }
                else if ( name.equals("account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = ((Long) value).longValue();
                    this.account_id_is_set = true;
                    return;
                }
                else if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    this.account_code_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("broker_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'broker_name' must be String: '"+value+"' is not." );
                    this.broker_name = (String) value;
                    this.broker_name_is_set = true;
                    return;
                }
                else if ( name.equals("branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    this.branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("biz_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'biz_date' must be String: '"+value+"' is not." );
                    this.biz_date = (String) value;
                    this.biz_date_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("change_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'change_quantity' must be Double: '"+value+"' is not." );
                    this.change_quantity = (Double) value;
                    return;
                }
                else if ( name.equals("change_limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'change_limit_price' must be Double: '"+value+"' is not." );
                    this.change_limit_price = (Double) value;
                    return;
                }
                else if ( name.equals("confirmed_by_sle_rcvd_q") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'confirmed_by_sle_rcvd_q' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.confirmed_by_sle_rcvd_q = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    this.confirmed_by_sle_rcvd_q_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    this.institution_code_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'limit_price' must be Double: '"+value+"' is not." );
                    this.limit_price = (Double) value;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    this.market_code_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_id' must be Long: '"+value+"' is not." );
                    this.order_id = (Long) value;
                    return;
                }
                else if ( name.equals("order_unit_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'order_unit_id' must be Long: '"+value+"' is not." );
                    this.order_unit_id = (Long) value;
                    return;
                }
                else if ( name.equals("op_type") ) {
                    if ( !(value instanceof webbroker3.slebase.enums.SleSendqOpTypeEnum) )
                        throw new IllegalArgumentException( "value for 'op_type' must be webbroker3.slebase.enums.SleSendqOpTypeEnum: '"+value+"' is not." );
                    this.op_type = (webbroker3.slebase.enums.SleSendqOpTypeEnum) value;
                    this.op_type_is_set = true;
                    return;
                }
                else if ( name.equals("order_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum) )
                        throw new IllegalArgumentException( "value for 'order_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum: '"+value+"' is not." );
                    this.order_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum) value;
                    this.order_type_is_set = true;
                    return;
                }
                else if ( name.equals("order_emp_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_emp_code' must be String: '"+value+"' is not." );
                    this.order_emp_code = (String) value;
                    this.order_emp_code_is_set = true;
                    return;
                }
                else if ( name.equals("order_request_number") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_request_number' must be String: '"+value+"' is not." );
                    this.order_request_number = (String) value;
                    this.order_request_number_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    this.product_type_is_set = true;
                    return;
                }
                else if ( name.equals("product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("queue_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'queue_id' must be Long: '"+value+"' is not." );
                    this.queue_id = ((Long) value).longValue();
                    this.queue_id_is_set = true;
                    return;
                }
                else if ( name.equals("quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Double: '"+value+"' is not." );
                    this.quantity = (Double) value;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sub_account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'sub_account_id' must be Long: '"+value+"' is not." );
                    this.sub_account_id = ((Long) value).longValue();
                    this.sub_account_id_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
                    if ( !(value instanceof webbroker3.slebase.enums.SleSendqProcStatusEnum) )
                        throw new IllegalArgumentException( "value for 'status' must be webbroker3.slebase.enums.SleSendqProcStatusEnum: '"+value+"' is not." );
                    this.status = (webbroker3.slebase.enums.SleSendqProcStatusEnum) value;
                    this.status_is_set = true;
                    return;
                }
                else if ( name.equals("send_process_date_time") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'send_process_date_time' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.send_process_date_time = (java.sql.Timestamp) value;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
