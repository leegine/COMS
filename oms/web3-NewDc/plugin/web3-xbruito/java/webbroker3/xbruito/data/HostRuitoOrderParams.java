head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostRuitoOrderParams.java;


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
 * host_ruito_orderテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostRuitoOrderRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostRuitoOrderParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostRuitoOrderParams}が{@@link HostRuitoOrderRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostRuitoOrderPK 
 * @@see HostRuitoOrderRow 
 */
public class HostRuitoOrderParams extends Params implements HostRuitoOrderRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_ruito_order";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostRuitoOrderRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostRuitoOrderRow.TYPE;
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
   * <em>pay_amount</em>カラムの値 
   */
  public  Long  pay_amount;

  /** 
   * <em>settle_div</em>カラムの値 
   */
  public  String  settle_div;

  /** 
   * <em>pay_div</em>カラムの値 
   */
  public  String  pay_div;

  /** 
   * <em>transfer_comm</em>カラムの値 
   */
  public  Integer  transfer_comm;

  /** 
   * <em>product</em>カラムの値 
   */
  public  String  product;

  /** 
   * <em>transfer_route</em>カラムの値 
   */
  public  String  transfer_route;

  /** 
   * <em>tax_div</em>カラムの値 
   */
  public  String  tax_div;

  /** 
   * <em>conv_div</em>カラムの値 
   */
  public  String  conv_div;

  /** 
   * <em>partner</em>カラムの値 
   */
  public  String  partner;

  /** 
   * <em>pay_date</em>カラムの値 
   */
  public  java.sql.Timestamp  pay_date;

  /** 
   * <em>order_date</em>カラムの値 
   */
  public  java.sql.Timestamp  order_date;

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
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean course_is_set = false;
  private boolean course_is_modified = false;
  private boolean plan_is_set = false;
  private boolean plan_is_modified = false;
  private boolean pay_amount_is_set = false;
  private boolean pay_amount_is_modified = false;
  private boolean settle_div_is_set = false;
  private boolean settle_div_is_modified = false;
  private boolean pay_div_is_set = false;
  private boolean pay_div_is_modified = false;
  private boolean transfer_comm_is_set = false;
  private boolean transfer_comm_is_modified = false;
  private boolean product_is_set = false;
  private boolean product_is_modified = false;
  private boolean transfer_route_is_set = false;
  private boolean transfer_route_is_modified = false;
  private boolean tax_div_is_set = false;
  private boolean tax_div_is_modified = false;
  private boolean conv_div_is_set = false;
  private boolean conv_div_is_modified = false;
  private boolean partner_is_set = false;
  private boolean partner_is_modified = false;
  private boolean pay_date_is_set = false;
  private boolean pay_date_is_modified = false;
  private boolean order_date_is_set = false;
  private boolean order_date_is_modified = false;
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
      + "," + "order_request_number=" +order_request_number
      + "," + "course=" +course
      + "," + "plan=" +plan
      + "," + "pay_amount=" +pay_amount
      + "," + "settle_div=" +settle_div
      + "," + "pay_div=" +pay_div
      + "," + "transfer_comm=" +transfer_comm
      + "," + "product=" +product
      + "," + "transfer_route=" +transfer_route
      + "," + "tax_div=" +tax_div
      + "," + "conv_div=" +conv_div
      + "," + "partner=" +partner
      + "," + "pay_date=" +pay_date
      + "," + "order_date=" +order_date
      + "," + "status=" +status
      + "}";
  }


  /** 
   * 値が未設定のHostRuitoOrderParamsオブジェクトを作成します。 
   */
  public HostRuitoOrderParams() {
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のHostRuitoOrderRowオブジェクトの値を利用してHostRuitoOrderParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostRuitoOrderRowオブジェクト 
   */
  public HostRuitoOrderParams( HostRuitoOrderRow p_row )
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
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    course = p_row.getCourse();
    course_is_set = p_row.getCourseIsSet();
    course_is_modified = p_row.getCourseIsModified();
    plan = p_row.getPlan();
    plan_is_set = p_row.getPlanIsSet();
    plan_is_modified = p_row.getPlanIsModified();
    if ( !p_row.getPayAmountIsNull() )
      pay_amount = new Long( p_row.getPayAmount() );
    pay_amount_is_set = p_row.getPayAmountIsSet();
    pay_amount_is_modified = p_row.getPayAmountIsModified();
    settle_div = p_row.getSettleDiv();
    settle_div_is_set = p_row.getSettleDivIsSet();
    settle_div_is_modified = p_row.getSettleDivIsModified();
    pay_div = p_row.getPayDiv();
    pay_div_is_set = p_row.getPayDivIsSet();
    pay_div_is_modified = p_row.getPayDivIsModified();
    if ( !p_row.getTransferCommIsNull() )
      transfer_comm = new Integer( p_row.getTransferComm() );
    transfer_comm_is_set = p_row.getTransferCommIsSet();
    transfer_comm_is_modified = p_row.getTransferCommIsModified();
    product = p_row.getProduct();
    product_is_set = p_row.getProductIsSet();
    product_is_modified = p_row.getProductIsModified();
    transfer_route = p_row.getTransferRoute();
    transfer_route_is_set = p_row.getTransferRouteIsSet();
    transfer_route_is_modified = p_row.getTransferRouteIsModified();
    tax_div = p_row.getTaxDiv();
    tax_div_is_set = p_row.getTaxDivIsSet();
    tax_div_is_modified = p_row.getTaxDivIsModified();
    conv_div = p_row.getConvDiv();
    conv_div_is_set = p_row.getConvDivIsSet();
    conv_div_is_modified = p_row.getConvDivIsModified();
    partner = p_row.getPartner();
    partner_is_set = p_row.getPartnerIsSet();
    partner_is_modified = p_row.getPartnerIsModified();
    pay_date = p_row.getPayDate();
    pay_date_is_set = p_row.getPayDateIsSet();
    pay_date_is_modified = p_row.getPayDateIsModified();
    order_date = p_row.getOrderDate();
    order_date_is_set = p_row.getOrderDateIsSet();
    order_date_is_modified = p_row.getOrderDateIsModified();
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
      order_request_number_is_set = true;
      order_request_number_is_modified = true;
      course_is_set = true;
      course_is_modified = true;
      plan_is_set = true;
      plan_is_modified = true;
      pay_amount_is_set = true;
      pay_amount_is_modified = true;
      settle_div_is_set = true;
      settle_div_is_modified = true;
      pay_div_is_set = true;
      pay_div_is_modified = true;
      transfer_comm_is_set = true;
      transfer_comm_is_modified = true;
      product_is_set = true;
      product_is_modified = true;
      transfer_route_is_set = true;
      transfer_route_is_modified = true;
      tax_div_is_set = true;
      tax_div_is_modified = true;
      conv_div_is_set = true;
      conv_div_is_modified = true;
      partner_is_set = true;
      partner_is_modified = true;
      pay_date_is_set = true;
      pay_date_is_modified = true;
      order_date_is_set = true;
      order_date_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof HostRuitoOrderRow ) )
       return false;
    return fieldsEqual( (HostRuitoOrderRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostRuitoOrderRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostRuitoOrderRow row )
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
    if ( pay_amount == null ) {
      if ( !row.getPayAmountIsNull() )
        return false;
    } else if ( row.getPayAmountIsNull() || ( pay_amount.longValue() != row.getPayAmount() ) ) {
        return false;
    }
    if ( settle_div == null ) {
      if ( row.getSettleDiv() != null )
        return false;
    } else if ( !settle_div.equals( row.getSettleDiv() ) ) {
        return false;
    }
    if ( pay_div == null ) {
      if ( row.getPayDiv() != null )
        return false;
    } else if ( !pay_div.equals( row.getPayDiv() ) ) {
        return false;
    }
    if ( transfer_comm == null ) {
      if ( !row.getTransferCommIsNull() )
        return false;
    } else if ( row.getTransferCommIsNull() || ( transfer_comm.intValue() != row.getTransferComm() ) ) {
        return false;
    }
    if ( product == null ) {
      if ( row.getProduct() != null )
        return false;
    } else if ( !product.equals( row.getProduct() ) ) {
        return false;
    }
    if ( transfer_route == null ) {
      if ( row.getTransferRoute() != null )
        return false;
    } else if ( !transfer_route.equals( row.getTransferRoute() ) ) {
        return false;
    }
    if ( tax_div == null ) {
      if ( row.getTaxDiv() != null )
        return false;
    } else if ( !tax_div.equals( row.getTaxDiv() ) ) {
        return false;
    }
    if ( conv_div == null ) {
      if ( row.getConvDiv() != null )
        return false;
    } else if ( !conv_div.equals( row.getConvDiv() ) ) {
        return false;
    }
    if ( partner == null ) {
      if ( row.getPartner() != null )
        return false;
    } else if ( !partner.equals( row.getPartner() ) ) {
        return false;
    }
    if ( pay_date == null ) {
      if ( row.getPayDate() != null )
        return false;
    } else if ( !pay_date.equals( row.getPayDate() ) ) {
        return false;
    }
    if ( order_date == null ) {
      if ( row.getOrderDate() != null )
        return false;
    } else if ( !order_date.equals( row.getOrderDate() ) ) {
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
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (course!=null? course.hashCode(): 0) 
        + (plan!=null? plan.hashCode(): 0) 
        + (pay_amount!=null? pay_amount.hashCode(): 0) 
        + (settle_div!=null? settle_div.hashCode(): 0) 
        + (pay_div!=null? pay_div.hashCode(): 0) 
        + (transfer_comm!=null? transfer_comm.hashCode(): 0) 
        + (product!=null? product.hashCode(): 0) 
        + (transfer_route!=null? transfer_route.hashCode(): 0) 
        + (tax_div!=null? tax_div.hashCode(): 0) 
        + (conv_div!=null? conv_div.hashCode(): 0) 
        + (partner!=null? partner.hashCode(): 0) 
        + (pay_date!=null? pay_date.hashCode(): 0) 
        + (order_date!=null? order_date.hashCode(): 0) 
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
		map.put("order_request_number",order_request_number);
		if ( course != null )
			map.put("course",course);
		if ( plan != null )
			map.put("plan",plan);
		if ( pay_amount != null )
			map.put("pay_amount",pay_amount);
		if ( settle_div != null )
			map.put("settle_div",settle_div);
		if ( pay_div != null )
			map.put("pay_div",pay_div);
		if ( transfer_comm != null )
			map.put("transfer_comm",transfer_comm);
		if ( product != null )
			map.put("product",product);
		if ( transfer_route != null )
			map.put("transfer_route",transfer_route);
		if ( tax_div != null )
			map.put("tax_div",tax_div);
		if ( conv_div != null )
			map.put("conv_div",conv_div);
		if ( partner != null )
			map.put("partner",partner);
		if ( pay_date != null )
			map.put("pay_date",pay_date);
		if ( order_date != null )
			map.put("order_date",order_date);
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
		if ( order_request_number_is_modified )
			map.put("order_request_number",order_request_number);
		if ( course_is_modified )
			map.put("course",course);
		if ( plan_is_modified )
			map.put("plan",plan);
		if ( pay_amount_is_modified )
			map.put("pay_amount",pay_amount);
		if ( settle_div_is_modified )
			map.put("settle_div",settle_div);
		if ( pay_div_is_modified )
			map.put("pay_div",pay_div);
		if ( transfer_comm_is_modified )
			map.put("transfer_comm",transfer_comm);
		if ( product_is_modified )
			map.put("product",product);
		if ( transfer_route_is_modified )
			map.put("transfer_route",transfer_route);
		if ( tax_div_is_modified )
			map.put("tax_div",tax_div);
		if ( conv_div_is_modified )
			map.put("conv_div",conv_div);
		if ( partner_is_modified )
			map.put("partner",partner);
		if ( pay_date_is_modified )
			map.put("pay_date",pay_date);
		if ( order_date_is_modified )
			map.put("order_date",order_date);
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
			if ( order_request_number_is_set )
				map.put("order_request_number",order_request_number);
			map.put("course",course);
			map.put("plan",plan);
			map.put("pay_amount",pay_amount);
			map.put("settle_div",settle_div);
			map.put("pay_div",pay_div);
			map.put("transfer_comm",transfer_comm);
			map.put("product",product);
			map.put("transfer_route",transfer_route);
			map.put("tax_div",tax_div);
			map.put("conv_div",conv_div);
			map.put("partner",partner);
			map.put("pay_date",pay_date);
			map.put("order_date",order_date);
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
   * <em>pay_amount</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getPayAmount()
  {
    return ( pay_amount==null? ((long)0): pay_amount.longValue() );
  }


  /** 
   * <em>pay_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPayAmountIsNull()
  {
    return pay_amount == null;
  }


  /** 
   * <em>pay_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPayAmountIsSet() {
    return pay_amount_is_set;
  }


  /** 
   * <em>pay_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPayAmountIsModified() {
    return pay_amount_is_modified;
  }


  /** 
   * <em>settle_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSettleDiv()
  {
    return settle_div;
  }


  /** 
   * <em>settle_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSettleDivIsSet() {
    return settle_div_is_set;
  }


  /** 
   * <em>settle_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSettleDivIsModified() {
    return settle_div_is_modified;
  }


  /** 
   * <em>pay_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPayDiv()
  {
    return pay_div;
  }


  /** 
   * <em>pay_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPayDivIsSet() {
    return pay_div_is_set;
  }


  /** 
   * <em>pay_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPayDivIsModified() {
    return pay_div_is_modified;
  }


  /** 
   * <em>transfer_comm</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getTransferComm()
  {
    return ( transfer_comm==null? ((int)0): transfer_comm.intValue() );
  }


  /** 
   * <em>transfer_comm</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTransferCommIsNull()
  {
    return transfer_comm == null;
  }


  /** 
   * <em>transfer_comm</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferCommIsSet() {
    return transfer_comm_is_set;
  }


  /** 
   * <em>transfer_comm</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferCommIsModified() {
    return transfer_comm_is_modified;
  }


  /** 
   * <em>product</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProduct()
  {
    return product;
  }


  /** 
   * <em>product</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIsSet() {
    return product_is_set;
  }


  /** 
   * <em>product</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIsModified() {
    return product_is_modified;
  }


  /** 
   * <em>transfer_route</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTransferRoute()
  {
    return transfer_route;
  }


  /** 
   * <em>transfer_route</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferRouteIsSet() {
    return transfer_route_is_set;
  }


  /** 
   * <em>transfer_route</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferRouteIsModified() {
    return transfer_route_is_modified;
  }


  /** 
   * <em>tax_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTaxDiv()
  {
    return tax_div;
  }


  /** 
   * <em>tax_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxDivIsSet() {
    return tax_div_is_set;
  }


  /** 
   * <em>tax_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxDivIsModified() {
    return tax_div_is_modified;
  }


  /** 
   * <em>conv_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getConvDiv()
  {
    return conv_div;
  }


  /** 
   * <em>conv_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConvDivIsSet() {
    return conv_div_is_set;
  }


  /** 
   * <em>conv_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConvDivIsModified() {
    return conv_div_is_modified;
  }


  /** 
   * <em>partner</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPartner()
  {
    return partner;
  }


  /** 
   * <em>partner</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartnerIsSet() {
    return partner_is_set;
  }


  /** 
   * <em>partner</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPartnerIsModified() {
    return partner_is_modified;
  }


  /** 
   * <em>pay_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getPayDate()
  {
    return pay_date;
  }


  /** 
   * <em>pay_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPayDateIsSet() {
    return pay_date_is_set;
  }


  /** 
   * <em>pay_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPayDateIsModified() {
    return pay_date_is_modified;
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
    throw new UnsupportedOperationException("Table host_ruito_order has no primary key.");
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
   * <em>pay_amount</em>カラムの値を設定します。 
   *
   * @@param p_payAmount <em>pay_amount</em>カラムの値をあらわす11桁以下のlong値 
   */
  public final void setPayAmount( long p_payAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pay_amount = new Long(p_payAmount);
    pay_amount_is_set = true;
    pay_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>pay_amount</em>カラムに値を設定します。 
   */
  public final void setPayAmount( Long p_payAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    pay_amount = p_payAmount;
    pay_amount_is_set = true;
    pay_amount_is_modified = true;
  }


  /** 
   * <em>settle_div</em>カラムの値を設定します。 
   *
   * @@param p_settleDiv <em>settle_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSettleDiv( String p_settleDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    settle_div = p_settleDiv;
    settle_div_is_set = true;
    settle_div_is_modified = true;
  }


  /** 
   * <em>pay_div</em>カラムの値を設定します。 
   *
   * @@param p_payDiv <em>pay_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPayDiv( String p_payDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pay_div = p_payDiv;
    pay_div_is_set = true;
    pay_div_is_modified = true;
  }


  /** 
   * <em>transfer_comm</em>カラムの値を設定します。 
   *
   * @@param p_transferComm <em>transfer_comm</em>カラムの値をあらわす5桁以下のint値 
   */
  public final void setTransferComm( int p_transferComm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_comm = new Integer(p_transferComm);
    transfer_comm_is_set = true;
    transfer_comm_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>transfer_comm</em>カラムに値を設定します。 
   */
  public final void setTransferComm( Integer p_transferComm )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_comm = p_transferComm;
    transfer_comm_is_set = true;
    transfer_comm_is_modified = true;
  }


  /** 
   * <em>product</em>カラムの値を設定します。 
   *
   * @@param p_product <em>product</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setProduct( String p_product )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product = p_product;
    product_is_set = true;
    product_is_modified = true;
  }


  /** 
   * <em>transfer_route</em>カラムの値を設定します。 
   *
   * @@param p_transferRoute <em>transfer_route</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTransferRoute( String p_transferRoute )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_route = p_transferRoute;
    transfer_route_is_set = true;
    transfer_route_is_modified = true;
  }


  /** 
   * <em>tax_div</em>カラムの値を設定します。 
   *
   * @@param p_taxDiv <em>tax_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTaxDiv( String p_taxDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tax_div = p_taxDiv;
    tax_div_is_set = true;
    tax_div_is_modified = true;
  }


  /** 
   * <em>conv_div</em>カラムの値を設定します。 
   *
   * @@param p_convDiv <em>conv_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setConvDiv( String p_convDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    conv_div = p_convDiv;
    conv_div_is_set = true;
    conv_div_is_modified = true;
  }


  /** 
   * <em>partner</em>カラムの値を設定します。 
   *
   * @@param p_partner <em>partner</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setPartner( String p_partner )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    partner = p_partner;
    partner_is_set = true;
    partner_is_modified = true;
  }


  /** 
   * <em>pay_date</em>カラムの値を設定します。 
   *
   * @@param p_payDate <em>pay_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setPayDate( java.sql.Timestamp p_payDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pay_date = p_payDate;
    pay_date_is_set = true;
    pay_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>pay_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setPayDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    pay_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    pay_date_is_set = true;
    pay_date_is_modified = true;
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
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("course") ) {
                    return this.course;
                }
                else if ( name.equals("conv_div") ) {
                    return this.conv_div;
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
                else if ( name.equals("order_date") ) {
                    return this.order_date;
                }
                break;
            case 'p':
                if ( name.equals("plan") ) {
                    return this.plan;
                }
                else if ( name.equals("pay_amount") ) {
                    return this.pay_amount;
                }
                else if ( name.equals("pay_div") ) {
                    return this.pay_div;
                }
                else if ( name.equals("product") ) {
                    return this.product;
                }
                else if ( name.equals("partner") ) {
                    return this.partner;
                }
                else if ( name.equals("pay_date") ) {
                    return this.pay_date;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                break;
            case 's':
                if ( name.equals("settle_div") ) {
                    return this.settle_div;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                else if ( name.equals("transfer_comm") ) {
                    return this.transfer_comm;
                }
                else if ( name.equals("transfer_route") ) {
                    return this.transfer_route;
                }
                else if ( name.equals("tax_div") ) {
                    return this.tax_div;
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
                else if ( name.equals("conv_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'conv_div' must be String: '"+value+"' is not." );
                    this.conv_div = (String) value;
                    if (this.conv_div_is_set)
                        this.conv_div_is_modified = true;
                    this.conv_div_is_set = true;
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
                else if ( name.equals("pay_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'pay_amount' must be Long: '"+value+"' is not." );
                    this.pay_amount = (Long) value;
                    if (this.pay_amount_is_set)
                        this.pay_amount_is_modified = true;
                    this.pay_amount_is_set = true;
                    return;
                }
                else if ( name.equals("pay_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pay_div' must be String: '"+value+"' is not." );
                    this.pay_div = (String) value;
                    if (this.pay_div_is_set)
                        this.pay_div_is_modified = true;
                    this.pay_div_is_set = true;
                    return;
                }
                else if ( name.equals("product") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product' must be String: '"+value+"' is not." );
                    this.product = (String) value;
                    if (this.product_is_set)
                        this.product_is_modified = true;
                    this.product_is_set = true;
                    return;
                }
                else if ( name.equals("partner") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'partner' must be String: '"+value+"' is not." );
                    this.partner = (String) value;
                    if (this.partner_is_set)
                        this.partner_is_modified = true;
                    this.partner_is_set = true;
                    return;
                }
                else if ( name.equals("pay_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'pay_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.pay_date = (java.sql.Timestamp) value;
                    if (this.pay_date_is_set)
                        this.pay_date_is_modified = true;
                    this.pay_date_is_set = true;
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
                if ( name.equals("settle_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'settle_div' must be String: '"+value+"' is not." );
                    this.settle_div = (String) value;
                    if (this.settle_div_is_set)
                        this.settle_div_is_modified = true;
                    this.settle_div_is_set = true;
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
                else if ( name.equals("transfer_comm") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'transfer_comm' must be Integer: '"+value+"' is not." );
                    this.transfer_comm = (Integer) value;
                    if (this.transfer_comm_is_set)
                        this.transfer_comm_is_modified = true;
                    this.transfer_comm_is_set = true;
                    return;
                }
                else if ( name.equals("transfer_route") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transfer_route' must be String: '"+value+"' is not." );
                    this.transfer_route = (String) value;
                    if (this.transfer_route_is_set)
                        this.transfer_route_is_modified = true;
                    this.transfer_route_is_set = true;
                    return;
                }
                else if ( name.equals("tax_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tax_div' must be String: '"+value+"' is not." );
                    this.tax_div = (String) value;
                    if (this.tax_div_is_set)
                        this.tax_div_is_modified = true;
                    this.tax_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
