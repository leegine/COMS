head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.56.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	;
permissions	666;
commitid	6a04d801c24738b;
filename	CompBankConditionRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * CompBankConditionRowインタフェースは変更不可でリードオンリーである<em>comp_bank_condition</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link CompBankConditionRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see CompBankConditionPK 
 */
public interface CompBankConditionRow extends Row {


  /** 
   * この{@@link CompBankConditionRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "comp_bank_condition", "master" );


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
   * <em>branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBranchCode();


  /** 
   * <em>branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchCodeIsSet();


  /** 
   * <em>branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchCodeIsModified();


  /** 
   * <em>pay_scheme_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPaySchemeId();


  /** 
   * <em>pay_scheme_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaySchemeIdIsSet();


  /** 
   * <em>pay_scheme_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaySchemeIdIsModified();


  /** 
   * <em>max_amount_daily</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMaxAmountDaily();


  /** 
   * <em>max_amount_daily</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaxAmountDailyIsNull();


  /** 
   * <em>max_amount_daily</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxAmountDailyIsSet();


  /** 
   * <em>max_amount_daily</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxAmountDailyIsModified();


  /** 
   * <em>max_amount_once</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMaxAmountOnce();


  /** 
   * <em>max_amount_once</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaxAmountOnceIsNull();


  /** 
   * <em>max_amount_once</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxAmountOnceIsSet();


  /** 
   * <em>max_amount_once</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxAmountOnceIsModified();


  /** 
   * <em>min_amount_once</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMinAmountOnce();


  /** 
   * <em>min_amount_once</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMinAmountOnceIsNull();


  /** 
   * <em>min_amount_once</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMinAmountOnceIsSet();


  /** 
   * <em>min_amount_once</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMinAmountOnceIsModified();


  /** 
   * <em>amount_unit</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountUnit();


  /** 
   * <em>amount_unit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountUnitIsNull();


  /** 
   * <em>amount_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountUnitIsSet();


  /** 
   * <em>amount_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountUnitIsModified();


  /** 
   * <em>max_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getMaxCount();


  /** 
   * <em>max_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaxCountIsNull();


  /** 
   * <em>max_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxCountIsSet();


  /** 
   * <em>max_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxCountIsModified();


  /** 
   * <em>shop_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getShopId();


  /** 
   * <em>shop_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShopIdIsSet();


  /** 
   * <em>shop_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShopIdIsModified();


  /** 
   * <em>access_key</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccessKey();


  /** 
   * <em>access_key</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccessKeyIsSet();


  /** 
   * <em>access_key</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccessKeyIsModified();


  /** 
   * <em>sonar_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSonarCode();


  /** 
   * <em>sonar_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarCodeIsSet();


  /** 
   * <em>sonar_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarCodeIsModified();


  /** 
   * <em>payment_method</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPaymentMethod();


  /** 
   * <em>payment_method</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentMethodIsSet();


  /** 
   * <em>payment_method</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentMethodIsModified();


  /** 
   * <em>complete_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCompleteFlag();


  /** 
   * <em>complete_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCompleteFlagIsSet();


  /** 
   * <em>complete_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCompleteFlagIsModified();


  /** 
   * <em>limit_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLimitTime();


  /** 
   * <em>limit_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitTimeIsSet();


  /** 
   * <em>limit_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitTimeIsModified();


  /** 
   * <em>suspension_start_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSuspensionStartTime();


  /** 
   * <em>suspension_start_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSuspensionStartTimeIsSet();


  /** 
   * <em>suspension_start_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSuspensionStartTimeIsModified();


  /** 
   * <em>suspension_end_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSuspensionEndTime();


  /** 
   * <em>suspension_end_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSuspensionEndTimeIsSet();


  /** 
   * <em>suspension_end_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSuspensionEndTimeIsModified();


}
@
