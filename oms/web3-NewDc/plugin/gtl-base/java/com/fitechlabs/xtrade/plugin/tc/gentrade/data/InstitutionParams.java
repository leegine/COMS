head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.37.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	InstitutionParams.java;


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
 * institutionテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link InstitutionRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link InstitutionParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link InstitutionParams}が{@@link InstitutionRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see InstitutionPK 
 * @@see InstitutionRow 
 */
public class InstitutionParams extends Params implements InstitutionRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "institution";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = InstitutionRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return InstitutionRow.TYPE;
  }


  /** 
   * <em>institution_id</em>カラムの値 
   */
  public  long  institution_id;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>institution_name</em>カラムの値 
   */
  public  String  institution_name;

  /** 
   * <em>asset_evaluation</em>カラムの値 
   */
  public  String  asset_evaluation;

  /** 
   * <em>maximum_assessment</em>カラムの値 
   */
  public  Long  maximum_assessment;

  /** 
   * <em>stock_evaluation</em>カラムの値 
   */
  public  String  stock_evaluation;

  /** 
   * <em>gp_evaluation</em>カラムの値 
   */
  public  String  gp_evaluation;

  /** 
   * <em>fund_evaluation</em>カラムの値 
   */
  public  String  fund_evaluation;

  /** 
   * <em>bond_evaluation</em>カラムの値 
   */
  public  String  bond_evaluation;

  /** 
   * <em>enable_ipo_quantity_change</em>カラムの値 
   */
  public  String  enable_ipo_quantity_change;

  /** 
   * <em>deposit_inform_mail_send</em>カラムの値 
   */
  public  String  deposit_inform_mail_send;

  /** 
   * <em>payment_apply_trigger</em>カラムの値 
   */
  public  String  payment_apply_trigger;

  /** 
   * <em>payment_apply_admin_task</em>カラムの値 
   */
  public  String  payment_apply_admin_task;

  /** 
   * <em>payment_reserve</em>カラムの値 
   */
  public  String  payment_reserve;

  /** 
   * <em>the_day_transfer</em>カラムの値 
   */
  public  String  the_day_transfer;

  /** 
   * <em>payment_automatic</em>カラムの値 
   */
  public  String  payment_automatic;

  /** 
   * <em>max_order_quantity</em>カラムの値 
   */
  public  Long  max_order_quantity;

  /** 
   * <em>off_floor_order_start_hhmmss</em>カラムの値 
   */
  public  String  off_floor_order_start_hhmmss;

  /** 
   * <em>off_floor_order_end_hhmmss</em>カラムの値 
   */
  public  String  off_floor_order_end_hhmmss;

  /** 
   * <em>system_status</em>カラムの値 
   */
  public  String  system_status;

  /** 
   * <em>hash_send_a</em>カラムの値 
   */
  public  String  hash_send_a;

  /** 
   * <em>hash_send_b</em>カラムの値 
   */
  public  String  hash_send_b;

  /** 
   * <em>hash_receive_a</em>カラムの値 
   */
  public  String  hash_receive_a;

  /** 
   * <em>hash_receive_b</em>カラムの値 
   */
  public  String  hash_receive_b;

  /** 
   * <em>email_address</em>カラムの値 
   */
  public  String  email_address;

  /** 
   * <em>slingshot_check_flag</em>カラムの値 
   */
  public  String  slingshot_check_flag;

  /** 
   * <em>trd_code_min</em>カラムの値 
   */
  public  Integer  trd_code_min;

  /** 
   * <em>trd_code_max</em>カラムの値 
   */
  public  Integer  trd_code_max;

  /** 
   * <em>trd_code_check_mode</em>カラムの値 
   */
  public  String  trd_code_check_mode;

  /** 
   * <em>admin_code_min</em>カラムの値 
   */
  public  Integer  admin_code_min;

  /** 
   * <em>admin_code_max</em>カラムの値 
   */
  public  Integer  admin_code_max;

  /** 
   * <em>admin_code_check_mode</em>カラムの値 
   */
  public  String  admin_code_check_mode;

  /** 
   * <em>ifo_real_price_calc_div</em>カラムの値 
   */
  public  String  ifo_real_price_calc_div;

  /** 
   * <em>simple_span_calc_div</em>カラムの値 
   */
  public  String  simple_span_calc_div;

  /** 
   * <em>span_trouble_div</em>カラムの値 
   */
  public  String  span_trouble_div;

  /** 
   * <em>span_factor</em>カラムの値 
   */
  public  Double  span_factor;

  /** 
   * <em>span_factor_red</em>カラムの値 
   */
  public  Double  span_factor_red;

  /** 
   * <em>transfer_power_factor</em>カラムの値 
   */
  public  Double  transfer_power_factor;

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
   * <em>future_day_trade_charge_div</em>カラムの値 
   */
  public  String  future_day_trade_charge_div;

  /** 
   * <em>securitites_cos_id_code</em>カラムの値 
   */
  public  String  securitites_cos_id_code;

  /** 
   * <em>db_current_price_check_div</em>カラムの値 
   */
  public  String  db_current_price_check_div;

  /** 
   * <em>forcedsettleorder_div</em>カラムの値 
   */
  public  String  forcedsettleorder_div;

  /** 
   * <em>forcedsettleorder_closeday_cnt</em>カラムの値 
   */
  public  Integer  forcedsettleorder_closeday_cnt;

  private boolean institution_id_is_set = false;
  private boolean institution_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean institution_name_is_set = false;
  private boolean institution_name_is_modified = false;
  private boolean asset_evaluation_is_set = false;
  private boolean asset_evaluation_is_modified = false;
  private boolean maximum_assessment_is_set = false;
  private boolean maximum_assessment_is_modified = false;
  private boolean stock_evaluation_is_set = false;
  private boolean stock_evaluation_is_modified = false;
  private boolean gp_evaluation_is_set = false;
  private boolean gp_evaluation_is_modified = false;
  private boolean fund_evaluation_is_set = false;
  private boolean fund_evaluation_is_modified = false;
  private boolean bond_evaluation_is_set = false;
  private boolean bond_evaluation_is_modified = false;
  private boolean enable_ipo_quantity_change_is_set = false;
  private boolean enable_ipo_quantity_change_is_modified = false;
  private boolean deposit_inform_mail_send_is_set = false;
  private boolean deposit_inform_mail_send_is_modified = false;
  private boolean payment_apply_trigger_is_set = false;
  private boolean payment_apply_trigger_is_modified = false;
  private boolean payment_apply_admin_task_is_set = false;
  private boolean payment_apply_admin_task_is_modified = false;
  private boolean payment_reserve_is_set = false;
  private boolean payment_reserve_is_modified = false;
  private boolean the_day_transfer_is_set = false;
  private boolean the_day_transfer_is_modified = false;
  private boolean payment_automatic_is_set = false;
  private boolean payment_automatic_is_modified = false;
  private boolean max_order_quantity_is_set = false;
  private boolean max_order_quantity_is_modified = false;
  private boolean off_floor_order_start_hhmmss_is_set = false;
  private boolean off_floor_order_start_hhmmss_is_modified = false;
  private boolean off_floor_order_end_hhmmss_is_set = false;
  private boolean off_floor_order_end_hhmmss_is_modified = false;
  private boolean system_status_is_set = false;
  private boolean system_status_is_modified = false;
  private boolean hash_send_a_is_set = false;
  private boolean hash_send_a_is_modified = false;
  private boolean hash_send_b_is_set = false;
  private boolean hash_send_b_is_modified = false;
  private boolean hash_receive_a_is_set = false;
  private boolean hash_receive_a_is_modified = false;
  private boolean hash_receive_b_is_set = false;
  private boolean hash_receive_b_is_modified = false;
  private boolean email_address_is_set = false;
  private boolean email_address_is_modified = false;
  private boolean slingshot_check_flag_is_set = false;
  private boolean slingshot_check_flag_is_modified = false;
  private boolean trd_code_min_is_set = false;
  private boolean trd_code_min_is_modified = false;
  private boolean trd_code_max_is_set = false;
  private boolean trd_code_max_is_modified = false;
  private boolean trd_code_check_mode_is_set = false;
  private boolean trd_code_check_mode_is_modified = false;
  private boolean admin_code_min_is_set = false;
  private boolean admin_code_min_is_modified = false;
  private boolean admin_code_max_is_set = false;
  private boolean admin_code_max_is_modified = false;
  private boolean admin_code_check_mode_is_set = false;
  private boolean admin_code_check_mode_is_modified = false;
  private boolean ifo_real_price_calc_div_is_set = false;
  private boolean ifo_real_price_calc_div_is_modified = false;
  private boolean simple_span_calc_div_is_set = false;
  private boolean simple_span_calc_div_is_modified = false;
  private boolean span_trouble_div_is_set = false;
  private boolean span_trouble_div_is_modified = false;
  private boolean span_factor_is_set = false;
  private boolean span_factor_is_modified = false;
  private boolean span_factor_red_is_set = false;
  private boolean span_factor_red_is_modified = false;
  private boolean transfer_power_factor_is_set = false;
  private boolean transfer_power_factor_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean future_day_trade_charge_div_is_set = false;
  private boolean future_day_trade_charge_div_is_modified = false;
  private boolean securitites_cos_id_code_is_set = false;
  private boolean securitites_cos_id_code_is_modified = false;
  private boolean db_current_price_check_div_is_set = false;
  private boolean db_current_price_check_div_is_modified = false;
  private boolean forcedsettleorder_div_is_set = false;
  private boolean forcedsettleorder_div_is_modified = false;
  private boolean forcedsettleorder_closeday_cnt_is_set = false;
  private boolean forcedsettleorder_closeday_cnt_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_id=" + institution_id
      + "," + "institution_code=" +institution_code
      + "," + "institution_name=" +institution_name
      + "," + "asset_evaluation=" +asset_evaluation
      + "," + "maximum_assessment=" +maximum_assessment
      + "," + "stock_evaluation=" +stock_evaluation
      + "," + "gp_evaluation=" +gp_evaluation
      + "," + "fund_evaluation=" +fund_evaluation
      + "," + "bond_evaluation=" +bond_evaluation
      + "," + "enable_ipo_quantity_change=" +enable_ipo_quantity_change
      + "," + "deposit_inform_mail_send=" +deposit_inform_mail_send
      + "," + "payment_apply_trigger=" +payment_apply_trigger
      + "," + "payment_apply_admin_task=" +payment_apply_admin_task
      + "," + "payment_reserve=" +payment_reserve
      + "," + "the_day_transfer=" +the_day_transfer
      + "," + "payment_automatic=" +payment_automatic
      + "," + "max_order_quantity=" +max_order_quantity
      + "," + "off_floor_order_start_hhmmss=" +off_floor_order_start_hhmmss
      + "," + "off_floor_order_end_hhmmss=" +off_floor_order_end_hhmmss
      + "," + "system_status=" +system_status
      + "," + "hash_send_a=" +hash_send_a
      + "," + "hash_send_b=" +hash_send_b
      + "," + "hash_receive_a=" +hash_receive_a
      + "," + "hash_receive_b=" +hash_receive_b
      + "," + "email_address=" +email_address
      + "," + "slingshot_check_flag=" +slingshot_check_flag
      + "," + "trd_code_min=" +trd_code_min
      + "," + "trd_code_max=" +trd_code_max
      + "," + "trd_code_check_mode=" +trd_code_check_mode
      + "," + "admin_code_min=" +admin_code_min
      + "," + "admin_code_max=" +admin_code_max
      + "," + "admin_code_check_mode=" +admin_code_check_mode
      + "," + "ifo_real_price_calc_div=" +ifo_real_price_calc_div
      + "," + "simple_span_calc_div=" +simple_span_calc_div
      + "," + "span_trouble_div=" +span_trouble_div
      + "," + "span_factor=" +span_factor
      + "," + "span_factor_red=" +span_factor_red
      + "," + "transfer_power_factor=" +transfer_power_factor
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "future_day_trade_charge_div=" +future_day_trade_charge_div
      + "," + "securitites_cos_id_code=" +securitites_cos_id_code
      + "," + "db_current_price_check_div=" +db_current_price_check_div
      + "," + "forcedsettleorder_div=" +forcedsettleorder_div
      + "," + "forcedsettleorder_closeday_cnt=" +forcedsettleorder_closeday_cnt
      + "}";
  }


  /** 
   * 値が未設定のInstitutionParamsオブジェクトを作成します。 
   */
  public InstitutionParams() {
    institution_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のInstitutionRowオブジェクトの値を利用してInstitutionParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するInstitutionRowオブジェクト 
   */
  public InstitutionParams( InstitutionRow p_row )
  {
    institution_id = p_row.getInstitutionId();
    institution_id_is_set = p_row.getInstitutionIdIsSet();
    institution_id_is_modified = p_row.getInstitutionIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    institution_name = p_row.getInstitutionName();
    institution_name_is_set = p_row.getInstitutionNameIsSet();
    institution_name_is_modified = p_row.getInstitutionNameIsModified();
    asset_evaluation = p_row.getAssetEvaluation();
    asset_evaluation_is_set = p_row.getAssetEvaluationIsSet();
    asset_evaluation_is_modified = p_row.getAssetEvaluationIsModified();
    if ( !p_row.getMaximumAssessmentIsNull() )
      maximum_assessment = new Long( p_row.getMaximumAssessment() );
    maximum_assessment_is_set = p_row.getMaximumAssessmentIsSet();
    maximum_assessment_is_modified = p_row.getMaximumAssessmentIsModified();
    stock_evaluation = p_row.getStockEvaluation();
    stock_evaluation_is_set = p_row.getStockEvaluationIsSet();
    stock_evaluation_is_modified = p_row.getStockEvaluationIsModified();
    gp_evaluation = p_row.getGpEvaluation();
    gp_evaluation_is_set = p_row.getGpEvaluationIsSet();
    gp_evaluation_is_modified = p_row.getGpEvaluationIsModified();
    fund_evaluation = p_row.getFundEvaluation();
    fund_evaluation_is_set = p_row.getFundEvaluationIsSet();
    fund_evaluation_is_modified = p_row.getFundEvaluationIsModified();
    bond_evaluation = p_row.getBondEvaluation();
    bond_evaluation_is_set = p_row.getBondEvaluationIsSet();
    bond_evaluation_is_modified = p_row.getBondEvaluationIsModified();
    enable_ipo_quantity_change = p_row.getEnableIpoQuantityChange();
    enable_ipo_quantity_change_is_set = p_row.getEnableIpoQuantityChangeIsSet();
    enable_ipo_quantity_change_is_modified = p_row.getEnableIpoQuantityChangeIsModified();
    deposit_inform_mail_send = p_row.getDepositInformMailSend();
    deposit_inform_mail_send_is_set = p_row.getDepositInformMailSendIsSet();
    deposit_inform_mail_send_is_modified = p_row.getDepositInformMailSendIsModified();
    payment_apply_trigger = p_row.getPaymentApplyTrigger();
    payment_apply_trigger_is_set = p_row.getPaymentApplyTriggerIsSet();
    payment_apply_trigger_is_modified = p_row.getPaymentApplyTriggerIsModified();
    payment_apply_admin_task = p_row.getPaymentApplyAdminTask();
    payment_apply_admin_task_is_set = p_row.getPaymentApplyAdminTaskIsSet();
    payment_apply_admin_task_is_modified = p_row.getPaymentApplyAdminTaskIsModified();
    payment_reserve = p_row.getPaymentReserve();
    payment_reserve_is_set = p_row.getPaymentReserveIsSet();
    payment_reserve_is_modified = p_row.getPaymentReserveIsModified();
    the_day_transfer = p_row.getTheDayTransfer();
    the_day_transfer_is_set = p_row.getTheDayTransferIsSet();
    the_day_transfer_is_modified = p_row.getTheDayTransferIsModified();
    payment_automatic = p_row.getPaymentAutomatic();
    payment_automatic_is_set = p_row.getPaymentAutomaticIsSet();
    payment_automatic_is_modified = p_row.getPaymentAutomaticIsModified();
    if ( !p_row.getMaxOrderQuantityIsNull() )
      max_order_quantity = new Long( p_row.getMaxOrderQuantity() );
    max_order_quantity_is_set = p_row.getMaxOrderQuantityIsSet();
    max_order_quantity_is_modified = p_row.getMaxOrderQuantityIsModified();
    off_floor_order_start_hhmmss = p_row.getOffFloorOrderStartHhmmss();
    off_floor_order_start_hhmmss_is_set = p_row.getOffFloorOrderStartHhmmssIsSet();
    off_floor_order_start_hhmmss_is_modified = p_row.getOffFloorOrderStartHhmmssIsModified();
    off_floor_order_end_hhmmss = p_row.getOffFloorOrderEndHhmmss();
    off_floor_order_end_hhmmss_is_set = p_row.getOffFloorOrderEndHhmmssIsSet();
    off_floor_order_end_hhmmss_is_modified = p_row.getOffFloorOrderEndHhmmssIsModified();
    system_status = p_row.getSystemStatus();
    system_status_is_set = p_row.getSystemStatusIsSet();
    system_status_is_modified = p_row.getSystemStatusIsModified();
    hash_send_a = p_row.getHashSendA();
    hash_send_a_is_set = p_row.getHashSendAIsSet();
    hash_send_a_is_modified = p_row.getHashSendAIsModified();
    hash_send_b = p_row.getHashSendB();
    hash_send_b_is_set = p_row.getHashSendBIsSet();
    hash_send_b_is_modified = p_row.getHashSendBIsModified();
    hash_receive_a = p_row.getHashReceiveA();
    hash_receive_a_is_set = p_row.getHashReceiveAIsSet();
    hash_receive_a_is_modified = p_row.getHashReceiveAIsModified();
    hash_receive_b = p_row.getHashReceiveB();
    hash_receive_b_is_set = p_row.getHashReceiveBIsSet();
    hash_receive_b_is_modified = p_row.getHashReceiveBIsModified();
    email_address = p_row.getEmailAddress();
    email_address_is_set = p_row.getEmailAddressIsSet();
    email_address_is_modified = p_row.getEmailAddressIsModified();
    slingshot_check_flag = p_row.getSlingshotCheckFlag();
    slingshot_check_flag_is_set = p_row.getSlingshotCheckFlagIsSet();
    slingshot_check_flag_is_modified = p_row.getSlingshotCheckFlagIsModified();
    if ( !p_row.getTrdCodeMinIsNull() )
      trd_code_min = new Integer( p_row.getTrdCodeMin() );
    trd_code_min_is_set = p_row.getTrdCodeMinIsSet();
    trd_code_min_is_modified = p_row.getTrdCodeMinIsModified();
    if ( !p_row.getTrdCodeMaxIsNull() )
      trd_code_max = new Integer( p_row.getTrdCodeMax() );
    trd_code_max_is_set = p_row.getTrdCodeMaxIsSet();
    trd_code_max_is_modified = p_row.getTrdCodeMaxIsModified();
    trd_code_check_mode = p_row.getTrdCodeCheckMode();
    trd_code_check_mode_is_set = p_row.getTrdCodeCheckModeIsSet();
    trd_code_check_mode_is_modified = p_row.getTrdCodeCheckModeIsModified();
    if ( !p_row.getAdminCodeMinIsNull() )
      admin_code_min = new Integer( p_row.getAdminCodeMin() );
    admin_code_min_is_set = p_row.getAdminCodeMinIsSet();
    admin_code_min_is_modified = p_row.getAdminCodeMinIsModified();
    if ( !p_row.getAdminCodeMaxIsNull() )
      admin_code_max = new Integer( p_row.getAdminCodeMax() );
    admin_code_max_is_set = p_row.getAdminCodeMaxIsSet();
    admin_code_max_is_modified = p_row.getAdminCodeMaxIsModified();
    admin_code_check_mode = p_row.getAdminCodeCheckMode();
    admin_code_check_mode_is_set = p_row.getAdminCodeCheckModeIsSet();
    admin_code_check_mode_is_modified = p_row.getAdminCodeCheckModeIsModified();
    ifo_real_price_calc_div = p_row.getIfoRealPriceCalcDiv();
    ifo_real_price_calc_div_is_set = p_row.getIfoRealPriceCalcDivIsSet();
    ifo_real_price_calc_div_is_modified = p_row.getIfoRealPriceCalcDivIsModified();
    simple_span_calc_div = p_row.getSimpleSpanCalcDiv();
    simple_span_calc_div_is_set = p_row.getSimpleSpanCalcDivIsSet();
    simple_span_calc_div_is_modified = p_row.getSimpleSpanCalcDivIsModified();
    span_trouble_div = p_row.getSpanTroubleDiv();
    span_trouble_div_is_set = p_row.getSpanTroubleDivIsSet();
    span_trouble_div_is_modified = p_row.getSpanTroubleDivIsModified();
    if ( !p_row.getSpanFactorIsNull() )
      span_factor = new Double( p_row.getSpanFactor() );
    span_factor_is_set = p_row.getSpanFactorIsSet();
    span_factor_is_modified = p_row.getSpanFactorIsModified();
    if ( !p_row.getSpanFactorRedIsNull() )
      span_factor_red = new Double( p_row.getSpanFactorRed() );
    span_factor_red_is_set = p_row.getSpanFactorRedIsSet();
    span_factor_red_is_modified = p_row.getSpanFactorRedIsModified();
    if ( !p_row.getTransferPowerFactorIsNull() )
      transfer_power_factor = new Double( p_row.getTransferPowerFactor() );
    transfer_power_factor_is_set = p_row.getTransferPowerFactorIsSet();
    transfer_power_factor_is_modified = p_row.getTransferPowerFactorIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    future_day_trade_charge_div = p_row.getFutureDayTradeChargeDiv();
    future_day_trade_charge_div_is_set = p_row.getFutureDayTradeChargeDivIsSet();
    future_day_trade_charge_div_is_modified = p_row.getFutureDayTradeChargeDivIsModified();
    securitites_cos_id_code = p_row.getSecurititesCosIdCode();
    securitites_cos_id_code_is_set = p_row.getSecurititesCosIdCodeIsSet();
    securitites_cos_id_code_is_modified = p_row.getSecurititesCosIdCodeIsModified();
    db_current_price_check_div = p_row.getDbCurrentPriceCheckDiv();
    db_current_price_check_div_is_set = p_row.getDbCurrentPriceCheckDivIsSet();
    db_current_price_check_div_is_modified = p_row.getDbCurrentPriceCheckDivIsModified();
    forcedsettleorder_div = p_row.getForcedsettleorderDiv();
    forcedsettleorder_div_is_set = p_row.getForcedsettleorderDivIsSet();
    forcedsettleorder_div_is_modified = p_row.getForcedsettleorderDivIsModified();
    if ( !p_row.getForcedsettleorderClosedayCntIsNull() )
      forcedsettleorder_closeday_cnt = new Integer( p_row.getForcedsettleorderClosedayCnt() );
    forcedsettleorder_closeday_cnt_is_set = p_row.getForcedsettleorderClosedayCntIsSet();
    forcedsettleorder_closeday_cnt_is_modified = p_row.getForcedsettleorderClosedayCntIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      institution_code_is_set = true;
      institution_code_is_modified = true;
      institution_name_is_set = true;
      institution_name_is_modified = true;
      asset_evaluation_is_set = true;
      asset_evaluation_is_modified = true;
      maximum_assessment_is_set = true;
      maximum_assessment_is_modified = true;
      stock_evaluation_is_set = true;
      stock_evaluation_is_modified = true;
      gp_evaluation_is_set = true;
      gp_evaluation_is_modified = true;
      fund_evaluation_is_set = true;
      fund_evaluation_is_modified = true;
      bond_evaluation_is_set = true;
      bond_evaluation_is_modified = true;
      enable_ipo_quantity_change_is_set = true;
      enable_ipo_quantity_change_is_modified = true;
      deposit_inform_mail_send_is_set = true;
      deposit_inform_mail_send_is_modified = true;
      payment_apply_trigger_is_set = true;
      payment_apply_trigger_is_modified = true;
      payment_apply_admin_task_is_set = true;
      payment_apply_admin_task_is_modified = true;
      payment_reserve_is_set = true;
      payment_reserve_is_modified = true;
      the_day_transfer_is_set = true;
      the_day_transfer_is_modified = true;
      payment_automatic_is_set = true;
      payment_automatic_is_modified = true;
      max_order_quantity_is_set = true;
      max_order_quantity_is_modified = true;
      off_floor_order_start_hhmmss_is_set = true;
      off_floor_order_start_hhmmss_is_modified = true;
      off_floor_order_end_hhmmss_is_set = true;
      off_floor_order_end_hhmmss_is_modified = true;
      system_status_is_set = true;
      system_status_is_modified = true;
      hash_send_a_is_set = true;
      hash_send_a_is_modified = true;
      hash_send_b_is_set = true;
      hash_send_b_is_modified = true;
      hash_receive_a_is_set = true;
      hash_receive_a_is_modified = true;
      hash_receive_b_is_set = true;
      hash_receive_b_is_modified = true;
      email_address_is_set = true;
      email_address_is_modified = true;
      slingshot_check_flag_is_set = true;
      slingshot_check_flag_is_modified = true;
      trd_code_min_is_set = true;
      trd_code_min_is_modified = true;
      trd_code_max_is_set = true;
      trd_code_max_is_modified = true;
      trd_code_check_mode_is_set = true;
      trd_code_check_mode_is_modified = true;
      admin_code_min_is_set = true;
      admin_code_min_is_modified = true;
      admin_code_max_is_set = true;
      admin_code_max_is_modified = true;
      admin_code_check_mode_is_set = true;
      admin_code_check_mode_is_modified = true;
      ifo_real_price_calc_div_is_set = true;
      ifo_real_price_calc_div_is_modified = true;
      simple_span_calc_div_is_set = true;
      simple_span_calc_div_is_modified = true;
      span_trouble_div_is_set = true;
      span_trouble_div_is_modified = true;
      span_factor_is_set = true;
      span_factor_is_modified = true;
      span_factor_red_is_set = true;
      span_factor_red_is_modified = true;
      transfer_power_factor_is_set = true;
      transfer_power_factor_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      future_day_trade_charge_div_is_set = true;
      future_day_trade_charge_div_is_modified = true;
      securitites_cos_id_code_is_set = true;
      securitites_cos_id_code_is_modified = true;
      db_current_price_check_div_is_set = true;
      db_current_price_check_div_is_modified = true;
      forcedsettleorder_div_is_set = true;
      forcedsettleorder_div_is_modified = true;
      forcedsettleorder_closeday_cnt_is_set = true;
      forcedsettleorder_closeday_cnt_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof InstitutionRow ) )
       return false;
    return fieldsEqual( (InstitutionRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のInstitutionRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( InstitutionRow row )
  {
    if ( institution_id != row.getInstitutionId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( institution_name == null ) {
      if ( row.getInstitutionName() != null )
        return false;
    } else if ( !institution_name.equals( row.getInstitutionName() ) ) {
        return false;
    }
    if ( asset_evaluation == null ) {
      if ( row.getAssetEvaluation() != null )
        return false;
    } else if ( !asset_evaluation.equals( row.getAssetEvaluation() ) ) {
        return false;
    }
    if ( maximum_assessment == null ) {
      if ( !row.getMaximumAssessmentIsNull() )
        return false;
    } else if ( row.getMaximumAssessmentIsNull() || ( maximum_assessment.longValue() != row.getMaximumAssessment() ) ) {
        return false;
    }
    if ( stock_evaluation == null ) {
      if ( row.getStockEvaluation() != null )
        return false;
    } else if ( !stock_evaluation.equals( row.getStockEvaluation() ) ) {
        return false;
    }
    if ( gp_evaluation == null ) {
      if ( row.getGpEvaluation() != null )
        return false;
    } else if ( !gp_evaluation.equals( row.getGpEvaluation() ) ) {
        return false;
    }
    if ( fund_evaluation == null ) {
      if ( row.getFundEvaluation() != null )
        return false;
    } else if ( !fund_evaluation.equals( row.getFundEvaluation() ) ) {
        return false;
    }
    if ( bond_evaluation == null ) {
      if ( row.getBondEvaluation() != null )
        return false;
    } else if ( !bond_evaluation.equals( row.getBondEvaluation() ) ) {
        return false;
    }
    if ( enable_ipo_quantity_change == null ) {
      if ( row.getEnableIpoQuantityChange() != null )
        return false;
    } else if ( !enable_ipo_quantity_change.equals( row.getEnableIpoQuantityChange() ) ) {
        return false;
    }
    if ( deposit_inform_mail_send == null ) {
      if ( row.getDepositInformMailSend() != null )
        return false;
    } else if ( !deposit_inform_mail_send.equals( row.getDepositInformMailSend() ) ) {
        return false;
    }
    if ( payment_apply_trigger == null ) {
      if ( row.getPaymentApplyTrigger() != null )
        return false;
    } else if ( !payment_apply_trigger.equals( row.getPaymentApplyTrigger() ) ) {
        return false;
    }
    if ( payment_apply_admin_task == null ) {
      if ( row.getPaymentApplyAdminTask() != null )
        return false;
    } else if ( !payment_apply_admin_task.equals( row.getPaymentApplyAdminTask() ) ) {
        return false;
    }
    if ( payment_reserve == null ) {
      if ( row.getPaymentReserve() != null )
        return false;
    } else if ( !payment_reserve.equals( row.getPaymentReserve() ) ) {
        return false;
    }
    if ( the_day_transfer == null ) {
      if ( row.getTheDayTransfer() != null )
        return false;
    } else if ( !the_day_transfer.equals( row.getTheDayTransfer() ) ) {
        return false;
    }
    if ( payment_automatic == null ) {
      if ( row.getPaymentAutomatic() != null )
        return false;
    } else if ( !payment_automatic.equals( row.getPaymentAutomatic() ) ) {
        return false;
    }
    if ( max_order_quantity == null ) {
      if ( !row.getMaxOrderQuantityIsNull() )
        return false;
    } else if ( row.getMaxOrderQuantityIsNull() || ( max_order_quantity.longValue() != row.getMaxOrderQuantity() ) ) {
        return false;
    }
    if ( off_floor_order_start_hhmmss == null ) {
      if ( row.getOffFloorOrderStartHhmmss() != null )
        return false;
    } else if ( !off_floor_order_start_hhmmss.equals( row.getOffFloorOrderStartHhmmss() ) ) {
        return false;
    }
    if ( off_floor_order_end_hhmmss == null ) {
      if ( row.getOffFloorOrderEndHhmmss() != null )
        return false;
    } else if ( !off_floor_order_end_hhmmss.equals( row.getOffFloorOrderEndHhmmss() ) ) {
        return false;
    }
    if ( system_status == null ) {
      if ( row.getSystemStatus() != null )
        return false;
    } else if ( !system_status.equals( row.getSystemStatus() ) ) {
        return false;
    }
    if ( hash_send_a == null ) {
      if ( row.getHashSendA() != null )
        return false;
    } else if ( !hash_send_a.equals( row.getHashSendA() ) ) {
        return false;
    }
    if ( hash_send_b == null ) {
      if ( row.getHashSendB() != null )
        return false;
    } else if ( !hash_send_b.equals( row.getHashSendB() ) ) {
        return false;
    }
    if ( hash_receive_a == null ) {
      if ( row.getHashReceiveA() != null )
        return false;
    } else if ( !hash_receive_a.equals( row.getHashReceiveA() ) ) {
        return false;
    }
    if ( hash_receive_b == null ) {
      if ( row.getHashReceiveB() != null )
        return false;
    } else if ( !hash_receive_b.equals( row.getHashReceiveB() ) ) {
        return false;
    }
    if ( email_address == null ) {
      if ( row.getEmailAddress() != null )
        return false;
    } else if ( !email_address.equals( row.getEmailAddress() ) ) {
        return false;
    }
    if ( slingshot_check_flag == null ) {
      if ( row.getSlingshotCheckFlag() != null )
        return false;
    } else if ( !slingshot_check_flag.equals( row.getSlingshotCheckFlag() ) ) {
        return false;
    }
    if ( trd_code_min == null ) {
      if ( !row.getTrdCodeMinIsNull() )
        return false;
    } else if ( row.getTrdCodeMinIsNull() || ( trd_code_min.intValue() != row.getTrdCodeMin() ) ) {
        return false;
    }
    if ( trd_code_max == null ) {
      if ( !row.getTrdCodeMaxIsNull() )
        return false;
    } else if ( row.getTrdCodeMaxIsNull() || ( trd_code_max.intValue() != row.getTrdCodeMax() ) ) {
        return false;
    }
    if ( trd_code_check_mode == null ) {
      if ( row.getTrdCodeCheckMode() != null )
        return false;
    } else if ( !trd_code_check_mode.equals( row.getTrdCodeCheckMode() ) ) {
        return false;
    }
    if ( admin_code_min == null ) {
      if ( !row.getAdminCodeMinIsNull() )
        return false;
    } else if ( row.getAdminCodeMinIsNull() || ( admin_code_min.intValue() != row.getAdminCodeMin() ) ) {
        return false;
    }
    if ( admin_code_max == null ) {
      if ( !row.getAdminCodeMaxIsNull() )
        return false;
    } else if ( row.getAdminCodeMaxIsNull() || ( admin_code_max.intValue() != row.getAdminCodeMax() ) ) {
        return false;
    }
    if ( admin_code_check_mode == null ) {
      if ( row.getAdminCodeCheckMode() != null )
        return false;
    } else if ( !admin_code_check_mode.equals( row.getAdminCodeCheckMode() ) ) {
        return false;
    }
    if ( ifo_real_price_calc_div == null ) {
      if ( row.getIfoRealPriceCalcDiv() != null )
        return false;
    } else if ( !ifo_real_price_calc_div.equals( row.getIfoRealPriceCalcDiv() ) ) {
        return false;
    }
    if ( simple_span_calc_div == null ) {
      if ( row.getSimpleSpanCalcDiv() != null )
        return false;
    } else if ( !simple_span_calc_div.equals( row.getSimpleSpanCalcDiv() ) ) {
        return false;
    }
    if ( span_trouble_div == null ) {
      if ( row.getSpanTroubleDiv() != null )
        return false;
    } else if ( !span_trouble_div.equals( row.getSpanTroubleDiv() ) ) {
        return false;
    }
    if ( span_factor == null ) {
      if ( !row.getSpanFactorIsNull() )
        return false;
    } else if ( row.getSpanFactorIsNull() || ( span_factor.doubleValue() != row.getSpanFactor() ) ) {
        return false;
    }
    if ( span_factor_red == null ) {
      if ( !row.getSpanFactorRedIsNull() )
        return false;
    } else if ( row.getSpanFactorRedIsNull() || ( span_factor_red.doubleValue() != row.getSpanFactorRed() ) ) {
        return false;
    }
    if ( transfer_power_factor == null ) {
      if ( !row.getTransferPowerFactorIsNull() )
        return false;
    } else if ( row.getTransferPowerFactorIsNull() || ( transfer_power_factor.doubleValue() != row.getTransferPowerFactor() ) ) {
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
    if ( future_day_trade_charge_div == null ) {
      if ( row.getFutureDayTradeChargeDiv() != null )
        return false;
    } else if ( !future_day_trade_charge_div.equals( row.getFutureDayTradeChargeDiv() ) ) {
        return false;
    }
    if ( securitites_cos_id_code == null ) {
      if ( row.getSecurititesCosIdCode() != null )
        return false;
    } else if ( !securitites_cos_id_code.equals( row.getSecurititesCosIdCode() ) ) {
        return false;
    }
    if ( db_current_price_check_div == null ) {
      if ( row.getDbCurrentPriceCheckDiv() != null )
        return false;
    } else if ( !db_current_price_check_div.equals( row.getDbCurrentPriceCheckDiv() ) ) {
        return false;
    }
    if ( forcedsettleorder_div == null ) {
      if ( row.getForcedsettleorderDiv() != null )
        return false;
    } else if ( !forcedsettleorder_div.equals( row.getForcedsettleorderDiv() ) ) {
        return false;
    }
    if ( forcedsettleorder_closeday_cnt == null ) {
      if ( !row.getForcedsettleorderClosedayCntIsNull() )
        return false;
    } else if ( row.getForcedsettleorderClosedayCntIsNull() || ( forcedsettleorder_closeday_cnt.intValue() != row.getForcedsettleorderClosedayCnt() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) institution_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (institution_name!=null? institution_name.hashCode(): 0) 
        + (asset_evaluation!=null? asset_evaluation.hashCode(): 0) 
        + (maximum_assessment!=null? maximum_assessment.hashCode(): 0) 
        + (stock_evaluation!=null? stock_evaluation.hashCode(): 0) 
        + (gp_evaluation!=null? gp_evaluation.hashCode(): 0) 
        + (fund_evaluation!=null? fund_evaluation.hashCode(): 0) 
        + (bond_evaluation!=null? bond_evaluation.hashCode(): 0) 
        + (enable_ipo_quantity_change!=null? enable_ipo_quantity_change.hashCode(): 0) 
        + (deposit_inform_mail_send!=null? deposit_inform_mail_send.hashCode(): 0) 
        + (payment_apply_trigger!=null? payment_apply_trigger.hashCode(): 0) 
        + (payment_apply_admin_task!=null? payment_apply_admin_task.hashCode(): 0) 
        + (payment_reserve!=null? payment_reserve.hashCode(): 0) 
        + (the_day_transfer!=null? the_day_transfer.hashCode(): 0) 
        + (payment_automatic!=null? payment_automatic.hashCode(): 0) 
        + (max_order_quantity!=null? max_order_quantity.hashCode(): 0) 
        + (off_floor_order_start_hhmmss!=null? off_floor_order_start_hhmmss.hashCode(): 0) 
        + (off_floor_order_end_hhmmss!=null? off_floor_order_end_hhmmss.hashCode(): 0) 
        + (system_status!=null? system_status.hashCode(): 0) 
        + (hash_send_a!=null? hash_send_a.hashCode(): 0) 
        + (hash_send_b!=null? hash_send_b.hashCode(): 0) 
        + (hash_receive_a!=null? hash_receive_a.hashCode(): 0) 
        + (hash_receive_b!=null? hash_receive_b.hashCode(): 0) 
        + (email_address!=null? email_address.hashCode(): 0) 
        + (slingshot_check_flag!=null? slingshot_check_flag.hashCode(): 0) 
        + (trd_code_min!=null? trd_code_min.hashCode(): 0) 
        + (trd_code_max!=null? trd_code_max.hashCode(): 0) 
        + (trd_code_check_mode!=null? trd_code_check_mode.hashCode(): 0) 
        + (admin_code_min!=null? admin_code_min.hashCode(): 0) 
        + (admin_code_max!=null? admin_code_max.hashCode(): 0) 
        + (admin_code_check_mode!=null? admin_code_check_mode.hashCode(): 0) 
        + (ifo_real_price_calc_div!=null? ifo_real_price_calc_div.hashCode(): 0) 
        + (simple_span_calc_div!=null? simple_span_calc_div.hashCode(): 0) 
        + (span_trouble_div!=null? span_trouble_div.hashCode(): 0) 
        + (span_factor!=null? span_factor.hashCode(): 0) 
        + (span_factor_red!=null? span_factor_red.hashCode(): 0) 
        + (transfer_power_factor!=null? transfer_power_factor.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (future_day_trade_charge_div!=null? future_day_trade_charge_div.hashCode(): 0) 
        + (securitites_cos_id_code!=null? securitites_cos_id_code.hashCode(): 0) 
        + (db_current_price_check_div!=null? db_current_price_check_div.hashCode(): 0) 
        + (forcedsettleorder_div!=null? forcedsettleorder_div.hashCode(): 0) 
        + (forcedsettleorder_closeday_cnt!=null? forcedsettleorder_closeday_cnt.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_id",new Long(institution_id));
		map.put("institution_code",institution_code);
		if ( institution_name != null )
			map.put("institution_name",institution_name);
		if ( asset_evaluation != null )
			map.put("asset_evaluation",asset_evaluation);
		if ( maximum_assessment != null )
			map.put("maximum_assessment",maximum_assessment);
		if ( stock_evaluation != null )
			map.put("stock_evaluation",stock_evaluation);
		if ( gp_evaluation != null )
			map.put("gp_evaluation",gp_evaluation);
		if ( fund_evaluation != null )
			map.put("fund_evaluation",fund_evaluation);
		if ( bond_evaluation != null )
			map.put("bond_evaluation",bond_evaluation);
		if ( enable_ipo_quantity_change != null )
			map.put("enable_ipo_quantity_change",enable_ipo_quantity_change);
		if ( deposit_inform_mail_send != null )
			map.put("deposit_inform_mail_send",deposit_inform_mail_send);
		if ( payment_apply_trigger != null )
			map.put("payment_apply_trigger",payment_apply_trigger);
		if ( payment_apply_admin_task != null )
			map.put("payment_apply_admin_task",payment_apply_admin_task);
		if ( payment_reserve != null )
			map.put("payment_reserve",payment_reserve);
		if ( the_day_transfer != null )
			map.put("the_day_transfer",the_day_transfer);
		if ( payment_automatic != null )
			map.put("payment_automatic",payment_automatic);
		if ( max_order_quantity != null )
			map.put("max_order_quantity",max_order_quantity);
		if ( off_floor_order_start_hhmmss != null )
			map.put("off_floor_order_start_hhmmss",off_floor_order_start_hhmmss);
		if ( off_floor_order_end_hhmmss != null )
			map.put("off_floor_order_end_hhmmss",off_floor_order_end_hhmmss);
		if ( system_status != null )
			map.put("system_status",system_status);
		if ( hash_send_a != null )
			map.put("hash_send_a",hash_send_a);
		if ( hash_send_b != null )
			map.put("hash_send_b",hash_send_b);
		if ( hash_receive_a != null )
			map.put("hash_receive_a",hash_receive_a);
		if ( hash_receive_b != null )
			map.put("hash_receive_b",hash_receive_b);
		if ( email_address != null )
			map.put("email_address",email_address);
		if ( slingshot_check_flag != null )
			map.put("slingshot_check_flag",slingshot_check_flag);
		if ( trd_code_min != null )
			map.put("trd_code_min",trd_code_min);
		if ( trd_code_max != null )
			map.put("trd_code_max",trd_code_max);
		if ( trd_code_check_mode != null )
			map.put("trd_code_check_mode",trd_code_check_mode);
		if ( admin_code_min != null )
			map.put("admin_code_min",admin_code_min);
		if ( admin_code_max != null )
			map.put("admin_code_max",admin_code_max);
		if ( admin_code_check_mode != null )
			map.put("admin_code_check_mode",admin_code_check_mode);
		if ( ifo_real_price_calc_div != null )
			map.put("ifo_real_price_calc_div",ifo_real_price_calc_div);
		if ( simple_span_calc_div != null )
			map.put("simple_span_calc_div",simple_span_calc_div);
		if ( span_trouble_div != null )
			map.put("span_trouble_div",span_trouble_div);
		if ( span_factor != null )
			map.put("span_factor",span_factor);
		if ( span_factor_red != null )
			map.put("span_factor_red",span_factor_red);
		if ( transfer_power_factor != null )
			map.put("transfer_power_factor",transfer_power_factor);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( future_day_trade_charge_div != null )
			map.put("future_day_trade_charge_div",future_day_trade_charge_div);
		if ( securitites_cos_id_code != null )
			map.put("securitites_cos_id_code",securitites_cos_id_code);
		if ( db_current_price_check_div != null )
			map.put("db_current_price_check_div",db_current_price_check_div);
		if ( forcedsettleorder_div_is_set )
			map.put("forcedsettleorder_div",forcedsettleorder_div);
		if ( forcedsettleorder_closeday_cnt_is_set )
			map.put("forcedsettleorder_closeday_cnt",forcedsettleorder_closeday_cnt);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( institution_name_is_modified )
			map.put("institution_name",institution_name);
		if ( asset_evaluation_is_modified )
			map.put("asset_evaluation",asset_evaluation);
		if ( maximum_assessment_is_modified )
			map.put("maximum_assessment",maximum_assessment);
		if ( stock_evaluation_is_modified )
			map.put("stock_evaluation",stock_evaluation);
		if ( gp_evaluation_is_modified )
			map.put("gp_evaluation",gp_evaluation);
		if ( fund_evaluation_is_modified )
			map.put("fund_evaluation",fund_evaluation);
		if ( bond_evaluation_is_modified )
			map.put("bond_evaluation",bond_evaluation);
		if ( enable_ipo_quantity_change_is_modified )
			map.put("enable_ipo_quantity_change",enable_ipo_quantity_change);
		if ( deposit_inform_mail_send_is_modified )
			map.put("deposit_inform_mail_send",deposit_inform_mail_send);
		if ( payment_apply_trigger_is_modified )
			map.put("payment_apply_trigger",payment_apply_trigger);
		if ( payment_apply_admin_task_is_modified )
			map.put("payment_apply_admin_task",payment_apply_admin_task);
		if ( payment_reserve_is_modified )
			map.put("payment_reserve",payment_reserve);
		if ( the_day_transfer_is_modified )
			map.put("the_day_transfer",the_day_transfer);
		if ( payment_automatic_is_modified )
			map.put("payment_automatic",payment_automatic);
		if ( max_order_quantity_is_modified )
			map.put("max_order_quantity",max_order_quantity);
		if ( off_floor_order_start_hhmmss_is_modified )
			map.put("off_floor_order_start_hhmmss",off_floor_order_start_hhmmss);
		if ( off_floor_order_end_hhmmss_is_modified )
			map.put("off_floor_order_end_hhmmss",off_floor_order_end_hhmmss);
		if ( system_status_is_modified )
			map.put("system_status",system_status);
		if ( hash_send_a_is_modified )
			map.put("hash_send_a",hash_send_a);
		if ( hash_send_b_is_modified )
			map.put("hash_send_b",hash_send_b);
		if ( hash_receive_a_is_modified )
			map.put("hash_receive_a",hash_receive_a);
		if ( hash_receive_b_is_modified )
			map.put("hash_receive_b",hash_receive_b);
		if ( email_address_is_modified )
			map.put("email_address",email_address);
		if ( slingshot_check_flag_is_modified )
			map.put("slingshot_check_flag",slingshot_check_flag);
		if ( trd_code_min_is_modified )
			map.put("trd_code_min",trd_code_min);
		if ( trd_code_max_is_modified )
			map.put("trd_code_max",trd_code_max);
		if ( trd_code_check_mode_is_modified )
			map.put("trd_code_check_mode",trd_code_check_mode);
		if ( admin_code_min_is_modified )
			map.put("admin_code_min",admin_code_min);
		if ( admin_code_max_is_modified )
			map.put("admin_code_max",admin_code_max);
		if ( admin_code_check_mode_is_modified )
			map.put("admin_code_check_mode",admin_code_check_mode);
		if ( ifo_real_price_calc_div_is_modified )
			map.put("ifo_real_price_calc_div",ifo_real_price_calc_div);
		if ( simple_span_calc_div_is_modified )
			map.put("simple_span_calc_div",simple_span_calc_div);
		if ( span_trouble_div_is_modified )
			map.put("span_trouble_div",span_trouble_div);
		if ( span_factor_is_modified )
			map.put("span_factor",span_factor);
		if ( span_factor_red_is_modified )
			map.put("span_factor_red",span_factor_red);
		if ( transfer_power_factor_is_modified )
			map.put("transfer_power_factor",transfer_power_factor);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( future_day_trade_charge_div_is_modified )
			map.put("future_day_trade_charge_div",future_day_trade_charge_div);
		if ( securitites_cos_id_code_is_modified )
			map.put("securitites_cos_id_code",securitites_cos_id_code);
		if ( db_current_price_check_div_is_modified )
			map.put("db_current_price_check_div",db_current_price_check_div);
		if ( forcedsettleorder_div_is_modified )
			map.put("forcedsettleorder_div",forcedsettleorder_div);
		if ( forcedsettleorder_closeday_cnt_is_modified )
			map.put("forcedsettleorder_closeday_cnt",forcedsettleorder_closeday_cnt);
		if (map.size() == 0) {
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			map.put("institution_name",institution_name);
			map.put("asset_evaluation",asset_evaluation);
			map.put("maximum_assessment",maximum_assessment);
			map.put("stock_evaluation",stock_evaluation);
			map.put("gp_evaluation",gp_evaluation);
			map.put("fund_evaluation",fund_evaluation);
			map.put("bond_evaluation",bond_evaluation);
			map.put("enable_ipo_quantity_change",enable_ipo_quantity_change);
			map.put("deposit_inform_mail_send",deposit_inform_mail_send);
			map.put("payment_apply_trigger",payment_apply_trigger);
			map.put("payment_apply_admin_task",payment_apply_admin_task);
			map.put("payment_reserve",payment_reserve);
			map.put("the_day_transfer",the_day_transfer);
			map.put("payment_automatic",payment_automatic);
			map.put("max_order_quantity",max_order_quantity);
			map.put("off_floor_order_start_hhmmss",off_floor_order_start_hhmmss);
			map.put("off_floor_order_end_hhmmss",off_floor_order_end_hhmmss);
			map.put("system_status",system_status);
			map.put("hash_send_a",hash_send_a);
			map.put("hash_send_b",hash_send_b);
			map.put("hash_receive_a",hash_receive_a);
			map.put("hash_receive_b",hash_receive_b);
			map.put("email_address",email_address);
			map.put("slingshot_check_flag",slingshot_check_flag);
			map.put("trd_code_min",trd_code_min);
			map.put("trd_code_max",trd_code_max);
			map.put("trd_code_check_mode",trd_code_check_mode);
			map.put("admin_code_min",admin_code_min);
			map.put("admin_code_max",admin_code_max);
			map.put("admin_code_check_mode",admin_code_check_mode);
			map.put("ifo_real_price_calc_div",ifo_real_price_calc_div);
			map.put("simple_span_calc_div",simple_span_calc_div);
			map.put("span_trouble_div",span_trouble_div);
			map.put("span_factor",span_factor);
			map.put("span_factor_red",span_factor_red);
			map.put("transfer_power_factor",transfer_power_factor);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("future_day_trade_charge_div",future_day_trade_charge_div);
			map.put("securitites_cos_id_code",securitites_cos_id_code);
			map.put("db_current_price_check_div",db_current_price_check_div);
			if ( forcedsettleorder_div_is_set )
				map.put("forcedsettleorder_div",forcedsettleorder_div);
			if ( forcedsettleorder_closeday_cnt_is_set )
				map.put("forcedsettleorder_closeday_cnt",forcedsettleorder_closeday_cnt);
		}
		return map;
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
   * <em>institution_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstitutionName()
  {
    return institution_name;
  }


  /** 
   * <em>institution_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionNameIsSet() {
    return institution_name_is_set;
  }


  /** 
   * <em>institution_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionNameIsModified() {
    return institution_name_is_modified;
  }


  /** 
   * <em>asset_evaluation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAssetEvaluation()
  {
    return asset_evaluation;
  }


  /** 
   * <em>asset_evaluation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAssetEvaluationIsSet() {
    return asset_evaluation_is_set;
  }


  /** 
   * <em>asset_evaluation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAssetEvaluationIsModified() {
    return asset_evaluation_is_modified;
  }


  /** 
   * <em>maximum_assessment</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMaximumAssessment()
  {
    return ( maximum_assessment==null? ((long)0): maximum_assessment.longValue() );
  }


  /** 
   * <em>maximum_assessment</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaximumAssessmentIsNull()
  {
    return maximum_assessment == null;
  }


  /** 
   * <em>maximum_assessment</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaximumAssessmentIsSet() {
    return maximum_assessment_is_set;
  }


  /** 
   * <em>maximum_assessment</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaximumAssessmentIsModified() {
    return maximum_assessment_is_modified;
  }


  /** 
   * <em>stock_evaluation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStockEvaluation()
  {
    return stock_evaluation;
  }


  /** 
   * <em>stock_evaluation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStockEvaluationIsSet() {
    return stock_evaluation_is_set;
  }


  /** 
   * <em>stock_evaluation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStockEvaluationIsModified() {
    return stock_evaluation_is_modified;
  }


  /** 
   * <em>gp_evaluation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getGpEvaluation()
  {
    return gp_evaluation;
  }


  /** 
   * <em>gp_evaluation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpEvaluationIsSet() {
    return gp_evaluation_is_set;
  }


  /** 
   * <em>gp_evaluation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGpEvaluationIsModified() {
    return gp_evaluation_is_modified;
  }


  /** 
   * <em>fund_evaluation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFundEvaluation()
  {
    return fund_evaluation;
  }


  /** 
   * <em>fund_evaluation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundEvaluationIsSet() {
    return fund_evaluation_is_set;
  }


  /** 
   * <em>fund_evaluation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFundEvaluationIsModified() {
    return fund_evaluation_is_modified;
  }


  /** 
   * <em>bond_evaluation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBondEvaluation()
  {
    return bond_evaluation;
  }


  /** 
   * <em>bond_evaluation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondEvaluationIsSet() {
    return bond_evaluation_is_set;
  }


  /** 
   * <em>bond_evaluation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondEvaluationIsModified() {
    return bond_evaluation_is_modified;
  }


  /** 
   * <em>enable_ipo_quantity_change</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEnableIpoQuantityChange()
  {
    return enable_ipo_quantity_change;
  }


  /** 
   * <em>enable_ipo_quantity_change</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEnableIpoQuantityChangeIsSet() {
    return enable_ipo_quantity_change_is_set;
  }


  /** 
   * <em>enable_ipo_quantity_change</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEnableIpoQuantityChangeIsModified() {
    return enable_ipo_quantity_change_is_modified;
  }


  /** 
   * <em>deposit_inform_mail_send</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDepositInformMailSend()
  {
    return deposit_inform_mail_send;
  }


  /** 
   * <em>deposit_inform_mail_send</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositInformMailSendIsSet() {
    return deposit_inform_mail_send_is_set;
  }


  /** 
   * <em>deposit_inform_mail_send</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDepositInformMailSendIsModified() {
    return deposit_inform_mail_send_is_modified;
  }


  /** 
   * <em>payment_apply_trigger</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaymentApplyTrigger()
  {
    return payment_apply_trigger;
  }


  /** 
   * <em>payment_apply_trigger</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentApplyTriggerIsSet() {
    return payment_apply_trigger_is_set;
  }


  /** 
   * <em>payment_apply_trigger</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentApplyTriggerIsModified() {
    return payment_apply_trigger_is_modified;
  }


  /** 
   * <em>payment_apply_admin_task</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaymentApplyAdminTask()
  {
    return payment_apply_admin_task;
  }


  /** 
   * <em>payment_apply_admin_task</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentApplyAdminTaskIsSet() {
    return payment_apply_admin_task_is_set;
  }


  /** 
   * <em>payment_apply_admin_task</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentApplyAdminTaskIsModified() {
    return payment_apply_admin_task_is_modified;
  }


  /** 
   * <em>payment_reserve</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaymentReserve()
  {
    return payment_reserve;
  }


  /** 
   * <em>payment_reserve</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentReserveIsSet() {
    return payment_reserve_is_set;
  }


  /** 
   * <em>payment_reserve</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentReserveIsModified() {
    return payment_reserve_is_modified;
  }


  /** 
   * <em>the_day_transfer</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTheDayTransfer()
  {
    return the_day_transfer;
  }


  /** 
   * <em>the_day_transfer</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTheDayTransferIsSet() {
    return the_day_transfer_is_set;
  }


  /** 
   * <em>the_day_transfer</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTheDayTransferIsModified() {
    return the_day_transfer_is_modified;
  }


  /** 
   * <em>payment_automatic</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaymentAutomatic()
  {
    return payment_automatic;
  }


  /** 
   * <em>payment_automatic</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAutomaticIsSet() {
    return payment_automatic_is_set;
  }


  /** 
   * <em>payment_automatic</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAutomaticIsModified() {
    return payment_automatic_is_modified;
  }


  /** 
   * <em>max_order_quantity</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMaxOrderQuantity()
  {
    return ( max_order_quantity==null? ((long)0): max_order_quantity.longValue() );
  }


  /** 
   * <em>max_order_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMaxOrderQuantityIsNull()
  {
    return max_order_quantity == null;
  }


  /** 
   * <em>max_order_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxOrderQuantityIsSet() {
    return max_order_quantity_is_set;
  }


  /** 
   * <em>max_order_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMaxOrderQuantityIsModified() {
    return max_order_quantity_is_modified;
  }


  /** 
   * <em>off_floor_order_start_hhmmss</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOffFloorOrderStartHhmmss()
  {
    return off_floor_order_start_hhmmss;
  }


  /** 
   * <em>off_floor_order_start_hhmmss</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOffFloorOrderStartHhmmssIsSet() {
    return off_floor_order_start_hhmmss_is_set;
  }


  /** 
   * <em>off_floor_order_start_hhmmss</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOffFloorOrderStartHhmmssIsModified() {
    return off_floor_order_start_hhmmss_is_modified;
  }


  /** 
   * <em>off_floor_order_end_hhmmss</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOffFloorOrderEndHhmmss()
  {
    return off_floor_order_end_hhmmss;
  }


  /** 
   * <em>off_floor_order_end_hhmmss</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOffFloorOrderEndHhmmssIsSet() {
    return off_floor_order_end_hhmmss_is_set;
  }


  /** 
   * <em>off_floor_order_end_hhmmss</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOffFloorOrderEndHhmmssIsModified() {
    return off_floor_order_end_hhmmss_is_modified;
  }


  /** 
   * <em>system_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSystemStatus()
  {
    return system_status;
  }


  /** 
   * <em>system_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSystemStatusIsSet() {
    return system_status_is_set;
  }


  /** 
   * <em>system_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSystemStatusIsModified() {
    return system_status_is_modified;
  }


  /** 
   * <em>hash_send_a</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHashSendA()
  {
    return hash_send_a;
  }


  /** 
   * <em>hash_send_a</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHashSendAIsSet() {
    return hash_send_a_is_set;
  }


  /** 
   * <em>hash_send_a</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHashSendAIsModified() {
    return hash_send_a_is_modified;
  }


  /** 
   * <em>hash_send_b</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHashSendB()
  {
    return hash_send_b;
  }


  /** 
   * <em>hash_send_b</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHashSendBIsSet() {
    return hash_send_b_is_set;
  }


  /** 
   * <em>hash_send_b</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHashSendBIsModified() {
    return hash_send_b_is_modified;
  }


  /** 
   * <em>hash_receive_a</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHashReceiveA()
  {
    return hash_receive_a;
  }


  /** 
   * <em>hash_receive_a</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHashReceiveAIsSet() {
    return hash_receive_a_is_set;
  }


  /** 
   * <em>hash_receive_a</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHashReceiveAIsModified() {
    return hash_receive_a_is_modified;
  }


  /** 
   * <em>hash_receive_b</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHashReceiveB()
  {
    return hash_receive_b;
  }


  /** 
   * <em>hash_receive_b</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHashReceiveBIsSet() {
    return hash_receive_b_is_set;
  }


  /** 
   * <em>hash_receive_b</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHashReceiveBIsModified() {
    return hash_receive_b_is_modified;
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
   * <em>slingshot_check_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSlingshotCheckFlag()
  {
    return slingshot_check_flag;
  }


  /** 
   * <em>slingshot_check_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSlingshotCheckFlagIsSet() {
    return slingshot_check_flag_is_set;
  }


  /** 
   * <em>slingshot_check_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSlingshotCheckFlagIsModified() {
    return slingshot_check_flag_is_modified;
  }


  /** 
   * <em>trd_code_min</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getTrdCodeMin()
  {
    return ( trd_code_min==null? ((int)0): trd_code_min.intValue() );
  }


  /** 
   * <em>trd_code_min</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTrdCodeMinIsNull()
  {
    return trd_code_min == null;
  }


  /** 
   * <em>trd_code_min</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrdCodeMinIsSet() {
    return trd_code_min_is_set;
  }


  /** 
   * <em>trd_code_min</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrdCodeMinIsModified() {
    return trd_code_min_is_modified;
  }


  /** 
   * <em>trd_code_max</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getTrdCodeMax()
  {
    return ( trd_code_max==null? ((int)0): trd_code_max.intValue() );
  }


  /** 
   * <em>trd_code_max</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTrdCodeMaxIsNull()
  {
    return trd_code_max == null;
  }


  /** 
   * <em>trd_code_max</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrdCodeMaxIsSet() {
    return trd_code_max_is_set;
  }


  /** 
   * <em>trd_code_max</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrdCodeMaxIsModified() {
    return trd_code_max_is_modified;
  }


  /** 
   * <em>trd_code_check_mode</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTrdCodeCheckMode()
  {
    return trd_code_check_mode;
  }


  /** 
   * <em>trd_code_check_mode</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrdCodeCheckModeIsSet() {
    return trd_code_check_mode_is_set;
  }


  /** 
   * <em>trd_code_check_mode</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrdCodeCheckModeIsModified() {
    return trd_code_check_mode_is_modified;
  }


  /** 
   * <em>admin_code_min</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getAdminCodeMin()
  {
    return ( admin_code_min==null? ((int)0): admin_code_min.intValue() );
  }


  /** 
   * <em>admin_code_min</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAdminCodeMinIsNull()
  {
    return admin_code_min == null;
  }


  /** 
   * <em>admin_code_min</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdminCodeMinIsSet() {
    return admin_code_min_is_set;
  }


  /** 
   * <em>admin_code_min</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdminCodeMinIsModified() {
    return admin_code_min_is_modified;
  }


  /** 
   * <em>admin_code_max</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getAdminCodeMax()
  {
    return ( admin_code_max==null? ((int)0): admin_code_max.intValue() );
  }


  /** 
   * <em>admin_code_max</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAdminCodeMaxIsNull()
  {
    return admin_code_max == null;
  }


  /** 
   * <em>admin_code_max</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdminCodeMaxIsSet() {
    return admin_code_max_is_set;
  }


  /** 
   * <em>admin_code_max</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdminCodeMaxIsModified() {
    return admin_code_max_is_modified;
  }


  /** 
   * <em>admin_code_check_mode</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAdminCodeCheckMode()
  {
    return admin_code_check_mode;
  }


  /** 
   * <em>admin_code_check_mode</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdminCodeCheckModeIsSet() {
    return admin_code_check_mode_is_set;
  }


  /** 
   * <em>admin_code_check_mode</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAdminCodeCheckModeIsModified() {
    return admin_code_check_mode_is_modified;
  }


  /** 
   * <em>ifo_real_price_calc_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoRealPriceCalcDiv()
  {
    return ifo_real_price_calc_div;
  }


  /** 
   * <em>ifo_real_price_calc_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoRealPriceCalcDivIsSet() {
    return ifo_real_price_calc_div_is_set;
  }


  /** 
   * <em>ifo_real_price_calc_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoRealPriceCalcDivIsModified() {
    return ifo_real_price_calc_div_is_modified;
  }


  /** 
   * <em>simple_span_calc_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSimpleSpanCalcDiv()
  {
    return simple_span_calc_div;
  }


  /** 
   * <em>simple_span_calc_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSimpleSpanCalcDivIsSet() {
    return simple_span_calc_div_is_set;
  }


  /** 
   * <em>simple_span_calc_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSimpleSpanCalcDivIsModified() {
    return simple_span_calc_div_is_modified;
  }


  /** 
   * <em>span_trouble_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSpanTroubleDiv()
  {
    return span_trouble_div;
  }


  /** 
   * <em>span_trouble_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpanTroubleDivIsSet() {
    return span_trouble_div_is_set;
  }


  /** 
   * <em>span_trouble_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpanTroubleDivIsModified() {
    return span_trouble_div_is_modified;
  }


  /** 
   * <em>span_factor</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSpanFactor()
  {
    return ( span_factor==null? ((double)0): span_factor.doubleValue() );
  }


  /** 
   * <em>span_factor</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSpanFactorIsNull()
  {
    return span_factor == null;
  }


  /** 
   * <em>span_factor</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpanFactorIsSet() {
    return span_factor_is_set;
  }


  /** 
   * <em>span_factor</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpanFactorIsModified() {
    return span_factor_is_modified;
  }


  /** 
   * <em>span_factor_red</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSpanFactorRed()
  {
    return ( span_factor_red==null? ((double)0): span_factor_red.doubleValue() );
  }


  /** 
   * <em>span_factor_red</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSpanFactorRedIsNull()
  {
    return span_factor_red == null;
  }


  /** 
   * <em>span_factor_red</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpanFactorRedIsSet() {
    return span_factor_red_is_set;
  }


  /** 
   * <em>span_factor_red</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpanFactorRedIsModified() {
    return span_factor_red_is_modified;
  }


  /** 
   * <em>transfer_power_factor</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTransferPowerFactor()
  {
    return ( transfer_power_factor==null? ((double)0): transfer_power_factor.doubleValue() );
  }


  /** 
   * <em>transfer_power_factor</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTransferPowerFactorIsNull()
  {
    return transfer_power_factor == null;
  }


  /** 
   * <em>transfer_power_factor</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferPowerFactorIsSet() {
    return transfer_power_factor_is_set;
  }


  /** 
   * <em>transfer_power_factor</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTransferPowerFactorIsModified() {
    return transfer_power_factor_is_modified;
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
   * <em>future_day_trade_charge_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFutureDayTradeChargeDiv()
  {
    return future_day_trade_charge_div;
  }


  /** 
   * <em>future_day_trade_charge_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutureDayTradeChargeDivIsSet() {
    return future_day_trade_charge_div_is_set;
  }


  /** 
   * <em>future_day_trade_charge_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutureDayTradeChargeDivIsModified() {
    return future_day_trade_charge_div_is_modified;
  }


  /** 
   * <em>securitites_cos_id_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSecurititesCosIdCode()
  {
    return securitites_cos_id_code;
  }


  /** 
   * <em>securitites_cos_id_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecurititesCosIdCodeIsSet() {
    return securitites_cos_id_code_is_set;
  }


  /** 
   * <em>securitites_cos_id_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecurititesCosIdCodeIsModified() {
    return securitites_cos_id_code_is_modified;
  }


  /** 
   * <em>db_current_price_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDbCurrentPriceCheckDiv()
  {
    return db_current_price_check_div;
  }


  /** 
   * <em>db_current_price_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDbCurrentPriceCheckDivIsSet() {
    return db_current_price_check_div_is_set;
  }


  /** 
   * <em>db_current_price_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDbCurrentPriceCheckDivIsModified() {
    return db_current_price_check_div_is_modified;
  }


  /** 
   * <em>forcedsettleorder_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getForcedsettleorderDiv()
  {
    return forcedsettleorder_div;
  }


  /** 
   * <em>forcedsettleorder_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForcedsettleorderDivIsSet() {
    return forcedsettleorder_div_is_set;
  }


  /** 
   * <em>forcedsettleorder_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForcedsettleorderDivIsModified() {
    return forcedsettleorder_div_is_modified;
  }


  /** 
   * <em>forcedsettleorder_closeday_cnt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getForcedsettleorderClosedayCnt()
  {
    return ( forcedsettleorder_closeday_cnt==null? ((int)0): forcedsettleorder_closeday_cnt.intValue() );
  }


  /** 
   * <em>forcedsettleorder_closeday_cnt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getForcedsettleorderClosedayCntIsNull()
  {
    return forcedsettleorder_closeday_cnt == null;
  }


  /** 
   * <em>forcedsettleorder_closeday_cnt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForcedsettleorderClosedayCntIsSet() {
    return forcedsettleorder_closeday_cnt_is_set;
  }


  /** 
   * <em>forcedsettleorder_closeday_cnt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForcedsettleorderClosedayCntIsModified() {
    return forcedsettleorder_closeday_cnt_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new InstitutionPK(institution_id);
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
   * <em>institution_name</em>カラムの値を設定します。 
   *
   * @@param p_institutionName <em>institution_name</em>カラムの値をあらわす80桁以下のString値 
   */
  public final void setInstitutionName( String p_institutionName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_name = p_institutionName;
    institution_name_is_set = true;
    institution_name_is_modified = true;
  }


  /** 
   * <em>asset_evaluation</em>カラムの値を設定します。 
   *
   * @@param p_assetEvaluation <em>asset_evaluation</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAssetEvaluation( String p_assetEvaluation )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    asset_evaluation = p_assetEvaluation;
    asset_evaluation_is_set = true;
    asset_evaluation_is_modified = true;
  }


  /** 
   * <em>maximum_assessment</em>カラムの値を設定します。 
   *
   * @@param p_maximumAssessment <em>maximum_assessment</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setMaximumAssessment( long p_maximumAssessment )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    maximum_assessment = new Long(p_maximumAssessment);
    maximum_assessment_is_set = true;
    maximum_assessment_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>maximum_assessment</em>カラムに値を設定します。 
   */
  public final void setMaximumAssessment( Long p_maximumAssessment )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    maximum_assessment = p_maximumAssessment;
    maximum_assessment_is_set = true;
    maximum_assessment_is_modified = true;
  }


  /** 
   * <em>stock_evaluation</em>カラムの値を設定します。 
   *
   * @@param p_stockEvaluation <em>stock_evaluation</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStockEvaluation( String p_stockEvaluation )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stock_evaluation = p_stockEvaluation;
    stock_evaluation_is_set = true;
    stock_evaluation_is_modified = true;
  }


  /** 
   * <em>gp_evaluation</em>カラムの値を設定します。 
   *
   * @@param p_gpEvaluation <em>gp_evaluation</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setGpEvaluation( String p_gpEvaluation )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    gp_evaluation = p_gpEvaluation;
    gp_evaluation_is_set = true;
    gp_evaluation_is_modified = true;
  }


  /** 
   * <em>fund_evaluation</em>カラムの値を設定します。 
   *
   * @@param p_fundEvaluation <em>fund_evaluation</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFundEvaluation( String p_fundEvaluation )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fund_evaluation = p_fundEvaluation;
    fund_evaluation_is_set = true;
    fund_evaluation_is_modified = true;
  }


  /** 
   * <em>bond_evaluation</em>カラムの値を設定します。 
   *
   * @@param p_bondEvaluation <em>bond_evaluation</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBondEvaluation( String p_bondEvaluation )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bond_evaluation = p_bondEvaluation;
    bond_evaluation_is_set = true;
    bond_evaluation_is_modified = true;
  }


  /** 
   * <em>enable_ipo_quantity_change</em>カラムの値を設定します。 
   *
   * @@param p_enableIpoQuantityChange <em>enable_ipo_quantity_change</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEnableIpoQuantityChange( String p_enableIpoQuantityChange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    enable_ipo_quantity_change = p_enableIpoQuantityChange;
    enable_ipo_quantity_change_is_set = true;
    enable_ipo_quantity_change_is_modified = true;
  }


  /** 
   * <em>deposit_inform_mail_send</em>カラムの値を設定します。 
   *
   * @@param p_depositInformMailSend <em>deposit_inform_mail_send</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDepositInformMailSend( String p_depositInformMailSend )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deposit_inform_mail_send = p_depositInformMailSend;
    deposit_inform_mail_send_is_set = true;
    deposit_inform_mail_send_is_modified = true;
  }


  /** 
   * <em>payment_apply_trigger</em>カラムの値を設定します。 
   *
   * @@param p_paymentApplyTrigger <em>payment_apply_trigger</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPaymentApplyTrigger( String p_paymentApplyTrigger )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_apply_trigger = p_paymentApplyTrigger;
    payment_apply_trigger_is_set = true;
    payment_apply_trigger_is_modified = true;
  }


  /** 
   * <em>payment_apply_admin_task</em>カラムの値を設定します。 
   *
   * @@param p_paymentApplyAdminTask <em>payment_apply_admin_task</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPaymentApplyAdminTask( String p_paymentApplyAdminTask )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_apply_admin_task = p_paymentApplyAdminTask;
    payment_apply_admin_task_is_set = true;
    payment_apply_admin_task_is_modified = true;
  }


  /** 
   * <em>payment_reserve</em>カラムの値を設定します。 
   *
   * @@param p_paymentReserve <em>payment_reserve</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPaymentReserve( String p_paymentReserve )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_reserve = p_paymentReserve;
    payment_reserve_is_set = true;
    payment_reserve_is_modified = true;
  }


  /** 
   * <em>the_day_transfer</em>カラムの値を設定します。 
   *
   * @@param p_theDayTransfer <em>the_day_transfer</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTheDayTransfer( String p_theDayTransfer )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    the_day_transfer = p_theDayTransfer;
    the_day_transfer_is_set = true;
    the_day_transfer_is_modified = true;
  }


  /** 
   * <em>payment_automatic</em>カラムの値を設定します。 
   *
   * @@param p_paymentAutomatic <em>payment_automatic</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPaymentAutomatic( String p_paymentAutomatic )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_automatic = p_paymentAutomatic;
    payment_automatic_is_set = true;
    payment_automatic_is_modified = true;
  }


  /** 
   * <em>max_order_quantity</em>カラムの値を設定します。 
   *
   * @@param p_maxOrderQuantity <em>max_order_quantity</em>カラムの値をあらわす10桁以下のlong値 
   */
  public final void setMaxOrderQuantity( long p_maxOrderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    max_order_quantity = new Long(p_maxOrderQuantity);
    max_order_quantity_is_set = true;
    max_order_quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>max_order_quantity</em>カラムに値を設定します。 
   */
  public final void setMaxOrderQuantity( Long p_maxOrderQuantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    max_order_quantity = p_maxOrderQuantity;
    max_order_quantity_is_set = true;
    max_order_quantity_is_modified = true;
  }


  /** 
   * <em>off_floor_order_start_hhmmss</em>カラムの値を設定します。 
   *
   * @@param p_offFloorOrderStartHhmmss <em>off_floor_order_start_hhmmss</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setOffFloorOrderStartHhmmss( String p_offFloorOrderStartHhmmss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    off_floor_order_start_hhmmss = p_offFloorOrderStartHhmmss;
    off_floor_order_start_hhmmss_is_set = true;
    off_floor_order_start_hhmmss_is_modified = true;
  }


  /** 
   * <em>off_floor_order_end_hhmmss</em>カラムの値を設定します。 
   *
   * @@param p_offFloorOrderEndHhmmss <em>off_floor_order_end_hhmmss</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setOffFloorOrderEndHhmmss( String p_offFloorOrderEndHhmmss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    off_floor_order_end_hhmmss = p_offFloorOrderEndHhmmss;
    off_floor_order_end_hhmmss_is_set = true;
    off_floor_order_end_hhmmss_is_modified = true;
  }


  /** 
   * <em>system_status</em>カラムの値を設定します。 
   *
   * @@param p_systemStatus <em>system_status</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSystemStatus( String p_systemStatus )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    system_status = p_systemStatus;
    system_status_is_set = true;
    system_status_is_modified = true;
  }


  /** 
   * <em>hash_send_a</em>カラムの値を設定します。 
   *
   * @@param p_hashSendA <em>hash_send_a</em>カラムの値をあらわす128桁以下のString値 
   */
  public final void setHashSendA( String p_hashSendA )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    hash_send_a = p_hashSendA;
    hash_send_a_is_set = true;
    hash_send_a_is_modified = true;
  }


  /** 
   * <em>hash_send_b</em>カラムの値を設定します。 
   *
   * @@param p_hashSendB <em>hash_send_b</em>カラムの値をあらわす128桁以下のString値 
   */
  public final void setHashSendB( String p_hashSendB )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    hash_send_b = p_hashSendB;
    hash_send_b_is_set = true;
    hash_send_b_is_modified = true;
  }


  /** 
   * <em>hash_receive_a</em>カラムの値を設定します。 
   *
   * @@param p_hashReceiveA <em>hash_receive_a</em>カラムの値をあらわす128桁以下のString値 
   */
  public final void setHashReceiveA( String p_hashReceiveA )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    hash_receive_a = p_hashReceiveA;
    hash_receive_a_is_set = true;
    hash_receive_a_is_modified = true;
  }


  /** 
   * <em>hash_receive_b</em>カラムの値を設定します。 
   *
   * @@param p_hashReceiveB <em>hash_receive_b</em>カラムの値をあらわす128桁以下のString値 
   */
  public final void setHashReceiveB( String p_hashReceiveB )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    hash_receive_b = p_hashReceiveB;
    hash_receive_b_is_set = true;
    hash_receive_b_is_modified = true;
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
   * <em>slingshot_check_flag</em>カラムの値を設定します。 
   *
   * @@param p_slingshotCheckFlag <em>slingshot_check_flag</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSlingshotCheckFlag( String p_slingshotCheckFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    slingshot_check_flag = p_slingshotCheckFlag;
    slingshot_check_flag_is_set = true;
    slingshot_check_flag_is_modified = true;
  }


  /** 
   * <em>trd_code_min</em>カラムの値を設定します。 
   *
   * @@param p_trdCodeMin <em>trd_code_min</em>カラムの値をあらわす2桁以下のint値 
   */
  public final void setTrdCodeMin( int p_trdCodeMin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trd_code_min = new Integer(p_trdCodeMin);
    trd_code_min_is_set = true;
    trd_code_min_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>trd_code_min</em>カラムに値を設定します。 
   */
  public final void setTrdCodeMin( Integer p_trdCodeMin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trd_code_min = p_trdCodeMin;
    trd_code_min_is_set = true;
    trd_code_min_is_modified = true;
  }


  /** 
   * <em>trd_code_max</em>カラムの値を設定します。 
   *
   * @@param p_trdCodeMax <em>trd_code_max</em>カラムの値をあらわす2桁以下のint値 
   */
  public final void setTrdCodeMax( int p_trdCodeMax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trd_code_max = new Integer(p_trdCodeMax);
    trd_code_max_is_set = true;
    trd_code_max_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>trd_code_max</em>カラムに値を設定します。 
   */
  public final void setTrdCodeMax( Integer p_trdCodeMax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trd_code_max = p_trdCodeMax;
    trd_code_max_is_set = true;
    trd_code_max_is_modified = true;
  }


  /** 
   * <em>trd_code_check_mode</em>カラムの値を設定します。 
   *
   * @@param p_trdCodeCheckMode <em>trd_code_check_mode</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTrdCodeCheckMode( String p_trdCodeCheckMode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trd_code_check_mode = p_trdCodeCheckMode;
    trd_code_check_mode_is_set = true;
    trd_code_check_mode_is_modified = true;
  }


  /** 
   * <em>admin_code_min</em>カラムの値を設定します。 
   *
   * @@param p_adminCodeMin <em>admin_code_min</em>カラムの値をあらわす2桁以下のint値 
   */
  public final void setAdminCodeMin( int p_adminCodeMin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    admin_code_min = new Integer(p_adminCodeMin);
    admin_code_min_is_set = true;
    admin_code_min_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>admin_code_min</em>カラムに値を設定します。 
   */
  public final void setAdminCodeMin( Integer p_adminCodeMin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    admin_code_min = p_adminCodeMin;
    admin_code_min_is_set = true;
    admin_code_min_is_modified = true;
  }


  /** 
   * <em>admin_code_max</em>カラムの値を設定します。 
   *
   * @@param p_adminCodeMax <em>admin_code_max</em>カラムの値をあらわす2桁以下のint値 
   */
  public final void setAdminCodeMax( int p_adminCodeMax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    admin_code_max = new Integer(p_adminCodeMax);
    admin_code_max_is_set = true;
    admin_code_max_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>admin_code_max</em>カラムに値を設定します。 
   */
  public final void setAdminCodeMax( Integer p_adminCodeMax )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    admin_code_max = p_adminCodeMax;
    admin_code_max_is_set = true;
    admin_code_max_is_modified = true;
  }


  /** 
   * <em>admin_code_check_mode</em>カラムの値を設定します。 
   *
   * @@param p_adminCodeCheckMode <em>admin_code_check_mode</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAdminCodeCheckMode( String p_adminCodeCheckMode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    admin_code_check_mode = p_adminCodeCheckMode;
    admin_code_check_mode_is_set = true;
    admin_code_check_mode_is_modified = true;
  }


  /** 
   * <em>ifo_real_price_calc_div</em>カラムの値を設定します。 
   *
   * @@param p_ifoRealPriceCalcDiv <em>ifo_real_price_calc_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setIfoRealPriceCalcDiv( String p_ifoRealPriceCalcDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_real_price_calc_div = p_ifoRealPriceCalcDiv;
    ifo_real_price_calc_div_is_set = true;
    ifo_real_price_calc_div_is_modified = true;
  }


  /** 
   * <em>simple_span_calc_div</em>カラムの値を設定します。 
   *
   * @@param p_simpleSpanCalcDiv <em>simple_span_calc_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSimpleSpanCalcDiv( String p_simpleSpanCalcDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    simple_span_calc_div = p_simpleSpanCalcDiv;
    simple_span_calc_div_is_set = true;
    simple_span_calc_div_is_modified = true;
  }


  /** 
   * <em>span_trouble_div</em>カラムの値を設定します。 
   *
   * @@param p_spanTroubleDiv <em>span_trouble_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSpanTroubleDiv( String p_spanTroubleDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    span_trouble_div = p_spanTroubleDiv;
    span_trouble_div_is_set = true;
    span_trouble_div_is_modified = true;
  }


  /** 
   * <em>span_factor</em>カラムの値を設定します。 
   *
   * @@param p_spanFactor <em>span_factor</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSpanFactor( double p_spanFactor )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    span_factor = new Double(p_spanFactor);
    span_factor_is_set = true;
    span_factor_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>span_factor</em>カラムに値を設定します。 
   */
  public final void setSpanFactor( Double p_spanFactor )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    span_factor = p_spanFactor;
    span_factor_is_set = true;
    span_factor_is_modified = true;
  }


  /** 
   * <em>span_factor_red</em>カラムの値を設定します。 
   *
   * @@param p_spanFactorRed <em>span_factor_red</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSpanFactorRed( double p_spanFactorRed )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    span_factor_red = new Double(p_spanFactorRed);
    span_factor_red_is_set = true;
    span_factor_red_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>span_factor_red</em>カラムに値を設定します。 
   */
  public final void setSpanFactorRed( Double p_spanFactorRed )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    span_factor_red = p_spanFactorRed;
    span_factor_red_is_set = true;
    span_factor_red_is_modified = true;
  }


  /** 
   * <em>transfer_power_factor</em>カラムの値を設定します。 
   *
   * @@param p_transferPowerFactor <em>transfer_power_factor</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTransferPowerFactor( double p_transferPowerFactor )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_power_factor = new Double(p_transferPowerFactor);
    transfer_power_factor_is_set = true;
    transfer_power_factor_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>transfer_power_factor</em>カラムに値を設定します。 
   */
  public final void setTransferPowerFactor( Double p_transferPowerFactor )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    transfer_power_factor = p_transferPowerFactor;
    transfer_power_factor_is_set = true;
    transfer_power_factor_is_modified = true;
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
   * <em>future_day_trade_charge_div</em>カラムの値を設定します。 
   *
   * @@param p_futureDayTradeChargeDiv <em>future_day_trade_charge_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFutureDayTradeChargeDiv( String p_futureDayTradeChargeDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    future_day_trade_charge_div = p_futureDayTradeChargeDiv;
    future_day_trade_charge_div_is_set = true;
    future_day_trade_charge_div_is_modified = true;
  }


  /** 
   * <em>securitites_cos_id_code</em>カラムの値を設定します。 
   *
   * @@param p_securititesCosIdCode <em>securitites_cos_id_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setSecurititesCosIdCode( String p_securititesCosIdCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    securitites_cos_id_code = p_securititesCosIdCode;
    securitites_cos_id_code_is_set = true;
    securitites_cos_id_code_is_modified = true;
  }


  /** 
   * <em>db_current_price_check_div</em>カラムの値を設定します。 
   *
   * @@param p_dbCurrentPriceCheckDiv <em>db_current_price_check_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDbCurrentPriceCheckDiv( String p_dbCurrentPriceCheckDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    db_current_price_check_div = p_dbCurrentPriceCheckDiv;
    db_current_price_check_div_is_set = true;
    db_current_price_check_div_is_modified = true;
  }


  /** 
   * <em>forcedsettleorder_div</em>カラムの値を設定します。 
   *
   * @@param p_forcedsettleorderDiv <em>forcedsettleorder_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setForcedsettleorderDiv( String p_forcedsettleorderDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    forcedsettleorder_div = p_forcedsettleorderDiv;
    forcedsettleorder_div_is_set = true;
    forcedsettleorder_div_is_modified = true;
  }


  /** 
   * <em>forcedsettleorder_closeday_cnt</em>カラムの値を設定します。 
   *
   * @@param p_forcedsettleorderClosedayCnt <em>forcedsettleorder_closeday_cnt</em>カラムの値をあらわす2桁以下のint値 
   */
  public final void setForcedsettleorderClosedayCnt( int p_forcedsettleorderClosedayCnt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    forcedsettleorder_closeday_cnt = new Integer(p_forcedsettleorderClosedayCnt);
    forcedsettleorder_closeday_cnt_is_set = true;
    forcedsettleorder_closeday_cnt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>forcedsettleorder_closeday_cnt</em>カラムに値を設定します。 
   */
  public final void setForcedsettleorderClosedayCnt( Integer p_forcedsettleorderClosedayCnt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    forcedsettleorder_closeday_cnt = p_forcedsettleorderClosedayCnt;
    forcedsettleorder_closeday_cnt_is_set = true;
    forcedsettleorder_closeday_cnt_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("asset_evaluation") ) {
                    return this.asset_evaluation;
                }
                else if ( name.equals("admin_code_min") ) {
                    return this.admin_code_min;
                }
                else if ( name.equals("admin_code_max") ) {
                    return this.admin_code_max;
                }
                else if ( name.equals("admin_code_check_mode") ) {
                    return this.admin_code_check_mode;
                }
                break;
            case 'b':
                if ( name.equals("bond_evaluation") ) {
                    return this.bond_evaluation;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("deposit_inform_mail_send") ) {
                    return this.deposit_inform_mail_send;
                }
                else if ( name.equals("db_current_price_check_div") ) {
                    return this.db_current_price_check_div;
                }
                break;
            case 'e':
                if ( name.equals("enable_ipo_quantity_change") ) {
                    return this.enable_ipo_quantity_change;
                }
                else if ( name.equals("email_address") ) {
                    return this.email_address;
                }
                break;
            case 'f':
                if ( name.equals("fund_evaluation") ) {
                    return this.fund_evaluation;
                }
                else if ( name.equals("future_day_trade_charge_div") ) {
                    return this.future_day_trade_charge_div;
                }
                else if ( name.equals("forcedsettleorder_div") ) {
                    return this.forcedsettleorder_div;
                }
                else if ( name.equals("forcedsettleorder_closeday_cnt") ) {
                    return this.forcedsettleorder_closeday_cnt;
                }
                break;
            case 'g':
                if ( name.equals("gp_evaluation") ) {
                    return this.gp_evaluation;
                }
                break;
            case 'h':
                if ( name.equals("hash_send_a") ) {
                    return this.hash_send_a;
                }
                else if ( name.equals("hash_send_b") ) {
                    return this.hash_send_b;
                }
                else if ( name.equals("hash_receive_a") ) {
                    return this.hash_receive_a;
                }
                else if ( name.equals("hash_receive_b") ) {
                    return this.hash_receive_b;
                }
                break;
            case 'i':
                if ( name.equals("institution_id") ) {
                    return new Long( institution_id );
                }
                else if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("institution_name") ) {
                    return this.institution_name;
                }
                else if ( name.equals("ifo_real_price_calc_div") ) {
                    return this.ifo_real_price_calc_div;
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
                if ( name.equals("maximum_assessment") ) {
                    return this.maximum_assessment;
                }
                else if ( name.equals("max_order_quantity") ) {
                    return this.max_order_quantity;
                }
                break;
            case 'o':
                if ( name.equals("off_floor_order_start_hhmmss") ) {
                    return this.off_floor_order_start_hhmmss;
                }
                else if ( name.equals("off_floor_order_end_hhmmss") ) {
                    return this.off_floor_order_end_hhmmss;
                }
                break;
            case 'p':
                if ( name.equals("payment_apply_trigger") ) {
                    return this.payment_apply_trigger;
                }
                else if ( name.equals("payment_apply_admin_task") ) {
                    return this.payment_apply_admin_task;
                }
                else if ( name.equals("payment_reserve") ) {
                    return this.payment_reserve;
                }
                else if ( name.equals("payment_automatic") ) {
                    return this.payment_automatic;
                }
                break;
            case 's':
                if ( name.equals("stock_evaluation") ) {
                    return this.stock_evaluation;
                }
                else if ( name.equals("system_status") ) {
                    return this.system_status;
                }
                else if ( name.equals("slingshot_check_flag") ) {
                    return this.slingshot_check_flag;
                }
                else if ( name.equals("simple_span_calc_div") ) {
                    return this.simple_span_calc_div;
                }
                else if ( name.equals("span_trouble_div") ) {
                    return this.span_trouble_div;
                }
                else if ( name.equals("span_factor") ) {
                    return this.span_factor;
                }
                else if ( name.equals("span_factor_red") ) {
                    return this.span_factor_red;
                }
                else if ( name.equals("securitites_cos_id_code") ) {
                    return this.securitites_cos_id_code;
                }
                break;
            case 't':
                if ( name.equals("the_day_transfer") ) {
                    return this.the_day_transfer;
                }
                else if ( name.equals("trd_code_min") ) {
                    return this.trd_code_min;
                }
                else if ( name.equals("trd_code_max") ) {
                    return this.trd_code_max;
                }
                else if ( name.equals("trd_code_check_mode") ) {
                    return this.trd_code_check_mode;
                }
                else if ( name.equals("transfer_power_factor") ) {
                    return this.transfer_power_factor;
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
                if ( name.equals("asset_evaluation") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'asset_evaluation' must be String: '"+value+"' is not." );
                    this.asset_evaluation = (String) value;
                    if (this.asset_evaluation_is_set)
                        this.asset_evaluation_is_modified = true;
                    this.asset_evaluation_is_set = true;
                    return;
                }
                else if ( name.equals("admin_code_min") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'admin_code_min' must be Integer: '"+value+"' is not." );
                    this.admin_code_min = (Integer) value;
                    if (this.admin_code_min_is_set)
                        this.admin_code_min_is_modified = true;
                    this.admin_code_min_is_set = true;
                    return;
                }
                else if ( name.equals("admin_code_max") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'admin_code_max' must be Integer: '"+value+"' is not." );
                    this.admin_code_max = (Integer) value;
                    if (this.admin_code_max_is_set)
                        this.admin_code_max_is_modified = true;
                    this.admin_code_max_is_set = true;
                    return;
                }
                else if ( name.equals("admin_code_check_mode") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'admin_code_check_mode' must be String: '"+value+"' is not." );
                    this.admin_code_check_mode = (String) value;
                    if (this.admin_code_check_mode_is_set)
                        this.admin_code_check_mode_is_modified = true;
                    this.admin_code_check_mode_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("bond_evaluation") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bond_evaluation' must be String: '"+value+"' is not." );
                    this.bond_evaluation = (String) value;
                    if (this.bond_evaluation_is_set)
                        this.bond_evaluation_is_modified = true;
                    this.bond_evaluation_is_set = true;
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
                if ( name.equals("deposit_inform_mail_send") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deposit_inform_mail_send' must be String: '"+value+"' is not." );
                    this.deposit_inform_mail_send = (String) value;
                    if (this.deposit_inform_mail_send_is_set)
                        this.deposit_inform_mail_send_is_modified = true;
                    this.deposit_inform_mail_send_is_set = true;
                    return;
                }
                else if ( name.equals("db_current_price_check_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'db_current_price_check_div' must be String: '"+value+"' is not." );
                    this.db_current_price_check_div = (String) value;
                    if (this.db_current_price_check_div_is_set)
                        this.db_current_price_check_div_is_modified = true;
                    this.db_current_price_check_div_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("enable_ipo_quantity_change") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'enable_ipo_quantity_change' must be String: '"+value+"' is not." );
                    this.enable_ipo_quantity_change = (String) value;
                    if (this.enable_ipo_quantity_change_is_set)
                        this.enable_ipo_quantity_change_is_modified = true;
                    this.enable_ipo_quantity_change_is_set = true;
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
                break;
            case 'f':
                if ( name.equals("fund_evaluation") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fund_evaluation' must be String: '"+value+"' is not." );
                    this.fund_evaluation = (String) value;
                    if (this.fund_evaluation_is_set)
                        this.fund_evaluation_is_modified = true;
                    this.fund_evaluation_is_set = true;
                    return;
                }
                else if ( name.equals("future_day_trade_charge_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'future_day_trade_charge_div' must be String: '"+value+"' is not." );
                    this.future_day_trade_charge_div = (String) value;
                    if (this.future_day_trade_charge_div_is_set)
                        this.future_day_trade_charge_div_is_modified = true;
                    this.future_day_trade_charge_div_is_set = true;
                    return;
                }
                else if ( name.equals("forcedsettleorder_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'forcedsettleorder_div' must be String: '"+value+"' is not." );
                    this.forcedsettleorder_div = (String) value;
                    if (this.forcedsettleorder_div_is_set)
                        this.forcedsettleorder_div_is_modified = true;
                    this.forcedsettleorder_div_is_set = true;
                    return;
                }
                else if ( name.equals("forcedsettleorder_closeday_cnt") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'forcedsettleorder_closeday_cnt' must be Integer: '"+value+"' is not." );
                    this.forcedsettleorder_closeday_cnt = (Integer) value;
                    if (this.forcedsettleorder_closeday_cnt_is_set)
                        this.forcedsettleorder_closeday_cnt_is_modified = true;
                    this.forcedsettleorder_closeday_cnt_is_set = true;
                    return;
                }
                break;
            case 'g':
                if ( name.equals("gp_evaluation") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'gp_evaluation' must be String: '"+value+"' is not." );
                    this.gp_evaluation = (String) value;
                    if (this.gp_evaluation_is_set)
                        this.gp_evaluation_is_modified = true;
                    this.gp_evaluation_is_set = true;
                    return;
                }
                break;
            case 'h':
                if ( name.equals("hash_send_a") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'hash_send_a' must be String: '"+value+"' is not." );
                    this.hash_send_a = (String) value;
                    if (this.hash_send_a_is_set)
                        this.hash_send_a_is_modified = true;
                    this.hash_send_a_is_set = true;
                    return;
                }
                else if ( name.equals("hash_send_b") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'hash_send_b' must be String: '"+value+"' is not." );
                    this.hash_send_b = (String) value;
                    if (this.hash_send_b_is_set)
                        this.hash_send_b_is_modified = true;
                    this.hash_send_b_is_set = true;
                    return;
                }
                else if ( name.equals("hash_receive_a") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'hash_receive_a' must be String: '"+value+"' is not." );
                    this.hash_receive_a = (String) value;
                    if (this.hash_receive_a_is_set)
                        this.hash_receive_a_is_modified = true;
                    this.hash_receive_a_is_set = true;
                    return;
                }
                else if ( name.equals("hash_receive_b") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'hash_receive_b' must be String: '"+value+"' is not." );
                    this.hash_receive_b = (String) value;
                    if (this.hash_receive_b_is_set)
                        this.hash_receive_b_is_modified = true;
                    this.hash_receive_b_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'institution_id' must be Long: '"+value+"' is not." );
                    this.institution_id = ((Long) value).longValue();
                    if (this.institution_id_is_set)
                        this.institution_id_is_modified = true;
                    this.institution_id_is_set = true;
                    return;
                }
                else if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("institution_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_name' must be String: '"+value+"' is not." );
                    this.institution_name = (String) value;
                    if (this.institution_name_is_set)
                        this.institution_name_is_modified = true;
                    this.institution_name_is_set = true;
                    return;
                }
                else if ( name.equals("ifo_real_price_calc_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_real_price_calc_div' must be String: '"+value+"' is not." );
                    this.ifo_real_price_calc_div = (String) value;
                    if (this.ifo_real_price_calc_div_is_set)
                        this.ifo_real_price_calc_div_is_modified = true;
                    this.ifo_real_price_calc_div_is_set = true;
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
                if ( name.equals("maximum_assessment") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'maximum_assessment' must be Long: '"+value+"' is not." );
                    this.maximum_assessment = (Long) value;
                    if (this.maximum_assessment_is_set)
                        this.maximum_assessment_is_modified = true;
                    this.maximum_assessment_is_set = true;
                    return;
                }
                else if ( name.equals("max_order_quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'max_order_quantity' must be Long: '"+value+"' is not." );
                    this.max_order_quantity = (Long) value;
                    if (this.max_order_quantity_is_set)
                        this.max_order_quantity_is_modified = true;
                    this.max_order_quantity_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("off_floor_order_start_hhmmss") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'off_floor_order_start_hhmmss' must be String: '"+value+"' is not." );
                    this.off_floor_order_start_hhmmss = (String) value;
                    if (this.off_floor_order_start_hhmmss_is_set)
                        this.off_floor_order_start_hhmmss_is_modified = true;
                    this.off_floor_order_start_hhmmss_is_set = true;
                    return;
                }
                else if ( name.equals("off_floor_order_end_hhmmss") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'off_floor_order_end_hhmmss' must be String: '"+value+"' is not." );
                    this.off_floor_order_end_hhmmss = (String) value;
                    if (this.off_floor_order_end_hhmmss_is_set)
                        this.off_floor_order_end_hhmmss_is_modified = true;
                    this.off_floor_order_end_hhmmss_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("payment_apply_trigger") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_apply_trigger' must be String: '"+value+"' is not." );
                    this.payment_apply_trigger = (String) value;
                    if (this.payment_apply_trigger_is_set)
                        this.payment_apply_trigger_is_modified = true;
                    this.payment_apply_trigger_is_set = true;
                    return;
                }
                else if ( name.equals("payment_apply_admin_task") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_apply_admin_task' must be String: '"+value+"' is not." );
                    this.payment_apply_admin_task = (String) value;
                    if (this.payment_apply_admin_task_is_set)
                        this.payment_apply_admin_task_is_modified = true;
                    this.payment_apply_admin_task_is_set = true;
                    return;
                }
                else if ( name.equals("payment_reserve") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_reserve' must be String: '"+value+"' is not." );
                    this.payment_reserve = (String) value;
                    if (this.payment_reserve_is_set)
                        this.payment_reserve_is_modified = true;
                    this.payment_reserve_is_set = true;
                    return;
                }
                else if ( name.equals("payment_automatic") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_automatic' must be String: '"+value+"' is not." );
                    this.payment_automatic = (String) value;
                    if (this.payment_automatic_is_set)
                        this.payment_automatic_is_modified = true;
                    this.payment_automatic_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("stock_evaluation") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stock_evaluation' must be String: '"+value+"' is not." );
                    this.stock_evaluation = (String) value;
                    if (this.stock_evaluation_is_set)
                        this.stock_evaluation_is_modified = true;
                    this.stock_evaluation_is_set = true;
                    return;
                }
                else if ( name.equals("system_status") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'system_status' must be String: '"+value+"' is not." );
                    this.system_status = (String) value;
                    if (this.system_status_is_set)
                        this.system_status_is_modified = true;
                    this.system_status_is_set = true;
                    return;
                }
                else if ( name.equals("slingshot_check_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'slingshot_check_flag' must be String: '"+value+"' is not." );
                    this.slingshot_check_flag = (String) value;
                    if (this.slingshot_check_flag_is_set)
                        this.slingshot_check_flag_is_modified = true;
                    this.slingshot_check_flag_is_set = true;
                    return;
                }
                else if ( name.equals("simple_span_calc_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'simple_span_calc_div' must be String: '"+value+"' is not." );
                    this.simple_span_calc_div = (String) value;
                    if (this.simple_span_calc_div_is_set)
                        this.simple_span_calc_div_is_modified = true;
                    this.simple_span_calc_div_is_set = true;
                    return;
                }
                else if ( name.equals("span_trouble_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'span_trouble_div' must be String: '"+value+"' is not." );
                    this.span_trouble_div = (String) value;
                    if (this.span_trouble_div_is_set)
                        this.span_trouble_div_is_modified = true;
                    this.span_trouble_div_is_set = true;
                    return;
                }
                else if ( name.equals("span_factor") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'span_factor' must be Double: '"+value+"' is not." );
                    this.span_factor = (Double) value;
                    if (this.span_factor_is_set)
                        this.span_factor_is_modified = true;
                    this.span_factor_is_set = true;
                    return;
                }
                else if ( name.equals("span_factor_red") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'span_factor_red' must be Double: '"+value+"' is not." );
                    this.span_factor_red = (Double) value;
                    if (this.span_factor_red_is_set)
                        this.span_factor_red_is_modified = true;
                    this.span_factor_red_is_set = true;
                    return;
                }
                else if ( name.equals("securitites_cos_id_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'securitites_cos_id_code' must be String: '"+value+"' is not." );
                    this.securitites_cos_id_code = (String) value;
                    if (this.securitites_cos_id_code_is_set)
                        this.securitites_cos_id_code_is_modified = true;
                    this.securitites_cos_id_code_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("the_day_transfer") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'the_day_transfer' must be String: '"+value+"' is not." );
                    this.the_day_transfer = (String) value;
                    if (this.the_day_transfer_is_set)
                        this.the_day_transfer_is_modified = true;
                    this.the_day_transfer_is_set = true;
                    return;
                }
                else if ( name.equals("trd_code_min") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'trd_code_min' must be Integer: '"+value+"' is not." );
                    this.trd_code_min = (Integer) value;
                    if (this.trd_code_min_is_set)
                        this.trd_code_min_is_modified = true;
                    this.trd_code_min_is_set = true;
                    return;
                }
                else if ( name.equals("trd_code_max") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'trd_code_max' must be Integer: '"+value+"' is not." );
                    this.trd_code_max = (Integer) value;
                    if (this.trd_code_max_is_set)
                        this.trd_code_max_is_modified = true;
                    this.trd_code_max_is_set = true;
                    return;
                }
                else if ( name.equals("trd_code_check_mode") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trd_code_check_mode' must be String: '"+value+"' is not." );
                    this.trd_code_check_mode = (String) value;
                    if (this.trd_code_check_mode_is_set)
                        this.trd_code_check_mode_is_modified = true;
                    this.trd_code_check_mode_is_set = true;
                    return;
                }
                else if ( name.equals("transfer_power_factor") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'transfer_power_factor' must be Double: '"+value+"' is not." );
                    this.transfer_power_factor = (Double) value;
                    if (this.transfer_power_factor_is_set)
                        this.transfer_power_factor_is_modified = true;
                    this.transfer_power_factor_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
