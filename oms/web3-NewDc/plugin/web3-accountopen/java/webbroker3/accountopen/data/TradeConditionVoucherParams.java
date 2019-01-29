head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.19.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	TradeConditionVoucherParams.java;


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
 * trade_condition_voucherテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link TradeConditionVoucherRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link TradeConditionVoucherParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link TradeConditionVoucherParams}が{@@link TradeConditionVoucherRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TradeConditionVoucherPK 
 * @@see TradeConditionVoucherRow 
 */
public class TradeConditionVoucherParams extends Params implements TradeConditionVoucherRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "trade_condition_voucher";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = TradeConditionVoucherRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return TradeConditionVoucherRow.TYPE;
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
   * <em>data_class</em>カラムの値 
   */
  public  String  data_class;

  /** 
   * <em>trading_e_report_div</em>カラムの値 
   */
  public  String  trading_e_report_div;

  /** 
   * <em>inv_e_report_div</em>カラムの値 
   */
  public  String  inv_e_report_div;

  /** 
   * <em>refund_e_report_div</em>カラムの値 
   */
  public  String  refund_e_report_div;

  /** 
   * <em>e_report_div1</em>カラムの値 
   */
  public  String  e_report_div1;

  /** 
   * <em>e_report_div2</em>カラムの値 
   */
  public  String  e_report_div2;

  /** 
   * <em>e_report_div3</em>カラムの値 
   */
  public  String  e_report_div3;

  /** 
   * <em>pos_report_term_div</em>カラムの値 
   */
  public  String  pos_report_term_div;

  /** 
   * <em>pos_report_cycle_div</em>カラムの値 
   */
  public  String  pos_report_cycle_div;

  /** 
   * <em>pos_report_certif_depo_div</em>カラムの値 
   */
  public  String  pos_report_certif_depo_div;

  /** 
   * <em>pos_report_acc_state_div</em>カラムの値 
   */
  public  String  pos_report_acc_state_div;

  /** 
   * <em>equity_tax_div</em>カラムの値 
   */
  public  String  equity_tax_div;

  /** 
   * <em>equity_tax_div_next</em>カラムの値 
   */
  public  String  equity_tax_div_next;

  /** 
   * <em>equity_sp_acc_open_dat</em>カラムの値 
   */
  public  String  equity_sp_acc_open_dat;

  /** 
   * <em>margin_tax_div</em>カラムの値 
   */
  public  String  margin_tax_div;

  /** 
   * <em>margin_tax_div_next</em>カラムの値 
   */
  public  String  margin_tax_div_next;

  /** 
   * <em>margin_sp_acc_open_dat</em>カラムの値 
   */
  public  String  margin_sp_acc_open_dat;

  /** 
   * <em>sp_mng_acc_open_div</em>カラムの値 
   */
  public  String  sp_mng_acc_open_div;

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

  /** 
   * <em>occupation_div</em>カラムの値 
   */
  public  String  occupation_div;

  /** 
   * <em>regist_div</em>カラムの値 
   */
  public  String  regist_div;

  /** 
   * <em>mobile_office_info_regist_id</em>カラムの値 
   */
  public  Long  mobile_office_info_regist_id;

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
  private boolean data_class_is_set = false;
  private boolean data_class_is_modified = false;
  private boolean trading_e_report_div_is_set = false;
  private boolean trading_e_report_div_is_modified = false;
  private boolean inv_e_report_div_is_set = false;
  private boolean inv_e_report_div_is_modified = false;
  private boolean refund_e_report_div_is_set = false;
  private boolean refund_e_report_div_is_modified = false;
  private boolean e_report_div1_is_set = false;
  private boolean e_report_div1_is_modified = false;
  private boolean e_report_div2_is_set = false;
  private boolean e_report_div2_is_modified = false;
  private boolean e_report_div3_is_set = false;
  private boolean e_report_div3_is_modified = false;
  private boolean pos_report_term_div_is_set = false;
  private boolean pos_report_term_div_is_modified = false;
  private boolean pos_report_cycle_div_is_set = false;
  private boolean pos_report_cycle_div_is_modified = false;
  private boolean pos_report_certif_depo_div_is_set = false;
  private boolean pos_report_certif_depo_div_is_modified = false;
  private boolean pos_report_acc_state_div_is_set = false;
  private boolean pos_report_acc_state_div_is_modified = false;
  private boolean equity_tax_div_is_set = false;
  private boolean equity_tax_div_is_modified = false;
  private boolean equity_tax_div_next_is_set = false;
  private boolean equity_tax_div_next_is_modified = false;
  private boolean equity_sp_acc_open_dat_is_set = false;
  private boolean equity_sp_acc_open_dat_is_modified = false;
  private boolean margin_tax_div_is_set = false;
  private boolean margin_tax_div_is_modified = false;
  private boolean margin_tax_div_next_is_set = false;
  private boolean margin_tax_div_next_is_modified = false;
  private boolean margin_sp_acc_open_dat_is_set = false;
  private boolean margin_sp_acc_open_dat_is_modified = false;
  private boolean sp_mng_acc_open_div_is_set = false;
  private boolean sp_mng_acc_open_div_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean send_timestamp_is_set = false;
  private boolean send_timestamp_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean occupation_div_is_set = false;
  private boolean occupation_div_is_modified = false;
  private boolean regist_div_is_set = false;
  private boolean regist_div_is_modified = false;
  private boolean mobile_office_info_regist_id_is_set = false;
  private boolean mobile_office_info_regist_id_is_modified = false;


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
      + "," + "data_class=" +data_class
      + "," + "trading_e_report_div=" +trading_e_report_div
      + "," + "inv_e_report_div=" +inv_e_report_div
      + "," + "refund_e_report_div=" +refund_e_report_div
      + "," + "e_report_div1=" +e_report_div1
      + "," + "e_report_div2=" +e_report_div2
      + "," + "e_report_div3=" +e_report_div3
      + "," + "pos_report_term_div=" +pos_report_term_div
      + "," + "pos_report_cycle_div=" +pos_report_cycle_div
      + "," + "pos_report_certif_depo_div=" +pos_report_certif_depo_div
      + "," + "pos_report_acc_state_div=" +pos_report_acc_state_div
      + "," + "equity_tax_div=" +equity_tax_div
      + "," + "equity_tax_div_next=" +equity_tax_div_next
      + "," + "equity_sp_acc_open_dat=" +equity_sp_acc_open_dat
      + "," + "margin_tax_div=" +margin_tax_div
      + "," + "margin_tax_div_next=" +margin_tax_div_next
      + "," + "margin_sp_acc_open_dat=" +margin_sp_acc_open_dat
      + "," + "sp_mng_acc_open_div=" +sp_mng_acc_open_div
      + "," + "status=" +status
      + "," + "send_timestamp=" +send_timestamp
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "occupation_div=" +occupation_div
      + "," + "regist_div=" +regist_div
      + "," + "mobile_office_info_regist_id=" +mobile_office_info_regist_id
      + "}";
  }


  /** 
   * 値が未設定のTradeConditionVoucherParamsオブジェクトを作成します。 
   */
  public TradeConditionVoucherParams() {
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
   * 指定のTradeConditionVoucherRowオブジェクトの値を利用してTradeConditionVoucherParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するTradeConditionVoucherRowオブジェクト 
   */
  public TradeConditionVoucherParams( TradeConditionVoucherRow p_row )
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
    data_class = p_row.getDataClass();
    data_class_is_set = p_row.getDataClassIsSet();
    data_class_is_modified = p_row.getDataClassIsModified();
    trading_e_report_div = p_row.getTradingEReportDiv();
    trading_e_report_div_is_set = p_row.getTradingEReportDivIsSet();
    trading_e_report_div_is_modified = p_row.getTradingEReportDivIsModified();
    inv_e_report_div = p_row.getInvEReportDiv();
    inv_e_report_div_is_set = p_row.getInvEReportDivIsSet();
    inv_e_report_div_is_modified = p_row.getInvEReportDivIsModified();
    refund_e_report_div = p_row.getRefundEReportDiv();
    refund_e_report_div_is_set = p_row.getRefundEReportDivIsSet();
    refund_e_report_div_is_modified = p_row.getRefundEReportDivIsModified();
    e_report_div1 = p_row.getEReportDiv1();
    e_report_div1_is_set = p_row.getEReportDiv1IsSet();
    e_report_div1_is_modified = p_row.getEReportDiv1IsModified();
    e_report_div2 = p_row.getEReportDiv2();
    e_report_div2_is_set = p_row.getEReportDiv2IsSet();
    e_report_div2_is_modified = p_row.getEReportDiv2IsModified();
    e_report_div3 = p_row.getEReportDiv3();
    e_report_div3_is_set = p_row.getEReportDiv3IsSet();
    e_report_div3_is_modified = p_row.getEReportDiv3IsModified();
    pos_report_term_div = p_row.getPosReportTermDiv();
    pos_report_term_div_is_set = p_row.getPosReportTermDivIsSet();
    pos_report_term_div_is_modified = p_row.getPosReportTermDivIsModified();
    pos_report_cycle_div = p_row.getPosReportCycleDiv();
    pos_report_cycle_div_is_set = p_row.getPosReportCycleDivIsSet();
    pos_report_cycle_div_is_modified = p_row.getPosReportCycleDivIsModified();
    pos_report_certif_depo_div = p_row.getPosReportCertifDepoDiv();
    pos_report_certif_depo_div_is_set = p_row.getPosReportCertifDepoDivIsSet();
    pos_report_certif_depo_div_is_modified = p_row.getPosReportCertifDepoDivIsModified();
    pos_report_acc_state_div = p_row.getPosReportAccStateDiv();
    pos_report_acc_state_div_is_set = p_row.getPosReportAccStateDivIsSet();
    pos_report_acc_state_div_is_modified = p_row.getPosReportAccStateDivIsModified();
    equity_tax_div = p_row.getEquityTaxDiv();
    equity_tax_div_is_set = p_row.getEquityTaxDivIsSet();
    equity_tax_div_is_modified = p_row.getEquityTaxDivIsModified();
    equity_tax_div_next = p_row.getEquityTaxDivNext();
    equity_tax_div_next_is_set = p_row.getEquityTaxDivNextIsSet();
    equity_tax_div_next_is_modified = p_row.getEquityTaxDivNextIsModified();
    equity_sp_acc_open_dat = p_row.getEquitySpAccOpenDat();
    equity_sp_acc_open_dat_is_set = p_row.getEquitySpAccOpenDatIsSet();
    equity_sp_acc_open_dat_is_modified = p_row.getEquitySpAccOpenDatIsModified();
    margin_tax_div = p_row.getMarginTaxDiv();
    margin_tax_div_is_set = p_row.getMarginTaxDivIsSet();
    margin_tax_div_is_modified = p_row.getMarginTaxDivIsModified();
    margin_tax_div_next = p_row.getMarginTaxDivNext();
    margin_tax_div_next_is_set = p_row.getMarginTaxDivNextIsSet();
    margin_tax_div_next_is_modified = p_row.getMarginTaxDivNextIsModified();
    margin_sp_acc_open_dat = p_row.getMarginSpAccOpenDat();
    margin_sp_acc_open_dat_is_set = p_row.getMarginSpAccOpenDatIsSet();
    margin_sp_acc_open_dat_is_modified = p_row.getMarginSpAccOpenDatIsModified();
    sp_mng_acc_open_div = p_row.getSpMngAccOpenDiv();
    sp_mng_acc_open_div_is_set = p_row.getSpMngAccOpenDivIsSet();
    sp_mng_acc_open_div_is_modified = p_row.getSpMngAccOpenDivIsModified();
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
    occupation_div = p_row.getOccupationDiv();
    occupation_div_is_set = p_row.getOccupationDivIsSet();
    occupation_div_is_modified = p_row.getOccupationDivIsModified();
    regist_div = p_row.getRegistDiv();
    regist_div_is_set = p_row.getRegistDivIsSet();
    regist_div_is_modified = p_row.getRegistDivIsModified();
    if ( !p_row.getMobileOfficeInfoRegistIdIsNull() )
      mobile_office_info_regist_id = new Long( p_row.getMobileOfficeInfoRegistId() );
    mobile_office_info_regist_id_is_set = p_row.getMobileOfficeInfoRegistIdIsSet();
    mobile_office_info_regist_id_is_modified = p_row.getMobileOfficeInfoRegistIdIsModified();
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
      data_class_is_set = true;
      data_class_is_modified = true;
      trading_e_report_div_is_set = true;
      trading_e_report_div_is_modified = true;
      inv_e_report_div_is_set = true;
      inv_e_report_div_is_modified = true;
      refund_e_report_div_is_set = true;
      refund_e_report_div_is_modified = true;
      e_report_div1_is_set = true;
      e_report_div1_is_modified = true;
      e_report_div2_is_set = true;
      e_report_div2_is_modified = true;
      e_report_div3_is_set = true;
      e_report_div3_is_modified = true;
      pos_report_term_div_is_set = true;
      pos_report_term_div_is_modified = true;
      pos_report_cycle_div_is_set = true;
      pos_report_cycle_div_is_modified = true;
      pos_report_certif_depo_div_is_set = true;
      pos_report_certif_depo_div_is_modified = true;
      pos_report_acc_state_div_is_set = true;
      pos_report_acc_state_div_is_modified = true;
      equity_tax_div_is_set = true;
      equity_tax_div_is_modified = true;
      equity_tax_div_next_is_set = true;
      equity_tax_div_next_is_modified = true;
      equity_sp_acc_open_dat_is_set = true;
      equity_sp_acc_open_dat_is_modified = true;
      margin_tax_div_is_set = true;
      margin_tax_div_is_modified = true;
      margin_tax_div_next_is_set = true;
      margin_tax_div_next_is_modified = true;
      margin_sp_acc_open_dat_is_set = true;
      margin_sp_acc_open_dat_is_modified = true;
      sp_mng_acc_open_div_is_set = true;
      sp_mng_acc_open_div_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      send_timestamp_is_set = true;
      send_timestamp_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      occupation_div_is_set = true;
      occupation_div_is_modified = true;
      regist_div_is_set = true;
      regist_div_is_modified = true;
      mobile_office_info_regist_id_is_set = true;
      mobile_office_info_regist_id_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof TradeConditionVoucherRow ) )
       return false;
    return fieldsEqual( (TradeConditionVoucherRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のTradeConditionVoucherRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( TradeConditionVoucherRow row )
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
    if ( data_class == null ) {
      if ( row.getDataClass() != null )
        return false;
    } else if ( !data_class.equals( row.getDataClass() ) ) {
        return false;
    }
    if ( trading_e_report_div == null ) {
      if ( row.getTradingEReportDiv() != null )
        return false;
    } else if ( !trading_e_report_div.equals( row.getTradingEReportDiv() ) ) {
        return false;
    }
    if ( inv_e_report_div == null ) {
      if ( row.getInvEReportDiv() != null )
        return false;
    } else if ( !inv_e_report_div.equals( row.getInvEReportDiv() ) ) {
        return false;
    }
    if ( refund_e_report_div == null ) {
      if ( row.getRefundEReportDiv() != null )
        return false;
    } else if ( !refund_e_report_div.equals( row.getRefundEReportDiv() ) ) {
        return false;
    }
    if ( e_report_div1 == null ) {
      if ( row.getEReportDiv1() != null )
        return false;
    } else if ( !e_report_div1.equals( row.getEReportDiv1() ) ) {
        return false;
    }
    if ( e_report_div2 == null ) {
      if ( row.getEReportDiv2() != null )
        return false;
    } else if ( !e_report_div2.equals( row.getEReportDiv2() ) ) {
        return false;
    }
    if ( e_report_div3 == null ) {
      if ( row.getEReportDiv3() != null )
        return false;
    } else if ( !e_report_div3.equals( row.getEReportDiv3() ) ) {
        return false;
    }
    if ( pos_report_term_div == null ) {
      if ( row.getPosReportTermDiv() != null )
        return false;
    } else if ( !pos_report_term_div.equals( row.getPosReportTermDiv() ) ) {
        return false;
    }
    if ( pos_report_cycle_div == null ) {
      if ( row.getPosReportCycleDiv() != null )
        return false;
    } else if ( !pos_report_cycle_div.equals( row.getPosReportCycleDiv() ) ) {
        return false;
    }
    if ( pos_report_certif_depo_div == null ) {
      if ( row.getPosReportCertifDepoDiv() != null )
        return false;
    } else if ( !pos_report_certif_depo_div.equals( row.getPosReportCertifDepoDiv() ) ) {
        return false;
    }
    if ( pos_report_acc_state_div == null ) {
      if ( row.getPosReportAccStateDiv() != null )
        return false;
    } else if ( !pos_report_acc_state_div.equals( row.getPosReportAccStateDiv() ) ) {
        return false;
    }
    if ( equity_tax_div == null ) {
      if ( row.getEquityTaxDiv() != null )
        return false;
    } else if ( !equity_tax_div.equals( row.getEquityTaxDiv() ) ) {
        return false;
    }
    if ( equity_tax_div_next == null ) {
      if ( row.getEquityTaxDivNext() != null )
        return false;
    } else if ( !equity_tax_div_next.equals( row.getEquityTaxDivNext() ) ) {
        return false;
    }
    if ( equity_sp_acc_open_dat == null ) {
      if ( row.getEquitySpAccOpenDat() != null )
        return false;
    } else if ( !equity_sp_acc_open_dat.equals( row.getEquitySpAccOpenDat() ) ) {
        return false;
    }
    if ( margin_tax_div == null ) {
      if ( row.getMarginTaxDiv() != null )
        return false;
    } else if ( !margin_tax_div.equals( row.getMarginTaxDiv() ) ) {
        return false;
    }
    if ( margin_tax_div_next == null ) {
      if ( row.getMarginTaxDivNext() != null )
        return false;
    } else if ( !margin_tax_div_next.equals( row.getMarginTaxDivNext() ) ) {
        return false;
    }
    if ( margin_sp_acc_open_dat == null ) {
      if ( row.getMarginSpAccOpenDat() != null )
        return false;
    } else if ( !margin_sp_acc_open_dat.equals( row.getMarginSpAccOpenDat() ) ) {
        return false;
    }
    if ( sp_mng_acc_open_div == null ) {
      if ( row.getSpMngAccOpenDiv() != null )
        return false;
    } else if ( !sp_mng_acc_open_div.equals( row.getSpMngAccOpenDiv() ) ) {
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
    if ( occupation_div == null ) {
      if ( row.getOccupationDiv() != null )
        return false;
    } else if ( !occupation_div.equals( row.getOccupationDiv() ) ) {
        return false;
    }
    if ( regist_div == null ) {
      if ( row.getRegistDiv() != null )
        return false;
    } else if ( !regist_div.equals( row.getRegistDiv() ) ) {
        return false;
    }
    if ( mobile_office_info_regist_id == null ) {
      if ( !row.getMobileOfficeInfoRegistIdIsNull() )
        return false;
    } else if ( row.getMobileOfficeInfoRegistIdIsNull() || ( mobile_office_info_regist_id.longValue() != row.getMobileOfficeInfoRegistId() ) ) {
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
        + (data_class!=null? data_class.hashCode(): 0) 
        + (trading_e_report_div!=null? trading_e_report_div.hashCode(): 0) 
        + (inv_e_report_div!=null? inv_e_report_div.hashCode(): 0) 
        + (refund_e_report_div!=null? refund_e_report_div.hashCode(): 0) 
        + (e_report_div1!=null? e_report_div1.hashCode(): 0) 
        + (e_report_div2!=null? e_report_div2.hashCode(): 0) 
        + (e_report_div3!=null? e_report_div3.hashCode(): 0) 
        + (pos_report_term_div!=null? pos_report_term_div.hashCode(): 0) 
        + (pos_report_cycle_div!=null? pos_report_cycle_div.hashCode(): 0) 
        + (pos_report_certif_depo_div!=null? pos_report_certif_depo_div.hashCode(): 0) 
        + (pos_report_acc_state_div!=null? pos_report_acc_state_div.hashCode(): 0) 
        + (equity_tax_div!=null? equity_tax_div.hashCode(): 0) 
        + (equity_tax_div_next!=null? equity_tax_div_next.hashCode(): 0) 
        + (equity_sp_acc_open_dat!=null? equity_sp_acc_open_dat.hashCode(): 0) 
        + (margin_tax_div!=null? margin_tax_div.hashCode(): 0) 
        + (margin_tax_div_next!=null? margin_tax_div_next.hashCode(): 0) 
        + (margin_sp_acc_open_dat!=null? margin_sp_acc_open_dat.hashCode(): 0) 
        + (sp_mng_acc_open_div!=null? sp_mng_acc_open_div.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (send_timestamp!=null? send_timestamp.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (occupation_div!=null? occupation_div.hashCode(): 0) 
        + (regist_div!=null? regist_div.hashCode(): 0) 
        + (mobile_office_info_regist_id!=null? mobile_office_info_regist_id.hashCode(): 0) 
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
		if ( data_class != null )
			map.put("data_class",data_class);
		if ( trading_e_report_div != null )
			map.put("trading_e_report_div",trading_e_report_div);
		if ( inv_e_report_div != null )
			map.put("inv_e_report_div",inv_e_report_div);
		if ( refund_e_report_div != null )
			map.put("refund_e_report_div",refund_e_report_div);
		if ( e_report_div1 != null )
			map.put("e_report_div1",e_report_div1);
		if ( e_report_div2 != null )
			map.put("e_report_div2",e_report_div2);
		if ( e_report_div3 != null )
			map.put("e_report_div3",e_report_div3);
		if ( pos_report_term_div != null )
			map.put("pos_report_term_div",pos_report_term_div);
		if ( pos_report_cycle_div != null )
			map.put("pos_report_cycle_div",pos_report_cycle_div);
		if ( pos_report_certif_depo_div != null )
			map.put("pos_report_certif_depo_div",pos_report_certif_depo_div);
		if ( pos_report_acc_state_div != null )
			map.put("pos_report_acc_state_div",pos_report_acc_state_div);
		if ( equity_tax_div != null )
			map.put("equity_tax_div",equity_tax_div);
		if ( equity_tax_div_next != null )
			map.put("equity_tax_div_next",equity_tax_div_next);
		if ( equity_sp_acc_open_dat != null )
			map.put("equity_sp_acc_open_dat",equity_sp_acc_open_dat);
		if ( margin_tax_div != null )
			map.put("margin_tax_div",margin_tax_div);
		if ( margin_tax_div_next != null )
			map.put("margin_tax_div_next",margin_tax_div_next);
		if ( margin_sp_acc_open_dat != null )
			map.put("margin_sp_acc_open_dat",margin_sp_acc_open_dat);
		if ( sp_mng_acc_open_div != null )
			map.put("sp_mng_acc_open_div",sp_mng_acc_open_div);
		if ( status != null )
			map.put("status",status);
		if ( send_timestamp != null )
			map.put("send_timestamp",send_timestamp);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		if ( occupation_div != null )
			map.put("occupation_div",occupation_div);
		if ( regist_div != null )
			map.put("regist_div",regist_div);
		if ( mobile_office_info_regist_id != null )
			map.put("mobile_office_info_regist_id",mobile_office_info_regist_id);
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
		if ( data_class_is_modified )
			map.put("data_class",data_class);
		if ( trading_e_report_div_is_modified )
			map.put("trading_e_report_div",trading_e_report_div);
		if ( inv_e_report_div_is_modified )
			map.put("inv_e_report_div",inv_e_report_div);
		if ( refund_e_report_div_is_modified )
			map.put("refund_e_report_div",refund_e_report_div);
		if ( e_report_div1_is_modified )
			map.put("e_report_div1",e_report_div1);
		if ( e_report_div2_is_modified )
			map.put("e_report_div2",e_report_div2);
		if ( e_report_div3_is_modified )
			map.put("e_report_div3",e_report_div3);
		if ( pos_report_term_div_is_modified )
			map.put("pos_report_term_div",pos_report_term_div);
		if ( pos_report_cycle_div_is_modified )
			map.put("pos_report_cycle_div",pos_report_cycle_div);
		if ( pos_report_certif_depo_div_is_modified )
			map.put("pos_report_certif_depo_div",pos_report_certif_depo_div);
		if ( pos_report_acc_state_div_is_modified )
			map.put("pos_report_acc_state_div",pos_report_acc_state_div);
		if ( equity_tax_div_is_modified )
			map.put("equity_tax_div",equity_tax_div);
		if ( equity_tax_div_next_is_modified )
			map.put("equity_tax_div_next",equity_tax_div_next);
		if ( equity_sp_acc_open_dat_is_modified )
			map.put("equity_sp_acc_open_dat",equity_sp_acc_open_dat);
		if ( margin_tax_div_is_modified )
			map.put("margin_tax_div",margin_tax_div);
		if ( margin_tax_div_next_is_modified )
			map.put("margin_tax_div_next",margin_tax_div_next);
		if ( margin_sp_acc_open_dat_is_modified )
			map.put("margin_sp_acc_open_dat",margin_sp_acc_open_dat);
		if ( sp_mng_acc_open_div_is_modified )
			map.put("sp_mng_acc_open_div",sp_mng_acc_open_div);
		if ( status_is_modified )
			map.put("status",status);
		if ( send_timestamp_is_modified )
			map.put("send_timestamp",send_timestamp);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( occupation_div_is_modified )
			map.put("occupation_div",occupation_div);
		if ( regist_div_is_modified )
			map.put("regist_div",regist_div);
		if ( mobile_office_info_regist_id_is_modified )
			map.put("mobile_office_info_regist_id",mobile_office_info_regist_id);
		if (map.size() == 0) {
			map.put("trader_code",trader_code);
			if ( acc_open_request_number_is_set )
				map.put("acc_open_request_number",acc_open_request_number);
			if ( serial_no_is_set )
				map.put("serial_no",serial_no);
			map.put("data_class",data_class);
			map.put("trading_e_report_div",trading_e_report_div);
			map.put("inv_e_report_div",inv_e_report_div);
			map.put("refund_e_report_div",refund_e_report_div);
			map.put("e_report_div1",e_report_div1);
			map.put("e_report_div2",e_report_div2);
			map.put("e_report_div3",e_report_div3);
			map.put("pos_report_term_div",pos_report_term_div);
			map.put("pos_report_cycle_div",pos_report_cycle_div);
			map.put("pos_report_certif_depo_div",pos_report_certif_depo_div);
			map.put("pos_report_acc_state_div",pos_report_acc_state_div);
			map.put("equity_tax_div",equity_tax_div);
			map.put("equity_tax_div_next",equity_tax_div_next);
			map.put("equity_sp_acc_open_dat",equity_sp_acc_open_dat);
			map.put("margin_tax_div",margin_tax_div);
			map.put("margin_tax_div_next",margin_tax_div_next);
			map.put("margin_sp_acc_open_dat",margin_sp_acc_open_dat);
			map.put("sp_mng_acc_open_div",sp_mng_acc_open_div);
			map.put("status",status);
			map.put("send_timestamp",send_timestamp);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("occupation_div",occupation_div);
			map.put("regist_div",regist_div);
			map.put("mobile_office_info_regist_id",mobile_office_info_regist_id);
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
   * <em>data_class</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDataClass()
  {
    return data_class;
  }


  /** 
   * <em>data_class</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataClassIsSet() {
    return data_class_is_set;
  }


  /** 
   * <em>data_class</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataClassIsModified() {
    return data_class_is_modified;
  }


  /** 
   * <em>trading_e_report_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradingEReportDiv()
  {
    return trading_e_report_div;
  }


  /** 
   * <em>trading_e_report_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingEReportDivIsSet() {
    return trading_e_report_div_is_set;
  }


  /** 
   * <em>trading_e_report_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingEReportDivIsModified() {
    return trading_e_report_div_is_modified;
  }


  /** 
   * <em>inv_e_report_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInvEReportDiv()
  {
    return inv_e_report_div;
  }


  /** 
   * <em>inv_e_report_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInvEReportDivIsSet() {
    return inv_e_report_div_is_set;
  }


  /** 
   * <em>inv_e_report_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInvEReportDivIsModified() {
    return inv_e_report_div_is_modified;
  }


  /** 
   * <em>refund_e_report_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRefundEReportDiv()
  {
    return refund_e_report_div;
  }


  /** 
   * <em>refund_e_report_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRefundEReportDivIsSet() {
    return refund_e_report_div_is_set;
  }


  /** 
   * <em>refund_e_report_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRefundEReportDivIsModified() {
    return refund_e_report_div_is_modified;
  }


  /** 
   * <em>e_report_div1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEReportDiv1()
  {
    return e_report_div1;
  }


  /** 
   * <em>e_report_div1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEReportDiv1IsSet() {
    return e_report_div1_is_set;
  }


  /** 
   * <em>e_report_div1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEReportDiv1IsModified() {
    return e_report_div1_is_modified;
  }


  /** 
   * <em>e_report_div2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEReportDiv2()
  {
    return e_report_div2;
  }


  /** 
   * <em>e_report_div2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEReportDiv2IsSet() {
    return e_report_div2_is_set;
  }


  /** 
   * <em>e_report_div2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEReportDiv2IsModified() {
    return e_report_div2_is_modified;
  }


  /** 
   * <em>e_report_div3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEReportDiv3()
  {
    return e_report_div3;
  }


  /** 
   * <em>e_report_div3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEReportDiv3IsSet() {
    return e_report_div3_is_set;
  }


  /** 
   * <em>e_report_div3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEReportDiv3IsModified() {
    return e_report_div3_is_modified;
  }


  /** 
   * <em>pos_report_term_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPosReportTermDiv()
  {
    return pos_report_term_div;
  }


  /** 
   * <em>pos_report_term_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPosReportTermDivIsSet() {
    return pos_report_term_div_is_set;
  }


  /** 
   * <em>pos_report_term_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPosReportTermDivIsModified() {
    return pos_report_term_div_is_modified;
  }


  /** 
   * <em>pos_report_cycle_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPosReportCycleDiv()
  {
    return pos_report_cycle_div;
  }


  /** 
   * <em>pos_report_cycle_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPosReportCycleDivIsSet() {
    return pos_report_cycle_div_is_set;
  }


  /** 
   * <em>pos_report_cycle_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPosReportCycleDivIsModified() {
    return pos_report_cycle_div_is_modified;
  }


  /** 
   * <em>pos_report_certif_depo_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPosReportCertifDepoDiv()
  {
    return pos_report_certif_depo_div;
  }


  /** 
   * <em>pos_report_certif_depo_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPosReportCertifDepoDivIsSet() {
    return pos_report_certif_depo_div_is_set;
  }


  /** 
   * <em>pos_report_certif_depo_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPosReportCertifDepoDivIsModified() {
    return pos_report_certif_depo_div_is_modified;
  }


  /** 
   * <em>pos_report_acc_state_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPosReportAccStateDiv()
  {
    return pos_report_acc_state_div;
  }


  /** 
   * <em>pos_report_acc_state_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPosReportAccStateDivIsSet() {
    return pos_report_acc_state_div_is_set;
  }


  /** 
   * <em>pos_report_acc_state_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPosReportAccStateDivIsModified() {
    return pos_report_acc_state_div_is_modified;
  }


  /** 
   * <em>equity_tax_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEquityTaxDiv()
  {
    return equity_tax_div;
  }


  /** 
   * <em>equity_tax_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityTaxDivIsSet() {
    return equity_tax_div_is_set;
  }


  /** 
   * <em>equity_tax_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityTaxDivIsModified() {
    return equity_tax_div_is_modified;
  }


  /** 
   * <em>equity_tax_div_next</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEquityTaxDivNext()
  {
    return equity_tax_div_next;
  }


  /** 
   * <em>equity_tax_div_next</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityTaxDivNextIsSet() {
    return equity_tax_div_next_is_set;
  }


  /** 
   * <em>equity_tax_div_next</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityTaxDivNextIsModified() {
    return equity_tax_div_next_is_modified;
  }


  /** 
   * <em>equity_sp_acc_open_dat</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEquitySpAccOpenDat()
  {
    return equity_sp_acc_open_dat;
  }


  /** 
   * <em>equity_sp_acc_open_dat</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquitySpAccOpenDatIsSet() {
    return equity_sp_acc_open_dat_is_set;
  }


  /** 
   * <em>equity_sp_acc_open_dat</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquitySpAccOpenDatIsModified() {
    return equity_sp_acc_open_dat_is_modified;
  }


  /** 
   * <em>margin_tax_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginTaxDiv()
  {
    return margin_tax_div;
  }


  /** 
   * <em>margin_tax_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTaxDivIsSet() {
    return margin_tax_div_is_set;
  }


  /** 
   * <em>margin_tax_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTaxDivIsModified() {
    return margin_tax_div_is_modified;
  }


  /** 
   * <em>margin_tax_div_next</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginTaxDivNext()
  {
    return margin_tax_div_next;
  }


  /** 
   * <em>margin_tax_div_next</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTaxDivNextIsSet() {
    return margin_tax_div_next_is_set;
  }


  /** 
   * <em>margin_tax_div_next</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTaxDivNextIsModified() {
    return margin_tax_div_next_is_modified;
  }


  /** 
   * <em>margin_sp_acc_open_dat</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginSpAccOpenDat()
  {
    return margin_sp_acc_open_dat;
  }


  /** 
   * <em>margin_sp_acc_open_dat</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSpAccOpenDatIsSet() {
    return margin_sp_acc_open_dat_is_set;
  }


  /** 
   * <em>margin_sp_acc_open_dat</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSpAccOpenDatIsModified() {
    return margin_sp_acc_open_dat_is_modified;
  }


  /** 
   * <em>sp_mng_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSpMngAccOpenDiv()
  {
    return sp_mng_acc_open_div;
  }


  /** 
   * <em>sp_mng_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpMngAccOpenDivIsSet() {
    return sp_mng_acc_open_div_is_set;
  }


  /** 
   * <em>sp_mng_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpMngAccOpenDivIsModified() {
    return sp_mng_acc_open_div_is_modified;
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
   * <em>occupation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOccupationDiv()
  {
    return occupation_div;
  }


  /** 
   * <em>occupation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOccupationDivIsSet() {
    return occupation_div_is_set;
  }


  /** 
   * <em>occupation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOccupationDivIsModified() {
    return occupation_div_is_modified;
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
   * <em>mobile_office_info_regist_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMobileOfficeInfoRegistId()
  {
    return ( mobile_office_info_regist_id==null? ((long)0): mobile_office_info_regist_id.longValue() );
  }


  /** 
   * <em>mobile_office_info_regist_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMobileOfficeInfoRegistIdIsNull()
  {
    return mobile_office_info_regist_id == null;
  }


  /** 
   * <em>mobile_office_info_regist_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMobileOfficeInfoRegistIdIsSet() {
    return mobile_office_info_regist_id_is_set;
  }


  /** 
   * <em>mobile_office_info_regist_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMobileOfficeInfoRegistIdIsModified() {
    return mobile_office_info_regist_id_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new TradeConditionVoucherPK(order_request_number, request_code, institution_code, branch_code, account_code);
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
   * <em>data_class</em>カラムの値を設定します。 
   *
   * @@param p_dataClass <em>data_class</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setDataClass( String p_dataClass )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    data_class = p_dataClass;
    data_class_is_set = true;
    data_class_is_modified = true;
  }


  /** 
   * <em>trading_e_report_div</em>カラムの値を設定します。 
   *
   * @@param p_tradingEReportDiv <em>trading_e_report_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTradingEReportDiv( String p_tradingEReportDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_e_report_div = p_tradingEReportDiv;
    trading_e_report_div_is_set = true;
    trading_e_report_div_is_modified = true;
  }


  /** 
   * <em>inv_e_report_div</em>カラムの値を設定します。 
   *
   * @@param p_invEReportDiv <em>inv_e_report_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setInvEReportDiv( String p_invEReportDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    inv_e_report_div = p_invEReportDiv;
    inv_e_report_div_is_set = true;
    inv_e_report_div_is_modified = true;
  }


  /** 
   * <em>refund_e_report_div</em>カラムの値を設定します。 
   *
   * @@param p_refundEReportDiv <em>refund_e_report_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRefundEReportDiv( String p_refundEReportDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    refund_e_report_div = p_refundEReportDiv;
    refund_e_report_div_is_set = true;
    refund_e_report_div_is_modified = true;
  }


  /** 
   * <em>e_report_div1</em>カラムの値を設定します。 
   *
   * @@param p_eReportDiv1 <em>e_report_div1</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEReportDiv1( String p_eReportDiv1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    e_report_div1 = p_eReportDiv1;
    e_report_div1_is_set = true;
    e_report_div1_is_modified = true;
  }


  /** 
   * <em>e_report_div2</em>カラムの値を設定します。 
   *
   * @@param p_eReportDiv2 <em>e_report_div2</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEReportDiv2( String p_eReportDiv2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    e_report_div2 = p_eReportDiv2;
    e_report_div2_is_set = true;
    e_report_div2_is_modified = true;
  }


  /** 
   * <em>e_report_div3</em>カラムの値を設定します。 
   *
   * @@param p_eReportDiv3 <em>e_report_div3</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEReportDiv3( String p_eReportDiv3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    e_report_div3 = p_eReportDiv3;
    e_report_div3_is_set = true;
    e_report_div3_is_modified = true;
  }


  /** 
   * <em>pos_report_term_div</em>カラムの値を設定します。 
   *
   * @@param p_posReportTermDiv <em>pos_report_term_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPosReportTermDiv( String p_posReportTermDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pos_report_term_div = p_posReportTermDiv;
    pos_report_term_div_is_set = true;
    pos_report_term_div_is_modified = true;
  }


  /** 
   * <em>pos_report_cycle_div</em>カラムの値を設定します。 
   *
   * @@param p_posReportCycleDiv <em>pos_report_cycle_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPosReportCycleDiv( String p_posReportCycleDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pos_report_cycle_div = p_posReportCycleDiv;
    pos_report_cycle_div_is_set = true;
    pos_report_cycle_div_is_modified = true;
  }


  /** 
   * <em>pos_report_certif_depo_div</em>カラムの値を設定します。 
   *
   * @@param p_posReportCertifDepoDiv <em>pos_report_certif_depo_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPosReportCertifDepoDiv( String p_posReportCertifDepoDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pos_report_certif_depo_div = p_posReportCertifDepoDiv;
    pos_report_certif_depo_div_is_set = true;
    pos_report_certif_depo_div_is_modified = true;
  }


  /** 
   * <em>pos_report_acc_state_div</em>カラムの値を設定します。 
   *
   * @@param p_posReportAccStateDiv <em>pos_report_acc_state_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPosReportAccStateDiv( String p_posReportAccStateDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pos_report_acc_state_div = p_posReportAccStateDiv;
    pos_report_acc_state_div_is_set = true;
    pos_report_acc_state_div_is_modified = true;
  }


  /** 
   * <em>equity_tax_div</em>カラムの値を設定します。 
   *
   * @@param p_equityTaxDiv <em>equity_tax_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEquityTaxDiv( String p_equityTaxDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_tax_div = p_equityTaxDiv;
    equity_tax_div_is_set = true;
    equity_tax_div_is_modified = true;
  }


  /** 
   * <em>equity_tax_div_next</em>カラムの値を設定します。 
   *
   * @@param p_equityTaxDivNext <em>equity_tax_div_next</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEquityTaxDivNext( String p_equityTaxDivNext )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_tax_div_next = p_equityTaxDivNext;
    equity_tax_div_next_is_set = true;
    equity_tax_div_next_is_modified = true;
  }


  /** 
   * <em>equity_sp_acc_open_dat</em>カラムの値を設定します。 
   *
   * @@param p_equitySpAccOpenDat <em>equity_sp_acc_open_dat</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setEquitySpAccOpenDat( String p_equitySpAccOpenDat )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_sp_acc_open_dat = p_equitySpAccOpenDat;
    equity_sp_acc_open_dat_is_set = true;
    equity_sp_acc_open_dat_is_modified = true;
  }


  /** 
   * <em>margin_tax_div</em>カラムの値を設定します。 
   *
   * @@param p_marginTaxDiv <em>margin_tax_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarginTaxDiv( String p_marginTaxDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_tax_div = p_marginTaxDiv;
    margin_tax_div_is_set = true;
    margin_tax_div_is_modified = true;
  }


  /** 
   * <em>margin_tax_div_next</em>カラムの値を設定します。 
   *
   * @@param p_marginTaxDivNext <em>margin_tax_div_next</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarginTaxDivNext( String p_marginTaxDivNext )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_tax_div_next = p_marginTaxDivNext;
    margin_tax_div_next_is_set = true;
    margin_tax_div_next_is_modified = true;
  }


  /** 
   * <em>margin_sp_acc_open_dat</em>カラムの値を設定します。 
   *
   * @@param p_marginSpAccOpenDat <em>margin_sp_acc_open_dat</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setMarginSpAccOpenDat( String p_marginSpAccOpenDat )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sp_acc_open_dat = p_marginSpAccOpenDat;
    margin_sp_acc_open_dat_is_set = true;
    margin_sp_acc_open_dat_is_modified = true;
  }


  /** 
   * <em>sp_mng_acc_open_div</em>カラムの値を設定します。 
   *
   * @@param p_spMngAccOpenDiv <em>sp_mng_acc_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSpMngAccOpenDiv( String p_spMngAccOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sp_mng_acc_open_div = p_spMngAccOpenDiv;
    sp_mng_acc_open_div_is_set = true;
    sp_mng_acc_open_div_is_modified = true;
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
   * <em>occupation_div</em>カラムの値を設定します。 
   *
   * @@param p_occupationDiv <em>occupation_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setOccupationDiv( String p_occupationDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    occupation_div = p_occupationDiv;
    occupation_div_is_set = true;
    occupation_div_is_modified = true;
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
   * <em>mobile_office_info_regist_id</em>カラムの値を設定します。 
   *
   * @@param p_mobileOfficeInfoRegistId <em>mobile_office_info_regist_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setMobileOfficeInfoRegistId( long p_mobileOfficeInfoRegistId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mobile_office_info_regist_id = new Long(p_mobileOfficeInfoRegistId);
    mobile_office_info_regist_id_is_set = true;
    mobile_office_info_regist_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>mobile_office_info_regist_id</em>カラムに値を設定します。 
   */
  public final void setMobileOfficeInfoRegistId( Long p_mobileOfficeInfoRegistId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mobile_office_info_regist_id = p_mobileOfficeInfoRegistId;
    mobile_office_info_regist_id_is_set = true;
    mobile_office_info_regist_id_is_modified = true;
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
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("data_class") ) {
                    return this.data_class;
                }
                break;
            case 'e':
                if ( name.equals("e_report_div1") ) {
                    return this.e_report_div1;
                }
                else if ( name.equals("e_report_div2") ) {
                    return this.e_report_div2;
                }
                else if ( name.equals("e_report_div3") ) {
                    return this.e_report_div3;
                }
                else if ( name.equals("equity_tax_div") ) {
                    return this.equity_tax_div;
                }
                else if ( name.equals("equity_tax_div_next") ) {
                    return this.equity_tax_div_next;
                }
                else if ( name.equals("equity_sp_acc_open_dat") ) {
                    return this.equity_sp_acc_open_dat;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("inv_e_report_div") ) {
                    return this.inv_e_report_div;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("margin_tax_div") ) {
                    return this.margin_tax_div;
                }
                else if ( name.equals("margin_tax_div_next") ) {
                    return this.margin_tax_div_next;
                }
                else if ( name.equals("margin_sp_acc_open_dat") ) {
                    return this.margin_sp_acc_open_dat;
                }
                else if ( name.equals("mobile_office_info_regist_id") ) {
                    return this.mobile_office_info_regist_id;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("occupation_div") ) {
                    return this.occupation_div;
                }
                break;
            case 'p':
                if ( name.equals("pos_report_term_div") ) {
                    return this.pos_report_term_div;
                }
                else if ( name.equals("pos_report_cycle_div") ) {
                    return this.pos_report_cycle_div;
                }
                else if ( name.equals("pos_report_certif_depo_div") ) {
                    return this.pos_report_certif_depo_div;
                }
                else if ( name.equals("pos_report_acc_state_div") ) {
                    return this.pos_report_acc_state_div;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("refund_e_report_div") ) {
                    return this.refund_e_report_div;
                }
                else if ( name.equals("regist_div") ) {
                    return this.regist_div;
                }
                break;
            case 's':
                if ( name.equals("serial_no") ) {
                    return this.serial_no;
                }
                else if ( name.equals("sp_mng_acc_open_div") ) {
                    return this.sp_mng_acc_open_div;
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
                else if ( name.equals("trading_e_report_div") ) {
                    return this.trading_e_report_div;
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
                if ( name.equals("data_class") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'data_class' must be String: '"+value+"' is not." );
                    this.data_class = (String) value;
                    if (this.data_class_is_set)
                        this.data_class_is_modified = true;
                    this.data_class_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("e_report_div1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'e_report_div1' must be String: '"+value+"' is not." );
                    this.e_report_div1 = (String) value;
                    if (this.e_report_div1_is_set)
                        this.e_report_div1_is_modified = true;
                    this.e_report_div1_is_set = true;
                    return;
                }
                else if ( name.equals("e_report_div2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'e_report_div2' must be String: '"+value+"' is not." );
                    this.e_report_div2 = (String) value;
                    if (this.e_report_div2_is_set)
                        this.e_report_div2_is_modified = true;
                    this.e_report_div2_is_set = true;
                    return;
                }
                else if ( name.equals("e_report_div3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'e_report_div3' must be String: '"+value+"' is not." );
                    this.e_report_div3 = (String) value;
                    if (this.e_report_div3_is_set)
                        this.e_report_div3_is_modified = true;
                    this.e_report_div3_is_set = true;
                    return;
                }
                else if ( name.equals("equity_tax_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'equity_tax_div' must be String: '"+value+"' is not." );
                    this.equity_tax_div = (String) value;
                    if (this.equity_tax_div_is_set)
                        this.equity_tax_div_is_modified = true;
                    this.equity_tax_div_is_set = true;
                    return;
                }
                else if ( name.equals("equity_tax_div_next") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'equity_tax_div_next' must be String: '"+value+"' is not." );
                    this.equity_tax_div_next = (String) value;
                    if (this.equity_tax_div_next_is_set)
                        this.equity_tax_div_next_is_modified = true;
                    this.equity_tax_div_next_is_set = true;
                    return;
                }
                else if ( name.equals("equity_sp_acc_open_dat") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'equity_sp_acc_open_dat' must be String: '"+value+"' is not." );
                    this.equity_sp_acc_open_dat = (String) value;
                    if (this.equity_sp_acc_open_dat_is_set)
                        this.equity_sp_acc_open_dat_is_modified = true;
                    this.equity_sp_acc_open_dat_is_set = true;
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
                else if ( name.equals("inv_e_report_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'inv_e_report_div' must be String: '"+value+"' is not." );
                    this.inv_e_report_div = (String) value;
                    if (this.inv_e_report_div_is_set)
                        this.inv_e_report_div_is_modified = true;
                    this.inv_e_report_div_is_set = true;
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
                if ( name.equals("margin_tax_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_tax_div' must be String: '"+value+"' is not." );
                    this.margin_tax_div = (String) value;
                    if (this.margin_tax_div_is_set)
                        this.margin_tax_div_is_modified = true;
                    this.margin_tax_div_is_set = true;
                    return;
                }
                else if ( name.equals("margin_tax_div_next") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_tax_div_next' must be String: '"+value+"' is not." );
                    this.margin_tax_div_next = (String) value;
                    if (this.margin_tax_div_next_is_set)
                        this.margin_tax_div_next_is_modified = true;
                    this.margin_tax_div_next_is_set = true;
                    return;
                }
                else if ( name.equals("margin_sp_acc_open_dat") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_sp_acc_open_dat' must be String: '"+value+"' is not." );
                    this.margin_sp_acc_open_dat = (String) value;
                    if (this.margin_sp_acc_open_dat_is_set)
                        this.margin_sp_acc_open_dat_is_modified = true;
                    this.margin_sp_acc_open_dat_is_set = true;
                    return;
                }
                else if ( name.equals("mobile_office_info_regist_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'mobile_office_info_regist_id' must be Long: '"+value+"' is not." );
                    this.mobile_office_info_regist_id = (Long) value;
                    if (this.mobile_office_info_regist_id_is_set)
                        this.mobile_office_info_regist_id_is_modified = true;
                    this.mobile_office_info_regist_id_is_set = true;
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
                else if ( name.equals("occupation_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'occupation_div' must be String: '"+value+"' is not." );
                    this.occupation_div = (String) value;
                    if (this.occupation_div_is_set)
                        this.occupation_div_is_modified = true;
                    this.occupation_div_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("pos_report_term_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pos_report_term_div' must be String: '"+value+"' is not." );
                    this.pos_report_term_div = (String) value;
                    if (this.pos_report_term_div_is_set)
                        this.pos_report_term_div_is_modified = true;
                    this.pos_report_term_div_is_set = true;
                    return;
                }
                else if ( name.equals("pos_report_cycle_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pos_report_cycle_div' must be String: '"+value+"' is not." );
                    this.pos_report_cycle_div = (String) value;
                    if (this.pos_report_cycle_div_is_set)
                        this.pos_report_cycle_div_is_modified = true;
                    this.pos_report_cycle_div_is_set = true;
                    return;
                }
                else if ( name.equals("pos_report_certif_depo_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pos_report_certif_depo_div' must be String: '"+value+"' is not." );
                    this.pos_report_certif_depo_div = (String) value;
                    if (this.pos_report_certif_depo_div_is_set)
                        this.pos_report_certif_depo_div_is_modified = true;
                    this.pos_report_certif_depo_div_is_set = true;
                    return;
                }
                else if ( name.equals("pos_report_acc_state_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pos_report_acc_state_div' must be String: '"+value+"' is not." );
                    this.pos_report_acc_state_div = (String) value;
                    if (this.pos_report_acc_state_div_is_set)
                        this.pos_report_acc_state_div_is_modified = true;
                    this.pos_report_acc_state_div_is_set = true;
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
                else if ( name.equals("refund_e_report_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'refund_e_report_div' must be String: '"+value+"' is not." );
                    this.refund_e_report_div = (String) value;
                    if (this.refund_e_report_div_is_set)
                        this.refund_e_report_div_is_modified = true;
                    this.refund_e_report_div_is_set = true;
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
                else if ( name.equals("sp_mng_acc_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sp_mng_acc_open_div' must be String: '"+value+"' is not." );
                    this.sp_mng_acc_open_div = (String) value;
                    if (this.sp_mng_acc_open_div_is_set)
                        this.sp_mng_acc_open_div_is_modified = true;
                    this.sp_mng_acc_open_div_is_set = true;
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
                else if ( name.equals("trading_e_report_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trading_e_report_div' must be String: '"+value+"' is not." );
                    this.trading_e_report_div = (String) value;
                    if (this.trading_e_report_div_is_set)
                        this.trading_e_report_div_is_modified = true;
                    this.trading_e_report_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
