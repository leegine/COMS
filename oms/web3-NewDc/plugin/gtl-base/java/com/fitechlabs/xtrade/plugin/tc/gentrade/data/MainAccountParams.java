head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.38.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MainAccountParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * main_accountテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link MainAccountRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link MainAccountParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link MainAccountParams}が{@@link MainAccountRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MainAccountPK 
 * @@see MainAccountRow 
 */
public class MainAccountParams extends Params implements MainAccountRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "main_account";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = MainAccountRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return MainAccountRow.TYPE;
  }


  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>institution_id</em>カラムの値 
   */
  public  long  institution_id;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  long  branch_id;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>sonar_trader_code</em>カラムの値 
   */
  public  String  sonar_trader_code;

  /** 
   * <em>account_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum  account_type;

  /** 
   * <em>family_name</em>カラムの値 
   */
  public  String  family_name;

  /** 
   * <em>middle_name</em>カラムの値 
   */
  public  String  middle_name;

  /** 
   * <em>given_name</em>カラムの値 
   */
  public  String  given_name;

  /** 
   * <em>family_name_alt1</em>カラムの値 
   */
  public  String  family_name_alt1;

  /** 
   * <em>middle_name_alt1</em>カラムの値 
   */
  public  String  middle_name_alt1;

  /** 
   * <em>given_name_alt1</em>カラムの値 
   */
  public  String  given_name_alt1;

  /** 
   * <em>family_name_alt2</em>カラムの値 
   */
  public  String  family_name_alt2;

  /** 
   * <em>middle_name_alt2</em>カラムの値 
   */
  public  String  middle_name_alt2;

  /** 
   * <em>given_name_alt2</em>カラムの値 
   */
  public  String  given_name_alt2;

  /** 
   * <em>zip_code</em>カラムの値 
   */
  public  String  zip_code;

  /** 
   * <em>sub_zip_code</em>カラムの値 
   */
  public  String  sub_zip_code;

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

  /** 
   * <em>telephone</em>カラムの値 
   */
  public  String  telephone;

  /** 
   * <em>mobile</em>カラムの値 
   */
  public  String  mobile;

  /** 
   * <em>fax</em>カラムの値 
   */
  public  String  fax;

  /** 
   * <em>office</em>カラムの値 
   */
  public  String  office;

  /** 
   * <em>office_zip_code</em>カラムの値 
   */
  public  String  office_zip_code;

  /** 
   * <em>office_address</em>カラムの値 
   */
  public  String  office_address;

  /** 
   * <em>office_telephone</em>カラムの値 
   */
  public  String  office_telephone;

  /** 
   * <em>post</em>カラムの値 
   */
  public  String  post;

  /** 
   * <em>equity_order_exe_mail_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  equity_order_exe_mail_flag;

  /** 
   * <em>equity_order_unexec_mail_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  equity_order_unexec_mail_flag;

  /** 
   * <em>ifo_order_exec_mail_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  ifo_order_exec_mail_flag;

  /** 
   * <em>ifo_order_unexec_mail_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  ifo_order_unexec_mail_flag;

  /** 
   * <em>information_mail_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  information_mail_flag;

  /** 
   * <em>resident</em>カラムの値 
   */
  public  String  resident;

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
   * <em>email_address</em>カラムの値 
   */
  public  String  email_address;

  /** 
   * <em>email_address_alt1</em>カラムの値 
   */
  public  String  email_address_alt1;

  /** 
   * <em>trading_password</em>カラムの値 
   */
  public  String  trading_password;

  /** 
   * <em>account_open_date</em>カラムの値 
   */
  public  java.sql.Timestamp  account_open_date;

  /** 
   * <em>account_close_date</em>カラムの値 
   */
  public  java.sql.Timestamp  account_close_date;

  /** 
   * <em>acc_open_send_mail_status</em>カラムの値 
   */
  public  String  acc_open_send_mail_status;

  /** 
   * <em>person_identify</em>カラムの値 
   */
  public  String  person_identify;

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
   * <em>yellow_customer</em>カラムの値 
   */
  public  String  yellow_customer;

  /** 
   * <em>ht_settlement_way</em>カラムの値 
   */
  public  String  ht_settlement_way;

  /** 
   * <em>bank_account_regi</em>カラムの値 
   */
  public  String  bank_account_regi;

  /** 
   * <em>account_status</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum  account_status;

  /** 
   * <em>examin_lock_flag</em>カラムの値 
   */
  public  String  examin_lock_flag;

  /** 
   * <em>mng_lock_flag</em>カラムの値 
   */
  public  String  mng_lock_flag;

  /** 
   * <em>mng_lock_off_start_date</em>カラムの値 
   */
  public  java.sql.Timestamp  mng_lock_off_start_date;

  /** 
   * <em>mng_lock_off_end_date</em>カラムの値 
   */
  public  java.sql.Timestamp  mng_lock_off_end_date;

  /** 
   * <em>mng_lock_flag_advance</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  mng_lock_flag_advance;

  /** 
   * <em>mng_lock_flag_unpay_margin</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  mng_lock_flag_unpay_margin;

  /** 
   * <em>mng_lock_flag_short_security</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  mng_lock_flag_short_security;

  /** 
   * <em>mng_lock_flag_unsubstit_depo</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  mng_lock_flag_unsubstit_depo;

  /** 
   * <em>branch_lock</em>カラムの値 
   */
  public  String  branch_lock;

  /** 
   * <em>order_permission</em>カラムの値 
   */
  public  String  order_permission;

  /** 
   * <em>tax_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum  tax_type;

  /** 
   * <em>tax_type_next</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum  tax_type_next;

  /** 
   * <em>margin_tax_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum  margin_tax_type;

  /** 
   * <em>margin_tax_type_next</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum  margin_tax_type_next;

  /** 
   * <em>qualified_inst_investor_div</em>カラムの値 
   */
  public  String  qualified_inst_investor_div;

  /** 
   * <em>margin_sys_acc_open_div</em>カラムの値 
   */
  public  String  margin_sys_acc_open_div;

  /** 
   * <em>margin_gen_acc_open_div</em>カラムの値 
   */
  public  String  margin_gen_acc_open_div;

  /** 
   * <em>equity_sp_acc_open_date</em>カラムの値 
   */
  public  java.sql.Timestamp  equity_sp_acc_open_date;

  /** 
   * <em>margin_sp_acc_open_date</em>カラムの値 
   */
  public  java.sql.Timestamp  margin_sp_acc_open_date;

  /** 
   * <em>transfer_count</em>カラムの値 
   */
  public  Integer  transfer_count;

  /** 
   * <em>foreign_sec_acc_open_div</em>カラムの値 
   */
  public  String  foreign_sec_acc_open_div;

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
   * <em>ruito_acc_open_div</em>カラムの値 
   */
  public  String  ruito_acc_open_div;

  /** 
   * <em>mrf_acc_open_div</em>カラムの値 
   */
  public  String  mrf_acc_open_div;

  /** 
   * <em>fx_acc_open_div</em>カラムの値 
   */
  public  String  fx_acc_open_div;

  /** 
   * <em>feq_con_acc_open_div</em>カラムの値 
   */
  public  String  feq_con_acc_open_div;

  /** 
   * <em>top_page_id</em>カラムの値 
   */
  public  String  top_page_id;

  /** 
   * <em>quoto_type</em>カラムの値 
   */
  public  String  quoto_type;

  /** 
   * <em>trading_report_div</em>カラムの値 
   */
  public  String  trading_report_div;

  /** 
   * <em>position_report_div</em>カラムの値 
   */
  public  String  position_report_div;

  /** 
   * <em>position_report_cycle_div</em>カラムの値 
   */
  public  String  position_report_cycle_div;

  /** 
   * <em>certificate_deposit_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  certificate_deposit_flag;

  /** 
   * <em>account_statement_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  account_statement_flag;

  /** 
   * <em>email_last_updater</em>カラムの値 
   */
  public  String  email_last_updater;

  /** 
   * <em>email_last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  email_last_updated_timestamp;

  /** 
   * <em>trading_password_updater</em>カラムの値 
   */
  public  String  trading_password_updater;

  /** 
   * <em>tr_pwd_last_update_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  tr_pwd_last_update_timestamp;

  /** 
   * <em>mb_off_last_updater</em>カラムの値 
   */
  public  String  mb_off_last_updater;

  /** 
   * <em>mb_off_last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  mb_off_last_updated_timestamp;

  /** 
   * <em>enable_order_last_updater</em>カラムの値 
   */
  public  String  enable_order_last_updater;

  /** 
   * <em>enable_order_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  enable_order_updated_timestamp;

  /** 
   * <em>fx_acc_open_div_last_updater</em>カラムの値 
   */
  public  String  fx_acc_open_div_last_updater;

  /** 
   * <em>fx_acc_open_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  fx_acc_open_updated_timestamp;

  /** 
   * <em>feq_con_acc_open_div_updater</em>カラムの値 
   */
  public  String  feq_con_acc_open_div_updater;

  /** 
   * <em>feq_con_acc_open_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  feq_con_acc_open_timestamp;

  /** 
   * <em>mrf_fund_code</em>カラムの値 
   */
  public  String  mrf_fund_code;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>sp_mng_acc_open_div</em>カラムの値 
   */
  public  String  sp_mng_acc_open_div;

  /** 
   * <em>lock_registration_reason</em>カラムの値 
   */
  public  String  lock_registration_reason;

  /** 
   * <em>inf_mail_flg_last_updater</em>カラムの値 
   */
  public  String  inf_mail_flg_last_updater;

  /** 
   * <em>inf_mail_flg_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  inf_mail_flg_updated_timestamp;

  /** 
   * <em>eq_exe_ml_flg_last_updater</em>カラムの値 
   */
  public  String  eq_exe_ml_flg_last_updater;

  /** 
   * <em>eq_exe_ml_flg_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  eq_exe_ml_flg_timestamp;

  /** 
   * <em>eq_unexe_ml_flg_last_updater</em>カラムの値 
   */
  public  String  eq_unexe_ml_flg_last_updater;

  /** 
   * <em>eq_unexe_ml_flg_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  eq_unexe_ml_flg_timestamp;

  /** 
   * <em>ifo_exe_ml_flg_last_updater</em>カラムの値 
   */
  public  String  ifo_exe_ml_flg_last_updater;

  /** 
   * <em>ifo_exe_ml_flg_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  ifo_exe_ml_flg_timestamp;

  /** 
   * <em>ifo_unexe_ml_flg_last_updater</em>カラムの値 
   */
  public  String  ifo_unexe_ml_flg_last_updater;

  /** 
   * <em>ifo_unexe_ml_flg_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  ifo_unexe_ml_flg_timestamp;

  /** 
   * <em>stock_option_acc_open_div</em>カラムの値 
   */
  public  String  stock_option_acc_open_div;

  /** 
   * <em>secured_loan_sec_acc_open_div</em>カラムの値 
   */
  public  String  secured_loan_sec_acc_open_div;

  /** 
   * <em>margin_acc_open_date</em>カラムの値 
   */
  public  java.sql.Timestamp  margin_acc_open_date;

  /** 
   * <em>ifo_acc_open_date</em>カラムの値 
   */
  public  java.sql.Timestamp  ifo_acc_open_date;

  /** 
   * <em>only_mobile_open_div</em>カラムの値 
   */
  public  String  only_mobile_open_div;

  /** 
   * <em>only_mbl_opn_div_last_updater</em>カラムの値 
   */
  public  String  only_mbl_opn_div_last_updater;

  /** 
   * <em>only_mbl_opn_div_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  only_mbl_opn_div_timestamp;

  /** 
   * <em>email_address_alt2</em>カラムの値 
   */
  public  String  email_address_alt2;

  /** 
   * <em>order_exe_flag</em>カラムの値 
   */
  public  Integer  order_exe_flag;

  /** 
   * <em>order_unexe_flag</em>カラムの値 
   */
  public  Integer  order_unexe_flag;

  /** 
   * <em>important_connection_mail_flag</em>カラムの値 
   */
  public  Integer  important_connection_mail_flag;

  /** 
   * <em>information_mail2_flag</em>カラムの値 
   */
  public  Integer  information_mail2_flag;

  /** 
   * <em>proam_div</em>カラムの値 
   */
  public  String  proam_div;

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
   * <em>pts_acc_open_div</em>カラムの値 
   */
  public  String  pts_acc_open_div;

  /** 
   * <em>pts_acc_open_div_last_updater</em>カラムの値 
   */
  public  String  pts_acc_open_div_last_updater;

  /** 
   * <em>pts_acc_open_div_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  pts_acc_open_div_timestamp;

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

  /** 
   * <em>cfd_acc_open_div</em>カラムの値 
   */
  public  String  cfd_acc_open_div;

  /** 
   * <em>cfd_acc_open_div_last_updater</em>カラムの値 
   */
  public  String  cfd_acc_open_div_last_updater;

  /** 
   * <em>cfd_acc_open_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  cfd_acc_open_updated_timestamp;

  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean institution_id_is_set = false;
  private boolean institution_id_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean sonar_trader_code_is_set = false;
  private boolean sonar_trader_code_is_modified = false;
  private boolean account_type_is_set = false;
  private boolean account_type_is_modified = false;
  private boolean family_name_is_set = false;
  private boolean family_name_is_modified = false;
  private boolean middle_name_is_set = false;
  private boolean middle_name_is_modified = false;
  private boolean given_name_is_set = false;
  private boolean given_name_is_modified = false;
  private boolean family_name_alt1_is_set = false;
  private boolean family_name_alt1_is_modified = false;
  private boolean middle_name_alt1_is_set = false;
  private boolean middle_name_alt1_is_modified = false;
  private boolean given_name_alt1_is_set = false;
  private boolean given_name_alt1_is_modified = false;
  private boolean family_name_alt2_is_set = false;
  private boolean family_name_alt2_is_modified = false;
  private boolean middle_name_alt2_is_set = false;
  private boolean middle_name_alt2_is_modified = false;
  private boolean given_name_alt2_is_set = false;
  private boolean given_name_alt2_is_modified = false;
  private boolean zip_code_is_set = false;
  private boolean zip_code_is_modified = false;
  private boolean sub_zip_code_is_set = false;
  private boolean sub_zip_code_is_modified = false;
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
  private boolean telephone_is_set = false;
  private boolean telephone_is_modified = false;
  private boolean mobile_is_set = false;
  private boolean mobile_is_modified = false;
  private boolean fax_is_set = false;
  private boolean fax_is_modified = false;
  private boolean office_is_set = false;
  private boolean office_is_modified = false;
  private boolean office_zip_code_is_set = false;
  private boolean office_zip_code_is_modified = false;
  private boolean office_address_is_set = false;
  private boolean office_address_is_modified = false;
  private boolean office_telephone_is_set = false;
  private boolean office_telephone_is_modified = false;
  private boolean post_is_set = false;
  private boolean post_is_modified = false;
  private boolean equity_order_exe_mail_flag_is_set = false;
  private boolean equity_order_exe_mail_flag_is_modified = false;
  private boolean equity_order_unexec_mail_flag_is_set = false;
  private boolean equity_order_unexec_mail_flag_is_modified = false;
  private boolean ifo_order_exec_mail_flag_is_set = false;
  private boolean ifo_order_exec_mail_flag_is_modified = false;
  private boolean ifo_order_unexec_mail_flag_is_set = false;
  private boolean ifo_order_unexec_mail_flag_is_modified = false;
  private boolean information_mail_flag_is_set = false;
  private boolean information_mail_flag_is_modified = false;
  private boolean resident_is_set = false;
  private boolean resident_is_modified = false;
  private boolean new_account_div_is_set = false;
  private boolean new_account_div_is_modified = false;
  private boolean via_trust_bank_div_is_set = false;
  private boolean via_trust_bank_div_is_modified = false;
  private boolean class_div_is_set = false;
  private boolean class_div_is_modified = false;
  private boolean email_address_is_set = false;
  private boolean email_address_is_modified = false;
  private boolean email_address_alt1_is_set = false;
  private boolean email_address_alt1_is_modified = false;
  private boolean trading_password_is_set = false;
  private boolean trading_password_is_modified = false;
  private boolean account_open_date_is_set = false;
  private boolean account_open_date_is_modified = false;
  private boolean account_close_date_is_set = false;
  private boolean account_close_date_is_modified = false;
  private boolean acc_open_send_mail_status_is_set = false;
  private boolean acc_open_send_mail_status_is_modified = false;
  private boolean person_identify_is_set = false;
  private boolean person_identify_is_modified = false;
  private boolean era_born_is_set = false;
  private boolean era_born_is_modified = false;
  private boolean born_date_is_set = false;
  private boolean born_date_is_modified = false;
  private boolean sex_is_set = false;
  private boolean sex_is_modified = false;
  private boolean yellow_customer_is_set = false;
  private boolean yellow_customer_is_modified = false;
  private boolean ht_settlement_way_is_set = false;
  private boolean ht_settlement_way_is_modified = false;
  private boolean bank_account_regi_is_set = false;
  private boolean bank_account_regi_is_modified = false;
  private boolean account_status_is_set = false;
  private boolean account_status_is_modified = false;
  private boolean examin_lock_flag_is_set = false;
  private boolean examin_lock_flag_is_modified = false;
  private boolean mng_lock_flag_is_set = false;
  private boolean mng_lock_flag_is_modified = false;
  private boolean mng_lock_off_start_date_is_set = false;
  private boolean mng_lock_off_start_date_is_modified = false;
  private boolean mng_lock_off_end_date_is_set = false;
  private boolean mng_lock_off_end_date_is_modified = false;
  private boolean mng_lock_flag_advance_is_set = false;
  private boolean mng_lock_flag_advance_is_modified = false;
  private boolean mng_lock_flag_unpay_margin_is_set = false;
  private boolean mng_lock_flag_unpay_margin_is_modified = false;
  private boolean mng_lock_flag_short_security_is_set = false;
  private boolean mng_lock_flag_short_security_is_modified = false;
  private boolean mng_lock_flag_unsubstit_depo_is_set = false;
  private boolean mng_lock_flag_unsubstit_depo_is_modified = false;
  private boolean branch_lock_is_set = false;
  private boolean branch_lock_is_modified = false;
  private boolean order_permission_is_set = false;
  private boolean order_permission_is_modified = false;
  private boolean tax_type_is_set = false;
  private boolean tax_type_is_modified = false;
  private boolean tax_type_next_is_set = false;
  private boolean tax_type_next_is_modified = false;
  private boolean margin_tax_type_is_set = false;
  private boolean margin_tax_type_is_modified = false;
  private boolean margin_tax_type_next_is_set = false;
  private boolean margin_tax_type_next_is_modified = false;
  private boolean qualified_inst_investor_div_is_set = false;
  private boolean qualified_inst_investor_div_is_modified = false;
  private boolean margin_sys_acc_open_div_is_set = false;
  private boolean margin_sys_acc_open_div_is_modified = false;
  private boolean margin_gen_acc_open_div_is_set = false;
  private boolean margin_gen_acc_open_div_is_modified = false;
  private boolean equity_sp_acc_open_date_is_set = false;
  private boolean equity_sp_acc_open_date_is_modified = false;
  private boolean margin_sp_acc_open_date_is_set = false;
  private boolean margin_sp_acc_open_date_is_modified = false;
  private boolean transfer_count_is_set = false;
  private boolean transfer_count_is_modified = false;
  private boolean foreign_sec_acc_open_div_is_set = false;
  private boolean foreign_sec_acc_open_div_is_modified = false;
  private boolean ifo_acc_open_div_tokyo_is_set = false;
  private boolean ifo_acc_open_div_tokyo_is_modified = false;
  private boolean ifo_acc_open_div_osaka_is_set = false;
  private boolean ifo_acc_open_div_osaka_is_modified = false;
  private boolean ifo_acc_open_div_nagoya_is_set = false;
  private boolean ifo_acc_open_div_nagoya_is_modified = false;
  private boolean ruito_acc_open_div_is_set = false;
  private boolean ruito_acc_open_div_is_modified = false;
  private boolean mrf_acc_open_div_is_set = false;
  private boolean mrf_acc_open_div_is_modified = false;
  private boolean fx_acc_open_div_is_set = false;
  private boolean fx_acc_open_div_is_modified = false;
  private boolean feq_con_acc_open_div_is_set = false;
  private boolean feq_con_acc_open_div_is_modified = false;
  private boolean top_page_id_is_set = false;
  private boolean top_page_id_is_modified = false;
  private boolean quoto_type_is_set = false;
  private boolean quoto_type_is_modified = false;
  private boolean trading_report_div_is_set = false;
  private boolean trading_report_div_is_modified = false;
  private boolean position_report_div_is_set = false;
  private boolean position_report_div_is_modified = false;
  private boolean position_report_cycle_div_is_set = false;
  private boolean position_report_cycle_div_is_modified = false;
  private boolean certificate_deposit_flag_is_set = false;
  private boolean certificate_deposit_flag_is_modified = false;
  private boolean account_statement_flag_is_set = false;
  private boolean account_statement_flag_is_modified = false;
  private boolean email_last_updater_is_set = false;
  private boolean email_last_updater_is_modified = false;
  private boolean email_last_updated_timestamp_is_set = false;
  private boolean email_last_updated_timestamp_is_modified = false;
  private boolean trading_password_updater_is_set = false;
  private boolean trading_password_updater_is_modified = false;
  private boolean tr_pwd_last_update_timestamp_is_set = false;
  private boolean tr_pwd_last_update_timestamp_is_modified = false;
  private boolean mb_off_last_updater_is_set = false;
  private boolean mb_off_last_updater_is_modified = false;
  private boolean mb_off_last_updated_timestamp_is_set = false;
  private boolean mb_off_last_updated_timestamp_is_modified = false;
  private boolean enable_order_last_updater_is_set = false;
  private boolean enable_order_last_updater_is_modified = false;
  private boolean enable_order_updated_timestamp_is_set = false;
  private boolean enable_order_updated_timestamp_is_modified = false;
  private boolean fx_acc_open_div_last_updater_is_set = false;
  private boolean fx_acc_open_div_last_updater_is_modified = false;
  private boolean fx_acc_open_updated_timestamp_is_set = false;
  private boolean fx_acc_open_updated_timestamp_is_modified = false;
  private boolean feq_con_acc_open_div_updater_is_set = false;
  private boolean feq_con_acc_open_div_updater_is_modified = false;
  private boolean feq_con_acc_open_timestamp_is_set = false;
  private boolean feq_con_acc_open_timestamp_is_modified = false;
  private boolean mrf_fund_code_is_set = false;
  private boolean mrf_fund_code_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean sp_mng_acc_open_div_is_set = false;
  private boolean sp_mng_acc_open_div_is_modified = false;
  private boolean lock_registration_reason_is_set = false;
  private boolean lock_registration_reason_is_modified = false;
  private boolean inf_mail_flg_last_updater_is_set = false;
  private boolean inf_mail_flg_last_updater_is_modified = false;
  private boolean inf_mail_flg_updated_timestamp_is_set = false;
  private boolean inf_mail_flg_updated_timestamp_is_modified = false;
  private boolean eq_exe_ml_flg_last_updater_is_set = false;
  private boolean eq_exe_ml_flg_last_updater_is_modified = false;
  private boolean eq_exe_ml_flg_timestamp_is_set = false;
  private boolean eq_exe_ml_flg_timestamp_is_modified = false;
  private boolean eq_unexe_ml_flg_last_updater_is_set = false;
  private boolean eq_unexe_ml_flg_last_updater_is_modified = false;
  private boolean eq_unexe_ml_flg_timestamp_is_set = false;
  private boolean eq_unexe_ml_flg_timestamp_is_modified = false;
  private boolean ifo_exe_ml_flg_last_updater_is_set = false;
  private boolean ifo_exe_ml_flg_last_updater_is_modified = false;
  private boolean ifo_exe_ml_flg_timestamp_is_set = false;
  private boolean ifo_exe_ml_flg_timestamp_is_modified = false;
  private boolean ifo_unexe_ml_flg_last_updater_is_set = false;
  private boolean ifo_unexe_ml_flg_last_updater_is_modified = false;
  private boolean ifo_unexe_ml_flg_timestamp_is_set = false;
  private boolean ifo_unexe_ml_flg_timestamp_is_modified = false;
  private boolean stock_option_acc_open_div_is_set = false;
  private boolean stock_option_acc_open_div_is_modified = false;
  private boolean secured_loan_sec_acc_open_div_is_set = false;
  private boolean secured_loan_sec_acc_open_div_is_modified = false;
  private boolean margin_acc_open_date_is_set = false;
  private boolean margin_acc_open_date_is_modified = false;
  private boolean ifo_acc_open_date_is_set = false;
  private boolean ifo_acc_open_date_is_modified = false;
  private boolean only_mobile_open_div_is_set = false;
  private boolean only_mobile_open_div_is_modified = false;
  private boolean only_mbl_opn_div_last_updater_is_set = false;
  private boolean only_mbl_opn_div_last_updater_is_modified = false;
  private boolean only_mbl_opn_div_timestamp_is_set = false;
  private boolean only_mbl_opn_div_timestamp_is_modified = false;
  private boolean email_address_alt2_is_set = false;
  private boolean email_address_alt2_is_modified = false;
  private boolean order_exe_flag_is_set = false;
  private boolean order_exe_flag_is_modified = false;
  private boolean order_unexe_flag_is_set = false;
  private boolean order_unexe_flag_is_modified = false;
  private boolean important_connection_mail_flag_is_set = false;
  private boolean important_connection_mail_flag_is_modified = false;
  private boolean information_mail2_flag_is_set = false;
  private boolean information_mail2_flag_is_modified = false;
  private boolean proam_div_is_set = false;
  private boolean proam_div_is_modified = false;
  private boolean org_deposit_div_is_set = false;
  private boolean org_deposit_div_is_modified = false;
  private boolean eq_hold_rep_div_is_set = false;
  private boolean eq_hold_rep_div_is_modified = false;
  private boolean chkup_rep_div_is_set = false;
  private boolean chkup_rep_div_is_modified = false;
  private boolean pts_acc_open_div_is_set = false;
  private boolean pts_acc_open_div_is_modified = false;
  private boolean pts_acc_open_div_last_updater_is_set = false;
  private boolean pts_acc_open_div_last_updater_is_modified = false;
  private boolean pts_acc_open_div_timestamp_is_set = false;
  private boolean pts_acc_open_div_timestamp_is_modified = false;
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
  private boolean cfd_acc_open_div_is_set = false;
  private boolean cfd_acc_open_div_is_modified = false;
  private boolean cfd_acc_open_div_last_updater_is_set = false;
  private boolean cfd_acc_open_div_last_updater_is_modified = false;
  private boolean cfd_acc_open_updated_timestamp_is_set = false;
  private boolean cfd_acc_open_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "account_id=" + account_id
      + "," + "institution_code=" +institution_code
      + "," + "institution_id=" +institution_id
      + "," + "account_code=" +account_code
      + "," + "branch_id=" +branch_id
      + "," + "branch_code=" +branch_code
      + "," + "sonar_trader_code=" +sonar_trader_code
      + "," + "account_type=" +account_type
      + "," + "family_name=" +family_name
      + "," + "middle_name=" +middle_name
      + "," + "given_name=" +given_name
      + "," + "family_name_alt1=" +family_name_alt1
      + "," + "middle_name_alt1=" +middle_name_alt1
      + "," + "given_name_alt1=" +given_name_alt1
      + "," + "family_name_alt2=" +family_name_alt2
      + "," + "middle_name_alt2=" +middle_name_alt2
      + "," + "given_name_alt2=" +given_name_alt2
      + "," + "zip_code=" +zip_code
      + "," + "sub_zip_code=" +sub_zip_code
      + "," + "address_line1=" +address_line1
      + "," + "address_line2=" +address_line2
      + "," + "address_line3=" +address_line3
      + "," + "address_line1_kana=" +address_line1_kana
      + "," + "address_line2_kana=" +address_line2_kana
      + "," + "address_line3_kana=" +address_line3_kana
      + "," + "telephone=" +telephone
      + "," + "mobile=" +mobile
      + "," + "fax=" +fax
      + "," + "office=" +office
      + "," + "office_zip_code=" +office_zip_code
      + "," + "office_address=" +office_address
      + "," + "office_telephone=" +office_telephone
      + "," + "post=" +post
      + "," + "equity_order_exe_mail_flag=" +equity_order_exe_mail_flag
      + "," + "equity_order_unexec_mail_flag=" +equity_order_unexec_mail_flag
      + "," + "ifo_order_exec_mail_flag=" +ifo_order_exec_mail_flag
      + "," + "ifo_order_unexec_mail_flag=" +ifo_order_unexec_mail_flag
      + "," + "information_mail_flag=" +information_mail_flag
      + "," + "resident=" +resident
      + "," + "new_account_div=" +new_account_div
      + "," + "via_trust_bank_div=" +via_trust_bank_div
      + "," + "class_div=" +class_div
      + "," + "email_address=" +email_address
      + "," + "email_address_alt1=" +email_address_alt1
      + "," + "trading_password=" +trading_password
      + "," + "account_open_date=" +account_open_date
      + "," + "account_close_date=" +account_close_date
      + "," + "acc_open_send_mail_status=" +acc_open_send_mail_status
      + "," + "person_identify=" +person_identify
      + "," + "era_born=" +era_born
      + "," + "born_date=" +born_date
      + "," + "sex=" +sex
      + "," + "yellow_customer=" +yellow_customer
      + "," + "ht_settlement_way=" +ht_settlement_way
      + "," + "bank_account_regi=" +bank_account_regi
      + "," + "account_status=" +account_status
      + "," + "examin_lock_flag=" +examin_lock_flag
      + "," + "mng_lock_flag=" +mng_lock_flag
      + "," + "mng_lock_off_start_date=" +mng_lock_off_start_date
      + "," + "mng_lock_off_end_date=" +mng_lock_off_end_date
      + "," + "mng_lock_flag_advance=" +mng_lock_flag_advance
      + "," + "mng_lock_flag_unpay_margin=" +mng_lock_flag_unpay_margin
      + "," + "mng_lock_flag_short_security=" +mng_lock_flag_short_security
      + "," + "mng_lock_flag_unsubstit_depo=" +mng_lock_flag_unsubstit_depo
      + "," + "branch_lock=" +branch_lock
      + "," + "order_permission=" +order_permission
      + "," + "tax_type=" +tax_type
      + "," + "tax_type_next=" +tax_type_next
      + "," + "margin_tax_type=" +margin_tax_type
      + "," + "margin_tax_type_next=" +margin_tax_type_next
      + "," + "qualified_inst_investor_div=" +qualified_inst_investor_div
      + "," + "margin_sys_acc_open_div=" +margin_sys_acc_open_div
      + "," + "margin_gen_acc_open_div=" +margin_gen_acc_open_div
      + "," + "equity_sp_acc_open_date=" +equity_sp_acc_open_date
      + "," + "margin_sp_acc_open_date=" +margin_sp_acc_open_date
      + "," + "transfer_count=" +transfer_count
      + "," + "foreign_sec_acc_open_div=" +foreign_sec_acc_open_div
      + "," + "ifo_acc_open_div_tokyo=" +ifo_acc_open_div_tokyo
      + "," + "ifo_acc_open_div_osaka=" +ifo_acc_open_div_osaka
      + "," + "ifo_acc_open_div_nagoya=" +ifo_acc_open_div_nagoya
      + "," + "ruito_acc_open_div=" +ruito_acc_open_div
      + "," + "mrf_acc_open_div=" +mrf_acc_open_div
      + "," + "fx_acc_open_div=" +fx_acc_open_div
      + "," + "feq_con_acc_open_div=" +feq_con_acc_open_div
      + "," + "top_page_id=" +top_page_id
      + "," + "quoto_type=" +quoto_type
      + "," + "trading_report_div=" +trading_report_div
      + "," + "position_report_div=" +position_report_div
      + "," + "position_report_cycle_div=" +position_report_cycle_div
      + "," + "certificate_deposit_flag=" +certificate_deposit_flag
      + "," + "account_statement_flag=" +account_statement_flag
      + "," + "email_last_updater=" +email_last_updater
      + "," + "email_last_updated_timestamp=" +email_last_updated_timestamp
      + "," + "trading_password_updater=" +trading_password_updater
      + "," + "tr_pwd_last_update_timestamp=" +tr_pwd_last_update_timestamp
      + "," + "mb_off_last_updater=" +mb_off_last_updater
      + "," + "mb_off_last_updated_timestamp=" +mb_off_last_updated_timestamp
      + "," + "enable_order_last_updater=" +enable_order_last_updater
      + "," + "enable_order_updated_timestamp=" +enable_order_updated_timestamp
      + "," + "fx_acc_open_div_last_updater=" +fx_acc_open_div_last_updater
      + "," + "fx_acc_open_updated_timestamp=" +fx_acc_open_updated_timestamp
      + "," + "feq_con_acc_open_div_updater=" +feq_con_acc_open_div_updater
      + "," + "feq_con_acc_open_timestamp=" +feq_con_acc_open_timestamp
      + "," + "mrf_fund_code=" +mrf_fund_code
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "sp_mng_acc_open_div=" +sp_mng_acc_open_div
      + "," + "lock_registration_reason=" +lock_registration_reason
      + "," + "inf_mail_flg_last_updater=" +inf_mail_flg_last_updater
      + "," + "inf_mail_flg_updated_timestamp=" +inf_mail_flg_updated_timestamp
      + "," + "eq_exe_ml_flg_last_updater=" +eq_exe_ml_flg_last_updater
      + "," + "eq_exe_ml_flg_timestamp=" +eq_exe_ml_flg_timestamp
      + "," + "eq_unexe_ml_flg_last_updater=" +eq_unexe_ml_flg_last_updater
      + "," + "eq_unexe_ml_flg_timestamp=" +eq_unexe_ml_flg_timestamp
      + "," + "ifo_exe_ml_flg_last_updater=" +ifo_exe_ml_flg_last_updater
      + "," + "ifo_exe_ml_flg_timestamp=" +ifo_exe_ml_flg_timestamp
      + "," + "ifo_unexe_ml_flg_last_updater=" +ifo_unexe_ml_flg_last_updater
      + "," + "ifo_unexe_ml_flg_timestamp=" +ifo_unexe_ml_flg_timestamp
      + "," + "stock_option_acc_open_div=" +stock_option_acc_open_div
      + "," + "secured_loan_sec_acc_open_div=" +secured_loan_sec_acc_open_div
      + "," + "margin_acc_open_date=" +margin_acc_open_date
      + "," + "ifo_acc_open_date=" +ifo_acc_open_date
      + "," + "only_mobile_open_div=" +only_mobile_open_div
      + "," + "only_mbl_opn_div_last_updater=" +only_mbl_opn_div_last_updater
      + "," + "only_mbl_opn_div_timestamp=" +only_mbl_opn_div_timestamp
      + "," + "email_address_alt2=" +email_address_alt2
      + "," + "order_exe_flag=" +order_exe_flag
      + "," + "order_unexe_flag=" +order_unexe_flag
      + "," + "important_connection_mail_flag=" +important_connection_mail_flag
      + "," + "information_mail2_flag=" +information_mail2_flag
      + "," + "proam_div=" +proam_div
      + "," + "org_deposit_div=" +org_deposit_div
      + "," + "eq_hold_rep_div=" +eq_hold_rep_div
      + "," + "chkup_rep_div=" +chkup_rep_div
      + "," + "pts_acc_open_div=" +pts_acc_open_div
      + "," + "pts_acc_open_div_last_updater=" +pts_acc_open_div_last_updater
      + "," + "pts_acc_open_div_timestamp=" +pts_acc_open_div_timestamp
      + "," + "broadcast_law=" +broadcast_law
      + "," + "aviation_law=" +aviation_law
      + "," + "ntt_law=" +ntt_law
      + "," + "dividend_trans_designate=" +dividend_trans_designate
      + "," + "standing_proxy=" +standing_proxy
      + "," + "statutory_agent=" +statutory_agent
      + "," + "affiliate_account_code=" +affiliate_account_code
      + "," + "agency_notify_cmp_div=" +agency_notify_cmp_div
      + "," + "cfd_acc_open_div=" +cfd_acc_open_div
      + "," + "cfd_acc_open_div_last_updater=" +cfd_acc_open_div_last_updater
      + "," + "cfd_acc_open_updated_timestamp=" +cfd_acc_open_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のMainAccountParamsオブジェクトを作成します。 
   */
  public MainAccountParams() {
    account_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のMainAccountRowオブジェクトの値を利用してMainAccountParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するMainAccountRowオブジェクト 
   */
  public MainAccountParams( MainAccountRow p_row )
  {
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    institution_id = p_row.getInstitutionId();
    institution_id_is_set = p_row.getInstitutionIdIsSet();
    institution_id_is_modified = p_row.getInstitutionIdIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    sonar_trader_code = p_row.getSonarTraderCode();
    sonar_trader_code_is_set = p_row.getSonarTraderCodeIsSet();
    sonar_trader_code_is_modified = p_row.getSonarTraderCodeIsModified();
    account_type = p_row.getAccountType();
    account_type_is_set = p_row.getAccountTypeIsSet();
    account_type_is_modified = p_row.getAccountTypeIsModified();
    family_name = p_row.getFamilyName();
    family_name_is_set = p_row.getFamilyNameIsSet();
    family_name_is_modified = p_row.getFamilyNameIsModified();
    middle_name = p_row.getMiddleName();
    middle_name_is_set = p_row.getMiddleNameIsSet();
    middle_name_is_modified = p_row.getMiddleNameIsModified();
    given_name = p_row.getGivenName();
    given_name_is_set = p_row.getGivenNameIsSet();
    given_name_is_modified = p_row.getGivenNameIsModified();
    family_name_alt1 = p_row.getFamilyNameAlt1();
    family_name_alt1_is_set = p_row.getFamilyNameAlt1IsSet();
    family_name_alt1_is_modified = p_row.getFamilyNameAlt1IsModified();
    middle_name_alt1 = p_row.getMiddleNameAlt1();
    middle_name_alt1_is_set = p_row.getMiddleNameAlt1IsSet();
    middle_name_alt1_is_modified = p_row.getMiddleNameAlt1IsModified();
    given_name_alt1 = p_row.getGivenNameAlt1();
    given_name_alt1_is_set = p_row.getGivenNameAlt1IsSet();
    given_name_alt1_is_modified = p_row.getGivenNameAlt1IsModified();
    family_name_alt2 = p_row.getFamilyNameAlt2();
    family_name_alt2_is_set = p_row.getFamilyNameAlt2IsSet();
    family_name_alt2_is_modified = p_row.getFamilyNameAlt2IsModified();
    middle_name_alt2 = p_row.getMiddleNameAlt2();
    middle_name_alt2_is_set = p_row.getMiddleNameAlt2IsSet();
    middle_name_alt2_is_modified = p_row.getMiddleNameAlt2IsModified();
    given_name_alt2 = p_row.getGivenNameAlt2();
    given_name_alt2_is_set = p_row.getGivenNameAlt2IsSet();
    given_name_alt2_is_modified = p_row.getGivenNameAlt2IsModified();
    zip_code = p_row.getZipCode();
    zip_code_is_set = p_row.getZipCodeIsSet();
    zip_code_is_modified = p_row.getZipCodeIsModified();
    sub_zip_code = p_row.getSubZipCode();
    sub_zip_code_is_set = p_row.getSubZipCodeIsSet();
    sub_zip_code_is_modified = p_row.getSubZipCodeIsModified();
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
    telephone = p_row.getTelephone();
    telephone_is_set = p_row.getTelephoneIsSet();
    telephone_is_modified = p_row.getTelephoneIsModified();
    mobile = p_row.getMobile();
    mobile_is_set = p_row.getMobileIsSet();
    mobile_is_modified = p_row.getMobileIsModified();
    fax = p_row.getFax();
    fax_is_set = p_row.getFaxIsSet();
    fax_is_modified = p_row.getFaxIsModified();
    office = p_row.getOffice();
    office_is_set = p_row.getOfficeIsSet();
    office_is_modified = p_row.getOfficeIsModified();
    office_zip_code = p_row.getOfficeZipCode();
    office_zip_code_is_set = p_row.getOfficeZipCodeIsSet();
    office_zip_code_is_modified = p_row.getOfficeZipCodeIsModified();
    office_address = p_row.getOfficeAddress();
    office_address_is_set = p_row.getOfficeAddressIsSet();
    office_address_is_modified = p_row.getOfficeAddressIsModified();
    office_telephone = p_row.getOfficeTelephone();
    office_telephone_is_set = p_row.getOfficeTelephoneIsSet();
    office_telephone_is_modified = p_row.getOfficeTelephoneIsModified();
    post = p_row.getPost();
    post_is_set = p_row.getPostIsSet();
    post_is_modified = p_row.getPostIsModified();
    equity_order_exe_mail_flag = p_row.getEquityOrderExeMailFlag();
    equity_order_exe_mail_flag_is_set = p_row.getEquityOrderExeMailFlagIsSet();
    equity_order_exe_mail_flag_is_modified = p_row.getEquityOrderExeMailFlagIsModified();
    equity_order_unexec_mail_flag = p_row.getEquityOrderUnexecMailFlag();
    equity_order_unexec_mail_flag_is_set = p_row.getEquityOrderUnexecMailFlagIsSet();
    equity_order_unexec_mail_flag_is_modified = p_row.getEquityOrderUnexecMailFlagIsModified();
    ifo_order_exec_mail_flag = p_row.getIfoOrderExecMailFlag();
    ifo_order_exec_mail_flag_is_set = p_row.getIfoOrderExecMailFlagIsSet();
    ifo_order_exec_mail_flag_is_modified = p_row.getIfoOrderExecMailFlagIsModified();
    ifo_order_unexec_mail_flag = p_row.getIfoOrderUnexecMailFlag();
    ifo_order_unexec_mail_flag_is_set = p_row.getIfoOrderUnexecMailFlagIsSet();
    ifo_order_unexec_mail_flag_is_modified = p_row.getIfoOrderUnexecMailFlagIsModified();
    information_mail_flag = p_row.getInformationMailFlag();
    information_mail_flag_is_set = p_row.getInformationMailFlagIsSet();
    information_mail_flag_is_modified = p_row.getInformationMailFlagIsModified();
    resident = p_row.getResident();
    resident_is_set = p_row.getResidentIsSet();
    resident_is_modified = p_row.getResidentIsModified();
    new_account_div = p_row.getNewAccountDiv();
    new_account_div_is_set = p_row.getNewAccountDivIsSet();
    new_account_div_is_modified = p_row.getNewAccountDivIsModified();
    via_trust_bank_div = p_row.getViaTrustBankDiv();
    via_trust_bank_div_is_set = p_row.getViaTrustBankDivIsSet();
    via_trust_bank_div_is_modified = p_row.getViaTrustBankDivIsModified();
    class_div = p_row.getClassDiv();
    class_div_is_set = p_row.getClassDivIsSet();
    class_div_is_modified = p_row.getClassDivIsModified();
    email_address = p_row.getEmailAddress();
    email_address_is_set = p_row.getEmailAddressIsSet();
    email_address_is_modified = p_row.getEmailAddressIsModified();
    email_address_alt1 = p_row.getEmailAddressAlt1();
    email_address_alt1_is_set = p_row.getEmailAddressAlt1IsSet();
    email_address_alt1_is_modified = p_row.getEmailAddressAlt1IsModified();
    trading_password = p_row.getTradingPassword();
    trading_password_is_set = p_row.getTradingPasswordIsSet();
    trading_password_is_modified = p_row.getTradingPasswordIsModified();
    account_open_date = p_row.getAccountOpenDate();
    account_open_date_is_set = p_row.getAccountOpenDateIsSet();
    account_open_date_is_modified = p_row.getAccountOpenDateIsModified();
    account_close_date = p_row.getAccountCloseDate();
    account_close_date_is_set = p_row.getAccountCloseDateIsSet();
    account_close_date_is_modified = p_row.getAccountCloseDateIsModified();
    acc_open_send_mail_status = p_row.getAccOpenSendMailStatus();
    acc_open_send_mail_status_is_set = p_row.getAccOpenSendMailStatusIsSet();
    acc_open_send_mail_status_is_modified = p_row.getAccOpenSendMailStatusIsModified();
    person_identify = p_row.getPersonIdentify();
    person_identify_is_set = p_row.getPersonIdentifyIsSet();
    person_identify_is_modified = p_row.getPersonIdentifyIsModified();
    era_born = p_row.getEraBorn();
    era_born_is_set = p_row.getEraBornIsSet();
    era_born_is_modified = p_row.getEraBornIsModified();
    born_date = p_row.getBornDate();
    born_date_is_set = p_row.getBornDateIsSet();
    born_date_is_modified = p_row.getBornDateIsModified();
    sex = p_row.getSex();
    sex_is_set = p_row.getSexIsSet();
    sex_is_modified = p_row.getSexIsModified();
    yellow_customer = p_row.getYellowCustomer();
    yellow_customer_is_set = p_row.getYellowCustomerIsSet();
    yellow_customer_is_modified = p_row.getYellowCustomerIsModified();
    ht_settlement_way = p_row.getHtSettlementWay();
    ht_settlement_way_is_set = p_row.getHtSettlementWayIsSet();
    ht_settlement_way_is_modified = p_row.getHtSettlementWayIsModified();
    bank_account_regi = p_row.getBankAccountRegi();
    bank_account_regi_is_set = p_row.getBankAccountRegiIsSet();
    bank_account_regi_is_modified = p_row.getBankAccountRegiIsModified();
    account_status = p_row.getAccountStatus();
    account_status_is_set = p_row.getAccountStatusIsSet();
    account_status_is_modified = p_row.getAccountStatusIsModified();
    examin_lock_flag = p_row.getExaminLockFlag();
    examin_lock_flag_is_set = p_row.getExaminLockFlagIsSet();
    examin_lock_flag_is_modified = p_row.getExaminLockFlagIsModified();
    mng_lock_flag = p_row.getMngLockFlag();
    mng_lock_flag_is_set = p_row.getMngLockFlagIsSet();
    mng_lock_flag_is_modified = p_row.getMngLockFlagIsModified();
    mng_lock_off_start_date = p_row.getMngLockOffStartDate();
    mng_lock_off_start_date_is_set = p_row.getMngLockOffStartDateIsSet();
    mng_lock_off_start_date_is_modified = p_row.getMngLockOffStartDateIsModified();
    mng_lock_off_end_date = p_row.getMngLockOffEndDate();
    mng_lock_off_end_date_is_set = p_row.getMngLockOffEndDateIsSet();
    mng_lock_off_end_date_is_modified = p_row.getMngLockOffEndDateIsModified();
    mng_lock_flag_advance = p_row.getMngLockFlagAdvance();
    mng_lock_flag_advance_is_set = p_row.getMngLockFlagAdvanceIsSet();
    mng_lock_flag_advance_is_modified = p_row.getMngLockFlagAdvanceIsModified();
    mng_lock_flag_unpay_margin = p_row.getMngLockFlagUnpayMargin();
    mng_lock_flag_unpay_margin_is_set = p_row.getMngLockFlagUnpayMarginIsSet();
    mng_lock_flag_unpay_margin_is_modified = p_row.getMngLockFlagUnpayMarginIsModified();
    mng_lock_flag_short_security = p_row.getMngLockFlagShortSecurity();
    mng_lock_flag_short_security_is_set = p_row.getMngLockFlagShortSecurityIsSet();
    mng_lock_flag_short_security_is_modified = p_row.getMngLockFlagShortSecurityIsModified();
    mng_lock_flag_unsubstit_depo = p_row.getMngLockFlagUnsubstitDepo();
    mng_lock_flag_unsubstit_depo_is_set = p_row.getMngLockFlagUnsubstitDepoIsSet();
    mng_lock_flag_unsubstit_depo_is_modified = p_row.getMngLockFlagUnsubstitDepoIsModified();
    branch_lock = p_row.getBranchLock();
    branch_lock_is_set = p_row.getBranchLockIsSet();
    branch_lock_is_modified = p_row.getBranchLockIsModified();
    order_permission = p_row.getOrderPermission();
    order_permission_is_set = p_row.getOrderPermissionIsSet();
    order_permission_is_modified = p_row.getOrderPermissionIsModified();
    tax_type = p_row.getTaxType();
    tax_type_is_set = p_row.getTaxTypeIsSet();
    tax_type_is_modified = p_row.getTaxTypeIsModified();
    tax_type_next = p_row.getTaxTypeNext();
    tax_type_next_is_set = p_row.getTaxTypeNextIsSet();
    tax_type_next_is_modified = p_row.getTaxTypeNextIsModified();
    margin_tax_type = p_row.getMarginTaxType();
    margin_tax_type_is_set = p_row.getMarginTaxTypeIsSet();
    margin_tax_type_is_modified = p_row.getMarginTaxTypeIsModified();
    margin_tax_type_next = p_row.getMarginTaxTypeNext();
    margin_tax_type_next_is_set = p_row.getMarginTaxTypeNextIsSet();
    margin_tax_type_next_is_modified = p_row.getMarginTaxTypeNextIsModified();
    qualified_inst_investor_div = p_row.getQualifiedInstInvestorDiv();
    qualified_inst_investor_div_is_set = p_row.getQualifiedInstInvestorDivIsSet();
    qualified_inst_investor_div_is_modified = p_row.getQualifiedInstInvestorDivIsModified();
    margin_sys_acc_open_div = p_row.getMarginSysAccOpenDiv();
    margin_sys_acc_open_div_is_set = p_row.getMarginSysAccOpenDivIsSet();
    margin_sys_acc_open_div_is_modified = p_row.getMarginSysAccOpenDivIsModified();
    margin_gen_acc_open_div = p_row.getMarginGenAccOpenDiv();
    margin_gen_acc_open_div_is_set = p_row.getMarginGenAccOpenDivIsSet();
    margin_gen_acc_open_div_is_modified = p_row.getMarginGenAccOpenDivIsModified();
    equity_sp_acc_open_date = p_row.getEquitySpAccOpenDate();
    equity_sp_acc_open_date_is_set = p_row.getEquitySpAccOpenDateIsSet();
    equity_sp_acc_open_date_is_modified = p_row.getEquitySpAccOpenDateIsModified();
    margin_sp_acc_open_date = p_row.getMarginSpAccOpenDate();
    margin_sp_acc_open_date_is_set = p_row.getMarginSpAccOpenDateIsSet();
    margin_sp_acc_open_date_is_modified = p_row.getMarginSpAccOpenDateIsModified();
    if ( !p_row.getTransferCountIsNull() )
      transfer_count = new Integer( p_row.getTransferCount() );
    transfer_count_is_set = p_row.getTransferCountIsSet();
    transfer_count_is_modified = p_row.getTransferCountIsModified();
    foreign_sec_acc_open_div = p_row.getForeignSecAccOpenDiv();
    foreign_sec_acc_open_div_is_set = p_row.getForeignSecAccOpenDivIsSet();
    foreign_sec_acc_open_div_is_modified = p_row.getForeignSecAccOpenDivIsModified();
    ifo_acc_open_div_tokyo = p_row.getIfoAccOpenDivTokyo();
    ifo_acc_open_div_tokyo_is_set = p_row.getIfoAccOpenDivTokyoIsSet();
    ifo_acc_open_div_tokyo_is_modified = p_row.getIfoAccOpenDivTokyoIsModified();
    ifo_acc_open_div_osaka = p_row.getIfoAccOpenDivOsaka();
    ifo_acc_open_div_osaka_is_set = p_row.getIfoAccOpenDivOsakaIsSet();
    ifo_acc_open_div_osaka_is_modified = p_row.getIfoAccOpenDivOsakaIsModified();
    ifo_acc_open_div_nagoya = p_row.getIfoAccOpenDivNagoya();
    ifo_acc_open_div_nagoya_is_set = p_row.getIfoAccOpenDivNagoyaIsSet();
    ifo_acc_open_div_nagoya_is_modified = p_row.getIfoAccOpenDivNagoyaIsModified();
    ruito_acc_open_div = p_row.getRuitoAccOpenDiv();
    ruito_acc_open_div_is_set = p_row.getRuitoAccOpenDivIsSet();
    ruito_acc_open_div_is_modified = p_row.getRuitoAccOpenDivIsModified();
    mrf_acc_open_div = p_row.getMrfAccOpenDiv();
    mrf_acc_open_div_is_set = p_row.getMrfAccOpenDivIsSet();
    mrf_acc_open_div_is_modified = p_row.getMrfAccOpenDivIsModified();
    fx_acc_open_div = p_row.getFxAccOpenDiv();
    fx_acc_open_div_is_set = p_row.getFxAccOpenDivIsSet();
    fx_acc_open_div_is_modified = p_row.getFxAccOpenDivIsModified();
    feq_con_acc_open_div = p_row.getFeqConAccOpenDiv();
    feq_con_acc_open_div_is_set = p_row.getFeqConAccOpenDivIsSet();
    feq_con_acc_open_div_is_modified = p_row.getFeqConAccOpenDivIsModified();
    top_page_id = p_row.getTopPageId();
    top_page_id_is_set = p_row.getTopPageIdIsSet();
    top_page_id_is_modified = p_row.getTopPageIdIsModified();
    quoto_type = p_row.getQuotoType();
    quoto_type_is_set = p_row.getQuotoTypeIsSet();
    quoto_type_is_modified = p_row.getQuotoTypeIsModified();
    trading_report_div = p_row.getTradingReportDiv();
    trading_report_div_is_set = p_row.getTradingReportDivIsSet();
    trading_report_div_is_modified = p_row.getTradingReportDivIsModified();
    position_report_div = p_row.getPositionReportDiv();
    position_report_div_is_set = p_row.getPositionReportDivIsSet();
    position_report_div_is_modified = p_row.getPositionReportDivIsModified();
    position_report_cycle_div = p_row.getPositionReportCycleDiv();
    position_report_cycle_div_is_set = p_row.getPositionReportCycleDivIsSet();
    position_report_cycle_div_is_modified = p_row.getPositionReportCycleDivIsModified();
    certificate_deposit_flag = p_row.getCertificateDepositFlag();
    certificate_deposit_flag_is_set = p_row.getCertificateDepositFlagIsSet();
    certificate_deposit_flag_is_modified = p_row.getCertificateDepositFlagIsModified();
    account_statement_flag = p_row.getAccountStatementFlag();
    account_statement_flag_is_set = p_row.getAccountStatementFlagIsSet();
    account_statement_flag_is_modified = p_row.getAccountStatementFlagIsModified();
    email_last_updater = p_row.getEmailLastUpdater();
    email_last_updater_is_set = p_row.getEmailLastUpdaterIsSet();
    email_last_updater_is_modified = p_row.getEmailLastUpdaterIsModified();
    email_last_updated_timestamp = p_row.getEmailLastUpdatedTimestamp();
    email_last_updated_timestamp_is_set = p_row.getEmailLastUpdatedTimestampIsSet();
    email_last_updated_timestamp_is_modified = p_row.getEmailLastUpdatedTimestampIsModified();
    trading_password_updater = p_row.getTradingPasswordUpdater();
    trading_password_updater_is_set = p_row.getTradingPasswordUpdaterIsSet();
    trading_password_updater_is_modified = p_row.getTradingPasswordUpdaterIsModified();
    tr_pwd_last_update_timestamp = p_row.getTrPwdLastUpdateTimestamp();
    tr_pwd_last_update_timestamp_is_set = p_row.getTrPwdLastUpdateTimestampIsSet();
    tr_pwd_last_update_timestamp_is_modified = p_row.getTrPwdLastUpdateTimestampIsModified();
    mb_off_last_updater = p_row.getMbOffLastUpdater();
    mb_off_last_updater_is_set = p_row.getMbOffLastUpdaterIsSet();
    mb_off_last_updater_is_modified = p_row.getMbOffLastUpdaterIsModified();
    mb_off_last_updated_timestamp = p_row.getMbOffLastUpdatedTimestamp();
    mb_off_last_updated_timestamp_is_set = p_row.getMbOffLastUpdatedTimestampIsSet();
    mb_off_last_updated_timestamp_is_modified = p_row.getMbOffLastUpdatedTimestampIsModified();
    enable_order_last_updater = p_row.getEnableOrderLastUpdater();
    enable_order_last_updater_is_set = p_row.getEnableOrderLastUpdaterIsSet();
    enable_order_last_updater_is_modified = p_row.getEnableOrderLastUpdaterIsModified();
    enable_order_updated_timestamp = p_row.getEnableOrderUpdatedTimestamp();
    enable_order_updated_timestamp_is_set = p_row.getEnableOrderUpdatedTimestampIsSet();
    enable_order_updated_timestamp_is_modified = p_row.getEnableOrderUpdatedTimestampIsModified();
    fx_acc_open_div_last_updater = p_row.getFxAccOpenDivLastUpdater();
    fx_acc_open_div_last_updater_is_set = p_row.getFxAccOpenDivLastUpdaterIsSet();
    fx_acc_open_div_last_updater_is_modified = p_row.getFxAccOpenDivLastUpdaterIsModified();
    fx_acc_open_updated_timestamp = p_row.getFxAccOpenUpdatedTimestamp();
    fx_acc_open_updated_timestamp_is_set = p_row.getFxAccOpenUpdatedTimestampIsSet();
    fx_acc_open_updated_timestamp_is_modified = p_row.getFxAccOpenUpdatedTimestampIsModified();
    feq_con_acc_open_div_updater = p_row.getFeqConAccOpenDivUpdater();
    feq_con_acc_open_div_updater_is_set = p_row.getFeqConAccOpenDivUpdaterIsSet();
    feq_con_acc_open_div_updater_is_modified = p_row.getFeqConAccOpenDivUpdaterIsModified();
    feq_con_acc_open_timestamp = p_row.getFeqConAccOpenTimestamp();
    feq_con_acc_open_timestamp_is_set = p_row.getFeqConAccOpenTimestampIsSet();
    feq_con_acc_open_timestamp_is_modified = p_row.getFeqConAccOpenTimestampIsModified();
    mrf_fund_code = p_row.getMrfFundCode();
    mrf_fund_code_is_set = p_row.getMrfFundCodeIsSet();
    mrf_fund_code_is_modified = p_row.getMrfFundCodeIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    sp_mng_acc_open_div = p_row.getSpMngAccOpenDiv();
    sp_mng_acc_open_div_is_set = p_row.getSpMngAccOpenDivIsSet();
    sp_mng_acc_open_div_is_modified = p_row.getSpMngAccOpenDivIsModified();
    lock_registration_reason = p_row.getLockRegistrationReason();
    lock_registration_reason_is_set = p_row.getLockRegistrationReasonIsSet();
    lock_registration_reason_is_modified = p_row.getLockRegistrationReasonIsModified();
    inf_mail_flg_last_updater = p_row.getInfMailFlgLastUpdater();
    inf_mail_flg_last_updater_is_set = p_row.getInfMailFlgLastUpdaterIsSet();
    inf_mail_flg_last_updater_is_modified = p_row.getInfMailFlgLastUpdaterIsModified();
    inf_mail_flg_updated_timestamp = p_row.getInfMailFlgUpdatedTimestamp();
    inf_mail_flg_updated_timestamp_is_set = p_row.getInfMailFlgUpdatedTimestampIsSet();
    inf_mail_flg_updated_timestamp_is_modified = p_row.getInfMailFlgUpdatedTimestampIsModified();
    eq_exe_ml_flg_last_updater = p_row.getEqExeMlFlgLastUpdater();
    eq_exe_ml_flg_last_updater_is_set = p_row.getEqExeMlFlgLastUpdaterIsSet();
    eq_exe_ml_flg_last_updater_is_modified = p_row.getEqExeMlFlgLastUpdaterIsModified();
    eq_exe_ml_flg_timestamp = p_row.getEqExeMlFlgTimestamp();
    eq_exe_ml_flg_timestamp_is_set = p_row.getEqExeMlFlgTimestampIsSet();
    eq_exe_ml_flg_timestamp_is_modified = p_row.getEqExeMlFlgTimestampIsModified();
    eq_unexe_ml_flg_last_updater = p_row.getEqUnexeMlFlgLastUpdater();
    eq_unexe_ml_flg_last_updater_is_set = p_row.getEqUnexeMlFlgLastUpdaterIsSet();
    eq_unexe_ml_flg_last_updater_is_modified = p_row.getEqUnexeMlFlgLastUpdaterIsModified();
    eq_unexe_ml_flg_timestamp = p_row.getEqUnexeMlFlgTimestamp();
    eq_unexe_ml_flg_timestamp_is_set = p_row.getEqUnexeMlFlgTimestampIsSet();
    eq_unexe_ml_flg_timestamp_is_modified = p_row.getEqUnexeMlFlgTimestampIsModified();
    ifo_exe_ml_flg_last_updater = p_row.getIfoExeMlFlgLastUpdater();
    ifo_exe_ml_flg_last_updater_is_set = p_row.getIfoExeMlFlgLastUpdaterIsSet();
    ifo_exe_ml_flg_last_updater_is_modified = p_row.getIfoExeMlFlgLastUpdaterIsModified();
    ifo_exe_ml_flg_timestamp = p_row.getIfoExeMlFlgTimestamp();
    ifo_exe_ml_flg_timestamp_is_set = p_row.getIfoExeMlFlgTimestampIsSet();
    ifo_exe_ml_flg_timestamp_is_modified = p_row.getIfoExeMlFlgTimestampIsModified();
    ifo_unexe_ml_flg_last_updater = p_row.getIfoUnexeMlFlgLastUpdater();
    ifo_unexe_ml_flg_last_updater_is_set = p_row.getIfoUnexeMlFlgLastUpdaterIsSet();
    ifo_unexe_ml_flg_last_updater_is_modified = p_row.getIfoUnexeMlFlgLastUpdaterIsModified();
    ifo_unexe_ml_flg_timestamp = p_row.getIfoUnexeMlFlgTimestamp();
    ifo_unexe_ml_flg_timestamp_is_set = p_row.getIfoUnexeMlFlgTimestampIsSet();
    ifo_unexe_ml_flg_timestamp_is_modified = p_row.getIfoUnexeMlFlgTimestampIsModified();
    stock_option_acc_open_div = p_row.getStockOptionAccOpenDiv();
    stock_option_acc_open_div_is_set = p_row.getStockOptionAccOpenDivIsSet();
    stock_option_acc_open_div_is_modified = p_row.getStockOptionAccOpenDivIsModified();
    secured_loan_sec_acc_open_div = p_row.getSecuredLoanSecAccOpenDiv();
    secured_loan_sec_acc_open_div_is_set = p_row.getSecuredLoanSecAccOpenDivIsSet();
    secured_loan_sec_acc_open_div_is_modified = p_row.getSecuredLoanSecAccOpenDivIsModified();
    margin_acc_open_date = p_row.getMarginAccOpenDate();
    margin_acc_open_date_is_set = p_row.getMarginAccOpenDateIsSet();
    margin_acc_open_date_is_modified = p_row.getMarginAccOpenDateIsModified();
    ifo_acc_open_date = p_row.getIfoAccOpenDate();
    ifo_acc_open_date_is_set = p_row.getIfoAccOpenDateIsSet();
    ifo_acc_open_date_is_modified = p_row.getIfoAccOpenDateIsModified();
    only_mobile_open_div = p_row.getOnlyMobileOpenDiv();
    only_mobile_open_div_is_set = p_row.getOnlyMobileOpenDivIsSet();
    only_mobile_open_div_is_modified = p_row.getOnlyMobileOpenDivIsModified();
    only_mbl_opn_div_last_updater = p_row.getOnlyMblOpnDivLastUpdater();
    only_mbl_opn_div_last_updater_is_set = p_row.getOnlyMblOpnDivLastUpdaterIsSet();
    only_mbl_opn_div_last_updater_is_modified = p_row.getOnlyMblOpnDivLastUpdaterIsModified();
    only_mbl_opn_div_timestamp = p_row.getOnlyMblOpnDivTimestamp();
    only_mbl_opn_div_timestamp_is_set = p_row.getOnlyMblOpnDivTimestampIsSet();
    only_mbl_opn_div_timestamp_is_modified = p_row.getOnlyMblOpnDivTimestampIsModified();
    email_address_alt2 = p_row.getEmailAddressAlt2();
    email_address_alt2_is_set = p_row.getEmailAddressAlt2IsSet();
    email_address_alt2_is_modified = p_row.getEmailAddressAlt2IsModified();
    if ( !p_row.getOrderExeFlagIsNull() )
      order_exe_flag = new Integer( p_row.getOrderExeFlag() );
    order_exe_flag_is_set = p_row.getOrderExeFlagIsSet();
    order_exe_flag_is_modified = p_row.getOrderExeFlagIsModified();
    if ( !p_row.getOrderUnexeFlagIsNull() )
      order_unexe_flag = new Integer( p_row.getOrderUnexeFlag() );
    order_unexe_flag_is_set = p_row.getOrderUnexeFlagIsSet();
    order_unexe_flag_is_modified = p_row.getOrderUnexeFlagIsModified();
    if ( !p_row.getImportantConnectionMailFlagIsNull() )
      important_connection_mail_flag = new Integer( p_row.getImportantConnectionMailFlag() );
    important_connection_mail_flag_is_set = p_row.getImportantConnectionMailFlagIsSet();
    important_connection_mail_flag_is_modified = p_row.getImportantConnectionMailFlagIsModified();
    if ( !p_row.getInformationMail2FlagIsNull() )
      information_mail2_flag = new Integer( p_row.getInformationMail2Flag() );
    information_mail2_flag_is_set = p_row.getInformationMail2FlagIsSet();
    information_mail2_flag_is_modified = p_row.getInformationMail2FlagIsModified();
    proam_div = p_row.getProamDiv();
    proam_div_is_set = p_row.getProamDivIsSet();
    proam_div_is_modified = p_row.getProamDivIsModified();
    org_deposit_div = p_row.getOrgDepositDiv();
    org_deposit_div_is_set = p_row.getOrgDepositDivIsSet();
    org_deposit_div_is_modified = p_row.getOrgDepositDivIsModified();
    eq_hold_rep_div = p_row.getEqHoldRepDiv();
    eq_hold_rep_div_is_set = p_row.getEqHoldRepDivIsSet();
    eq_hold_rep_div_is_modified = p_row.getEqHoldRepDivIsModified();
    chkup_rep_div = p_row.getChkupRepDiv();
    chkup_rep_div_is_set = p_row.getChkupRepDivIsSet();
    chkup_rep_div_is_modified = p_row.getChkupRepDivIsModified();
    pts_acc_open_div = p_row.getPtsAccOpenDiv();
    pts_acc_open_div_is_set = p_row.getPtsAccOpenDivIsSet();
    pts_acc_open_div_is_modified = p_row.getPtsAccOpenDivIsModified();
    pts_acc_open_div_last_updater = p_row.getPtsAccOpenDivLastUpdater();
    pts_acc_open_div_last_updater_is_set = p_row.getPtsAccOpenDivLastUpdaterIsSet();
    pts_acc_open_div_last_updater_is_modified = p_row.getPtsAccOpenDivLastUpdaterIsModified();
    pts_acc_open_div_timestamp = p_row.getPtsAccOpenDivTimestamp();
    pts_acc_open_div_timestamp_is_set = p_row.getPtsAccOpenDivTimestampIsSet();
    pts_acc_open_div_timestamp_is_modified = p_row.getPtsAccOpenDivTimestampIsModified();
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
    cfd_acc_open_div = p_row.getCfdAccOpenDiv();
    cfd_acc_open_div_is_set = p_row.getCfdAccOpenDivIsSet();
    cfd_acc_open_div_is_modified = p_row.getCfdAccOpenDivIsModified();
    cfd_acc_open_div_last_updater = p_row.getCfdAccOpenDivLastUpdater();
    cfd_acc_open_div_last_updater_is_set = p_row.getCfdAccOpenDivLastUpdaterIsSet();
    cfd_acc_open_div_last_updater_is_modified = p_row.getCfdAccOpenDivLastUpdaterIsModified();
    cfd_acc_open_updated_timestamp = p_row.getCfdAccOpenUpdatedTimestamp();
    cfd_acc_open_updated_timestamp_is_set = p_row.getCfdAccOpenUpdatedTimestampIsSet();
    cfd_acc_open_updated_timestamp_is_modified = p_row.getCfdAccOpenUpdatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      institution_code_is_set = true;
      institution_code_is_modified = true;
      institution_id_is_set = true;
      institution_id_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      branch_id_is_set = true;
      branch_id_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      sonar_trader_code_is_set = true;
      sonar_trader_code_is_modified = true;
      account_type_is_set = true;
      account_type_is_modified = true;
      family_name_is_set = true;
      family_name_is_modified = true;
      middle_name_is_set = true;
      middle_name_is_modified = true;
      given_name_is_set = true;
      given_name_is_modified = true;
      family_name_alt1_is_set = true;
      family_name_alt1_is_modified = true;
      middle_name_alt1_is_set = true;
      middle_name_alt1_is_modified = true;
      given_name_alt1_is_set = true;
      given_name_alt1_is_modified = true;
      family_name_alt2_is_set = true;
      family_name_alt2_is_modified = true;
      middle_name_alt2_is_set = true;
      middle_name_alt2_is_modified = true;
      given_name_alt2_is_set = true;
      given_name_alt2_is_modified = true;
      zip_code_is_set = true;
      zip_code_is_modified = true;
      sub_zip_code_is_set = true;
      sub_zip_code_is_modified = true;
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
      telephone_is_set = true;
      telephone_is_modified = true;
      mobile_is_set = true;
      mobile_is_modified = true;
      fax_is_set = true;
      fax_is_modified = true;
      office_is_set = true;
      office_is_modified = true;
      office_zip_code_is_set = true;
      office_zip_code_is_modified = true;
      office_address_is_set = true;
      office_address_is_modified = true;
      office_telephone_is_set = true;
      office_telephone_is_modified = true;
      post_is_set = true;
      post_is_modified = true;
      equity_order_exe_mail_flag_is_set = true;
      equity_order_exe_mail_flag_is_modified = true;
      equity_order_unexec_mail_flag_is_set = true;
      equity_order_unexec_mail_flag_is_modified = true;
      ifo_order_exec_mail_flag_is_set = true;
      ifo_order_exec_mail_flag_is_modified = true;
      ifo_order_unexec_mail_flag_is_set = true;
      ifo_order_unexec_mail_flag_is_modified = true;
      information_mail_flag_is_set = true;
      information_mail_flag_is_modified = true;
      resident_is_set = true;
      resident_is_modified = true;
      new_account_div_is_set = true;
      new_account_div_is_modified = true;
      via_trust_bank_div_is_set = true;
      via_trust_bank_div_is_modified = true;
      class_div_is_set = true;
      class_div_is_modified = true;
      email_address_is_set = true;
      email_address_is_modified = true;
      email_address_alt1_is_set = true;
      email_address_alt1_is_modified = true;
      trading_password_is_set = true;
      trading_password_is_modified = true;
      account_open_date_is_set = true;
      account_open_date_is_modified = true;
      account_close_date_is_set = true;
      account_close_date_is_modified = true;
      acc_open_send_mail_status_is_set = true;
      acc_open_send_mail_status_is_modified = true;
      person_identify_is_set = true;
      person_identify_is_modified = true;
      era_born_is_set = true;
      era_born_is_modified = true;
      born_date_is_set = true;
      born_date_is_modified = true;
      sex_is_set = true;
      sex_is_modified = true;
      yellow_customer_is_set = true;
      yellow_customer_is_modified = true;
      ht_settlement_way_is_set = true;
      ht_settlement_way_is_modified = true;
      bank_account_regi_is_set = true;
      bank_account_regi_is_modified = true;
      account_status_is_set = true;
      account_status_is_modified = true;
      examin_lock_flag_is_set = true;
      examin_lock_flag_is_modified = true;
      mng_lock_flag_is_set = true;
      mng_lock_flag_is_modified = true;
      mng_lock_off_start_date_is_set = true;
      mng_lock_off_start_date_is_modified = true;
      mng_lock_off_end_date_is_set = true;
      mng_lock_off_end_date_is_modified = true;
      mng_lock_flag_advance_is_set = true;
      mng_lock_flag_advance_is_modified = true;
      mng_lock_flag_unpay_margin_is_set = true;
      mng_lock_flag_unpay_margin_is_modified = true;
      mng_lock_flag_short_security_is_set = true;
      mng_lock_flag_short_security_is_modified = true;
      mng_lock_flag_unsubstit_depo_is_set = true;
      mng_lock_flag_unsubstit_depo_is_modified = true;
      branch_lock_is_set = true;
      branch_lock_is_modified = true;
      order_permission_is_set = true;
      order_permission_is_modified = true;
      tax_type_is_set = true;
      tax_type_is_modified = true;
      tax_type_next_is_set = true;
      tax_type_next_is_modified = true;
      margin_tax_type_is_set = true;
      margin_tax_type_is_modified = true;
      margin_tax_type_next_is_set = true;
      margin_tax_type_next_is_modified = true;
      qualified_inst_investor_div_is_set = true;
      qualified_inst_investor_div_is_modified = true;
      margin_sys_acc_open_div_is_set = true;
      margin_sys_acc_open_div_is_modified = true;
      margin_gen_acc_open_div_is_set = true;
      margin_gen_acc_open_div_is_modified = true;
      equity_sp_acc_open_date_is_set = true;
      equity_sp_acc_open_date_is_modified = true;
      margin_sp_acc_open_date_is_set = true;
      margin_sp_acc_open_date_is_modified = true;
      transfer_count_is_set = true;
      transfer_count_is_modified = true;
      foreign_sec_acc_open_div_is_set = true;
      foreign_sec_acc_open_div_is_modified = true;
      ifo_acc_open_div_tokyo_is_set = true;
      ifo_acc_open_div_tokyo_is_modified = true;
      ifo_acc_open_div_osaka_is_set = true;
      ifo_acc_open_div_osaka_is_modified = true;
      ifo_acc_open_div_nagoya_is_set = true;
      ifo_acc_open_div_nagoya_is_modified = true;
      ruito_acc_open_div_is_set = true;
      ruito_acc_open_div_is_modified = true;
      mrf_acc_open_div_is_set = true;
      mrf_acc_open_div_is_modified = true;
      fx_acc_open_div_is_set = true;
      fx_acc_open_div_is_modified = true;
      feq_con_acc_open_div_is_set = true;
      feq_con_acc_open_div_is_modified = true;
      top_page_id_is_set = true;
      top_page_id_is_modified = true;
      quoto_type_is_set = true;
      quoto_type_is_modified = true;
      trading_report_div_is_set = true;
      trading_report_div_is_modified = true;
      position_report_div_is_set = true;
      position_report_div_is_modified = true;
      position_report_cycle_div_is_set = true;
      position_report_cycle_div_is_modified = true;
      certificate_deposit_flag_is_set = true;
      certificate_deposit_flag_is_modified = true;
      account_statement_flag_is_set = true;
      account_statement_flag_is_modified = true;
      email_last_updater_is_set = true;
      email_last_updater_is_modified = true;
      email_last_updated_timestamp_is_set = true;
      email_last_updated_timestamp_is_modified = true;
      trading_password_updater_is_set = true;
      trading_password_updater_is_modified = true;
      tr_pwd_last_update_timestamp_is_set = true;
      tr_pwd_last_update_timestamp_is_modified = true;
      mb_off_last_updater_is_set = true;
      mb_off_last_updater_is_modified = true;
      mb_off_last_updated_timestamp_is_set = true;
      mb_off_last_updated_timestamp_is_modified = true;
      enable_order_last_updater_is_set = true;
      enable_order_last_updater_is_modified = true;
      enable_order_updated_timestamp_is_set = true;
      enable_order_updated_timestamp_is_modified = true;
      fx_acc_open_div_last_updater_is_set = true;
      fx_acc_open_div_last_updater_is_modified = true;
      fx_acc_open_updated_timestamp_is_set = true;
      fx_acc_open_updated_timestamp_is_modified = true;
      feq_con_acc_open_div_updater_is_set = true;
      feq_con_acc_open_div_updater_is_modified = true;
      feq_con_acc_open_timestamp_is_set = true;
      feq_con_acc_open_timestamp_is_modified = true;
      mrf_fund_code_is_set = true;
      mrf_fund_code_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      sp_mng_acc_open_div_is_set = true;
      sp_mng_acc_open_div_is_modified = true;
      lock_registration_reason_is_set = true;
      lock_registration_reason_is_modified = true;
      inf_mail_flg_last_updater_is_set = true;
      inf_mail_flg_last_updater_is_modified = true;
      inf_mail_flg_updated_timestamp_is_set = true;
      inf_mail_flg_updated_timestamp_is_modified = true;
      eq_exe_ml_flg_last_updater_is_set = true;
      eq_exe_ml_flg_last_updater_is_modified = true;
      eq_exe_ml_flg_timestamp_is_set = true;
      eq_exe_ml_flg_timestamp_is_modified = true;
      eq_unexe_ml_flg_last_updater_is_set = true;
      eq_unexe_ml_flg_last_updater_is_modified = true;
      eq_unexe_ml_flg_timestamp_is_set = true;
      eq_unexe_ml_flg_timestamp_is_modified = true;
      ifo_exe_ml_flg_last_updater_is_set = true;
      ifo_exe_ml_flg_last_updater_is_modified = true;
      ifo_exe_ml_flg_timestamp_is_set = true;
      ifo_exe_ml_flg_timestamp_is_modified = true;
      ifo_unexe_ml_flg_last_updater_is_set = true;
      ifo_unexe_ml_flg_last_updater_is_modified = true;
      ifo_unexe_ml_flg_timestamp_is_set = true;
      ifo_unexe_ml_flg_timestamp_is_modified = true;
      stock_option_acc_open_div_is_set = true;
      stock_option_acc_open_div_is_modified = true;
      secured_loan_sec_acc_open_div_is_set = true;
      secured_loan_sec_acc_open_div_is_modified = true;
      margin_acc_open_date_is_set = true;
      margin_acc_open_date_is_modified = true;
      ifo_acc_open_date_is_set = true;
      ifo_acc_open_date_is_modified = true;
      only_mobile_open_div_is_set = true;
      only_mobile_open_div_is_modified = true;
      only_mbl_opn_div_last_updater_is_set = true;
      only_mbl_opn_div_last_updater_is_modified = true;
      only_mbl_opn_div_timestamp_is_set = true;
      only_mbl_opn_div_timestamp_is_modified = true;
      email_address_alt2_is_set = true;
      email_address_alt2_is_modified = true;
      order_exe_flag_is_set = true;
      order_exe_flag_is_modified = true;
      order_unexe_flag_is_set = true;
      order_unexe_flag_is_modified = true;
      important_connection_mail_flag_is_set = true;
      important_connection_mail_flag_is_modified = true;
      information_mail2_flag_is_set = true;
      information_mail2_flag_is_modified = true;
      proam_div_is_set = true;
      proam_div_is_modified = true;
      org_deposit_div_is_set = true;
      org_deposit_div_is_modified = true;
      eq_hold_rep_div_is_set = true;
      eq_hold_rep_div_is_modified = true;
      chkup_rep_div_is_set = true;
      chkup_rep_div_is_modified = true;
      pts_acc_open_div_is_set = true;
      pts_acc_open_div_is_modified = true;
      pts_acc_open_div_last_updater_is_set = true;
      pts_acc_open_div_last_updater_is_modified = true;
      pts_acc_open_div_timestamp_is_set = true;
      pts_acc_open_div_timestamp_is_modified = true;
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
      cfd_acc_open_div_is_set = true;
      cfd_acc_open_div_is_modified = true;
      cfd_acc_open_div_last_updater_is_set = true;
      cfd_acc_open_div_last_updater_is_modified = true;
      cfd_acc_open_updated_timestamp_is_set = true;
      cfd_acc_open_updated_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof MainAccountRow ) )
       return false;
    return fieldsEqual( (MainAccountRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のMainAccountRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( MainAccountRow row )
  {
    if ( account_id != row.getAccountId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( institution_id != row.getInstitutionId() )
      return false;
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( branch_id != row.getBranchId() )
      return false;
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( sonar_trader_code == null ) {
      if ( row.getSonarTraderCode() != null )
        return false;
    } else if ( !sonar_trader_code.equals( row.getSonarTraderCode() ) ) {
        return false;
    }
    if ( account_type == null ) {
      if ( row.getAccountType() != null )
        return false;
    } else if ( !account_type.equals( row.getAccountType() ) ) {
        return false;
    }
    if ( family_name == null ) {
      if ( row.getFamilyName() != null )
        return false;
    } else if ( !family_name.equals( row.getFamilyName() ) ) {
        return false;
    }
    if ( middle_name == null ) {
      if ( row.getMiddleName() != null )
        return false;
    } else if ( !middle_name.equals( row.getMiddleName() ) ) {
        return false;
    }
    if ( given_name == null ) {
      if ( row.getGivenName() != null )
        return false;
    } else if ( !given_name.equals( row.getGivenName() ) ) {
        return false;
    }
    if ( family_name_alt1 == null ) {
      if ( row.getFamilyNameAlt1() != null )
        return false;
    } else if ( !family_name_alt1.equals( row.getFamilyNameAlt1() ) ) {
        return false;
    }
    if ( middle_name_alt1 == null ) {
      if ( row.getMiddleNameAlt1() != null )
        return false;
    } else if ( !middle_name_alt1.equals( row.getMiddleNameAlt1() ) ) {
        return false;
    }
    if ( given_name_alt1 == null ) {
      if ( row.getGivenNameAlt1() != null )
        return false;
    } else if ( !given_name_alt1.equals( row.getGivenNameAlt1() ) ) {
        return false;
    }
    if ( family_name_alt2 == null ) {
      if ( row.getFamilyNameAlt2() != null )
        return false;
    } else if ( !family_name_alt2.equals( row.getFamilyNameAlt2() ) ) {
        return false;
    }
    if ( middle_name_alt2 == null ) {
      if ( row.getMiddleNameAlt2() != null )
        return false;
    } else if ( !middle_name_alt2.equals( row.getMiddleNameAlt2() ) ) {
        return false;
    }
    if ( given_name_alt2 == null ) {
      if ( row.getGivenNameAlt2() != null )
        return false;
    } else if ( !given_name_alt2.equals( row.getGivenNameAlt2() ) ) {
        return false;
    }
    if ( zip_code == null ) {
      if ( row.getZipCode() != null )
        return false;
    } else if ( !zip_code.equals( row.getZipCode() ) ) {
        return false;
    }
    if ( sub_zip_code == null ) {
      if ( row.getSubZipCode() != null )
        return false;
    } else if ( !sub_zip_code.equals( row.getSubZipCode() ) ) {
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
    if ( telephone == null ) {
      if ( row.getTelephone() != null )
        return false;
    } else if ( !telephone.equals( row.getTelephone() ) ) {
        return false;
    }
    if ( mobile == null ) {
      if ( row.getMobile() != null )
        return false;
    } else if ( !mobile.equals( row.getMobile() ) ) {
        return false;
    }
    if ( fax == null ) {
      if ( row.getFax() != null )
        return false;
    } else if ( !fax.equals( row.getFax() ) ) {
        return false;
    }
    if ( office == null ) {
      if ( row.getOffice() != null )
        return false;
    } else if ( !office.equals( row.getOffice() ) ) {
        return false;
    }
    if ( office_zip_code == null ) {
      if ( row.getOfficeZipCode() != null )
        return false;
    } else if ( !office_zip_code.equals( row.getOfficeZipCode() ) ) {
        return false;
    }
    if ( office_address == null ) {
      if ( row.getOfficeAddress() != null )
        return false;
    } else if ( !office_address.equals( row.getOfficeAddress() ) ) {
        return false;
    }
    if ( office_telephone == null ) {
      if ( row.getOfficeTelephone() != null )
        return false;
    } else if ( !office_telephone.equals( row.getOfficeTelephone() ) ) {
        return false;
    }
    if ( post == null ) {
      if ( row.getPost() != null )
        return false;
    } else if ( !post.equals( row.getPost() ) ) {
        return false;
    }
    if ( equity_order_exe_mail_flag == null ) {
      if ( row.getEquityOrderExeMailFlag() != null )
        return false;
    } else if ( !equity_order_exe_mail_flag.equals( row.getEquityOrderExeMailFlag() ) ) {
        return false;
    }
    if ( equity_order_unexec_mail_flag == null ) {
      if ( row.getEquityOrderUnexecMailFlag() != null )
        return false;
    } else if ( !equity_order_unexec_mail_flag.equals( row.getEquityOrderUnexecMailFlag() ) ) {
        return false;
    }
    if ( ifo_order_exec_mail_flag == null ) {
      if ( row.getIfoOrderExecMailFlag() != null )
        return false;
    } else if ( !ifo_order_exec_mail_flag.equals( row.getIfoOrderExecMailFlag() ) ) {
        return false;
    }
    if ( ifo_order_unexec_mail_flag == null ) {
      if ( row.getIfoOrderUnexecMailFlag() != null )
        return false;
    } else if ( !ifo_order_unexec_mail_flag.equals( row.getIfoOrderUnexecMailFlag() ) ) {
        return false;
    }
    if ( information_mail_flag == null ) {
      if ( row.getInformationMailFlag() != null )
        return false;
    } else if ( !information_mail_flag.equals( row.getInformationMailFlag() ) ) {
        return false;
    }
    if ( resident == null ) {
      if ( row.getResident() != null )
        return false;
    } else if ( !resident.equals( row.getResident() ) ) {
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
    if ( email_address == null ) {
      if ( row.getEmailAddress() != null )
        return false;
    } else if ( !email_address.equals( row.getEmailAddress() ) ) {
        return false;
    }
    if ( email_address_alt1 == null ) {
      if ( row.getEmailAddressAlt1() != null )
        return false;
    } else if ( !email_address_alt1.equals( row.getEmailAddressAlt1() ) ) {
        return false;
    }
    if ( trading_password == null ) {
      if ( row.getTradingPassword() != null )
        return false;
    } else if ( !trading_password.equals( row.getTradingPassword() ) ) {
        return false;
    }
    if ( account_open_date == null ) {
      if ( row.getAccountOpenDate() != null )
        return false;
    } else if ( !account_open_date.equals( row.getAccountOpenDate() ) ) {
        return false;
    }
    if ( account_close_date == null ) {
      if ( row.getAccountCloseDate() != null )
        return false;
    } else if ( !account_close_date.equals( row.getAccountCloseDate() ) ) {
        return false;
    }
    if ( acc_open_send_mail_status == null ) {
      if ( row.getAccOpenSendMailStatus() != null )
        return false;
    } else if ( !acc_open_send_mail_status.equals( row.getAccOpenSendMailStatus() ) ) {
        return false;
    }
    if ( person_identify == null ) {
      if ( row.getPersonIdentify() != null )
        return false;
    } else if ( !person_identify.equals( row.getPersonIdentify() ) ) {
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
    if ( yellow_customer == null ) {
      if ( row.getYellowCustomer() != null )
        return false;
    } else if ( !yellow_customer.equals( row.getYellowCustomer() ) ) {
        return false;
    }
    if ( ht_settlement_way == null ) {
      if ( row.getHtSettlementWay() != null )
        return false;
    } else if ( !ht_settlement_way.equals( row.getHtSettlementWay() ) ) {
        return false;
    }
    if ( bank_account_regi == null ) {
      if ( row.getBankAccountRegi() != null )
        return false;
    } else if ( !bank_account_regi.equals( row.getBankAccountRegi() ) ) {
        return false;
    }
    if ( account_status == null ) {
      if ( row.getAccountStatus() != null )
        return false;
    } else if ( !account_status.equals( row.getAccountStatus() ) ) {
        return false;
    }
    if ( examin_lock_flag == null ) {
      if ( row.getExaminLockFlag() != null )
        return false;
    } else if ( !examin_lock_flag.equals( row.getExaminLockFlag() ) ) {
        return false;
    }
    if ( mng_lock_flag == null ) {
      if ( row.getMngLockFlag() != null )
        return false;
    } else if ( !mng_lock_flag.equals( row.getMngLockFlag() ) ) {
        return false;
    }
    if ( mng_lock_off_start_date == null ) {
      if ( row.getMngLockOffStartDate() != null )
        return false;
    } else if ( !mng_lock_off_start_date.equals( row.getMngLockOffStartDate() ) ) {
        return false;
    }
    if ( mng_lock_off_end_date == null ) {
      if ( row.getMngLockOffEndDate() != null )
        return false;
    } else if ( !mng_lock_off_end_date.equals( row.getMngLockOffEndDate() ) ) {
        return false;
    }
    if ( mng_lock_flag_advance == null ) {
      if ( row.getMngLockFlagAdvance() != null )
        return false;
    } else if ( !mng_lock_flag_advance.equals( row.getMngLockFlagAdvance() ) ) {
        return false;
    }
    if ( mng_lock_flag_unpay_margin == null ) {
      if ( row.getMngLockFlagUnpayMargin() != null )
        return false;
    } else if ( !mng_lock_flag_unpay_margin.equals( row.getMngLockFlagUnpayMargin() ) ) {
        return false;
    }
    if ( mng_lock_flag_short_security == null ) {
      if ( row.getMngLockFlagShortSecurity() != null )
        return false;
    } else if ( !mng_lock_flag_short_security.equals( row.getMngLockFlagShortSecurity() ) ) {
        return false;
    }
    if ( mng_lock_flag_unsubstit_depo == null ) {
      if ( row.getMngLockFlagUnsubstitDepo() != null )
        return false;
    } else if ( !mng_lock_flag_unsubstit_depo.equals( row.getMngLockFlagUnsubstitDepo() ) ) {
        return false;
    }
    if ( branch_lock == null ) {
      if ( row.getBranchLock() != null )
        return false;
    } else if ( !branch_lock.equals( row.getBranchLock() ) ) {
        return false;
    }
    if ( order_permission == null ) {
      if ( row.getOrderPermission() != null )
        return false;
    } else if ( !order_permission.equals( row.getOrderPermission() ) ) {
        return false;
    }
    if ( tax_type == null ) {
      if ( row.getTaxType() != null )
        return false;
    } else if ( !tax_type.equals( row.getTaxType() ) ) {
        return false;
    }
    if ( tax_type_next == null ) {
      if ( row.getTaxTypeNext() != null )
        return false;
    } else if ( !tax_type_next.equals( row.getTaxTypeNext() ) ) {
        return false;
    }
    if ( margin_tax_type == null ) {
      if ( row.getMarginTaxType() != null )
        return false;
    } else if ( !margin_tax_type.equals( row.getMarginTaxType() ) ) {
        return false;
    }
    if ( margin_tax_type_next == null ) {
      if ( row.getMarginTaxTypeNext() != null )
        return false;
    } else if ( !margin_tax_type_next.equals( row.getMarginTaxTypeNext() ) ) {
        return false;
    }
    if ( qualified_inst_investor_div == null ) {
      if ( row.getQualifiedInstInvestorDiv() != null )
        return false;
    } else if ( !qualified_inst_investor_div.equals( row.getQualifiedInstInvestorDiv() ) ) {
        return false;
    }
    if ( margin_sys_acc_open_div == null ) {
      if ( row.getMarginSysAccOpenDiv() != null )
        return false;
    } else if ( !margin_sys_acc_open_div.equals( row.getMarginSysAccOpenDiv() ) ) {
        return false;
    }
    if ( margin_gen_acc_open_div == null ) {
      if ( row.getMarginGenAccOpenDiv() != null )
        return false;
    } else if ( !margin_gen_acc_open_div.equals( row.getMarginGenAccOpenDiv() ) ) {
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
    if ( transfer_count == null ) {
      if ( !row.getTransferCountIsNull() )
        return false;
    } else if ( row.getTransferCountIsNull() || ( transfer_count.intValue() != row.getTransferCount() ) ) {
        return false;
    }
    if ( foreign_sec_acc_open_div == null ) {
      if ( row.getForeignSecAccOpenDiv() != null )
        return false;
    } else if ( !foreign_sec_acc_open_div.equals( row.getForeignSecAccOpenDiv() ) ) {
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
    if ( ruito_acc_open_div == null ) {
      if ( row.getRuitoAccOpenDiv() != null )
        return false;
    } else if ( !ruito_acc_open_div.equals( row.getRuitoAccOpenDiv() ) ) {
        return false;
    }
    if ( mrf_acc_open_div == null ) {
      if ( row.getMrfAccOpenDiv() != null )
        return false;
    } else if ( !mrf_acc_open_div.equals( row.getMrfAccOpenDiv() ) ) {
        return false;
    }
    if ( fx_acc_open_div == null ) {
      if ( row.getFxAccOpenDiv() != null )
        return false;
    } else if ( !fx_acc_open_div.equals( row.getFxAccOpenDiv() ) ) {
        return false;
    }
    if ( feq_con_acc_open_div == null ) {
      if ( row.getFeqConAccOpenDiv() != null )
        return false;
    } else if ( !feq_con_acc_open_div.equals( row.getFeqConAccOpenDiv() ) ) {
        return false;
    }
    if ( top_page_id == null ) {
      if ( row.getTopPageId() != null )
        return false;
    } else if ( !top_page_id.equals( row.getTopPageId() ) ) {
        return false;
    }
    if ( quoto_type == null ) {
      if ( row.getQuotoType() != null )
        return false;
    } else if ( !quoto_type.equals( row.getQuotoType() ) ) {
        return false;
    }
    if ( trading_report_div == null ) {
      if ( row.getTradingReportDiv() != null )
        return false;
    } else if ( !trading_report_div.equals( row.getTradingReportDiv() ) ) {
        return false;
    }
    if ( position_report_div == null ) {
      if ( row.getPositionReportDiv() != null )
        return false;
    } else if ( !position_report_div.equals( row.getPositionReportDiv() ) ) {
        return false;
    }
    if ( position_report_cycle_div == null ) {
      if ( row.getPositionReportCycleDiv() != null )
        return false;
    } else if ( !position_report_cycle_div.equals( row.getPositionReportCycleDiv() ) ) {
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
    if ( email_last_updater == null ) {
      if ( row.getEmailLastUpdater() != null )
        return false;
    } else if ( !email_last_updater.equals( row.getEmailLastUpdater() ) ) {
        return false;
    }
    if ( email_last_updated_timestamp == null ) {
      if ( row.getEmailLastUpdatedTimestamp() != null )
        return false;
    } else if ( !email_last_updated_timestamp.equals( row.getEmailLastUpdatedTimestamp() ) ) {
        return false;
    }
    if ( trading_password_updater == null ) {
      if ( row.getTradingPasswordUpdater() != null )
        return false;
    } else if ( !trading_password_updater.equals( row.getTradingPasswordUpdater() ) ) {
        return false;
    }
    if ( tr_pwd_last_update_timestamp == null ) {
      if ( row.getTrPwdLastUpdateTimestamp() != null )
        return false;
    } else if ( !tr_pwd_last_update_timestamp.equals( row.getTrPwdLastUpdateTimestamp() ) ) {
        return false;
    }
    if ( mb_off_last_updater == null ) {
      if ( row.getMbOffLastUpdater() != null )
        return false;
    } else if ( !mb_off_last_updater.equals( row.getMbOffLastUpdater() ) ) {
        return false;
    }
    if ( mb_off_last_updated_timestamp == null ) {
      if ( row.getMbOffLastUpdatedTimestamp() != null )
        return false;
    } else if ( !mb_off_last_updated_timestamp.equals( row.getMbOffLastUpdatedTimestamp() ) ) {
        return false;
    }
    if ( enable_order_last_updater == null ) {
      if ( row.getEnableOrderLastUpdater() != null )
        return false;
    } else if ( !enable_order_last_updater.equals( row.getEnableOrderLastUpdater() ) ) {
        return false;
    }
    if ( enable_order_updated_timestamp == null ) {
      if ( row.getEnableOrderUpdatedTimestamp() != null )
        return false;
    } else if ( !enable_order_updated_timestamp.equals( row.getEnableOrderUpdatedTimestamp() ) ) {
        return false;
    }
    if ( fx_acc_open_div_last_updater == null ) {
      if ( row.getFxAccOpenDivLastUpdater() != null )
        return false;
    } else if ( !fx_acc_open_div_last_updater.equals( row.getFxAccOpenDivLastUpdater() ) ) {
        return false;
    }
    if ( fx_acc_open_updated_timestamp == null ) {
      if ( row.getFxAccOpenUpdatedTimestamp() != null )
        return false;
    } else if ( !fx_acc_open_updated_timestamp.equals( row.getFxAccOpenUpdatedTimestamp() ) ) {
        return false;
    }
    if ( feq_con_acc_open_div_updater == null ) {
      if ( row.getFeqConAccOpenDivUpdater() != null )
        return false;
    } else if ( !feq_con_acc_open_div_updater.equals( row.getFeqConAccOpenDivUpdater() ) ) {
        return false;
    }
    if ( feq_con_acc_open_timestamp == null ) {
      if ( row.getFeqConAccOpenTimestamp() != null )
        return false;
    } else if ( !feq_con_acc_open_timestamp.equals( row.getFeqConAccOpenTimestamp() ) ) {
        return false;
    }
    if ( mrf_fund_code == null ) {
      if ( row.getMrfFundCode() != null )
        return false;
    } else if ( !mrf_fund_code.equals( row.getMrfFundCode() ) ) {
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
    if ( sp_mng_acc_open_div == null ) {
      if ( row.getSpMngAccOpenDiv() != null )
        return false;
    } else if ( !sp_mng_acc_open_div.equals( row.getSpMngAccOpenDiv() ) ) {
        return false;
    }
    if ( lock_registration_reason == null ) {
      if ( row.getLockRegistrationReason() != null )
        return false;
    } else if ( !lock_registration_reason.equals( row.getLockRegistrationReason() ) ) {
        return false;
    }
    if ( inf_mail_flg_last_updater == null ) {
      if ( row.getInfMailFlgLastUpdater() != null )
        return false;
    } else if ( !inf_mail_flg_last_updater.equals( row.getInfMailFlgLastUpdater() ) ) {
        return false;
    }
    if ( inf_mail_flg_updated_timestamp == null ) {
      if ( row.getInfMailFlgUpdatedTimestamp() != null )
        return false;
    } else if ( !inf_mail_flg_updated_timestamp.equals( row.getInfMailFlgUpdatedTimestamp() ) ) {
        return false;
    }
    if ( eq_exe_ml_flg_last_updater == null ) {
      if ( row.getEqExeMlFlgLastUpdater() != null )
        return false;
    } else if ( !eq_exe_ml_flg_last_updater.equals( row.getEqExeMlFlgLastUpdater() ) ) {
        return false;
    }
    if ( eq_exe_ml_flg_timestamp == null ) {
      if ( row.getEqExeMlFlgTimestamp() != null )
        return false;
    } else if ( !eq_exe_ml_flg_timestamp.equals( row.getEqExeMlFlgTimestamp() ) ) {
        return false;
    }
    if ( eq_unexe_ml_flg_last_updater == null ) {
      if ( row.getEqUnexeMlFlgLastUpdater() != null )
        return false;
    } else if ( !eq_unexe_ml_flg_last_updater.equals( row.getEqUnexeMlFlgLastUpdater() ) ) {
        return false;
    }
    if ( eq_unexe_ml_flg_timestamp == null ) {
      if ( row.getEqUnexeMlFlgTimestamp() != null )
        return false;
    } else if ( !eq_unexe_ml_flg_timestamp.equals( row.getEqUnexeMlFlgTimestamp() ) ) {
        return false;
    }
    if ( ifo_exe_ml_flg_last_updater == null ) {
      if ( row.getIfoExeMlFlgLastUpdater() != null )
        return false;
    } else if ( !ifo_exe_ml_flg_last_updater.equals( row.getIfoExeMlFlgLastUpdater() ) ) {
        return false;
    }
    if ( ifo_exe_ml_flg_timestamp == null ) {
      if ( row.getIfoExeMlFlgTimestamp() != null )
        return false;
    } else if ( !ifo_exe_ml_flg_timestamp.equals( row.getIfoExeMlFlgTimestamp() ) ) {
        return false;
    }
    if ( ifo_unexe_ml_flg_last_updater == null ) {
      if ( row.getIfoUnexeMlFlgLastUpdater() != null )
        return false;
    } else if ( !ifo_unexe_ml_flg_last_updater.equals( row.getIfoUnexeMlFlgLastUpdater() ) ) {
        return false;
    }
    if ( ifo_unexe_ml_flg_timestamp == null ) {
      if ( row.getIfoUnexeMlFlgTimestamp() != null )
        return false;
    } else if ( !ifo_unexe_ml_flg_timestamp.equals( row.getIfoUnexeMlFlgTimestamp() ) ) {
        return false;
    }
    if ( stock_option_acc_open_div == null ) {
      if ( row.getStockOptionAccOpenDiv() != null )
        return false;
    } else if ( !stock_option_acc_open_div.equals( row.getStockOptionAccOpenDiv() ) ) {
        return false;
    }
    if ( secured_loan_sec_acc_open_div == null ) {
      if ( row.getSecuredLoanSecAccOpenDiv() != null )
        return false;
    } else if ( !secured_loan_sec_acc_open_div.equals( row.getSecuredLoanSecAccOpenDiv() ) ) {
        return false;
    }
    if ( margin_acc_open_date == null ) {
      if ( row.getMarginAccOpenDate() != null )
        return false;
    } else if ( !margin_acc_open_date.equals( row.getMarginAccOpenDate() ) ) {
        return false;
    }
    if ( ifo_acc_open_date == null ) {
      if ( row.getIfoAccOpenDate() != null )
        return false;
    } else if ( !ifo_acc_open_date.equals( row.getIfoAccOpenDate() ) ) {
        return false;
    }
    if ( only_mobile_open_div == null ) {
      if ( row.getOnlyMobileOpenDiv() != null )
        return false;
    } else if ( !only_mobile_open_div.equals( row.getOnlyMobileOpenDiv() ) ) {
        return false;
    }
    if ( only_mbl_opn_div_last_updater == null ) {
      if ( row.getOnlyMblOpnDivLastUpdater() != null )
        return false;
    } else if ( !only_mbl_opn_div_last_updater.equals( row.getOnlyMblOpnDivLastUpdater() ) ) {
        return false;
    }
    if ( only_mbl_opn_div_timestamp == null ) {
      if ( row.getOnlyMblOpnDivTimestamp() != null )
        return false;
    } else if ( !only_mbl_opn_div_timestamp.equals( row.getOnlyMblOpnDivTimestamp() ) ) {
        return false;
    }
    if ( email_address_alt2 == null ) {
      if ( row.getEmailAddressAlt2() != null )
        return false;
    } else if ( !email_address_alt2.equals( row.getEmailAddressAlt2() ) ) {
        return false;
    }
    if ( order_exe_flag == null ) {
      if ( !row.getOrderExeFlagIsNull() )
        return false;
    } else if ( row.getOrderExeFlagIsNull() || ( order_exe_flag.intValue() != row.getOrderExeFlag() ) ) {
        return false;
    }
    if ( order_unexe_flag == null ) {
      if ( !row.getOrderUnexeFlagIsNull() )
        return false;
    } else if ( row.getOrderUnexeFlagIsNull() || ( order_unexe_flag.intValue() != row.getOrderUnexeFlag() ) ) {
        return false;
    }
    if ( important_connection_mail_flag == null ) {
      if ( !row.getImportantConnectionMailFlagIsNull() )
        return false;
    } else if ( row.getImportantConnectionMailFlagIsNull() || ( important_connection_mail_flag.intValue() != row.getImportantConnectionMailFlag() ) ) {
        return false;
    }
    if ( information_mail2_flag == null ) {
      if ( !row.getInformationMail2FlagIsNull() )
        return false;
    } else if ( row.getInformationMail2FlagIsNull() || ( information_mail2_flag.intValue() != row.getInformationMail2Flag() ) ) {
        return false;
    }
    if ( proam_div == null ) {
      if ( row.getProamDiv() != null )
        return false;
    } else if ( !proam_div.equals( row.getProamDiv() ) ) {
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
    if ( pts_acc_open_div == null ) {
      if ( row.getPtsAccOpenDiv() != null )
        return false;
    } else if ( !pts_acc_open_div.equals( row.getPtsAccOpenDiv() ) ) {
        return false;
    }
    if ( pts_acc_open_div_last_updater == null ) {
      if ( row.getPtsAccOpenDivLastUpdater() != null )
        return false;
    } else if ( !pts_acc_open_div_last_updater.equals( row.getPtsAccOpenDivLastUpdater() ) ) {
        return false;
    }
    if ( pts_acc_open_div_timestamp == null ) {
      if ( row.getPtsAccOpenDivTimestamp() != null )
        return false;
    } else if ( !pts_acc_open_div_timestamp.equals( row.getPtsAccOpenDivTimestamp() ) ) {
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
    if ( cfd_acc_open_div == null ) {
      if ( row.getCfdAccOpenDiv() != null )
        return false;
    } else if ( !cfd_acc_open_div.equals( row.getCfdAccOpenDiv() ) ) {
        return false;
    }
    if ( cfd_acc_open_div_last_updater == null ) {
      if ( row.getCfdAccOpenDivLastUpdater() != null )
        return false;
    } else if ( !cfd_acc_open_div_last_updater.equals( row.getCfdAccOpenDivLastUpdater() ) ) {
        return false;
    }
    if ( cfd_acc_open_updated_timestamp == null ) {
      if ( row.getCfdAccOpenUpdatedTimestamp() != null )
        return false;
    } else if ( !cfd_acc_open_updated_timestamp.equals( row.getCfdAccOpenUpdatedTimestamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) account_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) institution_id)
        + (account_code!=null? account_code.hashCode(): 0) 
        + ((int) branch_id)
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (sonar_trader_code!=null? sonar_trader_code.hashCode(): 0) 
        + (account_type!=null? account_type.hashCode(): 0) 
        + (family_name!=null? family_name.hashCode(): 0) 
        + (middle_name!=null? middle_name.hashCode(): 0) 
        + (given_name!=null? given_name.hashCode(): 0) 
        + (family_name_alt1!=null? family_name_alt1.hashCode(): 0) 
        + (middle_name_alt1!=null? middle_name_alt1.hashCode(): 0) 
        + (given_name_alt1!=null? given_name_alt1.hashCode(): 0) 
        + (family_name_alt2!=null? family_name_alt2.hashCode(): 0) 
        + (middle_name_alt2!=null? middle_name_alt2.hashCode(): 0) 
        + (given_name_alt2!=null? given_name_alt2.hashCode(): 0) 
        + (zip_code!=null? zip_code.hashCode(): 0) 
        + (sub_zip_code!=null? sub_zip_code.hashCode(): 0) 
        + (address_line1!=null? address_line1.hashCode(): 0) 
        + (address_line2!=null? address_line2.hashCode(): 0) 
        + (address_line3!=null? address_line3.hashCode(): 0) 
        + (address_line1_kana!=null? address_line1_kana.hashCode(): 0) 
        + (address_line2_kana!=null? address_line2_kana.hashCode(): 0) 
        + (address_line3_kana!=null? address_line3_kana.hashCode(): 0) 
        + (telephone!=null? telephone.hashCode(): 0) 
        + (mobile!=null? mobile.hashCode(): 0) 
        + (fax!=null? fax.hashCode(): 0) 
        + (office!=null? office.hashCode(): 0) 
        + (office_zip_code!=null? office_zip_code.hashCode(): 0) 
        + (office_address!=null? office_address.hashCode(): 0) 
        + (office_telephone!=null? office_telephone.hashCode(): 0) 
        + (post!=null? post.hashCode(): 0) 
        + (equity_order_exe_mail_flag!=null? equity_order_exe_mail_flag.hashCode(): 0) 
        + (equity_order_unexec_mail_flag!=null? equity_order_unexec_mail_flag.hashCode(): 0) 
        + (ifo_order_exec_mail_flag!=null? ifo_order_exec_mail_flag.hashCode(): 0) 
        + (ifo_order_unexec_mail_flag!=null? ifo_order_unexec_mail_flag.hashCode(): 0) 
        + (information_mail_flag!=null? information_mail_flag.hashCode(): 0) 
        + (resident!=null? resident.hashCode(): 0) 
        + (new_account_div!=null? new_account_div.hashCode(): 0) 
        + (via_trust_bank_div!=null? via_trust_bank_div.hashCode(): 0) 
        + (class_div!=null? class_div.hashCode(): 0) 
        + (email_address!=null? email_address.hashCode(): 0) 
        + (email_address_alt1!=null? email_address_alt1.hashCode(): 0) 
        + (trading_password!=null? trading_password.hashCode(): 0) 
        + (account_open_date!=null? account_open_date.hashCode(): 0) 
        + (account_close_date!=null? account_close_date.hashCode(): 0) 
        + (acc_open_send_mail_status!=null? acc_open_send_mail_status.hashCode(): 0) 
        + (person_identify!=null? person_identify.hashCode(): 0) 
        + (era_born!=null? era_born.hashCode(): 0) 
        + (born_date!=null? born_date.hashCode(): 0) 
        + (sex!=null? sex.hashCode(): 0) 
        + (yellow_customer!=null? yellow_customer.hashCode(): 0) 
        + (ht_settlement_way!=null? ht_settlement_way.hashCode(): 0) 
        + (bank_account_regi!=null? bank_account_regi.hashCode(): 0) 
        + (account_status!=null? account_status.hashCode(): 0) 
        + (examin_lock_flag!=null? examin_lock_flag.hashCode(): 0) 
        + (mng_lock_flag!=null? mng_lock_flag.hashCode(): 0) 
        + (mng_lock_off_start_date!=null? mng_lock_off_start_date.hashCode(): 0) 
        + (mng_lock_off_end_date!=null? mng_lock_off_end_date.hashCode(): 0) 
        + (mng_lock_flag_advance!=null? mng_lock_flag_advance.hashCode(): 0) 
        + (mng_lock_flag_unpay_margin!=null? mng_lock_flag_unpay_margin.hashCode(): 0) 
        + (mng_lock_flag_short_security!=null? mng_lock_flag_short_security.hashCode(): 0) 
        + (mng_lock_flag_unsubstit_depo!=null? mng_lock_flag_unsubstit_depo.hashCode(): 0) 
        + (branch_lock!=null? branch_lock.hashCode(): 0) 
        + (order_permission!=null? order_permission.hashCode(): 0) 
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (tax_type_next!=null? tax_type_next.hashCode(): 0) 
        + (margin_tax_type!=null? margin_tax_type.hashCode(): 0) 
        + (margin_tax_type_next!=null? margin_tax_type_next.hashCode(): 0) 
        + (qualified_inst_investor_div!=null? qualified_inst_investor_div.hashCode(): 0) 
        + (margin_sys_acc_open_div!=null? margin_sys_acc_open_div.hashCode(): 0) 
        + (margin_gen_acc_open_div!=null? margin_gen_acc_open_div.hashCode(): 0) 
        + (equity_sp_acc_open_date!=null? equity_sp_acc_open_date.hashCode(): 0) 
        + (margin_sp_acc_open_date!=null? margin_sp_acc_open_date.hashCode(): 0) 
        + (transfer_count!=null? transfer_count.hashCode(): 0) 
        + (foreign_sec_acc_open_div!=null? foreign_sec_acc_open_div.hashCode(): 0) 
        + (ifo_acc_open_div_tokyo!=null? ifo_acc_open_div_tokyo.hashCode(): 0) 
        + (ifo_acc_open_div_osaka!=null? ifo_acc_open_div_osaka.hashCode(): 0) 
        + (ifo_acc_open_div_nagoya!=null? ifo_acc_open_div_nagoya.hashCode(): 0) 
        + (ruito_acc_open_div!=null? ruito_acc_open_div.hashCode(): 0) 
        + (mrf_acc_open_div!=null? mrf_acc_open_div.hashCode(): 0) 
        + (fx_acc_open_div!=null? fx_acc_open_div.hashCode(): 0) 
        + (feq_con_acc_open_div!=null? feq_con_acc_open_div.hashCode(): 0) 
        + (top_page_id!=null? top_page_id.hashCode(): 0) 
        + (quoto_type!=null? quoto_type.hashCode(): 0) 
        + (trading_report_div!=null? trading_report_div.hashCode(): 0) 
        + (position_report_div!=null? position_report_div.hashCode(): 0) 
        + (position_report_cycle_div!=null? position_report_cycle_div.hashCode(): 0) 
        + (certificate_deposit_flag!=null? certificate_deposit_flag.hashCode(): 0) 
        + (account_statement_flag!=null? account_statement_flag.hashCode(): 0) 
        + (email_last_updater!=null? email_last_updater.hashCode(): 0) 
        + (email_last_updated_timestamp!=null? email_last_updated_timestamp.hashCode(): 0) 
        + (trading_password_updater!=null? trading_password_updater.hashCode(): 0) 
        + (tr_pwd_last_update_timestamp!=null? tr_pwd_last_update_timestamp.hashCode(): 0) 
        + (mb_off_last_updater!=null? mb_off_last_updater.hashCode(): 0) 
        + (mb_off_last_updated_timestamp!=null? mb_off_last_updated_timestamp.hashCode(): 0) 
        + (enable_order_last_updater!=null? enable_order_last_updater.hashCode(): 0) 
        + (enable_order_updated_timestamp!=null? enable_order_updated_timestamp.hashCode(): 0) 
        + (fx_acc_open_div_last_updater!=null? fx_acc_open_div_last_updater.hashCode(): 0) 
        + (fx_acc_open_updated_timestamp!=null? fx_acc_open_updated_timestamp.hashCode(): 0) 
        + (feq_con_acc_open_div_updater!=null? feq_con_acc_open_div_updater.hashCode(): 0) 
        + (feq_con_acc_open_timestamp!=null? feq_con_acc_open_timestamp.hashCode(): 0) 
        + (mrf_fund_code!=null? mrf_fund_code.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (sp_mng_acc_open_div!=null? sp_mng_acc_open_div.hashCode(): 0) 
        + (lock_registration_reason!=null? lock_registration_reason.hashCode(): 0) 
        + (inf_mail_flg_last_updater!=null? inf_mail_flg_last_updater.hashCode(): 0) 
        + (inf_mail_flg_updated_timestamp!=null? inf_mail_flg_updated_timestamp.hashCode(): 0) 
        + (eq_exe_ml_flg_last_updater!=null? eq_exe_ml_flg_last_updater.hashCode(): 0) 
        + (eq_exe_ml_flg_timestamp!=null? eq_exe_ml_flg_timestamp.hashCode(): 0) 
        + (eq_unexe_ml_flg_last_updater!=null? eq_unexe_ml_flg_last_updater.hashCode(): 0) 
        + (eq_unexe_ml_flg_timestamp!=null? eq_unexe_ml_flg_timestamp.hashCode(): 0) 
        + (ifo_exe_ml_flg_last_updater!=null? ifo_exe_ml_flg_last_updater.hashCode(): 0) 
        + (ifo_exe_ml_flg_timestamp!=null? ifo_exe_ml_flg_timestamp.hashCode(): 0) 
        + (ifo_unexe_ml_flg_last_updater!=null? ifo_unexe_ml_flg_last_updater.hashCode(): 0) 
        + (ifo_unexe_ml_flg_timestamp!=null? ifo_unexe_ml_flg_timestamp.hashCode(): 0) 
        + (stock_option_acc_open_div!=null? stock_option_acc_open_div.hashCode(): 0) 
        + (secured_loan_sec_acc_open_div!=null? secured_loan_sec_acc_open_div.hashCode(): 0) 
        + (margin_acc_open_date!=null? margin_acc_open_date.hashCode(): 0) 
        + (ifo_acc_open_date!=null? ifo_acc_open_date.hashCode(): 0) 
        + (only_mobile_open_div!=null? only_mobile_open_div.hashCode(): 0) 
        + (only_mbl_opn_div_last_updater!=null? only_mbl_opn_div_last_updater.hashCode(): 0) 
        + (only_mbl_opn_div_timestamp!=null? only_mbl_opn_div_timestamp.hashCode(): 0) 
        + (email_address_alt2!=null? email_address_alt2.hashCode(): 0) 
        + (order_exe_flag!=null? order_exe_flag.hashCode(): 0) 
        + (order_unexe_flag!=null? order_unexe_flag.hashCode(): 0) 
        + (important_connection_mail_flag!=null? important_connection_mail_flag.hashCode(): 0) 
        + (information_mail2_flag!=null? information_mail2_flag.hashCode(): 0) 
        + (proam_div!=null? proam_div.hashCode(): 0) 
        + (org_deposit_div!=null? org_deposit_div.hashCode(): 0) 
        + (eq_hold_rep_div!=null? eq_hold_rep_div.hashCode(): 0) 
        + (chkup_rep_div!=null? chkup_rep_div.hashCode(): 0) 
        + (pts_acc_open_div!=null? pts_acc_open_div.hashCode(): 0) 
        + (pts_acc_open_div_last_updater!=null? pts_acc_open_div_last_updater.hashCode(): 0) 
        + (pts_acc_open_div_timestamp!=null? pts_acc_open_div_timestamp.hashCode(): 0) 
        + (broadcast_law!=null? broadcast_law.hashCode(): 0) 
        + (aviation_law!=null? aviation_law.hashCode(): 0) 
        + (ntt_law!=null? ntt_law.hashCode(): 0) 
        + (dividend_trans_designate!=null? dividend_trans_designate.hashCode(): 0) 
        + (standing_proxy!=null? standing_proxy.hashCode(): 0) 
        + (statutory_agent!=null? statutory_agent.hashCode(): 0) 
        + (affiliate_account_code!=null? affiliate_account_code.hashCode(): 0) 
        + (agency_notify_cmp_div!=null? agency_notify_cmp_div.hashCode(): 0) 
        + (cfd_acc_open_div!=null? cfd_acc_open_div.hashCode(): 0) 
        + (cfd_acc_open_div_last_updater!=null? cfd_acc_open_div_last_updater.hashCode(): 0) 
        + (cfd_acc_open_updated_timestamp!=null? cfd_acc_open_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !institution_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_id' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !branch_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_id' must be set before inserting.");
		if ( !account_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_type' must be set before inserting.");
		if ( !family_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'family_name' must be set before inserting.");
		if ( !family_name_alt1_is_set )
			throw new IllegalArgumentException("non-nullable field 'family_name_alt1' must be set before inserting.");
		if ( !zip_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'zip_code' must be set before inserting.");
		if ( !address_line1_is_set )
			throw new IllegalArgumentException("non-nullable field 'address_line1' must be set before inserting.");
		if ( !equity_order_exe_mail_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'equity_order_exe_mail_flag' must be set before inserting.");
		if ( !equity_order_unexec_mail_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'equity_order_unexec_mail_flag' must be set before inserting.");
		if ( !ifo_order_exec_mail_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_order_exec_mail_flag' must be set before inserting.");
		if ( !ifo_order_unexec_mail_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_order_unexec_mail_flag' must be set before inserting.");
		if ( !information_mail_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'information_mail_flag' must be set before inserting.");
		if ( !trading_password_is_set )
			throw new IllegalArgumentException("non-nullable field 'trading_password' must be set before inserting.");
		if ( !tax_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'tax_type' must be set before inserting.");
		if ( !tax_type_next_is_set )
			throw new IllegalArgumentException("non-nullable field 'tax_type_next' must be set before inserting.");
		if ( !trading_report_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'trading_report_div' must be set before inserting.");
		if ( !position_report_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'position_report_div' must be set before inserting.");
		if ( !position_report_cycle_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'position_report_cycle_div' must be set before inserting.");
		if ( !certificate_deposit_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'certificate_deposit_flag' must be set before inserting.");
		if ( !account_statement_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_statement_flag' must be set before inserting.");
		if ( !email_last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'email_last_updater' must be set before inserting.");
		if ( !email_last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'email_last_updated_timestamp' must be set before inserting.");
		if ( !trading_password_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'trading_password_updater' must be set before inserting.");
		if ( !tr_pwd_last_update_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'tr_pwd_last_update_timestamp' must be set before inserting.");
		if ( !mb_off_last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'mb_off_last_updater' must be set before inserting.");
		if ( !mb_off_last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'mb_off_last_updated_timestamp' must be set before inserting.");
		if ( !enable_order_last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'enable_order_last_updater' must be set before inserting.");
		if ( !enable_order_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'enable_order_updated_timestamp' must be set before inserting.");
		if ( !mrf_fund_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'mrf_fund_code' must be set before inserting.");
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
		map.put("account_id",new Long(account_id));
		map.put("institution_code",institution_code);
		map.put("institution_id",new Long(institution_id));
		map.put("account_code",account_code);
		map.put("branch_id",new Long(branch_id));
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		if ( sonar_trader_code != null )
			map.put("sonar_trader_code",sonar_trader_code);
		map.put("account_type",account_type);
		map.put("family_name",family_name);
		if ( middle_name != null )
			map.put("middle_name",middle_name);
		if ( given_name != null )
			map.put("given_name",given_name);
		map.put("family_name_alt1",family_name_alt1);
		if ( middle_name_alt1 != null )
			map.put("middle_name_alt1",middle_name_alt1);
		if ( given_name_alt1 != null )
			map.put("given_name_alt1",given_name_alt1);
		if ( family_name_alt2 != null )
			map.put("family_name_alt2",family_name_alt2);
		if ( middle_name_alt2 != null )
			map.put("middle_name_alt2",middle_name_alt2);
		if ( given_name_alt2 != null )
			map.put("given_name_alt2",given_name_alt2);
		map.put("zip_code",zip_code);
		if ( sub_zip_code != null )
			map.put("sub_zip_code",sub_zip_code);
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
		if ( telephone != null )
			map.put("telephone",telephone);
		if ( mobile != null )
			map.put("mobile",mobile);
		if ( fax != null )
			map.put("fax",fax);
		if ( office != null )
			map.put("office",office);
		if ( office_zip_code != null )
			map.put("office_zip_code",office_zip_code);
		if ( office_address != null )
			map.put("office_address",office_address);
		if ( office_telephone != null )
			map.put("office_telephone",office_telephone);
		if ( post != null )
			map.put("post",post);
		map.put("equity_order_exe_mail_flag",equity_order_exe_mail_flag);
		map.put("equity_order_unexec_mail_flag",equity_order_unexec_mail_flag);
		map.put("ifo_order_exec_mail_flag",ifo_order_exec_mail_flag);
		map.put("ifo_order_unexec_mail_flag",ifo_order_unexec_mail_flag);
		map.put("information_mail_flag",information_mail_flag);
		if ( resident != null )
			map.put("resident",resident);
		if ( new_account_div != null )
			map.put("new_account_div",new_account_div);
		if ( via_trust_bank_div != null )
			map.put("via_trust_bank_div",via_trust_bank_div);
		if ( class_div != null )
			map.put("class_div",class_div);
		if ( email_address != null )
			map.put("email_address",email_address);
		if ( email_address_alt1 != null )
			map.put("email_address_alt1",email_address_alt1);
		map.put("trading_password",trading_password);
		if ( account_open_date != null )
			map.put("account_open_date",account_open_date);
		if ( account_close_date != null )
			map.put("account_close_date",account_close_date);
		if ( acc_open_send_mail_status != null )
			map.put("acc_open_send_mail_status",acc_open_send_mail_status);
		if ( person_identify != null )
			map.put("person_identify",person_identify);
		if ( era_born != null )
			map.put("era_born",era_born);
		if ( born_date != null )
			map.put("born_date",born_date);
		if ( sex != null )
			map.put("sex",sex);
		if ( yellow_customer != null )
			map.put("yellow_customer",yellow_customer);
		if ( ht_settlement_way != null )
			map.put("ht_settlement_way",ht_settlement_way);
		if ( bank_account_regi != null )
			map.put("bank_account_regi",bank_account_regi);
		if ( account_status != null )
			map.put("account_status",account_status);
		if ( examin_lock_flag != null )
			map.put("examin_lock_flag",examin_lock_flag);
		if ( mng_lock_flag != null )
			map.put("mng_lock_flag",mng_lock_flag);
		if ( mng_lock_off_start_date != null )
			map.put("mng_lock_off_start_date",mng_lock_off_start_date);
		if ( mng_lock_off_end_date != null )
			map.put("mng_lock_off_end_date",mng_lock_off_end_date);
		if ( mng_lock_flag_advance != null )
			map.put("mng_lock_flag_advance",mng_lock_flag_advance);
		if ( mng_lock_flag_unpay_margin != null )
			map.put("mng_lock_flag_unpay_margin",mng_lock_flag_unpay_margin);
		if ( mng_lock_flag_short_security != null )
			map.put("mng_lock_flag_short_security",mng_lock_flag_short_security);
		if ( mng_lock_flag_unsubstit_depo != null )
			map.put("mng_lock_flag_unsubstit_depo",mng_lock_flag_unsubstit_depo);
		if ( branch_lock != null )
			map.put("branch_lock",branch_lock);
		if ( order_permission != null )
			map.put("order_permission",order_permission);
		map.put("tax_type",tax_type);
		map.put("tax_type_next",tax_type_next);
		if ( margin_tax_type != null )
			map.put("margin_tax_type",margin_tax_type);
		if ( margin_tax_type_next != null )
			map.put("margin_tax_type_next",margin_tax_type_next);
		if ( qualified_inst_investor_div != null )
			map.put("qualified_inst_investor_div",qualified_inst_investor_div);
		if ( margin_sys_acc_open_div != null )
			map.put("margin_sys_acc_open_div",margin_sys_acc_open_div);
		if ( margin_gen_acc_open_div != null )
			map.put("margin_gen_acc_open_div",margin_gen_acc_open_div);
		if ( equity_sp_acc_open_date != null )
			map.put("equity_sp_acc_open_date",equity_sp_acc_open_date);
		if ( margin_sp_acc_open_date != null )
			map.put("margin_sp_acc_open_date",margin_sp_acc_open_date);
		if ( transfer_count != null )
			map.put("transfer_count",transfer_count);
		if ( foreign_sec_acc_open_div != null )
			map.put("foreign_sec_acc_open_div",foreign_sec_acc_open_div);
		if ( ifo_acc_open_div_tokyo != null )
			map.put("ifo_acc_open_div_tokyo",ifo_acc_open_div_tokyo);
		if ( ifo_acc_open_div_osaka != null )
			map.put("ifo_acc_open_div_osaka",ifo_acc_open_div_osaka);
		if ( ifo_acc_open_div_nagoya != null )
			map.put("ifo_acc_open_div_nagoya",ifo_acc_open_div_nagoya);
		if ( ruito_acc_open_div != null )
			map.put("ruito_acc_open_div",ruito_acc_open_div);
		if ( mrf_acc_open_div != null )
			map.put("mrf_acc_open_div",mrf_acc_open_div);
		if ( fx_acc_open_div != null )
			map.put("fx_acc_open_div",fx_acc_open_div);
		if ( feq_con_acc_open_div != null )
			map.put("feq_con_acc_open_div",feq_con_acc_open_div);
		if ( top_page_id != null )
			map.put("top_page_id",top_page_id);
		if ( quoto_type != null )
			map.put("quoto_type",quoto_type);
		map.put("trading_report_div",trading_report_div);
		map.put("position_report_div",position_report_div);
		map.put("position_report_cycle_div",position_report_cycle_div);
		map.put("certificate_deposit_flag",certificate_deposit_flag);
		map.put("account_statement_flag",account_statement_flag);
		map.put("email_last_updater",email_last_updater);
		map.put("email_last_updated_timestamp",email_last_updated_timestamp);
		map.put("trading_password_updater",trading_password_updater);
		map.put("tr_pwd_last_update_timestamp",tr_pwd_last_update_timestamp);
		map.put("mb_off_last_updater",mb_off_last_updater);
		map.put("mb_off_last_updated_timestamp",mb_off_last_updated_timestamp);
		map.put("enable_order_last_updater",enable_order_last_updater);
		map.put("enable_order_updated_timestamp",enable_order_updated_timestamp);
		if ( fx_acc_open_div_last_updater != null )
			map.put("fx_acc_open_div_last_updater",fx_acc_open_div_last_updater);
		if ( fx_acc_open_updated_timestamp != null )
			map.put("fx_acc_open_updated_timestamp",fx_acc_open_updated_timestamp);
		if ( feq_con_acc_open_div_updater != null )
			map.put("feq_con_acc_open_div_updater",feq_con_acc_open_div_updater);
		if ( feq_con_acc_open_timestamp != null )
			map.put("feq_con_acc_open_timestamp",feq_con_acc_open_timestamp);
		map.put("mrf_fund_code",mrf_fund_code);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		if ( sp_mng_acc_open_div != null )
			map.put("sp_mng_acc_open_div",sp_mng_acc_open_div);
		if ( lock_registration_reason != null )
			map.put("lock_registration_reason",lock_registration_reason);
		if ( inf_mail_flg_last_updater != null )
			map.put("inf_mail_flg_last_updater",inf_mail_flg_last_updater);
		if ( inf_mail_flg_updated_timestamp != null )
			map.put("inf_mail_flg_updated_timestamp",inf_mail_flg_updated_timestamp);
		if ( eq_exe_ml_flg_last_updater != null )
			map.put("eq_exe_ml_flg_last_updater",eq_exe_ml_flg_last_updater);
		if ( eq_exe_ml_flg_timestamp != null )
			map.put("eq_exe_ml_flg_timestamp",eq_exe_ml_flg_timestamp);
		if ( eq_unexe_ml_flg_last_updater != null )
			map.put("eq_unexe_ml_flg_last_updater",eq_unexe_ml_flg_last_updater);
		if ( eq_unexe_ml_flg_timestamp != null )
			map.put("eq_unexe_ml_flg_timestamp",eq_unexe_ml_flg_timestamp);
		if ( ifo_exe_ml_flg_last_updater != null )
			map.put("ifo_exe_ml_flg_last_updater",ifo_exe_ml_flg_last_updater);
		if ( ifo_exe_ml_flg_timestamp != null )
			map.put("ifo_exe_ml_flg_timestamp",ifo_exe_ml_flg_timestamp);
		if ( ifo_unexe_ml_flg_last_updater != null )
			map.put("ifo_unexe_ml_flg_last_updater",ifo_unexe_ml_flg_last_updater);
		if ( ifo_unexe_ml_flg_timestamp != null )
			map.put("ifo_unexe_ml_flg_timestamp",ifo_unexe_ml_flg_timestamp);
		if ( stock_option_acc_open_div_is_set )
			map.put("stock_option_acc_open_div",stock_option_acc_open_div);
		if ( secured_loan_sec_acc_open_div_is_set )
			map.put("secured_loan_sec_acc_open_div",secured_loan_sec_acc_open_div);
		if ( margin_acc_open_date != null )
			map.put("margin_acc_open_date",margin_acc_open_date);
		if ( ifo_acc_open_date != null )
			map.put("ifo_acc_open_date",ifo_acc_open_date);
		if ( only_mobile_open_div != null )
			map.put("only_mobile_open_div",only_mobile_open_div);
		if ( only_mbl_opn_div_last_updater != null )
			map.put("only_mbl_opn_div_last_updater",only_mbl_opn_div_last_updater);
		if ( only_mbl_opn_div_timestamp != null )
			map.put("only_mbl_opn_div_timestamp",only_mbl_opn_div_timestamp);
		if ( email_address_alt2 != null )
			map.put("email_address_alt2",email_address_alt2);
		if ( order_exe_flag != null )
			map.put("order_exe_flag",order_exe_flag);
		if ( order_unexe_flag != null )
			map.put("order_unexe_flag",order_unexe_flag);
		if ( important_connection_mail_flag != null )
			map.put("important_connection_mail_flag",important_connection_mail_flag);
		if ( information_mail2_flag != null )
			map.put("information_mail2_flag",information_mail2_flag);
		if ( proam_div != null )
			map.put("proam_div",proam_div);
		if ( org_deposit_div != null )
			map.put("org_deposit_div",org_deposit_div);
		if ( eq_hold_rep_div != null )
			map.put("eq_hold_rep_div",eq_hold_rep_div);
		if ( chkup_rep_div != null )
			map.put("chkup_rep_div",chkup_rep_div);
		if ( pts_acc_open_div != null )
			map.put("pts_acc_open_div",pts_acc_open_div);
		if ( pts_acc_open_div_last_updater != null )
			map.put("pts_acc_open_div_last_updater",pts_acc_open_div_last_updater);
		if ( pts_acc_open_div_timestamp != null )
			map.put("pts_acc_open_div_timestamp",pts_acc_open_div_timestamp);
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
		if ( cfd_acc_open_div != null )
			map.put("cfd_acc_open_div",cfd_acc_open_div);
		if ( cfd_acc_open_div_last_updater != null )
			map.put("cfd_acc_open_div_last_updater",cfd_acc_open_div_last_updater);
		if ( cfd_acc_open_updated_timestamp != null )
			map.put("cfd_acc_open_updated_timestamp",cfd_acc_open_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( institution_id_is_modified )
			map.put("institution_id",new Long(institution_id));
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( branch_id_is_modified )
			map.put("branch_id",new Long(branch_id));
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( sonar_trader_code_is_modified )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( account_type_is_modified )
			map.put("account_type",account_type);
		if ( family_name_is_modified )
			map.put("family_name",family_name);
		if ( middle_name_is_modified )
			map.put("middle_name",middle_name);
		if ( given_name_is_modified )
			map.put("given_name",given_name);
		if ( family_name_alt1_is_modified )
			map.put("family_name_alt1",family_name_alt1);
		if ( middle_name_alt1_is_modified )
			map.put("middle_name_alt1",middle_name_alt1);
		if ( given_name_alt1_is_modified )
			map.put("given_name_alt1",given_name_alt1);
		if ( family_name_alt2_is_modified )
			map.put("family_name_alt2",family_name_alt2);
		if ( middle_name_alt2_is_modified )
			map.put("middle_name_alt2",middle_name_alt2);
		if ( given_name_alt2_is_modified )
			map.put("given_name_alt2",given_name_alt2);
		if ( zip_code_is_modified )
			map.put("zip_code",zip_code);
		if ( sub_zip_code_is_modified )
			map.put("sub_zip_code",sub_zip_code);
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
		if ( telephone_is_modified )
			map.put("telephone",telephone);
		if ( mobile_is_modified )
			map.put("mobile",mobile);
		if ( fax_is_modified )
			map.put("fax",fax);
		if ( office_is_modified )
			map.put("office",office);
		if ( office_zip_code_is_modified )
			map.put("office_zip_code",office_zip_code);
		if ( office_address_is_modified )
			map.put("office_address",office_address);
		if ( office_telephone_is_modified )
			map.put("office_telephone",office_telephone);
		if ( post_is_modified )
			map.put("post",post);
		if ( equity_order_exe_mail_flag_is_modified )
			map.put("equity_order_exe_mail_flag",equity_order_exe_mail_flag);
		if ( equity_order_unexec_mail_flag_is_modified )
			map.put("equity_order_unexec_mail_flag",equity_order_unexec_mail_flag);
		if ( ifo_order_exec_mail_flag_is_modified )
			map.put("ifo_order_exec_mail_flag",ifo_order_exec_mail_flag);
		if ( ifo_order_unexec_mail_flag_is_modified )
			map.put("ifo_order_unexec_mail_flag",ifo_order_unexec_mail_flag);
		if ( information_mail_flag_is_modified )
			map.put("information_mail_flag",information_mail_flag);
		if ( resident_is_modified )
			map.put("resident",resident);
		if ( new_account_div_is_modified )
			map.put("new_account_div",new_account_div);
		if ( via_trust_bank_div_is_modified )
			map.put("via_trust_bank_div",via_trust_bank_div);
		if ( class_div_is_modified )
			map.put("class_div",class_div);
		if ( email_address_is_modified )
			map.put("email_address",email_address);
		if ( email_address_alt1_is_modified )
			map.put("email_address_alt1",email_address_alt1);
		if ( trading_password_is_modified )
			map.put("trading_password",trading_password);
		if ( account_open_date_is_modified )
			map.put("account_open_date",account_open_date);
		if ( account_close_date_is_modified )
			map.put("account_close_date",account_close_date);
		if ( acc_open_send_mail_status_is_modified )
			map.put("acc_open_send_mail_status",acc_open_send_mail_status);
		if ( person_identify_is_modified )
			map.put("person_identify",person_identify);
		if ( era_born_is_modified )
			map.put("era_born",era_born);
		if ( born_date_is_modified )
			map.put("born_date",born_date);
		if ( sex_is_modified )
			map.put("sex",sex);
		if ( yellow_customer_is_modified )
			map.put("yellow_customer",yellow_customer);
		if ( ht_settlement_way_is_modified )
			map.put("ht_settlement_way",ht_settlement_way);
		if ( bank_account_regi_is_modified )
			map.put("bank_account_regi",bank_account_regi);
		if ( account_status_is_modified )
			map.put("account_status",account_status);
		if ( examin_lock_flag_is_modified )
			map.put("examin_lock_flag",examin_lock_flag);
		if ( mng_lock_flag_is_modified )
			map.put("mng_lock_flag",mng_lock_flag);
		if ( mng_lock_off_start_date_is_modified )
			map.put("mng_lock_off_start_date",mng_lock_off_start_date);
		if ( mng_lock_off_end_date_is_modified )
			map.put("mng_lock_off_end_date",mng_lock_off_end_date);
		if ( mng_lock_flag_advance_is_modified )
			map.put("mng_lock_flag_advance",mng_lock_flag_advance);
		if ( mng_lock_flag_unpay_margin_is_modified )
			map.put("mng_lock_flag_unpay_margin",mng_lock_flag_unpay_margin);
		if ( mng_lock_flag_short_security_is_modified )
			map.put("mng_lock_flag_short_security",mng_lock_flag_short_security);
		if ( mng_lock_flag_unsubstit_depo_is_modified )
			map.put("mng_lock_flag_unsubstit_depo",mng_lock_flag_unsubstit_depo);
		if ( branch_lock_is_modified )
			map.put("branch_lock",branch_lock);
		if ( order_permission_is_modified )
			map.put("order_permission",order_permission);
		if ( tax_type_is_modified )
			map.put("tax_type",tax_type);
		if ( tax_type_next_is_modified )
			map.put("tax_type_next",tax_type_next);
		if ( margin_tax_type_is_modified )
			map.put("margin_tax_type",margin_tax_type);
		if ( margin_tax_type_next_is_modified )
			map.put("margin_tax_type_next",margin_tax_type_next);
		if ( qualified_inst_investor_div_is_modified )
			map.put("qualified_inst_investor_div",qualified_inst_investor_div);
		if ( margin_sys_acc_open_div_is_modified )
			map.put("margin_sys_acc_open_div",margin_sys_acc_open_div);
		if ( margin_gen_acc_open_div_is_modified )
			map.put("margin_gen_acc_open_div",margin_gen_acc_open_div);
		if ( equity_sp_acc_open_date_is_modified )
			map.put("equity_sp_acc_open_date",equity_sp_acc_open_date);
		if ( margin_sp_acc_open_date_is_modified )
			map.put("margin_sp_acc_open_date",margin_sp_acc_open_date);
		if ( transfer_count_is_modified )
			map.put("transfer_count",transfer_count);
		if ( foreign_sec_acc_open_div_is_modified )
			map.put("foreign_sec_acc_open_div",foreign_sec_acc_open_div);
		if ( ifo_acc_open_div_tokyo_is_modified )
			map.put("ifo_acc_open_div_tokyo",ifo_acc_open_div_tokyo);
		if ( ifo_acc_open_div_osaka_is_modified )
			map.put("ifo_acc_open_div_osaka",ifo_acc_open_div_osaka);
		if ( ifo_acc_open_div_nagoya_is_modified )
			map.put("ifo_acc_open_div_nagoya",ifo_acc_open_div_nagoya);
		if ( ruito_acc_open_div_is_modified )
			map.put("ruito_acc_open_div",ruito_acc_open_div);
		if ( mrf_acc_open_div_is_modified )
			map.put("mrf_acc_open_div",mrf_acc_open_div);
		if ( fx_acc_open_div_is_modified )
			map.put("fx_acc_open_div",fx_acc_open_div);
		if ( feq_con_acc_open_div_is_modified )
			map.put("feq_con_acc_open_div",feq_con_acc_open_div);
		if ( top_page_id_is_modified )
			map.put("top_page_id",top_page_id);
		if ( quoto_type_is_modified )
			map.put("quoto_type",quoto_type);
		if ( trading_report_div_is_modified )
			map.put("trading_report_div",trading_report_div);
		if ( position_report_div_is_modified )
			map.put("position_report_div",position_report_div);
		if ( position_report_cycle_div_is_modified )
			map.put("position_report_cycle_div",position_report_cycle_div);
		if ( certificate_deposit_flag_is_modified )
			map.put("certificate_deposit_flag",certificate_deposit_flag);
		if ( account_statement_flag_is_modified )
			map.put("account_statement_flag",account_statement_flag);
		if ( email_last_updater_is_modified )
			map.put("email_last_updater",email_last_updater);
		if ( email_last_updated_timestamp_is_modified )
			map.put("email_last_updated_timestamp",email_last_updated_timestamp);
		if ( trading_password_updater_is_modified )
			map.put("trading_password_updater",trading_password_updater);
		if ( tr_pwd_last_update_timestamp_is_modified )
			map.put("tr_pwd_last_update_timestamp",tr_pwd_last_update_timestamp);
		if ( mb_off_last_updater_is_modified )
			map.put("mb_off_last_updater",mb_off_last_updater);
		if ( mb_off_last_updated_timestamp_is_modified )
			map.put("mb_off_last_updated_timestamp",mb_off_last_updated_timestamp);
		if ( enable_order_last_updater_is_modified )
			map.put("enable_order_last_updater",enable_order_last_updater);
		if ( enable_order_updated_timestamp_is_modified )
			map.put("enable_order_updated_timestamp",enable_order_updated_timestamp);
		if ( fx_acc_open_div_last_updater_is_modified )
			map.put("fx_acc_open_div_last_updater",fx_acc_open_div_last_updater);
		if ( fx_acc_open_updated_timestamp_is_modified )
			map.put("fx_acc_open_updated_timestamp",fx_acc_open_updated_timestamp);
		if ( feq_con_acc_open_div_updater_is_modified )
			map.put("feq_con_acc_open_div_updater",feq_con_acc_open_div_updater);
		if ( feq_con_acc_open_timestamp_is_modified )
			map.put("feq_con_acc_open_timestamp",feq_con_acc_open_timestamp);
		if ( mrf_fund_code_is_modified )
			map.put("mrf_fund_code",mrf_fund_code);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( sp_mng_acc_open_div_is_modified )
			map.put("sp_mng_acc_open_div",sp_mng_acc_open_div);
		if ( lock_registration_reason_is_modified )
			map.put("lock_registration_reason",lock_registration_reason);
		if ( inf_mail_flg_last_updater_is_modified )
			map.put("inf_mail_flg_last_updater",inf_mail_flg_last_updater);
		if ( inf_mail_flg_updated_timestamp_is_modified )
			map.put("inf_mail_flg_updated_timestamp",inf_mail_flg_updated_timestamp);
		if ( eq_exe_ml_flg_last_updater_is_modified )
			map.put("eq_exe_ml_flg_last_updater",eq_exe_ml_flg_last_updater);
		if ( eq_exe_ml_flg_timestamp_is_modified )
			map.put("eq_exe_ml_flg_timestamp",eq_exe_ml_flg_timestamp);
		if ( eq_unexe_ml_flg_last_updater_is_modified )
			map.put("eq_unexe_ml_flg_last_updater",eq_unexe_ml_flg_last_updater);
		if ( eq_unexe_ml_flg_timestamp_is_modified )
			map.put("eq_unexe_ml_flg_timestamp",eq_unexe_ml_flg_timestamp);
		if ( ifo_exe_ml_flg_last_updater_is_modified )
			map.put("ifo_exe_ml_flg_last_updater",ifo_exe_ml_flg_last_updater);
		if ( ifo_exe_ml_flg_timestamp_is_modified )
			map.put("ifo_exe_ml_flg_timestamp",ifo_exe_ml_flg_timestamp);
		if ( ifo_unexe_ml_flg_last_updater_is_modified )
			map.put("ifo_unexe_ml_flg_last_updater",ifo_unexe_ml_flg_last_updater);
		if ( ifo_unexe_ml_flg_timestamp_is_modified )
			map.put("ifo_unexe_ml_flg_timestamp",ifo_unexe_ml_flg_timestamp);
		if ( stock_option_acc_open_div_is_modified )
			map.put("stock_option_acc_open_div",stock_option_acc_open_div);
		if ( secured_loan_sec_acc_open_div_is_modified )
			map.put("secured_loan_sec_acc_open_div",secured_loan_sec_acc_open_div);
		if ( margin_acc_open_date_is_modified )
			map.put("margin_acc_open_date",margin_acc_open_date);
		if ( ifo_acc_open_date_is_modified )
			map.put("ifo_acc_open_date",ifo_acc_open_date);
		if ( only_mobile_open_div_is_modified )
			map.put("only_mobile_open_div",only_mobile_open_div);
		if ( only_mbl_opn_div_last_updater_is_modified )
			map.put("only_mbl_opn_div_last_updater",only_mbl_opn_div_last_updater);
		if ( only_mbl_opn_div_timestamp_is_modified )
			map.put("only_mbl_opn_div_timestamp",only_mbl_opn_div_timestamp);
		if ( email_address_alt2_is_modified )
			map.put("email_address_alt2",email_address_alt2);
		if ( order_exe_flag_is_modified )
			map.put("order_exe_flag",order_exe_flag);
		if ( order_unexe_flag_is_modified )
			map.put("order_unexe_flag",order_unexe_flag);
		if ( important_connection_mail_flag_is_modified )
			map.put("important_connection_mail_flag",important_connection_mail_flag);
		if ( information_mail2_flag_is_modified )
			map.put("information_mail2_flag",information_mail2_flag);
		if ( proam_div_is_modified )
			map.put("proam_div",proam_div);
		if ( org_deposit_div_is_modified )
			map.put("org_deposit_div",org_deposit_div);
		if ( eq_hold_rep_div_is_modified )
			map.put("eq_hold_rep_div",eq_hold_rep_div);
		if ( chkup_rep_div_is_modified )
			map.put("chkup_rep_div",chkup_rep_div);
		if ( pts_acc_open_div_is_modified )
			map.put("pts_acc_open_div",pts_acc_open_div);
		if ( pts_acc_open_div_last_updater_is_modified )
			map.put("pts_acc_open_div_last_updater",pts_acc_open_div_last_updater);
		if ( pts_acc_open_div_timestamp_is_modified )
			map.put("pts_acc_open_div_timestamp",pts_acc_open_div_timestamp);
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
		if ( cfd_acc_open_div_is_modified )
			map.put("cfd_acc_open_div",cfd_acc_open_div);
		if ( cfd_acc_open_div_last_updater_is_modified )
			map.put("cfd_acc_open_div_last_updater",cfd_acc_open_div_last_updater);
		if ( cfd_acc_open_updated_timestamp_is_modified )
			map.put("cfd_acc_open_updated_timestamp",cfd_acc_open_updated_timestamp);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( institution_id_is_set )
				map.put("institution_id",new Long(institution_id));
			if ( account_code_is_set )
				map.put("account_code",account_code);
			if ( branch_id_is_set )
				map.put("branch_id",new Long(branch_id));
			map.put("branch_code",branch_code);
			map.put("sonar_trader_code",sonar_trader_code);
			if ( account_type_is_set )
				map.put("account_type",account_type);
			if ( family_name_is_set )
				map.put("family_name",family_name);
			map.put("middle_name",middle_name);
			map.put("given_name",given_name);
			if ( family_name_alt1_is_set )
				map.put("family_name_alt1",family_name_alt1);
			map.put("middle_name_alt1",middle_name_alt1);
			map.put("given_name_alt1",given_name_alt1);
			map.put("family_name_alt2",family_name_alt2);
			map.put("middle_name_alt2",middle_name_alt2);
			map.put("given_name_alt2",given_name_alt2);
			if ( zip_code_is_set )
				map.put("zip_code",zip_code);
			map.put("sub_zip_code",sub_zip_code);
			if ( address_line1_is_set )
				map.put("address_line1",address_line1);
			map.put("address_line2",address_line2);
			map.put("address_line3",address_line3);
			map.put("address_line1_kana",address_line1_kana);
			map.put("address_line2_kana",address_line2_kana);
			map.put("address_line3_kana",address_line3_kana);
			map.put("telephone",telephone);
			map.put("mobile",mobile);
			map.put("fax",fax);
			map.put("office",office);
			map.put("office_zip_code",office_zip_code);
			map.put("office_address",office_address);
			map.put("office_telephone",office_telephone);
			map.put("post",post);
			if ( equity_order_exe_mail_flag_is_set )
				map.put("equity_order_exe_mail_flag",equity_order_exe_mail_flag);
			if ( equity_order_unexec_mail_flag_is_set )
				map.put("equity_order_unexec_mail_flag",equity_order_unexec_mail_flag);
			if ( ifo_order_exec_mail_flag_is_set )
				map.put("ifo_order_exec_mail_flag",ifo_order_exec_mail_flag);
			if ( ifo_order_unexec_mail_flag_is_set )
				map.put("ifo_order_unexec_mail_flag",ifo_order_unexec_mail_flag);
			if ( information_mail_flag_is_set )
				map.put("information_mail_flag",information_mail_flag);
			map.put("resident",resident);
			map.put("new_account_div",new_account_div);
			map.put("via_trust_bank_div",via_trust_bank_div);
			map.put("class_div",class_div);
			map.put("email_address",email_address);
			map.put("email_address_alt1",email_address_alt1);
			if ( trading_password_is_set )
				map.put("trading_password",trading_password);
			map.put("account_open_date",account_open_date);
			map.put("account_close_date",account_close_date);
			map.put("acc_open_send_mail_status",acc_open_send_mail_status);
			map.put("person_identify",person_identify);
			map.put("era_born",era_born);
			map.put("born_date",born_date);
			map.put("sex",sex);
			map.put("yellow_customer",yellow_customer);
			map.put("ht_settlement_way",ht_settlement_way);
			map.put("bank_account_regi",bank_account_regi);
			map.put("account_status",account_status);
			map.put("examin_lock_flag",examin_lock_flag);
			map.put("mng_lock_flag",mng_lock_flag);
			map.put("mng_lock_off_start_date",mng_lock_off_start_date);
			map.put("mng_lock_off_end_date",mng_lock_off_end_date);
			map.put("mng_lock_flag_advance",mng_lock_flag_advance);
			map.put("mng_lock_flag_unpay_margin",mng_lock_flag_unpay_margin);
			map.put("mng_lock_flag_short_security",mng_lock_flag_short_security);
			map.put("mng_lock_flag_unsubstit_depo",mng_lock_flag_unsubstit_depo);
			map.put("branch_lock",branch_lock);
			map.put("order_permission",order_permission);
			if ( tax_type_is_set )
				map.put("tax_type",tax_type);
			if ( tax_type_next_is_set )
				map.put("tax_type_next",tax_type_next);
			map.put("margin_tax_type",margin_tax_type);
			map.put("margin_tax_type_next",margin_tax_type_next);
			map.put("qualified_inst_investor_div",qualified_inst_investor_div);
			map.put("margin_sys_acc_open_div",margin_sys_acc_open_div);
			map.put("margin_gen_acc_open_div",margin_gen_acc_open_div);
			map.put("equity_sp_acc_open_date",equity_sp_acc_open_date);
			map.put("margin_sp_acc_open_date",margin_sp_acc_open_date);
			map.put("transfer_count",transfer_count);
			map.put("foreign_sec_acc_open_div",foreign_sec_acc_open_div);
			map.put("ifo_acc_open_div_tokyo",ifo_acc_open_div_tokyo);
			map.put("ifo_acc_open_div_osaka",ifo_acc_open_div_osaka);
			map.put("ifo_acc_open_div_nagoya",ifo_acc_open_div_nagoya);
			map.put("ruito_acc_open_div",ruito_acc_open_div);
			map.put("mrf_acc_open_div",mrf_acc_open_div);
			map.put("fx_acc_open_div",fx_acc_open_div);
			map.put("feq_con_acc_open_div",feq_con_acc_open_div);
			map.put("top_page_id",top_page_id);
			map.put("quoto_type",quoto_type);
			if ( trading_report_div_is_set )
				map.put("trading_report_div",trading_report_div);
			if ( position_report_div_is_set )
				map.put("position_report_div",position_report_div);
			if ( position_report_cycle_div_is_set )
				map.put("position_report_cycle_div",position_report_cycle_div);
			if ( certificate_deposit_flag_is_set )
				map.put("certificate_deposit_flag",certificate_deposit_flag);
			if ( account_statement_flag_is_set )
				map.put("account_statement_flag",account_statement_flag);
			if ( email_last_updater_is_set )
				map.put("email_last_updater",email_last_updater);
			if ( email_last_updated_timestamp_is_set )
				map.put("email_last_updated_timestamp",email_last_updated_timestamp);
			if ( trading_password_updater_is_set )
				map.put("trading_password_updater",trading_password_updater);
			if ( tr_pwd_last_update_timestamp_is_set )
				map.put("tr_pwd_last_update_timestamp",tr_pwd_last_update_timestamp);
			if ( mb_off_last_updater_is_set )
				map.put("mb_off_last_updater",mb_off_last_updater);
			if ( mb_off_last_updated_timestamp_is_set )
				map.put("mb_off_last_updated_timestamp",mb_off_last_updated_timestamp);
			if ( enable_order_last_updater_is_set )
				map.put("enable_order_last_updater",enable_order_last_updater);
			if ( enable_order_updated_timestamp_is_set )
				map.put("enable_order_updated_timestamp",enable_order_updated_timestamp);
			map.put("fx_acc_open_div_last_updater",fx_acc_open_div_last_updater);
			map.put("fx_acc_open_updated_timestamp",fx_acc_open_updated_timestamp);
			map.put("feq_con_acc_open_div_updater",feq_con_acc_open_div_updater);
			map.put("feq_con_acc_open_timestamp",feq_con_acc_open_timestamp);
			if ( mrf_fund_code_is_set )
				map.put("mrf_fund_code",mrf_fund_code);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("sp_mng_acc_open_div",sp_mng_acc_open_div);
			map.put("lock_registration_reason",lock_registration_reason);
			map.put("inf_mail_flg_last_updater",inf_mail_flg_last_updater);
			map.put("inf_mail_flg_updated_timestamp",inf_mail_flg_updated_timestamp);
			map.put("eq_exe_ml_flg_last_updater",eq_exe_ml_flg_last_updater);
			map.put("eq_exe_ml_flg_timestamp",eq_exe_ml_flg_timestamp);
			map.put("eq_unexe_ml_flg_last_updater",eq_unexe_ml_flg_last_updater);
			map.put("eq_unexe_ml_flg_timestamp",eq_unexe_ml_flg_timestamp);
			map.put("ifo_exe_ml_flg_last_updater",ifo_exe_ml_flg_last_updater);
			map.put("ifo_exe_ml_flg_timestamp",ifo_exe_ml_flg_timestamp);
			map.put("ifo_unexe_ml_flg_last_updater",ifo_unexe_ml_flg_last_updater);
			map.put("ifo_unexe_ml_flg_timestamp",ifo_unexe_ml_flg_timestamp);
			if ( stock_option_acc_open_div_is_set )
				map.put("stock_option_acc_open_div",stock_option_acc_open_div);
			if ( secured_loan_sec_acc_open_div_is_set )
				map.put("secured_loan_sec_acc_open_div",secured_loan_sec_acc_open_div);
			map.put("margin_acc_open_date",margin_acc_open_date);
			map.put("ifo_acc_open_date",ifo_acc_open_date);
			map.put("only_mobile_open_div",only_mobile_open_div);
			map.put("only_mbl_opn_div_last_updater",only_mbl_opn_div_last_updater);
			map.put("only_mbl_opn_div_timestamp",only_mbl_opn_div_timestamp);
			map.put("email_address_alt2",email_address_alt2);
			map.put("order_exe_flag",order_exe_flag);
			map.put("order_unexe_flag",order_unexe_flag);
			map.put("important_connection_mail_flag",important_connection_mail_flag);
			map.put("information_mail2_flag",information_mail2_flag);
			map.put("proam_div",proam_div);
			map.put("org_deposit_div",org_deposit_div);
			map.put("eq_hold_rep_div",eq_hold_rep_div);
			map.put("chkup_rep_div",chkup_rep_div);
			map.put("pts_acc_open_div",pts_acc_open_div);
			map.put("pts_acc_open_div_last_updater",pts_acc_open_div_last_updater);
			map.put("pts_acc_open_div_timestamp",pts_acc_open_div_timestamp);
			map.put("broadcast_law",broadcast_law);
			map.put("aviation_law",aviation_law);
			map.put("ntt_law",ntt_law);
			map.put("dividend_trans_designate",dividend_trans_designate);
			map.put("standing_proxy",standing_proxy);
			map.put("statutory_agent",statutory_agent);
			map.put("affiliate_account_code",affiliate_account_code);
			map.put("agency_notify_cmp_div",agency_notify_cmp_div);
			map.put("cfd_acc_open_div",cfd_acc_open_div);
			map.put("cfd_acc_open_div_last_updater",cfd_acc_open_div_last_updater);
			map.put("cfd_acc_open_updated_timestamp",cfd_acc_open_updated_timestamp);
		}
		return map;
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
   * <em>institution_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getInstitutionId()
  {
    return institution_id;
  }


  /** 
   * <em>institution_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionIdIsSet() {
    return institution_id_is_set;
  }


  /** 
   * <em>institution_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionIdIsModified() {
    return institution_id_is_modified;
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
   * <em>branch_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBranchId()
  {
    return branch_id;
  }


  /** 
   * <em>branch_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsSet() {
    return branch_id_is_set;
  }


  /** 
   * <em>branch_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsModified() {
    return branch_id_is_modified;
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
   * <em>account_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum getAccountType()
  {
    return account_type;
  }


  /** 
   * <em>account_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountTypeIsSet() {
    return account_type_is_set;
  }


  /** 
   * <em>account_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountTypeIsModified() {
    return account_type_is_modified;
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
   * <em>middle_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMiddleName()
  {
    return middle_name;
  }


  /** 
   * <em>middle_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiddleNameIsSet() {
    return middle_name_is_set;
  }


  /** 
   * <em>middle_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiddleNameIsModified() {
    return middle_name_is_modified;
  }


  /** 
   * <em>given_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGivenName()
  {
    return given_name;
  }


  /** 
   * <em>given_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGivenNameIsSet() {
    return given_name_is_set;
  }


  /** 
   * <em>given_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGivenNameIsModified() {
    return given_name_is_modified;
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
   * <em>middle_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMiddleNameAlt1()
  {
    return middle_name_alt1;
  }


  /** 
   * <em>middle_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiddleNameAlt1IsSet() {
    return middle_name_alt1_is_set;
  }


  /** 
   * <em>middle_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiddleNameAlt1IsModified() {
    return middle_name_alt1_is_modified;
  }


  /** 
   * <em>given_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGivenNameAlt1()
  {
    return given_name_alt1;
  }


  /** 
   * <em>given_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGivenNameAlt1IsSet() {
    return given_name_alt1_is_set;
  }


  /** 
   * <em>given_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGivenNameAlt1IsModified() {
    return given_name_alt1_is_modified;
  }


  /** 
   * <em>family_name_alt2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFamilyNameAlt2()
  {
    return family_name_alt2;
  }


  /** 
   * <em>family_name_alt2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameAlt2IsSet() {
    return family_name_alt2_is_set;
  }


  /** 
   * <em>family_name_alt2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameAlt2IsModified() {
    return family_name_alt2_is_modified;
  }


  /** 
   * <em>middle_name_alt2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMiddleNameAlt2()
  {
    return middle_name_alt2;
  }


  /** 
   * <em>middle_name_alt2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiddleNameAlt2IsSet() {
    return middle_name_alt2_is_set;
  }


  /** 
   * <em>middle_name_alt2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiddleNameAlt2IsModified() {
    return middle_name_alt2_is_modified;
  }


  /** 
   * <em>given_name_alt2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGivenNameAlt2()
  {
    return given_name_alt2;
  }


  /** 
   * <em>given_name_alt2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGivenNameAlt2IsSet() {
    return given_name_alt2_is_set;
  }


  /** 
   * <em>given_name_alt2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGivenNameAlt2IsModified() {
    return given_name_alt2_is_modified;
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
   * <em>sub_zip_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSubZipCode()
  {
    return sub_zip_code;
  }


  /** 
   * <em>sub_zip_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubZipCodeIsSet() {
    return sub_zip_code_is_set;
  }


  /** 
   * <em>sub_zip_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubZipCodeIsModified() {
    return sub_zip_code_is_modified;
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
   * <em>mobile</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMobile()
  {
    return mobile;
  }


  /** 
   * <em>mobile</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMobileIsSet() {
    return mobile_is_set;
  }


  /** 
   * <em>mobile</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMobileIsModified() {
    return mobile_is_modified;
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
   * <em>office</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOffice()
  {
    return office;
  }


  /** 
   * <em>office</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfficeIsSet() {
    return office_is_set;
  }


  /** 
   * <em>office</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfficeIsModified() {
    return office_is_modified;
  }


  /** 
   * <em>office_zip_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOfficeZipCode()
  {
    return office_zip_code;
  }


  /** 
   * <em>office_zip_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfficeZipCodeIsSet() {
    return office_zip_code_is_set;
  }


  /** 
   * <em>office_zip_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfficeZipCodeIsModified() {
    return office_zip_code_is_modified;
  }


  /** 
   * <em>office_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOfficeAddress()
  {
    return office_address;
  }


  /** 
   * <em>office_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfficeAddressIsSet() {
    return office_address_is_set;
  }


  /** 
   * <em>office_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfficeAddressIsModified() {
    return office_address_is_modified;
  }


  /** 
   * <em>office_telephone</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOfficeTelephone()
  {
    return office_telephone;
  }


  /** 
   * <em>office_telephone</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfficeTelephoneIsSet() {
    return office_telephone_is_set;
  }


  /** 
   * <em>office_telephone</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfficeTelephoneIsModified() {
    return office_telephone_is_modified;
  }


  /** 
   * <em>post</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPost()
  {
    return post;
  }


  /** 
   * <em>post</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPostIsSet() {
    return post_is_set;
  }


  /** 
   * <em>post</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPostIsModified() {
    return post_is_modified;
  }


  /** 
   * <em>equity_order_exe_mail_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getEquityOrderExeMailFlag()
  {
    return equity_order_exe_mail_flag;
  }


  /** 
   * <em>equity_order_exe_mail_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityOrderExeMailFlagIsSet() {
    return equity_order_exe_mail_flag_is_set;
  }


  /** 
   * <em>equity_order_exe_mail_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityOrderExeMailFlagIsModified() {
    return equity_order_exe_mail_flag_is_modified;
  }


  /** 
   * <em>equity_order_unexec_mail_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getEquityOrderUnexecMailFlag()
  {
    return equity_order_unexec_mail_flag;
  }


  /** 
   * <em>equity_order_unexec_mail_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityOrderUnexecMailFlagIsSet() {
    return equity_order_unexec_mail_flag_is_set;
  }


  /** 
   * <em>equity_order_unexec_mail_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityOrderUnexecMailFlagIsModified() {
    return equity_order_unexec_mail_flag_is_modified;
  }


  /** 
   * <em>ifo_order_exec_mail_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getIfoOrderExecMailFlag()
  {
    return ifo_order_exec_mail_flag;
  }


  /** 
   * <em>ifo_order_exec_mail_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoOrderExecMailFlagIsSet() {
    return ifo_order_exec_mail_flag_is_set;
  }


  /** 
   * <em>ifo_order_exec_mail_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoOrderExecMailFlagIsModified() {
    return ifo_order_exec_mail_flag_is_modified;
  }


  /** 
   * <em>ifo_order_unexec_mail_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getIfoOrderUnexecMailFlag()
  {
    return ifo_order_unexec_mail_flag;
  }


  /** 
   * <em>ifo_order_unexec_mail_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoOrderUnexecMailFlagIsSet() {
    return ifo_order_unexec_mail_flag_is_set;
  }


  /** 
   * <em>ifo_order_unexec_mail_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoOrderUnexecMailFlagIsModified() {
    return ifo_order_unexec_mail_flag_is_modified;
  }


  /** 
   * <em>information_mail_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInformationMailFlag()
  {
    return information_mail_flag;
  }


  /** 
   * <em>information_mail_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInformationMailFlagIsSet() {
    return information_mail_flag_is_set;
  }


  /** 
   * <em>information_mail_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInformationMailFlagIsModified() {
    return information_mail_flag_is_modified;
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
   * <em>email_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEmailAddress()
  {
    return email_address;
  }


  /** 
   * <em>email_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailAddressIsSet() {
    return email_address_is_set;
  }


  /** 
   * <em>email_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailAddressIsModified() {
    return email_address_is_modified;
  }


  /** 
   * <em>email_address_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEmailAddressAlt1()
  {
    return email_address_alt1;
  }


  /** 
   * <em>email_address_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailAddressAlt1IsSet() {
    return email_address_alt1_is_set;
  }


  /** 
   * <em>email_address_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailAddressAlt1IsModified() {
    return email_address_alt1_is_modified;
  }


  /** 
   * <em>trading_password</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradingPassword()
  {
    return trading_password;
  }


  /** 
   * <em>trading_password</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingPasswordIsSet() {
    return trading_password_is_set;
  }


  /** 
   * <em>trading_password</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingPasswordIsModified() {
    return trading_password_is_modified;
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
   * <em>account_close_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getAccountCloseDate()
  {
    return account_close_date;
  }


  /** 
   * <em>account_close_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCloseDateIsSet() {
    return account_close_date_is_set;
  }


  /** 
   * <em>account_close_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCloseDateIsModified() {
    return account_close_date_is_modified;
  }


  /** 
   * <em>acc_open_send_mail_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccOpenSendMailStatus()
  {
    return acc_open_send_mail_status;
  }


  /** 
   * <em>acc_open_send_mail_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccOpenSendMailStatusIsSet() {
    return acc_open_send_mail_status_is_set;
  }


  /** 
   * <em>acc_open_send_mail_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccOpenSendMailStatusIsModified() {
    return acc_open_send_mail_status_is_modified;
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
   * <em>bank_account_regi</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBankAccountRegi()
  {
    return bank_account_regi;
  }


  /** 
   * <em>bank_account_regi</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankAccountRegiIsSet() {
    return bank_account_regi_is_set;
  }


  /** 
   * <em>bank_account_regi</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBankAccountRegiIsModified() {
    return bank_account_regi_is_modified;
  }


  /** 
   * <em>account_status</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum getAccountStatus()
  {
    return account_status;
  }


  /** 
   * <em>account_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountStatusIsSet() {
    return account_status_is_set;
  }


  /** 
   * <em>account_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountStatusIsModified() {
    return account_status_is_modified;
  }


  /** 
   * <em>examin_lock_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExaminLockFlag()
  {
    return examin_lock_flag;
  }


  /** 
   * <em>examin_lock_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExaminLockFlagIsSet() {
    return examin_lock_flag_is_set;
  }


  /** 
   * <em>examin_lock_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExaminLockFlagIsModified() {
    return examin_lock_flag_is_modified;
  }


  /** 
   * <em>mng_lock_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMngLockFlag()
  {
    return mng_lock_flag;
  }


  /** 
   * <em>mng_lock_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockFlagIsSet() {
    return mng_lock_flag_is_set;
  }


  /** 
   * <em>mng_lock_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockFlagIsModified() {
    return mng_lock_flag_is_modified;
  }


  /** 
   * <em>mng_lock_off_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getMngLockOffStartDate()
  {
    return mng_lock_off_start_date;
  }


  /** 
   * <em>mng_lock_off_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockOffStartDateIsSet() {
    return mng_lock_off_start_date_is_set;
  }


  /** 
   * <em>mng_lock_off_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockOffStartDateIsModified() {
    return mng_lock_off_start_date_is_modified;
  }


  /** 
   * <em>mng_lock_off_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getMngLockOffEndDate()
  {
    return mng_lock_off_end_date;
  }


  /** 
   * <em>mng_lock_off_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockOffEndDateIsSet() {
    return mng_lock_off_end_date_is_set;
  }


  /** 
   * <em>mng_lock_off_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockOffEndDateIsModified() {
    return mng_lock_off_end_date_is_modified;
  }


  /** 
   * <em>mng_lock_flag_advance</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMngLockFlagAdvance()
  {
    return mng_lock_flag_advance;
  }


  /** 
   * <em>mng_lock_flag_advance</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockFlagAdvanceIsSet() {
    return mng_lock_flag_advance_is_set;
  }


  /** 
   * <em>mng_lock_flag_advance</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockFlagAdvanceIsModified() {
    return mng_lock_flag_advance_is_modified;
  }


  /** 
   * <em>mng_lock_flag_unpay_margin</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMngLockFlagUnpayMargin()
  {
    return mng_lock_flag_unpay_margin;
  }


  /** 
   * <em>mng_lock_flag_unpay_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockFlagUnpayMarginIsSet() {
    return mng_lock_flag_unpay_margin_is_set;
  }


  /** 
   * <em>mng_lock_flag_unpay_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockFlagUnpayMarginIsModified() {
    return mng_lock_flag_unpay_margin_is_modified;
  }


  /** 
   * <em>mng_lock_flag_short_security</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMngLockFlagShortSecurity()
  {
    return mng_lock_flag_short_security;
  }


  /** 
   * <em>mng_lock_flag_short_security</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockFlagShortSecurityIsSet() {
    return mng_lock_flag_short_security_is_set;
  }


  /** 
   * <em>mng_lock_flag_short_security</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockFlagShortSecurityIsModified() {
    return mng_lock_flag_short_security_is_modified;
  }


  /** 
   * <em>mng_lock_flag_unsubstit_depo</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMngLockFlagUnsubstitDepo()
  {
    return mng_lock_flag_unsubstit_depo;
  }


  /** 
   * <em>mng_lock_flag_unsubstit_depo</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockFlagUnsubstitDepoIsSet() {
    return mng_lock_flag_unsubstit_depo_is_set;
  }


  /** 
   * <em>mng_lock_flag_unsubstit_depo</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMngLockFlagUnsubstitDepoIsModified() {
    return mng_lock_flag_unsubstit_depo_is_modified;
  }


  /** 
   * <em>branch_lock</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchLock()
  {
    return branch_lock;
  }


  /** 
   * <em>branch_lock</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchLockIsSet() {
    return branch_lock_is_set;
  }


  /** 
   * <em>branch_lock</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchLockIsModified() {
    return branch_lock_is_modified;
  }


  /** 
   * <em>order_permission</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderPermission()
  {
    return order_permission;
  }


  /** 
   * <em>order_permission</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderPermissionIsSet() {
    return order_permission_is_set;
  }


  /** 
   * <em>order_permission</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderPermissionIsModified() {
    return order_permission_is_modified;
  }


  /** 
   * <em>tax_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getTaxType()
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
   * <em>tax_type_next</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getTaxTypeNext()
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
   * <em>margin_tax_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getMarginTaxType()
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
   * <em>margin_tax_type_next</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getMarginTaxTypeNext()
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
   * <em>margin_sys_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginSysAccOpenDiv()
  {
    return margin_sys_acc_open_div;
  }


  /** 
   * <em>margin_sys_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSysAccOpenDivIsSet() {
    return margin_sys_acc_open_div_is_set;
  }


  /** 
   * <em>margin_sys_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSysAccOpenDivIsModified() {
    return margin_sys_acc_open_div_is_modified;
  }


  /** 
   * <em>margin_gen_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginGenAccOpenDiv()
  {
    return margin_gen_acc_open_div;
  }


  /** 
   * <em>margin_gen_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginGenAccOpenDivIsSet() {
    return margin_gen_acc_open_div_is_set;
  }


  /** 
   * <em>margin_gen_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginGenAccOpenDivIsModified() {
    return margin_gen_acc_open_div_is_modified;
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
   * <em>transfer_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getTransferCount()
  {
    return ( transfer_count==null? ((int)0): transfer_count.intValue() );
  }


  /** 
   * <em>transfer_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTransferCountIsNull()
  {
    return transfer_count == null;
  }


  /** 
   * <em>transfer_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferCountIsSet() {
    return transfer_count_is_set;
  }


  /** 
   * <em>transfer_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferCountIsModified() {
    return transfer_count_is_modified;
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
   * <em>fx_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFxAccOpenDiv()
  {
    return fx_acc_open_div;
  }


  /** 
   * <em>fx_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxAccOpenDivIsSet() {
    return fx_acc_open_div_is_set;
  }


  /** 
   * <em>fx_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxAccOpenDivIsModified() {
    return fx_acc_open_div_is_modified;
  }


  /** 
   * <em>feq_con_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFeqConAccOpenDiv()
  {
    return feq_con_acc_open_div;
  }


  /** 
   * <em>feq_con_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFeqConAccOpenDivIsSet() {
    return feq_con_acc_open_div_is_set;
  }


  /** 
   * <em>feq_con_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFeqConAccOpenDivIsModified() {
    return feq_con_acc_open_div_is_modified;
  }


  /** 
   * <em>top_page_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTopPageId()
  {
    return top_page_id;
  }


  /** 
   * <em>top_page_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTopPageIdIsSet() {
    return top_page_id_is_set;
  }


  /** 
   * <em>top_page_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTopPageIdIsModified() {
    return top_page_id_is_modified;
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
   * <em>certificate_deposit_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCertificateDepositFlag()
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
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getAccountStatementFlag()
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
   * <em>email_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEmailLastUpdater()
  {
    return email_last_updater;
  }


  /** 
   * <em>email_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailLastUpdaterIsSet() {
    return email_last_updater_is_set;
  }


  /** 
   * <em>email_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailLastUpdaterIsModified() {
    return email_last_updater_is_modified;
  }


  /** 
   * <em>email_last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getEmailLastUpdatedTimestamp()
  {
    return email_last_updated_timestamp;
  }


  /** 
   * <em>email_last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailLastUpdatedTimestampIsSet() {
    return email_last_updated_timestamp_is_set;
  }


  /** 
   * <em>email_last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailLastUpdatedTimestampIsModified() {
    return email_last_updated_timestamp_is_modified;
  }


  /** 
   * <em>trading_password_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradingPasswordUpdater()
  {
    return trading_password_updater;
  }


  /** 
   * <em>trading_password_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingPasswordUpdaterIsSet() {
    return trading_password_updater_is_set;
  }


  /** 
   * <em>trading_password_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingPasswordUpdaterIsModified() {
    return trading_password_updater_is_modified;
  }


  /** 
   * <em>tr_pwd_last_update_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTrPwdLastUpdateTimestamp()
  {
    return tr_pwd_last_update_timestamp;
  }


  /** 
   * <em>tr_pwd_last_update_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrPwdLastUpdateTimestampIsSet() {
    return tr_pwd_last_update_timestamp_is_set;
  }


  /** 
   * <em>tr_pwd_last_update_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrPwdLastUpdateTimestampIsModified() {
    return tr_pwd_last_update_timestamp_is_modified;
  }


  /** 
   * <em>mb_off_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMbOffLastUpdater()
  {
    return mb_off_last_updater;
  }


  /** 
   * <em>mb_off_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMbOffLastUpdaterIsSet() {
    return mb_off_last_updater_is_set;
  }


  /** 
   * <em>mb_off_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMbOffLastUpdaterIsModified() {
    return mb_off_last_updater_is_modified;
  }


  /** 
   * <em>mb_off_last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getMbOffLastUpdatedTimestamp()
  {
    return mb_off_last_updated_timestamp;
  }


  /** 
   * <em>mb_off_last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMbOffLastUpdatedTimestampIsSet() {
    return mb_off_last_updated_timestamp_is_set;
  }


  /** 
   * <em>mb_off_last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMbOffLastUpdatedTimestampIsModified() {
    return mb_off_last_updated_timestamp_is_modified;
  }


  /** 
   * <em>enable_order_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEnableOrderLastUpdater()
  {
    return enable_order_last_updater;
  }


  /** 
   * <em>enable_order_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEnableOrderLastUpdaterIsSet() {
    return enable_order_last_updater_is_set;
  }


  /** 
   * <em>enable_order_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEnableOrderLastUpdaterIsModified() {
    return enable_order_last_updater_is_modified;
  }


  /** 
   * <em>enable_order_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getEnableOrderUpdatedTimestamp()
  {
    return enable_order_updated_timestamp;
  }


  /** 
   * <em>enable_order_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEnableOrderUpdatedTimestampIsSet() {
    return enable_order_updated_timestamp_is_set;
  }


  /** 
   * <em>enable_order_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEnableOrderUpdatedTimestampIsModified() {
    return enable_order_updated_timestamp_is_modified;
  }


  /** 
   * <em>fx_acc_open_div_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFxAccOpenDivLastUpdater()
  {
    return fx_acc_open_div_last_updater;
  }


  /** 
   * <em>fx_acc_open_div_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxAccOpenDivLastUpdaterIsSet() {
    return fx_acc_open_div_last_updater_is_set;
  }


  /** 
   * <em>fx_acc_open_div_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxAccOpenDivLastUpdaterIsModified() {
    return fx_acc_open_div_last_updater_is_modified;
  }


  /** 
   * <em>fx_acc_open_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getFxAccOpenUpdatedTimestamp()
  {
    return fx_acc_open_updated_timestamp;
  }


  /** 
   * <em>fx_acc_open_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxAccOpenUpdatedTimestampIsSet() {
    return fx_acc_open_updated_timestamp_is_set;
  }


  /** 
   * <em>fx_acc_open_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFxAccOpenUpdatedTimestampIsModified() {
    return fx_acc_open_updated_timestamp_is_modified;
  }


  /** 
   * <em>feq_con_acc_open_div_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFeqConAccOpenDivUpdater()
  {
    return feq_con_acc_open_div_updater;
  }


  /** 
   * <em>feq_con_acc_open_div_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFeqConAccOpenDivUpdaterIsSet() {
    return feq_con_acc_open_div_updater_is_set;
  }


  /** 
   * <em>feq_con_acc_open_div_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFeqConAccOpenDivUpdaterIsModified() {
    return feq_con_acc_open_div_updater_is_modified;
  }


  /** 
   * <em>feq_con_acc_open_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getFeqConAccOpenTimestamp()
  {
    return feq_con_acc_open_timestamp;
  }


  /** 
   * <em>feq_con_acc_open_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFeqConAccOpenTimestampIsSet() {
    return feq_con_acc_open_timestamp_is_set;
  }


  /** 
   * <em>feq_con_acc_open_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFeqConAccOpenTimestampIsModified() {
    return feq_con_acc_open_timestamp_is_modified;
  }


  /** 
   * <em>mrf_fund_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMrfFundCode()
  {
    return mrf_fund_code;
  }


  /** 
   * <em>mrf_fund_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMrfFundCodeIsSet() {
    return mrf_fund_code_is_set;
  }


  /** 
   * <em>mrf_fund_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMrfFundCodeIsModified() {
    return mrf_fund_code_is_modified;
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
   * <em>lock_registration_reason</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLockRegistrationReason()
  {
    return lock_registration_reason;
  }


  /** 
   * <em>lock_registration_reason</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLockRegistrationReasonIsSet() {
    return lock_registration_reason_is_set;
  }


  /** 
   * <em>lock_registration_reason</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLockRegistrationReasonIsModified() {
    return lock_registration_reason_is_modified;
  }


  /** 
   * <em>inf_mail_flg_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInfMailFlgLastUpdater()
  {
    return inf_mail_flg_last_updater;
  }


  /** 
   * <em>inf_mail_flg_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInfMailFlgLastUpdaterIsSet() {
    return inf_mail_flg_last_updater_is_set;
  }


  /** 
   * <em>inf_mail_flg_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInfMailFlgLastUpdaterIsModified() {
    return inf_mail_flg_last_updater_is_modified;
  }


  /** 
   * <em>inf_mail_flg_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getInfMailFlgUpdatedTimestamp()
  {
    return inf_mail_flg_updated_timestamp;
  }


  /** 
   * <em>inf_mail_flg_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInfMailFlgUpdatedTimestampIsSet() {
    return inf_mail_flg_updated_timestamp_is_set;
  }


  /** 
   * <em>inf_mail_flg_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInfMailFlgUpdatedTimestampIsModified() {
    return inf_mail_flg_updated_timestamp_is_modified;
  }


  /** 
   * <em>eq_exe_ml_flg_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEqExeMlFlgLastUpdater()
  {
    return eq_exe_ml_flg_last_updater;
  }


  /** 
   * <em>eq_exe_ml_flg_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEqExeMlFlgLastUpdaterIsSet() {
    return eq_exe_ml_flg_last_updater_is_set;
  }


  /** 
   * <em>eq_exe_ml_flg_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEqExeMlFlgLastUpdaterIsModified() {
    return eq_exe_ml_flg_last_updater_is_modified;
  }


  /** 
   * <em>eq_exe_ml_flg_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getEqExeMlFlgTimestamp()
  {
    return eq_exe_ml_flg_timestamp;
  }


  /** 
   * <em>eq_exe_ml_flg_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEqExeMlFlgTimestampIsSet() {
    return eq_exe_ml_flg_timestamp_is_set;
  }


  /** 
   * <em>eq_exe_ml_flg_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEqExeMlFlgTimestampIsModified() {
    return eq_exe_ml_flg_timestamp_is_modified;
  }


  /** 
   * <em>eq_unexe_ml_flg_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEqUnexeMlFlgLastUpdater()
  {
    return eq_unexe_ml_flg_last_updater;
  }


  /** 
   * <em>eq_unexe_ml_flg_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEqUnexeMlFlgLastUpdaterIsSet() {
    return eq_unexe_ml_flg_last_updater_is_set;
  }


  /** 
   * <em>eq_unexe_ml_flg_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEqUnexeMlFlgLastUpdaterIsModified() {
    return eq_unexe_ml_flg_last_updater_is_modified;
  }


  /** 
   * <em>eq_unexe_ml_flg_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getEqUnexeMlFlgTimestamp()
  {
    return eq_unexe_ml_flg_timestamp;
  }


  /** 
   * <em>eq_unexe_ml_flg_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEqUnexeMlFlgTimestampIsSet() {
    return eq_unexe_ml_flg_timestamp_is_set;
  }


  /** 
   * <em>eq_unexe_ml_flg_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEqUnexeMlFlgTimestampIsModified() {
    return eq_unexe_ml_flg_timestamp_is_modified;
  }


  /** 
   * <em>ifo_exe_ml_flg_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoExeMlFlgLastUpdater()
  {
    return ifo_exe_ml_flg_last_updater;
  }


  /** 
   * <em>ifo_exe_ml_flg_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoExeMlFlgLastUpdaterIsSet() {
    return ifo_exe_ml_flg_last_updater_is_set;
  }


  /** 
   * <em>ifo_exe_ml_flg_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoExeMlFlgLastUpdaterIsModified() {
    return ifo_exe_ml_flg_last_updater_is_modified;
  }


  /** 
   * <em>ifo_exe_ml_flg_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getIfoExeMlFlgTimestamp()
  {
    return ifo_exe_ml_flg_timestamp;
  }


  /** 
   * <em>ifo_exe_ml_flg_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoExeMlFlgTimestampIsSet() {
    return ifo_exe_ml_flg_timestamp_is_set;
  }


  /** 
   * <em>ifo_exe_ml_flg_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoExeMlFlgTimestampIsModified() {
    return ifo_exe_ml_flg_timestamp_is_modified;
  }


  /** 
   * <em>ifo_unexe_ml_flg_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoUnexeMlFlgLastUpdater()
  {
    return ifo_unexe_ml_flg_last_updater;
  }


  /** 
   * <em>ifo_unexe_ml_flg_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoUnexeMlFlgLastUpdaterIsSet() {
    return ifo_unexe_ml_flg_last_updater_is_set;
  }


  /** 
   * <em>ifo_unexe_ml_flg_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoUnexeMlFlgLastUpdaterIsModified() {
    return ifo_unexe_ml_flg_last_updater_is_modified;
  }


  /** 
   * <em>ifo_unexe_ml_flg_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getIfoUnexeMlFlgTimestamp()
  {
    return ifo_unexe_ml_flg_timestamp;
  }


  /** 
   * <em>ifo_unexe_ml_flg_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoUnexeMlFlgTimestampIsSet() {
    return ifo_unexe_ml_flg_timestamp_is_set;
  }


  /** 
   * <em>ifo_unexe_ml_flg_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoUnexeMlFlgTimestampIsModified() {
    return ifo_unexe_ml_flg_timestamp_is_modified;
  }


  /** 
   * <em>stock_option_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStockOptionAccOpenDiv()
  {
    return stock_option_acc_open_div;
  }


  /** 
   * <em>stock_option_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStockOptionAccOpenDivIsSet() {
    return stock_option_acc_open_div_is_set;
  }


  /** 
   * <em>stock_option_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStockOptionAccOpenDivIsModified() {
    return stock_option_acc_open_div_is_modified;
  }


  /** 
   * <em>secured_loan_sec_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSecuredLoanSecAccOpenDiv()
  {
    return secured_loan_sec_acc_open_div;
  }


  /** 
   * <em>secured_loan_sec_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecuredLoanSecAccOpenDivIsSet() {
    return secured_loan_sec_acc_open_div_is_set;
  }


  /** 
   * <em>secured_loan_sec_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecuredLoanSecAccOpenDivIsModified() {
    return secured_loan_sec_acc_open_div_is_modified;
  }


  /** 
   * <em>margin_acc_open_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getMarginAccOpenDate()
  {
    return margin_acc_open_date;
  }


  /** 
   * <em>margin_acc_open_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginAccOpenDateIsSet() {
    return margin_acc_open_date_is_set;
  }


  /** 
   * <em>margin_acc_open_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginAccOpenDateIsModified() {
    return margin_acc_open_date_is_modified;
  }


  /** 
   * <em>ifo_acc_open_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getIfoAccOpenDate()
  {
    return ifo_acc_open_date;
  }


  /** 
   * <em>ifo_acc_open_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoAccOpenDateIsSet() {
    return ifo_acc_open_date_is_set;
  }


  /** 
   * <em>ifo_acc_open_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoAccOpenDateIsModified() {
    return ifo_acc_open_date_is_modified;
  }


  /** 
   * <em>only_mobile_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOnlyMobileOpenDiv()
  {
    return only_mobile_open_div;
  }


  /** 
   * <em>only_mobile_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlyMobileOpenDivIsSet() {
    return only_mobile_open_div_is_set;
  }


  /** 
   * <em>only_mobile_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlyMobileOpenDivIsModified() {
    return only_mobile_open_div_is_modified;
  }


  /** 
   * <em>only_mbl_opn_div_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOnlyMblOpnDivLastUpdater()
  {
    return only_mbl_opn_div_last_updater;
  }


  /** 
   * <em>only_mbl_opn_div_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlyMblOpnDivLastUpdaterIsSet() {
    return only_mbl_opn_div_last_updater_is_set;
  }


  /** 
   * <em>only_mbl_opn_div_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlyMblOpnDivLastUpdaterIsModified() {
    return only_mbl_opn_div_last_updater_is_modified;
  }


  /** 
   * <em>only_mbl_opn_div_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOnlyMblOpnDivTimestamp()
  {
    return only_mbl_opn_div_timestamp;
  }


  /** 
   * <em>only_mbl_opn_div_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlyMblOpnDivTimestampIsSet() {
    return only_mbl_opn_div_timestamp_is_set;
  }


  /** 
   * <em>only_mbl_opn_div_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlyMblOpnDivTimestampIsModified() {
    return only_mbl_opn_div_timestamp_is_modified;
  }


  /** 
   * <em>email_address_alt2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEmailAddressAlt2()
  {
    return email_address_alt2;
  }


  /** 
   * <em>email_address_alt2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailAddressAlt2IsSet() {
    return email_address_alt2_is_set;
  }


  /** 
   * <em>email_address_alt2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEmailAddressAlt2IsModified() {
    return email_address_alt2_is_modified;
  }


  /** 
   * <em>order_exe_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getOrderExeFlag()
  {
    return ( order_exe_flag==null? ((int)0): order_exe_flag.intValue() );
  }


  /** 
   * <em>order_exe_flag</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOrderExeFlagIsNull()
  {
    return order_exe_flag == null;
  }


  /** 
   * <em>order_exe_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderExeFlagIsSet() {
    return order_exe_flag_is_set;
  }


  /** 
   * <em>order_exe_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderExeFlagIsModified() {
    return order_exe_flag_is_modified;
  }


  /** 
   * <em>order_unexe_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getOrderUnexeFlag()
  {
    return ( order_unexe_flag==null? ((int)0): order_unexe_flag.intValue() );
  }


  /** 
   * <em>order_unexe_flag</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOrderUnexeFlagIsNull()
  {
    return order_unexe_flag == null;
  }


  /** 
   * <em>order_unexe_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderUnexeFlagIsSet() {
    return order_unexe_flag_is_set;
  }


  /** 
   * <em>order_unexe_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderUnexeFlagIsModified() {
    return order_unexe_flag_is_modified;
  }


  /** 
   * <em>important_connection_mail_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getImportantConnectionMailFlag()
  {
    return ( important_connection_mail_flag==null? ((int)0): important_connection_mail_flag.intValue() );
  }


  /** 
   * <em>important_connection_mail_flag</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getImportantConnectionMailFlagIsNull()
  {
    return important_connection_mail_flag == null;
  }


  /** 
   * <em>important_connection_mail_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getImportantConnectionMailFlagIsSet() {
    return important_connection_mail_flag_is_set;
  }


  /** 
   * <em>important_connection_mail_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getImportantConnectionMailFlagIsModified() {
    return important_connection_mail_flag_is_modified;
  }


  /** 
   * <em>information_mail2_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getInformationMail2Flag()
  {
    return ( information_mail2_flag==null? ((int)0): information_mail2_flag.intValue() );
  }


  /** 
   * <em>information_mail2_flag</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getInformationMail2FlagIsNull()
  {
    return information_mail2_flag == null;
  }


  /** 
   * <em>information_mail2_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInformationMail2FlagIsSet() {
    return information_mail2_flag_is_set;
  }


  /** 
   * <em>information_mail2_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInformationMail2FlagIsModified() {
    return information_mail2_flag_is_modified;
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
   * <em>pts_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPtsAccOpenDiv()
  {
    return pts_acc_open_div;
  }


  /** 
   * <em>pts_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPtsAccOpenDivIsSet() {
    return pts_acc_open_div_is_set;
  }


  /** 
   * <em>pts_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPtsAccOpenDivIsModified() {
    return pts_acc_open_div_is_modified;
  }


  /** 
   * <em>pts_acc_open_div_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPtsAccOpenDivLastUpdater()
  {
    return pts_acc_open_div_last_updater;
  }


  /** 
   * <em>pts_acc_open_div_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPtsAccOpenDivLastUpdaterIsSet() {
    return pts_acc_open_div_last_updater_is_set;
  }


  /** 
   * <em>pts_acc_open_div_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPtsAccOpenDivLastUpdaterIsModified() {
    return pts_acc_open_div_last_updater_is_modified;
  }


  /** 
   * <em>pts_acc_open_div_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getPtsAccOpenDivTimestamp()
  {
    return pts_acc_open_div_timestamp;
  }


  /** 
   * <em>pts_acc_open_div_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPtsAccOpenDivTimestampIsSet() {
    return pts_acc_open_div_timestamp_is_set;
  }


  /** 
   * <em>pts_acc_open_div_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPtsAccOpenDivTimestampIsModified() {
    return pts_acc_open_div_timestamp_is_modified;
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
   * <em>cfd_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCfdAccOpenDiv()
  {
    return cfd_acc_open_div;
  }


  /** 
   * <em>cfd_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCfdAccOpenDivIsSet() {
    return cfd_acc_open_div_is_set;
  }


  /** 
   * <em>cfd_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCfdAccOpenDivIsModified() {
    return cfd_acc_open_div_is_modified;
  }


  /** 
   * <em>cfd_acc_open_div_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCfdAccOpenDivLastUpdater()
  {
    return cfd_acc_open_div_last_updater;
  }


  /** 
   * <em>cfd_acc_open_div_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCfdAccOpenDivLastUpdaterIsSet() {
    return cfd_acc_open_div_last_updater_is_set;
  }


  /** 
   * <em>cfd_acc_open_div_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCfdAccOpenDivLastUpdaterIsModified() {
    return cfd_acc_open_div_last_updater_is_modified;
  }


  /** 
   * <em>cfd_acc_open_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCfdAccOpenUpdatedTimestamp()
  {
    return cfd_acc_open_updated_timestamp;
  }


  /** 
   * <em>cfd_acc_open_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCfdAccOpenUpdatedTimestampIsSet() {
    return cfd_acc_open_updated_timestamp_is_set;
  }


  /** 
   * <em>cfd_acc_open_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCfdAccOpenUpdatedTimestampIsModified() {
    return cfd_acc_open_updated_timestamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new MainAccountPK(account_id);
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
   * <em>institution_id</em>カラムの値を設定します。 
   *
   * @@param p_institutionId <em>institution_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setInstitutionId( long p_institutionId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_id = p_institutionId;
    institution_id_is_set = true;
    institution_id_is_modified = true;
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
   * <em>branch_id</em>カラムの値を設定します。 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * <em>branch_code</em>カラムの値を設定します。 
   *
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
    branch_code_is_modified = true;
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
   * <em>account_type</em>カラムの値を設定します。 
   *
   * @@param p_accountType <em>account_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum値 
   */
  public final void setAccountType( com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum p_accountType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_type = p_accountType;
    account_type_is_set = true;
    account_type_is_modified = true;
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
   * <em>middle_name</em>カラムの値を設定します。 
   *
   * @@param p_middleName <em>middle_name</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setMiddleName( String p_middleName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    middle_name = p_middleName;
    middle_name_is_set = true;
    middle_name_is_modified = true;
  }


  /** 
   * <em>given_name</em>カラムの値を設定します。 
   *
   * @@param p_givenName <em>given_name</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setGivenName( String p_givenName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    given_name = p_givenName;
    given_name_is_set = true;
    given_name_is_modified = true;
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
   * <em>middle_name_alt1</em>カラムの値を設定します。 
   *
   * @@param p_middleNameAlt1 <em>middle_name_alt1</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setMiddleNameAlt1( String p_middleNameAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    middle_name_alt1 = p_middleNameAlt1;
    middle_name_alt1_is_set = true;
    middle_name_alt1_is_modified = true;
  }


  /** 
   * <em>given_name_alt1</em>カラムの値を設定します。 
   *
   * @@param p_givenNameAlt1 <em>given_name_alt1</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setGivenNameAlt1( String p_givenNameAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    given_name_alt1 = p_givenNameAlt1;
    given_name_alt1_is_set = true;
    given_name_alt1_is_modified = true;
  }


  /** 
   * <em>family_name_alt2</em>カラムの値を設定します。 
   *
   * @@param p_familyNameAlt2 <em>family_name_alt2</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setFamilyNameAlt2( String p_familyNameAlt2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    family_name_alt2 = p_familyNameAlt2;
    family_name_alt2_is_set = true;
    family_name_alt2_is_modified = true;
  }


  /** 
   * <em>middle_name_alt2</em>カラムの値を設定します。 
   *
   * @@param p_middleNameAlt2 <em>middle_name_alt2</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setMiddleNameAlt2( String p_middleNameAlt2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    middle_name_alt2 = p_middleNameAlt2;
    middle_name_alt2_is_set = true;
    middle_name_alt2_is_modified = true;
  }


  /** 
   * <em>given_name_alt2</em>カラムの値を設定します。 
   *
   * @@param p_givenNameAlt2 <em>given_name_alt2</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setGivenNameAlt2( String p_givenNameAlt2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    given_name_alt2 = p_givenNameAlt2;
    given_name_alt2_is_set = true;
    given_name_alt2_is_modified = true;
  }


  /** 
   * <em>zip_code</em>カラムの値を設定します。 
   *
   * @@param p_zipCode <em>zip_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setZipCode( String p_zipCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    zip_code = p_zipCode;
    zip_code_is_set = true;
    zip_code_is_modified = true;
  }


  /** 
   * <em>sub_zip_code</em>カラムの値を設定します。 
   *
   * @@param p_subZipCode <em>sub_zip_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setSubZipCode( String p_subZipCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sub_zip_code = p_subZipCode;
    sub_zip_code_is_set = true;
    sub_zip_code_is_modified = true;
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
   * <em>mobile</em>カラムの値を設定します。 
   *
   * @@param p_mobile <em>mobile</em>カラムの値をあらわす16桁以下のString値 
   */
  public final void setMobile( String p_mobile )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mobile = p_mobile;
    mobile_is_set = true;
    mobile_is_modified = true;
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
   * <em>office</em>カラムの値を設定します。 
   *
   * @@param p_office <em>office</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setOffice( String p_office )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    office = p_office;
    office_is_set = true;
    office_is_modified = true;
  }


  /** 
   * <em>office_zip_code</em>カラムの値を設定します。 
   *
   * @@param p_officeZipCode <em>office_zip_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setOfficeZipCode( String p_officeZipCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    office_zip_code = p_officeZipCode;
    office_zip_code_is_set = true;
    office_zip_code_is_modified = true;
  }


  /** 
   * <em>office_address</em>カラムの値を設定します。 
   *
   * @@param p_officeAddress <em>office_address</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setOfficeAddress( String p_officeAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    office_address = p_officeAddress;
    office_address_is_set = true;
    office_address_is_modified = true;
  }


  /** 
   * <em>office_telephone</em>カラムの値を設定します。 
   *
   * @@param p_officeTelephone <em>office_telephone</em>カラムの値をあらわす16桁以下のString値 
   */
  public final void setOfficeTelephone( String p_officeTelephone )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    office_telephone = p_officeTelephone;
    office_telephone_is_set = true;
    office_telephone_is_modified = true;
  }


  /** 
   * <em>post</em>カラムの値を設定します。 
   *
   * @@param p_post <em>post</em>カラムの値をあらわす36桁以下のString値 
   */
  public final void setPost( String p_post )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    post = p_post;
    post_is_set = true;
    post_is_modified = true;
  }


  /** 
   * <em>equity_order_exe_mail_flag</em>カラムの値を設定します。 
   *
   * @@param p_equityOrderExeMailFlag <em>equity_order_exe_mail_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setEquityOrderExeMailFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_equityOrderExeMailFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_order_exe_mail_flag = p_equityOrderExeMailFlag;
    equity_order_exe_mail_flag_is_set = true;
    equity_order_exe_mail_flag_is_modified = true;
  }


  /** 
   * <em>equity_order_unexec_mail_flag</em>カラムの値を設定します。 
   *
   * @@param p_equityOrderUnexecMailFlag <em>equity_order_unexec_mail_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setEquityOrderUnexecMailFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_equityOrderUnexecMailFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_order_unexec_mail_flag = p_equityOrderUnexecMailFlag;
    equity_order_unexec_mail_flag_is_set = true;
    equity_order_unexec_mail_flag_is_modified = true;
  }


  /** 
   * <em>ifo_order_exec_mail_flag</em>カラムの値を設定します。 
   *
   * @@param p_ifoOrderExecMailFlag <em>ifo_order_exec_mail_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setIfoOrderExecMailFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_ifoOrderExecMailFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_order_exec_mail_flag = p_ifoOrderExecMailFlag;
    ifo_order_exec_mail_flag_is_set = true;
    ifo_order_exec_mail_flag_is_modified = true;
  }


  /** 
   * <em>ifo_order_unexec_mail_flag</em>カラムの値を設定します。 
   *
   * @@param p_ifoOrderUnexecMailFlag <em>ifo_order_unexec_mail_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setIfoOrderUnexecMailFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_ifoOrderUnexecMailFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_order_unexec_mail_flag = p_ifoOrderUnexecMailFlag;
    ifo_order_unexec_mail_flag_is_set = true;
    ifo_order_unexec_mail_flag_is_modified = true;
  }


  /** 
   * <em>information_mail_flag</em>カラムの値を設定します。 
   *
   * @@param p_informationMailFlag <em>information_mail_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setInformationMailFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_informationMailFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    information_mail_flag = p_informationMailFlag;
    information_mail_flag_is_set = true;
    information_mail_flag_is_modified = true;
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
   * <em>email_address</em>カラムの値を設定します。 
   *
   * @@param p_emailAddress <em>email_address</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setEmailAddress( String p_emailAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    email_address = p_emailAddress;
    email_address_is_set = true;
    email_address_is_modified = true;
  }


  /** 
   * <em>email_address_alt1</em>カラムの値を設定します。 
   *
   * @@param p_emailAddressAlt1 <em>email_address_alt1</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setEmailAddressAlt1( String p_emailAddressAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    email_address_alt1 = p_emailAddressAlt1;
    email_address_alt1_is_set = true;
    email_address_alt1_is_modified = true;
  }


  /** 
   * <em>trading_password</em>カラムの値を設定します。 
   *
   * @@param p_tradingPassword <em>trading_password</em>カラムの値をあらわす48桁以下のString値 
   */
  public final void setTradingPassword( String p_tradingPassword )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_password = p_tradingPassword;
    trading_password_is_set = true;
    trading_password_is_modified = true;
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
   * <em>account_close_date</em>カラムの値を設定します。 
   *
   * @@param p_accountCloseDate <em>account_close_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setAccountCloseDate( java.sql.Timestamp p_accountCloseDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_close_date = p_accountCloseDate;
    account_close_date_is_set = true;
    account_close_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>account_close_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setAccountCloseDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_close_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    account_close_date_is_set = true;
    account_close_date_is_modified = true;
  }


  /** 
   * <em>acc_open_send_mail_status</em>カラムの値を設定します。 
   *
   * @@param p_accOpenSendMailStatus <em>acc_open_send_mail_status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccOpenSendMailStatus( String p_accOpenSendMailStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    acc_open_send_mail_status = p_accOpenSendMailStatus;
    acc_open_send_mail_status_is_set = true;
    acc_open_send_mail_status_is_modified = true;
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
   * <em>bank_account_regi</em>カラムの値を設定します。 
   *
   * @@param p_bankAccountRegi <em>bank_account_regi</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBankAccountRegi( String p_bankAccountRegi )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bank_account_regi = p_bankAccountRegi;
    bank_account_regi_is_set = true;
    bank_account_regi_is_modified = true;
  }


  /** 
   * <em>account_status</em>カラムの値を設定します。 
   *
   * @@param p_accountStatus <em>account_status</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum値 
   */
  public final void setAccountStatus( com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum p_accountStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_status = p_accountStatus;
    account_status_is_set = true;
    account_status_is_modified = true;
  }


  /** 
   * <em>examin_lock_flag</em>カラムの値を設定します。 
   *
   * @@param p_examinLockFlag <em>examin_lock_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExaminLockFlag( String p_examinLockFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    examin_lock_flag = p_examinLockFlag;
    examin_lock_flag_is_set = true;
    examin_lock_flag_is_modified = true;
  }


  /** 
   * <em>mng_lock_flag</em>カラムの値を設定します。 
   *
   * @@param p_mngLockFlag <em>mng_lock_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMngLockFlag( String p_mngLockFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_flag = p_mngLockFlag;
    mng_lock_flag_is_set = true;
    mng_lock_flag_is_modified = true;
  }


  /** 
   * <em>mng_lock_off_start_date</em>カラムの値を設定します。 
   *
   * @@param p_mngLockOffStartDate <em>mng_lock_off_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setMngLockOffStartDate( java.sql.Timestamp p_mngLockOffStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_off_start_date = p_mngLockOffStartDate;
    mng_lock_off_start_date_is_set = true;
    mng_lock_off_start_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>mng_lock_off_start_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setMngLockOffStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_off_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    mng_lock_off_start_date_is_set = true;
    mng_lock_off_start_date_is_modified = true;
  }


  /** 
   * <em>mng_lock_off_end_date</em>カラムの値を設定します。 
   *
   * @@param p_mngLockOffEndDate <em>mng_lock_off_end_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setMngLockOffEndDate( java.sql.Timestamp p_mngLockOffEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_off_end_date = p_mngLockOffEndDate;
    mng_lock_off_end_date_is_set = true;
    mng_lock_off_end_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>mng_lock_off_end_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setMngLockOffEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_off_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    mng_lock_off_end_date_is_set = true;
    mng_lock_off_end_date_is_modified = true;
  }


  /** 
   * <em>mng_lock_flag_advance</em>カラムの値を設定します。 
   *
   * @@param p_mngLockFlagAdvance <em>mng_lock_flag_advance</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setMngLockFlagAdvance( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_mngLockFlagAdvance )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_flag_advance = p_mngLockFlagAdvance;
    mng_lock_flag_advance_is_set = true;
    mng_lock_flag_advance_is_modified = true;
  }


  /** 
   * <em>mng_lock_flag_unpay_margin</em>カラムの値を設定します。 
   *
   * @@param p_mngLockFlagUnpayMargin <em>mng_lock_flag_unpay_margin</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setMngLockFlagUnpayMargin( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_mngLockFlagUnpayMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_flag_unpay_margin = p_mngLockFlagUnpayMargin;
    mng_lock_flag_unpay_margin_is_set = true;
    mng_lock_flag_unpay_margin_is_modified = true;
  }


  /** 
   * <em>mng_lock_flag_short_security</em>カラムの値を設定します。 
   *
   * @@param p_mngLockFlagShortSecurity <em>mng_lock_flag_short_security</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setMngLockFlagShortSecurity( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_mngLockFlagShortSecurity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_flag_short_security = p_mngLockFlagShortSecurity;
    mng_lock_flag_short_security_is_set = true;
    mng_lock_flag_short_security_is_modified = true;
  }


  /** 
   * <em>mng_lock_flag_unsubstit_depo</em>カラムの値を設定します。 
   *
   * @@param p_mngLockFlagUnsubstitDepo <em>mng_lock_flag_unsubstit_depo</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setMngLockFlagUnsubstitDepo( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_mngLockFlagUnsubstitDepo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mng_lock_flag_unsubstit_depo = p_mngLockFlagUnsubstitDepo;
    mng_lock_flag_unsubstit_depo_is_set = true;
    mng_lock_flag_unsubstit_depo_is_modified = true;
  }


  /** 
   * <em>branch_lock</em>カラムの値を設定します。 
   *
   * @@param p_branchLock <em>branch_lock</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBranchLock( String p_branchLock )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_lock = p_branchLock;
    branch_lock_is_set = true;
    branch_lock_is_modified = true;
  }


  /** 
   * <em>order_permission</em>カラムの値を設定します。 
   *
   * @@param p_orderPermission <em>order_permission</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderPermission( String p_orderPermission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_permission = p_orderPermission;
    order_permission_is_set = true;
    order_permission_is_modified = true;
  }


  /** 
   * <em>tax_type</em>カラムの値を設定します。 
   *
   * @@param p_taxType <em>tax_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum値 
   */
  public final void setTaxType( com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_taxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tax_type = p_taxType;
    tax_type_is_set = true;
    tax_type_is_modified = true;
  }


  /** 
   * <em>tax_type_next</em>カラムの値を設定します。 
   *
   * @@param p_taxTypeNext <em>tax_type_next</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum値 
   */
  public final void setTaxTypeNext( com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_taxTypeNext )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tax_type_next = p_taxTypeNext;
    tax_type_next_is_set = true;
    tax_type_next_is_modified = true;
  }


  /** 
   * <em>margin_tax_type</em>カラムの値を設定します。 
   *
   * @@param p_marginTaxType <em>margin_tax_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum値 
   */
  public final void setMarginTaxType( com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_marginTaxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_tax_type = p_marginTaxType;
    margin_tax_type_is_set = true;
    margin_tax_type_is_modified = true;
  }


  /** 
   * <em>margin_tax_type_next</em>カラムの値を設定します。 
   *
   * @@param p_marginTaxTypeNext <em>margin_tax_type_next</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum値 
   */
  public final void setMarginTaxTypeNext( com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum p_marginTaxTypeNext )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_tax_type_next = p_marginTaxTypeNext;
    margin_tax_type_next_is_set = true;
    margin_tax_type_next_is_modified = true;
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
   * <em>margin_sys_acc_open_div</em>カラムの値を設定します。 
   *
   * @@param p_marginSysAccOpenDiv <em>margin_sys_acc_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarginSysAccOpenDiv( String p_marginSysAccOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sys_acc_open_div = p_marginSysAccOpenDiv;
    margin_sys_acc_open_div_is_set = true;
    margin_sys_acc_open_div_is_modified = true;
  }


  /** 
   * <em>margin_gen_acc_open_div</em>カラムの値を設定します。 
   *
   * @@param p_marginGenAccOpenDiv <em>margin_gen_acc_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarginGenAccOpenDiv( String p_marginGenAccOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_gen_acc_open_div = p_marginGenAccOpenDiv;
    margin_gen_acc_open_div_is_set = true;
    margin_gen_acc_open_div_is_modified = true;
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
   * <em>transfer_count</em>カラムの値を設定します。 
   *
   * @@param p_transferCount <em>transfer_count</em>カラムの値をあらわす2桁以下のint値 
   */
  public final void setTransferCount( int p_transferCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_count = new Integer(p_transferCount);
    transfer_count_is_set = true;
    transfer_count_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>transfer_count</em>カラムに値を設定します。 
   */
  public final void setTransferCount( Integer p_transferCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_count = p_transferCount;
    transfer_count_is_set = true;
    transfer_count_is_modified = true;
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
   * <em>fx_acc_open_div</em>カラムの値を設定します。 
   *
   * @@param p_fxAccOpenDiv <em>fx_acc_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFxAccOpenDiv( String p_fxAccOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_acc_open_div = p_fxAccOpenDiv;
    fx_acc_open_div_is_set = true;
    fx_acc_open_div_is_modified = true;
  }


  /** 
   * <em>feq_con_acc_open_div</em>カラムの値を設定します。 
   *
   * @@param p_feqConAccOpenDiv <em>feq_con_acc_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFeqConAccOpenDiv( String p_feqConAccOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    feq_con_acc_open_div = p_feqConAccOpenDiv;
    feq_con_acc_open_div_is_set = true;
    feq_con_acc_open_div_is_modified = true;
  }


  /** 
   * <em>top_page_id</em>カラムの値を設定します。 
   *
   * @@param p_topPageId <em>top_page_id</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setTopPageId( String p_topPageId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    top_page_id = p_topPageId;
    top_page_id_is_set = true;
    top_page_id_is_modified = true;
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
   * <em>certificate_deposit_flag</em>カラムの値を設定します。 
   *
   * @@param p_certificateDepositFlag <em>certificate_deposit_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setCertificateDepositFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_certificateDepositFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    certificate_deposit_flag = p_certificateDepositFlag;
    certificate_deposit_flag_is_set = true;
    certificate_deposit_flag_is_modified = true;
  }


  /** 
   * <em>account_statement_flag</em>カラムの値を設定します。 
   *
   * @@param p_accountStatementFlag <em>account_statement_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setAccountStatementFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_accountStatementFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_statement_flag = p_accountStatementFlag;
    account_statement_flag_is_set = true;
    account_statement_flag_is_modified = true;
  }


  /** 
   * <em>email_last_updater</em>カラムの値を設定します。 
   *
   * @@param p_emailLastUpdater <em>email_last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setEmailLastUpdater( String p_emailLastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    email_last_updater = p_emailLastUpdater;
    email_last_updater_is_set = true;
    email_last_updater_is_modified = true;
  }


  /** 
   * <em>email_last_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_emailLastUpdatedTimestamp <em>email_last_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setEmailLastUpdatedTimestamp( java.sql.Timestamp p_emailLastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    email_last_updated_timestamp = p_emailLastUpdatedTimestamp;
    email_last_updated_timestamp_is_set = true;
    email_last_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>email_last_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setEmailLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    email_last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    email_last_updated_timestamp_is_set = true;
    email_last_updated_timestamp_is_modified = true;
  }


  /** 
   * <em>trading_password_updater</em>カラムの値を設定します。 
   *
   * @@param p_tradingPasswordUpdater <em>trading_password_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setTradingPasswordUpdater( String p_tradingPasswordUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_password_updater = p_tradingPasswordUpdater;
    trading_password_updater_is_set = true;
    trading_password_updater_is_modified = true;
  }


  /** 
   * <em>tr_pwd_last_update_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_trPwdLastUpdateTimestamp <em>tr_pwd_last_update_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTrPwdLastUpdateTimestamp( java.sql.Timestamp p_trPwdLastUpdateTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tr_pwd_last_update_timestamp = p_trPwdLastUpdateTimestamp;
    tr_pwd_last_update_timestamp_is_set = true;
    tr_pwd_last_update_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>tr_pwd_last_update_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTrPwdLastUpdateTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    tr_pwd_last_update_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    tr_pwd_last_update_timestamp_is_set = true;
    tr_pwd_last_update_timestamp_is_modified = true;
  }


  /** 
   * <em>mb_off_last_updater</em>カラムの値を設定します。 
   *
   * @@param p_mbOffLastUpdater <em>mb_off_last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setMbOffLastUpdater( String p_mbOffLastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mb_off_last_updater = p_mbOffLastUpdater;
    mb_off_last_updater_is_set = true;
    mb_off_last_updater_is_modified = true;
  }


  /** 
   * <em>mb_off_last_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_mbOffLastUpdatedTimestamp <em>mb_off_last_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setMbOffLastUpdatedTimestamp( java.sql.Timestamp p_mbOffLastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mb_off_last_updated_timestamp = p_mbOffLastUpdatedTimestamp;
    mb_off_last_updated_timestamp_is_set = true;
    mb_off_last_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>mb_off_last_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setMbOffLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mb_off_last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    mb_off_last_updated_timestamp_is_set = true;
    mb_off_last_updated_timestamp_is_modified = true;
  }


  /** 
   * <em>enable_order_last_updater</em>カラムの値を設定します。 
   *
   * @@param p_enableOrderLastUpdater <em>enable_order_last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setEnableOrderLastUpdater( String p_enableOrderLastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    enable_order_last_updater = p_enableOrderLastUpdater;
    enable_order_last_updater_is_set = true;
    enable_order_last_updater_is_modified = true;
  }


  /** 
   * <em>enable_order_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_enableOrderUpdatedTimestamp <em>enable_order_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setEnableOrderUpdatedTimestamp( java.sql.Timestamp p_enableOrderUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    enable_order_updated_timestamp = p_enableOrderUpdatedTimestamp;
    enable_order_updated_timestamp_is_set = true;
    enable_order_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>enable_order_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setEnableOrderUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    enable_order_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    enable_order_updated_timestamp_is_set = true;
    enable_order_updated_timestamp_is_modified = true;
  }


  /** 
   * <em>fx_acc_open_div_last_updater</em>カラムの値を設定します。 
   *
   * @@param p_fxAccOpenDivLastUpdater <em>fx_acc_open_div_last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setFxAccOpenDivLastUpdater( String p_fxAccOpenDivLastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_acc_open_div_last_updater = p_fxAccOpenDivLastUpdater;
    fx_acc_open_div_last_updater_is_set = true;
    fx_acc_open_div_last_updater_is_modified = true;
  }


  /** 
   * <em>fx_acc_open_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_fxAccOpenUpdatedTimestamp <em>fx_acc_open_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setFxAccOpenUpdatedTimestamp( java.sql.Timestamp p_fxAccOpenUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fx_acc_open_updated_timestamp = p_fxAccOpenUpdatedTimestamp;
    fx_acc_open_updated_timestamp_is_set = true;
    fx_acc_open_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>fx_acc_open_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setFxAccOpenUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    fx_acc_open_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    fx_acc_open_updated_timestamp_is_set = true;
    fx_acc_open_updated_timestamp_is_modified = true;
  }


  /** 
   * <em>feq_con_acc_open_div_updater</em>カラムの値を設定します。 
   *
   * @@param p_feqConAccOpenDivUpdater <em>feq_con_acc_open_div_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setFeqConAccOpenDivUpdater( String p_feqConAccOpenDivUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    feq_con_acc_open_div_updater = p_feqConAccOpenDivUpdater;
    feq_con_acc_open_div_updater_is_set = true;
    feq_con_acc_open_div_updater_is_modified = true;
  }


  /** 
   * <em>feq_con_acc_open_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_feqConAccOpenTimestamp <em>feq_con_acc_open_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setFeqConAccOpenTimestamp( java.sql.Timestamp p_feqConAccOpenTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    feq_con_acc_open_timestamp = p_feqConAccOpenTimestamp;
    feq_con_acc_open_timestamp_is_set = true;
    feq_con_acc_open_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>feq_con_acc_open_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setFeqConAccOpenTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    feq_con_acc_open_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    feq_con_acc_open_timestamp_is_set = true;
    feq_con_acc_open_timestamp_is_modified = true;
  }


  /** 
   * <em>mrf_fund_code</em>カラムの値を設定します。 
   *
   * @@param p_mrfFundCode <em>mrf_fund_code</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMrfFundCode( String p_mrfFundCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mrf_fund_code = p_mrfFundCode;
    mrf_fund_code_is_set = true;
    mrf_fund_code_is_modified = true;
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
   * <em>lock_registration_reason</em>カラムの値を設定します。 
   *
   * @@param p_lockRegistrationReason <em>lock_registration_reason</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setLockRegistrationReason( String p_lockRegistrationReason )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    lock_registration_reason = p_lockRegistrationReason;
    lock_registration_reason_is_set = true;
    lock_registration_reason_is_modified = true;
  }


  /** 
   * <em>inf_mail_flg_last_updater</em>カラムの値を設定します。 
   *
   * @@param p_infMailFlgLastUpdater <em>inf_mail_flg_last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setInfMailFlgLastUpdater( String p_infMailFlgLastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    inf_mail_flg_last_updater = p_infMailFlgLastUpdater;
    inf_mail_flg_last_updater_is_set = true;
    inf_mail_flg_last_updater_is_modified = true;
  }


  /** 
   * <em>inf_mail_flg_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_infMailFlgUpdatedTimestamp <em>inf_mail_flg_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setInfMailFlgUpdatedTimestamp( java.sql.Timestamp p_infMailFlgUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    inf_mail_flg_updated_timestamp = p_infMailFlgUpdatedTimestamp;
    inf_mail_flg_updated_timestamp_is_set = true;
    inf_mail_flg_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>inf_mail_flg_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setInfMailFlgUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    inf_mail_flg_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    inf_mail_flg_updated_timestamp_is_set = true;
    inf_mail_flg_updated_timestamp_is_modified = true;
  }


  /** 
   * <em>eq_exe_ml_flg_last_updater</em>カラムの値を設定します。 
   *
   * @@param p_eqExeMlFlgLastUpdater <em>eq_exe_ml_flg_last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setEqExeMlFlgLastUpdater( String p_eqExeMlFlgLastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    eq_exe_ml_flg_last_updater = p_eqExeMlFlgLastUpdater;
    eq_exe_ml_flg_last_updater_is_set = true;
    eq_exe_ml_flg_last_updater_is_modified = true;
  }


  /** 
   * <em>eq_exe_ml_flg_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_eqExeMlFlgTimestamp <em>eq_exe_ml_flg_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setEqExeMlFlgTimestamp( java.sql.Timestamp p_eqExeMlFlgTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    eq_exe_ml_flg_timestamp = p_eqExeMlFlgTimestamp;
    eq_exe_ml_flg_timestamp_is_set = true;
    eq_exe_ml_flg_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>eq_exe_ml_flg_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setEqExeMlFlgTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    eq_exe_ml_flg_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    eq_exe_ml_flg_timestamp_is_set = true;
    eq_exe_ml_flg_timestamp_is_modified = true;
  }


  /** 
   * <em>eq_unexe_ml_flg_last_updater</em>カラムの値を設定します。 
   *
   * @@param p_eqUnexeMlFlgLastUpdater <em>eq_unexe_ml_flg_last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setEqUnexeMlFlgLastUpdater( String p_eqUnexeMlFlgLastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    eq_unexe_ml_flg_last_updater = p_eqUnexeMlFlgLastUpdater;
    eq_unexe_ml_flg_last_updater_is_set = true;
    eq_unexe_ml_flg_last_updater_is_modified = true;
  }


  /** 
   * <em>eq_unexe_ml_flg_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_eqUnexeMlFlgTimestamp <em>eq_unexe_ml_flg_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setEqUnexeMlFlgTimestamp( java.sql.Timestamp p_eqUnexeMlFlgTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    eq_unexe_ml_flg_timestamp = p_eqUnexeMlFlgTimestamp;
    eq_unexe_ml_flg_timestamp_is_set = true;
    eq_unexe_ml_flg_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>eq_unexe_ml_flg_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setEqUnexeMlFlgTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    eq_unexe_ml_flg_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    eq_unexe_ml_flg_timestamp_is_set = true;
    eq_unexe_ml_flg_timestamp_is_modified = true;
  }


  /** 
   * <em>ifo_exe_ml_flg_last_updater</em>カラムの値を設定します。 
   *
   * @@param p_ifoExeMlFlgLastUpdater <em>ifo_exe_ml_flg_last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setIfoExeMlFlgLastUpdater( String p_ifoExeMlFlgLastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_exe_ml_flg_last_updater = p_ifoExeMlFlgLastUpdater;
    ifo_exe_ml_flg_last_updater_is_set = true;
    ifo_exe_ml_flg_last_updater_is_modified = true;
  }


  /** 
   * <em>ifo_exe_ml_flg_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_ifoExeMlFlgTimestamp <em>ifo_exe_ml_flg_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setIfoExeMlFlgTimestamp( java.sql.Timestamp p_ifoExeMlFlgTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_exe_ml_flg_timestamp = p_ifoExeMlFlgTimestamp;
    ifo_exe_ml_flg_timestamp_is_set = true;
    ifo_exe_ml_flg_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>ifo_exe_ml_flg_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setIfoExeMlFlgTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_exe_ml_flg_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    ifo_exe_ml_flg_timestamp_is_set = true;
    ifo_exe_ml_flg_timestamp_is_modified = true;
  }


  /** 
   * <em>ifo_unexe_ml_flg_last_updater</em>カラムの値を設定します。 
   *
   * @@param p_ifoUnexeMlFlgLastUpdater <em>ifo_unexe_ml_flg_last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setIfoUnexeMlFlgLastUpdater( String p_ifoUnexeMlFlgLastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_unexe_ml_flg_last_updater = p_ifoUnexeMlFlgLastUpdater;
    ifo_unexe_ml_flg_last_updater_is_set = true;
    ifo_unexe_ml_flg_last_updater_is_modified = true;
  }


  /** 
   * <em>ifo_unexe_ml_flg_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_ifoUnexeMlFlgTimestamp <em>ifo_unexe_ml_flg_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setIfoUnexeMlFlgTimestamp( java.sql.Timestamp p_ifoUnexeMlFlgTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_unexe_ml_flg_timestamp = p_ifoUnexeMlFlgTimestamp;
    ifo_unexe_ml_flg_timestamp_is_set = true;
    ifo_unexe_ml_flg_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>ifo_unexe_ml_flg_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setIfoUnexeMlFlgTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_unexe_ml_flg_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    ifo_unexe_ml_flg_timestamp_is_set = true;
    ifo_unexe_ml_flg_timestamp_is_modified = true;
  }


  /** 
   * <em>stock_option_acc_open_div</em>カラムの値を設定します。 
   *
   * @@param p_stockOptionAccOpenDiv <em>stock_option_acc_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStockOptionAccOpenDiv( String p_stockOptionAccOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stock_option_acc_open_div = p_stockOptionAccOpenDiv;
    stock_option_acc_open_div_is_set = true;
    stock_option_acc_open_div_is_modified = true;
  }


  /** 
   * <em>secured_loan_sec_acc_open_div</em>カラムの値を設定します。 
   *
   * @@param p_securedLoanSecAccOpenDiv <em>secured_loan_sec_acc_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSecuredLoanSecAccOpenDiv( String p_securedLoanSecAccOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    secured_loan_sec_acc_open_div = p_securedLoanSecAccOpenDiv;
    secured_loan_sec_acc_open_div_is_set = true;
    secured_loan_sec_acc_open_div_is_modified = true;
  }


  /** 
   * <em>margin_acc_open_date</em>カラムの値を設定します。 
   *
   * @@param p_marginAccOpenDate <em>margin_acc_open_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setMarginAccOpenDate( java.sql.Timestamp p_marginAccOpenDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_acc_open_date = p_marginAccOpenDate;
    margin_acc_open_date_is_set = true;
    margin_acc_open_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>margin_acc_open_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setMarginAccOpenDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_acc_open_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    margin_acc_open_date_is_set = true;
    margin_acc_open_date_is_modified = true;
  }


  /** 
   * <em>ifo_acc_open_date</em>カラムの値を設定します。 
   *
   * @@param p_ifoAccOpenDate <em>ifo_acc_open_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setIfoAccOpenDate( java.sql.Timestamp p_ifoAccOpenDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_acc_open_date = p_ifoAccOpenDate;
    ifo_acc_open_date_is_set = true;
    ifo_acc_open_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>ifo_acc_open_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setIfoAccOpenDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_acc_open_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    ifo_acc_open_date_is_set = true;
    ifo_acc_open_date_is_modified = true;
  }


  /** 
   * <em>only_mobile_open_div</em>カラムの値を設定します。 
   *
   * @@param p_onlyMobileOpenDiv <em>only_mobile_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOnlyMobileOpenDiv( String p_onlyMobileOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    only_mobile_open_div = p_onlyMobileOpenDiv;
    only_mobile_open_div_is_set = true;
    only_mobile_open_div_is_modified = true;
  }


  /** 
   * <em>only_mbl_opn_div_last_updater</em>カラムの値を設定します。 
   *
   * @@param p_onlyMblOpnDivLastUpdater <em>only_mbl_opn_div_last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setOnlyMblOpnDivLastUpdater( String p_onlyMblOpnDivLastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    only_mbl_opn_div_last_updater = p_onlyMblOpnDivLastUpdater;
    only_mbl_opn_div_last_updater_is_set = true;
    only_mbl_opn_div_last_updater_is_modified = true;
  }


  /** 
   * <em>only_mbl_opn_div_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_onlyMblOpnDivTimestamp <em>only_mbl_opn_div_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOnlyMblOpnDivTimestamp( java.sql.Timestamp p_onlyMblOpnDivTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    only_mbl_opn_div_timestamp = p_onlyMblOpnDivTimestamp;
    only_mbl_opn_div_timestamp_is_set = true;
    only_mbl_opn_div_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>only_mbl_opn_div_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOnlyMblOpnDivTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    only_mbl_opn_div_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    only_mbl_opn_div_timestamp_is_set = true;
    only_mbl_opn_div_timestamp_is_modified = true;
  }


  /** 
   * <em>email_address_alt2</em>カラムの値を設定します。 
   *
   * @@param p_emailAddressAlt2 <em>email_address_alt2</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setEmailAddressAlt2( String p_emailAddressAlt2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    email_address_alt2 = p_emailAddressAlt2;
    email_address_alt2_is_set = true;
    email_address_alt2_is_modified = true;
  }


  /** 
   * <em>order_exe_flag</em>カラムの値を設定します。 
   *
   * @@param p_orderExeFlag <em>order_exe_flag</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setOrderExeFlag( int p_orderExeFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_exe_flag = new Integer(p_orderExeFlag);
    order_exe_flag_is_set = true;
    order_exe_flag_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>order_exe_flag</em>カラムに値を設定します。 
   */
  public final void setOrderExeFlag( Integer p_orderExeFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_exe_flag = p_orderExeFlag;
    order_exe_flag_is_set = true;
    order_exe_flag_is_modified = true;
  }


  /** 
   * <em>order_unexe_flag</em>カラムの値を設定します。 
   *
   * @@param p_orderUnexeFlag <em>order_unexe_flag</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setOrderUnexeFlag( int p_orderUnexeFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_unexe_flag = new Integer(p_orderUnexeFlag);
    order_unexe_flag_is_set = true;
    order_unexe_flag_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>order_unexe_flag</em>カラムに値を設定します。 
   */
  public final void setOrderUnexeFlag( Integer p_orderUnexeFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    order_unexe_flag = p_orderUnexeFlag;
    order_unexe_flag_is_set = true;
    order_unexe_flag_is_modified = true;
  }


  /** 
   * <em>important_connection_mail_flag</em>カラムの値を設定します。 
   *
   * @@param p_importantConnectionMailFlag <em>important_connection_mail_flag</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setImportantConnectionMailFlag( int p_importantConnectionMailFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    important_connection_mail_flag = new Integer(p_importantConnectionMailFlag);
    important_connection_mail_flag_is_set = true;
    important_connection_mail_flag_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>important_connection_mail_flag</em>カラムに値を設定します。 
   */
  public final void setImportantConnectionMailFlag( Integer p_importantConnectionMailFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    important_connection_mail_flag = p_importantConnectionMailFlag;
    important_connection_mail_flag_is_set = true;
    important_connection_mail_flag_is_modified = true;
  }


  /** 
   * <em>information_mail2_flag</em>カラムの値を設定します。 
   *
   * @@param p_informationMail2Flag <em>information_mail2_flag</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setInformationMail2Flag( int p_informationMail2Flag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    information_mail2_flag = new Integer(p_informationMail2Flag);
    information_mail2_flag_is_set = true;
    information_mail2_flag_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>information_mail2_flag</em>カラムに値を設定します。 
   */
  public final void setInformationMail2Flag( Integer p_informationMail2Flag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    information_mail2_flag = p_informationMail2Flag;
    information_mail2_flag_is_set = true;
    information_mail2_flag_is_modified = true;
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
   * <em>pts_acc_open_div</em>カラムの値を設定します。 
   *
   * @@param p_ptsAccOpenDiv <em>pts_acc_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPtsAccOpenDiv( String p_ptsAccOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pts_acc_open_div = p_ptsAccOpenDiv;
    pts_acc_open_div_is_set = true;
    pts_acc_open_div_is_modified = true;
  }


  /** 
   * <em>pts_acc_open_div_last_updater</em>カラムの値を設定します。 
   *
   * @@param p_ptsAccOpenDivLastUpdater <em>pts_acc_open_div_last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setPtsAccOpenDivLastUpdater( String p_ptsAccOpenDivLastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pts_acc_open_div_last_updater = p_ptsAccOpenDivLastUpdater;
    pts_acc_open_div_last_updater_is_set = true;
    pts_acc_open_div_last_updater_is_modified = true;
  }


  /** 
   * <em>pts_acc_open_div_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_ptsAccOpenDivTimestamp <em>pts_acc_open_div_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setPtsAccOpenDivTimestamp( java.sql.Timestamp p_ptsAccOpenDivTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pts_acc_open_div_timestamp = p_ptsAccOpenDivTimestamp;
    pts_acc_open_div_timestamp_is_set = true;
    pts_acc_open_div_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>pts_acc_open_div_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setPtsAccOpenDivTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    pts_acc_open_div_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    pts_acc_open_div_timestamp_is_set = true;
    pts_acc_open_div_timestamp_is_modified = true;
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
   * <em>cfd_acc_open_div</em>カラムの値を設定します。 
   *
   * @@param p_cfdAccOpenDiv <em>cfd_acc_open_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCfdAccOpenDiv( String p_cfdAccOpenDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cfd_acc_open_div = p_cfdAccOpenDiv;
    cfd_acc_open_div_is_set = true;
    cfd_acc_open_div_is_modified = true;
  }


  /** 
   * <em>cfd_acc_open_div_last_updater</em>カラムの値を設定します。 
   *
   * @@param p_cfdAccOpenDivLastUpdater <em>cfd_acc_open_div_last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setCfdAccOpenDivLastUpdater( String p_cfdAccOpenDivLastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cfd_acc_open_div_last_updater = p_cfdAccOpenDivLastUpdater;
    cfd_acc_open_div_last_updater_is_set = true;
    cfd_acc_open_div_last_updater_is_modified = true;
  }


  /** 
   * <em>cfd_acc_open_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_cfdAccOpenUpdatedTimestamp <em>cfd_acc_open_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCfdAccOpenUpdatedTimestamp( java.sql.Timestamp p_cfdAccOpenUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cfd_acc_open_updated_timestamp = p_cfdAccOpenUpdatedTimestamp;
    cfd_acc_open_updated_timestamp_is_set = true;
    cfd_acc_open_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>cfd_acc_open_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCfdAccOpenUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cfd_acc_open_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    cfd_acc_open_updated_timestamp_is_set = true;
    cfd_acc_open_updated_timestamp_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                else if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("account_type") ) {
                    return this.account_type;
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
                else if ( name.equals("account_open_date") ) {
                    return this.account_open_date;
                }
                else if ( name.equals("account_close_date") ) {
                    return this.account_close_date;
                }
                else if ( name.equals("acc_open_send_mail_status") ) {
                    return this.acc_open_send_mail_status;
                }
                else if ( name.equals("account_status") ) {
                    return this.account_status;
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
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                else if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("born_date") ) {
                    return this.born_date;
                }
                else if ( name.equals("bank_account_regi") ) {
                    return this.bank_account_regi;
                }
                else if ( name.equals("branch_lock") ) {
                    return this.branch_lock;
                }
                else if ( name.equals("broadcast_law") ) {
                    return this.broadcast_law;
                }
                break;
            case 'c':
                if ( name.equals("class_div") ) {
                    return this.class_div;
                }
                else if ( name.equals("certificate_deposit_flag") ) {
                    return this.certificate_deposit_flag;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("chkup_rep_div") ) {
                    return this.chkup_rep_div;
                }
                else if ( name.equals("cfd_acc_open_div") ) {
                    return this.cfd_acc_open_div;
                }
                else if ( name.equals("cfd_acc_open_div_last_updater") ) {
                    return this.cfd_acc_open_div_last_updater;
                }
                else if ( name.equals("cfd_acc_open_updated_timestamp") ) {
                    return this.cfd_acc_open_updated_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("dividend_trans_designate") ) {
                    return this.dividend_trans_designate;
                }
                break;
            case 'e':
                if ( name.equals("equity_order_exe_mail_flag") ) {
                    return this.equity_order_exe_mail_flag;
                }
                else if ( name.equals("equity_order_unexec_mail_flag") ) {
                    return this.equity_order_unexec_mail_flag;
                }
                else if ( name.equals("email_address") ) {
                    return this.email_address;
                }
                else if ( name.equals("email_address_alt1") ) {
                    return this.email_address_alt1;
                }
                else if ( name.equals("era_born") ) {
                    return this.era_born;
                }
                else if ( name.equals("examin_lock_flag") ) {
                    return this.examin_lock_flag;
                }
                else if ( name.equals("equity_sp_acc_open_date") ) {
                    return this.equity_sp_acc_open_date;
                }
                else if ( name.equals("email_last_updater") ) {
                    return this.email_last_updater;
                }
                else if ( name.equals("email_last_updated_timestamp") ) {
                    return this.email_last_updated_timestamp;
                }
                else if ( name.equals("enable_order_last_updater") ) {
                    return this.enable_order_last_updater;
                }
                else if ( name.equals("enable_order_updated_timestamp") ) {
                    return this.enable_order_updated_timestamp;
                }
                else if ( name.equals("eq_exe_ml_flg_last_updater") ) {
                    return this.eq_exe_ml_flg_last_updater;
                }
                else if ( name.equals("eq_exe_ml_flg_timestamp") ) {
                    return this.eq_exe_ml_flg_timestamp;
                }
                else if ( name.equals("eq_unexe_ml_flg_last_updater") ) {
                    return this.eq_unexe_ml_flg_last_updater;
                }
                else if ( name.equals("eq_unexe_ml_flg_timestamp") ) {
                    return this.eq_unexe_ml_flg_timestamp;
                }
                else if ( name.equals("email_address_alt2") ) {
                    return this.email_address_alt2;
                }
                else if ( name.equals("eq_hold_rep_div") ) {
                    return this.eq_hold_rep_div;
                }
                break;
            case 'f':
                if ( name.equals("family_name") ) {
                    return this.family_name;
                }
                else if ( name.equals("family_name_alt1") ) {
                    return this.family_name_alt1;
                }
                else if ( name.equals("family_name_alt2") ) {
                    return this.family_name_alt2;
                }
                else if ( name.equals("fax") ) {
                    return this.fax;
                }
                else if ( name.equals("foreign_sec_acc_open_div") ) {
                    return this.foreign_sec_acc_open_div;
                }
                else if ( name.equals("fx_acc_open_div") ) {
                    return this.fx_acc_open_div;
                }
                else if ( name.equals("feq_con_acc_open_div") ) {
                    return this.feq_con_acc_open_div;
                }
                else if ( name.equals("fx_acc_open_div_last_updater") ) {
                    return this.fx_acc_open_div_last_updater;
                }
                else if ( name.equals("fx_acc_open_updated_timestamp") ) {
                    return this.fx_acc_open_updated_timestamp;
                }
                else if ( name.equals("feq_con_acc_open_div_updater") ) {
                    return this.feq_con_acc_open_div_updater;
                }
                else if ( name.equals("feq_con_acc_open_timestamp") ) {
                    return this.feq_con_acc_open_timestamp;
                }
                break;
            case 'g':
                if ( name.equals("given_name") ) {
                    return this.given_name;
                }
                else if ( name.equals("given_name_alt1") ) {
                    return this.given_name_alt1;
                }
                else if ( name.equals("given_name_alt2") ) {
                    return this.given_name_alt2;
                }
                break;
            case 'h':
                if ( name.equals("ht_settlement_way") ) {
                    return this.ht_settlement_way;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("institution_id") ) {
                    return new Long( institution_id );
                }
                else if ( name.equals("ifo_order_exec_mail_flag") ) {
                    return this.ifo_order_exec_mail_flag;
                }
                else if ( name.equals("ifo_order_unexec_mail_flag") ) {
                    return this.ifo_order_unexec_mail_flag;
                }
                else if ( name.equals("information_mail_flag") ) {
                    return this.information_mail_flag;
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
                else if ( name.equals("inf_mail_flg_last_updater") ) {
                    return this.inf_mail_flg_last_updater;
                }
                else if ( name.equals("inf_mail_flg_updated_timestamp") ) {
                    return this.inf_mail_flg_updated_timestamp;
                }
                else if ( name.equals("ifo_exe_ml_flg_last_updater") ) {
                    return this.ifo_exe_ml_flg_last_updater;
                }
                else if ( name.equals("ifo_exe_ml_flg_timestamp") ) {
                    return this.ifo_exe_ml_flg_timestamp;
                }
                else if ( name.equals("ifo_unexe_ml_flg_last_updater") ) {
                    return this.ifo_unexe_ml_flg_last_updater;
                }
                else if ( name.equals("ifo_unexe_ml_flg_timestamp") ) {
                    return this.ifo_unexe_ml_flg_timestamp;
                }
                else if ( name.equals("ifo_acc_open_date") ) {
                    return this.ifo_acc_open_date;
                }
                else if ( name.equals("important_connection_mail_flag") ) {
                    return this.important_connection_mail_flag;
                }
                else if ( name.equals("information_mail2_flag") ) {
                    return this.information_mail2_flag;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                else if ( name.equals("lock_registration_reason") ) {
                    return this.lock_registration_reason;
                }
                break;
            case 'm':
                if ( name.equals("middle_name") ) {
                    return this.middle_name;
                }
                else if ( name.equals("middle_name_alt1") ) {
                    return this.middle_name_alt1;
                }
                else if ( name.equals("middle_name_alt2") ) {
                    return this.middle_name_alt2;
                }
                else if ( name.equals("mobile") ) {
                    return this.mobile;
                }
                else if ( name.equals("mng_lock_flag") ) {
                    return this.mng_lock_flag;
                }
                else if ( name.equals("mng_lock_off_start_date") ) {
                    return this.mng_lock_off_start_date;
                }
                else if ( name.equals("mng_lock_off_end_date") ) {
                    return this.mng_lock_off_end_date;
                }
                else if ( name.equals("mng_lock_flag_advance") ) {
                    return this.mng_lock_flag_advance;
                }
                else if ( name.equals("mng_lock_flag_unpay_margin") ) {
                    return this.mng_lock_flag_unpay_margin;
                }
                else if ( name.equals("mng_lock_flag_short_security") ) {
                    return this.mng_lock_flag_short_security;
                }
                else if ( name.equals("mng_lock_flag_unsubstit_depo") ) {
                    return this.mng_lock_flag_unsubstit_depo;
                }
                else if ( name.equals("margin_tax_type") ) {
                    return this.margin_tax_type;
                }
                else if ( name.equals("margin_tax_type_next") ) {
                    return this.margin_tax_type_next;
                }
                else if ( name.equals("margin_sys_acc_open_div") ) {
                    return this.margin_sys_acc_open_div;
                }
                else if ( name.equals("margin_gen_acc_open_div") ) {
                    return this.margin_gen_acc_open_div;
                }
                else if ( name.equals("margin_sp_acc_open_date") ) {
                    return this.margin_sp_acc_open_date;
                }
                else if ( name.equals("mrf_acc_open_div") ) {
                    return this.mrf_acc_open_div;
                }
                else if ( name.equals("mb_off_last_updater") ) {
                    return this.mb_off_last_updater;
                }
                else if ( name.equals("mb_off_last_updated_timestamp") ) {
                    return this.mb_off_last_updated_timestamp;
                }
                else if ( name.equals("mrf_fund_code") ) {
                    return this.mrf_fund_code;
                }
                else if ( name.equals("margin_acc_open_date") ) {
                    return this.margin_acc_open_date;
                }
                break;
            case 'n':
                if ( name.equals("new_account_div") ) {
                    return this.new_account_div;
                }
                else if ( name.equals("ntt_law") ) {
                    return this.ntt_law;
                }
                break;
            case 'o':
                if ( name.equals("office") ) {
                    return this.office;
                }
                else if ( name.equals("office_zip_code") ) {
                    return this.office_zip_code;
                }
                else if ( name.equals("office_address") ) {
                    return this.office_address;
                }
                else if ( name.equals("office_telephone") ) {
                    return this.office_telephone;
                }
                else if ( name.equals("order_permission") ) {
                    return this.order_permission;
                }
                else if ( name.equals("only_mobile_open_div") ) {
                    return this.only_mobile_open_div;
                }
                else if ( name.equals("only_mbl_opn_div_last_updater") ) {
                    return this.only_mbl_opn_div_last_updater;
                }
                else if ( name.equals("only_mbl_opn_div_timestamp") ) {
                    return this.only_mbl_opn_div_timestamp;
                }
                else if ( name.equals("order_exe_flag") ) {
                    return this.order_exe_flag;
                }
                else if ( name.equals("order_unexe_flag") ) {
                    return this.order_unexe_flag;
                }
                else if ( name.equals("org_deposit_div") ) {
                    return this.org_deposit_div;
                }
                break;
            case 'p':
                if ( name.equals("post") ) {
                    return this.post;
                }
                else if ( name.equals("person_identify") ) {
                    return this.person_identify;
                }
                else if ( name.equals("position_report_div") ) {
                    return this.position_report_div;
                }
                else if ( name.equals("position_report_cycle_div") ) {
                    return this.position_report_cycle_div;
                }
                else if ( name.equals("proam_div") ) {
                    return this.proam_div;
                }
                else if ( name.equals("pts_acc_open_div") ) {
                    return this.pts_acc_open_div;
                }
                else if ( name.equals("pts_acc_open_div_last_updater") ) {
                    return this.pts_acc_open_div_last_updater;
                }
                else if ( name.equals("pts_acc_open_div_timestamp") ) {
                    return this.pts_acc_open_div_timestamp;
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
                if ( name.equals("resident") ) {
                    return this.resident;
                }
                else if ( name.equals("ruito_acc_open_div") ) {
                    return this.ruito_acc_open_div;
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code") ) {
                    return this.sonar_trader_code;
                }
                else if ( name.equals("sub_zip_code") ) {
                    return this.sub_zip_code;
                }
                else if ( name.equals("sex") ) {
                    return this.sex;
                }
                else if ( name.equals("sp_mng_acc_open_div") ) {
                    return this.sp_mng_acc_open_div;
                }
                else if ( name.equals("stock_option_acc_open_div") ) {
                    return this.stock_option_acc_open_div;
                }
                else if ( name.equals("secured_loan_sec_acc_open_div") ) {
                    return this.secured_loan_sec_acc_open_div;
                }
                else if ( name.equals("standing_proxy") ) {
                    return this.standing_proxy;
                }
                else if ( name.equals("statutory_agent") ) {
                    return this.statutory_agent;
                }
                break;
            case 't':
                if ( name.equals("telephone") ) {
                    return this.telephone;
                }
                else if ( name.equals("trading_password") ) {
                    return this.trading_password;
                }
                else if ( name.equals("tax_type") ) {
                    return this.tax_type;
                }
                else if ( name.equals("tax_type_next") ) {
                    return this.tax_type_next;
                }
                else if ( name.equals("transfer_count") ) {
                    return this.transfer_count;
                }
                else if ( name.equals("top_page_id") ) {
                    return this.top_page_id;
                }
                else if ( name.equals("trading_report_div") ) {
                    return this.trading_report_div;
                }
                else if ( name.equals("trading_password_updater") ) {
                    return this.trading_password_updater;
                }
                else if ( name.equals("tr_pwd_last_update_timestamp") ) {
                    return this.tr_pwd_last_update_timestamp;
                }
                break;
            case 'v':
                if ( name.equals("via_trust_bank_div") ) {
                    return this.via_trust_bank_div;
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
                if ( name.equals("account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = ((Long) value).longValue();
                    if (this.account_id_is_set)
                        this.account_id_is_modified = true;
                    this.account_id_is_set = true;
                    return;
                }
                else if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("account_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum) )
                        throw new IllegalArgumentException( "value for 'account_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum: '"+value+"' is not." );
                    this.account_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum) value;
                    if (this.account_type_is_set)
                        this.account_type_is_modified = true;
                    this.account_type_is_set = true;
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
                else if ( name.equals("account_open_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'account_open_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.account_open_date = (java.sql.Timestamp) value;
                    if (this.account_open_date_is_set)
                        this.account_open_date_is_modified = true;
                    this.account_open_date_is_set = true;
                    return;
                }
                else if ( name.equals("account_close_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'account_close_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.account_close_date = (java.sql.Timestamp) value;
                    if (this.account_close_date_is_set)
                        this.account_close_date_is_modified = true;
                    this.account_close_date_is_set = true;
                    return;
                }
                else if ( name.equals("acc_open_send_mail_status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acc_open_send_mail_status' must be String: '"+value+"' is not." );
                    this.acc_open_send_mail_status = (String) value;
                    if (this.acc_open_send_mail_status_is_set)
                        this.acc_open_send_mail_status_is_modified = true;
                    this.acc_open_send_mail_status_is_set = true;
                    return;
                }
                else if ( name.equals("account_status") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum) )
                        throw new IllegalArgumentException( "value for 'account_status' must be com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum: '"+value+"' is not." );
                    this.account_status = (com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum) value;
                    if (this.account_status_is_set)
                        this.account_status_is_modified = true;
                    this.account_status_is_set = true;
                    return;
                }
                else if ( name.equals("account_statement_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'account_statement_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.account_statement_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
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
                if ( name.equals("branch_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'branch_id' must be Long: '"+value+"' is not." );
                    this.branch_id = ((Long) value).longValue();
                    if (this.branch_id_is_set)
                        this.branch_id_is_modified = true;
                    this.branch_id_is_set = true;
                    return;
                }
                else if ( name.equals("branch_code") ) {
                    if ( value != null )
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
                else if ( name.equals("bank_account_regi") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bank_account_regi' must be String: '"+value+"' is not." );
                    this.bank_account_regi = (String) value;
                    if (this.bank_account_regi_is_set)
                        this.bank_account_regi_is_modified = true;
                    this.bank_account_regi_is_set = true;
                    return;
                }
                else if ( name.equals("branch_lock") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_lock' must be String: '"+value+"' is not." );
                    this.branch_lock = (String) value;
                    if (this.branch_lock_is_set)
                        this.branch_lock_is_modified = true;
                    this.branch_lock_is_set = true;
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
                if ( name.equals("class_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'class_div' must be String: '"+value+"' is not." );
                    this.class_div = (String) value;
                    if (this.class_div_is_set)
                        this.class_div_is_modified = true;
                    this.class_div_is_set = true;
                    return;
                }
                else if ( name.equals("certificate_deposit_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'certificate_deposit_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.certificate_deposit_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.certificate_deposit_flag_is_set)
                        this.certificate_deposit_flag_is_modified = true;
                    this.certificate_deposit_flag_is_set = true;
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
                else if ( name.equals("cfd_acc_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cfd_acc_open_div' must be String: '"+value+"' is not." );
                    this.cfd_acc_open_div = (String) value;
                    if (this.cfd_acc_open_div_is_set)
                        this.cfd_acc_open_div_is_modified = true;
                    this.cfd_acc_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("cfd_acc_open_div_last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'cfd_acc_open_div_last_updater' must be String: '"+value+"' is not." );
                    this.cfd_acc_open_div_last_updater = (String) value;
                    if (this.cfd_acc_open_div_last_updater_is_set)
                        this.cfd_acc_open_div_last_updater_is_modified = true;
                    this.cfd_acc_open_div_last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("cfd_acc_open_updated_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'cfd_acc_open_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.cfd_acc_open_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.cfd_acc_open_updated_timestamp_is_set)
                        this.cfd_acc_open_updated_timestamp_is_modified = true;
                    this.cfd_acc_open_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("dividend_trans_designate") ) {
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
                if ( name.equals("equity_order_exe_mail_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'equity_order_exe_mail_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.equity_order_exe_mail_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.equity_order_exe_mail_flag_is_set)
                        this.equity_order_exe_mail_flag_is_modified = true;
                    this.equity_order_exe_mail_flag_is_set = true;
                    return;
                }
                else if ( name.equals("equity_order_unexec_mail_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'equity_order_unexec_mail_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.equity_order_unexec_mail_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.equity_order_unexec_mail_flag_is_set)
                        this.equity_order_unexec_mail_flag_is_modified = true;
                    this.equity_order_unexec_mail_flag_is_set = true;
                    return;
                }
                else if ( name.equals("email_address") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'email_address' must be String: '"+value+"' is not." );
                    this.email_address = (String) value;
                    if (this.email_address_is_set)
                        this.email_address_is_modified = true;
                    this.email_address_is_set = true;
                    return;
                }
                else if ( name.equals("email_address_alt1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'email_address_alt1' must be String: '"+value+"' is not." );
                    this.email_address_alt1 = (String) value;
                    if (this.email_address_alt1_is_set)
                        this.email_address_alt1_is_modified = true;
                    this.email_address_alt1_is_set = true;
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
                else if ( name.equals("examin_lock_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'examin_lock_flag' must be String: '"+value+"' is not." );
                    this.examin_lock_flag = (String) value;
                    if (this.examin_lock_flag_is_set)
                        this.examin_lock_flag_is_modified = true;
                    this.examin_lock_flag_is_set = true;
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
                else if ( name.equals("email_last_updater") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'email_last_updater' must be String: '"+value+"' is not." );
                    this.email_last_updater = (String) value;
                    if (this.email_last_updater_is_set)
                        this.email_last_updater_is_modified = true;
                    this.email_last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("email_last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'email_last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.email_last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.email_last_updated_timestamp_is_set)
                        this.email_last_updated_timestamp_is_modified = true;
                    this.email_last_updated_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("enable_order_last_updater") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'enable_order_last_updater' must be String: '"+value+"' is not." );
                    this.enable_order_last_updater = (String) value;
                    if (this.enable_order_last_updater_is_set)
                        this.enable_order_last_updater_is_modified = true;
                    this.enable_order_last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("enable_order_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'enable_order_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.enable_order_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.enable_order_updated_timestamp_is_set)
                        this.enable_order_updated_timestamp_is_modified = true;
                    this.enable_order_updated_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("eq_exe_ml_flg_last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'eq_exe_ml_flg_last_updater' must be String: '"+value+"' is not." );
                    this.eq_exe_ml_flg_last_updater = (String) value;
                    if (this.eq_exe_ml_flg_last_updater_is_set)
                        this.eq_exe_ml_flg_last_updater_is_modified = true;
                    this.eq_exe_ml_flg_last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("eq_exe_ml_flg_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'eq_exe_ml_flg_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.eq_exe_ml_flg_timestamp = (java.sql.Timestamp) value;
                    if (this.eq_exe_ml_flg_timestamp_is_set)
                        this.eq_exe_ml_flg_timestamp_is_modified = true;
                    this.eq_exe_ml_flg_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("eq_unexe_ml_flg_last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'eq_unexe_ml_flg_last_updater' must be String: '"+value+"' is not." );
                    this.eq_unexe_ml_flg_last_updater = (String) value;
                    if (this.eq_unexe_ml_flg_last_updater_is_set)
                        this.eq_unexe_ml_flg_last_updater_is_modified = true;
                    this.eq_unexe_ml_flg_last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("eq_unexe_ml_flg_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'eq_unexe_ml_flg_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.eq_unexe_ml_flg_timestamp = (java.sql.Timestamp) value;
                    if (this.eq_unexe_ml_flg_timestamp_is_set)
                        this.eq_unexe_ml_flg_timestamp_is_modified = true;
                    this.eq_unexe_ml_flg_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("email_address_alt2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'email_address_alt2' must be String: '"+value+"' is not." );
                    this.email_address_alt2 = (String) value;
                    if (this.email_address_alt2_is_set)
                        this.email_address_alt2_is_modified = true;
                    this.email_address_alt2_is_set = true;
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
                break;
            case 'f':
                if ( name.equals("family_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'family_name' must be String: '"+value+"' is not." );
                    this.family_name = (String) value;
                    if (this.family_name_is_set)
                        this.family_name_is_modified = true;
                    this.family_name_is_set = true;
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
                else if ( name.equals("family_name_alt2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'family_name_alt2' must be String: '"+value+"' is not." );
                    this.family_name_alt2 = (String) value;
                    if (this.family_name_alt2_is_set)
                        this.family_name_alt2_is_modified = true;
                    this.family_name_alt2_is_set = true;
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
                else if ( name.equals("foreign_sec_acc_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'foreign_sec_acc_open_div' must be String: '"+value+"' is not." );
                    this.foreign_sec_acc_open_div = (String) value;
                    if (this.foreign_sec_acc_open_div_is_set)
                        this.foreign_sec_acc_open_div_is_modified = true;
                    this.foreign_sec_acc_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("fx_acc_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fx_acc_open_div' must be String: '"+value+"' is not." );
                    this.fx_acc_open_div = (String) value;
                    if (this.fx_acc_open_div_is_set)
                        this.fx_acc_open_div_is_modified = true;
                    this.fx_acc_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("feq_con_acc_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'feq_con_acc_open_div' must be String: '"+value+"' is not." );
                    this.feq_con_acc_open_div = (String) value;
                    if (this.feq_con_acc_open_div_is_set)
                        this.feq_con_acc_open_div_is_modified = true;
                    this.feq_con_acc_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("fx_acc_open_div_last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fx_acc_open_div_last_updater' must be String: '"+value+"' is not." );
                    this.fx_acc_open_div_last_updater = (String) value;
                    if (this.fx_acc_open_div_last_updater_is_set)
                        this.fx_acc_open_div_last_updater_is_modified = true;
                    this.fx_acc_open_div_last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("fx_acc_open_updated_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'fx_acc_open_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.fx_acc_open_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.fx_acc_open_updated_timestamp_is_set)
                        this.fx_acc_open_updated_timestamp_is_modified = true;
                    this.fx_acc_open_updated_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("feq_con_acc_open_div_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'feq_con_acc_open_div_updater' must be String: '"+value+"' is not." );
                    this.feq_con_acc_open_div_updater = (String) value;
                    if (this.feq_con_acc_open_div_updater_is_set)
                        this.feq_con_acc_open_div_updater_is_modified = true;
                    this.feq_con_acc_open_div_updater_is_set = true;
                    return;
                }
                else if ( name.equals("feq_con_acc_open_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'feq_con_acc_open_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.feq_con_acc_open_timestamp = (java.sql.Timestamp) value;
                    if (this.feq_con_acc_open_timestamp_is_set)
                        this.feq_con_acc_open_timestamp_is_modified = true;
                    this.feq_con_acc_open_timestamp_is_set = true;
                    return;
                }
                break;
            case 'g':
                if ( name.equals("given_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'given_name' must be String: '"+value+"' is not." );
                    this.given_name = (String) value;
                    if (this.given_name_is_set)
                        this.given_name_is_modified = true;
                    this.given_name_is_set = true;
                    return;
                }
                else if ( name.equals("given_name_alt1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'given_name_alt1' must be String: '"+value+"' is not." );
                    this.given_name_alt1 = (String) value;
                    if (this.given_name_alt1_is_set)
                        this.given_name_alt1_is_modified = true;
                    this.given_name_alt1_is_set = true;
                    return;
                }
                else if ( name.equals("given_name_alt2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'given_name_alt2' must be String: '"+value+"' is not." );
                    this.given_name_alt2 = (String) value;
                    if (this.given_name_alt2_is_set)
                        this.given_name_alt2_is_modified = true;
                    this.given_name_alt2_is_set = true;
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
                else if ( name.equals("institution_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'institution_id' must be Long: '"+value+"' is not." );
                    this.institution_id = ((Long) value).longValue();
                    if (this.institution_id_is_set)
                        this.institution_id_is_modified = true;
                    this.institution_id_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_order_exec_mail_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'ifo_order_exec_mail_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.ifo_order_exec_mail_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.ifo_order_exec_mail_flag_is_set)
                        this.ifo_order_exec_mail_flag_is_modified = true;
                    this.ifo_order_exec_mail_flag_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_order_unexec_mail_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'ifo_order_unexec_mail_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.ifo_order_unexec_mail_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.ifo_order_unexec_mail_flag_is_set)
                        this.ifo_order_unexec_mail_flag_is_modified = true;
                    this.ifo_order_unexec_mail_flag_is_set = true;
                    return;
                }
                else if ( name.equals("information_mail_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'information_mail_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.information_mail_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.information_mail_flag_is_set)
                        this.information_mail_flag_is_modified = true;
                    this.information_mail_flag_is_set = true;
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
                else if ( name.equals("inf_mail_flg_last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'inf_mail_flg_last_updater' must be String: '"+value+"' is not." );
                    this.inf_mail_flg_last_updater = (String) value;
                    if (this.inf_mail_flg_last_updater_is_set)
                        this.inf_mail_flg_last_updater_is_modified = true;
                    this.inf_mail_flg_last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("inf_mail_flg_updated_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'inf_mail_flg_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.inf_mail_flg_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.inf_mail_flg_updated_timestamp_is_set)
                        this.inf_mail_flg_updated_timestamp_is_modified = true;
                    this.inf_mail_flg_updated_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_exe_ml_flg_last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_exe_ml_flg_last_updater' must be String: '"+value+"' is not." );
                    this.ifo_exe_ml_flg_last_updater = (String) value;
                    if (this.ifo_exe_ml_flg_last_updater_is_set)
                        this.ifo_exe_ml_flg_last_updater_is_modified = true;
                    this.ifo_exe_ml_flg_last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_exe_ml_flg_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'ifo_exe_ml_flg_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.ifo_exe_ml_flg_timestamp = (java.sql.Timestamp) value;
                    if (this.ifo_exe_ml_flg_timestamp_is_set)
                        this.ifo_exe_ml_flg_timestamp_is_modified = true;
                    this.ifo_exe_ml_flg_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_unexe_ml_flg_last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_unexe_ml_flg_last_updater' must be String: '"+value+"' is not." );
                    this.ifo_unexe_ml_flg_last_updater = (String) value;
                    if (this.ifo_unexe_ml_flg_last_updater_is_set)
                        this.ifo_unexe_ml_flg_last_updater_is_modified = true;
                    this.ifo_unexe_ml_flg_last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_unexe_ml_flg_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'ifo_unexe_ml_flg_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.ifo_unexe_ml_flg_timestamp = (java.sql.Timestamp) value;
                    if (this.ifo_unexe_ml_flg_timestamp_is_set)
                        this.ifo_unexe_ml_flg_timestamp_is_modified = true;
                    this.ifo_unexe_ml_flg_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_acc_open_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'ifo_acc_open_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.ifo_acc_open_date = (java.sql.Timestamp) value;
                    if (this.ifo_acc_open_date_is_set)
                        this.ifo_acc_open_date_is_modified = true;
                    this.ifo_acc_open_date_is_set = true;
                    return;
                }
                else if ( name.equals("important_connection_mail_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'important_connection_mail_flag' must be Integer: '"+value+"' is not." );
                    this.important_connection_mail_flag = (Integer) value;
                    if (this.important_connection_mail_flag_is_set)
                        this.important_connection_mail_flag_is_modified = true;
                    this.important_connection_mail_flag_is_set = true;
                    return;
                }
                else if ( name.equals("information_mail2_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'information_mail2_flag' must be Integer: '"+value+"' is not." );
                    this.information_mail2_flag = (Integer) value;
                    if (this.information_mail2_flag_is_set)
                        this.information_mail2_flag_is_modified = true;
                    this.information_mail2_flag_is_set = true;
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
                else if ( name.equals("lock_registration_reason") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'lock_registration_reason' must be String: '"+value+"' is not." );
                    this.lock_registration_reason = (String) value;
                    if (this.lock_registration_reason_is_set)
                        this.lock_registration_reason_is_modified = true;
                    this.lock_registration_reason_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("middle_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'middle_name' must be String: '"+value+"' is not." );
                    this.middle_name = (String) value;
                    if (this.middle_name_is_set)
                        this.middle_name_is_modified = true;
                    this.middle_name_is_set = true;
                    return;
                }
                else if ( name.equals("middle_name_alt1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'middle_name_alt1' must be String: '"+value+"' is not." );
                    this.middle_name_alt1 = (String) value;
                    if (this.middle_name_alt1_is_set)
                        this.middle_name_alt1_is_modified = true;
                    this.middle_name_alt1_is_set = true;
                    return;
                }
                else if ( name.equals("middle_name_alt2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'middle_name_alt2' must be String: '"+value+"' is not." );
                    this.middle_name_alt2 = (String) value;
                    if (this.middle_name_alt2_is_set)
                        this.middle_name_alt2_is_modified = true;
                    this.middle_name_alt2_is_set = true;
                    return;
                }
                else if ( name.equals("mobile") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mobile' must be String: '"+value+"' is not." );
                    this.mobile = (String) value;
                    if (this.mobile_is_set)
                        this.mobile_is_modified = true;
                    this.mobile_is_set = true;
                    return;
                }
                else if ( name.equals("mng_lock_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mng_lock_flag' must be String: '"+value+"' is not." );
                    this.mng_lock_flag = (String) value;
                    if (this.mng_lock_flag_is_set)
                        this.mng_lock_flag_is_modified = true;
                    this.mng_lock_flag_is_set = true;
                    return;
                }
                else if ( name.equals("mng_lock_off_start_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'mng_lock_off_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.mng_lock_off_start_date = (java.sql.Timestamp) value;
                    if (this.mng_lock_off_start_date_is_set)
                        this.mng_lock_off_start_date_is_modified = true;
                    this.mng_lock_off_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("mng_lock_off_end_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'mng_lock_off_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.mng_lock_off_end_date = (java.sql.Timestamp) value;
                    if (this.mng_lock_off_end_date_is_set)
                        this.mng_lock_off_end_date_is_modified = true;
                    this.mng_lock_off_end_date_is_set = true;
                    return;
                }
                else if ( name.equals("mng_lock_flag_advance") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'mng_lock_flag_advance' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.mng_lock_flag_advance = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.mng_lock_flag_advance_is_set)
                        this.mng_lock_flag_advance_is_modified = true;
                    this.mng_lock_flag_advance_is_set = true;
                    return;
                }
                else if ( name.equals("mng_lock_flag_unpay_margin") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'mng_lock_flag_unpay_margin' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.mng_lock_flag_unpay_margin = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.mng_lock_flag_unpay_margin_is_set)
                        this.mng_lock_flag_unpay_margin_is_modified = true;
                    this.mng_lock_flag_unpay_margin_is_set = true;
                    return;
                }
                else if ( name.equals("mng_lock_flag_short_security") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'mng_lock_flag_short_security' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.mng_lock_flag_short_security = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.mng_lock_flag_short_security_is_set)
                        this.mng_lock_flag_short_security_is_modified = true;
                    this.mng_lock_flag_short_security_is_set = true;
                    return;
                }
                else if ( name.equals("mng_lock_flag_unsubstit_depo") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'mng_lock_flag_unsubstit_depo' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.mng_lock_flag_unsubstit_depo = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.mng_lock_flag_unsubstit_depo_is_set)
                        this.mng_lock_flag_unsubstit_depo_is_modified = true;
                    this.mng_lock_flag_unsubstit_depo_is_set = true;
                    return;
                }
                else if ( name.equals("margin_tax_type") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) )
                        throw new IllegalArgumentException( "value for 'margin_tax_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum: '"+value+"' is not." );
                    this.margin_tax_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) value;
                    if (this.margin_tax_type_is_set)
                        this.margin_tax_type_is_modified = true;
                    this.margin_tax_type_is_set = true;
                    return;
                }
                else if ( name.equals("margin_tax_type_next") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) )
                        throw new IllegalArgumentException( "value for 'margin_tax_type_next' must be com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum: '"+value+"' is not." );
                    this.margin_tax_type_next = (com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) value;
                    if (this.margin_tax_type_next_is_set)
                        this.margin_tax_type_next_is_modified = true;
                    this.margin_tax_type_next_is_set = true;
                    return;
                }
                else if ( name.equals("margin_sys_acc_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_sys_acc_open_div' must be String: '"+value+"' is not." );
                    this.margin_sys_acc_open_div = (String) value;
                    if (this.margin_sys_acc_open_div_is_set)
                        this.margin_sys_acc_open_div_is_modified = true;
                    this.margin_sys_acc_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("margin_gen_acc_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_gen_acc_open_div' must be String: '"+value+"' is not." );
                    this.margin_gen_acc_open_div = (String) value;
                    if (this.margin_gen_acc_open_div_is_set)
                        this.margin_gen_acc_open_div_is_modified = true;
                    this.margin_gen_acc_open_div_is_set = true;
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
                else if ( name.equals("mrf_acc_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mrf_acc_open_div' must be String: '"+value+"' is not." );
                    this.mrf_acc_open_div = (String) value;
                    if (this.mrf_acc_open_div_is_set)
                        this.mrf_acc_open_div_is_modified = true;
                    this.mrf_acc_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("mb_off_last_updater") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mb_off_last_updater' must be String: '"+value+"' is not." );
                    this.mb_off_last_updater = (String) value;
                    if (this.mb_off_last_updater_is_set)
                        this.mb_off_last_updater_is_modified = true;
                    this.mb_off_last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("mb_off_last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'mb_off_last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.mb_off_last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.mb_off_last_updated_timestamp_is_set)
                        this.mb_off_last_updated_timestamp_is_modified = true;
                    this.mb_off_last_updated_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("mrf_fund_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mrf_fund_code' must be String: '"+value+"' is not." );
                    this.mrf_fund_code = (String) value;
                    if (this.mrf_fund_code_is_set)
                        this.mrf_fund_code_is_modified = true;
                    this.mrf_fund_code_is_set = true;
                    return;
                }
                else if ( name.equals("margin_acc_open_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'margin_acc_open_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.margin_acc_open_date = (java.sql.Timestamp) value;
                    if (this.margin_acc_open_date_is_set)
                        this.margin_acc_open_date_is_modified = true;
                    this.margin_acc_open_date_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("new_account_div") ) {
                    if ( value != null )
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
                if ( name.equals("office") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'office' must be String: '"+value+"' is not." );
                    this.office = (String) value;
                    if (this.office_is_set)
                        this.office_is_modified = true;
                    this.office_is_set = true;
                    return;
                }
                else if ( name.equals("office_zip_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'office_zip_code' must be String: '"+value+"' is not." );
                    this.office_zip_code = (String) value;
                    if (this.office_zip_code_is_set)
                        this.office_zip_code_is_modified = true;
                    this.office_zip_code_is_set = true;
                    return;
                }
                else if ( name.equals("office_address") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'office_address' must be String: '"+value+"' is not." );
                    this.office_address = (String) value;
                    if (this.office_address_is_set)
                        this.office_address_is_modified = true;
                    this.office_address_is_set = true;
                    return;
                }
                else if ( name.equals("office_telephone") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'office_telephone' must be String: '"+value+"' is not." );
                    this.office_telephone = (String) value;
                    if (this.office_telephone_is_set)
                        this.office_telephone_is_modified = true;
                    this.office_telephone_is_set = true;
                    return;
                }
                else if ( name.equals("order_permission") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_permission' must be String: '"+value+"' is not." );
                    this.order_permission = (String) value;
                    if (this.order_permission_is_set)
                        this.order_permission_is_modified = true;
                    this.order_permission_is_set = true;
                    return;
                }
                else if ( name.equals("only_mobile_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'only_mobile_open_div' must be String: '"+value+"' is not." );
                    this.only_mobile_open_div = (String) value;
                    if (this.only_mobile_open_div_is_set)
                        this.only_mobile_open_div_is_modified = true;
                    this.only_mobile_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("only_mbl_opn_div_last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'only_mbl_opn_div_last_updater' must be String: '"+value+"' is not." );
                    this.only_mbl_opn_div_last_updater = (String) value;
                    if (this.only_mbl_opn_div_last_updater_is_set)
                        this.only_mbl_opn_div_last_updater_is_modified = true;
                    this.only_mbl_opn_div_last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("only_mbl_opn_div_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'only_mbl_opn_div_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.only_mbl_opn_div_timestamp = (java.sql.Timestamp) value;
                    if (this.only_mbl_opn_div_timestamp_is_set)
                        this.only_mbl_opn_div_timestamp_is_modified = true;
                    this.only_mbl_opn_div_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("order_exe_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'order_exe_flag' must be Integer: '"+value+"' is not." );
                    this.order_exe_flag = (Integer) value;
                    if (this.order_exe_flag_is_set)
                        this.order_exe_flag_is_modified = true;
                    this.order_exe_flag_is_set = true;
                    return;
                }
                else if ( name.equals("order_unexe_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'order_unexe_flag' must be Integer: '"+value+"' is not." );
                    this.order_unexe_flag = (Integer) value;
                    if (this.order_unexe_flag_is_set)
                        this.order_unexe_flag_is_modified = true;
                    this.order_unexe_flag_is_set = true;
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
                if ( name.equals("post") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'post' must be String: '"+value+"' is not." );
                    this.post = (String) value;
                    if (this.post_is_set)
                        this.post_is_modified = true;
                    this.post_is_set = true;
                    return;
                }
                else if ( name.equals("person_identify") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'person_identify' must be String: '"+value+"' is not." );
                    this.person_identify = (String) value;
                    if (this.person_identify_is_set)
                        this.person_identify_is_modified = true;
                    this.person_identify_is_set = true;
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
                else if ( name.equals("position_report_cycle_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'position_report_cycle_div' must be String: '"+value+"' is not." );
                    this.position_report_cycle_div = (String) value;
                    if (this.position_report_cycle_div_is_set)
                        this.position_report_cycle_div_is_modified = true;
                    this.position_report_cycle_div_is_set = true;
                    return;
                }
                else if ( name.equals("proam_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'proam_div' must be String: '"+value+"' is not." );
                    this.proam_div = (String) value;
                    if (this.proam_div_is_set)
                        this.proam_div_is_modified = true;
                    this.proam_div_is_set = true;
                    return;
                }
                else if ( name.equals("pts_acc_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pts_acc_open_div' must be String: '"+value+"' is not." );
                    this.pts_acc_open_div = (String) value;
                    if (this.pts_acc_open_div_is_set)
                        this.pts_acc_open_div_is_modified = true;
                    this.pts_acc_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("pts_acc_open_div_last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'pts_acc_open_div_last_updater' must be String: '"+value+"' is not." );
                    this.pts_acc_open_div_last_updater = (String) value;
                    if (this.pts_acc_open_div_last_updater_is_set)
                        this.pts_acc_open_div_last_updater_is_modified = true;
                    this.pts_acc_open_div_last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("pts_acc_open_div_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'pts_acc_open_div_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.pts_acc_open_div_timestamp = (java.sql.Timestamp) value;
                    if (this.pts_acc_open_div_timestamp_is_set)
                        this.pts_acc_open_div_timestamp_is_modified = true;
                    this.pts_acc_open_div_timestamp_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("qualified_inst_investor_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'qualified_inst_investor_div' must be String: '"+value+"' is not." );
                    this.qualified_inst_investor_div = (String) value;
                    if (this.qualified_inst_investor_div_is_set)
                        this.qualified_inst_investor_div_is_modified = true;
                    this.qualified_inst_investor_div_is_set = true;
                    return;
                }
                else if ( name.equals("quoto_type") ) {
                    if ( value != null )
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
                if ( name.equals("resident") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'resident' must be String: '"+value+"' is not." );
                    this.resident = (String) value;
                    if (this.resident_is_set)
                        this.resident_is_modified = true;
                    this.resident_is_set = true;
                    return;
                }
                else if ( name.equals("ruito_acc_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ruito_acc_open_div' must be String: '"+value+"' is not." );
                    this.ruito_acc_open_div = (String) value;
                    if (this.ruito_acc_open_div_is_set)
                        this.ruito_acc_open_div_is_modified = true;
                    this.ruito_acc_open_div_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_trader_code' must be String: '"+value+"' is not." );
                    this.sonar_trader_code = (String) value;
                    if (this.sonar_trader_code_is_set)
                        this.sonar_trader_code_is_modified = true;
                    this.sonar_trader_code_is_set = true;
                    return;
                }
                else if ( name.equals("sub_zip_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sub_zip_code' must be String: '"+value+"' is not." );
                    this.sub_zip_code = (String) value;
                    if (this.sub_zip_code_is_set)
                        this.sub_zip_code_is_modified = true;
                    this.sub_zip_code_is_set = true;
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
                else if ( name.equals("stock_option_acc_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stock_option_acc_open_div' must be String: '"+value+"' is not." );
                    this.stock_option_acc_open_div = (String) value;
                    if (this.stock_option_acc_open_div_is_set)
                        this.stock_option_acc_open_div_is_modified = true;
                    this.stock_option_acc_open_div_is_set = true;
                    return;
                }
                else if ( name.equals("secured_loan_sec_acc_open_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'secured_loan_sec_acc_open_div' must be String: '"+value+"' is not." );
                    this.secured_loan_sec_acc_open_div = (String) value;
                    if (this.secured_loan_sec_acc_open_div_is_set)
                        this.secured_loan_sec_acc_open_div_is_modified = true;
                    this.secured_loan_sec_acc_open_div_is_set = true;
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
                if ( name.equals("telephone") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'telephone' must be String: '"+value+"' is not." );
                    this.telephone = (String) value;
                    if (this.telephone_is_set)
                        this.telephone_is_modified = true;
                    this.telephone_is_set = true;
                    return;
                }
                else if ( name.equals("trading_password") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trading_password' must be String: '"+value+"' is not." );
                    this.trading_password = (String) value;
                    if (this.trading_password_is_set)
                        this.trading_password_is_modified = true;
                    this.trading_password_is_set = true;
                    return;
                }
                else if ( name.equals("tax_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) )
                        throw new IllegalArgumentException( "value for 'tax_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum: '"+value+"' is not." );
                    this.tax_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) value;
                    if (this.tax_type_is_set)
                        this.tax_type_is_modified = true;
                    this.tax_type_is_set = true;
                    return;
                }
                else if ( name.equals("tax_type_next") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) )
                        throw new IllegalArgumentException( "value for 'tax_type_next' must be com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum: '"+value+"' is not." );
                    this.tax_type_next = (com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum) value;
                    if (this.tax_type_next_is_set)
                        this.tax_type_next_is_modified = true;
                    this.tax_type_next_is_set = true;
                    return;
                }
                else if ( name.equals("transfer_count") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'transfer_count' must be Integer: '"+value+"' is not." );
                    this.transfer_count = (Integer) value;
                    if (this.transfer_count_is_set)
                        this.transfer_count_is_modified = true;
                    this.transfer_count_is_set = true;
                    return;
                }
                else if ( name.equals("top_page_id") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'top_page_id' must be String: '"+value+"' is not." );
                    this.top_page_id = (String) value;
                    if (this.top_page_id_is_set)
                        this.top_page_id_is_modified = true;
                    this.top_page_id_is_set = true;
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
                else if ( name.equals("trading_password_updater") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trading_password_updater' must be String: '"+value+"' is not." );
                    this.trading_password_updater = (String) value;
                    if (this.trading_password_updater_is_set)
                        this.trading_password_updater_is_modified = true;
                    this.trading_password_updater_is_set = true;
                    return;
                }
                else if ( name.equals("tr_pwd_last_update_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'tr_pwd_last_update_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.tr_pwd_last_update_timestamp = (java.sql.Timestamp) value;
                    if (this.tr_pwd_last_update_timestamp_is_set)
                        this.tr_pwd_last_update_timestamp_is_modified = true;
                    this.tr_pwd_last_update_timestamp_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("via_trust_bank_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'via_trust_bank_div' must be String: '"+value+"' is not." );
                    this.via_trust_bank_div = (String) value;
                    if (this.via_trust_bank_div_is_set)
                        this.via_trust_bank_div_is_modified = true;
                    this.via_trust_bank_div_is_set = true;
                    return;
                }
                break;
            case 'y':
                if ( name.equals("yellow_customer") ) {
                    if ( value != null )
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
