head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.21.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	IdConfirmVoucherRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * IdConfirmVoucherRowインタフェースは変更不可でリードオンリーである<em>id_confirm_voucher</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link IdConfirmVoucherRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IdConfirmVoucherPK 
 */
public interface IdConfirmVoucherRow extends Row {


  /** 
   * この{@@link IdConfirmVoucherRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "id_confirm_voucher", "session" );


  /** 
   * <em>order_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderRequestNumber();


  /** 
   * <em>order_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderRequestNumberIsSet();


  /** 
   * <em>order_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderRequestNumberIsModified();


  /** 
   * <em>request_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRequestCode();


  /** 
   * <em>request_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRequestCodeIsSet();


  /** 
   * <em>request_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRequestCodeIsModified();


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
   * <em>account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountCode();


  /** 
   * <em>account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeIsSet();


  /** 
   * <em>account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeIsModified();


  /** 
   * <em>trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTraderCode();


  /** 
   * <em>trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTraderCodeIsSet();


  /** 
   * <em>trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTraderCodeIsModified();


  /** 
   * <em>acc_open_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccOpenRequestNumber();


  /** 
   * <em>acc_open_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccOpenRequestNumberIsSet();


  /** 
   * <em>acc_open_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccOpenRequestNumberIsModified();


  /** 
   * <em>serial_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSerialNo();


  /** 
   * <em>serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSerialNoIsSet();


  /** 
   * <em>serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSerialNoIsModified();


  /** 
   * <em>regist_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRegistDiv();


  /** 
   * <em>regist_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRegistDivIsSet();


  /** 
   * <em>regist_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRegistDivIsModified();


  /** 
   * <em>data_class</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDataClass();


  /** 
   * <em>data_class</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDataClassIsSet();


  /** 
   * <em>data_class</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDataClassIsModified();


  /** 
   * <em>id_attribute_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIdAttributeDiv();


  /** 
   * <em>id_attribute_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIdAttributeDivIsSet();


  /** 
   * <em>id_attribute_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIdAttributeDivIsModified();


  /** 
   * <em>trading_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradingDiv();


  /** 
   * <em>trading_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingDivIsSet();


  /** 
   * <em>trading_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingDivIsModified();


  /** 
   * <em>confirm_way_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getConfirmWayDiv();


  /** 
   * <em>confirm_way_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmWayDivIsSet();


  /** 
   * <em>confirm_way_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmWayDivIsModified();


  /** 
   * <em>confirm_doc_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getConfirmDocDiv();


  /** 
   * <em>confirm_doc_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmDocDivIsSet();


  /** 
   * <em>confirm_doc_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmDocDivIsModified();


  /** 
   * <em>address_confirm_doc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressConfirmDoc();


  /** 
   * <em>address_confirm_doc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressConfirmDocIsSet();


  /** 
   * <em>address_confirm_doc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressConfirmDocIsModified();


  /** 
   * <em>confirm_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getConfirmDate();


  /** 
   * <em>confirm_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmDateIsSet();


  /** 
   * <em>confirm_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmDateIsModified();


  /** 
   * <em>appli_motivat_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAppliMotivatDiv();


  /** 
   * <em>appli_motivat_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAppliMotivatDivIsSet();


  /** 
   * <em>appli_motivat_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAppliMotivatDivIsModified();


  /** 
   * <em>invest_purpose_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInvestPurposeDiv();


  /** 
   * <em>invest_purpose_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInvestPurposeDivIsSet();


  /** 
   * <em>invest_purpose_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInvestPurposeDivIsModified();


  /** 
   * <em>experience_equity</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceEquity();


  /** 
   * <em>experience_equity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceEquityIsSet();


  /** 
   * <em>experience_equity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceEquityIsModified();


  /** 
   * <em>experience_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceMargin();


  /** 
   * <em>experience_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceMarginIsSet();


  /** 
   * <em>experience_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceMarginIsModified();


  /** 
   * <em>experience_bond</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceBond();


  /** 
   * <em>experience_bond</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceBondIsSet();


  /** 
   * <em>experience_bond</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceBondIsModified();


  /** 
   * <em>experience_fund</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceFund();


  /** 
   * <em>experience_fund</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFundIsSet();


  /** 
   * <em>experience_fund</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFundIsModified();


  /** 
   * <em>experience_fo</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceFo();


  /** 
   * <em>experience_fo</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFoIsSet();


  /** 
   * <em>experience_fo</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFoIsModified();


  /** 
   * <em>experience_f_equity</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceFEquity();


  /** 
   * <em>experience_f_equity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFEquityIsSet();


  /** 
   * <em>experience_f_equity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFEquityIsModified();


  /** 
   * <em>experience_etc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceEtc();


  /** 
   * <em>experience_etc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceEtcIsSet();


  /** 
   * <em>experience_etc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceEtcIsModified();


  /** 
   * <em>experience_from</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceFrom();


  /** 
   * <em>experience_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFromIsSet();


  /** 
   * <em>experience_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFromIsModified();


  /** 
   * <em>experience_to</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceTo();


  /** 
   * <em>experience_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceToIsSet();


  /** 
   * <em>experience_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceToIsModified();


  /** 
   * <em>equity_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEquityTradeDiv();


  /** 
   * <em>equity_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityTradeDivIsSet();


  /** 
   * <em>equity_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityTradeDivIsModified();


  /** 
   * <em>margin_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginTradeDiv();


  /** 
   * <em>margin_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTradeDivIsSet();


  /** 
   * <em>margin_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTradeDivIsModified();


  /** 
   * <em>bond_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBondTradeDiv();


  /** 
   * <em>bond_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondTradeDivIsSet();


  /** 
   * <em>bond_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondTradeDivIsModified();


  /** 
   * <em>fund_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFundTradeDiv();


  /** 
   * <em>fund_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFundTradeDivIsSet();


  /** 
   * <em>fund_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFundTradeDivIsModified();


  /** 
   * <em>fo_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFoTradeDiv();


  /** 
   * <em>fo_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFoTradeDivIsSet();


  /** 
   * <em>fo_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFoTradeDivIsModified();


  /** 
   * <em>f_equity_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFEquityTradeDiv();


  /** 
   * <em>f_equity_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFEquityTradeDivIsSet();


  /** 
   * <em>f_equity_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFEquityTradeDivIsModified();


  /** 
   * <em>etc_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEtcTradeDiv();


  /** 
   * <em>etc_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEtcTradeDivIsSet();


  /** 
   * <em>etc_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEtcTradeDivIsModified();


  /** 
   * <em>annual_income_from</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAnnualIncomeFrom();


  /** 
   * <em>annual_income_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAnnualIncomeFromIsSet();


  /** 
   * <em>annual_income_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAnnualIncomeFromIsModified();


  /** 
   * <em>annual_income_to</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAnnualIncomeTo();


  /** 
   * <em>annual_income_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAnnualIncomeToIsSet();


  /** 
   * <em>annual_income_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAnnualIncomeToIsModified();


  /** 
   * <em>asset_value_from</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAssetValueFrom();


  /** 
   * <em>asset_value_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAssetValueFromIsSet();


  /** 
   * <em>asset_value_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAssetValueFromIsModified();


  /** 
   * <em>asset_value_to</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAssetValueTo();


  /** 
   * <em>asset_value_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAssetValueToIsSet();


  /** 
   * <em>asset_value_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAssetValueToIsModified();


  /** 
   * <em>status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStatus();


  /** 
   * <em>status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatusIsSet();


  /** 
   * <em>status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatusIsModified();


  /** 
   * <em>send_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getSendTimestamp();


  /** 
   * <em>send_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendTimestampIsSet();


  /** 
   * <em>send_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendTimestampIsModified();


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
   * <em>div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDiv();


  /** 
   * <em>div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDivIsSet();


  /** 
   * <em>div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDivIsModified();


  /** 
   * <em>relation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRelation();


  /** 
   * <em>relation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRelationIsSet();


  /** 
   * <em>relation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRelationIsModified();


  /** 
   * <em>confirm_way</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getConfirmWay();


  /** 
   * <em>confirm_way</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmWayIsSet();


  /** 
   * <em>confirm_way</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmWayIsModified();


  /** 
   * <em>confirm_doc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getConfirmDoc();


  /** 
   * <em>confirm_doc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmDocIsSet();


  /** 
   * <em>confirm_doc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getConfirmDocIsModified();


  /** 
   * <em>add_confirm_doc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddConfirmDoc();


  /** 
   * <em>add_confirm_doc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddConfirmDocIsSet();


  /** 
   * <em>add_confirm_doc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddConfirmDocIsModified();


  /** 
   * <em>charge_confirm_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getChargeConfirmDate();


  /** 
   * <em>charge_confirm_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getChargeConfirmDateIsSet();


  /** 
   * <em>charge_confirm_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getChargeConfirmDateIsModified();


  /** 
   * <em>charge_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getChargeName();


  /** 
   * <em>charge_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getChargeNameIsSet();


  /** 
   * <em>charge_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getChargeNameIsModified();


  /** 
   * <em>born_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getBornDate();


  /** 
   * <em>born_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBornDateIsSet();


  /** 
   * <em>born_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBornDateIsModified();


  /** 
   * <em>telephone1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTelephone1();


  /** 
   * <em>telephone1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTelephone1IsSet();


  /** 
   * <em>telephone1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTelephone1IsModified();


  /** 
   * <em>telephone2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTelephone2();


  /** 
   * <em>telephone2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTelephone2IsSet();


  /** 
   * <em>telephone2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTelephone2IsModified();


  /** 
   * <em>telephone3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTelephone3();


  /** 
   * <em>telephone3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTelephone3IsSet();


  /** 
   * <em>telephone3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTelephone3IsModified();


  /** 
   * <em>zip_code1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getZipCode1();


  /** 
   * <em>zip_code1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getZipCode1IsSet();


  /** 
   * <em>zip_code1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getZipCode1IsModified();


  /** 
   * <em>zip_code2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getZipCode2();


  /** 
   * <em>zip_code2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getZipCode2IsSet();


  /** 
   * <em>zip_code2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getZipCode2IsModified();


  /** 
   * <em>address_line1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine1();


  /** 
   * <em>address_line1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine1IsSet();


  /** 
   * <em>address_line1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine1IsModified();


  /** 
   * <em>address_line2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine2();


  /** 
   * <em>address_line2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine2IsSet();


  /** 
   * <em>address_line2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine2IsModified();


  /** 
   * <em>address_line3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine3();


  /** 
   * <em>address_line3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine3IsSet();


  /** 
   * <em>address_line3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine3IsModified();


  /** 
   * <em>address_line1_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine1Kana();


  /** 
   * <em>address_line1_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine1KanaIsSet();


  /** 
   * <em>address_line1_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine1KanaIsModified();


  /** 
   * <em>address_line2_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine2Kana();


  /** 
   * <em>address_line2_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine2KanaIsSet();


  /** 
   * <em>address_line2_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine2KanaIsModified();


  /** 
   * <em>address_line3_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine3Kana();


  /** 
   * <em>address_line3_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine3KanaIsSet();


  /** 
   * <em>address_line3_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine3KanaIsModified();


}
@
