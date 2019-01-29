head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.08.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88667f0644;
filename	IfoTradedProductParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbifo.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * ifo_traded_productテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link IfoTradedProductRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link IfoTradedProductParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link IfoTradedProductParams}が{@@link IfoTradedProductRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoTradedProductPK 
 * @@see IfoTradedProductRow 
 */
public class IfoTradedProductParams extends Params implements IfoTradedProductRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ifo_traded_product";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = IfoTradedProductRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return IfoTradedProductRow.TYPE;
  }


  /** 
   * <em>traded_product_id</em>カラムの値 
   */
  public  long  traded_product_id;

  /** 
   * <em>valid_for_biz_date</em>カラムの値 
   */
  public  String  valid_for_biz_date;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>market_id</em>カラムの値 
   */
  public  long  market_id;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>unit_size</em>カラムの値 
   */
  public  double  unit_size;

  /** 
   * <em>unit_margin</em>カラムの値 
   */
  public  long  unit_margin;

  /** 
   * <em>per_order_max_units</em>カラムの値 
   */
  public  double  per_order_max_units;

  /** 
   * <em>order_close_time</em>カラムの値 
   */
  public  String  order_close_time;

  /** 
   * <em>last_closing_price</em>カラムの値 
   */
  public  Double  last_closing_price;

  /** 
   * <em>start_trading_date</em>カラムの値 
   */
  public  java.sql.Timestamp  start_trading_date;

  /** 
   * <em>last_trading_date</em>カラムの値 
   */
  public  java.sql.Timestamp  last_trading_date;

  /** 
   * <em>trade_stop_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  trade_stop_flag;

  /** 
   * <em>buy_to_open_stop_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  buy_to_open_stop_flag;

  /** 
   * <em>sell_to_open_stop_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  sell_to_open_stop_flag;

  /** 
   * <em>sell_to_close_stop_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  sell_to_close_stop_flag;

  /** 
   * <em>buy_to_close_stop_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  buy_to_close_stop_flag;

  /** 
   * <em>list_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  list_flag;

  /** 
   * <em>list_date</em>カラムの値 
   */
  public  java.sql.Timestamp  list_date;

  /** 
   * <em>unlisted_date</em>カラムの値 
   */
  public  java.sql.Timestamp  unlisted_date;

  /** 
   * <em>exercise_stop</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  exercise_stop;

  /** 
   * <em>actual_recieve_stop</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  actual_recieve_stop;

  /** 
   * <em>actual_delivary_stop</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  actual_delivary_stop;

  /** 
   * <em>reserve_stop</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  reserve_stop;

  /** 
   * <em>indication_price</em>カラムの値 
   */
  public  Double  indication_price;

  /** 
   * <em>last_liquidation_price</em>カラムの値 
   */
  public  Double  last_liquidation_price;

  /** 
   * <em>target_spot_price</em>カラムの値 
   */
  public  Double  target_spot_price;

  /** 
   * <em>liquidation_price</em>カラムの値 
   */
  public  Double  liquidation_price;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean traded_product_id_is_set = false;
  private boolean traded_product_id_is_modified = false;
  private boolean valid_for_biz_date_is_set = false;
  private boolean valid_for_biz_date_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean unit_size_is_set = false;
  private boolean unit_size_is_modified = false;
  private boolean unit_margin_is_set = false;
  private boolean unit_margin_is_modified = false;
  private boolean per_order_max_units_is_set = false;
  private boolean per_order_max_units_is_modified = false;
  private boolean order_close_time_is_set = false;
  private boolean order_close_time_is_modified = false;
  private boolean last_closing_price_is_set = false;
  private boolean last_closing_price_is_modified = false;
  private boolean start_trading_date_is_set = false;
  private boolean start_trading_date_is_modified = false;
  private boolean last_trading_date_is_set = false;
  private boolean last_trading_date_is_modified = false;
  private boolean trade_stop_flag_is_set = false;
  private boolean trade_stop_flag_is_modified = false;
  private boolean buy_to_open_stop_flag_is_set = false;
  private boolean buy_to_open_stop_flag_is_modified = false;
  private boolean sell_to_open_stop_flag_is_set = false;
  private boolean sell_to_open_stop_flag_is_modified = false;
  private boolean sell_to_close_stop_flag_is_set = false;
  private boolean sell_to_close_stop_flag_is_modified = false;
  private boolean buy_to_close_stop_flag_is_set = false;
  private boolean buy_to_close_stop_flag_is_modified = false;
  private boolean list_flag_is_set = false;
  private boolean list_flag_is_modified = false;
  private boolean list_date_is_set = false;
  private boolean list_date_is_modified = false;
  private boolean unlisted_date_is_set = false;
  private boolean unlisted_date_is_modified = false;
  private boolean exercise_stop_is_set = false;
  private boolean exercise_stop_is_modified = false;
  private boolean actual_recieve_stop_is_set = false;
  private boolean actual_recieve_stop_is_modified = false;
  private boolean actual_delivary_stop_is_set = false;
  private boolean actual_delivary_stop_is_modified = false;
  private boolean reserve_stop_is_set = false;
  private boolean reserve_stop_is_modified = false;
  private boolean indication_price_is_set = false;
  private boolean indication_price_is_modified = false;
  private boolean last_liquidation_price_is_set = false;
  private boolean last_liquidation_price_is_modified = false;
  private boolean target_spot_price_is_set = false;
  private boolean target_spot_price_is_modified = false;
  private boolean liquidation_price_is_set = false;
  private boolean liquidation_price_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "traded_product_id=" + traded_product_id
      + "," + "valid_for_biz_date=" +valid_for_biz_date
      + "," + "institution_code=" +institution_code
      + "," + "market_id=" +market_id
      + "," + "product_id=" +product_id
      + "," + "unit_size=" +unit_size
      + "," + "unit_margin=" +unit_margin
      + "," + "per_order_max_units=" +per_order_max_units
      + "," + "order_close_time=" +order_close_time
      + "," + "last_closing_price=" +last_closing_price
      + "," + "start_trading_date=" +start_trading_date
      + "," + "last_trading_date=" +last_trading_date
      + "," + "trade_stop_flag=" +trade_stop_flag
      + "," + "buy_to_open_stop_flag=" +buy_to_open_stop_flag
      + "," + "sell_to_open_stop_flag=" +sell_to_open_stop_flag
      + "," + "sell_to_close_stop_flag=" +sell_to_close_stop_flag
      + "," + "buy_to_close_stop_flag=" +buy_to_close_stop_flag
      + "," + "list_flag=" +list_flag
      + "," + "list_date=" +list_date
      + "," + "unlisted_date=" +unlisted_date
      + "," + "exercise_stop=" +exercise_stop
      + "," + "actual_recieve_stop=" +actual_recieve_stop
      + "," + "actual_delivary_stop=" +actual_delivary_stop
      + "," + "reserve_stop=" +reserve_stop
      + "," + "indication_price=" +indication_price
      + "," + "last_liquidation_price=" +last_liquidation_price
      + "," + "target_spot_price=" +target_spot_price
      + "," + "liquidation_price=" +liquidation_price
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のIfoTradedProductParamsオブジェクトを作成します。 
   */
  public IfoTradedProductParams() {
    traded_product_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のIfoTradedProductRowオブジェクトの値を利用してIfoTradedProductParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するIfoTradedProductRowオブジェクト 
   */
  public IfoTradedProductParams( IfoTradedProductRow p_row )
  {
    traded_product_id = p_row.getTradedProductId();
    traded_product_id_is_set = p_row.getTradedProductIdIsSet();
    traded_product_id_is_modified = p_row.getTradedProductIdIsModified();
    valid_for_biz_date = p_row.getValidForBizDate();
    valid_for_biz_date_is_set = p_row.getValidForBizDateIsSet();
    valid_for_biz_date_is_modified = p_row.getValidForBizDateIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    unit_size = p_row.getUnitSize();
    unit_size_is_set = p_row.getUnitSizeIsSet();
    unit_size_is_modified = p_row.getUnitSizeIsModified();
    unit_margin = p_row.getUnitMargin();
    unit_margin_is_set = p_row.getUnitMarginIsSet();
    unit_margin_is_modified = p_row.getUnitMarginIsModified();
    per_order_max_units = p_row.getPerOrderMaxUnits();
    per_order_max_units_is_set = p_row.getPerOrderMaxUnitsIsSet();
    per_order_max_units_is_modified = p_row.getPerOrderMaxUnitsIsModified();
    order_close_time = p_row.getOrderCloseTime();
    order_close_time_is_set = p_row.getOrderCloseTimeIsSet();
    order_close_time_is_modified = p_row.getOrderCloseTimeIsModified();
    if ( !p_row.getLastClosingPriceIsNull() )
      last_closing_price = new Double( p_row.getLastClosingPrice() );
    last_closing_price_is_set = p_row.getLastClosingPriceIsSet();
    last_closing_price_is_modified = p_row.getLastClosingPriceIsModified();
    start_trading_date = p_row.getStartTradingDate();
    start_trading_date_is_set = p_row.getStartTradingDateIsSet();
    start_trading_date_is_modified = p_row.getStartTradingDateIsModified();
    last_trading_date = p_row.getLastTradingDate();
    last_trading_date_is_set = p_row.getLastTradingDateIsSet();
    last_trading_date_is_modified = p_row.getLastTradingDateIsModified();
    trade_stop_flag = p_row.getTradeStopFlag();
    trade_stop_flag_is_set = p_row.getTradeStopFlagIsSet();
    trade_stop_flag_is_modified = p_row.getTradeStopFlagIsModified();
    buy_to_open_stop_flag = p_row.getBuyToOpenStopFlag();
    buy_to_open_stop_flag_is_set = p_row.getBuyToOpenStopFlagIsSet();
    buy_to_open_stop_flag_is_modified = p_row.getBuyToOpenStopFlagIsModified();
    sell_to_open_stop_flag = p_row.getSellToOpenStopFlag();
    sell_to_open_stop_flag_is_set = p_row.getSellToOpenStopFlagIsSet();
    sell_to_open_stop_flag_is_modified = p_row.getSellToOpenStopFlagIsModified();
    sell_to_close_stop_flag = p_row.getSellToCloseStopFlag();
    sell_to_close_stop_flag_is_set = p_row.getSellToCloseStopFlagIsSet();
    sell_to_close_stop_flag_is_modified = p_row.getSellToCloseStopFlagIsModified();
    buy_to_close_stop_flag = p_row.getBuyToCloseStopFlag();
    buy_to_close_stop_flag_is_set = p_row.getBuyToCloseStopFlagIsSet();
    buy_to_close_stop_flag_is_modified = p_row.getBuyToCloseStopFlagIsModified();
    list_flag = p_row.getListFlag();
    list_flag_is_set = p_row.getListFlagIsSet();
    list_flag_is_modified = p_row.getListFlagIsModified();
    list_date = p_row.getListDate();
    list_date_is_set = p_row.getListDateIsSet();
    list_date_is_modified = p_row.getListDateIsModified();
    unlisted_date = p_row.getUnlistedDate();
    unlisted_date_is_set = p_row.getUnlistedDateIsSet();
    unlisted_date_is_modified = p_row.getUnlistedDateIsModified();
    exercise_stop = p_row.getExerciseStop();
    exercise_stop_is_set = p_row.getExerciseStopIsSet();
    exercise_stop_is_modified = p_row.getExerciseStopIsModified();
    actual_recieve_stop = p_row.getActualRecieveStop();
    actual_recieve_stop_is_set = p_row.getActualRecieveStopIsSet();
    actual_recieve_stop_is_modified = p_row.getActualRecieveStopIsModified();
    actual_delivary_stop = p_row.getActualDelivaryStop();
    actual_delivary_stop_is_set = p_row.getActualDelivaryStopIsSet();
    actual_delivary_stop_is_modified = p_row.getActualDelivaryStopIsModified();
    reserve_stop = p_row.getReserveStop();
    reserve_stop_is_set = p_row.getReserveStopIsSet();
    reserve_stop_is_modified = p_row.getReserveStopIsModified();
    if ( !p_row.getIndicationPriceIsNull() )
      indication_price = new Double( p_row.getIndicationPrice() );
    indication_price_is_set = p_row.getIndicationPriceIsSet();
    indication_price_is_modified = p_row.getIndicationPriceIsModified();
    if ( !p_row.getLastLiquidationPriceIsNull() )
      last_liquidation_price = new Double( p_row.getLastLiquidationPrice() );
    last_liquidation_price_is_set = p_row.getLastLiquidationPriceIsSet();
    last_liquidation_price_is_modified = p_row.getLastLiquidationPriceIsModified();
    if ( !p_row.getTargetSpotPriceIsNull() )
      target_spot_price = new Double( p_row.getTargetSpotPrice() );
    target_spot_price_is_set = p_row.getTargetSpotPriceIsSet();
    target_spot_price_is_modified = p_row.getTargetSpotPriceIsModified();
    if ( !p_row.getLiquidationPriceIsNull() )
      liquidation_price = new Double( p_row.getLiquidationPrice() );
    liquidation_price_is_set = p_row.getLiquidationPriceIsSet();
    liquidation_price_is_modified = p_row.getLiquidationPriceIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      valid_for_biz_date_is_set = true;
      valid_for_biz_date_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      unit_size_is_set = true;
      unit_size_is_modified = true;
      unit_margin_is_set = true;
      unit_margin_is_modified = true;
      per_order_max_units_is_set = true;
      per_order_max_units_is_modified = true;
      order_close_time_is_set = true;
      order_close_time_is_modified = true;
      last_closing_price_is_set = true;
      last_closing_price_is_modified = true;
      start_trading_date_is_set = true;
      start_trading_date_is_modified = true;
      last_trading_date_is_set = true;
      last_trading_date_is_modified = true;
      trade_stop_flag_is_set = true;
      trade_stop_flag_is_modified = true;
      buy_to_open_stop_flag_is_set = true;
      buy_to_open_stop_flag_is_modified = true;
      sell_to_open_stop_flag_is_set = true;
      sell_to_open_stop_flag_is_modified = true;
      sell_to_close_stop_flag_is_set = true;
      sell_to_close_stop_flag_is_modified = true;
      buy_to_close_stop_flag_is_set = true;
      buy_to_close_stop_flag_is_modified = true;
      list_flag_is_set = true;
      list_flag_is_modified = true;
      list_date_is_set = true;
      list_date_is_modified = true;
      unlisted_date_is_set = true;
      unlisted_date_is_modified = true;
      exercise_stop_is_set = true;
      exercise_stop_is_modified = true;
      actual_recieve_stop_is_set = true;
      actual_recieve_stop_is_modified = true;
      actual_delivary_stop_is_set = true;
      actual_delivary_stop_is_modified = true;
      reserve_stop_is_set = true;
      reserve_stop_is_modified = true;
      indication_price_is_set = true;
      indication_price_is_modified = true;
      last_liquidation_price_is_set = true;
      last_liquidation_price_is_modified = true;
      target_spot_price_is_set = true;
      target_spot_price_is_modified = true;
      liquidation_price_is_set = true;
      liquidation_price_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof IfoTradedProductRow ) )
       return false;
    return fieldsEqual( (IfoTradedProductRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のIfoTradedProductRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( IfoTradedProductRow row )
  {
    if ( traded_product_id != row.getTradedProductId() )
      return false;
    if ( valid_for_biz_date == null ) {
      if ( row.getValidForBizDate() != null )
        return false;
    } else if ( !valid_for_biz_date.equals( row.getValidForBizDate() ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( market_id != row.getMarketId() )
      return false;
    if ( product_id != row.getProductId() )
      return false;
    if ( unit_size != row.getUnitSize() )
      return false;
    if ( unit_margin != row.getUnitMargin() )
      return false;
    if ( per_order_max_units != row.getPerOrderMaxUnits() )
      return false;
    if ( order_close_time == null ) {
      if ( row.getOrderCloseTime() != null )
        return false;
    } else if ( !order_close_time.equals( row.getOrderCloseTime() ) ) {
        return false;
    }
    if ( last_closing_price == null ) {
      if ( !row.getLastClosingPriceIsNull() )
        return false;
    } else if ( row.getLastClosingPriceIsNull() || ( last_closing_price.doubleValue() != row.getLastClosingPrice() ) ) {
        return false;
    }
    if ( start_trading_date == null ) {
      if ( row.getStartTradingDate() != null )
        return false;
    } else if ( !start_trading_date.equals( row.getStartTradingDate() ) ) {
        return false;
    }
    if ( last_trading_date == null ) {
      if ( row.getLastTradingDate() != null )
        return false;
    } else if ( !last_trading_date.equals( row.getLastTradingDate() ) ) {
        return false;
    }
    if ( trade_stop_flag == null ) {
      if ( row.getTradeStopFlag() != null )
        return false;
    } else if ( !trade_stop_flag.equals( row.getTradeStopFlag() ) ) {
        return false;
    }
    if ( buy_to_open_stop_flag == null ) {
      if ( row.getBuyToOpenStopFlag() != null )
        return false;
    } else if ( !buy_to_open_stop_flag.equals( row.getBuyToOpenStopFlag() ) ) {
        return false;
    }
    if ( sell_to_open_stop_flag == null ) {
      if ( row.getSellToOpenStopFlag() != null )
        return false;
    } else if ( !sell_to_open_stop_flag.equals( row.getSellToOpenStopFlag() ) ) {
        return false;
    }
    if ( sell_to_close_stop_flag == null ) {
      if ( row.getSellToCloseStopFlag() != null )
        return false;
    } else if ( !sell_to_close_stop_flag.equals( row.getSellToCloseStopFlag() ) ) {
        return false;
    }
    if ( buy_to_close_stop_flag == null ) {
      if ( row.getBuyToCloseStopFlag() != null )
        return false;
    } else if ( !buy_to_close_stop_flag.equals( row.getBuyToCloseStopFlag() ) ) {
        return false;
    }
    if ( list_flag == null ) {
      if ( row.getListFlag() != null )
        return false;
    } else if ( !list_flag.equals( row.getListFlag() ) ) {
        return false;
    }
    if ( list_date == null ) {
      if ( row.getListDate() != null )
        return false;
    } else if ( !list_date.equals( row.getListDate() ) ) {
        return false;
    }
    if ( unlisted_date == null ) {
      if ( row.getUnlistedDate() != null )
        return false;
    } else if ( !unlisted_date.equals( row.getUnlistedDate() ) ) {
        return false;
    }
    if ( exercise_stop == null ) {
      if ( row.getExerciseStop() != null )
        return false;
    } else if ( !exercise_stop.equals( row.getExerciseStop() ) ) {
        return false;
    }
    if ( actual_recieve_stop == null ) {
      if ( row.getActualRecieveStop() != null )
        return false;
    } else if ( !actual_recieve_stop.equals( row.getActualRecieveStop() ) ) {
        return false;
    }
    if ( actual_delivary_stop == null ) {
      if ( row.getActualDelivaryStop() != null )
        return false;
    } else if ( !actual_delivary_stop.equals( row.getActualDelivaryStop() ) ) {
        return false;
    }
    if ( reserve_stop == null ) {
      if ( row.getReserveStop() != null )
        return false;
    } else if ( !reserve_stop.equals( row.getReserveStop() ) ) {
        return false;
    }
    if ( indication_price == null ) {
      if ( !row.getIndicationPriceIsNull() )
        return false;
    } else if ( row.getIndicationPriceIsNull() || ( indication_price.doubleValue() != row.getIndicationPrice() ) ) {
        return false;
    }
    if ( last_liquidation_price == null ) {
      if ( !row.getLastLiquidationPriceIsNull() )
        return false;
    } else if ( row.getLastLiquidationPriceIsNull() || ( last_liquidation_price.doubleValue() != row.getLastLiquidationPrice() ) ) {
        return false;
    }
    if ( target_spot_price == null ) {
      if ( !row.getTargetSpotPriceIsNull() )
        return false;
    } else if ( row.getTargetSpotPriceIsNull() || ( target_spot_price.doubleValue() != row.getTargetSpotPrice() ) ) {
        return false;
    }
    if ( liquidation_price == null ) {
      if ( !row.getLiquidationPriceIsNull() )
        return false;
    } else if ( row.getLiquidationPriceIsNull() || ( liquidation_price.doubleValue() != row.getLiquidationPrice() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_timestamp == null ) {
      if ( row.getLastUpdatedTimestamp() != null )
        return false;
    } else if ( !last_updated_timestamp.equals( row.getLastUpdatedTimestamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) traded_product_id)
        + (valid_for_biz_date!=null? valid_for_biz_date.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + ((int) market_id)
        + ((int) product_id)
        + ((int) unit_size)
        + ((int) unit_margin)
        + ((int) per_order_max_units)
        + (order_close_time!=null? order_close_time.hashCode(): 0) 
        + (last_closing_price!=null? last_closing_price.hashCode(): 0) 
        + (start_trading_date!=null? start_trading_date.hashCode(): 0) 
        + (last_trading_date!=null? last_trading_date.hashCode(): 0) 
        + (trade_stop_flag!=null? trade_stop_flag.hashCode(): 0) 
        + (buy_to_open_stop_flag!=null? buy_to_open_stop_flag.hashCode(): 0) 
        + (sell_to_open_stop_flag!=null? sell_to_open_stop_flag.hashCode(): 0) 
        + (sell_to_close_stop_flag!=null? sell_to_close_stop_flag.hashCode(): 0) 
        + (buy_to_close_stop_flag!=null? buy_to_close_stop_flag.hashCode(): 0) 
        + (list_flag!=null? list_flag.hashCode(): 0) 
        + (list_date!=null? list_date.hashCode(): 0) 
        + (unlisted_date!=null? unlisted_date.hashCode(): 0) 
        + (exercise_stop!=null? exercise_stop.hashCode(): 0) 
        + (actual_recieve_stop!=null? actual_recieve_stop.hashCode(): 0) 
        + (actual_delivary_stop!=null? actual_delivary_stop.hashCode(): 0) 
        + (reserve_stop!=null? reserve_stop.hashCode(): 0) 
        + (indication_price!=null? indication_price.hashCode(): 0) 
        + (last_liquidation_price!=null? last_liquidation_price.hashCode(): 0) 
        + (target_spot_price!=null? target_spot_price.hashCode(): 0) 
        + (liquidation_price!=null? liquidation_price.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !market_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_id' must be set before inserting.");
		if ( !product_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_id' must be set before inserting.");
		if ( !unit_size_is_set )
			throw new IllegalArgumentException("non-nullable field 'unit_size' must be set before inserting.");
		if ( !unit_margin_is_set )
			throw new IllegalArgumentException("non-nullable field 'unit_margin' must be set before inserting.");
		if ( !per_order_max_units_is_set )
			throw new IllegalArgumentException("non-nullable field 'per_order_max_units' must be set before inserting.");
		if ( !start_trading_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'start_trading_date' must be set before inserting.");
		if ( !last_trading_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_trading_date' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("traded_product_id",new Long(traded_product_id));
		if ( valid_for_biz_date != null )
			map.put("valid_for_biz_date",valid_for_biz_date);
		map.put("institution_code",institution_code);
		map.put("market_id",new Long(market_id));
		map.put("product_id",new Long(product_id));
		map.put("unit_size",new Double(unit_size));
		map.put("unit_margin",new Long(unit_margin));
		map.put("per_order_max_units",new Double(per_order_max_units));
		if ( order_close_time != null )
			map.put("order_close_time",order_close_time);
		if ( last_closing_price != null )
			map.put("last_closing_price",last_closing_price);
		map.put("start_trading_date",start_trading_date);
		map.put("last_trading_date",last_trading_date);
		if ( trade_stop_flag_is_set )
			map.put("trade_stop_flag",trade_stop_flag);
		if ( buy_to_open_stop_flag_is_set )
			map.put("buy_to_open_stop_flag",buy_to_open_stop_flag);
		if ( sell_to_open_stop_flag_is_set )
			map.put("sell_to_open_stop_flag",sell_to_open_stop_flag);
		if ( sell_to_close_stop_flag_is_set )
			map.put("sell_to_close_stop_flag",sell_to_close_stop_flag);
		if ( buy_to_close_stop_flag_is_set )
			map.put("buy_to_close_stop_flag",buy_to_close_stop_flag);
		if ( list_flag != null )
			map.put("list_flag",list_flag);
		if ( list_date != null )
			map.put("list_date",list_date);
		if ( unlisted_date != null )
			map.put("unlisted_date",unlisted_date);
		if ( exercise_stop != null )
			map.put("exercise_stop",exercise_stop);
		if ( actual_recieve_stop != null )
			map.put("actual_recieve_stop",actual_recieve_stop);
		if ( actual_delivary_stop != null )
			map.put("actual_delivary_stop",actual_delivary_stop);
		if ( reserve_stop != null )
			map.put("reserve_stop",reserve_stop);
		if ( indication_price != null )
			map.put("indication_price",indication_price);
		if ( last_liquidation_price != null )
			map.put("last_liquidation_price",last_liquidation_price);
		if ( target_spot_price != null )
			map.put("target_spot_price",target_spot_price);
		if ( liquidation_price != null )
			map.put("liquidation_price",liquidation_price);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( valid_for_biz_date_is_modified )
			map.put("valid_for_biz_date",valid_for_biz_date);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( market_id_is_modified )
			map.put("market_id",new Long(market_id));
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( unit_size_is_modified )
			map.put("unit_size",new Double(unit_size));
		if ( unit_margin_is_modified )
			map.put("unit_margin",new Long(unit_margin));
		if ( per_order_max_units_is_modified )
			map.put("per_order_max_units",new Double(per_order_max_units));
		if ( order_close_time_is_modified )
			map.put("order_close_time",order_close_time);
		if ( last_closing_price_is_modified )
			map.put("last_closing_price",last_closing_price);
		if ( start_trading_date_is_modified )
			map.put("start_trading_date",start_trading_date);
		if ( last_trading_date_is_modified )
			map.put("last_trading_date",last_trading_date);
		if ( trade_stop_flag_is_modified )
			map.put("trade_stop_flag",trade_stop_flag);
		if ( buy_to_open_stop_flag_is_modified )
			map.put("buy_to_open_stop_flag",buy_to_open_stop_flag);
		if ( sell_to_open_stop_flag_is_modified )
			map.put("sell_to_open_stop_flag",sell_to_open_stop_flag);
		if ( sell_to_close_stop_flag_is_modified )
			map.put("sell_to_close_stop_flag",sell_to_close_stop_flag);
		if ( buy_to_close_stop_flag_is_modified )
			map.put("buy_to_close_stop_flag",buy_to_close_stop_flag);
		if ( list_flag_is_modified )
			map.put("list_flag",list_flag);
		if ( list_date_is_modified )
			map.put("list_date",list_date);
		if ( unlisted_date_is_modified )
			map.put("unlisted_date",unlisted_date);
		if ( exercise_stop_is_modified )
			map.put("exercise_stop",exercise_stop);
		if ( actual_recieve_stop_is_modified )
			map.put("actual_recieve_stop",actual_recieve_stop);
		if ( actual_delivary_stop_is_modified )
			map.put("actual_delivary_stop",actual_delivary_stop);
		if ( reserve_stop_is_modified )
			map.put("reserve_stop",reserve_stop);
		if ( indication_price_is_modified )
			map.put("indication_price",indication_price);
		if ( last_liquidation_price_is_modified )
			map.put("last_liquidation_price",last_liquidation_price);
		if ( target_spot_price_is_modified )
			map.put("target_spot_price",target_spot_price);
		if ( liquidation_price_is_modified )
			map.put("liquidation_price",liquidation_price);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("valid_for_biz_date",valid_for_biz_date);
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( market_id_is_set )
				map.put("market_id",new Long(market_id));
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			if ( unit_size_is_set )
				map.put("unit_size",new Double(unit_size));
			if ( unit_margin_is_set )
				map.put("unit_margin",new Long(unit_margin));
			if ( per_order_max_units_is_set )
				map.put("per_order_max_units",new Double(per_order_max_units));
			map.put("order_close_time",order_close_time);
			map.put("last_closing_price",last_closing_price);
			if ( start_trading_date_is_set )
				map.put("start_trading_date",start_trading_date);
			if ( last_trading_date_is_set )
				map.put("last_trading_date",last_trading_date);
			if ( trade_stop_flag_is_set )
				map.put("trade_stop_flag",trade_stop_flag);
			if ( buy_to_open_stop_flag_is_set )
				map.put("buy_to_open_stop_flag",buy_to_open_stop_flag);
			if ( sell_to_open_stop_flag_is_set )
				map.put("sell_to_open_stop_flag",sell_to_open_stop_flag);
			if ( sell_to_close_stop_flag_is_set )
				map.put("sell_to_close_stop_flag",sell_to_close_stop_flag);
			if ( buy_to_close_stop_flag_is_set )
				map.put("buy_to_close_stop_flag",buy_to_close_stop_flag);
			map.put("list_flag",list_flag);
			map.put("list_date",list_date);
			map.put("unlisted_date",unlisted_date);
			map.put("exercise_stop",exercise_stop);
			map.put("actual_recieve_stop",actual_recieve_stop);
			map.put("actual_delivary_stop",actual_delivary_stop);
			map.put("reserve_stop",reserve_stop);
			map.put("indication_price",indication_price);
			map.put("last_liquidation_price",last_liquidation_price);
			map.put("target_spot_price",target_spot_price);
			map.put("liquidation_price",liquidation_price);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>traded_product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getTradedProductId()
  {
    return traded_product_id;
  }


  /** 
   * <em>traded_product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradedProductIdIsSet() {
    return traded_product_id_is_set;
  }


  /** 
   * <em>traded_product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradedProductIdIsModified() {
    return traded_product_id_is_modified;
  }


  /** 
   * <em>valid_for_biz_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getValidForBizDate()
  {
    return valid_for_biz_date;
  }


  /** 
   * <em>valid_for_biz_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidForBizDateIsSet() {
    return valid_for_biz_date_is_set;
  }


  /** 
   * <em>valid_for_biz_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidForBizDateIsModified() {
    return valid_for_biz_date_is_modified;
  }


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getInstitutionCode()
  {
    return institution_code;
  }


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsSet() {
    return institution_code_is_set;
  }


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInstitutionCodeIsModified() {
    return institution_code_is_modified;
  }


  /** 
   * <em>market_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarketId()
  {
    return market_id;
  }


  /** 
   * <em>market_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketIdIsSet() {
    return market_id_is_set;
  }


  /** 
   * <em>market_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketIdIsModified() {
    return market_id_is_modified;
  }


  /** 
   * <em>product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getProductId()
  {
    return product_id;
  }


  /** 
   * <em>product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsSet() {
    return product_id_is_set;
  }


  /** 
   * <em>product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsModified() {
    return product_id_is_modified;
  }


  /** 
   * <em>unit_size</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnitSize()
  {
    return unit_size;
  }


  /** 
   * <em>unit_size</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnitSizeIsSet() {
    return unit_size_is_set;
  }


  /** 
   * <em>unit_size</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnitSizeIsModified() {
    return unit_size_is_modified;
  }


  /** 
   * <em>unit_margin</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getUnitMargin()
  {
    return unit_margin;
  }


  /** 
   * <em>unit_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnitMarginIsSet() {
    return unit_margin_is_set;
  }


  /** 
   * <em>unit_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnitMarginIsModified() {
    return unit_margin_is_modified;
  }


  /** 
   * <em>per_order_max_units</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPerOrderMaxUnits()
  {
    return per_order_max_units;
  }


  /** 
   * <em>per_order_max_units</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPerOrderMaxUnitsIsSet() {
    return per_order_max_units_is_set;
  }


  /** 
   * <em>per_order_max_units</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPerOrderMaxUnitsIsModified() {
    return per_order_max_units_is_modified;
  }


  /** 
   * <em>order_close_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderCloseTime()
  {
    return order_close_time;
  }


  /** 
   * <em>order_close_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderCloseTimeIsSet() {
    return order_close_time_is_set;
  }


  /** 
   * <em>order_close_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderCloseTimeIsModified() {
    return order_close_time_is_modified;
  }


  /** 
   * <em>last_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLastClosingPrice()
  {
    return ( last_closing_price==null? ((double)0): last_closing_price.doubleValue() );
  }


  /** 
   * <em>last_closing_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLastClosingPriceIsNull()
  {
    return last_closing_price == null;
  }


  /** 
   * <em>last_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastClosingPriceIsSet() {
    return last_closing_price_is_set;
  }


  /** 
   * <em>last_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastClosingPriceIsModified() {
    return last_closing_price_is_modified;
  }


  /** 
   * <em>start_trading_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getStartTradingDate()
  {
    return start_trading_date;
  }


  /** 
   * <em>start_trading_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStartTradingDateIsSet() {
    return start_trading_date_is_set;
  }


  /** 
   * <em>start_trading_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStartTradingDateIsModified() {
    return start_trading_date_is_modified;
  }


  /** 
   * <em>last_trading_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastTradingDate()
  {
    return last_trading_date;
  }


  /** 
   * <em>last_trading_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastTradingDateIsSet() {
    return last_trading_date_is_set;
  }


  /** 
   * <em>last_trading_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastTradingDateIsModified() {
    return last_trading_date_is_modified;
  }


  /** 
   * <em>trade_stop_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getTradeStopFlag()
  {
    return trade_stop_flag;
  }


  /** 
   * <em>trade_stop_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeStopFlagIsSet() {
    return trade_stop_flag_is_set;
  }


  /** 
   * <em>trade_stop_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeStopFlagIsModified() {
    return trade_stop_flag_is_modified;
  }


  /** 
   * <em>buy_to_open_stop_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getBuyToOpenStopFlag()
  {
    return buy_to_open_stop_flag;
  }


  /** 
   * <em>buy_to_open_stop_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyToOpenStopFlagIsSet() {
    return buy_to_open_stop_flag_is_set;
  }


  /** 
   * <em>buy_to_open_stop_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyToOpenStopFlagIsModified() {
    return buy_to_open_stop_flag_is_modified;
  }


  /** 
   * <em>sell_to_open_stop_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getSellToOpenStopFlag()
  {
    return sell_to_open_stop_flag;
  }


  /** 
   * <em>sell_to_open_stop_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellToOpenStopFlagIsSet() {
    return sell_to_open_stop_flag_is_set;
  }


  /** 
   * <em>sell_to_open_stop_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellToOpenStopFlagIsModified() {
    return sell_to_open_stop_flag_is_modified;
  }


  /** 
   * <em>sell_to_close_stop_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getSellToCloseStopFlag()
  {
    return sell_to_close_stop_flag;
  }


  /** 
   * <em>sell_to_close_stop_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellToCloseStopFlagIsSet() {
    return sell_to_close_stop_flag_is_set;
  }


  /** 
   * <em>sell_to_close_stop_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellToCloseStopFlagIsModified() {
    return sell_to_close_stop_flag_is_modified;
  }


  /** 
   * <em>buy_to_close_stop_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getBuyToCloseStopFlag()
  {
    return buy_to_close_stop_flag;
  }


  /** 
   * <em>buy_to_close_stop_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyToCloseStopFlagIsSet() {
    return buy_to_close_stop_flag_is_set;
  }


  /** 
   * <em>buy_to_close_stop_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyToCloseStopFlagIsModified() {
    return buy_to_close_stop_flag_is_modified;
  }


  /** 
   * <em>list_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getListFlag()
  {
    return list_flag;
  }


  /** 
   * <em>list_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getListFlagIsSet() {
    return list_flag_is_set;
  }


  /** 
   * <em>list_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getListFlagIsModified() {
    return list_flag_is_modified;
  }


  /** 
   * <em>list_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getListDate()
  {
    return list_date;
  }


  /** 
   * <em>list_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getListDateIsSet() {
    return list_date_is_set;
  }


  /** 
   * <em>list_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getListDateIsModified() {
    return list_date_is_modified;
  }


  /** 
   * <em>unlisted_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getUnlistedDate()
  {
    return unlisted_date;
  }


  /** 
   * <em>unlisted_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnlistedDateIsSet() {
    return unlisted_date_is_set;
  }


  /** 
   * <em>unlisted_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnlistedDateIsModified() {
    return unlisted_date_is_modified;
  }


  /** 
   * <em>exercise_stop</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExerciseStop()
  {
    return exercise_stop;
  }


  /** 
   * <em>exercise_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExerciseStopIsSet() {
    return exercise_stop_is_set;
  }


  /** 
   * <em>exercise_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExerciseStopIsModified() {
    return exercise_stop_is_modified;
  }


  /** 
   * <em>actual_recieve_stop</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getActualRecieveStop()
  {
    return actual_recieve_stop;
  }


  /** 
   * <em>actual_recieve_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualRecieveStopIsSet() {
    return actual_recieve_stop_is_set;
  }


  /** 
   * <em>actual_recieve_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualRecieveStopIsModified() {
    return actual_recieve_stop_is_modified;
  }


  /** 
   * <em>actual_delivary_stop</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getActualDelivaryStop()
  {
    return actual_delivary_stop;
  }


  /** 
   * <em>actual_delivary_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualDelivaryStopIsSet() {
    return actual_delivary_stop_is_set;
  }


  /** 
   * <em>actual_delivary_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualDelivaryStopIsModified() {
    return actual_delivary_stop_is_modified;
  }


  /** 
   * <em>reserve_stop</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getReserveStop()
  {
    return reserve_stop;
  }


  /** 
   * <em>reserve_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReserveStopIsSet() {
    return reserve_stop_is_set;
  }


  /** 
   * <em>reserve_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReserveStopIsModified() {
    return reserve_stop_is_modified;
  }


  /** 
   * <em>indication_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getIndicationPrice()
  {
    return ( indication_price==null? ((double)0): indication_price.doubleValue() );
  }


  /** 
   * <em>indication_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getIndicationPriceIsNull()
  {
    return indication_price == null;
  }


  /** 
   * <em>indication_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIndicationPriceIsSet() {
    return indication_price_is_set;
  }


  /** 
   * <em>indication_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIndicationPriceIsModified() {
    return indication_price_is_modified;
  }


  /** 
   * <em>last_liquidation_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLastLiquidationPrice()
  {
    return ( last_liquidation_price==null? ((double)0): last_liquidation_price.doubleValue() );
  }


  /** 
   * <em>last_liquidation_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLastLiquidationPriceIsNull()
  {
    return last_liquidation_price == null;
  }


  /** 
   * <em>last_liquidation_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastLiquidationPriceIsSet() {
    return last_liquidation_price_is_set;
  }


  /** 
   * <em>last_liquidation_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastLiquidationPriceIsModified() {
    return last_liquidation_price_is_modified;
  }


  /** 
   * <em>target_spot_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTargetSpotPrice()
  {
    return ( target_spot_price==null? ((double)0): target_spot_price.doubleValue() );
  }


  /** 
   * <em>target_spot_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTargetSpotPriceIsNull()
  {
    return target_spot_price == null;
  }


  /** 
   * <em>target_spot_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTargetSpotPriceIsSet() {
    return target_spot_price_is_set;
  }


  /** 
   * <em>target_spot_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTargetSpotPriceIsModified() {
    return target_spot_price_is_modified;
  }


  /** 
   * <em>liquidation_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLiquidationPrice()
  {
    return ( liquidation_price==null? ((double)0): liquidation_price.doubleValue() );
  }


  /** 
   * <em>liquidation_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLiquidationPriceIsNull()
  {
    return liquidation_price == null;
  }


  /** 
   * <em>liquidation_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLiquidationPriceIsSet() {
    return liquidation_price_is_set;
  }


  /** 
   * <em>liquidation_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLiquidationPriceIsModified() {
    return liquidation_price_is_modified;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdatedTimestampIsModified() {
    return last_updated_timestamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new IfoTradedProductPK(traded_product_id);
  }


  /** 
   * <em>traded_product_id</em>カラムの値を設定します。 
   *
   * @@param p_tradedProductId <em>traded_product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setTradedProductId( long p_tradedProductId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    traded_product_id = p_tradedProductId;
    traded_product_id_is_set = true;
    traded_product_id_is_modified = true;
  }


  /** 
   * <em>valid_for_biz_date</em>カラムの値を設定します。 
   *
   * @@param p_validForBizDate <em>valid_for_biz_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setValidForBizDate( String p_validForBizDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    valid_for_biz_date = p_validForBizDate;
    valid_for_biz_date_is_set = true;
    valid_for_biz_date_is_modified = true;
  }


  /** 
   * <em>institution_code</em>カラムの値を設定します。 
   *
   * @@param p_institutionCode <em>institution_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setInstitutionCode( String p_institutionCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    institution_code = p_institutionCode;
    institution_code_is_set = true;
    institution_code_is_modified = true;
  }


  /** 
   * <em>market_id</em>カラムの値を設定します。 
   *
   * @@param p_marketId <em>market_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setMarketId( long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = p_marketId;
    market_id_is_set = true;
    market_id_is_modified = true;
  }


  /** 
   * <em>product_id</em>カラムの値を設定します。 
   *
   * @@param p_productId <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setProductId( long p_productId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_id = p_productId;
    product_id_is_set = true;
    product_id_is_modified = true;
  }


  /** 
   * <em>unit_size</em>カラムの値を設定します。 
   *
   * @@param p_unitSize <em>unit_size</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnitSize( double p_unitSize )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unit_size = p_unitSize;
    unit_size_is_set = true;
    unit_size_is_modified = true;
  }


  /** 
   * <em>unit_margin</em>カラムの値を設定します。 
   *
   * @@param p_unitMargin <em>unit_margin</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setUnitMargin( long p_unitMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unit_margin = p_unitMargin;
    unit_margin_is_set = true;
    unit_margin_is_modified = true;
  }


  /** 
   * <em>per_order_max_units</em>カラムの値を設定します。 
   *
   * @@param p_perOrderMaxUnits <em>per_order_max_units</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPerOrderMaxUnits( double p_perOrderMaxUnits )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    per_order_max_units = p_perOrderMaxUnits;
    per_order_max_units_is_set = true;
    per_order_max_units_is_modified = true;
  }


  /** 
   * <em>order_close_time</em>カラムの値を設定します。 
   *
   * @@param p_orderCloseTime <em>order_close_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setOrderCloseTime( String p_orderCloseTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_close_time = p_orderCloseTime;
    order_close_time_is_set = true;
    order_close_time_is_modified = true;
  }


  /** 
   * <em>last_closing_price</em>カラムの値を設定します。 
   *
   * @@param p_lastClosingPrice <em>last_closing_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLastClosingPrice( double p_lastClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_closing_price = new Double(p_lastClosingPrice);
    last_closing_price_is_set = true;
    last_closing_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>last_closing_price</em>カラムに値を設定します。 
   */
  public final void setLastClosingPrice( Double p_lastClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_closing_price = p_lastClosingPrice;
    last_closing_price_is_set = true;
    last_closing_price_is_modified = true;
  }


  /** 
   * <em>start_trading_date</em>カラムの値を設定します。 
   *
   * @@param p_startTradingDate <em>start_trading_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setStartTradingDate( java.sql.Timestamp p_startTradingDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    start_trading_date = p_startTradingDate;
    start_trading_date_is_set = true;
    start_trading_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>start_trading_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setStartTradingDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    start_trading_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    start_trading_date_is_set = true;
    start_trading_date_is_modified = true;
  }


  /** 
   * <em>last_trading_date</em>カラムの値を設定します。 
   *
   * @@param p_lastTradingDate <em>last_trading_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastTradingDate( java.sql.Timestamp p_lastTradingDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_trading_date = p_lastTradingDate;
    last_trading_date_is_set = true;
    last_trading_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_trading_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastTradingDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_trading_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_trading_date_is_set = true;
    last_trading_date_is_modified = true;
  }


  /** 
   * <em>trade_stop_flag</em>カラムの値を設定します。 
   *
   * @@param p_tradeStopFlag <em>trade_stop_flag</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setTradeStopFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_tradeStopFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_stop_flag = p_tradeStopFlag;
    trade_stop_flag_is_set = true;
    trade_stop_flag_is_modified = true;
  }


  /** 
   * <em>buy_to_open_stop_flag</em>カラムの値を設定します。 
   *
   * @@param p_buyToOpenStopFlag <em>buy_to_open_stop_flag</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setBuyToOpenStopFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_buyToOpenStopFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_to_open_stop_flag = p_buyToOpenStopFlag;
    buy_to_open_stop_flag_is_set = true;
    buy_to_open_stop_flag_is_modified = true;
  }


  /** 
   * <em>sell_to_open_stop_flag</em>カラムの値を設定します。 
   *
   * @@param p_sellToOpenStopFlag <em>sell_to_open_stop_flag</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setSellToOpenStopFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_sellToOpenStopFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_to_open_stop_flag = p_sellToOpenStopFlag;
    sell_to_open_stop_flag_is_set = true;
    sell_to_open_stop_flag_is_modified = true;
  }


  /** 
   * <em>sell_to_close_stop_flag</em>カラムの値を設定します。 
   *
   * @@param p_sellToCloseStopFlag <em>sell_to_close_stop_flag</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setSellToCloseStopFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_sellToCloseStopFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_to_close_stop_flag = p_sellToCloseStopFlag;
    sell_to_close_stop_flag_is_set = true;
    sell_to_close_stop_flag_is_modified = true;
  }


  /** 
   * <em>buy_to_close_stop_flag</em>カラムの値を設定します。 
   *
   * @@param p_buyToCloseStopFlag <em>buy_to_close_stop_flag</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setBuyToCloseStopFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_buyToCloseStopFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_to_close_stop_flag = p_buyToCloseStopFlag;
    buy_to_close_stop_flag_is_set = true;
    buy_to_close_stop_flag_is_modified = true;
  }


  /** 
   * <em>list_flag</em>カラムの値を設定します。 
   *
   * @@param p_listFlag <em>list_flag</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setListFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_listFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    list_flag = p_listFlag;
    list_flag_is_set = true;
    list_flag_is_modified = true;
  }


  /** 
   * <em>list_date</em>カラムの値を設定します。 
   *
   * @@param p_listDate <em>list_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setListDate( java.sql.Timestamp p_listDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    list_date = p_listDate;
    list_date_is_set = true;
    list_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>list_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setListDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    list_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    list_date_is_set = true;
    list_date_is_modified = true;
  }


  /** 
   * <em>unlisted_date</em>カラムの値を設定します。 
   *
   * @@param p_unlistedDate <em>unlisted_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setUnlistedDate( java.sql.Timestamp p_unlistedDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unlisted_date = p_unlistedDate;
    unlisted_date_is_set = true;
    unlisted_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>unlisted_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setUnlistedDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unlisted_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    unlisted_date_is_set = true;
    unlisted_date_is_modified = true;
  }


  /** 
   * <em>exercise_stop</em>カラムの値を設定します。 
   *
   * @@param p_exerciseStop <em>exercise_stop</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setExerciseStop( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_exerciseStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exercise_stop = p_exerciseStop;
    exercise_stop_is_set = true;
    exercise_stop_is_modified = true;
  }


  /** 
   * <em>actual_recieve_stop</em>カラムの値を設定します。 
   *
   * @@param p_actualRecieveStop <em>actual_recieve_stop</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setActualRecieveStop( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_actualRecieveStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    actual_recieve_stop = p_actualRecieveStop;
    actual_recieve_stop_is_set = true;
    actual_recieve_stop_is_modified = true;
  }


  /** 
   * <em>actual_delivary_stop</em>カラムの値を設定します。 
   *
   * @@param p_actualDelivaryStop <em>actual_delivary_stop</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setActualDelivaryStop( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_actualDelivaryStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    actual_delivary_stop = p_actualDelivaryStop;
    actual_delivary_stop_is_set = true;
    actual_delivary_stop_is_modified = true;
  }


  /** 
   * <em>reserve_stop</em>カラムの値を設定します。 
   *
   * @@param p_reserveStop <em>reserve_stop</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setReserveStop( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_reserveStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    reserve_stop = p_reserveStop;
    reserve_stop_is_set = true;
    reserve_stop_is_modified = true;
  }


  /** 
   * <em>indication_price</em>カラムの値を設定します。 
   *
   * @@param p_indicationPrice <em>indication_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setIndicationPrice( double p_indicationPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    indication_price = new Double(p_indicationPrice);
    indication_price_is_set = true;
    indication_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>indication_price</em>カラムに値を設定します。 
   */
  public final void setIndicationPrice( Double p_indicationPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    indication_price = p_indicationPrice;
    indication_price_is_set = true;
    indication_price_is_modified = true;
  }


  /** 
   * <em>last_liquidation_price</em>カラムの値を設定します。 
   *
   * @@param p_lastLiquidationPrice <em>last_liquidation_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLastLiquidationPrice( double p_lastLiquidationPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_liquidation_price = new Double(p_lastLiquidationPrice);
    last_liquidation_price_is_set = true;
    last_liquidation_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>last_liquidation_price</em>カラムに値を設定します。 
   */
  public final void setLastLiquidationPrice( Double p_lastLiquidationPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_liquidation_price = p_lastLiquidationPrice;
    last_liquidation_price_is_set = true;
    last_liquidation_price_is_modified = true;
  }


  /** 
   * <em>target_spot_price</em>カラムの値を設定します。 
   *
   * @@param p_targetSpotPrice <em>target_spot_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTargetSpotPrice( double p_targetSpotPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    target_spot_price = new Double(p_targetSpotPrice);
    target_spot_price_is_set = true;
    target_spot_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>target_spot_price</em>カラムに値を設定します。 
   */
  public final void setTargetSpotPrice( Double p_targetSpotPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    target_spot_price = p_targetSpotPrice;
    target_spot_price_is_set = true;
    target_spot_price_is_modified = true;
  }


  /** 
   * <em>liquidation_price</em>カラムの値を設定します。 
   *
   * @@param p_liquidationPrice <em>liquidation_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLiquidationPrice( double p_liquidationPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    liquidation_price = new Double(p_liquidationPrice);
    liquidation_price_is_set = true;
    liquidation_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>liquidation_price</em>カラムに値を設定します。 
   */
  public final void setLiquidationPrice( Double p_liquidationPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    liquidation_price = p_liquidationPrice;
    liquidation_price_is_set = true;
    liquidation_price_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>created_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>last_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("actual_recieve_stop") ) {
                    return this.actual_recieve_stop;
                }
                else if ( name.equals("actual_delivary_stop") ) {
                    return this.actual_delivary_stop;
                }
                break;
            case 'b':
                if ( name.equals("buy_to_open_stop_flag") ) {
                    return this.buy_to_open_stop_flag;
                }
                else if ( name.equals("buy_to_close_stop_flag") ) {
                    return this.buy_to_close_stop_flag;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("exercise_stop") ) {
                    return this.exercise_stop;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("indication_price") ) {
                    return this.indication_price;
                }
                break;
            case 'l':
                if ( name.equals("last_closing_price") ) {
                    return this.last_closing_price;
                }
                else if ( name.equals("last_trading_date") ) {
                    return this.last_trading_date;
                }
                else if ( name.equals("list_flag") ) {
                    return this.list_flag;
                }
                else if ( name.equals("list_date") ) {
                    return this.list_date;
                }
                else if ( name.equals("last_liquidation_price") ) {
                    return this.last_liquidation_price;
                }
                else if ( name.equals("liquidation_price") ) {
                    return this.liquidation_price;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return new Long( market_id );
                }
                break;
            case 'o':
                if ( name.equals("order_close_time") ) {
                    return this.order_close_time;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                else if ( name.equals("per_order_max_units") ) {
                    return new Double( per_order_max_units );
                }
                break;
            case 'r':
                if ( name.equals("reserve_stop") ) {
                    return this.reserve_stop;
                }
                break;
            case 's':
                if ( name.equals("start_trading_date") ) {
                    return this.start_trading_date;
                }
                else if ( name.equals("sell_to_open_stop_flag") ) {
                    return this.sell_to_open_stop_flag;
                }
                else if ( name.equals("sell_to_close_stop_flag") ) {
                    return this.sell_to_close_stop_flag;
                }
                break;
            case 't':
                if ( name.equals("traded_product_id") ) {
                    return new Long( traded_product_id );
                }
                else if ( name.equals("trade_stop_flag") ) {
                    return this.trade_stop_flag;
                }
                else if ( name.equals("target_spot_price") ) {
                    return this.target_spot_price;
                }
                break;
            case 'u':
                if ( name.equals("unit_size") ) {
                    return new Double( unit_size );
                }
                else if ( name.equals("unit_margin") ) {
                    return new Long( unit_margin );
                }
                else if ( name.equals("unlisted_date") ) {
                    return this.unlisted_date;
                }
                break;
            case 'v':
                if ( name.equals("valid_for_biz_date") ) {
                    return this.valid_for_biz_date;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }


  /** 
   * @@see com.fitechlabs.dbind.Params#setColumn(String, Object) 
   */
    public void setColumn( String name, Object value ) {
        if(!mutable())
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("actual_recieve_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'actual_recieve_stop' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.actual_recieve_stop = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.actual_recieve_stop_is_set)
                        this.actual_recieve_stop_is_modified = true;
                    this.actual_recieve_stop_is_set = true;
                    return;
                }
                else if ( name.equals("actual_delivary_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'actual_delivary_stop' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.actual_delivary_stop = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.actual_delivary_stop_is_set)
                        this.actual_delivary_stop_is_modified = true;
                    this.actual_delivary_stop_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("buy_to_open_stop_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'buy_to_open_stop_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.buy_to_open_stop_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.buy_to_open_stop_flag_is_set)
                        this.buy_to_open_stop_flag_is_modified = true;
                    this.buy_to_open_stop_flag_is_set = true;
                    return;
                }
                else if ( name.equals("buy_to_close_stop_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'buy_to_close_stop_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.buy_to_close_stop_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.buy_to_close_stop_flag_is_set)
                        this.buy_to_close_stop_flag_is_modified = true;
                    this.buy_to_close_stop_flag_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("exercise_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'exercise_stop' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.exercise_stop = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.exercise_stop_is_set)
                        this.exercise_stop_is_modified = true;
                    this.exercise_stop_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'institution_code' must be String: '"+value+"' is not." );
                    this.institution_code = (String) value;
                    if (this.institution_code_is_set)
                        this.institution_code_is_modified = true;
                    this.institution_code_is_set = true;
                    return;
                }
                else if ( name.equals("indication_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'indication_price' must be Double: '"+value+"' is not." );
                    this.indication_price = (Double) value;
                    if (this.indication_price_is_set)
                        this.indication_price_is_modified = true;
                    this.indication_price_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_closing_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'last_closing_price' must be Double: '"+value+"' is not." );
                    this.last_closing_price = (Double) value;
                    if (this.last_closing_price_is_set)
                        this.last_closing_price_is_modified = true;
                    this.last_closing_price_is_set = true;
                    return;
                }
                else if ( name.equals("last_trading_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_trading_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_trading_date = (java.sql.Timestamp) value;
                    if (this.last_trading_date_is_set)
                        this.last_trading_date_is_modified = true;
                    this.last_trading_date_is_set = true;
                    return;
                }
                else if ( name.equals("list_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'list_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.list_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.list_flag_is_set)
                        this.list_flag_is_modified = true;
                    this.list_flag_is_set = true;
                    return;
                }
                else if ( name.equals("list_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'list_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.list_date = (java.sql.Timestamp) value;
                    if (this.list_date_is_set)
                        this.list_date_is_modified = true;
                    this.list_date_is_set = true;
                    return;
                }
                else if ( name.equals("last_liquidation_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'last_liquidation_price' must be Double: '"+value+"' is not." );
                    this.last_liquidation_price = (Double) value;
                    if (this.last_liquidation_price_is_set)
                        this.last_liquidation_price_is_modified = true;
                    this.last_liquidation_price_is_set = true;
                    return;
                }
                else if ( name.equals("liquidation_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'liquidation_price' must be Double: '"+value+"' is not." );
                    this.liquidation_price = (Double) value;
                    if (this.liquidation_price_is_set)
                        this.liquidation_price_is_modified = true;
                    this.liquidation_price_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_id' must be Long: '"+value+"' is not." );
                    this.market_id = ((Long) value).longValue();
                    if (this.market_id_is_set)
                        this.market_id_is_modified = true;
                    this.market_id_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("order_close_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_close_time' must be String: '"+value+"' is not." );
                    this.order_close_time = (String) value;
                    if (this.order_close_time_is_set)
                        this.order_close_time_is_modified = true;
                    this.order_close_time_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'product_id' must be Long: '"+value+"' is not." );
                    this.product_id = ((Long) value).longValue();
                    if (this.product_id_is_set)
                        this.product_id_is_modified = true;
                    this.product_id_is_set = true;
                    return;
                }
                else if ( name.equals("per_order_max_units") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'per_order_max_units' must be Double: '"+value+"' is not." );
                    this.per_order_max_units = ((Double) value).doubleValue();
                    if (this.per_order_max_units_is_set)
                        this.per_order_max_units_is_modified = true;
                    this.per_order_max_units_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("reserve_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'reserve_stop' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.reserve_stop = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.reserve_stop_is_set)
                        this.reserve_stop_is_modified = true;
                    this.reserve_stop_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("start_trading_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'start_trading_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.start_trading_date = (java.sql.Timestamp) value;
                    if (this.start_trading_date_is_set)
                        this.start_trading_date_is_modified = true;
                    this.start_trading_date_is_set = true;
                    return;
                }
                else if ( name.equals("sell_to_open_stop_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'sell_to_open_stop_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.sell_to_open_stop_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.sell_to_open_stop_flag_is_set)
                        this.sell_to_open_stop_flag_is_modified = true;
                    this.sell_to_open_stop_flag_is_set = true;
                    return;
                }
                else if ( name.equals("sell_to_close_stop_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'sell_to_close_stop_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.sell_to_close_stop_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.sell_to_close_stop_flag_is_set)
                        this.sell_to_close_stop_flag_is_modified = true;
                    this.sell_to_close_stop_flag_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("traded_product_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'traded_product_id' must be Long: '"+value+"' is not." );
                    this.traded_product_id = ((Long) value).longValue();
                    if (this.traded_product_id_is_set)
                        this.traded_product_id_is_modified = true;
                    this.traded_product_id_is_set = true;
                    return;
                }
                else if ( name.equals("trade_stop_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'trade_stop_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.trade_stop_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.trade_stop_flag_is_set)
                        this.trade_stop_flag_is_modified = true;
                    this.trade_stop_flag_is_set = true;
                    return;
                }
                else if ( name.equals("target_spot_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'target_spot_price' must be Double: '"+value+"' is not." );
                    this.target_spot_price = (Double) value;
                    if (this.target_spot_price_is_set)
                        this.target_spot_price_is_modified = true;
                    this.target_spot_price_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("unit_size") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unit_size' must be Double: '"+value+"' is not." );
                    this.unit_size = ((Double) value).doubleValue();
                    if (this.unit_size_is_set)
                        this.unit_size_is_modified = true;
                    this.unit_size_is_set = true;
                    return;
                }
                else if ( name.equals("unit_margin") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'unit_margin' must be Long: '"+value+"' is not." );
                    this.unit_margin = ((Long) value).longValue();
                    if (this.unit_margin_is_set)
                        this.unit_margin_is_modified = true;
                    this.unit_margin_is_set = true;
                    return;
                }
                else if ( name.equals("unlisted_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'unlisted_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.unlisted_date = (java.sql.Timestamp) value;
                    if (this.unlisted_date_is_set)
                        this.unlisted_date_is_modified = true;
                    this.unlisted_date_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("valid_for_biz_date") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'valid_for_biz_date' must be String: '"+value+"' is not." );
                    this.valid_for_biz_date = (String) value;
                    if (this.valid_for_biz_date_is_set)
                        this.valid_for_biz_date_is_modified = true;
                    this.valid_for_biz_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
