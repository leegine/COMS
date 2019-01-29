head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.17.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4c04d8868c00da0;
filename	RuitoTradedProductParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.xbruito.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * ruito_traded_productテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link RuitoTradedProductRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link RuitoTradedProductParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link RuitoTradedProductParams}が{@@link RuitoTradedProductRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RuitoTradedProductPK 
 * @@see RuitoTradedProductRow 
 */
public class RuitoTradedProductParams extends Params implements RuitoTradedProductRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "ruito_traded_product";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = RuitoTradedProductRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return RuitoTradedProductRow.TYPE;
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
   * <em>order_close_time</em>カラムの値 
   */
  public  String  order_close_time;

  /** 
   * <em>market_id</em>カラムの値 
   */
  public  long  market_id;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>last_closing_price</em>カラムの値 
   */
  public  Double  last_closing_price;

  /** 
   * <em>buy_stop</em>カラムの値 
   */
  public  String  buy_stop;

  /** 
   * <em>sell_stop</em>カラムの値 
   */
  public  String  sell_stop;

  /** 
   * <em>buy_delivery_date_shift_days</em>カラムの値 
   */
  public  Integer  buy_delivery_date_shift_days;

  /** 
   * <em>sell_delivery_date_shift_days</em>カラムの値 
   */
  public  Integer  sell_delivery_date_shift_days;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>online_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  online_updated_timestamp;

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
  private boolean order_close_time_is_set = false;
  private boolean order_close_time_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean last_closing_price_is_set = false;
  private boolean last_closing_price_is_modified = false;
  private boolean buy_stop_is_set = false;
  private boolean buy_stop_is_modified = false;
  private boolean sell_stop_is_set = false;
  private boolean sell_stop_is_modified = false;
  private boolean buy_delivery_date_shift_days_is_set = false;
  private boolean buy_delivery_date_shift_days_is_modified = false;
  private boolean sell_delivery_date_shift_days_is_set = false;
  private boolean sell_delivery_date_shift_days_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean online_updated_timestamp_is_set = false;
  private boolean online_updated_timestamp_is_modified = false;
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
      + "," + "order_close_time=" +order_close_time
      + "," + "market_id=" +market_id
      + "," + "product_id=" +product_id
      + "," + "last_closing_price=" +last_closing_price
      + "," + "buy_stop=" +buy_stop
      + "," + "sell_stop=" +sell_stop
      + "," + "buy_delivery_date_shift_days=" +buy_delivery_date_shift_days
      + "," + "sell_delivery_date_shift_days=" +sell_delivery_date_shift_days
      + "," + "last_updater=" +last_updater
      + "," + "online_updated_timestamp=" +online_updated_timestamp
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のRuitoTradedProductParamsオブジェクトを作成します。 
   */
  public RuitoTradedProductParams() {
    traded_product_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のRuitoTradedProductRowオブジェクトの値を利用してRuitoTradedProductParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するRuitoTradedProductRowオブジェクト 
   */
  public RuitoTradedProductParams( RuitoTradedProductRow p_row )
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
    order_close_time = p_row.getOrderCloseTime();
    order_close_time_is_set = p_row.getOrderCloseTimeIsSet();
    order_close_time_is_modified = p_row.getOrderCloseTimeIsModified();
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    if ( !p_row.getLastClosingPriceIsNull() )
      last_closing_price = new Double( p_row.getLastClosingPrice() );
    last_closing_price_is_set = p_row.getLastClosingPriceIsSet();
    last_closing_price_is_modified = p_row.getLastClosingPriceIsModified();
    buy_stop = p_row.getBuyStop();
    buy_stop_is_set = p_row.getBuyStopIsSet();
    buy_stop_is_modified = p_row.getBuyStopIsModified();
    sell_stop = p_row.getSellStop();
    sell_stop_is_set = p_row.getSellStopIsSet();
    sell_stop_is_modified = p_row.getSellStopIsModified();
    if ( !p_row.getBuyDeliveryDateShiftDaysIsNull() )
      buy_delivery_date_shift_days = new Integer( p_row.getBuyDeliveryDateShiftDays() );
    buy_delivery_date_shift_days_is_set = p_row.getBuyDeliveryDateShiftDaysIsSet();
    buy_delivery_date_shift_days_is_modified = p_row.getBuyDeliveryDateShiftDaysIsModified();
    if ( !p_row.getSellDeliveryDateShiftDaysIsNull() )
      sell_delivery_date_shift_days = new Integer( p_row.getSellDeliveryDateShiftDays() );
    sell_delivery_date_shift_days_is_set = p_row.getSellDeliveryDateShiftDaysIsSet();
    sell_delivery_date_shift_days_is_modified = p_row.getSellDeliveryDateShiftDaysIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    online_updated_timestamp = p_row.getOnlineUpdatedTimestamp();
    online_updated_timestamp_is_set = p_row.getOnlineUpdatedTimestampIsSet();
    online_updated_timestamp_is_modified = p_row.getOnlineUpdatedTimestampIsModified();
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
      order_close_time_is_set = true;
      order_close_time_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      last_closing_price_is_set = true;
      last_closing_price_is_modified = true;
      buy_stop_is_set = true;
      buy_stop_is_modified = true;
      sell_stop_is_set = true;
      sell_stop_is_modified = true;
      buy_delivery_date_shift_days_is_set = true;
      buy_delivery_date_shift_days_is_modified = true;
      sell_delivery_date_shift_days_is_set = true;
      sell_delivery_date_shift_days_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      online_updated_timestamp_is_set = true;
      online_updated_timestamp_is_modified = true;
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
    if ( !( other instanceof RuitoTradedProductRow ) )
       return false;
    return fieldsEqual( (RuitoTradedProductRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のRuitoTradedProductRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( RuitoTradedProductRow row )
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
    if ( order_close_time == null ) {
      if ( row.getOrderCloseTime() != null )
        return false;
    } else if ( !order_close_time.equals( row.getOrderCloseTime() ) ) {
        return false;
    }
    if ( market_id != row.getMarketId() )
      return false;
    if ( product_id != row.getProductId() )
      return false;
    if ( last_closing_price == null ) {
      if ( !row.getLastClosingPriceIsNull() )
        return false;
    } else if ( row.getLastClosingPriceIsNull() || ( last_closing_price.doubleValue() != row.getLastClosingPrice() ) ) {
        return false;
    }
    if ( buy_stop == null ) {
      if ( row.getBuyStop() != null )
        return false;
    } else if ( !buy_stop.equals( row.getBuyStop() ) ) {
        return false;
    }
    if ( sell_stop == null ) {
      if ( row.getSellStop() != null )
        return false;
    } else if ( !sell_stop.equals( row.getSellStop() ) ) {
        return false;
    }
    if ( buy_delivery_date_shift_days == null ) {
      if ( !row.getBuyDeliveryDateShiftDaysIsNull() )
        return false;
    } else if ( row.getBuyDeliveryDateShiftDaysIsNull() || ( buy_delivery_date_shift_days.intValue() != row.getBuyDeliveryDateShiftDays() ) ) {
        return false;
    }
    if ( sell_delivery_date_shift_days == null ) {
      if ( !row.getSellDeliveryDateShiftDaysIsNull() )
        return false;
    } else if ( row.getSellDeliveryDateShiftDaysIsNull() || ( sell_delivery_date_shift_days.intValue() != row.getSellDeliveryDateShiftDays() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    if ( online_updated_timestamp == null ) {
      if ( row.getOnlineUpdatedTimestamp() != null )
        return false;
    } else if ( !online_updated_timestamp.equals( row.getOnlineUpdatedTimestamp() ) ) {
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
        + (order_close_time!=null? order_close_time.hashCode(): 0) 
        + ((int) market_id)
        + ((int) product_id)
        + (last_closing_price!=null? last_closing_price.hashCode(): 0) 
        + (buy_stop!=null? buy_stop.hashCode(): 0) 
        + (sell_stop!=null? sell_stop.hashCode(): 0) 
        + (buy_delivery_date_shift_days!=null? buy_delivery_date_shift_days.hashCode(): 0) 
        + (sell_delivery_date_shift_days!=null? sell_delivery_date_shift_days.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (online_updated_timestamp!=null? online_updated_timestamp.hashCode(): 0) 
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
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
		if ( !online_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'online_updated_timestamp' must be set before inserting.");
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
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
		if ( order_close_time != null )
			map.put("order_close_time",order_close_time);
		map.put("market_id",new Long(market_id));
		map.put("product_id",new Long(product_id));
		if ( last_closing_price != null )
			map.put("last_closing_price",last_closing_price);
		if ( buy_stop != null )
			map.put("buy_stop",buy_stop);
		if ( sell_stop != null )
			map.put("sell_stop",sell_stop);
		if ( buy_delivery_date_shift_days != null )
			map.put("buy_delivery_date_shift_days",buy_delivery_date_shift_days);
		if ( sell_delivery_date_shift_days != null )
			map.put("sell_delivery_date_shift_days",sell_delivery_date_shift_days);
		map.put("last_updater",last_updater);
		map.put("online_updated_timestamp",online_updated_timestamp);
		map.put("created_timestamp",created_timestamp);
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
		if ( order_close_time_is_modified )
			map.put("order_close_time",order_close_time);
		if ( market_id_is_modified )
			map.put("market_id",new Long(market_id));
		if ( product_id_is_modified )
			map.put("product_id",new Long(product_id));
		if ( last_closing_price_is_modified )
			map.put("last_closing_price",last_closing_price);
		if ( buy_stop_is_modified )
			map.put("buy_stop",buy_stop);
		if ( sell_stop_is_modified )
			map.put("sell_stop",sell_stop);
		if ( buy_delivery_date_shift_days_is_modified )
			map.put("buy_delivery_date_shift_days",buy_delivery_date_shift_days);
		if ( sell_delivery_date_shift_days_is_modified )
			map.put("sell_delivery_date_shift_days",sell_delivery_date_shift_days);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( online_updated_timestamp_is_modified )
			map.put("online_updated_timestamp",online_updated_timestamp);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("valid_for_biz_date",valid_for_biz_date);
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			map.put("order_close_time",order_close_time);
			if ( market_id_is_set )
				map.put("market_id",new Long(market_id));
			if ( product_id_is_set )
				map.put("product_id",new Long(product_id));
			map.put("last_closing_price",last_closing_price);
			map.put("buy_stop",buy_stop);
			map.put("sell_stop",sell_stop);
			map.put("buy_delivery_date_shift_days",buy_delivery_date_shift_days);
			map.put("sell_delivery_date_shift_days",sell_delivery_date_shift_days);
			if ( last_updater_is_set )
				map.put("last_updater",last_updater);
			if ( online_updated_timestamp_is_set )
				map.put("online_updated_timestamp",online_updated_timestamp);
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
   * <em>buy_stop</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBuyStop()
  {
    return buy_stop;
  }


  /** 
   * <em>buy_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyStopIsSet() {
    return buy_stop_is_set;
  }


  /** 
   * <em>buy_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyStopIsModified() {
    return buy_stop_is_modified;
  }


  /** 
   * <em>sell_stop</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSellStop()
  {
    return sell_stop;
  }


  /** 
   * <em>sell_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellStopIsSet() {
    return sell_stop_is_set;
  }


  /** 
   * <em>sell_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellStopIsModified() {
    return sell_stop_is_modified;
  }


  /** 
   * <em>buy_delivery_date_shift_days</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getBuyDeliveryDateShiftDays()
  {
    return ( buy_delivery_date_shift_days==null? ((int)0): buy_delivery_date_shift_days.intValue() );
  }


  /** 
   * <em>buy_delivery_date_shift_days</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBuyDeliveryDateShiftDaysIsNull()
  {
    return buy_delivery_date_shift_days == null;
  }


  /** 
   * <em>buy_delivery_date_shift_days</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyDeliveryDateShiftDaysIsSet() {
    return buy_delivery_date_shift_days_is_set;
  }


  /** 
   * <em>buy_delivery_date_shift_days</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBuyDeliveryDateShiftDaysIsModified() {
    return buy_delivery_date_shift_days_is_modified;
  }


  /** 
   * <em>sell_delivery_date_shift_days</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getSellDeliveryDateShiftDays()
  {
    return ( sell_delivery_date_shift_days==null? ((int)0): sell_delivery_date_shift_days.intValue() );
  }


  /** 
   * <em>sell_delivery_date_shift_days</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSellDeliveryDateShiftDaysIsNull()
  {
    return sell_delivery_date_shift_days == null;
  }


  /** 
   * <em>sell_delivery_date_shift_days</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellDeliveryDateShiftDaysIsSet() {
    return sell_delivery_date_shift_days_is_set;
  }


  /** 
   * <em>sell_delivery_date_shift_days</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSellDeliveryDateShiftDaysIsModified() {
    return sell_delivery_date_shift_days_is_modified;
  }


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
  }


  /** 
   * <em>online_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOnlineUpdatedTimestamp()
  {
    return online_updated_timestamp;
  }


  /** 
   * <em>online_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlineUpdatedTimestampIsSet() {
    return online_updated_timestamp_is_set;
  }


  /** 
   * <em>online_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOnlineUpdatedTimestampIsModified() {
    return online_updated_timestamp_is_modified;
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
    return new RuitoTradedProductPK(traded_product_id);
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
   * <em>buy_stop</em>カラムの値を設定します。 
   *
   * @@param p_buyStop <em>buy_stop</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setBuyStop( String p_buyStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_stop = p_buyStop;
    buy_stop_is_set = true;
    buy_stop_is_modified = true;
  }


  /** 
   * <em>sell_stop</em>カラムの値を設定します。 
   *
   * @@param p_sellStop <em>sell_stop</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setSellStop( String p_sellStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_stop = p_sellStop;
    sell_stop_is_set = true;
    sell_stop_is_modified = true;
  }


  /** 
   * <em>buy_delivery_date_shift_days</em>カラムの値を設定します。 
   *
   * @@param p_buyDeliveryDateShiftDays <em>buy_delivery_date_shift_days</em>カラムの値をあらわす2桁以下のint値 
   */
  public final void setBuyDeliveryDateShiftDays( int p_buyDeliveryDateShiftDays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    buy_delivery_date_shift_days = new Integer(p_buyDeliveryDateShiftDays);
    buy_delivery_date_shift_days_is_set = true;
    buy_delivery_date_shift_days_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>buy_delivery_date_shift_days</em>カラムに値を設定します。 
   */
  public final void setBuyDeliveryDateShiftDays( Integer p_buyDeliveryDateShiftDays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    buy_delivery_date_shift_days = p_buyDeliveryDateShiftDays;
    buy_delivery_date_shift_days_is_set = true;
    buy_delivery_date_shift_days_is_modified = true;
  }


  /** 
   * <em>sell_delivery_date_shift_days</em>カラムの値を設定します。 
   *
   * @@param p_sellDeliveryDateShiftDays <em>sell_delivery_date_shift_days</em>カラムの値をあらわす2桁以下のint値 
   */
  public final void setSellDeliveryDateShiftDays( int p_sellDeliveryDateShiftDays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sell_delivery_date_shift_days = new Integer(p_sellDeliveryDateShiftDays);
    sell_delivery_date_shift_days_is_set = true;
    sell_delivery_date_shift_days_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>sell_delivery_date_shift_days</em>カラムに値を設定します。 
   */
  public final void setSellDeliveryDateShiftDays( Integer p_sellDeliveryDateShiftDays )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    sell_delivery_date_shift_days = p_sellDeliveryDateShiftDays;
    sell_delivery_date_shift_days_is_set = true;
    sell_delivery_date_shift_days_is_modified = true;
  }


  /** 
   * <em>last_updater</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdater <em>last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
  }


  /** 
   * <em>online_updated_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_onlineUpdatedTimestamp <em>online_updated_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOnlineUpdatedTimestamp( java.sql.Timestamp p_onlineUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    online_updated_timestamp = p_onlineUpdatedTimestamp;
    online_updated_timestamp_is_set = true;
    online_updated_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>online_updated_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOnlineUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    online_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    online_updated_timestamp_is_set = true;
    online_updated_timestamp_is_modified = true;
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
            case 'b':
                if ( name.equals("buy_stop") ) {
                    return this.buy_stop;
                }
                else if ( name.equals("buy_delivery_date_shift_days") ) {
                    return this.buy_delivery_date_shift_days;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_closing_price") ) {
                    return this.last_closing_price;
                }
                else if ( name.equals("last_updater") ) {
                    return this.last_updater;
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
                else if ( name.equals("online_updated_timestamp") ) {
                    return this.online_updated_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                break;
            case 's':
                if ( name.equals("sell_stop") ) {
                    return this.sell_stop;
                }
                else if ( name.equals("sell_delivery_date_shift_days") ) {
                    return this.sell_delivery_date_shift_days;
                }
                break;
            case 't':
                if ( name.equals("traded_product_id") ) {
                    return new Long( traded_product_id );
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
            case 'b':
                if ( name.equals("buy_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'buy_stop' must be String: '"+value+"' is not." );
                    this.buy_stop = (String) value;
                    if (this.buy_stop_is_set)
                        this.buy_stop_is_modified = true;
                    this.buy_stop_is_set = true;
                    return;
                }
                else if ( name.equals("buy_delivery_date_shift_days") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'buy_delivery_date_shift_days' must be Integer: '"+value+"' is not." );
                    this.buy_delivery_date_shift_days = (Integer) value;
                    if (this.buy_delivery_date_shift_days_is_set)
                        this.buy_delivery_date_shift_days_is_modified = true;
                    this.buy_delivery_date_shift_days_is_set = true;
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
                else if ( name.equals("last_updater") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
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
                else if ( name.equals("online_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'online_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.online_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.online_updated_timestamp_is_set)
                        this.online_updated_timestamp_is_modified = true;
                    this.online_updated_timestamp_is_set = true;
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
                break;
            case 's':
                if ( name.equals("sell_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sell_stop' must be String: '"+value+"' is not." );
                    this.sell_stop = (String) value;
                    if (this.sell_stop_is_set)
                        this.sell_stop_is_modified = true;
                    this.sell_stop_is_set = true;
                    return;
                }
                else if ( name.equals("sell_delivery_date_shift_days") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'sell_delivery_date_shift_days' must be Integer: '"+value+"' is not." );
                    this.sell_delivery_date_shift_days = (Integer) value;
                    if (this.sell_delivery_date_shift_days_is_set)
                        this.sell_delivery_date_shift_days_is_modified = true;
                    this.sell_delivery_date_shift_days_is_set = true;
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
