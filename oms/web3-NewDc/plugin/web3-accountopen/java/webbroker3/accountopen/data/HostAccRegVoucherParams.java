head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.19.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	HostAccRegVoucherParams.java;


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
 * host_acc_reg_voucherテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link HostAccRegVoucherRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link HostAccRegVoucherParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link HostAccRegVoucherParams}が{@@link HostAccRegVoucherRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostAccRegVoucherPK 
 * @@see HostAccRegVoucherRow 
 */
public class HostAccRegVoucherParams extends Params implements HostAccRegVoucherRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "host_acc_reg_voucher";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = HostAccRegVoucherRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return HostAccRegVoucherRow.TYPE;
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
   * <em>account_name_kana</em>カラムの値 
   */
  public  String  account_name_kana;

  /** 
   * <em>account_name</em>カラムの値 
   */
  public  String  account_name;

  /** 
   * <em>zip_code</em>カラムの値 
   */
  public  String  zip_code;

  /** 
   * <em>address_line1</em>カラムの値 
   */
  public  String  address_line1;

  /** 
   * <em>address_line1_kana</em>カラムの値 
   */
  public  String  address_line1_kana;

  /** 
   * <em>address_line2</em>カラムの値 
   */
  public  String  address_line2;

  /** 
   * <em>address_line2_kana</em>カラムの値 
   */
  public  String  address_line2_kana;

  /** 
   * <em>address_line3</em>カラムの値 
   */
  public  String  address_line3;

  /** 
   * <em>address_line3_kana</em>カラムの値 
   */
  public  String  address_line3_kana;

  /** 
   * <em>telephone</em>カラムの値 
   */
  public  String  telephone;

  /** 
   * <em>contact_address</em>カラムの値 
   */
  public  String  contact_address;

  /** 
   * <em>contact_telephone</em>カラムの値 
   */
  public  String  contact_telephone;

  /** 
   * <em>ex_branch_name</em>カラムの値 
   */
  public  String  ex_branch_name;

  /** 
   * <em>ex_account_code</em>カラムの値 
   */
  public  String  ex_account_code;

  /** 
   * <em>occupation_div</em>カラムの値 
   */
  public  String  occupation_div;

  /** 
   * <em>era_born</em>カラムの値 
   */
  public  String  era_born;

  /** 
   * <em>born_date</em>カラムの値 
   */
  public  String  born_date;

  /** 
   * <em>sex</em>カラムの値 
   */
  public  String  sex;

  /** 
   * <em>document</em>カラムの値 
   */
  public  String  document;

  /** 
   * <em>unknown_address</em>カラムの値 
   */
  public  String  unknown_address;

  /** 
   * <em>report</em>カラムの値 
   */
  public  String  report;

  /** 
   * <em>resident</em>カラムの値 
   */
  public  String  resident;

  /** 
   * <em>taxation_div</em>カラムの値 
   */
  public  String  taxation_div;

  /** 
   * <em>forign_taxation_div</em>カラムの値 
   */
  public  String  forign_taxation_div;

  /** 
   * <em>account_div</em>カラムの値 
   */
  public  String  account_div;

  /** 
   * <em>account_open_div1</em>カラムの値 
   */
  public  String  account_open_div1;

  /** 
   * <em>account_open_div2</em>カラムの値 
   */
  public  String  account_open_div2;

  /** 
   * <em>account_open_div3</em>カラムの値 
   */
  public  String  account_open_div3;

  /** 
   * <em>account_open_div4</em>カラムの値 
   */
  public  String  account_open_div4;

  /** 
   * <em>account_open_div5</em>カラムの値 
   */
  public  String  account_open_div5;

  /** 
   * <em>account_open_div6</em>カラムの値 
   */
  public  String  account_open_div6;

  /** 
   * <em>account_open_div7</em>カラムの値 
   */
  public  String  account_open_div7;

  /** 
   * <em>account_open_div8</em>カラムの値 
   */
  public  String  account_open_div8;

  /** 
   * <em>account_open_div9</em>カラムの値 
   */
  public  String  account_open_div9;

  /** 
   * <em>account_open_div10</em>カラムの値 
   */
  public  String  account_open_div10;

  /** 
   * <em>account_open_div11</em>カラムの値 
   */
  public  String  account_open_div11;

  /** 
   * <em>account_open_div12</em>カラムの値 
   */
  public  String  account_open_div12;

  /** 
   * <em>account_open_div13</em>カラムの値 
   */
  public  String  account_open_div13;

  /** 
   * <em>taxation_appl_div</em>カラムの値 
   */
  public  String  taxation_appl_div;

  /** 
   * <em>taxation_tran_div</em>カラムの値 
   */
  public  String  taxation_tran_div;

  /** 
   * <em>send_div</em>カラムの値 
   */
  public  String  send_div;

  /** 
   * <em>trust_via_div</em>カラムの値 
   */
  public  String  trust_via_div;

  /** 
   * <em>correct_div1</em>カラムの値 
   */
  public  String  correct_div1;

  /** 
   * <em>correct_div2</em>カラムの値 
   */
  public  String  correct_div2;

  /** 
   * <em>correct_div3</em>カラムの値 
   */
  public  String  correct_div3;

  /** 
   * <em>correct_div4</em>カラムの値 
   */
  public  String  correct_div4;

  /** 
   * <em>correct_div5</em>カラムの値 
   */
  public  String  correct_div5;

  /** 
   * <em>ifo_acc_open_div_tokyo</em>カラムの値 
   */
  public  String  ifo_acc_open_div_tokyo;

  /** 
   * <em>ifo_acc_open_div_osaka</em>カラムの値 
   */
  public  String  ifo_acc_open_div_osaka;

  /** 
   * <em>ifo_acc_open_div_nagoya</em>カラムの値 
   */
  public  String  ifo_acc_open_div_nagoya;

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
   * <em>other_contact_telephone</em>カラムの値 
   */
  public  String  other_contact_telephone;

  /** 
   * <em>brokerage_trader_code</em>カラムの値 
   */
  public  String  brokerage_trader_code;

  /** 
   * <em>proam_div</em>カラムの値 
   */
  public  String  proam_div;

  /** 
   * <em>foreigner_div_broadcast</em>カラムの値 
   */
  public  String  foreigner_div_broadcast;

  /** 
   * <em>foreigner_div_aviation</em>カラムの値 
   */
  public  String  foreigner_div_aviation;

  /** 
   * <em>foreigner_div_ntt</em>カラムの値 
   */
  public  String  foreigner_div_ntt;

  /** 
   * <em>dividend_transfer_div</em>カラムの値 
   */
  public  String  dividend_transfer_div;

  /** 
   * <em>agent_div_permanent</em>カラムの値 
   */
  public  String  agent_div_permanent;

  /** 
   * <em>agent_div_legal</em>カラムの値 
   */
  public  String  agent_div_legal;

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
  private boolean account_name_kana_is_set = false;
  private boolean account_name_kana_is_modified = false;
  private boolean account_name_is_set = false;
  private boolean account_name_is_modified = false;
  private boolean zip_code_is_set = false;
  private boolean zip_code_is_modified = false;
  private boolean address_line1_is_set = false;
  private boolean address_line1_is_modified = false;
  private boolean address_line1_kana_is_set = false;
  private boolean address_line1_kana_is_modified = false;
  private boolean address_line2_is_set = false;
  private boolean address_line2_is_modified = false;
  private boolean address_line2_kana_is_set = false;
  private boolean address_line2_kana_is_modified = false;
  private boolean address_line3_is_set = false;
  private boolean address_line3_is_modified = false;
  private boolean address_line3_kana_is_set = false;
  private boolean address_line3_kana_is_modified = false;
  private boolean telephone_is_set = false;
  private boolean telephone_is_modified = false;
  private boolean contact_address_is_set = false;
  private boolean contact_address_is_modified = false;
  private boolean contact_telephone_is_set = false;
  private boolean contact_telephone_is_modified = false;
  private boolean ex_branch_name_is_set = false;
  private boolean ex_branch_name_is_modified = false;
  private boolean ex_account_code_is_set = false;
  private boolean ex_account_code_is_modified = false;
  private boolean occupation_div_is_set = false;
  private boolean occupation_div_is_modified = false;
  private boolean era_born_is_set = false;
  private boolean era_born_is_modified = false;
  private boolean born_date_is_set = false;
  private boolean born_date_is_modified = false;
  private boolean sex_is_set = false;
  private boolean sex_is_modified = false;
  private boolean document_is_set = false;
  private boolean document_is_modified = false;
  private boolean unknown_address_is_set = false;
  private boolean unknown_address_is_modified = false;
  private boolean report_is_set = false;
  private boolean report_is_modified = false;
  private boolean resident_is_set = false;
  private boolean resident_is_modified = false;
  private boolean taxation_div_is_set = false;
  private boolean taxation_div_is_modified = false;
  private boolean forign_taxation_div_is_set = false;
  private boolean forign_taxation_div_is_modified = false;
  private boolean account_div_is_set = false;
  private boolean account_div_is_modified = false;
  private boolean account_open_div1_is_set = false;
  private boolean account_open_div1_is_modified = false;
  private boolean account_open_div2_is_set = false;
  private boolean account_open_div2_is_modified = false;
  private boolean account_open_div3_is_set = false;
  private boolean account_open_div3_is_modified = false;
  private boolean account_open_div4_is_set = false;
  private boolean account_open_div4_is_modified = false;
  private boolean account_open_div5_is_set = false;
  private boolean account_open_div5_is_modified = false;
  private boolean account_open_div6_is_set = false;
  private boolean account_open_div6_is_modified = false;
  private boolean account_open_div7_is_set = false;
  private boolean account_open_div7_is_modified = false;
  private boolean account_open_div8_is_set = false;
  private boolean account_open_div8_is_modified = false;
  private boolean account_open_div9_is_set = false;
  private boolean account_open_div9_is_modified = false;
  private boolean account_open_div10_is_set = false;
  private boolean account_open_div10_is_modified = false;
  private boolean account_open_div11_is_set = false;
  private boolean account_open_div11_is_modified = false;
  private boolean account_open_div12_is_set = false;
  private boolean account_open_div12_is_modified = false;
  private boolean account_open_div13_is_set = false;
  private boolean account_open_div13_is_modified = false;
  private boolean taxation_appl_div_is_set = false;
  private boolean taxation_appl_div_is_modified = false;
  private boolean taxation_tran_div_is_set = false;
  private boolean taxation_tran_div_is_modified = false;
  private boolean send_div_is_set = false;
  private boolean send_div_is_modified = false;
  private boolean trust_via_div_is_set = false;
  private boolean trust_via_div_is_modified = false;
  private boolean correct_div1_is_set = false;
  private boolean correct_div1_is_modified = false;
  private boolean correct_div2_is_set = false;
  private boolean correct_div2_is_modified = false;
  private boolean correct_div3_is_set = false;
  private boolean correct_div3_is_modified = false;
  private boolean correct_div4_is_set = false;
  private boolean correct_div4_is_modified = false;
  private boolean correct_div5_is_set = false;
  private boolean correct_div5_is_modified = false;
  private boolean ifo_acc_open_div_tokyo_is_set = false;
  private boolean ifo_acc_open_div_tokyo_is_modified = false;
  private boolean ifo_acc_open_div_osaka_is_set = false;
  private boolean ifo_acc_open_div_osaka_is_modified = false;
  private boolean ifo_acc_open_div_nagoya_is_set = false;
  private boolean ifo_acc_open_div_nagoya_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean send_timestamp_is_set = false;
  private boolean send_timestamp_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean other_contact_telephone_is_set = false;
  private boolean other_contact_telephone_is_modified = false;
  private boolean brokerage_trader_code_is_set = false;
  private boolean brokerage_trader_code_is_modified = false;
  private boolean proam_div_is_set = false;
  private boolean proam_div_is_modified = false;
  private boolean foreigner_div_broadcast_is_set = false;
  private boolean foreigner_div_broadcast_is_modified = false;
  private boolean foreigner_div_aviation_is_set = false;
  private boolean foreigner_div_aviation_is_modified = false;
  private boolean foreigner_div_ntt_is_set = false;
  private boolean foreigner_div_ntt_is_modified = false;
  private boolean dividend_transfer_div_is_set = false;
  private boolean dividend_transfer_div_is_modified = false;
  private boolean agent_div_permanent_is_set = false;
  private boolean agent_div_permanent_is_modified = false;
  private boolean agent_div_legal_is_set = false;
  private boolean agent_div_legal_is_modified = false;


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
      + "," + "account_name_kana=" +account_name_kana
      + "," + "account_name=" +account_name
      + "," + "zip_code=" +zip_code
      + "," + "address_line1=" +address_line1
      + "," + "address_line1_kana=" +address_line1_kana
      + "," + "address_line2=" +address_line2
      + "," + "address_line2_kana=" +address_line2_kana
      + "," + "address_line3=" +address_line3
      + "," + "address_line3_kana=" +address_line3_kana
      + "," + "telephone=" +telephone
      + "," + "contact_address=" +contact_address
      + "," + "contact_telephone=" +contact_telephone
      + "," + "ex_branch_name=" +ex_branch_name
      + "," + "ex_account_code=" +ex_account_code
      + "," + "occupation_div=" +occupation_div
      + "," + "era_born=" +era_born
      + "," + "born_date=" +born_date
      + "," + "sex=" +sex
      + "," + "document=" +document
      + "," + "unknown_address=" +unknown_address
      + "," + "report=" +report
      + "," + "resident=" +resident
      + "," + "taxation_div=" +taxation_div
      + "," + "forign_taxation_div=" +forign_taxation_div
      + "," + "account_div=" +account_div
      + "," + "account_open_div1=" +account_open_div1
      + "," + "account_open_div2=" +account_open_div2
      + "," + "account_open_div3=" +account_open_div3
      + "," + "account_open_div4=" +account_open_div4
      + "," + "account_open_div5=" +account_open_div5
      + "," + "account_open_div6=" +account_open_div6
      + "," + "account_open_div7=" +account_open_div7
      + "," + "account_open_div8=" +account_open_div8
      + "," + "account_open_div9=" +account_open_div9
      + "," + "account_open_div10=" +account_open_div10
      + "," + "account_open_div11=" +account_open_div11
      + "," + "account_open_div12=" +account_open_div12
      + "," + "account_open_div13=" +account_open_div13
      + "," + "taxation_appl_div=" +taxation_appl_div
      + "," + "taxation_tran_div=" +taxation_tran_div
      + "," + "send_div=" +send_div
      + "," + "trust_via_div=" +trust_via_div
      + "," + "correct_div1=" +correct_div1
      + "," + "correct_div2=" +correct_div2
      + "," + "correct_div3=" +correct_div3
      + "," + "correct_div4=" +correct_div4
      + "," + "correct_div5=" +correct_div5
      + "," + "ifo_acc_open_div_tokyo=" +ifo_acc_open_div_tokyo
      + "," + "ifo_acc_open_div_osaka=" +ifo_acc_open_div_osaka
      + "," + "ifo_acc_open_div_nagoya=" +ifo_acc_open_div_nagoya
      + "," + "status=" +status
      + "," + "send_timestamp=" +send_timestamp
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "other_contact_telephone=" +other_contact_telephone
      + "," + "brokerage_trader_code=" +brokerage_trader_code
      + "," + "proam_div=" +proam_div
      + "," + "foreigner_div_broadcast=" +foreigner_div_broadcast
      + "," + "foreigner_div_aviation=" +foreigner_div_aviation
      + "," + "foreigner_div_ntt=" +foreigner_div_ntt
      + "," + "dividend_transfer_div=" +dividend_transfer_div
      + "," + "agent_div_permanent=" +agent_div_permanent
      + "," + "agent_div_legal=" +agent_div_legal
      + "}";
  }


  /** 
   * 値が未設定のHostAccRegVoucherParamsオブジェクトを作成します。 
   */
  public HostAccRegVoucherParams() {
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
   * 指定のHostAccRegVoucherRowオブジェクトの値を利用してHostAccRegVoucherParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するHostAccRegVoucherRowオブジェクト 
   */
  public HostAccRegVoucherParams( HostAccRegVoucherRow p_row )
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
    account_name_kana = p_row.getAccountNameKana();
    account_name_kana_is_set = p_row.getAccountNameKanaIsSet();
    account_name_kana_is_modified = p_row.getAccountNameKanaIsModified();
    account_name = p_row.getAccountName();
    account_name_is_set = p_row.getAccountNameIsSet();
    account_name_is_modified = p_row.getAccountNameIsModified();
    zip_code = p_row.getZipCode();
    zip_code_is_set = p_row.getZipCodeIsSet();
    zip_code_is_modified = p_row.getZipCodeIsModified();
    address_line1 = p_row.getAddressLine1();
    address_line1_is_set = p_row.getAddressLine1IsSet();
    address_line1_is_modified = p_row.getAddressLine1IsModified();
    address_line1_kana = p_row.getAddressLine1Kana();
    address_line1_kana_is_set = p_row.getAddressLine1KanaIsSet();
    address_line1_kana_is_modified = p_row.getAddressLine1KanaIsModified();
    address_line2 = p_row.getAddressLine2();
    address_line2_is_set = p_row.getAddressLine2IsSet();
    address_line2_is_modified = p_row.getAddressLine2IsModified();
    address_line2_kana = p_row.getAddressLine2Kana();
    address_line2_kana_is_set = p_row.getAddressLine2KanaIsSet();
    address_line2_kana_is_modified = p_row.getAddressLine2KanaIsModified();
    address_line3 = p_row.getAddressLine3();
    address_line3_is_set = p_row.getAddressLine3IsSet();
    address_line3_is_modified = p_row.getAddressLine3IsModified();
    address_line3_kana = p_row.getAddressLine3Kana();
    address_line3_kana_is_set = p_row.getAddressLine3KanaIsSet();
    address_line3_kana_is_modified = p_row.getAddressLine3KanaIsModified();
    telephone = p_row.getTelephone();
    telephone_is_set = p_row.getTelephoneIsSet();
    telephone_is_modified = p_row.getTelephoneIsModified();
    contact_address = p_row.getContactAddress();
    contact_address_is_set = p_row.getContactAddressIsSet();
    contact_address_is_modified = p_row.getContactAddressIsModified();
    contact_telephone = p_row.getContactTelephone();
    contact_telephone_is_set = p_row.getContactTelephoneIsSet();
    contact_telephone_is_modified = p_row.getContactTelephoneIsModified();
    ex_branch_name = p_row.getExBranchName();
    ex_branch_name_is_set = p_row.getExBranchNameIsSet();
    ex_branch_name_is_modified = p_row.getExBranchNameIsModified();
    ex_account_code = p_row.getExAccountCode();
    ex_account_code_is_set = p_row.getExAccountCodeIsSet();
    ex_account_code_is_modified = p_row.getExAccountCodeIsModified();
    occupation_div = p_row.getOccupationDiv();
    occupation_div_is_set = p_row.getOccupationDivIsSet();
    occupation_div_is_modified = p_row.getOccupationDivIsModified();
    era_born = p_row.getEraBorn();
    era_born_is_set = p_row.getEraBornIsSet();
    era_born_is_modified = p_row.getEraBornIsModified();
    born_date = p_row.getBornDate();
    born_date_is_set = p_row.getBornDateIsSet();
    born_date_is_modified = p_row.getBornDateIsModified();
    sex = p_row.getSex();
    sex_is_set = p_row.getSexIsSet();
    sex_is_modified = p_row.getSexIsModified();
    document = p_row.getDocument();
    document_is_set = p_row.getDocumentIsSet();
    document_is_modified = p_row.getDocumentIsModified();
    unknown_address = p_row.getUnknownAddress();
    unknown_address_is_set = p_row.getUnknownAddressIsSet();
    unknown_address_is_modified = p_row.getUnknownAddressIsModified();
    report = p_row.getReport();
    report_is_set = p_row.getReportIsSet();
    report_is_modified = p_row.getReportIsModified();
    resident = p_row.getResident();
    resident_is_set = p_row.getResidentIsSet();
    resident_is_modified = p_row.getResidentIsModified();
    taxation_div = p_row.getTaxationDiv();
    taxation_div_is_set = p_row.getTaxationDivIsSet();
    taxation_div_is_modified = p_row.getTaxationDivIsModified();
    forign_taxation_div = p_row.getForignTaxationDiv();
    forign_taxation_div_is_set = p_row.getForignTaxationDivIsSet();
    forign_taxation_div_is_modified = p_row.getForignTaxationDivIsModified();
    account_div = p_row.getAccountDiv();
    account_div_is_set = p_row.getAccountDivIsSet();
    account_div_is_modified = p_row.getAccountDivIsModified();
    account_open_div1 = p_row.getAccountOpenDiv1();
    account_open_div1_is_set = p_row.getAccountOpenDiv1IsSet();
    account_open_div1_is_modified = p_row.getAccountOpenDiv1IsModified();
    account_open_div2 = p_row.getAccountOpenDiv2();
    account_open_div2_is_set = p_row.getAccountOpenDiv2IsSet();
    account_open_div2_is_modified = p_row.getAccountOpenDiv2IsModified();
    account_open_div3 = p_row.getAccountOpenDiv3();
    account_open_div3_is_set = p_row.getAccountOpenDiv3IsSet();
    account_open_div3_is_modified = p_row.getAccountOpenDiv3IsModified();
    account_open_div4 = p_row.getAccountOpenDiv4();
    account_open_div4_is_set = p_row.getAccountOpenDiv4IsSet();
    account_open_div4_is_modified = p_row.getAccountOpenDiv4IsModified();
    account_open_div5 = p_row.getAccountOpenDiv5();
    account_open_div5_is_set = p_row.getAccountOpenDiv5IsSet();
    account_open_div5_is_modified = p_row.getAccountOpenDiv5IsModified();
    account_open_div6 = p_row.getAccountOpenDiv6();
    account_open_div6_is_set = p_row.getAccountOpenDiv6IsSet();
    account_open_div6_is_modified = p_row.getAccountOpenDiv6IsModified();
    account_open_div7 = p_row.getAccountOpenDiv7();
    account_open_div7_is_set = p_row.getAccountOpenDiv7IsSet();
    account_open_div7_is_modified = p_row.getAccountOpenDiv7IsModified();
    account_open_div8 = p_row.getAccountOpenDiv8();
    account_open_div8_is_set = p_row.getAccountOpenDiv8IsSet();
    account_open_div8_is_modified = p_row.getAccountOpenDiv8IsModified();
    account_open_div9 = p_row.getAccountOpenDiv9();
    account_open_div9_is_set = p_row.getAccountOpenDiv9IsSet();
    account_open_div9_is_modified = p_row.getAccountOpenDiv9IsModified();
    account_open_div10 = p_row.getAccountOpenDiv10();
    account_open_div10_is_set = p_row.getAccountOpenDiv10IsSet();
    account_open_div10_is_modified = p_row.getAccountOpenDiv10IsModified();
    account_open_div11 = p_row.getAccountOpenDiv11();
    account_open_div11_is_set = p_row.getAccountOpenDiv11IsSet();
    account_open_div11_is_modified = p_row.getAccountOpenDiv11IsModified();
    account_open_div12 = p_row.getAccountOpenDiv12();
    account_open_div12_is_set = p_row.getAccountOpenDiv12IsSet();
    account_open_div12_is_modified = p_row.getAccountOpenDiv12IsModified();
    account_open_div13 = p_row.getAccountOpenDiv13();
    account_open_div13_is_set = p_row.getAccountOpenDiv13IsSet();
    account_open_div13_is_modified = p_row.getAccountOpenDiv13IsModified();
    taxation_appl_div = p_row.getTaxationApplDiv();
    taxation_appl_div_is_set = p_row.getTaxationApplDivIsSet();
    taxation_appl_div_is_modified = p_row.getTaxationApplDivIsModified();
    taxation_tran_div = p_row.getTaxationTranDiv();
    taxation_tran_div_is_set = p_row.getTaxationTranDivIsSet();
    taxation_tran_div_is_modified = p_row.getTaxationTranDivIsModified();
    send_div = p_row.getSendDiv();
    send_div_is_set = p_row.getSendDivIsSet();
    send_div_is_modified = p_row.getSendDivIsModified();
    trust_via_div = p_row.getTrustViaDiv();
    trust_via_div_is_set = p_row.getTrustViaDivIsSet();
    trust_via_div_is_modified = p_row.getTrustViaDivIsModified();
    correct_div1 = p_row.getCorrectDiv1();
    correct_div1_is_set = p_row.getCorrectDiv1IsSet();
    correct_div1_is_modified = p_row.getCorrectDiv1IsModified();
    correct_div2 = p_row.getCorrectDiv2();
    correct_div2_is_set = p_row.getCorrectDiv2IsSet();
    correct_div2_is_modified = p_row.getCorrectDiv2IsModified();
    correct_div3 = p_row.getCorrectDiv3();
    correct_div3_is_set = p_row.getCorrectDiv3IsSet();
    correct_div3_is_modified = p_row.getCorrectDiv3IsModified();
    correct_div4 = p_row.getCorrectDiv4();
    correct_div4_is_set = p_row.getCorrectDiv4IsSet();
    correct_div4_is_modified = p_row.getCorrectDiv4IsModified();
    correct_div5 = p_row.getCorrectDiv5();
    correct_div5_is_set = p_row.getCorrectDiv5IsSet();
    correct_div5_is_modified = p_row.getCorrectDiv5IsModified();
    ifo_acc_open_div_tokyo = p_row.getIfoAccOpenDivTokyo();
    ifo_acc_open_div_tokyo_is_set = p_row.getIfoAccOpenDivTokyoIsSet();
    ifo_acc_open_div_tokyo_is_modified = p_row.getIfoAccOpenDivTokyoIsModified();
    ifo_acc_open_div_osaka = p_row.getIfoAccOpenDivOsaka();
    ifo_acc_open_div_osaka_is_set = p_row.getIfoAccOpenDivOsakaIsSet();
    ifo_acc_open_div_osaka_is_modified = p_row.getIfoAccOpenDivOsakaIsModified();
    ifo_acc_open_div_nagoya = p_row.getIfoAccOpenDivNagoya();
    ifo_acc_open_div_nagoya_is_set = p_row.getIfoAccOpenDivNagoyaIsSet();
    ifo_acc_open_div_nagoya_is_modified = p_row.getIfoAccOpenDivNagoyaIsModified();
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
    other_contact_telephone = p_row.getOtherContactTelephone();
    other_contact_telephone_is_set = p_row.getOtherContactTelephoneIsSet();
    other_contact_telephone_is_modified = p_row.getOtherContactTelephoneIsModified();
    brokerage_trader_code = p_row.getBrokerageTraderCode();
    brokerage_trader_code_is_set = p_row.getBrokerageTraderCodeIsSet();
    brokerage_trader_code_is_modified = p_row.getBrokerageTraderCodeIsModified();
    proam_div = p_row.getProamDiv();
    proam_div_is_set = p_row.getProamDivIsSet();
    proam_div_is_modified = p_row.getProamDivIsModified();
    foreigner_div_broadcast = p_row.getForeignerDivBroadcast();
    foreigner_div_broadcast_is_set = p_row.getForeignerDivBroadcastIsSet();
    foreigner_div_broadcast_is_modified = p_row.getForeignerDivBroadcastIsModified();
    foreigner_div_aviation = p_row.getForeignerDivAviation();
    foreigner_div_aviation_is_set = p_row.getForeignerDivAviationIsSet();
    foreigner_div_aviation_is_modified = p_row.getForeignerDivAviationIsModified();
    foreigner_div_ntt = p_row.getForeignerDivNtt();
    foreigner_div_ntt_is_set = p_row.getForeignerDivNttIsSet();
    foreigner_div_ntt_is_modified = p_row.getForeignerDivNttIsModified();
    dividend_transfer_div = p_row.getDividendTransferDiv();
    dividend_transfer_div_is_set = p_row.getDividendTransferDivIsSet();
    dividend_transfer_div_is_modified = p_row.getDividendTransferDivIsModified();
    agent_div_permanent = p_row.getAgentDivPermanent();
    agent_div_permanent_is_set = p_row.getAgentDivPermanentIsSet();
    agent_div_permanent_is_modified = p_row.getAgentDivPermanentIsModified();
    agent_div_legal = p_row.getAgentDivLegal();
    agent_div_legal_is_set = p_row.getAgentDivLegalIsSet();
    agent_div_legal_is_modified = p_row.getAgentDivLegalIsModified();
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
      account_name_kana_is_set = true;
      account_name_kana_is_modified = true;
      account_name_is_set = true;
      account_name_is_modified = true;
      zip_code_is_set = true;
      zip_code_is_modified = true;
      address_line1_is_set = true;
      address_line1_is_modified = true;
      address_line1_kana_is_set = true;
      address_line1_kana_is_modified = true;
      address_line2_is_set = true;
      address_line2_is_modified = true;
      address_line2_kana_is_set = true;
      address_line2_kana_is_modified = true;
      address_line3_is_set = true;
      address_line3_is_modified = true;
      address_line3_kana_is_set = true;
      address_line3_kana_is_modified = true;
      telephone_is_set = true;
      telephone_is_modified = true;
      contact_address_is_set = true;
      contact_address_is_modified = true;
      contact_telephone_is_set = true;
      contact_telephone_is_modified = true;
      ex_branch_name_is_set = true;
      ex_branch_name_is_modified = true;
      ex_account_code_is_set = true;
      ex_account_code_is_modified = true;
      occupation_div_is_set = true;
      occupation_div_is_modified = true;
      era_born_is_set = true;
      era_born_is_modified = true;
      born_date_is_set = true;
      born_date_is_modified = true;
      sex_is_set = true;
      sex_is_modified = true;
      document_is_set = true;
      document_is_modified = true;
      unknown_address_is_set = true;
      unknown_address_is_modified = true;
      report_is_set = true;
      report_is_modified = true;
      resident_is_set = true;
      resident_is_modified = true;
      taxation_div_is_set = true;
      taxation_div_is_modified = true;
      forign_taxation_div_is_set = true;
      forign_taxation_div_is_modified = true;
      account_div_is_set = true;
      account_div_is_modified = true;
      account_open_div1_is_set = true;
      account_open_div1_is_modified = true;
      account_open_div2_is_set = true;
      account_open_div2_is_modified = true;
      account_open_div3_is_set = true;
      account_open_div3_is_modified = true;
      account_open_div4_is_set = true;
      account_open_div4_is_modified = true;
      account_open_div5_is_set = true;
      account_open_div5_is_modified = true;
      account_open_div6_is_set = true;
      account_open_div6_is_modified = true;
      account_open_div7_is_set = true;
      account_open_div7_is_modified = true;
      account_open_div8_is_set = true;
      account_open_div8_is_modified = true;
      account_open_div9_is_set = true;
      account_open_div9_is_modified = true;
      account_open_div10_is_set = true;
      account_open_div10_is_modified = true;
      account_open_div11_is_set = true;
      account_open_div11_is_modified = true;
      account_open_div12_is_set = true;
      account_open_div12_is_modified = true;
      account_open_div13_is_set = true;
      account_open_div13_is_modified = true;
      taxation_appl_div_is_set = true;
      taxation_appl_div_is_modified = true;
      taxation_tran_div_is_set = true;
      taxation_tran_div_is_modified = true;
      send_div_is_set = true;
      send_div_is_modified = true;
      trust_via_div_is_set = true;
      trust_via_div_is_modified = true;
      correct_div1_is_set = true;
      correct_div1_is_modified = true;
      correct_div2_is_set = true;
      correct_div2_is_modified = true;
      correct_div3_is_set = true;
      correct_div3_is_modified = true;
      correct_div4_is_set = true;
      correct_div4_is_modified = true;
      correct_div5_is_set = true;
      correct_div5_is_modified = true;
      ifo_acc_open_div_tokyo_is_set = true;
      ifo_acc_open_div_tokyo_is_modified = true;
      ifo_acc_open_div_osaka_is_set = true;
      ifo_acc_open_div_osaka_is_modified = true;
      ifo_acc_open_div_nagoya_is_set = true;
      ifo_acc_open_div_nagoya_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      send_timestamp_is_set = true;
      send_timestamp_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      other_contact_telephone_is_set = true;
      other_contact_telephone_is_modified = true;
      brokerage_trader_code_is_set = true;
      brokerage_trader_code_is_modified = true;
      proam_div_is_set = true;
      proam_div_is_modified = true;
      foreigner_div_broadcast_is_set = true;
      foreigner_div_broadcast_is_modified = true;
      foreigner_div_aviation_is_set = true;
      foreigner_div_aviation_is_modified = true;
      foreigner_div_ntt_is_set = true;
      foreigner_div_ntt_is_modified = true;
      dividend_transfer_div_is_set = true;
      dividend_transfer_div_is_modified = true;
      agent_div_permanent_is_set = true;
      agent_div_permanent_is_modified = true;
      agent_div_legal_is_set = true;
      agent_div_legal_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof HostAccRegVoucherRow ) )
       return false;
    return fieldsEqual( (HostAccRegVoucherRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のHostAccRegVoucherRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( HostAccRegVoucherRow row )
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
    if ( account_name_kana == null ) {
      if ( row.getAccountNameKana() != null )
        return false;
    } else if ( !account_name_kana.equals( row.getAccountNameKana() ) ) {
        return false;
    }
    if ( account_name == null ) {
      if ( row.getAccountName() != null )
        return false;
    } else if ( !account_name.equals( row.getAccountName() ) ) {
        return false;
    }
    if ( zip_code == null ) {
      if ( row.getZipCode() != null )
        return false;
    } else if ( !zip_code.equals( row.getZipCode() ) ) {
        return false;
    }
    if ( address_line1 == null ) {
      if ( row.getAddressLine1() != null )
        return false;
    } else if ( !address_line1.equals( row.getAddressLine1() ) ) {
        return false;
    }
    if ( address_line1_kana == null ) {
      if ( row.getAddressLine1Kana() != null )
        return false;
    } else if ( !address_line1_kana.equals( row.getAddressLine1Kana() ) ) {
        return false;
    }
    if ( address_line2 == null ) {
      if ( row.getAddressLine2() != null )
        return false;
    } else if ( !address_line2.equals( row.getAddressLine2() ) ) {
        return false;
    }
    if ( address_line2_kana == null ) {
      if ( row.getAddressLine2Kana() != null )
        return false;
    } else if ( !address_line2_kana.equals( row.getAddressLine2Kana() ) ) {
        return false;
    }
    if ( address_line3 == null ) {
      if ( row.getAddressLine3() != null )
        return false;
    } else if ( !address_line3.equals( row.getAddressLine3() ) ) {
        return false;
    }
    if ( address_line3_kana == null ) {
      if ( row.getAddressLine3Kana() != null )
        return false;
    } else if ( !address_line3_kana.equals( row.getAddressLine3Kana() ) ) {
        return false;
    }
    if ( telephone == null ) {
      if ( row.getTelephone() != null )
        return false;
    } else if ( !telephone.equals( row.getTelephone() ) ) {
        return false;
    }
    if ( contact_address == null ) {
      if ( row.getContactAddress() != null )
        return false;
    } else if ( !contact_address.equals( row.getContactAddress() ) ) {
        return false;
    }
    if ( contact_telephone == null ) {
      if ( row.getContactTelephone() != null )
        return false;
    } else if ( !contact_telephone.equals( row.getContactTelephone() ) ) {
        return false;
    }
    if ( ex_branch_name == null ) {
      if ( row.getExBranchName() != null )
        return false;
    } else if ( !ex_branch_name.equals( row.getExBranchName() ) ) {
        return false;
    }
    if ( ex_account_code == null ) {
      if ( row.getExAccountCode() != null )
        return false;
    } else if ( !ex_account_code.equals( row.getExAccountCode() ) ) {
        return false;
    }
    if ( occupation_div == null ) {
      if ( row.getOccupationDiv() != null )
        return false;
    } else if ( !occupation_div.equals( row.getOccupationDiv() ) ) {
        return false;
    }
    if ( era_born == null ) {
      if ( row.getEraBorn() != null )
        return false;
    } else if ( !era_born.equals( row.getEraBorn() ) ) {
        return false;
    }
    if ( born_date == null ) {
      if ( row.getBornDate() != null )
        return false;
    } else if ( !born_date.equals( row.getBornDate() ) ) {
        return false;
    }
    if ( sex == null ) {
      if ( row.getSex() != null )
        return false;
    } else if ( !sex.equals( row.getSex() ) ) {
        return false;
    }
    if ( document == null ) {
      if ( row.getDocument() != null )
        return false;
    } else if ( !document.equals( row.getDocument() ) ) {
        return false;
    }
    if ( unknown_address == null ) {
      if ( row.getUnknownAddress() != null )
        return false;
    } else if ( !unknown_address.equals( row.getUnknownAddress() ) ) {
        return false;
    }
    if ( report == null ) {
      if ( row.getReport() != null )
        return false;
    } else if ( !report.equals( row.getReport() ) ) {
        return false;
    }
    if ( resident == null ) {
      if ( row.getResident() != null )
        return false;
    } else if ( !resident.equals( row.getResident() ) ) {
        return false;
    }
    if ( taxation_div == null ) {
      if ( row.getTaxationDiv() != null )
        return false;
    } else if ( !taxation_div.equals( row.getTaxationDiv() ) ) {
        return false;
    }
    if ( forign_taxation_div == null ) {
      if ( row.getForignTaxationDiv() != null )
        return false;
    } else if ( !forign_taxation_div.equals( row.getForignTaxationDiv() ) ) {
        return false;
    }
    if ( account_div == null ) {
      if ( row.getAccountDiv() != null )
        return false;
    } else if ( !account_div.equals( row.getAccountDiv() ) ) {
        return false;
    }
    if ( account_open_div1 == null ) {
      if ( row.getAccountOpenDiv1() != null )
        return false;
    } else if ( !account_open_div1.equals( row.getAccountOpenDiv1() ) ) {
        return false;
    }
    if ( account_open_div2 == null ) {
      if ( row.getAccountOpenDiv2() != null )
        return false;
    } else if ( !account_open_div2.equals( row.getAccountOpenDiv2() ) ) {
        return false;
    }
    if ( account_open_div3 == null ) {
      if ( row.getAccountOpenDiv3() != null )
        return false;
    } else if ( !account_open_div3.equals( row.getAccountOpenDiv3() ) ) {
        return false;
    }
    if ( account_open_div4 == null ) {
      if ( row.getAccountOpenDiv4() != null )
        return false;
    } else if ( !account_open_div4.equals( row.getAccountOpenDiv4() ) ) {
        return false;
    }
    if ( account_open_div5 == null ) {
      if ( row.getAccountOpenDiv5() != null )
        return false;
    } else if ( !account_open_div5.equals( row.getAccountOpenDiv5() ) ) {
        return false;
    }
    if ( account_open_div6 == null ) {
      if ( row.getAccountOpenDiv6() != null )
        return false;
    } else if ( !account_open_div6.equals( row.getAccountOpenDiv6() ) ) {
        return false;
    }
    if ( account_open_div7 == null ) {
      if ( row.getAccountOpenDiv7() != null )
        return false;
    } else if ( !account_open_div7.equals( row.getAccountOpenDiv7() ) ) {
        return false;
    }
    if ( account_open_div8 == null ) {
      if ( row.getAccountOpenDiv8() != null )
        return false;
    } else if ( !account_open_div8.equals( row.getAccountOpenDiv8() ) ) {
        return false;
    }
    if ( account_open_div9 == null ) {
      if ( row.getAccountOpenDiv9() != null )
        return false;
    } else if ( !account_open_div9.equals( row.getAccountOpenDiv9() ) ) {
        return false;
    }
    if ( account_open_div10 == null ) {
      if ( row.getAccountOpenDiv10() != null )
        return false;
    } else if ( !account_open_div10.equals( row.getAccountOpenDiv10() ) ) {
        return false;
    }
    if ( account_open_div11 == null ) {
      if ( row.getAccountOpenDiv11() != null )
        return false;
    } else if ( !account_open_div11.equals( row.getAccountOpenDiv11() ) ) {
        return false;
    }
    if ( account_open_div12 == null ) {
      if ( row.getAccountOpenDiv12() != null )
        return false;
    } else if ( !account_open_div12.equals( row.getAccountOpenDiv12() ) ) {
        return false;
    }
    if ( account_open_div13 == null ) {
      if ( row.getAccountOpenDiv13() != null )
        return false;
    } else if ( !account_open_div13.equals( row.getAccountOpenDiv13() ) ) {
        return false;
    }
    if ( taxation_appl_div == null ) {
      if ( row.getTaxationApplDiv() != null )
        return false;
    } else if ( !taxation_appl_div.equals( row.getTaxationApplDiv() ) ) {
        return false;
    }
    if ( taxation_tran_div == null ) {
      if ( row.getTaxationTranDiv() != null )
        return false;
    } else if ( !taxation_tran_div.equals( row.getTaxationTranDiv() ) ) {
        return false;
    }
    if ( send_div == null ) {
      if ( row.getSendDiv() != null )
        return false;
    } else if ( !send_div.equals( row.getSendDiv() ) ) {
        return false;
    }
    if ( trust_via_div == null ) {
      if ( row.getTrustViaDiv() != null )
        return false;
    } else if ( !trust_via_div.equals( row.getTrustViaDiv() ) ) {
        return false;
    }
    if ( correct_div1 == null ) {
      if ( row.getCorrectDiv1() != null )
        return false;
    } else if ( !correct_div1.equals( row.getCorrectDiv1() ) ) {
        return false;
    }
    if ( correct_div2 == null ) {
      if ( row.getCorrectDiv2() != null )
        return false;
    } else if ( !correct_div2.equals( row.getCorrectDiv2() ) ) {
        return false;
    }
    if ( correct_div3 == null ) {
      if ( row.getCorrectDiv3() != null )
        return false;
    } else if ( !correct_div3.equals( row.getCorrectDiv3() ) ) {
        return false;
    }
    if ( correct_div4 == null ) {
      if ( row.getCorrectDiv4() != null )
        return false;
    } else if ( !correct_div4.equals( row.getCorrectDiv4() ) ) {
        return false;
    }
    if ( correct_div5 == null ) {
      if ( row.getCorrectDiv5() != null )
        return false;
    } else if ( !correct_div5.equals( row.getCorrectDiv5() ) ) {
        return false;
    }
    if ( ifo_acc_open_div_tokyo == null ) {
      if ( row.getIfoAccOpenDivTokyo() != null )
        return false;
    } else if ( !ifo_acc_open_div_tokyo.equals( row.getIfoAccOpenDivTokyo() ) ) {
        return false;
    }
    if ( ifo_acc_open_div_osaka == null ) {
      if ( row.getIfoAccOpenDivOsaka() != null )
        return false;
    } else if ( !ifo_acc_open_div_osaka.equals( row.getIfoAccOpenDivOsaka() ) ) {
        return false;
    }
    if ( ifo_acc_open_div_nagoya == null ) {
      if ( row.getIfoAccOpenDivNagoya() != null )
        return false;
    } else if ( !ifo_acc_open_div_nagoya.equals( row.getIfoAccOpenDivNagoya() ) ) {
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
    if ( other_contact_telephone == null ) {
      if ( row.getOtherContactTelephone() != null )
        return false;
    } else if ( !other_contact_telephone.equals( row.getOtherContactTelephone() ) ) {
        return false;
    }
    if ( brokerage_trader_code == null ) {
      if ( row.getBrokerageTraderCode() != null )
        return false;
    } else if ( !brokerage_trader_code.equals( row.getBrokerageTraderCode() ) ) {
        return false;
    }
    if ( proam_div == null ) {
      if ( row.getProamDiv() != null )
        return false;
    } else if ( !proam_div.equals( row.getProamDiv() ) ) {
        return false;
    }
    if ( foreigner_div_broadcast == null ) {
      if ( row.getForeignerDivBroadcast() != null )
        return false;
    } else if ( !foreigner_div_broadcast.equals( row.getForeignerDivBroadcast() ) ) {
        return false;
    }
    if ( foreigner_div_aviation == null ) {
      if ( row.getForeignerDivAviation() != null )
        return false;
    } else if ( !foreigner_div_aviation.equals( row.getForeignerDivAviation() ) ) {
        return false;
    }
    if ( foreigner_div_ntt == null ) {
      if ( row.getForeignerDivNtt() != null )
        return false;
    } else if ( !foreigner_div_ntt.equals( row.getForeignerDivNtt() ) ) {
        return false;
    }
    if ( dividend_transfer_div == null ) {
      if ( row.getDividendTransferDiv() != null )
        return false;
    } else if ( !dividend_transfer_div.equals( row.getDividendTransferDiv() ) ) {
        return false;
    }
    if ( agent_div_permanent == null ) {
      if ( row.getAgentDivPermanent() != null )
        return false;
    } else if ( !agent_div_permanent.equals( row.getAgentDivPermanent() ) ) {
        return false;
    }
    if ( agent_div_legal == null ) {
      if ( row.getAgentDivLegal() != null )
        return false;
    } else if ( !agent_div_legal.equals( row.getAgentDivLegal() ) ) {
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
        + (account_name_kana!=null? account_name_kana.hashCode(): 0) 
        + (account_name!=null? account_name.hashCode(): 0) 
        + (zip_code!=null? zip_code.hashCode(): 0) 
        + (address_line1!=null? address_line1.hashCode(): 0) 
        + (address_line1_kana!=null? address_line1_kana.hashCode(): 0) 
        + (address_line2!=null? address_line2.hashCode(): 0) 
        + (address_line2_kana!=null? address_line2_kana.hashCode(): 0) 
        + (address_line3!=null? address_line3.hashCode(): 0) 
        + (address_line3_kana!=null? address_line3_kana.hashCode(): 0) 
        + (telephone!=null? telephone.hashCode(): 0) 
        + (contact_address!=null? contact_address.hashCode(): 0) 
        + (contact_telephone!=null? contact_telephone.hashCode(): 0) 
        + (ex_branch_name!=null? ex_branch_name.hashCode(): 0) 
        + (ex_account_code!=null? ex_account_code.hashCode(): 0) 
        + (occupation_div!=null? occupation_div.hashCode(): 0) 
        + (era_born!=null? era_born.hashCode(): 0) 
        + (born_date!=null? born_date.hashCode(): 0) 
        + (sex!=null? sex.hashCode(): 0) 
        + (document!=null? document.hashCode(): 0) 
        + (unknown_address!=null? unknown_address.hashCode(): 0) 
        + (report!=null? report.hashCode(): 0) 
        + (resident!=null? resident.hashCode(): 0) 
        + (taxation_div!=null? taxation_div.hashCode(): 0) 
        + (forign_taxation_div!=null? forign_taxation_div.hashCode(): 0) 
        + (account_div!=null? account_div.hashCode(): 0) 
        + (account_open_div1!=null? account_open_div1.hashCode(): 0) 
        + (account_open_div2!=null? account_open_div2.hashCode(): 0) 
        + (account_open_div3!=null? account_open_div3.hashCode(): 0) 
        + (account_open_div4!=null? account_open_div4.hashCode(): 0) 
        + (account_open_div5!=null? account_open_div5.hashCode(): 0) 
        + (account_open_div6!=null? account_open_div6.hashCode(): 0) 
        + (account_open_div7!=null? account_open_div7.hashCode(): 0) 
        + (account_open_div8!=null? account_open_div8.hashCode(): 0) 
        + (account_open_div9!=null? account_open_div9.hashCode(): 0) 
        + (account_open_div10!=null? account_open_div10.hashCode(): 0) 
        + (account_open_div11!=null? account_open_div11.hashCode(): 0) 
        + (account_open_div12!=null? account_open_div12.hashCode(): 0) 
        + (account_open_div13!=null? account_open_div13.hashCode(): 0) 
        + (taxation_appl_div!=null? taxation_appl_div.hashCode(): 0) 
        + (taxation_tran_div!=null? taxation_tran_div.hashCode(): 0) 
        + (send_div!=null? send_div.hashCode(): 0) 
        + (trust_via_div!=null? trust_via_div.hashCode(): 0) 
        + (correct_div1!=null? correct_div1.hashCode(): 0) 
        + (correct_div2!=null? correct_div2.hashCode(): 0) 
        + (correct_div3!=null? correct_div3.hashCode(): 0) 
        + (correct_div4!=null? correct_div4.hashCode(): 0) 
        + (correct_div5!=null? correct_div5.hashCode(): 0) 
        + (ifo_acc_open_div_tokyo!=null? ifo_acc_open_div_tokyo.hashCode(): 0) 
        + (ifo_acc_open_div_osaka!=null? ifo_acc_open_div_osaka.hashCode(): 0) 
        + (ifo_acc_open_div_nagoya!=null? ifo_acc_open_div_nagoya.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (send_timestamp!=null? send_timestamp.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (other_contact_telephone!=null? other_contact_telephone.hashCode(): 0) 
        + (brokerage_trader_code!=null? brokerage_trader_code.hashCode(): 0) 
        + (proam_div!=null? proam_div.hashCode(): 0) 
        + (foreigner_div_broadcast!=null? foreigner_div_broadcast.hashCode(): 0) 
        + (foreigner_div_aviation!=null? foreigner_div_aviation.hashCode(): 0) 
        + (foreigner_div_ntt!=null? foreigner_div_ntt.hashCode(): 0) 
        + (dividend_transfer_div!=null? dividend_transfer_div.hashCode(): 0) 
        + (agent_div_permanent!=null? agent_div_permanent.hashCode(): 0) 
        + (agent_div_legal!=null? agent_div_legal.hashCode(): 0) 
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
		if ( account_name_kana != null )
			map.put("account_name_kana",account_name_kana);
		if ( account_name != null )
			map.put("account_name",account_name);
		if ( zip_code != null )
			map.put("zip_code",zip_code);
		if ( address_line1 != null )
			map.put("address_line1",address_line1);
		if ( address_line1_kana != null )
			map.put("address_line1_kana",address_line1_kana);
		if ( address_line2 != null )
			map.put("address_line2",address_line2);
		if ( address_line2_kana != null )
			map.put("address_line2_kana",address_line2_kana);
		if ( address_line3 != null )
			map.put("address_line3",address_line3);
		if ( address_line3_kana != null )
			map.put("address_line3_kana",address_line3_kana);
		if ( telephone != null )
			map.put("telephone",telephone);
		if ( contact_address != null )
			map.put("contact_address",contact_address);
		if ( contact_telephone != null )
			map.put("contact_telephone",contact_telephone);
		if ( ex_branch_name != null )
			map.put("ex_branch_name",ex_branch_name);
		if ( ex_account_code != null )
			map.put("ex_account_code",ex_account_code);
		if ( occupation_div != null )
			map.put("occupation_div",occupation_div);
		if ( era_born != null )
			map.put("era_born",era_born);
		if ( born_date != null )
			map.put("born_date",born_date);
		if ( sex != null )
			map.put("sex",sex);
		if ( document != null )
			map.put("document",document);
		if ( unknown_address != null )
			map.put("unknown_address",unknown_address);
		if ( report != null )
			map.put("report",report);
		if ( resident != null )
			map.put("resident",resident);
		if ( taxation_div != null )
			map.put("taxation_div",taxation_div);
		if ( forign_taxation_div != null )
			map.put("forign_taxation_div",forign_taxation_div);
		if ( account_div != null )
			map.put("account_div",account_div);
		if ( account_open_div1 != null )
			map.put("account_open_div1",account_open_div1);
		if ( account_open_div2 != null )
			map.put("account_open_div2",account_open_div2);
		if ( account_open_div3 != null )
			map.put("account_open_div3",account_open_div3);
		if ( account_open_div4 != null )
			map.put("account_open_div4",account_open_div4);
		if ( account_open_div5 != null )
			map.put("account_open_div5",account_open_div5);
		if ( account_open_div6 != null )
			map.put("account_open_div6",account_open_div6);
		if ( account_open_div7 != null )
			map.put("account_open_div7",account_open_div7);
		if ( account_open_div8 != null )
			map.put("account_open_div8",account_open_div8);
		if ( account_open_div9 != null )
			map.put("account_open_div9",account_open_div9);
		if ( account_open_div10 != null )
			map.put("account_open_div10",account_open_div10);
		if ( account_open_div11 != null )
			map.put("account_open_div11",account_open_div11);
		if ( account_open_div12 != null )
			map.put("account_open_div12",account_open_div12);
		if ( account_open_div13 != null )
			map.put("account_open_div13",account_open_div13);
		if ( taxation_appl_div != null )
			map.put("taxation_appl_div",taxation_appl_div);
		if ( taxation_tran_div != null )
			map.put("taxation_tran_div",taxation_tran_div);
		if ( send_div != null )
			map.put("send_div",send_div);
		if ( trust_via_div != null )
			map.put("trust_via_div",trust_via_div);
		if ( correct_div1 != null )
			map.put("correct_div1",correct_div1);
		if ( correct_div2 != null )
			map.put("correct_div2",correct_div2);
		if ( correct_div3 != null )
			map.put("correct_div3",correct_div3);
		if ( correct_div4 != null )
			map.put("correct_div4",correct_div4);
		if ( correct_div5 != null )
			map.put("correct_div5",correct_div5);
		if ( ifo_acc_open_div_tokyo != null )
			map.put("ifo_acc_open_div_tokyo",ifo_acc_open_div_tokyo);
		if ( ifo_acc_open_div_osaka != null )
			map.put("ifo_acc_open_div_osaka",ifo_acc_open_div_osaka);
		if ( ifo_acc_open_div_nagoya != null )
			map.put("ifo_acc_open_div_nagoya",ifo_acc_open_div_nagoya);
		if ( status != null )
			map.put("status",status);
		if ( send_timestamp != null )
			map.put("send_timestamp",send_timestamp);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		if ( other_contact_telephone != null )
			map.put("other_contact_telephone",other_contact_telephone);
		if ( brokerage_trader_code != null )
			map.put("brokerage_trader_code",brokerage_trader_code);
		if ( proam_div != null )
			map.put("proam_div",proam_div);
		if ( foreigner_div_broadcast != null )
			map.put("foreigner_div_broadcast",foreigner_div_broadcast);
		if ( foreigner_div_aviation != null )
			map.put("foreigner_div_aviation",foreigner_div_aviation);
		if ( foreigner_div_ntt != null )
			map.put("foreigner_div_ntt",foreigner_div_ntt);
		if ( dividend_transfer_div != null )
			map.put("dividend_transfer_div",dividend_transfer_div);
		if ( agent_div_permanent != null )
			map.put("agent_div_permanent",agent_div_permanent);
		if ( agent_div_legal != null )
			map.put("agent_div_legal",agent_div_legal);
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
		if ( account_name_kana_is_modified )
			map.put("account_name_kana",account_name_kana);
		if ( account_name_is_modified )
			map.put("account_name",account_name);
		if ( zip_code_is_modified )
			map.put("zip_code",zip_code);
		if ( address_line1_is_modified )
			map.put("address_line1",address_line1);
		if ( address_line1_kana_is_modified )
			map.put("address_line1_kana",address_line1_kana);
		if ( address_line2_is_modified )
			map.put("address_line2",address_line2);
		if ( address_line2_kana_is_modified )
			map.put("address_line2_kana",address_line2_kana);
		if ( address_line3_is_modified )
			map.put("address_line3",address_line3);
		if ( address_line3_kana_is_modified )
			map.put("address_line3_kana",address_line3_kana);
		if ( telephone_is_modified )
			map.put("telephone",telephone);
		if ( contact_address_is_modified )
			map.put("contact_address",contact_address);
		if ( contact_telephone_is_modified )
			map.put("contact_telephone",contact_telephone);
		if ( ex_branch_name_is_modified )
			map.put("ex_branch_name",ex_branch_name);
		if ( ex_account_code_is_modified )
			map.put("ex_account_code",ex_account_code);
		if ( occupation_div_is_modified )
			map.put("occupation_div",occupation_div);
		if ( era_born_is_modified )
			map.put("era_born",era_born);
		if ( born_date_is_modified )
			map.put("born_date",born_date);
		if ( sex_is_modified )
			map.put("sex",sex);
		if ( document_is_modified )
			map.put("document",document);
		if ( unknown_address_is_modified )
			map.put("unknown_address",unknown_address);
		if ( report_is_modified )
			map.put("report",report);
		if ( resident_is_modified )
			map.put("resident",resident);
		if ( taxation_div_is_modified )
			map.put("taxation_div",taxation_div);
		if ( forign_taxation_div_is_modified )
			map.put("forign_taxation_div",forign_taxation_div);
		if ( account_div_is_modified )
			map.put("account_div",account_div);
		if ( account_open_div1_is_modified )
			map.put("account_open_div1",account_open_div1);
		if ( account_open_div2_is_modified )
			map.put("account_open_div2",account_open_div2);
		if ( account_open_div3_is_modified )
			map.put("account_open_div3",account_open_div3);
		if ( account_open_div4_is_modified )
			map.put("account_open_div4",account_open_div4);
		if ( account_open_div5_is_modified )
			map.put("account_open_div5",account_open_div5);
		if ( account_open_div6_is_modified )
			map.put("account_open_div6",account_open_div6);
		if ( account_open_div7_is_modified )
			map.put("account_open_div7",account_open_div7);
		if ( account_open_div8_is_modified )
			map.put("account_open_div8",account_open_div8);
		if ( account_open_div9_is_modified )
			map.put("account_open_div9",account_open_div9);
		if ( account_open_div10_is_modified )
			map.put("account_open_div10",account_open_div10);
		if ( account_open_div11_is_modified )
			map.put("account_open_div11",account_open_div11);
		if ( account_open_div12_is_modified )
			map.put("account_open_div12",account_open_div12);
		if ( account_open_div13_is_modified )
			map.put("account_open_div13",account_open_div13);
		if ( taxation_appl_div_is_modified )
			map.put("taxation_appl_div",taxation_appl_div);
		if ( taxation_tran_div_is_modified )
			map.put("taxation_tran_div",taxation_tran_div);
		if ( send_div_is_modified )
			map.put("send_div",send_div);
		if ( trust_via_div_is_modified )
			map.put("trust_via_div",trust_via_div);
		if ( correct_div1_is_modified )
			map.put("correct_div1",correct_div1);
		if ( correct_div2_is_modified )
			map.put("correct_div2",correct_div2);
		if ( correct_div3_is_modified )
			map.put("correct_div3",correct_div3);
		if ( correct_div4_is_modified )
			map.put("correct_div4",correct_div4);
		if ( correct_div5_is_modified )
			map.put("correct_div5",correct_div5);
		if ( ifo_acc_open_div_tokyo_is_modified )
			map.put("ifo_acc_open_div_tokyo",ifo_acc_open_div_tokyo);
		if ( ifo_acc_open_div_osaka_is_modified )
			map.put("ifo_acc_open_div_osaka",ifo_acc_open_div_osaka);
		if ( ifo_acc_open_div_nagoya_is_modified )
			map.put("ifo_acc_open_div_nagoya",ifo_acc_open_div_nagoya);
		if ( status_is_modified )
			map.put("status",status);
		if ( send_timestamp_is_modified )
			map.put("send_timestamp",send_timestamp);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( other_contact_telephone_is_modified )
			map.put("other_contact_telephone",other_contact_telephone);
		if ( brokerage_trader_code_is_modified )
			map.put("brokerage_trader_code",brokerage_trader_code);
		if ( proam_div_is_modified )
			map.put("proam_div",proam_div);
		if ( foreigner_div_broadcast_is_modified )
			map.put("foreigner_div_broadcast",foreigner_div_broadcast);
		if ( foreigner_div_aviation_is_modified )
			map.put("foreigner_div_aviation",foreigner_div_aviation);
		if ( foreigner_div_ntt_is_modified )
			map.put("foreigner_div_ntt",foreigner_div_ntt);
		if ( dividend_transfer_div_is_modified )
			map.put("dividend_transfer_div",dividend_transfer_div);
		if ( agent_div_permanent_is_modified )
			map.put("agent_div_permanent",agent_div_permanent);
		if ( agent_div_legal_is_modified )
			map.put("agent_div_legal",agent_div_legal);
		if (map.size() == 0) {
			map.put("trader_code",trader_code);
			if ( acc_open_request_number_is_set )
				map.put("acc_open_request_number",acc_open_request_number);
			if ( serial_no_is_set )
				map.put("serial_no",serial_no);
			map.put("regist_div",regist_div);
			map.put("account_name_kana",account_name_kana);
			map.put("account_name",account_name);
			map.put("zip_code",zip_code);
			map.put("address_line1",address_line1);
			map.put("address_line1_kana",address_line1_kana);
			map.put("address_line2",address_line2);
			map.put("address_line2_kana",address_line2_kana);
			map.put("address_line3",address_line3);
			map.put("address_line3_kana",address_line3_kana);
			map.put("telephone",telephone);
			map.put("contact_address",contact_address);
			map.put("contact_telephone",contact_telephone);
			map.put("ex_branch_name",ex_branch_name);
			map.put("ex_account_code",ex_account_code);
			map.put("occupation_div",occupation_div);
			map.put("era_born",era_born);
			map.put("born_date",born_date);
			map.put("sex",sex);
			map.put("document",document);
			map.put("unknown_address",unknown_address);
			map.put("report",report);
			map.put("resident",resident);
			map.put("taxation_div",taxation_div);
			map.put("forign_taxation_div",forign_taxation_div);
			map.put("account_div",account_div);
			map.put("account_open_div1",account_open_div1);
			map.put("account_open_div2",account_open_div2);
			map.put("account_open_div3",account_open_div3);
			map.put("account_open_div4",account_open_div4);
			map.put("account_open_div5",account_open_div5);
			map.put("account_open_div6",account_open_div6);
			map.put("account_open_div7",account_open_div7);
			map.put("account_open_div8",account_open_div8);
			map.put("account_open_div9",account_open_div9);
			map.put("account_open_div10",account_open_div10);
			map.put("account_open_div11",account_open_div11);
			map.put("account_open_div12",account_open_div12);
			map.put("account_open_div13",account_open_div13);
			map.put("taxation_appl_div",taxation_appl_div);
			map.put("taxation_tran_div",taxation_tran_div);
			map.put("send_div",send_div);
			map.put("trust_via_div",trust_via_div);
			map.put("correct_div1",correct_div1);
			map.put("correct_div2",correct_div2);
			map.put("correct_div3",correct_div3);
			map.put("correct_div4",correct_div4);
			map.put("correct_div5",correct_div5);
			map.put("ifo_acc_open_div_tokyo",ifo_acc_open_div_tokyo);
			map.put("ifo_acc_open_div_osaka",ifo_acc_open_div_osaka);
			map.put("ifo_acc_open_div_nagoya",ifo_acc_open_div_nagoya);
			map.put("status",status);
			map.put("send_timestamp",send_timestamp);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("other_contact_telephone",other_contact_telephone);
			map.put("brokerage_trader_code",brokerage_trader_code);
			map.put("proam_div",proam_div);
			map.put("foreigner_div_broadcast",foreigner_div_broadcast);
			map.put("foreigner_div_aviation",foreigner_div_aviation);
			map.put("foreigner_div_ntt",foreigner_div_ntt);
			map.put("dividend_transfer_div",dividend_transfer_div);
			map.put("agent_div_permanent",agent_div_permanent);
			map.put("agent_div_legal",agent_div_legal);
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
   * <em>account_name_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountNameKana()
  {
    return account_name_kana;
  }


  /** 
   * <em>account_name_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameKanaIsSet() {
    return account_name_kana_is_set;
  }


  /** 
   * <em>account_name_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameKanaIsModified() {
    return account_name_kana_is_modified;
  }


  /** 
   * <em>account_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountName()
  {
    return account_name;
  }


  /** 
   * <em>account_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameIsSet() {
    return account_name_is_set;
  }


  /** 
   * <em>account_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountNameIsModified() {
    return account_name_is_modified;
  }


  /** 
   * <em>zip_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getZipCode()
  {
    return zip_code;
  }


  /** 
   * <em>zip_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getZipCodeIsSet() {
    return zip_code_is_set;
  }


  /** 
   * <em>zip_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getZipCodeIsModified() {
    return zip_code_is_modified;
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
   * <em>telephone</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTelephone()
  {
    return telephone;
  }


  /** 
   * <em>telephone</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTelephoneIsSet() {
    return telephone_is_set;
  }


  /** 
   * <em>telephone</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTelephoneIsModified() {
    return telephone_is_modified;
  }


  /** 
   * <em>contact_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getContactAddress()
  {
    return contact_address;
  }


  /** 
   * <em>contact_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContactAddressIsSet() {
    return contact_address_is_set;
  }


  /** 
   * <em>contact_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContactAddressIsModified() {
    return contact_address_is_modified;
  }


  /** 
   * <em>contact_telephone</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getContactTelephone()
  {
    return contact_telephone;
  }


  /** 
   * <em>contact_telephone</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContactTelephoneIsSet() {
    return contact_telephone_is_set;
  }


  /** 
   * <em>contact_telephone</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContactTelephoneIsModified() {
    return contact_telephone_is_modified;
  }


  /** 
   * <em>ex_branch_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExBranchName()
  {
    return ex_branch_name;
  }


  /** 
   * <em>ex_branch_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExBranchNameIsSet() {
    return ex_branch_name_is_set;
  }


  /** 
   * <em>ex_branch_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExBranchNameIsModified() {
    return ex_branch_name_is_modified;
  }


  /** 
   * <em>ex_account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExAccountCode()
  {
    return ex_account_code;
  }


  /** 
   * <em>ex_account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExAccountCodeIsSet() {
    return ex_account_code_is_set;
  }


  /** 
   * <em>ex_account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExAccountCodeIsModified() {
    return ex_account_code_is_modified;
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
   * <em>era_born</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEraBorn()
  {
    return era_born;
  }


  /** 
   * <em>era_born</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEraBornIsSet() {
    return era_born_is_set;
  }


  /** 
   * <em>era_born</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEraBornIsModified() {
    return era_born_is_modified;
  }


  /** 
   * <em>born_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBornDate()
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
   * <em>sex</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSex()
  {
    return sex;
  }


  /** 
   * <em>sex</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSexIsSet() {
    return sex_is_set;
  }


  /** 
   * <em>sex</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSexIsModified() {
    return sex_is_modified;
  }


  /** 
   * <em>document</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDocument()
  {
    return document;
  }


  /** 
   * <em>document</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDocumentIsSet() {
    return document_is_set;
  }


  /** 
   * <em>document</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDocumentIsModified() {
    return document_is_modified;
  }


  /** 
   * <em>unknown_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getUnknownAddress()
  {
    return unknown_address;
  }


  /** 
   * <em>unknown_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnknownAddressIsSet() {
    return unknown_address_is_set;
  }


  /** 
   * <em>unknown_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnknownAddressIsModified() {
    return unknown_address_is_modified;
  }


  /** 
   * <em>report</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReport()
  {
    return report;
  }


  /** 
   * <em>report</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReportIsSet() {
    return report_is_set;
  }


  /** 
   * <em>report</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReportIsModified() {
    return report_is_modified;
  }


  /** 
   * <em>resident</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getResident()
  {
    return resident;
  }


  /** 
   * <em>resident</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResidentIsSet() {
    return resident_is_set;
  }


  /** 
   * <em>resident</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getResidentIsModified() {
    return resident_is_modified;
  }


  /** 
   * <em>taxation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTaxationDiv()
  {
    return taxation_div;
  }


  /** 
   * <em>taxation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxationDivIsSet() {
    return taxation_div_is_set;
  }


  /** 
   * <em>taxation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxationDivIsModified() {
    return taxation_div_is_modified;
  }


  /** 
   * <em>forign_taxation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getForignTaxationDiv()
  {
    return forign_taxation_div;
  }


  /** 
   * <em>forign_taxation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForignTaxationDivIsSet() {
    return forign_taxation_div_is_set;
  }


  /** 
   * <em>forign_taxation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForignTaxationDivIsModified() {
    return forign_taxation_div_is_modified;
  }


  /** 
   * <em>account_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountDiv()
  {
    return account_div;
  }


  /** 
   * <em>account_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountDivIsSet() {
    return account_div_is_set;
  }


  /** 
   * <em>account_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountDivIsModified() {
    return account_div_is_modified;
  }


  /** 
   * <em>account_open_div1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountOpenDiv1()
  {
    return account_open_div1;
  }


  /** 
   * <em>account_open_div1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv1IsSet() {
    return account_open_div1_is_set;
  }


  /** 
   * <em>account_open_div1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv1IsModified() {
    return account_open_div1_is_modified;
  }


  /** 
   * <em>account_open_div2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountOpenDiv2()
  {
    return account_open_div2;
  }


  /** 
   * <em>account_open_div2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv2IsSet() {
    return account_open_div2_is_set;
  }


  /** 
   * <em>account_open_div2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv2IsModified() {
    return account_open_div2_is_modified;
  }


  /** 
   * <em>account_open_div3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountOpenDiv3()
  {
    return account_open_div3;
  }


  /** 
   * <em>account_open_div3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv3IsSet() {
    return account_open_div3_is_set;
  }


  /** 
   * <em>account_open_div3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv3IsModified() {
    return account_open_div3_is_modified;
  }


  /** 
   * <em>account_open_div4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountOpenDiv4()
  {
    return account_open_div4;
  }


  /** 
   * <em>account_open_div4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv4IsSet() {
    return account_open_div4_is_set;
  }


  /** 
   * <em>account_open_div4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv4IsModified() {
    return account_open_div4_is_modified;
  }


  /** 
   * <em>account_open_div5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountOpenDiv5()
  {
    return account_open_div5;
  }


  /** 
   * <em>account_open_div5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv5IsSet() {
    return account_open_div5_is_set;
  }


  /** 
   * <em>account_open_div5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv5IsModified() {
    return account_open_div5_is_modified;
  }


  /** 
   * <em>account_open_div6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountOpenDiv6()
  {
    return account_open_div6;
  }


  /** 
   * <em>account_open_div6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv6IsSet() {
    return account_open_div6_is_set;
  }


  /** 
   * <em>account_open_div6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv6IsModified() {
    return account_open_div6_is_modified;
  }


  /** 
   * <em>account_open_div7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountOpenDiv7()
  {
    return account_open_div7;
  }


  /** 
   * <em>account_open_div7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv7IsSet() {
    return account_open_div7_is_set;
  }


  /** 
   * <em>account_open_div7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv7IsModified() {
    return account_open_div7_is_modified;
  }


  /** 
   * <em>account_open_div8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountOpenDiv8()
  {
    return account_open_div8;
  }


  /** 
   * <em>account_open_div8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv8IsSet() {
    return account_open_div8_is_set;
  }


  /** 
   * <em>account_open_div8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv8IsModified() {
    return account_open_div8_is_modified;
  }


  /** 
   * <em>account_open_div9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountOpenDiv9()
  {
    return account_open_div9;
  }


  /** 
   * <em>account_open_div9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv9IsSet() {
    return account_open_div9_is_set;
  }


  /** 
   * <em>account_open_div9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv9IsModified() {
    return account_open_div9_is_modified;
  }


  /** 
   * <em>account_open_div10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountOpenDiv10()
  {
    return account_open_div10;
  }


  /** 
   * <em>account_open_div10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv10IsSet() {
    return account_open_div10_is_set;
  }


  /** 
   * <em>account_open_div10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv10IsModified() {
    return account_open_div10_is_modified;
  }


  /** 
   * <em>account_open_div11</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountOpenDiv11()
  {
    return account_open_div11;
  }


  /** 
   * <em>account_open_div11</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv11IsSet() {
    return account_open_div11_is_set;
  }


  /** 
   * <em>account_open_div11</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv11IsModified() {
    return account_open_div11_is_modified;
  }


  /** 
   * <em>account_open_div12</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountOpenDiv12()
  {
    return account_open_div12;
  }


  /** 
   * <em>account_open_div12</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv12IsSet() {
    return account_open_div12_is_set;
  }


  /** 
   * <em>account_open_div12</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv12IsModified() {
    return account_open_div12_is_modified;
  }


  /** 
   * <em>account_open_div13</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountOpenDiv13()
  {
    return account_open_div13;
  }


  /** 
   * <em>account_open_div13</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv13IsSet() {
    return account_open_div13_is_set;
  }


  /** 
   * <em>account_open_div13</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDiv13IsModified() {
    return account_open_div13_is_modified;
  }


  /** 
   * <em>taxation_appl_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTaxationApplDiv()
  {
    return taxation_appl_div;
  }


  /** 
   * <em>taxation_appl_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxationApplDivIsSet() {
    return taxation_appl_div_is_set;
  }


  /** 
   * <em>taxation_appl_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxationApplDivIsModified() {
    return taxation_appl_div_is_modified;
  }


  /** 
   * <em>taxation_tran_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTaxationTranDiv()
  {
    return taxation_tran_div;
  }


  /** 
   * <em>taxation_tran_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxationTranDivIsSet() {
    return taxation_tran_div_is_set;
  }


  /** 
   * <em>taxation_tran_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxationTranDivIsModified() {
    return taxation_tran_div_is_modified;
  }


  /** 
   * <em>send_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSendDiv()
  {
    return send_div;
  }


  /** 
   * <em>send_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendDivIsSet() {
    return send_div_is_set;
  }


  /** 
   * <em>send_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendDivIsModified() {
    return send_div_is_modified;
  }


  /** 
   * <em>trust_via_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTrustViaDiv()
  {
    return trust_via_div;
  }


  /** 
   * <em>trust_via_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrustViaDivIsSet() {
    return trust_via_div_is_set;
  }


  /** 
   * <em>trust_via_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrustViaDivIsModified() {
    return trust_via_div_is_modified;
  }


  /** 
   * <em>correct_div1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCorrectDiv1()
  {
    return correct_div1;
  }


  /** 
   * <em>correct_div1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCorrectDiv1IsSet() {
    return correct_div1_is_set;
  }


  /** 
   * <em>correct_div1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCorrectDiv1IsModified() {
    return correct_div1_is_modified;
  }


  /** 
   * <em>correct_div2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCorrectDiv2()
  {
    return correct_div2;
  }


  /** 
   * <em>correct_div2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCorrectDiv2IsSet() {
    return correct_div2_is_set;
  }


  /** 
   * <em>correct_div2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCorrectDiv2IsModified() {
    return correct_div2_is_modified;
  }


  /** 
   * <em>correct_div3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCorrectDiv3()
  {
    return correct_div3;
  }


  /** 
   * <em>correct_div3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCorrectDiv3IsSet() {
    return correct_div3_is_set;
  }


  /** 
   * <em>correct_div3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCorrectDiv3IsModified() {
    return correct_div3_is_modified;
  }


  /** 
   * <em>correct_div4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCorrectDiv4()
  {
    return correct_div4;
  }


  /** 
   * <em>correct_div4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCorrectDiv4IsSet() {
    return correct_div4_is_set;
  }


  /** 
   * <em>correct_div4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCorrectDiv4IsModified() {
    return correct_div4_is_modified;
  }


  /** 
   * <em>correct_div5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCorrectDiv5()
  {
    return correct_div5;
  }


  /** 
   * <em>correct_div5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCorrectDiv5IsSet() {
    return correct_div5_is_set;
  }


  /** 
   * <em>correct_div5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCorrectDiv5IsModified() {
    return correct_div5_is_modified;
  }


  /** 
   * <em>ifo_acc_open_div_tokyo</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoAccOpenDivTokyo()
  {
    return ifo_acc_open_div_tokyo;
  }


  /** 
   * <em>ifo_acc_open_div_tokyo</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoAccOpenDivTokyoIsSet() {
    return ifo_acc_open_div_tokyo_is_set;
  }


  /** 
   * <em>ifo_acc_open_div_tokyo</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoAccOpenDivTokyoIsModified() {
    return ifo_acc_open_div_tokyo_is_modified;
  }


  /** 
   * <em>ifo_acc_open_div_osaka</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoAccOpenDivOsaka()
  {
    return ifo_acc_open_div_osaka;
  }


  /** 
   * <em>ifo_acc_open_div_osaka</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoAccOpenDivOsakaIsSet() {
    return ifo_acc_open_div_osaka_is_set;
  }


  /** 
   * <em>ifo_acc_open_div_osaka</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoAccOpenDivOsakaIsModified() {
    return ifo_acc_open_div_osaka_is_modified;
  }


  /** 
   * <em>ifo_acc_open_div_nagoya</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoAccOpenDivNagoya()
  {
    return ifo_acc_open_div_nagoya;
  }


  /** 
   * <em>ifo_acc_open_div_nagoya</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoAccOpenDivNagoyaIsSet() {
    return ifo_acc_open_div_nagoya_is_set;
  }


  /** 
   * <em>ifo_acc_open_div_nagoya</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoAccOpenDivNagoyaIsModified() {
    return ifo_acc_open_div_nagoya_is_modified;
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
   * <em>other_contact_telephone</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOtherContactTelephone()
  {
    return other_contact_telephone;
  }


  /** 
   * <em>other_contact_telephone</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherContactTelephoneIsSet() {
    return other_contact_telephone_is_set;
  }


  /** 
   * <em>other_contact_telephone</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherContactTelephoneIsModified() {
    return other_contact_telephone_is_modified;
  }


  /** 
   * <em>brokerage_trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBrokerageTraderCode()
  {
    return brokerage_trader_code;
  }


  /** 
   * <em>brokerage_trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBrokerageTraderCodeIsSet() {
    return brokerage_trader_code_is_set;
  }


  /** 
   * <em>brokerage_trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBrokerageTraderCodeIsModified() {
    return brokerage_trader_code_is_modified;
  }


  /** 
   * <em>proam_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProamDiv()
  {
    return proam_div;
  }


  /** 
   * <em>proam_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProamDivIsSet() {
    return proam_div_is_set;
  }


  /** 
   * <em>proam_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProamDivIsModified() {
    return proam_div_is_modified;
  }


  /** 
   * <em>foreigner_div_broadcast</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getForeignerDivBroadcast()
  {
    return foreigner_div_broadcast;
  }


  /** 
   * <em>foreigner_div_broadcast</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignerDivBroadcastIsSet() {
    return foreigner_div_broadcast_is_set;
  }


  /** 
   * <em>foreigner_div_broadcast</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignerDivBroadcastIsModified() {
    return foreigner_div_broadcast_is_modified;
  }


  /** 
   * <em>foreigner_div_aviation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getForeignerDivAviation()
  {
    return foreigner_div_aviation;
  }


  /** 
   * <em>foreigner_div_aviation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignerDivAviationIsSet() {
    return foreigner_div_aviation_is_set;
  }


  /** 
   * <em>foreigner_div_aviation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignerDivAviationIsModified() {
    return foreigner_div_aviation_is_modified;
  }


  /** 
   * <em>foreigner_div_ntt</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getForeignerDivNtt()
  {
    return foreigner_div_ntt;
  }


  /** 
   * <em>foreigner_div_ntt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignerDivNttIsSet() {
    return foreigner_div_ntt_is_set;
  }


  /** 
   * <em>foreigner_div_ntt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignerDivNttIsModified() {
    return foreigner_div_ntt_is_modified;
  }


  /** 
   * <em>dividend_transfer_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDividendTransferDiv()
  {
    return dividend_transfer_div;
  }


  /** 
   * <em>dividend_transfer_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDividendTransferDivIsSet() {
    return dividend_transfer_div_is_set;
  }


  /** 
   * <em>dividend_transfer_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDividendTransferDivIsModified() {
    return dividend_transfer_div_is_modified;
  }


  /** 
   * <em>agent_div_permanent</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAgentDivPermanent()
  {
    return agent_div_permanent;
  }


  /** 
   * <em>agent_div_permanent</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgentDivPermanentIsSet() {
    return agent_div_permanent_is_set;
  }


  /** 
   * <em>agent_div_permanent</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgentDivPermanentIsModified() {
    return agent_div_permanent_is_modified;
  }


  /** 
   * <em>agent_div_legal</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAgentDivLegal()
  {
    return agent_div_legal;
  }


  /** 
   * <em>agent_div_legal</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgentDivLegalIsSet() {
    return agent_div_legal_is_set;
  }


  /** 
   * <em>agent_div_legal</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgentDivLegalIsModified() {
    return agent_div_legal_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new HostAccRegVoucherPK(order_request_number, request_code, institution_code, branch_code, account_code);
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
   * <em>account_name_kana</em>カラムの値を設定します。 
   *
   * @@param p_accountNameKana <em>account_name_kana</em>カラムの値をあらわす19桁以下のString値 
   */
  public final void setAccountNameKana( String p_accountNameKana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_name_kana = p_accountNameKana;
    account_name_kana_is_set = true;
    account_name_kana_is_modified = true;
  }


  /** 
   * <em>account_name</em>カラムの値を設定します。 
   *
   * @@param p_accountName <em>account_name</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setAccountName( String p_accountName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_name = p_accountName;
    account_name_is_set = true;
    account_name_is_modified = true;
  }


  /** 
   * <em>zip_code</em>カラムの値を設定します。 
   *
   * @@param p_zipCode <em>zip_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setZipCode( String p_zipCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    zip_code = p_zipCode;
    zip_code_is_set = true;
    zip_code_is_modified = true;
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
   * <em>address_line1_kana</em>カラムの値を設定します。 
   *
   * @@param p_addressLine1Kana <em>address_line1_kana</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setAddressLine1Kana( String p_addressLine1Kana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address_line1_kana = p_addressLine1Kana;
    address_line1_kana_is_set = true;
    address_line1_kana_is_modified = true;
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
   * <em>address_line2_kana</em>カラムの値を設定します。 
   *
   * @@param p_addressLine2Kana <em>address_line2_kana</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setAddressLine2Kana( String p_addressLine2Kana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address_line2_kana = p_addressLine2Kana;
    address_line2_kana_is_set = true;
    address_line2_kana_is_modified = true;
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
   * <em>address_line3_kana</em>カラムの値を設定します。 
   *
   * @@param p_addressLine3Kana <em>address_line3_kana</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setAddressLine3Kana( String p_addressLine3Kana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address_line3_kana = p_addressLine3Kana;
    address_line3_kana_is_set = true;
    address_line3_kana_is_modified = true;
  }


  /** 
   * <em>telephone</em>カラムの値を設定します。 
   *
   * @@param p_telephone <em>telephone</em>カラムの値をあらわす16桁以下のString値 
   */
  public final void setTelephone( String p_telephone )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    telephone = p_telephone;
    telephone_is_set = true;
    telephone_is_modified = true;
  }


  /** 
   * <em>contact_address</em>カラムの値を設定します。 
   *
   * @@param p_contactAddress <em>contact_address</em>カラムの値をあらわす36桁以下のString値 
   */
  public final void setContactAddress( String p_contactAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contact_address = p_contactAddress;
    contact_address_is_set = true;
    contact_address_is_modified = true;
  }


  /** 
   * <em>contact_telephone</em>カラムの値を設定します。 
   *
   * @@param p_contactTelephone <em>contact_telephone</em>カラムの値をあらわす16桁以下のString値 
   */
  public final void setContactTelephone( String p_contactTelephone )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contact_telephone = p_contactTelephone;
    contact_telephone_is_set = true;
    contact_telephone_is_modified = true;
  }


  /** 
   * <em>ex_branch_name</em>カラムの値を設定します。 
   *
   * @@param p_exBranchName <em>ex_branch_name</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setExBranchName( String p_exBranchName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ex_branch_name = p_exBranchName;
    ex_branch_name_is_set = true;
    ex_branch_name_is_modified = true;
  }


  /** 
   * <em>ex_account_code</em>カラムの値を設定します。 
   *
   * @@param p_exAccountCode <em>ex_account_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setExAccountCode( String p_exAccountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ex_account_code = p_exAccountCode;
    ex_account_code_is_set = true;
    ex_account_code_is_modified = true;
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
   * <em>era_born</em>カラムの値を設定します。 
   *
   * @@param p_eraBorn <em>era_born</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEraBorn( String p_eraBorn )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    era_born = p_eraBorn;
    era_born_is_set = true;
    era_born_is_modified = true;
  }


  /** 
   * <em>born_date</em>カラムの値を設定します。 
   *
   * @@param p_bornDate <em>born_date</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setBornDate( String p_bornDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    born_date = p_bornDate;
    born_date_is_set = true;
    born_date_is_modified = true;
  }


  /** 
   * <em>sex</em>カラムの値を設定します。 
   *
   * @@param p_sex <em>sex</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSex( String p_sex )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sex = p_sex;
    sex_is_set = true;
    sex_is_modified = true;
  }


  /** 
   * <em>document</em>カラムの値を設定します。 
   *
   * @@param p_document <em>document</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDocument( String p_document )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    document = p_document;
    document_is_set = true;
    document_is_modified = true;
  }


  /** 
   * <em>unknown_address</em>カラムの値を設定します。 
   *
   * @@param p_unknownAddress <em>unknown_address</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setUnknownAddress( String p_unknownAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unknown_address = p_unknownAddress;
    unknown_address_is_set = true;
    unknown_address_is_modified = true;
  }


  /** 
   * <em>report</em>カラムの値を設定します。 
   *
   * @@param p_report <em>report</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setReport( String p_report )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    report = p_report;
    report_is_set = true;
    report_is_modified = true;
  }


  /** 
   * <em>resident</em>カラムの値を設定します。 
   *
   * @@param p_resident <em>resident</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setResident( String p_resident )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    resident = p_resident;
    resident_is_set = true;
    resident_is_modified = true;
  }


  /** 
   * <em>taxation_div</em>カラムの値を設定します。 
   *
   * @@param p_taxationDiv <em>taxation_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTaxationDiv( String p_taxationDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    taxation_div = p_taxationDiv;
    taxation_div_is_set = true;
    taxation_div_is_modified = true;
  }


  /** 
   * <em>forign_taxation_div</em>カラムの値を設定します。 
   *
   * @@param p_forignTaxationDiv <em>forign_taxation_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setForignTaxationDiv( String p_forignTaxationDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    forign_taxation_div = p_forignTaxationDiv;
    forign_taxation_div_is_set = true;
    forign_taxation_div_is_modified = true;
  }


  /** 
   * <em>account_div</em>カラムの値を設定します。 
   *
   * @@param p_accountDiv <em>account_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountDiv( String p_accountDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_div = p_accountDiv;
    account_div_is_set = true;
    account_div_is_modified = true;
  }


  /** 
   * <em>account_open_div1</em>カラムの値を設定します。 
   *
   * @@param p_accountOpenDiv1 <em>account_open_div1</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountOpenDiv1( String p_accountOpenDiv1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_div1 = p_accountOpenDiv1;
    account_open_div1_is_set = true;
    account_open_div1_is_modified = true;
  }


  /** 
   * <em>account_open_div2</em>カラムの値を設定します。 
   *
   * @@param p_accountOpenDiv2 <em>account_open_div2</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountOpenDiv2( String p_accountOpenDiv2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_div2 = p_accountOpenDiv2;
    account_open_div2_is_set = true;
    account_open_div2_is_modified = true;
  }


  /** 
   * <em>account_open_div3</em>カラムの値を設定します。 
   *
   * @@param p_accountOpenDiv3 <em>account_open_div3</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountOpenDiv3( String p_accountOpenDiv3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_div3 = p_accountOpenDiv3;
    account_open_div3_is_set = true;
    account_open_div3_is_modified = true;
  }


  /** 
   * <em>account_open_div4</em>カラムの値を設定します。 
   *
   * @@param p_accountOpenDiv4 <em>account_open_div4</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountOpenDiv4( String p_accountOpenDiv4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_div4 = p_accountOpenDiv4;
    account_open_div4_is_set = true;
    account_open_div4_is_modified = true;
  }


  /** 
   * <em>account_open_div5</em>カラムの値を設定します。 
   *
   * @@param p_accountOpenDiv5 <em>account_open_div5</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountOpenDiv5( String p_accountOpenDiv5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_div5 = p_accountOpenDiv5;
    account_open_div5_is_set = true;
    account_open_div5_is_modified = true;
  }


  /** 
   * <em>account_open_div6</em>カラムの値を設定します。 
   *
   * @@param p_accountOpenDiv6 <em>account_open_div6</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountOpenDiv6( String p_accountOpenDiv6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_div6 = p_accountOpenDiv6;
    account_open_div6_is_set = true;
    account_open_div6_is_modified = true;
  }


  /** 
   * <em>account_open_div7</em>カラムの値を設定します。 
   *
   * @@param p_accountOpenDiv7 <em>account_open_div7</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountOpenDiv7( String p_accountOpenDiv7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_div7 = p_accountOpenDiv7;
    account_open_div7_is_set = true;
    account_open_div7_is_modified = true;
  }


  /** 
   * <em>account_open_div8</em>カラムの値を設定します。 
   *
   * @@param p_accountOpenDiv8 <em>account_open_div8</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountOpenDiv8( String p_accountOpenDiv8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_div8 = p_accountOpenDiv8;
    account_open_div8_is_set = true;
    account_open_div8_is_modified = true;
  }


  /** 
   * <em>account_open_div9</em>カラムの値を設定します。 
   *
   * @@param p_accountOpenDiv9 <em>account_open_div9</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountOpenDiv9( String p_accountOpenDiv9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_div9 = p_accountOpenDiv9;
    account_open_div9_is_set = true;
    account_open_div9_is_modified = true;
  }


  /** 
   * <em>account_open_div10</em>カラムの値を設定します。 
   *
   * @@param p_accountOpenDiv10 <em>account_open_div10</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountOpenDiv10( String p_accountOpenDiv10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_div10 = p_accountOpenDiv10;
    account_open_div10_is_set = true;
    account_open_div10_is_modified = true;
  }


  /** 
   * <em>account_open_div11</em>カラムの値を設定します。 
   *
   * @@param p_accountOpenDiv11 <em>account_open_div11</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountOpenDiv11( String p_accountOpenDiv11 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_div11 = p_accountOpenDiv11;
    account_open_div11_is_set = true;
    account_open_div11_is_modified = true;
  }


  /** 
   * <em>account_open_div12</em>カラムの値を設定します。 
   *
   * @@param p_accountOpenDiv12 <em>account_open_div12</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountOpenDiv12( String p_accountOpenDiv12 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_div12 = p_accountOpenDiv12;
    account_open_div12_is_set = true;
    account_open_div12_is_modified = true;
  }


  /** 
   * <em>account_open_div13</em>カラムの値を設定します。 
   *
   * @@param p_accountOpenDiv13 <em>account_open_div13</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountOpenDiv13( String p_accountOpenDiv13 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_div13 = p_accountOpenDiv13;
    account_open_div13_is_set = true;
    account_open_div13_is_modified = true;
  }


  /** 
   * <em>taxation_appl_div</em>カラムの値を設定します。 
   *
   * @@param p_taxationApplDiv <em>taxation_appl_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTaxationApplDiv( String p_taxationApplDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    taxation_appl_div = p_taxationApplDiv;
    taxation_appl_div_is_set = true;
    taxation_appl_div_is_modified = true;
  }


  /** 
   * <em>taxation_tran_div</em>カラムの値を設定します。 
   *
   * @@param p_taxationTranDiv <em>taxation_tran_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTaxationTranDiv( String p_taxationTranDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    taxation_tran_div = p_taxationTranDiv;
    taxation_tran_div_is_set = true;
    taxation_tran_div_is_modified = true;
  }


  /** 
   * <em>send_div</em>カラムの値を設定します。 
   *
   * @@param p_sendDiv <em>send_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSendDiv( String p_sendDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    send_div = p_sendDiv;
    send_div_is_set = true;
    send_div_is_modified = true;
  }


  /** 
   * <em>trust_via_div</em>カラムの値を設定します。 
   *
   * @@param p_trustViaDiv <em>trust_via_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTrustViaDiv( String p_trustViaDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trust_via_div = p_trustViaDiv;
    trust_via_div_is_set = true;
    trust_via_div_is_modified = true;
  }


  /** 
   * <em>correct_div1</em>カラムの値を設定します。 
   *
   * @@param p_correctDiv1 <em>correct_div1</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCorrectDiv1( String p_correctDiv1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    correct_div1 = p_correctDiv1;
    correct_div1_is_set = true;
    correct_div1_is_modified = true;
  }


  /** 
   * <em>correct_div2</em>カラムの値を設定します。 
   *
   * @@param p_correctDiv2 <em>correct_div2</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCorrectDiv2( String p_correctDiv2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    correct_div2 = p_correctDiv2;
    correct_div2_is_set = true;
    correct_div2_is_modified = true;
  }


  /** 
   * <em>correct_div3</em>カラムの値を設定します。 
   *
   * @@param p_correctDiv3 <em>correct_div3</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCorrectDiv3( String p_correctDiv3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    correct_div3 = p_correctDiv3;
    correct_div3_is_set = true;
    correct_div3_is_modified = true;
  }


  /** 
   * <em>correct_div4</em>カラムの値を設定します。 
   *
   * @@param p_correctDiv4 <em>correct_div4</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCorrectDiv4( String p_correctDiv4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    correct_div4 = p_correctDiv4;
    correct_div4_is_set = true;
    correct_div4_is_modified = true;
  }


  /** 
   * <em>correct_div5</em>カラムの値を設定します。 
   *
   * @@param p_correctDiv5 <em>correct_div5</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCorrectDiv5( String p_correctDiv5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    correct_div5 = p_correctDiv5;
    correct_div5_is_set = true;
    correct_div5_is_modified = true;
  }


  /** 
   * <em>ifo_acc_open_div_tokyo</em>カラムの値を設定します。 
   *
   * @@param p_ifoAccOpenDivTokyo <em>ifo_acc_open_div_tokyo</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setIfoAccOpenDivTokyo( String p_ifoAccOpenDivTokyo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_acc_open_div_tokyo = p_ifoAccOpenDivTokyo;
    ifo_acc_open_div_tokyo_is_set = true;
    ifo_acc_open_div_tokyo_is_modified = true;
  }


  /** 
   * <em>ifo_acc_open_div_osaka</em>カラムの値を設定します。 
   *
   * @@param p_ifoAccOpenDivOsaka <em>ifo_acc_open_div_osaka</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setIfoAccOpenDivOsaka( String p_ifoAccOpenDivOsaka )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_acc_open_div_osaka = p_ifoAccOpenDivOsaka;
    ifo_acc_open_div_osaka_is_set = true;
    ifo_acc_open_div_osaka_is_modified = true;
  }


  /** 
   * <em>ifo_acc_open_div_nagoya</em>カラムの値を設定します。 
   *
   * @@param p_ifoAccOpenDivNagoya <em>ifo_acc_open_div_nagoya</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setIfoAccOpenDivNagoya( String p_ifoAccOpenDivNagoya )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_acc_open_div_nagoya = p_ifoAccOpenDivNagoya;
    ifo_acc_open_div_nagoya_is_set = true;
    ifo_acc_open_div_nagoya_is_modified = true;
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
   * <em>other_contact_telephone</em>カラムの値を設定します。 
   *
   * @@param p_otherContactTelephone <em>other_contact_telephone</em>カラムの値をあらわす16桁以下のString値 
   */
  public final void setOtherContactTelephone( String p_otherContactTelephone )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_contact_telephone = p_otherContactTelephone;
    other_contact_telephone_is_set = true;
    other_contact_telephone_is_modified = true;
  }


  /** 
   * <em>brokerage_trader_code</em>カラムの値を設定します。 
   *
   * @@param p_brokerageTraderCode <em>brokerage_trader_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setBrokerageTraderCode( String p_brokerageTraderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    brokerage_trader_code = p_brokerageTraderCode;
    brokerage_trader_code_is_set = true;
    brokerage_trader_code_is_modified = true;
  }


  /** 
   * <em>proam_div</em>カラムの値を設定します。 
   *
   * @@param p_proamDiv <em>proam_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setProamDiv( String p_proamDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    proam_div = p_proamDiv;
    proam_div_is_set = true;
    proam_div_is_modified = true;
  }


  /** 
   * <em>foreigner_div_broadcast</em>カラムの値を設定します。 
   *
   * @@param p_foreignerDivBroadcast <em>foreigner_div_broadcast</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setForeignerDivBroadcast( String p_foreignerDivBroadcast )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreigner_div_broadcast = p_foreignerDivBroadcast;
    foreigner_div_broadcast_is_set = true;
    foreigner_div_broadcast_is_modified = true;
  }


  /** 
   * <em>foreigner_div_aviation</em>カラムの値を設定します。 
   *
   * @@param p_foreignerDivAviation <em>foreigner_div_aviation</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setForeignerDivAviation( String p_foreignerDivAviation )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreigner_div_aviation = p_foreignerDivAviation;
    foreigner_div_aviation_is_set = true;
    foreigner_div_aviation_is_modified = true;
  }


  /** 
   * <em>foreigner_div_ntt</em>カラムの値を設定します。 
   *
   * @@param p_foreignerDivNtt <em>foreigner_div_ntt</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setForeignerDivNtt( String p_foreignerDivNtt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreigner_div_ntt = p_foreignerDivNtt;
    foreigner_div_ntt_is_set = true;
    foreigner_div_ntt_is_modified = true;
  }


  /** 
   * <em>dividend_transfer_div</em>カラムの値を設定します。 
   *
   * @@param p_dividendTransferDiv <em>dividend_transfer_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDividendTransferDiv( String p_dividendTransferDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dividend_transfer_div = p_dividendTransferDiv;
    dividend_transfer_div_is_set = true;
    dividend_transfer_div_is_modified = true;
  }


  /** 
   * <em>agent_div_permanent</em>カラムの値を設定します。 
   *
   * @@param p_agentDivPermanent <em>agent_div_permanent</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAgentDivPermanent( String p_agentDivPermanent )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agent_div_permanent = p_agentDivPermanent;
    agent_div_permanent_is_set = true;
    agent_div_permanent_is_modified = true;
  }


  /** 
   * <em>agent_div_legal</em>カラムの値を設定します。 
   *
   * @@param p_agentDivLegal <em>agent_div_legal</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAgentDivLegal( String p_agentDivLegal )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agent_div_legal = p_agentDivLegal;
    agent_div_legal_is_set = true;
    agent_div_legal_is_modified = true;
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
                else if ( name.equals("account_name_kana") ) {
                    return this.account_name_kana;
                }
                else if ( name.equals("account_name") ) {
                    return this.account_name;
                }
                else if ( name.equals("address_line1") ) {
                    return this.address_line1;
                }
                else if ( name.equals("address_line1_kana") ) {
                    return this.address_line1_kana;
                }
                else if ( name.equals("address_line2") ) {
                    return this.address_line2;
                }
                else if ( name.equals("address_line2_kana") ) {
                    return this.address_line2_kana;
                }
                else if ( name.equals("address_line3") ) {
                    return this.address_line3;
                }
                else if ( name.equals("address_line3_kana") ) {
                    return this.address_line3_kana;
                }
                else if ( name.equals("account_div") ) {
                    return this.account_div;
                }
                else if ( name.equals("account_open_div1") ) {
                    return this.account_open_div1;
                }
                else if ( name.equals("account_open_div2") ) {
                    return this.account_open_div2;
                }
                else if ( name.equals("account_open_div3") ) {
                    return this.account_open_div3;
                }
                else if ( name.equals("account_open_div4") ) {
                    return this.account_open_div4;
                }
                else if ( name.equals("account_open_div5") ) {
                    return this.account_open_div5;
                }
                else if ( name.equals("account_open_div6") ) {
                    return this.account_open_div6;
                }
                else if ( name.equals("account_open_div7") ) {
                    return this.account_open_div7;
                }
                else if ( name.equals("account_open_div8") ) {
                    return this.account_open_div8;
                }
                else if ( name.equals("account_open_div9") ) {
                    return this.account_open_div9;
                }
                else if ( name.equals("account_open_div10") ) {
                    return this.account_open_div10;
                }
                else if ( name.equals("account_open_div11") ) {
                    return this.account_open_div11;
                }
                else if ( name.equals("account_open_div12") ) {
                    return this.account_open_div12;
                }
                else if ( name.equals("account_open_div13") ) {
                    return this.account_open_div13;
                }
                else if ( name.equals("agent_div_permanent") ) {
                    return this.agent_div_permanent;
                }
                else if ( name.equals("agent_div_legal") ) {
                    return this.agent_div_legal;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("born_date") ) {
                    return this.born_date;
                }
                else if ( name.equals("brokerage_trader_code") ) {
                    return this.brokerage_trader_code;
                }
                break;
            case 'c':
                if ( name.equals("contact_address") ) {
                    return this.contact_address;
                }
                else if ( name.equals("contact_telephone") ) {
                    return this.contact_telephone;
                }
                else if ( name.equals("correct_div1") ) {
                    return this.correct_div1;
                }
                else if ( name.equals("correct_div2") ) {
                    return this.correct_div2;
                }
                else if ( name.equals("correct_div3") ) {
                    return this.correct_div3;
                }
                else if ( name.equals("correct_div4") ) {
                    return this.correct_div4;
                }
                else if ( name.equals("correct_div5") ) {
                    return this.correct_div5;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("document") ) {
                    return this.document;
                }
                else if ( name.equals("dividend_transfer_div") ) {
                    return this.dividend_transfer_div;
                }
                break;
            case 'e':
                if ( name.equals("ex_branch_name") ) {
                    return this.ex_branch_name;
                }
                else if ( name.equals("ex_account_code") ) {
                    return this.ex_account_code;
                }
                else if ( name.equals("era_born") ) {
                    return this.era_born;
                }
                break;
            case 'f':
                if ( name.equals("forign_taxation_div") ) {
                    return this.forign_taxation_div;
                }
                else if ( name.equals("foreigner_div_broadcast") ) {
                    return this.foreigner_div_broadcast;
                }
                else if ( name.equals("foreigner_div_aviation") ) {
                    return this.foreigner_div_aviation;
                }
                else if ( name.equals("foreigner_div_ntt") ) {
                    return this.foreigner_div_ntt;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("ifo_acc_open_div_tokyo") ) {
                    return this.ifo_acc_open_div_tokyo;
                }
                else if ( name.equals("ifo_acc_open_div_osaka") ) {
                    return this.ifo_acc_open_div_osaka;
                }
                else if ( name.equals("ifo_acc_open_div_nagoya") ) {
                    return this.ifo_acc_open_div_nagoya;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("order_request_number") ) {
                    return this.order_request_number;
                }
                else if ( name.equals("occupation_div") ) {
                    return this.occupation_div;
                }
                else if ( name.equals("other_contact_telephone") ) {
                    return this.other_contact_telephone;
                }
                break;
            case 'p':
                if ( name.equals("proam_div") ) {
                    return this.proam_div;
                }
                break;
            case 'r':
                if ( name.equals("request_code") ) {
                    return this.request_code;
                }
                else if ( name.equals("regist_div") ) {
                    return this.regist_div;
                }
                else if ( name.equals("report") ) {
                    return this.report;
                }
                else if ( name.equals("resident") ) {
                    return this.resident;
                }
                break;
            case 's':
                if ( name.equals("serial_no") ) {
                    return this.serial_no;
                }
                else if ( name.equals("sex") ) {
                    return this.sex;
                }
                else if ( name.equals("send_div") ) {
                    return this.send_div;
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
                else if ( name.equals("telephone") ) {
                    return this.telephone;
                }
                else if ( name.equals("taxation_div") ) {
                    return this.taxation_div;
                }
                else if ( name.equals("taxation_appl_div") ) {
                    return this.taxation_appl_div;
                }
                else if ( name.equals("taxation_tran_div") ) {
                    return this.taxation_tran_div;
                }
                else if ( name.equals("trust_via_div") ) {
                    return this.trust_via_div;
                }
                break;
            case 'u':
                if ( name.equals("unknown_address") ) {
                    return this.unknown_address;
                }
                break;
            case 'z':
                if ( name.equals("zip_code") ) {
                    return this.zip_code;
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
                else if ( name.equals("account_name_kana") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_name_kana' must be String: '"+value+"' is not." );
                    this.account_name_kana = (String) value;
                    if (this.account_name_kana_is_set)
                        this.account_name_kana_is_modified = true;
                    this.account_name_kana_is_set = true;
                    return;
                }
                else if ( name.equals("account_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_name' must be String: '"+value+"' is not." );
                    this.account_name = (String) value;
                    if (this.account_name_is_set)
                        this.account_name_is_modified = true;
                    this.account_name_is_set = true;
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
                else if ( name.equals("account_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_div' must be String: '"+value+"' is not." );
                    this.account_div = (String) value;
                    if (this.account_div_is_set)
                        this.account_div_is_modified = true;
                    this.account_div_is_set = true;
                    return;
                }
                else if ( name.equals("account_open_div1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_open_div1' must be String: '"+value+"' is not." );
                    this.account_open_div1 = (String) value;
                    if (this.account_open_div1_is_set)
                        this.account_open_div1_is_modified = true;
                    this.account_open_div1_is_set = true;
                    return;
                }
                else if ( name.equals("account_open_div2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_open_div2' must be String: '"+value+"' is not." );
                    this.account_open_div2 = (String) value;
                    if (this.account_open_div2_is_set)
                        this.account_open_div2_is_modified = true;
                    this.account_open_div2_is_set = true;
                    return;
                }
                else if ( name.equals("account_open_div3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_open_div3' must be String: '"+value+"' is not." );
                    this.account_open_div3 = (String) value;
                    if (this.account_open_div3_is_set)
                        this.account_open_div3_is_modified = true;
                    this.account_open_div3_is_set = true;
                    return;
                }
                else if ( name.equals("account_open_div4") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_open_div4' must be String: '"+value+"' is not." );
                    this.account_open_div4 = (String) value;
                    if (this.account_open_div4_is_set)
                        this.account_open_div4_is_modified = true;
                    this.account_open_div4_is_set = true;
                    return;
                }
                else if ( name.equals("account_open_div5") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_open_div5' must be String: '"+value+"' is not." );
                    this.account_open_div5 = (String) value;
                    if (this.account_open_div5_is_set)
                        this.account_open_div5_is_modified = true;
                    this.account_open_div5_is_set = true;
                    return;
                }
                else if ( name.equals("account_open_div6") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_open_div6' must be String: '"+value+"' is not." );
                    this.account_open_div6 = (String) value;
                    if (this.account_open_div6_is_set)
                        this.account_open_div6_is_modified = true;
                    this.account_open_div6_is_set = true;
                    return;
                }
                else if ( name.equals("account_open_div7") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_open_div7' must be String: '"+value+"' is not." );
                    this.account_open_div7 = (String) value;
                    if (this.account_open_div7_is_set)
                        this.account_open_div7_is_modified = true;
                    this.account_open_div7_is_set = true;
                    return;
                }
                else if ( name.equals("account_open_div8") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_open_div8' must be String: '"+value+"' is not." );
                    this.account_open_div8 = (String) value;
                    if (this.account_open_div8_is_set)
                        this.account_open_div8_is_modified = true;
                    this.account_open_div8_is_set = true;
                    return;
                }
                else if ( name.equals("account_open_div9") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_open_div9' must be String: '"+value+"' is not." );
                    this.account_open_div9 = (String) value;
                    if (this.account_open_div9_is_set)
                        this.account_open_div9_is_modified = true;
                    this.account_open_div9_is_set = true;
                    return;
                }
                else if ( name.equals("account_open_div10") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_open_div10' must be String: '"+value+"' is not." );
                    this.account_open_div10 = (String) value;
                    if (this.account_open_div10_is_set)
                        this.account_open_div10_is_modified = true;
                    this.account_open_div10_is_set = true;
                    return;
                }
                else if ( name.equals("account_open_div11") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_open_div11' must be String: '"+value+"' is not." );
                    this.account_open_div11 = (String) value;
                    if (this.account_open_div11_is_set)
                        this.account_open_div11_is_modified = true;
                    this.account_open_div11_is_set = true;
                    return;
                }
                else if ( name.equals("account_open_div12") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_open_div12' must be String: '"+value+"' is not." );
                    this.account_open_div12 = (String) value;
                    if (this.account_open_div12_is_set)
                        this.account_open_div12_is_modified = true;
                    this.account_open_div12_is_set = true;
                    return;
                }
                else if ( name.equals("account_open_div13") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_open_div13' must be String: '"+value+"' is not." );
                    this.account_open_div13 = (String) value;
                    if (this.account_open_div13_is_set)
                        this.account_open_div13_is_modified = true;
                    this.account_open_div13_is_set = true;
                    return;
                }
                else if ( name.equals("agent_div_permanent") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agent_div_permanent' must be String: '"+value+"' is not." );
                    this.agent_div_permanent = (String) value;
                    if (this.agent_div_permanent_is_set)
                        this.agent_div_permanent_is_modified = true;
                    this.agent_div_permanent_is_set = true;
                    return;
                }
                else if ( name.equals("agent_div_legal") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agent_div_legal' must be String: '"+value+"' is not." );
                    this.agent_div_legal = (String) value;
                    if (this.agent_div_legal_is_set)
                        this.agent_div_legal_is_modified = true;
                    this.agent_div_legal_is_set = true;
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
                else if ( name.equals("born_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'born_date' must be String: '"+value+"' is not." );
                    this.born_date = (String) value;
                    if (this.born_date_is_set)
                        this.born_date_is_modified = true;
                    this.born_date_is_set = true;
                    return;
                }
                else if ( name.equals("brokerage_trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'brokerage_trader_code' must be String: '"+value+"' is not." );
                    this.brokerage_trader_code = (String) value;
                    if (this.brokerage_trader_code_is_set)
                        this.brokerage_trader_code_is_modified = true;
                    this.brokerage_trader_code_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("contact_address") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'contact_address' must be String: '"+value+"' is not." );
                    this.contact_address = (String) value;
                    if (this.contact_address_is_set)
                        this.contact_address_is_modified = true;
                    this.contact_address_is_set = true;
                    return;
                }
                else if ( name.equals("contact_telephone") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'contact_telephone' must be String: '"+value+"' is not." );
                    this.contact_telephone = (String) value;
                    if (this.contact_telephone_is_set)
                        this.contact_telephone_is_modified = true;
                    this.contact_telephone_is_set = true;
                    return;
                }
                else if ( name.equals("correct_div1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'correct_div1' must be String: '"+value+"' is not." );
                    this.correct_div1 = (String) value;
                    if (this.correct_div1_is_set)
                        this.correct_div1_is_modified = true;
                    this.correct_div1_is_set = true;
                    return;
                }
                else if ( name.equals("correct_div2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'correct_div2' must be String: '"+value+"' is not." );
                    this.correct_div2 = (String) value;
                    if (this.correct_div2_is_set)
                        this.correct_div2_is_modified = true;
                    this.correct_div2_is_set = true;
                    return;
                }
                else if ( name.equals("correct_div3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'correct_div3' must be String: '"+value+"' is not." );
                    this.correct_div3 = (String) value;
                    if (this.correct_div3_is_set)
                        this.correct_div3_is_modified = true;
                    this.correct_div3_is_set = true;
                    return;
                }
                else if ( name.equals("correct_div4") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'correct_div4' must be String: '"+value+"' is not." );
                    this.correct_div4 = (String) value;
                    if (this.correct_div4_is_set)
                        this.correct_div4_is_modified = true;
                    this.correct_div4_is_set = true;
                    return;
                }
                else if ( name.equals("correct_div5") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'correct_div5' must be String: '"+value+"' is not." );
                    this.correct_div5 = (String) value;
                    if (this.correct_div5_is_set)
                        this.correct_div5_is_modified = true;
                    this.correct_div5_is_set = true;
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
                if ( name.equals("document") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'document' must be String: '"+value+"' is not." );
                    this.document = (String) value;
                    if (this.document_is_set)
                        this.document_is_modified = true;
                    this.document_is_set = true;
                    return;
                }
                else if ( name.equals("dividend_transfer_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dividend_transfer_div' must be String: '"+value+"' is not." );
                    this.dividend_transfer_div = (String) value;
                    if (this.dividend_transfer_div_is_set)
                        this.dividend_transfer_div_is_modified = true;
                    this.dividend_transfer_div_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("ex_branch_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ex_branch_name' must be String: '"+value+"' is not." );
                    this.ex_branch_name = (String) value;
                    if (this.ex_branch_name_is_set)
                        this.ex_branch_name_is_modified = true;
                    this.ex_branch_name_is_set = true;
                    return;
                }
                else if ( name.equals("ex_account_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ex_account_code' must be String: '"+value+"' is not." );
                    this.ex_account_code = (String) value;
                    if (this.ex_account_code_is_set)
                        this.ex_account_code_is_modified = true;
                    this.ex_account_code_is_set = true;
                    return;
                }
                else if ( name.equals("era_born") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'era_born' must be String: '"+value+"' is not." );
                    this.era_born = (String) value;
                    if (this.era_born_is_set)
                        this.era_born_is_modified = true;
                    this.era_born_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("forign_taxation_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'forign_taxation_div' must be String: '"+value+"' is not." );
                    this.forign_taxation_div = (String) value;
                    if (this.forign_taxation_div_is_set)
                        this.forign_taxation_div_is_modified = true;
                    this.forign_taxation_div_is_set = true;
                    return;
                }
                else if ( name.equals("foreigner_div_broadcast") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'foreigner_div_broadcast' must be String: '"+value+"' is not." );
                    this.foreigner_div_broadcast = (String) value;
                    if (this.foreigner_div_broadcast_is_set)
                        this.foreigner_div_broadcast_is_modified = true;
                    this.foreigner_div_broadcast_is_set = true;
                    return;
                }
                else if ( name.equals("foreigner_div_aviation") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'foreigner_div_aviation' must be String: '"+value+"' is not." );
                    this.foreigner_div_aviation = (String) value;
                    if (this.foreigner_div_aviation_is_set)
                        this.foreigner_div_aviation_is_modified = true;
                    this.foreigner_div_aviation_is_set = true;
                    return;
                }
                else if ( name.equals("foreigner_div_ntt") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'foreigner_div_ntt' must be String: '"+value+"' is not." );
                    this.foreigner_div_ntt = (String) value;
                    if (this.foreigner_div_ntt_is_set)
                        this.foreigner_div_ntt_is_modified = true;
                    this.foreigner_div_ntt_is_set = true;
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
                else if ( name.equals("ifo_acc_open_div_tokyo") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_acc_open_div_tokyo' must be String: '"+value+"' is not." );
                    this.ifo_acc_open_div_tokyo = (String) value;
                    if (this.ifo_acc_open_div_tokyo_is_set)
                        this.ifo_acc_open_div_tokyo_is_modified = true;
                    this.ifo_acc_open_div_tokyo_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_acc_open_div_osaka") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_acc_open_div_osaka' must be String: '"+value+"' is not." );
                    this.ifo_acc_open_div_osaka = (String) value;
                    if (this.ifo_acc_open_div_osaka_is_set)
                        this.ifo_acc_open_div_osaka_is_modified = true;
                    this.ifo_acc_open_div_osaka_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_acc_open_div_nagoya") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_acc_open_div_nagoya' must be String: '"+value+"' is not." );
                    this.ifo_acc_open_div_nagoya = (String) value;
                    if (this.ifo_acc_open_div_nagoya_is_set)
                        this.ifo_acc_open_div_nagoya_is_modified = true;
                    this.ifo_acc_open_div_nagoya_is_set = true;
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
                else if ( name.equals("other_contact_telephone") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'other_contact_telephone' must be String: '"+value+"' is not." );
                    this.other_contact_telephone = (String) value;
                    if (this.other_contact_telephone_is_set)
                        this.other_contact_telephone_is_modified = true;
                    this.other_contact_telephone_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("proam_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'proam_div' must be String: '"+value+"' is not." );
                    this.proam_div = (String) value;
                    if (this.proam_div_is_set)
                        this.proam_div_is_modified = true;
                    this.proam_div_is_set = true;
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
                else if ( name.equals("report") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'report' must be String: '"+value+"' is not." );
                    this.report = (String) value;
                    if (this.report_is_set)
                        this.report_is_modified = true;
                    this.report_is_set = true;
                    return;
                }
                else if ( name.equals("resident") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'resident' must be String: '"+value+"' is not." );
                    this.resident = (String) value;
                    if (this.resident_is_set)
                        this.resident_is_modified = true;
                    this.resident_is_set = true;
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
                else if ( name.equals("sex") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sex' must be String: '"+value+"' is not." );
                    this.sex = (String) value;
                    if (this.sex_is_set)
                        this.sex_is_modified = true;
                    this.sex_is_set = true;
                    return;
                }
                else if ( name.equals("send_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'send_div' must be String: '"+value+"' is not." );
                    this.send_div = (String) value;
                    if (this.send_div_is_set)
                        this.send_div_is_modified = true;
                    this.send_div_is_set = true;
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
                else if ( name.equals("telephone") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'telephone' must be String: '"+value+"' is not." );
                    this.telephone = (String) value;
                    if (this.telephone_is_set)
                        this.telephone_is_modified = true;
                    this.telephone_is_set = true;
                    return;
                }
                else if ( name.equals("taxation_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'taxation_div' must be String: '"+value+"' is not." );
                    this.taxation_div = (String) value;
                    if (this.taxation_div_is_set)
                        this.taxation_div_is_modified = true;
                    this.taxation_div_is_set = true;
                    return;
                }
                else if ( name.equals("taxation_appl_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'taxation_appl_div' must be String: '"+value+"' is not." );
                    this.taxation_appl_div = (String) value;
                    if (this.taxation_appl_div_is_set)
                        this.taxation_appl_div_is_modified = true;
                    this.taxation_appl_div_is_set = true;
                    return;
                }
                else if ( name.equals("taxation_tran_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'taxation_tran_div' must be String: '"+value+"' is not." );
                    this.taxation_tran_div = (String) value;
                    if (this.taxation_tran_div_is_set)
                        this.taxation_tran_div_is_modified = true;
                    this.taxation_tran_div_is_set = true;
                    return;
                }
                else if ( name.equals("trust_via_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trust_via_div' must be String: '"+value+"' is not." );
                    this.trust_via_div = (String) value;
                    if (this.trust_via_div_is_set)
                        this.trust_via_div_is_modified = true;
                    this.trust_via_div_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("unknown_address") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'unknown_address' must be String: '"+value+"' is not." );
                    this.unknown_address = (String) value;
                    if (this.unknown_address_is_set)
                        this.unknown_address_is_modified = true;
                    this.unknown_address_is_set = true;
                    return;
                }
                break;
            case 'z':
                if ( name.equals("zip_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'zip_code' must be String: '"+value+"' is not." );
                    this.zip_code = (String) value;
                    if (this.zip_code_is_set)
                        this.zip_code_is_modified = true;
                    this.zip_code_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
