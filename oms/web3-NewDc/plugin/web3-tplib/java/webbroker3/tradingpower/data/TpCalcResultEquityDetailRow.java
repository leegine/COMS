head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.28.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCalcResultEquityDetailRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * TpCalcResultEquityDetailRowインタフェースは変更不可でリードオンリーである<em>tp_calc_result_equity_detail</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link TpCalcResultEquityDetailRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpCalcResultEquityDetailPK 
 */
public interface TpCalcResultEquityDetailRow extends Row {


  /** 
   * この{@@link TpCalcResultEquityDetailRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "tp_calc_result_equity_detail", "account" );


  /** 
   * <em>calc_result_equity_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getCalcResultEquityId();


  /** 
   * <em>calc_result_equity_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcResultEquityIdIsSet();


  /** 
   * <em>calc_result_equity_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcResultEquityIdIsModified();


  /** 
   * <em>account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAccountId();


  /** 
   * <em>account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountIdIsSet();


  /** 
   * <em>account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountIdIsModified();


  /** 
   * <em>equity_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getEquityAssetDelivered();


  /** 
   * <em>equity_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getEquityAssetDeliveredIsNull();


  /** 
   * <em>equity_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityAssetDeliveredIsSet();


  /** 
   * <em>equity_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityAssetDeliveredIsModified();


  /** 
   * <em>equity_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getEquityAssetExecuted();


  /** 
   * <em>equity_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getEquityAssetExecutedIsNull();


  /** 
   * <em>equity_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityAssetExecutedIsSet();


  /** 
   * <em>equity_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityAssetExecutedIsModified();


  /** 
   * <em>mini_stock_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMiniStockAssetDelivered();


  /** 
   * <em>mini_stock_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMiniStockAssetDeliveredIsNull();


  /** 
   * <em>mini_stock_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMiniStockAssetDeliveredIsSet();


  /** 
   * <em>mini_stock_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMiniStockAssetDeliveredIsModified();


  /** 
   * <em>mini_stock_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMiniStockAssetExecuted();


  /** 
   * <em>mini_stock_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMiniStockAssetExecutedIsNull();


  /** 
   * <em>mini_stock_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMiniStockAssetExecutedIsSet();


  /** 
   * <em>mini_stock_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMiniStockAssetExecutedIsModified();


  /** 
   * <em>ruito_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getRuitoAssetDelivered();


  /** 
   * <em>ruito_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRuitoAssetDeliveredIsNull();


  /** 
   * <em>ruito_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRuitoAssetDeliveredIsSet();


  /** 
   * <em>ruito_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRuitoAssetDeliveredIsModified();


  /** 
   * <em>ruito_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getRuitoAssetExecuted();


  /** 
   * <em>ruito_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getRuitoAssetExecutedIsNull();


  /** 
   * <em>ruito_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRuitoAssetExecutedIsSet();


  /** 
   * <em>ruito_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRuitoAssetExecutedIsModified();


  /** 
   * <em>mutual_fund_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMutualFundAssetDelivered();


  /** 
   * <em>mutual_fund_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMutualFundAssetDeliveredIsNull();


  /** 
   * <em>mutual_fund_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMutualFundAssetDeliveredIsSet();


  /** 
   * <em>mutual_fund_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMutualFundAssetDeliveredIsModified();


  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getMutualFundAssetExecuted();


  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMutualFundAssetExecutedIsNull();


  /** 
   * <em>mutual_fund_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMutualFundAssetExecutedIsSet();


  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMutualFundAssetExecutedIsModified();


  /** 
   * <em>bond_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getBondAssetDelivered();


  /** 
   * <em>bond_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBondAssetDeliveredIsNull();


  /** 
   * <em>bond_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondAssetDeliveredIsSet();


  /** 
   * <em>bond_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondAssetDeliveredIsModified();


  /** 
   * <em>bond_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getBondAssetExecuted();


  /** 
   * <em>bond_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBondAssetExecutedIsNull();


  /** 
   * <em>bond_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondAssetExecutedIsSet();


  /** 
   * <em>bond_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBondAssetExecutedIsModified();


  /** 
   * <em>payment_amount_designate_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentAmountDesignate0();


  /** 
   * <em>payment_amount_designate_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentAmountDesignate0IsNull();


  /** 
   * <em>payment_amount_designate_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate0IsSet();


  /** 
   * <em>payment_amount_designate_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate0IsModified();


  /** 
   * <em>payment_amount_designate_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentAmountDesignate1();


  /** 
   * <em>payment_amount_designate_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentAmountDesignate1IsNull();


  /** 
   * <em>payment_amount_designate_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate1IsSet();


  /** 
   * <em>payment_amount_designate_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate1IsModified();


  /** 
   * <em>payment_amount_designate_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentAmountDesignate2();


  /** 
   * <em>payment_amount_designate_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentAmountDesignate2IsNull();


  /** 
   * <em>payment_amount_designate_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate2IsSet();


  /** 
   * <em>payment_amount_designate_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate2IsModified();


  /** 
   * <em>payment_amount_designate_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentAmountDesignate3();


  /** 
   * <em>payment_amount_designate_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentAmountDesignate3IsNull();


  /** 
   * <em>payment_amount_designate_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate3IsSet();


  /** 
   * <em>payment_amount_designate_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate3IsModified();


  /** 
   * <em>payment_amount_designate_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentAmountDesignate4();


  /** 
   * <em>payment_amount_designate_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentAmountDesignate4IsNull();


  /** 
   * <em>payment_amount_designate_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate4IsSet();


  /** 
   * <em>payment_amount_designate_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate4IsModified();


  /** 
   * <em>payment_amount_designate_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getPaymentAmountDesignate5();


  /** 
   * <em>payment_amount_designate_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getPaymentAmountDesignate5IsNull();


  /** 
   * <em>payment_amount_designate_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate5IsSet();


  /** 
   * <em>payment_amount_designate_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPaymentAmountDesignate5IsModified();


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
   * <em>foreign_equity_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getForeignEquityAssetDelivered();


  /** 
   * <em>foreign_equity_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getForeignEquityAssetDeliveredIsNull();


  /** 
   * <em>foreign_equity_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignEquityAssetDeliveredIsSet();


  /** 
   * <em>foreign_equity_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignEquityAssetDeliveredIsModified();


  /** 
   * <em>foreign_equity_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getForeignEquityAssetExecuted();


  /** 
   * <em>foreign_equity_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getForeignEquityAssetExecutedIsNull();


  /** 
   * <em>foreign_equity_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignEquityAssetExecutedIsSet();


  /** 
   * <em>foreign_equity_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignEquityAssetExecutedIsModified();


}
@
