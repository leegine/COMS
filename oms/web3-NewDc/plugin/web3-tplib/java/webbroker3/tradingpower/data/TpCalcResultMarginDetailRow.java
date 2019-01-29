head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.28.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCalcResultMarginDetailRow.java;


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
 * TpCalcResultMarginDetailRowインタフェースは変更不可でリードオンリーである<em>tp_calc_result_margin_detail</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link TpCalcResultMarginDetailRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpCalcResultMarginDetailPK 
 */
public interface TpCalcResultMarginDetailRow extends Row {


  /** 
   * この{@@link TpCalcResultMarginDetailRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "tp_calc_result_margin_detail", "account" );


  /** 
   * <em>calc_result_margin_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getCalcResultMarginId();


  /** 
   * <em>calc_result_margin_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcResultMarginIdIsSet();


  /** 
   * <em>calc_result_margin_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCalcResultMarginIdIsModified();


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
   * <em>unexec_substi_security_asset_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecSubstiSecurityAsset0();


  /** 
   * <em>unexec_substi_security_asset_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecSubstiSecurityAsset0IsNull();


  /** 
   * <em>unexec_substi_security_asset_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecSubstiSecurityAsset0IsSet();


  /** 
   * <em>unexec_substi_security_asset_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecSubstiSecurityAsset0IsModified();


  /** 
   * <em>unexec_substi_security_asset_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecSubstiSecurityAsset1();


  /** 
   * <em>unexec_substi_security_asset_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecSubstiSecurityAsset1IsNull();


  /** 
   * <em>unexec_substi_security_asset_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecSubstiSecurityAsset1IsSet();


  /** 
   * <em>unexec_substi_security_asset_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecSubstiSecurityAsset1IsModified();


  /** 
   * <em>unexec_substi_security_asset_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecSubstiSecurityAsset2();


  /** 
   * <em>unexec_substi_security_asset_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecSubstiSecurityAsset2IsNull();


  /** 
   * <em>unexec_substi_security_asset_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecSubstiSecurityAsset2IsSet();


  /** 
   * <em>unexec_substi_security_asset_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecSubstiSecurityAsset2IsModified();


  /** 
   * <em>unexec_substi_security_asset_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecSubstiSecurityAsset3();


  /** 
   * <em>unexec_substi_security_asset_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecSubstiSecurityAsset3IsNull();


  /** 
   * <em>unexec_substi_security_asset_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecSubstiSecurityAsset3IsSet();


  /** 
   * <em>unexec_substi_security_asset_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecSubstiSecurityAsset3IsModified();


  /** 
   * <em>unexec_substi_security_asset_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecSubstiSecurityAsset4();


  /** 
   * <em>unexec_substi_security_asset_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecSubstiSecurityAsset4IsNull();


  /** 
   * <em>unexec_substi_security_asset_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecSubstiSecurityAsset4IsSet();


  /** 
   * <em>unexec_substi_security_asset_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecSubstiSecurityAsset4IsModified();


  /** 
   * <em>unexec_substi_security_asset_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecSubstiSecurityAsset5();


  /** 
   * <em>unexec_substi_security_asset_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecSubstiSecurityAsset5IsNull();


  /** 
   * <em>unexec_substi_security_asset_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecSubstiSecurityAsset5IsSet();


  /** 
   * <em>unexec_substi_security_asset_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecSubstiSecurityAsset5IsModified();


  /** 
   * <em>contract_asset_loss</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetLoss();


  /** 
   * <em>contract_asset_loss</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetLossIsNull();


  /** 
   * <em>contract_asset_loss</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetLossIsSet();


  /** 
   * <em>contract_asset_loss</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetLossIsModified();


  /** 
   * <em>contract_asset_profit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetProfit();


  /** 
   * <em>contract_asset_profit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetProfitIsNull();


  /** 
   * <em>contract_asset_profit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfitIsSet();


  /** 
   * <em>contract_asset_profit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfitIsModified();


  /** 
   * <em>setup_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSetupFee();


  /** 
   * <em>setup_fee</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSetupFeeIsNull();


  /** 
   * <em>setup_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSetupFeeIsSet();


  /** 
   * <em>setup_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSetupFeeIsModified();


  /** 
   * <em>contract_interest_loss</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractInterestLoss();


  /** 
   * <em>contract_interest_loss</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractInterestLossIsNull();


  /** 
   * <em>contract_interest_loss</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestLossIsSet();


  /** 
   * <em>contract_interest_loss</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestLossIsModified();


  /** 
   * <em>contract_interest_profit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractInterestProfit();


  /** 
   * <em>contract_interest_profit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractInterestProfitIsNull();


  /** 
   * <em>contract_interest_profit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestProfitIsSet();


  /** 
   * <em>contract_interest_profit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestProfitIsModified();


  /** 
   * <em>contract_other_cost</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractOtherCost();


  /** 
   * <em>contract_other_cost</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractOtherCostIsNull();


  /** 
   * <em>contract_other_cost</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractOtherCostIsSet();


  /** 
   * <em>contract_other_cost</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractOtherCostIsModified();


  /** 
   * <em>unexec_contract_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecContractAmount0();


  /** 
   * <em>unexec_contract_amount_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecContractAmount0IsNull();


  /** 
   * <em>unexec_contract_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecContractAmount0IsSet();


  /** 
   * <em>unexec_contract_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecContractAmount0IsModified();


  /** 
   * <em>unexec_contract_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecContractAmount1();


  /** 
   * <em>unexec_contract_amount_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecContractAmount1IsNull();


  /** 
   * <em>unexec_contract_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecContractAmount1IsSet();


  /** 
   * <em>unexec_contract_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecContractAmount1IsModified();


  /** 
   * <em>unexec_contract_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecContractAmount2();


  /** 
   * <em>unexec_contract_amount_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecContractAmount2IsNull();


  /** 
   * <em>unexec_contract_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecContractAmount2IsSet();


  /** 
   * <em>unexec_contract_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecContractAmount2IsModified();


  /** 
   * <em>unexec_contract_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecContractAmount3();


  /** 
   * <em>unexec_contract_amount_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecContractAmount3IsNull();


  /** 
   * <em>unexec_contract_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecContractAmount3IsSet();


  /** 
   * <em>unexec_contract_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecContractAmount3IsModified();


  /** 
   * <em>unexec_contract_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecContractAmount4();


  /** 
   * <em>unexec_contract_amount_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecContractAmount4IsNull();


  /** 
   * <em>unexec_contract_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecContractAmount4IsSet();


  /** 
   * <em>unexec_contract_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecContractAmount4IsModified();


  /** 
   * <em>unexec_contract_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecContractAmount5();


  /** 
   * <em>unexec_contract_amount_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecContractAmount5IsNull();


  /** 
   * <em>unexec_contract_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecContractAmount5IsSet();


  /** 
   * <em>unexec_contract_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecContractAmount5IsModified();


  /** 
   * <em>unexec_margin_deposit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecMarginDeposit0();


  /** 
   * <em>unexec_margin_deposit_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecMarginDeposit0IsNull();


  /** 
   * <em>unexec_margin_deposit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecMarginDeposit0IsSet();


  /** 
   * <em>unexec_margin_deposit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecMarginDeposit0IsModified();


  /** 
   * <em>unexec_margin_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecMarginDeposit1();


  /** 
   * <em>unexec_margin_deposit_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecMarginDeposit1IsNull();


  /** 
   * <em>unexec_margin_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecMarginDeposit1IsSet();


  /** 
   * <em>unexec_margin_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecMarginDeposit1IsModified();


  /** 
   * <em>unexec_margin_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecMarginDeposit2();


  /** 
   * <em>unexec_margin_deposit_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecMarginDeposit2IsNull();


  /** 
   * <em>unexec_margin_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecMarginDeposit2IsSet();


  /** 
   * <em>unexec_margin_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecMarginDeposit2IsModified();


  /** 
   * <em>unexec_margin_deposit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecMarginDeposit3();


  /** 
   * <em>unexec_margin_deposit_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecMarginDeposit3IsNull();


  /** 
   * <em>unexec_margin_deposit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecMarginDeposit3IsSet();


  /** 
   * <em>unexec_margin_deposit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecMarginDeposit3IsModified();


  /** 
   * <em>unexec_margin_deposit_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecMarginDeposit4();


  /** 
   * <em>unexec_margin_deposit_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecMarginDeposit4IsNull();


  /** 
   * <em>unexec_margin_deposit_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecMarginDeposit4IsSet();


  /** 
   * <em>unexec_margin_deposit_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecMarginDeposit4IsModified();


  /** 
   * <em>unexec_margin_deposit_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecMarginDeposit5();


  /** 
   * <em>unexec_margin_deposit_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecMarginDeposit5IsNull();


  /** 
   * <em>unexec_margin_deposit_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecMarginDeposit5IsSet();


  /** 
   * <em>unexec_margin_deposit_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecMarginDeposit5IsModified();


  /** 
   * <em>unexec_cash_margin_deposit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecCashMarginDeposit0();


  /** 
   * <em>unexec_cash_margin_deposit_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecCashMarginDeposit0IsNull();


  /** 
   * <em>unexec_cash_margin_deposit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecCashMarginDeposit0IsSet();


  /** 
   * <em>unexec_cash_margin_deposit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecCashMarginDeposit0IsModified();


  /** 
   * <em>unexec_cash_margin_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecCashMarginDeposit1();


  /** 
   * <em>unexec_cash_margin_deposit_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecCashMarginDeposit1IsNull();


  /** 
   * <em>unexec_cash_margin_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecCashMarginDeposit1IsSet();


  /** 
   * <em>unexec_cash_margin_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecCashMarginDeposit1IsModified();


  /** 
   * <em>unexec_cash_margin_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecCashMarginDeposit2();


  /** 
   * <em>unexec_cash_margin_deposit_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecCashMarginDeposit2IsNull();


  /** 
   * <em>unexec_cash_margin_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecCashMarginDeposit2IsSet();


  /** 
   * <em>unexec_cash_margin_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecCashMarginDeposit2IsModified();


  /** 
   * <em>unexec_cash_margin_deposit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecCashMarginDeposit3();


  /** 
   * <em>unexec_cash_margin_deposit_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecCashMarginDeposit3IsNull();


  /** 
   * <em>unexec_cash_margin_deposit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecCashMarginDeposit3IsSet();


  /** 
   * <em>unexec_cash_margin_deposit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecCashMarginDeposit3IsModified();


  /** 
   * <em>unexec_cash_margin_deposit_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecCashMarginDeposit4();


  /** 
   * <em>unexec_cash_margin_deposit_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecCashMarginDeposit4IsNull();


  /** 
   * <em>unexec_cash_margin_deposit_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecCashMarginDeposit4IsSet();


  /** 
   * <em>unexec_cash_margin_deposit_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecCashMarginDeposit4IsModified();


  /** 
   * <em>unexec_cash_margin_deposit_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getUnexecCashMarginDeposit5();


  /** 
   * <em>unexec_cash_margin_deposit_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnexecCashMarginDeposit5IsNull();


  /** 
   * <em>unexec_cash_margin_deposit_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecCashMarginDeposit5IsSet();


  /** 
   * <em>unexec_cash_margin_deposit_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnexecCashMarginDeposit5IsModified();


  /** 
   * <em>day_repay_margin_deposit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayMarginDeposit0();


  /** 
   * <em>day_repay_margin_deposit_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayRepayMarginDeposit0IsNull();


  /** 
   * <em>day_repay_margin_deposit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayMarginDeposit0IsSet();


  /** 
   * <em>day_repay_margin_deposit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayMarginDeposit0IsModified();


  /** 
   * <em>day_repay_margin_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayMarginDeposit1();


  /** 
   * <em>day_repay_margin_deposit_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayRepayMarginDeposit1IsNull();


  /** 
   * <em>day_repay_margin_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayMarginDeposit1IsSet();


  /** 
   * <em>day_repay_margin_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayMarginDeposit1IsModified();


  /** 
   * <em>day_repay_margin_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayMarginDeposit2();


  /** 
   * <em>day_repay_margin_deposit_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayRepayMarginDeposit2IsNull();


  /** 
   * <em>day_repay_margin_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayMarginDeposit2IsSet();


  /** 
   * <em>day_repay_margin_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayMarginDeposit2IsModified();


  /** 
   * <em>day_repay_margin_deposit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayMarginDeposit3();


  /** 
   * <em>day_repay_margin_deposit_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayRepayMarginDeposit3IsNull();


  /** 
   * <em>day_repay_margin_deposit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayMarginDeposit3IsSet();


  /** 
   * <em>day_repay_margin_deposit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayMarginDeposit3IsModified();


  /** 
   * <em>day_repay_margin_deposit_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayMarginDeposit4();


  /** 
   * <em>day_repay_margin_deposit_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayRepayMarginDeposit4IsNull();


  /** 
   * <em>day_repay_margin_deposit_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayMarginDeposit4IsSet();


  /** 
   * <em>day_repay_margin_deposit_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayMarginDeposit4IsModified();


  /** 
   * <em>day_repay_margin_deposit_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayMarginDeposit5();


  /** 
   * <em>day_repay_margin_deposit_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayRepayMarginDeposit5IsNull();


  /** 
   * <em>day_repay_margin_deposit_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayMarginDeposit5IsSet();


  /** 
   * <em>day_repay_margin_deposit_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayMarginDeposit5IsModified();


  /** 
   * <em>day_repay_cash_margin_deposit0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayCashMarginDeposit0();


  /** 
   * <em>day_repay_cash_margin_deposit0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayRepayCashMarginDeposit0IsNull();


  /** 
   * <em>day_repay_cash_margin_deposit0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayCashMarginDeposit0IsSet();


  /** 
   * <em>day_repay_cash_margin_deposit0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayCashMarginDeposit0IsModified();


  /** 
   * <em>day_repay_cash_margin_deposit1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayCashMarginDeposit1();


  /** 
   * <em>day_repay_cash_margin_deposit1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayRepayCashMarginDeposit1IsNull();


  /** 
   * <em>day_repay_cash_margin_deposit1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayCashMarginDeposit1IsSet();


  /** 
   * <em>day_repay_cash_margin_deposit1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayCashMarginDeposit1IsModified();


  /** 
   * <em>day_repay_cash_margin_deposit2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayCashMarginDeposit2();


  /** 
   * <em>day_repay_cash_margin_deposit2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayRepayCashMarginDeposit2IsNull();


  /** 
   * <em>day_repay_cash_margin_deposit2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayCashMarginDeposit2IsSet();


  /** 
   * <em>day_repay_cash_margin_deposit2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayCashMarginDeposit2IsModified();


  /** 
   * <em>day_repay_cash_margin_deposit3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayCashMarginDeposit3();


  /** 
   * <em>day_repay_cash_margin_deposit3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayRepayCashMarginDeposit3IsNull();


  /** 
   * <em>day_repay_cash_margin_deposit3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayCashMarginDeposit3IsSet();


  /** 
   * <em>day_repay_cash_margin_deposit3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayCashMarginDeposit3IsModified();


  /** 
   * <em>day_repay_cash_margin_deposit4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayCashMarginDeposit4();


  /** 
   * <em>day_repay_cash_margin_deposit4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayRepayCashMarginDeposit4IsNull();


  /** 
   * <em>day_repay_cash_margin_deposit4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayCashMarginDeposit4IsSet();


  /** 
   * <em>day_repay_cash_margin_deposit4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayCashMarginDeposit4IsModified();


  /** 
   * <em>day_repay_cash_margin_deposit5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getDayRepayCashMarginDeposit5();


  /** 
   * <em>day_repay_cash_margin_deposit5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDayRepayCashMarginDeposit5IsNull();


  /** 
   * <em>day_repay_cash_margin_deposit5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayCashMarginDeposit5IsSet();


  /** 
   * <em>day_repay_cash_margin_deposit5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDayRepayCashMarginDeposit5IsModified();


  /** 
   * <em>today_repay_contract_loss</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayRepayContractLoss();


  /** 
   * <em>today_repay_contract_loss</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayRepayContractLossIsNull();


  /** 
   * <em>today_repay_contract_loss</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayRepayContractLossIsSet();


  /** 
   * <em>today_repay_contract_loss</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayRepayContractLossIsModified();


  /** 
   * <em>today_repay_contract_profit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayRepayContractProfit();


  /** 
   * <em>today_repay_contract_profit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayRepayContractProfitIsNull();


  /** 
   * <em>today_repay_contract_profit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayRepayContractProfitIsSet();


  /** 
   * <em>today_repay_contract_profit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayRepayContractProfitIsModified();


  /** 
   * <em>today_repay_contract_pre_asset</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayRepayContractPreAsset();


  /** 
   * <em>today_repay_contract_pre_asset</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayRepayContractPreAssetIsNull();


  /** 
   * <em>today_repay_contract_pre_asset</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayRepayContractPreAssetIsSet();


  /** 
   * <em>today_repay_contract_pre_asset</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayRepayContractPreAssetIsModified();


  /** 
   * <em>contract_loss_designate_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractLossDesignate1();


  /** 
   * <em>contract_loss_designate_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractLossDesignate1IsNull();


  /** 
   * <em>contract_loss_designate_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractLossDesignate1IsSet();


  /** 
   * <em>contract_loss_designate_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractLossDesignate1IsModified();


  /** 
   * <em>contract_loss_designate_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractLossDesignate2();


  /** 
   * <em>contract_loss_designate_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractLossDesignate2IsNull();


  /** 
   * <em>contract_loss_designate_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractLossDesignate2IsSet();


  /** 
   * <em>contract_loss_designate_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractLossDesignate2IsModified();


  /** 
   * <em>contract_loss_designate_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractLossDesignate3();


  /** 
   * <em>contract_loss_designate_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractLossDesignate3IsNull();


  /** 
   * <em>contract_loss_designate_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractLossDesignate3IsSet();


  /** 
   * <em>contract_loss_designate_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractLossDesignate3IsModified();


  /** 
   * <em>contract_loss_designate_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractLossDesignate4();


  /** 
   * <em>contract_loss_designate_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractLossDesignate4IsNull();


  /** 
   * <em>contract_loss_designate_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractLossDesignate4IsSet();


  /** 
   * <em>contract_loss_designate_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractLossDesignate4IsModified();


  /** 
   * <em>contract_loss_designate_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractLossDesignate5();


  /** 
   * <em>contract_loss_designate_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractLossDesignate5IsNull();


  /** 
   * <em>contract_loss_designate_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractLossDesignate5IsSet();


  /** 
   * <em>contract_loss_designate_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractLossDesignate5IsModified();


  /** 
   * <em>contract_profit_designate_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractProfitDesignate1();


  /** 
   * <em>contract_profit_designate_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractProfitDesignate1IsNull();


  /** 
   * <em>contract_profit_designate_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractProfitDesignate1IsSet();


  /** 
   * <em>contract_profit_designate_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractProfitDesignate1IsModified();


  /** 
   * <em>contract_profit_designate_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractProfitDesignate2();


  /** 
   * <em>contract_profit_designate_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractProfitDesignate2IsNull();


  /** 
   * <em>contract_profit_designate_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractProfitDesignate2IsSet();


  /** 
   * <em>contract_profit_designate_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractProfitDesignate2IsModified();


  /** 
   * <em>contract_profit_designate_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractProfitDesignate3();


  /** 
   * <em>contract_profit_designate_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractProfitDesignate3IsNull();


  /** 
   * <em>contract_profit_designate_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractProfitDesignate3IsSet();


  /** 
   * <em>contract_profit_designate_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractProfitDesignate3IsModified();


  /** 
   * <em>contract_profit_designate_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractProfitDesignate4();


  /** 
   * <em>contract_profit_designate_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractProfitDesignate4IsNull();


  /** 
   * <em>contract_profit_designate_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractProfitDesignate4IsSet();


  /** 
   * <em>contract_profit_designate_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractProfitDesignate4IsModified();


  /** 
   * <em>contract_profit_designate_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractProfitDesignate5();


  /** 
   * <em>contract_profit_designate_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractProfitDesignate5IsNull();


  /** 
   * <em>contract_profit_designate_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractProfitDesignate5IsSet();


  /** 
   * <em>contract_profit_designate_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractProfitDesignate5IsModified();


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
   * <em>today_dep_fund_restraint_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayDepFundRestraint0();


  /** 
   * <em>today_dep_fund_restraint_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayDepFundRestraint0IsNull();


  /** 
   * <em>today_dep_fund_restraint_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayDepFundRestraint0IsSet();


  /** 
   * <em>today_dep_fund_restraint_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayDepFundRestraint0IsModified();


  /** 
   * <em>today_dep_fund_restraint_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayDepFundRestraint1();


  /** 
   * <em>today_dep_fund_restraint_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayDepFundRestraint1IsNull();


  /** 
   * <em>today_dep_fund_restraint_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayDepFundRestraint1IsSet();


  /** 
   * <em>today_dep_fund_restraint_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayDepFundRestraint1IsModified();


  /** 
   * <em>today_dep_fund_restraint_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayDepFundRestraint2();


  /** 
   * <em>today_dep_fund_restraint_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayDepFundRestraint2IsNull();


  /** 
   * <em>today_dep_fund_restraint_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayDepFundRestraint2IsSet();


  /** 
   * <em>today_dep_fund_restraint_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayDepFundRestraint2IsModified();


  /** 
   * <em>today_dep_fund_restraint_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayDepFundRestraint3();


  /** 
   * <em>today_dep_fund_restraint_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayDepFundRestraint3IsNull();


  /** 
   * <em>today_dep_fund_restraint_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayDepFundRestraint3IsSet();


  /** 
   * <em>today_dep_fund_restraint_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayDepFundRestraint3IsModified();


  /** 
   * <em>today_dep_fund_restraint_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayDepFundRestraint4();


  /** 
   * <em>today_dep_fund_restraint_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayDepFundRestraint4IsNull();


  /** 
   * <em>today_dep_fund_restraint_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayDepFundRestraint4IsSet();


  /** 
   * <em>today_dep_fund_restraint_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayDepFundRestraint4IsModified();


  /** 
   * <em>today_dep_fund_restraint_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayDepFundRestraint5();


  /** 
   * <em>today_dep_fund_restraint_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayDepFundRestraint5IsNull();


  /** 
   * <em>today_dep_fund_restraint_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayDepFundRestraint5IsSet();


  /** 
   * <em>today_dep_fund_restraint_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayDepFundRestraint5IsModified();


  /** 
   * <em>contract_asset_loss_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetLoss1();


  /** 
   * <em>contract_asset_loss_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetLoss1IsNull();


  /** 
   * <em>contract_asset_loss_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetLoss1IsSet();


  /** 
   * <em>contract_asset_loss_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetLoss1IsModified();


  /** 
   * <em>contract_asset_loss_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetLoss2();


  /** 
   * <em>contract_asset_loss_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetLoss2IsNull();


  /** 
   * <em>contract_asset_loss_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetLoss2IsSet();


  /** 
   * <em>contract_asset_loss_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetLoss2IsModified();


  /** 
   * <em>contract_asset_loss_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetLoss3();


  /** 
   * <em>contract_asset_loss_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetLoss3IsNull();


  /** 
   * <em>contract_asset_loss_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetLoss3IsSet();


  /** 
   * <em>contract_asset_loss_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetLoss3IsModified();


  /** 
   * <em>contract_asset_loss_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetLoss4();


  /** 
   * <em>contract_asset_loss_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetLoss4IsNull();


  /** 
   * <em>contract_asset_loss_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetLoss4IsSet();


  /** 
   * <em>contract_asset_loss_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetLoss4IsModified();


  /** 
   * <em>contract_asset_loss_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetLoss5();


  /** 
   * <em>contract_asset_loss_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetLoss5IsNull();


  /** 
   * <em>contract_asset_loss_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetLoss5IsSet();


  /** 
   * <em>contract_asset_loss_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetLoss5IsModified();


  /** 
   * <em>contract_asset_profit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetProfit1();


  /** 
   * <em>contract_asset_profit_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetProfit1IsNull();


  /** 
   * <em>contract_asset_profit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfit1IsSet();


  /** 
   * <em>contract_asset_profit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfit1IsModified();


  /** 
   * <em>contract_asset_profit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetProfit2();


  /** 
   * <em>contract_asset_profit_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetProfit2IsNull();


  /** 
   * <em>contract_asset_profit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfit2IsSet();


  /** 
   * <em>contract_asset_profit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfit2IsModified();


  /** 
   * <em>contract_asset_profit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetProfit3();


  /** 
   * <em>contract_asset_profit_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetProfit3IsNull();


  /** 
   * <em>contract_asset_profit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfit3IsSet();


  /** 
   * <em>contract_asset_profit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfit3IsModified();


  /** 
   * <em>contract_asset_profit_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetProfit4();


  /** 
   * <em>contract_asset_profit_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetProfit4IsNull();


  /** 
   * <em>contract_asset_profit_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfit4IsSet();


  /** 
   * <em>contract_asset_profit_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfit4IsModified();


  /** 
   * <em>contract_asset_profit_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractAssetProfit5();


  /** 
   * <em>contract_asset_profit_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractAssetProfit5IsNull();


  /** 
   * <em>contract_asset_profit_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfit5IsSet();


  /** 
   * <em>contract_asset_profit_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractAssetProfit5IsModified();


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


  /** 
   * <em>today_repay_contract_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getTodayRepayContractAmount();


  /** 
   * <em>today_repay_contract_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTodayRepayContractAmountIsNull();


  /** 
   * <em>today_repay_contract_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayRepayContractAmountIsSet();


  /** 
   * <em>today_repay_contract_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTodayRepayContractAmountIsModified();


  /** 
   * <em>substitute_asset_old_day_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSubstituteAssetOldDayValue();


  /** 
   * <em>substitute_asset_old_day_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSubstituteAssetOldDayValueIsNull();


  /** 
   * <em>substitute_asset_old_day_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteAssetOldDayValueIsSet();


  /** 
   * <em>substitute_asset_old_day_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubstituteAssetOldDayValueIsModified();


  /** 
   * <em>setup_fee_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSetupFee1();


  /** 
   * <em>setup_fee_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSetupFee1IsNull();


  /** 
   * <em>setup_fee_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSetupFee1IsSet();


  /** 
   * <em>setup_fee_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSetupFee1IsModified();


  /** 
   * <em>setup_fee_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSetupFee2();


  /** 
   * <em>setup_fee_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSetupFee2IsNull();


  /** 
   * <em>setup_fee_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSetupFee2IsSet();


  /** 
   * <em>setup_fee_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSetupFee2IsModified();


  /** 
   * <em>setup_fee_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSetupFee3();


  /** 
   * <em>setup_fee_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSetupFee3IsNull();


  /** 
   * <em>setup_fee_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSetupFee3IsSet();


  /** 
   * <em>setup_fee_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSetupFee3IsModified();


  /** 
   * <em>setup_fee_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSetupFee4();


  /** 
   * <em>setup_fee_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSetupFee4IsNull();


  /** 
   * <em>setup_fee_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSetupFee4IsSet();


  /** 
   * <em>setup_fee_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSetupFee4IsModified();


  /** 
   * <em>setup_fee_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSetupFee5();


  /** 
   * <em>setup_fee_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSetupFee5IsNull();


  /** 
   * <em>setup_fee_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSetupFee5IsSet();


  /** 
   * <em>setup_fee_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSetupFee5IsModified();


  /** 
   * <em>contract_interest_loss_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractInterestLoss1();


  /** 
   * <em>contract_interest_loss_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractInterestLoss1IsNull();


  /** 
   * <em>contract_interest_loss_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestLoss1IsSet();


  /** 
   * <em>contract_interest_loss_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestLoss1IsModified();


  /** 
   * <em>contract_interest_loss_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractInterestLoss2();


  /** 
   * <em>contract_interest_loss_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractInterestLoss2IsNull();


  /** 
   * <em>contract_interest_loss_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestLoss2IsSet();


  /** 
   * <em>contract_interest_loss_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestLoss2IsModified();


  /** 
   * <em>contract_interest_loss_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractInterestLoss3();


  /** 
   * <em>contract_interest_loss_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractInterestLoss3IsNull();


  /** 
   * <em>contract_interest_loss_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestLoss3IsSet();


  /** 
   * <em>contract_interest_loss_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestLoss3IsModified();


  /** 
   * <em>contract_interest_loss_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractInterestLoss4();


  /** 
   * <em>contract_interest_loss_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractInterestLoss4IsNull();


  /** 
   * <em>contract_interest_loss_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestLoss4IsSet();


  /** 
   * <em>contract_interest_loss_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestLoss4IsModified();


  /** 
   * <em>contract_interest_loss_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractInterestLoss5();


  /** 
   * <em>contract_interest_loss_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractInterestLoss5IsNull();


  /** 
   * <em>contract_interest_loss_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestLoss5IsSet();


  /** 
   * <em>contract_interest_loss_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestLoss5IsModified();


  /** 
   * <em>contract_interest_profit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractInterestProfit1();


  /** 
   * <em>contract_interest_profit_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractInterestProfit1IsNull();


  /** 
   * <em>contract_interest_profit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestProfit1IsSet();


  /** 
   * <em>contract_interest_profit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestProfit1IsModified();


  /** 
   * <em>contract_interest_profit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractInterestProfit2();


  /** 
   * <em>contract_interest_profit_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractInterestProfit2IsNull();


  /** 
   * <em>contract_interest_profit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestProfit2IsSet();


  /** 
   * <em>contract_interest_profit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestProfit2IsModified();


  /** 
   * <em>contract_interest_profit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractInterestProfit3();


  /** 
   * <em>contract_interest_profit_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractInterestProfit3IsNull();


  /** 
   * <em>contract_interest_profit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestProfit3IsSet();


  /** 
   * <em>contract_interest_profit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestProfit3IsModified();


  /** 
   * <em>contract_interest_profit_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractInterestProfit4();


  /** 
   * <em>contract_interest_profit_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractInterestProfit4IsNull();


  /** 
   * <em>contract_interest_profit_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestProfit4IsSet();


  /** 
   * <em>contract_interest_profit_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestProfit4IsModified();


  /** 
   * <em>contract_interest_profit_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractInterestProfit5();


  /** 
   * <em>contract_interest_profit_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractInterestProfit5IsNull();


  /** 
   * <em>contract_interest_profit_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestProfit5IsSet();


  /** 
   * <em>contract_interest_profit_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractInterestProfit5IsModified();


  /** 
   * <em>contract_other_cost_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractOtherCost1();


  /** 
   * <em>contract_other_cost_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractOtherCost1IsNull();


  /** 
   * <em>contract_other_cost_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractOtherCost1IsSet();


  /** 
   * <em>contract_other_cost_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractOtherCost1IsModified();


  /** 
   * <em>contract_other_cost_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractOtherCost2();


  /** 
   * <em>contract_other_cost_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractOtherCost2IsNull();


  /** 
   * <em>contract_other_cost_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractOtherCost2IsSet();


  /** 
   * <em>contract_other_cost_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractOtherCost2IsModified();


  /** 
   * <em>contract_other_cost_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractOtherCost3();


  /** 
   * <em>contract_other_cost_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractOtherCost3IsNull();


  /** 
   * <em>contract_other_cost_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractOtherCost3IsSet();


  /** 
   * <em>contract_other_cost_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractOtherCost3IsModified();


  /** 
   * <em>contract_other_cost_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractOtherCost4();


  /** 
   * <em>contract_other_cost_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractOtherCost4IsNull();


  /** 
   * <em>contract_other_cost_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractOtherCost4IsSet();


  /** 
   * <em>contract_other_cost_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractOtherCost4IsModified();


  /** 
   * <em>contract_other_cost_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getContractOtherCost5();


  /** 
   * <em>contract_other_cost_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContractOtherCost5IsNull();


  /** 
   * <em>contract_other_cost_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractOtherCost5IsSet();


  /** 
   * <em>contract_other_cost_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContractOtherCost5IsModified();


}
@
