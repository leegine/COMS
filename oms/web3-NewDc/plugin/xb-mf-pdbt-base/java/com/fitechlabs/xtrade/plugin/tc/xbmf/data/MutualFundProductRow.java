head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.12.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5d04d8867b00a28;
filename	MutualFundProductRow.java;


desc
@@


1.1
log
@lo
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbmf.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * MutualFundProductRowインタフェースは変更不可でリードオンリーである<em>mutual_fund_product</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link MutualFundProductRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundProductPK 
 */
public interface MutualFundProductRow extends Row {


  /** 
   * この{@@link MutualFundProductRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "mutual_fund_product", "master" );


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
   * <em>fund_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum getFundType();


  /** 
   * <em>fund_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFundTypeIsSet();


  /** 
   * <em>fund_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFundTypeIsModified();


  /** 
   * <em>init_purchase_min_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getInitPurchaseMinQty();


  /** 
   * <em>init_purchase_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInitPurchaseMinQtyIsSet();


  /** 
   * <em>init_purchase_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInitPurchaseMinQtyIsModified();


  /** 
   * <em>addtl_purchase_min_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAddtlPurchaseMinQty();


  /** 
   * <em>addtl_purchase_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddtlPurchaseMinQtyIsSet();


  /** 
   * <em>addtl_purchase_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddtlPurchaseMinQtyIsModified();


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
   * <em>system_handling_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSystemHandlingDiv();


  /** 
   * <em>system_handling_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSystemHandlingDivIsSet();


  /** 
   * <em>system_handling_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSystemHandlingDivIsModified();


  /** 
   * <em>buy_limit_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuyLimitDiv();


  /** 
   * <em>buy_limit_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyLimitDivIsSet();


  /** 
   * <em>buy_limit_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyLimitDivIsModified();


  /** 
   * <em>standard_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStandardName();


  /** 
   * <em>standard_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStandardNameIsSet();


  /** 
   * <em>standard_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStandardNameIsModified();


  /** 
   * <em>eng_product_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEngProductName();


  /** 
   * <em>eng_product_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEngProductNameIsSet();


  /** 
   * <em>eng_product_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEngProductNameIsModified();


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
   * <em>sell_ban_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getSellBanDate();


  /** 
   * <em>sell_ban_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellBanDateIsSet();


  /** 
   * <em>sell_ban_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellBanDateIsModified();


  /** 
   * <em>swt_possible_group_id</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getSwtPossibleGroupId();


  /** 
   * <em>swt_possible_group_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSwtPossibleGroupIdIsNull();


  /** 
   * <em>swt_possible_group_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtPossibleGroupIdIsSet();


  /** 
   * <em>swt_possible_group_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtPossibleGroupIdIsModified();


  /** 
   * <em>category_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCategoryCode();


  /** 
   * <em>category_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCategoryCodeIsSet();


  /** 
   * <em>category_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCategoryCodeIsModified();


  /** 
   * <em>indication_ranking</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getIndicationRanking();


  /** 
   * <em>indication_ranking</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getIndicationRankingIsNull();


  /** 
   * <em>indication_ranking</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIndicationRankingIsSet();


  /** 
   * <em>indication_ranking</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIndicationRankingIsModified();


  /** 
   * <em>buy_constant_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getBuyConstantValue();


  /** 
   * <em>buy_constant_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBuyConstantValueIsNull();


  /** 
   * <em>buy_constant_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyConstantValueIsSet();


  /** 
   * <em>buy_constant_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyConstantValueIsModified();


  /** 
   * <em>sell_constant_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSellConstantValue();


  /** 
   * <em>sell_constant_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSellConstantValueIsNull();


  /** 
   * <em>sell_constant_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellConstantValueIsSet();


  /** 
   * <em>sell_constant_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellConstantValueIsModified();


  /** 
   * <em>reference_constant_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getReferenceConstantValue();


  /** 
   * <em>reference_constant_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getReferenceConstantValueIsNull();


  /** 
   * <em>reference_constant_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReferenceConstantValueIsSet();


  /** 
   * <em>reference_constant_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReferenceConstantValueIsModified();


  /** 
   * <em>constant_value_app_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getConstantValueAppDate();


  /** 
   * <em>constant_value_app_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConstantValueAppDateIsSet();


  /** 
   * <em>constant_value_app_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConstantValueAppDateIsModified();


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
   * <em>constant_value_calc_unit</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getConstantValueCalcUnit();


  /** 
   * <em>constant_value_calc_unit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getConstantValueCalcUnitIsNull();


  /** 
   * <em>constant_value_calc_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConstantValueCalcUnitIsSet();


  /** 
   * <em>constant_value_calc_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConstantValueCalcUnitIsModified();


  /** 
   * <em>buy_settlement_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuySettlementDiv();


  /** 
   * <em>buy_settlement_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuySettlementDivIsSet();


  /** 
   * <em>buy_settlement_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuySettlementDivIsModified();


  /** 
   * <em>sell_settlement_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSellSettlementDiv();


  /** 
   * <em>sell_settlement_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellSettlementDivIsSet();


  /** 
   * <em>sell_settlement_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellSettlementDivIsModified();


  /** 
   * <em>delivery_method</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDeliveryMethod();


  /** 
   * <em>delivery_method</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeliveryMethodIsSet();


  /** 
   * <em>delivery_method</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeliveryMethodIsModified();


  /** 
   * <em>buy_specity_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuySpecityDiv();


  /** 
   * <em>buy_specity_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuySpecityDivIsSet();


  /** 
   * <em>buy_specity_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuySpecityDivIsModified();


  /** 
   * <em>sell_specify_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSellSpecifyDiv();


  /** 
   * <em>sell_specify_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellSpecifyDivIsSet();


  /** 
   * <em>sell_specify_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellSpecifyDivIsModified();


  /** 
   * <em>swt_specify_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSwtSpecifyDiv();


  /** 
   * <em>swt_specify_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtSpecifyDivIsSet();


  /** 
   * <em>swt_specify_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtSpecifyDivIsModified();


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
   * <em>perference_money_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPerferenceMoneyDiv();


  /** 
   * <em>perference_money_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPerferenceMoneyDivIsSet();


  /** 
   * <em>perference_money_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPerferenceMoneyDivIsModified();


  /** 
   * <em>input_unit</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInputUnit();


  /** 
   * <em>input_unit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInputUnitIsSet();


  /** 
   * <em>input_unit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInputUnitIsModified();


  /** 
   * <em>new_buy_min_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getNewBuyMinQty();


  /** 
   * <em>new_buy_min_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getNewBuyMinQtyIsNull();


  /** 
   * <em>new_buy_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewBuyMinQtyIsSet();


  /** 
   * <em>new_buy_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewBuyMinQtyIsModified();


  /** 
   * <em>add_buy_min_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAddBuyMinQty();


  /** 
   * <em>add_buy_min_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAddBuyMinQtyIsNull();


  /** 
   * <em>add_buy_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddBuyMinQtyIsSet();


  /** 
   * <em>add_buy_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddBuyMinQtyIsModified();


  /** 
   * <em>sell_min_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getSellMinQty();


  /** 
   * <em>sell_min_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSellMinQtyIsNull();


  /** 
   * <em>sell_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellMinQtyIsSet();


  /** 
   * <em>sell_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellMinQtyIsModified();


  /** 
   * <em>swt_min_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getSwtMinQty();


  /** 
   * <em>swt_min_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSwtMinQtyIsNull();


  /** 
   * <em>swt_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtMinQtyIsSet();


  /** 
   * <em>swt_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtMinQtyIsModified();


  /** 
   * <em>new_buy_unit_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getNewBuyUnitQty();


  /** 
   * <em>new_buy_unit_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getNewBuyUnitQtyIsNull();


  /** 
   * <em>new_buy_unit_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewBuyUnitQtyIsSet();


  /** 
   * <em>new_buy_unit_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewBuyUnitQtyIsModified();


  /** 
   * <em>add_buy_unit_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAddBuyUnitQty();


  /** 
   * <em>add_buy_unit_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAddBuyUnitQtyIsNull();


  /** 
   * <em>add_buy_unit_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddBuyUnitQtyIsSet();


  /** 
   * <em>add_buy_unit_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddBuyUnitQtyIsModified();


  /** 
   * <em>sell_unit_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getSellUnitQty();


  /** 
   * <em>sell_unit_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSellUnitQtyIsNull();


  /** 
   * <em>sell_unit_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellUnitQtyIsSet();


  /** 
   * <em>sell_unit_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellUnitQtyIsModified();


  /** 
   * <em>swt_unit_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getSwtUnitQty();


  /** 
   * <em>swt_unit_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSwtUnitQtyIsNull();


  /** 
   * <em>swt_unit_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtUnitQtyIsSet();


  /** 
   * <em>swt_unit_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtUnitQtyIsModified();


  /** 
   * <em>new_buy_min_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getNewBuyMinAmt();


  /** 
   * <em>new_buy_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getNewBuyMinAmtIsNull();


  /** 
   * <em>new_buy_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewBuyMinAmtIsSet();


  /** 
   * <em>new_buy_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewBuyMinAmtIsModified();


  /** 
   * <em>add_buy_min_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAddBuyMinAmt();


  /** 
   * <em>add_buy_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAddBuyMinAmtIsNull();


  /** 
   * <em>add_buy_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddBuyMinAmtIsSet();


  /** 
   * <em>add_buy_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddBuyMinAmtIsModified();


  /** 
   * <em>sell_min_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getSellMinAmt();


  /** 
   * <em>sell_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSellMinAmtIsNull();


  /** 
   * <em>sell_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellMinAmtIsSet();


  /** 
   * <em>sell_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellMinAmtIsModified();


  /** 
   * <em>swt_min_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getSwtMinAmt();


  /** 
   * <em>swt_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSwtMinAmtIsNull();


  /** 
   * <em>swt_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtMinAmtIsSet();


  /** 
   * <em>swt_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtMinAmtIsModified();


  /** 
   * <em>new_buy_unit_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getNewBuyUnitAmt();


  /** 
   * <em>new_buy_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getNewBuyUnitAmtIsNull();


  /** 
   * <em>new_buy_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewBuyUnitAmtIsSet();


  /** 
   * <em>new_buy_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewBuyUnitAmtIsModified();


  /** 
   * <em>add_buy_unit_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAddBuyUnitAmt();


  /** 
   * <em>add_buy_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAddBuyUnitAmtIsNull();


  /** 
   * <em>add_buy_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddBuyUnitAmtIsSet();


  /** 
   * <em>add_buy_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddBuyUnitAmtIsModified();


  /** 
   * <em>sell_unit_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getSellUnitAmt();


  /** 
   * <em>sell_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSellUnitAmtIsNull();


  /** 
   * <em>sell_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellUnitAmtIsSet();


  /** 
   * <em>sell_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellUnitAmtIsModified();


  /** 
   * <em>swt_unit_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getSwtUnitAmt();


  /** 
   * <em>swt_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSwtUnitAmtIsNull();


  /** 
   * <em>swt_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtUnitAmtIsSet();


  /** 
   * <em>swt_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSwtUnitAmtIsModified();


  /** 
   * <em>buy_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getBuyStartDate();


  /** 
   * <em>buy_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyStartDateIsSet();


  /** 
   * <em>buy_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyStartDateIsModified();


  /** 
   * <em>buy_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getBuyEndDate();


  /** 
   * <em>buy_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyEndDateIsSet();


  /** 
   * <em>buy_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyEndDateIsModified();


  /** 
   * <em>sell_swt_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getSellSwtStartDate();


  /** 
   * <em>sell_swt_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellSwtStartDateIsSet();


  /** 
   * <em>sell_swt_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellSwtStartDateIsModified();


  /** 
   * <em>sell_swt_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getSellSwtEndDate();


  /** 
   * <em>sell_swt_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellSwtEndDateIsSet();


  /** 
   * <em>sell_swt_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellSwtEndDateIsModified();


  /** 
   * <em>buy_claim_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getBuyClaimStartDate();


  /** 
   * <em>buy_claim_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyClaimStartDateIsSet();


  /** 
   * <em>buy_claim_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyClaimStartDateIsModified();


  /** 
   * <em>buy_claim_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getBuyClaimEndDate();


  /** 
   * <em>buy_claim_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyClaimEndDateIsSet();


  /** 
   * <em>buy_claim_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyClaimEndDateIsModified();


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
   * <em>online_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getOnlineUpdatedTimestamp();


  /** 
   * <em>online_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOnlineUpdatedTimestampIsSet();


  /** 
   * <em>online_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOnlineUpdatedTimestampIsModified();


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
   * <em>recruit_constant_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getRecruitConstantValue();


  /** 
   * <em>recruit_constant_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRecruitConstantValueIsNull();


  /** 
   * <em>recruit_constant_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitConstantValueIsSet();


  /** 
   * <em>recruit_constant_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitConstantValueIsModified();


  /** 
   * <em>recruit_settlement_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRecruitSettlementDiv();


  /** 
   * <em>recruit_settlement_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitSettlementDivIsSet();


  /** 
   * <em>recruit_settlement_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitSettlementDivIsModified();


  /** 
   * <em>recruit_specity_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRecruitSpecityDiv();


  /** 
   * <em>recruit_specity_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitSpecityDivIsSet();


  /** 
   * <em>recruit_specity_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitSpecityDivIsModified();


  /** 
   * <em>recruit_min_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getRecruitMinQty();


  /** 
   * <em>recruit_min_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRecruitMinQtyIsNull();


  /** 
   * <em>recruit_min_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitMinQtyIsSet();


  /** 
   * <em>recruit_min_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitMinQtyIsModified();


  /** 
   * <em>recruit_unit_qty</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getRecruitUnitQty();


  /** 
   * <em>recruit_unit_qty</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRecruitUnitQtyIsNull();


  /** 
   * <em>recruit_unit_qty</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitUnitQtyIsSet();


  /** 
   * <em>recruit_unit_qty</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitUnitQtyIsModified();


  /** 
   * <em>recruit_min_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getRecruitMinAmt();


  /** 
   * <em>recruit_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRecruitMinAmtIsNull();


  /** 
   * <em>recruit_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitMinAmtIsSet();


  /** 
   * <em>recruit_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitMinAmtIsModified();


  /** 
   * <em>recruit_unit_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getRecruitUnitAmt();


  /** 
   * <em>recruit_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRecruitUnitAmtIsNull();


  /** 
   * <em>recruit_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitUnitAmtIsSet();


  /** 
   * <em>recruit_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitUnitAmtIsModified();


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
   * <em>cal_price_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCalPriceDiv();


  /** 
   * <em>cal_price_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalPriceDivIsSet();


  /** 
   * <em>cal_price_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalPriceDivIsModified();


  /** 
   * <em>plowback_product_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPlowbackProductDiv();


  /** 
   * <em>plowback_product_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPlowbackProductDivIsSet();


  /** 
   * <em>plowback_product_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPlowbackProductDivIsModified();


  /** 
   * <em>recruit_start_date_sonar</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getRecruitStartDateSonar();


  /** 
   * <em>recruit_start_date_sonar</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitStartDateSonarIsSet();


  /** 
   * <em>recruit_start_date_sonar</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitStartDateSonarIsModified();


  /** 
   * <em>recruit_end_date_sonar</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getRecruitEndDateSonar();


  /** 
   * <em>recruit_end_date_sonar</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitEndDateSonarIsSet();


  /** 
   * <em>recruit_end_date_sonar</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitEndDateSonarIsModified();


  /** 
   * <em>fixed_buy_possible_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFixedBuyPossibleDiv();


  /** 
   * <em>fixed_buy_possible_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFixedBuyPossibleDivIsSet();


  /** 
   * <em>fixed_buy_possible_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFixedBuyPossibleDivIsModified();


  /** 
   * <em>unit_type_product_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getUnitTypeProductDiv();


  /** 
   * <em>unit_type_product_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnitTypeProductDivIsSet();


  /** 
   * <em>unit_type_product_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnitTypeProductDivIsModified();


  /** 
   * <em>frgn_new_buy_min_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getFrgnNewBuyMinAmt();


  /** 
   * <em>frgn_new_buy_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFrgnNewBuyMinAmtIsNull();


  /** 
   * <em>frgn_new_buy_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnNewBuyMinAmtIsSet();


  /** 
   * <em>frgn_new_buy_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnNewBuyMinAmtIsModified();


  /** 
   * <em>frgn_add_buy_min_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getFrgnAddBuyMinAmt();


  /** 
   * <em>frgn_add_buy_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFrgnAddBuyMinAmtIsNull();


  /** 
   * <em>frgn_add_buy_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnAddBuyMinAmtIsSet();


  /** 
   * <em>frgn_add_buy_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnAddBuyMinAmtIsModified();


  /** 
   * <em>frgn_sell_min_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getFrgnSellMinAmt();


  /** 
   * <em>frgn_sell_min_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFrgnSellMinAmtIsNull();


  /** 
   * <em>frgn_sell_min_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnSellMinAmtIsSet();


  /** 
   * <em>frgn_sell_min_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnSellMinAmtIsModified();


  /** 
   * <em>frgn_new_buy_unit_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getFrgnNewBuyUnitAmt();


  /** 
   * <em>frgn_new_buy_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFrgnNewBuyUnitAmtIsNull();


  /** 
   * <em>frgn_new_buy_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnNewBuyUnitAmtIsSet();


  /** 
   * <em>frgn_new_buy_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnNewBuyUnitAmtIsModified();


  /** 
   * <em>frgn_add_buy_unit_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getFrgnAddBuyUnitAmt();


  /** 
   * <em>frgn_add_buy_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFrgnAddBuyUnitAmtIsNull();


  /** 
   * <em>frgn_add_buy_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnAddBuyUnitAmtIsSet();


  /** 
   * <em>frgn_add_buy_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnAddBuyUnitAmtIsModified();


  /** 
   * <em>frgn_sell_unit_amt</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getFrgnSellUnitAmt();


  /** 
   * <em>frgn_sell_unit_amt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getFrgnSellUnitAmtIsNull();


  /** 
   * <em>frgn_sell_unit_amt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnSellUnitAmtIsSet();


  /** 
   * <em>frgn_sell_unit_amt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrgnSellUnitAmtIsModified();


  /** 
   * <em>recruit_commission_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRecruitCommissionDiv();


  /** 
   * <em>recruit_commission_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitCommissionDivIsSet();


  /** 
   * <em>recruit_commission_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRecruitCommissionDivIsModified();


  /** 
   * <em>open_close_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOpenCloseDiv();


  /** 
   * <em>open_close_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOpenCloseDivIsSet();


  /** 
   * <em>open_close_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOpenCloseDivIsModified();


}
@
