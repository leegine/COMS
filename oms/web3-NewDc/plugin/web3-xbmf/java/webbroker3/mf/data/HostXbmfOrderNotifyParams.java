head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostXbmfOrderNotifyParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * host_xbmf_order_notifyテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostXbmfOrderNotifyRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostXbmfOrderNotifyParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostXbmfOrderNotifyParams}が{@@link HostXbmfOrderNotifyRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostXbmfOrderNotifyPK 
 * @@see HostXbmfOrderNotifyRow 
 */
public class HostXbmfOrderNotifyParams extends Params implements HostXbmfOrderNotifyRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_xbmf_order_notify";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostXbmfOrderNotifyRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostXbmfOrderNotifyRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

  /** 
   * <em>request_code</em>カラムの値 
   */
  public  String  request_code;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>trader_code</em>カラムの値 
   */
  public  String  trader_code;

  /** 
   * <em>order_date_div</em>カラムの値 
   */
  public  String  order_date_div;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>trade_type</em>カラムの値 
   */
  public  String  trade_type;

  /** 
   * <em>specify_div</em>カラムの値 
   */
  public  String  specify_div;

  /** 
   * <em>quantity</em>カラムの値 
   */
  public  Double  quantity;

  /** 
   * <em>pr_div</em>カラムの値 
   */
  public  String  pr_div;

  /** 
   * <em>settlement_div</em>カラムの値 
   */
  public  String  settlement_div;

  /** 
   * <em>commission_div</em>カラムの値 
   */
  public  String  commission_div;

  /** 
   * <em>deposit_check_div</em>カラムの値 
   */
  public  String  deposit_check_div;

  /** 
   * <em>estimated_price</em>カラムの値 
   */
  public  Double  estimated_price;

  /** 
   * <em>constant_value</em>カラムの値 
   */
  public  Double  constant_value;

  /** 
   * <em>estimated_unit</em>カラムの値 
   */
  public  Double  estimated_unit;

  /** 
   * <em>create_datetime</em>カラムの値 
   */
  public  java.sql.Timestamp  create_datetime;

  /** 
   * <em>biz_datetime</em>カラムの値 
   */
  public  java.sql.Timestamp  biz_datetime;

  /** 
   * <em>order_div</em>カラムの値 
   */
  public  String  order_div;

  /** 
   * <em>order_cancel_div</em>カラムの値 
   */
  public  String  order_cancel_div;

  /** 
   * <em>order_cancel_date</em>カラムの値 
   */
  public  java.sql.Timestamp  order_cancel_date;

  /** 
   * <em>delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>sonar_fund_type</em>カラムの値 
   */
  public  String  sonar_fund_type;

  /** 
   * <em>delivery_method</em>カラムの値 
   */
  public  String  delivery_method;

  /** 
   * <em>check_flag</em>カラムの値 
   */
  public  String  check_flag;

  /** 
   * <em>error_message</em>カラムの値 
   */
  public  String  error_message;

  /** 
   * <em>swt_div</em>カラムの値 
   */
  public  String  swt_div;

  /** 
   * <em>swt_product_code</em>カラムの値 
   */
  public  String  swt_product_code;

  /** 
   * <em>tax_type</em>カラムの値 
   */
  public  String  tax_type;

  /** 
   * <em>swt_tax_type</em>カラムの値 
   */
  public  String  swt_tax_type;

  /** 
   * <em>claim_div</em>カラムの値 
   */
  public  String  claim_div;

  /** 
   * <em>input_div</em>カラムの値 
   */
  public  String  input_div;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  /** 
   * <em>payment_date</em>カラムの値 
   */
  public  java.sql.Timestamp  payment_date;

  /** 
   * <em>cpu_no</em>カラムの値 
   */
  public  String  cpu_no;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

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
  private boolean order_date_div_is_set = false;
  private boolean order_date_div_is_modified = false;
  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean trade_type_is_set = false;
  private boolean trade_type_is_modified = false;
  private boolean specify_div_is_set = false;
  private boolean specify_div_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean pr_div_is_set = false;
  private boolean pr_div_is_modified = false;
  private boolean settlement_div_is_set = false;
  private boolean settlement_div_is_modified = false;
  private boolean commission_div_is_set = false;
  private boolean commission_div_is_modified = false;
  private boolean deposit_check_div_is_set = false;
  private boolean deposit_check_div_is_modified = false;
  private boolean estimated_price_is_set = false;
  private boolean estimated_price_is_modified = false;
  private boolean constant_value_is_set = false;
  private boolean constant_value_is_modified = false;
  private boolean estimated_unit_is_set = false;
  private boolean estimated_unit_is_modified = false;
  private boolean create_datetime_is_set = false;
  private boolean create_datetime_is_modified = false;
  private boolean biz_datetime_is_set = false;
  private boolean biz_datetime_is_modified = false;
  private boolean order_div_is_set = false;
  private boolean order_div_is_modified = false;
  private boolean order_cancel_div_is_set = false;
  private boolean order_cancel_div_is_modified = false;
  private boolean order_cancel_date_is_set = false;
  private boolean order_cancel_date_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean sonar_fund_type_is_set = false;
  private boolean sonar_fund_type_is_modified = false;
  private boolean delivery_method_is_set = false;
  private boolean delivery_method_is_modified = false;
  private boolean check_flag_is_set = false;
  private boolean check_flag_is_modified = false;
  private boolean error_message_is_set = false;
  private boolean error_message_is_modified = false;
  private boolean swt_div_is_set = false;
  private boolean swt_div_is_modified = false;
  private boolean swt_product_code_is_set = false;
  private boolean swt_product_code_is_modified = false;
  private boolean tax_type_is_set = false;
  private boolean tax_type_is_modified = false;
  private boolean swt_tax_type_is_set = false;
  private boolean swt_tax_type_is_modified = false;
  private boolean claim_div_is_set = false;
  private boolean claim_div_is_modified = false;
  private boolean input_div_is_set = false;
  private boolean input_div_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean payment_date_is_set = false;
  private boolean payment_date_is_modified = false;
  private boolean cpu_no_is_set = false;
  private boolean cpu_no_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "order_request_number=" + order_request_number
      + "," + "request_code=" +request_code
      + "," + "account_code=" +account_code
      + "," + "trader_code=" +trader_code
      + "," + "order_date_div=" +order_date_div
      + "," + "product_code=" +product_code
      + "," + "trade_type=" +trade_type
      + "," + "specify_div=" +specify_div
      + "," + "quantity=" +quantity
      + "," + "pr_div=" +pr_div
      + "," + "settlement_div=" +settlement_div
      + "," + "commission_div=" +commission_div
      + "," + "deposit_check_div=" +deposit_check_div
      + "," + "estimated_price=" +estimated_price
      + "," + "constant_value=" +constant_value
      + "," + "estimated_unit=" +estimated_unit
      + "," + "create_datetime=" +create_datetime
      + "," + "biz_datetime=" +biz_datetime
      + "," + "order_div=" +order_div
      + "," + "order_cancel_div=" +order_cancel_div
      + "," + "order_cancel_date=" +order_cancel_date
      + "," + "delivery_date=" +delivery_date
      + "," + "sonar_fund_type=" +sonar_fund_type
      + "," + "delivery_method=" +delivery_method
      + "," + "check_flag=" +check_flag
      + "," + "error_message=" +error_message
      + "," + "swt_div=" +swt_div
      + "," + "swt_product_code=" +swt_product_code
      + "," + "tax_type=" +tax_type
      + "," + "swt_tax_type=" +swt_tax_type
      + "," + "claim_div=" +claim_div
      + "," + "input_div=" +input_div
      + "," + "status=" +status
      + "," + "payment_date=" +payment_date
      + "," + "cpu_no=" +cpu_no
      + "," + "created_timestamp=" +created_timestamp
      + "}";
  }


  /** 
   * 値が未設定のHostXbmfOrderNotifyParamsオブジェクトを作成します。 
   */
  public HostXbmfOrderNotifyParams() {
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    order_request_number_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のHostXbmfOrderNotifyRowオブジェクトの値を利用してHostXbmfOrderNotifyParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostXbmfOrderNotifyRowオブジェクト 
   */
  public HostXbmfOrderNotifyParams( HostXbmfOrderNotifyRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
    request_code = p_row.getRequestCode();
    request_code_is_set = p_row.getRequestCodeIsSet();
    request_code_is_modified = p_row.getRequestCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    trader_code = p_row.getTraderCode();
    trader_code_is_set = p_row.getTraderCodeIsSet();
    trader_code_is_modified = p_row.getTraderCodeIsModified();
    order_date_div = p_row.getOrderDateDiv();
    order_date_div_is_set = p_row.getOrderDateDivIsSet();
    order_date_div_is_modified = p_row.getOrderDateDivIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    trade_type = p_row.getTradeType();
    trade_type_is_set = p_row.getTradeTypeIsSet();
    trade_type_is_modified = p_row.getTradeTypeIsModified();
    specify_div = p_row.getSpecifyDiv();
    specify_div_is_set = p_row.getSpecifyDivIsSet();
    specify_div_is_modified = p_row.getSpecifyDivIsModified();
    if ( !p_row.getQuantityIsNull() )
      quantity = new Double( p_row.getQuantity() );
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    pr_div = p_row.getPrDiv();
    pr_div_is_set = p_row.getPrDivIsSet();
    pr_div_is_modified = p_row.getPrDivIsModified();
    settlement_div = p_row.getSettlementDiv();
    settlement_div_is_set = p_row.getSettlementDivIsSet();
    settlement_div_is_modified = p_row.getSettlementDivIsModified();
    commission_div = p_row.getCommissionDiv();
    commission_div_is_set = p_row.getCommissionDivIsSet();
    commission_div_is_modified = p_row.getCommissionDivIsModified();
    deposit_check_div = p_row.getDepositCheckDiv();
    deposit_check_div_is_set = p_row.getDepositCheckDivIsSet();
    deposit_check_div_is_modified = p_row.getDepositCheckDivIsModified();
    if ( !p_row.getEstimatedPriceIsNull() )
      estimated_price = new Double( p_row.getEstimatedPrice() );
    estimated_price_is_set = p_row.getEstimatedPriceIsSet();
    estimated_price_is_modified = p_row.getEstimatedPriceIsModified();
    if ( !p_row.getConstantValueIsNull() )
      constant_value = new Double( p_row.getConstantValue() );
    constant_value_is_set = p_row.getConstantValueIsSet();
    constant_value_is_modified = p_row.getConstantValueIsModified();
    if ( !p_row.getEstimatedUnitIsNull() )
      estimated_unit = new Double( p_row.getEstimatedUnit() );
    estimated_unit_is_set = p_row.getEstimatedUnitIsSet();
    estimated_unit_is_modified = p_row.getEstimatedUnitIsModified();
    create_datetime = p_row.getCreateDatetime();
    create_datetime_is_set = p_row.getCreateDatetimeIsSet();
    create_datetime_is_modified = p_row.getCreateDatetimeIsModified();
    biz_datetime = p_row.getBizDatetime();
    biz_datetime_is_set = p_row.getBizDatetimeIsSet();
    biz_datetime_is_modified = p_row.getBizDatetimeIsModified();
    order_div = p_row.getOrderDiv();
    order_div_is_set = p_row.getOrderDivIsSet();
    order_div_is_modified = p_row.getOrderDivIsModified();
    order_cancel_div = p_row.getOrderCancelDiv();
    order_cancel_div_is_set = p_row.getOrderCancelDivIsSet();
    order_cancel_div_is_modified = p_row.getOrderCancelDivIsModified();
    order_cancel_date = p_row.getOrderCancelDate();
    order_cancel_date_is_set = p_row.getOrderCancelDateIsSet();
    order_cancel_date_is_modified = p_row.getOrderCancelDateIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    sonar_fund_type = p_row.getSonarFundType();
    sonar_fund_type_is_set = p_row.getSonarFundTypeIsSet();
    sonar_fund_type_is_modified = p_row.getSonarFundTypeIsModified();
    delivery_method = p_row.getDeliveryMethod();
    delivery_method_is_set = p_row.getDeliveryMethodIsSet();
    delivery_method_is_modified = p_row.getDeliveryMethodIsModified();
    check_flag = p_row.getCheckFlag();
    check_flag_is_set = p_row.getCheckFlagIsSet();
    check_flag_is_modified = p_row.getCheckFlagIsModified();
    error_message = p_row.getErrorMessage();
    error_message_is_set = p_row.getErrorMessageIsSet();
    error_message_is_modified = p_row.getErrorMessageIsModified();
    swt_div = p_row.getSwtDiv();
    swt_div_is_set = p_row.getSwtDivIsSet();
    swt_div_is_modified = p_row.getSwtDivIsModified();
    swt_product_code = p_row.getSwtProductCode();
    swt_product_code_is_set = p_row.getSwtProductCodeIsSet();
    swt_product_code_is_modified = p_row.getSwtProductCodeIsModified();
    tax_type = p_row.getTaxType();
    tax_type_is_set = p_row.getTaxTypeIsSet();
    tax_type_is_modified = p_row.getTaxTypeIsModified();
    swt_tax_type = p_row.getSwtTaxType();
    swt_tax_type_is_set = p_row.getSwtTaxTypeIsSet();
    swt_tax_type_is_modified = p_row.getSwtTaxTypeIsModified();
    claim_div = p_row.getClaimDiv();
    claim_div_is_set = p_row.getClaimDivIsSet();
    claim_div_is_modified = p_row.getClaimDivIsModified();
    input_div = p_row.getInputDiv();
    input_div_is_set = p_row.getInputDivIsSet();
    input_div_is_modified = p_row.getInputDivIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    payment_date = p_row.getPaymentDate();
    payment_date_is_set = p_row.getPaymentDateIsSet();
    payment_date_is_modified = p_row.getPaymentDateIsModified();
    cpu_no = p_row.getCpuNo();
    cpu_no_is_set = p_row.getCpuNoIsSet();
    cpu_no_is_modified = p_row.getCpuNoIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      request_code_is_set = true;
      request_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      trader_code_is_set = true;
      trader_code_is_modified = true;
      order_date_div_is_set = true;
      order_date_div_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      trade_type_is_set = true;
      trade_type_is_modified = true;
      specify_div_is_set = true;
      specify_div_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      pr_div_is_set = true;
      pr_div_is_modified = true;
      settlement_div_is_set = true;
      settlement_div_is_modified = true;
      commission_div_is_set = true;
      commission_div_is_modified = true;
      deposit_check_div_is_set = true;
      deposit_check_div_is_modified = true;
      estimated_price_is_set = true;
      estimated_price_is_modified = true;
      constant_value_is_set = true;
      constant_value_is_modified = true;
      estimated_unit_is_set = true;
      estimated_unit_is_modified = true;
      create_datetime_is_set = true;
      create_datetime_is_modified = true;
      biz_datetime_is_set = true;
      biz_datetime_is_modified = true;
      order_div_is_set = true;
      order_div_is_modified = true;
      order_cancel_div_is_set = true;
      order_cancel_div_is_modified = true;
      order_cancel_date_is_set = true;
      order_cancel_date_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      sonar_fund_type_is_set = true;
      sonar_fund_type_is_modified = true;
      delivery_method_is_set = true;
      delivery_method_is_modified = true;
      check_flag_is_set = true;
      check_flag_is_modified = true;
      error_message_is_set = true;
      error_message_is_modified = true;
      swt_div_is_set = true;
      swt_div_is_modified = true;
      swt_product_code_is_set = true;
      swt_product_code_is_modified = true;
      tax_type_is_set = true;
      tax_type_is_modified = true;
      swt_tax_type_is_set = true;
      swt_tax_type_is_modified = true;
      claim_div_is_set = true;
      claim_div_is_modified = true;
      input_div_is_set = true;
      input_div_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      payment_date_is_set = true;
      payment_date_is_modified = true;
      cpu_no_is_set = true;
      cpu_no_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof HostXbmfOrderNotifyRow ) )
       return false;
    return fieldsEqual( (HostXbmfOrderNotifyRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostXbmfOrderNotifyRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostXbmfOrderNotifyRow row )
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
    if ( order_date_div == null ) {
      if ( row.getOrderDateDiv() != null )
        return false;
    } else if ( !order_date_div.equals( row.getOrderDateDiv() ) ) {
        return false;
    }
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( trade_type == null ) {
      if ( row.getTradeType() != null )
        return false;
    } else if ( !trade_type.equals( row.getTradeType() ) ) {
        return false;
    }
    if ( specify_div == null ) {
      if ( row.getSpecifyDiv() != null )
        return false;
    } else if ( !specify_div.equals( row.getSpecifyDiv() ) ) {
        return false;
    }
    if ( quantity == null ) {
      if ( !row.getQuantityIsNull() )
        return false;
    } else if ( row.getQuantityIsNull() || ( quantity.doubleValue() != row.getQuantity() ) ) {
        return false;
    }
    if ( pr_div == null ) {
      if ( row.getPrDiv() != null )
        return false;
    } else if ( !pr_div.equals( row.getPrDiv() ) ) {
        return false;
    }
    if ( settlement_div == null ) {
      if ( row.getSettlementDiv() != null )
        return false;
    } else if ( !settlement_div.equals( row.getSettlementDiv() ) ) {
        return false;
    }
    if ( commission_div == null ) {
      if ( row.getCommissionDiv() != null )
        return false;
    } else if ( !commission_div.equals( row.getCommissionDiv() ) ) {
        return false;
    }
    if ( deposit_check_div == null ) {
      if ( row.getDepositCheckDiv() != null )
        return false;
    } else if ( !deposit_check_div.equals( row.getDepositCheckDiv() ) ) {
        return false;
    }
    if ( estimated_price == null ) {
      if ( !row.getEstimatedPriceIsNull() )
        return false;
    } else if ( row.getEstimatedPriceIsNull() || ( estimated_price.doubleValue() != row.getEstimatedPrice() ) ) {
        return false;
    }
    if ( constant_value == null ) {
      if ( !row.getConstantValueIsNull() )
        return false;
    } else if ( row.getConstantValueIsNull() || ( constant_value.doubleValue() != row.getConstantValue() ) ) {
        return false;
    }
    if ( estimated_unit == null ) {
      if ( !row.getEstimatedUnitIsNull() )
        return false;
    } else if ( row.getEstimatedUnitIsNull() || ( estimated_unit.doubleValue() != row.getEstimatedUnit() ) ) {
        return false;
    }
    if ( create_datetime == null ) {
      if ( row.getCreateDatetime() != null )
        return false;
    } else if ( !create_datetime.equals( row.getCreateDatetime() ) ) {
        return false;
    }
    if ( biz_datetime == null ) {
      if ( row.getBizDatetime() != null )
        return false;
    } else if ( !biz_datetime.equals( row.getBizDatetime() ) ) {
        return false;
    }
    if ( order_div == null ) {
      if ( row.getOrderDiv() != null )
        return false;
    } else if ( !order_div.equals( row.getOrderDiv() ) ) {
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
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( sonar_fund_type == null ) {
      if ( row.getSonarFundType() != null )
        return false;
    } else if ( !sonar_fund_type.equals( row.getSonarFundType() ) ) {
        return false;
    }
    if ( delivery_method == null ) {
      if ( row.getDeliveryMethod() != null )
        return false;
    } else if ( !delivery_method.equals( row.getDeliveryMethod() ) ) {
        return false;
    }
    if ( check_flag == null ) {
      if ( row.getCheckFlag() != null )
        return false;
    } else if ( !check_flag.equals( row.getCheckFlag() ) ) {
        return false;
    }
    if ( error_message == null ) {
      if ( row.getErrorMessage() != null )
        return false;
    } else if ( !error_message.equals( row.getErrorMessage() ) ) {
        return false;
    }
    if ( swt_div == null ) {
      if ( row.getSwtDiv() != null )
        return false;
    } else if ( !swt_div.equals( row.getSwtDiv() ) ) {
        return false;
    }
    if ( swt_product_code == null ) {
      if ( row.getSwtProductCode() != null )
        return false;
    } else if ( !swt_product_code.equals( row.getSwtProductCode() ) ) {
        return false;
    }
    if ( tax_type == null ) {
      if ( row.getTaxType() != null )
        return false;
    } else if ( !tax_type.equals( row.getTaxType() ) ) {
        return false;
    }
    if ( swt_tax_type == null ) {
      if ( row.getSwtTaxType() != null )
        return false;
    } else if ( !swt_tax_type.equals( row.getSwtTaxType() ) ) {
        return false;
    }
    if ( claim_div == null ) {
      if ( row.getClaimDiv() != null )
        return false;
    } else if ( !claim_div.equals( row.getClaimDiv() ) ) {
        return false;
    }
    if ( input_div == null ) {
      if ( row.getInputDiv() != null )
        return false;
    } else if ( !input_div.equals( row.getInputDiv() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( payment_date == null ) {
      if ( row.getPaymentDate() != null )
        return false;
    } else if ( !payment_date.equals( row.getPaymentDate() ) ) {
        return false;
    }
    if ( cpu_no == null ) {
      if ( row.getCpuNo() != null )
        return false;
    } else if ( !cpu_no.equals( row.getCpuNo() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
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
        + (order_date_div!=null? order_date_div.hashCode(): 0) 
        + (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (trade_type!=null? trade_type.hashCode(): 0) 
        + (specify_div!=null? specify_div.hashCode(): 0) 
        + (quantity!=null? quantity.hashCode(): 0) 
        + (pr_div!=null? pr_div.hashCode(): 0) 
        + (settlement_div!=null? settlement_div.hashCode(): 0) 
        + (commission_div!=null? commission_div.hashCode(): 0) 
        + (deposit_check_div!=null? deposit_check_div.hashCode(): 0) 
        + (estimated_price!=null? estimated_price.hashCode(): 0) 
        + (constant_value!=null? constant_value.hashCode(): 0) 
        + (estimated_unit!=null? estimated_unit.hashCode(): 0) 
        + (create_datetime!=null? create_datetime.hashCode(): 0) 
        + (biz_datetime!=null? biz_datetime.hashCode(): 0) 
        + (order_div!=null? order_div.hashCode(): 0) 
        + (order_cancel_div!=null? order_cancel_div.hashCode(): 0) 
        + (order_cancel_date!=null? order_cancel_date.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (sonar_fund_type!=null? sonar_fund_type.hashCode(): 0) 
        + (delivery_method!=null? delivery_method.hashCode(): 0) 
        + (check_flag!=null? check_flag.hashCode(): 0) 
        + (error_message!=null? error_message.hashCode(): 0) 
        + (swt_div!=null? swt_div.hashCode(): 0) 
        + (swt_product_code!=null? swt_product_code.hashCode(): 0) 
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (swt_tax_type!=null? swt_tax_type.hashCode(): 0) 
        + (claim_div!=null? claim_div.hashCode(): 0) 
        + (input_div!=null? input_div.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (payment_date!=null? payment_date.hashCode(): 0) 
        + (cpu_no!=null? cpu_no.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !request_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'request_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !order_date_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_date_div' must be set before inserting.");
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
		map.put("order_date_div",order_date_div);
		map.put("order_request_number",order_request_number);
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( trade_type != null )
			map.put("trade_type",trade_type);
		if ( specify_div != null )
			map.put("specify_div",specify_div);
		if ( quantity != null )
			map.put("quantity",quantity);
		if ( pr_div != null )
			map.put("pr_div",pr_div);
		if ( settlement_div != null )
			map.put("settlement_div",settlement_div);
		if ( commission_div != null )
			map.put("commission_div",commission_div);
		if ( deposit_check_div != null )
			map.put("deposit_check_div",deposit_check_div);
		if ( estimated_price != null )
			map.put("estimated_price",estimated_price);
		if ( constant_value != null )
			map.put("constant_value",constant_value);
		if ( estimated_unit != null )
			map.put("estimated_unit",estimated_unit);
		if ( create_datetime != null )
			map.put("create_datetime",create_datetime);
		if ( biz_datetime != null )
			map.put("biz_datetime",biz_datetime);
		if ( order_div != null )
			map.put("order_div",order_div);
		if ( order_cancel_div != null )
			map.put("order_cancel_div",order_cancel_div);
		if ( order_cancel_date != null )
			map.put("order_cancel_date",order_cancel_date);
		if ( delivery_date != null )
			map.put("delivery_date",delivery_date);
		if ( sonar_fund_type != null )
			map.put("sonar_fund_type",sonar_fund_type);
		if ( delivery_method != null )
			map.put("delivery_method",delivery_method);
		if ( check_flag_is_set )
			map.put("check_flag",check_flag);
		if ( error_message != null )
			map.put("error_message",error_message);
		if ( swt_div != null )
			map.put("swt_div",swt_div);
		if ( swt_product_code != null )
			map.put("swt_product_code",swt_product_code);
		if ( tax_type != null )
			map.put("tax_type",tax_type);
		if ( swt_tax_type != null )
			map.put("swt_tax_type",swt_tax_type);
		if ( claim_div != null )
			map.put("claim_div",claim_div);
		if ( input_div != null )
			map.put("input_div",input_div);
		if ( status != null )
			map.put("status",status);
		if ( payment_date != null )
			map.put("payment_date",payment_date);
		if ( cpu_no != null )
			map.put("cpu_no",cpu_no);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( request_code_is_modified )
			map.put("request_code",request_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( order_date_div_is_modified )
			map.put("order_date_div",order_date_div);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( trade_type_is_modified )
			map.put("trade_type",trade_type);
		if ( specify_div_is_modified )
			map.put("specify_div",specify_div);
		if ( quantity_is_modified )
			map.put("quantity",quantity);
		if ( pr_div_is_modified )
			map.put("pr_div",pr_div);
		if ( settlement_div_is_modified )
			map.put("settlement_div",settlement_div);
		if ( commission_div_is_modified )
			map.put("commission_div",commission_div);
		if ( deposit_check_div_is_modified )
			map.put("deposit_check_div",deposit_check_div);
		if ( estimated_price_is_modified )
			map.put("estimated_price",estimated_price);
		if ( constant_value_is_modified )
			map.put("constant_value",constant_value);
		if ( estimated_unit_is_modified )
			map.put("estimated_unit",estimated_unit);
		if ( create_datetime_is_modified )
			map.put("create_datetime",create_datetime);
		if ( biz_datetime_is_modified )
			map.put("biz_datetime",biz_datetime);
		if ( order_div_is_modified )
			map.put("order_div",order_div);
		if ( order_cancel_div_is_modified )
			map.put("order_cancel_div",order_cancel_div);
		if ( order_cancel_date_is_modified )
			map.put("order_cancel_date",order_cancel_date);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( sonar_fund_type_is_modified )
			map.put("sonar_fund_type",sonar_fund_type);
		if ( delivery_method_is_modified )
			map.put("delivery_method",delivery_method);
		if ( check_flag_is_modified )
			map.put("check_flag",check_flag);
		if ( error_message_is_modified )
			map.put("error_message",error_message);
		if ( swt_div_is_modified )
			map.put("swt_div",swt_div);
		if ( swt_product_code_is_modified )
			map.put("swt_product_code",swt_product_code);
		if ( tax_type_is_modified )
			map.put("tax_type",tax_type);
		if ( swt_tax_type_is_modified )
			map.put("swt_tax_type",swt_tax_type);
		if ( claim_div_is_modified )
			map.put("claim_div",claim_div);
		if ( input_div_is_modified )
			map.put("input_div",input_div);
		if ( status_is_modified )
			map.put("status",status);
		if ( payment_date_is_modified )
			map.put("payment_date",payment_date);
		if ( cpu_no_is_modified )
			map.put("cpu_no",cpu_no);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if (map.size() == 0) {
			if ( request_code_is_set )
				map.put("request_code",request_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("trader_code",trader_code);
			if ( order_date_div_is_set )
				map.put("order_date_div",order_date_div);
			map.put("product_code",product_code);
			map.put("trade_type",trade_type);
			map.put("specify_div",specify_div);
			map.put("quantity",quantity);
			map.put("pr_div",pr_div);
			map.put("settlement_div",settlement_div);
			map.put("commission_div",commission_div);
			map.put("deposit_check_div",deposit_check_div);
			map.put("estimated_price",estimated_price);
			map.put("constant_value",constant_value);
			map.put("estimated_unit",estimated_unit);
			map.put("create_datetime",create_datetime);
			map.put("biz_datetime",biz_datetime);
			map.put("order_div",order_div);
			map.put("order_cancel_div",order_cancel_div);
			map.put("order_cancel_date",order_cancel_date);
			map.put("delivery_date",delivery_date);
			map.put("sonar_fund_type",sonar_fund_type);
			map.put("delivery_method",delivery_method);
			if ( check_flag_is_set )
				map.put("check_flag",check_flag);
			map.put("error_message",error_message);
			map.put("swt_div",swt_div);
			map.put("swt_product_code",swt_product_code);
			map.put("tax_type",tax_type);
			map.put("swt_tax_type",swt_tax_type);
			map.put("claim_div",claim_div);
			map.put("input_div",input_div);
			map.put("status",status);
			map.put("payment_date",payment_date);
			map.put("cpu_no",cpu_no);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
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
   * <em>order_date_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderDateDiv()
  {
    return order_date_div;
  }


  /** 
   * <em>order_date_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderDateDivIsSet() {
    return order_date_div_is_set;
  }


  /** 
   * <em>order_date_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderDateDivIsModified() {
    return order_date_div_is_modified;
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
   * <em>trade_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradeType()
  {
    return trade_type;
  }


  /** 
   * <em>trade_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeTypeIsSet() {
    return trade_type_is_set;
  }


  /** 
   * <em>trade_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeTypeIsModified() {
    return trade_type_is_modified;
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
   * <em>quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getQuantity()
  {
    return ( quantity==null? ((double)0): quantity.doubleValue() );
  }


  /** 
   * <em>quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getQuantityIsNull()
  {
    return quantity == null;
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
   * <em>pr_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPrDiv()
  {
    return pr_div;
  }


  /** 
   * <em>pr_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrDivIsSet() {
    return pr_div_is_set;
  }


  /** 
   * <em>pr_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrDivIsModified() {
    return pr_div_is_modified;
  }


  /** 
   * <em>settlement_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSettlementDiv()
  {
    return settlement_div;
  }


  /** 
   * <em>settlement_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSettlementDivIsSet() {
    return settlement_div_is_set;
  }


  /** 
   * <em>settlement_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSettlementDivIsModified() {
    return settlement_div_is_modified;
  }


  /** 
   * <em>commission_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommissionDiv()
  {
    return commission_div;
  }


  /** 
   * <em>commission_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionDivIsSet() {
    return commission_div_is_set;
  }


  /** 
   * <em>commission_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionDivIsModified() {
    return commission_div_is_modified;
  }


  /** 
   * <em>deposit_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDepositCheckDiv()
  {
    return deposit_check_div;
  }


  /** 
   * <em>deposit_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositCheckDivIsSet() {
    return deposit_check_div_is_set;
  }


  /** 
   * <em>deposit_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositCheckDivIsModified() {
    return deposit_check_div_is_modified;
  }


  /** 
   * <em>estimated_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getEstimatedPrice()
  {
    return ( estimated_price==null? ((double)0): estimated_price.doubleValue() );
  }


  /** 
   * <em>estimated_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getEstimatedPriceIsNull()
  {
    return estimated_price == null;
  }


  /** 
   * <em>estimated_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEstimatedPriceIsSet() {
    return estimated_price_is_set;
  }


  /** 
   * <em>estimated_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEstimatedPriceIsModified() {
    return estimated_price_is_modified;
  }


  /** 
   * <em>constant_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getConstantValue()
  {
    return ( constant_value==null? ((double)0): constant_value.doubleValue() );
  }


  /** 
   * <em>constant_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getConstantValueIsNull()
  {
    return constant_value == null;
  }


  /** 
   * <em>constant_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConstantValueIsSet() {
    return constant_value_is_set;
  }


  /** 
   * <em>constant_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConstantValueIsModified() {
    return constant_value_is_modified;
  }


  /** 
   * <em>estimated_unit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getEstimatedUnit()
  {
    return ( estimated_unit==null? ((double)0): estimated_unit.doubleValue() );
  }


  /** 
   * <em>estimated_unit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getEstimatedUnitIsNull()
  {
    return estimated_unit == null;
  }


  /** 
   * <em>estimated_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEstimatedUnitIsSet() {
    return estimated_unit_is_set;
  }


  /** 
   * <em>estimated_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEstimatedUnitIsModified() {
    return estimated_unit_is_modified;
  }


  /** 
   * <em>create_datetime</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreateDatetime()
  {
    return create_datetime;
  }


  /** 
   * <em>create_datetime</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreateDatetimeIsSet() {
    return create_datetime_is_set;
  }


  /** 
   * <em>create_datetime</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreateDatetimeIsModified() {
    return create_datetime_is_modified;
  }


  /** 
   * <em>biz_datetime</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getBizDatetime()
  {
    return biz_datetime;
  }


  /** 
   * <em>biz_datetime</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBizDatetimeIsSet() {
    return biz_datetime_is_set;
  }


  /** 
   * <em>biz_datetime</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBizDatetimeIsModified() {
    return biz_datetime_is_modified;
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
   * <em>sonar_fund_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarFundType()
  {
    return sonar_fund_type;
  }


  /** 
   * <em>sonar_fund_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarFundTypeIsSet() {
    return sonar_fund_type_is_set;
  }


  /** 
   * <em>sonar_fund_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarFundTypeIsModified() {
    return sonar_fund_type_is_modified;
  }


  /** 
   * <em>delivery_method</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDeliveryMethod()
  {
    return delivery_method;
  }


  /** 
   * <em>delivery_method</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryMethodIsSet() {
    return delivery_method_is_set;
  }


  /** 
   * <em>delivery_method</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryMethodIsModified() {
    return delivery_method_is_modified;
  }


  /** 
   * <em>check_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCheckFlag()
  {
    return check_flag;
  }


  /** 
   * <em>check_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCheckFlagIsSet() {
    return check_flag_is_set;
  }


  /** 
   * <em>check_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCheckFlagIsModified() {
    return check_flag_is_modified;
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
   * <em>swt_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSwtDiv()
  {
    return swt_div;
  }


  /** 
   * <em>swt_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtDivIsSet() {
    return swt_div_is_set;
  }


  /** 
   * <em>swt_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtDivIsModified() {
    return swt_div_is_modified;
  }


  /** 
   * <em>swt_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSwtProductCode()
  {
    return swt_product_code;
  }


  /** 
   * <em>swt_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtProductCodeIsSet() {
    return swt_product_code_is_set;
  }


  /** 
   * <em>swt_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtProductCodeIsModified() {
    return swt_product_code_is_modified;
  }


  /** 
   * <em>tax_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTaxType()
  {
    return tax_type;
  }


  /** 
   * <em>tax_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxTypeIsSet() {
    return tax_type_is_set;
  }


  /** 
   * <em>tax_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxTypeIsModified() {
    return tax_type_is_modified;
  }


  /** 
   * <em>swt_tax_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSwtTaxType()
  {
    return swt_tax_type;
  }


  /** 
   * <em>swt_tax_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtTaxTypeIsSet() {
    return swt_tax_type_is_set;
  }


  /** 
   * <em>swt_tax_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSwtTaxTypeIsModified() {
    return swt_tax_type_is_modified;
  }


  /** 
   * <em>claim_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getClaimDiv()
  {
    return claim_div;
  }


  /** 
   * <em>claim_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClaimDivIsSet() {
    return claim_div_is_set;
  }


  /** 
   * <em>claim_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClaimDivIsModified() {
    return claim_div_is_modified;
  }


  /** 
   * <em>input_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInputDiv()
  {
    return input_div;
  }


  /** 
   * <em>input_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInputDivIsSet() {
    return input_div_is_set;
  }


  /** 
   * <em>input_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInputDivIsModified() {
    return input_div_is_modified;
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
   * <em>payment_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getPaymentDate()
  {
    return payment_date;
  }


  /** 
   * <em>payment_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentDateIsSet() {
    return payment_date_is_set;
  }


  /** 
   * <em>payment_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentDateIsModified() {
    return payment_date_is_modified;
  }


  /** 
   * <em>cpu_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCpuNo()
  {
    return cpu_no;
  }


  /** 
   * <em>cpu_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCpuNoIsSet() {
    return cpu_no_is_set;
  }


  /** 
   * <em>cpu_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCpuNoIsModified() {
    return cpu_no_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new HostXbmfOrderNotifyPK(institution_code, branch_code, order_request_number);
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
   * <em>order_date_div</em>カラムの値を設定します。 
   *
   * @@param p_orderDateDiv <em>order_date_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderDateDiv( String p_orderDateDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_date_div = p_orderDateDiv;
    order_date_div_is_set = true;
    order_date_div_is_modified = true;
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
   * <em>product_code</em>カラムの値を設定します。 
   *
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>trade_type</em>カラムの値を設定します。 
   *
   * @@param p_tradeType <em>trade_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTradeType( String p_tradeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_type = p_tradeType;
    trade_type_is_set = true;
    trade_type_is_modified = true;
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
   * <em>quantity</em>カラムの値を設定します。 
   *
   * @@param p_quantity <em>quantity</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setQuantity( double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = new Double(p_quantity);
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>quantity</em>カラムに値を設定します。 
   */
  public final void setQuantity( Double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * <em>pr_div</em>カラムの値を設定します。 
   *
   * @@param p_prDiv <em>pr_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPrDiv( String p_prDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pr_div = p_prDiv;
    pr_div_is_set = true;
    pr_div_is_modified = true;
  }


  /** 
   * <em>settlement_div</em>カラムの値を設定します。 
   *
   * @@param p_settlementDiv <em>settlement_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSettlementDiv( String p_settlementDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    settlement_div = p_settlementDiv;
    settlement_div_is_set = true;
    settlement_div_is_modified = true;
  }


  /** 
   * <em>commission_div</em>カラムの値を設定します。 
   *
   * @@param p_commissionDiv <em>commission_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCommissionDiv( String p_commissionDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_div = p_commissionDiv;
    commission_div_is_set = true;
    commission_div_is_modified = true;
  }


  /** 
   * <em>deposit_check_div</em>カラムの値を設定します。 
   *
   * @@param p_depositCheckDiv <em>deposit_check_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDepositCheckDiv( String p_depositCheckDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deposit_check_div = p_depositCheckDiv;
    deposit_check_div_is_set = true;
    deposit_check_div_is_modified = true;
  }


  /** 
   * <em>estimated_price</em>カラムの値を設定します。 
   *
   * @@param p_estimatedPrice <em>estimated_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setEstimatedPrice( double p_estimatedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    estimated_price = new Double(p_estimatedPrice);
    estimated_price_is_set = true;
    estimated_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>estimated_price</em>カラムに値を設定します。 
   */
  public final void setEstimatedPrice( Double p_estimatedPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    estimated_price = p_estimatedPrice;
    estimated_price_is_set = true;
    estimated_price_is_modified = true;
  }


  /** 
   * <em>constant_value</em>カラムの値を設定します。 
   *
   * @@param p_constantValue <em>constant_value</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setConstantValue( double p_constantValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    constant_value = new Double(p_constantValue);
    constant_value_is_set = true;
    constant_value_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>constant_value</em>カラムに値を設定します。 
   */
  public final void setConstantValue( Double p_constantValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    constant_value = p_constantValue;
    constant_value_is_set = true;
    constant_value_is_modified = true;
  }


  /** 
   * <em>estimated_unit</em>カラムの値を設定します。 
   *
   * @@param p_estimatedUnit <em>estimated_unit</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setEstimatedUnit( double p_estimatedUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    estimated_unit = new Double(p_estimatedUnit);
    estimated_unit_is_set = true;
    estimated_unit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>estimated_unit</em>カラムに値を設定します。 
   */
  public final void setEstimatedUnit( Double p_estimatedUnit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    estimated_unit = p_estimatedUnit;
    estimated_unit_is_set = true;
    estimated_unit_is_modified = true;
  }


  /** 
   * <em>create_datetime</em>カラムの値を設定します。 
   *
   * @@param p_createDatetime <em>create_datetime</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreateDatetime( java.sql.Timestamp p_createDatetime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    create_datetime = p_createDatetime;
    create_datetime_is_set = true;
    create_datetime_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>create_datetime</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreateDatetime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    create_datetime = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    create_datetime_is_set = true;
    create_datetime_is_modified = true;
  }


  /** 
   * <em>biz_datetime</em>カラムの値を設定します。 
   *
   * @@param p_bizDatetime <em>biz_datetime</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setBizDatetime( java.sql.Timestamp p_bizDatetime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    biz_datetime = p_bizDatetime;
    biz_datetime_is_set = true;
    biz_datetime_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>biz_datetime</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setBizDatetime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    biz_datetime = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    biz_datetime_is_set = true;
    biz_datetime_is_modified = true;
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
   * <em>sonar_fund_type</em>カラムの値を設定します。 
   *
   * @@param p_sonarFundType <em>sonar_fund_type</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setSonarFundType( String p_sonarFundType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_fund_type = p_sonarFundType;
    sonar_fund_type_is_set = true;
    sonar_fund_type_is_modified = true;
  }


  /** 
   * <em>delivery_method</em>カラムの値を設定します。 
   *
   * @@param p_deliveryMethod <em>delivery_method</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDeliveryMethod( String p_deliveryMethod )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_method = p_deliveryMethod;
    delivery_method_is_set = true;
    delivery_method_is_modified = true;
  }


  /** 
   * <em>check_flag</em>カラムの値を設定します。 
   *
   * @@param p_checkFlag <em>check_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCheckFlag( String p_checkFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    check_flag = p_checkFlag;
    check_flag_is_set = true;
    check_flag_is_modified = true;
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
   * <em>swt_div</em>カラムの値を設定します。 
   *
   * @@param p_swtDiv <em>swt_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSwtDiv( String p_swtDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_div = p_swtDiv;
    swt_div_is_set = true;
    swt_div_is_modified = true;
  }


  /** 
   * <em>swt_product_code</em>カラムの値を設定します。 
   *
   * @@param p_swtProductCode <em>swt_product_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setSwtProductCode( String p_swtProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_product_code = p_swtProductCode;
    swt_product_code_is_set = true;
    swt_product_code_is_modified = true;
  }


  /** 
   * <em>tax_type</em>カラムの値を設定します。 
   *
   * @@param p_taxType <em>tax_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTaxType( String p_taxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tax_type = p_taxType;
    tax_type_is_set = true;
    tax_type_is_modified = true;
  }


  /** 
   * <em>swt_tax_type</em>カラムの値を設定します。 
   *
   * @@param p_swtTaxType <em>swt_tax_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSwtTaxType( String p_swtTaxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    swt_tax_type = p_swtTaxType;
    swt_tax_type_is_set = true;
    swt_tax_type_is_modified = true;
  }


  /** 
   * <em>claim_div</em>カラムの値を設定します。 
   *
   * @@param p_claimDiv <em>claim_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setClaimDiv( String p_claimDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    claim_div = p_claimDiv;
    claim_div_is_set = true;
    claim_div_is_modified = true;
  }


  /** 
   * <em>input_div</em>カラムの値を設定します。 
   *
   * @@param p_inputDiv <em>input_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setInputDiv( String p_inputDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    input_div = p_inputDiv;
    input_div_is_set = true;
    input_div_is_modified = true;
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
   * <em>payment_date</em>カラムの値を設定します。 
   *
   * @@param p_paymentDate <em>payment_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setPaymentDate( java.sql.Timestamp p_paymentDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_date = p_paymentDate;
    payment_date_is_set = true;
    payment_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>payment_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setPaymentDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    payment_date_is_set = true;
    payment_date_is_modified = true;
  }


  /** 
   * <em>cpu_no</em>カラムの値を設定します。 
   *
   * @@param p_cpuNo <em>cpu_no</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setCpuNo( String p_cpuNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cpu_no = p_cpuNo;
    cpu_no_is_set = true;
    cpu_no_is_modified = true;
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
                else if ( name.equals("biz_datetime") ) {
                    return this.biz_datetime;
                }
                break;
            case 'c':
                if ( name.equals("commission_div") ) {
                    return this.commission_div;
                }
                else if ( name.equals("constant_value") ) {
                    return this.constant_value;
                }
                else if ( name.equals("create_datetime") ) {
                    return this.create_datetime;
                }
                else if ( name.equals("check_flag") ) {
                    return this.check_flag;
                }
                else if ( name.equals("claim_div") ) {
                    return this.claim_div;
                }
                else if ( name.equals("cpu_no") ) {
                    return this.cpu_no;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("deposit_check_div") ) {
                    return this.deposit_check_div;
                }
                else if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                else if ( name.equals("delivery_method") ) {
                    return this.delivery_method;
                }
                break;
            case 'e':
                if ( name.equals("estimated_price") ) {
                    return this.estimated_price;
                }
                else if ( name.equals("estimated_unit") ) {
                    return this.estimated_unit;
                }
                else if ( name.equals("error_message") ) {
                    return this.error_message;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("input_div") ) {
                    return this.input_div;
                }
                break;
            case 'o':
                if ( name.equals("order_date_div") ) {
                    return this.order_date_div;
                }
                else if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("order_div") ) {
                    return this.order_div;
                }
                else if ( name.equals("order_cancel_div") ) {
                    return this.order_cancel_div;
                }
                else if ( name.equals("order_cancel_date") ) {
                    return this.order_cancel_date;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("pr_div") ) {
                    return this.pr_div;
                }
                else if ( name.equals("payment_date") ) {
                    return this.payment_date;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return this.quantity;
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
                else if ( name.equals("settlement_div") ) {
                    return this.settlement_div;
                }
                else if ( name.equals("sonar_fund_type") ) {
                    return this.sonar_fund_type;
                }
                else if ( name.equals("swt_div") ) {
                    return this.swt_div;
                }
                else if ( name.equals("swt_product_code") ) {
                    return this.swt_product_code;
                }
                else if ( name.equals("swt_tax_type") ) {
                    return this.swt_tax_type;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                else if ( name.equals("trade_type") ) {
                    return this.trade_type;
                }
                else if ( name.equals("tax_type") ) {
                    return this.tax_type;
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
                else if ( name.equals("biz_datetime") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'biz_datetime' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.biz_datetime = (java.sql.Timestamp) value;
                    if (this.biz_datetime_is_set)
                        this.biz_datetime_is_modified = true;
                    this.biz_datetime_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("commission_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commission_div' must be String: '"+value+"' is not." );
                    this.commission_div = (String) value;
                    if (this.commission_div_is_set)
                        this.commission_div_is_modified = true;
                    this.commission_div_is_set = true;
                    return;
                }
                else if ( name.equals("constant_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'constant_value' must be Double: '"+value+"' is not." );
                    this.constant_value = (Double) value;
                    if (this.constant_value_is_set)
                        this.constant_value_is_modified = true;
                    this.constant_value_is_set = true;
                    return;
                }
                else if ( name.equals("create_datetime") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'create_datetime' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.create_datetime = (java.sql.Timestamp) value;
                    if (this.create_datetime_is_set)
                        this.create_datetime_is_modified = true;
                    this.create_datetime_is_set = true;
                    return;
                }
                else if ( name.equals("check_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'check_flag' must be String: '"+value+"' is not." );
                    this.check_flag = (String) value;
                    if (this.check_flag_is_set)
                        this.check_flag_is_modified = true;
                    this.check_flag_is_set = true;
                    return;
                }
                else if ( name.equals("claim_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'claim_div' must be String: '"+value+"' is not." );
                    this.claim_div = (String) value;
                    if (this.claim_div_is_set)
                        this.claim_div_is_modified = true;
                    this.claim_div_is_set = true;
                    return;
                }
                else if ( name.equals("cpu_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cpu_no' must be String: '"+value+"' is not." );
                    this.cpu_no = (String) value;
                    if (this.cpu_no_is_set)
                        this.cpu_no_is_modified = true;
                    this.cpu_no_is_set = true;
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
                if ( name.equals("deposit_check_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deposit_check_div' must be String: '"+value+"' is not." );
                    this.deposit_check_div = (String) value;
                    if (this.deposit_check_div_is_set)
                        this.deposit_check_div_is_modified = true;
                    this.deposit_check_div_is_set = true;
                    return;
                }
                else if ( name.equals("delivery_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.delivery_date = (java.sql.Timestamp) value;
                    if (this.delivery_date_is_set)
                        this.delivery_date_is_modified = true;
                    this.delivery_date_is_set = true;
                    return;
                }
                else if ( name.equals("delivery_method") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'delivery_method' must be String: '"+value+"' is not." );
                    this.delivery_method = (String) value;
                    if (this.delivery_method_is_set)
                        this.delivery_method_is_modified = true;
                    this.delivery_method_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("estimated_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'estimated_price' must be Double: '"+value+"' is not." );
                    this.estimated_price = (Double) value;
                    if (this.estimated_price_is_set)
                        this.estimated_price_is_modified = true;
                    this.estimated_price_is_set = true;
                    return;
                }
                else if ( name.equals("estimated_unit") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'estimated_unit' must be Double: '"+value+"' is not." );
                    this.estimated_unit = (Double) value;
                    if (this.estimated_unit_is_set)
                        this.estimated_unit_is_modified = true;
                    this.estimated_unit_is_set = true;
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
                else if ( name.equals("input_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'input_div' must be String: '"+value+"' is not." );
                    this.input_div = (String) value;
                    if (this.input_div_is_set)
                        this.input_div_is_modified = true;
                    this.input_div_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_date_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_date_div' must be String: '"+value+"' is not." );
                    this.order_date_div = (String) value;
                    if (this.order_date_div_is_set)
                        this.order_date_div_is_modified = true;
                    this.order_date_div_is_set = true;
                    return;
                }
                else if ( name.equals("order_request_number") ) {
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
                else if ( name.equals("pr_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pr_div' must be String: '"+value+"' is not." );
                    this.pr_div = (String) value;
                    if (this.pr_div_is_set)
                        this.pr_div_is_modified = true;
                    this.pr_div_is_set = true;
                    return;
                }
                else if ( name.equals("payment_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'payment_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.payment_date = (java.sql.Timestamp) value;
                    if (this.payment_date_is_set)
                        this.payment_date_is_modified = true;
                    this.payment_date_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Double: '"+value+"' is not." );
                    this.quantity = (Double) value;
                    if (this.quantity_is_set)
                        this.quantity_is_modified = true;
                    this.quantity_is_set = true;
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
                else if ( name.equals("settlement_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'settlement_div' must be String: '"+value+"' is not." );
                    this.settlement_div = (String) value;
                    if (this.settlement_div_is_set)
                        this.settlement_div_is_modified = true;
                    this.settlement_div_is_set = true;
                    return;
                }
                else if ( name.equals("sonar_fund_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_fund_type' must be String: '"+value+"' is not." );
                    this.sonar_fund_type = (String) value;
                    if (this.sonar_fund_type_is_set)
                        this.sonar_fund_type_is_modified = true;
                    this.sonar_fund_type_is_set = true;
                    return;
                }
                else if ( name.equals("swt_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'swt_div' must be String: '"+value+"' is not." );
                    this.swt_div = (String) value;
                    if (this.swt_div_is_set)
                        this.swt_div_is_modified = true;
                    this.swt_div_is_set = true;
                    return;
                }
                else if ( name.equals("swt_product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'swt_product_code' must be String: '"+value+"' is not." );
                    this.swt_product_code = (String) value;
                    if (this.swt_product_code_is_set)
                        this.swt_product_code_is_modified = true;
                    this.swt_product_code_is_set = true;
                    return;
                }
                else if ( name.equals("swt_tax_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'swt_tax_type' must be String: '"+value+"' is not." );
                    this.swt_tax_type = (String) value;
                    if (this.swt_tax_type_is_set)
                        this.swt_tax_type_is_modified = true;
                    this.swt_tax_type_is_set = true;
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
                else if ( name.equals("trade_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trade_type' must be String: '"+value+"' is not." );
                    this.trade_type = (String) value;
                    if (this.trade_type_is_set)
                        this.trade_type_is_modified = true;
                    this.trade_type_is_set = true;
                    return;
                }
                else if ( name.equals("tax_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tax_type' must be String: '"+value+"' is not." );
                    this.tax_type = (String) value;
                    if (this.tax_type_is_set)
                        this.tax_type_is_modified = true;
                    this.tax_type_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
