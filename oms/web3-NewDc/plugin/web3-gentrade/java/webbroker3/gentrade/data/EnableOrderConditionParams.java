head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.32.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EnableOrderConditionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * enable_order_conditionテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link EnableOrderConditionRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link EnableOrderConditionParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link EnableOrderConditionParams}が{@@link EnableOrderConditionRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see EnableOrderConditionPK 
 * @@see EnableOrderConditionRow 
 */
public class EnableOrderConditionParams extends Params implements EnableOrderConditionRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "enable_order_condition";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = EnableOrderConditionRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return EnableOrderConditionRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>product_type</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum  product_type;

  /** 
   * <em>future_option_div</em>カラムの値 
   */
  public  String  future_option_div;

  /** 
   * <em>margin_trading_div</em>カラムの値 
   */
  public  String  margin_trading_div;

  /** 
   * <em>market_code</em>カラムの値 
   */
  public  String  market_code;

  /** 
   * <em>carried_order</em>カラムの値 
   */
  public  String  carried_order;

  /** 
   * <em>carried_order_lapse_date_spec</em>カラムの値 
   */
  public  String  carried_order_lapse_date_spec;

  /** 
   * <em>carried_order_day_count</em>カラムの値 
   */
  public  Integer  carried_order_day_count;

  /** 
   * <em>mart_price_buy_to_open</em>カラムの値 
   */
  public  String  mart_price_buy_to_open;

  /** 
   * <em>mart_price_sell_to_open</em>カラムの値 
   */
  public  String  mart_price_sell_to_open;

  /** 
   * <em>mart_price_order_settle_cont</em>カラムの値 
   */
  public  String  mart_price_order_settle_cont;

  /** 
   * <em>current_price_order</em>カラムの値 
   */
  public  String  current_price_order;

  /** 
   * <em>ease_current_price_order</em>カラムの値 
   */
  public  String  ease_current_price_order;

  /** 
   * <em>market_price_rest_limit_price</em>カラムの値 
   */
  public  String  market_price_rest_limit_price;

  /** 
   * <em>market_price_rest_cancel</em>カラムの値 
   */
  public  String  market_price_rest_cancel;

  /** 
   * <em>at_market_open</em>カラムの値 
   */
  public  String  at_market_open;

  /** 
   * <em>at_market_close</em>カラムの値 
   */
  public  String  at_market_close;

  /** 
   * <em>no_exec_at_mart_close</em>カラムの値 
   */
  public  String  no_exec_at_mart_close;

  /** 
   * <em>stop_order</em>カラムの値 
   */
  public  String  stop_order;

  /** 
   * <em>w_limit_order</em>カラムの値 
   */
  public  String  w_limit_order;

  /** 
   * <em>chain_order</em>カラムの値 
   */
  public  String  chain_order;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>evening_session_order</em>カラムの値 
   */
  public  String  evening_session_order;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_type_is_set = false;
  private boolean product_type_is_modified = false;
  private boolean future_option_div_is_set = false;
  private boolean future_option_div_is_modified = false;
  private boolean margin_trading_div_is_set = false;
  private boolean margin_trading_div_is_modified = false;
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean carried_order_is_set = false;
  private boolean carried_order_is_modified = false;
  private boolean carried_order_lapse_date_spec_is_set = false;
  private boolean carried_order_lapse_date_spec_is_modified = false;
  private boolean carried_order_day_count_is_set = false;
  private boolean carried_order_day_count_is_modified = false;
  private boolean mart_price_buy_to_open_is_set = false;
  private boolean mart_price_buy_to_open_is_modified = false;
  private boolean mart_price_sell_to_open_is_set = false;
  private boolean mart_price_sell_to_open_is_modified = false;
  private boolean mart_price_order_settle_cont_is_set = false;
  private boolean mart_price_order_settle_cont_is_modified = false;
  private boolean current_price_order_is_set = false;
  private boolean current_price_order_is_modified = false;
  private boolean ease_current_price_order_is_set = false;
  private boolean ease_current_price_order_is_modified = false;
  private boolean market_price_rest_limit_price_is_set = false;
  private boolean market_price_rest_limit_price_is_modified = false;
  private boolean market_price_rest_cancel_is_set = false;
  private boolean market_price_rest_cancel_is_modified = false;
  private boolean at_market_open_is_set = false;
  private boolean at_market_open_is_modified = false;
  private boolean at_market_close_is_set = false;
  private boolean at_market_close_is_modified = false;
  private boolean no_exec_at_mart_close_is_set = false;
  private boolean no_exec_at_mart_close_is_modified = false;
  private boolean stop_order_is_set = false;
  private boolean stop_order_is_modified = false;
  private boolean w_limit_order_is_set = false;
  private boolean w_limit_order_is_modified = false;
  private boolean chain_order_is_set = false;
  private boolean chain_order_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean evening_session_order_is_set = false;
  private boolean evening_session_order_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "product_type=" + product_type
      + "," + "future_option_div=" + future_option_div
      + "," + "margin_trading_div=" + margin_trading_div
      + "," + "market_code=" + market_code
      + "," + "carried_order=" +carried_order
      + "," + "carried_order_lapse_date_spec=" +carried_order_lapse_date_spec
      + "," + "carried_order_day_count=" +carried_order_day_count
      + "," + "mart_price_buy_to_open=" +mart_price_buy_to_open
      + "," + "mart_price_sell_to_open=" +mart_price_sell_to_open
      + "," + "mart_price_order_settle_cont=" +mart_price_order_settle_cont
      + "," + "current_price_order=" +current_price_order
      + "," + "ease_current_price_order=" +ease_current_price_order
      + "," + "market_price_rest_limit_price=" +market_price_rest_limit_price
      + "," + "market_price_rest_cancel=" +market_price_rest_cancel
      + "," + "at_market_open=" +at_market_open
      + "," + "at_market_close=" +at_market_close
      + "," + "no_exec_at_mart_close=" +no_exec_at_mart_close
      + "," + "stop_order=" +stop_order
      + "," + "w_limit_order=" +w_limit_order
      + "," + "chain_order=" +chain_order
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "evening_session_order=" +evening_session_order
      + "}";
  }


  /** 
   * 値が未設定のEnableOrderConditionParamsオブジェクトを作成します。 
   */
  public EnableOrderConditionParams() {
    institution_code_is_modified = true;
    product_type_is_modified = true;
    future_option_div_is_modified = true;
    margin_trading_div_is_modified = true;
    market_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のEnableOrderConditionRowオブジェクトの値を利用してEnableOrderConditionParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するEnableOrderConditionRowオブジェクト 
   */
  public EnableOrderConditionParams( EnableOrderConditionRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    product_type = p_row.getProductType();
    product_type_is_set = p_row.getProductTypeIsSet();
    product_type_is_modified = p_row.getProductTypeIsModified();
    future_option_div = p_row.getFutureOptionDiv();
    future_option_div_is_set = p_row.getFutureOptionDivIsSet();
    future_option_div_is_modified = p_row.getFutureOptionDivIsModified();
    margin_trading_div = p_row.getMarginTradingDiv();
    margin_trading_div_is_set = p_row.getMarginTradingDivIsSet();
    margin_trading_div_is_modified = p_row.getMarginTradingDivIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    carried_order = p_row.getCarriedOrder();
    carried_order_is_set = p_row.getCarriedOrderIsSet();
    carried_order_is_modified = p_row.getCarriedOrderIsModified();
    carried_order_lapse_date_spec = p_row.getCarriedOrderLapseDateSpec();
    carried_order_lapse_date_spec_is_set = p_row.getCarriedOrderLapseDateSpecIsSet();
    carried_order_lapse_date_spec_is_modified = p_row.getCarriedOrderLapseDateSpecIsModified();
    if ( !p_row.getCarriedOrderDayCountIsNull() )
      carried_order_day_count = new Integer( p_row.getCarriedOrderDayCount() );
    carried_order_day_count_is_set = p_row.getCarriedOrderDayCountIsSet();
    carried_order_day_count_is_modified = p_row.getCarriedOrderDayCountIsModified();
    mart_price_buy_to_open = p_row.getMartPriceBuyToOpen();
    mart_price_buy_to_open_is_set = p_row.getMartPriceBuyToOpenIsSet();
    mart_price_buy_to_open_is_modified = p_row.getMartPriceBuyToOpenIsModified();
    mart_price_sell_to_open = p_row.getMartPriceSellToOpen();
    mart_price_sell_to_open_is_set = p_row.getMartPriceSellToOpenIsSet();
    mart_price_sell_to_open_is_modified = p_row.getMartPriceSellToOpenIsModified();
    mart_price_order_settle_cont = p_row.getMartPriceOrderSettleCont();
    mart_price_order_settle_cont_is_set = p_row.getMartPriceOrderSettleContIsSet();
    mart_price_order_settle_cont_is_modified = p_row.getMartPriceOrderSettleContIsModified();
    current_price_order = p_row.getCurrentPriceOrder();
    current_price_order_is_set = p_row.getCurrentPriceOrderIsSet();
    current_price_order_is_modified = p_row.getCurrentPriceOrderIsModified();
    ease_current_price_order = p_row.getEaseCurrentPriceOrder();
    ease_current_price_order_is_set = p_row.getEaseCurrentPriceOrderIsSet();
    ease_current_price_order_is_modified = p_row.getEaseCurrentPriceOrderIsModified();
    market_price_rest_limit_price = p_row.getMarketPriceRestLimitPrice();
    market_price_rest_limit_price_is_set = p_row.getMarketPriceRestLimitPriceIsSet();
    market_price_rest_limit_price_is_modified = p_row.getMarketPriceRestLimitPriceIsModified();
    market_price_rest_cancel = p_row.getMarketPriceRestCancel();
    market_price_rest_cancel_is_set = p_row.getMarketPriceRestCancelIsSet();
    market_price_rest_cancel_is_modified = p_row.getMarketPriceRestCancelIsModified();
    at_market_open = p_row.getAtMarketOpen();
    at_market_open_is_set = p_row.getAtMarketOpenIsSet();
    at_market_open_is_modified = p_row.getAtMarketOpenIsModified();
    at_market_close = p_row.getAtMarketClose();
    at_market_close_is_set = p_row.getAtMarketCloseIsSet();
    at_market_close_is_modified = p_row.getAtMarketCloseIsModified();
    no_exec_at_mart_close = p_row.getNoExecAtMartClose();
    no_exec_at_mart_close_is_set = p_row.getNoExecAtMartCloseIsSet();
    no_exec_at_mart_close_is_modified = p_row.getNoExecAtMartCloseIsModified();
    stop_order = p_row.getStopOrder();
    stop_order_is_set = p_row.getStopOrderIsSet();
    stop_order_is_modified = p_row.getStopOrderIsModified();
    w_limit_order = p_row.getWLimitOrder();
    w_limit_order_is_set = p_row.getWLimitOrderIsSet();
    w_limit_order_is_modified = p_row.getWLimitOrderIsModified();
    chain_order = p_row.getChainOrder();
    chain_order_is_set = p_row.getChainOrderIsSet();
    chain_order_is_modified = p_row.getChainOrderIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    evening_session_order = p_row.getEveningSessionOrder();
    evening_session_order_is_set = p_row.getEveningSessionOrderIsSet();
    evening_session_order_is_modified = p_row.getEveningSessionOrderIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      carried_order_is_set = true;
      carried_order_is_modified = true;
      carried_order_lapse_date_spec_is_set = true;
      carried_order_lapse_date_spec_is_modified = true;
      carried_order_day_count_is_set = true;
      carried_order_day_count_is_modified = true;
      mart_price_buy_to_open_is_set = true;
      mart_price_buy_to_open_is_modified = true;
      mart_price_sell_to_open_is_set = true;
      mart_price_sell_to_open_is_modified = true;
      mart_price_order_settle_cont_is_set = true;
      mart_price_order_settle_cont_is_modified = true;
      current_price_order_is_set = true;
      current_price_order_is_modified = true;
      ease_current_price_order_is_set = true;
      ease_current_price_order_is_modified = true;
      market_price_rest_limit_price_is_set = true;
      market_price_rest_limit_price_is_modified = true;
      market_price_rest_cancel_is_set = true;
      market_price_rest_cancel_is_modified = true;
      at_market_open_is_set = true;
      at_market_open_is_modified = true;
      at_market_close_is_set = true;
      at_market_close_is_modified = true;
      no_exec_at_mart_close_is_set = true;
      no_exec_at_mart_close_is_modified = true;
      stop_order_is_set = true;
      stop_order_is_modified = true;
      w_limit_order_is_set = true;
      w_limit_order_is_modified = true;
      chain_order_is_set = true;
      chain_order_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      evening_session_order_is_set = true;
      evening_session_order_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof EnableOrderConditionRow ) )
       return false;
    return fieldsEqual( (EnableOrderConditionRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のEnableOrderConditionRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( EnableOrderConditionRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( product_type == null ) {
      if ( row.getProductType() != null )
        return false;
    } else if ( !product_type.equals( row.getProductType() ) ) {
        return false;
    }
    if ( future_option_div == null ) {
      if ( row.getFutureOptionDiv() != null )
        return false;
    } else if ( !future_option_div.equals( row.getFutureOptionDiv() ) ) {
        return false;
    }
    if ( margin_trading_div == null ) {
      if ( row.getMarginTradingDiv() != null )
        return false;
    } else if ( !margin_trading_div.equals( row.getMarginTradingDiv() ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( carried_order == null ) {
      if ( row.getCarriedOrder() != null )
        return false;
    } else if ( !carried_order.equals( row.getCarriedOrder() ) ) {
        return false;
    }
    if ( carried_order_lapse_date_spec == null ) {
      if ( row.getCarriedOrderLapseDateSpec() != null )
        return false;
    } else if ( !carried_order_lapse_date_spec.equals( row.getCarriedOrderLapseDateSpec() ) ) {
        return false;
    }
    if ( carried_order_day_count == null ) {
      if ( !row.getCarriedOrderDayCountIsNull() )
        return false;
    } else if ( row.getCarriedOrderDayCountIsNull() || ( carried_order_day_count.intValue() != row.getCarriedOrderDayCount() ) ) {
        return false;
    }
    if ( mart_price_buy_to_open == null ) {
      if ( row.getMartPriceBuyToOpen() != null )
        return false;
    } else if ( !mart_price_buy_to_open.equals( row.getMartPriceBuyToOpen() ) ) {
        return false;
    }
    if ( mart_price_sell_to_open == null ) {
      if ( row.getMartPriceSellToOpen() != null )
        return false;
    } else if ( !mart_price_sell_to_open.equals( row.getMartPriceSellToOpen() ) ) {
        return false;
    }
    if ( mart_price_order_settle_cont == null ) {
      if ( row.getMartPriceOrderSettleCont() != null )
        return false;
    } else if ( !mart_price_order_settle_cont.equals( row.getMartPriceOrderSettleCont() ) ) {
        return false;
    }
    if ( current_price_order == null ) {
      if ( row.getCurrentPriceOrder() != null )
        return false;
    } else if ( !current_price_order.equals( row.getCurrentPriceOrder() ) ) {
        return false;
    }
    if ( ease_current_price_order == null ) {
      if ( row.getEaseCurrentPriceOrder() != null )
        return false;
    } else if ( !ease_current_price_order.equals( row.getEaseCurrentPriceOrder() ) ) {
        return false;
    }
    if ( market_price_rest_limit_price == null ) {
      if ( row.getMarketPriceRestLimitPrice() != null )
        return false;
    } else if ( !market_price_rest_limit_price.equals( row.getMarketPriceRestLimitPrice() ) ) {
        return false;
    }
    if ( market_price_rest_cancel == null ) {
      if ( row.getMarketPriceRestCancel() != null )
        return false;
    } else if ( !market_price_rest_cancel.equals( row.getMarketPriceRestCancel() ) ) {
        return false;
    }
    if ( at_market_open == null ) {
      if ( row.getAtMarketOpen() != null )
        return false;
    } else if ( !at_market_open.equals( row.getAtMarketOpen() ) ) {
        return false;
    }
    if ( at_market_close == null ) {
      if ( row.getAtMarketClose() != null )
        return false;
    } else if ( !at_market_close.equals( row.getAtMarketClose() ) ) {
        return false;
    }
    if ( no_exec_at_mart_close == null ) {
      if ( row.getNoExecAtMartClose() != null )
        return false;
    } else if ( !no_exec_at_mart_close.equals( row.getNoExecAtMartClose() ) ) {
        return false;
    }
    if ( stop_order == null ) {
      if ( row.getStopOrder() != null )
        return false;
    } else if ( !stop_order.equals( row.getStopOrder() ) ) {
        return false;
    }
    if ( w_limit_order == null ) {
      if ( row.getWLimitOrder() != null )
        return false;
    } else if ( !w_limit_order.equals( row.getWLimitOrder() ) ) {
        return false;
    }
    if ( chain_order == null ) {
      if ( row.getChainOrder() != null )
        return false;
    } else if ( !chain_order.equals( row.getChainOrder() ) ) {
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
    if ( evening_session_order == null ) {
      if ( row.getEveningSessionOrder() != null )
        return false;
    } else if ( !evening_session_order.equals( row.getEveningSessionOrder() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (product_type!=null? product_type.hashCode(): 0) 
        + (future_option_div!=null? future_option_div.hashCode(): 0) 
        + (margin_trading_div!=null? margin_trading_div.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (carried_order!=null? carried_order.hashCode(): 0) 
        + (carried_order_lapse_date_spec!=null? carried_order_lapse_date_spec.hashCode(): 0) 
        + (carried_order_day_count!=null? carried_order_day_count.hashCode(): 0) 
        + (mart_price_buy_to_open!=null? mart_price_buy_to_open.hashCode(): 0) 
        + (mart_price_sell_to_open!=null? mart_price_sell_to_open.hashCode(): 0) 
        + (mart_price_order_settle_cont!=null? mart_price_order_settle_cont.hashCode(): 0) 
        + (current_price_order!=null? current_price_order.hashCode(): 0) 
        + (ease_current_price_order!=null? ease_current_price_order.hashCode(): 0) 
        + (market_price_rest_limit_price!=null? market_price_rest_limit_price.hashCode(): 0) 
        + (market_price_rest_cancel!=null? market_price_rest_cancel.hashCode(): 0) 
        + (at_market_open!=null? at_market_open.hashCode(): 0) 
        + (at_market_close!=null? at_market_close.hashCode(): 0) 
        + (no_exec_at_mart_close!=null? no_exec_at_mart_close.hashCode(): 0) 
        + (stop_order!=null? stop_order.hashCode(): 0) 
        + (w_limit_order!=null? w_limit_order.hashCode(): 0) 
        + (chain_order!=null? chain_order.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (evening_session_order!=null? evening_session_order.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("product_type",product_type);
		map.put("future_option_div",future_option_div);
		map.put("margin_trading_div",margin_trading_div);
		map.put("market_code",market_code);
		if ( carried_order != null )
			map.put("carried_order",carried_order);
		if ( carried_order_lapse_date_spec != null )
			map.put("carried_order_lapse_date_spec",carried_order_lapse_date_spec);
		if ( carried_order_day_count != null )
			map.put("carried_order_day_count",carried_order_day_count);
		if ( mart_price_buy_to_open != null )
			map.put("mart_price_buy_to_open",mart_price_buy_to_open);
		if ( mart_price_sell_to_open != null )
			map.put("mart_price_sell_to_open",mart_price_sell_to_open);
		if ( mart_price_order_settle_cont != null )
			map.put("mart_price_order_settle_cont",mart_price_order_settle_cont);
		if ( current_price_order != null )
			map.put("current_price_order",current_price_order);
		if ( ease_current_price_order != null )
			map.put("ease_current_price_order",ease_current_price_order);
		if ( market_price_rest_limit_price != null )
			map.put("market_price_rest_limit_price",market_price_rest_limit_price);
		if ( market_price_rest_cancel != null )
			map.put("market_price_rest_cancel",market_price_rest_cancel);
		if ( at_market_open != null )
			map.put("at_market_open",at_market_open);
		if ( at_market_close != null )
			map.put("at_market_close",at_market_close);
		if ( no_exec_at_mart_close != null )
			map.put("no_exec_at_mart_close",no_exec_at_mart_close);
		if ( stop_order != null )
			map.put("stop_order",stop_order);
		if ( w_limit_order != null )
			map.put("w_limit_order",w_limit_order);
		if ( chain_order != null )
			map.put("chain_order",chain_order);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( evening_session_order != null )
			map.put("evening_session_order",evening_session_order);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( carried_order_is_modified )
			map.put("carried_order",carried_order);
		if ( carried_order_lapse_date_spec_is_modified )
			map.put("carried_order_lapse_date_spec",carried_order_lapse_date_spec);
		if ( carried_order_day_count_is_modified )
			map.put("carried_order_day_count",carried_order_day_count);
		if ( mart_price_buy_to_open_is_modified )
			map.put("mart_price_buy_to_open",mart_price_buy_to_open);
		if ( mart_price_sell_to_open_is_modified )
			map.put("mart_price_sell_to_open",mart_price_sell_to_open);
		if ( mart_price_order_settle_cont_is_modified )
			map.put("mart_price_order_settle_cont",mart_price_order_settle_cont);
		if ( current_price_order_is_modified )
			map.put("current_price_order",current_price_order);
		if ( ease_current_price_order_is_modified )
			map.put("ease_current_price_order",ease_current_price_order);
		if ( market_price_rest_limit_price_is_modified )
			map.put("market_price_rest_limit_price",market_price_rest_limit_price);
		if ( market_price_rest_cancel_is_modified )
			map.put("market_price_rest_cancel",market_price_rest_cancel);
		if ( at_market_open_is_modified )
			map.put("at_market_open",at_market_open);
		if ( at_market_close_is_modified )
			map.put("at_market_close",at_market_close);
		if ( no_exec_at_mart_close_is_modified )
			map.put("no_exec_at_mart_close",no_exec_at_mart_close);
		if ( stop_order_is_modified )
			map.put("stop_order",stop_order);
		if ( w_limit_order_is_modified )
			map.put("w_limit_order",w_limit_order);
		if ( chain_order_is_modified )
			map.put("chain_order",chain_order);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( evening_session_order_is_modified )
			map.put("evening_session_order",evening_session_order);
		if (map.size() == 0) {
			map.put("carried_order",carried_order);
			map.put("carried_order_lapse_date_spec",carried_order_lapse_date_spec);
			map.put("carried_order_day_count",carried_order_day_count);
			map.put("mart_price_buy_to_open",mart_price_buy_to_open);
			map.put("mart_price_sell_to_open",mart_price_sell_to_open);
			map.put("mart_price_order_settle_cont",mart_price_order_settle_cont);
			map.put("current_price_order",current_price_order);
			map.put("ease_current_price_order",ease_current_price_order);
			map.put("market_price_rest_limit_price",market_price_rest_limit_price);
			map.put("market_price_rest_cancel",market_price_rest_cancel);
			map.put("at_market_open",at_market_open);
			map.put("at_market_close",at_market_close);
			map.put("no_exec_at_mart_close",no_exec_at_mart_close);
			map.put("stop_order",stop_order);
			map.put("w_limit_order",w_limit_order);
			map.put("chain_order",chain_order);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("evening_session_order",evening_session_order);
		}
		return map;
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
   * <em>product_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum getProductType()
  {
    return product_type;
  }


  /** 
   * <em>product_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductTypeIsSet() {
    return product_type_is_set;
  }


  /** 
   * <em>product_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductTypeIsModified() {
    return product_type_is_modified;
  }


  /** 
   * <em>future_option_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFutureOptionDiv()
  {
    return future_option_div;
  }


  /** 
   * <em>future_option_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutureOptionDivIsSet() {
    return future_option_div_is_set;
  }


  /** 
   * <em>future_option_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFutureOptionDivIsModified() {
    return future_option_div_is_modified;
  }


  /** 
   * <em>margin_trading_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginTradingDiv()
  {
    return margin_trading_div;
  }


  /** 
   * <em>margin_trading_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTradingDivIsSet() {
    return margin_trading_div_is_set;
  }


  /** 
   * <em>margin_trading_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTradingDivIsModified() {
    return margin_trading_div_is_modified;
  }


  /** 
   * <em>market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarketCode()
  {
    return market_code;
  }


  /** 
   * <em>market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketCodeIsSet() {
    return market_code_is_set;
  }


  /** 
   * <em>market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketCodeIsModified() {
    return market_code_is_modified;
  }


  /** 
   * <em>carried_order</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCarriedOrder()
  {
    return carried_order;
  }


  /** 
   * <em>carried_order</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCarriedOrderIsSet() {
    return carried_order_is_set;
  }


  /** 
   * <em>carried_order</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCarriedOrderIsModified() {
    return carried_order_is_modified;
  }


  /** 
   * <em>carried_order_lapse_date_spec</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCarriedOrderLapseDateSpec()
  {
    return carried_order_lapse_date_spec;
  }


  /** 
   * <em>carried_order_lapse_date_spec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCarriedOrderLapseDateSpecIsSet() {
    return carried_order_lapse_date_spec_is_set;
  }


  /** 
   * <em>carried_order_lapse_date_spec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCarriedOrderLapseDateSpecIsModified() {
    return carried_order_lapse_date_spec_is_modified;
  }


  /** 
   * <em>carried_order_day_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getCarriedOrderDayCount()
  {
    return ( carried_order_day_count==null? ((int)0): carried_order_day_count.intValue() );
  }


  /** 
   * <em>carried_order_day_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCarriedOrderDayCountIsNull()
  {
    return carried_order_day_count == null;
  }


  /** 
   * <em>carried_order_day_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCarriedOrderDayCountIsSet() {
    return carried_order_day_count_is_set;
  }


  /** 
   * <em>carried_order_day_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCarriedOrderDayCountIsModified() {
    return carried_order_day_count_is_modified;
  }


  /** 
   * <em>mart_price_buy_to_open</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMartPriceBuyToOpen()
  {
    return mart_price_buy_to_open;
  }


  /** 
   * <em>mart_price_buy_to_open</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMartPriceBuyToOpenIsSet() {
    return mart_price_buy_to_open_is_set;
  }


  /** 
   * <em>mart_price_buy_to_open</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMartPriceBuyToOpenIsModified() {
    return mart_price_buy_to_open_is_modified;
  }


  /** 
   * <em>mart_price_sell_to_open</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMartPriceSellToOpen()
  {
    return mart_price_sell_to_open;
  }


  /** 
   * <em>mart_price_sell_to_open</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMartPriceSellToOpenIsSet() {
    return mart_price_sell_to_open_is_set;
  }


  /** 
   * <em>mart_price_sell_to_open</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMartPriceSellToOpenIsModified() {
    return mart_price_sell_to_open_is_modified;
  }


  /** 
   * <em>mart_price_order_settle_cont</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMartPriceOrderSettleCont()
  {
    return mart_price_order_settle_cont;
  }


  /** 
   * <em>mart_price_order_settle_cont</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMartPriceOrderSettleContIsSet() {
    return mart_price_order_settle_cont_is_set;
  }


  /** 
   * <em>mart_price_order_settle_cont</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMartPriceOrderSettleContIsModified() {
    return mart_price_order_settle_cont_is_modified;
  }


  /** 
   * <em>current_price_order</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCurrentPriceOrder()
  {
    return current_price_order;
  }


  /** 
   * <em>current_price_order</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentPriceOrderIsSet() {
    return current_price_order_is_set;
  }


  /** 
   * <em>current_price_order</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentPriceOrderIsModified() {
    return current_price_order_is_modified;
  }


  /** 
   * <em>ease_current_price_order</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEaseCurrentPriceOrder()
  {
    return ease_current_price_order;
  }


  /** 
   * <em>ease_current_price_order</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEaseCurrentPriceOrderIsSet() {
    return ease_current_price_order_is_set;
  }


  /** 
   * <em>ease_current_price_order</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEaseCurrentPriceOrderIsModified() {
    return ease_current_price_order_is_modified;
  }


  /** 
   * <em>market_price_rest_limit_price</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarketPriceRestLimitPrice()
  {
    return market_price_rest_limit_price;
  }


  /** 
   * <em>market_price_rest_limit_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketPriceRestLimitPriceIsSet() {
    return market_price_rest_limit_price_is_set;
  }


  /** 
   * <em>market_price_rest_limit_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketPriceRestLimitPriceIsModified() {
    return market_price_rest_limit_price_is_modified;
  }


  /** 
   * <em>market_price_rest_cancel</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarketPriceRestCancel()
  {
    return market_price_rest_cancel;
  }


  /** 
   * <em>market_price_rest_cancel</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketPriceRestCancelIsSet() {
    return market_price_rest_cancel_is_set;
  }


  /** 
   * <em>market_price_rest_cancel</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketPriceRestCancelIsModified() {
    return market_price_rest_cancel_is_modified;
  }


  /** 
   * <em>at_market_open</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAtMarketOpen()
  {
    return at_market_open;
  }


  /** 
   * <em>at_market_open</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAtMarketOpenIsSet() {
    return at_market_open_is_set;
  }


  /** 
   * <em>at_market_open</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAtMarketOpenIsModified() {
    return at_market_open_is_modified;
  }


  /** 
   * <em>at_market_close</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAtMarketClose()
  {
    return at_market_close;
  }


  /** 
   * <em>at_market_close</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAtMarketCloseIsSet() {
    return at_market_close_is_set;
  }


  /** 
   * <em>at_market_close</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAtMarketCloseIsModified() {
    return at_market_close_is_modified;
  }


  /** 
   * <em>no_exec_at_mart_close</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNoExecAtMartClose()
  {
    return no_exec_at_mart_close;
  }


  /** 
   * <em>no_exec_at_mart_close</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNoExecAtMartCloseIsSet() {
    return no_exec_at_mart_close_is_set;
  }


  /** 
   * <em>no_exec_at_mart_close</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNoExecAtMartCloseIsModified() {
    return no_exec_at_mart_close_is_modified;
  }


  /** 
   * <em>stop_order</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStopOrder()
  {
    return stop_order;
  }


  /** 
   * <em>stop_order</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopOrderIsSet() {
    return stop_order_is_set;
  }


  /** 
   * <em>stop_order</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopOrderIsModified() {
    return stop_order_is_modified;
  }


  /** 
   * <em>w_limit_order</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getWLimitOrder()
  {
    return w_limit_order;
  }


  /** 
   * <em>w_limit_order</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWLimitOrderIsSet() {
    return w_limit_order_is_set;
  }


  /** 
   * <em>w_limit_order</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWLimitOrderIsModified() {
    return w_limit_order_is_modified;
  }


  /** 
   * <em>chain_order</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getChainOrder()
  {
    return chain_order;
  }


  /** 
   * <em>chain_order</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChainOrderIsSet() {
    return chain_order_is_set;
  }


  /** 
   * <em>chain_order</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChainOrderIsModified() {
    return chain_order_is_modified;
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
   * <em>evening_session_order</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getEveningSessionOrder()
  {
    return evening_session_order;
  }


  /** 
   * <em>evening_session_order</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEveningSessionOrderIsSet() {
    return evening_session_order_is_set;
  }


  /** 
   * <em>evening_session_order</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEveningSessionOrderIsModified() {
    return evening_session_order_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new EnableOrderConditionPK(institution_code, product_type, future_option_div, margin_trading_div, market_code);
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
   * <em>product_type</em>カラムの値を設定します。 
   *
   * @@param p_productType <em>product_type</em>カラムの値をあらわす6桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum値 
   */
  public final void setProductType( com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_type = p_productType;
    product_type_is_set = true;
    product_type_is_modified = true;
  }


  /** 
   * <em>future_option_div</em>カラムの値を設定します。 
   *
   * @@param p_futureOptionDiv <em>future_option_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setFutureOptionDiv( String p_futureOptionDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    future_option_div = p_futureOptionDiv;
    future_option_div_is_set = true;
    future_option_div_is_modified = true;
  }


  /** 
   * <em>margin_trading_div</em>カラムの値を設定します。 
   *
   * @@param p_marginTradingDiv <em>margin_trading_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarginTradingDiv( String p_marginTradingDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_trading_div = p_marginTradingDiv;
    margin_trading_div_is_set = true;
    margin_trading_div_is_modified = true;
  }


  /** 
   * <em>market_code</em>カラムの値を設定します。 
   *
   * @@param p_marketCode <em>market_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setMarketCode( String p_marketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_code = p_marketCode;
    market_code_is_set = true;
    market_code_is_modified = true;
  }


  /** 
   * <em>carried_order</em>カラムの値を設定します。 
   *
   * @@param p_carriedOrder <em>carried_order</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCarriedOrder( String p_carriedOrder )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    carried_order = p_carriedOrder;
    carried_order_is_set = true;
    carried_order_is_modified = true;
  }


  /** 
   * <em>carried_order_lapse_date_spec</em>カラムの値を設定します。 
   *
   * @@param p_carriedOrderLapseDateSpec <em>carried_order_lapse_date_spec</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCarriedOrderLapseDateSpec( String p_carriedOrderLapseDateSpec )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    carried_order_lapse_date_spec = p_carriedOrderLapseDateSpec;
    carried_order_lapse_date_spec_is_set = true;
    carried_order_lapse_date_spec_is_modified = true;
  }


  /** 
   * <em>carried_order_day_count</em>カラムの値を設定します。 
   *
   * @@param p_carriedOrderDayCount <em>carried_order_day_count</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setCarriedOrderDayCount( int p_carriedOrderDayCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    carried_order_day_count = new Integer(p_carriedOrderDayCount);
    carried_order_day_count_is_set = true;
    carried_order_day_count_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>carried_order_day_count</em>カラムに値を設定します。 
   */
  public final void setCarriedOrderDayCount( Integer p_carriedOrderDayCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    carried_order_day_count = p_carriedOrderDayCount;
    carried_order_day_count_is_set = true;
    carried_order_day_count_is_modified = true;
  }


  /** 
   * <em>mart_price_buy_to_open</em>カラムの値を設定します。 
   *
   * @@param p_martPriceBuyToOpen <em>mart_price_buy_to_open</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMartPriceBuyToOpen( String p_martPriceBuyToOpen )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mart_price_buy_to_open = p_martPriceBuyToOpen;
    mart_price_buy_to_open_is_set = true;
    mart_price_buy_to_open_is_modified = true;
  }


  /** 
   * <em>mart_price_sell_to_open</em>カラムの値を設定します。 
   *
   * @@param p_martPriceSellToOpen <em>mart_price_sell_to_open</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMartPriceSellToOpen( String p_martPriceSellToOpen )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mart_price_sell_to_open = p_martPriceSellToOpen;
    mart_price_sell_to_open_is_set = true;
    mart_price_sell_to_open_is_modified = true;
  }


  /** 
   * <em>mart_price_order_settle_cont</em>カラムの値を設定します。 
   *
   * @@param p_martPriceOrderSettleCont <em>mart_price_order_settle_cont</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMartPriceOrderSettleCont( String p_martPriceOrderSettleCont )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mart_price_order_settle_cont = p_martPriceOrderSettleCont;
    mart_price_order_settle_cont_is_set = true;
    mart_price_order_settle_cont_is_modified = true;
  }


  /** 
   * <em>current_price_order</em>カラムの値を設定します。 
   *
   * @@param p_currentPriceOrder <em>current_price_order</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCurrentPriceOrder( String p_currentPriceOrder )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    current_price_order = p_currentPriceOrder;
    current_price_order_is_set = true;
    current_price_order_is_modified = true;
  }


  /** 
   * <em>ease_current_price_order</em>カラムの値を設定します。 
   *
   * @@param p_easeCurrentPriceOrder <em>ease_current_price_order</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEaseCurrentPriceOrder( String p_easeCurrentPriceOrder )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ease_current_price_order = p_easeCurrentPriceOrder;
    ease_current_price_order_is_set = true;
    ease_current_price_order_is_modified = true;
  }


  /** 
   * <em>market_price_rest_limit_price</em>カラムの値を設定します。 
   *
   * @@param p_marketPriceRestLimitPrice <em>market_price_rest_limit_price</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarketPriceRestLimitPrice( String p_marketPriceRestLimitPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_price_rest_limit_price = p_marketPriceRestLimitPrice;
    market_price_rest_limit_price_is_set = true;
    market_price_rest_limit_price_is_modified = true;
  }


  /** 
   * <em>market_price_rest_cancel</em>カラムの値を設定します。 
   *
   * @@param p_marketPriceRestCancel <em>market_price_rest_cancel</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarketPriceRestCancel( String p_marketPriceRestCancel )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_price_rest_cancel = p_marketPriceRestCancel;
    market_price_rest_cancel_is_set = true;
    market_price_rest_cancel_is_modified = true;
  }


  /** 
   * <em>at_market_open</em>カラムの値を設定します。 
   *
   * @@param p_atMarketOpen <em>at_market_open</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAtMarketOpen( String p_atMarketOpen )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    at_market_open = p_atMarketOpen;
    at_market_open_is_set = true;
    at_market_open_is_modified = true;
  }


  /** 
   * <em>at_market_close</em>カラムの値を設定します。 
   *
   * @@param p_atMarketClose <em>at_market_close</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAtMarketClose( String p_atMarketClose )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    at_market_close = p_atMarketClose;
    at_market_close_is_set = true;
    at_market_close_is_modified = true;
  }


  /** 
   * <em>no_exec_at_mart_close</em>カラムの値を設定します。 
   *
   * @@param p_noExecAtMartClose <em>no_exec_at_mart_close</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setNoExecAtMartClose( String p_noExecAtMartClose )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    no_exec_at_mart_close = p_noExecAtMartClose;
    no_exec_at_mart_close_is_set = true;
    no_exec_at_mart_close_is_modified = true;
  }


  /** 
   * <em>stop_order</em>カラムの値を設定します。 
   *
   * @@param p_stopOrder <em>stop_order</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStopOrder( String p_stopOrder )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_order = p_stopOrder;
    stop_order_is_set = true;
    stop_order_is_modified = true;
  }


  /** 
   * <em>w_limit_order</em>カラムの値を設定します。 
   *
   * @@param p_wLimitOrder <em>w_limit_order</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setWLimitOrder( String p_wLimitOrder )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    w_limit_order = p_wLimitOrder;
    w_limit_order_is_set = true;
    w_limit_order_is_modified = true;
  }


  /** 
   * <em>chain_order</em>カラムの値を設定します。 
   *
   * @@param p_chainOrder <em>chain_order</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setChainOrder( String p_chainOrder )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    chain_order = p_chainOrder;
    chain_order_is_set = true;
    chain_order_is_modified = true;
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
   * <em>evening_session_order</em>カラムの値を設定します。 
   *
   * @@param p_eveningSessionOrder <em>evening_session_order</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setEveningSessionOrder( String p_eveningSessionOrder )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    evening_session_order = p_eveningSessionOrder;
    evening_session_order_is_set = true;
    evening_session_order_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("at_market_open") ) {
                    return this.at_market_open;
                }
                else if ( name.equals("at_market_close") ) {
                    return this.at_market_close;
                }
                break;
            case 'c':
                if ( name.equals("carried_order") ) {
                    return this.carried_order;
                }
                else if ( name.equals("carried_order_lapse_date_spec") ) {
                    return this.carried_order_lapse_date_spec;
                }
                else if ( name.equals("carried_order_day_count") ) {
                    return this.carried_order_day_count;
                }
                else if ( name.equals("current_price_order") ) {
                    return this.current_price_order;
                }
                else if ( name.equals("chain_order") ) {
                    return this.chain_order;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("ease_current_price_order") ) {
                    return this.ease_current_price_order;
                }
                else if ( name.equals("evening_session_order") ) {
                    return this.evening_session_order;
                }
                break;
            case 'f':
                if ( name.equals("future_option_div") ) {
                    return this.future_option_div;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("margin_trading_div") ) {
                    return this.margin_trading_div;
                }
                else if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                else if ( name.equals("mart_price_buy_to_open") ) {
                    return this.mart_price_buy_to_open;
                }
                else if ( name.equals("mart_price_sell_to_open") ) {
                    return this.mart_price_sell_to_open;
                }
                else if ( name.equals("mart_price_order_settle_cont") ) {
                    return this.mart_price_order_settle_cont;
                }
                else if ( name.equals("market_price_rest_limit_price") ) {
                    return this.market_price_rest_limit_price;
                }
                else if ( name.equals("market_price_rest_cancel") ) {
                    return this.market_price_rest_cancel;
                }
                break;
            case 'n':
                if ( name.equals("no_exec_at_mart_close") ) {
                    return this.no_exec_at_mart_close;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    return this.product_type;
                }
                break;
            case 's':
                if ( name.equals("stop_order") ) {
                    return this.stop_order;
                }
                break;
            case 'w':
                if ( name.equals("w_limit_order") ) {
                    return this.w_limit_order;
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
                if ( name.equals("at_market_open") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'at_market_open' must be String: '"+value+"' is not." );
                    this.at_market_open = (String) value;
                    if (this.at_market_open_is_set)
                        this.at_market_open_is_modified = true;
                    this.at_market_open_is_set = true;
                    return;
                }
                else if ( name.equals("at_market_close") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'at_market_close' must be String: '"+value+"' is not." );
                    this.at_market_close = (String) value;
                    if (this.at_market_close_is_set)
                        this.at_market_close_is_modified = true;
                    this.at_market_close_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("carried_order") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'carried_order' must be String: '"+value+"' is not." );
                    this.carried_order = (String) value;
                    if (this.carried_order_is_set)
                        this.carried_order_is_modified = true;
                    this.carried_order_is_set = true;
                    return;
                }
                else if ( name.equals("carried_order_lapse_date_spec") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'carried_order_lapse_date_spec' must be String: '"+value+"' is not." );
                    this.carried_order_lapse_date_spec = (String) value;
                    if (this.carried_order_lapse_date_spec_is_set)
                        this.carried_order_lapse_date_spec_is_modified = true;
                    this.carried_order_lapse_date_spec_is_set = true;
                    return;
                }
                else if ( name.equals("carried_order_day_count") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'carried_order_day_count' must be Integer: '"+value+"' is not." );
                    this.carried_order_day_count = (Integer) value;
                    if (this.carried_order_day_count_is_set)
                        this.carried_order_day_count_is_modified = true;
                    this.carried_order_day_count_is_set = true;
                    return;
                }
                else if ( name.equals("current_price_order") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'current_price_order' must be String: '"+value+"' is not." );
                    this.current_price_order = (String) value;
                    if (this.current_price_order_is_set)
                        this.current_price_order_is_modified = true;
                    this.current_price_order_is_set = true;
                    return;
                }
                else if ( name.equals("chain_order") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'chain_order' must be String: '"+value+"' is not." );
                    this.chain_order = (String) value;
                    if (this.chain_order_is_set)
                        this.chain_order_is_modified = true;
                    this.chain_order_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
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
                if ( name.equals("ease_current_price_order") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ease_current_price_order' must be String: '"+value+"' is not." );
                    this.ease_current_price_order = (String) value;
                    if (this.ease_current_price_order_is_set)
                        this.ease_current_price_order_is_modified = true;
                    this.ease_current_price_order_is_set = true;
                    return;
                }
                else if ( name.equals("evening_session_order") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'evening_session_order' must be String: '"+value+"' is not." );
                    this.evening_session_order = (String) value;
                    if (this.evening_session_order_is_set)
                        this.evening_session_order_is_modified = true;
                    this.evening_session_order_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("future_option_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'future_option_div' must be String: '"+value+"' is not." );
                    this.future_option_div = (String) value;
                    if (this.future_option_div_is_set)
                        this.future_option_div_is_modified = true;
                    this.future_option_div_is_set = true;
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
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
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
                if ( name.equals("margin_trading_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_trading_div' must be String: '"+value+"' is not." );
                    this.margin_trading_div = (String) value;
                    if (this.margin_trading_div_is_set)
                        this.margin_trading_div_is_modified = true;
                    this.margin_trading_div_is_set = true;
                    return;
                }
                else if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    if (this.market_code_is_set)
                        this.market_code_is_modified = true;
                    this.market_code_is_set = true;
                    return;
                }
                else if ( name.equals("mart_price_buy_to_open") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mart_price_buy_to_open' must be String: '"+value+"' is not." );
                    this.mart_price_buy_to_open = (String) value;
                    if (this.mart_price_buy_to_open_is_set)
                        this.mart_price_buy_to_open_is_modified = true;
                    this.mart_price_buy_to_open_is_set = true;
                    return;
                }
                else if ( name.equals("mart_price_sell_to_open") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mart_price_sell_to_open' must be String: '"+value+"' is not." );
                    this.mart_price_sell_to_open = (String) value;
                    if (this.mart_price_sell_to_open_is_set)
                        this.mart_price_sell_to_open_is_modified = true;
                    this.mart_price_sell_to_open_is_set = true;
                    return;
                }
                else if ( name.equals("mart_price_order_settle_cont") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mart_price_order_settle_cont' must be String: '"+value+"' is not." );
                    this.mart_price_order_settle_cont = (String) value;
                    if (this.mart_price_order_settle_cont_is_set)
                        this.mart_price_order_settle_cont_is_modified = true;
                    this.mart_price_order_settle_cont_is_set = true;
                    return;
                }
                else if ( name.equals("market_price_rest_limit_price") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_price_rest_limit_price' must be String: '"+value+"' is not." );
                    this.market_price_rest_limit_price = (String) value;
                    if (this.market_price_rest_limit_price_is_set)
                        this.market_price_rest_limit_price_is_modified = true;
                    this.market_price_rest_limit_price_is_set = true;
                    return;
                }
                else if ( name.equals("market_price_rest_cancel") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_price_rest_cancel' must be String: '"+value+"' is not." );
                    this.market_price_rest_cancel = (String) value;
                    if (this.market_price_rest_cancel_is_set)
                        this.market_price_rest_cancel_is_modified = true;
                    this.market_price_rest_cancel_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("no_exec_at_mart_close") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'no_exec_at_mart_close' must be String: '"+value+"' is not." );
                    this.no_exec_at_mart_close = (String) value;
                    if (this.no_exec_at_mart_close_is_set)
                        this.no_exec_at_mart_close_is_modified = true;
                    this.no_exec_at_mart_close_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_type") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) )
                        throw new IllegalArgumentException( "value for 'product_type' must be com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum: '"+value+"' is not." );
                    this.product_type = (com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum) value;
                    if (this.product_type_is_set)
                        this.product_type_is_modified = true;
                    this.product_type_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("stop_order") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_order' must be String: '"+value+"' is not." );
                    this.stop_order = (String) value;
                    if (this.stop_order_is_set)
                        this.stop_order_is_modified = true;
                    this.stop_order_is_set = true;
                    return;
                }
                break;
            case 'w':
                if ( name.equals("w_limit_order") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'w_limit_order' must be String: '"+value+"' is not." );
                    this.w_limit_order = (String) value;
                    if (this.w_limit_order_is_set)
                        this.w_limit_order_is_modified = true;
                    this.w_limit_order_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
