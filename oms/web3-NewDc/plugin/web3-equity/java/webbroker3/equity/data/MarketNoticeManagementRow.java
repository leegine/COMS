head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MarketNoticeManagementRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;

/** 
 * MarketNoticeManagementRowインタフェースは変更不可でリードオンリーである<em>market_notice_management</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link MarketNoticeManagementRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MarketNoticeManagementPK 
 */
public interface MarketNoticeManagementRow extends Row {


  /** 
   * この{@@link MarketNoticeManagementRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "market_notice_management", "session" );


  /** 
   * <em>virtual_server_number_market</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getVirtualServerNumberMarket();


  /** 
   * <em>virtual_server_number_market</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getVirtualServerNumberMarketIsSet();


  /** 
   * <em>virtual_server_number_market</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getVirtualServerNumberMarketIsModified();


  /** 
   * <em>notice_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNoticeType();


  /** 
   * <em>notice_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNoticeTypeIsSet();


  /** 
   * <em>notice_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNoticeTypeIsModified();


  /** 
   * <em>notice_number</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getNoticeNumber();


  /** 
   * <em>notice_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNoticeNumberIsSet();


  /** 
   * <em>notice_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNoticeNumberIsModified();


  /** 
   * <em>client_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getClientNumber();


  /** 
   * <em>client_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClientNumberIsSet();


  /** 
   * <em>client_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClientNumberIsModified();


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
   * <em>front_order_exchange_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFrontOrderExchangeCode();


  /** 
   * <em>front_order_exchange_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrontOrderExchangeCodeIsSet();


  /** 
   * <em>front_order_exchange_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrontOrderExchangeCodeIsModified();


  /** 
   * <em>front_order_system_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFrontOrderSystemCode();


  /** 
   * <em>front_order_system_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrontOrderSystemCodeIsSet();


  /** 
   * <em>front_order_system_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrontOrderSystemCodeIsModified();


  /** 
   * <em>front_order_trade_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFrontOrderTradeCode();


  /** 
   * <em>front_order_trade_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrontOrderTradeCodeIsSet();


  /** 
   * <em>front_order_trade_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFrontOrderTradeCodeIsModified();


  /** 
   * <em>data_class_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDataClassCode();


  /** 
   * <em>data_class_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDataClassCodeIsSet();


  /** 
   * <em>data_class_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDataClassCodeIsModified();


  /** 
   * <em>data_class_detail_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDataClassDetailCode();


  /** 
   * <em>data_class_detail_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDataClassDetailCodeIsSet();


  /** 
   * <em>data_class_detail_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDataClassDetailCodeIsModified();


  /** 
   * <em>corp_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCorpCode();


  /** 
   * <em>corp_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCorpCodeIsSet();


  /** 
   * <em>corp_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCorpCodeIsModified();


  /** 
   * <em>resend_flg</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getResendFlg();


  /** 
   * <em>resend_flg</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getResendFlgIsSet();


  /** 
   * <em>resend_flg</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getResendFlgIsModified();


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
   * <em>error_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getErrorCode();


  /** 
   * <em>error_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getErrorCodeIsSet();


  /** 
   * <em>error_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getErrorCodeIsModified();


  /** 
   * <em>accept_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAcceptNumber();


  /** 
   * <em>accept_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAcceptNumberIsSet();


  /** 
   * <em>accept_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAcceptNumberIsModified();


  /** 
   * <em>market_time</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getMarketTime();


  /** 
   * <em>market_time</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarketTimeIsNull();


  /** 
   * <em>market_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketTimeIsSet();


  /** 
   * <em>market_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketTimeIsModified();


  /** 
   * <em>buy_sell_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuySellDiv();


  /** 
   * <em>buy_sell_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuySellDivIsSet();


  /** 
   * <em>buy_sell_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuySellDivIsModified();


  /** 
   * <em>execution_condition</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExecutionCondition();


  /** 
   * <em>execution_condition</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecutionConditionIsSet();


  /** 
   * <em>execution_condition</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecutionConditionIsModified();


  /** 
   * <em>price_condition_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPriceConditionType();


  /** 
   * <em>price_condition_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPriceConditionTypeIsSet();


  /** 
   * <em>price_condition_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPriceConditionTypeIsModified();


  /** 
   * <em>order_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOrderQuantity();


  /** 
   * <em>order_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOrderQuantityIsNull();


  /** 
   * <em>order_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderQuantityIsSet();


  /** 
   * <em>order_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderQuantityIsModified();


  /** 
   * <em>limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getLimitPrice();


  /** 
   * <em>limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLimitPriceIsNull();


  /** 
   * <em>limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitPriceIsSet();


  /** 
   * <em>limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitPriceIsModified();


  /** 
   * <em>tradeaudit_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradeauditCode();


  /** 
   * <em>tradeaudit_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeauditCodeIsSet();


  /** 
   * <em>tradeaudit_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeauditCodeIsModified();


  /** 
   * <em>margin_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginCode();


  /** 
   * <em>margin_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginCodeIsSet();


  /** 
   * <em>margin_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginCodeIsModified();


  /** 
   * <em>short_sell_order_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getShortSellOrderFlag();


  /** 
   * <em>short_sell_order_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortSellOrderFlagIsSet();


  /** 
   * <em>short_sell_order_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getShortSellOrderFlagIsModified();


  /** 
   * <em>org_limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOrgLimitPrice();


  /** 
   * <em>org_limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOrgLimitPriceIsNull();


  /** 
   * <em>org_limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgLimitPriceIsSet();


  /** 
   * <em>org_limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgLimitPriceIsModified();


  /** 
   * <em>org_corp_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrgCorpCode();


  /** 
   * <em>org_corp_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgCorpCodeIsSet();


  /** 
   * <em>org_corp_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgCorpCodeIsModified();


  /** 
   * <em>cut_quantity</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getCutQuantity();


  /** 
   * <em>cut_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCutQuantityIsNull();


  /** 
   * <em>cut_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCutQuantityIsSet();


  /** 
   * <em>cut_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCutQuantityIsModified();


  /** 
   * <em>exec_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getExecPrice();


  /** 
   * <em>exec_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExecPriceIsNull();


  /** 
   * <em>exec_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecPriceIsSet();


  /** 
   * <em>exec_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecPriceIsModified();


  /** 
   * <em>exec_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getExecQuantity();


  /** 
   * <em>exec_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExecQuantityIsNull();


  /** 
   * <em>exec_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecQuantityIsSet();


  /** 
   * <em>exec_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecQuantityIsModified();


  /** 
   * <em>left_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getLeftQuantity();


  /** 
   * <em>left_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLeftQuantityIsNull();


  /** 
   * <em>left_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLeftQuantityIsSet();


  /** 
   * <em>left_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLeftQuantityIsModified();


  /** 
   * <em>price_mark</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPriceMark();


  /** 
   * <em>price_mark</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPriceMarkIsSet();


  /** 
   * <em>price_mark</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPriceMarkIsModified();


  /** 
   * <em>exec_mark</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExecMark();


  /** 
   * <em>exec_mark</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecMarkIsSet();


  /** 
   * <em>exec_mark</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecMarkIsModified();


  /** 
   * <em>exec_number</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getExecNumber();


  /** 
   * <em>exec_number</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExecNumberIsNull();


  /** 
   * <em>exec_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecNumberIsSet();


  /** 
   * <em>exec_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecNumberIsModified();


  /** 
   * <em>modified_result</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getModifiedResult();


  /** 
   * <em>modified_result</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getModifiedResultIsSet();


  /** 
   * <em>modified_result</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getModifiedResultIsModified();


  /** 
   * <em>org_execution_condition</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrgExecutionCondition();


  /** 
   * <em>org_execution_condition</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgExecutionConditionIsSet();


  /** 
   * <em>org_execution_condition</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgExecutionConditionIsModified();


  /** 
   * <em>org_price_condition_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrgPriceConditionType();


  /** 
   * <em>org_price_condition_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgPriceConditionTypeIsSet();


  /** 
   * <em>org_price_condition_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgPriceConditionTypeIsModified();


  /** 
   * <em>org_order_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOrgOrderQuantity();


  /** 
   * <em>org_order_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOrgOrderQuantityIsNull();


  /** 
   * <em>org_order_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgOrderQuantityIsSet();


  /** 
   * <em>org_order_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgOrderQuantityIsModified();


  /** 
   * <em>canceled_quantity</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getCanceledQuantity();


  /** 
   * <em>canceled_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCanceledQuantityIsNull();


  /** 
   * <em>canceled_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCanceledQuantityIsSet();


  /** 
   * <em>canceled_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCanceledQuantityIsModified();


  /** 
   * <em>executed_quantity</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getExecutedQuantity();


  /** 
   * <em>executed_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExecutedQuantityIsNull();


  /** 
   * <em>executed_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecutedQuantityIsSet();


  /** 
   * <em>executed_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExecutedQuantityIsModified();


  /** 
   * <em>reason_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getReasonCode();


  /** 
   * <em>reason_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReasonCodeIsSet();


  /** 
   * <em>reason_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReasonCodeIsModified();


  /** 
   * <em>stop_mark</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStopMark();


  /** 
   * <em>stop_mark</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopMarkIsSet();


  /** 
   * <em>stop_mark</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopMarkIsModified();


  /** 
   * <em>expiration_quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getExpirationQuantity();


  /** 
   * <em>expiration_quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExpirationQuantityIsNull();


  /** 
   * <em>expiration_quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExpirationQuantityIsSet();


  /** 
   * <em>expiration_quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExpirationQuantityIsModified();


  /** 
   * <em>biz_date_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getBizDateCount();


  /** 
   * <em>biz_date_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBizDateCountIsSet();


  /** 
   * <em>biz_date_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBizDateCountIsModified();


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
   * <em>submit_order_route_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSubmitOrderRouteDiv();


  /** 
   * <em>submit_order_route_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubmitOrderRouteDivIsSet();


  /** 
   * <em>submit_order_route_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubmitOrderRouteDivIsModified();


  /** 
   * <em>product_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType();


  /** 
   * <em>product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductTypeIsSet();


  /** 
   * <em>product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductTypeIsModified();


  /** 
   * <em>stop_order_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getStopOrderPrice();


  /** 
   * <em>stop_order_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getStopOrderPriceIsNull();


  /** 
   * <em>stop_order_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopOrderPriceIsSet();


  /** 
   * <em>stop_order_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStopOrderPriceIsModified();


  /** 
   * <em>w_limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getWLimitPrice();


  /** 
   * <em>w_limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getWLimitPriceIsNull();


  /** 
   * <em>w_limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWLimitPriceIsSet();


  /** 
   * <em>w_limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getWLimitPriceIsModified();


  /** 
   * <em>org_stop_order_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOrgStopOrderPrice();


  /** 
   * <em>org_stop_order_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOrgStopOrderPriceIsNull();


  /** 
   * <em>org_stop_order_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgStopOrderPriceIsSet();


  /** 
   * <em>org_stop_order_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgStopOrderPriceIsModified();


  /** 
   * <em>org_w_limit_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOrgWLimitPrice();


  /** 
   * <em>org_w_limit_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOrgWLimitPriceIsNull();


  /** 
   * <em>org_w_limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgWLimitPriceIsSet();


  /** 
   * <em>org_w_limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgWLimitPriceIsModified();


}
@
