head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.22.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	ExpAccountOpenTempParams.java;


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
 * exp_account_open_tempテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link ExpAccountOpenTempRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link ExpAccountOpenTempParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link ExpAccountOpenTempParams}が{@@link ExpAccountOpenTempRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see ExpAccountOpenTempPK 
 * @@see ExpAccountOpenTempRow 
 */
public class ExpAccountOpenTempParams extends Params implements ExpAccountOpenTempRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "exp_account_open_temp";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = ExpAccountOpenTempRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return ExpAccountOpenTempRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>acc_open_request_number</em>カラムの値 
   */
  public  String  acc_open_request_number;

  /** 
   * <em>institution_id</em>カラムの値 
   */
  public  long  institution_id;

  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  long  branch_id;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>sonar_trader_code</em>カラムの値 
   */
  public  String  sonar_trader_code;

  /** 
   * <em>ex_account_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  ex_account_flag;

  /** 
   * <em>ex_branch_name</em>カラムの値 
   */
  public  String  ex_branch_name;

  /** 
   * <em>ex_account_code</em>カラムの値 
   */
  public  String  ex_account_code;

  /** 
   * <em>account_div</em>カラムの値 
   */
  public  String  account_div;

  /** 
   * <em>order_div</em>カラムの値 
   */
  public  String  order_div;

  /** 
   * <em>infomation_claim_datetime</em>カラムの値 
   */
  public  java.sql.Timestamp  infomation_claim_datetime;

  /** 
   * <em>account_open_date</em>カラムの値 
   */
  public  java.sql.Timestamp  account_open_date;

  /** 
   * <em>initial_password</em>カラムの値 
   */
  public  String  initial_password;

  /** 
   * <em>family_name</em>カラムの値 
   */
  public  String  family_name;

  /** 
   * <em>given_name</em>カラムの値 
   */
  public  String  given_name;

  /** 
   * <em>family_name_alt1</em>カラムの値 
   */
  public  String  family_name_alt1;

  /** 
   * <em>given_name_alt1</em>カラムの値 
   */
  public  String  given_name_alt1;

  /** 
   * <em>sex</em>カラムの値 
   */
  public  String  sex;

  /** 
   * <em>era_born</em>カラムの値 
   */
  public  String  era_born;

  /** 
   * <em>born_date</em>カラムの値 
   */
  public  String  born_date;

  /** 
   * <em>email_address</em>カラムの値 
   */
  public  String  email_address;

  /** 
   * <em>email_address_alt1</em>カラムの値 
   */
  public  String  email_address_alt1;

  /** 
   * <em>zip_code</em>カラムの値 
   */
  public  String  zip_code;

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
   * <em>occupation_div</em>カラムの値 
   */
  public  String  occupation_div;

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
   * <em>office_fax</em>カラムの値 
   */
  public  String  office_fax;

  /** 
   * <em>department</em>カラムの値 
   */
  public  String  department;

  /** 
   * <em>post</em>カラムの値 
   */
  public  String  post;

  /** 
   * <em>contact_address</em>カラムの値 
   */
  public  String  contact_address;

  /** 
   * <em>contact_telephone</em>カラムの値 
   */
  public  String  contact_telephone;

  /** 
   * <em>family_relationship</em>カラムの値 
   */
  public  String  family_relationship;

  /** 
   * <em>family_relationship_etc</em>カラムの値 
   */
  public  String  family_relationship_etc;

  /** 
   * <em>householder</em>カラムの値 
   */
  public  String  householder;

  /** 
   * <em>householder_kana</em>カラムの値 
   */
  public  String  householder_kana;

  /** 
   * <em>householder_occupation_div</em>カラムの値 
   */
  public  String  householder_occupation_div;

  /** 
   * <em>householder_office</em>カラムの値 
   */
  public  String  householder_office;

  /** 
   * <em>householder_office_address</em>カラムの値 
   */
  public  String  householder_office_address;

  /** 
   * <em>householder_department</em>カラムの値 
   */
  public  String  householder_department;

  /** 
   * <em>householder_office_tel</em>カラムの値 
   */
  public  String  householder_office_tel;

  /** 
   * <em>householder_office_fax</em>カラムの値 
   */
  public  String  householder_office_fax;

  /** 
   * <em>householder_post</em>カラムの値 
   */
  public  String  householder_post;

  /** 
   * <em>resident</em>カラムの値 
   */
  public  String  resident;

  /** 
   * <em>transfer_div</em>カラムの値 
   */
  public  String  transfer_div;

  /** 
   * <em>fin_institution_code</em>カラムの値 
   */
  public  String  fin_institution_code;

  /** 
   * <em>fin_institution_name</em>カラムの値 
   */
  public  String  fin_institution_name;

  /** 
   * <em>fin_branch_code</em>カラムの値 
   */
  public  String  fin_branch_code;

  /** 
   * <em>fin_branch_name</em>カラムの値 
   */
  public  String  fin_branch_name;

  /** 
   * <em>fin_save_div</em>カラムの値 
   */
  public  String  fin_save_div;

  /** 
   * <em>fin_account_no</em>カラムの値 
   */
  public  String  fin_account_no;

  /** 
   * <em>postal_save_code</em>カラムの値 
   */
  public  String  postal_save_code;

  /** 
   * <em>postal_save_no</em>カラムの値 
   */
  public  String  postal_save_no;

  /** 
   * <em>fin_account_name</em>カラムの値 
   */
  public  String  fin_account_name;

  /** 
   * <em>trans_commission</em>カラムの値 
   */
  public  String  trans_commission;

  /** 
   * <em>experience_div_equity</em>カラムの値 
   */
  public  String  experience_div_equity;

  /** 
   * <em>experience_div_margin</em>カラムの値 
   */
  public  String  experience_div_margin;

  /** 
   * <em>experience_div_bond</em>カラムの値 
   */
  public  String  experience_div_bond;

  /** 
   * <em>experience_div_wt</em>カラムの値 
   */
  public  String  experience_div_wt;

  /** 
   * <em>experience_div_fund_sk</em>カラムの値 
   */
  public  String  experience_div_fund_sk;

  /** 
   * <em>experience_div_fund_bd</em>カラムの値 
   */
  public  String  experience_div_fund_bd;

  /** 
   * <em>experience_div_fo</em>カラムの値 
   */
  public  String  experience_div_fo;

  /** 
   * <em>experience_div_f_equity</em>カラムの値 
   */
  public  String  experience_div_f_equity;

  /** 
   * <em>experience_div_etc</em>カラムの値 
   */
  public  String  experience_div_etc;

  /** 
   * <em>experience_flag_equity</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  experience_flag_equity;

  /** 
   * <em>experience_flag_margin</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  experience_flag_margin;

  /** 
   * <em>experience_flag_bond</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  experience_flag_bond;

  /** 
   * <em>experience_flag_wt</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  experience_flag_wt;

  /** 
   * <em>experience_flag_fund_sk</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  experience_flag_fund_sk;

  /** 
   * <em>experience_flag_fund_bd</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  experience_flag_fund_bd;

  /** 
   * <em>experience_flag_fo</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  experience_flag_fo;

  /** 
   * <em>experience_flag_f_equity</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  experience_flag_f_equity;

  /** 
   * <em>experience_flag_etc</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  experience_flag_etc;

  /** 
   * <em>experience_from</em>カラムの値 
   */
  public  String  experience_from;

  /** 
   * <em>experience_to</em>カラムの値 
   */
  public  String  experience_to;

  /** 
   * <em>interest_flag_equity</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  interest_flag_equity;

  /** 
   * <em>interest_flag_ministock</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  interest_flag_ministock;

  /** 
   * <em>interest_flag_margin</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  interest_flag_margin;

  /** 
   * <em>interest_flag_bond</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  interest_flag_bond;

  /** 
   * <em>interest_flag_fund</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  interest_flag_fund;

  /** 
   * <em>interest_flag_fo</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  interest_flag_fo;

  /** 
   * <em>interest_flag_f_equity</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  interest_flag_f_equity;

  /** 
   * <em>interest_flag_etc</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  interest_flag_etc;

  /** 
   * <em>invest_purpose_div</em>カラムの値 
   */
  public  String  invest_purpose_div;

  /** 
   * <em>appli_motivat_div</em>カラムの値 
   */
  public  String  appli_motivat_div;

  /** 
   * <em>annual_income_div</em>カラムの値 
   */
  public  String  annual_income_div;

  /** 
   * <em>annual_income_from</em>カラムの値 
   */
  public  String  annual_income_from;

  /** 
   * <em>annual_income_to</em>カラムの値 
   */
  public  String  annual_income_to;

  /** 
   * <em>asset_value_div</em>カラムの値 
   */
  public  String  asset_value_div;

  /** 
   * <em>asset_value_from</em>カラムの値 
   */
  public  String  asset_value_from;

  /** 
   * <em>asset_value_to</em>カラムの値 
   */
  public  String  asset_value_to;

  /** 
   * <em>fund_budget_amount_div</em>カラムの値 
   */
  public  String  fund_budget_amount_div;

  /** 
   * <em>fund_budget_div</em>カラムの値 
   */
  public  String  fund_budget_div;

  /** 
   * <em>fund_budget_etc</em>カラムの値 
   */
  public  String  fund_budget_etc;

  /** 
   * <em>id_confirm_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  id_confirm_flag;

  /** 
   * <em>id_confirm_doc_div</em>カラムの値 
   */
  public  String  id_confirm_doc_div;

  /** 
   * <em>id_confirm_doc_etc</em>カラムの値 
   */
  public  String  id_confirm_doc_etc;

  /** 
   * <em>special_acc</em>カラムの値 
   */
  public  String  special_acc;

  /** 
   * <em>special_acc_margin</em>カラムの値 
   */
  public  String  special_acc_margin;

  /** 
   * <em>insider_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  insider_flag;

  /** 
   * <em>product_name</em>カラムの値 
   */
  public  String  product_name;

  /** 
   * <em>send_zip_code</em>カラムの値 
   */
  public  String  send_zip_code;

  /** 
   * <em>send_address_line1</em>カラムの値 
   */
  public  String  send_address_line1;

  /** 
   * <em>send_address_line2</em>カラムの値 
   */
  public  String  send_address_line2;

  /** 
   * <em>send_address_line3</em>カラムの値 
   */
  public  String  send_address_line3;

  /** 
   * <em>ext_item_div1</em>カラムの値 
   */
  public  String  ext_item_div1;

  /** 
   * <em>ext_item_div2</em>カラムの値 
   */
  public  String  ext_item_div2;

  /** 
   * <em>ext_item_div3</em>カラムの値 
   */
  public  String  ext_item_div3;

  /** 
   * <em>ext_item_div4</em>カラムの値 
   */
  public  String  ext_item_div4;

  /** 
   * <em>ext_item_div5</em>カラムの値 
   */
  public  String  ext_item_div5;

  /** 
   * <em>ext_item_div6</em>カラムの値 
   */
  public  String  ext_item_div6;

  /** 
   * <em>ext_item_div7</em>カラムの値 
   */
  public  String  ext_item_div7;

  /** 
   * <em>ext_item_div8</em>カラムの値 
   */
  public  String  ext_item_div8;

  /** 
   * <em>ext_item_div9</em>カラムの値 
   */
  public  String  ext_item_div9;

  /** 
   * <em>ext_item_div10</em>カラムの値 
   */
  public  String  ext_item_div10;

  /** 
   * <em>ext_item_flag1</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  ext_item_flag1;

  /** 
   * <em>ext_item_flag2</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  ext_item_flag2;

  /** 
   * <em>ext_item_flag3</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  ext_item_flag3;

  /** 
   * <em>ext_item_flag4</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  ext_item_flag4;

  /** 
   * <em>ext_item_flag5</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  ext_item_flag5;

  /** 
   * <em>ext_item_flag6</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  ext_item_flag6;

  /** 
   * <em>ext_item_flag7</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  ext_item_flag7;

  /** 
   * <em>ext_item_flag8</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  ext_item_flag8;

  /** 
   * <em>ext_item_flag9</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  ext_item_flag9;

  /** 
   * <em>ext_item_flag10</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  ext_item_flag10;

  /** 
   * <em>ext_item_text1</em>カラムの値 
   */
  public  String  ext_item_text1;

  /** 
   * <em>ext_item_text2</em>カラムの値 
   */
  public  String  ext_item_text2;

  /** 
   * <em>ext_item_text3</em>カラムの値 
   */
  public  String  ext_item_text3;

  /** 
   * <em>ext_item_text4</em>カラムの値 
   */
  public  String  ext_item_text4;

  /** 
   * <em>ext_item_text5</em>カラムの値 
   */
  public  String  ext_item_text5;

  /** 
   * <em>ext_item_text6</em>カラムの値 
   */
  public  String  ext_item_text6;

  /** 
   * <em>ext_item_text7</em>カラムの値 
   */
  public  String  ext_item_text7;

  /** 
   * <em>ext_item_text8</em>カラムの値 
   */
  public  String  ext_item_text8;

  /** 
   * <em>ext_item_text9</em>カラムの値 
   */
  public  String  ext_item_text9;

  /** 
   * <em>ext_item_text10</em>カラムの値 
   */
  public  String  ext_item_text10;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>creator</em>カラムの値 
   */
  public  String  creator;

  /** 
   * <em>exclusive_use_account_no</em>カラムの値 
   */
  public  String  exclusive_use_account_no;

  /** 
   * <em>send_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  send_timestamp;

  /** 
   * <em>real_name_voucher_div</em>カラムの値 
   */
  public  String  real_name_voucher_div;

  /** 
   * <em>real_name1</em>カラムの値 
   */
  public  String  real_name1;

  /** 
   * <em>real_name2</em>カラムの値 
   */
  public  String  real_name2;

  /** 
   * <em>insider_voucher_div</em>カラムの値 
   */
  public  String  insider_voucher_div;

  /** 
   * <em>insider_product_code</em>カラムの値 
   */
  public  String  insider_product_code;

  /** 
   * <em>insider_relation_div</em>カラムの値 
   */
  public  String  insider_relation_div;

  /** 
   * <em>insider_officer_name</em>カラムの値 
   */
  public  String  insider_officer_name;

  /** 
   * <em>insider_post_code</em>カラムの値 
   */
  public  String  insider_post_code;

  /** 
   * <em>insider_post_name</em>カラムの値 
   */
  public  String  insider_post_name;

  /** 
   * <em>gp_voucher_div</em>カラムの値 
   */
  public  String  gp_voucher_div;

  /** 
   * <em>gp_course</em>カラムの値 
   */
  public  String  gp_course;

  /** 
   * <em>gp_plan</em>カラムの値 
   */
  public  String  gp_plan;

  /** 
   * <em>gp_target_figure</em>カラムの値 
   */
  public  String  gp_target_figure;

  /** 
   * <em>gp_target_year</em>カラムの値 
   */
  public  String  gp_target_year;

  /** 
   * <em>gp_target_month</em>カラムの値 
   */
  public  String  gp_target_month;

  /** 
   * <em>gp_installment_figure</em>カラムの値 
   */
  public  String  gp_installment_figure;

  /** 
   * <em>gp_deposit_cycle</em>カラムの値 
   */
  public  String  gp_deposit_cycle;

  /** 
   * <em>gp_payment_root</em>カラムの値 
   */
  public  String  gp_payment_root;

  /** 
   * <em>gp_reinvest_div</em>カラムの値 
   */
  public  String  gp_reinvest_div;

  /** 
   * <em>gp_tax_div</em>カラムの値 
   */
  public  String  gp_tax_div;

  /** 
   * <em>gp_taxfree_limit</em>カラムの値 
   */
  public  String  gp_taxfree_limit;

  /** 
   * <em>gp_special_taxfree_limit</em>カラムの値 
   */
  public  String  gp_special_taxfree_limit;

  /** 
   * <em>gp_subscr_summary</em>カラムの値 
   */
  public  String  gp_subscr_summary;

  /** 
   * <em>gp_product_code</em>カラムの値 
   */
  public  String  gp_product_code;

  /** 
   * <em>gp_mortgage_customer</em>カラムの値 
   */
  public  String  gp_mortgage_customer;

  /** 
   * <em>gp_mix_customer</em>カラムの値 
   */
  public  String  gp_mix_customer;

  /** 
   * <em>gp_contract</em>カラムの値 
   */
  public  String  gp_contract;

  /** 
   * <em>stk_voucher_div</em>カラムの値 
   */
  public  String  stk_voucher_div;

  /** 
   * <em>stk_taxation_tran_div</em>カラムの値 
   */
  public  String  stk_taxation_tran_div;

  /** 
   * <em>stk_address_line_kana</em>カラムの値 
   */
  public  String  stk_address_line_kana;

  /** 
   * <em>stk_transfer_div</em>カラムの値 
   */
  public  String  stk_transfer_div;

  /** 
   * <em>stk_fin_institution_code</em>カラムの値 
   */
  public  String  stk_fin_institution_code;

  /** 
   * <em>stk_fin_branch_code</em>カラムの値 
   */
  public  String  stk_fin_branch_code;

  /** 
   * <em>stk_fin_save_div</em>カラムの値 
   */
  public  String  stk_fin_save_div;

  /** 
   * <em>stk_fin_account_no</em>カラムの値 
   */
  public  String  stk_fin_account_no;

  /** 
   * <em>brokerage_trader_code</em>カラムの値 
   */
  public  String  brokerage_trader_code;

  /** 
   * <em>ext_item_div11</em>カラムの値 
   */
  public  String  ext_item_div11;

  /** 
   * <em>ext_item_div12</em>カラムの値 
   */
  public  String  ext_item_div12;

  /** 
   * <em>ext_item_div13</em>カラムの値 
   */
  public  String  ext_item_div13;

  /** 
   * <em>ext_item_div14</em>カラムの値 
   */
  public  String  ext_item_div14;

  /** 
   * <em>ext_item_div15</em>カラムの値 
   */
  public  String  ext_item_div15;

  /** 
   * <em>foreign_account_no</em>カラムの値 
   */
  public  String  foreign_account_no;

  /** 
   * <em>foreign_account_name</em>カラムの値 
   */
  public  String  foreign_account_name;

  /** 
   * <em>foreign_account_name_eng</em>カラムの値 
   */
  public  String  foreign_account_name_eng;

  /** 
   * <em>foreign_save_div</em>カラムの値 
   */
  public  String  foreign_save_div;

  /** 
   * <em>delete_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  delete_flag;

  /** 
   * <em>delete_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  delete_timestamp;

  /** 
   * <em>print_flag</em>カラムの値 
   */
  public  String  print_flag;

  /** 
   * <em>receipt_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  receipt_flag;

  /** 
   * <em>agreement_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  agreement_flag;

  /** 
   * <em>foreign_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  foreign_flag;

  /** 
   * <em>agency_acc_name_kana1</em>カラムの値 
   */
  public  String  agency_acc_name_kana1;

  /** 
   * <em>agency_acc_name_kana2</em>カラムの値 
   */
  public  String  agency_acc_name_kana2;

  /** 
   * <em>agency_acc_name1</em>カラムの値 
   */
  public  String  agency_acc_name1;

  /** 
   * <em>agency_acc_name2</em>カラムの値 
   */
  public  String  agency_acc_name2;

  /** 
   * <em>agency_address_line1</em>カラムの値 
   */
  public  String  agency_address_line1;

  /** 
   * <em>agency_address_line2</em>カラムの値 
   */
  public  String  agency_address_line2;

  /** 
   * <em>agency_rep_post</em>カラムの値 
   */
  public  String  agency_rep_post;

  /** 
   * <em>agency_rep_name_kana1</em>カラムの値 
   */
  public  String  agency_rep_name_kana1;

  /** 
   * <em>agency_rep_name_kana2</em>カラムの値 
   */
  public  String  agency_rep_name_kana2;

  /** 
   * <em>agency_rep_name1</em>カラムの値 
   */
  public  String  agency_rep_name1;

  /** 
   * <em>agency_rep_name2</em>カラムの値 
   */
  public  String  agency_rep_name2;

  /** 
   * <em>status</em>カラムの値 
   */
  public  String  status;

  /** 
   * <em>error_info</em>カラムの値 
   */
  public  String  error_info;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean institution_id_is_set = false;
  private boolean institution_id_is_modified = false;
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean acc_open_request_number_is_set = false;
  private boolean acc_open_request_number_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean sonar_trader_code_is_set = false;
  private boolean sonar_trader_code_is_modified = false;
  private boolean ex_account_flag_is_set = false;
  private boolean ex_account_flag_is_modified = false;
  private boolean ex_branch_name_is_set = false;
  private boolean ex_branch_name_is_modified = false;
  private boolean ex_account_code_is_set = false;
  private boolean ex_account_code_is_modified = false;
  private boolean account_div_is_set = false;
  private boolean account_div_is_modified = false;
  private boolean order_div_is_set = false;
  private boolean order_div_is_modified = false;
  private boolean infomation_claim_datetime_is_set = false;
  private boolean infomation_claim_datetime_is_modified = false;
  private boolean account_open_date_is_set = false;
  private boolean account_open_date_is_modified = false;
  private boolean initial_password_is_set = false;
  private boolean initial_password_is_modified = false;
  private boolean family_name_is_set = false;
  private boolean family_name_is_modified = false;
  private boolean given_name_is_set = false;
  private boolean given_name_is_modified = false;
  private boolean family_name_alt1_is_set = false;
  private boolean family_name_alt1_is_modified = false;
  private boolean given_name_alt1_is_set = false;
  private boolean given_name_alt1_is_modified = false;
  private boolean sex_is_set = false;
  private boolean sex_is_modified = false;
  private boolean era_born_is_set = false;
  private boolean era_born_is_modified = false;
  private boolean born_date_is_set = false;
  private boolean born_date_is_modified = false;
  private boolean email_address_is_set = false;
  private boolean email_address_is_modified = false;
  private boolean email_address_alt1_is_set = false;
  private boolean email_address_alt1_is_modified = false;
  private boolean zip_code_is_set = false;
  private boolean zip_code_is_modified = false;
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
  private boolean occupation_div_is_set = false;
  private boolean occupation_div_is_modified = false;
  private boolean office_is_set = false;
  private boolean office_is_modified = false;
  private boolean office_zip_code_is_set = false;
  private boolean office_zip_code_is_modified = false;
  private boolean office_address_is_set = false;
  private boolean office_address_is_modified = false;
  private boolean office_telephone_is_set = false;
  private boolean office_telephone_is_modified = false;
  private boolean office_fax_is_set = false;
  private boolean office_fax_is_modified = false;
  private boolean department_is_set = false;
  private boolean department_is_modified = false;
  private boolean post_is_set = false;
  private boolean post_is_modified = false;
  private boolean contact_address_is_set = false;
  private boolean contact_address_is_modified = false;
  private boolean contact_telephone_is_set = false;
  private boolean contact_telephone_is_modified = false;
  private boolean family_relationship_is_set = false;
  private boolean family_relationship_is_modified = false;
  private boolean family_relationship_etc_is_set = false;
  private boolean family_relationship_etc_is_modified = false;
  private boolean householder_is_set = false;
  private boolean householder_is_modified = false;
  private boolean householder_kana_is_set = false;
  private boolean householder_kana_is_modified = false;
  private boolean householder_occupation_div_is_set = false;
  private boolean householder_occupation_div_is_modified = false;
  private boolean householder_office_is_set = false;
  private boolean householder_office_is_modified = false;
  private boolean householder_office_address_is_set = false;
  private boolean householder_office_address_is_modified = false;
  private boolean householder_department_is_set = false;
  private boolean householder_department_is_modified = false;
  private boolean householder_office_tel_is_set = false;
  private boolean householder_office_tel_is_modified = false;
  private boolean householder_office_fax_is_set = false;
  private boolean householder_office_fax_is_modified = false;
  private boolean householder_post_is_set = false;
  private boolean householder_post_is_modified = false;
  private boolean resident_is_set = false;
  private boolean resident_is_modified = false;
  private boolean transfer_div_is_set = false;
  private boolean transfer_div_is_modified = false;
  private boolean fin_institution_code_is_set = false;
  private boolean fin_institution_code_is_modified = false;
  private boolean fin_institution_name_is_set = false;
  private boolean fin_institution_name_is_modified = false;
  private boolean fin_branch_code_is_set = false;
  private boolean fin_branch_code_is_modified = false;
  private boolean fin_branch_name_is_set = false;
  private boolean fin_branch_name_is_modified = false;
  private boolean fin_save_div_is_set = false;
  private boolean fin_save_div_is_modified = false;
  private boolean fin_account_no_is_set = false;
  private boolean fin_account_no_is_modified = false;
  private boolean postal_save_code_is_set = false;
  private boolean postal_save_code_is_modified = false;
  private boolean postal_save_no_is_set = false;
  private boolean postal_save_no_is_modified = false;
  private boolean fin_account_name_is_set = false;
  private boolean fin_account_name_is_modified = false;
  private boolean trans_commission_is_set = false;
  private boolean trans_commission_is_modified = false;
  private boolean experience_div_equity_is_set = false;
  private boolean experience_div_equity_is_modified = false;
  private boolean experience_div_margin_is_set = false;
  private boolean experience_div_margin_is_modified = false;
  private boolean experience_div_bond_is_set = false;
  private boolean experience_div_bond_is_modified = false;
  private boolean experience_div_wt_is_set = false;
  private boolean experience_div_wt_is_modified = false;
  private boolean experience_div_fund_sk_is_set = false;
  private boolean experience_div_fund_sk_is_modified = false;
  private boolean experience_div_fund_bd_is_set = false;
  private boolean experience_div_fund_bd_is_modified = false;
  private boolean experience_div_fo_is_set = false;
  private boolean experience_div_fo_is_modified = false;
  private boolean experience_div_f_equity_is_set = false;
  private boolean experience_div_f_equity_is_modified = false;
  private boolean experience_div_etc_is_set = false;
  private boolean experience_div_etc_is_modified = false;
  private boolean experience_flag_equity_is_set = false;
  private boolean experience_flag_equity_is_modified = false;
  private boolean experience_flag_margin_is_set = false;
  private boolean experience_flag_margin_is_modified = false;
  private boolean experience_flag_bond_is_set = false;
  private boolean experience_flag_bond_is_modified = false;
  private boolean experience_flag_wt_is_set = false;
  private boolean experience_flag_wt_is_modified = false;
  private boolean experience_flag_fund_sk_is_set = false;
  private boolean experience_flag_fund_sk_is_modified = false;
  private boolean experience_flag_fund_bd_is_set = false;
  private boolean experience_flag_fund_bd_is_modified = false;
  private boolean experience_flag_fo_is_set = false;
  private boolean experience_flag_fo_is_modified = false;
  private boolean experience_flag_f_equity_is_set = false;
  private boolean experience_flag_f_equity_is_modified = false;
  private boolean experience_flag_etc_is_set = false;
  private boolean experience_flag_etc_is_modified = false;
  private boolean experience_from_is_set = false;
  private boolean experience_from_is_modified = false;
  private boolean experience_to_is_set = false;
  private boolean experience_to_is_modified = false;
  private boolean interest_flag_equity_is_set = false;
  private boolean interest_flag_equity_is_modified = false;
  private boolean interest_flag_ministock_is_set = false;
  private boolean interest_flag_ministock_is_modified = false;
  private boolean interest_flag_margin_is_set = false;
  private boolean interest_flag_margin_is_modified = false;
  private boolean interest_flag_bond_is_set = false;
  private boolean interest_flag_bond_is_modified = false;
  private boolean interest_flag_fund_is_set = false;
  private boolean interest_flag_fund_is_modified = false;
  private boolean interest_flag_fo_is_set = false;
  private boolean interest_flag_fo_is_modified = false;
  private boolean interest_flag_f_equity_is_set = false;
  private boolean interest_flag_f_equity_is_modified = false;
  private boolean interest_flag_etc_is_set = false;
  private boolean interest_flag_etc_is_modified = false;
  private boolean invest_purpose_div_is_set = false;
  private boolean invest_purpose_div_is_modified = false;
  private boolean appli_motivat_div_is_set = false;
  private boolean appli_motivat_div_is_modified = false;
  private boolean annual_income_div_is_set = false;
  private boolean annual_income_div_is_modified = false;
  private boolean annual_income_from_is_set = false;
  private boolean annual_income_from_is_modified = false;
  private boolean annual_income_to_is_set = false;
  private boolean annual_income_to_is_modified = false;
  private boolean asset_value_div_is_set = false;
  private boolean asset_value_div_is_modified = false;
  private boolean asset_value_from_is_set = false;
  private boolean asset_value_from_is_modified = false;
  private boolean asset_value_to_is_set = false;
  private boolean asset_value_to_is_modified = false;
  private boolean fund_budget_amount_div_is_set = false;
  private boolean fund_budget_amount_div_is_modified = false;
  private boolean fund_budget_div_is_set = false;
  private boolean fund_budget_div_is_modified = false;
  private boolean fund_budget_etc_is_set = false;
  private boolean fund_budget_etc_is_modified = false;
  private boolean id_confirm_flag_is_set = false;
  private boolean id_confirm_flag_is_modified = false;
  private boolean id_confirm_doc_div_is_set = false;
  private boolean id_confirm_doc_div_is_modified = false;
  private boolean id_confirm_doc_etc_is_set = false;
  private boolean id_confirm_doc_etc_is_modified = false;
  private boolean special_acc_is_set = false;
  private boolean special_acc_is_modified = false;
  private boolean special_acc_margin_is_set = false;
  private boolean special_acc_margin_is_modified = false;
  private boolean insider_flag_is_set = false;
  private boolean insider_flag_is_modified = false;
  private boolean product_name_is_set = false;
  private boolean product_name_is_modified = false;
  private boolean send_zip_code_is_set = false;
  private boolean send_zip_code_is_modified = false;
  private boolean send_address_line1_is_set = false;
  private boolean send_address_line1_is_modified = false;
  private boolean send_address_line2_is_set = false;
  private boolean send_address_line2_is_modified = false;
  private boolean send_address_line3_is_set = false;
  private boolean send_address_line3_is_modified = false;
  private boolean ext_item_div1_is_set = false;
  private boolean ext_item_div1_is_modified = false;
  private boolean ext_item_div2_is_set = false;
  private boolean ext_item_div2_is_modified = false;
  private boolean ext_item_div3_is_set = false;
  private boolean ext_item_div3_is_modified = false;
  private boolean ext_item_div4_is_set = false;
  private boolean ext_item_div4_is_modified = false;
  private boolean ext_item_div5_is_set = false;
  private boolean ext_item_div5_is_modified = false;
  private boolean ext_item_div6_is_set = false;
  private boolean ext_item_div6_is_modified = false;
  private boolean ext_item_div7_is_set = false;
  private boolean ext_item_div7_is_modified = false;
  private boolean ext_item_div8_is_set = false;
  private boolean ext_item_div8_is_modified = false;
  private boolean ext_item_div9_is_set = false;
  private boolean ext_item_div9_is_modified = false;
  private boolean ext_item_div10_is_set = false;
  private boolean ext_item_div10_is_modified = false;
  private boolean ext_item_flag1_is_set = false;
  private boolean ext_item_flag1_is_modified = false;
  private boolean ext_item_flag2_is_set = false;
  private boolean ext_item_flag2_is_modified = false;
  private boolean ext_item_flag3_is_set = false;
  private boolean ext_item_flag3_is_modified = false;
  private boolean ext_item_flag4_is_set = false;
  private boolean ext_item_flag4_is_modified = false;
  private boolean ext_item_flag5_is_set = false;
  private boolean ext_item_flag5_is_modified = false;
  private boolean ext_item_flag6_is_set = false;
  private boolean ext_item_flag6_is_modified = false;
  private boolean ext_item_flag7_is_set = false;
  private boolean ext_item_flag7_is_modified = false;
  private boolean ext_item_flag8_is_set = false;
  private boolean ext_item_flag8_is_modified = false;
  private boolean ext_item_flag9_is_set = false;
  private boolean ext_item_flag9_is_modified = false;
  private boolean ext_item_flag10_is_set = false;
  private boolean ext_item_flag10_is_modified = false;
  private boolean ext_item_text1_is_set = false;
  private boolean ext_item_text1_is_modified = false;
  private boolean ext_item_text2_is_set = false;
  private boolean ext_item_text2_is_modified = false;
  private boolean ext_item_text3_is_set = false;
  private boolean ext_item_text3_is_modified = false;
  private boolean ext_item_text4_is_set = false;
  private boolean ext_item_text4_is_modified = false;
  private boolean ext_item_text5_is_set = false;
  private boolean ext_item_text5_is_modified = false;
  private boolean ext_item_text6_is_set = false;
  private boolean ext_item_text6_is_modified = false;
  private boolean ext_item_text7_is_set = false;
  private boolean ext_item_text7_is_modified = false;
  private boolean ext_item_text8_is_set = false;
  private boolean ext_item_text8_is_modified = false;
  private boolean ext_item_text9_is_set = false;
  private boolean ext_item_text9_is_modified = false;
  private boolean ext_item_text10_is_set = false;
  private boolean ext_item_text10_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean creator_is_set = false;
  private boolean creator_is_modified = false;
  private boolean exclusive_use_account_no_is_set = false;
  private boolean exclusive_use_account_no_is_modified = false;
  private boolean send_timestamp_is_set = false;
  private boolean send_timestamp_is_modified = false;
  private boolean real_name_voucher_div_is_set = false;
  private boolean real_name_voucher_div_is_modified = false;
  private boolean real_name1_is_set = false;
  private boolean real_name1_is_modified = false;
  private boolean real_name2_is_set = false;
  private boolean real_name2_is_modified = false;
  private boolean insider_voucher_div_is_set = false;
  private boolean insider_voucher_div_is_modified = false;
  private boolean insider_product_code_is_set = false;
  private boolean insider_product_code_is_modified = false;
  private boolean insider_relation_div_is_set = false;
  private boolean insider_relation_div_is_modified = false;
  private boolean insider_officer_name_is_set = false;
  private boolean insider_officer_name_is_modified = false;
  private boolean insider_post_code_is_set = false;
  private boolean insider_post_code_is_modified = false;
  private boolean insider_post_name_is_set = false;
  private boolean insider_post_name_is_modified = false;
  private boolean gp_voucher_div_is_set = false;
  private boolean gp_voucher_div_is_modified = false;
  private boolean gp_course_is_set = false;
  private boolean gp_course_is_modified = false;
  private boolean gp_plan_is_set = false;
  private boolean gp_plan_is_modified = false;
  private boolean gp_target_figure_is_set = false;
  private boolean gp_target_figure_is_modified = false;
  private boolean gp_target_year_is_set = false;
  private boolean gp_target_year_is_modified = false;
  private boolean gp_target_month_is_set = false;
  private boolean gp_target_month_is_modified = false;
  private boolean gp_installment_figure_is_set = false;
  private boolean gp_installment_figure_is_modified = false;
  private boolean gp_deposit_cycle_is_set = false;
  private boolean gp_deposit_cycle_is_modified = false;
  private boolean gp_payment_root_is_set = false;
  private boolean gp_payment_root_is_modified = false;
  private boolean gp_reinvest_div_is_set = false;
  private boolean gp_reinvest_div_is_modified = false;
  private boolean gp_tax_div_is_set = false;
  private boolean gp_tax_div_is_modified = false;
  private boolean gp_taxfree_limit_is_set = false;
  private boolean gp_taxfree_limit_is_modified = false;
  private boolean gp_special_taxfree_limit_is_set = false;
  private boolean gp_special_taxfree_limit_is_modified = false;
  private boolean gp_subscr_summary_is_set = false;
  private boolean gp_subscr_summary_is_modified = false;
  private boolean gp_product_code_is_set = false;
  private boolean gp_product_code_is_modified = false;
  private boolean gp_mortgage_customer_is_set = false;
  private boolean gp_mortgage_customer_is_modified = false;
  private boolean gp_mix_customer_is_set = false;
  private boolean gp_mix_customer_is_modified = false;
  private boolean gp_contract_is_set = false;
  private boolean gp_contract_is_modified = false;
  private boolean stk_voucher_div_is_set = false;
  private boolean stk_voucher_div_is_modified = false;
  private boolean stk_taxation_tran_div_is_set = false;
  private boolean stk_taxation_tran_div_is_modified = false;
  private boolean stk_address_line_kana_is_set = false;
  private boolean stk_address_line_kana_is_modified = false;
  private boolean stk_transfer_div_is_set = false;
  private boolean stk_transfer_div_is_modified = false;
  private boolean stk_fin_institution_code_is_set = false;
  private boolean stk_fin_institution_code_is_modified = false;
  private boolean stk_fin_branch_code_is_set = false;
  private boolean stk_fin_branch_code_is_modified = false;
  private boolean stk_fin_save_div_is_set = false;
  private boolean stk_fin_save_div_is_modified = false;
  private boolean stk_fin_account_no_is_set = false;
  private boolean stk_fin_account_no_is_modified = false;
  private boolean brokerage_trader_code_is_set = false;
  private boolean brokerage_trader_code_is_modified = false;
  private boolean ext_item_div11_is_set = false;
  private boolean ext_item_div11_is_modified = false;
  private boolean ext_item_div12_is_set = false;
  private boolean ext_item_div12_is_modified = false;
  private boolean ext_item_div13_is_set = false;
  private boolean ext_item_div13_is_modified = false;
  private boolean ext_item_div14_is_set = false;
  private boolean ext_item_div14_is_modified = false;
  private boolean ext_item_div15_is_set = false;
  private boolean ext_item_div15_is_modified = false;
  private boolean foreign_account_no_is_set = false;
  private boolean foreign_account_no_is_modified = false;
  private boolean foreign_account_name_is_set = false;
  private boolean foreign_account_name_is_modified = false;
  private boolean foreign_account_name_eng_is_set = false;
  private boolean foreign_account_name_eng_is_modified = false;
  private boolean foreign_save_div_is_set = false;
  private boolean foreign_save_div_is_modified = false;
  private boolean delete_flag_is_set = false;
  private boolean delete_flag_is_modified = false;
  private boolean delete_timestamp_is_set = false;
  private boolean delete_timestamp_is_modified = false;
  private boolean print_flag_is_set = false;
  private boolean print_flag_is_modified = false;
  private boolean receipt_flag_is_set = false;
  private boolean receipt_flag_is_modified = false;
  private boolean agreement_flag_is_set = false;
  private boolean agreement_flag_is_modified = false;
  private boolean foreign_flag_is_set = false;
  private boolean foreign_flag_is_modified = false;
  private boolean agency_acc_name_kana1_is_set = false;
  private boolean agency_acc_name_kana1_is_modified = false;
  private boolean agency_acc_name_kana2_is_set = false;
  private boolean agency_acc_name_kana2_is_modified = false;
  private boolean agency_acc_name1_is_set = false;
  private boolean agency_acc_name1_is_modified = false;
  private boolean agency_acc_name2_is_set = false;
  private boolean agency_acc_name2_is_modified = false;
  private boolean agency_address_line1_is_set = false;
  private boolean agency_address_line1_is_modified = false;
  private boolean agency_address_line2_is_set = false;
  private boolean agency_address_line2_is_modified = false;
  private boolean agency_rep_post_is_set = false;
  private boolean agency_rep_post_is_modified = false;
  private boolean agency_rep_name_kana1_is_set = false;
  private boolean agency_rep_name_kana1_is_modified = false;
  private boolean agency_rep_name_kana2_is_set = false;
  private boolean agency_rep_name_kana2_is_modified = false;
  private boolean agency_rep_name1_is_set = false;
  private boolean agency_rep_name1_is_modified = false;
  private boolean agency_rep_name2_is_set = false;
  private boolean agency_rep_name2_is_modified = false;
  private boolean status_is_set = false;
  private boolean status_is_modified = false;
  private boolean error_info_is_set = false;
  private boolean error_info_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "acc_open_request_number=" + acc_open_request_number
      + "," + "institution_id=" +institution_id
      + "," + "branch_id=" +branch_id
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "sonar_trader_code=" +sonar_trader_code
      + "," + "ex_account_flag=" +ex_account_flag
      + "," + "ex_branch_name=" +ex_branch_name
      + "," + "ex_account_code=" +ex_account_code
      + "," + "account_div=" +account_div
      + "," + "order_div=" +order_div
      + "," + "infomation_claim_datetime=" +infomation_claim_datetime
      + "," + "account_open_date=" +account_open_date
      + "," + "initial_password=" +initial_password
      + "," + "family_name=" +family_name
      + "," + "given_name=" +given_name
      + "," + "family_name_alt1=" +family_name_alt1
      + "," + "given_name_alt1=" +given_name_alt1
      + "," + "sex=" +sex
      + "," + "era_born=" +era_born
      + "," + "born_date=" +born_date
      + "," + "email_address=" +email_address
      + "," + "email_address_alt1=" +email_address_alt1
      + "," + "zip_code=" +zip_code
      + "," + "address_line1=" +address_line1
      + "," + "address_line2=" +address_line2
      + "," + "address_line3=" +address_line3
      + "," + "address_line1_kana=" +address_line1_kana
      + "," + "address_line2_kana=" +address_line2_kana
      + "," + "address_line3_kana=" +address_line3_kana
      + "," + "telephone=" +telephone
      + "," + "mobile=" +mobile
      + "," + "fax=" +fax
      + "," + "occupation_div=" +occupation_div
      + "," + "office=" +office
      + "," + "office_zip_code=" +office_zip_code
      + "," + "office_address=" +office_address
      + "," + "office_telephone=" +office_telephone
      + "," + "office_fax=" +office_fax
      + "," + "department=" +department
      + "," + "post=" +post
      + "," + "contact_address=" +contact_address
      + "," + "contact_telephone=" +contact_telephone
      + "," + "family_relationship=" +family_relationship
      + "," + "family_relationship_etc=" +family_relationship_etc
      + "," + "householder=" +householder
      + "," + "householder_kana=" +householder_kana
      + "," + "householder_occupation_div=" +householder_occupation_div
      + "," + "householder_office=" +householder_office
      + "," + "householder_office_address=" +householder_office_address
      + "," + "householder_department=" +householder_department
      + "," + "householder_office_tel=" +householder_office_tel
      + "," + "householder_office_fax=" +householder_office_fax
      + "," + "householder_post=" +householder_post
      + "," + "resident=" +resident
      + "," + "transfer_div=" +transfer_div
      + "," + "fin_institution_code=" +fin_institution_code
      + "," + "fin_institution_name=" +fin_institution_name
      + "," + "fin_branch_code=" +fin_branch_code
      + "," + "fin_branch_name=" +fin_branch_name
      + "," + "fin_save_div=" +fin_save_div
      + "," + "fin_account_no=" +fin_account_no
      + "," + "postal_save_code=" +postal_save_code
      + "," + "postal_save_no=" +postal_save_no
      + "," + "fin_account_name=" +fin_account_name
      + "," + "trans_commission=" +trans_commission
      + "," + "experience_div_equity=" +experience_div_equity
      + "," + "experience_div_margin=" +experience_div_margin
      + "," + "experience_div_bond=" +experience_div_bond
      + "," + "experience_div_wt=" +experience_div_wt
      + "," + "experience_div_fund_sk=" +experience_div_fund_sk
      + "," + "experience_div_fund_bd=" +experience_div_fund_bd
      + "," + "experience_div_fo=" +experience_div_fo
      + "," + "experience_div_f_equity=" +experience_div_f_equity
      + "," + "experience_div_etc=" +experience_div_etc
      + "," + "experience_flag_equity=" +experience_flag_equity
      + "," + "experience_flag_margin=" +experience_flag_margin
      + "," + "experience_flag_bond=" +experience_flag_bond
      + "," + "experience_flag_wt=" +experience_flag_wt
      + "," + "experience_flag_fund_sk=" +experience_flag_fund_sk
      + "," + "experience_flag_fund_bd=" +experience_flag_fund_bd
      + "," + "experience_flag_fo=" +experience_flag_fo
      + "," + "experience_flag_f_equity=" +experience_flag_f_equity
      + "," + "experience_flag_etc=" +experience_flag_etc
      + "," + "experience_from=" +experience_from
      + "," + "experience_to=" +experience_to
      + "," + "interest_flag_equity=" +interest_flag_equity
      + "," + "interest_flag_ministock=" +interest_flag_ministock
      + "," + "interest_flag_margin=" +interest_flag_margin
      + "," + "interest_flag_bond=" +interest_flag_bond
      + "," + "interest_flag_fund=" +interest_flag_fund
      + "," + "interest_flag_fo=" +interest_flag_fo
      + "," + "interest_flag_f_equity=" +interest_flag_f_equity
      + "," + "interest_flag_etc=" +interest_flag_etc
      + "," + "invest_purpose_div=" +invest_purpose_div
      + "," + "appli_motivat_div=" +appli_motivat_div
      + "," + "annual_income_div=" +annual_income_div
      + "," + "annual_income_from=" +annual_income_from
      + "," + "annual_income_to=" +annual_income_to
      + "," + "asset_value_div=" +asset_value_div
      + "," + "asset_value_from=" +asset_value_from
      + "," + "asset_value_to=" +asset_value_to
      + "," + "fund_budget_amount_div=" +fund_budget_amount_div
      + "," + "fund_budget_div=" +fund_budget_div
      + "," + "fund_budget_etc=" +fund_budget_etc
      + "," + "id_confirm_flag=" +id_confirm_flag
      + "," + "id_confirm_doc_div=" +id_confirm_doc_div
      + "," + "id_confirm_doc_etc=" +id_confirm_doc_etc
      + "," + "special_acc=" +special_acc
      + "," + "special_acc_margin=" +special_acc_margin
      + "," + "insider_flag=" +insider_flag
      + "," + "product_name=" +product_name
      + "," + "send_zip_code=" +send_zip_code
      + "," + "send_address_line1=" +send_address_line1
      + "," + "send_address_line2=" +send_address_line2
      + "," + "send_address_line3=" +send_address_line3
      + "," + "ext_item_div1=" +ext_item_div1
      + "," + "ext_item_div2=" +ext_item_div2
      + "," + "ext_item_div3=" +ext_item_div3
      + "," + "ext_item_div4=" +ext_item_div4
      + "," + "ext_item_div5=" +ext_item_div5
      + "," + "ext_item_div6=" +ext_item_div6
      + "," + "ext_item_div7=" +ext_item_div7
      + "," + "ext_item_div8=" +ext_item_div8
      + "," + "ext_item_div9=" +ext_item_div9
      + "," + "ext_item_div10=" +ext_item_div10
      + "," + "ext_item_flag1=" +ext_item_flag1
      + "," + "ext_item_flag2=" +ext_item_flag2
      + "," + "ext_item_flag3=" +ext_item_flag3
      + "," + "ext_item_flag4=" +ext_item_flag4
      + "," + "ext_item_flag5=" +ext_item_flag5
      + "," + "ext_item_flag6=" +ext_item_flag6
      + "," + "ext_item_flag7=" +ext_item_flag7
      + "," + "ext_item_flag8=" +ext_item_flag8
      + "," + "ext_item_flag9=" +ext_item_flag9
      + "," + "ext_item_flag10=" +ext_item_flag10
      + "," + "ext_item_text1=" +ext_item_text1
      + "," + "ext_item_text2=" +ext_item_text2
      + "," + "ext_item_text3=" +ext_item_text3
      + "," + "ext_item_text4=" +ext_item_text4
      + "," + "ext_item_text5=" +ext_item_text5
      + "," + "ext_item_text6=" +ext_item_text6
      + "," + "ext_item_text7=" +ext_item_text7
      + "," + "ext_item_text8=" +ext_item_text8
      + "," + "ext_item_text9=" +ext_item_text9
      + "," + "ext_item_text10=" +ext_item_text10
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "creator=" +creator
      + "," + "exclusive_use_account_no=" +exclusive_use_account_no
      + "," + "send_timestamp=" +send_timestamp
      + "," + "real_name_voucher_div=" +real_name_voucher_div
      + "," + "real_name1=" +real_name1
      + "," + "real_name2=" +real_name2
      + "," + "insider_voucher_div=" +insider_voucher_div
      + "," + "insider_product_code=" +insider_product_code
      + "," + "insider_relation_div=" +insider_relation_div
      + "," + "insider_officer_name=" +insider_officer_name
      + "," + "insider_post_code=" +insider_post_code
      + "," + "insider_post_name=" +insider_post_name
      + "," + "gp_voucher_div=" +gp_voucher_div
      + "," + "gp_course=" +gp_course
      + "," + "gp_plan=" +gp_plan
      + "," + "gp_target_figure=" +gp_target_figure
      + "," + "gp_target_year=" +gp_target_year
      + "," + "gp_target_month=" +gp_target_month
      + "," + "gp_installment_figure=" +gp_installment_figure
      + "," + "gp_deposit_cycle=" +gp_deposit_cycle
      + "," + "gp_payment_root=" +gp_payment_root
      + "," + "gp_reinvest_div=" +gp_reinvest_div
      + "," + "gp_tax_div=" +gp_tax_div
      + "," + "gp_taxfree_limit=" +gp_taxfree_limit
      + "," + "gp_special_taxfree_limit=" +gp_special_taxfree_limit
      + "," + "gp_subscr_summary=" +gp_subscr_summary
      + "," + "gp_product_code=" +gp_product_code
      + "," + "gp_mortgage_customer=" +gp_mortgage_customer
      + "," + "gp_mix_customer=" +gp_mix_customer
      + "," + "gp_contract=" +gp_contract
      + "," + "stk_voucher_div=" +stk_voucher_div
      + "," + "stk_taxation_tran_div=" +stk_taxation_tran_div
      + "," + "stk_address_line_kana=" +stk_address_line_kana
      + "," + "stk_transfer_div=" +stk_transfer_div
      + "," + "stk_fin_institution_code=" +stk_fin_institution_code
      + "," + "stk_fin_branch_code=" +stk_fin_branch_code
      + "," + "stk_fin_save_div=" +stk_fin_save_div
      + "," + "stk_fin_account_no=" +stk_fin_account_no
      + "," + "brokerage_trader_code=" +brokerage_trader_code
      + "," + "ext_item_div11=" +ext_item_div11
      + "," + "ext_item_div12=" +ext_item_div12
      + "," + "ext_item_div13=" +ext_item_div13
      + "," + "ext_item_div14=" +ext_item_div14
      + "," + "ext_item_div15=" +ext_item_div15
      + "," + "foreign_account_no=" +foreign_account_no
      + "," + "foreign_account_name=" +foreign_account_name
      + "," + "foreign_account_name_eng=" +foreign_account_name_eng
      + "," + "foreign_save_div=" +foreign_save_div
      + "," + "delete_flag=" +delete_flag
      + "," + "delete_timestamp=" +delete_timestamp
      + "," + "print_flag=" +print_flag
      + "," + "receipt_flag=" +receipt_flag
      + "," + "agreement_flag=" +agreement_flag
      + "," + "foreign_flag=" +foreign_flag
      + "," + "agency_acc_name_kana1=" +agency_acc_name_kana1
      + "," + "agency_acc_name_kana2=" +agency_acc_name_kana2
      + "," + "agency_acc_name1=" +agency_acc_name1
      + "," + "agency_acc_name2=" +agency_acc_name2
      + "," + "agency_address_line1=" +agency_address_line1
      + "," + "agency_address_line2=" +agency_address_line2
      + "," + "agency_rep_post=" +agency_rep_post
      + "," + "agency_rep_name_kana1=" +agency_rep_name_kana1
      + "," + "agency_rep_name_kana2=" +agency_rep_name_kana2
      + "," + "agency_rep_name1=" +agency_rep_name1
      + "," + "agency_rep_name2=" +agency_rep_name2
      + "," + "status=" +status
      + "," + "error_info=" +error_info
      + "}";
  }


  /** 
   * 値が未設定のExpAccountOpenTempParamsオブジェクトを作成します。 
   */
  public ExpAccountOpenTempParams() {
    institution_code_is_modified = true;
    acc_open_request_number_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のExpAccountOpenTempRowオブジェクトの値を利用してExpAccountOpenTempParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するExpAccountOpenTempRowオブジェクト 
   */
  public ExpAccountOpenTempParams( ExpAccountOpenTempRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    acc_open_request_number = p_row.getAccOpenRequestNumber();
    acc_open_request_number_is_set = p_row.getAccOpenRequestNumberIsSet();
    acc_open_request_number_is_modified = p_row.getAccOpenRequestNumberIsModified();
    institution_id = p_row.getInstitutionId();
    institution_id_is_set = p_row.getInstitutionIdIsSet();
    institution_id_is_modified = p_row.getInstitutionIdIsModified();
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    sonar_trader_code = p_row.getSonarTraderCode();
    sonar_trader_code_is_set = p_row.getSonarTraderCodeIsSet();
    sonar_trader_code_is_modified = p_row.getSonarTraderCodeIsModified();
    ex_account_flag = p_row.getExAccountFlag();
    ex_account_flag_is_set = p_row.getExAccountFlagIsSet();
    ex_account_flag_is_modified = p_row.getExAccountFlagIsModified();
    ex_branch_name = p_row.getExBranchName();
    ex_branch_name_is_set = p_row.getExBranchNameIsSet();
    ex_branch_name_is_modified = p_row.getExBranchNameIsModified();
    ex_account_code = p_row.getExAccountCode();
    ex_account_code_is_set = p_row.getExAccountCodeIsSet();
    ex_account_code_is_modified = p_row.getExAccountCodeIsModified();
    account_div = p_row.getAccountDiv();
    account_div_is_set = p_row.getAccountDivIsSet();
    account_div_is_modified = p_row.getAccountDivIsModified();
    order_div = p_row.getOrderDiv();
    order_div_is_set = p_row.getOrderDivIsSet();
    order_div_is_modified = p_row.getOrderDivIsModified();
    infomation_claim_datetime = p_row.getInfomationClaimDatetime();
    infomation_claim_datetime_is_set = p_row.getInfomationClaimDatetimeIsSet();
    infomation_claim_datetime_is_modified = p_row.getInfomationClaimDatetimeIsModified();
    account_open_date = p_row.getAccountOpenDate();
    account_open_date_is_set = p_row.getAccountOpenDateIsSet();
    account_open_date_is_modified = p_row.getAccountOpenDateIsModified();
    initial_password = p_row.getInitialPassword();
    initial_password_is_set = p_row.getInitialPasswordIsSet();
    initial_password_is_modified = p_row.getInitialPasswordIsModified();
    family_name = p_row.getFamilyName();
    family_name_is_set = p_row.getFamilyNameIsSet();
    family_name_is_modified = p_row.getFamilyNameIsModified();
    given_name = p_row.getGivenName();
    given_name_is_set = p_row.getGivenNameIsSet();
    given_name_is_modified = p_row.getGivenNameIsModified();
    family_name_alt1 = p_row.getFamilyNameAlt1();
    family_name_alt1_is_set = p_row.getFamilyNameAlt1IsSet();
    family_name_alt1_is_modified = p_row.getFamilyNameAlt1IsModified();
    given_name_alt1 = p_row.getGivenNameAlt1();
    given_name_alt1_is_set = p_row.getGivenNameAlt1IsSet();
    given_name_alt1_is_modified = p_row.getGivenNameAlt1IsModified();
    sex = p_row.getSex();
    sex_is_set = p_row.getSexIsSet();
    sex_is_modified = p_row.getSexIsModified();
    era_born = p_row.getEraBorn();
    era_born_is_set = p_row.getEraBornIsSet();
    era_born_is_modified = p_row.getEraBornIsModified();
    born_date = p_row.getBornDate();
    born_date_is_set = p_row.getBornDateIsSet();
    born_date_is_modified = p_row.getBornDateIsModified();
    email_address = p_row.getEmailAddress();
    email_address_is_set = p_row.getEmailAddressIsSet();
    email_address_is_modified = p_row.getEmailAddressIsModified();
    email_address_alt1 = p_row.getEmailAddressAlt1();
    email_address_alt1_is_set = p_row.getEmailAddressAlt1IsSet();
    email_address_alt1_is_modified = p_row.getEmailAddressAlt1IsModified();
    zip_code = p_row.getZipCode();
    zip_code_is_set = p_row.getZipCodeIsSet();
    zip_code_is_modified = p_row.getZipCodeIsModified();
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
    occupation_div = p_row.getOccupationDiv();
    occupation_div_is_set = p_row.getOccupationDivIsSet();
    occupation_div_is_modified = p_row.getOccupationDivIsModified();
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
    office_fax = p_row.getOfficeFax();
    office_fax_is_set = p_row.getOfficeFaxIsSet();
    office_fax_is_modified = p_row.getOfficeFaxIsModified();
    department = p_row.getDepartment();
    department_is_set = p_row.getDepartmentIsSet();
    department_is_modified = p_row.getDepartmentIsModified();
    post = p_row.getPost();
    post_is_set = p_row.getPostIsSet();
    post_is_modified = p_row.getPostIsModified();
    contact_address = p_row.getContactAddress();
    contact_address_is_set = p_row.getContactAddressIsSet();
    contact_address_is_modified = p_row.getContactAddressIsModified();
    contact_telephone = p_row.getContactTelephone();
    contact_telephone_is_set = p_row.getContactTelephoneIsSet();
    contact_telephone_is_modified = p_row.getContactTelephoneIsModified();
    family_relationship = p_row.getFamilyRelationship();
    family_relationship_is_set = p_row.getFamilyRelationshipIsSet();
    family_relationship_is_modified = p_row.getFamilyRelationshipIsModified();
    family_relationship_etc = p_row.getFamilyRelationshipEtc();
    family_relationship_etc_is_set = p_row.getFamilyRelationshipEtcIsSet();
    family_relationship_etc_is_modified = p_row.getFamilyRelationshipEtcIsModified();
    householder = p_row.getHouseholder();
    householder_is_set = p_row.getHouseholderIsSet();
    householder_is_modified = p_row.getHouseholderIsModified();
    householder_kana = p_row.getHouseholderKana();
    householder_kana_is_set = p_row.getHouseholderKanaIsSet();
    householder_kana_is_modified = p_row.getHouseholderKanaIsModified();
    householder_occupation_div = p_row.getHouseholderOccupationDiv();
    householder_occupation_div_is_set = p_row.getHouseholderOccupationDivIsSet();
    householder_occupation_div_is_modified = p_row.getHouseholderOccupationDivIsModified();
    householder_office = p_row.getHouseholderOffice();
    householder_office_is_set = p_row.getHouseholderOfficeIsSet();
    householder_office_is_modified = p_row.getHouseholderOfficeIsModified();
    householder_office_address = p_row.getHouseholderOfficeAddress();
    householder_office_address_is_set = p_row.getHouseholderOfficeAddressIsSet();
    householder_office_address_is_modified = p_row.getHouseholderOfficeAddressIsModified();
    householder_department = p_row.getHouseholderDepartment();
    householder_department_is_set = p_row.getHouseholderDepartmentIsSet();
    householder_department_is_modified = p_row.getHouseholderDepartmentIsModified();
    householder_office_tel = p_row.getHouseholderOfficeTel();
    householder_office_tel_is_set = p_row.getHouseholderOfficeTelIsSet();
    householder_office_tel_is_modified = p_row.getHouseholderOfficeTelIsModified();
    householder_office_fax = p_row.getHouseholderOfficeFax();
    householder_office_fax_is_set = p_row.getHouseholderOfficeFaxIsSet();
    householder_office_fax_is_modified = p_row.getHouseholderOfficeFaxIsModified();
    householder_post = p_row.getHouseholderPost();
    householder_post_is_set = p_row.getHouseholderPostIsSet();
    householder_post_is_modified = p_row.getHouseholderPostIsModified();
    resident = p_row.getResident();
    resident_is_set = p_row.getResidentIsSet();
    resident_is_modified = p_row.getResidentIsModified();
    transfer_div = p_row.getTransferDiv();
    transfer_div_is_set = p_row.getTransferDivIsSet();
    transfer_div_is_modified = p_row.getTransferDivIsModified();
    fin_institution_code = p_row.getFinInstitutionCode();
    fin_institution_code_is_set = p_row.getFinInstitutionCodeIsSet();
    fin_institution_code_is_modified = p_row.getFinInstitutionCodeIsModified();
    fin_institution_name = p_row.getFinInstitutionName();
    fin_institution_name_is_set = p_row.getFinInstitutionNameIsSet();
    fin_institution_name_is_modified = p_row.getFinInstitutionNameIsModified();
    fin_branch_code = p_row.getFinBranchCode();
    fin_branch_code_is_set = p_row.getFinBranchCodeIsSet();
    fin_branch_code_is_modified = p_row.getFinBranchCodeIsModified();
    fin_branch_name = p_row.getFinBranchName();
    fin_branch_name_is_set = p_row.getFinBranchNameIsSet();
    fin_branch_name_is_modified = p_row.getFinBranchNameIsModified();
    fin_save_div = p_row.getFinSaveDiv();
    fin_save_div_is_set = p_row.getFinSaveDivIsSet();
    fin_save_div_is_modified = p_row.getFinSaveDivIsModified();
    fin_account_no = p_row.getFinAccountNo();
    fin_account_no_is_set = p_row.getFinAccountNoIsSet();
    fin_account_no_is_modified = p_row.getFinAccountNoIsModified();
    postal_save_code = p_row.getPostalSaveCode();
    postal_save_code_is_set = p_row.getPostalSaveCodeIsSet();
    postal_save_code_is_modified = p_row.getPostalSaveCodeIsModified();
    postal_save_no = p_row.getPostalSaveNo();
    postal_save_no_is_set = p_row.getPostalSaveNoIsSet();
    postal_save_no_is_modified = p_row.getPostalSaveNoIsModified();
    fin_account_name = p_row.getFinAccountName();
    fin_account_name_is_set = p_row.getFinAccountNameIsSet();
    fin_account_name_is_modified = p_row.getFinAccountNameIsModified();
    trans_commission = p_row.getTransCommission();
    trans_commission_is_set = p_row.getTransCommissionIsSet();
    trans_commission_is_modified = p_row.getTransCommissionIsModified();
    experience_div_equity = p_row.getExperienceDivEquity();
    experience_div_equity_is_set = p_row.getExperienceDivEquityIsSet();
    experience_div_equity_is_modified = p_row.getExperienceDivEquityIsModified();
    experience_div_margin = p_row.getExperienceDivMargin();
    experience_div_margin_is_set = p_row.getExperienceDivMarginIsSet();
    experience_div_margin_is_modified = p_row.getExperienceDivMarginIsModified();
    experience_div_bond = p_row.getExperienceDivBond();
    experience_div_bond_is_set = p_row.getExperienceDivBondIsSet();
    experience_div_bond_is_modified = p_row.getExperienceDivBondIsModified();
    experience_div_wt = p_row.getExperienceDivWt();
    experience_div_wt_is_set = p_row.getExperienceDivWtIsSet();
    experience_div_wt_is_modified = p_row.getExperienceDivWtIsModified();
    experience_div_fund_sk = p_row.getExperienceDivFundSk();
    experience_div_fund_sk_is_set = p_row.getExperienceDivFundSkIsSet();
    experience_div_fund_sk_is_modified = p_row.getExperienceDivFundSkIsModified();
    experience_div_fund_bd = p_row.getExperienceDivFundBd();
    experience_div_fund_bd_is_set = p_row.getExperienceDivFundBdIsSet();
    experience_div_fund_bd_is_modified = p_row.getExperienceDivFundBdIsModified();
    experience_div_fo = p_row.getExperienceDivFo();
    experience_div_fo_is_set = p_row.getExperienceDivFoIsSet();
    experience_div_fo_is_modified = p_row.getExperienceDivFoIsModified();
    experience_div_f_equity = p_row.getExperienceDivFEquity();
    experience_div_f_equity_is_set = p_row.getExperienceDivFEquityIsSet();
    experience_div_f_equity_is_modified = p_row.getExperienceDivFEquityIsModified();
    experience_div_etc = p_row.getExperienceDivEtc();
    experience_div_etc_is_set = p_row.getExperienceDivEtcIsSet();
    experience_div_etc_is_modified = p_row.getExperienceDivEtcIsModified();
    experience_flag_equity = p_row.getExperienceFlagEquity();
    experience_flag_equity_is_set = p_row.getExperienceFlagEquityIsSet();
    experience_flag_equity_is_modified = p_row.getExperienceFlagEquityIsModified();
    experience_flag_margin = p_row.getExperienceFlagMargin();
    experience_flag_margin_is_set = p_row.getExperienceFlagMarginIsSet();
    experience_flag_margin_is_modified = p_row.getExperienceFlagMarginIsModified();
    experience_flag_bond = p_row.getExperienceFlagBond();
    experience_flag_bond_is_set = p_row.getExperienceFlagBondIsSet();
    experience_flag_bond_is_modified = p_row.getExperienceFlagBondIsModified();
    experience_flag_wt = p_row.getExperienceFlagWt();
    experience_flag_wt_is_set = p_row.getExperienceFlagWtIsSet();
    experience_flag_wt_is_modified = p_row.getExperienceFlagWtIsModified();
    experience_flag_fund_sk = p_row.getExperienceFlagFundSk();
    experience_flag_fund_sk_is_set = p_row.getExperienceFlagFundSkIsSet();
    experience_flag_fund_sk_is_modified = p_row.getExperienceFlagFundSkIsModified();
    experience_flag_fund_bd = p_row.getExperienceFlagFundBd();
    experience_flag_fund_bd_is_set = p_row.getExperienceFlagFundBdIsSet();
    experience_flag_fund_bd_is_modified = p_row.getExperienceFlagFundBdIsModified();
    experience_flag_fo = p_row.getExperienceFlagFo();
    experience_flag_fo_is_set = p_row.getExperienceFlagFoIsSet();
    experience_flag_fo_is_modified = p_row.getExperienceFlagFoIsModified();
    experience_flag_f_equity = p_row.getExperienceFlagFEquity();
    experience_flag_f_equity_is_set = p_row.getExperienceFlagFEquityIsSet();
    experience_flag_f_equity_is_modified = p_row.getExperienceFlagFEquityIsModified();
    experience_flag_etc = p_row.getExperienceFlagEtc();
    experience_flag_etc_is_set = p_row.getExperienceFlagEtcIsSet();
    experience_flag_etc_is_modified = p_row.getExperienceFlagEtcIsModified();
    experience_from = p_row.getExperienceFrom();
    experience_from_is_set = p_row.getExperienceFromIsSet();
    experience_from_is_modified = p_row.getExperienceFromIsModified();
    experience_to = p_row.getExperienceTo();
    experience_to_is_set = p_row.getExperienceToIsSet();
    experience_to_is_modified = p_row.getExperienceToIsModified();
    interest_flag_equity = p_row.getInterestFlagEquity();
    interest_flag_equity_is_set = p_row.getInterestFlagEquityIsSet();
    interest_flag_equity_is_modified = p_row.getInterestFlagEquityIsModified();
    interest_flag_ministock = p_row.getInterestFlagMinistock();
    interest_flag_ministock_is_set = p_row.getInterestFlagMinistockIsSet();
    interest_flag_ministock_is_modified = p_row.getInterestFlagMinistockIsModified();
    interest_flag_margin = p_row.getInterestFlagMargin();
    interest_flag_margin_is_set = p_row.getInterestFlagMarginIsSet();
    interest_flag_margin_is_modified = p_row.getInterestFlagMarginIsModified();
    interest_flag_bond = p_row.getInterestFlagBond();
    interest_flag_bond_is_set = p_row.getInterestFlagBondIsSet();
    interest_flag_bond_is_modified = p_row.getInterestFlagBondIsModified();
    interest_flag_fund = p_row.getInterestFlagFund();
    interest_flag_fund_is_set = p_row.getInterestFlagFundIsSet();
    interest_flag_fund_is_modified = p_row.getInterestFlagFundIsModified();
    interest_flag_fo = p_row.getInterestFlagFo();
    interest_flag_fo_is_set = p_row.getInterestFlagFoIsSet();
    interest_flag_fo_is_modified = p_row.getInterestFlagFoIsModified();
    interest_flag_f_equity = p_row.getInterestFlagFEquity();
    interest_flag_f_equity_is_set = p_row.getInterestFlagFEquityIsSet();
    interest_flag_f_equity_is_modified = p_row.getInterestFlagFEquityIsModified();
    interest_flag_etc = p_row.getInterestFlagEtc();
    interest_flag_etc_is_set = p_row.getInterestFlagEtcIsSet();
    interest_flag_etc_is_modified = p_row.getInterestFlagEtcIsModified();
    invest_purpose_div = p_row.getInvestPurposeDiv();
    invest_purpose_div_is_set = p_row.getInvestPurposeDivIsSet();
    invest_purpose_div_is_modified = p_row.getInvestPurposeDivIsModified();
    appli_motivat_div = p_row.getAppliMotivatDiv();
    appli_motivat_div_is_set = p_row.getAppliMotivatDivIsSet();
    appli_motivat_div_is_modified = p_row.getAppliMotivatDivIsModified();
    annual_income_div = p_row.getAnnualIncomeDiv();
    annual_income_div_is_set = p_row.getAnnualIncomeDivIsSet();
    annual_income_div_is_modified = p_row.getAnnualIncomeDivIsModified();
    annual_income_from = p_row.getAnnualIncomeFrom();
    annual_income_from_is_set = p_row.getAnnualIncomeFromIsSet();
    annual_income_from_is_modified = p_row.getAnnualIncomeFromIsModified();
    annual_income_to = p_row.getAnnualIncomeTo();
    annual_income_to_is_set = p_row.getAnnualIncomeToIsSet();
    annual_income_to_is_modified = p_row.getAnnualIncomeToIsModified();
    asset_value_div = p_row.getAssetValueDiv();
    asset_value_div_is_set = p_row.getAssetValueDivIsSet();
    asset_value_div_is_modified = p_row.getAssetValueDivIsModified();
    asset_value_from = p_row.getAssetValueFrom();
    asset_value_from_is_set = p_row.getAssetValueFromIsSet();
    asset_value_from_is_modified = p_row.getAssetValueFromIsModified();
    asset_value_to = p_row.getAssetValueTo();
    asset_value_to_is_set = p_row.getAssetValueToIsSet();
    asset_value_to_is_modified = p_row.getAssetValueToIsModified();
    fund_budget_amount_div = p_row.getFundBudgetAmountDiv();
    fund_budget_amount_div_is_set = p_row.getFundBudgetAmountDivIsSet();
    fund_budget_amount_div_is_modified = p_row.getFundBudgetAmountDivIsModified();
    fund_budget_div = p_row.getFundBudgetDiv();
    fund_budget_div_is_set = p_row.getFundBudgetDivIsSet();
    fund_budget_div_is_modified = p_row.getFundBudgetDivIsModified();
    fund_budget_etc = p_row.getFundBudgetEtc();
    fund_budget_etc_is_set = p_row.getFundBudgetEtcIsSet();
    fund_budget_etc_is_modified = p_row.getFundBudgetEtcIsModified();
    id_confirm_flag = p_row.getIdConfirmFlag();
    id_confirm_flag_is_set = p_row.getIdConfirmFlagIsSet();
    id_confirm_flag_is_modified = p_row.getIdConfirmFlagIsModified();
    id_confirm_doc_div = p_row.getIdConfirmDocDiv();
    id_confirm_doc_div_is_set = p_row.getIdConfirmDocDivIsSet();
    id_confirm_doc_div_is_modified = p_row.getIdConfirmDocDivIsModified();
    id_confirm_doc_etc = p_row.getIdConfirmDocEtc();
    id_confirm_doc_etc_is_set = p_row.getIdConfirmDocEtcIsSet();
    id_confirm_doc_etc_is_modified = p_row.getIdConfirmDocEtcIsModified();
    special_acc = p_row.getSpecialAcc();
    special_acc_is_set = p_row.getSpecialAccIsSet();
    special_acc_is_modified = p_row.getSpecialAccIsModified();
    special_acc_margin = p_row.getSpecialAccMargin();
    special_acc_margin_is_set = p_row.getSpecialAccMarginIsSet();
    special_acc_margin_is_modified = p_row.getSpecialAccMarginIsModified();
    insider_flag = p_row.getInsiderFlag();
    insider_flag_is_set = p_row.getInsiderFlagIsSet();
    insider_flag_is_modified = p_row.getInsiderFlagIsModified();
    product_name = p_row.getProductName();
    product_name_is_set = p_row.getProductNameIsSet();
    product_name_is_modified = p_row.getProductNameIsModified();
    send_zip_code = p_row.getSendZipCode();
    send_zip_code_is_set = p_row.getSendZipCodeIsSet();
    send_zip_code_is_modified = p_row.getSendZipCodeIsModified();
    send_address_line1 = p_row.getSendAddressLine1();
    send_address_line1_is_set = p_row.getSendAddressLine1IsSet();
    send_address_line1_is_modified = p_row.getSendAddressLine1IsModified();
    send_address_line2 = p_row.getSendAddressLine2();
    send_address_line2_is_set = p_row.getSendAddressLine2IsSet();
    send_address_line2_is_modified = p_row.getSendAddressLine2IsModified();
    send_address_line3 = p_row.getSendAddressLine3();
    send_address_line3_is_set = p_row.getSendAddressLine3IsSet();
    send_address_line3_is_modified = p_row.getSendAddressLine3IsModified();
    ext_item_div1 = p_row.getExtItemDiv1();
    ext_item_div1_is_set = p_row.getExtItemDiv1IsSet();
    ext_item_div1_is_modified = p_row.getExtItemDiv1IsModified();
    ext_item_div2 = p_row.getExtItemDiv2();
    ext_item_div2_is_set = p_row.getExtItemDiv2IsSet();
    ext_item_div2_is_modified = p_row.getExtItemDiv2IsModified();
    ext_item_div3 = p_row.getExtItemDiv3();
    ext_item_div3_is_set = p_row.getExtItemDiv3IsSet();
    ext_item_div3_is_modified = p_row.getExtItemDiv3IsModified();
    ext_item_div4 = p_row.getExtItemDiv4();
    ext_item_div4_is_set = p_row.getExtItemDiv4IsSet();
    ext_item_div4_is_modified = p_row.getExtItemDiv4IsModified();
    ext_item_div5 = p_row.getExtItemDiv5();
    ext_item_div5_is_set = p_row.getExtItemDiv5IsSet();
    ext_item_div5_is_modified = p_row.getExtItemDiv5IsModified();
    ext_item_div6 = p_row.getExtItemDiv6();
    ext_item_div6_is_set = p_row.getExtItemDiv6IsSet();
    ext_item_div6_is_modified = p_row.getExtItemDiv6IsModified();
    ext_item_div7 = p_row.getExtItemDiv7();
    ext_item_div7_is_set = p_row.getExtItemDiv7IsSet();
    ext_item_div7_is_modified = p_row.getExtItemDiv7IsModified();
    ext_item_div8 = p_row.getExtItemDiv8();
    ext_item_div8_is_set = p_row.getExtItemDiv8IsSet();
    ext_item_div8_is_modified = p_row.getExtItemDiv8IsModified();
    ext_item_div9 = p_row.getExtItemDiv9();
    ext_item_div9_is_set = p_row.getExtItemDiv9IsSet();
    ext_item_div9_is_modified = p_row.getExtItemDiv9IsModified();
    ext_item_div10 = p_row.getExtItemDiv10();
    ext_item_div10_is_set = p_row.getExtItemDiv10IsSet();
    ext_item_div10_is_modified = p_row.getExtItemDiv10IsModified();
    ext_item_flag1 = p_row.getExtItemFlag1();
    ext_item_flag1_is_set = p_row.getExtItemFlag1IsSet();
    ext_item_flag1_is_modified = p_row.getExtItemFlag1IsModified();
    ext_item_flag2 = p_row.getExtItemFlag2();
    ext_item_flag2_is_set = p_row.getExtItemFlag2IsSet();
    ext_item_flag2_is_modified = p_row.getExtItemFlag2IsModified();
    ext_item_flag3 = p_row.getExtItemFlag3();
    ext_item_flag3_is_set = p_row.getExtItemFlag3IsSet();
    ext_item_flag3_is_modified = p_row.getExtItemFlag3IsModified();
    ext_item_flag4 = p_row.getExtItemFlag4();
    ext_item_flag4_is_set = p_row.getExtItemFlag4IsSet();
    ext_item_flag4_is_modified = p_row.getExtItemFlag4IsModified();
    ext_item_flag5 = p_row.getExtItemFlag5();
    ext_item_flag5_is_set = p_row.getExtItemFlag5IsSet();
    ext_item_flag5_is_modified = p_row.getExtItemFlag5IsModified();
    ext_item_flag6 = p_row.getExtItemFlag6();
    ext_item_flag6_is_set = p_row.getExtItemFlag6IsSet();
    ext_item_flag6_is_modified = p_row.getExtItemFlag6IsModified();
    ext_item_flag7 = p_row.getExtItemFlag7();
    ext_item_flag7_is_set = p_row.getExtItemFlag7IsSet();
    ext_item_flag7_is_modified = p_row.getExtItemFlag7IsModified();
    ext_item_flag8 = p_row.getExtItemFlag8();
    ext_item_flag8_is_set = p_row.getExtItemFlag8IsSet();
    ext_item_flag8_is_modified = p_row.getExtItemFlag8IsModified();
    ext_item_flag9 = p_row.getExtItemFlag9();
    ext_item_flag9_is_set = p_row.getExtItemFlag9IsSet();
    ext_item_flag9_is_modified = p_row.getExtItemFlag9IsModified();
    ext_item_flag10 = p_row.getExtItemFlag10();
    ext_item_flag10_is_set = p_row.getExtItemFlag10IsSet();
    ext_item_flag10_is_modified = p_row.getExtItemFlag10IsModified();
    ext_item_text1 = p_row.getExtItemText1();
    ext_item_text1_is_set = p_row.getExtItemText1IsSet();
    ext_item_text1_is_modified = p_row.getExtItemText1IsModified();
    ext_item_text2 = p_row.getExtItemText2();
    ext_item_text2_is_set = p_row.getExtItemText2IsSet();
    ext_item_text2_is_modified = p_row.getExtItemText2IsModified();
    ext_item_text3 = p_row.getExtItemText3();
    ext_item_text3_is_set = p_row.getExtItemText3IsSet();
    ext_item_text3_is_modified = p_row.getExtItemText3IsModified();
    ext_item_text4 = p_row.getExtItemText4();
    ext_item_text4_is_set = p_row.getExtItemText4IsSet();
    ext_item_text4_is_modified = p_row.getExtItemText4IsModified();
    ext_item_text5 = p_row.getExtItemText5();
    ext_item_text5_is_set = p_row.getExtItemText5IsSet();
    ext_item_text5_is_modified = p_row.getExtItemText5IsModified();
    ext_item_text6 = p_row.getExtItemText6();
    ext_item_text6_is_set = p_row.getExtItemText6IsSet();
    ext_item_text6_is_modified = p_row.getExtItemText6IsModified();
    ext_item_text7 = p_row.getExtItemText7();
    ext_item_text7_is_set = p_row.getExtItemText7IsSet();
    ext_item_text7_is_modified = p_row.getExtItemText7IsModified();
    ext_item_text8 = p_row.getExtItemText8();
    ext_item_text8_is_set = p_row.getExtItemText8IsSet();
    ext_item_text8_is_modified = p_row.getExtItemText8IsModified();
    ext_item_text9 = p_row.getExtItemText9();
    ext_item_text9_is_set = p_row.getExtItemText9IsSet();
    ext_item_text9_is_modified = p_row.getExtItemText9IsModified();
    ext_item_text10 = p_row.getExtItemText10();
    ext_item_text10_is_set = p_row.getExtItemText10IsSet();
    ext_item_text10_is_modified = p_row.getExtItemText10IsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    creator = p_row.getCreator();
    creator_is_set = p_row.getCreatorIsSet();
    creator_is_modified = p_row.getCreatorIsModified();
    exclusive_use_account_no = p_row.getExclusiveUseAccountNo();
    exclusive_use_account_no_is_set = p_row.getExclusiveUseAccountNoIsSet();
    exclusive_use_account_no_is_modified = p_row.getExclusiveUseAccountNoIsModified();
    send_timestamp = p_row.getSendTimestamp();
    send_timestamp_is_set = p_row.getSendTimestampIsSet();
    send_timestamp_is_modified = p_row.getSendTimestampIsModified();
    real_name_voucher_div = p_row.getRealNameVoucherDiv();
    real_name_voucher_div_is_set = p_row.getRealNameVoucherDivIsSet();
    real_name_voucher_div_is_modified = p_row.getRealNameVoucherDivIsModified();
    real_name1 = p_row.getRealName1();
    real_name1_is_set = p_row.getRealName1IsSet();
    real_name1_is_modified = p_row.getRealName1IsModified();
    real_name2 = p_row.getRealName2();
    real_name2_is_set = p_row.getRealName2IsSet();
    real_name2_is_modified = p_row.getRealName2IsModified();
    insider_voucher_div = p_row.getInsiderVoucherDiv();
    insider_voucher_div_is_set = p_row.getInsiderVoucherDivIsSet();
    insider_voucher_div_is_modified = p_row.getInsiderVoucherDivIsModified();
    insider_product_code = p_row.getInsiderProductCode();
    insider_product_code_is_set = p_row.getInsiderProductCodeIsSet();
    insider_product_code_is_modified = p_row.getInsiderProductCodeIsModified();
    insider_relation_div = p_row.getInsiderRelationDiv();
    insider_relation_div_is_set = p_row.getInsiderRelationDivIsSet();
    insider_relation_div_is_modified = p_row.getInsiderRelationDivIsModified();
    insider_officer_name = p_row.getInsiderOfficerName();
    insider_officer_name_is_set = p_row.getInsiderOfficerNameIsSet();
    insider_officer_name_is_modified = p_row.getInsiderOfficerNameIsModified();
    insider_post_code = p_row.getInsiderPostCode();
    insider_post_code_is_set = p_row.getInsiderPostCodeIsSet();
    insider_post_code_is_modified = p_row.getInsiderPostCodeIsModified();
    insider_post_name = p_row.getInsiderPostName();
    insider_post_name_is_set = p_row.getInsiderPostNameIsSet();
    insider_post_name_is_modified = p_row.getInsiderPostNameIsModified();
    gp_voucher_div = p_row.getGpVoucherDiv();
    gp_voucher_div_is_set = p_row.getGpVoucherDivIsSet();
    gp_voucher_div_is_modified = p_row.getGpVoucherDivIsModified();
    gp_course = p_row.getGpCourse();
    gp_course_is_set = p_row.getGpCourseIsSet();
    gp_course_is_modified = p_row.getGpCourseIsModified();
    gp_plan = p_row.getGpPlan();
    gp_plan_is_set = p_row.getGpPlanIsSet();
    gp_plan_is_modified = p_row.getGpPlanIsModified();
    gp_target_figure = p_row.getGpTargetFigure();
    gp_target_figure_is_set = p_row.getGpTargetFigureIsSet();
    gp_target_figure_is_modified = p_row.getGpTargetFigureIsModified();
    gp_target_year = p_row.getGpTargetYear();
    gp_target_year_is_set = p_row.getGpTargetYearIsSet();
    gp_target_year_is_modified = p_row.getGpTargetYearIsModified();
    gp_target_month = p_row.getGpTargetMonth();
    gp_target_month_is_set = p_row.getGpTargetMonthIsSet();
    gp_target_month_is_modified = p_row.getGpTargetMonthIsModified();
    gp_installment_figure = p_row.getGpInstallmentFigure();
    gp_installment_figure_is_set = p_row.getGpInstallmentFigureIsSet();
    gp_installment_figure_is_modified = p_row.getGpInstallmentFigureIsModified();
    gp_deposit_cycle = p_row.getGpDepositCycle();
    gp_deposit_cycle_is_set = p_row.getGpDepositCycleIsSet();
    gp_deposit_cycle_is_modified = p_row.getGpDepositCycleIsModified();
    gp_payment_root = p_row.getGpPaymentRoot();
    gp_payment_root_is_set = p_row.getGpPaymentRootIsSet();
    gp_payment_root_is_modified = p_row.getGpPaymentRootIsModified();
    gp_reinvest_div = p_row.getGpReinvestDiv();
    gp_reinvest_div_is_set = p_row.getGpReinvestDivIsSet();
    gp_reinvest_div_is_modified = p_row.getGpReinvestDivIsModified();
    gp_tax_div = p_row.getGpTaxDiv();
    gp_tax_div_is_set = p_row.getGpTaxDivIsSet();
    gp_tax_div_is_modified = p_row.getGpTaxDivIsModified();
    gp_taxfree_limit = p_row.getGpTaxfreeLimit();
    gp_taxfree_limit_is_set = p_row.getGpTaxfreeLimitIsSet();
    gp_taxfree_limit_is_modified = p_row.getGpTaxfreeLimitIsModified();
    gp_special_taxfree_limit = p_row.getGpSpecialTaxfreeLimit();
    gp_special_taxfree_limit_is_set = p_row.getGpSpecialTaxfreeLimitIsSet();
    gp_special_taxfree_limit_is_modified = p_row.getGpSpecialTaxfreeLimitIsModified();
    gp_subscr_summary = p_row.getGpSubscrSummary();
    gp_subscr_summary_is_set = p_row.getGpSubscrSummaryIsSet();
    gp_subscr_summary_is_modified = p_row.getGpSubscrSummaryIsModified();
    gp_product_code = p_row.getGpProductCode();
    gp_product_code_is_set = p_row.getGpProductCodeIsSet();
    gp_product_code_is_modified = p_row.getGpProductCodeIsModified();
    gp_mortgage_customer = p_row.getGpMortgageCustomer();
    gp_mortgage_customer_is_set = p_row.getGpMortgageCustomerIsSet();
    gp_mortgage_customer_is_modified = p_row.getGpMortgageCustomerIsModified();
    gp_mix_customer = p_row.getGpMixCustomer();
    gp_mix_customer_is_set = p_row.getGpMixCustomerIsSet();
    gp_mix_customer_is_modified = p_row.getGpMixCustomerIsModified();
    gp_contract = p_row.getGpContract();
    gp_contract_is_set = p_row.getGpContractIsSet();
    gp_contract_is_modified = p_row.getGpContractIsModified();
    stk_voucher_div = p_row.getStkVoucherDiv();
    stk_voucher_div_is_set = p_row.getStkVoucherDivIsSet();
    stk_voucher_div_is_modified = p_row.getStkVoucherDivIsModified();
    stk_taxation_tran_div = p_row.getStkTaxationTranDiv();
    stk_taxation_tran_div_is_set = p_row.getStkTaxationTranDivIsSet();
    stk_taxation_tran_div_is_modified = p_row.getStkTaxationTranDivIsModified();
    stk_address_line_kana = p_row.getStkAddressLineKana();
    stk_address_line_kana_is_set = p_row.getStkAddressLineKanaIsSet();
    stk_address_line_kana_is_modified = p_row.getStkAddressLineKanaIsModified();
    stk_transfer_div = p_row.getStkTransferDiv();
    stk_transfer_div_is_set = p_row.getStkTransferDivIsSet();
    stk_transfer_div_is_modified = p_row.getStkTransferDivIsModified();
    stk_fin_institution_code = p_row.getStkFinInstitutionCode();
    stk_fin_institution_code_is_set = p_row.getStkFinInstitutionCodeIsSet();
    stk_fin_institution_code_is_modified = p_row.getStkFinInstitutionCodeIsModified();
    stk_fin_branch_code = p_row.getStkFinBranchCode();
    stk_fin_branch_code_is_set = p_row.getStkFinBranchCodeIsSet();
    stk_fin_branch_code_is_modified = p_row.getStkFinBranchCodeIsModified();
    stk_fin_save_div = p_row.getStkFinSaveDiv();
    stk_fin_save_div_is_set = p_row.getStkFinSaveDivIsSet();
    stk_fin_save_div_is_modified = p_row.getStkFinSaveDivIsModified();
    stk_fin_account_no = p_row.getStkFinAccountNo();
    stk_fin_account_no_is_set = p_row.getStkFinAccountNoIsSet();
    stk_fin_account_no_is_modified = p_row.getStkFinAccountNoIsModified();
    brokerage_trader_code = p_row.getBrokerageTraderCode();
    brokerage_trader_code_is_set = p_row.getBrokerageTraderCodeIsSet();
    brokerage_trader_code_is_modified = p_row.getBrokerageTraderCodeIsModified();
    ext_item_div11 = p_row.getExtItemDiv11();
    ext_item_div11_is_set = p_row.getExtItemDiv11IsSet();
    ext_item_div11_is_modified = p_row.getExtItemDiv11IsModified();
    ext_item_div12 = p_row.getExtItemDiv12();
    ext_item_div12_is_set = p_row.getExtItemDiv12IsSet();
    ext_item_div12_is_modified = p_row.getExtItemDiv12IsModified();
    ext_item_div13 = p_row.getExtItemDiv13();
    ext_item_div13_is_set = p_row.getExtItemDiv13IsSet();
    ext_item_div13_is_modified = p_row.getExtItemDiv13IsModified();
    ext_item_div14 = p_row.getExtItemDiv14();
    ext_item_div14_is_set = p_row.getExtItemDiv14IsSet();
    ext_item_div14_is_modified = p_row.getExtItemDiv14IsModified();
    ext_item_div15 = p_row.getExtItemDiv15();
    ext_item_div15_is_set = p_row.getExtItemDiv15IsSet();
    ext_item_div15_is_modified = p_row.getExtItemDiv15IsModified();
    foreign_account_no = p_row.getForeignAccountNo();
    foreign_account_no_is_set = p_row.getForeignAccountNoIsSet();
    foreign_account_no_is_modified = p_row.getForeignAccountNoIsModified();
    foreign_account_name = p_row.getForeignAccountName();
    foreign_account_name_is_set = p_row.getForeignAccountNameIsSet();
    foreign_account_name_is_modified = p_row.getForeignAccountNameIsModified();
    foreign_account_name_eng = p_row.getForeignAccountNameEng();
    foreign_account_name_eng_is_set = p_row.getForeignAccountNameEngIsSet();
    foreign_account_name_eng_is_modified = p_row.getForeignAccountNameEngIsModified();
    foreign_save_div = p_row.getForeignSaveDiv();
    foreign_save_div_is_set = p_row.getForeignSaveDivIsSet();
    foreign_save_div_is_modified = p_row.getForeignSaveDivIsModified();
    delete_flag = p_row.getDeleteFlag();
    delete_flag_is_set = p_row.getDeleteFlagIsSet();
    delete_flag_is_modified = p_row.getDeleteFlagIsModified();
    delete_timestamp = p_row.getDeleteTimestamp();
    delete_timestamp_is_set = p_row.getDeleteTimestampIsSet();
    delete_timestamp_is_modified = p_row.getDeleteTimestampIsModified();
    print_flag = p_row.getPrintFlag();
    print_flag_is_set = p_row.getPrintFlagIsSet();
    print_flag_is_modified = p_row.getPrintFlagIsModified();
    receipt_flag = p_row.getReceiptFlag();
    receipt_flag_is_set = p_row.getReceiptFlagIsSet();
    receipt_flag_is_modified = p_row.getReceiptFlagIsModified();
    agreement_flag = p_row.getAgreementFlag();
    agreement_flag_is_set = p_row.getAgreementFlagIsSet();
    agreement_flag_is_modified = p_row.getAgreementFlagIsModified();
    foreign_flag = p_row.getForeignFlag();
    foreign_flag_is_set = p_row.getForeignFlagIsSet();
    foreign_flag_is_modified = p_row.getForeignFlagIsModified();
    agency_acc_name_kana1 = p_row.getAgencyAccNameKana1();
    agency_acc_name_kana1_is_set = p_row.getAgencyAccNameKana1IsSet();
    agency_acc_name_kana1_is_modified = p_row.getAgencyAccNameKana1IsModified();
    agency_acc_name_kana2 = p_row.getAgencyAccNameKana2();
    agency_acc_name_kana2_is_set = p_row.getAgencyAccNameKana2IsSet();
    agency_acc_name_kana2_is_modified = p_row.getAgencyAccNameKana2IsModified();
    agency_acc_name1 = p_row.getAgencyAccName1();
    agency_acc_name1_is_set = p_row.getAgencyAccName1IsSet();
    agency_acc_name1_is_modified = p_row.getAgencyAccName1IsModified();
    agency_acc_name2 = p_row.getAgencyAccName2();
    agency_acc_name2_is_set = p_row.getAgencyAccName2IsSet();
    agency_acc_name2_is_modified = p_row.getAgencyAccName2IsModified();
    agency_address_line1 = p_row.getAgencyAddressLine1();
    agency_address_line1_is_set = p_row.getAgencyAddressLine1IsSet();
    agency_address_line1_is_modified = p_row.getAgencyAddressLine1IsModified();
    agency_address_line2 = p_row.getAgencyAddressLine2();
    agency_address_line2_is_set = p_row.getAgencyAddressLine2IsSet();
    agency_address_line2_is_modified = p_row.getAgencyAddressLine2IsModified();
    agency_rep_post = p_row.getAgencyRepPost();
    agency_rep_post_is_set = p_row.getAgencyRepPostIsSet();
    agency_rep_post_is_modified = p_row.getAgencyRepPostIsModified();
    agency_rep_name_kana1 = p_row.getAgencyRepNameKana1();
    agency_rep_name_kana1_is_set = p_row.getAgencyRepNameKana1IsSet();
    agency_rep_name_kana1_is_modified = p_row.getAgencyRepNameKana1IsModified();
    agency_rep_name_kana2 = p_row.getAgencyRepNameKana2();
    agency_rep_name_kana2_is_set = p_row.getAgencyRepNameKana2IsSet();
    agency_rep_name_kana2_is_modified = p_row.getAgencyRepNameKana2IsModified();
    agency_rep_name1 = p_row.getAgencyRepName1();
    agency_rep_name1_is_set = p_row.getAgencyRepName1IsSet();
    agency_rep_name1_is_modified = p_row.getAgencyRepName1IsModified();
    agency_rep_name2 = p_row.getAgencyRepName2();
    agency_rep_name2_is_set = p_row.getAgencyRepName2IsSet();
    agency_rep_name2_is_modified = p_row.getAgencyRepName2IsModified();
    status = p_row.getStatus();
    status_is_set = p_row.getStatusIsSet();
    status_is_modified = p_row.getStatusIsModified();
    error_info = p_row.getErrorInfo();
    error_info_is_set = p_row.getErrorInfoIsSet();
    error_info_is_modified = p_row.getErrorInfoIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      institution_id_is_set = true;
      institution_id_is_modified = true;
      branch_id_is_set = true;
      branch_id_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      sonar_trader_code_is_set = true;
      sonar_trader_code_is_modified = true;
      ex_account_flag_is_set = true;
      ex_account_flag_is_modified = true;
      ex_branch_name_is_set = true;
      ex_branch_name_is_modified = true;
      ex_account_code_is_set = true;
      ex_account_code_is_modified = true;
      account_div_is_set = true;
      account_div_is_modified = true;
      order_div_is_set = true;
      order_div_is_modified = true;
      infomation_claim_datetime_is_set = true;
      infomation_claim_datetime_is_modified = true;
      account_open_date_is_set = true;
      account_open_date_is_modified = true;
      initial_password_is_set = true;
      initial_password_is_modified = true;
      family_name_is_set = true;
      family_name_is_modified = true;
      given_name_is_set = true;
      given_name_is_modified = true;
      family_name_alt1_is_set = true;
      family_name_alt1_is_modified = true;
      given_name_alt1_is_set = true;
      given_name_alt1_is_modified = true;
      sex_is_set = true;
      sex_is_modified = true;
      era_born_is_set = true;
      era_born_is_modified = true;
      born_date_is_set = true;
      born_date_is_modified = true;
      email_address_is_set = true;
      email_address_is_modified = true;
      email_address_alt1_is_set = true;
      email_address_alt1_is_modified = true;
      zip_code_is_set = true;
      zip_code_is_modified = true;
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
      occupation_div_is_set = true;
      occupation_div_is_modified = true;
      office_is_set = true;
      office_is_modified = true;
      office_zip_code_is_set = true;
      office_zip_code_is_modified = true;
      office_address_is_set = true;
      office_address_is_modified = true;
      office_telephone_is_set = true;
      office_telephone_is_modified = true;
      office_fax_is_set = true;
      office_fax_is_modified = true;
      department_is_set = true;
      department_is_modified = true;
      post_is_set = true;
      post_is_modified = true;
      contact_address_is_set = true;
      contact_address_is_modified = true;
      contact_telephone_is_set = true;
      contact_telephone_is_modified = true;
      family_relationship_is_set = true;
      family_relationship_is_modified = true;
      family_relationship_etc_is_set = true;
      family_relationship_etc_is_modified = true;
      householder_is_set = true;
      householder_is_modified = true;
      householder_kana_is_set = true;
      householder_kana_is_modified = true;
      householder_occupation_div_is_set = true;
      householder_occupation_div_is_modified = true;
      householder_office_is_set = true;
      householder_office_is_modified = true;
      householder_office_address_is_set = true;
      householder_office_address_is_modified = true;
      householder_department_is_set = true;
      householder_department_is_modified = true;
      householder_office_tel_is_set = true;
      householder_office_tel_is_modified = true;
      householder_office_fax_is_set = true;
      householder_office_fax_is_modified = true;
      householder_post_is_set = true;
      householder_post_is_modified = true;
      resident_is_set = true;
      resident_is_modified = true;
      transfer_div_is_set = true;
      transfer_div_is_modified = true;
      fin_institution_code_is_set = true;
      fin_institution_code_is_modified = true;
      fin_institution_name_is_set = true;
      fin_institution_name_is_modified = true;
      fin_branch_code_is_set = true;
      fin_branch_code_is_modified = true;
      fin_branch_name_is_set = true;
      fin_branch_name_is_modified = true;
      fin_save_div_is_set = true;
      fin_save_div_is_modified = true;
      fin_account_no_is_set = true;
      fin_account_no_is_modified = true;
      postal_save_code_is_set = true;
      postal_save_code_is_modified = true;
      postal_save_no_is_set = true;
      postal_save_no_is_modified = true;
      fin_account_name_is_set = true;
      fin_account_name_is_modified = true;
      trans_commission_is_set = true;
      trans_commission_is_modified = true;
      experience_div_equity_is_set = true;
      experience_div_equity_is_modified = true;
      experience_div_margin_is_set = true;
      experience_div_margin_is_modified = true;
      experience_div_bond_is_set = true;
      experience_div_bond_is_modified = true;
      experience_div_wt_is_set = true;
      experience_div_wt_is_modified = true;
      experience_div_fund_sk_is_set = true;
      experience_div_fund_sk_is_modified = true;
      experience_div_fund_bd_is_set = true;
      experience_div_fund_bd_is_modified = true;
      experience_div_fo_is_set = true;
      experience_div_fo_is_modified = true;
      experience_div_f_equity_is_set = true;
      experience_div_f_equity_is_modified = true;
      experience_div_etc_is_set = true;
      experience_div_etc_is_modified = true;
      experience_flag_equity_is_set = true;
      experience_flag_equity_is_modified = true;
      experience_flag_margin_is_set = true;
      experience_flag_margin_is_modified = true;
      experience_flag_bond_is_set = true;
      experience_flag_bond_is_modified = true;
      experience_flag_wt_is_set = true;
      experience_flag_wt_is_modified = true;
      experience_flag_fund_sk_is_set = true;
      experience_flag_fund_sk_is_modified = true;
      experience_flag_fund_bd_is_set = true;
      experience_flag_fund_bd_is_modified = true;
      experience_flag_fo_is_set = true;
      experience_flag_fo_is_modified = true;
      experience_flag_f_equity_is_set = true;
      experience_flag_f_equity_is_modified = true;
      experience_flag_etc_is_set = true;
      experience_flag_etc_is_modified = true;
      experience_from_is_set = true;
      experience_from_is_modified = true;
      experience_to_is_set = true;
      experience_to_is_modified = true;
      interest_flag_equity_is_set = true;
      interest_flag_equity_is_modified = true;
      interest_flag_ministock_is_set = true;
      interest_flag_ministock_is_modified = true;
      interest_flag_margin_is_set = true;
      interest_flag_margin_is_modified = true;
      interest_flag_bond_is_set = true;
      interest_flag_bond_is_modified = true;
      interest_flag_fund_is_set = true;
      interest_flag_fund_is_modified = true;
      interest_flag_fo_is_set = true;
      interest_flag_fo_is_modified = true;
      interest_flag_f_equity_is_set = true;
      interest_flag_f_equity_is_modified = true;
      interest_flag_etc_is_set = true;
      interest_flag_etc_is_modified = true;
      invest_purpose_div_is_set = true;
      invest_purpose_div_is_modified = true;
      appli_motivat_div_is_set = true;
      appli_motivat_div_is_modified = true;
      annual_income_div_is_set = true;
      annual_income_div_is_modified = true;
      annual_income_from_is_set = true;
      annual_income_from_is_modified = true;
      annual_income_to_is_set = true;
      annual_income_to_is_modified = true;
      asset_value_div_is_set = true;
      asset_value_div_is_modified = true;
      asset_value_from_is_set = true;
      asset_value_from_is_modified = true;
      asset_value_to_is_set = true;
      asset_value_to_is_modified = true;
      fund_budget_amount_div_is_set = true;
      fund_budget_amount_div_is_modified = true;
      fund_budget_div_is_set = true;
      fund_budget_div_is_modified = true;
      fund_budget_etc_is_set = true;
      fund_budget_etc_is_modified = true;
      id_confirm_flag_is_set = true;
      id_confirm_flag_is_modified = true;
      id_confirm_doc_div_is_set = true;
      id_confirm_doc_div_is_modified = true;
      id_confirm_doc_etc_is_set = true;
      id_confirm_doc_etc_is_modified = true;
      special_acc_is_set = true;
      special_acc_is_modified = true;
      special_acc_margin_is_set = true;
      special_acc_margin_is_modified = true;
      insider_flag_is_set = true;
      insider_flag_is_modified = true;
      product_name_is_set = true;
      product_name_is_modified = true;
      send_zip_code_is_set = true;
      send_zip_code_is_modified = true;
      send_address_line1_is_set = true;
      send_address_line1_is_modified = true;
      send_address_line2_is_set = true;
      send_address_line2_is_modified = true;
      send_address_line3_is_set = true;
      send_address_line3_is_modified = true;
      ext_item_div1_is_set = true;
      ext_item_div1_is_modified = true;
      ext_item_div2_is_set = true;
      ext_item_div2_is_modified = true;
      ext_item_div3_is_set = true;
      ext_item_div3_is_modified = true;
      ext_item_div4_is_set = true;
      ext_item_div4_is_modified = true;
      ext_item_div5_is_set = true;
      ext_item_div5_is_modified = true;
      ext_item_div6_is_set = true;
      ext_item_div6_is_modified = true;
      ext_item_div7_is_set = true;
      ext_item_div7_is_modified = true;
      ext_item_div8_is_set = true;
      ext_item_div8_is_modified = true;
      ext_item_div9_is_set = true;
      ext_item_div9_is_modified = true;
      ext_item_div10_is_set = true;
      ext_item_div10_is_modified = true;
      ext_item_flag1_is_set = true;
      ext_item_flag1_is_modified = true;
      ext_item_flag2_is_set = true;
      ext_item_flag2_is_modified = true;
      ext_item_flag3_is_set = true;
      ext_item_flag3_is_modified = true;
      ext_item_flag4_is_set = true;
      ext_item_flag4_is_modified = true;
      ext_item_flag5_is_set = true;
      ext_item_flag5_is_modified = true;
      ext_item_flag6_is_set = true;
      ext_item_flag6_is_modified = true;
      ext_item_flag7_is_set = true;
      ext_item_flag7_is_modified = true;
      ext_item_flag8_is_set = true;
      ext_item_flag8_is_modified = true;
      ext_item_flag9_is_set = true;
      ext_item_flag9_is_modified = true;
      ext_item_flag10_is_set = true;
      ext_item_flag10_is_modified = true;
      ext_item_text1_is_set = true;
      ext_item_text1_is_modified = true;
      ext_item_text2_is_set = true;
      ext_item_text2_is_modified = true;
      ext_item_text3_is_set = true;
      ext_item_text3_is_modified = true;
      ext_item_text4_is_set = true;
      ext_item_text4_is_modified = true;
      ext_item_text5_is_set = true;
      ext_item_text5_is_modified = true;
      ext_item_text6_is_set = true;
      ext_item_text6_is_modified = true;
      ext_item_text7_is_set = true;
      ext_item_text7_is_modified = true;
      ext_item_text8_is_set = true;
      ext_item_text8_is_modified = true;
      ext_item_text9_is_set = true;
      ext_item_text9_is_modified = true;
      ext_item_text10_is_set = true;
      ext_item_text10_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      creator_is_set = true;
      creator_is_modified = true;
      exclusive_use_account_no_is_set = true;
      exclusive_use_account_no_is_modified = true;
      send_timestamp_is_set = true;
      send_timestamp_is_modified = true;
      real_name_voucher_div_is_set = true;
      real_name_voucher_div_is_modified = true;
      real_name1_is_set = true;
      real_name1_is_modified = true;
      real_name2_is_set = true;
      real_name2_is_modified = true;
      insider_voucher_div_is_set = true;
      insider_voucher_div_is_modified = true;
      insider_product_code_is_set = true;
      insider_product_code_is_modified = true;
      insider_relation_div_is_set = true;
      insider_relation_div_is_modified = true;
      insider_officer_name_is_set = true;
      insider_officer_name_is_modified = true;
      insider_post_code_is_set = true;
      insider_post_code_is_modified = true;
      insider_post_name_is_set = true;
      insider_post_name_is_modified = true;
      gp_voucher_div_is_set = true;
      gp_voucher_div_is_modified = true;
      gp_course_is_set = true;
      gp_course_is_modified = true;
      gp_plan_is_set = true;
      gp_plan_is_modified = true;
      gp_target_figure_is_set = true;
      gp_target_figure_is_modified = true;
      gp_target_year_is_set = true;
      gp_target_year_is_modified = true;
      gp_target_month_is_set = true;
      gp_target_month_is_modified = true;
      gp_installment_figure_is_set = true;
      gp_installment_figure_is_modified = true;
      gp_deposit_cycle_is_set = true;
      gp_deposit_cycle_is_modified = true;
      gp_payment_root_is_set = true;
      gp_payment_root_is_modified = true;
      gp_reinvest_div_is_set = true;
      gp_reinvest_div_is_modified = true;
      gp_tax_div_is_set = true;
      gp_tax_div_is_modified = true;
      gp_taxfree_limit_is_set = true;
      gp_taxfree_limit_is_modified = true;
      gp_special_taxfree_limit_is_set = true;
      gp_special_taxfree_limit_is_modified = true;
      gp_subscr_summary_is_set = true;
      gp_subscr_summary_is_modified = true;
      gp_product_code_is_set = true;
      gp_product_code_is_modified = true;
      gp_mortgage_customer_is_set = true;
      gp_mortgage_customer_is_modified = true;
      gp_mix_customer_is_set = true;
      gp_mix_customer_is_modified = true;
      gp_contract_is_set = true;
      gp_contract_is_modified = true;
      stk_voucher_div_is_set = true;
      stk_voucher_div_is_modified = true;
      stk_taxation_tran_div_is_set = true;
      stk_taxation_tran_div_is_modified = true;
      stk_address_line_kana_is_set = true;
      stk_address_line_kana_is_modified = true;
      stk_transfer_div_is_set = true;
      stk_transfer_div_is_modified = true;
      stk_fin_institution_code_is_set = true;
      stk_fin_institution_code_is_modified = true;
      stk_fin_branch_code_is_set = true;
      stk_fin_branch_code_is_modified = true;
      stk_fin_save_div_is_set = true;
      stk_fin_save_div_is_modified = true;
      stk_fin_account_no_is_set = true;
      stk_fin_account_no_is_modified = true;
      brokerage_trader_code_is_set = true;
      brokerage_trader_code_is_modified = true;
      ext_item_div11_is_set = true;
      ext_item_div11_is_modified = true;
      ext_item_div12_is_set = true;
      ext_item_div12_is_modified = true;
      ext_item_div13_is_set = true;
      ext_item_div13_is_modified = true;
      ext_item_div14_is_set = true;
      ext_item_div14_is_modified = true;
      ext_item_div15_is_set = true;
      ext_item_div15_is_modified = true;
      foreign_account_no_is_set = true;
      foreign_account_no_is_modified = true;
      foreign_account_name_is_set = true;
      foreign_account_name_is_modified = true;
      foreign_account_name_eng_is_set = true;
      foreign_account_name_eng_is_modified = true;
      foreign_save_div_is_set = true;
      foreign_save_div_is_modified = true;
      delete_flag_is_set = true;
      delete_flag_is_modified = true;
      delete_timestamp_is_set = true;
      delete_timestamp_is_modified = true;
      print_flag_is_set = true;
      print_flag_is_modified = true;
      receipt_flag_is_set = true;
      receipt_flag_is_modified = true;
      agreement_flag_is_set = true;
      agreement_flag_is_modified = true;
      foreign_flag_is_set = true;
      foreign_flag_is_modified = true;
      agency_acc_name_kana1_is_set = true;
      agency_acc_name_kana1_is_modified = true;
      agency_acc_name_kana2_is_set = true;
      agency_acc_name_kana2_is_modified = true;
      agency_acc_name1_is_set = true;
      agency_acc_name1_is_modified = true;
      agency_acc_name2_is_set = true;
      agency_acc_name2_is_modified = true;
      agency_address_line1_is_set = true;
      agency_address_line1_is_modified = true;
      agency_address_line2_is_set = true;
      agency_address_line2_is_modified = true;
      agency_rep_post_is_set = true;
      agency_rep_post_is_modified = true;
      agency_rep_name_kana1_is_set = true;
      agency_rep_name_kana1_is_modified = true;
      agency_rep_name_kana2_is_set = true;
      agency_rep_name_kana2_is_modified = true;
      agency_rep_name1_is_set = true;
      agency_rep_name1_is_modified = true;
      agency_rep_name2_is_set = true;
      agency_rep_name2_is_modified = true;
      status_is_set = true;
      status_is_modified = true;
      error_info_is_set = true;
      error_info_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof ExpAccountOpenTempRow ) )
       return false;
    return fieldsEqual( (ExpAccountOpenTempRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のExpAccountOpenTempRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( ExpAccountOpenTempRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( institution_id != row.getInstitutionId() )
      return false;
    if ( branch_id != row.getBranchId() )
      return false;
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( acc_open_request_number == null ) {
      if ( row.getAccOpenRequestNumber() != null )
        return false;
    } else if ( !acc_open_request_number.equals( row.getAccOpenRequestNumber() ) ) {
        return false;
    }
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( sonar_trader_code == null ) {
      if ( row.getSonarTraderCode() != null )
        return false;
    } else if ( !sonar_trader_code.equals( row.getSonarTraderCode() ) ) {
        return false;
    }
    if ( ex_account_flag == null ) {
      if ( row.getExAccountFlag() != null )
        return false;
    } else if ( !ex_account_flag.equals( row.getExAccountFlag() ) ) {
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
    if ( account_div == null ) {
      if ( row.getAccountDiv() != null )
        return false;
    } else if ( !account_div.equals( row.getAccountDiv() ) ) {
        return false;
    }
    if ( order_div == null ) {
      if ( row.getOrderDiv() != null )
        return false;
    } else if ( !order_div.equals( row.getOrderDiv() ) ) {
        return false;
    }
    if ( infomation_claim_datetime == null ) {
      if ( row.getInfomationClaimDatetime() != null )
        return false;
    } else if ( !infomation_claim_datetime.equals( row.getInfomationClaimDatetime() ) ) {
        return false;
    }
    if ( account_open_date == null ) {
      if ( row.getAccountOpenDate() != null )
        return false;
    } else if ( !account_open_date.equals( row.getAccountOpenDate() ) ) {
        return false;
    }
    if ( initial_password == null ) {
      if ( row.getInitialPassword() != null )
        return false;
    } else if ( !initial_password.equals( row.getInitialPassword() ) ) {
        return false;
    }
    if ( family_name == null ) {
      if ( row.getFamilyName() != null )
        return false;
    } else if ( !family_name.equals( row.getFamilyName() ) ) {
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
    if ( given_name_alt1 == null ) {
      if ( row.getGivenNameAlt1() != null )
        return false;
    } else if ( !given_name_alt1.equals( row.getGivenNameAlt1() ) ) {
        return false;
    }
    if ( sex == null ) {
      if ( row.getSex() != null )
        return false;
    } else if ( !sex.equals( row.getSex() ) ) {
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
    if ( occupation_div == null ) {
      if ( row.getOccupationDiv() != null )
        return false;
    } else if ( !occupation_div.equals( row.getOccupationDiv() ) ) {
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
    if ( office_fax == null ) {
      if ( row.getOfficeFax() != null )
        return false;
    } else if ( !office_fax.equals( row.getOfficeFax() ) ) {
        return false;
    }
    if ( department == null ) {
      if ( row.getDepartment() != null )
        return false;
    } else if ( !department.equals( row.getDepartment() ) ) {
        return false;
    }
    if ( post == null ) {
      if ( row.getPost() != null )
        return false;
    } else if ( !post.equals( row.getPost() ) ) {
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
    if ( family_relationship == null ) {
      if ( row.getFamilyRelationship() != null )
        return false;
    } else if ( !family_relationship.equals( row.getFamilyRelationship() ) ) {
        return false;
    }
    if ( family_relationship_etc == null ) {
      if ( row.getFamilyRelationshipEtc() != null )
        return false;
    } else if ( !family_relationship_etc.equals( row.getFamilyRelationshipEtc() ) ) {
        return false;
    }
    if ( householder == null ) {
      if ( row.getHouseholder() != null )
        return false;
    } else if ( !householder.equals( row.getHouseholder() ) ) {
        return false;
    }
    if ( householder_kana == null ) {
      if ( row.getHouseholderKana() != null )
        return false;
    } else if ( !householder_kana.equals( row.getHouseholderKana() ) ) {
        return false;
    }
    if ( householder_occupation_div == null ) {
      if ( row.getHouseholderOccupationDiv() != null )
        return false;
    } else if ( !householder_occupation_div.equals( row.getHouseholderOccupationDiv() ) ) {
        return false;
    }
    if ( householder_office == null ) {
      if ( row.getHouseholderOffice() != null )
        return false;
    } else if ( !householder_office.equals( row.getHouseholderOffice() ) ) {
        return false;
    }
    if ( householder_office_address == null ) {
      if ( row.getHouseholderOfficeAddress() != null )
        return false;
    } else if ( !householder_office_address.equals( row.getHouseholderOfficeAddress() ) ) {
        return false;
    }
    if ( householder_department == null ) {
      if ( row.getHouseholderDepartment() != null )
        return false;
    } else if ( !householder_department.equals( row.getHouseholderDepartment() ) ) {
        return false;
    }
    if ( householder_office_tel == null ) {
      if ( row.getHouseholderOfficeTel() != null )
        return false;
    } else if ( !householder_office_tel.equals( row.getHouseholderOfficeTel() ) ) {
        return false;
    }
    if ( householder_office_fax == null ) {
      if ( row.getHouseholderOfficeFax() != null )
        return false;
    } else if ( !householder_office_fax.equals( row.getHouseholderOfficeFax() ) ) {
        return false;
    }
    if ( householder_post == null ) {
      if ( row.getHouseholderPost() != null )
        return false;
    } else if ( !householder_post.equals( row.getHouseholderPost() ) ) {
        return false;
    }
    if ( resident == null ) {
      if ( row.getResident() != null )
        return false;
    } else if ( !resident.equals( row.getResident() ) ) {
        return false;
    }
    if ( transfer_div == null ) {
      if ( row.getTransferDiv() != null )
        return false;
    } else if ( !transfer_div.equals( row.getTransferDiv() ) ) {
        return false;
    }
    if ( fin_institution_code == null ) {
      if ( row.getFinInstitutionCode() != null )
        return false;
    } else if ( !fin_institution_code.equals( row.getFinInstitutionCode() ) ) {
        return false;
    }
    if ( fin_institution_name == null ) {
      if ( row.getFinInstitutionName() != null )
        return false;
    } else if ( !fin_institution_name.equals( row.getFinInstitutionName() ) ) {
        return false;
    }
    if ( fin_branch_code == null ) {
      if ( row.getFinBranchCode() != null )
        return false;
    } else if ( !fin_branch_code.equals( row.getFinBranchCode() ) ) {
        return false;
    }
    if ( fin_branch_name == null ) {
      if ( row.getFinBranchName() != null )
        return false;
    } else if ( !fin_branch_name.equals( row.getFinBranchName() ) ) {
        return false;
    }
    if ( fin_save_div == null ) {
      if ( row.getFinSaveDiv() != null )
        return false;
    } else if ( !fin_save_div.equals( row.getFinSaveDiv() ) ) {
        return false;
    }
    if ( fin_account_no == null ) {
      if ( row.getFinAccountNo() != null )
        return false;
    } else if ( !fin_account_no.equals( row.getFinAccountNo() ) ) {
        return false;
    }
    if ( postal_save_code == null ) {
      if ( row.getPostalSaveCode() != null )
        return false;
    } else if ( !postal_save_code.equals( row.getPostalSaveCode() ) ) {
        return false;
    }
    if ( postal_save_no == null ) {
      if ( row.getPostalSaveNo() != null )
        return false;
    } else if ( !postal_save_no.equals( row.getPostalSaveNo() ) ) {
        return false;
    }
    if ( fin_account_name == null ) {
      if ( row.getFinAccountName() != null )
        return false;
    } else if ( !fin_account_name.equals( row.getFinAccountName() ) ) {
        return false;
    }
    if ( trans_commission == null ) {
      if ( row.getTransCommission() != null )
        return false;
    } else if ( !trans_commission.equals( row.getTransCommission() ) ) {
        return false;
    }
    if ( experience_div_equity == null ) {
      if ( row.getExperienceDivEquity() != null )
        return false;
    } else if ( !experience_div_equity.equals( row.getExperienceDivEquity() ) ) {
        return false;
    }
    if ( experience_div_margin == null ) {
      if ( row.getExperienceDivMargin() != null )
        return false;
    } else if ( !experience_div_margin.equals( row.getExperienceDivMargin() ) ) {
        return false;
    }
    if ( experience_div_bond == null ) {
      if ( row.getExperienceDivBond() != null )
        return false;
    } else if ( !experience_div_bond.equals( row.getExperienceDivBond() ) ) {
        return false;
    }
    if ( experience_div_wt == null ) {
      if ( row.getExperienceDivWt() != null )
        return false;
    } else if ( !experience_div_wt.equals( row.getExperienceDivWt() ) ) {
        return false;
    }
    if ( experience_div_fund_sk == null ) {
      if ( row.getExperienceDivFundSk() != null )
        return false;
    } else if ( !experience_div_fund_sk.equals( row.getExperienceDivFundSk() ) ) {
        return false;
    }
    if ( experience_div_fund_bd == null ) {
      if ( row.getExperienceDivFundBd() != null )
        return false;
    } else if ( !experience_div_fund_bd.equals( row.getExperienceDivFundBd() ) ) {
        return false;
    }
    if ( experience_div_fo == null ) {
      if ( row.getExperienceDivFo() != null )
        return false;
    } else if ( !experience_div_fo.equals( row.getExperienceDivFo() ) ) {
        return false;
    }
    if ( experience_div_f_equity == null ) {
      if ( row.getExperienceDivFEquity() != null )
        return false;
    } else if ( !experience_div_f_equity.equals( row.getExperienceDivFEquity() ) ) {
        return false;
    }
    if ( experience_div_etc == null ) {
      if ( row.getExperienceDivEtc() != null )
        return false;
    } else if ( !experience_div_etc.equals( row.getExperienceDivEtc() ) ) {
        return false;
    }
    if ( experience_flag_equity == null ) {
      if ( row.getExperienceFlagEquity() != null )
        return false;
    } else if ( !experience_flag_equity.equals( row.getExperienceFlagEquity() ) ) {
        return false;
    }
    if ( experience_flag_margin == null ) {
      if ( row.getExperienceFlagMargin() != null )
        return false;
    } else if ( !experience_flag_margin.equals( row.getExperienceFlagMargin() ) ) {
        return false;
    }
    if ( experience_flag_bond == null ) {
      if ( row.getExperienceFlagBond() != null )
        return false;
    } else if ( !experience_flag_bond.equals( row.getExperienceFlagBond() ) ) {
        return false;
    }
    if ( experience_flag_wt == null ) {
      if ( row.getExperienceFlagWt() != null )
        return false;
    } else if ( !experience_flag_wt.equals( row.getExperienceFlagWt() ) ) {
        return false;
    }
    if ( experience_flag_fund_sk == null ) {
      if ( row.getExperienceFlagFundSk() != null )
        return false;
    } else if ( !experience_flag_fund_sk.equals( row.getExperienceFlagFundSk() ) ) {
        return false;
    }
    if ( experience_flag_fund_bd == null ) {
      if ( row.getExperienceFlagFundBd() != null )
        return false;
    } else if ( !experience_flag_fund_bd.equals( row.getExperienceFlagFundBd() ) ) {
        return false;
    }
    if ( experience_flag_fo == null ) {
      if ( row.getExperienceFlagFo() != null )
        return false;
    } else if ( !experience_flag_fo.equals( row.getExperienceFlagFo() ) ) {
        return false;
    }
    if ( experience_flag_f_equity == null ) {
      if ( row.getExperienceFlagFEquity() != null )
        return false;
    } else if ( !experience_flag_f_equity.equals( row.getExperienceFlagFEquity() ) ) {
        return false;
    }
    if ( experience_flag_etc == null ) {
      if ( row.getExperienceFlagEtc() != null )
        return false;
    } else if ( !experience_flag_etc.equals( row.getExperienceFlagEtc() ) ) {
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
    if ( interest_flag_equity == null ) {
      if ( row.getInterestFlagEquity() != null )
        return false;
    } else if ( !interest_flag_equity.equals( row.getInterestFlagEquity() ) ) {
        return false;
    }
    if ( interest_flag_ministock == null ) {
      if ( row.getInterestFlagMinistock() != null )
        return false;
    } else if ( !interest_flag_ministock.equals( row.getInterestFlagMinistock() ) ) {
        return false;
    }
    if ( interest_flag_margin == null ) {
      if ( row.getInterestFlagMargin() != null )
        return false;
    } else if ( !interest_flag_margin.equals( row.getInterestFlagMargin() ) ) {
        return false;
    }
    if ( interest_flag_bond == null ) {
      if ( row.getInterestFlagBond() != null )
        return false;
    } else if ( !interest_flag_bond.equals( row.getInterestFlagBond() ) ) {
        return false;
    }
    if ( interest_flag_fund == null ) {
      if ( row.getInterestFlagFund() != null )
        return false;
    } else if ( !interest_flag_fund.equals( row.getInterestFlagFund() ) ) {
        return false;
    }
    if ( interest_flag_fo == null ) {
      if ( row.getInterestFlagFo() != null )
        return false;
    } else if ( !interest_flag_fo.equals( row.getInterestFlagFo() ) ) {
        return false;
    }
    if ( interest_flag_f_equity == null ) {
      if ( row.getInterestFlagFEquity() != null )
        return false;
    } else if ( !interest_flag_f_equity.equals( row.getInterestFlagFEquity() ) ) {
        return false;
    }
    if ( interest_flag_etc == null ) {
      if ( row.getInterestFlagEtc() != null )
        return false;
    } else if ( !interest_flag_etc.equals( row.getInterestFlagEtc() ) ) {
        return false;
    }
    if ( invest_purpose_div == null ) {
      if ( row.getInvestPurposeDiv() != null )
        return false;
    } else if ( !invest_purpose_div.equals( row.getInvestPurposeDiv() ) ) {
        return false;
    }
    if ( appli_motivat_div == null ) {
      if ( row.getAppliMotivatDiv() != null )
        return false;
    } else if ( !appli_motivat_div.equals( row.getAppliMotivatDiv() ) ) {
        return false;
    }
    if ( annual_income_div == null ) {
      if ( row.getAnnualIncomeDiv() != null )
        return false;
    } else if ( !annual_income_div.equals( row.getAnnualIncomeDiv() ) ) {
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
    if ( asset_value_div == null ) {
      if ( row.getAssetValueDiv() != null )
        return false;
    } else if ( !asset_value_div.equals( row.getAssetValueDiv() ) ) {
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
    if ( fund_budget_amount_div == null ) {
      if ( row.getFundBudgetAmountDiv() != null )
        return false;
    } else if ( !fund_budget_amount_div.equals( row.getFundBudgetAmountDiv() ) ) {
        return false;
    }
    if ( fund_budget_div == null ) {
      if ( row.getFundBudgetDiv() != null )
        return false;
    } else if ( !fund_budget_div.equals( row.getFundBudgetDiv() ) ) {
        return false;
    }
    if ( fund_budget_etc == null ) {
      if ( row.getFundBudgetEtc() != null )
        return false;
    } else if ( !fund_budget_etc.equals( row.getFundBudgetEtc() ) ) {
        return false;
    }
    if ( id_confirm_flag == null ) {
      if ( row.getIdConfirmFlag() != null )
        return false;
    } else if ( !id_confirm_flag.equals( row.getIdConfirmFlag() ) ) {
        return false;
    }
    if ( id_confirm_doc_div == null ) {
      if ( row.getIdConfirmDocDiv() != null )
        return false;
    } else if ( !id_confirm_doc_div.equals( row.getIdConfirmDocDiv() ) ) {
        return false;
    }
    if ( id_confirm_doc_etc == null ) {
      if ( row.getIdConfirmDocEtc() != null )
        return false;
    } else if ( !id_confirm_doc_etc.equals( row.getIdConfirmDocEtc() ) ) {
        return false;
    }
    if ( special_acc == null ) {
      if ( row.getSpecialAcc() != null )
        return false;
    } else if ( !special_acc.equals( row.getSpecialAcc() ) ) {
        return false;
    }
    if ( special_acc_margin == null ) {
      if ( row.getSpecialAccMargin() != null )
        return false;
    } else if ( !special_acc_margin.equals( row.getSpecialAccMargin() ) ) {
        return false;
    }
    if ( insider_flag == null ) {
      if ( row.getInsiderFlag() != null )
        return false;
    } else if ( !insider_flag.equals( row.getInsiderFlag() ) ) {
        return false;
    }
    if ( product_name == null ) {
      if ( row.getProductName() != null )
        return false;
    } else if ( !product_name.equals( row.getProductName() ) ) {
        return false;
    }
    if ( send_zip_code == null ) {
      if ( row.getSendZipCode() != null )
        return false;
    } else if ( !send_zip_code.equals( row.getSendZipCode() ) ) {
        return false;
    }
    if ( send_address_line1 == null ) {
      if ( row.getSendAddressLine1() != null )
        return false;
    } else if ( !send_address_line1.equals( row.getSendAddressLine1() ) ) {
        return false;
    }
    if ( send_address_line2 == null ) {
      if ( row.getSendAddressLine2() != null )
        return false;
    } else if ( !send_address_line2.equals( row.getSendAddressLine2() ) ) {
        return false;
    }
    if ( send_address_line3 == null ) {
      if ( row.getSendAddressLine3() != null )
        return false;
    } else if ( !send_address_line3.equals( row.getSendAddressLine3() ) ) {
        return false;
    }
    if ( ext_item_div1 == null ) {
      if ( row.getExtItemDiv1() != null )
        return false;
    } else if ( !ext_item_div1.equals( row.getExtItemDiv1() ) ) {
        return false;
    }
    if ( ext_item_div2 == null ) {
      if ( row.getExtItemDiv2() != null )
        return false;
    } else if ( !ext_item_div2.equals( row.getExtItemDiv2() ) ) {
        return false;
    }
    if ( ext_item_div3 == null ) {
      if ( row.getExtItemDiv3() != null )
        return false;
    } else if ( !ext_item_div3.equals( row.getExtItemDiv3() ) ) {
        return false;
    }
    if ( ext_item_div4 == null ) {
      if ( row.getExtItemDiv4() != null )
        return false;
    } else if ( !ext_item_div4.equals( row.getExtItemDiv4() ) ) {
        return false;
    }
    if ( ext_item_div5 == null ) {
      if ( row.getExtItemDiv5() != null )
        return false;
    } else if ( !ext_item_div5.equals( row.getExtItemDiv5() ) ) {
        return false;
    }
    if ( ext_item_div6 == null ) {
      if ( row.getExtItemDiv6() != null )
        return false;
    } else if ( !ext_item_div6.equals( row.getExtItemDiv6() ) ) {
        return false;
    }
    if ( ext_item_div7 == null ) {
      if ( row.getExtItemDiv7() != null )
        return false;
    } else if ( !ext_item_div7.equals( row.getExtItemDiv7() ) ) {
        return false;
    }
    if ( ext_item_div8 == null ) {
      if ( row.getExtItemDiv8() != null )
        return false;
    } else if ( !ext_item_div8.equals( row.getExtItemDiv8() ) ) {
        return false;
    }
    if ( ext_item_div9 == null ) {
      if ( row.getExtItemDiv9() != null )
        return false;
    } else if ( !ext_item_div9.equals( row.getExtItemDiv9() ) ) {
        return false;
    }
    if ( ext_item_div10 == null ) {
      if ( row.getExtItemDiv10() != null )
        return false;
    } else if ( !ext_item_div10.equals( row.getExtItemDiv10() ) ) {
        return false;
    }
    if ( ext_item_flag1 == null ) {
      if ( row.getExtItemFlag1() != null )
        return false;
    } else if ( !ext_item_flag1.equals( row.getExtItemFlag1() ) ) {
        return false;
    }
    if ( ext_item_flag2 == null ) {
      if ( row.getExtItemFlag2() != null )
        return false;
    } else if ( !ext_item_flag2.equals( row.getExtItemFlag2() ) ) {
        return false;
    }
    if ( ext_item_flag3 == null ) {
      if ( row.getExtItemFlag3() != null )
        return false;
    } else if ( !ext_item_flag3.equals( row.getExtItemFlag3() ) ) {
        return false;
    }
    if ( ext_item_flag4 == null ) {
      if ( row.getExtItemFlag4() != null )
        return false;
    } else if ( !ext_item_flag4.equals( row.getExtItemFlag4() ) ) {
        return false;
    }
    if ( ext_item_flag5 == null ) {
      if ( row.getExtItemFlag5() != null )
        return false;
    } else if ( !ext_item_flag5.equals( row.getExtItemFlag5() ) ) {
        return false;
    }
    if ( ext_item_flag6 == null ) {
      if ( row.getExtItemFlag6() != null )
        return false;
    } else if ( !ext_item_flag6.equals( row.getExtItemFlag6() ) ) {
        return false;
    }
    if ( ext_item_flag7 == null ) {
      if ( row.getExtItemFlag7() != null )
        return false;
    } else if ( !ext_item_flag7.equals( row.getExtItemFlag7() ) ) {
        return false;
    }
    if ( ext_item_flag8 == null ) {
      if ( row.getExtItemFlag8() != null )
        return false;
    } else if ( !ext_item_flag8.equals( row.getExtItemFlag8() ) ) {
        return false;
    }
    if ( ext_item_flag9 == null ) {
      if ( row.getExtItemFlag9() != null )
        return false;
    } else if ( !ext_item_flag9.equals( row.getExtItemFlag9() ) ) {
        return false;
    }
    if ( ext_item_flag10 == null ) {
      if ( row.getExtItemFlag10() != null )
        return false;
    } else if ( !ext_item_flag10.equals( row.getExtItemFlag10() ) ) {
        return false;
    }
    if ( ext_item_text1 == null ) {
      if ( row.getExtItemText1() != null )
        return false;
    } else if ( !ext_item_text1.equals( row.getExtItemText1() ) ) {
        return false;
    }
    if ( ext_item_text2 == null ) {
      if ( row.getExtItemText2() != null )
        return false;
    } else if ( !ext_item_text2.equals( row.getExtItemText2() ) ) {
        return false;
    }
    if ( ext_item_text3 == null ) {
      if ( row.getExtItemText3() != null )
        return false;
    } else if ( !ext_item_text3.equals( row.getExtItemText3() ) ) {
        return false;
    }
    if ( ext_item_text4 == null ) {
      if ( row.getExtItemText4() != null )
        return false;
    } else if ( !ext_item_text4.equals( row.getExtItemText4() ) ) {
        return false;
    }
    if ( ext_item_text5 == null ) {
      if ( row.getExtItemText5() != null )
        return false;
    } else if ( !ext_item_text5.equals( row.getExtItemText5() ) ) {
        return false;
    }
    if ( ext_item_text6 == null ) {
      if ( row.getExtItemText6() != null )
        return false;
    } else if ( !ext_item_text6.equals( row.getExtItemText6() ) ) {
        return false;
    }
    if ( ext_item_text7 == null ) {
      if ( row.getExtItemText7() != null )
        return false;
    } else if ( !ext_item_text7.equals( row.getExtItemText7() ) ) {
        return false;
    }
    if ( ext_item_text8 == null ) {
      if ( row.getExtItemText8() != null )
        return false;
    } else if ( !ext_item_text8.equals( row.getExtItemText8() ) ) {
        return false;
    }
    if ( ext_item_text9 == null ) {
      if ( row.getExtItemText9() != null )
        return false;
    } else if ( !ext_item_text9.equals( row.getExtItemText9() ) ) {
        return false;
    }
    if ( ext_item_text10 == null ) {
      if ( row.getExtItemText10() != null )
        return false;
    } else if ( !ext_item_text10.equals( row.getExtItemText10() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
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
    if ( creator == null ) {
      if ( row.getCreator() != null )
        return false;
    } else if ( !creator.equals( row.getCreator() ) ) {
        return false;
    }
    if ( exclusive_use_account_no == null ) {
      if ( row.getExclusiveUseAccountNo() != null )
        return false;
    } else if ( !exclusive_use_account_no.equals( row.getExclusiveUseAccountNo() ) ) {
        return false;
    }
    if ( send_timestamp == null ) {
      if ( row.getSendTimestamp() != null )
        return false;
    } else if ( !send_timestamp.equals( row.getSendTimestamp() ) ) {
        return false;
    }
    if ( real_name_voucher_div == null ) {
      if ( row.getRealNameVoucherDiv() != null )
        return false;
    } else if ( !real_name_voucher_div.equals( row.getRealNameVoucherDiv() ) ) {
        return false;
    }
    if ( real_name1 == null ) {
      if ( row.getRealName1() != null )
        return false;
    } else if ( !real_name1.equals( row.getRealName1() ) ) {
        return false;
    }
    if ( real_name2 == null ) {
      if ( row.getRealName2() != null )
        return false;
    } else if ( !real_name2.equals( row.getRealName2() ) ) {
        return false;
    }
    if ( insider_voucher_div == null ) {
      if ( row.getInsiderVoucherDiv() != null )
        return false;
    } else if ( !insider_voucher_div.equals( row.getInsiderVoucherDiv() ) ) {
        return false;
    }
    if ( insider_product_code == null ) {
      if ( row.getInsiderProductCode() != null )
        return false;
    } else if ( !insider_product_code.equals( row.getInsiderProductCode() ) ) {
        return false;
    }
    if ( insider_relation_div == null ) {
      if ( row.getInsiderRelationDiv() != null )
        return false;
    } else if ( !insider_relation_div.equals( row.getInsiderRelationDiv() ) ) {
        return false;
    }
    if ( insider_officer_name == null ) {
      if ( row.getInsiderOfficerName() != null )
        return false;
    } else if ( !insider_officer_name.equals( row.getInsiderOfficerName() ) ) {
        return false;
    }
    if ( insider_post_code == null ) {
      if ( row.getInsiderPostCode() != null )
        return false;
    } else if ( !insider_post_code.equals( row.getInsiderPostCode() ) ) {
        return false;
    }
    if ( insider_post_name == null ) {
      if ( row.getInsiderPostName() != null )
        return false;
    } else if ( !insider_post_name.equals( row.getInsiderPostName() ) ) {
        return false;
    }
    if ( gp_voucher_div == null ) {
      if ( row.getGpVoucherDiv() != null )
        return false;
    } else if ( !gp_voucher_div.equals( row.getGpVoucherDiv() ) ) {
        return false;
    }
    if ( gp_course == null ) {
      if ( row.getGpCourse() != null )
        return false;
    } else if ( !gp_course.equals( row.getGpCourse() ) ) {
        return false;
    }
    if ( gp_plan == null ) {
      if ( row.getGpPlan() != null )
        return false;
    } else if ( !gp_plan.equals( row.getGpPlan() ) ) {
        return false;
    }
    if ( gp_target_figure == null ) {
      if ( row.getGpTargetFigure() != null )
        return false;
    } else if ( !gp_target_figure.equals( row.getGpTargetFigure() ) ) {
        return false;
    }
    if ( gp_target_year == null ) {
      if ( row.getGpTargetYear() != null )
        return false;
    } else if ( !gp_target_year.equals( row.getGpTargetYear() ) ) {
        return false;
    }
    if ( gp_target_month == null ) {
      if ( row.getGpTargetMonth() != null )
        return false;
    } else if ( !gp_target_month.equals( row.getGpTargetMonth() ) ) {
        return false;
    }
    if ( gp_installment_figure == null ) {
      if ( row.getGpInstallmentFigure() != null )
        return false;
    } else if ( !gp_installment_figure.equals( row.getGpInstallmentFigure() ) ) {
        return false;
    }
    if ( gp_deposit_cycle == null ) {
      if ( row.getGpDepositCycle() != null )
        return false;
    } else if ( !gp_deposit_cycle.equals( row.getGpDepositCycle() ) ) {
        return false;
    }
    if ( gp_payment_root == null ) {
      if ( row.getGpPaymentRoot() != null )
        return false;
    } else if ( !gp_payment_root.equals( row.getGpPaymentRoot() ) ) {
        return false;
    }
    if ( gp_reinvest_div == null ) {
      if ( row.getGpReinvestDiv() != null )
        return false;
    } else if ( !gp_reinvest_div.equals( row.getGpReinvestDiv() ) ) {
        return false;
    }
    if ( gp_tax_div == null ) {
      if ( row.getGpTaxDiv() != null )
        return false;
    } else if ( !gp_tax_div.equals( row.getGpTaxDiv() ) ) {
        return false;
    }
    if ( gp_taxfree_limit == null ) {
      if ( row.getGpTaxfreeLimit() != null )
        return false;
    } else if ( !gp_taxfree_limit.equals( row.getGpTaxfreeLimit() ) ) {
        return false;
    }
    if ( gp_special_taxfree_limit == null ) {
      if ( row.getGpSpecialTaxfreeLimit() != null )
        return false;
    } else if ( !gp_special_taxfree_limit.equals( row.getGpSpecialTaxfreeLimit() ) ) {
        return false;
    }
    if ( gp_subscr_summary == null ) {
      if ( row.getGpSubscrSummary() != null )
        return false;
    } else if ( !gp_subscr_summary.equals( row.getGpSubscrSummary() ) ) {
        return false;
    }
    if ( gp_product_code == null ) {
      if ( row.getGpProductCode() != null )
        return false;
    } else if ( !gp_product_code.equals( row.getGpProductCode() ) ) {
        return false;
    }
    if ( gp_mortgage_customer == null ) {
      if ( row.getGpMortgageCustomer() != null )
        return false;
    } else if ( !gp_mortgage_customer.equals( row.getGpMortgageCustomer() ) ) {
        return false;
    }
    if ( gp_mix_customer == null ) {
      if ( row.getGpMixCustomer() != null )
        return false;
    } else if ( !gp_mix_customer.equals( row.getGpMixCustomer() ) ) {
        return false;
    }
    if ( gp_contract == null ) {
      if ( row.getGpContract() != null )
        return false;
    } else if ( !gp_contract.equals( row.getGpContract() ) ) {
        return false;
    }
    if ( stk_voucher_div == null ) {
      if ( row.getStkVoucherDiv() != null )
        return false;
    } else if ( !stk_voucher_div.equals( row.getStkVoucherDiv() ) ) {
        return false;
    }
    if ( stk_taxation_tran_div == null ) {
      if ( row.getStkTaxationTranDiv() != null )
        return false;
    } else if ( !stk_taxation_tran_div.equals( row.getStkTaxationTranDiv() ) ) {
        return false;
    }
    if ( stk_address_line_kana == null ) {
      if ( row.getStkAddressLineKana() != null )
        return false;
    } else if ( !stk_address_line_kana.equals( row.getStkAddressLineKana() ) ) {
        return false;
    }
    if ( stk_transfer_div == null ) {
      if ( row.getStkTransferDiv() != null )
        return false;
    } else if ( !stk_transfer_div.equals( row.getStkTransferDiv() ) ) {
        return false;
    }
    if ( stk_fin_institution_code == null ) {
      if ( row.getStkFinInstitutionCode() != null )
        return false;
    } else if ( !stk_fin_institution_code.equals( row.getStkFinInstitutionCode() ) ) {
        return false;
    }
    if ( stk_fin_branch_code == null ) {
      if ( row.getStkFinBranchCode() != null )
        return false;
    } else if ( !stk_fin_branch_code.equals( row.getStkFinBranchCode() ) ) {
        return false;
    }
    if ( stk_fin_save_div == null ) {
      if ( row.getStkFinSaveDiv() != null )
        return false;
    } else if ( !stk_fin_save_div.equals( row.getStkFinSaveDiv() ) ) {
        return false;
    }
    if ( stk_fin_account_no == null ) {
      if ( row.getStkFinAccountNo() != null )
        return false;
    } else if ( !stk_fin_account_no.equals( row.getStkFinAccountNo() ) ) {
        return false;
    }
    if ( brokerage_trader_code == null ) {
      if ( row.getBrokerageTraderCode() != null )
        return false;
    } else if ( !brokerage_trader_code.equals( row.getBrokerageTraderCode() ) ) {
        return false;
    }
    if ( ext_item_div11 == null ) {
      if ( row.getExtItemDiv11() != null )
        return false;
    } else if ( !ext_item_div11.equals( row.getExtItemDiv11() ) ) {
        return false;
    }
    if ( ext_item_div12 == null ) {
      if ( row.getExtItemDiv12() != null )
        return false;
    } else if ( !ext_item_div12.equals( row.getExtItemDiv12() ) ) {
        return false;
    }
    if ( ext_item_div13 == null ) {
      if ( row.getExtItemDiv13() != null )
        return false;
    } else if ( !ext_item_div13.equals( row.getExtItemDiv13() ) ) {
        return false;
    }
    if ( ext_item_div14 == null ) {
      if ( row.getExtItemDiv14() != null )
        return false;
    } else if ( !ext_item_div14.equals( row.getExtItemDiv14() ) ) {
        return false;
    }
    if ( ext_item_div15 == null ) {
      if ( row.getExtItemDiv15() != null )
        return false;
    } else if ( !ext_item_div15.equals( row.getExtItemDiv15() ) ) {
        return false;
    }
    if ( foreign_account_no == null ) {
      if ( row.getForeignAccountNo() != null )
        return false;
    } else if ( !foreign_account_no.equals( row.getForeignAccountNo() ) ) {
        return false;
    }
    if ( foreign_account_name == null ) {
      if ( row.getForeignAccountName() != null )
        return false;
    } else if ( !foreign_account_name.equals( row.getForeignAccountName() ) ) {
        return false;
    }
    if ( foreign_account_name_eng == null ) {
      if ( row.getForeignAccountNameEng() != null )
        return false;
    } else if ( !foreign_account_name_eng.equals( row.getForeignAccountNameEng() ) ) {
        return false;
    }
    if ( foreign_save_div == null ) {
      if ( row.getForeignSaveDiv() != null )
        return false;
    } else if ( !foreign_save_div.equals( row.getForeignSaveDiv() ) ) {
        return false;
    }
    if ( delete_flag == null ) {
      if ( row.getDeleteFlag() != null )
        return false;
    } else if ( !delete_flag.equals( row.getDeleteFlag() ) ) {
        return false;
    }
    if ( delete_timestamp == null ) {
      if ( row.getDeleteTimestamp() != null )
        return false;
    } else if ( !delete_timestamp.equals( row.getDeleteTimestamp() ) ) {
        return false;
    }
    if ( print_flag == null ) {
      if ( row.getPrintFlag() != null )
        return false;
    } else if ( !print_flag.equals( row.getPrintFlag() ) ) {
        return false;
    }
    if ( receipt_flag == null ) {
      if ( row.getReceiptFlag() != null )
        return false;
    } else if ( !receipt_flag.equals( row.getReceiptFlag() ) ) {
        return false;
    }
    if ( agreement_flag == null ) {
      if ( row.getAgreementFlag() != null )
        return false;
    } else if ( !agreement_flag.equals( row.getAgreementFlag() ) ) {
        return false;
    }
    if ( foreign_flag == null ) {
      if ( row.getForeignFlag() != null )
        return false;
    } else if ( !foreign_flag.equals( row.getForeignFlag() ) ) {
        return false;
    }
    if ( agency_acc_name_kana1 == null ) {
      if ( row.getAgencyAccNameKana1() != null )
        return false;
    } else if ( !agency_acc_name_kana1.equals( row.getAgencyAccNameKana1() ) ) {
        return false;
    }
    if ( agency_acc_name_kana2 == null ) {
      if ( row.getAgencyAccNameKana2() != null )
        return false;
    } else if ( !agency_acc_name_kana2.equals( row.getAgencyAccNameKana2() ) ) {
        return false;
    }
    if ( agency_acc_name1 == null ) {
      if ( row.getAgencyAccName1() != null )
        return false;
    } else if ( !agency_acc_name1.equals( row.getAgencyAccName1() ) ) {
        return false;
    }
    if ( agency_acc_name2 == null ) {
      if ( row.getAgencyAccName2() != null )
        return false;
    } else if ( !agency_acc_name2.equals( row.getAgencyAccName2() ) ) {
        return false;
    }
    if ( agency_address_line1 == null ) {
      if ( row.getAgencyAddressLine1() != null )
        return false;
    } else if ( !agency_address_line1.equals( row.getAgencyAddressLine1() ) ) {
        return false;
    }
    if ( agency_address_line2 == null ) {
      if ( row.getAgencyAddressLine2() != null )
        return false;
    } else if ( !agency_address_line2.equals( row.getAgencyAddressLine2() ) ) {
        return false;
    }
    if ( agency_rep_post == null ) {
      if ( row.getAgencyRepPost() != null )
        return false;
    } else if ( !agency_rep_post.equals( row.getAgencyRepPost() ) ) {
        return false;
    }
    if ( agency_rep_name_kana1 == null ) {
      if ( row.getAgencyRepNameKana1() != null )
        return false;
    } else if ( !agency_rep_name_kana1.equals( row.getAgencyRepNameKana1() ) ) {
        return false;
    }
    if ( agency_rep_name_kana2 == null ) {
      if ( row.getAgencyRepNameKana2() != null )
        return false;
    } else if ( !agency_rep_name_kana2.equals( row.getAgencyRepNameKana2() ) ) {
        return false;
    }
    if ( agency_rep_name1 == null ) {
      if ( row.getAgencyRepName1() != null )
        return false;
    } else if ( !agency_rep_name1.equals( row.getAgencyRepName1() ) ) {
        return false;
    }
    if ( agency_rep_name2 == null ) {
      if ( row.getAgencyRepName2() != null )
        return false;
    } else if ( !agency_rep_name2.equals( row.getAgencyRepName2() ) ) {
        return false;
    }
    if ( status == null ) {
      if ( row.getStatus() != null )
        return false;
    } else if ( !status.equals( row.getStatus() ) ) {
        return false;
    }
    if ( error_info == null ) {
      if ( row.getErrorInfo() != null )
        return false;
    } else if ( !error_info.equals( row.getErrorInfo() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) institution_id)
        + ((int) branch_id)
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (acc_open_request_number!=null? acc_open_request_number.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (sonar_trader_code!=null? sonar_trader_code.hashCode(): 0) 
        + (ex_account_flag!=null? ex_account_flag.hashCode(): 0) 
        + (ex_branch_name!=null? ex_branch_name.hashCode(): 0) 
        + (ex_account_code!=null? ex_account_code.hashCode(): 0) 
        + (account_div!=null? account_div.hashCode(): 0) 
        + (order_div!=null? order_div.hashCode(): 0) 
        + (infomation_claim_datetime!=null? infomation_claim_datetime.hashCode(): 0) 
        + (account_open_date!=null? account_open_date.hashCode(): 0) 
        + (initial_password!=null? initial_password.hashCode(): 0) 
        + (family_name!=null? family_name.hashCode(): 0) 
        + (given_name!=null? given_name.hashCode(): 0) 
        + (family_name_alt1!=null? family_name_alt1.hashCode(): 0) 
        + (given_name_alt1!=null? given_name_alt1.hashCode(): 0) 
        + (sex!=null? sex.hashCode(): 0) 
        + (era_born!=null? era_born.hashCode(): 0) 
        + (born_date!=null? born_date.hashCode(): 0) 
        + (email_address!=null? email_address.hashCode(): 0) 
        + (email_address_alt1!=null? email_address_alt1.hashCode(): 0) 
        + (zip_code!=null? zip_code.hashCode(): 0) 
        + (address_line1!=null? address_line1.hashCode(): 0) 
        + (address_line2!=null? address_line2.hashCode(): 0) 
        + (address_line3!=null? address_line3.hashCode(): 0) 
        + (address_line1_kana!=null? address_line1_kana.hashCode(): 0) 
        + (address_line2_kana!=null? address_line2_kana.hashCode(): 0) 
        + (address_line3_kana!=null? address_line3_kana.hashCode(): 0) 
        + (telephone!=null? telephone.hashCode(): 0) 
        + (mobile!=null? mobile.hashCode(): 0) 
        + (fax!=null? fax.hashCode(): 0) 
        + (occupation_div!=null? occupation_div.hashCode(): 0) 
        + (office!=null? office.hashCode(): 0) 
        + (office_zip_code!=null? office_zip_code.hashCode(): 0) 
        + (office_address!=null? office_address.hashCode(): 0) 
        + (office_telephone!=null? office_telephone.hashCode(): 0) 
        + (office_fax!=null? office_fax.hashCode(): 0) 
        + (department!=null? department.hashCode(): 0) 
        + (post!=null? post.hashCode(): 0) 
        + (contact_address!=null? contact_address.hashCode(): 0) 
        + (contact_telephone!=null? contact_telephone.hashCode(): 0) 
        + (family_relationship!=null? family_relationship.hashCode(): 0) 
        + (family_relationship_etc!=null? family_relationship_etc.hashCode(): 0) 
        + (householder!=null? householder.hashCode(): 0) 
        + (householder_kana!=null? householder_kana.hashCode(): 0) 
        + (householder_occupation_div!=null? householder_occupation_div.hashCode(): 0) 
        + (householder_office!=null? householder_office.hashCode(): 0) 
        + (householder_office_address!=null? householder_office_address.hashCode(): 0) 
        + (householder_department!=null? householder_department.hashCode(): 0) 
        + (householder_office_tel!=null? householder_office_tel.hashCode(): 0) 
        + (householder_office_fax!=null? householder_office_fax.hashCode(): 0) 
        + (householder_post!=null? householder_post.hashCode(): 0) 
        + (resident!=null? resident.hashCode(): 0) 
        + (transfer_div!=null? transfer_div.hashCode(): 0) 
        + (fin_institution_code!=null? fin_institution_code.hashCode(): 0) 
        + (fin_institution_name!=null? fin_institution_name.hashCode(): 0) 
        + (fin_branch_code!=null? fin_branch_code.hashCode(): 0) 
        + (fin_branch_name!=null? fin_branch_name.hashCode(): 0) 
        + (fin_save_div!=null? fin_save_div.hashCode(): 0) 
        + (fin_account_no!=null? fin_account_no.hashCode(): 0) 
        + (postal_save_code!=null? postal_save_code.hashCode(): 0) 
        + (postal_save_no!=null? postal_save_no.hashCode(): 0) 
        + (fin_account_name!=null? fin_account_name.hashCode(): 0) 
        + (trans_commission!=null? trans_commission.hashCode(): 0) 
        + (experience_div_equity!=null? experience_div_equity.hashCode(): 0) 
        + (experience_div_margin!=null? experience_div_margin.hashCode(): 0) 
        + (experience_div_bond!=null? experience_div_bond.hashCode(): 0) 
        + (experience_div_wt!=null? experience_div_wt.hashCode(): 0) 
        + (experience_div_fund_sk!=null? experience_div_fund_sk.hashCode(): 0) 
        + (experience_div_fund_bd!=null? experience_div_fund_bd.hashCode(): 0) 
        + (experience_div_fo!=null? experience_div_fo.hashCode(): 0) 
        + (experience_div_f_equity!=null? experience_div_f_equity.hashCode(): 0) 
        + (experience_div_etc!=null? experience_div_etc.hashCode(): 0) 
        + (experience_flag_equity!=null? experience_flag_equity.hashCode(): 0) 
        + (experience_flag_margin!=null? experience_flag_margin.hashCode(): 0) 
        + (experience_flag_bond!=null? experience_flag_bond.hashCode(): 0) 
        + (experience_flag_wt!=null? experience_flag_wt.hashCode(): 0) 
        + (experience_flag_fund_sk!=null? experience_flag_fund_sk.hashCode(): 0) 
        + (experience_flag_fund_bd!=null? experience_flag_fund_bd.hashCode(): 0) 
        + (experience_flag_fo!=null? experience_flag_fo.hashCode(): 0) 
        + (experience_flag_f_equity!=null? experience_flag_f_equity.hashCode(): 0) 
        + (experience_flag_etc!=null? experience_flag_etc.hashCode(): 0) 
        + (experience_from!=null? experience_from.hashCode(): 0) 
        + (experience_to!=null? experience_to.hashCode(): 0) 
        + (interest_flag_equity!=null? interest_flag_equity.hashCode(): 0) 
        + (interest_flag_ministock!=null? interest_flag_ministock.hashCode(): 0) 
        + (interest_flag_margin!=null? interest_flag_margin.hashCode(): 0) 
        + (interest_flag_bond!=null? interest_flag_bond.hashCode(): 0) 
        + (interest_flag_fund!=null? interest_flag_fund.hashCode(): 0) 
        + (interest_flag_fo!=null? interest_flag_fo.hashCode(): 0) 
        + (interest_flag_f_equity!=null? interest_flag_f_equity.hashCode(): 0) 
        + (interest_flag_etc!=null? interest_flag_etc.hashCode(): 0) 
        + (invest_purpose_div!=null? invest_purpose_div.hashCode(): 0) 
        + (appli_motivat_div!=null? appli_motivat_div.hashCode(): 0) 
        + (annual_income_div!=null? annual_income_div.hashCode(): 0) 
        + (annual_income_from!=null? annual_income_from.hashCode(): 0) 
        + (annual_income_to!=null? annual_income_to.hashCode(): 0) 
        + (asset_value_div!=null? asset_value_div.hashCode(): 0) 
        + (asset_value_from!=null? asset_value_from.hashCode(): 0) 
        + (asset_value_to!=null? asset_value_to.hashCode(): 0) 
        + (fund_budget_amount_div!=null? fund_budget_amount_div.hashCode(): 0) 
        + (fund_budget_div!=null? fund_budget_div.hashCode(): 0) 
        + (fund_budget_etc!=null? fund_budget_etc.hashCode(): 0) 
        + (id_confirm_flag!=null? id_confirm_flag.hashCode(): 0) 
        + (id_confirm_doc_div!=null? id_confirm_doc_div.hashCode(): 0) 
        + (id_confirm_doc_etc!=null? id_confirm_doc_etc.hashCode(): 0) 
        + (special_acc!=null? special_acc.hashCode(): 0) 
        + (special_acc_margin!=null? special_acc_margin.hashCode(): 0) 
        + (insider_flag!=null? insider_flag.hashCode(): 0) 
        + (product_name!=null? product_name.hashCode(): 0) 
        + (send_zip_code!=null? send_zip_code.hashCode(): 0) 
        + (send_address_line1!=null? send_address_line1.hashCode(): 0) 
        + (send_address_line2!=null? send_address_line2.hashCode(): 0) 
        + (send_address_line3!=null? send_address_line3.hashCode(): 0) 
        + (ext_item_div1!=null? ext_item_div1.hashCode(): 0) 
        + (ext_item_div2!=null? ext_item_div2.hashCode(): 0) 
        + (ext_item_div3!=null? ext_item_div3.hashCode(): 0) 
        + (ext_item_div4!=null? ext_item_div4.hashCode(): 0) 
        + (ext_item_div5!=null? ext_item_div5.hashCode(): 0) 
        + (ext_item_div6!=null? ext_item_div6.hashCode(): 0) 
        + (ext_item_div7!=null? ext_item_div7.hashCode(): 0) 
        + (ext_item_div8!=null? ext_item_div8.hashCode(): 0) 
        + (ext_item_div9!=null? ext_item_div9.hashCode(): 0) 
        + (ext_item_div10!=null? ext_item_div10.hashCode(): 0) 
        + (ext_item_flag1!=null? ext_item_flag1.hashCode(): 0) 
        + (ext_item_flag2!=null? ext_item_flag2.hashCode(): 0) 
        + (ext_item_flag3!=null? ext_item_flag3.hashCode(): 0) 
        + (ext_item_flag4!=null? ext_item_flag4.hashCode(): 0) 
        + (ext_item_flag5!=null? ext_item_flag5.hashCode(): 0) 
        + (ext_item_flag6!=null? ext_item_flag6.hashCode(): 0) 
        + (ext_item_flag7!=null? ext_item_flag7.hashCode(): 0) 
        + (ext_item_flag8!=null? ext_item_flag8.hashCode(): 0) 
        + (ext_item_flag9!=null? ext_item_flag9.hashCode(): 0) 
        + (ext_item_flag10!=null? ext_item_flag10.hashCode(): 0) 
        + (ext_item_text1!=null? ext_item_text1.hashCode(): 0) 
        + (ext_item_text2!=null? ext_item_text2.hashCode(): 0) 
        + (ext_item_text3!=null? ext_item_text3.hashCode(): 0) 
        + (ext_item_text4!=null? ext_item_text4.hashCode(): 0) 
        + (ext_item_text5!=null? ext_item_text5.hashCode(): 0) 
        + (ext_item_text6!=null? ext_item_text6.hashCode(): 0) 
        + (ext_item_text7!=null? ext_item_text7.hashCode(): 0) 
        + (ext_item_text8!=null? ext_item_text8.hashCode(): 0) 
        + (ext_item_text9!=null? ext_item_text9.hashCode(): 0) 
        + (ext_item_text10!=null? ext_item_text10.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (creator!=null? creator.hashCode(): 0) 
        + (exclusive_use_account_no!=null? exclusive_use_account_no.hashCode(): 0) 
        + (send_timestamp!=null? send_timestamp.hashCode(): 0) 
        + (real_name_voucher_div!=null? real_name_voucher_div.hashCode(): 0) 
        + (real_name1!=null? real_name1.hashCode(): 0) 
        + (real_name2!=null? real_name2.hashCode(): 0) 
        + (insider_voucher_div!=null? insider_voucher_div.hashCode(): 0) 
        + (insider_product_code!=null? insider_product_code.hashCode(): 0) 
        + (insider_relation_div!=null? insider_relation_div.hashCode(): 0) 
        + (insider_officer_name!=null? insider_officer_name.hashCode(): 0) 
        + (insider_post_code!=null? insider_post_code.hashCode(): 0) 
        + (insider_post_name!=null? insider_post_name.hashCode(): 0) 
        + (gp_voucher_div!=null? gp_voucher_div.hashCode(): 0) 
        + (gp_course!=null? gp_course.hashCode(): 0) 
        + (gp_plan!=null? gp_plan.hashCode(): 0) 
        + (gp_target_figure!=null? gp_target_figure.hashCode(): 0) 
        + (gp_target_year!=null? gp_target_year.hashCode(): 0) 
        + (gp_target_month!=null? gp_target_month.hashCode(): 0) 
        + (gp_installment_figure!=null? gp_installment_figure.hashCode(): 0) 
        + (gp_deposit_cycle!=null? gp_deposit_cycle.hashCode(): 0) 
        + (gp_payment_root!=null? gp_payment_root.hashCode(): 0) 
        + (gp_reinvest_div!=null? gp_reinvest_div.hashCode(): 0) 
        + (gp_tax_div!=null? gp_tax_div.hashCode(): 0) 
        + (gp_taxfree_limit!=null? gp_taxfree_limit.hashCode(): 0) 
        + (gp_special_taxfree_limit!=null? gp_special_taxfree_limit.hashCode(): 0) 
        + (gp_subscr_summary!=null? gp_subscr_summary.hashCode(): 0) 
        + (gp_product_code!=null? gp_product_code.hashCode(): 0) 
        + (gp_mortgage_customer!=null? gp_mortgage_customer.hashCode(): 0) 
        + (gp_mix_customer!=null? gp_mix_customer.hashCode(): 0) 
        + (gp_contract!=null? gp_contract.hashCode(): 0) 
        + (stk_voucher_div!=null? stk_voucher_div.hashCode(): 0) 
        + (stk_taxation_tran_div!=null? stk_taxation_tran_div.hashCode(): 0) 
        + (stk_address_line_kana!=null? stk_address_line_kana.hashCode(): 0) 
        + (stk_transfer_div!=null? stk_transfer_div.hashCode(): 0) 
        + (stk_fin_institution_code!=null? stk_fin_institution_code.hashCode(): 0) 
        + (stk_fin_branch_code!=null? stk_fin_branch_code.hashCode(): 0) 
        + (stk_fin_save_div!=null? stk_fin_save_div.hashCode(): 0) 
        + (stk_fin_account_no!=null? stk_fin_account_no.hashCode(): 0) 
        + (brokerage_trader_code!=null? brokerage_trader_code.hashCode(): 0) 
        + (ext_item_div11!=null? ext_item_div11.hashCode(): 0) 
        + (ext_item_div12!=null? ext_item_div12.hashCode(): 0) 
        + (ext_item_div13!=null? ext_item_div13.hashCode(): 0) 
        + (ext_item_div14!=null? ext_item_div14.hashCode(): 0) 
        + (ext_item_div15!=null? ext_item_div15.hashCode(): 0) 
        + (foreign_account_no!=null? foreign_account_no.hashCode(): 0) 
        + (foreign_account_name!=null? foreign_account_name.hashCode(): 0) 
        + (foreign_account_name_eng!=null? foreign_account_name_eng.hashCode(): 0) 
        + (foreign_save_div!=null? foreign_save_div.hashCode(): 0) 
        + (delete_flag!=null? delete_flag.hashCode(): 0) 
        + (delete_timestamp!=null? delete_timestamp.hashCode(): 0) 
        + (print_flag!=null? print_flag.hashCode(): 0) 
        + (receipt_flag!=null? receipt_flag.hashCode(): 0) 
        + (agreement_flag!=null? agreement_flag.hashCode(): 0) 
        + (foreign_flag!=null? foreign_flag.hashCode(): 0) 
        + (agency_acc_name_kana1!=null? agency_acc_name_kana1.hashCode(): 0) 
        + (agency_acc_name_kana2!=null? agency_acc_name_kana2.hashCode(): 0) 
        + (agency_acc_name1!=null? agency_acc_name1.hashCode(): 0) 
        + (agency_acc_name2!=null? agency_acc_name2.hashCode(): 0) 
        + (agency_address_line1!=null? agency_address_line1.hashCode(): 0) 
        + (agency_address_line2!=null? agency_address_line2.hashCode(): 0) 
        + (agency_rep_post!=null? agency_rep_post.hashCode(): 0) 
        + (agency_rep_name_kana1!=null? agency_rep_name_kana1.hashCode(): 0) 
        + (agency_rep_name_kana2!=null? agency_rep_name_kana2.hashCode(): 0) 
        + (agency_rep_name1!=null? agency_rep_name1.hashCode(): 0) 
        + (agency_rep_name2!=null? agency_rep_name2.hashCode(): 0) 
        + (status!=null? status.hashCode(): 0) 
        + (error_info!=null? error_info.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_id' must be set before inserting.");
		if ( !branch_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_id' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !ex_account_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'ex_account_flag' must be set before inserting.");
		if ( !account_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_div' must be set before inserting.");
		if ( !order_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'order_div' must be set before inserting.");
		if ( !family_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'family_name' must be set before inserting.");
		if ( !given_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'given_name' must be set before inserting.");
		if ( !family_name_alt1_is_set )
			throw new IllegalArgumentException("non-nullable field 'family_name_alt1' must be set before inserting.");
		if ( !given_name_alt1_is_set )
			throw new IllegalArgumentException("non-nullable field 'given_name_alt1' must be set before inserting.");
		if ( !sex_is_set )
			throw new IllegalArgumentException("non-nullable field 'sex' must be set before inserting.");
		if ( !zip_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'zip_code' must be set before inserting.");
		if ( !address_line1_is_set )
			throw new IllegalArgumentException("non-nullable field 'address_line1' must be set before inserting.");
		if ( !address_line1_kana_is_set )
			throw new IllegalArgumentException("non-nullable field 'address_line1_kana' must be set before inserting.");
		if ( !experience_div_equity_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_div_equity' must be set before inserting.");
		if ( !experience_div_margin_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_div_margin' must be set before inserting.");
		if ( !experience_div_bond_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_div_bond' must be set before inserting.");
		if ( !experience_div_wt_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_div_wt' must be set before inserting.");
		if ( !experience_div_fund_sk_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_div_fund_sk' must be set before inserting.");
		if ( !experience_div_fund_bd_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_div_fund_bd' must be set before inserting.");
		if ( !experience_div_fo_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_div_fo' must be set before inserting.");
		if ( !experience_div_f_equity_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_div_f_equity' must be set before inserting.");
		if ( !experience_div_etc_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_div_etc' must be set before inserting.");
		if ( !experience_flag_equity_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_flag_equity' must be set before inserting.");
		if ( !experience_flag_margin_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_flag_margin' must be set before inserting.");
		if ( !experience_flag_bond_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_flag_bond' must be set before inserting.");
		if ( !experience_flag_wt_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_flag_wt' must be set before inserting.");
		if ( !experience_flag_fund_sk_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_flag_fund_sk' must be set before inserting.");
		if ( !experience_flag_fund_bd_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_flag_fund_bd' must be set before inserting.");
		if ( !experience_flag_fo_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_flag_fo' must be set before inserting.");
		if ( !experience_flag_f_equity_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_flag_f_equity' must be set before inserting.");
		if ( !experience_flag_etc_is_set )
			throw new IllegalArgumentException("non-nullable field 'experience_flag_etc' must be set before inserting.");
		if ( !interest_flag_equity_is_set )
			throw new IllegalArgumentException("non-nullable field 'interest_flag_equity' must be set before inserting.");
		if ( !interest_flag_ministock_is_set )
			throw new IllegalArgumentException("non-nullable field 'interest_flag_ministock' must be set before inserting.");
		if ( !interest_flag_margin_is_set )
			throw new IllegalArgumentException("non-nullable field 'interest_flag_margin' must be set before inserting.");
		if ( !interest_flag_bond_is_set )
			throw new IllegalArgumentException("non-nullable field 'interest_flag_bond' must be set before inserting.");
		if ( !interest_flag_fund_is_set )
			throw new IllegalArgumentException("non-nullable field 'interest_flag_fund' must be set before inserting.");
		if ( !interest_flag_fo_is_set )
			throw new IllegalArgumentException("non-nullable field 'interest_flag_fo' must be set before inserting.");
		if ( !interest_flag_f_equity_is_set )
			throw new IllegalArgumentException("non-nullable field 'interest_flag_f_equity' must be set before inserting.");
		if ( !interest_flag_etc_is_set )
			throw new IllegalArgumentException("non-nullable field 'interest_flag_etc' must be set before inserting.");
		if ( !insider_flag_is_set )
			throw new IllegalArgumentException("non-nullable field 'insider_flag' must be set before inserting.");
		if ( !status_is_set )
			throw new IllegalArgumentException("non-nullable field 'status' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("institution_id",new Long(institution_id));
		map.put("branch_id",new Long(branch_id));
		map.put("branch_code",branch_code);
		map.put("acc_open_request_number",acc_open_request_number);
		if ( account_code != null )
			map.put("account_code",account_code);
		if ( sonar_trader_code != null )
			map.put("sonar_trader_code",sonar_trader_code);
		map.put("ex_account_flag",ex_account_flag);
		if ( ex_branch_name != null )
			map.put("ex_branch_name",ex_branch_name);
		if ( ex_account_code != null )
			map.put("ex_account_code",ex_account_code);
		map.put("account_div",account_div);
		map.put("order_div",order_div);
		if ( infomation_claim_datetime != null )
			map.put("infomation_claim_datetime",infomation_claim_datetime);
		if ( account_open_date != null )
			map.put("account_open_date",account_open_date);
		if ( initial_password != null )
			map.put("initial_password",initial_password);
		map.put("family_name",family_name);
		map.put("given_name",given_name);
		map.put("family_name_alt1",family_name_alt1);
		map.put("given_name_alt1",given_name_alt1);
		map.put("sex",sex);
		if ( era_born != null )
			map.put("era_born",era_born);
		if ( born_date != null )
			map.put("born_date",born_date);
		if ( email_address != null )
			map.put("email_address",email_address);
		if ( email_address_alt1 != null )
			map.put("email_address_alt1",email_address_alt1);
		map.put("zip_code",zip_code);
		map.put("address_line1",address_line1);
		if ( address_line2 != null )
			map.put("address_line2",address_line2);
		if ( address_line3 != null )
			map.put("address_line3",address_line3);
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
		if ( occupation_div != null )
			map.put("occupation_div",occupation_div);
		if ( office != null )
			map.put("office",office);
		if ( office_zip_code != null )
			map.put("office_zip_code",office_zip_code);
		if ( office_address != null )
			map.put("office_address",office_address);
		if ( office_telephone != null )
			map.put("office_telephone",office_telephone);
		if ( office_fax != null )
			map.put("office_fax",office_fax);
		if ( department != null )
			map.put("department",department);
		if ( post != null )
			map.put("post",post);
		if ( contact_address != null )
			map.put("contact_address",contact_address);
		if ( contact_telephone != null )
			map.put("contact_telephone",contact_telephone);
		if ( family_relationship != null )
			map.put("family_relationship",family_relationship);
		if ( family_relationship_etc != null )
			map.put("family_relationship_etc",family_relationship_etc);
		if ( householder != null )
			map.put("householder",householder);
		if ( householder_kana != null )
			map.put("householder_kana",householder_kana);
		if ( householder_occupation_div != null )
			map.put("householder_occupation_div",householder_occupation_div);
		if ( householder_office != null )
			map.put("householder_office",householder_office);
		if ( householder_office_address != null )
			map.put("householder_office_address",householder_office_address);
		if ( householder_department != null )
			map.put("householder_department",householder_department);
		if ( householder_office_tel != null )
			map.put("householder_office_tel",householder_office_tel);
		if ( householder_office_fax != null )
			map.put("householder_office_fax",householder_office_fax);
		if ( householder_post != null )
			map.put("householder_post",householder_post);
		if ( resident != null )
			map.put("resident",resident);
		if ( transfer_div != null )
			map.put("transfer_div",transfer_div);
		if ( fin_institution_code != null )
			map.put("fin_institution_code",fin_institution_code);
		if ( fin_institution_name != null )
			map.put("fin_institution_name",fin_institution_name);
		if ( fin_branch_code != null )
			map.put("fin_branch_code",fin_branch_code);
		if ( fin_branch_name != null )
			map.put("fin_branch_name",fin_branch_name);
		if ( fin_save_div != null )
			map.put("fin_save_div",fin_save_div);
		if ( fin_account_no != null )
			map.put("fin_account_no",fin_account_no);
		if ( postal_save_code != null )
			map.put("postal_save_code",postal_save_code);
		if ( postal_save_no != null )
			map.put("postal_save_no",postal_save_no);
		if ( fin_account_name != null )
			map.put("fin_account_name",fin_account_name);
		if ( trans_commission != null )
			map.put("trans_commission",trans_commission);
		map.put("experience_div_equity",experience_div_equity);
		map.put("experience_div_margin",experience_div_margin);
		map.put("experience_div_bond",experience_div_bond);
		map.put("experience_div_wt",experience_div_wt);
		map.put("experience_div_fund_sk",experience_div_fund_sk);
		map.put("experience_div_fund_bd",experience_div_fund_bd);
		map.put("experience_div_fo",experience_div_fo);
		map.put("experience_div_f_equity",experience_div_f_equity);
		map.put("experience_div_etc",experience_div_etc);
		map.put("experience_flag_equity",experience_flag_equity);
		map.put("experience_flag_margin",experience_flag_margin);
		map.put("experience_flag_bond",experience_flag_bond);
		map.put("experience_flag_wt",experience_flag_wt);
		map.put("experience_flag_fund_sk",experience_flag_fund_sk);
		map.put("experience_flag_fund_bd",experience_flag_fund_bd);
		map.put("experience_flag_fo",experience_flag_fo);
		map.put("experience_flag_f_equity",experience_flag_f_equity);
		map.put("experience_flag_etc",experience_flag_etc);
		if ( experience_from != null )
			map.put("experience_from",experience_from);
		if ( experience_to != null )
			map.put("experience_to",experience_to);
		map.put("interest_flag_equity",interest_flag_equity);
		map.put("interest_flag_ministock",interest_flag_ministock);
		map.put("interest_flag_margin",interest_flag_margin);
		map.put("interest_flag_bond",interest_flag_bond);
		map.put("interest_flag_fund",interest_flag_fund);
		map.put("interest_flag_fo",interest_flag_fo);
		map.put("interest_flag_f_equity",interest_flag_f_equity);
		map.put("interest_flag_etc",interest_flag_etc);
		if ( invest_purpose_div != null )
			map.put("invest_purpose_div",invest_purpose_div);
		if ( appli_motivat_div != null )
			map.put("appli_motivat_div",appli_motivat_div);
		if ( annual_income_div != null )
			map.put("annual_income_div",annual_income_div);
		if ( annual_income_from != null )
			map.put("annual_income_from",annual_income_from);
		if ( annual_income_to != null )
			map.put("annual_income_to",annual_income_to);
		if ( asset_value_div != null )
			map.put("asset_value_div",asset_value_div);
		if ( asset_value_from != null )
			map.put("asset_value_from",asset_value_from);
		if ( asset_value_to != null )
			map.put("asset_value_to",asset_value_to);
		if ( fund_budget_amount_div != null )
			map.put("fund_budget_amount_div",fund_budget_amount_div);
		if ( fund_budget_div != null )
			map.put("fund_budget_div",fund_budget_div);
		if ( fund_budget_etc != null )
			map.put("fund_budget_etc",fund_budget_etc);
		if ( id_confirm_flag != null )
			map.put("id_confirm_flag",id_confirm_flag);
		if ( id_confirm_doc_div != null )
			map.put("id_confirm_doc_div",id_confirm_doc_div);
		if ( id_confirm_doc_etc != null )
			map.put("id_confirm_doc_etc",id_confirm_doc_etc);
		if ( special_acc != null )
			map.put("special_acc",special_acc);
		if ( special_acc_margin != null )
			map.put("special_acc_margin",special_acc_margin);
		map.put("insider_flag",insider_flag);
		if ( product_name != null )
			map.put("product_name",product_name);
		if ( send_zip_code != null )
			map.put("send_zip_code",send_zip_code);
		if ( send_address_line1 != null )
			map.put("send_address_line1",send_address_line1);
		if ( send_address_line2 != null )
			map.put("send_address_line2",send_address_line2);
		if ( send_address_line3 != null )
			map.put("send_address_line3",send_address_line3);
		if ( ext_item_div1 != null )
			map.put("ext_item_div1",ext_item_div1);
		if ( ext_item_div2 != null )
			map.put("ext_item_div2",ext_item_div2);
		if ( ext_item_div3 != null )
			map.put("ext_item_div3",ext_item_div3);
		if ( ext_item_div4 != null )
			map.put("ext_item_div4",ext_item_div4);
		if ( ext_item_div5 != null )
			map.put("ext_item_div5",ext_item_div5);
		if ( ext_item_div6 != null )
			map.put("ext_item_div6",ext_item_div6);
		if ( ext_item_div7 != null )
			map.put("ext_item_div7",ext_item_div7);
		if ( ext_item_div8 != null )
			map.put("ext_item_div8",ext_item_div8);
		if ( ext_item_div9 != null )
			map.put("ext_item_div9",ext_item_div9);
		if ( ext_item_div10 != null )
			map.put("ext_item_div10",ext_item_div10);
		if ( ext_item_flag1 != null )
			map.put("ext_item_flag1",ext_item_flag1);
		if ( ext_item_flag2 != null )
			map.put("ext_item_flag2",ext_item_flag2);
		if ( ext_item_flag3 != null )
			map.put("ext_item_flag3",ext_item_flag3);
		if ( ext_item_flag4 != null )
			map.put("ext_item_flag4",ext_item_flag4);
		if ( ext_item_flag5 != null )
			map.put("ext_item_flag5",ext_item_flag5);
		if ( ext_item_flag6 != null )
			map.put("ext_item_flag6",ext_item_flag6);
		if ( ext_item_flag7 != null )
			map.put("ext_item_flag7",ext_item_flag7);
		if ( ext_item_flag8 != null )
			map.put("ext_item_flag8",ext_item_flag8);
		if ( ext_item_flag9 != null )
			map.put("ext_item_flag9",ext_item_flag9);
		if ( ext_item_flag10 != null )
			map.put("ext_item_flag10",ext_item_flag10);
		if ( ext_item_text1 != null )
			map.put("ext_item_text1",ext_item_text1);
		if ( ext_item_text2 != null )
			map.put("ext_item_text2",ext_item_text2);
		if ( ext_item_text3 != null )
			map.put("ext_item_text3",ext_item_text3);
		if ( ext_item_text4 != null )
			map.put("ext_item_text4",ext_item_text4);
		if ( ext_item_text5 != null )
			map.put("ext_item_text5",ext_item_text5);
		if ( ext_item_text6 != null )
			map.put("ext_item_text6",ext_item_text6);
		if ( ext_item_text7 != null )
			map.put("ext_item_text7",ext_item_text7);
		if ( ext_item_text8 != null )
			map.put("ext_item_text8",ext_item_text8);
		if ( ext_item_text9 != null )
			map.put("ext_item_text9",ext_item_text9);
		if ( ext_item_text10 != null )
			map.put("ext_item_text10",ext_item_text10);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( creator != null )
			map.put("creator",creator);
		if ( exclusive_use_account_no != null )
			map.put("exclusive_use_account_no",exclusive_use_account_no);
		if ( send_timestamp != null )
			map.put("send_timestamp",send_timestamp);
		if ( real_name_voucher_div != null )
			map.put("real_name_voucher_div",real_name_voucher_div);
		if ( real_name1 != null )
			map.put("real_name1",real_name1);
		if ( real_name2 != null )
			map.put("real_name2",real_name2);
		if ( insider_voucher_div != null )
			map.put("insider_voucher_div",insider_voucher_div);
		if ( insider_product_code != null )
			map.put("insider_product_code",insider_product_code);
		if ( insider_relation_div != null )
			map.put("insider_relation_div",insider_relation_div);
		if ( insider_officer_name != null )
			map.put("insider_officer_name",insider_officer_name);
		if ( insider_post_code != null )
			map.put("insider_post_code",insider_post_code);
		if ( insider_post_name != null )
			map.put("insider_post_name",insider_post_name);
		if ( gp_voucher_div != null )
			map.put("gp_voucher_div",gp_voucher_div);
		if ( gp_course != null )
			map.put("gp_course",gp_course);
		if ( gp_plan != null )
			map.put("gp_plan",gp_plan);
		if ( gp_target_figure != null )
			map.put("gp_target_figure",gp_target_figure);
		if ( gp_target_year != null )
			map.put("gp_target_year",gp_target_year);
		if ( gp_target_month != null )
			map.put("gp_target_month",gp_target_month);
		if ( gp_installment_figure != null )
			map.put("gp_installment_figure",gp_installment_figure);
		if ( gp_deposit_cycle != null )
			map.put("gp_deposit_cycle",gp_deposit_cycle);
		if ( gp_payment_root != null )
			map.put("gp_payment_root",gp_payment_root);
		if ( gp_reinvest_div != null )
			map.put("gp_reinvest_div",gp_reinvest_div);
		if ( gp_tax_div != null )
			map.put("gp_tax_div",gp_tax_div);
		if ( gp_taxfree_limit != null )
			map.put("gp_taxfree_limit",gp_taxfree_limit);
		if ( gp_special_taxfree_limit != null )
			map.put("gp_special_taxfree_limit",gp_special_taxfree_limit);
		if ( gp_subscr_summary != null )
			map.put("gp_subscr_summary",gp_subscr_summary);
		if ( gp_product_code != null )
			map.put("gp_product_code",gp_product_code);
		if ( gp_mortgage_customer != null )
			map.put("gp_mortgage_customer",gp_mortgage_customer);
		if ( gp_mix_customer != null )
			map.put("gp_mix_customer",gp_mix_customer);
		if ( gp_contract != null )
			map.put("gp_contract",gp_contract);
		if ( stk_voucher_div != null )
			map.put("stk_voucher_div",stk_voucher_div);
		if ( stk_taxation_tran_div != null )
			map.put("stk_taxation_tran_div",stk_taxation_tran_div);
		if ( stk_address_line_kana != null )
			map.put("stk_address_line_kana",stk_address_line_kana);
		if ( stk_transfer_div != null )
			map.put("stk_transfer_div",stk_transfer_div);
		if ( stk_fin_institution_code != null )
			map.put("stk_fin_institution_code",stk_fin_institution_code);
		if ( stk_fin_branch_code != null )
			map.put("stk_fin_branch_code",stk_fin_branch_code);
		if ( stk_fin_save_div != null )
			map.put("stk_fin_save_div",stk_fin_save_div);
		if ( stk_fin_account_no != null )
			map.put("stk_fin_account_no",stk_fin_account_no);
		if ( brokerage_trader_code != null )
			map.put("brokerage_trader_code",brokerage_trader_code);
		if ( ext_item_div11 != null )
			map.put("ext_item_div11",ext_item_div11);
		if ( ext_item_div12 != null )
			map.put("ext_item_div12",ext_item_div12);
		if ( ext_item_div13 != null )
			map.put("ext_item_div13",ext_item_div13);
		if ( ext_item_div14 != null )
			map.put("ext_item_div14",ext_item_div14);
		if ( ext_item_div15 != null )
			map.put("ext_item_div15",ext_item_div15);
		if ( foreign_account_no != null )
			map.put("foreign_account_no",foreign_account_no);
		if ( foreign_account_name != null )
			map.put("foreign_account_name",foreign_account_name);
		if ( foreign_account_name_eng != null )
			map.put("foreign_account_name_eng",foreign_account_name_eng);
		if ( foreign_save_div != null )
			map.put("foreign_save_div",foreign_save_div);
		if ( delete_flag != null )
			map.put("delete_flag",delete_flag);
		if ( delete_timestamp != null )
			map.put("delete_timestamp",delete_timestamp);
		if ( print_flag != null )
			map.put("print_flag",print_flag);
		if ( receipt_flag != null )
			map.put("receipt_flag",receipt_flag);
		if ( agreement_flag != null )
			map.put("agreement_flag",agreement_flag);
		if ( foreign_flag != null )
			map.put("foreign_flag",foreign_flag);
		if ( agency_acc_name_kana1 != null )
			map.put("agency_acc_name_kana1",agency_acc_name_kana1);
		if ( agency_acc_name_kana2 != null )
			map.put("agency_acc_name_kana2",agency_acc_name_kana2);
		if ( agency_acc_name1 != null )
			map.put("agency_acc_name1",agency_acc_name1);
		if ( agency_acc_name2 != null )
			map.put("agency_acc_name2",agency_acc_name2);
		if ( agency_address_line1 != null )
			map.put("agency_address_line1",agency_address_line1);
		if ( agency_address_line2 != null )
			map.put("agency_address_line2",agency_address_line2);
		if ( agency_rep_post != null )
			map.put("agency_rep_post",agency_rep_post);
		if ( agency_rep_name_kana1 != null )
			map.put("agency_rep_name_kana1",agency_rep_name_kana1);
		if ( agency_rep_name_kana2 != null )
			map.put("agency_rep_name_kana2",agency_rep_name_kana2);
		if ( agency_rep_name1 != null )
			map.put("agency_rep_name1",agency_rep_name1);
		if ( agency_rep_name2 != null )
			map.put("agency_rep_name2",agency_rep_name2);
		map.put("status",status);
		if ( error_info != null )
			map.put("error_info",error_info);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_id_is_modified )
			map.put("institution_id",new Long(institution_id));
		if ( branch_id_is_modified )
			map.put("branch_id",new Long(branch_id));
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( sonar_trader_code_is_modified )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( ex_account_flag_is_modified )
			map.put("ex_account_flag",ex_account_flag);
		if ( ex_branch_name_is_modified )
			map.put("ex_branch_name",ex_branch_name);
		if ( ex_account_code_is_modified )
			map.put("ex_account_code",ex_account_code);
		if ( account_div_is_modified )
			map.put("account_div",account_div);
		if ( order_div_is_modified )
			map.put("order_div",order_div);
		if ( infomation_claim_datetime_is_modified )
			map.put("infomation_claim_datetime",infomation_claim_datetime);
		if ( account_open_date_is_modified )
			map.put("account_open_date",account_open_date);
		if ( initial_password_is_modified )
			map.put("initial_password",initial_password);
		if ( family_name_is_modified )
			map.put("family_name",family_name);
		if ( given_name_is_modified )
			map.put("given_name",given_name);
		if ( family_name_alt1_is_modified )
			map.put("family_name_alt1",family_name_alt1);
		if ( given_name_alt1_is_modified )
			map.put("given_name_alt1",given_name_alt1);
		if ( sex_is_modified )
			map.put("sex",sex);
		if ( era_born_is_modified )
			map.put("era_born",era_born);
		if ( born_date_is_modified )
			map.put("born_date",born_date);
		if ( email_address_is_modified )
			map.put("email_address",email_address);
		if ( email_address_alt1_is_modified )
			map.put("email_address_alt1",email_address_alt1);
		if ( zip_code_is_modified )
			map.put("zip_code",zip_code);
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
		if ( occupation_div_is_modified )
			map.put("occupation_div",occupation_div);
		if ( office_is_modified )
			map.put("office",office);
		if ( office_zip_code_is_modified )
			map.put("office_zip_code",office_zip_code);
		if ( office_address_is_modified )
			map.put("office_address",office_address);
		if ( office_telephone_is_modified )
			map.put("office_telephone",office_telephone);
		if ( office_fax_is_modified )
			map.put("office_fax",office_fax);
		if ( department_is_modified )
			map.put("department",department);
		if ( post_is_modified )
			map.put("post",post);
		if ( contact_address_is_modified )
			map.put("contact_address",contact_address);
		if ( contact_telephone_is_modified )
			map.put("contact_telephone",contact_telephone);
		if ( family_relationship_is_modified )
			map.put("family_relationship",family_relationship);
		if ( family_relationship_etc_is_modified )
			map.put("family_relationship_etc",family_relationship_etc);
		if ( householder_is_modified )
			map.put("householder",householder);
		if ( householder_kana_is_modified )
			map.put("householder_kana",householder_kana);
		if ( householder_occupation_div_is_modified )
			map.put("householder_occupation_div",householder_occupation_div);
		if ( householder_office_is_modified )
			map.put("householder_office",householder_office);
		if ( householder_office_address_is_modified )
			map.put("householder_office_address",householder_office_address);
		if ( householder_department_is_modified )
			map.put("householder_department",householder_department);
		if ( householder_office_tel_is_modified )
			map.put("householder_office_tel",householder_office_tel);
		if ( householder_office_fax_is_modified )
			map.put("householder_office_fax",householder_office_fax);
		if ( householder_post_is_modified )
			map.put("householder_post",householder_post);
		if ( resident_is_modified )
			map.put("resident",resident);
		if ( transfer_div_is_modified )
			map.put("transfer_div",transfer_div);
		if ( fin_institution_code_is_modified )
			map.put("fin_institution_code",fin_institution_code);
		if ( fin_institution_name_is_modified )
			map.put("fin_institution_name",fin_institution_name);
		if ( fin_branch_code_is_modified )
			map.put("fin_branch_code",fin_branch_code);
		if ( fin_branch_name_is_modified )
			map.put("fin_branch_name",fin_branch_name);
		if ( fin_save_div_is_modified )
			map.put("fin_save_div",fin_save_div);
		if ( fin_account_no_is_modified )
			map.put("fin_account_no",fin_account_no);
		if ( postal_save_code_is_modified )
			map.put("postal_save_code",postal_save_code);
		if ( postal_save_no_is_modified )
			map.put("postal_save_no",postal_save_no);
		if ( fin_account_name_is_modified )
			map.put("fin_account_name",fin_account_name);
		if ( trans_commission_is_modified )
			map.put("trans_commission",trans_commission);
		if ( experience_div_equity_is_modified )
			map.put("experience_div_equity",experience_div_equity);
		if ( experience_div_margin_is_modified )
			map.put("experience_div_margin",experience_div_margin);
		if ( experience_div_bond_is_modified )
			map.put("experience_div_bond",experience_div_bond);
		if ( experience_div_wt_is_modified )
			map.put("experience_div_wt",experience_div_wt);
		if ( experience_div_fund_sk_is_modified )
			map.put("experience_div_fund_sk",experience_div_fund_sk);
		if ( experience_div_fund_bd_is_modified )
			map.put("experience_div_fund_bd",experience_div_fund_bd);
		if ( experience_div_fo_is_modified )
			map.put("experience_div_fo",experience_div_fo);
		if ( experience_div_f_equity_is_modified )
			map.put("experience_div_f_equity",experience_div_f_equity);
		if ( experience_div_etc_is_modified )
			map.put("experience_div_etc",experience_div_etc);
		if ( experience_flag_equity_is_modified )
			map.put("experience_flag_equity",experience_flag_equity);
		if ( experience_flag_margin_is_modified )
			map.put("experience_flag_margin",experience_flag_margin);
		if ( experience_flag_bond_is_modified )
			map.put("experience_flag_bond",experience_flag_bond);
		if ( experience_flag_wt_is_modified )
			map.put("experience_flag_wt",experience_flag_wt);
		if ( experience_flag_fund_sk_is_modified )
			map.put("experience_flag_fund_sk",experience_flag_fund_sk);
		if ( experience_flag_fund_bd_is_modified )
			map.put("experience_flag_fund_bd",experience_flag_fund_bd);
		if ( experience_flag_fo_is_modified )
			map.put("experience_flag_fo",experience_flag_fo);
		if ( experience_flag_f_equity_is_modified )
			map.put("experience_flag_f_equity",experience_flag_f_equity);
		if ( experience_flag_etc_is_modified )
			map.put("experience_flag_etc",experience_flag_etc);
		if ( experience_from_is_modified )
			map.put("experience_from",experience_from);
		if ( experience_to_is_modified )
			map.put("experience_to",experience_to);
		if ( interest_flag_equity_is_modified )
			map.put("interest_flag_equity",interest_flag_equity);
		if ( interest_flag_ministock_is_modified )
			map.put("interest_flag_ministock",interest_flag_ministock);
		if ( interest_flag_margin_is_modified )
			map.put("interest_flag_margin",interest_flag_margin);
		if ( interest_flag_bond_is_modified )
			map.put("interest_flag_bond",interest_flag_bond);
		if ( interest_flag_fund_is_modified )
			map.put("interest_flag_fund",interest_flag_fund);
		if ( interest_flag_fo_is_modified )
			map.put("interest_flag_fo",interest_flag_fo);
		if ( interest_flag_f_equity_is_modified )
			map.put("interest_flag_f_equity",interest_flag_f_equity);
		if ( interest_flag_etc_is_modified )
			map.put("interest_flag_etc",interest_flag_etc);
		if ( invest_purpose_div_is_modified )
			map.put("invest_purpose_div",invest_purpose_div);
		if ( appli_motivat_div_is_modified )
			map.put("appli_motivat_div",appli_motivat_div);
		if ( annual_income_div_is_modified )
			map.put("annual_income_div",annual_income_div);
		if ( annual_income_from_is_modified )
			map.put("annual_income_from",annual_income_from);
		if ( annual_income_to_is_modified )
			map.put("annual_income_to",annual_income_to);
		if ( asset_value_div_is_modified )
			map.put("asset_value_div",asset_value_div);
		if ( asset_value_from_is_modified )
			map.put("asset_value_from",asset_value_from);
		if ( asset_value_to_is_modified )
			map.put("asset_value_to",asset_value_to);
		if ( fund_budget_amount_div_is_modified )
			map.put("fund_budget_amount_div",fund_budget_amount_div);
		if ( fund_budget_div_is_modified )
			map.put("fund_budget_div",fund_budget_div);
		if ( fund_budget_etc_is_modified )
			map.put("fund_budget_etc",fund_budget_etc);
		if ( id_confirm_flag_is_modified )
			map.put("id_confirm_flag",id_confirm_flag);
		if ( id_confirm_doc_div_is_modified )
			map.put("id_confirm_doc_div",id_confirm_doc_div);
		if ( id_confirm_doc_etc_is_modified )
			map.put("id_confirm_doc_etc",id_confirm_doc_etc);
		if ( special_acc_is_modified )
			map.put("special_acc",special_acc);
		if ( special_acc_margin_is_modified )
			map.put("special_acc_margin",special_acc_margin);
		if ( insider_flag_is_modified )
			map.put("insider_flag",insider_flag);
		if ( product_name_is_modified )
			map.put("product_name",product_name);
		if ( send_zip_code_is_modified )
			map.put("send_zip_code",send_zip_code);
		if ( send_address_line1_is_modified )
			map.put("send_address_line1",send_address_line1);
		if ( send_address_line2_is_modified )
			map.put("send_address_line2",send_address_line2);
		if ( send_address_line3_is_modified )
			map.put("send_address_line3",send_address_line3);
		if ( ext_item_div1_is_modified )
			map.put("ext_item_div1",ext_item_div1);
		if ( ext_item_div2_is_modified )
			map.put("ext_item_div2",ext_item_div2);
		if ( ext_item_div3_is_modified )
			map.put("ext_item_div3",ext_item_div3);
		if ( ext_item_div4_is_modified )
			map.put("ext_item_div4",ext_item_div4);
		if ( ext_item_div5_is_modified )
			map.put("ext_item_div5",ext_item_div5);
		if ( ext_item_div6_is_modified )
			map.put("ext_item_div6",ext_item_div6);
		if ( ext_item_div7_is_modified )
			map.put("ext_item_div7",ext_item_div7);
		if ( ext_item_div8_is_modified )
			map.put("ext_item_div8",ext_item_div8);
		if ( ext_item_div9_is_modified )
			map.put("ext_item_div9",ext_item_div9);
		if ( ext_item_div10_is_modified )
			map.put("ext_item_div10",ext_item_div10);
		if ( ext_item_flag1_is_modified )
			map.put("ext_item_flag1",ext_item_flag1);
		if ( ext_item_flag2_is_modified )
			map.put("ext_item_flag2",ext_item_flag2);
		if ( ext_item_flag3_is_modified )
			map.put("ext_item_flag3",ext_item_flag3);
		if ( ext_item_flag4_is_modified )
			map.put("ext_item_flag4",ext_item_flag4);
		if ( ext_item_flag5_is_modified )
			map.put("ext_item_flag5",ext_item_flag5);
		if ( ext_item_flag6_is_modified )
			map.put("ext_item_flag6",ext_item_flag6);
		if ( ext_item_flag7_is_modified )
			map.put("ext_item_flag7",ext_item_flag7);
		if ( ext_item_flag8_is_modified )
			map.put("ext_item_flag8",ext_item_flag8);
		if ( ext_item_flag9_is_modified )
			map.put("ext_item_flag9",ext_item_flag9);
		if ( ext_item_flag10_is_modified )
			map.put("ext_item_flag10",ext_item_flag10);
		if ( ext_item_text1_is_modified )
			map.put("ext_item_text1",ext_item_text1);
		if ( ext_item_text2_is_modified )
			map.put("ext_item_text2",ext_item_text2);
		if ( ext_item_text3_is_modified )
			map.put("ext_item_text3",ext_item_text3);
		if ( ext_item_text4_is_modified )
			map.put("ext_item_text4",ext_item_text4);
		if ( ext_item_text5_is_modified )
			map.put("ext_item_text5",ext_item_text5);
		if ( ext_item_text6_is_modified )
			map.put("ext_item_text6",ext_item_text6);
		if ( ext_item_text7_is_modified )
			map.put("ext_item_text7",ext_item_text7);
		if ( ext_item_text8_is_modified )
			map.put("ext_item_text8",ext_item_text8);
		if ( ext_item_text9_is_modified )
			map.put("ext_item_text9",ext_item_text9);
		if ( ext_item_text10_is_modified )
			map.put("ext_item_text10",ext_item_text10);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( creator_is_modified )
			map.put("creator",creator);
		if ( exclusive_use_account_no_is_modified )
			map.put("exclusive_use_account_no",exclusive_use_account_no);
		if ( send_timestamp_is_modified )
			map.put("send_timestamp",send_timestamp);
		if ( real_name_voucher_div_is_modified )
			map.put("real_name_voucher_div",real_name_voucher_div);
		if ( real_name1_is_modified )
			map.put("real_name1",real_name1);
		if ( real_name2_is_modified )
			map.put("real_name2",real_name2);
		if ( insider_voucher_div_is_modified )
			map.put("insider_voucher_div",insider_voucher_div);
		if ( insider_product_code_is_modified )
			map.put("insider_product_code",insider_product_code);
		if ( insider_relation_div_is_modified )
			map.put("insider_relation_div",insider_relation_div);
		if ( insider_officer_name_is_modified )
			map.put("insider_officer_name",insider_officer_name);
		if ( insider_post_code_is_modified )
			map.put("insider_post_code",insider_post_code);
		if ( insider_post_name_is_modified )
			map.put("insider_post_name",insider_post_name);
		if ( gp_voucher_div_is_modified )
			map.put("gp_voucher_div",gp_voucher_div);
		if ( gp_course_is_modified )
			map.put("gp_course",gp_course);
		if ( gp_plan_is_modified )
			map.put("gp_plan",gp_plan);
		if ( gp_target_figure_is_modified )
			map.put("gp_target_figure",gp_target_figure);
		if ( gp_target_year_is_modified )
			map.put("gp_target_year",gp_target_year);
		if ( gp_target_month_is_modified )
			map.put("gp_target_month",gp_target_month);
		if ( gp_installment_figure_is_modified )
			map.put("gp_installment_figure",gp_installment_figure);
		if ( gp_deposit_cycle_is_modified )
			map.put("gp_deposit_cycle",gp_deposit_cycle);
		if ( gp_payment_root_is_modified )
			map.put("gp_payment_root",gp_payment_root);
		if ( gp_reinvest_div_is_modified )
			map.put("gp_reinvest_div",gp_reinvest_div);
		if ( gp_tax_div_is_modified )
			map.put("gp_tax_div",gp_tax_div);
		if ( gp_taxfree_limit_is_modified )
			map.put("gp_taxfree_limit",gp_taxfree_limit);
		if ( gp_special_taxfree_limit_is_modified )
			map.put("gp_special_taxfree_limit",gp_special_taxfree_limit);
		if ( gp_subscr_summary_is_modified )
			map.put("gp_subscr_summary",gp_subscr_summary);
		if ( gp_product_code_is_modified )
			map.put("gp_product_code",gp_product_code);
		if ( gp_mortgage_customer_is_modified )
			map.put("gp_mortgage_customer",gp_mortgage_customer);
		if ( gp_mix_customer_is_modified )
			map.put("gp_mix_customer",gp_mix_customer);
		if ( gp_contract_is_modified )
			map.put("gp_contract",gp_contract);
		if ( stk_voucher_div_is_modified )
			map.put("stk_voucher_div",stk_voucher_div);
		if ( stk_taxation_tran_div_is_modified )
			map.put("stk_taxation_tran_div",stk_taxation_tran_div);
		if ( stk_address_line_kana_is_modified )
			map.put("stk_address_line_kana",stk_address_line_kana);
		if ( stk_transfer_div_is_modified )
			map.put("stk_transfer_div",stk_transfer_div);
		if ( stk_fin_institution_code_is_modified )
			map.put("stk_fin_institution_code",stk_fin_institution_code);
		if ( stk_fin_branch_code_is_modified )
			map.put("stk_fin_branch_code",stk_fin_branch_code);
		if ( stk_fin_save_div_is_modified )
			map.put("stk_fin_save_div",stk_fin_save_div);
		if ( stk_fin_account_no_is_modified )
			map.put("stk_fin_account_no",stk_fin_account_no);
		if ( brokerage_trader_code_is_modified )
			map.put("brokerage_trader_code",brokerage_trader_code);
		if ( ext_item_div11_is_modified )
			map.put("ext_item_div11",ext_item_div11);
		if ( ext_item_div12_is_modified )
			map.put("ext_item_div12",ext_item_div12);
		if ( ext_item_div13_is_modified )
			map.put("ext_item_div13",ext_item_div13);
		if ( ext_item_div14_is_modified )
			map.put("ext_item_div14",ext_item_div14);
		if ( ext_item_div15_is_modified )
			map.put("ext_item_div15",ext_item_div15);
		if ( foreign_account_no_is_modified )
			map.put("foreign_account_no",foreign_account_no);
		if ( foreign_account_name_is_modified )
			map.put("foreign_account_name",foreign_account_name);
		if ( foreign_account_name_eng_is_modified )
			map.put("foreign_account_name_eng",foreign_account_name_eng);
		if ( foreign_save_div_is_modified )
			map.put("foreign_save_div",foreign_save_div);
		if ( delete_flag_is_modified )
			map.put("delete_flag",delete_flag);
		if ( delete_timestamp_is_modified )
			map.put("delete_timestamp",delete_timestamp);
		if ( print_flag_is_modified )
			map.put("print_flag",print_flag);
		if ( receipt_flag_is_modified )
			map.put("receipt_flag",receipt_flag);
		if ( agreement_flag_is_modified )
			map.put("agreement_flag",agreement_flag);
		if ( foreign_flag_is_modified )
			map.put("foreign_flag",foreign_flag);
		if ( agency_acc_name_kana1_is_modified )
			map.put("agency_acc_name_kana1",agency_acc_name_kana1);
		if ( agency_acc_name_kana2_is_modified )
			map.put("agency_acc_name_kana2",agency_acc_name_kana2);
		if ( agency_acc_name1_is_modified )
			map.put("agency_acc_name1",agency_acc_name1);
		if ( agency_acc_name2_is_modified )
			map.put("agency_acc_name2",agency_acc_name2);
		if ( agency_address_line1_is_modified )
			map.put("agency_address_line1",agency_address_line1);
		if ( agency_address_line2_is_modified )
			map.put("agency_address_line2",agency_address_line2);
		if ( agency_rep_post_is_modified )
			map.put("agency_rep_post",agency_rep_post);
		if ( agency_rep_name_kana1_is_modified )
			map.put("agency_rep_name_kana1",agency_rep_name_kana1);
		if ( agency_rep_name_kana2_is_modified )
			map.put("agency_rep_name_kana2",agency_rep_name_kana2);
		if ( agency_rep_name1_is_modified )
			map.put("agency_rep_name1",agency_rep_name1);
		if ( agency_rep_name2_is_modified )
			map.put("agency_rep_name2",agency_rep_name2);
		if ( status_is_modified )
			map.put("status",status);
		if ( error_info_is_modified )
			map.put("error_info",error_info);
		if (map.size() == 0) {
			if ( institution_id_is_set )
				map.put("institution_id",new Long(institution_id));
			if ( branch_id_is_set )
				map.put("branch_id",new Long(branch_id));
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			map.put("account_code",account_code);
			map.put("sonar_trader_code",sonar_trader_code);
			if ( ex_account_flag_is_set )
				map.put("ex_account_flag",ex_account_flag);
			map.put("ex_branch_name",ex_branch_name);
			map.put("ex_account_code",ex_account_code);
			if ( account_div_is_set )
				map.put("account_div",account_div);
			if ( order_div_is_set )
				map.put("order_div",order_div);
			map.put("infomation_claim_datetime",infomation_claim_datetime);
			map.put("account_open_date",account_open_date);
			map.put("initial_password",initial_password);
			if ( family_name_is_set )
				map.put("family_name",family_name);
			if ( given_name_is_set )
				map.put("given_name",given_name);
			if ( family_name_alt1_is_set )
				map.put("family_name_alt1",family_name_alt1);
			if ( given_name_alt1_is_set )
				map.put("given_name_alt1",given_name_alt1);
			if ( sex_is_set )
				map.put("sex",sex);
			map.put("era_born",era_born);
			map.put("born_date",born_date);
			map.put("email_address",email_address);
			map.put("email_address_alt1",email_address_alt1);
			if ( zip_code_is_set )
				map.put("zip_code",zip_code);
			if ( address_line1_is_set )
				map.put("address_line1",address_line1);
			map.put("address_line2",address_line2);
			map.put("address_line3",address_line3);
			if ( address_line1_kana_is_set )
				map.put("address_line1_kana",address_line1_kana);
			map.put("address_line2_kana",address_line2_kana);
			map.put("address_line3_kana",address_line3_kana);
			map.put("telephone",telephone);
			map.put("mobile",mobile);
			map.put("fax",fax);
			map.put("occupation_div",occupation_div);
			map.put("office",office);
			map.put("office_zip_code",office_zip_code);
			map.put("office_address",office_address);
			map.put("office_telephone",office_telephone);
			map.put("office_fax",office_fax);
			map.put("department",department);
			map.put("post",post);
			map.put("contact_address",contact_address);
			map.put("contact_telephone",contact_telephone);
			map.put("family_relationship",family_relationship);
			map.put("family_relationship_etc",family_relationship_etc);
			map.put("householder",householder);
			map.put("householder_kana",householder_kana);
			map.put("householder_occupation_div",householder_occupation_div);
			map.put("householder_office",householder_office);
			map.put("householder_office_address",householder_office_address);
			map.put("householder_department",householder_department);
			map.put("householder_office_tel",householder_office_tel);
			map.put("householder_office_fax",householder_office_fax);
			map.put("householder_post",householder_post);
			map.put("resident",resident);
			map.put("transfer_div",transfer_div);
			map.put("fin_institution_code",fin_institution_code);
			map.put("fin_institution_name",fin_institution_name);
			map.put("fin_branch_code",fin_branch_code);
			map.put("fin_branch_name",fin_branch_name);
			map.put("fin_save_div",fin_save_div);
			map.put("fin_account_no",fin_account_no);
			map.put("postal_save_code",postal_save_code);
			map.put("postal_save_no",postal_save_no);
			map.put("fin_account_name",fin_account_name);
			map.put("trans_commission",trans_commission);
			if ( experience_div_equity_is_set )
				map.put("experience_div_equity",experience_div_equity);
			if ( experience_div_margin_is_set )
				map.put("experience_div_margin",experience_div_margin);
			if ( experience_div_bond_is_set )
				map.put("experience_div_bond",experience_div_bond);
			if ( experience_div_wt_is_set )
				map.put("experience_div_wt",experience_div_wt);
			if ( experience_div_fund_sk_is_set )
				map.put("experience_div_fund_sk",experience_div_fund_sk);
			if ( experience_div_fund_bd_is_set )
				map.put("experience_div_fund_bd",experience_div_fund_bd);
			if ( experience_div_fo_is_set )
				map.put("experience_div_fo",experience_div_fo);
			if ( experience_div_f_equity_is_set )
				map.put("experience_div_f_equity",experience_div_f_equity);
			if ( experience_div_etc_is_set )
				map.put("experience_div_etc",experience_div_etc);
			if ( experience_flag_equity_is_set )
				map.put("experience_flag_equity",experience_flag_equity);
			if ( experience_flag_margin_is_set )
				map.put("experience_flag_margin",experience_flag_margin);
			if ( experience_flag_bond_is_set )
				map.put("experience_flag_bond",experience_flag_bond);
			if ( experience_flag_wt_is_set )
				map.put("experience_flag_wt",experience_flag_wt);
			if ( experience_flag_fund_sk_is_set )
				map.put("experience_flag_fund_sk",experience_flag_fund_sk);
			if ( experience_flag_fund_bd_is_set )
				map.put("experience_flag_fund_bd",experience_flag_fund_bd);
			if ( experience_flag_fo_is_set )
				map.put("experience_flag_fo",experience_flag_fo);
			if ( experience_flag_f_equity_is_set )
				map.put("experience_flag_f_equity",experience_flag_f_equity);
			if ( experience_flag_etc_is_set )
				map.put("experience_flag_etc",experience_flag_etc);
			map.put("experience_from",experience_from);
			map.put("experience_to",experience_to);
			if ( interest_flag_equity_is_set )
				map.put("interest_flag_equity",interest_flag_equity);
			if ( interest_flag_ministock_is_set )
				map.put("interest_flag_ministock",interest_flag_ministock);
			if ( interest_flag_margin_is_set )
				map.put("interest_flag_margin",interest_flag_margin);
			if ( interest_flag_bond_is_set )
				map.put("interest_flag_bond",interest_flag_bond);
			if ( interest_flag_fund_is_set )
				map.put("interest_flag_fund",interest_flag_fund);
			if ( interest_flag_fo_is_set )
				map.put("interest_flag_fo",interest_flag_fo);
			if ( interest_flag_f_equity_is_set )
				map.put("interest_flag_f_equity",interest_flag_f_equity);
			if ( interest_flag_etc_is_set )
				map.put("interest_flag_etc",interest_flag_etc);
			map.put("invest_purpose_div",invest_purpose_div);
			map.put("appli_motivat_div",appli_motivat_div);
			map.put("annual_income_div",annual_income_div);
			map.put("annual_income_from",annual_income_from);
			map.put("annual_income_to",annual_income_to);
			map.put("asset_value_div",asset_value_div);
			map.put("asset_value_from",asset_value_from);
			map.put("asset_value_to",asset_value_to);
			map.put("fund_budget_amount_div",fund_budget_amount_div);
			map.put("fund_budget_div",fund_budget_div);
			map.put("fund_budget_etc",fund_budget_etc);
			map.put("id_confirm_flag",id_confirm_flag);
			map.put("id_confirm_doc_div",id_confirm_doc_div);
			map.put("id_confirm_doc_etc",id_confirm_doc_etc);
			map.put("special_acc",special_acc);
			map.put("special_acc_margin",special_acc_margin);
			if ( insider_flag_is_set )
				map.put("insider_flag",insider_flag);
			map.put("product_name",product_name);
			map.put("send_zip_code",send_zip_code);
			map.put("send_address_line1",send_address_line1);
			map.put("send_address_line2",send_address_line2);
			map.put("send_address_line3",send_address_line3);
			map.put("ext_item_div1",ext_item_div1);
			map.put("ext_item_div2",ext_item_div2);
			map.put("ext_item_div3",ext_item_div3);
			map.put("ext_item_div4",ext_item_div4);
			map.put("ext_item_div5",ext_item_div5);
			map.put("ext_item_div6",ext_item_div6);
			map.put("ext_item_div7",ext_item_div7);
			map.put("ext_item_div8",ext_item_div8);
			map.put("ext_item_div9",ext_item_div9);
			map.put("ext_item_div10",ext_item_div10);
			map.put("ext_item_flag1",ext_item_flag1);
			map.put("ext_item_flag2",ext_item_flag2);
			map.put("ext_item_flag3",ext_item_flag3);
			map.put("ext_item_flag4",ext_item_flag4);
			map.put("ext_item_flag5",ext_item_flag5);
			map.put("ext_item_flag6",ext_item_flag6);
			map.put("ext_item_flag7",ext_item_flag7);
			map.put("ext_item_flag8",ext_item_flag8);
			map.put("ext_item_flag9",ext_item_flag9);
			map.put("ext_item_flag10",ext_item_flag10);
			map.put("ext_item_text1",ext_item_text1);
			map.put("ext_item_text2",ext_item_text2);
			map.put("ext_item_text3",ext_item_text3);
			map.put("ext_item_text4",ext_item_text4);
			map.put("ext_item_text5",ext_item_text5);
			map.put("ext_item_text6",ext_item_text6);
			map.put("ext_item_text7",ext_item_text7);
			map.put("ext_item_text8",ext_item_text8);
			map.put("ext_item_text9",ext_item_text9);
			map.put("ext_item_text10",ext_item_text10);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("creator",creator);
			map.put("exclusive_use_account_no",exclusive_use_account_no);
			map.put("send_timestamp",send_timestamp);
			map.put("real_name_voucher_div",real_name_voucher_div);
			map.put("real_name1",real_name1);
			map.put("real_name2",real_name2);
			map.put("insider_voucher_div",insider_voucher_div);
			map.put("insider_product_code",insider_product_code);
			map.put("insider_relation_div",insider_relation_div);
			map.put("insider_officer_name",insider_officer_name);
			map.put("insider_post_code",insider_post_code);
			map.put("insider_post_name",insider_post_name);
			map.put("gp_voucher_div",gp_voucher_div);
			map.put("gp_course",gp_course);
			map.put("gp_plan",gp_plan);
			map.put("gp_target_figure",gp_target_figure);
			map.put("gp_target_year",gp_target_year);
			map.put("gp_target_month",gp_target_month);
			map.put("gp_installment_figure",gp_installment_figure);
			map.put("gp_deposit_cycle",gp_deposit_cycle);
			map.put("gp_payment_root",gp_payment_root);
			map.put("gp_reinvest_div",gp_reinvest_div);
			map.put("gp_tax_div",gp_tax_div);
			map.put("gp_taxfree_limit",gp_taxfree_limit);
			map.put("gp_special_taxfree_limit",gp_special_taxfree_limit);
			map.put("gp_subscr_summary",gp_subscr_summary);
			map.put("gp_product_code",gp_product_code);
			map.put("gp_mortgage_customer",gp_mortgage_customer);
			map.put("gp_mix_customer",gp_mix_customer);
			map.put("gp_contract",gp_contract);
			map.put("stk_voucher_div",stk_voucher_div);
			map.put("stk_taxation_tran_div",stk_taxation_tran_div);
			map.put("stk_address_line_kana",stk_address_line_kana);
			map.put("stk_transfer_div",stk_transfer_div);
			map.put("stk_fin_institution_code",stk_fin_institution_code);
			map.put("stk_fin_branch_code",stk_fin_branch_code);
			map.put("stk_fin_save_div",stk_fin_save_div);
			map.put("stk_fin_account_no",stk_fin_account_no);
			map.put("brokerage_trader_code",brokerage_trader_code);
			map.put("ext_item_div11",ext_item_div11);
			map.put("ext_item_div12",ext_item_div12);
			map.put("ext_item_div13",ext_item_div13);
			map.put("ext_item_div14",ext_item_div14);
			map.put("ext_item_div15",ext_item_div15);
			map.put("foreign_account_no",foreign_account_no);
			map.put("foreign_account_name",foreign_account_name);
			map.put("foreign_account_name_eng",foreign_account_name_eng);
			map.put("foreign_save_div",foreign_save_div);
			map.put("delete_flag",delete_flag);
			map.put("delete_timestamp",delete_timestamp);
			map.put("print_flag",print_flag);
			map.put("receipt_flag",receipt_flag);
			map.put("agreement_flag",agreement_flag);
			map.put("foreign_flag",foreign_flag);
			map.put("agency_acc_name_kana1",agency_acc_name_kana1);
			map.put("agency_acc_name_kana2",agency_acc_name_kana2);
			map.put("agency_acc_name1",agency_acc_name1);
			map.put("agency_acc_name2",agency_acc_name2);
			map.put("agency_address_line1",agency_address_line1);
			map.put("agency_address_line2",agency_address_line2);
			map.put("agency_rep_post",agency_rep_post);
			map.put("agency_rep_name_kana1",agency_rep_name_kana1);
			map.put("agency_rep_name_kana2",agency_rep_name_kana2);
			map.put("agency_rep_name1",agency_rep_name1);
			map.put("agency_rep_name2",agency_rep_name2);
			if ( status_is_set )
				map.put("status",status);
			map.put("error_info",error_info);
		}
		return map;
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
   * <em>ex_account_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExAccountFlag()
  {
    return ex_account_flag;
  }


  /** 
   * <em>ex_account_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExAccountFlagIsSet() {
    return ex_account_flag_is_set;
  }


  /** 
   * <em>ex_account_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExAccountFlagIsModified() {
    return ex_account_flag_is_modified;
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
   * <em>infomation_claim_datetime</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getInfomationClaimDatetime()
  {
    return infomation_claim_datetime;
  }


  /** 
   * <em>infomation_claim_datetime</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInfomationClaimDatetimeIsSet() {
    return infomation_claim_datetime_is_set;
  }


  /** 
   * <em>infomation_claim_datetime</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInfomationClaimDatetimeIsModified() {
    return infomation_claim_datetime_is_modified;
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
   * <em>initial_password</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInitialPassword()
  {
    return initial_password;
  }


  /** 
   * <em>initial_password</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInitialPasswordIsSet() {
    return initial_password_is_set;
  }


  /** 
   * <em>initial_password</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInitialPasswordIsModified() {
    return initial_password_is_modified;
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
   * <em>office_fax</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOfficeFax()
  {
    return office_fax;
  }


  /** 
   * <em>office_fax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfficeFaxIsSet() {
    return office_fax_is_set;
  }


  /** 
   * <em>office_fax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOfficeFaxIsModified() {
    return office_fax_is_modified;
  }


  /** 
   * <em>department</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDepartment()
  {
    return department;
  }


  /** 
   * <em>department</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepartmentIsSet() {
    return department_is_set;
  }


  /** 
   * <em>department</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepartmentIsModified() {
    return department_is_modified;
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
   * <em>family_relationship</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFamilyRelationship()
  {
    return family_relationship;
  }


  /** 
   * <em>family_relationship</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyRelationshipIsSet() {
    return family_relationship_is_set;
  }


  /** 
   * <em>family_relationship</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyRelationshipIsModified() {
    return family_relationship_is_modified;
  }


  /** 
   * <em>family_relationship_etc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFamilyRelationshipEtc()
  {
    return family_relationship_etc;
  }


  /** 
   * <em>family_relationship_etc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyRelationshipEtcIsSet() {
    return family_relationship_etc_is_set;
  }


  /** 
   * <em>family_relationship_etc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyRelationshipEtcIsModified() {
    return family_relationship_etc_is_modified;
  }


  /** 
   * <em>householder</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHouseholder()
  {
    return householder;
  }


  /** 
   * <em>householder</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderIsSet() {
    return householder_is_set;
  }


  /** 
   * <em>householder</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderIsModified() {
    return householder_is_modified;
  }


  /** 
   * <em>householder_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHouseholderKana()
  {
    return householder_kana;
  }


  /** 
   * <em>householder_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderKanaIsSet() {
    return householder_kana_is_set;
  }


  /** 
   * <em>householder_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderKanaIsModified() {
    return householder_kana_is_modified;
  }


  /** 
   * <em>householder_occupation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHouseholderOccupationDiv()
  {
    return householder_occupation_div;
  }


  /** 
   * <em>householder_occupation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderOccupationDivIsSet() {
    return householder_occupation_div_is_set;
  }


  /** 
   * <em>householder_occupation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderOccupationDivIsModified() {
    return householder_occupation_div_is_modified;
  }


  /** 
   * <em>householder_office</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHouseholderOffice()
  {
    return householder_office;
  }


  /** 
   * <em>householder_office</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderOfficeIsSet() {
    return householder_office_is_set;
  }


  /** 
   * <em>householder_office</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderOfficeIsModified() {
    return householder_office_is_modified;
  }


  /** 
   * <em>householder_office_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHouseholderOfficeAddress()
  {
    return householder_office_address;
  }


  /** 
   * <em>householder_office_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderOfficeAddressIsSet() {
    return householder_office_address_is_set;
  }


  /** 
   * <em>householder_office_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderOfficeAddressIsModified() {
    return householder_office_address_is_modified;
  }


  /** 
   * <em>householder_department</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHouseholderDepartment()
  {
    return householder_department;
  }


  /** 
   * <em>householder_department</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderDepartmentIsSet() {
    return householder_department_is_set;
  }


  /** 
   * <em>householder_department</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderDepartmentIsModified() {
    return householder_department_is_modified;
  }


  /** 
   * <em>householder_office_tel</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHouseholderOfficeTel()
  {
    return householder_office_tel;
  }


  /** 
   * <em>householder_office_tel</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderOfficeTelIsSet() {
    return householder_office_tel_is_set;
  }


  /** 
   * <em>householder_office_tel</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderOfficeTelIsModified() {
    return householder_office_tel_is_modified;
  }


  /** 
   * <em>householder_office_fax</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHouseholderOfficeFax()
  {
    return householder_office_fax;
  }


  /** 
   * <em>householder_office_fax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderOfficeFaxIsSet() {
    return householder_office_fax_is_set;
  }


  /** 
   * <em>householder_office_fax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderOfficeFaxIsModified() {
    return householder_office_fax_is_modified;
  }


  /** 
   * <em>householder_post</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHouseholderPost()
  {
    return householder_post;
  }


  /** 
   * <em>householder_post</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderPostIsSet() {
    return householder_post_is_set;
  }


  /** 
   * <em>householder_post</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHouseholderPostIsModified() {
    return householder_post_is_modified;
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
   * <em>transfer_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTransferDiv()
  {
    return transfer_div;
  }


  /** 
   * <em>transfer_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferDivIsSet() {
    return transfer_div_is_set;
  }


  /** 
   * <em>transfer_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferDivIsModified() {
    return transfer_div_is_modified;
  }


  /** 
   * <em>fin_institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinInstitutionCode()
  {
    return fin_institution_code;
  }


  /** 
   * <em>fin_institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinInstitutionCodeIsSet() {
    return fin_institution_code_is_set;
  }


  /** 
   * <em>fin_institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinInstitutionCodeIsModified() {
    return fin_institution_code_is_modified;
  }


  /** 
   * <em>fin_institution_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinInstitutionName()
  {
    return fin_institution_name;
  }


  /** 
   * <em>fin_institution_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinInstitutionNameIsSet() {
    return fin_institution_name_is_set;
  }


  /** 
   * <em>fin_institution_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinInstitutionNameIsModified() {
    return fin_institution_name_is_modified;
  }


  /** 
   * <em>fin_branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinBranchCode()
  {
    return fin_branch_code;
  }


  /** 
   * <em>fin_branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinBranchCodeIsSet() {
    return fin_branch_code_is_set;
  }


  /** 
   * <em>fin_branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinBranchCodeIsModified() {
    return fin_branch_code_is_modified;
  }


  /** 
   * <em>fin_branch_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinBranchName()
  {
    return fin_branch_name;
  }


  /** 
   * <em>fin_branch_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinBranchNameIsSet() {
    return fin_branch_name_is_set;
  }


  /** 
   * <em>fin_branch_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinBranchNameIsModified() {
    return fin_branch_name_is_modified;
  }


  /** 
   * <em>fin_save_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinSaveDiv()
  {
    return fin_save_div;
  }


  /** 
   * <em>fin_save_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinSaveDivIsSet() {
    return fin_save_div_is_set;
  }


  /** 
   * <em>fin_save_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinSaveDivIsModified() {
    return fin_save_div_is_modified;
  }


  /** 
   * <em>fin_account_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinAccountNo()
  {
    return fin_account_no;
  }


  /** 
   * <em>fin_account_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinAccountNoIsSet() {
    return fin_account_no_is_set;
  }


  /** 
   * <em>fin_account_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinAccountNoIsModified() {
    return fin_account_no_is_modified;
  }


  /** 
   * <em>postal_save_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPostalSaveCode()
  {
    return postal_save_code;
  }


  /** 
   * <em>postal_save_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPostalSaveCodeIsSet() {
    return postal_save_code_is_set;
  }


  /** 
   * <em>postal_save_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPostalSaveCodeIsModified() {
    return postal_save_code_is_modified;
  }


  /** 
   * <em>postal_save_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPostalSaveNo()
  {
    return postal_save_no;
  }


  /** 
   * <em>postal_save_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPostalSaveNoIsSet() {
    return postal_save_no_is_set;
  }


  /** 
   * <em>postal_save_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPostalSaveNoIsModified() {
    return postal_save_no_is_modified;
  }


  /** 
   * <em>fin_account_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFinAccountName()
  {
    return fin_account_name;
  }


  /** 
   * <em>fin_account_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinAccountNameIsSet() {
    return fin_account_name_is_set;
  }


  /** 
   * <em>fin_account_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFinAccountNameIsModified() {
    return fin_account_name_is_modified;
  }


  /** 
   * <em>trans_commission</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTransCommission()
  {
    return trans_commission;
  }


  /** 
   * <em>trans_commission</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransCommissionIsSet() {
    return trans_commission_is_set;
  }


  /** 
   * <em>trans_commission</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransCommissionIsModified() {
    return trans_commission_is_modified;
  }


  /** 
   * <em>experience_div_equity</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDivEquity()
  {
    return experience_div_equity;
  }


  /** 
   * <em>experience_div_equity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivEquityIsSet() {
    return experience_div_equity_is_set;
  }


  /** 
   * <em>experience_div_equity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivEquityIsModified() {
    return experience_div_equity_is_modified;
  }


  /** 
   * <em>experience_div_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDivMargin()
  {
    return experience_div_margin;
  }


  /** 
   * <em>experience_div_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivMarginIsSet() {
    return experience_div_margin_is_set;
  }


  /** 
   * <em>experience_div_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivMarginIsModified() {
    return experience_div_margin_is_modified;
  }


  /** 
   * <em>experience_div_bond</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDivBond()
  {
    return experience_div_bond;
  }


  /** 
   * <em>experience_div_bond</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivBondIsSet() {
    return experience_div_bond_is_set;
  }


  /** 
   * <em>experience_div_bond</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivBondIsModified() {
    return experience_div_bond_is_modified;
  }


  /** 
   * <em>experience_div_wt</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDivWt()
  {
    return experience_div_wt;
  }


  /** 
   * <em>experience_div_wt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivWtIsSet() {
    return experience_div_wt_is_set;
  }


  /** 
   * <em>experience_div_wt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivWtIsModified() {
    return experience_div_wt_is_modified;
  }


  /** 
   * <em>experience_div_fund_sk</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDivFundSk()
  {
    return experience_div_fund_sk;
  }


  /** 
   * <em>experience_div_fund_sk</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivFundSkIsSet() {
    return experience_div_fund_sk_is_set;
  }


  /** 
   * <em>experience_div_fund_sk</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivFundSkIsModified() {
    return experience_div_fund_sk_is_modified;
  }


  /** 
   * <em>experience_div_fund_bd</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDivFundBd()
  {
    return experience_div_fund_bd;
  }


  /** 
   * <em>experience_div_fund_bd</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivFundBdIsSet() {
    return experience_div_fund_bd_is_set;
  }


  /** 
   * <em>experience_div_fund_bd</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivFundBdIsModified() {
    return experience_div_fund_bd_is_modified;
  }


  /** 
   * <em>experience_div_fo</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDivFo()
  {
    return experience_div_fo;
  }


  /** 
   * <em>experience_div_fo</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivFoIsSet() {
    return experience_div_fo_is_set;
  }


  /** 
   * <em>experience_div_fo</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivFoIsModified() {
    return experience_div_fo_is_modified;
  }


  /** 
   * <em>experience_div_f_equity</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDivFEquity()
  {
    return experience_div_f_equity;
  }


  /** 
   * <em>experience_div_f_equity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivFEquityIsSet() {
    return experience_div_f_equity_is_set;
  }


  /** 
   * <em>experience_div_f_equity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivFEquityIsModified() {
    return experience_div_f_equity_is_modified;
  }


  /** 
   * <em>experience_div_etc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDivEtc()
  {
    return experience_div_etc;
  }


  /** 
   * <em>experience_div_etc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivEtcIsSet() {
    return experience_div_etc_is_set;
  }


  /** 
   * <em>experience_div_etc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDivEtcIsModified() {
    return experience_div_etc_is_modified;
  }


  /** 
   * <em>experience_flag_equity</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagEquity()
  {
    return experience_flag_equity;
  }


  /** 
   * <em>experience_flag_equity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagEquityIsSet() {
    return experience_flag_equity_is_set;
  }


  /** 
   * <em>experience_flag_equity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagEquityIsModified() {
    return experience_flag_equity_is_modified;
  }


  /** 
   * <em>experience_flag_margin</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagMargin()
  {
    return experience_flag_margin;
  }


  /** 
   * <em>experience_flag_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagMarginIsSet() {
    return experience_flag_margin_is_set;
  }


  /** 
   * <em>experience_flag_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagMarginIsModified() {
    return experience_flag_margin_is_modified;
  }


  /** 
   * <em>experience_flag_bond</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagBond()
  {
    return experience_flag_bond;
  }


  /** 
   * <em>experience_flag_bond</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagBondIsSet() {
    return experience_flag_bond_is_set;
  }


  /** 
   * <em>experience_flag_bond</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagBondIsModified() {
    return experience_flag_bond_is_modified;
  }


  /** 
   * <em>experience_flag_wt</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagWt()
  {
    return experience_flag_wt;
  }


  /** 
   * <em>experience_flag_wt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagWtIsSet() {
    return experience_flag_wt_is_set;
  }


  /** 
   * <em>experience_flag_wt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagWtIsModified() {
    return experience_flag_wt_is_modified;
  }


  /** 
   * <em>experience_flag_fund_sk</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagFundSk()
  {
    return experience_flag_fund_sk;
  }


  /** 
   * <em>experience_flag_fund_sk</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagFundSkIsSet() {
    return experience_flag_fund_sk_is_set;
  }


  /** 
   * <em>experience_flag_fund_sk</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagFundSkIsModified() {
    return experience_flag_fund_sk_is_modified;
  }


  /** 
   * <em>experience_flag_fund_bd</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagFundBd()
  {
    return experience_flag_fund_bd;
  }


  /** 
   * <em>experience_flag_fund_bd</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagFundBdIsSet() {
    return experience_flag_fund_bd_is_set;
  }


  /** 
   * <em>experience_flag_fund_bd</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagFundBdIsModified() {
    return experience_flag_fund_bd_is_modified;
  }


  /** 
   * <em>experience_flag_fo</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagFo()
  {
    return experience_flag_fo;
  }


  /** 
   * <em>experience_flag_fo</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagFoIsSet() {
    return experience_flag_fo_is_set;
  }


  /** 
   * <em>experience_flag_fo</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagFoIsModified() {
    return experience_flag_fo_is_modified;
  }


  /** 
   * <em>experience_flag_f_equity</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagFEquity()
  {
    return experience_flag_f_equity;
  }


  /** 
   * <em>experience_flag_f_equity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagFEquityIsSet() {
    return experience_flag_f_equity_is_set;
  }


  /** 
   * <em>experience_flag_f_equity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagFEquityIsModified() {
    return experience_flag_f_equity_is_modified;
  }


  /** 
   * <em>experience_flag_etc</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagEtc()
  {
    return experience_flag_etc;
  }


  /** 
   * <em>experience_flag_etc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagEtcIsSet() {
    return experience_flag_etc_is_set;
  }


  /** 
   * <em>experience_flag_etc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlagEtcIsModified() {
    return experience_flag_etc_is_modified;
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
   * <em>interest_flag_equity</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagEquity()
  {
    return interest_flag_equity;
  }


  /** 
   * <em>interest_flag_equity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlagEquityIsSet() {
    return interest_flag_equity_is_set;
  }


  /** 
   * <em>interest_flag_equity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlagEquityIsModified() {
    return interest_flag_equity_is_modified;
  }


  /** 
   * <em>interest_flag_ministock</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagMinistock()
  {
    return interest_flag_ministock;
  }


  /** 
   * <em>interest_flag_ministock</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlagMinistockIsSet() {
    return interest_flag_ministock_is_set;
  }


  /** 
   * <em>interest_flag_ministock</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlagMinistockIsModified() {
    return interest_flag_ministock_is_modified;
  }


  /** 
   * <em>interest_flag_margin</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagMargin()
  {
    return interest_flag_margin;
  }


  /** 
   * <em>interest_flag_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlagMarginIsSet() {
    return interest_flag_margin_is_set;
  }


  /** 
   * <em>interest_flag_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlagMarginIsModified() {
    return interest_flag_margin_is_modified;
  }


  /** 
   * <em>interest_flag_bond</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagBond()
  {
    return interest_flag_bond;
  }


  /** 
   * <em>interest_flag_bond</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlagBondIsSet() {
    return interest_flag_bond_is_set;
  }


  /** 
   * <em>interest_flag_bond</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlagBondIsModified() {
    return interest_flag_bond_is_modified;
  }


  /** 
   * <em>interest_flag_fund</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagFund()
  {
    return interest_flag_fund;
  }


  /** 
   * <em>interest_flag_fund</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlagFundIsSet() {
    return interest_flag_fund_is_set;
  }


  /** 
   * <em>interest_flag_fund</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlagFundIsModified() {
    return interest_flag_fund_is_modified;
  }


  /** 
   * <em>interest_flag_fo</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagFo()
  {
    return interest_flag_fo;
  }


  /** 
   * <em>interest_flag_fo</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlagFoIsSet() {
    return interest_flag_fo_is_set;
  }


  /** 
   * <em>interest_flag_fo</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlagFoIsModified() {
    return interest_flag_fo_is_modified;
  }


  /** 
   * <em>interest_flag_f_equity</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagFEquity()
  {
    return interest_flag_f_equity;
  }


  /** 
   * <em>interest_flag_f_equity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlagFEquityIsSet() {
    return interest_flag_f_equity_is_set;
  }


  /** 
   * <em>interest_flag_f_equity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlagFEquityIsModified() {
    return interest_flag_f_equity_is_modified;
  }


  /** 
   * <em>interest_flag_etc</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagEtc()
  {
    return interest_flag_etc;
  }


  /** 
   * <em>interest_flag_etc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlagEtcIsSet() {
    return interest_flag_etc_is_set;
  }


  /** 
   * <em>interest_flag_etc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlagEtcIsModified() {
    return interest_flag_etc_is_modified;
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
   * <em>annual_income_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAnnualIncomeDiv()
  {
    return annual_income_div;
  }


  /** 
   * <em>annual_income_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAnnualIncomeDivIsSet() {
    return annual_income_div_is_set;
  }


  /** 
   * <em>annual_income_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAnnualIncomeDivIsModified() {
    return annual_income_div_is_modified;
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
   * <em>asset_value_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAssetValueDiv()
  {
    return asset_value_div;
  }


  /** 
   * <em>asset_value_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAssetValueDivIsSet() {
    return asset_value_div_is_set;
  }


  /** 
   * <em>asset_value_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAssetValueDivIsModified() {
    return asset_value_div_is_modified;
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
   * <em>fund_budget_amount_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFundBudgetAmountDiv()
  {
    return fund_budget_amount_div;
  }


  /** 
   * <em>fund_budget_amount_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundBudgetAmountDivIsSet() {
    return fund_budget_amount_div_is_set;
  }


  /** 
   * <em>fund_budget_amount_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundBudgetAmountDivIsModified() {
    return fund_budget_amount_div_is_modified;
  }


  /** 
   * <em>fund_budget_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFundBudgetDiv()
  {
    return fund_budget_div;
  }


  /** 
   * <em>fund_budget_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundBudgetDivIsSet() {
    return fund_budget_div_is_set;
  }


  /** 
   * <em>fund_budget_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundBudgetDivIsModified() {
    return fund_budget_div_is_modified;
  }


  /** 
   * <em>fund_budget_etc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFundBudgetEtc()
  {
    return fund_budget_etc;
  }


  /** 
   * <em>fund_budget_etc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundBudgetEtcIsSet() {
    return fund_budget_etc_is_set;
  }


  /** 
   * <em>fund_budget_etc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundBudgetEtcIsModified() {
    return fund_budget_etc_is_modified;
  }


  /** 
   * <em>id_confirm_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getIdConfirmFlag()
  {
    return id_confirm_flag;
  }


  /** 
   * <em>id_confirm_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIdConfirmFlagIsSet() {
    return id_confirm_flag_is_set;
  }


  /** 
   * <em>id_confirm_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIdConfirmFlagIsModified() {
    return id_confirm_flag_is_modified;
  }


  /** 
   * <em>id_confirm_doc_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIdConfirmDocDiv()
  {
    return id_confirm_doc_div;
  }


  /** 
   * <em>id_confirm_doc_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIdConfirmDocDivIsSet() {
    return id_confirm_doc_div_is_set;
  }


  /** 
   * <em>id_confirm_doc_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIdConfirmDocDivIsModified() {
    return id_confirm_doc_div_is_modified;
  }


  /** 
   * <em>id_confirm_doc_etc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIdConfirmDocEtc()
  {
    return id_confirm_doc_etc;
  }


  /** 
   * <em>id_confirm_doc_etc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIdConfirmDocEtcIsSet() {
    return id_confirm_doc_etc_is_set;
  }


  /** 
   * <em>id_confirm_doc_etc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIdConfirmDocEtcIsModified() {
    return id_confirm_doc_etc_is_modified;
  }


  /** 
   * <em>special_acc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSpecialAcc()
  {
    return special_acc;
  }


  /** 
   * <em>special_acc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialAccIsSet() {
    return special_acc_is_set;
  }


  /** 
   * <em>special_acc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialAccIsModified() {
    return special_acc_is_modified;
  }


  /** 
   * <em>special_acc_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSpecialAccMargin()
  {
    return special_acc_margin;
  }


  /** 
   * <em>special_acc_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialAccMarginIsSet() {
    return special_acc_margin_is_set;
  }


  /** 
   * <em>special_acc_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialAccMarginIsModified() {
    return special_acc_margin_is_modified;
  }


  /** 
   * <em>insider_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInsiderFlag()
  {
    return insider_flag;
  }


  /** 
   * <em>insider_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsiderFlagIsSet() {
    return insider_flag_is_set;
  }


  /** 
   * <em>insider_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsiderFlagIsModified() {
    return insider_flag_is_modified;
  }


  /** 
   * <em>product_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductName()
  {
    return product_name;
  }


  /** 
   * <em>product_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductNameIsSet() {
    return product_name_is_set;
  }


  /** 
   * <em>product_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductNameIsModified() {
    return product_name_is_modified;
  }


  /** 
   * <em>send_zip_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSendZipCode()
  {
    return send_zip_code;
  }


  /** 
   * <em>send_zip_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendZipCodeIsSet() {
    return send_zip_code_is_set;
  }


  /** 
   * <em>send_zip_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendZipCodeIsModified() {
    return send_zip_code_is_modified;
  }


  /** 
   * <em>send_address_line1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSendAddressLine1()
  {
    return send_address_line1;
  }


  /** 
   * <em>send_address_line1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendAddressLine1IsSet() {
    return send_address_line1_is_set;
  }


  /** 
   * <em>send_address_line1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendAddressLine1IsModified() {
    return send_address_line1_is_modified;
  }


  /** 
   * <em>send_address_line2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSendAddressLine2()
  {
    return send_address_line2;
  }


  /** 
   * <em>send_address_line2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendAddressLine2IsSet() {
    return send_address_line2_is_set;
  }


  /** 
   * <em>send_address_line2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendAddressLine2IsModified() {
    return send_address_line2_is_modified;
  }


  /** 
   * <em>send_address_line3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSendAddressLine3()
  {
    return send_address_line3;
  }


  /** 
   * <em>send_address_line3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendAddressLine3IsSet() {
    return send_address_line3_is_set;
  }


  /** 
   * <em>send_address_line3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSendAddressLine3IsModified() {
    return send_address_line3_is_modified;
  }


  /** 
   * <em>ext_item_div1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemDiv1()
  {
    return ext_item_div1;
  }


  /** 
   * <em>ext_item_div1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv1IsSet() {
    return ext_item_div1_is_set;
  }


  /** 
   * <em>ext_item_div1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv1IsModified() {
    return ext_item_div1_is_modified;
  }


  /** 
   * <em>ext_item_div2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemDiv2()
  {
    return ext_item_div2;
  }


  /** 
   * <em>ext_item_div2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv2IsSet() {
    return ext_item_div2_is_set;
  }


  /** 
   * <em>ext_item_div2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv2IsModified() {
    return ext_item_div2_is_modified;
  }


  /** 
   * <em>ext_item_div3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemDiv3()
  {
    return ext_item_div3;
  }


  /** 
   * <em>ext_item_div3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv3IsSet() {
    return ext_item_div3_is_set;
  }


  /** 
   * <em>ext_item_div3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv3IsModified() {
    return ext_item_div3_is_modified;
  }


  /** 
   * <em>ext_item_div4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemDiv4()
  {
    return ext_item_div4;
  }


  /** 
   * <em>ext_item_div4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv4IsSet() {
    return ext_item_div4_is_set;
  }


  /** 
   * <em>ext_item_div4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv4IsModified() {
    return ext_item_div4_is_modified;
  }


  /** 
   * <em>ext_item_div5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemDiv5()
  {
    return ext_item_div5;
  }


  /** 
   * <em>ext_item_div5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv5IsSet() {
    return ext_item_div5_is_set;
  }


  /** 
   * <em>ext_item_div5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv5IsModified() {
    return ext_item_div5_is_modified;
  }


  /** 
   * <em>ext_item_div6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemDiv6()
  {
    return ext_item_div6;
  }


  /** 
   * <em>ext_item_div6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv6IsSet() {
    return ext_item_div6_is_set;
  }


  /** 
   * <em>ext_item_div6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv6IsModified() {
    return ext_item_div6_is_modified;
  }


  /** 
   * <em>ext_item_div7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemDiv7()
  {
    return ext_item_div7;
  }


  /** 
   * <em>ext_item_div7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv7IsSet() {
    return ext_item_div7_is_set;
  }


  /** 
   * <em>ext_item_div7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv7IsModified() {
    return ext_item_div7_is_modified;
  }


  /** 
   * <em>ext_item_div8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemDiv8()
  {
    return ext_item_div8;
  }


  /** 
   * <em>ext_item_div8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv8IsSet() {
    return ext_item_div8_is_set;
  }


  /** 
   * <em>ext_item_div8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv8IsModified() {
    return ext_item_div8_is_modified;
  }


  /** 
   * <em>ext_item_div9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemDiv9()
  {
    return ext_item_div9;
  }


  /** 
   * <em>ext_item_div9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv9IsSet() {
    return ext_item_div9_is_set;
  }


  /** 
   * <em>ext_item_div9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv9IsModified() {
    return ext_item_div9_is_modified;
  }


  /** 
   * <em>ext_item_div10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemDiv10()
  {
    return ext_item_div10;
  }


  /** 
   * <em>ext_item_div10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv10IsSet() {
    return ext_item_div10_is_set;
  }


  /** 
   * <em>ext_item_div10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv10IsModified() {
    return ext_item_div10_is_modified;
  }


  /** 
   * <em>ext_item_flag1</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag1()
  {
    return ext_item_flag1;
  }


  /** 
   * <em>ext_item_flag1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag1IsSet() {
    return ext_item_flag1_is_set;
  }


  /** 
   * <em>ext_item_flag1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag1IsModified() {
    return ext_item_flag1_is_modified;
  }


  /** 
   * <em>ext_item_flag2</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag2()
  {
    return ext_item_flag2;
  }


  /** 
   * <em>ext_item_flag2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag2IsSet() {
    return ext_item_flag2_is_set;
  }


  /** 
   * <em>ext_item_flag2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag2IsModified() {
    return ext_item_flag2_is_modified;
  }


  /** 
   * <em>ext_item_flag3</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag3()
  {
    return ext_item_flag3;
  }


  /** 
   * <em>ext_item_flag3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag3IsSet() {
    return ext_item_flag3_is_set;
  }


  /** 
   * <em>ext_item_flag3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag3IsModified() {
    return ext_item_flag3_is_modified;
  }


  /** 
   * <em>ext_item_flag4</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag4()
  {
    return ext_item_flag4;
  }


  /** 
   * <em>ext_item_flag4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag4IsSet() {
    return ext_item_flag4_is_set;
  }


  /** 
   * <em>ext_item_flag4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag4IsModified() {
    return ext_item_flag4_is_modified;
  }


  /** 
   * <em>ext_item_flag5</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag5()
  {
    return ext_item_flag5;
  }


  /** 
   * <em>ext_item_flag5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag5IsSet() {
    return ext_item_flag5_is_set;
  }


  /** 
   * <em>ext_item_flag5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag5IsModified() {
    return ext_item_flag5_is_modified;
  }


  /** 
   * <em>ext_item_flag6</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag6()
  {
    return ext_item_flag6;
  }


  /** 
   * <em>ext_item_flag6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag6IsSet() {
    return ext_item_flag6_is_set;
  }


  /** 
   * <em>ext_item_flag6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag6IsModified() {
    return ext_item_flag6_is_modified;
  }


  /** 
   * <em>ext_item_flag7</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag7()
  {
    return ext_item_flag7;
  }


  /** 
   * <em>ext_item_flag7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag7IsSet() {
    return ext_item_flag7_is_set;
  }


  /** 
   * <em>ext_item_flag7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag7IsModified() {
    return ext_item_flag7_is_modified;
  }


  /** 
   * <em>ext_item_flag8</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag8()
  {
    return ext_item_flag8;
  }


  /** 
   * <em>ext_item_flag8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag8IsSet() {
    return ext_item_flag8_is_set;
  }


  /** 
   * <em>ext_item_flag8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag8IsModified() {
    return ext_item_flag8_is_modified;
  }


  /** 
   * <em>ext_item_flag9</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag9()
  {
    return ext_item_flag9;
  }


  /** 
   * <em>ext_item_flag9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag9IsSet() {
    return ext_item_flag9_is_set;
  }


  /** 
   * <em>ext_item_flag9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag9IsModified() {
    return ext_item_flag9_is_modified;
  }


  /** 
   * <em>ext_item_flag10</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag10()
  {
    return ext_item_flag10;
  }


  /** 
   * <em>ext_item_flag10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag10IsSet() {
    return ext_item_flag10_is_set;
  }


  /** 
   * <em>ext_item_flag10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemFlag10IsModified() {
    return ext_item_flag10_is_modified;
  }


  /** 
   * <em>ext_item_text1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemText1()
  {
    return ext_item_text1;
  }


  /** 
   * <em>ext_item_text1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText1IsSet() {
    return ext_item_text1_is_set;
  }


  /** 
   * <em>ext_item_text1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText1IsModified() {
    return ext_item_text1_is_modified;
  }


  /** 
   * <em>ext_item_text2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemText2()
  {
    return ext_item_text2;
  }


  /** 
   * <em>ext_item_text2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText2IsSet() {
    return ext_item_text2_is_set;
  }


  /** 
   * <em>ext_item_text2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText2IsModified() {
    return ext_item_text2_is_modified;
  }


  /** 
   * <em>ext_item_text3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemText3()
  {
    return ext_item_text3;
  }


  /** 
   * <em>ext_item_text3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText3IsSet() {
    return ext_item_text3_is_set;
  }


  /** 
   * <em>ext_item_text3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText3IsModified() {
    return ext_item_text3_is_modified;
  }


  /** 
   * <em>ext_item_text4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemText4()
  {
    return ext_item_text4;
  }


  /** 
   * <em>ext_item_text4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText4IsSet() {
    return ext_item_text4_is_set;
  }


  /** 
   * <em>ext_item_text4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText4IsModified() {
    return ext_item_text4_is_modified;
  }


  /** 
   * <em>ext_item_text5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemText5()
  {
    return ext_item_text5;
  }


  /** 
   * <em>ext_item_text5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText5IsSet() {
    return ext_item_text5_is_set;
  }


  /** 
   * <em>ext_item_text5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText5IsModified() {
    return ext_item_text5_is_modified;
  }


  /** 
   * <em>ext_item_text6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemText6()
  {
    return ext_item_text6;
  }


  /** 
   * <em>ext_item_text6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText6IsSet() {
    return ext_item_text6_is_set;
  }


  /** 
   * <em>ext_item_text6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText6IsModified() {
    return ext_item_text6_is_modified;
  }


  /** 
   * <em>ext_item_text7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemText7()
  {
    return ext_item_text7;
  }


  /** 
   * <em>ext_item_text7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText7IsSet() {
    return ext_item_text7_is_set;
  }


  /** 
   * <em>ext_item_text7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText7IsModified() {
    return ext_item_text7_is_modified;
  }


  /** 
   * <em>ext_item_text8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemText8()
  {
    return ext_item_text8;
  }


  /** 
   * <em>ext_item_text8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText8IsSet() {
    return ext_item_text8_is_set;
  }


  /** 
   * <em>ext_item_text8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText8IsModified() {
    return ext_item_text8_is_modified;
  }


  /** 
   * <em>ext_item_text9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemText9()
  {
    return ext_item_text9;
  }


  /** 
   * <em>ext_item_text9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText9IsSet() {
    return ext_item_text9_is_set;
  }


  /** 
   * <em>ext_item_text9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText9IsModified() {
    return ext_item_text9_is_modified;
  }


  /** 
   * <em>ext_item_text10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemText10()
  {
    return ext_item_text10;
  }


  /** 
   * <em>ext_item_text10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText10IsSet() {
    return ext_item_text10_is_set;
  }


  /** 
   * <em>ext_item_text10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemText10IsModified() {
    return ext_item_text10_is_modified;
  }


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
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
   * <em>creator</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCreator()
  {
    return creator;
  }


  /** 
   * <em>creator</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatorIsSet() {
    return creator_is_set;
  }


  /** 
   * <em>creator</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatorIsModified() {
    return creator_is_modified;
  }


  /** 
   * <em>exclusive_use_account_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExclusiveUseAccountNo()
  {
    return exclusive_use_account_no;
  }


  /** 
   * <em>exclusive_use_account_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExclusiveUseAccountNoIsSet() {
    return exclusive_use_account_no_is_set;
  }


  /** 
   * <em>exclusive_use_account_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExclusiveUseAccountNoIsModified() {
    return exclusive_use_account_no_is_modified;
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
   * <em>real_name_voucher_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRealNameVoucherDiv()
  {
    return real_name_voucher_div;
  }


  /** 
   * <em>real_name_voucher_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealNameVoucherDivIsSet() {
    return real_name_voucher_div_is_set;
  }


  /** 
   * <em>real_name_voucher_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealNameVoucherDivIsModified() {
    return real_name_voucher_div_is_modified;
  }


  /** 
   * <em>real_name1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRealName1()
  {
    return real_name1;
  }


  /** 
   * <em>real_name1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealName1IsSet() {
    return real_name1_is_set;
  }


  /** 
   * <em>real_name1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealName1IsModified() {
    return real_name1_is_modified;
  }


  /** 
   * <em>real_name2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRealName2()
  {
    return real_name2;
  }


  /** 
   * <em>real_name2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealName2IsSet() {
    return real_name2_is_set;
  }


  /** 
   * <em>real_name2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealName2IsModified() {
    return real_name2_is_modified;
  }


  /** 
   * <em>insider_voucher_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInsiderVoucherDiv()
  {
    return insider_voucher_div;
  }


  /** 
   * <em>insider_voucher_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsiderVoucherDivIsSet() {
    return insider_voucher_div_is_set;
  }


  /** 
   * <em>insider_voucher_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsiderVoucherDivIsModified() {
    return insider_voucher_div_is_modified;
  }


  /** 
   * <em>insider_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInsiderProductCode()
  {
    return insider_product_code;
  }


  /** 
   * <em>insider_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsiderProductCodeIsSet() {
    return insider_product_code_is_set;
  }


  /** 
   * <em>insider_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsiderProductCodeIsModified() {
    return insider_product_code_is_modified;
  }


  /** 
   * <em>insider_relation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInsiderRelationDiv()
  {
    return insider_relation_div;
  }


  /** 
   * <em>insider_relation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsiderRelationDivIsSet() {
    return insider_relation_div_is_set;
  }


  /** 
   * <em>insider_relation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsiderRelationDivIsModified() {
    return insider_relation_div_is_modified;
  }


  /** 
   * <em>insider_officer_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInsiderOfficerName()
  {
    return insider_officer_name;
  }


  /** 
   * <em>insider_officer_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsiderOfficerNameIsSet() {
    return insider_officer_name_is_set;
  }


  /** 
   * <em>insider_officer_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsiderOfficerNameIsModified() {
    return insider_officer_name_is_modified;
  }


  /** 
   * <em>insider_post_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInsiderPostCode()
  {
    return insider_post_code;
  }


  /** 
   * <em>insider_post_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsiderPostCodeIsSet() {
    return insider_post_code_is_set;
  }


  /** 
   * <em>insider_post_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsiderPostCodeIsModified() {
    return insider_post_code_is_modified;
  }


  /** 
   * <em>insider_post_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInsiderPostName()
  {
    return insider_post_name;
  }


  /** 
   * <em>insider_post_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsiderPostNameIsSet() {
    return insider_post_name_is_set;
  }


  /** 
   * <em>insider_post_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInsiderPostNameIsModified() {
    return insider_post_name_is_modified;
  }


  /** 
   * <em>gp_voucher_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpVoucherDiv()
  {
    return gp_voucher_div;
  }


  /** 
   * <em>gp_voucher_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpVoucherDivIsSet() {
    return gp_voucher_div_is_set;
  }


  /** 
   * <em>gp_voucher_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpVoucherDivIsModified() {
    return gp_voucher_div_is_modified;
  }


  /** 
   * <em>gp_course</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpCourse()
  {
    return gp_course;
  }


  /** 
   * <em>gp_course</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpCourseIsSet() {
    return gp_course_is_set;
  }


  /** 
   * <em>gp_course</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpCourseIsModified() {
    return gp_course_is_modified;
  }


  /** 
   * <em>gp_plan</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpPlan()
  {
    return gp_plan;
  }


  /** 
   * <em>gp_plan</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpPlanIsSet() {
    return gp_plan_is_set;
  }


  /** 
   * <em>gp_plan</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpPlanIsModified() {
    return gp_plan_is_modified;
  }


  /** 
   * <em>gp_target_figure</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpTargetFigure()
  {
    return gp_target_figure;
  }


  /** 
   * <em>gp_target_figure</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpTargetFigureIsSet() {
    return gp_target_figure_is_set;
  }


  /** 
   * <em>gp_target_figure</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpTargetFigureIsModified() {
    return gp_target_figure_is_modified;
  }


  /** 
   * <em>gp_target_year</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpTargetYear()
  {
    return gp_target_year;
  }


  /** 
   * <em>gp_target_year</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpTargetYearIsSet() {
    return gp_target_year_is_set;
  }


  /** 
   * <em>gp_target_year</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpTargetYearIsModified() {
    return gp_target_year_is_modified;
  }


  /** 
   * <em>gp_target_month</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpTargetMonth()
  {
    return gp_target_month;
  }


  /** 
   * <em>gp_target_month</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpTargetMonthIsSet() {
    return gp_target_month_is_set;
  }


  /** 
   * <em>gp_target_month</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpTargetMonthIsModified() {
    return gp_target_month_is_modified;
  }


  /** 
   * <em>gp_installment_figure</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpInstallmentFigure()
  {
    return gp_installment_figure;
  }


  /** 
   * <em>gp_installment_figure</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpInstallmentFigureIsSet() {
    return gp_installment_figure_is_set;
  }


  /** 
   * <em>gp_installment_figure</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpInstallmentFigureIsModified() {
    return gp_installment_figure_is_modified;
  }


  /** 
   * <em>gp_deposit_cycle</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpDepositCycle()
  {
    return gp_deposit_cycle;
  }


  /** 
   * <em>gp_deposit_cycle</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpDepositCycleIsSet() {
    return gp_deposit_cycle_is_set;
  }


  /** 
   * <em>gp_deposit_cycle</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpDepositCycleIsModified() {
    return gp_deposit_cycle_is_modified;
  }


  /** 
   * <em>gp_payment_root</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpPaymentRoot()
  {
    return gp_payment_root;
  }


  /** 
   * <em>gp_payment_root</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpPaymentRootIsSet() {
    return gp_payment_root_is_set;
  }


  /** 
   * <em>gp_payment_root</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpPaymentRootIsModified() {
    return gp_payment_root_is_modified;
  }


  /** 
   * <em>gp_reinvest_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpReinvestDiv()
  {
    return gp_reinvest_div;
  }


  /** 
   * <em>gp_reinvest_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpReinvestDivIsSet() {
    return gp_reinvest_div_is_set;
  }


  /** 
   * <em>gp_reinvest_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpReinvestDivIsModified() {
    return gp_reinvest_div_is_modified;
  }


  /** 
   * <em>gp_tax_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpTaxDiv()
  {
    return gp_tax_div;
  }


  /** 
   * <em>gp_tax_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpTaxDivIsSet() {
    return gp_tax_div_is_set;
  }


  /** 
   * <em>gp_tax_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpTaxDivIsModified() {
    return gp_tax_div_is_modified;
  }


  /** 
   * <em>gp_taxfree_limit</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpTaxfreeLimit()
  {
    return gp_taxfree_limit;
  }


  /** 
   * <em>gp_taxfree_limit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpTaxfreeLimitIsSet() {
    return gp_taxfree_limit_is_set;
  }


  /** 
   * <em>gp_taxfree_limit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpTaxfreeLimitIsModified() {
    return gp_taxfree_limit_is_modified;
  }


  /** 
   * <em>gp_special_taxfree_limit</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpSpecialTaxfreeLimit()
  {
    return gp_special_taxfree_limit;
  }


  /** 
   * <em>gp_special_taxfree_limit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpSpecialTaxfreeLimitIsSet() {
    return gp_special_taxfree_limit_is_set;
  }


  /** 
   * <em>gp_special_taxfree_limit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpSpecialTaxfreeLimitIsModified() {
    return gp_special_taxfree_limit_is_modified;
  }


  /** 
   * <em>gp_subscr_summary</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpSubscrSummary()
  {
    return gp_subscr_summary;
  }


  /** 
   * <em>gp_subscr_summary</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpSubscrSummaryIsSet() {
    return gp_subscr_summary_is_set;
  }


  /** 
   * <em>gp_subscr_summary</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpSubscrSummaryIsModified() {
    return gp_subscr_summary_is_modified;
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
   * <em>gp_mortgage_customer</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpMortgageCustomer()
  {
    return gp_mortgage_customer;
  }


  /** 
   * <em>gp_mortgage_customer</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpMortgageCustomerIsSet() {
    return gp_mortgage_customer_is_set;
  }


  /** 
   * <em>gp_mortgage_customer</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpMortgageCustomerIsModified() {
    return gp_mortgage_customer_is_modified;
  }


  /** 
   * <em>gp_mix_customer</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpMixCustomer()
  {
    return gp_mix_customer;
  }


  /** 
   * <em>gp_mix_customer</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpMixCustomerIsSet() {
    return gp_mix_customer_is_set;
  }


  /** 
   * <em>gp_mix_customer</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpMixCustomerIsModified() {
    return gp_mix_customer_is_modified;
  }


  /** 
   * <em>gp_contract</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpContract()
  {
    return gp_contract;
  }


  /** 
   * <em>gp_contract</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpContractIsSet() {
    return gp_contract_is_set;
  }


  /** 
   * <em>gp_contract</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpContractIsModified() {
    return gp_contract_is_modified;
  }


  /** 
   * <em>stk_voucher_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStkVoucherDiv()
  {
    return stk_voucher_div;
  }


  /** 
   * <em>stk_voucher_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStkVoucherDivIsSet() {
    return stk_voucher_div_is_set;
  }


  /** 
   * <em>stk_voucher_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStkVoucherDivIsModified() {
    return stk_voucher_div_is_modified;
  }


  /** 
   * <em>stk_taxation_tran_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStkTaxationTranDiv()
  {
    return stk_taxation_tran_div;
  }


  /** 
   * <em>stk_taxation_tran_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStkTaxationTranDivIsSet() {
    return stk_taxation_tran_div_is_set;
  }


  /** 
   * <em>stk_taxation_tran_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStkTaxationTranDivIsModified() {
    return stk_taxation_tran_div_is_modified;
  }


  /** 
   * <em>stk_address_line_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStkAddressLineKana()
  {
    return stk_address_line_kana;
  }


  /** 
   * <em>stk_address_line_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStkAddressLineKanaIsSet() {
    return stk_address_line_kana_is_set;
  }


  /** 
   * <em>stk_address_line_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStkAddressLineKanaIsModified() {
    return stk_address_line_kana_is_modified;
  }


  /** 
   * <em>stk_transfer_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStkTransferDiv()
  {
    return stk_transfer_div;
  }


  /** 
   * <em>stk_transfer_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStkTransferDivIsSet() {
    return stk_transfer_div_is_set;
  }


  /** 
   * <em>stk_transfer_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStkTransferDivIsModified() {
    return stk_transfer_div_is_modified;
  }


  /** 
   * <em>stk_fin_institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStkFinInstitutionCode()
  {
    return stk_fin_institution_code;
  }


  /** 
   * <em>stk_fin_institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStkFinInstitutionCodeIsSet() {
    return stk_fin_institution_code_is_set;
  }


  /** 
   * <em>stk_fin_institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStkFinInstitutionCodeIsModified() {
    return stk_fin_institution_code_is_modified;
  }


  /** 
   * <em>stk_fin_branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStkFinBranchCode()
  {
    return stk_fin_branch_code;
  }


  /** 
   * <em>stk_fin_branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStkFinBranchCodeIsSet() {
    return stk_fin_branch_code_is_set;
  }


  /** 
   * <em>stk_fin_branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStkFinBranchCodeIsModified() {
    return stk_fin_branch_code_is_modified;
  }


  /** 
   * <em>stk_fin_save_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStkFinSaveDiv()
  {
    return stk_fin_save_div;
  }


  /** 
   * <em>stk_fin_save_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStkFinSaveDivIsSet() {
    return stk_fin_save_div_is_set;
  }


  /** 
   * <em>stk_fin_save_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStkFinSaveDivIsModified() {
    return stk_fin_save_div_is_modified;
  }


  /** 
   * <em>stk_fin_account_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStkFinAccountNo()
  {
    return stk_fin_account_no;
  }


  /** 
   * <em>stk_fin_account_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStkFinAccountNoIsSet() {
    return stk_fin_account_no_is_set;
  }


  /** 
   * <em>stk_fin_account_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStkFinAccountNoIsModified() {
    return stk_fin_account_no_is_modified;
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
   * <em>ext_item_div11</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemDiv11()
  {
    return ext_item_div11;
  }


  /** 
   * <em>ext_item_div11</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv11IsSet() {
    return ext_item_div11_is_set;
  }


  /** 
   * <em>ext_item_div11</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv11IsModified() {
    return ext_item_div11_is_modified;
  }


  /** 
   * <em>ext_item_div12</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemDiv12()
  {
    return ext_item_div12;
  }


  /** 
   * <em>ext_item_div12</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv12IsSet() {
    return ext_item_div12_is_set;
  }


  /** 
   * <em>ext_item_div12</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv12IsModified() {
    return ext_item_div12_is_modified;
  }


  /** 
   * <em>ext_item_div13</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemDiv13()
  {
    return ext_item_div13;
  }


  /** 
   * <em>ext_item_div13</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv13IsSet() {
    return ext_item_div13_is_set;
  }


  /** 
   * <em>ext_item_div13</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv13IsModified() {
    return ext_item_div13_is_modified;
  }


  /** 
   * <em>ext_item_div14</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemDiv14()
  {
    return ext_item_div14;
  }


  /** 
   * <em>ext_item_div14</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv14IsSet() {
    return ext_item_div14_is_set;
  }


  /** 
   * <em>ext_item_div14</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv14IsModified() {
    return ext_item_div14_is_modified;
  }


  /** 
   * <em>ext_item_div15</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExtItemDiv15()
  {
    return ext_item_div15;
  }


  /** 
   * <em>ext_item_div15</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv15IsSet() {
    return ext_item_div15_is_set;
  }


  /** 
   * <em>ext_item_div15</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExtItemDiv15IsModified() {
    return ext_item_div15_is_modified;
  }


  /** 
   * <em>foreign_account_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getForeignAccountNo()
  {
    return foreign_account_no;
  }


  /** 
   * <em>foreign_account_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignAccountNoIsSet() {
    return foreign_account_no_is_set;
  }


  /** 
   * <em>foreign_account_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignAccountNoIsModified() {
    return foreign_account_no_is_modified;
  }


  /** 
   * <em>foreign_account_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getForeignAccountName()
  {
    return foreign_account_name;
  }


  /** 
   * <em>foreign_account_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignAccountNameIsSet() {
    return foreign_account_name_is_set;
  }


  /** 
   * <em>foreign_account_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignAccountNameIsModified() {
    return foreign_account_name_is_modified;
  }


  /** 
   * <em>foreign_account_name_eng</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getForeignAccountNameEng()
  {
    return foreign_account_name_eng;
  }


  /** 
   * <em>foreign_account_name_eng</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignAccountNameEngIsSet() {
    return foreign_account_name_eng_is_set;
  }


  /** 
   * <em>foreign_account_name_eng</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignAccountNameEngIsModified() {
    return foreign_account_name_eng_is_modified;
  }


  /** 
   * <em>foreign_save_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getForeignSaveDiv()
  {
    return foreign_save_div;
  }


  /** 
   * <em>foreign_save_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignSaveDivIsSet() {
    return foreign_save_div_is_set;
  }


  /** 
   * <em>foreign_save_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignSaveDivIsModified() {
    return foreign_save_div_is_modified;
  }


  /** 
   * <em>delete_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDeleteFlag()
  {
    return delete_flag;
  }


  /** 
   * <em>delete_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteFlagIsSet() {
    return delete_flag_is_set;
  }


  /** 
   * <em>delete_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteFlagIsModified() {
    return delete_flag_is_modified;
  }


  /** 
   * <em>delete_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getDeleteTimestamp()
  {
    return delete_timestamp;
  }


  /** 
   * <em>delete_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteTimestampIsSet() {
    return delete_timestamp_is_set;
  }


  /** 
   * <em>delete_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeleteTimestampIsModified() {
    return delete_timestamp_is_modified;
  }


  /** 
   * <em>print_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPrintFlag()
  {
    return print_flag;
  }


  /** 
   * <em>print_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrintFlagIsSet() {
    return print_flag_is_set;
  }


  /** 
   * <em>print_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrintFlagIsModified() {
    return print_flag_is_modified;
  }


  /** 
   * <em>receipt_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getReceiptFlag()
  {
    return receipt_flag;
  }


  /** 
   * <em>receipt_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptFlagIsSet() {
    return receipt_flag_is_set;
  }


  /** 
   * <em>receipt_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptFlagIsModified() {
    return receipt_flag_is_modified;
  }


  /** 
   * <em>agreement_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getAgreementFlag()
  {
    return agreement_flag;
  }


  /** 
   * <em>agreement_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgreementFlagIsSet() {
    return agreement_flag_is_set;
  }


  /** 
   * <em>agreement_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgreementFlagIsModified() {
    return agreement_flag_is_modified;
  }


  /** 
   * <em>foreign_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getForeignFlag()
  {
    return foreign_flag;
  }


  /** 
   * <em>foreign_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignFlagIsSet() {
    return foreign_flag_is_set;
  }


  /** 
   * <em>foreign_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignFlagIsModified() {
    return foreign_flag_is_modified;
  }


  /** 
   * <em>agency_acc_name_kana1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAgencyAccNameKana1()
  {
    return agency_acc_name_kana1;
  }


  /** 
   * <em>agency_acc_name_kana1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyAccNameKana1IsSet() {
    return agency_acc_name_kana1_is_set;
  }


  /** 
   * <em>agency_acc_name_kana1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyAccNameKana1IsModified() {
    return agency_acc_name_kana1_is_modified;
  }


  /** 
   * <em>agency_acc_name_kana2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAgencyAccNameKana2()
  {
    return agency_acc_name_kana2;
  }


  /** 
   * <em>agency_acc_name_kana2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyAccNameKana2IsSet() {
    return agency_acc_name_kana2_is_set;
  }


  /** 
   * <em>agency_acc_name_kana2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyAccNameKana2IsModified() {
    return agency_acc_name_kana2_is_modified;
  }


  /** 
   * <em>agency_acc_name1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAgencyAccName1()
  {
    return agency_acc_name1;
  }


  /** 
   * <em>agency_acc_name1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyAccName1IsSet() {
    return agency_acc_name1_is_set;
  }


  /** 
   * <em>agency_acc_name1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyAccName1IsModified() {
    return agency_acc_name1_is_modified;
  }


  /** 
   * <em>agency_acc_name2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAgencyAccName2()
  {
    return agency_acc_name2;
  }


  /** 
   * <em>agency_acc_name2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyAccName2IsSet() {
    return agency_acc_name2_is_set;
  }


  /** 
   * <em>agency_acc_name2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyAccName2IsModified() {
    return agency_acc_name2_is_modified;
  }


  /** 
   * <em>agency_address_line1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAgencyAddressLine1()
  {
    return agency_address_line1;
  }


  /** 
   * <em>agency_address_line1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyAddressLine1IsSet() {
    return agency_address_line1_is_set;
  }


  /** 
   * <em>agency_address_line1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyAddressLine1IsModified() {
    return agency_address_line1_is_modified;
  }


  /** 
   * <em>agency_address_line2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAgencyAddressLine2()
  {
    return agency_address_line2;
  }


  /** 
   * <em>agency_address_line2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyAddressLine2IsSet() {
    return agency_address_line2_is_set;
  }


  /** 
   * <em>agency_address_line2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyAddressLine2IsModified() {
    return agency_address_line2_is_modified;
  }


  /** 
   * <em>agency_rep_post</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAgencyRepPost()
  {
    return agency_rep_post;
  }


  /** 
   * <em>agency_rep_post</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyRepPostIsSet() {
    return agency_rep_post_is_set;
  }


  /** 
   * <em>agency_rep_post</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyRepPostIsModified() {
    return agency_rep_post_is_modified;
  }


  /** 
   * <em>agency_rep_name_kana1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAgencyRepNameKana1()
  {
    return agency_rep_name_kana1;
  }


  /** 
   * <em>agency_rep_name_kana1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyRepNameKana1IsSet() {
    return agency_rep_name_kana1_is_set;
  }


  /** 
   * <em>agency_rep_name_kana1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyRepNameKana1IsModified() {
    return agency_rep_name_kana1_is_modified;
  }


  /** 
   * <em>agency_rep_name_kana2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAgencyRepNameKana2()
  {
    return agency_rep_name_kana2;
  }


  /** 
   * <em>agency_rep_name_kana2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyRepNameKana2IsSet() {
    return agency_rep_name_kana2_is_set;
  }


  /** 
   * <em>agency_rep_name_kana2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyRepNameKana2IsModified() {
    return agency_rep_name_kana2_is_modified;
  }


  /** 
   * <em>agency_rep_name1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAgencyRepName1()
  {
    return agency_rep_name1;
  }


  /** 
   * <em>agency_rep_name1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyRepName1IsSet() {
    return agency_rep_name1_is_set;
  }


  /** 
   * <em>agency_rep_name1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyRepName1IsModified() {
    return agency_rep_name1_is_modified;
  }


  /** 
   * <em>agency_rep_name2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAgencyRepName2()
  {
    return agency_rep_name2;
  }


  /** 
   * <em>agency_rep_name2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyRepName2IsSet() {
    return agency_rep_name2_is_set;
  }


  /** 
   * <em>agency_rep_name2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAgencyRepName2IsModified() {
    return agency_rep_name2_is_modified;
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
   * <em>error_info</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getErrorInfo()
  {
    return error_info;
  }


  /** 
   * <em>error_info</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorInfoIsSet() {
    return error_info_is_set;
  }


  /** 
   * <em>error_info</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getErrorInfoIsModified() {
    return error_info_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new ExpAccountOpenTempPK(institution_code, acc_open_request_number);
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
   * <em>ex_account_flag</em>カラムの値を設定します。 
   *
   * @@param p_exAccountFlag <em>ex_account_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExAccountFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_exAccountFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ex_account_flag = p_exAccountFlag;
    ex_account_flag_is_set = true;
    ex_account_flag_is_modified = true;
  }


  /** 
   * <em>ex_branch_name</em>カラムの値を設定します。 
   *
   * @@param p_exBranchName <em>ex_branch_name</em>カラムの値をあらわす32桁以下のString値 
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
   * <em>infomation_claim_datetime</em>カラムの値を設定します。 
   *
   * @@param p_infomationClaimDatetime <em>infomation_claim_datetime</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setInfomationClaimDatetime( java.sql.Timestamp p_infomationClaimDatetime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    infomation_claim_datetime = p_infomationClaimDatetime;
    infomation_claim_datetime_is_set = true;
    infomation_claim_datetime_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>infomation_claim_datetime</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setInfomationClaimDatetime( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    infomation_claim_datetime = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    infomation_claim_datetime_is_set = true;
    infomation_claim_datetime_is_modified = true;
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
   * <em>initial_password</em>カラムの値を設定します。 
   *
   * @@param p_initialPassword <em>initial_password</em>カラムの値をあらわす48桁以下のString値 
   */
  public final void setInitialPassword( String p_initialPassword )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    initial_password = p_initialPassword;
    initial_password_is_set = true;
    initial_password_is_modified = true;
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
   * <em>given_name</em>カラムの値を設定します。 
   *
   * @@param p_givenName <em>given_name</em>カラムの値をあらわす40桁以下のString値 
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
   * <em>given_name_alt1</em>カラムの値を設定します。 
   *
   * @@param p_givenNameAlt1 <em>given_name_alt1</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setGivenNameAlt1( String p_givenNameAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    given_name_alt1 = p_givenNameAlt1;
    given_name_alt1_is_set = true;
    given_name_alt1_is_modified = true;
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
   * @@param p_addressLine1Kana <em>address_line1_kana</em>カラムの値をあらわす60桁以下のString値 
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
   * @@param p_addressLine2Kana <em>address_line2_kana</em>カラムの値をあらわす60桁以下のString値 
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
   * @@param p_addressLine3Kana <em>address_line3_kana</em>カラムの値をあらわす60桁以下のString値 
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
   * @@param p_officeZipCode <em>office_zip_code</em>カラムの値をあらわす7桁以下のString値 
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
   * <em>office_fax</em>カラムの値を設定します。 
   *
   * @@param p_officeFax <em>office_fax</em>カラムの値をあらわす16桁以下のString値 
   */
  public final void setOfficeFax( String p_officeFax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    office_fax = p_officeFax;
    office_fax_is_set = true;
    office_fax_is_modified = true;
  }


  /** 
   * <em>department</em>カラムの値を設定します。 
   *
   * @@param p_department <em>department</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setDepartment( String p_department )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    department = p_department;
    department_is_set = true;
    department_is_modified = true;
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
   * <em>contact_address</em>カラムの値を設定します。 
   *
   * @@param p_contactAddress <em>contact_address</em>カラムの値をあらわす100桁以下のString値 
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
   * <em>family_relationship</em>カラムの値を設定します。 
   *
   * @@param p_familyRelationship <em>family_relationship</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFamilyRelationship( String p_familyRelationship )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    family_relationship = p_familyRelationship;
    family_relationship_is_set = true;
    family_relationship_is_modified = true;
  }


  /** 
   * <em>family_relationship_etc</em>カラムの値を設定します。 
   *
   * @@param p_familyRelationshipEtc <em>family_relationship_etc</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setFamilyRelationshipEtc( String p_familyRelationshipEtc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    family_relationship_etc = p_familyRelationshipEtc;
    family_relationship_etc_is_set = true;
    family_relationship_etc_is_modified = true;
  }


  /** 
   * <em>householder</em>カラムの値を設定します。 
   *
   * @@param p_householder <em>householder</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setHouseholder( String p_householder )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    householder = p_householder;
    householder_is_set = true;
    householder_is_modified = true;
  }


  /** 
   * <em>householder_kana</em>カラムの値を設定します。 
   *
   * @@param p_householderKana <em>householder_kana</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setHouseholderKana( String p_householderKana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    householder_kana = p_householderKana;
    householder_kana_is_set = true;
    householder_kana_is_modified = true;
  }


  /** 
   * <em>householder_occupation_div</em>カラムの値を設定します。 
   *
   * @@param p_householderOccupationDiv <em>householder_occupation_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setHouseholderOccupationDiv( String p_householderOccupationDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    householder_occupation_div = p_householderOccupationDiv;
    householder_occupation_div_is_set = true;
    householder_occupation_div_is_modified = true;
  }


  /** 
   * <em>householder_office</em>カラムの値を設定します。 
   *
   * @@param p_householderOffice <em>householder_office</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setHouseholderOffice( String p_householderOffice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    householder_office = p_householderOffice;
    householder_office_is_set = true;
    householder_office_is_modified = true;
  }


  /** 
   * <em>householder_office_address</em>カラムの値を設定します。 
   *
   * @@param p_householderOfficeAddress <em>householder_office_address</em>カラムの値をあらわす100桁以下のString値 
   */
  public final void setHouseholderOfficeAddress( String p_householderOfficeAddress )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    householder_office_address = p_householderOfficeAddress;
    householder_office_address_is_set = true;
    householder_office_address_is_modified = true;
  }


  /** 
   * <em>householder_department</em>カラムの値を設定します。 
   *
   * @@param p_householderDepartment <em>householder_department</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setHouseholderDepartment( String p_householderDepartment )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    householder_department = p_householderDepartment;
    householder_department_is_set = true;
    householder_department_is_modified = true;
  }


  /** 
   * <em>householder_office_tel</em>カラムの値を設定します。 
   *
   * @@param p_householderOfficeTel <em>householder_office_tel</em>カラムの値をあらわす16桁以下のString値 
   */
  public final void setHouseholderOfficeTel( String p_householderOfficeTel )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    householder_office_tel = p_householderOfficeTel;
    householder_office_tel_is_set = true;
    householder_office_tel_is_modified = true;
  }


  /** 
   * <em>householder_office_fax</em>カラムの値を設定します。 
   *
   * @@param p_householderOfficeFax <em>householder_office_fax</em>カラムの値をあらわす16桁以下のString値 
   */
  public final void setHouseholderOfficeFax( String p_householderOfficeFax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    householder_office_fax = p_householderOfficeFax;
    householder_office_fax_is_set = true;
    householder_office_fax_is_modified = true;
  }


  /** 
   * <em>householder_post</em>カラムの値を設定します。 
   *
   * @@param p_householderPost <em>householder_post</em>カラムの値をあらわす36桁以下のString値 
   */
  public final void setHouseholderPost( String p_householderPost )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    householder_post = p_householderPost;
    householder_post_is_set = true;
    householder_post_is_modified = true;
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
   * <em>transfer_div</em>カラムの値を設定します。 
   *
   * @@param p_transferDiv <em>transfer_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTransferDiv( String p_transferDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_div = p_transferDiv;
    transfer_div_is_set = true;
    transfer_div_is_modified = true;
  }


  /** 
   * <em>fin_institution_code</em>カラムの値を設定します。 
   *
   * @@param p_finInstitutionCode <em>fin_institution_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setFinInstitutionCode( String p_finInstitutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_institution_code = p_finInstitutionCode;
    fin_institution_code_is_set = true;
    fin_institution_code_is_modified = true;
  }


  /** 
   * <em>fin_institution_name</em>カラムの値を設定します。 
   *
   * @@param p_finInstitutionName <em>fin_institution_name</em>カラムの値をあらわす32桁以下のString値 
   */
  public final void setFinInstitutionName( String p_finInstitutionName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_institution_name = p_finInstitutionName;
    fin_institution_name_is_set = true;
    fin_institution_name_is_modified = true;
  }


  /** 
   * <em>fin_branch_code</em>カラムの値を設定します。 
   *
   * @@param p_finBranchCode <em>fin_branch_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setFinBranchCode( String p_finBranchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_branch_code = p_finBranchCode;
    fin_branch_code_is_set = true;
    fin_branch_code_is_modified = true;
  }


  /** 
   * <em>fin_branch_name</em>カラムの値を設定します。 
   *
   * @@param p_finBranchName <em>fin_branch_name</em>カラムの値をあらわす32桁以下のString値 
   */
  public final void setFinBranchName( String p_finBranchName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_branch_name = p_finBranchName;
    fin_branch_name_is_set = true;
    fin_branch_name_is_modified = true;
  }


  /** 
   * <em>fin_save_div</em>カラムの値を設定します。 
   *
   * @@param p_finSaveDiv <em>fin_save_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFinSaveDiv( String p_finSaveDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_save_div = p_finSaveDiv;
    fin_save_div_is_set = true;
    fin_save_div_is_modified = true;
  }


  /** 
   * <em>fin_account_no</em>カラムの値を設定します。 
   *
   * @@param p_finAccountNo <em>fin_account_no</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setFinAccountNo( String p_finAccountNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_account_no = p_finAccountNo;
    fin_account_no_is_set = true;
    fin_account_no_is_modified = true;
  }


  /** 
   * <em>postal_save_code</em>カラムの値を設定します。 
   *
   * @@param p_postalSaveCode <em>postal_save_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setPostalSaveCode( String p_postalSaveCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    postal_save_code = p_postalSaveCode;
    postal_save_code_is_set = true;
    postal_save_code_is_modified = true;
  }


  /** 
   * <em>postal_save_no</em>カラムの値を設定します。 
   *
   * @@param p_postalSaveNo <em>postal_save_no</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setPostalSaveNo( String p_postalSaveNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    postal_save_no = p_postalSaveNo;
    postal_save_no_is_set = true;
    postal_save_no_is_modified = true;
  }


  /** 
   * <em>fin_account_name</em>カラムの値を設定します。 
   *
   * @@param p_finAccountName <em>fin_account_name</em>カラムの値をあらわす60桁以下のString値 
   */
  public final void setFinAccountName( String p_finAccountName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fin_account_name = p_finAccountName;
    fin_account_name_is_set = true;
    fin_account_name_is_modified = true;
  }


  /** 
   * <em>trans_commission</em>カラムの値を設定します。 
   *
   * @@param p_transCommission <em>trans_commission</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTransCommission( String p_transCommission )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trans_commission = p_transCommission;
    trans_commission_is_set = true;
    trans_commission_is_modified = true;
  }


  /** 
   * <em>experience_div_equity</em>カラムの値を設定します。 
   *
   * @@param p_experienceDivEquity <em>experience_div_equity</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExperienceDivEquity( String p_experienceDivEquity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div_equity = p_experienceDivEquity;
    experience_div_equity_is_set = true;
    experience_div_equity_is_modified = true;
  }


  /** 
   * <em>experience_div_margin</em>カラムの値を設定します。 
   *
   * @@param p_experienceDivMargin <em>experience_div_margin</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExperienceDivMargin( String p_experienceDivMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div_margin = p_experienceDivMargin;
    experience_div_margin_is_set = true;
    experience_div_margin_is_modified = true;
  }


  /** 
   * <em>experience_div_bond</em>カラムの値を設定します。 
   *
   * @@param p_experienceDivBond <em>experience_div_bond</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExperienceDivBond( String p_experienceDivBond )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div_bond = p_experienceDivBond;
    experience_div_bond_is_set = true;
    experience_div_bond_is_modified = true;
  }


  /** 
   * <em>experience_div_wt</em>カラムの値を設定します。 
   *
   * @@param p_experienceDivWt <em>experience_div_wt</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExperienceDivWt( String p_experienceDivWt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div_wt = p_experienceDivWt;
    experience_div_wt_is_set = true;
    experience_div_wt_is_modified = true;
  }


  /** 
   * <em>experience_div_fund_sk</em>カラムの値を設定します。 
   *
   * @@param p_experienceDivFundSk <em>experience_div_fund_sk</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExperienceDivFundSk( String p_experienceDivFundSk )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div_fund_sk = p_experienceDivFundSk;
    experience_div_fund_sk_is_set = true;
    experience_div_fund_sk_is_modified = true;
  }


  /** 
   * <em>experience_div_fund_bd</em>カラムの値を設定します。 
   *
   * @@param p_experienceDivFundBd <em>experience_div_fund_bd</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExperienceDivFundBd( String p_experienceDivFundBd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div_fund_bd = p_experienceDivFundBd;
    experience_div_fund_bd_is_set = true;
    experience_div_fund_bd_is_modified = true;
  }


  /** 
   * <em>experience_div_fo</em>カラムの値を設定します。 
   *
   * @@param p_experienceDivFo <em>experience_div_fo</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExperienceDivFo( String p_experienceDivFo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div_fo = p_experienceDivFo;
    experience_div_fo_is_set = true;
    experience_div_fo_is_modified = true;
  }


  /** 
   * <em>experience_div_f_equity</em>カラムの値を設定します。 
   *
   * @@param p_experienceDivFEquity <em>experience_div_f_equity</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExperienceDivFEquity( String p_experienceDivFEquity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div_f_equity = p_experienceDivFEquity;
    experience_div_f_equity_is_set = true;
    experience_div_f_equity_is_modified = true;
  }


  /** 
   * <em>experience_div_etc</em>カラムの値を設定します。 
   *
   * @@param p_experienceDivEtc <em>experience_div_etc</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setExperienceDivEtc( String p_experienceDivEtc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div_etc = p_experienceDivEtc;
    experience_div_etc_is_set = true;
    experience_div_etc_is_modified = true;
  }


  /** 
   * <em>experience_flag_equity</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlagEquity <em>experience_flag_equity</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExperienceFlagEquity( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_experienceFlagEquity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag_equity = p_experienceFlagEquity;
    experience_flag_equity_is_set = true;
    experience_flag_equity_is_modified = true;
  }


  /** 
   * <em>experience_flag_margin</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlagMargin <em>experience_flag_margin</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExperienceFlagMargin( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_experienceFlagMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag_margin = p_experienceFlagMargin;
    experience_flag_margin_is_set = true;
    experience_flag_margin_is_modified = true;
  }


  /** 
   * <em>experience_flag_bond</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlagBond <em>experience_flag_bond</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExperienceFlagBond( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_experienceFlagBond )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag_bond = p_experienceFlagBond;
    experience_flag_bond_is_set = true;
    experience_flag_bond_is_modified = true;
  }


  /** 
   * <em>experience_flag_wt</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlagWt <em>experience_flag_wt</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExperienceFlagWt( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_experienceFlagWt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag_wt = p_experienceFlagWt;
    experience_flag_wt_is_set = true;
    experience_flag_wt_is_modified = true;
  }


  /** 
   * <em>experience_flag_fund_sk</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlagFundSk <em>experience_flag_fund_sk</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExperienceFlagFundSk( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_experienceFlagFundSk )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag_fund_sk = p_experienceFlagFundSk;
    experience_flag_fund_sk_is_set = true;
    experience_flag_fund_sk_is_modified = true;
  }


  /** 
   * <em>experience_flag_fund_bd</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlagFundBd <em>experience_flag_fund_bd</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExperienceFlagFundBd( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_experienceFlagFundBd )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag_fund_bd = p_experienceFlagFundBd;
    experience_flag_fund_bd_is_set = true;
    experience_flag_fund_bd_is_modified = true;
  }


  /** 
   * <em>experience_flag_fo</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlagFo <em>experience_flag_fo</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExperienceFlagFo( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_experienceFlagFo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag_fo = p_experienceFlagFo;
    experience_flag_fo_is_set = true;
    experience_flag_fo_is_modified = true;
  }


  /** 
   * <em>experience_flag_f_equity</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlagFEquity <em>experience_flag_f_equity</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExperienceFlagFEquity( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_experienceFlagFEquity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag_f_equity = p_experienceFlagFEquity;
    experience_flag_f_equity_is_set = true;
    experience_flag_f_equity_is_modified = true;
  }


  /** 
   * <em>experience_flag_etc</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlagEtc <em>experience_flag_etc</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExperienceFlagEtc( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_experienceFlagEtc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag_etc = p_experienceFlagEtc;
    experience_flag_etc_is_set = true;
    experience_flag_etc_is_modified = true;
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
   * <em>interest_flag_equity</em>カラムの値を設定します。 
   *
   * @@param p_interestFlagEquity <em>interest_flag_equity</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setInterestFlagEquity( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_interestFlagEquity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag_equity = p_interestFlagEquity;
    interest_flag_equity_is_set = true;
    interest_flag_equity_is_modified = true;
  }


  /** 
   * <em>interest_flag_ministock</em>カラムの値を設定します。 
   *
   * @@param p_interestFlagMinistock <em>interest_flag_ministock</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setInterestFlagMinistock( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_interestFlagMinistock )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag_ministock = p_interestFlagMinistock;
    interest_flag_ministock_is_set = true;
    interest_flag_ministock_is_modified = true;
  }


  /** 
   * <em>interest_flag_margin</em>カラムの値を設定します。 
   *
   * @@param p_interestFlagMargin <em>interest_flag_margin</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setInterestFlagMargin( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_interestFlagMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag_margin = p_interestFlagMargin;
    interest_flag_margin_is_set = true;
    interest_flag_margin_is_modified = true;
  }


  /** 
   * <em>interest_flag_bond</em>カラムの値を設定します。 
   *
   * @@param p_interestFlagBond <em>interest_flag_bond</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setInterestFlagBond( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_interestFlagBond )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag_bond = p_interestFlagBond;
    interest_flag_bond_is_set = true;
    interest_flag_bond_is_modified = true;
  }


  /** 
   * <em>interest_flag_fund</em>カラムの値を設定します。 
   *
   * @@param p_interestFlagFund <em>interest_flag_fund</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setInterestFlagFund( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_interestFlagFund )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag_fund = p_interestFlagFund;
    interest_flag_fund_is_set = true;
    interest_flag_fund_is_modified = true;
  }


  /** 
   * <em>interest_flag_fo</em>カラムの値を設定します。 
   *
   * @@param p_interestFlagFo <em>interest_flag_fo</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setInterestFlagFo( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_interestFlagFo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag_fo = p_interestFlagFo;
    interest_flag_fo_is_set = true;
    interest_flag_fo_is_modified = true;
  }


  /** 
   * <em>interest_flag_f_equity</em>カラムの値を設定します。 
   *
   * @@param p_interestFlagFEquity <em>interest_flag_f_equity</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setInterestFlagFEquity( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_interestFlagFEquity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag_f_equity = p_interestFlagFEquity;
    interest_flag_f_equity_is_set = true;
    interest_flag_f_equity_is_modified = true;
  }


  /** 
   * <em>interest_flag_etc</em>カラムの値を設定します。 
   *
   * @@param p_interestFlagEtc <em>interest_flag_etc</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setInterestFlagEtc( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_interestFlagEtc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag_etc = p_interestFlagEtc;
    interest_flag_etc_is_set = true;
    interest_flag_etc_is_modified = true;
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
   * <em>annual_income_div</em>カラムの値を設定します。 
   *
   * @@param p_annualIncomeDiv <em>annual_income_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAnnualIncomeDiv( String p_annualIncomeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    annual_income_div = p_annualIncomeDiv;
    annual_income_div_is_set = true;
    annual_income_div_is_modified = true;
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
   * <em>asset_value_div</em>カラムの値を設定します。 
   *
   * @@param p_assetValueDiv <em>asset_value_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAssetValueDiv( String p_assetValueDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    asset_value_div = p_assetValueDiv;
    asset_value_div_is_set = true;
    asset_value_div_is_modified = true;
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
   * <em>fund_budget_amount_div</em>カラムの値を設定します。 
   *
   * @@param p_fundBudgetAmountDiv <em>fund_budget_amount_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFundBudgetAmountDiv( String p_fundBudgetAmountDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fund_budget_amount_div = p_fundBudgetAmountDiv;
    fund_budget_amount_div_is_set = true;
    fund_budget_amount_div_is_modified = true;
  }


  /** 
   * <em>fund_budget_div</em>カラムの値を設定します。 
   *
   * @@param p_fundBudgetDiv <em>fund_budget_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFundBudgetDiv( String p_fundBudgetDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fund_budget_div = p_fundBudgetDiv;
    fund_budget_div_is_set = true;
    fund_budget_div_is_modified = true;
  }


  /** 
   * <em>fund_budget_etc</em>カラムの値を設定します。 
   *
   * @@param p_fundBudgetEtc <em>fund_budget_etc</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setFundBudgetEtc( String p_fundBudgetEtc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fund_budget_etc = p_fundBudgetEtc;
    fund_budget_etc_is_set = true;
    fund_budget_etc_is_modified = true;
  }


  /** 
   * <em>id_confirm_flag</em>カラムの値を設定します。 
   *
   * @@param p_idConfirmFlag <em>id_confirm_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setIdConfirmFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_idConfirmFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    id_confirm_flag = p_idConfirmFlag;
    id_confirm_flag_is_set = true;
    id_confirm_flag_is_modified = true;
  }


  /** 
   * <em>id_confirm_doc_div</em>カラムの値を設定します。 
   *
   * @@param p_idConfirmDocDiv <em>id_confirm_doc_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setIdConfirmDocDiv( String p_idConfirmDocDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    id_confirm_doc_div = p_idConfirmDocDiv;
    id_confirm_doc_div_is_set = true;
    id_confirm_doc_div_is_modified = true;
  }


  /** 
   * <em>id_confirm_doc_etc</em>カラムの値を設定します。 
   *
   * @@param p_idConfirmDocEtc <em>id_confirm_doc_etc</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setIdConfirmDocEtc( String p_idConfirmDocEtc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    id_confirm_doc_etc = p_idConfirmDocEtc;
    id_confirm_doc_etc_is_set = true;
    id_confirm_doc_etc_is_modified = true;
  }


  /** 
   * <em>special_acc</em>カラムの値を設定します。 
   *
   * @@param p_specialAcc <em>special_acc</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSpecialAcc( String p_specialAcc )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_acc = p_specialAcc;
    special_acc_is_set = true;
    special_acc_is_modified = true;
  }


  /** 
   * <em>special_acc_margin</em>カラムの値を設定します。 
   *
   * @@param p_specialAccMargin <em>special_acc_margin</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSpecialAccMargin( String p_specialAccMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_acc_margin = p_specialAccMargin;
    special_acc_margin_is_set = true;
    special_acc_margin_is_modified = true;
  }


  /** 
   * <em>insider_flag</em>カラムの値を設定します。 
   *
   * @@param p_insiderFlag <em>insider_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setInsiderFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_insiderFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    insider_flag = p_insiderFlag;
    insider_flag_is_set = true;
    insider_flag_is_modified = true;
  }


  /** 
   * <em>product_name</em>カラムの値を設定します。 
   *
   * @@param p_productName <em>product_name</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setProductName( String p_productName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_name = p_productName;
    product_name_is_set = true;
    product_name_is_modified = true;
  }


  /** 
   * <em>send_zip_code</em>カラムの値を設定します。 
   *
   * @@param p_sendZipCode <em>send_zip_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setSendZipCode( String p_sendZipCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    send_zip_code = p_sendZipCode;
    send_zip_code_is_set = true;
    send_zip_code_is_modified = true;
  }


  /** 
   * <em>send_address_line1</em>カラムの値を設定します。 
   *
   * @@param p_sendAddressLine1 <em>send_address_line1</em>カラムの値をあらわす34桁以下のString値 
   */
  public final void setSendAddressLine1( String p_sendAddressLine1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    send_address_line1 = p_sendAddressLine1;
    send_address_line1_is_set = true;
    send_address_line1_is_modified = true;
  }


  /** 
   * <em>send_address_line2</em>カラムの値を設定します。 
   *
   * @@param p_sendAddressLine2 <em>send_address_line2</em>カラムの値をあらわす34桁以下のString値 
   */
  public final void setSendAddressLine2( String p_sendAddressLine2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    send_address_line2 = p_sendAddressLine2;
    send_address_line2_is_set = true;
    send_address_line2_is_modified = true;
  }


  /** 
   * <em>send_address_line3</em>カラムの値を設定します。 
   *
   * @@param p_sendAddressLine3 <em>send_address_line3</em>カラムの値をあらわす34桁以下のString値 
   */
  public final void setSendAddressLine3( String p_sendAddressLine3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    send_address_line3 = p_sendAddressLine3;
    send_address_line3_is_set = true;
    send_address_line3_is_modified = true;
  }


  /** 
   * <em>ext_item_div1</em>カラムの値を設定します。 
   *
   * @@param p_extItemDiv1 <em>ext_item_div1</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExtItemDiv1( String p_extItemDiv1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_div1 = p_extItemDiv1;
    ext_item_div1_is_set = true;
    ext_item_div1_is_modified = true;
  }


  /** 
   * <em>ext_item_div2</em>カラムの値を設定します。 
   *
   * @@param p_extItemDiv2 <em>ext_item_div2</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExtItemDiv2( String p_extItemDiv2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_div2 = p_extItemDiv2;
    ext_item_div2_is_set = true;
    ext_item_div2_is_modified = true;
  }


  /** 
   * <em>ext_item_div3</em>カラムの値を設定します。 
   *
   * @@param p_extItemDiv3 <em>ext_item_div3</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExtItemDiv3( String p_extItemDiv3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_div3 = p_extItemDiv3;
    ext_item_div3_is_set = true;
    ext_item_div3_is_modified = true;
  }


  /** 
   * <em>ext_item_div4</em>カラムの値を設定します。 
   *
   * @@param p_extItemDiv4 <em>ext_item_div4</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExtItemDiv4( String p_extItemDiv4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_div4 = p_extItemDiv4;
    ext_item_div4_is_set = true;
    ext_item_div4_is_modified = true;
  }


  /** 
   * <em>ext_item_div5</em>カラムの値を設定します。 
   *
   * @@param p_extItemDiv5 <em>ext_item_div5</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExtItemDiv5( String p_extItemDiv5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_div5 = p_extItemDiv5;
    ext_item_div5_is_set = true;
    ext_item_div5_is_modified = true;
  }


  /** 
   * <em>ext_item_div6</em>カラムの値を設定します。 
   *
   * @@param p_extItemDiv6 <em>ext_item_div6</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExtItemDiv6( String p_extItemDiv6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_div6 = p_extItemDiv6;
    ext_item_div6_is_set = true;
    ext_item_div6_is_modified = true;
  }


  /** 
   * <em>ext_item_div7</em>カラムの値を設定します。 
   *
   * @@param p_extItemDiv7 <em>ext_item_div7</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExtItemDiv7( String p_extItemDiv7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_div7 = p_extItemDiv7;
    ext_item_div7_is_set = true;
    ext_item_div7_is_modified = true;
  }


  /** 
   * <em>ext_item_div8</em>カラムの値を設定します。 
   *
   * @@param p_extItemDiv8 <em>ext_item_div8</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExtItemDiv8( String p_extItemDiv8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_div8 = p_extItemDiv8;
    ext_item_div8_is_set = true;
    ext_item_div8_is_modified = true;
  }


  /** 
   * <em>ext_item_div9</em>カラムの値を設定します。 
   *
   * @@param p_extItemDiv9 <em>ext_item_div9</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExtItemDiv9( String p_extItemDiv9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_div9 = p_extItemDiv9;
    ext_item_div9_is_set = true;
    ext_item_div9_is_modified = true;
  }


  /** 
   * <em>ext_item_div10</em>カラムの値を設定します。 
   *
   * @@param p_extItemDiv10 <em>ext_item_div10</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExtItemDiv10( String p_extItemDiv10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_div10 = p_extItemDiv10;
    ext_item_div10_is_set = true;
    ext_item_div10_is_modified = true;
  }


  /** 
   * <em>ext_item_flag1</em>カラムの値を設定します。 
   *
   * @@param p_extItemFlag1 <em>ext_item_flag1</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExtItemFlag1( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_extItemFlag1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_flag1 = p_extItemFlag1;
    ext_item_flag1_is_set = true;
    ext_item_flag1_is_modified = true;
  }


  /** 
   * <em>ext_item_flag2</em>カラムの値を設定します。 
   *
   * @@param p_extItemFlag2 <em>ext_item_flag2</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExtItemFlag2( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_extItemFlag2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_flag2 = p_extItemFlag2;
    ext_item_flag2_is_set = true;
    ext_item_flag2_is_modified = true;
  }


  /** 
   * <em>ext_item_flag3</em>カラムの値を設定します。 
   *
   * @@param p_extItemFlag3 <em>ext_item_flag3</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExtItemFlag3( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_extItemFlag3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_flag3 = p_extItemFlag3;
    ext_item_flag3_is_set = true;
    ext_item_flag3_is_modified = true;
  }


  /** 
   * <em>ext_item_flag4</em>カラムの値を設定します。 
   *
   * @@param p_extItemFlag4 <em>ext_item_flag4</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExtItemFlag4( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_extItemFlag4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_flag4 = p_extItemFlag4;
    ext_item_flag4_is_set = true;
    ext_item_flag4_is_modified = true;
  }


  /** 
   * <em>ext_item_flag5</em>カラムの値を設定します。 
   *
   * @@param p_extItemFlag5 <em>ext_item_flag5</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExtItemFlag5( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_extItemFlag5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_flag5 = p_extItemFlag5;
    ext_item_flag5_is_set = true;
    ext_item_flag5_is_modified = true;
  }


  /** 
   * <em>ext_item_flag6</em>カラムの値を設定します。 
   *
   * @@param p_extItemFlag6 <em>ext_item_flag6</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExtItemFlag6( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_extItemFlag6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_flag6 = p_extItemFlag6;
    ext_item_flag6_is_set = true;
    ext_item_flag6_is_modified = true;
  }


  /** 
   * <em>ext_item_flag7</em>カラムの値を設定します。 
   *
   * @@param p_extItemFlag7 <em>ext_item_flag7</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExtItemFlag7( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_extItemFlag7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_flag7 = p_extItemFlag7;
    ext_item_flag7_is_set = true;
    ext_item_flag7_is_modified = true;
  }


  /** 
   * <em>ext_item_flag8</em>カラムの値を設定します。 
   *
   * @@param p_extItemFlag8 <em>ext_item_flag8</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExtItemFlag8( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_extItemFlag8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_flag8 = p_extItemFlag8;
    ext_item_flag8_is_set = true;
    ext_item_flag8_is_modified = true;
  }


  /** 
   * <em>ext_item_flag9</em>カラムの値を設定します。 
   *
   * @@param p_extItemFlag9 <em>ext_item_flag9</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExtItemFlag9( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_extItemFlag9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_flag9 = p_extItemFlag9;
    ext_item_flag9_is_set = true;
    ext_item_flag9_is_modified = true;
  }


  /** 
   * <em>ext_item_flag10</em>カラムの値を設定します。 
   *
   * @@param p_extItemFlag10 <em>ext_item_flag10</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExtItemFlag10( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_extItemFlag10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_flag10 = p_extItemFlag10;
    ext_item_flag10_is_set = true;
    ext_item_flag10_is_modified = true;
  }


  /** 
   * <em>ext_item_text1</em>カラムの値を設定します。 
   *
   * @@param p_extItemText1 <em>ext_item_text1</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtItemText1( String p_extItemText1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_text1 = p_extItemText1;
    ext_item_text1_is_set = true;
    ext_item_text1_is_modified = true;
  }


  /** 
   * <em>ext_item_text2</em>カラムの値を設定します。 
   *
   * @@param p_extItemText2 <em>ext_item_text2</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtItemText2( String p_extItemText2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_text2 = p_extItemText2;
    ext_item_text2_is_set = true;
    ext_item_text2_is_modified = true;
  }


  /** 
   * <em>ext_item_text3</em>カラムの値を設定します。 
   *
   * @@param p_extItemText3 <em>ext_item_text3</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtItemText3( String p_extItemText3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_text3 = p_extItemText3;
    ext_item_text3_is_set = true;
    ext_item_text3_is_modified = true;
  }


  /** 
   * <em>ext_item_text4</em>カラムの値を設定します。 
   *
   * @@param p_extItemText4 <em>ext_item_text4</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtItemText4( String p_extItemText4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_text4 = p_extItemText4;
    ext_item_text4_is_set = true;
    ext_item_text4_is_modified = true;
  }


  /** 
   * <em>ext_item_text5</em>カラムの値を設定します。 
   *
   * @@param p_extItemText5 <em>ext_item_text5</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtItemText5( String p_extItemText5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_text5 = p_extItemText5;
    ext_item_text5_is_set = true;
    ext_item_text5_is_modified = true;
  }


  /** 
   * <em>ext_item_text6</em>カラムの値を設定します。 
   *
   * @@param p_extItemText6 <em>ext_item_text6</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtItemText6( String p_extItemText6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_text6 = p_extItemText6;
    ext_item_text6_is_set = true;
    ext_item_text6_is_modified = true;
  }


  /** 
   * <em>ext_item_text7</em>カラムの値を設定します。 
   *
   * @@param p_extItemText7 <em>ext_item_text7</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtItemText7( String p_extItemText7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_text7 = p_extItemText7;
    ext_item_text7_is_set = true;
    ext_item_text7_is_modified = true;
  }


  /** 
   * <em>ext_item_text8</em>カラムの値を設定します。 
   *
   * @@param p_extItemText8 <em>ext_item_text8</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtItemText8( String p_extItemText8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_text8 = p_extItemText8;
    ext_item_text8_is_set = true;
    ext_item_text8_is_modified = true;
  }


  /** 
   * <em>ext_item_text9</em>カラムの値を設定します。 
   *
   * @@param p_extItemText9 <em>ext_item_text9</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtItemText9( String p_extItemText9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_text9 = p_extItemText9;
    ext_item_text9_is_set = true;
    ext_item_text9_is_modified = true;
  }


  /** 
   * <em>ext_item_text10</em>カラムの値を設定します。 
   *
   * @@param p_extItemText10 <em>ext_item_text10</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setExtItemText10( String p_extItemText10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_text10 = p_extItemText10;
    ext_item_text10_is_set = true;
    ext_item_text10_is_modified = true;
  }


  /** 
   * <em>last_updater</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdater <em>last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
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
   * <em>creator</em>カラムの値を設定します。 
   *
   * @@param p_creator <em>creator</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setCreator( String p_creator )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    creator = p_creator;
    creator_is_set = true;
    creator_is_modified = true;
  }


  /** 
   * <em>exclusive_use_account_no</em>カラムの値を設定します。 
   *
   * @@param p_exclusiveUseAccountNo <em>exclusive_use_account_no</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setExclusiveUseAccountNo( String p_exclusiveUseAccountNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exclusive_use_account_no = p_exclusiveUseAccountNo;
    exclusive_use_account_no_is_set = true;
    exclusive_use_account_no_is_modified = true;
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
   * <em>real_name_voucher_div</em>カラムの値を設定します。 
   *
   * @@param p_realNameVoucherDiv <em>real_name_voucher_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRealNameVoucherDiv( String p_realNameVoucherDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    real_name_voucher_div = p_realNameVoucherDiv;
    real_name_voucher_div_is_set = true;
    real_name_voucher_div_is_modified = true;
  }


  /** 
   * <em>real_name1</em>カラムの値を設定します。 
   *
   * @@param p_realName1 <em>real_name1</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setRealName1( String p_realName1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    real_name1 = p_realName1;
    real_name1_is_set = true;
    real_name1_is_modified = true;
  }


  /** 
   * <em>real_name2</em>カラムの値を設定します。 
   *
   * @@param p_realName2 <em>real_name2</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setRealName2( String p_realName2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    real_name2 = p_realName2;
    real_name2_is_set = true;
    real_name2_is_modified = true;
  }


  /** 
   * <em>insider_voucher_div</em>カラムの値を設定します。 
   *
   * @@param p_insiderVoucherDiv <em>insider_voucher_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setInsiderVoucherDiv( String p_insiderVoucherDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    insider_voucher_div = p_insiderVoucherDiv;
    insider_voucher_div_is_set = true;
    insider_voucher_div_is_modified = true;
  }


  /** 
   * <em>insider_product_code</em>カラムの値を設定します。 
   *
   * @@param p_insiderProductCode <em>insider_product_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setInsiderProductCode( String p_insiderProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    insider_product_code = p_insiderProductCode;
    insider_product_code_is_set = true;
    insider_product_code_is_modified = true;
  }


  /** 
   * <em>insider_relation_div</em>カラムの値を設定します。 
   *
   * @@param p_insiderRelationDiv <em>insider_relation_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setInsiderRelationDiv( String p_insiderRelationDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    insider_relation_div = p_insiderRelationDiv;
    insider_relation_div_is_set = true;
    insider_relation_div_is_modified = true;
  }


  /** 
   * <em>insider_officer_name</em>カラムの値を設定します。 
   *
   * @@param p_insiderOfficerName <em>insider_officer_name</em>カラムの値をあらわす18桁以下のString値 
   */
  public final void setInsiderOfficerName( String p_insiderOfficerName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    insider_officer_name = p_insiderOfficerName;
    insider_officer_name_is_set = true;
    insider_officer_name_is_modified = true;
  }


  /** 
   * <em>insider_post_code</em>カラムの値を設定します。 
   *
   * @@param p_insiderPostCode <em>insider_post_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setInsiderPostCode( String p_insiderPostCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    insider_post_code = p_insiderPostCode;
    insider_post_code_is_set = true;
    insider_post_code_is_modified = true;
  }


  /** 
   * <em>insider_post_name</em>カラムの値を設定します。 
   *
   * @@param p_insiderPostName <em>insider_post_name</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setInsiderPostName( String p_insiderPostName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    insider_post_name = p_insiderPostName;
    insider_post_name_is_set = true;
    insider_post_name_is_modified = true;
  }


  /** 
   * <em>gp_voucher_div</em>カラムの値を設定します。 
   *
   * @@param p_gpVoucherDiv <em>gp_voucher_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGpVoucherDiv( String p_gpVoucherDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_voucher_div = p_gpVoucherDiv;
    gp_voucher_div_is_set = true;
    gp_voucher_div_is_modified = true;
  }


  /** 
   * <em>gp_course</em>カラムの値を設定します。 
   *
   * @@param p_gpCourse <em>gp_course</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGpCourse( String p_gpCourse )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_course = p_gpCourse;
    gp_course_is_set = true;
    gp_course_is_modified = true;
  }


  /** 
   * <em>gp_plan</em>カラムの値を設定します。 
   *
   * @@param p_gpPlan <em>gp_plan</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGpPlan( String p_gpPlan )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_plan = p_gpPlan;
    gp_plan_is_set = true;
    gp_plan_is_modified = true;
  }


  /** 
   * <em>gp_target_figure</em>カラムの値を設定します。 
   *
   * @@param p_gpTargetFigure <em>gp_target_figure</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setGpTargetFigure( String p_gpTargetFigure )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_target_figure = p_gpTargetFigure;
    gp_target_figure_is_set = true;
    gp_target_figure_is_modified = true;
  }


  /** 
   * <em>gp_target_year</em>カラムの値を設定します。 
   *
   * @@param p_gpTargetYear <em>gp_target_year</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setGpTargetYear( String p_gpTargetYear )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_target_year = p_gpTargetYear;
    gp_target_year_is_set = true;
    gp_target_year_is_modified = true;
  }


  /** 
   * <em>gp_target_month</em>カラムの値を設定します。 
   *
   * @@param p_gpTargetMonth <em>gp_target_month</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setGpTargetMonth( String p_gpTargetMonth )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_target_month = p_gpTargetMonth;
    gp_target_month_is_set = true;
    gp_target_month_is_modified = true;
  }


  /** 
   * <em>gp_installment_figure</em>カラムの値を設定します。 
   *
   * @@param p_gpInstallmentFigure <em>gp_installment_figure</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setGpInstallmentFigure( String p_gpInstallmentFigure )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_installment_figure = p_gpInstallmentFigure;
    gp_installment_figure_is_set = true;
    gp_installment_figure_is_modified = true;
  }


  /** 
   * <em>gp_deposit_cycle</em>カラムの値を設定します。 
   *
   * @@param p_gpDepositCycle <em>gp_deposit_cycle</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGpDepositCycle( String p_gpDepositCycle )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_deposit_cycle = p_gpDepositCycle;
    gp_deposit_cycle_is_set = true;
    gp_deposit_cycle_is_modified = true;
  }


  /** 
   * <em>gp_payment_root</em>カラムの値を設定します。 
   *
   * @@param p_gpPaymentRoot <em>gp_payment_root</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGpPaymentRoot( String p_gpPaymentRoot )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_payment_root = p_gpPaymentRoot;
    gp_payment_root_is_set = true;
    gp_payment_root_is_modified = true;
  }


  /** 
   * <em>gp_reinvest_div</em>カラムの値を設定します。 
   *
   * @@param p_gpReinvestDiv <em>gp_reinvest_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGpReinvestDiv( String p_gpReinvestDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_reinvest_div = p_gpReinvestDiv;
    gp_reinvest_div_is_set = true;
    gp_reinvest_div_is_modified = true;
  }


  /** 
   * <em>gp_tax_div</em>カラムの値を設定します。 
   *
   * @@param p_gpTaxDiv <em>gp_tax_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGpTaxDiv( String p_gpTaxDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_tax_div = p_gpTaxDiv;
    gp_tax_div_is_set = true;
    gp_tax_div_is_modified = true;
  }


  /** 
   * <em>gp_taxfree_limit</em>カラムの値を設定します。 
   *
   * @@param p_gpTaxfreeLimit <em>gp_taxfree_limit</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setGpTaxfreeLimit( String p_gpTaxfreeLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_taxfree_limit = p_gpTaxfreeLimit;
    gp_taxfree_limit_is_set = true;
    gp_taxfree_limit_is_modified = true;
  }


  /** 
   * <em>gp_special_taxfree_limit</em>カラムの値を設定します。 
   *
   * @@param p_gpSpecialTaxfreeLimit <em>gp_special_taxfree_limit</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setGpSpecialTaxfreeLimit( String p_gpSpecialTaxfreeLimit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_special_taxfree_limit = p_gpSpecialTaxfreeLimit;
    gp_special_taxfree_limit_is_set = true;
    gp_special_taxfree_limit_is_modified = true;
  }


  /** 
   * <em>gp_subscr_summary</em>カラムの値を設定します。 
   *
   * @@param p_gpSubscrSummary <em>gp_subscr_summary</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGpSubscrSummary( String p_gpSubscrSummary )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_subscr_summary = p_gpSubscrSummary;
    gp_subscr_summary_is_set = true;
    gp_subscr_summary_is_modified = true;
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
   * <em>gp_mortgage_customer</em>カラムの値を設定します。 
   *
   * @@param p_gpMortgageCustomer <em>gp_mortgage_customer</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGpMortgageCustomer( String p_gpMortgageCustomer )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_mortgage_customer = p_gpMortgageCustomer;
    gp_mortgage_customer_is_set = true;
    gp_mortgage_customer_is_modified = true;
  }


  /** 
   * <em>gp_mix_customer</em>カラムの値を設定します。 
   *
   * @@param p_gpMixCustomer <em>gp_mix_customer</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGpMixCustomer( String p_gpMixCustomer )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_mix_customer = p_gpMixCustomer;
    gp_mix_customer_is_set = true;
    gp_mix_customer_is_modified = true;
  }


  /** 
   * <em>gp_contract</em>カラムの値を設定します。 
   *
   * @@param p_gpContract <em>gp_contract</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGpContract( String p_gpContract )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_contract = p_gpContract;
    gp_contract_is_set = true;
    gp_contract_is_modified = true;
  }


  /** 
   * <em>stk_voucher_div</em>カラムの値を設定します。 
   *
   * @@param p_stkVoucherDiv <em>stk_voucher_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStkVoucherDiv( String p_stkVoucherDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stk_voucher_div = p_stkVoucherDiv;
    stk_voucher_div_is_set = true;
    stk_voucher_div_is_modified = true;
  }


  /** 
   * <em>stk_taxation_tran_div</em>カラムの値を設定します。 
   *
   * @@param p_stkTaxationTranDiv <em>stk_taxation_tran_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStkTaxationTranDiv( String p_stkTaxationTranDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stk_taxation_tran_div = p_stkTaxationTranDiv;
    stk_taxation_tran_div_is_set = true;
    stk_taxation_tran_div_is_modified = true;
  }


  /** 
   * <em>stk_address_line_kana</em>カラムの値を設定します。 
   *
   * @@param p_stkAddressLineKana <em>stk_address_line_kana</em>カラムの値をあらわす70桁以下のString値 
   */
  public final void setStkAddressLineKana( String p_stkAddressLineKana )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stk_address_line_kana = p_stkAddressLineKana;
    stk_address_line_kana_is_set = true;
    stk_address_line_kana_is_modified = true;
  }


  /** 
   * <em>stk_transfer_div</em>カラムの値を設定します。 
   *
   * @@param p_stkTransferDiv <em>stk_transfer_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStkTransferDiv( String p_stkTransferDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stk_transfer_div = p_stkTransferDiv;
    stk_transfer_div_is_set = true;
    stk_transfer_div_is_modified = true;
  }


  /** 
   * <em>stk_fin_institution_code</em>カラムの値を設定します。 
   *
   * @@param p_stkFinInstitutionCode <em>stk_fin_institution_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setStkFinInstitutionCode( String p_stkFinInstitutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stk_fin_institution_code = p_stkFinInstitutionCode;
    stk_fin_institution_code_is_set = true;
    stk_fin_institution_code_is_modified = true;
  }


  /** 
   * <em>stk_fin_branch_code</em>カラムの値を設定します。 
   *
   * @@param p_stkFinBranchCode <em>stk_fin_branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setStkFinBranchCode( String p_stkFinBranchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stk_fin_branch_code = p_stkFinBranchCode;
    stk_fin_branch_code_is_set = true;
    stk_fin_branch_code_is_modified = true;
  }


  /** 
   * <em>stk_fin_save_div</em>カラムの値を設定します。 
   *
   * @@param p_stkFinSaveDiv <em>stk_fin_save_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStkFinSaveDiv( String p_stkFinSaveDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stk_fin_save_div = p_stkFinSaveDiv;
    stk_fin_save_div_is_set = true;
    stk_fin_save_div_is_modified = true;
  }


  /** 
   * <em>stk_fin_account_no</em>カラムの値を設定します。 
   *
   * @@param p_stkFinAccountNo <em>stk_fin_account_no</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setStkFinAccountNo( String p_stkFinAccountNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stk_fin_account_no = p_stkFinAccountNo;
    stk_fin_account_no_is_set = true;
    stk_fin_account_no_is_modified = true;
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
   * <em>ext_item_div11</em>カラムの値を設定します。 
   *
   * @@param p_extItemDiv11 <em>ext_item_div11</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setExtItemDiv11( String p_extItemDiv11 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_div11 = p_extItemDiv11;
    ext_item_div11_is_set = true;
    ext_item_div11_is_modified = true;
  }


  /** 
   * <em>ext_item_div12</em>カラムの値を設定します。 
   *
   * @@param p_extItemDiv12 <em>ext_item_div12</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setExtItemDiv12( String p_extItemDiv12 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_div12 = p_extItemDiv12;
    ext_item_div12_is_set = true;
    ext_item_div12_is_modified = true;
  }


  /** 
   * <em>ext_item_div13</em>カラムの値を設定します。 
   *
   * @@param p_extItemDiv13 <em>ext_item_div13</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setExtItemDiv13( String p_extItemDiv13 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_div13 = p_extItemDiv13;
    ext_item_div13_is_set = true;
    ext_item_div13_is_modified = true;
  }


  /** 
   * <em>ext_item_div14</em>カラムの値を設定します。 
   *
   * @@param p_extItemDiv14 <em>ext_item_div14</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setExtItemDiv14( String p_extItemDiv14 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_div14 = p_extItemDiv14;
    ext_item_div14_is_set = true;
    ext_item_div14_is_modified = true;
  }


  /** 
   * <em>ext_item_div15</em>カラムの値を設定します。 
   *
   * @@param p_extItemDiv15 <em>ext_item_div15</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setExtItemDiv15( String p_extItemDiv15 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ext_item_div15 = p_extItemDiv15;
    ext_item_div15_is_set = true;
    ext_item_div15_is_modified = true;
  }


  /** 
   * <em>foreign_account_no</em>カラムの値を設定します。 
   *
   * @@param p_foreignAccountNo <em>foreign_account_no</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setForeignAccountNo( String p_foreignAccountNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_account_no = p_foreignAccountNo;
    foreign_account_no_is_set = true;
    foreign_account_no_is_modified = true;
  }


  /** 
   * <em>foreign_account_name</em>カラムの値を設定します。 
   *
   * @@param p_foreignAccountName <em>foreign_account_name</em>カラムの値をあらわす60桁以下のString値 
   */
  public final void setForeignAccountName( String p_foreignAccountName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_account_name = p_foreignAccountName;
    foreign_account_name_is_set = true;
    foreign_account_name_is_modified = true;
  }


  /** 
   * <em>foreign_account_name_eng</em>カラムの値を設定します。 
   *
   * @@param p_foreignAccountNameEng <em>foreign_account_name_eng</em>カラムの値をあらわす30桁以下のString値 
   */
  public final void setForeignAccountNameEng( String p_foreignAccountNameEng )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_account_name_eng = p_foreignAccountNameEng;
    foreign_account_name_eng_is_set = true;
    foreign_account_name_eng_is_modified = true;
  }


  /** 
   * <em>foreign_save_div</em>カラムの値を設定します。 
   *
   * @@param p_foreignSaveDiv <em>foreign_save_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setForeignSaveDiv( String p_foreignSaveDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_save_div = p_foreignSaveDiv;
    foreign_save_div_is_set = true;
    foreign_save_div_is_modified = true;
  }


  /** 
   * <em>delete_flag</em>カラムの値を設定します。 
   *
   * @@param p_deleteFlag <em>delete_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setDeleteFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_deleteFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delete_flag = p_deleteFlag;
    delete_flag_is_set = true;
    delete_flag_is_modified = true;
  }


  /** 
   * <em>delete_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_deleteTimestamp <em>delete_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setDeleteTimestamp( java.sql.Timestamp p_deleteTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delete_timestamp = p_deleteTimestamp;
    delete_timestamp_is_set = true;
    delete_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>delete_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setDeleteTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    delete_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    delete_timestamp_is_set = true;
    delete_timestamp_is_modified = true;
  }


  /** 
   * <em>print_flag</em>カラムの値を設定します。 
   *
   * @@param p_printFlag <em>print_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPrintFlag( String p_printFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    print_flag = p_printFlag;
    print_flag_is_set = true;
    print_flag_is_modified = true;
  }


  /** 
   * <em>receipt_flag</em>カラムの値を設定します。 
   *
   * @@param p_receiptFlag <em>receipt_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setReceiptFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_receiptFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    receipt_flag = p_receiptFlag;
    receipt_flag_is_set = true;
    receipt_flag_is_modified = true;
  }


  /** 
   * <em>agreement_flag</em>カラムの値を設定します。 
   *
   * @@param p_agreementFlag <em>agreement_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setAgreementFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_agreementFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agreement_flag = p_agreementFlag;
    agreement_flag_is_set = true;
    agreement_flag_is_modified = true;
  }


  /** 
   * <em>foreign_flag</em>カラムの値を設定します。 
   *
   * @@param p_foreignFlag <em>foreign_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setForeignFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_foreignFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_flag = p_foreignFlag;
    foreign_flag_is_set = true;
    foreign_flag_is_modified = true;
  }


  /** 
   * <em>agency_acc_name_kana1</em>カラムの値を設定します。 
   *
   * @@param p_agencyAccNameKana1 <em>agency_acc_name_kana1</em>カラムの値をあらわす60桁以下のString値 
   */
  public final void setAgencyAccNameKana1( String p_agencyAccNameKana1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agency_acc_name_kana1 = p_agencyAccNameKana1;
    agency_acc_name_kana1_is_set = true;
    agency_acc_name_kana1_is_modified = true;
  }


  /** 
   * <em>agency_acc_name_kana2</em>カラムの値を設定します。 
   *
   * @@param p_agencyAccNameKana2 <em>agency_acc_name_kana2</em>カラムの値をあらわす60桁以下のString値 
   */
  public final void setAgencyAccNameKana2( String p_agencyAccNameKana2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agency_acc_name_kana2 = p_agencyAccNameKana2;
    agency_acc_name_kana2_is_set = true;
    agency_acc_name_kana2_is_modified = true;
  }


  /** 
   * <em>agency_acc_name1</em>カラムの値を設定します。 
   *
   * @@param p_agencyAccName1 <em>agency_acc_name1</em>カラムの値をあらわす60桁以下のString値 
   */
  public final void setAgencyAccName1( String p_agencyAccName1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agency_acc_name1 = p_agencyAccName1;
    agency_acc_name1_is_set = true;
    agency_acc_name1_is_modified = true;
  }


  /** 
   * <em>agency_acc_name2</em>カラムの値を設定します。 
   *
   * @@param p_agencyAccName2 <em>agency_acc_name2</em>カラムの値をあらわす60桁以下のString値 
   */
  public final void setAgencyAccName2( String p_agencyAccName2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agency_acc_name2 = p_agencyAccName2;
    agency_acc_name2_is_set = true;
    agency_acc_name2_is_modified = true;
  }


  /** 
   * <em>agency_address_line1</em>カラムの値を設定します。 
   *
   * @@param p_agencyAddressLine1 <em>agency_address_line1</em>カラムの値をあらわす48桁以下のString値 
   */
  public final void setAgencyAddressLine1( String p_agencyAddressLine1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agency_address_line1 = p_agencyAddressLine1;
    agency_address_line1_is_set = true;
    agency_address_line1_is_modified = true;
  }


  /** 
   * <em>agency_address_line2</em>カラムの値を設定します。 
   *
   * @@param p_agencyAddressLine2 <em>agency_address_line2</em>カラムの値をあらわす48桁以下のString値 
   */
  public final void setAgencyAddressLine2( String p_agencyAddressLine2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agency_address_line2 = p_agencyAddressLine2;
    agency_address_line2_is_set = true;
    agency_address_line2_is_modified = true;
  }


  /** 
   * <em>agency_rep_post</em>カラムの値を設定します。 
   *
   * @@param p_agencyRepPost <em>agency_rep_post</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setAgencyRepPost( String p_agencyRepPost )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agency_rep_post = p_agencyRepPost;
    agency_rep_post_is_set = true;
    agency_rep_post_is_modified = true;
  }


  /** 
   * <em>agency_rep_name_kana1</em>カラムの値を設定します。 
   *
   * @@param p_agencyRepNameKana1 <em>agency_rep_name_kana1</em>カラムの値をあらわす60桁以下のString値 
   */
  public final void setAgencyRepNameKana1( String p_agencyRepNameKana1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agency_rep_name_kana1 = p_agencyRepNameKana1;
    agency_rep_name_kana1_is_set = true;
    agency_rep_name_kana1_is_modified = true;
  }


  /** 
   * <em>agency_rep_name_kana2</em>カラムの値を設定します。 
   *
   * @@param p_agencyRepNameKana2 <em>agency_rep_name_kana2</em>カラムの値をあらわす60桁以下のString値 
   */
  public final void setAgencyRepNameKana2( String p_agencyRepNameKana2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agency_rep_name_kana2 = p_agencyRepNameKana2;
    agency_rep_name_kana2_is_set = true;
    agency_rep_name_kana2_is_modified = true;
  }


  /** 
   * <em>agency_rep_name1</em>カラムの値を設定します。 
   *
   * @@param p_agencyRepName1 <em>agency_rep_name1</em>カラムの値をあらわす60桁以下のString値 
   */
  public final void setAgencyRepName1( String p_agencyRepName1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agency_rep_name1 = p_agencyRepName1;
    agency_rep_name1_is_set = true;
    agency_rep_name1_is_modified = true;
  }


  /** 
   * <em>agency_rep_name2</em>カラムの値を設定します。 
   *
   * @@param p_agencyRepName2 <em>agency_rep_name2</em>カラムの値をあらわす60桁以下のString値 
   */
  public final void setAgencyRepName2( String p_agencyRepName2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    agency_rep_name2 = p_agencyRepName2;
    agency_rep_name2_is_set = true;
    agency_rep_name2_is_modified = true;
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
   * <em>error_info</em>カラムの値を設定します。 
   *
   * @@param p_errorInfo <em>error_info</em>カラムの値をあらわす200桁以下のString値 
   */
  public final void setErrorInfo( String p_errorInfo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    error_info = p_errorInfo;
    error_info_is_set = true;
    error_info_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("acc_open_request_number") ) {
                    return this.acc_open_request_number;
                }
                else if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("account_div") ) {
                    return this.account_div;
                }
                else if ( name.equals("account_open_date") ) {
                    return this.account_open_date;
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
                else if ( name.equals("appli_motivat_div") ) {
                    return this.appli_motivat_div;
                }
                else if ( name.equals("annual_income_div") ) {
                    return this.annual_income_div;
                }
                else if ( name.equals("annual_income_from") ) {
                    return this.annual_income_from;
                }
                else if ( name.equals("annual_income_to") ) {
                    return this.annual_income_to;
                }
                else if ( name.equals("asset_value_div") ) {
                    return this.asset_value_div;
                }
                else if ( name.equals("asset_value_from") ) {
                    return this.asset_value_from;
                }
                else if ( name.equals("asset_value_to") ) {
                    return this.asset_value_to;
                }
                else if ( name.equals("agreement_flag") ) {
                    return this.agreement_flag;
                }
                else if ( name.equals("agency_acc_name_kana1") ) {
                    return this.agency_acc_name_kana1;
                }
                else if ( name.equals("agency_acc_name_kana2") ) {
                    return this.agency_acc_name_kana2;
                }
                else if ( name.equals("agency_acc_name1") ) {
                    return this.agency_acc_name1;
                }
                else if ( name.equals("agency_acc_name2") ) {
                    return this.agency_acc_name2;
                }
                else if ( name.equals("agency_address_line1") ) {
                    return this.agency_address_line1;
                }
                else if ( name.equals("agency_address_line2") ) {
                    return this.agency_address_line2;
                }
                else if ( name.equals("agency_rep_post") ) {
                    return this.agency_rep_post;
                }
                else if ( name.equals("agency_rep_name_kana1") ) {
                    return this.agency_rep_name_kana1;
                }
                else if ( name.equals("agency_rep_name_kana2") ) {
                    return this.agency_rep_name_kana2;
                }
                else if ( name.equals("agency_rep_name1") ) {
                    return this.agency_rep_name1;
                }
                else if ( name.equals("agency_rep_name2") ) {
                    return this.agency_rep_name2;
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
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("creator") ) {
                    return this.creator;
                }
                break;
            case 'd':
                if ( name.equals("department") ) {
                    return this.department;
                }
                else if ( name.equals("delete_flag") ) {
                    return this.delete_flag;
                }
                else if ( name.equals("delete_timestamp") ) {
                    return this.delete_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("ex_account_flag") ) {
                    return this.ex_account_flag;
                }
                else if ( name.equals("ex_branch_name") ) {
                    return this.ex_branch_name;
                }
                else if ( name.equals("ex_account_code") ) {
                    return this.ex_account_code;
                }
                else if ( name.equals("era_born") ) {
                    return this.era_born;
                }
                else if ( name.equals("email_address") ) {
                    return this.email_address;
                }
                else if ( name.equals("email_address_alt1") ) {
                    return this.email_address_alt1;
                }
                else if ( name.equals("experience_div_equity") ) {
                    return this.experience_div_equity;
                }
                else if ( name.equals("experience_div_margin") ) {
                    return this.experience_div_margin;
                }
                else if ( name.equals("experience_div_bond") ) {
                    return this.experience_div_bond;
                }
                else if ( name.equals("experience_div_wt") ) {
                    return this.experience_div_wt;
                }
                else if ( name.equals("experience_div_fund_sk") ) {
                    return this.experience_div_fund_sk;
                }
                else if ( name.equals("experience_div_fund_bd") ) {
                    return this.experience_div_fund_bd;
                }
                else if ( name.equals("experience_div_fo") ) {
                    return this.experience_div_fo;
                }
                else if ( name.equals("experience_div_f_equity") ) {
                    return this.experience_div_f_equity;
                }
                else if ( name.equals("experience_div_etc") ) {
                    return this.experience_div_etc;
                }
                else if ( name.equals("experience_flag_equity") ) {
                    return this.experience_flag_equity;
                }
                else if ( name.equals("experience_flag_margin") ) {
                    return this.experience_flag_margin;
                }
                else if ( name.equals("experience_flag_bond") ) {
                    return this.experience_flag_bond;
                }
                else if ( name.equals("experience_flag_wt") ) {
                    return this.experience_flag_wt;
                }
                else if ( name.equals("experience_flag_fund_sk") ) {
                    return this.experience_flag_fund_sk;
                }
                else if ( name.equals("experience_flag_fund_bd") ) {
                    return this.experience_flag_fund_bd;
                }
                else if ( name.equals("experience_flag_fo") ) {
                    return this.experience_flag_fo;
                }
                else if ( name.equals("experience_flag_f_equity") ) {
                    return this.experience_flag_f_equity;
                }
                else if ( name.equals("experience_flag_etc") ) {
                    return this.experience_flag_etc;
                }
                else if ( name.equals("experience_from") ) {
                    return this.experience_from;
                }
                else if ( name.equals("experience_to") ) {
                    return this.experience_to;
                }
                else if ( name.equals("ext_item_div1") ) {
                    return this.ext_item_div1;
                }
                else if ( name.equals("ext_item_div2") ) {
                    return this.ext_item_div2;
                }
                else if ( name.equals("ext_item_div3") ) {
                    return this.ext_item_div3;
                }
                else if ( name.equals("ext_item_div4") ) {
                    return this.ext_item_div4;
                }
                else if ( name.equals("ext_item_div5") ) {
                    return this.ext_item_div5;
                }
                else if ( name.equals("ext_item_div6") ) {
                    return this.ext_item_div6;
                }
                else if ( name.equals("ext_item_div7") ) {
                    return this.ext_item_div7;
                }
                else if ( name.equals("ext_item_div8") ) {
                    return this.ext_item_div8;
                }
                else if ( name.equals("ext_item_div9") ) {
                    return this.ext_item_div9;
                }
                else if ( name.equals("ext_item_div10") ) {
                    return this.ext_item_div10;
                }
                else if ( name.equals("ext_item_flag1") ) {
                    return this.ext_item_flag1;
                }
                else if ( name.equals("ext_item_flag2") ) {
                    return this.ext_item_flag2;
                }
                else if ( name.equals("ext_item_flag3") ) {
                    return this.ext_item_flag3;
                }
                else if ( name.equals("ext_item_flag4") ) {
                    return this.ext_item_flag4;
                }
                else if ( name.equals("ext_item_flag5") ) {
                    return this.ext_item_flag5;
                }
                else if ( name.equals("ext_item_flag6") ) {
                    return this.ext_item_flag6;
                }
                else if ( name.equals("ext_item_flag7") ) {
                    return this.ext_item_flag7;
                }
                else if ( name.equals("ext_item_flag8") ) {
                    return this.ext_item_flag8;
                }
                else if ( name.equals("ext_item_flag9") ) {
                    return this.ext_item_flag9;
                }
                else if ( name.equals("ext_item_flag10") ) {
                    return this.ext_item_flag10;
                }
                else if ( name.equals("ext_item_text1") ) {
                    return this.ext_item_text1;
                }
                else if ( name.equals("ext_item_text2") ) {
                    return this.ext_item_text2;
                }
                else if ( name.equals("ext_item_text3") ) {
                    return this.ext_item_text3;
                }
                else if ( name.equals("ext_item_text4") ) {
                    return this.ext_item_text4;
                }
                else if ( name.equals("ext_item_text5") ) {
                    return this.ext_item_text5;
                }
                else if ( name.equals("ext_item_text6") ) {
                    return this.ext_item_text6;
                }
                else if ( name.equals("ext_item_text7") ) {
                    return this.ext_item_text7;
                }
                else if ( name.equals("ext_item_text8") ) {
                    return this.ext_item_text8;
                }
                else if ( name.equals("ext_item_text9") ) {
                    return this.ext_item_text9;
                }
                else if ( name.equals("ext_item_text10") ) {
                    return this.ext_item_text10;
                }
                else if ( name.equals("exclusive_use_account_no") ) {
                    return this.exclusive_use_account_no;
                }
                else if ( name.equals("ext_item_div11") ) {
                    return this.ext_item_div11;
                }
                else if ( name.equals("ext_item_div12") ) {
                    return this.ext_item_div12;
                }
                else if ( name.equals("ext_item_div13") ) {
                    return this.ext_item_div13;
                }
                else if ( name.equals("ext_item_div14") ) {
                    return this.ext_item_div14;
                }
                else if ( name.equals("ext_item_div15") ) {
                    return this.ext_item_div15;
                }
                else if ( name.equals("error_info") ) {
                    return this.error_info;
                }
                break;
            case 'f':
                if ( name.equals("family_name") ) {
                    return this.family_name;
                }
                else if ( name.equals("family_name_alt1") ) {
                    return this.family_name_alt1;
                }
                else if ( name.equals("fax") ) {
                    return this.fax;
                }
                else if ( name.equals("family_relationship") ) {
                    return this.family_relationship;
                }
                else if ( name.equals("family_relationship_etc") ) {
                    return this.family_relationship_etc;
                }
                else if ( name.equals("fin_institution_code") ) {
                    return this.fin_institution_code;
                }
                else if ( name.equals("fin_institution_name") ) {
                    return this.fin_institution_name;
                }
                else if ( name.equals("fin_branch_code") ) {
                    return this.fin_branch_code;
                }
                else if ( name.equals("fin_branch_name") ) {
                    return this.fin_branch_name;
                }
                else if ( name.equals("fin_save_div") ) {
                    return this.fin_save_div;
                }
                else if ( name.equals("fin_account_no") ) {
                    return this.fin_account_no;
                }
                else if ( name.equals("fin_account_name") ) {
                    return this.fin_account_name;
                }
                else if ( name.equals("fund_budget_amount_div") ) {
                    return this.fund_budget_amount_div;
                }
                else if ( name.equals("fund_budget_div") ) {
                    return this.fund_budget_div;
                }
                else if ( name.equals("fund_budget_etc") ) {
                    return this.fund_budget_etc;
                }
                else if ( name.equals("foreign_account_no") ) {
                    return this.foreign_account_no;
                }
                else if ( name.equals("foreign_account_name") ) {
                    return this.foreign_account_name;
                }
                else if ( name.equals("foreign_account_name_eng") ) {
                    return this.foreign_account_name_eng;
                }
                else if ( name.equals("foreign_save_div") ) {
                    return this.foreign_save_div;
                }
                else if ( name.equals("foreign_flag") ) {
                    return this.foreign_flag;
                }
                break;
            case 'g':
                if ( name.equals("given_name") ) {
                    return this.given_name;
                }
                else if ( name.equals("given_name_alt1") ) {
                    return this.given_name_alt1;
                }
                else if ( name.equals("gp_voucher_div") ) {
                    return this.gp_voucher_div;
                }
                else if ( name.equals("gp_course") ) {
                    return this.gp_course;
                }
                else if ( name.equals("gp_plan") ) {
                    return this.gp_plan;
                }
                else if ( name.equals("gp_target_figure") ) {
                    return this.gp_target_figure;
                }
                else if ( name.equals("gp_target_year") ) {
                    return this.gp_target_year;
                }
                else if ( name.equals("gp_target_month") ) {
                    return this.gp_target_month;
                }
                else if ( name.equals("gp_installment_figure") ) {
                    return this.gp_installment_figure;
                }
                else if ( name.equals("gp_deposit_cycle") ) {
                    return this.gp_deposit_cycle;
                }
                else if ( name.equals("gp_payment_root") ) {
                    return this.gp_payment_root;
                }
                else if ( name.equals("gp_reinvest_div") ) {
                    return this.gp_reinvest_div;
                }
                else if ( name.equals("gp_tax_div") ) {
                    return this.gp_tax_div;
                }
                else if ( name.equals("gp_taxfree_limit") ) {
                    return this.gp_taxfree_limit;
                }
                else if ( name.equals("gp_special_taxfree_limit") ) {
                    return this.gp_special_taxfree_limit;
                }
                else if ( name.equals("gp_subscr_summary") ) {
                    return this.gp_subscr_summary;
                }
                else if ( name.equals("gp_product_code") ) {
                    return this.gp_product_code;
                }
                else if ( name.equals("gp_mortgage_customer") ) {
                    return this.gp_mortgage_customer;
                }
                else if ( name.equals("gp_mix_customer") ) {
                    return this.gp_mix_customer;
                }
                else if ( name.equals("gp_contract") ) {
                    return this.gp_contract;
                }
                break;
            case 'h':
                if ( name.equals("householder") ) {
                    return this.householder;
                }
                else if ( name.equals("householder_kana") ) {
                    return this.householder_kana;
                }
                else if ( name.equals("householder_occupation_div") ) {
                    return this.householder_occupation_div;
                }
                else if ( name.equals("householder_office") ) {
                    return this.householder_office;
                }
                else if ( name.equals("householder_office_address") ) {
                    return this.householder_office_address;
                }
                else if ( name.equals("householder_department") ) {
                    return this.householder_department;
                }
                else if ( name.equals("householder_office_tel") ) {
                    return this.householder_office_tel;
                }
                else if ( name.equals("householder_office_fax") ) {
                    return this.householder_office_fax;
                }
                else if ( name.equals("householder_post") ) {
                    return this.householder_post;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("institution_id") ) {
                    return new Long( institution_id );
                }
                else if ( name.equals("infomation_claim_datetime") ) {
                    return this.infomation_claim_datetime;
                }
                else if ( name.equals("initial_password") ) {
                    return this.initial_password;
                }
                else if ( name.equals("interest_flag_equity") ) {
                    return this.interest_flag_equity;
                }
                else if ( name.equals("interest_flag_ministock") ) {
                    return this.interest_flag_ministock;
                }
                else if ( name.equals("interest_flag_margin") ) {
                    return this.interest_flag_margin;
                }
                else if ( name.equals("interest_flag_bond") ) {
                    return this.interest_flag_bond;
                }
                else if ( name.equals("interest_flag_fund") ) {
                    return this.interest_flag_fund;
                }
                else if ( name.equals("interest_flag_fo") ) {
                    return this.interest_flag_fo;
                }
                else if ( name.equals("interest_flag_f_equity") ) {
                    return this.interest_flag_f_equity;
                }
                else if ( name.equals("interest_flag_etc") ) {
                    return this.interest_flag_etc;
                }
                else if ( name.equals("invest_purpose_div") ) {
                    return this.invest_purpose_div;
                }
                else if ( name.equals("id_confirm_flag") ) {
                    return this.id_confirm_flag;
                }
                else if ( name.equals("id_confirm_doc_div") ) {
                    return this.id_confirm_doc_div;
                }
                else if ( name.equals("id_confirm_doc_etc") ) {
                    return this.id_confirm_doc_etc;
                }
                else if ( name.equals("insider_flag") ) {
                    return this.insider_flag;
                }
                else if ( name.equals("insider_voucher_div") ) {
                    return this.insider_voucher_div;
                }
                else if ( name.equals("insider_product_code") ) {
                    return this.insider_product_code;
                }
                else if ( name.equals("insider_relation_div") ) {
                    return this.insider_relation_div;
                }
                else if ( name.equals("insider_officer_name") ) {
                    return this.insider_officer_name;
                }
                else if ( name.equals("insider_post_code") ) {
                    return this.insider_post_code;
                }
                else if ( name.equals("insider_post_name") ) {
                    return this.insider_post_name;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("mobile") ) {
                    return this.mobile;
                }
                break;
            case 'o':
                if ( name.equals("order_div") ) {
                    return this.order_div;
                }
                else if ( name.equals("occupation_div") ) {
                    return this.occupation_div;
                }
                else if ( name.equals("office") ) {
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
                else if ( name.equals("office_fax") ) {
                    return this.office_fax;
                }
                break;
            case 'p':
                if ( name.equals("post") ) {
                    return this.post;
                }
                else if ( name.equals("postal_save_code") ) {
                    return this.postal_save_code;
                }
                else if ( name.equals("postal_save_no") ) {
                    return this.postal_save_no;
                }
                else if ( name.equals("product_name") ) {
                    return this.product_name;
                }
                else if ( name.equals("print_flag") ) {
                    return this.print_flag;
                }
                break;
            case 'r':
                if ( name.equals("resident") ) {
                    return this.resident;
                }
                else if ( name.equals("real_name_voucher_div") ) {
                    return this.real_name_voucher_div;
                }
                else if ( name.equals("real_name1") ) {
                    return this.real_name1;
                }
                else if ( name.equals("real_name2") ) {
                    return this.real_name2;
                }
                else if ( name.equals("receipt_flag") ) {
                    return this.receipt_flag;
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code") ) {
                    return this.sonar_trader_code;
                }
                else if ( name.equals("sex") ) {
                    return this.sex;
                }
                else if ( name.equals("special_acc") ) {
                    return this.special_acc;
                }
                else if ( name.equals("special_acc_margin") ) {
                    return this.special_acc_margin;
                }
                else if ( name.equals("send_zip_code") ) {
                    return this.send_zip_code;
                }
                else if ( name.equals("send_address_line1") ) {
                    return this.send_address_line1;
                }
                else if ( name.equals("send_address_line2") ) {
                    return this.send_address_line2;
                }
                else if ( name.equals("send_address_line3") ) {
                    return this.send_address_line3;
                }
                else if ( name.equals("send_timestamp") ) {
                    return this.send_timestamp;
                }
                else if ( name.equals("stk_voucher_div") ) {
                    return this.stk_voucher_div;
                }
                else if ( name.equals("stk_taxation_tran_div") ) {
                    return this.stk_taxation_tran_div;
                }
                else if ( name.equals("stk_address_line_kana") ) {
                    return this.stk_address_line_kana;
                }
                else if ( name.equals("stk_transfer_div") ) {
                    return this.stk_transfer_div;
                }
                else if ( name.equals("stk_fin_institution_code") ) {
                    return this.stk_fin_institution_code;
                }
                else if ( name.equals("stk_fin_branch_code") ) {
                    return this.stk_fin_branch_code;
                }
                else if ( name.equals("stk_fin_save_div") ) {
                    return this.stk_fin_save_div;
                }
                else if ( name.equals("stk_fin_account_no") ) {
                    return this.stk_fin_account_no;
                }
                else if ( name.equals("status") ) {
                    return this.status;
                }
                break;
            case 't':
                if ( name.equals("telephone") ) {
                    return this.telephone;
                }
                else if ( name.equals("transfer_div") ) {
                    return this.transfer_div;
                }
                else if ( name.equals("trans_commission") ) {
                    return this.trans_commission;
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
                if ( name.equals("acc_open_request_number") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'acc_open_request_number' must be String: '"+value+"' is not." );
                    this.acc_open_request_number = (String) value;
                    if (this.acc_open_request_number_is_set)
                        this.acc_open_request_number_is_modified = true;
                    this.acc_open_request_number_is_set = true;
                    return;
                }
                else if ( name.equals("account_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("account_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_div' must be String: '"+value+"' is not." );
                    this.account_div = (String) value;
                    if (this.account_div_is_set)
                        this.account_div_is_modified = true;
                    this.account_div_is_set = true;
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
                else if ( name.equals("annual_income_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'annual_income_div' must be String: '"+value+"' is not." );
                    this.annual_income_div = (String) value;
                    if (this.annual_income_div_is_set)
                        this.annual_income_div_is_modified = true;
                    this.annual_income_div_is_set = true;
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
                else if ( name.equals("asset_value_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'asset_value_div' must be String: '"+value+"' is not." );
                    this.asset_value_div = (String) value;
                    if (this.asset_value_div_is_set)
                        this.asset_value_div_is_modified = true;
                    this.asset_value_div_is_set = true;
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
                else if ( name.equals("agreement_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'agreement_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.agreement_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.agreement_flag_is_set)
                        this.agreement_flag_is_modified = true;
                    this.agreement_flag_is_set = true;
                    return;
                }
                else if ( name.equals("agency_acc_name_kana1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agency_acc_name_kana1' must be String: '"+value+"' is not." );
                    this.agency_acc_name_kana1 = (String) value;
                    if (this.agency_acc_name_kana1_is_set)
                        this.agency_acc_name_kana1_is_modified = true;
                    this.agency_acc_name_kana1_is_set = true;
                    return;
                }
                else if ( name.equals("agency_acc_name_kana2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agency_acc_name_kana2' must be String: '"+value+"' is not." );
                    this.agency_acc_name_kana2 = (String) value;
                    if (this.agency_acc_name_kana2_is_set)
                        this.agency_acc_name_kana2_is_modified = true;
                    this.agency_acc_name_kana2_is_set = true;
                    return;
                }
                else if ( name.equals("agency_acc_name1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agency_acc_name1' must be String: '"+value+"' is not." );
                    this.agency_acc_name1 = (String) value;
                    if (this.agency_acc_name1_is_set)
                        this.agency_acc_name1_is_modified = true;
                    this.agency_acc_name1_is_set = true;
                    return;
                }
                else if ( name.equals("agency_acc_name2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agency_acc_name2' must be String: '"+value+"' is not." );
                    this.agency_acc_name2 = (String) value;
                    if (this.agency_acc_name2_is_set)
                        this.agency_acc_name2_is_modified = true;
                    this.agency_acc_name2_is_set = true;
                    return;
                }
                else if ( name.equals("agency_address_line1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agency_address_line1' must be String: '"+value+"' is not." );
                    this.agency_address_line1 = (String) value;
                    if (this.agency_address_line1_is_set)
                        this.agency_address_line1_is_modified = true;
                    this.agency_address_line1_is_set = true;
                    return;
                }
                else if ( name.equals("agency_address_line2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agency_address_line2' must be String: '"+value+"' is not." );
                    this.agency_address_line2 = (String) value;
                    if (this.agency_address_line2_is_set)
                        this.agency_address_line2_is_modified = true;
                    this.agency_address_line2_is_set = true;
                    return;
                }
                else if ( name.equals("agency_rep_post") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agency_rep_post' must be String: '"+value+"' is not." );
                    this.agency_rep_post = (String) value;
                    if (this.agency_rep_post_is_set)
                        this.agency_rep_post_is_modified = true;
                    this.agency_rep_post_is_set = true;
                    return;
                }
                else if ( name.equals("agency_rep_name_kana1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agency_rep_name_kana1' must be String: '"+value+"' is not." );
                    this.agency_rep_name_kana1 = (String) value;
                    if (this.agency_rep_name_kana1_is_set)
                        this.agency_rep_name_kana1_is_modified = true;
                    this.agency_rep_name_kana1_is_set = true;
                    return;
                }
                else if ( name.equals("agency_rep_name_kana2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agency_rep_name_kana2' must be String: '"+value+"' is not." );
                    this.agency_rep_name_kana2 = (String) value;
                    if (this.agency_rep_name_kana2_is_set)
                        this.agency_rep_name_kana2_is_modified = true;
                    this.agency_rep_name_kana2_is_set = true;
                    return;
                }
                else if ( name.equals("agency_rep_name1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agency_rep_name1' must be String: '"+value+"' is not." );
                    this.agency_rep_name1 = (String) value;
                    if (this.agency_rep_name1_is_set)
                        this.agency_rep_name1_is_modified = true;
                    this.agency_rep_name1_is_set = true;
                    return;
                }
                else if ( name.equals("agency_rep_name2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'agency_rep_name2' must be String: '"+value+"' is not." );
                    this.agency_rep_name2 = (String) value;
                    if (this.agency_rep_name2_is_set)
                        this.agency_rep_name2_is_modified = true;
                    this.agency_rep_name2_is_set = true;
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
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("creator") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'creator' must be String: '"+value+"' is not." );
                    this.creator = (String) value;
                    if (this.creator_is_set)
                        this.creator_is_modified = true;
                    this.creator_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("department") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'department' must be String: '"+value+"' is not." );
                    this.department = (String) value;
                    if (this.department_is_set)
                        this.department_is_modified = true;
                    this.department_is_set = true;
                    return;
                }
                else if ( name.equals("delete_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'delete_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.delete_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.delete_flag_is_set)
                        this.delete_flag_is_modified = true;
                    this.delete_flag_is_set = true;
                    return;
                }
                else if ( name.equals("delete_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'delete_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.delete_timestamp = (java.sql.Timestamp) value;
                    if (this.delete_timestamp_is_set)
                        this.delete_timestamp_is_modified = true;
                    this.delete_timestamp_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("ex_account_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'ex_account_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.ex_account_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.ex_account_flag_is_set)
                        this.ex_account_flag_is_modified = true;
                    this.ex_account_flag_is_set = true;
                    return;
                }
                else if ( name.equals("ex_branch_name") ) {
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
                else if ( name.equals("experience_div_equity") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div_equity' must be String: '"+value+"' is not." );
                    this.experience_div_equity = (String) value;
                    if (this.experience_div_equity_is_set)
                        this.experience_div_equity_is_modified = true;
                    this.experience_div_equity_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div_margin") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div_margin' must be String: '"+value+"' is not." );
                    this.experience_div_margin = (String) value;
                    if (this.experience_div_margin_is_set)
                        this.experience_div_margin_is_modified = true;
                    this.experience_div_margin_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div_bond") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div_bond' must be String: '"+value+"' is not." );
                    this.experience_div_bond = (String) value;
                    if (this.experience_div_bond_is_set)
                        this.experience_div_bond_is_modified = true;
                    this.experience_div_bond_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div_wt") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div_wt' must be String: '"+value+"' is not." );
                    this.experience_div_wt = (String) value;
                    if (this.experience_div_wt_is_set)
                        this.experience_div_wt_is_modified = true;
                    this.experience_div_wt_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div_fund_sk") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div_fund_sk' must be String: '"+value+"' is not." );
                    this.experience_div_fund_sk = (String) value;
                    if (this.experience_div_fund_sk_is_set)
                        this.experience_div_fund_sk_is_modified = true;
                    this.experience_div_fund_sk_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div_fund_bd") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div_fund_bd' must be String: '"+value+"' is not." );
                    this.experience_div_fund_bd = (String) value;
                    if (this.experience_div_fund_bd_is_set)
                        this.experience_div_fund_bd_is_modified = true;
                    this.experience_div_fund_bd_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div_fo") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div_fo' must be String: '"+value+"' is not." );
                    this.experience_div_fo = (String) value;
                    if (this.experience_div_fo_is_set)
                        this.experience_div_fo_is_modified = true;
                    this.experience_div_fo_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div_f_equity") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div_f_equity' must be String: '"+value+"' is not." );
                    this.experience_div_f_equity = (String) value;
                    if (this.experience_div_f_equity_is_set)
                        this.experience_div_f_equity_is_modified = true;
                    this.experience_div_f_equity_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div_etc") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div_etc' must be String: '"+value+"' is not." );
                    this.experience_div_etc = (String) value;
                    if (this.experience_div_etc_is_set)
                        this.experience_div_etc_is_modified = true;
                    this.experience_div_etc_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag_equity") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'experience_flag_equity' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.experience_flag_equity = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.experience_flag_equity_is_set)
                        this.experience_flag_equity_is_modified = true;
                    this.experience_flag_equity_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag_margin") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'experience_flag_margin' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.experience_flag_margin = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.experience_flag_margin_is_set)
                        this.experience_flag_margin_is_modified = true;
                    this.experience_flag_margin_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag_bond") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'experience_flag_bond' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.experience_flag_bond = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.experience_flag_bond_is_set)
                        this.experience_flag_bond_is_modified = true;
                    this.experience_flag_bond_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag_wt") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'experience_flag_wt' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.experience_flag_wt = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.experience_flag_wt_is_set)
                        this.experience_flag_wt_is_modified = true;
                    this.experience_flag_wt_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag_fund_sk") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'experience_flag_fund_sk' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.experience_flag_fund_sk = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.experience_flag_fund_sk_is_set)
                        this.experience_flag_fund_sk_is_modified = true;
                    this.experience_flag_fund_sk_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag_fund_bd") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'experience_flag_fund_bd' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.experience_flag_fund_bd = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.experience_flag_fund_bd_is_set)
                        this.experience_flag_fund_bd_is_modified = true;
                    this.experience_flag_fund_bd_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag_fo") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'experience_flag_fo' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.experience_flag_fo = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.experience_flag_fo_is_set)
                        this.experience_flag_fo_is_modified = true;
                    this.experience_flag_fo_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag_f_equity") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'experience_flag_f_equity' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.experience_flag_f_equity = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.experience_flag_f_equity_is_set)
                        this.experience_flag_f_equity_is_modified = true;
                    this.experience_flag_f_equity_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag_etc") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'experience_flag_etc' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.experience_flag_etc = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.experience_flag_etc_is_set)
                        this.experience_flag_etc_is_modified = true;
                    this.experience_flag_etc_is_set = true;
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
                else if ( name.equals("ext_item_div1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_div1' must be String: '"+value+"' is not." );
                    this.ext_item_div1 = (String) value;
                    if (this.ext_item_div1_is_set)
                        this.ext_item_div1_is_modified = true;
                    this.ext_item_div1_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_div2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_div2' must be String: '"+value+"' is not." );
                    this.ext_item_div2 = (String) value;
                    if (this.ext_item_div2_is_set)
                        this.ext_item_div2_is_modified = true;
                    this.ext_item_div2_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_div3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_div3' must be String: '"+value+"' is not." );
                    this.ext_item_div3 = (String) value;
                    if (this.ext_item_div3_is_set)
                        this.ext_item_div3_is_modified = true;
                    this.ext_item_div3_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_div4") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_div4' must be String: '"+value+"' is not." );
                    this.ext_item_div4 = (String) value;
                    if (this.ext_item_div4_is_set)
                        this.ext_item_div4_is_modified = true;
                    this.ext_item_div4_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_div5") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_div5' must be String: '"+value+"' is not." );
                    this.ext_item_div5 = (String) value;
                    if (this.ext_item_div5_is_set)
                        this.ext_item_div5_is_modified = true;
                    this.ext_item_div5_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_div6") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_div6' must be String: '"+value+"' is not." );
                    this.ext_item_div6 = (String) value;
                    if (this.ext_item_div6_is_set)
                        this.ext_item_div6_is_modified = true;
                    this.ext_item_div6_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_div7") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_div7' must be String: '"+value+"' is not." );
                    this.ext_item_div7 = (String) value;
                    if (this.ext_item_div7_is_set)
                        this.ext_item_div7_is_modified = true;
                    this.ext_item_div7_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_div8") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_div8' must be String: '"+value+"' is not." );
                    this.ext_item_div8 = (String) value;
                    if (this.ext_item_div8_is_set)
                        this.ext_item_div8_is_modified = true;
                    this.ext_item_div8_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_div9") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_div9' must be String: '"+value+"' is not." );
                    this.ext_item_div9 = (String) value;
                    if (this.ext_item_div9_is_set)
                        this.ext_item_div9_is_modified = true;
                    this.ext_item_div9_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_div10") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_div10' must be String: '"+value+"' is not." );
                    this.ext_item_div10 = (String) value;
                    if (this.ext_item_div10_is_set)
                        this.ext_item_div10_is_modified = true;
                    this.ext_item_div10_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_flag1") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'ext_item_flag1' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.ext_item_flag1 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.ext_item_flag1_is_set)
                        this.ext_item_flag1_is_modified = true;
                    this.ext_item_flag1_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_flag2") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'ext_item_flag2' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.ext_item_flag2 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.ext_item_flag2_is_set)
                        this.ext_item_flag2_is_modified = true;
                    this.ext_item_flag2_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_flag3") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'ext_item_flag3' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.ext_item_flag3 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.ext_item_flag3_is_set)
                        this.ext_item_flag3_is_modified = true;
                    this.ext_item_flag3_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_flag4") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'ext_item_flag4' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.ext_item_flag4 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.ext_item_flag4_is_set)
                        this.ext_item_flag4_is_modified = true;
                    this.ext_item_flag4_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_flag5") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'ext_item_flag5' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.ext_item_flag5 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.ext_item_flag5_is_set)
                        this.ext_item_flag5_is_modified = true;
                    this.ext_item_flag5_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_flag6") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'ext_item_flag6' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.ext_item_flag6 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.ext_item_flag6_is_set)
                        this.ext_item_flag6_is_modified = true;
                    this.ext_item_flag6_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_flag7") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'ext_item_flag7' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.ext_item_flag7 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.ext_item_flag7_is_set)
                        this.ext_item_flag7_is_modified = true;
                    this.ext_item_flag7_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_flag8") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'ext_item_flag8' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.ext_item_flag8 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.ext_item_flag8_is_set)
                        this.ext_item_flag8_is_modified = true;
                    this.ext_item_flag8_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_flag9") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'ext_item_flag9' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.ext_item_flag9 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.ext_item_flag9_is_set)
                        this.ext_item_flag9_is_modified = true;
                    this.ext_item_flag9_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_flag10") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'ext_item_flag10' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.ext_item_flag10 = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.ext_item_flag10_is_set)
                        this.ext_item_flag10_is_modified = true;
                    this.ext_item_flag10_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_text1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_text1' must be String: '"+value+"' is not." );
                    this.ext_item_text1 = (String) value;
                    if (this.ext_item_text1_is_set)
                        this.ext_item_text1_is_modified = true;
                    this.ext_item_text1_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_text2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_text2' must be String: '"+value+"' is not." );
                    this.ext_item_text2 = (String) value;
                    if (this.ext_item_text2_is_set)
                        this.ext_item_text2_is_modified = true;
                    this.ext_item_text2_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_text3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_text3' must be String: '"+value+"' is not." );
                    this.ext_item_text3 = (String) value;
                    if (this.ext_item_text3_is_set)
                        this.ext_item_text3_is_modified = true;
                    this.ext_item_text3_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_text4") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_text4' must be String: '"+value+"' is not." );
                    this.ext_item_text4 = (String) value;
                    if (this.ext_item_text4_is_set)
                        this.ext_item_text4_is_modified = true;
                    this.ext_item_text4_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_text5") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_text5' must be String: '"+value+"' is not." );
                    this.ext_item_text5 = (String) value;
                    if (this.ext_item_text5_is_set)
                        this.ext_item_text5_is_modified = true;
                    this.ext_item_text5_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_text6") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_text6' must be String: '"+value+"' is not." );
                    this.ext_item_text6 = (String) value;
                    if (this.ext_item_text6_is_set)
                        this.ext_item_text6_is_modified = true;
                    this.ext_item_text6_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_text7") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_text7' must be String: '"+value+"' is not." );
                    this.ext_item_text7 = (String) value;
                    if (this.ext_item_text7_is_set)
                        this.ext_item_text7_is_modified = true;
                    this.ext_item_text7_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_text8") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_text8' must be String: '"+value+"' is not." );
                    this.ext_item_text8 = (String) value;
                    if (this.ext_item_text8_is_set)
                        this.ext_item_text8_is_modified = true;
                    this.ext_item_text8_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_text9") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_text9' must be String: '"+value+"' is not." );
                    this.ext_item_text9 = (String) value;
                    if (this.ext_item_text9_is_set)
                        this.ext_item_text9_is_modified = true;
                    this.ext_item_text9_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_text10") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_text10' must be String: '"+value+"' is not." );
                    this.ext_item_text10 = (String) value;
                    if (this.ext_item_text10_is_set)
                        this.ext_item_text10_is_modified = true;
                    this.ext_item_text10_is_set = true;
                    return;
                }
                else if ( name.equals("exclusive_use_account_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'exclusive_use_account_no' must be String: '"+value+"' is not." );
                    this.exclusive_use_account_no = (String) value;
                    if (this.exclusive_use_account_no_is_set)
                        this.exclusive_use_account_no_is_modified = true;
                    this.exclusive_use_account_no_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_div11") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_div11' must be String: '"+value+"' is not." );
                    this.ext_item_div11 = (String) value;
                    if (this.ext_item_div11_is_set)
                        this.ext_item_div11_is_modified = true;
                    this.ext_item_div11_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_div12") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_div12' must be String: '"+value+"' is not." );
                    this.ext_item_div12 = (String) value;
                    if (this.ext_item_div12_is_set)
                        this.ext_item_div12_is_modified = true;
                    this.ext_item_div12_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_div13") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_div13' must be String: '"+value+"' is not." );
                    this.ext_item_div13 = (String) value;
                    if (this.ext_item_div13_is_set)
                        this.ext_item_div13_is_modified = true;
                    this.ext_item_div13_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_div14") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_div14' must be String: '"+value+"' is not." );
                    this.ext_item_div14 = (String) value;
                    if (this.ext_item_div14_is_set)
                        this.ext_item_div14_is_modified = true;
                    this.ext_item_div14_is_set = true;
                    return;
                }
                else if ( name.equals("ext_item_div15") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ext_item_div15' must be String: '"+value+"' is not." );
                    this.ext_item_div15 = (String) value;
                    if (this.ext_item_div15_is_set)
                        this.ext_item_div15_is_modified = true;
                    this.ext_item_div15_is_set = true;
                    return;
                }
                else if ( name.equals("error_info") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'error_info' must be String: '"+value+"' is not." );
                    this.error_info = (String) value;
                    if (this.error_info_is_set)
                        this.error_info_is_modified = true;
                    this.error_info_is_set = true;
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
                else if ( name.equals("family_relationship") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'family_relationship' must be String: '"+value+"' is not." );
                    this.family_relationship = (String) value;
                    if (this.family_relationship_is_set)
                        this.family_relationship_is_modified = true;
                    this.family_relationship_is_set = true;
                    return;
                }
                else if ( name.equals("family_relationship_etc") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'family_relationship_etc' must be String: '"+value+"' is not." );
                    this.family_relationship_etc = (String) value;
                    if (this.family_relationship_etc_is_set)
                        this.family_relationship_etc_is_modified = true;
                    this.family_relationship_etc_is_set = true;
                    return;
                }
                else if ( name.equals("fin_institution_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_institution_code' must be String: '"+value+"' is not." );
                    this.fin_institution_code = (String) value;
                    if (this.fin_institution_code_is_set)
                        this.fin_institution_code_is_modified = true;
                    this.fin_institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("fin_institution_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_institution_name' must be String: '"+value+"' is not." );
                    this.fin_institution_name = (String) value;
                    if (this.fin_institution_name_is_set)
                        this.fin_institution_name_is_modified = true;
                    this.fin_institution_name_is_set = true;
                    return;
                }
                else if ( name.equals("fin_branch_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_branch_code' must be String: '"+value+"' is not." );
                    this.fin_branch_code = (String) value;
                    if (this.fin_branch_code_is_set)
                        this.fin_branch_code_is_modified = true;
                    this.fin_branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("fin_branch_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_branch_name' must be String: '"+value+"' is not." );
                    this.fin_branch_name = (String) value;
                    if (this.fin_branch_name_is_set)
                        this.fin_branch_name_is_modified = true;
                    this.fin_branch_name_is_set = true;
                    return;
                }
                else if ( name.equals("fin_save_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_save_div' must be String: '"+value+"' is not." );
                    this.fin_save_div = (String) value;
                    if (this.fin_save_div_is_set)
                        this.fin_save_div_is_modified = true;
                    this.fin_save_div_is_set = true;
                    return;
                }
                else if ( name.equals("fin_account_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_account_no' must be String: '"+value+"' is not." );
                    this.fin_account_no = (String) value;
                    if (this.fin_account_no_is_set)
                        this.fin_account_no_is_modified = true;
                    this.fin_account_no_is_set = true;
                    return;
                }
                else if ( name.equals("fin_account_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fin_account_name' must be String: '"+value+"' is not." );
                    this.fin_account_name = (String) value;
                    if (this.fin_account_name_is_set)
                        this.fin_account_name_is_modified = true;
                    this.fin_account_name_is_set = true;
                    return;
                }
                else if ( name.equals("fund_budget_amount_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fund_budget_amount_div' must be String: '"+value+"' is not." );
                    this.fund_budget_amount_div = (String) value;
                    if (this.fund_budget_amount_div_is_set)
                        this.fund_budget_amount_div_is_modified = true;
                    this.fund_budget_amount_div_is_set = true;
                    return;
                }
                else if ( name.equals("fund_budget_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fund_budget_div' must be String: '"+value+"' is not." );
                    this.fund_budget_div = (String) value;
                    if (this.fund_budget_div_is_set)
                        this.fund_budget_div_is_modified = true;
                    this.fund_budget_div_is_set = true;
                    return;
                }
                else if ( name.equals("fund_budget_etc") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fund_budget_etc' must be String: '"+value+"' is not." );
                    this.fund_budget_etc = (String) value;
                    if (this.fund_budget_etc_is_set)
                        this.fund_budget_etc_is_modified = true;
                    this.fund_budget_etc_is_set = true;
                    return;
                }
                else if ( name.equals("foreign_account_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'foreign_account_no' must be String: '"+value+"' is not." );
                    this.foreign_account_no = (String) value;
                    if (this.foreign_account_no_is_set)
                        this.foreign_account_no_is_modified = true;
                    this.foreign_account_no_is_set = true;
                    return;
                }
                else if ( name.equals("foreign_account_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'foreign_account_name' must be String: '"+value+"' is not." );
                    this.foreign_account_name = (String) value;
                    if (this.foreign_account_name_is_set)
                        this.foreign_account_name_is_modified = true;
                    this.foreign_account_name_is_set = true;
                    return;
                }
                else if ( name.equals("foreign_account_name_eng") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'foreign_account_name_eng' must be String: '"+value+"' is not." );
                    this.foreign_account_name_eng = (String) value;
                    if (this.foreign_account_name_eng_is_set)
                        this.foreign_account_name_eng_is_modified = true;
                    this.foreign_account_name_eng_is_set = true;
                    return;
                }
                else if ( name.equals("foreign_save_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'foreign_save_div' must be String: '"+value+"' is not." );
                    this.foreign_save_div = (String) value;
                    if (this.foreign_save_div_is_set)
                        this.foreign_save_div_is_modified = true;
                    this.foreign_save_div_is_set = true;
                    return;
                }
                else if ( name.equals("foreign_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'foreign_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.foreign_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.foreign_flag_is_set)
                        this.foreign_flag_is_modified = true;
                    this.foreign_flag_is_set = true;
                    return;
                }
                break;
            case 'g':
                if ( name.equals("given_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'given_name' must be String: '"+value+"' is not." );
                    this.given_name = (String) value;
                    if (this.given_name_is_set)
                        this.given_name_is_modified = true;
                    this.given_name_is_set = true;
                    return;
                }
                else if ( name.equals("given_name_alt1") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'given_name_alt1' must be String: '"+value+"' is not." );
                    this.given_name_alt1 = (String) value;
                    if (this.given_name_alt1_is_set)
                        this.given_name_alt1_is_modified = true;
                    this.given_name_alt1_is_set = true;
                    return;
                }
                else if ( name.equals("gp_voucher_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_voucher_div' must be String: '"+value+"' is not." );
                    this.gp_voucher_div = (String) value;
                    if (this.gp_voucher_div_is_set)
                        this.gp_voucher_div_is_modified = true;
                    this.gp_voucher_div_is_set = true;
                    return;
                }
                else if ( name.equals("gp_course") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_course' must be String: '"+value+"' is not." );
                    this.gp_course = (String) value;
                    if (this.gp_course_is_set)
                        this.gp_course_is_modified = true;
                    this.gp_course_is_set = true;
                    return;
                }
                else if ( name.equals("gp_plan") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_plan' must be String: '"+value+"' is not." );
                    this.gp_plan = (String) value;
                    if (this.gp_plan_is_set)
                        this.gp_plan_is_modified = true;
                    this.gp_plan_is_set = true;
                    return;
                }
                else if ( name.equals("gp_target_figure") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_target_figure' must be String: '"+value+"' is not." );
                    this.gp_target_figure = (String) value;
                    if (this.gp_target_figure_is_set)
                        this.gp_target_figure_is_modified = true;
                    this.gp_target_figure_is_set = true;
                    return;
                }
                else if ( name.equals("gp_target_year") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_target_year' must be String: '"+value+"' is not." );
                    this.gp_target_year = (String) value;
                    if (this.gp_target_year_is_set)
                        this.gp_target_year_is_modified = true;
                    this.gp_target_year_is_set = true;
                    return;
                }
                else if ( name.equals("gp_target_month") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_target_month' must be String: '"+value+"' is not." );
                    this.gp_target_month = (String) value;
                    if (this.gp_target_month_is_set)
                        this.gp_target_month_is_modified = true;
                    this.gp_target_month_is_set = true;
                    return;
                }
                else if ( name.equals("gp_installment_figure") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_installment_figure' must be String: '"+value+"' is not." );
                    this.gp_installment_figure = (String) value;
                    if (this.gp_installment_figure_is_set)
                        this.gp_installment_figure_is_modified = true;
                    this.gp_installment_figure_is_set = true;
                    return;
                }
                else if ( name.equals("gp_deposit_cycle") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_deposit_cycle' must be String: '"+value+"' is not." );
                    this.gp_deposit_cycle = (String) value;
                    if (this.gp_deposit_cycle_is_set)
                        this.gp_deposit_cycle_is_modified = true;
                    this.gp_deposit_cycle_is_set = true;
                    return;
                }
                else if ( name.equals("gp_payment_root") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_payment_root' must be String: '"+value+"' is not." );
                    this.gp_payment_root = (String) value;
                    if (this.gp_payment_root_is_set)
                        this.gp_payment_root_is_modified = true;
                    this.gp_payment_root_is_set = true;
                    return;
                }
                else if ( name.equals("gp_reinvest_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_reinvest_div' must be String: '"+value+"' is not." );
                    this.gp_reinvest_div = (String) value;
                    if (this.gp_reinvest_div_is_set)
                        this.gp_reinvest_div_is_modified = true;
                    this.gp_reinvest_div_is_set = true;
                    return;
                }
                else if ( name.equals("gp_tax_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_tax_div' must be String: '"+value+"' is not." );
                    this.gp_tax_div = (String) value;
                    if (this.gp_tax_div_is_set)
                        this.gp_tax_div_is_modified = true;
                    this.gp_tax_div_is_set = true;
                    return;
                }
                else if ( name.equals("gp_taxfree_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_taxfree_limit' must be String: '"+value+"' is not." );
                    this.gp_taxfree_limit = (String) value;
                    if (this.gp_taxfree_limit_is_set)
                        this.gp_taxfree_limit_is_modified = true;
                    this.gp_taxfree_limit_is_set = true;
                    return;
                }
                else if ( name.equals("gp_special_taxfree_limit") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_special_taxfree_limit' must be String: '"+value+"' is not." );
                    this.gp_special_taxfree_limit = (String) value;
                    if (this.gp_special_taxfree_limit_is_set)
                        this.gp_special_taxfree_limit_is_modified = true;
                    this.gp_special_taxfree_limit_is_set = true;
                    return;
                }
                else if ( name.equals("gp_subscr_summary") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_subscr_summary' must be String: '"+value+"' is not." );
                    this.gp_subscr_summary = (String) value;
                    if (this.gp_subscr_summary_is_set)
                        this.gp_subscr_summary_is_modified = true;
                    this.gp_subscr_summary_is_set = true;
                    return;
                }
                else if ( name.equals("gp_product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_product_code' must be String: '"+value+"' is not." );
                    this.gp_product_code = (String) value;
                    if (this.gp_product_code_is_set)
                        this.gp_product_code_is_modified = true;
                    this.gp_product_code_is_set = true;
                    return;
                }
                else if ( name.equals("gp_mortgage_customer") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_mortgage_customer' must be String: '"+value+"' is not." );
                    this.gp_mortgage_customer = (String) value;
                    if (this.gp_mortgage_customer_is_set)
                        this.gp_mortgage_customer_is_modified = true;
                    this.gp_mortgage_customer_is_set = true;
                    return;
                }
                else if ( name.equals("gp_mix_customer") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_mix_customer' must be String: '"+value+"' is not." );
                    this.gp_mix_customer = (String) value;
                    if (this.gp_mix_customer_is_set)
                        this.gp_mix_customer_is_modified = true;
                    this.gp_mix_customer_is_set = true;
                    return;
                }
                else if ( name.equals("gp_contract") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_contract' must be String: '"+value+"' is not." );
                    this.gp_contract = (String) value;
                    if (this.gp_contract_is_set)
                        this.gp_contract_is_modified = true;
                    this.gp_contract_is_set = true;
                    return;
                }
                break;
            case 'h':
                if ( name.equals("householder") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'householder' must be String: '"+value+"' is not." );
                    this.householder = (String) value;
                    if (this.householder_is_set)
                        this.householder_is_modified = true;
                    this.householder_is_set = true;
                    return;
                }
                else if ( name.equals("householder_kana") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'householder_kana' must be String: '"+value+"' is not." );
                    this.householder_kana = (String) value;
                    if (this.householder_kana_is_set)
                        this.householder_kana_is_modified = true;
                    this.householder_kana_is_set = true;
                    return;
                }
                else if ( name.equals("householder_occupation_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'householder_occupation_div' must be String: '"+value+"' is not." );
                    this.householder_occupation_div = (String) value;
                    if (this.householder_occupation_div_is_set)
                        this.householder_occupation_div_is_modified = true;
                    this.householder_occupation_div_is_set = true;
                    return;
                }
                else if ( name.equals("householder_office") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'householder_office' must be String: '"+value+"' is not." );
                    this.householder_office = (String) value;
                    if (this.householder_office_is_set)
                        this.householder_office_is_modified = true;
                    this.householder_office_is_set = true;
                    return;
                }
                else if ( name.equals("householder_office_address") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'householder_office_address' must be String: '"+value+"' is not." );
                    this.householder_office_address = (String) value;
                    if (this.householder_office_address_is_set)
                        this.householder_office_address_is_modified = true;
                    this.householder_office_address_is_set = true;
                    return;
                }
                else if ( name.equals("householder_department") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'householder_department' must be String: '"+value+"' is not." );
                    this.householder_department = (String) value;
                    if (this.householder_department_is_set)
                        this.householder_department_is_modified = true;
                    this.householder_department_is_set = true;
                    return;
                }
                else if ( name.equals("householder_office_tel") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'householder_office_tel' must be String: '"+value+"' is not." );
                    this.householder_office_tel = (String) value;
                    if (this.householder_office_tel_is_set)
                        this.householder_office_tel_is_modified = true;
                    this.householder_office_tel_is_set = true;
                    return;
                }
                else if ( name.equals("householder_office_fax") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'householder_office_fax' must be String: '"+value+"' is not." );
                    this.householder_office_fax = (String) value;
                    if (this.householder_office_fax_is_set)
                        this.householder_office_fax_is_modified = true;
                    this.householder_office_fax_is_set = true;
                    return;
                }
                else if ( name.equals("householder_post") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'householder_post' must be String: '"+value+"' is not." );
                    this.householder_post = (String) value;
                    if (this.householder_post_is_set)
                        this.householder_post_is_modified = true;
                    this.householder_post_is_set = true;
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
                else if ( name.equals("infomation_claim_datetime") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'infomation_claim_datetime' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.infomation_claim_datetime = (java.sql.Timestamp) value;
                    if (this.infomation_claim_datetime_is_set)
                        this.infomation_claim_datetime_is_modified = true;
                    this.infomation_claim_datetime_is_set = true;
                    return;
                }
                else if ( name.equals("initial_password") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'initial_password' must be String: '"+value+"' is not." );
                    this.initial_password = (String) value;
                    if (this.initial_password_is_set)
                        this.initial_password_is_modified = true;
                    this.initial_password_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag_equity") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'interest_flag_equity' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.interest_flag_equity = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.interest_flag_equity_is_set)
                        this.interest_flag_equity_is_modified = true;
                    this.interest_flag_equity_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag_ministock") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'interest_flag_ministock' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.interest_flag_ministock = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.interest_flag_ministock_is_set)
                        this.interest_flag_ministock_is_modified = true;
                    this.interest_flag_ministock_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag_margin") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'interest_flag_margin' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.interest_flag_margin = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.interest_flag_margin_is_set)
                        this.interest_flag_margin_is_modified = true;
                    this.interest_flag_margin_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag_bond") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'interest_flag_bond' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.interest_flag_bond = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.interest_flag_bond_is_set)
                        this.interest_flag_bond_is_modified = true;
                    this.interest_flag_bond_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag_fund") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'interest_flag_fund' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.interest_flag_fund = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.interest_flag_fund_is_set)
                        this.interest_flag_fund_is_modified = true;
                    this.interest_flag_fund_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag_fo") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'interest_flag_fo' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.interest_flag_fo = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.interest_flag_fo_is_set)
                        this.interest_flag_fo_is_modified = true;
                    this.interest_flag_fo_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag_f_equity") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'interest_flag_f_equity' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.interest_flag_f_equity = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.interest_flag_f_equity_is_set)
                        this.interest_flag_f_equity_is_modified = true;
                    this.interest_flag_f_equity_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag_etc") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'interest_flag_etc' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.interest_flag_etc = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.interest_flag_etc_is_set)
                        this.interest_flag_etc_is_modified = true;
                    this.interest_flag_etc_is_set = true;
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
                else if ( name.equals("id_confirm_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'id_confirm_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.id_confirm_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.id_confirm_flag_is_set)
                        this.id_confirm_flag_is_modified = true;
                    this.id_confirm_flag_is_set = true;
                    return;
                }
                else if ( name.equals("id_confirm_doc_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'id_confirm_doc_div' must be String: '"+value+"' is not." );
                    this.id_confirm_doc_div = (String) value;
                    if (this.id_confirm_doc_div_is_set)
                        this.id_confirm_doc_div_is_modified = true;
                    this.id_confirm_doc_div_is_set = true;
                    return;
                }
                else if ( name.equals("id_confirm_doc_etc") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'id_confirm_doc_etc' must be String: '"+value+"' is not." );
                    this.id_confirm_doc_etc = (String) value;
                    if (this.id_confirm_doc_etc_is_set)
                        this.id_confirm_doc_etc_is_modified = true;
                    this.id_confirm_doc_etc_is_set = true;
                    return;
                }
                else if ( name.equals("insider_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'insider_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.insider_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.insider_flag_is_set)
                        this.insider_flag_is_modified = true;
                    this.insider_flag_is_set = true;
                    return;
                }
                else if ( name.equals("insider_voucher_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'insider_voucher_div' must be String: '"+value+"' is not." );
                    this.insider_voucher_div = (String) value;
                    if (this.insider_voucher_div_is_set)
                        this.insider_voucher_div_is_modified = true;
                    this.insider_voucher_div_is_set = true;
                    return;
                }
                else if ( name.equals("insider_product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'insider_product_code' must be String: '"+value+"' is not." );
                    this.insider_product_code = (String) value;
                    if (this.insider_product_code_is_set)
                        this.insider_product_code_is_modified = true;
                    this.insider_product_code_is_set = true;
                    return;
                }
                else if ( name.equals("insider_relation_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'insider_relation_div' must be String: '"+value+"' is not." );
                    this.insider_relation_div = (String) value;
                    if (this.insider_relation_div_is_set)
                        this.insider_relation_div_is_modified = true;
                    this.insider_relation_div_is_set = true;
                    return;
                }
                else if ( name.equals("insider_officer_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'insider_officer_name' must be String: '"+value+"' is not." );
                    this.insider_officer_name = (String) value;
                    if (this.insider_officer_name_is_set)
                        this.insider_officer_name_is_modified = true;
                    this.insider_officer_name_is_set = true;
                    return;
                }
                else if ( name.equals("insider_post_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'insider_post_code' must be String: '"+value+"' is not." );
                    this.insider_post_code = (String) value;
                    if (this.insider_post_code_is_set)
                        this.insider_post_code_is_modified = true;
                    this.insider_post_code_is_set = true;
                    return;
                }
                else if ( name.equals("insider_post_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'insider_post_name' must be String: '"+value+"' is not." );
                    this.insider_post_name = (String) value;
                    if (this.insider_post_name_is_set)
                        this.insider_post_name_is_modified = true;
                    this.insider_post_name_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
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
                if ( name.equals("mobile") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mobile' must be String: '"+value+"' is not." );
                    this.mobile = (String) value;
                    if (this.mobile_is_set)
                        this.mobile_is_modified = true;
                    this.mobile_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_div' must be String: '"+value+"' is not." );
                    this.order_div = (String) value;
                    if (this.order_div_is_set)
                        this.order_div_is_modified = true;
                    this.order_div_is_set = true;
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
                else if ( name.equals("office") ) {
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
                else if ( name.equals("office_fax") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'office_fax' must be String: '"+value+"' is not." );
                    this.office_fax = (String) value;
                    if (this.office_fax_is_set)
                        this.office_fax_is_modified = true;
                    this.office_fax_is_set = true;
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
                else if ( name.equals("postal_save_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'postal_save_code' must be String: '"+value+"' is not." );
                    this.postal_save_code = (String) value;
                    if (this.postal_save_code_is_set)
                        this.postal_save_code_is_modified = true;
                    this.postal_save_code_is_set = true;
                    return;
                }
                else if ( name.equals("postal_save_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'postal_save_no' must be String: '"+value+"' is not." );
                    this.postal_save_no = (String) value;
                    if (this.postal_save_no_is_set)
                        this.postal_save_no_is_modified = true;
                    this.postal_save_no_is_set = true;
                    return;
                }
                else if ( name.equals("product_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_name' must be String: '"+value+"' is not." );
                    this.product_name = (String) value;
                    if (this.product_name_is_set)
                        this.product_name_is_modified = true;
                    this.product_name_is_set = true;
                    return;
                }
                else if ( name.equals("print_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'print_flag' must be String: '"+value+"' is not." );
                    this.print_flag = (String) value;
                    if (this.print_flag_is_set)
                        this.print_flag_is_modified = true;
                    this.print_flag_is_set = true;
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
                else if ( name.equals("real_name_voucher_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'real_name_voucher_div' must be String: '"+value+"' is not." );
                    this.real_name_voucher_div = (String) value;
                    if (this.real_name_voucher_div_is_set)
                        this.real_name_voucher_div_is_modified = true;
                    this.real_name_voucher_div_is_set = true;
                    return;
                }
                else if ( name.equals("real_name1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'real_name1' must be String: '"+value+"' is not." );
                    this.real_name1 = (String) value;
                    if (this.real_name1_is_set)
                        this.real_name1_is_modified = true;
                    this.real_name1_is_set = true;
                    return;
                }
                else if ( name.equals("real_name2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'real_name2' must be String: '"+value+"' is not." );
                    this.real_name2 = (String) value;
                    if (this.real_name2_is_set)
                        this.real_name2_is_modified = true;
                    this.real_name2_is_set = true;
                    return;
                }
                else if ( name.equals("receipt_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'receipt_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.receipt_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.receipt_flag_is_set)
                        this.receipt_flag_is_modified = true;
                    this.receipt_flag_is_set = true;
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
                else if ( name.equals("sex") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sex' must be String: '"+value+"' is not." );
                    this.sex = (String) value;
                    if (this.sex_is_set)
                        this.sex_is_modified = true;
                    this.sex_is_set = true;
                    return;
                }
                else if ( name.equals("special_acc") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'special_acc' must be String: '"+value+"' is not." );
                    this.special_acc = (String) value;
                    if (this.special_acc_is_set)
                        this.special_acc_is_modified = true;
                    this.special_acc_is_set = true;
                    return;
                }
                else if ( name.equals("special_acc_margin") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'special_acc_margin' must be String: '"+value+"' is not." );
                    this.special_acc_margin = (String) value;
                    if (this.special_acc_margin_is_set)
                        this.special_acc_margin_is_modified = true;
                    this.special_acc_margin_is_set = true;
                    return;
                }
                else if ( name.equals("send_zip_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'send_zip_code' must be String: '"+value+"' is not." );
                    this.send_zip_code = (String) value;
                    if (this.send_zip_code_is_set)
                        this.send_zip_code_is_modified = true;
                    this.send_zip_code_is_set = true;
                    return;
                }
                else if ( name.equals("send_address_line1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'send_address_line1' must be String: '"+value+"' is not." );
                    this.send_address_line1 = (String) value;
                    if (this.send_address_line1_is_set)
                        this.send_address_line1_is_modified = true;
                    this.send_address_line1_is_set = true;
                    return;
                }
                else if ( name.equals("send_address_line2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'send_address_line2' must be String: '"+value+"' is not." );
                    this.send_address_line2 = (String) value;
                    if (this.send_address_line2_is_set)
                        this.send_address_line2_is_modified = true;
                    this.send_address_line2_is_set = true;
                    return;
                }
                else if ( name.equals("send_address_line3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'send_address_line3' must be String: '"+value+"' is not." );
                    this.send_address_line3 = (String) value;
                    if (this.send_address_line3_is_set)
                        this.send_address_line3_is_modified = true;
                    this.send_address_line3_is_set = true;
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
                else if ( name.equals("stk_voucher_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stk_voucher_div' must be String: '"+value+"' is not." );
                    this.stk_voucher_div = (String) value;
                    if (this.stk_voucher_div_is_set)
                        this.stk_voucher_div_is_modified = true;
                    this.stk_voucher_div_is_set = true;
                    return;
                }
                else if ( name.equals("stk_taxation_tran_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stk_taxation_tran_div' must be String: '"+value+"' is not." );
                    this.stk_taxation_tran_div = (String) value;
                    if (this.stk_taxation_tran_div_is_set)
                        this.stk_taxation_tran_div_is_modified = true;
                    this.stk_taxation_tran_div_is_set = true;
                    return;
                }
                else if ( name.equals("stk_address_line_kana") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stk_address_line_kana' must be String: '"+value+"' is not." );
                    this.stk_address_line_kana = (String) value;
                    if (this.stk_address_line_kana_is_set)
                        this.stk_address_line_kana_is_modified = true;
                    this.stk_address_line_kana_is_set = true;
                    return;
                }
                else if ( name.equals("stk_transfer_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stk_transfer_div' must be String: '"+value+"' is not." );
                    this.stk_transfer_div = (String) value;
                    if (this.stk_transfer_div_is_set)
                        this.stk_transfer_div_is_modified = true;
                    this.stk_transfer_div_is_set = true;
                    return;
                }
                else if ( name.equals("stk_fin_institution_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stk_fin_institution_code' must be String: '"+value+"' is not." );
                    this.stk_fin_institution_code = (String) value;
                    if (this.stk_fin_institution_code_is_set)
                        this.stk_fin_institution_code_is_modified = true;
                    this.stk_fin_institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("stk_fin_branch_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stk_fin_branch_code' must be String: '"+value+"' is not." );
                    this.stk_fin_branch_code = (String) value;
                    if (this.stk_fin_branch_code_is_set)
                        this.stk_fin_branch_code_is_modified = true;
                    this.stk_fin_branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("stk_fin_save_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stk_fin_save_div' must be String: '"+value+"' is not." );
                    this.stk_fin_save_div = (String) value;
                    if (this.stk_fin_save_div_is_set)
                        this.stk_fin_save_div_is_modified = true;
                    this.stk_fin_save_div_is_set = true;
                    return;
                }
                else if ( name.equals("stk_fin_account_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stk_fin_account_no' must be String: '"+value+"' is not." );
                    this.stk_fin_account_no = (String) value;
                    if (this.stk_fin_account_no_is_set)
                        this.stk_fin_account_no_is_modified = true;
                    this.stk_fin_account_no_is_set = true;
                    return;
                }
                else if ( name.equals("status") ) {
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
                else if ( name.equals("transfer_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'transfer_div' must be String: '"+value+"' is not." );
                    this.transfer_div = (String) value;
                    if (this.transfer_div_is_set)
                        this.transfer_div_is_modified = true;
                    this.transfer_div_is_set = true;
                    return;
                }
                else if ( name.equals("trans_commission") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trans_commission' must be String: '"+value+"' is not." );
                    this.trans_commission = (String) value;
                    if (this.trans_commission_is_set)
                        this.trans_commission_is_modified = true;
                    this.trans_commission_is_set = true;
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
