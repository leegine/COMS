head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.40.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AccountProductOrderStopParams.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * account_product_order_stopテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AccountProductOrderStopRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AccountProductOrderStopParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AccountProductOrderStopParams}が{@@link AccountProductOrderStopRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccountProductOrderStopPK 
 * @@see AccountProductOrderStopRow 
 */
public class AccountProductOrderStopParams extends Params implements AccountProductOrderStopRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "account_product_order_stop";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AccountProductOrderStopRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AccountProductOrderStopRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_id</em>カラムの値 
   */
  public  long  branch_id;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  long  product_id;

  /** 
   * <em>apply_start_date</em>カラムの値 
   */
  public  java.sql.Timestamp  apply_start_date;

  /** 
   * <em>apply_end_date</em>カラムの値 
   */
  public  java.sql.Timestamp  apply_end_date;

  /** 
   * <em>stop_trade_reason</em>カラムの値 
   */
  public  String  stop_trade_reason;

  /** 
   * <em>stop_trade_div_buy_cash</em>カラムの値 
   */
  public  String  stop_trade_div_buy_cash;

  /** 
   * <em>stop_trade_div_sell_cash</em>カラムの値 
   */
  public  String  stop_trade_div_sell_cash;

  /** 
   * <em>stop_trade_div_long_margin</em>カラムの値 
   */
  public  String  stop_trade_div_long_margin;

  /** 
   * <em>stop_trade_div_short_margin</em>カラムの値 
   */
  public  String  stop_trade_div_short_margin;

  /** 
   * <em>stop_div_long_close_margin</em>カラムの値 
   */
  public  String  stop_div_long_close_margin;

  /** 
   * <em>stop_div_short_close_margin</em>カラムの値 
   */
  public  String  stop_div_short_close_margin;

  /** 
   * <em>stop_div_long_swap_margin</em>カラムの値 
   */
  public  String  stop_div_long_swap_margin;

  /** 
   * <em>stop_div_short_swap_margin</em>カラムの値 
   */
  public  String  stop_div_short_swap_margin;

  /** 
   * <em>stop_div_buy_mini_stock</em>カラムの値 
   */
  public  String  stop_div_buy_mini_stock;

  /** 
   * <em>stop_div_sell_mini_stock</em>カラムの値 
   */
  public  String  stop_div_sell_mini_stock;

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
  private boolean branch_id_is_set = false;
  private boolean branch_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean apply_start_date_is_set = false;
  private boolean apply_start_date_is_modified = false;
  private boolean apply_end_date_is_set = false;
  private boolean apply_end_date_is_modified = false;
  private boolean stop_trade_reason_is_set = false;
  private boolean stop_trade_reason_is_modified = false;
  private boolean stop_trade_div_buy_cash_is_set = false;
  private boolean stop_trade_div_buy_cash_is_modified = false;
  private boolean stop_trade_div_sell_cash_is_set = false;
  private boolean stop_trade_div_sell_cash_is_modified = false;
  private boolean stop_trade_div_long_margin_is_set = false;
  private boolean stop_trade_div_long_margin_is_modified = false;
  private boolean stop_trade_div_short_margin_is_set = false;
  private boolean stop_trade_div_short_margin_is_modified = false;
  private boolean stop_div_long_close_margin_is_set = false;
  private boolean stop_div_long_close_margin_is_modified = false;
  private boolean stop_div_short_close_margin_is_set = false;
  private boolean stop_div_short_close_margin_is_modified = false;
  private boolean stop_div_long_swap_margin_is_set = false;
  private boolean stop_div_long_swap_margin_is_modified = false;
  private boolean stop_div_short_swap_margin_is_set = false;
  private boolean stop_div_short_swap_margin_is_modified = false;
  private boolean stop_div_buy_mini_stock_is_set = false;
  private boolean stop_div_buy_mini_stock_is_modified = false;
  private boolean stop_div_sell_mini_stock_is_set = false;
  private boolean stop_div_sell_mini_stock_is_modified = false;
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
      + "," + "branch_id=" + branch_id
      + "," + "account_id=" + account_id
      + "," + "product_id=" + product_id
      + "," + "apply_start_date=" + apply_start_date
      + "," + "apply_end_date=" +apply_end_date
      + "," + "stop_trade_reason=" +stop_trade_reason
      + "," + "stop_trade_div_buy_cash=" +stop_trade_div_buy_cash
      + "," + "stop_trade_div_sell_cash=" +stop_trade_div_sell_cash
      + "," + "stop_trade_div_long_margin=" +stop_trade_div_long_margin
      + "," + "stop_trade_div_short_margin=" +stop_trade_div_short_margin
      + "," + "stop_div_long_close_margin=" +stop_div_long_close_margin
      + "," + "stop_div_short_close_margin=" +stop_div_short_close_margin
      + "," + "stop_div_long_swap_margin=" +stop_div_long_swap_margin
      + "," + "stop_div_short_swap_margin=" +stop_div_short_swap_margin
      + "," + "stop_div_buy_mini_stock=" +stop_div_buy_mini_stock
      + "," + "stop_div_sell_mini_stock=" +stop_div_sell_mini_stock
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のAccountProductOrderStopParamsオブジェクトを作成します。 
   */
  public AccountProductOrderStopParams() {
    institution_code_is_modified = true;
    branch_id_is_modified = true;
    account_id_is_modified = true;
    product_id_is_modified = true;
    apply_start_date_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAccountProductOrderStopRowオブジェクトの値を利用してAccountProductOrderStopParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAccountProductOrderStopRowオブジェクト 
   */
  public AccountProductOrderStopParams( AccountProductOrderStopRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_id = p_row.getBranchId();
    branch_id_is_set = p_row.getBranchIdIsSet();
    branch_id_is_modified = p_row.getBranchIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    product_id = p_row.getProductId();
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    apply_start_date = p_row.getApplyStartDate();
    apply_start_date_is_set = p_row.getApplyStartDateIsSet();
    apply_start_date_is_modified = p_row.getApplyStartDateIsModified();
    apply_end_date = p_row.getApplyEndDate();
    apply_end_date_is_set = p_row.getApplyEndDateIsSet();
    apply_end_date_is_modified = p_row.getApplyEndDateIsModified();
    stop_trade_reason = p_row.getStopTradeReason();
    stop_trade_reason_is_set = p_row.getStopTradeReasonIsSet();
    stop_trade_reason_is_modified = p_row.getStopTradeReasonIsModified();
    stop_trade_div_buy_cash = p_row.getStopTradeDivBuyCash();
    stop_trade_div_buy_cash_is_set = p_row.getStopTradeDivBuyCashIsSet();
    stop_trade_div_buy_cash_is_modified = p_row.getStopTradeDivBuyCashIsModified();
    stop_trade_div_sell_cash = p_row.getStopTradeDivSellCash();
    stop_trade_div_sell_cash_is_set = p_row.getStopTradeDivSellCashIsSet();
    stop_trade_div_sell_cash_is_modified = p_row.getStopTradeDivSellCashIsModified();
    stop_trade_div_long_margin = p_row.getStopTradeDivLongMargin();
    stop_trade_div_long_margin_is_set = p_row.getStopTradeDivLongMarginIsSet();
    stop_trade_div_long_margin_is_modified = p_row.getStopTradeDivLongMarginIsModified();
    stop_trade_div_short_margin = p_row.getStopTradeDivShortMargin();
    stop_trade_div_short_margin_is_set = p_row.getStopTradeDivShortMarginIsSet();
    stop_trade_div_short_margin_is_modified = p_row.getStopTradeDivShortMarginIsModified();
    stop_div_long_close_margin = p_row.getStopDivLongCloseMargin();
    stop_div_long_close_margin_is_set = p_row.getStopDivLongCloseMarginIsSet();
    stop_div_long_close_margin_is_modified = p_row.getStopDivLongCloseMarginIsModified();
    stop_div_short_close_margin = p_row.getStopDivShortCloseMargin();
    stop_div_short_close_margin_is_set = p_row.getStopDivShortCloseMarginIsSet();
    stop_div_short_close_margin_is_modified = p_row.getStopDivShortCloseMarginIsModified();
    stop_div_long_swap_margin = p_row.getStopDivLongSwapMargin();
    stop_div_long_swap_margin_is_set = p_row.getStopDivLongSwapMarginIsSet();
    stop_div_long_swap_margin_is_modified = p_row.getStopDivLongSwapMarginIsModified();
    stop_div_short_swap_margin = p_row.getStopDivShortSwapMargin();
    stop_div_short_swap_margin_is_set = p_row.getStopDivShortSwapMarginIsSet();
    stop_div_short_swap_margin_is_modified = p_row.getStopDivShortSwapMarginIsModified();
    stop_div_buy_mini_stock = p_row.getStopDivBuyMiniStock();
    stop_div_buy_mini_stock_is_set = p_row.getStopDivBuyMiniStockIsSet();
    stop_div_buy_mini_stock_is_modified = p_row.getStopDivBuyMiniStockIsModified();
    stop_div_sell_mini_stock = p_row.getStopDivSellMiniStock();
    stop_div_sell_mini_stock_is_set = p_row.getStopDivSellMiniStockIsSet();
    stop_div_sell_mini_stock_is_modified = p_row.getStopDivSellMiniStockIsModified();
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
      apply_end_date_is_set = true;
      apply_end_date_is_modified = true;
      stop_trade_reason_is_set = true;
      stop_trade_reason_is_modified = true;
      stop_trade_div_buy_cash_is_set = true;
      stop_trade_div_buy_cash_is_modified = true;
      stop_trade_div_sell_cash_is_set = true;
      stop_trade_div_sell_cash_is_modified = true;
      stop_trade_div_long_margin_is_set = true;
      stop_trade_div_long_margin_is_modified = true;
      stop_trade_div_short_margin_is_set = true;
      stop_trade_div_short_margin_is_modified = true;
      stop_div_long_close_margin_is_set = true;
      stop_div_long_close_margin_is_modified = true;
      stop_div_short_close_margin_is_set = true;
      stop_div_short_close_margin_is_modified = true;
      stop_div_long_swap_margin_is_set = true;
      stop_div_long_swap_margin_is_modified = true;
      stop_div_short_swap_margin_is_set = true;
      stop_div_short_swap_margin_is_modified = true;
      stop_div_buy_mini_stock_is_set = true;
      stop_div_buy_mini_stock_is_modified = true;
      stop_div_sell_mini_stock_is_set = true;
      stop_div_sell_mini_stock_is_modified = true;
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
    if ( !( other instanceof AccountProductOrderStopRow ) )
       return false;
    return fieldsEqual( (AccountProductOrderStopRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAccountProductOrderStopRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AccountProductOrderStopRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( branch_id != row.getBranchId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( product_id != row.getProductId() )
      return false;
    if ( apply_start_date == null ) {
      if ( row.getApplyStartDate() != null )
        return false;
    } else if ( !apply_start_date.equals( row.getApplyStartDate() ) ) {
        return false;
    }
    if ( apply_end_date == null ) {
      if ( row.getApplyEndDate() != null )
        return false;
    } else if ( !apply_end_date.equals( row.getApplyEndDate() ) ) {
        return false;
    }
    if ( stop_trade_reason == null ) {
      if ( row.getStopTradeReason() != null )
        return false;
    } else if ( !stop_trade_reason.equals( row.getStopTradeReason() ) ) {
        return false;
    }
    if ( stop_trade_div_buy_cash == null ) {
      if ( row.getStopTradeDivBuyCash() != null )
        return false;
    } else if ( !stop_trade_div_buy_cash.equals( row.getStopTradeDivBuyCash() ) ) {
        return false;
    }
    if ( stop_trade_div_sell_cash == null ) {
      if ( row.getStopTradeDivSellCash() != null )
        return false;
    } else if ( !stop_trade_div_sell_cash.equals( row.getStopTradeDivSellCash() ) ) {
        return false;
    }
    if ( stop_trade_div_long_margin == null ) {
      if ( row.getStopTradeDivLongMargin() != null )
        return false;
    } else if ( !stop_trade_div_long_margin.equals( row.getStopTradeDivLongMargin() ) ) {
        return false;
    }
    if ( stop_trade_div_short_margin == null ) {
      if ( row.getStopTradeDivShortMargin() != null )
        return false;
    } else if ( !stop_trade_div_short_margin.equals( row.getStopTradeDivShortMargin() ) ) {
        return false;
    }
    if ( stop_div_long_close_margin == null ) {
      if ( row.getStopDivLongCloseMargin() != null )
        return false;
    } else if ( !stop_div_long_close_margin.equals( row.getStopDivLongCloseMargin() ) ) {
        return false;
    }
    if ( stop_div_short_close_margin == null ) {
      if ( row.getStopDivShortCloseMargin() != null )
        return false;
    } else if ( !stop_div_short_close_margin.equals( row.getStopDivShortCloseMargin() ) ) {
        return false;
    }
    if ( stop_div_long_swap_margin == null ) {
      if ( row.getStopDivLongSwapMargin() != null )
        return false;
    } else if ( !stop_div_long_swap_margin.equals( row.getStopDivLongSwapMargin() ) ) {
        return false;
    }
    if ( stop_div_short_swap_margin == null ) {
      if ( row.getStopDivShortSwapMargin() != null )
        return false;
    } else if ( !stop_div_short_swap_margin.equals( row.getStopDivShortSwapMargin() ) ) {
        return false;
    }
    if ( stop_div_buy_mini_stock == null ) {
      if ( row.getStopDivBuyMiniStock() != null )
        return false;
    } else if ( !stop_div_buy_mini_stock.equals( row.getStopDivBuyMiniStock() ) ) {
        return false;
    }
    if ( stop_div_sell_mini_stock == null ) {
      if ( row.getStopDivSellMiniStock() != null )
        return false;
    } else if ( !stop_div_sell_mini_stock.equals( row.getStopDivSellMiniStock() ) ) {
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
        + ((int) branch_id)
        + ((int) account_id)
        + ((int) product_id)
        + (apply_start_date!=null? apply_start_date.hashCode(): 0) 
        + (apply_end_date!=null? apply_end_date.hashCode(): 0) 
        + (stop_trade_reason!=null? stop_trade_reason.hashCode(): 0) 
        + (stop_trade_div_buy_cash!=null? stop_trade_div_buy_cash.hashCode(): 0) 
        + (stop_trade_div_sell_cash!=null? stop_trade_div_sell_cash.hashCode(): 0) 
        + (stop_trade_div_long_margin!=null? stop_trade_div_long_margin.hashCode(): 0) 
        + (stop_trade_div_short_margin!=null? stop_trade_div_short_margin.hashCode(): 0) 
        + (stop_div_long_close_margin!=null? stop_div_long_close_margin.hashCode(): 0) 
        + (stop_div_short_close_margin!=null? stop_div_short_close_margin.hashCode(): 0) 
        + (stop_div_long_swap_margin!=null? stop_div_long_swap_margin.hashCode(): 0) 
        + (stop_div_short_swap_margin!=null? stop_div_short_swap_margin.hashCode(): 0) 
        + (stop_div_buy_mini_stock!=null? stop_div_buy_mini_stock.hashCode(): 0) 
        + (stop_div_sell_mini_stock!=null? stop_div_sell_mini_stock.hashCode(): 0) 
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
		if ( !apply_end_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'apply_end_date' must be set before inserting.");
		if ( !last_updater_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updater' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("institution_code",institution_code);
		map.put("branch_id",new Long(branch_id));
		map.put("account_id",new Long(account_id));
		map.put("product_id",new Long(product_id));
		map.put("apply_start_date",apply_start_date);
		map.put("apply_end_date",apply_end_date);
		if ( stop_trade_reason != null )
			map.put("stop_trade_reason",stop_trade_reason);
		if ( stop_trade_div_buy_cash != null )
			map.put("stop_trade_div_buy_cash",stop_trade_div_buy_cash);
		if ( stop_trade_div_sell_cash != null )
			map.put("stop_trade_div_sell_cash",stop_trade_div_sell_cash);
		if ( stop_trade_div_long_margin != null )
			map.put("stop_trade_div_long_margin",stop_trade_div_long_margin);
		if ( stop_trade_div_short_margin != null )
			map.put("stop_trade_div_short_margin",stop_trade_div_short_margin);
		if ( stop_div_long_close_margin != null )
			map.put("stop_div_long_close_margin",stop_div_long_close_margin);
		if ( stop_div_short_close_margin != null )
			map.put("stop_div_short_close_margin",stop_div_short_close_margin);
		if ( stop_div_long_swap_margin != null )
			map.put("stop_div_long_swap_margin",stop_div_long_swap_margin);
		if ( stop_div_short_swap_margin != null )
			map.put("stop_div_short_swap_margin",stop_div_short_swap_margin);
		if ( stop_div_buy_mini_stock != null )
			map.put("stop_div_buy_mini_stock",stop_div_buy_mini_stock);
		if ( stop_div_sell_mini_stock != null )
			map.put("stop_div_sell_mini_stock",stop_div_sell_mini_stock);
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
		if ( apply_end_date_is_modified )
			map.put("apply_end_date",apply_end_date);
		if ( stop_trade_reason_is_modified )
			map.put("stop_trade_reason",stop_trade_reason);
		if ( stop_trade_div_buy_cash_is_modified )
			map.put("stop_trade_div_buy_cash",stop_trade_div_buy_cash);
		if ( stop_trade_div_sell_cash_is_modified )
			map.put("stop_trade_div_sell_cash",stop_trade_div_sell_cash);
		if ( stop_trade_div_long_margin_is_modified )
			map.put("stop_trade_div_long_margin",stop_trade_div_long_margin);
		if ( stop_trade_div_short_margin_is_modified )
			map.put("stop_trade_div_short_margin",stop_trade_div_short_margin);
		if ( stop_div_long_close_margin_is_modified )
			map.put("stop_div_long_close_margin",stop_div_long_close_margin);
		if ( stop_div_short_close_margin_is_modified )
			map.put("stop_div_short_close_margin",stop_div_short_close_margin);
		if ( stop_div_long_swap_margin_is_modified )
			map.put("stop_div_long_swap_margin",stop_div_long_swap_margin);
		if ( stop_div_short_swap_margin_is_modified )
			map.put("stop_div_short_swap_margin",stop_div_short_swap_margin);
		if ( stop_div_buy_mini_stock_is_modified )
			map.put("stop_div_buy_mini_stock",stop_div_buy_mini_stock);
		if ( stop_div_sell_mini_stock_is_modified )
			map.put("stop_div_sell_mini_stock",stop_div_sell_mini_stock);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( apply_end_date_is_set )
				map.put("apply_end_date",apply_end_date);
			map.put("stop_trade_reason",stop_trade_reason);
			map.put("stop_trade_div_buy_cash",stop_trade_div_buy_cash);
			map.put("stop_trade_div_sell_cash",stop_trade_div_sell_cash);
			map.put("stop_trade_div_long_margin",stop_trade_div_long_margin);
			map.put("stop_trade_div_short_margin",stop_trade_div_short_margin);
			map.put("stop_div_long_close_margin",stop_div_long_close_margin);
			map.put("stop_div_short_close_margin",stop_div_short_close_margin);
			map.put("stop_div_long_swap_margin",stop_div_long_swap_margin);
			map.put("stop_div_short_swap_margin",stop_div_short_swap_margin);
			map.put("stop_div_buy_mini_stock",stop_div_buy_mini_stock);
			map.put("stop_div_sell_mini_stock",stop_div_sell_mini_stock);
			if ( last_updater_is_set )
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
   * <em>branch_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBranchId()
  {
    return branch_id;
  }


  /** 
   * <em>branch_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsSet() {
    return branch_id_is_set;
  }


  /** 
   * <em>branch_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchIdIsModified() {
    return branch_id_is_modified;
  }


  /** 
   * <em>account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountId()
  {
    return account_id;
  }


  /** 
   * <em>account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsSet() {
    return account_id_is_set;
  }


  /** 
   * <em>account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountIdIsModified() {
    return account_id_is_modified;
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
   * <em>apply_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getApplyStartDate()
  {
    return apply_start_date;
  }


  /** 
   * <em>apply_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApplyStartDateIsSet() {
    return apply_start_date_is_set;
  }


  /** 
   * <em>apply_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApplyStartDateIsModified() {
    return apply_start_date_is_modified;
  }


  /** 
   * <em>apply_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getApplyEndDate()
  {
    return apply_end_date;
  }


  /** 
   * <em>apply_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApplyEndDateIsSet() {
    return apply_end_date_is_set;
  }


  /** 
   * <em>apply_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApplyEndDateIsModified() {
    return apply_end_date_is_modified;
  }


  /** 
   * <em>stop_trade_reason</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStopTradeReason()
  {
    return stop_trade_reason;
  }


  /** 
   * <em>stop_trade_reason</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopTradeReasonIsSet() {
    return stop_trade_reason_is_set;
  }


  /** 
   * <em>stop_trade_reason</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopTradeReasonIsModified() {
    return stop_trade_reason_is_modified;
  }


  /** 
   * <em>stop_trade_div_buy_cash</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStopTradeDivBuyCash()
  {
    return stop_trade_div_buy_cash;
  }


  /** 
   * <em>stop_trade_div_buy_cash</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopTradeDivBuyCashIsSet() {
    return stop_trade_div_buy_cash_is_set;
  }


  /** 
   * <em>stop_trade_div_buy_cash</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopTradeDivBuyCashIsModified() {
    return stop_trade_div_buy_cash_is_modified;
  }


  /** 
   * <em>stop_trade_div_sell_cash</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStopTradeDivSellCash()
  {
    return stop_trade_div_sell_cash;
  }


  /** 
   * <em>stop_trade_div_sell_cash</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopTradeDivSellCashIsSet() {
    return stop_trade_div_sell_cash_is_set;
  }


  /** 
   * <em>stop_trade_div_sell_cash</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopTradeDivSellCashIsModified() {
    return stop_trade_div_sell_cash_is_modified;
  }


  /** 
   * <em>stop_trade_div_long_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStopTradeDivLongMargin()
  {
    return stop_trade_div_long_margin;
  }


  /** 
   * <em>stop_trade_div_long_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopTradeDivLongMarginIsSet() {
    return stop_trade_div_long_margin_is_set;
  }


  /** 
   * <em>stop_trade_div_long_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopTradeDivLongMarginIsModified() {
    return stop_trade_div_long_margin_is_modified;
  }


  /** 
   * <em>stop_trade_div_short_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStopTradeDivShortMargin()
  {
    return stop_trade_div_short_margin;
  }


  /** 
   * <em>stop_trade_div_short_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopTradeDivShortMarginIsSet() {
    return stop_trade_div_short_margin_is_set;
  }


  /** 
   * <em>stop_trade_div_short_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopTradeDivShortMarginIsModified() {
    return stop_trade_div_short_margin_is_modified;
  }


  /** 
   * <em>stop_div_long_close_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStopDivLongCloseMargin()
  {
    return stop_div_long_close_margin;
  }


  /** 
   * <em>stop_div_long_close_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopDivLongCloseMarginIsSet() {
    return stop_div_long_close_margin_is_set;
  }


  /** 
   * <em>stop_div_long_close_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopDivLongCloseMarginIsModified() {
    return stop_div_long_close_margin_is_modified;
  }


  /** 
   * <em>stop_div_short_close_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStopDivShortCloseMargin()
  {
    return stop_div_short_close_margin;
  }


  /** 
   * <em>stop_div_short_close_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopDivShortCloseMarginIsSet() {
    return stop_div_short_close_margin_is_set;
  }


  /** 
   * <em>stop_div_short_close_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopDivShortCloseMarginIsModified() {
    return stop_div_short_close_margin_is_modified;
  }


  /** 
   * <em>stop_div_long_swap_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStopDivLongSwapMargin()
  {
    return stop_div_long_swap_margin;
  }


  /** 
   * <em>stop_div_long_swap_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopDivLongSwapMarginIsSet() {
    return stop_div_long_swap_margin_is_set;
  }


  /** 
   * <em>stop_div_long_swap_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopDivLongSwapMarginIsModified() {
    return stop_div_long_swap_margin_is_modified;
  }


  /** 
   * <em>stop_div_short_swap_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStopDivShortSwapMargin()
  {
    return stop_div_short_swap_margin;
  }


  /** 
   * <em>stop_div_short_swap_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopDivShortSwapMarginIsSet() {
    return stop_div_short_swap_margin_is_set;
  }


  /** 
   * <em>stop_div_short_swap_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopDivShortSwapMarginIsModified() {
    return stop_div_short_swap_margin_is_modified;
  }


  /** 
   * <em>stop_div_buy_mini_stock</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStopDivBuyMiniStock()
  {
    return stop_div_buy_mini_stock;
  }


  /** 
   * <em>stop_div_buy_mini_stock</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopDivBuyMiniStockIsSet() {
    return stop_div_buy_mini_stock_is_set;
  }


  /** 
   * <em>stop_div_buy_mini_stock</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopDivBuyMiniStockIsModified() {
    return stop_div_buy_mini_stock_is_modified;
  }


  /** 
   * <em>stop_div_sell_mini_stock</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStopDivSellMiniStock()
  {
    return stop_div_sell_mini_stock;
  }


  /** 
   * <em>stop_div_sell_mini_stock</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopDivSellMiniStockIsSet() {
    return stop_div_sell_mini_stock_is_set;
  }


  /** 
   * <em>stop_div_sell_mini_stock</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStopDivSellMiniStockIsModified() {
    return stop_div_sell_mini_stock_is_modified;
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
    return new AccountProductOrderStopPK(institution_code, branch_id, account_id, product_id, apply_start_date);
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
   * <em>branch_id</em>カラムの値を設定します。 
   *
   * @@param p_branchId <em>branch_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setBranchId( long p_branchId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_id = p_branchId;
    branch_id_is_set = true;
    branch_id_is_modified = true;
  }


  /** 
   * <em>account_id</em>カラムの値を設定します。 
   *
   * @@param p_accountId <em>account_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAccountId( long p_accountId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_id = p_accountId;
    account_id_is_set = true;
    account_id_is_modified = true;
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
   * <em>apply_start_date</em>カラムの値を設定します。 
   *
   * @@param p_applyStartDate <em>apply_start_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setApplyStartDate( java.sql.Timestamp p_applyStartDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    apply_start_date = p_applyStartDate;
    apply_start_date_is_set = true;
    apply_start_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>apply_start_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setApplyStartDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    apply_start_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    apply_start_date_is_set = true;
    apply_start_date_is_modified = true;
  }


  /** 
   * <em>apply_end_date</em>カラムの値を設定します。 
   *
   * @@param p_applyEndDate <em>apply_end_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setApplyEndDate( java.sql.Timestamp p_applyEndDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    apply_end_date = p_applyEndDate;
    apply_end_date_is_set = true;
    apply_end_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>apply_end_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setApplyEndDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    apply_end_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    apply_end_date_is_set = true;
    apply_end_date_is_modified = true;
  }


  /** 
   * <em>stop_trade_reason</em>カラムの値を設定します。 
   *
   * @@param p_stopTradeReason <em>stop_trade_reason</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setStopTradeReason( String p_stopTradeReason )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_trade_reason = p_stopTradeReason;
    stop_trade_reason_is_set = true;
    stop_trade_reason_is_modified = true;
  }


  /** 
   * <em>stop_trade_div_buy_cash</em>カラムの値を設定します。 
   *
   * @@param p_stopTradeDivBuyCash <em>stop_trade_div_buy_cash</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStopTradeDivBuyCash( String p_stopTradeDivBuyCash )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_trade_div_buy_cash = p_stopTradeDivBuyCash;
    stop_trade_div_buy_cash_is_set = true;
    stop_trade_div_buy_cash_is_modified = true;
  }


  /** 
   * <em>stop_trade_div_sell_cash</em>カラムの値を設定します。 
   *
   * @@param p_stopTradeDivSellCash <em>stop_trade_div_sell_cash</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStopTradeDivSellCash( String p_stopTradeDivSellCash )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_trade_div_sell_cash = p_stopTradeDivSellCash;
    stop_trade_div_sell_cash_is_set = true;
    stop_trade_div_sell_cash_is_modified = true;
  }


  /** 
   * <em>stop_trade_div_long_margin</em>カラムの値を設定します。 
   *
   * @@param p_stopTradeDivLongMargin <em>stop_trade_div_long_margin</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStopTradeDivLongMargin( String p_stopTradeDivLongMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_trade_div_long_margin = p_stopTradeDivLongMargin;
    stop_trade_div_long_margin_is_set = true;
    stop_trade_div_long_margin_is_modified = true;
  }


  /** 
   * <em>stop_trade_div_short_margin</em>カラムの値を設定します。 
   *
   * @@param p_stopTradeDivShortMargin <em>stop_trade_div_short_margin</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStopTradeDivShortMargin( String p_stopTradeDivShortMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_trade_div_short_margin = p_stopTradeDivShortMargin;
    stop_trade_div_short_margin_is_set = true;
    stop_trade_div_short_margin_is_modified = true;
  }


  /** 
   * <em>stop_div_long_close_margin</em>カラムの値を設定します。 
   *
   * @@param p_stopDivLongCloseMargin <em>stop_div_long_close_margin</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStopDivLongCloseMargin( String p_stopDivLongCloseMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_div_long_close_margin = p_stopDivLongCloseMargin;
    stop_div_long_close_margin_is_set = true;
    stop_div_long_close_margin_is_modified = true;
  }


  /** 
   * <em>stop_div_short_close_margin</em>カラムの値を設定します。 
   *
   * @@param p_stopDivShortCloseMargin <em>stop_div_short_close_margin</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStopDivShortCloseMargin( String p_stopDivShortCloseMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_div_short_close_margin = p_stopDivShortCloseMargin;
    stop_div_short_close_margin_is_set = true;
    stop_div_short_close_margin_is_modified = true;
  }


  /** 
   * <em>stop_div_long_swap_margin</em>カラムの値を設定します。 
   *
   * @@param p_stopDivLongSwapMargin <em>stop_div_long_swap_margin</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStopDivLongSwapMargin( String p_stopDivLongSwapMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_div_long_swap_margin = p_stopDivLongSwapMargin;
    stop_div_long_swap_margin_is_set = true;
    stop_div_long_swap_margin_is_modified = true;
  }


  /** 
   * <em>stop_div_short_swap_margin</em>カラムの値を設定します。 
   *
   * @@param p_stopDivShortSwapMargin <em>stop_div_short_swap_margin</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStopDivShortSwapMargin( String p_stopDivShortSwapMargin )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_div_short_swap_margin = p_stopDivShortSwapMargin;
    stop_div_short_swap_margin_is_set = true;
    stop_div_short_swap_margin_is_modified = true;
  }


  /** 
   * <em>stop_div_buy_mini_stock</em>カラムの値を設定します。 
   *
   * @@param p_stopDivBuyMiniStock <em>stop_div_buy_mini_stock</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStopDivBuyMiniStock( String p_stopDivBuyMiniStock )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_div_buy_mini_stock = p_stopDivBuyMiniStock;
    stop_div_buy_mini_stock_is_set = true;
    stop_div_buy_mini_stock_is_modified = true;
  }


  /** 
   * <em>stop_div_sell_mini_stock</em>カラムの値を設定します。 
   *
   * @@param p_stopDivSellMiniStock <em>stop_div_sell_mini_stock</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setStopDivSellMiniStock( String p_stopDivSellMiniStock )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    stop_div_sell_mini_stock = p_stopDivSellMiniStock;
    stop_div_sell_mini_stock_is_set = true;
    stop_div_sell_mini_stock_is_modified = true;
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
            case 'a':
                if ( name.equals("account_id") ) {
                    return new Long( account_id );
                }
                else if ( name.equals("apply_start_date") ) {
                    return this.apply_start_date;
                }
                else if ( name.equals("apply_end_date") ) {
                    return this.apply_end_date;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    return new Long( branch_id );
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
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return new Long( product_id );
                }
                break;
            case 's':
                if ( name.equals("stop_trade_reason") ) {
                    return this.stop_trade_reason;
                }
                else if ( name.equals("stop_trade_div_buy_cash") ) {
                    return this.stop_trade_div_buy_cash;
                }
                else if ( name.equals("stop_trade_div_sell_cash") ) {
                    return this.stop_trade_div_sell_cash;
                }
                else if ( name.equals("stop_trade_div_long_margin") ) {
                    return this.stop_trade_div_long_margin;
                }
                else if ( name.equals("stop_trade_div_short_margin") ) {
                    return this.stop_trade_div_short_margin;
                }
                else if ( name.equals("stop_div_long_close_margin") ) {
                    return this.stop_div_long_close_margin;
                }
                else if ( name.equals("stop_div_short_close_margin") ) {
                    return this.stop_div_short_close_margin;
                }
                else if ( name.equals("stop_div_long_swap_margin") ) {
                    return this.stop_div_long_swap_margin;
                }
                else if ( name.equals("stop_div_short_swap_margin") ) {
                    return this.stop_div_short_swap_margin;
                }
                else if ( name.equals("stop_div_buy_mini_stock") ) {
                    return this.stop_div_buy_mini_stock;
                }
                else if ( name.equals("stop_div_sell_mini_stock") ) {
                    return this.stop_div_sell_mini_stock;
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
                if ( name.equals("account_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_id' must be Long: '"+value+"' is not." );
                    this.account_id = ((Long) value).longValue();
                    if (this.account_id_is_set)
                        this.account_id_is_modified = true;
                    this.account_id_is_set = true;
                    return;
                }
                else if ( name.equals("apply_start_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'apply_start_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.apply_start_date = (java.sql.Timestamp) value;
                    if (this.apply_start_date_is_set)
                        this.apply_start_date_is_modified = true;
                    this.apply_start_date_is_set = true;
                    return;
                }
                else if ( name.equals("apply_end_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'apply_end_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.apply_end_date = (java.sql.Timestamp) value;
                    if (this.apply_end_date_is_set)
                        this.apply_end_date_is_modified = true;
                    this.apply_end_date_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'branch_id' must be Long: '"+value+"' is not." );
                    this.branch_id = ((Long) value).longValue();
                    if (this.branch_id_is_set)
                        this.branch_id_is_modified = true;
                    this.branch_id_is_set = true;
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
                if ( name.equals("last_updater") ) {
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
                if ( name.equals("stop_trade_reason") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_trade_reason' must be String: '"+value+"' is not." );
                    this.stop_trade_reason = (String) value;
                    if (this.stop_trade_reason_is_set)
                        this.stop_trade_reason_is_modified = true;
                    this.stop_trade_reason_is_set = true;
                    return;
                }
                else if ( name.equals("stop_trade_div_buy_cash") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_trade_div_buy_cash' must be String: '"+value+"' is not." );
                    this.stop_trade_div_buy_cash = (String) value;
                    if (this.stop_trade_div_buy_cash_is_set)
                        this.stop_trade_div_buy_cash_is_modified = true;
                    this.stop_trade_div_buy_cash_is_set = true;
                    return;
                }
                else if ( name.equals("stop_trade_div_sell_cash") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_trade_div_sell_cash' must be String: '"+value+"' is not." );
                    this.stop_trade_div_sell_cash = (String) value;
                    if (this.stop_trade_div_sell_cash_is_set)
                        this.stop_trade_div_sell_cash_is_modified = true;
                    this.stop_trade_div_sell_cash_is_set = true;
                    return;
                }
                else if ( name.equals("stop_trade_div_long_margin") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_trade_div_long_margin' must be String: '"+value+"' is not." );
                    this.stop_trade_div_long_margin = (String) value;
                    if (this.stop_trade_div_long_margin_is_set)
                        this.stop_trade_div_long_margin_is_modified = true;
                    this.stop_trade_div_long_margin_is_set = true;
                    return;
                }
                else if ( name.equals("stop_trade_div_short_margin") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_trade_div_short_margin' must be String: '"+value+"' is not." );
                    this.stop_trade_div_short_margin = (String) value;
                    if (this.stop_trade_div_short_margin_is_set)
                        this.stop_trade_div_short_margin_is_modified = true;
                    this.stop_trade_div_short_margin_is_set = true;
                    return;
                }
                else if ( name.equals("stop_div_long_close_margin") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_div_long_close_margin' must be String: '"+value+"' is not." );
                    this.stop_div_long_close_margin = (String) value;
                    if (this.stop_div_long_close_margin_is_set)
                        this.stop_div_long_close_margin_is_modified = true;
                    this.stop_div_long_close_margin_is_set = true;
                    return;
                }
                else if ( name.equals("stop_div_short_close_margin") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_div_short_close_margin' must be String: '"+value+"' is not." );
                    this.stop_div_short_close_margin = (String) value;
                    if (this.stop_div_short_close_margin_is_set)
                        this.stop_div_short_close_margin_is_modified = true;
                    this.stop_div_short_close_margin_is_set = true;
                    return;
                }
                else if ( name.equals("stop_div_long_swap_margin") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_div_long_swap_margin' must be String: '"+value+"' is not." );
                    this.stop_div_long_swap_margin = (String) value;
                    if (this.stop_div_long_swap_margin_is_set)
                        this.stop_div_long_swap_margin_is_modified = true;
                    this.stop_div_long_swap_margin_is_set = true;
                    return;
                }
                else if ( name.equals("stop_div_short_swap_margin") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_div_short_swap_margin' must be String: '"+value+"' is not." );
                    this.stop_div_short_swap_margin = (String) value;
                    if (this.stop_div_short_swap_margin_is_set)
                        this.stop_div_short_swap_margin_is_modified = true;
                    this.stop_div_short_swap_margin_is_set = true;
                    return;
                }
                else if ( name.equals("stop_div_buy_mini_stock") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_div_buy_mini_stock' must be String: '"+value+"' is not." );
                    this.stop_div_buy_mini_stock = (String) value;
                    if (this.stop_div_buy_mini_stock_is_set)
                        this.stop_div_buy_mini_stock_is_modified = true;
                    this.stop_div_buy_mini_stock_is_set = true;
                    return;
                }
                else if ( name.equals("stop_div_sell_mini_stock") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'stop_div_sell_mini_stock' must be String: '"+value+"' is not." );
                    this.stop_div_sell_mini_stock = (String) value;
                    if (this.stop_div_sell_mini_stock_is_set)
                        this.stop_div_sell_mini_stock_is_modified = true;
                    this.stop_div_sell_mini_stock_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
