head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.34.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	InstitutionRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;

/** 
 * InstitutionRowインタフェースは変更不可でリードオンリーである<em>institution</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link InstitutionRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see InstitutionPK 
 */
public interface InstitutionRow extends Row {


  /** 
   * この{@@link InstitutionRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "institution", "master" );


  /** 
   * <em>institution_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getInstitutionId();


  /** 
   * <em>institution_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionIdIsSet();


  /** 
   * <em>institution_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionIdIsModified();


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInstitutionCode();


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionCodeIsSet();


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionCodeIsModified();


  /** 
   * <em>institution_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInstitutionName();


  /** 
   * <em>institution_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionNameIsSet();


  /** 
   * <em>institution_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionNameIsModified();


  /** 
   * <em>asset_evaluation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAssetEvaluation();


  /** 
   * <em>asset_evaluation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAssetEvaluationIsSet();


  /** 
   * <em>asset_evaluation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAssetEvaluationIsModified();


  /** 
   * <em>maximum_assessment</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMaximumAssessment();


  /** 
   * <em>maximum_assessment</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaximumAssessmentIsNull();


  /** 
   * <em>maximum_assessment</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaximumAssessmentIsSet();


  /** 
   * <em>maximum_assessment</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaximumAssessmentIsModified();


  /** 
   * <em>stock_evaluation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStockEvaluation();


  /** 
   * <em>stock_evaluation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStockEvaluationIsSet();


  /** 
   * <em>stock_evaluation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStockEvaluationIsModified();


  /** 
   * <em>gp_evaluation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpEvaluation();


  /** 
   * <em>gp_evaluation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpEvaluationIsSet();


  /** 
   * <em>gp_evaluation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpEvaluationIsModified();


  /** 
   * <em>fund_evaluation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFundEvaluation();


  /** 
   * <em>fund_evaluation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFundEvaluationIsSet();


  /** 
   * <em>fund_evaluation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFundEvaluationIsModified();


  /** 
   * <em>bond_evaluation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBondEvaluation();


  /** 
   * <em>bond_evaluation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondEvaluationIsSet();


  /** 
   * <em>bond_evaluation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondEvaluationIsModified();


  /** 
   * <em>enable_ipo_quantity_change</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEnableIpoQuantityChange();


  /** 
   * <em>enable_ipo_quantity_change</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEnableIpoQuantityChangeIsSet();


  /** 
   * <em>enable_ipo_quantity_change</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEnableIpoQuantityChangeIsModified();


  /** 
   * <em>deposit_inform_mail_send</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDepositInformMailSend();


  /** 
   * <em>deposit_inform_mail_send</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositInformMailSendIsSet();


  /** 
   * <em>deposit_inform_mail_send</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepositInformMailSendIsModified();


  /** 
   * <em>payment_apply_trigger</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPaymentApplyTrigger();


  /** 
   * <em>payment_apply_trigger</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentApplyTriggerIsSet();


  /** 
   * <em>payment_apply_trigger</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentApplyTriggerIsModified();


  /** 
   * <em>payment_apply_admin_task</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPaymentApplyAdminTask();


  /** 
   * <em>payment_apply_admin_task</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentApplyAdminTaskIsSet();


  /** 
   * <em>payment_apply_admin_task</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentApplyAdminTaskIsModified();


  /** 
   * <em>payment_reserve</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPaymentReserve();


  /** 
   * <em>payment_reserve</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentReserveIsSet();


  /** 
   * <em>payment_reserve</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentReserveIsModified();


  /** 
   * <em>the_day_transfer</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTheDayTransfer();


  /** 
   * <em>the_day_transfer</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTheDayTransferIsSet();


  /** 
   * <em>the_day_transfer</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTheDayTransferIsModified();


  /** 
   * <em>payment_automatic</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPaymentAutomatic();


  /** 
   * <em>payment_automatic</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAutomaticIsSet();


  /** 
   * <em>payment_automatic</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAutomaticIsModified();


  /** 
   * <em>max_order_quantity</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMaxOrderQuantity();


  /** 
   * <em>max_order_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaxOrderQuantityIsNull();


  /** 
   * <em>max_order_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxOrderQuantityIsSet();


  /** 
   * <em>max_order_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxOrderQuantityIsModified();


  /** 
   * <em>off_floor_order_start_hhmmss</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOffFloorOrderStartHhmmss();


  /** 
   * <em>off_floor_order_start_hhmmss</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOffFloorOrderStartHhmmssIsSet();


  /** 
   * <em>off_floor_order_start_hhmmss</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOffFloorOrderStartHhmmssIsModified();


  /** 
   * <em>off_floor_order_end_hhmmss</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOffFloorOrderEndHhmmss();


  /** 
   * <em>off_floor_order_end_hhmmss</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOffFloorOrderEndHhmmssIsSet();


  /** 
   * <em>off_floor_order_end_hhmmss</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOffFloorOrderEndHhmmssIsModified();


  /** 
   * <em>system_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSystemStatus();


  /** 
   * <em>system_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSystemStatusIsSet();


  /** 
   * <em>system_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSystemStatusIsModified();


  /** 
   * <em>hash_send_a</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHashSendA();


  /** 
   * <em>hash_send_a</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHashSendAIsSet();


  /** 
   * <em>hash_send_a</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHashSendAIsModified();


  /** 
   * <em>hash_send_b</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHashSendB();


  /** 
   * <em>hash_send_b</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHashSendBIsSet();


  /** 
   * <em>hash_send_b</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHashSendBIsModified();


  /** 
   * <em>hash_receive_a</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHashReceiveA();


  /** 
   * <em>hash_receive_a</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHashReceiveAIsSet();


  /** 
   * <em>hash_receive_a</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHashReceiveAIsModified();


  /** 
   * <em>hash_receive_b</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHashReceiveB();


  /** 
   * <em>hash_receive_b</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHashReceiveBIsSet();


  /** 
   * <em>hash_receive_b</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHashReceiveBIsModified();


  /** 
   * <em>email_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEmailAddress();


  /** 
   * <em>email_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEmailAddressIsSet();


  /** 
   * <em>email_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEmailAddressIsModified();


  /** 
   * <em>slingshot_check_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSlingshotCheckFlag();


  /** 
   * <em>slingshot_check_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSlingshotCheckFlagIsSet();


  /** 
   * <em>slingshot_check_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSlingshotCheckFlagIsModified();


  /** 
   * <em>trd_code_min</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getTrdCodeMin();


  /** 
   * <em>trd_code_min</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTrdCodeMinIsNull();


  /** 
   * <em>trd_code_min</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrdCodeMinIsSet();


  /** 
   * <em>trd_code_min</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrdCodeMinIsModified();


  /** 
   * <em>trd_code_max</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getTrdCodeMax();


  /** 
   * <em>trd_code_max</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTrdCodeMaxIsNull();


  /** 
   * <em>trd_code_max</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrdCodeMaxIsSet();


  /** 
   * <em>trd_code_max</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrdCodeMaxIsModified();


  /** 
   * <em>trd_code_check_mode</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTrdCodeCheckMode();


  /** 
   * <em>trd_code_check_mode</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrdCodeCheckModeIsSet();


  /** 
   * <em>trd_code_check_mode</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrdCodeCheckModeIsModified();


  /** 
   * <em>admin_code_min</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getAdminCodeMin();


  /** 
   * <em>admin_code_min</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAdminCodeMinIsNull();


  /** 
   * <em>admin_code_min</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAdminCodeMinIsSet();


  /** 
   * <em>admin_code_min</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAdminCodeMinIsModified();


  /** 
   * <em>admin_code_max</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getAdminCodeMax();


  /** 
   * <em>admin_code_max</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAdminCodeMaxIsNull();


  /** 
   * <em>admin_code_max</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAdminCodeMaxIsSet();


  /** 
   * <em>admin_code_max</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAdminCodeMaxIsModified();


  /** 
   * <em>admin_code_check_mode</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAdminCodeCheckMode();


  /** 
   * <em>admin_code_check_mode</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAdminCodeCheckModeIsSet();


  /** 
   * <em>admin_code_check_mode</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAdminCodeCheckModeIsModified();


  /** 
   * <em>ifo_real_price_calc_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoRealPriceCalcDiv();


  /** 
   * <em>ifo_real_price_calc_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoRealPriceCalcDivIsSet();


  /** 
   * <em>ifo_real_price_calc_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoRealPriceCalcDivIsModified();


  /** 
   * <em>simple_span_calc_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSimpleSpanCalcDiv();


  /** 
   * <em>simple_span_calc_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSimpleSpanCalcDivIsSet();


  /** 
   * <em>simple_span_calc_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSimpleSpanCalcDivIsModified();


  /** 
   * <em>span_trouble_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSpanTroubleDiv();


  /** 
   * <em>span_trouble_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpanTroubleDivIsSet();


  /** 
   * <em>span_trouble_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpanTroubleDivIsModified();


  /** 
   * <em>span_factor</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSpanFactor();


  /** 
   * <em>span_factor</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSpanFactorIsNull();


  /** 
   * <em>span_factor</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpanFactorIsSet();


  /** 
   * <em>span_factor</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpanFactorIsModified();


  /** 
   * <em>span_factor_red</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSpanFactorRed();


  /** 
   * <em>span_factor_red</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSpanFactorRedIsNull();


  /** 
   * <em>span_factor_red</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpanFactorRedIsSet();


  /** 
   * <em>span_factor_red</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpanFactorRedIsModified();


  /** 
   * <em>transfer_power_factor</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTransferPowerFactor();


  /** 
   * <em>transfer_power_factor</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTransferPowerFactorIsNull();


  /** 
   * <em>transfer_power_factor</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferPowerFactorIsSet();


  /** 
   * <em>transfer_power_factor</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferPowerFactorIsModified();


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLastUpdater();


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdaterIsSet();


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdaterIsModified();


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getCreatedTimestamp();


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCreatedTimestampIsSet();


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCreatedTimestampIsModified();


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getLastUpdatedTimestamp();


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdatedTimestampIsSet();


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdatedTimestampIsModified();


  /** 
   * <em>future_day_trade_charge_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFutureDayTradeChargeDiv();


  /** 
   * <em>future_day_trade_charge_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFutureDayTradeChargeDivIsSet();


  /** 
   * <em>future_day_trade_charge_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFutureDayTradeChargeDivIsModified();


  /** 
   * <em>securitites_cos_id_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSecurititesCosIdCode();


  /** 
   * <em>securitites_cos_id_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecurititesCosIdCodeIsSet();


  /** 
   * <em>securitites_cos_id_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecurititesCosIdCodeIsModified();


  /** 
   * <em>db_current_price_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDbCurrentPriceCheckDiv();


  /** 
   * <em>db_current_price_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDbCurrentPriceCheckDivIsSet();


  /** 
   * <em>db_current_price_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDbCurrentPriceCheckDivIsModified();


  /** 
   * <em>forcedsettleorder_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getForcedsettleorderDiv();


  /** 
   * <em>forcedsettleorder_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForcedsettleorderDivIsSet();


  /** 
   * <em>forcedsettleorder_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForcedsettleorderDivIsModified();


  /** 
   * <em>forcedsettleorder_closeday_cnt</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getForcedsettleorderClosedayCnt();


  /** 
   * <em>forcedsettleorder_closeday_cnt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getForcedsettleorderClosedayCntIsNull();


  /** 
   * <em>forcedsettleorder_closeday_cnt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForcedsettleorderClosedayCntIsSet();


  /** 
   * <em>forcedsettleorder_closeday_cnt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForcedsettleorderClosedayCntIsModified();


}
@
