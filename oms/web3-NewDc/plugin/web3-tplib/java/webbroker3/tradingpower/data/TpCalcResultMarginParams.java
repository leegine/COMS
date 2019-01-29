head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.30.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCalcResultMarginParams.java;


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
 * tp_calc_result_marginテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link TpCalcResultMarginRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link TpCalcResultMarginParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link TpCalcResultMarginParams}が{@@link TpCalcResultMarginRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpCalcResultMarginPK 
 * @@see TpCalcResultMarginRow 
 */
public class TpCalcResultMarginParams extends Params implements TpCalcResultMarginRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "tp_calc_result_margin";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = TpCalcResultMarginRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return TpCalcResultMarginRow.TYPE;
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
   * <em>account_balance_0</em>カラムの値 
   */
  public  double  account_balance_0;

  /** 
   * <em>account_balance_1</em>カラムの値 
   */
  public  double  account_balance_1;

  /** 
   * <em>account_balance_2</em>カラムの値 
   */
  public  double  account_balance_2;

  /** 
   * <em>account_balance_3</em>カラムの値 
   */
  public  double  account_balance_3;

  /** 
   * <em>account_balance_4</em>カラムの値 
   */
  public  double  account_balance_4;

  /** 
   * <em>account_balance_5</em>カラムの値 
   */
  public  double  account_balance_5;

  /** 
   * <em>today_executed_amount_0</em>カラムの値 
   */
  public  double  today_executed_amount_0;

  /** 
   * <em>today_executed_amount_1</em>カラムの値 
   */
  public  double  today_executed_amount_1;

  /** 
   * <em>today_executed_amount_2</em>カラムの値 
   */
  public  double  today_executed_amount_2;

  /** 
   * <em>today_executed_amount_3</em>カラムの値 
   */
  public  double  today_executed_amount_3;

  /** 
   * <em>today_executed_amount_4</em>カラムの値 
   */
  public  double  today_executed_amount_4;

  /** 
   * <em>today_executed_amount_5</em>カラムの値 
   */
  public  double  today_executed_amount_5;

  /** 
   * <em>today_unexecuted_amount_0</em>カラムの値 
   */
  public  double  today_unexecuted_amount_0;

  /** 
   * <em>today_unexecuted_amount_1</em>カラムの値 
   */
  public  double  today_unexecuted_amount_1;

  /** 
   * <em>today_unexecuted_amount_2</em>カラムの値 
   */
  public  double  today_unexecuted_amount_2;

  /** 
   * <em>today_unexecuted_amount_3</em>カラムの値 
   */
  public  double  today_unexecuted_amount_3;

  /** 
   * <em>today_unexecuted_amount_4</em>カラムの値 
   */
  public  double  today_unexecuted_amount_4;

  /** 
   * <em>today_unexecuted_amount_5</em>カラムの値 
   */
  public  double  today_unexecuted_amount_5;

  /** 
   * <em>day_trade_restraint_0</em>カラムの値 
   */
  public  double  day_trade_restraint_0;

  /** 
   * <em>day_trade_restraint_1</em>カラムの値 
   */
  public  double  day_trade_restraint_1;

  /** 
   * <em>day_trade_restraint_2</em>カラムの値 
   */
  public  double  day_trade_restraint_2;

  /** 
   * <em>day_trade_restraint_3</em>カラムの値 
   */
  public  double  day_trade_restraint_3;

  /** 
   * <em>day_trade_restraint_4</em>カラムの値 
   */
  public  double  day_trade_restraint_4;

  /** 
   * <em>other_restraint_0</em>カラムの値 
   */
  public  double  other_restraint_0;

  /** 
   * <em>other_restraint_1</em>カラムの値 
   */
  public  double  other_restraint_1;

  /** 
   * <em>other_restraint_2</em>カラムの値 
   */
  public  double  other_restraint_2;

  /** 
   * <em>other_restraint_3</em>カラムの値 
   */
  public  double  other_restraint_3;

  /** 
   * <em>other_restraint_4</em>カラムの値 
   */
  public  double  other_restraint_4;

  /** 
   * <em>other_restraint_5</em>カラムの値 
   */
  public  double  other_restraint_5;

  /** 
   * <em>substitute_security_asset_0</em>カラムの値 
   */
  public  double  substitute_security_asset_0;

  /** 
   * <em>substitute_security_asset_1</em>カラムの値 
   */
  public  double  substitute_security_asset_1;

  /** 
   * <em>substitute_security_asset_2</em>カラムの値 
   */
  public  double  substitute_security_asset_2;

  /** 
   * <em>substitute_security_asset_3</em>カラムの値 
   */
  public  double  substitute_security_asset_3;

  /** 
   * <em>substitute_security_asset_4</em>カラムの値 
   */
  public  double  substitute_security_asset_4;

  /** 
   * <em>substitute_security_asset_5</em>カラムの値 
   */
  public  double  substitute_security_asset_5;

  /** 
   * <em>contract_amount_0</em>カラムの値 
   */
  public  double  contract_amount_0;

  /** 
   * <em>contract_amount_1</em>カラムの値 
   */
  public  double  contract_amount_1;

  /** 
   * <em>contract_amount_2</em>カラムの値 
   */
  public  double  contract_amount_2;

  /** 
   * <em>contract_amount_3</em>カラムの値 
   */
  public  double  contract_amount_3;

  /** 
   * <em>contract_amount_4</em>カラムの値 
   */
  public  double  contract_amount_4;

  /** 
   * <em>contract_amount_5</em>カラムの値 
   */
  public  double  contract_amount_5;

  /** 
   * <em>day_repay_contract_amount_0</em>カラムの値 
   */
  public  double  day_repay_contract_amount_0;

  /** 
   * <em>day_repay_contract_amount_1</em>カラムの値 
   */
  public  double  day_repay_contract_amount_1;

  /** 
   * <em>day_repay_contract_amount_2</em>カラムの値 
   */
  public  double  day_repay_contract_amount_2;

  /** 
   * <em>day_repay_contract_amount_3</em>カラムの値 
   */
  public  double  day_repay_contract_amount_3;

  /** 
   * <em>day_repay_contract_amount_4</em>カラムの値 
   */
  public  double  day_repay_contract_amount_4;

  /** 
   * <em>day_repay_contract_amount_5</em>カラムの値 
   */
  public  double  day_repay_contract_amount_5;

  /** 
   * <em>margin_deposit_0</em>カラムの値 
   */
  public  double  margin_deposit_0;

  /** 
   * <em>margin_deposit_1</em>カラムの値 
   */
  public  double  margin_deposit_1;

  /** 
   * <em>margin_deposit_2</em>カラムの値 
   */
  public  double  margin_deposit_2;

  /** 
   * <em>margin_deposit_3</em>カラムの値 
   */
  public  double  margin_deposit_3;

  /** 
   * <em>margin_deposit_4</em>カラムの値 
   */
  public  double  margin_deposit_4;

  /** 
   * <em>margin_deposit_5</em>カラムの値 
   */
  public  double  margin_deposit_5;

  /** 
   * <em>cash_margin_deposit_0</em>カラムの値 
   */
  public  double  cash_margin_deposit_0;

  /** 
   * <em>cash_margin_deposit_1</em>カラムの値 
   */
  public  double  cash_margin_deposit_1;

  /** 
   * <em>cash_margin_deposit_2</em>カラムの値 
   */
  public  double  cash_margin_deposit_2;

  /** 
   * <em>cash_margin_deposit_3</em>カラムの値 
   */
  public  double  cash_margin_deposit_3;

  /** 
   * <em>cash_margin_deposit_4</em>カラムの値 
   */
  public  double  cash_margin_deposit_4;

  /** 
   * <em>cash_margin_deposit_5</em>カラムの値 
   */
  public  double  cash_margin_deposit_5;

  /** 
   * <em>contract_asset_profit_loss</em>カラムの値 
   */
  public  double  contract_asset_profit_loss;

  /** 
   * <em>undeli_contract_amount_0</em>カラムの値 
   */
  public  double  undeli_contract_amount_0;

  /** 
   * <em>undeli_contract_amount_1</em>カラムの値 
   */
  public  double  undeli_contract_amount_1;

  /** 
   * <em>undeli_contract_amount_2</em>カラムの値 
   */
  public  double  undeli_contract_amount_2;

  /** 
   * <em>undeli_contract_amount_3</em>カラムの値 
   */
  public  double  undeli_contract_amount_3;

  /** 
   * <em>undeli_margin_deposit_0</em>カラムの値 
   */
  public  double  undeli_margin_deposit_0;

  /** 
   * <em>undeli_margin_deposit_1</em>カラムの値 
   */
  public  double  undeli_margin_deposit_1;

  /** 
   * <em>undeli_margin_deposit_2</em>カラムの値 
   */
  public  double  undeli_margin_deposit_2;

  /** 
   * <em>undeli_margin_deposit_3</em>カラムの値 
   */
  public  double  undeli_margin_deposit_3;

  /** 
   * <em>undeli_cash_margin_deposit_0</em>カラムの値 
   */
  public  double  undeli_cash_margin_deposit_0;

  /** 
   * <em>undeli_cash_margin_deposit_1</em>カラムの値 
   */
  public  double  undeli_cash_margin_deposit_1;

  /** 
   * <em>undeli_cash_margin_deposit_2</em>カラムの値 
   */
  public  double  undeli_cash_margin_deposit_2;

  /** 
   * <em>undeli_cash_margin_deposit_3</em>カラムの値 
   */
  public  double  undeli_cash_margin_deposit_3;

  /** 
   * <em>undeli_contract_loss_0</em>カラムの値 
   */
  public  double  undeli_contract_loss_0;

  /** 
   * <em>undeli_contract_loss_1</em>カラムの値 
   */
  public  double  undeli_contract_loss_1;

  /** 
   * <em>undeli_contract_loss_2</em>カラムの値 
   */
  public  double  undeli_contract_loss_2;

  /** 
   * <em>undeli_contract_loss_3</em>カラムの値 
   */
  public  double  undeli_contract_loss_3;

  /** 
   * <em>undeli_contract_profit_0</em>カラムの値 
   */
  public  double  undeli_contract_profit_0;

  /** 
   * <em>undeli_contract_profit_1</em>カラムの値 
   */
  public  double  undeli_contract_profit_1;

  /** 
   * <em>undeli_contract_profit_2</em>カラムの値 
   */
  public  double  undeli_contract_profit_2;

  /** 
   * <em>undeli_contract_profit_3</em>カラムの値 
   */
  public  double  undeli_contract_profit_3;

  /** 
   * <em>contract_total_cost</em>カラムの値 
   */
  public  double  contract_total_cost;

  /** 
   * <em>mark_to_market_div</em>カラムの値 
   */
  public  String  mark_to_market_div;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>contract_asset_profit_loss_1</em>カラムの値 
   */
  public  Double  contract_asset_profit_loss_1;

  /** 
   * <em>contract_asset_profit_loss_2</em>カラムの値 
   */
  public  Double  contract_asset_profit_loss_2;

  /** 
   * <em>contract_asset_profit_loss_3</em>カラムの値 
   */
  public  Double  contract_asset_profit_loss_3;

  /** 
   * <em>contract_asset_profit_loss_4</em>カラムの値 
   */
  public  Double  contract_asset_profit_loss_4;

  /** 
   * <em>contract_asset_profit_loss_5</em>カラムの値 
   */
  public  Double  contract_asset_profit_loss_5;

  /** 
   * <em>day_trade_restraint_5</em>カラムの値 
   */
  public  Double  day_trade_restraint_5;

  /** 
   * <em>contract_total_cost_1</em>カラムの値 
   */
  public  Double  contract_total_cost_1;

  /** 
   * <em>contract_total_cost_2</em>カラムの値 
   */
  public  Double  contract_total_cost_2;

  /** 
   * <em>contract_total_cost_3</em>カラムの値 
   */
  public  Double  contract_total_cost_3;

  /** 
   * <em>contract_total_cost_4</em>カラムの値 
   */
  public  Double  contract_total_cost_4;

  /** 
   * <em>contract_total_cost_5</em>カラムの値 
   */
  public  Double  contract_total_cost_5;

  private boolean calc_result_margin_id_is_set = false;
  private boolean calc_result_margin_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
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
  private boolean account_balance_5_is_set = false;
  private boolean account_balance_5_is_modified = false;
  private boolean today_executed_amount_0_is_set = false;
  private boolean today_executed_amount_0_is_modified = false;
  private boolean today_executed_amount_1_is_set = false;
  private boolean today_executed_amount_1_is_modified = false;
  private boolean today_executed_amount_2_is_set = false;
  private boolean today_executed_amount_2_is_modified = false;
  private boolean today_executed_amount_3_is_set = false;
  private boolean today_executed_amount_3_is_modified = false;
  private boolean today_executed_amount_4_is_set = false;
  private boolean today_executed_amount_4_is_modified = false;
  private boolean today_executed_amount_5_is_set = false;
  private boolean today_executed_amount_5_is_modified = false;
  private boolean today_unexecuted_amount_0_is_set = false;
  private boolean today_unexecuted_amount_0_is_modified = false;
  private boolean today_unexecuted_amount_1_is_set = false;
  private boolean today_unexecuted_amount_1_is_modified = false;
  private boolean today_unexecuted_amount_2_is_set = false;
  private boolean today_unexecuted_amount_2_is_modified = false;
  private boolean today_unexecuted_amount_3_is_set = false;
  private boolean today_unexecuted_amount_3_is_modified = false;
  private boolean today_unexecuted_amount_4_is_set = false;
  private boolean today_unexecuted_amount_4_is_modified = false;
  private boolean today_unexecuted_amount_5_is_set = false;
  private boolean today_unexecuted_amount_5_is_modified = false;
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
  private boolean other_restraint_5_is_set = false;
  private boolean other_restraint_5_is_modified = false;
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
  private boolean substitute_security_asset_5_is_set = false;
  private boolean substitute_security_asset_5_is_modified = false;
  private boolean contract_amount_0_is_set = false;
  private boolean contract_amount_0_is_modified = false;
  private boolean contract_amount_1_is_set = false;
  private boolean contract_amount_1_is_modified = false;
  private boolean contract_amount_2_is_set = false;
  private boolean contract_amount_2_is_modified = false;
  private boolean contract_amount_3_is_set = false;
  private boolean contract_amount_3_is_modified = false;
  private boolean contract_amount_4_is_set = false;
  private boolean contract_amount_4_is_modified = false;
  private boolean contract_amount_5_is_set = false;
  private boolean contract_amount_5_is_modified = false;
  private boolean day_repay_contract_amount_0_is_set = false;
  private boolean day_repay_contract_amount_0_is_modified = false;
  private boolean day_repay_contract_amount_1_is_set = false;
  private boolean day_repay_contract_amount_1_is_modified = false;
  private boolean day_repay_contract_amount_2_is_set = false;
  private boolean day_repay_contract_amount_2_is_modified = false;
  private boolean day_repay_contract_amount_3_is_set = false;
  private boolean day_repay_contract_amount_3_is_modified = false;
  private boolean day_repay_contract_amount_4_is_set = false;
  private boolean day_repay_contract_amount_4_is_modified = false;
  private boolean day_repay_contract_amount_5_is_set = false;
  private boolean day_repay_contract_amount_5_is_modified = false;
  private boolean margin_deposit_0_is_set = false;
  private boolean margin_deposit_0_is_modified = false;
  private boolean margin_deposit_1_is_set = false;
  private boolean margin_deposit_1_is_modified = false;
  private boolean margin_deposit_2_is_set = false;
  private boolean margin_deposit_2_is_modified = false;
  private boolean margin_deposit_3_is_set = false;
  private boolean margin_deposit_3_is_modified = false;
  private boolean margin_deposit_4_is_set = false;
  private boolean margin_deposit_4_is_modified = false;
  private boolean margin_deposit_5_is_set = false;
  private boolean margin_deposit_5_is_modified = false;
  private boolean cash_margin_deposit_0_is_set = false;
  private boolean cash_margin_deposit_0_is_modified = false;
  private boolean cash_margin_deposit_1_is_set = false;
  private boolean cash_margin_deposit_1_is_modified = false;
  private boolean cash_margin_deposit_2_is_set = false;
  private boolean cash_margin_deposit_2_is_modified = false;
  private boolean cash_margin_deposit_3_is_set = false;
  private boolean cash_margin_deposit_3_is_modified = false;
  private boolean cash_margin_deposit_4_is_set = false;
  private boolean cash_margin_deposit_4_is_modified = false;
  private boolean cash_margin_deposit_5_is_set = false;
  private boolean cash_margin_deposit_5_is_modified = false;
  private boolean contract_asset_profit_loss_is_set = false;
  private boolean contract_asset_profit_loss_is_modified = false;
  private boolean undeli_contract_amount_0_is_set = false;
  private boolean undeli_contract_amount_0_is_modified = false;
  private boolean undeli_contract_amount_1_is_set = false;
  private boolean undeli_contract_amount_1_is_modified = false;
  private boolean undeli_contract_amount_2_is_set = false;
  private boolean undeli_contract_amount_2_is_modified = false;
  private boolean undeli_contract_amount_3_is_set = false;
  private boolean undeli_contract_amount_3_is_modified = false;
  private boolean undeli_margin_deposit_0_is_set = false;
  private boolean undeli_margin_deposit_0_is_modified = false;
  private boolean undeli_margin_deposit_1_is_set = false;
  private boolean undeli_margin_deposit_1_is_modified = false;
  private boolean undeli_margin_deposit_2_is_set = false;
  private boolean undeli_margin_deposit_2_is_modified = false;
  private boolean undeli_margin_deposit_3_is_set = false;
  private boolean undeli_margin_deposit_3_is_modified = false;
  private boolean undeli_cash_margin_deposit_0_is_set = false;
  private boolean undeli_cash_margin_deposit_0_is_modified = false;
  private boolean undeli_cash_margin_deposit_1_is_set = false;
  private boolean undeli_cash_margin_deposit_1_is_modified = false;
  private boolean undeli_cash_margin_deposit_2_is_set = false;
  private boolean undeli_cash_margin_deposit_2_is_modified = false;
  private boolean undeli_cash_margin_deposit_3_is_set = false;
  private boolean undeli_cash_margin_deposit_3_is_modified = false;
  private boolean undeli_contract_loss_0_is_set = false;
  private boolean undeli_contract_loss_0_is_modified = false;
  private boolean undeli_contract_loss_1_is_set = false;
  private boolean undeli_contract_loss_1_is_modified = false;
  private boolean undeli_contract_loss_2_is_set = false;
  private boolean undeli_contract_loss_2_is_modified = false;
  private boolean undeli_contract_loss_3_is_set = false;
  private boolean undeli_contract_loss_3_is_modified = false;
  private boolean undeli_contract_profit_0_is_set = false;
  private boolean undeli_contract_profit_0_is_modified = false;
  private boolean undeli_contract_profit_1_is_set = false;
  private boolean undeli_contract_profit_1_is_modified = false;
  private boolean undeli_contract_profit_2_is_set = false;
  private boolean undeli_contract_profit_2_is_modified = false;
  private boolean undeli_contract_profit_3_is_set = false;
  private boolean undeli_contract_profit_3_is_modified = false;
  private boolean contract_total_cost_is_set = false;
  private boolean contract_total_cost_is_modified = false;
  private boolean mark_to_market_div_is_set = false;
  private boolean mark_to_market_div_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean contract_asset_profit_loss_1_is_set = false;
  private boolean contract_asset_profit_loss_1_is_modified = false;
  private boolean contract_asset_profit_loss_2_is_set = false;
  private boolean contract_asset_profit_loss_2_is_modified = false;
  private boolean contract_asset_profit_loss_3_is_set = false;
  private boolean contract_asset_profit_loss_3_is_modified = false;
  private boolean contract_asset_profit_loss_4_is_set = false;
  private boolean contract_asset_profit_loss_4_is_modified = false;
  private boolean contract_asset_profit_loss_5_is_set = false;
  private boolean contract_asset_profit_loss_5_is_modified = false;
  private boolean day_trade_restraint_5_is_set = false;
  private boolean day_trade_restraint_5_is_modified = false;
  private boolean contract_total_cost_1_is_set = false;
  private boolean contract_total_cost_1_is_modified = false;
  private boolean contract_total_cost_2_is_set = false;
  private boolean contract_total_cost_2_is_modified = false;
  private boolean contract_total_cost_3_is_set = false;
  private boolean contract_total_cost_3_is_modified = false;
  private boolean contract_total_cost_4_is_set = false;
  private boolean contract_total_cost_4_is_modified = false;
  private boolean contract_total_cost_5_is_set = false;
  private boolean contract_total_cost_5_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "calc_result_margin_id=" + calc_result_margin_id
      + "," + "account_id=" +account_id
      + "," + "account_balance_0=" +account_balance_0
      + "," + "account_balance_1=" +account_balance_1
      + "," + "account_balance_2=" +account_balance_2
      + "," + "account_balance_3=" +account_balance_3
      + "," + "account_balance_4=" +account_balance_4
      + "," + "account_balance_5=" +account_balance_5
      + "," + "today_executed_amount_0=" +today_executed_amount_0
      + "," + "today_executed_amount_1=" +today_executed_amount_1
      + "," + "today_executed_amount_2=" +today_executed_amount_2
      + "," + "today_executed_amount_3=" +today_executed_amount_3
      + "," + "today_executed_amount_4=" +today_executed_amount_4
      + "," + "today_executed_amount_5=" +today_executed_amount_5
      + "," + "today_unexecuted_amount_0=" +today_unexecuted_amount_0
      + "," + "today_unexecuted_amount_1=" +today_unexecuted_amount_1
      + "," + "today_unexecuted_amount_2=" +today_unexecuted_amount_2
      + "," + "today_unexecuted_amount_3=" +today_unexecuted_amount_3
      + "," + "today_unexecuted_amount_4=" +today_unexecuted_amount_4
      + "," + "today_unexecuted_amount_5=" +today_unexecuted_amount_5
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
      + "," + "other_restraint_5=" +other_restraint_5
      + "," + "substitute_security_asset_0=" +substitute_security_asset_0
      + "," + "substitute_security_asset_1=" +substitute_security_asset_1
      + "," + "substitute_security_asset_2=" +substitute_security_asset_2
      + "," + "substitute_security_asset_3=" +substitute_security_asset_3
      + "," + "substitute_security_asset_4=" +substitute_security_asset_4
      + "," + "substitute_security_asset_5=" +substitute_security_asset_5
      + "," + "contract_amount_0=" +contract_amount_0
      + "," + "contract_amount_1=" +contract_amount_1
      + "," + "contract_amount_2=" +contract_amount_2
      + "," + "contract_amount_3=" +contract_amount_3
      + "," + "contract_amount_4=" +contract_amount_4
      + "," + "contract_amount_5=" +contract_amount_5
      + "," + "day_repay_contract_amount_0=" +day_repay_contract_amount_0
      + "," + "day_repay_contract_amount_1=" +day_repay_contract_amount_1
      + "," + "day_repay_contract_amount_2=" +day_repay_contract_amount_2
      + "," + "day_repay_contract_amount_3=" +day_repay_contract_amount_3
      + "," + "day_repay_contract_amount_4=" +day_repay_contract_amount_4
      + "," + "day_repay_contract_amount_5=" +day_repay_contract_amount_5
      + "," + "margin_deposit_0=" +margin_deposit_0
      + "," + "margin_deposit_1=" +margin_deposit_1
      + "," + "margin_deposit_2=" +margin_deposit_2
      + "," + "margin_deposit_3=" +margin_deposit_3
      + "," + "margin_deposit_4=" +margin_deposit_4
      + "," + "margin_deposit_5=" +margin_deposit_5
      + "," + "cash_margin_deposit_0=" +cash_margin_deposit_0
      + "," + "cash_margin_deposit_1=" +cash_margin_deposit_1
      + "," + "cash_margin_deposit_2=" +cash_margin_deposit_2
      + "," + "cash_margin_deposit_3=" +cash_margin_deposit_3
      + "," + "cash_margin_deposit_4=" +cash_margin_deposit_4
      + "," + "cash_margin_deposit_5=" +cash_margin_deposit_5
      + "," + "contract_asset_profit_loss=" +contract_asset_profit_loss
      + "," + "undeli_contract_amount_0=" +undeli_contract_amount_0
      + "," + "undeli_contract_amount_1=" +undeli_contract_amount_1
      + "," + "undeli_contract_amount_2=" +undeli_contract_amount_2
      + "," + "undeli_contract_amount_3=" +undeli_contract_amount_3
      + "," + "undeli_margin_deposit_0=" +undeli_margin_deposit_0
      + "," + "undeli_margin_deposit_1=" +undeli_margin_deposit_1
      + "," + "undeli_margin_deposit_2=" +undeli_margin_deposit_2
      + "," + "undeli_margin_deposit_3=" +undeli_margin_deposit_3
      + "," + "undeli_cash_margin_deposit_0=" +undeli_cash_margin_deposit_0
      + "," + "undeli_cash_margin_deposit_1=" +undeli_cash_margin_deposit_1
      + "," + "undeli_cash_margin_deposit_2=" +undeli_cash_margin_deposit_2
      + "," + "undeli_cash_margin_deposit_3=" +undeli_cash_margin_deposit_3
      + "," + "undeli_contract_loss_0=" +undeli_contract_loss_0
      + "," + "undeli_contract_loss_1=" +undeli_contract_loss_1
      + "," + "undeli_contract_loss_2=" +undeli_contract_loss_2
      + "," + "undeli_contract_loss_3=" +undeli_contract_loss_3
      + "," + "undeli_contract_profit_0=" +undeli_contract_profit_0
      + "," + "undeli_contract_profit_1=" +undeli_contract_profit_1
      + "," + "undeli_contract_profit_2=" +undeli_contract_profit_2
      + "," + "undeli_contract_profit_3=" +undeli_contract_profit_3
      + "," + "contract_total_cost=" +contract_total_cost
      + "," + "mark_to_market_div=" +mark_to_market_div
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "contract_asset_profit_loss_1=" +contract_asset_profit_loss_1
      + "," + "contract_asset_profit_loss_2=" +contract_asset_profit_loss_2
      + "," + "contract_asset_profit_loss_3=" +contract_asset_profit_loss_3
      + "," + "contract_asset_profit_loss_4=" +contract_asset_profit_loss_4
      + "," + "contract_asset_profit_loss_5=" +contract_asset_profit_loss_5
      + "," + "day_trade_restraint_5=" +day_trade_restraint_5
      + "," + "contract_total_cost_1=" +contract_total_cost_1
      + "," + "contract_total_cost_2=" +contract_total_cost_2
      + "," + "contract_total_cost_3=" +contract_total_cost_3
      + "," + "contract_total_cost_4=" +contract_total_cost_4
      + "," + "contract_total_cost_5=" +contract_total_cost_5
      + "}";
  }


  /** 
   * 値が未設定のTpCalcResultMarginParamsオブジェクトを作成します。 
   */
  public TpCalcResultMarginParams() {
    calc_result_margin_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のTpCalcResultMarginRowオブジェクトの値を利用してTpCalcResultMarginParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するTpCalcResultMarginRowオブジェクト 
   */
  public TpCalcResultMarginParams( TpCalcResultMarginRow p_row )
  {
    calc_result_margin_id = p_row.getCalcResultMarginId();
    calc_result_margin_id_is_set = p_row.getCalcResultMarginIdIsSet();
    calc_result_margin_id_is_modified = p_row.getCalcResultMarginIdIsModified();
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    account_balance_0 = p_row.getAccountBalance0();
    account_balance_0_is_set = p_row.getAccountBalance0IsSet();
    account_balance_0_is_modified = p_row.getAccountBalance0IsModified();
    account_balance_1 = p_row.getAccountBalance1();
    account_balance_1_is_set = p_row.getAccountBalance1IsSet();
    account_balance_1_is_modified = p_row.getAccountBalance1IsModified();
    account_balance_2 = p_row.getAccountBalance2();
    account_balance_2_is_set = p_row.getAccountBalance2IsSet();
    account_balance_2_is_modified = p_row.getAccountBalance2IsModified();
    account_balance_3 = p_row.getAccountBalance3();
    account_balance_3_is_set = p_row.getAccountBalance3IsSet();
    account_balance_3_is_modified = p_row.getAccountBalance3IsModified();
    account_balance_4 = p_row.getAccountBalance4();
    account_balance_4_is_set = p_row.getAccountBalance4IsSet();
    account_balance_4_is_modified = p_row.getAccountBalance4IsModified();
    account_balance_5 = p_row.getAccountBalance5();
    account_balance_5_is_set = p_row.getAccountBalance5IsSet();
    account_balance_5_is_modified = p_row.getAccountBalance5IsModified();
    today_executed_amount_0 = p_row.getTodayExecutedAmount0();
    today_executed_amount_0_is_set = p_row.getTodayExecutedAmount0IsSet();
    today_executed_amount_0_is_modified = p_row.getTodayExecutedAmount0IsModified();
    today_executed_amount_1 = p_row.getTodayExecutedAmount1();
    today_executed_amount_1_is_set = p_row.getTodayExecutedAmount1IsSet();
    today_executed_amount_1_is_modified = p_row.getTodayExecutedAmount1IsModified();
    today_executed_amount_2 = p_row.getTodayExecutedAmount2();
    today_executed_amount_2_is_set = p_row.getTodayExecutedAmount2IsSet();
    today_executed_amount_2_is_modified = p_row.getTodayExecutedAmount2IsModified();
    today_executed_amount_3 = p_row.getTodayExecutedAmount3();
    today_executed_amount_3_is_set = p_row.getTodayExecutedAmount3IsSet();
    today_executed_amount_3_is_modified = p_row.getTodayExecutedAmount3IsModified();
    today_executed_amount_4 = p_row.getTodayExecutedAmount4();
    today_executed_amount_4_is_set = p_row.getTodayExecutedAmount4IsSet();
    today_executed_amount_4_is_modified = p_row.getTodayExecutedAmount4IsModified();
    today_executed_amount_5 = p_row.getTodayExecutedAmount5();
    today_executed_amount_5_is_set = p_row.getTodayExecutedAmount5IsSet();
    today_executed_amount_5_is_modified = p_row.getTodayExecutedAmount5IsModified();
    today_unexecuted_amount_0 = p_row.getTodayUnexecutedAmount0();
    today_unexecuted_amount_0_is_set = p_row.getTodayUnexecutedAmount0IsSet();
    today_unexecuted_amount_0_is_modified = p_row.getTodayUnexecutedAmount0IsModified();
    today_unexecuted_amount_1 = p_row.getTodayUnexecutedAmount1();
    today_unexecuted_amount_1_is_set = p_row.getTodayUnexecutedAmount1IsSet();
    today_unexecuted_amount_1_is_modified = p_row.getTodayUnexecutedAmount1IsModified();
    today_unexecuted_amount_2 = p_row.getTodayUnexecutedAmount2();
    today_unexecuted_amount_2_is_set = p_row.getTodayUnexecutedAmount2IsSet();
    today_unexecuted_amount_2_is_modified = p_row.getTodayUnexecutedAmount2IsModified();
    today_unexecuted_amount_3 = p_row.getTodayUnexecutedAmount3();
    today_unexecuted_amount_3_is_set = p_row.getTodayUnexecutedAmount3IsSet();
    today_unexecuted_amount_3_is_modified = p_row.getTodayUnexecutedAmount3IsModified();
    today_unexecuted_amount_4 = p_row.getTodayUnexecutedAmount4();
    today_unexecuted_amount_4_is_set = p_row.getTodayUnexecutedAmount4IsSet();
    today_unexecuted_amount_4_is_modified = p_row.getTodayUnexecutedAmount4IsModified();
    today_unexecuted_amount_5 = p_row.getTodayUnexecutedAmount5();
    today_unexecuted_amount_5_is_set = p_row.getTodayUnexecutedAmount5IsSet();
    today_unexecuted_amount_5_is_modified = p_row.getTodayUnexecutedAmount5IsModified();
    day_trade_restraint_0 = p_row.getDayTradeRestraint0();
    day_trade_restraint_0_is_set = p_row.getDayTradeRestraint0IsSet();
    day_trade_restraint_0_is_modified = p_row.getDayTradeRestraint0IsModified();
    day_trade_restraint_1 = p_row.getDayTradeRestraint1();
    day_trade_restraint_1_is_set = p_row.getDayTradeRestraint1IsSet();
    day_trade_restraint_1_is_modified = p_row.getDayTradeRestraint1IsModified();
    day_trade_restraint_2 = p_row.getDayTradeRestraint2();
    day_trade_restraint_2_is_set = p_row.getDayTradeRestraint2IsSet();
    day_trade_restraint_2_is_modified = p_row.getDayTradeRestraint2IsModified();
    day_trade_restraint_3 = p_row.getDayTradeRestraint3();
    day_trade_restraint_3_is_set = p_row.getDayTradeRestraint3IsSet();
    day_trade_restraint_3_is_modified = p_row.getDayTradeRestraint3IsModified();
    day_trade_restraint_4 = p_row.getDayTradeRestraint4();
    day_trade_restraint_4_is_set = p_row.getDayTradeRestraint4IsSet();
    day_trade_restraint_4_is_modified = p_row.getDayTradeRestraint4IsModified();
    other_restraint_0 = p_row.getOtherRestraint0();
    other_restraint_0_is_set = p_row.getOtherRestraint0IsSet();
    other_restraint_0_is_modified = p_row.getOtherRestraint0IsModified();
    other_restraint_1 = p_row.getOtherRestraint1();
    other_restraint_1_is_set = p_row.getOtherRestraint1IsSet();
    other_restraint_1_is_modified = p_row.getOtherRestraint1IsModified();
    other_restraint_2 = p_row.getOtherRestraint2();
    other_restraint_2_is_set = p_row.getOtherRestraint2IsSet();
    other_restraint_2_is_modified = p_row.getOtherRestraint2IsModified();
    other_restraint_3 = p_row.getOtherRestraint3();
    other_restraint_3_is_set = p_row.getOtherRestraint3IsSet();
    other_restraint_3_is_modified = p_row.getOtherRestraint3IsModified();
    other_restraint_4 = p_row.getOtherRestraint4();
    other_restraint_4_is_set = p_row.getOtherRestraint4IsSet();
    other_restraint_4_is_modified = p_row.getOtherRestraint4IsModified();
    other_restraint_5 = p_row.getOtherRestraint5();
    other_restraint_5_is_set = p_row.getOtherRestraint5IsSet();
    other_restraint_5_is_modified = p_row.getOtherRestraint5IsModified();
    substitute_security_asset_0 = p_row.getSubstituteSecurityAsset0();
    substitute_security_asset_0_is_set = p_row.getSubstituteSecurityAsset0IsSet();
    substitute_security_asset_0_is_modified = p_row.getSubstituteSecurityAsset0IsModified();
    substitute_security_asset_1 = p_row.getSubstituteSecurityAsset1();
    substitute_security_asset_1_is_set = p_row.getSubstituteSecurityAsset1IsSet();
    substitute_security_asset_1_is_modified = p_row.getSubstituteSecurityAsset1IsModified();
    substitute_security_asset_2 = p_row.getSubstituteSecurityAsset2();
    substitute_security_asset_2_is_set = p_row.getSubstituteSecurityAsset2IsSet();
    substitute_security_asset_2_is_modified = p_row.getSubstituteSecurityAsset2IsModified();
    substitute_security_asset_3 = p_row.getSubstituteSecurityAsset3();
    substitute_security_asset_3_is_set = p_row.getSubstituteSecurityAsset3IsSet();
    substitute_security_asset_3_is_modified = p_row.getSubstituteSecurityAsset3IsModified();
    substitute_security_asset_4 = p_row.getSubstituteSecurityAsset4();
    substitute_security_asset_4_is_set = p_row.getSubstituteSecurityAsset4IsSet();
    substitute_security_asset_4_is_modified = p_row.getSubstituteSecurityAsset4IsModified();
    substitute_security_asset_5 = p_row.getSubstituteSecurityAsset5();
    substitute_security_asset_5_is_set = p_row.getSubstituteSecurityAsset5IsSet();
    substitute_security_asset_5_is_modified = p_row.getSubstituteSecurityAsset5IsModified();
    contract_amount_0 = p_row.getContractAmount0();
    contract_amount_0_is_set = p_row.getContractAmount0IsSet();
    contract_amount_0_is_modified = p_row.getContractAmount0IsModified();
    contract_amount_1 = p_row.getContractAmount1();
    contract_amount_1_is_set = p_row.getContractAmount1IsSet();
    contract_amount_1_is_modified = p_row.getContractAmount1IsModified();
    contract_amount_2 = p_row.getContractAmount2();
    contract_amount_2_is_set = p_row.getContractAmount2IsSet();
    contract_amount_2_is_modified = p_row.getContractAmount2IsModified();
    contract_amount_3 = p_row.getContractAmount3();
    contract_amount_3_is_set = p_row.getContractAmount3IsSet();
    contract_amount_3_is_modified = p_row.getContractAmount3IsModified();
    contract_amount_4 = p_row.getContractAmount4();
    contract_amount_4_is_set = p_row.getContractAmount4IsSet();
    contract_amount_4_is_modified = p_row.getContractAmount4IsModified();
    contract_amount_5 = p_row.getContractAmount5();
    contract_amount_5_is_set = p_row.getContractAmount5IsSet();
    contract_amount_5_is_modified = p_row.getContractAmount5IsModified();
    day_repay_contract_amount_0 = p_row.getDayRepayContractAmount0();
    day_repay_contract_amount_0_is_set = p_row.getDayRepayContractAmount0IsSet();
    day_repay_contract_amount_0_is_modified = p_row.getDayRepayContractAmount0IsModified();
    day_repay_contract_amount_1 = p_row.getDayRepayContractAmount1();
    day_repay_contract_amount_1_is_set = p_row.getDayRepayContractAmount1IsSet();
    day_repay_contract_amount_1_is_modified = p_row.getDayRepayContractAmount1IsModified();
    day_repay_contract_amount_2 = p_row.getDayRepayContractAmount2();
    day_repay_contract_amount_2_is_set = p_row.getDayRepayContractAmount2IsSet();
    day_repay_contract_amount_2_is_modified = p_row.getDayRepayContractAmount2IsModified();
    day_repay_contract_amount_3 = p_row.getDayRepayContractAmount3();
    day_repay_contract_amount_3_is_set = p_row.getDayRepayContractAmount3IsSet();
    day_repay_contract_amount_3_is_modified = p_row.getDayRepayContractAmount3IsModified();
    day_repay_contract_amount_4 = p_row.getDayRepayContractAmount4();
    day_repay_contract_amount_4_is_set = p_row.getDayRepayContractAmount4IsSet();
    day_repay_contract_amount_4_is_modified = p_row.getDayRepayContractAmount4IsModified();
    day_repay_contract_amount_5 = p_row.getDayRepayContractAmount5();
    day_repay_contract_amount_5_is_set = p_row.getDayRepayContractAmount5IsSet();
    day_repay_contract_amount_5_is_modified = p_row.getDayRepayContractAmount5IsModified();
    margin_deposit_0 = p_row.getMarginDeposit0();
    margin_deposit_0_is_set = p_row.getMarginDeposit0IsSet();
    margin_deposit_0_is_modified = p_row.getMarginDeposit0IsModified();
    margin_deposit_1 = p_row.getMarginDeposit1();
    margin_deposit_1_is_set = p_row.getMarginDeposit1IsSet();
    margin_deposit_1_is_modified = p_row.getMarginDeposit1IsModified();
    margin_deposit_2 = p_row.getMarginDeposit2();
    margin_deposit_2_is_set = p_row.getMarginDeposit2IsSet();
    margin_deposit_2_is_modified = p_row.getMarginDeposit2IsModified();
    margin_deposit_3 = p_row.getMarginDeposit3();
    margin_deposit_3_is_set = p_row.getMarginDeposit3IsSet();
    margin_deposit_3_is_modified = p_row.getMarginDeposit3IsModified();
    margin_deposit_4 = p_row.getMarginDeposit4();
    margin_deposit_4_is_set = p_row.getMarginDeposit4IsSet();
    margin_deposit_4_is_modified = p_row.getMarginDeposit4IsModified();
    margin_deposit_5 = p_row.getMarginDeposit5();
    margin_deposit_5_is_set = p_row.getMarginDeposit5IsSet();
    margin_deposit_5_is_modified = p_row.getMarginDeposit5IsModified();
    cash_margin_deposit_0 = p_row.getCashMarginDeposit0();
    cash_margin_deposit_0_is_set = p_row.getCashMarginDeposit0IsSet();
    cash_margin_deposit_0_is_modified = p_row.getCashMarginDeposit0IsModified();
    cash_margin_deposit_1 = p_row.getCashMarginDeposit1();
    cash_margin_deposit_1_is_set = p_row.getCashMarginDeposit1IsSet();
    cash_margin_deposit_1_is_modified = p_row.getCashMarginDeposit1IsModified();
    cash_margin_deposit_2 = p_row.getCashMarginDeposit2();
    cash_margin_deposit_2_is_set = p_row.getCashMarginDeposit2IsSet();
    cash_margin_deposit_2_is_modified = p_row.getCashMarginDeposit2IsModified();
    cash_margin_deposit_3 = p_row.getCashMarginDeposit3();
    cash_margin_deposit_3_is_set = p_row.getCashMarginDeposit3IsSet();
    cash_margin_deposit_3_is_modified = p_row.getCashMarginDeposit3IsModified();
    cash_margin_deposit_4 = p_row.getCashMarginDeposit4();
    cash_margin_deposit_4_is_set = p_row.getCashMarginDeposit4IsSet();
    cash_margin_deposit_4_is_modified = p_row.getCashMarginDeposit4IsModified();
    cash_margin_deposit_5 = p_row.getCashMarginDeposit5();
    cash_margin_deposit_5_is_set = p_row.getCashMarginDeposit5IsSet();
    cash_margin_deposit_5_is_modified = p_row.getCashMarginDeposit5IsModified();
    contract_asset_profit_loss = p_row.getContractAssetProfitLoss();
    contract_asset_profit_loss_is_set = p_row.getContractAssetProfitLossIsSet();
    contract_asset_profit_loss_is_modified = p_row.getContractAssetProfitLossIsModified();
    undeli_contract_amount_0 = p_row.getUndeliContractAmount0();
    undeli_contract_amount_0_is_set = p_row.getUndeliContractAmount0IsSet();
    undeli_contract_amount_0_is_modified = p_row.getUndeliContractAmount0IsModified();
    undeli_contract_amount_1 = p_row.getUndeliContractAmount1();
    undeli_contract_amount_1_is_set = p_row.getUndeliContractAmount1IsSet();
    undeli_contract_amount_1_is_modified = p_row.getUndeliContractAmount1IsModified();
    undeli_contract_amount_2 = p_row.getUndeliContractAmount2();
    undeli_contract_amount_2_is_set = p_row.getUndeliContractAmount2IsSet();
    undeli_contract_amount_2_is_modified = p_row.getUndeliContractAmount2IsModified();
    undeli_contract_amount_3 = p_row.getUndeliContractAmount3();
    undeli_contract_amount_3_is_set = p_row.getUndeliContractAmount3IsSet();
    undeli_contract_amount_3_is_modified = p_row.getUndeliContractAmount3IsModified();
    undeli_margin_deposit_0 = p_row.getUndeliMarginDeposit0();
    undeli_margin_deposit_0_is_set = p_row.getUndeliMarginDeposit0IsSet();
    undeli_margin_deposit_0_is_modified = p_row.getUndeliMarginDeposit0IsModified();
    undeli_margin_deposit_1 = p_row.getUndeliMarginDeposit1();
    undeli_margin_deposit_1_is_set = p_row.getUndeliMarginDeposit1IsSet();
    undeli_margin_deposit_1_is_modified = p_row.getUndeliMarginDeposit1IsModified();
    undeli_margin_deposit_2 = p_row.getUndeliMarginDeposit2();
    undeli_margin_deposit_2_is_set = p_row.getUndeliMarginDeposit2IsSet();
    undeli_margin_deposit_2_is_modified = p_row.getUndeliMarginDeposit2IsModified();
    undeli_margin_deposit_3 = p_row.getUndeliMarginDeposit3();
    undeli_margin_deposit_3_is_set = p_row.getUndeliMarginDeposit3IsSet();
    undeli_margin_deposit_3_is_modified = p_row.getUndeliMarginDeposit3IsModified();
    undeli_cash_margin_deposit_0 = p_row.getUndeliCashMarginDeposit0();
    undeli_cash_margin_deposit_0_is_set = p_row.getUndeliCashMarginDeposit0IsSet();
    undeli_cash_margin_deposit_0_is_modified = p_row.getUndeliCashMarginDeposit0IsModified();
    undeli_cash_margin_deposit_1 = p_row.getUndeliCashMarginDeposit1();
    undeli_cash_margin_deposit_1_is_set = p_row.getUndeliCashMarginDeposit1IsSet();
    undeli_cash_margin_deposit_1_is_modified = p_row.getUndeliCashMarginDeposit1IsModified();
    undeli_cash_margin_deposit_2 = p_row.getUndeliCashMarginDeposit2();
    undeli_cash_margin_deposit_2_is_set = p_row.getUndeliCashMarginDeposit2IsSet();
    undeli_cash_margin_deposit_2_is_modified = p_row.getUndeliCashMarginDeposit2IsModified();
    undeli_cash_margin_deposit_3 = p_row.getUndeliCashMarginDeposit3();
    undeli_cash_margin_deposit_3_is_set = p_row.getUndeliCashMarginDeposit3IsSet();
    undeli_cash_margin_deposit_3_is_modified = p_row.getUndeliCashMarginDeposit3IsModified();
    undeli_contract_loss_0 = p_row.getUndeliContractLoss0();
    undeli_contract_loss_0_is_set = p_row.getUndeliContractLoss0IsSet();
    undeli_contract_loss_0_is_modified = p_row.getUndeliContractLoss0IsModified();
    undeli_contract_loss_1 = p_row.getUndeliContractLoss1();
    undeli_contract_loss_1_is_set = p_row.getUndeliContractLoss1IsSet();
    undeli_contract_loss_1_is_modified = p_row.getUndeliContractLoss1IsModified();
    undeli_contract_loss_2 = p_row.getUndeliContractLoss2();
    undeli_contract_loss_2_is_set = p_row.getUndeliContractLoss2IsSet();
    undeli_contract_loss_2_is_modified = p_row.getUndeliContractLoss2IsModified();
    undeli_contract_loss_3 = p_row.getUndeliContractLoss3();
    undeli_contract_loss_3_is_set = p_row.getUndeliContractLoss3IsSet();
    undeli_contract_loss_3_is_modified = p_row.getUndeliContractLoss3IsModified();
    undeli_contract_profit_0 = p_row.getUndeliContractProfit0();
    undeli_contract_profit_0_is_set = p_row.getUndeliContractProfit0IsSet();
    undeli_contract_profit_0_is_modified = p_row.getUndeliContractProfit0IsModified();
    undeli_contract_profit_1 = p_row.getUndeliContractProfit1();
    undeli_contract_profit_1_is_set = p_row.getUndeliContractProfit1IsSet();
    undeli_contract_profit_1_is_modified = p_row.getUndeliContractProfit1IsModified();
    undeli_contract_profit_2 = p_row.getUndeliContractProfit2();
    undeli_contract_profit_2_is_set = p_row.getUndeliContractProfit2IsSet();
    undeli_contract_profit_2_is_modified = p_row.getUndeliContractProfit2IsModified();
    undeli_contract_profit_3 = p_row.getUndeliContractProfit3();
    undeli_contract_profit_3_is_set = p_row.getUndeliContractProfit3IsSet();
    undeli_contract_profit_3_is_modified = p_row.getUndeliContractProfit3IsModified();
    contract_total_cost = p_row.getContractTotalCost();
    contract_total_cost_is_set = p_row.getContractTotalCostIsSet();
    contract_total_cost_is_modified = p_row.getContractTotalCostIsModified();
    mark_to_market_div = p_row.getMarkToMarketDiv();
    mark_to_market_div_is_set = p_row.getMarkToMarketDivIsSet();
    mark_to_market_div_is_modified = p_row.getMarkToMarketDivIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    if ( !p_row.getContractAssetProfitLoss1IsNull() )
      contract_asset_profit_loss_1 = new Double( p_row.getContractAssetProfitLoss1() );
    contract_asset_profit_loss_1_is_set = p_row.getContractAssetProfitLoss1IsSet();
    contract_asset_profit_loss_1_is_modified = p_row.getContractAssetProfitLoss1IsModified();
    if ( !p_row.getContractAssetProfitLoss2IsNull() )
      contract_asset_profit_loss_2 = new Double( p_row.getContractAssetProfitLoss2() );
    contract_asset_profit_loss_2_is_set = p_row.getContractAssetProfitLoss2IsSet();
    contract_asset_profit_loss_2_is_modified = p_row.getContractAssetProfitLoss2IsModified();
    if ( !p_row.getContractAssetProfitLoss3IsNull() )
      contract_asset_profit_loss_3 = new Double( p_row.getContractAssetProfitLoss3() );
    contract_asset_profit_loss_3_is_set = p_row.getContractAssetProfitLoss3IsSet();
    contract_asset_profit_loss_3_is_modified = p_row.getContractAssetProfitLoss3IsModified();
    if ( !p_row.getContractAssetProfitLoss4IsNull() )
      contract_asset_profit_loss_4 = new Double( p_row.getContractAssetProfitLoss4() );
    contract_asset_profit_loss_4_is_set = p_row.getContractAssetProfitLoss4IsSet();
    contract_asset_profit_loss_4_is_modified = p_row.getContractAssetProfitLoss4IsModified();
    if ( !p_row.getContractAssetProfitLoss5IsNull() )
      contract_asset_profit_loss_5 = new Double( p_row.getContractAssetProfitLoss5() );
    contract_asset_profit_loss_5_is_set = p_row.getContractAssetProfitLoss5IsSet();
    contract_asset_profit_loss_5_is_modified = p_row.getContractAssetProfitLoss5IsModified();
    if ( !p_row.getDayTradeRestraint5IsNull() )
      day_trade_restraint_5 = new Double( p_row.getDayTradeRestraint5() );
    day_trade_restraint_5_is_set = p_row.getDayTradeRestraint5IsSet();
    day_trade_restraint_5_is_modified = p_row.getDayTradeRestraint5IsModified();
    if ( !p_row.getContractTotalCost1IsNull() )
      contract_total_cost_1 = new Double( p_row.getContractTotalCost1() );
    contract_total_cost_1_is_set = p_row.getContractTotalCost1IsSet();
    contract_total_cost_1_is_modified = p_row.getContractTotalCost1IsModified();
    if ( !p_row.getContractTotalCost2IsNull() )
      contract_total_cost_2 = new Double( p_row.getContractTotalCost2() );
    contract_total_cost_2_is_set = p_row.getContractTotalCost2IsSet();
    contract_total_cost_2_is_modified = p_row.getContractTotalCost2IsModified();
    if ( !p_row.getContractTotalCost3IsNull() )
      contract_total_cost_3 = new Double( p_row.getContractTotalCost3() );
    contract_total_cost_3_is_set = p_row.getContractTotalCost3IsSet();
    contract_total_cost_3_is_modified = p_row.getContractTotalCost3IsModified();
    if ( !p_row.getContractTotalCost4IsNull() )
      contract_total_cost_4 = new Double( p_row.getContractTotalCost4() );
    contract_total_cost_4_is_set = p_row.getContractTotalCost4IsSet();
    contract_total_cost_4_is_modified = p_row.getContractTotalCost4IsModified();
    if ( !p_row.getContractTotalCost5IsNull() )
      contract_total_cost_5 = new Double( p_row.getContractTotalCost5() );
    contract_total_cost_5_is_set = p_row.getContractTotalCost5IsSet();
    contract_total_cost_5_is_modified = p_row.getContractTotalCost5IsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      account_id_is_set = true;
      account_id_is_modified = true;
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
      account_balance_5_is_set = true;
      account_balance_5_is_modified = true;
      today_executed_amount_0_is_set = true;
      today_executed_amount_0_is_modified = true;
      today_executed_amount_1_is_set = true;
      today_executed_amount_1_is_modified = true;
      today_executed_amount_2_is_set = true;
      today_executed_amount_2_is_modified = true;
      today_executed_amount_3_is_set = true;
      today_executed_amount_3_is_modified = true;
      today_executed_amount_4_is_set = true;
      today_executed_amount_4_is_modified = true;
      today_executed_amount_5_is_set = true;
      today_executed_amount_5_is_modified = true;
      today_unexecuted_amount_0_is_set = true;
      today_unexecuted_amount_0_is_modified = true;
      today_unexecuted_amount_1_is_set = true;
      today_unexecuted_amount_1_is_modified = true;
      today_unexecuted_amount_2_is_set = true;
      today_unexecuted_amount_2_is_modified = true;
      today_unexecuted_amount_3_is_set = true;
      today_unexecuted_amount_3_is_modified = true;
      today_unexecuted_amount_4_is_set = true;
      today_unexecuted_amount_4_is_modified = true;
      today_unexecuted_amount_5_is_set = true;
      today_unexecuted_amount_5_is_modified = true;
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
      other_restraint_5_is_set = true;
      other_restraint_5_is_modified = true;
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
      substitute_security_asset_5_is_set = true;
      substitute_security_asset_5_is_modified = true;
      contract_amount_0_is_set = true;
      contract_amount_0_is_modified = true;
      contract_amount_1_is_set = true;
      contract_amount_1_is_modified = true;
      contract_amount_2_is_set = true;
      contract_amount_2_is_modified = true;
      contract_amount_3_is_set = true;
      contract_amount_3_is_modified = true;
      contract_amount_4_is_set = true;
      contract_amount_4_is_modified = true;
      contract_amount_5_is_set = true;
      contract_amount_5_is_modified = true;
      day_repay_contract_amount_0_is_set = true;
      day_repay_contract_amount_0_is_modified = true;
      day_repay_contract_amount_1_is_set = true;
      day_repay_contract_amount_1_is_modified = true;
      day_repay_contract_amount_2_is_set = true;
      day_repay_contract_amount_2_is_modified = true;
      day_repay_contract_amount_3_is_set = true;
      day_repay_contract_amount_3_is_modified = true;
      day_repay_contract_amount_4_is_set = true;
      day_repay_contract_amount_4_is_modified = true;
      day_repay_contract_amount_5_is_set = true;
      day_repay_contract_amount_5_is_modified = true;
      margin_deposit_0_is_set = true;
      margin_deposit_0_is_modified = true;
      margin_deposit_1_is_set = true;
      margin_deposit_1_is_modified = true;
      margin_deposit_2_is_set = true;
      margin_deposit_2_is_modified = true;
      margin_deposit_3_is_set = true;
      margin_deposit_3_is_modified = true;
      margin_deposit_4_is_set = true;
      margin_deposit_4_is_modified = true;
      margin_deposit_5_is_set = true;
      margin_deposit_5_is_modified = true;
      cash_margin_deposit_0_is_set = true;
      cash_margin_deposit_0_is_modified = true;
      cash_margin_deposit_1_is_set = true;
      cash_margin_deposit_1_is_modified = true;
      cash_margin_deposit_2_is_set = true;
      cash_margin_deposit_2_is_modified = true;
      cash_margin_deposit_3_is_set = true;
      cash_margin_deposit_3_is_modified = true;
      cash_margin_deposit_4_is_set = true;
      cash_margin_deposit_4_is_modified = true;
      cash_margin_deposit_5_is_set = true;
      cash_margin_deposit_5_is_modified = true;
      contract_asset_profit_loss_is_set = true;
      contract_asset_profit_loss_is_modified = true;
      undeli_contract_amount_0_is_set = true;
      undeli_contract_amount_0_is_modified = true;
      undeli_contract_amount_1_is_set = true;
      undeli_contract_amount_1_is_modified = true;
      undeli_contract_amount_2_is_set = true;
      undeli_contract_amount_2_is_modified = true;
      undeli_contract_amount_3_is_set = true;
      undeli_contract_amount_3_is_modified = true;
      undeli_margin_deposit_0_is_set = true;
      undeli_margin_deposit_0_is_modified = true;
      undeli_margin_deposit_1_is_set = true;
      undeli_margin_deposit_1_is_modified = true;
      undeli_margin_deposit_2_is_set = true;
      undeli_margin_deposit_2_is_modified = true;
      undeli_margin_deposit_3_is_set = true;
      undeli_margin_deposit_3_is_modified = true;
      undeli_cash_margin_deposit_0_is_set = true;
      undeli_cash_margin_deposit_0_is_modified = true;
      undeli_cash_margin_deposit_1_is_set = true;
      undeli_cash_margin_deposit_1_is_modified = true;
      undeli_cash_margin_deposit_2_is_set = true;
      undeli_cash_margin_deposit_2_is_modified = true;
      undeli_cash_margin_deposit_3_is_set = true;
      undeli_cash_margin_deposit_3_is_modified = true;
      undeli_contract_loss_0_is_set = true;
      undeli_contract_loss_0_is_modified = true;
      undeli_contract_loss_1_is_set = true;
      undeli_contract_loss_1_is_modified = true;
      undeli_contract_loss_2_is_set = true;
      undeli_contract_loss_2_is_modified = true;
      undeli_contract_loss_3_is_set = true;
      undeli_contract_loss_3_is_modified = true;
      undeli_contract_profit_0_is_set = true;
      undeli_contract_profit_0_is_modified = true;
      undeli_contract_profit_1_is_set = true;
      undeli_contract_profit_1_is_modified = true;
      undeli_contract_profit_2_is_set = true;
      undeli_contract_profit_2_is_modified = true;
      undeli_contract_profit_3_is_set = true;
      undeli_contract_profit_3_is_modified = true;
      contract_total_cost_is_set = true;
      contract_total_cost_is_modified = true;
      mark_to_market_div_is_set = true;
      mark_to_market_div_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      contract_asset_profit_loss_1_is_set = true;
      contract_asset_profit_loss_1_is_modified = true;
      contract_asset_profit_loss_2_is_set = true;
      contract_asset_profit_loss_2_is_modified = true;
      contract_asset_profit_loss_3_is_set = true;
      contract_asset_profit_loss_3_is_modified = true;
      contract_asset_profit_loss_4_is_set = true;
      contract_asset_profit_loss_4_is_modified = true;
      contract_asset_profit_loss_5_is_set = true;
      contract_asset_profit_loss_5_is_modified = true;
      day_trade_restraint_5_is_set = true;
      day_trade_restraint_5_is_modified = true;
      contract_total_cost_1_is_set = true;
      contract_total_cost_1_is_modified = true;
      contract_total_cost_2_is_set = true;
      contract_total_cost_2_is_modified = true;
      contract_total_cost_3_is_set = true;
      contract_total_cost_3_is_modified = true;
      contract_total_cost_4_is_set = true;
      contract_total_cost_4_is_modified = true;
      contract_total_cost_5_is_set = true;
      contract_total_cost_5_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof TpCalcResultMarginRow ) )
       return false;
    return fieldsEqual( (TpCalcResultMarginRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のTpCalcResultMarginRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( TpCalcResultMarginRow row )
  {
    if ( calc_result_margin_id != row.getCalcResultMarginId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( account_balance_0 != row.getAccountBalance0() )
      return false;
    if ( account_balance_1 != row.getAccountBalance1() )
      return false;
    if ( account_balance_2 != row.getAccountBalance2() )
      return false;
    if ( account_balance_3 != row.getAccountBalance3() )
      return false;
    if ( account_balance_4 != row.getAccountBalance4() )
      return false;
    if ( account_balance_5 != row.getAccountBalance5() )
      return false;
    if ( today_executed_amount_0 != row.getTodayExecutedAmount0() )
      return false;
    if ( today_executed_amount_1 != row.getTodayExecutedAmount1() )
      return false;
    if ( today_executed_amount_2 != row.getTodayExecutedAmount2() )
      return false;
    if ( today_executed_amount_3 != row.getTodayExecutedAmount3() )
      return false;
    if ( today_executed_amount_4 != row.getTodayExecutedAmount4() )
      return false;
    if ( today_executed_amount_5 != row.getTodayExecutedAmount5() )
      return false;
    if ( today_unexecuted_amount_0 != row.getTodayUnexecutedAmount0() )
      return false;
    if ( today_unexecuted_amount_1 != row.getTodayUnexecutedAmount1() )
      return false;
    if ( today_unexecuted_amount_2 != row.getTodayUnexecutedAmount2() )
      return false;
    if ( today_unexecuted_amount_3 != row.getTodayUnexecutedAmount3() )
      return false;
    if ( today_unexecuted_amount_4 != row.getTodayUnexecutedAmount4() )
      return false;
    if ( today_unexecuted_amount_5 != row.getTodayUnexecutedAmount5() )
      return false;
    if ( day_trade_restraint_0 != row.getDayTradeRestraint0() )
      return false;
    if ( day_trade_restraint_1 != row.getDayTradeRestraint1() )
      return false;
    if ( day_trade_restraint_2 != row.getDayTradeRestraint2() )
      return false;
    if ( day_trade_restraint_3 != row.getDayTradeRestraint3() )
      return false;
    if ( day_trade_restraint_4 != row.getDayTradeRestraint4() )
      return false;
    if ( other_restraint_0 != row.getOtherRestraint0() )
      return false;
    if ( other_restraint_1 != row.getOtherRestraint1() )
      return false;
    if ( other_restraint_2 != row.getOtherRestraint2() )
      return false;
    if ( other_restraint_3 != row.getOtherRestraint3() )
      return false;
    if ( other_restraint_4 != row.getOtherRestraint4() )
      return false;
    if ( other_restraint_5 != row.getOtherRestraint5() )
      return false;
    if ( substitute_security_asset_0 != row.getSubstituteSecurityAsset0() )
      return false;
    if ( substitute_security_asset_1 != row.getSubstituteSecurityAsset1() )
      return false;
    if ( substitute_security_asset_2 != row.getSubstituteSecurityAsset2() )
      return false;
    if ( substitute_security_asset_3 != row.getSubstituteSecurityAsset3() )
      return false;
    if ( substitute_security_asset_4 != row.getSubstituteSecurityAsset4() )
      return false;
    if ( substitute_security_asset_5 != row.getSubstituteSecurityAsset5() )
      return false;
    if ( contract_amount_0 != row.getContractAmount0() )
      return false;
    if ( contract_amount_1 != row.getContractAmount1() )
      return false;
    if ( contract_amount_2 != row.getContractAmount2() )
      return false;
    if ( contract_amount_3 != row.getContractAmount3() )
      return false;
    if ( contract_amount_4 != row.getContractAmount4() )
      return false;
    if ( contract_amount_5 != row.getContractAmount5() )
      return false;
    if ( day_repay_contract_amount_0 != row.getDayRepayContractAmount0() )
      return false;
    if ( day_repay_contract_amount_1 != row.getDayRepayContractAmount1() )
      return false;
    if ( day_repay_contract_amount_2 != row.getDayRepayContractAmount2() )
      return false;
    if ( day_repay_contract_amount_3 != row.getDayRepayContractAmount3() )
      return false;
    if ( day_repay_contract_amount_4 != row.getDayRepayContractAmount4() )
      return false;
    if ( day_repay_contract_amount_5 != row.getDayRepayContractAmount5() )
      return false;
    if ( margin_deposit_0 != row.getMarginDeposit0() )
      return false;
    if ( margin_deposit_1 != row.getMarginDeposit1() )
      return false;
    if ( margin_deposit_2 != row.getMarginDeposit2() )
      return false;
    if ( margin_deposit_3 != row.getMarginDeposit3() )
      return false;
    if ( margin_deposit_4 != row.getMarginDeposit4() )
      return false;
    if ( margin_deposit_5 != row.getMarginDeposit5() )
      return false;
    if ( cash_margin_deposit_0 != row.getCashMarginDeposit0() )
      return false;
    if ( cash_margin_deposit_1 != row.getCashMarginDeposit1() )
      return false;
    if ( cash_margin_deposit_2 != row.getCashMarginDeposit2() )
      return false;
    if ( cash_margin_deposit_3 != row.getCashMarginDeposit3() )
      return false;
    if ( cash_margin_deposit_4 != row.getCashMarginDeposit4() )
      return false;
    if ( cash_margin_deposit_5 != row.getCashMarginDeposit5() )
      return false;
    if ( contract_asset_profit_loss != row.getContractAssetProfitLoss() )
      return false;
    if ( undeli_contract_amount_0 != row.getUndeliContractAmount0() )
      return false;
    if ( undeli_contract_amount_1 != row.getUndeliContractAmount1() )
      return false;
    if ( undeli_contract_amount_2 != row.getUndeliContractAmount2() )
      return false;
    if ( undeli_contract_amount_3 != row.getUndeliContractAmount3() )
      return false;
    if ( undeli_margin_deposit_0 != row.getUndeliMarginDeposit0() )
      return false;
    if ( undeli_margin_deposit_1 != row.getUndeliMarginDeposit1() )
      return false;
    if ( undeli_margin_deposit_2 != row.getUndeliMarginDeposit2() )
      return false;
    if ( undeli_margin_deposit_3 != row.getUndeliMarginDeposit3() )
      return false;
    if ( undeli_cash_margin_deposit_0 != row.getUndeliCashMarginDeposit0() )
      return false;
    if ( undeli_cash_margin_deposit_1 != row.getUndeliCashMarginDeposit1() )
      return false;
    if ( undeli_cash_margin_deposit_2 != row.getUndeliCashMarginDeposit2() )
      return false;
    if ( undeli_cash_margin_deposit_3 != row.getUndeliCashMarginDeposit3() )
      return false;
    if ( undeli_contract_loss_0 != row.getUndeliContractLoss0() )
      return false;
    if ( undeli_contract_loss_1 != row.getUndeliContractLoss1() )
      return false;
    if ( undeli_contract_loss_2 != row.getUndeliContractLoss2() )
      return false;
    if ( undeli_contract_loss_3 != row.getUndeliContractLoss3() )
      return false;
    if ( undeli_contract_profit_0 != row.getUndeliContractProfit0() )
      return false;
    if ( undeli_contract_profit_1 != row.getUndeliContractProfit1() )
      return false;
    if ( undeli_contract_profit_2 != row.getUndeliContractProfit2() )
      return false;
    if ( undeli_contract_profit_3 != row.getUndeliContractProfit3() )
      return false;
    if ( contract_total_cost != row.getContractTotalCost() )
      return false;
    if ( mark_to_market_div == null ) {
      if ( row.getMarkToMarketDiv() != null )
        return false;
    } else if ( !mark_to_market_div.equals( row.getMarkToMarketDiv() ) ) {
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
    if ( contract_asset_profit_loss_1 == null ) {
      if ( !row.getContractAssetProfitLoss1IsNull() )
        return false;
    } else if ( row.getContractAssetProfitLoss1IsNull() || ( contract_asset_profit_loss_1.doubleValue() != row.getContractAssetProfitLoss1() ) ) {
        return false;
    }
    if ( contract_asset_profit_loss_2 == null ) {
      if ( !row.getContractAssetProfitLoss2IsNull() )
        return false;
    } else if ( row.getContractAssetProfitLoss2IsNull() || ( contract_asset_profit_loss_2.doubleValue() != row.getContractAssetProfitLoss2() ) ) {
        return false;
    }
    if ( contract_asset_profit_loss_3 == null ) {
      if ( !row.getContractAssetProfitLoss3IsNull() )
        return false;
    } else if ( row.getContractAssetProfitLoss3IsNull() || ( contract_asset_profit_loss_3.doubleValue() != row.getContractAssetProfitLoss3() ) ) {
        return false;
    }
    if ( contract_asset_profit_loss_4 == null ) {
      if ( !row.getContractAssetProfitLoss4IsNull() )
        return false;
    } else if ( row.getContractAssetProfitLoss4IsNull() || ( contract_asset_profit_loss_4.doubleValue() != row.getContractAssetProfitLoss4() ) ) {
        return false;
    }
    if ( contract_asset_profit_loss_5 == null ) {
      if ( !row.getContractAssetProfitLoss5IsNull() )
        return false;
    } else if ( row.getContractAssetProfitLoss5IsNull() || ( contract_asset_profit_loss_5.doubleValue() != row.getContractAssetProfitLoss5() ) ) {
        return false;
    }
    if ( day_trade_restraint_5 == null ) {
      if ( !row.getDayTradeRestraint5IsNull() )
        return false;
    } else if ( row.getDayTradeRestraint5IsNull() || ( day_trade_restraint_5.doubleValue() != row.getDayTradeRestraint5() ) ) {
        return false;
    }
    if ( contract_total_cost_1 == null ) {
      if ( !row.getContractTotalCost1IsNull() )
        return false;
    } else if ( row.getContractTotalCost1IsNull() || ( contract_total_cost_1.doubleValue() != row.getContractTotalCost1() ) ) {
        return false;
    }
    if ( contract_total_cost_2 == null ) {
      if ( !row.getContractTotalCost2IsNull() )
        return false;
    } else if ( row.getContractTotalCost2IsNull() || ( contract_total_cost_2.doubleValue() != row.getContractTotalCost2() ) ) {
        return false;
    }
    if ( contract_total_cost_3 == null ) {
      if ( !row.getContractTotalCost3IsNull() )
        return false;
    } else if ( row.getContractTotalCost3IsNull() || ( contract_total_cost_3.doubleValue() != row.getContractTotalCost3() ) ) {
        return false;
    }
    if ( contract_total_cost_4 == null ) {
      if ( !row.getContractTotalCost4IsNull() )
        return false;
    } else if ( row.getContractTotalCost4IsNull() || ( contract_total_cost_4.doubleValue() != row.getContractTotalCost4() ) ) {
        return false;
    }
    if ( contract_total_cost_5 == null ) {
      if ( !row.getContractTotalCost5IsNull() )
        return false;
    } else if ( row.getContractTotalCost5IsNull() || ( contract_total_cost_5.doubleValue() != row.getContractTotalCost5() ) ) {
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
        + ((int) account_balance_0)
        + ((int) account_balance_1)
        + ((int) account_balance_2)
        + ((int) account_balance_3)
        + ((int) account_balance_4)
        + ((int) account_balance_5)
        + ((int) today_executed_amount_0)
        + ((int) today_executed_amount_1)
        + ((int) today_executed_amount_2)
        + ((int) today_executed_amount_3)
        + ((int) today_executed_amount_4)
        + ((int) today_executed_amount_5)
        + ((int) today_unexecuted_amount_0)
        + ((int) today_unexecuted_amount_1)
        + ((int) today_unexecuted_amount_2)
        + ((int) today_unexecuted_amount_3)
        + ((int) today_unexecuted_amount_4)
        + ((int) today_unexecuted_amount_5)
        + ((int) day_trade_restraint_0)
        + ((int) day_trade_restraint_1)
        + ((int) day_trade_restraint_2)
        + ((int) day_trade_restraint_3)
        + ((int) day_trade_restraint_4)
        + ((int) other_restraint_0)
        + ((int) other_restraint_1)
        + ((int) other_restraint_2)
        + ((int) other_restraint_3)
        + ((int) other_restraint_4)
        + ((int) other_restraint_5)
        + ((int) substitute_security_asset_0)
        + ((int) substitute_security_asset_1)
        + ((int) substitute_security_asset_2)
        + ((int) substitute_security_asset_3)
        + ((int) substitute_security_asset_4)
        + ((int) substitute_security_asset_5)
        + ((int) contract_amount_0)
        + ((int) contract_amount_1)
        + ((int) contract_amount_2)
        + ((int) contract_amount_3)
        + ((int) contract_amount_4)
        + ((int) contract_amount_5)
        + ((int) day_repay_contract_amount_0)
        + ((int) day_repay_contract_amount_1)
        + ((int) day_repay_contract_amount_2)
        + ((int) day_repay_contract_amount_3)
        + ((int) day_repay_contract_amount_4)
        + ((int) day_repay_contract_amount_5)
        + ((int) margin_deposit_0)
        + ((int) margin_deposit_1)
        + ((int) margin_deposit_2)
        + ((int) margin_deposit_3)
        + ((int) margin_deposit_4)
        + ((int) margin_deposit_5)
        + ((int) cash_margin_deposit_0)
        + ((int) cash_margin_deposit_1)
        + ((int) cash_margin_deposit_2)
        + ((int) cash_margin_deposit_3)
        + ((int) cash_margin_deposit_4)
        + ((int) cash_margin_deposit_5)
        + ((int) contract_asset_profit_loss)
        + ((int) undeli_contract_amount_0)
        + ((int) undeli_contract_amount_1)
        + ((int) undeli_contract_amount_2)
        + ((int) undeli_contract_amount_3)
        + ((int) undeli_margin_deposit_0)
        + ((int) undeli_margin_deposit_1)
        + ((int) undeli_margin_deposit_2)
        + ((int) undeli_margin_deposit_3)
        + ((int) undeli_cash_margin_deposit_0)
        + ((int) undeli_cash_margin_deposit_1)
        + ((int) undeli_cash_margin_deposit_2)
        + ((int) undeli_cash_margin_deposit_3)
        + ((int) undeli_contract_loss_0)
        + ((int) undeli_contract_loss_1)
        + ((int) undeli_contract_loss_2)
        + ((int) undeli_contract_loss_3)
        + ((int) undeli_contract_profit_0)
        + ((int) undeli_contract_profit_1)
        + ((int) undeli_contract_profit_2)
        + ((int) undeli_contract_profit_3)
        + ((int) contract_total_cost)
        + (mark_to_market_div!=null? mark_to_market_div.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (contract_asset_profit_loss_1!=null? contract_asset_profit_loss_1.hashCode(): 0) 
        + (contract_asset_profit_loss_2!=null? contract_asset_profit_loss_2.hashCode(): 0) 
        + (contract_asset_profit_loss_3!=null? contract_asset_profit_loss_3.hashCode(): 0) 
        + (contract_asset_profit_loss_4!=null? contract_asset_profit_loss_4.hashCode(): 0) 
        + (contract_asset_profit_loss_5!=null? contract_asset_profit_loss_5.hashCode(): 0) 
        + (day_trade_restraint_5!=null? day_trade_restraint_5.hashCode(): 0) 
        + (contract_total_cost_1!=null? contract_total_cost_1.hashCode(): 0) 
        + (contract_total_cost_2!=null? contract_total_cost_2.hashCode(): 0) 
        + (contract_total_cost_3!=null? contract_total_cost_3.hashCode(): 0) 
        + (contract_total_cost_4!=null? contract_total_cost_4.hashCode(): 0) 
        + (contract_total_cost_5!=null? contract_total_cost_5.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !account_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_id' must be set before inserting.");
		if ( !account_balance_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_balance_0' must be set before inserting.");
		if ( !account_balance_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_balance_1' must be set before inserting.");
		if ( !account_balance_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_balance_2' must be set before inserting.");
		if ( !account_balance_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_balance_3' must be set before inserting.");
		if ( !account_balance_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_balance_4' must be set before inserting.");
		if ( !account_balance_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_balance_5' must be set before inserting.");
		if ( !today_executed_amount_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'today_executed_amount_0' must be set before inserting.");
		if ( !today_executed_amount_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'today_executed_amount_1' must be set before inserting.");
		if ( !today_executed_amount_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'today_executed_amount_2' must be set before inserting.");
		if ( !today_executed_amount_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'today_executed_amount_3' must be set before inserting.");
		if ( !today_executed_amount_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'today_executed_amount_4' must be set before inserting.");
		if ( !today_executed_amount_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'today_executed_amount_5' must be set before inserting.");
		if ( !today_unexecuted_amount_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'today_unexecuted_amount_0' must be set before inserting.");
		if ( !today_unexecuted_amount_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'today_unexecuted_amount_1' must be set before inserting.");
		if ( !today_unexecuted_amount_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'today_unexecuted_amount_2' must be set before inserting.");
		if ( !today_unexecuted_amount_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'today_unexecuted_amount_3' must be set before inserting.");
		if ( !today_unexecuted_amount_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'today_unexecuted_amount_4' must be set before inserting.");
		if ( !today_unexecuted_amount_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'today_unexecuted_amount_5' must be set before inserting.");
		if ( !day_trade_restraint_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'day_trade_restraint_0' must be set before inserting.");
		if ( !day_trade_restraint_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'day_trade_restraint_1' must be set before inserting.");
		if ( !day_trade_restraint_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'day_trade_restraint_2' must be set before inserting.");
		if ( !day_trade_restraint_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'day_trade_restraint_3' must be set before inserting.");
		if ( !day_trade_restraint_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'day_trade_restraint_4' must be set before inserting.");
		if ( !other_restraint_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'other_restraint_0' must be set before inserting.");
		if ( !other_restraint_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'other_restraint_1' must be set before inserting.");
		if ( !other_restraint_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'other_restraint_2' must be set before inserting.");
		if ( !other_restraint_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'other_restraint_3' must be set before inserting.");
		if ( !other_restraint_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'other_restraint_4' must be set before inserting.");
		if ( !other_restraint_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'other_restraint_5' must be set before inserting.");
		if ( !substitute_security_asset_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'substitute_security_asset_0' must be set before inserting.");
		if ( !substitute_security_asset_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'substitute_security_asset_1' must be set before inserting.");
		if ( !substitute_security_asset_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'substitute_security_asset_2' must be set before inserting.");
		if ( !substitute_security_asset_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'substitute_security_asset_3' must be set before inserting.");
		if ( !substitute_security_asset_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'substitute_security_asset_4' must be set before inserting.");
		if ( !substitute_security_asset_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'substitute_security_asset_5' must be set before inserting.");
		if ( !contract_amount_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'contract_amount_0' must be set before inserting.");
		if ( !contract_amount_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'contract_amount_1' must be set before inserting.");
		if ( !contract_amount_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'contract_amount_2' must be set before inserting.");
		if ( !contract_amount_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'contract_amount_3' must be set before inserting.");
		if ( !contract_amount_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'contract_amount_4' must be set before inserting.");
		if ( !contract_amount_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'contract_amount_5' must be set before inserting.");
		if ( !day_repay_contract_amount_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'day_repay_contract_amount_0' must be set before inserting.");
		if ( !day_repay_contract_amount_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'day_repay_contract_amount_1' must be set before inserting.");
		if ( !day_repay_contract_amount_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'day_repay_contract_amount_2' must be set before inserting.");
		if ( !day_repay_contract_amount_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'day_repay_contract_amount_3' must be set before inserting.");
		if ( !day_repay_contract_amount_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'day_repay_contract_amount_4' must be set before inserting.");
		if ( !day_repay_contract_amount_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'day_repay_contract_amount_5' must be set before inserting.");
		if ( !margin_deposit_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_deposit_0' must be set before inserting.");
		if ( !margin_deposit_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_deposit_1' must be set before inserting.");
		if ( !margin_deposit_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_deposit_2' must be set before inserting.");
		if ( !margin_deposit_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_deposit_3' must be set before inserting.");
		if ( !margin_deposit_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_deposit_4' must be set before inserting.");
		if ( !margin_deposit_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_deposit_5' must be set before inserting.");
		if ( !cash_margin_deposit_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'cash_margin_deposit_0' must be set before inserting.");
		if ( !cash_margin_deposit_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'cash_margin_deposit_1' must be set before inserting.");
		if ( !cash_margin_deposit_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'cash_margin_deposit_2' must be set before inserting.");
		if ( !cash_margin_deposit_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'cash_margin_deposit_3' must be set before inserting.");
		if ( !cash_margin_deposit_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'cash_margin_deposit_4' must be set before inserting.");
		if ( !cash_margin_deposit_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'cash_margin_deposit_5' must be set before inserting.");
		if ( !contract_asset_profit_loss_is_set )
			throw new IllegalArgumentException("non-nullable field 'contract_asset_profit_loss' must be set before inserting.");
		if ( !undeli_contract_amount_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_contract_amount_0' must be set before inserting.");
		if ( !undeli_contract_amount_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_contract_amount_1' must be set before inserting.");
		if ( !undeli_contract_amount_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_contract_amount_2' must be set before inserting.");
		if ( !undeli_contract_amount_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_contract_amount_3' must be set before inserting.");
		if ( !undeli_margin_deposit_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_margin_deposit_0' must be set before inserting.");
		if ( !undeli_margin_deposit_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_margin_deposit_1' must be set before inserting.");
		if ( !undeli_margin_deposit_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_margin_deposit_2' must be set before inserting.");
		if ( !undeli_margin_deposit_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_margin_deposit_3' must be set before inserting.");
		if ( !undeli_cash_margin_deposit_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_cash_margin_deposit_0' must be set before inserting.");
		if ( !undeli_cash_margin_deposit_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_cash_margin_deposit_1' must be set before inserting.");
		if ( !undeli_cash_margin_deposit_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_cash_margin_deposit_2' must be set before inserting.");
		if ( !undeli_cash_margin_deposit_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_cash_margin_deposit_3' must be set before inserting.");
		if ( !undeli_contract_loss_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_contract_loss_0' must be set before inserting.");
		if ( !undeli_contract_loss_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_contract_loss_1' must be set before inserting.");
		if ( !undeli_contract_loss_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_contract_loss_2' must be set before inserting.");
		if ( !undeli_contract_loss_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_contract_loss_3' must be set before inserting.");
		if ( !undeli_contract_profit_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_contract_profit_0' must be set before inserting.");
		if ( !undeli_contract_profit_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_contract_profit_1' must be set before inserting.");
		if ( !undeli_contract_profit_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_contract_profit_2' must be set before inserting.");
		if ( !undeli_contract_profit_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'undeli_contract_profit_3' must be set before inserting.");
		if ( !contract_total_cost_is_set )
			throw new IllegalArgumentException("non-nullable field 'contract_total_cost' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("calc_result_margin_id",new Long(calc_result_margin_id));
		map.put("account_id",new Long(account_id));
		map.put("account_balance_0",new Double(account_balance_0));
		map.put("account_balance_1",new Double(account_balance_1));
		map.put("account_balance_2",new Double(account_balance_2));
		map.put("account_balance_3",new Double(account_balance_3));
		map.put("account_balance_4",new Double(account_balance_4));
		map.put("account_balance_5",new Double(account_balance_5));
		map.put("today_executed_amount_0",new Double(today_executed_amount_0));
		map.put("today_executed_amount_1",new Double(today_executed_amount_1));
		map.put("today_executed_amount_2",new Double(today_executed_amount_2));
		map.put("today_executed_amount_3",new Double(today_executed_amount_3));
		map.put("today_executed_amount_4",new Double(today_executed_amount_4));
		map.put("today_executed_amount_5",new Double(today_executed_amount_5));
		map.put("today_unexecuted_amount_0",new Double(today_unexecuted_amount_0));
		map.put("today_unexecuted_amount_1",new Double(today_unexecuted_amount_1));
		map.put("today_unexecuted_amount_2",new Double(today_unexecuted_amount_2));
		map.put("today_unexecuted_amount_3",new Double(today_unexecuted_amount_3));
		map.put("today_unexecuted_amount_4",new Double(today_unexecuted_amount_4));
		map.put("today_unexecuted_amount_5",new Double(today_unexecuted_amount_5));
		map.put("day_trade_restraint_0",new Double(day_trade_restraint_0));
		map.put("day_trade_restraint_1",new Double(day_trade_restraint_1));
		map.put("day_trade_restraint_2",new Double(day_trade_restraint_2));
		map.put("day_trade_restraint_3",new Double(day_trade_restraint_3));
		map.put("day_trade_restraint_4",new Double(day_trade_restraint_4));
		map.put("other_restraint_0",new Double(other_restraint_0));
		map.put("other_restraint_1",new Double(other_restraint_1));
		map.put("other_restraint_2",new Double(other_restraint_2));
		map.put("other_restraint_3",new Double(other_restraint_3));
		map.put("other_restraint_4",new Double(other_restraint_4));
		map.put("other_restraint_5",new Double(other_restraint_5));
		map.put("substitute_security_asset_0",new Double(substitute_security_asset_0));
		map.put("substitute_security_asset_1",new Double(substitute_security_asset_1));
		map.put("substitute_security_asset_2",new Double(substitute_security_asset_2));
		map.put("substitute_security_asset_3",new Double(substitute_security_asset_3));
		map.put("substitute_security_asset_4",new Double(substitute_security_asset_4));
		map.put("substitute_security_asset_5",new Double(substitute_security_asset_5));
		map.put("contract_amount_0",new Double(contract_amount_0));
		map.put("contract_amount_1",new Double(contract_amount_1));
		map.put("contract_amount_2",new Double(contract_amount_2));
		map.put("contract_amount_3",new Double(contract_amount_3));
		map.put("contract_amount_4",new Double(contract_amount_4));
		map.put("contract_amount_5",new Double(contract_amount_5));
		map.put("day_repay_contract_amount_0",new Double(day_repay_contract_amount_0));
		map.put("day_repay_contract_amount_1",new Double(day_repay_contract_amount_1));
		map.put("day_repay_contract_amount_2",new Double(day_repay_contract_amount_2));
		map.put("day_repay_contract_amount_3",new Double(day_repay_contract_amount_3));
		map.put("day_repay_contract_amount_4",new Double(day_repay_contract_amount_4));
		map.put("day_repay_contract_amount_5",new Double(day_repay_contract_amount_5));
		map.put("margin_deposit_0",new Double(margin_deposit_0));
		map.put("margin_deposit_1",new Double(margin_deposit_1));
		map.put("margin_deposit_2",new Double(margin_deposit_2));
		map.put("margin_deposit_3",new Double(margin_deposit_3));
		map.put("margin_deposit_4",new Double(margin_deposit_4));
		map.put("margin_deposit_5",new Double(margin_deposit_5));
		map.put("cash_margin_deposit_0",new Double(cash_margin_deposit_0));
		map.put("cash_margin_deposit_1",new Double(cash_margin_deposit_1));
		map.put("cash_margin_deposit_2",new Double(cash_margin_deposit_2));
		map.put("cash_margin_deposit_3",new Double(cash_margin_deposit_3));
		map.put("cash_margin_deposit_4",new Double(cash_margin_deposit_4));
		map.put("cash_margin_deposit_5",new Double(cash_margin_deposit_5));
		map.put("contract_asset_profit_loss",new Double(contract_asset_profit_loss));
		map.put("undeli_contract_amount_0",new Double(undeli_contract_amount_0));
		map.put("undeli_contract_amount_1",new Double(undeli_contract_amount_1));
		map.put("undeli_contract_amount_2",new Double(undeli_contract_amount_2));
		map.put("undeli_contract_amount_3",new Double(undeli_contract_amount_3));
		map.put("undeli_margin_deposit_0",new Double(undeli_margin_deposit_0));
		map.put("undeli_margin_deposit_1",new Double(undeli_margin_deposit_1));
		map.put("undeli_margin_deposit_2",new Double(undeli_margin_deposit_2));
		map.put("undeli_margin_deposit_3",new Double(undeli_margin_deposit_3));
		map.put("undeli_cash_margin_deposit_0",new Double(undeli_cash_margin_deposit_0));
		map.put("undeli_cash_margin_deposit_1",new Double(undeli_cash_margin_deposit_1));
		map.put("undeli_cash_margin_deposit_2",new Double(undeli_cash_margin_deposit_2));
		map.put("undeli_cash_margin_deposit_3",new Double(undeli_cash_margin_deposit_3));
		map.put("undeli_contract_loss_0",new Double(undeli_contract_loss_0));
		map.put("undeli_contract_loss_1",new Double(undeli_contract_loss_1));
		map.put("undeli_contract_loss_2",new Double(undeli_contract_loss_2));
		map.put("undeli_contract_loss_3",new Double(undeli_contract_loss_3));
		map.put("undeli_contract_profit_0",new Double(undeli_contract_profit_0));
		map.put("undeli_contract_profit_1",new Double(undeli_contract_profit_1));
		map.put("undeli_contract_profit_2",new Double(undeli_contract_profit_2));
		map.put("undeli_contract_profit_3",new Double(undeli_contract_profit_3));
		map.put("contract_total_cost",new Double(contract_total_cost));
		if ( mark_to_market_div_is_set )
			map.put("mark_to_market_div",mark_to_market_div);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( contract_asset_profit_loss_1 != null )
			map.put("contract_asset_profit_loss_1",contract_asset_profit_loss_1);
		if ( contract_asset_profit_loss_2 != null )
			map.put("contract_asset_profit_loss_2",contract_asset_profit_loss_2);
		if ( contract_asset_profit_loss_3 != null )
			map.put("contract_asset_profit_loss_3",contract_asset_profit_loss_3);
		if ( contract_asset_profit_loss_4 != null )
			map.put("contract_asset_profit_loss_4",contract_asset_profit_loss_4);
		if ( contract_asset_profit_loss_5 != null )
			map.put("contract_asset_profit_loss_5",contract_asset_profit_loss_5);
		if ( day_trade_restraint_5 != null )
			map.put("day_trade_restraint_5",day_trade_restraint_5);
		if ( contract_total_cost_1 != null )
			map.put("contract_total_cost_1",contract_total_cost_1);
		if ( contract_total_cost_2 != null )
			map.put("contract_total_cost_2",contract_total_cost_2);
		if ( contract_total_cost_3 != null )
			map.put("contract_total_cost_3",contract_total_cost_3);
		if ( contract_total_cost_4 != null )
			map.put("contract_total_cost_4",contract_total_cost_4);
		if ( contract_total_cost_5 != null )
			map.put("contract_total_cost_5",contract_total_cost_5);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( account_id_is_modified )
			map.put("account_id",new Long(account_id));
		if ( account_balance_0_is_modified )
			map.put("account_balance_0",new Double(account_balance_0));
		if ( account_balance_1_is_modified )
			map.put("account_balance_1",new Double(account_balance_1));
		if ( account_balance_2_is_modified )
			map.put("account_balance_2",new Double(account_balance_2));
		if ( account_balance_3_is_modified )
			map.put("account_balance_3",new Double(account_balance_3));
		if ( account_balance_4_is_modified )
			map.put("account_balance_4",new Double(account_balance_4));
		if ( account_balance_5_is_modified )
			map.put("account_balance_5",new Double(account_balance_5));
		if ( today_executed_amount_0_is_modified )
			map.put("today_executed_amount_0",new Double(today_executed_amount_0));
		if ( today_executed_amount_1_is_modified )
			map.put("today_executed_amount_1",new Double(today_executed_amount_1));
		if ( today_executed_amount_2_is_modified )
			map.put("today_executed_amount_2",new Double(today_executed_amount_2));
		if ( today_executed_amount_3_is_modified )
			map.put("today_executed_amount_3",new Double(today_executed_amount_3));
		if ( today_executed_amount_4_is_modified )
			map.put("today_executed_amount_4",new Double(today_executed_amount_4));
		if ( today_executed_amount_5_is_modified )
			map.put("today_executed_amount_5",new Double(today_executed_amount_5));
		if ( today_unexecuted_amount_0_is_modified )
			map.put("today_unexecuted_amount_0",new Double(today_unexecuted_amount_0));
		if ( today_unexecuted_amount_1_is_modified )
			map.put("today_unexecuted_amount_1",new Double(today_unexecuted_amount_1));
		if ( today_unexecuted_amount_2_is_modified )
			map.put("today_unexecuted_amount_2",new Double(today_unexecuted_amount_2));
		if ( today_unexecuted_amount_3_is_modified )
			map.put("today_unexecuted_amount_3",new Double(today_unexecuted_amount_3));
		if ( today_unexecuted_amount_4_is_modified )
			map.put("today_unexecuted_amount_4",new Double(today_unexecuted_amount_4));
		if ( today_unexecuted_amount_5_is_modified )
			map.put("today_unexecuted_amount_5",new Double(today_unexecuted_amount_5));
		if ( day_trade_restraint_0_is_modified )
			map.put("day_trade_restraint_0",new Double(day_trade_restraint_0));
		if ( day_trade_restraint_1_is_modified )
			map.put("day_trade_restraint_1",new Double(day_trade_restraint_1));
		if ( day_trade_restraint_2_is_modified )
			map.put("day_trade_restraint_2",new Double(day_trade_restraint_2));
		if ( day_trade_restraint_3_is_modified )
			map.put("day_trade_restraint_3",new Double(day_trade_restraint_3));
		if ( day_trade_restraint_4_is_modified )
			map.put("day_trade_restraint_4",new Double(day_trade_restraint_4));
		if ( other_restraint_0_is_modified )
			map.put("other_restraint_0",new Double(other_restraint_0));
		if ( other_restraint_1_is_modified )
			map.put("other_restraint_1",new Double(other_restraint_1));
		if ( other_restraint_2_is_modified )
			map.put("other_restraint_2",new Double(other_restraint_2));
		if ( other_restraint_3_is_modified )
			map.put("other_restraint_3",new Double(other_restraint_3));
		if ( other_restraint_4_is_modified )
			map.put("other_restraint_4",new Double(other_restraint_4));
		if ( other_restraint_5_is_modified )
			map.put("other_restraint_5",new Double(other_restraint_5));
		if ( substitute_security_asset_0_is_modified )
			map.put("substitute_security_asset_0",new Double(substitute_security_asset_0));
		if ( substitute_security_asset_1_is_modified )
			map.put("substitute_security_asset_1",new Double(substitute_security_asset_1));
		if ( substitute_security_asset_2_is_modified )
			map.put("substitute_security_asset_2",new Double(substitute_security_asset_2));
		if ( substitute_security_asset_3_is_modified )
			map.put("substitute_security_asset_3",new Double(substitute_security_asset_3));
		if ( substitute_security_asset_4_is_modified )
			map.put("substitute_security_asset_4",new Double(substitute_security_asset_4));
		if ( substitute_security_asset_5_is_modified )
			map.put("substitute_security_asset_5",new Double(substitute_security_asset_5));
		if ( contract_amount_0_is_modified )
			map.put("contract_amount_0",new Double(contract_amount_0));
		if ( contract_amount_1_is_modified )
			map.put("contract_amount_1",new Double(contract_amount_1));
		if ( contract_amount_2_is_modified )
			map.put("contract_amount_2",new Double(contract_amount_2));
		if ( contract_amount_3_is_modified )
			map.put("contract_amount_3",new Double(contract_amount_3));
		if ( contract_amount_4_is_modified )
			map.put("contract_amount_4",new Double(contract_amount_4));
		if ( contract_amount_5_is_modified )
			map.put("contract_amount_5",new Double(contract_amount_5));
		if ( day_repay_contract_amount_0_is_modified )
			map.put("day_repay_contract_amount_0",new Double(day_repay_contract_amount_0));
		if ( day_repay_contract_amount_1_is_modified )
			map.put("day_repay_contract_amount_1",new Double(day_repay_contract_amount_1));
		if ( day_repay_contract_amount_2_is_modified )
			map.put("day_repay_contract_amount_2",new Double(day_repay_contract_amount_2));
		if ( day_repay_contract_amount_3_is_modified )
			map.put("day_repay_contract_amount_3",new Double(day_repay_contract_amount_3));
		if ( day_repay_contract_amount_4_is_modified )
			map.put("day_repay_contract_amount_4",new Double(day_repay_contract_amount_4));
		if ( day_repay_contract_amount_5_is_modified )
			map.put("day_repay_contract_amount_5",new Double(day_repay_contract_amount_5));
		if ( margin_deposit_0_is_modified )
			map.put("margin_deposit_0",new Double(margin_deposit_0));
		if ( margin_deposit_1_is_modified )
			map.put("margin_deposit_1",new Double(margin_deposit_1));
		if ( margin_deposit_2_is_modified )
			map.put("margin_deposit_2",new Double(margin_deposit_2));
		if ( margin_deposit_3_is_modified )
			map.put("margin_deposit_3",new Double(margin_deposit_3));
		if ( margin_deposit_4_is_modified )
			map.put("margin_deposit_4",new Double(margin_deposit_4));
		if ( margin_deposit_5_is_modified )
			map.put("margin_deposit_5",new Double(margin_deposit_5));
		if ( cash_margin_deposit_0_is_modified )
			map.put("cash_margin_deposit_0",new Double(cash_margin_deposit_0));
		if ( cash_margin_deposit_1_is_modified )
			map.put("cash_margin_deposit_1",new Double(cash_margin_deposit_1));
		if ( cash_margin_deposit_2_is_modified )
			map.put("cash_margin_deposit_2",new Double(cash_margin_deposit_2));
		if ( cash_margin_deposit_3_is_modified )
			map.put("cash_margin_deposit_3",new Double(cash_margin_deposit_3));
		if ( cash_margin_deposit_4_is_modified )
			map.put("cash_margin_deposit_4",new Double(cash_margin_deposit_4));
		if ( cash_margin_deposit_5_is_modified )
			map.put("cash_margin_deposit_5",new Double(cash_margin_deposit_5));
		if ( contract_asset_profit_loss_is_modified )
			map.put("contract_asset_profit_loss",new Double(contract_asset_profit_loss));
		if ( undeli_contract_amount_0_is_modified )
			map.put("undeli_contract_amount_0",new Double(undeli_contract_amount_0));
		if ( undeli_contract_amount_1_is_modified )
			map.put("undeli_contract_amount_1",new Double(undeli_contract_amount_1));
		if ( undeli_contract_amount_2_is_modified )
			map.put("undeli_contract_amount_2",new Double(undeli_contract_amount_2));
		if ( undeli_contract_amount_3_is_modified )
			map.put("undeli_contract_amount_3",new Double(undeli_contract_amount_3));
		if ( undeli_margin_deposit_0_is_modified )
			map.put("undeli_margin_deposit_0",new Double(undeli_margin_deposit_0));
		if ( undeli_margin_deposit_1_is_modified )
			map.put("undeli_margin_deposit_1",new Double(undeli_margin_deposit_1));
		if ( undeli_margin_deposit_2_is_modified )
			map.put("undeli_margin_deposit_2",new Double(undeli_margin_deposit_2));
		if ( undeli_margin_deposit_3_is_modified )
			map.put("undeli_margin_deposit_3",new Double(undeli_margin_deposit_3));
		if ( undeli_cash_margin_deposit_0_is_modified )
			map.put("undeli_cash_margin_deposit_0",new Double(undeli_cash_margin_deposit_0));
		if ( undeli_cash_margin_deposit_1_is_modified )
			map.put("undeli_cash_margin_deposit_1",new Double(undeli_cash_margin_deposit_1));
		if ( undeli_cash_margin_deposit_2_is_modified )
			map.put("undeli_cash_margin_deposit_2",new Double(undeli_cash_margin_deposit_2));
		if ( undeli_cash_margin_deposit_3_is_modified )
			map.put("undeli_cash_margin_deposit_3",new Double(undeli_cash_margin_deposit_3));
		if ( undeli_contract_loss_0_is_modified )
			map.put("undeli_contract_loss_0",new Double(undeli_contract_loss_0));
		if ( undeli_contract_loss_1_is_modified )
			map.put("undeli_contract_loss_1",new Double(undeli_contract_loss_1));
		if ( undeli_contract_loss_2_is_modified )
			map.put("undeli_contract_loss_2",new Double(undeli_contract_loss_2));
		if ( undeli_contract_loss_3_is_modified )
			map.put("undeli_contract_loss_3",new Double(undeli_contract_loss_3));
		if ( undeli_contract_profit_0_is_modified )
			map.put("undeli_contract_profit_0",new Double(undeli_contract_profit_0));
		if ( undeli_contract_profit_1_is_modified )
			map.put("undeli_contract_profit_1",new Double(undeli_contract_profit_1));
		if ( undeli_contract_profit_2_is_modified )
			map.put("undeli_contract_profit_2",new Double(undeli_contract_profit_2));
		if ( undeli_contract_profit_3_is_modified )
			map.put("undeli_contract_profit_3",new Double(undeli_contract_profit_3));
		if ( contract_total_cost_is_modified )
			map.put("contract_total_cost",new Double(contract_total_cost));
		if ( mark_to_market_div_is_modified )
			map.put("mark_to_market_div",mark_to_market_div);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( contract_asset_profit_loss_1_is_modified )
			map.put("contract_asset_profit_loss_1",contract_asset_profit_loss_1);
		if ( contract_asset_profit_loss_2_is_modified )
			map.put("contract_asset_profit_loss_2",contract_asset_profit_loss_2);
		if ( contract_asset_profit_loss_3_is_modified )
			map.put("contract_asset_profit_loss_3",contract_asset_profit_loss_3);
		if ( contract_asset_profit_loss_4_is_modified )
			map.put("contract_asset_profit_loss_4",contract_asset_profit_loss_4);
		if ( contract_asset_profit_loss_5_is_modified )
			map.put("contract_asset_profit_loss_5",contract_asset_profit_loss_5);
		if ( day_trade_restraint_5_is_modified )
			map.put("day_trade_restraint_5",day_trade_restraint_5);
		if ( contract_total_cost_1_is_modified )
			map.put("contract_total_cost_1",contract_total_cost_1);
		if ( contract_total_cost_2_is_modified )
			map.put("contract_total_cost_2",contract_total_cost_2);
		if ( contract_total_cost_3_is_modified )
			map.put("contract_total_cost_3",contract_total_cost_3);
		if ( contract_total_cost_4_is_modified )
			map.put("contract_total_cost_4",contract_total_cost_4);
		if ( contract_total_cost_5_is_modified )
			map.put("contract_total_cost_5",contract_total_cost_5);
		if (map.size() == 0) {
			if ( account_id_is_set )
				map.put("account_id",new Long(account_id));
			if ( account_balance_0_is_set )
				map.put("account_balance_0",new Double(account_balance_0));
			if ( account_balance_1_is_set )
				map.put("account_balance_1",new Double(account_balance_1));
			if ( account_balance_2_is_set )
				map.put("account_balance_2",new Double(account_balance_2));
			if ( account_balance_3_is_set )
				map.put("account_balance_3",new Double(account_balance_3));
			if ( account_balance_4_is_set )
				map.put("account_balance_4",new Double(account_balance_4));
			if ( account_balance_5_is_set )
				map.put("account_balance_5",new Double(account_balance_5));
			if ( today_executed_amount_0_is_set )
				map.put("today_executed_amount_0",new Double(today_executed_amount_0));
			if ( today_executed_amount_1_is_set )
				map.put("today_executed_amount_1",new Double(today_executed_amount_1));
			if ( today_executed_amount_2_is_set )
				map.put("today_executed_amount_2",new Double(today_executed_amount_2));
			if ( today_executed_amount_3_is_set )
				map.put("today_executed_amount_3",new Double(today_executed_amount_3));
			if ( today_executed_amount_4_is_set )
				map.put("today_executed_amount_4",new Double(today_executed_amount_4));
			if ( today_executed_amount_5_is_set )
				map.put("today_executed_amount_5",new Double(today_executed_amount_5));
			if ( today_unexecuted_amount_0_is_set )
				map.put("today_unexecuted_amount_0",new Double(today_unexecuted_amount_0));
			if ( today_unexecuted_amount_1_is_set )
				map.put("today_unexecuted_amount_1",new Double(today_unexecuted_amount_1));
			if ( today_unexecuted_amount_2_is_set )
				map.put("today_unexecuted_amount_2",new Double(today_unexecuted_amount_2));
			if ( today_unexecuted_amount_3_is_set )
				map.put("today_unexecuted_amount_3",new Double(today_unexecuted_amount_3));
			if ( today_unexecuted_amount_4_is_set )
				map.put("today_unexecuted_amount_4",new Double(today_unexecuted_amount_4));
			if ( today_unexecuted_amount_5_is_set )
				map.put("today_unexecuted_amount_5",new Double(today_unexecuted_amount_5));
			if ( day_trade_restraint_0_is_set )
				map.put("day_trade_restraint_0",new Double(day_trade_restraint_0));
			if ( day_trade_restraint_1_is_set )
				map.put("day_trade_restraint_1",new Double(day_trade_restraint_1));
			if ( day_trade_restraint_2_is_set )
				map.put("day_trade_restraint_2",new Double(day_trade_restraint_2));
			if ( day_trade_restraint_3_is_set )
				map.put("day_trade_restraint_3",new Double(day_trade_restraint_3));
			if ( day_trade_restraint_4_is_set )
				map.put("day_trade_restraint_4",new Double(day_trade_restraint_4));
			if ( other_restraint_0_is_set )
				map.put("other_restraint_0",new Double(other_restraint_0));
			if ( other_restraint_1_is_set )
				map.put("other_restraint_1",new Double(other_restraint_1));
			if ( other_restraint_2_is_set )
				map.put("other_restraint_2",new Double(other_restraint_2));
			if ( other_restraint_3_is_set )
				map.put("other_restraint_3",new Double(other_restraint_3));
			if ( other_restraint_4_is_set )
				map.put("other_restraint_4",new Double(other_restraint_4));
			if ( other_restraint_5_is_set )
				map.put("other_restraint_5",new Double(other_restraint_5));
			if ( substitute_security_asset_0_is_set )
				map.put("substitute_security_asset_0",new Double(substitute_security_asset_0));
			if ( substitute_security_asset_1_is_set )
				map.put("substitute_security_asset_1",new Double(substitute_security_asset_1));
			if ( substitute_security_asset_2_is_set )
				map.put("substitute_security_asset_2",new Double(substitute_security_asset_2));
			if ( substitute_security_asset_3_is_set )
				map.put("substitute_security_asset_3",new Double(substitute_security_asset_3));
			if ( substitute_security_asset_4_is_set )
				map.put("substitute_security_asset_4",new Double(substitute_security_asset_4));
			if ( substitute_security_asset_5_is_set )
				map.put("substitute_security_asset_5",new Double(substitute_security_asset_5));
			if ( contract_amount_0_is_set )
				map.put("contract_amount_0",new Double(contract_amount_0));
			if ( contract_amount_1_is_set )
				map.put("contract_amount_1",new Double(contract_amount_1));
			if ( contract_amount_2_is_set )
				map.put("contract_amount_2",new Double(contract_amount_2));
			if ( contract_amount_3_is_set )
				map.put("contract_amount_3",new Double(contract_amount_3));
			if ( contract_amount_4_is_set )
				map.put("contract_amount_4",new Double(contract_amount_4));
			if ( contract_amount_5_is_set )
				map.put("contract_amount_5",new Double(contract_amount_5));
			if ( day_repay_contract_amount_0_is_set )
				map.put("day_repay_contract_amount_0",new Double(day_repay_contract_amount_0));
			if ( day_repay_contract_amount_1_is_set )
				map.put("day_repay_contract_amount_1",new Double(day_repay_contract_amount_1));
			if ( day_repay_contract_amount_2_is_set )
				map.put("day_repay_contract_amount_2",new Double(day_repay_contract_amount_2));
			if ( day_repay_contract_amount_3_is_set )
				map.put("day_repay_contract_amount_3",new Double(day_repay_contract_amount_3));
			if ( day_repay_contract_amount_4_is_set )
				map.put("day_repay_contract_amount_4",new Double(day_repay_contract_amount_4));
			if ( day_repay_contract_amount_5_is_set )
				map.put("day_repay_contract_amount_5",new Double(day_repay_contract_amount_5));
			if ( margin_deposit_0_is_set )
				map.put("margin_deposit_0",new Double(margin_deposit_0));
			if ( margin_deposit_1_is_set )
				map.put("margin_deposit_1",new Double(margin_deposit_1));
			if ( margin_deposit_2_is_set )
				map.put("margin_deposit_2",new Double(margin_deposit_2));
			if ( margin_deposit_3_is_set )
				map.put("margin_deposit_3",new Double(margin_deposit_3));
			if ( margin_deposit_4_is_set )
				map.put("margin_deposit_4",new Double(margin_deposit_4));
			if ( margin_deposit_5_is_set )
				map.put("margin_deposit_5",new Double(margin_deposit_5));
			if ( cash_margin_deposit_0_is_set )
				map.put("cash_margin_deposit_0",new Double(cash_margin_deposit_0));
			if ( cash_margin_deposit_1_is_set )
				map.put("cash_margin_deposit_1",new Double(cash_margin_deposit_1));
			if ( cash_margin_deposit_2_is_set )
				map.put("cash_margin_deposit_2",new Double(cash_margin_deposit_2));
			if ( cash_margin_deposit_3_is_set )
				map.put("cash_margin_deposit_3",new Double(cash_margin_deposit_3));
			if ( cash_margin_deposit_4_is_set )
				map.put("cash_margin_deposit_4",new Double(cash_margin_deposit_4));
			if ( cash_margin_deposit_5_is_set )
				map.put("cash_margin_deposit_5",new Double(cash_margin_deposit_5));
			if ( contract_asset_profit_loss_is_set )
				map.put("contract_asset_profit_loss",new Double(contract_asset_profit_loss));
			if ( undeli_contract_amount_0_is_set )
				map.put("undeli_contract_amount_0",new Double(undeli_contract_amount_0));
			if ( undeli_contract_amount_1_is_set )
				map.put("undeli_contract_amount_1",new Double(undeli_contract_amount_1));
			if ( undeli_contract_amount_2_is_set )
				map.put("undeli_contract_amount_2",new Double(undeli_contract_amount_2));
			if ( undeli_contract_amount_3_is_set )
				map.put("undeli_contract_amount_3",new Double(undeli_contract_amount_3));
			if ( undeli_margin_deposit_0_is_set )
				map.put("undeli_margin_deposit_0",new Double(undeli_margin_deposit_0));
			if ( undeli_margin_deposit_1_is_set )
				map.put("undeli_margin_deposit_1",new Double(undeli_margin_deposit_1));
			if ( undeli_margin_deposit_2_is_set )
				map.put("undeli_margin_deposit_2",new Double(undeli_margin_deposit_2));
			if ( undeli_margin_deposit_3_is_set )
				map.put("undeli_margin_deposit_3",new Double(undeli_margin_deposit_3));
			if ( undeli_cash_margin_deposit_0_is_set )
				map.put("undeli_cash_margin_deposit_0",new Double(undeli_cash_margin_deposit_0));
			if ( undeli_cash_margin_deposit_1_is_set )
				map.put("undeli_cash_margin_deposit_1",new Double(undeli_cash_margin_deposit_1));
			if ( undeli_cash_margin_deposit_2_is_set )
				map.put("undeli_cash_margin_deposit_2",new Double(undeli_cash_margin_deposit_2));
			if ( undeli_cash_margin_deposit_3_is_set )
				map.put("undeli_cash_margin_deposit_3",new Double(undeli_cash_margin_deposit_3));
			if ( undeli_contract_loss_0_is_set )
				map.put("undeli_contract_loss_0",new Double(undeli_contract_loss_0));
			if ( undeli_contract_loss_1_is_set )
				map.put("undeli_contract_loss_1",new Double(undeli_contract_loss_1));
			if ( undeli_contract_loss_2_is_set )
				map.put("undeli_contract_loss_2",new Double(undeli_contract_loss_2));
			if ( undeli_contract_loss_3_is_set )
				map.put("undeli_contract_loss_3",new Double(undeli_contract_loss_3));
			if ( undeli_contract_profit_0_is_set )
				map.put("undeli_contract_profit_0",new Double(undeli_contract_profit_0));
			if ( undeli_contract_profit_1_is_set )
				map.put("undeli_contract_profit_1",new Double(undeli_contract_profit_1));
			if ( undeli_contract_profit_2_is_set )
				map.put("undeli_contract_profit_2",new Double(undeli_contract_profit_2));
			if ( undeli_contract_profit_3_is_set )
				map.put("undeli_contract_profit_3",new Double(undeli_contract_profit_3));
			if ( contract_total_cost_is_set )
				map.put("contract_total_cost",new Double(contract_total_cost));
			if ( mark_to_market_div_is_set )
				map.put("mark_to_market_div",mark_to_market_div);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("contract_asset_profit_loss_1",contract_asset_profit_loss_1);
			map.put("contract_asset_profit_loss_2",contract_asset_profit_loss_2);
			map.put("contract_asset_profit_loss_3",contract_asset_profit_loss_3);
			map.put("contract_asset_profit_loss_4",contract_asset_profit_loss_4);
			map.put("contract_asset_profit_loss_5",contract_asset_profit_loss_5);
			map.put("day_trade_restraint_5",day_trade_restraint_5);
			map.put("contract_total_cost_1",contract_total_cost_1);
			map.put("contract_total_cost_2",contract_total_cost_2);
			map.put("contract_total_cost_3",contract_total_cost_3);
			map.put("contract_total_cost_4",contract_total_cost_4);
			map.put("contract_total_cost_5",contract_total_cost_5);
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
   * <em>account_balance_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAccountBalance0()
  {
    return account_balance_0;
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
   * @@return doubleの値 
   */
  public final double getAccountBalance1()
  {
    return account_balance_1;
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
   * @@return doubleの値 
   */
  public final double getAccountBalance2()
  {
    return account_balance_2;
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
   * @@return doubleの値 
   */
  public final double getAccountBalance3()
  {
    return account_balance_3;
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
   * @@return doubleの値 
   */
  public final double getAccountBalance4()
  {
    return account_balance_4;
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
   * <em>account_balance_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAccountBalance5()
  {
    return account_balance_5;
  }


  /** 
   * <em>account_balance_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalance5IsSet() {
    return account_balance_5_is_set;
  }


  /** 
   * <em>account_balance_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountBalance5IsModified() {
    return account_balance_5_is_modified;
  }


  /** 
   * <em>today_executed_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayExecutedAmount0()
  {
    return today_executed_amount_0;
  }


  /** 
   * <em>today_executed_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayExecutedAmount0IsSet() {
    return today_executed_amount_0_is_set;
  }


  /** 
   * <em>today_executed_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayExecutedAmount0IsModified() {
    return today_executed_amount_0_is_modified;
  }


  /** 
   * <em>today_executed_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayExecutedAmount1()
  {
    return today_executed_amount_1;
  }


  /** 
   * <em>today_executed_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayExecutedAmount1IsSet() {
    return today_executed_amount_1_is_set;
  }


  /** 
   * <em>today_executed_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayExecutedAmount1IsModified() {
    return today_executed_amount_1_is_modified;
  }


  /** 
   * <em>today_executed_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayExecutedAmount2()
  {
    return today_executed_amount_2;
  }


  /** 
   * <em>today_executed_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayExecutedAmount2IsSet() {
    return today_executed_amount_2_is_set;
  }


  /** 
   * <em>today_executed_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayExecutedAmount2IsModified() {
    return today_executed_amount_2_is_modified;
  }


  /** 
   * <em>today_executed_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayExecutedAmount3()
  {
    return today_executed_amount_3;
  }


  /** 
   * <em>today_executed_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayExecutedAmount3IsSet() {
    return today_executed_amount_3_is_set;
  }


  /** 
   * <em>today_executed_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayExecutedAmount3IsModified() {
    return today_executed_amount_3_is_modified;
  }


  /** 
   * <em>today_executed_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayExecutedAmount4()
  {
    return today_executed_amount_4;
  }


  /** 
   * <em>today_executed_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayExecutedAmount4IsSet() {
    return today_executed_amount_4_is_set;
  }


  /** 
   * <em>today_executed_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayExecutedAmount4IsModified() {
    return today_executed_amount_4_is_modified;
  }


  /** 
   * <em>today_executed_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayExecutedAmount5()
  {
    return today_executed_amount_5;
  }


  /** 
   * <em>today_executed_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayExecutedAmount5IsSet() {
    return today_executed_amount_5_is_set;
  }


  /** 
   * <em>today_executed_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayExecutedAmount5IsModified() {
    return today_executed_amount_5_is_modified;
  }


  /** 
   * <em>today_unexecuted_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayUnexecutedAmount0()
  {
    return today_unexecuted_amount_0;
  }


  /** 
   * <em>today_unexecuted_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayUnexecutedAmount0IsSet() {
    return today_unexecuted_amount_0_is_set;
  }


  /** 
   * <em>today_unexecuted_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayUnexecutedAmount0IsModified() {
    return today_unexecuted_amount_0_is_modified;
  }


  /** 
   * <em>today_unexecuted_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayUnexecutedAmount1()
  {
    return today_unexecuted_amount_1;
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
   * @@return doubleの値 
   */
  public final double getTodayUnexecutedAmount2()
  {
    return today_unexecuted_amount_2;
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
   * @@return doubleの値 
   */
  public final double getTodayUnexecutedAmount3()
  {
    return today_unexecuted_amount_3;
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
   * @@return doubleの値 
   */
  public final double getTodayUnexecutedAmount4()
  {
    return today_unexecuted_amount_4;
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
   * <em>today_unexecuted_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTodayUnexecutedAmount5()
  {
    return today_unexecuted_amount_5;
  }


  /** 
   * <em>today_unexecuted_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayUnexecutedAmount5IsSet() {
    return today_unexecuted_amount_5_is_set;
  }


  /** 
   * <em>today_unexecuted_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTodayUnexecutedAmount5IsModified() {
    return today_unexecuted_amount_5_is_modified;
  }


  /** 
   * <em>day_trade_restraint_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayTradeRestraint0()
  {
    return day_trade_restraint_0;
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
   * @@return doubleの値 
   */
  public final double getDayTradeRestraint1()
  {
    return day_trade_restraint_1;
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
   * @@return doubleの値 
   */
  public final double getDayTradeRestraint2()
  {
    return day_trade_restraint_2;
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
   * @@return doubleの値 
   */
  public final double getDayTradeRestraint3()
  {
    return day_trade_restraint_3;
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
   * @@return doubleの値 
   */
  public final double getDayTradeRestraint4()
  {
    return day_trade_restraint_4;
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
   * @@return doubleの値 
   */
  public final double getOtherRestraint0()
  {
    return other_restraint_0;
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
   * @@return doubleの値 
   */
  public final double getOtherRestraint1()
  {
    return other_restraint_1;
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
   * @@return doubleの値 
   */
  public final double getOtherRestraint2()
  {
    return other_restraint_2;
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
   * @@return doubleの値 
   */
  public final double getOtherRestraint3()
  {
    return other_restraint_3;
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
   * @@return doubleの値 
   */
  public final double getOtherRestraint4()
  {
    return other_restraint_4;
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
   * <em>other_restraint_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOtherRestraint5()
  {
    return other_restraint_5;
  }


  /** 
   * <em>other_restraint_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherRestraint5IsSet() {
    return other_restraint_5_is_set;
  }


  /** 
   * <em>other_restraint_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOtherRestraint5IsModified() {
    return other_restraint_5_is_modified;
  }


  /** 
   * <em>substitute_security_asset_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSubstituteSecurityAsset0()
  {
    return substitute_security_asset_0;
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
   * @@return doubleの値 
   */
  public final double getSubstituteSecurityAsset1()
  {
    return substitute_security_asset_1;
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
   * @@return doubleの値 
   */
  public final double getSubstituteSecurityAsset2()
  {
    return substitute_security_asset_2;
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
   * @@return doubleの値 
   */
  public final double getSubstituteSecurityAsset3()
  {
    return substitute_security_asset_3;
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
   * @@return doubleの値 
   */
  public final double getSubstituteSecurityAsset4()
  {
    return substitute_security_asset_4;
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
   * <em>substitute_security_asset_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSubstituteSecurityAsset5()
  {
    return substitute_security_asset_5;
  }


  /** 
   * <em>substitute_security_asset_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteSecurityAsset5IsSet() {
    return substitute_security_asset_5_is_set;
  }


  /** 
   * <em>substitute_security_asset_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSubstituteSecurityAsset5IsModified() {
    return substitute_security_asset_5_is_modified;
  }


  /** 
   * <em>contract_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAmount0()
  {
    return contract_amount_0;
  }


  /** 
   * <em>contract_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmount0IsSet() {
    return contract_amount_0_is_set;
  }


  /** 
   * <em>contract_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmount0IsModified() {
    return contract_amount_0_is_modified;
  }


  /** 
   * <em>contract_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAmount1()
  {
    return contract_amount_1;
  }


  /** 
   * <em>contract_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmount1IsSet() {
    return contract_amount_1_is_set;
  }


  /** 
   * <em>contract_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmount1IsModified() {
    return contract_amount_1_is_modified;
  }


  /** 
   * <em>contract_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAmount2()
  {
    return contract_amount_2;
  }


  /** 
   * <em>contract_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmount2IsSet() {
    return contract_amount_2_is_set;
  }


  /** 
   * <em>contract_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmount2IsModified() {
    return contract_amount_2_is_modified;
  }


  /** 
   * <em>contract_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAmount3()
  {
    return contract_amount_3;
  }


  /** 
   * <em>contract_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmount3IsSet() {
    return contract_amount_3_is_set;
  }


  /** 
   * <em>contract_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmount3IsModified() {
    return contract_amount_3_is_modified;
  }


  /** 
   * <em>contract_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAmount4()
  {
    return contract_amount_4;
  }


  /** 
   * <em>contract_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmount4IsSet() {
    return contract_amount_4_is_set;
  }


  /** 
   * <em>contract_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmount4IsModified() {
    return contract_amount_4_is_modified;
  }


  /** 
   * <em>contract_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAmount5()
  {
    return contract_amount_5;
  }


  /** 
   * <em>contract_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmount5IsSet() {
    return contract_amount_5_is_set;
  }


  /** 
   * <em>contract_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAmount5IsModified() {
    return contract_amount_5_is_modified;
  }


  /** 
   * <em>day_repay_contract_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayContractAmount0()
  {
    return day_repay_contract_amount_0;
  }


  /** 
   * <em>day_repay_contract_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayContractAmount0IsSet() {
    return day_repay_contract_amount_0_is_set;
  }


  /** 
   * <em>day_repay_contract_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayContractAmount0IsModified() {
    return day_repay_contract_amount_0_is_modified;
  }


  /** 
   * <em>day_repay_contract_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayContractAmount1()
  {
    return day_repay_contract_amount_1;
  }


  /** 
   * <em>day_repay_contract_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayContractAmount1IsSet() {
    return day_repay_contract_amount_1_is_set;
  }


  /** 
   * <em>day_repay_contract_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayContractAmount1IsModified() {
    return day_repay_contract_amount_1_is_modified;
  }


  /** 
   * <em>day_repay_contract_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayContractAmount2()
  {
    return day_repay_contract_amount_2;
  }


  /** 
   * <em>day_repay_contract_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayContractAmount2IsSet() {
    return day_repay_contract_amount_2_is_set;
  }


  /** 
   * <em>day_repay_contract_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayContractAmount2IsModified() {
    return day_repay_contract_amount_2_is_modified;
  }


  /** 
   * <em>day_repay_contract_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayContractAmount3()
  {
    return day_repay_contract_amount_3;
  }


  /** 
   * <em>day_repay_contract_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayContractAmount3IsSet() {
    return day_repay_contract_amount_3_is_set;
  }


  /** 
   * <em>day_repay_contract_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayContractAmount3IsModified() {
    return day_repay_contract_amount_3_is_modified;
  }


  /** 
   * <em>day_repay_contract_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayContractAmount4()
  {
    return day_repay_contract_amount_4;
  }


  /** 
   * <em>day_repay_contract_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayContractAmount4IsSet() {
    return day_repay_contract_amount_4_is_set;
  }


  /** 
   * <em>day_repay_contract_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayContractAmount4IsModified() {
    return day_repay_contract_amount_4_is_modified;
  }


  /** 
   * <em>day_repay_contract_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayRepayContractAmount5()
  {
    return day_repay_contract_amount_5;
  }


  /** 
   * <em>day_repay_contract_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayContractAmount5IsSet() {
    return day_repay_contract_amount_5_is_set;
  }


  /** 
   * <em>day_repay_contract_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayRepayContractAmount5IsModified() {
    return day_repay_contract_amount_5_is_modified;
  }


  /** 
   * <em>margin_deposit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginDeposit0()
  {
    return margin_deposit_0;
  }


  /** 
   * <em>margin_deposit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDeposit0IsSet() {
    return margin_deposit_0_is_set;
  }


  /** 
   * <em>margin_deposit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDeposit0IsModified() {
    return margin_deposit_0_is_modified;
  }


  /** 
   * <em>margin_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginDeposit1()
  {
    return margin_deposit_1;
  }


  /** 
   * <em>margin_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDeposit1IsSet() {
    return margin_deposit_1_is_set;
  }


  /** 
   * <em>margin_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDeposit1IsModified() {
    return margin_deposit_1_is_modified;
  }


  /** 
   * <em>margin_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginDeposit2()
  {
    return margin_deposit_2;
  }


  /** 
   * <em>margin_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDeposit2IsSet() {
    return margin_deposit_2_is_set;
  }


  /** 
   * <em>margin_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDeposit2IsModified() {
    return margin_deposit_2_is_modified;
  }


  /** 
   * <em>margin_deposit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginDeposit3()
  {
    return margin_deposit_3;
  }


  /** 
   * <em>margin_deposit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDeposit3IsSet() {
    return margin_deposit_3_is_set;
  }


  /** 
   * <em>margin_deposit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDeposit3IsModified() {
    return margin_deposit_3_is_modified;
  }


  /** 
   * <em>margin_deposit_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginDeposit4()
  {
    return margin_deposit_4;
  }


  /** 
   * <em>margin_deposit_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDeposit4IsSet() {
    return margin_deposit_4_is_set;
  }


  /** 
   * <em>margin_deposit_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDeposit4IsModified() {
    return margin_deposit_4_is_modified;
  }


  /** 
   * <em>margin_deposit_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginDeposit5()
  {
    return margin_deposit_5;
  }


  /** 
   * <em>margin_deposit_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDeposit5IsSet() {
    return margin_deposit_5_is_set;
  }


  /** 
   * <em>margin_deposit_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDeposit5IsModified() {
    return margin_deposit_5_is_modified;
  }


  /** 
   * <em>cash_margin_deposit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashMarginDeposit0()
  {
    return cash_margin_deposit_0;
  }


  /** 
   * <em>cash_margin_deposit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDeposit0IsSet() {
    return cash_margin_deposit_0_is_set;
  }


  /** 
   * <em>cash_margin_deposit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDeposit0IsModified() {
    return cash_margin_deposit_0_is_modified;
  }


  /** 
   * <em>cash_margin_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashMarginDeposit1()
  {
    return cash_margin_deposit_1;
  }


  /** 
   * <em>cash_margin_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDeposit1IsSet() {
    return cash_margin_deposit_1_is_set;
  }


  /** 
   * <em>cash_margin_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDeposit1IsModified() {
    return cash_margin_deposit_1_is_modified;
  }


  /** 
   * <em>cash_margin_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashMarginDeposit2()
  {
    return cash_margin_deposit_2;
  }


  /** 
   * <em>cash_margin_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDeposit2IsSet() {
    return cash_margin_deposit_2_is_set;
  }


  /** 
   * <em>cash_margin_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDeposit2IsModified() {
    return cash_margin_deposit_2_is_modified;
  }


  /** 
   * <em>cash_margin_deposit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashMarginDeposit3()
  {
    return cash_margin_deposit_3;
  }


  /** 
   * <em>cash_margin_deposit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDeposit3IsSet() {
    return cash_margin_deposit_3_is_set;
  }


  /** 
   * <em>cash_margin_deposit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDeposit3IsModified() {
    return cash_margin_deposit_3_is_modified;
  }


  /** 
   * <em>cash_margin_deposit_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashMarginDeposit4()
  {
    return cash_margin_deposit_4;
  }


  /** 
   * <em>cash_margin_deposit_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDeposit4IsSet() {
    return cash_margin_deposit_4_is_set;
  }


  /** 
   * <em>cash_margin_deposit_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDeposit4IsModified() {
    return cash_margin_deposit_4_is_modified;
  }


  /** 
   * <em>cash_margin_deposit_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashMarginDeposit5()
  {
    return cash_margin_deposit_5;
  }


  /** 
   * <em>cash_margin_deposit_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDeposit5IsSet() {
    return cash_margin_deposit_5_is_set;
  }


  /** 
   * <em>cash_margin_deposit_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashMarginDeposit5IsModified() {
    return cash_margin_deposit_5_is_modified;
  }


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetProfitLoss()
  {
    return contract_asset_profit_loss;
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
   * <em>undeli_contract_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUndeliContractAmount0()
  {
    return undeli_contract_amount_0;
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
   * @@return doubleの値 
   */
  public final double getUndeliContractAmount1()
  {
    return undeli_contract_amount_1;
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
   * @@return doubleの値 
   */
  public final double getUndeliContractAmount2()
  {
    return undeli_contract_amount_2;
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
   * @@return doubleの値 
   */
  public final double getUndeliContractAmount3()
  {
    return undeli_contract_amount_3;
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
   * <em>undeli_margin_deposit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUndeliMarginDeposit0()
  {
    return undeli_margin_deposit_0;
  }


  /** 
   * <em>undeli_margin_deposit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliMarginDeposit0IsSet() {
    return undeli_margin_deposit_0_is_set;
  }


  /** 
   * <em>undeli_margin_deposit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliMarginDeposit0IsModified() {
    return undeli_margin_deposit_0_is_modified;
  }


  /** 
   * <em>undeli_margin_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUndeliMarginDeposit1()
  {
    return undeli_margin_deposit_1;
  }


  /** 
   * <em>undeli_margin_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliMarginDeposit1IsSet() {
    return undeli_margin_deposit_1_is_set;
  }


  /** 
   * <em>undeli_margin_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliMarginDeposit1IsModified() {
    return undeli_margin_deposit_1_is_modified;
  }


  /** 
   * <em>undeli_margin_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUndeliMarginDeposit2()
  {
    return undeli_margin_deposit_2;
  }


  /** 
   * <em>undeli_margin_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliMarginDeposit2IsSet() {
    return undeli_margin_deposit_2_is_set;
  }


  /** 
   * <em>undeli_margin_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliMarginDeposit2IsModified() {
    return undeli_margin_deposit_2_is_modified;
  }


  /** 
   * <em>undeli_margin_deposit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUndeliMarginDeposit3()
  {
    return undeli_margin_deposit_3;
  }


  /** 
   * <em>undeli_margin_deposit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliMarginDeposit3IsSet() {
    return undeli_margin_deposit_3_is_set;
  }


  /** 
   * <em>undeli_margin_deposit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliMarginDeposit3IsModified() {
    return undeli_margin_deposit_3_is_modified;
  }


  /** 
   * <em>undeli_cash_margin_deposit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUndeliCashMarginDeposit0()
  {
    return undeli_cash_margin_deposit_0;
  }


  /** 
   * <em>undeli_cash_margin_deposit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliCashMarginDeposit0IsSet() {
    return undeli_cash_margin_deposit_0_is_set;
  }


  /** 
   * <em>undeli_cash_margin_deposit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliCashMarginDeposit0IsModified() {
    return undeli_cash_margin_deposit_0_is_modified;
  }


  /** 
   * <em>undeli_cash_margin_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUndeliCashMarginDeposit1()
  {
    return undeli_cash_margin_deposit_1;
  }


  /** 
   * <em>undeli_cash_margin_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliCashMarginDeposit1IsSet() {
    return undeli_cash_margin_deposit_1_is_set;
  }


  /** 
   * <em>undeli_cash_margin_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliCashMarginDeposit1IsModified() {
    return undeli_cash_margin_deposit_1_is_modified;
  }


  /** 
   * <em>undeli_cash_margin_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUndeliCashMarginDeposit2()
  {
    return undeli_cash_margin_deposit_2;
  }


  /** 
   * <em>undeli_cash_margin_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliCashMarginDeposit2IsSet() {
    return undeli_cash_margin_deposit_2_is_set;
  }


  /** 
   * <em>undeli_cash_margin_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliCashMarginDeposit2IsModified() {
    return undeli_cash_margin_deposit_2_is_modified;
  }


  /** 
   * <em>undeli_cash_margin_deposit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUndeliCashMarginDeposit3()
  {
    return undeli_cash_margin_deposit_3;
  }


  /** 
   * <em>undeli_cash_margin_deposit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliCashMarginDeposit3IsSet() {
    return undeli_cash_margin_deposit_3_is_set;
  }


  /** 
   * <em>undeli_cash_margin_deposit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliCashMarginDeposit3IsModified() {
    return undeli_cash_margin_deposit_3_is_modified;
  }


  /** 
   * <em>undeli_contract_loss_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUndeliContractLoss0()
  {
    return undeli_contract_loss_0;
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
   * @@return doubleの値 
   */
  public final double getUndeliContractLoss1()
  {
    return undeli_contract_loss_1;
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
   * @@return doubleの値 
   */
  public final double getUndeliContractLoss2()
  {
    return undeli_contract_loss_2;
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
   * @@return doubleの値 
   */
  public final double getUndeliContractLoss3()
  {
    return undeli_contract_loss_3;
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
   * <em>undeli_contract_profit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUndeliContractProfit0()
  {
    return undeli_contract_profit_0;
  }


  /** 
   * <em>undeli_contract_profit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractProfit0IsSet() {
    return undeli_contract_profit_0_is_set;
  }


  /** 
   * <em>undeli_contract_profit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractProfit0IsModified() {
    return undeli_contract_profit_0_is_modified;
  }


  /** 
   * <em>undeli_contract_profit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUndeliContractProfit1()
  {
    return undeli_contract_profit_1;
  }


  /** 
   * <em>undeli_contract_profit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractProfit1IsSet() {
    return undeli_contract_profit_1_is_set;
  }


  /** 
   * <em>undeli_contract_profit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractProfit1IsModified() {
    return undeli_contract_profit_1_is_modified;
  }


  /** 
   * <em>undeli_contract_profit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUndeliContractProfit2()
  {
    return undeli_contract_profit_2;
  }


  /** 
   * <em>undeli_contract_profit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractProfit2IsSet() {
    return undeli_contract_profit_2_is_set;
  }


  /** 
   * <em>undeli_contract_profit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractProfit2IsModified() {
    return undeli_contract_profit_2_is_modified;
  }


  /** 
   * <em>undeli_contract_profit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getUndeliContractProfit3()
  {
    return undeli_contract_profit_3;
  }


  /** 
   * <em>undeli_contract_profit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractProfit3IsSet() {
    return undeli_contract_profit_3_is_set;
  }


  /** 
   * <em>undeli_contract_profit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUndeliContractProfit3IsModified() {
    return undeli_contract_profit_3_is_modified;
  }


  /** 
   * <em>contract_total_cost</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractTotalCost()
  {
    return contract_total_cost;
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
   * <em>mark_to_market_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getMarkToMarketDiv()
  {
    return mark_to_market_div;
  }


  /** 
   * <em>mark_to_market_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarkToMarketDivIsSet() {
    return mark_to_market_div_is_set;
  }


  /** 
   * <em>mark_to_market_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarkToMarketDivIsModified() {
    return mark_to_market_div_is_modified;
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
   * <em>contract_asset_profit_loss_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetProfitLoss1()
  {
    return ( contract_asset_profit_loss_1==null? ((double)0): contract_asset_profit_loss_1.doubleValue() );
  }


  /** 
   * <em>contract_asset_profit_loss_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetProfitLoss1IsNull()
  {
    return contract_asset_profit_loss_1 == null;
  }


  /** 
   * <em>contract_asset_profit_loss_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitLoss1IsSet() {
    return contract_asset_profit_loss_1_is_set;
  }


  /** 
   * <em>contract_asset_profit_loss_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitLoss1IsModified() {
    return contract_asset_profit_loss_1_is_modified;
  }


  /** 
   * <em>contract_asset_profit_loss_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetProfitLoss2()
  {
    return ( contract_asset_profit_loss_2==null? ((double)0): contract_asset_profit_loss_2.doubleValue() );
  }


  /** 
   * <em>contract_asset_profit_loss_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetProfitLoss2IsNull()
  {
    return contract_asset_profit_loss_2 == null;
  }


  /** 
   * <em>contract_asset_profit_loss_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitLoss2IsSet() {
    return contract_asset_profit_loss_2_is_set;
  }


  /** 
   * <em>contract_asset_profit_loss_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitLoss2IsModified() {
    return contract_asset_profit_loss_2_is_modified;
  }


  /** 
   * <em>contract_asset_profit_loss_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetProfitLoss3()
  {
    return ( contract_asset_profit_loss_3==null? ((double)0): contract_asset_profit_loss_3.doubleValue() );
  }


  /** 
   * <em>contract_asset_profit_loss_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetProfitLoss3IsNull()
  {
    return contract_asset_profit_loss_3 == null;
  }


  /** 
   * <em>contract_asset_profit_loss_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitLoss3IsSet() {
    return contract_asset_profit_loss_3_is_set;
  }


  /** 
   * <em>contract_asset_profit_loss_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitLoss3IsModified() {
    return contract_asset_profit_loss_3_is_modified;
  }


  /** 
   * <em>contract_asset_profit_loss_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetProfitLoss4()
  {
    return ( contract_asset_profit_loss_4==null? ((double)0): contract_asset_profit_loss_4.doubleValue() );
  }


  /** 
   * <em>contract_asset_profit_loss_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetProfitLoss4IsNull()
  {
    return contract_asset_profit_loss_4 == null;
  }


  /** 
   * <em>contract_asset_profit_loss_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitLoss4IsSet() {
    return contract_asset_profit_loss_4_is_set;
  }


  /** 
   * <em>contract_asset_profit_loss_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitLoss4IsModified() {
    return contract_asset_profit_loss_4_is_modified;
  }


  /** 
   * <em>contract_asset_profit_loss_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractAssetProfitLoss5()
  {
    return ( contract_asset_profit_loss_5==null? ((double)0): contract_asset_profit_loss_5.doubleValue() );
  }


  /** 
   * <em>contract_asset_profit_loss_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractAssetProfitLoss5IsNull()
  {
    return contract_asset_profit_loss_5 == null;
  }


  /** 
   * <em>contract_asset_profit_loss_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitLoss5IsSet() {
    return contract_asset_profit_loss_5_is_set;
  }


  /** 
   * <em>contract_asset_profit_loss_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractAssetProfitLoss5IsModified() {
    return contract_asset_profit_loss_5_is_modified;
  }


  /** 
   * <em>day_trade_restraint_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayTradeRestraint5()
  {
    return ( day_trade_restraint_5==null? ((double)0): day_trade_restraint_5.doubleValue() );
  }


  /** 
   * <em>day_trade_restraint_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getDayTradeRestraint5IsNull()
  {
    return day_trade_restraint_5 == null;
  }


  /** 
   * <em>day_trade_restraint_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayTradeRestraint5IsSet() {
    return day_trade_restraint_5_is_set;
  }


  /** 
   * <em>day_trade_restraint_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDayTradeRestraint5IsModified() {
    return day_trade_restraint_5_is_modified;
  }


  /** 
   * <em>contract_total_cost_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractTotalCost1()
  {
    return ( contract_total_cost_1==null? ((double)0): contract_total_cost_1.doubleValue() );
  }


  /** 
   * <em>contract_total_cost_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractTotalCost1IsNull()
  {
    return contract_total_cost_1 == null;
  }


  /** 
   * <em>contract_total_cost_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractTotalCost1IsSet() {
    return contract_total_cost_1_is_set;
  }


  /** 
   * <em>contract_total_cost_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractTotalCost1IsModified() {
    return contract_total_cost_1_is_modified;
  }


  /** 
   * <em>contract_total_cost_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractTotalCost2()
  {
    return ( contract_total_cost_2==null? ((double)0): contract_total_cost_2.doubleValue() );
  }


  /** 
   * <em>contract_total_cost_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractTotalCost2IsNull()
  {
    return contract_total_cost_2 == null;
  }


  /** 
   * <em>contract_total_cost_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractTotalCost2IsSet() {
    return contract_total_cost_2_is_set;
  }


  /** 
   * <em>contract_total_cost_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractTotalCost2IsModified() {
    return contract_total_cost_2_is_modified;
  }


  /** 
   * <em>contract_total_cost_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractTotalCost3()
  {
    return ( contract_total_cost_3==null? ((double)0): contract_total_cost_3.doubleValue() );
  }


  /** 
   * <em>contract_total_cost_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractTotalCost3IsNull()
  {
    return contract_total_cost_3 == null;
  }


  /** 
   * <em>contract_total_cost_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractTotalCost3IsSet() {
    return contract_total_cost_3_is_set;
  }


  /** 
   * <em>contract_total_cost_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractTotalCost3IsModified() {
    return contract_total_cost_3_is_modified;
  }


  /** 
   * <em>contract_total_cost_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractTotalCost4()
  {
    return ( contract_total_cost_4==null? ((double)0): contract_total_cost_4.doubleValue() );
  }


  /** 
   * <em>contract_total_cost_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractTotalCost4IsNull()
  {
    return contract_total_cost_4 == null;
  }


  /** 
   * <em>contract_total_cost_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractTotalCost4IsSet() {
    return contract_total_cost_4_is_set;
  }


  /** 
   * <em>contract_total_cost_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractTotalCost4IsModified() {
    return contract_total_cost_4_is_modified;
  }


  /** 
   * <em>contract_total_cost_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getContractTotalCost5()
  {
    return ( contract_total_cost_5==null? ((double)0): contract_total_cost_5.doubleValue() );
  }


  /** 
   * <em>contract_total_cost_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getContractTotalCost5IsNull()
  {
    return contract_total_cost_5 == null;
  }


  /** 
   * <em>contract_total_cost_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractTotalCost5IsSet() {
    return contract_total_cost_5_is_set;
  }


  /** 
   * <em>contract_total_cost_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getContractTotalCost5IsModified() {
    return contract_total_cost_5_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new TpCalcResultMarginPK(calc_result_margin_id);
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
   * <em>account_balance_0</em>カラムの値を設定します。 
   *
   * @@param p_accountBalance0 <em>account_balance_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAccountBalance0( double p_accountBalance0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_0 = p_accountBalance0;
    account_balance_0_is_set = true;
    account_balance_0_is_modified = true;
  }


  /** 
   * <em>account_balance_1</em>カラムの値を設定します。 
   *
   * @@param p_accountBalance1 <em>account_balance_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAccountBalance1( double p_accountBalance1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_1 = p_accountBalance1;
    account_balance_1_is_set = true;
    account_balance_1_is_modified = true;
  }


  /** 
   * <em>account_balance_2</em>カラムの値を設定します。 
   *
   * @@param p_accountBalance2 <em>account_balance_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAccountBalance2( double p_accountBalance2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_2 = p_accountBalance2;
    account_balance_2_is_set = true;
    account_balance_2_is_modified = true;
  }


  /** 
   * <em>account_balance_3</em>カラムの値を設定します。 
   *
   * @@param p_accountBalance3 <em>account_balance_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAccountBalance3( double p_accountBalance3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_3 = p_accountBalance3;
    account_balance_3_is_set = true;
    account_balance_3_is_modified = true;
  }


  /** 
   * <em>account_balance_4</em>カラムの値を設定します。 
   *
   * @@param p_accountBalance4 <em>account_balance_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAccountBalance4( double p_accountBalance4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_4 = p_accountBalance4;
    account_balance_4_is_set = true;
    account_balance_4_is_modified = true;
  }


  /** 
   * <em>account_balance_5</em>カラムの値を設定します。 
   *
   * @@param p_accountBalance5 <em>account_balance_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAccountBalance5( double p_accountBalance5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_5 = p_accountBalance5;
    account_balance_5_is_set = true;
    account_balance_5_is_modified = true;
  }


  /** 
   * <em>today_executed_amount_0</em>カラムの値を設定します。 
   *
   * @@param p_todayExecutedAmount0 <em>today_executed_amount_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayExecutedAmount0( double p_todayExecutedAmount0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_executed_amount_0 = p_todayExecutedAmount0;
    today_executed_amount_0_is_set = true;
    today_executed_amount_0_is_modified = true;
  }


  /** 
   * <em>today_executed_amount_1</em>カラムの値を設定します。 
   *
   * @@param p_todayExecutedAmount1 <em>today_executed_amount_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayExecutedAmount1( double p_todayExecutedAmount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_executed_amount_1 = p_todayExecutedAmount1;
    today_executed_amount_1_is_set = true;
    today_executed_amount_1_is_modified = true;
  }


  /** 
   * <em>today_executed_amount_2</em>カラムの値を設定します。 
   *
   * @@param p_todayExecutedAmount2 <em>today_executed_amount_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayExecutedAmount2( double p_todayExecutedAmount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_executed_amount_2 = p_todayExecutedAmount2;
    today_executed_amount_2_is_set = true;
    today_executed_amount_2_is_modified = true;
  }


  /** 
   * <em>today_executed_amount_3</em>カラムの値を設定します。 
   *
   * @@param p_todayExecutedAmount3 <em>today_executed_amount_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayExecutedAmount3( double p_todayExecutedAmount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_executed_amount_3 = p_todayExecutedAmount3;
    today_executed_amount_3_is_set = true;
    today_executed_amount_3_is_modified = true;
  }


  /** 
   * <em>today_executed_amount_4</em>カラムの値を設定します。 
   *
   * @@param p_todayExecutedAmount4 <em>today_executed_amount_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayExecutedAmount4( double p_todayExecutedAmount4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_executed_amount_4 = p_todayExecutedAmount4;
    today_executed_amount_4_is_set = true;
    today_executed_amount_4_is_modified = true;
  }


  /** 
   * <em>today_executed_amount_5</em>カラムの値を設定します。 
   *
   * @@param p_todayExecutedAmount5 <em>today_executed_amount_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayExecutedAmount5( double p_todayExecutedAmount5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_executed_amount_5 = p_todayExecutedAmount5;
    today_executed_amount_5_is_set = true;
    today_executed_amount_5_is_modified = true;
  }


  /** 
   * <em>today_unexecuted_amount_0</em>カラムの値を設定します。 
   *
   * @@param p_todayUnexecutedAmount0 <em>today_unexecuted_amount_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayUnexecutedAmount0( double p_todayUnexecutedAmount0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_unexecuted_amount_0 = p_todayUnexecutedAmount0;
    today_unexecuted_amount_0_is_set = true;
    today_unexecuted_amount_0_is_modified = true;
  }


  /** 
   * <em>today_unexecuted_amount_1</em>カラムの値を設定します。 
   *
   * @@param p_todayUnexecutedAmount1 <em>today_unexecuted_amount_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayUnexecutedAmount1( double p_todayUnexecutedAmount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_unexecuted_amount_1 = p_todayUnexecutedAmount1;
    today_unexecuted_amount_1_is_set = true;
    today_unexecuted_amount_1_is_modified = true;
  }


  /** 
   * <em>today_unexecuted_amount_2</em>カラムの値を設定します。 
   *
   * @@param p_todayUnexecutedAmount2 <em>today_unexecuted_amount_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayUnexecutedAmount2( double p_todayUnexecutedAmount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_unexecuted_amount_2 = p_todayUnexecutedAmount2;
    today_unexecuted_amount_2_is_set = true;
    today_unexecuted_amount_2_is_modified = true;
  }


  /** 
   * <em>today_unexecuted_amount_3</em>カラムの値を設定します。 
   *
   * @@param p_todayUnexecutedAmount3 <em>today_unexecuted_amount_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayUnexecutedAmount3( double p_todayUnexecutedAmount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_unexecuted_amount_3 = p_todayUnexecutedAmount3;
    today_unexecuted_amount_3_is_set = true;
    today_unexecuted_amount_3_is_modified = true;
  }


  /** 
   * <em>today_unexecuted_amount_4</em>カラムの値を設定します。 
   *
   * @@param p_todayUnexecutedAmount4 <em>today_unexecuted_amount_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayUnexecutedAmount4( double p_todayUnexecutedAmount4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_unexecuted_amount_4 = p_todayUnexecutedAmount4;
    today_unexecuted_amount_4_is_set = true;
    today_unexecuted_amount_4_is_modified = true;
  }


  /** 
   * <em>today_unexecuted_amount_5</em>カラムの値を設定します。 
   *
   * @@param p_todayUnexecutedAmount5 <em>today_unexecuted_amount_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTodayUnexecutedAmount5( double p_todayUnexecutedAmount5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    today_unexecuted_amount_5 = p_todayUnexecutedAmount5;
    today_unexecuted_amount_5_is_set = true;
    today_unexecuted_amount_5_is_modified = true;
  }


  /** 
   * <em>day_trade_restraint_0</em>カラムの値を設定します。 
   *
   * @@param p_dayTradeRestraint0 <em>day_trade_restraint_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayTradeRestraint0( double p_dayTradeRestraint0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_0 = p_dayTradeRestraint0;
    day_trade_restraint_0_is_set = true;
    day_trade_restraint_0_is_modified = true;
  }


  /** 
   * <em>day_trade_restraint_1</em>カラムの値を設定します。 
   *
   * @@param p_dayTradeRestraint1 <em>day_trade_restraint_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayTradeRestraint1( double p_dayTradeRestraint1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_1 = p_dayTradeRestraint1;
    day_trade_restraint_1_is_set = true;
    day_trade_restraint_1_is_modified = true;
  }


  /** 
   * <em>day_trade_restraint_2</em>カラムの値を設定します。 
   *
   * @@param p_dayTradeRestraint2 <em>day_trade_restraint_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayTradeRestraint2( double p_dayTradeRestraint2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_2 = p_dayTradeRestraint2;
    day_trade_restraint_2_is_set = true;
    day_trade_restraint_2_is_modified = true;
  }


  /** 
   * <em>day_trade_restraint_3</em>カラムの値を設定します。 
   *
   * @@param p_dayTradeRestraint3 <em>day_trade_restraint_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayTradeRestraint3( double p_dayTradeRestraint3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_3 = p_dayTradeRestraint3;
    day_trade_restraint_3_is_set = true;
    day_trade_restraint_3_is_modified = true;
  }


  /** 
   * <em>day_trade_restraint_4</em>カラムの値を設定します。 
   *
   * @@param p_dayTradeRestraint4 <em>day_trade_restraint_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayTradeRestraint4( double p_dayTradeRestraint4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_4 = p_dayTradeRestraint4;
    day_trade_restraint_4_is_set = true;
    day_trade_restraint_4_is_modified = true;
  }


  /** 
   * <em>other_restraint_0</em>カラムの値を設定します。 
   *
   * @@param p_otherRestraint0 <em>other_restraint_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOtherRestraint0( double p_otherRestraint0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_restraint_0 = p_otherRestraint0;
    other_restraint_0_is_set = true;
    other_restraint_0_is_modified = true;
  }


  /** 
   * <em>other_restraint_1</em>カラムの値を設定します。 
   *
   * @@param p_otherRestraint1 <em>other_restraint_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOtherRestraint1( double p_otherRestraint1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_restraint_1 = p_otherRestraint1;
    other_restraint_1_is_set = true;
    other_restraint_1_is_modified = true;
  }


  /** 
   * <em>other_restraint_2</em>カラムの値を設定します。 
   *
   * @@param p_otherRestraint2 <em>other_restraint_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOtherRestraint2( double p_otherRestraint2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_restraint_2 = p_otherRestraint2;
    other_restraint_2_is_set = true;
    other_restraint_2_is_modified = true;
  }


  /** 
   * <em>other_restraint_3</em>カラムの値を設定します。 
   *
   * @@param p_otherRestraint3 <em>other_restraint_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOtherRestraint3( double p_otherRestraint3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_restraint_3 = p_otherRestraint3;
    other_restraint_3_is_set = true;
    other_restraint_3_is_modified = true;
  }


  /** 
   * <em>other_restraint_4</em>カラムの値を設定します。 
   *
   * @@param p_otherRestraint4 <em>other_restraint_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOtherRestraint4( double p_otherRestraint4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_restraint_4 = p_otherRestraint4;
    other_restraint_4_is_set = true;
    other_restraint_4_is_modified = true;
  }


  /** 
   * <em>other_restraint_5</em>カラムの値を設定します。 
   *
   * @@param p_otherRestraint5 <em>other_restraint_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOtherRestraint5( double p_otherRestraint5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    other_restraint_5 = p_otherRestraint5;
    other_restraint_5_is_set = true;
    other_restraint_5_is_modified = true;
  }


  /** 
   * <em>substitute_security_asset_0</em>カラムの値を設定します。 
   *
   * @@param p_substituteSecurityAsset0 <em>substitute_security_asset_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSubstituteSecurityAsset0( double p_substituteSecurityAsset0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_0 = p_substituteSecurityAsset0;
    substitute_security_asset_0_is_set = true;
    substitute_security_asset_0_is_modified = true;
  }


  /** 
   * <em>substitute_security_asset_1</em>カラムの値を設定します。 
   *
   * @@param p_substituteSecurityAsset1 <em>substitute_security_asset_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSubstituteSecurityAsset1( double p_substituteSecurityAsset1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_1 = p_substituteSecurityAsset1;
    substitute_security_asset_1_is_set = true;
    substitute_security_asset_1_is_modified = true;
  }


  /** 
   * <em>substitute_security_asset_2</em>カラムの値を設定します。 
   *
   * @@param p_substituteSecurityAsset2 <em>substitute_security_asset_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSubstituteSecurityAsset2( double p_substituteSecurityAsset2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_2 = p_substituteSecurityAsset2;
    substitute_security_asset_2_is_set = true;
    substitute_security_asset_2_is_modified = true;
  }


  /** 
   * <em>substitute_security_asset_3</em>カラムの値を設定します。 
   *
   * @@param p_substituteSecurityAsset3 <em>substitute_security_asset_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSubstituteSecurityAsset3( double p_substituteSecurityAsset3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_3 = p_substituteSecurityAsset3;
    substitute_security_asset_3_is_set = true;
    substitute_security_asset_3_is_modified = true;
  }


  /** 
   * <em>substitute_security_asset_4</em>カラムの値を設定します。 
   *
   * @@param p_substituteSecurityAsset4 <em>substitute_security_asset_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSubstituteSecurityAsset4( double p_substituteSecurityAsset4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_4 = p_substituteSecurityAsset4;
    substitute_security_asset_4_is_set = true;
    substitute_security_asset_4_is_modified = true;
  }


  /** 
   * <em>substitute_security_asset_5</em>カラムの値を設定します。 
   *
   * @@param p_substituteSecurityAsset5 <em>substitute_security_asset_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSubstituteSecurityAsset5( double p_substituteSecurityAsset5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    substitute_security_asset_5 = p_substituteSecurityAsset5;
    substitute_security_asset_5_is_set = true;
    substitute_security_asset_5_is_modified = true;
  }


  /** 
   * <em>contract_amount_0</em>カラムの値を設定します。 
   *
   * @@param p_contractAmount0 <em>contract_amount_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAmount0( double p_contractAmount0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount_0 = p_contractAmount0;
    contract_amount_0_is_set = true;
    contract_amount_0_is_modified = true;
  }


  /** 
   * <em>contract_amount_1</em>カラムの値を設定します。 
   *
   * @@param p_contractAmount1 <em>contract_amount_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAmount1( double p_contractAmount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount_1 = p_contractAmount1;
    contract_amount_1_is_set = true;
    contract_amount_1_is_modified = true;
  }


  /** 
   * <em>contract_amount_2</em>カラムの値を設定します。 
   *
   * @@param p_contractAmount2 <em>contract_amount_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAmount2( double p_contractAmount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount_2 = p_contractAmount2;
    contract_amount_2_is_set = true;
    contract_amount_2_is_modified = true;
  }


  /** 
   * <em>contract_amount_3</em>カラムの値を設定します。 
   *
   * @@param p_contractAmount3 <em>contract_amount_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAmount3( double p_contractAmount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount_3 = p_contractAmount3;
    contract_amount_3_is_set = true;
    contract_amount_3_is_modified = true;
  }


  /** 
   * <em>contract_amount_4</em>カラムの値を設定します。 
   *
   * @@param p_contractAmount4 <em>contract_amount_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAmount4( double p_contractAmount4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount_4 = p_contractAmount4;
    contract_amount_4_is_set = true;
    contract_amount_4_is_modified = true;
  }


  /** 
   * <em>contract_amount_5</em>カラムの値を設定します。 
   *
   * @@param p_contractAmount5 <em>contract_amount_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAmount5( double p_contractAmount5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_amount_5 = p_contractAmount5;
    contract_amount_5_is_set = true;
    contract_amount_5_is_modified = true;
  }


  /** 
   * <em>day_repay_contract_amount_0</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayContractAmount0 <em>day_repay_contract_amount_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayContractAmount0( double p_dayRepayContractAmount0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_contract_amount_0 = p_dayRepayContractAmount0;
    day_repay_contract_amount_0_is_set = true;
    day_repay_contract_amount_0_is_modified = true;
  }


  /** 
   * <em>day_repay_contract_amount_1</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayContractAmount1 <em>day_repay_contract_amount_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayContractAmount1( double p_dayRepayContractAmount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_contract_amount_1 = p_dayRepayContractAmount1;
    day_repay_contract_amount_1_is_set = true;
    day_repay_contract_amount_1_is_modified = true;
  }


  /** 
   * <em>day_repay_contract_amount_2</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayContractAmount2 <em>day_repay_contract_amount_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayContractAmount2( double p_dayRepayContractAmount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_contract_amount_2 = p_dayRepayContractAmount2;
    day_repay_contract_amount_2_is_set = true;
    day_repay_contract_amount_2_is_modified = true;
  }


  /** 
   * <em>day_repay_contract_amount_3</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayContractAmount3 <em>day_repay_contract_amount_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayContractAmount3( double p_dayRepayContractAmount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_contract_amount_3 = p_dayRepayContractAmount3;
    day_repay_contract_amount_3_is_set = true;
    day_repay_contract_amount_3_is_modified = true;
  }


  /** 
   * <em>day_repay_contract_amount_4</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayContractAmount4 <em>day_repay_contract_amount_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayContractAmount4( double p_dayRepayContractAmount4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_contract_amount_4 = p_dayRepayContractAmount4;
    day_repay_contract_amount_4_is_set = true;
    day_repay_contract_amount_4_is_modified = true;
  }


  /** 
   * <em>day_repay_contract_amount_5</em>カラムの値を設定します。 
   *
   * @@param p_dayRepayContractAmount5 <em>day_repay_contract_amount_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayRepayContractAmount5( double p_dayRepayContractAmount5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_repay_contract_amount_5 = p_dayRepayContractAmount5;
    day_repay_contract_amount_5_is_set = true;
    day_repay_contract_amount_5_is_modified = true;
  }


  /** 
   * <em>margin_deposit_0</em>カラムの値を設定します。 
   *
   * @@param p_marginDeposit0 <em>margin_deposit_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginDeposit0( double p_marginDeposit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_0 = p_marginDeposit0;
    margin_deposit_0_is_set = true;
    margin_deposit_0_is_modified = true;
  }


  /** 
   * <em>margin_deposit_1</em>カラムの値を設定します。 
   *
   * @@param p_marginDeposit1 <em>margin_deposit_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginDeposit1( double p_marginDeposit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_1 = p_marginDeposit1;
    margin_deposit_1_is_set = true;
    margin_deposit_1_is_modified = true;
  }


  /** 
   * <em>margin_deposit_2</em>カラムの値を設定します。 
   *
   * @@param p_marginDeposit2 <em>margin_deposit_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginDeposit2( double p_marginDeposit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_2 = p_marginDeposit2;
    margin_deposit_2_is_set = true;
    margin_deposit_2_is_modified = true;
  }


  /** 
   * <em>margin_deposit_3</em>カラムの値を設定します。 
   *
   * @@param p_marginDeposit3 <em>margin_deposit_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginDeposit3( double p_marginDeposit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_3 = p_marginDeposit3;
    margin_deposit_3_is_set = true;
    margin_deposit_3_is_modified = true;
  }


  /** 
   * <em>margin_deposit_4</em>カラムの値を設定します。 
   *
   * @@param p_marginDeposit4 <em>margin_deposit_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginDeposit4( double p_marginDeposit4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_4 = p_marginDeposit4;
    margin_deposit_4_is_set = true;
    margin_deposit_4_is_modified = true;
  }


  /** 
   * <em>margin_deposit_5</em>カラムの値を設定します。 
   *
   * @@param p_marginDeposit5 <em>margin_deposit_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginDeposit5( double p_marginDeposit5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_5 = p_marginDeposit5;
    margin_deposit_5_is_set = true;
    margin_deposit_5_is_modified = true;
  }


  /** 
   * <em>cash_margin_deposit_0</em>カラムの値を設定します。 
   *
   * @@param p_cashMarginDeposit0 <em>cash_margin_deposit_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashMarginDeposit0( double p_cashMarginDeposit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_margin_deposit_0 = p_cashMarginDeposit0;
    cash_margin_deposit_0_is_set = true;
    cash_margin_deposit_0_is_modified = true;
  }


  /** 
   * <em>cash_margin_deposit_1</em>カラムの値を設定します。 
   *
   * @@param p_cashMarginDeposit1 <em>cash_margin_deposit_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashMarginDeposit1( double p_cashMarginDeposit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_margin_deposit_1 = p_cashMarginDeposit1;
    cash_margin_deposit_1_is_set = true;
    cash_margin_deposit_1_is_modified = true;
  }


  /** 
   * <em>cash_margin_deposit_2</em>カラムの値を設定します。 
   *
   * @@param p_cashMarginDeposit2 <em>cash_margin_deposit_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashMarginDeposit2( double p_cashMarginDeposit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_margin_deposit_2 = p_cashMarginDeposit2;
    cash_margin_deposit_2_is_set = true;
    cash_margin_deposit_2_is_modified = true;
  }


  /** 
   * <em>cash_margin_deposit_3</em>カラムの値を設定します。 
   *
   * @@param p_cashMarginDeposit3 <em>cash_margin_deposit_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashMarginDeposit3( double p_cashMarginDeposit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_margin_deposit_3 = p_cashMarginDeposit3;
    cash_margin_deposit_3_is_set = true;
    cash_margin_deposit_3_is_modified = true;
  }


  /** 
   * <em>cash_margin_deposit_4</em>カラムの値を設定します。 
   *
   * @@param p_cashMarginDeposit4 <em>cash_margin_deposit_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashMarginDeposit4( double p_cashMarginDeposit4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_margin_deposit_4 = p_cashMarginDeposit4;
    cash_margin_deposit_4_is_set = true;
    cash_margin_deposit_4_is_modified = true;
  }


  /** 
   * <em>cash_margin_deposit_5</em>カラムの値を設定します。 
   *
   * @@param p_cashMarginDeposit5 <em>cash_margin_deposit_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashMarginDeposit5( double p_cashMarginDeposit5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_margin_deposit_5 = p_cashMarginDeposit5;
    cash_margin_deposit_5_is_set = true;
    cash_margin_deposit_5_is_modified = true;
  }


  /** 
   * <em>contract_asset_profit_loss</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetProfitLoss <em>contract_asset_profit_loss</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetProfitLoss( double p_contractAssetProfitLoss )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_loss = p_contractAssetProfitLoss;
    contract_asset_profit_loss_is_set = true;
    contract_asset_profit_loss_is_modified = true;
  }


  /** 
   * <em>undeli_contract_amount_0</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractAmount0 <em>undeli_contract_amount_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliContractAmount0( double p_undeliContractAmount0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_amount_0 = p_undeliContractAmount0;
    undeli_contract_amount_0_is_set = true;
    undeli_contract_amount_0_is_modified = true;
  }


  /** 
   * <em>undeli_contract_amount_1</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractAmount1 <em>undeli_contract_amount_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliContractAmount1( double p_undeliContractAmount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_amount_1 = p_undeliContractAmount1;
    undeli_contract_amount_1_is_set = true;
    undeli_contract_amount_1_is_modified = true;
  }


  /** 
   * <em>undeli_contract_amount_2</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractAmount2 <em>undeli_contract_amount_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliContractAmount2( double p_undeliContractAmount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_amount_2 = p_undeliContractAmount2;
    undeli_contract_amount_2_is_set = true;
    undeli_contract_amount_2_is_modified = true;
  }


  /** 
   * <em>undeli_contract_amount_3</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractAmount3 <em>undeli_contract_amount_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliContractAmount3( double p_undeliContractAmount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_amount_3 = p_undeliContractAmount3;
    undeli_contract_amount_3_is_set = true;
    undeli_contract_amount_3_is_modified = true;
  }


  /** 
   * <em>undeli_margin_deposit_0</em>カラムの値を設定します。 
   *
   * @@param p_undeliMarginDeposit0 <em>undeli_margin_deposit_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliMarginDeposit0( double p_undeliMarginDeposit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_margin_deposit_0 = p_undeliMarginDeposit0;
    undeli_margin_deposit_0_is_set = true;
    undeli_margin_deposit_0_is_modified = true;
  }


  /** 
   * <em>undeli_margin_deposit_1</em>カラムの値を設定します。 
   *
   * @@param p_undeliMarginDeposit1 <em>undeli_margin_deposit_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliMarginDeposit1( double p_undeliMarginDeposit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_margin_deposit_1 = p_undeliMarginDeposit1;
    undeli_margin_deposit_1_is_set = true;
    undeli_margin_deposit_1_is_modified = true;
  }


  /** 
   * <em>undeli_margin_deposit_2</em>カラムの値を設定します。 
   *
   * @@param p_undeliMarginDeposit2 <em>undeli_margin_deposit_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliMarginDeposit2( double p_undeliMarginDeposit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_margin_deposit_2 = p_undeliMarginDeposit2;
    undeli_margin_deposit_2_is_set = true;
    undeli_margin_deposit_2_is_modified = true;
  }


  /** 
   * <em>undeli_margin_deposit_3</em>カラムの値を設定します。 
   *
   * @@param p_undeliMarginDeposit3 <em>undeli_margin_deposit_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliMarginDeposit3( double p_undeliMarginDeposit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_margin_deposit_3 = p_undeliMarginDeposit3;
    undeli_margin_deposit_3_is_set = true;
    undeli_margin_deposit_3_is_modified = true;
  }


  /** 
   * <em>undeli_cash_margin_deposit_0</em>カラムの値を設定します。 
   *
   * @@param p_undeliCashMarginDeposit0 <em>undeli_cash_margin_deposit_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliCashMarginDeposit0( double p_undeliCashMarginDeposit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_cash_margin_deposit_0 = p_undeliCashMarginDeposit0;
    undeli_cash_margin_deposit_0_is_set = true;
    undeli_cash_margin_deposit_0_is_modified = true;
  }


  /** 
   * <em>undeli_cash_margin_deposit_1</em>カラムの値を設定します。 
   *
   * @@param p_undeliCashMarginDeposit1 <em>undeli_cash_margin_deposit_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliCashMarginDeposit1( double p_undeliCashMarginDeposit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_cash_margin_deposit_1 = p_undeliCashMarginDeposit1;
    undeli_cash_margin_deposit_1_is_set = true;
    undeli_cash_margin_deposit_1_is_modified = true;
  }


  /** 
   * <em>undeli_cash_margin_deposit_2</em>カラムの値を設定します。 
   *
   * @@param p_undeliCashMarginDeposit2 <em>undeli_cash_margin_deposit_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliCashMarginDeposit2( double p_undeliCashMarginDeposit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_cash_margin_deposit_2 = p_undeliCashMarginDeposit2;
    undeli_cash_margin_deposit_2_is_set = true;
    undeli_cash_margin_deposit_2_is_modified = true;
  }


  /** 
   * <em>undeli_cash_margin_deposit_3</em>カラムの値を設定します。 
   *
   * @@param p_undeliCashMarginDeposit3 <em>undeli_cash_margin_deposit_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliCashMarginDeposit3( double p_undeliCashMarginDeposit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_cash_margin_deposit_3 = p_undeliCashMarginDeposit3;
    undeli_cash_margin_deposit_3_is_set = true;
    undeli_cash_margin_deposit_3_is_modified = true;
  }


  /** 
   * <em>undeli_contract_loss_0</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractLoss0 <em>undeli_contract_loss_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliContractLoss0( double p_undeliContractLoss0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_loss_0 = p_undeliContractLoss0;
    undeli_contract_loss_0_is_set = true;
    undeli_contract_loss_0_is_modified = true;
  }


  /** 
   * <em>undeli_contract_loss_1</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractLoss1 <em>undeli_contract_loss_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliContractLoss1( double p_undeliContractLoss1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_loss_1 = p_undeliContractLoss1;
    undeli_contract_loss_1_is_set = true;
    undeli_contract_loss_1_is_modified = true;
  }


  /** 
   * <em>undeli_contract_loss_2</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractLoss2 <em>undeli_contract_loss_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliContractLoss2( double p_undeliContractLoss2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_loss_2 = p_undeliContractLoss2;
    undeli_contract_loss_2_is_set = true;
    undeli_contract_loss_2_is_modified = true;
  }


  /** 
   * <em>undeli_contract_loss_3</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractLoss3 <em>undeli_contract_loss_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliContractLoss3( double p_undeliContractLoss3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_loss_3 = p_undeliContractLoss3;
    undeli_contract_loss_3_is_set = true;
    undeli_contract_loss_3_is_modified = true;
  }


  /** 
   * <em>undeli_contract_profit_0</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractProfit0 <em>undeli_contract_profit_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliContractProfit0( double p_undeliContractProfit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_profit_0 = p_undeliContractProfit0;
    undeli_contract_profit_0_is_set = true;
    undeli_contract_profit_0_is_modified = true;
  }


  /** 
   * <em>undeli_contract_profit_1</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractProfit1 <em>undeli_contract_profit_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliContractProfit1( double p_undeliContractProfit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_profit_1 = p_undeliContractProfit1;
    undeli_contract_profit_1_is_set = true;
    undeli_contract_profit_1_is_modified = true;
  }


  /** 
   * <em>undeli_contract_profit_2</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractProfit2 <em>undeli_contract_profit_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliContractProfit2( double p_undeliContractProfit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_profit_2 = p_undeliContractProfit2;
    undeli_contract_profit_2_is_set = true;
    undeli_contract_profit_2_is_modified = true;
  }


  /** 
   * <em>undeli_contract_profit_3</em>カラムの値を設定します。 
   *
   * @@param p_undeliContractProfit3 <em>undeli_contract_profit_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setUndeliContractProfit3( double p_undeliContractProfit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    undeli_contract_profit_3 = p_undeliContractProfit3;
    undeli_contract_profit_3_is_set = true;
    undeli_contract_profit_3_is_modified = true;
  }


  /** 
   * <em>contract_total_cost</em>カラムの値を設定します。 
   *
   * @@param p_contractTotalCost <em>contract_total_cost</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractTotalCost( double p_contractTotalCost )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_total_cost = p_contractTotalCost;
    contract_total_cost_is_set = true;
    contract_total_cost_is_modified = true;
  }


  /** 
   * <em>mark_to_market_div</em>カラムの値を設定します。 
   *
   * @@param p_markToMarketDiv <em>mark_to_market_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setMarkToMarketDiv( String p_markToMarketDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    mark_to_market_div = p_markToMarketDiv;
    mark_to_market_div_is_set = true;
    mark_to_market_div_is_modified = true;
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
   * <em>contract_asset_profit_loss_1</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetProfitLoss1 <em>contract_asset_profit_loss_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetProfitLoss1( double p_contractAssetProfitLoss1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_loss_1 = new Double(p_contractAssetProfitLoss1);
    contract_asset_profit_loss_1_is_set = true;
    contract_asset_profit_loss_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_profit_loss_1</em>カラムに値を設定します。 
   */
  public final void setContractAssetProfitLoss1( Double p_contractAssetProfitLoss1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_loss_1 = p_contractAssetProfitLoss1;
    contract_asset_profit_loss_1_is_set = true;
    contract_asset_profit_loss_1_is_modified = true;
  }


  /** 
   * <em>contract_asset_profit_loss_2</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetProfitLoss2 <em>contract_asset_profit_loss_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetProfitLoss2( double p_contractAssetProfitLoss2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_loss_2 = new Double(p_contractAssetProfitLoss2);
    contract_asset_profit_loss_2_is_set = true;
    contract_asset_profit_loss_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_profit_loss_2</em>カラムに値を設定します。 
   */
  public final void setContractAssetProfitLoss2( Double p_contractAssetProfitLoss2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_loss_2 = p_contractAssetProfitLoss2;
    contract_asset_profit_loss_2_is_set = true;
    contract_asset_profit_loss_2_is_modified = true;
  }


  /** 
   * <em>contract_asset_profit_loss_3</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetProfitLoss3 <em>contract_asset_profit_loss_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetProfitLoss3( double p_contractAssetProfitLoss3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_loss_3 = new Double(p_contractAssetProfitLoss3);
    contract_asset_profit_loss_3_is_set = true;
    contract_asset_profit_loss_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_profit_loss_3</em>カラムに値を設定します。 
   */
  public final void setContractAssetProfitLoss3( Double p_contractAssetProfitLoss3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_loss_3 = p_contractAssetProfitLoss3;
    contract_asset_profit_loss_3_is_set = true;
    contract_asset_profit_loss_3_is_modified = true;
  }


  /** 
   * <em>contract_asset_profit_loss_4</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetProfitLoss4 <em>contract_asset_profit_loss_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetProfitLoss4( double p_contractAssetProfitLoss4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_loss_4 = new Double(p_contractAssetProfitLoss4);
    contract_asset_profit_loss_4_is_set = true;
    contract_asset_profit_loss_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_profit_loss_4</em>カラムに値を設定します。 
   */
  public final void setContractAssetProfitLoss4( Double p_contractAssetProfitLoss4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_loss_4 = p_contractAssetProfitLoss4;
    contract_asset_profit_loss_4_is_set = true;
    contract_asset_profit_loss_4_is_modified = true;
  }


  /** 
   * <em>contract_asset_profit_loss_5</em>カラムの値を設定します。 
   *
   * @@param p_contractAssetProfitLoss5 <em>contract_asset_profit_loss_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractAssetProfitLoss5( double p_contractAssetProfitLoss5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_loss_5 = new Double(p_contractAssetProfitLoss5);
    contract_asset_profit_loss_5_is_set = true;
    contract_asset_profit_loss_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_asset_profit_loss_5</em>カラムに値を設定します。 
   */
  public final void setContractAssetProfitLoss5( Double p_contractAssetProfitLoss5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_asset_profit_loss_5 = p_contractAssetProfitLoss5;
    contract_asset_profit_loss_5_is_set = true;
    contract_asset_profit_loss_5_is_modified = true;
  }


  /** 
   * <em>day_trade_restraint_5</em>カラムの値を設定します。 
   *
   * @@param p_dayTradeRestraint5 <em>day_trade_restraint_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayTradeRestraint5( double p_dayTradeRestraint5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_5 = new Double(p_dayTradeRestraint5);
    day_trade_restraint_5_is_set = true;
    day_trade_restraint_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_trade_restraint_5</em>カラムに値を設定します。 
   */
  public final void setDayTradeRestraint5( Double p_dayTradeRestraint5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_5 = p_dayTradeRestraint5;
    day_trade_restraint_5_is_set = true;
    day_trade_restraint_5_is_modified = true;
  }


  /** 
   * <em>contract_total_cost_1</em>カラムの値を設定します。 
   *
   * @@param p_contractTotalCost1 <em>contract_total_cost_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractTotalCost1( double p_contractTotalCost1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_total_cost_1 = new Double(p_contractTotalCost1);
    contract_total_cost_1_is_set = true;
    contract_total_cost_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_total_cost_1</em>カラムに値を設定します。 
   */
  public final void setContractTotalCost1( Double p_contractTotalCost1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_total_cost_1 = p_contractTotalCost1;
    contract_total_cost_1_is_set = true;
    contract_total_cost_1_is_modified = true;
  }


  /** 
   * <em>contract_total_cost_2</em>カラムの値を設定します。 
   *
   * @@param p_contractTotalCost2 <em>contract_total_cost_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractTotalCost2( double p_contractTotalCost2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_total_cost_2 = new Double(p_contractTotalCost2);
    contract_total_cost_2_is_set = true;
    contract_total_cost_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_total_cost_2</em>カラムに値を設定します。 
   */
  public final void setContractTotalCost2( Double p_contractTotalCost2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_total_cost_2 = p_contractTotalCost2;
    contract_total_cost_2_is_set = true;
    contract_total_cost_2_is_modified = true;
  }


  /** 
   * <em>contract_total_cost_3</em>カラムの値を設定します。 
   *
   * @@param p_contractTotalCost3 <em>contract_total_cost_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractTotalCost3( double p_contractTotalCost3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_total_cost_3 = new Double(p_contractTotalCost3);
    contract_total_cost_3_is_set = true;
    contract_total_cost_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_total_cost_3</em>カラムに値を設定します。 
   */
  public final void setContractTotalCost3( Double p_contractTotalCost3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_total_cost_3 = p_contractTotalCost3;
    contract_total_cost_3_is_set = true;
    contract_total_cost_3_is_modified = true;
  }


  /** 
   * <em>contract_total_cost_4</em>カラムの値を設定します。 
   *
   * @@param p_contractTotalCost4 <em>contract_total_cost_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractTotalCost4( double p_contractTotalCost4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_total_cost_4 = new Double(p_contractTotalCost4);
    contract_total_cost_4_is_set = true;
    contract_total_cost_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_total_cost_4</em>カラムに値を設定します。 
   */
  public final void setContractTotalCost4( Double p_contractTotalCost4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_total_cost_4 = p_contractTotalCost4;
    contract_total_cost_4_is_set = true;
    contract_total_cost_4_is_modified = true;
  }


  /** 
   * <em>contract_total_cost_5</em>カラムの値を設定します。 
   *
   * @@param p_contractTotalCost5 <em>contract_total_cost_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setContractTotalCost5( double p_contractTotalCost5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_total_cost_5 = new Double(p_contractTotalCost5);
    contract_total_cost_5_is_set = true;
    contract_total_cost_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>contract_total_cost_5</em>カラムに値を設定します。 
   */
  public final void setContractTotalCost5( Double p_contractTotalCost5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    contract_total_cost_5 = p_contractTotalCost5;
    contract_total_cost_5_is_set = true;
    contract_total_cost_5_is_modified = true;
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
                else if ( name.equals("account_balance_0") ) {
                    return new Double( account_balance_0 );
                }
                else if ( name.equals("account_balance_1") ) {
                    return new Double( account_balance_1 );
                }
                else if ( name.equals("account_balance_2") ) {
                    return new Double( account_balance_2 );
                }
                else if ( name.equals("account_balance_3") ) {
                    return new Double( account_balance_3 );
                }
                else if ( name.equals("account_balance_4") ) {
                    return new Double( account_balance_4 );
                }
                else if ( name.equals("account_balance_5") ) {
                    return new Double( account_balance_5 );
                }
                break;
            case 'c':
                if ( name.equals("calc_result_margin_id") ) {
                    return new Long( calc_result_margin_id );
                }
                else if ( name.equals("contract_amount_0") ) {
                    return new Double( contract_amount_0 );
                }
                else if ( name.equals("contract_amount_1") ) {
                    return new Double( contract_amount_1 );
                }
                else if ( name.equals("contract_amount_2") ) {
                    return new Double( contract_amount_2 );
                }
                else if ( name.equals("contract_amount_3") ) {
                    return new Double( contract_amount_3 );
                }
                else if ( name.equals("contract_amount_4") ) {
                    return new Double( contract_amount_4 );
                }
                else if ( name.equals("contract_amount_5") ) {
                    return new Double( contract_amount_5 );
                }
                else if ( name.equals("cash_margin_deposit_0") ) {
                    return new Double( cash_margin_deposit_0 );
                }
                else if ( name.equals("cash_margin_deposit_1") ) {
                    return new Double( cash_margin_deposit_1 );
                }
                else if ( name.equals("cash_margin_deposit_2") ) {
                    return new Double( cash_margin_deposit_2 );
                }
                else if ( name.equals("cash_margin_deposit_3") ) {
                    return new Double( cash_margin_deposit_3 );
                }
                else if ( name.equals("cash_margin_deposit_4") ) {
                    return new Double( cash_margin_deposit_4 );
                }
                else if ( name.equals("cash_margin_deposit_5") ) {
                    return new Double( cash_margin_deposit_5 );
                }
                else if ( name.equals("contract_asset_profit_loss") ) {
                    return new Double( contract_asset_profit_loss );
                }
                else if ( name.equals("contract_total_cost") ) {
                    return new Double( contract_total_cost );
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("contract_asset_profit_loss_1") ) {
                    return this.contract_asset_profit_loss_1;
                }
                else if ( name.equals("contract_asset_profit_loss_2") ) {
                    return this.contract_asset_profit_loss_2;
                }
                else if ( name.equals("contract_asset_profit_loss_3") ) {
                    return this.contract_asset_profit_loss_3;
                }
                else if ( name.equals("contract_asset_profit_loss_4") ) {
                    return this.contract_asset_profit_loss_4;
                }
                else if ( name.equals("contract_asset_profit_loss_5") ) {
                    return this.contract_asset_profit_loss_5;
                }
                else if ( name.equals("contract_total_cost_1") ) {
                    return this.contract_total_cost_1;
                }
                else if ( name.equals("contract_total_cost_2") ) {
                    return this.contract_total_cost_2;
                }
                else if ( name.equals("contract_total_cost_3") ) {
                    return this.contract_total_cost_3;
                }
                else if ( name.equals("contract_total_cost_4") ) {
                    return this.contract_total_cost_4;
                }
                else if ( name.equals("contract_total_cost_5") ) {
                    return this.contract_total_cost_5;
                }
                break;
            case 'd':
                if ( name.equals("day_trade_restraint_0") ) {
                    return new Double( day_trade_restraint_0 );
                }
                else if ( name.equals("day_trade_restraint_1") ) {
                    return new Double( day_trade_restraint_1 );
                }
                else if ( name.equals("day_trade_restraint_2") ) {
                    return new Double( day_trade_restraint_2 );
                }
                else if ( name.equals("day_trade_restraint_3") ) {
                    return new Double( day_trade_restraint_3 );
                }
                else if ( name.equals("day_trade_restraint_4") ) {
                    return new Double( day_trade_restraint_4 );
                }
                else if ( name.equals("day_repay_contract_amount_0") ) {
                    return new Double( day_repay_contract_amount_0 );
                }
                else if ( name.equals("day_repay_contract_amount_1") ) {
                    return new Double( day_repay_contract_amount_1 );
                }
                else if ( name.equals("day_repay_contract_amount_2") ) {
                    return new Double( day_repay_contract_amount_2 );
                }
                else if ( name.equals("day_repay_contract_amount_3") ) {
                    return new Double( day_repay_contract_amount_3 );
                }
                else if ( name.equals("day_repay_contract_amount_4") ) {
                    return new Double( day_repay_contract_amount_4 );
                }
                else if ( name.equals("day_repay_contract_amount_5") ) {
                    return new Double( day_repay_contract_amount_5 );
                }
                else if ( name.equals("day_trade_restraint_5") ) {
                    return this.day_trade_restraint_5;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("margin_deposit_0") ) {
                    return new Double( margin_deposit_0 );
                }
                else if ( name.equals("margin_deposit_1") ) {
                    return new Double( margin_deposit_1 );
                }
                else if ( name.equals("margin_deposit_2") ) {
                    return new Double( margin_deposit_2 );
                }
                else if ( name.equals("margin_deposit_3") ) {
                    return new Double( margin_deposit_3 );
                }
                else if ( name.equals("margin_deposit_4") ) {
                    return new Double( margin_deposit_4 );
                }
                else if ( name.equals("margin_deposit_5") ) {
                    return new Double( margin_deposit_5 );
                }
                else if ( name.equals("mark_to_market_div") ) {
                    return this.mark_to_market_div;
                }
                break;
            case 'o':
                if ( name.equals("other_restraint_0") ) {
                    return new Double( other_restraint_0 );
                }
                else if ( name.equals("other_restraint_1") ) {
                    return new Double( other_restraint_1 );
                }
                else if ( name.equals("other_restraint_2") ) {
                    return new Double( other_restraint_2 );
                }
                else if ( name.equals("other_restraint_3") ) {
                    return new Double( other_restraint_3 );
                }
                else if ( name.equals("other_restraint_4") ) {
                    return new Double( other_restraint_4 );
                }
                else if ( name.equals("other_restraint_5") ) {
                    return new Double( other_restraint_5 );
                }
                break;
            case 's':
                if ( name.equals("substitute_security_asset_0") ) {
                    return new Double( substitute_security_asset_0 );
                }
                else if ( name.equals("substitute_security_asset_1") ) {
                    return new Double( substitute_security_asset_1 );
                }
                else if ( name.equals("substitute_security_asset_2") ) {
                    return new Double( substitute_security_asset_2 );
                }
                else if ( name.equals("substitute_security_asset_3") ) {
                    return new Double( substitute_security_asset_3 );
                }
                else if ( name.equals("substitute_security_asset_4") ) {
                    return new Double( substitute_security_asset_4 );
                }
                else if ( name.equals("substitute_security_asset_5") ) {
                    return new Double( substitute_security_asset_5 );
                }
                break;
            case 't':
                if ( name.equals("today_executed_amount_0") ) {
                    return new Double( today_executed_amount_0 );
                }
                else if ( name.equals("today_executed_amount_1") ) {
                    return new Double( today_executed_amount_1 );
                }
                else if ( name.equals("today_executed_amount_2") ) {
                    return new Double( today_executed_amount_2 );
                }
                else if ( name.equals("today_executed_amount_3") ) {
                    return new Double( today_executed_amount_3 );
                }
                else if ( name.equals("today_executed_amount_4") ) {
                    return new Double( today_executed_amount_4 );
                }
                else if ( name.equals("today_executed_amount_5") ) {
                    return new Double( today_executed_amount_5 );
                }
                else if ( name.equals("today_unexecuted_amount_0") ) {
                    return new Double( today_unexecuted_amount_0 );
                }
                else if ( name.equals("today_unexecuted_amount_1") ) {
                    return new Double( today_unexecuted_amount_1 );
                }
                else if ( name.equals("today_unexecuted_amount_2") ) {
                    return new Double( today_unexecuted_amount_2 );
                }
                else if ( name.equals("today_unexecuted_amount_3") ) {
                    return new Double( today_unexecuted_amount_3 );
                }
                else if ( name.equals("today_unexecuted_amount_4") ) {
                    return new Double( today_unexecuted_amount_4 );
                }
                else if ( name.equals("today_unexecuted_amount_5") ) {
                    return new Double( today_unexecuted_amount_5 );
                }
                break;
            case 'u':
                if ( name.equals("undeli_contract_amount_0") ) {
                    return new Double( undeli_contract_amount_0 );
                }
                else if ( name.equals("undeli_contract_amount_1") ) {
                    return new Double( undeli_contract_amount_1 );
                }
                else if ( name.equals("undeli_contract_amount_2") ) {
                    return new Double( undeli_contract_amount_2 );
                }
                else if ( name.equals("undeli_contract_amount_3") ) {
                    return new Double( undeli_contract_amount_3 );
                }
                else if ( name.equals("undeli_margin_deposit_0") ) {
                    return new Double( undeli_margin_deposit_0 );
                }
                else if ( name.equals("undeli_margin_deposit_1") ) {
                    return new Double( undeli_margin_deposit_1 );
                }
                else if ( name.equals("undeli_margin_deposit_2") ) {
                    return new Double( undeli_margin_deposit_2 );
                }
                else if ( name.equals("undeli_margin_deposit_3") ) {
                    return new Double( undeli_margin_deposit_3 );
                }
                else if ( name.equals("undeli_cash_margin_deposit_0") ) {
                    return new Double( undeli_cash_margin_deposit_0 );
                }
                else if ( name.equals("undeli_cash_margin_deposit_1") ) {
                    return new Double( undeli_cash_margin_deposit_1 );
                }
                else if ( name.equals("undeli_cash_margin_deposit_2") ) {
                    return new Double( undeli_cash_margin_deposit_2 );
                }
                else if ( name.equals("undeli_cash_margin_deposit_3") ) {
                    return new Double( undeli_cash_margin_deposit_3 );
                }
                else if ( name.equals("undeli_contract_loss_0") ) {
                    return new Double( undeli_contract_loss_0 );
                }
                else if ( name.equals("undeli_contract_loss_1") ) {
                    return new Double( undeli_contract_loss_1 );
                }
                else if ( name.equals("undeli_contract_loss_2") ) {
                    return new Double( undeli_contract_loss_2 );
                }
                else if ( name.equals("undeli_contract_loss_3") ) {
                    return new Double( undeli_contract_loss_3 );
                }
                else if ( name.equals("undeli_contract_profit_0") ) {
                    return new Double( undeli_contract_profit_0 );
                }
                else if ( name.equals("undeli_contract_profit_1") ) {
                    return new Double( undeli_contract_profit_1 );
                }
                else if ( name.equals("undeli_contract_profit_2") ) {
                    return new Double( undeli_contract_profit_2 );
                }
                else if ( name.equals("undeli_contract_profit_3") ) {
                    return new Double( undeli_contract_profit_3 );
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
                else if ( name.equals("account_balance_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_balance_0' must be Double: '"+value+"' is not." );
                    this.account_balance_0 = ((Double) value).doubleValue();
                    if (this.account_balance_0_is_set)
                        this.account_balance_0_is_modified = true;
                    this.account_balance_0_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_balance_1' must be Double: '"+value+"' is not." );
                    this.account_balance_1 = ((Double) value).doubleValue();
                    if (this.account_balance_1_is_set)
                        this.account_balance_1_is_modified = true;
                    this.account_balance_1_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_balance_2' must be Double: '"+value+"' is not." );
                    this.account_balance_2 = ((Double) value).doubleValue();
                    if (this.account_balance_2_is_set)
                        this.account_balance_2_is_modified = true;
                    this.account_balance_2_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_balance_3' must be Double: '"+value+"' is not." );
                    this.account_balance_3 = ((Double) value).doubleValue();
                    if (this.account_balance_3_is_set)
                        this.account_balance_3_is_modified = true;
                    this.account_balance_3_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_balance_4' must be Double: '"+value+"' is not." );
                    this.account_balance_4 = ((Double) value).doubleValue();
                    if (this.account_balance_4_is_set)
                        this.account_balance_4_is_modified = true;
                    this.account_balance_4_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_balance_5' must be Double: '"+value+"' is not." );
                    this.account_balance_5 = ((Double) value).doubleValue();
                    if (this.account_balance_5_is_set)
                        this.account_balance_5_is_modified = true;
                    this.account_balance_5_is_set = true;
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
                else if ( name.equals("contract_amount_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_amount_0' must be Double: '"+value+"' is not." );
                    this.contract_amount_0 = ((Double) value).doubleValue();
                    if (this.contract_amount_0_is_set)
                        this.contract_amount_0_is_modified = true;
                    this.contract_amount_0_is_set = true;
                    return;
                }
                else if ( name.equals("contract_amount_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_amount_1' must be Double: '"+value+"' is not." );
                    this.contract_amount_1 = ((Double) value).doubleValue();
                    if (this.contract_amount_1_is_set)
                        this.contract_amount_1_is_modified = true;
                    this.contract_amount_1_is_set = true;
                    return;
                }
                else if ( name.equals("contract_amount_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_amount_2' must be Double: '"+value+"' is not." );
                    this.contract_amount_2 = ((Double) value).doubleValue();
                    if (this.contract_amount_2_is_set)
                        this.contract_amount_2_is_modified = true;
                    this.contract_amount_2_is_set = true;
                    return;
                }
                else if ( name.equals("contract_amount_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_amount_3' must be Double: '"+value+"' is not." );
                    this.contract_amount_3 = ((Double) value).doubleValue();
                    if (this.contract_amount_3_is_set)
                        this.contract_amount_3_is_modified = true;
                    this.contract_amount_3_is_set = true;
                    return;
                }
                else if ( name.equals("contract_amount_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_amount_4' must be Double: '"+value+"' is not." );
                    this.contract_amount_4 = ((Double) value).doubleValue();
                    if (this.contract_amount_4_is_set)
                        this.contract_amount_4_is_modified = true;
                    this.contract_amount_4_is_set = true;
                    return;
                }
                else if ( name.equals("contract_amount_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_amount_5' must be Double: '"+value+"' is not." );
                    this.contract_amount_5 = ((Double) value).doubleValue();
                    if (this.contract_amount_5_is_set)
                        this.contract_amount_5_is_modified = true;
                    this.contract_amount_5_is_set = true;
                    return;
                }
                else if ( name.equals("cash_margin_deposit_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_margin_deposit_0' must be Double: '"+value+"' is not." );
                    this.cash_margin_deposit_0 = ((Double) value).doubleValue();
                    if (this.cash_margin_deposit_0_is_set)
                        this.cash_margin_deposit_0_is_modified = true;
                    this.cash_margin_deposit_0_is_set = true;
                    return;
                }
                else if ( name.equals("cash_margin_deposit_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_margin_deposit_1' must be Double: '"+value+"' is not." );
                    this.cash_margin_deposit_1 = ((Double) value).doubleValue();
                    if (this.cash_margin_deposit_1_is_set)
                        this.cash_margin_deposit_1_is_modified = true;
                    this.cash_margin_deposit_1_is_set = true;
                    return;
                }
                else if ( name.equals("cash_margin_deposit_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_margin_deposit_2' must be Double: '"+value+"' is not." );
                    this.cash_margin_deposit_2 = ((Double) value).doubleValue();
                    if (this.cash_margin_deposit_2_is_set)
                        this.cash_margin_deposit_2_is_modified = true;
                    this.cash_margin_deposit_2_is_set = true;
                    return;
                }
                else if ( name.equals("cash_margin_deposit_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_margin_deposit_3' must be Double: '"+value+"' is not." );
                    this.cash_margin_deposit_3 = ((Double) value).doubleValue();
                    if (this.cash_margin_deposit_3_is_set)
                        this.cash_margin_deposit_3_is_modified = true;
                    this.cash_margin_deposit_3_is_set = true;
                    return;
                }
                else if ( name.equals("cash_margin_deposit_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_margin_deposit_4' must be Double: '"+value+"' is not." );
                    this.cash_margin_deposit_4 = ((Double) value).doubleValue();
                    if (this.cash_margin_deposit_4_is_set)
                        this.cash_margin_deposit_4_is_modified = true;
                    this.cash_margin_deposit_4_is_set = true;
                    return;
                }
                else if ( name.equals("cash_margin_deposit_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_margin_deposit_5' must be Double: '"+value+"' is not." );
                    this.cash_margin_deposit_5 = ((Double) value).doubleValue();
                    if (this.cash_margin_deposit_5_is_set)
                        this.cash_margin_deposit_5_is_modified = true;
                    this.cash_margin_deposit_5_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_profit_loss") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_profit_loss' must be Double: '"+value+"' is not." );
                    this.contract_asset_profit_loss = ((Double) value).doubleValue();
                    if (this.contract_asset_profit_loss_is_set)
                        this.contract_asset_profit_loss_is_modified = true;
                    this.contract_asset_profit_loss_is_set = true;
                    return;
                }
                else if ( name.equals("contract_total_cost") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_total_cost' must be Double: '"+value+"' is not." );
                    this.contract_total_cost = ((Double) value).doubleValue();
                    if (this.contract_total_cost_is_set)
                        this.contract_total_cost_is_modified = true;
                    this.contract_total_cost_is_set = true;
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
                else if ( name.equals("contract_asset_profit_loss_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_profit_loss_1' must be Double: '"+value+"' is not." );
                    this.contract_asset_profit_loss_1 = (Double) value;
                    if (this.contract_asset_profit_loss_1_is_set)
                        this.contract_asset_profit_loss_1_is_modified = true;
                    this.contract_asset_profit_loss_1_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_profit_loss_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_profit_loss_2' must be Double: '"+value+"' is not." );
                    this.contract_asset_profit_loss_2 = (Double) value;
                    if (this.contract_asset_profit_loss_2_is_set)
                        this.contract_asset_profit_loss_2_is_modified = true;
                    this.contract_asset_profit_loss_2_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_profit_loss_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_profit_loss_3' must be Double: '"+value+"' is not." );
                    this.contract_asset_profit_loss_3 = (Double) value;
                    if (this.contract_asset_profit_loss_3_is_set)
                        this.contract_asset_profit_loss_3_is_modified = true;
                    this.contract_asset_profit_loss_3_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_profit_loss_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_profit_loss_4' must be Double: '"+value+"' is not." );
                    this.contract_asset_profit_loss_4 = (Double) value;
                    if (this.contract_asset_profit_loss_4_is_set)
                        this.contract_asset_profit_loss_4_is_modified = true;
                    this.contract_asset_profit_loss_4_is_set = true;
                    return;
                }
                else if ( name.equals("contract_asset_profit_loss_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_asset_profit_loss_5' must be Double: '"+value+"' is not." );
                    this.contract_asset_profit_loss_5 = (Double) value;
                    if (this.contract_asset_profit_loss_5_is_set)
                        this.contract_asset_profit_loss_5_is_modified = true;
                    this.contract_asset_profit_loss_5_is_set = true;
                    return;
                }
                else if ( name.equals("contract_total_cost_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_total_cost_1' must be Double: '"+value+"' is not." );
                    this.contract_total_cost_1 = (Double) value;
                    if (this.contract_total_cost_1_is_set)
                        this.contract_total_cost_1_is_modified = true;
                    this.contract_total_cost_1_is_set = true;
                    return;
                }
                else if ( name.equals("contract_total_cost_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_total_cost_2' must be Double: '"+value+"' is not." );
                    this.contract_total_cost_2 = (Double) value;
                    if (this.contract_total_cost_2_is_set)
                        this.contract_total_cost_2_is_modified = true;
                    this.contract_total_cost_2_is_set = true;
                    return;
                }
                else if ( name.equals("contract_total_cost_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_total_cost_3' must be Double: '"+value+"' is not." );
                    this.contract_total_cost_3 = (Double) value;
                    if (this.contract_total_cost_3_is_set)
                        this.contract_total_cost_3_is_modified = true;
                    this.contract_total_cost_3_is_set = true;
                    return;
                }
                else if ( name.equals("contract_total_cost_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_total_cost_4' must be Double: '"+value+"' is not." );
                    this.contract_total_cost_4 = (Double) value;
                    if (this.contract_total_cost_4_is_set)
                        this.contract_total_cost_4_is_modified = true;
                    this.contract_total_cost_4_is_set = true;
                    return;
                }
                else if ( name.equals("contract_total_cost_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'contract_total_cost_5' must be Double: '"+value+"' is not." );
                    this.contract_total_cost_5 = (Double) value;
                    if (this.contract_total_cost_5_is_set)
                        this.contract_total_cost_5_is_modified = true;
                    this.contract_total_cost_5_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("day_trade_restraint_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_trade_restraint_0' must be Double: '"+value+"' is not." );
                    this.day_trade_restraint_0 = ((Double) value).doubleValue();
                    if (this.day_trade_restraint_0_is_set)
                        this.day_trade_restraint_0_is_modified = true;
                    this.day_trade_restraint_0_is_set = true;
                    return;
                }
                else if ( name.equals("day_trade_restraint_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_trade_restraint_1' must be Double: '"+value+"' is not." );
                    this.day_trade_restraint_1 = ((Double) value).doubleValue();
                    if (this.day_trade_restraint_1_is_set)
                        this.day_trade_restraint_1_is_modified = true;
                    this.day_trade_restraint_1_is_set = true;
                    return;
                }
                else if ( name.equals("day_trade_restraint_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_trade_restraint_2' must be Double: '"+value+"' is not." );
                    this.day_trade_restraint_2 = ((Double) value).doubleValue();
                    if (this.day_trade_restraint_2_is_set)
                        this.day_trade_restraint_2_is_modified = true;
                    this.day_trade_restraint_2_is_set = true;
                    return;
                }
                else if ( name.equals("day_trade_restraint_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_trade_restraint_3' must be Double: '"+value+"' is not." );
                    this.day_trade_restraint_3 = ((Double) value).doubleValue();
                    if (this.day_trade_restraint_3_is_set)
                        this.day_trade_restraint_3_is_modified = true;
                    this.day_trade_restraint_3_is_set = true;
                    return;
                }
                else if ( name.equals("day_trade_restraint_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_trade_restraint_4' must be Double: '"+value+"' is not." );
                    this.day_trade_restraint_4 = ((Double) value).doubleValue();
                    if (this.day_trade_restraint_4_is_set)
                        this.day_trade_restraint_4_is_modified = true;
                    this.day_trade_restraint_4_is_set = true;
                    return;
                }
                else if ( name.equals("day_repay_contract_amount_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_contract_amount_0' must be Double: '"+value+"' is not." );
                    this.day_repay_contract_amount_0 = ((Double) value).doubleValue();
                    if (this.day_repay_contract_amount_0_is_set)
                        this.day_repay_contract_amount_0_is_modified = true;
                    this.day_repay_contract_amount_0_is_set = true;
                    return;
                }
                else if ( name.equals("day_repay_contract_amount_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_contract_amount_1' must be Double: '"+value+"' is not." );
                    this.day_repay_contract_amount_1 = ((Double) value).doubleValue();
                    if (this.day_repay_contract_amount_1_is_set)
                        this.day_repay_contract_amount_1_is_modified = true;
                    this.day_repay_contract_amount_1_is_set = true;
                    return;
                }
                else if ( name.equals("day_repay_contract_amount_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_contract_amount_2' must be Double: '"+value+"' is not." );
                    this.day_repay_contract_amount_2 = ((Double) value).doubleValue();
                    if (this.day_repay_contract_amount_2_is_set)
                        this.day_repay_contract_amount_2_is_modified = true;
                    this.day_repay_contract_amount_2_is_set = true;
                    return;
                }
                else if ( name.equals("day_repay_contract_amount_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_contract_amount_3' must be Double: '"+value+"' is not." );
                    this.day_repay_contract_amount_3 = ((Double) value).doubleValue();
                    if (this.day_repay_contract_amount_3_is_set)
                        this.day_repay_contract_amount_3_is_modified = true;
                    this.day_repay_contract_amount_3_is_set = true;
                    return;
                }
                else if ( name.equals("day_repay_contract_amount_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_contract_amount_4' must be Double: '"+value+"' is not." );
                    this.day_repay_contract_amount_4 = ((Double) value).doubleValue();
                    if (this.day_repay_contract_amount_4_is_set)
                        this.day_repay_contract_amount_4_is_modified = true;
                    this.day_repay_contract_amount_4_is_set = true;
                    return;
                }
                else if ( name.equals("day_repay_contract_amount_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_repay_contract_amount_5' must be Double: '"+value+"' is not." );
                    this.day_repay_contract_amount_5 = ((Double) value).doubleValue();
                    if (this.day_repay_contract_amount_5_is_set)
                        this.day_repay_contract_amount_5_is_modified = true;
                    this.day_repay_contract_amount_5_is_set = true;
                    return;
                }
                else if ( name.equals("day_trade_restraint_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_trade_restraint_5' must be Double: '"+value+"' is not." );
                    this.day_trade_restraint_5 = (Double) value;
                    if (this.day_trade_restraint_5_is_set)
                        this.day_trade_restraint_5_is_modified = true;
                    this.day_trade_restraint_5_is_set = true;
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
                if ( name.equals("margin_deposit_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_0' must be Double: '"+value+"' is not." );
                    this.margin_deposit_0 = ((Double) value).doubleValue();
                    if (this.margin_deposit_0_is_set)
                        this.margin_deposit_0_is_modified = true;
                    this.margin_deposit_0_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_1' must be Double: '"+value+"' is not." );
                    this.margin_deposit_1 = ((Double) value).doubleValue();
                    if (this.margin_deposit_1_is_set)
                        this.margin_deposit_1_is_modified = true;
                    this.margin_deposit_1_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_2' must be Double: '"+value+"' is not." );
                    this.margin_deposit_2 = ((Double) value).doubleValue();
                    if (this.margin_deposit_2_is_set)
                        this.margin_deposit_2_is_modified = true;
                    this.margin_deposit_2_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_3' must be Double: '"+value+"' is not." );
                    this.margin_deposit_3 = ((Double) value).doubleValue();
                    if (this.margin_deposit_3_is_set)
                        this.margin_deposit_3_is_modified = true;
                    this.margin_deposit_3_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_4' must be Double: '"+value+"' is not." );
                    this.margin_deposit_4 = ((Double) value).doubleValue();
                    if (this.margin_deposit_4_is_set)
                        this.margin_deposit_4_is_modified = true;
                    this.margin_deposit_4_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_5' must be Double: '"+value+"' is not." );
                    this.margin_deposit_5 = ((Double) value).doubleValue();
                    if (this.margin_deposit_5_is_set)
                        this.margin_deposit_5_is_modified = true;
                    this.margin_deposit_5_is_set = true;
                    return;
                }
                else if ( name.equals("mark_to_market_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mark_to_market_div' must be String: '"+value+"' is not." );
                    this.mark_to_market_div = (String) value;
                    if (this.mark_to_market_div_is_set)
                        this.mark_to_market_div_is_modified = true;
                    this.mark_to_market_div_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("other_restraint_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'other_restraint_0' must be Double: '"+value+"' is not." );
                    this.other_restraint_0 = ((Double) value).doubleValue();
                    if (this.other_restraint_0_is_set)
                        this.other_restraint_0_is_modified = true;
                    this.other_restraint_0_is_set = true;
                    return;
                }
                else if ( name.equals("other_restraint_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'other_restraint_1' must be Double: '"+value+"' is not." );
                    this.other_restraint_1 = ((Double) value).doubleValue();
                    if (this.other_restraint_1_is_set)
                        this.other_restraint_1_is_modified = true;
                    this.other_restraint_1_is_set = true;
                    return;
                }
                else if ( name.equals("other_restraint_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'other_restraint_2' must be Double: '"+value+"' is not." );
                    this.other_restraint_2 = ((Double) value).doubleValue();
                    if (this.other_restraint_2_is_set)
                        this.other_restraint_2_is_modified = true;
                    this.other_restraint_2_is_set = true;
                    return;
                }
                else if ( name.equals("other_restraint_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'other_restraint_3' must be Double: '"+value+"' is not." );
                    this.other_restraint_3 = ((Double) value).doubleValue();
                    if (this.other_restraint_3_is_set)
                        this.other_restraint_3_is_modified = true;
                    this.other_restraint_3_is_set = true;
                    return;
                }
                else if ( name.equals("other_restraint_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'other_restraint_4' must be Double: '"+value+"' is not." );
                    this.other_restraint_4 = ((Double) value).doubleValue();
                    if (this.other_restraint_4_is_set)
                        this.other_restraint_4_is_modified = true;
                    this.other_restraint_4_is_set = true;
                    return;
                }
                else if ( name.equals("other_restraint_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'other_restraint_5' must be Double: '"+value+"' is not." );
                    this.other_restraint_5 = ((Double) value).doubleValue();
                    if (this.other_restraint_5_is_set)
                        this.other_restraint_5_is_modified = true;
                    this.other_restraint_5_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("substitute_security_asset_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'substitute_security_asset_0' must be Double: '"+value+"' is not." );
                    this.substitute_security_asset_0 = ((Double) value).doubleValue();
                    if (this.substitute_security_asset_0_is_set)
                        this.substitute_security_asset_0_is_modified = true;
                    this.substitute_security_asset_0_is_set = true;
                    return;
                }
                else if ( name.equals("substitute_security_asset_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'substitute_security_asset_1' must be Double: '"+value+"' is not." );
                    this.substitute_security_asset_1 = ((Double) value).doubleValue();
                    if (this.substitute_security_asset_1_is_set)
                        this.substitute_security_asset_1_is_modified = true;
                    this.substitute_security_asset_1_is_set = true;
                    return;
                }
                else if ( name.equals("substitute_security_asset_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'substitute_security_asset_2' must be Double: '"+value+"' is not." );
                    this.substitute_security_asset_2 = ((Double) value).doubleValue();
                    if (this.substitute_security_asset_2_is_set)
                        this.substitute_security_asset_2_is_modified = true;
                    this.substitute_security_asset_2_is_set = true;
                    return;
                }
                else if ( name.equals("substitute_security_asset_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'substitute_security_asset_3' must be Double: '"+value+"' is not." );
                    this.substitute_security_asset_3 = ((Double) value).doubleValue();
                    if (this.substitute_security_asset_3_is_set)
                        this.substitute_security_asset_3_is_modified = true;
                    this.substitute_security_asset_3_is_set = true;
                    return;
                }
                else if ( name.equals("substitute_security_asset_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'substitute_security_asset_4' must be Double: '"+value+"' is not." );
                    this.substitute_security_asset_4 = ((Double) value).doubleValue();
                    if (this.substitute_security_asset_4_is_set)
                        this.substitute_security_asset_4_is_modified = true;
                    this.substitute_security_asset_4_is_set = true;
                    return;
                }
                else if ( name.equals("substitute_security_asset_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'substitute_security_asset_5' must be Double: '"+value+"' is not." );
                    this.substitute_security_asset_5 = ((Double) value).doubleValue();
                    if (this.substitute_security_asset_5_is_set)
                        this.substitute_security_asset_5_is_modified = true;
                    this.substitute_security_asset_5_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("today_executed_amount_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_executed_amount_0' must be Double: '"+value+"' is not." );
                    this.today_executed_amount_0 = ((Double) value).doubleValue();
                    if (this.today_executed_amount_0_is_set)
                        this.today_executed_amount_0_is_modified = true;
                    this.today_executed_amount_0_is_set = true;
                    return;
                }
                else if ( name.equals("today_executed_amount_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_executed_amount_1' must be Double: '"+value+"' is not." );
                    this.today_executed_amount_1 = ((Double) value).doubleValue();
                    if (this.today_executed_amount_1_is_set)
                        this.today_executed_amount_1_is_modified = true;
                    this.today_executed_amount_1_is_set = true;
                    return;
                }
                else if ( name.equals("today_executed_amount_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_executed_amount_2' must be Double: '"+value+"' is not." );
                    this.today_executed_amount_2 = ((Double) value).doubleValue();
                    if (this.today_executed_amount_2_is_set)
                        this.today_executed_amount_2_is_modified = true;
                    this.today_executed_amount_2_is_set = true;
                    return;
                }
                else if ( name.equals("today_executed_amount_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_executed_amount_3' must be Double: '"+value+"' is not." );
                    this.today_executed_amount_3 = ((Double) value).doubleValue();
                    if (this.today_executed_amount_3_is_set)
                        this.today_executed_amount_3_is_modified = true;
                    this.today_executed_amount_3_is_set = true;
                    return;
                }
                else if ( name.equals("today_executed_amount_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_executed_amount_4' must be Double: '"+value+"' is not." );
                    this.today_executed_amount_4 = ((Double) value).doubleValue();
                    if (this.today_executed_amount_4_is_set)
                        this.today_executed_amount_4_is_modified = true;
                    this.today_executed_amount_4_is_set = true;
                    return;
                }
                else if ( name.equals("today_executed_amount_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_executed_amount_5' must be Double: '"+value+"' is not." );
                    this.today_executed_amount_5 = ((Double) value).doubleValue();
                    if (this.today_executed_amount_5_is_set)
                        this.today_executed_amount_5_is_modified = true;
                    this.today_executed_amount_5_is_set = true;
                    return;
                }
                else if ( name.equals("today_unexecuted_amount_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_unexecuted_amount_0' must be Double: '"+value+"' is not." );
                    this.today_unexecuted_amount_0 = ((Double) value).doubleValue();
                    if (this.today_unexecuted_amount_0_is_set)
                        this.today_unexecuted_amount_0_is_modified = true;
                    this.today_unexecuted_amount_0_is_set = true;
                    return;
                }
                else if ( name.equals("today_unexecuted_amount_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_unexecuted_amount_1' must be Double: '"+value+"' is not." );
                    this.today_unexecuted_amount_1 = ((Double) value).doubleValue();
                    if (this.today_unexecuted_amount_1_is_set)
                        this.today_unexecuted_amount_1_is_modified = true;
                    this.today_unexecuted_amount_1_is_set = true;
                    return;
                }
                else if ( name.equals("today_unexecuted_amount_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_unexecuted_amount_2' must be Double: '"+value+"' is not." );
                    this.today_unexecuted_amount_2 = ((Double) value).doubleValue();
                    if (this.today_unexecuted_amount_2_is_set)
                        this.today_unexecuted_amount_2_is_modified = true;
                    this.today_unexecuted_amount_2_is_set = true;
                    return;
                }
                else if ( name.equals("today_unexecuted_amount_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_unexecuted_amount_3' must be Double: '"+value+"' is not." );
                    this.today_unexecuted_amount_3 = ((Double) value).doubleValue();
                    if (this.today_unexecuted_amount_3_is_set)
                        this.today_unexecuted_amount_3_is_modified = true;
                    this.today_unexecuted_amount_3_is_set = true;
                    return;
                }
                else if ( name.equals("today_unexecuted_amount_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_unexecuted_amount_4' must be Double: '"+value+"' is not." );
                    this.today_unexecuted_amount_4 = ((Double) value).doubleValue();
                    if (this.today_unexecuted_amount_4_is_set)
                        this.today_unexecuted_amount_4_is_modified = true;
                    this.today_unexecuted_amount_4_is_set = true;
                    return;
                }
                else if ( name.equals("today_unexecuted_amount_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'today_unexecuted_amount_5' must be Double: '"+value+"' is not." );
                    this.today_unexecuted_amount_5 = ((Double) value).doubleValue();
                    if (this.today_unexecuted_amount_5_is_set)
                        this.today_unexecuted_amount_5_is_modified = true;
                    this.today_unexecuted_amount_5_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("undeli_contract_amount_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_amount_0' must be Double: '"+value+"' is not." );
                    this.undeli_contract_amount_0 = ((Double) value).doubleValue();
                    if (this.undeli_contract_amount_0_is_set)
                        this.undeli_contract_amount_0_is_modified = true;
                    this.undeli_contract_amount_0_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_amount_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_amount_1' must be Double: '"+value+"' is not." );
                    this.undeli_contract_amount_1 = ((Double) value).doubleValue();
                    if (this.undeli_contract_amount_1_is_set)
                        this.undeli_contract_amount_1_is_modified = true;
                    this.undeli_contract_amount_1_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_amount_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_amount_2' must be Double: '"+value+"' is not." );
                    this.undeli_contract_amount_2 = ((Double) value).doubleValue();
                    if (this.undeli_contract_amount_2_is_set)
                        this.undeli_contract_amount_2_is_modified = true;
                    this.undeli_contract_amount_2_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_amount_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_amount_3' must be Double: '"+value+"' is not." );
                    this.undeli_contract_amount_3 = ((Double) value).doubleValue();
                    if (this.undeli_contract_amount_3_is_set)
                        this.undeli_contract_amount_3_is_modified = true;
                    this.undeli_contract_amount_3_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_margin_deposit_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_margin_deposit_0' must be Double: '"+value+"' is not." );
                    this.undeli_margin_deposit_0 = ((Double) value).doubleValue();
                    if (this.undeli_margin_deposit_0_is_set)
                        this.undeli_margin_deposit_0_is_modified = true;
                    this.undeli_margin_deposit_0_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_margin_deposit_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_margin_deposit_1' must be Double: '"+value+"' is not." );
                    this.undeli_margin_deposit_1 = ((Double) value).doubleValue();
                    if (this.undeli_margin_deposit_1_is_set)
                        this.undeli_margin_deposit_1_is_modified = true;
                    this.undeli_margin_deposit_1_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_margin_deposit_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_margin_deposit_2' must be Double: '"+value+"' is not." );
                    this.undeli_margin_deposit_2 = ((Double) value).doubleValue();
                    if (this.undeli_margin_deposit_2_is_set)
                        this.undeli_margin_deposit_2_is_modified = true;
                    this.undeli_margin_deposit_2_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_margin_deposit_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_margin_deposit_3' must be Double: '"+value+"' is not." );
                    this.undeli_margin_deposit_3 = ((Double) value).doubleValue();
                    if (this.undeli_margin_deposit_3_is_set)
                        this.undeli_margin_deposit_3_is_modified = true;
                    this.undeli_margin_deposit_3_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_cash_margin_deposit_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_cash_margin_deposit_0' must be Double: '"+value+"' is not." );
                    this.undeli_cash_margin_deposit_0 = ((Double) value).doubleValue();
                    if (this.undeli_cash_margin_deposit_0_is_set)
                        this.undeli_cash_margin_deposit_0_is_modified = true;
                    this.undeli_cash_margin_deposit_0_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_cash_margin_deposit_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_cash_margin_deposit_1' must be Double: '"+value+"' is not." );
                    this.undeli_cash_margin_deposit_1 = ((Double) value).doubleValue();
                    if (this.undeli_cash_margin_deposit_1_is_set)
                        this.undeli_cash_margin_deposit_1_is_modified = true;
                    this.undeli_cash_margin_deposit_1_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_cash_margin_deposit_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_cash_margin_deposit_2' must be Double: '"+value+"' is not." );
                    this.undeli_cash_margin_deposit_2 = ((Double) value).doubleValue();
                    if (this.undeli_cash_margin_deposit_2_is_set)
                        this.undeli_cash_margin_deposit_2_is_modified = true;
                    this.undeli_cash_margin_deposit_2_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_cash_margin_deposit_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_cash_margin_deposit_3' must be Double: '"+value+"' is not." );
                    this.undeli_cash_margin_deposit_3 = ((Double) value).doubleValue();
                    if (this.undeli_cash_margin_deposit_3_is_set)
                        this.undeli_cash_margin_deposit_3_is_modified = true;
                    this.undeli_cash_margin_deposit_3_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_loss_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_loss_0' must be Double: '"+value+"' is not." );
                    this.undeli_contract_loss_0 = ((Double) value).doubleValue();
                    if (this.undeli_contract_loss_0_is_set)
                        this.undeli_contract_loss_0_is_modified = true;
                    this.undeli_contract_loss_0_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_loss_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_loss_1' must be Double: '"+value+"' is not." );
                    this.undeli_contract_loss_1 = ((Double) value).doubleValue();
                    if (this.undeli_contract_loss_1_is_set)
                        this.undeli_contract_loss_1_is_modified = true;
                    this.undeli_contract_loss_1_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_loss_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_loss_2' must be Double: '"+value+"' is not." );
                    this.undeli_contract_loss_2 = ((Double) value).doubleValue();
                    if (this.undeli_contract_loss_2_is_set)
                        this.undeli_contract_loss_2_is_modified = true;
                    this.undeli_contract_loss_2_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_loss_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_loss_3' must be Double: '"+value+"' is not." );
                    this.undeli_contract_loss_3 = ((Double) value).doubleValue();
                    if (this.undeli_contract_loss_3_is_set)
                        this.undeli_contract_loss_3_is_modified = true;
                    this.undeli_contract_loss_3_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_profit_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_profit_0' must be Double: '"+value+"' is not." );
                    this.undeli_contract_profit_0 = ((Double) value).doubleValue();
                    if (this.undeli_contract_profit_0_is_set)
                        this.undeli_contract_profit_0_is_modified = true;
                    this.undeli_contract_profit_0_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_profit_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_profit_1' must be Double: '"+value+"' is not." );
                    this.undeli_contract_profit_1 = ((Double) value).doubleValue();
                    if (this.undeli_contract_profit_1_is_set)
                        this.undeli_contract_profit_1_is_modified = true;
                    this.undeli_contract_profit_1_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_profit_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_profit_2' must be Double: '"+value+"' is not." );
                    this.undeli_contract_profit_2 = ((Double) value).doubleValue();
                    if (this.undeli_contract_profit_2_is_set)
                        this.undeli_contract_profit_2_is_modified = true;
                    this.undeli_contract_profit_2_is_set = true;
                    return;
                }
                else if ( name.equals("undeli_contract_profit_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'undeli_contract_profit_3' must be Double: '"+value+"' is not." );
                    this.undeli_contract_profit_3 = ((Double) value).doubleValue();
                    if (this.undeli_contract_profit_3_is_set)
                        this.undeli_contract_profit_3_is_modified = true;
                    this.undeli_contract_profit_3_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
