head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.58.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondProductRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbbd.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * BondProductRowインタフェースは変更不可でリードオンリーである<em>bond_product</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link BondProductRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BondProductPK 
 */
public interface BondProductRow extends Row {


  /** 
   * この{@@link BondProductRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "bond_product", "master" );


  /** 
   * <em>product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getProductId();


  /** 
   * <em>product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductIdIsSet();


  /** 
   * <em>product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductIdIsModified();


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
   * <em>product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProductCode();


  /** 
   * <em>product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductCodeIsSet();


  /** 
   * <em>product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductCodeIsModified();


  /** 
   * <em>product_issue_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProductIssueCode();


  /** 
   * <em>product_issue_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductIssueCodeIsSet();


  /** 
   * <em>product_issue_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductIssueCodeIsModified();


  /** 
   * <em>bond_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum getBondType();


  /** 
   * <em>bond_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondTypeIsSet();


  /** 
   * <em>bond_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondTypeIsModified();


  /** 
   * <em>host_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHostProductCode();


  /** 
   * <em>host_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHostProductCodeIsSet();


  /** 
   * <em>host_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHostProductCodeIsModified();


  /** 
   * <em>host_product_issue_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHostProductIssueCode();


  /** 
   * <em>host_product_issue_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHostProductIssueCodeIsSet();


  /** 
   * <em>host_product_issue_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHostProductIssueCodeIsModified();


  /** 
   * <em>issue_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getIssueDate();


  /** 
   * <em>issue_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIssueDateIsSet();


  /** 
   * <em>issue_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIssueDateIsModified();


  /** 
   * <em>issue_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getIssuePrice();


  /** 
   * <em>issue_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIssuePriceIsSet();


  /** 
   * <em>issue_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIssuePriceIsModified();


  /** 
   * <em>issue_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getIssueAmount();


  /** 
   * <em>issue_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getIssueAmountIsNull();


  /** 
   * <em>issue_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIssueAmountIsSet();


  /** 
   * <em>issue_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIssueAmountIsModified();


  /** 
   * <em>par_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getParValue();


  /** 
   * <em>par_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParValueIsSet();


  /** 
   * <em>par_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParValueIsModified();


  /** 
   * <em>maturity_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getMaturityDate();


  /** 
   * <em>maturity_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaturityDateIsSet();


  /** 
   * <em>maturity_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaturityDateIsModified();


  /** 
   * <em>redemption_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getRedemptionPrice();


  /** 
   * <em>redemption_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRedemptionPriceIsSet();


  /** 
   * <em>redemption_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRedemptionPriceIsModified();


  /** 
   * <em>coupon_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.xbbd.CouponTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.xbbd.CouponTypeEnum getCouponType();


  /** 
   * <em>coupon_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCouponTypeIsSet();


  /** 
   * <em>coupon_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCouponTypeIsModified();


  /** 
   * <em>coupon</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCoupon();


  /** 
   * <em>coupon</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCouponIsSet();


  /** 
   * <em>coupon</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCouponIsModified();


  /** 
   * <em>yearly_interest_payments</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getYearlyInterestPayments();


  /** 
   * <em>yearly_interest_payments</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getYearlyInterestPaymentsIsSet();


  /** 
   * <em>yearly_interest_payments</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getYearlyInterestPaymentsIsModified();


  /** 
   * <em>interest_payment_day_1st</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInterestPaymentDay1st();


  /** 
   * <em>interest_payment_day_1st</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestPaymentDay1stIsSet();


  /** 
   * <em>interest_payment_day_1st</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestPaymentDay1stIsModified();


  /** 
   * <em>interest_payment_day_2nd</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInterestPaymentDay2nd();


  /** 
   * <em>interest_payment_day_2nd</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestPaymentDay2ndIsSet();


  /** 
   * <em>interest_payment_day_2nd</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestPaymentDay2ndIsModified();


  /** 
   * <em>first_interest_payment_day</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getFirstInterestPaymentDay();


  /** 
   * <em>first_interest_payment_day</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstInterestPaymentDayIsSet();


  /** 
   * <em>first_interest_payment_day</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstInterestPaymentDayIsModified();


  /** 
   * <em>interest_payment_day</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getInterestPaymentDay();


  /** 
   * <em>interest_payment_day</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestPaymentDayIsSet();


  /** 
   * <em>interest_payment_day</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestPaymentDayIsModified();


  /** 
   * <em>interest_payment_day2</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getInterestPaymentDay2();


  /** 
   * <em>interest_payment_day2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestPaymentDay2IsSet();


  /** 
   * <em>interest_payment_day2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestPaymentDay2IsModified();


  /** 
   * <em>interest_payment_day3</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getInterestPaymentDay3();


  /** 
   * <em>interest_payment_day3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestPaymentDay3IsSet();


  /** 
   * <em>interest_payment_day3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestPaymentDay3IsModified();


  /** 
   * <em>interest_payment_day4</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getInterestPaymentDay4();


  /** 
   * <em>interest_payment_day4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestPaymentDay4IsSet();


  /** 
   * <em>interest_payment_day4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestPaymentDay4IsModified();


  /** 
   * <em>host_recruit_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getHostRecruitStartDate();


  /** 
   * <em>host_recruit_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHostRecruitStartDateIsSet();


  /** 
   * <em>host_recruit_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHostRecruitStartDateIsModified();


  /** 
   * <em>host_recruit_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getHostRecruitEndDate();


  /** 
   * <em>host_recruit_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHostRecruitEndDateIsSet();


  /** 
   * <em>host_recruit_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHostRecruitEndDateIsModified();


  /** 
   * <em>trade_handle_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradeHandleDiv();


  /** 
   * <em>trade_handle_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeHandleDivIsSet();


  /** 
   * <em>trade_handle_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeHandleDivIsModified();


  /** 
   * <em>trade_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getTradeStartDate();


  /** 
   * <em>trade_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeStartDateIsSet();


  /** 
   * <em>trade_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeStartDateIsModified();


  /** 
   * <em>trade_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getTradeEndDate();


  /** 
   * <em>trade_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeEndDateIsSet();


  /** 
   * <em>trade_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeEndDateIsModified();


  /** 
   * <em>recruit_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getRecruitStartDate();


  /** 
   * <em>recruit_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitStartDateIsSet();


  /** 
   * <em>recruit_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitStartDateIsModified();


  /** 
   * <em>recruit_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getRecruitEndDate();


  /** 
   * <em>recruit_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitEndDateIsSet();


  /** 
   * <em>recruit_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitEndDateIsModified();


  /** 
   * <em>trade_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradeType();


  /** 
   * <em>trade_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeTypeIsSet();


  /** 
   * <em>trade_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeTypeIsModified();


  /** 
   * <em>product_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProductName();


  /** 
   * <em>product_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductNameIsSet();


  /** 
   * <em>product_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductNameIsModified();


  /** 
   * <em>buy_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getBuyPrice();


  /** 
   * <em>buy_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBuyPriceIsNull();


  /** 
   * <em>buy_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyPriceIsSet();


  /** 
   * <em>buy_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyPriceIsModified();


  /** 
   * <em>sell_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSellPrice();


  /** 
   * <em>sell_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSellPriceIsNull();


  /** 
   * <em>sell_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellPriceIsSet();


  /** 
   * <em>sell_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellPriceIsModified();


  /** 
   * <em>trade_unit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTradeUnit();


  /** 
   * <em>trade_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeUnitIsSet();


  /** 
   * <em>trade_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeUnitIsModified();


  /** 
   * <em>min_face_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMinFaceAmount();


  /** 
   * <em>min_face_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMinFaceAmountIsSet();


  /** 
   * <em>min_face_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMinFaceAmountIsModified();


  /** 
   * <em>max_face_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMaxFaceAmount();


  /** 
   * <em>max_face_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaxFaceAmountIsNull();


  /** 
   * <em>max_face_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxFaceAmountIsSet();


  /** 
   * <em>max_face_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxFaceAmountIsModified();


  /** 
   * <em>cal_linked_market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCalLinkedMarketCode();


  /** 
   * <em>cal_linked_market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalLinkedMarketCodeIsSet();


  /** 
   * <em>cal_linked_market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalLinkedMarketCodeIsModified();


  /** 
   * <em>buy_delivery_date_shiftdays</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getBuyDeliveryDateShiftdays();


  /** 
   * <em>buy_delivery_date_shiftdays</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBuyDeliveryDateShiftdaysIsNull();


  /** 
   * <em>buy_delivery_date_shiftdays</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyDeliveryDateShiftdaysIsSet();


  /** 
   * <em>buy_delivery_date_shiftdays</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyDeliveryDateShiftdaysIsModified();


  /** 
   * <em>sell_delivery_date_shiftdays</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getSellDeliveryDateShiftdays();


  /** 
   * <em>sell_delivery_date_shiftdays</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSellDeliveryDateShiftdaysIsNull();


  /** 
   * <em>sell_delivery_date_shiftdays</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellDeliveryDateShiftdaysIsSet();


  /** 
   * <em>sell_delivery_date_shiftdays</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellDeliveryDateShiftdaysIsModified();


  /** 
   * <em>auto_exec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAutoExecDiv();


  /** 
   * <em>auto_exec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAutoExecDivIsSet();


  /** 
   * <em>auto_exec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAutoExecDivIsModified();


  /** 
   * <em>auto_exec_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAutoExecAmount();


  /** 
   * <em>auto_exec_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAutoExecAmountIsNull();


  /** 
   * <em>auto_exec_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAutoExecAmountIsSet();


  /** 
   * <em>auto_exec_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAutoExecAmountIsModified();


  /** 
   * <em>auto_exec_limit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAutoExecLimit();


  /** 
   * <em>auto_exec_limit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAutoExecLimitIsNull();


  /** 
   * <em>auto_exec_limit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAutoExecLimitIsSet();


  /** 
   * <em>auto_exec_limit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAutoExecLimitIsModified();


  /** 
   * <em>custodian_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCustodianCode();


  /** 
   * <em>custodian_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCustodianCodeIsSet();


  /** 
   * <em>custodian_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCustodianCodeIsModified();


  /** 
   * <em>host_product_name_1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHostProductName1();


  /** 
   * <em>host_product_name_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHostProductName1IsSet();


  /** 
   * <em>host_product_name_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHostProductName1IsModified();


  /** 
   * <em>host_product_name_2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHostProductName2();


  /** 
   * <em>host_product_name_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHostProductName2IsSet();


  /** 
   * <em>host_product_name_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHostProductName2IsModified();


  /** 
   * <em>host_short_product_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHostShortProductName();


  /** 
   * <em>host_short_product_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHostShortProductNameIsSet();


  /** 
   * <em>host_short_product_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHostShortProductNameIsModified();


  /** 
   * <em>isin_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIsinCode();


  /** 
   * <em>isin_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIsinCodeIsSet();


  /** 
   * <em>isin_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIsinCodeIsModified();


  /** 
   * <em>bond_categ_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBondCategCode();


  /** 
   * <em>bond_categ_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondCategCodeIsSet();


  /** 
   * <em>bond_categ_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondCategCodeIsModified();


  /** 
   * <em>currency_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCurrencyCode();


  /** 
   * <em>currency_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrencyCodeIsSet();


  /** 
   * <em>currency_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrencyCodeIsModified();


  /** 
   * <em>issue_market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIssueMarketCode();


  /** 
   * <em>issue_market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIssueMarketCodeIsSet();


  /** 
   * <em>issue_market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIssueMarketCodeIsModified();


  /** 
   * <em>issue_association_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIssueAssociationCode();


  /** 
   * <em>issue_association_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIssueAssociationCodeIsSet();


  /** 
   * <em>issue_association_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIssueAssociationCodeIsModified();


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
   * <em>accrued_interest_start_day</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getAccruedInterestStartDay();


  /** 
   * <em>accrued_interest_start_day</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccruedInterestStartDayIsSet();


  /** 
   * <em>accrued_interest_start_day</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccruedInterestStartDayIsModified();


  /** 
   * <em>special_payment_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSpecialPaymentDiv();


  /** 
   * <em>special_payment_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialPaymentDivIsSet();


  /** 
   * <em>special_payment_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialPaymentDivIsModified();


  /** 
   * <em>floating_interest_period_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFloatingInterestPeriodDiv();


  /** 
   * <em>floating_interest_period_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFloatingInterestPeriodDivIsSet();


  /** 
   * <em>floating_interest_period_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFloatingInterestPeriodDivIsModified();


  /** 
   * <em>floating_interest_period</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFloatingInterestPeriod();


  /** 
   * <em>floating_interest_period</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFloatingInterestPeriodIsSet();


  /** 
   * <em>floating_interest_period</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFloatingInterestPeriodIsModified();


  /** 
   * <em>floating_interest_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFloatingInterestType();


  /** 
   * <em>floating_interest_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFloatingInterestTypeIsSet();


  /** 
   * <em>floating_interest_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFloatingInterestTypeIsModified();


  /** 
   * <em>floating_interest_spread</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getFloatingInterestSpread();


  /** 
   * <em>floating_interest_spread</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFloatingInterestSpreadIsNull();


  /** 
   * <em>floating_interest_spread</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFloatingInterestSpreadIsSet();


  /** 
   * <em>floating_interest_spread</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFloatingInterestSpreadIsModified();


  /** 
   * <em>floating_min_coupon</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getFloatingMinCoupon();


  /** 
   * <em>floating_min_coupon</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFloatingMinCouponIsNull();


  /** 
   * <em>floating_min_coupon</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFloatingMinCouponIsSet();


  /** 
   * <em>floating_min_coupon</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFloatingMinCouponIsModified();


  /** 
   * <em>tax_free_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTaxFreeDiv();


  /** 
   * <em>tax_free_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxFreeDivIsSet();


  /** 
   * <em>tax_free_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxFreeDivIsModified();


  /** 
   * <em>s_and_p</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSAndP();


  /** 
   * <em>s_and_p</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSAndPIsSet();


  /** 
   * <em>s_and_p</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSAndPIsModified();


  /** 
   * <em>moodys</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMoodys();


  /** 
   * <em>moodys</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMoodysIsSet();


  /** 
   * <em>moodys</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMoodysIsModified();


  /** 
   * <em>cusip</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCusip();


  /** 
   * <em>cusip</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCusipIsSet();


  /** 
   * <em>cusip</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCusipIsModified();


  /** 
   * <em>buying_fx_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getBuyingFxRate();


  /** 
   * <em>buying_fx_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBuyingFxRateIsNull();


  /** 
   * <em>buying_fx_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyingFxRateIsSet();


  /** 
   * <em>buying_fx_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyingFxRateIsModified();


  /** 
   * <em>trading_time_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradingTimeCheckDiv();


  /** 
   * <em>trading_time_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingTimeCheckDivIsSet();


  /** 
   * <em>trading_time_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingTimeCheckDivIsModified();


  /** 
   * <em>mediator_commission_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMediatorCommissionRate();


  /** 
   * <em>mediator_commission_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMediatorCommissionRateIsNull();


  /** 
   * <em>mediator_commission_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMediatorCommissionRateIsSet();


  /** 
   * <em>mediator_commission_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMediatorCommissionRateIsModified();


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
   * <em>admin_last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getAdminLastUpdatedTimestamp();


  /** 
   * <em>admin_last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAdminLastUpdatedTimestampIsSet();


  /** 
   * <em>admin_last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAdminLastUpdatedTimestampIsModified();


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
   * <em>recruit_invitation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRecruitInvitationDiv();


  /** 
   * <em>recruit_invitation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitInvitationDivIsSet();


  /** 
   * <em>recruit_invitation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitInvitationDivIsModified();


  /** 
   * <em>recruit_accept_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRecruitAcceptDiv();


  /** 
   * <em>recruit_accept_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitAcceptDivIsSet();


  /** 
   * <em>recruit_accept_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitAcceptDivIsModified();


  /** 
   * <em>redemption_term</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getRedemptionTerm();


  /** 
   * <em>redemption_term</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRedemptionTermIsNull();


  /** 
   * <em>redemption_term</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRedemptionTermIsSet();


  /** 
   * <em>redemption_term</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRedemptionTermIsModified();


  /** 
   * <em>min_issue_coupon_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMinIssueCouponType();


  /** 
   * <em>min_issue_coupon_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMinIssueCouponTypeIsSet();


  /** 
   * <em>min_issue_coupon_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMinIssueCouponTypeIsModified();


  /** 
   * <em>delivery_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getDeliveryDate();


  /** 
   * <em>delivery_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeliveryDateIsSet();


  /** 
   * <em>delivery_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeliveryDateIsModified();


  /** 
   * <em>prospectus_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProspectusCheckDiv();


  /** 
   * <em>prospectus_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProspectusCheckDivIsSet();


  /** 
   * <em>prospectus_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProspectusCheckDivIsModified();


}
@
