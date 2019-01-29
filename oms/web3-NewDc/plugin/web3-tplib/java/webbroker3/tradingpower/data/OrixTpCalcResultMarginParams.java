head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.29.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	OrixTpCalcResultMarginParams.java;


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
 * orix_tp_calc_result_marginテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link OrixTpCalcResultMarginRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link OrixTpCalcResultMarginParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link OrixTpCalcResultMarginParams}が{@@link OrixTpCalcResultMarginRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OrixTpCalcResultMarginPK 
 * @@see OrixTpCalcResultMarginRow 
 */
public class OrixTpCalcResultMarginParams extends Params implements OrixTpCalcResultMarginRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "orix_tp_calc_result_margin";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = OrixTpCalcResultMarginRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return OrixTpCalcResultMarginRow.TYPE;
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
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>work_date</em>カラムの値 
   */
  public  String  work_date;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  /** 
   * <em>asset_evaluation_div</em>カラムの値 
   */
  public  String  asset_evaluation_div;

  /** 
   * <em>account_balance_0</em>カラムの値 
   */
  public  Long  account_balance_0;

  /** 
   * <em>account_balance_1</em>カラムの値 
   */
  public  Long  account_balance_1;

  /** 
   * <em>account_balance_2</em>カラムの値 
   */
  public  Long  account_balance_2;

  /** 
   * <em>account_balance_3</em>カラムの値 
   */
  public  Long  account_balance_3;

  /** 
   * <em>account_balance_4</em>カラムの値 
   */
  public  Long  account_balance_4;

  /** 
   * <em>today_unexecuted_amount_1</em>カラムの値 
   */
  public  Long  today_unexecuted_amount_1;

  /** 
   * <em>today_unexecuted_amount_2</em>カラムの値 
   */
  public  Long  today_unexecuted_amount_2;

  /** 
   * <em>today_unexecuted_amount_3</em>カラムの値 
   */
  public  Long  today_unexecuted_amount_3;

  /** 
   * <em>today_unexecuted_amount_4</em>カラムの値 
   */
  public  Long  today_unexecuted_amount_4;

  /** 
   * <em>day_trade_restraint_0</em>カラムの値 
   */
  public  Long  day_trade_restraint_0;

  /** 
   * <em>day_trade_restraint_1</em>カラムの値 
   */
  public  Long  day_trade_restraint_1;

  /** 
   * <em>day_trade_restraint_2</em>カラムの値 
   */
  public  Long  day_trade_restraint_2;

  /** 
   * <em>day_trade_restraint_3</em>カラムの値 
   */
  public  Long  day_trade_restraint_3;

  /** 
   * <em>day_trade_restraint_4</em>カラムの値 
   */
  public  Long  day_trade_restraint_4;

  /** 
   * <em>other_restraint_0</em>カラムの値 
   */
  public  Long  other_restraint_0;

  /** 
   * <em>other_restraint_1</em>カラムの値 
   */
  public  Long  other_restraint_1;

  /** 
   * <em>other_restraint_2</em>カラムの値 
   */
  public  Long  other_restraint_2;

  /** 
   * <em>other_restraint_3</em>カラムの値 
   */
  public  Long  other_restraint_3;

  /** 
   * <em>other_restraint_4</em>カラムの値 
   */
  public  Long  other_restraint_4;

  /** 
   * <em>margin_account_balance_0</em>カラムの値 
   */
  public  Long  margin_account_balance_0;

  /** 
   * <em>margin_account_balance_1</em>カラムの値 
   */
  public  Long  margin_account_balance_1;

  /** 
   * <em>margin_account_balance_2</em>カラムの値 
   */
  public  Long  margin_account_balance_2;

  /** 
   * <em>margin_account_balance_3</em>カラムの値 
   */
  public  Long  margin_account_balance_3;

  /** 
   * <em>margin_account_balance_4</em>カラムの値 
   */
  public  Long  margin_account_balance_4;

  /** 
   * <em>substitute_security_asset_0</em>カラムの値 
   */
  public  Long  substitute_security_asset_0;

  /** 
   * <em>substitute_security_asset_1</em>カラムの値 
   */
  public  Long  substitute_security_asset_1;

  /** 
   * <em>substitute_security_asset_2</em>カラムの値 
   */
  public  Long  substitute_security_asset_2;

  /** 
   * <em>substitute_security_asset_3</em>カラムの値 
   */
  public  Long  substitute_security_asset_3;

  /** 
   * <em>substitute_security_asset_4</em>カラムの値 
   */
  public  Long  substitute_security_asset_4;

  /** 
   * <em>contract_asset_profit_loss</em>カラムの値 
   */
  public  Long  contract_asset_profit_loss;

  /** 
   * <em>contract_total_cost</em>カラムの値 
   */
  public  Long  contract_total_cost;

  /** 
   * <em>undeli_contract_loss_0</em>カラムの値 
   */
  public  Long  undeli_contract_loss_0;

  /** 
   * <em>undeli_contract_loss_1</em>カラムの値 
   */
  public  Long  undeli_contract_loss_1;

  /** 
   * <em>undeli_contract_loss_2</em>カラムの値 
   */
  public  Long  undeli_contract_loss_2;

  /** 
   * <em>undeli_contract_loss_3</em>カラムの値 
   */
  public  Long  undeli_contract_loss_3;

  /** 
   * <em>today_repay_contract_pre_asset</em>カラムの値 
   */
  public  Long  today_repay_contract_pre_asset;

  /** 
   * <em>contract_amount_day_repay_0</em>カラムの値 
   */
  public  Long  contract_amount_day_repay_0;

  /** 
   * <em>contract_amount_day_repay_1</em>カラムの値 
   */
  public  Long  contract_amount_day_repay_1;

  /** 
   * <em>contract_amount_day_repay_2</em>カラムの値 
   */
  public  Long  contract_amount_day_repay_2;

  /** 
   * <em>contract_amount_day_repay_3</em>カラムの値 
   */
  public  Long  contract_amount_day_repay_3;

  /** 
   * <em>contract_amount_day_repay_4</em>カラムの値 
   */
  public  Long  contract_amount_day_repay_4;

  /** 
   * <em>margin_power_0</em>カラムの値 
   */
  public  Long  margin_power_0;

  /** 
   * <em>margin_power_1</em>カラムの値 
   */
  public  Long  margin_power_1;

  /** 
   * <em>margin_power_2</em>カラムの値 
   */
  public  Long  margin_power_2;

  /** 
   * <em>margin_power_3</em>カラムの値 
   */
  public  Long  margin_power_3;

  /** 
   * <em>margin_power_4</em>カラムの値 
   */
  public  Long  margin_power_4;

  /** 
   * <em>margin_trading_power_1</em>カラムの値 
   */
  public  Long  margin_trading_power_1;

  /** 
   * <em>margin_trading_power_2</em>カラムの値 
   */
  public  Long  margin_trading_power_2;

  /** 
   * <em>margin_trading_power_3</em>カラムの値 
   */
  public  Long  margin_trading_power_3;

  /** 
   * <em>margin_trading_power_4</em>カラムの値 
   */
  public  Long  margin_trading_power_4;

  /** 
   * <em>margin_deposit_rate_0</em>カラムの値 
   */
  public  Double  margin_deposit_rate_0;

  /** 
   * <em>margin_deposit_rate_1</em>カラムの値 
   */
  public  Double  margin_deposit_rate_1;

  /** 
   * <em>margin_deposit_rate_2</em>カラムの値 
   */
  public  Double  margin_deposit_rate_2;

  /** 
   * <em>margin_deposit_rate_3</em>カラムの値 
   */
  public  Double  margin_deposit_rate_3;

  /** 
   * <em>margin_deposit_rate_4</em>カラムの値 
   */
  public  Double  margin_deposit_rate_4;

  /** 
   * <em>act_rec_trading_power_3</em>カラムの値 
   */
  public  Long  act_rec_trading_power_3;

  /** 
   * <em>act_rec_trading_power_4</em>カラムの値 
   */
  public  Long  act_rec_trading_power_4;

  /** 
   * <em>act_rec_trading_power_4_dash</em>カラムの値 
   */
  public  Long  act_rec_trading_power_4_dash;

  /** 
   * <em>equity_trading_power_3</em>カラムの値 
   */
  public  Long  equity_trading_power_3;

  /** 
   * <em>equity_trading_power_4</em>カラムの値 
   */
  public  Long  equity_trading_power_4;

  /** 
   * <em>equity_trading_power_4_dash</em>カラムの値 
   */
  public  Long  equity_trading_power_4_dash;

  /** 
   * <em>undeli_contract_amount_0</em>カラムの値 
   */
  public  Long  undeli_contract_amount_0;

  /** 
   * <em>undeli_contract_amount_1</em>カラムの値 
   */
  public  Long  undeli_contract_amount_1;

  /** 
   * <em>undeli_contract_amount_2</em>カラムの値 
   */
  public  Long  undeli_contract_amount_2;

  /** 
   * <em>undeli_contract_amount_3</em>カラムの値 
   */
  public  Long  undeli_contract_amount_3;

  /** 
   * <em>margin_draw_power_0</em>カラムの値 
   */
  public  Long  margin_draw_power_0;

  /** 
   * <em>margin_draw_power_1</em>カラムの値 
   */
  public  Long  margin_draw_power_1;

  /** 
   * <em>margin_draw_power_2</em>カラムの値 
   */
  public  Long  margin_draw_power_2;

  /** 
   * <em>margin_draw_power_3</em>カラムの値 
   */
  public  Long  margin_draw_power_3;

  /** 
   * <em>margin_draw_power_4</em>カラムの値 
   */
  public  Long  margin_draw_power_4;

  /** 
   * <em>other_trading_power_1</em>カラムの値 
   */
  public  Long  other_trading_power_1;

  /** 
   * <em>other_trading_power_2</em>カラムの値 
   */
  public  Long  other_trading_power_2;

  /** 
   * <em>other_trading_power_3</em>カラムの値 
   */
  public  Long  other_trading_power_3;

  /** 
   * <em>other_trading_power_4</em>カラムの値 
   */
  public  Long  other_trading_power_4;

  /** 
   * <em>demandamount0</em>カラムの値 
   */
  public  Long  demandamount0;

  /** 
   * <em>demandamount1</em>カラムの値 
   */
  public  Long  demandamount1;

  /** 
   * <em>demandamount2</em>カラムの値 
   */
  public  Long  demandamount2;

  /** 
   * <em>demandamount3</em>カラムの値 
   */
  public  Long  demandamount3;

  /** 
   * <em>demandamount4</em>カラムの値 
   */
  public  Long  demandamount4;

  /** 
   * <em>payment_amount_designate0</em>カラムの値 
   */
  public  Long  payment_amount_designate0;

  /** 
   * <em>payment_amount_designate1</em>カラムの値 
   */
  public  Long  payment_amount_designate1;

  /** 
   * <em>payment_amount_designate2</em>カラムの値 
   */
  public  Long  payment_amount_designate2;

  /** 
   * <em>margin_sec_product_code</em>カラムの値 
   */
  public  String  margin_sec_product_code;

  /** 
   * <em>margin_sec_rate</em>カラムの値 
   */
  public  Double  margin_sec_rate;

  /** 
   * <em>equity_asset_executed</em>カラムの値 
   */
  public  Long  equity_asset_executed;

  /** 
   * <em>ruito_asset_executed</em>カラムの値 
   */
  public  Long  ruito_asset_executed;

  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値 
   */
  public  Long  mutual_fund_asset_executed;

  /** 
   * <em>bond_asset_executed</em>カラムの値 
   */
  public  Long  bond_asset_executed;

  /** 
   * <em>trading_stop</em>カラムの値 
   */
  public  String  trading_stop;

  /** 
   * <em>margin_open_position_stop</em>カラムの値 
   */
  public  String  margin_open_position_stop;

  /** 
   * <em>payment_stop</em>カラムの値 
   */
  public  String  payment_stop;

  /** 
   * <em>other_trading_stop</em>カラムの値 
   */
  public  String  other_trading_stop;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean calc_result_margin_id_is_set = false;
  private boolean calc_result_margin_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean work_date_is_set = false;
  private boolean work_date_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean asset_evaluation_div_is_set = false;
  private boolean asset_evaluation_div_is_modified = false;
  private boolean account_balance_0_is_set = false;
  private boolean account_balance_0_is_modified = false;
  private boolean account_balance_1_is_set = false;
  private boolean account_balance_1_is_modified = false;
  private boolean account_balance_2_is_set = false;
  private boolean account_balance_2_is_modified = false;
  private boolean account_balance_3_is_set = false;
  private boolean account_balance_3_is_modified = false;
  private boolean account_balance_4_is_set = false;
  private boolean account_balance_4_is_modified = false;
  private boolean today_unexecuted_amount_1_is_set = false;
  private boolean today_unexecuted_amount_1_is_modified = false;
  private boolean today_unexecuted_amount_2_is_set = false;
  private boolean today_unexecuted_amount_2_is_modified = false;
  private boolean today_unexecuted_amount_3_is_set = false;
  private boolean today_unexecuted_amount_3_is_modified = false;
  private boolean today_unexecuted_amount_4_is_set = false;
  private boolean today_unexecuted_amount_4_is_modified = false;
  private boolean day_trade_restraint_0_is_set = false;
  private boolean day_trade_restraint_0_is_modified = false;
  private boolean day_trade_restraint_1_is_set = false;
  private boolean day_trade_restraint_1_is_modified = false;
  private boolean day_trade_restraint_2_is_set = false;
  private boolean day_trade_restraint_2_is_modified = false;
  private boolean day_trade_restraint_3_is_set = false;
  private boolean day_trade_restraint_3_is_modified = false;
  private boolean day_trade_restraint_4_is_set = false;
  private boolean day_trade_restraint_4_is_modified = false;
  private boolean other_restraint_0_is_set = false;
  private boolean other_restraint_0_is_modified = false;
  private boolean other_restraint_1_is_set = false;
  private boolean other_restraint_1_is_modified = false;
  private boolean other_restraint_2_is_set = false;
  private boolean other_restraint_2_is_modified = false;
  private boolean other_restraint_3_is_set = false;
  private boolean other_restraint_3_is_modified = false;
  private boolean other_restraint_4_is_set = false;
  private boolean other_restraint_4_is_modified = false;
  private boolean margin_account_balance_0_is_set = false;
  private boolean margin_account_balance_0_is_modified = false;
  private boolean margin_account_balance_1_is_set = false;
  private boolean margin_account_balance_1_is_modified = false;
  private boolean margin_account_balance_2_is_set = false;
  private boolean margin_account_balance_2_is_modified = false;
  private boolean margin_account_balance_3_is_set = false;
  private boolean margin_account_balance_3_is_modified = false;
  private boolean margin_account_balance_4_is_set = false;
  private boolean margin_account_balance_4_is_modified = false;
  private boolean substitute_security_asset_0_is_set = false;
  private boolean substitute_security_asset_0_is_modified = false;
  private boolean substitute_security_asset_1_is_set = false;
  private boolean substitute_security_asset_1_is_modified = false;
  private boolean substitute_security_asset_2_is_set = false;
  private boolean substitute_security_asset_2_is_modified = false;
  private boolean substitute_security_asset_3_is_set = false;
  private boolean substitute_security_asset_3_is_modified = false;
  private boolean substitute_security_asset_4_is_set = false;
  private boolean substitute_security_asset_4_is_modified = false;
  private boolean contract_asset_profit_loss_is_set = false;
  private boolean contract_asset_profit_loss_is_modified = false;
  private boolean contract_total_cost_is_set = false;
  private boolean contract_total_cost_is_modified = false;
  private boolean undeli_contract_loss_0_is_set = false;
  private boolean undeli_contract_loss_0_is_modified = false;
  private boolean undeli_contract_loss_1_is_set = false;
  private boolean undeli_contract_loss_1_is_modified = false;
  private boolean undeli_contract_loss_2_is_set = false;
  private boolean undeli_contract_loss_2_is_modified = false;
  private boolean undeli_contract_loss_3_is_set = false;
  private boolean undeli_contract_loss_3_is_modified = false;
  private boolean today_repay_contract_pre_asset_is_set = false;
  private boolean today_repay_contract_pre_asset_is_modified = false;
  private boolean contract_amount_day_repay_0_is_set = false;
  private boolean contract_amount_day_repay_0_is_modified = false;
  private boolean contract_amount_day_repay_1_is_set = false;
  private boolean contract_amount_day_repay_1_is_modified = false;
  private boolean contract_amount_day_repay_2_is_set = false;
  private boolean contract_amount_day_repay_2_is_modified = false;
  private boolean contract_amount_day_repay_3_is_set = false;
  private boolean contract_amount_day_repay_3_is_modified = false;
  private boolean contract_amount_day_repay_4_is_set = false;
  private boolean contract_amount_day_repay_4_is_modified = false;
  private boolean margin_power_0_is_set = false;
  private boolean margin_power_0_is_modified = false;
  private boolean margin_power_1_is_set = false;
  private boolean margin_power_1_is_modified = false;
  private boolean margin_power_2_is_set = false;
  private boolean margin_power_2_is_modified = false;
  private boolean margin_power_3_is_set = false;
  private boolean margin_power_3_is_modified = false;
  private boolean margin_power_4_is_set = false;
  private boolean margin_power_4_is_modified = false;
  private boolean margin_trading_power_1_is_set = false;
  private boolean margin_trading_power_1_is_modified = false;
  private boolean margin_trading_power_2_is_set = false;
  private boolean margin_trading_power_2_is_modified = false;
  private boolean margin_trading_power_3_is_set = false;
  private boolean margin_trading_power_3_is_modified = false;
  private boolean margin_trading_power_4_is_set = false;
  private boolean margin_trading_power_4_is_modified = false;
  private boolean margin_deposit_rate_0_is_set = false;
  private boolean margin_deposit_rate_0_is_modified = false;
  private boolean margin_deposit_rate_1_is_set = false;
  private boolean margin_deposit_rate_1_is_modified = false;
  private boolean margin_deposit_rate_2_is_set = false;
  private boolean margin_deposit_rate_2_is_modified = false;
  private boolean margin_deposit_rate_3_is_set = false;
  private boolean margin_deposit_rate_3_is_modified = false;
  private boolean margin_deposit_rate_4_is_set = false;
  private boolean margin_deposit_rate_4_is_modified = false;
  private boolean act_rec_trading_power_3_is_set = false;
  private boolean act_rec_trading_power_3_is_modified = false;
  private boolean act_rec_trading_power_4_is_set = false;
  private boolean act_rec_trading_power_4_is_modified = false;
  private boolean act_rec_trading_power_4_dash_is_set = false;
  private boolean act_rec_trading_power_4_dash_is_modified = false;
  private boolean equity_trading_power_3_is_set = false;
  private boolean equity_trading_power_3_is_modified = false;
  private boolean equity_trading_power_4_is_set = false;
  private boolean equity_trading_power_4_is_modified = false;
  private boolean equity_trading_power_4_dash_is_set = false;
  private boolean equity_trading_power_4_dash_is_modified = false;
  private boolean undeli_contract_amount_0_is_set = false;
  private boolean undeli_contract_amount_0_is_modified = false;
  private boolean undeli_contract_amount_1_is_set = false;
  private boolean undeli_contract_amount_1_is_modified = false;
  private boolean undeli_contract_amount_2_is_set = false;
  private boolean undeli_contract_amount_2_is_modified = false;
  private boolean undeli_contract_amount_3_is_set = false;
  private boolean undeli_contract_amount_3_is_modified = false;
  private boolean margin_draw_power_0_is_set = false;
  private boolean margin_draw_power_0_is_modified = false;
  private boolean margin_draw_power_1_is_set = false;
  private boolean margin_draw_power_1_is_modified = false;
  private boolean margin_draw_power_2_is_set = false;
  private boolean margin_draw_power_2_is_modified = false;
  private boolean margin_draw_power_3_is_set = false;
  private boolean margin_draw_power_3_is_modified = false;
  private boolean margin_draw_power_4_is_set = false;
  private boolean margin_draw_power_4_is_modified = false;
  private boolean other_trading_power_1_is_set = false;
  private boolean other_trading_power_1_is_modified = false;
  private boolean other_trading_power_2_is_set = false;
  private boolean other_trading_power_2_is_modified = false;
  private boolean other_trading_power_3_is_set = false;
  private boolean other_trading_power_3_is_modified = false;
  private boolean other_trading_power_4_is_set = false;
  private boolean other_trading_power_4_is_modified = false;
  private boolean demandamount0_is_set = false;
  private boolean demandamount0_is_modified = false;
  private boolean demandamount1_is_set = false;
  private boolean demandamount1_is_modified = false;
  private boolean demandamount2_is_set = false;
  private boolean demandamount2_is_modified = false;
  private boolean demandamount3_is_set = false;
  private boolean demandamount3_is_modified = false;
  private boolean demandamount4_is_set = false;
  private boolean demandamount4_is_modified = false;
  private boolean payment_amount_designate0_is_set = false;
  private boolean payment_amount_designate0_is_modified = false;
  private boolean payment_amount_designate1_is_set = false;
  private boolean payment_amount_designate1_is_modified = false;
  private boolean payment_amount_designate2_is_set = false;
  private boolean payment_amount_designate2_is_modified = false;
  private boolean margin_sec_product_code_is_set = false;
  private boolean margin_sec_product_code_is_modified = false;
  private boolean margin_sec_rate_is_set = false;
  private boolean margin_sec_rate_is_modified = false;
  private boolean equity_asset_executed_is_set = false;
  private boolean equity_asset_executed_is_modified = false;
  private boolean ruito_asset_executed_is_set = false;
  private boolean ruito_asset_executed_is_modified = false;
  private boolean mutual_fund_asset_executed_is_set = false;
  private boolean mutual_fund_asset_executed_is_modified = false;
  private boolean bond_asset_executed_is_set = false;
  private boolean bond_asset_executed_is_modified = false;
  private boolean trading_stop_is_set = false;
  private boolean trading_stop_is_modified = false;
  private boolean margin_open_position_stop_is_set = false;
  private boolean margin_open_position_stop_is_modified = false;
  private boolean payment_stop_is_set = false;
  private boolean payment_stop_is_modified = false;
  private boolean other_trading_stop_is_set = false;
  private boolean other_trading_stop_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "calc_result_margin_id=" + calc_result_margin_id
      + "," + "account_id=" +account_id
      + "," + "institution_code=" +institution_code
      + "," + "work_date=" +work_date
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "asset_evaluation_div=" +asset_evaluation_div
      + "," + "account_balance_0=" +account_balance_0
      + "," + "account_balance_1=" +account_balance_1
      + "," + "account_balance_2=" +account_balance_2
      + "," + "account_balance_3=" +account_balance_3
      + "," + "account_balance_4=" +account_balance_4
      + "," + "today_unexecuted_amount_1=" +today_unexecuted_amount_1
      + "," + "today_unexecuted_amount_2=" +today_unexecuted_amount_2
      + "," + "today_unexecuted_amount_3=" +today_unexecuted_amount_3
      + "," + "today_unexecuted_amount_4=" +today_unexecuted_amount_4
      + "," + "day_trade_restraint_0=" +day_trade_restraint_0
      + "," + "day_trade_restraint_1=" +day_trade_restraint_1
      + "," + "day_trade_restraint_2=" +day_trade_restraint_2
      + "," + "day_trade_restraint_3=" +day_trade_restraint_3
      + "," + "day_trade_restraint_4=" +day_trade_restraint_4
      + "," + "other_restraint_0=" +other_restraint_0
      + "," + "other_restraint_1=" +other_restraint_1
      + "," + "other_restraint_2=" +other_restraint_2
      + "," + "other_restraint_3=" +other_restraint_3
      + "," + "other_restraint_4=" +other_restraint_4
      + "," + "margin_account_balance_0=" +margin_account_balance_0
      + "," + "margin_account_balance_1=" +margin_account_balance_1
      + "," + "margin_account_balance_2=" +margin_account_balance_2
      + "," + "margin_account_balance_3=" +margin_account_balance_3
      + "," + "margin_account_balance_4=" +margin_account_balance_4
      + "," + "substitute_security_asset_0=" +substitute_security_asset_0
      + "," + "substitute_security_asset_1=" +substitute_security_asset_1
      + "," + "substitute_security_asset_2=" +substitute_security_asset_2
      + "," + "substitute_security_asset_3=" +substitute_security_asset_3
      + "," + "substitute_security_asset_4=" +substitute_security_asset_4
      + "," + "contract_asset_profit_loss=" +contract_asset_profit_loss
      + "," + "contract_total_cost=" +contract_total_cost
      + "," + "undeli_contract_loss_0=" +undeli_contract_loss_0
      + "," + "undeli_contract_loss_1=" +undeli_contract_loss_1
      + "," + "undeli_contract_loss_2=" +undeli_contract_loss_2
      + "," + "undeli_contract_loss_3=" +undeli_contract_loss_3
      + "," + "today_repay_contract_pre_asset=" +today_repay_contract_pre_asset
      + "," + "contract_amount_day_repay_0=" +contract_amount_day_repay_0
      + "," + "contract_amount_day_repay_1=" +contract_amount_day_repay_1
      + "," + "contract_amount_day_repay_2=" +contract_amount_day_repay_2
      + "," + "contract_amount_day_repay_3=" +contract_amount_day_repay_3
      + "," + "contract_amount_day_repay_4=" +contract_amount_day_repay_4
      + "," + "margin_power_0=" +margin_power_0
      + "," + "margin_power_1=" +margin_power_1
      + "," + "margin_power_2=" +margin_power_2
      + "," + "margin_power_3=" +margin_power_3
      + "," + "margin_power_4=" +margin_power_4
      + "," + "margin_trading_power_1=" +margin_trading_power_1
      + "," + "margin_trading_power_2=" +margin_trading_power_2
      + "," + "margin_trading_power_3=" +margin_trading_power_3
      + "," + "margin_trading_power_4=" +margin_trading_power_4
      + "," + "margin_deposit_rate_0=" +margin_deposit_rate_0
      + "," + "margin_deposit_rate_1=" +margin_deposit_rate_1
      + "," + "margin_deposit_rate_2=" +margin_deposit_rate_2
      + "," + "margin_deposit_rate_3=" +margin_deposit_rate_3
      + "," + "margin_deposit_rate_4=" +margin_deposit_rate_4
      + "," + "act_rec_trading_power_3=" +act_rec_trading_power_3
      + "," + "act_rec_trading_power_4=" +act_rec_trading_power_4
      + "," + "act_rec_trading_power_4_dash=" +act_rec_trading_power_4_dash
      + "," + "equity_trading_power_3=" +equity_trading_power_3
      + "," + "equity_trading_power_4=" +equity_trading_power_4
      + "," + "equity_trading_power_4_dash=" +equity_trading_power_4_dash
      + "," + "undeli_contract_amount_0=" +undeli_contract_amount_0
      + "," + "undeli_contract_amount_1=" +undeli_contract_amount_1
      + "," + "undeli_contract_amount_2=" +undeli_contract_amount_2
      + "," + "undeli_contract_amount_3=" +undeli_contract_amount_3
      + "," + "margin_draw_power_0=" +margin_draw_power_0
      + "," + "margin_draw_power_1=" +margin_draw_power_1
      + "," + "margin_draw_power_2=" +margin_draw_power_2
      + "," + "margin_draw_power_3=" +margin_draw_power_3
      + "," + "margin_draw_power_4=" +margin_draw_power_4
      + "," + "other_trading_power_1=" +other_trading_power_1
      + "," + "other_trading_power_2=" +other_trading_power_2
      + "," + "other_trading_power_3=" +other_trading_power_3
      + "," + "other_trading_power_4=" +other_trading_power_4
      + "," + "demandamount0=" +demandamount0
      + "," + "demandamount1=" +demandamount1
      + "," + "demandamount2=" +demandamount2
      + "," + "demandamount3=" +demandamount3
      + "," + "demandamount4=" +demandamount4
      + "," + "payment_amount_designate0=" +payment_amount_designate0
      + "," + "payment_amount_designate1=" +payment_amount_designate1
      + "," + "payment_amount_designate2=" +payment_amount_designate2
      + "," + "margin_sec_product_code=" +margin_sec_product_code
      + "," + "margin_sec_rate=" +margin_sec_rate
      + "," + "equity_asset_executed=" +equity_asset_executed
      + "," + "ruito_asset_executed=" +ruito_asset_executed
      + "," + "mutual_fund_asset_executed=" +mutual_fund_asset_executed
      + "," + "bond_asset_executed=" +bond_asset_executed
      + "," + "trading_stop=" +trading_stop
      + "," + "margin_open_position_stop=" +margin_open_position_stop
      + "," + "payment_stop=" +payment_stop
      + "," + "other_trading_stop=" +other_trading_stop
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のOrixTpCalcResultMarginParamsオブジェクトを作成します。 
   */
  public OrixTpCalcResultMarginParams() {
    calc_result_margin_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のOrixTpCalcResultMarginRowオブジェクトの値を利用してOrixTpCalcResultMarginParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するOrixTpCalcResultMarginRowオブジェクト 
   */
  public OrixTpCalcResultMarginParams( OrixTpCalcResultMarginRow p_row )
  {
    calc_result_margin_id = p_row.getCalcResultMarginId();
    calc_result_margin_id_is_set = p_row.getCalcResultMarginIdIsSet();
    calc_result_margin_id_is_modified = p_row.getCalcResultMarginIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    work_date = p_row.getWorkDate();
    work_date_is_set = p_row.getWorkDateIsSet();
    work_date_is_modified = p_row.getWorkDateIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    asset_evaluation_div = p_row.getAssetEvaluationDiv();
    asset_evaluation_div_is_set = p_row.getAssetEvaluationDivIsSet();
    asset_evaluation_div_is_modified = p_row.getAssetEvaluationDivIsModified();
    if ( !p_row.getAccountBalance0IsNull() )
      account_balance_0 = new Long( p_row.getAccountBalance0() );
    account_balance_0_is_set = p_row.getAccountBalance0IsSet();
    account_balance_0_is_modified = p_row.getAccountBalance0IsModified();
    if ( !p_row.getAccountBalance1IsNull() )
      account_balance_1 = new Long( p_row.getAccountBalance1() );
    account_balance_1_is_set = p_row.getAccountBalance1IsSet();
    account_balance_1_is_modified = p_row.getAccountBalance1IsModified();
    if ( !p_row.getAccountBalance2IsNull() )
      account_balance_2 = new Long( p_row.getAccountBalance2() );
    account_balance_2_is_set = p_row.getAccountBalance2IsSet();
    account_balance_2_is_modified = p_row.getAccountBalance2IsModified();
    if ( !p_row.getAccountBalance3IsNull() )
      account_balance_3 = new Long( p_row.getAccountBalance3() );
    account_balance_3_is_set = p_row.getAccountBalance3IsSet();
    account_balance_3_is_modified = p_row.getAccountBalance3IsModified();
    if ( !p_row.getAccountBalance4IsNull() )
      account_balance_4 = new Long( p_row.getAccountBalance4() );
    account_balance_4_is_set = p_row.getAccountBalance4IsSet();
    account_balance_4_is_modified = p_row.getAccountBalance4IsModified();
    if ( !p_row.getTodayUnexecutedAmount1IsNull() )
      today_unexecuted_amount_1 = new Long( p_row.getTodayUnexecutedAmount1() );
    today_unexecuted_amount_1_is_set = p_row.getTodayUnexecutedAmount1IsSet();
    today_unexecuted_amount_1_is_modified = p_row.getTodayUnexecutedAmount1IsModified();
    if ( !p_row.getTodayUnexecutedAmount2IsNull() )
      today_unexecuted_amount_2 = new Long( p_row.getTodayUnexecutedAmount2() );
    today_unexecuted_amount_2_is_set = p_row.getTodayUnexecutedAmount2IsSet();
    today_unexecuted_amount_2_is_modified = p_row.getTodayUnexecutedAmount2IsModified();
    if ( !p_row.getTodayUnexecutedAmount3IsNull() )
      today_unexecuted_amount_3 = new Long( p_row.getTodayUnexecutedAmount3() );
    today_unexecuted_amount_3_is_set = p_row.getTodayUnexecutedAmount3IsSet();
    today_unexecuted_amount_3_is_modified = p_row.getTodayUnexecutedAmount3IsModified();
    if ( !p_row.getTodayUnexecutedAmount4IsNull() )
      today_unexecuted_amount_4 = new Long( p_row.getTodayUnexecutedAmount4() );
    today_unexecuted_amount_4_is_set = p_row.getTodayUnexecutedAmount4IsSet();
    today_unexecuted_amount_4_is_modified = p_row.getTodayUnexecutedAmount4IsModified();
    if ( !p_row.getDayTradeRestraint0IsNull() )
      day_trade_restraint_0 = new Long( p_row.getDayTradeRestraint0() );
    day_trade_restraint_0_is_set = p_row.getDayTradeRestraint0IsSet();
    day_trade_restraint_0_is_modified = p_row.getDayTradeRestraint0IsModified();
    if ( !p_row.getDayTradeRestraint1IsNull() )
      day_trade_restraint_1 = new Long( p_row.getDayTradeRestraint1() );
    day_trade_restraint_1_is_set = p_row.getDayTradeRestraint1IsSet();
    day_trade_restraint_1_is_modified = p_row.getDayTradeRestraint1IsModified();
    if ( !p_row.getDayTradeRestraint2IsNull() )
      day_trade_restraint_2 = new Long( p_row.getDayTradeRestraint2() );
    day_trade_restraint_2_is_set = p_row.getDayTradeRestraint2IsSet();
    day_trade_restraint_2_is_modified = p_row.getDayTradeRestraint2IsModified();
    if ( !p_row.getDayTradeRestraint3IsNull() )
      day_trade_restraint_3 = new Long( p_row.getDayTradeRestraint3() );
    day_trade_restraint_3_is_set = p_row.getDayTradeRestraint3IsSet();
    day_trade_restraint_3_is_modified = p_row.getDayTradeRestraint3IsModified();
    if ( !p_row.getDayTradeRestraint4IsNull() )
      day_trade_restraint_4 = new Long( p_row.getDayTradeRestraint4() );
    day_trade_restraint_4_is_set = p_row.getDayTradeRestraint4IsSet();
    day_trade_restraint_4_is_modified = p_row.getDayTradeRestraint4IsModified();
    if ( !p_row.getOtherRestraint0IsNull() )
      other_restraint_0 = new Long( p_row.getOtherRestraint0() );
    other_restraint_0_is_set = p_row.getOtherRestraint0IsSet();
    other_restraint_0_is_modified = p_row.getOtherRestraint0IsModified();
    if ( !p_row.getOtherRestraint1IsNull() )
      other_restraint_1 = new Long( p_row.getOtherRestraint1() );
    other_restraint_1_is_set = p_row.getOtherRestraint1IsSet();
    other_restraint_1_is_modified = p_row.getOtherRestraint1IsModified();
    if ( !p_row.getOtherRestraint2IsNull() )
      other_restraint_2 = new Long( p_row.getOtherRestraint2() );
    other_restraint_2_is_set = p_row.getOtherRestraint2IsSet();
    other_restraint_2_is_modified = p_row.getOtherRestraint2IsModified();
    if ( !p_row.getOtherRestraint3IsNull() )
      other_restraint_3 = new Long( p_row.getOtherRestraint3() );
    other_restraint_3_is_set = p_row.getOtherRestraint3IsSet();
    other_restraint_3_is_modified = p_row.getOtherRestraint3IsModified();
    if ( !p_row.getOtherRestraint4IsNull() )
      other_restraint_4 = new Long( p_row.getOtherRestraint4() );
    other_restraint_4_is_set = p_row.getOtherRestraint4IsSet();
    other_restraint_4_is_modified = p_row.getOtherRestraint4IsModified();
    if ( !p_row.getMarginAccountBalance0IsNull() )
      margin_account_balance_0 = new Long( p_row.getMarginAccountBalance0() );
    margin_account_balance_0_is_set = p_row.getMarginAccountBalance0IsSet();
    margin_account_balance_0_is_modified = p_row.getMarginAccountBalance0IsModified();
    if ( !p_row.getMarginAccountBalance1IsNull() )
      margin_account_balance_1 = new Long( p_row.getMarginAccountBalance1() );
    margin_account_balance_1_is_set = p_row.getMarginAccountBalance1IsSet();
    margin_account_balance_1_is_modified = p_row.getMarginAccountBalance1IsModified();
    if ( !p_row.getMarginAccountBalance2IsNull() )
      margin_account_balance_2 = new Long( p_row.getMarginAccountBalance2() );
    margin_account_balance_2_is_set = p_row.getMarginAccountBalance2IsSet();
    margin_account_balance_2_is_modified = p_row.getMarginAccountBalance2IsModified();
    if ( !p_row.getMarginAccountBalance3IsNull() )
      margin_account_balance_3 = new Long( p_row.getMarginAccountBalance3() );
    margin_account_balance_3_is_set = p_row.getMarginAccountBalance3IsSet();
    margin_account_balance_3_is_modified = p_row.getMarginAccountBalance3IsModified();
    if ( !p_row.getMarginAccountBalance4IsNull() )
      margin_account_balance_4 = new Long( p_row.getMarginAccountBalance4() );
    margin_account_balance_4_is_set = p_row.getMarginAccountBalance4IsSet();
    margin_account_balance_4_is_modified = p_row.getMarginAccountBalance4IsModified();
    if ( !p_row.getSubstituteSecurityAsset0IsNull() )
      substitute_security_asset_0 = new Long( p_row.getSubstituteSecurityAsset0() );
    substitute_security_asset_0_is_set = p_row.getSubstituteSecurityAsset0IsSet();
    substitute_security_asset_0_is_modified = p_row.getSubstituteSecurityAsset0IsModified();
    if ( !p_row.getSubstituteSecurityAsset1IsNull() )
      substitute_security_asset_1 = new Long( p_row.getSubstituteSecurityAsset1() );
    substitute_security_asset_1_is_set = p_row.getSubstituteSecurityAsset1IsSet();
    substitute_security_asset_1_is_modified = p_row.getSubstituteSecurityAsset1IsModified();
    if ( !p_row.getSubstituteSecurityAsset2IsNull() )
      substitute_security_asset_2 = new Long( p_row.getSubstituteSecurityAsset2() );
    substitute_security_asset_2_is_set = p_row.getSubstituteSecurityAsset2IsSet();
    substitute_security_asset_2_is_modified = p_row.getSubstituteSecurityAsset2IsModified();
    if ( !p_row.getSubstituteSecurityAsset3IsNull() )
      substitute_security_asset_3 = new Long( p_row.getSubstituteSecurityAsset3() );
    substitute_security_asset_3_is_set = p_row.getSubstituteSecurityAsset3IsSet();
    substitute_security_asset_3_is_modified = p_row.getSubstituteSecurityAsset3IsModified();
    if ( !p_row.getSubstituteSecurityAsset4IsNull() )
      substitute_security_asset_4 = new Long( p_row.getSubstituteSecurityAsset4() );
    substitute_security_asset_4_is_set = p_row.getSubstituteSecurityAsset4IsSet();
    substitute_security_asset_4_is_modified = p_row.getSubstituteSecurityAsset4IsModified();
    if ( !p_row.getContractAssetProfitLossIsNull() )
      contract_asset_profit_loss = new Long( p_row.getContractAssetProfitLoss() );
    contract_asset_profit_loss_is_set = p_row.getContractAssetProfitLossIsSet();
    contract_asset_profit_loss_is_modified = p_row.getContractAssetProfitLossIsModified();
    if ( !p_row.getContractTotalCostIsNull() )
      contract_total_cost = new Long( p_row.getContractTotalCost() );
    contract_total_cost_is_set = p_row.getContractTotalCostIsSet();
    contract_total_cost_is_modified = p_row.getContractTotalCostIsModified();
    if ( !p_row.getUndeliContractLoss0IsNull() )
      undeli_contract_loss_0 = new Long( p_row.getUndeliContractLoss0() );
    undeli_contract_loss_0_is_set = p_row.getUndeliContractLoss0IsSet();
    undeli_contract_loss_0_is_modified = p_row.getUndeliContractLoss0IsModified();
    if ( !p_row.getUndeliContractLoss1IsNull() )
      undeli_contract_loss_1 = new Long( p_row.getUndeliContractLoss1() );
    undeli_contract_loss_1_is_set = p_row.getUndeliContractLoss1IsSet();
    undeli_contract_loss_1_is_modified = p_row.getUndeliContractLoss1IsModified();
    if ( !p_row.getUndeliContractLoss2IsNull() )
      undeli_contract_loss_2 = new Long( p_row.getUndeliContractLoss2() );
    undeli_contract_loss_2_is_set = p_row.getUndeliContractLoss2IsSet();
    undeli_contract_loss_2_is_modified = p_row.getUndeliContractLoss2IsModified();
    if ( !p_row.getUndeliContractLoss3IsNull() )
      undeli_contract_loss_3 = new Long( p_row.getUndeliContractLoss3() );
    undeli_contract_loss_3_is_set = p_row.getUndeliContractLoss3IsSet();
    undeli_contract_loss_3_is_modified = p_row.getUndeliContractLoss3IsModified();
    if ( !p_row.getTodayRepayContractPreAssetIsNull() )
      today_repay_contract_pre_asset = new Long( p_row.getTodayRepayContractPreAsset() );
    today_repay_contract_pre_asset_is_set = p_row.getTodayRepayContractPreAssetIsSet();
    today_repay_contract_pre_asset_is_modified = p_row.getTodayRepayContractPreAssetIsModified();
    if ( !p_row.getContractAmountDayRepay0IsNull() )
      contract_amount_day_repay_0 = new Long( p_row.getContractAmountDayRepay0() );
    contract_amount_day_repay_0_is_set = p_row.getContractAmountDayRepay0IsSet();
    contract_amount_day_repay_0_is_modified = p_row.getContractAmountDayRepay0IsModified();
    if ( !p_row.getContractAmountDayRepay1IsNull() )
      contract_amount_day_repay_1 = new Long( p_row.getContractAmountDayRepay1() );
    contract_amount_day_repay_1_is_set = p_row.getContractAmountDayRepay1IsSet();
    contract_amount_day_repay_1_is_modified = p_row.getContractAmountDayRepay1IsModified();
    if ( !p_row.getContractAmountDayRepay2IsNull() )
      contract_amount_day_repay_2 = new Long( p_row.getContractAmountDayRepay2() );
    contract_amount_day_repay_2_is_set = p_row.getContractAmountDayRepay2IsSet();
    contract_amount_day_repay_2_is_modified = p_row.getContractAmountDayRepay2IsModified();
    if ( !p_row.getContractAmountDayRepay3IsNull() )
      contract_amount_day_repay_3 = new Long( p_row.getContractAmountDayRepay3() );
    contract_amount_day_repay_3_is_set = p_row.getContractAmountDayRepay3IsSet();
    contract_amount_day_repay_3_is_modified = p_row.getContractAmountDayRepay3IsModified();
    if ( !p_row.getContractAmountDayRepay4IsNull() )
      contract_amount_day_repay_4 = new Long( p_row.getContractAmountDayRepay4() );
    contract_amount_day_repay_4_is_set = p_row.getContractAmountDayRepay4IsSet();
    contract_amount_day_repay_4_is_modified = p_row.getContractAmountDayRepay4IsModified();
    if ( !p_row.getMarginPower0IsNull() )
      margin_power_0 = new Long( p_row.getMarginPower0() );
    margin_power_0_is_set = p_row.getMarginPower0IsSet();
    margin_power_0_is_modified = p_row.getMarginPower0IsModified();
    if ( !p_row.getMarginPower1IsNull() )
      margin_power_1 = new Long( p_row.getMarginPower1() );
    margin_power_1_is_set = p_row.getMarginPower1IsSet();
    margin_power_1_is_modified = p_row.getMarginPower1IsModified();
    if ( !p_row.getMarginPower2IsNull() )
      margin_power_2 = new Long( p_row.getMarginPower2() );
    margin_power_2_is_set = p_row.getMarginPower2IsSet();
    margin_power_2_is_modified = p_row.getMarginPower2IsModified();
    if ( !p_row.getMarginPower3IsNull() )
      margin_power_3 = new Long( p_row.getMarginPower3() );
    margin_power_3_is_set = p_row.getMarginPower3IsSet();
    margin_power_3_is_modified = p_row.getMarginPower3IsModified();
    if ( !p_row.getMarginPower4IsNull() )
      margin_power_4 = new Long( p_row.getMarginPower4() );
    margin_power_4_is_set = p_row.getMarginPower4IsSet();
    margin_power_4_is_modified = p_row.getMarginPower4IsModified();
    if ( !p_row.getMarginTradingPower1IsNull() )
      margin_trading_power_1 = new Long( p_row.getMarginTradingPower1() );
    margin_trading_power_1_is_set = p_row.getMarginTradingPower1IsSet();
    margin_trading_power_1_is_modified = p_row.getMarginTradingPower1IsModified();
    if ( !p_row.getMarginTradingPower2IsNull() )
      margin_trading_power_2 = new Long( p_row.getMarginTradingPower2() );
    margin_trading_power_2_is_set = p_row.getMarginTradingPower2IsSet();
    margin_trading_power_2_is_modified = p_row.getMarginTradingPower2IsModified();
    if ( !p_row.getMarginTradingPower3IsNull() )
      margin_trading_power_3 = new Long( p_row.getMarginTradingPower3() );
    margin_trading_power_3_is_set = p_row.getMarginTradingPower3IsSet();
    margin_trading_power_3_is_modified = p_row.getMarginTradingPower3IsModified();
    if ( !p_row.getMarginTradingPower4IsNull() )
      margin_trading_power_4 = new Long( p_row.getMarginTradingPower4() );
    margin_trading_power_4_is_set = p_row.getMarginTradingPower4IsSet();
    margin_trading_power_4_is_modified = p_row.getMarginTradingPower4IsModified();
    if ( !p_row.getMarginDepositRate0IsNull() )
      margin_deposit_rate_0 = new Double( p_row.getMarginDepositRate0() );
    margin_deposit_rate_0_is_set = p_row.getMarginDepositRate0IsSet();
    margin_deposit_rate_0_is_modified = p_row.getMarginDepositRate0IsModified();
    if ( !p_row.getMarginDepositRate1IsNull() )
      margin_deposit_rate_1 = new Double( p_row.getMarginDepositRate1() );
    margin_deposit_rate_1_is_set = p_row.getMarginDepositRate1IsSet();
    margin_deposit_rate_1_is_modified = p_row.getMarginDepositRate1IsModified();
    if ( !p_row.getMarginDepositRate2IsNull() )
      margin_deposit_rate_2 = new Double( p_row.getMarginDepositRate2() );
    margin_deposit_rate_2_is_set = p_row.getMarginDepositRate2IsSet();
    margin_deposit_rate_2_is_modified = p_row.getMarginDepositRate2IsModified();
    if ( !p_row.getMarginDepositRate3IsNull() )
      margin_deposit_rate_3 = new Double( p_row.getMarginDepositRate3() );
    margin_deposit_rate_3_is_set = p_row.getMarginDepositRate3IsSet();
    margin_deposit_rate_3_is_modified = p_row.getMarginDepositRate3IsModified();
    if ( !p_row.getMarginDepositRate4IsNull() )
      margin_deposit_rate_4 = new Double( p_row.getMarginDepositRate4() );
    margin_deposit_rate_4_is_set = p_row.getMarginDepositRate4IsSet();
    margin_deposit_rate_4_is_modified = p_row.getMarginDepositRate4IsModified();
    if ( !p_row.getActRecTradingPower3IsNull() )
      act_rec_trading_power_3 = new Long( p_row.getActRecTradingPower3() );
    act_rec_trading_power_3_is_set = p_row.getActRecTradingPower3IsSet();
    act_rec_trading_power_3_is_modified = p_row.getActRecTradingPower3IsModified();
    if ( !p_row.getActRecTradingPower4IsNull() )
      act_rec_trading_power_4 = new Long( p_row.getActRecTradingPower4() );
    act_rec_trading_power_4_is_set = p_row.getActRecTradingPower4IsSet();
    act_rec_trading_power_4_is_modified = p_row.getActRecTradingPower4IsModified();
    if ( !p_row.getActRecTradingPower4DashIsNull() )
      act_rec_trading_power_4_dash = new Long( p_row.getActRecTradingPower4Dash() );
    act_rec_trading_power_4_dash_is_set = p_row.getActRecTradingPower4DashIsSet();
    act_rec_trading_power_4_dash_is_modified = p_row.getActRecTradingPower4DashIsModified();
    if ( !p_row.getEquityTradingPower3IsNull() )
      equity_trading_power_3 = new Long( p_row.getEquityTradingPower3() );
    equity_trading_power_3_is_set = p_row.getEquityTradingPower3IsSet();
    equity_trading_power_3_is_modified = p_row.getEquityTradingPower3IsModified();
    if ( !p_row.getEquityTradingPower4IsNull() )
      equity_trading_power_4 = new Long( p_row.getEquityTradingPower4() );
    equity_trading_power_4_is_set = p_row.getEquityTradingPower4IsSet();
    equity_trading_power_4_is_modified = p_row.getEquityTradingPower4IsModified();
    if ( !p_row.getEquityTradingPower4DashIsNull() )
      equity_trading_power_4_dash = new Long( p_row.getEquityTradingPower4Dash() );
    equity_trading_power_4_dash_is_set = p_row.getEquityTradingPower4DashIsSet();
    equity_trading_power_4_dash_is_modified = p_row.getEquityTradingPower4DashIsModified();
    if ( !p_row.getUndeliContractAmount0IsNull() )
      undeli_contract_amount_0 = new Long( p_row.getUndeliContractAmount0() );
    undeli_contract_amount_0_is_set = p_row.getUndeliContractAmount0IsSet();
    undeli_contract_amount_0_is_modified = p_row.getUndeliContractAmount0IsModified();
    if ( !p_row.getUndeliContractAmount1IsNull() )
      undeli_contract_amount_1 = new Long( p_row.getUndeliContractAmount1() );
    undeli_contract_amount_1_is_set = p_row.getUndeliContractAmount1IsSet();
    undeli_contract_amount_1_is_modified = p_row.getUndeliContractAmount1IsModified();
    if ( !p_row.getUndeliContractAmount2IsNull() )
      undeli_contract_amount_2 = new Long( p_row.getUndeliContractAmount2() );
    undeli_contract_amount_2_is_set = p_row.getUndeliContractAmount2IsSet();
    undeli_contract_amount_2_is_modified = p_row.getUndeliContractAmount2IsModified();
    if ( !p_row.getUndeliContractAmount3IsNull() )
      undeli_contract_amount_3 = new Long( p_row.getUndeliContractAmount3() );
    undeli_contract_amount_3_is_set = p_row.getUndeliContractAmount3IsSet();
    undeli_contract_amount_3_is_modified = p_row.getUndeliContractAmount3IsModified();
    if ( !p_row.getMarginDrawPower0IsNull() )
      margin_draw_power_0 = new Long( p_row.getMarginDrawPower0() );
    margin_draw_power_0_is_set = p_row.getMarginDrawPower0IsSet();
    margin_draw_power_0_is_modified = p_row.getMarginDrawPower0IsModified();
    if ( !p_row.getMarginDrawPower1IsNull() )
      margin_draw_power_1 = new Long( p_row.getMarginDrawPower1() );
    margin_draw_power_1_is_set = p_row.getMarginDrawPower1IsSet();
    margin_draw_power_1_is_modified = p_row.getMarginDrawPower1IsModified();
    if ( !p_row.getMarginDrawPower2IsNull() )
      margin_draw_power_2 = new Long( p_row.getMarginDrawPower2() );
    margin_draw_power_2_is_set = p_row.getMarginDrawPower2IsSet();
    margin_draw_power_2_is_modified = p_row.getMarginDrawPower2IsModified();
    if ( !p_row.getMarginDrawPower3IsNull() )
      margin_draw_power_3 = new Long( p_row.getMarginDrawPower3() );
    margin_draw_power_3_is_set = p_row.getMarginDrawPower3IsSet();
    margin_draw_power_3_is_modified = p_row.getMarginDrawPower3IsModified();
    if ( !p_row.getMarginDrawPower4IsNull() )
      margin_draw_power_4 = new Long( p_row.getMarginDrawPower4() );
    margin_draw_power_4_is_set = p_row.getMarginDrawPower4IsSet();
    margin_draw_power_4_is_modified = p_row.getMarginDrawPower4IsModified();
    if ( !p_row.getOtherTradingPower1IsNull() )
      other_trading_power_1 = new Long( p_row.getOtherTradingPower1() );
    other_trading_power_1_is_set = p_row.getOtherTradingPower1IsSet();
    other_trading_power_1_is_modified = p_row.getOtherTradingPower1IsModified();
    if ( !p_row.getOtherTradingPower2IsNull() )
      other_trading_power_2 = new Long( p_row.getOtherTradingPower2() );
    other_trading_power_2_is_set = p_row.getOtherTradingPower2IsSet();
    other_trading_power_2_is_modified = p_row.getOtherTradingPower2IsModified();
    if ( !p_row.getOtherTradingPower3IsNull() )
      other_trading_power_3 = new Long( p_row.getOtherTradingPower3() );
    other_trading_power_3_is_set = p_row.getOtherTradingPower3IsSet();
    other_trading_power_3_is_modified = p_row.getOtherTradingPower3IsModified();
    if ( !p_row.getOtherTradingPower4IsNull() )
      other_trading_power_4 = new Long( p_row.getOtherTradingPower4() );
    other_trading_power_4_is_set = p_row.getOtherTradingPower4IsSet();
    other_trading_power_4_is_modified = p_row.getOtherTradingPower4IsModified();
    if ( !p_row.getDemandamount0IsNull() )
      demandamount0 = new Long( p_row.getDemandamount0() );
    demandamount0_is_set = p_row.getDemandamount0IsSet();
    demandamount0_is_modified = p_row.getDemandamount0IsModified();
    if ( !p_row.getDemandamount1IsNull() )
      demandamount1 = new Long( p_row.getDemandamount1() );
    demandamount1_is_set = p_row.getDemandamount1IsSet();
    demandamount1_is_modified = p_row.getDemandamount1IsModified();
    if ( !p_row.getDemandamount2IsNull() )
      demandamount2 = new Long( p_row.getDemandamount2() );
    demandamount2_is_set = p_row.getDemandamount2IsSet();
    demandamount2_is_modified = p_row.getDemandamount2IsModified();
    if ( !p_row.getDemandamount3IsNull() )
      demandamount3 = new Long( p_row.getDemandamount3() );
    demandamount3_is_set = p_row.getDemandamount3IsSet();
    demandamount3_is_modified = p_row.getDemandamount3IsModified();
    if ( !p_row.getDemandamount4IsNull() )
      demandamount4 = new Long( p_row.getDemandamount4() );
    demandamount4_is_set = p_row.getDemandamount4IsSet();
    demandamount4_is_modified = p_row.getDemandamount4IsModified();
    if ( !p_row.getPaymentAmountDesignate0IsNull() )
      payment_amount_designate0 = new Long( p_row.getPaymentAmountDesignate0() );
    payment_amount_designate0_is_set = p_row.getPaymentAmountDesignate0IsSet();
    payment_amount_designate0_is_modified = p_row.getPaymentAmountDesignate0IsModified();
    if ( !p_row.getPaymentAmountDesignate1IsNull() )
      payment_amount_designate1 = new Long( p_row.getPaymentAmountDesignate1() );
    payment_amount_designate1_is_set = p_row.getPaymentAmountDesignate1IsSet();
    payment_amount_designate1_is_modified = p_row.getPaymentAmountDesignate1IsModified();
    if ( !p_row.getPaymentAmountDesignate2IsNull() )
      payment_amount_designate2 = new Long( p_row.getPaymentAmountDesignate2() );
    payment_amount_designate2_is_set = p_row.getPaymentAmountDesignate2IsSet();
    payment_amount_designate2_is_modified = p_row.getPaymentAmountDesignate2IsModified();
    margin_sec_product_code = p_row.getMarginSecProductCode();
    margin_sec_product_code_is_set = p_row.getMarginSecProductCodeIsSet();
    margin_sec_product_code_is_modified = p_row.getMarginSecProductCodeIsModified();
    if ( !p_row.getMarginSecRateIsNull() )
      margin_sec_rate = new Double( p_row.getMarginSecRate() );
    margin_sec_rate_is_set = p_row.getMarginSecRateIsSet();
    margin_sec_rate_is_modified = p_row.getMarginSecRateIsModified();
    if ( !p_row.getEquityAssetExecutedIsNull() )
      equity_asset_executed = new Long( p_row.getEquityAssetExecuted() );
    equity_asset_executed_is_set = p_row.getEquityAssetExecutedIsSet();
    equity_asset_executed_is_modified = p_row.getEquityAssetExecutedIsModified();
    if ( !p_row.getRuitoAssetExecutedIsNull() )
      ruito_asset_executed = new Long( p_row.getRuitoAssetExecuted() );
    ruito_asset_executed_is_set = p_row.getRuitoAssetExecutedIsSet();
    ruito_asset_executed_is_modified = p_row.getRuitoAssetExecutedIsModified();
    if ( !p_row.getMutualFundAssetExecutedIsNull() )
      mutual_fund_asset_executed = new Long( p_row.getMutualFundAssetExecuted() );
    mutual_fund_asset_executed_is_set = p_row.getMutualFundAssetExecutedIsSet();
    mutual_fund_asset_executed_is_modified = p_row.getMutualFundAssetExecutedIsModified();
    if ( !p_row.getBondAssetExecutedIsNull() )
      bond_asset_executed = new Long( p_row.getBondAssetExecuted() );
    bond_asset_executed_is_set = p_row.getBondAssetExecutedIsSet();
    bond_asset_executed_is_modified = p_row.getBondAssetExecutedIsModified();
    trading_stop = p_row.getTradingStop();
    trading_stop_is_set = p_row.getTradingStopIsSet();
    trading_stop_is_modified = p_row.getTradingStopIsModified();
    margin_open_position_stop = p_row.getMarginOpenPositionStop();
    margin_open_position_stop_is_set = p_row.getMarginOpenPositionStopIsSet();
    margin_open_position_stop_is_modified = p_row.getMarginOpenPositionStopIsModified();
    payment_stop = p_row.getPaymentStop();
    payment_stop_is_set = p_row.getPaymentStopIsSet();
    payment_stop_is_modified = p_row.getPaymentStopIsModified();
    other_trading_stop = p_row.getOtherTradingStop();
    other_trading_stop_is_set = p_row.getOtherTradingStopIsSet();
    other_trading_stop_is_modified = p_row.getOtherTradingStopIsModified();
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
      account_id_is_set = true;
      account_id_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      work_date_is_set = true;
      work_date_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      asset_evaluation_div_is_set = true;
      asset_evaluation_div_is_modified = true;
      account_balance_0_is_set = true;
      account_balance_0_is_modified = true;
      account_balance_1_is_set = true;
      account_balance_1_is_modified = true;
      account_balance_2_is_set = true;
      account_balance_2_is_modified = true;
      account_balance_3_is_set = true;
      account_balance_3_is_modified = true;
      account_balance_4_is_set = true;
      account_balance_4_is_modified = true;
      today_unexecuted_amount_1_is_set = true;
      today_unexecuted_amount_1_is_modified = true;
      today_unexecuted_amount_2_is_set = true;
      today_unexecuted_amount_2_is_modified = true;
      today_unexecuted_amount_3_is_set = true;
      today_unexecuted_amount_3_is_modified = true;
      today_unexecuted_amount_4_is_set = true;
      today_unexecuted_amount_4_is_modified = true;
      day_trade_restraint_0_is_set = true;
      day_trade_restraint_0_is_modified = true;
      day_trade_restraint_1_is_set = true;
      day_trade_restraint_1_is_modified = true;
      day_trade_restraint_2_is_set = true;
      day_trade_restraint_2_is_modified = true;
      day_trade_restraint_3_is_set = true;
      day_trade_restraint_3_is_modified = true;
      day_trade_restraint_4_is_set = true;
      day_trade_restraint_4_is_modified = true;
      other_restraint_0_is_set = true;
      other_restraint_0_is_modified = true;
      other_restraint_1_is_set = true;
      other_restraint_1_is_modified = true;
      other_restraint_2_is_set = true;
      other_restraint_2_is_modified = true;
      other_restraint_3_is_set = true;
      other_restraint_3_is_modified = true;
      other_restraint_4_is_set = true;
      other_restraint_4_is_modified = true;
      margin_account_balance_0_is_set = true;
      margin_account_balance_0_is_modified = true;
      margin_account_balance_1_is_set = true;
      margin_account_balance_1_is_modified = true;
      margin_account_balance_2_is_set = true;
      margin_account_balance_2_is_modified = true;
      margin_account_balance_3_is_set = true;
      margin_account_balance_3_is_modified = true;
      margin_account_balance_4_is_set = true;
      margin_account_balance_4_is_modified = true;
      substitute_security_asset_0_is_set = true;
      substitute_security_asset_0_is_modified = true;
      substitute_security_asset_1_is_set = true;
      substitute_security_asset_1_is_modified = true;
      substitute_security_asset_2_is_set = true;
      substitute_security_asset_2_is_modified = true;
      substitute_security_asset_3_is_set = true;
      substitute_security_asset_3_is_modified = true;
      substitute_security_asset_4_is_set = true;
      substitute_security_asset_4_is_modified = true;
      contract_asset_profit_loss_is_set = true;
      contract_asset_profit_loss_is_modified = true;
      contract_total_cost_is_set = true;
      contract_total_cost_is_modified = true;
      undeli_contract_loss_0_is_set = true;
      undeli_contract_loss_0_is_modified = true;
      undeli_contract_loss_1_is_set = true;
      undeli_contract_loss_1_is_modified = true;
      undeli_contract_loss_2_is_set = true;
      undeli_contract_loss_2_is_modified = true;
      undeli_contract_loss_3_is_set = true;
      undeli_contract_loss_3_is_modified = true;
      today_repay_contract_pre_asset_is_set = true;
      today_repay_contract_pre_asset_is_modified = true;
      contract_amount_day_repay_0_is_set = true;
      contract_amount_day_repay_0_is_modified = true;
      contract_amount_day_repay_1_is_set = true;
      contract_amount_day_repay_1_is_modified = true;
      contract_amount_day_repay_2_is_set = true;
      contract_amount_day_repay_2_is_modified = true;
      contract_amount_day_repay_3_is_set = true;
      contract_amount_day_repay_3_is_modified = true;
      contract_amount_day_repay_4_is_set = true;
      contract_amount_day_repay_4_is_modified = true;
      margin_power_0_is_set = true;
      margin_power_0_is_modified = true;
      margin_power_1_is_set = true;
      margin_power_1_is_modified = true;
      margin_power_2_is_set = true;
      margin_power_2_is_modified = true;
      margin_power_3_is_set = true;
      margin_power_3_is_modified = true;
      margin_power_4_is_set = true;
      margin_power_4_is_modified = true;
      margin_trading_power_1_is_set = true;
      margin_trading_power_1_is_modified = true;
      margin_trading_power_2_is_set = true;
      margin_trading_power_2_is_modified = true;
      margin_trading_power_3_is_set = true;
      margin_trading_power_3_is_modified = true;
      margin_trading_power_4_is_set = true;
      margin_trading_power_4_is_modified = true;
      margin_deposit_rate_0_is_set = true;
      margin_deposit_rate_0_is_modified = true;
      margin_deposit_rate_1_is_set = true;
      margin_deposit_rate_1_is_modified = true;
      margin_deposit_rate_2_is_set = true;
      margin_deposit_rate_2_is_modified = true;
      margin_deposit_rate_3_is_set = true;
      margin_deposit_rate_3_is_modified = true;
      margin_deposit_rate_4_is_set = true;
      margin_deposit_rate_4_is_modified = true;
      act_rec_trading_power_3_is_set = true;
      act_rec_trading_power_3_is_modified = true;
      act_rec_trading_power_4_is_set = true;
      act_rec_trading_power_4_is_modified = true;
      act_rec_trading_power_4_dash_is_set = true;
      act_rec_trading_power_4_dash_is_modified = true;
      equity_trading_power_3_is_set = true;
      equity_trading_power_3_is_modified = true;
      equity_trading_power_4_is_set = true;
      equity_trading_power_4_is_modified = true;
      equity_trading_power_4_dash_is_set = true;
      equity_trading_power_4_dash_is_modified = true;
      undeli_contract_amount_0_is_set = true;
      undeli_contract_amount_0_is_modified = true;
      undeli_contract_amount_1_is_set = true;
      undeli_contract_amount_1_is_modified = true;
      undeli_contract_amount_2_is_set = true;
      undeli_contract_amount_2_is_modified = true;
      undeli_contract_amount_3_is_set = true;
      undeli_contract_amount_3_is_modified = true;
      margin_draw_power_0_is_set = true;
      margin_draw_power_0_is_modified = true;
      margin_draw_power_1_is_set = true;
      margin_draw_power_1_is_modified = true;
      margin_draw_power_2_is_set = true;
      margin_draw_power_2_is_modified = true;
      margin_draw_power_3_is_set = true;
      margin_draw_power_3_is_modified = true;
      margin_draw_power_4_is_set = true;
      margin_draw_power_4_is_modified = true;
      other_trading_power_1_is_set = true;
      other_trading_power_1_is_modified = true;
      other_trading_power_2_is_set = true;
      other_trading_power_2_is_modified = true;
      other_trading_power_3_is_set = true;
      other_trading_power_3_is_modified = true;
      other_trading_power_4_is_set = true;
      other_trading_power_4_is_modified = true;
      demandamount0_is_set = true;
      demandamount0_is_modified = true;
      demandamount1_is_set = true;
      demandamount1_is_modified = true;
      demandamount2_is_set = true;
      demandamount2_is_modified = true;
      demandamount3_is_set = true;
      demandamount3_is_modified = true;
      demandamount4_is_set = true;
      demandamount4_is_modified = true;
      payment_amount_designate0_is_set = true;
      payment_amount_designate0_is_modified = true;
      payment_amount_designate1_is_set = true;
      payment_amount_designate1_is_modified = true;
      payment_amount_designate2_is_set = true;
      payment_amount_designate2_is_modified = true;
      margin_sec_product_code_is_set = true;
      margin_sec_product_code_is_modified = true;
      margin_sec_rate_is_set = true;
      margin_sec_rate_is_modified = true;
      equity_asset_executed_is_set = true;
      equity_asset_executed_is_modified = true;
      ruito_asset_executed_is_set = true;
      ruito_asset_executed_is_modified = true;
      mutual_fund_asset_executed_is_set = true;
      mutual_fund_asset_executed_is_modified = true;
      bond_asset_executed_is_set = true;
      bond_asset_executed_is_modified = true;
      trading_stop_is_set = true;
      trading_stop_is_modified = true;
      margin_open_position_stop_is_set = true;
      margin_open_position_stop_is_modified = true;
      payment_stop_is_set = true;
      payment_stop_is_modified = true;
      other_trading_stop_is_set = true;
      other_trading_stop_is_modified = true;
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
    if ( !( other instanceof OrixTpCalcResultMarginRow ) )
       return false;
    return fieldsEqual( (OrixTpCalcResultMarginRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のOrixTpCalcResultMarginRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( OrixTpCalcResultMarginRow row )
  {
    if ( calc_result_margin_id != row.getCalcResultMarginId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( work_date == null ) {
      if ( row.getWorkDate() != null )
        return false;
    } else if ( !work_date.equals( row.getWorkDate() ) ) {
        return false;
    }
    if ( branch_code == null ) {
      if ( row.getBranchCode() != null )
        return false;
    } else if ( !branch_code.equals( row.getBranchCode() ) ) {
        return false;
    }
    if ( account_code == null ) {
      if ( row.getAccountCode() != null )
        return false;
    } else if ( !account_code.equals( row.getAccountCode() ) ) {
        return false;
    }
    if ( asset_evaluation_div == null ) {
      if ( row.getAssetEvaluationDiv() != null )
        return false;
    } else if ( !asset_evaluation_div.equals( row.getAssetEvaluationDiv() ) ) {
        return false;
    }
    if ( account_balance_0 == null ) {
      if ( !row.getAccountBalance0IsNull() )
        return false;
    } else if ( row.getAccountBalance0IsNull() || ( account_balance_0.longValue() != row.getAccountBalance0() ) ) {
        return false;
    }
    if ( account_balance_1 == null ) {
      if ( !row.getAccountBalance1IsNull() )
        return false;
    } else if ( row.getAccountBalance1IsNull() || ( account_balance_1.longValue() != row.getAccountBalance1() ) ) {
        return false;
    }
    if ( account_balance_2 == null ) {
      if ( !row.getAccountBalance2IsNull() )
        return false;
    } else if ( row.getAccountBalance2IsNull() || ( account_balance_2.longValue() != row.getAccountBalance2() ) ) {
        return false;
    }
    if ( account_balance_3 == null ) {
      if ( !row.getAccountBalance3IsNull() )
        return false;
    } else if ( row.getAccountBalance3IsNull() || ( account_balance_3.longValue() != row.getAccountBalance3() ) ) {
        return false;
    }
    if ( account_balance_4 == null ) {
      if ( !row.getAccountBalance4IsNull() )
        return false;
    } else if ( row.getAccountBalance4IsNull() || ( account_balance_4.longValue() != row.getAccountBalance4() ) ) {
        return false;
    }
    if ( today_unexecuted_amount_1 == null ) {
      if ( !row.getTodayUnexecutedAmount1IsNull() )
        return false;
    } else if ( row.getTodayUnexecutedAmount1IsNull() || ( today_unexecuted_amount_1.longValue() != row.getTodayUnexecutedAmount1() ) ) {
        return false;
    }
    if ( today_unexecuted_amount_2 == null ) {
      if ( !row.getTodayUnexecutedAmount2IsNull() )
        return false;
    } else if ( row.getTodayUnexecutedAmount2IsNull() || ( today_unexecuted_amount_2.longValue() != row.getTodayUnexecutedAmount2() ) ) {
        return false;
    }
    if ( today_unexecuted_amount_3 == null ) {
      if ( !row.getTodayUnexecutedAmount3IsNull() )
        return false;
    } else if ( row.getTodayUnexecutedAmount3IsNull() || ( today_unexecuted_amount_3.longValue() != row.getTodayUnexecutedAmount3() ) ) {
        return false;
    }
    if ( today_unexecuted_amount_4 == null ) {
      if ( !row.getTodayUnexecutedAmount4IsNull() )
        return false;
    } else if ( row.getTodayUnexecutedAmount4IsNull() || ( today_unexecuted_amount_4.longValue() != row.getTodayUnexecutedAmount4() ) ) {
        return false;
    }
    if ( day_trade_restraint_0 == null ) {
      if ( !row.getDayTradeRestraint0IsNull() )
        return false;
    } else if ( row.getDayTradeRestraint0IsNull() || ( day_trade_restraint_0.longValue() != row.getDayTradeRestraint0() ) ) {
        return false;
    }
    if ( day_trade_restraint_1 == null ) {
      if ( !row.getDayTradeRestraint1IsNull() )
        return false;
    } else if ( row.getDayTradeRestraint1IsNull() || ( day_trade_restraint_1.longValue() != row.getDayTradeRestraint1() ) ) {
        return false;
    }
    if ( day_trade_restraint_2 == null ) {
      if ( !row.getDayTradeRestraint2IsNull() )
        return false;
    } else if ( row.getDayTradeRestraint2IsNull() || ( day_trade_restraint_2.longValue() != row.getDayTradeRestraint2() ) ) {
        return false;
    }
    if ( day_trade_restraint_3 == null ) {
      if ( !row.getDayTradeRestraint3IsNull() )
        return false;
    } else if ( row.getDayTradeRestraint3IsNull() || ( day_trade_restraint_3.longValue() != row.getDayTradeRestraint3() ) ) {
        return false;
    }
    if ( day_trade_restraint_4 == null ) {
      if ( !row.getDayTradeRestraint4IsNull() )
        return false;
    } else if ( row.getDayTradeRestraint4IsNull() || ( day_trade_restraint_4.longValue() != row.getDayTradeRestraint4() ) ) {
        return false;
    }
    if ( other_restraint_0 == null ) {
      if ( !row.getOtherRestraint0IsNull() )
        return false;
    } else if ( row.getOtherRestraint0IsNull() || ( other_restraint_0.longValue() != row.getOtherRestraint0() ) ) {
        return false;
    }
    if ( other_restraint_1 == null ) {
      if ( !row.getOtherRestraint1IsNull() )
        return false;
    } else if ( row.getOtherRestraint1IsNull() || ( other_restraint_1.longValue() != row.getOtherRestraint1() ) ) {
        return false;
    }
    if ( other_restraint_2 == null ) {
      if ( !row.getOtherRestraint2IsNull() )
        return false;
    } else if ( row.getOtherRestraint2IsNull() || ( other_restraint_2.longValue() != row.getOtherRestraint2() ) ) {
        return false;
    }
    if ( other_restraint_3 == null ) {
      if ( !row.getOtherRestraint3IsNull() )
        return false;
    } else if ( row.getOtherRestraint3IsNull() || ( other_restraint_3.longValue() != row.getOtherRestraint3() ) ) {
        return false;
    }
    if ( other_restraint_4 == null ) {
      if ( !row.getOtherRestraint4IsNull() )
        return false;
    } else if ( row.getOtherRestraint4IsNull() || ( other_restraint_4.longValue() != row.getOtherRestraint4() ) ) {
        return false;
    }
    if ( margin_account_balance_0 == null ) {
      if ( !row.getMarginAccountBalance0IsNull() )
        return false;
    } else if ( row.getMarginAccountBalance0IsNull() || ( margin_account_balance_0.longValue() != row.getMarginAccountBalance0() ) ) {
        return false;
    }
    if ( margin_account_balance_1 == null ) {
      if ( !row.getMarginAccountBalance1IsNull() )
        return false;
    } else if ( row.getMarginAccountBalance1IsNull() || ( margin_account_balance_1.longValue() != row.getMarginAccountBalance1() ) ) {
        return false;
    }
    if ( margin_account_balance_2 == null ) {
      if ( !row.getMarginAccountBalance2IsNull() )
        return false;
    } else if ( row.getMarginAccountBalance2IsNull() || ( margin_account_balance_2.longValue() != row.getMarginAccountBalance2() ) ) {
        return false;
    }
    if ( margin_account_balance_3 == null ) {
      if ( !row.getMarginAccountBalance3IsNull() )
        return false;
    } else if ( row.getMarginAccountBalance3IsNull() || ( margin_account_balance_3.longValue() != row.getMarginAccountBalance3() ) ) {
        return false;
    }
    if ( margin_account_balance_4 == null ) {
      if ( !row.getMarginAccountBalance4IsNull() )
        return false;
    } else if ( row.getMarginAccountBalance4IsNull() || ( margin_account_balance_4.longValue() != row.getMarginAccountBalance4() ) ) {
        return false;
    }
    if ( substitute_security_asset_0 == null ) {
      if ( !row.getSubstituteSecurityAsset0IsNull() )
        return false;
    } else if ( row.getSubstituteSecurityAsset0IsNull() || ( substitute_security_asset_0.longValue() != row.getSubstituteSecurityAsset0() ) ) {
        return false;
    }
    if ( substitute_security_asset_1 == null ) {
      if ( !row.getSubstituteSecurityAsset1IsNull() )
        return false;
    } else if ( row.getSubstituteSecurityAsset1IsNull() || ( substitute_security_asset_1.longValue() != row.getSubstituteSecurityAsset1() ) ) {
        return false;
    }
    if ( substitute_security_asset_2 == null ) {
      if ( !row.getSubstituteSecurityAsset2IsNull() )
        return false;
    } else if ( row.getSubstituteSecurityAsset2IsNull() || ( substitute_security_asset_2.longValue() != row.getSubstituteSecurityAsset2() ) ) {
        return false;
    }
    if ( substitute_security_asset_3 == null ) {
      if ( !row.getSubstituteSecurityAsset3IsNull() )
        return false;
    } else if ( row.getSubstituteSecurityAsset3IsNull() || ( substitute_security_asset_3.longValue() != row.getSubstituteSecurityAsset3() ) ) {
        return false;
    }
    if ( substitute_security_asset_4 == null ) {
      if ( !row.getSubstituteSecurityAsset4IsNull() )
        return false;
    } else if ( row.getSubstituteSecurityAsset4IsNull() || ( substitute_security_asset_4.longValue() != row.getSubstituteSecurityAsset4() ) ) {
        return false;
    }
    if ( contract_asset_profit_loss == null ) {
      if ( !row.getContractAssetProfitLossIsNull() )
        return false;
    } else if ( row.getContractAssetProfitLossIsNull() || ( contract_asset_profit_loss.longValue() != row.getContractAssetProfitLoss() ) ) {
        return false;
    }
    if ( contract_total_cost == null ) {
      if ( !row.getContractTotalCostIsNull() )
        return false;
    } else if ( row.getContractTotalCostIsNull() || ( contract_total_cost.longValue() != row.getContractTotalCost() ) ) {
        return false;
    }
    if ( undeli_contract_loss_0 == null ) {
      if ( !row.getUndeliContractLoss0IsNull() )
        return false;
    } else if ( row.getUndeliContractLoss0IsNull() || ( undeli_contract_loss_0.longValue() != row.getUndeliContractLoss0() ) ) {
        return false;
    }
    if ( undeli_contract_loss_1 == null ) {
      if ( !row.getUndeliContractLoss1IsNull() )
        return false;
    } else if ( row.getUndeliContractLoss1IsNull() || ( undeli_contract_loss_1.longValue() != row.getUndeliContractLoss1() ) ) {
        return false;
    }
    if ( undeli_contract_loss_2 == null ) {
      if ( !row.getUndeliContractLoss2IsNull() )
        return false;
    } else if ( row.getUndeliContractLoss2IsNull() || ( undeli_contract_loss_2.longValue() != row.getUndeliContractLoss2() ) ) {
        return false;
    }
    if ( undeli_contract_loss_3 == null ) {
      if ( !row.getUndeliContractLoss3IsNull() )
        return false;
    } else if ( row.getUndeliContractLoss3IsNull() || ( undeli_contract_loss_3.longValue() != row.getUndeliContractLoss3() ) ) {
        return false;
    }
    if ( today_repay_contract_pre_asset == null ) {
      if ( !row.getTodayRepayContractPreAssetIsNull() )
        return false;
    } else if ( row.getTodayRepayContractPreAssetIsNull() || ( today_repay_contract_pre_asset.longValue() != row.getTodayRepayContractPreAsset() ) ) {
        return false;
    }
    if ( contract_amount_day_repay_0 == null ) {
      if ( !row.getContractAmountDayRepay0IsNull() )
        return false;
    } else if ( row.getContractAmountDayRepay0IsNull() || ( contract_amount_day_repay_0.longValue() != row.getContractAmountDayRepay0() ) ) {
        return false;
    }
    if ( contract_amount_day_repay_1 == null ) {
      if ( !row.getContractAmountDayRepay1IsNull() )
        return false;
    } else if ( row.getContractAmountDayRepay1IsNull() || ( contract_amount_day_repay_1.longValue() != row.getContractAmountDayRepay1() ) ) {
        return false;
    }
    if ( contract_amount_day_repay_2 == null ) {
      if ( !row.getContractAmountDayRepay2IsNull() )
        return false;
    } else if ( row.getContractAmountDayRepay2IsNull() || ( contract_amount_day_repay_2.longValue() != row.getContractAmountDayRepay2() ) ) {
        return false;
    }
    if ( contract_amount_day_repay_3 == null ) {
      if ( !row.getContractAmountDayRepay3IsNull() )
        return false;
    } else if ( row.getContractAmountDayRepay3IsNull() || ( contract_amount_day_repay_3.longValue() != row.getContractAmountDayRepay3() ) ) {
        return false;
    }
    if ( contract_amount_day_repay_4 == null ) {
      if ( !row.getContractAmountDayRepay4IsNull() )
        return false;
    } else if ( row.getContractAmountDayRepay4IsNull() || ( contract_amount_day_repay_4.longValue() != row.getContractAmountDayRepay4() ) ) {
        return false;
    }
    if ( margin_power_0 == null ) {
      if ( !row.getMarginPower0IsNull() )
        return false;
    } else if ( row.getMarginPower0IsNull() || ( margin_power_0.longValue() != row.getMarginPower0() ) ) {
        return false;
    }
    if ( margin_power_1 == null ) {
      if ( !row.getMarginPower1IsNull() )
        return false;
    } else if ( row.getMarginPower1IsNull() || ( margin_power_1.longValue() != row.getMarginPower1() ) ) {
        return false;
    }
    if ( margin_power_2 == null ) {
      if ( !row.getMarginPower2IsNull() )
        return false;
    } else if ( row.getMarginPower2IsNull() || ( margin_power_2.longValue() != row.getMarginPower2() ) ) {
        return false;
    }
    if ( margin_power_3 == null ) {
      if ( !row.getMarginPower3IsNull() )
        return false;
    } else if ( row.getMarginPower3IsNull() || ( margin_power_3.longValue() != row.getMarginPower3() ) ) {
        return false;
    }
    if ( margin_power_4 == null ) {
      if ( !row.getMarginPower4IsNull() )
        return false;
    } else if ( row.getMarginPower4IsNull() || ( margin_power_4.longValue() != row.getMarginPower4() ) ) {
        return false;
    }
    if ( margin_trading_power_1 == null ) {
      if ( !row.getMarginTradingPower1IsNull() )
        return false;
    } else if ( row.getMarginTradingPower1IsNull() || ( margin_trading_power_1.longValue() != row.getMarginTradingPower1() ) ) {
        return false;
    }
    if ( margin_trading_power_2 == null ) {
      if ( !row.getMarginTradingPower2IsNull() )
        return false;
    } else if ( row.getMarginTradingPower2IsNull() || ( margin_trading_power_2.longValue() != row.getMarginTradingPower2() ) ) {
        return false;
    }
    if ( margin_trading_power_3 == null ) {
      if ( !row.getMarginTradingPower3IsNull() )
        return false;
    } else if ( row.getMarginTradingPower3IsNull() || ( margin_trading_power_3.longValue() != row.getMarginTradingPower3() ) ) {
        return false;
    }
    if ( margin_trading_power_4 == null ) {
      if ( !row.getMarginTradingPower4IsNull() )
        return false;
    } else if ( row.getMarginTradingPower4IsNull() || ( margin_trading_power_4.longValue() != row.getMarginTradingPower4() ) ) {
        return false;
    }
    if ( margin_deposit_rate_0 == null ) {
      if ( !row.getMarginDepositRate0IsNull() )
        return false;
    } else if ( row.getMarginDepositRate0IsNull() || ( margin_deposit_rate_0.doubleValue() != row.getMarginDepositRate0() ) ) {
        return false;
    }
    if ( margin_deposit_rate_1 == null ) {
      if ( !row.getMarginDepositRate1IsNull() )
        return false;
    } else if ( row.getMarginDepositRate1IsNull() || ( margin_deposit_rate_1.doubleValue() != row.getMarginDepositRate1() ) ) {
        return false;
    }
    if ( margin_deposit_rate_2 == null ) {
      if ( !row.getMarginDepositRate2IsNull() )
        return false;
    } else if ( row.getMarginDepositRate2IsNull() || ( margin_deposit_rate_2.doubleValue() != row.getMarginDepositRate2() ) ) {
        return false;
    }
    if ( margin_deposit_rate_3 == null ) {
      if ( !row.getMarginDepositRate3IsNull() )
        return false;
    } else if ( row.getMarginDepositRate3IsNull() || ( margin_deposit_rate_3.doubleValue() != row.getMarginDepositRate3() ) ) {
        return false;
    }
    if ( margin_deposit_rate_4 == null ) {
      if ( !row.getMarginDepositRate4IsNull() )
        return false;
    } else if ( row.getMarginDepositRate4IsNull() || ( margin_deposit_rate_4.doubleValue() != row.getMarginDepositRate4() ) ) {
        return false;
    }
    if ( act_rec_trading_power_3 == null ) {
      if ( !row.getActRecTradingPower3IsNull() )
        return false;
    } else if ( row.getActRecTradingPower3IsNull() || ( act_rec_trading_power_3.longValue() != row.getActRecTradingPower3() ) ) {
        return false;
    }
    if ( act_rec_trading_power_4 == null ) {
      if ( !row.getActRecTradingPower4IsNull() )
        return false;
    } else if ( row.getActRecTradingPower4IsNull() || ( act_rec_trading_power_4.longValue() != row.getActRecTradingPower4() ) ) {
        return false;
    }
    if ( act_rec_trading_power_4_dash == null ) {
      if ( !row.getActRecTradingPower4DashIsNull() )
        return false;
    } else if ( row.getActRecTradingPower4DashIsNull() || ( act_rec_trading_power_4_dash.longValue() != row.getActRecTradingPower4Dash() ) ) {
        return false;
    }
    if ( equity_trading_power_3 == null ) {
      if ( !row.getEquityTradingPower3IsNull() )
        return false;
    } else if ( row.getEquityTradingPower3IsNull() || ( equity_trading_power_3.longValue() != row.getEquityTradingPower3() ) ) {
        return false;
    }
    if ( equity_trading_power_4 == null ) {
      if ( !row.getEquityTradingPower4IsNull() )
        return false;
    } else if ( row.getEquityTradingPower4IsNull() || ( equity_trading_power_4.longValue() != row.getEquityTradingPower4() ) ) {
        return false;
    }
    if ( equity_trading_power_4_dash == null ) {
      if ( !row.getEquityTradingPower4DashIsNull() )
        return false;
    } else if ( row.getEquityTradingPower4DashIsNull() || ( equity_trading_power_4_dash.longValue() != row.getEquityTradingPower4Dash() ) ) {
        return false;
    }
    if ( undeli_contract_amount_0 == null ) {
      if ( !row.getUndeliContractAmount0IsNull() )
        return false;
    } else if ( row.getUndeliContractAmount0IsNull() || ( undeli_contract_amount_0.longValue() != row.getUndeliContractAmount0() ) ) {
        return false;
    }
    if ( undeli_contract_amount_1 == null ) {
      if ( !row.getUndeliContractAmount1IsNull() )
        return false;
    } else if ( row.getUndeliContractAmount1IsNull() || ( undeli_contract_amount_1.longValue() != row.getUndeliContractAmount1() ) ) {
        return false;
    }
    if ( undeli_contract_amount_2 == null ) {
      if ( !row.getUndeliContractAmount2IsNull() )
        return false;
    } else if ( row.getUndeliContractAmount2IsNull() || ( undeli_contract_amount_2.longValue() != row.getUndeliContractAmount2() ) ) {
        return false;
    }
    if ( undeli_contract_amount_3 == null ) {
      if ( !row.getUndeliContractAmount3IsNull() )
        return false;
    } else if ( row.getUndeliContractAmount3IsNull() || ( undeli_contract_amount_3.longValue() != row.getUndeliContractAmount3() ) ) {
        return false;
    }
    if ( margin_draw_power_0 == null ) {
      if ( !row.getMarginDrawPower0IsNull() )
        return false;
    } else if ( row.getMarginDrawPower0IsNull() || ( margin_draw_power_0.longValue() != row.getMarginDrawPower0() ) ) {
        return false;
    }
    if ( margin_draw_power_1 == null ) {
      if ( !row.getMarginDrawPower1IsNull() )
        return false;
    } else if ( row.getMarginDrawPower1IsNull() || ( margin_draw_power_1.longValue() != row.getMarginDrawPower1() ) ) {
        return false;
    }
    if ( margin_draw_power_2 == null ) {
      if ( !row.getMarginDrawPower2IsNull() )
        return false;
    } else if ( row.getMarginDrawPower2IsNull() || ( margin_draw_power_2.longValue() != row.getMarginDrawPower2() ) ) {
        return false;
    }
    if ( margin_draw_power_3 == null ) {
      if ( !row.getMarginDrawPower3IsNull() )
        return false;
    } else if ( row.getMarginDrawPower3IsNull() || ( margin_draw_power_3.longValue() != row.getMarginDrawPower3() ) ) {
        return false;
    }
    if ( margin_draw_power_4 == null ) {
      if ( !row.getMarginDrawPower4IsNull() )
        return false;
    } else if ( row.getMarginDrawPower4IsNull() || ( margin_draw_power_4.longValue() != row.getMarginDrawPower4() ) ) {
        return false;
    }
    if ( other_trading_power_1 == null ) {
      if ( !row.getOtherTradingPower1IsNull() )
        return false;
    } else if ( row.getOtherTradingPower1IsNull() || ( other_trading_power_1.longValue() != row.getOtherTradingPower1() ) ) {
        return false;
    }
    if ( other_trading_power_2 == null ) {
      if ( !row.getOtherTradingPower2IsNull() )
        return false;
    } else if ( row.getOtherTradingPower2IsNull() || ( other_trading_power_2.longValue() != row.getOtherTradingPower2() ) ) {
        return false;
    }
    if ( other_trading_power_3 == null ) {
      if ( !row.getOtherTradingPower3IsNull() )
        return false;
    } else if ( row.getOtherTradingPower3IsNull() || ( other_trading_power_3.longValue() != row.getOtherTradingPower3() ) ) {
        return false;
    }
    if ( other_trading_power_4 == null ) {
      if ( !row.getOtherTradingPower4IsNull() )
        return false;
    } else if ( row.getOtherTradingPower4IsNull() || ( other_trading_power_4.longValue() != row.getOtherTradingPower4() ) ) {
        return false;
    }
    if ( demandamount0 == null ) {
      if ( !row.getDemandamount0IsNull() )
        return false;
    } else if ( row.getDemandamount0IsNull() || ( demandamount0.longValue() != row.getDemandamount0() ) ) {
        return false;
    }
    if ( demandamount1 == null ) {
      if ( !row.getDemandamount1IsNull() )
        return false;
    } else if ( row.getDemandamount1IsNull() || ( demandamount1.longValue() != row.getDemandamount1() ) ) {
        return false;
    }
    if ( demandamount2 == null ) {
      if ( !row.getDemandamount2IsNull() )
        return false;
    } else if ( row.getDemandamount2IsNull() || ( demandamount2.longValue() != row.getDemandamount2() ) ) {
        return false;
    }
    if ( demandamount3 == null ) {
      if ( !row.getDemandamount3IsNull() )
        return false;
    } else if ( row.getDemandamount3IsNull() || ( demandamount3.longValue() != row.getDemandamount3() ) ) {
        return false;
    }
    if ( demandamount4 == null ) {
      if ( !row.getDemandamount4IsNull() )
        return false;
    } else if ( row.getDemandamount4IsNull() || ( demandamount4.longValue() != row.getDemandamount4() ) ) {
        return false;
    }
    if ( payment_amount_designate0 == null ) {
      if ( !row.getPaymentAmountDesignate0IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate0IsNull() || ( payment_amount_designate0.longValue() != row.getPaymentAmountDesignate0() ) ) {
        return false;
    }
    if ( payment_amount_designate1 == null ) {
      if ( !row.getPaymentAmountDesignate1IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate1IsNull() || ( payment_amount_designate1.longValue() != row.getPaymentAmountDesignate1() ) ) {
        return false;
    }
    if ( payment_amount_designate2 == null ) {
      if ( !row.getPaymentAmountDesignate2IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate2IsNull() || ( payment_amount_designate2.longValue() != row.getPaymentAmountDesignate2() ) ) {
        return false;
    }
    if ( margin_sec_product_code == null ) {
      if ( row.getMarginSecProductCode() != null )
        return false;
    } else if ( !margin_sec_product_code.equals( row.getMarginSecProductCode() ) ) {
        return false;
    }
    if ( margin_sec_rate == null ) {
      if ( !row.getMarginSecRateIsNull() )
        return false;
    } else if ( row.getMarginSecRateIsNull() || ( margin_sec_rate.doubleValue() != row.getMarginSecRate() ) ) {
        return false;
    }
    if ( equity_asset_executed == null ) {
      if ( !row.getEquityAssetExecutedIsNull() )
        return false;
    } else if ( row.getEquityAssetExecutedIsNull() || ( equity_asset_executed.longValue() != row.getEquityAssetExecuted() ) ) {
        return false;
    }
    if ( ruito_asset_executed == null ) {
      if ( !row.getRuitoAssetExecutedIsNull() )
        return false;
    } else if ( row.getRuitoAssetExecutedIsNull() || ( ruito_asset_executed.longValue() != row.getRuitoAssetExecuted() ) ) {
        return false;
    }
    if ( mutual_fund_asset_executed == null ) {
      if ( !row.getMutualFundAssetExecutedIsNull() )
        return false;
    } else if ( row.getMutualFundAssetExecutedIsNull() || ( mutual_fund_asset_executed.longValue() != row.getMutualFundAssetExecuted() ) ) {
        return false;
    }
    if ( bond_asset_executed == null ) {
      if ( !row.getBondAssetExecutedIsNull() )
        return false;
    } else if ( row.getBondAssetExecutedIsNull() || ( bond_asset_executed.longValue() != row.getBondAssetExecuted() ) ) {
        return false;
    }
    if ( trading_stop == null ) {
      if ( row.getTradingStop() != null )
        return false;
    } else if ( !trading_stop.equals( row.getTradingStop() ) ) {
        return false;
    }
    if ( margin_open_position_stop == null ) {
      if ( row.getMarginOpenPositionStop() != null )
        return false;
    } else if ( !margin_open_position_stop.equals( row.getMarginOpenPositionStop() ) ) {
        return false;
    }
    if ( payment_stop == null ) {
      if ( row.getPaymentStop() != null )
        return false;
    } else if ( !payment_stop.equals( row.getPaymentStop() ) ) {
        return false;
    }
    if ( other_trading_stop == null ) {
      if ( row.getOtherTradingStop() != null )
        return false;
    } else if ( !other_trading_stop.equals( row.getOtherTradingStop() ) ) {
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
      return  ((int) calc_result_margin_id)
        + ((int) account_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (work_date!=null? work_date.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (asset_evaluation_div!=null? asset_evaluation_div.hashCode(): 0) 
        + (account_balance_0!=null? account_balance_0.hashCode(): 0) 
        + (account_balance_1!=null? account_balance_1.hashCode(): 0) 
        + (account_balance_2!=null? account_balance_2.hashCode(): 0) 
        + (account_balance_3!=null? account_balance_3.hashCode(): 0) 
        + (account_balance_4!=null? account_balance_4.hashCode(): 0) 
        + (today_unexecuted_amount_1!=null? today_unexecuted_amount_1.hashCode(): 0) 
        + (today_unexecuted_amount_2!=null? today_unexecuted_amount_2.hashCode(): 0) 
        + (today_unexecuted_amount_3!=null? today_unexecuted_amount_3.hashCode(): 0) 
        + (today_unexecuted_amount_4!=null? today_unexecuted_amount_4.hashCode(): 0) 
        + (day_trade_restraint_0!=null? day_trade_restraint_0.hashCode(): 0) 
        + (day_trade_restraint_1!=null? day_trade_restraint_1.hashCode(): 0) 
        + (day_trade_restraint_2!=null? day_trade_restraint_2.hashCode(): 0) 
        + (day_trade_restraint_3!=null? day_trade_restraint_3.hashCode(): 0) 
        + (day_trade_restraint_4!=null? day_trade_restraint_4.hashCode(): 0) 
        + (other_restraint_0!=null? other_restraint_0.hashCode(): 0) 
        + (other_restraint_1!=null? other_restraint_1.hashCode(): 0) 
        + (other_restraint_2!=null? other_restraint_2.hashCode(): 0) 
        + (other_restraint_3!=null? other_restraint_3.hashCode(): 0) 
        + (other_restraint_4!=null? other_restraint_4.hashCode(): 0) 
        + (margin_account_balance_0!=null? margin_account_balance_0.hashCode(): 0) 
        + (margin_account_balance_1!=null? margin_account_balance_1.hashCode(): 0) 
        + (margin_account_balance_2!=null? margin_account_balance_2.hashCode(): 0) 
        + (margin_account_balance_3!=null? margin_account_balance_3.hashCode(): 0) 
        + (margin_account_balance_4!=null? margin_account_balance_4.hashCode(): 0) 
        + (substitute_security_asset_0!=null? substitute_security_asset_0.hashCode(): 0) 
        + (substitute_security_asset_1!=null? substitute_security_asset_1.hashCode(): 0) 
        + (substitute_security_asset_2!=null? substitute_security_asset_2.hashCode(): 0) 
        + (substitute_security_asset_3!=null? substitute_security_asset_3.hashCode(): 0) 
        + (substitute_security_asset_4!=null? substitute_security_asset_4.hashCode(): 0) 
        + (contract_asset_profit_loss!=null? contract_asset_profit_loss.hashCode(): 0) 
        + (contract_total_cost!=null? contract_total_cost.hashCode(): 0) 
        + (undeli_contract_loss_0!=null? undeli_contract_loss_0.hashCode(): 0) 
        + (undeli_contract_loss_1!=null? undeli_contract_loss_1.hashCode(): 0) 
        + (undeli_contract_loss_2!=null? undeli_contract_loss_2.hashCode(): 0) 
        + (undeli_contract_loss_3!=null? undeli_contract_loss_3.hashCode(): 0) 
        + (today_repay_contract_pre_asset!=null? today_repay_contract_pre_asset.hashCode(): 0) 
        + (contract_amount_day_repay_0!=null? contract_amount_day_repay_0.hashCode(): 0) 
        + (contract_amount_day_repay_1!=null? contract_amount_day_repay_1.hashCode(): 0) 
        + (contract_amount_day_repay_2!=null? contract_amount_day_repay_2.hashCode(): 0) 
        + (contract_amount_day_repay_3!=null? contract_amount_day_repay_3.hashCode(): 0) 
        + (contract_amount_day_repay_4!=null? contract_amount_day_repay_4.hashCode(): 0) 
        + (margin_power_0!=null? margin_power_0.hashCode(): 0) 
        + (margin_power_1!=null? margin_power_1.hashCode(): 0) 
        + (margin_power_2!=null? margin_power_2.hashCode(): 0) 
        + (margin_power_3!=null? margin_power_3.hashCode(): 0) 
        + (margin_power_4!=null? margin_power_4.hashCode(): 0) 
        + (margin_trading_power_1!=null? margin_trading_power_1.hashCode(): 0) 
        + (margin_trading_power_2!=null? margin_trading_power_2.hashCode(): 0) 
        + (margin_trading_power_3!=null? margin_trading_power_3.hashCode(): 0) 
        + (margin_trading_power_4!=null? margin_trading_power_4.hashCode(): 0) 
        + (margin_deposit_rate_0!=null? margin_deposit_rate_0.hashCode(): 0) 
        + (margin_deposit_rate_1!=null? margin_deposit_rate_1.hashCode(): 0) 
        + (margin_deposit_rate_2!=null? margin_deposit_rate_2.hashCode(): 0) 
        + (margin_deposit_rate_3!=null? margin_deposit_rate_3.hashCode(): 0) 
        + (margin_deposit_rate_4!=null? margin_deposit_rate_4.hashCode(): 0) 
        + (act_rec_trading_power_3!=null? act_rec_trading_power_3.hashCode(): 0) 
        + (act_rec_trading_power_4!=null? act_rec_trading_power_4.hashCode(): 0) 
        + (act_rec_trading_power_4_dash!=null? act_rec_trading_power_4_dash.hashCode(): 0) 
        + (equity_trading_power_3!=null? equity_trading_power_3.hashCode(): 0) 
        + (equity_trading_power_4!=null? equity_trading_power_4.hashCode(): 0) 
        + (equity_trading_power_4_dash!=null? equity_trading_power_4_dash.hashCode(): 0) 
        + (undeli_contract_amount_0!=null? undeli_contract_amount_0.hashCode(): 0) 
        + (undeli_contract_amount_1!=null? undeli_contract_amount_1.hashCode(): 0) 
        + (undeli_contract_amount_2!=null? undeli_contract_amount_2.hashCode(): 0) 
        + (undeli_contract_amount_3!=null? undeli_contract_amount_3.hashCode(): 0) 
        + (margin_draw_power_0!=null? margin_draw_power_0.hashCode(): 0) 
        + (margin_draw_power_1!=null? margin_draw_power_1.hashCode(): 0) 
        + (margin_draw_power_2!=null? margin_draw_power_2.hashCode(): 0) 
        + (margin_draw_power_3!=null? margin_draw_power_3.hashCode(): 0) 
        + (margin_draw_power_4!=null? margin_draw_power_4.hashCode(): 0) 
        + (other_trading_power_1!=null? other_trading_power_1.hashCode(): 0) 
        + (other_trading_power_2!=null? other_trading_power_2.hashCode(): 0) 
        + (other_trading_power_3!=null? other_trading_power_3.hashCode(): 0) 
        + (other_trading_power_4!=null? other_trading_power_4.hashCode(): 0) 
        + (demandamount0!=null? demandamount0.hashCode(): 0) 
        + (demandamount1!=null? demandamount1.hashCode(): 0) 
        + (demandamount2!=null? demandamount2.hashCode(): 0) 
        + (demandamount3!=null? demandamount3.hashCode(): 0) 
        + (demandamount4!=null? demandamount4.hashCode(): 0) 
        + (payment_amount_designate0!=null? payment_amount_designate0.hashCode(): 0) 
        + (payment_amount_designate1!=null? payment_amount_designate1.hashCode(): 0) 
        + (payment_amount_designate2!=null? payment_amount_designate2.hashCode(): 0) 
        + (margin_sec_product_code!=null? margin_sec_product_code.hashCode(): 0) 
        + (margin_sec_rate!=null? margin_sec_rate.hashCode(): 0) 
        + (equity_asset_executed!=null? equity_asset_executed.hashCode(): 0) 
        + (ruito_asset_executed!=null? ruito_asset_executed.hashCode(): 0) 
        + (mutual_fund_asset_executed!=null? mutual_fund_asset_executed.hashCode(): 0) 
        + (bond_asset_executed!=null? bond_asset_executed.hashCode(): 0) 
        + (trading_stop!=null? trading_stop.hashCode(): 0) 
        + (margin_open_position_stop!=null? margin_open_position_stop.hashCode(): 0) 
        + (payment_stop!=null? payment_stop.hashCode(): 0) 
        + (other_trading_stop!=null? other_trading_stop.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !work_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'work_date' must be set before inserting.");
		if ( !branch_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'branch_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("calc_result_margin_id",new Long(calc_result_margin_id));
		map.put("account_id",new Long(account_id));
		map.put("institution_code",institution_code);
		map.put("work_date",work_date);
		map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		if ( asset_evaluation_div != null )
			map.put("asset_evaluation_div",asset_evaluation_div);
		if ( account_balance_0 != null )
			map.put("account_balance_0",account_balance_0);
		if ( account_balance_1 != null )
			map.put("account_balance_1",account_balance_1);
		if ( account_balance_2 != null )
			map.put("account_balance_2",account_balance_2);
		if ( account_balance_3 != null )
			map.put("account_balance_3",account_balance_3);
		if ( account_balance_4 != null )
			map.put("account_balance_4",account_balance_4);
		if ( today_unexecuted_amount_1 != null )
			map.put("today_unexecuted_amount_1",today_unexecuted_amount_1);
		if ( today_unexecuted_amount_2 != null )
			map.put("today_unexecuted_amount_2",today_unexecuted_amount_2);
		if ( today_unexecuted_amount_3 != null )
			map.put("today_unexecuted_amount_3",today_unexecuted_amount_3);
		if ( today_unexecuted_amount_4 != null )
			map.put("today_unexecuted_amount_4",today_unexecuted_amount_4);
		if ( day_trade_restraint_0 != null )
			map.put("day_trade_restraint_0",day_trade_restraint_0);
		if ( day_trade_restraint_1 != null )
			map.put("day_trade_restraint_1",day_trade_restraint_1);
		if ( day_trade_restraint_2 != null )
			map.put("day_trade_restraint_2",day_trade_restraint_2);
		if ( day_trade_restraint_3 != null )
			map.put("day_trade_restraint_3",day_trade_restraint_3);
		if ( day_trade_restraint_4 != null )
			map.put("day_trade_restraint_4",day_trade_restraint_4);
		if ( other_restraint_0 != null )
			map.put("other_restraint_0",other_restraint_0);
		if ( other_restraint_1 != null )
			map.put("other_restraint_1",other_restraint_1);
		if ( other_restraint_2 != null )
			map.put("other_restraint_2",other_restraint_2);
		if ( other_restraint_3 != null )
			map.put("other_restraint_3",other_restraint_3);
		if ( other_restraint_4 != null )
			map.put("other_restraint_4",other_restraint_4);
		if ( margin_account_balance_0 != null )
			map.put("margin_account_balance_0",margin_account_balance_0);
		if ( margin_account_balance_1 != null )
			map.put("margin_account_balance_1",margin_account_balance_1);
		if ( margin_account_balance_2 != null )
			map.put("margin_account_balance_2",margin_account_balance_2);
		if ( margin_account_balance_3 != null )
			map.put("margin_account_balance_3",margin_account_balance_3);
		if ( margin_account_balance_4 != null )
			map.put("margin_account_balance_4",margin_account_balance_4);
		if ( substitute_security_asset_0 != null )
			map.put("substitute_security_asset_0",substitute_security_asset_0);
		if ( substitute_security_asset_1 != null )
			map.put("substitute_security_asset_1",substitute_security_asset_1);
		if ( substitute_security_asset_2 != null )
			map.put("substitute_security_asset_2",substitute_security_asset_2);
		if ( substitute_security_asset_3 != null )
			map.put("substitute_security_asset_3",substitute_security_asset_3);
		if ( substitute_security_asset_4 != null )
			map.put("substitute_security_asset_4",substitute_security_asset_4);
		if ( contract_asset_profit_loss != null )
			map.put("contract_asset_profit_loss",contract_asset_profit_loss);
		if ( contract_total_cost != null )
			map.put("contract_total_cost",contract_total_cost);
		if ( undeli_contract_loss_0 != null )
			map.put("undeli_contract_loss_0",undeli_contract_loss_0);
		if ( undeli_contract_loss_1 != null )
			map.put("undeli_contract_loss_1",undeli_contract_loss_1);
		if ( undeli_contract_loss_2 != null )
			map.put("undeli_contract_loss_2",undeli_contract_loss_2);
		if ( undeli_contract_loss_3 != null )
			map.put("undeli_contract_loss_3",undeli_contract_loss_3);
		if ( today_repay_contract_pre_asset != null )
			map.put("today_repay_contract_pre_asset",today_repay_contract_pre_asset);
		if ( contract_amount_day_repay_0 != null )
			map.put("contract_amount_day_repay_0",contract_amount_day_repay_0);
		if ( contract_amount_day_repay_1 != null )
			map.put("contract_amount_day_repay_1",contract_amount_day_repay_1);
		if ( contract_amount_day_repay_2 != null )
			map.put("contract_amount_day_repay_2",contract_amount_day_repay_2);
		if ( contract_amount_day_repay_3 != null )
			map.put("contract_amount_day_repay_3",contract_amount_day_repay_3);
		if ( contract_amount_day_repay_4 != null )
			map.put("contract_amount_day_repay_4",contract_amount_day_repay_4);
		if ( margin_power_0 != null )
			map.put("margin_power_0",margin_power_0);
		if ( margin_power_1 != null )
			map.put("margin_power_1",margin_power_1);
		if ( margin_power_2 != null )
			map.put("margin_power_2",margin_power_2);
		if ( margin_power_3 != null )
			map.put("margin_power_3",margin_power_3);
		if ( margin_power_4 != null )
			map.put("margin_power_4",margin_power_4);
		if ( margin_trading_power_1 != null )
			map.put("margin_trading_power_1",margin_trading_power_1);
		if ( margin_trading_power_2 != null )
			map.put("margin_trading_power_2",margin_trading_power_2);
		if ( margin_trading_power_3 != null )
			map.put("margin_trading_power_3",margin_trading_power_3);
		if ( margin_trading_power_4 != null )
			map.put("margin_trading_power_4",margin_trading_power_4);
		if ( margin_deposit_rate_0 != null )
			map.put("margin_deposit_rate_0",margin_deposit_rate_0);
		if ( margin_deposit_rate_1 != null )
			map.put("margin_deposit_rate_1",margin_deposit_rate_1);
		if ( margin_deposit_rate_2 != null )
			map.put("margin_deposit_rate_2",margin_deposit_rate_2);
		if ( margin_deposit_rate_3 != null )
			map.put("margin_deposit_rate_3",margin_deposit_rate_3);
		if ( margin_deposit_rate_4 != null )
			map.put("margin_deposit_rate_4",margin_deposit_rate_4);
		if ( act_rec_trading_power_3 != null )
			map.put("act_rec_trading_power_3",act_rec_trading_power_3);
		if ( act_rec_trading_power_4 != null )
			map.put("act_rec_trading_power_4",act_rec_trading_power_4);
		if ( act_rec_trading_power_4_dash != null )
			map.put("act_rec_trading_power_4_dash",act_rec_trading_power_4_dash);
		if ( equity_trading_power_3 != null )
			map.put("equity_trading_power_3",equity_trading_power_3);
		if ( equity_trading_power_4 != null )
			map.put("equity_trading_power_4",equity_trading_power_4);
		if ( equity_trading_power_4_dash != null )
			map.put("equity_trading_power_4_dash",equity_trading_power_4_dash);
		if ( undeli_contract_amount_0 != null )
			map.put("undeli_contract_amount_0",undeli_contract_amount_0);
		if ( undeli_contract_amount_1 != null )
			map.put("undeli_contract_amount_1",undeli_contract_amount_1);
		if ( undeli_contract_amount_2 != null )
			map.put("undeli_contract_amount_2",undeli_contract_amount_2);
		if ( undeli_contract_amount_3 != null )
			map.put("undeli_contract_amount_3",undeli_contract_amount_3);
		if ( margin_draw_power_0 != null )
			map.put("margin_draw_power_0",margin_draw_power_0);
		if ( margin_draw_power_1 != null )
			map.put("margin_draw_power_1",margin_draw_power_1);
		if ( margin_draw_power_2 != null )
			map.put("margin_draw_power_2",margin_draw_power_2);
		if ( margin_draw_power_3 != null )
			map.put("margin_draw_power_3",margin_draw_power_3);
		if ( margin_draw_power_4 != null )
			map.put("margin_draw_power_4",margin_draw_power_4);
		if ( other_trading_power_1 != null )
			map.put("other_trading_power_1",other_trading_power_1);
		if ( other_trading_power_2 != null )
			map.put("other_trading_power_2",other_trading_power_2);
		if ( other_trading_power_3 != null )
			map.put("other_trading_power_3",other_trading_power_3);
		if ( other_trading_power_4 != null )
			map.put("other_trading_power_4",other_trading_power_4);
		if ( demandamount0 != null )
			map.put("demandamount0",demandamount0);
		if ( demandamount1 != null )
			map.put("demandamount1",demandamount1);
		if ( demandamount2 != null )
			map.put("demandamount2",demandamount2);
		if ( demandamount3 != null )
			map.put("demandamount3",demandamount3);
		if ( demandamount4 != null )
			map.put("demandamount4",demandamount4);
		if ( payment_amount_designate0 != null )
			map.put("payment_amount_designate0",payment_amount_designate0);
		if ( payment_amount_designate1 != null )
			map.put("payment_amount_designate1",payment_amount_designate1);
		if ( payment_amount_designate2 != null )
			map.put("payment_amount_designate2",payment_amount_designate2);
		if ( margin_sec_product_code != null )
			map.put("margin_sec_product_code",margin_sec_product_code);
		if ( margin_sec_rate != null )
			map.put("margin_sec_rate",margin_sec_rate);
		if ( equity_asset_executed != null )
			map.put("equity_asset_executed",equity_asset_executed);
		if ( ruito_asset_executed != null )
			map.put("ruito_asset_executed",ruito_asset_executed);
		if ( mutual_fund_asset_executed != null )
			map.put("mutual_fund_asset_executed",mutual_fund_asset_executed);
		if ( bond_asset_executed != null )
			map.put("bond_asset_executed",bond_asset_executed);
		if ( trading_stop != null )
			map.put("trading_stop",trading_stop);
		if ( margin_open_position_stop != null )
			map.put("margin_open_position_stop",margin_open_position_stop);
		if ( payment_stop != null )
			map.put("payment_stop",payment_stop);
		if ( other_trading_stop != null )
			map.put("other_trading_stop",other_trading_stop);
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
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( work_date_is_modified )
			map.put("work_date",work_date);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( asset_evaluation_div_is_modified )
			map.put("asset_evaluation_div",asset_evaluation_div);
		if ( account_balance_0_is_modified )
			map.put("account_balance_0",account_balance_0);
		if ( account_balance_1_is_modified )
			map.put("account_balance_1",account_balance_1);
		if ( account_balance_2_is_modified )
			map.put("account_balance_2",account_balance_2);
		if ( account_balance_3_is_modified )
			map.put("account_balance_3",account_balance_3);
		if ( account_balance_4_is_modified )
			map.put("account_balance_4",account_balance_4);
		if ( today_unexecuted_amount_1_is_modified )
			map.put("today_unexecuted_amount_1",today_unexecuted_amount_1);
		if ( today_unexecuted_amount_2_is_modified )
			map.put("today_unexecuted_amount_2",today_unexecuted_amount_2);
		if ( today_unexecuted_amount_3_is_modified )
			map.put("today_unexecuted_amount_3",today_unexecuted_amount_3);
		if ( today_unexecuted_amount_4_is_modified )
			map.put("today_unexecuted_amount_4",today_unexecuted_amount_4);
		if ( day_trade_restraint_0_is_modified )
			map.put("day_trade_restraint_0",day_trade_restraint_0);
		if ( day_trade_restraint_1_is_modified )
			map.put("day_trade_restraint_1",day_trade_restraint_1);
		if ( day_trade_restraint_2_is_modified )
			map.put("day_trade_restraint_2",day_trade_restraint_2);
		if ( day_trade_restraint_3_is_modified )
			map.put("day_trade_restraint_3",day_trade_restraint_3);
		if ( day_trade_restraint_4_is_modified )
			map.put("day_trade_restraint_4",day_trade_restraint_4);
		if ( other_restraint_0_is_modified )
			map.put("other_restraint_0",other_restraint_0);
		if ( other_restraint_1_is_modified )
			map.put("other_restraint_1",other_restraint_1);
		if ( other_restraint_2_is_modified )
			map.put("other_restraint_2",other_restraint_2);
		if ( other_restraint_3_is_modified )
			map.put("other_restraint_3",other_restraint_3);
		if ( other_restraint_4_is_modified )
			map.put("other_restraint_4",other_restraint_4);
		if ( margin_account_balance_0_is_modified )
			map.put("margin_account_balance_0",margin_account_balance_0);
		if ( margin_account_balance_1_is_modified )
			map.put("margin_account_balance_1",margin_account_balance_1);
		if ( margin_account_balance_2_is_modified )
			map.put("margin_account_balance_2",margin_account_balance_2);
		if ( margin_account_balance_3_is_modified )
			map.put("margin_account_balance_3",margin_account_balance_3);
		if ( margin_account_balance_4_is_modified )
			map.put("margin_account_balance_4",margin_account_balance_4);
		if ( substitute_security_asset_0_is_modified )
			map.put("substitute_security_asset_0",substitute_security_asset_0);
		if ( substitute_security_asset_1_is_modified )
			map.put("substitute_security_asset_1",substitute_security_asset_1);
		if ( substitute_security_asset_2_is_modified )
			map.put("substitute_security_asset_2",substitute_security_asset_2);
		if ( substitute_security_asset_3_is_modified )
			map.put("substitute_security_asset_3",substitute_security_asset_3);
		if ( substitute_security_asset_4_is_modified )
			map.put("substitute_security_asset_4",substitute_security_asset_4);
		if ( contract_asset_profit_loss_is_modified )
			map.put("contract_asset_profit_loss",contract_asset_profit_loss);
		if ( contract_total_cost_is_modified )
			map.put("contract_total_cost",contract_total_cost);
		if ( undeli_contract_loss_0_is_modified )
			map.put("undeli_contract_loss_0",undeli_contract_loss_0);
		if ( undeli_contract_loss_1_is_modified )
			map.put("undeli_contract_loss_1",undeli_contract_loss_1);
		if ( undeli_contract_loss_2_is_modified )
			map.put("undeli_contract_loss_2",undeli_contract_loss_2);
		if ( undeli_contract_loss_3_is_modified )
			map.put("undeli_contract_loss_3",undeli_contract_loss_3);
		if ( today_repay_contract_pre_asset_is_modified )
			map.put("today_repay_contract_pre_asset",today_repay_contract_pre_asset);
		if ( contract_amount_day_repay_0_is_modified )
			map.put("contract_amount_day_repay_0",contract_amount_day_repay_0);
		if ( contract_amount_day_repay_1_is_modified )
			map.put("contract_amount_day_repay_1",contract_amount_day_repay_1);
		if ( contract_amount_day_repay_2_is_modified )
			map.put("contract_amount_day_repay_2",contract_amount_day_repay_2);
		if ( contract_amount_day_repay_3_is_modified )
			map.put("contract_amount_day_repay_3",contract_amount_day_repay_3);
		if ( contract_amount_day_repay_4_is_modified )
			map.put("contract_amount_day_repay_4",contract_amount_day_repay_4);
		if ( margin_power_0_is_modified )
			map.put("margin_power_0",margin_power_0);
		if ( margin_power_1_is_modified )
			map.put("margin_power_1",margin_power_1);
		if ( margin_power_2_is_modified )
			map.put("margin_power_2",margin_power_2);
		if ( margin_power_3_is_modified )
			map.put("margin_power_3",margin_power_3);
		if ( margin_power_4_is_modified )
			map.put("margin_power_4",margin_power_4);
		if ( margin_trading_power_1_is_modified )
			map.put("margin_trading_power_1",margin_trading_power_1);
		if ( margin_trading_power_2_is_modified )
			map.put("margin_trading_power_2",margin_trading_power_2);
		if ( margin_trading_power_3_is_modified )
			map.put("margin_trading_power_3",margin_trading_power_3);
		if ( margin_trading_power_4_is_modified )
			map.put("margin_trading_power_4",margin_trading_power_4);
		if ( margin_deposit_rate_0_is_modified )
			map.put("margin_deposit_rate_0",margin_deposit_rate_0);
		if ( margin_deposit_rate_1_is_modified )
			map.put("margin_deposit_rate_1",margin_deposit_rate_1);
		if ( margin_deposit_rate_2_is_modified )
			map.put("margin_deposit_rate_2",margin_deposit_rate_2);
		if ( margin_deposit_rate_3_is_modified )
			map.put("margin_deposit_rate_3",margin_deposit_rate_3);
		if ( margin_deposit_rate_4_is_modified )
			map.put("margin_deposit_rate_4",margin_deposit_rate_4);
		if ( act_rec_trading_power_3_is_modified )
			map.put("act_rec_trading_power_3",act_rec_trading_power_3);
		if ( act_rec_trading_power_4_is_modified )
			map.put("act_rec_trading_power_4",act_rec_trading_power_4);
		if ( act_rec_trading_power_4_dash_is_modified )
			map.put("act_rec_trading_power_4_dash",act_rec_trading_power_4_dash);
		if ( equity_trading_power_3_is_modified )
			map.put("equity_trading_power_3",equity_trading_power_3);
		if ( equity_trading_power_4_is_modified )
			map.put("equity_trading_power_4",equity_trading_power_4);
		if ( equity_trading_power_4_dash_is_modified )
			map.put("equity_trading_power_4_dash",equity_trading_power_4_dash);
		if ( undeli_contract_amount_0_is_modified )
			map.put("undeli_contract_amount_0",undeli_contract_amount_0);
		if ( undeli_contract_amount_1_is_modified )
			map.put("undeli_contract_amount_1",undeli_contract_amount_1);
		if ( undeli_contract_amount_2_is_modified )
			map.put("undeli_contract_amount_2",undeli_contract_amount_2);
		if ( undeli_contract_amount_3_is_modified )
			map.put("undeli_contract_amount_3",undeli_contract_amount_3);
		if ( margin_draw_power_0_is_modified )
			map.put("margin_draw_power_0",margin_draw_power_0);
		if ( margin_draw_power_1_is_modified )
			map.put("margin_draw_power_1",margin_draw_power_1);
		if ( margin_draw_power_2_is_modified )
			map.put("margin_draw_power_2",margin_draw_power_2);
		if ( margin_draw_power_3_is_modified )
			map.put("margin_draw_power_3",margin_draw_power_3);
		if ( margin_draw_power_4_is_modified )
			map.put("margin_draw_power_4",margin_draw_power_4);
		if ( other_trading_power_1_is_modified )
			map.put("other_trading_power_1",other_trading_power_1);
		if ( other_trading_power_2_is_modified )
			map.put("other_trading_power_2",other_trading_power_2);
		if ( other_trading_power_3_is_modified )
			map.put("other_trading_power_3",other_trading_power_3);
		if ( other_trading_power_4_is_modified )
			map.put("other_trading_power_4",other_trading_power_4);
		if ( demandamount0_is_modified )
			map.put("demandamount0",demandamount0);
		if ( demandamount1_is_modified )
			map.put("demandamount1",demandamount1);
		if ( demandamount2_is_modified )
			map.put("demandamount2",demandamount2);
		if ( demandamount3_is_modified )
			map.put("demandamount3",demandamount3);
		if ( demandamount4_is_modified )
			map.put("demandamount4",demandamount4);
		if ( payment_amount_designate0_is_modified )
			map.put("payment_amount_designate0",payment_amount_designate0);
		if ( payment_amount_designate1_is_modified )
			map.put("payment_amount_designate1",payment_amount_designate1);
		if ( payment_amount_designate2_is_modified )
			map.put("payment_amount_designate2",payment_amount_designate2);
		if ( margin_sec_product_code_is_modified )
			map.put("margin_sec_product_code",margin_sec_product_code);
		if ( margin_sec_rate_is_modified )
			map.put("margin_sec_rate",margin_sec_rate);
		if ( equity_asset_executed_is_modified )
			map.put("equity_asset_executed",equity_asset_executed);
		if ( ruito_asset_executed_is_modified )
			map.put("ruito_asset_executed",ruito_asset_executed);
		if ( mutual_fund_asset_executed_is_modified )
			map.put("mutual_fund_asset_executed",mutual_fund_asset_executed);
		if ( bond_asset_executed_is_modified )
			map.put("bond_asset_executed",bond_asset_executed);
		if ( trading_stop_is_modified )
			map.put("trading_stop",trading_stop);
		if ( margin_open_position_stop_is_modified )
			map.put("margin_open_position_stop",margin_open_position_stop);
		if ( payment_stop_is_modified )
			map.put("payment_stop",payment_stop);
		if ( other_trading_stop_is_modified )
			map.put("other_trading_stop",other_trading_stop);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			if ( work_date_is_set )
				map.put("work_date",work_date);
			if ( branch_code_is_set )
				map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			map.put("asset_evaluation_div",asset_evaluation_div);
			map.put("account_balance_0",account_balance_0);
			map.put("account_balance_1",account_balance_1);
			map.put("account_balance_2",account_balance_2);
			map.put("account_balance_3",account_balance_3);
			map.put("account_balance_4",account_balance_4);
			map.put("today_unexecuted_amount_1",today_unexecuted_amount_1);
			map.put("today_unexecuted_amount_2",today_unexecuted_amount_2);
			map.put("today_unexecuted_amount_3",today_unexecuted_amount_3);
			map.put("today_unexecuted_amount_4",today_unexecuted_amount_4);
			map.put("day_trade_restraint_0",day_trade_restraint_0);
			map.put("day_trade_restraint_1",day_trade_restraint_1);
			map.put("day_trade_restraint_2",day_trade_restraint_2);
			map.put("day_trade_restraint_3",day_trade_restraint_3);
			map.put("day_trade_restraint_4",day_trade_restraint_4);
			map.put("other_restraint_0",other_restraint_0);
			map.put("other_restraint_1",other_restraint_1);
			map.put("other_restraint_2",other_restraint_2);
			map.put("other_restraint_3",other_restraint_3);
			map.put("other_restraint_4",other_restraint_4);
			map.put("margin_account_balance_0",margin_account_balance_0);
			map.put("margin_account_balance_1",margin_account_balance_1);
			map.put("margin_account_balance_2",margin_account_balance_2);
			map.put("margin_account_balance_3",margin_account_balance_3);
			map.put("margin_account_balance_4",margin_account_balance_4);
			map.put("substitute_security_asset_0",substitute_security_asset_0);
			map.put("substitute_security_asset_1",substitute_security_asset_1);
			map.put("substitute_security_asset_2",substitute_security_asset_2);
			map.put("substitute_security_asset_3",substitute_security_asset_3);
			map.put("substitute_security_asset_4",substitute_security_asset_4);
			map.put("contract_asset_profit_loss",contract_asset_profit_loss);
			map.put("contract_total_cost",contract_total_cost);
			map.put("undeli_contract_loss_0",undeli_contract_loss_0);
			map.put("undeli_contract_loss_1",undeli_contract_loss_1);
			map.put("undeli_contract_loss_2",undeli_contract_loss_2);
			map.put("undeli_contract_loss_3",undeli_contract_loss_3);
			map.put("today_repay_contract_pre_asset",today_repay_contract_pre_asset);
			map.put("contract_amount_day_repay_0",contract_amount_day_repay_0);
			map.put("contract_amount_day_repay_1",contract_amount_day_repay_1);
			map.put("contract_amount_day_repay_2",contract_amount_day_repay_2);
			map.put("contract_amount_day_repay_3",contract_amount_day_repay_3);
			map.put("contract_amount_day_repay_4",contract_amount_day_repay_4);
			map.put("margin_power_0",margin_power_0);
			map.put("margin_power_1",margin_power_1);
			map.put("margin_power_2",margin_power_2);
			map.put("margin_power_3",margin_power_3);
			map.put("margin_power_4",margin_power_4);
			map.put("margin_trading_power_1",margin_trading_power_1);
			map.put("margin_trading_power_2",margin_trading_power_2);
			map.put("margin_trading_power_3",margin_trading_power_3);
			map.put("margin_trading_power_4",margin_trading_power_4);
			map.put("margin_deposit_rate_0",margin_deposit_rate_0);
			map.put("margin_deposit_rate_1",margin_deposit_rate_1);
			map.put("margin_deposit_rate_2",margin_deposit_rate_2);
			map.put("margin_deposit_rate_3",margin_deposit_rate_3);
			map.put("margin_deposit_rate_4",margin_deposit_rate_4);
			map.put("act_rec_trading_power_3",act_rec_trading_power_3);
			map.put("act_rec_trading_power_4",act_rec_trading_power_4);
			map.put("act_rec_trading_power_4_dash",act_rec_trading_power_4_dash);
			map.put("equity_trading_power_3",equity_trading_power_3);
			map.put("equity_trading_power_4",equity_trading_power_4);
			map.put("equity_trading_power_4_dash",equity_trading_power_4_dash);
			map.put("undeli_contract_amount_0",undeli_contract_amount_0);
			map.put("undeli_contract_amount_1",undeli_contract_amount_1);
			map.put("undeli_contract_amount_2",undeli_contract_amount_2);
			map.put("undeli_contract_amount_3",undeli_contract_amount_3);
			map.put("margin_draw_power_0",margin_draw_power_0);
			map.put("margin_draw_power_1",margin_draw_power_1);
			map.put("margin_draw_power_2",margin_draw_power_2);
			map.put("margin_draw_power_3",margin_draw_power_3);
			map.put("margin_draw_power_4",margin_draw_power_4);
			map.put("other_trading_power_1",other_trading_power_1);
			map.put("other_trading_power_2",other_trading_power_2);
			map.put("other_trading_power_3",other_trading_power_3);
			map.put("other_trading_power_4",other_trading_power_4);
			map.put("demandamount0",demandamount0);
			map.put("demandamount1",demandamount1);
			map.put("demandamount2",demandamount2);
			map.put("demandamount3",demandamount3);
			map.put("demandamount4",demandamount4);
			map.put("payment_amount_designate0",payment_amount_designate0);
			map.put("payment_amount_designate1",payment_amount_designate1);
			map.put("payment_amount_designate2",payment_amount_designate2);
			map.put("margin_sec_product_code",margin_sec_product_code);
			map.put("margin_sec_rate",margin_sec_rate);
			map.put("equity_asset_executed",equity_asset_executed);
			map.put("ruito_asset_executed",ruito_asset_executed);
			map.put("mutual_fund_asset_executed",mutual_fund_asset_executed);
			map.put("bond_asset_executed",bond_asset_executed);
			map.put("trading_stop",trading_stop);
			map.put("margin_open_position_stop",margin_open_position_stop);
			map.put("payment_stop",payment_stop);
			map.put("other_trading_stop",other_trading_stop);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
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
   * <em>work_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getWorkDate()
  {
    return work_date;
  }


  /** 
   * <em>work_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWorkDateIsSet() {
    return work_date_is_set;
  }


  /** 
   * <em>work_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getWorkDateIsModified() {
    return work_date_is_modified;
  }


  /** 
   * <em>branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getBranchCode()
  {
    return branch_code;
  }


  /** 
   * <em>branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsSet() {
    return branch_code_is_set;
  }


  /** 
   * <em>branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getBranchCodeIsModified() {
    return branch_code_is_modified;
  }


  /** 
   * <em>account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountCode()
  {
    return account_code;
  }


  /** 
   * <em>account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsSet() {
    return account_code_is_set;
  }


  /** 
   * <em>account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountCodeIsModified() {
    return account_code_is_modified;
  }


  /** 
   * <em>asset_evaluation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAssetEvaluationDiv()
  {
    return asset_evaluation_div;
  }


  /** 
   * <em>asset_evaluation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAssetEvaluationDivIsSet() {
    return asset_evaluation_div_is_set;
  }


  /** 
   * <em>asset_evaluation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAssetEvaluationDivIsModified() {
    return asset_evaluation_div_is_modified;
  }


  /** 
   * <em>account_balance_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountBalance0()
  {
    return ( account_balance_0==null? ((long)0): account_balance_0.longValue() );
  }


  /** 
   * <em>account_balance_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccountBalance0IsNull()
  {
    return account_balance_0 == null;
  }


  /** 
   * <em>account_balance_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalance0IsSet() {
    return account_balance_0_is_set;
  }


  /** 
   * <em>account_balance_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalance0IsModified() {
    return account_balance_0_is_modified;
  }


  /** 
   * <em>account_balance_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountBalance1()
  {
    return ( account_balance_1==null? ((long)0): account_balance_1.longValue() );
  }


  /** 
   * <em>account_balance_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccountBalance1IsNull()
  {
    return account_balance_1 == null;
  }


  /** 
   * <em>account_balance_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalance1IsSet() {
    return account_balance_1_is_set;
  }


  /** 
   * <em>account_balance_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalance1IsModified() {
    return account_balance_1_is_modified;
  }


  /** 
   * <em>account_balance_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountBalance2()
  {
    return ( account_balance_2==null? ((long)0): account_balance_2.longValue() );
  }


  /** 
   * <em>account_balance_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccountBalance2IsNull()
  {
    return account_balance_2 == null;
  }


  /** 
   * <em>account_balance_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalance2IsSet() {
    return account_balance_2_is_set;
  }


  /** 
   * <em>account_balance_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalance2IsModified() {
    return account_balance_2_is_modified;
  }


  /** 
   * <em>account_balance_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountBalance3()
  {
    return ( account_balance_3==null? ((long)0): account_balance_3.longValue() );
  }


  /** 
   * <em>account_balance_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccountBalance3IsNull()
  {
    return account_balance_3 == null;
  }


  /** 
   * <em>account_balance_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalance3IsSet() {
    return account_balance_3_is_set;
  }


  /** 
   * <em>account_balance_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalance3IsModified() {
    return account_balance_3_is_modified;
  }


  /** 
   * <em>account_balance_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAccountBalance4()
  {
    return ( account_balance_4==null? ((long)0): account_balance_4.longValue() );
  }


  /** 
   * <em>account_balance_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccountBalance4IsNull()
  {
    return account_balance_4 == null;
  }


  /** 
   * <em>account_balance_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalance4IsSet() {
    return account_balance_4_is_set;
  }


  /** 
   * <em>account_balance_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalance4IsModified() {
    return account_balance_4_is_modified;
  }


  /** 
   * <em>today_unexecuted_amount_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getTodayUnexecutedAmount1()
  {
    return ( today_unexecuted_amount_1==null? ((long)0): today_unexecuted_amount_1.longValue() );
  }


  /** 
   * <em>today_unexecuted_amount_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTodayUnexecutedAmount1IsNull()
  {
    return today_unexecuted_amount_1 == null;
  }


  /** 
   * <em>today_unexecuted_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayUnexecutedAmount1IsSet() {
    return today_unexecuted_amount_1_is_set;
  }


  /** 
   * <em>today_unexecuted_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayUnexecutedAmount1IsModified() {
    return today_unexecuted_amount_1_is_modified;
  }


  /** 
   * <em>today_unexecuted_amount_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getTodayUnexecutedAmount2()
  {
    return ( today_unexecuted_amount_2==null? ((long)0): today_unexecuted_amount_2.longValue() );
  }


  /** 
   * <em>today_unexecuted_amount_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTodayUnexecutedAmount2IsNull()
  {
    return today_unexecuted_amount_2 == null;
  }


  /** 
   * <em>today_unexecuted_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayUnexecutedAmount2IsSet() {
    return today_unexecuted_amount_2_is_set;
  }


  /** 
   * <em>today_unexecuted_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayUnexecutedAmount2IsModified() {
    return today_unexecuted_amount_2_is_modified;
  }


  /** 
   * <em>today_unexecuted_amount_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getTodayUnexecutedAmount3()
  {
    return ( today_unexecuted_amount_3==null? ((long)0): today_unexecuted_amount_3.longValue() );
  }


  /** 
   * <em>today_unexecuted_amount_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTodayUnexecutedAmount3IsNull()
  {
    return today_unexecuted_amount_3 == null;
  }


  /** 
   * <em>today_unexecuted_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayUnexecutedAmount3IsSet() {
    return today_unexecuted_amount_3_is_set;
  }


  /** 
   * <em>today_unexecuted_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayUnexecutedAmount3IsModified() {
    return today_unexecuted_amount_3_is_modified;
  }


  /** 
   * <em>today_unexecuted_amount_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getTodayUnexecutedAmount4()
  {
    return ( today_unexecuted_amount_4==null? ((long)0): today_unexecuted_amount_4.longValue() );
  }


  /** 
   * <em>today_unexecuted_amount_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTodayUnexecutedAmount4IsNull()
  {
    return today_unexecuted_amount_4 == null;
  }


  /** 
   * <em>today_unexecuted_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayUnexecutedAmount4IsSet() {
    return today_unexecuted_amount_4_is_set;
  }


  /** 
   * <em>today_unexecuted_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayUnexecutedAmount4IsModified() {
    return today_unexecuted_amount_4_is_modified;
  }


  /** 
   * <em>day_trade_restraint_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getDayTradeRestraint0()
  {
    return ( day_trade_restraint_0==null? ((long)0): day_trade_restraint_0.longValue() );
  }


  /** 
   * <em>day_trade_restraint_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayTradeRestraint0IsNull()
  {
    return day_trade_restraint_0 == null;
  }


  /** 
   * <em>day_trade_restraint_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayTradeRestraint0IsSet() {
    return day_trade_restraint_0_is_set;
  }


  /** 
   * <em>day_trade_restraint_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayTradeRestraint0IsModified() {
    return day_trade_restraint_0_is_modified;
  }


  /** 
   * <em>day_trade_restraint_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getDayTradeRestraint1()
  {
    return ( day_trade_restraint_1==null? ((long)0): day_trade_restraint_1.longValue() );
  }


  /** 
   * <em>day_trade_restraint_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayTradeRestraint1IsNull()
  {
    return day_trade_restraint_1 == null;
  }


  /** 
   * <em>day_trade_restraint_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayTradeRestraint1IsSet() {
    return day_trade_restraint_1_is_set;
  }


  /** 
   * <em>day_trade_restraint_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayTradeRestraint1IsModified() {
    return day_trade_restraint_1_is_modified;
  }


  /** 
   * <em>day_trade_restraint_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getDayTradeRestraint2()
  {
    return ( day_trade_restraint_2==null? ((long)0): day_trade_restraint_2.longValue() );
  }


  /** 
   * <em>day_trade_restraint_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayTradeRestraint2IsNull()
  {
    return day_trade_restraint_2 == null;
  }


  /** 
   * <em>day_trade_restraint_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayTradeRestraint2IsSet() {
    return day_trade_restraint_2_is_set;
  }


  /** 
   * <em>day_trade_restraint_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayTradeRestraint2IsModified() {
    return day_trade_restraint_2_is_modified;
  }


  /** 
   * <em>day_trade_restraint_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getDayTradeRestraint3()
  {
    return ( day_trade_restraint_3==null? ((long)0): day_trade_restraint_3.longValue() );
  }


  /** 
   * <em>day_trade_restraint_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayTradeRestraint3IsNull()
  {
    return day_trade_restraint_3 == null;
  }


  /** 
   * <em>day_trade_restraint_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayTradeRestraint3IsSet() {
    return day_trade_restraint_3_is_set;
  }


  /** 
   * <em>day_trade_restraint_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayTradeRestraint3IsModified() {
    return day_trade_restraint_3_is_modified;
  }


  /** 
   * <em>day_trade_restraint_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getDayTradeRestraint4()
  {
    return ( day_trade_restraint_4==null? ((long)0): day_trade_restraint_4.longValue() );
  }


  /** 
   * <em>day_trade_restraint_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayTradeRestraint4IsNull()
  {
    return day_trade_restraint_4 == null;
  }


  /** 
   * <em>day_trade_restraint_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayTradeRestraint4IsSet() {
    return day_trade_restraint_4_is_set;
  }


  /** 
   * <em>day_trade_restraint_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayTradeRestraint4IsModified() {
    return day_trade_restraint_4_is_modified;
  }


  /** 
   * <em>other_restraint_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOtherRestraint0()
  {
    return ( other_restraint_0==null? ((long)0): other_restraint_0.longValue() );
  }


  /** 
   * <em>other_restraint_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOtherRestraint0IsNull()
  {
    return other_restraint_0 == null;
  }


  /** 
   * <em>other_restraint_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherRestraint0IsSet() {
    return other_restraint_0_is_set;
  }


  /** 
   * <em>other_restraint_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherRestraint0IsModified() {
    return other_restraint_0_is_modified;
  }


  /** 
   * <em>other_restraint_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOtherRestraint1()
  {
    return ( other_restraint_1==null? ((long)0): other_restraint_1.longValue() );
  }


  /** 
   * <em>other_restraint_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOtherRestraint1IsNull()
  {
    return other_restraint_1 == null;
  }


  /** 
   * <em>other_restraint_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherRestraint1IsSet() {
    return other_restraint_1_is_set;
  }


  /** 
   * <em>other_restraint_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherRestraint1IsModified() {
    return other_restraint_1_is_modified;
  }


  /** 
   * <em>other_restraint_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOtherRestraint2()
  {
    return ( other_restraint_2==null? ((long)0): other_restraint_2.longValue() );
  }


  /** 
   * <em>other_restraint_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOtherRestraint2IsNull()
  {
    return other_restraint_2 == null;
  }


  /** 
   * <em>other_restraint_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherRestraint2IsSet() {
    return other_restraint_2_is_set;
  }


  /** 
   * <em>other_restraint_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherRestraint2IsModified() {
    return other_restraint_2_is_modified;
  }


  /** 
   * <em>other_restraint_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOtherRestraint3()
  {
    return ( other_restraint_3==null? ((long)0): other_restraint_3.longValue() );
  }


  /** 
   * <em>other_restraint_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOtherRestraint3IsNull()
  {
    return other_restraint_3 == null;
  }


  /** 
   * <em>other_restraint_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherRestraint3IsSet() {
    return other_restraint_3_is_set;
  }


  /** 
   * <em>other_restraint_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherRestraint3IsModified() {
    return other_restraint_3_is_modified;
  }


  /** 
   * <em>other_restraint_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOtherRestraint4()
  {
    return ( other_restraint_4==null? ((long)0): other_restraint_4.longValue() );
  }


  /** 
   * <em>other_restraint_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOtherRestraint4IsNull()
  {
    return other_restraint_4 == null;
  }


  /** 
   * <em>other_restraint_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherRestraint4IsSet() {
    return other_restraint_4_is_set;
  }


  /** 
   * <em>other_restraint_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherRestraint4IsModified() {
    return other_restraint_4_is_modified;
  }


  /** 
   * <em>margin_account_balance_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginAccountBalance0()
  {
    return ( margin_account_balance_0==null? ((long)0): margin_account_balance_0.longValue() );
  }


  /** 
   * <em>margin_account_balance_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginAccountBalance0IsNull()
  {
    return margin_account_balance_0 == null;
  }


  /** 
   * <em>margin_account_balance_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginAccountBalance0IsSet() {
    return margin_account_balance_0_is_set;
  }


  /** 
   * <em>margin_account_balance_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginAccountBalance0IsModified() {
    return margin_account_balance_0_is_modified;
  }


  /** 
   * <em>margin_account_balance_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginAccountBalance1()
  {
    return ( margin_account_balance_1==null? ((long)0): margin_account_balance_1.longValue() );
  }


  /** 
   * <em>margin_account_balance_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginAccountBalance1IsNull()
  {
    return margin_account_balance_1 == null;
  }


  /** 
   * <em>margin_account_balance_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginAccountBalance1IsSet() {
    return margin_account_balance_1_is_set;
  }


  /** 
   * <em>margin_account_balance_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginAccountBalance1IsModified() {
    return margin_account_balance_1_is_modified;
  }


  /** 
   * <em>margin_account_balance_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginAccountBalance2()
  {
    return ( margin_account_balance_2==null? ((long)0): margin_account_balance_2.longValue() );
  }


  /** 
   * <em>margin_account_balance_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginAccountBalance2IsNull()
  {
    return margin_account_balance_2 == null;
  }


  /** 
   * <em>margin_account_balance_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginAccountBalance2IsSet() {
    return margin_account_balance_2_is_set;
  }


  /** 
   * <em>margin_account_balance_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginAccountBalance2IsModified() {
    return margin_account_balance_2_is_modified;
  }


  /** 
   * <em>margin_account_balance_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginAccountBalance3()
  {
    return ( margin_account_balance_3==null? ((long)0): margin_account_balance_3.longValue() );
  }


  /** 
   * <em>margin_account_balance_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginAccountBalance3IsNull()
  {
    return margin_account_balance_3 == null;
  }


  /** 
   * <em>margin_account_balance_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginAccountBalance3IsSet() {
    return margin_account_balance_3_is_set;
  }


  /** 
   * <em>margin_account_balance_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginAccountBalance3IsModified() {
    return margin_account_balance_3_is_modified;
  }


  /** 
   * <em>margin_account_balance_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginAccountBalance4()
  {
    return ( margin_account_balance_4==null? ((long)0): margin_account_balance_4.longValue() );
  }


  /** 
   * <em>margin_account_balance_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginAccountBalance4IsNull()
  {
    return margin_account_balance_4 == null;
  }


  /** 
   * <em>margin_account_balance_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginAccountBalance4IsSet() {
    return margin_account_balance_4_is_set;
  }


  /** 
   * <em>margin_account_balance_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginAccountBalance4IsModified() {
    return margin_account_balance_4_is_modified;
  }


  /** 
   * <em>substitute_security_asset_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSubstituteSecurityAsset0()
  {
    return ( substitute_security_asset_0==null? ((long)0): substitute_security_asset_0.longValue() );
  }


  /** 
   * <em>substitute_security_asset_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSubstituteSecurityAsset0IsNull()
  {
    return substitute_security_asset_0 == null;
  }


  /** 
   * <em>substitute_security_asset_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteSecurityAsset0IsSet() {
    return substitute_security_asset_0_is_set;
  }


  /** 
   * <em>substitute_security_asset_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteSecurityAsset0IsModified() {
    return substitute_security_asset_0_is_modified;
  }


  /** 
   * <em>substitute_security_asset_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSubstituteSecurityAsset1()
  {
    return ( substitute_security_asset_1==null? ((long)0): substitute_security_asset_1.longValue() );
  }


  /** 
   * <em>substitute_security_asset_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSubstituteSecurityAsset1IsNull()
  {
    return substitute_security_asset_1 == null;
  }


  /** 
   * <em>substitute_security_asset_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteSecurityAsset1IsSet() {
    return substitute_security_asset_1_is_set;
  }


  /** 
   * <em>substitute_security_asset_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteSecurityAsset1IsModified() {
    return substitute_security_asset_1_is_modified;
  }


  /** 
   * <em>substitute_security_asset_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSubstituteSecurityAsset2()
  {
    return ( substitute_security_asset_2==null? ((long)0): substitute_security_asset_2.longValue() );
  }


  /** 
   * <em>substitute_security_asset_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSubstituteSecurityAsset2IsNull()
  {
    return substitute_security_asset_2 == null;
  }


  /** 
   * <em>substitute_security_asset_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteSecurityAsset2IsSet() {
    return substitute_security_asset_2_is_set;
  }


  /** 
   * <em>substitute_security_asset_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteSecurityAsset2IsModified() {
    return substitute_security_asset_2_is_modified;
  }


  /** 
   * <em>substitute_security_asset_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSubstituteSecurityAsset3()
  {
    return ( substitute_security_asset_3==null? ((long)0): substitute_security_asset_3.longValue() );
  }


  /** 
   * <em>substitute_security_asset_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSubstituteSecurityAsset3IsNull()
  {
    return substitute_security_asset_3 == null;
  }


  /** 
   * <em>substitute_security_asset_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteSecurityAsset3IsSet() {
    return substitute_security_asset_3_is_set;
  }


  /** 
   * <em>substitute_security_asset_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteSecurityAsset3IsModified() {
    return substitute_security_asset_3_is_modified;
  }


  /** 
   * <em>substitute_security_asset_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getSubstituteSecurityAsset4()
  {
    return ( substitute_security_asset_4==null? ((long)0): substitute_security_asset_4.longValue() );
  }


  /** 
   * <em>substitute_security_asset_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getSubstituteSecurityAsset4IsNull()
  {
    return substitute_security_asset_4 == null;
  }


  /** 
   * <em>substitute_security_asset_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteSecurityAsset4IsSet() {
    return substitute_security_asset_4_is_set;
  }


  /** 
   * <em>substitute_security_asset_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteSecurityAsset4IsModified() {
    return substitute_security_asset_4_is_modified;
  }


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getContractAssetProfitLoss()
  {
    return ( contract_asset_profit_loss==null? ((long)0): contract_asset_profit_loss.longValue() );
  }


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetProfitLossIsNull()
  {
    return contract_asset_profit_loss == null;
  }


  /** 
   * <em>contract_asset_profit_loss</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitLossIsSet() {
    return contract_asset_profit_loss_is_set;
  }


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitLossIsModified() {
    return contract_asset_profit_loss_is_modified;
  }


  /** 
   * <em>contract_total_cost</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getContractTotalCost()
  {
    return ( contract_total_cost==null? ((long)0): contract_total_cost.longValue() );
  }


  /** 
   * <em>contract_total_cost</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractTotalCostIsNull()
  {
    return contract_total_cost == null;
  }


  /** 
   * <em>contract_total_cost</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractTotalCostIsSet() {
    return contract_total_cost_is_set;
  }


  /** 
   * <em>contract_total_cost</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractTotalCostIsModified() {
    return contract_total_cost_is_modified;
  }


  /** 
   * <em>undeli_contract_loss_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getUndeliContractLoss0()
  {
    return ( undeli_contract_loss_0==null? ((long)0): undeli_contract_loss_0.longValue() );
  }


  /** 
   * <em>undeli_contract_loss_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUndeliContractLoss0IsNull()
  {
    return undeli_contract_loss_0 == null;
  }


  /** 
   * <em>undeli_contract_loss_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractLoss0IsSet() {
    return undeli_contract_loss_0_is_set;
  }


  /** 
   * <em>undeli_contract_loss_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractLoss0IsModified() {
    return undeli_contract_loss_0_is_modified;
  }


  /** 
   * <em>undeli_contract_loss_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getUndeliContractLoss1()
  {
    return ( undeli_contract_loss_1==null? ((long)0): undeli_contract_loss_1.longValue() );
  }


  /** 
   * <em>undeli_contract_loss_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUndeliContractLoss1IsNull()
  {
    return undeli_contract_loss_1 == null;
  }


  /** 
   * <em>undeli_contract_loss_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractLoss1IsSet() {
    return undeli_contract_loss_1_is_set;
  }


  /** 
   * <em>undeli_contract_loss_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractLoss1IsModified() {
    return undeli_contract_loss_1_is_modified;
  }


  /** 
   * <em>undeli_contract_loss_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getUndeliContractLoss2()
  {
    return ( undeli_contract_loss_2==null? ((long)0): undeli_contract_loss_2.longValue() );
  }


  /** 
   * <em>undeli_contract_loss_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUndeliContractLoss2IsNull()
  {
    return undeli_contract_loss_2 == null;
  }


  /** 
   * <em>undeli_contract_loss_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractLoss2IsSet() {
    return undeli_contract_loss_2_is_set;
  }


  /** 
   * <em>undeli_contract_loss_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractLoss2IsModified() {
    return undeli_contract_loss_2_is_modified;
  }


  /** 
   * <em>undeli_contract_loss_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getUndeliContractLoss3()
  {
    return ( undeli_contract_loss_3==null? ((long)0): undeli_contract_loss_3.longValue() );
  }


  /** 
   * <em>undeli_contract_loss_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUndeliContractLoss3IsNull()
  {
    return undeli_contract_loss_3 == null;
  }


  /** 
   * <em>undeli_contract_loss_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractLoss3IsSet() {
    return undeli_contract_loss_3_is_set;
  }


  /** 
   * <em>undeli_contract_loss_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractLoss3IsModified() {
    return undeli_contract_loss_3_is_modified;
  }


  /** 
   * <em>today_repay_contract_pre_asset</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getTodayRepayContractPreAsset()
  {
    return ( today_repay_contract_pre_asset==null? ((long)0): today_repay_contract_pre_asset.longValue() );
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
   * <em>contract_amount_day_repay_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getContractAmountDayRepay0()
  {
    return ( contract_amount_day_repay_0==null? ((long)0): contract_amount_day_repay_0.longValue() );
  }


  /** 
   * <em>contract_amount_day_repay_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAmountDayRepay0IsNull()
  {
    return contract_amount_day_repay_0 == null;
  }


  /** 
   * <em>contract_amount_day_repay_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmountDayRepay0IsSet() {
    return contract_amount_day_repay_0_is_set;
  }


  /** 
   * <em>contract_amount_day_repay_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmountDayRepay0IsModified() {
    return contract_amount_day_repay_0_is_modified;
  }


  /** 
   * <em>contract_amount_day_repay_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getContractAmountDayRepay1()
  {
    return ( contract_amount_day_repay_1==null? ((long)0): contract_amount_day_repay_1.longValue() );
  }


  /** 
   * <em>contract_amount_day_repay_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAmountDayRepay1IsNull()
  {
    return contract_amount_day_repay_1 == null;
  }


  /** 
   * <em>contract_amount_day_repay_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmountDayRepay1IsSet() {
    return contract_amount_day_repay_1_is_set;
  }


  /** 
   * <em>contract_amount_day_repay_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmountDayRepay1IsModified() {
    return contract_amount_day_repay_1_is_modified;
  }


  /** 
   * <em>contract_amount_day_repay_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getContractAmountDayRepay2()
  {
    return ( contract_amount_day_repay_2==null? ((long)0): contract_amount_day_repay_2.longValue() );
  }


  /** 
   * <em>contract_amount_day_repay_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAmountDayRepay2IsNull()
  {
    return contract_amount_day_repay_2 == null;
  }


  /** 
   * <em>contract_amount_day_repay_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmountDayRepay2IsSet() {
    return contract_amount_day_repay_2_is_set;
  }


  /** 
   * <em>contract_amount_day_repay_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmountDayRepay2IsModified() {
    return contract_amount_day_repay_2_is_modified;
  }


  /** 
   * <em>contract_amount_day_repay_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getContractAmountDayRepay3()
  {
    return ( contract_amount_day_repay_3==null? ((long)0): contract_amount_day_repay_3.longValue() );
  }


  /** 
   * <em>contract_amount_day_repay_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAmountDayRepay3IsNull()
  {
    return contract_amount_day_repay_3 == null;
  }


  /** 
   * <em>contract_amount_day_repay_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmountDayRepay3IsSet() {
    return contract_amount_day_repay_3_is_set;
  }


  /** 
   * <em>contract_amount_day_repay_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmountDayRepay3IsModified() {
    return contract_amount_day_repay_3_is_modified;
  }


  /** 
   * <em>contract_amount_day_repay_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getContractAmountDayRepay4()
  {
    return ( contract_amount_day_repay_4==null? ((long)0): contract_amount_day_repay_4.longValue() );
  }


  /** 
   * <em>contract_amount_day_repay_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAmountDayRepay4IsNull()
  {
    return contract_amount_day_repay_4 == null;
  }


  /** 
   * <em>contract_amount_day_repay_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmountDayRepay4IsSet() {
    return contract_amount_day_repay_4_is_set;
  }


  /** 
   * <em>contract_amount_day_repay_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmountDayRepay4IsModified() {
    return contract_amount_day_repay_4_is_modified;
  }


  /** 
   * <em>margin_power_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginPower0()
  {
    return ( margin_power_0==null? ((long)0): margin_power_0.longValue() );
  }


  /** 
   * <em>margin_power_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginPower0IsNull()
  {
    return margin_power_0 == null;
  }


  /** 
   * <em>margin_power_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginPower0IsSet() {
    return margin_power_0_is_set;
  }


  /** 
   * <em>margin_power_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginPower0IsModified() {
    return margin_power_0_is_modified;
  }


  /** 
   * <em>margin_power_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginPower1()
  {
    return ( margin_power_1==null? ((long)0): margin_power_1.longValue() );
  }


  /** 
   * <em>margin_power_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginPower1IsNull()
  {
    return margin_power_1 == null;
  }


  /** 
   * <em>margin_power_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginPower1IsSet() {
    return margin_power_1_is_set;
  }


  /** 
   * <em>margin_power_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginPower1IsModified() {
    return margin_power_1_is_modified;
  }


  /** 
   * <em>margin_power_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginPower2()
  {
    return ( margin_power_2==null? ((long)0): margin_power_2.longValue() );
  }


  /** 
   * <em>margin_power_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginPower2IsNull()
  {
    return margin_power_2 == null;
  }


  /** 
   * <em>margin_power_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginPower2IsSet() {
    return margin_power_2_is_set;
  }


  /** 
   * <em>margin_power_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginPower2IsModified() {
    return margin_power_2_is_modified;
  }


  /** 
   * <em>margin_power_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginPower3()
  {
    return ( margin_power_3==null? ((long)0): margin_power_3.longValue() );
  }


  /** 
   * <em>margin_power_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginPower3IsNull()
  {
    return margin_power_3 == null;
  }


  /** 
   * <em>margin_power_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginPower3IsSet() {
    return margin_power_3_is_set;
  }


  /** 
   * <em>margin_power_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginPower3IsModified() {
    return margin_power_3_is_modified;
  }


  /** 
   * <em>margin_power_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginPower4()
  {
    return ( margin_power_4==null? ((long)0): margin_power_4.longValue() );
  }


  /** 
   * <em>margin_power_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginPower4IsNull()
  {
    return margin_power_4 == null;
  }


  /** 
   * <em>margin_power_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginPower4IsSet() {
    return margin_power_4_is_set;
  }


  /** 
   * <em>margin_power_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginPower4IsModified() {
    return margin_power_4_is_modified;
  }


  /** 
   * <em>margin_trading_power_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginTradingPower1()
  {
    return ( margin_trading_power_1==null? ((long)0): margin_trading_power_1.longValue() );
  }


  /** 
   * <em>margin_trading_power_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginTradingPower1IsNull()
  {
    return margin_trading_power_1 == null;
  }


  /** 
   * <em>margin_trading_power_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTradingPower1IsSet() {
    return margin_trading_power_1_is_set;
  }


  /** 
   * <em>margin_trading_power_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTradingPower1IsModified() {
    return margin_trading_power_1_is_modified;
  }


  /** 
   * <em>margin_trading_power_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginTradingPower2()
  {
    return ( margin_trading_power_2==null? ((long)0): margin_trading_power_2.longValue() );
  }


  /** 
   * <em>margin_trading_power_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginTradingPower2IsNull()
  {
    return margin_trading_power_2 == null;
  }


  /** 
   * <em>margin_trading_power_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTradingPower2IsSet() {
    return margin_trading_power_2_is_set;
  }


  /** 
   * <em>margin_trading_power_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTradingPower2IsModified() {
    return margin_trading_power_2_is_modified;
  }


  /** 
   * <em>margin_trading_power_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginTradingPower3()
  {
    return ( margin_trading_power_3==null? ((long)0): margin_trading_power_3.longValue() );
  }


  /** 
   * <em>margin_trading_power_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginTradingPower3IsNull()
  {
    return margin_trading_power_3 == null;
  }


  /** 
   * <em>margin_trading_power_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTradingPower3IsSet() {
    return margin_trading_power_3_is_set;
  }


  /** 
   * <em>margin_trading_power_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTradingPower3IsModified() {
    return margin_trading_power_3_is_modified;
  }


  /** 
   * <em>margin_trading_power_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginTradingPower4()
  {
    return ( margin_trading_power_4==null? ((long)0): margin_trading_power_4.longValue() );
  }


  /** 
   * <em>margin_trading_power_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginTradingPower4IsNull()
  {
    return margin_trading_power_4 == null;
  }


  /** 
   * <em>margin_trading_power_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTradingPower4IsSet() {
    return margin_trading_power_4_is_set;
  }


  /** 
   * <em>margin_trading_power_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginTradingPower4IsModified() {
    return margin_trading_power_4_is_modified;
  }


  /** 
   * <em>margin_deposit_rate_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginDepositRate0()
  {
    return ( margin_deposit_rate_0==null? ((double)0): margin_deposit_rate_0.doubleValue() );
  }


  /** 
   * <em>margin_deposit_rate_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginDepositRate0IsNull()
  {
    return margin_deposit_rate_0 == null;
  }


  /** 
   * <em>margin_deposit_rate_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRate0IsSet() {
    return margin_deposit_rate_0_is_set;
  }


  /** 
   * <em>margin_deposit_rate_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRate0IsModified() {
    return margin_deposit_rate_0_is_modified;
  }


  /** 
   * <em>margin_deposit_rate_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginDepositRate1()
  {
    return ( margin_deposit_rate_1==null? ((double)0): margin_deposit_rate_1.doubleValue() );
  }


  /** 
   * <em>margin_deposit_rate_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginDepositRate1IsNull()
  {
    return margin_deposit_rate_1 == null;
  }


  /** 
   * <em>margin_deposit_rate_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRate1IsSet() {
    return margin_deposit_rate_1_is_set;
  }


  /** 
   * <em>margin_deposit_rate_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRate1IsModified() {
    return margin_deposit_rate_1_is_modified;
  }


  /** 
   * <em>margin_deposit_rate_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginDepositRate2()
  {
    return ( margin_deposit_rate_2==null? ((double)0): margin_deposit_rate_2.doubleValue() );
  }


  /** 
   * <em>margin_deposit_rate_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginDepositRate2IsNull()
  {
    return margin_deposit_rate_2 == null;
  }


  /** 
   * <em>margin_deposit_rate_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRate2IsSet() {
    return margin_deposit_rate_2_is_set;
  }


  /** 
   * <em>margin_deposit_rate_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRate2IsModified() {
    return margin_deposit_rate_2_is_modified;
  }


  /** 
   * <em>margin_deposit_rate_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginDepositRate3()
  {
    return ( margin_deposit_rate_3==null? ((double)0): margin_deposit_rate_3.doubleValue() );
  }


  /** 
   * <em>margin_deposit_rate_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginDepositRate3IsNull()
  {
    return margin_deposit_rate_3 == null;
  }


  /** 
   * <em>margin_deposit_rate_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRate3IsSet() {
    return margin_deposit_rate_3_is_set;
  }


  /** 
   * <em>margin_deposit_rate_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRate3IsModified() {
    return margin_deposit_rate_3_is_modified;
  }


  /** 
   * <em>margin_deposit_rate_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginDepositRate4()
  {
    return ( margin_deposit_rate_4==null? ((double)0): margin_deposit_rate_4.doubleValue() );
  }


  /** 
   * <em>margin_deposit_rate_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginDepositRate4IsNull()
  {
    return margin_deposit_rate_4 == null;
  }


  /** 
   * <em>margin_deposit_rate_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRate4IsSet() {
    return margin_deposit_rate_4_is_set;
  }


  /** 
   * <em>margin_deposit_rate_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRate4IsModified() {
    return margin_deposit_rate_4_is_modified;
  }


  /** 
   * <em>act_rec_trading_power_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getActRecTradingPower3()
  {
    return ( act_rec_trading_power_3==null? ((long)0): act_rec_trading_power_3.longValue() );
  }


  /** 
   * <em>act_rec_trading_power_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getActRecTradingPower3IsNull()
  {
    return act_rec_trading_power_3 == null;
  }


  /** 
   * <em>act_rec_trading_power_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActRecTradingPower3IsSet() {
    return act_rec_trading_power_3_is_set;
  }


  /** 
   * <em>act_rec_trading_power_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActRecTradingPower3IsModified() {
    return act_rec_trading_power_3_is_modified;
  }


  /** 
   * <em>act_rec_trading_power_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getActRecTradingPower4()
  {
    return ( act_rec_trading_power_4==null? ((long)0): act_rec_trading_power_4.longValue() );
  }


  /** 
   * <em>act_rec_trading_power_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getActRecTradingPower4IsNull()
  {
    return act_rec_trading_power_4 == null;
  }


  /** 
   * <em>act_rec_trading_power_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActRecTradingPower4IsSet() {
    return act_rec_trading_power_4_is_set;
  }


  /** 
   * <em>act_rec_trading_power_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActRecTradingPower4IsModified() {
    return act_rec_trading_power_4_is_modified;
  }


  /** 
   * <em>act_rec_trading_power_4_dash</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getActRecTradingPower4Dash()
  {
    return ( act_rec_trading_power_4_dash==null? ((long)0): act_rec_trading_power_4_dash.longValue() );
  }


  /** 
   * <em>act_rec_trading_power_4_dash</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getActRecTradingPower4DashIsNull()
  {
    return act_rec_trading_power_4_dash == null;
  }


  /** 
   * <em>act_rec_trading_power_4_dash</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActRecTradingPower4DashIsSet() {
    return act_rec_trading_power_4_dash_is_set;
  }


  /** 
   * <em>act_rec_trading_power_4_dash</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActRecTradingPower4DashIsModified() {
    return act_rec_trading_power_4_dash_is_modified;
  }


  /** 
   * <em>equity_trading_power_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getEquityTradingPower3()
  {
    return ( equity_trading_power_3==null? ((long)0): equity_trading_power_3.longValue() );
  }


  /** 
   * <em>equity_trading_power_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getEquityTradingPower3IsNull()
  {
    return equity_trading_power_3 == null;
  }


  /** 
   * <em>equity_trading_power_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityTradingPower3IsSet() {
    return equity_trading_power_3_is_set;
  }


  /** 
   * <em>equity_trading_power_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityTradingPower3IsModified() {
    return equity_trading_power_3_is_modified;
  }


  /** 
   * <em>equity_trading_power_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getEquityTradingPower4()
  {
    return ( equity_trading_power_4==null? ((long)0): equity_trading_power_4.longValue() );
  }


  /** 
   * <em>equity_trading_power_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getEquityTradingPower4IsNull()
  {
    return equity_trading_power_4 == null;
  }


  /** 
   * <em>equity_trading_power_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityTradingPower4IsSet() {
    return equity_trading_power_4_is_set;
  }


  /** 
   * <em>equity_trading_power_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityTradingPower4IsModified() {
    return equity_trading_power_4_is_modified;
  }


  /** 
   * <em>equity_trading_power_4_dash</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getEquityTradingPower4Dash()
  {
    return ( equity_trading_power_4_dash==null? ((long)0): equity_trading_power_4_dash.longValue() );
  }


  /** 
   * <em>equity_trading_power_4_dash</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getEquityTradingPower4DashIsNull()
  {
    return equity_trading_power_4_dash == null;
  }


  /** 
   * <em>equity_trading_power_4_dash</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityTradingPower4DashIsSet() {
    return equity_trading_power_4_dash_is_set;
  }


  /** 
   * <em>equity_trading_power_4_dash</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getEquityTradingPower4DashIsModified() {
    return equity_trading_power_4_dash_is_modified;
  }


  /** 
   * <em>undeli_contract_amount_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getUndeliContractAmount0()
  {
    return ( undeli_contract_amount_0==null? ((long)0): undeli_contract_amount_0.longValue() );
  }


  /** 
   * <em>undeli_contract_amount_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUndeliContractAmount0IsNull()
  {
    return undeli_contract_amount_0 == null;
  }


  /** 
   * <em>undeli_contract_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractAmount0IsSet() {
    return undeli_contract_amount_0_is_set;
  }


  /** 
   * <em>undeli_contract_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractAmount0IsModified() {
    return undeli_contract_amount_0_is_modified;
  }


  /** 
   * <em>undeli_contract_amount_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getUndeliContractAmount1()
  {
    return ( undeli_contract_amount_1==null? ((long)0): undeli_contract_amount_1.longValue() );
  }


  /** 
   * <em>undeli_contract_amount_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUndeliContractAmount1IsNull()
  {
    return undeli_contract_amount_1 == null;
  }


  /** 
   * <em>undeli_contract_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractAmount1IsSet() {
    return undeli_contract_amount_1_is_set;
  }


  /** 
   * <em>undeli_contract_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractAmount1IsModified() {
    return undeli_contract_amount_1_is_modified;
  }


  /** 
   * <em>undeli_contract_amount_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getUndeliContractAmount2()
  {
    return ( undeli_contract_amount_2==null? ((long)0): undeli_contract_amount_2.longValue() );
  }


  /** 
   * <em>undeli_contract_amount_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUndeliContractAmount2IsNull()
  {
    return undeli_contract_amount_2 == null;
  }


  /** 
   * <em>undeli_contract_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractAmount2IsSet() {
    return undeli_contract_amount_2_is_set;
  }


  /** 
   * <em>undeli_contract_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractAmount2IsModified() {
    return undeli_contract_amount_2_is_modified;
  }


  /** 
   * <em>undeli_contract_amount_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getUndeliContractAmount3()
  {
    return ( undeli_contract_amount_3==null? ((long)0): undeli_contract_amount_3.longValue() );
  }


  /** 
   * <em>undeli_contract_amount_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUndeliContractAmount3IsNull()
  {
    return undeli_contract_amount_3 == null;
  }


  /** 
   * <em>undeli_contract_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractAmount3IsSet() {
    return undeli_contract_amount_3_is_set;
  }


  /** 
   * <em>undeli_contract_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractAmount3IsModified() {
    return undeli_contract_amount_3_is_modified;
  }


  /** 
   * <em>margin_draw_power_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginDrawPower0()
  {
    return ( margin_draw_power_0==null? ((long)0): margin_draw_power_0.longValue() );
  }


  /** 
   * <em>margin_draw_power_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginDrawPower0IsNull()
  {
    return margin_draw_power_0 == null;
  }


  /** 
   * <em>margin_draw_power_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDrawPower0IsSet() {
    return margin_draw_power_0_is_set;
  }


  /** 
   * <em>margin_draw_power_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDrawPower0IsModified() {
    return margin_draw_power_0_is_modified;
  }


  /** 
   * <em>margin_draw_power_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginDrawPower1()
  {
    return ( margin_draw_power_1==null? ((long)0): margin_draw_power_1.longValue() );
  }


  /** 
   * <em>margin_draw_power_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginDrawPower1IsNull()
  {
    return margin_draw_power_1 == null;
  }


  /** 
   * <em>margin_draw_power_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDrawPower1IsSet() {
    return margin_draw_power_1_is_set;
  }


  /** 
   * <em>margin_draw_power_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDrawPower1IsModified() {
    return margin_draw_power_1_is_modified;
  }


  /** 
   * <em>margin_draw_power_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginDrawPower2()
  {
    return ( margin_draw_power_2==null? ((long)0): margin_draw_power_2.longValue() );
  }


  /** 
   * <em>margin_draw_power_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginDrawPower2IsNull()
  {
    return margin_draw_power_2 == null;
  }


  /** 
   * <em>margin_draw_power_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDrawPower2IsSet() {
    return margin_draw_power_2_is_set;
  }


  /** 
   * <em>margin_draw_power_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDrawPower2IsModified() {
    return margin_draw_power_2_is_modified;
  }


  /** 
   * <em>margin_draw_power_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginDrawPower3()
  {
    return ( margin_draw_power_3==null? ((long)0): margin_draw_power_3.longValue() );
  }


  /** 
   * <em>margin_draw_power_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginDrawPower3IsNull()
  {
    return margin_draw_power_3 == null;
  }


  /** 
   * <em>margin_draw_power_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDrawPower3IsSet() {
    return margin_draw_power_3_is_set;
  }


  /** 
   * <em>margin_draw_power_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDrawPower3IsModified() {
    return margin_draw_power_3_is_modified;
  }


  /** 
   * <em>margin_draw_power_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarginDrawPower4()
  {
    return ( margin_draw_power_4==null? ((long)0): margin_draw_power_4.longValue() );
  }


  /** 
   * <em>margin_draw_power_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginDrawPower4IsNull()
  {
    return margin_draw_power_4 == null;
  }


  /** 
   * <em>margin_draw_power_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDrawPower4IsSet() {
    return margin_draw_power_4_is_set;
  }


  /** 
   * <em>margin_draw_power_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDrawPower4IsModified() {
    return margin_draw_power_4_is_modified;
  }


  /** 
   * <em>other_trading_power_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOtherTradingPower1()
  {
    return ( other_trading_power_1==null? ((long)0): other_trading_power_1.longValue() );
  }


  /** 
   * <em>other_trading_power_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOtherTradingPower1IsNull()
  {
    return other_trading_power_1 == null;
  }


  /** 
   * <em>other_trading_power_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherTradingPower1IsSet() {
    return other_trading_power_1_is_set;
  }


  /** 
   * <em>other_trading_power_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherTradingPower1IsModified() {
    return other_trading_power_1_is_modified;
  }


  /** 
   * <em>other_trading_power_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOtherTradingPower2()
  {
    return ( other_trading_power_2==null? ((long)0): other_trading_power_2.longValue() );
  }


  /** 
   * <em>other_trading_power_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOtherTradingPower2IsNull()
  {
    return other_trading_power_2 == null;
  }


  /** 
   * <em>other_trading_power_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherTradingPower2IsSet() {
    return other_trading_power_2_is_set;
  }


  /** 
   * <em>other_trading_power_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherTradingPower2IsModified() {
    return other_trading_power_2_is_modified;
  }


  /** 
   * <em>other_trading_power_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOtherTradingPower3()
  {
    return ( other_trading_power_3==null? ((long)0): other_trading_power_3.longValue() );
  }


  /** 
   * <em>other_trading_power_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOtherTradingPower3IsNull()
  {
    return other_trading_power_3 == null;
  }


  /** 
   * <em>other_trading_power_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherTradingPower3IsSet() {
    return other_trading_power_3_is_set;
  }


  /** 
   * <em>other_trading_power_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherTradingPower3IsModified() {
    return other_trading_power_3_is_modified;
  }


  /** 
   * <em>other_trading_power_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getOtherTradingPower4()
  {
    return ( other_trading_power_4==null? ((long)0): other_trading_power_4.longValue() );
  }


  /** 
   * <em>other_trading_power_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOtherTradingPower4IsNull()
  {
    return other_trading_power_4 == null;
  }


  /** 
   * <em>other_trading_power_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherTradingPower4IsSet() {
    return other_trading_power_4_is_set;
  }


  /** 
   * <em>other_trading_power_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherTradingPower4IsModified() {
    return other_trading_power_4_is_modified;
  }


  /** 
   * <em>demandamount0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getDemandamount0()
  {
    return ( demandamount0==null? ((long)0): demandamount0.longValue() );
  }


  /** 
   * <em>demandamount0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDemandamount0IsNull()
  {
    return demandamount0 == null;
  }


  /** 
   * <em>demandamount0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDemandamount0IsSet() {
    return demandamount0_is_set;
  }


  /** 
   * <em>demandamount0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDemandamount0IsModified() {
    return demandamount0_is_modified;
  }


  /** 
   * <em>demandamount1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getDemandamount1()
  {
    return ( demandamount1==null? ((long)0): demandamount1.longValue() );
  }


  /** 
   * <em>demandamount1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDemandamount1IsNull()
  {
    return demandamount1 == null;
  }


  /** 
   * <em>demandamount1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDemandamount1IsSet() {
    return demandamount1_is_set;
  }


  /** 
   * <em>demandamount1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDemandamount1IsModified() {
    return demandamount1_is_modified;
  }


  /** 
   * <em>demandamount2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getDemandamount2()
  {
    return ( demandamount2==null? ((long)0): demandamount2.longValue() );
  }


  /** 
   * <em>demandamount2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDemandamount2IsNull()
  {
    return demandamount2 == null;
  }


  /** 
   * <em>demandamount2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDemandamount2IsSet() {
    return demandamount2_is_set;
  }


  /** 
   * <em>demandamount2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDemandamount2IsModified() {
    return demandamount2_is_modified;
  }


  /** 
   * <em>demandamount3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getDemandamount3()
  {
    return ( demandamount3==null? ((long)0): demandamount3.longValue() );
  }


  /** 
   * <em>demandamount3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDemandamount3IsNull()
  {
    return demandamount3 == null;
  }


  /** 
   * <em>demandamount3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDemandamount3IsSet() {
    return demandamount3_is_set;
  }


  /** 
   * <em>demandamount3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDemandamount3IsModified() {
    return demandamount3_is_modified;
  }


  /** 
   * <em>demandamount4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getDemandamount4()
  {
    return ( demandamount4==null? ((long)0): demandamount4.longValue() );
  }


  /** 
   * <em>demandamount4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDemandamount4IsNull()
  {
    return demandamount4 == null;
  }


  /** 
   * <em>demandamount4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDemandamount4IsSet() {
    return demandamount4_is_set;
  }


  /** 
   * <em>demandamount4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDemandamount4IsModified() {
    return demandamount4_is_modified;
  }


  /** 
   * <em>payment_amount_designate0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getPaymentAmountDesignate0()
  {
    return ( payment_amount_designate0==null? ((long)0): payment_amount_designate0.longValue() );
  }


  /** 
   * <em>payment_amount_designate0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentAmountDesignate0IsNull()
  {
    return payment_amount_designate0 == null;
  }


  /** 
   * <em>payment_amount_designate0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate0IsSet() {
    return payment_amount_designate0_is_set;
  }


  /** 
   * <em>payment_amount_designate0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate0IsModified() {
    return payment_amount_designate0_is_modified;
  }


  /** 
   * <em>payment_amount_designate1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getPaymentAmountDesignate1()
  {
    return ( payment_amount_designate1==null? ((long)0): payment_amount_designate1.longValue() );
  }


  /** 
   * <em>payment_amount_designate1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentAmountDesignate1IsNull()
  {
    return payment_amount_designate1 == null;
  }


  /** 
   * <em>payment_amount_designate1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate1IsSet() {
    return payment_amount_designate1_is_set;
  }


  /** 
   * <em>payment_amount_designate1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate1IsModified() {
    return payment_amount_designate1_is_modified;
  }


  /** 
   * <em>payment_amount_designate2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getPaymentAmountDesignate2()
  {
    return ( payment_amount_designate2==null? ((long)0): payment_amount_designate2.longValue() );
  }


  /** 
   * <em>payment_amount_designate2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentAmountDesignate2IsNull()
  {
    return payment_amount_designate2 == null;
  }


  /** 
   * <em>payment_amount_designate2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate2IsSet() {
    return payment_amount_designate2_is_set;
  }


  /** 
   * <em>payment_amount_designate2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentAmountDesignate2IsModified() {
    return payment_amount_designate2_is_modified;
  }


  /** 
   * <em>margin_sec_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginSecProductCode()
  {
    return margin_sec_product_code;
  }


  /** 
   * <em>margin_sec_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSecProductCodeIsSet() {
    return margin_sec_product_code_is_set;
  }


  /** 
   * <em>margin_sec_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSecProductCodeIsModified() {
    return margin_sec_product_code_is_modified;
  }


  /** 
   * <em>margin_sec_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginSecRate()
  {
    return ( margin_sec_rate==null? ((double)0): margin_sec_rate.doubleValue() );
  }


  /** 
   * <em>margin_sec_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarginSecRateIsNull()
  {
    return margin_sec_rate == null;
  }


  /** 
   * <em>margin_sec_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSecRateIsSet() {
    return margin_sec_rate_is_set;
  }


  /** 
   * <em>margin_sec_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginSecRateIsModified() {
    return margin_sec_rate_is_modified;
  }


  /** 
   * <em>equity_asset_executed</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getEquityAssetExecuted()
  {
    return ( equity_asset_executed==null? ((long)0): equity_asset_executed.longValue() );
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
   * <em>ruito_asset_executed</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getRuitoAssetExecuted()
  {
    return ( ruito_asset_executed==null? ((long)0): ruito_asset_executed.longValue() );
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
   * <em>mutual_fund_asset_executed</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMutualFundAssetExecuted()
  {
    return ( mutual_fund_asset_executed==null? ((long)0): mutual_fund_asset_executed.longValue() );
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
   * <em>bond_asset_executed</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getBondAssetExecuted()
  {
    return ( bond_asset_executed==null? ((long)0): bond_asset_executed.longValue() );
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
   * <em>trading_stop</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTradingStop()
  {
    return trading_stop;
  }


  /** 
   * <em>trading_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingStopIsSet() {
    return trading_stop_is_set;
  }


  /** 
   * <em>trading_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradingStopIsModified() {
    return trading_stop_is_modified;
  }


  /** 
   * <em>margin_open_position_stop</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarginOpenPositionStop()
  {
    return margin_open_position_stop;
  }


  /** 
   * <em>margin_open_position_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginOpenPositionStopIsSet() {
    return margin_open_position_stop_is_set;
  }


  /** 
   * <em>margin_open_position_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginOpenPositionStopIsModified() {
    return margin_open_position_stop_is_modified;
  }


  /** 
   * <em>payment_stop</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getPaymentStop()
  {
    return payment_stop;
  }


  /** 
   * <em>payment_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentStopIsSet() {
    return payment_stop_is_set;
  }


  /** 
   * <em>payment_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentStopIsModified() {
    return payment_stop_is_modified;
  }


  /** 
   * <em>other_trading_stop</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOtherTradingStop()
  {
    return other_trading_stop;
  }


  /** 
   * <em>other_trading_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherTradingStopIsSet() {
    return other_trading_stop_is_set;
  }


  /** 
   * <em>other_trading_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherTradingStopIsModified() {
    return other_trading_stop_is_modified;
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
    return new OrixTpCalcResultMarginPK(calc_result_margin_id);
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
   * <em>work_date</em>カラムの値を設定します。 
   *
   * @@param p_workDate <em>work_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setWorkDate( String p_workDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    work_date = p_workDate;
    work_date_is_set = true;
    work_date_is_modified = true;
  }


  /** 
   * <em>branch_code</em>カラムの値を設定します。 
   *
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす3桁以下のString値 
   */
  public final void setBranchCode( String p_branchCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    branch_code = p_branchCode;
    branch_code_is_set = true;
    branch_code_is_modified = true;
  }


  /** 
   * <em>account_code</em>カラムの値を設定します。 
   *
   * @@param p_accountCode <em>account_code</em>カラムの値をあらわす7桁以下のString値 
   */
  public final void setAccountCode( String p_accountCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_code = p_accountCode;
    account_code_is_set = true;
    account_code_is_modified = true;
  }


  /** 
   * <em>asset_evaluation_div</em>カラムの値を設定します。 
   *
   * @@param p_assetEvaluationDiv <em>asset_evaluation_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAssetEvaluationDiv( String p_assetEvaluationDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    asset_evaluation_div = p_assetEvaluationDiv;
    asset_evaluation_div_is_set = true;
    asset_evaluation_div_is_modified = true;
  }


  /** 
   * <em>account_balance_0</em>カラムの値を設定します。 
   *
   * @@param p_accountBalance0 <em>account_balance_0</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setAccountBalance0( long p_accountBalance0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_0 = new Long(p_accountBalance0);
    account_balance_0_is_set = true;
    account_balance_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_balance_0</em>カラムに値を設定します。 
   */
  public final void setAccountBalance0( Long p_accountBalance0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_0 = p_accountBalance0;
    account_balance_0_is_set = true;
    account_balance_0_is_modified = true;
  }


  /** 
   * <em>account_balance_1</em>カラムの値を設定します。 
   *
   * @@param p_accountBalance1 <em>account_balance_1</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setAccountBalance1( long p_accountBalance1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_1 = new Long(p_accountBalance1);
    account_balance_1_is_set = true;
    account_balance_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_balance_1</em>カラムに値を設定します。 
   */
  public final void setAccountBalance1( Long p_accountBalance1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_1 = p_accountBalance1;
    account_balance_1_is_set = true;
    account_balance_1_is_modified = true;
  }


  /** 
   * <em>account_balance_2</em>カラムの値を設定します。 
   *
   * @@param p_accountBalance2 <em>account_balance_2</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setAccountBalance2( long p_accountBalance2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_2 = new Long(p_accountBalance2);
    account_balance_2_is_set = true;
    account_balance_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_balance_2</em>カラムに値を設定します。 
   */
  public final void setAccountBalance2( Long p_accountBalance2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_2 = p_accountBalance2;
    account_balance_2_is_set = true;
    account_balance_2_is_modified = true;
  }


  /** 
   * <em>account_balance_3</em>カラムの値を設定します。 
   *
   * @@param p_accountBalance3 <em>account_balance_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setAccountBalance3( long p_accountBalance3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_3 = new Long(p_accountBalance3);
    account_balance_3_is_set = true;
    account_balance_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_balance_3</em>カラムに値を設定します。 
   */
  public final void setAccountBalance3( Long p_accountBalance3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_3 = p_accountBalance3;
    account_balance_3_is_set = true;
    account_balance_3_is_modified = true;
  }


  /** 
   * <em>account_balance_4</em>カラムの値を設定します。 
   *
   * @@param p_accountBalance4 <em>account_balance_4</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setAccountBalance4( long p_accountBalance4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_4 = new Long(p_accountBalance4);
    account_balance_4_is_set = true;
    account_balance_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_balance_4</em>カラムに値を設定します。 
   */
  public final void setAccountBalance4( Long p_accountBalance4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_4 = p_accountBalance4;
    account_balance_4_is_set = true;
    account_balance_4_is_modified = true;
  }


  /** 
   * <em>today_unexecuted_amount_1</em>カラムの値を設定します。 
   *
   * @@param p_todayUnexecutedAmount1 <em>today_unexecuted_amount_1</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setTodayUnexecutedAmount1( long p_todayUnexecutedAmount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_unexecuted_amount_1 = new Long(p_todayUnexecutedAmount1);
    today_unexecuted_amount_1_is_set = true;
    today_unexecuted_amount_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_unexecuted_amount_1</em>カラムに値を設定します。 
   */
  public final void setTodayUnexecutedAmount1( Long p_todayUnexecutedAmount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_unexecuted_amount_1 = p_todayUnexecutedAmount1;
    today_unexecuted_amount_1_is_set = true;
    today_unexecuted_amount_1_is_modified = true;
  }


  /** 
   * <em>today_unexecuted_amount_2</em>カラムの値を設定します。 
   *
   * @@param p_todayUnexecutedAmount2 <em>today_unexecuted_amount_2</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setTodayUnexecutedAmount2( long p_todayUnexecutedAmount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_unexecuted_amount_2 = new Long(p_todayUnexecutedAmount2);
    today_unexecuted_amount_2_is_set = true;
    today_unexecuted_amount_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_unexecuted_amount_2</em>カラムに値を設定します。 
   */
  public final void setTodayUnexecutedAmount2( Long p_todayUnexecutedAmount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_unexecuted_amount_2 = p_todayUnexecutedAmount2;
    today_unexecuted_amount_2_is_set = true;
    today_unexecuted_amount_2_is_modified = true;
  }


  /** 
   * <em>today_unexecuted_amount_3</em>カラムの値を設定します。 
   *
   * @@param p_todayUnexecutedAmount3 <em>today_unexecuted_amount_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setTodayUnexecutedAmount3( long p_todayUnexecutedAmount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_unexecuted_amount_3 = new Long(p_todayUnexecutedAmount3);
    today_unexecuted_amount_3_is_set = true;
    today_unexecuted_amount_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_unexecuted_amount_3</em>カラムに値を設定します。 
   */
  public final void setTodayUnexecutedAmount3( Long p_todayUnexecutedAmount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_unexecuted_amount_3 = p_todayUnexecutedAmount3;
    today_unexecuted_amount_3_is_set = true;
    today_unexecuted_amount_3_is_modified = true;
  }


  /** 
   * <em>today_unexecuted_amount_4</em>カラムの値を設定します。 
   *
   * @@param p_todayUnexecutedAmount4 <em>today_unexecuted_amount_4</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setTodayUnexecutedAmount4( long p_todayUnexecutedAmount4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_unexecuted_amount_4 = new Long(p_todayUnexecutedAmount4);
    today_unexecuted_amount_4_is_set = true;
    today_unexecuted_amount_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_unexecuted_amount_4</em>カラムに値を設定します。 
   */
  public final void setTodayUnexecutedAmount4( Long p_todayUnexecutedAmount4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_unexecuted_amount_4 = p_todayUnexecutedAmount4;
    today_unexecuted_amount_4_is_set = true;
    today_unexecuted_amount_4_is_modified = true;
  }


  /** 
   * <em>day_trade_restraint_0</em>カラムの値を設定します。 
   *
   * @@param p_dayTradeRestraint0 <em>day_trade_restraint_0</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setDayTradeRestraint0( long p_dayTradeRestraint0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_0 = new Long(p_dayTradeRestraint0);
    day_trade_restraint_0_is_set = true;
    day_trade_restraint_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_trade_restraint_0</em>カラムに値を設定します。 
   */
  public final void setDayTradeRestraint0( Long p_dayTradeRestraint0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_0 = p_dayTradeRestraint0;
    day_trade_restraint_0_is_set = true;
    day_trade_restraint_0_is_modified = true;
  }


  /** 
   * <em>day_trade_restraint_1</em>カラムの値を設定します。 
   *
   * @@param p_dayTradeRestraint1 <em>day_trade_restraint_1</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setDayTradeRestraint1( long p_dayTradeRestraint1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_1 = new Long(p_dayTradeRestraint1);
    day_trade_restraint_1_is_set = true;
    day_trade_restraint_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_trade_restraint_1</em>カラムに値を設定します。 
   */
  public final void setDayTradeRestraint1( Long p_dayTradeRestraint1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_1 = p_dayTradeRestraint1;
    day_trade_restraint_1_is_set = true;
    day_trade_restraint_1_is_modified = true;
  }


  /** 
   * <em>day_trade_restraint_2</em>カラムの値を設定します。 
   *
   * @@param p_dayTradeRestraint2 <em>day_trade_restraint_2</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setDayTradeRestraint2( long p_dayTradeRestraint2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_2 = new Long(p_dayTradeRestraint2);
    day_trade_restraint_2_is_set = true;
    day_trade_restraint_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_trade_restraint_2</em>カラムに値を設定します。 
   */
  public final void setDayTradeRestraint2( Long p_dayTradeRestraint2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_2 = p_dayTradeRestraint2;
    day_trade_restraint_2_is_set = true;
    day_trade_restraint_2_is_modified = true;
  }


  /** 
   * <em>day_trade_restraint_3</em>カラムの値を設定します。 
   *
   * @@param p_dayTradeRestraint3 <em>day_trade_restraint_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setDayTradeRestraint3( long p_dayTradeRestraint3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_3 = new Long(p_dayTradeRestraint3);
    day_trade_restraint_3_is_set = true;
    day_trade_restraint_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_trade_restraint_3</em>カラムに値を設定します。 
   */
  public final void setDayTradeRestraint3( Long p_dayTradeRestraint3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_3 = p_dayTradeRestraint3;
    day_trade_restraint_3_is_set = true;
    day_trade_restraint_3_is_modified = true;
  }


  /** 
   * <em>day_trade_restraint_4</em>カラムの値を設定します。 
   *
   * @@param p_dayTradeRestraint4 <em>day_trade_restraint_4</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setDayTradeRestraint4( long p_dayTradeRestraint4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_4 = new Long(p_dayTradeRestraint4);
    day_trade_restraint_4_is_set = true;
    day_trade_restraint_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_trade_restraint_4</em>カラムに値を設定します。 
   */
  public final void setDayTradeRestraint4( Long p_dayTradeRestraint4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_4 = p_dayTradeRestraint4;
    day_trade_restraint_4_is_set = true;
    day_trade_restraint_4_is_modified = true;
  }


  /** 
   * <em>other_restraint_0</em>カラムの値を設定します。 
   *
   * @@param p_otherRestraint0 <em>other_restraint_0</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setOtherRestraint0( long p_otherRestraint0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_restraint_0 = new Long(p_otherRestraint0);
    other_restraint_0_is_set = true;
    other_restraint_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other_restraint_0</em>カラムに値を設定します。 
   */
  public final void setOtherRestraint0( Long p_otherRestraint0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    other_restraint_0 = p_otherRestraint0;
    other_restraint_0_is_set = true;
    other_restraint_0_is_modified = true;
  }


  /** 
   * <em>other_restraint_1</em>カラムの値を設定します。 
   *
   * @@param p_otherRestraint1 <em>other_restraint_1</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setOtherRestraint1( long p_otherRestraint1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_restraint_1 = new Long(p_otherRestraint1);
    other_restraint_1_is_set = true;
    other_restraint_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other_restraint_1</em>カラムに値を設定します。 
   */
  public final void setOtherRestraint1( Long p_otherRestraint1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    other_restraint_1 = p_otherRestraint1;
    other_restraint_1_is_set = true;
    other_restraint_1_is_modified = true;
  }


  /** 
   * <em>other_restraint_2</em>カラムの値を設定します。 
   *
   * @@param p_otherRestraint2 <em>other_restraint_2</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setOtherRestraint2( long p_otherRestraint2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_restraint_2 = new Long(p_otherRestraint2);
    other_restraint_2_is_set = true;
    other_restraint_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other_restraint_2</em>カラムに値を設定します。 
   */
  public final void setOtherRestraint2( Long p_otherRestraint2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    other_restraint_2 = p_otherRestraint2;
    other_restraint_2_is_set = true;
    other_restraint_2_is_modified = true;
  }


  /** 
   * <em>other_restraint_3</em>カラムの値を設定します。 
   *
   * @@param p_otherRestraint3 <em>other_restraint_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setOtherRestraint3( long p_otherRestraint3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_restraint_3 = new Long(p_otherRestraint3);
    other_restraint_3_is_set = true;
    other_restraint_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other_restraint_3</em>カラムに値を設定します。 
   */
  public final void setOtherRestraint3( Long p_otherRestraint3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    other_restraint_3 = p_otherRestraint3;
    other_restraint_3_is_set = true;
    other_restraint_3_is_modified = true;
  }


  /** 
   * <em>other_restraint_4</em>カラムの値を設定します。 
   *
   * @@param p_otherRestraint4 <em>other_restraint_4</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setOtherRestraint4( long p_otherRestraint4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_restraint_4 = new Long(p_otherRestraint4);
    other_restraint_4_is_set = true;
    other_restraint_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other_restraint_4</em>カラムに値を設定します。 
   */
  public final void setOtherRestraint4( Long p_otherRestraint4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    other_restraint_4 = p_otherRestraint4;
    other_restraint_4_is_set = true;
    other_restraint_4_is_modified = true;
  }


  /** 
   * <em>margin_account_balance_0</em>カラムの値を設定します。 
   *
   * @@param p_marginAccountBalance0 <em>margin_account_balance_0</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginAccountBalance0( long p_marginAccountBalance0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_account_balance_0 = new Long(p_marginAccountBalance0);
    margin_account_balance_0_is_set = true;
    margin_account_balance_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_account_balance_0</em>カラムに値を設定します。 
   */
  public final void setMarginAccountBalance0( Long p_marginAccountBalance0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_account_balance_0 = p_marginAccountBalance0;
    margin_account_balance_0_is_set = true;
    margin_account_balance_0_is_modified = true;
  }


  /** 
   * <em>margin_account_balance_1</em>カラムの値を設定します。 
   *
   * @@param p_marginAccountBalance1 <em>margin_account_balance_1</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginAccountBalance1( long p_marginAccountBalance1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_account_balance_1 = new Long(p_marginAccountBalance1);
    margin_account_balance_1_is_set = true;
    margin_account_balance_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_account_balance_1</em>カラムに値を設定します。 
   */
  public final void setMarginAccountBalance1( Long p_marginAccountBalance1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_account_balance_1 = p_marginAccountBalance1;
    margin_account_balance_1_is_set = true;
    margin_account_balance_1_is_modified = true;
  }


  /** 
   * <em>margin_account_balance_2</em>カラムの値を設定します。 
   *
   * @@param p_marginAccountBalance2 <em>margin_account_balance_2</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginAccountBalance2( long p_marginAccountBalance2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_account_balance_2 = new Long(p_marginAccountBalance2);
    margin_account_balance_2_is_set = true;
    margin_account_balance_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_account_balance_2</em>カラムに値を設定します。 
   */
  public final void setMarginAccountBalance2( Long p_marginAccountBalance2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_account_balance_2 = p_marginAccountBalance2;
    margin_account_balance_2_is_set = true;
    margin_account_balance_2_is_modified = true;
  }


  /** 
   * <em>margin_account_balance_3</em>カラムの値を設定します。 
   *
   * @@param p_marginAccountBalance3 <em>margin_account_balance_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginAccountBalance3( long p_marginAccountBalance3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_account_balance_3 = new Long(p_marginAccountBalance3);
    margin_account_balance_3_is_set = true;
    margin_account_balance_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_account_balance_3</em>カラムに値を設定します。 
   */
  public final void setMarginAccountBalance3( Long p_marginAccountBalance3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_account_balance_3 = p_marginAccountBalance3;
    margin_account_balance_3_is_set = true;
    margin_account_balance_3_is_modified = true;
  }


  /** 
   * <em>margin_account_balance_4</em>カラムの値を設定します。 
   *
   * @@param p_marginAccountBalance4 <em>margin_account_balance_4</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginAccountBalance4( long p_marginAccountBalance4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_account_balance_4 = new Long(p_marginAccountBalance4);
    margin_account_balance_4_is_set = true;
    margin_account_balance_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_account_balance_4</em>カラムに値を設定します。 
   */
  public final void setMarginAccountBalance4( Long p_marginAccountBalance4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_account_balance_4 = p_marginAccountBalance4;
    margin_account_balance_4_is_set = true;
    margin_account_balance_4_is_modified = true;
  }


  /** 
   * <em>substitute_security_asset_0</em>カラムの値を設定します。 
   *
   * @@param p_substituteSecurityAsset0 <em>substitute_security_asset_0</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setSubstituteSecurityAsset0( long p_substituteSecurityAsset0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_0 = new Long(p_substituteSecurityAsset0);
    substitute_security_asset_0_is_set = true;
    substitute_security_asset_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>substitute_security_asset_0</em>カラムに値を設定します。 
   */
  public final void setSubstituteSecurityAsset0( Long p_substituteSecurityAsset0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_0 = p_substituteSecurityAsset0;
    substitute_security_asset_0_is_set = true;
    substitute_security_asset_0_is_modified = true;
  }


  /** 
   * <em>substitute_security_asset_1</em>カラムの値を設定します。 
   *
   * @@param p_substituteSecurityAsset1 <em>substitute_security_asset_1</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setSubstituteSecurityAsset1( long p_substituteSecurityAsset1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_1 = new Long(p_substituteSecurityAsset1);
    substitute_security_asset_1_is_set = true;
    substitute_security_asset_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>substitute_security_asset_1</em>カラムに値を設定します。 
   */
  public final void setSubstituteSecurityAsset1( Long p_substituteSecurityAsset1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_1 = p_substituteSecurityAsset1;
    substitute_security_asset_1_is_set = true;
    substitute_security_asset_1_is_modified = true;
  }


  /** 
   * <em>substitute_security_asset_2</em>カラムの値を設定します。 
   *
   * @@param p_substituteSecurityAsset2 <em>substitute_security_asset_2</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setSubstituteSecurityAsset2( long p_substituteSecurityAsset2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_2 = new Long(p_substituteSecurityAsset2);
    substitute_security_asset_2_is_set = true;
    substitute_security_asset_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>substitute_security_asset_2</em>カラムに値を設定します。 
   */
  public final void setSubstituteSecurityAsset2( Long p_substituteSecurityAsset2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_2 = p_substituteSecurityAsset2;
    substitute_security_asset_2_is_set = true;
    substitute_security_asset_2_is_modified = true;
  }


  /** 
   * <em>substitute_security_asset_3</em>カラムの値を設定します。 
   *
   * @@param p_substituteSecurityAsset3 <em>substitute_security_asset_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setSubstituteSecurityAsset3( long p_substituteSecurityAsset3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_3 = new Long(p_substituteSecurityAsset3);
    substitute_security_asset_3_is_set = true;
    substitute_security_asset_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>substitute_security_asset_3</em>カラムに値を設定します。 
   */
  public final void setSubstituteSecurityAsset3( Long p_substituteSecurityAsset3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_3 = p_substituteSecurityAsset3;
    substitute_security_asset_3_is_set = true;
    substitute_security_asset_3_is_modified = true;
  }


  /** 
   * <em>substitute_security_asset_4</em>カラムの値を設定します。 
   *
   * @@param p_substituteSecurityAsset4 <em>substitute_security_asset_4</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setSubstituteSecurityAsset4( long p_substituteSecurityAsset4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_4 = new Long(p_substituteSecurityAsset4);
    substitute_security_asset_4_is_set = true;
    substitute_security_asset_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>substitute_security_asset_4</em>カラムに値を設定します。 
   */
  public final void setSubstituteSecurityAsset4( Long p_substituteSecurityAsset4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_4 = p_substituteSecurityAsset4;
    substitute_security_asset_4_is_set = true;
    substitute_security_asset_4_is_modified = true;
  }


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetProfitLoss <em>contract_asset_profit_loss</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setContractAssetProfitLoss( long p_contractAssetProfitLoss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_loss = new Long(p_contractAssetProfitLoss);
    contract_asset_profit_loss_is_set = true;
    contract_asset_profit_loss_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_profit_loss</em>カラムに値を設定します。 
   */
  public final void setContractAssetProfitLoss( Long p_contractAssetProfitLoss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_loss = p_contractAssetProfitLoss;
    contract_asset_profit_loss_is_set = true;
    contract_asset_profit_loss_is_modified = true;
  }


  /** 
   * <em>contract_total_cost</em>カラムの値を設定します。 
   *
   * @@param p_contractTotalCost <em>contract_total_cost</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setContractTotalCost( long p_contractTotalCost )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_total_cost = new Long(p_contractTotalCost);
    contract_total_cost_is_set = true;
    contract_total_cost_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_total_cost</em>カラムに値を設定します。 
   */
  public final void setContractTotalCost( Long p_contractTotalCost )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_total_cost = p_contractTotalCost;
    contract_total_cost_is_set = true;
    contract_total_cost_is_modified = true;
  }


  /** 
   * <em>undeli_contract_loss_0</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractLoss0 <em>undeli_contract_loss_0</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setUndeliContractLoss0( long p_undeliContractLoss0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_loss_0 = new Long(p_undeliContractLoss0);
    undeli_contract_loss_0_is_set = true;
    undeli_contract_loss_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>undeli_contract_loss_0</em>カラムに値を設定します。 
   */
  public final void setUndeliContractLoss0( Long p_undeliContractLoss0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_loss_0 = p_undeliContractLoss0;
    undeli_contract_loss_0_is_set = true;
    undeli_contract_loss_0_is_modified = true;
  }


  /** 
   * <em>undeli_contract_loss_1</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractLoss1 <em>undeli_contract_loss_1</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setUndeliContractLoss1( long p_undeliContractLoss1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_loss_1 = new Long(p_undeliContractLoss1);
    undeli_contract_loss_1_is_set = true;
    undeli_contract_loss_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>undeli_contract_loss_1</em>カラムに値を設定します。 
   */
  public final void setUndeliContractLoss1( Long p_undeliContractLoss1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_loss_1 = p_undeliContractLoss1;
    undeli_contract_loss_1_is_set = true;
    undeli_contract_loss_1_is_modified = true;
  }


  /** 
   * <em>undeli_contract_loss_2</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractLoss2 <em>undeli_contract_loss_2</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setUndeliContractLoss2( long p_undeliContractLoss2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_loss_2 = new Long(p_undeliContractLoss2);
    undeli_contract_loss_2_is_set = true;
    undeli_contract_loss_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>undeli_contract_loss_2</em>カラムに値を設定します。 
   */
  public final void setUndeliContractLoss2( Long p_undeliContractLoss2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_loss_2 = p_undeliContractLoss2;
    undeli_contract_loss_2_is_set = true;
    undeli_contract_loss_2_is_modified = true;
  }


  /** 
   * <em>undeli_contract_loss_3</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractLoss3 <em>undeli_contract_loss_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setUndeliContractLoss3( long p_undeliContractLoss3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_loss_3 = new Long(p_undeliContractLoss3);
    undeli_contract_loss_3_is_set = true;
    undeli_contract_loss_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>undeli_contract_loss_3</em>カラムに値を設定します。 
   */
  public final void setUndeliContractLoss3( Long p_undeliContractLoss3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_loss_3 = p_undeliContractLoss3;
    undeli_contract_loss_3_is_set = true;
    undeli_contract_loss_3_is_modified = true;
  }


  /** 
   * <em>today_repay_contract_pre_asset</em>カラムの値を設定します。 
   *
   * @@param p_todayRepayContractPreAsset <em>today_repay_contract_pre_asset</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setTodayRepayContractPreAsset( long p_todayRepayContractPreAsset )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_repay_contract_pre_asset = new Long(p_todayRepayContractPreAsset);
    today_repay_contract_pre_asset_is_set = true;
    today_repay_contract_pre_asset_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>today_repay_contract_pre_asset</em>カラムに値を設定します。 
   */
  public final void setTodayRepayContractPreAsset( Long p_todayRepayContractPreAsset )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    today_repay_contract_pre_asset = p_todayRepayContractPreAsset;
    today_repay_contract_pre_asset_is_set = true;
    today_repay_contract_pre_asset_is_modified = true;
  }


  /** 
   * <em>contract_amount_day_repay_0</em>カラムの値を設定します。 
   *
   * @@param p_contractAmountDayRepay0 <em>contract_amount_day_repay_0</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setContractAmountDayRepay0( long p_contractAmountDayRepay0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount_day_repay_0 = new Long(p_contractAmountDayRepay0);
    contract_amount_day_repay_0_is_set = true;
    contract_amount_day_repay_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_amount_day_repay_0</em>カラムに値を設定します。 
   */
  public final void setContractAmountDayRepay0( Long p_contractAmountDayRepay0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount_day_repay_0 = p_contractAmountDayRepay0;
    contract_amount_day_repay_0_is_set = true;
    contract_amount_day_repay_0_is_modified = true;
  }


  /** 
   * <em>contract_amount_day_repay_1</em>カラムの値を設定します。 
   *
   * @@param p_contractAmountDayRepay1 <em>contract_amount_day_repay_1</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setContractAmountDayRepay1( long p_contractAmountDayRepay1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount_day_repay_1 = new Long(p_contractAmountDayRepay1);
    contract_amount_day_repay_1_is_set = true;
    contract_amount_day_repay_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_amount_day_repay_1</em>カラムに値を設定します。 
   */
  public final void setContractAmountDayRepay1( Long p_contractAmountDayRepay1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount_day_repay_1 = p_contractAmountDayRepay1;
    contract_amount_day_repay_1_is_set = true;
    contract_amount_day_repay_1_is_modified = true;
  }


  /** 
   * <em>contract_amount_day_repay_2</em>カラムの値を設定します。 
   *
   * @@param p_contractAmountDayRepay2 <em>contract_amount_day_repay_2</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setContractAmountDayRepay2( long p_contractAmountDayRepay2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount_day_repay_2 = new Long(p_contractAmountDayRepay2);
    contract_amount_day_repay_2_is_set = true;
    contract_amount_day_repay_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_amount_day_repay_2</em>カラムに値を設定します。 
   */
  public final void setContractAmountDayRepay2( Long p_contractAmountDayRepay2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount_day_repay_2 = p_contractAmountDayRepay2;
    contract_amount_day_repay_2_is_set = true;
    contract_amount_day_repay_2_is_modified = true;
  }


  /** 
   * <em>contract_amount_day_repay_3</em>カラムの値を設定します。 
   *
   * @@param p_contractAmountDayRepay3 <em>contract_amount_day_repay_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setContractAmountDayRepay3( long p_contractAmountDayRepay3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount_day_repay_3 = new Long(p_contractAmountDayRepay3);
    contract_amount_day_repay_3_is_set = true;
    contract_amount_day_repay_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_amount_day_repay_3</em>カラムに値を設定します。 
   */
  public final void setContractAmountDayRepay3( Long p_contractAmountDayRepay3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount_day_repay_3 = p_contractAmountDayRepay3;
    contract_amount_day_repay_3_is_set = true;
    contract_amount_day_repay_3_is_modified = true;
  }


  /** 
   * <em>contract_amount_day_repay_4</em>カラムの値を設定します。 
   *
   * @@param p_contractAmountDayRepay4 <em>contract_amount_day_repay_4</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setContractAmountDayRepay4( long p_contractAmountDayRepay4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount_day_repay_4 = new Long(p_contractAmountDayRepay4);
    contract_amount_day_repay_4_is_set = true;
    contract_amount_day_repay_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_amount_day_repay_4</em>カラムに値を設定します。 
   */
  public final void setContractAmountDayRepay4( Long p_contractAmountDayRepay4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount_day_repay_4 = p_contractAmountDayRepay4;
    contract_amount_day_repay_4_is_set = true;
    contract_amount_day_repay_4_is_modified = true;
  }


  /** 
   * <em>margin_power_0</em>カラムの値を設定します。 
   *
   * @@param p_marginPower0 <em>margin_power_0</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginPower0( long p_marginPower0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_power_0 = new Long(p_marginPower0);
    margin_power_0_is_set = true;
    margin_power_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_power_0</em>カラムに値を設定します。 
   */
  public final void setMarginPower0( Long p_marginPower0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_power_0 = p_marginPower0;
    margin_power_0_is_set = true;
    margin_power_0_is_modified = true;
  }


  /** 
   * <em>margin_power_1</em>カラムの値を設定します。 
   *
   * @@param p_marginPower1 <em>margin_power_1</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginPower1( long p_marginPower1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_power_1 = new Long(p_marginPower1);
    margin_power_1_is_set = true;
    margin_power_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_power_1</em>カラムに値を設定します。 
   */
  public final void setMarginPower1( Long p_marginPower1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_power_1 = p_marginPower1;
    margin_power_1_is_set = true;
    margin_power_1_is_modified = true;
  }


  /** 
   * <em>margin_power_2</em>カラムの値を設定します。 
   *
   * @@param p_marginPower2 <em>margin_power_2</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginPower2( long p_marginPower2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_power_2 = new Long(p_marginPower2);
    margin_power_2_is_set = true;
    margin_power_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_power_2</em>カラムに値を設定します。 
   */
  public final void setMarginPower2( Long p_marginPower2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_power_2 = p_marginPower2;
    margin_power_2_is_set = true;
    margin_power_2_is_modified = true;
  }


  /** 
   * <em>margin_power_3</em>カラムの値を設定します。 
   *
   * @@param p_marginPower3 <em>margin_power_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginPower3( long p_marginPower3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_power_3 = new Long(p_marginPower3);
    margin_power_3_is_set = true;
    margin_power_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_power_3</em>カラムに値を設定します。 
   */
  public final void setMarginPower3( Long p_marginPower3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_power_3 = p_marginPower3;
    margin_power_3_is_set = true;
    margin_power_3_is_modified = true;
  }


  /** 
   * <em>margin_power_4</em>カラムの値を設定します。 
   *
   * @@param p_marginPower4 <em>margin_power_4</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginPower4( long p_marginPower4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_power_4 = new Long(p_marginPower4);
    margin_power_4_is_set = true;
    margin_power_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_power_4</em>カラムに値を設定します。 
   */
  public final void setMarginPower4( Long p_marginPower4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_power_4 = p_marginPower4;
    margin_power_4_is_set = true;
    margin_power_4_is_modified = true;
  }


  /** 
   * <em>margin_trading_power_1</em>カラムの値を設定します。 
   *
   * @@param p_marginTradingPower1 <em>margin_trading_power_1</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginTradingPower1( long p_marginTradingPower1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_trading_power_1 = new Long(p_marginTradingPower1);
    margin_trading_power_1_is_set = true;
    margin_trading_power_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_trading_power_1</em>カラムに値を設定します。 
   */
  public final void setMarginTradingPower1( Long p_marginTradingPower1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_trading_power_1 = p_marginTradingPower1;
    margin_trading_power_1_is_set = true;
    margin_trading_power_1_is_modified = true;
  }


  /** 
   * <em>margin_trading_power_2</em>カラムの値を設定します。 
   *
   * @@param p_marginTradingPower2 <em>margin_trading_power_2</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginTradingPower2( long p_marginTradingPower2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_trading_power_2 = new Long(p_marginTradingPower2);
    margin_trading_power_2_is_set = true;
    margin_trading_power_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_trading_power_2</em>カラムに値を設定します。 
   */
  public final void setMarginTradingPower2( Long p_marginTradingPower2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_trading_power_2 = p_marginTradingPower2;
    margin_trading_power_2_is_set = true;
    margin_trading_power_2_is_modified = true;
  }


  /** 
   * <em>margin_trading_power_3</em>カラムの値を設定します。 
   *
   * @@param p_marginTradingPower3 <em>margin_trading_power_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginTradingPower3( long p_marginTradingPower3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_trading_power_3 = new Long(p_marginTradingPower3);
    margin_trading_power_3_is_set = true;
    margin_trading_power_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_trading_power_3</em>カラムに値を設定します。 
   */
  public final void setMarginTradingPower3( Long p_marginTradingPower3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_trading_power_3 = p_marginTradingPower3;
    margin_trading_power_3_is_set = true;
    margin_trading_power_3_is_modified = true;
  }


  /** 
   * <em>margin_trading_power_4</em>カラムの値を設定します。 
   *
   * @@param p_marginTradingPower4 <em>margin_trading_power_4</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginTradingPower4( long p_marginTradingPower4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_trading_power_4 = new Long(p_marginTradingPower4);
    margin_trading_power_4_is_set = true;
    margin_trading_power_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_trading_power_4</em>カラムに値を設定します。 
   */
  public final void setMarginTradingPower4( Long p_marginTradingPower4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_trading_power_4 = p_marginTradingPower4;
    margin_trading_power_4_is_set = true;
    margin_trading_power_4_is_modified = true;
  }


  /** 
   * <em>margin_deposit_rate_0</em>カラムの値を設定します。 
   *
   * @@param p_marginDepositRate0 <em>margin_deposit_rate_0</em>カラムの値をあらわす26桁以下のdouble値で、その精度は12桁まで
   */
  public final void setMarginDepositRate0( double p_marginDepositRate0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_0 = new Double(p_marginDepositRate0);
    margin_deposit_rate_0_is_set = true;
    margin_deposit_rate_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_deposit_rate_0</em>カラムに値を設定します。 
   */
  public final void setMarginDepositRate0( Double p_marginDepositRate0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_0 = p_marginDepositRate0;
    margin_deposit_rate_0_is_set = true;
    margin_deposit_rate_0_is_modified = true;
  }


  /** 
   * <em>margin_deposit_rate_1</em>カラムの値を設定します。 
   *
   * @@param p_marginDepositRate1 <em>margin_deposit_rate_1</em>カラムの値をあらわす26桁以下のdouble値で、その精度は12桁まで
   */
  public final void setMarginDepositRate1( double p_marginDepositRate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_1 = new Double(p_marginDepositRate1);
    margin_deposit_rate_1_is_set = true;
    margin_deposit_rate_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_deposit_rate_1</em>カラムに値を設定します。 
   */
  public final void setMarginDepositRate1( Double p_marginDepositRate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_1 = p_marginDepositRate1;
    margin_deposit_rate_1_is_set = true;
    margin_deposit_rate_1_is_modified = true;
  }


  /** 
   * <em>margin_deposit_rate_2</em>カラムの値を設定します。 
   *
   * @@param p_marginDepositRate2 <em>margin_deposit_rate_2</em>カラムの値をあらわす26桁以下のdouble値で、その精度は12桁まで
   */
  public final void setMarginDepositRate2( double p_marginDepositRate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_2 = new Double(p_marginDepositRate2);
    margin_deposit_rate_2_is_set = true;
    margin_deposit_rate_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_deposit_rate_2</em>カラムに値を設定します。 
   */
  public final void setMarginDepositRate2( Double p_marginDepositRate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_2 = p_marginDepositRate2;
    margin_deposit_rate_2_is_set = true;
    margin_deposit_rate_2_is_modified = true;
  }


  /** 
   * <em>margin_deposit_rate_3</em>カラムの値を設定します。 
   *
   * @@param p_marginDepositRate3 <em>margin_deposit_rate_3</em>カラムの値をあらわす26桁以下のdouble値で、その精度は12桁まで
   */
  public final void setMarginDepositRate3( double p_marginDepositRate3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_3 = new Double(p_marginDepositRate3);
    margin_deposit_rate_3_is_set = true;
    margin_deposit_rate_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_deposit_rate_3</em>カラムに値を設定します。 
   */
  public final void setMarginDepositRate3( Double p_marginDepositRate3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_3 = p_marginDepositRate3;
    margin_deposit_rate_3_is_set = true;
    margin_deposit_rate_3_is_modified = true;
  }


  /** 
   * <em>margin_deposit_rate_4</em>カラムの値を設定します。 
   *
   * @@param p_marginDepositRate4 <em>margin_deposit_rate_4</em>カラムの値をあらわす26桁以下のdouble値で、その精度は12桁まで
   */
  public final void setMarginDepositRate4( double p_marginDepositRate4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_4 = new Double(p_marginDepositRate4);
    margin_deposit_rate_4_is_set = true;
    margin_deposit_rate_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_deposit_rate_4</em>カラムに値を設定します。 
   */
  public final void setMarginDepositRate4( Double p_marginDepositRate4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_4 = p_marginDepositRate4;
    margin_deposit_rate_4_is_set = true;
    margin_deposit_rate_4_is_modified = true;
  }


  /** 
   * <em>act_rec_trading_power_3</em>カラムの値を設定します。 
   *
   * @@param p_actRecTradingPower3 <em>act_rec_trading_power_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setActRecTradingPower3( long p_actRecTradingPower3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    act_rec_trading_power_3 = new Long(p_actRecTradingPower3);
    act_rec_trading_power_3_is_set = true;
    act_rec_trading_power_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>act_rec_trading_power_3</em>カラムに値を設定します。 
   */
  public final void setActRecTradingPower3( Long p_actRecTradingPower3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    act_rec_trading_power_3 = p_actRecTradingPower3;
    act_rec_trading_power_3_is_set = true;
    act_rec_trading_power_3_is_modified = true;
  }


  /** 
   * <em>act_rec_trading_power_4</em>カラムの値を設定します。 
   *
   * @@param p_actRecTradingPower4 <em>act_rec_trading_power_4</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setActRecTradingPower4( long p_actRecTradingPower4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    act_rec_trading_power_4 = new Long(p_actRecTradingPower4);
    act_rec_trading_power_4_is_set = true;
    act_rec_trading_power_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>act_rec_trading_power_4</em>カラムに値を設定します。 
   */
  public final void setActRecTradingPower4( Long p_actRecTradingPower4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    act_rec_trading_power_4 = p_actRecTradingPower4;
    act_rec_trading_power_4_is_set = true;
    act_rec_trading_power_4_is_modified = true;
  }


  /** 
   * <em>act_rec_trading_power_4_dash</em>カラムの値を設定します。 
   *
   * @@param p_actRecTradingPower4Dash <em>act_rec_trading_power_4_dash</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setActRecTradingPower4Dash( long p_actRecTradingPower4Dash )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    act_rec_trading_power_4_dash = new Long(p_actRecTradingPower4Dash);
    act_rec_trading_power_4_dash_is_set = true;
    act_rec_trading_power_4_dash_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>act_rec_trading_power_4_dash</em>カラムに値を設定します。 
   */
  public final void setActRecTradingPower4Dash( Long p_actRecTradingPower4Dash )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    act_rec_trading_power_4_dash = p_actRecTradingPower4Dash;
    act_rec_trading_power_4_dash_is_set = true;
    act_rec_trading_power_4_dash_is_modified = true;
  }


  /** 
   * <em>equity_trading_power_3</em>カラムの値を設定します。 
   *
   * @@param p_equityTradingPower3 <em>equity_trading_power_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setEquityTradingPower3( long p_equityTradingPower3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_trading_power_3 = new Long(p_equityTradingPower3);
    equity_trading_power_3_is_set = true;
    equity_trading_power_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>equity_trading_power_3</em>カラムに値を設定します。 
   */
  public final void setEquityTradingPower3( Long p_equityTradingPower3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    equity_trading_power_3 = p_equityTradingPower3;
    equity_trading_power_3_is_set = true;
    equity_trading_power_3_is_modified = true;
  }


  /** 
   * <em>equity_trading_power_4</em>カラムの値を設定します。 
   *
   * @@param p_equityTradingPower4 <em>equity_trading_power_4</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setEquityTradingPower4( long p_equityTradingPower4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_trading_power_4 = new Long(p_equityTradingPower4);
    equity_trading_power_4_is_set = true;
    equity_trading_power_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>equity_trading_power_4</em>カラムに値を設定します。 
   */
  public final void setEquityTradingPower4( Long p_equityTradingPower4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    equity_trading_power_4 = p_equityTradingPower4;
    equity_trading_power_4_is_set = true;
    equity_trading_power_4_is_modified = true;
  }


  /** 
   * <em>equity_trading_power_4_dash</em>カラムの値を設定します。 
   *
   * @@param p_equityTradingPower4Dash <em>equity_trading_power_4_dash</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setEquityTradingPower4Dash( long p_equityTradingPower4Dash )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_trading_power_4_dash = new Long(p_equityTradingPower4Dash);
    equity_trading_power_4_dash_is_set = true;
    equity_trading_power_4_dash_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>equity_trading_power_4_dash</em>カラムに値を設定します。 
   */
  public final void setEquityTradingPower4Dash( Long p_equityTradingPower4Dash )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    equity_trading_power_4_dash = p_equityTradingPower4Dash;
    equity_trading_power_4_dash_is_set = true;
    equity_trading_power_4_dash_is_modified = true;
  }


  /** 
   * <em>undeli_contract_amount_0</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractAmount0 <em>undeli_contract_amount_0</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setUndeliContractAmount0( long p_undeliContractAmount0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_amount_0 = new Long(p_undeliContractAmount0);
    undeli_contract_amount_0_is_set = true;
    undeli_contract_amount_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>undeli_contract_amount_0</em>カラムに値を設定します。 
   */
  public final void setUndeliContractAmount0( Long p_undeliContractAmount0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_amount_0 = p_undeliContractAmount0;
    undeli_contract_amount_0_is_set = true;
    undeli_contract_amount_0_is_modified = true;
  }


  /** 
   * <em>undeli_contract_amount_1</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractAmount1 <em>undeli_contract_amount_1</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setUndeliContractAmount1( long p_undeliContractAmount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_amount_1 = new Long(p_undeliContractAmount1);
    undeli_contract_amount_1_is_set = true;
    undeli_contract_amount_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>undeli_contract_amount_1</em>カラムに値を設定します。 
   */
  public final void setUndeliContractAmount1( Long p_undeliContractAmount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_amount_1 = p_undeliContractAmount1;
    undeli_contract_amount_1_is_set = true;
    undeli_contract_amount_1_is_modified = true;
  }


  /** 
   * <em>undeli_contract_amount_2</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractAmount2 <em>undeli_contract_amount_2</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setUndeliContractAmount2( long p_undeliContractAmount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_amount_2 = new Long(p_undeliContractAmount2);
    undeli_contract_amount_2_is_set = true;
    undeli_contract_amount_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>undeli_contract_amount_2</em>カラムに値を設定します。 
   */
  public final void setUndeliContractAmount2( Long p_undeliContractAmount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_amount_2 = p_undeliContractAmount2;
    undeli_contract_amount_2_is_set = true;
    undeli_contract_amount_2_is_modified = true;
  }


  /** 
   * <em>undeli_contract_amount_3</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractAmount3 <em>undeli_contract_amount_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setUndeliContractAmount3( long p_undeliContractAmount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_amount_3 = new Long(p_undeliContractAmount3);
    undeli_contract_amount_3_is_set = true;
    undeli_contract_amount_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>undeli_contract_amount_3</em>カラムに値を設定します。 
   */
  public final void setUndeliContractAmount3( Long p_undeliContractAmount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_amount_3 = p_undeliContractAmount3;
    undeli_contract_amount_3_is_set = true;
    undeli_contract_amount_3_is_modified = true;
  }


  /** 
   * <em>margin_draw_power_0</em>カラムの値を設定します。 
   *
   * @@param p_marginDrawPower0 <em>margin_draw_power_0</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginDrawPower0( long p_marginDrawPower0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_draw_power_0 = new Long(p_marginDrawPower0);
    margin_draw_power_0_is_set = true;
    margin_draw_power_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_draw_power_0</em>カラムに値を設定します。 
   */
  public final void setMarginDrawPower0( Long p_marginDrawPower0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_draw_power_0 = p_marginDrawPower0;
    margin_draw_power_0_is_set = true;
    margin_draw_power_0_is_modified = true;
  }


  /** 
   * <em>margin_draw_power_1</em>カラムの値を設定します。 
   *
   * @@param p_marginDrawPower1 <em>margin_draw_power_1</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginDrawPower1( long p_marginDrawPower1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_draw_power_1 = new Long(p_marginDrawPower1);
    margin_draw_power_1_is_set = true;
    margin_draw_power_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_draw_power_1</em>カラムに値を設定します。 
   */
  public final void setMarginDrawPower1( Long p_marginDrawPower1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_draw_power_1 = p_marginDrawPower1;
    margin_draw_power_1_is_set = true;
    margin_draw_power_1_is_modified = true;
  }


  /** 
   * <em>margin_draw_power_2</em>カラムの値を設定します。 
   *
   * @@param p_marginDrawPower2 <em>margin_draw_power_2</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginDrawPower2( long p_marginDrawPower2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_draw_power_2 = new Long(p_marginDrawPower2);
    margin_draw_power_2_is_set = true;
    margin_draw_power_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_draw_power_2</em>カラムに値を設定します。 
   */
  public final void setMarginDrawPower2( Long p_marginDrawPower2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_draw_power_2 = p_marginDrawPower2;
    margin_draw_power_2_is_set = true;
    margin_draw_power_2_is_modified = true;
  }


  /** 
   * <em>margin_draw_power_3</em>カラムの値を設定します。 
   *
   * @@param p_marginDrawPower3 <em>margin_draw_power_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginDrawPower3( long p_marginDrawPower3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_draw_power_3 = new Long(p_marginDrawPower3);
    margin_draw_power_3_is_set = true;
    margin_draw_power_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_draw_power_3</em>カラムに値を設定します。 
   */
  public final void setMarginDrawPower3( Long p_marginDrawPower3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_draw_power_3 = p_marginDrawPower3;
    margin_draw_power_3_is_set = true;
    margin_draw_power_3_is_modified = true;
  }


  /** 
   * <em>margin_draw_power_4</em>カラムの値を設定します。 
   *
   * @@param p_marginDrawPower4 <em>margin_draw_power_4</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMarginDrawPower4( long p_marginDrawPower4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_draw_power_4 = new Long(p_marginDrawPower4);
    margin_draw_power_4_is_set = true;
    margin_draw_power_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_draw_power_4</em>カラムに値を設定します。 
   */
  public final void setMarginDrawPower4( Long p_marginDrawPower4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_draw_power_4 = p_marginDrawPower4;
    margin_draw_power_4_is_set = true;
    margin_draw_power_4_is_modified = true;
  }


  /** 
   * <em>other_trading_power_1</em>カラムの値を設定します。 
   *
   * @@param p_otherTradingPower1 <em>other_trading_power_1</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setOtherTradingPower1( long p_otherTradingPower1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_trading_power_1 = new Long(p_otherTradingPower1);
    other_trading_power_1_is_set = true;
    other_trading_power_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other_trading_power_1</em>カラムに値を設定します。 
   */
  public final void setOtherTradingPower1( Long p_otherTradingPower1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    other_trading_power_1 = p_otherTradingPower1;
    other_trading_power_1_is_set = true;
    other_trading_power_1_is_modified = true;
  }


  /** 
   * <em>other_trading_power_2</em>カラムの値を設定します。 
   *
   * @@param p_otherTradingPower2 <em>other_trading_power_2</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setOtherTradingPower2( long p_otherTradingPower2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_trading_power_2 = new Long(p_otherTradingPower2);
    other_trading_power_2_is_set = true;
    other_trading_power_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other_trading_power_2</em>カラムに値を設定します。 
   */
  public final void setOtherTradingPower2( Long p_otherTradingPower2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    other_trading_power_2 = p_otherTradingPower2;
    other_trading_power_2_is_set = true;
    other_trading_power_2_is_modified = true;
  }


  /** 
   * <em>other_trading_power_3</em>カラムの値を設定します。 
   *
   * @@param p_otherTradingPower3 <em>other_trading_power_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setOtherTradingPower3( long p_otherTradingPower3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_trading_power_3 = new Long(p_otherTradingPower3);
    other_trading_power_3_is_set = true;
    other_trading_power_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other_trading_power_3</em>カラムに値を設定します。 
   */
  public final void setOtherTradingPower3( Long p_otherTradingPower3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    other_trading_power_3 = p_otherTradingPower3;
    other_trading_power_3_is_set = true;
    other_trading_power_3_is_modified = true;
  }


  /** 
   * <em>other_trading_power_4</em>カラムの値を設定します。 
   *
   * @@param p_otherTradingPower4 <em>other_trading_power_4</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setOtherTradingPower4( long p_otherTradingPower4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_trading_power_4 = new Long(p_otherTradingPower4);
    other_trading_power_4_is_set = true;
    other_trading_power_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other_trading_power_4</em>カラムに値を設定します。 
   */
  public final void setOtherTradingPower4( Long p_otherTradingPower4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    other_trading_power_4 = p_otherTradingPower4;
    other_trading_power_4_is_set = true;
    other_trading_power_4_is_modified = true;
  }


  /** 
   * <em>demandamount0</em>カラムの値を設定します。 
   *
   * @@param p_demandamount0 <em>demandamount0</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setDemandamount0( long p_demandamount0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    demandamount0 = new Long(p_demandamount0);
    demandamount0_is_set = true;
    demandamount0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>demandamount0</em>カラムに値を設定します。 
   */
  public final void setDemandamount0( Long p_demandamount0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    demandamount0 = p_demandamount0;
    demandamount0_is_set = true;
    demandamount0_is_modified = true;
  }


  /** 
   * <em>demandamount1</em>カラムの値を設定します。 
   *
   * @@param p_demandamount1 <em>demandamount1</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setDemandamount1( long p_demandamount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    demandamount1 = new Long(p_demandamount1);
    demandamount1_is_set = true;
    demandamount1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>demandamount1</em>カラムに値を設定します。 
   */
  public final void setDemandamount1( Long p_demandamount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    demandamount1 = p_demandamount1;
    demandamount1_is_set = true;
    demandamount1_is_modified = true;
  }


  /** 
   * <em>demandamount2</em>カラムの値を設定します。 
   *
   * @@param p_demandamount2 <em>demandamount2</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setDemandamount2( long p_demandamount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    demandamount2 = new Long(p_demandamount2);
    demandamount2_is_set = true;
    demandamount2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>demandamount2</em>カラムに値を設定します。 
   */
  public final void setDemandamount2( Long p_demandamount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    demandamount2 = p_demandamount2;
    demandamount2_is_set = true;
    demandamount2_is_modified = true;
  }


  /** 
   * <em>demandamount3</em>カラムの値を設定します。 
   *
   * @@param p_demandamount3 <em>demandamount3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setDemandamount3( long p_demandamount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    demandamount3 = new Long(p_demandamount3);
    demandamount3_is_set = true;
    demandamount3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>demandamount3</em>カラムに値を設定します。 
   */
  public final void setDemandamount3( Long p_demandamount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    demandamount3 = p_demandamount3;
    demandamount3_is_set = true;
    demandamount3_is_modified = true;
  }


  /** 
   * <em>demandamount4</em>カラムの値を設定します。 
   *
   * @@param p_demandamount4 <em>demandamount4</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setDemandamount4( long p_demandamount4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    demandamount4 = new Long(p_demandamount4);
    demandamount4_is_set = true;
    demandamount4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>demandamount4</em>カラムに値を設定します。 
   */
  public final void setDemandamount4( Long p_demandamount4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    demandamount4 = p_demandamount4;
    demandamount4_is_set = true;
    demandamount4_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate0</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate0 <em>payment_amount_designate0</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setPaymentAmountDesignate0( long p_paymentAmountDesignate0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate0 = new Long(p_paymentAmountDesignate0);
    payment_amount_designate0_is_set = true;
    payment_amount_designate0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate0</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate0( Long p_paymentAmountDesignate0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate0 = p_paymentAmountDesignate0;
    payment_amount_designate0_is_set = true;
    payment_amount_designate0_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate1</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate1 <em>payment_amount_designate1</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setPaymentAmountDesignate1( long p_paymentAmountDesignate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate1 = new Long(p_paymentAmountDesignate1);
    payment_amount_designate1_is_set = true;
    payment_amount_designate1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate1</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate1( Long p_paymentAmountDesignate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate1 = p_paymentAmountDesignate1;
    payment_amount_designate1_is_set = true;
    payment_amount_designate1_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate2</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate2 <em>payment_amount_designate2</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setPaymentAmountDesignate2( long p_paymentAmountDesignate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate2 = new Long(p_paymentAmountDesignate2);
    payment_amount_designate2_is_set = true;
    payment_amount_designate2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate2</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate2( Long p_paymentAmountDesignate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate2 = p_paymentAmountDesignate2;
    payment_amount_designate2_is_set = true;
    payment_amount_designate2_is_modified = true;
  }


  /** 
   * <em>margin_sec_product_code</em>カラムの値を設定します。 
   *
   * @@param p_marginSecProductCode <em>margin_sec_product_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setMarginSecProductCode( String p_marginSecProductCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sec_product_code = p_marginSecProductCode;
    margin_sec_product_code_is_set = true;
    margin_sec_product_code_is_modified = true;
  }


  /** 
   * <em>margin_sec_rate</em>カラムの値を設定します。 
   *
   * @@param p_marginSecRate <em>margin_sec_rate</em>カラムの値をあらわす5桁以下のdouble値で、その精度は2桁まで
   */
  public final void setMarginSecRate( double p_marginSecRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sec_rate = new Double(p_marginSecRate);
    margin_sec_rate_is_set = true;
    margin_sec_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>margin_sec_rate</em>カラムに値を設定します。 
   */
  public final void setMarginSecRate( Double p_marginSecRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    margin_sec_rate = p_marginSecRate;
    margin_sec_rate_is_set = true;
    margin_sec_rate_is_modified = true;
  }


  /** 
   * <em>equity_asset_executed</em>カラムの値を設定します。 
   *
   * @@param p_equityAssetExecuted <em>equity_asset_executed</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setEquityAssetExecuted( long p_equityAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    equity_asset_executed = new Long(p_equityAssetExecuted);
    equity_asset_executed_is_set = true;
    equity_asset_executed_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>equity_asset_executed</em>カラムに値を設定します。 
   */
  public final void setEquityAssetExecuted( Long p_equityAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    equity_asset_executed = p_equityAssetExecuted;
    equity_asset_executed_is_set = true;
    equity_asset_executed_is_modified = true;
  }


  /** 
   * <em>ruito_asset_executed</em>カラムの値を設定します。 
   *
   * @@param p_ruitoAssetExecuted <em>ruito_asset_executed</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setRuitoAssetExecuted( long p_ruitoAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ruito_asset_executed = new Long(p_ruitoAssetExecuted);
    ruito_asset_executed_is_set = true;
    ruito_asset_executed_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>ruito_asset_executed</em>カラムに値を設定します。 
   */
  public final void setRuitoAssetExecuted( Long p_ruitoAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ruito_asset_executed = p_ruitoAssetExecuted;
    ruito_asset_executed_is_set = true;
    ruito_asset_executed_is_modified = true;
  }


  /** 
   * <em>mutual_fund_asset_executed</em>カラムの値を設定します。 
   *
   * @@param p_mutualFundAssetExecuted <em>mutual_fund_asset_executed</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setMutualFundAssetExecuted( long p_mutualFundAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mutual_fund_asset_executed = new Long(p_mutualFundAssetExecuted);
    mutual_fund_asset_executed_is_set = true;
    mutual_fund_asset_executed_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>mutual_fund_asset_executed</em>カラムに値を設定します。 
   */
  public final void setMutualFundAssetExecuted( Long p_mutualFundAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    mutual_fund_asset_executed = p_mutualFundAssetExecuted;
    mutual_fund_asset_executed_is_set = true;
    mutual_fund_asset_executed_is_modified = true;
  }


  /** 
   * <em>bond_asset_executed</em>カラムの値を設定します。 
   *
   * @@param p_bondAssetExecuted <em>bond_asset_executed</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setBondAssetExecuted( long p_bondAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bond_asset_executed = new Long(p_bondAssetExecuted);
    bond_asset_executed_is_set = true;
    bond_asset_executed_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>bond_asset_executed</em>カラムに値を設定します。 
   */
  public final void setBondAssetExecuted( Long p_bondAssetExecuted )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bond_asset_executed = p_bondAssetExecuted;
    bond_asset_executed_is_set = true;
    bond_asset_executed_is_modified = true;
  }


  /** 
   * <em>trading_stop</em>カラムの値を設定します。 
   *
   * @@param p_tradingStop <em>trading_stop</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTradingStop( String p_tradingStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trading_stop = p_tradingStop;
    trading_stop_is_set = true;
    trading_stop_is_modified = true;
  }


  /** 
   * <em>margin_open_position_stop</em>カラムの値を設定します。 
   *
   * @@param p_marginOpenPositionStop <em>margin_open_position_stop</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarginOpenPositionStop( String p_marginOpenPositionStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_open_position_stop = p_marginOpenPositionStop;
    margin_open_position_stop_is_set = true;
    margin_open_position_stop_is_modified = true;
  }


  /** 
   * <em>payment_stop</em>カラムの値を設定します。 
   *
   * @@param p_paymentStop <em>payment_stop</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setPaymentStop( String p_paymentStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_stop = p_paymentStop;
    payment_stop_is_set = true;
    payment_stop_is_modified = true;
  }


  /** 
   * <em>other_trading_stop</em>カラムの値を設定します。 
   *
   * @@param p_otherTradingStop <em>other_trading_stop</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOtherTradingStop( String p_otherTradingStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_trading_stop = p_otherTradingStop;
    other_trading_stop_is_set = true;
    other_trading_stop_is_modified = true;
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
                else if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("asset_evaluation_div") ) {
                    return this.asset_evaluation_div;
                }
                else if ( name.equals("account_balance_0") ) {
                    return this.account_balance_0;
                }
                else if ( name.equals("account_balance_1") ) {
                    return this.account_balance_1;
                }
                else if ( name.equals("account_balance_2") ) {
                    return this.account_balance_2;
                }
                else if ( name.equals("account_balance_3") ) {
                    return this.account_balance_3;
                }
                else if ( name.equals("account_balance_4") ) {
                    return this.account_balance_4;
                }
                else if ( name.equals("act_rec_trading_power_3") ) {
                    return this.act_rec_trading_power_3;
                }
                else if ( name.equals("act_rec_trading_power_4") ) {
                    return this.act_rec_trading_power_4;
                }
                else if ( name.equals("act_rec_trading_power_4_dash") ) {
                    return this.act_rec_trading_power_4_dash;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                else if ( name.equals("bond_asset_executed") ) {
                    return this.bond_asset_executed;
                }
                break;
            case 'c':
                if ( name.equals("calc_result_margin_id") ) {
                    return new Long( calc_result_margin_id );
                }
                else if ( name.equals("contract_asset_profit_loss") ) {
                    return this.contract_asset_profit_loss;
                }
                else if ( name.equals("contract_total_cost") ) {
                    return this.contract_total_cost;
                }
                else if ( name.equals("contract_amount_day_repay_0") ) {
                    return this.contract_amount_day_repay_0;
                }
                else if ( name.equals("contract_amount_day_repay_1") ) {
                    return this.contract_amount_day_repay_1;
                }
                else if ( name.equals("contract_amount_day_repay_2") ) {
                    return this.contract_amount_day_repay_2;
                }
                else if ( name.equals("contract_amount_day_repay_3") ) {
                    return this.contract_amount_day_repay_3;
                }
                else if ( name.equals("contract_amount_day_repay_4") ) {
                    return this.contract_amount_day_repay_4;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("day_trade_restraint_0") ) {
                    return this.day_trade_restraint_0;
                }
                else if ( name.equals("day_trade_restraint_1") ) {
                    return this.day_trade_restraint_1;
                }
                else if ( name.equals("day_trade_restraint_2") ) {
                    return this.day_trade_restraint_2;
                }
                else if ( name.equals("day_trade_restraint_3") ) {
                    return this.day_trade_restraint_3;
                }
                else if ( name.equals("day_trade_restraint_4") ) {
                    return this.day_trade_restraint_4;
                }
                else if ( name.equals("demandamount0") ) {
                    return this.demandamount0;
                }
                else if ( name.equals("demandamount1") ) {
                    return this.demandamount1;
                }
                else if ( name.equals("demandamount2") ) {
                    return this.demandamount2;
                }
                else if ( name.equals("demandamount3") ) {
                    return this.demandamount3;
                }
                else if ( name.equals("demandamount4") ) {
                    return this.demandamount4;
                }
                break;
            case 'e':
                if ( name.equals("equity_trading_power_3") ) {
                    return this.equity_trading_power_3;
                }
                else if ( name.equals("equity_trading_power_4") ) {
                    return this.equity_trading_power_4;
                }
                else if ( name.equals("equity_trading_power_4_dash") ) {
                    return this.equity_trading_power_4_dash;
                }
                else if ( name.equals("equity_asset_executed") ) {
                    return this.equity_asset_executed;
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
                if ( name.equals("margin_account_balance_0") ) {
                    return this.margin_account_balance_0;
                }
                else if ( name.equals("margin_account_balance_1") ) {
                    return this.margin_account_balance_1;
                }
                else if ( name.equals("margin_account_balance_2") ) {
                    return this.margin_account_balance_2;
                }
                else if ( name.equals("margin_account_balance_3") ) {
                    return this.margin_account_balance_3;
                }
                else if ( name.equals("margin_account_balance_4") ) {
                    return this.margin_account_balance_4;
                }
                else if ( name.equals("margin_power_0") ) {
                    return this.margin_power_0;
                }
                else if ( name.equals("margin_power_1") ) {
                    return this.margin_power_1;
                }
                else if ( name.equals("margin_power_2") ) {
                    return this.margin_power_2;
                }
                else if ( name.equals("margin_power_3") ) {
                    return this.margin_power_3;
                }
                else if ( name.equals("margin_power_4") ) {
                    return this.margin_power_4;
                }
                else if ( name.equals("margin_trading_power_1") ) {
                    return this.margin_trading_power_1;
                }
                else if ( name.equals("margin_trading_power_2") ) {
                    return this.margin_trading_power_2;
                }
                else if ( name.equals("margin_trading_power_3") ) {
                    return this.margin_trading_power_3;
                }
                else if ( name.equals("margin_trading_power_4") ) {
                    return this.margin_trading_power_4;
                }
                else if ( name.equals("margin_deposit_rate_0") ) {
                    return this.margin_deposit_rate_0;
                }
                else if ( name.equals("margin_deposit_rate_1") ) {
                    return this.margin_deposit_rate_1;
                }
                else if ( name.equals("margin_deposit_rate_2") ) {
                    return this.margin_deposit_rate_2;
                }
                else if ( name.equals("margin_deposit_rate_3") ) {
                    return this.margin_deposit_rate_3;
                }
                else if ( name.equals("margin_deposit_rate_4") ) {
                    return this.margin_deposit_rate_4;
                }
                else if ( name.equals("margin_draw_power_0") ) {
                    return this.margin_draw_power_0;
                }
                else if ( name.equals("margin_draw_power_1") ) {
                    return this.margin_draw_power_1;
                }
                else if ( name.equals("margin_draw_power_2") ) {
                    return this.margin_draw_power_2;
                }
                else if ( name.equals("margin_draw_power_3") ) {
                    return this.margin_draw_power_3;
                }
                else if ( name.equals("margin_draw_power_4") ) {
                    return this.margin_draw_power_4;
                }
                else if ( name.equals("margin_sec_product_code") ) {
                    return this.margin_sec_product_code;
                }
                else if ( name.equals("margin_sec_rate") ) {
                    return this.margin_sec_rate;
                }
                else if ( name.equals("mutual_fund_asset_executed") ) {
                    return this.mutual_fund_asset_executed;
                }
                else if ( name.equals("margin_open_position_stop") ) {
                    return this.margin_open_position_stop;
                }
                break;
            case 'o':
                if ( name.equals("other_restraint_0") ) {
                    return this.other_restraint_0;
                }
                else if ( name.equals("other_restraint_1") ) {
                    return this.other_restraint_1;
                }
                else if ( name.equals("other_restraint_2") ) {
                    return this.other_restraint_2;
                }
                else if ( name.equals("other_restraint_3") ) {
                    return this.other_restraint_3;
                }
                else if ( name.equals("other_restraint_4") ) {
                    return this.other_restraint_4;
                }
                else if ( name.equals("other_trading_power_1") ) {
                    return this.other_trading_power_1;
                }
                else if ( name.equals("other_trading_power_2") ) {
                    return this.other_trading_power_2;
                }
                else if ( name.equals("other_trading_power_3") ) {
                    return this.other_trading_power_3;
                }
                else if ( name.equals("other_trading_power_4") ) {
                    return this.other_trading_power_4;
                }
                else if ( name.equals("other_trading_stop") ) {
                    return this.other_trading_stop;
                }
                break;
            case 'p':
                if ( name.equals("payment_amount_designate0") ) {
                    return this.payment_amount_designate0;
                }
                else if ( name.equals("payment_amount_designate1") ) {
                    return this.payment_amount_designate1;
                }
                else if ( name.equals("payment_amount_designate2") ) {
                    return this.payment_amount_designate2;
                }
                else if ( name.equals("payment_stop") ) {
                    return this.payment_stop;
                }
                break;
            case 'r':
                if ( name.equals("ruito_asset_executed") ) {
                    return this.ruito_asset_executed;
                }
                break;
            case 's':
                if ( name.equals("substitute_security_asset_0") ) {
                    return this.substitute_security_asset_0;
                }
                else if ( name.equals("substitute_security_asset_1") ) {
                    return this.substitute_security_asset_1;
                }
                else if ( name.equals("substitute_security_asset_2") ) {
                    return this.substitute_security_asset_2;
                }
                else if ( name.equals("substitute_security_asset_3") ) {
                    return this.substitute_security_asset_3;
                }
                else if ( name.equals("substitute_security_asset_4") ) {
                    return this.substitute_security_asset_4;
                }
                break;
            case 't':
                if ( name.equals("today_unexecuted_amount_1") ) {
                    return this.today_unexecuted_amount_1;
                }
                else if ( name.equals("today_unexecuted_amount_2") ) {
                    return this.today_unexecuted_amount_2;
                }
                else if ( name.equals("today_unexecuted_amount_3") ) {
                    return this.today_unexecuted_amount_3;
                }
                else if ( name.equals("today_unexecuted_amount_4") ) {
                    return this.today_unexecuted_amount_4;
                }
                else if ( name.equals("today_repay_contract_pre_asset") ) {
                    return this.today_repay_contract_pre_asset;
                }
                else if ( name.equals("trading_stop") ) {
                    return this.trading_stop;
                }
                break;
            case 'u':
                if ( name.equals("undeli_contract_loss_0") ) {
                    return this.undeli_contract_loss_0;
                }
                else if ( name.equals("undeli_contract_loss_1") ) {
                    return this.undeli_contract_loss_1;
                }
                else if ( name.equals("undeli_contract_loss_2") ) {
                    return this.undeli_contract_loss_2;
                }
                else if ( name.equals("undeli_contract_loss_3") ) {
                    return this.undeli_contract_loss_3;
                }
                else if ( name.equals("undeli_contract_amount_0") ) {
                    return this.undeli_contract_amount_0;
                }
                else if ( name.equals("undeli_contract_amount_1") ) {
                    return this.undeli_contract_amount_1;
                }
                else if ( name.equals("undeli_contract_amount_2") ) {
                    return this.undeli_contract_amount_2;
                }
                else if ( name.equals("undeli_contract_amount_3") ) {
                    return this.undeli_contract_amount_3;
                }
                break;
            case 'w':
                if ( name.equals("work_date") ) {
                    return this.work_date;
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
                else if ( name.equals("account_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("asset_evaluation_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'asset_evaluation_div' must be String: '"+value+"' is not." );
                    this.asset_evaluation_div = (String) value;
                    if (this.asset_evaluation_div_is_set)
                        this.asset_evaluation_div_is_modified = true;
                    this.asset_evaluation_div_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_balance_0' must be Long: '"+value+"' is not." );
                    this.account_balance_0 = (Long) value;
                    if (this.account_balance_0_is_set)
                        this.account_balance_0_is_modified = true;
                    this.account_balance_0_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_balance_1' must be Long: '"+value+"' is not." );
                    this.account_balance_1 = (Long) value;
                    if (this.account_balance_1_is_set)
                        this.account_balance_1_is_modified = true;
                    this.account_balance_1_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_balance_2' must be Long: '"+value+"' is not." );
                    this.account_balance_2 = (Long) value;
                    if (this.account_balance_2_is_set)
                        this.account_balance_2_is_modified = true;
                    this.account_balance_2_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_balance_3' must be Long: '"+value+"' is not." );
                    this.account_balance_3 = (Long) value;
                    if (this.account_balance_3_is_set)
                        this.account_balance_3_is_modified = true;
                    this.account_balance_3_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'account_balance_4' must be Long: '"+value+"' is not." );
                    this.account_balance_4 = (Long) value;
                    if (this.account_balance_4_is_set)
                        this.account_balance_4_is_modified = true;
                    this.account_balance_4_is_set = true;
                    return;
                }
                else if ( name.equals("act_rec_trading_power_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'act_rec_trading_power_3' must be Long: '"+value+"' is not." );
                    this.act_rec_trading_power_3 = (Long) value;
                    if (this.act_rec_trading_power_3_is_set)
                        this.act_rec_trading_power_3_is_modified = true;
                    this.act_rec_trading_power_3_is_set = true;
                    return;
                }
                else if ( name.equals("act_rec_trading_power_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'act_rec_trading_power_4' must be Long: '"+value+"' is not." );
                    this.act_rec_trading_power_4 = (Long) value;
                    if (this.act_rec_trading_power_4_is_set)
                        this.act_rec_trading_power_4_is_modified = true;
                    this.act_rec_trading_power_4_is_set = true;
                    return;
                }
                else if ( name.equals("act_rec_trading_power_4_dash") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'act_rec_trading_power_4_dash' must be Long: '"+value+"' is not." );
                    this.act_rec_trading_power_4_dash = (Long) value;
                    if (this.act_rec_trading_power_4_dash_is_set)
                        this.act_rec_trading_power_4_dash_is_modified = true;
                    this.act_rec_trading_power_4_dash_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
                    return;
                }
                else if ( name.equals("bond_asset_executed") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'bond_asset_executed' must be Long: '"+value+"' is not." );
                    this.bond_asset_executed = (Long) value;
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
                else if ( name.equals("contract_asset_profit_loss") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'contract_asset_profit_loss' must be Long: '"+value+"' is not." );
                    this.contract_asset_profit_loss = (Long) value;
                    if (this.contract_asset_profit_loss_is_set)
                        this.contract_asset_profit_loss_is_modified = true;
                    this.contract_asset_profit_loss_is_set = true;
                    return;
                }
                else if ( name.equals("contract_total_cost") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'contract_total_cost' must be Long: '"+value+"' is not." );
                    this.contract_total_cost = (Long) value;
                    if (this.contract_total_cost_is_set)
                        this.contract_total_cost_is_modified = true;
                    this.contract_total_cost_is_set = true;
                    return;
                }
                else if ( name.equals("contract_amount_day_repay_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'contract_amount_day_repay_0' must be Long: '"+value+"' is not." );
                    this.contract_amount_day_repay_0 = (Long) value;
                    if (this.contract_amount_day_repay_0_is_set)
                        this.contract_amount_day_repay_0_is_modified = true;
                    this.contract_amount_day_repay_0_is_set = true;
                    return;
                }
                else if ( name.equals("contract_amount_day_repay_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'contract_amount_day_repay_1' must be Long: '"+value+"' is not." );
                    this.contract_amount_day_repay_1 = (Long) value;
                    if (this.contract_amount_day_repay_1_is_set)
                        this.contract_amount_day_repay_1_is_modified = true;
                    this.contract_amount_day_repay_1_is_set = true;
                    return;
                }
                else if ( name.equals("contract_amount_day_repay_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'contract_amount_day_repay_2' must be Long: '"+value+"' is not." );
                    this.contract_amount_day_repay_2 = (Long) value;
                    if (this.contract_amount_day_repay_2_is_set)
                        this.contract_amount_day_repay_2_is_modified = true;
                    this.contract_amount_day_repay_2_is_set = true;
                    return;
                }
                else if ( name.equals("contract_amount_day_repay_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'contract_amount_day_repay_3' must be Long: '"+value+"' is not." );
                    this.contract_amount_day_repay_3 = (Long) value;
                    if (this.contract_amount_day_repay_3_is_set)
                        this.contract_amount_day_repay_3_is_modified = true;
                    this.contract_amount_day_repay_3_is_set = true;
                    return;
                }
                else if ( name.equals("contract_amount_day_repay_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'contract_amount_day_repay_4' must be Long: '"+value+"' is not." );
                    this.contract_amount_day_repay_4 = (Long) value;
                    if (this.contract_amount_day_repay_4_is_set)
                        this.contract_amount_day_repay_4_is_modified = true;
                    this.contract_amount_day_repay_4_is_set = true;
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
                if ( name.equals("day_trade_restraint_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'day_trade_restraint_0' must be Long: '"+value+"' is not." );
                    this.day_trade_restraint_0 = (Long) value;
                    if (this.day_trade_restraint_0_is_set)
                        this.day_trade_restraint_0_is_modified = true;
                    this.day_trade_restraint_0_is_set = true;
                    return;
                }
                else if ( name.equals("day_trade_restraint_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'day_trade_restraint_1' must be Long: '"+value+"' is not." );
                    this.day_trade_restraint_1 = (Long) value;
                    if (this.day_trade_restraint_1_is_set)
                        this.day_trade_restraint_1_is_modified = true;
                    this.day_trade_restraint_1_is_set = true;
                    return;
                }
                else if ( name.equals("day_trade_restraint_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'day_trade_restraint_2' must be Long: '"+value+"' is not." );
                    this.day_trade_restraint_2 = (Long) value;
                    if (this.day_trade_restraint_2_is_set)
                        this.day_trade_restraint_2_is_modified = true;
                    this.day_trade_restraint_2_is_set = true;
                    return;
                }
                else if ( name.equals("day_trade_restraint_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'day_trade_restraint_3' must be Long: '"+value+"' is not." );
                    this.day_trade_restraint_3 = (Long) value;
                    if (this.day_trade_restraint_3_is_set)
                        this.day_trade_restraint_3_is_modified = true;
                    this.day_trade_restraint_3_is_set = true;
                    return;
                }
                else if ( name.equals("day_trade_restraint_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'day_trade_restraint_4' must be Long: '"+value+"' is not." );
                    this.day_trade_restraint_4 = (Long) value;
                    if (this.day_trade_restraint_4_is_set)
                        this.day_trade_restraint_4_is_modified = true;
                    this.day_trade_restraint_4_is_set = true;
                    return;
                }
                else if ( name.equals("demandamount0") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'demandamount0' must be Long: '"+value+"' is not." );
                    this.demandamount0 = (Long) value;
                    if (this.demandamount0_is_set)
                        this.demandamount0_is_modified = true;
                    this.demandamount0_is_set = true;
                    return;
                }
                else if ( name.equals("demandamount1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'demandamount1' must be Long: '"+value+"' is not." );
                    this.demandamount1 = (Long) value;
                    if (this.demandamount1_is_set)
                        this.demandamount1_is_modified = true;
                    this.demandamount1_is_set = true;
                    return;
                }
                else if ( name.equals("demandamount2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'demandamount2' must be Long: '"+value+"' is not." );
                    this.demandamount2 = (Long) value;
                    if (this.demandamount2_is_set)
                        this.demandamount2_is_modified = true;
                    this.demandamount2_is_set = true;
                    return;
                }
                else if ( name.equals("demandamount3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'demandamount3' must be Long: '"+value+"' is not." );
                    this.demandamount3 = (Long) value;
                    if (this.demandamount3_is_set)
                        this.demandamount3_is_modified = true;
                    this.demandamount3_is_set = true;
                    return;
                }
                else if ( name.equals("demandamount4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'demandamount4' must be Long: '"+value+"' is not." );
                    this.demandamount4 = (Long) value;
                    if (this.demandamount4_is_set)
                        this.demandamount4_is_modified = true;
                    this.demandamount4_is_set = true;
                    return;
                }
                break;
            case 'e':
                if ( name.equals("equity_trading_power_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'equity_trading_power_3' must be Long: '"+value+"' is not." );
                    this.equity_trading_power_3 = (Long) value;
                    if (this.equity_trading_power_3_is_set)
                        this.equity_trading_power_3_is_modified = true;
                    this.equity_trading_power_3_is_set = true;
                    return;
                }
                else if ( name.equals("equity_trading_power_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'equity_trading_power_4' must be Long: '"+value+"' is not." );
                    this.equity_trading_power_4 = (Long) value;
                    if (this.equity_trading_power_4_is_set)
                        this.equity_trading_power_4_is_modified = true;
                    this.equity_trading_power_4_is_set = true;
                    return;
                }
                else if ( name.equals("equity_trading_power_4_dash") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'equity_trading_power_4_dash' must be Long: '"+value+"' is not." );
                    this.equity_trading_power_4_dash = (Long) value;
                    if (this.equity_trading_power_4_dash_is_set)
                        this.equity_trading_power_4_dash_is_modified = true;
                    this.equity_trading_power_4_dash_is_set = true;
                    return;
                }
                else if ( name.equals("equity_asset_executed") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'equity_asset_executed' must be Long: '"+value+"' is not." );
                    this.equity_asset_executed = (Long) value;
                    if (this.equity_asset_executed_is_set)
                        this.equity_asset_executed_is_modified = true;
                    this.equity_asset_executed_is_set = true;
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
                if ( name.equals("margin_account_balance_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_account_balance_0' must be Long: '"+value+"' is not." );
                    this.margin_account_balance_0 = (Long) value;
                    if (this.margin_account_balance_0_is_set)
                        this.margin_account_balance_0_is_modified = true;
                    this.margin_account_balance_0_is_set = true;
                    return;
                }
                else if ( name.equals("margin_account_balance_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_account_balance_1' must be Long: '"+value+"' is not." );
                    this.margin_account_balance_1 = (Long) value;
                    if (this.margin_account_balance_1_is_set)
                        this.margin_account_balance_1_is_modified = true;
                    this.margin_account_balance_1_is_set = true;
                    return;
                }
                else if ( name.equals("margin_account_balance_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_account_balance_2' must be Long: '"+value+"' is not." );
                    this.margin_account_balance_2 = (Long) value;
                    if (this.margin_account_balance_2_is_set)
                        this.margin_account_balance_2_is_modified = true;
                    this.margin_account_balance_2_is_set = true;
                    return;
                }
                else if ( name.equals("margin_account_balance_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_account_balance_3' must be Long: '"+value+"' is not." );
                    this.margin_account_balance_3 = (Long) value;
                    if (this.margin_account_balance_3_is_set)
                        this.margin_account_balance_3_is_modified = true;
                    this.margin_account_balance_3_is_set = true;
                    return;
                }
                else if ( name.equals("margin_account_balance_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_account_balance_4' must be Long: '"+value+"' is not." );
                    this.margin_account_balance_4 = (Long) value;
                    if (this.margin_account_balance_4_is_set)
                        this.margin_account_balance_4_is_modified = true;
                    this.margin_account_balance_4_is_set = true;
                    return;
                }
                else if ( name.equals("margin_power_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_power_0' must be Long: '"+value+"' is not." );
                    this.margin_power_0 = (Long) value;
                    if (this.margin_power_0_is_set)
                        this.margin_power_0_is_modified = true;
                    this.margin_power_0_is_set = true;
                    return;
                }
                else if ( name.equals("margin_power_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_power_1' must be Long: '"+value+"' is not." );
                    this.margin_power_1 = (Long) value;
                    if (this.margin_power_1_is_set)
                        this.margin_power_1_is_modified = true;
                    this.margin_power_1_is_set = true;
                    return;
                }
                else if ( name.equals("margin_power_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_power_2' must be Long: '"+value+"' is not." );
                    this.margin_power_2 = (Long) value;
                    if (this.margin_power_2_is_set)
                        this.margin_power_2_is_modified = true;
                    this.margin_power_2_is_set = true;
                    return;
                }
                else if ( name.equals("margin_power_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_power_3' must be Long: '"+value+"' is not." );
                    this.margin_power_3 = (Long) value;
                    if (this.margin_power_3_is_set)
                        this.margin_power_3_is_modified = true;
                    this.margin_power_3_is_set = true;
                    return;
                }
                else if ( name.equals("margin_power_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_power_4' must be Long: '"+value+"' is not." );
                    this.margin_power_4 = (Long) value;
                    if (this.margin_power_4_is_set)
                        this.margin_power_4_is_modified = true;
                    this.margin_power_4_is_set = true;
                    return;
                }
                else if ( name.equals("margin_trading_power_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_trading_power_1' must be Long: '"+value+"' is not." );
                    this.margin_trading_power_1 = (Long) value;
                    if (this.margin_trading_power_1_is_set)
                        this.margin_trading_power_1_is_modified = true;
                    this.margin_trading_power_1_is_set = true;
                    return;
                }
                else if ( name.equals("margin_trading_power_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_trading_power_2' must be Long: '"+value+"' is not." );
                    this.margin_trading_power_2 = (Long) value;
                    if (this.margin_trading_power_2_is_set)
                        this.margin_trading_power_2_is_modified = true;
                    this.margin_trading_power_2_is_set = true;
                    return;
                }
                else if ( name.equals("margin_trading_power_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_trading_power_3' must be Long: '"+value+"' is not." );
                    this.margin_trading_power_3 = (Long) value;
                    if (this.margin_trading_power_3_is_set)
                        this.margin_trading_power_3_is_modified = true;
                    this.margin_trading_power_3_is_set = true;
                    return;
                }
                else if ( name.equals("margin_trading_power_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_trading_power_4' must be Long: '"+value+"' is not." );
                    this.margin_trading_power_4 = (Long) value;
                    if (this.margin_trading_power_4_is_set)
                        this.margin_trading_power_4_is_modified = true;
                    this.margin_trading_power_4_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_rate_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_rate_0' must be Double: '"+value+"' is not." );
                    this.margin_deposit_rate_0 = (Double) value;
                    if (this.margin_deposit_rate_0_is_set)
                        this.margin_deposit_rate_0_is_modified = true;
                    this.margin_deposit_rate_0_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_rate_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_rate_1' must be Double: '"+value+"' is not." );
                    this.margin_deposit_rate_1 = (Double) value;
                    if (this.margin_deposit_rate_1_is_set)
                        this.margin_deposit_rate_1_is_modified = true;
                    this.margin_deposit_rate_1_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_rate_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_rate_2' must be Double: '"+value+"' is not." );
                    this.margin_deposit_rate_2 = (Double) value;
                    if (this.margin_deposit_rate_2_is_set)
                        this.margin_deposit_rate_2_is_modified = true;
                    this.margin_deposit_rate_2_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_rate_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_rate_3' must be Double: '"+value+"' is not." );
                    this.margin_deposit_rate_3 = (Double) value;
                    if (this.margin_deposit_rate_3_is_set)
                        this.margin_deposit_rate_3_is_modified = true;
                    this.margin_deposit_rate_3_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_rate_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_rate_4' must be Double: '"+value+"' is not." );
                    this.margin_deposit_rate_4 = (Double) value;
                    if (this.margin_deposit_rate_4_is_set)
                        this.margin_deposit_rate_4_is_modified = true;
                    this.margin_deposit_rate_4_is_set = true;
                    return;
                }
                else if ( name.equals("margin_draw_power_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_draw_power_0' must be Long: '"+value+"' is not." );
                    this.margin_draw_power_0 = (Long) value;
                    if (this.margin_draw_power_0_is_set)
                        this.margin_draw_power_0_is_modified = true;
                    this.margin_draw_power_0_is_set = true;
                    return;
                }
                else if ( name.equals("margin_draw_power_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_draw_power_1' must be Long: '"+value+"' is not." );
                    this.margin_draw_power_1 = (Long) value;
                    if (this.margin_draw_power_1_is_set)
                        this.margin_draw_power_1_is_modified = true;
                    this.margin_draw_power_1_is_set = true;
                    return;
                }
                else if ( name.equals("margin_draw_power_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_draw_power_2' must be Long: '"+value+"' is not." );
                    this.margin_draw_power_2 = (Long) value;
                    if (this.margin_draw_power_2_is_set)
                        this.margin_draw_power_2_is_modified = true;
                    this.margin_draw_power_2_is_set = true;
                    return;
                }
                else if ( name.equals("margin_draw_power_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_draw_power_3' must be Long: '"+value+"' is not." );
                    this.margin_draw_power_3 = (Long) value;
                    if (this.margin_draw_power_3_is_set)
                        this.margin_draw_power_3_is_modified = true;
                    this.margin_draw_power_3_is_set = true;
                    return;
                }
                else if ( name.equals("margin_draw_power_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'margin_draw_power_4' must be Long: '"+value+"' is not." );
                    this.margin_draw_power_4 = (Long) value;
                    if (this.margin_draw_power_4_is_set)
                        this.margin_draw_power_4_is_modified = true;
                    this.margin_draw_power_4_is_set = true;
                    return;
                }
                else if ( name.equals("margin_sec_product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_sec_product_code' must be String: '"+value+"' is not." );
                    this.margin_sec_product_code = (String) value;
                    if (this.margin_sec_product_code_is_set)
                        this.margin_sec_product_code_is_modified = true;
                    this.margin_sec_product_code_is_set = true;
                    return;
                }
                else if ( name.equals("margin_sec_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_sec_rate' must be Double: '"+value+"' is not." );
                    this.margin_sec_rate = (Double) value;
                    if (this.margin_sec_rate_is_set)
                        this.margin_sec_rate_is_modified = true;
                    this.margin_sec_rate_is_set = true;
                    return;
                }
                else if ( name.equals("mutual_fund_asset_executed") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'mutual_fund_asset_executed' must be Long: '"+value+"' is not." );
                    this.mutual_fund_asset_executed = (Long) value;
                    if (this.mutual_fund_asset_executed_is_set)
                        this.mutual_fund_asset_executed_is_modified = true;
                    this.mutual_fund_asset_executed_is_set = true;
                    return;
                }
                else if ( name.equals("margin_open_position_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'margin_open_position_stop' must be String: '"+value+"' is not." );
                    this.margin_open_position_stop = (String) value;
                    if (this.margin_open_position_stop_is_set)
                        this.margin_open_position_stop_is_modified = true;
                    this.margin_open_position_stop_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("other_restraint_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'other_restraint_0' must be Long: '"+value+"' is not." );
                    this.other_restraint_0 = (Long) value;
                    if (this.other_restraint_0_is_set)
                        this.other_restraint_0_is_modified = true;
                    this.other_restraint_0_is_set = true;
                    return;
                }
                else if ( name.equals("other_restraint_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'other_restraint_1' must be Long: '"+value+"' is not." );
                    this.other_restraint_1 = (Long) value;
                    if (this.other_restraint_1_is_set)
                        this.other_restraint_1_is_modified = true;
                    this.other_restraint_1_is_set = true;
                    return;
                }
                else if ( name.equals("other_restraint_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'other_restraint_2' must be Long: '"+value+"' is not." );
                    this.other_restraint_2 = (Long) value;
                    if (this.other_restraint_2_is_set)
                        this.other_restraint_2_is_modified = true;
                    this.other_restraint_2_is_set = true;
                    return;
                }
                else if ( name.equals("other_restraint_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'other_restraint_3' must be Long: '"+value+"' is not." );
                    this.other_restraint_3 = (Long) value;
                    if (this.other_restraint_3_is_set)
                        this.other_restraint_3_is_modified = true;
                    this.other_restraint_3_is_set = true;
                    return;
                }
                else if ( name.equals("other_restraint_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'other_restraint_4' must be Long: '"+value+"' is not." );
                    this.other_restraint_4 = (Long) value;
                    if (this.other_restraint_4_is_set)
                        this.other_restraint_4_is_modified = true;
                    this.other_restraint_4_is_set = true;
                    return;
                }
                else if ( name.equals("other_trading_power_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'other_trading_power_1' must be Long: '"+value+"' is not." );
                    this.other_trading_power_1 = (Long) value;
                    if (this.other_trading_power_1_is_set)
                        this.other_trading_power_1_is_modified = true;
                    this.other_trading_power_1_is_set = true;
                    return;
                }
                else if ( name.equals("other_trading_power_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'other_trading_power_2' must be Long: '"+value+"' is not." );
                    this.other_trading_power_2 = (Long) value;
                    if (this.other_trading_power_2_is_set)
                        this.other_trading_power_2_is_modified = true;
                    this.other_trading_power_2_is_set = true;
                    return;
                }
                else if ( name.equals("other_trading_power_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'other_trading_power_3' must be Long: '"+value+"' is not." );
                    this.other_trading_power_3 = (Long) value;
                    if (this.other_trading_power_3_is_set)
                        this.other_trading_power_3_is_modified = true;
                    this.other_trading_power_3_is_set = true;
                    return;
                }
                else if ( name.equals("other_trading_power_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'other_trading_power_4' must be Long: '"+value+"' is not." );
                    this.other_trading_power_4 = (Long) value;
                    if (this.other_trading_power_4_is_set)
                        this.other_trading_power_4_is_modified = true;
                    this.other_trading_power_4_is_set = true;
                    return;
                }
                else if ( name.equals("other_trading_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'other_trading_stop' must be String: '"+value+"' is not." );
                    this.other_trading_stop = (String) value;
                    if (this.other_trading_stop_is_set)
                        this.other_trading_stop_is_modified = true;
                    this.other_trading_stop_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("payment_amount_designate0") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate0' must be Long: '"+value+"' is not." );
                    this.payment_amount_designate0 = (Long) value;
                    if (this.payment_amount_designate0_is_set)
                        this.payment_amount_designate0_is_modified = true;
                    this.payment_amount_designate0_is_set = true;
                    return;
                }
                else if ( name.equals("payment_amount_designate1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate1' must be Long: '"+value+"' is not." );
                    this.payment_amount_designate1 = (Long) value;
                    if (this.payment_amount_designate1_is_set)
                        this.payment_amount_designate1_is_modified = true;
                    this.payment_amount_designate1_is_set = true;
                    return;
                }
                else if ( name.equals("payment_amount_designate2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate2' must be Long: '"+value+"' is not." );
                    this.payment_amount_designate2 = (Long) value;
                    if (this.payment_amount_designate2_is_set)
                        this.payment_amount_designate2_is_modified = true;
                    this.payment_amount_designate2_is_set = true;
                    return;
                }
                else if ( name.equals("payment_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_stop' must be String: '"+value+"' is not." );
                    this.payment_stop = (String) value;
                    if (this.payment_stop_is_set)
                        this.payment_stop_is_modified = true;
                    this.payment_stop_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("ruito_asset_executed") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'ruito_asset_executed' must be Long: '"+value+"' is not." );
                    this.ruito_asset_executed = (Long) value;
                    if (this.ruito_asset_executed_is_set)
                        this.ruito_asset_executed_is_modified = true;
                    this.ruito_asset_executed_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("substitute_security_asset_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'substitute_security_asset_0' must be Long: '"+value+"' is not." );
                    this.substitute_security_asset_0 = (Long) value;
                    if (this.substitute_security_asset_0_is_set)
                        this.substitute_security_asset_0_is_modified = true;
                    this.substitute_security_asset_0_is_set = true;
                    return;
                }
                else if ( name.equals("substitute_security_asset_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'substitute_security_asset_1' must be Long: '"+value+"' is not." );
                    this.substitute_security_asset_1 = (Long) value;
                    if (this.substitute_security_asset_1_is_set)
                        this.substitute_security_asset_1_is_modified = true;
                    this.substitute_security_asset_1_is_set = true;
                    return;
                }
                else if ( name.equals("substitute_security_asset_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'substitute_security_asset_2' must be Long: '"+value+"' is not." );
                    this.substitute_security_asset_2 = (Long) value;
                    if (this.substitute_security_asset_2_is_set)
                        this.substitute_security_asset_2_is_modified = true;
                    this.substitute_security_asset_2_is_set = true;
                    return;
                }
                else if ( name.equals("substitute_security_asset_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'substitute_security_asset_3' must be Long: '"+value+"' is not." );
                    this.substitute_security_asset_3 = (Long) value;
                    if (this.substitute_security_asset_3_is_set)
                        this.substitute_security_asset_3_is_modified = true;
                    this.substitute_security_asset_3_is_set = true;
                    return;
                }
                else if ( name.equals("substitute_security_asset_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'substitute_security_asset_4' must be Long: '"+value+"' is not." );
                    this.substitute_security_asset_4 = (Long) value;
                    if (this.substitute_security_asset_4_is_set)
                        this.substitute_security_asset_4_is_modified = true;
                    this.substitute_security_asset_4_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("today_unexecuted_amount_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'today_unexecuted_amount_1' must be Long: '"+value+"' is not." );
                    this.today_unexecuted_amount_1 = (Long) value;
                    if (this.today_unexecuted_amount_1_is_set)
                        this.today_unexecuted_amount_1_is_modified = true;
                    this.today_unexecuted_amount_1_is_set = true;
                    return;
                }
                else if ( name.equals("today_unexecuted_amount_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'today_unexecuted_amount_2' must be Long: '"+value+"' is not." );
                    this.today_unexecuted_amount_2 = (Long) value;
                    if (this.today_unexecuted_amount_2_is_set)
                        this.today_unexecuted_amount_2_is_modified = true;
                    this.today_unexecuted_amount_2_is_set = true;
                    return;
                }
                else if ( name.equals("today_unexecuted_amount_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'today_unexecuted_amount_3' must be Long: '"+value+"' is not." );
                    this.today_unexecuted_amount_3 = (Long) value;
                    if (this.today_unexecuted_amount_3_is_set)
                        this.today_unexecuted_amount_3_is_modified = true;
                    this.today_unexecuted_amount_3_is_set = true;
                    return;
                }
                else if ( name.equals("today_unexecuted_amount_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'today_unexecuted_amount_4' must be Long: '"+value+"' is not." );
                    this.today_unexecuted_amount_4 = (Long) value;
                    if (this.today_unexecuted_amount_4_is_set)
                        this.today_unexecuted_amount_4_is_modified = true;
                    this.today_unexecuted_amount_4_is_set = true;
                    return;
                }
                else if ( name.equals("today_repay_contract_pre_asset") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'today_repay_contract_pre_asset' must be Long: '"+value+"' is not." );
                    this.today_repay_contract_pre_asset = (Long) value;
                    if (this.today_repay_contract_pre_asset_is_set)
                        this.today_repay_contract_pre_asset_is_modified = true;
                    this.today_repay_contract_pre_asset_is_set = true;
                    return;
                }
                else if ( name.equals("trading_stop") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trading_stop' must be String: '"+value+"' is not." );
                    this.trading_stop = (String) value;
                    if (this.trading_stop_is_set)
                        this.trading_stop_is_modified = true;
                    this.trading_stop_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("undeli_contract_loss_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_loss_0' must be Long: '"+value+"' is not." );
                    this.undeli_contract_loss_0 = (Long) value;
                    if (this.undeli_contract_loss_0_is_set)
                        this.undeli_contract_loss_0_is_modified = true;
                    this.undeli_contract_loss_0_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_loss_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_loss_1' must be Long: '"+value+"' is not." );
                    this.undeli_contract_loss_1 = (Long) value;
                    if (this.undeli_contract_loss_1_is_set)
                        this.undeli_contract_loss_1_is_modified = true;
                    this.undeli_contract_loss_1_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_loss_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_loss_2' must be Long: '"+value+"' is not." );
                    this.undeli_contract_loss_2 = (Long) value;
                    if (this.undeli_contract_loss_2_is_set)
                        this.undeli_contract_loss_2_is_modified = true;
                    this.undeli_contract_loss_2_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_loss_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_loss_3' must be Long: '"+value+"' is not." );
                    this.undeli_contract_loss_3 = (Long) value;
                    if (this.undeli_contract_loss_3_is_set)
                        this.undeli_contract_loss_3_is_modified = true;
                    this.undeli_contract_loss_3_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_amount_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_amount_0' must be Long: '"+value+"' is not." );
                    this.undeli_contract_amount_0 = (Long) value;
                    if (this.undeli_contract_amount_0_is_set)
                        this.undeli_contract_amount_0_is_modified = true;
                    this.undeli_contract_amount_0_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_amount_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_amount_1' must be Long: '"+value+"' is not." );
                    this.undeli_contract_amount_1 = (Long) value;
                    if (this.undeli_contract_amount_1_is_set)
                        this.undeli_contract_amount_1_is_modified = true;
                    this.undeli_contract_amount_1_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_amount_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_amount_2' must be Long: '"+value+"' is not." );
                    this.undeli_contract_amount_2 = (Long) value;
                    if (this.undeli_contract_amount_2_is_set)
                        this.undeli_contract_amount_2_is_modified = true;
                    this.undeli_contract_amount_2_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_amount_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_amount_3' must be Long: '"+value+"' is not." );
                    this.undeli_contract_amount_3 = (Long) value;
                    if (this.undeli_contract_amount_3_is_set)
                        this.undeli_contract_amount_3_is_modified = true;
                    this.undeli_contract_amount_3_is_set = true;
                    return;
                }
                break;
            case 'w':
                if ( name.equals("work_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'work_date' must be String: '"+value+"' is not." );
                    this.work_date = (String) value;
                    if (this.work_date_is_set)
                        this.work_date_is_modified = true;
                    this.work_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
