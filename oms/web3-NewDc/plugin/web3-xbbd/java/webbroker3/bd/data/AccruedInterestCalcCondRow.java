head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	AccruedInterestCalcCondRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * AccruedInterestCalcCondRowインタフェースは変更不可でリードオンリーである<em>accrued_interest_calc_cond</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link AccruedInterestCalcCondRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccruedInterestCalcCondPK 
 */
public interface AccruedInterestCalcCondRow extends Row {


  /** 
   * この{@@link AccruedInterestCalcCondRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "accrued_interest_calc_cond", "master" );


  /** 
   * <em>accrued_interest_calc_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccruedInterestCalcType();


  /** 
   * <em>accrued_interest_calc_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccruedInterestCalcTypeIsSet();


  /** 
   * <em>accrued_interest_calc_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccruedInterestCalcTypeIsModified();


  /** 
   * <em>calc_type_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCalcTypeName();


  /** 
   * <em>calc_type_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcTypeNameIsSet();


  /** 
   * <em>calc_type_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcTypeNameIsModified();


  /** 
   * <em>base_date_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBaseDateDiv();


  /** 
   * <em>base_date_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBaseDateDivIsSet();


  /** 
   * <em>base_date_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBaseDateDivIsModified();


  /** 
   * <em>base_days_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBaseDaysDiv();


  /** 
   * <em>base_days_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBaseDaysDivIsSet();


  /** 
   * <em>base_days_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBaseDaysDivIsModified();


  /** 
   * <em>elapsed_days_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getElapsedDaysDiv();


  /** 
   * <em>elapsed_days_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getElapsedDaysDivIsSet();


  /** 
   * <em>elapsed_days_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getElapsedDaysDivIsModified();


  /** 
   * <em>non_elapsed_days_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNonElapsedDaysDiv();


  /** 
   * <em>non_elapsed_days_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNonElapsedDaysDivIsSet();


  /** 
   * <em>non_elapsed_days_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNonElapsedDaysDivIsModified();


  /** 
   * <em>taxation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTaxationDiv();


  /** 
   * <em>taxation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxationDivIsSet();


  /** 
   * <em>taxation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxationDivIsModified();


  /** 
   * <em>calc_base_form</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCalcBaseForm();


  /** 
   * <em>calc_base_form</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcBaseFormIsSet();


  /** 
   * <em>calc_base_form</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcBaseFormIsModified();


  /** 
   * <em>unit_price_scale</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getUnitPriceScale();


  /** 
   * <em>unit_price_scale</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnitPriceScaleIsNull();


  /** 
   * <em>unit_price_scale</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnitPriceScaleIsSet();


  /** 
   * <em>unit_price_scale</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnitPriceScaleIsModified();


  /** 
   * <em>unit_round_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getUnitRoundDiv();


  /** 
   * <em>unit_round_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnitRoundDivIsSet();


  /** 
   * <em>unit_round_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnitRoundDivIsModified();


  /** 
   * <em>amount_round_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAmountRoundDiv();


  /** 
   * <em>amount_round_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountRoundDivIsSet();


  /** 
   * <em>amount_round_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountRoundDivIsModified();


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
