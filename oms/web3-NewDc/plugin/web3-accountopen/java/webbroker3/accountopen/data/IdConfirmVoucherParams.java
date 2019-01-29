head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.19.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	IdConfirmVoucherParams.java;


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
 * id_confirm_voucherテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link IdConfirmVoucherRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link IdConfirmVoucherParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link IdConfirmVoucherParams}が{@@link IdConfirmVoucherRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IdConfirmVoucherPK 
 * @@see IdConfirmVoucherRow 
 */
public class IdConfirmVoucherParams extends Params implements IdConfirmVoucherRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "id_confirm_voucher";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = IdConfirmVoucherRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return IdConfirmVoucherRow.TYPE;
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
   * <em>regist_div</em>カラムの値 
   */
  public  String  regist_div;

  /** 
   * <em>data_class</em>カラムの値 
   */
  public  String  data_class;

  /** 
   * <em>id_attribute_div</em>カラムの値 
   */
  public  String  id_attribute_div;

  /** 
   * <em>trading_div</em>カラムの値 
   */
  public  String  trading_div;

  /** 
   * <em>confirm_way_div</em>カラムの値 
   */
  public  String  confirm_way_div;

  /** 
   * <em>confirm_doc_div</em>カラムの値 
   */
  public  String  confirm_doc_div;

  /** 
   * <em>address_confirm_doc</em>カラムの値 
   */
  public  String  address_confirm_doc;

  /** 
   * <em>confirm_date</em>カラムの値 
   */
  public  java.sql.Timestamp  confirm_date;

  /** 
   * <em>appli_motivat_div</em>カラムの値 
   */
  public  String  appli_motivat_div;

  /** 
   * <em>invest_purpose_div</em>カラムの値 
   */
  public  String  invest_purpose_div;

  /** 
   * <em>experience_equity</em>カラムの値 
   */
  public  String  experience_equity;

  /** 
   * <em>experience_margin</em>カラムの値 
   */
  public  String  experience_margin;

  /** 
   * <em>experience_bond</em>カラムの値 
   */
  public  String  experience_bond;

  /** 
   * <em>experience_fund</em>カラムの値 
   */
  public  String  experience_fund;

  /** 
   * <em>experience_fo</em>カラムの値 
   */
  public  String  experience_fo;

  /** 
   * <em>experience_f_equity</em>カラムの値 
   */
  public  String  experience_f_equity;

  /** 
   * <em>experience_etc</em>カラムの値 
   */
  public  String  experience_etc;

  /** 
   * <em>experience_from</em>カラムの値 
   */
  public  String  experience_from;

  /** 
   * <em>experience_to</em>カラムの値 
   */
  public  String  experience_to;

  /** 
   * <em>equity_trade_div</em>カラムの値 
   */
  public  String  equity_trade_div;

  /** 
   * <em>margin_trade_div</em>カラムの値 
   */
  public  String  margin_trade_div;

  /** 
   * <em>bond_trade_div</em>カラムの値 
   */
  public  String  bond_trade_div;

  /** 
   * <em>fund_trade_div</em>カラムの値 
   */
  public  String  fund_trade_div;

  /** 
   * <em>fo_trade_div</em>カラムの値 
   */
  public  String  fo_trade_div;

  /** 
   * <em>f_equity_trade_div</em>カラムの値 
   */
  public  String  f_equity_trade_div;

  /** 
   * <em>etc_trade_div</em>カラムの値 
   */
  public  String  etc_trade_div;

  /** 
   * <em>annual_income_from</em>カラムの値 
   */
  public  String  annual_income_from;

  /** 
   * <em>annual_income_to</em>カラムの値 
   */
  public  String  annual_income_to;

  /** 
   * <em>asset_value_from</em>カラムの値 
   */
  public  String  asset_value_from;

  /** 
   * <em>asset_value_to</em>カラムの値 
   */
  public  String  asset_value_to;

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
   * <em>div</em>カラムの値 
   */
  public  String  div;

  /** 
   * <em>relation</em>カラムの値 
   */
  public  String  relation;

  /** 
   * <em>confirm_way</em>カラムの値 
   */
  public  String  confirm_way;

  /** 
   * <em>confirm_doc</em>カラムの値 
   */
  public  String  confirm_doc;

  /** 
   * <em>add_confirm_doc</em>カラムの値 
   */
  public  String  add_confirm_doc;

  /** 
   * <em>charge_confirm_date</em>カラムの値 
   */
  public  java.sql.Timestamp  charge_confirm_date;

  /** 
   * <em>charge_name</em>カラムの値 
   */
  public  String  charge_name;

  /** 
   * <em>born_date</em>カラムの値 
   */
  public  java.sql.Timestamp  born_date;

  /** 
   * <em>telephone1</em>カラムの値 
   */
  public  String  telephone1;

  /** 
   * <em>telephone2</em>カラムの値 
   */
  public  String  telephone2;

  /** 
   * <em>telephone3</em>カラムの値 
   */
  public  String  telephone3;

  /** 
   * <em>zip_code1</em>カラムの値 
   */
  public  String  zip_code1;

  /** 
   * <em>zip_code2</em>カラムの値 
   */
  public  String  zip_code2;

  /** 
   * <em>address_line1</em>カラムの値 
   */
  public  String  address_line1;

  /** 
   * <em>address_line2</em>カラムの値 
   */
  public  String  address_line2;

  /** 
   * <em>address_line3</em>カラムの値 
   */
  public  String  address_line3;

  /** 
   * <em>address_line1_kana</em>カラムの値 
   */
  public  String  address_line1_kana;

  /** 
   * <em>address_line2_kana</em>カラムの値 
   */
  public  String  address_line2_kana;

  /** 
   * <em>address_line3_kana</em>カラムの値 
   */
  public  String  address_line3_kana;

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
  private boolean regist_div_is_set = false;
  private boolean regist_div_is_modified = false;
  private boolean data_class_is_set = false;
  private boolean data_class_is_modified = false;
  private boolean id_attribute_div_is_set = false;
  private boolean id_attribute_div_is_modified = false;
  private boolean trading_div_is_set = false;
  private boolean trading_div_is_modified = false;
  private boolean confirm_way_div_is_set = false;
  private boolean confirm_way_div_is_modified = false;
  private boolean confirm_doc_div_is_set = false;
  private boolean confirm_doc_div_is_modified = false;
  private boolean address_confirm_doc_is_set = false;
  private boolean address_confirm_doc_is_modified = false;
  private boolean confirm_date_is_set = false;
  private boolean confirm_date_is_modified = false;
  private boolean appli_motivat_div_is_set = false;
  private boolean appli_motivat_div_is_modified = false;
  private boolean invest_purpose_div_is_set = false;
  private boolean invest_purpose_div_is_modified = false;
  private boolean experience_equity_is_set = false;
  private boolean experience_equity_is_modified = false;
  private boolean experience_margin_is_set = false;
  private boolean experience_margin_is_modified = false;
  private boolean experience_bond_is_set = false;
  private boolean experience_bond_is_modified = false;
  private boolean experience_fund_is_set = false;
  private boolean experience_fund_is_modified = false;
  private boolean experience_fo_is_set = false;
  private boolean experience_fo_is_modified = false;
  private boolean experience_f_equity_is_set = false;
  private boolean experience_f_equity_is_modified = false;
  private boolean experience_etc_is_set = false;
  private boolean experience_etc_is_modified = false;
  private boolean experience_from_is_set = false;
  private boolean experience_from_is_modified = false;
  private boolean experience_to_is_set = false;
  private boolean experience_to_is_modified = false;
  private boolean equity_trade_div_is_set = false;
  private boolean equity_trade_div_is_modified = false;
  private boolean margin_trade_div_is_set = false;
  private boolean margin_trade_div_is_modified = false;
  private boolean bond_trade_div_is_set = false;
  private boolean bond_trade_div_is_modified = false;
  private boolean fund_trade_div_is_set = false;
  private boolean fund_trade_div_is_modified = false;
  private boolean fo_trade_div_is_set = false;
  private boolean fo_trade_div_is_modified = false;
  private boolean f_equity_trade_div_is_set = false;
  private boolean f_equity_trade_div_is_modified = false;
  private boolean etc_trade_div_is_set = false;
  private boolean etc_trade_div_is_modified = false;
  private boolean annual_income_from_is_set = false;
  private boolean annual_income_from_is_modified = false;
  private boolean annual_income_to_is_set = false;
  private boolean annual_income_to_is_modified = false;
  private boolean asset_value_from_is_set = false;
  private boolean asset_value_from_is_modified = false;
  private boolean asset_value_to_is_set = false;
  private boolean asset_value_to_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean send_timestamp_is_set = false;
  private boolean send_timestamp_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean div_is_set = false;
  private boolean div_is_modified = false;
  private boolean relation_is_set = false;
  private boolean relation_is_modified = false;
  private boolean confirm_way_is_set = false;
  private boolean confirm_way_is_modified = false;
  private boolean confirm_doc_is_set = false;
  private boolean confirm_doc_is_modified = false;
  private boolean add_confirm_doc_is_set = false;
  private boolean add_confirm_doc_is_modified = false;
  private boolean charge_confirm_date_is_set = false;
  private boolean charge_confirm_date_is_modified = false;
  private boolean charge_name_is_set = false;
  private boolean charge_name_is_modified = false;
  private boolean born_date_is_set = false;
  private boolean born_date_is_modified = false;
  private boolean telephone1_is_set = false;
  private boolean telephone1_is_modified = false;
  private boolean telephone2_is_set = false;
  private boolean telephone2_is_modified = false;
  private boolean telephone3_is_set = false;
  private boolean telephone3_is_modified = false;
  private boolean zip_code1_is_set = false;
  private boolean zip_code1_is_modified = false;
  private boolean zip_code2_is_set = false;
  private boolean zip_code2_is_modified = false;
  private boolean address_line1_is_set = false;
  private boolean address_line1_is_modified = false;
  private boolean address_line2_is_set = false;
  private boolean address_line2_is_modified = false;
  private boolean address_line3_is_set = false;
  private boolean address_line3_is_modified = false;
  private boolean address_line1_kana_is_set = false;
  private boolean address_line1_kana_is_modified = false;
  private boolean address_line2_kana_is_set = false;
  private boolean address_line2_kana_is_modified = false;
  private boolean address_line3_kana_is_set = false;
  private boolean address_line3_kana_is_modified = false;


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
      + "," + "regist_div=" +regist_div
      + "," + "data_class=" +data_class
      + "," + "id_attribute_div=" +id_attribute_div
      + "," + "trading_div=" +trading_div
      + "," + "confirm_way_div=" +confirm_way_div
      + "," + "confirm_doc_div=" +confirm_doc_div
      + "," + "address_confirm_doc=" +address_confirm_doc
      + "," + "confirm_date=" +confirm_date
      + "," + "appli_motivat_div=" +appli_motivat_div
      + "," + "invest_purpose_div=" +invest_purpose_div
      + "," + "experience_equity=" +experience_equity
      + "," + "experience_margin=" +experience_margin
      + "," + "experience_bond=" +experience_bond
      + "," + "experience_fund=" +experience_fund
      + "," + "experience_fo=" +experience_fo
      + "," + "experience_f_equity=" +experience_f_equity
      + "," + "experience_etc=" +experience_etc
      + "," + "experience_from=" +experience_from
      + "," + "experience_to=" +experience_to
      + "," + "equity_trade_div=" +equity_trade_div
      + "," + "margin_trade_div=" +margin_trade_div
      + "," + "bond_trade_div=" +bond_trade_div
      + "," + "fund_trade_div=" +fund_trade_div
      + "," + "fo_trade_div=" +fo_trade_div
      + "," + "f_equity_trade_div=" +f_equity_trade_div
      + "," + "etc_trade_div=" +etc_trade_div
      + "," + "annual_income_from=" +annual_income_from
      + "," + "annual_income_to=" +annual_income_to
      + "," + "asset_value_from=" +asset_value_from
      + "," + "asset_value_to=" +asset_value_to
      + "," + "status=" +status
      + "," + "send_timestamp=" +send_timestamp
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "div=" +div
      + "," + "relation=" +relation
      + "," + "confirm_way=" +confirm_way
      + "," + "confirm_doc=" +confirm_doc
      + "," + "add_confirm_doc=" +add_confirm_doc
      + "," + "charge_confirm_date=" +charge_confirm_date
      + "," + "charge_name=" +charge_name
      + "," + "born_date=" +born_date
      + "," + "telephone1=" +telephone1
      + "," + "telephone2=" +telephone2
      + "," + "telephone3=" +telephone3
      + "," + "zip_code1=" +zip_code1
      + "," + "zip_code2=" +zip_code2
      + "," + "address_line1=" +address_line1
      + "," + "address_line2=" +address_line2
      + "," + "address_line3=" +address_line3
      + "," + "address_line1_kana=" +address_line1_kana
      + "," + "address_line2_kana=" +address_line2_kana
      + "," + "address_line3_kana=" +address_line3_kana
      + "}";
  }


  /** 
   * 値が未設定のIdConfirmVoucherParamsオブジェクトを作成します。 
   */
  public IdConfirmVoucherParams() {
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
   * 指定のIdConfirmVoucherRowオブジェクトの値を利用してIdConfirmVoucherParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するIdConfirmVoucherRowオブジェクト 
   */
  public IdConfirmVoucherParams( IdConfirmVoucherRow p_row )
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
    regist_div = p_row.getRegistDiv();
    regist_div_is_set = p_row.getRegistDivIsSet();
    regist_div_is_modified = p_row.getRegistDivIsModified();
    data_class = p_row.getDataClass();
    data_class_is_set = p_row.getDataClassIsSet();
    data_class_is_modified = p_row.getDataClassIsModified();
    id_attribute_div = p_row.getIdAttributeDiv();
    id_attribute_div_is_set = p_row.getIdAttributeDivIsSet();
    id_attribute_div_is_modified = p_row.getIdAttributeDivIsModified();
    trading_div = p_row.getTradingDiv();
    trading_div_is_set = p_row.getTradingDivIsSet();
    trading_div_is_modified = p_row.getTradingDivIsModified();
    confirm_way_div = p_row.getConfirmWayDiv();
    confirm_way_div_is_set = p_row.getConfirmWayDivIsSet();
    confirm_way_div_is_modified = p_row.getConfirmWayDivIsModified();
    confirm_doc_div = p_row.getConfirmDocDiv();
    confirm_doc_div_is_set = p_row.getConfirmDocDivIsSet();
    confirm_doc_div_is_modified = p_row.getConfirmDocDivIsModified();
    address_confirm_doc = p_row.getAddressConfirmDoc();
    address_confirm_doc_is_set = p_row.getAddressConfirmDocIsSet();
    address_confirm_doc_is_modified = p_row.getAddressConfirmDocIsModified();
    confirm_date = p_row.getConfirmDate();
    confirm_date_is_set = p_row.getConfirmDateIsSet();
    confirm_date_is_modified = p_row.getConfirmDateIsModified();
    appli_motivat_div = p_row.getAppliMotivatDiv();
    appli_motivat_div_is_set = p_row.getAppliMotivatDivIsSet();
    appli_motivat_div_is_modified = p_row.getAppliMotivatDivIsModified();
    invest_purpose_div = p_row.getInvestPurposeDiv();
    invest_purpose_div_is_set = p_row.getInvestPurposeDivIsSet();
    invest_purpose_div_is_modified = p_row.getInvestPurposeDivIsModified();
    experience_equity = p_row.getExperienceEquity();
    experience_equity_is_set = p_row.getExperienceEquityIsSet();
    experience_equity_is_modified = p_row.getExperienceEquityIsModified();
    experience_margin = p_row.getExperienceMargin();
    experience_margin_is_set = p_row.getExperienceMarginIsSet();
    experience_margin_is_modified = p_row.getExperienceMarginIsModified();
    experience_bond = p_row.getExperienceBond();
    experience_bond_is_set = p_row.getExperienceBondIsSet();
    experience_bond_is_modified = p_row.getExperienceBondIsModified();
    experience_fund = p_row.getExperienceFund();
    experience_fund_is_set = p_row.getExperienceFundIsSet();
    experience_fund_is_modified = p_row.getExperienceFundIsModified();
    experience_fo = p_row.getExperienceFo();
    experience_fo_is_set = p_row.getExperienceFoIsSet();
    experience_fo_is_modified = p_row.getExperienceFoIsModified();
    experience_f_equity = p_row.getExperienceFEquity();
    experience_f_equity_is_set = p_row.getExperienceFEquityIsSet();
    experience_f_equity_is_modified = p_row.getExperienceFEquityIsModified();
    experience_etc = p_row.getExperienceEtc();
    experience_etc_is_set = p_row.getExperienceEtcIsSet();
    experience_etc_is_modified = p_row.getExperienceEtcIsModified();
    experience_from = p_row.getExperienceFrom();
    experience_from_is_set = p_row.getExperienceFromIsSet();
    experience_from_is_modified = p_row.getExperienceFromIsModified();
    experience_to = p_row.getExperienceTo();
    experience_to_is_set = p_row.getExperienceToIsSet();
    experience_to_is_modified = p_row.getExperienceToIsModified();
    equity_trade_div = p_row.getEquityTradeDiv();
    equity_trade_div_is_set = p_row.getEquityTradeDivIsSet();
    equity_trade_div_is_modified = p_row.getEquityTradeDivIsModified();
    margin_trade_div = p_row.getMarginTradeDiv();
    margin_trade_div_is_set = p_row.getMarginTradeDivIsSet();
    margin_trade_div_is_modified = p_row.getMarginTradeDivIsModified();
    bond_trade_div = p_row.getBondTradeDiv();
    bond_trade_div_is_set = p_row.getBondTradeDivIsSet();
    bond_trade_div_is_modified = p_row.getBondTradeDivIsModified();
    fund_trade_div = p_row.getFundTradeDiv();
    fund_trade_div_is_set = p_row.getFundTradeDivIsSet();
    fund_trade_div_is_modified = p_row.getFundTradeDivIsModified();
    fo_trade_div = p_row.getFoTradeDiv();
    fo_trade_div_is_set = p_row.getFoTradeDivIsSet();
    fo_trade_div_is_modified = p_row.getFoTradeDivIsModified();
    f_equity_trade_div = p_row.getFEquityTradeDiv();
    f_equity_trade_div_is_set = p_row.getFEquityTradeDivIsSet();
    f_equity_trade_div_is_modified = p_row.getFEquityTradeDivIsModified();
    etc_trade_div = p_row.getEtcTradeDiv();
    etc_trade_div_is_set = p_row.getEtcTradeDivIsSet();
    etc_trade_div_is_modified = p_row.getEtcTradeDivIsModified();
    annual_income_from = p_row.getAnnualIncomeFrom();
    annual_income_from_is_set = p_row.getAnnualIncomeFromIsSet();
    annual_income_from_is_modified = p_row.getAnnualIncomeFromIsModified();
    annual_income_to = p_row.getAnnualIncomeTo();
    annual_income_to_is_set = p_row.getAnnualIncomeToIsSet();
    annual_income_to_is_modified = p_row.getAnnualIncomeToIsModified();
    asset_value_from = p_row.getAssetValueFrom();
    asset_value_from_is_set = p_row.getAssetValueFromIsSet();
    asset_value_from_is_modified = p_row.getAssetValueFromIsModified();
    asset_value_to = p_row.getAssetValueTo();
    asset_value_to_is_set = p_row.getAssetValueToIsSet();
    asset_value_to_is_modified = p_row.getAssetValueToIsModified();
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
    div = p_row.getDiv();
    div_is_set = p_row.getDivIsSet();
    div_is_modified = p_row.getDivIsModified();
    relation = p_row.getRelation();
    relation_is_set = p_row.getRelationIsSet();
    relation_is_modified = p_row.getRelationIsModified();
    confirm_way = p_row.getConfirmWay();
    confirm_way_is_set = p_row.getConfirmWayIsSet();
    confirm_way_is_modified = p_row.getConfirmWayIsModified();
    confirm_doc = p_row.getConfirmDoc();
    confirm_doc_is_set = p_row.getConfirmDocIsSet();
    confirm_doc_is_modified = p_row.getConfirmDocIsModified();
    add_confirm_doc = p_row.getAddConfirmDoc();
    add_confirm_doc_is_set = p_row.getAddConfirmDocIsSet();
    add_confirm_doc_is_modified = p_row.getAddConfirmDocIsModified();
    charge_confirm_date = p_row.getChargeConfirmDate();
    charge_confirm_date_is_set = p_row.getChargeConfirmDateIsSet();
    charge_confirm_date_is_modified = p_row.getChargeConfirmDateIsModified();
    charge_name = p_row.getChargeName();
    charge_name_is_set = p_row.getChargeNameIsSet();
    charge_name_is_modified = p_row.getChargeNameIsModified();
    born_date = p_row.getBornDate();
    born_date_is_set = p_row.getBornDateIsSet();
    born_date_is_modified = p_row.getBornDateIsModified();
    telephone1 = p_row.getTelephone1();
    telephone1_is_set = p_row.getTelephone1IsSet();
    telephone1_is_modified = p_row.getTelephone1IsModified();
    telephone2 = p_row.getTelephone2();
    telephone2_is_set = p_row.getTelephone2IsSet();
    telephone2_is_modified = p_row.getTelephone2IsModified();
    telephone3 = p_row.getTelephone3();
    telephone3_is_set = p_row.getTelephone3IsSet();
    telephone3_is_modified = p_row.getTelephone3IsModified();
    zip_code1 = p_row.getZipCode1();
    zip_code1_is_set = p_row.getZipCode1IsSet();
    zip_code1_is_modified = p_row.getZipCode1IsModified();
    zip_code2 = p_row.getZipCode2();
    zip_code2_is_set = p_row.getZipCode2IsSet();
    zip_code2_is_modified = p_row.getZipCode2IsModified();
    address_line1 = p_row.getAddressLine1();
    address_line1_is_set = p_row.getAddressLine1IsSet();
    address_line1_is_modified = p_row.getAddressLine1IsModified();
    address_line2 = p_row.getAddressLine2();
    address_line2_is_set = p_row.getAddressLine2IsSet();
    address_line2_is_modified = p_row.getAddressLine2IsModified();
    address_line3 = p_row.getAddressLine3();
    address_line3_is_set = p_row.getAddressLine3IsSet();
    address_line3_is_modified = p_row.getAddressLine3IsModified();
    address_line1_kana = p_row.getAddressLine1Kana();
    address_line1_kana_is_set = p_row.getAddressLine1KanaIsSet();
    address_line1_kana_is_modified = p_row.getAddressLine1KanaIsModified();
    address_line2_kana = p_row.getAddressLine2Kana();
    address_line2_kana_is_set = p_row.getAddressLine2KanaIsSet();
    address_line2_kana_is_modified = p_row.getAddressLine2KanaIsModified();
    address_line3_kana = p_row.getAddressLine3Kana();
    address_line3_kana_is_set = p_row.getAddressLine3KanaIsSet();
    address_line3_kana_is_modified = p_row.getAddressLine3KanaIsModified();
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
      regist_div_is_set = true;
      regist_div_is_modified = true;
      data_class_is_set = true;
      data_class_is_modified = true;
      id_attribute_div_is_set = true;
      id_attribute_div_is_modified = true;
      trading_div_is_set = true;
      trading_div_is_modified = true;
      confirm_way_div_is_set = true;
      confirm_way_div_is_modified = true;
      confirm_doc_div_is_set = true;
      confirm_doc_div_is_modified = true;
      address_confirm_doc_is_set = true;
      address_confirm_doc_is_modified = true;
      confirm_date_is_set = true;
      confirm_date_is_modified = true;
      appli_motivat_div_is_set = true;
      appli_motivat_div_is_modified = true;
      invest_purpose_div_is_set = true;
      invest_purpose_div_is_modified = true;
      experience_equity_is_set = true;
      experience_equity_is_modified = true;
      experience_margin_is_set = true;
      experience_margin_is_modified = true;
      experience_bond_is_set = true;
      experience_bond_is_modified = true;
      experience_fund_is_set = true;
      experience_fund_is_modified = true;
      experience_fo_is_set = true;
      experience_fo_is_modified = true;
      experience_f_equity_is_set = true;
      experience_f_equity_is_modified = true;
      experience_etc_is_set = true;
      experience_etc_is_modified = true;
      experience_from_is_set = true;
      experience_from_is_modified = true;
      experience_to_is_set = true;
      experience_to_is_modified = true;
      equity_trade_div_is_set = true;
      equity_trade_div_is_modified = true;
      margin_trade_div_is_set = true;
      margin_trade_div_is_modified = true;
      bond_trade_div_is_set = true;
      bond_trade_div_is_modified = true;
      fund_trade_div_is_set = true;
      fund_trade_div_is_modified = true;
      fo_trade_div_is_set = true;
      fo_trade_div_is_modified = true;
      f_equity_trade_div_is_set = true;
      f_equity_trade_div_is_modified = true;
      etc_trade_div_is_set = true;
      etc_trade_div_is_modified = true;
      annual_income_from_is_set = true;
      annual_income_from_is_modified = true;
      annual_income_to_is_set = true;
      annual_income_to_is_modified = true;
      asset_value_from_is_set = true;
      asset_value_from_is_modified = true;
      asset_value_to_is_set = true;
      asset_value_to_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      send_timestamp_is_set = true;
      send_timestamp_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      div_is_set = true;
      div_is_modified = true;
      relation_is_set = true;
      relation_is_modified = true;
      confirm_way_is_set = true;
      confirm_way_is_modified = true;
      confirm_doc_is_set = true;
      confirm_doc_is_modified = true;
      add_confirm_doc_is_set = true;
      add_confirm_doc_is_modified = true;
      charge_confirm_date_is_set = true;
      charge_confirm_date_is_modified = true;
      charge_name_is_set = true;
      charge_name_is_modified = true;
      born_date_is_set = true;
      born_date_is_modified = true;
      telephone1_is_set = true;
      telephone1_is_modified = true;
      telephone2_is_set = true;
      telephone2_is_modified = true;
      telephone3_is_set = true;
      telephone3_is_modified = true;
      zip_code1_is_set = true;
      zip_code1_is_modified = true;
      zip_code2_is_set = true;
      zip_code2_is_modified = true;
      address_line1_is_set = true;
      address_line1_is_modified = true;
      address_line2_is_set = true;
      address_line2_is_modified = true;
      address_line3_is_set = true;
      address_line3_is_modified = true;
      address_line1_kana_is_set = true;
      address_line1_kana_is_modified = true;
      address_line2_kana_is_set = true;
      address_line2_kana_is_modified = true;
      address_line3_kana_is_set = true;
      address_line3_kana_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof IdConfirmVoucherRow ) )
       return false;
    return fieldsEqual( (IdConfirmVoucherRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のIdConfirmVoucherRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( IdConfirmVoucherRow row )
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
    if ( regist_div == null ) {
      if ( row.getRegistDiv() != null )
        return false;
    } else if ( !regist_div.equals( row.getRegistDiv() ) ) {
        return false;
    }
    if ( data_class == null ) {
      if ( row.getDataClass() != null )
        return false;
    } else if ( !data_class.equals( row.getDataClass() ) ) {
        return false;
    }
    if ( id_attribute_div == null ) {
      if ( row.getIdAttributeDiv() != null )
        return false;
    } else if ( !id_attribute_div.equals( row.getIdAttributeDiv() ) ) {
        return false;
    }
    if ( trading_div == null ) {
      if ( row.getTradingDiv() != null )
        return false;
    } else if ( !trading_div.equals( row.getTradingDiv() ) ) {
        return false;
    }
    if ( confirm_way_div == null ) {
      if ( row.getConfirmWayDiv() != null )
        return false;
    } else if ( !confirm_way_div.equals( row.getConfirmWayDiv() ) ) {
        return false;
    }
    if ( confirm_doc_div == null ) {
      if ( row.getConfirmDocDiv() != null )
        return false;
    } else if ( !confirm_doc_div.equals( row.getConfirmDocDiv() ) ) {
        return false;
    }
    if ( address_confirm_doc == null ) {
      if ( row.getAddressConfirmDoc() != null )
        return false;
    } else if ( !address_confirm_doc.equals( row.getAddressConfirmDoc() ) ) {
        return false;
    }
    if ( confirm_date == null ) {
      if ( row.getConfirmDate() != null )
        return false;
    } else if ( !confirm_date.equals( row.getConfirmDate() ) ) {
        return false;
    }
    if ( appli_motivat_div == null ) {
      if ( row.getAppliMotivatDiv() != null )
        return false;
    } else if ( !appli_motivat_div.equals( row.getAppliMotivatDiv() ) ) {
        return false;
    }
    if ( invest_purpose_div == null ) {
      if ( row.getInvestPurposeDiv() != null )
        return false;
    } else if ( !invest_purpose_div.equals( row.getInvestPurposeDiv() ) ) {
        return false;
    }
    if ( experience_equity == null ) {
      if ( row.getExperienceEquity() != null )
        return false;
    } else if ( !experience_equity.equals( row.getExperienceEquity() ) ) {
        return false;
    }
    if ( experience_margin == null ) {
      if ( row.getExperienceMargin() != null )
        return false;
    } else if ( !experience_margin.equals( row.getExperienceMargin() ) ) {
        return false;
    }
    if ( experience_bond == null ) {
      if ( row.getExperienceBond() != null )
        return false;
    } else if ( !experience_bond.equals( row.getExperienceBond() ) ) {
        return false;
    }
    if ( experience_fund == null ) {
      if ( row.getExperienceFund() != null )
        return false;
    } else if ( !experience_fund.equals( row.getExperienceFund() ) ) {
        return false;
    }
    if ( experience_fo == null ) {
      if ( row.getExperienceFo() != null )
        return false;
    } else if ( !experience_fo.equals( row.getExperienceFo() ) ) {
        return false;
    }
    if ( experience_f_equity == null ) {
      if ( row.getExperienceFEquity() != null )
        return false;
    } else if ( !experience_f_equity.equals( row.getExperienceFEquity() ) ) {
        return false;
    }
    if ( experience_etc == null ) {
      if ( row.getExperienceEtc() != null )
        return false;
    } else if ( !experience_etc.equals( row.getExperienceEtc() ) ) {
        return false;
    }
    if ( experience_from == null ) {
      if ( row.getExperienceFrom() != null )
        return false;
    } else if ( !experience_from.equals( row.getExperienceFrom() ) ) {
        return false;
    }
    if ( experience_to == null ) {
      if ( row.getExperienceTo() != null )
        return false;
    } else if ( !experience_to.equals( row.getExperienceTo() ) ) {
        return false;
    }
    if ( equity_trade_div == null ) {
      if ( row.getEquityTradeDiv() != null )
        return false;
    } else if ( !equity_trade_div.equals( row.getEquityTradeDiv() ) ) {
        return false;
    }
    if ( margin_trade_div == null ) {
      if ( row.getMarginTradeDiv() != null )
        return false;
    } else if ( !margin_trade_div.equals( row.getMarginTradeDiv() ) ) {
        return false;
    }
    if ( bond_trade_div == null ) {
      if ( row.getBondTradeDiv() != null )
        return false;
    } else if ( !bond_trade_div.equals( row.getBondTradeDiv() ) ) {
        return false;
    }
    if ( fund_trade_div == null ) {
      if ( row.getFundTradeDiv() != null )
        return false;
    } else if ( !fund_trade_div.equals( row.getFundTradeDiv() ) ) {
        return false;
    }
    if ( fo_trade_div == null ) {
      if ( row.getFoTradeDiv() != null )
        return false;
    } else if ( !fo_trade_div.equals( row.getFoTradeDiv() ) ) {
        return false;
    }
    if ( f_equity_trade_div == null ) {
      if ( row.getFEquityTradeDiv() != null )
        return false;
    } else if ( !f_equity_trade_div.equals( row.getFEquityTradeDiv() ) ) {
        return false;
    }
    if ( etc_trade_div == null ) {
      if ( row.getEtcTradeDiv() != null )
        return false;
    } else if ( !etc_trade_div.equals( row.getEtcTradeDiv() ) ) {
        return false;
    }
    if ( annual_income_from == null ) {
      if ( row.getAnnualIncomeFrom() != null )
        return false;
    } else if ( !annual_income_from.equals( row.getAnnualIncomeFrom() ) ) {
        return false;
    }
    if ( annual_income_to == null ) {
      if ( row.getAnnualIncomeTo() != null )
        return false;
    } else if ( !annual_income_to.equals( row.getAnnualIncomeTo() ) ) {
        return false;
    }
    if ( asset_value_from == null ) {
      if ( row.getAssetValueFrom() != null )
        return false;
    } else if ( !asset_value_from.equals( row.getAssetValueFrom() ) ) {
        return false;
    }
    if ( asset_value_to == null ) {
      if ( row.getAssetValueTo() != null )
        return false;
    } else if ( !asset_value_to.equals( row.getAssetValueTo() ) ) {
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
    if ( div == null ) {
      if ( row.getDiv() != null )
        return false;
    } else if ( !div.equals( row.getDiv() ) ) {
        return false;
    }
    if ( relation == null ) {
      if ( row.getRelation() != null )
        return false;
    } else if ( !relation.equals( row.getRelation() ) ) {
        return false;
    }
    if ( confirm_way == null ) {
      if ( row.getConfirmWay() != null )
        return false;
    } else if ( !confirm_way.equals( row.getConfirmWay() ) ) {
        return false;
    }
    if ( confirm_doc == null ) {
      if ( row.getConfirmDoc() != null )
        return false;
    } else if ( !confirm_doc.equals( row.getConfirmDoc() ) ) {
        return false;
    }
    if ( add_confirm_doc == null ) {
      if ( row.getAddConfirmDoc() != null )
        return false;
    } else if ( !add_confirm_doc.equals( row.getAddConfirmDoc() ) ) {
        return false;
    }
    if ( charge_confirm_date == null ) {
      if ( row.getChargeConfirmDate() != null )
        return false;
    } else if ( !charge_confirm_date.equals( row.getChargeConfirmDate() ) ) {
        return false;
    }
    if ( charge_name == null ) {
      if ( row.getChargeName() != null )
        return false;
    } else if ( !charge_name.equals( row.getChargeName() ) ) {
        return false;
    }
    if ( born_date == null ) {
      if ( row.getBornDate() != null )
        return false;
    } else if ( !born_date.equals( row.getBornDate() ) ) {
        return false;
    }
    if ( telephone1 == null ) {
      if ( row.getTelephone1() != null )
        return false;
    } else if ( !telephone1.equals( row.getTelephone1() ) ) {
        return false;
    }
    if ( telephone2 == null ) {
      if ( row.getTelephone2() != null )
        return false;
    } else if ( !telephone2.equals( row.getTelephone2() ) ) {
        return false;
    }
    if ( telephone3 == null ) {
      if ( row.getTelephone3() != null )
        return false;
    } else if ( !telephone3.equals( row.getTelephone3() ) ) {
        return false;
    }
    if ( zip_code1 == null ) {
      if ( row.getZipCode1() != null )
        return false;
    } else if ( !zip_code1.equals( row.getZipCode1() ) ) {
        return false;
    }
    if ( zip_code2 == null ) {
      if ( row.getZipCode2() != null )
        return false;
    } else if ( !zip_code2.equals( row.getZipCode2() ) ) {
        return false;
    }
    if ( address_line1 == null ) {
      if ( row.getAddressLine1() != null )
        return false;
    } else if ( !address_line1.equals( row.getAddressLine1() ) ) {
        return false;
    }
    if ( address_line2 == null ) {
      if ( row.getAddressLine2() != null )
        return false;
    } else if ( !address_line2.equals( row.getAddressLine2() ) ) {
        return false;
    }
    if ( address_line3 == null ) {
      if ( row.getAddressLine3() != null )
        return false;
    } else if ( !address_line3.equals( row.getAddressLine3() ) ) {
        return false;
    }
    if ( address_line1_kana == null ) {
      if ( row.getAddressLine1Kana() != null )
        return false;
    } else if ( !address_line1_kana.equals( row.getAddressLine1Kana() ) ) {
        return false;
    }
    if ( address_line2_kana == null ) {
      if ( row.getAddressLine2Kana() != null )
        return false;
    } else if ( !address_line2_kana.equals( row.getAddressLine2Kana() ) ) {
        return false;
    }
    if ( address_line3_kana == null ) {
      if ( row.getAddressLine3Kana() != null )
        return false;
    } else if ( !address_line3_kana.equals( row.getAddressLine3Kana() ) ) {
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
        + (regist_div!=null? regist_div.hashCode(): 0) 
        + (data_class!=null? data_class.hashCode(): 0) 
        + (id_attribute_div!=null? id_attribute_div.hashCode(): 0) 
        + (trading_div!=null? trading_div.hashCode(): 0) 
        + (confirm_way_div!=null? confirm_way_div.hashCode(): 0) 
        + (confirm_doc_div!=null? confirm_doc_div.hashCode(): 0) 
        + (address_confirm_doc!=null? address_confirm_doc.hashCode(): 0) 
        + (confirm_date!=null? confirm_date.hashCode(): 0) 
        + (appli_motivat_div!=null? appli_motivat_div.hashCode(): 0) 
        + (invest_purpose_div!=null? invest_purpose_div.hashCode(): 0) 
        + (experience_equity!=null? experience_equity.hashCode(): 0) 
        + (experience_margin!=null? experience_margin.hashCode(): 0) 
        + (experience_bond!=null? experience_bond.hashCode(): 0) 
        + (experience_fund!=null? experience_fund.hashCode(): 0) 
        + (experience_fo!=null? experience_fo.hashCode(): 0) 
        + (experience_f_equity!=null? experience_f_equity.hashCode(): 0) 
        + (experience_etc!=null? experience_etc.hashCode(): 0) 
        + (experience_from!=null? experience_from.hashCode(): 0) 
        + (experience_to!=null? experience_to.hashCode(): 0) 
        + (equity_trade_div!=null? equity_trade_div.hashCode(): 0) 
        + (margin_trade_div!=null? margin_trade_div.hashCode(): 0) 
        + (bond_trade_div!=null? bond_trade_div.hashCode(): 0) 
        + (fund_trade_div!=null? fund_trade_div.hashCode(): 0) 
        + (fo_trade_div!=null? fo_trade_div.hashCode(): 0) 
        + (f_equity_trade_div!=null? f_equity_trade_div.hashCode(): 0) 
        + (etc_trade_div!=null? etc_trade_div.hashCode(): 0) 
        + (annual_income_from!=null? annual_income_from.hashCode(): 0) 
        + (annual_income_to!=null? annual_income_to.hashCode(): 0) 
        + (asset_value_from!=null? asset_value_from.hashCode(): 0) 
        + (asset_value_to!=null? asset_value_to.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (send_timestamp!=null? send_timestamp.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (div!=null? div.hashCode(): 0) 
        + (relation!=null? relation.hashCode(): 0) 
        + (confirm_way!=null? confirm_way.hashCode(): 0) 
        + (confirm_doc!=null? confirm_doc.hashCode(): 0) 
        + (add_confirm_doc!=null? add_confirm_doc.hashCode(): 0) 
        + (charge_confirm_date!=null? charge_confirm_date.hashCode(): 0) 
        + (charge_name!=null? charge_name.hashCode(): 0) 
        + (born_date!=null? born_date.hashCode(): 0) 
        + (telephone1!=null? telephone1.hashCode(): 0) 
        + (telephone2!=null? telephone2.hashCode(): 0) 
        + (telephone3!=null? telephone3.hashCode(): 0) 
        + (zip_code1!=null? zip_code1.hashCode(): 0) 
        + (zip_code2!=null? zip_code2.hashCode(): 0) 
        + (address_line1!=null? address_line1.hashCode(): 0) 
        + (address_line2!=null? address_line2.hashCode(): 0) 
        + (address_line3!=null? address_line3.hashCode(): 0) 
        + (address_line1_kana!=null? address_line1_kana.hashCode(): 0) 
        + (address_line2_kana!=null? address_line2_kana.hashCode(): 0) 
        + (address_line3_kana!=null? address_line3_kana.hashCode(): 0) 
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
		if ( regist_div != null )
			map.put("regist_div",regist_div);
		if ( data_class != null )
			map.put("data_class",data_class);
		if ( id_attribute_div != null )
			map.put("id_attribute_div",id_attribute_div);
		if ( trading_div != null )
			map.put("trading_div",trading_div);
		if ( confirm_way_div != null )
			map.put("confirm_way_div",confirm_way_div);
		if ( confirm_doc_div != null )
			map.put("confirm_doc_div",confirm_doc_div);
		if ( address_confirm_doc != null )
			map.put("address_confirm_doc",address_confirm_doc);
		if ( confirm_date != null )
			map.put("confirm_date",confirm_date);
		if ( appli_motivat_div != null )
			map.put("appli_motivat_div",appli_motivat_div);
		if ( invest_purpose_div != null )
			map.put("invest_purpose_div",invest_purpose_div);
		if ( experience_equity != null )
			map.put("experience_equity",experience_equity);
		if ( experience_margin != null )
			map.put("experience_margin",experience_margin);
		if ( experience_bond != null )
			map.put("experience_bond",experience_bond);
		if ( experience_fund != null )
			map.put("experience_fund",experience_fund);
		if ( experience_fo != null )
			map.put("experience_fo",experience_fo);
		if ( experience_f_equity != null )
			map.put("experience_f_equity",experience_f_equity);
		if ( experience_etc != null )
			map.put("experience_etc",experience_etc);
		if ( experience_from != null )
			map.put("experience_from",experience_from);
		if ( experience_to != null )
			map.put("experience_to",experience_to);
		if ( equity_trade_div != null )
			map.put("equity_trade_div",equity_trade_div);
		if ( margin_trade_div != null )
			map.put("margin_trade_div",margin_trade_div);
		if ( bond_trade_div != null )
			map.put("bond_trade_div",bond_trade_div);
		if ( fund_trade_div != null )
			map.put("fund_trade_div",fund_trade_div);
		if ( fo_trade_div != null )
			map.put("fo_trade_div",fo_trade_div);
		if ( f_equity_trade_div != null )
			map.put("f_equity_trade_div",f_equity_trade_div);
		if ( etc_trade_div != null )
			map.put("etc_trade_div",etc_trade_div);
		if ( annual_income_from != null )
			map.put("annual_income_from",annual_income_from);
		if ( annual_income_to != null )
			map.put("annual_income_to",annual_income_to);
		if ( asset_value_from != null )
			map.put("asset_value_from",asset_value_from);
		if ( asset_value_to != null )
			map.put("asset_value_to",asset_value_to);
		if ( status != null )
			map.put("status",status);
		if ( send_timestamp != null )
			map.put("send_timestamp",send_timestamp);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		if ( div != null )
			map.put("div",div);
		if ( relation != null )
			map.put("relation",relation);
		if ( confirm_way != null )
			map.put("confirm_way",confirm_way);
		if ( confirm_doc != null )
			map.put("confirm_doc",confirm_doc);
		if ( add_confirm_doc != null )
			map.put("add_confirm_doc",add_confirm_doc);
		if ( charge_confirm_date != null )
			map.put("charge_confirm_date",charge_confirm_date);
		if ( charge_name != null )
			map.put("charge_name",charge_name);
		if ( born_date != null )
			map.put("born_date",born_date);
		if ( telephone1 != null )
			map.put("telephone1",telephone1);
		if ( telephone2 != null )
			map.put("telephone2",telephone2);
		if ( telephone3 != null )
			map.put("telephone3",telephone3);
		if ( zip_code1 != null )
			map.put("zip_code1",zip_code1);
		if ( zip_code2 != null )
			map.put("zip_code2",zip_code2);
		if ( address_line1 != null )
			map.put("address_line1",address_line1);
		if ( address_line2 != null )
			map.put("address_line2",address_line2);
		if ( address_line3 != null )
			map.put("address_line3",address_line3);
		if ( address_line1_kana != null )
			map.put("address_line1_kana",address_line1_kana);
		if ( address_line2_kana != null )
			map.put("address_line2_kana",address_line2_kana);
		if ( address_line3_kana != null )
			map.put("address_line3_kana",address_line3_kana);
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
		if ( regist_div_is_modified )
			map.put("regist_div",regist_div);
		if ( data_class_is_modified )
			map.put("data_class",data_class);
		if ( id_attribute_div_is_modified )
			map.put("id_attribute_div",id_attribute_div);
		if ( trading_div_is_modified )
			map.put("trading_div",trading_div);
		if ( confirm_way_div_is_modified )
			map.put("confirm_way_div",confirm_way_div);
		if ( confirm_doc_div_is_modified )
			map.put("confirm_doc_div",confirm_doc_div);
		if ( address_confirm_doc_is_modified )
			map.put("address_confirm_doc",address_confirm_doc);
		if ( confirm_date_is_modified )
			map.put("confirm_date",confirm_date);
		if ( appli_motivat_div_is_modified )
			map.put("appli_motivat_div",appli_motivat_div);
		if ( invest_purpose_div_is_modified )
			map.put("invest_purpose_div",invest_purpose_div);
		if ( experience_equity_is_modified )
			map.put("experience_equity",experience_equity);
		if ( experience_margin_is_modified )
			map.put("experience_margin",experience_margin);
		if ( experience_bond_is_modified )
			map.put("experience_bond",experience_bond);
		if ( experience_fund_is_modified )
			map.put("experience_fund",experience_fund);
		if ( experience_fo_is_modified )
			map.put("experience_fo",experience_fo);
		if ( experience_f_equity_is_modified )
			map.put("experience_f_equity",experience_f_equity);
		if ( experience_etc_is_modified )
			map.put("experience_etc",experience_etc);
		if ( experience_from_is_modified )
			map.put("experience_from",experience_from);
		if ( experience_to_is_modified )
			map.put("experience_to",experience_to);
		if ( equity_trade_div_is_modified )
			map.put("equity_trade_div",equity_trade_div);
		if ( margin_trade_div_is_modified )
			map.put("margin_trade_div",margin_trade_div);
		if ( bond_trade_div_is_modified )
			map.put("bond_trade_div",bond_trade_div);
		if ( fund_trade_div_is_modified )
			map.put("fund_trade_div",fund_trade_div);
		if ( fo_trade_div_is_modified )
			map.put("fo_trade_div",fo_trade_div);
		if ( f_equity_trade_div_is_modified )
			map.put("f_equity_trade_div",f_equity_trade_div);
		if ( etc_trade_div_is_modified )
			map.put("etc_trade_div",etc_trade_div);
		if ( annual_income_from_is_modified )
			map.put("annual_income_from",annual_income_from);
		if ( annual_income_to_is_modified )
			map.put("annual_income_to",annual_income_to);
		if ( asset_value_from_is_modified )
			map.put("asset_value_from",asset_value_from);
		if ( asset_value_to_is_modified )
			map.put("asset_value_to",asset_value_to);
		if ( status_is_modified )
			map.put("status",status);
		if ( send_timestamp_is_modified )
			map.put("send_timestamp",send_timestamp);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( div_is_modified )
			map.put("div",div);
		if ( relation_is_modified )
			map.put("relation",relation);
		if ( confirm_way_is_modified )
			map.put("confirm_way",confirm_way);
		if ( confirm_doc_is_modified )
			map.put("confirm_doc",confirm_doc);
		if ( add_confirm_doc_is_modified )
			map.put("add_confirm_doc",add_confirm_doc);
		if ( charge_confirm_date_is_modified )
			map.put("charge_confirm_date",charge_confirm_date);
		if ( charge_name_is_modified )
			map.put("charge_name",charge_name);
		if ( born_date_is_modified )
			map.put("born_date",born_date);
		if ( telephone1_is_modified )
			map.put("telephone1",telephone1);
		if ( telephone2_is_modified )
			map.put("telephone2",telephone2);
		if ( telephone3_is_modified )
			map.put("telephone3",telephone3);
		if ( zip_code1_is_modified )
			map.put("zip_code1",zip_code1);
		if ( zip_code2_is_modified )
			map.put("zip_code2",zip_code2);
		if ( address_line1_is_modified )
			map.put("address_line1",address_line1);
		if ( address_line2_is_modified )
			map.put("address_line2",address_line2);
		if ( address_line3_is_modified )
			map.put("address_line3",address_line3);
		if ( address_line1_kana_is_modified )
			map.put("address_line1_kana",address_line1_kana);
		if ( address_line2_kana_is_modified )
			map.put("address_line2_kana",address_line2_kana);
		if ( address_line3_kana_is_modified )
			map.put("address_line3_kana",address_line3_kana);
		if (map.size() == 0) {
			map.put("trader_code",trader_code);
			if ( acc_open_request_number_is_set )
				map.put("acc_open_request_number",acc_open_request_number);
			if ( serial_no_is_set )
				map.put("serial_no",serial_no);
			map.put("regist_div",regist_div);
			map.put("data_class",data_class);
			map.put("id_attribute_div",id_attribute_div);
			map.put("trading_div",trading_div);
			map.put("confirm_way_div",confirm_way_div);
			map.put("confirm_doc_div",confirm_doc_div);
			map.put("address_confirm_doc",address_confirm_doc);
			map.put("confirm_date",confirm_date);
			map.put("appli_motivat_div",appli_motivat_div);
			map.put("invest_purpose_div",invest_purpose_div);
			map.put("experience_equity",experience_equity);
			map.put("experience_margin",experience_margin);
			map.put("experience_bond",experience_bond);
			map.put("experience_fund",experience_fund);
			map.put("experience_fo",experience_fo);
			map.put("experience_f_equity",experience_f_equity);
			map.put("experience_etc",experience_etc);
			map.put("experience_from",experience_from);
			map.put("experience_to",experience_to);
			map.put("equity_trade_div",equity_trade_div);
			map.put("margin_trade_div",margin_trade_div);
			map.put("bond_trade_div",bond_trade_div);
			map.put("fund_trade_div",fund_trade_div);
			map.put("fo_trade_div",fo_trade_div);
			map.put("f_equity_trade_div",f_equity_trade_div);
			map.put("etc_trade_div",etc_trade_div);
			map.put("annual_income_from",annual_income_from);
			map.put("annual_income_to",annual_income_to);
			map.put("asset_value_from",asset_value_from);
			map.put("asset_value_to",asset_value_to);
			map.put("status",status);
			map.put("send_timestamp",send_timestamp);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("div",div);
			map.put("relation",relation);
			map.put("confirm_way",confirm_way);
			map.put("confirm_doc",confirm_doc);
			map.put("add_confirm_doc",add_confirm_doc);
			map.put("charge_confirm_date",charge_confirm_date);
			map.put("charge_name",charge_name);
			map.put("born_date",born_date);
			map.put("telephone1",telephone1);
			map.put("telephone2",telephone2);
			map.put("telephone3",telephone3);
			map.put("zip_code1",zip_code1);
			map.put("zip_code2",zip_code2);
			map.put("address_line1",address_line1);
			map.put("address_line2",address_line2);
			map.put("address_line3",address_line3);
			map.put("address_line1_kana",address_line1_kana);
			map.put("address_line2_kana",address_line2_kana);
			map.put("address_line3_kana",address_line3_kana);
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
   * <em>id_attribute_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIdAttributeDiv()
  {
    return id_attribute_div;
  }


  /** 
   * <em>id_attribute_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIdAttributeDivIsSet() {
    return id_attribute_div_is_set;
  }


  /** 
   * <em>id_attribute_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIdAttributeDivIsModified() {
    return id_attribute_div_is_modified;
  }


  /** 
   * <em>trading_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradingDiv()
  {
    return trading_div;
  }


  /** 
   * <em>trading_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingDivIsSet() {
    return trading_div_is_set;
  }


  /** 
   * <em>trading_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingDivIsModified() {
    return trading_div_is_modified;
  }


  /** 
   * <em>confirm_way_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getConfirmWayDiv()
  {
    return confirm_way_div;
  }


  /** 
   * <em>confirm_way_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmWayDivIsSet() {
    return confirm_way_div_is_set;
  }


  /** 
   * <em>confirm_way_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmWayDivIsModified() {
    return confirm_way_div_is_modified;
  }


  /** 
   * <em>confirm_doc_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getConfirmDocDiv()
  {
    return confirm_doc_div;
  }


  /** 
   * <em>confirm_doc_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmDocDivIsSet() {
    return confirm_doc_div_is_set;
  }


  /** 
   * <em>confirm_doc_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmDocDivIsModified() {
    return confirm_doc_div_is_modified;
  }


  /** 
   * <em>address_confirm_doc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddressConfirmDoc()
  {
    return address_confirm_doc;
  }


  /** 
   * <em>address_confirm_doc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressConfirmDocIsSet() {
    return address_confirm_doc_is_set;
  }


  /** 
   * <em>address_confirm_doc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressConfirmDocIsModified() {
    return address_confirm_doc_is_modified;
  }


  /** 
   * <em>confirm_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getConfirmDate()
  {
    return confirm_date;
  }


  /** 
   * <em>confirm_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmDateIsSet() {
    return confirm_date_is_set;
  }


  /** 
   * <em>confirm_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmDateIsModified() {
    return confirm_date_is_modified;
  }


  /** 
   * <em>appli_motivat_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAppliMotivatDiv()
  {
    return appli_motivat_div;
  }


  /** 
   * <em>appli_motivat_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliMotivatDivIsSet() {
    return appli_motivat_div_is_set;
  }


  /** 
   * <em>appli_motivat_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliMotivatDivIsModified() {
    return appli_motivat_div_is_modified;
  }


  /** 
   * <em>invest_purpose_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInvestPurposeDiv()
  {
    return invest_purpose_div;
  }


  /** 
   * <em>invest_purpose_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInvestPurposeDivIsSet() {
    return invest_purpose_div_is_set;
  }


  /** 
   * <em>invest_purpose_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInvestPurposeDivIsModified() {
    return invest_purpose_div_is_modified;
  }


  /** 
   * <em>experience_equity</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceEquity()
  {
    return experience_equity;
  }


  /** 
   * <em>experience_equity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceEquityIsSet() {
    return experience_equity_is_set;
  }


  /** 
   * <em>experience_equity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceEquityIsModified() {
    return experience_equity_is_modified;
  }


  /** 
   * <em>experience_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceMargin()
  {
    return experience_margin;
  }


  /** 
   * <em>experience_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceMarginIsSet() {
    return experience_margin_is_set;
  }


  /** 
   * <em>experience_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceMarginIsModified() {
    return experience_margin_is_modified;
  }


  /** 
   * <em>experience_bond</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceBond()
  {
    return experience_bond;
  }


  /** 
   * <em>experience_bond</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceBondIsSet() {
    return experience_bond_is_set;
  }


  /** 
   * <em>experience_bond</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceBondIsModified() {
    return experience_bond_is_modified;
  }


  /** 
   * <em>experience_fund</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceFund()
  {
    return experience_fund;
  }


  /** 
   * <em>experience_fund</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFundIsSet() {
    return experience_fund_is_set;
  }


  /** 
   * <em>experience_fund</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFundIsModified() {
    return experience_fund_is_modified;
  }


  /** 
   * <em>experience_fo</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceFo()
  {
    return experience_fo;
  }


  /** 
   * <em>experience_fo</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFoIsSet() {
    return experience_fo_is_set;
  }


  /** 
   * <em>experience_fo</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFoIsModified() {
    return experience_fo_is_modified;
  }


  /** 
   * <em>experience_f_equity</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceFEquity()
  {
    return experience_f_equity;
  }


  /** 
   * <em>experience_f_equity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFEquityIsSet() {
    return experience_f_equity_is_set;
  }


  /** 
   * <em>experience_f_equity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFEquityIsModified() {
    return experience_f_equity_is_modified;
  }


  /** 
   * <em>experience_etc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceEtc()
  {
    return experience_etc;
  }


  /** 
   * <em>experience_etc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceEtcIsSet() {
    return experience_etc_is_set;
  }


  /** 
   * <em>experience_etc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceEtcIsModified() {
    return experience_etc_is_modified;
  }


  /** 
   * <em>experience_from</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceFrom()
  {
    return experience_from;
  }


  /** 
   * <em>experience_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFromIsSet() {
    return experience_from_is_set;
  }


  /** 
   * <em>experience_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFromIsModified() {
    return experience_from_is_modified;
  }


  /** 
   * <em>experience_to</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceTo()
  {
    return experience_to;
  }


  /** 
   * <em>experience_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceToIsSet() {
    return experience_to_is_set;
  }


  /** 
   * <em>experience_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceToIsModified() {
    return experience_to_is_modified;
  }


  /** 
   * <em>equity_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEquityTradeDiv()
  {
    return equity_trade_div;
  }


  /** 
   * <em>equity_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityTradeDivIsSet() {
    return equity_trade_div_is_set;
  }


  /** 
   * <em>equity_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityTradeDivIsModified() {
    return equity_trade_div_is_modified;
  }


  /** 
   * <em>margin_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginTradeDiv()
  {
    return margin_trade_div;
  }


  /** 
   * <em>margin_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTradeDivIsSet() {
    return margin_trade_div_is_set;
  }


  /** 
   * <em>margin_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTradeDivIsModified() {
    return margin_trade_div_is_modified;
  }


  /** 
   * <em>bond_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBondTradeDiv()
  {
    return bond_trade_div;
  }


  /** 
   * <em>bond_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondTradeDivIsSet() {
    return bond_trade_div_is_set;
  }


  /** 
   * <em>bond_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondTradeDivIsModified() {
    return bond_trade_div_is_modified;
  }


  /** 
   * <em>fund_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFundTradeDiv()
  {
    return fund_trade_div;
  }


  /** 
   * <em>fund_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundTradeDivIsSet() {
    return fund_trade_div_is_set;
  }


  /** 
   * <em>fund_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundTradeDivIsModified() {
    return fund_trade_div_is_modified;
  }


  /** 
   * <em>fo_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFoTradeDiv()
  {
    return fo_trade_div;
  }


  /** 
   * <em>fo_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFoTradeDivIsSet() {
    return fo_trade_div_is_set;
  }


  /** 
   * <em>fo_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFoTradeDivIsModified() {
    return fo_trade_div_is_modified;
  }


  /** 
   * <em>f_equity_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFEquityTradeDiv()
  {
    return f_equity_trade_div;
  }


  /** 
   * <em>f_equity_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFEquityTradeDivIsSet() {
    return f_equity_trade_div_is_set;
  }


  /** 
   * <em>f_equity_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFEquityTradeDivIsModified() {
    return f_equity_trade_div_is_modified;
  }


  /** 
   * <em>etc_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEtcTradeDiv()
  {
    return etc_trade_div;
  }


  /** 
   * <em>etc_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEtcTradeDivIsSet() {
    return etc_trade_div_is_set;
  }


  /** 
   * <em>etc_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEtcTradeDivIsModified() {
    return etc_trade_div_is_modified;
  }


  /** 
   * <em>annual_income_from</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAnnualIncomeFrom()
  {
    return annual_income_from;
  }


  /** 
   * <em>annual_income_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAnnualIncomeFromIsSet() {
    return annual_income_from_is_set;
  }


  /** 
   * <em>annual_income_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAnnualIncomeFromIsModified() {
    return annual_income_from_is_modified;
  }


  /** 
   * <em>annual_income_to</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAnnualIncomeTo()
  {
    return annual_income_to;
  }


  /** 
   * <em>annual_income_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAnnualIncomeToIsSet() {
    return annual_income_to_is_set;
  }


  /** 
   * <em>annual_income_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAnnualIncomeToIsModified() {
    return annual_income_to_is_modified;
  }


  /** 
   * <em>asset_value_from</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAssetValueFrom()
  {
    return asset_value_from;
  }


  /** 
   * <em>asset_value_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAssetValueFromIsSet() {
    return asset_value_from_is_set;
  }


  /** 
   * <em>asset_value_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAssetValueFromIsModified() {
    return asset_value_from_is_modified;
  }


  /** 
   * <em>asset_value_to</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAssetValueTo()
  {
    return asset_value_to;
  }


  /** 
   * <em>asset_value_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAssetValueToIsSet() {
    return asset_value_to_is_set;
  }


  /** 
   * <em>asset_value_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAssetValueToIsModified() {
    return asset_value_to_is_modified;
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
   * <em>div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDiv()
  {
    return div;
  }


  /** 
   * <em>div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDivIsSet() {
    return div_is_set;
  }


  /** 
   * <em>div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDivIsModified() {
    return div_is_modified;
  }


  /** 
   * <em>relation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRelation()
  {
    return relation;
  }


  /** 
   * <em>relation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRelationIsSet() {
    return relation_is_set;
  }


  /** 
   * <em>relation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRelationIsModified() {
    return relation_is_modified;
  }


  /** 
   * <em>confirm_way</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getConfirmWay()
  {
    return confirm_way;
  }


  /** 
   * <em>confirm_way</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmWayIsSet() {
    return confirm_way_is_set;
  }


  /** 
   * <em>confirm_way</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmWayIsModified() {
    return confirm_way_is_modified;
  }


  /** 
   * <em>confirm_doc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getConfirmDoc()
  {
    return confirm_doc;
  }


  /** 
   * <em>confirm_doc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmDocIsSet() {
    return confirm_doc_is_set;
  }


  /** 
   * <em>confirm_doc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getConfirmDocIsModified() {
    return confirm_doc_is_modified;
  }


  /** 
   * <em>add_confirm_doc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddConfirmDoc()
  {
    return add_confirm_doc;
  }


  /** 
   * <em>add_confirm_doc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddConfirmDocIsSet() {
    return add_confirm_doc_is_set;
  }


  /** 
   * <em>add_confirm_doc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddConfirmDocIsModified() {
    return add_confirm_doc_is_modified;
  }


  /** 
   * <em>charge_confirm_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getChargeConfirmDate()
  {
    return charge_confirm_date;
  }


  /** 
   * <em>charge_confirm_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChargeConfirmDateIsSet() {
    return charge_confirm_date_is_set;
  }


  /** 
   * <em>charge_confirm_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChargeConfirmDateIsModified() {
    return charge_confirm_date_is_modified;
  }


  /** 
   * <em>charge_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getChargeName()
  {
    return charge_name;
  }


  /** 
   * <em>charge_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChargeNameIsSet() {
    return charge_name_is_set;
  }


  /** 
   * <em>charge_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChargeNameIsModified() {
    return charge_name_is_modified;
  }


  /** 
   * <em>born_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getBornDate()
  {
    return born_date;
  }


  /** 
   * <em>born_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBornDateIsSet() {
    return born_date_is_set;
  }


  /** 
   * <em>born_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBornDateIsModified() {
    return born_date_is_modified;
  }


  /** 
   * <em>telephone1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTelephone1()
  {
    return telephone1;
  }


  /** 
   * <em>telephone1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTelephone1IsSet() {
    return telephone1_is_set;
  }


  /** 
   * <em>telephone1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTelephone1IsModified() {
    return telephone1_is_modified;
  }


  /** 
   * <em>telephone2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTelephone2()
  {
    return telephone2;
  }


  /** 
   * <em>telephone2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTelephone2IsSet() {
    return telephone2_is_set;
  }


  /** 
   * <em>telephone2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTelephone2IsModified() {
    return telephone2_is_modified;
  }


  /** 
   * <em>telephone3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTelephone3()
  {
    return telephone3;
  }


  /** 
   * <em>telephone3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTelephone3IsSet() {
    return telephone3_is_set;
  }


  /** 
   * <em>telephone3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTelephone3IsModified() {
    return telephone3_is_modified;
  }


  /** 
   * <em>zip_code1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getZipCode1()
  {
    return zip_code1;
  }


  /** 
   * <em>zip_code1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getZipCode1IsSet() {
    return zip_code1_is_set;
  }


  /** 
   * <em>zip_code1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getZipCode1IsModified() {
    return zip_code1_is_modified;
  }


  /** 
   * <em>zip_code2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getZipCode2()
  {
    return zip_code2;
  }


  /** 
   * <em>zip_code2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getZipCode2IsSet() {
    return zip_code2_is_set;
  }


  /** 
   * <em>zip_code2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getZipCode2IsModified() {
    return zip_code2_is_modified;
  }


  /** 
   * <em>address_line1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddressLine1()
  {
    return address_line1;
  }


  /** 
   * <em>address_line1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressLine1IsSet() {
    return address_line1_is_set;
  }


  /** 
   * <em>address_line1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressLine1IsModified() {
    return address_line1_is_modified;
  }


  /** 
   * <em>address_line2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddressLine2()
  {
    return address_line2;
  }


  /** 
   * <em>address_line2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressLine2IsSet() {
    return address_line2_is_set;
  }


  /** 
   * <em>address_line2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressLine2IsModified() {
    return address_line2_is_modified;
  }


  /** 
   * <em>address_line3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddressLine3()
  {
    return address_line3;
  }


  /** 
   * <em>address_line3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressLine3IsSet() {
    return address_line3_is_set;
  }


  /** 
   * <em>address_line3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressLine3IsModified() {
    return address_line3_is_modified;
  }


  /** 
   * <em>address_line1_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddressLine1Kana()
  {
    return address_line1_kana;
  }


  /** 
   * <em>address_line1_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressLine1KanaIsSet() {
    return address_line1_kana_is_set;
  }


  /** 
   * <em>address_line1_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressLine1KanaIsModified() {
    return address_line1_kana_is_modified;
  }


  /** 
   * <em>address_line2_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddressLine2Kana()
  {
    return address_line2_kana;
  }


  /** 
   * <em>address_line2_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressLine2KanaIsSet() {
    return address_line2_kana_is_set;
  }


  /** 
   * <em>address_line2_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressLine2KanaIsModified() {
    return address_line2_kana_is_modified;
  }


  /** 
   * <em>address_line3_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddressLine3Kana()
  {
    return address_line3_kana;
  }


  /** 
   * <em>address_line3_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressLine3KanaIsSet() {
    return address_line3_kana_is_set;
  }


  /** 
   * <em>address_line3_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressLine3KanaIsModified() {
    return address_line3_kana_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new IdConfirmVoucherPK(order_request_number, request_code, institution_code, branch_code, account_code);
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
   * <em>id_attribute_div</em>カラムの値を設定します。 
   *
   * @@param p_idAttributeDiv <em>id_attribute_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setIdAttributeDiv( String p_idAttributeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    id_attribute_div = p_idAttributeDiv;
    id_attribute_div_is_set = true;
    id_attribute_div_is_modified = true;
  }


  /** 
   * <em>trading_div</em>カラムの値を設定します。 
   *
   * @@param p_tradingDiv <em>trading_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTradingDiv( String p_tradingDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_div = p_tradingDiv;
    trading_div_is_set = true;
    trading_div_is_modified = true;
  }


  /** 
   * <em>confirm_way_div</em>カラムの値を設定します。 
   *
   * @@param p_confirmWayDiv <em>confirm_way_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setConfirmWayDiv( String p_confirmWayDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirm_way_div = p_confirmWayDiv;
    confirm_way_div_is_set = true;
    confirm_way_div_is_modified = true;
  }


  /** 
   * <em>confirm_doc_div</em>カラムの値を設定します。 
   *
   * @@param p_confirmDocDiv <em>confirm_doc_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setConfirmDocDiv( String p_confirmDocDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirm_doc_div = p_confirmDocDiv;
    confirm_doc_div_is_set = true;
    confirm_doc_div_is_modified = true;
  }


  /** 
   * <em>address_confirm_doc</em>カラムの値を設定します。 
   *
   * @@param p_addressConfirmDoc <em>address_confirm_doc</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAddressConfirmDoc( String p_addressConfirmDoc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address_confirm_doc = p_addressConfirmDoc;
    address_confirm_doc_is_set = true;
    address_confirm_doc_is_modified = true;
  }


  /** 
   * <em>confirm_date</em>カラムの値を設定します。 
   *
   * @@param p_confirmDate <em>confirm_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setConfirmDate( java.sql.Timestamp p_confirmDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirm_date = p_confirmDate;
    confirm_date_is_set = true;
    confirm_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>confirm_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setConfirmDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    confirm_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    confirm_date_is_set = true;
    confirm_date_is_modified = true;
  }


  /** 
   * <em>appli_motivat_div</em>カラムの値を設定します。 
   *
   * @@param p_appliMotivatDiv <em>appli_motivat_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAppliMotivatDiv( String p_appliMotivatDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_motivat_div = p_appliMotivatDiv;
    appli_motivat_div_is_set = true;
    appli_motivat_div_is_modified = true;
  }


  /** 
   * <em>invest_purpose_div</em>カラムの値を設定します。 
   *
   * @@param p_investPurposeDiv <em>invest_purpose_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setInvestPurposeDiv( String p_investPurposeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    invest_purpose_div = p_investPurposeDiv;
    invest_purpose_div_is_set = true;
    invest_purpose_div_is_modified = true;
  }


  /** 
   * <em>experience_equity</em>カラムの値を設定します。 
   *
   * @@param p_experienceEquity <em>experience_equity</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExperienceEquity( String p_experienceEquity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_equity = p_experienceEquity;
    experience_equity_is_set = true;
    experience_equity_is_modified = true;
  }


  /** 
   * <em>experience_margin</em>カラムの値を設定します。 
   *
   * @@param p_experienceMargin <em>experience_margin</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExperienceMargin( String p_experienceMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_margin = p_experienceMargin;
    experience_margin_is_set = true;
    experience_margin_is_modified = true;
  }


  /** 
   * <em>experience_bond</em>カラムの値を設定します。 
   *
   * @@param p_experienceBond <em>experience_bond</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExperienceBond( String p_experienceBond )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_bond = p_experienceBond;
    experience_bond_is_set = true;
    experience_bond_is_modified = true;
  }


  /** 
   * <em>experience_fund</em>カラムの値を設定します。 
   *
   * @@param p_experienceFund <em>experience_fund</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExperienceFund( String p_experienceFund )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_fund = p_experienceFund;
    experience_fund_is_set = true;
    experience_fund_is_modified = true;
  }


  /** 
   * <em>experience_fo</em>カラムの値を設定します。 
   *
   * @@param p_experienceFo <em>experience_fo</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExperienceFo( String p_experienceFo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_fo = p_experienceFo;
    experience_fo_is_set = true;
    experience_fo_is_modified = true;
  }


  /** 
   * <em>experience_f_equity</em>カラムの値を設定します。 
   *
   * @@param p_experienceFEquity <em>experience_f_equity</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExperienceFEquity( String p_experienceFEquity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_f_equity = p_experienceFEquity;
    experience_f_equity_is_set = true;
    experience_f_equity_is_modified = true;
  }


  /** 
   * <em>experience_etc</em>カラムの値を設定します。 
   *
   * @@param p_experienceEtc <em>experience_etc</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExperienceEtc( String p_experienceEtc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_etc = p_experienceEtc;
    experience_etc_is_set = true;
    experience_etc_is_modified = true;
  }


  /** 
   * <em>experience_from</em>カラムの値を設定します。 
   *
   * @@param p_experienceFrom <em>experience_from</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExperienceFrom( String p_experienceFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_from = p_experienceFrom;
    experience_from_is_set = true;
    experience_from_is_modified = true;
  }


  /** 
   * <em>experience_to</em>カラムの値を設定します。 
   *
   * @@param p_experienceTo <em>experience_to</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setExperienceTo( String p_experienceTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_to = p_experienceTo;
    experience_to_is_set = true;
    experience_to_is_modified = true;
  }


  /** 
   * <em>equity_trade_div</em>カラムの値を設定します。 
   *
   * @@param p_equityTradeDiv <em>equity_trade_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEquityTradeDiv( String p_equityTradeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_trade_div = p_equityTradeDiv;
    equity_trade_div_is_set = true;
    equity_trade_div_is_modified = true;
  }


  /** 
   * <em>margin_trade_div</em>カラムの値を設定します。 
   *
   * @@param p_marginTradeDiv <em>margin_trade_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarginTradeDiv( String p_marginTradeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_trade_div = p_marginTradeDiv;
    margin_trade_div_is_set = true;
    margin_trade_div_is_modified = true;
  }


  /** 
   * <em>bond_trade_div</em>カラムの値を設定します。 
   *
   * @@param p_bondTradeDiv <em>bond_trade_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBondTradeDiv( String p_bondTradeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bond_trade_div = p_bondTradeDiv;
    bond_trade_div_is_set = true;
    bond_trade_div_is_modified = true;
  }


  /** 
   * <em>fund_trade_div</em>カラムの値を設定します。 
   *
   * @@param p_fundTradeDiv <em>fund_trade_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFundTradeDiv( String p_fundTradeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fund_trade_div = p_fundTradeDiv;
    fund_trade_div_is_set = true;
    fund_trade_div_is_modified = true;
  }


  /** 
   * <em>fo_trade_div</em>カラムの値を設定します。 
   *
   * @@param p_foTradeDiv <em>fo_trade_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFoTradeDiv( String p_foTradeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fo_trade_div = p_foTradeDiv;
    fo_trade_div_is_set = true;
    fo_trade_div_is_modified = true;
  }


  /** 
   * <em>f_equity_trade_div</em>カラムの値を設定します。 
   *
   * @@param p_fEquityTradeDiv <em>f_equity_trade_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFEquityTradeDiv( String p_fEquityTradeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    f_equity_trade_div = p_fEquityTradeDiv;
    f_equity_trade_div_is_set = true;
    f_equity_trade_div_is_modified = true;
  }


  /** 
   * <em>etc_trade_div</em>カラムの値を設定します。 
   *
   * @@param p_etcTradeDiv <em>etc_trade_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEtcTradeDiv( String p_etcTradeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    etc_trade_div = p_etcTradeDiv;
    etc_trade_div_is_set = true;
    etc_trade_div_is_modified = true;
  }


  /** 
   * <em>annual_income_from</em>カラムの値を設定します。 
   *
   * @@param p_annualIncomeFrom <em>annual_income_from</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setAnnualIncomeFrom( String p_annualIncomeFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    annual_income_from = p_annualIncomeFrom;
    annual_income_from_is_set = true;
    annual_income_from_is_modified = true;
  }


  /** 
   * <em>annual_income_to</em>カラムの値を設定します。 
   *
   * @@param p_annualIncomeTo <em>annual_income_to</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setAnnualIncomeTo( String p_annualIncomeTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    annual_income_to = p_annualIncomeTo;
    annual_income_to_is_set = true;
    annual_income_to_is_modified = true;
  }


  /** 
   * <em>asset_value_from</em>カラムの値を設定します。 
   *
   * @@param p_assetValueFrom <em>asset_value_from</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setAssetValueFrom( String p_assetValueFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    asset_value_from = p_assetValueFrom;
    asset_value_from_is_set = true;
    asset_value_from_is_modified = true;
  }


  /** 
   * <em>asset_value_to</em>カラムの値を設定します。 
   *
   * @@param p_assetValueTo <em>asset_value_to</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setAssetValueTo( String p_assetValueTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    asset_value_to = p_assetValueTo;
    asset_value_to_is_set = true;
    asset_value_to_is_modified = true;
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
   * <em>div</em>カラムの値を設定します。 
   *
   * @@param p_div <em>div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDiv( String p_div )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    div = p_div;
    div_is_set = true;
    div_is_modified = true;
  }


  /** 
   * <em>relation</em>カラムの値を設定します。 
   *
   * @@param p_relation <em>relation</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRelation( String p_relation )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    relation = p_relation;
    relation_is_set = true;
    relation_is_modified = true;
  }


  /** 
   * <em>confirm_way</em>カラムの値を設定します。 
   *
   * @@param p_confirmWay <em>confirm_way</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setConfirmWay( String p_confirmWay )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirm_way = p_confirmWay;
    confirm_way_is_set = true;
    confirm_way_is_modified = true;
  }


  /** 
   * <em>confirm_doc</em>カラムの値を設定します。 
   *
   * @@param p_confirmDoc <em>confirm_doc</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setConfirmDoc( String p_confirmDoc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    confirm_doc = p_confirmDoc;
    confirm_doc_is_set = true;
    confirm_doc_is_modified = true;
  }


  /** 
   * <em>add_confirm_doc</em>カラムの値を設定します。 
   *
   * @@param p_addConfirmDoc <em>add_confirm_doc</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAddConfirmDoc( String p_addConfirmDoc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    add_confirm_doc = p_addConfirmDoc;
    add_confirm_doc_is_set = true;
    add_confirm_doc_is_modified = true;
  }


  /** 
   * <em>charge_confirm_date</em>カラムの値を設定します。 
   *
   * @@param p_chargeConfirmDate <em>charge_confirm_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setChargeConfirmDate( java.sql.Timestamp p_chargeConfirmDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    charge_confirm_date = p_chargeConfirmDate;
    charge_confirm_date_is_set = true;
    charge_confirm_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>charge_confirm_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setChargeConfirmDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    charge_confirm_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    charge_confirm_date_is_set = true;
    charge_confirm_date_is_modified = true;
  }


  /** 
   * <em>charge_name</em>カラムの値を設定します。 
   *
   * @@param p_chargeName <em>charge_name</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setChargeName( String p_chargeName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    charge_name = p_chargeName;
    charge_name_is_set = true;
    charge_name_is_modified = true;
  }


  /** 
   * <em>born_date</em>カラムの値を設定します。 
   *
   * @@param p_bornDate <em>born_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setBornDate( java.sql.Timestamp p_bornDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    born_date = p_bornDate;
    born_date_is_set = true;
    born_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>born_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setBornDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    born_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    born_date_is_set = true;
    born_date_is_modified = true;
  }


  /** 
   * <em>telephone1</em>カラムの値を設定します。 
   *
   * @@param p_telephone1 <em>telephone1</em>カラムの値をあらわす16桁以下のString値 
   */
  public final void setTelephone1( String p_telephone1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    telephone1 = p_telephone1;
    telephone1_is_set = true;
    telephone1_is_modified = true;
  }


  /** 
   * <em>telephone2</em>カラムの値を設定します。 
   *
   * @@param p_telephone2 <em>telephone2</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setTelephone2( String p_telephone2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    telephone2 = p_telephone2;
    telephone2_is_set = true;
    telephone2_is_modified = true;
  }


  /** 
   * <em>telephone3</em>カラムの値を設定します。 
   *
   * @@param p_telephone3 <em>telephone3</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setTelephone3( String p_telephone3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    telephone3 = p_telephone3;
    telephone3_is_set = true;
    telephone3_is_modified = true;
  }


  /** 
   * <em>zip_code1</em>カラムの値を設定します。 
   *
   * @@param p_zipCode1 <em>zip_code1</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setZipCode1( String p_zipCode1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    zip_code1 = p_zipCode1;
    zip_code1_is_set = true;
    zip_code1_is_modified = true;
  }


  /** 
   * <em>zip_code2</em>カラムの値を設定します。 
   *
   * @@param p_zipCode2 <em>zip_code2</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setZipCode2( String p_zipCode2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    zip_code2 = p_zipCode2;
    zip_code2_is_set = true;
    zip_code2_is_modified = true;
  }


  /** 
   * <em>address_line1</em>カラムの値を設定します。 
   *
   * @@param p_addressLine1 <em>address_line1</em>カラムの値をあらわす32桁以下のString値 
   */
  public final void setAddressLine1( String p_addressLine1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address_line1 = p_addressLine1;
    address_line1_is_set = true;
    address_line1_is_modified = true;
  }


  /** 
   * <em>address_line2</em>カラムの値を設定します。 
   *
   * @@param p_addressLine2 <em>address_line2</em>カラムの値をあらわす32桁以下のString値 
   */
  public final void setAddressLine2( String p_addressLine2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address_line2 = p_addressLine2;
    address_line2_is_set = true;
    address_line2_is_modified = true;
  }


  /** 
   * <em>address_line3</em>カラムの値を設定します。 
   *
   * @@param p_addressLine3 <em>address_line3</em>カラムの値をあらわす32桁以下のString値 
   */
  public final void setAddressLine3( String p_addressLine3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address_line3 = p_addressLine3;
    address_line3_is_set = true;
    address_line3_is_modified = true;
  }


  /** 
   * <em>address_line1_kana</em>カラムの値を設定します。 
   *
   * @@param p_addressLine1Kana <em>address_line1_kana</em>カラムの値をあらわす16桁以下のString値 
   */
  public final void setAddressLine1Kana( String p_addressLine1Kana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address_line1_kana = p_addressLine1Kana;
    address_line1_kana_is_set = true;
    address_line1_kana_is_modified = true;
  }


  /** 
   * <em>address_line2_kana</em>カラムの値を設定します。 
   *
   * @@param p_addressLine2Kana <em>address_line2_kana</em>カラムの値をあらわす27桁以下のString値 
   */
  public final void setAddressLine2Kana( String p_addressLine2Kana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address_line2_kana = p_addressLine2Kana;
    address_line2_kana_is_set = true;
    address_line2_kana_is_modified = true;
  }


  /** 
   * <em>address_line3_kana</em>カラムの値を設定します。 
   *
   * @@param p_addressLine3Kana <em>address_line3_kana</em>カラムの値をあらわす27桁以下のString値 
   */
  public final void setAddressLine3Kana( String p_addressLine3Kana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address_line3_kana = p_addressLine3Kana;
    address_line3_kana_is_set = true;
    address_line3_kana_is_modified = true;
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
                else if ( name.equals("address_confirm_doc") ) {
                    return this.address_confirm_doc;
                }
                else if ( name.equals("appli_motivat_div") ) {
                    return this.appli_motivat_div;
                }
                else if ( name.equals("annual_income_from") ) {
                    return this.annual_income_from;
                }
                else if ( name.equals("annual_income_to") ) {
                    return this.annual_income_to;
                }
                else if ( name.equals("asset_value_from") ) {
                    return this.asset_value_from;
                }
                else if ( name.equals("asset_value_to") ) {
                    return this.asset_value_to;
                }
                else if ( name.equals("add_confirm_doc") ) {
                    return this.add_confirm_doc;
                }
                else if ( name.equals("address_line1") ) {
                    return this.address_line1;
                }
                else if ( name.equals("address_line2") ) {
                    return this.address_line2;
                }
                else if ( name.equals("address_line3") ) {
                    return this.address_line3;
                }
                else if ( name.equals("address_line1_kana") ) {
                    return this.address_line1_kana;
                }
                else if ( name.equals("address_line2_kana") ) {
                    return this.address_line2_kana;
                }
                else if ( name.equals("address_line3_kana") ) {
                    return this.address_line3_kana;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("bond_trade_div") ) {
                    return this.bond_trade_div;
                }
                else if ( name.equals("born_date") ) {
                    return this.born_date;
                }
                break;
            case 'c':
                if ( name.equals("confirm_way_div") ) {
                    return this.confirm_way_div;
                }
                else if ( name.equals("confirm_doc_div") ) {
                    return this.confirm_doc_div;
                }
                else if ( name.equals("confirm_date") ) {
                    return this.confirm_date;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("confirm_way") ) {
                    return this.confirm_way;
                }
                else if ( name.equals("confirm_doc") ) {
                    return this.confirm_doc;
                }
                else if ( name.equals("charge_confirm_date") ) {
                    return this.charge_confirm_date;
                }
                else if ( name.equals("charge_name") ) {
                    return this.charge_name;
                }
                break;
            case 'd':
                if ( name.equals("data_class") ) {
                    return this.data_class;
                }
                else if ( name.equals("div") ) {
                    return this.div;
                }
                break;
            case 'e':
                if ( name.equals("experience_equity") ) {
                    return this.experience_equity;
                }
                else if ( name.equals("experience_margin") ) {
                    return this.experience_margin;
                }
                else if ( name.equals("experience_bond") ) {
                    return this.experience_bond;
                }
                else if ( name.equals("experience_fund") ) {
                    return this.experience_fund;
                }
                else if ( name.equals("experience_fo") ) {
                    return this.experience_fo;
                }
                else if ( name.equals("experience_f_equity") ) {
                    return this.experience_f_equity;
                }
                else if ( name.equals("experience_etc") ) {
                    return this.experience_etc;
                }
                else if ( name.equals("experience_from") ) {
                    return this.experience_from;
                }
                else if ( name.equals("experience_to") ) {
                    return this.experience_to;
                }
                else if ( name.equals("equity_trade_div") ) {
                    return this.equity_trade_div;
                }
                else if ( name.equals("etc_trade_div") ) {
                    return this.etc_trade_div;
                }
                break;
            case 'f':
                if ( name.equals("fund_trade_div") ) {
                    return this.fund_trade_div;
                }
                else if ( name.equals("fo_trade_div") ) {
                    return this.fo_trade_div;
                }
                else if ( name.equals("f_equity_trade_div") ) {
                    return this.f_equity_trade_div;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("id_attribute_div") ) {
                    return this.id_attribute_div;
                }
                else if ( name.equals("invest_purpose_div") ) {
                    return this.invest_purpose_div;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("margin_trade_div") ) {
                    return this.margin_trade_div;
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
                else if ( name.equals("regist_div") ) {
                    return this.regist_div;
                }
                else if ( name.equals("relation") ) {
                    return this.relation;
                }
                break;
            case 's':
                if ( name.equals("serial_no") ) {
                    return this.serial_no;
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
                else if ( name.equals("trading_div") ) {
                    return this.trading_div;
                }
                else if ( name.equals("telephone1") ) {
                    return this.telephone1;
                }
                else if ( name.equals("telephone2") ) {
                    return this.telephone2;
                }
                else if ( name.equals("telephone3") ) {
                    return this.telephone3;
                }
                break;
            case 'z':
                if ( name.equals("zip_code1") ) {
                    return this.zip_code1;
                }
                else if ( name.equals("zip_code2") ) {
                    return this.zip_code2;
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
                else if ( name.equals("address_confirm_doc") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'address_confirm_doc' must be String: '"+value+"' is not." );
                    this.address_confirm_doc = (String) value;
                    if (this.address_confirm_doc_is_set)
                        this.address_confirm_doc_is_modified = true;
                    this.address_confirm_doc_is_set = true;
                    return;
                }
                else if ( name.equals("appli_motivat_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'appli_motivat_div' must be String: '"+value+"' is not." );
                    this.appli_motivat_div = (String) value;
                    if (this.appli_motivat_div_is_set)
                        this.appli_motivat_div_is_modified = true;
                    this.appli_motivat_div_is_set = true;
                    return;
                }
                else if ( name.equals("annual_income_from") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'annual_income_from' must be String: '"+value+"' is not." );
                    this.annual_income_from = (String) value;
                    if (this.annual_income_from_is_set)
                        this.annual_income_from_is_modified = true;
                    this.annual_income_from_is_set = true;
                    return;
                }
                else if ( name.equals("annual_income_to") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'annual_income_to' must be String: '"+value+"' is not." );
                    this.annual_income_to = (String) value;
                    if (this.annual_income_to_is_set)
                        this.annual_income_to_is_modified = true;
                    this.annual_income_to_is_set = true;
                    return;
                }
                else if ( name.equals("asset_value_from") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'asset_value_from' must be String: '"+value+"' is not." );
                    this.asset_value_from = (String) value;
                    if (this.asset_value_from_is_set)
                        this.asset_value_from_is_modified = true;
                    this.asset_value_from_is_set = true;
                    return;
                }
                else if ( name.equals("asset_value_to") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'asset_value_to' must be String: '"+value+"' is not." );
                    this.asset_value_to = (String) value;
                    if (this.asset_value_to_is_set)
                        this.asset_value_to_is_modified = true;
                    this.asset_value_to_is_set = true;
                    return;
                }
                else if ( name.equals("add_confirm_doc") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'add_confirm_doc' must be String: '"+value+"' is not." );
                    this.add_confirm_doc = (String) value;
                    if (this.add_confirm_doc_is_set)
                        this.add_confirm_doc_is_modified = true;
                    this.add_confirm_doc_is_set = true;
                    return;
                }
                else if ( name.equals("address_line1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'address_line1' must be String: '"+value+"' is not." );
                    this.address_line1 = (String) value;
                    if (this.address_line1_is_set)
                        this.address_line1_is_modified = true;
                    this.address_line1_is_set = true;
                    return;
                }
                else if ( name.equals("address_line2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'address_line2' must be String: '"+value+"' is not." );
                    this.address_line2 = (String) value;
                    if (this.address_line2_is_set)
                        this.address_line2_is_modified = true;
                    this.address_line2_is_set = true;
                    return;
                }
                else if ( name.equals("address_line3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'address_line3' must be String: '"+value+"' is not." );
                    this.address_line3 = (String) value;
                    if (this.address_line3_is_set)
                        this.address_line3_is_modified = true;
                    this.address_line3_is_set = true;
                    return;
                }
                else if ( name.equals("address_line1_kana") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'address_line1_kana' must be String: '"+value+"' is not." );
                    this.address_line1_kana = (String) value;
                    if (this.address_line1_kana_is_set)
                        this.address_line1_kana_is_modified = true;
                    this.address_line1_kana_is_set = true;
                    return;
                }
                else if ( name.equals("address_line2_kana") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'address_line2_kana' must be String: '"+value+"' is not." );
                    this.address_line2_kana = (String) value;
                    if (this.address_line2_kana_is_set)
                        this.address_line2_kana_is_modified = true;
                    this.address_line2_kana_is_set = true;
                    return;
                }
                else if ( name.equals("address_line3_kana") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'address_line3_kana' must be String: '"+value+"' is not." );
                    this.address_line3_kana = (String) value;
                    if (this.address_line3_kana_is_set)
                        this.address_line3_kana_is_modified = true;
                    this.address_line3_kana_is_set = true;
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
                else if ( name.equals("bond_trade_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bond_trade_div' must be String: '"+value+"' is not." );
                    this.bond_trade_div = (String) value;
                    if (this.bond_trade_div_is_set)
                        this.bond_trade_div_is_modified = true;
                    this.bond_trade_div_is_set = true;
                    return;
                }
                else if ( name.equals("born_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'born_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.born_date = (java.sql.Timestamp) value;
                    if (this.born_date_is_set)
                        this.born_date_is_modified = true;
                    this.born_date_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("confirm_way_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'confirm_way_div' must be String: '"+value+"' is not." );
                    this.confirm_way_div = (String) value;
                    if (this.confirm_way_div_is_set)
                        this.confirm_way_div_is_modified = true;
                    this.confirm_way_div_is_set = true;
                    return;
                }
                else if ( name.equals("confirm_doc_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'confirm_doc_div' must be String: '"+value+"' is not." );
                    this.confirm_doc_div = (String) value;
                    if (this.confirm_doc_div_is_set)
                        this.confirm_doc_div_is_modified = true;
                    this.confirm_doc_div_is_set = true;
                    return;
                }
                else if ( name.equals("confirm_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'confirm_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.confirm_date = (java.sql.Timestamp) value;
                    if (this.confirm_date_is_set)
                        this.confirm_date_is_modified = true;
                    this.confirm_date_is_set = true;
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
                else if ( name.equals("confirm_way") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'confirm_way' must be String: '"+value+"' is not." );
                    this.confirm_way = (String) value;
                    if (this.confirm_way_is_set)
                        this.confirm_way_is_modified = true;
                    this.confirm_way_is_set = true;
                    return;
                }
                else if ( name.equals("confirm_doc") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'confirm_doc' must be String: '"+value+"' is not." );
                    this.confirm_doc = (String) value;
                    if (this.confirm_doc_is_set)
                        this.confirm_doc_is_modified = true;
                    this.confirm_doc_is_set = true;
                    return;
                }
                else if ( name.equals("charge_confirm_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'charge_confirm_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.charge_confirm_date = (java.sql.Timestamp) value;
                    if (this.charge_confirm_date_is_set)
                        this.charge_confirm_date_is_modified = true;
                    this.charge_confirm_date_is_set = true;
                    return;
                }
                else if ( name.equals("charge_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'charge_name' must be String: '"+value+"' is not." );
                    this.charge_name = (String) value;
                    if (this.charge_name_is_set)
                        this.charge_name_is_modified = true;
                    this.charge_name_is_set = true;
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
                else if ( name.equals("div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'div' must be String: '"+value+"' is not." );
                    this.div = (String) value;
                    if (this.div_is_set)
                        this.div_is_modified = true;
                    this.div_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("experience_equity") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_equity' must be String: '"+value+"' is not." );
                    this.experience_equity = (String) value;
                    if (this.experience_equity_is_set)
                        this.experience_equity_is_modified = true;
                    this.experience_equity_is_set = true;
                    return;
                }
                else if ( name.equals("experience_margin") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_margin' must be String: '"+value+"' is not." );
                    this.experience_margin = (String) value;
                    if (this.experience_margin_is_set)
                        this.experience_margin_is_modified = true;
                    this.experience_margin_is_set = true;
                    return;
                }
                else if ( name.equals("experience_bond") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_bond' must be String: '"+value+"' is not." );
                    this.experience_bond = (String) value;
                    if (this.experience_bond_is_set)
                        this.experience_bond_is_modified = true;
                    this.experience_bond_is_set = true;
                    return;
                }
                else if ( name.equals("experience_fund") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_fund' must be String: '"+value+"' is not." );
                    this.experience_fund = (String) value;
                    if (this.experience_fund_is_set)
                        this.experience_fund_is_modified = true;
                    this.experience_fund_is_set = true;
                    return;
                }
                else if ( name.equals("experience_fo") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_fo' must be String: '"+value+"' is not." );
                    this.experience_fo = (String) value;
                    if (this.experience_fo_is_set)
                        this.experience_fo_is_modified = true;
                    this.experience_fo_is_set = true;
                    return;
                }
                else if ( name.equals("experience_f_equity") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_f_equity' must be String: '"+value+"' is not." );
                    this.experience_f_equity = (String) value;
                    if (this.experience_f_equity_is_set)
                        this.experience_f_equity_is_modified = true;
                    this.experience_f_equity_is_set = true;
                    return;
                }
                else if ( name.equals("experience_etc") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_etc' must be String: '"+value+"' is not." );
                    this.experience_etc = (String) value;
                    if (this.experience_etc_is_set)
                        this.experience_etc_is_modified = true;
                    this.experience_etc_is_set = true;
                    return;
                }
                else if ( name.equals("experience_from") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_from' must be String: '"+value+"' is not." );
                    this.experience_from = (String) value;
                    if (this.experience_from_is_set)
                        this.experience_from_is_modified = true;
                    this.experience_from_is_set = true;
                    return;
                }
                else if ( name.equals("experience_to") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_to' must be String: '"+value+"' is not." );
                    this.experience_to = (String) value;
                    if (this.experience_to_is_set)
                        this.experience_to_is_modified = true;
                    this.experience_to_is_set = true;
                    return;
                }
                else if ( name.equals("equity_trade_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'equity_trade_div' must be String: '"+value+"' is not." );
                    this.equity_trade_div = (String) value;
                    if (this.equity_trade_div_is_set)
                        this.equity_trade_div_is_modified = true;
                    this.equity_trade_div_is_set = true;
                    return;
                }
                else if ( name.equals("etc_trade_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'etc_trade_div' must be String: '"+value+"' is not." );
                    this.etc_trade_div = (String) value;
                    if (this.etc_trade_div_is_set)
                        this.etc_trade_div_is_modified = true;
                    this.etc_trade_div_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fund_trade_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fund_trade_div' must be String: '"+value+"' is not." );
                    this.fund_trade_div = (String) value;
                    if (this.fund_trade_div_is_set)
                        this.fund_trade_div_is_modified = true;
                    this.fund_trade_div_is_set = true;
                    return;
                }
                else if ( name.equals("fo_trade_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fo_trade_div' must be String: '"+value+"' is not." );
                    this.fo_trade_div = (String) value;
                    if (this.fo_trade_div_is_set)
                        this.fo_trade_div_is_modified = true;
                    this.fo_trade_div_is_set = true;
                    return;
                }
                else if ( name.equals("f_equity_trade_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'f_equity_trade_div' must be String: '"+value+"' is not." );
                    this.f_equity_trade_div = (String) value;
                    if (this.f_equity_trade_div_is_set)
                        this.f_equity_trade_div_is_modified = true;
                    this.f_equity_trade_div_is_set = true;
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
                else if ( name.equals("id_attribute_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'id_attribute_div' must be String: '"+value+"' is not." );
                    this.id_attribute_div = (String) value;
                    if (this.id_attribute_div_is_set)
                        this.id_attribute_div_is_modified = true;
                    this.id_attribute_div_is_set = true;
                    return;
                }
                else if ( name.equals("invest_purpose_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'invest_purpose_div' must be String: '"+value+"' is not." );
                    this.invest_purpose_div = (String) value;
                    if (this.invest_purpose_div_is_set)
                        this.invest_purpose_div_is_modified = true;
                    this.invest_purpose_div_is_set = true;
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
                if ( name.equals("margin_trade_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_trade_div' must be String: '"+value+"' is not." );
                    this.margin_trade_div = (String) value;
                    if (this.margin_trade_div_is_set)
                        this.margin_trade_div_is_modified = true;
                    this.margin_trade_div_is_set = true;
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
                else if ( name.equals("relation") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'relation' must be String: '"+value+"' is not." );
                    this.relation = (String) value;
                    if (this.relation_is_set)
                        this.relation_is_modified = true;
                    this.relation_is_set = true;
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
                else if ( name.equals("trading_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trading_div' must be String: '"+value+"' is not." );
                    this.trading_div = (String) value;
                    if (this.trading_div_is_set)
                        this.trading_div_is_modified = true;
                    this.trading_div_is_set = true;
                    return;
                }
                else if ( name.equals("telephone1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'telephone1' must be String: '"+value+"' is not." );
                    this.telephone1 = (String) value;
                    if (this.telephone1_is_set)
                        this.telephone1_is_modified = true;
                    this.telephone1_is_set = true;
                    return;
                }
                else if ( name.equals("telephone2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'telephone2' must be String: '"+value+"' is not." );
                    this.telephone2 = (String) value;
                    if (this.telephone2_is_set)
                        this.telephone2_is_modified = true;
                    this.telephone2_is_set = true;
                    return;
                }
                else if ( name.equals("telephone3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'telephone3' must be String: '"+value+"' is not." );
                    this.telephone3 = (String) value;
                    if (this.telephone3_is_set)
                        this.telephone3_is_modified = true;
                    this.telephone3_is_set = true;
                    return;
                }
                break;
            case 'z':
                if ( name.equals("zip_code1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'zip_code1' must be String: '"+value+"' is not." );
                    this.zip_code1 = (String) value;
                    if (this.zip_code1_is_set)
                        this.zip_code1_is_modified = true;
                    this.zip_code1_is_set = true;
                    return;
                }
                else if ( name.equals("zip_code2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'zip_code2' must be String: '"+value+"' is not." );
                    this.zip_code2 = (String) value;
                    if (this.zip_code2_is_set)
                        this.zip_code2_is_modified = true;
                    this.zip_code2_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
