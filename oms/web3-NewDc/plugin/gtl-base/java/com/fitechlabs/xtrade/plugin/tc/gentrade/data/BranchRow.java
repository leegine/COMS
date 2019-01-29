head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.40.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	BranchRow.java;


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
 * BranchRowインタフェースは変更不可でリードオンリーである<em>branch</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link BranchRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BranchPK 
 */
public interface BranchRow extends Row {


  /** 
   * この{@@link BranchRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "branch", "master" );


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
   * <em>branch_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBranchName();


  /** 
   * <em>branch_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchNameIsSet();


  /** 
   * <em>branch_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchNameIsModified();


  /** 
   * <em>branch_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBranchNameAlt1();


  /** 
   * <em>branch_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchNameAlt1IsSet();


  /** 
   * <em>branch_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchNameAlt1IsModified();


  /** 
   * <em>branch_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum getBranchType();


  /** 
   * <em>branch_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchTypeIsSet();


  /** 
   * <em>branch_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchTypeIsModified();


  /** 
   * <em>max_handling_price_ind</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMaxHandlingPriceInd();


  /** 
   * <em>max_handling_price_ind</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaxHandlingPriceIndIsNull();


  /** 
   * <em>max_handling_price_ind</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxHandlingPriceIndIsSet();


  /** 
   * <em>max_handling_price_ind</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxHandlingPriceIndIsModified();


  /** 
   * <em>max_handling_price_corp</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMaxHandlingPriceCorp();


  /** 
   * <em>max_handling_price_corp</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaxHandlingPriceCorpIsNull();


  /** 
   * <em>max_handling_price_corp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxHandlingPriceCorpIsSet();


  /** 
   * <em>max_handling_price_corp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxHandlingPriceCorpIsModified();


  /** 
   * <em>max_handling_price_ind_option</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMaxHandlingPriceIndOption();


  /** 
   * <em>max_handling_price_ind_option</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaxHandlingPriceIndOptionIsNull();


  /** 
   * <em>max_handling_price_ind_option</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxHandlingPriceIndOptionIsSet();


  /** 
   * <em>max_handling_price_ind_option</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxHandlingPriceIndOptionIsModified();


  /** 
   * <em>max_handling_price_corp_option</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMaxHandlingPriceCorpOption();


  /** 
   * <em>max_handling_price_corp_option</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaxHandlingPriceCorpOptionIsNull();


  /** 
   * <em>max_handling_price_corp_option</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxHandlingPriceCorpOptionIsSet();


  /** 
   * <em>max_handling_price_corp_option</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxHandlingPriceCorpOptionIsModified();


  /** 
   * <em>max_handling_price_ind_future</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMaxHandlingPriceIndFuture();


  /** 
   * <em>max_handling_price_ind_future</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaxHandlingPriceIndFutureIsNull();


  /** 
   * <em>max_handling_price_ind_future</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxHandlingPriceIndFutureIsSet();


  /** 
   * <em>max_handling_price_ind_future</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxHandlingPriceIndFutureIsModified();


  /** 
   * <em>max_handling_price_corp_future</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMaxHandlingPriceCorpFuture();


  /** 
   * <em>max_handling_price_corp_future</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaxHandlingPriceCorpFutureIsNull();


  /** 
   * <em>max_handling_price_corp_future</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxHandlingPriceCorpFutureIsSet();


  /** 
   * <em>max_handling_price_corp_future</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxHandlingPriceCorpFutureIsModified();


  /** 
   * <em>max_cont_price_all_ind</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMaxContPriceAllInd();


  /** 
   * <em>max_cont_price_all_ind</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaxContPriceAllIndIsNull();


  /** 
   * <em>max_cont_price_all_ind</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxContPriceAllIndIsSet();


  /** 
   * <em>max_cont_price_all_ind</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxContPriceAllIndIsModified();


  /** 
   * <em>max_cont_price_all_corp</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMaxContPriceAllCorp();


  /** 
   * <em>max_cont_price_all_corp</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaxContPriceAllCorpIsNull();


  /** 
   * <em>max_cont_price_all_corp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxContPriceAllCorpIsSet();


  /** 
   * <em>max_cont_price_all_corp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxContPriceAllCorpIsModified();


  /** 
   * <em>max_cont_price_product_ind</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMaxContPriceProductInd();


  /** 
   * <em>max_cont_price_product_ind</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaxContPriceProductIndIsNull();


  /** 
   * <em>max_cont_price_product_ind</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxContPriceProductIndIsSet();


  /** 
   * <em>max_cont_price_product_ind</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxContPriceProductIndIsModified();


  /** 
   * <em>max_cont_price_product_corp</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMaxContPriceProductCorp();


  /** 
   * <em>max_cont_price_product_corp</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaxContPriceProductCorpIsNull();


  /** 
   * <em>max_cont_price_product_corp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxContPriceProductCorpIsSet();


  /** 
   * <em>max_cont_price_product_corp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxContPriceProductCorpIsModified();


  /** 
   * <em>max_cont_price_1day_ind</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMaxContPrice1dayInd();


  /** 
   * <em>max_cont_price_1day_ind</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaxContPrice1dayIndIsNull();


  /** 
   * <em>max_cont_price_1day_ind</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxContPrice1dayIndIsSet();


  /** 
   * <em>max_cont_price_1day_ind</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxContPrice1dayIndIsModified();


  /** 
   * <em>max_cont_price_1day_corp</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMaxContPrice1dayCorp();


  /** 
   * <em>max_cont_price_1day_corp</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMaxContPrice1dayCorpIsNull();


  /** 
   * <em>max_cont_price_1day_corp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxContPrice1dayCorpIsSet();


  /** 
   * <em>max_cont_price_1day_corp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxContPrice1dayCorpIsModified();


  /** 
   * <em>handling_market_make</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getHandlingMarketMake();


  /** 
   * <em>handling_market_make</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getHandlingMarketMakeIsNull();


  /** 
   * <em>handling_market_make</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHandlingMarketMakeIsSet();


  /** 
   * <em>handling_market_make</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHandlingMarketMakeIsModified();


  /** 
   * <em>handling_not_loan_trans_stock</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getHandlingNotLoanTransStock();


  /** 
   * <em>handling_not_loan_trans_stock</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getHandlingNotLoanTransStockIsNull();


  /** 
   * <em>handling_not_loan_trans_stock</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHandlingNotLoanTransStockIsSet();


  /** 
   * <em>handling_not_loan_trans_stock</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHandlingNotLoanTransStockIsModified();


  /** 
   * <em>fin_sales_law_execution</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFinSalesLawExecution();


  /** 
   * <em>fin_sales_law_execution</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinSalesLawExecutionIsSet();


  /** 
   * <em>fin_sales_law_execution</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinSalesLawExecutionIsModified();


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
   * <em>login_stop_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLoginStopDiv();


  /** 
   * <em>login_stop_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLoginStopDivIsSet();


  /** 
   * <em>login_stop_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLoginStopDivIsModified();


  /** 
   * <em>admin_type_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAdminTypeId();


  /** 
   * <em>admin_type_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAdminTypeIdIsNull();


  /** 
   * <em>admin_type_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAdminTypeIdIsSet();


  /** 
   * <em>admin_type_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAdminTypeIdIsModified();


  /** 
   * <em>trader_type_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getTraderTypeId();


  /** 
   * <em>trader_type_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTraderTypeIdIsNull();


  /** 
   * <em>trader_type_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTraderTypeIdIsSet();


  /** 
   * <em>trader_type_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTraderTypeIdIsModified();


  /** 
   * <em>account_type_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAccountTypeId();


  /** 
   * <em>account_type_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAccountTypeIdIsNull();


  /** 
   * <em>account_type_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountTypeIdIsSet();


  /** 
   * <em>account_type_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountTypeIdIsModified();


  /** 
   * <em>account_group_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAccountGroupId();


  /** 
   * <em>account_group_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAccountGroupIdIsNull();


  /** 
   * <em>account_group_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountGroupIdIsSet();


  /** 
   * <em>account_group_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountGroupIdIsModified();


  /** 
   * <em>account_code_min</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getAccountCodeMin();


  /** 
   * <em>account_code_min</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAccountCodeMinIsNull();


  /** 
   * <em>account_code_min</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeMinIsSet();


  /** 
   * <em>account_code_min</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeMinIsModified();


  /** 
   * <em>account_code_max</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getAccountCodeMax();


  /** 
   * <em>account_code_max</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAccountCodeMaxIsNull();


  /** 
   * <em>account_code_max</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeMaxIsSet();


  /** 
   * <em>account_code_max</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeMaxIsModified();


  /** 
   * <em>account_code_check_mode</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountCodeCheckMode();


  /** 
   * <em>account_code_check_mode</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeCheckModeIsSet();


  /** 
   * <em>account_code_check_mode</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeCheckModeIsModified();


  /** 
   * <em>insider_default_regist_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInsiderDefaultRegistDiv();


  /** 
   * <em>insider_default_regist_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsiderDefaultRegistDivIsSet();


  /** 
   * <em>insider_default_regist_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsiderDefaultRegistDivIsModified();


  /** 
   * <em>margin_sys_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginSysDiv();


  /** 
   * <em>margin_sys_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSysDivIsSet();


  /** 
   * <em>margin_sys_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSysDivIsModified();


  /** 
   * <em>margin_gen_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginGenDiv();


  /** 
   * <em>margin_gen_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginGenDivIsSet();


  /** 
   * <em>margin_gen_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginGenDivIsModified();


  /** 
   * <em>fstk_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFstkDiv();


  /** 
   * <em>fstk_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFstkDivIsSet();


  /** 
   * <em>fstk_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFstkDivIsModified();


  /** 
   * <em>mstk_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMstkDiv();


  /** 
   * <em>mstk_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMstkDivIsSet();


  /** 
   * <em>mstk_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMstkDivIsModified();


  /** 
   * <em>option_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOptionDiv();


  /** 
   * <em>option_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOptionDivIsSet();


  /** 
   * <em>option_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOptionDivIsModified();


  /** 
   * <em>future_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFutureDiv();


  /** 
   * <em>future_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFutureDivIsSet();


  /** 
   * <em>future_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFutureDivIsModified();


  /** 
   * <em>mf_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMfDiv();


  /** 
   * <em>mf_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMfDivIsSet();


  /** 
   * <em>mf_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMfDivIsModified();


  /** 
   * <em>ruito_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRuitoDiv();


  /** 
   * <em>ruito_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRuitoDivIsSet();


  /** 
   * <em>ruito_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRuitoDivIsModified();


  /** 
   * <em>qualified_investor_confirm_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getQualifiedInvestorConfirmDiv();


  /** 
   * <em>qualified_investor_confirm_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQualifiedInvestorConfirmDivIsSet();


  /** 
   * <em>qualified_investor_confirm_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQualifiedInvestorConfirmDivIsModified();


  /** 
   * <em>margin_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginDepositRate();


  /** 
   * <em>margin_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginDepositRateIsNull();


  /** 
   * <em>margin_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRateIsSet();


  /** 
   * <em>margin_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginDepositRateIsModified();


  /** 
   * <em>cash_margin_deposit_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCashMarginDepositRate();


  /** 
   * <em>cash_margin_deposit_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCashMarginDepositRateIsNull();


  /** 
   * <em>cash_margin_deposit_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashMarginDepositRateIsSet();


  /** 
   * <em>cash_margin_deposit_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCashMarginDepositRateIsModified();


  /** 
   * <em>margin_maintenance_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginMaintenanceRate();


  /** 
   * <em>margin_maintenance_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginMaintenanceRateIsNull();


  /** 
   * <em>margin_maintenance_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginMaintenanceRateIsSet();


  /** 
   * <em>margin_maintenance_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginMaintenanceRateIsModified();


  /** 
   * <em>min_margin_deposit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMinMarginDeposit();


  /** 
   * <em>min_margin_deposit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMinMarginDepositIsNull();


  /** 
   * <em>min_margin_deposit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMinMarginDepositIsSet();


  /** 
   * <em>min_margin_deposit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMinMarginDepositIsModified();


  /** 
   * <em>min_ifo_deposit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMinIfoDeposit();


  /** 
   * <em>min_ifo_deposit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMinIfoDepositIsNull();


  /** 
   * <em>min_ifo_deposit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMinIfoDepositIsSet();


  /** 
   * <em>min_ifo_deposit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMinIfoDepositIsModified();


  /** 
   * <em>calc_substitute_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCalcSubstituteRate();


  /** 
   * <em>calc_substitute_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCalcSubstituteRateIsNull();


  /** 
   * <em>calc_substitute_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcSubstituteRateIsSet();


  /** 
   * <em>calc_substitute_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcSubstituteRateIsModified();


  /** 
   * <em>margin_sec_check_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMarginSecCheckRate();


  /** 
   * <em>margin_sec_check_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginSecCheckRateIsNull();


  /** 
   * <em>margin_sec_check_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSecCheckRateIsSet();


  /** 
   * <em>margin_sec_check_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSecCheckRateIsModified();


  /** 
   * <em>short_margin_restrain_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getShortMarginRestrainDiv();


  /** 
   * <em>short_margin_restrain_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortMarginRestrainDivIsSet();


  /** 
   * <em>short_margin_restrain_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortMarginRestrainDivIsModified();


  /** 
   * <em>short_margin_restrain_unit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getShortMarginRestrainUnit();


  /** 
   * <em>short_margin_restrain_unit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getShortMarginRestrainUnitIsNull();


  /** 
   * <em>short_margin_restrain_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortMarginRestrainUnitIsSet();


  /** 
   * <em>short_margin_restrain_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortMarginRestrainUnitIsModified();


  /** 
   * <em>short_sell_order_valid_minute</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getShortSellOrderValidMinute();


  /** 
   * <em>short_sell_order_valid_minute</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getShortSellOrderValidMinuteIsNull();


  /** 
   * <em>short_sell_order_valid_minute</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortSellOrderValidMinuteIsSet();


  /** 
   * <em>short_sell_order_valid_minute</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortSellOrderValidMinuteIsModified();


  /** 
   * <em>margin_sec_transfer_max_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getMarginSecTransferMaxCount();


  /** 
   * <em>margin_sec_transfer_max_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarginSecTransferMaxCountIsNull();


  /** 
   * <em>margin_sec_transfer_max_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSecTransferMaxCountIsSet();


  /** 
   * <em>margin_sec_transfer_max_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSecTransferMaxCountIsModified();


  /** 
   * <em>close_worng_equity_margin</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getCloseWorngEquityMargin();


  /** 
   * <em>close_worng_equity_margin</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCloseWorngEquityMarginIsNull();


  /** 
   * <em>close_worng_equity_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCloseWorngEquityMarginIsSet();


  /** 
   * <em>close_worng_equity_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCloseWorngEquityMarginIsModified();


  /** 
   * <em>close_worng_option</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getCloseWorngOption();


  /** 
   * <em>close_worng_option</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCloseWorngOptionIsNull();


  /** 
   * <em>close_worng_option</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCloseWorngOptionIsSet();


  /** 
   * <em>close_worng_option</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCloseWorngOptionIsModified();


  /** 
   * <em>close_worng_sys_future</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getCloseWorngSysFuture();


  /** 
   * <em>close_worng_sys_future</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCloseWorngSysFutureIsNull();


  /** 
   * <em>close_worng_sys_future</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCloseWorngSysFutureIsSet();


  /** 
   * <em>close_worng_sys_future</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCloseWorngSysFutureIsModified();


  /** 
   * <em>daily_interest_adjust_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDailyInterestAdjustAmount();


  /** 
   * <em>daily_interest_adjust_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDailyInterestAdjustAmountIsNull();


  /** 
   * <em>daily_interest_adjust_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDailyInterestAdjustAmountIsSet();


  /** 
   * <em>daily_interest_adjust_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDailyInterestAdjustAmountIsModified();


  /** 
   * <em>pay_auto_calc_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPayAutoCalcDiv();


  /** 
   * <em>pay_auto_calc_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPayAutoCalcDivIsSet();


  /** 
   * <em>pay_auto_calc_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPayAutoCalcDivIsModified();


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
   * <em>max_handling_price_close_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMaxHandlingPriceCloseDiv();


  /** 
   * <em>max_handling_price_close_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxHandlingPriceCloseDivIsSet();


  /** 
   * <em>max_handling_price_close_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMaxHandlingPriceCloseDivIsModified();


  /** 
   * <em>off_floor_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOffFloorDiv();


  /** 
   * <em>off_floor_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOffFloorDivIsSet();


  /** 
   * <em>off_floor_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOffFloorDivIsModified();


  /** 
   * <em>close_worng_feq</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getCloseWorngFeq();


  /** 
   * <em>close_worng_feq</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCloseWorngFeqIsNull();


  /** 
   * <em>close_worng_feq</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCloseWorngFeqIsSet();


  /** 
   * <em>close_worng_feq</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCloseWorngFeqIsModified();


}
@
