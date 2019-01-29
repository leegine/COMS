head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFundProductSonarRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * MutualFundProductSonarRowインタフェースは変更不可でリードオンリーである<em>mutual_fund_product_sonar</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link MutualFundProductSonarRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundProductSonarPK 
 */
public interface MutualFundProductSonarRow extends Row {


  /** 
   * この{@@link MutualFundProductSonarRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "mutual_fund_product_sonar", "master" );


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
   * <em>product_name_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProductNameKana();


  /** 
   * <em>product_name_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductNameKanaIsSet();


  /** 
   * <em>product_name_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductNameKanaIsModified();


  /** 
   * <em>product_name_kanji</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProductNameKanji();


  /** 
   * <em>product_name_kanji</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductNameKanjiIsSet();


  /** 
   * <em>product_name_kanji</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductNameKanjiIsModified();


  /** 
   * <em>product_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProductDiv();


  /** 
   * <em>product_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductDivIsSet();


  /** 
   * <em>product_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductDivIsModified();


  /** 
   * <em>work_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getWorkDate();


  /** 
   * <em>work_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWorkDateIsSet();


  /** 
   * <em>work_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWorkDateIsModified();


  /** 
   * <em>effect_generating_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getEffectGeneratingDate();


  /** 
   * <em>effect_generating_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEffectGeneratingDateIsSet();


  /** 
   * <em>effect_generating_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEffectGeneratingDateIsModified();


  /** 
   * <em>invalid_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getInvalidDate();


  /** 
   * <em>invalid_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInvalidDateIsSet();


  /** 
   * <em>invalid_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInvalidDateIsModified();


  /** 
   * <em>closing_date1</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getClosingDate1();


  /** 
   * <em>closing_date1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClosingDate1IsSet();


  /** 
   * <em>closing_date1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClosingDate1IsModified();


  /** 
   * <em>closing_date2</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getClosingDate2();


  /** 
   * <em>closing_date2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClosingDate2IsSet();


  /** 
   * <em>closing_date2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClosingDate2IsModified();


  /** 
   * <em>redemption_extend_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRedemptionExtendDiv();


  /** 
   * <em>redemption_extend_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRedemptionExtendDivIsSet();


  /** 
   * <em>redemption_extend_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRedemptionExtendDivIsModified();


  /** 
   * <em>redemption_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getRedemptionDate();


  /** 
   * <em>redemption_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRedemptionDateIsSet();


  /** 
   * <em>redemption_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRedemptionDateIsModified();


  /** 
   * <em>first_recruitment_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getFirstRecruitmentDate();


  /** 
   * <em>first_recruitment_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstRecruitmentDateIsSet();


  /** 
   * <em>first_recruitment_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFirstRecruitmentDateIsModified();


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
   * <em>recruit_price</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getRecruitPrice();


  /** 
   * <em>recruit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRecruitPriceIsNull();


  /** 
   * <em>recruit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitPriceIsSet();


  /** 
   * <em>recruit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitPriceIsModified();


  /** 
   * <em>payment_start_date1</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getPaymentStartDate1();


  /** 
   * <em>payment_start_date1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentStartDate1IsSet();


  /** 
   * <em>payment_start_date1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentStartDate1IsModified();


  /** 
   * <em>payment_start_date2</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getPaymentStartDate2();


  /** 
   * <em>payment_start_date2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentStartDate2IsSet();


  /** 
   * <em>payment_start_date2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentStartDate2IsModified();


  /** 
   * <em>storage_stop_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStorageStopFlag();


  /** 
   * <em>storage_stop_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStorageStopFlagIsSet();


  /** 
   * <em>storage_stop_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStorageStopFlagIsModified();


  /** 
   * <em>trade_stop_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradeStopFlag();


  /** 
   * <em>trade_stop_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeStopFlagIsSet();


  /** 
   * <em>trade_stop_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeStopFlagIsModified();


  /** 
   * <em>obliterate_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getObliterateType();


  /** 
   * <em>obliterate_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getObliterateTypeIsSet();


  /** 
   * <em>obliterate_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getObliterateTypeIsModified();


  /** 
   * <em>corpus_price</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getCorpusPrice();


  /** 
   * <em>corpus_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCorpusPriceIsNull();


  /** 
   * <em>corpus_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCorpusPriceIsSet();


  /** 
   * <em>corpus_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCorpusPriceIsModified();


  /** 
   * <em>open_close_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOpenCloseType();


  /** 
   * <em>open_close_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOpenCloseTypeIsSet();


  /** 
   * <em>open_close_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOpenCloseTypeIsModified();


  /** 
   * <em>dayreport_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDayreportProductCode();


  /** 
   * <em>dayreport_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayreportProductCodeIsSet();


  /** 
   * <em>dayreport_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayreportProductCodeIsModified();


  /** 
   * <em>recruit_sales</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRecruitSales();


  /** 
   * <em>recruit_sales</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitSalesIsSet();


  /** 
   * <em>recruit_sales</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitSalesIsModified();


  /** 
   * <em>stock_type_bond_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStockTypeBondType();


  /** 
   * <em>stock_type_bond_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStockTypeBondTypeIsSet();


  /** 
   * <em>stock_type_bond_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStockTypeBondTypeIsModified();


  /** 
   * <em>contract_institution_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getContractInstitutionType();


  /** 
   * <em>contract_institution_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInstitutionTypeIsSet();


  /** 
   * <em>contract_institution_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInstitutionTypeIsModified();


  /** 
   * <em>purchs_deduction_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getPurchsDeductionStartDate();


  /** 
   * <em>purchs_deduction_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPurchsDeductionStartDateIsSet();


  /** 
   * <em>purchs_deduction_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPurchsDeductionStartDateIsModified();


  /** 
   * <em>spot_closing_date1</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getSpotClosingDate1();


  /** 
   * <em>spot_closing_date1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpotClosingDate1IsSet();


  /** 
   * <em>spot_closing_date1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpotClosingDate1IsModified();


  /** 
   * <em>spot_closing_date2</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getSpotClosingDate2();


  /** 
   * <em>spot_closing_date2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpotClosingDate2IsSet();


  /** 
   * <em>spot_closing_date2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpotClosingDate2IsModified();


  /** 
   * <em>calc_unit</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getCalcUnit();


  /** 
   * <em>calc_unit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCalcUnitIsNull();


  /** 
   * <em>calc_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcUnitIsSet();


  /** 
   * <em>calc_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcUnitIsModified();


  /** 
   * <em>biz_asset_product_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBizAssetProductType();


  /** 
   * <em>biz_asset_product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBizAssetProductTypeIsSet();


  /** 
   * <em>biz_asset_product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBizAssetProductTypeIsModified();


  /** 
   * <em>biz_asset_evaluate_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getBizAssetEvaluatePrice();


  /** 
   * <em>biz_asset_evaluate_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBizAssetEvaluatePriceIsNull();


  /** 
   * <em>biz_asset_evaluate_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBizAssetEvaluatePriceIsSet();


  /** 
   * <em>biz_asset_evaluate_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBizAssetEvaluatePriceIsModified();


  /** 
   * <em>profit_balance_confirm_data</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getProfitBalanceConfirmData();


  /** 
   * <em>profit_balance_confirm_data</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProfitBalanceConfirmDataIsSet();


  /** 
   * <em>profit_balance_confirm_data</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProfitBalanceConfirmDataIsModified();


  /** 
   * <em>profit_term_quantity</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProfitTermQuantity();


  /** 
   * <em>profit_term_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProfitTermQuantityIsSet();


  /** 
   * <em>profit_term_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProfitTermQuantityIsModified();


  /** 
   * <em>general_profit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getGeneralProfitPrice();


  /** 
   * <em>general_profit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getGeneralProfitPriceIsNull();


  /** 
   * <em>general_profit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGeneralProfitPriceIsSet();


  /** 
   * <em>general_profit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGeneralProfitPriceIsModified();


  /** 
   * <em>spcprofit_distribution_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSpcprofitDistributionPrice();


  /** 
   * <em>spcprofit_distribution_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSpcprofitDistributionPriceIsNull();


  /** 
   * <em>spcprofit_distribution_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpcprofitDistributionPriceIsSet();


  /** 
   * <em>spcprofit_distribution_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpcprofitDistributionPriceIsModified();


  /** 
   * <em>taxinlots_aftertax_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTaxinlotsAftertaxPrice();


  /** 
   * <em>taxinlots_aftertax_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTaxinlotsAftertaxPriceIsNull();


  /** 
   * <em>taxinlots_aftertax_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxinlotsAftertaxPriceIsSet();


  /** 
   * <em>taxinlots_aftertax_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxinlotsAftertaxPriceIsModified();


  /** 
   * <em>taxaggregate_aftertax_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTaxaggregateAftertaxPrice();


  /** 
   * <em>taxaggregate_aftertax_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTaxaggregateAftertaxPriceIsNull();


  /** 
   * <em>taxaggregate_aftertax_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxaggregateAftertaxPriceIsSet();


  /** 
   * <em>taxaggregate_aftertax_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxaggregateAftertaxPriceIsModified();


  /** 
   * <em>pay_start_date_advanced_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPayStartDateAdvancedDiv();


  /** 
   * <em>pay_start_date_advanced_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPayStartDateAdvancedDivIsSet();


  /** 
   * <em>pay_start_date_advanced_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPayStartDateAdvancedDivIsModified();


  /** 
   * <em>method_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMethodType();


  /** 
   * <em>method_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMethodTypeIsSet();


  /** 
   * <em>method_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMethodTypeIsModified();


  /** 
   * <em>setting_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getSettingDate();


  /** 
   * <em>setting_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSettingDateIsSet();


  /** 
   * <em>setting_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSettingDateIsModified();


  /** 
   * <em>sell_forbidden_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getSellForbiddenDate();


  /** 
   * <em>sell_forbidden_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellForbiddenDateIsSet();


  /** 
   * <em>sell_forbidden_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellForbiddenDateIsModified();


  /** 
   * <em>adding_forbidden_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getAddingForbiddenDate();


  /** 
   * <em>adding_forbidden_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddingForbiddenDateIsSet();


  /** 
   * <em>adding_forbidden_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddingForbiddenDateIsModified();


  /** 
   * <em>profit_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getProfitStartDate();


  /** 
   * <em>profit_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProfitStartDateIsSet();


  /** 
   * <em>profit_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProfitStartDateIsModified();


  /** 
   * <em>best_exception_product_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBestExceptionProductFlag();


  /** 
   * <em>best_exception_product_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBestExceptionProductFlagIsSet();


  /** 
   * <em>best_exception_product_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBestExceptionProductFlagIsModified();


  /** 
   * <em>currency_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCurrencyType();


  /** 
   * <em>currency_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrencyTypeIsSet();


  /** 
   * <em>currency_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrencyTypeIsModified();


  /** 
   * <em>profit_distribution_regdate</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getProfitDistributionRegdate();


  /** 
   * <em>profit_distribution_regdate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProfitDistributionRegdateIsSet();


  /** 
   * <em>profit_distribution_regdate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProfitDistributionRegdateIsModified();


  /** 
   * <em>consign_contact_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getConsignContactProductCode();


  /** 
   * <em>consign_contact_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConsignContactProductCodeIsSet();


  /** 
   * <em>consign_contact_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConsignContactProductCodeIsModified();


  /** 
   * <em>mutualassoc_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMutualassocProductCode();


  /** 
   * <em>mutualassoc_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMutualassocProductCodeIsSet();


  /** 
   * <em>mutualassoc_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMutualassocProductCodeIsModified();


  /** 
   * <em>trust_bank_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTrustBankCode();


  /** 
   * <em>trust_bank_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrustBankCodeIsSet();


  /** 
   * <em>trust_bank_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrustBankCodeIsModified();


  /** 
   * <em>consign_institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getConsignInstitutionCode();


  /** 
   * <em>consign_institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConsignInstitutionCodeIsSet();


  /** 
   * <em>consign_institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConsignInstitutionCodeIsModified();


  /** 
   * <em>average_trust_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAverageTrustPrice();


  /** 
   * <em>average_trust_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAverageTrustPriceIsNull();


  /** 
   * <em>average_trust_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAverageTrustPriceIsSet();


  /** 
   * <em>average_trust_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAverageTrustPriceIsModified();


  /** 
   * <em>same_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSameCheckDiv();


  /** 
   * <em>same_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSameCheckDivIsSet();


  /** 
   * <em>same_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSameCheckDivIsModified();


  /** 
   * <em>same_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSameDiv();


  /** 
   * <em>same_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSameDivIsSet();


  /** 
   * <em>same_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSameDivIsModified();


  /** 
   * <em>recruit_short_swt_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRecruitShortSwtCheckDiv();


  /** 
   * <em>recruit_short_swt_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitShortSwtCheckDivIsSet();


  /** 
   * <em>recruit_short_swt_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitShortSwtCheckDivIsModified();


  /** 
   * <em>buy_short_swt_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuyShortSwtCheckDiv();


  /** 
   * <em>buy_short_swt_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyShortSwtCheckDivIsSet();


  /** 
   * <em>buy_short_swt_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyShortSwtCheckDivIsModified();


  /** 
   * <em>sell_short_swt_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSellShortSwtCheckDiv();


  /** 
   * <em>sell_short_swt_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellShortSwtCheckDivIsSet();


  /** 
   * <em>sell_short_swt_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellShortSwtCheckDivIsModified();


  /** 
   * <em>recruit_start_stop</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRecruitStartStop();


  /** 
   * <em>recruit_start_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitStartStopIsSet();


  /** 
   * <em>recruit_start_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitStartStopIsModified();


  /** 
   * <em>collateral_qualified_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCollateralQualifiedDiv();


  /** 
   * <em>collateral_qualified_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCollateralQualifiedDivIsSet();


  /** 
   * <em>collateral_qualified_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCollateralQualifiedDivIsModified();


  /** 
   * <em>collateral_evaluation</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCollateralEvaluation();


  /** 
   * <em>collateral_evaluation</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCollateralEvaluationIsNull();


  /** 
   * <em>collateral_evaluation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCollateralEvaluationIsSet();


  /** 
   * <em>collateral_evaluation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCollateralEvaluationIsModified();


  /** 
   * <em>collateral_ratio</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCollateralRatio();


  /** 
   * <em>collateral_ratio</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCollateralRatioIsNull();


  /** 
   * <em>collateral_ratio</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCollateralRatioIsSet();


  /** 
   * <em>collateral_ratio</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCollateralRatioIsModified();


}
@
