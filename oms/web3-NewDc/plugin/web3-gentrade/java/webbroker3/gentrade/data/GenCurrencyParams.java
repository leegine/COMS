head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.40.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	GenCurrencyParams.java;


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
 * gen_currencyテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link GenCurrencyRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link GenCurrencyParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link GenCurrencyParams}が{@@link GenCurrencyRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see GenCurrencyPK 
 * @@see GenCurrencyRow 
 */
public class GenCurrencyParams extends Params implements GenCurrencyRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "gen_currency";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = GenCurrencyRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return GenCurrencyRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>currency_code</em>カラムの値 
   */
  public  String  currency_code;

  /** 
   * <em>currency_name</em>カラムの値 
   */
  public  String  currency_name;

  /** 
   * <em>currency_short_name</em>カラムの値 
   */
  public  String  currency_short_name;

  /** 
   * <em>current_sell_rate</em>カラムの値 
   */
  public  Double  current_sell_rate;

  /** 
   * <em>prev_sell_rate</em>カラムの値 
   */
  public  Double  prev_sell_rate;

  /** 
   * <em>current_buy_rate</em>カラムの値 
   */
  public  Double  current_buy_rate;

  /** 
   * <em>prev_buy_rate</em>カラムの値 
   */
  public  Double  prev_buy_rate;

  /** 
   * <em>current_sell_exec_rate</em>カラムの値 
   */
  public  Double  current_sell_exec_rate;

  /** 
   * <em>prev_sell_exec_rate</em>カラムの値 
   */
  public  Double  prev_sell_exec_rate;

  /** 
   * <em>current_buy_exec_rate</em>カラムの値 
   */
  public  Double  current_buy_exec_rate;

  /** 
   * <em>prev_buy_exec_rate</em>カラムの値 
   */
  public  Double  prev_buy_exec_rate;

  /** 
   * <em>scale</em>カラムの値 
   */
  public  int  scale;

  /** 
   * <em>change_jpy_round_div</em>カラムの値 
   */
  public  String  change_jpy_round_div;

  /** 
   * <em>change_f_ccy_round_div</em>カラムの値 
   */
  public  String  change_f_ccy_round_div;

  /** 
   * <em>rate_last_updater</em>カラムの値 
   */
  public  String  rate_last_updater;

  /** 
   * <em>exec_rate_last_updater</em>カラムの値 
   */
  public  String  exec_rate_last_updater;

  /** 
   * <em>rate_update_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  rate_update_timestamp;

  /** 
   * <em>exec_rate_update_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  exec_rate_update_timestamp;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean currency_code_is_set = false;
  private boolean currency_code_is_modified = false;
  private boolean currency_name_is_set = false;
  private boolean currency_name_is_modified = false;
  private boolean currency_short_name_is_set = false;
  private boolean currency_short_name_is_modified = false;
  private boolean current_sell_rate_is_set = false;
  private boolean current_sell_rate_is_modified = false;
  private boolean prev_sell_rate_is_set = false;
  private boolean prev_sell_rate_is_modified = false;
  private boolean current_buy_rate_is_set = false;
  private boolean current_buy_rate_is_modified = false;
  private boolean prev_buy_rate_is_set = false;
  private boolean prev_buy_rate_is_modified = false;
  private boolean current_sell_exec_rate_is_set = false;
  private boolean current_sell_exec_rate_is_modified = false;
  private boolean prev_sell_exec_rate_is_set = false;
  private boolean prev_sell_exec_rate_is_modified = false;
  private boolean current_buy_exec_rate_is_set = false;
  private boolean current_buy_exec_rate_is_modified = false;
  private boolean prev_buy_exec_rate_is_set = false;
  private boolean prev_buy_exec_rate_is_modified = false;
  private boolean scale_is_set = false;
  private boolean scale_is_modified = false;
  private boolean change_jpy_round_div_is_set = false;
  private boolean change_jpy_round_div_is_modified = false;
  private boolean change_f_ccy_round_div_is_set = false;
  private boolean change_f_ccy_round_div_is_modified = false;
  private boolean rate_last_updater_is_set = false;
  private boolean rate_last_updater_is_modified = false;
  private boolean exec_rate_last_updater_is_set = false;
  private boolean exec_rate_last_updater_is_modified = false;
  private boolean rate_update_timestamp_is_set = false;
  private boolean rate_update_timestamp_is_modified = false;
  private boolean exec_rate_update_timestamp_is_set = false;
  private boolean exec_rate_update_timestamp_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "currency_code=" + currency_code
      + "," + "currency_name=" +currency_name
      + "," + "currency_short_name=" +currency_short_name
      + "," + "current_sell_rate=" +current_sell_rate
      + "," + "prev_sell_rate=" +prev_sell_rate
      + "," + "current_buy_rate=" +current_buy_rate
      + "," + "prev_buy_rate=" +prev_buy_rate
      + "," + "current_sell_exec_rate=" +current_sell_exec_rate
      + "," + "prev_sell_exec_rate=" +prev_sell_exec_rate
      + "," + "current_buy_exec_rate=" +current_buy_exec_rate
      + "," + "prev_buy_exec_rate=" +prev_buy_exec_rate
      + "," + "scale=" +scale
      + "," + "change_jpy_round_div=" +change_jpy_round_div
      + "," + "change_f_ccy_round_div=" +change_f_ccy_round_div
      + "," + "rate_last_updater=" +rate_last_updater
      + "," + "exec_rate_last_updater=" +exec_rate_last_updater
      + "," + "rate_update_timestamp=" +rate_update_timestamp
      + "," + "exec_rate_update_timestamp=" +exec_rate_update_timestamp
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のGenCurrencyParamsオブジェクトを作成します。 
   */
  public GenCurrencyParams() {
    institution_code_is_modified = true;
    currency_code_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のGenCurrencyRowオブジェクトの値を利用してGenCurrencyParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するGenCurrencyRowオブジェクト 
   */
  public GenCurrencyParams( GenCurrencyRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    currency_code = p_row.getCurrencyCode();
    currency_code_is_set = p_row.getCurrencyCodeIsSet();
    currency_code_is_modified = p_row.getCurrencyCodeIsModified();
    currency_name = p_row.getCurrencyName();
    currency_name_is_set = p_row.getCurrencyNameIsSet();
    currency_name_is_modified = p_row.getCurrencyNameIsModified();
    currency_short_name = p_row.getCurrencyShortName();
    currency_short_name_is_set = p_row.getCurrencyShortNameIsSet();
    currency_short_name_is_modified = p_row.getCurrencyShortNameIsModified();
    if ( !p_row.getCurrentSellRateIsNull() )
      current_sell_rate = new Double( p_row.getCurrentSellRate() );
    current_sell_rate_is_set = p_row.getCurrentSellRateIsSet();
    current_sell_rate_is_modified = p_row.getCurrentSellRateIsModified();
    if ( !p_row.getPrevSellRateIsNull() )
      prev_sell_rate = new Double( p_row.getPrevSellRate() );
    prev_sell_rate_is_set = p_row.getPrevSellRateIsSet();
    prev_sell_rate_is_modified = p_row.getPrevSellRateIsModified();
    if ( !p_row.getCurrentBuyRateIsNull() )
      current_buy_rate = new Double( p_row.getCurrentBuyRate() );
    current_buy_rate_is_set = p_row.getCurrentBuyRateIsSet();
    current_buy_rate_is_modified = p_row.getCurrentBuyRateIsModified();
    if ( !p_row.getPrevBuyRateIsNull() )
      prev_buy_rate = new Double( p_row.getPrevBuyRate() );
    prev_buy_rate_is_set = p_row.getPrevBuyRateIsSet();
    prev_buy_rate_is_modified = p_row.getPrevBuyRateIsModified();
    if ( !p_row.getCurrentSellExecRateIsNull() )
      current_sell_exec_rate = new Double( p_row.getCurrentSellExecRate() );
    current_sell_exec_rate_is_set = p_row.getCurrentSellExecRateIsSet();
    current_sell_exec_rate_is_modified = p_row.getCurrentSellExecRateIsModified();
    if ( !p_row.getPrevSellExecRateIsNull() )
      prev_sell_exec_rate = new Double( p_row.getPrevSellExecRate() );
    prev_sell_exec_rate_is_set = p_row.getPrevSellExecRateIsSet();
    prev_sell_exec_rate_is_modified = p_row.getPrevSellExecRateIsModified();
    if ( !p_row.getCurrentBuyExecRateIsNull() )
      current_buy_exec_rate = new Double( p_row.getCurrentBuyExecRate() );
    current_buy_exec_rate_is_set = p_row.getCurrentBuyExecRateIsSet();
    current_buy_exec_rate_is_modified = p_row.getCurrentBuyExecRateIsModified();
    if ( !p_row.getPrevBuyExecRateIsNull() )
      prev_buy_exec_rate = new Double( p_row.getPrevBuyExecRate() );
    prev_buy_exec_rate_is_set = p_row.getPrevBuyExecRateIsSet();
    prev_buy_exec_rate_is_modified = p_row.getPrevBuyExecRateIsModified();
    scale = p_row.getScale();
    scale_is_set = p_row.getScaleIsSet();
    scale_is_modified = p_row.getScaleIsModified();
    change_jpy_round_div = p_row.getChangeJpyRoundDiv();
    change_jpy_round_div_is_set = p_row.getChangeJpyRoundDivIsSet();
    change_jpy_round_div_is_modified = p_row.getChangeJpyRoundDivIsModified();
    change_f_ccy_round_div = p_row.getChangeFCcyRoundDiv();
    change_f_ccy_round_div_is_set = p_row.getChangeFCcyRoundDivIsSet();
    change_f_ccy_round_div_is_modified = p_row.getChangeFCcyRoundDivIsModified();
    rate_last_updater = p_row.getRateLastUpdater();
    rate_last_updater_is_set = p_row.getRateLastUpdaterIsSet();
    rate_last_updater_is_modified = p_row.getRateLastUpdaterIsModified();
    exec_rate_last_updater = p_row.getExecRateLastUpdater();
    exec_rate_last_updater_is_set = p_row.getExecRateLastUpdaterIsSet();
    exec_rate_last_updater_is_modified = p_row.getExecRateLastUpdaterIsModified();
    rate_update_timestamp = p_row.getRateUpdateTimestamp();
    rate_update_timestamp_is_set = p_row.getRateUpdateTimestampIsSet();
    rate_update_timestamp_is_modified = p_row.getRateUpdateTimestampIsModified();
    exec_rate_update_timestamp = p_row.getExecRateUpdateTimestamp();
    exec_rate_update_timestamp_is_set = p_row.getExecRateUpdateTimestampIsSet();
    exec_rate_update_timestamp_is_modified = p_row.getExecRateUpdateTimestampIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
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
      currency_name_is_set = true;
      currency_name_is_modified = true;
      currency_short_name_is_set = true;
      currency_short_name_is_modified = true;
      current_sell_rate_is_set = true;
      current_sell_rate_is_modified = true;
      prev_sell_rate_is_set = true;
      prev_sell_rate_is_modified = true;
      current_buy_rate_is_set = true;
      current_buy_rate_is_modified = true;
      prev_buy_rate_is_set = true;
      prev_buy_rate_is_modified = true;
      current_sell_exec_rate_is_set = true;
      current_sell_exec_rate_is_modified = true;
      prev_sell_exec_rate_is_set = true;
      prev_sell_exec_rate_is_modified = true;
      current_buy_exec_rate_is_set = true;
      current_buy_exec_rate_is_modified = true;
      prev_buy_exec_rate_is_set = true;
      prev_buy_exec_rate_is_modified = true;
      scale_is_set = true;
      scale_is_modified = true;
      change_jpy_round_div_is_set = true;
      change_jpy_round_div_is_modified = true;
      change_f_ccy_round_div_is_set = true;
      change_f_ccy_round_div_is_modified = true;
      rate_last_updater_is_set = true;
      rate_last_updater_is_modified = true;
      exec_rate_last_updater_is_set = true;
      exec_rate_last_updater_is_modified = true;
      rate_update_timestamp_is_set = true;
      rate_update_timestamp_is_modified = true;
      exec_rate_update_timestamp_is_set = true;
      exec_rate_update_timestamp_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
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
    if ( !( other instanceof GenCurrencyRow ) )
       return false;
    return fieldsEqual( (GenCurrencyRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のGenCurrencyRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( GenCurrencyRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( currency_code == null ) {
      if ( row.getCurrencyCode() != null )
        return false;
    } else if ( !currency_code.equals( row.getCurrencyCode() ) ) {
        return false;
    }
    if ( currency_name == null ) {
      if ( row.getCurrencyName() != null )
        return false;
    } else if ( !currency_name.equals( row.getCurrencyName() ) ) {
        return false;
    }
    if ( currency_short_name == null ) {
      if ( row.getCurrencyShortName() != null )
        return false;
    } else if ( !currency_short_name.equals( row.getCurrencyShortName() ) ) {
        return false;
    }
    if ( current_sell_rate == null ) {
      if ( !row.getCurrentSellRateIsNull() )
        return false;
    } else if ( row.getCurrentSellRateIsNull() || ( current_sell_rate.doubleValue() != row.getCurrentSellRate() ) ) {
        return false;
    }
    if ( prev_sell_rate == null ) {
      if ( !row.getPrevSellRateIsNull() )
        return false;
    } else if ( row.getPrevSellRateIsNull() || ( prev_sell_rate.doubleValue() != row.getPrevSellRate() ) ) {
        return false;
    }
    if ( current_buy_rate == null ) {
      if ( !row.getCurrentBuyRateIsNull() )
        return false;
    } else if ( row.getCurrentBuyRateIsNull() || ( current_buy_rate.doubleValue() != row.getCurrentBuyRate() ) ) {
        return false;
    }
    if ( prev_buy_rate == null ) {
      if ( !row.getPrevBuyRateIsNull() )
        return false;
    } else if ( row.getPrevBuyRateIsNull() || ( prev_buy_rate.doubleValue() != row.getPrevBuyRate() ) ) {
        return false;
    }
    if ( current_sell_exec_rate == null ) {
      if ( !row.getCurrentSellExecRateIsNull() )
        return false;
    } else if ( row.getCurrentSellExecRateIsNull() || ( current_sell_exec_rate.doubleValue() != row.getCurrentSellExecRate() ) ) {
        return false;
    }
    if ( prev_sell_exec_rate == null ) {
      if ( !row.getPrevSellExecRateIsNull() )
        return false;
    } else if ( row.getPrevSellExecRateIsNull() || ( prev_sell_exec_rate.doubleValue() != row.getPrevSellExecRate() ) ) {
        return false;
    }
    if ( current_buy_exec_rate == null ) {
      if ( !row.getCurrentBuyExecRateIsNull() )
        return false;
    } else if ( row.getCurrentBuyExecRateIsNull() || ( current_buy_exec_rate.doubleValue() != row.getCurrentBuyExecRate() ) ) {
        return false;
    }
    if ( prev_buy_exec_rate == null ) {
      if ( !row.getPrevBuyExecRateIsNull() )
        return false;
    } else if ( row.getPrevBuyExecRateIsNull() || ( prev_buy_exec_rate.doubleValue() != row.getPrevBuyExecRate() ) ) {
        return false;
    }
    if ( scale != row.getScale() )
      return false;
    if ( change_jpy_round_div == null ) {
      if ( row.getChangeJpyRoundDiv() != null )
        return false;
    } else if ( !change_jpy_round_div.equals( row.getChangeJpyRoundDiv() ) ) {
        return false;
    }
    if ( change_f_ccy_round_div == null ) {
      if ( row.getChangeFCcyRoundDiv() != null )
        return false;
    } else if ( !change_f_ccy_round_div.equals( row.getChangeFCcyRoundDiv() ) ) {
        return false;
    }
    if ( rate_last_updater == null ) {
      if ( row.getRateLastUpdater() != null )
        return false;
    } else if ( !rate_last_updater.equals( row.getRateLastUpdater() ) ) {
        return false;
    }
    if ( exec_rate_last_updater == null ) {
      if ( row.getExecRateLastUpdater() != null )
        return false;
    } else if ( !exec_rate_last_updater.equals( row.getExecRateLastUpdater() ) ) {
        return false;
    }
    if ( rate_update_timestamp == null ) {
      if ( row.getRateUpdateTimestamp() != null )
        return false;
    } else if ( !rate_update_timestamp.equals( row.getRateUpdateTimestamp() ) ) {
        return false;
    }
    if ( exec_rate_update_timestamp == null ) {
      if ( row.getExecRateUpdateTimestamp() != null )
        return false;
    } else if ( !exec_rate_update_timestamp.equals( row.getExecRateUpdateTimestamp() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
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
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (currency_code!=null? currency_code.hashCode(): 0) 
        + (currency_name!=null? currency_name.hashCode(): 0) 
        + (currency_short_name!=null? currency_short_name.hashCode(): 0) 
        + (current_sell_rate!=null? current_sell_rate.hashCode(): 0) 
        + (prev_sell_rate!=null? prev_sell_rate.hashCode(): 0) 
        + (current_buy_rate!=null? current_buy_rate.hashCode(): 0) 
        + (prev_buy_rate!=null? prev_buy_rate.hashCode(): 0) 
        + (current_sell_exec_rate!=null? current_sell_exec_rate.hashCode(): 0) 
        + (prev_sell_exec_rate!=null? prev_sell_exec_rate.hashCode(): 0) 
        + (current_buy_exec_rate!=null? current_buy_exec_rate.hashCode(): 0) 
        + (prev_buy_exec_rate!=null? prev_buy_exec_rate.hashCode(): 0) 
        + ((int) scale)
        + (change_jpy_round_div!=null? change_jpy_round_div.hashCode(): 0) 
        + (change_f_ccy_round_div!=null? change_f_ccy_round_div.hashCode(): 0) 
        + (rate_last_updater!=null? rate_last_updater.hashCode(): 0) 
        + (exec_rate_last_updater!=null? exec_rate_last_updater.hashCode(): 0) 
        + (rate_update_timestamp!=null? rate_update_timestamp.hashCode(): 0) 
        + (exec_rate_update_timestamp!=null? exec_rate_update_timestamp.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !scale_is_set )
			throw new IllegalArgumentException("non-nullable field 'scale' must be set before inserting.");
		if ( !change_jpy_round_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'change_jpy_round_div' must be set before inserting.");
		if ( !change_f_ccy_round_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'change_f_ccy_round_div' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("currency_code",currency_code);
		if ( currency_name != null )
			map.put("currency_name",currency_name);
		if ( currency_short_name != null )
			map.put("currency_short_name",currency_short_name);
		if ( current_sell_rate != null )
			map.put("current_sell_rate",current_sell_rate);
		if ( prev_sell_rate != null )
			map.put("prev_sell_rate",prev_sell_rate);
		if ( current_buy_rate != null )
			map.put("current_buy_rate",current_buy_rate);
		if ( prev_buy_rate != null )
			map.put("prev_buy_rate",prev_buy_rate);
		if ( current_sell_exec_rate != null )
			map.put("current_sell_exec_rate",current_sell_exec_rate);
		if ( prev_sell_exec_rate != null )
			map.put("prev_sell_exec_rate",prev_sell_exec_rate);
		if ( current_buy_exec_rate != null )
			map.put("current_buy_exec_rate",current_buy_exec_rate);
		if ( prev_buy_exec_rate != null )
			map.put("prev_buy_exec_rate",prev_buy_exec_rate);
		map.put("scale",new Integer(scale));
		map.put("change_jpy_round_div",change_jpy_round_div);
		map.put("change_f_ccy_round_div",change_f_ccy_round_div);
		if ( rate_last_updater != null )
			map.put("rate_last_updater",rate_last_updater);
		if ( exec_rate_last_updater != null )
			map.put("exec_rate_last_updater",exec_rate_last_updater);
		if ( rate_update_timestamp_is_set )
			map.put("rate_update_timestamp",rate_update_timestamp);
		if ( exec_rate_update_timestamp_is_set )
			map.put("exec_rate_update_timestamp",exec_rate_update_timestamp);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
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
		if ( currency_name_is_modified )
			map.put("currency_name",currency_name);
		if ( currency_short_name_is_modified )
			map.put("currency_short_name",currency_short_name);
		if ( current_sell_rate_is_modified )
			map.put("current_sell_rate",current_sell_rate);
		if ( prev_sell_rate_is_modified )
			map.put("prev_sell_rate",prev_sell_rate);
		if ( current_buy_rate_is_modified )
			map.put("current_buy_rate",current_buy_rate);
		if ( prev_buy_rate_is_modified )
			map.put("prev_buy_rate",prev_buy_rate);
		if ( current_sell_exec_rate_is_modified )
			map.put("current_sell_exec_rate",current_sell_exec_rate);
		if ( prev_sell_exec_rate_is_modified )
			map.put("prev_sell_exec_rate",prev_sell_exec_rate);
		if ( current_buy_exec_rate_is_modified )
			map.put("current_buy_exec_rate",current_buy_exec_rate);
		if ( prev_buy_exec_rate_is_modified )
			map.put("prev_buy_exec_rate",prev_buy_exec_rate);
		if ( scale_is_modified )
			map.put("scale",new Integer(scale));
		if ( change_jpy_round_div_is_modified )
			map.put("change_jpy_round_div",change_jpy_round_div);
		if ( change_f_ccy_round_div_is_modified )
			map.put("change_f_ccy_round_div",change_f_ccy_round_div);
		if ( rate_last_updater_is_modified )
			map.put("rate_last_updater",rate_last_updater);
		if ( exec_rate_last_updater_is_modified )
			map.put("exec_rate_last_updater",exec_rate_last_updater);
		if ( rate_update_timestamp_is_modified )
			map.put("rate_update_timestamp",rate_update_timestamp);
		if ( exec_rate_update_timestamp_is_modified )
			map.put("exec_rate_update_timestamp",exec_rate_update_timestamp);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("currency_name",currency_name);
			map.put("currency_short_name",currency_short_name);
			map.put("current_sell_rate",current_sell_rate);
			map.put("prev_sell_rate",prev_sell_rate);
			map.put("current_buy_rate",current_buy_rate);
			map.put("prev_buy_rate",prev_buy_rate);
			map.put("current_sell_exec_rate",current_sell_exec_rate);
			map.put("prev_sell_exec_rate",prev_sell_exec_rate);
			map.put("current_buy_exec_rate",current_buy_exec_rate);
			map.put("prev_buy_exec_rate",prev_buy_exec_rate);
			if ( scale_is_set )
				map.put("scale",new Integer(scale));
			if ( change_jpy_round_div_is_set )
				map.put("change_jpy_round_div",change_jpy_round_div);
			if ( change_f_ccy_round_div_is_set )
				map.put("change_f_ccy_round_div",change_f_ccy_round_div);
			map.put("rate_last_updater",rate_last_updater);
			map.put("exec_rate_last_updater",exec_rate_last_updater);
			if ( rate_update_timestamp_is_set )
				map.put("rate_update_timestamp",rate_update_timestamp);
			if ( exec_rate_update_timestamp_is_set )
				map.put("exec_rate_update_timestamp",exec_rate_update_timestamp);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
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
   * <em>currency_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCurrencyCode()
  {
    return currency_code;
  }


  /** 
   * <em>currency_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyCodeIsSet() {
    return currency_code_is_set;
  }


  /** 
   * <em>currency_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyCodeIsModified() {
    return currency_code_is_modified;
  }


  /** 
   * <em>currency_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCurrencyName()
  {
    return currency_name;
  }


  /** 
   * <em>currency_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyNameIsSet() {
    return currency_name_is_set;
  }


  /** 
   * <em>currency_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyNameIsModified() {
    return currency_name_is_modified;
  }


  /** 
   * <em>currency_short_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCurrencyShortName()
  {
    return currency_short_name;
  }


  /** 
   * <em>currency_short_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyShortNameIsSet() {
    return currency_short_name_is_set;
  }


  /** 
   * <em>currency_short_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrencyShortNameIsModified() {
    return currency_short_name_is_modified;
  }


  /** 
   * <em>current_sell_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCurrentSellRate()
  {
    return ( current_sell_rate==null? ((double)0): current_sell_rate.doubleValue() );
  }


  /** 
   * <em>current_sell_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCurrentSellRateIsNull()
  {
    return current_sell_rate == null;
  }


  /** 
   * <em>current_sell_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentSellRateIsSet() {
    return current_sell_rate_is_set;
  }


  /** 
   * <em>current_sell_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentSellRateIsModified() {
    return current_sell_rate_is_modified;
  }


  /** 
   * <em>prev_sell_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPrevSellRate()
  {
    return ( prev_sell_rate==null? ((double)0): prev_sell_rate.doubleValue() );
  }


  /** 
   * <em>prev_sell_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPrevSellRateIsNull()
  {
    return prev_sell_rate == null;
  }


  /** 
   * <em>prev_sell_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrevSellRateIsSet() {
    return prev_sell_rate_is_set;
  }


  /** 
   * <em>prev_sell_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrevSellRateIsModified() {
    return prev_sell_rate_is_modified;
  }


  /** 
   * <em>current_buy_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCurrentBuyRate()
  {
    return ( current_buy_rate==null? ((double)0): current_buy_rate.doubleValue() );
  }


  /** 
   * <em>current_buy_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCurrentBuyRateIsNull()
  {
    return current_buy_rate == null;
  }


  /** 
   * <em>current_buy_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentBuyRateIsSet() {
    return current_buy_rate_is_set;
  }


  /** 
   * <em>current_buy_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentBuyRateIsModified() {
    return current_buy_rate_is_modified;
  }


  /** 
   * <em>prev_buy_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPrevBuyRate()
  {
    return ( prev_buy_rate==null? ((double)0): prev_buy_rate.doubleValue() );
  }


  /** 
   * <em>prev_buy_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPrevBuyRateIsNull()
  {
    return prev_buy_rate == null;
  }


  /** 
   * <em>prev_buy_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrevBuyRateIsSet() {
    return prev_buy_rate_is_set;
  }


  /** 
   * <em>prev_buy_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrevBuyRateIsModified() {
    return prev_buy_rate_is_modified;
  }


  /** 
   * <em>current_sell_exec_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCurrentSellExecRate()
  {
    return ( current_sell_exec_rate==null? ((double)0): current_sell_exec_rate.doubleValue() );
  }


  /** 
   * <em>current_sell_exec_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCurrentSellExecRateIsNull()
  {
    return current_sell_exec_rate == null;
  }


  /** 
   * <em>current_sell_exec_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentSellExecRateIsSet() {
    return current_sell_exec_rate_is_set;
  }


  /** 
   * <em>current_sell_exec_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentSellExecRateIsModified() {
    return current_sell_exec_rate_is_modified;
  }


  /** 
   * <em>prev_sell_exec_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPrevSellExecRate()
  {
    return ( prev_sell_exec_rate==null? ((double)0): prev_sell_exec_rate.doubleValue() );
  }


  /** 
   * <em>prev_sell_exec_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPrevSellExecRateIsNull()
  {
    return prev_sell_exec_rate == null;
  }


  /** 
   * <em>prev_sell_exec_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrevSellExecRateIsSet() {
    return prev_sell_exec_rate_is_set;
  }


  /** 
   * <em>prev_sell_exec_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrevSellExecRateIsModified() {
    return prev_sell_exec_rate_is_modified;
  }


  /** 
   * <em>current_buy_exec_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCurrentBuyExecRate()
  {
    return ( current_buy_exec_rate==null? ((double)0): current_buy_exec_rate.doubleValue() );
  }


  /** 
   * <em>current_buy_exec_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCurrentBuyExecRateIsNull()
  {
    return current_buy_exec_rate == null;
  }


  /** 
   * <em>current_buy_exec_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentBuyExecRateIsSet() {
    return current_buy_exec_rate_is_set;
  }


  /** 
   * <em>current_buy_exec_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCurrentBuyExecRateIsModified() {
    return current_buy_exec_rate_is_modified;
  }


  /** 
   * <em>prev_buy_exec_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPrevBuyExecRate()
  {
    return ( prev_buy_exec_rate==null? ((double)0): prev_buy_exec_rate.doubleValue() );
  }


  /** 
   * <em>prev_buy_exec_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPrevBuyExecRateIsNull()
  {
    return prev_buy_exec_rate == null;
  }


  /** 
   * <em>prev_buy_exec_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrevBuyExecRateIsSet() {
    return prev_buy_exec_rate_is_set;
  }


  /** 
   * <em>prev_buy_exec_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPrevBuyExecRateIsModified() {
    return prev_buy_exec_rate_is_modified;
  }


  /** 
   * <em>scale</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getScale()
  {
    return scale;
  }


  /** 
   * <em>scale</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getScaleIsSet() {
    return scale_is_set;
  }


  /** 
   * <em>scale</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getScaleIsModified() {
    return scale_is_modified;
  }


  /** 
   * <em>change_jpy_round_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getChangeJpyRoundDiv()
  {
    return change_jpy_round_div;
  }


  /** 
   * <em>change_jpy_round_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeJpyRoundDivIsSet() {
    return change_jpy_round_div_is_set;
  }


  /** 
   * <em>change_jpy_round_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeJpyRoundDivIsModified() {
    return change_jpy_round_div_is_modified;
  }


  /** 
   * <em>change_f_ccy_round_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getChangeFCcyRoundDiv()
  {
    return change_f_ccy_round_div;
  }


  /** 
   * <em>change_f_ccy_round_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeFCcyRoundDivIsSet() {
    return change_f_ccy_round_div_is_set;
  }


  /** 
   * <em>change_f_ccy_round_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getChangeFCcyRoundDivIsModified() {
    return change_f_ccy_round_div_is_modified;
  }


  /** 
   * <em>rate_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRateLastUpdater()
  {
    return rate_last_updater;
  }


  /** 
   * <em>rate_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRateLastUpdaterIsSet() {
    return rate_last_updater_is_set;
  }


  /** 
   * <em>rate_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRateLastUpdaterIsModified() {
    return rate_last_updater_is_modified;
  }


  /** 
   * <em>exec_rate_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getExecRateLastUpdater()
  {
    return exec_rate_last_updater;
  }


  /** 
   * <em>exec_rate_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecRateLastUpdaterIsSet() {
    return exec_rate_last_updater_is_set;
  }


  /** 
   * <em>exec_rate_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecRateLastUpdaterIsModified() {
    return exec_rate_last_updater_is_modified;
  }


  /** 
   * <em>rate_update_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getRateUpdateTimestamp()
  {
    return rate_update_timestamp;
  }


  /** 
   * <em>rate_update_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRateUpdateTimestampIsSet() {
    return rate_update_timestamp_is_set;
  }


  /** 
   * <em>rate_update_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRateUpdateTimestampIsModified() {
    return rate_update_timestamp_is_modified;
  }


  /** 
   * <em>exec_rate_update_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getExecRateUpdateTimestamp()
  {
    return exec_rate_update_timestamp;
  }


  /** 
   * <em>exec_rate_update_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecRateUpdateTimestampIsSet() {
    return exec_rate_update_timestamp_is_set;
  }


  /** 
   * <em>exec_rate_update_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getExecRateUpdateTimestampIsModified() {
    return exec_rate_update_timestamp_is_modified;
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
    return new GenCurrencyPK(institution_code, currency_code);
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
   * <em>currency_code</em>カラムの値を設定します。 
   *
   * @@param p_currencyCode <em>currency_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setCurrencyCode( String p_currencyCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    currency_code = p_currencyCode;
    currency_code_is_set = true;
    currency_code_is_modified = true;
  }


  /** 
   * <em>currency_name</em>カラムの値を設定します。 
   *
   * @@param p_currencyName <em>currency_name</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setCurrencyName( String p_currencyName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    currency_name = p_currencyName;
    currency_name_is_set = true;
    currency_name_is_modified = true;
  }


  /** 
   * <em>currency_short_name</em>カラムの値を設定します。 
   *
   * @@param p_currencyShortName <em>currency_short_name</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setCurrencyShortName( String p_currencyShortName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    currency_short_name = p_currencyShortName;
    currency_short_name_is_set = true;
    currency_short_name_is_modified = true;
  }


  /** 
   * <em>current_sell_rate</em>カラムの値を設定します。 
   *
   * @@param p_currentSellRate <em>current_sell_rate</em>カラムの値をあらわす7桁以下のdouble値で、その精度は4桁まで
   */
  public final void setCurrentSellRate( double p_currentSellRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    current_sell_rate = new Double(p_currentSellRate);
    current_sell_rate_is_set = true;
    current_sell_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>current_sell_rate</em>カラムに値を設定します。 
   */
  public final void setCurrentSellRate( Double p_currentSellRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    current_sell_rate = p_currentSellRate;
    current_sell_rate_is_set = true;
    current_sell_rate_is_modified = true;
  }


  /** 
   * <em>prev_sell_rate</em>カラムの値を設定します。 
   *
   * @@param p_prevSellRate <em>prev_sell_rate</em>カラムの値をあらわす7桁以下のdouble値で、その精度は4桁まで
   */
  public final void setPrevSellRate( double p_prevSellRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    prev_sell_rate = new Double(p_prevSellRate);
    prev_sell_rate_is_set = true;
    prev_sell_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>prev_sell_rate</em>カラムに値を設定します。 
   */
  public final void setPrevSellRate( Double p_prevSellRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    prev_sell_rate = p_prevSellRate;
    prev_sell_rate_is_set = true;
    prev_sell_rate_is_modified = true;
  }


  /** 
   * <em>current_buy_rate</em>カラムの値を設定します。 
   *
   * @@param p_currentBuyRate <em>current_buy_rate</em>カラムの値をあらわす7桁以下のdouble値で、その精度は4桁まで
   */
  public final void setCurrentBuyRate( double p_currentBuyRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    current_buy_rate = new Double(p_currentBuyRate);
    current_buy_rate_is_set = true;
    current_buy_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>current_buy_rate</em>カラムに値を設定します。 
   */
  public final void setCurrentBuyRate( Double p_currentBuyRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    current_buy_rate = p_currentBuyRate;
    current_buy_rate_is_set = true;
    current_buy_rate_is_modified = true;
  }


  /** 
   * <em>prev_buy_rate</em>カラムの値を設定します。 
   *
   * @@param p_prevBuyRate <em>prev_buy_rate</em>カラムの値をあらわす7桁以下のdouble値で、その精度は4桁まで
   */
  public final void setPrevBuyRate( double p_prevBuyRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    prev_buy_rate = new Double(p_prevBuyRate);
    prev_buy_rate_is_set = true;
    prev_buy_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>prev_buy_rate</em>カラムに値を設定します。 
   */
  public final void setPrevBuyRate( Double p_prevBuyRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    prev_buy_rate = p_prevBuyRate;
    prev_buy_rate_is_set = true;
    prev_buy_rate_is_modified = true;
  }


  /** 
   * <em>current_sell_exec_rate</em>カラムの値を設定します。 
   *
   * @@param p_currentSellExecRate <em>current_sell_exec_rate</em>カラムの値をあらわす7桁以下のdouble値で、その精度は4桁まで
   */
  public final void setCurrentSellExecRate( double p_currentSellExecRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    current_sell_exec_rate = new Double(p_currentSellExecRate);
    current_sell_exec_rate_is_set = true;
    current_sell_exec_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>current_sell_exec_rate</em>カラムに値を設定します。 
   */
  public final void setCurrentSellExecRate( Double p_currentSellExecRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    current_sell_exec_rate = p_currentSellExecRate;
    current_sell_exec_rate_is_set = true;
    current_sell_exec_rate_is_modified = true;
  }


  /** 
   * <em>prev_sell_exec_rate</em>カラムの値を設定します。 
   *
   * @@param p_prevSellExecRate <em>prev_sell_exec_rate</em>カラムの値をあらわす7桁以下のdouble値で、その精度は4桁まで
   */
  public final void setPrevSellExecRate( double p_prevSellExecRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    prev_sell_exec_rate = new Double(p_prevSellExecRate);
    prev_sell_exec_rate_is_set = true;
    prev_sell_exec_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>prev_sell_exec_rate</em>カラムに値を設定します。 
   */
  public final void setPrevSellExecRate( Double p_prevSellExecRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    prev_sell_exec_rate = p_prevSellExecRate;
    prev_sell_exec_rate_is_set = true;
    prev_sell_exec_rate_is_modified = true;
  }


  /** 
   * <em>current_buy_exec_rate</em>カラムの値を設定します。 
   *
   * @@param p_currentBuyExecRate <em>current_buy_exec_rate</em>カラムの値をあらわす7桁以下のdouble値で、その精度は4桁まで
   */
  public final void setCurrentBuyExecRate( double p_currentBuyExecRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    current_buy_exec_rate = new Double(p_currentBuyExecRate);
    current_buy_exec_rate_is_set = true;
    current_buy_exec_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>current_buy_exec_rate</em>カラムに値を設定します。 
   */
  public final void setCurrentBuyExecRate( Double p_currentBuyExecRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    current_buy_exec_rate = p_currentBuyExecRate;
    current_buy_exec_rate_is_set = true;
    current_buy_exec_rate_is_modified = true;
  }


  /** 
   * <em>prev_buy_exec_rate</em>カラムの値を設定します。 
   *
   * @@param p_prevBuyExecRate <em>prev_buy_exec_rate</em>カラムの値をあらわす7桁以下のdouble値で、その精度は4桁まで
   */
  public final void setPrevBuyExecRate( double p_prevBuyExecRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    prev_buy_exec_rate = new Double(p_prevBuyExecRate);
    prev_buy_exec_rate_is_set = true;
    prev_buy_exec_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>prev_buy_exec_rate</em>カラムに値を設定します。 
   */
  public final void setPrevBuyExecRate( Double p_prevBuyExecRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    prev_buy_exec_rate = p_prevBuyExecRate;
    prev_buy_exec_rate_is_set = true;
    prev_buy_exec_rate_is_modified = true;
  }


  /** 
   * <em>scale</em>カラムの値を設定します。 
   *
   * @@param p_scale <em>scale</em>カラムの値をあらわす6桁以下のint値 
   */
  public final void setScale( int p_scale )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    scale = p_scale;
    scale_is_set = true;
    scale_is_modified = true;
  }


  /** 
   * <em>change_jpy_round_div</em>カラムの値を設定します。 
   *
   * @@param p_changeJpyRoundDiv <em>change_jpy_round_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setChangeJpyRoundDiv( String p_changeJpyRoundDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change_jpy_round_div = p_changeJpyRoundDiv;
    change_jpy_round_div_is_set = true;
    change_jpy_round_div_is_modified = true;
  }


  /** 
   * <em>change_f_ccy_round_div</em>カラムの値を設定します。 
   *
   * @@param p_changeFCcyRoundDiv <em>change_f_ccy_round_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setChangeFCcyRoundDiv( String p_changeFCcyRoundDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change_f_ccy_round_div = p_changeFCcyRoundDiv;
    change_f_ccy_round_div_is_set = true;
    change_f_ccy_round_div_is_modified = true;
  }


  /** 
   * <em>rate_last_updater</em>カラムの値を設定します。 
   *
   * @@param p_rateLastUpdater <em>rate_last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setRateLastUpdater( String p_rateLastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rate_last_updater = p_rateLastUpdater;
    rate_last_updater_is_set = true;
    rate_last_updater_is_modified = true;
  }


  /** 
   * <em>exec_rate_last_updater</em>カラムの値を設定します。 
   *
   * @@param p_execRateLastUpdater <em>exec_rate_last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setExecRateLastUpdater( String p_execRateLastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_rate_last_updater = p_execRateLastUpdater;
    exec_rate_last_updater_is_set = true;
    exec_rate_last_updater_is_modified = true;
  }


  /** 
   * <em>rate_update_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_rateUpdateTimestamp <em>rate_update_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setRateUpdateTimestamp( java.sql.Timestamp p_rateUpdateTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rate_update_timestamp = p_rateUpdateTimestamp;
    rate_update_timestamp_is_set = true;
    rate_update_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>rate_update_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setRateUpdateTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    rate_update_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    rate_update_timestamp_is_set = true;
    rate_update_timestamp_is_modified = true;
  }


  /** 
   * <em>exec_rate_update_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_execRateUpdateTimestamp <em>exec_rate_update_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setExecRateUpdateTimestamp( java.sql.Timestamp p_execRateUpdateTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    exec_rate_update_timestamp = p_execRateUpdateTimestamp;
    exec_rate_update_timestamp_is_set = true;
    exec_rate_update_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>exec_rate_update_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setExecRateUpdateTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    exec_rate_update_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    exec_rate_update_timestamp_is_set = true;
    exec_rate_update_timestamp_is_modified = true;
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
                if ( name.equals("currency_code") ) {
                    return this.currency_code;
                }
                else if ( name.equals("currency_name") ) {
                    return this.currency_name;
                }
                else if ( name.equals("currency_short_name") ) {
                    return this.currency_short_name;
                }
                else if ( name.equals("current_sell_rate") ) {
                    return this.current_sell_rate;
                }
                else if ( name.equals("current_buy_rate") ) {
                    return this.current_buy_rate;
                }
                else if ( name.equals("current_sell_exec_rate") ) {
                    return this.current_sell_exec_rate;
                }
                else if ( name.equals("current_buy_exec_rate") ) {
                    return this.current_buy_exec_rate;
                }
                else if ( name.equals("change_jpy_round_div") ) {
                    return this.change_jpy_round_div;
                }
                else if ( name.equals("change_f_ccy_round_div") ) {
                    return this.change_f_ccy_round_div;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'e':
                if ( name.equals("exec_rate_last_updater") ) {
                    return this.exec_rate_last_updater;
                }
                else if ( name.equals("exec_rate_update_timestamp") ) {
                    return this.exec_rate_update_timestamp;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("prev_sell_rate") ) {
                    return this.prev_sell_rate;
                }
                else if ( name.equals("prev_buy_rate") ) {
                    return this.prev_buy_rate;
                }
                else if ( name.equals("prev_sell_exec_rate") ) {
                    return this.prev_sell_exec_rate;
                }
                else if ( name.equals("prev_buy_exec_rate") ) {
                    return this.prev_buy_exec_rate;
                }
                break;
            case 'r':
                if ( name.equals("rate_last_updater") ) {
                    return this.rate_last_updater;
                }
                else if ( name.equals("rate_update_timestamp") ) {
                    return this.rate_update_timestamp;
                }
                break;
            case 's':
                if ( name.equals("scale") ) {
                    return new Integer( scale );
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
                if ( name.equals("currency_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'currency_code' must be String: '"+value+"' is not." );
                    this.currency_code = (String) value;
                    if (this.currency_code_is_set)
                        this.currency_code_is_modified = true;
                    this.currency_code_is_set = true;
                    return;
                }
                else if ( name.equals("currency_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'currency_name' must be String: '"+value+"' is not." );
                    this.currency_name = (String) value;
                    if (this.currency_name_is_set)
                        this.currency_name_is_modified = true;
                    this.currency_name_is_set = true;
                    return;
                }
                else if ( name.equals("currency_short_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'currency_short_name' must be String: '"+value+"' is not." );
                    this.currency_short_name = (String) value;
                    if (this.currency_short_name_is_set)
                        this.currency_short_name_is_modified = true;
                    this.currency_short_name_is_set = true;
                    return;
                }
                else if ( name.equals("current_sell_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'current_sell_rate' must be Double: '"+value+"' is not." );
                    this.current_sell_rate = (Double) value;
                    if (this.current_sell_rate_is_set)
                        this.current_sell_rate_is_modified = true;
                    this.current_sell_rate_is_set = true;
                    return;
                }
                else if ( name.equals("current_buy_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'current_buy_rate' must be Double: '"+value+"' is not." );
                    this.current_buy_rate = (Double) value;
                    if (this.current_buy_rate_is_set)
                        this.current_buy_rate_is_modified = true;
                    this.current_buy_rate_is_set = true;
                    return;
                }
                else if ( name.equals("current_sell_exec_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'current_sell_exec_rate' must be Double: '"+value+"' is not." );
                    this.current_sell_exec_rate = (Double) value;
                    if (this.current_sell_exec_rate_is_set)
                        this.current_sell_exec_rate_is_modified = true;
                    this.current_sell_exec_rate_is_set = true;
                    return;
                }
                else if ( name.equals("current_buy_exec_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'current_buy_exec_rate' must be Double: '"+value+"' is not." );
                    this.current_buy_exec_rate = (Double) value;
                    if (this.current_buy_exec_rate_is_set)
                        this.current_buy_exec_rate_is_modified = true;
                    this.current_buy_exec_rate_is_set = true;
                    return;
                }
                else if ( name.equals("change_jpy_round_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'change_jpy_round_div' must be String: '"+value+"' is not." );
                    this.change_jpy_round_div = (String) value;
                    if (this.change_jpy_round_div_is_set)
                        this.change_jpy_round_div_is_modified = true;
                    this.change_jpy_round_div_is_set = true;
                    return;
                }
                else if ( name.equals("change_f_ccy_round_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'change_f_ccy_round_div' must be String: '"+value+"' is not." );
                    this.change_f_ccy_round_div = (String) value;
                    if (this.change_f_ccy_round_div_is_set)
                        this.change_f_ccy_round_div_is_modified = true;
                    this.change_f_ccy_round_div_is_set = true;
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
                if ( name.equals("exec_rate_last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'exec_rate_last_updater' must be String: '"+value+"' is not." );
                    this.exec_rate_last_updater = (String) value;
                    if (this.exec_rate_last_updater_is_set)
                        this.exec_rate_last_updater_is_modified = true;
                    this.exec_rate_last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("exec_rate_update_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'exec_rate_update_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.exec_rate_update_timestamp = (java.sql.Timestamp) value;
                    if (this.exec_rate_update_timestamp_is_set)
                        this.exec_rate_update_timestamp_is_modified = true;
                    this.exec_rate_update_timestamp_is_set = true;
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
                if ( name.equals("last_updater") ) {
                    if ( value != null )
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
            case 'p':
                if ( name.equals("prev_sell_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'prev_sell_rate' must be Double: '"+value+"' is not." );
                    this.prev_sell_rate = (Double) value;
                    if (this.prev_sell_rate_is_set)
                        this.prev_sell_rate_is_modified = true;
                    this.prev_sell_rate_is_set = true;
                    return;
                }
                else if ( name.equals("prev_buy_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'prev_buy_rate' must be Double: '"+value+"' is not." );
                    this.prev_buy_rate = (Double) value;
                    if (this.prev_buy_rate_is_set)
                        this.prev_buy_rate_is_modified = true;
                    this.prev_buy_rate_is_set = true;
                    return;
                }
                else if ( name.equals("prev_sell_exec_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'prev_sell_exec_rate' must be Double: '"+value+"' is not." );
                    this.prev_sell_exec_rate = (Double) value;
                    if (this.prev_sell_exec_rate_is_set)
                        this.prev_sell_exec_rate_is_modified = true;
                    this.prev_sell_exec_rate_is_set = true;
                    return;
                }
                else if ( name.equals("prev_buy_exec_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'prev_buy_exec_rate' must be Double: '"+value+"' is not." );
                    this.prev_buy_exec_rate = (Double) value;
                    if (this.prev_buy_exec_rate_is_set)
                        this.prev_buy_exec_rate_is_modified = true;
                    this.prev_buy_exec_rate_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("rate_last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'rate_last_updater' must be String: '"+value+"' is not." );
                    this.rate_last_updater = (String) value;
                    if (this.rate_last_updater_is_set)
                        this.rate_last_updater_is_modified = true;
                    this.rate_last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("rate_update_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'rate_update_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.rate_update_timestamp = (java.sql.Timestamp) value;
                    if (this.rate_update_timestamp_is_set)
                        this.rate_update_timestamp_is_modified = true;
                    this.rate_update_timestamp_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("scale") ) {
                    if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'scale' must be Integer: '"+value+"' is not." );
                    this.scale = ((Integer) value).intValue();
                    if (this.scale_is_set)
                        this.scale_is_modified = true;
                    this.scale_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
