head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.38.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	InstBranchProductRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * InstBranchProductRowインタフェースは変更不可でリードオンリーである<em>inst_branch_product</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link InstBranchProductRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see InstBranchProductPK 
 */
public interface InstBranchProductRow extends Row {


  /** 
   * この{@@link InstBranchProductRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "inst_branch_product", "master" );


  /** 
   * <em>branch_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getBranchId();


  /** 
   * <em>branch_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchIdIsSet();


  /** 
   * <em>branch_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchIdIsModified();


  /** 
   * <em>commission_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCommissionProductCode();


  /** 
   * <em>commission_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionProductCodeIsSet();


  /** 
   * <em>commission_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionProductCodeIsModified();


  /** 
   * <em>commission_fee_cond_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCommissionFeeCondFlag();


  /** 
   * <em>commission_fee_cond_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionFeeCondFlagIsSet();


  /** 
   * <em>commission_fee_cond_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionFeeCondFlagIsModified();


  /** 
   * <em>estimate_price_calc_form</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getEstimatePriceCalcForm();


  /** 
   * <em>estimate_price_calc_form</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getEstimatePriceCalcFormIsNull();


  /** 
   * <em>estimate_price_calc_form</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEstimatePriceCalcFormIsSet();


  /** 
   * <em>estimate_price_calc_form</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEstimatePriceCalcFormIsModified();


  /** 
   * <em>premium_restraint_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPremiumRestraintRate();


  /** 
   * <em>premium_restraint_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPremiumRestraintRateIsNull();


  /** 
   * <em>premium_restraint_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPremiumRestraintRateIsSet();


  /** 
   * <em>premium_restraint_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPremiumRestraintRateIsModified();


  /** 
   * <em>discount_restraint_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDiscountRestraintRate();


  /** 
   * <em>discount_restraint_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDiscountRestraintRateIsNull();


  /** 
   * <em>discount_restraint_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDiscountRestraintRateIsSet();


  /** 
   * <em>discount_restraint_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDiscountRestraintRateIsModified();


  /** 
   * <em>cancel_price_restraint_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCancelPriceRestraintRate();


  /** 
   * <em>cancel_price_restraint_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCancelPriceRestraintRateIsNull();


  /** 
   * <em>cancel_price_restraint_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCancelPriceRestraintRateIsSet();


  /** 
   * <em>cancel_price_restraint_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCancelPriceRestraintRateIsModified();


  /** 
   * <em>commission_lump_adjust_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCommissionLumpAdjustAmount();


  /** 
   * <em>commission_lump_adjust_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCommissionLumpAdjustAmountIsNull();


  /** 
   * <em>commission_lump_adjust_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionLumpAdjustAmountIsSet();


  /** 
   * <em>commission_lump_adjust_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionLumpAdjustAmountIsModified();


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


}
@
