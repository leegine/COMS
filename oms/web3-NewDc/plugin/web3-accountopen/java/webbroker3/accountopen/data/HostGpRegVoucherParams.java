head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.14.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	HostGpRegVoucherParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * host_gp_reg_voucherテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostGpRegVoucherRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostGpRegVoucherParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostGpRegVoucherParams}が{@@link HostGpRegVoucherRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostGpRegVoucherPK 
 * @@see HostGpRegVoucherRow 
 */
public class HostGpRegVoucherParams extends Params implements HostGpRegVoucherRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_gp_reg_voucher";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostGpRegVoucherRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostGpRegVoucherRow.TYPE;
  }


  /** 
   * <em>order_request_number</em>カラムの値 
   */
  public  String  order_request_number;

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
   * <em>acc_open_request_number</em>カラムの値 
   */
  public  String  acc_open_request_number;

  /** 
   * <em>serial_no</em>カラムの値 
   */
  public  String  serial_no;

  /** 
   * <em>course</em>カラムの値 
   */
  public  String  course;

  /** 
   * <em>plan</em>カラムの値 
   */
  public  String  plan;

  /** 
   * <em>regist_div</em>カラムの値 
   */
  public  String  regist_div;

  /** 
   * <em>target_figure</em>カラムの値 
   */
  public  String  target_figure;

  /** 
   * <em>target_year</em>カラムの値 
   */
  public  String  target_year;

  /** 
   * <em>target_month</em>カラムの値 
   */
  public  String  target_month;

  /** 
   * <em>installment_figure</em>カラムの値 
   */
  public  String  installment_figure;

  /** 
   * <em>deposit_cycle</em>カラムの値 
   */
  public  String  deposit_cycle;

  /** 
   * <em>payment_root</em>カラムの値 
   */
  public  String  payment_root;

  /** 
   * <em>reinvest_div</em>カラムの値 
   */
  public  String  reinvest_div;

  /** 
   * <em>tax_div</em>カラムの値 
   */
  public  String  tax_div;

  /** 
   * <em>taxfree_limit</em>カラムの値 
   */
  public  String  taxfree_limit;

  /** 
   * <em>special_taxfree_limit</em>カラムの値 
   */
  public  String  special_taxfree_limit;

  /** 
   * <em>subscr_summary</em>カラムの値 
   */
  public  String  subscr_summary;

  /** 
   * <em>gp_product_code</em>カラムの値 
   */
  public  String  gp_product_code;

  /** 
   * <em>mortgage_customer</em>カラムの値 
   */
  public  String  mortgage_customer;

  /** 
   * <em>mix_customer</em>カラムの値 
   */
  public  String  mix_customer;

  /** 
   * <em>contract</em>カラムの値 
   */
  public  String  contract;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  /** 
   * <em>send_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  send_timestamp;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean order_request_number_is_set = false;
  private boolean order_request_number_is_modified = false;
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
  private boolean acc_open_request_number_is_set = false;
  private boolean acc_open_request_number_is_modified = false;
  private boolean serial_no_is_set = false;
  private boolean serial_no_is_modified = false;
  private boolean course_is_set = false;
  private boolean course_is_modified = false;
  private boolean plan_is_set = false;
  private boolean plan_is_modified = false;
  private boolean regist_div_is_set = false;
  private boolean regist_div_is_modified = false;
  private boolean target_figure_is_set = false;
  private boolean target_figure_is_modified = false;
  private boolean target_year_is_set = false;
  private boolean target_year_is_modified = false;
  private boolean target_month_is_set = false;
  private boolean target_month_is_modified = false;
  private boolean installment_figure_is_set = false;
  private boolean installment_figure_is_modified = false;
  private boolean deposit_cycle_is_set = false;
  private boolean deposit_cycle_is_modified = false;
  private boolean payment_root_is_set = false;
  private boolean payment_root_is_modified = false;
  private boolean reinvest_div_is_set = false;
  private boolean reinvest_div_is_modified = false;
  private boolean tax_div_is_set = false;
  private boolean tax_div_is_modified = false;
  private boolean taxfree_limit_is_set = false;
  private boolean taxfree_limit_is_modified = false;
  private boolean special_taxfree_limit_is_set = false;
  private boolean special_taxfree_limit_is_modified = false;
  private boolean subscr_summary_is_set = false;
  private boolean subscr_summary_is_modified = false;
  private boolean gp_product_code_is_set = false;
  private boolean gp_product_code_is_modified = false;
  private boolean mortgage_customer_is_set = false;
  private boolean mortgage_customer_is_modified = false;
  private boolean mix_customer_is_set = false;
  private boolean mix_customer_is_modified = false;
  private boolean contract_is_set = false;
  private boolean contract_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean send_timestamp_is_set = false;
  private boolean send_timestamp_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "order_request_number=" + order_request_number
      + "," + "request_code=" + request_code
      + "," + "institution_code=" + institution_code
      + "," + "branch_code=" + branch_code
      + "," + "account_code=" + account_code
      + "," + "trader_code=" +trader_code
      + "," + "acc_open_request_number=" +acc_open_request_number
      + "," + "serial_no=" +serial_no
      + "," + "course=" +course
      + "," + "plan=" +plan
      + "," + "regist_div=" +regist_div
      + "," + "target_figure=" +target_figure
      + "," + "target_year=" +target_year
      + "," + "target_month=" +target_month
      + "," + "installment_figure=" +installment_figure
      + "," + "deposit_cycle=" +deposit_cycle
      + "," + "payment_root=" +payment_root
      + "," + "reinvest_div=" +reinvest_div
      + "," + "tax_div=" +tax_div
      + "," + "taxfree_limit=" +taxfree_limit
      + "," + "special_taxfree_limit=" +special_taxfree_limit
      + "," + "subscr_summary=" +subscr_summary
      + "," + "gp_product_code=" +gp_product_code
      + "," + "mortgage_customer=" +mortgage_customer
      + "," + "mix_customer=" +mix_customer
      + "," + "contract=" +contract
      + "," + "status=" +status
      + "," + "send_timestamp=" +send_timestamp
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のHostGpRegVoucherParamsオブジェクトを作成します。 
   */
  public HostGpRegVoucherParams() {
    order_request_number_is_modified = true;
    request_code_is_modified = true;
    institution_code_is_modified = true;
    branch_code_is_modified = true;
    account_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のHostGpRegVoucherRowオブジェクトの値を利用してHostGpRegVoucherParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostGpRegVoucherRowオブジェクト 
   */
  public HostGpRegVoucherParams( HostGpRegVoucherRow p_row )
  {
    order_request_number = p_row.getOrderRequestNumber();
    order_request_number_is_set = p_row.getOrderRequestNumberIsSet();
    order_request_number_is_modified = p_row.getOrderRequestNumberIsModified();
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
    acc_open_request_number = p_row.getAccOpenRequestNumber();
    acc_open_request_number_is_set = p_row.getAccOpenRequestNumberIsSet();
    acc_open_request_number_is_modified = p_row.getAccOpenRequestNumberIsModified();
    serial_no = p_row.getSerialNo();
    serial_no_is_set = p_row.getSerialNoIsSet();
    serial_no_is_modified = p_row.getSerialNoIsModified();
    course = p_row.getCourse();
    course_is_set = p_row.getCourseIsSet();
    course_is_modified = p_row.getCourseIsModified();
    plan = p_row.getPlan();
    plan_is_set = p_row.getPlanIsSet();
    plan_is_modified = p_row.getPlanIsModified();
    regist_div = p_row.getRegistDiv();
    regist_div_is_set = p_row.getRegistDivIsSet();
    regist_div_is_modified = p_row.getRegistDivIsModified();
    target_figure = p_row.getTargetFigure();
    target_figure_is_set = p_row.getTargetFigureIsSet();
    target_figure_is_modified = p_row.getTargetFigureIsModified();
    target_year = p_row.getTargetYear();
    target_year_is_set = p_row.getTargetYearIsSet();
    target_year_is_modified = p_row.getTargetYearIsModified();
    target_month = p_row.getTargetMonth();
    target_month_is_set = p_row.getTargetMonthIsSet();
    target_month_is_modified = p_row.getTargetMonthIsModified();
    installment_figure = p_row.getInstallmentFigure();
    installment_figure_is_set = p_row.getInstallmentFigureIsSet();
    installment_figure_is_modified = p_row.getInstallmentFigureIsModified();
    deposit_cycle = p_row.getDepositCycle();
    deposit_cycle_is_set = p_row.getDepositCycleIsSet();
    deposit_cycle_is_modified = p_row.getDepositCycleIsModified();
    payment_root = p_row.getPaymentRoot();
    payment_root_is_set = p_row.getPaymentRootIsSet();
    payment_root_is_modified = p_row.getPaymentRootIsModified();
    reinvest_div = p_row.getReinvestDiv();
    reinvest_div_is_set = p_row.getReinvestDivIsSet();
    reinvest_div_is_modified = p_row.getReinvestDivIsModified();
    tax_div = p_row.getTaxDiv();
    tax_div_is_set = p_row.getTaxDivIsSet();
    tax_div_is_modified = p_row.getTaxDivIsModified();
    taxfree_limit = p_row.getTaxfreeLimit();
    taxfree_limit_is_set = p_row.getTaxfreeLimitIsSet();
    taxfree_limit_is_modified = p_row.getTaxfreeLimitIsModified();
    special_taxfree_limit = p_row.getSpecialTaxfreeLimit();
    special_taxfree_limit_is_set = p_row.getSpecialTaxfreeLimitIsSet();
    special_taxfree_limit_is_modified = p_row.getSpecialTaxfreeLimitIsModified();
    subscr_summary = p_row.getSubscrSummary();
    subscr_summary_is_set = p_row.getSubscrSummaryIsSet();
    subscr_summary_is_modified = p_row.getSubscrSummaryIsModified();
    gp_product_code = p_row.getGpProductCode();
    gp_product_code_is_set = p_row.getGpProductCodeIsSet();
    gp_product_code_is_modified = p_row.getGpProductCodeIsModified();
    mortgage_customer = p_row.getMortgageCustomer();
    mortgage_customer_is_set = p_row.getMortgageCustomerIsSet();
    mortgage_customer_is_modified = p_row.getMortgageCustomerIsModified();
    mix_customer = p_row.getMixCustomer();
    mix_customer_is_set = p_row.getMixCustomerIsSet();
    mix_customer_is_modified = p_row.getMixCustomerIsModified();
    contract = p_row.getContract();
    contract_is_set = p_row.getContractIsSet();
    contract_is_modified = p_row.getContractIsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    send_timestamp = p_row.getSendTimestamp();
    send_timestamp_is_set = p_row.getSendTimestampIsSet();
    send_timestamp_is_modified = p_row.getSendTimestampIsModified();
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
      trader_code_is_set = true;
      trader_code_is_modified = true;
      acc_open_request_number_is_set = true;
      acc_open_request_number_is_modified = true;
      serial_no_is_set = true;
      serial_no_is_modified = true;
      course_is_set = true;
      course_is_modified = true;
      plan_is_set = true;
      plan_is_modified = true;
      regist_div_is_set = true;
      regist_div_is_modified = true;
      target_figure_is_set = true;
      target_figure_is_modified = true;
      target_year_is_set = true;
      target_year_is_modified = true;
      target_month_is_set = true;
      target_month_is_modified = true;
      installment_figure_is_set = true;
      installment_figure_is_modified = true;
      deposit_cycle_is_set = true;
      deposit_cycle_is_modified = true;
      payment_root_is_set = true;
      payment_root_is_modified = true;
      reinvest_div_is_set = true;
      reinvest_div_is_modified = true;
      tax_div_is_set = true;
      tax_div_is_modified = true;
      taxfree_limit_is_set = true;
      taxfree_limit_is_modified = true;
      special_taxfree_limit_is_set = true;
      special_taxfree_limit_is_modified = true;
      subscr_summary_is_set = true;
      subscr_summary_is_modified = true;
      gp_product_code_is_set = true;
      gp_product_code_is_modified = true;
      mortgage_customer_is_set = true;
      mortgage_customer_is_modified = true;
      mix_customer_is_set = true;
      mix_customer_is_modified = true;
      contract_is_set = true;
      contract_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      send_timestamp_is_set = true;
      send_timestamp_is_modified = true;
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
    if ( !( other instanceof HostGpRegVoucherRow ) )
       return false;
    return fieldsEqual( (HostGpRegVoucherRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostGpRegVoucherRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostGpRegVoucherRow row )
  {
    if ( order_request_number == null ) {
      if ( row.getOrderRequestNumber() != null )
        return false;
    } else if ( !order_request_number.equals( row.getOrderRequestNumber() ) ) {
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
    if ( acc_open_request_number == null ) {
      if ( row.getAccOpenRequestNumber() != null )
        return false;
    } else if ( !acc_open_request_number.equals( row.getAccOpenRequestNumber() ) ) {
        return false;
    }
    if ( serial_no == null ) {
      if ( row.getSerialNo() != null )
        return false;
    } else if ( !serial_no.equals( row.getSerialNo() ) ) {
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
    if ( regist_div == null ) {
      if ( row.getRegistDiv() != null )
        return false;
    } else if ( !regist_div.equals( row.getRegistDiv() ) ) {
        return false;
    }
    if ( target_figure == null ) {
      if ( row.getTargetFigure() != null )
        return false;
    } else if ( !target_figure.equals( row.getTargetFigure() ) ) {
        return false;
    }
    if ( target_year == null ) {
      if ( row.getTargetYear() != null )
        return false;
    } else if ( !target_year.equals( row.getTargetYear() ) ) {
        return false;
    }
    if ( target_month == null ) {
      if ( row.getTargetMonth() != null )
        return false;
    } else if ( !target_month.equals( row.getTargetMonth() ) ) {
        return false;
    }
    if ( installment_figure == null ) {
      if ( row.getInstallmentFigure() != null )
        return false;
    } else if ( !installment_figure.equals( row.getInstallmentFigure() ) ) {
        return false;
    }
    if ( deposit_cycle == null ) {
      if ( row.getDepositCycle() != null )
        return false;
    } else if ( !deposit_cycle.equals( row.getDepositCycle() ) ) {
        return false;
    }
    if ( payment_root == null ) {
      if ( row.getPaymentRoot() != null )
        return false;
    } else if ( !payment_root.equals( row.getPaymentRoot() ) ) {
        return false;
    }
    if ( reinvest_div == null ) {
      if ( row.getReinvestDiv() != null )
        return false;
    } else if ( !reinvest_div.equals( row.getReinvestDiv() ) ) {
        return false;
    }
    if ( tax_div == null ) {
      if ( row.getTaxDiv() != null )
        return false;
    } else if ( !tax_div.equals( row.getTaxDiv() ) ) {
        return false;
    }
    if ( taxfree_limit == null ) {
      if ( row.getTaxfreeLimit() != null )
        return false;
    } else if ( !taxfree_limit.equals( row.getTaxfreeLimit() ) ) {
        return false;
    }
    if ( special_taxfree_limit == null ) {
      if ( row.getSpecialTaxfreeLimit() != null )
        return false;
    } else if ( !special_taxfree_limit.equals( row.getSpecialTaxfreeLimit() ) ) {
        return false;
    }
    if ( subscr_summary == null ) {
      if ( row.getSubscrSummary() != null )
        return false;
    } else if ( !subscr_summary.equals( row.getSubscrSummary() ) ) {
        return false;
    }
    if ( gp_product_code == null ) {
      if ( row.getGpProductCode() != null )
        return false;
    } else if ( !gp_product_code.equals( row.getGpProductCode() ) ) {
        return false;
    }
    if ( mortgage_customer == null ) {
      if ( row.getMortgageCustomer() != null )
        return false;
    } else if ( !mortgage_customer.equals( row.getMortgageCustomer() ) ) {
        return false;
    }
    if ( mix_customer == null ) {
      if ( row.getMixCustomer() != null )
        return false;
    } else if ( !mix_customer.equals( row.getMixCustomer() ) ) {
        return false;
    }
    if ( contract == null ) {
      if ( row.getContract() != null )
        return false;
    } else if ( !contract.equals( row.getContract() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( send_timestamp == null ) {
      if ( row.getSendTimestamp() != null )
        return false;
    } else if ( !send_timestamp.equals( row.getSendTimestamp() ) ) {
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
      return  (order_request_number!=null? order_request_number.hashCode(): 0) 
        + (request_code!=null? request_code.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (trader_code!=null? trader_code.hashCode(): 0) 
        + (acc_open_request_number!=null? acc_open_request_number.hashCode(): 0) 
        + (serial_no!=null? serial_no.hashCode(): 0) 
        + (course!=null? course.hashCode(): 0) 
        + (plan!=null? plan.hashCode(): 0) 
        + (regist_div!=null? regist_div.hashCode(): 0) 
        + (target_figure!=null? target_figure.hashCode(): 0) 
        + (target_year!=null? target_year.hashCode(): 0) 
        + (target_month!=null? target_month.hashCode(): 0) 
        + (installment_figure!=null? installment_figure.hashCode(): 0) 
        + (deposit_cycle!=null? deposit_cycle.hashCode(): 0) 
        + (payment_root!=null? payment_root.hashCode(): 0) 
        + (reinvest_div!=null? reinvest_div.hashCode(): 0) 
        + (tax_div!=null? tax_div.hashCode(): 0) 
        + (taxfree_limit!=null? taxfree_limit.hashCode(): 0) 
        + (special_taxfree_limit!=null? special_taxfree_limit.hashCode(): 0) 
        + (subscr_summary!=null? subscr_summary.hashCode(): 0) 
        + (gp_product_code!=null? gp_product_code.hashCode(): 0) 
        + (mortgage_customer!=null? mortgage_customer.hashCode(): 0) 
        + (mix_customer!=null? mix_customer.hashCode(): 0) 
        + (contract!=null? contract.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (send_timestamp!=null? send_timestamp.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !acc_open_request_number_is_set )
			throw new IllegalArgumentException("non-nullable field 'acc_open_request_number' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("order_request_number",order_request_number);
		map.put("request_code",request_code);
		map.put("institution_code",institution_code);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( trader_code != null )
			map.put("trader_code",trader_code);
		map.put("acc_open_request_number",acc_open_request_number);
		if ( serial_no_is_set )
			map.put("serial_no",serial_no);
		if ( course != null )
			map.put("course",course);
		if ( plan != null )
			map.put("plan",plan);
		if ( regist_div != null )
			map.put("regist_div",regist_div);
		if ( target_figure != null )
			map.put("target_figure",target_figure);
		if ( target_year != null )
			map.put("target_year",target_year);
		if ( target_month != null )
			map.put("target_month",target_month);
		if ( installment_figure != null )
			map.put("installment_figure",installment_figure);
		if ( deposit_cycle != null )
			map.put("deposit_cycle",deposit_cycle);
		if ( payment_root != null )
			map.put("payment_root",payment_root);
		if ( reinvest_div != null )
			map.put("reinvest_div",reinvest_div);
		if ( tax_div != null )
			map.put("tax_div",tax_div);
		if ( taxfree_limit != null )
			map.put("taxfree_limit",taxfree_limit);
		if ( special_taxfree_limit != null )
			map.put("special_taxfree_limit",special_taxfree_limit);
		if ( subscr_summary != null )
			map.put("subscr_summary",subscr_summary);
		if ( gp_product_code != null )
			map.put("gp_product_code",gp_product_code);
		if ( mortgage_customer != null )
			map.put("mortgage_customer",mortgage_customer);
		if ( mix_customer != null )
			map.put("mix_customer",mix_customer);
		if ( contract != null )
			map.put("contract",contract);
		if ( status != null )
			map.put("status",status);
		if ( send_timestamp != null )
			map.put("send_timestamp",send_timestamp);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( trader_code_is_modified )
			map.put("trader_code",trader_code);
		if ( acc_open_request_number_is_modified )
			map.put("acc_open_request_number",acc_open_request_number);
		if ( serial_no_is_modified )
			map.put("serial_no",serial_no);
		if ( course_is_modified )
			map.put("course",course);
		if ( plan_is_modified )
			map.put("plan",plan);
		if ( regist_div_is_modified )
			map.put("regist_div",regist_div);
		if ( target_figure_is_modified )
			map.put("target_figure",target_figure);
		if ( target_year_is_modified )
			map.put("target_year",target_year);
		if ( target_month_is_modified )
			map.put("target_month",target_month);
		if ( installment_figure_is_modified )
			map.put("installment_figure",installment_figure);
		if ( deposit_cycle_is_modified )
			map.put("deposit_cycle",deposit_cycle);
		if ( payment_root_is_modified )
			map.put("payment_root",payment_root);
		if ( reinvest_div_is_modified )
			map.put("reinvest_div",reinvest_div);
		if ( tax_div_is_modified )
			map.put("tax_div",tax_div);
		if ( taxfree_limit_is_modified )
			map.put("taxfree_limit",taxfree_limit);
		if ( special_taxfree_limit_is_modified )
			map.put("special_taxfree_limit",special_taxfree_limit);
		if ( subscr_summary_is_modified )
			map.put("subscr_summary",subscr_summary);
		if ( gp_product_code_is_modified )
			map.put("gp_product_code",gp_product_code);
		if ( mortgage_customer_is_modified )
			map.put("mortgage_customer",mortgage_customer);
		if ( mix_customer_is_modified )
			map.put("mix_customer",mix_customer);
		if ( contract_is_modified )
			map.put("contract",contract);
		if ( status_is_modified )
			map.put("status",status);
		if ( send_timestamp_is_modified )
			map.put("send_timestamp",send_timestamp);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("trader_code",trader_code);
			if ( acc_open_request_number_is_set )
				map.put("acc_open_request_number",acc_open_request_number);
			if ( serial_no_is_set )
				map.put("serial_no",serial_no);
			map.put("course",course);
			map.put("plan",plan);
			map.put("regist_div",regist_div);
			map.put("target_figure",target_figure);
			map.put("target_year",target_year);
			map.put("target_month",target_month);
			map.put("installment_figure",installment_figure);
			map.put("deposit_cycle",deposit_cycle);
			map.put("payment_root",payment_root);
			map.put("reinvest_div",reinvest_div);
			map.put("tax_div",tax_div);
			map.put("taxfree_limit",taxfree_limit);
			map.put("special_taxfree_limit",special_taxfree_limit);
			map.put("subscr_summary",subscr_summary);
			map.put("gp_product_code",gp_product_code);
			map.put("mortgage_customer",mortgage_customer);
			map.put("mix_customer",mix_customer);
			map.put("contract",contract);
			map.put("status",status);
			map.put("send_timestamp",send_timestamp);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
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
   * <em>acc_open_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccOpenRequestNumber()
  {
    return acc_open_request_number;
  }


  /** 
   * <em>acc_open_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccOpenRequestNumberIsSet() {
    return acc_open_request_number_is_set;
  }


  /** 
   * <em>acc_open_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccOpenRequestNumberIsModified() {
    return acc_open_request_number_is_modified;
  }


  /** 
   * <em>serial_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSerialNo()
  {
    return serial_no;
  }


  /** 
   * <em>serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSerialNoIsSet() {
    return serial_no_is_set;
  }


  /** 
   * <em>serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSerialNoIsModified() {
    return serial_no_is_modified;
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
   * <em>regist_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRegistDiv()
  {
    return regist_div;
  }


  /** 
   * <em>regist_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistDivIsSet() {
    return regist_div_is_set;
  }


  /** 
   * <em>regist_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistDivIsModified() {
    return regist_div_is_modified;
  }


  /** 
   * <em>target_figure</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTargetFigure()
  {
    return target_figure;
  }


  /** 
   * <em>target_figure</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTargetFigureIsSet() {
    return target_figure_is_set;
  }


  /** 
   * <em>target_figure</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTargetFigureIsModified() {
    return target_figure_is_modified;
  }


  /** 
   * <em>target_year</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTargetYear()
  {
    return target_year;
  }


  /** 
   * <em>target_year</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTargetYearIsSet() {
    return target_year_is_set;
  }


  /** 
   * <em>target_year</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTargetYearIsModified() {
    return target_year_is_modified;
  }


  /** 
   * <em>target_month</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTargetMonth()
  {
    return target_month;
  }


  /** 
   * <em>target_month</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTargetMonthIsSet() {
    return target_month_is_set;
  }


  /** 
   * <em>target_month</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTargetMonthIsModified() {
    return target_month_is_modified;
  }


  /** 
   * <em>installment_figure</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstallmentFigure()
  {
    return installment_figure;
  }


  /** 
   * <em>installment_figure</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstallmentFigureIsSet() {
    return installment_figure_is_set;
  }


  /** 
   * <em>installment_figure</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstallmentFigureIsModified() {
    return installment_figure_is_modified;
  }


  /** 
   * <em>deposit_cycle</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDepositCycle()
  {
    return deposit_cycle;
  }


  /** 
   * <em>deposit_cycle</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositCycleIsSet() {
    return deposit_cycle_is_set;
  }


  /** 
   * <em>deposit_cycle</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositCycleIsModified() {
    return deposit_cycle_is_modified;
  }


  /** 
   * <em>payment_root</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaymentRoot()
  {
    return payment_root;
  }


  /** 
   * <em>payment_root</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRootIsSet() {
    return payment_root_is_set;
  }


  /** 
   * <em>payment_root</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRootIsModified() {
    return payment_root_is_modified;
  }


  /** 
   * <em>reinvest_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReinvestDiv()
  {
    return reinvest_div;
  }


  /** 
   * <em>reinvest_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReinvestDivIsSet() {
    return reinvest_div_is_set;
  }


  /** 
   * <em>reinvest_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReinvestDivIsModified() {
    return reinvest_div_is_modified;
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
   * <em>taxfree_limit</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTaxfreeLimit()
  {
    return taxfree_limit;
  }


  /** 
   * <em>taxfree_limit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxfreeLimitIsSet() {
    return taxfree_limit_is_set;
  }


  /** 
   * <em>taxfree_limit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxfreeLimitIsModified() {
    return taxfree_limit_is_modified;
  }


  /** 
   * <em>special_taxfree_limit</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSpecialTaxfreeLimit()
  {
    return special_taxfree_limit;
  }


  /** 
   * <em>special_taxfree_limit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialTaxfreeLimitIsSet() {
    return special_taxfree_limit_is_set;
  }


  /** 
   * <em>special_taxfree_limit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialTaxfreeLimitIsModified() {
    return special_taxfree_limit_is_modified;
  }


  /** 
   * <em>subscr_summary</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSubscrSummary()
  {
    return subscr_summary;
  }


  /** 
   * <em>subscr_summary</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubscrSummaryIsSet() {
    return subscr_summary_is_set;
  }


  /** 
   * <em>subscr_summary</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubscrSummaryIsModified() {
    return subscr_summary_is_modified;
  }


  /** 
   * <em>gp_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpProductCode()
  {
    return gp_product_code;
  }


  /** 
   * <em>gp_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpProductCodeIsSet() {
    return gp_product_code_is_set;
  }


  /** 
   * <em>gp_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpProductCodeIsModified() {
    return gp_product_code_is_modified;
  }


  /** 
   * <em>mortgage_customer</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMortgageCustomer()
  {
    return mortgage_customer;
  }


  /** 
   * <em>mortgage_customer</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMortgageCustomerIsSet() {
    return mortgage_customer_is_set;
  }


  /** 
   * <em>mortgage_customer</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMortgageCustomerIsModified() {
    return mortgage_customer_is_modified;
  }


  /** 
   * <em>mix_customer</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMixCustomer()
  {
    return mix_customer;
  }


  /** 
   * <em>mix_customer</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMixCustomerIsSet() {
    return mix_customer_is_set;
  }


  /** 
   * <em>mix_customer</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMixCustomerIsModified() {
    return mix_customer_is_modified;
  }


  /** 
   * <em>contract</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getContract()
  {
    return contract;
  }


  /** 
   * <em>contract</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractIsSet() {
    return contract_is_set;
  }


  /** 
   * <em>contract</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractIsModified() {
    return contract_is_modified;
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
   * <em>send_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getSendTimestamp()
  {
    return send_timestamp;
  }


  /** 
   * <em>send_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendTimestampIsSet() {
    return send_timestamp_is_set;
  }


  /** 
   * <em>send_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendTimestampIsModified() {
    return send_timestamp_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new HostGpRegVoucherPK(order_request_number, request_code, institution_code, branch_code, account_code);
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
   * <em>acc_open_request_number</em>カラムの値を設定します。 
   *
   * @@param p_accOpenRequestNumber <em>acc_open_request_number</em>カラムの値をあらわす13桁以下のString値 
   */
  public final void setAccOpenRequestNumber( String p_accOpenRequestNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acc_open_request_number = p_accOpenRequestNumber;
    acc_open_request_number_is_set = true;
    acc_open_request_number_is_modified = true;
  }


  /** 
   * <em>serial_no</em>カラムの値を設定します。 
   *
   * @@param p_serialNo <em>serial_no</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setSerialNo( String p_serialNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    serial_no = p_serialNo;
    serial_no_is_set = true;
    serial_no_is_modified = true;
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
   * <em>regist_div</em>カラムの値を設定します。 
   *
   * @@param p_registDiv <em>regist_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRegistDiv( String p_registDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    regist_div = p_registDiv;
    regist_div_is_set = true;
    regist_div_is_modified = true;
  }


  /** 
   * <em>target_figure</em>カラムの値を設定します。 
   *
   * @@param p_targetFigure <em>target_figure</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setTargetFigure( String p_targetFigure )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    target_figure = p_targetFigure;
    target_figure_is_set = true;
    target_figure_is_modified = true;
  }


  /** 
   * <em>target_year</em>カラムの値を設定します。 
   *
   * @@param p_targetYear <em>target_year</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setTargetYear( String p_targetYear )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    target_year = p_targetYear;
    target_year_is_set = true;
    target_year_is_modified = true;
  }


  /** 
   * <em>target_month</em>カラムの値を設定します。 
   *
   * @@param p_targetMonth <em>target_month</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setTargetMonth( String p_targetMonth )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    target_month = p_targetMonth;
    target_month_is_set = true;
    target_month_is_modified = true;
  }


  /** 
   * <em>installment_figure</em>カラムの値を設定します。 
   *
   * @@param p_installmentFigure <em>installment_figure</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setInstallmentFigure( String p_installmentFigure )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    installment_figure = p_installmentFigure;
    installment_figure_is_set = true;
    installment_figure_is_modified = true;
  }


  /** 
   * <em>deposit_cycle</em>カラムの値を設定します。 
   *
   * @@param p_depositCycle <em>deposit_cycle</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDepositCycle( String p_depositCycle )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deposit_cycle = p_depositCycle;
    deposit_cycle_is_set = true;
    deposit_cycle_is_modified = true;
  }


  /** 
   * <em>payment_root</em>カラムの値を設定します。 
   *
   * @@param p_paymentRoot <em>payment_root</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPaymentRoot( String p_paymentRoot )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_root = p_paymentRoot;
    payment_root_is_set = true;
    payment_root_is_modified = true;
  }


  /** 
   * <em>reinvest_div</em>カラムの値を設定します。 
   *
   * @@param p_reinvestDiv <em>reinvest_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setReinvestDiv( String p_reinvestDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reinvest_div = p_reinvestDiv;
    reinvest_div_is_set = true;
    reinvest_div_is_modified = true;
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
   * <em>taxfree_limit</em>カラムの値を設定します。 
   *
   * @@param p_taxfreeLimit <em>taxfree_limit</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setTaxfreeLimit( String p_taxfreeLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    taxfree_limit = p_taxfreeLimit;
    taxfree_limit_is_set = true;
    taxfree_limit_is_modified = true;
  }


  /** 
   * <em>special_taxfree_limit</em>カラムの値を設定します。 
   *
   * @@param p_specialTaxfreeLimit <em>special_taxfree_limit</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setSpecialTaxfreeLimit( String p_specialTaxfreeLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_taxfree_limit = p_specialTaxfreeLimit;
    special_taxfree_limit_is_set = true;
    special_taxfree_limit_is_modified = true;
  }


  /** 
   * <em>subscr_summary</em>カラムの値を設定します。 
   *
   * @@param p_subscrSummary <em>subscr_summary</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSubscrSummary( String p_subscrSummary )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    subscr_summary = p_subscrSummary;
    subscr_summary_is_set = true;
    subscr_summary_is_modified = true;
  }


  /** 
   * <em>gp_product_code</em>カラムの値を設定します。 
   *
   * @@param p_gpProductCode <em>gp_product_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGpProductCode( String p_gpProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_product_code = p_gpProductCode;
    gp_product_code_is_set = true;
    gp_product_code_is_modified = true;
  }


  /** 
   * <em>mortgage_customer</em>カラムの値を設定します。 
   *
   * @@param p_mortgageCustomer <em>mortgage_customer</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMortgageCustomer( String p_mortgageCustomer )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mortgage_customer = p_mortgageCustomer;
    mortgage_customer_is_set = true;
    mortgage_customer_is_modified = true;
  }


  /** 
   * <em>mix_customer</em>カラムの値を設定します。 
   *
   * @@param p_mixCustomer <em>mix_customer</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMixCustomer( String p_mixCustomer )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mix_customer = p_mixCustomer;
    mix_customer_is_set = true;
    mix_customer_is_modified = true;
  }


  /** 
   * <em>contract</em>カラムの値を設定します。 
   *
   * @@param p_contract <em>contract</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setContract( String p_contract )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract = p_contract;
    contract_is_set = true;
    contract_is_modified = true;
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
   * <em>send_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_sendTimestamp <em>send_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setSendTimestamp( java.sql.Timestamp p_sendTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    send_timestamp = p_sendTimestamp;
    send_timestamp_is_set = true;
    send_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>send_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setSendTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    send_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    send_timestamp_is_set = true;
    send_timestamp_is_modified = true;
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
                else if ( name.equals("acc_open_request_number") ) {
                    return this.acc_open_request_number;
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
                else if ( name.equals("contract") ) {
                    return this.contract;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("deposit_cycle") ) {
                    return this.deposit_cycle;
                }
                break;
            case 'g':
                if ( name.equals("gp_product_code") ) {
                    return this.gp_product_code;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("installment_figure") ) {
                    return this.installment_figure;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("mortgage_customer") ) {
                    return this.mortgage_customer;
                }
                else if ( name.equals("mix_customer") ) {
                    return this.mix_customer;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                break;
            case 'p':
                if ( name.equals("plan") ) {
                    return this.plan;
                }
                else if ( name.equals("payment_root") ) {
                    return this.payment_root;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("regist_div") ) {
                    return this.regist_div;
                }
                else if ( name.equals("reinvest_div") ) {
                    return this.reinvest_div;
                }
                break;
            case 's':
                if ( name.equals("serial_no") ) {
                    return this.serial_no;
                }
                else if ( name.equals("special_taxfree_limit") ) {
                    return this.special_taxfree_limit;
                }
                else if ( name.equals("subscr_summary") ) {
                    return this.subscr_summary;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                else if ( name.equals("send_timestamp") ) {
                    return this.send_timestamp;
                }
                break;
            case 't':
                if ( name.equals("trader_code") ) {
                    return this.trader_code;
                }
                else if ( name.equals("target_figure") ) {
                    return this.target_figure;
                }
                else if ( name.equals("target_year") ) {
                    return this.target_year;
                }
                else if ( name.equals("target_month") ) {
                    return this.target_month;
                }
                else if ( name.equals("tax_div") ) {
                    return this.tax_div;
                }
                else if ( name.equals("taxfree_limit") ) {
                    return this.taxfree_limit;
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
                else if ( name.equals("acc_open_request_number") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acc_open_request_number' must be String: '"+value+"' is not." );
                    this.acc_open_request_number = (String) value;
                    if (this.acc_open_request_number_is_set)
                        this.acc_open_request_number_is_modified = true;
                    this.acc_open_request_number_is_set = true;
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
                else if ( name.equals("contract") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'contract' must be String: '"+value+"' is not." );
                    this.contract = (String) value;
                    if (this.contract_is_set)
                        this.contract_is_modified = true;
                    this.contract_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
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
                if ( name.equals("deposit_cycle") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deposit_cycle' must be String: '"+value+"' is not." );
                    this.deposit_cycle = (String) value;
                    if (this.deposit_cycle_is_set)
                        this.deposit_cycle_is_modified = true;
                    this.deposit_cycle_is_set = true;
                    return;
                }
                break;
            case 'g':
                if ( name.equals("gp_product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_product_code' must be String: '"+value+"' is not." );
                    this.gp_product_code = (String) value;
                    if (this.gp_product_code_is_set)
                        this.gp_product_code_is_modified = true;
                    this.gp_product_code_is_set = true;
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
                else if ( name.equals("installment_figure") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'installment_figure' must be String: '"+value+"' is not." );
                    this.installment_figure = (String) value;
                    if (this.installment_figure_is_set)
                        this.installment_figure_is_modified = true;
                    this.installment_figure_is_set = true;
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
                if ( name.equals("mortgage_customer") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mortgage_customer' must be String: '"+value+"' is not." );
                    this.mortgage_customer = (String) value;
                    if (this.mortgage_customer_is_set)
                        this.mortgage_customer_is_modified = true;
                    this.mortgage_customer_is_set = true;
                    return;
                }
                else if ( name.equals("mix_customer") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mix_customer' must be String: '"+value+"' is not." );
                    this.mix_customer = (String) value;
                    if (this.mix_customer_is_set)
                        this.mix_customer_is_modified = true;
                    this.mix_customer_is_set = true;
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
                else if ( name.equals("payment_root") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_root' must be String: '"+value+"' is not." );
                    this.payment_root = (String) value;
                    if (this.payment_root_is_set)
                        this.payment_root_is_modified = true;
                    this.payment_root_is_set = true;
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
                else if ( name.equals("regist_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'regist_div' must be String: '"+value+"' is not." );
                    this.regist_div = (String) value;
                    if (this.regist_div_is_set)
                        this.regist_div_is_modified = true;
                    this.regist_div_is_set = true;
                    return;
                }
                else if ( name.equals("reinvest_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reinvest_div' must be String: '"+value+"' is not." );
                    this.reinvest_div = (String) value;
                    if (this.reinvest_div_is_set)
                        this.reinvest_div_is_modified = true;
                    this.reinvest_div_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("serial_no") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'serial_no' must be String: '"+value+"' is not." );
                    this.serial_no = (String) value;
                    if (this.serial_no_is_set)
                        this.serial_no_is_modified = true;
                    this.serial_no_is_set = true;
                    return;
                }
                else if ( name.equals("special_taxfree_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'special_taxfree_limit' must be String: '"+value+"' is not." );
                    this.special_taxfree_limit = (String) value;
                    if (this.special_taxfree_limit_is_set)
                        this.special_taxfree_limit_is_modified = true;
                    this.special_taxfree_limit_is_set = true;
                    return;
                }
                else if ( name.equals("subscr_summary") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'subscr_summary' must be String: '"+value+"' is not." );
                    this.subscr_summary = (String) value;
                    if (this.subscr_summary_is_set)
                        this.subscr_summary_is_modified = true;
                    this.subscr_summary_is_set = true;
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
                else if ( name.equals("send_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'send_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.send_timestamp = (java.sql.Timestamp) value;
                    if (this.send_timestamp_is_set)
                        this.send_timestamp_is_modified = true;
                    this.send_timestamp_is_set = true;
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
                else if ( name.equals("target_figure") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'target_figure' must be String: '"+value+"' is not." );
                    this.target_figure = (String) value;
                    if (this.target_figure_is_set)
                        this.target_figure_is_modified = true;
                    this.target_figure_is_set = true;
                    return;
                }
                else if ( name.equals("target_year") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'target_year' must be String: '"+value+"' is not." );
                    this.target_year = (String) value;
                    if (this.target_year_is_set)
                        this.target_year_is_modified = true;
                    this.target_year_is_set = true;
                    return;
                }
                else if ( name.equals("target_month") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'target_month' must be String: '"+value+"' is not." );
                    this.target_month = (String) value;
                    if (this.target_month_is_set)
                        this.target_month_is_modified = true;
                    this.target_month_is_set = true;
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
                else if ( name.equals("taxfree_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'taxfree_limit' must be String: '"+value+"' is not." );
                    this.taxfree_limit = (String) value;
                    if (this.taxfree_limit_is_set)
                        this.taxfree_limit_is_modified = true;
                    this.taxfree_limit_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
