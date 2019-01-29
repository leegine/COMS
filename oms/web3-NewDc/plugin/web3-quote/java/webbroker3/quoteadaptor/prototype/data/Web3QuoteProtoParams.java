head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	Web3QuoteProtoParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.prototype.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * web3_quote_protoテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link Web3QuoteProtoRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link Web3QuoteProtoParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link Web3QuoteProtoParams}が{@@link Web3QuoteProtoRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see Web3QuoteProtoPK 
 * @@see Web3QuoteProtoRow 
 */
public class Web3QuoteProtoParams extends Params implements Web3QuoteProtoRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "web3_quote_proto";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = Web3QuoteProtoRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return Web3QuoteProtoRow.TYPE;
  }


  /** 
   * <em>quote_data_id</em>カラムの値 
   */
  public  long  quote_data_id;

  /** 
   * <em>quote_date</em>カラムの値 
   */
  public  String  quote_date;

  /** 
   * <em>real_type</em>カラムの値 
   */
  public  webbroker3.quoteadaptor.RealType  real_type;

  /** 
   * <em>data_type</em>カラムの値 
   */
  public  webbroker3.quoteadaptor.DataType  data_type;

  /** 
   * <em>market_code</em>カラムの値 
   */
  public  String  market_code;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>contract_month</em>カラムの値 
   */
  public  String  contract_month;

  /** 
   * <em>put_and_call</em>カラムの値 
   */
  public  String  put_and_call;

  /** 
   * <em>strike_price</em>カラムの値 
   */
  public  Double  strike_price;

  /** 
   * <em>open_price</em>カラムの値 
   */
  public  Double  open_price;

  /** 
   * <em>open_price_time</em>カラムの値 
   */
  public  String  open_price_time;

  /** 
   * <em>high_price</em>カラムの値 
   */
  public  Double  high_price;

  /** 
   * <em>high_price_time</em>カラムの値 
   */
  public  String  high_price_time;

  /** 
   * <em>low_price</em>カラムの値 
   */
  public  Double  low_price;

  /** 
   * <em>low_price_time</em>カラムの値 
   */
  public  String  low_price_time;

  /** 
   * <em>current_price</em>カラムの値 
   */
  public  Double  current_price;

  /** 
   * <em>current_price_time</em>カラムの値 
   */
  public  String  current_price_time;

  /** 
   * <em>current_price_flag</em>カラムの値 
   */
  public  webbroker3.quoteadaptor.CurrentPriceFlag  current_price_flag;

  /** 
   * <em>change</em>カラムの値 
   */
  public  Double  change;

  /** 
   * <em>volume</em>カラムの値 
   */
  public  Double  volume;

  /** 
   * <em>volume_time</em>カラムの値 
   */
  public  String  volume_time;

  /** 
   * <em>ask_price_title</em>カラムの値 
   */
  public  webbroker3.quoteadaptor.AskPriceTitle  ask_price_title;

  /** 
   * <em>ask_price</em>カラムの値 
   */
  public  Double  ask_price;

  /** 
   * <em>ask_price_time</em>カラムの値 
   */
  public  String  ask_price_time;

  /** 
   * <em>bid_price_title</em>カラムの値 
   */
  public  webbroker3.quoteadaptor.BidPriceTitle  bid_price_title;

  /** 
   * <em>bid_price</em>カラムの値 
   */
  public  Double  bid_price;

  /** 
   * <em>bid_price_time</em>カラムの値 
   */
  public  String  bid_price_time;

  /** 
   * <em>base_price</em>カラムの値 
   */
  public  Double  base_price;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean quote_data_id_is_set = false;
  private boolean quote_data_id_is_modified = false;
  private boolean quote_date_is_set = false;
  private boolean quote_date_is_modified = false;
  private boolean real_type_is_set = false;
  private boolean real_type_is_modified = false;
  private boolean data_type_is_set = false;
  private boolean data_type_is_modified = false;
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean contract_month_is_set = false;
  private boolean contract_month_is_modified = false;
  private boolean put_and_call_is_set = false;
  private boolean put_and_call_is_modified = false;
  private boolean strike_price_is_set = false;
  private boolean strike_price_is_modified = false;
  private boolean open_price_is_set = false;
  private boolean open_price_is_modified = false;
  private boolean open_price_time_is_set = false;
  private boolean open_price_time_is_modified = false;
  private boolean high_price_is_set = false;
  private boolean high_price_is_modified = false;
  private boolean high_price_time_is_set = false;
  private boolean high_price_time_is_modified = false;
  private boolean low_price_is_set = false;
  private boolean low_price_is_modified = false;
  private boolean low_price_time_is_set = false;
  private boolean low_price_time_is_modified = false;
  private boolean current_price_is_set = false;
  private boolean current_price_is_modified = false;
  private boolean current_price_time_is_set = false;
  private boolean current_price_time_is_modified = false;
  private boolean current_price_flag_is_set = false;
  private boolean current_price_flag_is_modified = false;
  private boolean change_is_set = false;
  private boolean change_is_modified = false;
  private boolean volume_is_set = false;
  private boolean volume_is_modified = false;
  private boolean volume_time_is_set = false;
  private boolean volume_time_is_modified = false;
  private boolean ask_price_title_is_set = false;
  private boolean ask_price_title_is_modified = false;
  private boolean ask_price_is_set = false;
  private boolean ask_price_is_modified = false;
  private boolean ask_price_time_is_set = false;
  private boolean ask_price_time_is_modified = false;
  private boolean bid_price_title_is_set = false;
  private boolean bid_price_title_is_modified = false;
  private boolean bid_price_is_set = false;
  private boolean bid_price_is_modified = false;
  private boolean bid_price_time_is_set = false;
  private boolean bid_price_time_is_modified = false;
  private boolean base_price_is_set = false;
  private boolean base_price_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "quote_data_id=" + quote_data_id
      + "," + "quote_date=" +quote_date
      + "," + "real_type=" +real_type
      + "," + "data_type=" +data_type
      + "," + "market_code=" +market_code
      + "," + "product_code=" +product_code
      + "," + "contract_month=" +contract_month
      + "," + "put_and_call=" +put_and_call
      + "," + "strike_price=" +strike_price
      + "," + "open_price=" +open_price
      + "," + "open_price_time=" +open_price_time
      + "," + "high_price=" +high_price
      + "," + "high_price_time=" +high_price_time
      + "," + "low_price=" +low_price
      + "," + "low_price_time=" +low_price_time
      + "," + "current_price=" +current_price
      + "," + "current_price_time=" +current_price_time
      + "," + "current_price_flag=" +current_price_flag
      + "," + "change=" +change
      + "," + "volume=" +volume
      + "," + "volume_time=" +volume_time
      + "," + "ask_price_title=" +ask_price_title
      + "," + "ask_price=" +ask_price
      + "," + "ask_price_time=" +ask_price_time
      + "," + "bid_price_title=" +bid_price_title
      + "," + "bid_price=" +bid_price
      + "," + "bid_price_time=" +bid_price_time
      + "," + "base_price=" +base_price
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のWeb3QuoteProtoParamsオブジェクトを作成します。 
   */
  public Web3QuoteProtoParams() {
    quote_data_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のWeb3QuoteProtoRowオブジェクトの値を利用してWeb3QuoteProtoParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するWeb3QuoteProtoRowオブジェクト 
   */
  public Web3QuoteProtoParams( Web3QuoteProtoRow p_row )
  {
    quote_data_id = p_row.getQuoteDataId();
    quote_data_id_is_set = p_row.getQuoteDataIdIsSet();
    quote_data_id_is_modified = p_row.getQuoteDataIdIsModified();
    quote_date = p_row.getQuoteDate();
    quote_date_is_set = p_row.getQuoteDateIsSet();
    quote_date_is_modified = p_row.getQuoteDateIsModified();
    real_type = p_row.getRealType();
    real_type_is_set = p_row.getRealTypeIsSet();
    real_type_is_modified = p_row.getRealTypeIsModified();
    data_type = p_row.getDataType();
    data_type_is_set = p_row.getDataTypeIsSet();
    data_type_is_modified = p_row.getDataTypeIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    contract_month = p_row.getContractMonth();
    contract_month_is_set = p_row.getContractMonthIsSet();
    contract_month_is_modified = p_row.getContractMonthIsModified();
    put_and_call = p_row.getPutAndCall();
    put_and_call_is_set = p_row.getPutAndCallIsSet();
    put_and_call_is_modified = p_row.getPutAndCallIsModified();
    if ( !p_row.getStrikePriceIsNull() )
      strike_price = new Double( p_row.getStrikePrice() );
    strike_price_is_set = p_row.getStrikePriceIsSet();
    strike_price_is_modified = p_row.getStrikePriceIsModified();
    if ( !p_row.getOpenPriceIsNull() )
      open_price = new Double( p_row.getOpenPrice() );
    open_price_is_set = p_row.getOpenPriceIsSet();
    open_price_is_modified = p_row.getOpenPriceIsModified();
    open_price_time = p_row.getOpenPriceTime();
    open_price_time_is_set = p_row.getOpenPriceTimeIsSet();
    open_price_time_is_modified = p_row.getOpenPriceTimeIsModified();
    if ( !p_row.getHighPriceIsNull() )
      high_price = new Double( p_row.getHighPrice() );
    high_price_is_set = p_row.getHighPriceIsSet();
    high_price_is_modified = p_row.getHighPriceIsModified();
    high_price_time = p_row.getHighPriceTime();
    high_price_time_is_set = p_row.getHighPriceTimeIsSet();
    high_price_time_is_modified = p_row.getHighPriceTimeIsModified();
    if ( !p_row.getLowPriceIsNull() )
      low_price = new Double( p_row.getLowPrice() );
    low_price_is_set = p_row.getLowPriceIsSet();
    low_price_is_modified = p_row.getLowPriceIsModified();
    low_price_time = p_row.getLowPriceTime();
    low_price_time_is_set = p_row.getLowPriceTimeIsSet();
    low_price_time_is_modified = p_row.getLowPriceTimeIsModified();
    if ( !p_row.getCurrentPriceIsNull() )
      current_price = new Double( p_row.getCurrentPrice() );
    current_price_is_set = p_row.getCurrentPriceIsSet();
    current_price_is_modified = p_row.getCurrentPriceIsModified();
    current_price_time = p_row.getCurrentPriceTime();
    current_price_time_is_set = p_row.getCurrentPriceTimeIsSet();
    current_price_time_is_modified = p_row.getCurrentPriceTimeIsModified();
    current_price_flag = p_row.getCurrentPriceFlag();
    current_price_flag_is_set = p_row.getCurrentPriceFlagIsSet();
    current_price_flag_is_modified = p_row.getCurrentPriceFlagIsModified();
    if ( !p_row.getChangeIsNull() )
      change = new Double( p_row.getChange() );
    change_is_set = p_row.getChangeIsSet();
    change_is_modified = p_row.getChangeIsModified();
    if ( !p_row.getVolumeIsNull() )
      volume = new Double( p_row.getVolume() );
    volume_is_set = p_row.getVolumeIsSet();
    volume_is_modified = p_row.getVolumeIsModified();
    volume_time = p_row.getVolumeTime();
    volume_time_is_set = p_row.getVolumeTimeIsSet();
    volume_time_is_modified = p_row.getVolumeTimeIsModified();
    ask_price_title = p_row.getAskPriceTitle();
    ask_price_title_is_set = p_row.getAskPriceTitleIsSet();
    ask_price_title_is_modified = p_row.getAskPriceTitleIsModified();
    if ( !p_row.getAskPriceIsNull() )
      ask_price = new Double( p_row.getAskPrice() );
    ask_price_is_set = p_row.getAskPriceIsSet();
    ask_price_is_modified = p_row.getAskPriceIsModified();
    ask_price_time = p_row.getAskPriceTime();
    ask_price_time_is_set = p_row.getAskPriceTimeIsSet();
    ask_price_time_is_modified = p_row.getAskPriceTimeIsModified();
    bid_price_title = p_row.getBidPriceTitle();
    bid_price_title_is_set = p_row.getBidPriceTitleIsSet();
    bid_price_title_is_modified = p_row.getBidPriceTitleIsModified();
    if ( !p_row.getBidPriceIsNull() )
      bid_price = new Double( p_row.getBidPrice() );
    bid_price_is_set = p_row.getBidPriceIsSet();
    bid_price_is_modified = p_row.getBidPriceIsModified();
    bid_price_time = p_row.getBidPriceTime();
    bid_price_time_is_set = p_row.getBidPriceTimeIsSet();
    bid_price_time_is_modified = p_row.getBidPriceTimeIsModified();
    if ( !p_row.getBasePriceIsNull() )
      base_price = new Double( p_row.getBasePrice() );
    base_price_is_set = p_row.getBasePriceIsSet();
    base_price_is_modified = p_row.getBasePriceIsModified();
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
      quote_date_is_set = true;
      quote_date_is_modified = true;
      real_type_is_set = true;
      real_type_is_modified = true;
      data_type_is_set = true;
      data_type_is_modified = true;
      market_code_is_set = true;
      market_code_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      contract_month_is_set = true;
      contract_month_is_modified = true;
      put_and_call_is_set = true;
      put_and_call_is_modified = true;
      strike_price_is_set = true;
      strike_price_is_modified = true;
      open_price_is_set = true;
      open_price_is_modified = true;
      open_price_time_is_set = true;
      open_price_time_is_modified = true;
      high_price_is_set = true;
      high_price_is_modified = true;
      high_price_time_is_set = true;
      high_price_time_is_modified = true;
      low_price_is_set = true;
      low_price_is_modified = true;
      low_price_time_is_set = true;
      low_price_time_is_modified = true;
      current_price_is_set = true;
      current_price_is_modified = true;
      current_price_time_is_set = true;
      current_price_time_is_modified = true;
      current_price_flag_is_set = true;
      current_price_flag_is_modified = true;
      change_is_set = true;
      change_is_modified = true;
      volume_is_set = true;
      volume_is_modified = true;
      volume_time_is_set = true;
      volume_time_is_modified = true;
      ask_price_title_is_set = true;
      ask_price_title_is_modified = true;
      ask_price_is_set = true;
      ask_price_is_modified = true;
      ask_price_time_is_set = true;
      ask_price_time_is_modified = true;
      bid_price_title_is_set = true;
      bid_price_title_is_modified = true;
      bid_price_is_set = true;
      bid_price_is_modified = true;
      bid_price_time_is_set = true;
      bid_price_time_is_modified = true;
      base_price_is_set = true;
      base_price_is_modified = true;
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
    if ( !( other instanceof Web3QuoteProtoRow ) )
       return false;
    return fieldsEqual( (Web3QuoteProtoRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のWeb3QuoteProtoRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( Web3QuoteProtoRow row )
  {
    if ( quote_data_id != row.getQuoteDataId() )
      return false;
    if ( quote_date == null ) {
      if ( row.getQuoteDate() != null )
        return false;
    } else if ( !quote_date.equals( row.getQuoteDate() ) ) {
        return false;
    }
    if ( real_type == null ) {
      if ( row.getRealType() != null )
        return false;
    } else if ( !real_type.equals( row.getRealType() ) ) {
        return false;
    }
    if ( data_type == null ) {
      if ( row.getDataType() != null )
        return false;
    } else if ( !data_type.equals( row.getDataType() ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( contract_month == null ) {
      if ( row.getContractMonth() != null )
        return false;
    } else if ( !contract_month.equals( row.getContractMonth() ) ) {
        return false;
    }
    if ( put_and_call == null ) {
      if ( row.getPutAndCall() != null )
        return false;
    } else if ( !put_and_call.equals( row.getPutAndCall() ) ) {
        return false;
    }
    if ( strike_price == null ) {
      if ( !row.getStrikePriceIsNull() )
        return false;
    } else if ( row.getStrikePriceIsNull() || ( strike_price.doubleValue() != row.getStrikePrice() ) ) {
        return false;
    }
    if ( open_price == null ) {
      if ( !row.getOpenPriceIsNull() )
        return false;
    } else if ( row.getOpenPriceIsNull() || ( open_price.doubleValue() != row.getOpenPrice() ) ) {
        return false;
    }
    if ( open_price_time == null ) {
      if ( row.getOpenPriceTime() != null )
        return false;
    } else if ( !open_price_time.equals( row.getOpenPriceTime() ) ) {
        return false;
    }
    if ( high_price == null ) {
      if ( !row.getHighPriceIsNull() )
        return false;
    } else if ( row.getHighPriceIsNull() || ( high_price.doubleValue() != row.getHighPrice() ) ) {
        return false;
    }
    if ( high_price_time == null ) {
      if ( row.getHighPriceTime() != null )
        return false;
    } else if ( !high_price_time.equals( row.getHighPriceTime() ) ) {
        return false;
    }
    if ( low_price == null ) {
      if ( !row.getLowPriceIsNull() )
        return false;
    } else if ( row.getLowPriceIsNull() || ( low_price.doubleValue() != row.getLowPrice() ) ) {
        return false;
    }
    if ( low_price_time == null ) {
      if ( row.getLowPriceTime() != null )
        return false;
    } else if ( !low_price_time.equals( row.getLowPriceTime() ) ) {
        return false;
    }
    if ( current_price == null ) {
      if ( !row.getCurrentPriceIsNull() )
        return false;
    } else if ( row.getCurrentPriceIsNull() || ( current_price.doubleValue() != row.getCurrentPrice() ) ) {
        return false;
    }
    if ( current_price_time == null ) {
      if ( row.getCurrentPriceTime() != null )
        return false;
    } else if ( !current_price_time.equals( row.getCurrentPriceTime() ) ) {
        return false;
    }
    if ( current_price_flag == null ) {
      if ( row.getCurrentPriceFlag() != null )
        return false;
    } else if ( !current_price_flag.equals( row.getCurrentPriceFlag() ) ) {
        return false;
    }
    if ( change == null ) {
      if ( !row.getChangeIsNull() )
        return false;
    } else if ( row.getChangeIsNull() || ( change.doubleValue() != row.getChange() ) ) {
        return false;
    }
    if ( volume == null ) {
      if ( !row.getVolumeIsNull() )
        return false;
    } else if ( row.getVolumeIsNull() || ( volume.doubleValue() != row.getVolume() ) ) {
        return false;
    }
    if ( volume_time == null ) {
      if ( row.getVolumeTime() != null )
        return false;
    } else if ( !volume_time.equals( row.getVolumeTime() ) ) {
        return false;
    }
    if ( ask_price_title == null ) {
      if ( row.getAskPriceTitle() != null )
        return false;
    } else if ( !ask_price_title.equals( row.getAskPriceTitle() ) ) {
        return false;
    }
    if ( ask_price == null ) {
      if ( !row.getAskPriceIsNull() )
        return false;
    } else if ( row.getAskPriceIsNull() || ( ask_price.doubleValue() != row.getAskPrice() ) ) {
        return false;
    }
    if ( ask_price_time == null ) {
      if ( row.getAskPriceTime() != null )
        return false;
    } else if ( !ask_price_time.equals( row.getAskPriceTime() ) ) {
        return false;
    }
    if ( bid_price_title == null ) {
      if ( row.getBidPriceTitle() != null )
        return false;
    } else if ( !bid_price_title.equals( row.getBidPriceTitle() ) ) {
        return false;
    }
    if ( bid_price == null ) {
      if ( !row.getBidPriceIsNull() )
        return false;
    } else if ( row.getBidPriceIsNull() || ( bid_price.doubleValue() != row.getBidPrice() ) ) {
        return false;
    }
    if ( bid_price_time == null ) {
      if ( row.getBidPriceTime() != null )
        return false;
    } else if ( !bid_price_time.equals( row.getBidPriceTime() ) ) {
        return false;
    }
    if ( base_price == null ) {
      if ( !row.getBasePriceIsNull() )
        return false;
    } else if ( row.getBasePriceIsNull() || ( base_price.doubleValue() != row.getBasePrice() ) ) {
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
      return  ((int) quote_data_id)
        + (quote_date!=null? quote_date.hashCode(): 0) 
        + (real_type!=null? real_type.hashCode(): 0) 
        + (data_type!=null? data_type.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (contract_month!=null? contract_month.hashCode(): 0) 
        + (put_and_call!=null? put_and_call.hashCode(): 0) 
        + (strike_price!=null? strike_price.hashCode(): 0) 
        + (open_price!=null? open_price.hashCode(): 0) 
        + (open_price_time!=null? open_price_time.hashCode(): 0) 
        + (high_price!=null? high_price.hashCode(): 0) 
        + (high_price_time!=null? high_price_time.hashCode(): 0) 
        + (low_price!=null? low_price.hashCode(): 0) 
        + (low_price_time!=null? low_price_time.hashCode(): 0) 
        + (current_price!=null? current_price.hashCode(): 0) 
        + (current_price_time!=null? current_price_time.hashCode(): 0) 
        + (current_price_flag!=null? current_price_flag.hashCode(): 0) 
        + (change!=null? change.hashCode(): 0) 
        + (volume!=null? volume.hashCode(): 0) 
        + (volume_time!=null? volume_time.hashCode(): 0) 
        + (ask_price_title!=null? ask_price_title.hashCode(): 0) 
        + (ask_price!=null? ask_price.hashCode(): 0) 
        + (ask_price_time!=null? ask_price_time.hashCode(): 0) 
        + (bid_price_title!=null? bid_price_title.hashCode(): 0) 
        + (bid_price!=null? bid_price.hashCode(): 0) 
        + (bid_price_time!=null? bid_price_time.hashCode(): 0) 
        + (base_price!=null? base_price.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !quote_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'quote_date' must be set before inserting.");
		if ( !market_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_code' must be set before inserting.");
		if ( !product_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_code' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("quote_data_id",new Long(quote_data_id));
		map.put("quote_date",quote_date);
		if ( real_type_is_set )
			map.put("real_type",real_type);
		if ( data_type_is_set )
			map.put("data_type",data_type);
		map.put("market_code",market_code);
		map.put("product_code",product_code);
		if ( contract_month != null )
			map.put("contract_month",contract_month);
		if ( put_and_call != null )
			map.put("put_and_call",put_and_call);
		if ( strike_price != null )
			map.put("strike_price",strike_price);
		if ( open_price != null )
			map.put("open_price",open_price);
		if ( open_price_time != null )
			map.put("open_price_time",open_price_time);
		if ( high_price != null )
			map.put("high_price",high_price);
		if ( high_price_time != null )
			map.put("high_price_time",high_price_time);
		if ( low_price != null )
			map.put("low_price",low_price);
		if ( low_price_time != null )
			map.put("low_price_time",low_price_time);
		if ( current_price != null )
			map.put("current_price",current_price);
		if ( current_price_time != null )
			map.put("current_price_time",current_price_time);
		if ( current_price_flag_is_set )
			map.put("current_price_flag",current_price_flag);
		if ( change != null )
			map.put("change",change);
		if ( volume != null )
			map.put("volume",volume);
		if ( volume_time != null )
			map.put("volume_time",volume_time);
		if ( ask_price_title_is_set )
			map.put("ask_price_title",ask_price_title);
		if ( ask_price != null )
			map.put("ask_price",ask_price);
		if ( ask_price_time != null )
			map.put("ask_price_time",ask_price_time);
		if ( bid_price_title_is_set )
			map.put("bid_price_title",bid_price_title);
		if ( bid_price != null )
			map.put("bid_price",bid_price);
		if ( bid_price_time != null )
			map.put("bid_price_time",bid_price_time);
		if ( base_price != null )
			map.put("base_price",base_price);
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
		if ( quote_date_is_modified )
			map.put("quote_date",quote_date);
		if ( real_type_is_modified )
			map.put("real_type",real_type);
		if ( data_type_is_modified )
			map.put("data_type",data_type);
		if ( market_code_is_modified )
			map.put("market_code",market_code);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( contract_month_is_modified )
			map.put("contract_month",contract_month);
		if ( put_and_call_is_modified )
			map.put("put_and_call",put_and_call);
		if ( strike_price_is_modified )
			map.put("strike_price",strike_price);
		if ( open_price_is_modified )
			map.put("open_price",open_price);
		if ( open_price_time_is_modified )
			map.put("open_price_time",open_price_time);
		if ( high_price_is_modified )
			map.put("high_price",high_price);
		if ( high_price_time_is_modified )
			map.put("high_price_time",high_price_time);
		if ( low_price_is_modified )
			map.put("low_price",low_price);
		if ( low_price_time_is_modified )
			map.put("low_price_time",low_price_time);
		if ( current_price_is_modified )
			map.put("current_price",current_price);
		if ( current_price_time_is_modified )
			map.put("current_price_time",current_price_time);
		if ( current_price_flag_is_modified )
			map.put("current_price_flag",current_price_flag);
		if ( change_is_modified )
			map.put("change",change);
		if ( volume_is_modified )
			map.put("volume",volume);
		if ( volume_time_is_modified )
			map.put("volume_time",volume_time);
		if ( ask_price_title_is_modified )
			map.put("ask_price_title",ask_price_title);
		if ( ask_price_is_modified )
			map.put("ask_price",ask_price);
		if ( ask_price_time_is_modified )
			map.put("ask_price_time",ask_price_time);
		if ( bid_price_title_is_modified )
			map.put("bid_price_title",bid_price_title);
		if ( bid_price_is_modified )
			map.put("bid_price",bid_price);
		if ( bid_price_time_is_modified )
			map.put("bid_price_time",bid_price_time);
		if ( base_price_is_modified )
			map.put("base_price",base_price);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( quote_date_is_set )
				map.put("quote_date",quote_date);
			if ( real_type_is_set )
				map.put("real_type",real_type);
			if ( data_type_is_set )
				map.put("data_type",data_type);
			if ( market_code_is_set )
				map.put("market_code",market_code);
			if ( product_code_is_set )
				map.put("product_code",product_code);
			map.put("contract_month",contract_month);
			map.put("put_and_call",put_and_call);
			map.put("strike_price",strike_price);
			map.put("open_price",open_price);
			map.put("open_price_time",open_price_time);
			map.put("high_price",high_price);
			map.put("high_price_time",high_price_time);
			map.put("low_price",low_price);
			map.put("low_price_time",low_price_time);
			map.put("current_price",current_price);
			map.put("current_price_time",current_price_time);
			if ( current_price_flag_is_set )
				map.put("current_price_flag",current_price_flag);
			map.put("change",change);
			map.put("volume",volume);
			map.put("volume_time",volume_time);
			if ( ask_price_title_is_set )
				map.put("ask_price_title",ask_price_title);
			map.put("ask_price",ask_price);
			map.put("ask_price_time",ask_price_time);
			if ( bid_price_title_is_set )
				map.put("bid_price_title",bid_price_title);
			map.put("bid_price",bid_price);
			map.put("bid_price_time",bid_price_time);
			map.put("base_price",base_price);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>quote_data_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getQuoteDataId()
  {
    return quote_data_id;
  }


  /** 
   * <em>quote_data_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuoteDataIdIsSet() {
    return quote_data_id_is_set;
  }


  /** 
   * <em>quote_data_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuoteDataIdIsModified() {
    return quote_data_id_is_modified;
  }


  /** 
   * <em>quote_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getQuoteDate()
  {
    return quote_date;
  }


  /** 
   * <em>quote_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuoteDateIsSet() {
    return quote_date_is_set;
  }


  /** 
   * <em>quote_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuoteDateIsModified() {
    return quote_date_is_modified;
  }


  /** 
   * <em>real_type</em>カラムの値を取得します。
   * @@return webbroker3.quoteadaptor.RealTypeの値 
   */
  public final webbroker3.quoteadaptor.RealType getRealType()
  {
    return real_type;
  }


  /** 
   * <em>real_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealTypeIsSet() {
    return real_type_is_set;
  }


  /** 
   * <em>real_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealTypeIsModified() {
    return real_type_is_modified;
  }


  /** 
   * <em>data_type</em>カラムの値を取得します。
   * @@return webbroker3.quoteadaptor.DataTypeの値 
   */
  public final webbroker3.quoteadaptor.DataType getDataType()
  {
    return data_type;
  }


  /** 
   * <em>data_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataTypeIsSet() {
    return data_type_is_set;
  }


  /** 
   * <em>data_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDataTypeIsModified() {
    return data_type_is_modified;
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
   * <em>product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductCode()
  {
    return product_code;
  }


  /** 
   * <em>product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductCodeIsSet() {
    return product_code_is_set;
  }


  /** 
   * <em>product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductCodeIsModified() {
    return product_code_is_modified;
  }


  /** 
   * <em>contract_month</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getContractMonth()
  {
    return contract_month;
  }


  /** 
   * <em>contract_month</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractMonthIsSet() {
    return contract_month_is_set;
  }


  /** 
   * <em>contract_month</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractMonthIsModified() {
    return contract_month_is_modified;
  }


  /** 
   * <em>put_and_call</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPutAndCall()
  {
    return put_and_call;
  }


  /** 
   * <em>put_and_call</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPutAndCallIsSet() {
    return put_and_call_is_set;
  }


  /** 
   * <em>put_and_call</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPutAndCallIsModified() {
    return put_and_call_is_modified;
  }


  /** 
   * <em>strike_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getStrikePrice()
  {
    return ( strike_price==null? ((double)0): strike_price.doubleValue() );
  }


  /** 
   * <em>strike_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getStrikePriceIsNull()
  {
    return strike_price == null;
  }


  /** 
   * <em>strike_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStrikePriceIsSet() {
    return strike_price_is_set;
  }


  /** 
   * <em>strike_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStrikePriceIsModified() {
    return strike_price_is_modified;
  }


  /** 
   * <em>open_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOpenPrice()
  {
    return ( open_price==null? ((double)0): open_price.doubleValue() );
  }


  /** 
   * <em>open_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOpenPriceIsNull()
  {
    return open_price == null;
  }


  /** 
   * <em>open_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenPriceIsSet() {
    return open_price_is_set;
  }


  /** 
   * <em>open_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenPriceIsModified() {
    return open_price_is_modified;
  }


  /** 
   * <em>open_price_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOpenPriceTime()
  {
    return open_price_time;
  }


  /** 
   * <em>open_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenPriceTimeIsSet() {
    return open_price_time_is_set;
  }


  /** 
   * <em>open_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenPriceTimeIsModified() {
    return open_price_time_is_modified;
  }


  /** 
   * <em>high_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getHighPrice()
  {
    return ( high_price==null? ((double)0): high_price.doubleValue() );
  }


  /** 
   * <em>high_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getHighPriceIsNull()
  {
    return high_price == null;
  }


  /** 
   * <em>high_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHighPriceIsSet() {
    return high_price_is_set;
  }


  /** 
   * <em>high_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHighPriceIsModified() {
    return high_price_is_modified;
  }


  /** 
   * <em>high_price_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getHighPriceTime()
  {
    return high_price_time;
  }


  /** 
   * <em>high_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHighPriceTimeIsSet() {
    return high_price_time_is_set;
  }


  /** 
   * <em>high_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getHighPriceTimeIsModified() {
    return high_price_time_is_modified;
  }


  /** 
   * <em>low_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getLowPrice()
  {
    return ( low_price==null? ((double)0): low_price.doubleValue() );
  }


  /** 
   * <em>low_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getLowPriceIsNull()
  {
    return low_price == null;
  }


  /** 
   * <em>low_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLowPriceIsSet() {
    return low_price_is_set;
  }


  /** 
   * <em>low_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLowPriceIsModified() {
    return low_price_is_modified;
  }


  /** 
   * <em>low_price_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLowPriceTime()
  {
    return low_price_time;
  }


  /** 
   * <em>low_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLowPriceTimeIsSet() {
    return low_price_time_is_set;
  }


  /** 
   * <em>low_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLowPriceTimeIsModified() {
    return low_price_time_is_modified;
  }


  /** 
   * <em>current_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCurrentPrice()
  {
    return ( current_price==null? ((double)0): current_price.doubleValue() );
  }


  /** 
   * <em>current_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCurrentPriceIsNull()
  {
    return current_price == null;
  }


  /** 
   * <em>current_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentPriceIsSet() {
    return current_price_is_set;
  }


  /** 
   * <em>current_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentPriceIsModified() {
    return current_price_is_modified;
  }


  /** 
   * <em>current_price_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCurrentPriceTime()
  {
    return current_price_time;
  }


  /** 
   * <em>current_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentPriceTimeIsSet() {
    return current_price_time_is_set;
  }


  /** 
   * <em>current_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentPriceTimeIsModified() {
    return current_price_time_is_modified;
  }


  /** 
   * <em>current_price_flag</em>カラムの値を取得します。
   * @@return webbroker3.quoteadaptor.CurrentPriceFlagの値 
   */
  public final webbroker3.quoteadaptor.CurrentPriceFlag getCurrentPriceFlag()
  {
    return current_price_flag;
  }


  /** 
   * <em>current_price_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentPriceFlagIsSet() {
    return current_price_flag_is_set;
  }


  /** 
   * <em>current_price_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentPriceFlagIsModified() {
    return current_price_flag_is_modified;
  }


  /** 
   * <em>change</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getChange()
  {
    return ( change==null? ((double)0): change.doubleValue() );
  }


  /** 
   * <em>change</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getChangeIsNull()
  {
    return change == null;
  }


  /** 
   * <em>change</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeIsSet() {
    return change_is_set;
  }


  /** 
   * <em>change</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeIsModified() {
    return change_is_modified;
  }


  /** 
   * <em>volume</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getVolume()
  {
    return ( volume==null? ((double)0): volume.doubleValue() );
  }


  /** 
   * <em>volume</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getVolumeIsNull()
  {
    return volume == null;
  }


  /** 
   * <em>volume</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVolumeIsSet() {
    return volume_is_set;
  }


  /** 
   * <em>volume</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVolumeIsModified() {
    return volume_is_modified;
  }


  /** 
   * <em>volume_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getVolumeTime()
  {
    return volume_time;
  }


  /** 
   * <em>volume_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVolumeTimeIsSet() {
    return volume_time_is_set;
  }


  /** 
   * <em>volume_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getVolumeTimeIsModified() {
    return volume_time_is_modified;
  }


  /** 
   * <em>ask_price_title</em>カラムの値を取得します。
   * @@return webbroker3.quoteadaptor.AskPriceTitleの値 
   */
  public final webbroker3.quoteadaptor.AskPriceTitle getAskPriceTitle()
  {
    return ask_price_title;
  }


  /** 
   * <em>ask_price_title</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAskPriceTitleIsSet() {
    return ask_price_title_is_set;
  }


  /** 
   * <em>ask_price_title</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAskPriceTitleIsModified() {
    return ask_price_title_is_modified;
  }


  /** 
   * <em>ask_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAskPrice()
  {
    return ( ask_price==null? ((double)0): ask_price.doubleValue() );
  }


  /** 
   * <em>ask_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAskPriceIsNull()
  {
    return ask_price == null;
  }


  /** 
   * <em>ask_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAskPriceIsSet() {
    return ask_price_is_set;
  }


  /** 
   * <em>ask_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAskPriceIsModified() {
    return ask_price_is_modified;
  }


  /** 
   * <em>ask_price_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAskPriceTime()
  {
    return ask_price_time;
  }


  /** 
   * <em>ask_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAskPriceTimeIsSet() {
    return ask_price_time_is_set;
  }


  /** 
   * <em>ask_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAskPriceTimeIsModified() {
    return ask_price_time_is_modified;
  }


  /** 
   * <em>bid_price_title</em>カラムの値を取得します。
   * @@return webbroker3.quoteadaptor.BidPriceTitleの値 
   */
  public final webbroker3.quoteadaptor.BidPriceTitle getBidPriceTitle()
  {
    return bid_price_title;
  }


  /** 
   * <em>bid_price_title</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBidPriceTitleIsSet() {
    return bid_price_title_is_set;
  }


  /** 
   * <em>bid_price_title</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBidPriceTitleIsModified() {
    return bid_price_title_is_modified;
  }


  /** 
   * <em>bid_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBidPrice()
  {
    return ( bid_price==null? ((double)0): bid_price.doubleValue() );
  }


  /** 
   * <em>bid_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBidPriceIsNull()
  {
    return bid_price == null;
  }


  /** 
   * <em>bid_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBidPriceIsSet() {
    return bid_price_is_set;
  }


  /** 
   * <em>bid_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBidPriceIsModified() {
    return bid_price_is_modified;
  }


  /** 
   * <em>bid_price_time</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBidPriceTime()
  {
    return bid_price_time;
  }


  /** 
   * <em>bid_price_time</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBidPriceTimeIsSet() {
    return bid_price_time_is_set;
  }


  /** 
   * <em>bid_price_time</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBidPriceTimeIsModified() {
    return bid_price_time_is_modified;
  }


  /** 
   * <em>base_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBasePrice()
  {
    return ( base_price==null? ((double)0): base_price.doubleValue() );
  }


  /** 
   * <em>base_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBasePriceIsNull()
  {
    return base_price == null;
  }


  /** 
   * <em>base_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBasePriceIsSet() {
    return base_price_is_set;
  }


  /** 
   * <em>base_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBasePriceIsModified() {
    return base_price_is_modified;
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
    return new Web3QuoteProtoPK(quote_data_id);
  }


  /** 
   * <em>quote_data_id</em>カラムの値を設定します。 
   *
   * @@param p_quoteDataId <em>quote_data_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setQuoteDataId( long p_quoteDataId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quote_data_id = p_quoteDataId;
    quote_data_id_is_set = true;
    quote_data_id_is_modified = true;
  }


  /** 
   * <em>quote_date</em>カラムの値を設定します。 
   *
   * @@param p_quoteDate <em>quote_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setQuoteDate( String p_quoteDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quote_date = p_quoteDate;
    quote_date_is_set = true;
    quote_date_is_modified = true;
  }


  /** 
   * <em>real_type</em>カラムの値を設定します。 
   *
   * @@param p_realType <em>real_type</em>カラムの値をあらわす1桁以下のwebbroker3.quoteadaptor.RealType値 
   */
  public final void setRealType( webbroker3.quoteadaptor.RealType p_realType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    real_type = p_realType;
    real_type_is_set = true;
    real_type_is_modified = true;
  }


  /** 
   * <em>data_type</em>カラムの値を設定します。 
   *
   * @@param p_dataType <em>data_type</em>カラムの値をあらわす1桁以下のwebbroker3.quoteadaptor.DataType値 
   */
  public final void setDataType( webbroker3.quoteadaptor.DataType p_dataType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    data_type = p_dataType;
    data_type_is_set = true;
    data_type_is_modified = true;
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
   * <em>product_code</em>カラムの値を設定します。 
   *
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>contract_month</em>カラムの値を設定します。 
   *
   * @@param p_contractMonth <em>contract_month</em>カラムの値をあらわす6桁以下のString値 
   */
  public final void setContractMonth( String p_contractMonth )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_month = p_contractMonth;
    contract_month_is_set = true;
    contract_month_is_modified = true;
  }


  /** 
   * <em>put_and_call</em>カラムの値を設定します。 
   *
   * @@param p_putAndCall <em>put_and_call</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPutAndCall( String p_putAndCall )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    put_and_call = p_putAndCall;
    put_and_call_is_set = true;
    put_and_call_is_modified = true;
  }


  /** 
   * <em>strike_price</em>カラムの値を設定します。 
   *
   * @@param p_strikePrice <em>strike_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setStrikePrice( double p_strikePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    strike_price = new Double(p_strikePrice);
    strike_price_is_set = true;
    strike_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>strike_price</em>カラムに値を設定します。 
   */
  public final void setStrikePrice( Double p_strikePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    strike_price = p_strikePrice;
    strike_price_is_set = true;
    strike_price_is_modified = true;
  }


  /** 
   * <em>open_price</em>カラムの値を設定します。 
   *
   * @@param p_openPrice <em>open_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOpenPrice( double p_openPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_price = new Double(p_openPrice);
    open_price_is_set = true;
    open_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>open_price</em>カラムに値を設定します。 
   */
  public final void setOpenPrice( Double p_openPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    open_price = p_openPrice;
    open_price_is_set = true;
    open_price_is_modified = true;
  }


  /** 
   * <em>open_price_time</em>カラムの値を設定します。 
   *
   * @@param p_openPriceTime <em>open_price_time</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setOpenPriceTime( String p_openPriceTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_price_time = p_openPriceTime;
    open_price_time_is_set = true;
    open_price_time_is_modified = true;
  }


  /** 
   * <em>high_price</em>カラムの値を設定します。 
   *
   * @@param p_highPrice <em>high_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setHighPrice( double p_highPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    high_price = new Double(p_highPrice);
    high_price_is_set = true;
    high_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>high_price</em>カラムに値を設定します。 
   */
  public final void setHighPrice( Double p_highPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    high_price = p_highPrice;
    high_price_is_set = true;
    high_price_is_modified = true;
  }


  /** 
   * <em>high_price_time</em>カラムの値を設定します。 
   *
   * @@param p_highPriceTime <em>high_price_time</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setHighPriceTime( String p_highPriceTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    high_price_time = p_highPriceTime;
    high_price_time_is_set = true;
    high_price_time_is_modified = true;
  }


  /** 
   * <em>low_price</em>カラムの値を設定します。 
   *
   * @@param p_lowPrice <em>low_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setLowPrice( double p_lowPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    low_price = new Double(p_lowPrice);
    low_price_is_set = true;
    low_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>low_price</em>カラムに値を設定します。 
   */
  public final void setLowPrice( Double p_lowPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    low_price = p_lowPrice;
    low_price_is_set = true;
    low_price_is_modified = true;
  }


  /** 
   * <em>low_price_time</em>カラムの値を設定します。 
   *
   * @@param p_lowPriceTime <em>low_price_time</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setLowPriceTime( String p_lowPriceTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    low_price_time = p_lowPriceTime;
    low_price_time_is_set = true;
    low_price_time_is_modified = true;
  }


  /** 
   * <em>current_price</em>カラムの値を設定します。 
   *
   * @@param p_currentPrice <em>current_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCurrentPrice( double p_currentPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    current_price = new Double(p_currentPrice);
    current_price_is_set = true;
    current_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>current_price</em>カラムに値を設定します。 
   */
  public final void setCurrentPrice( Double p_currentPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    current_price = p_currentPrice;
    current_price_is_set = true;
    current_price_is_modified = true;
  }


  /** 
   * <em>current_price_time</em>カラムの値を設定します。 
   *
   * @@param p_currentPriceTime <em>current_price_time</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setCurrentPriceTime( String p_currentPriceTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    current_price_time = p_currentPriceTime;
    current_price_time_is_set = true;
    current_price_time_is_modified = true;
  }


  /** 
   * <em>current_price_flag</em>カラムの値を設定します。 
   *
   * @@param p_currentPriceFlag <em>current_price_flag</em>カラムの値をあらわす1桁以下のwebbroker3.quoteadaptor.CurrentPriceFlag値 
   */
  public final void setCurrentPriceFlag( webbroker3.quoteadaptor.CurrentPriceFlag p_currentPriceFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    current_price_flag = p_currentPriceFlag;
    current_price_flag_is_set = true;
    current_price_flag_is_modified = true;
  }


  /** 
   * <em>change</em>カラムの値を設定します。 
   *
   * @@param p_change <em>change</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setChange( double p_change )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change = new Double(p_change);
    change_is_set = true;
    change_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>change</em>カラムに値を設定します。 
   */
  public final void setChange( Double p_change )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    change = p_change;
    change_is_set = true;
    change_is_modified = true;
  }


  /** 
   * <em>volume</em>カラムの値を設定します。 
   *
   * @@param p_volume <em>volume</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setVolume( double p_volume )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    volume = new Double(p_volume);
    volume_is_set = true;
    volume_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>volume</em>カラムに値を設定します。 
   */
  public final void setVolume( Double p_volume )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    volume = p_volume;
    volume_is_set = true;
    volume_is_modified = true;
  }


  /** 
   * <em>volume_time</em>カラムの値を設定します。 
   *
   * @@param p_volumeTime <em>volume_time</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setVolumeTime( String p_volumeTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    volume_time = p_volumeTime;
    volume_time_is_set = true;
    volume_time_is_modified = true;
  }


  /** 
   * <em>ask_price_title</em>カラムの値を設定します。 
   *
   * @@param p_askPriceTitle <em>ask_price_title</em>カラムの値をあらわす1桁以下のwebbroker3.quoteadaptor.AskPriceTitle値 
   */
  public final void setAskPriceTitle( webbroker3.quoteadaptor.AskPriceTitle p_askPriceTitle )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ask_price_title = p_askPriceTitle;
    ask_price_title_is_set = true;
    ask_price_title_is_modified = true;
  }


  /** 
   * <em>ask_price</em>カラムの値を設定します。 
   *
   * @@param p_askPrice <em>ask_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAskPrice( double p_askPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ask_price = new Double(p_askPrice);
    ask_price_is_set = true;
    ask_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ask_price</em>カラムに値を設定します。 
   */
  public final void setAskPrice( Double p_askPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ask_price = p_askPrice;
    ask_price_is_set = true;
    ask_price_is_modified = true;
  }


  /** 
   * <em>ask_price_time</em>カラムの値を設定します。 
   *
   * @@param p_askPriceTime <em>ask_price_time</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setAskPriceTime( String p_askPriceTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ask_price_time = p_askPriceTime;
    ask_price_time_is_set = true;
    ask_price_time_is_modified = true;
  }


  /** 
   * <em>bid_price_title</em>カラムの値を設定します。 
   *
   * @@param p_bidPriceTitle <em>bid_price_title</em>カラムの値をあらわす1桁以下のwebbroker3.quoteadaptor.BidPriceTitle値 
   */
  public final void setBidPriceTitle( webbroker3.quoteadaptor.BidPriceTitle p_bidPriceTitle )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bid_price_title = p_bidPriceTitle;
    bid_price_title_is_set = true;
    bid_price_title_is_modified = true;
  }


  /** 
   * <em>bid_price</em>カラムの値を設定します。 
   *
   * @@param p_bidPrice <em>bid_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBidPrice( double p_bidPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bid_price = new Double(p_bidPrice);
    bid_price_is_set = true;
    bid_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>bid_price</em>カラムに値を設定します。 
   */
  public final void setBidPrice( Double p_bidPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bid_price = p_bidPrice;
    bid_price_is_set = true;
    bid_price_is_modified = true;
  }


  /** 
   * <em>bid_price_time</em>カラムの値を設定します。 
   *
   * @@param p_bidPriceTime <em>bid_price_time</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setBidPriceTime( String p_bidPriceTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bid_price_time = p_bidPriceTime;
    bid_price_time_is_set = true;
    bid_price_time_is_modified = true;
  }


  /** 
   * <em>base_price</em>カラムの値を設定します。 
   *
   * @@param p_basePrice <em>base_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBasePrice( double p_basePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    base_price = new Double(p_basePrice);
    base_price_is_set = true;
    base_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>base_price</em>カラムに値を設定します。 
   */
  public final void setBasePrice( Double p_basePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    base_price = p_basePrice;
    base_price_is_set = true;
    base_price_is_modified = true;
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
                if ( name.equals("ask_price_title") ) {
                    return this.ask_price_title;
                }
                else if ( name.equals("ask_price") ) {
                    return this.ask_price;
                }
                else if ( name.equals("ask_price_time") ) {
                    return this.ask_price_time;
                }
                break;
            case 'b':
                if ( name.equals("bid_price_title") ) {
                    return this.bid_price_title;
                }
                else if ( name.equals("bid_price") ) {
                    return this.bid_price;
                }
                else if ( name.equals("bid_price_time") ) {
                    return this.bid_price_time;
                }
                else if ( name.equals("base_price") ) {
                    return this.base_price;
                }
                break;
            case 'c':
                if ( name.equals("contract_month") ) {
                    return this.contract_month;
                }
                else if ( name.equals("current_price") ) {
                    return this.current_price;
                }
                else if ( name.equals("current_price_time") ) {
                    return this.current_price_time;
                }
                else if ( name.equals("current_price_flag") ) {
                    return this.current_price_flag;
                }
                else if ( name.equals("change") ) {
                    return this.change;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("data_type") ) {
                    return this.data_type;
                }
                break;
            case 'h':
                if ( name.equals("high_price") ) {
                    return this.high_price;
                }
                else if ( name.equals("high_price_time") ) {
                    return this.high_price_time;
                }
                break;
            case 'l':
                if ( name.equals("low_price") ) {
                    return this.low_price;
                }
                else if ( name.equals("low_price_time") ) {
                    return this.low_price_time;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                break;
            case 'o':
                if ( name.equals("open_price") ) {
                    return this.open_price;
                }
                else if ( name.equals("open_price_time") ) {
                    return this.open_price_time;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("put_and_call") ) {
                    return this.put_and_call;
                }
                break;
            case 'q':
                if ( name.equals("quote_data_id") ) {
                    return new Long( quote_data_id );
                }
                else if ( name.equals("quote_date") ) {
                    return this.quote_date;
                }
                break;
            case 'r':
                if ( name.equals("real_type") ) {
                    return this.real_type;
                }
                break;
            case 's':
                if ( name.equals("strike_price") ) {
                    return this.strike_price;
                }
                break;
            case 'v':
                if ( name.equals("volume") ) {
                    return this.volume;
                }
                else if ( name.equals("volume_time") ) {
                    return this.volume_time;
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
                if ( name.equals("ask_price_title") ) {
                    if ( value != null )
                      if ( !(value instanceof webbroker3.quoteadaptor.AskPriceTitle) )
                        throw new IllegalArgumentException( "value for 'ask_price_title' must be webbroker3.quoteadaptor.AskPriceTitle: '"+value+"' is not." );
                    this.ask_price_title = (webbroker3.quoteadaptor.AskPriceTitle) value;
                    if (this.ask_price_title_is_set)
                        this.ask_price_title_is_modified = true;
                    this.ask_price_title_is_set = true;
                    return;
                }
                else if ( name.equals("ask_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'ask_price' must be Double: '"+value+"' is not." );
                    this.ask_price = (Double) value;
                    if (this.ask_price_is_set)
                        this.ask_price_is_modified = true;
                    this.ask_price_is_set = true;
                    return;
                }
                else if ( name.equals("ask_price_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ask_price_time' must be String: '"+value+"' is not." );
                    this.ask_price_time = (String) value;
                    if (this.ask_price_time_is_set)
                        this.ask_price_time_is_modified = true;
                    this.ask_price_time_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("bid_price_title") ) {
                    if ( value != null )
                      if ( !(value instanceof webbroker3.quoteadaptor.BidPriceTitle) )
                        throw new IllegalArgumentException( "value for 'bid_price_title' must be webbroker3.quoteadaptor.BidPriceTitle: '"+value+"' is not." );
                    this.bid_price_title = (webbroker3.quoteadaptor.BidPriceTitle) value;
                    if (this.bid_price_title_is_set)
                        this.bid_price_title_is_modified = true;
                    this.bid_price_title_is_set = true;
                    return;
                }
                else if ( name.equals("bid_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'bid_price' must be Double: '"+value+"' is not." );
                    this.bid_price = (Double) value;
                    if (this.bid_price_is_set)
                        this.bid_price_is_modified = true;
                    this.bid_price_is_set = true;
                    return;
                }
                else if ( name.equals("bid_price_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bid_price_time' must be String: '"+value+"' is not." );
                    this.bid_price_time = (String) value;
                    if (this.bid_price_time_is_set)
                        this.bid_price_time_is_modified = true;
                    this.bid_price_time_is_set = true;
                    return;
                }
                else if ( name.equals("base_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'base_price' must be Double: '"+value+"' is not." );
                    this.base_price = (Double) value;
                    if (this.base_price_is_set)
                        this.base_price_is_modified = true;
                    this.base_price_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("contract_month") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'contract_month' must be String: '"+value+"' is not." );
                    this.contract_month = (String) value;
                    if (this.contract_month_is_set)
                        this.contract_month_is_modified = true;
                    this.contract_month_is_set = true;
                    return;
                }
                else if ( name.equals("current_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'current_price' must be Double: '"+value+"' is not." );
                    this.current_price = (Double) value;
                    if (this.current_price_is_set)
                        this.current_price_is_modified = true;
                    this.current_price_is_set = true;
                    return;
                }
                else if ( name.equals("current_price_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'current_price_time' must be String: '"+value+"' is not." );
                    this.current_price_time = (String) value;
                    if (this.current_price_time_is_set)
                        this.current_price_time_is_modified = true;
                    this.current_price_time_is_set = true;
                    return;
                }
                else if ( name.equals("current_price_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof webbroker3.quoteadaptor.CurrentPriceFlag) )
                        throw new IllegalArgumentException( "value for 'current_price_flag' must be webbroker3.quoteadaptor.CurrentPriceFlag: '"+value+"' is not." );
                    this.current_price_flag = (webbroker3.quoteadaptor.CurrentPriceFlag) value;
                    if (this.current_price_flag_is_set)
                        this.current_price_flag_is_modified = true;
                    this.current_price_flag_is_set = true;
                    return;
                }
                else if ( name.equals("change") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'change' must be Double: '"+value+"' is not." );
                    this.change = (Double) value;
                    if (this.change_is_set)
                        this.change_is_modified = true;
                    this.change_is_set = true;
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
            case 'd':
                if ( name.equals("data_type") ) {
                    if ( !(value instanceof webbroker3.quoteadaptor.DataType) )
                        throw new IllegalArgumentException( "value for 'data_type' must be webbroker3.quoteadaptor.DataType: '"+value+"' is not." );
                    this.data_type = (webbroker3.quoteadaptor.DataType) value;
                    if (this.data_type_is_set)
                        this.data_type_is_modified = true;
                    this.data_type_is_set = true;
                    return;
                }
                break;
            case 'h':
                if ( name.equals("high_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'high_price' must be Double: '"+value+"' is not." );
                    this.high_price = (Double) value;
                    if (this.high_price_is_set)
                        this.high_price_is_modified = true;
                    this.high_price_is_set = true;
                    return;
                }
                else if ( name.equals("high_price_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'high_price_time' must be String: '"+value+"' is not." );
                    this.high_price_time = (String) value;
                    if (this.high_price_time_is_set)
                        this.high_price_time_is_modified = true;
                    this.high_price_time_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("low_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'low_price' must be Double: '"+value+"' is not." );
                    this.low_price = (Double) value;
                    if (this.low_price_is_set)
                        this.low_price_is_modified = true;
                    this.low_price_is_set = true;
                    return;
                }
                else if ( name.equals("low_price_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'low_price_time' must be String: '"+value+"' is not." );
                    this.low_price_time = (String) value;
                    if (this.low_price_time_is_set)
                        this.low_price_time_is_modified = true;
                    this.low_price_time_is_set = true;
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
                if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    if (this.market_code_is_set)
                        this.market_code_is_modified = true;
                    this.market_code_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("open_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'open_price' must be Double: '"+value+"' is not." );
                    this.open_price = (Double) value;
                    if (this.open_price_is_set)
                        this.open_price_is_modified = true;
                    this.open_price_is_set = true;
                    return;
                }
                else if ( name.equals("open_price_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'open_price_time' must be String: '"+value+"' is not." );
                    this.open_price_time = (String) value;
                    if (this.open_price_time_is_set)
                        this.open_price_time_is_modified = true;
                    this.open_price_time_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                else if ( name.equals("put_and_call") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'put_and_call' must be String: '"+value+"' is not." );
                    this.put_and_call = (String) value;
                    if (this.put_and_call_is_set)
                        this.put_and_call_is_modified = true;
                    this.put_and_call_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("quote_data_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'quote_data_id' must be Long: '"+value+"' is not." );
                    this.quote_data_id = ((Long) value).longValue();
                    if (this.quote_data_id_is_set)
                        this.quote_data_id_is_modified = true;
                    this.quote_data_id_is_set = true;
                    return;
                }
                else if ( name.equals("quote_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'quote_date' must be String: '"+value+"' is not." );
                    this.quote_date = (String) value;
                    if (this.quote_date_is_set)
                        this.quote_date_is_modified = true;
                    this.quote_date_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("real_type") ) {
                    if ( !(value instanceof webbroker3.quoteadaptor.RealType) )
                        throw new IllegalArgumentException( "value for 'real_type' must be webbroker3.quoteadaptor.RealType: '"+value+"' is not." );
                    this.real_type = (webbroker3.quoteadaptor.RealType) value;
                    if (this.real_type_is_set)
                        this.real_type_is_modified = true;
                    this.real_type_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("strike_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'strike_price' must be Double: '"+value+"' is not." );
                    this.strike_price = (Double) value;
                    if (this.strike_price_is_set)
                        this.strike_price_is_modified = true;
                    this.strike_price_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("volume") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'volume' must be Double: '"+value+"' is not." );
                    this.volume = (Double) value;
                    if (this.volume_is_set)
                        this.volume_is_modified = true;
                    this.volume_is_set = true;
                    return;
                }
                else if ( name.equals("volume_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'volume_time' must be String: '"+value+"' is not." );
                    this.volume_time = (String) value;
                    if (this.volume_time_is_set)
                        this.volume_time_is_modified = true;
                    this.volume_time_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
