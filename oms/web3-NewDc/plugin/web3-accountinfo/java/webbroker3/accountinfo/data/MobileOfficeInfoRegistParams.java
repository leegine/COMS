head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.17.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	MobileOfficeInfoRegistParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * mobile_office_info_registテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link MobileOfficeInfoRegistRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link MobileOfficeInfoRegistParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link MobileOfficeInfoRegistParams}が{@@link MobileOfficeInfoRegistRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MobileOfficeInfoRegistPK 
 * @@see MobileOfficeInfoRegistRow 
 */
public class MobileOfficeInfoRegistParams extends Params implements MobileOfficeInfoRegistRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "mobile_office_info_regist";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = MobileOfficeInfoRegistRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return MobileOfficeInfoRegistRow.TYPE;
  }


  /** 
   * <em>mobile_office_info_regist_id</em>カラムの値 
   */
  public  long  mobile_office_info_regist_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  long  branch_id;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>mobile</em>カラムの値 
   */
  public  String  mobile;

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
   * <em>regist_updater</em>カラムの値 
   */
  public  String  regist_updater;

  /** 
   * <em>decision_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  decision_flag;

  /** 
   * <em>decision</em>カラムの値 
   */
  public  Integer  decision;

  /** 
   * <em>decision_updater</em>カラムの値 
   */
  public  String  decision_updater;

  /** 
   * <em>decision_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  decision_timestamp;

  /** 
   * <em>delete_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  delete_flag;

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
   * <em>contact_priority1</em>カラムの値 
   */
  public  String  contact_priority1;

  /** 
   * <em>contact_priority2</em>カラムの値 
   */
  public  String  contact_priority2;

  /** 
   * <em>contact_priority3</em>カラムの値 
   */
  public  String  contact_priority3;

  /** 
   * <em>real_name1</em>カラムの値 
   */
  public  String  real_name1;

  /** 
   * <em>real_name2</em>カラムの値 
   */
  public  String  real_name2;

  /** 
   * <em>occupation_div</em>カラムの値 
   */
  public  String  occupation_div;

  /** 
   * <em>department</em>カラムの値 
   */
  public  String  department;

  /** 
   * <em>nationality</em>カラムの値 
   */
  public  String  nationality;

  /** 
   * <em>nationality_other</em>カラムの値 
   */
  public  String  nationality_other;

  /** 
   * <em>represent_family_name</em>カラムの値 
   */
  public  String  represent_family_name;

  /** 
   * <em>represent_given_name</em>カラムの値 
   */
  public  String  represent_given_name;

  /** 
   * <em>represent_family_name_alt1</em>カラムの値 
   */
  public  String  represent_family_name_alt1;

  /** 
   * <em>represent_given_name_alt1</em>カラムの値 
   */
  public  String  represent_given_name_alt1;

  /** 
   * <em>represent_power</em>カラムの値 
   */
  public  String  represent_power;

  /** 
   * <em>director_family_name</em>カラムの値 
   */
  public  String  director_family_name;

  /** 
   * <em>director_given_name</em>カラムの値 
   */
  public  String  director_given_name;

  /** 
   * <em>director_family_name_alt1</em>カラムの値 
   */
  public  String  director_family_name_alt1;

  /** 
   * <em>director_given_name_alt1</em>カラムの値 
   */
  public  String  director_given_name_alt1;

  /** 
   * <em>director_department</em>カラムの値 
   */
  public  String  director_department;

  /** 
   * <em>director_post</em>カラムの値 
   */
  public  String  director_post;

  /** 
   * <em>director_zip_code</em>カラムの値 
   */
  public  String  director_zip_code;

  /** 
   * <em>director_address1</em>カラムの値 
   */
  public  String  director_address1;

  /** 
   * <em>director_address2</em>カラムの値 
   */
  public  String  director_address2;

  /** 
   * <em>director_address3</em>カラムの値 
   */
  public  String  director_address3;

  /** 
   * <em>director_era_born</em>カラムの値 
   */
  public  String  director_era_born;

  /** 
   * <em>director_born_date</em>カラムの値 
   */
  public  String  director_born_date;

  /** 
   * <em>director_corp_telephone</em>カラムの値 
   */
  public  String  director_corp_telephone;

  /** 
   * <em>other_contact</em>カラムの値 
   */
  public  String  other_contact;

  /** 
   * <em>fax</em>カラムの値 
   */
  public  String  fax;

  /** 
   * <em>annual_income_div</em>カラムの値 
   */
  public  String  annual_income_div;

  /** 
   * <em>asset_value_div</em>カラムの値 
   */
  public  String  asset_value_div;

  /** 
   * <em>fund_budget_amount_div</em>カラムの値 
   */
  public  String  fund_budget_amount_div;

  /** 
   * <em>invest_purpose_div</em>カラムの値 
   */
  public  String  invest_purpose_div;

  /** 
   * <em>invest_plan_period_div</em>カラムの値 
   */
  public  String  invest_plan_period_div;

  /** 
   * <em>experience_flag1</em>カラムの値 
   */
  public  Integer  experience_flag1;

  /** 
   * <em>experience_flag2</em>カラムの値 
   */
  public  Integer  experience_flag2;

  /** 
   * <em>experience_flag3</em>カラムの値 
   */
  public  Integer  experience_flag3;

  /** 
   * <em>experience_flag4</em>カラムの値 
   */
  public  Integer  experience_flag4;

  /** 
   * <em>experience_flag5</em>カラムの値 
   */
  public  Integer  experience_flag5;

  /** 
   * <em>experience_flag6</em>カラムの値 
   */
  public  Integer  experience_flag6;

  /** 
   * <em>experience_flag7</em>カラムの値 
   */
  public  Integer  experience_flag7;

  /** 
   * <em>experience_flag8</em>カラムの値 
   */
  public  Integer  experience_flag8;

  /** 
   * <em>experience_flag9</em>カラムの値 
   */
  public  Integer  experience_flag9;

  /** 
   * <em>experience_flag10</em>カラムの値 
   */
  public  Integer  experience_flag10;

  /** 
   * <em>experience_div1</em>カラムの値 
   */
  public  String  experience_div1;

  /** 
   * <em>experience_div2</em>カラムの値 
   */
  public  String  experience_div2;

  /** 
   * <em>experience_div3</em>カラムの値 
   */
  public  String  experience_div3;

  /** 
   * <em>experience_div4</em>カラムの値 
   */
  public  String  experience_div4;

  /** 
   * <em>experience_div5</em>カラムの値 
   */
  public  String  experience_div5;

  /** 
   * <em>experience_div6</em>カラムの値 
   */
  public  String  experience_div6;

  /** 
   * <em>experience_div7</em>カラムの値 
   */
  public  String  experience_div7;

  /** 
   * <em>experience_div8</em>カラムの値 
   */
  public  String  experience_div8;

  /** 
   * <em>experience_div9</em>カラムの値 
   */
  public  String  experience_div9;

  /** 
   * <em>experience_div10</em>カラムの値 
   */
  public  String  experience_div10;

  /** 
   * <em>interest_flag1</em>カラムの値 
   */
  public  Integer  interest_flag1;

  /** 
   * <em>interest_flag2</em>カラムの値 
   */
  public  Integer  interest_flag2;

  /** 
   * <em>interest_flag3</em>カラムの値 
   */
  public  Integer  interest_flag3;

  /** 
   * <em>interest_flag4</em>カラムの値 
   */
  public  Integer  interest_flag4;

  /** 
   * <em>interest_flag5</em>カラムの値 
   */
  public  Integer  interest_flag5;

  /** 
   * <em>interest_flag6</em>カラムの値 
   */
  public  Integer  interest_flag6;

  /** 
   * <em>interest_flag7</em>カラムの値 
   */
  public  Integer  interest_flag7;

  /** 
   * <em>interest_flag8</em>カラムの値 
   */
  public  Integer  interest_flag8;

  /** 
   * <em>interest_flag9</em>カラムの値 
   */
  public  Integer  interest_flag9;

  /** 
   * <em>interest_flag10</em>カラムの値 
   */
  public  Integer  interest_flag10;

  /** 
   * <em>appli_motivat_div</em>カラムの値 
   */
  public  String  appli_motivat_div;

  /** 
   * <em>appli_motivat_div_detail</em>カラムの値 
   */
  public  String  appli_motivat_div_detail;

  /** 
   * <em>use_institution_div</em>カラムの値 
   */
  public  String  use_institution_div;

  /** 
   * <em>internet_trade_div</em>カラムの値 
   */
  public  String  internet_trade_div;

  /** 
   * <em>introduce_branch_code</em>カラムの値 
   */
  public  String  introduce_branch_code;

  /** 
   * <em>accept_status</em>カラムの値 
   */
  public  String  accept_status;

  private boolean mobile_office_info_regist_id_is_set = false;
  private boolean mobile_office_info_regist_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean mobile_is_set = false;
  private boolean mobile_is_modified = false;
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
  private boolean regist_updater_is_set = false;
  private boolean regist_updater_is_modified = false;
  private boolean decision_flag_is_set = false;
  private boolean decision_flag_is_modified = false;
  private boolean decision_is_set = false;
  private boolean decision_is_modified = false;
  private boolean decision_updater_is_set = false;
  private boolean decision_updater_is_modified = false;
  private boolean decision_timestamp_is_set = false;
  private boolean decision_timestamp_is_modified = false;
  private boolean delete_flag_is_set = false;
  private boolean delete_flag_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean contact_priority1_is_set = false;
  private boolean contact_priority1_is_modified = false;
  private boolean contact_priority2_is_set = false;
  private boolean contact_priority2_is_modified = false;
  private boolean contact_priority3_is_set = false;
  private boolean contact_priority3_is_modified = false;
  private boolean real_name1_is_set = false;
  private boolean real_name1_is_modified = false;
  private boolean real_name2_is_set = false;
  private boolean real_name2_is_modified = false;
  private boolean occupation_div_is_set = false;
  private boolean occupation_div_is_modified = false;
  private boolean department_is_set = false;
  private boolean department_is_modified = false;
  private boolean nationality_is_set = false;
  private boolean nationality_is_modified = false;
  private boolean nationality_other_is_set = false;
  private boolean nationality_other_is_modified = false;
  private boolean represent_family_name_is_set = false;
  private boolean represent_family_name_is_modified = false;
  private boolean represent_given_name_is_set = false;
  private boolean represent_given_name_is_modified = false;
  private boolean represent_family_name_alt1_is_set = false;
  private boolean represent_family_name_alt1_is_modified = false;
  private boolean represent_given_name_alt1_is_set = false;
  private boolean represent_given_name_alt1_is_modified = false;
  private boolean represent_power_is_set = false;
  private boolean represent_power_is_modified = false;
  private boolean director_family_name_is_set = false;
  private boolean director_family_name_is_modified = false;
  private boolean director_given_name_is_set = false;
  private boolean director_given_name_is_modified = false;
  private boolean director_family_name_alt1_is_set = false;
  private boolean director_family_name_alt1_is_modified = false;
  private boolean director_given_name_alt1_is_set = false;
  private boolean director_given_name_alt1_is_modified = false;
  private boolean director_department_is_set = false;
  private boolean director_department_is_modified = false;
  private boolean director_post_is_set = false;
  private boolean director_post_is_modified = false;
  private boolean director_zip_code_is_set = false;
  private boolean director_zip_code_is_modified = false;
  private boolean director_address1_is_set = false;
  private boolean director_address1_is_modified = false;
  private boolean director_address2_is_set = false;
  private boolean director_address2_is_modified = false;
  private boolean director_address3_is_set = false;
  private boolean director_address3_is_modified = false;
  private boolean director_era_born_is_set = false;
  private boolean director_era_born_is_modified = false;
  private boolean director_born_date_is_set = false;
  private boolean director_born_date_is_modified = false;
  private boolean director_corp_telephone_is_set = false;
  private boolean director_corp_telephone_is_modified = false;
  private boolean other_contact_is_set = false;
  private boolean other_contact_is_modified = false;
  private boolean fax_is_set = false;
  private boolean fax_is_modified = false;
  private boolean annual_income_div_is_set = false;
  private boolean annual_income_div_is_modified = false;
  private boolean asset_value_div_is_set = false;
  private boolean asset_value_div_is_modified = false;
  private boolean fund_budget_amount_div_is_set = false;
  private boolean fund_budget_amount_div_is_modified = false;
  private boolean invest_purpose_div_is_set = false;
  private boolean invest_purpose_div_is_modified = false;
  private boolean invest_plan_period_div_is_set = false;
  private boolean invest_plan_period_div_is_modified = false;
  private boolean experience_flag1_is_set = false;
  private boolean experience_flag1_is_modified = false;
  private boolean experience_flag2_is_set = false;
  private boolean experience_flag2_is_modified = false;
  private boolean experience_flag3_is_set = false;
  private boolean experience_flag3_is_modified = false;
  private boolean experience_flag4_is_set = false;
  private boolean experience_flag4_is_modified = false;
  private boolean experience_flag5_is_set = false;
  private boolean experience_flag5_is_modified = false;
  private boolean experience_flag6_is_set = false;
  private boolean experience_flag6_is_modified = false;
  private boolean experience_flag7_is_set = false;
  private boolean experience_flag7_is_modified = false;
  private boolean experience_flag8_is_set = false;
  private boolean experience_flag8_is_modified = false;
  private boolean experience_flag9_is_set = false;
  private boolean experience_flag9_is_modified = false;
  private boolean experience_flag10_is_set = false;
  private boolean experience_flag10_is_modified = false;
  private boolean experience_div1_is_set = false;
  private boolean experience_div1_is_modified = false;
  private boolean experience_div2_is_set = false;
  private boolean experience_div2_is_modified = false;
  private boolean experience_div3_is_set = false;
  private boolean experience_div3_is_modified = false;
  private boolean experience_div4_is_set = false;
  private boolean experience_div4_is_modified = false;
  private boolean experience_div5_is_set = false;
  private boolean experience_div5_is_modified = false;
  private boolean experience_div6_is_set = false;
  private boolean experience_div6_is_modified = false;
  private boolean experience_div7_is_set = false;
  private boolean experience_div7_is_modified = false;
  private boolean experience_div8_is_set = false;
  private boolean experience_div8_is_modified = false;
  private boolean experience_div9_is_set = false;
  private boolean experience_div9_is_modified = false;
  private boolean experience_div10_is_set = false;
  private boolean experience_div10_is_modified = false;
  private boolean interest_flag1_is_set = false;
  private boolean interest_flag1_is_modified = false;
  private boolean interest_flag2_is_set = false;
  private boolean interest_flag2_is_modified = false;
  private boolean interest_flag3_is_set = false;
  private boolean interest_flag3_is_modified = false;
  private boolean interest_flag4_is_set = false;
  private boolean interest_flag4_is_modified = false;
  private boolean interest_flag5_is_set = false;
  private boolean interest_flag5_is_modified = false;
  private boolean interest_flag6_is_set = false;
  private boolean interest_flag6_is_modified = false;
  private boolean interest_flag7_is_set = false;
  private boolean interest_flag7_is_modified = false;
  private boolean interest_flag8_is_set = false;
  private boolean interest_flag8_is_modified = false;
  private boolean interest_flag9_is_set = false;
  private boolean interest_flag9_is_modified = false;
  private boolean interest_flag10_is_set = false;
  private boolean interest_flag10_is_modified = false;
  private boolean appli_motivat_div_is_set = false;
  private boolean appli_motivat_div_is_modified = false;
  private boolean appli_motivat_div_detail_is_set = false;
  private boolean appli_motivat_div_detail_is_modified = false;
  private boolean use_institution_div_is_set = false;
  private boolean use_institution_div_is_modified = false;
  private boolean internet_trade_div_is_set = false;
  private boolean internet_trade_div_is_modified = false;
  private boolean introduce_branch_code_is_set = false;
  private boolean introduce_branch_code_is_modified = false;
  private boolean accept_status_is_set = false;
  private boolean accept_status_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "mobile_office_info_regist_id=" + mobile_office_info_regist_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_id=" +branch_id
      + "," + "account_id=" +account_id
      + "," + "account_code=" +account_code
      + "," + "mobile=" +mobile
      + "," + "office=" +office
      + "," + "office_zip_code=" +office_zip_code
      + "," + "office_address=" +office_address
      + "," + "office_telephone=" +office_telephone
      + "," + "post=" +post
      + "," + "regist_updater=" +regist_updater
      + "," + "decision_flag=" +decision_flag
      + "," + "decision=" +decision
      + "," + "decision_updater=" +decision_updater
      + "," + "decision_timestamp=" +decision_timestamp
      + "," + "delete_flag=" +delete_flag
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "contact_priority1=" +contact_priority1
      + "," + "contact_priority2=" +contact_priority2
      + "," + "contact_priority3=" +contact_priority3
      + "," + "real_name1=" +real_name1
      + "," + "real_name2=" +real_name2
      + "," + "occupation_div=" +occupation_div
      + "," + "department=" +department
      + "," + "nationality=" +nationality
      + "," + "nationality_other=" +nationality_other
      + "," + "represent_family_name=" +represent_family_name
      + "," + "represent_given_name=" +represent_given_name
      + "," + "represent_family_name_alt1=" +represent_family_name_alt1
      + "," + "represent_given_name_alt1=" +represent_given_name_alt1
      + "," + "represent_power=" +represent_power
      + "," + "director_family_name=" +director_family_name
      + "," + "director_given_name=" +director_given_name
      + "," + "director_family_name_alt1=" +director_family_name_alt1
      + "," + "director_given_name_alt1=" +director_given_name_alt1
      + "," + "director_department=" +director_department
      + "," + "director_post=" +director_post
      + "," + "director_zip_code=" +director_zip_code
      + "," + "director_address1=" +director_address1
      + "," + "director_address2=" +director_address2
      + "," + "director_address3=" +director_address3
      + "," + "director_era_born=" +director_era_born
      + "," + "director_born_date=" +director_born_date
      + "," + "director_corp_telephone=" +director_corp_telephone
      + "," + "other_contact=" +other_contact
      + "," + "fax=" +fax
      + "," + "annual_income_div=" +annual_income_div
      + "," + "asset_value_div=" +asset_value_div
      + "," + "fund_budget_amount_div=" +fund_budget_amount_div
      + "," + "invest_purpose_div=" +invest_purpose_div
      + "," + "invest_plan_period_div=" +invest_plan_period_div
      + "," + "experience_flag1=" +experience_flag1
      + "," + "experience_flag2=" +experience_flag2
      + "," + "experience_flag3=" +experience_flag3
      + "," + "experience_flag4=" +experience_flag4
      + "," + "experience_flag5=" +experience_flag5
      + "," + "experience_flag6=" +experience_flag6
      + "," + "experience_flag7=" +experience_flag7
      + "," + "experience_flag8=" +experience_flag8
      + "," + "experience_flag9=" +experience_flag9
      + "," + "experience_flag10=" +experience_flag10
      + "," + "experience_div1=" +experience_div1
      + "," + "experience_div2=" +experience_div2
      + "," + "experience_div3=" +experience_div3
      + "," + "experience_div4=" +experience_div4
      + "," + "experience_div5=" +experience_div5
      + "," + "experience_div6=" +experience_div6
      + "," + "experience_div7=" +experience_div7
      + "," + "experience_div8=" +experience_div8
      + "," + "experience_div9=" +experience_div9
      + "," + "experience_div10=" +experience_div10
      + "," + "interest_flag1=" +interest_flag1
      + "," + "interest_flag2=" +interest_flag2
      + "," + "interest_flag3=" +interest_flag3
      + "," + "interest_flag4=" +interest_flag4
      + "," + "interest_flag5=" +interest_flag5
      + "," + "interest_flag6=" +interest_flag6
      + "," + "interest_flag7=" +interest_flag7
      + "," + "interest_flag8=" +interest_flag8
      + "," + "interest_flag9=" +interest_flag9
      + "," + "interest_flag10=" +interest_flag10
      + "," + "appli_motivat_div=" +appli_motivat_div
      + "," + "appli_motivat_div_detail=" +appli_motivat_div_detail
      + "," + "use_institution_div=" +use_institution_div
      + "," + "internet_trade_div=" +internet_trade_div
      + "," + "introduce_branch_code=" +introduce_branch_code
      + "," + "accept_status=" +accept_status
      + "}";
  }


  /** 
   * 値が未設定のMobileOfficeInfoRegistParamsオブジェクトを作成します。 
   */
  public MobileOfficeInfoRegistParams() {
    mobile_office_info_regist_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のMobileOfficeInfoRegistRowオブジェクトの値を利用してMobileOfficeInfoRegistParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するMobileOfficeInfoRegistRowオブジェクト 
   */
  public MobileOfficeInfoRegistParams( MobileOfficeInfoRegistRow p_row )
  {
    mobile_office_info_regist_id = p_row.getMobileOfficeInfoRegistId();
    mobile_office_info_regist_id_is_set = p_row.getMobileOfficeInfoRegistIdIsSet();
    mobile_office_info_regist_id_is_modified = p_row.getMobileOfficeInfoRegistIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    mobile = p_row.getMobile();
    mobile_is_set = p_row.getMobileIsSet();
    mobile_is_modified = p_row.getMobileIsModified();
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
    regist_updater = p_row.getRegistUpdater();
    regist_updater_is_set = p_row.getRegistUpdaterIsSet();
    regist_updater_is_modified = p_row.getRegistUpdaterIsModified();
    decision_flag = p_row.getDecisionFlag();
    decision_flag_is_set = p_row.getDecisionFlagIsSet();
    decision_flag_is_modified = p_row.getDecisionFlagIsModified();
    if ( !p_row.getDecisionIsNull() )
      decision = new Integer( p_row.getDecision() );
    decision_is_set = p_row.getDecisionIsSet();
    decision_is_modified = p_row.getDecisionIsModified();
    decision_updater = p_row.getDecisionUpdater();
    decision_updater_is_set = p_row.getDecisionUpdaterIsSet();
    decision_updater_is_modified = p_row.getDecisionUpdaterIsModified();
    decision_timestamp = p_row.getDecisionTimestamp();
    decision_timestamp_is_set = p_row.getDecisionTimestampIsSet();
    decision_timestamp_is_modified = p_row.getDecisionTimestampIsModified();
    delete_flag = p_row.getDeleteFlag();
    delete_flag_is_set = p_row.getDeleteFlagIsSet();
    delete_flag_is_modified = p_row.getDeleteFlagIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    contact_priority1 = p_row.getContactPriority1();
    contact_priority1_is_set = p_row.getContactPriority1IsSet();
    contact_priority1_is_modified = p_row.getContactPriority1IsModified();
    contact_priority2 = p_row.getContactPriority2();
    contact_priority2_is_set = p_row.getContactPriority2IsSet();
    contact_priority2_is_modified = p_row.getContactPriority2IsModified();
    contact_priority3 = p_row.getContactPriority3();
    contact_priority3_is_set = p_row.getContactPriority3IsSet();
    contact_priority3_is_modified = p_row.getContactPriority3IsModified();
    real_name1 = p_row.getRealName1();
    real_name1_is_set = p_row.getRealName1IsSet();
    real_name1_is_modified = p_row.getRealName1IsModified();
    real_name2 = p_row.getRealName2();
    real_name2_is_set = p_row.getRealName2IsSet();
    real_name2_is_modified = p_row.getRealName2IsModified();
    occupation_div = p_row.getOccupationDiv();
    occupation_div_is_set = p_row.getOccupationDivIsSet();
    occupation_div_is_modified = p_row.getOccupationDivIsModified();
    department = p_row.getDepartment();
    department_is_set = p_row.getDepartmentIsSet();
    department_is_modified = p_row.getDepartmentIsModified();
    nationality = p_row.getNationality();
    nationality_is_set = p_row.getNationalityIsSet();
    nationality_is_modified = p_row.getNationalityIsModified();
    nationality_other = p_row.getNationalityOther();
    nationality_other_is_set = p_row.getNationalityOtherIsSet();
    nationality_other_is_modified = p_row.getNationalityOtherIsModified();
    represent_family_name = p_row.getRepresentFamilyName();
    represent_family_name_is_set = p_row.getRepresentFamilyNameIsSet();
    represent_family_name_is_modified = p_row.getRepresentFamilyNameIsModified();
    represent_given_name = p_row.getRepresentGivenName();
    represent_given_name_is_set = p_row.getRepresentGivenNameIsSet();
    represent_given_name_is_modified = p_row.getRepresentGivenNameIsModified();
    represent_family_name_alt1 = p_row.getRepresentFamilyNameAlt1();
    represent_family_name_alt1_is_set = p_row.getRepresentFamilyNameAlt1IsSet();
    represent_family_name_alt1_is_modified = p_row.getRepresentFamilyNameAlt1IsModified();
    represent_given_name_alt1 = p_row.getRepresentGivenNameAlt1();
    represent_given_name_alt1_is_set = p_row.getRepresentGivenNameAlt1IsSet();
    represent_given_name_alt1_is_modified = p_row.getRepresentGivenNameAlt1IsModified();
    represent_power = p_row.getRepresentPower();
    represent_power_is_set = p_row.getRepresentPowerIsSet();
    represent_power_is_modified = p_row.getRepresentPowerIsModified();
    director_family_name = p_row.getDirectorFamilyName();
    director_family_name_is_set = p_row.getDirectorFamilyNameIsSet();
    director_family_name_is_modified = p_row.getDirectorFamilyNameIsModified();
    director_given_name = p_row.getDirectorGivenName();
    director_given_name_is_set = p_row.getDirectorGivenNameIsSet();
    director_given_name_is_modified = p_row.getDirectorGivenNameIsModified();
    director_family_name_alt1 = p_row.getDirectorFamilyNameAlt1();
    director_family_name_alt1_is_set = p_row.getDirectorFamilyNameAlt1IsSet();
    director_family_name_alt1_is_modified = p_row.getDirectorFamilyNameAlt1IsModified();
    director_given_name_alt1 = p_row.getDirectorGivenNameAlt1();
    director_given_name_alt1_is_set = p_row.getDirectorGivenNameAlt1IsSet();
    director_given_name_alt1_is_modified = p_row.getDirectorGivenNameAlt1IsModified();
    director_department = p_row.getDirectorDepartment();
    director_department_is_set = p_row.getDirectorDepartmentIsSet();
    director_department_is_modified = p_row.getDirectorDepartmentIsModified();
    director_post = p_row.getDirectorPost();
    director_post_is_set = p_row.getDirectorPostIsSet();
    director_post_is_modified = p_row.getDirectorPostIsModified();
    director_zip_code = p_row.getDirectorZipCode();
    director_zip_code_is_set = p_row.getDirectorZipCodeIsSet();
    director_zip_code_is_modified = p_row.getDirectorZipCodeIsModified();
    director_address1 = p_row.getDirectorAddress1();
    director_address1_is_set = p_row.getDirectorAddress1IsSet();
    director_address1_is_modified = p_row.getDirectorAddress1IsModified();
    director_address2 = p_row.getDirectorAddress2();
    director_address2_is_set = p_row.getDirectorAddress2IsSet();
    director_address2_is_modified = p_row.getDirectorAddress2IsModified();
    director_address3 = p_row.getDirectorAddress3();
    director_address3_is_set = p_row.getDirectorAddress3IsSet();
    director_address3_is_modified = p_row.getDirectorAddress3IsModified();
    director_era_born = p_row.getDirectorEraBorn();
    director_era_born_is_set = p_row.getDirectorEraBornIsSet();
    director_era_born_is_modified = p_row.getDirectorEraBornIsModified();
    director_born_date = p_row.getDirectorBornDate();
    director_born_date_is_set = p_row.getDirectorBornDateIsSet();
    director_born_date_is_modified = p_row.getDirectorBornDateIsModified();
    director_corp_telephone = p_row.getDirectorCorpTelephone();
    director_corp_telephone_is_set = p_row.getDirectorCorpTelephoneIsSet();
    director_corp_telephone_is_modified = p_row.getDirectorCorpTelephoneIsModified();
    other_contact = p_row.getOtherContact();
    other_contact_is_set = p_row.getOtherContactIsSet();
    other_contact_is_modified = p_row.getOtherContactIsModified();
    fax = p_row.getFax();
    fax_is_set = p_row.getFaxIsSet();
    fax_is_modified = p_row.getFaxIsModified();
    annual_income_div = p_row.getAnnualIncomeDiv();
    annual_income_div_is_set = p_row.getAnnualIncomeDivIsSet();
    annual_income_div_is_modified = p_row.getAnnualIncomeDivIsModified();
    asset_value_div = p_row.getAssetValueDiv();
    asset_value_div_is_set = p_row.getAssetValueDivIsSet();
    asset_value_div_is_modified = p_row.getAssetValueDivIsModified();
    fund_budget_amount_div = p_row.getFundBudgetAmountDiv();
    fund_budget_amount_div_is_set = p_row.getFundBudgetAmountDivIsSet();
    fund_budget_amount_div_is_modified = p_row.getFundBudgetAmountDivIsModified();
    invest_purpose_div = p_row.getInvestPurposeDiv();
    invest_purpose_div_is_set = p_row.getInvestPurposeDivIsSet();
    invest_purpose_div_is_modified = p_row.getInvestPurposeDivIsModified();
    invest_plan_period_div = p_row.getInvestPlanPeriodDiv();
    invest_plan_period_div_is_set = p_row.getInvestPlanPeriodDivIsSet();
    invest_plan_period_div_is_modified = p_row.getInvestPlanPeriodDivIsModified();
    if ( !p_row.getExperienceFlag1IsNull() )
      experience_flag1 = new Integer( p_row.getExperienceFlag1() );
    experience_flag1_is_set = p_row.getExperienceFlag1IsSet();
    experience_flag1_is_modified = p_row.getExperienceFlag1IsModified();
    if ( !p_row.getExperienceFlag2IsNull() )
      experience_flag2 = new Integer( p_row.getExperienceFlag2() );
    experience_flag2_is_set = p_row.getExperienceFlag2IsSet();
    experience_flag2_is_modified = p_row.getExperienceFlag2IsModified();
    if ( !p_row.getExperienceFlag3IsNull() )
      experience_flag3 = new Integer( p_row.getExperienceFlag3() );
    experience_flag3_is_set = p_row.getExperienceFlag3IsSet();
    experience_flag3_is_modified = p_row.getExperienceFlag3IsModified();
    if ( !p_row.getExperienceFlag4IsNull() )
      experience_flag4 = new Integer( p_row.getExperienceFlag4() );
    experience_flag4_is_set = p_row.getExperienceFlag4IsSet();
    experience_flag4_is_modified = p_row.getExperienceFlag4IsModified();
    if ( !p_row.getExperienceFlag5IsNull() )
      experience_flag5 = new Integer( p_row.getExperienceFlag5() );
    experience_flag5_is_set = p_row.getExperienceFlag5IsSet();
    experience_flag5_is_modified = p_row.getExperienceFlag5IsModified();
    if ( !p_row.getExperienceFlag6IsNull() )
      experience_flag6 = new Integer( p_row.getExperienceFlag6() );
    experience_flag6_is_set = p_row.getExperienceFlag6IsSet();
    experience_flag6_is_modified = p_row.getExperienceFlag6IsModified();
    if ( !p_row.getExperienceFlag7IsNull() )
      experience_flag7 = new Integer( p_row.getExperienceFlag7() );
    experience_flag7_is_set = p_row.getExperienceFlag7IsSet();
    experience_flag7_is_modified = p_row.getExperienceFlag7IsModified();
    if ( !p_row.getExperienceFlag8IsNull() )
      experience_flag8 = new Integer( p_row.getExperienceFlag8() );
    experience_flag8_is_set = p_row.getExperienceFlag8IsSet();
    experience_flag8_is_modified = p_row.getExperienceFlag8IsModified();
    if ( !p_row.getExperienceFlag9IsNull() )
      experience_flag9 = new Integer( p_row.getExperienceFlag9() );
    experience_flag9_is_set = p_row.getExperienceFlag9IsSet();
    experience_flag9_is_modified = p_row.getExperienceFlag9IsModified();
    if ( !p_row.getExperienceFlag10IsNull() )
      experience_flag10 = new Integer( p_row.getExperienceFlag10() );
    experience_flag10_is_set = p_row.getExperienceFlag10IsSet();
    experience_flag10_is_modified = p_row.getExperienceFlag10IsModified();
    experience_div1 = p_row.getExperienceDiv1();
    experience_div1_is_set = p_row.getExperienceDiv1IsSet();
    experience_div1_is_modified = p_row.getExperienceDiv1IsModified();
    experience_div2 = p_row.getExperienceDiv2();
    experience_div2_is_set = p_row.getExperienceDiv2IsSet();
    experience_div2_is_modified = p_row.getExperienceDiv2IsModified();
    experience_div3 = p_row.getExperienceDiv3();
    experience_div3_is_set = p_row.getExperienceDiv3IsSet();
    experience_div3_is_modified = p_row.getExperienceDiv3IsModified();
    experience_div4 = p_row.getExperienceDiv4();
    experience_div4_is_set = p_row.getExperienceDiv4IsSet();
    experience_div4_is_modified = p_row.getExperienceDiv4IsModified();
    experience_div5 = p_row.getExperienceDiv5();
    experience_div5_is_set = p_row.getExperienceDiv5IsSet();
    experience_div5_is_modified = p_row.getExperienceDiv5IsModified();
    experience_div6 = p_row.getExperienceDiv6();
    experience_div6_is_set = p_row.getExperienceDiv6IsSet();
    experience_div6_is_modified = p_row.getExperienceDiv6IsModified();
    experience_div7 = p_row.getExperienceDiv7();
    experience_div7_is_set = p_row.getExperienceDiv7IsSet();
    experience_div7_is_modified = p_row.getExperienceDiv7IsModified();
    experience_div8 = p_row.getExperienceDiv8();
    experience_div8_is_set = p_row.getExperienceDiv8IsSet();
    experience_div8_is_modified = p_row.getExperienceDiv8IsModified();
    experience_div9 = p_row.getExperienceDiv9();
    experience_div9_is_set = p_row.getExperienceDiv9IsSet();
    experience_div9_is_modified = p_row.getExperienceDiv9IsModified();
    experience_div10 = p_row.getExperienceDiv10();
    experience_div10_is_set = p_row.getExperienceDiv10IsSet();
    experience_div10_is_modified = p_row.getExperienceDiv10IsModified();
    if ( !p_row.getInterestFlag1IsNull() )
      interest_flag1 = new Integer( p_row.getInterestFlag1() );
    interest_flag1_is_set = p_row.getInterestFlag1IsSet();
    interest_flag1_is_modified = p_row.getInterestFlag1IsModified();
    if ( !p_row.getInterestFlag2IsNull() )
      interest_flag2 = new Integer( p_row.getInterestFlag2() );
    interest_flag2_is_set = p_row.getInterestFlag2IsSet();
    interest_flag2_is_modified = p_row.getInterestFlag2IsModified();
    if ( !p_row.getInterestFlag3IsNull() )
      interest_flag3 = new Integer( p_row.getInterestFlag3() );
    interest_flag3_is_set = p_row.getInterestFlag3IsSet();
    interest_flag3_is_modified = p_row.getInterestFlag3IsModified();
    if ( !p_row.getInterestFlag4IsNull() )
      interest_flag4 = new Integer( p_row.getInterestFlag4() );
    interest_flag4_is_set = p_row.getInterestFlag4IsSet();
    interest_flag4_is_modified = p_row.getInterestFlag4IsModified();
    if ( !p_row.getInterestFlag5IsNull() )
      interest_flag5 = new Integer( p_row.getInterestFlag5() );
    interest_flag5_is_set = p_row.getInterestFlag5IsSet();
    interest_flag5_is_modified = p_row.getInterestFlag5IsModified();
    if ( !p_row.getInterestFlag6IsNull() )
      interest_flag6 = new Integer( p_row.getInterestFlag6() );
    interest_flag6_is_set = p_row.getInterestFlag6IsSet();
    interest_flag6_is_modified = p_row.getInterestFlag6IsModified();
    if ( !p_row.getInterestFlag7IsNull() )
      interest_flag7 = new Integer( p_row.getInterestFlag7() );
    interest_flag7_is_set = p_row.getInterestFlag7IsSet();
    interest_flag7_is_modified = p_row.getInterestFlag7IsModified();
    if ( !p_row.getInterestFlag8IsNull() )
      interest_flag8 = new Integer( p_row.getInterestFlag8() );
    interest_flag8_is_set = p_row.getInterestFlag8IsSet();
    interest_flag8_is_modified = p_row.getInterestFlag8IsModified();
    if ( !p_row.getInterestFlag9IsNull() )
      interest_flag9 = new Integer( p_row.getInterestFlag9() );
    interest_flag9_is_set = p_row.getInterestFlag9IsSet();
    interest_flag9_is_modified = p_row.getInterestFlag9IsModified();
    if ( !p_row.getInterestFlag10IsNull() )
      interest_flag10 = new Integer( p_row.getInterestFlag10() );
    interest_flag10_is_set = p_row.getInterestFlag10IsSet();
    interest_flag10_is_modified = p_row.getInterestFlag10IsModified();
    appli_motivat_div = p_row.getAppliMotivatDiv();
    appli_motivat_div_is_set = p_row.getAppliMotivatDivIsSet();
    appli_motivat_div_is_modified = p_row.getAppliMotivatDivIsModified();
    appli_motivat_div_detail = p_row.getAppliMotivatDivDetail();
    appli_motivat_div_detail_is_set = p_row.getAppliMotivatDivDetailIsSet();
    appli_motivat_div_detail_is_modified = p_row.getAppliMotivatDivDetailIsModified();
    use_institution_div = p_row.getUseInstitutionDiv();
    use_institution_div_is_set = p_row.getUseInstitutionDivIsSet();
    use_institution_div_is_modified = p_row.getUseInstitutionDivIsModified();
    internet_trade_div = p_row.getInternetTradeDiv();
    internet_trade_div_is_set = p_row.getInternetTradeDivIsSet();
    internet_trade_div_is_modified = p_row.getInternetTradeDivIsModified();
    introduce_branch_code = p_row.getIntroduceBranchCode();
    introduce_branch_code_is_set = p_row.getIntroduceBranchCodeIsSet();
    introduce_branch_code_is_modified = p_row.getIntroduceBranchCodeIsModified();
    accept_status = p_row.getAcceptStatus();
    accept_status_is_set = p_row.getAcceptStatusIsSet();
    accept_status_is_modified = p_row.getAcceptStatusIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_id_is_set = true;
      branch_id_is_modified = true;
      account_id_is_set = true;
      account_id_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      mobile_is_set = true;
      mobile_is_modified = true;
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
      regist_updater_is_set = true;
      regist_updater_is_modified = true;
      decision_flag_is_set = true;
      decision_flag_is_modified = true;
      decision_is_set = true;
      decision_is_modified = true;
      decision_updater_is_set = true;
      decision_updater_is_modified = true;
      decision_timestamp_is_set = true;
      decision_timestamp_is_modified = true;
      delete_flag_is_set = true;
      delete_flag_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      contact_priority1_is_set = true;
      contact_priority1_is_modified = true;
      contact_priority2_is_set = true;
      contact_priority2_is_modified = true;
      contact_priority3_is_set = true;
      contact_priority3_is_modified = true;
      real_name1_is_set = true;
      real_name1_is_modified = true;
      real_name2_is_set = true;
      real_name2_is_modified = true;
      occupation_div_is_set = true;
      occupation_div_is_modified = true;
      department_is_set = true;
      department_is_modified = true;
      nationality_is_set = true;
      nationality_is_modified = true;
      nationality_other_is_set = true;
      nationality_other_is_modified = true;
      represent_family_name_is_set = true;
      represent_family_name_is_modified = true;
      represent_given_name_is_set = true;
      represent_given_name_is_modified = true;
      represent_family_name_alt1_is_set = true;
      represent_family_name_alt1_is_modified = true;
      represent_given_name_alt1_is_set = true;
      represent_given_name_alt1_is_modified = true;
      represent_power_is_set = true;
      represent_power_is_modified = true;
      director_family_name_is_set = true;
      director_family_name_is_modified = true;
      director_given_name_is_set = true;
      director_given_name_is_modified = true;
      director_family_name_alt1_is_set = true;
      director_family_name_alt1_is_modified = true;
      director_given_name_alt1_is_set = true;
      director_given_name_alt1_is_modified = true;
      director_department_is_set = true;
      director_department_is_modified = true;
      director_post_is_set = true;
      director_post_is_modified = true;
      director_zip_code_is_set = true;
      director_zip_code_is_modified = true;
      director_address1_is_set = true;
      director_address1_is_modified = true;
      director_address2_is_set = true;
      director_address2_is_modified = true;
      director_address3_is_set = true;
      director_address3_is_modified = true;
      director_era_born_is_set = true;
      director_era_born_is_modified = true;
      director_born_date_is_set = true;
      director_born_date_is_modified = true;
      director_corp_telephone_is_set = true;
      director_corp_telephone_is_modified = true;
      other_contact_is_set = true;
      other_contact_is_modified = true;
      fax_is_set = true;
      fax_is_modified = true;
      annual_income_div_is_set = true;
      annual_income_div_is_modified = true;
      asset_value_div_is_set = true;
      asset_value_div_is_modified = true;
      fund_budget_amount_div_is_set = true;
      fund_budget_amount_div_is_modified = true;
      invest_purpose_div_is_set = true;
      invest_purpose_div_is_modified = true;
      invest_plan_period_div_is_set = true;
      invest_plan_period_div_is_modified = true;
      experience_flag1_is_set = true;
      experience_flag1_is_modified = true;
      experience_flag2_is_set = true;
      experience_flag2_is_modified = true;
      experience_flag3_is_set = true;
      experience_flag3_is_modified = true;
      experience_flag4_is_set = true;
      experience_flag4_is_modified = true;
      experience_flag5_is_set = true;
      experience_flag5_is_modified = true;
      experience_flag6_is_set = true;
      experience_flag6_is_modified = true;
      experience_flag7_is_set = true;
      experience_flag7_is_modified = true;
      experience_flag8_is_set = true;
      experience_flag8_is_modified = true;
      experience_flag9_is_set = true;
      experience_flag9_is_modified = true;
      experience_flag10_is_set = true;
      experience_flag10_is_modified = true;
      experience_div1_is_set = true;
      experience_div1_is_modified = true;
      experience_div2_is_set = true;
      experience_div2_is_modified = true;
      experience_div3_is_set = true;
      experience_div3_is_modified = true;
      experience_div4_is_set = true;
      experience_div4_is_modified = true;
      experience_div5_is_set = true;
      experience_div5_is_modified = true;
      experience_div6_is_set = true;
      experience_div6_is_modified = true;
      experience_div7_is_set = true;
      experience_div7_is_modified = true;
      experience_div8_is_set = true;
      experience_div8_is_modified = true;
      experience_div9_is_set = true;
      experience_div9_is_modified = true;
      experience_div10_is_set = true;
      experience_div10_is_modified = true;
      interest_flag1_is_set = true;
      interest_flag1_is_modified = true;
      interest_flag2_is_set = true;
      interest_flag2_is_modified = true;
      interest_flag3_is_set = true;
      interest_flag3_is_modified = true;
      interest_flag4_is_set = true;
      interest_flag4_is_modified = true;
      interest_flag5_is_set = true;
      interest_flag5_is_modified = true;
      interest_flag6_is_set = true;
      interest_flag6_is_modified = true;
      interest_flag7_is_set = true;
      interest_flag7_is_modified = true;
      interest_flag8_is_set = true;
      interest_flag8_is_modified = true;
      interest_flag9_is_set = true;
      interest_flag9_is_modified = true;
      interest_flag10_is_set = true;
      interest_flag10_is_modified = true;
      appli_motivat_div_is_set = true;
      appli_motivat_div_is_modified = true;
      appli_motivat_div_detail_is_set = true;
      appli_motivat_div_detail_is_modified = true;
      use_institution_div_is_set = true;
      use_institution_div_is_modified = true;
      internet_trade_div_is_set = true;
      internet_trade_div_is_modified = true;
      introduce_branch_code_is_set = true;
      introduce_branch_code_is_modified = true;
      accept_status_is_set = true;
      accept_status_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof MobileOfficeInfoRegistRow ) )
       return false;
    return fieldsEqual( (MobileOfficeInfoRegistRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のMobileOfficeInfoRegistRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( MobileOfficeInfoRegistRow row )
  {
    if ( mobile_office_info_regist_id != row.getMobileOfficeInfoRegistId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_id != row.getBranchId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( mobile == null ) {
      if ( row.getMobile() != null )
        return false;
    } else if ( !mobile.equals( row.getMobile() ) ) {
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
    if ( regist_updater == null ) {
      if ( row.getRegistUpdater() != null )
        return false;
    } else if ( !regist_updater.equals( row.getRegistUpdater() ) ) {
        return false;
    }
    if ( decision_flag == null ) {
      if ( row.getDecisionFlag() != null )
        return false;
    } else if ( !decision_flag.equals( row.getDecisionFlag() ) ) {
        return false;
    }
    if ( decision == null ) {
      if ( !row.getDecisionIsNull() )
        return false;
    } else if ( row.getDecisionIsNull() || ( decision.intValue() != row.getDecision() ) ) {
        return false;
    }
    if ( decision_updater == null ) {
      if ( row.getDecisionUpdater() != null )
        return false;
    } else if ( !decision_updater.equals( row.getDecisionUpdater() ) ) {
        return false;
    }
    if ( decision_timestamp == null ) {
      if ( row.getDecisionTimestamp() != null )
        return false;
    } else if ( !decision_timestamp.equals( row.getDecisionTimestamp() ) ) {
        return false;
    }
    if ( delete_flag == null ) {
      if ( row.getDeleteFlag() != null )
        return false;
    } else if ( !delete_flag.equals( row.getDeleteFlag() ) ) {
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
    if ( contact_priority1 == null ) {
      if ( row.getContactPriority1() != null )
        return false;
    } else if ( !contact_priority1.equals( row.getContactPriority1() ) ) {
        return false;
    }
    if ( contact_priority2 == null ) {
      if ( row.getContactPriority2() != null )
        return false;
    } else if ( !contact_priority2.equals( row.getContactPriority2() ) ) {
        return false;
    }
    if ( contact_priority3 == null ) {
      if ( row.getContactPriority3() != null )
        return false;
    } else if ( !contact_priority3.equals( row.getContactPriority3() ) ) {
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
    if ( occupation_div == null ) {
      if ( row.getOccupationDiv() != null )
        return false;
    } else if ( !occupation_div.equals( row.getOccupationDiv() ) ) {
        return false;
    }
    if ( department == null ) {
      if ( row.getDepartment() != null )
        return false;
    } else if ( !department.equals( row.getDepartment() ) ) {
        return false;
    }
    if ( nationality == null ) {
      if ( row.getNationality() != null )
        return false;
    } else if ( !nationality.equals( row.getNationality() ) ) {
        return false;
    }
    if ( nationality_other == null ) {
      if ( row.getNationalityOther() != null )
        return false;
    } else if ( !nationality_other.equals( row.getNationalityOther() ) ) {
        return false;
    }
    if ( represent_family_name == null ) {
      if ( row.getRepresentFamilyName() != null )
        return false;
    } else if ( !represent_family_name.equals( row.getRepresentFamilyName() ) ) {
        return false;
    }
    if ( represent_given_name == null ) {
      if ( row.getRepresentGivenName() != null )
        return false;
    } else if ( !represent_given_name.equals( row.getRepresentGivenName() ) ) {
        return false;
    }
    if ( represent_family_name_alt1 == null ) {
      if ( row.getRepresentFamilyNameAlt1() != null )
        return false;
    } else if ( !represent_family_name_alt1.equals( row.getRepresentFamilyNameAlt1() ) ) {
        return false;
    }
    if ( represent_given_name_alt1 == null ) {
      if ( row.getRepresentGivenNameAlt1() != null )
        return false;
    } else if ( !represent_given_name_alt1.equals( row.getRepresentGivenNameAlt1() ) ) {
        return false;
    }
    if ( represent_power == null ) {
      if ( row.getRepresentPower() != null )
        return false;
    } else if ( !represent_power.equals( row.getRepresentPower() ) ) {
        return false;
    }
    if ( director_family_name == null ) {
      if ( row.getDirectorFamilyName() != null )
        return false;
    } else if ( !director_family_name.equals( row.getDirectorFamilyName() ) ) {
        return false;
    }
    if ( director_given_name == null ) {
      if ( row.getDirectorGivenName() != null )
        return false;
    } else if ( !director_given_name.equals( row.getDirectorGivenName() ) ) {
        return false;
    }
    if ( director_family_name_alt1 == null ) {
      if ( row.getDirectorFamilyNameAlt1() != null )
        return false;
    } else if ( !director_family_name_alt1.equals( row.getDirectorFamilyNameAlt1() ) ) {
        return false;
    }
    if ( director_given_name_alt1 == null ) {
      if ( row.getDirectorGivenNameAlt1() != null )
        return false;
    } else if ( !director_given_name_alt1.equals( row.getDirectorGivenNameAlt1() ) ) {
        return false;
    }
    if ( director_department == null ) {
      if ( row.getDirectorDepartment() != null )
        return false;
    } else if ( !director_department.equals( row.getDirectorDepartment() ) ) {
        return false;
    }
    if ( director_post == null ) {
      if ( row.getDirectorPost() != null )
        return false;
    } else if ( !director_post.equals( row.getDirectorPost() ) ) {
        return false;
    }
    if ( director_zip_code == null ) {
      if ( row.getDirectorZipCode() != null )
        return false;
    } else if ( !director_zip_code.equals( row.getDirectorZipCode() ) ) {
        return false;
    }
    if ( director_address1 == null ) {
      if ( row.getDirectorAddress1() != null )
        return false;
    } else if ( !director_address1.equals( row.getDirectorAddress1() ) ) {
        return false;
    }
    if ( director_address2 == null ) {
      if ( row.getDirectorAddress2() != null )
        return false;
    } else if ( !director_address2.equals( row.getDirectorAddress2() ) ) {
        return false;
    }
    if ( director_address3 == null ) {
      if ( row.getDirectorAddress3() != null )
        return false;
    } else if ( !director_address3.equals( row.getDirectorAddress3() ) ) {
        return false;
    }
    if ( director_era_born == null ) {
      if ( row.getDirectorEraBorn() != null )
        return false;
    } else if ( !director_era_born.equals( row.getDirectorEraBorn() ) ) {
        return false;
    }
    if ( director_born_date == null ) {
      if ( row.getDirectorBornDate() != null )
        return false;
    } else if ( !director_born_date.equals( row.getDirectorBornDate() ) ) {
        return false;
    }
    if ( director_corp_telephone == null ) {
      if ( row.getDirectorCorpTelephone() != null )
        return false;
    } else if ( !director_corp_telephone.equals( row.getDirectorCorpTelephone() ) ) {
        return false;
    }
    if ( other_contact == null ) {
      if ( row.getOtherContact() != null )
        return false;
    } else if ( !other_contact.equals( row.getOtherContact() ) ) {
        return false;
    }
    if ( fax == null ) {
      if ( row.getFax() != null )
        return false;
    } else if ( !fax.equals( row.getFax() ) ) {
        return false;
    }
    if ( annual_income_div == null ) {
      if ( row.getAnnualIncomeDiv() != null )
        return false;
    } else if ( !annual_income_div.equals( row.getAnnualIncomeDiv() ) ) {
        return false;
    }
    if ( asset_value_div == null ) {
      if ( row.getAssetValueDiv() != null )
        return false;
    } else if ( !asset_value_div.equals( row.getAssetValueDiv() ) ) {
        return false;
    }
    if ( fund_budget_amount_div == null ) {
      if ( row.getFundBudgetAmountDiv() != null )
        return false;
    } else if ( !fund_budget_amount_div.equals( row.getFundBudgetAmountDiv() ) ) {
        return false;
    }
    if ( invest_purpose_div == null ) {
      if ( row.getInvestPurposeDiv() != null )
        return false;
    } else if ( !invest_purpose_div.equals( row.getInvestPurposeDiv() ) ) {
        return false;
    }
    if ( invest_plan_period_div == null ) {
      if ( row.getInvestPlanPeriodDiv() != null )
        return false;
    } else if ( !invest_plan_period_div.equals( row.getInvestPlanPeriodDiv() ) ) {
        return false;
    }
    if ( experience_flag1 == null ) {
      if ( !row.getExperienceFlag1IsNull() )
        return false;
    } else if ( row.getExperienceFlag1IsNull() || ( experience_flag1.intValue() != row.getExperienceFlag1() ) ) {
        return false;
    }
    if ( experience_flag2 == null ) {
      if ( !row.getExperienceFlag2IsNull() )
        return false;
    } else if ( row.getExperienceFlag2IsNull() || ( experience_flag2.intValue() != row.getExperienceFlag2() ) ) {
        return false;
    }
    if ( experience_flag3 == null ) {
      if ( !row.getExperienceFlag3IsNull() )
        return false;
    } else if ( row.getExperienceFlag3IsNull() || ( experience_flag3.intValue() != row.getExperienceFlag3() ) ) {
        return false;
    }
    if ( experience_flag4 == null ) {
      if ( !row.getExperienceFlag4IsNull() )
        return false;
    } else if ( row.getExperienceFlag4IsNull() || ( experience_flag4.intValue() != row.getExperienceFlag4() ) ) {
        return false;
    }
    if ( experience_flag5 == null ) {
      if ( !row.getExperienceFlag5IsNull() )
        return false;
    } else if ( row.getExperienceFlag5IsNull() || ( experience_flag5.intValue() != row.getExperienceFlag5() ) ) {
        return false;
    }
    if ( experience_flag6 == null ) {
      if ( !row.getExperienceFlag6IsNull() )
        return false;
    } else if ( row.getExperienceFlag6IsNull() || ( experience_flag6.intValue() != row.getExperienceFlag6() ) ) {
        return false;
    }
    if ( experience_flag7 == null ) {
      if ( !row.getExperienceFlag7IsNull() )
        return false;
    } else if ( row.getExperienceFlag7IsNull() || ( experience_flag7.intValue() != row.getExperienceFlag7() ) ) {
        return false;
    }
    if ( experience_flag8 == null ) {
      if ( !row.getExperienceFlag8IsNull() )
        return false;
    } else if ( row.getExperienceFlag8IsNull() || ( experience_flag8.intValue() != row.getExperienceFlag8() ) ) {
        return false;
    }
    if ( experience_flag9 == null ) {
      if ( !row.getExperienceFlag9IsNull() )
        return false;
    } else if ( row.getExperienceFlag9IsNull() || ( experience_flag9.intValue() != row.getExperienceFlag9() ) ) {
        return false;
    }
    if ( experience_flag10 == null ) {
      if ( !row.getExperienceFlag10IsNull() )
        return false;
    } else if ( row.getExperienceFlag10IsNull() || ( experience_flag10.intValue() != row.getExperienceFlag10() ) ) {
        return false;
    }
    if ( experience_div1 == null ) {
      if ( row.getExperienceDiv1() != null )
        return false;
    } else if ( !experience_div1.equals( row.getExperienceDiv1() ) ) {
        return false;
    }
    if ( experience_div2 == null ) {
      if ( row.getExperienceDiv2() != null )
        return false;
    } else if ( !experience_div2.equals( row.getExperienceDiv2() ) ) {
        return false;
    }
    if ( experience_div3 == null ) {
      if ( row.getExperienceDiv3() != null )
        return false;
    } else if ( !experience_div3.equals( row.getExperienceDiv3() ) ) {
        return false;
    }
    if ( experience_div4 == null ) {
      if ( row.getExperienceDiv4() != null )
        return false;
    } else if ( !experience_div4.equals( row.getExperienceDiv4() ) ) {
        return false;
    }
    if ( experience_div5 == null ) {
      if ( row.getExperienceDiv5() != null )
        return false;
    } else if ( !experience_div5.equals( row.getExperienceDiv5() ) ) {
        return false;
    }
    if ( experience_div6 == null ) {
      if ( row.getExperienceDiv6() != null )
        return false;
    } else if ( !experience_div6.equals( row.getExperienceDiv6() ) ) {
        return false;
    }
    if ( experience_div7 == null ) {
      if ( row.getExperienceDiv7() != null )
        return false;
    } else if ( !experience_div7.equals( row.getExperienceDiv7() ) ) {
        return false;
    }
    if ( experience_div8 == null ) {
      if ( row.getExperienceDiv8() != null )
        return false;
    } else if ( !experience_div8.equals( row.getExperienceDiv8() ) ) {
        return false;
    }
    if ( experience_div9 == null ) {
      if ( row.getExperienceDiv9() != null )
        return false;
    } else if ( !experience_div9.equals( row.getExperienceDiv9() ) ) {
        return false;
    }
    if ( experience_div10 == null ) {
      if ( row.getExperienceDiv10() != null )
        return false;
    } else if ( !experience_div10.equals( row.getExperienceDiv10() ) ) {
        return false;
    }
    if ( interest_flag1 == null ) {
      if ( !row.getInterestFlag1IsNull() )
        return false;
    } else if ( row.getInterestFlag1IsNull() || ( interest_flag1.intValue() != row.getInterestFlag1() ) ) {
        return false;
    }
    if ( interest_flag2 == null ) {
      if ( !row.getInterestFlag2IsNull() )
        return false;
    } else if ( row.getInterestFlag2IsNull() || ( interest_flag2.intValue() != row.getInterestFlag2() ) ) {
        return false;
    }
    if ( interest_flag3 == null ) {
      if ( !row.getInterestFlag3IsNull() )
        return false;
    } else if ( row.getInterestFlag3IsNull() || ( interest_flag3.intValue() != row.getInterestFlag3() ) ) {
        return false;
    }
    if ( interest_flag4 == null ) {
      if ( !row.getInterestFlag4IsNull() )
        return false;
    } else if ( row.getInterestFlag4IsNull() || ( interest_flag4.intValue() != row.getInterestFlag4() ) ) {
        return false;
    }
    if ( interest_flag5 == null ) {
      if ( !row.getInterestFlag5IsNull() )
        return false;
    } else if ( row.getInterestFlag5IsNull() || ( interest_flag5.intValue() != row.getInterestFlag5() ) ) {
        return false;
    }
    if ( interest_flag6 == null ) {
      if ( !row.getInterestFlag6IsNull() )
        return false;
    } else if ( row.getInterestFlag6IsNull() || ( interest_flag6.intValue() != row.getInterestFlag6() ) ) {
        return false;
    }
    if ( interest_flag7 == null ) {
      if ( !row.getInterestFlag7IsNull() )
        return false;
    } else if ( row.getInterestFlag7IsNull() || ( interest_flag7.intValue() != row.getInterestFlag7() ) ) {
        return false;
    }
    if ( interest_flag8 == null ) {
      if ( !row.getInterestFlag8IsNull() )
        return false;
    } else if ( row.getInterestFlag8IsNull() || ( interest_flag8.intValue() != row.getInterestFlag8() ) ) {
        return false;
    }
    if ( interest_flag9 == null ) {
      if ( !row.getInterestFlag9IsNull() )
        return false;
    } else if ( row.getInterestFlag9IsNull() || ( interest_flag9.intValue() != row.getInterestFlag9() ) ) {
        return false;
    }
    if ( interest_flag10 == null ) {
      if ( !row.getInterestFlag10IsNull() )
        return false;
    } else if ( row.getInterestFlag10IsNull() || ( interest_flag10.intValue() != row.getInterestFlag10() ) ) {
        return false;
    }
    if ( appli_motivat_div == null ) {
      if ( row.getAppliMotivatDiv() != null )
        return false;
    } else if ( !appli_motivat_div.equals( row.getAppliMotivatDiv() ) ) {
        return false;
    }
    if ( appli_motivat_div_detail == null ) {
      if ( row.getAppliMotivatDivDetail() != null )
        return false;
    } else if ( !appli_motivat_div_detail.equals( row.getAppliMotivatDivDetail() ) ) {
        return false;
    }
    if ( use_institution_div == null ) {
      if ( row.getUseInstitutionDiv() != null )
        return false;
    } else if ( !use_institution_div.equals( row.getUseInstitutionDiv() ) ) {
        return false;
    }
    if ( internet_trade_div == null ) {
      if ( row.getInternetTradeDiv() != null )
        return false;
    } else if ( !internet_trade_div.equals( row.getInternetTradeDiv() ) ) {
        return false;
    }
    if ( introduce_branch_code == null ) {
      if ( row.getIntroduceBranchCode() != null )
        return false;
    } else if ( !introduce_branch_code.equals( row.getIntroduceBranchCode() ) ) {
        return false;
    }
    if ( accept_status == null ) {
      if ( row.getAcceptStatus() != null )
        return false;
    } else if ( !accept_status.equals( row.getAcceptStatus() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) mobile_office_info_regist_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) branch_id)
        + ((int) account_id)
        + (account_code!=null? account_code.hashCode(): 0) 
        + (mobile!=null? mobile.hashCode(): 0) 
        + (office!=null? office.hashCode(): 0) 
        + (office_zip_code!=null? office_zip_code.hashCode(): 0) 
        + (office_address!=null? office_address.hashCode(): 0) 
        + (office_telephone!=null? office_telephone.hashCode(): 0) 
        + (post!=null? post.hashCode(): 0) 
        + (regist_updater!=null? regist_updater.hashCode(): 0) 
        + (decision_flag!=null? decision_flag.hashCode(): 0) 
        + (decision!=null? decision.hashCode(): 0) 
        + (decision_updater!=null? decision_updater.hashCode(): 0) 
        + (decision_timestamp!=null? decision_timestamp.hashCode(): 0) 
        + (delete_flag!=null? delete_flag.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (contact_priority1!=null? contact_priority1.hashCode(): 0) 
        + (contact_priority2!=null? contact_priority2.hashCode(): 0) 
        + (contact_priority3!=null? contact_priority3.hashCode(): 0) 
        + (real_name1!=null? real_name1.hashCode(): 0) 
        + (real_name2!=null? real_name2.hashCode(): 0) 
        + (occupation_div!=null? occupation_div.hashCode(): 0) 
        + (department!=null? department.hashCode(): 0) 
        + (nationality!=null? nationality.hashCode(): 0) 
        + (nationality_other!=null? nationality_other.hashCode(): 0) 
        + (represent_family_name!=null? represent_family_name.hashCode(): 0) 
        + (represent_given_name!=null? represent_given_name.hashCode(): 0) 
        + (represent_family_name_alt1!=null? represent_family_name_alt1.hashCode(): 0) 
        + (represent_given_name_alt1!=null? represent_given_name_alt1.hashCode(): 0) 
        + (represent_power!=null? represent_power.hashCode(): 0) 
        + (director_family_name!=null? director_family_name.hashCode(): 0) 
        + (director_given_name!=null? director_given_name.hashCode(): 0) 
        + (director_family_name_alt1!=null? director_family_name_alt1.hashCode(): 0) 
        + (director_given_name_alt1!=null? director_given_name_alt1.hashCode(): 0) 
        + (director_department!=null? director_department.hashCode(): 0) 
        + (director_post!=null? director_post.hashCode(): 0) 
        + (director_zip_code!=null? director_zip_code.hashCode(): 0) 
        + (director_address1!=null? director_address1.hashCode(): 0) 
        + (director_address2!=null? director_address2.hashCode(): 0) 
        + (director_address3!=null? director_address3.hashCode(): 0) 
        + (director_era_born!=null? director_era_born.hashCode(): 0) 
        + (director_born_date!=null? director_born_date.hashCode(): 0) 
        + (director_corp_telephone!=null? director_corp_telephone.hashCode(): 0) 
        + (other_contact!=null? other_contact.hashCode(): 0) 
        + (fax!=null? fax.hashCode(): 0) 
        + (annual_income_div!=null? annual_income_div.hashCode(): 0) 
        + (asset_value_div!=null? asset_value_div.hashCode(): 0) 
        + (fund_budget_amount_div!=null? fund_budget_amount_div.hashCode(): 0) 
        + (invest_purpose_div!=null? invest_purpose_div.hashCode(): 0) 
        + (invest_plan_period_div!=null? invest_plan_period_div.hashCode(): 0) 
        + (experience_flag1!=null? experience_flag1.hashCode(): 0) 
        + (experience_flag2!=null? experience_flag2.hashCode(): 0) 
        + (experience_flag3!=null? experience_flag3.hashCode(): 0) 
        + (experience_flag4!=null? experience_flag4.hashCode(): 0) 
        + (experience_flag5!=null? experience_flag5.hashCode(): 0) 
        + (experience_flag6!=null? experience_flag6.hashCode(): 0) 
        + (experience_flag7!=null? experience_flag7.hashCode(): 0) 
        + (experience_flag8!=null? experience_flag8.hashCode(): 0) 
        + (experience_flag9!=null? experience_flag9.hashCode(): 0) 
        + (experience_flag10!=null? experience_flag10.hashCode(): 0) 
        + (experience_div1!=null? experience_div1.hashCode(): 0) 
        + (experience_div2!=null? experience_div2.hashCode(): 0) 
        + (experience_div3!=null? experience_div3.hashCode(): 0) 
        + (experience_div4!=null? experience_div4.hashCode(): 0) 
        + (experience_div5!=null? experience_div5.hashCode(): 0) 
        + (experience_div6!=null? experience_div6.hashCode(): 0) 
        + (experience_div7!=null? experience_div7.hashCode(): 0) 
        + (experience_div8!=null? experience_div8.hashCode(): 0) 
        + (experience_div9!=null? experience_div9.hashCode(): 0) 
        + (experience_div10!=null? experience_div10.hashCode(): 0) 
        + (interest_flag1!=null? interest_flag1.hashCode(): 0) 
        + (interest_flag2!=null? interest_flag2.hashCode(): 0) 
        + (interest_flag3!=null? interest_flag3.hashCode(): 0) 
        + (interest_flag4!=null? interest_flag4.hashCode(): 0) 
        + (interest_flag5!=null? interest_flag5.hashCode(): 0) 
        + (interest_flag6!=null? interest_flag6.hashCode(): 0) 
        + (interest_flag7!=null? interest_flag7.hashCode(): 0) 
        + (interest_flag8!=null? interest_flag8.hashCode(): 0) 
        + (interest_flag9!=null? interest_flag9.hashCode(): 0) 
        + (interest_flag10!=null? interest_flag10.hashCode(): 0) 
        + (appli_motivat_div!=null? appli_motivat_div.hashCode(): 0) 
        + (appli_motivat_div_detail!=null? appli_motivat_div_detail.hashCode(): 0) 
        + (use_institution_div!=null? use_institution_div.hashCode(): 0) 
        + (internet_trade_div!=null? internet_trade_div.hashCode(): 0) 
        + (introduce_branch_code!=null? introduce_branch_code.hashCode(): 0) 
        + (accept_status!=null? accept_status.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !branch_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_id' must be set before inserting.");
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !regist_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'regist_updater' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
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
		map.put("mobile_office_info_regist_id",new Long(mobile_office_info_regist_id));
		map.put("institution_code",institution_code);
		map.put("branch_id",new Long(branch_id));
		map.put("account_id",new Long(account_id));
		map.put("account_code",account_code);
		if ( mobile != null )
			map.put("mobile",mobile);
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
		map.put("regist_updater",regist_updater);
		if ( decision_flag_is_set )
			map.put("decision_flag",decision_flag);
		if ( decision != null )
			map.put("decision",decision);
		if ( decision_updater != null )
			map.put("decision_updater",decision_updater);
		if ( decision_timestamp != null )
			map.put("decision_timestamp",decision_timestamp);
		if ( delete_flag_is_set )
			map.put("delete_flag",delete_flag);
		map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		if ( contact_priority1 != null )
			map.put("contact_priority1",contact_priority1);
		if ( contact_priority2 != null )
			map.put("contact_priority2",contact_priority2);
		if ( contact_priority3 != null )
			map.put("contact_priority3",contact_priority3);
		if ( real_name1 != null )
			map.put("real_name1",real_name1);
		if ( real_name2 != null )
			map.put("real_name2",real_name2);
		if ( occupation_div != null )
			map.put("occupation_div",occupation_div);
		if ( department != null )
			map.put("department",department);
		if ( nationality != null )
			map.put("nationality",nationality);
		if ( nationality_other != null )
			map.put("nationality_other",nationality_other);
		if ( represent_family_name != null )
			map.put("represent_family_name",represent_family_name);
		if ( represent_given_name != null )
			map.put("represent_given_name",represent_given_name);
		if ( represent_family_name_alt1 != null )
			map.put("represent_family_name_alt1",represent_family_name_alt1);
		if ( represent_given_name_alt1 != null )
			map.put("represent_given_name_alt1",represent_given_name_alt1);
		if ( represent_power != null )
			map.put("represent_power",represent_power);
		if ( director_family_name != null )
			map.put("director_family_name",director_family_name);
		if ( director_given_name != null )
			map.put("director_given_name",director_given_name);
		if ( director_family_name_alt1 != null )
			map.put("director_family_name_alt1",director_family_name_alt1);
		if ( director_given_name_alt1 != null )
			map.put("director_given_name_alt1",director_given_name_alt1);
		if ( director_department != null )
			map.put("director_department",director_department);
		if ( director_post != null )
			map.put("director_post",director_post);
		if ( director_zip_code != null )
			map.put("director_zip_code",director_zip_code);
		if ( director_address1 != null )
			map.put("director_address1",director_address1);
		if ( director_address2 != null )
			map.put("director_address2",director_address2);
		if ( director_address3 != null )
			map.put("director_address3",director_address3);
		if ( director_era_born != null )
			map.put("director_era_born",director_era_born);
		if ( director_born_date != null )
			map.put("director_born_date",director_born_date);
		if ( director_corp_telephone != null )
			map.put("director_corp_telephone",director_corp_telephone);
		if ( other_contact != null )
			map.put("other_contact",other_contact);
		if ( fax != null )
			map.put("fax",fax);
		if ( annual_income_div != null )
			map.put("annual_income_div",annual_income_div);
		if ( asset_value_div != null )
			map.put("asset_value_div",asset_value_div);
		if ( fund_budget_amount_div != null )
			map.put("fund_budget_amount_div",fund_budget_amount_div);
		if ( invest_purpose_div != null )
			map.put("invest_purpose_div",invest_purpose_div);
		if ( invest_plan_period_div != null )
			map.put("invest_plan_period_div",invest_plan_period_div);
		if ( experience_flag1 != null )
			map.put("experience_flag1",experience_flag1);
		if ( experience_flag2 != null )
			map.put("experience_flag2",experience_flag2);
		if ( experience_flag3 != null )
			map.put("experience_flag3",experience_flag3);
		if ( experience_flag4 != null )
			map.put("experience_flag4",experience_flag4);
		if ( experience_flag5 != null )
			map.put("experience_flag5",experience_flag5);
		if ( experience_flag6 != null )
			map.put("experience_flag6",experience_flag6);
		if ( experience_flag7 != null )
			map.put("experience_flag7",experience_flag7);
		if ( experience_flag8 != null )
			map.put("experience_flag8",experience_flag8);
		if ( experience_flag9 != null )
			map.put("experience_flag9",experience_flag9);
		if ( experience_flag10 != null )
			map.put("experience_flag10",experience_flag10);
		if ( experience_div1 != null )
			map.put("experience_div1",experience_div1);
		if ( experience_div2 != null )
			map.put("experience_div2",experience_div2);
		if ( experience_div3 != null )
			map.put("experience_div3",experience_div3);
		if ( experience_div4 != null )
			map.put("experience_div4",experience_div4);
		if ( experience_div5 != null )
			map.put("experience_div5",experience_div5);
		if ( experience_div6 != null )
			map.put("experience_div6",experience_div6);
		if ( experience_div7 != null )
			map.put("experience_div7",experience_div7);
		if ( experience_div8 != null )
			map.put("experience_div8",experience_div8);
		if ( experience_div9 != null )
			map.put("experience_div9",experience_div9);
		if ( experience_div10 != null )
			map.put("experience_div10",experience_div10);
		if ( interest_flag1 != null )
			map.put("interest_flag1",interest_flag1);
		if ( interest_flag2 != null )
			map.put("interest_flag2",interest_flag2);
		if ( interest_flag3 != null )
			map.put("interest_flag3",interest_flag3);
		if ( interest_flag4 != null )
			map.put("interest_flag4",interest_flag4);
		if ( interest_flag5 != null )
			map.put("interest_flag5",interest_flag5);
		if ( interest_flag6 != null )
			map.put("interest_flag6",interest_flag6);
		if ( interest_flag7 != null )
			map.put("interest_flag7",interest_flag7);
		if ( interest_flag8 != null )
			map.put("interest_flag8",interest_flag8);
		if ( interest_flag9 != null )
			map.put("interest_flag9",interest_flag9);
		if ( interest_flag10 != null )
			map.put("interest_flag10",interest_flag10);
		if ( appli_motivat_div != null )
			map.put("appli_motivat_div",appli_motivat_div);
		if ( appli_motivat_div_detail != null )
			map.put("appli_motivat_div_detail",appli_motivat_div_detail);
		if ( use_institution_div != null )
			map.put("use_institution_div",use_institution_div);
		if ( internet_trade_div != null )
			map.put("internet_trade_div",internet_trade_div);
		if ( introduce_branch_code != null )
			map.put("introduce_branch_code",introduce_branch_code);
		if ( accept_status != null )
			map.put("accept_status",accept_status);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_id_is_modified )
			map.put("branch_id",new Long(branch_id));
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( mobile_is_modified )
			map.put("mobile",mobile);
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
		if ( regist_updater_is_modified )
			map.put("regist_updater",regist_updater);
		if ( decision_flag_is_modified )
			map.put("decision_flag",decision_flag);
		if ( decision_is_modified )
			map.put("decision",decision);
		if ( decision_updater_is_modified )
			map.put("decision_updater",decision_updater);
		if ( decision_timestamp_is_modified )
			map.put("decision_timestamp",decision_timestamp);
		if ( delete_flag_is_modified )
			map.put("delete_flag",delete_flag);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( contact_priority1_is_modified )
			map.put("contact_priority1",contact_priority1);
		if ( contact_priority2_is_modified )
			map.put("contact_priority2",contact_priority2);
		if ( contact_priority3_is_modified )
			map.put("contact_priority3",contact_priority3);
		if ( real_name1_is_modified )
			map.put("real_name1",real_name1);
		if ( real_name2_is_modified )
			map.put("real_name2",real_name2);
		if ( occupation_div_is_modified )
			map.put("occupation_div",occupation_div);
		if ( department_is_modified )
			map.put("department",department);
		if ( nationality_is_modified )
			map.put("nationality",nationality);
		if ( nationality_other_is_modified )
			map.put("nationality_other",nationality_other);
		if ( represent_family_name_is_modified )
			map.put("represent_family_name",represent_family_name);
		if ( represent_given_name_is_modified )
			map.put("represent_given_name",represent_given_name);
		if ( represent_family_name_alt1_is_modified )
			map.put("represent_family_name_alt1",represent_family_name_alt1);
		if ( represent_given_name_alt1_is_modified )
			map.put("represent_given_name_alt1",represent_given_name_alt1);
		if ( represent_power_is_modified )
			map.put("represent_power",represent_power);
		if ( director_family_name_is_modified )
			map.put("director_family_name",director_family_name);
		if ( director_given_name_is_modified )
			map.put("director_given_name",director_given_name);
		if ( director_family_name_alt1_is_modified )
			map.put("director_family_name_alt1",director_family_name_alt1);
		if ( director_given_name_alt1_is_modified )
			map.put("director_given_name_alt1",director_given_name_alt1);
		if ( director_department_is_modified )
			map.put("director_department",director_department);
		if ( director_post_is_modified )
			map.put("director_post",director_post);
		if ( director_zip_code_is_modified )
			map.put("director_zip_code",director_zip_code);
		if ( director_address1_is_modified )
			map.put("director_address1",director_address1);
		if ( director_address2_is_modified )
			map.put("director_address2",director_address2);
		if ( director_address3_is_modified )
			map.put("director_address3",director_address3);
		if ( director_era_born_is_modified )
			map.put("director_era_born",director_era_born);
		if ( director_born_date_is_modified )
			map.put("director_born_date",director_born_date);
		if ( director_corp_telephone_is_modified )
			map.put("director_corp_telephone",director_corp_telephone);
		if ( other_contact_is_modified )
			map.put("other_contact",other_contact);
		if ( fax_is_modified )
			map.put("fax",fax);
		if ( annual_income_div_is_modified )
			map.put("annual_income_div",annual_income_div);
		if ( asset_value_div_is_modified )
			map.put("asset_value_div",asset_value_div);
		if ( fund_budget_amount_div_is_modified )
			map.put("fund_budget_amount_div",fund_budget_amount_div);
		if ( invest_purpose_div_is_modified )
			map.put("invest_purpose_div",invest_purpose_div);
		if ( invest_plan_period_div_is_modified )
			map.put("invest_plan_period_div",invest_plan_period_div);
		if ( experience_flag1_is_modified )
			map.put("experience_flag1",experience_flag1);
		if ( experience_flag2_is_modified )
			map.put("experience_flag2",experience_flag2);
		if ( experience_flag3_is_modified )
			map.put("experience_flag3",experience_flag3);
		if ( experience_flag4_is_modified )
			map.put("experience_flag4",experience_flag4);
		if ( experience_flag5_is_modified )
			map.put("experience_flag5",experience_flag5);
		if ( experience_flag6_is_modified )
			map.put("experience_flag6",experience_flag6);
		if ( experience_flag7_is_modified )
			map.put("experience_flag7",experience_flag7);
		if ( experience_flag8_is_modified )
			map.put("experience_flag8",experience_flag8);
		if ( experience_flag9_is_modified )
			map.put("experience_flag9",experience_flag9);
		if ( experience_flag10_is_modified )
			map.put("experience_flag10",experience_flag10);
		if ( experience_div1_is_modified )
			map.put("experience_div1",experience_div1);
		if ( experience_div2_is_modified )
			map.put("experience_div2",experience_div2);
		if ( experience_div3_is_modified )
			map.put("experience_div3",experience_div3);
		if ( experience_div4_is_modified )
			map.put("experience_div4",experience_div4);
		if ( experience_div5_is_modified )
			map.put("experience_div5",experience_div5);
		if ( experience_div6_is_modified )
			map.put("experience_div6",experience_div6);
		if ( experience_div7_is_modified )
			map.put("experience_div7",experience_div7);
		if ( experience_div8_is_modified )
			map.put("experience_div8",experience_div8);
		if ( experience_div9_is_modified )
			map.put("experience_div9",experience_div9);
		if ( experience_div10_is_modified )
			map.put("experience_div10",experience_div10);
		if ( interest_flag1_is_modified )
			map.put("interest_flag1",interest_flag1);
		if ( interest_flag2_is_modified )
			map.put("interest_flag2",interest_flag2);
		if ( interest_flag3_is_modified )
			map.put("interest_flag3",interest_flag3);
		if ( interest_flag4_is_modified )
			map.put("interest_flag4",interest_flag4);
		if ( interest_flag5_is_modified )
			map.put("interest_flag5",interest_flag5);
		if ( interest_flag6_is_modified )
			map.put("interest_flag6",interest_flag6);
		if ( interest_flag7_is_modified )
			map.put("interest_flag7",interest_flag7);
		if ( interest_flag8_is_modified )
			map.put("interest_flag8",interest_flag8);
		if ( interest_flag9_is_modified )
			map.put("interest_flag9",interest_flag9);
		if ( interest_flag10_is_modified )
			map.put("interest_flag10",interest_flag10);
		if ( appli_motivat_div_is_modified )
			map.put("appli_motivat_div",appli_motivat_div);
		if ( appli_motivat_div_detail_is_modified )
			map.put("appli_motivat_div_detail",appli_motivat_div_detail);
		if ( use_institution_div_is_modified )
			map.put("use_institution_div",use_institution_div);
		if ( internet_trade_div_is_modified )
			map.put("internet_trade_div",internet_trade_div);
		if ( introduce_branch_code_is_modified )
			map.put("introduce_branch_code",introduce_branch_code);
		if ( accept_status_is_modified )
			map.put("accept_status",accept_status);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( branch_id_is_set )
				map.put("branch_id",new Long(branch_id));
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("mobile",mobile);
			map.put("office",office);
			map.put("office_zip_code",office_zip_code);
			map.put("office_address",office_address);
			map.put("office_telephone",office_telephone);
			map.put("post",post);
			if ( regist_updater_is_set )
				map.put("regist_updater",regist_updater);
			if ( decision_flag_is_set )
				map.put("decision_flag",decision_flag);
			map.put("decision",decision);
			map.put("decision_updater",decision_updater);
			map.put("decision_timestamp",decision_timestamp);
			if ( delete_flag_is_set )
				map.put("delete_flag",delete_flag);
			if ( last_updater_is_set )
				map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("contact_priority1",contact_priority1);
			map.put("contact_priority2",contact_priority2);
			map.put("contact_priority3",contact_priority3);
			map.put("real_name1",real_name1);
			map.put("real_name2",real_name2);
			map.put("occupation_div",occupation_div);
			map.put("department",department);
			map.put("nationality",nationality);
			map.put("nationality_other",nationality_other);
			map.put("represent_family_name",represent_family_name);
			map.put("represent_given_name",represent_given_name);
			map.put("represent_family_name_alt1",represent_family_name_alt1);
			map.put("represent_given_name_alt1",represent_given_name_alt1);
			map.put("represent_power",represent_power);
			map.put("director_family_name",director_family_name);
			map.put("director_given_name",director_given_name);
			map.put("director_family_name_alt1",director_family_name_alt1);
			map.put("director_given_name_alt1",director_given_name_alt1);
			map.put("director_department",director_department);
			map.put("director_post",director_post);
			map.put("director_zip_code",director_zip_code);
			map.put("director_address1",director_address1);
			map.put("director_address2",director_address2);
			map.put("director_address3",director_address3);
			map.put("director_era_born",director_era_born);
			map.put("director_born_date",director_born_date);
			map.put("director_corp_telephone",director_corp_telephone);
			map.put("other_contact",other_contact);
			map.put("fax",fax);
			map.put("annual_income_div",annual_income_div);
			map.put("asset_value_div",asset_value_div);
			map.put("fund_budget_amount_div",fund_budget_amount_div);
			map.put("invest_purpose_div",invest_purpose_div);
			map.put("invest_plan_period_div",invest_plan_period_div);
			map.put("experience_flag1",experience_flag1);
			map.put("experience_flag2",experience_flag2);
			map.put("experience_flag3",experience_flag3);
			map.put("experience_flag4",experience_flag4);
			map.put("experience_flag5",experience_flag5);
			map.put("experience_flag6",experience_flag6);
			map.put("experience_flag7",experience_flag7);
			map.put("experience_flag8",experience_flag8);
			map.put("experience_flag9",experience_flag9);
			map.put("experience_flag10",experience_flag10);
			map.put("experience_div1",experience_div1);
			map.put("experience_div2",experience_div2);
			map.put("experience_div3",experience_div3);
			map.put("experience_div4",experience_div4);
			map.put("experience_div5",experience_div5);
			map.put("experience_div6",experience_div6);
			map.put("experience_div7",experience_div7);
			map.put("experience_div8",experience_div8);
			map.put("experience_div9",experience_div9);
			map.put("experience_div10",experience_div10);
			map.put("interest_flag1",interest_flag1);
			map.put("interest_flag2",interest_flag2);
			map.put("interest_flag3",interest_flag3);
			map.put("interest_flag4",interest_flag4);
			map.put("interest_flag5",interest_flag5);
			map.put("interest_flag6",interest_flag6);
			map.put("interest_flag7",interest_flag7);
			map.put("interest_flag8",interest_flag8);
			map.put("interest_flag9",interest_flag9);
			map.put("interest_flag10",interest_flag10);
			map.put("appli_motivat_div",appli_motivat_div);
			map.put("appli_motivat_div_detail",appli_motivat_div_detail);
			map.put("use_institution_div",use_institution_div);
			map.put("internet_trade_div",internet_trade_div);
			map.put("introduce_branch_code",introduce_branch_code);
			map.put("accept_status",accept_status);
		}
		return map;
	}


  /** 
   * <em>mobile_office_info_regist_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMobileOfficeInfoRegistId()
  {
    return mobile_office_info_regist_id;
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
   * <em>regist_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRegistUpdater()
  {
    return regist_updater;
  }


  /** 
   * <em>regist_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistUpdaterIsSet() {
    return regist_updater_is_set;
  }


  /** 
   * <em>regist_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistUpdaterIsModified() {
    return regist_updater_is_modified;
  }


  /** 
   * <em>decision_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDecisionFlag()
  {
    return decision_flag;
  }


  /** 
   * <em>decision_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDecisionFlagIsSet() {
    return decision_flag_is_set;
  }


  /** 
   * <em>decision_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDecisionFlagIsModified() {
    return decision_flag_is_modified;
  }


  /** 
   * <em>decision</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getDecision()
  {
    return ( decision==null? ((int)0): decision.intValue() );
  }


  /** 
   * <em>decision</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDecisionIsNull()
  {
    return decision == null;
  }


  /** 
   * <em>decision</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDecisionIsSet() {
    return decision_is_set;
  }


  /** 
   * <em>decision</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDecisionIsModified() {
    return decision_is_modified;
  }


  /** 
   * <em>decision_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDecisionUpdater()
  {
    return decision_updater;
  }


  /** 
   * <em>decision_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDecisionUpdaterIsSet() {
    return decision_updater_is_set;
  }


  /** 
   * <em>decision_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDecisionUpdaterIsModified() {
    return decision_updater_is_modified;
  }


  /** 
   * <em>decision_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getDecisionTimestamp()
  {
    return decision_timestamp;
  }


  /** 
   * <em>decision_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDecisionTimestampIsSet() {
    return decision_timestamp_is_set;
  }


  /** 
   * <em>decision_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDecisionTimestampIsModified() {
    return decision_timestamp_is_modified;
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
   * <em>contact_priority1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getContactPriority1()
  {
    return contact_priority1;
  }


  /** 
   * <em>contact_priority1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContactPriority1IsSet() {
    return contact_priority1_is_set;
  }


  /** 
   * <em>contact_priority1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContactPriority1IsModified() {
    return contact_priority1_is_modified;
  }


  /** 
   * <em>contact_priority2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getContactPriority2()
  {
    return contact_priority2;
  }


  /** 
   * <em>contact_priority2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContactPriority2IsSet() {
    return contact_priority2_is_set;
  }


  /** 
   * <em>contact_priority2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContactPriority2IsModified() {
    return contact_priority2_is_modified;
  }


  /** 
   * <em>contact_priority3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getContactPriority3()
  {
    return contact_priority3;
  }


  /** 
   * <em>contact_priority3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContactPriority3IsSet() {
    return contact_priority3_is_set;
  }


  /** 
   * <em>contact_priority3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContactPriority3IsModified() {
    return contact_priority3_is_modified;
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
   * <em>nationality</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNationality()
  {
    return nationality;
  }


  /** 
   * <em>nationality</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNationalityIsSet() {
    return nationality_is_set;
  }


  /** 
   * <em>nationality</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNationalityIsModified() {
    return nationality_is_modified;
  }


  /** 
   * <em>nationality_other</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNationalityOther()
  {
    return nationality_other;
  }


  /** 
   * <em>nationality_other</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNationalityOtherIsSet() {
    return nationality_other_is_set;
  }


  /** 
   * <em>nationality_other</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNationalityOtherIsModified() {
    return nationality_other_is_modified;
  }


  /** 
   * <em>represent_family_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRepresentFamilyName()
  {
    return represent_family_name;
  }


  /** 
   * <em>represent_family_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentFamilyNameIsSet() {
    return represent_family_name_is_set;
  }


  /** 
   * <em>represent_family_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentFamilyNameIsModified() {
    return represent_family_name_is_modified;
  }


  /** 
   * <em>represent_given_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRepresentGivenName()
  {
    return represent_given_name;
  }


  /** 
   * <em>represent_given_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentGivenNameIsSet() {
    return represent_given_name_is_set;
  }


  /** 
   * <em>represent_given_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentGivenNameIsModified() {
    return represent_given_name_is_modified;
  }


  /** 
   * <em>represent_family_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRepresentFamilyNameAlt1()
  {
    return represent_family_name_alt1;
  }


  /** 
   * <em>represent_family_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentFamilyNameAlt1IsSet() {
    return represent_family_name_alt1_is_set;
  }


  /** 
   * <em>represent_family_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentFamilyNameAlt1IsModified() {
    return represent_family_name_alt1_is_modified;
  }


  /** 
   * <em>represent_given_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRepresentGivenNameAlt1()
  {
    return represent_given_name_alt1;
  }


  /** 
   * <em>represent_given_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentGivenNameAlt1IsSet() {
    return represent_given_name_alt1_is_set;
  }


  /** 
   * <em>represent_given_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentGivenNameAlt1IsModified() {
    return represent_given_name_alt1_is_modified;
  }


  /** 
   * <em>represent_power</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRepresentPower()
  {
    return represent_power;
  }


  /** 
   * <em>represent_power</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentPowerIsSet() {
    return represent_power_is_set;
  }


  /** 
   * <em>represent_power</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRepresentPowerIsModified() {
    return represent_power_is_modified;
  }


  /** 
   * <em>director_family_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDirectorFamilyName()
  {
    return director_family_name;
  }


  /** 
   * <em>director_family_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorFamilyNameIsSet() {
    return director_family_name_is_set;
  }


  /** 
   * <em>director_family_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorFamilyNameIsModified() {
    return director_family_name_is_modified;
  }


  /** 
   * <em>director_given_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDirectorGivenName()
  {
    return director_given_name;
  }


  /** 
   * <em>director_given_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorGivenNameIsSet() {
    return director_given_name_is_set;
  }


  /** 
   * <em>director_given_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorGivenNameIsModified() {
    return director_given_name_is_modified;
  }


  /** 
   * <em>director_family_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDirectorFamilyNameAlt1()
  {
    return director_family_name_alt1;
  }


  /** 
   * <em>director_family_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorFamilyNameAlt1IsSet() {
    return director_family_name_alt1_is_set;
  }


  /** 
   * <em>director_family_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorFamilyNameAlt1IsModified() {
    return director_family_name_alt1_is_modified;
  }


  /** 
   * <em>director_given_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDirectorGivenNameAlt1()
  {
    return director_given_name_alt1;
  }


  /** 
   * <em>director_given_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorGivenNameAlt1IsSet() {
    return director_given_name_alt1_is_set;
  }


  /** 
   * <em>director_given_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorGivenNameAlt1IsModified() {
    return director_given_name_alt1_is_modified;
  }


  /** 
   * <em>director_department</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDirectorDepartment()
  {
    return director_department;
  }


  /** 
   * <em>director_department</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorDepartmentIsSet() {
    return director_department_is_set;
  }


  /** 
   * <em>director_department</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorDepartmentIsModified() {
    return director_department_is_modified;
  }


  /** 
   * <em>director_post</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDirectorPost()
  {
    return director_post;
  }


  /** 
   * <em>director_post</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorPostIsSet() {
    return director_post_is_set;
  }


  /** 
   * <em>director_post</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorPostIsModified() {
    return director_post_is_modified;
  }


  /** 
   * <em>director_zip_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDirectorZipCode()
  {
    return director_zip_code;
  }


  /** 
   * <em>director_zip_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorZipCodeIsSet() {
    return director_zip_code_is_set;
  }


  /** 
   * <em>director_zip_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorZipCodeIsModified() {
    return director_zip_code_is_modified;
  }


  /** 
   * <em>director_address1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDirectorAddress1()
  {
    return director_address1;
  }


  /** 
   * <em>director_address1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorAddress1IsSet() {
    return director_address1_is_set;
  }


  /** 
   * <em>director_address1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorAddress1IsModified() {
    return director_address1_is_modified;
  }


  /** 
   * <em>director_address2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDirectorAddress2()
  {
    return director_address2;
  }


  /** 
   * <em>director_address2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorAddress2IsSet() {
    return director_address2_is_set;
  }


  /** 
   * <em>director_address2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorAddress2IsModified() {
    return director_address2_is_modified;
  }


  /** 
   * <em>director_address3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDirectorAddress3()
  {
    return director_address3;
  }


  /** 
   * <em>director_address3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorAddress3IsSet() {
    return director_address3_is_set;
  }


  /** 
   * <em>director_address3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorAddress3IsModified() {
    return director_address3_is_modified;
  }


  /** 
   * <em>director_era_born</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDirectorEraBorn()
  {
    return director_era_born;
  }


  /** 
   * <em>director_era_born</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorEraBornIsSet() {
    return director_era_born_is_set;
  }


  /** 
   * <em>director_era_born</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorEraBornIsModified() {
    return director_era_born_is_modified;
  }


  /** 
   * <em>director_born_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDirectorBornDate()
  {
    return director_born_date;
  }


  /** 
   * <em>director_born_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorBornDateIsSet() {
    return director_born_date_is_set;
  }


  /** 
   * <em>director_born_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorBornDateIsModified() {
    return director_born_date_is_modified;
  }


  /** 
   * <em>director_corp_telephone</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDirectorCorpTelephone()
  {
    return director_corp_telephone;
  }


  /** 
   * <em>director_corp_telephone</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorCorpTelephoneIsSet() {
    return director_corp_telephone_is_set;
  }


  /** 
   * <em>director_corp_telephone</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDirectorCorpTelephoneIsModified() {
    return director_corp_telephone_is_modified;
  }


  /** 
   * <em>other_contact</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOtherContact()
  {
    return other_contact;
  }


  /** 
   * <em>other_contact</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherContactIsSet() {
    return other_contact_is_set;
  }


  /** 
   * <em>other_contact</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherContactIsModified() {
    return other_contact_is_modified;
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
   * <em>invest_plan_period_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInvestPlanPeriodDiv()
  {
    return invest_plan_period_div;
  }


  /** 
   * <em>invest_plan_period_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInvestPlanPeriodDivIsSet() {
    return invest_plan_period_div_is_set;
  }


  /** 
   * <em>invest_plan_period_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInvestPlanPeriodDivIsModified() {
    return invest_plan_period_div_is_modified;
  }


  /** 
   * <em>experience_flag1</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getExperienceFlag1()
  {
    return ( experience_flag1==null? ((int)0): experience_flag1.intValue() );
  }


  /** 
   * <em>experience_flag1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExperienceFlag1IsNull()
  {
    return experience_flag1 == null;
  }


  /** 
   * <em>experience_flag1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag1IsSet() {
    return experience_flag1_is_set;
  }


  /** 
   * <em>experience_flag1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag1IsModified() {
    return experience_flag1_is_modified;
  }


  /** 
   * <em>experience_flag2</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getExperienceFlag2()
  {
    return ( experience_flag2==null? ((int)0): experience_flag2.intValue() );
  }


  /** 
   * <em>experience_flag2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExperienceFlag2IsNull()
  {
    return experience_flag2 == null;
  }


  /** 
   * <em>experience_flag2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag2IsSet() {
    return experience_flag2_is_set;
  }


  /** 
   * <em>experience_flag2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag2IsModified() {
    return experience_flag2_is_modified;
  }


  /** 
   * <em>experience_flag3</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getExperienceFlag3()
  {
    return ( experience_flag3==null? ((int)0): experience_flag3.intValue() );
  }


  /** 
   * <em>experience_flag3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExperienceFlag3IsNull()
  {
    return experience_flag3 == null;
  }


  /** 
   * <em>experience_flag3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag3IsSet() {
    return experience_flag3_is_set;
  }


  /** 
   * <em>experience_flag3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag3IsModified() {
    return experience_flag3_is_modified;
  }


  /** 
   * <em>experience_flag4</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getExperienceFlag4()
  {
    return ( experience_flag4==null? ((int)0): experience_flag4.intValue() );
  }


  /** 
   * <em>experience_flag4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExperienceFlag4IsNull()
  {
    return experience_flag4 == null;
  }


  /** 
   * <em>experience_flag4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag4IsSet() {
    return experience_flag4_is_set;
  }


  /** 
   * <em>experience_flag4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag4IsModified() {
    return experience_flag4_is_modified;
  }


  /** 
   * <em>experience_flag5</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getExperienceFlag5()
  {
    return ( experience_flag5==null? ((int)0): experience_flag5.intValue() );
  }


  /** 
   * <em>experience_flag5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExperienceFlag5IsNull()
  {
    return experience_flag5 == null;
  }


  /** 
   * <em>experience_flag5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag5IsSet() {
    return experience_flag5_is_set;
  }


  /** 
   * <em>experience_flag5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag5IsModified() {
    return experience_flag5_is_modified;
  }


  /** 
   * <em>experience_flag6</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getExperienceFlag6()
  {
    return ( experience_flag6==null? ((int)0): experience_flag6.intValue() );
  }


  /** 
   * <em>experience_flag6</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExperienceFlag6IsNull()
  {
    return experience_flag6 == null;
  }


  /** 
   * <em>experience_flag6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag6IsSet() {
    return experience_flag6_is_set;
  }


  /** 
   * <em>experience_flag6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag6IsModified() {
    return experience_flag6_is_modified;
  }


  /** 
   * <em>experience_flag7</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getExperienceFlag7()
  {
    return ( experience_flag7==null? ((int)0): experience_flag7.intValue() );
  }


  /** 
   * <em>experience_flag7</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExperienceFlag7IsNull()
  {
    return experience_flag7 == null;
  }


  /** 
   * <em>experience_flag7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag7IsSet() {
    return experience_flag7_is_set;
  }


  /** 
   * <em>experience_flag7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag7IsModified() {
    return experience_flag7_is_modified;
  }


  /** 
   * <em>experience_flag8</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getExperienceFlag8()
  {
    return ( experience_flag8==null? ((int)0): experience_flag8.intValue() );
  }


  /** 
   * <em>experience_flag8</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExperienceFlag8IsNull()
  {
    return experience_flag8 == null;
  }


  /** 
   * <em>experience_flag8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag8IsSet() {
    return experience_flag8_is_set;
  }


  /** 
   * <em>experience_flag8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag8IsModified() {
    return experience_flag8_is_modified;
  }


  /** 
   * <em>experience_flag9</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getExperienceFlag9()
  {
    return ( experience_flag9==null? ((int)0): experience_flag9.intValue() );
  }


  /** 
   * <em>experience_flag9</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExperienceFlag9IsNull()
  {
    return experience_flag9 == null;
  }


  /** 
   * <em>experience_flag9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag9IsSet() {
    return experience_flag9_is_set;
  }


  /** 
   * <em>experience_flag9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag9IsModified() {
    return experience_flag9_is_modified;
  }


  /** 
   * <em>experience_flag10</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getExperienceFlag10()
  {
    return ( experience_flag10==null? ((int)0): experience_flag10.intValue() );
  }


  /** 
   * <em>experience_flag10</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getExperienceFlag10IsNull()
  {
    return experience_flag10 == null;
  }


  /** 
   * <em>experience_flag10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag10IsSet() {
    return experience_flag10_is_set;
  }


  /** 
   * <em>experience_flag10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceFlag10IsModified() {
    return experience_flag10_is_modified;
  }


  /** 
   * <em>experience_div1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDiv1()
  {
    return experience_div1;
  }


  /** 
   * <em>experience_div1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv1IsSet() {
    return experience_div1_is_set;
  }


  /** 
   * <em>experience_div1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv1IsModified() {
    return experience_div1_is_modified;
  }


  /** 
   * <em>experience_div2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDiv2()
  {
    return experience_div2;
  }


  /** 
   * <em>experience_div2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv2IsSet() {
    return experience_div2_is_set;
  }


  /** 
   * <em>experience_div2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv2IsModified() {
    return experience_div2_is_modified;
  }


  /** 
   * <em>experience_div3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDiv3()
  {
    return experience_div3;
  }


  /** 
   * <em>experience_div3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv3IsSet() {
    return experience_div3_is_set;
  }


  /** 
   * <em>experience_div3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv3IsModified() {
    return experience_div3_is_modified;
  }


  /** 
   * <em>experience_div4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDiv4()
  {
    return experience_div4;
  }


  /** 
   * <em>experience_div4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv4IsSet() {
    return experience_div4_is_set;
  }


  /** 
   * <em>experience_div4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv4IsModified() {
    return experience_div4_is_modified;
  }


  /** 
   * <em>experience_div5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDiv5()
  {
    return experience_div5;
  }


  /** 
   * <em>experience_div5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv5IsSet() {
    return experience_div5_is_set;
  }


  /** 
   * <em>experience_div5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv5IsModified() {
    return experience_div5_is_modified;
  }


  /** 
   * <em>experience_div6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDiv6()
  {
    return experience_div6;
  }


  /** 
   * <em>experience_div6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv6IsSet() {
    return experience_div6_is_set;
  }


  /** 
   * <em>experience_div6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv6IsModified() {
    return experience_div6_is_modified;
  }


  /** 
   * <em>experience_div7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDiv7()
  {
    return experience_div7;
  }


  /** 
   * <em>experience_div7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv7IsSet() {
    return experience_div7_is_set;
  }


  /** 
   * <em>experience_div7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv7IsModified() {
    return experience_div7_is_modified;
  }


  /** 
   * <em>experience_div8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDiv8()
  {
    return experience_div8;
  }


  /** 
   * <em>experience_div8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv8IsSet() {
    return experience_div8_is_set;
  }


  /** 
   * <em>experience_div8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv8IsModified() {
    return experience_div8_is_modified;
  }


  /** 
   * <em>experience_div9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDiv9()
  {
    return experience_div9;
  }


  /** 
   * <em>experience_div9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv9IsSet() {
    return experience_div9_is_set;
  }


  /** 
   * <em>experience_div9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv9IsModified() {
    return experience_div9_is_modified;
  }


  /** 
   * <em>experience_div10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExperienceDiv10()
  {
    return experience_div10;
  }


  /** 
   * <em>experience_div10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv10IsSet() {
    return experience_div10_is_set;
  }


  /** 
   * <em>experience_div10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExperienceDiv10IsModified() {
    return experience_div10_is_modified;
  }


  /** 
   * <em>interest_flag1</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getInterestFlag1()
  {
    return ( interest_flag1==null? ((int)0): interest_flag1.intValue() );
  }


  /** 
   * <em>interest_flag1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getInterestFlag1IsNull()
  {
    return interest_flag1 == null;
  }


  /** 
   * <em>interest_flag1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag1IsSet() {
    return interest_flag1_is_set;
  }


  /** 
   * <em>interest_flag1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag1IsModified() {
    return interest_flag1_is_modified;
  }


  /** 
   * <em>interest_flag2</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getInterestFlag2()
  {
    return ( interest_flag2==null? ((int)0): interest_flag2.intValue() );
  }


  /** 
   * <em>interest_flag2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getInterestFlag2IsNull()
  {
    return interest_flag2 == null;
  }


  /** 
   * <em>interest_flag2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag2IsSet() {
    return interest_flag2_is_set;
  }


  /** 
   * <em>interest_flag2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag2IsModified() {
    return interest_flag2_is_modified;
  }


  /** 
   * <em>interest_flag3</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getInterestFlag3()
  {
    return ( interest_flag3==null? ((int)0): interest_flag3.intValue() );
  }


  /** 
   * <em>interest_flag3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getInterestFlag3IsNull()
  {
    return interest_flag3 == null;
  }


  /** 
   * <em>interest_flag3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag3IsSet() {
    return interest_flag3_is_set;
  }


  /** 
   * <em>interest_flag3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag3IsModified() {
    return interest_flag3_is_modified;
  }


  /** 
   * <em>interest_flag4</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getInterestFlag4()
  {
    return ( interest_flag4==null? ((int)0): interest_flag4.intValue() );
  }


  /** 
   * <em>interest_flag4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getInterestFlag4IsNull()
  {
    return interest_flag4 == null;
  }


  /** 
   * <em>interest_flag4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag4IsSet() {
    return interest_flag4_is_set;
  }


  /** 
   * <em>interest_flag4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag4IsModified() {
    return interest_flag4_is_modified;
  }


  /** 
   * <em>interest_flag5</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getInterestFlag5()
  {
    return ( interest_flag5==null? ((int)0): interest_flag5.intValue() );
  }


  /** 
   * <em>interest_flag5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getInterestFlag5IsNull()
  {
    return interest_flag5 == null;
  }


  /** 
   * <em>interest_flag5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag5IsSet() {
    return interest_flag5_is_set;
  }


  /** 
   * <em>interest_flag5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag5IsModified() {
    return interest_flag5_is_modified;
  }


  /** 
   * <em>interest_flag6</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getInterestFlag6()
  {
    return ( interest_flag6==null? ((int)0): interest_flag6.intValue() );
  }


  /** 
   * <em>interest_flag6</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getInterestFlag6IsNull()
  {
    return interest_flag6 == null;
  }


  /** 
   * <em>interest_flag6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag6IsSet() {
    return interest_flag6_is_set;
  }


  /** 
   * <em>interest_flag6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag6IsModified() {
    return interest_flag6_is_modified;
  }


  /** 
   * <em>interest_flag7</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getInterestFlag7()
  {
    return ( interest_flag7==null? ((int)0): interest_flag7.intValue() );
  }


  /** 
   * <em>interest_flag7</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getInterestFlag7IsNull()
  {
    return interest_flag7 == null;
  }


  /** 
   * <em>interest_flag7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag7IsSet() {
    return interest_flag7_is_set;
  }


  /** 
   * <em>interest_flag7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag7IsModified() {
    return interest_flag7_is_modified;
  }


  /** 
   * <em>interest_flag8</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getInterestFlag8()
  {
    return ( interest_flag8==null? ((int)0): interest_flag8.intValue() );
  }


  /** 
   * <em>interest_flag8</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getInterestFlag8IsNull()
  {
    return interest_flag8 == null;
  }


  /** 
   * <em>interest_flag8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag8IsSet() {
    return interest_flag8_is_set;
  }


  /** 
   * <em>interest_flag8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag8IsModified() {
    return interest_flag8_is_modified;
  }


  /** 
   * <em>interest_flag9</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getInterestFlag9()
  {
    return ( interest_flag9==null? ((int)0): interest_flag9.intValue() );
  }


  /** 
   * <em>interest_flag9</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getInterestFlag9IsNull()
  {
    return interest_flag9 == null;
  }


  /** 
   * <em>interest_flag9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag9IsSet() {
    return interest_flag9_is_set;
  }


  /** 
   * <em>interest_flag9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag9IsModified() {
    return interest_flag9_is_modified;
  }


  /** 
   * <em>interest_flag10</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getInterestFlag10()
  {
    return ( interest_flag10==null? ((int)0): interest_flag10.intValue() );
  }


  /** 
   * <em>interest_flag10</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getInterestFlag10IsNull()
  {
    return interest_flag10 == null;
  }


  /** 
   * <em>interest_flag10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag10IsSet() {
    return interest_flag10_is_set;
  }


  /** 
   * <em>interest_flag10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInterestFlag10IsModified() {
    return interest_flag10_is_modified;
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
   * <em>appli_motivat_div_detail</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAppliMotivatDivDetail()
  {
    return appli_motivat_div_detail;
  }


  /** 
   * <em>appli_motivat_div_detail</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliMotivatDivDetailIsSet() {
    return appli_motivat_div_detail_is_set;
  }


  /** 
   * <em>appli_motivat_div_detail</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAppliMotivatDivDetailIsModified() {
    return appli_motivat_div_detail_is_modified;
  }


  /** 
   * <em>use_institution_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getUseInstitutionDiv()
  {
    return use_institution_div;
  }


  /** 
   * <em>use_institution_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUseInstitutionDivIsSet() {
    return use_institution_div_is_set;
  }


  /** 
   * <em>use_institution_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUseInstitutionDivIsModified() {
    return use_institution_div_is_modified;
  }


  /** 
   * <em>internet_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInternetTradeDiv()
  {
    return internet_trade_div;
  }


  /** 
   * <em>internet_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInternetTradeDivIsSet() {
    return internet_trade_div_is_set;
  }


  /** 
   * <em>internet_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInternetTradeDivIsModified() {
    return internet_trade_div_is_modified;
  }


  /** 
   * <em>introduce_branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIntroduceBranchCode()
  {
    return introduce_branch_code;
  }


  /** 
   * <em>introduce_branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIntroduceBranchCodeIsSet() {
    return introduce_branch_code_is_set;
  }


  /** 
   * <em>introduce_branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIntroduceBranchCodeIsModified() {
    return introduce_branch_code_is_modified;
  }


  /** 
   * <em>accept_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAcceptStatus()
  {
    return accept_status;
  }


  /** 
   * <em>accept_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAcceptStatusIsSet() {
    return accept_status_is_set;
  }


  /** 
   * <em>accept_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAcceptStatusIsModified() {
    return accept_status_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new MobileOfficeInfoRegistPK(mobile_office_info_regist_id);
  }


  /** 
   * <em>mobile_office_info_regist_id</em>カラムの値を設定します。 
   *
   * @@param p_mobileOfficeInfoRegistId <em>mobile_office_info_regist_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setMobileOfficeInfoRegistId( long p_mobileOfficeInfoRegistId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mobile_office_info_regist_id = p_mobileOfficeInfoRegistId;
    mobile_office_info_regist_id_is_set = true;
    mobile_office_info_regist_id_is_modified = true;
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
   * <em>regist_updater</em>カラムの値を設定します。 
   *
   * @@param p_registUpdater <em>regist_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setRegistUpdater( String p_registUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    regist_updater = p_registUpdater;
    regist_updater_is_set = true;
    regist_updater_is_modified = true;
  }


  /** 
   * <em>decision_flag</em>カラムの値を設定します。 
   *
   * @@param p_decisionFlag <em>decision_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setDecisionFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_decisionFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    decision_flag = p_decisionFlag;
    decision_flag_is_set = true;
    decision_flag_is_modified = true;
  }


  /** 
   * <em>decision</em>カラムの値を設定します。 
   *
   * @@param p_decision <em>decision</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setDecision( int p_decision )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    decision = new Integer(p_decision);
    decision_is_set = true;
    decision_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>decision</em>カラムに値を設定します。 
   */
  public final void setDecision( Integer p_decision )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    decision = p_decision;
    decision_is_set = true;
    decision_is_modified = true;
  }


  /** 
   * <em>decision_updater</em>カラムの値を設定します。 
   *
   * @@param p_decisionUpdater <em>decision_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setDecisionUpdater( String p_decisionUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    decision_updater = p_decisionUpdater;
    decision_updater_is_set = true;
    decision_updater_is_modified = true;
  }


  /** 
   * <em>decision_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_decisionTimestamp <em>decision_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setDecisionTimestamp( java.sql.Timestamp p_decisionTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    decision_timestamp = p_decisionTimestamp;
    decision_timestamp_is_set = true;
    decision_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>decision_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setDecisionTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    decision_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    decision_timestamp_is_set = true;
    decision_timestamp_is_modified = true;
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
   * <em>contact_priority1</em>カラムの値を設定します。 
   *
   * @@param p_contactPriority1 <em>contact_priority1</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setContactPriority1( String p_contactPriority1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contact_priority1 = p_contactPriority1;
    contact_priority1_is_set = true;
    contact_priority1_is_modified = true;
  }


  /** 
   * <em>contact_priority2</em>カラムの値を設定します。 
   *
   * @@param p_contactPriority2 <em>contact_priority2</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setContactPriority2( String p_contactPriority2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contact_priority2 = p_contactPriority2;
    contact_priority2_is_set = true;
    contact_priority2_is_modified = true;
  }


  /** 
   * <em>contact_priority3</em>カラムの値を設定します。 
   *
   * @@param p_contactPriority3 <em>contact_priority3</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setContactPriority3( String p_contactPriority3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contact_priority3 = p_contactPriority3;
    contact_priority3_is_set = true;
    contact_priority3_is_modified = true;
  }


  /** 
   * <em>real_name1</em>カラムの値を設定します。 
   *
   * @@param p_realName1 <em>real_name1</em>カラムの値をあらわす66桁以下のString値 
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
   * @@param p_realName2 <em>real_name2</em>カラムの値をあらわす66桁以下のString値 
   */
  public final void setRealName2( String p_realName2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    real_name2 = p_realName2;
    real_name2_is_set = true;
    real_name2_is_modified = true;
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
   * <em>nationality</em>カラムの値を設定します。 
   *
   * @@param p_nationality <em>nationality</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setNationality( String p_nationality )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    nationality = p_nationality;
    nationality_is_set = true;
    nationality_is_modified = true;
  }


  /** 
   * <em>nationality_other</em>カラムの値を設定します。 
   *
   * @@param p_nationalityOther <em>nationality_other</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setNationalityOther( String p_nationalityOther )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    nationality_other = p_nationalityOther;
    nationality_other_is_set = true;
    nationality_other_is_modified = true;
  }


  /** 
   * <em>represent_family_name</em>カラムの値を設定します。 
   *
   * @@param p_representFamilyName <em>represent_family_name</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setRepresentFamilyName( String p_representFamilyName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    represent_family_name = p_representFamilyName;
    represent_family_name_is_set = true;
    represent_family_name_is_modified = true;
  }


  /** 
   * <em>represent_given_name</em>カラムの値を設定します。 
   *
   * @@param p_representGivenName <em>represent_given_name</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setRepresentGivenName( String p_representGivenName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    represent_given_name = p_representGivenName;
    represent_given_name_is_set = true;
    represent_given_name_is_modified = true;
  }


  /** 
   * <em>represent_family_name_alt1</em>カラムの値を設定します。 
   *
   * @@param p_representFamilyNameAlt1 <em>represent_family_name_alt1</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setRepresentFamilyNameAlt1( String p_representFamilyNameAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    represent_family_name_alt1 = p_representFamilyNameAlt1;
    represent_family_name_alt1_is_set = true;
    represent_family_name_alt1_is_modified = true;
  }


  /** 
   * <em>represent_given_name_alt1</em>カラムの値を設定します。 
   *
   * @@param p_representGivenNameAlt1 <em>represent_given_name_alt1</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setRepresentGivenNameAlt1( String p_representGivenNameAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    represent_given_name_alt1 = p_representGivenNameAlt1;
    represent_given_name_alt1_is_set = true;
    represent_given_name_alt1_is_modified = true;
  }


  /** 
   * <em>represent_power</em>カラムの値を設定します。 
   *
   * @@param p_representPower <em>represent_power</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setRepresentPower( String p_representPower )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    represent_power = p_representPower;
    represent_power_is_set = true;
    represent_power_is_modified = true;
  }


  /** 
   * <em>director_family_name</em>カラムの値を設定します。 
   *
   * @@param p_directorFamilyName <em>director_family_name</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setDirectorFamilyName( String p_directorFamilyName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    director_family_name = p_directorFamilyName;
    director_family_name_is_set = true;
    director_family_name_is_modified = true;
  }


  /** 
   * <em>director_given_name</em>カラムの値を設定します。 
   *
   * @@param p_directorGivenName <em>director_given_name</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setDirectorGivenName( String p_directorGivenName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    director_given_name = p_directorGivenName;
    director_given_name_is_set = true;
    director_given_name_is_modified = true;
  }


  /** 
   * <em>director_family_name_alt1</em>カラムの値を設定します。 
   *
   * @@param p_directorFamilyNameAlt1 <em>director_family_name_alt1</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setDirectorFamilyNameAlt1( String p_directorFamilyNameAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    director_family_name_alt1 = p_directorFamilyNameAlt1;
    director_family_name_alt1_is_set = true;
    director_family_name_alt1_is_modified = true;
  }


  /** 
   * <em>director_given_name_alt1</em>カラムの値を設定します。 
   *
   * @@param p_directorGivenNameAlt1 <em>director_given_name_alt1</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setDirectorGivenNameAlt1( String p_directorGivenNameAlt1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    director_given_name_alt1 = p_directorGivenNameAlt1;
    director_given_name_alt1_is_set = true;
    director_given_name_alt1_is_modified = true;
  }


  /** 
   * <em>director_department</em>カラムの値を設定します。 
   *
   * @@param p_directorDepartment <em>director_department</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setDirectorDepartment( String p_directorDepartment )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    director_department = p_directorDepartment;
    director_department_is_set = true;
    director_department_is_modified = true;
  }


  /** 
   * <em>director_post</em>カラムの値を設定します。 
   *
   * @@param p_directorPost <em>director_post</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setDirectorPost( String p_directorPost )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    director_post = p_directorPost;
    director_post_is_set = true;
    director_post_is_modified = true;
  }


  /** 
   * <em>director_zip_code</em>カラムの値を設定します。 
   *
   * @@param p_directorZipCode <em>director_zip_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setDirectorZipCode( String p_directorZipCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    director_zip_code = p_directorZipCode;
    director_zip_code_is_set = true;
    director_zip_code_is_modified = true;
  }


  /** 
   * <em>director_address1</em>カラムの値を設定します。 
   *
   * @@param p_directorAddress1 <em>director_address1</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setDirectorAddress1( String p_directorAddress1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    director_address1 = p_directorAddress1;
    director_address1_is_set = true;
    director_address1_is_modified = true;
  }


  /** 
   * <em>director_address2</em>カラムの値を設定します。 
   *
   * @@param p_directorAddress2 <em>director_address2</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setDirectorAddress2( String p_directorAddress2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    director_address2 = p_directorAddress2;
    director_address2_is_set = true;
    director_address2_is_modified = true;
  }


  /** 
   * <em>director_address3</em>カラムの値を設定します。 
   *
   * @@param p_directorAddress3 <em>director_address3</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setDirectorAddress3( String p_directorAddress3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    director_address3 = p_directorAddress3;
    director_address3_is_set = true;
    director_address3_is_modified = true;
  }


  /** 
   * <em>director_era_born</em>カラムの値を設定します。 
   *
   * @@param p_directorEraBorn <em>director_era_born</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDirectorEraBorn( String p_directorEraBorn )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    director_era_born = p_directorEraBorn;
    director_era_born_is_set = true;
    director_era_born_is_modified = true;
  }


  /** 
   * <em>director_born_date</em>カラムの値を設定します。 
   *
   * @@param p_directorBornDate <em>director_born_date</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setDirectorBornDate( String p_directorBornDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    director_born_date = p_directorBornDate;
    director_born_date_is_set = true;
    director_born_date_is_modified = true;
  }


  /** 
   * <em>director_corp_telephone</em>カラムの値を設定します。 
   *
   * @@param p_directorCorpTelephone <em>director_corp_telephone</em>カラムの値をあらわす16桁以下のString値 
   */
  public final void setDirectorCorpTelephone( String p_directorCorpTelephone )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    director_corp_telephone = p_directorCorpTelephone;
    director_corp_telephone_is_set = true;
    director_corp_telephone_is_modified = true;
  }


  /** 
   * <em>other_contact</em>カラムの値を設定します。 
   *
   * @@param p_otherContact <em>other_contact</em>カラムの値をあらわす16桁以下のString値 
   */
  public final void setOtherContact( String p_otherContact )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_contact = p_otherContact;
    other_contact_is_set = true;
    other_contact_is_modified = true;
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
   * <em>annual_income_div</em>カラムの値を設定します。 
   *
   * @@param p_annualIncomeDiv <em>annual_income_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setAnnualIncomeDiv( String p_annualIncomeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    annual_income_div = p_annualIncomeDiv;
    annual_income_div_is_set = true;
    annual_income_div_is_modified = true;
  }


  /** 
   * <em>asset_value_div</em>カラムの値を設定します。 
   *
   * @@param p_assetValueDiv <em>asset_value_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setAssetValueDiv( String p_assetValueDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    asset_value_div = p_assetValueDiv;
    asset_value_div_is_set = true;
    asset_value_div_is_modified = true;
  }


  /** 
   * <em>fund_budget_amount_div</em>カラムの値を設定します。 
   *
   * @@param p_fundBudgetAmountDiv <em>fund_budget_amount_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setFundBudgetAmountDiv( String p_fundBudgetAmountDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fund_budget_amount_div = p_fundBudgetAmountDiv;
    fund_budget_amount_div_is_set = true;
    fund_budget_amount_div_is_modified = true;
  }


  /** 
   * <em>invest_purpose_div</em>カラムの値を設定します。 
   *
   * @@param p_investPurposeDiv <em>invest_purpose_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setInvestPurposeDiv( String p_investPurposeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    invest_purpose_div = p_investPurposeDiv;
    invest_purpose_div_is_set = true;
    invest_purpose_div_is_modified = true;
  }


  /** 
   * <em>invest_plan_period_div</em>カラムの値を設定します。 
   *
   * @@param p_investPlanPeriodDiv <em>invest_plan_period_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setInvestPlanPeriodDiv( String p_investPlanPeriodDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    invest_plan_period_div = p_investPlanPeriodDiv;
    invest_plan_period_div_is_set = true;
    invest_plan_period_div_is_modified = true;
  }


  /** 
   * <em>experience_flag1</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlag1 <em>experience_flag1</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setExperienceFlag1( int p_experienceFlag1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag1 = new Integer(p_experienceFlag1);
    experience_flag1_is_set = true;
    experience_flag1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>experience_flag1</em>カラムに値を設定します。 
   */
  public final void setExperienceFlag1( Integer p_experienceFlag1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag1 = p_experienceFlag1;
    experience_flag1_is_set = true;
    experience_flag1_is_modified = true;
  }


  /** 
   * <em>experience_flag2</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlag2 <em>experience_flag2</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setExperienceFlag2( int p_experienceFlag2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag2 = new Integer(p_experienceFlag2);
    experience_flag2_is_set = true;
    experience_flag2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>experience_flag2</em>カラムに値を設定します。 
   */
  public final void setExperienceFlag2( Integer p_experienceFlag2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag2 = p_experienceFlag2;
    experience_flag2_is_set = true;
    experience_flag2_is_modified = true;
  }


  /** 
   * <em>experience_flag3</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlag3 <em>experience_flag3</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setExperienceFlag3( int p_experienceFlag3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag3 = new Integer(p_experienceFlag3);
    experience_flag3_is_set = true;
    experience_flag3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>experience_flag3</em>カラムに値を設定します。 
   */
  public final void setExperienceFlag3( Integer p_experienceFlag3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag3 = p_experienceFlag3;
    experience_flag3_is_set = true;
    experience_flag3_is_modified = true;
  }


  /** 
   * <em>experience_flag4</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlag4 <em>experience_flag4</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setExperienceFlag4( int p_experienceFlag4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag4 = new Integer(p_experienceFlag4);
    experience_flag4_is_set = true;
    experience_flag4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>experience_flag4</em>カラムに値を設定します。 
   */
  public final void setExperienceFlag4( Integer p_experienceFlag4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag4 = p_experienceFlag4;
    experience_flag4_is_set = true;
    experience_flag4_is_modified = true;
  }


  /** 
   * <em>experience_flag5</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlag5 <em>experience_flag5</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setExperienceFlag5( int p_experienceFlag5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag5 = new Integer(p_experienceFlag5);
    experience_flag5_is_set = true;
    experience_flag5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>experience_flag5</em>カラムに値を設定します。 
   */
  public final void setExperienceFlag5( Integer p_experienceFlag5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag5 = p_experienceFlag5;
    experience_flag5_is_set = true;
    experience_flag5_is_modified = true;
  }


  /** 
   * <em>experience_flag6</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlag6 <em>experience_flag6</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setExperienceFlag6( int p_experienceFlag6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag6 = new Integer(p_experienceFlag6);
    experience_flag6_is_set = true;
    experience_flag6_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>experience_flag6</em>カラムに値を設定します。 
   */
  public final void setExperienceFlag6( Integer p_experienceFlag6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag6 = p_experienceFlag6;
    experience_flag6_is_set = true;
    experience_flag6_is_modified = true;
  }


  /** 
   * <em>experience_flag7</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlag7 <em>experience_flag7</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setExperienceFlag7( int p_experienceFlag7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag7 = new Integer(p_experienceFlag7);
    experience_flag7_is_set = true;
    experience_flag7_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>experience_flag7</em>カラムに値を設定します。 
   */
  public final void setExperienceFlag7( Integer p_experienceFlag7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag7 = p_experienceFlag7;
    experience_flag7_is_set = true;
    experience_flag7_is_modified = true;
  }


  /** 
   * <em>experience_flag8</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlag8 <em>experience_flag8</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setExperienceFlag8( int p_experienceFlag8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag8 = new Integer(p_experienceFlag8);
    experience_flag8_is_set = true;
    experience_flag8_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>experience_flag8</em>カラムに値を設定します。 
   */
  public final void setExperienceFlag8( Integer p_experienceFlag8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag8 = p_experienceFlag8;
    experience_flag8_is_set = true;
    experience_flag8_is_modified = true;
  }


  /** 
   * <em>experience_flag9</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlag9 <em>experience_flag9</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setExperienceFlag9( int p_experienceFlag9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag9 = new Integer(p_experienceFlag9);
    experience_flag9_is_set = true;
    experience_flag9_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>experience_flag9</em>カラムに値を設定します。 
   */
  public final void setExperienceFlag9( Integer p_experienceFlag9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag9 = p_experienceFlag9;
    experience_flag9_is_set = true;
    experience_flag9_is_modified = true;
  }


  /** 
   * <em>experience_flag10</em>カラムの値を設定します。 
   *
   * @@param p_experienceFlag10 <em>experience_flag10</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setExperienceFlag10( int p_experienceFlag10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag10 = new Integer(p_experienceFlag10);
    experience_flag10_is_set = true;
    experience_flag10_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>experience_flag10</em>カラムに値を設定します。 
   */
  public final void setExperienceFlag10( Integer p_experienceFlag10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    experience_flag10 = p_experienceFlag10;
    experience_flag10_is_set = true;
    experience_flag10_is_modified = true;
  }


  /** 
   * <em>experience_div1</em>カラムの値を設定します。 
   *
   * @@param p_experienceDiv1 <em>experience_div1</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExperienceDiv1( String p_experienceDiv1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div1 = p_experienceDiv1;
    experience_div1_is_set = true;
    experience_div1_is_modified = true;
  }


  /** 
   * <em>experience_div2</em>カラムの値を設定します。 
   *
   * @@param p_experienceDiv2 <em>experience_div2</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExperienceDiv2( String p_experienceDiv2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div2 = p_experienceDiv2;
    experience_div2_is_set = true;
    experience_div2_is_modified = true;
  }


  /** 
   * <em>experience_div3</em>カラムの値を設定します。 
   *
   * @@param p_experienceDiv3 <em>experience_div3</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExperienceDiv3( String p_experienceDiv3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div3 = p_experienceDiv3;
    experience_div3_is_set = true;
    experience_div3_is_modified = true;
  }


  /** 
   * <em>experience_div4</em>カラムの値を設定します。 
   *
   * @@param p_experienceDiv4 <em>experience_div4</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExperienceDiv4( String p_experienceDiv4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div4 = p_experienceDiv4;
    experience_div4_is_set = true;
    experience_div4_is_modified = true;
  }


  /** 
   * <em>experience_div5</em>カラムの値を設定します。 
   *
   * @@param p_experienceDiv5 <em>experience_div5</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExperienceDiv5( String p_experienceDiv5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div5 = p_experienceDiv5;
    experience_div5_is_set = true;
    experience_div5_is_modified = true;
  }


  /** 
   * <em>experience_div6</em>カラムの値を設定します。 
   *
   * @@param p_experienceDiv6 <em>experience_div6</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExperienceDiv6( String p_experienceDiv6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div6 = p_experienceDiv6;
    experience_div6_is_set = true;
    experience_div6_is_modified = true;
  }


  /** 
   * <em>experience_div7</em>カラムの値を設定します。 
   *
   * @@param p_experienceDiv7 <em>experience_div7</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExperienceDiv7( String p_experienceDiv7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div7 = p_experienceDiv7;
    experience_div7_is_set = true;
    experience_div7_is_modified = true;
  }


  /** 
   * <em>experience_div8</em>カラムの値を設定します。 
   *
   * @@param p_experienceDiv8 <em>experience_div8</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExperienceDiv8( String p_experienceDiv8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div8 = p_experienceDiv8;
    experience_div8_is_set = true;
    experience_div8_is_modified = true;
  }


  /** 
   * <em>experience_div9</em>カラムの値を設定します。 
   *
   * @@param p_experienceDiv9 <em>experience_div9</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExperienceDiv9( String p_experienceDiv9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div9 = p_experienceDiv9;
    experience_div9_is_set = true;
    experience_div9_is_modified = true;
  }


  /** 
   * <em>experience_div10</em>カラムの値を設定します。 
   *
   * @@param p_experienceDiv10 <em>experience_div10</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setExperienceDiv10( String p_experienceDiv10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    experience_div10 = p_experienceDiv10;
    experience_div10_is_set = true;
    experience_div10_is_modified = true;
  }


  /** 
   * <em>interest_flag1</em>カラムの値を設定します。 
   *
   * @@param p_interestFlag1 <em>interest_flag1</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setInterestFlag1( int p_interestFlag1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag1 = new Integer(p_interestFlag1);
    interest_flag1_is_set = true;
    interest_flag1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>interest_flag1</em>カラムに値を設定します。 
   */
  public final void setInterestFlag1( Integer p_interestFlag1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag1 = p_interestFlag1;
    interest_flag1_is_set = true;
    interest_flag1_is_modified = true;
  }


  /** 
   * <em>interest_flag2</em>カラムの値を設定します。 
   *
   * @@param p_interestFlag2 <em>interest_flag2</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setInterestFlag2( int p_interestFlag2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag2 = new Integer(p_interestFlag2);
    interest_flag2_is_set = true;
    interest_flag2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>interest_flag2</em>カラムに値を設定します。 
   */
  public final void setInterestFlag2( Integer p_interestFlag2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag2 = p_interestFlag2;
    interest_flag2_is_set = true;
    interest_flag2_is_modified = true;
  }


  /** 
   * <em>interest_flag3</em>カラムの値を設定します。 
   *
   * @@param p_interestFlag3 <em>interest_flag3</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setInterestFlag3( int p_interestFlag3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag3 = new Integer(p_interestFlag3);
    interest_flag3_is_set = true;
    interest_flag3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>interest_flag3</em>カラムに値を設定します。 
   */
  public final void setInterestFlag3( Integer p_interestFlag3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag3 = p_interestFlag3;
    interest_flag3_is_set = true;
    interest_flag3_is_modified = true;
  }


  /** 
   * <em>interest_flag4</em>カラムの値を設定します。 
   *
   * @@param p_interestFlag4 <em>interest_flag4</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setInterestFlag4( int p_interestFlag4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag4 = new Integer(p_interestFlag4);
    interest_flag4_is_set = true;
    interest_flag4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>interest_flag4</em>カラムに値を設定します。 
   */
  public final void setInterestFlag4( Integer p_interestFlag4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag4 = p_interestFlag4;
    interest_flag4_is_set = true;
    interest_flag4_is_modified = true;
  }


  /** 
   * <em>interest_flag5</em>カラムの値を設定します。 
   *
   * @@param p_interestFlag5 <em>interest_flag5</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setInterestFlag5( int p_interestFlag5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag5 = new Integer(p_interestFlag5);
    interest_flag5_is_set = true;
    interest_flag5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>interest_flag5</em>カラムに値を設定します。 
   */
  public final void setInterestFlag5( Integer p_interestFlag5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag5 = p_interestFlag5;
    interest_flag5_is_set = true;
    interest_flag5_is_modified = true;
  }


  /** 
   * <em>interest_flag6</em>カラムの値を設定します。 
   *
   * @@param p_interestFlag6 <em>interest_flag6</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setInterestFlag6( int p_interestFlag6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag6 = new Integer(p_interestFlag6);
    interest_flag6_is_set = true;
    interest_flag6_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>interest_flag6</em>カラムに値を設定します。 
   */
  public final void setInterestFlag6( Integer p_interestFlag6 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag6 = p_interestFlag6;
    interest_flag6_is_set = true;
    interest_flag6_is_modified = true;
  }


  /** 
   * <em>interest_flag7</em>カラムの値を設定します。 
   *
   * @@param p_interestFlag7 <em>interest_flag7</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setInterestFlag7( int p_interestFlag7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag7 = new Integer(p_interestFlag7);
    interest_flag7_is_set = true;
    interest_flag7_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>interest_flag7</em>カラムに値を設定します。 
   */
  public final void setInterestFlag7( Integer p_interestFlag7 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag7 = p_interestFlag7;
    interest_flag7_is_set = true;
    interest_flag7_is_modified = true;
  }


  /** 
   * <em>interest_flag8</em>カラムの値を設定します。 
   *
   * @@param p_interestFlag8 <em>interest_flag8</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setInterestFlag8( int p_interestFlag8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag8 = new Integer(p_interestFlag8);
    interest_flag8_is_set = true;
    interest_flag8_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>interest_flag8</em>カラムに値を設定します。 
   */
  public final void setInterestFlag8( Integer p_interestFlag8 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag8 = p_interestFlag8;
    interest_flag8_is_set = true;
    interest_flag8_is_modified = true;
  }


  /** 
   * <em>interest_flag9</em>カラムの値を設定します。 
   *
   * @@param p_interestFlag9 <em>interest_flag9</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setInterestFlag9( int p_interestFlag9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag9 = new Integer(p_interestFlag9);
    interest_flag9_is_set = true;
    interest_flag9_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>interest_flag9</em>カラムに値を設定します。 
   */
  public final void setInterestFlag9( Integer p_interestFlag9 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag9 = p_interestFlag9;
    interest_flag9_is_set = true;
    interest_flag9_is_modified = true;
  }


  /** 
   * <em>interest_flag10</em>カラムの値を設定します。 
   *
   * @@param p_interestFlag10 <em>interest_flag10</em>カラムの値をあらわす1桁以下のint値 
   */
  public final void setInterestFlag10( int p_interestFlag10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag10 = new Integer(p_interestFlag10);
    interest_flag10_is_set = true;
    interest_flag10_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>interest_flag10</em>カラムに値を設定します。 
   */
  public final void setInterestFlag10( Integer p_interestFlag10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    interest_flag10 = p_interestFlag10;
    interest_flag10_is_set = true;
    interest_flag10_is_modified = true;
  }


  /** 
   * <em>appli_motivat_div</em>カラムの値を設定します。 
   *
   * @@param p_appliMotivatDiv <em>appli_motivat_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setAppliMotivatDiv( String p_appliMotivatDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_motivat_div = p_appliMotivatDiv;
    appli_motivat_div_is_set = true;
    appli_motivat_div_is_modified = true;
  }


  /** 
   * <em>appli_motivat_div_detail</em>カラムの値を設定します。 
   *
   * @@param p_appliMotivatDivDetail <em>appli_motivat_div_detail</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setAppliMotivatDivDetail( String p_appliMotivatDivDetail )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    appli_motivat_div_detail = p_appliMotivatDivDetail;
    appli_motivat_div_detail_is_set = true;
    appli_motivat_div_detail_is_modified = true;
  }


  /** 
   * <em>use_institution_div</em>カラムの値を設定します。 
   *
   * @@param p_useInstitutionDiv <em>use_institution_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setUseInstitutionDiv( String p_useInstitutionDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    use_institution_div = p_useInstitutionDiv;
    use_institution_div_is_set = true;
    use_institution_div_is_modified = true;
  }


  /** 
   * <em>internet_trade_div</em>カラムの値を設定します。 
   *
   * @@param p_internetTradeDiv <em>internet_trade_div</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setInternetTradeDiv( String p_internetTradeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    internet_trade_div = p_internetTradeDiv;
    internet_trade_div_is_set = true;
    internet_trade_div_is_modified = true;
  }


  /** 
   * <em>introduce_branch_code</em>カラムの値を設定します。 
   *
   * @@param p_introduceBranchCode <em>introduce_branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setIntroduceBranchCode( String p_introduceBranchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    introduce_branch_code = p_introduceBranchCode;
    introduce_branch_code_is_set = true;
    introduce_branch_code_is_modified = true;
  }


  /** 
   * <em>accept_status</em>カラムの値を設定します。 
   *
   * @@param p_acceptStatus <em>accept_status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAcceptStatus( String p_acceptStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    accept_status = p_acceptStatus;
    accept_status_is_set = true;
    accept_status_is_modified = true;
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
                else if ( name.equals("annual_income_div") ) {
                    return this.annual_income_div;
                }
                else if ( name.equals("asset_value_div") ) {
                    return this.asset_value_div;
                }
                else if ( name.equals("appli_motivat_div") ) {
                    return this.appli_motivat_div;
                }
                else if ( name.equals("appli_motivat_div_detail") ) {
                    return this.appli_motivat_div_detail;
                }
                else if ( name.equals("accept_status") ) {
                    return this.accept_status;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("contact_priority1") ) {
                    return this.contact_priority1;
                }
                else if ( name.equals("contact_priority2") ) {
                    return this.contact_priority2;
                }
                else if ( name.equals("contact_priority3") ) {
                    return this.contact_priority3;
                }
                break;
            case 'd':
                if ( name.equals("decision_flag") ) {
                    return this.decision_flag;
                }
                else if ( name.equals("decision") ) {
                    return this.decision;
                }
                else if ( name.equals("decision_updater") ) {
                    return this.decision_updater;
                }
                else if ( name.equals("decision_timestamp") ) {
                    return this.decision_timestamp;
                }
                else if ( name.equals("delete_flag") ) {
                    return this.delete_flag;
                }
                else if ( name.equals("department") ) {
                    return this.department;
                }
                else if ( name.equals("director_family_name") ) {
                    return this.director_family_name;
                }
                else if ( name.equals("director_given_name") ) {
                    return this.director_given_name;
                }
                else if ( name.equals("director_family_name_alt1") ) {
                    return this.director_family_name_alt1;
                }
                else if ( name.equals("director_given_name_alt1") ) {
                    return this.director_given_name_alt1;
                }
                else if ( name.equals("director_department") ) {
                    return this.director_department;
                }
                else if ( name.equals("director_post") ) {
                    return this.director_post;
                }
                else if ( name.equals("director_zip_code") ) {
                    return this.director_zip_code;
                }
                else if ( name.equals("director_address1") ) {
                    return this.director_address1;
                }
                else if ( name.equals("director_address2") ) {
                    return this.director_address2;
                }
                else if ( name.equals("director_address3") ) {
                    return this.director_address3;
                }
                else if ( name.equals("director_era_born") ) {
                    return this.director_era_born;
                }
                else if ( name.equals("director_born_date") ) {
                    return this.director_born_date;
                }
                else if ( name.equals("director_corp_telephone") ) {
                    return this.director_corp_telephone;
                }
                break;
            case 'e':
                if ( name.equals("experience_flag1") ) {
                    return this.experience_flag1;
                }
                else if ( name.equals("experience_flag2") ) {
                    return this.experience_flag2;
                }
                else if ( name.equals("experience_flag3") ) {
                    return this.experience_flag3;
                }
                else if ( name.equals("experience_flag4") ) {
                    return this.experience_flag4;
                }
                else if ( name.equals("experience_flag5") ) {
                    return this.experience_flag5;
                }
                else if ( name.equals("experience_flag6") ) {
                    return this.experience_flag6;
                }
                else if ( name.equals("experience_flag7") ) {
                    return this.experience_flag7;
                }
                else if ( name.equals("experience_flag8") ) {
                    return this.experience_flag8;
                }
                else if ( name.equals("experience_flag9") ) {
                    return this.experience_flag9;
                }
                else if ( name.equals("experience_flag10") ) {
                    return this.experience_flag10;
                }
                else if ( name.equals("experience_div1") ) {
                    return this.experience_div1;
                }
                else if ( name.equals("experience_div2") ) {
                    return this.experience_div2;
                }
                else if ( name.equals("experience_div3") ) {
                    return this.experience_div3;
                }
                else if ( name.equals("experience_div4") ) {
                    return this.experience_div4;
                }
                else if ( name.equals("experience_div5") ) {
                    return this.experience_div5;
                }
                else if ( name.equals("experience_div6") ) {
                    return this.experience_div6;
                }
                else if ( name.equals("experience_div7") ) {
                    return this.experience_div7;
                }
                else if ( name.equals("experience_div8") ) {
                    return this.experience_div8;
                }
                else if ( name.equals("experience_div9") ) {
                    return this.experience_div9;
                }
                else if ( name.equals("experience_div10") ) {
                    return this.experience_div10;
                }
                break;
            case 'f':
                if ( name.equals("fax") ) {
                    return this.fax;
                }
                else if ( name.equals("fund_budget_amount_div") ) {
                    return this.fund_budget_amount_div;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("invest_purpose_div") ) {
                    return this.invest_purpose_div;
                }
                else if ( name.equals("invest_plan_period_div") ) {
                    return this.invest_plan_period_div;
                }
                else if ( name.equals("interest_flag1") ) {
                    return this.interest_flag1;
                }
                else if ( name.equals("interest_flag2") ) {
                    return this.interest_flag2;
                }
                else if ( name.equals("interest_flag3") ) {
                    return this.interest_flag3;
                }
                else if ( name.equals("interest_flag4") ) {
                    return this.interest_flag4;
                }
                else if ( name.equals("interest_flag5") ) {
                    return this.interest_flag5;
                }
                else if ( name.equals("interest_flag6") ) {
                    return this.interest_flag6;
                }
                else if ( name.equals("interest_flag7") ) {
                    return this.interest_flag7;
                }
                else if ( name.equals("interest_flag8") ) {
                    return this.interest_flag8;
                }
                else if ( name.equals("interest_flag9") ) {
                    return this.interest_flag9;
                }
                else if ( name.equals("interest_flag10") ) {
                    return this.interest_flag10;
                }
                else if ( name.equals("internet_trade_div") ) {
                    return this.internet_trade_div;
                }
                else if ( name.equals("introduce_branch_code") ) {
                    return this.introduce_branch_code;
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
                if ( name.equals("mobile_office_info_regist_id") ) {
                    return new Long( mobile_office_info_regist_id );
                }
                else if ( name.equals("mobile") ) {
                    return this.mobile;
                }
                break;
            case 'n':
                if ( name.equals("nationality") ) {
                    return this.nationality;
                }
                else if ( name.equals("nationality_other") ) {
                    return this.nationality_other;
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
                else if ( name.equals("occupation_div") ) {
                    return this.occupation_div;
                }
                else if ( name.equals("other_contact") ) {
                    return this.other_contact;
                }
                break;
            case 'p':
                if ( name.equals("post") ) {
                    return this.post;
                }
                break;
            case 'r':
                if ( name.equals("regist_updater") ) {
                    return this.regist_updater;
                }
                else if ( name.equals("real_name1") ) {
                    return this.real_name1;
                }
                else if ( name.equals("real_name2") ) {
                    return this.real_name2;
                }
                else if ( name.equals("represent_family_name") ) {
                    return this.represent_family_name;
                }
                else if ( name.equals("represent_given_name") ) {
                    return this.represent_given_name;
                }
                else if ( name.equals("represent_family_name_alt1") ) {
                    return this.represent_family_name_alt1;
                }
                else if ( name.equals("represent_given_name_alt1") ) {
                    return this.represent_given_name_alt1;
                }
                else if ( name.equals("represent_power") ) {
                    return this.represent_power;
                }
                break;
            case 'u':
                if ( name.equals("use_institution_div") ) {
                    return this.use_institution_div;
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
                else if ( name.equals("appli_motivat_div_detail") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'appli_motivat_div_detail' must be String: '"+value+"' is not." );
                    this.appli_motivat_div_detail = (String) value;
                    if (this.appli_motivat_div_detail_is_set)
                        this.appli_motivat_div_detail_is_modified = true;
                    this.appli_motivat_div_detail_is_set = true;
                    return;
                }
                else if ( name.equals("accept_status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'accept_status' must be String: '"+value+"' is not." );
                    this.accept_status = (String) value;
                    if (this.accept_status_is_set)
                        this.accept_status_is_modified = true;
                    this.accept_status_is_set = true;
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
                else if ( name.equals("contact_priority1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'contact_priority1' must be String: '"+value+"' is not." );
                    this.contact_priority1 = (String) value;
                    if (this.contact_priority1_is_set)
                        this.contact_priority1_is_modified = true;
                    this.contact_priority1_is_set = true;
                    return;
                }
                else if ( name.equals("contact_priority2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'contact_priority2' must be String: '"+value+"' is not." );
                    this.contact_priority2 = (String) value;
                    if (this.contact_priority2_is_set)
                        this.contact_priority2_is_modified = true;
                    this.contact_priority2_is_set = true;
                    return;
                }
                else if ( name.equals("contact_priority3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'contact_priority3' must be String: '"+value+"' is not." );
                    this.contact_priority3 = (String) value;
                    if (this.contact_priority3_is_set)
                        this.contact_priority3_is_modified = true;
                    this.contact_priority3_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("decision_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'decision_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.decision_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.decision_flag_is_set)
                        this.decision_flag_is_modified = true;
                    this.decision_flag_is_set = true;
                    return;
                }
                else if ( name.equals("decision") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'decision' must be Integer: '"+value+"' is not." );
                    this.decision = (Integer) value;
                    if (this.decision_is_set)
                        this.decision_is_modified = true;
                    this.decision_is_set = true;
                    return;
                }
                else if ( name.equals("decision_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'decision_updater' must be String: '"+value+"' is not." );
                    this.decision_updater = (String) value;
                    if (this.decision_updater_is_set)
                        this.decision_updater_is_modified = true;
                    this.decision_updater_is_set = true;
                    return;
                }
                else if ( name.equals("decision_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'decision_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.decision_timestamp = (java.sql.Timestamp) value;
                    if (this.decision_timestamp_is_set)
                        this.decision_timestamp_is_modified = true;
                    this.decision_timestamp_is_set = true;
                    return;
                }
                else if ( name.equals("delete_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'delete_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.delete_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.delete_flag_is_set)
                        this.delete_flag_is_modified = true;
                    this.delete_flag_is_set = true;
                    return;
                }
                else if ( name.equals("department") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'department' must be String: '"+value+"' is not." );
                    this.department = (String) value;
                    if (this.department_is_set)
                        this.department_is_modified = true;
                    this.department_is_set = true;
                    return;
                }
                else if ( name.equals("director_family_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'director_family_name' must be String: '"+value+"' is not." );
                    this.director_family_name = (String) value;
                    if (this.director_family_name_is_set)
                        this.director_family_name_is_modified = true;
                    this.director_family_name_is_set = true;
                    return;
                }
                else if ( name.equals("director_given_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'director_given_name' must be String: '"+value+"' is not." );
                    this.director_given_name = (String) value;
                    if (this.director_given_name_is_set)
                        this.director_given_name_is_modified = true;
                    this.director_given_name_is_set = true;
                    return;
                }
                else if ( name.equals("director_family_name_alt1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'director_family_name_alt1' must be String: '"+value+"' is not." );
                    this.director_family_name_alt1 = (String) value;
                    if (this.director_family_name_alt1_is_set)
                        this.director_family_name_alt1_is_modified = true;
                    this.director_family_name_alt1_is_set = true;
                    return;
                }
                else if ( name.equals("director_given_name_alt1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'director_given_name_alt1' must be String: '"+value+"' is not." );
                    this.director_given_name_alt1 = (String) value;
                    if (this.director_given_name_alt1_is_set)
                        this.director_given_name_alt1_is_modified = true;
                    this.director_given_name_alt1_is_set = true;
                    return;
                }
                else if ( name.equals("director_department") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'director_department' must be String: '"+value+"' is not." );
                    this.director_department = (String) value;
                    if (this.director_department_is_set)
                        this.director_department_is_modified = true;
                    this.director_department_is_set = true;
                    return;
                }
                else if ( name.equals("director_post") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'director_post' must be String: '"+value+"' is not." );
                    this.director_post = (String) value;
                    if (this.director_post_is_set)
                        this.director_post_is_modified = true;
                    this.director_post_is_set = true;
                    return;
                }
                else if ( name.equals("director_zip_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'director_zip_code' must be String: '"+value+"' is not." );
                    this.director_zip_code = (String) value;
                    if (this.director_zip_code_is_set)
                        this.director_zip_code_is_modified = true;
                    this.director_zip_code_is_set = true;
                    return;
                }
                else if ( name.equals("director_address1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'director_address1' must be String: '"+value+"' is not." );
                    this.director_address1 = (String) value;
                    if (this.director_address1_is_set)
                        this.director_address1_is_modified = true;
                    this.director_address1_is_set = true;
                    return;
                }
                else if ( name.equals("director_address2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'director_address2' must be String: '"+value+"' is not." );
                    this.director_address2 = (String) value;
                    if (this.director_address2_is_set)
                        this.director_address2_is_modified = true;
                    this.director_address2_is_set = true;
                    return;
                }
                else if ( name.equals("director_address3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'director_address3' must be String: '"+value+"' is not." );
                    this.director_address3 = (String) value;
                    if (this.director_address3_is_set)
                        this.director_address3_is_modified = true;
                    this.director_address3_is_set = true;
                    return;
                }
                else if ( name.equals("director_era_born") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'director_era_born' must be String: '"+value+"' is not." );
                    this.director_era_born = (String) value;
                    if (this.director_era_born_is_set)
                        this.director_era_born_is_modified = true;
                    this.director_era_born_is_set = true;
                    return;
                }
                else if ( name.equals("director_born_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'director_born_date' must be String: '"+value+"' is not." );
                    this.director_born_date = (String) value;
                    if (this.director_born_date_is_set)
                        this.director_born_date_is_modified = true;
                    this.director_born_date_is_set = true;
                    return;
                }
                else if ( name.equals("director_corp_telephone") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'director_corp_telephone' must be String: '"+value+"' is not." );
                    this.director_corp_telephone = (String) value;
                    if (this.director_corp_telephone_is_set)
                        this.director_corp_telephone_is_modified = true;
                    this.director_corp_telephone_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("experience_flag1") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'experience_flag1' must be Integer: '"+value+"' is not." );
                    this.experience_flag1 = (Integer) value;
                    if (this.experience_flag1_is_set)
                        this.experience_flag1_is_modified = true;
                    this.experience_flag1_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag2") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'experience_flag2' must be Integer: '"+value+"' is not." );
                    this.experience_flag2 = (Integer) value;
                    if (this.experience_flag2_is_set)
                        this.experience_flag2_is_modified = true;
                    this.experience_flag2_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag3") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'experience_flag3' must be Integer: '"+value+"' is not." );
                    this.experience_flag3 = (Integer) value;
                    if (this.experience_flag3_is_set)
                        this.experience_flag3_is_modified = true;
                    this.experience_flag3_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag4") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'experience_flag4' must be Integer: '"+value+"' is not." );
                    this.experience_flag4 = (Integer) value;
                    if (this.experience_flag4_is_set)
                        this.experience_flag4_is_modified = true;
                    this.experience_flag4_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag5") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'experience_flag5' must be Integer: '"+value+"' is not." );
                    this.experience_flag5 = (Integer) value;
                    if (this.experience_flag5_is_set)
                        this.experience_flag5_is_modified = true;
                    this.experience_flag5_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag6") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'experience_flag6' must be Integer: '"+value+"' is not." );
                    this.experience_flag6 = (Integer) value;
                    if (this.experience_flag6_is_set)
                        this.experience_flag6_is_modified = true;
                    this.experience_flag6_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag7") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'experience_flag7' must be Integer: '"+value+"' is not." );
                    this.experience_flag7 = (Integer) value;
                    if (this.experience_flag7_is_set)
                        this.experience_flag7_is_modified = true;
                    this.experience_flag7_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag8") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'experience_flag8' must be Integer: '"+value+"' is not." );
                    this.experience_flag8 = (Integer) value;
                    if (this.experience_flag8_is_set)
                        this.experience_flag8_is_modified = true;
                    this.experience_flag8_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag9") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'experience_flag9' must be Integer: '"+value+"' is not." );
                    this.experience_flag9 = (Integer) value;
                    if (this.experience_flag9_is_set)
                        this.experience_flag9_is_modified = true;
                    this.experience_flag9_is_set = true;
                    return;
                }
                else if ( name.equals("experience_flag10") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'experience_flag10' must be Integer: '"+value+"' is not." );
                    this.experience_flag10 = (Integer) value;
                    if (this.experience_flag10_is_set)
                        this.experience_flag10_is_modified = true;
                    this.experience_flag10_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div1' must be String: '"+value+"' is not." );
                    this.experience_div1 = (String) value;
                    if (this.experience_div1_is_set)
                        this.experience_div1_is_modified = true;
                    this.experience_div1_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div2") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div2' must be String: '"+value+"' is not." );
                    this.experience_div2 = (String) value;
                    if (this.experience_div2_is_set)
                        this.experience_div2_is_modified = true;
                    this.experience_div2_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div3") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div3' must be String: '"+value+"' is not." );
                    this.experience_div3 = (String) value;
                    if (this.experience_div3_is_set)
                        this.experience_div3_is_modified = true;
                    this.experience_div3_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div4") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div4' must be String: '"+value+"' is not." );
                    this.experience_div4 = (String) value;
                    if (this.experience_div4_is_set)
                        this.experience_div4_is_modified = true;
                    this.experience_div4_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div5") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div5' must be String: '"+value+"' is not." );
                    this.experience_div5 = (String) value;
                    if (this.experience_div5_is_set)
                        this.experience_div5_is_modified = true;
                    this.experience_div5_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div6") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div6' must be String: '"+value+"' is not." );
                    this.experience_div6 = (String) value;
                    if (this.experience_div6_is_set)
                        this.experience_div6_is_modified = true;
                    this.experience_div6_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div7") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div7' must be String: '"+value+"' is not." );
                    this.experience_div7 = (String) value;
                    if (this.experience_div7_is_set)
                        this.experience_div7_is_modified = true;
                    this.experience_div7_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div8") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div8' must be String: '"+value+"' is not." );
                    this.experience_div8 = (String) value;
                    if (this.experience_div8_is_set)
                        this.experience_div8_is_modified = true;
                    this.experience_div8_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div9") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div9' must be String: '"+value+"' is not." );
                    this.experience_div9 = (String) value;
                    if (this.experience_div9_is_set)
                        this.experience_div9_is_modified = true;
                    this.experience_div9_is_set = true;
                    return;
                }
                else if ( name.equals("experience_div10") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'experience_div10' must be String: '"+value+"' is not." );
                    this.experience_div10 = (String) value;
                    if (this.experience_div10_is_set)
                        this.experience_div10_is_modified = true;
                    this.experience_div10_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fax") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fax' must be String: '"+value+"' is not." );
                    this.fax = (String) value;
                    if (this.fax_is_set)
                        this.fax_is_modified = true;
                    this.fax_is_set = true;
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
                else if ( name.equals("invest_plan_period_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'invest_plan_period_div' must be String: '"+value+"' is not." );
                    this.invest_plan_period_div = (String) value;
                    if (this.invest_plan_period_div_is_set)
                        this.invest_plan_period_div_is_modified = true;
                    this.invest_plan_period_div_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag1") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'interest_flag1' must be Integer: '"+value+"' is not." );
                    this.interest_flag1 = (Integer) value;
                    if (this.interest_flag1_is_set)
                        this.interest_flag1_is_modified = true;
                    this.interest_flag1_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag2") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'interest_flag2' must be Integer: '"+value+"' is not." );
                    this.interest_flag2 = (Integer) value;
                    if (this.interest_flag2_is_set)
                        this.interest_flag2_is_modified = true;
                    this.interest_flag2_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag3") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'interest_flag3' must be Integer: '"+value+"' is not." );
                    this.interest_flag3 = (Integer) value;
                    if (this.interest_flag3_is_set)
                        this.interest_flag3_is_modified = true;
                    this.interest_flag3_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag4") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'interest_flag4' must be Integer: '"+value+"' is not." );
                    this.interest_flag4 = (Integer) value;
                    if (this.interest_flag4_is_set)
                        this.interest_flag4_is_modified = true;
                    this.interest_flag4_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag5") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'interest_flag5' must be Integer: '"+value+"' is not." );
                    this.interest_flag5 = (Integer) value;
                    if (this.interest_flag5_is_set)
                        this.interest_flag5_is_modified = true;
                    this.interest_flag5_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag6") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'interest_flag6' must be Integer: '"+value+"' is not." );
                    this.interest_flag6 = (Integer) value;
                    if (this.interest_flag6_is_set)
                        this.interest_flag6_is_modified = true;
                    this.interest_flag6_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag7") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'interest_flag7' must be Integer: '"+value+"' is not." );
                    this.interest_flag7 = (Integer) value;
                    if (this.interest_flag7_is_set)
                        this.interest_flag7_is_modified = true;
                    this.interest_flag7_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag8") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'interest_flag8' must be Integer: '"+value+"' is not." );
                    this.interest_flag8 = (Integer) value;
                    if (this.interest_flag8_is_set)
                        this.interest_flag8_is_modified = true;
                    this.interest_flag8_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag9") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'interest_flag9' must be Integer: '"+value+"' is not." );
                    this.interest_flag9 = (Integer) value;
                    if (this.interest_flag9_is_set)
                        this.interest_flag9_is_modified = true;
                    this.interest_flag9_is_set = true;
                    return;
                }
                else if ( name.equals("interest_flag10") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'interest_flag10' must be Integer: '"+value+"' is not." );
                    this.interest_flag10 = (Integer) value;
                    if (this.interest_flag10_is_set)
                        this.interest_flag10_is_modified = true;
                    this.interest_flag10_is_set = true;
                    return;
                }
                else if ( name.equals("internet_trade_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'internet_trade_div' must be String: '"+value+"' is not." );
                    this.internet_trade_div = (String) value;
                    if (this.internet_trade_div_is_set)
                        this.internet_trade_div_is_modified = true;
                    this.internet_trade_div_is_set = true;
                    return;
                }
                else if ( name.equals("introduce_branch_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'introduce_branch_code' must be String: '"+value+"' is not." );
                    this.introduce_branch_code = (String) value;
                    if (this.introduce_branch_code_is_set)
                        this.introduce_branch_code_is_modified = true;
                    this.introduce_branch_code_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
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
                if ( name.equals("mobile_office_info_regist_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'mobile_office_info_regist_id' must be Long: '"+value+"' is not." );
                    this.mobile_office_info_regist_id = ((Long) value).longValue();
                    if (this.mobile_office_info_regist_id_is_set)
                        this.mobile_office_info_regist_id_is_modified = true;
                    this.mobile_office_info_regist_id_is_set = true;
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
                break;
            case 'n':
                if ( name.equals("nationality") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'nationality' must be String: '"+value+"' is not." );
                    this.nationality = (String) value;
                    if (this.nationality_is_set)
                        this.nationality_is_modified = true;
                    this.nationality_is_set = true;
                    return;
                }
                else if ( name.equals("nationality_other") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'nationality_other' must be String: '"+value+"' is not." );
                    this.nationality_other = (String) value;
                    if (this.nationality_other_is_set)
                        this.nationality_other_is_modified = true;
                    this.nationality_other_is_set = true;
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
                else if ( name.equals("other_contact") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'other_contact' must be String: '"+value+"' is not." );
                    this.other_contact = (String) value;
                    if (this.other_contact_is_set)
                        this.other_contact_is_modified = true;
                    this.other_contact_is_set = true;
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
                break;
            case 'r':
                if ( name.equals("regist_updater") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'regist_updater' must be String: '"+value+"' is not." );
                    this.regist_updater = (String) value;
                    if (this.regist_updater_is_set)
                        this.regist_updater_is_modified = true;
                    this.regist_updater_is_set = true;
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
                else if ( name.equals("represent_family_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'represent_family_name' must be String: '"+value+"' is not." );
                    this.represent_family_name = (String) value;
                    if (this.represent_family_name_is_set)
                        this.represent_family_name_is_modified = true;
                    this.represent_family_name_is_set = true;
                    return;
                }
                else if ( name.equals("represent_given_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'represent_given_name' must be String: '"+value+"' is not." );
                    this.represent_given_name = (String) value;
                    if (this.represent_given_name_is_set)
                        this.represent_given_name_is_modified = true;
                    this.represent_given_name_is_set = true;
                    return;
                }
                else if ( name.equals("represent_family_name_alt1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'represent_family_name_alt1' must be String: '"+value+"' is not." );
                    this.represent_family_name_alt1 = (String) value;
                    if (this.represent_family_name_alt1_is_set)
                        this.represent_family_name_alt1_is_modified = true;
                    this.represent_family_name_alt1_is_set = true;
                    return;
                }
                else if ( name.equals("represent_given_name_alt1") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'represent_given_name_alt1' must be String: '"+value+"' is not." );
                    this.represent_given_name_alt1 = (String) value;
                    if (this.represent_given_name_alt1_is_set)
                        this.represent_given_name_alt1_is_modified = true;
                    this.represent_given_name_alt1_is_set = true;
                    return;
                }
                else if ( name.equals("represent_power") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'represent_power' must be String: '"+value+"' is not." );
                    this.represent_power = (String) value;
                    if (this.represent_power_is_set)
                        this.represent_power_is_modified = true;
                    this.represent_power_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("use_institution_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'use_institution_div' must be String: '"+value+"' is not." );
                    this.use_institution_div = (String) value;
                    if (this.use_institution_div_is_set)
                        this.use_institution_div_is_modified = true;
                    this.use_institution_div_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
