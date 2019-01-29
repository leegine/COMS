head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.30.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCalcResultMarginDetailParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * tp_calc_result_margin_detailテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link TpCalcResultMarginDetailRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link TpCalcResultMarginDetailParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link TpCalcResultMarginDetailParams}が{@@link TpCalcResultMarginDetailRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpCalcResultMarginDetailPK 
 * @@see TpCalcResultMarginDetailRow 
 */
public class TpCalcResultMarginDetailParams extends Params implements TpCalcResultMarginDetailRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "tp_calc_result_margin_detail";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = TpCalcResultMarginDetailRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return TpCalcResultMarginDetailRow.TYPE;
  }


  /** 
   * <em>calc_result_margin_id</em>カラムの値 
   */
  public  long  calc_result_margin_id;

  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>equity_asset_delivered</em>カラムの値 
   */
  public  Double  equity_asset_delivered;

  /** 
   * <em>equity_asset_executed</em>カラムの値 
   */
  public  Double  equity_asset_executed;

  /** 
   * <em>mini_stock_asset_delivered</em>カラムの値 
   */
  public  Double  mini_stock_asset_delivered;

  /** 
   * <em>mini_stock_asset_executed</em>カラムの値 
   */
  public  Double  mini_stock_asset_executed;

  /** 
   * <em>ruito_asset_delivered</em>カラムの値 
   */
  public  Double  ruito_asset_delivered;

  /** 
   * <em>ruito_asset_executed</em>カラムの値 
   */
  public  Double  ruito_asset_executed;

  /** 
   * <em>mutual_fund_asset_delivered</em>カラムの値 
   */
  public  Double  mutual_fund_asset_delivered;

  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値 
   */
  public  Double  mutual_fund_asset_executed;

  /** 
   * <em>bond_asset_delivered</em>カラムの値 
   */
  public  Double  bond_asset_delivered;

  /** 
   * <em>bond_asset_executed</em>カラムの値 
   */
  public  Double  bond_asset_executed;

  /** 
   * <em>unexec_substi_security_asset_0</em>カラムの値 
   */
  public  Double  unexec_substi_security_asset_0;

  /** 
   * <em>unexec_substi_security_asset_1</em>カラムの値 
   */
  public  Double  unexec_substi_security_asset_1;

  /** 
   * <em>unexec_substi_security_asset_2</em>カラムの値 
   */
  public  Double  unexec_substi_security_asset_2;

  /** 
   * <em>unexec_substi_security_asset_3</em>カラムの値 
   */
  public  Double  unexec_substi_security_asset_3;

  /** 
   * <em>unexec_substi_security_asset_4</em>カラムの値 
   */
  public  Double  unexec_substi_security_asset_4;

  /** 
   * <em>unexec_substi_security_asset_5</em>カラムの値 
   */
  public  Double  unexec_substi_security_asset_5;

  /** 
   * <em>contract_asset_loss</em>カラムの値 
   */
  public  Double  contract_asset_loss;

  /** 
   * <em>contract_asset_profit</em>カラムの値 
   */
  public  Double  contract_asset_profit;

  /** 
   * <em>setup_fee</em>カラムの値 
   */
  public  Double  setup_fee;

  /** 
   * <em>contract_interest_loss</em>カラムの値 
   */
  public  Double  contract_interest_loss;

  /** 
   * <em>contract_interest_profit</em>カラムの値 
   */
  public  Double  contract_interest_profit;

  /** 
   * <em>contract_other_cost</em>カラムの値 
   */
  public  Double  contract_other_cost;

  /** 
   * <em>unexec_contract_amount_0</em>カラムの値 
   */
  public  Double  unexec_contract_amount_0;

  /** 
   * <em>unexec_contract_amount_1</em>カラムの値 
   */
  public  Double  unexec_contract_amount_1;

  /** 
   * <em>unexec_contract_amount_2</em>カラムの値 
   */
  public  Double  unexec_contract_amount_2;

  /** 
   * <em>unexec_contract_amount_3</em>カラムの値 
   */
  public  Double  unexec_contract_amount_3;

  /** 
   * <em>unexec_contract_amount_4</em>カラムの値 
   */
  public  Double  unexec_contract_amount_4;

  /** 
   * <em>unexec_contract_amount_5</em>カラムの値 
   */
  public  Double  unexec_contract_amount_5;

  /** 
   * <em>unexec_margin_deposit_0</em>カラムの値 
   */
  public  Double  unexec_margin_deposit_0;

  /** 
   * <em>unexec_margin_deposit_1</em>カラムの値 
   */
  public  Double  unexec_margin_deposit_1;

  /** 
   * <em>unexec_margin_deposit_2</em>カラムの値 
   */
  public  Double  unexec_margin_deposit_2;

  /** 
   * <em>unexec_margin_deposit_3</em>カラムの値 
   */
  public  Double  unexec_margin_deposit_3;

  /** 
   * <em>unexec_margin_deposit_4</em>カラムの値 
   */
  public  Double  unexec_margin_deposit_4;

  /** 
   * <em>unexec_margin_deposit_5</em>カラムの値 
   */
  public  Double  unexec_margin_deposit_5;

  /** 
   * <em>unexec_cash_margin_deposit_0</em>カラムの値 
   */
  public  Double  unexec_cash_margin_deposit_0;

  /** 
   * <em>unexec_cash_margin_deposit_1</em>カラムの値 
   */
  public  Double  unexec_cash_margin_deposit_1;

  /** 
   * <em>unexec_cash_margin_deposit_2</em>カラムの値 
   */
  public  Double  unexec_cash_margin_deposit_2;

  /** 
   * <em>unexec_cash_margin_deposit_3</em>カラムの値 
   */
  public  Double  unexec_cash_margin_deposit_3;

  /** 
   * <em>unexec_cash_margin_deposit_4</em>カラムの値 
   */
  public  Double  unexec_cash_margin_deposit_4;

  /** 
   * <em>unexec_cash_margin_deposit_5</em>カラムの値 
   */
  public  Double  unexec_cash_margin_deposit_5;

  /** 
   * <em>day_repay_margin_deposit_0</em>カラムの値 
   */
  public  Double  day_repay_margin_deposit_0;

  /** 
   * <em>day_repay_margin_deposit_1</em>カラムの値 
   */
  public  Double  day_repay_margin_deposit_1;

  /** 
   * <em>day_repay_margin_deposit_2</em>カラムの値 
   */
  public  Double  day_repay_margin_deposit_2;

  /** 
   * <em>day_repay_margin_deposit_3</em>カラムの値 
   */
  public  Double  day_repay_margin_deposit_3;

  /** 
   * <em>day_repay_margin_deposit_4</em>カラムの値 
   */
  public  Double  day_repay_margin_deposit_4;

  /** 
   * <em>day_repay_margin_deposit_5</em>カラムの値 
   */
  public  Double  day_repay_margin_deposit_5;

  /** 
   * <em>day_repay_cash_margin_deposit0</em>カラムの値 
   */
  public  Double  day_repay_cash_margin_deposit0;

  /** 
   * <em>day_repay_cash_margin_deposit1</em>カラムの値 
   */
  public  Double  day_repay_cash_margin_deposit1;

  /** 
   * <em>day_repay_cash_margin_deposit2</em>カラムの値 
   */
  public  Double  day_repay_cash_margin_deposit2;

  /** 
   * <em>day_repay_cash_margin_deposit3</em>カラムの値 
   */
  public  Double  day_repay_cash_margin_deposit3;

  /** 
   * <em>day_repay_cash_margin_deposit4</em>カラムの値 
   */
  public  Double  day_repay_cash_margin_deposit4;

  /** 
   * <em>day_repay_cash_margin_deposit5</em>カラムの値 
   */
  public  Double  day_repay_cash_margin_deposit5;

  /** 
   * <em>today_repay_contract_loss</em>カラムの値 
   */
  public  Double  today_repay_contract_loss;

  /** 
   * <em>today_repay_contract_profit</em>カラムの値 
   */
  public  Double  today_repay_contract_profit;

  /** 
   * <em>today_repay_contract_pre_asset</em>カラムの値 
   */
  public  Double  today_repay_contract_pre_asset;

  /** 
   * <em>contract_loss_designate_1</em>カラムの値 
   */
  public  Double  contract_loss_designate_1;

  /** 
   * <em>contract_loss_designate_2</em>カラムの値 
   */
  public  Double  contract_loss_designate_2;

  /** 
   * <em>contract_loss_designate_3</em>カラムの値 
   */
  public  Double  contract_loss_designate_3;

  /** 
   * <em>contract_loss_designate_4</em>カラムの値 
   */
  public  Double  contract_loss_designate_4;

  /** 
   * <em>contract_loss_designate_5</em>カラムの値 
   */
  public  Double  contract_loss_designate_5;

  /** 
   * <em>contract_profit_designate_1</em>カラムの値 
   */
  public  Double  contract_profit_designate_1;

  /** 
   * <em>contract_profit_designate_2</em>カラムの値 
   */
  public  Double  contract_profit_designate_2;

  /** 
   * <em>contract_profit_designate_3</em>カラムの値 
   */
  public  Double  contract_profit_designate_3;

  /** 
   * <em>contract_profit_designate_4</em>カラムの値 
   */
  public  Double  contract_profit_designate_4;

  /** 
   * <em>contract_profit_designate_5</em>カラムの値 
   */
  public  Double  contract_profit_designate_5;

  /** 
   * <em>payment_amount_designate_0</em>カラムの値 
   */
  public  Double  payment_amount_designate_0;

  /** 
   * <em>payment_amount_designate_1</em>カラムの値 
   */
  public  Double  payment_amount_designate_1;

  /** 
   * <em>payment_amount_designate_2</em>カラムの値 
   */
  public  Double  payment_amount_designate_2;

  /** 
   * <em>payment_amount_designate_3</em>カラムの値 
   */
  public  Double  payment_amount_designate_3;

  /** 
   * <em>payment_amount_designate_4</em>カラムの値 
   */
  public  Double  payment_amount_designate_4;

  /** 
   * <em>payment_amount_designate_5</em>カラムの値 
   */
  public  Double  payment_amount_designate_5;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>today_dep_fund_restraint_0</em>カラムの値 
   */
  public  Double  today_dep_fund_restraint_0;

  /** 
   * <em>today_dep_fund_restraint_1</em>カラムの値 
   */
  public  Double  today_dep_fund_restraint_1;

  /** 
   * <em>today_dep_fund_restraint_2</em>カラムの値 
   */
  public  Double  today_dep_fund_restraint_2;

  /** 
   * <em>today_dep_fund_restraint_3</em>カラムの値 
   */
  public  Double  today_dep_fund_restraint_3;

  /** 
   * <em>today_dep_fund_restraint_4</em>カラムの値 
   */
  public  Double  today_dep_fund_restraint_4;

  /** 
   * <em>today_dep_fund_restraint_5</em>カラムの値 
   */
  public  Double  today_dep_fund_restraint_5;

  /** 
   * <em>contract_asset_loss_1</em>カラムの値 
   */
  public  Double  contract_asset_loss_1;

  /** 
   * <em>contract_asset_loss_2</em>カラムの値 
   */
  public  Double  contract_asset_loss_2;

  /** 
   * <em>contract_asset_loss_3</em>カラムの値 
   */
  public  Double  contract_asset_loss_3;

  /** 
   * <em>contract_asset_loss_4</em>カラムの値 
   */
  public  Double  contract_asset_loss_4;

  /** 
   * <em>contract_asset_loss_5</em>カラムの値 
   */
  public  Double  contract_asset_loss_5;

  /** 
   * <em>contract_asset_profit_1</em>カラムの値 
   */
  public  Double  contract_asset_profit_1;

  /** 
   * <em>contract_asset_profit_2</em>カラムの値 
   */
  public  Double  contract_asset_profit_2;

  /** 
   * <em>contract_asset_profit_3</em>カラムの値 
   */
  public  Double  contract_asset_profit_3;

  /** 
   * <em>contract_asset_profit_4</em>カラムの値 
   */
  public  Double  contract_asset_profit_4;

  /** 
   * <em>contract_asset_profit_5</em>カラムの値 
   */
  public  Double  contract_asset_profit_5;

  /** 
   * <em>foreign_equity_asset_delivered</em>カラムの値 
   */
  public  Double  foreign_equity_asset_delivered;

  /** 
   * <em>foreign_equity_asset_executed</em>カラムの値 
   */
  public  Double  foreign_equity_asset_executed;

  /** 
   * <em>today_repay_contract_amount</em>カラムの値 
   */
  public  Double  today_repay_contract_amount;

  /** 
   * <em>substitute_asset_old_day_value</em>カラムの値 
   */
  public  Double  substitute_asset_old_day_value;

  /** 
   * <em>setup_fee_1</em>カラムの値 
   */
  public  Double  setup_fee_1;

  /** 
   * <em>setup_fee_2</em>カラムの値 
   */
  public  Double  setup_fee_2;

  /** 
   * <em>setup_fee_3</em>カラムの値 
   */
  public  Double  setup_fee_3;

  /** 
   * <em>setup_fee_4</em>カラムの値 
   */
  public  Double  setup_fee_4;

  /** 
   * <em>setup_fee_5</em>カラムの値 
   */
  public  Double  setup_fee_5;

  /** 
   * <em>contract_interest_loss_1</em>カラムの値 
   */
  public  Double  contract_interest_loss_1;

  /** 
   * <em>contract_interest_loss_2</em>カラムの値 
   */
  public  Double  contract_interest_loss_2;

  /** 
   * <em>contract_interest_loss_3</em>カラムの値 
   */
  public  Double  contract_interest_loss_3;

  /** 
   * <em>contract_interest_loss_4</em>カラムの値 
   */
  public  Double  contract_interest_loss_4;

  /** 
   * <em>contract_interest_loss_5</em>カラムの値 
   */
  public  Double  contract_interest_loss_5;

  /** 
   * <em>contract_interest_profit_1</em>カラムの値 
   */
  public  Double  contract_interest_profit_1;

  /** 
   * <em>contract_interest_profit_2</em>カラムの値 
   */
  public  Double  contract_interest_profit_2;

  /** 
   * <em>contract_interest_profit_3</em>カラムの値 
   */
  public  Double  contract_interest_profit_3;

  /** 
   * <em>contract_interest_profit_4</em>カラムの値 
   */
  public  Double  contract_interest_profit_4;

  /** 
   * <em>contract_interest_profit_5</em>カラムの値 
   */
  public  Double  contract_interest_profit_5;

  /** 
   * <em>contract_other_cost_1</em>カラムの値 
   */
  public  Double  contract_other_cost_1;

  /** 
   * <em>contract_other_cost_2</em>カラムの値 
   */
  public  Double  contract_other_cost_2;

  /** 
   * <em>contract_other_cost_3</em>カラムの値 
   */
  public  Double  contract_other_cost_3;

  /** 
   * <em>contract_other_cost_4</em>カラムの値 
   */
  public  Double  contract_other_cost_4;

  /** 
   * <em>contract_other_cost_5</em>カラムの値 
   */
  public  Double  contract_other_cost_5;

  private boolean calc_result_margin_id_is_set = false;
  private boolean calc_result_margin_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean equity_asset_delivered_is_set = false;
  private boolean equity_asset_delivered_is_modified = false;
  private boolean equity_asset_executed_is_set = false;
  private boolean equity_asset_executed_is_modified = false;
  private boolean mini_stock_asset_delivered_is_set = false;
  private boolean mini_stock_asset_delivered_is_modified = false;
  private boolean mini_stock_asset_executed_is_set = false;
  private boolean mini_stock_asset_executed_is_modified = false;
  private boolean ruito_asset_delivered_is_set = false;
  private boolean ruito_asset_delivered_is_modified = false;
  private boolean ruito_asset_executed_is_set = false;
  private boolean ruito_asset_executed_is_modified = false;
  private boolean mutual_fund_asset_delivered_is_set = false;
  private boolean mutual_fund_asset_delivered_is_modified = false;
  private boolean mutual_fund_asset_executed_is_set = false;
  private boolean mutual_fund_asset_executed_is_modified = false;
  private boolean bond_asset_delivered_is_set = false;
  private boolean bond_asset_delivered_is_modified = false;
  private boolean bond_asset_executed_is_set = false;
  private boolean bond_asset_executed_is_modified = false;
  private boolean unexec_substi_security_asset_0_is_set = false;
  private boolean unexec_substi_security_asset_0_is_modified = false;
  private boolean unexec_substi_security_asset_1_is_set = false;
  private boolean unexec_substi_security_asset_1_is_modified = false;
  private boolean unexec_substi_security_asset_2_is_set = false;
  private boolean unexec_substi_security_asset_2_is_modified = false;
  private boolean unexec_substi_security_asset_3_is_set = false;
  private boolean unexec_substi_security_asset_3_is_modified = false;
  private boolean unexec_substi_security_asset_4_is_set = false;
  private boolean unexec_substi_security_asset_4_is_modified = false;
  private boolean unexec_substi_security_asset_5_is_set = false;
  private boolean unexec_substi_security_asset_5_is_modified = false;
  private boolean contract_asset_loss_is_set = false;
  private boolean contract_asset_loss_is_modified = false;
  private boolean contract_asset_profit_is_set = false;
  private boolean contract_asset_profit_is_modified = false;
  private boolean setup_fee_is_set = false;
  private boolean setup_fee_is_modified = false;
  private boolean contract_interest_loss_is_set = false;
  private boolean contract_interest_loss_is_modified = false;
  private boolean contract_interest_profit_is_set = false;
  private boolean contract_interest_profit_is_modified = false;
  private boolean contract_other_cost_is_set = false;
  private boolean contract_other_cost_is_modified = false;
  private boolean unexec_contract_amount_0_is_set = false;
  private boolean unexec_contract_amount_0_is_modified = false;
  private boolean unexec_contract_amount_1_is_set = false;
  private boolean unexec_contract_amount_1_is_modified = false;
  private boolean unexec_contract_amount_2_is_set = false;
  private boolean unexec_contract_amount_2_is_modified = false;
  private boolean unexec_contract_amount_3_is_set = false;
  private boolean unexec_contract_amount_3_is_modified = false;
  private boolean unexec_contract_amount_4_is_set = false;
  private boolean unexec_contract_amount_4_is_modified = false;
  private boolean unexec_contract_amount_5_is_set = false;
  private boolean unexec_contract_amount_5_is_modified = false;
  private boolean unexec_margin_deposit_0_is_set = false;
  private boolean unexec_margin_deposit_0_is_modified = false;
  private boolean unexec_margin_deposit_1_is_set = false;
  private boolean unexec_margin_deposit_1_is_modified = false;
  private boolean unexec_margin_deposit_2_is_set = false;
  private boolean unexec_margin_deposit_2_is_modified = false;
  private boolean unexec_margin_deposit_3_is_set = false;
  private boolean unexec_margin_deposit_3_is_modified = false;
  private boolean unexec_margin_deposit_4_is_set = false;
  private boolean unexec_margin_deposit_4_is_modified = false;
  private boolean unexec_margin_deposit_5_is_set = false;
  private boolean unexec_margin_deposit_5_is_modified = false;
  private boolean unexec_cash_margin_deposit_0_is_set = false;
  private boolean unexec_cash_margin_deposit_0_is_modified = false;
  private boolean unexec_cash_margin_deposit_1_is_set = false;
  private boolean unexec_cash_margin_deposit_1_is_modified = false;
  private boolean unexec_cash_margin_deposit_2_is_set = false;
  private boolean unexec_cash_margin_deposit_2_is_modified = false;
  private boolean unexec_cash_margin_deposit_3_is_set = false;
  private boolean unexec_cash_margin_deposit_3_is_modified = false;
  private boolean unexec_cash_margin_deposit_4_is_set = false;
  private boolean unexec_cash_margin_deposit_4_is_modified = false;
  private boolean unexec_cash_margin_deposit_5_is_set = false;
  private boolean unexec_cash_margin_deposit_5_is_modified = false;
  private boolean day_repay_margin_deposit_0_is_set = false;
  private boolean day_repay_margin_deposit_0_is_modified = false;
  private boolean day_repay_margin_deposit_1_is_set = false;
  private boolean day_repay_margin_deposit_1_is_modified = false;
  private boolean day_repay_margin_deposit_2_is_set = false;
  private boolean day_repay_margin_deposit_2_is_modified = false;
  private boolean day_repay_margin_deposit_3_is_set = false;
  private boolean day_repay_margin_deposit_3_is_modified = false;
  private boolean day_repay_margin_deposit_4_is_set = false;
  private boolean day_repay_margin_deposit_4_is_modified = false;
  private boolean day_repay_margin_deposit_5_is_set = false;
  private boolean day_repay_margin_deposit_5_is_modified = false;
  private boolean day_repay_cash_margin_deposit0_is_set = false;
  private boolean day_repay_cash_margin_deposit0_is_modified = false;
  private boolean day_repay_cash_margin_deposit1_is_set = false;
  private boolean day_repay_cash_margin_deposit1_is_modified = false;
  private boolean day_repay_cash_margin_deposit2_is_set = false;
  private boolean day_repay_cash_margin_deposit2_is_modified = false;
  private boolean day_repay_cash_margin_deposit3_is_set = false;
  private boolean day_repay_cash_margin_deposit3_is_modified = false;
  private boolean day_repay_cash_margin_deposit4_is_set = false;
  private boolean day_repay_cash_margin_deposit4_is_modified = false;
  private boolean day_repay_cash_margin_deposit5_is_set = false;
  private boolean day_repay_cash_margin_deposit5_is_modified = false;
  private boolean today_repay_contract_loss_is_set = false;
  private boolean today_repay_contract_loss_is_modified = false;
  private boolean today_repay_contract_profit_is_set = false;
  private boolean today_repay_contract_profit_is_modified = false;
  private boolean today_repay_contract_pre_asset_is_set = false;
  private boolean today_repay_contract_pre_asset_is_modified = false;
  private boolean contract_loss_designate_1_is_set = false;
  private boolean contract_loss_designate_1_is_modified = false;
  private boolean contract_loss_designate_2_is_set = false;
  private boolean contract_loss_designate_2_is_modified = false;
  private boolean contract_loss_designate_3_is_set = false;
  private boolean contract_loss_designate_3_is_modified = false;
  private boolean contract_loss_designate_4_is_set = false;
  private boolean contract_loss_designate_4_is_modified = false;
  private boolean contract_loss_designate_5_is_set = false;
  private boolean contract_loss_designate_5_is_modified = false;
  private boolean contract_profit_designate_1_is_set = false;
  private boolean contract_profit_designate_1_is_modified = false;
  private boolean contract_profit_designate_2_is_set = false;
  private boolean contract_profit_designate_2_is_modified = false;
  private boolean contract_profit_designate_3_is_set = false;
  private boolean contract_profit_designate_3_is_modified = false;
  private boolean contract_profit_designate_4_is_set = false;
  private boolean contract_profit_designate_4_is_modified = false;
  private boolean contract_profit_designate_5_is_set = false;
  private boolean contract_profit_designate_5_is_modified = false;
  private boolean payment_amount_designate_0_is_set = false;
  private boolean payment_amount_designate_0_is_modified = false;
  private boolean payment_amount_designate_1_is_set = false;
  private boolean payment_amount_designate_1_is_modified = false;
  private boolean payment_amount_designate_2_is_set = false;
  private boolean payment_amount_designate_2_is_modified = false;
  private boolean payment_amount_designate_3_is_set = false;
  private boolean payment_amount_designate_3_is_modified = false;
  private boolean payment_amount_designate_4_is_set = false;
  private boolean payment_amount_designate_4_is_modified = false;
  private boolean payment_amount_designate_5_is_set = false;
  private boolean payment_amount_designate_5_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean today_dep_fund_restraint_0_is_set = false;
  private boolean today_dep_fund_restraint_0_is_modified = false;
  private boolean today_dep_fund_restraint_1_is_set = false;
  private boolean today_dep_fund_restraint_1_is_modified = false;
  private boolean today_dep_fund_restraint_2_is_set = false;
  private boolean today_dep_fund_restraint_2_is_modified = false;
  private boolean today_dep_fund_restraint_3_is_set = false;
  private boolean today_dep_fund_restraint_3_is_modified = false;
  private boolean today_dep_fund_restraint_4_is_set = false;
  private boolean today_dep_fund_restraint_4_is_modified = false;
  private boolean today_dep_fund_restraint_5_is_set = false;
  private boolean today_dep_fund_restraint_5_is_modified = false;
  private boolean contract_asset_loss_1_is_set = false;
  private boolean contract_asset_loss_1_is_modified = false;
  private boolean contract_asset_loss_2_is_set = false;
  private boolean contract_asset_loss_2_is_modified = false;
  private boolean contract_asset_loss_3_is_set = false;
  private boolean contract_asset_loss_3_is_modified = false;
  private boolean contract_asset_loss_4_is_set = false;
  private boolean contract_asset_loss_4_is_modified = false;
  private boolean contract_asset_loss_5_is_set = false;
  private boolean contract_asset_loss_5_is_modified = false;
  private boolean contract_asset_profit_1_is_set = false;
  private boolean contract_asset_profit_1_is_modified = false;
  private boolean contract_asset_profit_2_is_set = false;
  private boolean contract_asset_profit_2_is_modified = false;
  private boolean contract_asset_profit_3_is_set = false;
  private boolean contract_asset_profit_3_is_modified = false;
  private boolean contract_asset_profit_4_is_set = false;
  private boolean contract_asset_profit_4_is_modified = false;
  private boolean contract_asset_profit_5_is_set = false;
  private boolean contract_asset_profit_5_is_modified = false;
  private boolean foreign_equity_asset_delivered_is_set = false;
  private boolean foreign_equity_asset_delivered_is_modified = false;
  private boolean foreign_equity_asset_executed_is_set = false;
  private boolean foreign_equity_asset_executed_is_modified = false;
  private boolean today_repay_contract_amount_is_set = false;
  private boolean today_repay_contract_amount_is_modified = false;
  private boolean substitute_asset_old_day_value_is_set = false;
  private boolean substitute_asset_old_day_value_is_modified = false;
  private boolean setup_fee_1_is_set = false;
  private boolean setup_fee_1_is_modified = false;
  private boolean setup_fee_2_is_set = false;
  private boolean setup_fee_2_is_modified = false;
  private boolean setup_fee_3_is_set = false;
  private boolean setup_fee_3_is_modified = false;
  private boolean setup_fee_4_is_set = false;
  private boolean setup_fee_4_is_modified = false;
  private boolean setup_fee_5_is_set = false;
  private boolean setup_fee_5_is_modified = false;
  private boolean contract_interest_loss_1_is_set = false;
  private boolean contract_interest_loss_1_is_modified = false;
  private boolean contract_interest_loss_2_is_set = false;
  private boolean contract_interest_loss_2_is_modified = false;
  private boolean contract_interest_loss_3_is_set = false;
  private boolean contract_interest_loss_3_is_modified = false;
  private boolean contract_interest_loss_4_is_set = false;
  private boolean contract_interest_loss_4_is_modified = false;
  private boolean contract_interest_loss_5_is_set = false;
  private boolean contract_interest_loss_5_is_modified = false;
  private boolean contract_interest_profit_1_is_set = false;
  private boolean contract_interest_profit_1_is_modified = false;
  private boolean contract_interest_profit_2_is_set = false;
  private boolean contract_interest_profit_2_is_modified = false;
  private boolean contract_interest_profit_3_is_set = false;
  private boolean contract_interest_profit_3_is_modified = false;
  private boolean contract_interest_profit_4_is_set = false;
  private boolean contract_interest_profit_4_is_modified = false;
  private boolean contract_interest_profit_5_is_set = false;
  private boolean contract_interest_profit_5_is_modified = false;
  private boolean contract_other_cost_1_is_set = false;
  private boolean contract_other_cost_1_is_modified = false;
  private boolean contract_other_cost_2_is_set = false;
  private boolean contract_other_cost_2_is_modified = false;
  private boolean contract_other_cost_3_is_set = false;
  private boolean contract_other_cost_3_is_modified = false;
  private boolean contract_other_cost_4_is_set = false;
  private boolean contract_other_cost_4_is_modified = false;
  private boolean contract_other_cost_5_is_set = false;
  private boolean contract_other_cost_5_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "calc_result_margin_id=" + calc_result_margin_id
      + "," + "account_id=" +account_id
      + "," + "equity_asset_delivered=" +equity_asset_delivered
      + "," + "equity_asset_executed=" +equity_asset_executed
      + "," + "mini_stock_asset_delivered=" +mini_stock_asset_delivered
      + "," + "mini_stock_asset_executed=" +mini_stock_asset_executed
      + "," + "ruito_asset_delivered=" +ruito_asset_delivered
      + "," + "ruito_asset_executed=" +ruito_asset_executed
      + "," + "mutual_fund_asset_delivered=" +mutual_fund_asset_delivered
      + "," + "mutual_fund_asset_executed=" +mutual_fund_asset_executed
      + "," + "bond_asset_delivered=" +bond_asset_delivered
      + "," + "bond_asset_executed=" +bond_asset_executed
      + "," + "unexec_substi_security_asset_0=" +unexec_substi_security_asset_0
      + "," + "unexec_substi_security_asset_1=" +unexec_substi_security_asset_1
      + "," + "unexec_substi_security_asset_2=" +unexec_substi_security_asset_2
      + "," + "unexec_substi_security_asset_3=" +unexec_substi_security_asset_3
      + "," + "unexec_substi_security_asset_4=" +unexec_substi_security_asset_4
      + "," + "unexec_substi_security_asset_5=" +unexec_substi_security_asset_5
      + "," + "contract_asset_loss=" +contract_asset_loss
      + "," + "contract_asset_profit=" +contract_asset_profit
      + "," + "setup_fee=" +setup_fee
      + "," + "contract_interest_loss=" +contract_interest_loss
      + "," + "contract_interest_profit=" +contract_interest_profit
      + "," + "contract_other_cost=" +contract_other_cost
      + "," + "unexec_contract_amount_0=" +unexec_contract_amount_0
      + "," + "unexec_contract_amount_1=" +unexec_contract_amount_1
      + "," + "unexec_contract_amount_2=" +unexec_contract_amount_2
      + "," + "unexec_contract_amount_3=" +unexec_contract_amount_3
      + "," + "unexec_contract_amount_4=" +unexec_contract_amount_4
      + "," + "unexec_contract_amount_5=" +unexec_contract_amount_5
      + "," + "unexec_margin_deposit_0=" +unexec_margin_deposit_0
      + "," + "unexec_margin_deposit_1=" +unexec_margin_deposit_1
      + "," + "unexec_margin_deposit_2=" +unexec_margin_deposit_2
      + "," + "unexec_margin_deposit_3=" +unexec_margin_deposit_3
      + "," + "unexec_margin_deposit_4=" +unexec_margin_deposit_4
      + "," + "unexec_margin_deposit_5=" +unexec_margin_deposit_5
      + "," + "unexec_cash_margin_deposit_0=" +unexec_cash_margin_deposit_0
      + "," + "unexec_cash_margin_deposit_1=" +unexec_cash_margin_deposit_1
      + "," + "unexec_cash_margin_deposit_2=" +unexec_cash_margin_deposit_2
      + "," + "unexec_cash_margin_deposit_3=" +unexec_cash_margin_deposit_3
      + "," + "unexec_cash_margin_deposit_4=" +unexec_cash_margin_deposit_4
      + "," + "unexec_cash_margin_deposit_5=" +unexec_cash_margin_deposit_5
      + "," + "day_repay_margin_deposit_0=" +day_repay_margin_deposit_0
      + "," + "day_repay_margin_deposit_1=" +day_repay_margin_deposit_1
      + "," + "day_repay_margin_deposit_2=" +day_repay_margin_deposit_2
      + "," + "day_repay_margin_deposit_3=" +day_repay_margin_deposit_3
      + "," + "day_repay_margin_deposit_4=" +day_repay_margin_deposit_4
      + "," + "day_repay_margin_deposit_5=" +day_repay_margin_deposit_5
      + "," + "day_repay_cash_margin_deposit0=" +day_repay_cash_margin_deposit0
      + "," + "day_repay_cash_margin_deposit1=" +day_repay_cash_margin_deposit1
      + "," + "day_repay_cash_margin_deposit2=" +day_repay_cash_margin_deposit2
      + "," + "day_repay_cash_margin_deposit3=" +day_repay_cash_margin_deposit3
      + "," + "day_repay_cash_margin_deposit4=" +day_repay_cash_margin_deposit4
      + "," + "day_repay_cash_margin_deposit5=" +day_repay_cash_margin_deposit5
      + "," + "today_repay_contract_loss=" +today_repay_contract_loss
      + "," + "today_repay_contract_profit=" +today_repay_contract_profit
      + "," + "today_repay_contract_pre_asset=" +today_repay_contract_pre_asset
      + "," + "contract_loss_designate_1=" +contract_loss_designate_1
      + "," + "contract_loss_designate_2=" +contract_loss_designate_2
      + "," + "contract_loss_designate_3=" +contract_loss_designate_3
      + "," + "contract_loss_designate_4=" +contract_loss_designate_4
      + "," + "contract_loss_designate_5=" +contract_loss_designate_5
      + "," + "contract_profit_designate_1=" +contract_profit_designate_1
      + "," + "contract_profit_designate_2=" +contract_profit_designate_2
      + "," + "contract_profit_designate_3=" +contract_profit_designate_3
      + "," + "contract_profit_designate_4=" +contract_profit_designate_4
      + "," + "contract_profit_designate_5=" +contract_profit_designate_5
      + "," + "payment_amount_designate_0=" +payment_amount_designate_0
      + "," + "payment_amount_designate_1=" +payment_amount_designate_1
      + "," + "payment_amount_designate_2=" +payment_amount_designate_2
      + "," + "payment_amount_designate_3=" +payment_amount_designate_3
      + "," + "payment_amount_designate_4=" +payment_amount_designate_4
      + "," + "payment_amount_designate_5=" +payment_amount_designate_5
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "today_dep_fund_restraint_0=" +today_dep_fund_restraint_0
      + "," + "today_dep_fund_restraint_1=" +today_dep_fund_restraint_1
      + "," + "today_dep_fund_restraint_2=" +today_dep_fund_restraint_2
      + "," + "today_dep_fund_restraint_3=" +today_dep_fund_restraint_3
      + "," + "today_dep_fund_restraint_4=" +today_dep_fund_restraint_4
      + "," + "today_dep_fund_restraint_5=" +today_dep_fund_restraint_5
      + "," + "contract_asset_loss_1=" +contract_asset_loss_1
      + "," + "contract_asset_loss_2=" +contract_asset_loss_2
      + "," + "contract_asset_loss_3=" +contract_asset_loss_3
      + "," + "contract_asset_loss_4=" +contract_asset_loss_4
      + "," + "contract_asset_loss_5=" +contract_asset_loss_5
      + "," + "contract_asset_profit_1=" +contract_asset_profit_1
      + "," + "contract_asset_profit_2=" +contract_asset_profit_2
      + "," + "contract_asset_profit_3=" +contract_asset_profit_3
      + "," + "contract_asset_profit_4=" +contract_asset_profit_4
      + "," + "contract_asset_profit_5=" +contract_asset_profit_5
      + "," + "foreign_equity_asset_delivered=" +foreign_equity_asset_delivered
      + "," + "foreign_equity_asset_executed=" +foreign_equity_asset_executed
      + "," + "today_repay_contract_amount=" +today_repay_contract_amount
      + "," + "substitute_asset_old_day_value=" +substitute_asset_old_day_value
      + "," + "setup_fee_1=" +setup_fee_1
      + "," + "setup_fee_2=" +setup_fee_2
      + "," + "setup_fee_3=" +setup_fee_3
      + "," + "setup_fee_4=" +setup_fee_4
      + "," + "setup_fee_5=" +setup_fee_5
      + "," + "contract_interest_loss_1=" +contract_interest_loss_1
      + "," + "contract_interest_loss_2=" +contract_interest_loss_2
      + "," + "contract_interest_loss_3=" +contract_interest_loss_3
      + "," + "contract_interest_loss_4=" +contract_interest_loss_4
      + "," + "contract_interest_loss_5=" +contract_interest_loss_5
      + "," + "contract_interest_profit_1=" +contract_interest_profit_1
      + "," + "contract_interest_profit_2=" +contract_interest_profit_2
      + "," + "contract_interest_profit_3=" +contract_interest_profit_3
      + "," + "contract_interest_profit_4=" +contract_interest_profit_4
      + "," + "contract_interest_profit_5=" +contract_interest_profit_5
      + "," + "contract_other_cost_1=" +contract_other_cost_1
      + "," + "contract_other_cost_2=" +contract_other_cost_2
      + "," + "contract_other_cost_3=" +contract_other_cost_3
      + "," + "contract_other_cost_4=" +contract_other_cost_4
      + "," + "contract_other_cost_5=" +contract_other_cost_5
      + "}";
  }


  /** 
   * 値が未設定のTpCalcResultMarginDetailParamsオブジェクトを作成します。 
   */
  public TpCalcResultMarginDetailParams() {
    calc_result_margin_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のTpCalcResultMarginDetailRowオブジェクトの値を利用してTpCalcResultMarginDetailParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するTpCalcResultMarginDetailRowオブジェクト 
   */
  public TpCalcResultMarginDetailParams( TpCalcResultMarginDetailRow p_row )
  {
    calc_result_margin_id = p_row.getCalcResultMarginId();
    calc_result_margin_id_is_set = p_row.getCalcResultMarginIdIsSet();
    calc_result_margin_id_is_modified = p_row.getCalcResultMarginIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    if ( !p_row.getEquityAssetDeliveredIsNull() )
      equity_asset_delivered = new Double( p_row.getEquityAssetDelivered() );
    equity_asset_delivered_is_set = p_row.getEquityAssetDeliveredIsSet();
    equity_asset_delivered_is_modified = p_row.getEquityAssetDeliveredIsModified();
    if ( !p_row.getEquityAssetExecutedIsNull() )
      equity_asset_executed = new Double( p_row.getEquityAssetExecuted() );
    equity_asset_executed_is_set = p_row.getEquityAssetExecutedIsSet();
    equity_asset_executed_is_modified = p_row.getEquityAssetExecutedIsModified();
    if ( !p_row.getMiniStockAssetDeliveredIsNull() )
      mini_stock_asset_delivered = new Double( p_row.getMiniStockAssetDelivered() );
    mini_stock_asset_delivered_is_set = p_row.getMiniStockAssetDeliveredIsSet();
    mini_stock_asset_delivered_is_modified = p_row.getMiniStockAssetDeliveredIsModified();
    if ( !p_row.getMiniStockAssetExecutedIsNull() )
      mini_stock_asset_executed = new Double( p_row.getMiniStockAssetExecuted() );
    mini_stock_asset_executed_is_set = p_row.getMiniStockAssetExecutedIsSet();
    mini_stock_asset_executed_is_modified = p_row.getMiniStockAssetExecutedIsModified();
    if ( !p_row.getRuitoAssetDeliveredIsNull() )
      ruito_asset_delivered = new Double( p_row.getRuitoAssetDelivered() );
    ruito_asset_delivered_is_set = p_row.getRuitoAssetDeliveredIsSet();
    ruito_asset_delivered_is_modified = p_row.getRuitoAssetDeliveredIsModified();
    if ( !p_row.getRuitoAssetExecutedIsNull() )
      ruito_asset_executed = new Double( p_row.getRuitoAssetExecuted() );
    ruito_asset_executed_is_set = p_row.getRuitoAssetExecutedIsSet();
    ruito_asset_executed_is_modified = p_row.getRuitoAssetExecutedIsModified();
    if ( !p_row.getMutualFundAssetDeliveredIsNull() )
      mutual_fund_asset_delivered = new Double( p_row.getMutualFundAssetDelivered() );
    mutual_fund_asset_delivered_is_set = p_row.getMutualFundAssetDeliveredIsSet();
    mutual_fund_asset_delivered_is_modified = p_row.getMutualFundAssetDeliveredIsModified();
    if ( !p_row.getMutualFundAssetExecutedIsNull() )
      mutual_fund_asset_executed = new Double( p_row.getMutualFundAssetExecuted() );
    mutual_fund_asset_executed_is_set = p_row.getMutualFundAssetExecutedIsSet();
    mutual_fund_asset_executed_is_modified = p_row.getMutualFundAssetExecutedIsModified();
    if ( !p_row.getBondAssetDeliveredIsNull() )
      bond_asset_delivered = new Double( p_row.getBondAssetDelivered() );
    bond_asset_delivered_is_set = p_row.getBondAssetDeliveredIsSet();
    bond_asset_delivered_is_modified = p_row.getBondAssetDeliveredIsModified();
    if ( !p_row.getBondAssetExecutedIsNull() )
      bond_asset_executed = new Double( p_row.getBondAssetExecuted() );
    bond_asset_executed_is_set = p_row.getBondAssetExecutedIsSet();
    bond_asset_executed_is_modified = p_row.getBondAssetExecutedIsModified();
    if ( !p_row.getUnexecSubstiSecurityAsset0IsNull() )
      unexec_substi_security_asset_0 = new Double( p_row.getUnexecSubstiSecurityAsset0() );
    unexec_substi_security_asset_0_is_set = p_row.getUnexecSubstiSecurityAsset0IsSet();
    unexec_substi_security_asset_0_is_modified = p_row.getUnexecSubstiSecurityAsset0IsModified();
    if ( !p_row.getUnexecSubstiSecurityAsset1IsNull() )
      unexec_substi_security_asset_1 = new Double( p_row.getUnexecSubstiSecurityAsset1() );
    unexec_substi_security_asset_1_is_set = p_row.getUnexecSubstiSecurityAsset1IsSet();
    unexec_substi_security_asset_1_is_modified = p_row.getUnexecSubstiSecurityAsset1IsModified();
    if ( !p_row.getUnexecSubstiSecurityAsset2IsNull() )
      unexec_substi_security_asset_2 = new Double( p_row.getUnexecSubstiSecurityAsset2() );
    unexec_substi_security_asset_2_is_set = p_row.getUnexecSubstiSecurityAsset2IsSet();
    unexec_substi_security_asset_2_is_modified = p_row.getUnexecSubstiSecurityAsset2IsModified();
    if ( !p_row.getUnexecSubstiSecurityAsset3IsNull() )
      unexec_substi_security_asset_3 = new Double( p_row.getUnexecSubstiSecurityAsset3() );
    unexec_substi_security_asset_3_is_set = p_row.getUnexecSubstiSecurityAsset3IsSet();
    unexec_substi_security_asset_3_is_modified = p_row.getUnexecSubstiSecurityAsset3IsModified();
    if ( !p_row.getUnexecSubstiSecurityAsset4IsNull() )
      unexec_substi_security_asset_4 = new Double( p_row.getUnexecSubstiSecurityAsset4() );
    unexec_substi_security_asset_4_is_set = p_row.getUnexecSubstiSecurityAsset4IsSet();
    unexec_substi_security_asset_4_is_modified = p_row.getUnexecSubstiSecurityAsset4IsModified();
    if ( !p_row.getUnexecSubstiSecurityAsset5IsNull() )
      unexec_substi_security_asset_5 = new Double( p_row.getUnexecSubstiSecurityAsset5() );
    unexec_substi_security_asset_5_is_set = p_row.getUnexecSubstiSecurityAsset5IsSet();
    unexec_substi_security_asset_5_is_modified = p_row.getUnexecSubstiSecurityAsset5IsModified();
    if ( !p_row.getContractAssetLossIsNull() )
      contract_asset_loss = new Double( p_row.getContractAssetLoss() );
    contract_asset_loss_is_set = p_row.getContractAssetLossIsSet();
    contract_asset_loss_is_modified = p_row.getContractAssetLossIsModified();
    if ( !p_row.getContractAssetProfitIsNull() )
      contract_asset_profit = new Double( p_row.getContractAssetProfit() );
    contract_asset_profit_is_set = p_row.getContractAssetProfitIsSet();
    contract_asset_profit_is_modified = p_row.getContractAssetProfitIsModified();
    if ( !p_row.getSetupFeeIsNull() )
      setup_fee = new Double( p_row.getSetupFee() );
    setup_fee_is_set = p_row.getSetupFeeIsSet();
    setup_fee_is_modified = p_row.getSetupFeeIsModified();
    if ( !p_row.getContractInterestLossIsNull() )
      contract_interest_loss = new Double( p_row.getContractInterestLoss() );
    contract_interest_loss_is_set = p_row.getContractInterestLossIsSet();
    contract_interest_loss_is_modified = p_row.getContractInterestLossIsModified();
    if ( !p_row.getContractInterestProfitIsNull() )
      contract_interest_profit = new Double( p_row.getContractInterestProfit() );
    contract_interest_profit_is_set = p_row.getContractInterestProfitIsSet();
    contract_interest_profit_is_modified = p_row.getContractInterestProfitIsModified();
    if ( !p_row.getContractOtherCostIsNull() )
      contract_other_cost = new Double( p_row.getContractOtherCost() );
    contract_other_cost_is_set = p_row.getContractOtherCostIsSet();
    contract_other_cost_is_modified = p_row.getContractOtherCostIsModified();
    if ( !p_row.getUnexecContractAmount0IsNull() )
      unexec_contract_amount_0 = new Double( p_row.getUnexecContractAmount0() );
    unexec_contract_amount_0_is_set = p_row.getUnexecContractAmount0IsSet();
    unexec_contract_amount_0_is_modified = p_row.getUnexecContractAmount0IsModified();
    if ( !p_row.getUnexecContractAmount1IsNull() )
      unexec_contract_amount_1 = new Double( p_row.getUnexecContractAmount1() );
    unexec_contract_amount_1_is_set = p_row.getUnexecContractAmount1IsSet();
    unexec_contract_amount_1_is_modified = p_row.getUnexecContractAmount1IsModified();
    if ( !p_row.getUnexecContractAmount2IsNull() )
      unexec_contract_amount_2 = new Double( p_row.getUnexecContractAmount2() );
    unexec_contract_amount_2_is_set = p_row.getUnexecContractAmount2IsSet();
    unexec_contract_amount_2_is_modified = p_row.getUnexecContractAmount2IsModified();
    if ( !p_row.getUnexecContractAmount3IsNull() )
      unexec_contract_amount_3 = new Double( p_row.getUnexecContractAmount3() );
    unexec_contract_amount_3_is_set = p_row.getUnexecContractAmount3IsSet();
    unexec_contract_amount_3_is_modified = p_row.getUnexecContractAmount3IsModified();
    if ( !p_row.getUnexecContractAmount4IsNull() )
      unexec_contract_amount_4 = new Double( p_row.getUnexecContractAmount4() );
    unexec_contract_amount_4_is_set = p_row.getUnexecContractAmount4IsSet();
    unexec_contract_amount_4_is_modified = p_row.getUnexecContractAmount4IsModified();
    if ( !p_row.getUnexecContractAmount5IsNull() )
      unexec_contract_amount_5 = new Double( p_row.getUnexecContractAmount5() );
    unexec_contract_amount_5_is_set = p_row.getUnexecContractAmount5IsSet();
    unexec_contract_amount_5_is_modified = p_row.getUnexecContractAmount5IsModified();
    if ( !p_row.getUnexecMarginDeposit0IsNull() )
      unexec_margin_deposit_0 = new Double( p_row.getUnexecMarginDeposit0() );
    unexec_margin_deposit_0_is_set = p_row.getUnexecMarginDeposit0IsSet();
    unexec_margin_deposit_0_is_modified = p_row.getUnexecMarginDeposit0IsModified();
    if ( !p_row.getUnexecMarginDeposit1IsNull() )
      unexec_margin_deposit_1 = new Double( p_row.getUnexecMarginDeposit1() );
    unexec_margin_deposit_1_is_set = p_row.getUnexecMarginDeposit1IsSet();
    unexec_margin_deposit_1_is_modified = p_row.getUnexecMarginDeposit1IsModified();
    if ( !p_row.getUnexecMarginDeposit2IsNull() )
      unexec_margin_deposit_2 = new Double( p_row.getUnexecMarginDeposit2() );
    unexec_margin_deposit_2_is_set = p_row.getUnexecMarginDeposit2IsSet();
    unexec_margin_deposit_2_is_modified = p_row.getUnexecMarginDeposit2IsModified();
    if ( !p_row.getUnexecMarginDeposit3IsNull() )
      unexec_margin_deposit_3 = new Double( p_row.getUnexecMarginDeposit3() );
    unexec_margin_deposit_3_is_set = p_row.getUnexecMarginDeposit3IsSet();
    unexec_margin_deposit_3_is_modified = p_row.getUnexecMarginDeposit3IsModified();
    if ( !p_row.getUnexecMarginDeposit4IsNull() )
      unexec_margin_deposit_4 = new Double( p_row.getUnexecMarginDeposit4() );
    unexec_margin_deposit_4_is_set = p_row.getUnexecMarginDeposit4IsSet();
    unexec_margin_deposit_4_is_modified = p_row.getUnexecMarginDeposit4IsModified();
    if ( !p_row.getUnexecMarginDeposit5IsNull() )
      unexec_margin_deposit_5 = new Double( p_row.getUnexecMarginDeposit5() );
    unexec_margin_deposit_5_is_set = p_row.getUnexecMarginDeposit5IsSet();
    unexec_margin_deposit_5_is_modified = p_row.getUnexecMarginDeposit5IsModified();
    if ( !p_row.getUnexecCashMarginDeposit0IsNull() )
      unexec_cash_margin_deposit_0 = new Double( p_row.getUnexecCashMarginDeposit0() );
    unexec_cash_margin_deposit_0_is_set = p_row.getUnexecCashMarginDeposit0IsSet();
    unexec_cash_margin_deposit_0_is_modified = p_row.getUnexecCashMarginDeposit0IsModified();
    if ( !p_row.getUnexecCashMarginDeposit1IsNull() )
      unexec_cash_margin_deposit_1 = new Double( p_row.getUnexecCashMarginDeposit1() );
    unexec_cash_margin_deposit_1_is_set = p_row.getUnexecCashMarginDeposit1IsSet();
    unexec_cash_margin_deposit_1_is_modified = p_row.getUnexecCashMarginDeposit1IsModified();
    if ( !p_row.getUnexecCashMarginDeposit2IsNull() )
      unexec_cash_margin_deposit_2 = new Double( p_row.getUnexecCashMarginDeposit2() );
    unexec_cash_margin_deposit_2_is_set = p_row.getUnexecCashMarginDeposit2IsSet();
    unexec_cash_margin_deposit_2_is_modified = p_row.getUnexecCashMarginDeposit2IsModified();
    if ( !p_row.getUnexecCashMarginDeposit3IsNull() )
      unexec_cash_margin_deposit_3 = new Double( p_row.getUnexecCashMarginDeposit3() );
    unexec_cash_margin_deposit_3_is_set = p_row.getUnexecCashMarginDeposit3IsSet();
    unexec_cash_margin_deposit_3_is_modified = p_row.getUnexecCashMarginDeposit3IsModified();
    if ( !p_row.getUnexecCashMarginDeposit4IsNull() )
      unexec_cash_margin_deposit_4 = new Double( p_row.getUnexecCashMarginDeposit4() );
    unexec_cash_margin_deposit_4_is_set = p_row.getUnexecCashMarginDeposit4IsSet();
    unexec_cash_margin_deposit_4_is_modified = p_row.getUnexecCashMarginDeposit4IsModified();
    if ( !p_row.getUnexecCashMarginDeposit5IsNull() )
      unexec_cash_margin_deposit_5 = new Double( p_row.getUnexecCashMarginDeposit5() );
    unexec_cash_margin_deposit_5_is_set = p_row.getUnexecCashMarginDeposit5IsSet();
    unexec_cash_margin_deposit_5_is_modified = p_row.getUnexecCashMarginDeposit5IsModified();
    if ( !p_row.getDayRepayMarginDeposit0IsNull() )
      day_repay_margin_deposit_0 = new Double( p_row.getDayRepayMarginDeposit0() );
    day_repay_margin_deposit_0_is_set = p_row.getDayRepayMarginDeposit0IsSet();
    day_repay_margin_deposit_0_is_modified = p_row.getDayRepayMarginDeposit0IsModified();
    if ( !p_row.getDayRepayMarginDeposit1IsNull() )
      day_repay_margin_deposit_1 = new Double( p_row.getDayRepayMarginDeposit1() );
    day_repay_margin_deposit_1_is_set = p_row.getDayRepayMarginDeposit1IsSet();
    day_repay_margin_deposit_1_is_modified = p_row.getDayRepayMarginDeposit1IsModified();
    if ( !p_row.getDayRepayMarginDeposit2IsNull() )
      day_repay_margin_deposit_2 = new Double( p_row.getDayRepayMarginDeposit2() );
    day_repay_margin_deposit_2_is_set = p_row.getDayRepayMarginDeposit2IsSet();
    day_repay_margin_deposit_2_is_modified = p_row.getDayRepayMarginDeposit2IsModified();
    if ( !p_row.getDayRepayMarginDeposit3IsNull() )
      day_repay_margin_deposit_3 = new Double( p_row.getDayRepayMarginDeposit3() );
    day_repay_margin_deposit_3_is_set = p_row.getDayRepayMarginDeposit3IsSet();
    day_repay_margin_deposit_3_is_modified = p_row.getDayRepayMarginDeposit3IsModified();
    if ( !p_row.getDayRepayMarginDeposit4IsNull() )
      day_repay_margin_deposit_4 = new Double( p_row.getDayRepayMarginDeposit4() );
    day_repay_margin_deposit_4_is_set = p_row.getDayRepayMarginDeposit4IsSet();
    day_repay_margin_deposit_4_is_modified = p_row.getDayRepayMarginDeposit4IsModified();
    if ( !p_row.getDayRepayMarginDeposit5IsNull() )
      day_repay_margin_deposit_5 = new Double( p_row.getDayRepayMarginDeposit5() );
    day_repay_margin_deposit_5_is_set = p_row.getDayRepayMarginDeposit5IsSet();
    day_repay_margin_deposit_5_is_modified = p_row.getDayRepayMarginDeposit5IsModified();
    if ( !p_row.getDayRepayCashMarginDeposit0IsNull() )
      day_repay_cash_margin_deposit0 = new Double( p_row.getDayRepayCashMarginDeposit0() );
    day_repay_cash_margin_deposit0_is_set = p_row.getDayRepayCashMarginDeposit0IsSet();
    day_repay_cash_margin_deposit0_is_modified = p_row.getDayRepayCashMarginDeposit0IsModified();
    if ( !p_row.getDayRepayCashMarginDeposit1IsNull() )
      day_repay_cash_margin_deposit1 = new Double( p_row.getDayRepayCashMarginDeposit1() );
    day_repay_cash_margin_deposit1_is_set = p_row.getDayRepayCashMarginDeposit1IsSet();
    day_repay_cash_margin_deposit1_is_modified = p_row.getDayRepayCashMarginDeposit1IsModified();
    if ( !p_row.getDayRepayCashMarginDeposit2IsNull() )
      day_repay_cash_margin_deposit2 = new Double( p_row.getDayRepayCashMarginDeposit2() );
    day_repay_cash_margin_deposit2_is_set = p_row.getDayRepayCashMarginDeposit2IsSet();
    day_repay_cash_margin_deposit2_is_modified = p_row.getDayRepayCashMarginDeposit2IsModified();
    if ( !p_row.getDayRepayCashMarginDeposit3IsNull() )
      day_repay_cash_margin_deposit3 = new Double( p_row.getDayRepayCashMarginDeposit3() );
    day_repay_cash_margin_deposit3_is_set = p_row.getDayRepayCashMarginDeposit3IsSet();
    day_repay_cash_margin_deposit3_is_modified = p_row.getDayRepayCashMarginDeposit3IsModified();
    if ( !p_row.getDayRepayCashMarginDeposit4IsNull() )
      day_repay_cash_margin_deposit4 = new Double( p_row.getDayRepayCashMarginDeposit4() );
    day_repay_cash_margin_deposit4_is_set = p_row.getDayRepayCashMarginDeposit4IsSet();
    day_repay_cash_margin_deposit4_is_modified = p_row.getDayRepayCashMarginDeposit4IsModified();
    if ( !p_row.getDayRepayCashMarginDeposit5IsNull() )
      day_repay_cash_margin_deposit5 = new Double( p_row.getDayRepayCashMarginDeposit5() );
    day_repay_cash_margin_deposit5_is_set = p_row.getDayRepayCashMarginDeposit5IsSet();
    day_repay_cash_margin_deposit5_is_modified = p_row.getDayRepayCashMarginDeposit5IsModified();
    if ( !p_row.getTodayRepayContractLossIsNull() )
      today_repay_contract_loss = new Double( p_row.getTodayRepayContractLoss() );
    today_repay_contract_loss_is_set = p_row.getTodayRepayContractLossIsSet();
    today_repay_contract_loss_is_modified = p_row.getTodayRepayContractLossIsModified();
    if ( !p_row.getTodayRepayContractProfitIsNull() )
      today_repay_contract_profit = new Double( p_row.getTodayRepayContractProfit() );
    today_repay_contract_profit_is_set = p_row.getTodayRepayContractProfitIsSet();
    today_repay_contract_profit_is_modified = p_row.getTodayRepayContractProfitIsModified();
    if ( !p_row.getTodayRepayContractPreAssetIsNull() )
      today_repay_contract_pre_asset = new Double( p_row.getTodayRepayContractPreAsset() );
    today_repay_contract_pre_asset_is_set = p_row.getTodayRepayContractPreAssetIsSet();
    today_repay_contract_pre_asset_is_modified = p_row.getTodayRepayContractPreAssetIsModified();
    if ( !p_row.getContractLossDesignate1IsNull() )
      contract_loss_designate_1 = new Double( p_row.getContractLossDesignate1() );
    contract_loss_designate_1_is_set = p_row.getContractLossDesignate1IsSet();
    contract_loss_designate_1_is_modified = p_row.getContractLossDesignate1IsModified();
    if ( !p_row.getContractLossDesignate2IsNull() )
      contract_loss_designate_2 = new Double( p_row.getContractLossDesignate2() );
    contract_loss_designate_2_is_set = p_row.getContractLossDesignate2IsSet();
    contract_loss_designate_2_is_modified = p_row.getContractLossDesignate2IsModified();
    if ( !p_row.getContractLossDesignate3IsNull() )
      contract_loss_designate_3 = new Double( p_row.getContractLossDesignate3() );
    contract_loss_designate_3_is_set = p_row.getContractLossDesignate3IsSet();
    contract_loss_designate_3_is_modified = p_row.getContractLossDesignate3IsModified();
    if ( !p_row.getContractLossDesignate4IsNull() )
      contract_loss_designate_4 = new Double( p_row.getContractLossDesignate4() );
    contract_loss_designate_4_is_set = p_row.getContractLossDesignate4IsSet();
    contract_loss_designate_4_is_modified = p_row.getContractLossDesignate4IsModified();
    if ( !p_row.getContractLossDesignate5IsNull() )
      contract_loss_designate_5 = new Double( p_row.getContractLossDesignate5() );
    contract_loss_designate_5_is_set = p_row.getContractLossDesignate5IsSet();
    contract_loss_designate_5_is_modified = p_row.getContractLossDesignate5IsModified();
    if ( !p_row.getContractProfitDesignate1IsNull() )
      contract_profit_designate_1 = new Double( p_row.getContractProfitDesignate1() );
    contract_profit_designate_1_is_set = p_row.getContractProfitDesignate1IsSet();
    contract_profit_designate_1_is_modified = p_row.getContractProfitDesignate1IsModified();
    if ( !p_row.getContractProfitDesignate2IsNull() )
      contract_profit_designate_2 = new Double( p_row.getContractProfitDesignate2() );
    contract_profit_designate_2_is_set = p_row.getContractProfitDesignate2IsSet();
    contract_profit_designate_2_is_modified = p_row.getContractProfitDesignate2IsModified();
    if ( !p_row.getContractProfitDesignate3IsNull() )
      contract_profit_designate_3 = new Double( p_row.getContractProfitDesignate3() );
    contract_profit_designate_3_is_set = p_row.getContractProfitDesignate3IsSet();
    contract_profit_designate_3_is_modified = p_row.getContractProfitDesignate3IsModified();
    if ( !p_row.getContractProfitDesignate4IsNull() )
      contract_profit_designate_4 = new Double( p_row.getContractProfitDesignate4() );
    contract_profit_designate_4_is_set = p_row.getContractProfitDesignate4IsSet();
    contract_profit_designate_4_is_modified = p_row.getContractProfitDesignate4IsModified();
    if ( !p_row.getContractProfitDesignate5IsNull() )
      contract_profit_designate_5 = new Double( p_row.getContractProfitDesignate5() );
    contract_profit_designate_5_is_set = p_row.getContractProfitDesignate5IsSet();
    contract_profit_designate_5_is_modified = p_row.getContractProfitDesignate5IsModified();
    if ( !p_row.getPaymentAmountDesignate0IsNull() )
      payment_amount_designate_0 = new Double( p_row.getPaymentAmountDesignate0() );
    payment_amount_designate_0_is_set = p_row.getPaymentAmountDesignate0IsSet();
    payment_amount_designate_0_is_modified = p_row.getPaymentAmountDesignate0IsModified();
    if ( !p_row.getPaymentAmountDesignate1IsNull() )
      payment_amount_designate_1 = new Double( p_row.getPaymentAmountDesignate1() );
    payment_amount_designate_1_is_set = p_row.getPaymentAmountDesignate1IsSet();
    payment_amount_designate_1_is_modified = p_row.getPaymentAmountDesignate1IsModified();
    if ( !p_row.getPaymentAmountDesignate2IsNull() )
      payment_amount_designate_2 = new Double( p_row.getPaymentAmountDesignate2() );
    payment_amount_designate_2_is_set = p_row.getPaymentAmountDesignate2IsSet();
    payment_amount_designate_2_is_modified = p_row.getPaymentAmountDesignate2IsModified();
    if ( !p_row.getPaymentAmountDesignate3IsNull() )
      payment_amount_designate_3 = new Double( p_row.getPaymentAmountDesignate3() );
    payment_amount_designate_3_is_set = p_row.getPaymentAmountDesignate3IsSet();
    payment_amount_designate_3_is_modified = p_row.getPaymentAmountDesignate3IsModified();
    if ( !p_row.getPaymentAmountDesignate4IsNull() )
      payment_amount_designate_4 = new Double( p_row.getPaymentAmountDesignate4() );
    payment_amount_designate_4_is_set = p_row.getPaymentAmountDesignate4IsSet();
    payment_amount_designate_4_is_modified = p_row.getPaymentAmountDesignate4IsModified();
    if ( !p_row.getPaymentAmountDesignate5IsNull() )
      payment_amount_designate_5 = new Double( p_row.getPaymentAmountDesignate5() );
    payment_amount_designate_5_is_set = p_row.getPaymentAmountDesignate5IsSet();
    payment_amount_designate_5_is_modified = p_row.getPaymentAmountDesignate5IsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    if ( !p_row.getTodayDepFundRestraint0IsNull() )
      today_dep_fund_restraint_0 = new Double( p_row.getTodayDepFundRestraint0() );
    today_dep_fund_restraint_0_is_set = p_row.getTodayDepFundRestraint0IsSet();
    today_dep_fund_restraint_0_is_modified = p_row.getTodayDepFundRestraint0IsModified();
    if ( !p_row.getTodayDepFundRestraint1IsNull() )
      today_dep_fund_restraint_1 = new Double( p_row.getTodayDepFundRestraint1() );
    today_dep_fund_restraint_1_is_set = p_row.getTodayDepFundRestraint1IsSet();
    today_dep_fund_restraint_1_is_modified = p_row.getTodayDepFundRestraint1IsModified();
    if ( !p_row.getTodayDepFundRestraint2IsNull() )
      today_dep_fund_restraint_2 = new Double( p_row.getTodayDepFundRestraint2() );
    today_dep_fund_restraint_2_is_set = p_row.getTodayDepFundRestraint2IsSet();
    today_dep_fund_restraint_2_is_modified = p_row.getTodayDepFundRestraint2IsModified();
    if ( !p_row.getTodayDepFundRestraint3IsNull() )
      today_dep_fund_restraint_3 = new Double( p_row.getTodayDepFundRestraint3() );
    today_dep_fund_restraint_3_is_set = p_row.getTodayDepFundRestraint3IsSet();
    today_dep_fund_restraint_3_is_modified = p_row.getTodayDepFundRestraint3IsModified();
    if ( !p_row.getTodayDepFundRestraint4IsNull() )
      today_dep_fund_restraint_4 = new Double( p_row.getTodayDepFundRestraint4() );
    today_dep_fund_restraint_4_is_set = p_row.getTodayDepFundRestraint4IsSet();
    today_dep_fund_restraint_4_is_modified = p_row.getTodayDepFundRestraint4IsModified();
    if ( !p_row.getTodayDepFundRestraint5IsNull() )
      today_dep_fund_restraint_5 = new Double( p_row.getTodayDepFundRestraint5() );
    today_dep_fund_restraint_5_is_set = p_row.getTodayDepFundRestraint5IsSet();
    today_dep_fund_restraint_5_is_modified = p_row.getTodayDepFundRestraint5IsModified();
    if ( !p_row.getContractAssetLoss1IsNull() )
      contract_asset_loss_1 = new Double( p_row.getContractAssetLoss1() );
    contract_asset_loss_1_is_set = p_row.getContractAssetLoss1IsSet();
    contract_asset_loss_1_is_modified = p_row.getContractAssetLoss1IsModified();
    if ( !p_row.getContractAssetLoss2IsNull() )
      contract_asset_loss_2 = new Double( p_row.getContractAssetLoss2() );
    contract_asset_loss_2_is_set = p_row.getContractAssetLoss2IsSet();
    contract_asset_loss_2_is_modified = p_row.getContractAssetLoss2IsModified();
    if ( !p_row.getContractAssetLoss3IsNull() )
      contract_asset_loss_3 = new Double( p_row.getContractAssetLoss3() );
    contract_asset_loss_3_is_set = p_row.getContractAssetLoss3IsSet();
    contract_asset_loss_3_is_modified = p_row.getContractAssetLoss3IsModified();
    if ( !p_row.getContractAssetLoss4IsNull() )
      contract_asset_loss_4 = new Double( p_row.getContractAssetLoss4() );
    contract_asset_loss_4_is_set = p_row.getContractAssetLoss4IsSet();
    contract_asset_loss_4_is_modified = p_row.getContractAssetLoss4IsModified();
    if ( !p_row.getContractAssetLoss5IsNull() )
      contract_asset_loss_5 = new Double( p_row.getContractAssetLoss5() );
    contract_asset_loss_5_is_set = p_row.getContractAssetLoss5IsSet();
    contract_asset_loss_5_is_modified = p_row.getContractAssetLoss5IsModified();
    if ( !p_row.getContractAssetProfit1IsNull() )
      contract_asset_profit_1 = new Double( p_row.getContractAssetProfit1() );
    contract_asset_profit_1_is_set = p_row.getContractAssetProfit1IsSet();
    contract_asset_profit_1_is_modified = p_row.getContractAssetProfit1IsModified();
    if ( !p_row.getContractAssetProfit2IsNull() )
      contract_asset_profit_2 = new Double( p_row.getContractAssetProfit2() );
    contract_asset_profit_2_is_set = p_row.getContractAssetProfit2IsSet();
    contract_asset_profit_2_is_modified = p_row.getContractAssetProfit2IsModified();
    if ( !p_row.getContractAssetProfit3IsNull() )
      contract_asset_profit_3 = new Double( p_row.getContractAssetProfit3() );
    contract_asset_profit_3_is_set = p_row.getContractAssetProfit3IsSet();
    contract_asset_profit_3_is_modified = p_row.getContractAssetProfit3IsModified();
    if ( !p_row.getContractAssetProfit4IsNull() )
      contract_asset_profit_4 = new Double( p_row.getContractAssetProfit4() );
    contract_asset_profit_4_is_set = p_row.getContractAssetProfit4IsSet();
    contract_asset_profit_4_is_modified = p_row.getContractAssetProfit4IsModified();
    if ( !p_row.getContractAssetProfit5IsNull() )
      contract_asset_profit_5 = new Double( p_row.getContractAssetProfit5() );
    contract_asset_profit_5_is_set = p_row.getContractAssetProfit5IsSet();
    contract_asset_profit_5_is_modified = p_row.getContractAssetProfit5IsModified();
    if ( !p_row.getForeignEquityAssetDeliveredIsNull() )
      foreign_equity_asset_delivered = new Double( p_row.getForeignEquityAssetDelivered() );
    foreign_equity_asset_delivered_is_set = p_row.getForeignEquityAssetDeliveredIsSet();
    foreign_equity_asset_delivered_is_modified = p_row.getForeignEquityAssetDeliveredIsModified();
    if ( !p_row.getForeignEquityAssetExecutedIsNull() )
      foreign_equity_asset_executed = new Double( p_row.getForeignEquityAssetExecuted() );
    foreign_equity_asset_executed_is_set = p_row.getForeignEquityAssetExecutedIsSet();
    foreign_equity_asset_executed_is_modified = p_row.getForeignEquityAssetExecutedIsModified();
    if ( !p_row.getTodayRepayContractAmountIsNull() )
      today_repay_contract_amount = new Double( p_row.getTodayRepayContractAmount() );
    today_repay_contract_amount_is_set = p_row.getTodayRepayContractAmountIsSet();
    today_repay_contract_amount_is_modified = p_row.getTodayRepayContractAmountIsModified();
    if ( !p_row.getSubstituteAssetOldDayValueIsNull() )
      substitute_asset_old_day_value = new Double( p_row.getSubstituteAssetOldDayValue() );
    substitute_asset_old_day_value_is_set = p_row.getSubstituteAssetOldDayValueIsSet();
    substitute_asset_old_day_value_is_modified = p_row.getSubstituteAssetOldDayValueIsModified();
    if ( !p_row.getSetupFee1IsNull() )
      setup_fee_1 = new Double( p_row.getSetupFee1() );
    setup_fee_1_is_set = p_row.getSetupFee1IsSet();
    setup_fee_1_is_modified = p_row.getSetupFee1IsModified();
    if ( !p_row.getSetupFee2IsNull() )
      setup_fee_2 = new Double( p_row.getSetupFee2() );
    setup_fee_2_is_set = p_row.getSetupFee2IsSet();
    setup_fee_2_is_modified = p_row.getSetupFee2IsModified();
    if ( !p_row.getSetupFee3IsNull() )
      setup_fee_3 = new Double( p_row.getSetupFee3() );
    setup_fee_3_is_set = p_row.getSetupFee3IsSet();
    setup_fee_3_is_modified = p_row.getSetupFee3IsModified();
    if ( !p_row.getSetupFee4IsNull() )
      setup_fee_4 = new Double( p_row.getSetupFee4() );
    setup_fee_4_is_set = p_row.getSetupFee4IsSet();
    setup_fee_4_is_modified = p_row.getSetupFee4IsModified();
    if ( !p_row.getSetupFee5IsNull() )
      setup_fee_5 = new Double( p_row.getSetupFee5() );
    setup_fee_5_is_set = p_row.getSetupFee5IsSet();
    setup_fee_5_is_modified = p_row.getSetupFee5IsModified();
    if ( !p_row.getContractInterestLoss1IsNull() )
      contract_interest_loss_1 = new Double( p_row.getContractInterestLoss1() );
    contract_interest_loss_1_is_set = p_row.getContractInterestLoss1IsSet();
    contract_interest_loss_1_is_modified = p_row.getContractInterestLoss1IsModified();
    if ( !p_row.getContractInterestLoss2IsNull() )
      contract_interest_loss_2 = new Double( p_row.getContractInterestLoss2() );
    contract_interest_loss_2_is_set = p_row.getContractInterestLoss2IsSet();
    contract_interest_loss_2_is_modified = p_row.getContractInterestLoss2IsModified();
    if ( !p_row.getContractInterestLoss3IsNull() )
      contract_interest_loss_3 = new Double( p_row.getContractInterestLoss3() );
    contract_interest_loss_3_is_set = p_row.getContractInterestLoss3IsSet();
    contract_interest_loss_3_is_modified = p_row.getContractInterestLoss3IsModified();
    if ( !p_row.getContractInterestLoss4IsNull() )
      contract_interest_loss_4 = new Double( p_row.getContractInterestLoss4() );
    contract_interest_loss_4_is_set = p_row.getContractInterestLoss4IsSet();
    contract_interest_loss_4_is_modified = p_row.getContractInterestLoss4IsModified();
    if ( !p_row.getContractInterestLoss5IsNull() )
      contract_interest_loss_5 = new Double( p_row.getContractInterestLoss5() );
    contract_interest_loss_5_is_set = p_row.getContractInterestLoss5IsSet();
    contract_interest_loss_5_is_modified = p_row.getContractInterestLoss5IsModified();
    if ( !p_row.getContractInterestProfit1IsNull() )
      contract_interest_profit_1 = new Double( p_row.getContractInterestProfit1() );
    contract_interest_profit_1_is_set = p_row.getContractInterestProfit1IsSet();
    contract_interest_profit_1_is_modified = p_row.getContractInterestProfit1IsModified();
    if ( !p_row.getContractInterestProfit2IsNull() )
      contract_interest_profit_2 = new Double( p_row.getContractInterestProfit2() );
    contract_interest_profit_2_is_set = p_row.getContractInterestProfit2IsSet();
    contract_interest_profit_2_is_modified = p_row.getContractInterestProfit2IsModified();
    if ( !p_row.getContractInterestProfit3IsNull() )
      contract_interest_profit_3 = new Double( p_row.getContractInterestProfit3() );
    contract_interest_profit_3_is_set = p_row.getContractInterestProfit3IsSet();
    contract_interest_profit_3_is_modified = p_row.getContractInterestProfit3IsModified();
    if ( !p_row.getContractInterestProfit4IsNull() )
      contract_interest_profit_4 = new Double( p_row.getContractInterestProfit4() );
    contract_interest_profit_4_is_set = p_row.getContractInterestProfit4IsSet();
    contract_interest_profit_4_is_modified = p_row.getContractInterestProfit4IsModified();
    if ( !p_row.getContractInterestProfit5IsNull() )
      contract_interest_profit_5 = new Double( p_row.getContractInterestProfit5() );
    contract_interest_profit_5_is_set = p_row.getContractInterestProfit5IsSet();
    contract_interest_profit_5_is_modified = p_row.getContractInterestProfit5IsModified();
    if ( !p_row.getContractOtherCost1IsNull() )
      contract_other_cost_1 = new Double( p_row.getContractOtherCost1() );
    contract_other_cost_1_is_set = p_row.getContractOtherCost1IsSet();
    contract_other_cost_1_is_modified = p_row.getContractOtherCost1IsModified();
    if ( !p_row.getContractOtherCost2IsNull() )
      contract_other_cost_2 = new Double( p_row.getContractOtherCost2() );
    contract_other_cost_2_is_set = p_row.getContractOtherCost2IsSet();
    contract_other_cost_2_is_modified = p_row.getContractOtherCost2IsModified();
    if ( !p_row.getContractOtherCost3IsNull() )
      contract_other_cost_3 = new Double( p_row.getContractOtherCost3() );
    contract_other_cost_3_is_set = p_row.getContractOtherCost3IsSet();
    contract_other_cost_3_is_modified = p_row.getContractOtherCost3IsModified();
    if ( !p_row.getContractOtherCost4IsNull() )
      contract_other_cost_4 = new Double( p_row.getContractOtherCost4() );
    contract_other_cost_4_is_set = p_row.getContractOtherCost4IsSet();
    contract_other_cost_4_is_modified = p_row.getContractOtherCost4IsModified();
    if ( !p_row.getContractOtherCost5IsNull() )
      contract_other_cost_5 = new Double( p_row.getContractOtherCost5() );
    contract_other_cost_5_is_set = p_row.getContractOtherCost5IsSet();
    contract_other_cost_5_is_modified = p_row.getContractOtherCost5IsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      account_id_is_set = true;
      account_id_is_modified = true;
      equity_asset_delivered_is_set = true;
      equity_asset_delivered_is_modified = true;
      equity_asset_executed_is_set = true;
      equity_asset_executed_is_modified = true;
      mini_stock_asset_delivered_is_set = true;
      mini_stock_asset_delivered_is_modified = true;
      mini_stock_asset_executed_is_set = true;
      mini_stock_asset_executed_is_modified = true;
      ruito_asset_delivered_is_set = true;
      ruito_asset_delivered_is_modified = true;
      ruito_asset_executed_is_set = true;
      ruito_asset_executed_is_modified = true;
      mutual_fund_asset_delivered_is_set = true;
      mutual_fund_asset_delivered_is_modified = true;
      mutual_fund_asset_executed_is_set = true;
      mutual_fund_asset_executed_is_modified = true;
      bond_asset_delivered_is_set = true;
      bond_asset_delivered_is_modified = true;
      bond_asset_executed_is_set = true;
      bond_asset_executed_is_modified = true;
      unexec_substi_security_asset_0_is_set = true;
      unexec_substi_security_asset_0_is_modified = true;
      unexec_substi_security_asset_1_is_set = true;
      unexec_substi_security_asset_1_is_modified = true;
      unexec_substi_security_asset_2_is_set = true;
      unexec_substi_security_asset_2_is_modified = true;
      unexec_substi_security_asset_3_is_set = true;
      unexec_substi_security_asset_3_is_modified = true;
      unexec_substi_security_asset_4_is_set = true;
      unexec_substi_security_asset_4_is_modified = true;
      unexec_substi_security_asset_5_is_set = true;
      unexec_substi_security_asset_5_is_modified = true;
      contract_asset_loss_is_set = true;
      contract_asset_loss_is_modified = true;
      contract_asset_profit_is_set = true;
      contract_asset_profit_is_modified = true;
      setup_fee_is_set = true;
      setup_fee_is_modified = true;
      contract_interest_loss_is_set = true;
      contract_interest_loss_is_modified = true;
      contract_interest_profit_is_set = true;
      contract_interest_profit_is_modified = true;
      contract_other_cost_is_set = true;
      contract_other_cost_is_modified = true;
      unexec_contract_amount_0_is_set = true;
      unexec_contract_amount_0_is_modified = true;
      unexec_contract_amount_1_is_set = true;
      unexec_contract_amount_1_is_modified = true;
      unexec_contract_amount_2_is_set = true;
      unexec_contract_amount_2_is_modified = true;
      unexec_contract_amount_3_is_set = true;
      unexec_contract_amount_3_is_modified = true;
      unexec_contract_amount_4_is_set = true;
      unexec_contract_amount_4_is_modified = true;
      unexec_contract_amount_5_is_set = true;
      unexec_contract_amount_5_is_modified = true;
      unexec_margin_deposit_0_is_set = true;
      unexec_margin_deposit_0_is_modified = true;
      unexec_margin_deposit_1_is_set = true;
      unexec_margin_deposit_1_is_modified = true;
      unexec_margin_deposit_2_is_set = true;
      unexec_margin_deposit_2_is_modified = true;
      unexec_margin_deposit_3_is_set = true;
      unexec_margin_deposit_3_is_modified = true;
      unexec_margin_deposit_4_is_set = true;
      unexec_margin_deposit_4_is_modified = true;
      unexec_margin_deposit_5_is_set = true;
      unexec_margin_deposit_5_is_modified = true;
      unexec_cash_margin_deposit_0_is_set = true;
      unexec_cash_margin_deposit_0_is_modified = true;
      unexec_cash_margin_deposit_1_is_set = true;
      unexec_cash_margin_deposit_1_is_modified = true;
      unexec_cash_margin_deposit_2_is_set = true;
      unexec_cash_margin_deposit_2_is_modified = true;
      unexec_cash_margin_deposit_3_is_set = true;
      unexec_cash_margin_deposit_3_is_modified = true;
      unexec_cash_margin_deposit_4_is_set = true;
      unexec_cash_margin_deposit_4_is_modified = true;
      unexec_cash_margin_deposit_5_is_set = true;
      unexec_cash_margin_deposit_5_is_modified = true;
      day_repay_margin_deposit_0_is_set = true;
      day_repay_margin_deposit_0_is_modified = true;
      day_repay_margin_deposit_1_is_set = true;
      day_repay_margin_deposit_1_is_modified = true;
      day_repay_margin_deposit_2_is_set = true;
      day_repay_margin_deposit_2_is_modified = true;
      day_repay_margin_deposit_3_is_set = true;
      day_repay_margin_deposit_3_is_modified = true;
      day_repay_margin_deposit_4_is_set = true;
      day_repay_margin_deposit_4_is_modified = true;
      day_repay_margin_deposit_5_is_set = true;
      day_repay_margin_deposit_5_is_modified = true;
      day_repay_cash_margin_deposit0_is_set = true;
      day_repay_cash_margin_deposit0_is_modified = true;
      day_repay_cash_margin_deposit1_is_set = true;
      day_repay_cash_margin_deposit1_is_modified = true;
      day_repay_cash_margin_deposit2_is_set = true;
      day_repay_cash_margin_deposit2_is_modified = true;
      day_repay_cash_margin_deposit3_is_set = true;
      day_repay_cash_margin_deposit3_is_modified = true;
      day_repay_cash_margin_deposit4_is_set = true;
      day_repay_cash_margin_deposit4_is_modified = true;
      day_repay_cash_margin_deposit5_is_set = true;
      day_repay_cash_margin_deposit5_is_modified = true;
      today_repay_contract_loss_is_set = true;
      today_repay_contract_loss_is_modified = true;
      today_repay_contract_profit_is_set = true;
      today_repay_contract_profit_is_modified = true;
      today_repay_contract_pre_asset_is_set = true;
      today_repay_contract_pre_asset_is_modified = true;
      contract_loss_designate_1_is_set = true;
      contract_loss_designate_1_is_modified = true;
      contract_loss_designate_2_is_set = true;
      contract_loss_designate_2_is_modified = true;
      contract_loss_designate_3_is_set = true;
      contract_loss_designate_3_is_modified = true;
      contract_loss_designate_4_is_set = true;
      contract_loss_designate_4_is_modified = true;
      contract_loss_designate_5_is_set = true;
      contract_loss_designate_5_is_modified = true;
      contract_profit_designate_1_is_set = true;
      contract_profit_designate_1_is_modified = true;
      contract_profit_designate_2_is_set = true;
      contract_profit_designate_2_is_modified = true;
      contract_profit_designate_3_is_set = true;
      contract_profit_designate_3_is_modified = true;
      contract_profit_designate_4_is_set = true;
      contract_profit_designate_4_is_modified = true;
      contract_profit_designate_5_is_set = true;
      contract_profit_designate_5_is_modified = true;
      payment_amount_designate_0_is_set = true;
      payment_amount_designate_0_is_modified = true;
      payment_amount_designate_1_is_set = true;
      payment_amount_designate_1_is_modified = true;
      payment_amount_designate_2_is_set = true;
      payment_amount_designate_2_is_modified = true;
      payment_amount_designate_3_is_set = true;
      payment_amount_designate_3_is_modified = true;
      payment_amount_designate_4_is_set = true;
      payment_amount_designate_4_is_modified = true;
      payment_amount_designate_5_is_set = true;
      payment_amount_designate_5_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      today_dep_fund_restraint_0_is_set = true;
      today_dep_fund_restraint_0_is_modified = true;
      today_dep_fund_restraint_1_is_set = true;
      today_dep_fund_restraint_1_is_modified = true;
      today_dep_fund_restraint_2_is_set = true;
      today_dep_fund_restraint_2_is_modified = true;
      today_dep_fund_restraint_3_is_set = true;
      today_dep_fund_restraint_3_is_modified = true;
      today_dep_fund_restraint_4_is_set = true;
      today_dep_fund_restraint_4_is_modified = true;
      today_dep_fund_restraint_5_is_set = true;
      today_dep_fund_restraint_5_is_modified = true;
      contract_asset_loss_1_is_set = true;
      contract_asset_loss_1_is_modified = true;
      contract_asset_loss_2_is_set = true;
      contract_asset_loss_2_is_modified = true;
      contract_asset_loss_3_is_set = true;
      contract_asset_loss_3_is_modified = true;
      contract_asset_loss_4_is_set = true;
      contract_asset_loss_4_is_modified = true;
      contract_asset_loss_5_is_set = true;
      contract_asset_loss_5_is_modified = true;
      contract_asset_profit_1_is_set = true;
      contract_asset_profit_1_is_modified = true;
      contract_asset_profit_2_is_set = true;
      contract_asset_profit_2_is_modified = true;
      contract_asset_profit_3_is_set = true;
      contract_asset_profit_3_is_modified = true;
      contract_asset_profit_4_is_set = true;
      contract_asset_profit_4_is_modified = true;
      contract_asset_profit_5_is_set = true;
      contract_asset_profit_5_is_modified = true;
      foreign_equity_asset_delivered_is_set = true;
      foreign_equity_asset_delivered_is_modified = true;
      foreign_equity_asset_executed_is_set = true;
      foreign_equity_asset_executed_is_modified = true;
      today_repay_contract_amount_is_set = true;
      today_repay_contract_amount_is_modified = true;
      substitute_asset_old_day_value_is_set = true;
      substitute_asset_old_day_value_is_modified = true;
      setup_fee_1_is_set = true;
      setup_fee_1_is_modified = true;
      setup_fee_2_is_set = true;
      setup_fee_2_is_modified = true;
      setup_fee_3_is_set = true;
      setup_fee_3_is_modified = true;
      setup_fee_4_is_set = true;
      setup_fee_4_is_modified = true;
      setup_fee_5_is_set = true;
      setup_fee_5_is_modified = true;
      contract_interest_loss_1_is_set = true;
      contract_interest_loss_1_is_modified = true;
      contract_interest_loss_2_is_set = true;
      contract_interest_loss_2_is_modified = true;
      contract_interest_loss_3_is_set = true;
      contract_interest_loss_3_is_modified = true;
      contract_interest_loss_4_is_set = true;
      contract_interest_loss_4_is_modified = true;
      contract_interest_loss_5_is_set = true;
      contract_interest_loss_5_is_modified = true;
      contract_interest_profit_1_is_set = true;
      contract_interest_profit_1_is_modified = true;
      contract_interest_profit_2_is_set = true;
      contract_interest_profit_2_is_modified = true;
      contract_interest_profit_3_is_set = true;
      contract_interest_profit_3_is_modified = true;
      contract_interest_profit_4_is_set = true;
      contract_interest_profit_4_is_modified = true;
      contract_interest_profit_5_is_set = true;
      contract_interest_profit_5_is_modified = true;
      contract_other_cost_1_is_set = true;
      contract_other_cost_1_is_modified = true;
      contract_other_cost_2_is_set = true;
      contract_other_cost_2_is_modified = true;
      contract_other_cost_3_is_set = true;
      contract_other_cost_3_is_modified = true;
      contract_other_cost_4_is_set = true;
      contract_other_cost_4_is_modified = true;
      contract_other_cost_5_is_set = true;
      contract_other_cost_5_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof TpCalcResultMarginDetailRow ) )
       return false;
    return fieldsEqual( (TpCalcResultMarginDetailRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のTpCalcResultMarginDetailRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( TpCalcResultMarginDetailRow row )
  {
    if ( calc_result_margin_id != row.getCalcResultMarginId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( equity_asset_delivered == null ) {
      if ( !row.getEquityAssetDeliveredIsNull() )
        return false;
    } else if ( row.getEquityAssetDeliveredIsNull() || ( equity_asset_delivered.doubleValue() != row.getEquityAssetDelivered() ) ) {
        return false;
    }
    if ( equity_asset_executed == null ) {
      if ( !row.getEquityAssetExecutedIsNull() )
        return false;
    } else if ( row.getEquityAssetExecutedIsNull() || ( equity_asset_executed.doubleValue() != row.getEquityAssetExecuted() ) ) {
        return false;
    }
    if ( mini_stock_asset_delivered == null ) {
      if ( !row.getMiniStockAssetDeliveredIsNull() )
        return false;
    } else if ( row.getMiniStockAssetDeliveredIsNull() || ( mini_stock_asset_delivered.doubleValue() != row.getMiniStockAssetDelivered() ) ) {
        return false;
    }
    if ( mini_stock_asset_executed == null ) {
      if ( !row.getMiniStockAssetExecutedIsNull() )
        return false;
    } else if ( row.getMiniStockAssetExecutedIsNull() || ( mini_stock_asset_executed.doubleValue() != row.getMiniStockAssetExecuted() ) ) {
        return false;
    }
    if ( ruito_asset_delivered == null ) {
      if ( !row.getRuitoAssetDeliveredIsNull() )
        return false;
    } else if ( row.getRuitoAssetDeliveredIsNull() || ( ruito_asset_delivered.doubleValue() != row.getRuitoAssetDelivered() ) ) {
        return false;
    }
    if ( ruito_asset_executed == null ) {
      if ( !row.getRuitoAssetExecutedIsNull() )
        return false;
    } else if ( row.getRuitoAssetExecutedIsNull() || ( ruito_asset_executed.doubleValue() != row.getRuitoAssetExecuted() ) ) {
        return false;
    }
    if ( mutual_fund_asset_delivered == null ) {
      if ( !row.getMutualFundAssetDeliveredIsNull() )
        return false;
    } else if ( row.getMutualFundAssetDeliveredIsNull() || ( mutual_fund_asset_delivered.doubleValue() != row.getMutualFundAssetDelivered() ) ) {
        return false;
    }
    if ( mutual_fund_asset_executed == null ) {
      if ( !row.getMutualFundAssetExecutedIsNull() )
        return false;
    } else if ( row.getMutualFundAssetExecutedIsNull() || ( mutual_fund_asset_executed.doubleValue() != row.getMutualFundAssetExecuted() ) ) {
        return false;
    }
    if ( bond_asset_delivered == null ) {
      if ( !row.getBondAssetDeliveredIsNull() )
        return false;
    } else if ( row.getBondAssetDeliveredIsNull() || ( bond_asset_delivered.doubleValue() != row.getBondAssetDelivered() ) ) {
        return false;
    }
    if ( bond_asset_executed == null ) {
      if ( !row.getBondAssetExecutedIsNull() )
        return false;
    } else if ( row.getBondAssetExecutedIsNull() || ( bond_asset_executed.doubleValue() != row.getBondAssetExecuted() ) ) {
        return false;
    }
    if ( unexec_substi_security_asset_0 == null ) {
      if ( !row.getUnexecSubstiSecurityAsset0IsNull() )
        return false;
    } else if ( row.getUnexecSubstiSecurityAsset0IsNull() || ( unexec_substi_security_asset_0.doubleValue() != row.getUnexecSubstiSecurityAsset0() ) ) {
        return false;
    }
    if ( unexec_substi_security_asset_1 == null ) {
      if ( !row.getUnexecSubstiSecurityAsset1IsNull() )
        return false;
    } else if ( row.getUnexecSubstiSecurityAsset1IsNull() || ( unexec_substi_security_asset_1.doubleValue() != row.getUnexecSubstiSecurityAsset1() ) ) {
        return false;
    }
    if ( unexec_substi_security_asset_2 == null ) {
      if ( !row.getUnexecSubstiSecurityAsset2IsNull() )
        return false;
    } else if ( row.getUnexecSubstiSecurityAsset2IsNull() || ( unexec_substi_security_asset_2.doubleValue() != row.getUnexecSubstiSecurityAsset2() ) ) {
        return false;
    }
    if ( unexec_substi_security_asset_3 == null ) {
      if ( !row.getUnexecSubstiSecurityAsset3IsNull() )
        return false;
    } else if ( row.getUnexecSubstiSecurityAsset3IsNull() || ( unexec_substi_security_asset_3.doubleValue() != row.getUnexecSubstiSecurityAsset3() ) ) {
        return false;
    }
    if ( unexec_substi_security_asset_4 == null ) {
      if ( !row.getUnexecSubstiSecurityAsset4IsNull() )
        return false;
    } else if ( row.getUnexecSubstiSecurityAsset4IsNull() || ( unexec_substi_security_asset_4.doubleValue() != row.getUnexecSubstiSecurityAsset4() ) ) {
        return false;
    }
    if ( unexec_substi_security_asset_5 == null ) {
      if ( !row.getUnexecSubstiSecurityAsset5IsNull() )
        return false;
    } else if ( row.getUnexecSubstiSecurityAsset5IsNull() || ( unexec_substi_security_asset_5.doubleValue() != row.getUnexecSubstiSecurityAsset5() ) ) {
        return false;
    }
    if ( contract_asset_loss == null ) {
      if ( !row.getContractAssetLossIsNull() )
        return false;
    } else if ( row.getContractAssetLossIsNull() || ( contract_asset_loss.doubleValue() != row.getContractAssetLoss() ) ) {
        return false;
    }
    if ( contract_asset_profit == null ) {
      if ( !row.getContractAssetProfitIsNull() )
        return false;
    } else if ( row.getContractAssetProfitIsNull() || ( contract_asset_profit.doubleValue() != row.getContractAssetProfit() ) ) {
        return false;
    }
    if ( setup_fee == null ) {
      if ( !row.getSetupFeeIsNull() )
        return false;
    } else if ( row.getSetupFeeIsNull() || ( setup_fee.doubleValue() != row.getSetupFee() ) ) {
        return false;
    }
    if ( contract_interest_loss == null ) {
      if ( !row.getContractInterestLossIsNull() )
        return false;
    } else if ( row.getContractInterestLossIsNull() || ( contract_interest_loss.doubleValue() != row.getContractInterestLoss() ) ) {
        return false;
    }
    if ( contract_interest_profit == null ) {
      if ( !row.getContractInterestProfitIsNull() )
        return false;
    } else if ( row.getContractInterestProfitIsNull() || ( contract_interest_profit.doubleValue() != row.getContractInterestProfit() ) ) {
        return false;
    }
    if ( contract_other_cost == null ) {
      if ( !row.getContractOtherCostIsNull() )
        return false;
    } else if ( row.getContractOtherCostIsNull() || ( contract_other_cost.doubleValue() != row.getContractOtherCost() ) ) {
        return false;
    }
    if ( unexec_contract_amount_0 == null ) {
      if ( !row.getUnexecContractAmount0IsNull() )
        return false;
    } else if ( row.getUnexecContractAmount0IsNull() || ( unexec_contract_amount_0.doubleValue() != row.getUnexecContractAmount0() ) ) {
        return false;
    }
    if ( unexec_contract_amount_1 == null ) {
      if ( !row.getUnexecContractAmount1IsNull() )
        return false;
    } else if ( row.getUnexecContractAmount1IsNull() || ( unexec_contract_amount_1.doubleValue() != row.getUnexecContractAmount1() ) ) {
        return false;
    }
    if ( unexec_contract_amount_2 == null ) {
      if ( !row.getUnexecContractAmount2IsNull() )
        return false;
    } else if ( row.getUnexecContractAmount2IsNull() || ( unexec_contract_amount_2.doubleValue() != row.getUnexecContractAmount2() ) ) {
        return false;
    }
    if ( unexec_contract_amount_3 == null ) {
      if ( !row.getUnexecContractAmount3IsNull() )
        return false;
    } else if ( row.getUnexecContractAmount3IsNull() || ( unexec_contract_amount_3.doubleValue() != row.getUnexecContractAmount3() ) ) {
        return false;
    }
    if ( unexec_contract_amount_4 == null ) {
      if ( !row.getUnexecContractAmount4IsNull() )
        return false;
    } else if ( row.getUnexecContractAmount4IsNull() || ( unexec_contract_amount_4.doubleValue() != row.getUnexecContractAmount4() ) ) {
        return false;
    }
    if ( unexec_contract_amount_5 == null ) {
      if ( !row.getUnexecContractAmount5IsNull() )
        return false;
    } else if ( row.getUnexecContractAmount5IsNull() || ( unexec_contract_amount_5.doubleValue() != row.getUnexecContractAmount5() ) ) {
        return false;
    }
    if ( unexec_margin_deposit_0 == null ) {
      if ( !row.getUnexecMarginDeposit0IsNull() )
        return false;
    } else if ( row.getUnexecMarginDeposit0IsNull() || ( unexec_margin_deposit_0.doubleValue() != row.getUnexecMarginDeposit0() ) ) {
        return false;
    }
    if ( unexec_margin_deposit_1 == null ) {
      if ( !row.getUnexecMarginDeposit1IsNull() )
        return false;
    } else if ( row.getUnexecMarginDeposit1IsNull() || ( unexec_margin_deposit_1.doubleValue() != row.getUnexecMarginDeposit1() ) ) {
        return false;
    }
    if ( unexec_margin_deposit_2 == null ) {
      if ( !row.getUnexecMarginDeposit2IsNull() )
        return false;
    } else if ( row.getUnexecMarginDeposit2IsNull() || ( unexec_margin_deposit_2.doubleValue() != row.getUnexecMarginDeposit2() ) ) {
        return false;
    }
    if ( unexec_margin_deposit_3 == null ) {
      if ( !row.getUnexecMarginDeposit3IsNull() )
        return false;
    } else if ( row.getUnexecMarginDeposit3IsNull() || ( unexec_margin_deposit_3.doubleValue() != row.getUnexecMarginDeposit3() ) ) {
        return false;
    }
    if ( unexec_margin_deposit_4 == null ) {
      if ( !row.getUnexecMarginDeposit4IsNull() )
        return false;
    } else if ( row.getUnexecMarginDeposit4IsNull() || ( unexec_margin_deposit_4.doubleValue() != row.getUnexecMarginDeposit4() ) ) {
        return false;
    }
    if ( unexec_margin_deposit_5 == null ) {
      if ( !row.getUnexecMarginDeposit5IsNull() )
        return false;
    } else if ( row.getUnexecMarginDeposit5IsNull() || ( unexec_margin_deposit_5.doubleValue() != row.getUnexecMarginDeposit5() ) ) {
        return false;
    }
    if ( unexec_cash_margin_deposit_0 == null ) {
      if ( !row.getUnexecCashMarginDeposit0IsNull() )
        return false;
    } else if ( row.getUnexecCashMarginDeposit0IsNull() || ( unexec_cash_margin_deposit_0.doubleValue() != row.getUnexecCashMarginDeposit0() ) ) {
        return false;
    }
    if ( unexec_cash_margin_deposit_1 == null ) {
      if ( !row.getUnexecCashMarginDeposit1IsNull() )
        return false;
    } else if ( row.getUnexecCashMarginDeposit1IsNull() || ( unexec_cash_margin_deposit_1.doubleValue() != row.getUnexecCashMarginDeposit1() ) ) {
        return false;
    }
    if ( unexec_cash_margin_deposit_2 == null ) {
      if ( !row.getUnexecCashMarginDeposit2IsNull() )
        return false;
    } else if ( row.getUnexecCashMarginDeposit2IsNull() || ( unexec_cash_margin_deposit_2.doubleValue() != row.getUnexecCashMarginDeposit2() ) ) {
        return false;
    }
    if ( unexec_cash_margin_deposit_3 == null ) {
      if ( !row.getUnexecCashMarginDeposit3IsNull() )
        return false;
    } else if ( row.getUnexecCashMarginDeposit3IsNull() || ( unexec_cash_margin_deposit_3.doubleValue() != row.getUnexecCashMarginDeposit3() ) ) {
        return false;
    }
    if ( unexec_cash_margin_deposit_4 == null ) {
      if ( !row.getUnexecCashMarginDeposit4IsNull() )
        return false;
    } else if ( row.getUnexecCashMarginDeposit4IsNull() || ( unexec_cash_margin_deposit_4.doubleValue() != row.getUnexecCashMarginDeposit4() ) ) {
        return false;
    }
    if ( unexec_cash_margin_deposit_5 == null ) {
      if ( !row.getUnexecCashMarginDeposit5IsNull() )
        return false;
    } else if ( row.getUnexecCashMarginDeposit5IsNull() || ( unexec_cash_margin_deposit_5.doubleValue() != row.getUnexecCashMarginDeposit5() ) ) {
        return false;
    }
    if ( day_repay_margin_deposit_0 == null ) {
      if ( !row.getDayRepayMarginDeposit0IsNull() )
        return false;
    } else if ( row.getDayRepayMarginDeposit0IsNull() || ( day_repay_margin_deposit_0.doubleValue() != row.getDayRepayMarginDeposit0() ) ) {
        return false;
    }
    if ( day_repay_margin_deposit_1 == null ) {
      if ( !row.getDayRepayMarginDeposit1IsNull() )
        return false;
    } else if ( row.getDayRepayMarginDeposit1IsNull() || ( day_repay_margin_deposit_1.doubleValue() != row.getDayRepayMarginDeposit1() ) ) {
        return false;
    }
    if ( day_repay_margin_deposit_2 == null ) {
      if ( !row.getDayRepayMarginDeposit2IsNull() )
        return false;
    } else if ( row.getDayRepayMarginDeposit2IsNull() || ( day_repay_margin_deposit_2.doubleValue() != row.getDayRepayMarginDeposit2() ) ) {
        return false;
    }
    if ( day_repay_margin_deposit_3 == null ) {
      if ( !row.getDayRepayMarginDeposit3IsNull() )
        return false;
    } else if ( row.getDayRepayMarginDeposit3IsNull() || ( day_repay_margin_deposit_3.doubleValue() != row.getDayRepayMarginDeposit3() ) ) {
        return false;
    }
    if ( day_repay_margin_deposit_4 == null ) {
      if ( !row.getDayRepayMarginDeposit4IsNull() )
        return false;
    } else if ( row.getDayRepayMarginDeposit4IsNull() || ( day_repay_margin_deposit_4.doubleValue() != row.getDayRepayMarginDeposit4() ) ) {
        return false;
    }
    if ( day_repay_margin_deposit_5 == null ) {
      if ( !row.getDayRepayMarginDeposit5IsNull() )
        return false;
    } else if ( row.getDayRepayMarginDeposit5IsNull() || ( day_repay_margin_deposit_5.doubleValue() != row.getDayRepayMarginDeposit5() ) ) {
        return false;
    }
    if ( day_repay_cash_margin_deposit0 == null ) {
      if ( !row.getDayRepayCashMarginDeposit0IsNull() )
        return false;
    } else if ( row.getDayRepayCashMarginDeposit0IsNull() || ( day_repay_cash_margin_deposit0.doubleValue() != row.getDayRepayCashMarginDeposit0() ) ) {
        return false;
    }
    if ( day_repay_cash_margin_deposit1 == null ) {
      if ( !row.getDayRepayCashMarginDeposit1IsNull() )
        return false;
    } else if ( row.getDayRepayCashMarginDeposit1IsNull() || ( day_repay_cash_margin_deposit1.doubleValue() != row.getDayRepayCashMarginDeposit1() ) ) {
        return false;
    }
    if ( day_repay_cash_margin_deposit2 == null ) {
      if ( !row.getDayRepayCashMarginDeposit2IsNull() )
        return false;
    } else if ( row.getDayRepayCashMarginDeposit2IsNull() || ( day_repay_cash_margin_deposit2.doubleValue() != row.getDayRepayCashMarginDeposit2() ) ) {
        return false;
    }
    if ( day_repay_cash_margin_deposit3 == null ) {
      if ( !row.getDayRepayCashMarginDeposit3IsNull() )
        return false;
    } else if ( row.getDayRepayCashMarginDeposit3IsNull() || ( day_repay_cash_margin_deposit3.doubleValue() != row.getDayRepayCashMarginDeposit3() ) ) {
        return false;
    }
    if ( day_repay_cash_margin_deposit4 == null ) {
      if ( !row.getDayRepayCashMarginDeposit4IsNull() )
        return false;
    } else if ( row.getDayRepayCashMarginDeposit4IsNull() || ( day_repay_cash_margin_deposit4.doubleValue() != row.getDayRepayCashMarginDeposit4() ) ) {
        return false;
    }
    if ( day_repay_cash_margin_deposit5 == null ) {
      if ( !row.getDayRepayCashMarginDeposit5IsNull() )
        return false;
    } else if ( row.getDayRepayCashMarginDeposit5IsNull() || ( day_repay_cash_margin_deposit5.doubleValue() != row.getDayRepayCashMarginDeposit5() ) ) {
        return false;
    }
    if ( today_repay_contract_loss == null ) {
      if ( !row.getTodayRepayContractLossIsNull() )
        return false;
    } else if ( row.getTodayRepayContractLossIsNull() || ( today_repay_contract_loss.doubleValue() != row.getTodayRepayContractLoss() ) ) {
        return false;
    }
    if ( today_repay_contract_profit == null ) {
      if ( !row.getTodayRepayContractProfitIsNull() )
        return false;
    } else if ( row.getTodayRepayContractProfitIsNull() || ( today_repay_contract_profit.doubleValue() != row.getTodayRepayContractProfit() ) ) {
        return false;
    }
    if ( today_repay_contract_pre_asset == null ) {
      if ( !row.getTodayRepayContractPreAssetIsNull() )
        return false;
    } else if ( row.getTodayRepayContractPreAssetIsNull() || ( today_repay_contract_pre_asset.doubleValue() != row.getTodayRepayContractPreAsset() ) ) {
        return false;
    }
    if ( contract_loss_designate_1 == null ) {
      if ( !row.getContractLossDesignate1IsNull() )
        return false;
    } else if ( row.getContractLossDesignate1IsNull() || ( contract_loss_designate_1.doubleValue() != row.getContractLossDesignate1() ) ) {
        return false;
    }
    if ( contract_loss_designate_2 == null ) {
      if ( !row.getContractLossDesignate2IsNull() )
        return false;
    } else if ( row.getContractLossDesignate2IsNull() || ( contract_loss_designate_2.doubleValue() != row.getContractLossDesignate2() ) ) {
        return false;
    }
    if ( contract_loss_designate_3 == null ) {
      if ( !row.getContractLossDesignate3IsNull() )
        return false;
    } else if ( row.getContractLossDesignate3IsNull() || ( contract_loss_designate_3.doubleValue() != row.getContractLossDesignate3() ) ) {
        return false;
    }
    if ( contract_loss_designate_4 == null ) {
      if ( !row.getContractLossDesignate4IsNull() )
        return false;
    } else if ( row.getContractLossDesignate4IsNull() || ( contract_loss_designate_4.doubleValue() != row.getContractLossDesignate4() ) ) {
        return false;
    }
    if ( contract_loss_designate_5 == null ) {
      if ( !row.getContractLossDesignate5IsNull() )
        return false;
    } else if ( row.getContractLossDesignate5IsNull() || ( contract_loss_designate_5.doubleValue() != row.getContractLossDesignate5() ) ) {
        return false;
    }
    if ( contract_profit_designate_1 == null ) {
      if ( !row.getContractProfitDesignate1IsNull() )
        return false;
    } else if ( row.getContractProfitDesignate1IsNull() || ( contract_profit_designate_1.doubleValue() != row.getContractProfitDesignate1() ) ) {
        return false;
    }
    if ( contract_profit_designate_2 == null ) {
      if ( !row.getContractProfitDesignate2IsNull() )
        return false;
    } else if ( row.getContractProfitDesignate2IsNull() || ( contract_profit_designate_2.doubleValue() != row.getContractProfitDesignate2() ) ) {
        return false;
    }
    if ( contract_profit_designate_3 == null ) {
      if ( !row.getContractProfitDesignate3IsNull() )
        return false;
    } else if ( row.getContractProfitDesignate3IsNull() || ( contract_profit_designate_3.doubleValue() != row.getContractProfitDesignate3() ) ) {
        return false;
    }
    if ( contract_profit_designate_4 == null ) {
      if ( !row.getContractProfitDesignate4IsNull() )
        return false;
    } else if ( row.getContractProfitDesignate4IsNull() || ( contract_profit_designate_4.doubleValue() != row.getContractProfitDesignate4() ) ) {
        return false;
    }
    if ( contract_profit_designate_5 == null ) {
      if ( !row.getContractProfitDesignate5IsNull() )
        return false;
    } else if ( row.getContractProfitDesignate5IsNull() || ( contract_profit_designate_5.doubleValue() != row.getContractProfitDesignate5() ) ) {
        return false;
    }
    if ( payment_amount_designate_0 == null ) {
      if ( !row.getPaymentAmountDesignate0IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate0IsNull() || ( payment_amount_designate_0.doubleValue() != row.getPaymentAmountDesignate0() ) ) {
        return false;
    }
    if ( payment_amount_designate_1 == null ) {
      if ( !row.getPaymentAmountDesignate1IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate1IsNull() || ( payment_amount_designate_1.doubleValue() != row.getPaymentAmountDesignate1() ) ) {
        return false;
    }
    if ( payment_amount_designate_2 == null ) {
      if ( !row.getPaymentAmountDesignate2IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate2IsNull() || ( payment_amount_designate_2.doubleValue() != row.getPaymentAmountDesignate2() ) ) {
        return false;
    }
    if ( payment_amount_designate_3 == null ) {
      if ( !row.getPaymentAmountDesignate3IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate3IsNull() || ( payment_amount_designate_3.doubleValue() != row.getPaymentAmountDesignate3() ) ) {
        return false;
    }
    if ( payment_amount_designate_4 == null ) {
      if ( !row.getPaymentAmountDesignate4IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate4IsNull() || ( payment_amount_designate_4.doubleValue() != row.getPaymentAmountDesignate4() ) ) {
        return false;
    }
    if ( payment_amount_designate_5 == null ) {
      if ( !row.getPaymentAmountDesignate5IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate5IsNull() || ( payment_amount_designate_5.doubleValue() != row.getPaymentAmountDesignate5() ) ) {
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
    if ( today_dep_fund_restraint_0 == null ) {
      if ( !row.getTodayDepFundRestraint0IsNull() )
        return false;
    } else if ( row.getTodayDepFundRestraint0IsNull() || ( today_dep_fund_restraint_0.doubleValue() != row.getTodayDepFundRestraint0() ) ) {
        return false;
    }
    if ( today_dep_fund_restraint_1 == null ) {
      if ( !row.getTodayDepFundRestraint1IsNull() )
        return false;
    } else if ( row.getTodayDepFundRestraint1IsNull() || ( today_dep_fund_restraint_1.doubleValue() != row.getTodayDepFundRestraint1() ) ) {
        return false;
    }
    if ( today_dep_fund_restraint_2 == null ) {
      if ( !row.getTodayDepFundRestraint2IsNull() )
        return false;
    } else if ( row.getTodayDepFundRestraint2IsNull() || ( today_dep_fund_restraint_2.doubleValue() != row.getTodayDepFundRestraint2() ) ) {
        return false;
    }
    if ( today_dep_fund_restraint_3 == null ) {
      if ( !row.getTodayDepFundRestraint3IsNull() )
        return false;
    } else if ( row.getTodayDepFundRestraint3IsNull() || ( today_dep_fund_restraint_3.doubleValue() != row.getTodayDepFundRestraint3() ) ) {
        return false;
    }
    if ( today_dep_fund_restraint_4 == null ) {
      if ( !row.getTodayDepFundRestraint4IsNull() )
        return false;
    } else if ( row.getTodayDepFundRestraint4IsNull() || ( today_dep_fund_restraint_4.doubleValue() != row.getTodayDepFundRestraint4() ) ) {
        return false;
    }
    if ( today_dep_fund_restraint_5 == null ) {
      if ( !row.getTodayDepFundRestraint5IsNull() )
        return false;
    } else if ( row.getTodayDepFundRestraint5IsNull() || ( today_dep_fund_restraint_5.doubleValue() != row.getTodayDepFundRestraint5() ) ) {
        return false;
    }
    if ( contract_asset_loss_1 == null ) {
      if ( !row.getContractAssetLoss1IsNull() )
        return false;
    } else if ( row.getContractAssetLoss1IsNull() || ( contract_asset_loss_1.doubleValue() != row.getContractAssetLoss1() ) ) {
        return false;
    }
    if ( contract_asset_loss_2 == null ) {
      if ( !row.getContractAssetLoss2IsNull() )
        return false;
    } else if ( row.getContractAssetLoss2IsNull() || ( contract_asset_loss_2.doubleValue() != row.getContractAssetLoss2() ) ) {
        return false;
    }
    if ( contract_asset_loss_3 == null ) {
      if ( !row.getContractAssetLoss3IsNull() )
        return false;
    } else if ( row.getContractAssetLoss3IsNull() || ( contract_asset_loss_3.doubleValue() != row.getContractAssetLoss3() ) ) {
        return false;
    }
    if ( contract_asset_loss_4 == null ) {
      if ( !row.getContractAssetLoss4IsNull() )
        return false;
    } else if ( row.getContractAssetLoss4IsNull() || ( contract_asset_loss_4.doubleValue() != row.getContractAssetLoss4() ) ) {
        return false;
    }
    if ( contract_asset_loss_5 == null ) {
      if ( !row.getContractAssetLoss5IsNull() )
        return false;
    } else if ( row.getContractAssetLoss5IsNull() || ( contract_asset_loss_5.doubleValue() != row.getContractAssetLoss5() ) ) {
        return false;
    }
    if ( contract_asset_profit_1 == null ) {
      if ( !row.getContractAssetProfit1IsNull() )
        return false;
    } else if ( row.getContractAssetProfit1IsNull() || ( contract_asset_profit_1.doubleValue() != row.getContractAssetProfit1() ) ) {
        return false;
    }
    if ( contract_asset_profit_2 == null ) {
      if ( !row.getContractAssetProfit2IsNull() )
        return false;
    } else if ( row.getContractAssetProfit2IsNull() || ( contract_asset_profit_2.doubleValue() != row.getContractAssetProfit2() ) ) {
        return false;
    }
    if ( contract_asset_profit_3 == null ) {
      if ( !row.getContractAssetProfit3IsNull() )
        return false;
    } else if ( row.getContractAssetProfit3IsNull() || ( contract_asset_profit_3.doubleValue() != row.getContractAssetProfit3() ) ) {
        return false;
    }
    if ( contract_asset_profit_4 == null ) {
      if ( !row.getContractAssetProfit4IsNull() )
        return false;
    } else if ( row.getContractAssetProfit4IsNull() || ( contract_asset_profit_4.doubleValue() != row.getContractAssetProfit4() ) ) {
        return false;
    }
    if ( contract_asset_profit_5 == null ) {
      if ( !row.getContractAssetProfit5IsNull() )
        return false;
    } else if ( row.getContractAssetProfit5IsNull() || ( contract_asset_profit_5.doubleValue() != row.getContractAssetProfit5() ) ) {
        return false;
    }
    if ( foreign_equity_asset_delivered == null ) {
      if ( !row.getForeignEquityAssetDeliveredIsNull() )
        return false;
    } else if ( row.getForeignEquityAssetDeliveredIsNull() || ( foreign_equity_asset_delivered.doubleValue() != row.getForeignEquityAssetDelivered() ) ) {
        return false;
    }
    if ( foreign_equity_asset_executed == null ) {
      if ( !row.getForeignEquityAssetExecutedIsNull() )
        return false;
    } else if ( row.getForeignEquityAssetExecutedIsNull() || ( foreign_equity_asset_executed.doubleValue() != row.getForeignEquityAssetExecuted() ) ) {
        return false;
    }
    if ( today_repay_contract_amount == null ) {
      if ( !row.getTodayRepayContractAmountIsNull() )
        return false;
    } else if ( row.getTodayRepayContractAmountIsNull() || ( today_repay_contract_amount.doubleValue() != row.getTodayRepayContractAmount() ) ) {
        return false;
    }
    if ( substitute_asset_old_day_value == null ) {
      if ( !row.getSubstituteAssetOldDayValueIsNull() )
        return false;
    } else if ( row.getSubstituteAssetOldDayValueIsNull() || ( substitute_asset_old_day_value.doubleValue() != row.getSubstituteAssetOldDayValue() ) ) {
        return false;
    }
    if ( setup_fee_1 == null ) {
      if ( !row.getSetupFee1IsNull() )
        return false;
    } else if ( row.getSetupFee1IsNull() || ( setup_fee_1.doubleValue() != row.getSetupFee1() ) ) {
        return false;
    }
    if ( setup_fee_2 == null ) {
      if ( !row.getSetupFee2IsNull() )
        return false;
    } else if ( row.getSetupFee2IsNull() || ( setup_fee_2.doubleValue() != row.getSetupFee2() ) ) {
        return false;
    }
    if ( setup_fee_3 == null ) {
      if ( !row.getSetupFee3IsNull() )
        return false;
    } else if ( row.getSetupFee3IsNull() || ( setup_fee_3.doubleValue() != row.getSetupFee3() ) ) {
        return false;
    }
    if ( setup_fee_4 == null ) {
      if ( !row.getSetupFee4IsNull() )
        return false;
    } else if ( row.getSetupFee4IsNull() || ( setup_fee_4.doubleValue() != row.getSetupFee4() ) ) {
        return false;
    }
    if ( setup_fee_5 == null ) {
      if ( !row.getSetupFee5IsNull() )
        return false;
    } else if ( row.getSetupFee5IsNull() || ( setup_fee_5.doubleValue() != row.getSetupFee5() ) ) {
        return false;
    }
    if ( contract_interest_loss_1 == null ) {
      if ( !row.getContractInterestLoss1IsNull() )
        return false;
    } else if ( row.getContractInterestLoss1IsNull() || ( contract_interest_loss_1.doubleValue() != row.getContractInterestLoss1() ) ) {
        return false;
    }
    if ( contract_interest_loss_2 == null ) {
      if ( !row.getContractInterestLoss2IsNull() )
        return false;
    } else if ( row.getContractInterestLoss2IsNull() || ( contract_interest_loss_2.doubleValue() != row.getContractInterestLoss2() ) ) {
        return false;
    }
    if ( contract_interest_loss_3 == null ) {
      if ( !row.getContractInterestLoss3IsNull() )
        return false;
    } else if ( row.getContractInterestLoss3IsNull() || ( contract_interest_loss_3.doubleValue() != row.getContractInterestLoss3() ) ) {
        return false;
    }
    if ( contract_interest_loss_4 == null ) {
      if ( !row.getContractInterestLoss4IsNull() )
        return false;
    } else if ( row.getContractInterestLoss4IsNull() || ( contract_interest_loss_4.doubleValue() != row.getContractInterestLoss4() ) ) {
        return false;
    }
    if ( contract_interest_loss_5 == null ) {
      if ( !row.getContractInterestLoss5IsNull() )
        return false;
    } else if ( row.getContractInterestLoss5IsNull() || ( contract_interest_loss_5.doubleValue() != row.getContractInterestLoss5() ) ) {
        return false;
    }
    if ( contract_interest_profit_1 == null ) {
      if ( !row.getContractInterestProfit1IsNull() )
        return false;
    } else if ( row.getContractInterestProfit1IsNull() || ( contract_interest_profit_1.doubleValue() != row.getContractInterestProfit1() ) ) {
        return false;
    }
    if ( contract_interest_profit_2 == null ) {
      if ( !row.getContractInterestProfit2IsNull() )
        return false;
    } else if ( row.getContractInterestProfit2IsNull() || ( contract_interest_profit_2.doubleValue() != row.getContractInterestProfit2() ) ) {
        return false;
    }
    if ( contract_interest_profit_3 == null ) {
      if ( !row.getContractInterestProfit3IsNull() )
        return false;
    } else if ( row.getContractInterestProfit3IsNull() || ( contract_interest_profit_3.doubleValue() != row.getContractInterestProfit3() ) ) {
        return false;
    }
    if ( contract_interest_profit_4 == null ) {
      if ( !row.getContractInterestProfit4IsNull() )
        return false;
    } else if ( row.getContractInterestProfit4IsNull() || ( contract_interest_profit_4.doubleValue() != row.getContractInterestProfit4() ) ) {
        return false;
    }
    if ( contract_interest_profit_5 == null ) {
      if ( !row.getContractInterestProfit5IsNull() )
        return false;
    } else if ( row.getContractInterestProfit5IsNull() || ( contract_interest_profit_5.doubleValue() != row.getContractInterestProfit5() ) ) {
        return false;
    }
    if ( contract_other_cost_1 == null ) {
      if ( !row.getContractOtherCost1IsNull() )
        return false;
    } else if ( row.getContractOtherCost1IsNull() || ( contract_other_cost_1.doubleValue() != row.getContractOtherCost1() ) ) {
        return false;
    }
    if ( contract_other_cost_2 == null ) {
      if ( !row.getContractOtherCost2IsNull() )
        return false;
    } else if ( row.getContractOtherCost2IsNull() || ( contract_other_cost_2.doubleValue() != row.getContractOtherCost2() ) ) {
        return false;
    }
    if ( contract_other_cost_3 == null ) {
      if ( !row.getContractOtherCost3IsNull() )
        return false;
    } else if ( row.getContractOtherCost3IsNull() || ( contract_other_cost_3.doubleValue() != row.getContractOtherCost3() ) ) {
        return false;
    }
    if ( contract_other_cost_4 == null ) {
      if ( !row.getContractOtherCost4IsNull() )
        return false;
    } else if ( row.getContractOtherCost4IsNull() || ( contract_other_cost_4.doubleValue() != row.getContractOtherCost4() ) ) {
        return false;
    }
    if ( contract_other_cost_5 == null ) {
      if ( !row.getContractOtherCost5IsNull() )
        return false;
    } else if ( row.getContractOtherCost5IsNull() || ( contract_other_cost_5.doubleValue() != row.getContractOtherCost5() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) calc_result_margin_id)
        + ((int) account_id)
        + (equity_asset_delivered!=null? equity_asset_delivered.hashCode(): 0) 
        + (equity_asset_executed!=null? equity_asset_executed.hashCode(): 0) 
        + (mini_stock_asset_delivered!=null? mini_stock_asset_delivered.hashCode(): 0) 
        + (mini_stock_asset_executed!=null? mini_stock_asset_executed.hashCode(): 0) 
        + (ruito_asset_delivered!=null? ruito_asset_delivered.hashCode(): 0) 
        + (ruito_asset_executed!=null? ruito_asset_executed.hashCode(): 0) 
        + (mutual_fund_asset_delivered!=null? mutual_fund_asset_delivered.hashCode(): 0) 
        + (mutual_fund_asset_executed!=null? mutual_fund_asset_executed.hashCode(): 0) 
        + (bond_asset_delivered!=null? bond_asset_delivered.hashCode(): 0) 
        + (bond_asset_executed!=null? bond_asset_executed.hashCode(): 0) 
        + (unexec_substi_security_asset_0!=null? unexec_substi_security_asset_0.hashCode(): 0) 
        + (unexec_substi_security_asset_1!=null? unexec_substi_security_asset_1.hashCode(): 0) 
        + (unexec_substi_security_asset_2!=null? unexec_substi_security_asset_2.hashCode(): 0) 
        + (unexec_substi_security_asset_3!=null? unexec_substi_security_asset_3.hashCode(): 0) 
        + (unexec_substi_security_asset_4!=null? unexec_substi_security_asset_4.hashCode(): 0) 
        + (unexec_substi_security_asset_5!=null? unexec_substi_security_asset_5.hashCode(): 0) 
        + (contract_asset_loss!=null? contract_asset_loss.hashCode(): 0) 
        + (contract_asset_profit!=null? contract_asset_profit.hashCode(): 0) 
        + (setup_fee!=null? setup_fee.hashCode(): 0) 
        + (contract_interest_loss!=null? contract_interest_loss.hashCode(): 0) 
        + (contract_interest_profit!=null? contract_interest_profit.hashCode(): 0) 
        + (contract_other_cost!=null? contract_other_cost.hashCode(): 0) 
        + (unexec_contract_amount_0!=null? unexec_contract_amount_0.hashCode(): 0) 
        + (unexec_contract_amount_1!=null? unexec_contract_amount_1.hashCode(): 0) 
        + (unexec_contract_amount_2!=null? unexec_contract_amount_2.hashCode(): 0) 
        + (unexec_contract_amount_3!=null? unexec_contract_amount_3.hashCode(): 0) 
        + (unexec_contract_amount_4!=null? unexec_contract_amount_4.hashCode(): 0) 
        + (unexec_contract_amount_5!=null? unexec_contract_amount_5.hashCode(): 0) 
        + (unexec_margin_deposit_0!=null? unexec_margin_deposit_0.hashCode(): 0) 
        + (unexec_margin_deposit_1!=null? unexec_margin_deposit_1.hashCode(): 0) 
        + (unexec_margin_deposit_2!=null? unexec_margin_deposit_2.hashCode(): 0) 
        + (unexec_margin_deposit_3!=null? unexec_margin_deposit_3.hashCode(): 0) 
        + (unexec_margin_deposit_4!=null? unexec_margin_deposit_4.hashCode(): 0) 
        + (unexec_margin_deposit_5!=null? unexec_margin_deposit_5.hashCode(): 0) 
        + (unexec_cash_margin_deposit_0!=null? unexec_cash_margin_deposit_0.hashCode(): 0) 
        + (unexec_cash_margin_deposit_1!=null? unexec_cash_margin_deposit_1.hashCode(): 0) 
        + (unexec_cash_margin_deposit_2!=null? unexec_cash_margin_deposit_2.hashCode(): 0) 
        + (unexec_cash_margin_deposit_3!=null? unexec_cash_margin_deposit_3.hashCode(): 0) 
        + (unexec_cash_margin_deposit_4!=null? unexec_cash_margin_deposit_4.hashCode(): 0) 
        + (unexec_cash_margin_deposit_5!=null? unexec_cash_margin_deposit_5.hashCode(): 0) 
        + (day_repay_margin_deposit_0!=null? day_repay_margin_deposit_0.hashCode(): 0) 
        + (day_repay_margin_deposit_1!=null? day_repay_margin_deposit_1.hashCode(): 0) 
        + (day_repay_margin_deposit_2!=null? day_repay_margin_deposit_2.hashCode(): 0) 
        + (day_repay_margin_deposit_3!=null? day_repay_margin_deposit_3.hashCode(): 0) 
        + (day_repay_margin_deposit_4!=null? day_repay_margin_deposit_4.hashCode(): 0) 
        + (day_repay_margin_deposit_5!=null? day_repay_margin_deposit_5.hashCode(): 0) 
        + (day_repay_cash_margin_deposit0!=null? day_repay_cash_margin_deposit0.hashCode(): 0) 
        + (day_repay_cash_margin_deposit1!=null? day_repay_cash_margin_deposit1.hashCode(): 0) 
        + (day_repay_cash_margin_deposit2!=null? day_repay_cash_margin_deposit2.hashCode(): 0) 
        + (day_repay_cash_margin_deposit3!=null? day_repay_cash_margin_deposit3.hashCode(): 0) 
        + (day_repay_cash_margin_deposit4!=null? day_repay_cash_margin_deposit4.hashCode(): 0) 
        + (day_repay_cash_margin_deposit5!=null? day_repay_cash_margin_deposit5.hashCode(): 0) 
        + (today_repay_contract_loss!=null? today_repay_contract_loss.hashCode(): 0) 
        + (today_repay_contract_profit!=null? today_repay_contract_profit.hashCode(): 0) 
        + (today_repay_contract_pre_asset!=null? today_repay_contract_pre_asset.hashCode(): 0) 
        + (contract_loss_designate_1!=null? contract_loss_designate_1.hashCode(): 0) 
        + (contract_loss_designate_2!=null? contract_loss_designate_2.hashCode(): 0) 
        + (contract_loss_designate_3!=null? contract_loss_designate_3.hashCode(): 0) 
        + (contract_loss_designate_4!=null? contract_loss_designate_4.hashCode(): 0) 
        + (contract_loss_designate_5!=null? contract_loss_designate_5.hashCode(): 0) 
        + (contract_profit_designate_1!=null? contract_profit_designate_1.hashCode(): 0) 
        + (contract_profit_designate_2!=null? contract_profit_designate_2.hashCode(): 0) 
        + (contract_profit_designate_3!=null? contract_profit_designate_3.hashCode(): 0) 
        + (contract_profit_designate_4!=null? contract_profit_designate_4.hashCode(): 0) 
        + (contract_profit_designate_5!=null? contract_profit_designate_5.hashCode(): 0) 
        + (payment_amount_designate_0!=null? payment_amount_designate_0.hashCode(): 0) 
        + (payment_amount_designate_1!=null? payment_amount_designate_1.hashCode(): 0) 
        + (payment_amount_designate_2!=null? payment_amount_designate_2.hashCode(): 0) 
        + (payment_amount_designate_3!=null? payment_amount_designate_3.hashCode(): 0) 
        + (payment_amount_designate_4!=null? payment_amount_designate_4.hashCode(): 0) 
        + (payment_amount_designate_5!=null? payment_amount_designate_5.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (today_dep_fund_restraint_0!=null? today_dep_fund_restraint_0.hashCode(): 0) 
        + (today_dep_fund_restraint_1!=null? today_dep_fund_restraint_1.hashCode(): 0) 
        + (today_dep_fund_restraint_2!=null? today_dep_fund_restraint_2.hashCode(): 0) 
        + (today_dep_fund_restraint_3!=null? today_dep_fund_restraint_3.hashCode(): 0) 
        + (today_dep_fund_restraint_4!=null? today_dep_fund_restraint_4.hashCode(): 0) 
        + (today_dep_fund_restraint_5!=null? today_dep_fund_restraint_5.hashCode(): 0) 
        + (contract_asset_loss_1!=null? contract_asset_loss_1.hashCode(): 0) 
        + (contract_asset_loss_2!=null? contract_asset_loss_2.hashCode(): 0) 
        + (contract_asset_loss_3!=null? contract_asset_loss_3.hashCode(): 0) 
        + (contract_asset_loss_4!=null? contract_asset_loss_4.hashCode(): 0) 
        + (contract_asset_loss_5!=null? contract_asset_loss_5.hashCode(): 0) 
        + (contract_asset_profit_1!=null? contract_asset_profit_1.hashCode(): 0) 
        + (contract_asset_profit_2!=null? contract_asset_profit_2.hashCode(): 0) 
        + (contract_asset_profit_3!=null? contract_asset_profit_3.hashCode(): 0) 
        + (contract_asset_profit_4!=null? contract_asset_profit_4.hashCode(): 0) 
        + (contract_asset_profit_5!=null? contract_asset_profit_5.hashCode(): 0) 
        + (foreign_equity_asset_delivered!=null? foreign_equity_asset_delivered.hashCode(): 0) 
        + (foreign_equity_asset_executed!=null? foreign_equity_asset_executed.hashCode(): 0) 
        + (today_repay_contract_amount!=null? today_repay_contract_amount.hashCode(): 0) 
        + (substitute_asset_old_day_value!=null? substitute_asset_old_day_value.hashCode(): 0) 
        + (setup_fee_1!=null? setup_fee_1.hashCode(): 0) 
        + (setup_fee_2!=null? setup_fee_2.hashCode(): 0) 
        + (setup_fee_3!=null? setup_fee_3.hashCode(): 0) 
        + (setup_fee_4!=null? setup_fee_4.hashCode(): 0) 
        + (setup_fee_5!=null? setup_fee_5.hashCode(): 0) 
        + (contract_interest_loss_1!=null? contract_interest_loss_1.hashCode(): 0) 
        + (contract_interest_loss_2!=null? contract_interest_loss_2.hashCode(): 0) 
        + (contract_interest_loss_3!=null? contract_interest_loss_3.hashCode(): 0) 
        + (contract_interest_loss_4!=null? contract_interest_loss_4.hashCode(): 0) 
        + (contract_interest_loss_5!=null? contract_interest_loss_5.hashCode(): 0) 
        + (contract_interest_profit_1!=null? contract_interest_profit_1.hashCode(): 0) 
        + (contract_interest_profit_2!=null? contract_interest_profit_2.hashCode(): 0) 
        + (contract_interest_profit_3!=null? contract_interest_profit_3.hashCode(): 0) 
        + (contract_interest_profit_4!=null? contract_interest_profit_4.hashCode(): 0) 
        + (contract_interest_profit_5!=null? contract_interest_profit_5.hashCode(): 0) 
        + (contract_other_cost_1!=null? contract_other_cost_1.hashCode(): 0) 
        + (contract_other_cost_2!=null? contract_other_cost_2.hashCode(): 0) 
        + (contract_other_cost_3!=null? contract_other_cost_3.hashCode(): 0) 
        + (contract_other_cost_4!=null? contract_other_cost_4.hashCode(): 0) 
        + (contract_other_cost_5!=null? contract_other_cost_5.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("calc_result_margin_id",new Long(calc_result_margin_id));
		map.put("account_id",new Long(account_id));
		if ( equity_asset_delivered_is_set )
			map.put("equity_asset_delivered",equity_asset_delivered);
		if ( equity_asset_executed_is_set )
			map.put("equity_asset_executed",equity_asset_executed);
		if ( mini_stock_asset_delivered_is_set )
			map.put("mini_stock_asset_delivered",mini_stock_asset_delivered);
		if ( mini_stock_asset_executed_is_set )
			map.put("mini_stock_asset_executed",mini_stock_asset_executed);
		if ( ruito_asset_delivered_is_set )
			map.put("ruito_asset_delivered",ruito_asset_delivered);
		if ( ruito_asset_executed_is_set )
			map.put("ruito_asset_executed",ruito_asset_executed);
		if ( mutual_fund_asset_delivered_is_set )
			map.put("mutual_fund_asset_delivered",mutual_fund_asset_delivered);
		if ( mutual_fund_asset_executed_is_set )
			map.put("mutual_fund_asset_executed",mutual_fund_asset_executed);
		if ( bond_asset_delivered_is_set )
			map.put("bond_asset_delivered",bond_asset_delivered);
		if ( bond_asset_executed_is_set )
			map.put("bond_asset_executed",bond_asset_executed);
		if ( unexec_substi_security_asset_0_is_set )
			map.put("unexec_substi_security_asset_0",unexec_substi_security_asset_0);
		if ( unexec_substi_security_asset_1_is_set )
			map.put("unexec_substi_security_asset_1",unexec_substi_security_asset_1);
		if ( unexec_substi_security_asset_2_is_set )
			map.put("unexec_substi_security_asset_2",unexec_substi_security_asset_2);
		if ( unexec_substi_security_asset_3_is_set )
			map.put("unexec_substi_security_asset_3",unexec_substi_security_asset_3);
		if ( unexec_substi_security_asset_4_is_set )
			map.put("unexec_substi_security_asset_4",unexec_substi_security_asset_4);
		if ( unexec_substi_security_asset_5_is_set )
			map.put("unexec_substi_security_asset_5",unexec_substi_security_asset_5);
		if ( contract_asset_loss_is_set )
			map.put("contract_asset_loss",contract_asset_loss);
		if ( contract_asset_profit_is_set )
			map.put("contract_asset_profit",contract_asset_profit);
		if ( setup_fee_is_set )
			map.put("setup_fee",setup_fee);
		if ( contract_interest_loss_is_set )
			map.put("contract_interest_loss",contract_interest_loss);
		if ( contract_interest_profit_is_set )
			map.put("contract_interest_profit",contract_interest_profit);
		if ( contract_other_cost_is_set )
			map.put("contract_other_cost",contract_other_cost);
		if ( unexec_contract_amount_0_is_set )
			map.put("unexec_contract_amount_0",unexec_contract_amount_0);
		if ( unexec_contract_amount_1_is_set )
			map.put("unexec_contract_amount_1",unexec_contract_amount_1);
		if ( unexec_contract_amount_2_is_set )
			map.put("unexec_contract_amount_2",unexec_contract_amount_2);
		if ( unexec_contract_amount_3_is_set )
			map.put("unexec_contract_amount_3",unexec_contract_amount_3);
		if ( unexec_contract_amount_4_is_set )
			map.put("unexec_contract_amount_4",unexec_contract_amount_4);
		if ( unexec_contract_amount_5_is_set )
			map.put("unexec_contract_amount_5",unexec_contract_amount_5);
		if ( unexec_margin_deposit_0_is_set )
			map.put("unexec_margin_deposit_0",unexec_margin_deposit_0);
		if ( unexec_margin_deposit_1_is_set )
			map.put("unexec_margin_deposit_1",unexec_margin_deposit_1);
		if ( unexec_margin_deposit_2_is_set )
			map.put("unexec_margin_deposit_2",unexec_margin_deposit_2);
		if ( unexec_margin_deposit_3_is_set )
			map.put("unexec_margin_deposit_3",unexec_margin_deposit_3);
		if ( unexec_margin_deposit_4_is_set )
			map.put("unexec_margin_deposit_4",unexec_margin_deposit_4);
		if ( unexec_margin_deposit_5_is_set )
			map.put("unexec_margin_deposit_5",unexec_margin_deposit_5);
		if ( unexec_cash_margin_deposit_0_is_set )
			map.put("unexec_cash_margin_deposit_0",unexec_cash_margin_deposit_0);
		if ( unexec_cash_margin_deposit_1_is_set )
			map.put("unexec_cash_margin_deposit_1",unexec_cash_margin_deposit_1);
		if ( unexec_cash_margin_deposit_2_is_set )
			map.put("unexec_cash_margin_deposit_2",unexec_cash_margin_deposit_2);
		if ( unexec_cash_margin_deposit_3_is_set )
			map.put("unexec_cash_margin_deposit_3",unexec_cash_margin_deposit_3);
		if ( unexec_cash_margin_deposit_4_is_set )
			map.put("unexec_cash_margin_deposit_4",unexec_cash_margin_deposit_4);
		if ( unexec_cash_margin_deposit_5_is_set )
			map.put("unexec_cash_margin_deposit_5",unexec_cash_margin_deposit_5);
		if ( day_repay_margin_deposit_0_is_set )
			map.put("day_repay_margin_deposit_0",day_repay_margin_deposit_0);
		if ( day_repay_margin_deposit_1_is_set )
			map.put("day_repay_margin_deposit_1",day_repay_margin_deposit_1);
		if ( day_repay_margin_deposit_2_is_set )
			map.put("day_repay_margin_deposit_2",day_repay_margin_deposit_2);
		if ( day_repay_margin_deposit_3_is_set )
			map.put("day_repay_margin_deposit_3",day_repay_margin_deposit_3);
		if ( day_repay_margin_deposit_4_is_set )
			map.put("day_repay_margin_deposit_4",day_repay_margin_deposit_4);
		if ( day_repay_margin_deposit_5_is_set )
			map.put("day_repay_margin_deposit_5",day_repay_margin_deposit_5);
		if ( day_repay_cash_margin_deposit0_is_set )
			map.put("day_repay_cash_margin_deposit0",day_repay_cash_margin_deposit0);
		if ( day_repay_cash_margin_deposit1_is_set )
			map.put("day_repay_cash_margin_deposit1",day_repay_cash_margin_deposit1);
		if ( day_repay_cash_margin_deposit2_is_set )
			map.put("day_repay_cash_margin_deposit2",day_repay_cash_margin_deposit2);
		if ( day_repay_cash_margin_deposit3_is_set )
			map.put("day_repay_cash_margin_deposit3",day_repay_cash_margin_deposit3);
		if ( day_repay_cash_margin_deposit4_is_set )
			map.put("day_repay_cash_margin_deposit4",day_repay_cash_margin_deposit4);
		if ( day_repay_cash_margin_deposit5_is_set )
			map.put("day_repay_cash_margin_deposit5",day_repay_cash_margin_deposit5);
		if ( today_repay_contract_loss_is_set )
			map.put("today_repay_contract_loss",today_repay_contract_loss);
		if ( today_repay_contract_profit_is_set )
			map.put("today_repay_contract_profit",today_repay_contract_profit);
		if ( today_repay_contract_pre_asset_is_set )
			map.put("today_repay_contract_pre_asset",today_repay_contract_pre_asset);
		if ( contract_loss_designate_1_is_set )
			map.put("contract_loss_designate_1",contract_loss_designate_1);
		if ( contract_loss_designate_2_is_set )
			map.put("contract_loss_designate_2",contract_loss_designate_2);
		if ( contract_loss_designate_3_is_set )
			map.put("contract_loss_designate_3",contract_loss_designate_3);
		if ( contract_loss_designate_4_is_set )
			map.put("contract_loss_designate_4",contract_loss_designate_4);
		if ( contract_loss_designate_5_is_set )
			map.put("contract_loss_designate_5",contract_loss_designate_5);
		if ( contract_profit_designate_1_is_set )
			map.put("contract_profit_designate_1",contract_profit_designate_1);
		if ( contract_profit_designate_2_is_set )
			map.put("contract_profit_designate_2",contract_profit_designate_2);
		if ( contract_profit_designate_3_is_set )
			map.put("contract_profit_designate_3",contract_profit_designate_3);
		if ( contract_profit_designate_4_is_set )
			map.put("contract_profit_designate_4",contract_profit_designate_4);
		if ( contract_profit_designate_5_is_set )
			map.put("contract_profit_designate_5",contract_profit_designate_5);
		if ( payment_amount_designate_0_is_set )
			map.put("payment_amount_designate_0",payment_amount_designate_0);
		if ( payment_amount_designate_1_is_set )
			map.put("payment_amount_designate_1",payment_amount_designate_1);
		if ( payment_amount_designate_2_is_set )
			map.put("payment_amount_designate_2",payment_amount_designate_2);
		if ( payment_amount_designate_3_is_set )
			map.put("payment_amount_designate_3",payment_amount_designate_3);
		if ( payment_amount_designate_4_is_set )
			map.put("payment_amount_designate_4",payment_amount_designate_4);
		if ( payment_amount_designate_5_is_set )
			map.put("payment_amount_designate_5",payment_amount_designate_5);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( today_dep_fund_restraint_0_is_set )
			map.put("today_dep_fund_restraint_0",today_dep_fund_restraint_0);
		if ( today_dep_fund_restraint_1_is_set )
			map.put("today_dep_fund_restraint_1",today_dep_fund_restraint_1);
		if ( today_dep_fund_restraint_2_is_set )
			map.put("today_dep_fund_restraint_2",today_dep_fund_restraint_2);
		if ( today_dep_fund_restraint_3_is_set )
			map.put("today_dep_fund_restraint_3",today_dep_fund_restraint_3);
		if ( today_dep_fund_restraint_4_is_set )
			map.put("today_dep_fund_restraint_4",today_dep_fund_restraint_4);
		if ( today_dep_fund_restraint_5_is_set )
			map.put("today_dep_fund_restraint_5",today_dep_fund_restraint_5);
		if ( contract_asset_loss_1 != null )
			map.put("contract_asset_loss_1",contract_asset_loss_1);
		if ( contract_asset_loss_2 != null )
			map.put("contract_asset_loss_2",contract_asset_loss_2);
		if ( contract_asset_loss_3 != null )
			map.put("contract_asset_loss_3",contract_asset_loss_3);
		if ( contract_asset_loss_4 != null )
			map.put("contract_asset_loss_4",contract_asset_loss_4);
		if ( contract_asset_loss_5 != null )
			map.put("contract_asset_loss_5",contract_asset_loss_5);
		if ( contract_asset_profit_1 != null )
			map.put("contract_asset_profit_1",contract_asset_profit_1);
		if ( contract_asset_profit_2 != null )
			map.put("contract_asset_profit_2",contract_asset_profit_2);
		if ( contract_asset_profit_3 != null )
			map.put("contract_asset_profit_3",contract_asset_profit_3);
		if ( contract_asset_profit_4 != null )
			map.put("contract_asset_profit_4",contract_asset_profit_4);
		if ( contract_asset_profit_5 != null )
			map.put("contract_asset_profit_5",contract_asset_profit_5);
		if ( foreign_equity_asset_delivered != null )
			map.put("foreign_equity_asset_delivered",foreign_equity_asset_delivered);
		if ( foreign_equity_asset_executed != null )
			map.put("foreign_equity_asset_executed",foreign_equity_asset_executed);
		if ( today_repay_contract_amount != null )
			map.put("today_repay_contract_amount",today_repay_contract_amount);
		if ( substitute_asset_old_day_value != null )
			map.put("substitute_asset_old_day_value",substitute_asset_old_day_value);
		if ( setup_fee_1 != null )
			map.put("setup_fee_1",setup_fee_1);
		if ( setup_fee_2 != null )
			map.put("setup_fee_2",setup_fee_2);
		if ( setup_fee_3 != null )
			map.put("setup_fee_3",setup_fee_3);
		if ( setup_fee_4 != null )
			map.put("setup_fee_4",setup_fee_4);
		if ( setup_fee_5 != null )
			map.put("setup_fee_5",setup_fee_5);
		if ( contract_interest_loss_1 != null )
			map.put("contract_interest_loss_1",contract_interest_loss_1);
		if ( contract_interest_loss_2 != null )
			map.put("contract_interest_loss_2",contract_interest_loss_2);
		if ( contract_interest_loss_3 != null )
			map.put("contract_interest_loss_3",contract_interest_loss_3);
		if ( contract_interest_loss_4 != null )
			map.put("contract_interest_loss_4",contract_interest_loss_4);
		if ( contract_interest_loss_5 != null )
			map.put("contract_interest_loss_5",contract_interest_loss_5);
		if ( contract_interest_profit_1 != null )
			map.put("contract_interest_profit_1",contract_interest_profit_1);
		if ( contract_interest_profit_2 != null )
			map.put("contract_interest_profit_2",contract_interest_profit_2);
		if ( contract_interest_profit_3 != null )
			map.put("contract_interest_profit_3",contract_interest_profit_3);
		if ( contract_interest_profit_4 != null )
			map.put("contract_interest_profit_4",contract_interest_profit_4);
		if ( contract_interest_profit_5 != null )
			map.put("contract_interest_profit_5",contract_interest_profit_5);
		if ( contract_other_cost_1 != null )
			map.put("contract_other_cost_1",contract_other_cost_1);
		if ( contract_other_cost_2 != null )
			map.put("contract_other_cost_2",contract_other_cost_2);
		if ( contract_other_cost_3 != null )
			map.put("contract_other_cost_3",contract_other_cost_3);
		if ( contract_other_cost_4 != null )
			map.put("contract_other_cost_4",contract_other_cost_4);
		if ( contract_other_cost_5 != null )
			map.put("contract_other_cost_5",contract_other_cost_5);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( equity_asset_delivered_is_modified )
			map.put("equity_asset_delivered",equity_asset_delivered);
		if ( equity_asset_executed_is_modified )
			map.put("equity_asset_executed",equity_asset_executed);
		if ( mini_stock_asset_delivered_is_modified )
			map.put("mini_stock_asset_delivered",mini_stock_asset_delivered);
		if ( mini_stock_asset_executed_is_modified )
			map.put("mini_stock_asset_executed",mini_stock_asset_executed);
		if ( ruito_asset_delivered_is_modified )
			map.put("ruito_asset_delivered",ruito_asset_delivered);
		if ( ruito_asset_executed_is_modified )
			map.put("ruito_asset_executed",ruito_asset_executed);
		if ( mutual_fund_asset_delivered_is_modified )
			map.put("mutual_fund_asset_delivered",mutual_fund_asset_delivered);
		if ( mutual_fund_asset_executed_is_modified )
			map.put("mutual_fund_asset_executed",mutual_fund_asset_executed);
		if ( bond_asset_delivered_is_modified )
			map.put("bond_asset_delivered",bond_asset_delivered);
		if ( bond_asset_executed_is_modified )
			map.put("bond_asset_executed",bond_asset_executed);
		if ( unexec_substi_security_asset_0_is_modified )
			map.put("unexec_substi_security_asset_0",unexec_substi_security_asset_0);
		if ( unexec_substi_security_asset_1_is_modified )
			map.put("unexec_substi_security_asset_1",unexec_substi_security_asset_1);
		if ( unexec_substi_security_asset_2_is_modified )
			map.put("unexec_substi_security_asset_2",unexec_substi_security_asset_2);
		if ( unexec_substi_security_asset_3_is_modified )
			map.put("unexec_substi_security_asset_3",unexec_substi_security_asset_3);
		if ( unexec_substi_security_asset_4_is_modified )
			map.put("unexec_substi_security_asset_4",unexec_substi_security_asset_4);
		if ( unexec_substi_security_asset_5_is_modified )
			map.put("unexec_substi_security_asset_5",unexec_substi_security_asset_5);
		if ( contract_asset_loss_is_modified )
			map.put("contract_asset_loss",contract_asset_loss);
		if ( contract_asset_profit_is_modified )
			map.put("contract_asset_profit",contract_asset_profit);
		if ( setup_fee_is_modified )
			map.put("setup_fee",setup_fee);
		if ( contract_interest_loss_is_modified )
			map.put("contract_interest_loss",contract_interest_loss);
		if ( contract_interest_profit_is_modified )
			map.put("contract_interest_profit",contract_interest_profit);
		if ( contract_other_cost_is_modified )
			map.put("contract_other_cost",contract_other_cost);
		if ( unexec_contract_amount_0_is_modified )
			map.put("unexec_contract_amount_0",unexec_contract_amount_0);
		if ( unexec_contract_amount_1_is_modified )
			map.put("unexec_contract_amount_1",unexec_contract_amount_1);
		if ( unexec_contract_amount_2_is_modified )
			map.put("unexec_contract_amount_2",unexec_contract_amount_2);
		if ( unexec_contract_amount_3_is_modified )
			map.put("unexec_contract_amount_3",unexec_contract_amount_3);
		if ( unexec_contract_amount_4_is_modified )
			map.put("unexec_contract_amount_4",unexec_contract_amount_4);
		if ( unexec_contract_amount_5_is_modified )
			map.put("unexec_contract_amount_5",unexec_contract_amount_5);
		if ( unexec_margin_deposit_0_is_modified )
			map.put("unexec_margin_deposit_0",unexec_margin_deposit_0);
		if ( unexec_margin_deposit_1_is_modified )
			map.put("unexec_margin_deposit_1",unexec_margin_deposit_1);
		if ( unexec_margin_deposit_2_is_modified )
			map.put("unexec_margin_deposit_2",unexec_margin_deposit_2);
		if ( unexec_margin_deposit_3_is_modified )
			map.put("unexec_margin_deposit_3",unexec_margin_deposit_3);
		if ( unexec_margin_deposit_4_is_modified )
			map.put("unexec_margin_deposit_4",unexec_margin_deposit_4);
		if ( unexec_margin_deposit_5_is_modified )
			map.put("unexec_margin_deposit_5",unexec_margin_deposit_5);
		if ( unexec_cash_margin_deposit_0_is_modified )
			map.put("unexec_cash_margin_deposit_0",unexec_cash_margin_deposit_0);
		if ( unexec_cash_margin_deposit_1_is_modified )
			map.put("unexec_cash_margin_deposit_1",unexec_cash_margin_deposit_1);
		if ( unexec_cash_margin_deposit_2_is_modified )
			map.put("unexec_cash_margin_deposit_2",unexec_cash_margin_deposit_2);
		if ( unexec_cash_margin_deposit_3_is_modified )
			map.put("unexec_cash_margin_deposit_3",unexec_cash_margin_deposit_3);
		if ( unexec_cash_margin_deposit_4_is_modified )
			map.put("unexec_cash_margin_deposit_4",unexec_cash_margin_deposit_4);
		if ( unexec_cash_margin_deposit_5_is_modified )
			map.put("unexec_cash_margin_deposit_5",unexec_cash_margin_deposit_5);
		if ( day_repay_margin_deposit_0_is_modified )
			map.put("day_repay_margin_deposit_0",day_repay_margin_deposit_0);
		if ( day_repay_margin_deposit_1_is_modified )
			map.put("day_repay_margin_deposit_1",day_repay_margin_deposit_1);
		if ( day_repay_margin_deposit_2_is_modified )
			map.put("day_repay_margin_deposit_2",day_repay_margin_deposit_2);
		if ( day_repay_margin_deposit_3_is_modified )
			map.put("day_repay_margin_deposit_3",day_repay_margin_deposit_3);
		if ( day_repay_margin_deposit_4_is_modified )
			map.put("day_repay_margin_deposit_4",day_repay_margin_deposit_4);
		if ( day_repay_margin_deposit_5_is_modified )
			map.put("day_repay_margin_deposit_5",day_repay_margin_deposit_5);
		if ( day_repay_cash_margin_deposit0_is_modified )
			map.put("day_repay_cash_margin_deposit0",day_repay_cash_margin_deposit0);
		if ( day_repay_cash_margin_deposit1_is_modified )
			map.put("day_repay_cash_margin_deposit1",day_repay_cash_margin_deposit1);
		if ( day_repay_cash_margin_deposit2_is_modified )
			map.put("day_repay_cash_margin_deposit2",day_repay_cash_margin_deposit2);
		if ( day_repay_cash_margin_deposit3_is_modified )
			map.put("day_repay_cash_margin_deposit3",day_repay_cash_margin_deposit3);
		if ( day_repay_cash_margin_deposit4_is_modified )
			map.put("day_repay_cash_margin_deposit4",day_repay_cash_margin_deposit4);
		if ( day_repay_cash_margin_deposit5_is_modified )
			map.put("day_repay_cash_margin_deposit5",day_repay_cash_margin_deposit5);
		if ( today_repay_contract_loss_is_modified )
			map.put("today_repay_contract_loss",today_repay_contract_loss);
		if ( today_repay_contract_profit_is_modified )
			map.put("today_repay_contract_profit",today_repay_contract_profit);
		if ( today_repay_contract_pre_asset_is_modified )
			map.put("today_repay_contract_pre_asset",today_repay_contract_pre_asset);
		if ( contract_loss_designate_1_is_modified )
			map.put("contract_loss_designate_1",contract_loss_designate_1);
		if ( contract_loss_designate_2_is_modified )
			map.put("contract_loss_designate_2",contract_loss_designate_2);
		if ( contract_loss_designate_3_is_modified )
			map.put("contract_loss_designate_3",contract_loss_designate_3);
		if ( contract_loss_designate_4_is_modified )
			map.put("contract_loss_designate_4",contract_loss_designate_4);
		if ( contract_loss_designate_5_is_modified )
			map.put("contract_loss_designate_5",contract_loss_designate_5);
		if ( contract_profit_designate_1_is_modified )
			map.put("contract_profit_designate_1",contract_profit_designate_1);
		if ( contract_profit_designate_2_is_modified )
			map.put("contract_profit_designate_2",contract_profit_designate_2);
		if ( contract_profit_designate_3_is_modified )
			map.put("contract_profit_designate_3",contract_profit_designate_3);
		if ( contract_profit_designate_4_is_modified )
			map.put("contract_profit_designate_4",contract_profit_designate_4);
		if ( contract_profit_designate_5_is_modified )
			map.put("contract_profit_designate_5",contract_profit_designate_5);
		if ( payment_amount_designate_0_is_modified )
			map.put("payment_amount_designate_0",payment_amount_designate_0);
		if ( payment_amount_designate_1_is_modified )
			map.put("payment_amount_designate_1",payment_amount_designate_1);
		if ( payment_amount_designate_2_is_modified )
			map.put("payment_amount_designate_2",payment_amount_designate_2);
		if ( payment_amount_designate_3_is_modified )
			map.put("payment_amount_designate_3",payment_amount_designate_3);
		if ( payment_amount_designate_4_is_modified )
			map.put("payment_amount_designate_4",payment_amount_designate_4);
		if ( payment_amount_designate_5_is_modified )
			map.put("payment_amount_designate_5",payment_amount_designate_5);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( today_dep_fund_restraint_0_is_modified )
			map.put("today_dep_fund_restraint_0",today_dep_fund_restraint_0);
		if ( today_dep_fund_restraint_1_is_modified )
			map.put("today_dep_fund_restraint_1",today_dep_fund_restraint_1);
		if ( today_dep_fund_restraint_2_is_modified )
			map.put("today_dep_fund_restraint_2",today_dep_fund_restraint_2);
		if ( today_dep_fund_restraint_3_is_modified )
			map.put("today_dep_fund_restraint_3",today_dep_fund_restraint_3);
		if ( today_dep_fund_restraint_4_is_modified )
			map.put("today_dep_fund_restraint_4",today_dep_fund_restraint_4);
		if ( today_dep_fund_restraint_5_is_modified )
			map.put("today_dep_fund_restraint_5",today_dep_fund_restraint_5);
		if ( contract_asset_loss_1_is_modified )
			map.put("contract_asset_loss_1",contract_asset_loss_1);
		if ( contract_asset_loss_2_is_modified )
			map.put("contract_asset_loss_2",contract_asset_loss_2);
		if ( contract_asset_loss_3_is_modified )
			map.put("contract_asset_loss_3",contract_asset_loss_3);
		if ( contract_asset_loss_4_is_modified )
			map.put("contract_asset_loss_4",contract_asset_loss_4);
		if ( contract_asset_loss_5_is_modified )
			map.put("contract_asset_loss_5",contract_asset_loss_5);
		if ( contract_asset_profit_1_is_modified )
			map.put("contract_asset_profit_1",contract_asset_profit_1);
		if ( contract_asset_profit_2_is_modified )
			map.put("contract_asset_profit_2",contract_asset_profit_2);
		if ( contract_asset_profit_3_is_modified )
			map.put("contract_asset_profit_3",contract_asset_profit_3);
		if ( contract_asset_profit_4_is_modified )
			map.put("contract_asset_profit_4",contract_asset_profit_4);
		if ( contract_asset_profit_5_is_modified )
			map.put("contract_asset_profit_5",contract_asset_profit_5);
		if ( foreign_equity_asset_delivered_is_modified )
			map.put("foreign_equity_asset_delivered",foreign_equity_asset_delivered);
		if ( foreign_equity_asset_executed_is_modified )
			map.put("foreign_equity_asset_executed",foreign_equity_asset_executed);
		if ( today_repay_contract_amount_is_modified )
			map.put("today_repay_contract_amount",today_repay_contract_amount);
		if ( substitute_asset_old_day_value_is_modified )
			map.put("substitute_asset_old_day_value",substitute_asset_old_day_value);
		if ( setup_fee_1_is_modified )
			map.put("setup_fee_1",setup_fee_1);
		if ( setup_fee_2_is_modified )
			map.put("setup_fee_2",setup_fee_2);
		if ( setup_fee_3_is_modified )
			map.put("setup_fee_3",setup_fee_3);
		if ( setup_fee_4_is_modified )
			map.put("setup_fee_4",setup_fee_4);
		if ( setup_fee_5_is_modified )
			map.put("setup_fee_5",setup_fee_5);
		if ( contract_interest_loss_1_is_modified )
			map.put("contract_interest_loss_1",contract_interest_loss_1);
		if ( contract_interest_loss_2_is_modified )
			map.put("contract_interest_loss_2",contract_interest_loss_2);
		if ( contract_interest_loss_3_is_modified )
			map.put("contract_interest_loss_3",contract_interest_loss_3);
		if ( contract_interest_loss_4_is_modified )
			map.put("contract_interest_loss_4",contract_interest_loss_4);
		if ( contract_interest_loss_5_is_modified )
			map.put("contract_interest_loss_5",contract_interest_loss_5);
		if ( contract_interest_profit_1_is_modified )
			map.put("contract_interest_profit_1",contract_interest_profit_1);
		if ( contract_interest_profit_2_is_modified )
			map.put("contract_interest_profit_2",contract_interest_profit_2);
		if ( contract_interest_profit_3_is_modified )
			map.put("contract_interest_profit_3",contract_interest_profit_3);
		if ( contract_interest_profit_4_is_modified )
			map.put("contract_interest_profit_4",contract_interest_profit_4);
		if ( contract_interest_profit_5_is_modified )
			map.put("contract_interest_profit_5",contract_interest_profit_5);
		if ( contract_other_cost_1_is_modified )
			map.put("contract_other_cost_1",contract_other_cost_1);
		if ( contract_other_cost_2_is_modified )
			map.put("contract_other_cost_2",contract_other_cost_2);
		if ( contract_other_cost_3_is_modified )
			map.put("contract_other_cost_3",contract_other_cost_3);
		if ( contract_other_cost_4_is_modified )
			map.put("contract_other_cost_4",contract_other_cost_4);
		if ( contract_other_cost_5_is_modified )
			map.put("contract_other_cost_5",contract_other_cost_5);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( equity_asset_delivered_is_set )
				map.put("equity_asset_delivered",equity_asset_delivered);
			if ( equity_asset_executed_is_set )
				map.put("equity_asset_executed",equity_asset_executed);
			if ( mini_stock_asset_delivered_is_set )
				map.put("mini_stock_asset_delivered",mini_stock_asset_delivered);
			if ( mini_stock_asset_executed_is_set )
				map.put("mini_stock_asset_executed",mini_stock_asset_executed);
			if ( ruito_asset_delivered_is_set )
				map.put("ruito_asset_delivered",ruito_asset_delivered);
			if ( ruito_asset_executed_is_set )
				map.put("ruito_asset_executed",ruito_asset_executed);
			if ( mutual_fund_asset_delivered_is_set )
				map.put("mutual_fund_asset_delivered",mutual_fund_asset_delivered);
			if ( mutual_fund_asset_executed_is_set )
				map.put("mutual_fund_asset_executed",mutual_fund_asset_executed);
			if ( bond_asset_delivered_is_set )
				map.put("bond_asset_delivered",bond_asset_delivered);
			if ( bond_asset_executed_is_set )
				map.put("bond_asset_executed",bond_asset_executed);
			if ( unexec_substi_security_asset_0_is_set )
				map.put("unexec_substi_security_asset_0",unexec_substi_security_asset_0);
			if ( unexec_substi_security_asset_1_is_set )
				map.put("unexec_substi_security_asset_1",unexec_substi_security_asset_1);
			if ( unexec_substi_security_asset_2_is_set )
				map.put("unexec_substi_security_asset_2",unexec_substi_security_asset_2);
			if ( unexec_substi_security_asset_3_is_set )
				map.put("unexec_substi_security_asset_3",unexec_substi_security_asset_3);
			if ( unexec_substi_security_asset_4_is_set )
				map.put("unexec_substi_security_asset_4",unexec_substi_security_asset_4);
			if ( unexec_substi_security_asset_5_is_set )
				map.put("unexec_substi_security_asset_5",unexec_substi_security_asset_5);
			if ( contract_asset_loss_is_set )
				map.put("contract_asset_loss",contract_asset_loss);
			if ( contract_asset_profit_is_set )
				map.put("contract_asset_profit",contract_asset_profit);
			if ( setup_fee_is_set )
				map.put("setup_fee",setup_fee);
			if ( contract_interest_loss_is_set )
				map.put("contract_interest_loss",contract_interest_loss);
			if ( contract_interest_profit_is_set )
				map.put("contract_interest_profit",contract_interest_profit);
			if ( contract_other_cost_is_set )
				map.put("contract_other_cost",contract_other_cost);
			if ( unexec_contract_amount_0_is_set )
				map.put("unexec_contract_amount_0",unexec_contract_amount_0);
			if ( unexec_contract_amount_1_is_set )
				map.put("unexec_contract_amount_1",unexec_contract_amount_1);
			if ( unexec_contract_amount_2_is_set )
				map.put("unexec_contract_amount_2",unexec_contract_amount_2);
			if ( unexec_contract_amount_3_is_set )
				map.put("unexec_contract_amount_3",unexec_contract_amount_3);
			if ( unexec_contract_amount_4_is_set )
				map.put("unexec_contract_amount_4",unexec_contract_amount_4);
			if ( unexec_contract_amount_5_is_set )
				map.put("unexec_contract_amount_5",unexec_contract_amount_5);
			if ( unexec_margin_deposit_0_is_set )
				map.put("unexec_margin_deposit_0",unexec_margin_deposit_0);
			if ( unexec_margin_deposit_1_is_set )
				map.put("unexec_margin_deposit_1",unexec_margin_deposit_1);
			if ( unexec_margin_deposit_2_is_set )
				map.put("unexec_margin_deposit_2",unexec_margin_deposit_2);
			if ( unexec_margin_deposit_3_is_set )
				map.put("unexec_margin_deposit_3",unexec_margin_deposit_3);
			if ( unexec_margin_deposit_4_is_set )
				map.put("unexec_margin_deposit_4",unexec_margin_deposit_4);
			if ( unexec_margin_deposit_5_is_set )
				map.put("unexec_margin_deposit_5",unexec_margin_deposit_5);
			if ( unexec_cash_margin_deposit_0_is_set )
				map.put("unexec_cash_margin_deposit_0",unexec_cash_margin_deposit_0);
			if ( unexec_cash_margin_deposit_1_is_set )
				map.put("unexec_cash_margin_deposit_1",unexec_cash_margin_deposit_1);
			if ( unexec_cash_margin_deposit_2_is_set )
				map.put("unexec_cash_margin_deposit_2",unexec_cash_margin_deposit_2);
			if ( unexec_cash_margin_deposit_3_is_set )
				map.put("unexec_cash_margin_deposit_3",unexec_cash_margin_deposit_3);
			if ( unexec_cash_margin_deposit_4_is_set )
				map.put("unexec_cash_margin_deposit_4",unexec_cash_margin_deposit_4);
			if ( unexec_cash_margin_deposit_5_is_set )
				map.put("unexec_cash_margin_deposit_5",unexec_cash_margin_deposit_5);
			if ( day_repay_margin_deposit_0_is_set )
				map.put("day_repay_margin_deposit_0",day_repay_margin_deposit_0);
			if ( day_repay_margin_deposit_1_is_set )
				map.put("day_repay_margin_deposit_1",day_repay_margin_deposit_1);
			if ( day_repay_margin_deposit_2_is_set )
				map.put("day_repay_margin_deposit_2",day_repay_margin_deposit_2);
			if ( day_repay_margin_deposit_3_is_set )
				map.put("day_repay_margin_deposit_3",day_repay_margin_deposit_3);
			if ( day_repay_margin_deposit_4_is_set )
				map.put("day_repay_margin_deposit_4",day_repay_margin_deposit_4);
			if ( day_repay_margin_deposit_5_is_set )
				map.put("day_repay_margin_deposit_5",day_repay_margin_deposit_5);
			if ( day_repay_cash_margin_deposit0_is_set )
				map.put("day_repay_cash_margin_deposit0",day_repay_cash_margin_deposit0);
			if ( day_repay_cash_margin_deposit1_is_set )
				map.put("day_repay_cash_margin_deposit1",day_repay_cash_margin_deposit1);
			if ( day_repay_cash_margin_deposit2_is_set )
				map.put("day_repay_cash_margin_deposit2",day_repay_cash_margin_deposit2);
			if ( day_repay_cash_margin_deposit3_is_set )
				map.put("day_repay_cash_margin_deposit3",day_repay_cash_margin_deposit3);
			if ( day_repay_cash_margin_deposit4_is_set )
				map.put("day_repay_cash_margin_deposit4",day_repay_cash_margin_deposit4);
			if ( day_repay_cash_margin_deposit5_is_set )
				map.put("day_repay_cash_margin_deposit5",day_repay_cash_margin_deposit5);
			if ( today_repay_contract_loss_is_set )
				map.put("today_repay_contract_loss",today_repay_contract_loss);
			if ( today_repay_contract_profit_is_set )
				map.put("today_repay_contract_profit",today_repay_contract_profit);
			if ( today_repay_contract_pre_asset_is_set )
				map.put("today_repay_contract_pre_asset",today_repay_contract_pre_asset);
			if ( contract_loss_designate_1_is_set )
				map.put("contract_loss_designate_1",contract_loss_designate_1);
			if ( contract_loss_designate_2_is_set )
				map.put("contract_loss_designate_2",contract_loss_designate_2);
			if ( contract_loss_designate_3_is_set )
				map.put("contract_loss_designate_3",contract_loss_designate_3);
			if ( contract_loss_designate_4_is_set )
				map.put("contract_loss_designate_4",contract_loss_designate_4);
			if ( contract_loss_designate_5_is_set )
				map.put("contract_loss_designate_5",contract_loss_designate_5);
			if ( contract_profit_designate_1_is_set )
				map.put("contract_profit_designate_1",contract_profit_designate_1);
			if ( contract_profit_designate_2_is_set )
				map.put("contract_profit_designate_2",contract_profit_designate_2);
			if ( contract_profit_designate_3_is_set )
				map.put("contract_profit_designate_3",contract_profit_designate_3);
			if ( contract_profit_designate_4_is_set )
				map.put("contract_profit_designate_4",contract_profit_designate_4);
			if ( contract_profit_designate_5_is_set )
				map.put("contract_profit_designate_5",contract_profit_designate_5);
			if ( payment_amount_designate_0_is_set )
				map.put("payment_amount_designate_0",payment_amount_designate_0);
			if ( payment_amount_designate_1_is_set )
				map.put("payment_amount_designate_1",payment_amount_designate_1);
			if ( payment_amount_designate_2_is_set )
				map.put("payment_amount_designate_2",payment_amount_designate_2);
			if ( payment_amount_designate_3_is_set )
				map.put("payment_amount_designate_3",payment_amount_designate_3);
			if ( payment_amount_designate_4_is_set )
				map.put("payment_amount_designate_4",payment_amount_designate_4);
			if ( payment_amount_designate_5_is_set )
				map.put("payment_amount_designate_5",payment_amount_designate_5);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			if ( today_dep_fund_restraint_0_is_set )
				map.put("today_dep_fund_restraint_0",today_dep_fund_restraint_0);
			if ( today_dep_fund_restraint_1_is_set )
				map.put("today_dep_fund_restraint_1",today_dep_fund_restraint_1);
			if ( today_dep_fund_restraint_2_is_set )
				map.put("today_dep_fund_restraint_2",today_dep_fund_restraint_2);
			if ( today_dep_fund_restraint_3_is_set )
				map.put("today_dep_fund_restraint_3",today_dep_fund_restraint_3);
			if ( today_dep_fund_restraint_4_is_set )
				map.put("today_dep_fund_restraint_4",today_dep_fund_restraint_4);
			if ( today_dep_fund_restraint_5_is_set )
				map.put("today_dep_fund_restraint_5",today_dep_fund_restraint_5);
			map.put("contract_asset_loss_1",contract_asset_loss_1);
			map.put("contract_asset_loss_2",contract_asset_loss_2);
			map.put("contract_asset_loss_3",contract_asset_loss_3);
			map.put("contract_asset_loss_4",contract_asset_loss_4);
			map.put("contract_asset_loss_5",contract_asset_loss_5);
			map.put("contract_asset_profit_1",contract_asset_profit_1);
			map.put("contract_asset_profit_2",contract_asset_profit_2);
			map.put("contract_asset_profit_3",contract_asset_profit_3);
			map.put("contract_asset_profit_4",contract_asset_profit_4);
			map.put("contract_asset_profit_5",contract_asset_profit_5);
			map.put("foreign_equity_asset_delivered",foreign_equity_asset_delivered);
			map.put("foreign_equity_asset_executed",foreign_equity_asset_executed);
			map.put("today_repay_contract_amount",today_repay_contract_amount);
			map.put("substitute_asset_old_day_value",substitute_asset_old_day_value);
			map.put("setup_fee_1",setup_fee_1);
			map.put("setup_fee_2",setup_fee_2);
			map.put("setup_fee_3",setup_fee_3);
			map.put("setup_fee_4",setup_fee_4);
			map.put("setup_fee_5",setup_fee_5);
			map.put("contract_interest_loss_1",contract_interest_loss_1);
			map.put("contract_interest_loss_2",contract_interest_loss_2);
			map.put("contract_interest_loss_3",contract_interest_loss_3);
			map.put("contract_interest_loss_4",contract_interest_loss_4);
			map.put("contract_interest_loss_5",contract_interest_loss_5);
			map.put("contract_interest_profit_1",contract_interest_profit_1);
			map.put("contract_interest_profit_2",contract_interest_profit_2);
			map.put("contract_interest_profit_3",contract_interest_profit_3);
			map.put("contract_interest_profit_4",contract_interest_profit_4);
			map.put("contract_interest_profit_5",contract_interest_profit_5);
			map.put("contract_other_cost_1",contract_other_cost_1);
			map.put("contract_other_cost_2",contract_other_cost_2);
			map.put("contract_other_cost_3",contract_other_cost_3);
			map.put("contract_other_cost_4",contract_other_cost_4);
			map.put("contract_other_cost_5",contract_other_cost_5);
		}
		return map;
	}


  /** 
   * <em>calc_result_margin_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getCalcResultMarginId()
  {
    return calc_result_margin_id;
  }


  /** 
   * <em>calc_result_margin_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcResultMarginIdIsSet() {
    return calc_result_margin_id_is_set;
  }


  /** 
   * <em>calc_result_margin_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcResultMarginIdIsModified() {
    return calc_result_margin_id_is_modified;
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
   * <em>equity_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getEquityAssetDelivered()
  {
    return ( equity_asset_delivered==null? ((double)0): equity_asset_delivered.doubleValue() );
  }


  /** 
   * <em>equity_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getEquityAssetDeliveredIsNull()
  {
    return equity_asset_delivered == null;
  }


  /** 
   * <em>equity_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityAssetDeliveredIsSet() {
    return equity_asset_delivered_is_set;
  }


  /** 
   * <em>equity_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityAssetDeliveredIsModified() {
    return equity_asset_delivered_is_modified;
  }


  /** 
   * <em>equity_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getEquityAssetExecuted()
  {
    return ( equity_asset_executed==null? ((double)0): equity_asset_executed.doubleValue() );
  }


  /** 
   * <em>equity_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getEquityAssetExecutedIsNull()
  {
    return equity_asset_executed == null;
  }


  /** 
   * <em>equity_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityAssetExecutedIsSet() {
    return equity_asset_executed_is_set;
  }


  /** 
   * <em>equity_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityAssetExecutedIsModified() {
    return equity_asset_executed_is_modified;
  }


  /** 
   * <em>mini_stock_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMiniStockAssetDelivered()
  {
    return ( mini_stock_asset_delivered==null? ((double)0): mini_stock_asset_delivered.doubleValue() );
  }


  /** 
   * <em>mini_stock_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMiniStockAssetDeliveredIsNull()
  {
    return mini_stock_asset_delivered == null;
  }


  /** 
   * <em>mini_stock_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiniStockAssetDeliveredIsSet() {
    return mini_stock_asset_delivered_is_set;
  }


  /** 
   * <em>mini_stock_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiniStockAssetDeliveredIsModified() {
    return mini_stock_asset_delivered_is_modified;
  }


  /** 
   * <em>mini_stock_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMiniStockAssetExecuted()
  {
    return ( mini_stock_asset_executed==null? ((double)0): mini_stock_asset_executed.doubleValue() );
  }


  /** 
   * <em>mini_stock_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMiniStockAssetExecutedIsNull()
  {
    return mini_stock_asset_executed == null;
  }


  /** 
   * <em>mini_stock_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiniStockAssetExecutedIsSet() {
    return mini_stock_asset_executed_is_set;
  }


  /** 
   * <em>mini_stock_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMiniStockAssetExecutedIsModified() {
    return mini_stock_asset_executed_is_modified;
  }


  /** 
   * <em>ruito_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRuitoAssetDelivered()
  {
    return ( ruito_asset_delivered==null? ((double)0): ruito_asset_delivered.doubleValue() );
  }


  /** 
   * <em>ruito_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRuitoAssetDeliveredIsNull()
  {
    return ruito_asset_delivered == null;
  }


  /** 
   * <em>ruito_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRuitoAssetDeliveredIsSet() {
    return ruito_asset_delivered_is_set;
  }


  /** 
   * <em>ruito_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRuitoAssetDeliveredIsModified() {
    return ruito_asset_delivered_is_modified;
  }


  /** 
   * <em>ruito_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRuitoAssetExecuted()
  {
    return ( ruito_asset_executed==null? ((double)0): ruito_asset_executed.doubleValue() );
  }


  /** 
   * <em>ruito_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getRuitoAssetExecutedIsNull()
  {
    return ruito_asset_executed == null;
  }


  /** 
   * <em>ruito_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRuitoAssetExecutedIsSet() {
    return ruito_asset_executed_is_set;
  }


  /** 
   * <em>ruito_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRuitoAssetExecutedIsModified() {
    return ruito_asset_executed_is_modified;
  }


  /** 
   * <em>mutual_fund_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMutualFundAssetDelivered()
  {
    return ( mutual_fund_asset_delivered==null? ((double)0): mutual_fund_asset_delivered.doubleValue() );
  }


  /** 
   * <em>mutual_fund_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMutualFundAssetDeliveredIsNull()
  {
    return mutual_fund_asset_delivered == null;
  }


  /** 
   * <em>mutual_fund_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMutualFundAssetDeliveredIsSet() {
    return mutual_fund_asset_delivered_is_set;
  }


  /** 
   * <em>mutual_fund_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMutualFundAssetDeliveredIsModified() {
    return mutual_fund_asset_delivered_is_modified;
  }


  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMutualFundAssetExecuted()
  {
    return ( mutual_fund_asset_executed==null? ((double)0): mutual_fund_asset_executed.doubleValue() );
  }


  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMutualFundAssetExecutedIsNull()
  {
    return mutual_fund_asset_executed == null;
  }


  /** 
   * <em>mutual_fund_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMutualFundAssetExecutedIsSet() {
    return mutual_fund_asset_executed_is_set;
  }


  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMutualFundAssetExecutedIsModified() {
    return mutual_fund_asset_executed_is_modified;
  }


  /** 
   * <em>bond_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBondAssetDelivered()
  {
    return ( bond_asset_delivered==null? ((double)0): bond_asset_delivered.doubleValue() );
  }


  /** 
   * <em>bond_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBondAssetDeliveredIsNull()
  {
    return bond_asset_delivered == null;
  }


  /** 
   * <em>bond_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondAssetDeliveredIsSet() {
    return bond_asset_delivered_is_set;
  }


  /** 
   * <em>bond_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondAssetDeliveredIsModified() {
    return bond_asset_delivered_is_modified;
  }


  /** 
   * <em>bond_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getBondAssetExecuted()
  {
    return ( bond_asset_executed==null? ((double)0): bond_asset_executed.doubleValue() );
  }


  /** 
   * <em>bond_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getBondAssetExecutedIsNull()
  {
    return bond_asset_executed == null;
  }


  /** 
   * <em>bond_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondAssetExecutedIsSet() {
    return bond_asset_executed_is_set;
  }


  /** 
   * <em>bond_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBondAssetExecutedIsModified() {
    return bond_asset_executed_is_modified;
  }


  /** 
   * <em>unexec_substi_security_asset_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecSubstiSecurityAsset0()
  {
    return ( unexec_substi_security_asset_0==null? ((double)0): unexec_substi_security_asset_0.doubleValue() );
  }


  /** 
   * <em>unexec_substi_security_asset_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecSubstiSecurityAsset0IsNull()
  {
    return unexec_substi_security_asset_0 == null;
  }


  /** 
   * <em>unexec_substi_security_asset_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecSubstiSecurityAsset0IsSet() {
    return unexec_substi_security_asset_0_is_set;
  }


  /** 
   * <em>unexec_substi_security_asset_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecSubstiSecurityAsset0IsModified() {
    return unexec_substi_security_asset_0_is_modified;
  }


  /** 
   * <em>unexec_substi_security_asset_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecSubstiSecurityAsset1()
  {
    return ( unexec_substi_security_asset_1==null? ((double)0): unexec_substi_security_asset_1.doubleValue() );
  }


  /** 
   * <em>unexec_substi_security_asset_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecSubstiSecurityAsset1IsNull()
  {
    return unexec_substi_security_asset_1 == null;
  }


  /** 
   * <em>unexec_substi_security_asset_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecSubstiSecurityAsset1IsSet() {
    return unexec_substi_security_asset_1_is_set;
  }


  /** 
   * <em>unexec_substi_security_asset_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecSubstiSecurityAsset1IsModified() {
    return unexec_substi_security_asset_1_is_modified;
  }


  /** 
   * <em>unexec_substi_security_asset_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecSubstiSecurityAsset2()
  {
    return ( unexec_substi_security_asset_2==null? ((double)0): unexec_substi_security_asset_2.doubleValue() );
  }


  /** 
   * <em>unexec_substi_security_asset_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecSubstiSecurityAsset2IsNull()
  {
    return unexec_substi_security_asset_2 == null;
  }


  /** 
   * <em>unexec_substi_security_asset_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecSubstiSecurityAsset2IsSet() {
    return unexec_substi_security_asset_2_is_set;
  }


  /** 
   * <em>unexec_substi_security_asset_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecSubstiSecurityAsset2IsModified() {
    return unexec_substi_security_asset_2_is_modified;
  }


  /** 
   * <em>unexec_substi_security_asset_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecSubstiSecurityAsset3()
  {
    return ( unexec_substi_security_asset_3==null? ((double)0): unexec_substi_security_asset_3.doubleValue() );
  }


  /** 
   * <em>unexec_substi_security_asset_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecSubstiSecurityAsset3IsNull()
  {
    return unexec_substi_security_asset_3 == null;
  }


  /** 
   * <em>unexec_substi_security_asset_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecSubstiSecurityAsset3IsSet() {
    return unexec_substi_security_asset_3_is_set;
  }


  /** 
   * <em>unexec_substi_security_asset_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecSubstiSecurityAsset3IsModified() {
    return unexec_substi_security_asset_3_is_modified;
  }


  /** 
   * <em>unexec_substi_security_asset_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecSubstiSecurityAsset4()
  {
    return ( unexec_substi_security_asset_4==null? ((double)0): unexec_substi_security_asset_4.doubleValue() );
  }


  /** 
   * <em>unexec_substi_security_asset_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecSubstiSecurityAsset4IsNull()
  {
    return unexec_substi_security_asset_4 == null;
  }


  /** 
   * <em>unexec_substi_security_asset_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecSubstiSecurityAsset4IsSet() {
    return unexec_substi_security_asset_4_is_set;
  }


  /** 
   * <em>unexec_substi_security_asset_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecSubstiSecurityAsset4IsModified() {
    return unexec_substi_security_asset_4_is_modified;
  }


  /** 
   * <em>unexec_substi_security_asset_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecSubstiSecurityAsset5()
  {
    return ( unexec_substi_security_asset_5==null? ((double)0): unexec_substi_security_asset_5.doubleValue() );
  }


  /** 
   * <em>unexec_substi_security_asset_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecSubstiSecurityAsset5IsNull()
  {
    return unexec_substi_security_asset_5 == null;
  }


  /** 
   * <em>unexec_substi_security_asset_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecSubstiSecurityAsset5IsSet() {
    return unexec_substi_security_asset_5_is_set;
  }


  /** 
   * <em>unexec_substi_security_asset_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecSubstiSecurityAsset5IsModified() {
    return unexec_substi_security_asset_5_is_modified;
  }


  /** 
   * <em>contract_asset_loss</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetLoss()
  {
    return ( contract_asset_loss==null? ((double)0): contract_asset_loss.doubleValue() );
  }


  /** 
   * <em>contract_asset_loss</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetLossIsNull()
  {
    return contract_asset_loss == null;
  }


  /** 
   * <em>contract_asset_loss</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetLossIsSet() {
    return contract_asset_loss_is_set;
  }


  /** 
   * <em>contract_asset_loss</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetLossIsModified() {
    return contract_asset_loss_is_modified;
  }


  /** 
   * <em>contract_asset_profit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetProfit()
  {
    return ( contract_asset_profit==null? ((double)0): contract_asset_profit.doubleValue() );
  }


  /** 
   * <em>contract_asset_profit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetProfitIsNull()
  {
    return contract_asset_profit == null;
  }


  /** 
   * <em>contract_asset_profit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitIsSet() {
    return contract_asset_profit_is_set;
  }


  /** 
   * <em>contract_asset_profit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitIsModified() {
    return contract_asset_profit_is_modified;
  }


  /** 
   * <em>setup_fee</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSetupFee()
  {
    return ( setup_fee==null? ((double)0): setup_fee.doubleValue() );
  }


  /** 
   * <em>setup_fee</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSetupFeeIsNull()
  {
    return setup_fee == null;
  }


  /** 
   * <em>setup_fee</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSetupFeeIsSet() {
    return setup_fee_is_set;
  }


  /** 
   * <em>setup_fee</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSetupFeeIsModified() {
    return setup_fee_is_modified;
  }


  /** 
   * <em>contract_interest_loss</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractInterestLoss()
  {
    return ( contract_interest_loss==null? ((double)0): contract_interest_loss.doubleValue() );
  }


  /** 
   * <em>contract_interest_loss</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractInterestLossIsNull()
  {
    return contract_interest_loss == null;
  }


  /** 
   * <em>contract_interest_loss</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestLossIsSet() {
    return contract_interest_loss_is_set;
  }


  /** 
   * <em>contract_interest_loss</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestLossIsModified() {
    return contract_interest_loss_is_modified;
  }


  /** 
   * <em>contract_interest_profit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractInterestProfit()
  {
    return ( contract_interest_profit==null? ((double)0): contract_interest_profit.doubleValue() );
  }


  /** 
   * <em>contract_interest_profit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractInterestProfitIsNull()
  {
    return contract_interest_profit == null;
  }


  /** 
   * <em>contract_interest_profit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestProfitIsSet() {
    return contract_interest_profit_is_set;
  }


  /** 
   * <em>contract_interest_profit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestProfitIsModified() {
    return contract_interest_profit_is_modified;
  }


  /** 
   * <em>contract_other_cost</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractOtherCost()
  {
    return ( contract_other_cost==null? ((double)0): contract_other_cost.doubleValue() );
  }


  /** 
   * <em>contract_other_cost</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractOtherCostIsNull()
  {
    return contract_other_cost == null;
  }


  /** 
   * <em>contract_other_cost</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractOtherCostIsSet() {
    return contract_other_cost_is_set;
  }


  /** 
   * <em>contract_other_cost</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractOtherCostIsModified() {
    return contract_other_cost_is_modified;
  }


  /** 
   * <em>unexec_contract_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecContractAmount0()
  {
    return ( unexec_contract_amount_0==null? ((double)0): unexec_contract_amount_0.doubleValue() );
  }


  /** 
   * <em>unexec_contract_amount_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecContractAmount0IsNull()
  {
    return unexec_contract_amount_0 == null;
  }


  /** 
   * <em>unexec_contract_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecContractAmount0IsSet() {
    return unexec_contract_amount_0_is_set;
  }


  /** 
   * <em>unexec_contract_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecContractAmount0IsModified() {
    return unexec_contract_amount_0_is_modified;
  }


  /** 
   * <em>unexec_contract_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecContractAmount1()
  {
    return ( unexec_contract_amount_1==null? ((double)0): unexec_contract_amount_1.doubleValue() );
  }


  /** 
   * <em>unexec_contract_amount_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecContractAmount1IsNull()
  {
    return unexec_contract_amount_1 == null;
  }


  /** 
   * <em>unexec_contract_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecContractAmount1IsSet() {
    return unexec_contract_amount_1_is_set;
  }


  /** 
   * <em>unexec_contract_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecContractAmount1IsModified() {
    return unexec_contract_amount_1_is_modified;
  }


  /** 
   * <em>unexec_contract_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecContractAmount2()
  {
    return ( unexec_contract_amount_2==null? ((double)0): unexec_contract_amount_2.doubleValue() );
  }


  /** 
   * <em>unexec_contract_amount_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecContractAmount2IsNull()
  {
    return unexec_contract_amount_2 == null;
  }


  /** 
   * <em>unexec_contract_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecContractAmount2IsSet() {
    return unexec_contract_amount_2_is_set;
  }


  /** 
   * <em>unexec_contract_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecContractAmount2IsModified() {
    return unexec_contract_amount_2_is_modified;
  }


  /** 
   * <em>unexec_contract_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecContractAmount3()
  {
    return ( unexec_contract_amount_3==null? ((double)0): unexec_contract_amount_3.doubleValue() );
  }


  /** 
   * <em>unexec_contract_amount_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecContractAmount3IsNull()
  {
    return unexec_contract_amount_3 == null;
  }


  /** 
   * <em>unexec_contract_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecContractAmount3IsSet() {
    return unexec_contract_amount_3_is_set;
  }


  /** 
   * <em>unexec_contract_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecContractAmount3IsModified() {
    return unexec_contract_amount_3_is_modified;
  }


  /** 
   * <em>unexec_contract_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecContractAmount4()
  {
    return ( unexec_contract_amount_4==null? ((double)0): unexec_contract_amount_4.doubleValue() );
  }


  /** 
   * <em>unexec_contract_amount_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecContractAmount4IsNull()
  {
    return unexec_contract_amount_4 == null;
  }


  /** 
   * <em>unexec_contract_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecContractAmount4IsSet() {
    return unexec_contract_amount_4_is_set;
  }


  /** 
   * <em>unexec_contract_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecContractAmount4IsModified() {
    return unexec_contract_amount_4_is_modified;
  }


  /** 
   * <em>unexec_contract_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecContractAmount5()
  {
    return ( unexec_contract_amount_5==null? ((double)0): unexec_contract_amount_5.doubleValue() );
  }


  /** 
   * <em>unexec_contract_amount_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecContractAmount5IsNull()
  {
    return unexec_contract_amount_5 == null;
  }


  /** 
   * <em>unexec_contract_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecContractAmount5IsSet() {
    return unexec_contract_amount_5_is_set;
  }


  /** 
   * <em>unexec_contract_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecContractAmount5IsModified() {
    return unexec_contract_amount_5_is_modified;
  }


  /** 
   * <em>unexec_margin_deposit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecMarginDeposit0()
  {
    return ( unexec_margin_deposit_0==null? ((double)0): unexec_margin_deposit_0.doubleValue() );
  }


  /** 
   * <em>unexec_margin_deposit_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecMarginDeposit0IsNull()
  {
    return unexec_margin_deposit_0 == null;
  }


  /** 
   * <em>unexec_margin_deposit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecMarginDeposit0IsSet() {
    return unexec_margin_deposit_0_is_set;
  }


  /** 
   * <em>unexec_margin_deposit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecMarginDeposit0IsModified() {
    return unexec_margin_deposit_0_is_modified;
  }


  /** 
   * <em>unexec_margin_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecMarginDeposit1()
  {
    return ( unexec_margin_deposit_1==null? ((double)0): unexec_margin_deposit_1.doubleValue() );
  }


  /** 
   * <em>unexec_margin_deposit_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecMarginDeposit1IsNull()
  {
    return unexec_margin_deposit_1 == null;
  }


  /** 
   * <em>unexec_margin_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecMarginDeposit1IsSet() {
    return unexec_margin_deposit_1_is_set;
  }


  /** 
   * <em>unexec_margin_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecMarginDeposit1IsModified() {
    return unexec_margin_deposit_1_is_modified;
  }


  /** 
   * <em>unexec_margin_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecMarginDeposit2()
  {
    return ( unexec_margin_deposit_2==null? ((double)0): unexec_margin_deposit_2.doubleValue() );
  }


  /** 
   * <em>unexec_margin_deposit_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecMarginDeposit2IsNull()
  {
    return unexec_margin_deposit_2 == null;
  }


  /** 
   * <em>unexec_margin_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecMarginDeposit2IsSet() {
    return unexec_margin_deposit_2_is_set;
  }


  /** 
   * <em>unexec_margin_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecMarginDeposit2IsModified() {
    return unexec_margin_deposit_2_is_modified;
  }


  /** 
   * <em>unexec_margin_deposit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecMarginDeposit3()
  {
    return ( unexec_margin_deposit_3==null? ((double)0): unexec_margin_deposit_3.doubleValue() );
  }


  /** 
   * <em>unexec_margin_deposit_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecMarginDeposit3IsNull()
  {
    return unexec_margin_deposit_3 == null;
  }


  /** 
   * <em>unexec_margin_deposit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecMarginDeposit3IsSet() {
    return unexec_margin_deposit_3_is_set;
  }


  /** 
   * <em>unexec_margin_deposit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecMarginDeposit3IsModified() {
    return unexec_margin_deposit_3_is_modified;
  }


  /** 
   * <em>unexec_margin_deposit_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecMarginDeposit4()
  {
    return ( unexec_margin_deposit_4==null? ((double)0): unexec_margin_deposit_4.doubleValue() );
  }


  /** 
   * <em>unexec_margin_deposit_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecMarginDeposit4IsNull()
  {
    return unexec_margin_deposit_4 == null;
  }


  /** 
   * <em>unexec_margin_deposit_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecMarginDeposit4IsSet() {
    return unexec_margin_deposit_4_is_set;
  }


  /** 
   * <em>unexec_margin_deposit_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecMarginDeposit4IsModified() {
    return unexec_margin_deposit_4_is_modified;
  }


  /** 
   * <em>unexec_margin_deposit_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecMarginDeposit5()
  {
    return ( unexec_margin_deposit_5==null? ((double)0): unexec_margin_deposit_5.doubleValue() );
  }


  /** 
   * <em>unexec_margin_deposit_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecMarginDeposit5IsNull()
  {
    return unexec_margin_deposit_5 == null;
  }


  /** 
   * <em>unexec_margin_deposit_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecMarginDeposit5IsSet() {
    return unexec_margin_deposit_5_is_set;
  }


  /** 
   * <em>unexec_margin_deposit_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecMarginDeposit5IsModified() {
    return unexec_margin_deposit_5_is_modified;
  }


  /** 
   * <em>unexec_cash_margin_deposit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecCashMarginDeposit0()
  {
    return ( unexec_cash_margin_deposit_0==null? ((double)0): unexec_cash_margin_deposit_0.doubleValue() );
  }


  /** 
   * <em>unexec_cash_margin_deposit_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecCashMarginDeposit0IsNull()
  {
    return unexec_cash_margin_deposit_0 == null;
  }


  /** 
   * <em>unexec_cash_margin_deposit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecCashMarginDeposit0IsSet() {
    return unexec_cash_margin_deposit_0_is_set;
  }


  /** 
   * <em>unexec_cash_margin_deposit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecCashMarginDeposit0IsModified() {
    return unexec_cash_margin_deposit_0_is_modified;
  }


  /** 
   * <em>unexec_cash_margin_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecCashMarginDeposit1()
  {
    return ( unexec_cash_margin_deposit_1==null? ((double)0): unexec_cash_margin_deposit_1.doubleValue() );
  }


  /** 
   * <em>unexec_cash_margin_deposit_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecCashMarginDeposit1IsNull()
  {
    return unexec_cash_margin_deposit_1 == null;
  }


  /** 
   * <em>unexec_cash_margin_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecCashMarginDeposit1IsSet() {
    return unexec_cash_margin_deposit_1_is_set;
  }


  /** 
   * <em>unexec_cash_margin_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecCashMarginDeposit1IsModified() {
    return unexec_cash_margin_deposit_1_is_modified;
  }


  /** 
   * <em>unexec_cash_margin_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecCashMarginDeposit2()
  {
    return ( unexec_cash_margin_deposit_2==null? ((double)0): unexec_cash_margin_deposit_2.doubleValue() );
  }


  /** 
   * <em>unexec_cash_margin_deposit_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecCashMarginDeposit2IsNull()
  {
    return unexec_cash_margin_deposit_2 == null;
  }


  /** 
   * <em>unexec_cash_margin_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecCashMarginDeposit2IsSet() {
    return unexec_cash_margin_deposit_2_is_set;
  }


  /** 
   * <em>unexec_cash_margin_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecCashMarginDeposit2IsModified() {
    return unexec_cash_margin_deposit_2_is_modified;
  }


  /** 
   * <em>unexec_cash_margin_deposit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecCashMarginDeposit3()
  {
    return ( unexec_cash_margin_deposit_3==null? ((double)0): unexec_cash_margin_deposit_3.doubleValue() );
  }


  /** 
   * <em>unexec_cash_margin_deposit_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecCashMarginDeposit3IsNull()
  {
    return unexec_cash_margin_deposit_3 == null;
  }


  /** 
   * <em>unexec_cash_margin_deposit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecCashMarginDeposit3IsSet() {
    return unexec_cash_margin_deposit_3_is_set;
  }


  /** 
   * <em>unexec_cash_margin_deposit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecCashMarginDeposit3IsModified() {
    return unexec_cash_margin_deposit_3_is_modified;
  }


  /** 
   * <em>unexec_cash_margin_deposit_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecCashMarginDeposit4()
  {
    return ( unexec_cash_margin_deposit_4==null? ((double)0): unexec_cash_margin_deposit_4.doubleValue() );
  }


  /** 
   * <em>unexec_cash_margin_deposit_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecCashMarginDeposit4IsNull()
  {
    return unexec_cash_margin_deposit_4 == null;
  }


  /** 
   * <em>unexec_cash_margin_deposit_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecCashMarginDeposit4IsSet() {
    return unexec_cash_margin_deposit_4_is_set;
  }


  /** 
   * <em>unexec_cash_margin_deposit_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecCashMarginDeposit4IsModified() {
    return unexec_cash_margin_deposit_4_is_modified;
  }


  /** 
   * <em>unexec_cash_margin_deposit_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUnexecCashMarginDeposit5()
  {
    return ( unexec_cash_margin_deposit_5==null? ((double)0): unexec_cash_margin_deposit_5.doubleValue() );
  }


  /** 
   * <em>unexec_cash_margin_deposit_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnexecCashMarginDeposit5IsNull()
  {
    return unexec_cash_margin_deposit_5 == null;
  }


  /** 
   * <em>unexec_cash_margin_deposit_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecCashMarginDeposit5IsSet() {
    return unexec_cash_margin_deposit_5_is_set;
  }


  /** 
   * <em>unexec_cash_margin_deposit_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnexecCashMarginDeposit5IsModified() {
    return unexec_cash_margin_deposit_5_is_modified;
  }


  /** 
   * <em>day_repay_margin_deposit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayMarginDeposit0()
  {
    return ( day_repay_margin_deposit_0==null? ((double)0): day_repay_margin_deposit_0.doubleValue() );
  }


  /** 
   * <em>day_repay_margin_deposit_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayRepayMarginDeposit0IsNull()
  {
    return day_repay_margin_deposit_0 == null;
  }


  /** 
   * <em>day_repay_margin_deposit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayMarginDeposit0IsSet() {
    return day_repay_margin_deposit_0_is_set;
  }


  /** 
   * <em>day_repay_margin_deposit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayMarginDeposit0IsModified() {
    return day_repay_margin_deposit_0_is_modified;
  }


  /** 
   * <em>day_repay_margin_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayMarginDeposit1()
  {
    return ( day_repay_margin_deposit_1==null? ((double)0): day_repay_margin_deposit_1.doubleValue() );
  }


  /** 
   * <em>day_repay_margin_deposit_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayRepayMarginDeposit1IsNull()
  {
    return day_repay_margin_deposit_1 == null;
  }


  /** 
   * <em>day_repay_margin_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayMarginDeposit1IsSet() {
    return day_repay_margin_deposit_1_is_set;
  }


  /** 
   * <em>day_repay_margin_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayMarginDeposit1IsModified() {
    return day_repay_margin_deposit_1_is_modified;
  }


  /** 
   * <em>day_repay_margin_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayMarginDeposit2()
  {
    return ( day_repay_margin_deposit_2==null? ((double)0): day_repay_margin_deposit_2.doubleValue() );
  }


  /** 
   * <em>day_repay_margin_deposit_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayRepayMarginDeposit2IsNull()
  {
    return day_repay_margin_deposit_2 == null;
  }


  /** 
   * <em>day_repay_margin_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayMarginDeposit2IsSet() {
    return day_repay_margin_deposit_2_is_set;
  }


  /** 
   * <em>day_repay_margin_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayMarginDeposit2IsModified() {
    return day_repay_margin_deposit_2_is_modified;
  }


  /** 
   * <em>day_repay_margin_deposit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayMarginDeposit3()
  {
    return ( day_repay_margin_deposit_3==null? ((double)0): day_repay_margin_deposit_3.doubleValue() );
  }


  /** 
   * <em>day_repay_margin_deposit_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayRepayMarginDeposit3IsNull()
  {
    return day_repay_margin_deposit_3 == null;
  }


  /** 
   * <em>day_repay_margin_deposit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayMarginDeposit3IsSet() {
    return day_repay_margin_deposit_3_is_set;
  }


  /** 
   * <em>day_repay_margin_deposit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayMarginDeposit3IsModified() {
    return day_repay_margin_deposit_3_is_modified;
  }


  /** 
   * <em>day_repay_margin_deposit_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayMarginDeposit4()
  {
    return ( day_repay_margin_deposit_4==null? ((double)0): day_repay_margin_deposit_4.doubleValue() );
  }


  /** 
   * <em>day_repay_margin_deposit_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayRepayMarginDeposit4IsNull()
  {
    return day_repay_margin_deposit_4 == null;
  }


  /** 
   * <em>day_repay_margin_deposit_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayMarginDeposit4IsSet() {
    return day_repay_margin_deposit_4_is_set;
  }


  /** 
   * <em>day_repay_margin_deposit_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayMarginDeposit4IsModified() {
    return day_repay_margin_deposit_4_is_modified;
  }


  /** 
   * <em>day_repay_margin_deposit_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayMarginDeposit5()
  {
    return ( day_repay_margin_deposit_5==null? ((double)0): day_repay_margin_deposit_5.doubleValue() );
  }


  /** 
   * <em>day_repay_margin_deposit_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayRepayMarginDeposit5IsNull()
  {
    return day_repay_margin_deposit_5 == null;
  }


  /** 
   * <em>day_repay_margin_deposit_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayMarginDeposit5IsSet() {
    return day_repay_margin_deposit_5_is_set;
  }


  /** 
   * <em>day_repay_margin_deposit_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayMarginDeposit5IsModified() {
    return day_repay_margin_deposit_5_is_modified;
  }


  /** 
   * <em>day_repay_cash_margin_deposit0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayCashMarginDeposit0()
  {
    return ( day_repay_cash_margin_deposit0==null? ((double)0): day_repay_cash_margin_deposit0.doubleValue() );
  }


  /** 
   * <em>day_repay_cash_margin_deposit0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayRepayCashMarginDeposit0IsNull()
  {
    return day_repay_cash_margin_deposit0 == null;
  }


  /** 
   * <em>day_repay_cash_margin_deposit0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayCashMarginDeposit0IsSet() {
    return day_repay_cash_margin_deposit0_is_set;
  }


  /** 
   * <em>day_repay_cash_margin_deposit0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayCashMarginDeposit0IsModified() {
    return day_repay_cash_margin_deposit0_is_modified;
  }


  /** 
   * <em>day_repay_cash_margin_deposit1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayCashMarginDeposit1()
  {
    return ( day_repay_cash_margin_deposit1==null? ((double)0): day_repay_cash_margin_deposit1.doubleValue() );
  }


  /** 
   * <em>day_repay_cash_margin_deposit1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayRepayCashMarginDeposit1IsNull()
  {
    return day_repay_cash_margin_deposit1 == null;
  }


  /** 
   * <em>day_repay_cash_margin_deposit1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayCashMarginDeposit1IsSet() {
    return day_repay_cash_margin_deposit1_is_set;
  }


  /** 
   * <em>day_repay_cash_margin_deposit1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayCashMarginDeposit1IsModified() {
    return day_repay_cash_margin_deposit1_is_modified;
  }


  /** 
   * <em>day_repay_cash_margin_deposit2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayCashMarginDeposit2()
  {
    return ( day_repay_cash_margin_deposit2==null? ((double)0): day_repay_cash_margin_deposit2.doubleValue() );
  }


  /** 
   * <em>day_repay_cash_margin_deposit2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayRepayCashMarginDeposit2IsNull()
  {
    return day_repay_cash_margin_deposit2 == null;
  }


  /** 
   * <em>day_repay_cash_margin_deposit2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayCashMarginDeposit2IsSet() {
    return day_repay_cash_margin_deposit2_is_set;
  }


  /** 
   * <em>day_repay_cash_margin_deposit2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayCashMarginDeposit2IsModified() {
    return day_repay_cash_margin_deposit2_is_modified;
  }


  /** 
   * <em>day_repay_cash_margin_deposit3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayCashMarginDeposit3()
  {
    return ( day_repay_cash_margin_deposit3==null? ((double)0): day_repay_cash_margin_deposit3.doubleValue() );
  }


  /** 
   * <em>day_repay_cash_margin_deposit3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayRepayCashMarginDeposit3IsNull()
  {
    return day_repay_cash_margin_deposit3 == null;
  }


  /** 
   * <em>day_repay_cash_margin_deposit3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayCashMarginDeposit3IsSet() {
    return day_repay_cash_margin_deposit3_is_set;
  }


  /** 
   * <em>day_repay_cash_margin_deposit3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayCashMarginDeposit3IsModified() {
    return day_repay_cash_margin_deposit3_is_modified;
  }


  /** 
   * <em>day_repay_cash_margin_deposit4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayCashMarginDeposit4()
  {
    return ( day_repay_cash_margin_deposit4==null? ((double)0): day_repay_cash_margin_deposit4.doubleValue() );
  }


  /** 
   * <em>day_repay_cash_margin_deposit4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayRepayCashMarginDeposit4IsNull()
  {
    return day_repay_cash_margin_deposit4 == null;
  }


  /** 
   * <em>day_repay_cash_margin_deposit4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayCashMarginDeposit4IsSet() {
    return day_repay_cash_margin_deposit4_is_set;
  }


  /** 
   * <em>day_repay_cash_margin_deposit4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayCashMarginDeposit4IsModified() {
    return day_repay_cash_margin_deposit4_is_modified;
  }


  /** 
   * <em>day_repay_cash_margin_deposit5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayCashMarginDeposit5()
  {
    return ( day_repay_cash_margin_deposit5==null? ((double)0): day_repay_cash_margin_deposit5.doubleValue() );
  }


  /** 
   * <em>day_repay_cash_margin_deposit5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayRepayCashMarginDeposit5IsNull()
  {
    return day_repay_cash_margin_deposit5 == null;
  }


  /** 
   * <em>day_repay_cash_margin_deposit5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayCashMarginDeposit5IsSet() {
    return day_repay_cash_margin_deposit5_is_set;
  }


  /** 
   * <em>day_repay_cash_margin_deposit5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayCashMarginDeposit5IsModified() {
    return day_repay_cash_margin_deposit5_is_modified;
  }


  /** 
   * <em>today_repay_contract_loss</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayRepayContractLoss()
  {
    return ( today_repay_contract_loss==null? ((double)0): today_repay_contract_loss.doubleValue() );
  }


  /** 
   * <em>today_repay_contract_loss</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTodayRepayContractLossIsNull()
  {
    return today_repay_contract_loss == null;
  }


  /** 
   * <em>today_repay_contract_loss</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayRepayContractLossIsSet() {
    return today_repay_contract_loss_is_set;
  }


  /** 
   * <em>today_repay_contract_loss</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayRepayContractLossIsModified() {
    return today_repay_contract_loss_is_modified;
  }


  /** 
   * <em>today_repay_contract_profit</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayRepayContractProfit()
  {
    return ( today_repay_contract_profit==null? ((double)0): today_repay_contract_profit.doubleValue() );
  }


  /** 
   * <em>today_repay_contract_profit</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTodayRepayContractProfitIsNull()
  {
    return today_repay_contract_profit == null;
  }


  /** 
   * <em>today_repay_contract_profit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayRepayContractProfitIsSet() {
    return today_repay_contract_profit_is_set;
  }


  /** 
   * <em>today_repay_contract_profit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayRepayContractProfitIsModified() {
    return today_repay_contract_profit_is_modified;
  }


  /** 
   * <em>today_repay_contract_pre_asset</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayRepayContractPreAsset()
  {
    return ( today_repay_contract_pre_asset==null? ((double)0): today_repay_contract_pre_asset.doubleValue() );
  }


  /** 
   * <em>today_repay_contract_pre_asset</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTodayRepayContractPreAssetIsNull()
  {
    return today_repay_contract_pre_asset == null;
  }


  /** 
   * <em>today_repay_contract_pre_asset</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayRepayContractPreAssetIsSet() {
    return today_repay_contract_pre_asset_is_set;
  }


  /** 
   * <em>today_repay_contract_pre_asset</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayRepayContractPreAssetIsModified() {
    return today_repay_contract_pre_asset_is_modified;
  }


  /** 
   * <em>contract_loss_designate_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractLossDesignate1()
  {
    return ( contract_loss_designate_1==null? ((double)0): contract_loss_designate_1.doubleValue() );
  }


  /** 
   * <em>contract_loss_designate_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractLossDesignate1IsNull()
  {
    return contract_loss_designate_1 == null;
  }


  /** 
   * <em>contract_loss_designate_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractLossDesignate1IsSet() {
    return contract_loss_designate_1_is_set;
  }


  /** 
   * <em>contract_loss_designate_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractLossDesignate1IsModified() {
    return contract_loss_designate_1_is_modified;
  }


  /** 
   * <em>contract_loss_designate_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractLossDesignate2()
  {
    return ( contract_loss_designate_2==null? ((double)0): contract_loss_designate_2.doubleValue() );
  }


  /** 
   * <em>contract_loss_designate_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractLossDesignate2IsNull()
  {
    return contract_loss_designate_2 == null;
  }


  /** 
   * <em>contract_loss_designate_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractLossDesignate2IsSet() {
    return contract_loss_designate_2_is_set;
  }


  /** 
   * <em>contract_loss_designate_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractLossDesignate2IsModified() {
    return contract_loss_designate_2_is_modified;
  }


  /** 
   * <em>contract_loss_designate_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractLossDesignate3()
  {
    return ( contract_loss_designate_3==null? ((double)0): contract_loss_designate_3.doubleValue() );
  }


  /** 
   * <em>contract_loss_designate_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractLossDesignate3IsNull()
  {
    return contract_loss_designate_3 == null;
  }


  /** 
   * <em>contract_loss_designate_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractLossDesignate3IsSet() {
    return contract_loss_designate_3_is_set;
  }


  /** 
   * <em>contract_loss_designate_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractLossDesignate3IsModified() {
    return contract_loss_designate_3_is_modified;
  }


  /** 
   * <em>contract_loss_designate_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractLossDesignate4()
  {
    return ( contract_loss_designate_4==null? ((double)0): contract_loss_designate_4.doubleValue() );
  }


  /** 
   * <em>contract_loss_designate_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractLossDesignate4IsNull()
  {
    return contract_loss_designate_4 == null;
  }


  /** 
   * <em>contract_loss_designate_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractLossDesignate4IsSet() {
    return contract_loss_designate_4_is_set;
  }


  /** 
   * <em>contract_loss_designate_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractLossDesignate4IsModified() {
    return contract_loss_designate_4_is_modified;
  }


  /** 
   * <em>contract_loss_designate_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractLossDesignate5()
  {
    return ( contract_loss_designate_5==null? ((double)0): contract_loss_designate_5.doubleValue() );
  }


  /** 
   * <em>contract_loss_designate_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractLossDesignate5IsNull()
  {
    return contract_loss_designate_5 == null;
  }


  /** 
   * <em>contract_loss_designate_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractLossDesignate5IsSet() {
    return contract_loss_designate_5_is_set;
  }


  /** 
   * <em>contract_loss_designate_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractLossDesignate5IsModified() {
    return contract_loss_designate_5_is_modified;
  }


  /** 
   * <em>contract_profit_designate_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractProfitDesignate1()
  {
    return ( contract_profit_designate_1==null? ((double)0): contract_profit_designate_1.doubleValue() );
  }


  /** 
   * <em>contract_profit_designate_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractProfitDesignate1IsNull()
  {
    return contract_profit_designate_1 == null;
  }


  /** 
   * <em>contract_profit_designate_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractProfitDesignate1IsSet() {
    return contract_profit_designate_1_is_set;
  }


  /** 
   * <em>contract_profit_designate_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractProfitDesignate1IsModified() {
    return contract_profit_designate_1_is_modified;
  }


  /** 
   * <em>contract_profit_designate_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractProfitDesignate2()
  {
    return ( contract_profit_designate_2==null? ((double)0): contract_profit_designate_2.doubleValue() );
  }


  /** 
   * <em>contract_profit_designate_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractProfitDesignate2IsNull()
  {
    return contract_profit_designate_2 == null;
  }


  /** 
   * <em>contract_profit_designate_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractProfitDesignate2IsSet() {
    return contract_profit_designate_2_is_set;
  }


  /** 
   * <em>contract_profit_designate_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractProfitDesignate2IsModified() {
    return contract_profit_designate_2_is_modified;
  }


  /** 
   * <em>contract_profit_designate_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractProfitDesignate3()
  {
    return ( contract_profit_designate_3==null? ((double)0): contract_profit_designate_3.doubleValue() );
  }


  /** 
   * <em>contract_profit_designate_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractProfitDesignate3IsNull()
  {
    return contract_profit_designate_3 == null;
  }


  /** 
   * <em>contract_profit_designate_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractProfitDesignate3IsSet() {
    return contract_profit_designate_3_is_set;
  }


  /** 
   * <em>contract_profit_designate_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractProfitDesignate3IsModified() {
    return contract_profit_designate_3_is_modified;
  }


  /** 
   * <em>contract_profit_designate_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractProfitDesignate4()
  {
    return ( contract_profit_designate_4==null? ((double)0): contract_profit_designate_4.doubleValue() );
  }


  /** 
   * <em>contract_profit_designate_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractProfitDesignate4IsNull()
  {
    return contract_profit_designate_4 == null;
  }


  /** 
   * <em>contract_profit_designate_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractProfitDesignate4IsSet() {
    return contract_profit_designate_4_is_set;
  }


  /** 
   * <em>contract_profit_designate_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractProfitDesignate4IsModified() {
    return contract_profit_designate_4_is_modified;
  }


  /** 
   * <em>contract_profit_designate_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractProfitDesignate5()
  {
    return ( contract_profit_designate_5==null? ((double)0): contract_profit_designate_5.doubleValue() );
  }


  /** 
   * <em>contract_profit_designate_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractProfitDesignate5IsNull()
  {
    return contract_profit_designate_5 == null;
  }


  /** 
   * <em>contract_profit_designate_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractProfitDesignate5IsSet() {
    return contract_profit_designate_5_is_set;
  }


  /** 
   * <em>contract_profit_designate_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractProfitDesignate5IsModified() {
    return contract_profit_designate_5_is_modified;
  }


  /** 
   * <em>payment_amount_designate_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentAmountDesignate0()
  {
    return ( payment_amount_designate_0==null? ((double)0): payment_amount_designate_0.doubleValue() );
  }


  /** 
   * <em>payment_amount_designate_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentAmountDesignate0IsNull()
  {
    return payment_amount_designate_0 == null;
  }


  /** 
   * <em>payment_amount_designate_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate0IsSet() {
    return payment_amount_designate_0_is_set;
  }


  /** 
   * <em>payment_amount_designate_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate0IsModified() {
    return payment_amount_designate_0_is_modified;
  }


  /** 
   * <em>payment_amount_designate_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentAmountDesignate1()
  {
    return ( payment_amount_designate_1==null? ((double)0): payment_amount_designate_1.doubleValue() );
  }


  /** 
   * <em>payment_amount_designate_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentAmountDesignate1IsNull()
  {
    return payment_amount_designate_1 == null;
  }


  /** 
   * <em>payment_amount_designate_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate1IsSet() {
    return payment_amount_designate_1_is_set;
  }


  /** 
   * <em>payment_amount_designate_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate1IsModified() {
    return payment_amount_designate_1_is_modified;
  }


  /** 
   * <em>payment_amount_designate_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentAmountDesignate2()
  {
    return ( payment_amount_designate_2==null? ((double)0): payment_amount_designate_2.doubleValue() );
  }


  /** 
   * <em>payment_amount_designate_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentAmountDesignate2IsNull()
  {
    return payment_amount_designate_2 == null;
  }


  /** 
   * <em>payment_amount_designate_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate2IsSet() {
    return payment_amount_designate_2_is_set;
  }


  /** 
   * <em>payment_amount_designate_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate2IsModified() {
    return payment_amount_designate_2_is_modified;
  }


  /** 
   * <em>payment_amount_designate_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentAmountDesignate3()
  {
    return ( payment_amount_designate_3==null? ((double)0): payment_amount_designate_3.doubleValue() );
  }


  /** 
   * <em>payment_amount_designate_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentAmountDesignate3IsNull()
  {
    return payment_amount_designate_3 == null;
  }


  /** 
   * <em>payment_amount_designate_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate3IsSet() {
    return payment_amount_designate_3_is_set;
  }


  /** 
   * <em>payment_amount_designate_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate3IsModified() {
    return payment_amount_designate_3_is_modified;
  }


  /** 
   * <em>payment_amount_designate_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentAmountDesignate4()
  {
    return ( payment_amount_designate_4==null? ((double)0): payment_amount_designate_4.doubleValue() );
  }


  /** 
   * <em>payment_amount_designate_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentAmountDesignate4IsNull()
  {
    return payment_amount_designate_4 == null;
  }


  /** 
   * <em>payment_amount_designate_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate4IsSet() {
    return payment_amount_designate_4_is_set;
  }


  /** 
   * <em>payment_amount_designate_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate4IsModified() {
    return payment_amount_designate_4_is_modified;
  }


  /** 
   * <em>payment_amount_designate_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentAmountDesignate5()
  {
    return ( payment_amount_designate_5==null? ((double)0): payment_amount_designate_5.doubleValue() );
  }


  /** 
   * <em>payment_amount_designate_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentAmountDesignate5IsNull()
  {
    return payment_amount_designate_5 == null;
  }


  /** 
   * <em>payment_amount_designate_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate5IsSet() {
    return payment_amount_designate_5_is_set;
  }


  /** 
   * <em>payment_amount_designate_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate5IsModified() {
    return payment_amount_designate_5_is_modified;
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
   * <em>today_dep_fund_restraint_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayDepFundRestraint0()
  {
    return ( today_dep_fund_restraint_0==null? ((double)0): today_dep_fund_restraint_0.doubleValue() );
  }


  /** 
   * <em>today_dep_fund_restraint_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTodayDepFundRestraint0IsNull()
  {
    return today_dep_fund_restraint_0 == null;
  }


  /** 
   * <em>today_dep_fund_restraint_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayDepFundRestraint0IsSet() {
    return today_dep_fund_restraint_0_is_set;
  }


  /** 
   * <em>today_dep_fund_restraint_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayDepFundRestraint0IsModified() {
    return today_dep_fund_restraint_0_is_modified;
  }


  /** 
   * <em>today_dep_fund_restraint_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayDepFundRestraint1()
  {
    return ( today_dep_fund_restraint_1==null? ((double)0): today_dep_fund_restraint_1.doubleValue() );
  }


  /** 
   * <em>today_dep_fund_restraint_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTodayDepFundRestraint1IsNull()
  {
    return today_dep_fund_restraint_1 == null;
  }


  /** 
   * <em>today_dep_fund_restraint_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayDepFundRestraint1IsSet() {
    return today_dep_fund_restraint_1_is_set;
  }


  /** 
   * <em>today_dep_fund_restraint_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayDepFundRestraint1IsModified() {
    return today_dep_fund_restraint_1_is_modified;
  }


  /** 
   * <em>today_dep_fund_restraint_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayDepFundRestraint2()
  {
    return ( today_dep_fund_restraint_2==null? ((double)0): today_dep_fund_restraint_2.doubleValue() );
  }


  /** 
   * <em>today_dep_fund_restraint_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTodayDepFundRestraint2IsNull()
  {
    return today_dep_fund_restraint_2 == null;
  }


  /** 
   * <em>today_dep_fund_restraint_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayDepFundRestraint2IsSet() {
    return today_dep_fund_restraint_2_is_set;
  }


  /** 
   * <em>today_dep_fund_restraint_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayDepFundRestraint2IsModified() {
    return today_dep_fund_restraint_2_is_modified;
  }


  /** 
   * <em>today_dep_fund_restraint_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayDepFundRestraint3()
  {
    return ( today_dep_fund_restraint_3==null? ((double)0): today_dep_fund_restraint_3.doubleValue() );
  }


  /** 
   * <em>today_dep_fund_restraint_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTodayDepFundRestraint3IsNull()
  {
    return today_dep_fund_restraint_3 == null;
  }


  /** 
   * <em>today_dep_fund_restraint_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayDepFundRestraint3IsSet() {
    return today_dep_fund_restraint_3_is_set;
  }


  /** 
   * <em>today_dep_fund_restraint_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayDepFundRestraint3IsModified() {
    return today_dep_fund_restraint_3_is_modified;
  }


  /** 
   * <em>today_dep_fund_restraint_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayDepFundRestraint4()
  {
    return ( today_dep_fund_restraint_4==null? ((double)0): today_dep_fund_restraint_4.doubleValue() );
  }


  /** 
   * <em>today_dep_fund_restraint_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTodayDepFundRestraint4IsNull()
  {
    return today_dep_fund_restraint_4 == null;
  }


  /** 
   * <em>today_dep_fund_restraint_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayDepFundRestraint4IsSet() {
    return today_dep_fund_restraint_4_is_set;
  }


  /** 
   * <em>today_dep_fund_restraint_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayDepFundRestraint4IsModified() {
    return today_dep_fund_restraint_4_is_modified;
  }


  /** 
   * <em>today_dep_fund_restraint_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayDepFundRestraint5()
  {
    return ( today_dep_fund_restraint_5==null? ((double)0): today_dep_fund_restraint_5.doubleValue() );
  }


  /** 
   * <em>today_dep_fund_restraint_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTodayDepFundRestraint5IsNull()
  {
    return today_dep_fund_restraint_5 == null;
  }


  /** 
   * <em>today_dep_fund_restraint_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayDepFundRestraint5IsSet() {
    return today_dep_fund_restraint_5_is_set;
  }


  /** 
   * <em>today_dep_fund_restraint_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayDepFundRestraint5IsModified() {
    return today_dep_fund_restraint_5_is_modified;
  }


  /** 
   * <em>contract_asset_loss_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetLoss1()
  {
    return ( contract_asset_loss_1==null? ((double)0): contract_asset_loss_1.doubleValue() );
  }


  /** 
   * <em>contract_asset_loss_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetLoss1IsNull()
  {
    return contract_asset_loss_1 == null;
  }


  /** 
   * <em>contract_asset_loss_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetLoss1IsSet() {
    return contract_asset_loss_1_is_set;
  }


  /** 
   * <em>contract_asset_loss_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetLoss1IsModified() {
    return contract_asset_loss_1_is_modified;
  }


  /** 
   * <em>contract_asset_loss_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetLoss2()
  {
    return ( contract_asset_loss_2==null? ((double)0): contract_asset_loss_2.doubleValue() );
  }


  /** 
   * <em>contract_asset_loss_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetLoss2IsNull()
  {
    return contract_asset_loss_2 == null;
  }


  /** 
   * <em>contract_asset_loss_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetLoss2IsSet() {
    return contract_asset_loss_2_is_set;
  }


  /** 
   * <em>contract_asset_loss_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetLoss2IsModified() {
    return contract_asset_loss_2_is_modified;
  }


  /** 
   * <em>contract_asset_loss_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetLoss3()
  {
    return ( contract_asset_loss_3==null? ((double)0): contract_asset_loss_3.doubleValue() );
  }


  /** 
   * <em>contract_asset_loss_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetLoss3IsNull()
  {
    return contract_asset_loss_3 == null;
  }


  /** 
   * <em>contract_asset_loss_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetLoss3IsSet() {
    return contract_asset_loss_3_is_set;
  }


  /** 
   * <em>contract_asset_loss_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetLoss3IsModified() {
    return contract_asset_loss_3_is_modified;
  }


  /** 
   * <em>contract_asset_loss_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetLoss4()
  {
    return ( contract_asset_loss_4==null? ((double)0): contract_asset_loss_4.doubleValue() );
  }


  /** 
   * <em>contract_asset_loss_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetLoss4IsNull()
  {
    return contract_asset_loss_4 == null;
  }


  /** 
   * <em>contract_asset_loss_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetLoss4IsSet() {
    return contract_asset_loss_4_is_set;
  }


  /** 
   * <em>contract_asset_loss_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetLoss4IsModified() {
    return contract_asset_loss_4_is_modified;
  }


  /** 
   * <em>contract_asset_loss_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetLoss5()
  {
    return ( contract_asset_loss_5==null? ((double)0): contract_asset_loss_5.doubleValue() );
  }


  /** 
   * <em>contract_asset_loss_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetLoss5IsNull()
  {
    return contract_asset_loss_5 == null;
  }


  /** 
   * <em>contract_asset_loss_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetLoss5IsSet() {
    return contract_asset_loss_5_is_set;
  }


  /** 
   * <em>contract_asset_loss_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetLoss5IsModified() {
    return contract_asset_loss_5_is_modified;
  }


  /** 
   * <em>contract_asset_profit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetProfit1()
  {
    return ( contract_asset_profit_1==null? ((double)0): contract_asset_profit_1.doubleValue() );
  }


  /** 
   * <em>contract_asset_profit_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetProfit1IsNull()
  {
    return contract_asset_profit_1 == null;
  }


  /** 
   * <em>contract_asset_profit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfit1IsSet() {
    return contract_asset_profit_1_is_set;
  }


  /** 
   * <em>contract_asset_profit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfit1IsModified() {
    return contract_asset_profit_1_is_modified;
  }


  /** 
   * <em>contract_asset_profit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetProfit2()
  {
    return ( contract_asset_profit_2==null? ((double)0): contract_asset_profit_2.doubleValue() );
  }


  /** 
   * <em>contract_asset_profit_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetProfit2IsNull()
  {
    return contract_asset_profit_2 == null;
  }


  /** 
   * <em>contract_asset_profit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfit2IsSet() {
    return contract_asset_profit_2_is_set;
  }


  /** 
   * <em>contract_asset_profit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfit2IsModified() {
    return contract_asset_profit_2_is_modified;
  }


  /** 
   * <em>contract_asset_profit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetProfit3()
  {
    return ( contract_asset_profit_3==null? ((double)0): contract_asset_profit_3.doubleValue() );
  }


  /** 
   * <em>contract_asset_profit_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetProfit3IsNull()
  {
    return contract_asset_profit_3 == null;
  }


  /** 
   * <em>contract_asset_profit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfit3IsSet() {
    return contract_asset_profit_3_is_set;
  }


  /** 
   * <em>contract_asset_profit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfit3IsModified() {
    return contract_asset_profit_3_is_modified;
  }


  /** 
   * <em>contract_asset_profit_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetProfit4()
  {
    return ( contract_asset_profit_4==null? ((double)0): contract_asset_profit_4.doubleValue() );
  }


  /** 
   * <em>contract_asset_profit_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetProfit4IsNull()
  {
    return contract_asset_profit_4 == null;
  }


  /** 
   * <em>contract_asset_profit_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfit4IsSet() {
    return contract_asset_profit_4_is_set;
  }


  /** 
   * <em>contract_asset_profit_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfit4IsModified() {
    return contract_asset_profit_4_is_modified;
  }


  /** 
   * <em>contract_asset_profit_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetProfit5()
  {
    return ( contract_asset_profit_5==null? ((double)0): contract_asset_profit_5.doubleValue() );
  }


  /** 
   * <em>contract_asset_profit_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetProfit5IsNull()
  {
    return contract_asset_profit_5 == null;
  }


  /** 
   * <em>contract_asset_profit_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfit5IsSet() {
    return contract_asset_profit_5_is_set;
  }


  /** 
   * <em>contract_asset_profit_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfit5IsModified() {
    return contract_asset_profit_5_is_modified;
  }


  /** 
   * <em>foreign_equity_asset_delivered</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getForeignEquityAssetDelivered()
  {
    return ( foreign_equity_asset_delivered==null? ((double)0): foreign_equity_asset_delivered.doubleValue() );
  }


  /** 
   * <em>foreign_equity_asset_delivered</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getForeignEquityAssetDeliveredIsNull()
  {
    return foreign_equity_asset_delivered == null;
  }


  /** 
   * <em>foreign_equity_asset_delivered</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignEquityAssetDeliveredIsSet() {
    return foreign_equity_asset_delivered_is_set;
  }


  /** 
   * <em>foreign_equity_asset_delivered</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignEquityAssetDeliveredIsModified() {
    return foreign_equity_asset_delivered_is_modified;
  }


  /** 
   * <em>foreign_equity_asset_executed</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getForeignEquityAssetExecuted()
  {
    return ( foreign_equity_asset_executed==null? ((double)0): foreign_equity_asset_executed.doubleValue() );
  }


  /** 
   * <em>foreign_equity_asset_executed</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getForeignEquityAssetExecutedIsNull()
  {
    return foreign_equity_asset_executed == null;
  }


  /** 
   * <em>foreign_equity_asset_executed</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignEquityAssetExecutedIsSet() {
    return foreign_equity_asset_executed_is_set;
  }


  /** 
   * <em>foreign_equity_asset_executed</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getForeignEquityAssetExecutedIsModified() {
    return foreign_equity_asset_executed_is_modified;
  }


  /** 
   * <em>today_repay_contract_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayRepayContractAmount()
  {
    return ( today_repay_contract_amount==null? ((double)0): today_repay_contract_amount.doubleValue() );
  }


  /** 
   * <em>today_repay_contract_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTodayRepayContractAmountIsNull()
  {
    return today_repay_contract_amount == null;
  }


  /** 
   * <em>today_repay_contract_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayRepayContractAmountIsSet() {
    return today_repay_contract_amount_is_set;
  }


  /** 
   * <em>today_repay_contract_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayRepayContractAmountIsModified() {
    return today_repay_contract_amount_is_modified;
  }


  /** 
   * <em>substitute_asset_old_day_value</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSubstituteAssetOldDayValue()
  {
    return ( substitute_asset_old_day_value==null? ((double)0): substitute_asset_old_day_value.doubleValue() );
  }


  /** 
   * <em>substitute_asset_old_day_value</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSubstituteAssetOldDayValueIsNull()
  {
    return substitute_asset_old_day_value == null;
  }


  /** 
   * <em>substitute_asset_old_day_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteAssetOldDayValueIsSet() {
    return substitute_asset_old_day_value_is_set;
  }


  /** 
   * <em>substitute_asset_old_day_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteAssetOldDayValueIsModified() {
    return substitute_asset_old_day_value_is_modified;
  }


  /** 
   * <em>setup_fee_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSetupFee1()
  {
    return ( setup_fee_1==null? ((double)0): setup_fee_1.doubleValue() );
  }


  /** 
   * <em>setup_fee_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSetupFee1IsNull()
  {
    return setup_fee_1 == null;
  }


  /** 
   * <em>setup_fee_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSetupFee1IsSet() {
    return setup_fee_1_is_set;
  }


  /** 
   * <em>setup_fee_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSetupFee1IsModified() {
    return setup_fee_1_is_modified;
  }


  /** 
   * <em>setup_fee_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSetupFee2()
  {
    return ( setup_fee_2==null? ((double)0): setup_fee_2.doubleValue() );
  }


  /** 
   * <em>setup_fee_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSetupFee2IsNull()
  {
    return setup_fee_2 == null;
  }


  /** 
   * <em>setup_fee_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSetupFee2IsSet() {
    return setup_fee_2_is_set;
  }


  /** 
   * <em>setup_fee_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSetupFee2IsModified() {
    return setup_fee_2_is_modified;
  }


  /** 
   * <em>setup_fee_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSetupFee3()
  {
    return ( setup_fee_3==null? ((double)0): setup_fee_3.doubleValue() );
  }


  /** 
   * <em>setup_fee_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSetupFee3IsNull()
  {
    return setup_fee_3 == null;
  }


  /** 
   * <em>setup_fee_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSetupFee3IsSet() {
    return setup_fee_3_is_set;
  }


  /** 
   * <em>setup_fee_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSetupFee3IsModified() {
    return setup_fee_3_is_modified;
  }


  /** 
   * <em>setup_fee_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSetupFee4()
  {
    return ( setup_fee_4==null? ((double)0): setup_fee_4.doubleValue() );
  }


  /** 
   * <em>setup_fee_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSetupFee4IsNull()
  {
    return setup_fee_4 == null;
  }


  /** 
   * <em>setup_fee_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSetupFee4IsSet() {
    return setup_fee_4_is_set;
  }


  /** 
   * <em>setup_fee_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSetupFee4IsModified() {
    return setup_fee_4_is_modified;
  }


  /** 
   * <em>setup_fee_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSetupFee5()
  {
    return ( setup_fee_5==null? ((double)0): setup_fee_5.doubleValue() );
  }


  /** 
   * <em>setup_fee_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSetupFee5IsNull()
  {
    return setup_fee_5 == null;
  }


  /** 
   * <em>setup_fee_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSetupFee5IsSet() {
    return setup_fee_5_is_set;
  }


  /** 
   * <em>setup_fee_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSetupFee5IsModified() {
    return setup_fee_5_is_modified;
  }


  /** 
   * <em>contract_interest_loss_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractInterestLoss1()
  {
    return ( contract_interest_loss_1==null? ((double)0): contract_interest_loss_1.doubleValue() );
  }


  /** 
   * <em>contract_interest_loss_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractInterestLoss1IsNull()
  {
    return contract_interest_loss_1 == null;
  }


  /** 
   * <em>contract_interest_loss_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestLoss1IsSet() {
    return contract_interest_loss_1_is_set;
  }


  /** 
   * <em>contract_interest_loss_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestLoss1IsModified() {
    return contract_interest_loss_1_is_modified;
  }


  /** 
   * <em>contract_interest_loss_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractInterestLoss2()
  {
    return ( contract_interest_loss_2==null? ((double)0): contract_interest_loss_2.doubleValue() );
  }


  /** 
   * <em>contract_interest_loss_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractInterestLoss2IsNull()
  {
    return contract_interest_loss_2 == null;
  }


  /** 
   * <em>contract_interest_loss_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestLoss2IsSet() {
    return contract_interest_loss_2_is_set;
  }


  /** 
   * <em>contract_interest_loss_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestLoss2IsModified() {
    return contract_interest_loss_2_is_modified;
  }


  /** 
   * <em>contract_interest_loss_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractInterestLoss3()
  {
    return ( contract_interest_loss_3==null? ((double)0): contract_interest_loss_3.doubleValue() );
  }


  /** 
   * <em>contract_interest_loss_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractInterestLoss3IsNull()
  {
    return contract_interest_loss_3 == null;
  }


  /** 
   * <em>contract_interest_loss_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestLoss3IsSet() {
    return contract_interest_loss_3_is_set;
  }


  /** 
   * <em>contract_interest_loss_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestLoss3IsModified() {
    return contract_interest_loss_3_is_modified;
  }


  /** 
   * <em>contract_interest_loss_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractInterestLoss4()
  {
    return ( contract_interest_loss_4==null? ((double)0): contract_interest_loss_4.doubleValue() );
  }


  /** 
   * <em>contract_interest_loss_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractInterestLoss4IsNull()
  {
    return contract_interest_loss_4 == null;
  }


  /** 
   * <em>contract_interest_loss_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestLoss4IsSet() {
    return contract_interest_loss_4_is_set;
  }


  /** 
   * <em>contract_interest_loss_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestLoss4IsModified() {
    return contract_interest_loss_4_is_modified;
  }


  /** 
   * <em>contract_interest_loss_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractInterestLoss5()
  {
    return ( contract_interest_loss_5==null? ((double)0): contract_interest_loss_5.doubleValue() );
  }


  /** 
   * <em>contract_interest_loss_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractInterestLoss5IsNull()
  {
    return contract_interest_loss_5 == null;
  }


  /** 
   * <em>contract_interest_loss_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestLoss5IsSet() {
    return contract_interest_loss_5_is_set;
  }


  /** 
   * <em>contract_interest_loss_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestLoss5IsModified() {
    return contract_interest_loss_5_is_modified;
  }


  /** 
   * <em>contract_interest_profit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractInterestProfit1()
  {
    return ( contract_interest_profit_1==null? ((double)0): contract_interest_profit_1.doubleValue() );
  }


  /** 
   * <em>contract_interest_profit_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractInterestProfit1IsNull()
  {
    return contract_interest_profit_1 == null;
  }


  /** 
   * <em>contract_interest_profit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestProfit1IsSet() {
    return contract_interest_profit_1_is_set;
  }


  /** 
   * <em>contract_interest_profit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestProfit1IsModified() {
    return contract_interest_profit_1_is_modified;
  }


  /** 
   * <em>contract_interest_profit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractInterestProfit2()
  {
    return ( contract_interest_profit_2==null? ((double)0): contract_interest_profit_2.doubleValue() );
  }


  /** 
   * <em>contract_interest_profit_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractInterestProfit2IsNull()
  {
    return contract_interest_profit_2 == null;
  }


  /** 
   * <em>contract_interest_profit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestProfit2IsSet() {
    return contract_interest_profit_2_is_set;
  }


  /** 
   * <em>contract_interest_profit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestProfit2IsModified() {
    return contract_interest_profit_2_is_modified;
  }


  /** 
   * <em>contract_interest_profit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractInterestProfit3()
  {
    return ( contract_interest_profit_3==null? ((double)0): contract_interest_profit_3.doubleValue() );
  }


  /** 
   * <em>contract_interest_profit_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractInterestProfit3IsNull()
  {
    return contract_interest_profit_3 == null;
  }


  /** 
   * <em>contract_interest_profit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestProfit3IsSet() {
    return contract_interest_profit_3_is_set;
  }


  /** 
   * <em>contract_interest_profit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestProfit3IsModified() {
    return contract_interest_profit_3_is_modified;
  }


  /** 
   * <em>contract_interest_profit_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractInterestProfit4()
  {
    return ( contract_interest_profit_4==null? ((double)0): contract_interest_profit_4.doubleValue() );
  }


  /** 
   * <em>contract_interest_profit_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractInterestProfit4IsNull()
  {
    return contract_interest_profit_4 == null;
  }


  /** 
   * <em>contract_interest_profit_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestProfit4IsSet() {
    return contract_interest_profit_4_is_set;
  }


  /** 
   * <em>contract_interest_profit_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestProfit4IsModified() {
    return contract_interest_profit_4_is_modified;
  }


  /** 
   * <em>contract_interest_profit_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractInterestProfit5()
  {
    return ( contract_interest_profit_5==null? ((double)0): contract_interest_profit_5.doubleValue() );
  }


  /** 
   * <em>contract_interest_profit_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractInterestProfit5IsNull()
  {
    return contract_interest_profit_5 == null;
  }


  /** 
   * <em>contract_interest_profit_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestProfit5IsSet() {
    return contract_interest_profit_5_is_set;
  }


  /** 
   * <em>contract_interest_profit_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractInterestProfit5IsModified() {
    return contract_interest_profit_5_is_modified;
  }


  /** 
   * <em>contract_other_cost_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractOtherCost1()
  {
    return ( contract_other_cost_1==null? ((double)0): contract_other_cost_1.doubleValue() );
  }


  /** 
   * <em>contract_other_cost_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractOtherCost1IsNull()
  {
    return contract_other_cost_1 == null;
  }


  /** 
   * <em>contract_other_cost_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractOtherCost1IsSet() {
    return contract_other_cost_1_is_set;
  }


  /** 
   * <em>contract_other_cost_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractOtherCost1IsModified() {
    return contract_other_cost_1_is_modified;
  }


  /** 
   * <em>contract_other_cost_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractOtherCost2()
  {
    return ( contract_other_cost_2==null? ((double)0): contract_other_cost_2.doubleValue() );
  }


  /** 
   * <em>contract_other_cost_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractOtherCost2IsNull()
  {
    return contract_other_cost_2 == null;
  }


  /** 
   * <em>contract_other_cost_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractOtherCost2IsSet() {
    return contract_other_cost_2_is_set;
  }


  /** 
   * <em>contract_other_cost_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractOtherCost2IsModified() {
    return contract_other_cost_2_is_modified;
  }


  /** 
   * <em>contract_other_cost_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractOtherCost3()
  {
    return ( contract_other_cost_3==null? ((double)0): contract_other_cost_3.doubleValue() );
  }


  /** 
   * <em>contract_other_cost_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractOtherCost3IsNull()
  {
    return contract_other_cost_3 == null;
  }


  /** 
   * <em>contract_other_cost_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractOtherCost3IsSet() {
    return contract_other_cost_3_is_set;
  }


  /** 
   * <em>contract_other_cost_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractOtherCost3IsModified() {
    return contract_other_cost_3_is_modified;
  }


  /** 
   * <em>contract_other_cost_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractOtherCost4()
  {
    return ( contract_other_cost_4==null? ((double)0): contract_other_cost_4.doubleValue() );
  }


  /** 
   * <em>contract_other_cost_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractOtherCost4IsNull()
  {
    return contract_other_cost_4 == null;
  }


  /** 
   * <em>contract_other_cost_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractOtherCost4IsSet() {
    return contract_other_cost_4_is_set;
  }


  /** 
   * <em>contract_other_cost_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractOtherCost4IsModified() {
    return contract_other_cost_4_is_modified;
  }


  /** 
   * <em>contract_other_cost_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractOtherCost5()
  {
    return ( contract_other_cost_5==null? ((double)0): contract_other_cost_5.doubleValue() );
  }


  /** 
   * <em>contract_other_cost_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractOtherCost5IsNull()
  {
    return contract_other_cost_5 == null;
  }


  /** 
   * <em>contract_other_cost_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractOtherCost5IsSet() {
    return contract_other_cost_5_is_set;
  }


  /** 
   * <em>contract_other_cost_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractOtherCost5IsModified() {
    return contract_other_cost_5_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new TpCalcResultMarginDetailPK(calc_result_margin_id);
  }


  /** 
   * <em>calc_result_margin_id</em>カラムの値を設定します。 
   *
   * @@param p_calcResultMarginId <em>calc_result_margin_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setCalcResultMarginId( long p_calcResultMarginId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_result_margin_id = p_calcResultMarginId;
    calc_result_margin_id_is_set = true;
    calc_result_margin_id_is_modified = true;
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
   * <em>equity_asset_delivered</em>カラムの値を設定します。 
   *
   * @@param p_equityAssetDelivered <em>equity_asset_delivered</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setEquityAssetDelivered( double p_equityAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_asset_delivered = new Double(p_equityAssetDelivered);
    equity_asset_delivered_is_set = true;
    equity_asset_delivered_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>equity_asset_delivered</em>カラムに値を設定します。 
   */
  public final void setEquityAssetDelivered( Double p_equityAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    equity_asset_delivered = p_equityAssetDelivered;
    equity_asset_delivered_is_set = true;
    equity_asset_delivered_is_modified = true;
  }


  /** 
   * <em>equity_asset_executed</em>カラムの値を設定します。 
   *
   * @@param p_equityAssetExecuted <em>equity_asset_executed</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setEquityAssetExecuted( double p_equityAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_asset_executed = new Double(p_equityAssetExecuted);
    equity_asset_executed_is_set = true;
    equity_asset_executed_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>equity_asset_executed</em>カラムに値を設定します。 
   */
  public final void setEquityAssetExecuted( Double p_equityAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    equity_asset_executed = p_equityAssetExecuted;
    equity_asset_executed_is_set = true;
    equity_asset_executed_is_modified = true;
  }


  /** 
   * <em>mini_stock_asset_delivered</em>カラムの値を設定します。 
   *
   * @@param p_miniStockAssetDelivered <em>mini_stock_asset_delivered</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMiniStockAssetDelivered( double p_miniStockAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_asset_delivered = new Double(p_miniStockAssetDelivered);
    mini_stock_asset_delivered_is_set = true;
    mini_stock_asset_delivered_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>mini_stock_asset_delivered</em>カラムに値を設定します。 
   */
  public final void setMiniStockAssetDelivered( Double p_miniStockAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_asset_delivered = p_miniStockAssetDelivered;
    mini_stock_asset_delivered_is_set = true;
    mini_stock_asset_delivered_is_modified = true;
  }


  /** 
   * <em>mini_stock_asset_executed</em>カラムの値を設定します。 
   *
   * @@param p_miniStockAssetExecuted <em>mini_stock_asset_executed</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMiniStockAssetExecuted( double p_miniStockAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_asset_executed = new Double(p_miniStockAssetExecuted);
    mini_stock_asset_executed_is_set = true;
    mini_stock_asset_executed_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>mini_stock_asset_executed</em>カラムに値を設定します。 
   */
  public final void setMiniStockAssetExecuted( Double p_miniStockAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mini_stock_asset_executed = p_miniStockAssetExecuted;
    mini_stock_asset_executed_is_set = true;
    mini_stock_asset_executed_is_modified = true;
  }


  /** 
   * <em>ruito_asset_delivered</em>カラムの値を設定します。 
   *
   * @@param p_ruitoAssetDelivered <em>ruito_asset_delivered</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRuitoAssetDelivered( double p_ruitoAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ruito_asset_delivered = new Double(p_ruitoAssetDelivered);
    ruito_asset_delivered_is_set = true;
    ruito_asset_delivered_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ruito_asset_delivered</em>カラムに値を設定します。 
   */
  public final void setRuitoAssetDelivered( Double p_ruitoAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ruito_asset_delivered = p_ruitoAssetDelivered;
    ruito_asset_delivered_is_set = true;
    ruito_asset_delivered_is_modified = true;
  }


  /** 
   * <em>ruito_asset_executed</em>カラムの値を設定します。 
   *
   * @@param p_ruitoAssetExecuted <em>ruito_asset_executed</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRuitoAssetExecuted( double p_ruitoAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ruito_asset_executed = new Double(p_ruitoAssetExecuted);
    ruito_asset_executed_is_set = true;
    ruito_asset_executed_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ruito_asset_executed</em>カラムに値を設定します。 
   */
  public final void setRuitoAssetExecuted( Double p_ruitoAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ruito_asset_executed = p_ruitoAssetExecuted;
    ruito_asset_executed_is_set = true;
    ruito_asset_executed_is_modified = true;
  }


  /** 
   * <em>mutual_fund_asset_delivered</em>カラムの値を設定します。 
   *
   * @@param p_mutualFundAssetDelivered <em>mutual_fund_asset_delivered</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMutualFundAssetDelivered( double p_mutualFundAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mutual_fund_asset_delivered = new Double(p_mutualFundAssetDelivered);
    mutual_fund_asset_delivered_is_set = true;
    mutual_fund_asset_delivered_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>mutual_fund_asset_delivered</em>カラムに値を設定します。 
   */
  public final void setMutualFundAssetDelivered( Double p_mutualFundAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mutual_fund_asset_delivered = p_mutualFundAssetDelivered;
    mutual_fund_asset_delivered_is_set = true;
    mutual_fund_asset_delivered_is_modified = true;
  }


  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値を設定します。 
   *
   * @@param p_mutualFundAssetExecuted <em>mutual_fund_asset_executed</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMutualFundAssetExecuted( double p_mutualFundAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mutual_fund_asset_executed = new Double(p_mutualFundAssetExecuted);
    mutual_fund_asset_executed_is_set = true;
    mutual_fund_asset_executed_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>mutual_fund_asset_executed</em>カラムに値を設定します。 
   */
  public final void setMutualFundAssetExecuted( Double p_mutualFundAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mutual_fund_asset_executed = p_mutualFundAssetExecuted;
    mutual_fund_asset_executed_is_set = true;
    mutual_fund_asset_executed_is_modified = true;
  }


  /** 
   * <em>bond_asset_delivered</em>カラムの値を設定します。 
   *
   * @@param p_bondAssetDelivered <em>bond_asset_delivered</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBondAssetDelivered( double p_bondAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bond_asset_delivered = new Double(p_bondAssetDelivered);
    bond_asset_delivered_is_set = true;
    bond_asset_delivered_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>bond_asset_delivered</em>カラムに値を設定します。 
   */
  public final void setBondAssetDelivered( Double p_bondAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bond_asset_delivered = p_bondAssetDelivered;
    bond_asset_delivered_is_set = true;
    bond_asset_delivered_is_modified = true;
  }


  /** 
   * <em>bond_asset_executed</em>カラムの値を設定します。 
   *
   * @@param p_bondAssetExecuted <em>bond_asset_executed</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setBondAssetExecuted( double p_bondAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bond_asset_executed = new Double(p_bondAssetExecuted);
    bond_asset_executed_is_set = true;
    bond_asset_executed_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>bond_asset_executed</em>カラムに値を設定します。 
   */
  public final void setBondAssetExecuted( Double p_bondAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bond_asset_executed = p_bondAssetExecuted;
    bond_asset_executed_is_set = true;
    bond_asset_executed_is_modified = true;
  }


  /** 
   * <em>unexec_substi_security_asset_0</em>カラムの値を設定します。 
   *
   * @@param p_unexecSubstiSecurityAsset0 <em>unexec_substi_security_asset_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecSubstiSecurityAsset0( double p_unexecSubstiSecurityAsset0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_substi_security_asset_0 = new Double(p_unexecSubstiSecurityAsset0);
    unexec_substi_security_asset_0_is_set = true;
    unexec_substi_security_asset_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_substi_security_asset_0</em>カラムに値を設定します。 
   */
  public final void setUnexecSubstiSecurityAsset0( Double p_unexecSubstiSecurityAsset0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_substi_security_asset_0 = p_unexecSubstiSecurityAsset0;
    unexec_substi_security_asset_0_is_set = true;
    unexec_substi_security_asset_0_is_modified = true;
  }


  /** 
   * <em>unexec_substi_security_asset_1</em>カラムの値を設定します。 
   *
   * @@param p_unexecSubstiSecurityAsset1 <em>unexec_substi_security_asset_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecSubstiSecurityAsset1( double p_unexecSubstiSecurityAsset1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_substi_security_asset_1 = new Double(p_unexecSubstiSecurityAsset1);
    unexec_substi_security_asset_1_is_set = true;
    unexec_substi_security_asset_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_substi_security_asset_1</em>カラムに値を設定します。 
   */
  public final void setUnexecSubstiSecurityAsset1( Double p_unexecSubstiSecurityAsset1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_substi_security_asset_1 = p_unexecSubstiSecurityAsset1;
    unexec_substi_security_asset_1_is_set = true;
    unexec_substi_security_asset_1_is_modified = true;
  }


  /** 
   * <em>unexec_substi_security_asset_2</em>カラムの値を設定します。 
   *
   * @@param p_unexecSubstiSecurityAsset2 <em>unexec_substi_security_asset_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecSubstiSecurityAsset2( double p_unexecSubstiSecurityAsset2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_substi_security_asset_2 = new Double(p_unexecSubstiSecurityAsset2);
    unexec_substi_security_asset_2_is_set = true;
    unexec_substi_security_asset_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_substi_security_asset_2</em>カラムに値を設定します。 
   */
  public final void setUnexecSubstiSecurityAsset2( Double p_unexecSubstiSecurityAsset2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_substi_security_asset_2 = p_unexecSubstiSecurityAsset2;
    unexec_substi_security_asset_2_is_set = true;
    unexec_substi_security_asset_2_is_modified = true;
  }


  /** 
   * <em>unexec_substi_security_asset_3</em>カラムの値を設定します。 
   *
   * @@param p_unexecSubstiSecurityAsset3 <em>unexec_substi_security_asset_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecSubstiSecurityAsset3( double p_unexecSubstiSecurityAsset3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_substi_security_asset_3 = new Double(p_unexecSubstiSecurityAsset3);
    unexec_substi_security_asset_3_is_set = true;
    unexec_substi_security_asset_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_substi_security_asset_3</em>カラムに値を設定します。 
   */
  public final void setUnexecSubstiSecurityAsset3( Double p_unexecSubstiSecurityAsset3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_substi_security_asset_3 = p_unexecSubstiSecurityAsset3;
    unexec_substi_security_asset_3_is_set = true;
    unexec_substi_security_asset_3_is_modified = true;
  }


  /** 
   * <em>unexec_substi_security_asset_4</em>カラムの値を設定します。 
   *
   * @@param p_unexecSubstiSecurityAsset4 <em>unexec_substi_security_asset_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecSubstiSecurityAsset4( double p_unexecSubstiSecurityAsset4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_substi_security_asset_4 = new Double(p_unexecSubstiSecurityAsset4);
    unexec_substi_security_asset_4_is_set = true;
    unexec_substi_security_asset_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_substi_security_asset_4</em>カラムに値を設定します。 
   */
  public final void setUnexecSubstiSecurityAsset4( Double p_unexecSubstiSecurityAsset4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_substi_security_asset_4 = p_unexecSubstiSecurityAsset4;
    unexec_substi_security_asset_4_is_set = true;
    unexec_substi_security_asset_4_is_modified = true;
  }


  /** 
   * <em>unexec_substi_security_asset_5</em>カラムの値を設定します。 
   *
   * @@param p_unexecSubstiSecurityAsset5 <em>unexec_substi_security_asset_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecSubstiSecurityAsset5( double p_unexecSubstiSecurityAsset5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_substi_security_asset_5 = new Double(p_unexecSubstiSecurityAsset5);
    unexec_substi_security_asset_5_is_set = true;
    unexec_substi_security_asset_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_substi_security_asset_5</em>カラムに値を設定します。 
   */
  public final void setUnexecSubstiSecurityAsset5( Double p_unexecSubstiSecurityAsset5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_substi_security_asset_5 = p_unexecSubstiSecurityAsset5;
    unexec_substi_security_asset_5_is_set = true;
    unexec_substi_security_asset_5_is_modified = true;
  }


  /** 
   * <em>contract_asset_loss</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetLoss <em>contract_asset_loss</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetLoss( double p_contractAssetLoss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_loss = new Double(p_contractAssetLoss);
    contract_asset_loss_is_set = true;
    contract_asset_loss_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_loss</em>カラムに値を設定します。 
   */
  public final void setContractAssetLoss( Double p_contractAssetLoss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_loss = p_contractAssetLoss;
    contract_asset_loss_is_set = true;
    contract_asset_loss_is_modified = true;
  }


  /** 
   * <em>contract_asset_profit</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetProfit <em>contract_asset_profit</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetProfit( double p_contractAssetProfit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit = new Double(p_contractAssetProfit);
    contract_asset_profit_is_set = true;
    contract_asset_profit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_profit</em>カラムに値を設定します。 
   */
  public final void setContractAssetProfit( Double p_contractAssetProfit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit = p_contractAssetProfit;
    contract_asset_profit_is_set = true;
    contract_asset_profit_is_modified = true;
  }


  /** 
   * <em>setup_fee</em>カラムの値を設定します。 
   *
   * @@param p_setupFee <em>setup_fee</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSetupFee( double p_setupFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    setup_fee = new Double(p_setupFee);
    setup_fee_is_set = true;
    setup_fee_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>setup_fee</em>カラムに値を設定します。 
   */
  public final void setSetupFee( Double p_setupFee )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    setup_fee = p_setupFee;
    setup_fee_is_set = true;
    setup_fee_is_modified = true;
  }


  /** 
   * <em>contract_interest_loss</em>カラムの値を設定します。 
   *
   * @@param p_contractInterestLoss <em>contract_interest_loss</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractInterestLoss( double p_contractInterestLoss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_loss = new Double(p_contractInterestLoss);
    contract_interest_loss_is_set = true;
    contract_interest_loss_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_interest_loss</em>カラムに値を設定します。 
   */
  public final void setContractInterestLoss( Double p_contractInterestLoss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_loss = p_contractInterestLoss;
    contract_interest_loss_is_set = true;
    contract_interest_loss_is_modified = true;
  }


  /** 
   * <em>contract_interest_profit</em>カラムの値を設定します。 
   *
   * @@param p_contractInterestProfit <em>contract_interest_profit</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractInterestProfit( double p_contractInterestProfit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_profit = new Double(p_contractInterestProfit);
    contract_interest_profit_is_set = true;
    contract_interest_profit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_interest_profit</em>カラムに値を設定します。 
   */
  public final void setContractInterestProfit( Double p_contractInterestProfit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_profit = p_contractInterestProfit;
    contract_interest_profit_is_set = true;
    contract_interest_profit_is_modified = true;
  }


  /** 
   * <em>contract_other_cost</em>カラムの値を設定します。 
   *
   * @@param p_contractOtherCost <em>contract_other_cost</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractOtherCost( double p_contractOtherCost )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_other_cost = new Double(p_contractOtherCost);
    contract_other_cost_is_set = true;
    contract_other_cost_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_other_cost</em>カラムに値を設定します。 
   */
  public final void setContractOtherCost( Double p_contractOtherCost )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_other_cost = p_contractOtherCost;
    contract_other_cost_is_set = true;
    contract_other_cost_is_modified = true;
  }


  /** 
   * <em>unexec_contract_amount_0</em>カラムの値を設定します。 
   *
   * @@param p_unexecContractAmount0 <em>unexec_contract_amount_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecContractAmount0( double p_unexecContractAmount0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_contract_amount_0 = new Double(p_unexecContractAmount0);
    unexec_contract_amount_0_is_set = true;
    unexec_contract_amount_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_contract_amount_0</em>カラムに値を設定します。 
   */
  public final void setUnexecContractAmount0( Double p_unexecContractAmount0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_contract_amount_0 = p_unexecContractAmount0;
    unexec_contract_amount_0_is_set = true;
    unexec_contract_amount_0_is_modified = true;
  }


  /** 
   * <em>unexec_contract_amount_1</em>カラムの値を設定します。 
   *
   * @@param p_unexecContractAmount1 <em>unexec_contract_amount_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecContractAmount1( double p_unexecContractAmount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_contract_amount_1 = new Double(p_unexecContractAmount1);
    unexec_contract_amount_1_is_set = true;
    unexec_contract_amount_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_contract_amount_1</em>カラムに値を設定します。 
   */
  public final void setUnexecContractAmount1( Double p_unexecContractAmount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_contract_amount_1 = p_unexecContractAmount1;
    unexec_contract_amount_1_is_set = true;
    unexec_contract_amount_1_is_modified = true;
  }


  /** 
   * <em>unexec_contract_amount_2</em>カラムの値を設定します。 
   *
   * @@param p_unexecContractAmount2 <em>unexec_contract_amount_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecContractAmount2( double p_unexecContractAmount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_contract_amount_2 = new Double(p_unexecContractAmount2);
    unexec_contract_amount_2_is_set = true;
    unexec_contract_amount_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_contract_amount_2</em>カラムに値を設定します。 
   */
  public final void setUnexecContractAmount2( Double p_unexecContractAmount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_contract_amount_2 = p_unexecContractAmount2;
    unexec_contract_amount_2_is_set = true;
    unexec_contract_amount_2_is_modified = true;
  }


  /** 
   * <em>unexec_contract_amount_3</em>カラムの値を設定します。 
   *
   * @@param p_unexecContractAmount3 <em>unexec_contract_amount_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecContractAmount3( double p_unexecContractAmount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_contract_amount_3 = new Double(p_unexecContractAmount3);
    unexec_contract_amount_3_is_set = true;
    unexec_contract_amount_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_contract_amount_3</em>カラムに値を設定します。 
   */
  public final void setUnexecContractAmount3( Double p_unexecContractAmount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_contract_amount_3 = p_unexecContractAmount3;
    unexec_contract_amount_3_is_set = true;
    unexec_contract_amount_3_is_modified = true;
  }


  /** 
   * <em>unexec_contract_amount_4</em>カラムの値を設定します。 
   *
   * @@param p_unexecContractAmount4 <em>unexec_contract_amount_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecContractAmount4( double p_unexecContractAmount4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_contract_amount_4 = new Double(p_unexecContractAmount4);
    unexec_contract_amount_4_is_set = true;
    unexec_contract_amount_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_contract_amount_4</em>カラムに値を設定します。 
   */
  public final void setUnexecContractAmount4( Double p_unexecContractAmount4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_contract_amount_4 = p_unexecContractAmount4;
    unexec_contract_amount_4_is_set = true;
    unexec_contract_amount_4_is_modified = true;
  }


  /** 
   * <em>unexec_contract_amount_5</em>カラムの値を設定します。 
   *
   * @@param p_unexecContractAmount5 <em>unexec_contract_amount_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecContractAmount5( double p_unexecContractAmount5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_contract_amount_5 = new Double(p_unexecContractAmount5);
    unexec_contract_amount_5_is_set = true;
    unexec_contract_amount_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_contract_amount_5</em>カラムに値を設定します。 
   */
  public final void setUnexecContractAmount5( Double p_unexecContractAmount5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_contract_amount_5 = p_unexecContractAmount5;
    unexec_contract_amount_5_is_set = true;
    unexec_contract_amount_5_is_modified = true;
  }


  /** 
   * <em>unexec_margin_deposit_0</em>カラムの値を設定します。 
   *
   * @@param p_unexecMarginDeposit0 <em>unexec_margin_deposit_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecMarginDeposit0( double p_unexecMarginDeposit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_margin_deposit_0 = new Double(p_unexecMarginDeposit0);
    unexec_margin_deposit_0_is_set = true;
    unexec_margin_deposit_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_margin_deposit_0</em>カラムに値を設定します。 
   */
  public final void setUnexecMarginDeposit0( Double p_unexecMarginDeposit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_margin_deposit_0 = p_unexecMarginDeposit0;
    unexec_margin_deposit_0_is_set = true;
    unexec_margin_deposit_0_is_modified = true;
  }


  /** 
   * <em>unexec_margin_deposit_1</em>カラムの値を設定します。 
   *
   * @@param p_unexecMarginDeposit1 <em>unexec_margin_deposit_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecMarginDeposit1( double p_unexecMarginDeposit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_margin_deposit_1 = new Double(p_unexecMarginDeposit1);
    unexec_margin_deposit_1_is_set = true;
    unexec_margin_deposit_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_margin_deposit_1</em>カラムに値を設定します。 
   */
  public final void setUnexecMarginDeposit1( Double p_unexecMarginDeposit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_margin_deposit_1 = p_unexecMarginDeposit1;
    unexec_margin_deposit_1_is_set = true;
    unexec_margin_deposit_1_is_modified = true;
  }


  /** 
   * <em>unexec_margin_deposit_2</em>カラムの値を設定します。 
   *
   * @@param p_unexecMarginDeposit2 <em>unexec_margin_deposit_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecMarginDeposit2( double p_unexecMarginDeposit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_margin_deposit_2 = new Double(p_unexecMarginDeposit2);
    unexec_margin_deposit_2_is_set = true;
    unexec_margin_deposit_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_margin_deposit_2</em>カラムに値を設定します。 
   */
  public final void setUnexecMarginDeposit2( Double p_unexecMarginDeposit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_margin_deposit_2 = p_unexecMarginDeposit2;
    unexec_margin_deposit_2_is_set = true;
    unexec_margin_deposit_2_is_modified = true;
  }


  /** 
   * <em>unexec_margin_deposit_3</em>カラムの値を設定します。 
   *
   * @@param p_unexecMarginDeposit3 <em>unexec_margin_deposit_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecMarginDeposit3( double p_unexecMarginDeposit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_margin_deposit_3 = new Double(p_unexecMarginDeposit3);
    unexec_margin_deposit_3_is_set = true;
    unexec_margin_deposit_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_margin_deposit_3</em>カラムに値を設定します。 
   */
  public final void setUnexecMarginDeposit3( Double p_unexecMarginDeposit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_margin_deposit_3 = p_unexecMarginDeposit3;
    unexec_margin_deposit_3_is_set = true;
    unexec_margin_deposit_3_is_modified = true;
  }


  /** 
   * <em>unexec_margin_deposit_4</em>カラムの値を設定します。 
   *
   * @@param p_unexecMarginDeposit4 <em>unexec_margin_deposit_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecMarginDeposit4( double p_unexecMarginDeposit4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_margin_deposit_4 = new Double(p_unexecMarginDeposit4);
    unexec_margin_deposit_4_is_set = true;
    unexec_margin_deposit_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_margin_deposit_4</em>カラムに値を設定します。 
   */
  public final void setUnexecMarginDeposit4( Double p_unexecMarginDeposit4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_margin_deposit_4 = p_unexecMarginDeposit4;
    unexec_margin_deposit_4_is_set = true;
    unexec_margin_deposit_4_is_modified = true;
  }


  /** 
   * <em>unexec_margin_deposit_5</em>カラムの値を設定します。 
   *
   * @@param p_unexecMarginDeposit5 <em>unexec_margin_deposit_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecMarginDeposit5( double p_unexecMarginDeposit5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_margin_deposit_5 = new Double(p_unexecMarginDeposit5);
    unexec_margin_deposit_5_is_set = true;
    unexec_margin_deposit_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_margin_deposit_5</em>カラムに値を設定します。 
   */
  public final void setUnexecMarginDeposit5( Double p_unexecMarginDeposit5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_margin_deposit_5 = p_unexecMarginDeposit5;
    unexec_margin_deposit_5_is_set = true;
    unexec_margin_deposit_5_is_modified = true;
  }


  /** 
   * <em>unexec_cash_margin_deposit_0</em>カラムの値を設定します。 
   *
   * @@param p_unexecCashMarginDeposit0 <em>unexec_cash_margin_deposit_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecCashMarginDeposit0( double p_unexecCashMarginDeposit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_cash_margin_deposit_0 = new Double(p_unexecCashMarginDeposit0);
    unexec_cash_margin_deposit_0_is_set = true;
    unexec_cash_margin_deposit_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_cash_margin_deposit_0</em>カラムに値を設定します。 
   */
  public final void setUnexecCashMarginDeposit0( Double p_unexecCashMarginDeposit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_cash_margin_deposit_0 = p_unexecCashMarginDeposit0;
    unexec_cash_margin_deposit_0_is_set = true;
    unexec_cash_margin_deposit_0_is_modified = true;
  }


  /** 
   * <em>unexec_cash_margin_deposit_1</em>カラムの値を設定します。 
   *
   * @@param p_unexecCashMarginDeposit1 <em>unexec_cash_margin_deposit_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecCashMarginDeposit1( double p_unexecCashMarginDeposit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_cash_margin_deposit_1 = new Double(p_unexecCashMarginDeposit1);
    unexec_cash_margin_deposit_1_is_set = true;
    unexec_cash_margin_deposit_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_cash_margin_deposit_1</em>カラムに値を設定します。 
   */
  public final void setUnexecCashMarginDeposit1( Double p_unexecCashMarginDeposit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_cash_margin_deposit_1 = p_unexecCashMarginDeposit1;
    unexec_cash_margin_deposit_1_is_set = true;
    unexec_cash_margin_deposit_1_is_modified = true;
  }


  /** 
   * <em>unexec_cash_margin_deposit_2</em>カラムの値を設定します。 
   *
   * @@param p_unexecCashMarginDeposit2 <em>unexec_cash_margin_deposit_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecCashMarginDeposit2( double p_unexecCashMarginDeposit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_cash_margin_deposit_2 = new Double(p_unexecCashMarginDeposit2);
    unexec_cash_margin_deposit_2_is_set = true;
    unexec_cash_margin_deposit_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_cash_margin_deposit_2</em>カラムに値を設定します。 
   */
  public final void setUnexecCashMarginDeposit2( Double p_unexecCashMarginDeposit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_cash_margin_deposit_2 = p_unexecCashMarginDeposit2;
    unexec_cash_margin_deposit_2_is_set = true;
    unexec_cash_margin_deposit_2_is_modified = true;
  }


  /** 
   * <em>unexec_cash_margin_deposit_3</em>カラムの値を設定します。 
   *
   * @@param p_unexecCashMarginDeposit3 <em>unexec_cash_margin_deposit_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecCashMarginDeposit3( double p_unexecCashMarginDeposit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_cash_margin_deposit_3 = new Double(p_unexecCashMarginDeposit3);
    unexec_cash_margin_deposit_3_is_set = true;
    unexec_cash_margin_deposit_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_cash_margin_deposit_3</em>カラムに値を設定します。 
   */
  public final void setUnexecCashMarginDeposit3( Double p_unexecCashMarginDeposit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_cash_margin_deposit_3 = p_unexecCashMarginDeposit3;
    unexec_cash_margin_deposit_3_is_set = true;
    unexec_cash_margin_deposit_3_is_modified = true;
  }


  /** 
   * <em>unexec_cash_margin_deposit_4</em>カラムの値を設定します。 
   *
   * @@param p_unexecCashMarginDeposit4 <em>unexec_cash_margin_deposit_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecCashMarginDeposit4( double p_unexecCashMarginDeposit4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_cash_margin_deposit_4 = new Double(p_unexecCashMarginDeposit4);
    unexec_cash_margin_deposit_4_is_set = true;
    unexec_cash_margin_deposit_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_cash_margin_deposit_4</em>カラムに値を設定します。 
   */
  public final void setUnexecCashMarginDeposit4( Double p_unexecCashMarginDeposit4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_cash_margin_deposit_4 = p_unexecCashMarginDeposit4;
    unexec_cash_margin_deposit_4_is_set = true;
    unexec_cash_margin_deposit_4_is_modified = true;
  }


  /** 
   * <em>unexec_cash_margin_deposit_5</em>カラムの値を設定します。 
   *
   * @@param p_unexecCashMarginDeposit5 <em>unexec_cash_margin_deposit_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUnexecCashMarginDeposit5( double p_unexecCashMarginDeposit5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_cash_margin_deposit_5 = new Double(p_unexecCashMarginDeposit5);
    unexec_cash_margin_deposit_5_is_set = true;
    unexec_cash_margin_deposit_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unexec_cash_margin_deposit_5</em>カラムに値を設定します。 
   */
  public final void setUnexecCashMarginDeposit5( Double p_unexecCashMarginDeposit5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unexec_cash_margin_deposit_5 = p_unexecCashMarginDeposit5;
    unexec_cash_margin_deposit_5_is_set = true;
    unexec_cash_margin_deposit_5_is_modified = true;
  }


  /** 
   * <em>day_repay_margin_deposit_0</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayMarginDeposit0 <em>day_repay_margin_deposit_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayMarginDeposit0( double p_dayRepayMarginDeposit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_margin_deposit_0 = new Double(p_dayRepayMarginDeposit0);
    day_repay_margin_deposit_0_is_set = true;
    day_repay_margin_deposit_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_repay_margin_deposit_0</em>カラムに値を設定します。 
   */
  public final void setDayRepayMarginDeposit0( Double p_dayRepayMarginDeposit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_margin_deposit_0 = p_dayRepayMarginDeposit0;
    day_repay_margin_deposit_0_is_set = true;
    day_repay_margin_deposit_0_is_modified = true;
  }


  /** 
   * <em>day_repay_margin_deposit_1</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayMarginDeposit1 <em>day_repay_margin_deposit_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayMarginDeposit1( double p_dayRepayMarginDeposit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_margin_deposit_1 = new Double(p_dayRepayMarginDeposit1);
    day_repay_margin_deposit_1_is_set = true;
    day_repay_margin_deposit_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_repay_margin_deposit_1</em>カラムに値を設定します。 
   */
  public final void setDayRepayMarginDeposit1( Double p_dayRepayMarginDeposit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_margin_deposit_1 = p_dayRepayMarginDeposit1;
    day_repay_margin_deposit_1_is_set = true;
    day_repay_margin_deposit_1_is_modified = true;
  }


  /** 
   * <em>day_repay_margin_deposit_2</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayMarginDeposit2 <em>day_repay_margin_deposit_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayMarginDeposit2( double p_dayRepayMarginDeposit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_margin_deposit_2 = new Double(p_dayRepayMarginDeposit2);
    day_repay_margin_deposit_2_is_set = true;
    day_repay_margin_deposit_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_repay_margin_deposit_2</em>カラムに値を設定します。 
   */
  public final void setDayRepayMarginDeposit2( Double p_dayRepayMarginDeposit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_margin_deposit_2 = p_dayRepayMarginDeposit2;
    day_repay_margin_deposit_2_is_set = true;
    day_repay_margin_deposit_2_is_modified = true;
  }


  /** 
   * <em>day_repay_margin_deposit_3</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayMarginDeposit3 <em>day_repay_margin_deposit_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayMarginDeposit3( double p_dayRepayMarginDeposit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_margin_deposit_3 = new Double(p_dayRepayMarginDeposit3);
    day_repay_margin_deposit_3_is_set = true;
    day_repay_margin_deposit_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_repay_margin_deposit_3</em>カラムに値を設定します。 
   */
  public final void setDayRepayMarginDeposit3( Double p_dayRepayMarginDeposit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_margin_deposit_3 = p_dayRepayMarginDeposit3;
    day_repay_margin_deposit_3_is_set = true;
    day_repay_margin_deposit_3_is_modified = true;
  }


  /** 
   * <em>day_repay_margin_deposit_4</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayMarginDeposit4 <em>day_repay_margin_deposit_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayMarginDeposit4( double p_dayRepayMarginDeposit4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_margin_deposit_4 = new Double(p_dayRepayMarginDeposit4);
    day_repay_margin_deposit_4_is_set = true;
    day_repay_margin_deposit_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_repay_margin_deposit_4</em>カラムに値を設定します。 
   */
  public final void setDayRepayMarginDeposit4( Double p_dayRepayMarginDeposit4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_margin_deposit_4 = p_dayRepayMarginDeposit4;
    day_repay_margin_deposit_4_is_set = true;
    day_repay_margin_deposit_4_is_modified = true;
  }


  /** 
   * <em>day_repay_margin_deposit_5</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayMarginDeposit5 <em>day_repay_margin_deposit_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayMarginDeposit5( double p_dayRepayMarginDeposit5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_margin_deposit_5 = new Double(p_dayRepayMarginDeposit5);
    day_repay_margin_deposit_5_is_set = true;
    day_repay_margin_deposit_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_repay_margin_deposit_5</em>カラムに値を設定します。 
   */
  public final void setDayRepayMarginDeposit5( Double p_dayRepayMarginDeposit5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_margin_deposit_5 = p_dayRepayMarginDeposit5;
    day_repay_margin_deposit_5_is_set = true;
    day_repay_margin_deposit_5_is_modified = true;
  }


  /** 
   * <em>day_repay_cash_margin_deposit0</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayCashMarginDeposit0 <em>day_repay_cash_margin_deposit0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayCashMarginDeposit0( double p_dayRepayCashMarginDeposit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_cash_margin_deposit0 = new Double(p_dayRepayCashMarginDeposit0);
    day_repay_cash_margin_deposit0_is_set = true;
    day_repay_cash_margin_deposit0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_repay_cash_margin_deposit0</em>カラムに値を設定します。 
   */
  public final void setDayRepayCashMarginDeposit0( Double p_dayRepayCashMarginDeposit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_cash_margin_deposit0 = p_dayRepayCashMarginDeposit0;
    day_repay_cash_margin_deposit0_is_set = true;
    day_repay_cash_margin_deposit0_is_modified = true;
  }


  /** 
   * <em>day_repay_cash_margin_deposit1</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayCashMarginDeposit1 <em>day_repay_cash_margin_deposit1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayCashMarginDeposit1( double p_dayRepayCashMarginDeposit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_cash_margin_deposit1 = new Double(p_dayRepayCashMarginDeposit1);
    day_repay_cash_margin_deposit1_is_set = true;
    day_repay_cash_margin_deposit1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_repay_cash_margin_deposit1</em>カラムに値を設定します。 
   */
  public final void setDayRepayCashMarginDeposit1( Double p_dayRepayCashMarginDeposit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_cash_margin_deposit1 = p_dayRepayCashMarginDeposit1;
    day_repay_cash_margin_deposit1_is_set = true;
    day_repay_cash_margin_deposit1_is_modified = true;
  }


  /** 
   * <em>day_repay_cash_margin_deposit2</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayCashMarginDeposit2 <em>day_repay_cash_margin_deposit2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayCashMarginDeposit2( double p_dayRepayCashMarginDeposit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_cash_margin_deposit2 = new Double(p_dayRepayCashMarginDeposit2);
    day_repay_cash_margin_deposit2_is_set = true;
    day_repay_cash_margin_deposit2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_repay_cash_margin_deposit2</em>カラムに値を設定します。 
   */
  public final void setDayRepayCashMarginDeposit2( Double p_dayRepayCashMarginDeposit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_cash_margin_deposit2 = p_dayRepayCashMarginDeposit2;
    day_repay_cash_margin_deposit2_is_set = true;
    day_repay_cash_margin_deposit2_is_modified = true;
  }


  /** 
   * <em>day_repay_cash_margin_deposit3</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayCashMarginDeposit3 <em>day_repay_cash_margin_deposit3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayCashMarginDeposit3( double p_dayRepayCashMarginDeposit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_cash_margin_deposit3 = new Double(p_dayRepayCashMarginDeposit3);
    day_repay_cash_margin_deposit3_is_set = true;
    day_repay_cash_margin_deposit3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_repay_cash_margin_deposit3</em>カラムに値を設定します。 
   */
  public final void setDayRepayCashMarginDeposit3( Double p_dayRepayCashMarginDeposit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_cash_margin_deposit3 = p_dayRepayCashMarginDeposit3;
    day_repay_cash_margin_deposit3_is_set = true;
    day_repay_cash_margin_deposit3_is_modified = true;
  }


  /** 
   * <em>day_repay_cash_margin_deposit4</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayCashMarginDeposit4 <em>day_repay_cash_margin_deposit4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayCashMarginDeposit4( double p_dayRepayCashMarginDeposit4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_cash_margin_deposit4 = new Double(p_dayRepayCashMarginDeposit4);
    day_repay_cash_margin_deposit4_is_set = true;
    day_repay_cash_margin_deposit4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_repay_cash_margin_deposit4</em>カラムに値を設定します。 
   */
  public final void setDayRepayCashMarginDeposit4( Double p_dayRepayCashMarginDeposit4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_cash_margin_deposit4 = p_dayRepayCashMarginDeposit4;
    day_repay_cash_margin_deposit4_is_set = true;
    day_repay_cash_margin_deposit4_is_modified = true;
  }


  /** 
   * <em>day_repay_cash_margin_deposit5</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayCashMarginDeposit5 <em>day_repay_cash_margin_deposit5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayCashMarginDeposit5( double p_dayRepayCashMarginDeposit5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_cash_margin_deposit5 = new Double(p_dayRepayCashMarginDeposit5);
    day_repay_cash_margin_deposit5_is_set = true;
    day_repay_cash_margin_deposit5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_repay_cash_margin_deposit5</em>カラムに値を設定します。 
   */
  public final void setDayRepayCashMarginDeposit5( Double p_dayRepayCashMarginDeposit5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_cash_margin_deposit5 = p_dayRepayCashMarginDeposit5;
    day_repay_cash_margin_deposit5_is_set = true;
    day_repay_cash_margin_deposit5_is_modified = true;
  }


  /** 
   * <em>today_repay_contract_loss</em>カラムの値を設定します。 
   *
   * @@param p_todayRepayContractLoss <em>today_repay_contract_loss</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayRepayContractLoss( double p_todayRepayContractLoss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_repay_contract_loss = new Double(p_todayRepayContractLoss);
    today_repay_contract_loss_is_set = true;
    today_repay_contract_loss_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_repay_contract_loss</em>カラムに値を設定します。 
   */
  public final void setTodayRepayContractLoss( Double p_todayRepayContractLoss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_repay_contract_loss = p_todayRepayContractLoss;
    today_repay_contract_loss_is_set = true;
    today_repay_contract_loss_is_modified = true;
  }


  /** 
   * <em>today_repay_contract_profit</em>カラムの値を設定します。 
   *
   * @@param p_todayRepayContractProfit <em>today_repay_contract_profit</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayRepayContractProfit( double p_todayRepayContractProfit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_repay_contract_profit = new Double(p_todayRepayContractProfit);
    today_repay_contract_profit_is_set = true;
    today_repay_contract_profit_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_repay_contract_profit</em>カラムに値を設定します。 
   */
  public final void setTodayRepayContractProfit( Double p_todayRepayContractProfit )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_repay_contract_profit = p_todayRepayContractProfit;
    today_repay_contract_profit_is_set = true;
    today_repay_contract_profit_is_modified = true;
  }


  /** 
   * <em>today_repay_contract_pre_asset</em>カラムの値を設定します。 
   *
   * @@param p_todayRepayContractPreAsset <em>today_repay_contract_pre_asset</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayRepayContractPreAsset( double p_todayRepayContractPreAsset )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_repay_contract_pre_asset = new Double(p_todayRepayContractPreAsset);
    today_repay_contract_pre_asset_is_set = true;
    today_repay_contract_pre_asset_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_repay_contract_pre_asset</em>カラムに値を設定します。 
   */
  public final void setTodayRepayContractPreAsset( Double p_todayRepayContractPreAsset )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_repay_contract_pre_asset = p_todayRepayContractPreAsset;
    today_repay_contract_pre_asset_is_set = true;
    today_repay_contract_pre_asset_is_modified = true;
  }


  /** 
   * <em>contract_loss_designate_1</em>カラムの値を設定します。 
   *
   * @@param p_contractLossDesignate1 <em>contract_loss_designate_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractLossDesignate1( double p_contractLossDesignate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_loss_designate_1 = new Double(p_contractLossDesignate1);
    contract_loss_designate_1_is_set = true;
    contract_loss_designate_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_loss_designate_1</em>カラムに値を設定します。 
   */
  public final void setContractLossDesignate1( Double p_contractLossDesignate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_loss_designate_1 = p_contractLossDesignate1;
    contract_loss_designate_1_is_set = true;
    contract_loss_designate_1_is_modified = true;
  }


  /** 
   * <em>contract_loss_designate_2</em>カラムの値を設定します。 
   *
   * @@param p_contractLossDesignate2 <em>contract_loss_designate_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractLossDesignate2( double p_contractLossDesignate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_loss_designate_2 = new Double(p_contractLossDesignate2);
    contract_loss_designate_2_is_set = true;
    contract_loss_designate_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_loss_designate_2</em>カラムに値を設定します。 
   */
  public final void setContractLossDesignate2( Double p_contractLossDesignate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_loss_designate_2 = p_contractLossDesignate2;
    contract_loss_designate_2_is_set = true;
    contract_loss_designate_2_is_modified = true;
  }


  /** 
   * <em>contract_loss_designate_3</em>カラムの値を設定します。 
   *
   * @@param p_contractLossDesignate3 <em>contract_loss_designate_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractLossDesignate3( double p_contractLossDesignate3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_loss_designate_3 = new Double(p_contractLossDesignate3);
    contract_loss_designate_3_is_set = true;
    contract_loss_designate_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_loss_designate_3</em>カラムに値を設定します。 
   */
  public final void setContractLossDesignate3( Double p_contractLossDesignate3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_loss_designate_3 = p_contractLossDesignate3;
    contract_loss_designate_3_is_set = true;
    contract_loss_designate_3_is_modified = true;
  }


  /** 
   * <em>contract_loss_designate_4</em>カラムの値を設定します。 
   *
   * @@param p_contractLossDesignate4 <em>contract_loss_designate_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractLossDesignate4( double p_contractLossDesignate4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_loss_designate_4 = new Double(p_contractLossDesignate4);
    contract_loss_designate_4_is_set = true;
    contract_loss_designate_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_loss_designate_4</em>カラムに値を設定します。 
   */
  public final void setContractLossDesignate4( Double p_contractLossDesignate4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_loss_designate_4 = p_contractLossDesignate4;
    contract_loss_designate_4_is_set = true;
    contract_loss_designate_4_is_modified = true;
  }


  /** 
   * <em>contract_loss_designate_5</em>カラムの値を設定します。 
   *
   * @@param p_contractLossDesignate5 <em>contract_loss_designate_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractLossDesignate5( double p_contractLossDesignate5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_loss_designate_5 = new Double(p_contractLossDesignate5);
    contract_loss_designate_5_is_set = true;
    contract_loss_designate_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_loss_designate_5</em>カラムに値を設定します。 
   */
  public final void setContractLossDesignate5( Double p_contractLossDesignate5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_loss_designate_5 = p_contractLossDesignate5;
    contract_loss_designate_5_is_set = true;
    contract_loss_designate_5_is_modified = true;
  }


  /** 
   * <em>contract_profit_designate_1</em>カラムの値を設定します。 
   *
   * @@param p_contractProfitDesignate1 <em>contract_profit_designate_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractProfitDesignate1( double p_contractProfitDesignate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_profit_designate_1 = new Double(p_contractProfitDesignate1);
    contract_profit_designate_1_is_set = true;
    contract_profit_designate_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_profit_designate_1</em>カラムに値を設定します。 
   */
  public final void setContractProfitDesignate1( Double p_contractProfitDesignate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_profit_designate_1 = p_contractProfitDesignate1;
    contract_profit_designate_1_is_set = true;
    contract_profit_designate_1_is_modified = true;
  }


  /** 
   * <em>contract_profit_designate_2</em>カラムの値を設定します。 
   *
   * @@param p_contractProfitDesignate2 <em>contract_profit_designate_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractProfitDesignate2( double p_contractProfitDesignate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_profit_designate_2 = new Double(p_contractProfitDesignate2);
    contract_profit_designate_2_is_set = true;
    contract_profit_designate_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_profit_designate_2</em>カラムに値を設定します。 
   */
  public final void setContractProfitDesignate2( Double p_contractProfitDesignate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_profit_designate_2 = p_contractProfitDesignate2;
    contract_profit_designate_2_is_set = true;
    contract_profit_designate_2_is_modified = true;
  }


  /** 
   * <em>contract_profit_designate_3</em>カラムの値を設定します。 
   *
   * @@param p_contractProfitDesignate3 <em>contract_profit_designate_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractProfitDesignate3( double p_contractProfitDesignate3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_profit_designate_3 = new Double(p_contractProfitDesignate3);
    contract_profit_designate_3_is_set = true;
    contract_profit_designate_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_profit_designate_3</em>カラムに値を設定します。 
   */
  public final void setContractProfitDesignate3( Double p_contractProfitDesignate3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_profit_designate_3 = p_contractProfitDesignate3;
    contract_profit_designate_3_is_set = true;
    contract_profit_designate_3_is_modified = true;
  }


  /** 
   * <em>contract_profit_designate_4</em>カラムの値を設定します。 
   *
   * @@param p_contractProfitDesignate4 <em>contract_profit_designate_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractProfitDesignate4( double p_contractProfitDesignate4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_profit_designate_4 = new Double(p_contractProfitDesignate4);
    contract_profit_designate_4_is_set = true;
    contract_profit_designate_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_profit_designate_4</em>カラムに値を設定します。 
   */
  public final void setContractProfitDesignate4( Double p_contractProfitDesignate4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_profit_designate_4 = p_contractProfitDesignate4;
    contract_profit_designate_4_is_set = true;
    contract_profit_designate_4_is_modified = true;
  }


  /** 
   * <em>contract_profit_designate_5</em>カラムの値を設定します。 
   *
   * @@param p_contractProfitDesignate5 <em>contract_profit_designate_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractProfitDesignate5( double p_contractProfitDesignate5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_profit_designate_5 = new Double(p_contractProfitDesignate5);
    contract_profit_designate_5_is_set = true;
    contract_profit_designate_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_profit_designate_5</em>カラムに値を設定します。 
   */
  public final void setContractProfitDesignate5( Double p_contractProfitDesignate5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_profit_designate_5 = p_contractProfitDesignate5;
    contract_profit_designate_5_is_set = true;
    contract_profit_designate_5_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate_0</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate0 <em>payment_amount_designate_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentAmountDesignate0( double p_paymentAmountDesignate0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_0 = new Double(p_paymentAmountDesignate0);
    payment_amount_designate_0_is_set = true;
    payment_amount_designate_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate_0</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate0( Double p_paymentAmountDesignate0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_0 = p_paymentAmountDesignate0;
    payment_amount_designate_0_is_set = true;
    payment_amount_designate_0_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate_1</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate1 <em>payment_amount_designate_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentAmountDesignate1( double p_paymentAmountDesignate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_1 = new Double(p_paymentAmountDesignate1);
    payment_amount_designate_1_is_set = true;
    payment_amount_designate_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate_1</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate1( Double p_paymentAmountDesignate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_1 = p_paymentAmountDesignate1;
    payment_amount_designate_1_is_set = true;
    payment_amount_designate_1_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate_2</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate2 <em>payment_amount_designate_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentAmountDesignate2( double p_paymentAmountDesignate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_2 = new Double(p_paymentAmountDesignate2);
    payment_amount_designate_2_is_set = true;
    payment_amount_designate_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate_2</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate2( Double p_paymentAmountDesignate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_2 = p_paymentAmountDesignate2;
    payment_amount_designate_2_is_set = true;
    payment_amount_designate_2_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate_3</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate3 <em>payment_amount_designate_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentAmountDesignate3( double p_paymentAmountDesignate3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_3 = new Double(p_paymentAmountDesignate3);
    payment_amount_designate_3_is_set = true;
    payment_amount_designate_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate_3</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate3( Double p_paymentAmountDesignate3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_3 = p_paymentAmountDesignate3;
    payment_amount_designate_3_is_set = true;
    payment_amount_designate_3_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate_4</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate4 <em>payment_amount_designate_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentAmountDesignate4( double p_paymentAmountDesignate4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_4 = new Double(p_paymentAmountDesignate4);
    payment_amount_designate_4_is_set = true;
    payment_amount_designate_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate_4</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate4( Double p_paymentAmountDesignate4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_4 = p_paymentAmountDesignate4;
    payment_amount_designate_4_is_set = true;
    payment_amount_designate_4_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate_5</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate5 <em>payment_amount_designate_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentAmountDesignate5( double p_paymentAmountDesignate5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_5 = new Double(p_paymentAmountDesignate5);
    payment_amount_designate_5_is_set = true;
    payment_amount_designate_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate_5</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate5( Double p_paymentAmountDesignate5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_5 = p_paymentAmountDesignate5;
    payment_amount_designate_5_is_set = true;
    payment_amount_designate_5_is_modified = true;
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
   * <em>today_dep_fund_restraint_0</em>カラムの値を設定します。 
   *
   * @@param p_todayDepFundRestraint0 <em>today_dep_fund_restraint_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayDepFundRestraint0( double p_todayDepFundRestraint0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_dep_fund_restraint_0 = new Double(p_todayDepFundRestraint0);
    today_dep_fund_restraint_0_is_set = true;
    today_dep_fund_restraint_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_dep_fund_restraint_0</em>カラムに値を設定します。 
   */
  public final void setTodayDepFundRestraint0( Double p_todayDepFundRestraint0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_dep_fund_restraint_0 = p_todayDepFundRestraint0;
    today_dep_fund_restraint_0_is_set = true;
    today_dep_fund_restraint_0_is_modified = true;
  }


  /** 
   * <em>today_dep_fund_restraint_1</em>カラムの値を設定します。 
   *
   * @@param p_todayDepFundRestraint1 <em>today_dep_fund_restraint_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayDepFundRestraint1( double p_todayDepFundRestraint1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_dep_fund_restraint_1 = new Double(p_todayDepFundRestraint1);
    today_dep_fund_restraint_1_is_set = true;
    today_dep_fund_restraint_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_dep_fund_restraint_1</em>カラムに値を設定します。 
   */
  public final void setTodayDepFundRestraint1( Double p_todayDepFundRestraint1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_dep_fund_restraint_1 = p_todayDepFundRestraint1;
    today_dep_fund_restraint_1_is_set = true;
    today_dep_fund_restraint_1_is_modified = true;
  }


  /** 
   * <em>today_dep_fund_restraint_2</em>カラムの値を設定します。 
   *
   * @@param p_todayDepFundRestraint2 <em>today_dep_fund_restraint_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayDepFundRestraint2( double p_todayDepFundRestraint2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_dep_fund_restraint_2 = new Double(p_todayDepFundRestraint2);
    today_dep_fund_restraint_2_is_set = true;
    today_dep_fund_restraint_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_dep_fund_restraint_2</em>カラムに値を設定します。 
   */
  public final void setTodayDepFundRestraint2( Double p_todayDepFundRestraint2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_dep_fund_restraint_2 = p_todayDepFundRestraint2;
    today_dep_fund_restraint_2_is_set = true;
    today_dep_fund_restraint_2_is_modified = true;
  }


  /** 
   * <em>today_dep_fund_restraint_3</em>カラムの値を設定します。 
   *
   * @@param p_todayDepFundRestraint3 <em>today_dep_fund_restraint_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayDepFundRestraint3( double p_todayDepFundRestraint3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_dep_fund_restraint_3 = new Double(p_todayDepFundRestraint3);
    today_dep_fund_restraint_3_is_set = true;
    today_dep_fund_restraint_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_dep_fund_restraint_3</em>カラムに値を設定します。 
   */
  public final void setTodayDepFundRestraint3( Double p_todayDepFundRestraint3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_dep_fund_restraint_3 = p_todayDepFundRestraint3;
    today_dep_fund_restraint_3_is_set = true;
    today_dep_fund_restraint_3_is_modified = true;
  }


  /** 
   * <em>today_dep_fund_restraint_4</em>カラムの値を設定します。 
   *
   * @@param p_todayDepFundRestraint4 <em>today_dep_fund_restraint_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayDepFundRestraint4( double p_todayDepFundRestraint4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_dep_fund_restraint_4 = new Double(p_todayDepFundRestraint4);
    today_dep_fund_restraint_4_is_set = true;
    today_dep_fund_restraint_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_dep_fund_restraint_4</em>カラムに値を設定します。 
   */
  public final void setTodayDepFundRestraint4( Double p_todayDepFundRestraint4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_dep_fund_restraint_4 = p_todayDepFundRestraint4;
    today_dep_fund_restraint_4_is_set = true;
    today_dep_fund_restraint_4_is_modified = true;
  }


  /** 
   * <em>today_dep_fund_restraint_5</em>カラムの値を設定します。 
   *
   * @@param p_todayDepFundRestraint5 <em>today_dep_fund_restraint_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayDepFundRestraint5( double p_todayDepFundRestraint5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_dep_fund_restraint_5 = new Double(p_todayDepFundRestraint5);
    today_dep_fund_restraint_5_is_set = true;
    today_dep_fund_restraint_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_dep_fund_restraint_5</em>カラムに値を設定します。 
   */
  public final void setTodayDepFundRestraint5( Double p_todayDepFundRestraint5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_dep_fund_restraint_5 = p_todayDepFundRestraint5;
    today_dep_fund_restraint_5_is_set = true;
    today_dep_fund_restraint_5_is_modified = true;
  }


  /** 
   * <em>contract_asset_loss_1</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetLoss1 <em>contract_asset_loss_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetLoss1( double p_contractAssetLoss1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_loss_1 = new Double(p_contractAssetLoss1);
    contract_asset_loss_1_is_set = true;
    contract_asset_loss_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_loss_1</em>カラムに値を設定します。 
   */
  public final void setContractAssetLoss1( Double p_contractAssetLoss1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_loss_1 = p_contractAssetLoss1;
    contract_asset_loss_1_is_set = true;
    contract_asset_loss_1_is_modified = true;
  }


  /** 
   * <em>contract_asset_loss_2</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetLoss2 <em>contract_asset_loss_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetLoss2( double p_contractAssetLoss2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_loss_2 = new Double(p_contractAssetLoss2);
    contract_asset_loss_2_is_set = true;
    contract_asset_loss_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_loss_2</em>カラムに値を設定します。 
   */
  public final void setContractAssetLoss2( Double p_contractAssetLoss2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_loss_2 = p_contractAssetLoss2;
    contract_asset_loss_2_is_set = true;
    contract_asset_loss_2_is_modified = true;
  }


  /** 
   * <em>contract_asset_loss_3</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetLoss3 <em>contract_asset_loss_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetLoss3( double p_contractAssetLoss3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_loss_3 = new Double(p_contractAssetLoss3);
    contract_asset_loss_3_is_set = true;
    contract_asset_loss_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_loss_3</em>カラムに値を設定します。 
   */
  public final void setContractAssetLoss3( Double p_contractAssetLoss3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_loss_3 = p_contractAssetLoss3;
    contract_asset_loss_3_is_set = true;
    contract_asset_loss_3_is_modified = true;
  }


  /** 
   * <em>contract_asset_loss_4</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetLoss4 <em>contract_asset_loss_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetLoss4( double p_contractAssetLoss4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_loss_4 = new Double(p_contractAssetLoss4);
    contract_asset_loss_4_is_set = true;
    contract_asset_loss_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_loss_4</em>カラムに値を設定します。 
   */
  public final void setContractAssetLoss4( Double p_contractAssetLoss4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_loss_4 = p_contractAssetLoss4;
    contract_asset_loss_4_is_set = true;
    contract_asset_loss_4_is_modified = true;
  }


  /** 
   * <em>contract_asset_loss_5</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetLoss5 <em>contract_asset_loss_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetLoss5( double p_contractAssetLoss5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_loss_5 = new Double(p_contractAssetLoss5);
    contract_asset_loss_5_is_set = true;
    contract_asset_loss_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_loss_5</em>カラムに値を設定します。 
   */
  public final void setContractAssetLoss5( Double p_contractAssetLoss5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_loss_5 = p_contractAssetLoss5;
    contract_asset_loss_5_is_set = true;
    contract_asset_loss_5_is_modified = true;
  }


  /** 
   * <em>contract_asset_profit_1</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetProfit1 <em>contract_asset_profit_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetProfit1( double p_contractAssetProfit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_1 = new Double(p_contractAssetProfit1);
    contract_asset_profit_1_is_set = true;
    contract_asset_profit_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_profit_1</em>カラムに値を設定します。 
   */
  public final void setContractAssetProfit1( Double p_contractAssetProfit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_1 = p_contractAssetProfit1;
    contract_asset_profit_1_is_set = true;
    contract_asset_profit_1_is_modified = true;
  }


  /** 
   * <em>contract_asset_profit_2</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetProfit2 <em>contract_asset_profit_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetProfit2( double p_contractAssetProfit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_2 = new Double(p_contractAssetProfit2);
    contract_asset_profit_2_is_set = true;
    contract_asset_profit_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_profit_2</em>カラムに値を設定します。 
   */
  public final void setContractAssetProfit2( Double p_contractAssetProfit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_2 = p_contractAssetProfit2;
    contract_asset_profit_2_is_set = true;
    contract_asset_profit_2_is_modified = true;
  }


  /** 
   * <em>contract_asset_profit_3</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetProfit3 <em>contract_asset_profit_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetProfit3( double p_contractAssetProfit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_3 = new Double(p_contractAssetProfit3);
    contract_asset_profit_3_is_set = true;
    contract_asset_profit_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_profit_3</em>カラムに値を設定します。 
   */
  public final void setContractAssetProfit3( Double p_contractAssetProfit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_3 = p_contractAssetProfit3;
    contract_asset_profit_3_is_set = true;
    contract_asset_profit_3_is_modified = true;
  }


  /** 
   * <em>contract_asset_profit_4</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetProfit4 <em>contract_asset_profit_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetProfit4( double p_contractAssetProfit4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_4 = new Double(p_contractAssetProfit4);
    contract_asset_profit_4_is_set = true;
    contract_asset_profit_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_profit_4</em>カラムに値を設定します。 
   */
  public final void setContractAssetProfit4( Double p_contractAssetProfit4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_4 = p_contractAssetProfit4;
    contract_asset_profit_4_is_set = true;
    contract_asset_profit_4_is_modified = true;
  }


  /** 
   * <em>contract_asset_profit_5</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetProfit5 <em>contract_asset_profit_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetProfit5( double p_contractAssetProfit5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_5 = new Double(p_contractAssetProfit5);
    contract_asset_profit_5_is_set = true;
    contract_asset_profit_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_profit_5</em>カラムに値を設定します。 
   */
  public final void setContractAssetProfit5( Double p_contractAssetProfit5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_5 = p_contractAssetProfit5;
    contract_asset_profit_5_is_set = true;
    contract_asset_profit_5_is_modified = true;
  }


  /** 
   * <em>foreign_equity_asset_delivered</em>カラムの値を設定します。 
   *
   * @@param p_foreignEquityAssetDelivered <em>foreign_equity_asset_delivered</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setForeignEquityAssetDelivered( double p_foreignEquityAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_equity_asset_delivered = new Double(p_foreignEquityAssetDelivered);
    foreign_equity_asset_delivered_is_set = true;
    foreign_equity_asset_delivered_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>foreign_equity_asset_delivered</em>カラムに値を設定します。 
   */
  public final void setForeignEquityAssetDelivered( Double p_foreignEquityAssetDelivered )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_equity_asset_delivered = p_foreignEquityAssetDelivered;
    foreign_equity_asset_delivered_is_set = true;
    foreign_equity_asset_delivered_is_modified = true;
  }


  /** 
   * <em>foreign_equity_asset_executed</em>カラムの値を設定します。 
   *
   * @@param p_foreignEquityAssetExecuted <em>foreign_equity_asset_executed</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setForeignEquityAssetExecuted( double p_foreignEquityAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_equity_asset_executed = new Double(p_foreignEquityAssetExecuted);
    foreign_equity_asset_executed_is_set = true;
    foreign_equity_asset_executed_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>foreign_equity_asset_executed</em>カラムに値を設定します。 
   */
  public final void setForeignEquityAssetExecuted( Double p_foreignEquityAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    foreign_equity_asset_executed = p_foreignEquityAssetExecuted;
    foreign_equity_asset_executed_is_set = true;
    foreign_equity_asset_executed_is_modified = true;
  }


  /** 
   * <em>today_repay_contract_amount</em>カラムの値を設定します。 
   *
   * @@param p_todayRepayContractAmount <em>today_repay_contract_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayRepayContractAmount( double p_todayRepayContractAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_repay_contract_amount = new Double(p_todayRepayContractAmount);
    today_repay_contract_amount_is_set = true;
    today_repay_contract_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_repay_contract_amount</em>カラムに値を設定します。 
   */
  public final void setTodayRepayContractAmount( Double p_todayRepayContractAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_repay_contract_amount = p_todayRepayContractAmount;
    today_repay_contract_amount_is_set = true;
    today_repay_contract_amount_is_modified = true;
  }


  /** 
   * <em>substitute_asset_old_day_value</em>カラムの値を設定します。 
   *
   * @@param p_substituteAssetOldDayValue <em>substitute_asset_old_day_value</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSubstituteAssetOldDayValue( double p_substituteAssetOldDayValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_asset_old_day_value = new Double(p_substituteAssetOldDayValue);
    substitute_asset_old_day_value_is_set = true;
    substitute_asset_old_day_value_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>substitute_asset_old_day_value</em>カラムに値を設定します。 
   */
  public final void setSubstituteAssetOldDayValue( Double p_substituteAssetOldDayValue )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_asset_old_day_value = p_substituteAssetOldDayValue;
    substitute_asset_old_day_value_is_set = true;
    substitute_asset_old_day_value_is_modified = true;
  }


  /** 
   * <em>setup_fee_1</em>カラムの値を設定します。 
   *
   * @@param p_setupFee1 <em>setup_fee_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSetupFee1( double p_setupFee1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    setup_fee_1 = new Double(p_setupFee1);
    setup_fee_1_is_set = true;
    setup_fee_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>setup_fee_1</em>カラムに値を設定します。 
   */
  public final void setSetupFee1( Double p_setupFee1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    setup_fee_1 = p_setupFee1;
    setup_fee_1_is_set = true;
    setup_fee_1_is_modified = true;
  }


  /** 
   * <em>setup_fee_2</em>カラムの値を設定します。 
   *
   * @@param p_setupFee2 <em>setup_fee_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSetupFee2( double p_setupFee2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    setup_fee_2 = new Double(p_setupFee2);
    setup_fee_2_is_set = true;
    setup_fee_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>setup_fee_2</em>カラムに値を設定します。 
   */
  public final void setSetupFee2( Double p_setupFee2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    setup_fee_2 = p_setupFee2;
    setup_fee_2_is_set = true;
    setup_fee_2_is_modified = true;
  }


  /** 
   * <em>setup_fee_3</em>カラムの値を設定します。 
   *
   * @@param p_setupFee3 <em>setup_fee_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSetupFee3( double p_setupFee3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    setup_fee_3 = new Double(p_setupFee3);
    setup_fee_3_is_set = true;
    setup_fee_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>setup_fee_3</em>カラムに値を設定します。 
   */
  public final void setSetupFee3( Double p_setupFee3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    setup_fee_3 = p_setupFee3;
    setup_fee_3_is_set = true;
    setup_fee_3_is_modified = true;
  }


  /** 
   * <em>setup_fee_4</em>カラムの値を設定します。 
   *
   * @@param p_setupFee4 <em>setup_fee_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSetupFee4( double p_setupFee4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    setup_fee_4 = new Double(p_setupFee4);
    setup_fee_4_is_set = true;
    setup_fee_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>setup_fee_4</em>カラムに値を設定します。 
   */
  public final void setSetupFee4( Double p_setupFee4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    setup_fee_4 = p_setupFee4;
    setup_fee_4_is_set = true;
    setup_fee_4_is_modified = true;
  }


  /** 
   * <em>setup_fee_5</em>カラムの値を設定します。 
   *
   * @@param p_setupFee5 <em>setup_fee_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSetupFee5( double p_setupFee5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    setup_fee_5 = new Double(p_setupFee5);
    setup_fee_5_is_set = true;
    setup_fee_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>setup_fee_5</em>カラムに値を設定します。 
   */
  public final void setSetupFee5( Double p_setupFee5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    setup_fee_5 = p_setupFee5;
    setup_fee_5_is_set = true;
    setup_fee_5_is_modified = true;
  }


  /** 
   * <em>contract_interest_loss_1</em>カラムの値を設定します。 
   *
   * @@param p_contractInterestLoss1 <em>contract_interest_loss_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractInterestLoss1( double p_contractInterestLoss1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_loss_1 = new Double(p_contractInterestLoss1);
    contract_interest_loss_1_is_set = true;
    contract_interest_loss_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_interest_loss_1</em>カラムに値を設定します。 
   */
  public final void setContractInterestLoss1( Double p_contractInterestLoss1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_loss_1 = p_contractInterestLoss1;
    contract_interest_loss_1_is_set = true;
    contract_interest_loss_1_is_modified = true;
  }


  /** 
   * <em>contract_interest_loss_2</em>カラムの値を設定します。 
   *
   * @@param p_contractInterestLoss2 <em>contract_interest_loss_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractInterestLoss2( double p_contractInterestLoss2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_loss_2 = new Double(p_contractInterestLoss2);
    contract_interest_loss_2_is_set = true;
    contract_interest_loss_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_interest_loss_2</em>カラムに値を設定します。 
   */
  public final void setContractInterestLoss2( Double p_contractInterestLoss2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_loss_2 = p_contractInterestLoss2;
    contract_interest_loss_2_is_set = true;
    contract_interest_loss_2_is_modified = true;
  }


  /** 
   * <em>contract_interest_loss_3</em>カラムの値を設定します。 
   *
   * @@param p_contractInterestLoss3 <em>contract_interest_loss_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractInterestLoss3( double p_contractInterestLoss3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_loss_3 = new Double(p_contractInterestLoss3);
    contract_interest_loss_3_is_set = true;
    contract_interest_loss_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_interest_loss_3</em>カラムに値を設定します。 
   */
  public final void setContractInterestLoss3( Double p_contractInterestLoss3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_loss_3 = p_contractInterestLoss3;
    contract_interest_loss_3_is_set = true;
    contract_interest_loss_3_is_modified = true;
  }


  /** 
   * <em>contract_interest_loss_4</em>カラムの値を設定します。 
   *
   * @@param p_contractInterestLoss4 <em>contract_interest_loss_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractInterestLoss4( double p_contractInterestLoss4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_loss_4 = new Double(p_contractInterestLoss4);
    contract_interest_loss_4_is_set = true;
    contract_interest_loss_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_interest_loss_4</em>カラムに値を設定します。 
   */
  public final void setContractInterestLoss4( Double p_contractInterestLoss4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_loss_4 = p_contractInterestLoss4;
    contract_interest_loss_4_is_set = true;
    contract_interest_loss_4_is_modified = true;
  }


  /** 
   * <em>contract_interest_loss_5</em>カラムの値を設定します。 
   *
   * @@param p_contractInterestLoss5 <em>contract_interest_loss_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractInterestLoss5( double p_contractInterestLoss5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_loss_5 = new Double(p_contractInterestLoss5);
    contract_interest_loss_5_is_set = true;
    contract_interest_loss_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_interest_loss_5</em>カラムに値を設定します。 
   */
  public final void setContractInterestLoss5( Double p_contractInterestLoss5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_loss_5 = p_contractInterestLoss5;
    contract_interest_loss_5_is_set = true;
    contract_interest_loss_5_is_modified = true;
  }


  /** 
   * <em>contract_interest_profit_1</em>カラムの値を設定します。 
   *
   * @@param p_contractInterestProfit1 <em>contract_interest_profit_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractInterestProfit1( double p_contractInterestProfit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_profit_1 = new Double(p_contractInterestProfit1);
    contract_interest_profit_1_is_set = true;
    contract_interest_profit_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_interest_profit_1</em>カラムに値を設定します。 
   */
  public final void setContractInterestProfit1( Double p_contractInterestProfit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_profit_1 = p_contractInterestProfit1;
    contract_interest_profit_1_is_set = true;
    contract_interest_profit_1_is_modified = true;
  }


  /** 
   * <em>contract_interest_profit_2</em>カラムの値を設定します。 
   *
   * @@param p_contractInterestProfit2 <em>contract_interest_profit_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractInterestProfit2( double p_contractInterestProfit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_profit_2 = new Double(p_contractInterestProfit2);
    contract_interest_profit_2_is_set = true;
    contract_interest_profit_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_interest_profit_2</em>カラムに値を設定します。 
   */
  public final void setContractInterestProfit2( Double p_contractInterestProfit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_profit_2 = p_contractInterestProfit2;
    contract_interest_profit_2_is_set = true;
    contract_interest_profit_2_is_modified = true;
  }


  /** 
   * <em>contract_interest_profit_3</em>カラムの値を設定します。 
   *
   * @@param p_contractInterestProfit3 <em>contract_interest_profit_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractInterestProfit3( double p_contractInterestProfit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_profit_3 = new Double(p_contractInterestProfit3);
    contract_interest_profit_3_is_set = true;
    contract_interest_profit_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_interest_profit_3</em>カラムに値を設定します。 
   */
  public final void setContractInterestProfit3( Double p_contractInterestProfit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_profit_3 = p_contractInterestProfit3;
    contract_interest_profit_3_is_set = true;
    contract_interest_profit_3_is_modified = true;
  }


  /** 
   * <em>contract_interest_profit_4</em>カラムの値を設定します。 
   *
   * @@param p_contractInterestProfit4 <em>contract_interest_profit_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractInterestProfit4( double p_contractInterestProfit4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_profit_4 = new Double(p_contractInterestProfit4);
    contract_interest_profit_4_is_set = true;
    contract_interest_profit_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_interest_profit_4</em>カラムに値を設定します。 
   */
  public final void setContractInterestProfit4( Double p_contractInterestProfit4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_profit_4 = p_contractInterestProfit4;
    contract_interest_profit_4_is_set = true;
    contract_interest_profit_4_is_modified = true;
  }


  /** 
   * <em>contract_interest_profit_5</em>カラムの値を設定します。 
   *
   * @@param p_contractInterestProfit5 <em>contract_interest_profit_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractInterestProfit5( double p_contractInterestProfit5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_profit_5 = new Double(p_contractInterestProfit5);
    contract_interest_profit_5_is_set = true;
    contract_interest_profit_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_interest_profit_5</em>カラムに値を設定します。 
   */
  public final void setContractInterestProfit5( Double p_contractInterestProfit5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_interest_profit_5 = p_contractInterestProfit5;
    contract_interest_profit_5_is_set = true;
    contract_interest_profit_5_is_modified = true;
  }


  /** 
   * <em>contract_other_cost_1</em>カラムの値を設定します。 
   *
   * @@param p_contractOtherCost1 <em>contract_other_cost_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractOtherCost1( double p_contractOtherCost1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_other_cost_1 = new Double(p_contractOtherCost1);
    contract_other_cost_1_is_set = true;
    contract_other_cost_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_other_cost_1</em>カラムに値を設定します。 
   */
  public final void setContractOtherCost1( Double p_contractOtherCost1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_other_cost_1 = p_contractOtherCost1;
    contract_other_cost_1_is_set = true;
    contract_other_cost_1_is_modified = true;
  }


  /** 
   * <em>contract_other_cost_2</em>カラムの値を設定します。 
   *
   * @@param p_contractOtherCost2 <em>contract_other_cost_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractOtherCost2( double p_contractOtherCost2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_other_cost_2 = new Double(p_contractOtherCost2);
    contract_other_cost_2_is_set = true;
    contract_other_cost_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_other_cost_2</em>カラムに値を設定します。 
   */
  public final void setContractOtherCost2( Double p_contractOtherCost2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_other_cost_2 = p_contractOtherCost2;
    contract_other_cost_2_is_set = true;
    contract_other_cost_2_is_modified = true;
  }


  /** 
   * <em>contract_other_cost_3</em>カラムの値を設定します。 
   *
   * @@param p_contractOtherCost3 <em>contract_other_cost_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractOtherCost3( double p_contractOtherCost3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_other_cost_3 = new Double(p_contractOtherCost3);
    contract_other_cost_3_is_set = true;
    contract_other_cost_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_other_cost_3</em>カラムに値を設定します。 
   */
  public final void setContractOtherCost3( Double p_contractOtherCost3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_other_cost_3 = p_contractOtherCost3;
    contract_other_cost_3_is_set = true;
    contract_other_cost_3_is_modified = true;
  }


  /** 
   * <em>contract_other_cost_4</em>カラムの値を設定します。 
   *
   * @@param p_contractOtherCost4 <em>contract_other_cost_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractOtherCost4( double p_contractOtherCost4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_other_cost_4 = new Double(p_contractOtherCost4);
    contract_other_cost_4_is_set = true;
    contract_other_cost_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_other_cost_4</em>カラムに値を設定します。 
   */
  public final void setContractOtherCost4( Double p_contractOtherCost4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_other_cost_4 = p_contractOtherCost4;
    contract_other_cost_4_is_set = true;
    contract_other_cost_4_is_modified = true;
  }


  /** 
   * <em>contract_other_cost_5</em>カラムの値を設定します。 
   *
   * @@param p_contractOtherCost5 <em>contract_other_cost_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractOtherCost5( double p_contractOtherCost5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_other_cost_5 = new Double(p_contractOtherCost5);
    contract_other_cost_5_is_set = true;
    contract_other_cost_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_other_cost_5</em>カラムに値を設定します。 
   */
  public final void setContractOtherCost5( Double p_contractOtherCost5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_other_cost_5 = p_contractOtherCost5;
    contract_other_cost_5_is_set = true;
    contract_other_cost_5_is_modified = true;
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
                break;
            case 'b':
                if ( name.equals("bond_asset_delivered") ) {
                    return this.bond_asset_delivered;
                }
                else if ( name.equals("bond_asset_executed") ) {
                    return this.bond_asset_executed;
                }
                break;
            case 'c':
                if ( name.equals("calc_result_margin_id") ) {
                    return new Long( calc_result_margin_id );
                }
                else if ( name.equals("contract_asset_loss") ) {
                    return this.contract_asset_loss;
                }
                else if ( name.equals("contract_asset_profit") ) {
                    return this.contract_asset_profit;
                }
                else if ( name.equals("contract_interest_loss") ) {
                    return this.contract_interest_loss;
                }
                else if ( name.equals("contract_interest_profit") ) {
                    return this.contract_interest_profit;
                }
                else if ( name.equals("contract_other_cost") ) {
                    return this.contract_other_cost;
                }
                else if ( name.equals("contract_loss_designate_1") ) {
                    return this.contract_loss_designate_1;
                }
                else if ( name.equals("contract_loss_designate_2") ) {
                    return this.contract_loss_designate_2;
                }
                else if ( name.equals("contract_loss_designate_3") ) {
                    return this.contract_loss_designate_3;
                }
                else if ( name.equals("contract_loss_designate_4") ) {
                    return this.contract_loss_designate_4;
                }
                else if ( name.equals("contract_loss_designate_5") ) {
                    return this.contract_loss_designate_5;
                }
                else if ( name.equals("contract_profit_designate_1") ) {
                    return this.contract_profit_designate_1;
                }
                else if ( name.equals("contract_profit_designate_2") ) {
                    return this.contract_profit_designate_2;
                }
                else if ( name.equals("contract_profit_designate_3") ) {
                    return this.contract_profit_designate_3;
                }
                else if ( name.equals("contract_profit_designate_4") ) {
                    return this.contract_profit_designate_4;
                }
                else if ( name.equals("contract_profit_designate_5") ) {
                    return this.contract_profit_designate_5;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("contract_asset_loss_1") ) {
                    return this.contract_asset_loss_1;
                }
                else if ( name.equals("contract_asset_loss_2") ) {
                    return this.contract_asset_loss_2;
                }
                else if ( name.equals("contract_asset_loss_3") ) {
                    return this.contract_asset_loss_3;
                }
                else if ( name.equals("contract_asset_loss_4") ) {
                    return this.contract_asset_loss_4;
                }
                else if ( name.equals("contract_asset_loss_5") ) {
                    return this.contract_asset_loss_5;
                }
                else if ( name.equals("contract_asset_profit_1") ) {
                    return this.contract_asset_profit_1;
                }
                else if ( name.equals("contract_asset_profit_2") ) {
                    return this.contract_asset_profit_2;
                }
                else if ( name.equals("contract_asset_profit_3") ) {
                    return this.contract_asset_profit_3;
                }
                else if ( name.equals("contract_asset_profit_4") ) {
                    return this.contract_asset_profit_4;
                }
                else if ( name.equals("contract_asset_profit_5") ) {
                    return this.contract_asset_profit_5;
                }
                else if ( name.equals("contract_interest_loss_1") ) {
                    return this.contract_interest_loss_1;
                }
                else if ( name.equals("contract_interest_loss_2") ) {
                    return this.contract_interest_loss_2;
                }
                else if ( name.equals("contract_interest_loss_3") ) {
                    return this.contract_interest_loss_3;
                }
                else if ( name.equals("contract_interest_loss_4") ) {
                    return this.contract_interest_loss_4;
                }
                else if ( name.equals("contract_interest_loss_5") ) {
                    return this.contract_interest_loss_5;
                }
                else if ( name.equals("contract_interest_profit_1") ) {
                    return this.contract_interest_profit_1;
                }
                else if ( name.equals("contract_interest_profit_2") ) {
                    return this.contract_interest_profit_2;
                }
                else if ( name.equals("contract_interest_profit_3") ) {
                    return this.contract_interest_profit_3;
                }
                else if ( name.equals("contract_interest_profit_4") ) {
                    return this.contract_interest_profit_4;
                }
                else if ( name.equals("contract_interest_profit_5") ) {
                    return this.contract_interest_profit_5;
                }
                else if ( name.equals("contract_other_cost_1") ) {
                    return this.contract_other_cost_1;
                }
                else if ( name.equals("contract_other_cost_2") ) {
                    return this.contract_other_cost_2;
                }
                else if ( name.equals("contract_other_cost_3") ) {
                    return this.contract_other_cost_3;
                }
                else if ( name.equals("contract_other_cost_4") ) {
                    return this.contract_other_cost_4;
                }
                else if ( name.equals("contract_other_cost_5") ) {
                    return this.contract_other_cost_5;
                }
                break;
            case 'd':
                if ( name.equals("day_repay_margin_deposit_0") ) {
                    return this.day_repay_margin_deposit_0;
                }
                else if ( name.equals("day_repay_margin_deposit_1") ) {
                    return this.day_repay_margin_deposit_1;
                }
                else if ( name.equals("day_repay_margin_deposit_2") ) {
                    return this.day_repay_margin_deposit_2;
                }
                else if ( name.equals("day_repay_margin_deposit_3") ) {
                    return this.day_repay_margin_deposit_3;
                }
                else if ( name.equals("day_repay_margin_deposit_4") ) {
                    return this.day_repay_margin_deposit_4;
                }
                else if ( name.equals("day_repay_margin_deposit_5") ) {
                    return this.day_repay_margin_deposit_5;
                }
                else if ( name.equals("day_repay_cash_margin_deposit0") ) {
                    return this.day_repay_cash_margin_deposit0;
                }
                else if ( name.equals("day_repay_cash_margin_deposit1") ) {
                    return this.day_repay_cash_margin_deposit1;
                }
                else if ( name.equals("day_repay_cash_margin_deposit2") ) {
                    return this.day_repay_cash_margin_deposit2;
                }
                else if ( name.equals("day_repay_cash_margin_deposit3") ) {
                    return this.day_repay_cash_margin_deposit3;
                }
                else if ( name.equals("day_repay_cash_margin_deposit4") ) {
                    return this.day_repay_cash_margin_deposit4;
                }
                else if ( name.equals("day_repay_cash_margin_deposit5") ) {
                    return this.day_repay_cash_margin_deposit5;
                }
                break;
            case 'e':
                if ( name.equals("equity_asset_delivered") ) {
                    return this.equity_asset_delivered;
                }
                else if ( name.equals("equity_asset_executed") ) {
                    return this.equity_asset_executed;
                }
                break;
            case 'f':
                if ( name.equals("foreign_equity_asset_delivered") ) {
                    return this.foreign_equity_asset_delivered;
                }
                else if ( name.equals("foreign_equity_asset_executed") ) {
                    return this.foreign_equity_asset_executed;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("mini_stock_asset_delivered") ) {
                    return this.mini_stock_asset_delivered;
                }
                else if ( name.equals("mini_stock_asset_executed") ) {
                    return this.mini_stock_asset_executed;
                }
                else if ( name.equals("mutual_fund_asset_delivered") ) {
                    return this.mutual_fund_asset_delivered;
                }
                else if ( name.equals("mutual_fund_asset_executed") ) {
                    return this.mutual_fund_asset_executed;
                }
                break;
            case 'p':
                if ( name.equals("payment_amount_designate_0") ) {
                    return this.payment_amount_designate_0;
                }
                else if ( name.equals("payment_amount_designate_1") ) {
                    return this.payment_amount_designate_1;
                }
                else if ( name.equals("payment_amount_designate_2") ) {
                    return this.payment_amount_designate_2;
                }
                else if ( name.equals("payment_amount_designate_3") ) {
                    return this.payment_amount_designate_3;
                }
                else if ( name.equals("payment_amount_designate_4") ) {
                    return this.payment_amount_designate_4;
                }
                else if ( name.equals("payment_amount_designate_5") ) {
                    return this.payment_amount_designate_5;
                }
                break;
            case 'r':
                if ( name.equals("ruito_asset_delivered") ) {
                    return this.ruito_asset_delivered;
                }
                else if ( name.equals("ruito_asset_executed") ) {
                    return this.ruito_asset_executed;
                }
                break;
            case 's':
                if ( name.equals("setup_fee") ) {
                    return this.setup_fee;
                }
                else if ( name.equals("substitute_asset_old_day_value") ) {
                    return this.substitute_asset_old_day_value;
                }
                else if ( name.equals("setup_fee_1") ) {
                    return this.setup_fee_1;
                }
                else if ( name.equals("setup_fee_2") ) {
                    return this.setup_fee_2;
                }
                else if ( name.equals("setup_fee_3") ) {
                    return this.setup_fee_3;
                }
                else if ( name.equals("setup_fee_4") ) {
                    return this.setup_fee_4;
                }
                else if ( name.equals("setup_fee_5") ) {
                    return this.setup_fee_5;
                }
                break;
            case 't':
                if ( name.equals("today_repay_contract_loss") ) {
                    return this.today_repay_contract_loss;
                }
                else if ( name.equals("today_repay_contract_profit") ) {
                    return this.today_repay_contract_profit;
                }
                else if ( name.equals("today_repay_contract_pre_asset") ) {
                    return this.today_repay_contract_pre_asset;
                }
                else if ( name.equals("today_dep_fund_restraint_0") ) {
                    return this.today_dep_fund_restraint_0;
                }
                else if ( name.equals("today_dep_fund_restraint_1") ) {
                    return this.today_dep_fund_restraint_1;
                }
                else if ( name.equals("today_dep_fund_restraint_2") ) {
                    return this.today_dep_fund_restraint_2;
                }
                else if ( name.equals("today_dep_fund_restraint_3") ) {
                    return this.today_dep_fund_restraint_3;
                }
                else if ( name.equals("today_dep_fund_restraint_4") ) {
                    return this.today_dep_fund_restraint_4;
                }
                else if ( name.equals("today_dep_fund_restraint_5") ) {
                    return this.today_dep_fund_restraint_5;
                }
                else if ( name.equals("today_repay_contract_amount") ) {
                    return this.today_repay_contract_amount;
                }
                break;
            case 'u':
                if ( name.equals("unexec_substi_security_asset_0") ) {
                    return this.unexec_substi_security_asset_0;
                }
                else if ( name.equals("unexec_substi_security_asset_1") ) {
                    return this.unexec_substi_security_asset_1;
                }
                else if ( name.equals("unexec_substi_security_asset_2") ) {
                    return this.unexec_substi_security_asset_2;
                }
                else if ( name.equals("unexec_substi_security_asset_3") ) {
                    return this.unexec_substi_security_asset_3;
                }
                else if ( name.equals("unexec_substi_security_asset_4") ) {
                    return this.unexec_substi_security_asset_4;
                }
                else if ( name.equals("unexec_substi_security_asset_5") ) {
                    return this.unexec_substi_security_asset_5;
                }
                else if ( name.equals("unexec_contract_amount_0") ) {
                    return this.unexec_contract_amount_0;
                }
                else if ( name.equals("unexec_contract_amount_1") ) {
                    return this.unexec_contract_amount_1;
                }
                else if ( name.equals("unexec_contract_amount_2") ) {
                    return this.unexec_contract_amount_2;
                }
                else if ( name.equals("unexec_contract_amount_3") ) {
                    return this.unexec_contract_amount_3;
                }
                else if ( name.equals("unexec_contract_amount_4") ) {
                    return this.unexec_contract_amount_4;
                }
                else if ( name.equals("unexec_contract_amount_5") ) {
                    return this.unexec_contract_amount_5;
                }
                else if ( name.equals("unexec_margin_deposit_0") ) {
                    return this.unexec_margin_deposit_0;
                }
                else if ( name.equals("unexec_margin_deposit_1") ) {
                    return this.unexec_margin_deposit_1;
                }
                else if ( name.equals("unexec_margin_deposit_2") ) {
                    return this.unexec_margin_deposit_2;
                }
                else if ( name.equals("unexec_margin_deposit_3") ) {
                    return this.unexec_margin_deposit_3;
                }
                else if ( name.equals("unexec_margin_deposit_4") ) {
                    return this.unexec_margin_deposit_4;
                }
                else if ( name.equals("unexec_margin_deposit_5") ) {
                    return this.unexec_margin_deposit_5;
                }
                else if ( name.equals("unexec_cash_margin_deposit_0") ) {
                    return this.unexec_cash_margin_deposit_0;
                }
                else if ( name.equals("unexec_cash_margin_deposit_1") ) {
                    return this.unexec_cash_margin_deposit_1;
                }
                else if ( name.equals("unexec_cash_margin_deposit_2") ) {
                    return this.unexec_cash_margin_deposit_2;
                }
                else if ( name.equals("unexec_cash_margin_deposit_3") ) {
                    return this.unexec_cash_margin_deposit_3;
                }
                else if ( name.equals("unexec_cash_margin_deposit_4") ) {
                    return this.unexec_cash_margin_deposit_4;
                }
                else if ( name.equals("unexec_cash_margin_deposit_5") ) {
                    return this.unexec_cash_margin_deposit_5;
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
                break;
            case 'b':
                if ( name.equals("bond_asset_delivered") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'bond_asset_delivered' must be Double: '"+value+"' is not." );
                    this.bond_asset_delivered = (Double) value;
                    if (this.bond_asset_delivered_is_set)
                        this.bond_asset_delivered_is_modified = true;
                    this.bond_asset_delivered_is_set = true;
                    return;
                }
                else if ( name.equals("bond_asset_executed") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'bond_asset_executed' must be Double: '"+value+"' is not." );
                    this.bond_asset_executed = (Double) value;
                    if (this.bond_asset_executed_is_set)
                        this.bond_asset_executed_is_modified = true;
                    this.bond_asset_executed_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("calc_result_margin_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'calc_result_margin_id' must be Long: '"+value+"' is not." );
                    this.calc_result_margin_id = ((Long) value).longValue();
                    if (this.calc_result_margin_id_is_set)
                        this.calc_result_margin_id_is_modified = true;
                    this.calc_result_margin_id_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_loss") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_loss' must be Double: '"+value+"' is not." );
                    this.contract_asset_loss = (Double) value;
                    if (this.contract_asset_loss_is_set)
                        this.contract_asset_loss_is_modified = true;
                    this.contract_asset_loss_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_profit") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_profit' must be Double: '"+value+"' is not." );
                    this.contract_asset_profit = (Double) value;
                    if (this.contract_asset_profit_is_set)
                        this.contract_asset_profit_is_modified = true;
                    this.contract_asset_profit_is_set = true;
                    return;
                }
                else if ( name.equals("contract_interest_loss") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_interest_loss' must be Double: '"+value+"' is not." );
                    this.contract_interest_loss = (Double) value;
                    if (this.contract_interest_loss_is_set)
                        this.contract_interest_loss_is_modified = true;
                    this.contract_interest_loss_is_set = true;
                    return;
                }
                else if ( name.equals("contract_interest_profit") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_interest_profit' must be Double: '"+value+"' is not." );
                    this.contract_interest_profit = (Double) value;
                    if (this.contract_interest_profit_is_set)
                        this.contract_interest_profit_is_modified = true;
                    this.contract_interest_profit_is_set = true;
                    return;
                }
                else if ( name.equals("contract_other_cost") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_other_cost' must be Double: '"+value+"' is not." );
                    this.contract_other_cost = (Double) value;
                    if (this.contract_other_cost_is_set)
                        this.contract_other_cost_is_modified = true;
                    this.contract_other_cost_is_set = true;
                    return;
                }
                else if ( name.equals("contract_loss_designate_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_loss_designate_1' must be Double: '"+value+"' is not." );
                    this.contract_loss_designate_1 = (Double) value;
                    if (this.contract_loss_designate_1_is_set)
                        this.contract_loss_designate_1_is_modified = true;
                    this.contract_loss_designate_1_is_set = true;
                    return;
                }
                else if ( name.equals("contract_loss_designate_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_loss_designate_2' must be Double: '"+value+"' is not." );
                    this.contract_loss_designate_2 = (Double) value;
                    if (this.contract_loss_designate_2_is_set)
                        this.contract_loss_designate_2_is_modified = true;
                    this.contract_loss_designate_2_is_set = true;
                    return;
                }
                else if ( name.equals("contract_loss_designate_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_loss_designate_3' must be Double: '"+value+"' is not." );
                    this.contract_loss_designate_3 = (Double) value;
                    if (this.contract_loss_designate_3_is_set)
                        this.contract_loss_designate_3_is_modified = true;
                    this.contract_loss_designate_3_is_set = true;
                    return;
                }
                else if ( name.equals("contract_loss_designate_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_loss_designate_4' must be Double: '"+value+"' is not." );
                    this.contract_loss_designate_4 = (Double) value;
                    if (this.contract_loss_designate_4_is_set)
                        this.contract_loss_designate_4_is_modified = true;
                    this.contract_loss_designate_4_is_set = true;
                    return;
                }
                else if ( name.equals("contract_loss_designate_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_loss_designate_5' must be Double: '"+value+"' is not." );
                    this.contract_loss_designate_5 = (Double) value;
                    if (this.contract_loss_designate_5_is_set)
                        this.contract_loss_designate_5_is_modified = true;
                    this.contract_loss_designate_5_is_set = true;
                    return;
                }
                else if ( name.equals("contract_profit_designate_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_profit_designate_1' must be Double: '"+value+"' is not." );
                    this.contract_profit_designate_1 = (Double) value;
                    if (this.contract_profit_designate_1_is_set)
                        this.contract_profit_designate_1_is_modified = true;
                    this.contract_profit_designate_1_is_set = true;
                    return;
                }
                else if ( name.equals("contract_profit_designate_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_profit_designate_2' must be Double: '"+value+"' is not." );
                    this.contract_profit_designate_2 = (Double) value;
                    if (this.contract_profit_designate_2_is_set)
                        this.contract_profit_designate_2_is_modified = true;
                    this.contract_profit_designate_2_is_set = true;
                    return;
                }
                else if ( name.equals("contract_profit_designate_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_profit_designate_3' must be Double: '"+value+"' is not." );
                    this.contract_profit_designate_3 = (Double) value;
                    if (this.contract_profit_designate_3_is_set)
                        this.contract_profit_designate_3_is_modified = true;
                    this.contract_profit_designate_3_is_set = true;
                    return;
                }
                else if ( name.equals("contract_profit_designate_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_profit_designate_4' must be Double: '"+value+"' is not." );
                    this.contract_profit_designate_4 = (Double) value;
                    if (this.contract_profit_designate_4_is_set)
                        this.contract_profit_designate_4_is_modified = true;
                    this.contract_profit_designate_4_is_set = true;
                    return;
                }
                else if ( name.equals("contract_profit_designate_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_profit_designate_5' must be Double: '"+value+"' is not." );
                    this.contract_profit_designate_5 = (Double) value;
                    if (this.contract_profit_designate_5_is_set)
                        this.contract_profit_designate_5_is_modified = true;
                    this.contract_profit_designate_5_is_set = true;
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
                else if ( name.equals("contract_asset_loss_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_loss_1' must be Double: '"+value+"' is not." );
                    this.contract_asset_loss_1 = (Double) value;
                    if (this.contract_asset_loss_1_is_set)
                        this.contract_asset_loss_1_is_modified = true;
                    this.contract_asset_loss_1_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_loss_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_loss_2' must be Double: '"+value+"' is not." );
                    this.contract_asset_loss_2 = (Double) value;
                    if (this.contract_asset_loss_2_is_set)
                        this.contract_asset_loss_2_is_modified = true;
                    this.contract_asset_loss_2_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_loss_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_loss_3' must be Double: '"+value+"' is not." );
                    this.contract_asset_loss_3 = (Double) value;
                    if (this.contract_asset_loss_3_is_set)
                        this.contract_asset_loss_3_is_modified = true;
                    this.contract_asset_loss_3_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_loss_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_loss_4' must be Double: '"+value+"' is not." );
                    this.contract_asset_loss_4 = (Double) value;
                    if (this.contract_asset_loss_4_is_set)
                        this.contract_asset_loss_4_is_modified = true;
                    this.contract_asset_loss_4_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_loss_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_loss_5' must be Double: '"+value+"' is not." );
                    this.contract_asset_loss_5 = (Double) value;
                    if (this.contract_asset_loss_5_is_set)
                        this.contract_asset_loss_5_is_modified = true;
                    this.contract_asset_loss_5_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_profit_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_profit_1' must be Double: '"+value+"' is not." );
                    this.contract_asset_profit_1 = (Double) value;
                    if (this.contract_asset_profit_1_is_set)
                        this.contract_asset_profit_1_is_modified = true;
                    this.contract_asset_profit_1_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_profit_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_profit_2' must be Double: '"+value+"' is not." );
                    this.contract_asset_profit_2 = (Double) value;
                    if (this.contract_asset_profit_2_is_set)
                        this.contract_asset_profit_2_is_modified = true;
                    this.contract_asset_profit_2_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_profit_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_profit_3' must be Double: '"+value+"' is not." );
                    this.contract_asset_profit_3 = (Double) value;
                    if (this.contract_asset_profit_3_is_set)
                        this.contract_asset_profit_3_is_modified = true;
                    this.contract_asset_profit_3_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_profit_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_profit_4' must be Double: '"+value+"' is not." );
                    this.contract_asset_profit_4 = (Double) value;
                    if (this.contract_asset_profit_4_is_set)
                        this.contract_asset_profit_4_is_modified = true;
                    this.contract_asset_profit_4_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_profit_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_profit_5' must be Double: '"+value+"' is not." );
                    this.contract_asset_profit_5 = (Double) value;
                    if (this.contract_asset_profit_5_is_set)
                        this.contract_asset_profit_5_is_modified = true;
                    this.contract_asset_profit_5_is_set = true;
                    return;
                }
                else if ( name.equals("contract_interest_loss_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_interest_loss_1' must be Double: '"+value+"' is not." );
                    this.contract_interest_loss_1 = (Double) value;
                    if (this.contract_interest_loss_1_is_set)
                        this.contract_interest_loss_1_is_modified = true;
                    this.contract_interest_loss_1_is_set = true;
                    return;
                }
                else if ( name.equals("contract_interest_loss_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_interest_loss_2' must be Double: '"+value+"' is not." );
                    this.contract_interest_loss_2 = (Double) value;
                    if (this.contract_interest_loss_2_is_set)
                        this.contract_interest_loss_2_is_modified = true;
                    this.contract_interest_loss_2_is_set = true;
                    return;
                }
                else if ( name.equals("contract_interest_loss_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_interest_loss_3' must be Double: '"+value+"' is not." );
                    this.contract_interest_loss_3 = (Double) value;
                    if (this.contract_interest_loss_3_is_set)
                        this.contract_interest_loss_3_is_modified = true;
                    this.contract_interest_loss_3_is_set = true;
                    return;
                }
                else if ( name.equals("contract_interest_loss_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_interest_loss_4' must be Double: '"+value+"' is not." );
                    this.contract_interest_loss_4 = (Double) value;
                    if (this.contract_interest_loss_4_is_set)
                        this.contract_interest_loss_4_is_modified = true;
                    this.contract_interest_loss_4_is_set = true;
                    return;
                }
                else if ( name.equals("contract_interest_loss_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_interest_loss_5' must be Double: '"+value+"' is not." );
                    this.contract_interest_loss_5 = (Double) value;
                    if (this.contract_interest_loss_5_is_set)
                        this.contract_interest_loss_5_is_modified = true;
                    this.contract_interest_loss_5_is_set = true;
                    return;
                }
                else if ( name.equals("contract_interest_profit_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_interest_profit_1' must be Double: '"+value+"' is not." );
                    this.contract_interest_profit_1 = (Double) value;
                    if (this.contract_interest_profit_1_is_set)
                        this.contract_interest_profit_1_is_modified = true;
                    this.contract_interest_profit_1_is_set = true;
                    return;
                }
                else if ( name.equals("contract_interest_profit_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_interest_profit_2' must be Double: '"+value+"' is not." );
                    this.contract_interest_profit_2 = (Double) value;
                    if (this.contract_interest_profit_2_is_set)
                        this.contract_interest_profit_2_is_modified = true;
                    this.contract_interest_profit_2_is_set = true;
                    return;
                }
                else if ( name.equals("contract_interest_profit_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_interest_profit_3' must be Double: '"+value+"' is not." );
                    this.contract_interest_profit_3 = (Double) value;
                    if (this.contract_interest_profit_3_is_set)
                        this.contract_interest_profit_3_is_modified = true;
                    this.contract_interest_profit_3_is_set = true;
                    return;
                }
                else if ( name.equals("contract_interest_profit_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_interest_profit_4' must be Double: '"+value+"' is not." );
                    this.contract_interest_profit_4 = (Double) value;
                    if (this.contract_interest_profit_4_is_set)
                        this.contract_interest_profit_4_is_modified = true;
                    this.contract_interest_profit_4_is_set = true;
                    return;
                }
                else if ( name.equals("contract_interest_profit_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_interest_profit_5' must be Double: '"+value+"' is not." );
                    this.contract_interest_profit_5 = (Double) value;
                    if (this.contract_interest_profit_5_is_set)
                        this.contract_interest_profit_5_is_modified = true;
                    this.contract_interest_profit_5_is_set = true;
                    return;
                }
                else if ( name.equals("contract_other_cost_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_other_cost_1' must be Double: '"+value+"' is not." );
                    this.contract_other_cost_1 = (Double) value;
                    if (this.contract_other_cost_1_is_set)
                        this.contract_other_cost_1_is_modified = true;
                    this.contract_other_cost_1_is_set = true;
                    return;
                }
                else if ( name.equals("contract_other_cost_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_other_cost_2' must be Double: '"+value+"' is not." );
                    this.contract_other_cost_2 = (Double) value;
                    if (this.contract_other_cost_2_is_set)
                        this.contract_other_cost_2_is_modified = true;
                    this.contract_other_cost_2_is_set = true;
                    return;
                }
                else if ( name.equals("contract_other_cost_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_other_cost_3' must be Double: '"+value+"' is not." );
                    this.contract_other_cost_3 = (Double) value;
                    if (this.contract_other_cost_3_is_set)
                        this.contract_other_cost_3_is_modified = true;
                    this.contract_other_cost_3_is_set = true;
                    return;
                }
                else if ( name.equals("contract_other_cost_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_other_cost_4' must be Double: '"+value+"' is not." );
                    this.contract_other_cost_4 = (Double) value;
                    if (this.contract_other_cost_4_is_set)
                        this.contract_other_cost_4_is_modified = true;
                    this.contract_other_cost_4_is_set = true;
                    return;
                }
                else if ( name.equals("contract_other_cost_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_other_cost_5' must be Double: '"+value+"' is not." );
                    this.contract_other_cost_5 = (Double) value;
                    if (this.contract_other_cost_5_is_set)
                        this.contract_other_cost_5_is_modified = true;
                    this.contract_other_cost_5_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("day_repay_margin_deposit_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_margin_deposit_0' must be Double: '"+value+"' is not." );
                    this.day_repay_margin_deposit_0 = (Double) value;
                    if (this.day_repay_margin_deposit_0_is_set)
                        this.day_repay_margin_deposit_0_is_modified = true;
                    this.day_repay_margin_deposit_0_is_set = true;
                    return;
                }
                else if ( name.equals("day_repay_margin_deposit_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_margin_deposit_1' must be Double: '"+value+"' is not." );
                    this.day_repay_margin_deposit_1 = (Double) value;
                    if (this.day_repay_margin_deposit_1_is_set)
                        this.day_repay_margin_deposit_1_is_modified = true;
                    this.day_repay_margin_deposit_1_is_set = true;
                    return;
                }
                else if ( name.equals("day_repay_margin_deposit_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_margin_deposit_2' must be Double: '"+value+"' is not." );
                    this.day_repay_margin_deposit_2 = (Double) value;
                    if (this.day_repay_margin_deposit_2_is_set)
                        this.day_repay_margin_deposit_2_is_modified = true;
                    this.day_repay_margin_deposit_2_is_set = true;
                    return;
                }
                else if ( name.equals("day_repay_margin_deposit_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_margin_deposit_3' must be Double: '"+value+"' is not." );
                    this.day_repay_margin_deposit_3 = (Double) value;
                    if (this.day_repay_margin_deposit_3_is_set)
                        this.day_repay_margin_deposit_3_is_modified = true;
                    this.day_repay_margin_deposit_3_is_set = true;
                    return;
                }
                else if ( name.equals("day_repay_margin_deposit_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_margin_deposit_4' must be Double: '"+value+"' is not." );
                    this.day_repay_margin_deposit_4 = (Double) value;
                    if (this.day_repay_margin_deposit_4_is_set)
                        this.day_repay_margin_deposit_4_is_modified = true;
                    this.day_repay_margin_deposit_4_is_set = true;
                    return;
                }
                else if ( name.equals("day_repay_margin_deposit_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_margin_deposit_5' must be Double: '"+value+"' is not." );
                    this.day_repay_margin_deposit_5 = (Double) value;
                    if (this.day_repay_margin_deposit_5_is_set)
                        this.day_repay_margin_deposit_5_is_modified = true;
                    this.day_repay_margin_deposit_5_is_set = true;
                    return;
                }
                else if ( name.equals("day_repay_cash_margin_deposit0") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_cash_margin_deposit0' must be Double: '"+value+"' is not." );
                    this.day_repay_cash_margin_deposit0 = (Double) value;
                    if (this.day_repay_cash_margin_deposit0_is_set)
                        this.day_repay_cash_margin_deposit0_is_modified = true;
                    this.day_repay_cash_margin_deposit0_is_set = true;
                    return;
                }
                else if ( name.equals("day_repay_cash_margin_deposit1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_cash_margin_deposit1' must be Double: '"+value+"' is not." );
                    this.day_repay_cash_margin_deposit1 = (Double) value;
                    if (this.day_repay_cash_margin_deposit1_is_set)
                        this.day_repay_cash_margin_deposit1_is_modified = true;
                    this.day_repay_cash_margin_deposit1_is_set = true;
                    return;
                }
                else if ( name.equals("day_repay_cash_margin_deposit2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_cash_margin_deposit2' must be Double: '"+value+"' is not." );
                    this.day_repay_cash_margin_deposit2 = (Double) value;
                    if (this.day_repay_cash_margin_deposit2_is_set)
                        this.day_repay_cash_margin_deposit2_is_modified = true;
                    this.day_repay_cash_margin_deposit2_is_set = true;
                    return;
                }
                else if ( name.equals("day_repay_cash_margin_deposit3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_cash_margin_deposit3' must be Double: '"+value+"' is not." );
                    this.day_repay_cash_margin_deposit3 = (Double) value;
                    if (this.day_repay_cash_margin_deposit3_is_set)
                        this.day_repay_cash_margin_deposit3_is_modified = true;
                    this.day_repay_cash_margin_deposit3_is_set = true;
                    return;
                }
                else if ( name.equals("day_repay_cash_margin_deposit4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_cash_margin_deposit4' must be Double: '"+value+"' is not." );
                    this.day_repay_cash_margin_deposit4 = (Double) value;
                    if (this.day_repay_cash_margin_deposit4_is_set)
                        this.day_repay_cash_margin_deposit4_is_modified = true;
                    this.day_repay_cash_margin_deposit4_is_set = true;
                    return;
                }
                else if ( name.equals("day_repay_cash_margin_deposit5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_cash_margin_deposit5' must be Double: '"+value+"' is not." );
                    this.day_repay_cash_margin_deposit5 = (Double) value;
                    if (this.day_repay_cash_margin_deposit5_is_set)
                        this.day_repay_cash_margin_deposit5_is_modified = true;
                    this.day_repay_cash_margin_deposit5_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("equity_asset_delivered") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'equity_asset_delivered' must be Double: '"+value+"' is not." );
                    this.equity_asset_delivered = (Double) value;
                    if (this.equity_asset_delivered_is_set)
                        this.equity_asset_delivered_is_modified = true;
                    this.equity_asset_delivered_is_set = true;
                    return;
                }
                else if ( name.equals("equity_asset_executed") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'equity_asset_executed' must be Double: '"+value+"' is not." );
                    this.equity_asset_executed = (Double) value;
                    if (this.equity_asset_executed_is_set)
                        this.equity_asset_executed_is_modified = true;
                    this.equity_asset_executed_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("foreign_equity_asset_delivered") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'foreign_equity_asset_delivered' must be Double: '"+value+"' is not." );
                    this.foreign_equity_asset_delivered = (Double) value;
                    if (this.foreign_equity_asset_delivered_is_set)
                        this.foreign_equity_asset_delivered_is_modified = true;
                    this.foreign_equity_asset_delivered_is_set = true;
                    return;
                }
                else if ( name.equals("foreign_equity_asset_executed") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'foreign_equity_asset_executed' must be Double: '"+value+"' is not." );
                    this.foreign_equity_asset_executed = (Double) value;
                    if (this.foreign_equity_asset_executed_is_set)
                        this.foreign_equity_asset_executed_is_modified = true;
                    this.foreign_equity_asset_executed_is_set = true;
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
                if ( name.equals("mini_stock_asset_delivered") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'mini_stock_asset_delivered' must be Double: '"+value+"' is not." );
                    this.mini_stock_asset_delivered = (Double) value;
                    if (this.mini_stock_asset_delivered_is_set)
                        this.mini_stock_asset_delivered_is_modified = true;
                    this.mini_stock_asset_delivered_is_set = true;
                    return;
                }
                else if ( name.equals("mini_stock_asset_executed") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'mini_stock_asset_executed' must be Double: '"+value+"' is not." );
                    this.mini_stock_asset_executed = (Double) value;
                    if (this.mini_stock_asset_executed_is_set)
                        this.mini_stock_asset_executed_is_modified = true;
                    this.mini_stock_asset_executed_is_set = true;
                    return;
                }
                else if ( name.equals("mutual_fund_asset_delivered") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'mutual_fund_asset_delivered' must be Double: '"+value+"' is not." );
                    this.mutual_fund_asset_delivered = (Double) value;
                    if (this.mutual_fund_asset_delivered_is_set)
                        this.mutual_fund_asset_delivered_is_modified = true;
                    this.mutual_fund_asset_delivered_is_set = true;
                    return;
                }
                else if ( name.equals("mutual_fund_asset_executed") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'mutual_fund_asset_executed' must be Double: '"+value+"' is not." );
                    this.mutual_fund_asset_executed = (Double) value;
                    if (this.mutual_fund_asset_executed_is_set)
                        this.mutual_fund_asset_executed_is_modified = true;
                    this.mutual_fund_asset_executed_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("payment_amount_designate_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate_0' must be Double: '"+value+"' is not." );
                    this.payment_amount_designate_0 = (Double) value;
                    if (this.payment_amount_designate_0_is_set)
                        this.payment_amount_designate_0_is_modified = true;
                    this.payment_amount_designate_0_is_set = true;
                    return;
                }
                else if ( name.equals("payment_amount_designate_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate_1' must be Double: '"+value+"' is not." );
                    this.payment_amount_designate_1 = (Double) value;
                    if (this.payment_amount_designate_1_is_set)
                        this.payment_amount_designate_1_is_modified = true;
                    this.payment_amount_designate_1_is_set = true;
                    return;
                }
                else if ( name.equals("payment_amount_designate_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate_2' must be Double: '"+value+"' is not." );
                    this.payment_amount_designate_2 = (Double) value;
                    if (this.payment_amount_designate_2_is_set)
                        this.payment_amount_designate_2_is_modified = true;
                    this.payment_amount_designate_2_is_set = true;
                    return;
                }
                else if ( name.equals("payment_amount_designate_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate_3' must be Double: '"+value+"' is not." );
                    this.payment_amount_designate_3 = (Double) value;
                    if (this.payment_amount_designate_3_is_set)
                        this.payment_amount_designate_3_is_modified = true;
                    this.payment_amount_designate_3_is_set = true;
                    return;
                }
                else if ( name.equals("payment_amount_designate_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate_4' must be Double: '"+value+"' is not." );
                    this.payment_amount_designate_4 = (Double) value;
                    if (this.payment_amount_designate_4_is_set)
                        this.payment_amount_designate_4_is_modified = true;
                    this.payment_amount_designate_4_is_set = true;
                    return;
                }
                else if ( name.equals("payment_amount_designate_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate_5' must be Double: '"+value+"' is not." );
                    this.payment_amount_designate_5 = (Double) value;
                    if (this.payment_amount_designate_5_is_set)
                        this.payment_amount_designate_5_is_modified = true;
                    this.payment_amount_designate_5_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("ruito_asset_delivered") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'ruito_asset_delivered' must be Double: '"+value+"' is not." );
                    this.ruito_asset_delivered = (Double) value;
                    if (this.ruito_asset_delivered_is_set)
                        this.ruito_asset_delivered_is_modified = true;
                    this.ruito_asset_delivered_is_set = true;
                    return;
                }
                else if ( name.equals("ruito_asset_executed") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'ruito_asset_executed' must be Double: '"+value+"' is not." );
                    this.ruito_asset_executed = (Double) value;
                    if (this.ruito_asset_executed_is_set)
                        this.ruito_asset_executed_is_modified = true;
                    this.ruito_asset_executed_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("setup_fee") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'setup_fee' must be Double: '"+value+"' is not." );
                    this.setup_fee = (Double) value;
                    if (this.setup_fee_is_set)
                        this.setup_fee_is_modified = true;
                    this.setup_fee_is_set = true;
                    return;
                }
                else if ( name.equals("substitute_asset_old_day_value") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'substitute_asset_old_day_value' must be Double: '"+value+"' is not." );
                    this.substitute_asset_old_day_value = (Double) value;
                    if (this.substitute_asset_old_day_value_is_set)
                        this.substitute_asset_old_day_value_is_modified = true;
                    this.substitute_asset_old_day_value_is_set = true;
                    return;
                }
                else if ( name.equals("setup_fee_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'setup_fee_1' must be Double: '"+value+"' is not." );
                    this.setup_fee_1 = (Double) value;
                    if (this.setup_fee_1_is_set)
                        this.setup_fee_1_is_modified = true;
                    this.setup_fee_1_is_set = true;
                    return;
                }
                else if ( name.equals("setup_fee_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'setup_fee_2' must be Double: '"+value+"' is not." );
                    this.setup_fee_2 = (Double) value;
                    if (this.setup_fee_2_is_set)
                        this.setup_fee_2_is_modified = true;
                    this.setup_fee_2_is_set = true;
                    return;
                }
                else if ( name.equals("setup_fee_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'setup_fee_3' must be Double: '"+value+"' is not." );
                    this.setup_fee_3 = (Double) value;
                    if (this.setup_fee_3_is_set)
                        this.setup_fee_3_is_modified = true;
                    this.setup_fee_3_is_set = true;
                    return;
                }
                else if ( name.equals("setup_fee_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'setup_fee_4' must be Double: '"+value+"' is not." );
                    this.setup_fee_4 = (Double) value;
                    if (this.setup_fee_4_is_set)
                        this.setup_fee_4_is_modified = true;
                    this.setup_fee_4_is_set = true;
                    return;
                }
                else if ( name.equals("setup_fee_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'setup_fee_5' must be Double: '"+value+"' is not." );
                    this.setup_fee_5 = (Double) value;
                    if (this.setup_fee_5_is_set)
                        this.setup_fee_5_is_modified = true;
                    this.setup_fee_5_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("today_repay_contract_loss") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_repay_contract_loss' must be Double: '"+value+"' is not." );
                    this.today_repay_contract_loss = (Double) value;
                    if (this.today_repay_contract_loss_is_set)
                        this.today_repay_contract_loss_is_modified = true;
                    this.today_repay_contract_loss_is_set = true;
                    return;
                }
                else if ( name.equals("today_repay_contract_profit") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_repay_contract_profit' must be Double: '"+value+"' is not." );
                    this.today_repay_contract_profit = (Double) value;
                    if (this.today_repay_contract_profit_is_set)
                        this.today_repay_contract_profit_is_modified = true;
                    this.today_repay_contract_profit_is_set = true;
                    return;
                }
                else if ( name.equals("today_repay_contract_pre_asset") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_repay_contract_pre_asset' must be Double: '"+value+"' is not." );
                    this.today_repay_contract_pre_asset = (Double) value;
                    if (this.today_repay_contract_pre_asset_is_set)
                        this.today_repay_contract_pre_asset_is_modified = true;
                    this.today_repay_contract_pre_asset_is_set = true;
                    return;
                }
                else if ( name.equals("today_dep_fund_restraint_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_dep_fund_restraint_0' must be Double: '"+value+"' is not." );
                    this.today_dep_fund_restraint_0 = (Double) value;
                    if (this.today_dep_fund_restraint_0_is_set)
                        this.today_dep_fund_restraint_0_is_modified = true;
                    this.today_dep_fund_restraint_0_is_set = true;
                    return;
                }
                else if ( name.equals("today_dep_fund_restraint_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_dep_fund_restraint_1' must be Double: '"+value+"' is not." );
                    this.today_dep_fund_restraint_1 = (Double) value;
                    if (this.today_dep_fund_restraint_1_is_set)
                        this.today_dep_fund_restraint_1_is_modified = true;
                    this.today_dep_fund_restraint_1_is_set = true;
                    return;
                }
                else if ( name.equals("today_dep_fund_restraint_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_dep_fund_restraint_2' must be Double: '"+value+"' is not." );
                    this.today_dep_fund_restraint_2 = (Double) value;
                    if (this.today_dep_fund_restraint_2_is_set)
                        this.today_dep_fund_restraint_2_is_modified = true;
                    this.today_dep_fund_restraint_2_is_set = true;
                    return;
                }
                else if ( name.equals("today_dep_fund_restraint_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_dep_fund_restraint_3' must be Double: '"+value+"' is not." );
                    this.today_dep_fund_restraint_3 = (Double) value;
                    if (this.today_dep_fund_restraint_3_is_set)
                        this.today_dep_fund_restraint_3_is_modified = true;
                    this.today_dep_fund_restraint_3_is_set = true;
                    return;
                }
                else if ( name.equals("today_dep_fund_restraint_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_dep_fund_restraint_4' must be Double: '"+value+"' is not." );
                    this.today_dep_fund_restraint_4 = (Double) value;
                    if (this.today_dep_fund_restraint_4_is_set)
                        this.today_dep_fund_restraint_4_is_modified = true;
                    this.today_dep_fund_restraint_4_is_set = true;
                    return;
                }
                else if ( name.equals("today_dep_fund_restraint_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_dep_fund_restraint_5' must be Double: '"+value+"' is not." );
                    this.today_dep_fund_restraint_5 = (Double) value;
                    if (this.today_dep_fund_restraint_5_is_set)
                        this.today_dep_fund_restraint_5_is_modified = true;
                    this.today_dep_fund_restraint_5_is_set = true;
                    return;
                }
                else if ( name.equals("today_repay_contract_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_repay_contract_amount' must be Double: '"+value+"' is not." );
                    this.today_repay_contract_amount = (Double) value;
                    if (this.today_repay_contract_amount_is_set)
                        this.today_repay_contract_amount_is_modified = true;
                    this.today_repay_contract_amount_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("unexec_substi_security_asset_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_substi_security_asset_0' must be Double: '"+value+"' is not." );
                    this.unexec_substi_security_asset_0 = (Double) value;
                    if (this.unexec_substi_security_asset_0_is_set)
                        this.unexec_substi_security_asset_0_is_modified = true;
                    this.unexec_substi_security_asset_0_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_substi_security_asset_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_substi_security_asset_1' must be Double: '"+value+"' is not." );
                    this.unexec_substi_security_asset_1 = (Double) value;
                    if (this.unexec_substi_security_asset_1_is_set)
                        this.unexec_substi_security_asset_1_is_modified = true;
                    this.unexec_substi_security_asset_1_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_substi_security_asset_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_substi_security_asset_2' must be Double: '"+value+"' is not." );
                    this.unexec_substi_security_asset_2 = (Double) value;
                    if (this.unexec_substi_security_asset_2_is_set)
                        this.unexec_substi_security_asset_2_is_modified = true;
                    this.unexec_substi_security_asset_2_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_substi_security_asset_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_substi_security_asset_3' must be Double: '"+value+"' is not." );
                    this.unexec_substi_security_asset_3 = (Double) value;
                    if (this.unexec_substi_security_asset_3_is_set)
                        this.unexec_substi_security_asset_3_is_modified = true;
                    this.unexec_substi_security_asset_3_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_substi_security_asset_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_substi_security_asset_4' must be Double: '"+value+"' is not." );
                    this.unexec_substi_security_asset_4 = (Double) value;
                    if (this.unexec_substi_security_asset_4_is_set)
                        this.unexec_substi_security_asset_4_is_modified = true;
                    this.unexec_substi_security_asset_4_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_substi_security_asset_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_substi_security_asset_5' must be Double: '"+value+"' is not." );
                    this.unexec_substi_security_asset_5 = (Double) value;
                    if (this.unexec_substi_security_asset_5_is_set)
                        this.unexec_substi_security_asset_5_is_modified = true;
                    this.unexec_substi_security_asset_5_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_contract_amount_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_contract_amount_0' must be Double: '"+value+"' is not." );
                    this.unexec_contract_amount_0 = (Double) value;
                    if (this.unexec_contract_amount_0_is_set)
                        this.unexec_contract_amount_0_is_modified = true;
                    this.unexec_contract_amount_0_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_contract_amount_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_contract_amount_1' must be Double: '"+value+"' is not." );
                    this.unexec_contract_amount_1 = (Double) value;
                    if (this.unexec_contract_amount_1_is_set)
                        this.unexec_contract_amount_1_is_modified = true;
                    this.unexec_contract_amount_1_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_contract_amount_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_contract_amount_2' must be Double: '"+value+"' is not." );
                    this.unexec_contract_amount_2 = (Double) value;
                    if (this.unexec_contract_amount_2_is_set)
                        this.unexec_contract_amount_2_is_modified = true;
                    this.unexec_contract_amount_2_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_contract_amount_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_contract_amount_3' must be Double: '"+value+"' is not." );
                    this.unexec_contract_amount_3 = (Double) value;
                    if (this.unexec_contract_amount_3_is_set)
                        this.unexec_contract_amount_3_is_modified = true;
                    this.unexec_contract_amount_3_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_contract_amount_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_contract_amount_4' must be Double: '"+value+"' is not." );
                    this.unexec_contract_amount_4 = (Double) value;
                    if (this.unexec_contract_amount_4_is_set)
                        this.unexec_contract_amount_4_is_modified = true;
                    this.unexec_contract_amount_4_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_contract_amount_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_contract_amount_5' must be Double: '"+value+"' is not." );
                    this.unexec_contract_amount_5 = (Double) value;
                    if (this.unexec_contract_amount_5_is_set)
                        this.unexec_contract_amount_5_is_modified = true;
                    this.unexec_contract_amount_5_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_margin_deposit_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_margin_deposit_0' must be Double: '"+value+"' is not." );
                    this.unexec_margin_deposit_0 = (Double) value;
                    if (this.unexec_margin_deposit_0_is_set)
                        this.unexec_margin_deposit_0_is_modified = true;
                    this.unexec_margin_deposit_0_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_margin_deposit_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_margin_deposit_1' must be Double: '"+value+"' is not." );
                    this.unexec_margin_deposit_1 = (Double) value;
                    if (this.unexec_margin_deposit_1_is_set)
                        this.unexec_margin_deposit_1_is_modified = true;
                    this.unexec_margin_deposit_1_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_margin_deposit_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_margin_deposit_2' must be Double: '"+value+"' is not." );
                    this.unexec_margin_deposit_2 = (Double) value;
                    if (this.unexec_margin_deposit_2_is_set)
                        this.unexec_margin_deposit_2_is_modified = true;
                    this.unexec_margin_deposit_2_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_margin_deposit_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_margin_deposit_3' must be Double: '"+value+"' is not." );
                    this.unexec_margin_deposit_3 = (Double) value;
                    if (this.unexec_margin_deposit_3_is_set)
                        this.unexec_margin_deposit_3_is_modified = true;
                    this.unexec_margin_deposit_3_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_margin_deposit_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_margin_deposit_4' must be Double: '"+value+"' is not." );
                    this.unexec_margin_deposit_4 = (Double) value;
                    if (this.unexec_margin_deposit_4_is_set)
                        this.unexec_margin_deposit_4_is_modified = true;
                    this.unexec_margin_deposit_4_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_margin_deposit_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_margin_deposit_5' must be Double: '"+value+"' is not." );
                    this.unexec_margin_deposit_5 = (Double) value;
                    if (this.unexec_margin_deposit_5_is_set)
                        this.unexec_margin_deposit_5_is_modified = true;
                    this.unexec_margin_deposit_5_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_cash_margin_deposit_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_cash_margin_deposit_0' must be Double: '"+value+"' is not." );
                    this.unexec_cash_margin_deposit_0 = (Double) value;
                    if (this.unexec_cash_margin_deposit_0_is_set)
                        this.unexec_cash_margin_deposit_0_is_modified = true;
                    this.unexec_cash_margin_deposit_0_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_cash_margin_deposit_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_cash_margin_deposit_1' must be Double: '"+value+"' is not." );
                    this.unexec_cash_margin_deposit_1 = (Double) value;
                    if (this.unexec_cash_margin_deposit_1_is_set)
                        this.unexec_cash_margin_deposit_1_is_modified = true;
                    this.unexec_cash_margin_deposit_1_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_cash_margin_deposit_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_cash_margin_deposit_2' must be Double: '"+value+"' is not." );
                    this.unexec_cash_margin_deposit_2 = (Double) value;
                    if (this.unexec_cash_margin_deposit_2_is_set)
                        this.unexec_cash_margin_deposit_2_is_modified = true;
                    this.unexec_cash_margin_deposit_2_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_cash_margin_deposit_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_cash_margin_deposit_3' must be Double: '"+value+"' is not." );
                    this.unexec_cash_margin_deposit_3 = (Double) value;
                    if (this.unexec_cash_margin_deposit_3_is_set)
                        this.unexec_cash_margin_deposit_3_is_modified = true;
                    this.unexec_cash_margin_deposit_3_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_cash_margin_deposit_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_cash_margin_deposit_4' must be Double: '"+value+"' is not." );
                    this.unexec_cash_margin_deposit_4 = (Double) value;
                    if (this.unexec_cash_margin_deposit_4_is_set)
                        this.unexec_cash_margin_deposit_4_is_modified = true;
                    this.unexec_cash_margin_deposit_4_is_set = true;
                    return;
                }
                else if ( name.equals("unexec_cash_margin_deposit_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'unexec_cash_margin_deposit_5' must be Double: '"+value+"' is not." );
                    this.unexec_cash_margin_deposit_5 = (Double) value;
                    if (this.unexec_cash_margin_deposit_5_is_set)
                        this.unexec_cash_margin_deposit_5_is_modified = true;
                    this.unexec_cash_margin_deposit_5_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
