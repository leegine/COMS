head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.34.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MainAccountAllParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * main_account_allテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link MainAccountAllRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link MainAccountAllParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link MainAccountAllParams}が{@@link MainAccountAllRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MainAccountAllPK 
 * @@see MainAccountAllRow 
 */
public class MainAccountAllParams extends Params implements MainAccountAllRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "main_account_all";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = MainAccountAllRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return MainAccountAllRow.TYPE;
  }


  /** 
   * <em>comp_code</em>カラムの値 
   */
  public  String  comp_code;

  /** 
   * <em>br_code</em>カラムの値 
   */
  public  String  br_code;

  /** 
   * <em>cust_code</em>カラムの値 
   */
  public  String  cust_code;

  /** 
   * <em>cust_code_cd</em>カラムの値 
   */
  public  String  cust_code_cd;

  /** 
   * <em>gen_acc_div</em>カラムの値 
   */
  public  String  gen_acc_div;

  /** 
   * <em>gen_br_del_div</em>カラムの値 
   */
  public  String  gen_br_del_div;

  /** 
   * <em>ruito_acc_open_div</em>カラムの値 
   */
  public  String  ruito_acc_open_div;

  /** 
   * <em>margin_acc_open_div</em>カラムの値 
   */
  public  String  margin_acc_open_div;

  /** 
   * <em>when_issued_acc_open_div</em>カラムの値 
   */
  public  String  when_issued_acc_open_div;

  /** 
   * <em>report_dispatch_stop_div</em>カラムの値 
   */
  public  String  report_dispatch_stop_div;

  /** 
   * <em>doc_dispatch_div</em>カラムの値 
   */
  public  String  doc_dispatch_div;

  /** 
   * <em>gp_br_del_div</em>カラムの値 
   */
  public  String  gp_br_del_div;

  /** 
   * <em>account_open_date</em>カラムの値 
   */
  public  java.sql.Timestamp  account_open_date;

  /** 
   * <em>last_update_date</em>カラムの値 
   */
  public  java.sql.Timestamp  last_update_date;

  /** 
   * <em>sonar_trader_code</em>カラムの値 
   */
  public  String  sonar_trader_code;

  /** 
   * <em>rep_dispatch_stp_bd</em>カラムの値 
   */
  public  String  rep_dispatch_stp_bd;

  /** 
   * <em>occupation</em>カラムの値 
   */
  public  String  occupation;

  /** 
   * <em>safe_cont_open_div</em>カラムの値 
   */
  public  String  safe_cont_open_div;

  /** 
   * <em>foreign_sec_acc_open_div</em>カラムの値 
   */
  public  String  foreign_sec_acc_open_div;

  /** 
   * <em>tokuyu_acc_open_div</em>カラムの値 
   */
  public  String  tokuyu_acc_open_div;

  /** 
   * <em>gold_acc_open_div</em>カラムの値 
   */
  public  String  gold_acc_open_div;

  /** 
   * <em>total_trade_open_div</em>カラムの値 
   */
  public  String  total_trade_open_div;

  /** 
   * <em>tie_up_loan_open_div</em>カラムの値 
   */
  public  String  tie_up_loan_open_div;

  /** 
   * <em>ifo_acc_open_div_tokyo</em>カラムの値 
   */
  public  String  ifo_acc_open_div_tokyo;

  /** 
   * <em>address_unknown</em>カラムの値 
   */
  public  String  address_unknown;

  /** 
   * <em>cust_div</em>カラムの値 
   */
  public  String  cust_div;

  /** 
   * <em>deposit_div</em>カラムの値 
   */
  public  String  deposit_div;

  /** 
   * <em>all_substitution_div</em>カラムの値 
   */
  public  String  all_substitution_div;

  /** 
   * <em>ins_loan_bill_mthd_div</em>カラムの値 
   */
  public  String  ins_loan_bill_mthd_div;

  /** 
   * <em>ins_loan_cer_mthd_div</em>カラムの値 
   */
  public  String  ins_loan_cer_mthd_div;

  /** 
   * <em>ins_loan_clause_mthd_div</em>カラムの値 
   */
  public  String  ins_loan_clause_mthd_div;

  /** 
   * <em>dom_tax_div</em>カラムの値 
   */
  public  String  dom_tax_div;

  /** 
   * <em>client_trader_code</em>カラムの値 
   */
  public  String  client_trader_code;

  /** 
   * <em>telephone</em>カラムの値 
   */
  public  String  telephone;

  /** 
   * <em>family_name_alt1</em>カラムの値 
   */
  public  String  family_name_alt1;

  /** 
   * <em>zip_code</em>カラムの値 
   */
  public  String  zip_code;

  /** 
   * <em>prefecture</em>カラムの値 
   */
  public  String  prefecture;

  /** 
   * <em>comma</em>カラムの値 
   */
  public  String  comma;

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

  /** 
   * <em>family_name</em>カラムの値 
   */
  public  String  family_name;

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
   * <em>contact_address_telephone</em>カラムの値 
   */
  public  String  contact_address_telephone;

  /** 
   * <em>contact_address</em>カラムの値 
   */
  public  String  contact_address;

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
   * <em>before_acc_trans_br_code</em>カラムの値 
   */
  public  String  before_acc_trans_br_code;

  /** 
   * <em>before_acc_trans_cust_code</em>カラムの値 
   */
  public  String  before_acc_trans_cust_code;

  /** 
   * <em>d_card_appli_date</em>カラムの値 
   */
  public  java.sql.Timestamp  d_card_appli_date;

  /** 
   * <em>d_card_issue_date</em>カラムの値 
   */
  public  java.sql.Timestamp  d_card_issue_date;

  /** 
   * <em>d_card_password</em>カラムの値 
   */
  public  String  d_card_password;

  /** 
   * <em>d_card_issue_reason</em>カラムの値 
   */
  public  String  d_card_issue_reason;

  /** 
   * <em>d_card_issue_number</em>カラムの値 
   */
  public  String  d_card_issue_number;

  /** 
   * <em>d_card_stop_date</em>カラムの値 
   */
  public  java.sql.Timestamp  d_card_stop_date;

  /** 
   * <em>d_card_stop_reason</em>カラムの値 
   */
  public  String  d_card_stop_reason;

  /** 
   * <em>d_card_cancel_date</em>カラムの値 
   */
  public  java.sql.Timestamp  d_card_cancel_date;

  /** 
   * <em>d_card_cancel_reason</em>カラムの値 
   */
  public  String  d_card_cancel_reason;

  /** 
   * <em>d_card_name</em>カラムの値 
   */
  public  String  d_card_name;

  /** 
   * <em>pass_err_code_type</em>カラムの値 
   */
  public  String  pass_err_code_type;

  /** 
   * <em>pass_err_code_number</em>カラムの値 
   */
  public  String  pass_err_code_number;

  /** 
   * <em>pass_err_code_chg_date</em>カラムの値 
   */
  public  java.sql.Timestamp  pass_err_code_chg_date;

  /** 
   * <em>pass_err_code_chg_time</em>カラムの値 
   */
  public  String  pass_err_code_chg_time;

  /** 
   * <em>ans_cust_div</em>カラムの値 
   */
  public  String  ans_cust_div;

  /** 
   * <em>ans_stock_appli_cate</em>カラムの値 
   */
  public  java.sql.Timestamp  ans_stock_appli_cate;

  /** 
   * <em>dummy1</em>カラムの値 
   */
  public  String  dummy1;

  /** 
   * <em>self_assessed_sep_tax</em>カラムの値 
   */
  public  String  self_assessed_sep_tax;

  /** 
   * <em>tokuyu_appli_div</em>カラムの値 
   */
  public  String  tokuyu_appli_div;

  /** 
   * <em>maruyu_appli_div</em>カラムの値 
   */
  public  String  maruyu_appli_div;

  /** 
   * <em>tokuyu_laundering_div</em>カラムの値 
   */
  public  String  tokuyu_laundering_div;

  /** 
   * <em>maruyu_laundering_div</em>カラムの値 
   */
  public  String  maruyu_laundering_div;

  /** 
   * <em>tax_div</em>カラムの値 
   */
  public  String  tax_div;

  /** 
   * <em>dummy2</em>カラムの値 
   */
  public  String  dummy2;

  /** 
   * <em>ht_settlement_way</em>カラムの値 
   */
  public  String  ht_settlement_way;

  /** 
   * <em>dummy3</em>カラムの値 
   */
  public  String  dummy3;

  /** 
   * <em>total_tax_identity</em>カラムの値 
   */
  public  String  total_tax_identity;

  /** 
   * <em>dummy4</em>カラムの値 
   */
  public  String  dummy4;

  /** 
   * <em>ifo_acc_open_div_osaka</em>カラムの値 
   */
  public  String  ifo_acc_open_div_osaka;

  /** 
   * <em>mort_trade_open_div</em>カラムの値 
   */
  public  String  mort_trade_open_div;

  /** 
   * <em>ifo_acc_open_div_nagoya</em>カラムの値 
   */
  public  String  ifo_acc_open_div_nagoya;

  /** 
   * <em>os_fnc_futures_open_div</em>カラムの値 
   */
  public  String  os_fnc_futures_open_div;

  /** 
   * <em>os_sec_futures_open_div</em>カラムの値 
   */
  public  String  os_sec_futures_open_div;

  /** 
   * <em>tokyo_fnc_futures_open_div</em>カラムの値 
   */
  public  String  tokyo_fnc_futures_open_div;

  /** 
   * <em>for_war_trade_open_div</em>カラムの値 
   */
  public  String  for_war_trade_open_div;

  /** 
   * <em>tentou_trade_open_div</em>カラムの値 
   */
  public  String  tentou_trade_open_div;

  /** 
   * <em>represent_div</em>カラムの値 
   */
  public  String  represent_div;

  /** 
   * <em>family_nurturer_code</em>カラムの値 
   */
  public  String  family_nurturer_code;

  /** 
   * <em>family_unit_adimin</em>カラムの値 
   */
  public  String  family_unit_adimin;

  /** 
   * <em>new_monthly_div</em>カラムの値 
   */
  public  String  new_monthly_div;

  /** 
   * <em>statement_skip1_div</em>カラムの値 
   */
  public  String  statement_skip1_div;

  /** 
   * <em>statement_skip2_div</em>カラムの値 
   */
  public  String  statement_skip2_div;

  /** 
   * <em>family_unit_del_div</em>カラムの値 
   */
  public  String  family_unit_del_div;

  /** 
   * <em>dummy5</em>カラムの値 
   */
  public  String  dummy5;

  /** 
   * <em>br_dispatch_div</em>カラムの値 
   */
  public  String  br_dispatch_div;

  /** 
   * <em>maruyu_grade_div</em>カラムの値 
   */
  public  String  maruyu_grade_div;

  /** 
   * <em>dummy6</em>カラムの値 
   */
  public  String  dummy6;

  /** 
   * <em>dummy7</em>カラムの値 
   */
  public  String  dummy7;

  /** 
   * <em>trans_tax_div_acc_date</em>カラムの値 
   */
  public  java.sql.Timestamp  trans_tax_div_acc_date;

  /** 
   * <em>trans_tax_div</em>カラムの値 
   */
  public  String  trans_tax_div;

  /** 
   * <em>resident</em>カラムの値 
   */
  public  String  resident;

  /** 
   * <em>bond_d_and_c_open_div</em>カラムの値 
   */
  public  String  bond_d_and_c_open_div;

  /** 
   * <em>os_cd_cp_open_div</em>カラムの値 
   */
  public  String  os_cd_cp_open_div;

  /** 
   * <em>new_tb_open_div</em>カラムの値 
   */
  public  String  new_tb_open_div;

  /** 
   * <em>dom_cp_open_div</em>カラムの値 
   */
  public  String  dom_cp_open_div;

  /** 
   * <em>bond_tentou_op_open_div</em>カラムの値 
   */
  public  String  bond_tentou_op_open_div;

  /** 
   * <em>t_bond_futures_open_div</em>カラムの値 
   */
  public  String  t_bond_futures_open_div;

  /** 
   * <em>dom_war_trade_open_div</em>カラムの値 
   */
  public  String  dom_war_trade_open_div;

  /** 
   * <em>dummy8</em>カラムの値 
   */
  public  String  dummy8;

  /** 
   * <em>dom_for_bond_open_div</em>カラムの値 
   */
  public  String  dom_for_bond_open_div;

  /** 
   * <em>tentou_sp_rule_open_div</em>カラムの値 
   */
  public  String  tentou_sp_rule_open_div;

  /** 
   * <em>dummy9</em>カラムの値 
   */
  public  String  dummy9;

  /** 
   * <em>dummy10</em>カラムの値 
   */
  public  String  dummy10;

  /** 
   * <em>dummy11</em>カラムの値 
   */
  public  String  dummy11;

  /** 
   * <em>dummy12</em>カラムの値 
   */
  public  String  dummy12;

  /** 
   * <em>dummy13</em>カラムの値 
   */
  public  String  dummy13;

  /** 
   * <em>mrf_acc_open_div</em>カラムの値 
   */
  public  String  mrf_acc_open_div;

  /** 
   * <em>safe_cont_div</em>カラムの値 
   */
  public  String  safe_cont_div;

  /** 
   * <em>foreign_cont_div</em>カラムの値 
   */
  public  String  foreign_cont_div;

  /** 
   * <em>dummy14</em>カラムの値 
   */
  public  String  dummy14;

  /** 
   * <em>gold_cont_div</em>カラムの値 
   */
  public  String  gold_cont_div;

  /** 
   * <em>margin_cont_div</em>カラムの値 
   */
  public  String  margin_cont_div;

  /** 
   * <em>when_issued_cont_div</em>カラムの値 
   */
  public  String  when_issued_cont_div;

  /** 
   * <em>futures_op_cont_div_tokyo</em>カラムの値 
   */
  public  String  futures_op_cont_div_tokyo;

  /** 
   * <em>dummy15</em>カラムの値 
   */
  public  String  dummy15;

  /** 
   * <em>futures_op_cont_div_osaka</em>カラムの値 
   */
  public  String  futures_op_cont_div_osaka;

  /** 
   * <em>tokyo_mothers_cont_div</em>カラムの値 
   */
  public  String  tokyo_mothers_cont_div;

  /** 
   * <em>futures_op_cont_div_nagoya</em>カラムの値 
   */
  public  String  futures_op_cont_div_nagoya;

  /** 
   * <em>nq_j_gl_cont_div</em>カラムの値 
   */
  public  String  nq_j_gl_cont_div;

  /** 
   * <em>osaka_nw_mkt_cont_div</em>カラムの値 
   */
  public  String  osaka_nw_mkt_cont_div;

  /** 
   * <em>nagoya_grw_cpy_mkt_cont_div</em>カラムの値 
   */
  public  String  nagoya_grw_cpy_mkt_cont_div;

  /** 
   * <em>for_war_trade_cont_div</em>カラムの値 
   */
  public  String  for_war_trade_cont_div;

  /** 
   * <em>tentou_trade_cont_div</em>カラムの値 
   */
  public  String  tentou_trade_cont_div;

  /** 
   * <em>bond_d_and_c_cont_div</em>カラムの値 
   */
  public  String  bond_d_and_c_cont_div;

  /** 
   * <em>sapporo_amb_cont_div</em>カラムの値 
   */
  public  String  sapporo_amb_cont_div;

  /** 
   * <em>new_tb_cont_div</em>カラムの値 
   */
  public  String  new_tb_cont_div;

  /** 
   * <em>gen_credit_acc_cont_div</em>カラムの値 
   */
  public  String  gen_credit_acc_cont_div;

  /** 
   * <em>bond_tentou_op_cont_div</em>カラムの値 
   */
  public  String  bond_tentou_op_cont_div;

  /** 
   * <em>fukuoka_qb_cont_div</em>カラムの値 
   */
  public  String  fukuoka_qb_cont_div;

  /** 
   * <em>dom_war_trade_cont_div</em>カラムの値 
   */
  public  String  dom_war_trade_cont_div;

  /** 
   * <em>tentou_sec_basis_cont_div</em>カラムの値 
   */
  public  String  tentou_sec_basis_cont_div;

  /** 
   * <em>dom_for_bond_cont_div</em>カラムの値 
   */
  public  String  dom_for_bond_cont_div;

  /** 
   * <em>tentou_sp_rule_cont_div</em>カラムの値 
   */
  public  String  tentou_sp_rule_cont_div;

  /** 
   * <em>dummy16</em>カラムの値 
   */
  public  String  dummy16;

  /** 
   * <em>dummy17</em>カラムの値 
   */
  public  String  dummy17;

  /** 
   * <em>dummy18</em>カラムの値 
   */
  public  String  dummy18;

  /** 
   * <em>dummy19</em>カラムの値 
   */
  public  String  dummy19;

  /** 
   * <em>dummy20</em>カラムの値 
   */
  public  String  dummy20;

  /** 
   * <em>mrf_contract_div</em>カラムの値 
   */
  public  String  mrf_contract_div;

  /** 
   * <em>new_account_div</em>カラムの値 
   */
  public  String  new_account_div;

  /** 
   * <em>via_trust_bank_div</em>カラムの値 
   */
  public  String  via_trust_bank_div;

  /** 
   * <em>class_div</em>カラムの値 
   */
  public  String  class_div;

  /** 
   * <em>address_code</em>カラムの値 
   */
  public  String  address_code;

  /** 
   * <em>person_identify</em>カラムの値 
   */
  public  String  person_identify;

  /** 
   * <em>cancel_div</em>カラムの値 
   */
  public  String  cancel_div;

  /** 
   * <em>add_chg_div</em>カラムの値 
   */
  public  String  add_chg_div;

  /** 
   * <em>reserve</em>カラムの値 
   */
  public  String  reserve;

  /** 
   * <em>org_deposit_div</em>カラムの値 
   */
  public  String  org_deposit_div;

  /** 
   * <em>eq_hold_rep_div</em>カラムの値 
   */
  public  String  eq_hold_rep_div;

  /** 
   * <em>chkup_rep_div</em>カラムの値 
   */
  public  String  chkup_rep_div;

  /** 
   * <em>fax</em>カラムの値 
   */
  public  String  fax;

  /** 
   * <em>hq_input_div</em>カラムの値 
   */
  public  String  hq_input_div;

  /** 
   * <em>yellow_customer</em>カラムの値 
   */
  public  String  yellow_customer;

  /** 
   * <em>dummy21</em>カラムの値 
   */
  public  String  dummy21;

  /** 
   * <em>dummy22</em>カラムの値 
   */
  public  String  dummy22;

  /** 
   * <em>bond_butt_div</em>カラムの値 
   */
  public  String  bond_butt_div;

  /** 
   * <em>hofuri_entry</em>カラムの値 
   */
  public  String  hofuri_entry;

  /** 
   * <em>agreed_non_pkg_div</em>カラムの値 
   */
  public  String  agreed_non_pkg_div;

  /** 
   * <em>position_report_cycle_div</em>カラムの値 
   */
  public  String  position_report_cycle_div;

  /** 
   * <em>position_report_div</em>カラムの値 
   */
  public  String  position_report_div;

  /** 
   * <em>certificate_deposit_flag</em>カラムの値 
   */
  public  String  certificate_deposit_flag;

  /** 
   * <em>account_statement_flag</em>カラムの値 
   */
  public  String  account_statement_flag;

  /** 
   * <em>trading_report_div</em>カラムの値 
   */
  public  String  trading_report_div;

  /** 
   * <em>inv_trast_ope_report</em>カラムの値 
   */
  public  String  inv_trast_ope_report;

  /** 
   * <em>tax_type</em>カラムの値 
   */
  public  String  tax_type;

  /** 
   * <em>margin_tax_type</em>カラムの値 
   */
  public  String  margin_tax_type;

  /** 
   * <em>equity_sp_acc_open_date</em>カラムの値 
   */
  public  java.sql.Timestamp  equity_sp_acc_open_date;

  /** 
   * <em>margin_sp_acc_open_date</em>カラムの値 
   */
  public  java.sql.Timestamp  margin_sp_acc_open_date;

  /** 
   * <em>tax_type_last</em>カラムの値 
   */
  public  String  tax_type_last;

  /** 
   * <em>margin_tax_type_last</em>カラムの値 
   */
  public  String  margin_tax_type_last;

  /** 
   * <em>tax_type_next</em>カラムの値 
   */
  public  String  tax_type_next;

  /** 
   * <em>margin_tax_type_next</em>カラムの値 
   */
  public  String  margin_tax_type_next;

  /** 
   * <em>fluct_date</em>カラムの値 
   */
  public  java.sql.Timestamp  fluct_date;

  /** 
   * <em>margin_fluct_date</em>カラムの値 
   */
  public  java.sql.Timestamp  margin_fluct_date;

  /** 
   * <em>local_tax_div_last</em>カラムの値 
   */
  public  String  local_tax_div_last;

  /** 
   * <em>local_tax_div</em>カラムの値 
   */
  public  String  local_tax_div;

  /** 
   * <em>local_tax_div_next</em>カラムの値 
   */
  public  String  local_tax_div_next;

  /** 
   * <em>qualified_inst_investor_div</em>カラムの値 
   */
  public  String  qualified_inst_investor_div;

  /** 
   * <em>quoto_type</em>カラムの値 
   */
  public  String  quoto_type;

  /** 
   * <em>sp_acc_abolish_date</em>カラムの値 
   */
  public  java.sql.Timestamp  sp_acc_abolish_date;

  /** 
   * <em>sp_mng_acc_open_div</em>カラムの値 
   */
  public  String  sp_mng_acc_open_div;

  /** 
   * <em>reserve_area</em>カラムの値 
   */
  public  String  reserve_area;

  /** 
   * <em>web3_encrypted_password</em>カラムの値 
   */
  public  String  web3_encrypted_password;

  /** 
   * <em>xtrade_encrypted_password</em>カラムの値 
   */
  public  String  xtrade_encrypted_password;

  /** 
   * <em>broadcast_law</em>カラムの値 
   */
  public  String  broadcast_law;

  /** 
   * <em>aviation_law</em>カラムの値 
   */
  public  String  aviation_law;

  /** 
   * <em>ntt_law</em>カラムの値 
   */
  public  String  ntt_law;

  /** 
   * <em>dividend_trans_designate</em>カラムの値 
   */
  public  String  dividend_trans_designate;

  /** 
   * <em>standing_proxy</em>カラムの値 
   */
  public  String  standing_proxy;

  /** 
   * <em>statutory_agent</em>カラムの値 
   */
  public  String  statutory_agent;

  /** 
   * <em>affiliate_account_code</em>カラムの値 
   */
  public  String  affiliate_account_code;

  /** 
   * <em>agency_notify_cmp_div</em>カラムの値 
   */
  public  String  agency_notify_cmp_div;

  private boolean comp_code_is_set = false;
  private boolean comp_code_is_modified = false;
  private boolean gen_acc_div_is_set = false;
  private boolean gen_acc_div_is_modified = false;
  private boolean gen_br_del_div_is_set = false;
  private boolean gen_br_del_div_is_modified = false;
  private boolean ruito_acc_open_div_is_set = false;
  private boolean ruito_acc_open_div_is_modified = false;
  private boolean margin_acc_open_div_is_set = false;
  private boolean margin_acc_open_div_is_modified = false;
  private boolean when_issued_acc_open_div_is_set = false;
  private boolean when_issued_acc_open_div_is_modified = false;
  private boolean report_dispatch_stop_div_is_set = false;
  private boolean report_dispatch_stop_div_is_modified = false;
  private boolean doc_dispatch_div_is_set = false;
  private boolean doc_dispatch_div_is_modified = false;
  private boolean gp_br_del_div_is_set = false;
  private boolean gp_br_del_div_is_modified = false;
  private boolean account_open_date_is_set = false;
  private boolean account_open_date_is_modified = false;
  private boolean last_update_date_is_set = false;
  private boolean last_update_date_is_modified = false;
  private boolean br_code_is_set = false;
  private boolean br_code_is_modified = false;
  private boolean cust_code_is_set = false;
  private boolean cust_code_is_modified = false;
  private boolean cust_code_cd_is_set = false;
  private boolean cust_code_cd_is_modified = false;
  private boolean sonar_trader_code_is_set = false;
  private boolean sonar_trader_code_is_modified = false;
  private boolean rep_dispatch_stp_bd_is_set = false;
  private boolean rep_dispatch_stp_bd_is_modified = false;
  private boolean occupation_is_set = false;
  private boolean occupation_is_modified = false;
  private boolean safe_cont_open_div_is_set = false;
  private boolean safe_cont_open_div_is_modified = false;
  private boolean foreign_sec_acc_open_div_is_set = false;
  private boolean foreign_sec_acc_open_div_is_modified = false;
  private boolean tokuyu_acc_open_div_is_set = false;
  private boolean tokuyu_acc_open_div_is_modified = false;
  private boolean gold_acc_open_div_is_set = false;
  private boolean gold_acc_open_div_is_modified = false;
  private boolean total_trade_open_div_is_set = false;
  private boolean total_trade_open_div_is_modified = false;
  private boolean tie_up_loan_open_div_is_set = false;
  private boolean tie_up_loan_open_div_is_modified = false;
  private boolean ifo_acc_open_div_tokyo_is_set = false;
  private boolean ifo_acc_open_div_tokyo_is_modified = false;
  private boolean address_unknown_is_set = false;
  private boolean address_unknown_is_modified = false;
  private boolean cust_div_is_set = false;
  private boolean cust_div_is_modified = false;
  private boolean deposit_div_is_set = false;
  private boolean deposit_div_is_modified = false;
  private boolean all_substitution_div_is_set = false;
  private boolean all_substitution_div_is_modified = false;
  private boolean ins_loan_bill_mthd_div_is_set = false;
  private boolean ins_loan_bill_mthd_div_is_modified = false;
  private boolean ins_loan_cer_mthd_div_is_set = false;
  private boolean ins_loan_cer_mthd_div_is_modified = false;
  private boolean ins_loan_clause_mthd_div_is_set = false;
  private boolean ins_loan_clause_mthd_div_is_modified = false;
  private boolean dom_tax_div_is_set = false;
  private boolean dom_tax_div_is_modified = false;
  private boolean client_trader_code_is_set = false;
  private boolean client_trader_code_is_modified = false;
  private boolean telephone_is_set = false;
  private boolean telephone_is_modified = false;
  private boolean family_name_alt1_is_set = false;
  private boolean family_name_alt1_is_modified = false;
  private boolean zip_code_is_set = false;
  private boolean zip_code_is_modified = false;
  private boolean prefecture_is_set = false;
  private boolean prefecture_is_modified = false;
  private boolean comma_is_set = false;
  private boolean comma_is_modified = false;
  private boolean address_line1_kana_is_set = false;
  private boolean address_line1_kana_is_modified = false;
  private boolean address_line2_kana_is_set = false;
  private boolean address_line2_kana_is_modified = false;
  private boolean address_line3_kana_is_set = false;
  private boolean address_line3_kana_is_modified = false;
  private boolean family_name_is_set = false;
  private boolean family_name_is_modified = false;
  private boolean address_line1_is_set = false;
  private boolean address_line1_is_modified = false;
  private boolean address_line2_is_set = false;
  private boolean address_line2_is_modified = false;
  private boolean address_line3_is_set = false;
  private boolean address_line3_is_modified = false;
  private boolean contact_address_telephone_is_set = false;
  private boolean contact_address_telephone_is_modified = false;
  private boolean contact_address_is_set = false;
  private boolean contact_address_is_modified = false;
  private boolean era_born_is_set = false;
  private boolean era_born_is_modified = false;
  private boolean born_date_is_set = false;
  private boolean born_date_is_modified = false;
  private boolean sex_is_set = false;
  private boolean sex_is_modified = false;
  private boolean before_acc_trans_br_code_is_set = false;
  private boolean before_acc_trans_br_code_is_modified = false;
  private boolean before_acc_trans_cust_code_is_set = false;
  private boolean before_acc_trans_cust_code_is_modified = false;
  private boolean d_card_appli_date_is_set = false;
  private boolean d_card_appli_date_is_modified = false;
  private boolean d_card_issue_date_is_set = false;
  private boolean d_card_issue_date_is_modified = false;
  private boolean d_card_password_is_set = false;
  private boolean d_card_password_is_modified = false;
  private boolean d_card_issue_reason_is_set = false;
  private boolean d_card_issue_reason_is_modified = false;
  private boolean d_card_issue_number_is_set = false;
  private boolean d_card_issue_number_is_modified = false;
  private boolean d_card_stop_date_is_set = false;
  private boolean d_card_stop_date_is_modified = false;
  private boolean d_card_stop_reason_is_set = false;
  private boolean d_card_stop_reason_is_modified = false;
  private boolean d_card_cancel_date_is_set = false;
  private boolean d_card_cancel_date_is_modified = false;
  private boolean d_card_cancel_reason_is_set = false;
  private boolean d_card_cancel_reason_is_modified = false;
  private boolean d_card_name_is_set = false;
  private boolean d_card_name_is_modified = false;
  private boolean pass_err_code_type_is_set = false;
  private boolean pass_err_code_type_is_modified = false;
  private boolean pass_err_code_number_is_set = false;
  private boolean pass_err_code_number_is_modified = false;
  private boolean pass_err_code_chg_date_is_set = false;
  private boolean pass_err_code_chg_date_is_modified = false;
  private boolean pass_err_code_chg_time_is_set = false;
  private boolean pass_err_code_chg_time_is_modified = false;
  private boolean ans_cust_div_is_set = false;
  private boolean ans_cust_div_is_modified = false;
  private boolean ans_stock_appli_cate_is_set = false;
  private boolean ans_stock_appli_cate_is_modified = false;
  private boolean dummy1_is_set = false;
  private boolean dummy1_is_modified = false;
  private boolean self_assessed_sep_tax_is_set = false;
  private boolean self_assessed_sep_tax_is_modified = false;
  private boolean tokuyu_appli_div_is_set = false;
  private boolean tokuyu_appli_div_is_modified = false;
  private boolean maruyu_appli_div_is_set = false;
  private boolean maruyu_appli_div_is_modified = false;
  private boolean tokuyu_laundering_div_is_set = false;
  private boolean tokuyu_laundering_div_is_modified = false;
  private boolean maruyu_laundering_div_is_set = false;
  private boolean maruyu_laundering_div_is_modified = false;
  private boolean tax_div_is_set = false;
  private boolean tax_div_is_modified = false;
  private boolean dummy2_is_set = false;
  private boolean dummy2_is_modified = false;
  private boolean ht_settlement_way_is_set = false;
  private boolean ht_settlement_way_is_modified = false;
  private boolean dummy3_is_set = false;
  private boolean dummy3_is_modified = false;
  private boolean total_tax_identity_is_set = false;
  private boolean total_tax_identity_is_modified = false;
  private boolean dummy4_is_set = false;
  private boolean dummy4_is_modified = false;
  private boolean ifo_acc_open_div_osaka_is_set = false;
  private boolean ifo_acc_open_div_osaka_is_modified = false;
  private boolean mort_trade_open_div_is_set = false;
  private boolean mort_trade_open_div_is_modified = false;
  private boolean ifo_acc_open_div_nagoya_is_set = false;
  private boolean ifo_acc_open_div_nagoya_is_modified = false;
  private boolean os_fnc_futures_open_div_is_set = false;
  private boolean os_fnc_futures_open_div_is_modified = false;
  private boolean os_sec_futures_open_div_is_set = false;
  private boolean os_sec_futures_open_div_is_modified = false;
  private boolean tokyo_fnc_futures_open_div_is_set = false;
  private boolean tokyo_fnc_futures_open_div_is_modified = false;
  private boolean for_war_trade_open_div_is_set = false;
  private boolean for_war_trade_open_div_is_modified = false;
  private boolean tentou_trade_open_div_is_set = false;
  private boolean tentou_trade_open_div_is_modified = false;
  private boolean represent_div_is_set = false;
  private boolean represent_div_is_modified = false;
  private boolean family_nurturer_code_is_set = false;
  private boolean family_nurturer_code_is_modified = false;
  private boolean family_unit_adimin_is_set = false;
  private boolean family_unit_adimin_is_modified = false;
  private boolean new_monthly_div_is_set = false;
  private boolean new_monthly_div_is_modified = false;
  private boolean statement_skip1_div_is_set = false;
  private boolean statement_skip1_div_is_modified = false;
  private boolean statement_skip2_div_is_set = false;
  private boolean statement_skip2_div_is_modified = false;
  private boolean family_unit_del_div_is_set = false;
  private boolean family_unit_del_div_is_modified = false;
  private boolean dummy5_is_set = false;
  private boolean dummy5_is_modified = false;
  private boolean br_dispatch_div_is_set = false;
  private boolean br_dispatch_div_is_modified = false;
  private boolean maruyu_grade_div_is_set = false;
  private boolean maruyu_grade_div_is_modified = false;
  private boolean dummy6_is_set = false;
  private boolean dummy6_is_modified = false;
  private boolean dummy7_is_set = false;
  private boolean dummy7_is_modified = false;
  private boolean trans_tax_div_acc_date_is_set = false;
  private boolean trans_tax_div_acc_date_is_modified = false;
  private boolean trans_tax_div_is_set = false;
  private boolean trans_tax_div_is_modified = false;
  private boolean resident_is_set = false;
  private boolean resident_is_modified = false;
  private boolean bond_d_and_c_open_div_is_set = false;
  private boolean bond_d_and_c_open_div_is_modified = false;
  private boolean os_cd_cp_open_div_is_set = false;
  private boolean os_cd_cp_open_div_is_modified = false;
  private boolean new_tb_open_div_is_set = false;
  private boolean new_tb_open_div_is_modified = false;
  private boolean dom_cp_open_div_is_set = false;
  private boolean dom_cp_open_div_is_modified = false;
  private boolean bond_tentou_op_open_div_is_set = false;
  private boolean bond_tentou_op_open_div_is_modified = false;
  private boolean t_bond_futures_open_div_is_set = false;
  private boolean t_bond_futures_open_div_is_modified = false;
  private boolean dom_war_trade_open_div_is_set = false;
  private boolean dom_war_trade_open_div_is_modified = false;
  private boolean dummy8_is_set = false;
  private boolean dummy8_is_modified = false;
  private boolean dom_for_bond_open_div_is_set = false;
  private boolean dom_for_bond_open_div_is_modified = false;
  private boolean tentou_sp_rule_open_div_is_set = false;
  private boolean tentou_sp_rule_open_div_is_modified = false;
  private boolean dummy9_is_set = false;
  private boolean dummy9_is_modified = false;
  private boolean dummy10_is_set = false;
  private boolean dummy10_is_modified = false;
  private boolean dummy11_is_set = false;
  private boolean dummy11_is_modified = false;
  private boolean dummy12_is_set = false;
  private boolean dummy12_is_modified = false;
  private boolean dummy13_is_set = false;
  private boolean dummy13_is_modified = false;
  private boolean mrf_acc_open_div_is_set = false;
  private boolean mrf_acc_open_div_is_modified = false;
  private boolean safe_cont_div_is_set = false;
  private boolean safe_cont_div_is_modified = false;
  private boolean foreign_cont_div_is_set = false;
  private boolean foreign_cont_div_is_modified = false;
  private boolean dummy14_is_set = false;
  private boolean dummy14_is_modified = false;
  private boolean gold_cont_div_is_set = false;
  private boolean gold_cont_div_is_modified = false;
  private boolean margin_cont_div_is_set = false;
  private boolean margin_cont_div_is_modified = false;
  private boolean when_issued_cont_div_is_set = false;
  private boolean when_issued_cont_div_is_modified = false;
  private boolean futures_op_cont_div_tokyo_is_set = false;
  private boolean futures_op_cont_div_tokyo_is_modified = false;
  private boolean dummy15_is_set = false;
  private boolean dummy15_is_modified = false;
  private boolean futures_op_cont_div_osaka_is_set = false;
  private boolean futures_op_cont_div_osaka_is_modified = false;
  private boolean tokyo_mothers_cont_div_is_set = false;
  private boolean tokyo_mothers_cont_div_is_modified = false;
  private boolean futures_op_cont_div_nagoya_is_set = false;
  private boolean futures_op_cont_div_nagoya_is_modified = false;
  private boolean nq_j_gl_cont_div_is_set = false;
  private boolean nq_j_gl_cont_div_is_modified = false;
  private boolean osaka_nw_mkt_cont_div_is_set = false;
  private boolean osaka_nw_mkt_cont_div_is_modified = false;
  private boolean nagoya_grw_cpy_mkt_cont_div_is_set = false;
  private boolean nagoya_grw_cpy_mkt_cont_div_is_modified = false;
  private boolean for_war_trade_cont_div_is_set = false;
  private boolean for_war_trade_cont_div_is_modified = false;
  private boolean tentou_trade_cont_div_is_set = false;
  private boolean tentou_trade_cont_div_is_modified = false;
  private boolean bond_d_and_c_cont_div_is_set = false;
  private boolean bond_d_and_c_cont_div_is_modified = false;
  private boolean sapporo_amb_cont_div_is_set = false;
  private boolean sapporo_amb_cont_div_is_modified = false;
  private boolean new_tb_cont_div_is_set = false;
  private boolean new_tb_cont_div_is_modified = false;
  private boolean gen_credit_acc_cont_div_is_set = false;
  private boolean gen_credit_acc_cont_div_is_modified = false;
  private boolean bond_tentou_op_cont_div_is_set = false;
  private boolean bond_tentou_op_cont_div_is_modified = false;
  private boolean fukuoka_qb_cont_div_is_set = false;
  private boolean fukuoka_qb_cont_div_is_modified = false;
  private boolean dom_war_trade_cont_div_is_set = false;
  private boolean dom_war_trade_cont_div_is_modified = false;
  private boolean tentou_sec_basis_cont_div_is_set = false;
  private boolean tentou_sec_basis_cont_div_is_modified = false;
  private boolean dom_for_bond_cont_div_is_set = false;
  private boolean dom_for_bond_cont_div_is_modified = false;
  private boolean tentou_sp_rule_cont_div_is_set = false;
  private boolean tentou_sp_rule_cont_div_is_modified = false;
  private boolean dummy16_is_set = false;
  private boolean dummy16_is_modified = false;
  private boolean dummy17_is_set = false;
  private boolean dummy17_is_modified = false;
  private boolean dummy18_is_set = false;
  private boolean dummy18_is_modified = false;
  private boolean dummy19_is_set = false;
  private boolean dummy19_is_modified = false;
  private boolean dummy20_is_set = false;
  private boolean dummy20_is_modified = false;
  private boolean mrf_contract_div_is_set = false;
  private boolean mrf_contract_div_is_modified = false;
  private boolean new_account_div_is_set = false;
  private boolean new_account_div_is_modified = false;
  private boolean via_trust_bank_div_is_set = false;
  private boolean via_trust_bank_div_is_modified = false;
  private boolean class_div_is_set = false;
  private boolean class_div_is_modified = false;
  private boolean address_code_is_set = false;
  private boolean address_code_is_modified = false;
  private boolean person_identify_is_set = false;
  private boolean person_identify_is_modified = false;
  private boolean cancel_div_is_set = false;
  private boolean cancel_div_is_modified = false;
  private boolean add_chg_div_is_set = false;
  private boolean add_chg_div_is_modified = false;
  private boolean reserve_is_set = false;
  private boolean reserve_is_modified = false;
  private boolean org_deposit_div_is_set = false;
  private boolean org_deposit_div_is_modified = false;
  private boolean eq_hold_rep_div_is_set = false;
  private boolean eq_hold_rep_div_is_modified = false;
  private boolean chkup_rep_div_is_set = false;
  private boolean chkup_rep_div_is_modified = false;
  private boolean fax_is_set = false;
  private boolean fax_is_modified = false;
  private boolean hq_input_div_is_set = false;
  private boolean hq_input_div_is_modified = false;
  private boolean yellow_customer_is_set = false;
  private boolean yellow_customer_is_modified = false;
  private boolean dummy21_is_set = false;
  private boolean dummy21_is_modified = false;
  private boolean dummy22_is_set = false;
  private boolean dummy22_is_modified = false;
  private boolean bond_butt_div_is_set = false;
  private boolean bond_butt_div_is_modified = false;
  private boolean hofuri_entry_is_set = false;
  private boolean hofuri_entry_is_modified = false;
  private boolean agreed_non_pkg_div_is_set = false;
  private boolean agreed_non_pkg_div_is_modified = false;
  private boolean position_report_cycle_div_is_set = false;
  private boolean position_report_cycle_div_is_modified = false;
  private boolean position_report_div_is_set = false;
  private boolean position_report_div_is_modified = false;
  private boolean certificate_deposit_flag_is_set = false;
  private boolean certificate_deposit_flag_is_modified = false;
  private boolean account_statement_flag_is_set = false;
  private boolean account_statement_flag_is_modified = false;
  private boolean trading_report_div_is_set = false;
  private boolean trading_report_div_is_modified = false;
  private boolean inv_trast_ope_report_is_set = false;
  private boolean inv_trast_ope_report_is_modified = false;
  private boolean tax_type_is_set = false;
  private boolean tax_type_is_modified = false;
  private boolean margin_tax_type_is_set = false;
  private boolean margin_tax_type_is_modified = false;
  private boolean equity_sp_acc_open_date_is_set = false;
  private boolean equity_sp_acc_open_date_is_modified = false;
  private boolean margin_sp_acc_open_date_is_set = false;
  private boolean margin_sp_acc_open_date_is_modified = false;
  private boolean tax_type_last_is_set = false;
  private boolean tax_type_last_is_modified = false;
  private boolean margin_tax_type_last_is_set = false;
  private boolean margin_tax_type_last_is_modified = false;
  private boolean tax_type_next_is_set = false;
  private boolean tax_type_next_is_modified = false;
  private boolean margin_tax_type_next_is_set = false;
  private boolean margin_tax_type_next_is_modified = false;
  private boolean fluct_date_is_set = false;
  private boolean fluct_date_is_modified = false;
  private boolean margin_fluct_date_is_set = false;
  private boolean margin_fluct_date_is_modified = false;
  private boolean local_tax_div_last_is_set = false;
  private boolean local_tax_div_last_is_modified = false;
  private boolean local_tax_div_is_set = false;
  private boolean local_tax_div_is_modified = false;
  private boolean local_tax_div_next_is_set = false;
  private boolean local_tax_div_next_is_modified = false;
  private boolean qualified_inst_investor_div_is_set = false;
  private boolean qualified_inst_investor_div_is_modified = false;
  private boolean quoto_type_is_set = false;
  private boolean quoto_type_is_modified = false;
  private boolean sp_acc_abolish_date_is_set = false;
  private boolean sp_acc_abolish_date_is_modified = false;
  private boolean sp_mng_acc_open_div_is_set = false;
  private boolean sp_mng_acc_open_div_is_modified = false;
  private boolean reserve_area_is_set = false;
  private boolean reserve_area_is_modified = false;
  private boolean web3_encrypted_password_is_set = false;
  private boolean web3_encrypted_password_is_modified = false;
  private boolean xtrade_encrypted_password_is_set = false;
  private boolean xtrade_encrypted_password_is_modified = false;
  private boolean broadcast_law_is_set = false;
  private boolean broadcast_law_is_modified = false;
  private boolean aviation_law_is_set = false;
  private boolean aviation_law_is_modified = false;
  private boolean ntt_law_is_set = false;
  private boolean ntt_law_is_modified = false;
  private boolean dividend_trans_designate_is_set = false;
  private boolean dividend_trans_designate_is_modified = false;
  private boolean standing_proxy_is_set = false;
  private boolean standing_proxy_is_modified = false;
  private boolean statutory_agent_is_set = false;
  private boolean statutory_agent_is_modified = false;
  private boolean affiliate_account_code_is_set = false;
  private boolean affiliate_account_code_is_modified = false;
  private boolean agency_notify_cmp_div_is_set = false;
  private boolean agency_notify_cmp_div_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "comp_code=" + comp_code
      + "," + "br_code=" + br_code
      + "," + "cust_code=" + cust_code
      + "," + "cust_code_cd=" + cust_code_cd
      + "," + "gen_acc_div=" +gen_acc_div
      + "," + "gen_br_del_div=" +gen_br_del_div
      + "," + "ruito_acc_open_div=" +ruito_acc_open_div
      + "," + "margin_acc_open_div=" +margin_acc_open_div
      + "," + "when_issued_acc_open_div=" +when_issued_acc_open_div
      + "," + "report_dispatch_stop_div=" +report_dispatch_stop_div
      + "," + "doc_dispatch_div=" +doc_dispatch_div
      + "," + "gp_br_del_div=" +gp_br_del_div
      + "," + "account_open_date=" +account_open_date
      + "," + "last_update_date=" +last_update_date
      + "," + "sonar_trader_code=" +sonar_trader_code
      + "," + "rep_dispatch_stp_bd=" +rep_dispatch_stp_bd
      + "," + "occupation=" +occupation
      + "," + "safe_cont_open_div=" +safe_cont_open_div
      + "," + "foreign_sec_acc_open_div=" +foreign_sec_acc_open_div
      + "," + "tokuyu_acc_open_div=" +tokuyu_acc_open_div
      + "," + "gold_acc_open_div=" +gold_acc_open_div
      + "," + "total_trade_open_div=" +total_trade_open_div
      + "," + "tie_up_loan_open_div=" +tie_up_loan_open_div
      + "," + "ifo_acc_open_div_tokyo=" +ifo_acc_open_div_tokyo
      + "," + "address_unknown=" +address_unknown
      + "," + "cust_div=" +cust_div
      + "," + "deposit_div=" +deposit_div
      + "," + "all_substitution_div=" +all_substitution_div
      + "," + "ins_loan_bill_mthd_div=" +ins_loan_bill_mthd_div
      + "," + "ins_loan_cer_mthd_div=" +ins_loan_cer_mthd_div
      + "," + "ins_loan_clause_mthd_div=" +ins_loan_clause_mthd_div
      + "," + "dom_tax_div=" +dom_tax_div
      + "," + "client_trader_code=" +client_trader_code
      + "," + "telephone=" +telephone
      + "," + "family_name_alt1=" +family_name_alt1
      + "," + "zip_code=" +zip_code
      + "," + "prefecture=" +prefecture
      + "," + "comma=" +comma
      + "," + "address_line1_kana=" +address_line1_kana
      + "," + "address_line2_kana=" +address_line2_kana
      + "," + "address_line3_kana=" +address_line3_kana
      + "," + "family_name=" +family_name
      + "," + "address_line1=" +address_line1
      + "," + "address_line2=" +address_line2
      + "," + "address_line3=" +address_line3
      + "," + "contact_address_telephone=" +contact_address_telephone
      + "," + "contact_address=" +contact_address
      + "," + "era_born=" +era_born
      + "," + "born_date=" +born_date
      + "," + "sex=" +sex
      + "," + "before_acc_trans_br_code=" +before_acc_trans_br_code
      + "," + "before_acc_trans_cust_code=" +before_acc_trans_cust_code
      + "," + "d_card_appli_date=" +d_card_appli_date
      + "," + "d_card_issue_date=" +d_card_issue_date
      + "," + "d_card_password=" +d_card_password
      + "," + "d_card_issue_reason=" +d_card_issue_reason
      + "," + "d_card_issue_number=" +d_card_issue_number
      + "," + "d_card_stop_date=" +d_card_stop_date
      + "," + "d_card_stop_reason=" +d_card_stop_reason
      + "," + "d_card_cancel_date=" +d_card_cancel_date
      + "," + "d_card_cancel_reason=" +d_card_cancel_reason
      + "," + "d_card_name=" +d_card_name
      + "," + "pass_err_code_type=" +pass_err_code_type
      + "," + "pass_err_code_number=" +pass_err_code_number
      + "," + "pass_err_code_chg_date=" +pass_err_code_chg_date
      + "," + "pass_err_code_chg_time=" +pass_err_code_chg_time
      + "," + "ans_cust_div=" +ans_cust_div
      + "," + "ans_stock_appli_cate=" +ans_stock_appli_cate
      + "," + "dummy1=" +dummy1
      + "," + "self_assessed_sep_tax=" +self_assessed_sep_tax
      + "," + "tokuyu_appli_div=" +tokuyu_appli_div
      + "," + "maruyu_appli_div=" +maruyu_appli_div
      + "," + "tokuyu_laundering_div=" +tokuyu_laundering_div
      + "," + "maruyu_laundering_div=" +maruyu_laundering_div
      + "," + "tax_div=" +tax_div
      + "," + "dummy2=" +dummy2
      + "," + "ht_settlement_way=" +ht_settlement_way
      + "," + "dummy3=" +dummy3
      + "," + "total_tax_identity=" +total_tax_identity
      + "," + "dummy4=" +dummy4
      + "," + "ifo_acc_open_div_osaka=" +ifo_acc_open_div_osaka
      + "," + "mort_trade_open_div=" +mort_trade_open_div
      + "," + "ifo_acc_open_div_nagoya=" +ifo_acc_open_div_nagoya
      + "," + "os_fnc_futures_open_div=" +os_fnc_futures_open_div
      + "," + "os_sec_futures_open_div=" +os_sec_futures_open_div
      + "," + "tokyo_fnc_futures_open_div=" +tokyo_fnc_futures_open_div
      + "," + "for_war_trade_open_div=" +for_war_trade_open_div
      + "," + "tentou_trade_open_div=" +tentou_trade_open_div
      + "," + "represent_div=" +represent_div
      + "," + "family_nurturer_code=" +family_nurturer_code
      + "," + "family_unit_adimin=" +family_unit_adimin
      + "," + "new_monthly_div=" +new_monthly_div
      + "," + "statement_skip1_div=" +statement_skip1_div
      + "," + "statement_skip2_div=" +statement_skip2_div
      + "," + "family_unit_del_div=" +family_unit_del_div
      + "," + "dummy5=" +dummy5
      + "," + "br_dispatch_div=" +br_dispatch_div
      + "," + "maruyu_grade_div=" +maruyu_grade_div
      + "," + "dummy6=" +dummy6
      + "," + "dummy7=" +dummy7
      + "," + "trans_tax_div_acc_date=" +trans_tax_div_acc_date
      + "," + "trans_tax_div=" +trans_tax_div
      + "," + "resident=" +resident
      + "," + "bond_d_and_c_open_div=" +bond_d_and_c_open_div
      + "," + "os_cd_cp_open_div=" +os_cd_cp_open_div
      + "," + "new_tb_open_div=" +new_tb_open_div
      + "," + "dom_cp_open_div=" +dom_cp_open_div
      + "," + "bond_tentou_op_open_div=" +bond_tentou_op_open_div
      + "," + "t_bond_futures_open_div=" +t_bond_futures_open_div
      + "," + "dom_war_trade_open_div=" +dom_war_trade_open_div
      + "," + "dummy8=" +dummy8
      + "," + "dom_for_bond_open_div=" +dom_for_bond_open_div
      + "," + "tentou_sp_rule_open_div=" +tentou_sp_rule_open_div
      + "," + "dummy9=" +dummy9
      + "," + "dummy10=" +dummy10
      + "," + "dummy11=" +dummy11
      + "," + "dummy12=" +dummy12
      + "," + "dummy13=" +dummy13
      + "," + "mrf_acc_open_div=" +mrf_acc_open_div
      + "," + "safe_cont_div=" +safe_cont_div
      + "," + "foreign_cont_div=" +foreign_cont_div
      + "," + "dummy14=" +dummy14
      + "," + "gold_cont_div=" +gold_cont_div
      + "," + "margin_cont_div=" +margin_cont_div
      + "," + "when_issued_cont_div=" +when_issued_cont_div
      + "," + "futures_op_cont_div_tokyo=" +futures_op_cont_div_tokyo
      + "," + "dummy15=" +dummy15
      + "," + "futures_op_cont_div_osaka=" +futures_op_cont_div_osaka
      + "," + "tokyo_mothers_cont_div=" +tokyo_mothers_cont_div
      + "," + "futures_op_cont_div_nagoya=" +futures_op_cont_div_nagoya
      + "," + "nq_j_gl_cont_div=" +nq_j_gl_cont_div
      + "," + "osaka_nw_mkt_cont_div=" +osaka_nw_mkt_cont_div
      + "," + "nagoya_grw_cpy_mkt_cont_div=" +nagoya_grw_cpy_mkt_cont_div
      + "," + "for_war_trade_cont_div=" +for_war_trade_cont_div
      + "," + "tentou_trade_cont_div=" +tentou_trade_cont_div
      + "," + "bond_d_and_c_cont_div=" +bond_d_and_c_cont_div
      + "," + "sapporo_amb_cont_div=" +sapporo_amb_cont_div
      + "," + "new_tb_cont_div=" +new_tb_cont_div
      + "," + "gen_credit_acc_cont_div=" +gen_credit_acc_cont_div
      + "," + "bond_tentou_op_cont_div=" +bond_tentou_op_cont_div
      + "," + "fukuoka_qb_cont_div=" +fukuoka_qb_cont_div
      + "," + "dom_war_trade_cont_div=" +dom_war_trade_cont_div
      + "," + "tentou_sec_basis_cont_div=" +tentou_sec_basis_cont_div
      + "," + "dom_for_bond_cont_div=" +dom_for_bond_cont_div
      + "," + "tentou_sp_rule_cont_div=" +tentou_sp_rule_cont_div
      + "," + "dummy16=" +dummy16
      + "," + "dummy17=" +dummy17
      + "," + "dummy18=" +dummy18
      + "," + "dummy19=" +dummy19
      + "," + "dummy20=" +dummy20
      + "," + "mrf_contract_div=" +mrf_contract_div
      + "," + "new_account_div=" +new_account_div
      + "," + "via_trust_bank_div=" +via_trust_bank_div
      + "," + "class_div=" +class_div
      + "," + "address_code=" +address_code
      + "," + "person_identify=" +person_identify
      + "," + "cancel_div=" +cancel_div
      + "," + "add_chg_div=" +add_chg_div
      + "," + "reserve=" +reserve
      + "," + "org_deposit_div=" +org_deposit_div
      + "," + "eq_hold_rep_div=" +eq_hold_rep_div
      + "," + "chkup_rep_div=" +chkup_rep_div
      + "," + "fax=" +fax
      + "," + "hq_input_div=" +hq_input_div
      + "," + "yellow_customer=" +yellow_customer
      + "," + "dummy21=" +dummy21
      + "," + "dummy22=" +dummy22
      + "," + "bond_butt_div=" +bond_butt_div
      + "," + "hofuri_entry=" +hofuri_entry
      + "," + "agreed_non_pkg_div=" +agreed_non_pkg_div
      + "," + "position_report_cycle_div=" +position_report_cycle_div
      + "," + "position_report_div=" +position_report_div
      + "," + "certificate_deposit_flag=" +certificate_deposit_flag
      + "," + "account_statement_flag=" +account_statement_flag
      + "," + "trading_report_div=" +trading_report_div
      + "," + "inv_trast_ope_report=" +inv_trast_ope_report
      + "," + "tax_type=" +tax_type
      + "," + "margin_tax_type=" +margin_tax_type
      + "," + "equity_sp_acc_open_date=" +equity_sp_acc_open_date
      + "," + "margin_sp_acc_open_date=" +margin_sp_acc_open_date
      + "," + "tax_type_last=" +tax_type_last
      + "," + "margin_tax_type_last=" +margin_tax_type_last
      + "," + "tax_type_next=" +tax_type_next
      + "," + "margin_tax_type_next=" +margin_tax_type_next
      + "," + "fluct_date=" +fluct_date
      + "," + "margin_fluct_date=" +margin_fluct_date
      + "," + "local_tax_div_last=" +local_tax_div_last
      + "," + "local_tax_div=" +local_tax_div
      + "," + "local_tax_div_next=" +local_tax_div_next
      + "," + "qualified_inst_investor_div=" +qualified_inst_investor_div
      + "," + "quoto_type=" +quoto_type
      + "," + "sp_acc_abolish_date=" +sp_acc_abolish_date
      + "," + "sp_mng_acc_open_div=" +sp_mng_acc_open_div
      + "," + "reserve_area=" +reserve_area
      + "," + "web3_encrypted_password=" +web3_encrypted_password
      + "," + "xtrade_encrypted_password=" +xtrade_encrypted_password
      + "," + "broadcast_law=" +broadcast_law
      + "," + "aviation_law=" +aviation_law
      + "," + "ntt_law=" +ntt_law
      + "," + "dividend_trans_designate=" +dividend_trans_designate
      + "," + "standing_proxy=" +standing_proxy
      + "," + "statutory_agent=" +statutory_agent
      + "," + "affiliate_account_code=" +affiliate_account_code
      + "," + "agency_notify_cmp_div=" +agency_notify_cmp_div
      + "}";
  }


  /** 
   * 値が未設定のMainAccountAllParamsオブジェクトを作成します。 
   */
  public MainAccountAllParams() {
    comp_code_is_modified = true;
    br_code_is_modified = true;
    cust_code_is_modified = true;
    cust_code_cd_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のMainAccountAllRowオブジェクトの値を利用してMainAccountAllParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するMainAccountAllRowオブジェクト 
   */
  public MainAccountAllParams( MainAccountAllRow p_row )
  {
    comp_code = p_row.getCompCode();
    comp_code_is_set = p_row.getCompCodeIsSet();
    comp_code_is_modified = p_row.getCompCodeIsModified();
    br_code = p_row.getBrCode();
    br_code_is_set = p_row.getBrCodeIsSet();
    br_code_is_modified = p_row.getBrCodeIsModified();
    cust_code = p_row.getCustCode();
    cust_code_is_set = p_row.getCustCodeIsSet();
    cust_code_is_modified = p_row.getCustCodeIsModified();
    cust_code_cd = p_row.getCustCodeCd();
    cust_code_cd_is_set = p_row.getCustCodeCdIsSet();
    cust_code_cd_is_modified = p_row.getCustCodeCdIsModified();
    gen_acc_div = p_row.getGenAccDiv();
    gen_acc_div_is_set = p_row.getGenAccDivIsSet();
    gen_acc_div_is_modified = p_row.getGenAccDivIsModified();
    gen_br_del_div = p_row.getGenBrDelDiv();
    gen_br_del_div_is_set = p_row.getGenBrDelDivIsSet();
    gen_br_del_div_is_modified = p_row.getGenBrDelDivIsModified();
    ruito_acc_open_div = p_row.getRuitoAccOpenDiv();
    ruito_acc_open_div_is_set = p_row.getRuitoAccOpenDivIsSet();
    ruito_acc_open_div_is_modified = p_row.getRuitoAccOpenDivIsModified();
    margin_acc_open_div = p_row.getMarginAccOpenDiv();
    margin_acc_open_div_is_set = p_row.getMarginAccOpenDivIsSet();
    margin_acc_open_div_is_modified = p_row.getMarginAccOpenDivIsModified();
    when_issued_acc_open_div = p_row.getWhenIssuedAccOpenDiv();
    when_issued_acc_open_div_is_set = p_row.getWhenIssuedAccOpenDivIsSet();
    when_issued_acc_open_div_is_modified = p_row.getWhenIssuedAccOpenDivIsModified();
    report_dispatch_stop_div = p_row.getReportDispatchStopDiv();
    report_dispatch_stop_div_is_set = p_row.getReportDispatchStopDivIsSet();
    report_dispatch_stop_div_is_modified = p_row.getReportDispatchStopDivIsModified();
    doc_dispatch_div = p_row.getDocDispatchDiv();
    doc_dispatch_div_is_set = p_row.getDocDispatchDivIsSet();
    doc_dispatch_div_is_modified = p_row.getDocDispatchDivIsModified();
    gp_br_del_div = p_row.getGpBrDelDiv();
    gp_br_del_div_is_set = p_row.getGpBrDelDivIsSet();
    gp_br_del_div_is_modified = p_row.getGpBrDelDivIsModified();
    account_open_date = p_row.getAccountOpenDate();
    account_open_date_is_set = p_row.getAccountOpenDateIsSet();
    account_open_date_is_modified = p_row.getAccountOpenDateIsModified();
    last_update_date = p_row.getLastUpdateDate();
    last_update_date_is_set = p_row.getLastUpdateDateIsSet();
    last_update_date_is_modified = p_row.getLastUpdateDateIsModified();
    sonar_trader_code = p_row.getSonarTraderCode();
    sonar_trader_code_is_set = p_row.getSonarTraderCodeIsSet();
    sonar_trader_code_is_modified = p_row.getSonarTraderCodeIsModified();
    rep_dispatch_stp_bd = p_row.getRepDispatchStpBd();
    rep_dispatch_stp_bd_is_set = p_row.getRepDispatchStpBdIsSet();
    rep_dispatch_stp_bd_is_modified = p_row.getRepDispatchStpBdIsModified();
    occupation = p_row.getOccupation();
    occupation_is_set = p_row.getOccupationIsSet();
    occupation_is_modified = p_row.getOccupationIsModified();
    safe_cont_open_div = p_row.getSafeContOpenDiv();
    safe_cont_open_div_is_set = p_row.getSafeContOpenDivIsSet();
    safe_cont_open_div_is_modified = p_row.getSafeContOpenDivIsModified();
    foreign_sec_acc_open_div = p_row.getForeignSecAccOpenDiv();
    foreign_sec_acc_open_div_is_set = p_row.getForeignSecAccOpenDivIsSet();
    foreign_sec_acc_open_div_is_modified = p_row.getForeignSecAccOpenDivIsModified();
    tokuyu_acc_open_div = p_row.getTokuyuAccOpenDiv();
    tokuyu_acc_open_div_is_set = p_row.getTokuyuAccOpenDivIsSet();
    tokuyu_acc_open_div_is_modified = p_row.getTokuyuAccOpenDivIsModified();
    gold_acc_open_div = p_row.getGoldAccOpenDiv();
    gold_acc_open_div_is_set = p_row.getGoldAccOpenDivIsSet();
    gold_acc_open_div_is_modified = p_row.getGoldAccOpenDivIsModified();
    total_trade_open_div = p_row.getTotalTradeOpenDiv();
    total_trade_open_div_is_set = p_row.getTotalTradeOpenDivIsSet();
    total_trade_open_div_is_modified = p_row.getTotalTradeOpenDivIsModified();
    tie_up_loan_open_div = p_row.getTieUpLoanOpenDiv();
    tie_up_loan_open_div_is_set = p_row.getTieUpLoanOpenDivIsSet();
    tie_up_loan_open_div_is_modified = p_row.getTieUpLoanOpenDivIsModified();
    ifo_acc_open_div_tokyo = p_row.getIfoAccOpenDivTokyo();
    ifo_acc_open_div_tokyo_is_set = p_row.getIfoAccOpenDivTokyoIsSet();
    ifo_acc_open_div_tokyo_is_modified = p_row.getIfoAccOpenDivTokyoIsModified();
    address_unknown = p_row.getAddressUnknown();
    address_unknown_is_set = p_row.getAddressUnknownIsSet();
    address_unknown_is_modified = p_row.getAddressUnknownIsModified();
    cust_div = p_row.getCustDiv();
    cust_div_is_set = p_row.getCustDivIsSet();
    cust_div_is_modified = p_row.getCustDivIsModified();
    deposit_div = p_row.getDepositDiv();
    deposit_div_is_set = p_row.getDepositDivIsSet();
    deposit_div_is_modified = p_row.getDepositDivIsModified();
    all_substitution_div = p_row.getAllSubstitutionDiv();
    all_substitution_div_is_set = p_row.getAllSubstitutionDivIsSet();
    all_substitution_div_is_modified = p_row.getAllSubstitutionDivIsModified();
    ins_loan_bill_mthd_div = p_row.getInsLoanBillMthdDiv();
    ins_loan_bill_mthd_div_is_set = p_row.getInsLoanBillMthdDivIsSet();
    ins_loan_bill_mthd_div_is_modified = p_row.getInsLoanBillMthdDivIsModified();
    ins_loan_cer_mthd_div = p_row.getInsLoanCerMthdDiv();
    ins_loan_cer_mthd_div_is_set = p_row.getInsLoanCerMthdDivIsSet();
    ins_loan_cer_mthd_div_is_modified = p_row.getInsLoanCerMthdDivIsModified();
    ins_loan_clause_mthd_div = p_row.getInsLoanClauseMthdDiv();
    ins_loan_clause_mthd_div_is_set = p_row.getInsLoanClauseMthdDivIsSet();
    ins_loan_clause_mthd_div_is_modified = p_row.getInsLoanClauseMthdDivIsModified();
    dom_tax_div = p_row.getDomTaxDiv();
    dom_tax_div_is_set = p_row.getDomTaxDivIsSet();
    dom_tax_div_is_modified = p_row.getDomTaxDivIsModified();
    client_trader_code = p_row.getClientTraderCode();
    client_trader_code_is_set = p_row.getClientTraderCodeIsSet();
    client_trader_code_is_modified = p_row.getClientTraderCodeIsModified();
    telephone = p_row.getTelephone();
    telephone_is_set = p_row.getTelephoneIsSet();
    telephone_is_modified = p_row.getTelephoneIsModified();
    family_name_alt1 = p_row.getFamilyNameAlt1();
    family_name_alt1_is_set = p_row.getFamilyNameAlt1IsSet();
    family_name_alt1_is_modified = p_row.getFamilyNameAlt1IsModified();
    zip_code = p_row.getZipCode();
    zip_code_is_set = p_row.getZipCodeIsSet();
    zip_code_is_modified = p_row.getZipCodeIsModified();
    prefecture = p_row.getPrefecture();
    prefecture_is_set = p_row.getPrefectureIsSet();
    prefecture_is_modified = p_row.getPrefectureIsModified();
    comma = p_row.getComma();
    comma_is_set = p_row.getCommaIsSet();
    comma_is_modified = p_row.getCommaIsModified();
    address_line1_kana = p_row.getAddressLine1Kana();
    address_line1_kana_is_set = p_row.getAddressLine1KanaIsSet();
    address_line1_kana_is_modified = p_row.getAddressLine1KanaIsModified();
    address_line2_kana = p_row.getAddressLine2Kana();
    address_line2_kana_is_set = p_row.getAddressLine2KanaIsSet();
    address_line2_kana_is_modified = p_row.getAddressLine2KanaIsModified();
    address_line3_kana = p_row.getAddressLine3Kana();
    address_line3_kana_is_set = p_row.getAddressLine3KanaIsSet();
    address_line3_kana_is_modified = p_row.getAddressLine3KanaIsModified();
    family_name = p_row.getFamilyName();
    family_name_is_set = p_row.getFamilyNameIsSet();
    family_name_is_modified = p_row.getFamilyNameIsModified();
    address_line1 = p_row.getAddressLine1();
    address_line1_is_set = p_row.getAddressLine1IsSet();
    address_line1_is_modified = p_row.getAddressLine1IsModified();
    address_line2 = p_row.getAddressLine2();
    address_line2_is_set = p_row.getAddressLine2IsSet();
    address_line2_is_modified = p_row.getAddressLine2IsModified();
    address_line3 = p_row.getAddressLine3();
    address_line3_is_set = p_row.getAddressLine3IsSet();
    address_line3_is_modified = p_row.getAddressLine3IsModified();
    contact_address_telephone = p_row.getContactAddressTelephone();
    contact_address_telephone_is_set = p_row.getContactAddressTelephoneIsSet();
    contact_address_telephone_is_modified = p_row.getContactAddressTelephoneIsModified();
    contact_address = p_row.getContactAddress();
    contact_address_is_set = p_row.getContactAddressIsSet();
    contact_address_is_modified = p_row.getContactAddressIsModified();
    era_born = p_row.getEraBorn();
    era_born_is_set = p_row.getEraBornIsSet();
    era_born_is_modified = p_row.getEraBornIsModified();
    born_date = p_row.getBornDate();
    born_date_is_set = p_row.getBornDateIsSet();
    born_date_is_modified = p_row.getBornDateIsModified();
    sex = p_row.getSex();
    sex_is_set = p_row.getSexIsSet();
    sex_is_modified = p_row.getSexIsModified();
    before_acc_trans_br_code = p_row.getBeforeAccTransBrCode();
    before_acc_trans_br_code_is_set = p_row.getBeforeAccTransBrCodeIsSet();
    before_acc_trans_br_code_is_modified = p_row.getBeforeAccTransBrCodeIsModified();
    before_acc_trans_cust_code = p_row.getBeforeAccTransCustCode();
    before_acc_trans_cust_code_is_set = p_row.getBeforeAccTransCustCodeIsSet();
    before_acc_trans_cust_code_is_modified = p_row.getBeforeAccTransCustCodeIsModified();
    d_card_appli_date = p_row.getDCardAppliDate();
    d_card_appli_date_is_set = p_row.getDCardAppliDateIsSet();
    d_card_appli_date_is_modified = p_row.getDCardAppliDateIsModified();
    d_card_issue_date = p_row.getDCardIssueDate();
    d_card_issue_date_is_set = p_row.getDCardIssueDateIsSet();
    d_card_issue_date_is_modified = p_row.getDCardIssueDateIsModified();
    d_card_password = p_row.getDCardPassword();
    d_card_password_is_set = p_row.getDCardPasswordIsSet();
    d_card_password_is_modified = p_row.getDCardPasswordIsModified();
    d_card_issue_reason = p_row.getDCardIssueReason();
    d_card_issue_reason_is_set = p_row.getDCardIssueReasonIsSet();
    d_card_issue_reason_is_modified = p_row.getDCardIssueReasonIsModified();
    d_card_issue_number = p_row.getDCardIssueNumber();
    d_card_issue_number_is_set = p_row.getDCardIssueNumberIsSet();
    d_card_issue_number_is_modified = p_row.getDCardIssueNumberIsModified();
    d_card_stop_date = p_row.getDCardStopDate();
    d_card_stop_date_is_set = p_row.getDCardStopDateIsSet();
    d_card_stop_date_is_modified = p_row.getDCardStopDateIsModified();
    d_card_stop_reason = p_row.getDCardStopReason();
    d_card_stop_reason_is_set = p_row.getDCardStopReasonIsSet();
    d_card_stop_reason_is_modified = p_row.getDCardStopReasonIsModified();
    d_card_cancel_date = p_row.getDCardCancelDate();
    d_card_cancel_date_is_set = p_row.getDCardCancelDateIsSet();
    d_card_cancel_date_is_modified = p_row.getDCardCancelDateIsModified();
    d_card_cancel_reason = p_row.getDCardCancelReason();
    d_card_cancel_reason_is_set = p_row.getDCardCancelReasonIsSet();
    d_card_cancel_reason_is_modified = p_row.getDCardCancelReasonIsModified();
    d_card_name = p_row.getDCardName();
    d_card_name_is_set = p_row.getDCardNameIsSet();
    d_card_name_is_modified = p_row.getDCardNameIsModified();
    pass_err_code_type = p_row.getPassErrCodeType();
    pass_err_code_type_is_set = p_row.getPassErrCodeTypeIsSet();
    pass_err_code_type_is_modified = p_row.getPassErrCodeTypeIsModified();
    pass_err_code_number = p_row.getPassErrCodeNumber();
    pass_err_code_number_is_set = p_row.getPassErrCodeNumberIsSet();
    pass_err_code_number_is_modified = p_row.getPassErrCodeNumberIsModified();
    pass_err_code_chg_date = p_row.getPassErrCodeChgDate();
    pass_err_code_chg_date_is_set = p_row.getPassErrCodeChgDateIsSet();
    pass_err_code_chg_date_is_modified = p_row.getPassErrCodeChgDateIsModified();
    pass_err_code_chg_time = p_row.getPassErrCodeChgTime();
    pass_err_code_chg_time_is_set = p_row.getPassErrCodeChgTimeIsSet();
    pass_err_code_chg_time_is_modified = p_row.getPassErrCodeChgTimeIsModified();
    ans_cust_div = p_row.getAnsCustDiv();
    ans_cust_div_is_set = p_row.getAnsCustDivIsSet();
    ans_cust_div_is_modified = p_row.getAnsCustDivIsModified();
    ans_stock_appli_cate = p_row.getAnsStockAppliCate();
    ans_stock_appli_cate_is_set = p_row.getAnsStockAppliCateIsSet();
    ans_stock_appli_cate_is_modified = p_row.getAnsStockAppliCateIsModified();
    dummy1 = p_row.getDummy1();
    dummy1_is_set = p_row.getDummy1IsSet();
    dummy1_is_modified = p_row.getDummy1IsModified();
    self_assessed_sep_tax = p_row.getSelfAssessedSepTax();
    self_assessed_sep_tax_is_set = p_row.getSelfAssessedSepTaxIsSet();
    self_assessed_sep_tax_is_modified = p_row.getSelfAssessedSepTaxIsModified();
    tokuyu_appli_div = p_row.getTokuyuAppliDiv();
    tokuyu_appli_div_is_set = p_row.getTokuyuAppliDivIsSet();
    tokuyu_appli_div_is_modified = p_row.getTokuyuAppliDivIsModified();
    maruyu_appli_div = p_row.getMaruyuAppliDiv();
    maruyu_appli_div_is_set = p_row.getMaruyuAppliDivIsSet();
    maruyu_appli_div_is_modified = p_row.getMaruyuAppliDivIsModified();
    tokuyu_laundering_div = p_row.getTokuyuLaunderingDiv();
    tokuyu_laundering_div_is_set = p_row.getTokuyuLaunderingDivIsSet();
    tokuyu_laundering_div_is_modified = p_row.getTokuyuLaunderingDivIsModified();
    maruyu_laundering_div = p_row.getMaruyuLaunderingDiv();
    maruyu_laundering_div_is_set = p_row.getMaruyuLaunderingDivIsSet();
    maruyu_laundering_div_is_modified = p_row.getMaruyuLaunderingDivIsModified();
    tax_div = p_row.getTaxDiv();
    tax_div_is_set = p_row.getTaxDivIsSet();
    tax_div_is_modified = p_row.getTaxDivIsModified();
    dummy2 = p_row.getDummy2();
    dummy2_is_set = p_row.getDummy2IsSet();
    dummy2_is_modified = p_row.getDummy2IsModified();
    ht_settlement_way = p_row.getHtSettlementWay();
    ht_settlement_way_is_set = p_row.getHtSettlementWayIsSet();
    ht_settlement_way_is_modified = p_row.getHtSettlementWayIsModified();
    dummy3 = p_row.getDummy3();
    dummy3_is_set = p_row.getDummy3IsSet();
    dummy3_is_modified = p_row.getDummy3IsModified();
    total_tax_identity = p_row.getTotalTaxIdentity();
    total_tax_identity_is_set = p_row.getTotalTaxIdentityIsSet();
    total_tax_identity_is_modified = p_row.getTotalTaxIdentityIsModified();
    dummy4 = p_row.getDummy4();
    dummy4_is_set = p_row.getDummy4IsSet();
    dummy4_is_modified = p_row.getDummy4IsModified();
    ifo_acc_open_div_osaka = p_row.getIfoAccOpenDivOsaka();
    ifo_acc_open_div_osaka_is_set = p_row.getIfoAccOpenDivOsakaIsSet();
    ifo_acc_open_div_osaka_is_modified = p_row.getIfoAccOpenDivOsakaIsModified();
    mort_trade_open_div = p_row.getMortTradeOpenDiv();
    mort_trade_open_div_is_set = p_row.getMortTradeOpenDivIsSet();
    mort_trade_open_div_is_modified = p_row.getMortTradeOpenDivIsModified();
    ifo_acc_open_div_nagoya = p_row.getIfoAccOpenDivNagoya();
    ifo_acc_open_div_nagoya_is_set = p_row.getIfoAccOpenDivNagoyaIsSet();
    ifo_acc_open_div_nagoya_is_modified = p_row.getIfoAccOpenDivNagoyaIsModified();
    os_fnc_futures_open_div = p_row.getOsFncFuturesOpenDiv();
    os_fnc_futures_open_div_is_set = p_row.getOsFncFuturesOpenDivIsSet();
    os_fnc_futures_open_div_is_modified = p_row.getOsFncFuturesOpenDivIsModified();
    os_sec_futures_open_div = p_row.getOsSecFuturesOpenDiv();
    os_sec_futures_open_div_is_set = p_row.getOsSecFuturesOpenDivIsSet();
    os_sec_futures_open_div_is_modified = p_row.getOsSecFuturesOpenDivIsModified();
    tokyo_fnc_futures_open_div = p_row.getTokyoFncFuturesOpenDiv();
    tokyo_fnc_futures_open_div_is_set = p_row.getTokyoFncFuturesOpenDivIsSet();
    tokyo_fnc_futures_open_div_is_modified = p_row.getTokyoFncFuturesOpenDivIsModified();
    for_war_trade_open_div = p_row.getForWarTradeOpenDiv();
    for_war_trade_open_div_is_set = p_row.getForWarTradeOpenDivIsSet();
    for_war_trade_open_div_is_modified = p_row.getForWarTradeOpenDivIsModified();
    tentou_trade_open_div = p_row.getTentouTradeOpenDiv();
    tentou_trade_open_div_is_set = p_row.getTentouTradeOpenDivIsSet();
    tentou_trade_open_div_is_modified = p_row.getTentouTradeOpenDivIsModified();
    represent_div = p_row.getRepresentDiv();
    represent_div_is_set = p_row.getRepresentDivIsSet();
    represent_div_is_modified = p_row.getRepresentDivIsModified();
    family_nurturer_code = p_row.getFamilyNurturerCode();
    family_nurturer_code_is_set = p_row.getFamilyNurturerCodeIsSet();
    family_nurturer_code_is_modified = p_row.getFamilyNurturerCodeIsModified();
    family_unit_adimin = p_row.getFamilyUnitAdimin();
    family_unit_adimin_is_set = p_row.getFamilyUnitAdiminIsSet();
    family_unit_adimin_is_modified = p_row.getFamilyUnitAdiminIsModified();
    new_monthly_div = p_row.getNewMonthlyDiv();
    new_monthly_div_is_set = p_row.getNewMonthlyDivIsSet();
    new_monthly_div_is_modified = p_row.getNewMonthlyDivIsModified();
    statement_skip1_div = p_row.getStatementSkip1Div();
    statement_skip1_div_is_set = p_row.getStatementSkip1DivIsSet();
    statement_skip1_div_is_modified = p_row.getStatementSkip1DivIsModified();
    statement_skip2_div = p_row.getStatementSkip2Div();
    statement_skip2_div_is_set = p_row.getStatementSkip2DivIsSet();
    statement_skip2_div_is_modified = p_row.getStatementSkip2DivIsModified();
    family_unit_del_div = p_row.getFamilyUnitDelDiv();
    family_unit_del_div_is_set = p_row.getFamilyUnitDelDivIsSet();
    family_unit_del_div_is_modified = p_row.getFamilyUnitDelDivIsModified();
    dummy5 = p_row.getDummy5();
    dummy5_is_set = p_row.getDummy5IsSet();
    dummy5_is_modified = p_row.getDummy5IsModified();
    br_dispatch_div = p_row.getBrDispatchDiv();
    br_dispatch_div_is_set = p_row.getBrDispatchDivIsSet();
    br_dispatch_div_is_modified = p_row.getBrDispatchDivIsModified();
    maruyu_grade_div = p_row.getMaruyuGradeDiv();
    maruyu_grade_div_is_set = p_row.getMaruyuGradeDivIsSet();
    maruyu_grade_div_is_modified = p_row.getMaruyuGradeDivIsModified();
    dummy6 = p_row.getDummy6();
    dummy6_is_set = p_row.getDummy6IsSet();
    dummy6_is_modified = p_row.getDummy6IsModified();
    dummy7 = p_row.getDummy7();
    dummy7_is_set = p_row.getDummy7IsSet();
    dummy7_is_modified = p_row.getDummy7IsModified();
    trans_tax_div_acc_date = p_row.getTransTaxDivAccDate();
    trans_tax_div_acc_date_is_set = p_row.getTransTaxDivAccDateIsSet();
    trans_tax_div_acc_date_is_modified = p_row.getTransTaxDivAccDateIsModified();
    trans_tax_div = p_row.getTransTaxDiv();
    trans_tax_div_is_set = p_row.getTransTaxDivIsSet();
    trans_tax_div_is_modified = p_row.getTransTaxDivIsModified();
    resident = p_row.getResident();
    resident_is_set = p_row.getResidentIsSet();
    resident_is_modified = p_row.getResidentIsModified();
    bond_d_and_c_open_div = p_row.getBondDAndCOpenDiv();
    bond_d_and_c_open_div_is_set = p_row.getBondDAndCOpenDivIsSet();
    bond_d_and_c_open_div_is_modified = p_row.getBondDAndCOpenDivIsModified();
    os_cd_cp_open_div = p_row.getOsCdCpOpenDiv();
    os_cd_cp_open_div_is_set = p_row.getOsCdCpOpenDivIsSet();
    os_cd_cp_open_div_is_modified = p_row.getOsCdCpOpenDivIsModified();
    new_tb_open_div = p_row.getNewTbOpenDiv();
    new_tb_open_div_is_set = p_row.getNewTbOpenDivIsSet();
    new_tb_open_div_is_modified = p_row.getNewTbOpenDivIsModified();
    dom_cp_open_div = p_row.getDomCpOpenDiv();
    dom_cp_open_div_is_set = p_row.getDomCpOpenDivIsSet();
    dom_cp_open_div_is_modified = p_row.getDomCpOpenDivIsModified();
    bond_tentou_op_open_div = p_row.getBondTentouOpOpenDiv();
    bond_tentou_op_open_div_is_set = p_row.getBondTentouOpOpenDivIsSet();
    bond_tentou_op_open_div_is_modified = p_row.getBondTentouOpOpenDivIsModified();
    t_bond_futures_open_div = p_row.getTBondFuturesOpenDiv();
    t_bond_futures_open_div_is_set = p_row.getTBondFuturesOpenDivIsSet();
    t_bond_futures_open_div_is_modified = p_row.getTBondFuturesOpenDivIsModified();
    dom_war_trade_open_div = p_row.getDomWarTradeOpenDiv();
    dom_war_trade_open_div_is_set = p_row.getDomWarTradeOpenDivIsSet();
    dom_war_trade_open_div_is_modified = p_row.getDomWarTradeOpenDivIsModified();
    dummy8 = p_row.getDummy8();
    dummy8_is_set = p_row.getDummy8IsSet();
    dummy8_is_modified = p_row.getDummy8IsModified();
    dom_for_bond_open_div = p_row.getDomForBondOpenDiv();
    dom_for_bond_open_div_is_set = p_row.getDomForBondOpenDivIsSet();
    dom_for_bond_open_div_is_modified = p_row.getDomForBondOpenDivIsModified();
    tentou_sp_rule_open_div = p_row.getTentouSpRuleOpenDiv();
    tentou_sp_rule_open_div_is_set = p_row.getTentouSpRuleOpenDivIsSet();
    tentou_sp_rule_open_div_is_modified = p_row.getTentouSpRuleOpenDivIsModified();
    dummy9 = p_row.getDummy9();
    dummy9_is_set = p_row.getDummy9IsSet();
    dummy9_is_modified = p_row.getDummy9IsModified();
    dummy10 = p_row.getDummy10();
    dummy10_is_set = p_row.getDummy10IsSet();
    dummy10_is_modified = p_row.getDummy10IsModified();
    dummy11 = p_row.getDummy11();
    dummy11_is_set = p_row.getDummy11IsSet();
    dummy11_is_modified = p_row.getDummy11IsModified();
    dummy12 = p_row.getDummy12();
    dummy12_is_set = p_row.getDummy12IsSet();
    dummy12_is_modified = p_row.getDummy12IsModified();
    dummy13 = p_row.getDummy13();
    dummy13_is_set = p_row.getDummy13IsSet();
    dummy13_is_modified = p_row.getDummy13IsModified();
    mrf_acc_open_div = p_row.getMrfAccOpenDiv();
    mrf_acc_open_div_is_set = p_row.getMrfAccOpenDivIsSet();
    mrf_acc_open_div_is_modified = p_row.getMrfAccOpenDivIsModified();
    safe_cont_div = p_row.getSafeContDiv();
    safe_cont_div_is_set = p_row.getSafeContDivIsSet();
    safe_cont_div_is_modified = p_row.getSafeContDivIsModified();
    foreign_cont_div = p_row.getForeignContDiv();
    foreign_cont_div_is_set = p_row.getForeignContDivIsSet();
    foreign_cont_div_is_modified = p_row.getForeignContDivIsModified();
    dummy14 = p_row.getDummy14();
    dummy14_is_set = p_row.getDummy14IsSet();
    dummy14_is_modified = p_row.getDummy14IsModified();
    gold_cont_div = p_row.getGoldContDiv();
    gold_cont_div_is_set = p_row.getGoldContDivIsSet();
    gold_cont_div_is_modified = p_row.getGoldContDivIsModified();
    margin_cont_div = p_row.getMarginContDiv();
    margin_cont_div_is_set = p_row.getMarginContDivIsSet();
    margin_cont_div_is_modified = p_row.getMarginContDivIsModified();
    when_issued_cont_div = p_row.getWhenIssuedContDiv();
    when_issued_cont_div_is_set = p_row.getWhenIssuedContDivIsSet();
    when_issued_cont_div_is_modified = p_row.getWhenIssuedContDivIsModified();
    futures_op_cont_div_tokyo = p_row.getFuturesOpContDivTokyo();
    futures_op_cont_div_tokyo_is_set = p_row.getFuturesOpContDivTokyoIsSet();
    futures_op_cont_div_tokyo_is_modified = p_row.getFuturesOpContDivTokyoIsModified();
    dummy15 = p_row.getDummy15();
    dummy15_is_set = p_row.getDummy15IsSet();
    dummy15_is_modified = p_row.getDummy15IsModified();
    futures_op_cont_div_osaka = p_row.getFuturesOpContDivOsaka();
    futures_op_cont_div_osaka_is_set = p_row.getFuturesOpContDivOsakaIsSet();
    futures_op_cont_div_osaka_is_modified = p_row.getFuturesOpContDivOsakaIsModified();
    tokyo_mothers_cont_div = p_row.getTokyoMothersContDiv();
    tokyo_mothers_cont_div_is_set = p_row.getTokyoMothersContDivIsSet();
    tokyo_mothers_cont_div_is_modified = p_row.getTokyoMothersContDivIsModified();
    futures_op_cont_div_nagoya = p_row.getFuturesOpContDivNagoya();
    futures_op_cont_div_nagoya_is_set = p_row.getFuturesOpContDivNagoyaIsSet();
    futures_op_cont_div_nagoya_is_modified = p_row.getFuturesOpContDivNagoyaIsModified();
    nq_j_gl_cont_div = p_row.getNqJGlContDiv();
    nq_j_gl_cont_div_is_set = p_row.getNqJGlContDivIsSet();
    nq_j_gl_cont_div_is_modified = p_row.getNqJGlContDivIsModified();
    osaka_nw_mkt_cont_div = p_row.getOsakaNwMktContDiv();
    osaka_nw_mkt_cont_div_is_set = p_row.getOsakaNwMktContDivIsSet();
    osaka_nw_mkt_cont_div_is_modified = p_row.getOsakaNwMktContDivIsModified();
    nagoya_grw_cpy_mkt_cont_div = p_row.getNagoyaGrwCpyMktContDiv();
    nagoya_grw_cpy_mkt_cont_div_is_set = p_row.getNagoyaGrwCpyMktContDivIsSet();
    nagoya_grw_cpy_mkt_cont_div_is_modified = p_row.getNagoyaGrwCpyMktContDivIsModified();
    for_war_trade_cont_div = p_row.getForWarTradeContDiv();
    for_war_trade_cont_div_is_set = p_row.getForWarTradeContDivIsSet();
    for_war_trade_cont_div_is_modified = p_row.getForWarTradeContDivIsModified();
    tentou_trade_cont_div = p_row.getTentouTradeContDiv();
    tentou_trade_cont_div_is_set = p_row.getTentouTradeContDivIsSet();
    tentou_trade_cont_div_is_modified = p_row.getTentouTradeContDivIsModified();
    bond_d_and_c_cont_div = p_row.getBondDAndCContDiv();
    bond_d_and_c_cont_div_is_set = p_row.getBondDAndCContDivIsSet();
    bond_d_and_c_cont_div_is_modified = p_row.getBondDAndCContDivIsModified();
    sapporo_amb_cont_div = p_row.getSapporoAmbContDiv();
    sapporo_amb_cont_div_is_set = p_row.getSapporoAmbContDivIsSet();
    sapporo_amb_cont_div_is_modified = p_row.getSapporoAmbContDivIsModified();
    new_tb_cont_div = p_row.getNewTbContDiv();
    new_tb_cont_div_is_set = p_row.getNewTbContDivIsSet();
    new_tb_cont_div_is_modified = p_row.getNewTbContDivIsModified();
    gen_credit_acc_cont_div = p_row.getGenCreditAccContDiv();
    gen_credit_acc_cont_div_is_set = p_row.getGenCreditAccContDivIsSet();
    gen_credit_acc_cont_div_is_modified = p_row.getGenCreditAccContDivIsModified();
    bond_tentou_op_cont_div = p_row.getBondTentouOpContDiv();
    bond_tentou_op_cont_div_is_set = p_row.getBondTentouOpContDivIsSet();
    bond_tentou_op_cont_div_is_modified = p_row.getBondTentouOpContDivIsModified();
    fukuoka_qb_cont_div = p_row.getFukuokaQbContDiv();
    fukuoka_qb_cont_div_is_set = p_row.getFukuokaQbContDivIsSet();
    fukuoka_qb_cont_div_is_modified = p_row.getFukuokaQbContDivIsModified();
    dom_war_trade_cont_div = p_row.getDomWarTradeContDiv();
    dom_war_trade_cont_div_is_set = p_row.getDomWarTradeContDivIsSet();
    dom_war_trade_cont_div_is_modified = p_row.getDomWarTradeContDivIsModified();
    tentou_sec_basis_cont_div = p_row.getTentouSecBasisContDiv();
    tentou_sec_basis_cont_div_is_set = p_row.getTentouSecBasisContDivIsSet();
    tentou_sec_basis_cont_div_is_modified = p_row.getTentouSecBasisContDivIsModified();
    dom_for_bond_cont_div = p_row.getDomForBondContDiv();
    dom_for_bond_cont_div_is_set = p_row.getDomForBondContDivIsSet();
    dom_for_bond_cont_div_is_modified = p_row.getDomForBondContDivIsModified();
    tentou_sp_rule_cont_div = p_row.getTentouSpRuleContDiv();
    tentou_sp_rule_cont_div_is_set = p_row.getTentouSpRuleContDivIsSet();
    tentou_sp_rule_cont_div_is_modified = p_row.getTentouSpRuleContDivIsModified();
    dummy16 = p_row.getDummy16();
    dummy16_is_set = p_row.getDummy16IsSet();
    dummy16_is_modified = p_row.getDummy16IsModified();
    dummy17 = p_row.getDummy17();
    dummy17_is_set = p_row.getDummy17IsSet();
    dummy17_is_modified = p_row.getDummy17IsModified();
    dummy18 = p_row.getDummy18();
    dummy18_is_set = p_row.getDummy18IsSet();
    dummy18_is_modified = p_row.getDummy18IsModified();
    dummy19 = p_row.getDummy19();
    dummy19_is_set = p_row.getDummy19IsSet();
    dummy19_is_modified = p_row.getDummy19IsModified();
    dummy20 = p_row.getDummy20();
    dummy20_is_set = p_row.getDummy20IsSet();
    dummy20_is_modified = p_row.getDummy20IsModified();
    mrf_contract_div = p_row.getMrfContractDiv();
    mrf_contract_div_is_set = p_row.getMrfContractDivIsSet();
    mrf_contract_div_is_modified = p_row.getMrfContractDivIsModified();
    new_account_div = p_row.getNewAccountDiv();
    new_account_div_is_set = p_row.getNewAccountDivIsSet();
    new_account_div_is_modified = p_row.getNewAccountDivIsModified();
    via_trust_bank_div = p_row.getViaTrustBankDiv();
    via_trust_bank_div_is_set = p_row.getViaTrustBankDivIsSet();
    via_trust_bank_div_is_modified = p_row.getViaTrustBankDivIsModified();
    class_div = p_row.getClassDiv();
    class_div_is_set = p_row.getClassDivIsSet();
    class_div_is_modified = p_row.getClassDivIsModified();
    address_code = p_row.getAddressCode();
    address_code_is_set = p_row.getAddressCodeIsSet();
    address_code_is_modified = p_row.getAddressCodeIsModified();
    person_identify = p_row.getPersonIdentify();
    person_identify_is_set = p_row.getPersonIdentifyIsSet();
    person_identify_is_modified = p_row.getPersonIdentifyIsModified();
    cancel_div = p_row.getCancelDiv();
    cancel_div_is_set = p_row.getCancelDivIsSet();
    cancel_div_is_modified = p_row.getCancelDivIsModified();
    add_chg_div = p_row.getAddChgDiv();
    add_chg_div_is_set = p_row.getAddChgDivIsSet();
    add_chg_div_is_modified = p_row.getAddChgDivIsModified();
    reserve = p_row.getReserve();
    reserve_is_set = p_row.getReserveIsSet();
    reserve_is_modified = p_row.getReserveIsModified();
    org_deposit_div = p_row.getOrgDepositDiv();
    org_deposit_div_is_set = p_row.getOrgDepositDivIsSet();
    org_deposit_div_is_modified = p_row.getOrgDepositDivIsModified();
    eq_hold_rep_div = p_row.getEqHoldRepDiv();
    eq_hold_rep_div_is_set = p_row.getEqHoldRepDivIsSet();
    eq_hold_rep_div_is_modified = p_row.getEqHoldRepDivIsModified();
    chkup_rep_div = p_row.getChkupRepDiv();
    chkup_rep_div_is_set = p_row.getChkupRepDivIsSet();
    chkup_rep_div_is_modified = p_row.getChkupRepDivIsModified();
    fax = p_row.getFax();
    fax_is_set = p_row.getFaxIsSet();
    fax_is_modified = p_row.getFaxIsModified();
    hq_input_div = p_row.getHqInputDiv();
    hq_input_div_is_set = p_row.getHqInputDivIsSet();
    hq_input_div_is_modified = p_row.getHqInputDivIsModified();
    yellow_customer = p_row.getYellowCustomer();
    yellow_customer_is_set = p_row.getYellowCustomerIsSet();
    yellow_customer_is_modified = p_row.getYellowCustomerIsModified();
    dummy21 = p_row.getDummy21();
    dummy21_is_set = p_row.getDummy21IsSet();
    dummy21_is_modified = p_row.getDummy21IsModified();
    dummy22 = p_row.getDummy22();
    dummy22_is_set = p_row.getDummy22IsSet();
    dummy22_is_modified = p_row.getDummy22IsModified();
    bond_butt_div = p_row.getBondButtDiv();
    bond_butt_div_is_set = p_row.getBondButtDivIsSet();
    bond_butt_div_is_modified = p_row.getBondButtDivIsModified();
    hofuri_entry = p_row.getHofuriEntry();
    hofuri_entry_is_set = p_row.getHofuriEntryIsSet();
    hofuri_entry_is_modified = p_row.getHofuriEntryIsModified();
    agreed_non_pkg_div = p_row.getAgreedNonPkgDiv();
    agreed_non_pkg_div_is_set = p_row.getAgreedNonPkgDivIsSet();
    agreed_non_pkg_div_is_modified = p_row.getAgreedNonPkgDivIsModified();
    position_report_cycle_div = p_row.getPositionReportCycleDiv();
    position_report_cycle_div_is_set = p_row.getPositionReportCycleDivIsSet();
    position_report_cycle_div_is_modified = p_row.getPositionReportCycleDivIsModified();
    position_report_div = p_row.getPositionReportDiv();
    position_report_div_is_set = p_row.getPositionReportDivIsSet();
    position_report_div_is_modified = p_row.getPositionReportDivIsModified();
    certificate_deposit_flag = p_row.getCertificateDepositFlag();
    certificate_deposit_flag_is_set = p_row.getCertificateDepositFlagIsSet();
    certificate_deposit_flag_is_modified = p_row.getCertificateDepositFlagIsModified();
    account_statement_flag = p_row.getAccountStatementFlag();
    account_statement_flag_is_set = p_row.getAccountStatementFlagIsSet();
    account_statement_flag_is_modified = p_row.getAccountStatementFlagIsModified();
    trading_report_div = p_row.getTradingReportDiv();
    trading_report_div_is_set = p_row.getTradingReportDivIsSet();
    trading_report_div_is_modified = p_row.getTradingReportDivIsModified();
    inv_trast_ope_report = p_row.getInvTrastOpeReport();
    inv_trast_ope_report_is_set = p_row.getInvTrastOpeReportIsSet();
    inv_trast_ope_report_is_modified = p_row.getInvTrastOpeReportIsModified();
    tax_type = p_row.getTaxType();
    tax_type_is_set = p_row.getTaxTypeIsSet();
    tax_type_is_modified = p_row.getTaxTypeIsModified();
    margin_tax_type = p_row.getMarginTaxType();
    margin_tax_type_is_set = p_row.getMarginTaxTypeIsSet();
    margin_tax_type_is_modified = p_row.getMarginTaxTypeIsModified();
    equity_sp_acc_open_date = p_row.getEquitySpAccOpenDate();
    equity_sp_acc_open_date_is_set = p_row.getEquitySpAccOpenDateIsSet();
    equity_sp_acc_open_date_is_modified = p_row.getEquitySpAccOpenDateIsModified();
    margin_sp_acc_open_date = p_row.getMarginSpAccOpenDate();
    margin_sp_acc_open_date_is_set = p_row.getMarginSpAccOpenDateIsSet();
    margin_sp_acc_open_date_is_modified = p_row.getMarginSpAccOpenDateIsModified();
    tax_type_last = p_row.getTaxTypeLast();
    tax_type_last_is_set = p_row.getTaxTypeLastIsSet();
    tax_type_last_is_modified = p_row.getTaxTypeLastIsModified();
    margin_tax_type_last = p_row.getMarginTaxTypeLast();
    margin_tax_type_last_is_set = p_row.getMarginTaxTypeLastIsSet();
    margin_tax_type_last_is_modified = p_row.getMarginTaxTypeLastIsModified();
    tax_type_next = p_row.getTaxTypeNext();
    tax_type_next_is_set = p_row.getTaxTypeNextIsSet();
    tax_type_next_is_modified = p_row.getTaxTypeNextIsModified();
    margin_tax_type_next = p_row.getMarginTaxTypeNext();
    margin_tax_type_next_is_set = p_row.getMarginTaxTypeNextIsSet();
    margin_tax_type_next_is_modified = p_row.getMarginTaxTypeNextIsModified();
    fluct_date = p_row.getFluctDate();
    fluct_date_is_set = p_row.getFluctDateIsSet();
    fluct_date_is_modified = p_row.getFluctDateIsModified();
    margin_fluct_date = p_row.getMarginFluctDate();
    margin_fluct_date_is_set = p_row.getMarginFluctDateIsSet();
    margin_fluct_date_is_modified = p_row.getMarginFluctDateIsModified();
    local_tax_div_last = p_row.getLocalTaxDivLast();
    local_tax_div_last_is_set = p_row.getLocalTaxDivLastIsSet();
    local_tax_div_last_is_modified = p_row.getLocalTaxDivLastIsModified();
    local_tax_div = p_row.getLocalTaxDiv();
    local_tax_div_is_set = p_row.getLocalTaxDivIsSet();
    local_tax_div_is_modified = p_row.getLocalTaxDivIsModified();
    local_tax_div_next = p_row.getLocalTaxDivNext();
    local_tax_div_next_is_set = p_row.getLocalTaxDivNextIsSet();
    local_tax_div_next_is_modified = p_row.getLocalTaxDivNextIsModified();
    qualified_inst_investor_div = p_row.getQualifiedInstInvestorDiv();
    qualified_inst_investor_div_is_set = p_row.getQualifiedInstInvestorDivIsSet();
    qualified_inst_investor_div_is_modified = p_row.getQualifiedInstInvestorDivIsModified();
    quoto_type = p_row.getQuotoType();
    quoto_type_is_set = p_row.getQuotoTypeIsSet();
    quoto_type_is_modified = p_row.getQuotoTypeIsModified();
    sp_acc_abolish_date = p_row.getSpAccAbolishDate();
    sp_acc_abolish_date_is_set = p_row.getSpAccAbolishDateIsSet();
    sp_acc_abolish_date_is_modified = p_row.getSpAccAbolishDateIsModified();
    sp_mng_acc_open_div = p_row.getSpMngAccOpenDiv();
    sp_mng_acc_open_div_is_set = p_row.getSpMngAccOpenDivIsSet();
    sp_mng_acc_open_div_is_modified = p_row.getSpMngAccOpenDivIsModified();
    reserve_area = p_row.getReserveArea();
    reserve_area_is_set = p_row.getReserveAreaIsSet();
    reserve_area_is_modified = p_row.getReserveAreaIsModified();
    web3_encrypted_password = p_row.getWeb3EncryptedPassword();
    web3_encrypted_password_is_set = p_row.getWeb3EncryptedPasswordIsSet();
    web3_encrypted_password_is_modified = p_row.getWeb3EncryptedPasswordIsModified();
    xtrade_encrypted_password = p_row.getXtradeEncryptedPassword();
    xtrade_encrypted_password_is_set = p_row.getXtradeEncryptedPasswordIsSet();
    xtrade_encrypted_password_is_modified = p_row.getXtradeEncryptedPasswordIsModified();
    broadcast_law = p_row.getBroadcastLaw();
    broadcast_law_is_set = p_row.getBroadcastLawIsSet();
    broadcast_law_is_modified = p_row.getBroadcastLawIsModified();
    aviation_law = p_row.getAviationLaw();
    aviation_law_is_set = p_row.getAviationLawIsSet();
    aviation_law_is_modified = p_row.getAviationLawIsModified();
    ntt_law = p_row.getNttLaw();
    ntt_law_is_set = p_row.getNttLawIsSet();
    ntt_law_is_modified = p_row.getNttLawIsModified();
    dividend_trans_designate = p_row.getDividendTransDesignate();
    dividend_trans_designate_is_set = p_row.getDividendTransDesignateIsSet();
    dividend_trans_designate_is_modified = p_row.getDividendTransDesignateIsModified();
    standing_proxy = p_row.getStandingProxy();
    standing_proxy_is_set = p_row.getStandingProxyIsSet();
    standing_proxy_is_modified = p_row.getStandingProxyIsModified();
    statutory_agent = p_row.getStatutoryAgent();
    statutory_agent_is_set = p_row.getStatutoryAgentIsSet();
    statutory_agent_is_modified = p_row.getStatutoryAgentIsModified();
    affiliate_account_code = p_row.getAffiliateAccountCode();
    affiliate_account_code_is_set = p_row.getAffiliateAccountCodeIsSet();
    affiliate_account_code_is_modified = p_row.getAffiliateAccountCodeIsModified();
    agency_notify_cmp_div = p_row.getAgencyNotifyCmpDiv();
    agency_notify_cmp_div_is_set = p_row.getAgencyNotifyCmpDivIsSet();
    agency_notify_cmp_div_is_modified = p_row.getAgencyNotifyCmpDivIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      gen_acc_div_is_set = true;
      gen_acc_div_is_modified = true;
      gen_br_del_div_is_set = true;
      gen_br_del_div_is_modified = true;
      ruito_acc_open_div_is_set = true;
      ruito_acc_open_div_is_modified = true;
      margin_acc_open_div_is_set = true;
      margin_acc_open_div_is_modified = true;
      when_issued_acc_open_div_is_set = true;
      when_issued_acc_open_div_is_modified = true;
      report_dispatch_stop_div_is_set = true;
      report_dispatch_stop_div_is_modified = true;
      doc_dispatch_div_is_set = true;
      doc_dispatch_div_is_modified = true;
      gp_br_del_div_is_set = true;
      gp_br_del_div_is_modified = true;
      account_open_date_is_set = true;
      account_open_date_is_modified = true;
      last_update_date_is_set = true;
      last_update_date_is_modified = true;
      sonar_trader_code_is_set = true;
      sonar_trader_code_is_modified = true;
      rep_dispatch_stp_bd_is_set = true;
      rep_dispatch_stp_bd_is_modified = true;
      occupation_is_set = true;
      occupation_is_modified = true;
      safe_cont_open_div_is_set = true;
      safe_cont_open_div_is_modified = true;
      foreign_sec_acc_open_div_is_set = true;
      foreign_sec_acc_open_div_is_modified = true;
      tokuyu_acc_open_div_is_set = true;
      tokuyu_acc_open_div_is_modified = true;
      gold_acc_open_div_is_set = true;
      gold_acc_open_div_is_modified = true;
      total_trade_open_div_is_set = true;
      total_trade_open_div_is_modified = true;
      tie_up_loan_open_div_is_set = true;
      tie_up_loan_open_div_is_modified = true;
      ifo_acc_open_div_tokyo_is_set = true;
      ifo_acc_open_div_tokyo_is_modified = true;
      address_unknown_is_set = true;
      address_unknown_is_modified = true;
      cust_div_is_set = true;
      cust_div_is_modified = true;
      deposit_div_is_set = true;
      deposit_div_is_modified = true;
      all_substitution_div_is_set = true;
      all_substitution_div_is_modified = true;
      ins_loan_bill_mthd_div_is_set = true;
      ins_loan_bill_mthd_div_is_modified = true;
      ins_loan_cer_mthd_div_is_set = true;
      ins_loan_cer_mthd_div_is_modified = true;
      ins_loan_clause_mthd_div_is_set = true;
      ins_loan_clause_mthd_div_is_modified = true;
      dom_tax_div_is_set = true;
      dom_tax_div_is_modified = true;
      client_trader_code_is_set = true;
      client_trader_code_is_modified = true;
      telephone_is_set = true;
      telephone_is_modified = true;
      family_name_alt1_is_set = true;
      family_name_alt1_is_modified = true;
      zip_code_is_set = true;
      zip_code_is_modified = true;
      prefecture_is_set = true;
      prefecture_is_modified = true;
      comma_is_set = true;
      comma_is_modified = true;
      address_line1_kana_is_set = true;
      address_line1_kana_is_modified = true;
      address_line2_kana_is_set = true;
      address_line2_kana_is_modified = true;
      address_line3_kana_is_set = true;
      address_line3_kana_is_modified = true;
      family_name_is_set = true;
      family_name_is_modified = true;
      address_line1_is_set = true;
      address_line1_is_modified = true;
      address_line2_is_set = true;
      address_line2_is_modified = true;
      address_line3_is_set = true;
      address_line3_is_modified = true;
      contact_address_telephone_is_set = true;
      contact_address_telephone_is_modified = true;
      contact_address_is_set = true;
      contact_address_is_modified = true;
      era_born_is_set = true;
      era_born_is_modified = true;
      born_date_is_set = true;
      born_date_is_modified = true;
      sex_is_set = true;
      sex_is_modified = true;
      before_acc_trans_br_code_is_set = true;
      before_acc_trans_br_code_is_modified = true;
      before_acc_trans_cust_code_is_set = true;
      before_acc_trans_cust_code_is_modified = true;
      d_card_appli_date_is_set = true;
      d_card_appli_date_is_modified = true;
      d_card_issue_date_is_set = true;
      d_card_issue_date_is_modified = true;
      d_card_password_is_set = true;
      d_card_password_is_modified = true;
      d_card_issue_reason_is_set = true;
      d_card_issue_reason_is_modified = true;
      d_card_issue_number_is_set = true;
      d_card_issue_number_is_modified = true;
      d_card_stop_date_is_set = true;
      d_card_stop_date_is_modified = true;
      d_card_stop_reason_is_set = true;
      d_card_stop_reason_is_modified = true;
      d_card_cancel_date_is_set = true;
      d_card_cancel_date_is_modified = true;
      d_card_cancel_reason_is_set = true;
      d_card_cancel_reason_is_modified = true;
      d_card_name_is_set = true;
      d_card_name_is_modified = true;
      pass_err_code_type_is_set = true;
      pass_err_code_type_is_modified = true;
      pass_err_code_number_is_set = true;
      pass_err_code_number_is_modified = true;
      pass_err_code_chg_date_is_set = true;
      pass_err_code_chg_date_is_modified = true;
      pass_err_code_chg_time_is_set = true;
      pass_err_code_chg_time_is_modified = true;
      ans_cust_div_is_set = true;
      ans_cust_div_is_modified = true;
      ans_stock_appli_cate_is_set = true;
      ans_stock_appli_cate_is_modified = true;
      dummy1_is_set = true;
      dummy1_is_modified = true;
      self_assessed_sep_tax_is_set = true;
      self_assessed_sep_tax_is_modified = true;
      tokuyu_appli_div_is_set = true;
      tokuyu_appli_div_is_modified = true;
      maruyu_appli_div_is_set = true;
      maruyu_appli_div_is_modified = true;
      tokuyu_laundering_div_is_set = true;
      tokuyu_laundering_div_is_modified = true;
      maruyu_laundering_div_is_set = true;
      maruyu_laundering_div_is_modified = true;
      tax_div_is_set = true;
      tax_div_is_modified = true;
      dummy2_is_set = true;
      dummy2_is_modified = true;
      ht_settlement_way_is_set = true;
      ht_settlement_way_is_modified = true;
      dummy3_is_set = true;
      dummy3_is_modified = true;
      total_tax_identity_is_set = true;
      total_tax_identity_is_modified = true;
      dummy4_is_set = true;
      dummy4_is_modified = true;
      ifo_acc_open_div_osaka_is_set = true;
      ifo_acc_open_div_osaka_is_modified = true;
      mort_trade_open_div_is_set = true;
      mort_trade_open_div_is_modified = true;
      ifo_acc_open_div_nagoya_is_set = true;
      ifo_acc_open_div_nagoya_is_modified = true;
      os_fnc_futures_open_div_is_set = true;
      os_fnc_futures_open_div_is_modified = true;
      os_sec_futures_open_div_is_set = true;
      os_sec_futures_open_div_is_modified = true;
      tokyo_fnc_futures_open_div_is_set = true;
      tokyo_fnc_futures_open_div_is_modified = true;
      for_war_trade_open_div_is_set = true;
      for_war_trade_open_div_is_modified = true;
      tentou_trade_open_div_is_set = true;
      tentou_trade_open_div_is_modified = true;
      represent_div_is_set = true;
      represent_div_is_modified = true;
      family_nurturer_code_is_set = true;
      family_nurturer_code_is_modified = true;
      family_unit_adimin_is_set = true;
      family_unit_adimin_is_modified = true;
      new_monthly_div_is_set = true;
      new_monthly_div_is_modified = true;
      statement_skip1_div_is_set = true;
      statement_skip1_div_is_modified = true;
      statement_skip2_div_is_set = true;
      statement_skip2_div_is_modified = true;
      family_unit_del_div_is_set = true;
      family_unit_del_div_is_modified = true;
      dummy5_is_set = true;
      dummy5_is_modified = true;
      br_dispatch_div_is_set = true;
      br_dispatch_div_is_modified = true;
      maruyu_grade_div_is_set = true;
      maruyu_grade_div_is_modified = true;
      dummy6_is_set = true;
      dummy6_is_modified = true;
      dummy7_is_set = true;
      dummy7_is_modified = true;
      trans_tax_div_acc_date_is_set = true;
      trans_tax_div_acc_date_is_modified = true;
      trans_tax_div_is_set = true;
      trans_tax_div_is_modified = true;
      resident_is_set = true;
      resident_is_modified = true;
      bond_d_and_c_open_div_is_set = true;
      bond_d_and_c_open_div_is_modified = true;
      os_cd_cp_open_div_is_set = true;
      os_cd_cp_open_div_is_modified = true;
      new_tb_open_div_is_set = true;
      new_tb_open_div_is_modified = true;
      dom_cp_open_div_is_set = true;
      dom_cp_open_div_is_modified = true;
      bond_tentou_op_open_div_is_set = true;
      bond_tentou_op_open_div_is_modified = true;
      t_bond_futures_open_div_is_set = true;
      t_bond_futures_open_div_is_modified = true;
      dom_war_trade_open_div_is_set = true;
      dom_war_trade_open_div_is_modified = true;
      dummy8_is_set = true;
      dummy8_is_modified = true;
      dom_for_bond_open_div_is_set = true;
      dom_for_bond_open_div_is_modified = true;
      tentou_sp_rule_open_div_is_set = true;
      tentou_sp_rule_open_div_is_modified = true;
      dummy9_is_set = true;
      dummy9_is_modified = true;
      dummy10_is_set = true;
      dummy10_is_modified = true;
      dummy11_is_set = true;
      dummy11_is_modified = true;
      dummy12_is_set = true;
      dummy12_is_modified = true;
      dummy13_is_set = true;
      dummy13_is_modified = true;
      mrf_acc_open_div_is_set = true;
      mrf_acc_open_div_is_modified = true;
      safe_cont_div_is_set = true;
      safe_cont_div_is_modified = true;
      foreign_cont_div_is_set = true;
      foreign_cont_div_is_modified = true;
      dummy14_is_set = true;
      dummy14_is_modified = true;
      gold_cont_div_is_set = true;
      gold_cont_div_is_modified = true;
      margin_cont_div_is_set = true;
      margin_cont_div_is_modified = true;
      when_issued_cont_div_is_set = true;
      when_issued_cont_div_is_modified = true;
      futures_op_cont_div_tokyo_is_set = true;
      futures_op_cont_div_tokyo_is_modified = true;
      dummy15_is_set = true;
      dummy15_is_modified = true;
      futures_op_cont_div_osaka_is_set = true;
      futures_op_cont_div_osaka_is_modified = true;
      tokyo_mothers_cont_div_is_set = true;
      tokyo_mothers_cont_div_is_modified = true;
      futures_op_cont_div_nagoya_is_set = true;
      futures_op_cont_div_nagoya_is_modified = true;
      nq_j_gl_cont_div_is_set = true;
      nq_j_gl_cont_div_is_modified = true;
      osaka_nw_mkt_cont_div_is_set = true;
      osaka_nw_mkt_cont_div_is_modified = true;
      nagoya_grw_cpy_mkt_cont_div_is_set = true;
      nagoya_grw_cpy_mkt_cont_div_is_modified = true;
      for_war_trade_cont_div_is_set = true;
      for_war_trade_cont_div_is_modified = true;
      tentou_trade_cont_div_is_set = true;
      tentou_trade_cont_div_is_modified = true;
      bond_d_and_c_cont_div_is_set = true;
      bond_d_and_c_cont_div_is_modified = true;
      sapporo_amb_cont_div_is_set = true;
      sapporo_amb_cont_div_is_modified = true;
      new_tb_cont_div_is_set = true;
      new_tb_cont_div_is_modified = true;
      gen_credit_acc_cont_div_is_set = true;
      gen_credit_acc_cont_div_is_modified = true;
      bond_tentou_op_cont_div_is_set = true;
      bond_tentou_op_cont_div_is_modified = true;
      fukuoka_qb_cont_div_is_set = true;
      fukuoka_qb_cont_div_is_modified = true;
      dom_war_trade_cont_div_is_set = true;
      dom_war_trade_cont_div_is_modified = true;
      tentou_sec_basis_cont_div_is_set = true;
      tentou_sec_basis_cont_div_is_modified = true;
      dom_for_bond_cont_div_is_set = true;
      dom_for_bond_cont_div_is_modified = true;
      tentou_sp_rule_cont_div_is_set = true;
      tentou_sp_rule_cont_div_is_modified = true;
      dummy16_is_set = true;
      dummy16_is_modified = true;
      dummy17_is_set = true;
      dummy17_is_modified = true;
      dummy18_is_set = true;
      dummy18_is_modified = true;
      dummy19_is_set = true;
      dummy19_is_modified = true;
      dummy20_is_set = true;
      dummy20_is_modified = true;
      mrf_contract_div_is_set = true;
      mrf_contract_div_is_modified = true;
      new_account_div_is_set = true;
      new_account_div_is_modified = true;
      via_trust_bank_div_is_set = true;
      via_trust_bank_div_is_modified = true;
      class_div_is_set = true;
      class_div_is_modified = true;
      address_code_is_set = true;
      address_code_is_modified = true;
      person_identify_is_set = true;
      person_identify_is_modified = true;
      cancel_div_is_set = true;
      cancel_div_is_modified = true;
      add_chg_div_is_set = true;
      add_chg_div_is_modified = true;
      reserve_is_set = true;
      reserve_is_modified = true;
      org_deposit_div_is_set = true;
      org_deposit_div_is_modified = true;
      eq_hold_rep_div_is_set = true;
      eq_hold_rep_div_is_modified = true;
      chkup_rep_div_is_set = true;
      chkup_rep_div_is_modified = true;
      fax_is_set = true;
      fax_is_modified = true;
      hq_input_div_is_set = true;
      hq_input_div_is_modified = true;
      yellow_customer_is_set = true;
      yellow_customer_is_modified = true;
      dummy21_is_set = true;
      dummy21_is_modified = true;
      dummy22_is_set = true;
      dummy22_is_modified = true;
      bond_butt_div_is_set = true;
      bond_butt_div_is_modified = true;
      hofuri_entry_is_set = true;
      hofuri_entry_is_modified = true;
      agreed_non_pkg_div_is_set = true;
      agreed_non_pkg_div_is_modified = true;
      position_report_cycle_div_is_set = true;
      position_report_cycle_div_is_modified = true;
      position_report_div_is_set = true;
      position_report_div_is_modified = true;
      certificate_deposit_flag_is_set = true;
      certificate_deposit_flag_is_modified = true;
      account_statement_flag_is_set = true;
      account_statement_flag_is_modified = true;
      trading_report_div_is_set = true;
      trading_report_div_is_modified = true;
      inv_trast_ope_report_is_set = true;
      inv_trast_ope_report_is_modified = true;
      tax_type_is_set = true;
      tax_type_is_modified = true;
      margin_tax_type_is_set = true;
      margin_tax_type_is_modified = true;
      equity_sp_acc_open_date_is_set = true;
      equity_sp_acc_open_date_is_modified = true;
      margin_sp_acc_open_date_is_set = true;
      margin_sp_acc_open_date_is_modified = true;
      tax_type_last_is_set = true;
      tax_type_last_is_modified = true;
      margin_tax_type_last_is_set = true;
      margin_tax_type_last_is_modified = true;
      tax_type_next_is_set = true;
      tax_type_next_is_modified = true;
      margin_tax_type_next_is_set = true;
      margin_tax_type_next_is_modified = true;
      fluct_date_is_set = true;
      fluct_date_is_modified = true;
      margin_fluct_date_is_set = true;
      margin_fluct_date_is_modified = true;
      local_tax_div_last_is_set = true;
      local_tax_div_last_is_modified = true;
      local_tax_div_is_set = true;
      local_tax_div_is_modified = true;
      local_tax_div_next_is_set = true;
      local_tax_div_next_is_modified = true;
      qualified_inst_investor_div_is_set = true;
      qualified_inst_investor_div_is_modified = true;
      quoto_type_is_set = true;
      quoto_type_is_modified = true;
      sp_acc_abolish_date_is_set = true;
      sp_acc_abolish_date_is_modified = true;
      sp_mng_acc_open_div_is_set = true;
      sp_mng_acc_open_div_is_modified = true;
      reserve_area_is_set = true;
      reserve_area_is_modified = true;
      web3_encrypted_password_is_set = true;
      web3_encrypted_password_is_modified = true;
      xtrade_encrypted_password_is_set = true;
      xtrade_encrypted_password_is_modified = true;
      broadcast_law_is_set = true;
      broadcast_law_is_modified = true;
      aviation_law_is_set = true;
      aviation_law_is_modified = true;
      ntt_law_is_set = true;
      ntt_law_is_modified = true;
      dividend_trans_designate_is_set = true;
      dividend_trans_designate_is_modified = true;
      standing_proxy_is_set = true;
      standing_proxy_is_modified = true;
      statutory_agent_is_set = true;
      statutory_agent_is_modified = true;
      affiliate_account_code_is_set = true;
      affiliate_account_code_is_modified = true;
      agency_notify_cmp_div_is_set = true;
      agency_notify_cmp_div_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof MainAccountAllRow ) )
       return false;
    return fieldsEqual( (MainAccountAllRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のMainAccountAllRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( MainAccountAllRow row )
  {
    if ( comp_code == null ) {
      if ( row.getCompCode() != null )
        return false;
    } else if ( !comp_code.equals( row.getCompCode() ) ) {
        return false;
    }
    if ( gen_acc_div == null ) {
      if ( row.getGenAccDiv() != null )
        return false;
    } else if ( !gen_acc_div.equals( row.getGenAccDiv() ) ) {
        return false;
    }
    if ( gen_br_del_div == null ) {
      if ( row.getGenBrDelDiv() != null )
        return false;
    } else if ( !gen_br_del_div.equals( row.getGenBrDelDiv() ) ) {
        return false;
    }
    if ( ruito_acc_open_div == null ) {
      if ( row.getRuitoAccOpenDiv() != null )
        return false;
    } else if ( !ruito_acc_open_div.equals( row.getRuitoAccOpenDiv() ) ) {
        return false;
    }
    if ( margin_acc_open_div == null ) {
      if ( row.getMarginAccOpenDiv() != null )
        return false;
    } else if ( !margin_acc_open_div.equals( row.getMarginAccOpenDiv() ) ) {
        return false;
    }
    if ( when_issued_acc_open_div == null ) {
      if ( row.getWhenIssuedAccOpenDiv() != null )
        return false;
    } else if ( !when_issued_acc_open_div.equals( row.getWhenIssuedAccOpenDiv() ) ) {
        return false;
    }
    if ( report_dispatch_stop_div == null ) {
      if ( row.getReportDispatchStopDiv() != null )
        return false;
    } else if ( !report_dispatch_stop_div.equals( row.getReportDispatchStopDiv() ) ) {
        return false;
    }
    if ( doc_dispatch_div == null ) {
      if ( row.getDocDispatchDiv() != null )
        return false;
    } else if ( !doc_dispatch_div.equals( row.getDocDispatchDiv() ) ) {
        return false;
    }
    if ( gp_br_del_div == null ) {
      if ( row.getGpBrDelDiv() != null )
        return false;
    } else if ( !gp_br_del_div.equals( row.getGpBrDelDiv() ) ) {
        return false;
    }
    if ( account_open_date == null ) {
      if ( row.getAccountOpenDate() != null )
        return false;
    } else if ( !account_open_date.equals( row.getAccountOpenDate() ) ) {
        return false;
    }
    if ( last_update_date == null ) {
      if ( row.getLastUpdateDate() != null )
        return false;
    } else if ( !last_update_date.equals( row.getLastUpdateDate() ) ) {
        return false;
    }
    if ( br_code == null ) {
      if ( row.getBrCode() != null )
        return false;
    } else if ( !br_code.equals( row.getBrCode() ) ) {
        return false;
    }
    if ( cust_code == null ) {
      if ( row.getCustCode() != null )
        return false;
    } else if ( !cust_code.equals( row.getCustCode() ) ) {
        return false;
    }
    if ( cust_code_cd == null ) {
      if ( row.getCustCodeCd() != null )
        return false;
    } else if ( !cust_code_cd.equals( row.getCustCodeCd() ) ) {
        return false;
    }
    if ( sonar_trader_code == null ) {
      if ( row.getSonarTraderCode() != null )
        return false;
    } else if ( !sonar_trader_code.equals( row.getSonarTraderCode() ) ) {
        return false;
    }
    if ( rep_dispatch_stp_bd == null ) {
      if ( row.getRepDispatchStpBd() != null )
        return false;
    } else if ( !rep_dispatch_stp_bd.equals( row.getRepDispatchStpBd() ) ) {
        return false;
    }
    if ( occupation == null ) {
      if ( row.getOccupation() != null )
        return false;
    } else if ( !occupation.equals( row.getOccupation() ) ) {
        return false;
    }
    if ( safe_cont_open_div == null ) {
      if ( row.getSafeContOpenDiv() != null )
        return false;
    } else if ( !safe_cont_open_div.equals( row.getSafeContOpenDiv() ) ) {
        return false;
    }
    if ( foreign_sec_acc_open_div == null ) {
      if ( row.getForeignSecAccOpenDiv() != null )
        return false;
    } else if ( !foreign_sec_acc_open_div.equals( row.getForeignSecAccOpenDiv() ) ) {
        return false;
    }
    if ( tokuyu_acc_open_div == null ) {
      if ( row.getTokuyuAccOpenDiv() != null )
        return false;
    } else if ( !tokuyu_acc_open_div.equals( row.getTokuyuAccOpenDiv() ) ) {
        return false;
    }
    if ( gold_acc_open_div == null ) {
      if ( row.getGoldAccOpenDiv() != null )
        return false;
    } else if ( !gold_acc_open_div.equals( row.getGoldAccOpenDiv() ) ) {
        return false;
    }
    if ( total_trade_open_div == null ) {
      if ( row.getTotalTradeOpenDiv() != null )
        return false;
    } else if ( !total_trade_open_div.equals( row.getTotalTradeOpenDiv() ) ) {
        return false;
    }
    if ( tie_up_loan_open_div == null ) {
      if ( row.getTieUpLoanOpenDiv() != null )
        return false;
    } else if ( !tie_up_loan_open_div.equals( row.getTieUpLoanOpenDiv() ) ) {
        return false;
    }
    if ( ifo_acc_open_div_tokyo == null ) {
      if ( row.getIfoAccOpenDivTokyo() != null )
        return false;
    } else if ( !ifo_acc_open_div_tokyo.equals( row.getIfoAccOpenDivTokyo() ) ) {
        return false;
    }
    if ( address_unknown == null ) {
      if ( row.getAddressUnknown() != null )
        return false;
    } else if ( !address_unknown.equals( row.getAddressUnknown() ) ) {
        return false;
    }
    if ( cust_div == null ) {
      if ( row.getCustDiv() != null )
        return false;
    } else if ( !cust_div.equals( row.getCustDiv() ) ) {
        return false;
    }
    if ( deposit_div == null ) {
      if ( row.getDepositDiv() != null )
        return false;
    } else if ( !deposit_div.equals( row.getDepositDiv() ) ) {
        return false;
    }
    if ( all_substitution_div == null ) {
      if ( row.getAllSubstitutionDiv() != null )
        return false;
    } else if ( !all_substitution_div.equals( row.getAllSubstitutionDiv() ) ) {
        return false;
    }
    if ( ins_loan_bill_mthd_div == null ) {
      if ( row.getInsLoanBillMthdDiv() != null )
        return false;
    } else if ( !ins_loan_bill_mthd_div.equals( row.getInsLoanBillMthdDiv() ) ) {
        return false;
    }
    if ( ins_loan_cer_mthd_div == null ) {
      if ( row.getInsLoanCerMthdDiv() != null )
        return false;
    } else if ( !ins_loan_cer_mthd_div.equals( row.getInsLoanCerMthdDiv() ) ) {
        return false;
    }
    if ( ins_loan_clause_mthd_div == null ) {
      if ( row.getInsLoanClauseMthdDiv() != null )
        return false;
    } else if ( !ins_loan_clause_mthd_div.equals( row.getInsLoanClauseMthdDiv() ) ) {
        return false;
    }
    if ( dom_tax_div == null ) {
      if ( row.getDomTaxDiv() != null )
        return false;
    } else if ( !dom_tax_div.equals( row.getDomTaxDiv() ) ) {
        return false;
    }
    if ( client_trader_code == null ) {
      if ( row.getClientTraderCode() != null )
        return false;
    } else if ( !client_trader_code.equals( row.getClientTraderCode() ) ) {
        return false;
    }
    if ( telephone == null ) {
      if ( row.getTelephone() != null )
        return false;
    } else if ( !telephone.equals( row.getTelephone() ) ) {
        return false;
    }
    if ( family_name_alt1 == null ) {
      if ( row.getFamilyNameAlt1() != null )
        return false;
    } else if ( !family_name_alt1.equals( row.getFamilyNameAlt1() ) ) {
        return false;
    }
    if ( zip_code == null ) {
      if ( row.getZipCode() != null )
        return false;
    } else if ( !zip_code.equals( row.getZipCode() ) ) {
        return false;
    }
    if ( prefecture == null ) {
      if ( row.getPrefecture() != null )
        return false;
    } else if ( !prefecture.equals( row.getPrefecture() ) ) {
        return false;
    }
    if ( comma == null ) {
      if ( row.getComma() != null )
        return false;
    } else if ( !comma.equals( row.getComma() ) ) {
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
    if ( family_name == null ) {
      if ( row.getFamilyName() != null )
        return false;
    } else if ( !family_name.equals( row.getFamilyName() ) ) {
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
    if ( contact_address_telephone == null ) {
      if ( row.getContactAddressTelephone() != null )
        return false;
    } else if ( !contact_address_telephone.equals( row.getContactAddressTelephone() ) ) {
        return false;
    }
    if ( contact_address == null ) {
      if ( row.getContactAddress() != null )
        return false;
    } else if ( !contact_address.equals( row.getContactAddress() ) ) {
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
    if ( before_acc_trans_br_code == null ) {
      if ( row.getBeforeAccTransBrCode() != null )
        return false;
    } else if ( !before_acc_trans_br_code.equals( row.getBeforeAccTransBrCode() ) ) {
        return false;
    }
    if ( before_acc_trans_cust_code == null ) {
      if ( row.getBeforeAccTransCustCode() != null )
        return false;
    } else if ( !before_acc_trans_cust_code.equals( row.getBeforeAccTransCustCode() ) ) {
        return false;
    }
    if ( d_card_appli_date == null ) {
      if ( row.getDCardAppliDate() != null )
        return false;
    } else if ( !d_card_appli_date.equals( row.getDCardAppliDate() ) ) {
        return false;
    }
    if ( d_card_issue_date == null ) {
      if ( row.getDCardIssueDate() != null )
        return false;
    } else if ( !d_card_issue_date.equals( row.getDCardIssueDate() ) ) {
        return false;
    }
    if ( d_card_password == null ) {
      if ( row.getDCardPassword() != null )
        return false;
    } else if ( !d_card_password.equals( row.getDCardPassword() ) ) {
        return false;
    }
    if ( d_card_issue_reason == null ) {
      if ( row.getDCardIssueReason() != null )
        return false;
    } else if ( !d_card_issue_reason.equals( row.getDCardIssueReason() ) ) {
        return false;
    }
    if ( d_card_issue_number == null ) {
      if ( row.getDCardIssueNumber() != null )
        return false;
    } else if ( !d_card_issue_number.equals( row.getDCardIssueNumber() ) ) {
        return false;
    }
    if ( d_card_stop_date == null ) {
      if ( row.getDCardStopDate() != null )
        return false;
    } else if ( !d_card_stop_date.equals( row.getDCardStopDate() ) ) {
        return false;
    }
    if ( d_card_stop_reason == null ) {
      if ( row.getDCardStopReason() != null )
        return false;
    } else if ( !d_card_stop_reason.equals( row.getDCardStopReason() ) ) {
        return false;
    }
    if ( d_card_cancel_date == null ) {
      if ( row.getDCardCancelDate() != null )
        return false;
    } else if ( !d_card_cancel_date.equals( row.getDCardCancelDate() ) ) {
        return false;
    }
    if ( d_card_cancel_reason == null ) {
      if ( row.getDCardCancelReason() != null )
        return false;
    } else if ( !d_card_cancel_reason.equals( row.getDCardCancelReason() ) ) {
        return false;
    }
    if ( d_card_name == null ) {
      if ( row.getDCardName() != null )
        return false;
    } else if ( !d_card_name.equals( row.getDCardName() ) ) {
        return false;
    }
    if ( pass_err_code_type == null ) {
      if ( row.getPassErrCodeType() != null )
        return false;
    } else if ( !pass_err_code_type.equals( row.getPassErrCodeType() ) ) {
        return false;
    }
    if ( pass_err_code_number == null ) {
      if ( row.getPassErrCodeNumber() != null )
        return false;
    } else if ( !pass_err_code_number.equals( row.getPassErrCodeNumber() ) ) {
        return false;
    }
    if ( pass_err_code_chg_date == null ) {
      if ( row.getPassErrCodeChgDate() != null )
        return false;
    } else if ( !pass_err_code_chg_date.equals( row.getPassErrCodeChgDate() ) ) {
        return false;
    }
    if ( pass_err_code_chg_time == null ) {
      if ( row.getPassErrCodeChgTime() != null )
        return false;
    } else if ( !pass_err_code_chg_time.equals( row.getPassErrCodeChgTime() ) ) {
        return false;
    }
    if ( ans_cust_div == null ) {
      if ( row.getAnsCustDiv() != null )
        return false;
    } else if ( !ans_cust_div.equals( row.getAnsCustDiv() ) ) {
        return false;
    }
    if ( ans_stock_appli_cate == null ) {
      if ( row.getAnsStockAppliCate() != null )
        return false;
    } else if ( !ans_stock_appli_cate.equals( row.getAnsStockAppliCate() ) ) {
        return false;
    }
    if ( dummy1 == null ) {
      if ( row.getDummy1() != null )
        return false;
    } else if ( !dummy1.equals( row.getDummy1() ) ) {
        return false;
    }
    if ( self_assessed_sep_tax == null ) {
      if ( row.getSelfAssessedSepTax() != null )
        return false;
    } else if ( !self_assessed_sep_tax.equals( row.getSelfAssessedSepTax() ) ) {
        return false;
    }
    if ( tokuyu_appli_div == null ) {
      if ( row.getTokuyuAppliDiv() != null )
        return false;
    } else if ( !tokuyu_appli_div.equals( row.getTokuyuAppliDiv() ) ) {
        return false;
    }
    if ( maruyu_appli_div == null ) {
      if ( row.getMaruyuAppliDiv() != null )
        return false;
    } else if ( !maruyu_appli_div.equals( row.getMaruyuAppliDiv() ) ) {
        return false;
    }
    if ( tokuyu_laundering_div == null ) {
      if ( row.getTokuyuLaunderingDiv() != null )
        return false;
    } else if ( !tokuyu_laundering_div.equals( row.getTokuyuLaunderingDiv() ) ) {
        return false;
    }
    if ( maruyu_laundering_div == null ) {
      if ( row.getMaruyuLaunderingDiv() != null )
        return false;
    } else if ( !maruyu_laundering_div.equals( row.getMaruyuLaunderingDiv() ) ) {
        return false;
    }
    if ( tax_div == null ) {
      if ( row.getTaxDiv() != null )
        return false;
    } else if ( !tax_div.equals( row.getTaxDiv() ) ) {
        return false;
    }
    if ( dummy2 == null ) {
      if ( row.getDummy2() != null )
        return false;
    } else if ( !dummy2.equals( row.getDummy2() ) ) {
        return false;
    }
    if ( ht_settlement_way == null ) {
      if ( row.getHtSettlementWay() != null )
        return false;
    } else if ( !ht_settlement_way.equals( row.getHtSettlementWay() ) ) {
        return false;
    }
    if ( dummy3 == null ) {
      if ( row.getDummy3() != null )
        return false;
    } else if ( !dummy3.equals( row.getDummy3() ) ) {
        return false;
    }
    if ( total_tax_identity == null ) {
      if ( row.getTotalTaxIdentity() != null )
        return false;
    } else if ( !total_tax_identity.equals( row.getTotalTaxIdentity() ) ) {
        return false;
    }
    if ( dummy4 == null ) {
      if ( row.getDummy4() != null )
        return false;
    } else if ( !dummy4.equals( row.getDummy4() ) ) {
        return false;
    }
    if ( ifo_acc_open_div_osaka == null ) {
      if ( row.getIfoAccOpenDivOsaka() != null )
        return false;
    } else if ( !ifo_acc_open_div_osaka.equals( row.getIfoAccOpenDivOsaka() ) ) {
        return false;
    }
    if ( mort_trade_open_div == null ) {
      if ( row.getMortTradeOpenDiv() != null )
        return false;
    } else if ( !mort_trade_open_div.equals( row.getMortTradeOpenDiv() ) ) {
        return false;
    }
    if ( ifo_acc_open_div_nagoya == null ) {
      if ( row.getIfoAccOpenDivNagoya() != null )
        return false;
    } else if ( !ifo_acc_open_div_nagoya.equals( row.getIfoAccOpenDivNagoya() ) ) {
        return false;
    }
    if ( os_fnc_futures_open_div == null ) {
      if ( row.getOsFncFuturesOpenDiv() != null )
        return false;
    } else if ( !os_fnc_futures_open_div.equals( row.getOsFncFuturesOpenDiv() ) ) {
        return false;
    }
    if ( os_sec_futures_open_div == null ) {
      if ( row.getOsSecFuturesOpenDiv() != null )
        return false;
    } else if ( !os_sec_futures_open_div.equals( row.getOsSecFuturesOpenDiv() ) ) {
        return false;
    }
    if ( tokyo_fnc_futures_open_div == null ) {
      if ( row.getTokyoFncFuturesOpenDiv() != null )
        return false;
    } else if ( !tokyo_fnc_futures_open_div.equals( row.getTokyoFncFuturesOpenDiv() ) ) {
        return false;
    }
    if ( for_war_trade_open_div == null ) {
      if ( row.getForWarTradeOpenDiv() != null )
        return false;
    } else if ( !for_war_trade_open_div.equals( row.getForWarTradeOpenDiv() ) ) {
        return false;
    }
    if ( tentou_trade_open_div == null ) {
      if ( row.getTentouTradeOpenDiv() != null )
        return false;
    } else if ( !tentou_trade_open_div.equals( row.getTentouTradeOpenDiv() ) ) {
        return false;
    }
    if ( represent_div == null ) {
      if ( row.getRepresentDiv() != null )
        return false;
    } else if ( !represent_div.equals( row.getRepresentDiv() ) ) {
        return false;
    }
    if ( family_nurturer_code == null ) {
      if ( row.getFamilyNurturerCode() != null )
        return false;
    } else if ( !family_nurturer_code.equals( row.getFamilyNurturerCode() ) ) {
        return false;
    }
    if ( family_unit_adimin == null ) {
      if ( row.getFamilyUnitAdimin() != null )
        return false;
    } else if ( !family_unit_adimin.equals( row.getFamilyUnitAdimin() ) ) {
        return false;
    }
    if ( new_monthly_div == null ) {
      if ( row.getNewMonthlyDiv() != null )
        return false;
    } else if ( !new_monthly_div.equals( row.getNewMonthlyDiv() ) ) {
        return false;
    }
    if ( statement_skip1_div == null ) {
      if ( row.getStatementSkip1Div() != null )
        return false;
    } else if ( !statement_skip1_div.equals( row.getStatementSkip1Div() ) ) {
        return false;
    }
    if ( statement_skip2_div == null ) {
      if ( row.getStatementSkip2Div() != null )
        return false;
    } else if ( !statement_skip2_div.equals( row.getStatementSkip2Div() ) ) {
        return false;
    }
    if ( family_unit_del_div == null ) {
      if ( row.getFamilyUnitDelDiv() != null )
        return false;
    } else if ( !family_unit_del_div.equals( row.getFamilyUnitDelDiv() ) ) {
        return false;
    }
    if ( dummy5 == null ) {
      if ( row.getDummy5() != null )
        return false;
    } else if ( !dummy5.equals( row.getDummy5() ) ) {
        return false;
    }
    if ( br_dispatch_div == null ) {
      if ( row.getBrDispatchDiv() != null )
        return false;
    } else if ( !br_dispatch_div.equals( row.getBrDispatchDiv() ) ) {
        return false;
    }
    if ( maruyu_grade_div == null ) {
      if ( row.getMaruyuGradeDiv() != null )
        return false;
    } else if ( !maruyu_grade_div.equals( row.getMaruyuGradeDiv() ) ) {
        return false;
    }
    if ( dummy6 == null ) {
      if ( row.getDummy6() != null )
        return false;
    } else if ( !dummy6.equals( row.getDummy6() ) ) {
        return false;
    }
    if ( dummy7 == null ) {
      if ( row.getDummy7() != null )
        return false;
    } else if ( !dummy7.equals( row.getDummy7() ) ) {
        return false;
    }
    if ( trans_tax_div_acc_date == null ) {
      if ( row.getTransTaxDivAccDate() != null )
        return false;
    } else if ( !trans_tax_div_acc_date.equals( row.getTransTaxDivAccDate() ) ) {
        return false;
    }
    if ( trans_tax_div == null ) {
      if ( row.getTransTaxDiv() != null )
        return false;
    } else if ( !trans_tax_div.equals( row.getTransTaxDiv() ) ) {
        return false;
    }
    if ( resident == null ) {
      if ( row.getResident() != null )
        return false;
    } else if ( !resident.equals( row.getResident() ) ) {
        return false;
    }
    if ( bond_d_and_c_open_div == null ) {
      if ( row.getBondDAndCOpenDiv() != null )
        return false;
    } else if ( !bond_d_and_c_open_div.equals( row.getBondDAndCOpenDiv() ) ) {
        return false;
    }
    if ( os_cd_cp_open_div == null ) {
      if ( row.getOsCdCpOpenDiv() != null )
        return false;
    } else if ( !os_cd_cp_open_div.equals( row.getOsCdCpOpenDiv() ) ) {
        return false;
    }
    if ( new_tb_open_div == null ) {
      if ( row.getNewTbOpenDiv() != null )
        return false;
    } else if ( !new_tb_open_div.equals( row.getNewTbOpenDiv() ) ) {
        return false;
    }
    if ( dom_cp_open_div == null ) {
      if ( row.getDomCpOpenDiv() != null )
        return false;
    } else if ( !dom_cp_open_div.equals( row.getDomCpOpenDiv() ) ) {
        return false;
    }
    if ( bond_tentou_op_open_div == null ) {
      if ( row.getBondTentouOpOpenDiv() != null )
        return false;
    } else if ( !bond_tentou_op_open_div.equals( row.getBondTentouOpOpenDiv() ) ) {
        return false;
    }
    if ( t_bond_futures_open_div == null ) {
      if ( row.getTBondFuturesOpenDiv() != null )
        return false;
    } else if ( !t_bond_futures_open_div.equals( row.getTBondFuturesOpenDiv() ) ) {
        return false;
    }
    if ( dom_war_trade_open_div == null ) {
      if ( row.getDomWarTradeOpenDiv() != null )
        return false;
    } else if ( !dom_war_trade_open_div.equals( row.getDomWarTradeOpenDiv() ) ) {
        return false;
    }
    if ( dummy8 == null ) {
      if ( row.getDummy8() != null )
        return false;
    } else if ( !dummy8.equals( row.getDummy8() ) ) {
        return false;
    }
    if ( dom_for_bond_open_div == null ) {
      if ( row.getDomForBondOpenDiv() != null )
        return false;
    } else if ( !dom_for_bond_open_div.equals( row.getDomForBondOpenDiv() ) ) {
        return false;
    }
    if ( tentou_sp_rule_open_div == null ) {
      if ( row.getTentouSpRuleOpenDiv() != null )
        return false;
    } else if ( !tentou_sp_rule_open_div.equals( row.getTentouSpRuleOpenDiv() ) ) {
        return false;
    }
    if ( dummy9 == null ) {
      if ( row.getDummy9() != null )
        return false;
    } else if ( !dummy9.equals( row.getDummy9() ) ) {
        return false;
    }
    if ( dummy10 == null ) {
      if ( row.getDummy10() != null )
        return false;
    } else if ( !dummy10.equals( row.getDummy10() ) ) {
        return false;
    }
    if ( dummy11 == null ) {
      if ( row.getDummy11() != null )
        return false;
    } else if ( !dummy11.equals( row.getDummy11() ) ) {
        return false;
    }
    if ( dummy12 == null ) {
      if ( row.getDummy12() != null )
        return false;
    } else if ( !dummy12.equals( row.getDummy12() ) ) {
        return false;
    }
    if ( dummy13 == null ) {
      if ( row.getDummy13() != null )
        return false;
    } else if ( !dummy13.equals( row.getDummy13() ) ) {
        return false;
    }
    if ( mrf_acc_open_div == null ) {
      if ( row.getMrfAccOpenDiv() != null )
        return false;
    } else if ( !mrf_acc_open_div.equals( row.getMrfAccOpenDiv() ) ) {
        return false;
    }
    if ( safe_cont_div == null ) {
      if ( row.getSafeContDiv() != null )
        return false;
    } else if ( !safe_cont_div.equals( row.getSafeContDiv() ) ) {
        return false;
    }
    if ( foreign_cont_div == null ) {
      if ( row.getForeignContDiv() != null )
        return false;
    } else if ( !foreign_cont_div.equals( row.getForeignContDiv() ) ) {
        return false;
    }
    if ( dummy14 == null ) {
      if ( row.getDummy14() != null )
        return false;
    } else if ( !dummy14.equals( row.getDummy14() ) ) {
        return false;
    }
    if ( gold_cont_div == null ) {
      if ( row.getGoldContDiv() != null )
        return false;
    } else if ( !gold_cont_div.equals( row.getGoldContDiv() ) ) {
        return false;
    }
    if ( margin_cont_div == null ) {
      if ( row.getMarginContDiv() != null )
        return false;
    } else if ( !margin_cont_div.equals( row.getMarginContDiv() ) ) {
        return false;
    }
    if ( when_issued_cont_div == null ) {
      if ( row.getWhenIssuedContDiv() != null )
        return false;
    } else if ( !when_issued_cont_div.equals( row.getWhenIssuedContDiv() ) ) {
        return false;
    }
    if ( futures_op_cont_div_tokyo == null ) {
      if ( row.getFuturesOpContDivTokyo() != null )
        return false;
    } else if ( !futures_op_cont_div_tokyo.equals( row.getFuturesOpContDivTokyo() ) ) {
        return false;
    }
    if ( dummy15 == null ) {
      if ( row.getDummy15() != null )
        return false;
    } else if ( !dummy15.equals( row.getDummy15() ) ) {
        return false;
    }
    if ( futures_op_cont_div_osaka == null ) {
      if ( row.getFuturesOpContDivOsaka() != null )
        return false;
    } else if ( !futures_op_cont_div_osaka.equals( row.getFuturesOpContDivOsaka() ) ) {
        return false;
    }
    if ( tokyo_mothers_cont_div == null ) {
      if ( row.getTokyoMothersContDiv() != null )
        return false;
    } else if ( !tokyo_mothers_cont_div.equals( row.getTokyoMothersContDiv() ) ) {
        return false;
    }
    if ( futures_op_cont_div_nagoya == null ) {
      if ( row.getFuturesOpContDivNagoya() != null )
        return false;
    } else if ( !futures_op_cont_div_nagoya.equals( row.getFuturesOpContDivNagoya() ) ) {
        return false;
    }
    if ( nq_j_gl_cont_div == null ) {
      if ( row.getNqJGlContDiv() != null )
        return false;
    } else if ( !nq_j_gl_cont_div.equals( row.getNqJGlContDiv() ) ) {
        return false;
    }
    if ( osaka_nw_mkt_cont_div == null ) {
      if ( row.getOsakaNwMktContDiv() != null )
        return false;
    } else if ( !osaka_nw_mkt_cont_div.equals( row.getOsakaNwMktContDiv() ) ) {
        return false;
    }
    if ( nagoya_grw_cpy_mkt_cont_div == null ) {
      if ( row.getNagoyaGrwCpyMktContDiv() != null )
        return false;
    } else if ( !nagoya_grw_cpy_mkt_cont_div.equals( row.getNagoyaGrwCpyMktContDiv() ) ) {
        return false;
    }
    if ( for_war_trade_cont_div == null ) {
      if ( row.getForWarTradeContDiv() != null )
        return false;
    } else if ( !for_war_trade_cont_div.equals( row.getForWarTradeContDiv() ) ) {
        return false;
    }
    if ( tentou_trade_cont_div == null ) {
      if ( row.getTentouTradeContDiv() != null )
        return false;
    } else if ( !tentou_trade_cont_div.equals( row.getTentouTradeContDiv() ) ) {
        return false;
    }
    if ( bond_d_and_c_cont_div == null ) {
      if ( row.getBondDAndCContDiv() != null )
        return false;
    } else if ( !bond_d_and_c_cont_div.equals( row.getBondDAndCContDiv() ) ) {
        return false;
    }
    if ( sapporo_amb_cont_div == null ) {
      if ( row.getSapporoAmbContDiv() != null )
        return false;
    } else if ( !sapporo_amb_cont_div.equals( row.getSapporoAmbContDiv() ) ) {
        return false;
    }
    if ( new_tb_cont_div == null ) {
      if ( row.getNewTbContDiv() != null )
        return false;
    } else if ( !new_tb_cont_div.equals( row.getNewTbContDiv() ) ) {
        return false;
    }
    if ( gen_credit_acc_cont_div == null ) {
      if ( row.getGenCreditAccContDiv() != null )
        return false;
    } else if ( !gen_credit_acc_cont_div.equals( row.getGenCreditAccContDiv() ) ) {
        return false;
    }
    if ( bond_tentou_op_cont_div == null ) {
      if ( row.getBondTentouOpContDiv() != null )
        return false;
    } else if ( !bond_tentou_op_cont_div.equals( row.getBondTentouOpContDiv() ) ) {
        return false;
    }
    if ( fukuoka_qb_cont_div == null ) {
      if ( row.getFukuokaQbContDiv() != null )
        return false;
    } else if ( !fukuoka_qb_cont_div.equals( row.getFukuokaQbContDiv() ) ) {
        return false;
    }
    if ( dom_war_trade_cont_div == null ) {
      if ( row.getDomWarTradeContDiv() != null )
        return false;
    } else if ( !dom_war_trade_cont_div.equals( row.getDomWarTradeContDiv() ) ) {
        return false;
    }
    if ( tentou_sec_basis_cont_div == null ) {
      if ( row.getTentouSecBasisContDiv() != null )
        return false;
    } else if ( !tentou_sec_basis_cont_div.equals( row.getTentouSecBasisContDiv() ) ) {
        return false;
    }
    if ( dom_for_bond_cont_div == null ) {
      if ( row.getDomForBondContDiv() != null )
        return false;
    } else if ( !dom_for_bond_cont_div.equals( row.getDomForBondContDiv() ) ) {
        return false;
    }
    if ( tentou_sp_rule_cont_div == null ) {
      if ( row.getTentouSpRuleContDiv() != null )
        return false;
    } else if ( !tentou_sp_rule_cont_div.equals( row.getTentouSpRuleContDiv() ) ) {
        return false;
    }
    if ( dummy16 == null ) {
      if ( row.getDummy16() != null )
        return false;
    } else if ( !dummy16.equals( row.getDummy16() ) ) {
        return false;
    }
    if ( dummy17 == null ) {
      if ( row.getDummy17() != null )
        return false;
    } else if ( !dummy17.equals( row.getDummy17() ) ) {
        return false;
    }
    if ( dummy18 == null ) {
      if ( row.getDummy18() != null )
        return false;
    } else if ( !dummy18.equals( row.getDummy18() ) ) {
        return false;
    }
    if ( dummy19 == null ) {
      if ( row.getDummy19() != null )
        return false;
    } else if ( !dummy19.equals( row.getDummy19() ) ) {
        return false;
    }
    if ( dummy20 == null ) {
      if ( row.getDummy20() != null )
        return false;
    } else if ( !dummy20.equals( row.getDummy20() ) ) {
        return false;
    }
    if ( mrf_contract_div == null ) {
      if ( row.getMrfContractDiv() != null )
        return false;
    } else if ( !mrf_contract_div.equals( row.getMrfContractDiv() ) ) {
        return false;
    }
    if ( new_account_div == null ) {
      if ( row.getNewAccountDiv() != null )
        return false;
    } else if ( !new_account_div.equals( row.getNewAccountDiv() ) ) {
        return false;
    }
    if ( via_trust_bank_div == null ) {
      if ( row.getViaTrustBankDiv() != null )
        return false;
    } else if ( !via_trust_bank_div.equals( row.getViaTrustBankDiv() ) ) {
        return false;
    }
    if ( class_div == null ) {
      if ( row.getClassDiv() != null )
        return false;
    } else if ( !class_div.equals( row.getClassDiv() ) ) {
        return false;
    }
    if ( address_code == null ) {
      if ( row.getAddressCode() != null )
        return false;
    } else if ( !address_code.equals( row.getAddressCode() ) ) {
        return false;
    }
    if ( person_identify == null ) {
      if ( row.getPersonIdentify() != null )
        return false;
    } else if ( !person_identify.equals( row.getPersonIdentify() ) ) {
        return false;
    }
    if ( cancel_div == null ) {
      if ( row.getCancelDiv() != null )
        return false;
    } else if ( !cancel_div.equals( row.getCancelDiv() ) ) {
        return false;
    }
    if ( add_chg_div == null ) {
      if ( row.getAddChgDiv() != null )
        return false;
    } else if ( !add_chg_div.equals( row.getAddChgDiv() ) ) {
        return false;
    }
    if ( reserve == null ) {
      if ( row.getReserve() != null )
        return false;
    } else if ( !reserve.equals( row.getReserve() ) ) {
        return false;
    }
    if ( org_deposit_div == null ) {
      if ( row.getOrgDepositDiv() != null )
        return false;
    } else if ( !org_deposit_div.equals( row.getOrgDepositDiv() ) ) {
        return false;
    }
    if ( eq_hold_rep_div == null ) {
      if ( row.getEqHoldRepDiv() != null )
        return false;
    } else if ( !eq_hold_rep_div.equals( row.getEqHoldRepDiv() ) ) {
        return false;
    }
    if ( chkup_rep_div == null ) {
      if ( row.getChkupRepDiv() != null )
        return false;
    } else if ( !chkup_rep_div.equals( row.getChkupRepDiv() ) ) {
        return false;
    }
    if ( fax == null ) {
      if ( row.getFax() != null )
        return false;
    } else if ( !fax.equals( row.getFax() ) ) {
        return false;
    }
    if ( hq_input_div == null ) {
      if ( row.getHqInputDiv() != null )
        return false;
    } else if ( !hq_input_div.equals( row.getHqInputDiv() ) ) {
        return false;
    }
    if ( yellow_customer == null ) {
      if ( row.getYellowCustomer() != null )
        return false;
    } else if ( !yellow_customer.equals( row.getYellowCustomer() ) ) {
        return false;
    }
    if ( dummy21 == null ) {
      if ( row.getDummy21() != null )
        return false;
    } else if ( !dummy21.equals( row.getDummy21() ) ) {
        return false;
    }
    if ( dummy22 == null ) {
      if ( row.getDummy22() != null )
        return false;
    } else if ( !dummy22.equals( row.getDummy22() ) ) {
        return false;
    }
    if ( bond_butt_div == null ) {
      if ( row.getBondButtDiv() != null )
        return false;
    } else if ( !bond_butt_div.equals( row.getBondButtDiv() ) ) {
        return false;
    }
    if ( hofuri_entry == null ) {
      if ( row.getHofuriEntry() != null )
        return false;
    } else if ( !hofuri_entry.equals( row.getHofuriEntry() ) ) {
        return false;
    }
    if ( agreed_non_pkg_div == null ) {
      if ( row.getAgreedNonPkgDiv() != null )
        return false;
    } else if ( !agreed_non_pkg_div.equals( row.getAgreedNonPkgDiv() ) ) {
        return false;
    }
    if ( position_report_cycle_div == null ) {
      if ( row.getPositionReportCycleDiv() != null )
        return false;
    } else if ( !position_report_cycle_div.equals( row.getPositionReportCycleDiv() ) ) {
        return false;
    }
    if ( position_report_div == null ) {
      if ( row.getPositionReportDiv() != null )
        return false;
    } else if ( !position_report_div.equals( row.getPositionReportDiv() ) ) {
        return false;
    }
    if ( certificate_deposit_flag == null ) {
      if ( row.getCertificateDepositFlag() != null )
        return false;
    } else if ( !certificate_deposit_flag.equals( row.getCertificateDepositFlag() ) ) {
        return false;
    }
    if ( account_statement_flag == null ) {
      if ( row.getAccountStatementFlag() != null )
        return false;
    } else if ( !account_statement_flag.equals( row.getAccountStatementFlag() ) ) {
        return false;
    }
    if ( trading_report_div == null ) {
      if ( row.getTradingReportDiv() != null )
        return false;
    } else if ( !trading_report_div.equals( row.getTradingReportDiv() ) ) {
        return false;
    }
    if ( inv_trast_ope_report == null ) {
      if ( row.getInvTrastOpeReport() != null )
        return false;
    } else if ( !inv_trast_ope_report.equals( row.getInvTrastOpeReport() ) ) {
        return false;
    }
    if ( tax_type == null ) {
      if ( row.getTaxType() != null )
        return false;
    } else if ( !tax_type.equals( row.getTaxType() ) ) {
        return false;
    }
    if ( margin_tax_type == null ) {
      if ( row.getMarginTaxType() != null )
        return false;
    } else if ( !margin_tax_type.equals( row.getMarginTaxType() ) ) {
        return false;
    }
    if ( equity_sp_acc_open_date == null ) {
      if ( row.getEquitySpAccOpenDate() != null )
        return false;
    } else if ( !equity_sp_acc_open_date.equals( row.getEquitySpAccOpenDate() ) ) {
        return false;
    }
    if ( margin_sp_acc_open_date == null ) {
      if ( row.getMarginSpAccOpenDate() != null )
        return false;
    } else if ( !margin_sp_acc_open_date.equals( row.getMarginSpAccOpenDate() ) ) {
        return false;
    }
    if ( tax_type_last == null ) {
      if ( row.getTaxTypeLast() != null )
        return false;
    } else if ( !tax_type_last.equals( row.getTaxTypeLast() ) ) {
        return false;
    }
    if ( margin_tax_type_last == null ) {
      if ( row.getMarginTaxTypeLast() != null )
        return false;
    } else if ( !margin_tax_type_last.equals( row.getMarginTaxTypeLast() ) ) {
        return false;
    }
    if ( tax_type_next == null ) {
      if ( row.getTaxTypeNext() != null )
        return false;
    } else if ( !tax_type_next.equals( row.getTaxTypeNext() ) ) {
        return false;
    }
    if ( margin_tax_type_next == null ) {
      if ( row.getMarginTaxTypeNext() != null )
        return false;
    } else if ( !margin_tax_type_next.equals( row.getMarginTaxTypeNext() ) ) {
        return false;
    }
    if ( fluct_date == null ) {
      if ( row.getFluctDate() != null )
        return false;
    } else if ( !fluct_date.equals( row.getFluctDate() ) ) {
        return false;
    }
    if ( margin_fluct_date == null ) {
      if ( row.getMarginFluctDate() != null )
        return false;
    } else if ( !margin_fluct_date.equals( row.getMarginFluctDate() ) ) {
        return false;
    }
    if ( local_tax_div_last == null ) {
      if ( row.getLocalTaxDivLast() != null )
        return false;
    } else if ( !local_tax_div_last.equals( row.getLocalTaxDivLast() ) ) {
        return false;
    }
    if ( local_tax_div == null ) {
      if ( row.getLocalTaxDiv() != null )
        return false;
    } else if ( !local_tax_div.equals( row.getLocalTaxDiv() ) ) {
        return false;
    }
    if ( local_tax_div_next == null ) {
      if ( row.getLocalTaxDivNext() != null )
        return false;
    } else if ( !local_tax_div_next.equals( row.getLocalTaxDivNext() ) ) {
        return false;
    }
    if ( qualified_inst_investor_div == null ) {
      if ( row.getQualifiedInstInvestorDiv() != null )
        return false;
    } else if ( !qualified_inst_investor_div.equals( row.getQualifiedInstInvestorDiv() ) ) {
        return false;
    }
    if ( quoto_type == null ) {
      if ( row.getQuotoType() != null )
        return false;
    } else if ( !quoto_type.equals( row.getQuotoType() ) ) {
        return false;
    }
    if ( sp_acc_abolish_date == null ) {
      if ( row.getSpAccAbolishDate() != null )
        return false;
    } else if ( !sp_acc_abolish_date.equals( row.getSpAccAbolishDate() ) ) {
        return false;
    }
    if ( sp_mng_acc_open_div == null ) {
      if ( row.getSpMngAccOpenDiv() != null )
        return false;
    } else if ( !sp_mng_acc_open_div.equals( row.getSpMngAccOpenDiv() ) ) {
        return false;
    }
    if ( reserve_area == null ) {
      if ( row.getReserveArea() != null )
        return false;
    } else if ( !reserve_area.equals( row.getReserveArea() ) ) {
        return false;
    }
    if ( web3_encrypted_password == null ) {
      if ( row.getWeb3EncryptedPassword() != null )
        return false;
    } else if ( !web3_encrypted_password.equals( row.getWeb3EncryptedPassword() ) ) {
        return false;
    }
    if ( xtrade_encrypted_password == null ) {
      if ( row.getXtradeEncryptedPassword() != null )
        return false;
    } else if ( !xtrade_encrypted_password.equals( row.getXtradeEncryptedPassword() ) ) {
        return false;
    }
    if ( broadcast_law == null ) {
      if ( row.getBroadcastLaw() != null )
        return false;
    } else if ( !broadcast_law.equals( row.getBroadcastLaw() ) ) {
        return false;
    }
    if ( aviation_law == null ) {
      if ( row.getAviationLaw() != null )
        return false;
    } else if ( !aviation_law.equals( row.getAviationLaw() ) ) {
        return false;
    }
    if ( ntt_law == null ) {
      if ( row.getNttLaw() != null )
        return false;
    } else if ( !ntt_law.equals( row.getNttLaw() ) ) {
        return false;
    }
    if ( dividend_trans_designate == null ) {
      if ( row.getDividendTransDesignate() != null )
        return false;
    } else if ( !dividend_trans_designate.equals( row.getDividendTransDesignate() ) ) {
        return false;
    }
    if ( standing_proxy == null ) {
      if ( row.getStandingProxy() != null )
        return false;
    } else if ( !standing_proxy.equals( row.getStandingProxy() ) ) {
        return false;
    }
    if ( statutory_agent == null ) {
      if ( row.getStatutoryAgent() != null )
        return false;
    } else if ( !statutory_agent.equals( row.getStatutoryAgent() ) ) {
        return false;
    }
    if ( affiliate_account_code == null ) {
      if ( row.getAffiliateAccountCode() != null )
        return false;
    } else if ( !affiliate_account_code.equals( row.getAffiliateAccountCode() ) ) {
        return false;
    }
    if ( agency_notify_cmp_div == null ) {
      if ( row.getAgencyNotifyCmpDiv() != null )
        return false;
    } else if ( !agency_notify_cmp_div.equals( row.getAgencyNotifyCmpDiv() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (comp_code!=null? comp_code.hashCode(): 0) 
        + (gen_acc_div!=null? gen_acc_div.hashCode(): 0) 
        + (gen_br_del_div!=null? gen_br_del_div.hashCode(): 0) 
        + (ruito_acc_open_div!=null? ruito_acc_open_div.hashCode(): 0) 
        + (margin_acc_open_div!=null? margin_acc_open_div.hashCode(): 0) 
        + (when_issued_acc_open_div!=null? when_issued_acc_open_div.hashCode(): 0) 
        + (report_dispatch_stop_div!=null? report_dispatch_stop_div.hashCode(): 0) 
        + (doc_dispatch_div!=null? doc_dispatch_div.hashCode(): 0) 
        + (gp_br_del_div!=null? gp_br_del_div.hashCode(): 0) 
        + (account_open_date!=null? account_open_date.hashCode(): 0) 
        + (last_update_date!=null? last_update_date.hashCode(): 0) 
        + (br_code!=null? br_code.hashCode(): 0) 
        + (cust_code!=null? cust_code.hashCode(): 0) 
        + (cust_code_cd!=null? cust_code_cd.hashCode(): 0) 
        + (sonar_trader_code!=null? sonar_trader_code.hashCode(): 0) 
        + (rep_dispatch_stp_bd!=null? rep_dispatch_stp_bd.hashCode(): 0) 
        + (occupation!=null? occupation.hashCode(): 0) 
        + (safe_cont_open_div!=null? safe_cont_open_div.hashCode(): 0) 
        + (foreign_sec_acc_open_div!=null? foreign_sec_acc_open_div.hashCode(): 0) 
        + (tokuyu_acc_open_div!=null? tokuyu_acc_open_div.hashCode(): 0) 
        + (gold_acc_open_div!=null? gold_acc_open_div.hashCode(): 0) 
        + (total_trade_open_div!=null? total_trade_open_div.hashCode(): 0) 
        + (tie_up_loan_open_div!=null? tie_up_loan_open_div.hashCode(): 0) 
        + (ifo_acc_open_div_tokyo!=null? ifo_acc_open_div_tokyo.hashCode(): 0) 
        + (address_unknown!=null? address_unknown.hashCode(): 0) 
        + (cust_div!=null? cust_div.hashCode(): 0) 
        + (deposit_div!=null? deposit_div.hashCode(): 0) 
        + (all_substitution_div!=null? all_substitution_div.hashCode(): 0) 
        + (ins_loan_bill_mthd_div!=null? ins_loan_bill_mthd_div.hashCode(): 0) 
        + (ins_loan_cer_mthd_div!=null? ins_loan_cer_mthd_div.hashCode(): 0) 
        + (ins_loan_clause_mthd_div!=null? ins_loan_clause_mthd_div.hashCode(): 0) 
        + (dom_tax_div!=null? dom_tax_div.hashCode(): 0) 
        + (client_trader_code!=null? client_trader_code.hashCode(): 0) 
        + (telephone!=null? telephone.hashCode(): 0) 
        + (family_name_alt1!=null? family_name_alt1.hashCode(): 0) 
        + (zip_code!=null? zip_code.hashCode(): 0) 
        + (prefecture!=null? prefecture.hashCode(): 0) 
        + (comma!=null? comma.hashCode(): 0) 
        + (address_line1_kana!=null? address_line1_kana.hashCode(): 0) 
        + (address_line2_kana!=null? address_line2_kana.hashCode(): 0) 
        + (address_line3_kana!=null? address_line3_kana.hashCode(): 0) 
        + (family_name!=null? family_name.hashCode(): 0) 
        + (address_line1!=null? address_line1.hashCode(): 0) 
        + (address_line2!=null? address_line2.hashCode(): 0) 
        + (address_line3!=null? address_line3.hashCode(): 0) 
        + (contact_address_telephone!=null? contact_address_telephone.hashCode(): 0) 
        + (contact_address!=null? contact_address.hashCode(): 0) 
        + (era_born!=null? era_born.hashCode(): 0) 
        + (born_date!=null? born_date.hashCode(): 0) 
        + (sex!=null? sex.hashCode(): 0) 
        + (before_acc_trans_br_code!=null? before_acc_trans_br_code.hashCode(): 0) 
        + (before_acc_trans_cust_code!=null? before_acc_trans_cust_code.hashCode(): 0) 
        + (d_card_appli_date!=null? d_card_appli_date.hashCode(): 0) 
        + (d_card_issue_date!=null? d_card_issue_date.hashCode(): 0) 
        + (d_card_password!=null? d_card_password.hashCode(): 0) 
        + (d_card_issue_reason!=null? d_card_issue_reason.hashCode(): 0) 
        + (d_card_issue_number!=null? d_card_issue_number.hashCode(): 0) 
        + (d_card_stop_date!=null? d_card_stop_date.hashCode(): 0) 
        + (d_card_stop_reason!=null? d_card_stop_reason.hashCode(): 0) 
        + (d_card_cancel_date!=null? d_card_cancel_date.hashCode(): 0) 
        + (d_card_cancel_reason!=null? d_card_cancel_reason.hashCode(): 0) 
        + (d_card_name!=null? d_card_name.hashCode(): 0) 
        + (pass_err_code_type!=null? pass_err_code_type.hashCode(): 0) 
        + (pass_err_code_number!=null? pass_err_code_number.hashCode(): 0) 
        + (pass_err_code_chg_date!=null? pass_err_code_chg_date.hashCode(): 0) 
        + (pass_err_code_chg_time!=null? pass_err_code_chg_time.hashCode(): 0) 
        + (ans_cust_div!=null? ans_cust_div.hashCode(): 0) 
        + (ans_stock_appli_cate!=null? ans_stock_appli_cate.hashCode(): 0) 
        + (dummy1!=null? dummy1.hashCode(): 0) 
        + (self_assessed_sep_tax!=null? self_assessed_sep_tax.hashCode(): 0) 
        + (tokuyu_appli_div!=null? tokuyu_appli_div.hashCode(): 0) 
        + (maruyu_appli_div!=null? maruyu_appli_div.hashCode(): 0) 
        + (tokuyu_laundering_div!=null? tokuyu_laundering_div.hashCode(): 0) 
        + (maruyu_laundering_div!=null? maruyu_laundering_div.hashCode(): 0) 
        + (tax_div!=null? tax_div.hashCode(): 0) 
        + (dummy2!=null? dummy2.hashCode(): 0) 
        + (ht_settlement_way!=null? ht_settlement_way.hashCode(): 0) 
        + (dummy3!=null? dummy3.hashCode(): 0) 
        + (total_tax_identity!=null? total_tax_identity.hashCode(): 0) 
        + (dummy4!=null? dummy4.hashCode(): 0) 
        + (ifo_acc_open_div_osaka!=null? ifo_acc_open_div_osaka.hashCode(): 0) 
        + (mort_trade_open_div!=null? mort_trade_open_div.hashCode(): 0) 
        + (ifo_acc_open_div_nagoya!=null? ifo_acc_open_div_nagoya.hashCode(): 0) 
        + (os_fnc_futures_open_div!=null? os_fnc_futures_open_div.hashCode(): 0) 
        + (os_sec_futures_open_div!=null? os_sec_futures_open_div.hashCode(): 0) 
        + (tokyo_fnc_futures_open_div!=null? tokyo_fnc_futures_open_div.hashCode(): 0) 
        + (for_war_trade_open_div!=null? for_war_trade_open_div.hashCode(): 0) 
        + (tentou_trade_open_div!=null? tentou_trade_open_div.hashCode(): 0) 
        + (represent_div!=null? represent_div.hashCode(): 0) 
        + (family_nurturer_code!=null? family_nurturer_code.hashCode(): 0) 
        + (family_unit_adimin!=null? family_unit_adimin.hashCode(): 0) 
        + (new_monthly_div!=null? new_monthly_div.hashCode(): 0) 
        + (statement_skip1_div!=null? statement_skip1_div.hashCode(): 0) 
        + (statement_skip2_div!=null? statement_skip2_div.hashCode(): 0) 
        + (family_unit_del_div!=null? family_unit_del_div.hashCode(): 0) 
        + (dummy5!=null? dummy5.hashCode(): 0) 
        + (br_dispatch_div!=null? br_dispatch_div.hashCode(): 0) 
        + (maruyu_grade_div!=null? maruyu_grade_div.hashCode(): 0) 
        + (dummy6!=null? dummy6.hashCode(): 0) 
        + (dummy7!=null? dummy7.hashCode(): 0) 
        + (trans_tax_div_acc_date!=null? trans_tax_div_acc_date.hashCode(): 0) 
        + (trans_tax_div!=null? trans_tax_div.hashCode(): 0) 
        + (resident!=null? resident.hashCode(): 0) 
        + (bond_d_and_c_open_div!=null? bond_d_and_c_open_div.hashCode(): 0) 
        + (os_cd_cp_open_div!=null? os_cd_cp_open_div.hashCode(): 0) 
        + (new_tb_open_div!=null? new_tb_open_div.hashCode(): 0) 
        + (dom_cp_open_div!=null? dom_cp_open_div.hashCode(): 0) 
        + (bond_tentou_op_open_div!=null? bond_tentou_op_open_div.hashCode(): 0) 
        + (t_bond_futures_open_div!=null? t_bond_futures_open_div.hashCode(): 0) 
        + (dom_war_trade_open_div!=null? dom_war_trade_open_div.hashCode(): 0) 
        + (dummy8!=null? dummy8.hashCode(): 0) 
        + (dom_for_bond_open_div!=null? dom_for_bond_open_div.hashCode(): 0) 
        + (tentou_sp_rule_open_div!=null? tentou_sp_rule_open_div.hashCode(): 0) 
        + (dummy9!=null? dummy9.hashCode(): 0) 
        + (dummy10!=null? dummy10.hashCode(): 0) 
        + (dummy11!=null? dummy11.hashCode(): 0) 
        + (dummy12!=null? dummy12.hashCode(): 0) 
        + (dummy13!=null? dummy13.hashCode(): 0) 
        + (mrf_acc_open_div!=null? mrf_acc_open_div.hashCode(): 0) 
        + (safe_cont_div!=null? safe_cont_div.hashCode(): 0) 
        + (foreign_cont_div!=null? foreign_cont_div.hashCode(): 0) 
        + (dummy14!=null? dummy14.hashCode(): 0) 
        + (gold_cont_div!=null? gold_cont_div.hashCode(): 0) 
        + (margin_cont_div!=null? margin_cont_div.hashCode(): 0) 
        + (when_issued_cont_div!=null? when_issued_cont_div.hashCode(): 0) 
        + (futures_op_cont_div_tokyo!=null? futures_op_cont_div_tokyo.hashCode(): 0) 
        + (dummy15!=null? dummy15.hashCode(): 0) 
        + (futures_op_cont_div_osaka!=null? futures_op_cont_div_osaka.hashCode(): 0) 
        + (tokyo_mothers_cont_div!=null? tokyo_mothers_cont_div.hashCode(): 0) 
        + (futures_op_cont_div_nagoya!=null? futures_op_cont_div_nagoya.hashCode(): 0) 
        + (nq_j_gl_cont_div!=null? nq_j_gl_cont_div.hashCode(): 0) 
        + (osaka_nw_mkt_cont_div!=null? osaka_nw_mkt_cont_div.hashCode(): 0) 
        + (nagoya_grw_cpy_mkt_cont_div!=null? nagoya_grw_cpy_mkt_cont_div.hashCode(): 0) 
        + (for_war_trade_cont_div!=null? for_war_trade_cont_div.hashCode(): 0) 
        + (tentou_trade_cont_div!=null? tentou_trade_cont_div.hashCode(): 0) 
        + (bond_d_and_c_cont_div!=null? bond_d_and_c_cont_div.hashCode(): 0) 
        + (sapporo_amb_cont_div!=null? sapporo_amb_cont_div.hashCode(): 0) 
        + (new_tb_cont_div!=null? new_tb_cont_div.hashCode(): 0) 
        + (gen_credit_acc_cont_div!=null? gen_credit_acc_cont_div.hashCode(): 0) 
        + (bond_tentou_op_cont_div!=null? bond_tentou_op_cont_div.hashCode(): 0) 
        + (fukuoka_qb_cont_div!=null? fukuoka_qb_cont_div.hashCode(): 0) 
        + (dom_war_trade_cont_div!=null? dom_war_trade_cont_div.hashCode(): 0) 
        + (tentou_sec_basis_cont_div!=null? tentou_sec_basis_cont_div.hashCode(): 0) 
        + (dom_for_bond_cont_div!=null? dom_for_bond_cont_div.hashCode(): 0) 
        + (tentou_sp_rule_cont_div!=null? tentou_sp_rule_cont_div.hashCode(): 0) 
        + (dummy16!=null? dummy16.hashCode(): 0) 
        + (dummy17!=null? dummy17.hashCode(): 0) 
        + (dummy18!=null? dummy18.hashCode(): 0) 
        + (dummy19!=null? dummy19.hashCode(): 0) 
        + (dummy20!=null? dummy20.hashCode(): 0) 
        + (mrf_contract_div!=null? mrf_contract_div.hashCode(): 0) 
        + (new_account_div!=null? new_account_div.hashCode(): 0) 
        + (via_trust_bank_div!=null? via_trust_bank_div.hashCode(): 0) 
        + (class_div!=null? class_div.hashCode(): 0) 
        + (address_code!=null? address_code.hashCode(): 0) 
        + (person_identify!=null? person_identify.hashCode(): 0) 
        + (cancel_div!=null? cancel_div.hashCode(): 0) 
        + (add_chg_div!=null? add_chg_div.hashCode(): 0) 
        + (reserve!=null? reserve.hashCode(): 0) 
        + (org_deposit_div!=null? org_deposit_div.hashCode(): 0) 
        + (eq_hold_rep_div!=null? eq_hold_rep_div.hashCode(): 0) 
        + (chkup_rep_div!=null? chkup_rep_div.hashCode(): 0) 
        + (fax!=null? fax.hashCode(): 0) 
        + (hq_input_div!=null? hq_input_div.hashCode(): 0) 
        + (yellow_customer!=null? yellow_customer.hashCode(): 0) 
        + (dummy21!=null? dummy21.hashCode(): 0) 
        + (dummy22!=null? dummy22.hashCode(): 0) 
        + (bond_butt_div!=null? bond_butt_div.hashCode(): 0) 
        + (hofuri_entry!=null? hofuri_entry.hashCode(): 0) 
        + (agreed_non_pkg_div!=null? agreed_non_pkg_div.hashCode(): 0) 
        + (position_report_cycle_div!=null? position_report_cycle_div.hashCode(): 0) 
        + (position_report_div!=null? position_report_div.hashCode(): 0) 
        + (certificate_deposit_flag!=null? certificate_deposit_flag.hashCode(): 0) 
        + (account_statement_flag!=null? account_statement_flag.hashCode(): 0) 
        + (trading_report_div!=null? trading_report_div.hashCode(): 0) 
        + (inv_trast_ope_report!=null? inv_trast_ope_report.hashCode(): 0) 
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (margin_tax_type!=null? margin_tax_type.hashCode(): 0) 
        + (equity_sp_acc_open_date!=null? equity_sp_acc_open_date.hashCode(): 0) 
        + (margin_sp_acc_open_date!=null? margin_sp_acc_open_date.hashCode(): 0) 
        + (tax_type_last!=null? tax_type_last.hashCode(): 0) 
        + (margin_tax_type_last!=null? margin_tax_type_last.hashCode(): 0) 
        + (tax_type_next!=null? tax_type_next.hashCode(): 0) 
        + (margin_tax_type_next!=null? margin_tax_type_next.hashCode(): 0) 
        + (fluct_date!=null? fluct_date.hashCode(): 0) 
        + (margin_fluct_date!=null? margin_fluct_date.hashCode(): 0) 
        + (local_tax_div_last!=null? local_tax_div_last.hashCode(): 0) 
        + (local_tax_div!=null? local_tax_div.hashCode(): 0) 
        + (local_tax_div_next!=null? local_tax_div_next.hashCode(): 0) 
        + (qualified_inst_investor_div!=null? qualified_inst_investor_div.hashCode(): 0) 
        + (quoto_type!=null? quoto_type.hashCode(): 0) 
        + (sp_acc_abolish_date!=null? sp_acc_abolish_date.hashCode(): 0) 
        + (sp_mng_acc_open_div!=null? sp_mng_acc_open_div.hashCode(): 0) 
        + (reserve_area!=null? reserve_area.hashCode(): 0) 
        + (web3_encrypted_password!=null? web3_encrypted_password.hashCode(): 0) 
        + (xtrade_encrypted_password!=null? xtrade_encrypted_password.hashCode(): 0) 
        + (broadcast_law!=null? broadcast_law.hashCode(): 0) 
        + (aviation_law!=null? aviation_law.hashCode(): 0) 
        + (ntt_law!=null? ntt_law.hashCode(): 0) 
        + (dividend_trans_designate!=null? dividend_trans_designate.hashCode(): 0) 
        + (standing_proxy!=null? standing_proxy.hashCode(): 0) 
        + (statutory_agent!=null? statutory_agent.hashCode(): 0) 
        + (affiliate_account_code!=null? affiliate_account_code.hashCode(): 0) 
        + (agency_notify_cmp_div!=null? agency_notify_cmp_div.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !ruito_acc_open_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'ruito_acc_open_div' must be set before inserting.");
		if ( !margin_acc_open_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_acc_open_div' must be set before inserting.");
		if ( !sonar_trader_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'sonar_trader_code' must be set before inserting.");
		if ( !foreign_sec_acc_open_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'foreign_sec_acc_open_div' must be set before inserting.");
		if ( !ifo_acc_open_div_tokyo_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_acc_open_div_tokyo' must be set before inserting.");
		if ( !family_name_alt1_is_set )
			throw new IllegalArgumentException("non-nullable field 'family_name_alt1' must be set before inserting.");
		if ( !zip_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'zip_code' must be set before inserting.");
		if ( !address_line1_kana_is_set )
			throw new IllegalArgumentException("non-nullable field 'address_line1_kana' must be set before inserting.");
		if ( !family_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'family_name' must be set before inserting.");
		if ( !address_line1_is_set )
			throw new IllegalArgumentException("non-nullable field 'address_line1' must be set before inserting.");
		if ( !d_card_password_is_set )
			throw new IllegalArgumentException("non-nullable field 'd_card_password' must be set before inserting.");
		if ( !ifo_acc_open_div_osaka_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_acc_open_div_osaka' must be set before inserting.");
		if ( !ifo_acc_open_div_nagoya_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_acc_open_div_nagoya' must be set before inserting.");
		if ( !mrf_acc_open_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'mrf_acc_open_div' must be set before inserting.");
		if ( !gen_credit_acc_cont_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'gen_credit_acc_cont_div' must be set before inserting.");
		if ( !new_account_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'new_account_div' must be set before inserting.");
		if ( !via_trust_bank_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'via_trust_bank_div' must be set before inserting.");
		if ( !person_identify_is_set )
			throw new IllegalArgumentException("non-nullable field 'person_identify' must be set before inserting.");
		if ( !yellow_customer_is_set )
			throw new IllegalArgumentException("non-nullable field 'yellow_customer' must be set before inserting.");
		if ( !position_report_cycle_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'position_report_cycle_div' must be set before inserting.");
		if ( !position_report_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'position_report_div' must be set before inserting.");
		if ( !certificate_deposit_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'certificate_deposit_flag' must be set before inserting.");
		if ( !account_statement_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_statement_flag' must be set before inserting.");
		if ( !trading_report_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'trading_report_div' must be set before inserting.");
		if ( !tax_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'tax_type' must be set before inserting.");
		if ( !margin_tax_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_tax_type' must be set before inserting.");
		if ( !tax_type_next_is_set )
			throw new IllegalArgumentException("non-nullable field 'tax_type_next' must be set before inserting.");
		if ( !margin_tax_type_next_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_tax_type_next' must be set before inserting.");
		if ( !qualified_inst_investor_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'qualified_inst_investor_div' must be set before inserting.");
		if ( !quoto_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'quoto_type' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("comp_code",comp_code);
		if ( gen_acc_div != null )
			map.put("gen_acc_div",gen_acc_div);
		if ( gen_br_del_div != null )
			map.put("gen_br_del_div",gen_br_del_div);
		map.put("ruito_acc_open_div",ruito_acc_open_div);
		map.put("margin_acc_open_div",margin_acc_open_div);
		if ( when_issued_acc_open_div != null )
			map.put("when_issued_acc_open_div",when_issued_acc_open_div);
		if ( report_dispatch_stop_div != null )
			map.put("report_dispatch_stop_div",report_dispatch_stop_div);
		if ( doc_dispatch_div != null )
			map.put("doc_dispatch_div",doc_dispatch_div);
		if ( gp_br_del_div != null )
			map.put("gp_br_del_div",gp_br_del_div);
		if ( account_open_date != null )
			map.put("account_open_date",account_open_date);
		if ( last_update_date != null )
			map.put("last_update_date",last_update_date);
		map.put("br_code",br_code);
		map.put("cust_code",cust_code);
		map.put("cust_code_cd",cust_code_cd);
		map.put("sonar_trader_code",sonar_trader_code);
		if ( rep_dispatch_stp_bd != null )
			map.put("rep_dispatch_stp_bd",rep_dispatch_stp_bd);
		if ( occupation != null )
			map.put("occupation",occupation);
		if ( safe_cont_open_div != null )
			map.put("safe_cont_open_div",safe_cont_open_div);
		map.put("foreign_sec_acc_open_div",foreign_sec_acc_open_div);
		if ( tokuyu_acc_open_div != null )
			map.put("tokuyu_acc_open_div",tokuyu_acc_open_div);
		if ( gold_acc_open_div != null )
			map.put("gold_acc_open_div",gold_acc_open_div);
		if ( total_trade_open_div != null )
			map.put("total_trade_open_div",total_trade_open_div);
		if ( tie_up_loan_open_div != null )
			map.put("tie_up_loan_open_div",tie_up_loan_open_div);
		map.put("ifo_acc_open_div_tokyo",ifo_acc_open_div_tokyo);
		if ( address_unknown != null )
			map.put("address_unknown",address_unknown);
		if ( cust_div != null )
			map.put("cust_div",cust_div);
		if ( deposit_div != null )
			map.put("deposit_div",deposit_div);
		if ( all_substitution_div != null )
			map.put("all_substitution_div",all_substitution_div);
		if ( ins_loan_bill_mthd_div != null )
			map.put("ins_loan_bill_mthd_div",ins_loan_bill_mthd_div);
		if ( ins_loan_cer_mthd_div != null )
			map.put("ins_loan_cer_mthd_div",ins_loan_cer_mthd_div);
		if ( ins_loan_clause_mthd_div != null )
			map.put("ins_loan_clause_mthd_div",ins_loan_clause_mthd_div);
		if ( dom_tax_div != null )
			map.put("dom_tax_div",dom_tax_div);
		if ( client_trader_code != null )
			map.put("client_trader_code",client_trader_code);
		if ( telephone != null )
			map.put("telephone",telephone);
		map.put("family_name_alt1",family_name_alt1);
		map.put("zip_code",zip_code);
		if ( prefecture != null )
			map.put("prefecture",prefecture);
		if ( comma != null )
			map.put("comma",comma);
		map.put("address_line1_kana",address_line1_kana);
		if ( address_line2_kana != null )
			map.put("address_line2_kana",address_line2_kana);
		if ( address_line3_kana != null )
			map.put("address_line3_kana",address_line3_kana);
		map.put("family_name",family_name);
		map.put("address_line1",address_line1);
		if ( address_line2 != null )
			map.put("address_line2",address_line2);
		if ( address_line3 != null )
			map.put("address_line3",address_line3);
		if ( contact_address_telephone != null )
			map.put("contact_address_telephone",contact_address_telephone);
		if ( contact_address != null )
			map.put("contact_address",contact_address);
		if ( era_born != null )
			map.put("era_born",era_born);
		if ( born_date != null )
			map.put("born_date",born_date);
		if ( sex != null )
			map.put("sex",sex);
		if ( before_acc_trans_br_code != null )
			map.put("before_acc_trans_br_code",before_acc_trans_br_code);
		if ( before_acc_trans_cust_code != null )
			map.put("before_acc_trans_cust_code",before_acc_trans_cust_code);
		if ( d_card_appli_date != null )
			map.put("d_card_appli_date",d_card_appli_date);
		if ( d_card_issue_date != null )
			map.put("d_card_issue_date",d_card_issue_date);
		map.put("d_card_password",d_card_password);
		if ( d_card_issue_reason != null )
			map.put("d_card_issue_reason",d_card_issue_reason);
		if ( d_card_issue_number != null )
			map.put("d_card_issue_number",d_card_issue_number);
		if ( d_card_stop_date != null )
			map.put("d_card_stop_date",d_card_stop_date);
		if ( d_card_stop_reason != null )
			map.put("d_card_stop_reason",d_card_stop_reason);
		if ( d_card_cancel_date != null )
			map.put("d_card_cancel_date",d_card_cancel_date);
		if ( d_card_cancel_reason != null )
			map.put("d_card_cancel_reason",d_card_cancel_reason);
		if ( d_card_name != null )
			map.put("d_card_name",d_card_name);
		if ( pass_err_code_type != null )
			map.put("pass_err_code_type",pass_err_code_type);
		if ( pass_err_code_number != null )
			map.put("pass_err_code_number",pass_err_code_number);
		if ( pass_err_code_chg_date != null )
			map.put("pass_err_code_chg_date",pass_err_code_chg_date);
		if ( pass_err_code_chg_time != null )
			map.put("pass_err_code_chg_time",pass_err_code_chg_time);
		if ( ans_cust_div != null )
			map.put("ans_cust_div",ans_cust_div);
		if ( ans_stock_appli_cate != null )
			map.put("ans_stock_appli_cate",ans_stock_appli_cate);
		if ( dummy1 != null )
			map.put("dummy1",dummy1);
		if ( self_assessed_sep_tax != null )
			map.put("self_assessed_sep_tax",self_assessed_sep_tax);
		if ( tokuyu_appli_div != null )
			map.put("tokuyu_appli_div",tokuyu_appli_div);
		if ( maruyu_appli_div != null )
			map.put("maruyu_appli_div",maruyu_appli_div);
		if ( tokuyu_laundering_div != null )
			map.put("tokuyu_laundering_div",tokuyu_laundering_div);
		if ( maruyu_laundering_div != null )
			map.put("maruyu_laundering_div",maruyu_laundering_div);
		if ( tax_div != null )
			map.put("tax_div",tax_div);
		if ( dummy2 != null )
			map.put("dummy2",dummy2);
		if ( ht_settlement_way != null )
			map.put("ht_settlement_way",ht_settlement_way);
		if ( dummy3 != null )
			map.put("dummy3",dummy3);
		if ( total_tax_identity != null )
			map.put("total_tax_identity",total_tax_identity);
		if ( dummy4 != null )
			map.put("dummy4",dummy4);
		map.put("ifo_acc_open_div_osaka",ifo_acc_open_div_osaka);
		if ( mort_trade_open_div != null )
			map.put("mort_trade_open_div",mort_trade_open_div);
		map.put("ifo_acc_open_div_nagoya",ifo_acc_open_div_nagoya);
		if ( os_fnc_futures_open_div != null )
			map.put("os_fnc_futures_open_div",os_fnc_futures_open_div);
		if ( os_sec_futures_open_div != null )
			map.put("os_sec_futures_open_div",os_sec_futures_open_div);
		if ( tokyo_fnc_futures_open_div != null )
			map.put("tokyo_fnc_futures_open_div",tokyo_fnc_futures_open_div);
		if ( for_war_trade_open_div != null )
			map.put("for_war_trade_open_div",for_war_trade_open_div);
		if ( tentou_trade_open_div != null )
			map.put("tentou_trade_open_div",tentou_trade_open_div);
		if ( represent_div != null )
			map.put("represent_div",represent_div);
		if ( family_nurturer_code != null )
			map.put("family_nurturer_code",family_nurturer_code);
		if ( family_unit_adimin != null )
			map.put("family_unit_adimin",family_unit_adimin);
		if ( new_monthly_div != null )
			map.put("new_monthly_div",new_monthly_div);
		if ( statement_skip1_div != null )
			map.put("statement_skip1_div",statement_skip1_div);
		if ( statement_skip2_div != null )
			map.put("statement_skip2_div",statement_skip2_div);
		if ( family_unit_del_div != null )
			map.put("family_unit_del_div",family_unit_del_div);
		if ( dummy5 != null )
			map.put("dummy5",dummy5);
		if ( br_dispatch_div != null )
			map.put("br_dispatch_div",br_dispatch_div);
		if ( maruyu_grade_div != null )
			map.put("maruyu_grade_div",maruyu_grade_div);
		if ( dummy6 != null )
			map.put("dummy6",dummy6);
		if ( dummy7 != null )
			map.put("dummy7",dummy7);
		if ( trans_tax_div_acc_date != null )
			map.put("trans_tax_div_acc_date",trans_tax_div_acc_date);
		if ( trans_tax_div != null )
			map.put("trans_tax_div",trans_tax_div);
		if ( resident != null )
			map.put("resident",resident);
		if ( bond_d_and_c_open_div != null )
			map.put("bond_d_and_c_open_div",bond_d_and_c_open_div);
		if ( os_cd_cp_open_div != null )
			map.put("os_cd_cp_open_div",os_cd_cp_open_div);
		if ( new_tb_open_div != null )
			map.put("new_tb_open_div",new_tb_open_div);
		if ( dom_cp_open_div != null )
			map.put("dom_cp_open_div",dom_cp_open_div);
		if ( bond_tentou_op_open_div != null )
			map.put("bond_tentou_op_open_div",bond_tentou_op_open_div);
		if ( t_bond_futures_open_div != null )
			map.put("t_bond_futures_open_div",t_bond_futures_open_div);
		if ( dom_war_trade_open_div != null )
			map.put("dom_war_trade_open_div",dom_war_trade_open_div);
		if ( dummy8 != null )
			map.put("dummy8",dummy8);
		if ( dom_for_bond_open_div != null )
			map.put("dom_for_bond_open_div",dom_for_bond_open_div);
		if ( tentou_sp_rule_open_div != null )
			map.put("tentou_sp_rule_open_div",tentou_sp_rule_open_div);
		if ( dummy9 != null )
			map.put("dummy9",dummy9);
		if ( dummy10 != null )
			map.put("dummy10",dummy10);
		if ( dummy11 != null )
			map.put("dummy11",dummy11);
		if ( dummy12 != null )
			map.put("dummy12",dummy12);
		if ( dummy13 != null )
			map.put("dummy13",dummy13);
		map.put("mrf_acc_open_div",mrf_acc_open_div);
		if ( safe_cont_div != null )
			map.put("safe_cont_div",safe_cont_div);
		if ( foreign_cont_div != null )
			map.put("foreign_cont_div",foreign_cont_div);
		if ( dummy14 != null )
			map.put("dummy14",dummy14);
		if ( gold_cont_div != null )
			map.put("gold_cont_div",gold_cont_div);
		if ( margin_cont_div != null )
			map.put("margin_cont_div",margin_cont_div);
		if ( when_issued_cont_div != null )
			map.put("when_issued_cont_div",when_issued_cont_div);
		if ( futures_op_cont_div_tokyo != null )
			map.put("futures_op_cont_div_tokyo",futures_op_cont_div_tokyo);
		if ( dummy15 != null )
			map.put("dummy15",dummy15);
		if ( futures_op_cont_div_osaka != null )
			map.put("futures_op_cont_div_osaka",futures_op_cont_div_osaka);
		if ( tokyo_mothers_cont_div != null )
			map.put("tokyo_mothers_cont_div",tokyo_mothers_cont_div);
		if ( futures_op_cont_div_nagoya != null )
			map.put("futures_op_cont_div_nagoya",futures_op_cont_div_nagoya);
		if ( nq_j_gl_cont_div != null )
			map.put("nq_j_gl_cont_div",nq_j_gl_cont_div);
		if ( osaka_nw_mkt_cont_div != null )
			map.put("osaka_nw_mkt_cont_div",osaka_nw_mkt_cont_div);
		if ( nagoya_grw_cpy_mkt_cont_div != null )
			map.put("nagoya_grw_cpy_mkt_cont_div",nagoya_grw_cpy_mkt_cont_div);
		if ( for_war_trade_cont_div != null )
			map.put("for_war_trade_cont_div",for_war_trade_cont_div);
		if ( tentou_trade_cont_div != null )
			map.put("tentou_trade_cont_div",tentou_trade_cont_div);
		if ( bond_d_and_c_cont_div != null )
			map.put("bond_d_and_c_cont_div",bond_d_and_c_cont_div);
		if ( sapporo_amb_cont_div != null )
			map.put("sapporo_amb_cont_div",sapporo_amb_cont_div);
		if ( new_tb_cont_div != null )
			map.put("new_tb_cont_div",new_tb_cont_div);
		map.put("gen_credit_acc_cont_div",gen_credit_acc_cont_div);
		if ( bond_tentou_op_cont_div != null )
			map.put("bond_tentou_op_cont_div",bond_tentou_op_cont_div);
		if ( fukuoka_qb_cont_div != null )
			map.put("fukuoka_qb_cont_div",fukuoka_qb_cont_div);
		if ( dom_war_trade_cont_div != null )
			map.put("dom_war_trade_cont_div",dom_war_trade_cont_div);
		if ( tentou_sec_basis_cont_div != null )
			map.put("tentou_sec_basis_cont_div",tentou_sec_basis_cont_div);
		if ( dom_for_bond_cont_div != null )
			map.put("dom_for_bond_cont_div",dom_for_bond_cont_div);
		if ( tentou_sp_rule_cont_div != null )
			map.put("tentou_sp_rule_cont_div",tentou_sp_rule_cont_div);
		if ( dummy16 != null )
			map.put("dummy16",dummy16);
		if ( dummy17 != null )
			map.put("dummy17",dummy17);
		if ( dummy18 != null )
			map.put("dummy18",dummy18);
		if ( dummy19 != null )
			map.put("dummy19",dummy19);
		if ( dummy20 != null )
			map.put("dummy20",dummy20);
		if ( mrf_contract_div != null )
			map.put("mrf_contract_div",mrf_contract_div);
		map.put("new_account_div",new_account_div);
		map.put("via_trust_bank_div",via_trust_bank_div);
		if ( class_div != null )
			map.put("class_div",class_div);
		if ( address_code != null )
			map.put("address_code",address_code);
		map.put("person_identify",person_identify);
		if ( cancel_div != null )
			map.put("cancel_div",cancel_div);
		if ( add_chg_div != null )
			map.put("add_chg_div",add_chg_div);
		if ( reserve != null )
			map.put("reserve",reserve);
		if ( org_deposit_div != null )
			map.put("org_deposit_div",org_deposit_div);
		if ( eq_hold_rep_div != null )
			map.put("eq_hold_rep_div",eq_hold_rep_div);
		if ( chkup_rep_div != null )
			map.put("chkup_rep_div",chkup_rep_div);
		if ( fax != null )
			map.put("fax",fax);
		if ( hq_input_div != null )
			map.put("hq_input_div",hq_input_div);
		map.put("yellow_customer",yellow_customer);
		if ( dummy21 != null )
			map.put("dummy21",dummy21);
		if ( dummy22 != null )
			map.put("dummy22",dummy22);
		if ( bond_butt_div != null )
			map.put("bond_butt_div",bond_butt_div);
		if ( hofuri_entry != null )
			map.put("hofuri_entry",hofuri_entry);
		if ( agreed_non_pkg_div != null )
			map.put("agreed_non_pkg_div",agreed_non_pkg_div);
		map.put("position_report_cycle_div",position_report_cycle_div);
		map.put("position_report_div",position_report_div);
		map.put("certificate_deposit_flag",certificate_deposit_flag);
		map.put("account_statement_flag",account_statement_flag);
		map.put("trading_report_div",trading_report_div);
		if ( inv_trast_ope_report != null )
			map.put("inv_trast_ope_report",inv_trast_ope_report);
		map.put("tax_type",tax_type);
		map.put("margin_tax_type",margin_tax_type);
		if ( equity_sp_acc_open_date != null )
			map.put("equity_sp_acc_open_date",equity_sp_acc_open_date);
		if ( margin_sp_acc_open_date != null )
			map.put("margin_sp_acc_open_date",margin_sp_acc_open_date);
		if ( tax_type_last != null )
			map.put("tax_type_last",tax_type_last);
		if ( margin_tax_type_last != null )
			map.put("margin_tax_type_last",margin_tax_type_last);
		map.put("tax_type_next",tax_type_next);
		map.put("margin_tax_type_next",margin_tax_type_next);
		if ( fluct_date != null )
			map.put("fluct_date",fluct_date);
		if ( margin_fluct_date != null )
			map.put("margin_fluct_date",margin_fluct_date);
		if ( local_tax_div_last != null )
			map.put("local_tax_div_last",local_tax_div_last);
		if ( local_tax_div != null )
			map.put("local_tax_div",local_tax_div);
		if ( local_tax_div_next != null )
			map.put("local_tax_div_next",local_tax_div_next);
		map.put("qualified_inst_investor_div",qualified_inst_investor_div);
		map.put("quoto_type",quoto_type);
		if ( sp_acc_abolish_date != null )
			map.put("sp_acc_abolish_date",sp_acc_abolish_date);
		if ( sp_mng_acc_open_div != null )
			map.put("sp_mng_acc_open_div",sp_mng_acc_open_div);
		if ( reserve_area != null )
			map.put("reserve_area",reserve_area);
		if ( web3_encrypted_password != null )
			map.put("web3_encrypted_password",web3_encrypted_password);
		if ( xtrade_encrypted_password != null )
			map.put("xtrade_encrypted_password",xtrade_encrypted_password);
		if ( broadcast_law != null )
			map.put("broadcast_law",broadcast_law);
		if ( aviation_law != null )
			map.put("aviation_law",aviation_law);
		if ( ntt_law != null )
			map.put("ntt_law",ntt_law);
		if ( dividend_trans_designate != null )
			map.put("dividend_trans_designate",dividend_trans_designate);
		if ( standing_proxy != null )
			map.put("standing_proxy",standing_proxy);
		if ( statutory_agent != null )
			map.put("statutory_agent",statutory_agent);
		if ( affiliate_account_code != null )
			map.put("affiliate_account_code",affiliate_account_code);
		if ( agency_notify_cmp_div != null )
			map.put("agency_notify_cmp_div",agency_notify_cmp_div);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( gen_acc_div_is_modified )
			map.put("gen_acc_div",gen_acc_div);
		if ( gen_br_del_div_is_modified )
			map.put("gen_br_del_div",gen_br_del_div);
		if ( ruito_acc_open_div_is_modified )
			map.put("ruito_acc_open_div",ruito_acc_open_div);
		if ( margin_acc_open_div_is_modified )
			map.put("margin_acc_open_div",margin_acc_open_div);
		if ( when_issued_acc_open_div_is_modified )
			map.put("when_issued_acc_open_div",when_issued_acc_open_div);
		if ( report_dispatch_stop_div_is_modified )
			map.put("report_dispatch_stop_div",report_dispatch_stop_div);
		if ( doc_dispatch_div_is_modified )
			map.put("doc_dispatch_div",doc_dispatch_div);
		if ( gp_br_del_div_is_modified )
			map.put("gp_br_del_div",gp_br_del_div);
		if ( account_open_date_is_modified )
			map.put("account_open_date",account_open_date);
		if ( last_update_date_is_modified )
			map.put("last_update_date",last_update_date);
		if ( sonar_trader_code_is_modified )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( rep_dispatch_stp_bd_is_modified )
			map.put("rep_dispatch_stp_bd",rep_dispatch_stp_bd);
		if ( occupation_is_modified )
			map.put("occupation",occupation);
		if ( safe_cont_open_div_is_modified )
			map.put("safe_cont_open_div",safe_cont_open_div);
		if ( foreign_sec_acc_open_div_is_modified )
			map.put("foreign_sec_acc_open_div",foreign_sec_acc_open_div);
		if ( tokuyu_acc_open_div_is_modified )
			map.put("tokuyu_acc_open_div",tokuyu_acc_open_div);
		if ( gold_acc_open_div_is_modified )
			map.put("gold_acc_open_div",gold_acc_open_div);
		if ( total_trade_open_div_is_modified )
			map.put("total_trade_open_div",total_trade_open_div);
		if ( tie_up_loan_open_div_is_modified )
			map.put("tie_up_loan_open_div",tie_up_loan_open_div);
		if ( ifo_acc_open_div_tokyo_is_modified )
			map.put("ifo_acc_open_div_tokyo",ifo_acc_open_div_tokyo);
		if ( address_unknown_is_modified )
			map.put("address_unknown",address_unknown);
		if ( cust_div_is_modified )
			map.put("cust_div",cust_div);
		if ( deposit_div_is_modified )
			map.put("deposit_div",deposit_div);
		if ( all_substitution_div_is_modified )
			map.put("all_substitution_div",all_substitution_div);
		if ( ins_loan_bill_mthd_div_is_modified )
			map.put("ins_loan_bill_mthd_div",ins_loan_bill_mthd_div);
		if ( ins_loan_cer_mthd_div_is_modified )
			map.put("ins_loan_cer_mthd_div",ins_loan_cer_mthd_div);
		if ( ins_loan_clause_mthd_div_is_modified )
			map.put("ins_loan_clause_mthd_div",ins_loan_clause_mthd_div);
		if ( dom_tax_div_is_modified )
			map.put("dom_tax_div",dom_tax_div);
		if ( client_trader_code_is_modified )
			map.put("client_trader_code",client_trader_code);
		if ( telephone_is_modified )
			map.put("telephone",telephone);
		if ( family_name_alt1_is_modified )
			map.put("family_name_alt1",family_name_alt1);
		if ( zip_code_is_modified )
			map.put("zip_code",zip_code);
		if ( prefecture_is_modified )
			map.put("prefecture",prefecture);
		if ( comma_is_modified )
			map.put("comma",comma);
		if ( address_line1_kana_is_modified )
			map.put("address_line1_kana",address_line1_kana);
		if ( address_line2_kana_is_modified )
			map.put("address_line2_kana",address_line2_kana);
		if ( address_line3_kana_is_modified )
			map.put("address_line3_kana",address_line3_kana);
		if ( family_name_is_modified )
			map.put("family_name",family_name);
		if ( address_line1_is_modified )
			map.put("address_line1",address_line1);
		if ( address_line2_is_modified )
			map.put("address_line2",address_line2);
		if ( address_line3_is_modified )
			map.put("address_line3",address_line3);
		if ( contact_address_telephone_is_modified )
			map.put("contact_address_telephone",contact_address_telephone);
		if ( contact_address_is_modified )
			map.put("contact_address",contact_address);
		if ( era_born_is_modified )
			map.put("era_born",era_born);
		if ( born_date_is_modified )
			map.put("born_date",born_date);
		if ( sex_is_modified )
			map.put("sex",sex);
		if ( before_acc_trans_br_code_is_modified )
			map.put("before_acc_trans_br_code",before_acc_trans_br_code);
		if ( before_acc_trans_cust_code_is_modified )
			map.put("before_acc_trans_cust_code",before_acc_trans_cust_code);
		if ( d_card_appli_date_is_modified )
			map.put("d_card_appli_date",d_card_appli_date);
		if ( d_card_issue_date_is_modified )
			map.put("d_card_issue_date",d_card_issue_date);
		if ( d_card_password_is_modified )
			map.put("d_card_password",d_card_password);
		if ( d_card_issue_reason_is_modified )
			map.put("d_card_issue_reason",d_card_issue_reason);
		if ( d_card_issue_number_is_modified )
			map.put("d_card_issue_number",d_card_issue_number);
		if ( d_card_stop_date_is_modified )
			map.put("d_card_stop_date",d_card_stop_date);
		if ( d_card_stop_reason_is_modified )
			map.put("d_card_stop_reason",d_card_stop_reason);
		if ( d_card_cancel_date_is_modified )
			map.put("d_card_cancel_date",d_card_cancel_date);
		if ( d_card_cancel_reason_is_modified )
			map.put("d_card_cancel_reason",d_card_cancel_reason);
		if ( d_card_name_is_modified )
			map.put("d_card_name",d_card_name);
		if ( pass_err_code_type_is_modified )
			map.put("pass_err_code_type",pass_err_code_type);
		if ( pass_err_code_number_is_modified )
			map.put("pass_err_code_number",pass_err_code_number);
		if ( pass_err_code_chg_date_is_modified )
			map.put("pass_err_code_chg_date",pass_err_code_chg_date);
		if ( pass_err_code_chg_time_is_modified )
			map.put("pass_err_code_chg_time",pass_err_code_chg_time);
		if ( ans_cust_div_is_modified )
			map.put("ans_cust_div",ans_cust_div);
		if ( ans_stock_appli_cate_is_modified )
			map.put("ans_stock_appli_cate",ans_stock_appli_cate);
		if ( dummy1_is_modified )
			map.put("dummy1",dummy1);
		if ( self_assessed_sep_tax_is_modified )
			map.put("self_assessed_sep_tax",self_assessed_sep_tax);
		if ( tokuyu_appli_div_is_modified )
			map.put("tokuyu_appli_div",tokuyu_appli_div);
		if ( maruyu_appli_div_is_modified )
			map.put("maruyu_appli_div",maruyu_appli_div);
		if ( tokuyu_laundering_div_is_modified )
			map.put("tokuyu_laundering_div",tokuyu_laundering_div);
		if ( maruyu_laundering_div_is_modified )
			map.put("maruyu_laundering_div",maruyu_laundering_div);
		if ( tax_div_is_modified )
			map.put("tax_div",tax_div);
		if ( dummy2_is_modified )
			map.put("dummy2",dummy2);
		if ( ht_settlement_way_is_modified )
			map.put("ht_settlement_way",ht_settlement_way);
		if ( dummy3_is_modified )
			map.put("dummy3",dummy3);
		if ( total_tax_identity_is_modified )
			map.put("total_tax_identity",total_tax_identity);
		if ( dummy4_is_modified )
			map.put("dummy4",dummy4);
		if ( ifo_acc_open_div_osaka_is_modified )
			map.put("ifo_acc_open_div_osaka",ifo_acc_open_div_osaka);
		if ( mort_trade_open_div_is_modified )
			map.put("mort_trade_open_div",mort_trade_open_div);
		if ( ifo_acc_open_div_nagoya_is_modified )
			map.put("ifo_acc_open_div_nagoya",ifo_acc_open_div_nagoya);
		if ( os_fnc_futures_open_div_is_modified )
			map.put("os_fnc_futures_open_div",os_fnc_futures_open_div);
		if ( os_sec_futures_open_div_is_modified )
			map.put("os_sec_futures_open_div",os_sec_futures_open_div);
		if ( tokyo_fnc_futures_open_div_is_modified )
			map.put("tokyo_fnc_futures_open_div",tokyo_fnc_futures_open_div);
		if ( for_war_trade_open_div_is_modified )
			map.put("for_war_trade_open_div",for_war_trade_open_div);
		if ( tentou_trade_open_div_is_modified )
			map.put("tentou_trade_open_div",tentou_trade_open_div);
		if ( represent_div_is_modified )
			map.put("represent_div",represent_div);
		if ( family_nurturer_code_is_modified )
			map.put("family_nurturer_code",family_nurturer_code);
		if ( family_unit_adimin_is_modified )
			map.put("family_unit_adimin",family_unit_adimin);
		if ( new_monthly_div_is_modified )
			map.put("new_monthly_div",new_monthly_div);
		if ( statement_skip1_div_is_modified )
			map.put("statement_skip1_div",statement_skip1_div);
		if ( statement_skip2_div_is_modified )
			map.put("statement_skip2_div",statement_skip2_div);
		if ( family_unit_del_div_is_modified )
			map.put("family_unit_del_div",family_unit_del_div);
		if ( dummy5_is_modified )
			map.put("dummy5",dummy5);
		if ( br_dispatch_div_is_modified )
			map.put("br_dispatch_div",br_dispatch_div);
		if ( maruyu_grade_div_is_modified )
			map.put("maruyu_grade_div",maruyu_grade_div);
		if ( dummy6_is_modified )
			map.put("dummy6",dummy6);
		if ( dummy7_is_modified )
			map.put("dummy7",dummy7);
		if ( trans_tax_div_acc_date_is_modified )
			map.put("trans_tax_div_acc_date",trans_tax_div_acc_date);
		if ( trans_tax_div_is_modified )
			map.put("trans_tax_div",trans_tax_div);
		if ( resident_is_modified )
			map.put("resident",resident);
		if ( bond_d_and_c_open_div_is_modified )
			map.put("bond_d_and_c_open_div",bond_d_and_c_open_div);
		if ( os_cd_cp_open_div_is_modified )
			map.put("os_cd_cp_open_div",os_cd_cp_open_div);
		if ( new_tb_open_div_is_modified )
			map.put("new_tb_open_div",new_tb_open_div);
		if ( dom_cp_open_div_is_modified )
			map.put("dom_cp_open_div",dom_cp_open_div);
		if ( bond_tentou_op_open_div_is_modified )
			map.put("bond_tentou_op_open_div",bond_tentou_op_open_div);
		if ( t_bond_futures_open_div_is_modified )
			map.put("t_bond_futures_open_div",t_bond_futures_open_div);
		if ( dom_war_trade_open_div_is_modified )
			map.put("dom_war_trade_open_div",dom_war_trade_open_div);
		if ( dummy8_is_modified )
			map.put("dummy8",dummy8);
		if ( dom_for_bond_open_div_is_modified )
			map.put("dom_for_bond_open_div",dom_for_bond_open_div);
		if ( tentou_sp_rule_open_div_is_modified )
			map.put("tentou_sp_rule_open_div",tentou_sp_rule_open_div);
		if ( dummy9_is_modified )
			map.put("dummy9",dummy9);
		if ( dummy10_is_modified )
			map.put("dummy10",dummy10);
		if ( dummy11_is_modified )
			map.put("dummy11",dummy11);
		if ( dummy12_is_modified )
			map.put("dummy12",dummy12);
		if ( dummy13_is_modified )
			map.put("dummy13",dummy13);
		if ( mrf_acc_open_div_is_modified )
			map.put("mrf_acc_open_div",mrf_acc_open_div);
		if ( safe_cont_div_is_modified )
			map.put("safe_cont_div",safe_cont_div);
		if ( foreign_cont_div_is_modified )
			map.put("foreign_cont_div",foreign_cont_div);
		if ( dummy14_is_modified )
			map.put("dummy14",dummy14);
		if ( gold_cont_div_is_modified )
			map.put("gold_cont_div",gold_cont_div);
		if ( margin_cont_div_is_modified )
			map.put("margin_cont_div",margin_cont_div);
		if ( when_issued_cont_div_is_modified )
			map.put("when_issued_cont_div",when_issued_cont_div);
		if ( futures_op_cont_div_tokyo_is_modified )
			map.put("futures_op_cont_div_tokyo",futures_op_cont_div_tokyo);
		if ( dummy15_is_modified )
			map.put("dummy15",dummy15);
		if ( futures_op_cont_div_osaka_is_modified )
			map.put("futures_op_cont_div_osaka",futures_op_cont_div_osaka);
		if ( tokyo_mothers_cont_div_is_modified )
			map.put("tokyo_mothers_cont_div",tokyo_mothers_cont_div);
		if ( futures_op_cont_div_nagoya_is_modified )
			map.put("futures_op_cont_div_nagoya",futures_op_cont_div_nagoya);
		if ( nq_j_gl_cont_div_is_modified )
			map.put("nq_j_gl_cont_div",nq_j_gl_cont_div);
		if ( osaka_nw_mkt_cont_div_is_modified )
			map.put("osaka_nw_mkt_cont_div",osaka_nw_mkt_cont_div);
		if ( nagoya_grw_cpy_mkt_cont_div_is_modified )
			map.put("nagoya_grw_cpy_mkt_cont_div",nagoya_grw_cpy_mkt_cont_div);
		if ( for_war_trade_cont_div_is_modified )
			map.put("for_war_trade_cont_div",for_war_trade_cont_div);
		if ( tentou_trade_cont_div_is_modified )
			map.put("tentou_trade_cont_div",tentou_trade_cont_div);
		if ( bond_d_and_c_cont_div_is_modified )
			map.put("bond_d_and_c_cont_div",bond_d_and_c_cont_div);
		if ( sapporo_amb_cont_div_is_modified )
			map.put("sapporo_amb_cont_div",sapporo_amb_cont_div);
		if ( new_tb_cont_div_is_modified )
			map.put("new_tb_cont_div",new_tb_cont_div);
		if ( gen_credit_acc_cont_div_is_modified )
			map.put("gen_credit_acc_cont_div",gen_credit_acc_cont_div);
		if ( bond_tentou_op_cont_div_is_modified )
			map.put("bond_tentou_op_cont_div",bond_tentou_op_cont_div);
		if ( fukuoka_qb_cont_div_is_modified )
			map.put("fukuoka_qb_cont_div",fukuoka_qb_cont_div);
		if ( dom_war_trade_cont_div_is_modified )
			map.put("dom_war_trade_cont_div",dom_war_trade_cont_div);
		if ( tentou_sec_basis_cont_div_is_modified )
			map.put("tentou_sec_basis_cont_div",tentou_sec_basis_cont_div);
		if ( dom_for_bond_cont_div_is_modified )
			map.put("dom_for_bond_cont_div",dom_for_bond_cont_div);
		if ( tentou_sp_rule_cont_div_is_modified )
			map.put("tentou_sp_rule_cont_div",tentou_sp_rule_cont_div);
		if ( dummy16_is_modified )
			map.put("dummy16",dummy16);
		if ( dummy17_is_modified )
			map.put("dummy17",dummy17);
		if ( dummy18_is_modified )
			map.put("dummy18",dummy18);
		if ( dummy19_is_modified )
			map.put("dummy19",dummy19);
		if ( dummy20_is_modified )
			map.put("dummy20",dummy20);
		if ( mrf_contract_div_is_modified )
			map.put("mrf_contract_div",mrf_contract_div);
		if ( new_account_div_is_modified )
			map.put("new_account_div",new_account_div);
		if ( via_trust_bank_div_is_modified )
			map.put("via_trust_bank_div",via_trust_bank_div);
		if ( class_div_is_modified )
			map.put("class_div",class_div);
		if ( address_code_is_modified )
			map.put("address_code",address_code);
		if ( person_identify_is_modified )
			map.put("person_identify",person_identify);
		if ( cancel_div_is_modified )
			map.put("cancel_div",cancel_div);
		if ( add_chg_div_is_modified )
			map.put("add_chg_div",add_chg_div);
		if ( reserve_is_modified )
			map.put("reserve",reserve);
		if ( org_deposit_div_is_modified )
			map.put("org_deposit_div",org_deposit_div);
		if ( eq_hold_rep_div_is_modified )
			map.put("eq_hold_rep_div",eq_hold_rep_div);
		if ( chkup_rep_div_is_modified )
			map.put("chkup_rep_div",chkup_rep_div);
		if ( fax_is_modified )
			map.put("fax",fax);
		if ( hq_input_div_is_modified )
			map.put("hq_input_div",hq_input_div);
		if ( yellow_customer_is_modified )
			map.put("yellow_customer",yellow_customer);
		if ( dummy21_is_modified )
			map.put("dummy21",dummy21);
		if ( dummy22_is_modified )
			map.put("dummy22",dummy22);
		if ( bond_butt_div_is_modified )
			map.put("bond_butt_div",bond_butt_div);
		if ( hofuri_entry_is_modified )
			map.put("hofuri_entry",hofuri_entry);
		if ( agreed_non_pkg_div_is_modified )
			map.put("agreed_non_pkg_div",agreed_non_pkg_div);
		if ( position_report_cycle_div_is_modified )
			map.put("position_report_cycle_div",position_report_cycle_div);
		if ( position_report_div_is_modified )
			map.put("position_report_div",position_report_div);
		if ( certificate_deposit_flag_is_modified )
			map.put("certificate_deposit_flag",certificate_deposit_flag);
		if ( account_statement_flag_is_modified )
			map.put("account_statement_flag",account_statement_flag);
		if ( trading_report_div_is_modified )
			map.put("trading_report_div",trading_report_div);
		if ( inv_trast_ope_report_is_modified )
			map.put("inv_trast_ope_report",inv_trast_ope_report);
		if ( tax_type_is_modified )
			map.put("tax_type",tax_type);
		if ( margin_tax_type_is_modified )
			map.put("margin_tax_type",margin_tax_type);
		if ( equity_sp_acc_open_date_is_modified )
			map.put("equity_sp_acc_open_date",equity_sp_acc_open_date);
		if ( margin_sp_acc_open_date_is_modified )
			map.put("margin_sp_acc_open_date",margin_sp_acc_open_date);
		if ( tax_type_last_is_modified )
			map.put("tax_type_last",tax_type_last);
		if ( margin_tax_type_last_is_modified )
			map.put("margin_tax_type_last",margin_tax_type_last);
		if ( tax_type_next_is_modified )
			map.put("tax_type_next",tax_type_next);
		if ( margin_tax_type_next_is_modified )
			map.put("margin_tax_type_next",margin_tax_type_next);
		if ( fluct_date_is_modified )
			map.put("fluct_date",fluct_date);
		if ( margin_fluct_date_is_modified )
			map.put("margin_fluct_date",margin_fluct_date);
		if ( local_tax_div_last_is_modified )
			map.put("local_tax_div_last",local_tax_div_last);
		if ( local_tax_div_is_modified )
			map.put("local_tax_div",local_tax_div);
		if ( local_tax_div_next_is_modified )
			map.put("local_tax_div_next",local_tax_div_next);
		if ( qualified_inst_investor_div_is_modified )
			map.put("qualified_inst_investor_div",qualified_inst_investor_div);
		if ( quoto_type_is_modified )
			map.put("quoto_type",quoto_type);
		if ( sp_acc_abolish_date_is_modified )
			map.put("sp_acc_abolish_date",sp_acc_abolish_date);
		if ( sp_mng_acc_open_div_is_modified )
			map.put("sp_mng_acc_open_div",sp_mng_acc_open_div);
		if ( reserve_area_is_modified )
			map.put("reserve_area",reserve_area);
		if ( web3_encrypted_password_is_modified )
			map.put("web3_encrypted_password",web3_encrypted_password);
		if ( xtrade_encrypted_password_is_modified )
			map.put("xtrade_encrypted_password",xtrade_encrypted_password);
		if ( broadcast_law_is_modified )
			map.put("broadcast_law",broadcast_law);
		if ( aviation_law_is_modified )
			map.put("aviation_law",aviation_law);
		if ( ntt_law_is_modified )
			map.put("ntt_law",ntt_law);
		if ( dividend_trans_designate_is_modified )
			map.put("dividend_trans_designate",dividend_trans_designate);
		if ( standing_proxy_is_modified )
			map.put("standing_proxy",standing_proxy);
		if ( statutory_agent_is_modified )
			map.put("statutory_agent",statutory_agent);
		if ( affiliate_account_code_is_modified )
			map.put("affiliate_account_code",affiliate_account_code);
		if ( agency_notify_cmp_div_is_modified )
			map.put("agency_notify_cmp_div",agency_notify_cmp_div);
		if (map.size() == 0) {
			map.put("gen_acc_div",gen_acc_div);
			map.put("gen_br_del_div",gen_br_del_div);
			if ( ruito_acc_open_div_is_set )
				map.put("ruito_acc_open_div",ruito_acc_open_div);
			if ( margin_acc_open_div_is_set )
				map.put("margin_acc_open_div",margin_acc_open_div);
			map.put("when_issued_acc_open_div",when_issued_acc_open_div);
			map.put("report_dispatch_stop_div",report_dispatch_stop_div);
			map.put("doc_dispatch_div",doc_dispatch_div);
			map.put("gp_br_del_div",gp_br_del_div);
			map.put("account_open_date",account_open_date);
			map.put("last_update_date",last_update_date);
			if ( sonar_trader_code_is_set )
				map.put("sonar_trader_code",sonar_trader_code);
			map.put("rep_dispatch_stp_bd",rep_dispatch_stp_bd);
			map.put("occupation",occupation);
			map.put("safe_cont_open_div",safe_cont_open_div);
			if ( foreign_sec_acc_open_div_is_set )
				map.put("foreign_sec_acc_open_div",foreign_sec_acc_open_div);
			map.put("tokuyu_acc_open_div",tokuyu_acc_open_div);
			map.put("gold_acc_open_div",gold_acc_open_div);
			map.put("total_trade_open_div",total_trade_open_div);
			map.put("tie_up_loan_open_div",tie_up_loan_open_div);
			if ( ifo_acc_open_div_tokyo_is_set )
				map.put("ifo_acc_open_div_tokyo",ifo_acc_open_div_tokyo);
			map.put("address_unknown",address_unknown);
			map.put("cust_div",cust_div);
			map.put("deposit_div",deposit_div);
			map.put("all_substitution_div",all_substitution_div);
			map.put("ins_loan_bill_mthd_div",ins_loan_bill_mthd_div);
			map.put("ins_loan_cer_mthd_div",ins_loan_cer_mthd_div);
			map.put("ins_loan_clause_mthd_div",ins_loan_clause_mthd_div);
			map.put("dom_tax_div",dom_tax_div);
			map.put("client_trader_code",client_trader_code);
			map.put("telephone",telephone);
			if ( family_name_alt1_is_set )
				map.put("family_name_alt1",family_name_alt1);
			if ( zip_code_is_set )
				map.put("zip_code",zip_code);
			map.put("prefecture",prefecture);
			map.put("comma",comma);
			if ( address_line1_kana_is_set )
				map.put("address_line1_kana",address_line1_kana);
			map.put("address_line2_kana",address_line2_kana);
			map.put("address_line3_kana",address_line3_kana);
			if ( family_name_is_set )
				map.put("family_name",family_name);
			if ( address_line1_is_set )
				map.put("address_line1",address_line1);
			map.put("address_line2",address_line2);
			map.put("address_line3",address_line3);
			map.put("contact_address_telephone",contact_address_telephone);
			map.put("contact_address",contact_address);
			map.put("era_born",era_born);
			map.put("born_date",born_date);
			map.put("sex",sex);
			map.put("before_acc_trans_br_code",before_acc_trans_br_code);
			map.put("before_acc_trans_cust_code",before_acc_trans_cust_code);
			map.put("d_card_appli_date",d_card_appli_date);
			map.put("d_card_issue_date",d_card_issue_date);
			if ( d_card_password_is_set )
				map.put("d_card_password",d_card_password);
			map.put("d_card_issue_reason",d_card_issue_reason);
			map.put("d_card_issue_number",d_card_issue_number);
			map.put("d_card_stop_date",d_card_stop_date);
			map.put("d_card_stop_reason",d_card_stop_reason);
			map.put("d_card_cancel_date",d_card_cancel_date);
			map.put("d_card_cancel_reason",d_card_cancel_reason);
			map.put("d_card_name",d_card_name);
			map.put("pass_err_code_type",pass_err_code_type);
			map.put("pass_err_code_number",pass_err_code_number);
			map.put("pass_err_code_chg_date",pass_err_code_chg_date);
			map.put("pass_err_code_chg_time",pass_err_code_chg_time);
			map.put("ans_cust_div",ans_cust_div);
			map.put("ans_stock_appli_cate",ans_stock_appli_cate);
			map.put("dummy1",dummy1);
			map.put("self_assessed_sep_tax",self_assessed_sep_tax);
			map.put("tokuyu_appli_div",tokuyu_appli_div);
			map.put("maruyu_appli_div",maruyu_appli_div);
			map.put("tokuyu_laundering_div",tokuyu_laundering_div);
			map.put("maruyu_laundering_div",maruyu_laundering_div);
			map.put("tax_div",tax_div);
			map.put("dummy2",dummy2);
			map.put("ht_settlement_way",ht_settlement_way);
			map.put("dummy3",dummy3);
			map.put("total_tax_identity",total_tax_identity);
			map.put("dummy4",dummy4);
			if ( ifo_acc_open_div_osaka_is_set )
				map.put("ifo_acc_open_div_osaka",ifo_acc_open_div_osaka);
			map.put("mort_trade_open_div",mort_trade_open_div);
			if ( ifo_acc_open_div_nagoya_is_set )
				map.put("ifo_acc_open_div_nagoya",ifo_acc_open_div_nagoya);
			map.put("os_fnc_futures_open_div",os_fnc_futures_open_div);
			map.put("os_sec_futures_open_div",os_sec_futures_open_div);
			map.put("tokyo_fnc_futures_open_div",tokyo_fnc_futures_open_div);
			map.put("for_war_trade_open_div",for_war_trade_open_div);
			map.put("tentou_trade_open_div",tentou_trade_open_div);
			map.put("represent_div",represent_div);
			map.put("family_nurturer_code",family_nurturer_code);
			map.put("family_unit_adimin",family_unit_adimin);
			map.put("new_monthly_div",new_monthly_div);
			map.put("statement_skip1_div",statement_skip1_div);
			map.put("statement_skip2_div",statement_skip2_div);
			map.put("family_unit_del_div",family_unit_del_div);
			map.put("dummy5",dummy5);
			map.put("br_dispatch_div",br_dispatch_div);
			map.put("maruyu_grade_div",maruyu_grade_div);
			map.put("dummy6",dummy6);
			map.put("dummy7",dummy7);
			map.put("trans_tax_div_acc_date",trans_tax_div_acc_date);
			map.put("trans_tax_div",trans_tax_div);
			map.put("resident",resident);
			map.put("bond_d_and_c_open_div",bond_d_and_c_open_div);
			map.put("os_cd_cp_open_div",os_cd_cp_open_div);
			map.put("new_tb_open_div",new_tb_open_div);
			map.put("dom_cp_open_div",dom_cp_open_div);
			map.put("bond_tentou_op_open_div",bond_tentou_op_open_div);
			map.put("t_bond_futures_open_div",t_bond_futures_open_div);
			map.put("dom_war_trade_open_div",dom_war_trade_open_div);
			map.put("dummy8",dummy8);
			map.put("dom_for_bond_open_div",dom_for_bond_open_div);
			map.put("tentou_sp_rule_open_div",tentou_sp_rule_open_div);
			map.put("dummy9",dummy9);
			map.put("dummy10",dummy10);
			map.put("dummy11",dummy11);
			map.put("dummy12",dummy12);
			map.put("dummy13",dummy13);
			if ( mrf_acc_open_div_is_set )
				map.put("mrf_acc_open_div",mrf_acc_open_div);
			map.put("safe_cont_div",safe_cont_div);
			map.put("foreign_cont_div",foreign_cont_div);
			map.put("dummy14",dummy14);
			map.put("gold_cont_div",gold_cont_div);
			map.put("margin_cont_div",margin_cont_div);
			map.put("when_issued_cont_div",when_issued_cont_div);
			map.put("futures_op_cont_div_tokyo",futures_op_cont_div_tokyo);
			map.put("dummy15",dummy15);
			map.put("futures_op_cont_div_osaka",futures_op_cont_div_osaka);
			map.put("tokyo_mothers_cont_div",tokyo_mothers_cont_div);
			map.put("futures_op_cont_div_nagoya",futures_op_cont_div_nagoya);
			map.put("nq_j_gl_cont_div",nq_j_gl_cont_div);
			map.put("osaka_nw_mkt_cont_div",osaka_nw_mkt_cont_div);
			map.put("nagoya_grw_cpy_mkt_cont_div",nagoya_grw_cpy_mkt_cont_div);
			map.put("for_war_trade_cont_div",for_war_trade_cont_div);
			map.put("tentou_trade_cont_div",tentou_trade_cont_div);
			map.put("bond_d_and_c_cont_div",bond_d_and_c_cont_div);
			map.put("sapporo_amb_cont_div",sapporo_amb_cont_div);
			map.put("new_tb_cont_div",new_tb_cont_div);
			if ( gen_credit_acc_cont_div_is_set )
				map.put("gen_credit_acc_cont_div",gen_credit_acc_cont_div);
			map.put("bond_tentou_op_cont_div",bond_tentou_op_cont_div);
			map.put("fukuoka_qb_cont_div",fukuoka_qb_cont_div);
			map.put("dom_war_trade_cont_div",dom_war_trade_cont_div);
			map.put("tentou_sec_basis_cont_div",tentou_sec_basis_cont_div);
			map.put("dom_for_bond_cont_div",dom_for_bond_cont_div);
			map.put("tentou_sp_rule_cont_div",tentou_sp_rule_cont_div);
			map.put("dummy16",dummy16);
			map.put("dummy17",dummy17);
			map.put("dummy18",dummy18);
			map.put("dummy19",dummy19);
			map.put("dummy20",dummy20);
			map.put("mrf_contract_div",mrf_contract_div);
			if ( new_account_div_is_set )
				map.put("new_account_div",new_account_div);
			if ( via_trust_bank_div_is_set )
				map.put("via_trust_bank_div",via_trust_bank_div);
			map.put("class_div",class_div);
			map.put("address_code",address_code);
			if ( person_identify_is_set )
				map.put("person_identify",person_identify);
			map.put("cancel_div",cancel_div);
			map.put("add_chg_div",add_chg_div);
			map.put("reserve",reserve);
			map.put("org_deposit_div",org_deposit_div);
			map.put("eq_hold_rep_div",eq_hold_rep_div);
			map.put("chkup_rep_div",chkup_rep_div);
			map.put("fax",fax);
			map.put("hq_input_div",hq_input_div);
			if ( yellow_customer_is_set )
				map.put("yellow_customer",yellow_customer);
			map.put("dummy21",dummy21);
			map.put("dummy22",dummy22);
			map.put("bond_butt_div",bond_butt_div);
			map.put("hofuri_entry",hofuri_entry);
			map.put("agreed_non_pkg_div",agreed_non_pkg_div);
			if ( position_report_cycle_div_is_set )
				map.put("position_report_cycle_div",position_report_cycle_div);
			if ( position_report_div_is_set )
				map.put("position_report_div",position_report_div);
			if ( certificate_deposit_flag_is_set )
				map.put("certificate_deposit_flag",certificate_deposit_flag);
			if ( account_statement_flag_is_set )
				map.put("account_statement_flag",account_statement_flag);
			if ( trading_report_div_is_set )
				map.put("trading_report_div",trading_report_div);
			map.put("inv_trast_ope_report",inv_trast_ope_report);
			if ( tax_type_is_set )
				map.put("tax_type",tax_type);
			if ( margin_tax_type_is_set )
				map.put("margin_tax_type",margin_tax_type);
			map.put("equity_sp_acc_open_date",equity_sp_acc_open_date);
			map.put("margin_sp_acc_open_date",margin_sp_acc_open_date);
			map.put("tax_type_last",tax_type_last);
			map.put("margin_tax_type_last",margin_tax_type_last);
			if ( tax_type_next_is_set )
				map.put("tax_type_next",tax_type_next);
			if ( margin_tax_type_next_is_set )
				map.put("margin_tax_type_next",margin_tax_type_next);
			map.put("fluct_date",fluct_date);
			map.put("margin_fluct_date",margin_fluct_date);
			map.put("local_tax_div_last",local_tax_div_last);
			map.put("local_tax_div",local_tax_div);
			map.put("local_tax_div_next",local_tax_div_next);
			if ( qualified_inst_investor_div_is_set )
				map.put("qualified_inst_investor_div",qualified_inst_investor_div);
			if ( quoto_type_is_set )
				map.put("quoto_type",quoto_type);
			map.put("sp_acc_abolish_date",sp_acc_abolish_date);
			map.put("sp_mng_acc_open_div",sp_mng_acc_open_div);
			map.put("reserve_area",reserve_area);
			map.put("web3_encrypted_password",web3_encrypted_password);
			map.put("xtrade_encrypted_password",xtrade_encrypted_password);
			map.put("broadcast_law",broadcast_law);
			map.put("aviation_law",aviation_law);
			map.put("ntt_law",ntt_law);
			map.put("dividend_trans_designate",dividend_trans_designate);
			map.put("standing_proxy",standing_proxy);
			map.put("statutory_agent",statutory_agent);
			map.put("affiliate_account_code",affiliate_account_code);
			map.put("agency_notify_cmp_div",agency_notify_cmp_div);
		}
		return map;
	}


  /** 
   * <em>comp_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCompCode()
  {
    return comp_code;
  }


  /** 
   * <em>comp_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompCodeIsSet() {
    return comp_code_is_set;
  }


  /** 
   * <em>comp_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCompCodeIsModified() {
    return comp_code_is_modified;
  }


  /** 
   * <em>gen_acc_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGenAccDiv()
  {
    return gen_acc_div;
  }


  /** 
   * <em>gen_acc_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGenAccDivIsSet() {
    return gen_acc_div_is_set;
  }


  /** 
   * <em>gen_acc_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGenAccDivIsModified() {
    return gen_acc_div_is_modified;
  }


  /** 
   * <em>gen_br_del_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGenBrDelDiv()
  {
    return gen_br_del_div;
  }


  /** 
   * <em>gen_br_del_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGenBrDelDivIsSet() {
    return gen_br_del_div_is_set;
  }


  /** 
   * <em>gen_br_del_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGenBrDelDivIsModified() {
    return gen_br_del_div_is_modified;
  }


  /** 
   * <em>ruito_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRuitoAccOpenDiv()
  {
    return ruito_acc_open_div;
  }


  /** 
   * <em>ruito_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRuitoAccOpenDivIsSet() {
    return ruito_acc_open_div_is_set;
  }


  /** 
   * <em>ruito_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRuitoAccOpenDivIsModified() {
    return ruito_acc_open_div_is_modified;
  }


  /** 
   * <em>margin_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginAccOpenDiv()
  {
    return margin_acc_open_div;
  }


  /** 
   * <em>margin_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginAccOpenDivIsSet() {
    return margin_acc_open_div_is_set;
  }


  /** 
   * <em>margin_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginAccOpenDivIsModified() {
    return margin_acc_open_div_is_modified;
  }


  /** 
   * <em>when_issued_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getWhenIssuedAccOpenDiv()
  {
    return when_issued_acc_open_div;
  }


  /** 
   * <em>when_issued_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWhenIssuedAccOpenDivIsSet() {
    return when_issued_acc_open_div_is_set;
  }


  /** 
   * <em>when_issued_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWhenIssuedAccOpenDivIsModified() {
    return when_issued_acc_open_div_is_modified;
  }


  /** 
   * <em>report_dispatch_stop_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReportDispatchStopDiv()
  {
    return report_dispatch_stop_div;
  }


  /** 
   * <em>report_dispatch_stop_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReportDispatchStopDivIsSet() {
    return report_dispatch_stop_div_is_set;
  }


  /** 
   * <em>report_dispatch_stop_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReportDispatchStopDivIsModified() {
    return report_dispatch_stop_div_is_modified;
  }


  /** 
   * <em>doc_dispatch_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDocDispatchDiv()
  {
    return doc_dispatch_div;
  }


  /** 
   * <em>doc_dispatch_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDocDispatchDivIsSet() {
    return doc_dispatch_div_is_set;
  }


  /** 
   * <em>doc_dispatch_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDocDispatchDivIsModified() {
    return doc_dispatch_div_is_modified;
  }


  /** 
   * <em>gp_br_del_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpBrDelDiv()
  {
    return gp_br_del_div;
  }


  /** 
   * <em>gp_br_del_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpBrDelDivIsSet() {
    return gp_br_del_div_is_set;
  }


  /** 
   * <em>gp_br_del_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpBrDelDivIsModified() {
    return gp_br_del_div_is_modified;
  }


  /** 
   * <em>account_open_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getAccountOpenDate()
  {
    return account_open_date;
  }


  /** 
   * <em>account_open_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDateIsSet() {
    return account_open_date_is_set;
  }


  /** 
   * <em>account_open_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountOpenDateIsModified() {
    return account_open_date_is_modified;
  }


  /** 
   * <em>last_update_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdateDate()
  {
    return last_update_date;
  }


  /** 
   * <em>last_update_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateDateIsSet() {
    return last_update_date_is_set;
  }


  /** 
   * <em>last_update_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdateDateIsModified() {
    return last_update_date_is_modified;
  }


  /** 
   * <em>br_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBrCode()
  {
    return br_code;
  }


  /** 
   * <em>br_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBrCodeIsSet() {
    return br_code_is_set;
  }


  /** 
   * <em>br_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBrCodeIsModified() {
    return br_code_is_modified;
  }


  /** 
   * <em>cust_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCustCode()
  {
    return cust_code;
  }


  /** 
   * <em>cust_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCustCodeIsSet() {
    return cust_code_is_set;
  }


  /** 
   * <em>cust_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCustCodeIsModified() {
    return cust_code_is_modified;
  }


  /** 
   * <em>cust_code_cd</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCustCodeCd()
  {
    return cust_code_cd;
  }


  /** 
   * <em>cust_code_cd</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCustCodeCdIsSet() {
    return cust_code_cd_is_set;
  }


  /** 
   * <em>cust_code_cd</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCustCodeCdIsModified() {
    return cust_code_cd_is_modified;
  }


  /** 
   * <em>sonar_trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarTraderCode()
  {
    return sonar_trader_code;
  }


  /** 
   * <em>sonar_trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTraderCodeIsSet() {
    return sonar_trader_code_is_set;
  }


  /** 
   * <em>sonar_trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTraderCodeIsModified() {
    return sonar_trader_code_is_modified;
  }


  /** 
   * <em>rep_dispatch_stp_bd</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRepDispatchStpBd()
  {
    return rep_dispatch_stp_bd;
  }


  /** 
   * <em>rep_dispatch_stp_bd</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepDispatchStpBdIsSet() {
    return rep_dispatch_stp_bd_is_set;
  }


  /** 
   * <em>rep_dispatch_stp_bd</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepDispatchStpBdIsModified() {
    return rep_dispatch_stp_bd_is_modified;
  }


  /** 
   * <em>occupation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOccupation()
  {
    return occupation;
  }


  /** 
   * <em>occupation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOccupationIsSet() {
    return occupation_is_set;
  }


  /** 
   * <em>occupation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOccupationIsModified() {
    return occupation_is_modified;
  }


  /** 
   * <em>safe_cont_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSafeContOpenDiv()
  {
    return safe_cont_open_div;
  }


  /** 
   * <em>safe_cont_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSafeContOpenDivIsSet() {
    return safe_cont_open_div_is_set;
  }


  /** 
   * <em>safe_cont_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSafeContOpenDivIsModified() {
    return safe_cont_open_div_is_modified;
  }


  /** 
   * <em>foreign_sec_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getForeignSecAccOpenDiv()
  {
    return foreign_sec_acc_open_div;
  }


  /** 
   * <em>foreign_sec_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignSecAccOpenDivIsSet() {
    return foreign_sec_acc_open_div_is_set;
  }


  /** 
   * <em>foreign_sec_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignSecAccOpenDivIsModified() {
    return foreign_sec_acc_open_div_is_modified;
  }


  /** 
   * <em>tokuyu_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTokuyuAccOpenDiv()
  {
    return tokuyu_acc_open_div;
  }


  /** 
   * <em>tokuyu_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTokuyuAccOpenDivIsSet() {
    return tokuyu_acc_open_div_is_set;
  }


  /** 
   * <em>tokuyu_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTokuyuAccOpenDivIsModified() {
    return tokuyu_acc_open_div_is_modified;
  }


  /** 
   * <em>gold_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGoldAccOpenDiv()
  {
    return gold_acc_open_div;
  }


  /** 
   * <em>gold_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGoldAccOpenDivIsSet() {
    return gold_acc_open_div_is_set;
  }


  /** 
   * <em>gold_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGoldAccOpenDivIsModified() {
    return gold_acc_open_div_is_modified;
  }


  /** 
   * <em>total_trade_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTotalTradeOpenDiv()
  {
    return total_trade_open_div;
  }


  /** 
   * <em>total_trade_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTotalTradeOpenDivIsSet() {
    return total_trade_open_div_is_set;
  }


  /** 
   * <em>total_trade_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTotalTradeOpenDivIsModified() {
    return total_trade_open_div_is_modified;
  }


  /** 
   * <em>tie_up_loan_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTieUpLoanOpenDiv()
  {
    return tie_up_loan_open_div;
  }


  /** 
   * <em>tie_up_loan_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTieUpLoanOpenDivIsSet() {
    return tie_up_loan_open_div_is_set;
  }


  /** 
   * <em>tie_up_loan_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTieUpLoanOpenDivIsModified() {
    return tie_up_loan_open_div_is_modified;
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
   * <em>address_unknown</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddressUnknown()
  {
    return address_unknown;
  }


  /** 
   * <em>address_unknown</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressUnknownIsSet() {
    return address_unknown_is_set;
  }


  /** 
   * <em>address_unknown</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressUnknownIsModified() {
    return address_unknown_is_modified;
  }


  /** 
   * <em>cust_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCustDiv()
  {
    return cust_div;
  }


  /** 
   * <em>cust_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCustDivIsSet() {
    return cust_div_is_set;
  }


  /** 
   * <em>cust_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCustDivIsModified() {
    return cust_div_is_modified;
  }


  /** 
   * <em>deposit_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDepositDiv()
  {
    return deposit_div;
  }


  /** 
   * <em>deposit_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDivIsSet() {
    return deposit_div_is_set;
  }


  /** 
   * <em>deposit_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositDivIsModified() {
    return deposit_div_is_modified;
  }


  /** 
   * <em>all_substitution_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAllSubstitutionDiv()
  {
    return all_substitution_div;
  }


  /** 
   * <em>all_substitution_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAllSubstitutionDivIsSet() {
    return all_substitution_div_is_set;
  }


  /** 
   * <em>all_substitution_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAllSubstitutionDivIsModified() {
    return all_substitution_div_is_modified;
  }


  /** 
   * <em>ins_loan_bill_mthd_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInsLoanBillMthdDiv()
  {
    return ins_loan_bill_mthd_div;
  }


  /** 
   * <em>ins_loan_bill_mthd_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsLoanBillMthdDivIsSet() {
    return ins_loan_bill_mthd_div_is_set;
  }


  /** 
   * <em>ins_loan_bill_mthd_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsLoanBillMthdDivIsModified() {
    return ins_loan_bill_mthd_div_is_modified;
  }


  /** 
   * <em>ins_loan_cer_mthd_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInsLoanCerMthdDiv()
  {
    return ins_loan_cer_mthd_div;
  }


  /** 
   * <em>ins_loan_cer_mthd_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsLoanCerMthdDivIsSet() {
    return ins_loan_cer_mthd_div_is_set;
  }


  /** 
   * <em>ins_loan_cer_mthd_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsLoanCerMthdDivIsModified() {
    return ins_loan_cer_mthd_div_is_modified;
  }


  /** 
   * <em>ins_loan_clause_mthd_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInsLoanClauseMthdDiv()
  {
    return ins_loan_clause_mthd_div;
  }


  /** 
   * <em>ins_loan_clause_mthd_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsLoanClauseMthdDivIsSet() {
    return ins_loan_clause_mthd_div_is_set;
  }


  /** 
   * <em>ins_loan_clause_mthd_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsLoanClauseMthdDivIsModified() {
    return ins_loan_clause_mthd_div_is_modified;
  }


  /** 
   * <em>dom_tax_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDomTaxDiv()
  {
    return dom_tax_div;
  }


  /** 
   * <em>dom_tax_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDomTaxDivIsSet() {
    return dom_tax_div_is_set;
  }


  /** 
   * <em>dom_tax_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDomTaxDivIsModified() {
    return dom_tax_div_is_modified;
  }


  /** 
   * <em>client_trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getClientTraderCode()
  {
    return client_trader_code;
  }


  /** 
   * <em>client_trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClientTraderCodeIsSet() {
    return client_trader_code_is_set;
  }


  /** 
   * <em>client_trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClientTraderCodeIsModified() {
    return client_trader_code_is_modified;
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
   * <em>family_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFamilyNameAlt1()
  {
    return family_name_alt1;
  }


  /** 
   * <em>family_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameAlt1IsSet() {
    return family_name_alt1_is_set;
  }


  /** 
   * <em>family_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameAlt1IsModified() {
    return family_name_alt1_is_modified;
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
   * <em>prefecture</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPrefecture()
  {
    return prefecture;
  }


  /** 
   * <em>prefecture</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrefectureIsSet() {
    return prefecture_is_set;
  }


  /** 
   * <em>prefecture</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrefectureIsModified() {
    return prefecture_is_modified;
  }


  /** 
   * <em>comma</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getComma()
  {
    return comma;
  }


  /** 
   * <em>comma</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommaIsSet() {
    return comma_is_set;
  }


  /** 
   * <em>comma</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommaIsModified() {
    return comma_is_modified;
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
   * <em>family_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFamilyName()
  {
    return family_name;
  }


  /** 
   * <em>family_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameIsSet() {
    return family_name_is_set;
  }


  /** 
   * <em>family_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameIsModified() {
    return family_name_is_modified;
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
   * <em>contact_address_telephone</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getContactAddressTelephone()
  {
    return contact_address_telephone;
  }


  /** 
   * <em>contact_address_telephone</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContactAddressTelephoneIsSet() {
    return contact_address_telephone_is_set;
  }


  /** 
   * <em>contact_address_telephone</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContactAddressTelephoneIsModified() {
    return contact_address_telephone_is_modified;
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
   * <em>before_acc_trans_br_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBeforeAccTransBrCode()
  {
    return before_acc_trans_br_code;
  }


  /** 
   * <em>before_acc_trans_br_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeAccTransBrCodeIsSet() {
    return before_acc_trans_br_code_is_set;
  }


  /** 
   * <em>before_acc_trans_br_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeAccTransBrCodeIsModified() {
    return before_acc_trans_br_code_is_modified;
  }


  /** 
   * <em>before_acc_trans_cust_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBeforeAccTransCustCode()
  {
    return before_acc_trans_cust_code;
  }


  /** 
   * <em>before_acc_trans_cust_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeAccTransCustCodeIsSet() {
    return before_acc_trans_cust_code_is_set;
  }


  /** 
   * <em>before_acc_trans_cust_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBeforeAccTransCustCodeIsModified() {
    return before_acc_trans_cust_code_is_modified;
  }


  /** 
   * <em>d_card_appli_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getDCardAppliDate()
  {
    return d_card_appli_date;
  }


  /** 
   * <em>d_card_appli_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardAppliDateIsSet() {
    return d_card_appli_date_is_set;
  }


  /** 
   * <em>d_card_appli_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardAppliDateIsModified() {
    return d_card_appli_date_is_modified;
  }


  /** 
   * <em>d_card_issue_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getDCardIssueDate()
  {
    return d_card_issue_date;
  }


  /** 
   * <em>d_card_issue_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardIssueDateIsSet() {
    return d_card_issue_date_is_set;
  }


  /** 
   * <em>d_card_issue_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardIssueDateIsModified() {
    return d_card_issue_date_is_modified;
  }


  /** 
   * <em>d_card_password</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDCardPassword()
  {
    return d_card_password;
  }


  /** 
   * <em>d_card_password</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardPasswordIsSet() {
    return d_card_password_is_set;
  }


  /** 
   * <em>d_card_password</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardPasswordIsModified() {
    return d_card_password_is_modified;
  }


  /** 
   * <em>d_card_issue_reason</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDCardIssueReason()
  {
    return d_card_issue_reason;
  }


  /** 
   * <em>d_card_issue_reason</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardIssueReasonIsSet() {
    return d_card_issue_reason_is_set;
  }


  /** 
   * <em>d_card_issue_reason</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardIssueReasonIsModified() {
    return d_card_issue_reason_is_modified;
  }


  /** 
   * <em>d_card_issue_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDCardIssueNumber()
  {
    return d_card_issue_number;
  }


  /** 
   * <em>d_card_issue_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardIssueNumberIsSet() {
    return d_card_issue_number_is_set;
  }


  /** 
   * <em>d_card_issue_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardIssueNumberIsModified() {
    return d_card_issue_number_is_modified;
  }


  /** 
   * <em>d_card_stop_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getDCardStopDate()
  {
    return d_card_stop_date;
  }


  /** 
   * <em>d_card_stop_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardStopDateIsSet() {
    return d_card_stop_date_is_set;
  }


  /** 
   * <em>d_card_stop_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardStopDateIsModified() {
    return d_card_stop_date_is_modified;
  }


  /** 
   * <em>d_card_stop_reason</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDCardStopReason()
  {
    return d_card_stop_reason;
  }


  /** 
   * <em>d_card_stop_reason</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardStopReasonIsSet() {
    return d_card_stop_reason_is_set;
  }


  /** 
   * <em>d_card_stop_reason</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardStopReasonIsModified() {
    return d_card_stop_reason_is_modified;
  }


  /** 
   * <em>d_card_cancel_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getDCardCancelDate()
  {
    return d_card_cancel_date;
  }


  /** 
   * <em>d_card_cancel_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardCancelDateIsSet() {
    return d_card_cancel_date_is_set;
  }


  /** 
   * <em>d_card_cancel_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardCancelDateIsModified() {
    return d_card_cancel_date_is_modified;
  }


  /** 
   * <em>d_card_cancel_reason</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDCardCancelReason()
  {
    return d_card_cancel_reason;
  }


  /** 
   * <em>d_card_cancel_reason</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardCancelReasonIsSet() {
    return d_card_cancel_reason_is_set;
  }


  /** 
   * <em>d_card_cancel_reason</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardCancelReasonIsModified() {
    return d_card_cancel_reason_is_modified;
  }


  /** 
   * <em>d_card_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDCardName()
  {
    return d_card_name;
  }


  /** 
   * <em>d_card_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardNameIsSet() {
    return d_card_name_is_set;
  }


  /** 
   * <em>d_card_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDCardNameIsModified() {
    return d_card_name_is_modified;
  }


  /** 
   * <em>pass_err_code_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPassErrCodeType()
  {
    return pass_err_code_type;
  }


  /** 
   * <em>pass_err_code_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPassErrCodeTypeIsSet() {
    return pass_err_code_type_is_set;
  }


  /** 
   * <em>pass_err_code_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPassErrCodeTypeIsModified() {
    return pass_err_code_type_is_modified;
  }


  /** 
   * <em>pass_err_code_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPassErrCodeNumber()
  {
    return pass_err_code_number;
  }


  /** 
   * <em>pass_err_code_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPassErrCodeNumberIsSet() {
    return pass_err_code_number_is_set;
  }


  /** 
   * <em>pass_err_code_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPassErrCodeNumberIsModified() {
    return pass_err_code_number_is_modified;
  }


  /** 
   * <em>pass_err_code_chg_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getPassErrCodeChgDate()
  {
    return pass_err_code_chg_date;
  }


  /** 
   * <em>pass_err_code_chg_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPassErrCodeChgDateIsSet() {
    return pass_err_code_chg_date_is_set;
  }


  /** 
   * <em>pass_err_code_chg_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPassErrCodeChgDateIsModified() {
    return pass_err_code_chg_date_is_modified;
  }


  /** 
   * <em>pass_err_code_chg_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPassErrCodeChgTime()
  {
    return pass_err_code_chg_time;
  }


  /** 
   * <em>pass_err_code_chg_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPassErrCodeChgTimeIsSet() {
    return pass_err_code_chg_time_is_set;
  }


  /** 
   * <em>pass_err_code_chg_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPassErrCodeChgTimeIsModified() {
    return pass_err_code_chg_time_is_modified;
  }


  /** 
   * <em>ans_cust_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAnsCustDiv()
  {
    return ans_cust_div;
  }


  /** 
   * <em>ans_cust_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAnsCustDivIsSet() {
    return ans_cust_div_is_set;
  }


  /** 
   * <em>ans_cust_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAnsCustDivIsModified() {
    return ans_cust_div_is_modified;
  }


  /** 
   * <em>ans_stock_appli_cate</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getAnsStockAppliCate()
  {
    return ans_stock_appli_cate;
  }


  /** 
   * <em>ans_stock_appli_cate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAnsStockAppliCateIsSet() {
    return ans_stock_appli_cate_is_set;
  }


  /** 
   * <em>ans_stock_appli_cate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAnsStockAppliCateIsModified() {
    return ans_stock_appli_cate_is_modified;
  }


  /** 
   * <em>dummy1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy1()
  {
    return dummy1;
  }


  /** 
   * <em>dummy1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy1IsSet() {
    return dummy1_is_set;
  }


  /** 
   * <em>dummy1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy1IsModified() {
    return dummy1_is_modified;
  }


  /** 
   * <em>self_assessed_sep_tax</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSelfAssessedSepTax()
  {
    return self_assessed_sep_tax;
  }


  /** 
   * <em>self_assessed_sep_tax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSelfAssessedSepTaxIsSet() {
    return self_assessed_sep_tax_is_set;
  }


  /** 
   * <em>self_assessed_sep_tax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSelfAssessedSepTaxIsModified() {
    return self_assessed_sep_tax_is_modified;
  }


  /** 
   * <em>tokuyu_appli_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTokuyuAppliDiv()
  {
    return tokuyu_appli_div;
  }


  /** 
   * <em>tokuyu_appli_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTokuyuAppliDivIsSet() {
    return tokuyu_appli_div_is_set;
  }


  /** 
   * <em>tokuyu_appli_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTokuyuAppliDivIsModified() {
    return tokuyu_appli_div_is_modified;
  }


  /** 
   * <em>maruyu_appli_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMaruyuAppliDiv()
  {
    return maruyu_appli_div;
  }


  /** 
   * <em>maruyu_appli_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaruyuAppliDivIsSet() {
    return maruyu_appli_div_is_set;
  }


  /** 
   * <em>maruyu_appli_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaruyuAppliDivIsModified() {
    return maruyu_appli_div_is_modified;
  }


  /** 
   * <em>tokuyu_laundering_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTokuyuLaunderingDiv()
  {
    return tokuyu_laundering_div;
  }


  /** 
   * <em>tokuyu_laundering_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTokuyuLaunderingDivIsSet() {
    return tokuyu_laundering_div_is_set;
  }


  /** 
   * <em>tokuyu_laundering_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTokuyuLaunderingDivIsModified() {
    return tokuyu_laundering_div_is_modified;
  }


  /** 
   * <em>maruyu_laundering_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMaruyuLaunderingDiv()
  {
    return maruyu_laundering_div;
  }


  /** 
   * <em>maruyu_laundering_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaruyuLaunderingDivIsSet() {
    return maruyu_laundering_div_is_set;
  }


  /** 
   * <em>maruyu_laundering_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaruyuLaunderingDivIsModified() {
    return maruyu_laundering_div_is_modified;
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
   * <em>dummy2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy2()
  {
    return dummy2;
  }


  /** 
   * <em>dummy2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy2IsSet() {
    return dummy2_is_set;
  }


  /** 
   * <em>dummy2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy2IsModified() {
    return dummy2_is_modified;
  }


  /** 
   * <em>ht_settlement_way</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHtSettlementWay()
  {
    return ht_settlement_way;
  }


  /** 
   * <em>ht_settlement_way</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHtSettlementWayIsSet() {
    return ht_settlement_way_is_set;
  }


  /** 
   * <em>ht_settlement_way</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHtSettlementWayIsModified() {
    return ht_settlement_way_is_modified;
  }


  /** 
   * <em>dummy3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy3()
  {
    return dummy3;
  }


  /** 
   * <em>dummy3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy3IsSet() {
    return dummy3_is_set;
  }


  /** 
   * <em>dummy3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy3IsModified() {
    return dummy3_is_modified;
  }


  /** 
   * <em>total_tax_identity</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTotalTaxIdentity()
  {
    return total_tax_identity;
  }


  /** 
   * <em>total_tax_identity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTotalTaxIdentityIsSet() {
    return total_tax_identity_is_set;
  }


  /** 
   * <em>total_tax_identity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTotalTaxIdentityIsModified() {
    return total_tax_identity_is_modified;
  }


  /** 
   * <em>dummy4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy4()
  {
    return dummy4;
  }


  /** 
   * <em>dummy4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy4IsSet() {
    return dummy4_is_set;
  }


  /** 
   * <em>dummy4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy4IsModified() {
    return dummy4_is_modified;
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
   * <em>mort_trade_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMortTradeOpenDiv()
  {
    return mort_trade_open_div;
  }


  /** 
   * <em>mort_trade_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMortTradeOpenDivIsSet() {
    return mort_trade_open_div_is_set;
  }


  /** 
   * <em>mort_trade_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMortTradeOpenDivIsModified() {
    return mort_trade_open_div_is_modified;
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
   * <em>os_fnc_futures_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOsFncFuturesOpenDiv()
  {
    return os_fnc_futures_open_div;
  }


  /** 
   * <em>os_fnc_futures_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOsFncFuturesOpenDivIsSet() {
    return os_fnc_futures_open_div_is_set;
  }


  /** 
   * <em>os_fnc_futures_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOsFncFuturesOpenDivIsModified() {
    return os_fnc_futures_open_div_is_modified;
  }


  /** 
   * <em>os_sec_futures_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOsSecFuturesOpenDiv()
  {
    return os_sec_futures_open_div;
  }


  /** 
   * <em>os_sec_futures_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOsSecFuturesOpenDivIsSet() {
    return os_sec_futures_open_div_is_set;
  }


  /** 
   * <em>os_sec_futures_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOsSecFuturesOpenDivIsModified() {
    return os_sec_futures_open_div_is_modified;
  }


  /** 
   * <em>tokyo_fnc_futures_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTokyoFncFuturesOpenDiv()
  {
    return tokyo_fnc_futures_open_div;
  }


  /** 
   * <em>tokyo_fnc_futures_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTokyoFncFuturesOpenDivIsSet() {
    return tokyo_fnc_futures_open_div_is_set;
  }


  /** 
   * <em>tokyo_fnc_futures_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTokyoFncFuturesOpenDivIsModified() {
    return tokyo_fnc_futures_open_div_is_modified;
  }


  /** 
   * <em>for_war_trade_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getForWarTradeOpenDiv()
  {
    return for_war_trade_open_div;
  }


  /** 
   * <em>for_war_trade_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForWarTradeOpenDivIsSet() {
    return for_war_trade_open_div_is_set;
  }


  /** 
   * <em>for_war_trade_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForWarTradeOpenDivIsModified() {
    return for_war_trade_open_div_is_modified;
  }


  /** 
   * <em>tentou_trade_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTentouTradeOpenDiv()
  {
    return tentou_trade_open_div;
  }


  /** 
   * <em>tentou_trade_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTentouTradeOpenDivIsSet() {
    return tentou_trade_open_div_is_set;
  }


  /** 
   * <em>tentou_trade_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTentouTradeOpenDivIsModified() {
    return tentou_trade_open_div_is_modified;
  }


  /** 
   * <em>represent_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRepresentDiv()
  {
    return represent_div;
  }


  /** 
   * <em>represent_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentDivIsSet() {
    return represent_div_is_set;
  }


  /** 
   * <em>represent_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentDivIsModified() {
    return represent_div_is_modified;
  }


  /** 
   * <em>family_nurturer_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFamilyNurturerCode()
  {
    return family_nurturer_code;
  }


  /** 
   * <em>family_nurturer_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNurturerCodeIsSet() {
    return family_nurturer_code_is_set;
  }


  /** 
   * <em>family_nurturer_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNurturerCodeIsModified() {
    return family_nurturer_code_is_modified;
  }


  /** 
   * <em>family_unit_adimin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFamilyUnitAdimin()
  {
    return family_unit_adimin;
  }


  /** 
   * <em>family_unit_adimin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyUnitAdiminIsSet() {
    return family_unit_adimin_is_set;
  }


  /** 
   * <em>family_unit_adimin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyUnitAdiminIsModified() {
    return family_unit_adimin_is_modified;
  }


  /** 
   * <em>new_monthly_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNewMonthlyDiv()
  {
    return new_monthly_div;
  }


  /** 
   * <em>new_monthly_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewMonthlyDivIsSet() {
    return new_monthly_div_is_set;
  }


  /** 
   * <em>new_monthly_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewMonthlyDivIsModified() {
    return new_monthly_div_is_modified;
  }


  /** 
   * <em>statement_skip1_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStatementSkip1Div()
  {
    return statement_skip1_div;
  }


  /** 
   * <em>statement_skip1_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatementSkip1DivIsSet() {
    return statement_skip1_div_is_set;
  }


  /** 
   * <em>statement_skip1_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatementSkip1DivIsModified() {
    return statement_skip1_div_is_modified;
  }


  /** 
   * <em>statement_skip2_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStatementSkip2Div()
  {
    return statement_skip2_div;
  }


  /** 
   * <em>statement_skip2_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatementSkip2DivIsSet() {
    return statement_skip2_div_is_set;
  }


  /** 
   * <em>statement_skip2_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatementSkip2DivIsModified() {
    return statement_skip2_div_is_modified;
  }


  /** 
   * <em>family_unit_del_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFamilyUnitDelDiv()
  {
    return family_unit_del_div;
  }


  /** 
   * <em>family_unit_del_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyUnitDelDivIsSet() {
    return family_unit_del_div_is_set;
  }


  /** 
   * <em>family_unit_del_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyUnitDelDivIsModified() {
    return family_unit_del_div_is_modified;
  }


  /** 
   * <em>dummy5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy5()
  {
    return dummy5;
  }


  /** 
   * <em>dummy5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy5IsSet() {
    return dummy5_is_set;
  }


  /** 
   * <em>dummy5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy5IsModified() {
    return dummy5_is_modified;
  }


  /** 
   * <em>br_dispatch_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBrDispatchDiv()
  {
    return br_dispatch_div;
  }


  /** 
   * <em>br_dispatch_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBrDispatchDivIsSet() {
    return br_dispatch_div_is_set;
  }


  /** 
   * <em>br_dispatch_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBrDispatchDivIsModified() {
    return br_dispatch_div_is_modified;
  }


  /** 
   * <em>maruyu_grade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMaruyuGradeDiv()
  {
    return maruyu_grade_div;
  }


  /** 
   * <em>maruyu_grade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaruyuGradeDivIsSet() {
    return maruyu_grade_div_is_set;
  }


  /** 
   * <em>maruyu_grade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaruyuGradeDivIsModified() {
    return maruyu_grade_div_is_modified;
  }


  /** 
   * <em>dummy6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy6()
  {
    return dummy6;
  }


  /** 
   * <em>dummy6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy6IsSet() {
    return dummy6_is_set;
  }


  /** 
   * <em>dummy6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy6IsModified() {
    return dummy6_is_modified;
  }


  /** 
   * <em>dummy7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy7()
  {
    return dummy7;
  }


  /** 
   * <em>dummy7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy7IsSet() {
    return dummy7_is_set;
  }


  /** 
   * <em>dummy7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy7IsModified() {
    return dummy7_is_modified;
  }


  /** 
   * <em>trans_tax_div_acc_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTransTaxDivAccDate()
  {
    return trans_tax_div_acc_date;
  }


  /** 
   * <em>trans_tax_div_acc_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransTaxDivAccDateIsSet() {
    return trans_tax_div_acc_date_is_set;
  }


  /** 
   * <em>trans_tax_div_acc_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransTaxDivAccDateIsModified() {
    return trans_tax_div_acc_date_is_modified;
  }


  /** 
   * <em>trans_tax_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTransTaxDiv()
  {
    return trans_tax_div;
  }


  /** 
   * <em>trans_tax_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransTaxDivIsSet() {
    return trans_tax_div_is_set;
  }


  /** 
   * <em>trans_tax_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransTaxDivIsModified() {
    return trans_tax_div_is_modified;
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
   * <em>bond_d_and_c_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBondDAndCOpenDiv()
  {
    return bond_d_and_c_open_div;
  }


  /** 
   * <em>bond_d_and_c_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondDAndCOpenDivIsSet() {
    return bond_d_and_c_open_div_is_set;
  }


  /** 
   * <em>bond_d_and_c_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondDAndCOpenDivIsModified() {
    return bond_d_and_c_open_div_is_modified;
  }


  /** 
   * <em>os_cd_cp_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOsCdCpOpenDiv()
  {
    return os_cd_cp_open_div;
  }


  /** 
   * <em>os_cd_cp_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOsCdCpOpenDivIsSet() {
    return os_cd_cp_open_div_is_set;
  }


  /** 
   * <em>os_cd_cp_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOsCdCpOpenDivIsModified() {
    return os_cd_cp_open_div_is_modified;
  }


  /** 
   * <em>new_tb_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNewTbOpenDiv()
  {
    return new_tb_open_div;
  }


  /** 
   * <em>new_tb_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewTbOpenDivIsSet() {
    return new_tb_open_div_is_set;
  }


  /** 
   * <em>new_tb_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewTbOpenDivIsModified() {
    return new_tb_open_div_is_modified;
  }


  /** 
   * <em>dom_cp_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDomCpOpenDiv()
  {
    return dom_cp_open_div;
  }


  /** 
   * <em>dom_cp_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDomCpOpenDivIsSet() {
    return dom_cp_open_div_is_set;
  }


  /** 
   * <em>dom_cp_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDomCpOpenDivIsModified() {
    return dom_cp_open_div_is_modified;
  }


  /** 
   * <em>bond_tentou_op_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBondTentouOpOpenDiv()
  {
    return bond_tentou_op_open_div;
  }


  /** 
   * <em>bond_tentou_op_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondTentouOpOpenDivIsSet() {
    return bond_tentou_op_open_div_is_set;
  }


  /** 
   * <em>bond_tentou_op_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondTentouOpOpenDivIsModified() {
    return bond_tentou_op_open_div_is_modified;
  }


  /** 
   * <em>t_bond_futures_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTBondFuturesOpenDiv()
  {
    return t_bond_futures_open_div;
  }


  /** 
   * <em>t_bond_futures_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTBondFuturesOpenDivIsSet() {
    return t_bond_futures_open_div_is_set;
  }


  /** 
   * <em>t_bond_futures_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTBondFuturesOpenDivIsModified() {
    return t_bond_futures_open_div_is_modified;
  }


  /** 
   * <em>dom_war_trade_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDomWarTradeOpenDiv()
  {
    return dom_war_trade_open_div;
  }


  /** 
   * <em>dom_war_trade_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDomWarTradeOpenDivIsSet() {
    return dom_war_trade_open_div_is_set;
  }


  /** 
   * <em>dom_war_trade_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDomWarTradeOpenDivIsModified() {
    return dom_war_trade_open_div_is_modified;
  }


  /** 
   * <em>dummy8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy8()
  {
    return dummy8;
  }


  /** 
   * <em>dummy8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy8IsSet() {
    return dummy8_is_set;
  }


  /** 
   * <em>dummy8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy8IsModified() {
    return dummy8_is_modified;
  }


  /** 
   * <em>dom_for_bond_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDomForBondOpenDiv()
  {
    return dom_for_bond_open_div;
  }


  /** 
   * <em>dom_for_bond_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDomForBondOpenDivIsSet() {
    return dom_for_bond_open_div_is_set;
  }


  /** 
   * <em>dom_for_bond_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDomForBondOpenDivIsModified() {
    return dom_for_bond_open_div_is_modified;
  }


  /** 
   * <em>tentou_sp_rule_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTentouSpRuleOpenDiv()
  {
    return tentou_sp_rule_open_div;
  }


  /** 
   * <em>tentou_sp_rule_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTentouSpRuleOpenDivIsSet() {
    return tentou_sp_rule_open_div_is_set;
  }


  /** 
   * <em>tentou_sp_rule_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTentouSpRuleOpenDivIsModified() {
    return tentou_sp_rule_open_div_is_modified;
  }


  /** 
   * <em>dummy9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy9()
  {
    return dummy9;
  }


  /** 
   * <em>dummy9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy9IsSet() {
    return dummy9_is_set;
  }


  /** 
   * <em>dummy9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy9IsModified() {
    return dummy9_is_modified;
  }


  /** 
   * <em>dummy10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy10()
  {
    return dummy10;
  }


  /** 
   * <em>dummy10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy10IsSet() {
    return dummy10_is_set;
  }


  /** 
   * <em>dummy10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy10IsModified() {
    return dummy10_is_modified;
  }


  /** 
   * <em>dummy11</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy11()
  {
    return dummy11;
  }


  /** 
   * <em>dummy11</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy11IsSet() {
    return dummy11_is_set;
  }


  /** 
   * <em>dummy11</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy11IsModified() {
    return dummy11_is_modified;
  }


  /** 
   * <em>dummy12</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy12()
  {
    return dummy12;
  }


  /** 
   * <em>dummy12</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy12IsSet() {
    return dummy12_is_set;
  }


  /** 
   * <em>dummy12</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy12IsModified() {
    return dummy12_is_modified;
  }


  /** 
   * <em>dummy13</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy13()
  {
    return dummy13;
  }


  /** 
   * <em>dummy13</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy13IsSet() {
    return dummy13_is_set;
  }


  /** 
   * <em>dummy13</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy13IsModified() {
    return dummy13_is_modified;
  }


  /** 
   * <em>mrf_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMrfAccOpenDiv()
  {
    return mrf_acc_open_div;
  }


  /** 
   * <em>mrf_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMrfAccOpenDivIsSet() {
    return mrf_acc_open_div_is_set;
  }


  /** 
   * <em>mrf_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMrfAccOpenDivIsModified() {
    return mrf_acc_open_div_is_modified;
  }


  /** 
   * <em>safe_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSafeContDiv()
  {
    return safe_cont_div;
  }


  /** 
   * <em>safe_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSafeContDivIsSet() {
    return safe_cont_div_is_set;
  }


  /** 
   * <em>safe_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSafeContDivIsModified() {
    return safe_cont_div_is_modified;
  }


  /** 
   * <em>foreign_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getForeignContDiv()
  {
    return foreign_cont_div;
  }


  /** 
   * <em>foreign_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignContDivIsSet() {
    return foreign_cont_div_is_set;
  }


  /** 
   * <em>foreign_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignContDivIsModified() {
    return foreign_cont_div_is_modified;
  }


  /** 
   * <em>dummy14</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy14()
  {
    return dummy14;
  }


  /** 
   * <em>dummy14</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy14IsSet() {
    return dummy14_is_set;
  }


  /** 
   * <em>dummy14</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy14IsModified() {
    return dummy14_is_modified;
  }


  /** 
   * <em>gold_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGoldContDiv()
  {
    return gold_cont_div;
  }


  /** 
   * <em>gold_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGoldContDivIsSet() {
    return gold_cont_div_is_set;
  }


  /** 
   * <em>gold_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGoldContDivIsModified() {
    return gold_cont_div_is_modified;
  }


  /** 
   * <em>margin_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginContDiv()
  {
    return margin_cont_div;
  }


  /** 
   * <em>margin_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginContDivIsSet() {
    return margin_cont_div_is_set;
  }


  /** 
   * <em>margin_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginContDivIsModified() {
    return margin_cont_div_is_modified;
  }


  /** 
   * <em>when_issued_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getWhenIssuedContDiv()
  {
    return when_issued_cont_div;
  }


  /** 
   * <em>when_issued_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWhenIssuedContDivIsSet() {
    return when_issued_cont_div_is_set;
  }


  /** 
   * <em>when_issued_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWhenIssuedContDivIsModified() {
    return when_issued_cont_div_is_modified;
  }


  /** 
   * <em>futures_op_cont_div_tokyo</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFuturesOpContDivTokyo()
  {
    return futures_op_cont_div_tokyo;
  }


  /** 
   * <em>futures_op_cont_div_tokyo</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFuturesOpContDivTokyoIsSet() {
    return futures_op_cont_div_tokyo_is_set;
  }


  /** 
   * <em>futures_op_cont_div_tokyo</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFuturesOpContDivTokyoIsModified() {
    return futures_op_cont_div_tokyo_is_modified;
  }


  /** 
   * <em>dummy15</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy15()
  {
    return dummy15;
  }


  /** 
   * <em>dummy15</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy15IsSet() {
    return dummy15_is_set;
  }


  /** 
   * <em>dummy15</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy15IsModified() {
    return dummy15_is_modified;
  }


  /** 
   * <em>futures_op_cont_div_osaka</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFuturesOpContDivOsaka()
  {
    return futures_op_cont_div_osaka;
  }


  /** 
   * <em>futures_op_cont_div_osaka</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFuturesOpContDivOsakaIsSet() {
    return futures_op_cont_div_osaka_is_set;
  }


  /** 
   * <em>futures_op_cont_div_osaka</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFuturesOpContDivOsakaIsModified() {
    return futures_op_cont_div_osaka_is_modified;
  }


  /** 
   * <em>tokyo_mothers_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTokyoMothersContDiv()
  {
    return tokyo_mothers_cont_div;
  }


  /** 
   * <em>tokyo_mothers_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTokyoMothersContDivIsSet() {
    return tokyo_mothers_cont_div_is_set;
  }


  /** 
   * <em>tokyo_mothers_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTokyoMothersContDivIsModified() {
    return tokyo_mothers_cont_div_is_modified;
  }


  /** 
   * <em>futures_op_cont_div_nagoya</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFuturesOpContDivNagoya()
  {
    return futures_op_cont_div_nagoya;
  }


  /** 
   * <em>futures_op_cont_div_nagoya</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFuturesOpContDivNagoyaIsSet() {
    return futures_op_cont_div_nagoya_is_set;
  }


  /** 
   * <em>futures_op_cont_div_nagoya</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFuturesOpContDivNagoyaIsModified() {
    return futures_op_cont_div_nagoya_is_modified;
  }


  /** 
   * <em>nq_j_gl_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNqJGlContDiv()
  {
    return nq_j_gl_cont_div;
  }


  /** 
   * <em>nq_j_gl_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNqJGlContDivIsSet() {
    return nq_j_gl_cont_div_is_set;
  }


  /** 
   * <em>nq_j_gl_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNqJGlContDivIsModified() {
    return nq_j_gl_cont_div_is_modified;
  }


  /** 
   * <em>osaka_nw_mkt_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOsakaNwMktContDiv()
  {
    return osaka_nw_mkt_cont_div;
  }


  /** 
   * <em>osaka_nw_mkt_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOsakaNwMktContDivIsSet() {
    return osaka_nw_mkt_cont_div_is_set;
  }


  /** 
   * <em>osaka_nw_mkt_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOsakaNwMktContDivIsModified() {
    return osaka_nw_mkt_cont_div_is_modified;
  }


  /** 
   * <em>nagoya_grw_cpy_mkt_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNagoyaGrwCpyMktContDiv()
  {
    return nagoya_grw_cpy_mkt_cont_div;
  }


  /** 
   * <em>nagoya_grw_cpy_mkt_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNagoyaGrwCpyMktContDivIsSet() {
    return nagoya_grw_cpy_mkt_cont_div_is_set;
  }


  /** 
   * <em>nagoya_grw_cpy_mkt_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNagoyaGrwCpyMktContDivIsModified() {
    return nagoya_grw_cpy_mkt_cont_div_is_modified;
  }


  /** 
   * <em>for_war_trade_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getForWarTradeContDiv()
  {
    return for_war_trade_cont_div;
  }


  /** 
   * <em>for_war_trade_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForWarTradeContDivIsSet() {
    return for_war_trade_cont_div_is_set;
  }


  /** 
   * <em>for_war_trade_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForWarTradeContDivIsModified() {
    return for_war_trade_cont_div_is_modified;
  }


  /** 
   * <em>tentou_trade_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTentouTradeContDiv()
  {
    return tentou_trade_cont_div;
  }


  /** 
   * <em>tentou_trade_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTentouTradeContDivIsSet() {
    return tentou_trade_cont_div_is_set;
  }


  /** 
   * <em>tentou_trade_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTentouTradeContDivIsModified() {
    return tentou_trade_cont_div_is_modified;
  }


  /** 
   * <em>bond_d_and_c_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBondDAndCContDiv()
  {
    return bond_d_and_c_cont_div;
  }


  /** 
   * <em>bond_d_and_c_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondDAndCContDivIsSet() {
    return bond_d_and_c_cont_div_is_set;
  }


  /** 
   * <em>bond_d_and_c_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondDAndCContDivIsModified() {
    return bond_d_and_c_cont_div_is_modified;
  }


  /** 
   * <em>sapporo_amb_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSapporoAmbContDiv()
  {
    return sapporo_amb_cont_div;
  }


  /** 
   * <em>sapporo_amb_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSapporoAmbContDivIsSet() {
    return sapporo_amb_cont_div_is_set;
  }


  /** 
   * <em>sapporo_amb_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSapporoAmbContDivIsModified() {
    return sapporo_amb_cont_div_is_modified;
  }


  /** 
   * <em>new_tb_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNewTbContDiv()
  {
    return new_tb_cont_div;
  }


  /** 
   * <em>new_tb_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewTbContDivIsSet() {
    return new_tb_cont_div_is_set;
  }


  /** 
   * <em>new_tb_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewTbContDivIsModified() {
    return new_tb_cont_div_is_modified;
  }


  /** 
   * <em>gen_credit_acc_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGenCreditAccContDiv()
  {
    return gen_credit_acc_cont_div;
  }


  /** 
   * <em>gen_credit_acc_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGenCreditAccContDivIsSet() {
    return gen_credit_acc_cont_div_is_set;
  }


  /** 
   * <em>gen_credit_acc_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGenCreditAccContDivIsModified() {
    return gen_credit_acc_cont_div_is_modified;
  }


  /** 
   * <em>bond_tentou_op_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBondTentouOpContDiv()
  {
    return bond_tentou_op_cont_div;
  }


  /** 
   * <em>bond_tentou_op_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondTentouOpContDivIsSet() {
    return bond_tentou_op_cont_div_is_set;
  }


  /** 
   * <em>bond_tentou_op_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondTentouOpContDivIsModified() {
    return bond_tentou_op_cont_div_is_modified;
  }


  /** 
   * <em>fukuoka_qb_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFukuokaQbContDiv()
  {
    return fukuoka_qb_cont_div;
  }


  /** 
   * <em>fukuoka_qb_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFukuokaQbContDivIsSet() {
    return fukuoka_qb_cont_div_is_set;
  }


  /** 
   * <em>fukuoka_qb_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFukuokaQbContDivIsModified() {
    return fukuoka_qb_cont_div_is_modified;
  }


  /** 
   * <em>dom_war_trade_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDomWarTradeContDiv()
  {
    return dom_war_trade_cont_div;
  }


  /** 
   * <em>dom_war_trade_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDomWarTradeContDivIsSet() {
    return dom_war_trade_cont_div_is_set;
  }


  /** 
   * <em>dom_war_trade_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDomWarTradeContDivIsModified() {
    return dom_war_trade_cont_div_is_modified;
  }


  /** 
   * <em>tentou_sec_basis_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTentouSecBasisContDiv()
  {
    return tentou_sec_basis_cont_div;
  }


  /** 
   * <em>tentou_sec_basis_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTentouSecBasisContDivIsSet() {
    return tentou_sec_basis_cont_div_is_set;
  }


  /** 
   * <em>tentou_sec_basis_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTentouSecBasisContDivIsModified() {
    return tentou_sec_basis_cont_div_is_modified;
  }


  /** 
   * <em>dom_for_bond_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDomForBondContDiv()
  {
    return dom_for_bond_cont_div;
  }


  /** 
   * <em>dom_for_bond_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDomForBondContDivIsSet() {
    return dom_for_bond_cont_div_is_set;
  }


  /** 
   * <em>dom_for_bond_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDomForBondContDivIsModified() {
    return dom_for_bond_cont_div_is_modified;
  }


  /** 
   * <em>tentou_sp_rule_cont_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTentouSpRuleContDiv()
  {
    return tentou_sp_rule_cont_div;
  }


  /** 
   * <em>tentou_sp_rule_cont_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTentouSpRuleContDivIsSet() {
    return tentou_sp_rule_cont_div_is_set;
  }


  /** 
   * <em>tentou_sp_rule_cont_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTentouSpRuleContDivIsModified() {
    return tentou_sp_rule_cont_div_is_modified;
  }


  /** 
   * <em>dummy16</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy16()
  {
    return dummy16;
  }


  /** 
   * <em>dummy16</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy16IsSet() {
    return dummy16_is_set;
  }


  /** 
   * <em>dummy16</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy16IsModified() {
    return dummy16_is_modified;
  }


  /** 
   * <em>dummy17</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy17()
  {
    return dummy17;
  }


  /** 
   * <em>dummy17</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy17IsSet() {
    return dummy17_is_set;
  }


  /** 
   * <em>dummy17</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy17IsModified() {
    return dummy17_is_modified;
  }


  /** 
   * <em>dummy18</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy18()
  {
    return dummy18;
  }


  /** 
   * <em>dummy18</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy18IsSet() {
    return dummy18_is_set;
  }


  /** 
   * <em>dummy18</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy18IsModified() {
    return dummy18_is_modified;
  }


  /** 
   * <em>dummy19</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy19()
  {
    return dummy19;
  }


  /** 
   * <em>dummy19</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy19IsSet() {
    return dummy19_is_set;
  }


  /** 
   * <em>dummy19</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy19IsModified() {
    return dummy19_is_modified;
  }


  /** 
   * <em>dummy20</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy20()
  {
    return dummy20;
  }


  /** 
   * <em>dummy20</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy20IsSet() {
    return dummy20_is_set;
  }


  /** 
   * <em>dummy20</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy20IsModified() {
    return dummy20_is_modified;
  }


  /** 
   * <em>mrf_contract_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMrfContractDiv()
  {
    return mrf_contract_div;
  }


  /** 
   * <em>mrf_contract_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMrfContractDivIsSet() {
    return mrf_contract_div_is_set;
  }


  /** 
   * <em>mrf_contract_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMrfContractDivIsModified() {
    return mrf_contract_div_is_modified;
  }


  /** 
   * <em>new_account_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNewAccountDiv()
  {
    return new_account_div;
  }


  /** 
   * <em>new_account_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewAccountDivIsSet() {
    return new_account_div_is_set;
  }


  /** 
   * <em>new_account_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewAccountDivIsModified() {
    return new_account_div_is_modified;
  }


  /** 
   * <em>via_trust_bank_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getViaTrustBankDiv()
  {
    return via_trust_bank_div;
  }


  /** 
   * <em>via_trust_bank_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getViaTrustBankDivIsSet() {
    return via_trust_bank_div_is_set;
  }


  /** 
   * <em>via_trust_bank_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getViaTrustBankDivIsModified() {
    return via_trust_bank_div_is_modified;
  }


  /** 
   * <em>class_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getClassDiv()
  {
    return class_div;
  }


  /** 
   * <em>class_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClassDivIsSet() {
    return class_div_is_set;
  }


  /** 
   * <em>class_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getClassDivIsModified() {
    return class_div_is_modified;
  }


  /** 
   * <em>address_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddressCode()
  {
    return address_code;
  }


  /** 
   * <em>address_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressCodeIsSet() {
    return address_code_is_set;
  }


  /** 
   * <em>address_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddressCodeIsModified() {
    return address_code_is_modified;
  }


  /** 
   * <em>person_identify</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPersonIdentify()
  {
    return person_identify;
  }


  /** 
   * <em>person_identify</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPersonIdentifyIsSet() {
    return person_identify_is_set;
  }


  /** 
   * <em>person_identify</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPersonIdentifyIsModified() {
    return person_identify_is_modified;
  }


  /** 
   * <em>cancel_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCancelDiv()
  {
    return cancel_div;
  }


  /** 
   * <em>cancel_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelDivIsSet() {
    return cancel_div_is_set;
  }


  /** 
   * <em>cancel_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCancelDivIsModified() {
    return cancel_div_is_modified;
  }


  /** 
   * <em>add_chg_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAddChgDiv()
  {
    return add_chg_div;
  }


  /** 
   * <em>add_chg_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddChgDivIsSet() {
    return add_chg_div_is_set;
  }


  /** 
   * <em>add_chg_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAddChgDivIsModified() {
    return add_chg_div_is_modified;
  }


  /** 
   * <em>reserve</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReserve()
  {
    return reserve;
  }


  /** 
   * <em>reserve</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReserveIsSet() {
    return reserve_is_set;
  }


  /** 
   * <em>reserve</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReserveIsModified() {
    return reserve_is_modified;
  }


  /** 
   * <em>org_deposit_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrgDepositDiv()
  {
    return org_deposit_div;
  }


  /** 
   * <em>org_deposit_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgDepositDivIsSet() {
    return org_deposit_div_is_set;
  }


  /** 
   * <em>org_deposit_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrgDepositDivIsModified() {
    return org_deposit_div_is_modified;
  }


  /** 
   * <em>eq_hold_rep_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEqHoldRepDiv()
  {
    return eq_hold_rep_div;
  }


  /** 
   * <em>eq_hold_rep_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEqHoldRepDivIsSet() {
    return eq_hold_rep_div_is_set;
  }


  /** 
   * <em>eq_hold_rep_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEqHoldRepDivIsModified() {
    return eq_hold_rep_div_is_modified;
  }


  /** 
   * <em>chkup_rep_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getChkupRepDiv()
  {
    return chkup_rep_div;
  }


  /** 
   * <em>chkup_rep_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChkupRepDivIsSet() {
    return chkup_rep_div_is_set;
  }


  /** 
   * <em>chkup_rep_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChkupRepDivIsModified() {
    return chkup_rep_div_is_modified;
  }


  /** 
   * <em>fax</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFax()
  {
    return fax;
  }


  /** 
   * <em>fax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFaxIsSet() {
    return fax_is_set;
  }


  /** 
   * <em>fax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFaxIsModified() {
    return fax_is_modified;
  }


  /** 
   * <em>hq_input_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHqInputDiv()
  {
    return hq_input_div;
  }


  /** 
   * <em>hq_input_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHqInputDivIsSet() {
    return hq_input_div_is_set;
  }


  /** 
   * <em>hq_input_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHqInputDivIsModified() {
    return hq_input_div_is_modified;
  }


  /** 
   * <em>yellow_customer</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getYellowCustomer()
  {
    return yellow_customer;
  }


  /** 
   * <em>yellow_customer</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getYellowCustomerIsSet() {
    return yellow_customer_is_set;
  }


  /** 
   * <em>yellow_customer</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getYellowCustomerIsModified() {
    return yellow_customer_is_modified;
  }


  /** 
   * <em>dummy21</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy21()
  {
    return dummy21;
  }


  /** 
   * <em>dummy21</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy21IsSet() {
    return dummy21_is_set;
  }


  /** 
   * <em>dummy21</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy21IsModified() {
    return dummy21_is_modified;
  }


  /** 
   * <em>dummy22</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDummy22()
  {
    return dummy22;
  }


  /** 
   * <em>dummy22</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy22IsSet() {
    return dummy22_is_set;
  }


  /** 
   * <em>dummy22</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDummy22IsModified() {
    return dummy22_is_modified;
  }


  /** 
   * <em>bond_butt_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBondButtDiv()
  {
    return bond_butt_div;
  }


  /** 
   * <em>bond_butt_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondButtDivIsSet() {
    return bond_butt_div_is_set;
  }


  /** 
   * <em>bond_butt_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondButtDivIsModified() {
    return bond_butt_div_is_modified;
  }


  /** 
   * <em>hofuri_entry</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHofuriEntry()
  {
    return hofuri_entry;
  }


  /** 
   * <em>hofuri_entry</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHofuriEntryIsSet() {
    return hofuri_entry_is_set;
  }


  /** 
   * <em>hofuri_entry</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHofuriEntryIsModified() {
    return hofuri_entry_is_modified;
  }


  /** 
   * <em>agreed_non_pkg_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAgreedNonPkgDiv()
  {
    return agreed_non_pkg_div;
  }


  /** 
   * <em>agreed_non_pkg_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgreedNonPkgDivIsSet() {
    return agreed_non_pkg_div_is_set;
  }


  /** 
   * <em>agreed_non_pkg_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgreedNonPkgDivIsModified() {
    return agreed_non_pkg_div_is_modified;
  }


  /** 
   * <em>position_report_cycle_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPositionReportCycleDiv()
  {
    return position_report_cycle_div;
  }


  /** 
   * <em>position_report_cycle_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPositionReportCycleDivIsSet() {
    return position_report_cycle_div_is_set;
  }


  /** 
   * <em>position_report_cycle_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPositionReportCycleDivIsModified() {
    return position_report_cycle_div_is_modified;
  }


  /** 
   * <em>position_report_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPositionReportDiv()
  {
    return position_report_div;
  }


  /** 
   * <em>position_report_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPositionReportDivIsSet() {
    return position_report_div_is_set;
  }


  /** 
   * <em>position_report_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPositionReportDivIsModified() {
    return position_report_div_is_modified;
  }


  /** 
   * <em>certificate_deposit_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCertificateDepositFlag()
  {
    return certificate_deposit_flag;
  }


  /** 
   * <em>certificate_deposit_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCertificateDepositFlagIsSet() {
    return certificate_deposit_flag_is_set;
  }


  /** 
   * <em>certificate_deposit_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCertificateDepositFlagIsModified() {
    return certificate_deposit_flag_is_modified;
  }


  /** 
   * <em>account_statement_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountStatementFlag()
  {
    return account_statement_flag;
  }


  /** 
   * <em>account_statement_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountStatementFlagIsSet() {
    return account_statement_flag_is_set;
  }


  /** 
   * <em>account_statement_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountStatementFlagIsModified() {
    return account_statement_flag_is_modified;
  }


  /** 
   * <em>trading_report_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradingReportDiv()
  {
    return trading_report_div;
  }


  /** 
   * <em>trading_report_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingReportDivIsSet() {
    return trading_report_div_is_set;
  }


  /** 
   * <em>trading_report_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingReportDivIsModified() {
    return trading_report_div_is_modified;
  }


  /** 
   * <em>inv_trast_ope_report</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInvTrastOpeReport()
  {
    return inv_trast_ope_report;
  }


  /** 
   * <em>inv_trast_ope_report</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInvTrastOpeReportIsSet() {
    return inv_trast_ope_report_is_set;
  }


  /** 
   * <em>inv_trast_ope_report</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInvTrastOpeReportIsModified() {
    return inv_trast_ope_report_is_modified;
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
   * <em>margin_tax_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginTaxType()
  {
    return margin_tax_type;
  }


  /** 
   * <em>margin_tax_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTaxTypeIsSet() {
    return margin_tax_type_is_set;
  }


  /** 
   * <em>margin_tax_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTaxTypeIsModified() {
    return margin_tax_type_is_modified;
  }


  /** 
   * <em>equity_sp_acc_open_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getEquitySpAccOpenDate()
  {
    return equity_sp_acc_open_date;
  }


  /** 
   * <em>equity_sp_acc_open_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquitySpAccOpenDateIsSet() {
    return equity_sp_acc_open_date_is_set;
  }


  /** 
   * <em>equity_sp_acc_open_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquitySpAccOpenDateIsModified() {
    return equity_sp_acc_open_date_is_modified;
  }


  /** 
   * <em>margin_sp_acc_open_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getMarginSpAccOpenDate()
  {
    return margin_sp_acc_open_date;
  }


  /** 
   * <em>margin_sp_acc_open_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSpAccOpenDateIsSet() {
    return margin_sp_acc_open_date_is_set;
  }


  /** 
   * <em>margin_sp_acc_open_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSpAccOpenDateIsModified() {
    return margin_sp_acc_open_date_is_modified;
  }


  /** 
   * <em>tax_type_last</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTaxTypeLast()
  {
    return tax_type_last;
  }


  /** 
   * <em>tax_type_last</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxTypeLastIsSet() {
    return tax_type_last_is_set;
  }


  /** 
   * <em>tax_type_last</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxTypeLastIsModified() {
    return tax_type_last_is_modified;
  }


  /** 
   * <em>margin_tax_type_last</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginTaxTypeLast()
  {
    return margin_tax_type_last;
  }


  /** 
   * <em>margin_tax_type_last</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTaxTypeLastIsSet() {
    return margin_tax_type_last_is_set;
  }


  /** 
   * <em>margin_tax_type_last</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTaxTypeLastIsModified() {
    return margin_tax_type_last_is_modified;
  }


  /** 
   * <em>tax_type_next</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTaxTypeNext()
  {
    return tax_type_next;
  }


  /** 
   * <em>tax_type_next</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxTypeNextIsSet() {
    return tax_type_next_is_set;
  }


  /** 
   * <em>tax_type_next</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxTypeNextIsModified() {
    return tax_type_next_is_modified;
  }


  /** 
   * <em>margin_tax_type_next</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginTaxTypeNext()
  {
    return margin_tax_type_next;
  }


  /** 
   * <em>margin_tax_type_next</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTaxTypeNextIsSet() {
    return margin_tax_type_next_is_set;
  }


  /** 
   * <em>margin_tax_type_next</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTaxTypeNextIsModified() {
    return margin_tax_type_next_is_modified;
  }


  /** 
   * <em>fluct_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getFluctDate()
  {
    return fluct_date;
  }


  /** 
   * <em>fluct_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFluctDateIsSet() {
    return fluct_date_is_set;
  }


  /** 
   * <em>fluct_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFluctDateIsModified() {
    return fluct_date_is_modified;
  }


  /** 
   * <em>margin_fluct_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getMarginFluctDate()
  {
    return margin_fluct_date;
  }


  /** 
   * <em>margin_fluct_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginFluctDateIsSet() {
    return margin_fluct_date_is_set;
  }


  /** 
   * <em>margin_fluct_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginFluctDateIsModified() {
    return margin_fluct_date_is_modified;
  }


  /** 
   * <em>local_tax_div_last</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLocalTaxDivLast()
  {
    return local_tax_div_last;
  }


  /** 
   * <em>local_tax_div_last</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLocalTaxDivLastIsSet() {
    return local_tax_div_last_is_set;
  }


  /** 
   * <em>local_tax_div_last</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLocalTaxDivLastIsModified() {
    return local_tax_div_last_is_modified;
  }


  /** 
   * <em>local_tax_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLocalTaxDiv()
  {
    return local_tax_div;
  }


  /** 
   * <em>local_tax_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLocalTaxDivIsSet() {
    return local_tax_div_is_set;
  }


  /** 
   * <em>local_tax_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLocalTaxDivIsModified() {
    return local_tax_div_is_modified;
  }


  /** 
   * <em>local_tax_div_next</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLocalTaxDivNext()
  {
    return local_tax_div_next;
  }


  /** 
   * <em>local_tax_div_next</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLocalTaxDivNextIsSet() {
    return local_tax_div_next_is_set;
  }


  /** 
   * <em>local_tax_div_next</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLocalTaxDivNextIsModified() {
    return local_tax_div_next_is_modified;
  }


  /** 
   * <em>qualified_inst_investor_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getQualifiedInstInvestorDiv()
  {
    return qualified_inst_investor_div;
  }


  /** 
   * <em>qualified_inst_investor_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQualifiedInstInvestorDivIsSet() {
    return qualified_inst_investor_div_is_set;
  }


  /** 
   * <em>qualified_inst_investor_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQualifiedInstInvestorDivIsModified() {
    return qualified_inst_investor_div_is_modified;
  }


  /** 
   * <em>quoto_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getQuotoType()
  {
    return quoto_type;
  }


  /** 
   * <em>quoto_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuotoTypeIsSet() {
    return quoto_type_is_set;
  }


  /** 
   * <em>quoto_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuotoTypeIsModified() {
    return quoto_type_is_modified;
  }


  /** 
   * <em>sp_acc_abolish_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getSpAccAbolishDate()
  {
    return sp_acc_abolish_date;
  }


  /** 
   * <em>sp_acc_abolish_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpAccAbolishDateIsSet() {
    return sp_acc_abolish_date_is_set;
  }


  /** 
   * <em>sp_acc_abolish_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpAccAbolishDateIsModified() {
    return sp_acc_abolish_date_is_modified;
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
   * <em>reserve_area</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReserveArea()
  {
    return reserve_area;
  }


  /** 
   * <em>reserve_area</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReserveAreaIsSet() {
    return reserve_area_is_set;
  }


  /** 
   * <em>reserve_area</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReserveAreaIsModified() {
    return reserve_area_is_modified;
  }


  /** 
   * <em>web3_encrypted_password</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getWeb3EncryptedPassword()
  {
    return web3_encrypted_password;
  }


  /** 
   * <em>web3_encrypted_password</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWeb3EncryptedPasswordIsSet() {
    return web3_encrypted_password_is_set;
  }


  /** 
   * <em>web3_encrypted_password</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWeb3EncryptedPasswordIsModified() {
    return web3_encrypted_password_is_modified;
  }


  /** 
   * <em>xtrade_encrypted_password</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getXtradeEncryptedPassword()
  {
    return xtrade_encrypted_password;
  }


  /** 
   * <em>xtrade_encrypted_password</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getXtradeEncryptedPasswordIsSet() {
    return xtrade_encrypted_password_is_set;
  }


  /** 
   * <em>xtrade_encrypted_password</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getXtradeEncryptedPasswordIsModified() {
    return xtrade_encrypted_password_is_modified;
  }


  /** 
   * <em>broadcast_law</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBroadcastLaw()
  {
    return broadcast_law;
  }


  /** 
   * <em>broadcast_law</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBroadcastLawIsSet() {
    return broadcast_law_is_set;
  }


  /** 
   * <em>broadcast_law</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBroadcastLawIsModified() {
    return broadcast_law_is_modified;
  }


  /** 
   * <em>aviation_law</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAviationLaw()
  {
    return aviation_law;
  }


  /** 
   * <em>aviation_law</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAviationLawIsSet() {
    return aviation_law_is_set;
  }


  /** 
   * <em>aviation_law</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAviationLawIsModified() {
    return aviation_law_is_modified;
  }


  /** 
   * <em>ntt_law</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNttLaw()
  {
    return ntt_law;
  }


  /** 
   * <em>ntt_law</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNttLawIsSet() {
    return ntt_law_is_set;
  }


  /** 
   * <em>ntt_law</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNttLawIsModified() {
    return ntt_law_is_modified;
  }


  /** 
   * <em>dividend_trans_designate</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDividendTransDesignate()
  {
    return dividend_trans_designate;
  }


  /** 
   * <em>dividend_trans_designate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDividendTransDesignateIsSet() {
    return dividend_trans_designate_is_set;
  }


  /** 
   * <em>dividend_trans_designate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDividendTransDesignateIsModified() {
    return dividend_trans_designate_is_modified;
  }


  /** 
   * <em>standing_proxy</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStandingProxy()
  {
    return standing_proxy;
  }


  /** 
   * <em>standing_proxy</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStandingProxyIsSet() {
    return standing_proxy_is_set;
  }


  /** 
   * <em>standing_proxy</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStandingProxyIsModified() {
    return standing_proxy_is_modified;
  }


  /** 
   * <em>statutory_agent</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStatutoryAgent()
  {
    return statutory_agent;
  }


  /** 
   * <em>statutory_agent</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatutoryAgentIsSet() {
    return statutory_agent_is_set;
  }


  /** 
   * <em>statutory_agent</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStatutoryAgentIsModified() {
    return statutory_agent_is_modified;
  }


  /** 
   * <em>affiliate_account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAffiliateAccountCode()
  {
    return affiliate_account_code;
  }


  /** 
   * <em>affiliate_account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAffiliateAccountCodeIsSet() {
    return affiliate_account_code_is_set;
  }


  /** 
   * <em>affiliate_account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAffiliateAccountCodeIsModified() {
    return affiliate_account_code_is_modified;
  }


  /** 
   * <em>agency_notify_cmp_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAgencyNotifyCmpDiv()
  {
    return agency_notify_cmp_div;
  }


  /** 
   * <em>agency_notify_cmp_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyNotifyCmpDivIsSet() {
    return agency_notify_cmp_div_is_set;
  }


  /** 
   * <em>agency_notify_cmp_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyNotifyCmpDivIsModified() {
    return agency_notify_cmp_div_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new MainAccountAllPK(comp_code, br_code, cust_code, cust_code_cd);
  }


  /** 
   * <em>comp_code</em>カラムの値を設定します。 
   *
   * @@param p_compCode <em>comp_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCompCode( String p_compCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comp_code = p_compCode;
    comp_code_is_set = true;
    comp_code_is_modified = true;
  }


  /** 
   * <em>gen_acc_div</em>カラムの値を設定します。 
   *
   * @@param p_genAccDiv <em>gen_acc_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGenAccDiv( String p_genAccDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gen_acc_div = p_genAccDiv;
    gen_acc_div_is_set = true;
    gen_acc_div_is_modified = true;
  }


  /** 
   * <em>gen_br_del_div</em>カラムの値を設定します。 
   *
   * @@param p_genBrDelDiv <em>gen_br_del_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGenBrDelDiv( String p_genBrDelDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gen_br_del_div = p_genBrDelDiv;
    gen_br_del_div_is_set = true;
    gen_br_del_div_is_modified = true;
  }


  /** 
   * <em>ruito_acc_open_div</em>カラムの値を設定します。 
   *
   * @@param p_ruitoAccOpenDiv <em>ruito_acc_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRuitoAccOpenDiv( String p_ruitoAccOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ruito_acc_open_div = p_ruitoAccOpenDiv;
    ruito_acc_open_div_is_set = true;
    ruito_acc_open_div_is_modified = true;
  }


  /** 
   * <em>margin_acc_open_div</em>カラムの値を設定します。 
   *
   * @@param p_marginAccOpenDiv <em>margin_acc_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarginAccOpenDiv( String p_marginAccOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_acc_open_div = p_marginAccOpenDiv;
    margin_acc_open_div_is_set = true;
    margin_acc_open_div_is_modified = true;
  }


  /** 
   * <em>when_issued_acc_open_div</em>カラムの値を設定します。 
   *
   * @@param p_whenIssuedAccOpenDiv <em>when_issued_acc_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setWhenIssuedAccOpenDiv( String p_whenIssuedAccOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    when_issued_acc_open_div = p_whenIssuedAccOpenDiv;
    when_issued_acc_open_div_is_set = true;
    when_issued_acc_open_div_is_modified = true;
  }


  /** 
   * <em>report_dispatch_stop_div</em>カラムの値を設定します。 
   *
   * @@param p_reportDispatchStopDiv <em>report_dispatch_stop_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setReportDispatchStopDiv( String p_reportDispatchStopDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    report_dispatch_stop_div = p_reportDispatchStopDiv;
    report_dispatch_stop_div_is_set = true;
    report_dispatch_stop_div_is_modified = true;
  }


  /** 
   * <em>doc_dispatch_div</em>カラムの値を設定します。 
   *
   * @@param p_docDispatchDiv <em>doc_dispatch_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDocDispatchDiv( String p_docDispatchDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    doc_dispatch_div = p_docDispatchDiv;
    doc_dispatch_div_is_set = true;
    doc_dispatch_div_is_modified = true;
  }


  /** 
   * <em>gp_br_del_div</em>カラムの値を設定します。 
   *
   * @@param p_gpBrDelDiv <em>gp_br_del_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGpBrDelDiv( String p_gpBrDelDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_br_del_div = p_gpBrDelDiv;
    gp_br_del_div_is_set = true;
    gp_br_del_div_is_modified = true;
  }


  /** 
   * <em>account_open_date</em>カラムの値を設定します。 
   *
   * @@param p_accountOpenDate <em>account_open_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setAccountOpenDate( java.sql.Timestamp p_accountOpenDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_date = p_accountOpenDate;
    account_open_date_is_set = true;
    account_open_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>account_open_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setAccountOpenDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_open_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    account_open_date_is_set = true;
    account_open_date_is_modified = true;
  }


  /** 
   * <em>last_update_date</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdateDate <em>last_update_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdateDate( java.sql.Timestamp p_lastUpdateDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_date = p_lastUpdateDate;
    last_update_date_is_set = true;
    last_update_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_update_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdateDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_update_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_update_date_is_set = true;
    last_update_date_is_modified = true;
  }


  /** 
   * <em>br_code</em>カラムの値を設定します。 
   *
   * @@param p_brCode <em>br_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setBrCode( String p_brCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    br_code = p_brCode;
    br_code_is_set = true;
    br_code_is_modified = true;
  }


  /** 
   * <em>cust_code</em>カラムの値を設定します。 
   *
   * @@param p_custCode <em>cust_code</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setCustCode( String p_custCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cust_code = p_custCode;
    cust_code_is_set = true;
    cust_code_is_modified = true;
  }


  /** 
   * <em>cust_code_cd</em>カラムの値を設定します。 
   *
   * @@param p_custCodeCd <em>cust_code_cd</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCustCodeCd( String p_custCodeCd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cust_code_cd = p_custCodeCd;
    cust_code_cd_is_set = true;
    cust_code_cd_is_modified = true;
  }


  /** 
   * <em>sonar_trader_code</em>カラムの値を設定します。 
   *
   * @@param p_sonarTraderCode <em>sonar_trader_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setSonarTraderCode( String p_sonarTraderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_trader_code = p_sonarTraderCode;
    sonar_trader_code_is_set = true;
    sonar_trader_code_is_modified = true;
  }


  /** 
   * <em>rep_dispatch_stp_bd</em>カラムの値を設定します。 
   *
   * @@param p_repDispatchStpBd <em>rep_dispatch_stp_bd</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRepDispatchStpBd( String p_repDispatchStpBd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rep_dispatch_stp_bd = p_repDispatchStpBd;
    rep_dispatch_stp_bd_is_set = true;
    rep_dispatch_stp_bd_is_modified = true;
  }


  /** 
   * <em>occupation</em>カラムの値を設定します。 
   *
   * @@param p_occupation <em>occupation</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setOccupation( String p_occupation )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    occupation = p_occupation;
    occupation_is_set = true;
    occupation_is_modified = true;
  }


  /** 
   * <em>safe_cont_open_div</em>カラムの値を設定します。 
   *
   * @@param p_safeContOpenDiv <em>safe_cont_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSafeContOpenDiv( String p_safeContOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    safe_cont_open_div = p_safeContOpenDiv;
    safe_cont_open_div_is_set = true;
    safe_cont_open_div_is_modified = true;
  }


  /** 
   * <em>foreign_sec_acc_open_div</em>カラムの値を設定します。 
   *
   * @@param p_foreignSecAccOpenDiv <em>foreign_sec_acc_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setForeignSecAccOpenDiv( String p_foreignSecAccOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_sec_acc_open_div = p_foreignSecAccOpenDiv;
    foreign_sec_acc_open_div_is_set = true;
    foreign_sec_acc_open_div_is_modified = true;
  }


  /** 
   * <em>tokuyu_acc_open_div</em>カラムの値を設定します。 
   *
   * @@param p_tokuyuAccOpenDiv <em>tokuyu_acc_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTokuyuAccOpenDiv( String p_tokuyuAccOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tokuyu_acc_open_div = p_tokuyuAccOpenDiv;
    tokuyu_acc_open_div_is_set = true;
    tokuyu_acc_open_div_is_modified = true;
  }


  /** 
   * <em>gold_acc_open_div</em>カラムの値を設定します。 
   *
   * @@param p_goldAccOpenDiv <em>gold_acc_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGoldAccOpenDiv( String p_goldAccOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gold_acc_open_div = p_goldAccOpenDiv;
    gold_acc_open_div_is_set = true;
    gold_acc_open_div_is_modified = true;
  }


  /** 
   * <em>total_trade_open_div</em>カラムの値を設定します。 
   *
   * @@param p_totalTradeOpenDiv <em>total_trade_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTotalTradeOpenDiv( String p_totalTradeOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    total_trade_open_div = p_totalTradeOpenDiv;
    total_trade_open_div_is_set = true;
    total_trade_open_div_is_modified = true;
  }


  /** 
   * <em>tie_up_loan_open_div</em>カラムの値を設定します。 
   *
   * @@param p_tieUpLoanOpenDiv <em>tie_up_loan_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTieUpLoanOpenDiv( String p_tieUpLoanOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tie_up_loan_open_div = p_tieUpLoanOpenDiv;
    tie_up_loan_open_div_is_set = true;
    tie_up_loan_open_div_is_modified = true;
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
   * <em>address_unknown</em>カラムの値を設定します。 
   *
   * @@param p_addressUnknown <em>address_unknown</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAddressUnknown( String p_addressUnknown )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address_unknown = p_addressUnknown;
    address_unknown_is_set = true;
    address_unknown_is_modified = true;
  }


  /** 
   * <em>cust_div</em>カラムの値を設定します。 
   *
   * @@param p_custDiv <em>cust_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCustDiv( String p_custDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cust_div = p_custDiv;
    cust_div_is_set = true;
    cust_div_is_modified = true;
  }


  /** 
   * <em>deposit_div</em>カラムの値を設定します。 
   *
   * @@param p_depositDiv <em>deposit_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDepositDiv( String p_depositDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deposit_div = p_depositDiv;
    deposit_div_is_set = true;
    deposit_div_is_modified = true;
  }


  /** 
   * <em>all_substitution_div</em>カラムの値を設定します。 
   *
   * @@param p_allSubstitutionDiv <em>all_substitution_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAllSubstitutionDiv( String p_allSubstitutionDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    all_substitution_div = p_allSubstitutionDiv;
    all_substitution_div_is_set = true;
    all_substitution_div_is_modified = true;
  }


  /** 
   * <em>ins_loan_bill_mthd_div</em>カラムの値を設定します。 
   *
   * @@param p_insLoanBillMthdDiv <em>ins_loan_bill_mthd_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setInsLoanBillMthdDiv( String p_insLoanBillMthdDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ins_loan_bill_mthd_div = p_insLoanBillMthdDiv;
    ins_loan_bill_mthd_div_is_set = true;
    ins_loan_bill_mthd_div_is_modified = true;
  }


  /** 
   * <em>ins_loan_cer_mthd_div</em>カラムの値を設定します。 
   *
   * @@param p_insLoanCerMthdDiv <em>ins_loan_cer_mthd_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setInsLoanCerMthdDiv( String p_insLoanCerMthdDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ins_loan_cer_mthd_div = p_insLoanCerMthdDiv;
    ins_loan_cer_mthd_div_is_set = true;
    ins_loan_cer_mthd_div_is_modified = true;
  }


  /** 
   * <em>ins_loan_clause_mthd_div</em>カラムの値を設定します。 
   *
   * @@param p_insLoanClauseMthdDiv <em>ins_loan_clause_mthd_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setInsLoanClauseMthdDiv( String p_insLoanClauseMthdDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ins_loan_clause_mthd_div = p_insLoanClauseMthdDiv;
    ins_loan_clause_mthd_div_is_set = true;
    ins_loan_clause_mthd_div_is_modified = true;
  }


  /** 
   * <em>dom_tax_div</em>カラムの値を設定します。 
   *
   * @@param p_domTaxDiv <em>dom_tax_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDomTaxDiv( String p_domTaxDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dom_tax_div = p_domTaxDiv;
    dom_tax_div_is_set = true;
    dom_tax_div_is_modified = true;
  }


  /** 
   * <em>client_trader_code</em>カラムの値を設定します。 
   *
   * @@param p_clientTraderCode <em>client_trader_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setClientTraderCode( String p_clientTraderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    client_trader_code = p_clientTraderCode;
    client_trader_code_is_set = true;
    client_trader_code_is_modified = true;
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
   * <em>family_name_alt1</em>カラムの値を設定します。 
   *
   * @@param p_familyNameAlt1 <em>family_name_alt1</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setFamilyNameAlt1( String p_familyNameAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    family_name_alt1 = p_familyNameAlt1;
    family_name_alt1_is_set = true;
    family_name_alt1_is_modified = true;
  }


  /** 
   * <em>zip_code</em>カラムの値を設定します。 
   *
   * @@param p_zipCode <em>zip_code</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setZipCode( String p_zipCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    zip_code = p_zipCode;
    zip_code_is_set = true;
    zip_code_is_modified = true;
  }


  /** 
   * <em>prefecture</em>カラムの値を設定します。 
   *
   * @@param p_prefecture <em>prefecture</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setPrefecture( String p_prefecture )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    prefecture = p_prefecture;
    prefecture_is_set = true;
    prefecture_is_modified = true;
  }


  /** 
   * <em>comma</em>カラムの値を設定します。 
   *
   * @@param p_comma <em>comma</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setComma( String p_comma )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    comma = p_comma;
    comma_is_set = true;
    comma_is_modified = true;
  }


  /** 
   * <em>address_line1_kana</em>カラムの値を設定します。 
   *
   * @@param p_addressLine1Kana <em>address_line1_kana</em>カラムの値をあらわす34桁以下のString値 
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
   * @@param p_addressLine2Kana <em>address_line2_kana</em>カラムの値をあらわす56桁以下のString値 
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
   * @@param p_addressLine3Kana <em>address_line3_kana</em>カラムの値をあらわす56桁以下のString値 
   */
  public final void setAddressLine3Kana( String p_addressLine3Kana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address_line3_kana = p_addressLine3Kana;
    address_line3_kana_is_set = true;
    address_line3_kana_is_modified = true;
  }


  /** 
   * <em>family_name</em>カラムの値を設定します。 
   *
   * @@param p_familyName <em>family_name</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setFamilyName( String p_familyName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    family_name = p_familyName;
    family_name_is_set = true;
    family_name_is_modified = true;
  }


  /** 
   * <em>address_line1</em>カラムの値を設定します。 
   *
   * @@param p_addressLine1 <em>address_line1</em>カラムの値をあらわす34桁以下のString値 
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
   * @@param p_addressLine2 <em>address_line2</em>カラムの値をあらわす34桁以下のString値 
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
   * @@param p_addressLine3 <em>address_line3</em>カラムの値をあらわす34桁以下のString値 
   */
  public final void setAddressLine3( String p_addressLine3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address_line3 = p_addressLine3;
    address_line3_is_set = true;
    address_line3_is_modified = true;
  }


  /** 
   * <em>contact_address_telephone</em>カラムの値を設定します。 
   *
   * @@param p_contactAddressTelephone <em>contact_address_telephone</em>カラムの値をあらわす16桁以下のString値 
   */
  public final void setContactAddressTelephone( String p_contactAddressTelephone )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contact_address_telephone = p_contactAddressTelephone;
    contact_address_telephone_is_set = true;
    contact_address_telephone_is_modified = true;
  }


  /** 
   * <em>contact_address</em>カラムの値を設定します。 
   *
   * @@param p_contactAddress <em>contact_address</em>カラムの値をあらわす38桁以下のString値 
   */
  public final void setContactAddress( String p_contactAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contact_address = p_contactAddress;
    contact_address_is_set = true;
    contact_address_is_modified = true;
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
   * @@param p_bornDate <em>born_date</em>カラムの値をあらわす8桁以下のString値 
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
   * <em>before_acc_trans_br_code</em>カラムの値を設定します。 
   *
   * @@param p_beforeAccTransBrCode <em>before_acc_trans_br_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setBeforeAccTransBrCode( String p_beforeAccTransBrCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    before_acc_trans_br_code = p_beforeAccTransBrCode;
    before_acc_trans_br_code_is_set = true;
    before_acc_trans_br_code_is_modified = true;
  }


  /** 
   * <em>before_acc_trans_cust_code</em>カラムの値を設定します。 
   *
   * @@param p_beforeAccTransCustCode <em>before_acc_trans_cust_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setBeforeAccTransCustCode( String p_beforeAccTransCustCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    before_acc_trans_cust_code = p_beforeAccTransCustCode;
    before_acc_trans_cust_code_is_set = true;
    before_acc_trans_cust_code_is_modified = true;
  }


  /** 
   * <em>d_card_appli_date</em>カラムの値を設定します。 
   *
   * @@param p_dCardAppliDate <em>d_card_appli_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setDCardAppliDate( java.sql.Timestamp p_dCardAppliDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    d_card_appli_date = p_dCardAppliDate;
    d_card_appli_date_is_set = true;
    d_card_appli_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>d_card_appli_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setDCardAppliDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    d_card_appli_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    d_card_appli_date_is_set = true;
    d_card_appli_date_is_modified = true;
  }


  /** 
   * <em>d_card_issue_date</em>カラムの値を設定します。 
   *
   * @@param p_dCardIssueDate <em>d_card_issue_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setDCardIssueDate( java.sql.Timestamp p_dCardIssueDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    d_card_issue_date = p_dCardIssueDate;
    d_card_issue_date_is_set = true;
    d_card_issue_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>d_card_issue_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setDCardIssueDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    d_card_issue_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    d_card_issue_date_is_set = true;
    d_card_issue_date_is_modified = true;
  }


  /** 
   * <em>d_card_password</em>カラムの値を設定します。 
   *
   * @@param p_dCardPassword <em>d_card_password</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setDCardPassword( String p_dCardPassword )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    d_card_password = p_dCardPassword;
    d_card_password_is_set = true;
    d_card_password_is_modified = true;
  }


  /** 
   * <em>d_card_issue_reason</em>カラムの値を設定します。 
   *
   * @@param p_dCardIssueReason <em>d_card_issue_reason</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDCardIssueReason( String p_dCardIssueReason )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    d_card_issue_reason = p_dCardIssueReason;
    d_card_issue_reason_is_set = true;
    d_card_issue_reason_is_modified = true;
  }


  /** 
   * <em>d_card_issue_number</em>カラムの値を設定します。 
   *
   * @@param p_dCardIssueNumber <em>d_card_issue_number</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setDCardIssueNumber( String p_dCardIssueNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    d_card_issue_number = p_dCardIssueNumber;
    d_card_issue_number_is_set = true;
    d_card_issue_number_is_modified = true;
  }


  /** 
   * <em>d_card_stop_date</em>カラムの値を設定します。 
   *
   * @@param p_dCardStopDate <em>d_card_stop_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setDCardStopDate( java.sql.Timestamp p_dCardStopDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    d_card_stop_date = p_dCardStopDate;
    d_card_stop_date_is_set = true;
    d_card_stop_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>d_card_stop_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setDCardStopDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    d_card_stop_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    d_card_stop_date_is_set = true;
    d_card_stop_date_is_modified = true;
  }


  /** 
   * <em>d_card_stop_reason</em>カラムの値を設定します。 
   *
   * @@param p_dCardStopReason <em>d_card_stop_reason</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDCardStopReason( String p_dCardStopReason )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    d_card_stop_reason = p_dCardStopReason;
    d_card_stop_reason_is_set = true;
    d_card_stop_reason_is_modified = true;
  }


  /** 
   * <em>d_card_cancel_date</em>カラムの値を設定します。 
   *
   * @@param p_dCardCancelDate <em>d_card_cancel_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setDCardCancelDate( java.sql.Timestamp p_dCardCancelDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    d_card_cancel_date = p_dCardCancelDate;
    d_card_cancel_date_is_set = true;
    d_card_cancel_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>d_card_cancel_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setDCardCancelDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    d_card_cancel_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    d_card_cancel_date_is_set = true;
    d_card_cancel_date_is_modified = true;
  }


  /** 
   * <em>d_card_cancel_reason</em>カラムの値を設定します。 
   *
   * @@param p_dCardCancelReason <em>d_card_cancel_reason</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDCardCancelReason( String p_dCardCancelReason )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    d_card_cancel_reason = p_dCardCancelReason;
    d_card_cancel_reason_is_set = true;
    d_card_cancel_reason_is_modified = true;
  }


  /** 
   * <em>d_card_name</em>カラムの値を設定します。 
   *
   * @@param p_dCardName <em>d_card_name</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setDCardName( String p_dCardName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    d_card_name = p_dCardName;
    d_card_name_is_set = true;
    d_card_name_is_modified = true;
  }


  /** 
   * <em>pass_err_code_type</em>カラムの値を設定します。 
   *
   * @@param p_passErrCodeType <em>pass_err_code_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPassErrCodeType( String p_passErrCodeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pass_err_code_type = p_passErrCodeType;
    pass_err_code_type_is_set = true;
    pass_err_code_type_is_modified = true;
  }


  /** 
   * <em>pass_err_code_number</em>カラムの値を設定します。 
   *
   * @@param p_passErrCodeNumber <em>pass_err_code_number</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPassErrCodeNumber( String p_passErrCodeNumber )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pass_err_code_number = p_passErrCodeNumber;
    pass_err_code_number_is_set = true;
    pass_err_code_number_is_modified = true;
  }


  /** 
   * <em>pass_err_code_chg_date</em>カラムの値を設定します。 
   *
   * @@param p_passErrCodeChgDate <em>pass_err_code_chg_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setPassErrCodeChgDate( java.sql.Timestamp p_passErrCodeChgDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pass_err_code_chg_date = p_passErrCodeChgDate;
    pass_err_code_chg_date_is_set = true;
    pass_err_code_chg_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>pass_err_code_chg_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setPassErrCodeChgDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    pass_err_code_chg_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    pass_err_code_chg_date_is_set = true;
    pass_err_code_chg_date_is_modified = true;
  }


  /** 
   * <em>pass_err_code_chg_time</em>カラムの値を設定します。 
   *
   * @@param p_passErrCodeChgTime <em>pass_err_code_chg_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setPassErrCodeChgTime( String p_passErrCodeChgTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pass_err_code_chg_time = p_passErrCodeChgTime;
    pass_err_code_chg_time_is_set = true;
    pass_err_code_chg_time_is_modified = true;
  }


  /** 
   * <em>ans_cust_div</em>カラムの値を設定します。 
   *
   * @@param p_ansCustDiv <em>ans_cust_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAnsCustDiv( String p_ansCustDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ans_cust_div = p_ansCustDiv;
    ans_cust_div_is_set = true;
    ans_cust_div_is_modified = true;
  }


  /** 
   * <em>ans_stock_appli_cate</em>カラムの値を設定します。 
   *
   * @@param p_ansStockAppliCate <em>ans_stock_appli_cate</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setAnsStockAppliCate( java.sql.Timestamp p_ansStockAppliCate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ans_stock_appli_cate = p_ansStockAppliCate;
    ans_stock_appli_cate_is_set = true;
    ans_stock_appli_cate_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>ans_stock_appli_cate</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setAnsStockAppliCate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ans_stock_appli_cate = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    ans_stock_appli_cate_is_set = true;
    ans_stock_appli_cate_is_modified = true;
  }


  /** 
   * <em>dummy1</em>カラムの値を設定します。 
   *
   * @@param p_dummy1 <em>dummy1</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setDummy1( String p_dummy1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy1 = p_dummy1;
    dummy1_is_set = true;
    dummy1_is_modified = true;
  }


  /** 
   * <em>self_assessed_sep_tax</em>カラムの値を設定します。 
   *
   * @@param p_selfAssessedSepTax <em>self_assessed_sep_tax</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSelfAssessedSepTax( String p_selfAssessedSepTax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    self_assessed_sep_tax = p_selfAssessedSepTax;
    self_assessed_sep_tax_is_set = true;
    self_assessed_sep_tax_is_modified = true;
  }


  /** 
   * <em>tokuyu_appli_div</em>カラムの値を設定します。 
   *
   * @@param p_tokuyuAppliDiv <em>tokuyu_appli_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTokuyuAppliDiv( String p_tokuyuAppliDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tokuyu_appli_div = p_tokuyuAppliDiv;
    tokuyu_appli_div_is_set = true;
    tokuyu_appli_div_is_modified = true;
  }


  /** 
   * <em>maruyu_appli_div</em>カラムの値を設定します。 
   *
   * @@param p_maruyuAppliDiv <em>maruyu_appli_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMaruyuAppliDiv( String p_maruyuAppliDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    maruyu_appli_div = p_maruyuAppliDiv;
    maruyu_appli_div_is_set = true;
    maruyu_appli_div_is_modified = true;
  }


  /** 
   * <em>tokuyu_laundering_div</em>カラムの値を設定します。 
   *
   * @@param p_tokuyuLaunderingDiv <em>tokuyu_laundering_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTokuyuLaunderingDiv( String p_tokuyuLaunderingDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tokuyu_laundering_div = p_tokuyuLaunderingDiv;
    tokuyu_laundering_div_is_set = true;
    tokuyu_laundering_div_is_modified = true;
  }


  /** 
   * <em>maruyu_laundering_div</em>カラムの値を設定します。 
   *
   * @@param p_maruyuLaunderingDiv <em>maruyu_laundering_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMaruyuLaunderingDiv( String p_maruyuLaunderingDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    maruyu_laundering_div = p_maruyuLaunderingDiv;
    maruyu_laundering_div_is_set = true;
    maruyu_laundering_div_is_modified = true;
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
   * <em>dummy2</em>カラムの値を設定します。 
   *
   * @@param p_dummy2 <em>dummy2</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDummy2( String p_dummy2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy2 = p_dummy2;
    dummy2_is_set = true;
    dummy2_is_modified = true;
  }


  /** 
   * <em>ht_settlement_way</em>カラムの値を設定します。 
   *
   * @@param p_htSettlementWay <em>ht_settlement_way</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setHtSettlementWay( String p_htSettlementWay )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ht_settlement_way = p_htSettlementWay;
    ht_settlement_way_is_set = true;
    ht_settlement_way_is_modified = true;
  }


  /** 
   * <em>dummy3</em>カラムの値を設定します。 
   *
   * @@param p_dummy3 <em>dummy3</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDummy3( String p_dummy3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy3 = p_dummy3;
    dummy3_is_set = true;
    dummy3_is_modified = true;
  }


  /** 
   * <em>total_tax_identity</em>カラムの値を設定します。 
   *
   * @@param p_totalTaxIdentity <em>total_tax_identity</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTotalTaxIdentity( String p_totalTaxIdentity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    total_tax_identity = p_totalTaxIdentity;
    total_tax_identity_is_set = true;
    total_tax_identity_is_modified = true;
  }


  /** 
   * <em>dummy4</em>カラムの値を設定します。 
   *
   * @@param p_dummy4 <em>dummy4</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDummy4( String p_dummy4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy4 = p_dummy4;
    dummy4_is_set = true;
    dummy4_is_modified = true;
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
   * <em>mort_trade_open_div</em>カラムの値を設定します。 
   *
   * @@param p_mortTradeOpenDiv <em>mort_trade_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMortTradeOpenDiv( String p_mortTradeOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mort_trade_open_div = p_mortTradeOpenDiv;
    mort_trade_open_div_is_set = true;
    mort_trade_open_div_is_modified = true;
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
   * <em>os_fnc_futures_open_div</em>カラムの値を設定します。 
   *
   * @@param p_osFncFuturesOpenDiv <em>os_fnc_futures_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOsFncFuturesOpenDiv( String p_osFncFuturesOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    os_fnc_futures_open_div = p_osFncFuturesOpenDiv;
    os_fnc_futures_open_div_is_set = true;
    os_fnc_futures_open_div_is_modified = true;
  }


  /** 
   * <em>os_sec_futures_open_div</em>カラムの値を設定します。 
   *
   * @@param p_osSecFuturesOpenDiv <em>os_sec_futures_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOsSecFuturesOpenDiv( String p_osSecFuturesOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    os_sec_futures_open_div = p_osSecFuturesOpenDiv;
    os_sec_futures_open_div_is_set = true;
    os_sec_futures_open_div_is_modified = true;
  }


  /** 
   * <em>tokyo_fnc_futures_open_div</em>カラムの値を設定します。 
   *
   * @@param p_tokyoFncFuturesOpenDiv <em>tokyo_fnc_futures_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTokyoFncFuturesOpenDiv( String p_tokyoFncFuturesOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tokyo_fnc_futures_open_div = p_tokyoFncFuturesOpenDiv;
    tokyo_fnc_futures_open_div_is_set = true;
    tokyo_fnc_futures_open_div_is_modified = true;
  }


  /** 
   * <em>for_war_trade_open_div</em>カラムの値を設定します。 
   *
   * @@param p_forWarTradeOpenDiv <em>for_war_trade_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setForWarTradeOpenDiv( String p_forWarTradeOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    for_war_trade_open_div = p_forWarTradeOpenDiv;
    for_war_trade_open_div_is_set = true;
    for_war_trade_open_div_is_modified = true;
  }


  /** 
   * <em>tentou_trade_open_div</em>カラムの値を設定します。 
   *
   * @@param p_tentouTradeOpenDiv <em>tentou_trade_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTentouTradeOpenDiv( String p_tentouTradeOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tentou_trade_open_div = p_tentouTradeOpenDiv;
    tentou_trade_open_div_is_set = true;
    tentou_trade_open_div_is_modified = true;
  }


  /** 
   * <em>represent_div</em>カラムの値を設定します。 
   *
   * @@param p_representDiv <em>represent_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRepresentDiv( String p_representDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    represent_div = p_representDiv;
    represent_div_is_set = true;
    represent_div_is_modified = true;
  }


  /** 
   * <em>family_nurturer_code</em>カラムの値を設定します。 
   *
   * @@param p_familyNurturerCode <em>family_nurturer_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setFamilyNurturerCode( String p_familyNurturerCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    family_nurturer_code = p_familyNurturerCode;
    family_nurturer_code_is_set = true;
    family_nurturer_code_is_modified = true;
  }


  /** 
   * <em>family_unit_adimin</em>カラムの値を設定します。 
   *
   * @@param p_familyUnitAdimin <em>family_unit_adimin</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFamilyUnitAdimin( String p_familyUnitAdimin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    family_unit_adimin = p_familyUnitAdimin;
    family_unit_adimin_is_set = true;
    family_unit_adimin_is_modified = true;
  }


  /** 
   * <em>new_monthly_div</em>カラムの値を設定します。 
   *
   * @@param p_newMonthlyDiv <em>new_monthly_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setNewMonthlyDiv( String p_newMonthlyDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_monthly_div = p_newMonthlyDiv;
    new_monthly_div_is_set = true;
    new_monthly_div_is_modified = true;
  }


  /** 
   * <em>statement_skip1_div</em>カラムの値を設定します。 
   *
   * @@param p_statementSkip1Div <em>statement_skip1_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStatementSkip1Div( String p_statementSkip1Div )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    statement_skip1_div = p_statementSkip1Div;
    statement_skip1_div_is_set = true;
    statement_skip1_div_is_modified = true;
  }


  /** 
   * <em>statement_skip2_div</em>カラムの値を設定します。 
   *
   * @@param p_statementSkip2Div <em>statement_skip2_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStatementSkip2Div( String p_statementSkip2Div )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    statement_skip2_div = p_statementSkip2Div;
    statement_skip2_div_is_set = true;
    statement_skip2_div_is_modified = true;
  }


  /** 
   * <em>family_unit_del_div</em>カラムの値を設定します。 
   *
   * @@param p_familyUnitDelDiv <em>family_unit_del_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFamilyUnitDelDiv( String p_familyUnitDelDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    family_unit_del_div = p_familyUnitDelDiv;
    family_unit_del_div_is_set = true;
    family_unit_del_div_is_modified = true;
  }


  /** 
   * <em>dummy5</em>カラムの値を設定します。 
   *
   * @@param p_dummy5 <em>dummy5</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setDummy5( String p_dummy5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy5 = p_dummy5;
    dummy5_is_set = true;
    dummy5_is_modified = true;
  }


  /** 
   * <em>br_dispatch_div</em>カラムの値を設定します。 
   *
   * @@param p_brDispatchDiv <em>br_dispatch_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBrDispatchDiv( String p_brDispatchDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    br_dispatch_div = p_brDispatchDiv;
    br_dispatch_div_is_set = true;
    br_dispatch_div_is_modified = true;
  }


  /** 
   * <em>maruyu_grade_div</em>カラムの値を設定します。 
   *
   * @@param p_maruyuGradeDiv <em>maruyu_grade_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMaruyuGradeDiv( String p_maruyuGradeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    maruyu_grade_div = p_maruyuGradeDiv;
    maruyu_grade_div_is_set = true;
    maruyu_grade_div_is_modified = true;
  }


  /** 
   * <em>dummy6</em>カラムの値を設定します。 
   *
   * @@param p_dummy6 <em>dummy6</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setDummy6( String p_dummy6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy6 = p_dummy6;
    dummy6_is_set = true;
    dummy6_is_modified = true;
  }


  /** 
   * <em>dummy7</em>カラムの値を設定します。 
   *
   * @@param p_dummy7 <em>dummy7</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setDummy7( String p_dummy7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy7 = p_dummy7;
    dummy7_is_set = true;
    dummy7_is_modified = true;
  }


  /** 
   * <em>trans_tax_div_acc_date</em>カラムの値を設定します。 
   *
   * @@param p_transTaxDivAccDate <em>trans_tax_div_acc_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTransTaxDivAccDate( java.sql.Timestamp p_transTaxDivAccDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trans_tax_div_acc_date = p_transTaxDivAccDate;
    trans_tax_div_acc_date_is_set = true;
    trans_tax_div_acc_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>trans_tax_div_acc_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTransTaxDivAccDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trans_tax_div_acc_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    trans_tax_div_acc_date_is_set = true;
    trans_tax_div_acc_date_is_modified = true;
  }


  /** 
   * <em>trans_tax_div</em>カラムの値を設定します。 
   *
   * @@param p_transTaxDiv <em>trans_tax_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTransTaxDiv( String p_transTaxDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trans_tax_div = p_transTaxDiv;
    trans_tax_div_is_set = true;
    trans_tax_div_is_modified = true;
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
   * <em>bond_d_and_c_open_div</em>カラムの値を設定します。 
   *
   * @@param p_bondDAndCOpenDiv <em>bond_d_and_c_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBondDAndCOpenDiv( String p_bondDAndCOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bond_d_and_c_open_div = p_bondDAndCOpenDiv;
    bond_d_and_c_open_div_is_set = true;
    bond_d_and_c_open_div_is_modified = true;
  }


  /** 
   * <em>os_cd_cp_open_div</em>カラムの値を設定します。 
   *
   * @@param p_osCdCpOpenDiv <em>os_cd_cp_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOsCdCpOpenDiv( String p_osCdCpOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    os_cd_cp_open_div = p_osCdCpOpenDiv;
    os_cd_cp_open_div_is_set = true;
    os_cd_cp_open_div_is_modified = true;
  }


  /** 
   * <em>new_tb_open_div</em>カラムの値を設定します。 
   *
   * @@param p_newTbOpenDiv <em>new_tb_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setNewTbOpenDiv( String p_newTbOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_tb_open_div = p_newTbOpenDiv;
    new_tb_open_div_is_set = true;
    new_tb_open_div_is_modified = true;
  }


  /** 
   * <em>dom_cp_open_div</em>カラムの値を設定します。 
   *
   * @@param p_domCpOpenDiv <em>dom_cp_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDomCpOpenDiv( String p_domCpOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dom_cp_open_div = p_domCpOpenDiv;
    dom_cp_open_div_is_set = true;
    dom_cp_open_div_is_modified = true;
  }


  /** 
   * <em>bond_tentou_op_open_div</em>カラムの値を設定します。 
   *
   * @@param p_bondTentouOpOpenDiv <em>bond_tentou_op_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBondTentouOpOpenDiv( String p_bondTentouOpOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bond_tentou_op_open_div = p_bondTentouOpOpenDiv;
    bond_tentou_op_open_div_is_set = true;
    bond_tentou_op_open_div_is_modified = true;
  }


  /** 
   * <em>t_bond_futures_open_div</em>カラムの値を設定します。 
   *
   * @@param p_tBondFuturesOpenDiv <em>t_bond_futures_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTBondFuturesOpenDiv( String p_tBondFuturesOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    t_bond_futures_open_div = p_tBondFuturesOpenDiv;
    t_bond_futures_open_div_is_set = true;
    t_bond_futures_open_div_is_modified = true;
  }


  /** 
   * <em>dom_war_trade_open_div</em>カラムの値を設定します。 
   *
   * @@param p_domWarTradeOpenDiv <em>dom_war_trade_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDomWarTradeOpenDiv( String p_domWarTradeOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dom_war_trade_open_div = p_domWarTradeOpenDiv;
    dom_war_trade_open_div_is_set = true;
    dom_war_trade_open_div_is_modified = true;
  }


  /** 
   * <em>dummy8</em>カラムの値を設定します。 
   *
   * @@param p_dummy8 <em>dummy8</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDummy8( String p_dummy8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy8 = p_dummy8;
    dummy8_is_set = true;
    dummy8_is_modified = true;
  }


  /** 
   * <em>dom_for_bond_open_div</em>カラムの値を設定します。 
   *
   * @@param p_domForBondOpenDiv <em>dom_for_bond_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDomForBondOpenDiv( String p_domForBondOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dom_for_bond_open_div = p_domForBondOpenDiv;
    dom_for_bond_open_div_is_set = true;
    dom_for_bond_open_div_is_modified = true;
  }


  /** 
   * <em>tentou_sp_rule_open_div</em>カラムの値を設定します。 
   *
   * @@param p_tentouSpRuleOpenDiv <em>tentou_sp_rule_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTentouSpRuleOpenDiv( String p_tentouSpRuleOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tentou_sp_rule_open_div = p_tentouSpRuleOpenDiv;
    tentou_sp_rule_open_div_is_set = true;
    tentou_sp_rule_open_div_is_modified = true;
  }


  /** 
   * <em>dummy9</em>カラムの値を設定します。 
   *
   * @@param p_dummy9 <em>dummy9</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDummy9( String p_dummy9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy9 = p_dummy9;
    dummy9_is_set = true;
    dummy9_is_modified = true;
  }


  /** 
   * <em>dummy10</em>カラムの値を設定します。 
   *
   * @@param p_dummy10 <em>dummy10</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDummy10( String p_dummy10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy10 = p_dummy10;
    dummy10_is_set = true;
    dummy10_is_modified = true;
  }


  /** 
   * <em>dummy11</em>カラムの値を設定します。 
   *
   * @@param p_dummy11 <em>dummy11</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDummy11( String p_dummy11 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy11 = p_dummy11;
    dummy11_is_set = true;
    dummy11_is_modified = true;
  }


  /** 
   * <em>dummy12</em>カラムの値を設定します。 
   *
   * @@param p_dummy12 <em>dummy12</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDummy12( String p_dummy12 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy12 = p_dummy12;
    dummy12_is_set = true;
    dummy12_is_modified = true;
  }


  /** 
   * <em>dummy13</em>カラムの値を設定します。 
   *
   * @@param p_dummy13 <em>dummy13</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDummy13( String p_dummy13 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy13 = p_dummy13;
    dummy13_is_set = true;
    dummy13_is_modified = true;
  }


  /** 
   * <em>mrf_acc_open_div</em>カラムの値を設定します。 
   *
   * @@param p_mrfAccOpenDiv <em>mrf_acc_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMrfAccOpenDiv( String p_mrfAccOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mrf_acc_open_div = p_mrfAccOpenDiv;
    mrf_acc_open_div_is_set = true;
    mrf_acc_open_div_is_modified = true;
  }


  /** 
   * <em>safe_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_safeContDiv <em>safe_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSafeContDiv( String p_safeContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    safe_cont_div = p_safeContDiv;
    safe_cont_div_is_set = true;
    safe_cont_div_is_modified = true;
  }


  /** 
   * <em>foreign_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_foreignContDiv <em>foreign_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setForeignContDiv( String p_foreignContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_cont_div = p_foreignContDiv;
    foreign_cont_div_is_set = true;
    foreign_cont_div_is_modified = true;
  }


  /** 
   * <em>dummy14</em>カラムの値を設定します。 
   *
   * @@param p_dummy14 <em>dummy14</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDummy14( String p_dummy14 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy14 = p_dummy14;
    dummy14_is_set = true;
    dummy14_is_modified = true;
  }


  /** 
   * <em>gold_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_goldContDiv <em>gold_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGoldContDiv( String p_goldContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gold_cont_div = p_goldContDiv;
    gold_cont_div_is_set = true;
    gold_cont_div_is_modified = true;
  }


  /** 
   * <em>margin_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_marginContDiv <em>margin_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarginContDiv( String p_marginContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_cont_div = p_marginContDiv;
    margin_cont_div_is_set = true;
    margin_cont_div_is_modified = true;
  }


  /** 
   * <em>when_issued_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_whenIssuedContDiv <em>when_issued_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setWhenIssuedContDiv( String p_whenIssuedContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    when_issued_cont_div = p_whenIssuedContDiv;
    when_issued_cont_div_is_set = true;
    when_issued_cont_div_is_modified = true;
  }


  /** 
   * <em>futures_op_cont_div_tokyo</em>カラムの値を設定します。 
   *
   * @@param p_futuresOpContDivTokyo <em>futures_op_cont_div_tokyo</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFuturesOpContDivTokyo( String p_futuresOpContDivTokyo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    futures_op_cont_div_tokyo = p_futuresOpContDivTokyo;
    futures_op_cont_div_tokyo_is_set = true;
    futures_op_cont_div_tokyo_is_modified = true;
  }


  /** 
   * <em>dummy15</em>カラムの値を設定します。 
   *
   * @@param p_dummy15 <em>dummy15</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDummy15( String p_dummy15 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy15 = p_dummy15;
    dummy15_is_set = true;
    dummy15_is_modified = true;
  }


  /** 
   * <em>futures_op_cont_div_osaka</em>カラムの値を設定します。 
   *
   * @@param p_futuresOpContDivOsaka <em>futures_op_cont_div_osaka</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFuturesOpContDivOsaka( String p_futuresOpContDivOsaka )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    futures_op_cont_div_osaka = p_futuresOpContDivOsaka;
    futures_op_cont_div_osaka_is_set = true;
    futures_op_cont_div_osaka_is_modified = true;
  }


  /** 
   * <em>tokyo_mothers_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_tokyoMothersContDiv <em>tokyo_mothers_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTokyoMothersContDiv( String p_tokyoMothersContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tokyo_mothers_cont_div = p_tokyoMothersContDiv;
    tokyo_mothers_cont_div_is_set = true;
    tokyo_mothers_cont_div_is_modified = true;
  }


  /** 
   * <em>futures_op_cont_div_nagoya</em>カラムの値を設定します。 
   *
   * @@param p_futuresOpContDivNagoya <em>futures_op_cont_div_nagoya</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFuturesOpContDivNagoya( String p_futuresOpContDivNagoya )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    futures_op_cont_div_nagoya = p_futuresOpContDivNagoya;
    futures_op_cont_div_nagoya_is_set = true;
    futures_op_cont_div_nagoya_is_modified = true;
  }


  /** 
   * <em>nq_j_gl_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_nqJGlContDiv <em>nq_j_gl_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setNqJGlContDiv( String p_nqJGlContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    nq_j_gl_cont_div = p_nqJGlContDiv;
    nq_j_gl_cont_div_is_set = true;
    nq_j_gl_cont_div_is_modified = true;
  }


  /** 
   * <em>osaka_nw_mkt_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_osakaNwMktContDiv <em>osaka_nw_mkt_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOsakaNwMktContDiv( String p_osakaNwMktContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    osaka_nw_mkt_cont_div = p_osakaNwMktContDiv;
    osaka_nw_mkt_cont_div_is_set = true;
    osaka_nw_mkt_cont_div_is_modified = true;
  }


  /** 
   * <em>nagoya_grw_cpy_mkt_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_nagoyaGrwCpyMktContDiv <em>nagoya_grw_cpy_mkt_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setNagoyaGrwCpyMktContDiv( String p_nagoyaGrwCpyMktContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    nagoya_grw_cpy_mkt_cont_div = p_nagoyaGrwCpyMktContDiv;
    nagoya_grw_cpy_mkt_cont_div_is_set = true;
    nagoya_grw_cpy_mkt_cont_div_is_modified = true;
  }


  /** 
   * <em>for_war_trade_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_forWarTradeContDiv <em>for_war_trade_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setForWarTradeContDiv( String p_forWarTradeContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    for_war_trade_cont_div = p_forWarTradeContDiv;
    for_war_trade_cont_div_is_set = true;
    for_war_trade_cont_div_is_modified = true;
  }


  /** 
   * <em>tentou_trade_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_tentouTradeContDiv <em>tentou_trade_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTentouTradeContDiv( String p_tentouTradeContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tentou_trade_cont_div = p_tentouTradeContDiv;
    tentou_trade_cont_div_is_set = true;
    tentou_trade_cont_div_is_modified = true;
  }


  /** 
   * <em>bond_d_and_c_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_bondDAndCContDiv <em>bond_d_and_c_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBondDAndCContDiv( String p_bondDAndCContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bond_d_and_c_cont_div = p_bondDAndCContDiv;
    bond_d_and_c_cont_div_is_set = true;
    bond_d_and_c_cont_div_is_modified = true;
  }


  /** 
   * <em>sapporo_amb_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_sapporoAmbContDiv <em>sapporo_amb_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSapporoAmbContDiv( String p_sapporoAmbContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sapporo_amb_cont_div = p_sapporoAmbContDiv;
    sapporo_amb_cont_div_is_set = true;
    sapporo_amb_cont_div_is_modified = true;
  }


  /** 
   * <em>new_tb_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_newTbContDiv <em>new_tb_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setNewTbContDiv( String p_newTbContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_tb_cont_div = p_newTbContDiv;
    new_tb_cont_div_is_set = true;
    new_tb_cont_div_is_modified = true;
  }


  /** 
   * <em>gen_credit_acc_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_genCreditAccContDiv <em>gen_credit_acc_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGenCreditAccContDiv( String p_genCreditAccContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gen_credit_acc_cont_div = p_genCreditAccContDiv;
    gen_credit_acc_cont_div_is_set = true;
    gen_credit_acc_cont_div_is_modified = true;
  }


  /** 
   * <em>bond_tentou_op_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_bondTentouOpContDiv <em>bond_tentou_op_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBondTentouOpContDiv( String p_bondTentouOpContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bond_tentou_op_cont_div = p_bondTentouOpContDiv;
    bond_tentou_op_cont_div_is_set = true;
    bond_tentou_op_cont_div_is_modified = true;
  }


  /** 
   * <em>fukuoka_qb_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_fukuokaQbContDiv <em>fukuoka_qb_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFukuokaQbContDiv( String p_fukuokaQbContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fukuoka_qb_cont_div = p_fukuokaQbContDiv;
    fukuoka_qb_cont_div_is_set = true;
    fukuoka_qb_cont_div_is_modified = true;
  }


  /** 
   * <em>dom_war_trade_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_domWarTradeContDiv <em>dom_war_trade_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDomWarTradeContDiv( String p_domWarTradeContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dom_war_trade_cont_div = p_domWarTradeContDiv;
    dom_war_trade_cont_div_is_set = true;
    dom_war_trade_cont_div_is_modified = true;
  }


  /** 
   * <em>tentou_sec_basis_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_tentouSecBasisContDiv <em>tentou_sec_basis_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTentouSecBasisContDiv( String p_tentouSecBasisContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tentou_sec_basis_cont_div = p_tentouSecBasisContDiv;
    tentou_sec_basis_cont_div_is_set = true;
    tentou_sec_basis_cont_div_is_modified = true;
  }


  /** 
   * <em>dom_for_bond_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_domForBondContDiv <em>dom_for_bond_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDomForBondContDiv( String p_domForBondContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dom_for_bond_cont_div = p_domForBondContDiv;
    dom_for_bond_cont_div_is_set = true;
    dom_for_bond_cont_div_is_modified = true;
  }


  /** 
   * <em>tentou_sp_rule_cont_div</em>カラムの値を設定します。 
   *
   * @@param p_tentouSpRuleContDiv <em>tentou_sp_rule_cont_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTentouSpRuleContDiv( String p_tentouSpRuleContDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tentou_sp_rule_cont_div = p_tentouSpRuleContDiv;
    tentou_sp_rule_cont_div_is_set = true;
    tentou_sp_rule_cont_div_is_modified = true;
  }


  /** 
   * <em>dummy16</em>カラムの値を設定します。 
   *
   * @@param p_dummy16 <em>dummy16</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDummy16( String p_dummy16 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy16 = p_dummy16;
    dummy16_is_set = true;
    dummy16_is_modified = true;
  }


  /** 
   * <em>dummy17</em>カラムの値を設定します。 
   *
   * @@param p_dummy17 <em>dummy17</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDummy17( String p_dummy17 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy17 = p_dummy17;
    dummy17_is_set = true;
    dummy17_is_modified = true;
  }


  /** 
   * <em>dummy18</em>カラムの値を設定します。 
   *
   * @@param p_dummy18 <em>dummy18</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDummy18( String p_dummy18 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy18 = p_dummy18;
    dummy18_is_set = true;
    dummy18_is_modified = true;
  }


  /** 
   * <em>dummy19</em>カラムの値を設定します。 
   *
   * @@param p_dummy19 <em>dummy19</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDummy19( String p_dummy19 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy19 = p_dummy19;
    dummy19_is_set = true;
    dummy19_is_modified = true;
  }


  /** 
   * <em>dummy20</em>カラムの値を設定します。 
   *
   * @@param p_dummy20 <em>dummy20</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDummy20( String p_dummy20 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy20 = p_dummy20;
    dummy20_is_set = true;
    dummy20_is_modified = true;
  }


  /** 
   * <em>mrf_contract_div</em>カラムの値を設定します。 
   *
   * @@param p_mrfContractDiv <em>mrf_contract_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMrfContractDiv( String p_mrfContractDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mrf_contract_div = p_mrfContractDiv;
    mrf_contract_div_is_set = true;
    mrf_contract_div_is_modified = true;
  }


  /** 
   * <em>new_account_div</em>カラムの値を設定します。 
   *
   * @@param p_newAccountDiv <em>new_account_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setNewAccountDiv( String p_newAccountDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_account_div = p_newAccountDiv;
    new_account_div_is_set = true;
    new_account_div_is_modified = true;
  }


  /** 
   * <em>via_trust_bank_div</em>カラムの値を設定します。 
   *
   * @@param p_viaTrustBankDiv <em>via_trust_bank_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setViaTrustBankDiv( String p_viaTrustBankDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    via_trust_bank_div = p_viaTrustBankDiv;
    via_trust_bank_div_is_set = true;
    via_trust_bank_div_is_modified = true;
  }


  /** 
   * <em>class_div</em>カラムの値を設定します。 
   *
   * @@param p_classDiv <em>class_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setClassDiv( String p_classDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    class_div = p_classDiv;
    class_div_is_set = true;
    class_div_is_modified = true;
  }


  /** 
   * <em>address_code</em>カラムの値を設定します。 
   *
   * @@param p_addressCode <em>address_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setAddressCode( String p_addressCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    address_code = p_addressCode;
    address_code_is_set = true;
    address_code_is_modified = true;
  }


  /** 
   * <em>person_identify</em>カラムの値を設定します。 
   *
   * @@param p_personIdentify <em>person_identify</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPersonIdentify( String p_personIdentify )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    person_identify = p_personIdentify;
    person_identify_is_set = true;
    person_identify_is_modified = true;
  }


  /** 
   * <em>cancel_div</em>カラムの値を設定します。 
   *
   * @@param p_cancelDiv <em>cancel_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCancelDiv( String p_cancelDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cancel_div = p_cancelDiv;
    cancel_div_is_set = true;
    cancel_div_is_modified = true;
  }


  /** 
   * <em>add_chg_div</em>カラムの値を設定します。 
   *
   * @@param p_addChgDiv <em>add_chg_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAddChgDiv( String p_addChgDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    add_chg_div = p_addChgDiv;
    add_chg_div_is_set = true;
    add_chg_div_is_modified = true;
  }


  /** 
   * <em>reserve</em>カラムの値を設定します。 
   *
   * @@param p_reserve <em>reserve</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setReserve( String p_reserve )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reserve = p_reserve;
    reserve_is_set = true;
    reserve_is_modified = true;
  }


  /** 
   * <em>org_deposit_div</em>カラムの値を設定します。 
   *
   * @@param p_orgDepositDiv <em>org_deposit_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrgDepositDiv( String p_orgDepositDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    org_deposit_div = p_orgDepositDiv;
    org_deposit_div_is_set = true;
    org_deposit_div_is_modified = true;
  }


  /** 
   * <em>eq_hold_rep_div</em>カラムの値を設定します。 
   *
   * @@param p_eqHoldRepDiv <em>eq_hold_rep_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEqHoldRepDiv( String p_eqHoldRepDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    eq_hold_rep_div = p_eqHoldRepDiv;
    eq_hold_rep_div_is_set = true;
    eq_hold_rep_div_is_modified = true;
  }


  /** 
   * <em>chkup_rep_div</em>カラムの値を設定します。 
   *
   * @@param p_chkupRepDiv <em>chkup_rep_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setChkupRepDiv( String p_chkupRepDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    chkup_rep_div = p_chkupRepDiv;
    chkup_rep_div_is_set = true;
    chkup_rep_div_is_modified = true;
  }


  /** 
   * <em>fax</em>カラムの値を設定します。 
   *
   * @@param p_fax <em>fax</em>カラムの値をあらわす16桁以下のString値 
   */
  public final void setFax( String p_fax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fax = p_fax;
    fax_is_set = true;
    fax_is_modified = true;
  }


  /** 
   * <em>hq_input_div</em>カラムの値を設定します。 
   *
   * @@param p_hqInputDiv <em>hq_input_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setHqInputDiv( String p_hqInputDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    hq_input_div = p_hqInputDiv;
    hq_input_div_is_set = true;
    hq_input_div_is_modified = true;
  }


  /** 
   * <em>yellow_customer</em>カラムの値を設定します。 
   *
   * @@param p_yellowCustomer <em>yellow_customer</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setYellowCustomer( String p_yellowCustomer )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    yellow_customer = p_yellowCustomer;
    yellow_customer_is_set = true;
    yellow_customer_is_modified = true;
  }


  /** 
   * <em>dummy21</em>カラムの値を設定します。 
   *
   * @@param p_dummy21 <em>dummy21</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setDummy21( String p_dummy21 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy21 = p_dummy21;
    dummy21_is_set = true;
    dummy21_is_modified = true;
  }


  /** 
   * <em>dummy22</em>カラムの値を設定します。 
   *
   * @@param p_dummy22 <em>dummy22</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setDummy22( String p_dummy22 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dummy22 = p_dummy22;
    dummy22_is_set = true;
    dummy22_is_modified = true;
  }


  /** 
   * <em>bond_butt_div</em>カラムの値を設定します。 
   *
   * @@param p_bondButtDiv <em>bond_butt_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBondButtDiv( String p_bondButtDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bond_butt_div = p_bondButtDiv;
    bond_butt_div_is_set = true;
    bond_butt_div_is_modified = true;
  }


  /** 
   * <em>hofuri_entry</em>カラムの値を設定します。 
   *
   * @@param p_hofuriEntry <em>hofuri_entry</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setHofuriEntry( String p_hofuriEntry )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    hofuri_entry = p_hofuriEntry;
    hofuri_entry_is_set = true;
    hofuri_entry_is_modified = true;
  }


  /** 
   * <em>agreed_non_pkg_div</em>カラムの値を設定します。 
   *
   * @@param p_agreedNonPkgDiv <em>agreed_non_pkg_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAgreedNonPkgDiv( String p_agreedNonPkgDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agreed_non_pkg_div = p_agreedNonPkgDiv;
    agreed_non_pkg_div_is_set = true;
    agreed_non_pkg_div_is_modified = true;
  }


  /** 
   * <em>position_report_cycle_div</em>カラムの値を設定します。 
   *
   * @@param p_positionReportCycleDiv <em>position_report_cycle_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPositionReportCycleDiv( String p_positionReportCycleDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    position_report_cycle_div = p_positionReportCycleDiv;
    position_report_cycle_div_is_set = true;
    position_report_cycle_div_is_modified = true;
  }


  /** 
   * <em>position_report_div</em>カラムの値を設定します。 
   *
   * @@param p_positionReportDiv <em>position_report_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPositionReportDiv( String p_positionReportDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    position_report_div = p_positionReportDiv;
    position_report_div_is_set = true;
    position_report_div_is_modified = true;
  }


  /** 
   * <em>certificate_deposit_flag</em>カラムの値を設定します。 
   *
   * @@param p_certificateDepositFlag <em>certificate_deposit_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCertificateDepositFlag( String p_certificateDepositFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    certificate_deposit_flag = p_certificateDepositFlag;
    certificate_deposit_flag_is_set = true;
    certificate_deposit_flag_is_modified = true;
  }


  /** 
   * <em>account_statement_flag</em>カラムの値を設定します。 
   *
   * @@param p_accountStatementFlag <em>account_statement_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountStatementFlag( String p_accountStatementFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_statement_flag = p_accountStatementFlag;
    account_statement_flag_is_set = true;
    account_statement_flag_is_modified = true;
  }


  /** 
   * <em>trading_report_div</em>カラムの値を設定します。 
   *
   * @@param p_tradingReportDiv <em>trading_report_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTradingReportDiv( String p_tradingReportDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_report_div = p_tradingReportDiv;
    trading_report_div_is_set = true;
    trading_report_div_is_modified = true;
  }


  /** 
   * <em>inv_trast_ope_report</em>カラムの値を設定します。 
   *
   * @@param p_invTrastOpeReport <em>inv_trast_ope_report</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setInvTrastOpeReport( String p_invTrastOpeReport )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    inv_trast_ope_report = p_invTrastOpeReport;
    inv_trast_ope_report_is_set = true;
    inv_trast_ope_report_is_modified = true;
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
   * <em>margin_tax_type</em>カラムの値を設定します。 
   *
   * @@param p_marginTaxType <em>margin_tax_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarginTaxType( String p_marginTaxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_tax_type = p_marginTaxType;
    margin_tax_type_is_set = true;
    margin_tax_type_is_modified = true;
  }


  /** 
   * <em>equity_sp_acc_open_date</em>カラムの値を設定します。 
   *
   * @@param p_equitySpAccOpenDate <em>equity_sp_acc_open_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setEquitySpAccOpenDate( java.sql.Timestamp p_equitySpAccOpenDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_sp_acc_open_date = p_equitySpAccOpenDate;
    equity_sp_acc_open_date_is_set = true;
    equity_sp_acc_open_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>equity_sp_acc_open_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setEquitySpAccOpenDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    equity_sp_acc_open_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    equity_sp_acc_open_date_is_set = true;
    equity_sp_acc_open_date_is_modified = true;
  }


  /** 
   * <em>margin_sp_acc_open_date</em>カラムの値を設定します。 
   *
   * @@param p_marginSpAccOpenDate <em>margin_sp_acc_open_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setMarginSpAccOpenDate( java.sql.Timestamp p_marginSpAccOpenDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sp_acc_open_date = p_marginSpAccOpenDate;
    margin_sp_acc_open_date_is_set = true;
    margin_sp_acc_open_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>margin_sp_acc_open_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setMarginSpAccOpenDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sp_acc_open_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    margin_sp_acc_open_date_is_set = true;
    margin_sp_acc_open_date_is_modified = true;
  }


  /** 
   * <em>tax_type_last</em>カラムの値を設定します。 
   *
   * @@param p_taxTypeLast <em>tax_type_last</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTaxTypeLast( String p_taxTypeLast )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tax_type_last = p_taxTypeLast;
    tax_type_last_is_set = true;
    tax_type_last_is_modified = true;
  }


  /** 
   * <em>margin_tax_type_last</em>カラムの値を設定します。 
   *
   * @@param p_marginTaxTypeLast <em>margin_tax_type_last</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarginTaxTypeLast( String p_marginTaxTypeLast )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_tax_type_last = p_marginTaxTypeLast;
    margin_tax_type_last_is_set = true;
    margin_tax_type_last_is_modified = true;
  }


  /** 
   * <em>tax_type_next</em>カラムの値を設定します。 
   *
   * @@param p_taxTypeNext <em>tax_type_next</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTaxTypeNext( String p_taxTypeNext )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tax_type_next = p_taxTypeNext;
    tax_type_next_is_set = true;
    tax_type_next_is_modified = true;
  }


  /** 
   * <em>margin_tax_type_next</em>カラムの値を設定します。 
   *
   * @@param p_marginTaxTypeNext <em>margin_tax_type_next</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarginTaxTypeNext( String p_marginTaxTypeNext )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_tax_type_next = p_marginTaxTypeNext;
    margin_tax_type_next_is_set = true;
    margin_tax_type_next_is_modified = true;
  }


  /** 
   * <em>fluct_date</em>カラムの値を設定します。 
   *
   * @@param p_fluctDate <em>fluct_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setFluctDate( java.sql.Timestamp p_fluctDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fluct_date = p_fluctDate;
    fluct_date_is_set = true;
    fluct_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>fluct_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setFluctDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    fluct_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    fluct_date_is_set = true;
    fluct_date_is_modified = true;
  }


  /** 
   * <em>margin_fluct_date</em>カラムの値を設定します。 
   *
   * @@param p_marginFluctDate <em>margin_fluct_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setMarginFluctDate( java.sql.Timestamp p_marginFluctDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_fluct_date = p_marginFluctDate;
    margin_fluct_date_is_set = true;
    margin_fluct_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>margin_fluct_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setMarginFluctDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_fluct_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    margin_fluct_date_is_set = true;
    margin_fluct_date_is_modified = true;
  }


  /** 
   * <em>local_tax_div_last</em>カラムの値を設定します。 
   *
   * @@param p_localTaxDivLast <em>local_tax_div_last</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setLocalTaxDivLast( String p_localTaxDivLast )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    local_tax_div_last = p_localTaxDivLast;
    local_tax_div_last_is_set = true;
    local_tax_div_last_is_modified = true;
  }


  /** 
   * <em>local_tax_div</em>カラムの値を設定します。 
   *
   * @@param p_localTaxDiv <em>local_tax_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setLocalTaxDiv( String p_localTaxDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    local_tax_div = p_localTaxDiv;
    local_tax_div_is_set = true;
    local_tax_div_is_modified = true;
  }


  /** 
   * <em>local_tax_div_next</em>カラムの値を設定します。 
   *
   * @@param p_localTaxDivNext <em>local_tax_div_next</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setLocalTaxDivNext( String p_localTaxDivNext )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    local_tax_div_next = p_localTaxDivNext;
    local_tax_div_next_is_set = true;
    local_tax_div_next_is_modified = true;
  }


  /** 
   * <em>qualified_inst_investor_div</em>カラムの値を設定します。 
   *
   * @@param p_qualifiedInstInvestorDiv <em>qualified_inst_investor_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setQualifiedInstInvestorDiv( String p_qualifiedInstInvestorDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    qualified_inst_investor_div = p_qualifiedInstInvestorDiv;
    qualified_inst_investor_div_is_set = true;
    qualified_inst_investor_div_is_modified = true;
  }


  /** 
   * <em>quoto_type</em>カラムの値を設定します。 
   *
   * @@param p_quotoType <em>quoto_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setQuotoType( String p_quotoType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quoto_type = p_quotoType;
    quoto_type_is_set = true;
    quoto_type_is_modified = true;
  }


  /** 
   * <em>sp_acc_abolish_date</em>カラムの値を設定します。 
   *
   * @@param p_spAccAbolishDate <em>sp_acc_abolish_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setSpAccAbolishDate( java.sql.Timestamp p_spAccAbolishDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sp_acc_abolish_date = p_spAccAbolishDate;
    sp_acc_abolish_date_is_set = true;
    sp_acc_abolish_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>sp_acc_abolish_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setSpAccAbolishDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sp_acc_abolish_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    sp_acc_abolish_date_is_set = true;
    sp_acc_abolish_date_is_modified = true;
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
   * <em>reserve_area</em>カラムの値を設定します。 
   *
   * @@param p_reserveArea <em>reserve_area</em>カラムの値をあらわす19桁以下のString値 
   */
  public final void setReserveArea( String p_reserveArea )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reserve_area = p_reserveArea;
    reserve_area_is_set = true;
    reserve_area_is_modified = true;
  }


  /** 
   * <em>web3_encrypted_password</em>カラムの値を設定します。 
   *
   * @@param p_web3EncryptedPassword <em>web3_encrypted_password</em>カラムの値をあらわす48桁以下のString値 
   */
  public final void setWeb3EncryptedPassword( String p_web3EncryptedPassword )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    web3_encrypted_password = p_web3EncryptedPassword;
    web3_encrypted_password_is_set = true;
    web3_encrypted_password_is_modified = true;
  }


  /** 
   * <em>xtrade_encrypted_password</em>カラムの値を設定します。 
   *
   * @@param p_xtradeEncryptedPassword <em>xtrade_encrypted_password</em>カラムの値をあらわす48桁以下のString値 
   */
  public final void setXtradeEncryptedPassword( String p_xtradeEncryptedPassword )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    xtrade_encrypted_password = p_xtradeEncryptedPassword;
    xtrade_encrypted_password_is_set = true;
    xtrade_encrypted_password_is_modified = true;
  }


  /** 
   * <em>broadcast_law</em>カラムの値を設定します。 
   *
   * @@param p_broadcastLaw <em>broadcast_law</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBroadcastLaw( String p_broadcastLaw )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    broadcast_law = p_broadcastLaw;
    broadcast_law_is_set = true;
    broadcast_law_is_modified = true;
  }


  /** 
   * <em>aviation_law</em>カラムの値を設定します。 
   *
   * @@param p_aviationLaw <em>aviation_law</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAviationLaw( String p_aviationLaw )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    aviation_law = p_aviationLaw;
    aviation_law_is_set = true;
    aviation_law_is_modified = true;
  }


  /** 
   * <em>ntt_law</em>カラムの値を設定します。 
   *
   * @@param p_nttLaw <em>ntt_law</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setNttLaw( String p_nttLaw )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ntt_law = p_nttLaw;
    ntt_law_is_set = true;
    ntt_law_is_modified = true;
  }


  /** 
   * <em>dividend_trans_designate</em>カラムの値を設定します。 
   *
   * @@param p_dividendTransDesignate <em>dividend_trans_designate</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDividendTransDesignate( String p_dividendTransDesignate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    dividend_trans_designate = p_dividendTransDesignate;
    dividend_trans_designate_is_set = true;
    dividend_trans_designate_is_modified = true;
  }


  /** 
   * <em>standing_proxy</em>カラムの値を設定します。 
   *
   * @@param p_standingProxy <em>standing_proxy</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStandingProxy( String p_standingProxy )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    standing_proxy = p_standingProxy;
    standing_proxy_is_set = true;
    standing_proxy_is_modified = true;
  }


  /** 
   * <em>statutory_agent</em>カラムの値を設定します。 
   *
   * @@param p_statutoryAgent <em>statutory_agent</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStatutoryAgent( String p_statutoryAgent )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    statutory_agent = p_statutoryAgent;
    statutory_agent_is_set = true;
    statutory_agent_is_modified = true;
  }


  /** 
   * <em>affiliate_account_code</em>カラムの値を設定します。 
   *
   * @@param p_affiliateAccountCode <em>affiliate_account_code</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setAffiliateAccountCode( String p_affiliateAccountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    affiliate_account_code = p_affiliateAccountCode;
    affiliate_account_code_is_set = true;
    affiliate_account_code_is_modified = true;
  }


  /** 
   * <em>agency_notify_cmp_div</em>カラムの値を設定します。 
   *
   * @@param p_agencyNotifyCmpDiv <em>agency_notify_cmp_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAgencyNotifyCmpDiv( String p_agencyNotifyCmpDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agency_notify_cmp_div = p_agencyNotifyCmpDiv;
    agency_notify_cmp_div_is_set = true;
    agency_notify_cmp_div_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_open_date") ) {
                    return this.account_open_date;
                }
                else if ( name.equals("address_unknown") ) {
                    return this.address_unknown;
                }
                else if ( name.equals("all_substitution_div") ) {
                    return this.all_substitution_div;
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
                else if ( name.equals("address_line1") ) {
                    return this.address_line1;
                }
                else if ( name.equals("address_line2") ) {
                    return this.address_line2;
                }
                else if ( name.equals("address_line3") ) {
                    return this.address_line3;
                }
                else if ( name.equals("ans_cust_div") ) {
                    return this.ans_cust_div;
                }
                else if ( name.equals("ans_stock_appli_cate") ) {
                    return this.ans_stock_appli_cate;
                }
                else if ( name.equals("address_code") ) {
                    return this.address_code;
                }
                else if ( name.equals("add_chg_div") ) {
                    return this.add_chg_div;
                }
                else if ( name.equals("agreed_non_pkg_div") ) {
                    return this.agreed_non_pkg_div;
                }
                else if ( name.equals("account_statement_flag") ) {
                    return this.account_statement_flag;
                }
                else if ( name.equals("aviation_law") ) {
                    return this.aviation_law;
                }
                else if ( name.equals("affiliate_account_code") ) {
                    return this.affiliate_account_code;
                }
                else if ( name.equals("agency_notify_cmp_div") ) {
                    return this.agency_notify_cmp_div;
                }
                break;
            case 'b':
                if ( name.equals("br_code") ) {
                    return this.br_code;
                }
                else if ( name.equals("born_date") ) {
                    return this.born_date;
                }
                else if ( name.equals("before_acc_trans_br_code") ) {
                    return this.before_acc_trans_br_code;
                }
                else if ( name.equals("before_acc_trans_cust_code") ) {
                    return this.before_acc_trans_cust_code;
                }
                else if ( name.equals("br_dispatch_div") ) {
                    return this.br_dispatch_div;
                }
                else if ( name.equals("bond_d_and_c_open_div") ) {
                    return this.bond_d_and_c_open_div;
                }
                else if ( name.equals("bond_tentou_op_open_div") ) {
                    return this.bond_tentou_op_open_div;
                }
                else if ( name.equals("bond_d_and_c_cont_div") ) {
                    return this.bond_d_and_c_cont_div;
                }
                else if ( name.equals("bond_tentou_op_cont_div") ) {
                    return this.bond_tentou_op_cont_div;
                }
                else if ( name.equals("bond_butt_div") ) {
                    return this.bond_butt_div;
                }
                else if ( name.equals("broadcast_law") ) {
                    return this.broadcast_law;
                }
                break;
            case 'c':
                if ( name.equals("comp_code") ) {
                    return this.comp_code;
                }
                else if ( name.equals("cust_code") ) {
                    return this.cust_code;
                }
                else if ( name.equals("cust_code_cd") ) {
                    return this.cust_code_cd;
                }
                else if ( name.equals("cust_div") ) {
                    return this.cust_div;
                }
                else if ( name.equals("client_trader_code") ) {
                    return this.client_trader_code;
                }
                else if ( name.equals("comma") ) {
                    return this.comma;
                }
                else if ( name.equals("contact_address_telephone") ) {
                    return this.contact_address_telephone;
                }
                else if ( name.equals("contact_address") ) {
                    return this.contact_address;
                }
                else if ( name.equals("class_div") ) {
                    return this.class_div;
                }
                else if ( name.equals("cancel_div") ) {
                    return this.cancel_div;
                }
                else if ( name.equals("chkup_rep_div") ) {
                    return this.chkup_rep_div;
                }
                else if ( name.equals("certificate_deposit_flag") ) {
                    return this.certificate_deposit_flag;
                }
                break;
            case 'd':
                if ( name.equals("doc_dispatch_div") ) {
                    return this.doc_dispatch_div;
                }
                else if ( name.equals("deposit_div") ) {
                    return this.deposit_div;
                }
                else if ( name.equals("dom_tax_div") ) {
                    return this.dom_tax_div;
                }
                else if ( name.equals("d_card_appli_date") ) {
                    return this.d_card_appli_date;
                }
                else if ( name.equals("d_card_issue_date") ) {
                    return this.d_card_issue_date;
                }
                else if ( name.equals("d_card_password") ) {
                    return this.d_card_password;
                }
                else if ( name.equals("d_card_issue_reason") ) {
                    return this.d_card_issue_reason;
                }
                else if ( name.equals("d_card_issue_number") ) {
                    return this.d_card_issue_number;
                }
                else if ( name.equals("d_card_stop_date") ) {
                    return this.d_card_stop_date;
                }
                else if ( name.equals("d_card_stop_reason") ) {
                    return this.d_card_stop_reason;
                }
                else if ( name.equals("d_card_cancel_date") ) {
                    return this.d_card_cancel_date;
                }
                else if ( name.equals("d_card_cancel_reason") ) {
                    return this.d_card_cancel_reason;
                }
                else if ( name.equals("d_card_name") ) {
                    return this.d_card_name;
                }
                else if ( name.equals("dummy1") ) {
                    return this.dummy1;
                }
                else if ( name.equals("dummy2") ) {
                    return this.dummy2;
                }
                else if ( name.equals("dummy3") ) {
                    return this.dummy3;
                }
                else if ( name.equals("dummy4") ) {
                    return this.dummy4;
                }
                else if ( name.equals("dummy5") ) {
                    return this.dummy5;
                }
                else if ( name.equals("dummy6") ) {
                    return this.dummy6;
                }
                else if ( name.equals("dummy7") ) {
                    return this.dummy7;
                }
                else if ( name.equals("dom_cp_open_div") ) {
                    return this.dom_cp_open_div;
                }
                else if ( name.equals("dom_war_trade_open_div") ) {
                    return this.dom_war_trade_open_div;
                }
                else if ( name.equals("dummy8") ) {
                    return this.dummy8;
                }
                else if ( name.equals("dom_for_bond_open_div") ) {
                    return this.dom_for_bond_open_div;
                }
                else if ( name.equals("dummy9") ) {
                    return this.dummy9;
                }
                else if ( name.equals("dummy10") ) {
                    return this.dummy10;
                }
                else if ( name.equals("dummy11") ) {
                    return this.dummy11;
                }
                else if ( name.equals("dummy12") ) {
                    return this.dummy12;
                }
                else if ( name.equals("dummy13") ) {
                    return this.dummy13;
                }
                else if ( name.equals("dummy14") ) {
                    return this.dummy14;
                }
                else if ( name.equals("dummy15") ) {
                    return this.dummy15;
                }
                else if ( name.equals("dom_war_trade_cont_div") ) {
                    return this.dom_war_trade_cont_div;
                }
                else if ( name.equals("dom_for_bond_cont_div") ) {
                    return this.dom_for_bond_cont_div;
                }
                else if ( name.equals("dummy16") ) {
                    return this.dummy16;
                }
                else if ( name.equals("dummy17") ) {
                    return this.dummy17;
                }
                else if ( name.equals("dummy18") ) {
                    return this.dummy18;
                }
                else if ( name.equals("dummy19") ) {
                    return this.dummy19;
                }
                else if ( name.equals("dummy20") ) {
                    return this.dummy20;
                }
                else if ( name.equals("dummy21") ) {
                    return this.dummy21;
                }
                else if ( name.equals("dummy22") ) {
                    return this.dummy22;
                }
                else if ( name.equals("dividend_trans_designate") ) {
                    return this.dividend_trans_designate;
                }
                break;
            case 'e':
                if ( name.equals("era_born") ) {
                    return this.era_born;
                }
                else if ( name.equals("eq_hold_rep_div") ) {
                    return this.eq_hold_rep_div;
                }
                else if ( name.equals("equity_sp_acc_open_date") ) {
                    return this.equity_sp_acc_open_date;
                }
                break;
            case 'f':
                if ( name.equals("foreign_sec_acc_open_div") ) {
                    return this.foreign_sec_acc_open_div;
                }
                else if ( name.equals("family_name_alt1") ) {
                    return this.family_name_alt1;
                }
                else if ( name.equals("family_name") ) {
                    return this.family_name;
                }
                else if ( name.equals("for_war_trade_open_div") ) {
                    return this.for_war_trade_open_div;
                }
                else if ( name.equals("family_nurturer_code") ) {
                    return this.family_nurturer_code;
                }
                else if ( name.equals("family_unit_adimin") ) {
                    return this.family_unit_adimin;
                }
                else if ( name.equals("family_unit_del_div") ) {
                    return this.family_unit_del_div;
                }
                else if ( name.equals("foreign_cont_div") ) {
                    return this.foreign_cont_div;
                }
                else if ( name.equals("futures_op_cont_div_tokyo") ) {
                    return this.futures_op_cont_div_tokyo;
                }
                else if ( name.equals("futures_op_cont_div_osaka") ) {
                    return this.futures_op_cont_div_osaka;
                }
                else if ( name.equals("futures_op_cont_div_nagoya") ) {
                    return this.futures_op_cont_div_nagoya;
                }
                else if ( name.equals("for_war_trade_cont_div") ) {
                    return this.for_war_trade_cont_div;
                }
                else if ( name.equals("fukuoka_qb_cont_div") ) {
                    return this.fukuoka_qb_cont_div;
                }
                else if ( name.equals("fax") ) {
                    return this.fax;
                }
                else if ( name.equals("fluct_date") ) {
                    return this.fluct_date;
                }
                break;
            case 'g':
                if ( name.equals("gen_acc_div") ) {
                    return this.gen_acc_div;
                }
                else if ( name.equals("gen_br_del_div") ) {
                    return this.gen_br_del_div;
                }
                else if ( name.equals("gp_br_del_div") ) {
                    return this.gp_br_del_div;
                }
                else if ( name.equals("gold_acc_open_div") ) {
                    return this.gold_acc_open_div;
                }
                else if ( name.equals("gold_cont_div") ) {
                    return this.gold_cont_div;
                }
                else if ( name.equals("gen_credit_acc_cont_div") ) {
                    return this.gen_credit_acc_cont_div;
                }
                break;
            case 'h':
                if ( name.equals("ht_settlement_way") ) {
                    return this.ht_settlement_way;
                }
                else if ( name.equals("hq_input_div") ) {
                    return this.hq_input_div;
                }
                else if ( name.equals("hofuri_entry") ) {
                    return this.hofuri_entry;
                }
                break;
            case 'i':
                if ( name.equals("ifo_acc_open_div_tokyo") ) {
                    return this.ifo_acc_open_div_tokyo;
                }
                else if ( name.equals("ins_loan_bill_mthd_div") ) {
                    return this.ins_loan_bill_mthd_div;
                }
                else if ( name.equals("ins_loan_cer_mthd_div") ) {
                    return this.ins_loan_cer_mthd_div;
                }
                else if ( name.equals("ins_loan_clause_mthd_div") ) {
                    return this.ins_loan_clause_mthd_div;
                }
                else if ( name.equals("ifo_acc_open_div_osaka") ) {
                    return this.ifo_acc_open_div_osaka;
                }
                else if ( name.equals("ifo_acc_open_div_nagoya") ) {
                    return this.ifo_acc_open_div_nagoya;
                }
                else if ( name.equals("inv_trast_ope_report") ) {
                    return this.inv_trast_ope_report;
                }
                break;
            case 'l':
                if ( name.equals("last_update_date") ) {
                    return this.last_update_date;
                }
                else if ( name.equals("local_tax_div_last") ) {
                    return this.local_tax_div_last;
                }
                else if ( name.equals("local_tax_div") ) {
                    return this.local_tax_div;
                }
                else if ( name.equals("local_tax_div_next") ) {
                    return this.local_tax_div_next;
                }
                break;
            case 'm':
                if ( name.equals("margin_acc_open_div") ) {
                    return this.margin_acc_open_div;
                }
                else if ( name.equals("maruyu_appli_div") ) {
                    return this.maruyu_appli_div;
                }
                else if ( name.equals("maruyu_laundering_div") ) {
                    return this.maruyu_laundering_div;
                }
                else if ( name.equals("mort_trade_open_div") ) {
                    return this.mort_trade_open_div;
                }
                else if ( name.equals("maruyu_grade_div") ) {
                    return this.maruyu_grade_div;
                }
                else if ( name.equals("mrf_acc_open_div") ) {
                    return this.mrf_acc_open_div;
                }
                else if ( name.equals("margin_cont_div") ) {
                    return this.margin_cont_div;
                }
                else if ( name.equals("mrf_contract_div") ) {
                    return this.mrf_contract_div;
                }
                else if ( name.equals("margin_tax_type") ) {
                    return this.margin_tax_type;
                }
                else if ( name.equals("margin_sp_acc_open_date") ) {
                    return this.margin_sp_acc_open_date;
                }
                else if ( name.equals("margin_tax_type_last") ) {
                    return this.margin_tax_type_last;
                }
                else if ( name.equals("margin_tax_type_next") ) {
                    return this.margin_tax_type_next;
                }
                else if ( name.equals("margin_fluct_date") ) {
                    return this.margin_fluct_date;
                }
                break;
            case 'n':
                if ( name.equals("new_monthly_div") ) {
                    return this.new_monthly_div;
                }
                else if ( name.equals("new_tb_open_div") ) {
                    return this.new_tb_open_div;
                }
                else if ( name.equals("nq_j_gl_cont_div") ) {
                    return this.nq_j_gl_cont_div;
                }
                else if ( name.equals("nagoya_grw_cpy_mkt_cont_div") ) {
                    return this.nagoya_grw_cpy_mkt_cont_div;
                }
                else if ( name.equals("new_tb_cont_div") ) {
                    return this.new_tb_cont_div;
                }
                else if ( name.equals("new_account_div") ) {
                    return this.new_account_div;
                }
                else if ( name.equals("ntt_law") ) {
                    return this.ntt_law;
                }
                break;
            case 'o':
                if ( name.equals("occupation") ) {
                    return this.occupation;
                }
                else if ( name.equals("os_fnc_futures_open_div") ) {
                    return this.os_fnc_futures_open_div;
                }
                else if ( name.equals("os_sec_futures_open_div") ) {
                    return this.os_sec_futures_open_div;
                }
                else if ( name.equals("os_cd_cp_open_div") ) {
                    return this.os_cd_cp_open_div;
                }
                else if ( name.equals("osaka_nw_mkt_cont_div") ) {
                    return this.osaka_nw_mkt_cont_div;
                }
                else if ( name.equals("org_deposit_div") ) {
                    return this.org_deposit_div;
                }
                break;
            case 'p':
                if ( name.equals("prefecture") ) {
                    return this.prefecture;
                }
                else if ( name.equals("pass_err_code_type") ) {
                    return this.pass_err_code_type;
                }
                else if ( name.equals("pass_err_code_number") ) {
                    return this.pass_err_code_number;
                }
                else if ( name.equals("pass_err_code_chg_date") ) {
                    return this.pass_err_code_chg_date;
                }
                else if ( name.equals("pass_err_code_chg_time") ) {
                    return this.pass_err_code_chg_time;
                }
                else if ( name.equals("person_identify") ) {
                    return this.person_identify;
                }
                else if ( name.equals("position_report_cycle_div") ) {
                    return this.position_report_cycle_div;
                }
                else if ( name.equals("position_report_div") ) {
                    return this.position_report_div;
                }
                break;
            case 'q':
                if ( name.equals("qualified_inst_investor_div") ) {
                    return this.qualified_inst_investor_div;
                }
                else if ( name.equals("quoto_type") ) {
                    return this.quoto_type;
                }
                break;
            case 'r':
                if ( name.equals("ruito_acc_open_div") ) {
                    return this.ruito_acc_open_div;
                }
                else if ( name.equals("report_dispatch_stop_div") ) {
                    return this.report_dispatch_stop_div;
                }
                else if ( name.equals("rep_dispatch_stp_bd") ) {
                    return this.rep_dispatch_stp_bd;
                }
                else if ( name.equals("represent_div") ) {
                    return this.represent_div;
                }
                else if ( name.equals("resident") ) {
                    return this.resident;
                }
                else if ( name.equals("reserve") ) {
                    return this.reserve;
                }
                else if ( name.equals("reserve_area") ) {
                    return this.reserve_area;
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code") ) {
                    return this.sonar_trader_code;
                }
                else if ( name.equals("safe_cont_open_div") ) {
                    return this.safe_cont_open_div;
                }
                else if ( name.equals("sex") ) {
                    return this.sex;
                }
                else if ( name.equals("self_assessed_sep_tax") ) {
                    return this.self_assessed_sep_tax;
                }
                else if ( name.equals("statement_skip1_div") ) {
                    return this.statement_skip1_div;
                }
                else if ( name.equals("statement_skip2_div") ) {
                    return this.statement_skip2_div;
                }
                else if ( name.equals("safe_cont_div") ) {
                    return this.safe_cont_div;
                }
                else if ( name.equals("sapporo_amb_cont_div") ) {
                    return this.sapporo_amb_cont_div;
                }
                else if ( name.equals("sp_acc_abolish_date") ) {
                    return this.sp_acc_abolish_date;
                }
                else if ( name.equals("sp_mng_acc_open_div") ) {
                    return this.sp_mng_acc_open_div;
                }
                else if ( name.equals("standing_proxy") ) {
                    return this.standing_proxy;
                }
                else if ( name.equals("statutory_agent") ) {
                    return this.statutory_agent;
                }
                break;
            case 't':
                if ( name.equals("tokuyu_acc_open_div") ) {
                    return this.tokuyu_acc_open_div;
                }
                else if ( name.equals("total_trade_open_div") ) {
                    return this.total_trade_open_div;
                }
                else if ( name.equals("tie_up_loan_open_div") ) {
                    return this.tie_up_loan_open_div;
                }
                else if ( name.equals("telephone") ) {
                    return this.telephone;
                }
                else if ( name.equals("tokuyu_appli_div") ) {
                    return this.tokuyu_appli_div;
                }
                else if ( name.equals("tokuyu_laundering_div") ) {
                    return this.tokuyu_laundering_div;
                }
                else if ( name.equals("tax_div") ) {
                    return this.tax_div;
                }
                else if ( name.equals("total_tax_identity") ) {
                    return this.total_tax_identity;
                }
                else if ( name.equals("tokyo_fnc_futures_open_div") ) {
                    return this.tokyo_fnc_futures_open_div;
                }
                else if ( name.equals("tentou_trade_open_div") ) {
                    return this.tentou_trade_open_div;
                }
                else if ( name.equals("trans_tax_div_acc_date") ) {
                    return this.trans_tax_div_acc_date;
                }
                else if ( name.equals("trans_tax_div") ) {
                    return this.trans_tax_div;
                }
                else if ( name.equals("t_bond_futures_open_div") ) {
                    return this.t_bond_futures_open_div;
                }
                else if ( name.equals("tentou_sp_rule_open_div") ) {
                    return this.tentou_sp_rule_open_div;
                }
                else if ( name.equals("tokyo_mothers_cont_div") ) {
                    return this.tokyo_mothers_cont_div;
                }
                else if ( name.equals("tentou_trade_cont_div") ) {
                    return this.tentou_trade_cont_div;
                }
                else if ( name.equals("tentou_sec_basis_cont_div") ) {
                    return this.tentou_sec_basis_cont_div;
                }
                else if ( name.equals("tentou_sp_rule_cont_div") ) {
                    return this.tentou_sp_rule_cont_div;
                }
                else if ( name.equals("trading_report_div") ) {
                    return this.trading_report_div;
                }
                else if ( name.equals("tax_type") ) {
                    return this.tax_type;
                }
                else if ( name.equals("tax_type_last") ) {
                    return this.tax_type_last;
                }
                else if ( name.equals("tax_type_next") ) {
                    return this.tax_type_next;
                }
                break;
            case 'v':
                if ( name.equals("via_trust_bank_div") ) {
                    return this.via_trust_bank_div;
                }
                break;
            case 'w':
                if ( name.equals("when_issued_acc_open_div") ) {
                    return this.when_issued_acc_open_div;
                }
                else if ( name.equals("when_issued_cont_div") ) {
                    return this.when_issued_cont_div;
                }
                else if ( name.equals("web3_encrypted_password") ) {
                    return this.web3_encrypted_password;
                }
                break;
            case 'x':
                if ( name.equals("xtrade_encrypted_password") ) {
                    return this.xtrade_encrypted_password;
                }
                break;
            case 'y':
                if ( name.equals("yellow_customer") ) {
                    return this.yellow_customer;
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
                if ( name.equals("account_open_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'account_open_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.account_open_date = (java.sql.Timestamp) value;
                    if (this.account_open_date_is_set)
                        this.account_open_date_is_modified = true;
                    this.account_open_date_is_set = true;
                    return;
                }
                else if ( name.equals("address_unknown") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'address_unknown' must be String: '"+value+"' is not." );
                    this.address_unknown = (String) value;
                    if (this.address_unknown_is_set)
                        this.address_unknown_is_modified = true;
                    this.address_unknown_is_set = true;
                    return;
                }
                else if ( name.equals("all_substitution_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'all_substitution_div' must be String: '"+value+"' is not." );
                    this.all_substitution_div = (String) value;
                    if (this.all_substitution_div_is_set)
                        this.all_substitution_div_is_modified = true;
                    this.all_substitution_div_is_set = true;
                    return;
                }
                else if ( name.equals("address_line1_kana") ) {
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
                else if ( name.equals("address_line1") ) {
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
                else if ( name.equals("ans_cust_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ans_cust_div' must be String: '"+value+"' is not." );
                    this.ans_cust_div = (String) value;
                    if (this.ans_cust_div_is_set)
                        this.ans_cust_div_is_modified = true;
                    this.ans_cust_div_is_set = true;
                    return;
                }
                else if ( name.equals("ans_stock_appli_cate") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'ans_stock_appli_cate' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.ans_stock_appli_cate = (java.sql.Timestamp) value;
                    if (this.ans_stock_appli_cate_is_set)
                        this.ans_stock_appli_cate_is_modified = true;
                    this.ans_stock_appli_cate_is_set = true;
                    return;
                }
                else if ( name.equals("address_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'address_code' must be String: '"+value+"' is not." );
                    this.address_code = (String) value;
                    if (this.address_code_is_set)
                        this.address_code_is_modified = true;
                    this.address_code_is_set = true;
                    return;
                }
                else if ( name.equals("add_chg_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'add_chg_div' must be String: '"+value+"' is not." );
                    this.add_chg_div = (String) value;
                    if (this.add_chg_div_is_set)
                        this.add_chg_div_is_modified = true;
                    this.add_chg_div_is_set = true;
                    return;
                }
                else if ( name.equals("agreed_non_pkg_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agreed_non_pkg_div' must be String: '"+value+"' is not." );
                    this.agreed_non_pkg_div = (String) value;
                    if (this.agreed_non_pkg_div_is_set)
                        this.agreed_non_pkg_div_is_modified = true;
                    this.agreed_non_pkg_div_is_set = true;
                    return;
                }
                else if ( name.equals("account_statement_flag") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_statement_flag' must be String: '"+value+"' is not." );
                    this.account_statement_flag = (String) value;
                    if (this.account_statement_flag_is_set)
                        this.account_statement_flag_is_modified = true;
                    this.account_statement_flag_is_set = true;
                    return;
                }
                else if ( name.equals("aviation_law") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'aviation_law' must be String: '"+value+"' is not." );
                    this.aviation_law = (String) value;
                    if (this.aviation_law_is_set)
                        this.aviation_law_is_modified = true;
                    this.aviation_law_is_set = true;
                    return;
                }
                else if ( name.equals("affiliate_account_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'affiliate_account_code' must be String: '"+value+"' is not." );
                    this.affiliate_account_code = (String) value;
                    if (this.affiliate_account_code_is_set)
                        this.affiliate_account_code_is_modified = true;
                    this.affiliate_account_code_is_set = true;
                    return;
                }
                else if ( name.equals("agency_notify_cmp_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agency_notify_cmp_div' must be String: '"+value+"' is not." );
                    this.agency_notify_cmp_div = (String) value;
                    if (this.agency_notify_cmp_div_is_set)
                        this.agency_notify_cmp_div_is_modified = true;
                    this.agency_notify_cmp_div_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("br_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'br_code' must be String: '"+value+"' is not." );
                    this.br_code = (String) value;
                    if (this.br_code_is_set)
                        this.br_code_is_modified = true;
                    this.br_code_is_set = true;
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
                else if ( name.equals("before_acc_trans_br_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'before_acc_trans_br_code' must be String: '"+value+"' is not." );
                    this.before_acc_trans_br_code = (String) value;
                    if (this.before_acc_trans_br_code_is_set)
                        this.before_acc_trans_br_code_is_modified = true;
                    this.before_acc_trans_br_code_is_set = true;
                    return;
                }
                else if ( name.equals("before_acc_trans_cust_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'before_acc_trans_cust_code' must be String: '"+value+"' is not." );
                    this.before_acc_trans_cust_code = (String) value;
                    if (this.before_acc_trans_cust_code_is_set)
                        this.before_acc_trans_cust_code_is_modified = true;
                    this.before_acc_trans_cust_code_is_set = true;
                    return;
                }
                else if ( name.equals("br_dispatch_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'br_dispatch_div' must be String: '"+value+"' is not." );
                    this.br_dispatch_div = (String) value;
                    if (this.br_dispatch_div_is_set)
                        this.br_dispatch_div_is_modified = true;
                    this.br_dispatch_div_is_set = true;
                    return;
                }
                else if ( name.equals("bond_d_and_c_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bond_d_and_c_open_div' must be String: '"+value+"' is not." );
                    this.bond_d_and_c_open_div = (String) value;
                    if (this.bond_d_and_c_open_div_is_set)
                        this.bond_d_and_c_open_div_is_modified = true;
                    this.bond_d_and_c_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("bond_tentou_op_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bond_tentou_op_open_div' must be String: '"+value+"' is not." );
                    this.bond_tentou_op_open_div = (String) value;
                    if (this.bond_tentou_op_open_div_is_set)
                        this.bond_tentou_op_open_div_is_modified = true;
                    this.bond_tentou_op_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("bond_d_and_c_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bond_d_and_c_cont_div' must be String: '"+value+"' is not." );
                    this.bond_d_and_c_cont_div = (String) value;
                    if (this.bond_d_and_c_cont_div_is_set)
                        this.bond_d_and_c_cont_div_is_modified = true;
                    this.bond_d_and_c_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("bond_tentou_op_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bond_tentou_op_cont_div' must be String: '"+value+"' is not." );
                    this.bond_tentou_op_cont_div = (String) value;
                    if (this.bond_tentou_op_cont_div_is_set)
                        this.bond_tentou_op_cont_div_is_modified = true;
                    this.bond_tentou_op_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("bond_butt_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bond_butt_div' must be String: '"+value+"' is not." );
                    this.bond_butt_div = (String) value;
                    if (this.bond_butt_div_is_set)
                        this.bond_butt_div_is_modified = true;
                    this.bond_butt_div_is_set = true;
                    return;
                }
                else if ( name.equals("broadcast_law") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'broadcast_law' must be String: '"+value+"' is not." );
                    this.broadcast_law = (String) value;
                    if (this.broadcast_law_is_set)
                        this.broadcast_law_is_modified = true;
                    this.broadcast_law_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("comp_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comp_code' must be String: '"+value+"' is not." );
                    this.comp_code = (String) value;
                    if (this.comp_code_is_set)
                        this.comp_code_is_modified = true;
                    this.comp_code_is_set = true;
                    return;
                }
                else if ( name.equals("cust_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cust_code' must be String: '"+value+"' is not." );
                    this.cust_code = (String) value;
                    if (this.cust_code_is_set)
                        this.cust_code_is_modified = true;
                    this.cust_code_is_set = true;
                    return;
                }
                else if ( name.equals("cust_code_cd") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cust_code_cd' must be String: '"+value+"' is not." );
                    this.cust_code_cd = (String) value;
                    if (this.cust_code_cd_is_set)
                        this.cust_code_cd_is_modified = true;
                    this.cust_code_cd_is_set = true;
                    return;
                }
                else if ( name.equals("cust_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cust_div' must be String: '"+value+"' is not." );
                    this.cust_div = (String) value;
                    if (this.cust_div_is_set)
                        this.cust_div_is_modified = true;
                    this.cust_div_is_set = true;
                    return;
                }
                else if ( name.equals("client_trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'client_trader_code' must be String: '"+value+"' is not." );
                    this.client_trader_code = (String) value;
                    if (this.client_trader_code_is_set)
                        this.client_trader_code_is_modified = true;
                    this.client_trader_code_is_set = true;
                    return;
                }
                else if ( name.equals("comma") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'comma' must be String: '"+value+"' is not." );
                    this.comma = (String) value;
                    if (this.comma_is_set)
                        this.comma_is_modified = true;
                    this.comma_is_set = true;
                    return;
                }
                else if ( name.equals("contact_address_telephone") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'contact_address_telephone' must be String: '"+value+"' is not." );
                    this.contact_address_telephone = (String) value;
                    if (this.contact_address_telephone_is_set)
                        this.contact_address_telephone_is_modified = true;
                    this.contact_address_telephone_is_set = true;
                    return;
                }
                else if ( name.equals("contact_address") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'contact_address' must be String: '"+value+"' is not." );
                    this.contact_address = (String) value;
                    if (this.contact_address_is_set)
                        this.contact_address_is_modified = true;
                    this.contact_address_is_set = true;
                    return;
                }
                else if ( name.equals("class_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'class_div' must be String: '"+value+"' is not." );
                    this.class_div = (String) value;
                    if (this.class_div_is_set)
                        this.class_div_is_modified = true;
                    this.class_div_is_set = true;
                    return;
                }
                else if ( name.equals("cancel_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cancel_div' must be String: '"+value+"' is not." );
                    this.cancel_div = (String) value;
                    if (this.cancel_div_is_set)
                        this.cancel_div_is_modified = true;
                    this.cancel_div_is_set = true;
                    return;
                }
                else if ( name.equals("chkup_rep_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'chkup_rep_div' must be String: '"+value+"' is not." );
                    this.chkup_rep_div = (String) value;
                    if (this.chkup_rep_div_is_set)
                        this.chkup_rep_div_is_modified = true;
                    this.chkup_rep_div_is_set = true;
                    return;
                }
                else if ( name.equals("certificate_deposit_flag") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'certificate_deposit_flag' must be String: '"+value+"' is not." );
                    this.certificate_deposit_flag = (String) value;
                    if (this.certificate_deposit_flag_is_set)
                        this.certificate_deposit_flag_is_modified = true;
                    this.certificate_deposit_flag_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("doc_dispatch_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'doc_dispatch_div' must be String: '"+value+"' is not." );
                    this.doc_dispatch_div = (String) value;
                    if (this.doc_dispatch_div_is_set)
                        this.doc_dispatch_div_is_modified = true;
                    this.doc_dispatch_div_is_set = true;
                    return;
                }
                else if ( name.equals("deposit_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deposit_div' must be String: '"+value+"' is not." );
                    this.deposit_div = (String) value;
                    if (this.deposit_div_is_set)
                        this.deposit_div_is_modified = true;
                    this.deposit_div_is_set = true;
                    return;
                }
                else if ( name.equals("dom_tax_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dom_tax_div' must be String: '"+value+"' is not." );
                    this.dom_tax_div = (String) value;
                    if (this.dom_tax_div_is_set)
                        this.dom_tax_div_is_modified = true;
                    this.dom_tax_div_is_set = true;
                    return;
                }
                else if ( name.equals("d_card_appli_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'd_card_appli_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.d_card_appli_date = (java.sql.Timestamp) value;
                    if (this.d_card_appli_date_is_set)
                        this.d_card_appli_date_is_modified = true;
                    this.d_card_appli_date_is_set = true;
                    return;
                }
                else if ( name.equals("d_card_issue_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'd_card_issue_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.d_card_issue_date = (java.sql.Timestamp) value;
                    if (this.d_card_issue_date_is_set)
                        this.d_card_issue_date_is_modified = true;
                    this.d_card_issue_date_is_set = true;
                    return;
                }
                else if ( name.equals("d_card_password") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'd_card_password' must be String: '"+value+"' is not." );
                    this.d_card_password = (String) value;
                    if (this.d_card_password_is_set)
                        this.d_card_password_is_modified = true;
                    this.d_card_password_is_set = true;
                    return;
                }
                else if ( name.equals("d_card_issue_reason") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'd_card_issue_reason' must be String: '"+value+"' is not." );
                    this.d_card_issue_reason = (String) value;
                    if (this.d_card_issue_reason_is_set)
                        this.d_card_issue_reason_is_modified = true;
                    this.d_card_issue_reason_is_set = true;
                    return;
                }
                else if ( name.equals("d_card_issue_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'd_card_issue_number' must be String: '"+value+"' is not." );
                    this.d_card_issue_number = (String) value;
                    if (this.d_card_issue_number_is_set)
                        this.d_card_issue_number_is_modified = true;
                    this.d_card_issue_number_is_set = true;
                    return;
                }
                else if ( name.equals("d_card_stop_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'd_card_stop_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.d_card_stop_date = (java.sql.Timestamp) value;
                    if (this.d_card_stop_date_is_set)
                        this.d_card_stop_date_is_modified = true;
                    this.d_card_stop_date_is_set = true;
                    return;
                }
                else if ( name.equals("d_card_stop_reason") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'd_card_stop_reason' must be String: '"+value+"' is not." );
                    this.d_card_stop_reason = (String) value;
                    if (this.d_card_stop_reason_is_set)
                        this.d_card_stop_reason_is_modified = true;
                    this.d_card_stop_reason_is_set = true;
                    return;
                }
                else if ( name.equals("d_card_cancel_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'd_card_cancel_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.d_card_cancel_date = (java.sql.Timestamp) value;
                    if (this.d_card_cancel_date_is_set)
                        this.d_card_cancel_date_is_modified = true;
                    this.d_card_cancel_date_is_set = true;
                    return;
                }
                else if ( name.equals("d_card_cancel_reason") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'd_card_cancel_reason' must be String: '"+value+"' is not." );
                    this.d_card_cancel_reason = (String) value;
                    if (this.d_card_cancel_reason_is_set)
                        this.d_card_cancel_reason_is_modified = true;
                    this.d_card_cancel_reason_is_set = true;
                    return;
                }
                else if ( name.equals("d_card_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'd_card_name' must be String: '"+value+"' is not." );
                    this.d_card_name = (String) value;
                    if (this.d_card_name_is_set)
                        this.d_card_name_is_modified = true;
                    this.d_card_name_is_set = true;
                    return;
                }
                else if ( name.equals("dummy1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy1' must be String: '"+value+"' is not." );
                    this.dummy1 = (String) value;
                    if (this.dummy1_is_set)
                        this.dummy1_is_modified = true;
                    this.dummy1_is_set = true;
                    return;
                }
                else if ( name.equals("dummy2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy2' must be String: '"+value+"' is not." );
                    this.dummy2 = (String) value;
                    if (this.dummy2_is_set)
                        this.dummy2_is_modified = true;
                    this.dummy2_is_set = true;
                    return;
                }
                else if ( name.equals("dummy3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy3' must be String: '"+value+"' is not." );
                    this.dummy3 = (String) value;
                    if (this.dummy3_is_set)
                        this.dummy3_is_modified = true;
                    this.dummy3_is_set = true;
                    return;
                }
                else if ( name.equals("dummy4") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy4' must be String: '"+value+"' is not." );
                    this.dummy4 = (String) value;
                    if (this.dummy4_is_set)
                        this.dummy4_is_modified = true;
                    this.dummy4_is_set = true;
                    return;
                }
                else if ( name.equals("dummy5") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy5' must be String: '"+value+"' is not." );
                    this.dummy5 = (String) value;
                    if (this.dummy5_is_set)
                        this.dummy5_is_modified = true;
                    this.dummy5_is_set = true;
                    return;
                }
                else if ( name.equals("dummy6") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy6' must be String: '"+value+"' is not." );
                    this.dummy6 = (String) value;
                    if (this.dummy6_is_set)
                        this.dummy6_is_modified = true;
                    this.dummy6_is_set = true;
                    return;
                }
                else if ( name.equals("dummy7") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy7' must be String: '"+value+"' is not." );
                    this.dummy7 = (String) value;
                    if (this.dummy7_is_set)
                        this.dummy7_is_modified = true;
                    this.dummy7_is_set = true;
                    return;
                }
                else if ( name.equals("dom_cp_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dom_cp_open_div' must be String: '"+value+"' is not." );
                    this.dom_cp_open_div = (String) value;
                    if (this.dom_cp_open_div_is_set)
                        this.dom_cp_open_div_is_modified = true;
                    this.dom_cp_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("dom_war_trade_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dom_war_trade_open_div' must be String: '"+value+"' is not." );
                    this.dom_war_trade_open_div = (String) value;
                    if (this.dom_war_trade_open_div_is_set)
                        this.dom_war_trade_open_div_is_modified = true;
                    this.dom_war_trade_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("dummy8") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy8' must be String: '"+value+"' is not." );
                    this.dummy8 = (String) value;
                    if (this.dummy8_is_set)
                        this.dummy8_is_modified = true;
                    this.dummy8_is_set = true;
                    return;
                }
                else if ( name.equals("dom_for_bond_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dom_for_bond_open_div' must be String: '"+value+"' is not." );
                    this.dom_for_bond_open_div = (String) value;
                    if (this.dom_for_bond_open_div_is_set)
                        this.dom_for_bond_open_div_is_modified = true;
                    this.dom_for_bond_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("dummy9") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy9' must be String: '"+value+"' is not." );
                    this.dummy9 = (String) value;
                    if (this.dummy9_is_set)
                        this.dummy9_is_modified = true;
                    this.dummy9_is_set = true;
                    return;
                }
                else if ( name.equals("dummy10") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy10' must be String: '"+value+"' is not." );
                    this.dummy10 = (String) value;
                    if (this.dummy10_is_set)
                        this.dummy10_is_modified = true;
                    this.dummy10_is_set = true;
                    return;
                }
                else if ( name.equals("dummy11") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy11' must be String: '"+value+"' is not." );
                    this.dummy11 = (String) value;
                    if (this.dummy11_is_set)
                        this.dummy11_is_modified = true;
                    this.dummy11_is_set = true;
                    return;
                }
                else if ( name.equals("dummy12") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy12' must be String: '"+value+"' is not." );
                    this.dummy12 = (String) value;
                    if (this.dummy12_is_set)
                        this.dummy12_is_modified = true;
                    this.dummy12_is_set = true;
                    return;
                }
                else if ( name.equals("dummy13") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy13' must be String: '"+value+"' is not." );
                    this.dummy13 = (String) value;
                    if (this.dummy13_is_set)
                        this.dummy13_is_modified = true;
                    this.dummy13_is_set = true;
                    return;
                }
                else if ( name.equals("dummy14") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy14' must be String: '"+value+"' is not." );
                    this.dummy14 = (String) value;
                    if (this.dummy14_is_set)
                        this.dummy14_is_modified = true;
                    this.dummy14_is_set = true;
                    return;
                }
                else if ( name.equals("dummy15") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy15' must be String: '"+value+"' is not." );
                    this.dummy15 = (String) value;
                    if (this.dummy15_is_set)
                        this.dummy15_is_modified = true;
                    this.dummy15_is_set = true;
                    return;
                }
                else if ( name.equals("dom_war_trade_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dom_war_trade_cont_div' must be String: '"+value+"' is not." );
                    this.dom_war_trade_cont_div = (String) value;
                    if (this.dom_war_trade_cont_div_is_set)
                        this.dom_war_trade_cont_div_is_modified = true;
                    this.dom_war_trade_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("dom_for_bond_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dom_for_bond_cont_div' must be String: '"+value+"' is not." );
                    this.dom_for_bond_cont_div = (String) value;
                    if (this.dom_for_bond_cont_div_is_set)
                        this.dom_for_bond_cont_div_is_modified = true;
                    this.dom_for_bond_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("dummy16") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy16' must be String: '"+value+"' is not." );
                    this.dummy16 = (String) value;
                    if (this.dummy16_is_set)
                        this.dummy16_is_modified = true;
                    this.dummy16_is_set = true;
                    return;
                }
                else if ( name.equals("dummy17") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy17' must be String: '"+value+"' is not." );
                    this.dummy17 = (String) value;
                    if (this.dummy17_is_set)
                        this.dummy17_is_modified = true;
                    this.dummy17_is_set = true;
                    return;
                }
                else if ( name.equals("dummy18") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy18' must be String: '"+value+"' is not." );
                    this.dummy18 = (String) value;
                    if (this.dummy18_is_set)
                        this.dummy18_is_modified = true;
                    this.dummy18_is_set = true;
                    return;
                }
                else if ( name.equals("dummy19") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy19' must be String: '"+value+"' is not." );
                    this.dummy19 = (String) value;
                    if (this.dummy19_is_set)
                        this.dummy19_is_modified = true;
                    this.dummy19_is_set = true;
                    return;
                }
                else if ( name.equals("dummy20") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy20' must be String: '"+value+"' is not." );
                    this.dummy20 = (String) value;
                    if (this.dummy20_is_set)
                        this.dummy20_is_modified = true;
                    this.dummy20_is_set = true;
                    return;
                }
                else if ( name.equals("dummy21") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy21' must be String: '"+value+"' is not." );
                    this.dummy21 = (String) value;
                    if (this.dummy21_is_set)
                        this.dummy21_is_modified = true;
                    this.dummy21_is_set = true;
                    return;
                }
                else if ( name.equals("dummy22") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dummy22' must be String: '"+value+"' is not." );
                    this.dummy22 = (String) value;
                    if (this.dummy22_is_set)
                        this.dummy22_is_modified = true;
                    this.dummy22_is_set = true;
                    return;
                }
                else if ( name.equals("dividend_trans_designate") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'dividend_trans_designate' must be String: '"+value+"' is not." );
                    this.dividend_trans_designate = (String) value;
                    if (this.dividend_trans_designate_is_set)
                        this.dividend_trans_designate_is_modified = true;
                    this.dividend_trans_designate_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("era_born") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'era_born' must be String: '"+value+"' is not." );
                    this.era_born = (String) value;
                    if (this.era_born_is_set)
                        this.era_born_is_modified = true;
                    this.era_born_is_set = true;
                    return;
                }
                else if ( name.equals("eq_hold_rep_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'eq_hold_rep_div' must be String: '"+value+"' is not." );
                    this.eq_hold_rep_div = (String) value;
                    if (this.eq_hold_rep_div_is_set)
                        this.eq_hold_rep_div_is_modified = true;
                    this.eq_hold_rep_div_is_set = true;
                    return;
                }
                else if ( name.equals("equity_sp_acc_open_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'equity_sp_acc_open_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.equity_sp_acc_open_date = (java.sql.Timestamp) value;
                    if (this.equity_sp_acc_open_date_is_set)
                        this.equity_sp_acc_open_date_is_modified = true;
                    this.equity_sp_acc_open_date_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("foreign_sec_acc_open_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'foreign_sec_acc_open_div' must be String: '"+value+"' is not." );
                    this.foreign_sec_acc_open_div = (String) value;
                    if (this.foreign_sec_acc_open_div_is_set)
                        this.foreign_sec_acc_open_div_is_modified = true;
                    this.foreign_sec_acc_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("family_name_alt1") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'family_name_alt1' must be String: '"+value+"' is not." );
                    this.family_name_alt1 = (String) value;
                    if (this.family_name_alt1_is_set)
                        this.family_name_alt1_is_modified = true;
                    this.family_name_alt1_is_set = true;
                    return;
                }
                else if ( name.equals("family_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'family_name' must be String: '"+value+"' is not." );
                    this.family_name = (String) value;
                    if (this.family_name_is_set)
                        this.family_name_is_modified = true;
                    this.family_name_is_set = true;
                    return;
                }
                else if ( name.equals("for_war_trade_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'for_war_trade_open_div' must be String: '"+value+"' is not." );
                    this.for_war_trade_open_div = (String) value;
                    if (this.for_war_trade_open_div_is_set)
                        this.for_war_trade_open_div_is_modified = true;
                    this.for_war_trade_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("family_nurturer_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'family_nurturer_code' must be String: '"+value+"' is not." );
                    this.family_nurturer_code = (String) value;
                    if (this.family_nurturer_code_is_set)
                        this.family_nurturer_code_is_modified = true;
                    this.family_nurturer_code_is_set = true;
                    return;
                }
                else if ( name.equals("family_unit_adimin") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'family_unit_adimin' must be String: '"+value+"' is not." );
                    this.family_unit_adimin = (String) value;
                    if (this.family_unit_adimin_is_set)
                        this.family_unit_adimin_is_modified = true;
                    this.family_unit_adimin_is_set = true;
                    return;
                }
                else if ( name.equals("family_unit_del_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'family_unit_del_div' must be String: '"+value+"' is not." );
                    this.family_unit_del_div = (String) value;
                    if (this.family_unit_del_div_is_set)
                        this.family_unit_del_div_is_modified = true;
                    this.family_unit_del_div_is_set = true;
                    return;
                }
                else if ( name.equals("foreign_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'foreign_cont_div' must be String: '"+value+"' is not." );
                    this.foreign_cont_div = (String) value;
                    if (this.foreign_cont_div_is_set)
                        this.foreign_cont_div_is_modified = true;
                    this.foreign_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("futures_op_cont_div_tokyo") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'futures_op_cont_div_tokyo' must be String: '"+value+"' is not." );
                    this.futures_op_cont_div_tokyo = (String) value;
                    if (this.futures_op_cont_div_tokyo_is_set)
                        this.futures_op_cont_div_tokyo_is_modified = true;
                    this.futures_op_cont_div_tokyo_is_set = true;
                    return;
                }
                else if ( name.equals("futures_op_cont_div_osaka") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'futures_op_cont_div_osaka' must be String: '"+value+"' is not." );
                    this.futures_op_cont_div_osaka = (String) value;
                    if (this.futures_op_cont_div_osaka_is_set)
                        this.futures_op_cont_div_osaka_is_modified = true;
                    this.futures_op_cont_div_osaka_is_set = true;
                    return;
                }
                else if ( name.equals("futures_op_cont_div_nagoya") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'futures_op_cont_div_nagoya' must be String: '"+value+"' is not." );
                    this.futures_op_cont_div_nagoya = (String) value;
                    if (this.futures_op_cont_div_nagoya_is_set)
                        this.futures_op_cont_div_nagoya_is_modified = true;
                    this.futures_op_cont_div_nagoya_is_set = true;
                    return;
                }
                else if ( name.equals("for_war_trade_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'for_war_trade_cont_div' must be String: '"+value+"' is not." );
                    this.for_war_trade_cont_div = (String) value;
                    if (this.for_war_trade_cont_div_is_set)
                        this.for_war_trade_cont_div_is_modified = true;
                    this.for_war_trade_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("fukuoka_qb_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fukuoka_qb_cont_div' must be String: '"+value+"' is not." );
                    this.fukuoka_qb_cont_div = (String) value;
                    if (this.fukuoka_qb_cont_div_is_set)
                        this.fukuoka_qb_cont_div_is_modified = true;
                    this.fukuoka_qb_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("fax") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fax' must be String: '"+value+"' is not." );
                    this.fax = (String) value;
                    if (this.fax_is_set)
                        this.fax_is_modified = true;
                    this.fax_is_set = true;
                    return;
                }
                else if ( name.equals("fluct_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'fluct_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.fluct_date = (java.sql.Timestamp) value;
                    if (this.fluct_date_is_set)
                        this.fluct_date_is_modified = true;
                    this.fluct_date_is_set = true;
                    return;
                }
                break;
            case 'g':
                if ( name.equals("gen_acc_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gen_acc_div' must be String: '"+value+"' is not." );
                    this.gen_acc_div = (String) value;
                    if (this.gen_acc_div_is_set)
                        this.gen_acc_div_is_modified = true;
                    this.gen_acc_div_is_set = true;
                    return;
                }
                else if ( name.equals("gen_br_del_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gen_br_del_div' must be String: '"+value+"' is not." );
                    this.gen_br_del_div = (String) value;
                    if (this.gen_br_del_div_is_set)
                        this.gen_br_del_div_is_modified = true;
                    this.gen_br_del_div_is_set = true;
                    return;
                }
                else if ( name.equals("gp_br_del_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_br_del_div' must be String: '"+value+"' is not." );
                    this.gp_br_del_div = (String) value;
                    if (this.gp_br_del_div_is_set)
                        this.gp_br_del_div_is_modified = true;
                    this.gp_br_del_div_is_set = true;
                    return;
                }
                else if ( name.equals("gold_acc_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gold_acc_open_div' must be String: '"+value+"' is not." );
                    this.gold_acc_open_div = (String) value;
                    if (this.gold_acc_open_div_is_set)
                        this.gold_acc_open_div_is_modified = true;
                    this.gold_acc_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("gold_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gold_cont_div' must be String: '"+value+"' is not." );
                    this.gold_cont_div = (String) value;
                    if (this.gold_cont_div_is_set)
                        this.gold_cont_div_is_modified = true;
                    this.gold_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("gen_credit_acc_cont_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gen_credit_acc_cont_div' must be String: '"+value+"' is not." );
                    this.gen_credit_acc_cont_div = (String) value;
                    if (this.gen_credit_acc_cont_div_is_set)
                        this.gen_credit_acc_cont_div_is_modified = true;
                    this.gen_credit_acc_cont_div_is_set = true;
                    return;
                }
                break;
            case 'h':
                if ( name.equals("ht_settlement_way") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ht_settlement_way' must be String: '"+value+"' is not." );
                    this.ht_settlement_way = (String) value;
                    if (this.ht_settlement_way_is_set)
                        this.ht_settlement_way_is_modified = true;
                    this.ht_settlement_way_is_set = true;
                    return;
                }
                else if ( name.equals("hq_input_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'hq_input_div' must be String: '"+value+"' is not." );
                    this.hq_input_div = (String) value;
                    if (this.hq_input_div_is_set)
                        this.hq_input_div_is_modified = true;
                    this.hq_input_div_is_set = true;
                    return;
                }
                else if ( name.equals("hofuri_entry") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'hofuri_entry' must be String: '"+value+"' is not." );
                    this.hofuri_entry = (String) value;
                    if (this.hofuri_entry_is_set)
                        this.hofuri_entry_is_modified = true;
                    this.hofuri_entry_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("ifo_acc_open_div_tokyo") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_acc_open_div_tokyo' must be String: '"+value+"' is not." );
                    this.ifo_acc_open_div_tokyo = (String) value;
                    if (this.ifo_acc_open_div_tokyo_is_set)
                        this.ifo_acc_open_div_tokyo_is_modified = true;
                    this.ifo_acc_open_div_tokyo_is_set = true;
                    return;
                }
                else if ( name.equals("ins_loan_bill_mthd_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ins_loan_bill_mthd_div' must be String: '"+value+"' is not." );
                    this.ins_loan_bill_mthd_div = (String) value;
                    if (this.ins_loan_bill_mthd_div_is_set)
                        this.ins_loan_bill_mthd_div_is_modified = true;
                    this.ins_loan_bill_mthd_div_is_set = true;
                    return;
                }
                else if ( name.equals("ins_loan_cer_mthd_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ins_loan_cer_mthd_div' must be String: '"+value+"' is not." );
                    this.ins_loan_cer_mthd_div = (String) value;
                    if (this.ins_loan_cer_mthd_div_is_set)
                        this.ins_loan_cer_mthd_div_is_modified = true;
                    this.ins_loan_cer_mthd_div_is_set = true;
                    return;
                }
                else if ( name.equals("ins_loan_clause_mthd_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ins_loan_clause_mthd_div' must be String: '"+value+"' is not." );
                    this.ins_loan_clause_mthd_div = (String) value;
                    if (this.ins_loan_clause_mthd_div_is_set)
                        this.ins_loan_clause_mthd_div_is_modified = true;
                    this.ins_loan_clause_mthd_div_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_acc_open_div_osaka") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_acc_open_div_osaka' must be String: '"+value+"' is not." );
                    this.ifo_acc_open_div_osaka = (String) value;
                    if (this.ifo_acc_open_div_osaka_is_set)
                        this.ifo_acc_open_div_osaka_is_modified = true;
                    this.ifo_acc_open_div_osaka_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_acc_open_div_nagoya") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_acc_open_div_nagoya' must be String: '"+value+"' is not." );
                    this.ifo_acc_open_div_nagoya = (String) value;
                    if (this.ifo_acc_open_div_nagoya_is_set)
                        this.ifo_acc_open_div_nagoya_is_modified = true;
                    this.ifo_acc_open_div_nagoya_is_set = true;
                    return;
                }
                else if ( name.equals("inv_trast_ope_report") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'inv_trast_ope_report' must be String: '"+value+"' is not." );
                    this.inv_trast_ope_report = (String) value;
                    if (this.inv_trast_ope_report_is_set)
                        this.inv_trast_ope_report_is_modified = true;
                    this.inv_trast_ope_report_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_update_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_update_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_update_date = (java.sql.Timestamp) value;
                    if (this.last_update_date_is_set)
                        this.last_update_date_is_modified = true;
                    this.last_update_date_is_set = true;
                    return;
                }
                else if ( name.equals("local_tax_div_last") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'local_tax_div_last' must be String: '"+value+"' is not." );
                    this.local_tax_div_last = (String) value;
                    if (this.local_tax_div_last_is_set)
                        this.local_tax_div_last_is_modified = true;
                    this.local_tax_div_last_is_set = true;
                    return;
                }
                else if ( name.equals("local_tax_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'local_tax_div' must be String: '"+value+"' is not." );
                    this.local_tax_div = (String) value;
                    if (this.local_tax_div_is_set)
                        this.local_tax_div_is_modified = true;
                    this.local_tax_div_is_set = true;
                    return;
                }
                else if ( name.equals("local_tax_div_next") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'local_tax_div_next' must be String: '"+value+"' is not." );
                    this.local_tax_div_next = (String) value;
                    if (this.local_tax_div_next_is_set)
                        this.local_tax_div_next_is_modified = true;
                    this.local_tax_div_next_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("margin_acc_open_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_acc_open_div' must be String: '"+value+"' is not." );
                    this.margin_acc_open_div = (String) value;
                    if (this.margin_acc_open_div_is_set)
                        this.margin_acc_open_div_is_modified = true;
                    this.margin_acc_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("maruyu_appli_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'maruyu_appli_div' must be String: '"+value+"' is not." );
                    this.maruyu_appli_div = (String) value;
                    if (this.maruyu_appli_div_is_set)
                        this.maruyu_appli_div_is_modified = true;
                    this.maruyu_appli_div_is_set = true;
                    return;
                }
                else if ( name.equals("maruyu_laundering_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'maruyu_laundering_div' must be String: '"+value+"' is not." );
                    this.maruyu_laundering_div = (String) value;
                    if (this.maruyu_laundering_div_is_set)
                        this.maruyu_laundering_div_is_modified = true;
                    this.maruyu_laundering_div_is_set = true;
                    return;
                }
                else if ( name.equals("mort_trade_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mort_trade_open_div' must be String: '"+value+"' is not." );
                    this.mort_trade_open_div = (String) value;
                    if (this.mort_trade_open_div_is_set)
                        this.mort_trade_open_div_is_modified = true;
                    this.mort_trade_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("maruyu_grade_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'maruyu_grade_div' must be String: '"+value+"' is not." );
                    this.maruyu_grade_div = (String) value;
                    if (this.maruyu_grade_div_is_set)
                        this.maruyu_grade_div_is_modified = true;
                    this.maruyu_grade_div_is_set = true;
                    return;
                }
                else if ( name.equals("mrf_acc_open_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mrf_acc_open_div' must be String: '"+value+"' is not." );
                    this.mrf_acc_open_div = (String) value;
                    if (this.mrf_acc_open_div_is_set)
                        this.mrf_acc_open_div_is_modified = true;
                    this.mrf_acc_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("margin_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_cont_div' must be String: '"+value+"' is not." );
                    this.margin_cont_div = (String) value;
                    if (this.margin_cont_div_is_set)
                        this.margin_cont_div_is_modified = true;
                    this.margin_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("mrf_contract_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mrf_contract_div' must be String: '"+value+"' is not." );
                    this.mrf_contract_div = (String) value;
                    if (this.mrf_contract_div_is_set)
                        this.mrf_contract_div_is_modified = true;
                    this.mrf_contract_div_is_set = true;
                    return;
                }
                else if ( name.equals("margin_tax_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_tax_type' must be String: '"+value+"' is not." );
                    this.margin_tax_type = (String) value;
                    if (this.margin_tax_type_is_set)
                        this.margin_tax_type_is_modified = true;
                    this.margin_tax_type_is_set = true;
                    return;
                }
                else if ( name.equals("margin_sp_acc_open_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'margin_sp_acc_open_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.margin_sp_acc_open_date = (java.sql.Timestamp) value;
                    if (this.margin_sp_acc_open_date_is_set)
                        this.margin_sp_acc_open_date_is_modified = true;
                    this.margin_sp_acc_open_date_is_set = true;
                    return;
                }
                else if ( name.equals("margin_tax_type_last") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_tax_type_last' must be String: '"+value+"' is not." );
                    this.margin_tax_type_last = (String) value;
                    if (this.margin_tax_type_last_is_set)
                        this.margin_tax_type_last_is_modified = true;
                    this.margin_tax_type_last_is_set = true;
                    return;
                }
                else if ( name.equals("margin_tax_type_next") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_tax_type_next' must be String: '"+value+"' is not." );
                    this.margin_tax_type_next = (String) value;
                    if (this.margin_tax_type_next_is_set)
                        this.margin_tax_type_next_is_modified = true;
                    this.margin_tax_type_next_is_set = true;
                    return;
                }
                else if ( name.equals("margin_fluct_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'margin_fluct_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.margin_fluct_date = (java.sql.Timestamp) value;
                    if (this.margin_fluct_date_is_set)
                        this.margin_fluct_date_is_modified = true;
                    this.margin_fluct_date_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("new_monthly_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'new_monthly_div' must be String: '"+value+"' is not." );
                    this.new_monthly_div = (String) value;
                    if (this.new_monthly_div_is_set)
                        this.new_monthly_div_is_modified = true;
                    this.new_monthly_div_is_set = true;
                    return;
                }
                else if ( name.equals("new_tb_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'new_tb_open_div' must be String: '"+value+"' is not." );
                    this.new_tb_open_div = (String) value;
                    if (this.new_tb_open_div_is_set)
                        this.new_tb_open_div_is_modified = true;
                    this.new_tb_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("nq_j_gl_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'nq_j_gl_cont_div' must be String: '"+value+"' is not." );
                    this.nq_j_gl_cont_div = (String) value;
                    if (this.nq_j_gl_cont_div_is_set)
                        this.nq_j_gl_cont_div_is_modified = true;
                    this.nq_j_gl_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("nagoya_grw_cpy_mkt_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'nagoya_grw_cpy_mkt_cont_div' must be String: '"+value+"' is not." );
                    this.nagoya_grw_cpy_mkt_cont_div = (String) value;
                    if (this.nagoya_grw_cpy_mkt_cont_div_is_set)
                        this.nagoya_grw_cpy_mkt_cont_div_is_modified = true;
                    this.nagoya_grw_cpy_mkt_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("new_tb_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'new_tb_cont_div' must be String: '"+value+"' is not." );
                    this.new_tb_cont_div = (String) value;
                    if (this.new_tb_cont_div_is_set)
                        this.new_tb_cont_div_is_modified = true;
                    this.new_tb_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("new_account_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'new_account_div' must be String: '"+value+"' is not." );
                    this.new_account_div = (String) value;
                    if (this.new_account_div_is_set)
                        this.new_account_div_is_modified = true;
                    this.new_account_div_is_set = true;
                    return;
                }
                else if ( name.equals("ntt_law") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ntt_law' must be String: '"+value+"' is not." );
                    this.ntt_law = (String) value;
                    if (this.ntt_law_is_set)
                        this.ntt_law_is_modified = true;
                    this.ntt_law_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("occupation") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'occupation' must be String: '"+value+"' is not." );
                    this.occupation = (String) value;
                    if (this.occupation_is_set)
                        this.occupation_is_modified = true;
                    this.occupation_is_set = true;
                    return;
                }
                else if ( name.equals("os_fnc_futures_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'os_fnc_futures_open_div' must be String: '"+value+"' is not." );
                    this.os_fnc_futures_open_div = (String) value;
                    if (this.os_fnc_futures_open_div_is_set)
                        this.os_fnc_futures_open_div_is_modified = true;
                    this.os_fnc_futures_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("os_sec_futures_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'os_sec_futures_open_div' must be String: '"+value+"' is not." );
                    this.os_sec_futures_open_div = (String) value;
                    if (this.os_sec_futures_open_div_is_set)
                        this.os_sec_futures_open_div_is_modified = true;
                    this.os_sec_futures_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("os_cd_cp_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'os_cd_cp_open_div' must be String: '"+value+"' is not." );
                    this.os_cd_cp_open_div = (String) value;
                    if (this.os_cd_cp_open_div_is_set)
                        this.os_cd_cp_open_div_is_modified = true;
                    this.os_cd_cp_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("osaka_nw_mkt_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'osaka_nw_mkt_cont_div' must be String: '"+value+"' is not." );
                    this.osaka_nw_mkt_cont_div = (String) value;
                    if (this.osaka_nw_mkt_cont_div_is_set)
                        this.osaka_nw_mkt_cont_div_is_modified = true;
                    this.osaka_nw_mkt_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("org_deposit_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'org_deposit_div' must be String: '"+value+"' is not." );
                    this.org_deposit_div = (String) value;
                    if (this.org_deposit_div_is_set)
                        this.org_deposit_div_is_modified = true;
                    this.org_deposit_div_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("prefecture") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'prefecture' must be String: '"+value+"' is not." );
                    this.prefecture = (String) value;
                    if (this.prefecture_is_set)
                        this.prefecture_is_modified = true;
                    this.prefecture_is_set = true;
                    return;
                }
                else if ( name.equals("pass_err_code_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pass_err_code_type' must be String: '"+value+"' is not." );
                    this.pass_err_code_type = (String) value;
                    if (this.pass_err_code_type_is_set)
                        this.pass_err_code_type_is_modified = true;
                    this.pass_err_code_type_is_set = true;
                    return;
                }
                else if ( name.equals("pass_err_code_number") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pass_err_code_number' must be String: '"+value+"' is not." );
                    this.pass_err_code_number = (String) value;
                    if (this.pass_err_code_number_is_set)
                        this.pass_err_code_number_is_modified = true;
                    this.pass_err_code_number_is_set = true;
                    return;
                }
                else if ( name.equals("pass_err_code_chg_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'pass_err_code_chg_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.pass_err_code_chg_date = (java.sql.Timestamp) value;
                    if (this.pass_err_code_chg_date_is_set)
                        this.pass_err_code_chg_date_is_modified = true;
                    this.pass_err_code_chg_date_is_set = true;
                    return;
                }
                else if ( name.equals("pass_err_code_chg_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pass_err_code_chg_time' must be String: '"+value+"' is not." );
                    this.pass_err_code_chg_time = (String) value;
                    if (this.pass_err_code_chg_time_is_set)
                        this.pass_err_code_chg_time_is_modified = true;
                    this.pass_err_code_chg_time_is_set = true;
                    return;
                }
                else if ( name.equals("person_identify") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'person_identify' must be String: '"+value+"' is not." );
                    this.person_identify = (String) value;
                    if (this.person_identify_is_set)
                        this.person_identify_is_modified = true;
                    this.person_identify_is_set = true;
                    return;
                }
                else if ( name.equals("position_report_cycle_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'position_report_cycle_div' must be String: '"+value+"' is not." );
                    this.position_report_cycle_div = (String) value;
                    if (this.position_report_cycle_div_is_set)
                        this.position_report_cycle_div_is_modified = true;
                    this.position_report_cycle_div_is_set = true;
                    return;
                }
                else if ( name.equals("position_report_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'position_report_div' must be String: '"+value+"' is not." );
                    this.position_report_div = (String) value;
                    if (this.position_report_div_is_set)
                        this.position_report_div_is_modified = true;
                    this.position_report_div_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("qualified_inst_investor_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'qualified_inst_investor_div' must be String: '"+value+"' is not." );
                    this.qualified_inst_investor_div = (String) value;
                    if (this.qualified_inst_investor_div_is_set)
                        this.qualified_inst_investor_div_is_modified = true;
                    this.qualified_inst_investor_div_is_set = true;
                    return;
                }
                else if ( name.equals("quoto_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'quoto_type' must be String: '"+value+"' is not." );
                    this.quoto_type = (String) value;
                    if (this.quoto_type_is_set)
                        this.quoto_type_is_modified = true;
                    this.quoto_type_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("ruito_acc_open_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ruito_acc_open_div' must be String: '"+value+"' is not." );
                    this.ruito_acc_open_div = (String) value;
                    if (this.ruito_acc_open_div_is_set)
                        this.ruito_acc_open_div_is_modified = true;
                    this.ruito_acc_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("report_dispatch_stop_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'report_dispatch_stop_div' must be String: '"+value+"' is not." );
                    this.report_dispatch_stop_div = (String) value;
                    if (this.report_dispatch_stop_div_is_set)
                        this.report_dispatch_stop_div_is_modified = true;
                    this.report_dispatch_stop_div_is_set = true;
                    return;
                }
                else if ( name.equals("rep_dispatch_stp_bd") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'rep_dispatch_stp_bd' must be String: '"+value+"' is not." );
                    this.rep_dispatch_stp_bd = (String) value;
                    if (this.rep_dispatch_stp_bd_is_set)
                        this.rep_dispatch_stp_bd_is_modified = true;
                    this.rep_dispatch_stp_bd_is_set = true;
                    return;
                }
                else if ( name.equals("represent_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'represent_div' must be String: '"+value+"' is not." );
                    this.represent_div = (String) value;
                    if (this.represent_div_is_set)
                        this.represent_div_is_modified = true;
                    this.represent_div_is_set = true;
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
                else if ( name.equals("reserve") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reserve' must be String: '"+value+"' is not." );
                    this.reserve = (String) value;
                    if (this.reserve_is_set)
                        this.reserve_is_modified = true;
                    this.reserve_is_set = true;
                    return;
                }
                else if ( name.equals("reserve_area") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'reserve_area' must be String: '"+value+"' is not." );
                    this.reserve_area = (String) value;
                    if (this.reserve_area_is_set)
                        this.reserve_area_is_modified = true;
                    this.reserve_area_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_trader_code' must be String: '"+value+"' is not." );
                    this.sonar_trader_code = (String) value;
                    if (this.sonar_trader_code_is_set)
                        this.sonar_trader_code_is_modified = true;
                    this.sonar_trader_code_is_set = true;
                    return;
                }
                else if ( name.equals("safe_cont_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'safe_cont_open_div' must be String: '"+value+"' is not." );
                    this.safe_cont_open_div = (String) value;
                    if (this.safe_cont_open_div_is_set)
                        this.safe_cont_open_div_is_modified = true;
                    this.safe_cont_open_div_is_set = true;
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
                else if ( name.equals("self_assessed_sep_tax") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'self_assessed_sep_tax' must be String: '"+value+"' is not." );
                    this.self_assessed_sep_tax = (String) value;
                    if (this.self_assessed_sep_tax_is_set)
                        this.self_assessed_sep_tax_is_modified = true;
                    this.self_assessed_sep_tax_is_set = true;
                    return;
                }
                else if ( name.equals("statement_skip1_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'statement_skip1_div' must be String: '"+value+"' is not." );
                    this.statement_skip1_div = (String) value;
                    if (this.statement_skip1_div_is_set)
                        this.statement_skip1_div_is_modified = true;
                    this.statement_skip1_div_is_set = true;
                    return;
                }
                else if ( name.equals("statement_skip2_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'statement_skip2_div' must be String: '"+value+"' is not." );
                    this.statement_skip2_div = (String) value;
                    if (this.statement_skip2_div_is_set)
                        this.statement_skip2_div_is_modified = true;
                    this.statement_skip2_div_is_set = true;
                    return;
                }
                else if ( name.equals("safe_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'safe_cont_div' must be String: '"+value+"' is not." );
                    this.safe_cont_div = (String) value;
                    if (this.safe_cont_div_is_set)
                        this.safe_cont_div_is_modified = true;
                    this.safe_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("sapporo_amb_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sapporo_amb_cont_div' must be String: '"+value+"' is not." );
                    this.sapporo_amb_cont_div = (String) value;
                    if (this.sapporo_amb_cont_div_is_set)
                        this.sapporo_amb_cont_div_is_modified = true;
                    this.sapporo_amb_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("sp_acc_abolish_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'sp_acc_abolish_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.sp_acc_abolish_date = (java.sql.Timestamp) value;
                    if (this.sp_acc_abolish_date_is_set)
                        this.sp_acc_abolish_date_is_modified = true;
                    this.sp_acc_abolish_date_is_set = true;
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
                else if ( name.equals("standing_proxy") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'standing_proxy' must be String: '"+value+"' is not." );
                    this.standing_proxy = (String) value;
                    if (this.standing_proxy_is_set)
                        this.standing_proxy_is_modified = true;
                    this.standing_proxy_is_set = true;
                    return;
                }
                else if ( name.equals("statutory_agent") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'statutory_agent' must be String: '"+value+"' is not." );
                    this.statutory_agent = (String) value;
                    if (this.statutory_agent_is_set)
                        this.statutory_agent_is_modified = true;
                    this.statutory_agent_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("tokuyu_acc_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tokuyu_acc_open_div' must be String: '"+value+"' is not." );
                    this.tokuyu_acc_open_div = (String) value;
                    if (this.tokuyu_acc_open_div_is_set)
                        this.tokuyu_acc_open_div_is_modified = true;
                    this.tokuyu_acc_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("total_trade_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'total_trade_open_div' must be String: '"+value+"' is not." );
                    this.total_trade_open_div = (String) value;
                    if (this.total_trade_open_div_is_set)
                        this.total_trade_open_div_is_modified = true;
                    this.total_trade_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("tie_up_loan_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tie_up_loan_open_div' must be String: '"+value+"' is not." );
                    this.tie_up_loan_open_div = (String) value;
                    if (this.tie_up_loan_open_div_is_set)
                        this.tie_up_loan_open_div_is_modified = true;
                    this.tie_up_loan_open_div_is_set = true;
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
                else if ( name.equals("tokuyu_appli_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tokuyu_appli_div' must be String: '"+value+"' is not." );
                    this.tokuyu_appli_div = (String) value;
                    if (this.tokuyu_appli_div_is_set)
                        this.tokuyu_appli_div_is_modified = true;
                    this.tokuyu_appli_div_is_set = true;
                    return;
                }
                else if ( name.equals("tokuyu_laundering_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tokuyu_laundering_div' must be String: '"+value+"' is not." );
                    this.tokuyu_laundering_div = (String) value;
                    if (this.tokuyu_laundering_div_is_set)
                        this.tokuyu_laundering_div_is_modified = true;
                    this.tokuyu_laundering_div_is_set = true;
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
                else if ( name.equals("total_tax_identity") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'total_tax_identity' must be String: '"+value+"' is not." );
                    this.total_tax_identity = (String) value;
                    if (this.total_tax_identity_is_set)
                        this.total_tax_identity_is_modified = true;
                    this.total_tax_identity_is_set = true;
                    return;
                }
                else if ( name.equals("tokyo_fnc_futures_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tokyo_fnc_futures_open_div' must be String: '"+value+"' is not." );
                    this.tokyo_fnc_futures_open_div = (String) value;
                    if (this.tokyo_fnc_futures_open_div_is_set)
                        this.tokyo_fnc_futures_open_div_is_modified = true;
                    this.tokyo_fnc_futures_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("tentou_trade_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tentou_trade_open_div' must be String: '"+value+"' is not." );
                    this.tentou_trade_open_div = (String) value;
                    if (this.tentou_trade_open_div_is_set)
                        this.tentou_trade_open_div_is_modified = true;
                    this.tentou_trade_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("trans_tax_div_acc_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'trans_tax_div_acc_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.trans_tax_div_acc_date = (java.sql.Timestamp) value;
                    if (this.trans_tax_div_acc_date_is_set)
                        this.trans_tax_div_acc_date_is_modified = true;
                    this.trans_tax_div_acc_date_is_set = true;
                    return;
                }
                else if ( name.equals("trans_tax_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trans_tax_div' must be String: '"+value+"' is not." );
                    this.trans_tax_div = (String) value;
                    if (this.trans_tax_div_is_set)
                        this.trans_tax_div_is_modified = true;
                    this.trans_tax_div_is_set = true;
                    return;
                }
                else if ( name.equals("t_bond_futures_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 't_bond_futures_open_div' must be String: '"+value+"' is not." );
                    this.t_bond_futures_open_div = (String) value;
                    if (this.t_bond_futures_open_div_is_set)
                        this.t_bond_futures_open_div_is_modified = true;
                    this.t_bond_futures_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("tentou_sp_rule_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tentou_sp_rule_open_div' must be String: '"+value+"' is not." );
                    this.tentou_sp_rule_open_div = (String) value;
                    if (this.tentou_sp_rule_open_div_is_set)
                        this.tentou_sp_rule_open_div_is_modified = true;
                    this.tentou_sp_rule_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("tokyo_mothers_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tokyo_mothers_cont_div' must be String: '"+value+"' is not." );
                    this.tokyo_mothers_cont_div = (String) value;
                    if (this.tokyo_mothers_cont_div_is_set)
                        this.tokyo_mothers_cont_div_is_modified = true;
                    this.tokyo_mothers_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("tentou_trade_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tentou_trade_cont_div' must be String: '"+value+"' is not." );
                    this.tentou_trade_cont_div = (String) value;
                    if (this.tentou_trade_cont_div_is_set)
                        this.tentou_trade_cont_div_is_modified = true;
                    this.tentou_trade_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("tentou_sec_basis_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tentou_sec_basis_cont_div' must be String: '"+value+"' is not." );
                    this.tentou_sec_basis_cont_div = (String) value;
                    if (this.tentou_sec_basis_cont_div_is_set)
                        this.tentou_sec_basis_cont_div_is_modified = true;
                    this.tentou_sec_basis_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("tentou_sp_rule_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tentou_sp_rule_cont_div' must be String: '"+value+"' is not." );
                    this.tentou_sp_rule_cont_div = (String) value;
                    if (this.tentou_sp_rule_cont_div_is_set)
                        this.tentou_sp_rule_cont_div_is_modified = true;
                    this.tentou_sp_rule_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("trading_report_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trading_report_div' must be String: '"+value+"' is not." );
                    this.trading_report_div = (String) value;
                    if (this.trading_report_div_is_set)
                        this.trading_report_div_is_modified = true;
                    this.trading_report_div_is_set = true;
                    return;
                }
                else if ( name.equals("tax_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tax_type' must be String: '"+value+"' is not." );
                    this.tax_type = (String) value;
                    if (this.tax_type_is_set)
                        this.tax_type_is_modified = true;
                    this.tax_type_is_set = true;
                    return;
                }
                else if ( name.equals("tax_type_last") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tax_type_last' must be String: '"+value+"' is not." );
                    this.tax_type_last = (String) value;
                    if (this.tax_type_last_is_set)
                        this.tax_type_last_is_modified = true;
                    this.tax_type_last_is_set = true;
                    return;
                }
                else if ( name.equals("tax_type_next") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tax_type_next' must be String: '"+value+"' is not." );
                    this.tax_type_next = (String) value;
                    if (this.tax_type_next_is_set)
                        this.tax_type_next_is_modified = true;
                    this.tax_type_next_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("via_trust_bank_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'via_trust_bank_div' must be String: '"+value+"' is not." );
                    this.via_trust_bank_div = (String) value;
                    if (this.via_trust_bank_div_is_set)
                        this.via_trust_bank_div_is_modified = true;
                    this.via_trust_bank_div_is_set = true;
                    return;
                }
                break;
            case 'w':
                if ( name.equals("when_issued_acc_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'when_issued_acc_open_div' must be String: '"+value+"' is not." );
                    this.when_issued_acc_open_div = (String) value;
                    if (this.when_issued_acc_open_div_is_set)
                        this.when_issued_acc_open_div_is_modified = true;
                    this.when_issued_acc_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("when_issued_cont_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'when_issued_cont_div' must be String: '"+value+"' is not." );
                    this.when_issued_cont_div = (String) value;
                    if (this.when_issued_cont_div_is_set)
                        this.when_issued_cont_div_is_modified = true;
                    this.when_issued_cont_div_is_set = true;
                    return;
                }
                else if ( name.equals("web3_encrypted_password") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'web3_encrypted_password' must be String: '"+value+"' is not." );
                    this.web3_encrypted_password = (String) value;
                    if (this.web3_encrypted_password_is_set)
                        this.web3_encrypted_password_is_modified = true;
                    this.web3_encrypted_password_is_set = true;
                    return;
                }
                break;
            case 'x':
                if ( name.equals("xtrade_encrypted_password") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'xtrade_encrypted_password' must be String: '"+value+"' is not." );
                    this.xtrade_encrypted_password = (String) value;
                    if (this.xtrade_encrypted_password_is_set)
                        this.xtrade_encrypted_password_is_modified = true;
                    this.xtrade_encrypted_password_is_set = true;
                    return;
                }
                break;
            case 'y':
                if ( name.equals("yellow_customer") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'yellow_customer' must be String: '"+value+"' is not." );
                    this.yellow_customer = (String) value;
                    if (this.yellow_customer_is_set)
                        this.yellow_customer_is_modified = true;
                    this.yellow_customer_is_set = true;
                    return;
                }
                break;
            case 'z':
                if ( name.equals("zip_code") ) {
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
