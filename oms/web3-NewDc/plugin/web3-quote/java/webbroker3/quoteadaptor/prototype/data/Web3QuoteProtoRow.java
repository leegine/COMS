head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	Web3QuoteProtoRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.prototype.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;

/** 
 * Web3QuoteProtoRowインタフェースは変更不可でリードオンリーである<em>web3_quote_proto</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link Web3QuoteProtoRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see Web3QuoteProtoPK 
 */
public interface Web3QuoteProtoRow extends Row {


  /** 
   * この{@@link Web3QuoteProtoRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "web3_quote_proto", "session" );


  /** 
   * <em>quote_data_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getQuoteDataId();


  /** 
   * <em>quote_data_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQuoteDataIdIsSet();


  /** 
   * <em>quote_data_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQuoteDataIdIsModified();


  /** 
   * <em>quote_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getQuoteDate();


  /** 
   * <em>quote_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQuoteDateIsSet();


  /** 
   * <em>quote_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQuoteDateIsModified();


  /** 
   * <em>real_type</em>カラムの値を取得します。
   * @@return webbroker3.quoteadaptor.RealTypeの値 
   */
  public webbroker3.quoteadaptor.RealType getRealType();


  /** 
   * <em>real_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealTypeIsSet();


  /** 
   * <em>real_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealTypeIsModified();


  /** 
   * <em>data_type</em>カラムの値を取得します。
   * @@return webbroker3.quoteadaptor.DataTypeの値 
   */
  public webbroker3.quoteadaptor.DataType getDataType();


  /** 
   * <em>data_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDataTypeIsSet();


  /** 
   * <em>data_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDataTypeIsModified();


  /** 
   * <em>market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarketCode();


  /** 
   * <em>market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketCodeIsSet();


  /** 
   * <em>market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketCodeIsModified();


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
   * <em>contract_month</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getContractMonth();


  /** 
   * <em>contract_month</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractMonthIsSet();


  /** 
   * <em>contract_month</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractMonthIsModified();


  /** 
   * <em>put_and_call</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPutAndCall();


  /** 
   * <em>put_and_call</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPutAndCallIsSet();


  /** 
   * <em>put_and_call</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPutAndCallIsModified();


  /** 
   * <em>strike_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getStrikePrice();


  /** 
   * <em>strike_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getStrikePriceIsNull();


  /** 
   * <em>strike_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStrikePriceIsSet();


  /** 
   * <em>strike_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStrikePriceIsModified();


  /** 
   * <em>open_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOpenPrice();


  /** 
   * <em>open_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOpenPriceIsNull();


  /** 
   * <em>open_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOpenPriceIsSet();


  /** 
   * <em>open_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOpenPriceIsModified();


  /** 
   * <em>open_price_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOpenPriceTime();


  /** 
   * <em>open_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOpenPriceTimeIsSet();


  /** 
   * <em>open_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOpenPriceTimeIsModified();


  /** 
   * <em>high_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getHighPrice();


  /** 
   * <em>high_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getHighPriceIsNull();


  /** 
   * <em>high_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHighPriceIsSet();


  /** 
   * <em>high_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHighPriceIsModified();


  /** 
   * <em>high_price_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHighPriceTime();


  /** 
   * <em>high_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHighPriceTimeIsSet();


  /** 
   * <em>high_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHighPriceTimeIsModified();


  /** 
   * <em>low_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getLowPrice();


  /** 
   * <em>low_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLowPriceIsNull();


  /** 
   * <em>low_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLowPriceIsSet();


  /** 
   * <em>low_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLowPriceIsModified();


  /** 
   * <em>low_price_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLowPriceTime();


  /** 
   * <em>low_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLowPriceTimeIsSet();


  /** 
   * <em>low_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLowPriceTimeIsModified();


  /** 
   * <em>current_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCurrentPrice();


  /** 
   * <em>current_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCurrentPriceIsNull();


  /** 
   * <em>current_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrentPriceIsSet();


  /** 
   * <em>current_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrentPriceIsModified();


  /** 
   * <em>current_price_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCurrentPriceTime();


  /** 
   * <em>current_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrentPriceTimeIsSet();


  /** 
   * <em>current_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrentPriceTimeIsModified();


  /** 
   * <em>current_price_flag</em>カラムの値を取得します。
   * @@return webbroker3.quoteadaptor.CurrentPriceFlagの値 
   */
  public webbroker3.quoteadaptor.CurrentPriceFlag getCurrentPriceFlag();


  /** 
   * <em>current_price_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrentPriceFlagIsSet();


  /** 
   * <em>current_price_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCurrentPriceFlagIsModified();


  /** 
   * <em>change</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getChange();


  /** 
   * <em>change</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getChangeIsNull();


  /** 
   * <em>change</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getChangeIsSet();


  /** 
   * <em>change</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getChangeIsModified();


  /** 
   * <em>volume</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getVolume();


  /** 
   * <em>volume</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getVolumeIsNull();


  /** 
   * <em>volume</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getVolumeIsSet();


  /** 
   * <em>volume</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getVolumeIsModified();


  /** 
   * <em>volume_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getVolumeTime();


  /** 
   * <em>volume_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getVolumeTimeIsSet();


  /** 
   * <em>volume_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getVolumeTimeIsModified();


  /** 
   * <em>ask_price_title</em>カラムの値を取得します。
   * @@return webbroker3.quoteadaptor.AskPriceTitleの値 
   */
  public webbroker3.quoteadaptor.AskPriceTitle getAskPriceTitle();


  /** 
   * <em>ask_price_title</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAskPriceTitleIsSet();


  /** 
   * <em>ask_price_title</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAskPriceTitleIsModified();


  /** 
   * <em>ask_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getAskPrice();


  /** 
   * <em>ask_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAskPriceIsNull();


  /** 
   * <em>ask_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAskPriceIsSet();


  /** 
   * <em>ask_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAskPriceIsModified();


  /** 
   * <em>ask_price_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAskPriceTime();


  /** 
   * <em>ask_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAskPriceTimeIsSet();


  /** 
   * <em>ask_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAskPriceTimeIsModified();


  /** 
   * <em>bid_price_title</em>カラムの値を取得します。
   * @@return webbroker3.quoteadaptor.BidPriceTitleの値 
   */
  public webbroker3.quoteadaptor.BidPriceTitle getBidPriceTitle();


  /** 
   * <em>bid_price_title</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBidPriceTitleIsSet();


  /** 
   * <em>bid_price_title</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBidPriceTitleIsModified();


  /** 
   * <em>bid_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getBidPrice();


  /** 
   * <em>bid_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBidPriceIsNull();


  /** 
   * <em>bid_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBidPriceIsSet();


  /** 
   * <em>bid_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBidPriceIsModified();


  /** 
   * <em>bid_price_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBidPriceTime();


  /** 
   * <em>bid_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBidPriceTimeIsSet();


  /** 
   * <em>bid_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBidPriceTimeIsModified();


  /** 
   * <em>base_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getBasePrice();


  /** 
   * <em>base_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBasePriceIsNull();


  /** 
   * <em>base_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBasePriceIsSet();


  /** 
   * <em>base_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBasePriceIsModified();


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
