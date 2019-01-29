head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostRuitoOrderNotifyParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.xbruito.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * host_ruito_order_notifyテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostRuitoOrderNotifyRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostRuitoOrderNotifyParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostRuitoOrderNotifyParams}が{@@link HostRuitoOrderNotifyRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostRuitoOrderNotifyPK 
 * @@see HostRuitoOrderNotifyRow 
 */
public class HostRuitoOrderNotifyParams extends Params implements HostRuitoOrderNotifyRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_ruito_order_notify";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostRuitoOrderNotifyRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostRuitoOrderNotifyRow.TYPE;
  }


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
   * <em>bizdate_div</em>カラムの値 
   */
  public  String  bizdate_div;

  /** 
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>course</em>カラムの値 
   */
  public  String  course;

  /** 
   * <em>plan</em>カラムの値 
   */
  public  String  plan;

  /** 
   * <em>order_div</em>カラムの値 
   */
  public  String  order_div;

  /** 
   * <em>amount</em>カラムの値 
   */
  public  Long  amount;

  /** 
   * <em>payment_method</em>カラムの値 
   */
  public  String  payment_method;

  /** 
   * <em>order_date</em>カラムの値 
   */
  public  java.sql.Timestamp  order_date;

  /** 
   * <em>delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>order_rcv_div</em>カラムの値 
   */
  public  String  order_rcv_div;

  /** 
   * <em>order_cancel_div</em>カラムの値 
   */
  public  String  order_cancel_div;

  /** 
   * <em>order_cancel_date</em>カラムの値 
   */
  public  java.sql.Timestamp  order_cancel_date;

  /** 
   * <em>error_code</em>カラムの値 
   */
  public  String  error_code;

  /** 
   * <em>specify_div</em>カラムの値 
   */
  public  String  specify_div;

  /** 
   * <em>channel</em>カラムの値 
   */
  public  String  channel;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

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
  private boolean bizdate_div_is_set = false;
  private boolean bizdate_div_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean course_is_set = false;
  private boolean course_is_modified = false;
  private boolean plan_is_set = false;
  private boolean plan_is_modified = false;
  private boolean order_div_is_set = false;
  private boolean order_div_is_modified = false;
  private boolean amount_is_set = false;
  private boolean amount_is_modified = false;
  private boolean payment_method_is_set = false;
  private boolean payment_method_is_modified = false;
  private boolean order_date_is_set = false;
  private boolean order_date_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean order_rcv_div_is_set = false;
  private boolean order_rcv_div_is_modified = false;
  private boolean order_cancel_div_is_set = false;
  private boolean order_cancel_div_is_modified = false;
  private boolean order_cancel_date_is_set = false;
  private boolean order_cancel_date_is_modified = false;
  private boolean error_code_is_set = false;
  private boolean error_code_is_modified = false;
  private boolean specify_div_is_set = false;
  private boolean specify_div_is_modified = false;
  private boolean channel_is_set = false;
  private boolean channel_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "request_code=" +request_code
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "bizdate_div=" +bizdate_div
      + "," + "order_request_number=" +order_request_number
      + "," + "course=" +course
      + "," + "plan=" +plan
      + "," + "order_div=" +order_div
      + "," + "amount=" +amount
      + "," + "payment_method=" +payment_method
      + "," + "order_date=" +order_date
      + "," + "delivery_date=" +delivery_date
      + "," + "order_rcv_div=" +order_rcv_div
      + "," + "order_cancel_div=" +order_cancel_div
      + "," + "order_cancel_date=" +order_cancel_date
      + "," + "error_code=" +error_code
      + "," + "specify_div=" +specify_div
      + "," + "channel=" +channel
      + "," + "status=" +status
      + "}";
  }


  /** 
   * 値が未設定のHostRuitoOrderNotifyParamsオブジェクトを作成します。 
   */
  public HostRuitoOrderNotifyParams() {
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のHostRuitoOrderNotifyRowオブジェクトの値を利用してHostRuitoOrderNotifyParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostRuitoOrderNotifyRowオブジェクト 
   */
  public HostRuitoOrderNotifyParams( HostRuitoOrderNotifyRow p_row )
  {
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
    bizdate_div = p_row.getBizdateDiv();
    bizdate_div_is_set = p_row.getBizdateDivIsSet();
    bizdate_div_is_modified = p_row.getBizdateDivIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    course = p_row.getCourse();
    course_is_set = p_row.getCourseIsSet();
    course_is_modified = p_row.getCourseIsModified();
    plan = p_row.getPlan();
    plan_is_set = p_row.getPlanIsSet();
    plan_is_modified = p_row.getPlanIsModified();
    order_div = p_row.getOrderDiv();
    order_div_is_set = p_row.getOrderDivIsSet();
    order_div_is_modified = p_row.getOrderDivIsModified();
    if ( !p_row.getAmountIsNull() )
      amount = new Long( p_row.getAmount() );
    amount_is_set = p_row.getAmountIsSet();
    amount_is_modified = p_row.getAmountIsModified();
    payment_method = p_row.getPaymentMethod();
    payment_method_is_set = p_row.getPaymentMethodIsSet();
    payment_method_is_modified = p_row.getPaymentMethodIsModified();
    order_date = p_row.getOrderDate();
    order_date_is_set = p_row.getOrderDateIsSet();
    order_date_is_modified = p_row.getOrderDateIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    order_rcv_div = p_row.getOrderRcvDiv();
    order_rcv_div_is_set = p_row.getOrderRcvDivIsSet();
    order_rcv_div_is_modified = p_row.getOrderRcvDivIsModified();
    order_cancel_div = p_row.getOrderCancelDiv();
    order_cancel_div_is_set = p_row.getOrderCancelDivIsSet();
    order_cancel_div_is_modified = p_row.getOrderCancelDivIsModified();
    order_cancel_date = p_row.getOrderCancelDate();
    order_cancel_date_is_set = p_row.getOrderCancelDateIsSet();
    order_cancel_date_is_modified = p_row.getOrderCancelDateIsModified();
    error_code = p_row.getErrorCode();
    error_code_is_set = p_row.getErrorCodeIsSet();
    error_code_is_modified = p_row.getErrorCodeIsModified();
    specify_div = p_row.getSpecifyDiv();
    specify_div_is_set = p_row.getSpecifyDivIsSet();
    specify_div_is_modified = p_row.getSpecifyDivIsModified();
    channel = p_row.getChannel();
    channel_is_set = p_row.getChannelIsSet();
    channel_is_modified = p_row.getChannelIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
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
      bizdate_div_is_set = true;
      bizdate_div_is_modified = true;
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      course_is_set = true;
      course_is_modified = true;
      plan_is_set = true;
      plan_is_modified = true;
      order_div_is_set = true;
      order_div_is_modified = true;
      amount_is_set = true;
      amount_is_modified = true;
      payment_method_is_set = true;
      payment_method_is_modified = true;
      order_date_is_set = true;
      order_date_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      order_rcv_div_is_set = true;
      order_rcv_div_is_modified = true;
      order_cancel_div_is_set = true;
      order_cancel_div_is_modified = true;
      order_cancel_date_is_set = true;
      order_cancel_date_is_modified = true;
      error_code_is_set = true;
      error_code_is_modified = true;
      specify_div_is_set = true;
      specify_div_is_modified = true;
      channel_is_set = true;
      channel_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof HostRuitoOrderNotifyRow ) )
       return false;
    return fieldsEqual( (HostRuitoOrderNotifyRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostRuitoOrderNotifyRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostRuitoOrderNotifyRow row )
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
    if ( bizdate_div == null ) {
      if ( row.getBizdateDiv() != null )
        return false;
    } else if ( !bizdate_div.equals( row.getBizdateDiv() ) ) {
        return false;
    }
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( course == null ) {
      if ( row.getCourse() != null )
        return false;
    } else if ( !course.equals( row.getCourse() ) ) {
        return false;
    }
    if ( plan == null ) {
      if ( row.getPlan() != null )
        return false;
    } else if ( !plan.equals( row.getPlan() ) ) {
        return false;
    }
    if ( order_div == null ) {
      if ( row.getOrderDiv() != null )
        return false;
    } else if ( !order_div.equals( row.getOrderDiv() ) ) {
        return false;
    }
    if ( amount == null ) {
      if ( !row.getAmountIsNull() )
        return false;
    } else if ( row.getAmountIsNull() || ( amount.longValue() != row.getAmount() ) ) {
        return false;
    }
    if ( payment_method == null ) {
      if ( row.getPaymentMethod() != null )
        return false;
    } else if ( !payment_method.equals( row.getPaymentMethod() ) ) {
        return false;
    }
    if ( order_date == null ) {
      if ( row.getOrderDate() != null )
        return false;
    } else if ( !order_date.equals( row.getOrderDate() ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( order_rcv_div == null ) {
      if ( row.getOrderRcvDiv() != null )
        return false;
    } else if ( !order_rcv_div.equals( row.getOrderRcvDiv() ) ) {
        return false;
    }
    if ( order_cancel_div == null ) {
      if ( row.getOrderCancelDiv() != null )
        return false;
    } else if ( !order_cancel_div.equals( row.getOrderCancelDiv() ) ) {
        return false;
    }
    if ( order_cancel_date == null ) {
      if ( row.getOrderCancelDate() != null )
        return false;
    } else if ( !order_cancel_date.equals( row.getOrderCancelDate() ) ) {
        return false;
    }
    if ( error_code == null ) {
      if ( row.getErrorCode() != null )
        return false;
    } else if ( !error_code.equals( row.getErrorCode() ) ) {
        return false;
    }
    if ( specify_div == null ) {
      if ( row.getSpecifyDiv() != null )
        return false;
    } else if ( !specify_div.equals( row.getSpecifyDiv() ) ) {
        return false;
    }
    if ( channel == null ) {
      if ( row.getChannel() != null )
        return false;
    } else if ( !channel.equals( row.getChannel() ) ) {
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
      return  (request_code!=null? request_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (trader_code!=null? trader_code.hashCode(): 0) 
        + (bizdate_div!=null? bizdate_div.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (course!=null? course.hashCode(): 0) 
        + (plan!=null? plan.hashCode(): 0) 
        + (order_div!=null? order_div.hashCode(): 0) 
        + (amount!=null? amount.hashCode(): 0) 
        + (payment_method!=null? payment_method.hashCode(): 0) 
        + (order_date!=null? order_date.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (order_rcv_div!=null? order_rcv_div.hashCode(): 0) 
        + (order_cancel_div!=null? order_cancel_div.hashCode(): 0) 
        + (order_cancel_date!=null? order_cancel_date.hashCode(): 0) 
        + (error_code!=null? error_code.hashCode(): 0) 
        + (specify_div!=null? specify_div.hashCode(): 0) 
        + (channel!=null? channel.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
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
		if ( !bizdate_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'bizdate_div' must be set before inserting.");
		if ( !order_request_number_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_request_number' must be set before inserting.");
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
		map.put("bizdate_div",bizdate_div);
		map.put("order_request_number",order_request_number);
		if ( course != null )
			map.put("course",course);
		if ( plan != null )
			map.put("plan",plan);
		if ( order_div != null )
			map.put("order_div",order_div);
		if ( amount != null )
			map.put("amount",amount);
		if ( payment_method != null )
			map.put("payment_method",payment_method);
		if ( order_date != null )
			map.put("order_date",order_date);
		if ( delivery_date != null )
			map.put("delivery_date",delivery_date);
		if ( order_rcv_div != null )
			map.put("order_rcv_div",order_rcv_div);
		if ( order_cancel_div != null )
			map.put("order_cancel_div",order_cancel_div);
		if ( order_cancel_date != null )
			map.put("order_cancel_date",order_cancel_date);
		if ( error_code != null )
			map.put("error_code",error_code);
		if ( specify_div != null )
			map.put("specify_div",specify_div);
		if ( channel != null )
			map.put("channel",channel);
		if ( status != null )
			map.put("status",status);
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
		if ( bizdate_div_is_modified )
			map.put("bizdate_div",bizdate_div);
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( course_is_modified )
			map.put("course",course);
		if ( plan_is_modified )
			map.put("plan",plan);
		if ( order_div_is_modified )
			map.put("order_div",order_div);
		if ( amount_is_modified )
			map.put("amount",amount);
		if ( payment_method_is_modified )
			map.put("payment_method",payment_method);
		if ( order_date_is_modified )
			map.put("order_date",order_date);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( order_rcv_div_is_modified )
			map.put("order_rcv_div",order_rcv_div);
		if ( order_cancel_div_is_modified )
			map.put("order_cancel_div",order_cancel_div);
		if ( order_cancel_date_is_modified )
			map.put("order_cancel_date",order_cancel_date);
		if ( error_code_is_modified )
			map.put("error_code",error_code);
		if ( specify_div_is_modified )
			map.put("specify_div",specify_div);
		if ( channel_is_modified )
			map.put("channel",channel);
		if ( status_is_modified )
			map.put("status",status);
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
			if ( bizdate_div_is_set )
				map.put("bizdate_div",bizdate_div);
			if ( order_request_number_is_set )
				map.put("order_request_number",order_request_number);
			map.put("course",course);
			map.put("plan",plan);
			map.put("order_div",order_div);
			map.put("amount",amount);
			map.put("payment_method",payment_method);
			map.put("order_date",order_date);
			map.put("delivery_date",delivery_date);
			map.put("order_rcv_div",order_rcv_div);
			map.put("order_cancel_div",order_cancel_div);
			map.put("order_cancel_date",order_cancel_date);
			map.put("error_code",error_code);
			map.put("specify_div",specify_div);
			map.put("channel",channel);
			map.put("status",status);
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
   * <em>bizdate_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBizdateDiv()
  {
    return bizdate_div;
  }


  /** 
   * <em>bizdate_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBizdateDivIsSet() {
    return bizdate_div_is_set;
  }


  /** 
   * <em>bizdate_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBizdateDivIsModified() {
    return bizdate_div_is_modified;
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
   * <em>course</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCourse()
  {
    return course;
  }


  /** 
   * <em>course</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCourseIsSet() {
    return course_is_set;
  }


  /** 
   * <em>course</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCourseIsModified() {
    return course_is_modified;
  }


  /** 
   * <em>plan</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPlan()
  {
    return plan;
  }


  /** 
   * <em>plan</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPlanIsSet() {
    return plan_is_set;
  }


  /** 
   * <em>plan</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPlanIsModified() {
    return plan_is_modified;
  }


  /** 
   * <em>order_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderDiv()
  {
    return order_div;
  }


  /** 
   * <em>order_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderDivIsSet() {
    return order_div_is_set;
  }


  /** 
   * <em>order_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderDivIsModified() {
    return order_div_is_modified;
  }


  /** 
   * <em>amount</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmount()
  {
    return ( amount==null? ((long)0): amount.longValue() );
  }


  /** 
   * <em>amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountIsNull()
  {
    return amount == null;
  }


  /** 
   * <em>amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountIsSet() {
    return amount_is_set;
  }


  /** 
   * <em>amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountIsModified() {
    return amount_is_modified;
  }


  /** 
   * <em>payment_method</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaymentMethod()
  {
    return payment_method;
  }


  /** 
   * <em>payment_method</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentMethodIsSet() {
    return payment_method_is_set;
  }


  /** 
   * <em>payment_method</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentMethodIsModified() {
    return payment_method_is_modified;
  }


  /** 
   * <em>order_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOrderDate()
  {
    return order_date;
  }


  /** 
   * <em>order_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderDateIsSet() {
    return order_date_is_set;
  }


  /** 
   * <em>order_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderDateIsModified() {
    return order_date_is_modified;
  }


  /** 
   * <em>delivery_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getDeliveryDate()
  {
    return delivery_date;
  }


  /** 
   * <em>delivery_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateIsSet() {
    return delivery_date_is_set;
  }


  /** 
   * <em>delivery_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateIsModified() {
    return delivery_date_is_modified;
  }


  /** 
   * <em>order_rcv_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderRcvDiv()
  {
    return order_rcv_div;
  }


  /** 
   * <em>order_rcv_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRcvDivIsSet() {
    return order_rcv_div_is_set;
  }


  /** 
   * <em>order_rcv_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderRcvDivIsModified() {
    return order_rcv_div_is_modified;
  }


  /** 
   * <em>order_cancel_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderCancelDiv()
  {
    return order_cancel_div;
  }


  /** 
   * <em>order_cancel_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderCancelDivIsSet() {
    return order_cancel_div_is_set;
  }


  /** 
   * <em>order_cancel_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderCancelDivIsModified() {
    return order_cancel_div_is_modified;
  }


  /** 
   * <em>order_cancel_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOrderCancelDate()
  {
    return order_cancel_date;
  }


  /** 
   * <em>order_cancel_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderCancelDateIsSet() {
    return order_cancel_date_is_set;
  }


  /** 
   * <em>order_cancel_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderCancelDateIsModified() {
    return order_cancel_date_is_modified;
  }


  /** 
   * <em>error_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getErrorCode()
  {
    return error_code;
  }


  /** 
   * <em>error_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorCodeIsSet() {
    return error_code_is_set;
  }


  /** 
   * <em>error_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorCodeIsModified() {
    return error_code_is_modified;
  }


  /** 
   * <em>specify_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSpecifyDiv()
  {
    return specify_div;
  }


  /** 
   * <em>specify_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecifyDivIsSet() {
    return specify_div_is_set;
  }


  /** 
   * <em>specify_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecifyDivIsModified() {
    return specify_div_is_modified;
  }


  /** 
   * <em>channel</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getChannel()
  {
    return channel;
  }


  /** 
   * <em>channel</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChannelIsSet() {
    return channel_is_set;
  }


  /** 
   * <em>channel</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChannelIsModified() {
    return channel_is_modified;
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
    throw new UnsupportedOperationException("Table host_ruito_order_notify has no primary key.");
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
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす2桁以下のString値 
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
   * <em>bizdate_div</em>カラムの値を設定します。 
   *
   * @@param p_bizdateDiv <em>bizdate_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBizdateDiv( String p_bizdateDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bizdate_div = p_bizdateDiv;
    bizdate_div_is_set = true;
    bizdate_div_is_modified = true;
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
   * <em>course</em>カラムの値を設定します。 
   *
   * @@param p_course <em>course</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCourse( String p_course )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    course = p_course;
    course_is_set = true;
    course_is_modified = true;
  }


  /** 
   * <em>plan</em>カラムの値を設定します。 
   *
   * @@param p_plan <em>plan</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPlan( String p_plan )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    plan = p_plan;
    plan_is_set = true;
    plan_is_modified = true;
  }


  /** 
   * <em>order_div</em>カラムの値を設定します。 
   *
   * @@param p_orderDiv <em>order_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderDiv( String p_orderDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_div = p_orderDiv;
    order_div_is_set = true;
    order_div_is_modified = true;
  }


  /** 
   * <em>amount</em>カラムの値を設定します。 
   *
   * @@param p_amount <em>amount</em>カラムの値をあらわす11桁以下のlong値 
   */
  public final void setAmount( long p_amount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount = new Long(p_amount);
    amount_is_set = true;
    amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount</em>カラムに値を設定します。 
   */
  public final void setAmount( Long p_amount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount = p_amount;
    amount_is_set = true;
    amount_is_modified = true;
  }


  /** 
   * <em>payment_method</em>カラムの値を設定します。 
   *
   * @@param p_paymentMethod <em>payment_method</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPaymentMethod( String p_paymentMethod )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_method = p_paymentMethod;
    payment_method_is_set = true;
    payment_method_is_modified = true;
  }


  /** 
   * <em>order_date</em>カラムの値を設定します。 
   *
   * @@param p_orderDate <em>order_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOrderDate( java.sql.Timestamp p_orderDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_date = p_orderDate;
    order_date_is_set = true;
    order_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>order_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOrderDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    order_date_is_set = true;
    order_date_is_modified = true;
  }


  /** 
   * <em>delivery_date</em>カラムの値を設定します。 
   *
   * @@param p_deliveryDate <em>delivery_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setDeliveryDate( java.sql.Timestamp p_deliveryDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = p_deliveryDate;
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>delivery_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setDeliveryDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


  /** 
   * <em>order_rcv_div</em>カラムの値を設定します。 
   *
   * @@param p_orderRcvDiv <em>order_rcv_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderRcvDiv( String p_orderRcvDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_rcv_div = p_orderRcvDiv;
    order_rcv_div_is_set = true;
    order_rcv_div_is_modified = true;
  }


  /** 
   * <em>order_cancel_div</em>カラムの値を設定します。 
   *
   * @@param p_orderCancelDiv <em>order_cancel_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderCancelDiv( String p_orderCancelDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_cancel_div = p_orderCancelDiv;
    order_cancel_div_is_set = true;
    order_cancel_div_is_modified = true;
  }


  /** 
   * <em>order_cancel_date</em>カラムの値を設定します。 
   *
   * @@param p_orderCancelDate <em>order_cancel_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOrderCancelDate( java.sql.Timestamp p_orderCancelDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_cancel_date = p_orderCancelDate;
    order_cancel_date_is_set = true;
    order_cancel_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>order_cancel_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOrderCancelDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_cancel_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    order_cancel_date_is_set = true;
    order_cancel_date_is_modified = true;
  }


  /** 
   * <em>error_code</em>カラムの値を設定します。 
   *
   * @@param p_errorCode <em>error_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setErrorCode( String p_errorCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    error_code = p_errorCode;
    error_code_is_set = true;
    error_code_is_modified = true;
  }


  /** 
   * <em>specify_div</em>カラムの値を設定します。 
   *
   * @@param p_specifyDiv <em>specify_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSpecifyDiv( String p_specifyDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    specify_div = p_specifyDiv;
    specify_div_is_set = true;
    specify_div_is_modified = true;
  }


  /** 
   * <em>channel</em>カラムの値を設定します。 
   *
   * @@param p_channel <em>channel</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setChannel( String p_channel )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    channel = p_channel;
    channel_is_set = true;
    channel_is_modified = true;
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
                if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("amount") ) {
                    return this.amount;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("bizdate_div") ) {
                    return this.bizdate_div;
                }
                break;
            case 'c':
                if ( name.equals("course") ) {
                    return this.course;
                }
                else if ( name.equals("channel") ) {
                    return this.channel;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                break;
            case 'e':
                if ( name.equals("error_code") ) {
                    return this.error_code;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("order_div") ) {
                    return this.order_div;
                }
                else if ( name.equals("order_date") ) {
                    return this.order_date;
                }
                else if ( name.equals("order_rcv_div") ) {
                    return this.order_rcv_div;
                }
                else if ( name.equals("order_cancel_div") ) {
                    return this.order_cancel_div;
                }
                else if ( name.equals("order_cancel_date") ) {
                    return this.order_cancel_date;
                }
                break;
            case 'p':
                if ( name.equals("plan") ) {
                    return this.plan;
                }
                else if ( name.equals("payment_method") ) {
                    return this.payment_method;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                break;
            case 's':
                if ( name.equals("specify_div") ) {
                    return this.specify_div;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
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
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount' must be Long: '"+value+"' is not." );
                    this.amount = (Long) value;
                    if (this.amount_is_set)
                        this.amount_is_modified = true;
                    this.amount_is_set = true;
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
                else if ( name.equals("bizdate_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bizdate_div' must be String: '"+value+"' is not." );
                    this.bizdate_div = (String) value;
                    if (this.bizdate_div_is_set)
                        this.bizdate_div_is_modified = true;
                    this.bizdate_div_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("course") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'course' must be String: '"+value+"' is not." );
                    this.course = (String) value;
                    if (this.course_is_set)
                        this.course_is_modified = true;
                    this.course_is_set = true;
                    return;
                }
                else if ( name.equals("channel") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'channel' must be String: '"+value+"' is not." );
                    this.channel = (String) value;
                    if (this.channel_is_set)
                        this.channel_is_modified = true;
                    this.channel_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.delivery_date = (java.sql.Timestamp) value;
                    if (this.delivery_date_is_set)
                        this.delivery_date_is_modified = true;
                    this.delivery_date_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("error_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'error_code' must be String: '"+value+"' is not." );
                    this.error_code = (String) value;
                    if (this.error_code_is_set)
                        this.error_code_is_modified = true;
                    this.error_code_is_set = true;
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
                else if ( name.equals("order_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_div' must be String: '"+value+"' is not." );
                    this.order_div = (String) value;
                    if (this.order_div_is_set)
                        this.order_div_is_modified = true;
                    this.order_div_is_set = true;
                    return;
                }
                else if ( name.equals("order_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'order_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.order_date = (java.sql.Timestamp) value;
                    if (this.order_date_is_set)
                        this.order_date_is_modified = true;
                    this.order_date_is_set = true;
                    return;
                }
                else if ( name.equals("order_rcv_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_rcv_div' must be String: '"+value+"' is not." );
                    this.order_rcv_div = (String) value;
                    if (this.order_rcv_div_is_set)
                        this.order_rcv_div_is_modified = true;
                    this.order_rcv_div_is_set = true;
                    return;
                }
                else if ( name.equals("order_cancel_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_cancel_div' must be String: '"+value+"' is not." );
                    this.order_cancel_div = (String) value;
                    if (this.order_cancel_div_is_set)
                        this.order_cancel_div_is_modified = true;
                    this.order_cancel_div_is_set = true;
                    return;
                }
                else if ( name.equals("order_cancel_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'order_cancel_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.order_cancel_date = (java.sql.Timestamp) value;
                    if (this.order_cancel_date_is_set)
                        this.order_cancel_date_is_modified = true;
                    this.order_cancel_date_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("plan") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'plan' must be String: '"+value+"' is not." );
                    this.plan = (String) value;
                    if (this.plan_is_set)
                        this.plan_is_modified = true;
                    this.plan_is_set = true;
                    return;
                }
                else if ( name.equals("payment_method") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_method' must be String: '"+value+"' is not." );
                    this.payment_method = (String) value;
                    if (this.payment_method_is_set)
                        this.payment_method_is_modified = true;
                    this.payment_method_is_set = true;
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
                break;
            case 's':
                if ( name.equals("specify_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'specify_div' must be String: '"+value+"' is not." );
                    this.specify_div = (String) value;
                    if (this.specify_div_is_set)
                        this.specify_div_is_modified = true;
                    this.specify_div_is_set = true;
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
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
