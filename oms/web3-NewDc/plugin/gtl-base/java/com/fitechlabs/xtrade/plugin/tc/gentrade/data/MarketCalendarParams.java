head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.38.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MarketCalendarParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * market_calendarテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link MarketCalendarRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link MarketCalendarParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link MarketCalendarParams}が{@@link MarketCalendarRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MarketCalendarPK 
 * @@see MarketCalendarRow 
 */
public class MarketCalendarParams extends Params implements MarketCalendarRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "market_calendar";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = MarketCalendarRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return MarketCalendarRow.TYPE;
  }


  /** 
   * <em>market_id</em>カラムの値 
   */
  public  long  market_id;

  /** 
   * <em>trade_date</em>カラムの値 
   */
  public  java.sql.Timestamp  trade_date;

  /** 
   * <em>holiday_flag</em>カラムの値 
   */
  public  com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum  holiday_flag;

  /** 
   * <em>trade_open_time</em>カラムの値 
   */
  public  String  trade_open_time;

  /** 
   * <em>trade_close_time</em>カラムの値 
   */
  public  String  trade_close_time;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean trade_date_is_set = false;
  private boolean trade_date_is_modified = false;
  private boolean holiday_flag_is_set = false;
  private boolean holiday_flag_is_modified = false;
  private boolean trade_open_time_is_set = false;
  private boolean trade_open_time_is_modified = false;
  private boolean trade_close_time_is_set = false;
  private boolean trade_close_time_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "market_id=" + market_id
      + "," + "trade_date=" + trade_date
      + "," + "holiday_flag=" +holiday_flag
      + "," + "trade_open_time=" +trade_open_time
      + "," + "trade_close_time=" +trade_close_time
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のMarketCalendarParamsオブジェクトを作成します。 
   */
  public MarketCalendarParams() {
    market_id_is_modified = true;
    trade_date_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のMarketCalendarRowオブジェクトの値を利用してMarketCalendarParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するMarketCalendarRowオブジェクト 
   */
  public MarketCalendarParams( MarketCalendarRow p_row )
  {
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    trade_date = p_row.getTradeDate();
    trade_date_is_set = p_row.getTradeDateIsSet();
    trade_date_is_modified = p_row.getTradeDateIsModified();
    holiday_flag = p_row.getHolidayFlag();
    holiday_flag_is_set = p_row.getHolidayFlagIsSet();
    holiday_flag_is_modified = p_row.getHolidayFlagIsModified();
    trade_open_time = p_row.getTradeOpenTime();
    trade_open_time_is_set = p_row.getTradeOpenTimeIsSet();
    trade_open_time_is_modified = p_row.getTradeOpenTimeIsModified();
    trade_close_time = p_row.getTradeCloseTime();
    trade_close_time_is_set = p_row.getTradeCloseTimeIsSet();
    trade_close_time_is_modified = p_row.getTradeCloseTimeIsModified();
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
      holiday_flag_is_set = true;
      holiday_flag_is_modified = true;
      trade_open_time_is_set = true;
      trade_open_time_is_modified = true;
      trade_close_time_is_set = true;
      trade_close_time_is_modified = true;
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
    if ( !( other instanceof MarketCalendarRow ) )
       return false;
    return fieldsEqual( (MarketCalendarRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のMarketCalendarRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( MarketCalendarRow row )
  {
    if ( market_id != row.getMarketId() )
      return false;
    if ( trade_date == null ) {
      if ( row.getTradeDate() != null )
        return false;
    } else if ( !trade_date.equals( row.getTradeDate() ) ) {
        return false;
    }
    if ( holiday_flag == null ) {
      if ( row.getHolidayFlag() != null )
        return false;
    } else if ( !holiday_flag.equals( row.getHolidayFlag() ) ) {
        return false;
    }
    if ( trade_open_time == null ) {
      if ( row.getTradeOpenTime() != null )
        return false;
    } else if ( !trade_open_time.equals( row.getTradeOpenTime() ) ) {
        return false;
    }
    if ( trade_close_time == null ) {
      if ( row.getTradeCloseTime() != null )
        return false;
    } else if ( !trade_close_time.equals( row.getTradeCloseTime() ) ) {
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
      return  ((int) market_id)
        + (trade_date!=null? trade_date.hashCode(): 0) 
        + (holiday_flag!=null? holiday_flag.hashCode(): 0) 
        + (trade_open_time!=null? trade_open_time.hashCode(): 0) 
        + (trade_close_time!=null? trade_close_time.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
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
		map.put("market_id",new Long(market_id));
		map.put("trade_date",trade_date);
		if ( holiday_flag_is_set )
			map.put("holiday_flag",holiday_flag);
		if ( trade_open_time != null )
			map.put("trade_open_time",trade_open_time);
		if ( trade_close_time != null )
			map.put("trade_close_time",trade_close_time);
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
		if ( holiday_flag_is_modified )
			map.put("holiday_flag",holiday_flag);
		if ( trade_open_time_is_modified )
			map.put("trade_open_time",trade_open_time);
		if ( trade_close_time_is_modified )
			map.put("trade_close_time",trade_close_time);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( holiday_flag_is_set )
				map.put("holiday_flag",holiday_flag);
			map.put("trade_open_time",trade_open_time);
			map.put("trade_close_time",trade_close_time);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
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
   * <em>trade_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTradeDate()
  {
    return trade_date;
  }


  /** 
   * <em>trade_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeDateIsSet() {
    return trade_date_is_set;
  }


  /** 
   * <em>trade_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeDateIsModified() {
    return trade_date_is_modified;
  }


  /** 
   * <em>holiday_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public final com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getHolidayFlag()
  {
    return holiday_flag;
  }


  /** 
   * <em>holiday_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHolidayFlagIsSet() {
    return holiday_flag_is_set;
  }


  /** 
   * <em>holiday_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHolidayFlagIsModified() {
    return holiday_flag_is_modified;
  }


  /** 
   * <em>trade_open_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradeOpenTime()
  {
    return trade_open_time;
  }


  /** 
   * <em>trade_open_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeOpenTimeIsSet() {
    return trade_open_time_is_set;
  }


  /** 
   * <em>trade_open_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeOpenTimeIsModified() {
    return trade_open_time_is_modified;
  }


  /** 
   * <em>trade_close_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradeCloseTime()
  {
    return trade_close_time;
  }


  /** 
   * <em>trade_close_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeCloseTimeIsSet() {
    return trade_close_time_is_set;
  }


  /** 
   * <em>trade_close_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeCloseTimeIsModified() {
    return trade_close_time_is_modified;
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
    return new MarketCalendarPK(market_id, trade_date);
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
   * <em>trade_date</em>カラムの値を設定します。 
   *
   * @@param p_tradeDate <em>trade_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTradeDate( java.sql.Timestamp p_tradeDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_date = p_tradeDate;
    trade_date_is_set = true;
    trade_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>trade_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTradeDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trade_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    trade_date_is_set = true;
    trade_date_is_modified = true;
  }


  /** 
   * <em>holiday_flag</em>カラムの値を設定します。 
   *
   * @@param p_holidayFlag <em>holiday_flag</em>カラムの値をあらわす1桁以下のcom.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum値 
   */
  public final void setHolidayFlag( com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_holidayFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    holiday_flag = p_holidayFlag;
    holiday_flag_is_set = true;
    holiday_flag_is_modified = true;
  }


  /** 
   * <em>trade_open_time</em>カラムの値を設定します。 
   *
   * @@param p_tradeOpenTime <em>trade_open_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setTradeOpenTime( String p_tradeOpenTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_open_time = p_tradeOpenTime;
    trade_open_time_is_set = true;
    trade_open_time_is_modified = true;
  }


  /** 
   * <em>trade_close_time</em>カラムの値を設定します。 
   *
   * @@param p_tradeCloseTime <em>trade_close_time</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setTradeCloseTime( String p_tradeCloseTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_close_time = p_tradeCloseTime;
    trade_close_time_is_set = true;
    trade_close_time_is_modified = true;
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
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'h':
                if ( name.equals("holiday_flag") ) {
                    return this.holiday_flag;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return new Long( market_id );
                }
                break;
            case 't':
                if ( name.equals("trade_date") ) {
                    return this.trade_date;
                }
                else if ( name.equals("trade_open_time") ) {
                    return this.trade_open_time;
                }
                else if ( name.equals("trade_close_time") ) {
                    return this.trade_close_time;
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
            case 'h':
                if ( name.equals("holiday_flag") ) {
                    if ( !(value instanceof com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) )
                        throw new IllegalArgumentException( "value for 'holiday_flag' must be com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum: '"+value+"' is not." );
                    this.holiday_flag = (com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum) value;
                    if (this.holiday_flag_is_set)
                        this.holiday_flag_is_modified = true;
                    this.holiday_flag_is_set = true;
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
            case 't':
                if ( name.equals("trade_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'trade_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.trade_date = (java.sql.Timestamp) value;
                    if (this.trade_date_is_set)
                        this.trade_date_is_modified = true;
                    this.trade_date_is_set = true;
                    return;
                }
                else if ( name.equals("trade_open_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trade_open_time' must be String: '"+value+"' is not." );
                    this.trade_open_time = (String) value;
                    if (this.trade_open_time_is_set)
                        this.trade_open_time_is_modified = true;
                    this.trade_open_time_is_set = true;
                    return;
                }
                else if ( name.equals("trade_close_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trade_close_time' must be String: '"+value+"' is not." );
                    this.trade_close_time = (String) value;
                    if (this.trade_close_time_is_set)
                        this.trade_close_time_is_modified = true;
                    this.trade_close_time_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
