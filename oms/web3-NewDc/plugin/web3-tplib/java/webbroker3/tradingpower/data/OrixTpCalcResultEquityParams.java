head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.30.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	OrixTpCalcResultEquityParams.java;


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
 * orix_tp_calc_result_equityテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link OrixTpCalcResultEquityRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link OrixTpCalcResultEquityParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link OrixTpCalcResultEquityParams}が{@@link OrixTpCalcResultEquityRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see OrixTpCalcResultEquityPK 
 * @@see OrixTpCalcResultEquityRow 
 */
public class OrixTpCalcResultEquityParams extends Params implements OrixTpCalcResultEquityRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "orix_tp_calc_result_equity";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = OrixTpCalcResultEquityRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return OrixTpCalcResultEquityRow.TYPE;
  }


  /** 
   * <em>calc_result_equity_id</em>カラムの値 
   */
  public  long  calc_result_equity_id;

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
   * <em>trust_security_asset_3</em>カラムの値 
   */
  public  Long  trust_security_asset_3;

  /** 
   * <em>trust_security_asset_4</em>カラムの値 
   */
  public  Long  trust_security_asset_4;

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
   * <em>actual_account_balance_3</em>カラムの値 
   */
  public  Long  actual_account_balance_3;

  /** 
   * <em>actual_account_balance_4</em>カラムの値 
   */
  public  Long  actual_account_balance_4;

  /** 
   * <em>actual_account_balance_4_dash</em>カラムの値 
   */
  public  Long  actual_account_balance_4_dash;

  /** 
   * <em>actual_payment_balance_1</em>カラムの値 
   */
  public  Long  actual_payment_balance_1;

  /** 
   * <em>actual_payment_balance_2</em>カラムの値 
   */
  public  Long  actual_payment_balance_2;

  /** 
   * <em>actual_payment_balance_3</em>カラムの値 
   */
  public  Long  actual_payment_balance_3;

  /** 
   * <em>actual_payment_balance_4</em>カラムの値 
   */
  public  Long  actual_payment_balance_4;

  /** 
   * <em>payment_amount_designate_0</em>カラムの値 
   */
  public  Long  payment_amount_designate_0;

  /** 
   * <em>payment_amount_designate_1</em>カラムの値 
   */
  public  Long  payment_amount_designate_1;

  /** 
   * <em>payment_amount_designate_2</em>カラムの値 
   */
  public  Long  payment_amount_designate_2;

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

  private boolean calc_result_equity_id_is_set = false;
  private boolean calc_result_equity_id_is_modified = false;
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
  private boolean trust_security_asset_3_is_set = false;
  private boolean trust_security_asset_3_is_modified = false;
  private boolean trust_security_asset_4_is_set = false;
  private boolean trust_security_asset_4_is_modified = false;
  private boolean equity_trading_power_3_is_set = false;
  private boolean equity_trading_power_3_is_modified = false;
  private boolean equity_trading_power_4_is_set = false;
  private boolean equity_trading_power_4_is_modified = false;
  private boolean equity_trading_power_4_dash_is_set = false;
  private boolean equity_trading_power_4_dash_is_modified = false;
  private boolean actual_account_balance_3_is_set = false;
  private boolean actual_account_balance_3_is_modified = false;
  private boolean actual_account_balance_4_is_set = false;
  private boolean actual_account_balance_4_is_modified = false;
  private boolean actual_account_balance_4_dash_is_set = false;
  private boolean actual_account_balance_4_dash_is_modified = false;
  private boolean actual_payment_balance_1_is_set = false;
  private boolean actual_payment_balance_1_is_modified = false;
  private boolean actual_payment_balance_2_is_set = false;
  private boolean actual_payment_balance_2_is_modified = false;
  private boolean actual_payment_balance_3_is_set = false;
  private boolean actual_payment_balance_3_is_modified = false;
  private boolean actual_payment_balance_4_is_set = false;
  private boolean actual_payment_balance_4_is_modified = false;
  private boolean payment_amount_designate_0_is_set = false;
  private boolean payment_amount_designate_0_is_modified = false;
  private boolean payment_amount_designate_1_is_set = false;
  private boolean payment_amount_designate_1_is_modified = false;
  private boolean payment_amount_designate_2_is_set = false;
  private boolean payment_amount_designate_2_is_modified = false;
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
       + "calc_result_equity_id=" + calc_result_equity_id
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
      + "," + "trust_security_asset_3=" +trust_security_asset_3
      + "," + "trust_security_asset_4=" +trust_security_asset_4
      + "," + "equity_trading_power_3=" +equity_trading_power_3
      + "," + "equity_trading_power_4=" +equity_trading_power_4
      + "," + "equity_trading_power_4_dash=" +equity_trading_power_4_dash
      + "," + "actual_account_balance_3=" +actual_account_balance_3
      + "," + "actual_account_balance_4=" +actual_account_balance_4
      + "," + "actual_account_balance_4_dash=" +actual_account_balance_4_dash
      + "," + "actual_payment_balance_1=" +actual_payment_balance_1
      + "," + "actual_payment_balance_2=" +actual_payment_balance_2
      + "," + "actual_payment_balance_3=" +actual_payment_balance_3
      + "," + "actual_payment_balance_4=" +actual_payment_balance_4
      + "," + "payment_amount_designate_0=" +payment_amount_designate_0
      + "," + "payment_amount_designate_1=" +payment_amount_designate_1
      + "," + "payment_amount_designate_2=" +payment_amount_designate_2
      + "," + "equity_asset_executed=" +equity_asset_executed
      + "," + "ruito_asset_executed=" +ruito_asset_executed
      + "," + "mutual_fund_asset_executed=" +mutual_fund_asset_executed
      + "," + "bond_asset_executed=" +bond_asset_executed
      + "," + "trading_stop=" +trading_stop
      + "," + "payment_stop=" +payment_stop
      + "," + "other_trading_stop=" +other_trading_stop
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のOrixTpCalcResultEquityParamsオブジェクトを作成します。 
   */
  public OrixTpCalcResultEquityParams() {
    calc_result_equity_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のOrixTpCalcResultEquityRowオブジェクトの値を利用してOrixTpCalcResultEquityParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するOrixTpCalcResultEquityRowオブジェクト 
   */
  public OrixTpCalcResultEquityParams( OrixTpCalcResultEquityRow p_row )
  {
    calc_result_equity_id = p_row.getCalcResultEquityId();
    calc_result_equity_id_is_set = p_row.getCalcResultEquityIdIsSet();
    calc_result_equity_id_is_modified = p_row.getCalcResultEquityIdIsModified();
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
    if ( !p_row.getTrustSecurityAsset3IsNull() )
      trust_security_asset_3 = new Long( p_row.getTrustSecurityAsset3() );
    trust_security_asset_3_is_set = p_row.getTrustSecurityAsset3IsSet();
    trust_security_asset_3_is_modified = p_row.getTrustSecurityAsset3IsModified();
    if ( !p_row.getTrustSecurityAsset4IsNull() )
      trust_security_asset_4 = new Long( p_row.getTrustSecurityAsset4() );
    trust_security_asset_4_is_set = p_row.getTrustSecurityAsset4IsSet();
    trust_security_asset_4_is_modified = p_row.getTrustSecurityAsset4IsModified();
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
    if ( !p_row.getActualAccountBalance3IsNull() )
      actual_account_balance_3 = new Long( p_row.getActualAccountBalance3() );
    actual_account_balance_3_is_set = p_row.getActualAccountBalance3IsSet();
    actual_account_balance_3_is_modified = p_row.getActualAccountBalance3IsModified();
    if ( !p_row.getActualAccountBalance4IsNull() )
      actual_account_balance_4 = new Long( p_row.getActualAccountBalance4() );
    actual_account_balance_4_is_set = p_row.getActualAccountBalance4IsSet();
    actual_account_balance_4_is_modified = p_row.getActualAccountBalance4IsModified();
    if ( !p_row.getActualAccountBalance4DashIsNull() )
      actual_account_balance_4_dash = new Long( p_row.getActualAccountBalance4Dash() );
    actual_account_balance_4_dash_is_set = p_row.getActualAccountBalance4DashIsSet();
    actual_account_balance_4_dash_is_modified = p_row.getActualAccountBalance4DashIsModified();
    if ( !p_row.getActualPaymentBalance1IsNull() )
      actual_payment_balance_1 = new Long( p_row.getActualPaymentBalance1() );
    actual_payment_balance_1_is_set = p_row.getActualPaymentBalance1IsSet();
    actual_payment_balance_1_is_modified = p_row.getActualPaymentBalance1IsModified();
    if ( !p_row.getActualPaymentBalance2IsNull() )
      actual_payment_balance_2 = new Long( p_row.getActualPaymentBalance2() );
    actual_payment_balance_2_is_set = p_row.getActualPaymentBalance2IsSet();
    actual_payment_balance_2_is_modified = p_row.getActualPaymentBalance2IsModified();
    if ( !p_row.getActualPaymentBalance3IsNull() )
      actual_payment_balance_3 = new Long( p_row.getActualPaymentBalance3() );
    actual_payment_balance_3_is_set = p_row.getActualPaymentBalance3IsSet();
    actual_payment_balance_3_is_modified = p_row.getActualPaymentBalance3IsModified();
    if ( !p_row.getActualPaymentBalance4IsNull() )
      actual_payment_balance_4 = new Long( p_row.getActualPaymentBalance4() );
    actual_payment_balance_4_is_set = p_row.getActualPaymentBalance4IsSet();
    actual_payment_balance_4_is_modified = p_row.getActualPaymentBalance4IsModified();
    if ( !p_row.getPaymentAmountDesignate0IsNull() )
      payment_amount_designate_0 = new Long( p_row.getPaymentAmountDesignate0() );
    payment_amount_designate_0_is_set = p_row.getPaymentAmountDesignate0IsSet();
    payment_amount_designate_0_is_modified = p_row.getPaymentAmountDesignate0IsModified();
    if ( !p_row.getPaymentAmountDesignate1IsNull() )
      payment_amount_designate_1 = new Long( p_row.getPaymentAmountDesignate1() );
    payment_amount_designate_1_is_set = p_row.getPaymentAmountDesignate1IsSet();
    payment_amount_designate_1_is_modified = p_row.getPaymentAmountDesignate1IsModified();
    if ( !p_row.getPaymentAmountDesignate2IsNull() )
      payment_amount_designate_2 = new Long( p_row.getPaymentAmountDesignate2() );
    payment_amount_designate_2_is_set = p_row.getPaymentAmountDesignate2IsSet();
    payment_amount_designate_2_is_modified = p_row.getPaymentAmountDesignate2IsModified();
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
      trust_security_asset_3_is_set = true;
      trust_security_asset_3_is_modified = true;
      trust_security_asset_4_is_set = true;
      trust_security_asset_4_is_modified = true;
      equity_trading_power_3_is_set = true;
      equity_trading_power_3_is_modified = true;
      equity_trading_power_4_is_set = true;
      equity_trading_power_4_is_modified = true;
      equity_trading_power_4_dash_is_set = true;
      equity_trading_power_4_dash_is_modified = true;
      actual_account_balance_3_is_set = true;
      actual_account_balance_3_is_modified = true;
      actual_account_balance_4_is_set = true;
      actual_account_balance_4_is_modified = true;
      actual_account_balance_4_dash_is_set = true;
      actual_account_balance_4_dash_is_modified = true;
      actual_payment_balance_1_is_set = true;
      actual_payment_balance_1_is_modified = true;
      actual_payment_balance_2_is_set = true;
      actual_payment_balance_2_is_modified = true;
      actual_payment_balance_3_is_set = true;
      actual_payment_balance_3_is_modified = true;
      actual_payment_balance_4_is_set = true;
      actual_payment_balance_4_is_modified = true;
      payment_amount_designate_0_is_set = true;
      payment_amount_designate_0_is_modified = true;
      payment_amount_designate_1_is_set = true;
      payment_amount_designate_1_is_modified = true;
      payment_amount_designate_2_is_set = true;
      payment_amount_designate_2_is_modified = true;
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
    if ( !( other instanceof OrixTpCalcResultEquityRow ) )
       return false;
    return fieldsEqual( (OrixTpCalcResultEquityRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のOrixTpCalcResultEquityRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( OrixTpCalcResultEquityRow row )
  {
    if ( calc_result_equity_id != row.getCalcResultEquityId() )
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
    if ( trust_security_asset_3 == null ) {
      if ( !row.getTrustSecurityAsset3IsNull() )
        return false;
    } else if ( row.getTrustSecurityAsset3IsNull() || ( trust_security_asset_3.longValue() != row.getTrustSecurityAsset3() ) ) {
        return false;
    }
    if ( trust_security_asset_4 == null ) {
      if ( !row.getTrustSecurityAsset4IsNull() )
        return false;
    } else if ( row.getTrustSecurityAsset4IsNull() || ( trust_security_asset_4.longValue() != row.getTrustSecurityAsset4() ) ) {
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
    if ( actual_account_balance_3 == null ) {
      if ( !row.getActualAccountBalance3IsNull() )
        return false;
    } else if ( row.getActualAccountBalance3IsNull() || ( actual_account_balance_3.longValue() != row.getActualAccountBalance3() ) ) {
        return false;
    }
    if ( actual_account_balance_4 == null ) {
      if ( !row.getActualAccountBalance4IsNull() )
        return false;
    } else if ( row.getActualAccountBalance4IsNull() || ( actual_account_balance_4.longValue() != row.getActualAccountBalance4() ) ) {
        return false;
    }
    if ( actual_account_balance_4_dash == null ) {
      if ( !row.getActualAccountBalance4DashIsNull() )
        return false;
    } else if ( row.getActualAccountBalance4DashIsNull() || ( actual_account_balance_4_dash.longValue() != row.getActualAccountBalance4Dash() ) ) {
        return false;
    }
    if ( actual_payment_balance_1 == null ) {
      if ( !row.getActualPaymentBalance1IsNull() )
        return false;
    } else if ( row.getActualPaymentBalance1IsNull() || ( actual_payment_balance_1.longValue() != row.getActualPaymentBalance1() ) ) {
        return false;
    }
    if ( actual_payment_balance_2 == null ) {
      if ( !row.getActualPaymentBalance2IsNull() )
        return false;
    } else if ( row.getActualPaymentBalance2IsNull() || ( actual_payment_balance_2.longValue() != row.getActualPaymentBalance2() ) ) {
        return false;
    }
    if ( actual_payment_balance_3 == null ) {
      if ( !row.getActualPaymentBalance3IsNull() )
        return false;
    } else if ( row.getActualPaymentBalance3IsNull() || ( actual_payment_balance_3.longValue() != row.getActualPaymentBalance3() ) ) {
        return false;
    }
    if ( actual_payment_balance_4 == null ) {
      if ( !row.getActualPaymentBalance4IsNull() )
        return false;
    } else if ( row.getActualPaymentBalance4IsNull() || ( actual_payment_balance_4.longValue() != row.getActualPaymentBalance4() ) ) {
        return false;
    }
    if ( payment_amount_designate_0 == null ) {
      if ( !row.getPaymentAmountDesignate0IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate0IsNull() || ( payment_amount_designate_0.longValue() != row.getPaymentAmountDesignate0() ) ) {
        return false;
    }
    if ( payment_amount_designate_1 == null ) {
      if ( !row.getPaymentAmountDesignate1IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate1IsNull() || ( payment_amount_designate_1.longValue() != row.getPaymentAmountDesignate1() ) ) {
        return false;
    }
    if ( payment_amount_designate_2 == null ) {
      if ( !row.getPaymentAmountDesignate2IsNull() )
        return false;
    } else if ( row.getPaymentAmountDesignate2IsNull() || ( payment_amount_designate_2.longValue() != row.getPaymentAmountDesignate2() ) ) {
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
      return  ((int) calc_result_equity_id)
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
        + (trust_security_asset_3!=null? trust_security_asset_3.hashCode(): 0) 
        + (trust_security_asset_4!=null? trust_security_asset_4.hashCode(): 0) 
        + (equity_trading_power_3!=null? equity_trading_power_3.hashCode(): 0) 
        + (equity_trading_power_4!=null? equity_trading_power_4.hashCode(): 0) 
        + (equity_trading_power_4_dash!=null? equity_trading_power_4_dash.hashCode(): 0) 
        + (actual_account_balance_3!=null? actual_account_balance_3.hashCode(): 0) 
        + (actual_account_balance_4!=null? actual_account_balance_4.hashCode(): 0) 
        + (actual_account_balance_4_dash!=null? actual_account_balance_4_dash.hashCode(): 0) 
        + (actual_payment_balance_1!=null? actual_payment_balance_1.hashCode(): 0) 
        + (actual_payment_balance_2!=null? actual_payment_balance_2.hashCode(): 0) 
        + (actual_payment_balance_3!=null? actual_payment_balance_3.hashCode(): 0) 
        + (actual_payment_balance_4!=null? actual_payment_balance_4.hashCode(): 0) 
        + (payment_amount_designate_0!=null? payment_amount_designate_0.hashCode(): 0) 
        + (payment_amount_designate_1!=null? payment_amount_designate_1.hashCode(): 0) 
        + (payment_amount_designate_2!=null? payment_amount_designate_2.hashCode(): 0) 
        + (equity_asset_executed!=null? equity_asset_executed.hashCode(): 0) 
        + (ruito_asset_executed!=null? ruito_asset_executed.hashCode(): 0) 
        + (mutual_fund_asset_executed!=null? mutual_fund_asset_executed.hashCode(): 0) 
        + (bond_asset_executed!=null? bond_asset_executed.hashCode(): 0) 
        + (trading_stop!=null? trading_stop.hashCode(): 0) 
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
		map.put("calc_result_equity_id",new Long(calc_result_equity_id));
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
		if ( trust_security_asset_3 != null )
			map.put("trust_security_asset_3",trust_security_asset_3);
		if ( trust_security_asset_4 != null )
			map.put("trust_security_asset_4",trust_security_asset_4);
		if ( equity_trading_power_3 != null )
			map.put("equity_trading_power_3",equity_trading_power_3);
		if ( equity_trading_power_4 != null )
			map.put("equity_trading_power_4",equity_trading_power_4);
		if ( equity_trading_power_4_dash != null )
			map.put("equity_trading_power_4_dash",equity_trading_power_4_dash);
		if ( actual_account_balance_3 != null )
			map.put("actual_account_balance_3",actual_account_balance_3);
		if ( actual_account_balance_4 != null )
			map.put("actual_account_balance_4",actual_account_balance_4);
		if ( actual_account_balance_4_dash != null )
			map.put("actual_account_balance_4_dash",actual_account_balance_4_dash);
		if ( actual_payment_balance_1 != null )
			map.put("actual_payment_balance_1",actual_payment_balance_1);
		if ( actual_payment_balance_2 != null )
			map.put("actual_payment_balance_2",actual_payment_balance_2);
		if ( actual_payment_balance_3 != null )
			map.put("actual_payment_balance_3",actual_payment_balance_3);
		if ( actual_payment_balance_4 != null )
			map.put("actual_payment_balance_4",actual_payment_balance_4);
		if ( payment_amount_designate_0 != null )
			map.put("payment_amount_designate_0",payment_amount_designate_0);
		if ( payment_amount_designate_1 != null )
			map.put("payment_amount_designate_1",payment_amount_designate_1);
		if ( payment_amount_designate_2 != null )
			map.put("payment_amount_designate_2",payment_amount_designate_2);
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
		if ( trust_security_asset_3_is_modified )
			map.put("trust_security_asset_3",trust_security_asset_3);
		if ( trust_security_asset_4_is_modified )
			map.put("trust_security_asset_4",trust_security_asset_4);
		if ( equity_trading_power_3_is_modified )
			map.put("equity_trading_power_3",equity_trading_power_3);
		if ( equity_trading_power_4_is_modified )
			map.put("equity_trading_power_4",equity_trading_power_4);
		if ( equity_trading_power_4_dash_is_modified )
			map.put("equity_trading_power_4_dash",equity_trading_power_4_dash);
		if ( actual_account_balance_3_is_modified )
			map.put("actual_account_balance_3",actual_account_balance_3);
		if ( actual_account_balance_4_is_modified )
			map.put("actual_account_balance_4",actual_account_balance_4);
		if ( actual_account_balance_4_dash_is_modified )
			map.put("actual_account_balance_4_dash",actual_account_balance_4_dash);
		if ( actual_payment_balance_1_is_modified )
			map.put("actual_payment_balance_1",actual_payment_balance_1);
		if ( actual_payment_balance_2_is_modified )
			map.put("actual_payment_balance_2",actual_payment_balance_2);
		if ( actual_payment_balance_3_is_modified )
			map.put("actual_payment_balance_3",actual_payment_balance_3);
		if ( actual_payment_balance_4_is_modified )
			map.put("actual_payment_balance_4",actual_payment_balance_4);
		if ( payment_amount_designate_0_is_modified )
			map.put("payment_amount_designate_0",payment_amount_designate_0);
		if ( payment_amount_designate_1_is_modified )
			map.put("payment_amount_designate_1",payment_amount_designate_1);
		if ( payment_amount_designate_2_is_modified )
			map.put("payment_amount_designate_2",payment_amount_designate_2);
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
			map.put("trust_security_asset_3",trust_security_asset_3);
			map.put("trust_security_asset_4",trust_security_asset_4);
			map.put("equity_trading_power_3",equity_trading_power_3);
			map.put("equity_trading_power_4",equity_trading_power_4);
			map.put("equity_trading_power_4_dash",equity_trading_power_4_dash);
			map.put("actual_account_balance_3",actual_account_balance_3);
			map.put("actual_account_balance_4",actual_account_balance_4);
			map.put("actual_account_balance_4_dash",actual_account_balance_4_dash);
			map.put("actual_payment_balance_1",actual_payment_balance_1);
			map.put("actual_payment_balance_2",actual_payment_balance_2);
			map.put("actual_payment_balance_3",actual_payment_balance_3);
			map.put("actual_payment_balance_4",actual_payment_balance_4);
			map.put("payment_amount_designate_0",payment_amount_designate_0);
			map.put("payment_amount_designate_1",payment_amount_designate_1);
			map.put("payment_amount_designate_2",payment_amount_designate_2);
			map.put("equity_asset_executed",equity_asset_executed);
			map.put("ruito_asset_executed",ruito_asset_executed);
			map.put("mutual_fund_asset_executed",mutual_fund_asset_executed);
			map.put("bond_asset_executed",bond_asset_executed);
			map.put("trading_stop",trading_stop);
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
   * <em>calc_result_equity_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getCalcResultEquityId()
  {
    return calc_result_equity_id;
  }


  /** 
   * <em>calc_result_equity_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcResultEquityIdIsSet() {
    return calc_result_equity_id_is_set;
  }


  /** 
   * <em>calc_result_equity_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcResultEquityIdIsModified() {
    return calc_result_equity_id_is_modified;
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
   * <em>trust_security_asset_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getTrustSecurityAsset3()
  {
    return ( trust_security_asset_3==null? ((long)0): trust_security_asset_3.longValue() );
  }


  /** 
   * <em>trust_security_asset_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTrustSecurityAsset3IsNull()
  {
    return trust_security_asset_3 == null;
  }


  /** 
   * <em>trust_security_asset_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrustSecurityAsset3IsSet() {
    return trust_security_asset_3_is_set;
  }


  /** 
   * <em>trust_security_asset_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrustSecurityAsset3IsModified() {
    return trust_security_asset_3_is_modified;
  }


  /** 
   * <em>trust_security_asset_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getTrustSecurityAsset4()
  {
    return ( trust_security_asset_4==null? ((long)0): trust_security_asset_4.longValue() );
  }


  /** 
   * <em>trust_security_asset_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTrustSecurityAsset4IsNull()
  {
    return trust_security_asset_4 == null;
  }


  /** 
   * <em>trust_security_asset_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrustSecurityAsset4IsSet() {
    return trust_security_asset_4_is_set;
  }


  /** 
   * <em>trust_security_asset_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrustSecurityAsset4IsModified() {
    return trust_security_asset_4_is_modified;
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
   * <em>actual_account_balance_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getActualAccountBalance3()
  {
    return ( actual_account_balance_3==null? ((long)0): actual_account_balance_3.longValue() );
  }


  /** 
   * <em>actual_account_balance_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getActualAccountBalance3IsNull()
  {
    return actual_account_balance_3 == null;
  }


  /** 
   * <em>actual_account_balance_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualAccountBalance3IsSet() {
    return actual_account_balance_3_is_set;
  }


  /** 
   * <em>actual_account_balance_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualAccountBalance3IsModified() {
    return actual_account_balance_3_is_modified;
  }


  /** 
   * <em>actual_account_balance_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getActualAccountBalance4()
  {
    return ( actual_account_balance_4==null? ((long)0): actual_account_balance_4.longValue() );
  }


  /** 
   * <em>actual_account_balance_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getActualAccountBalance4IsNull()
  {
    return actual_account_balance_4 == null;
  }


  /** 
   * <em>actual_account_balance_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualAccountBalance4IsSet() {
    return actual_account_balance_4_is_set;
  }


  /** 
   * <em>actual_account_balance_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualAccountBalance4IsModified() {
    return actual_account_balance_4_is_modified;
  }


  /** 
   * <em>actual_account_balance_4_dash</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getActualAccountBalance4Dash()
  {
    return ( actual_account_balance_4_dash==null? ((long)0): actual_account_balance_4_dash.longValue() );
  }


  /** 
   * <em>actual_account_balance_4_dash</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getActualAccountBalance4DashIsNull()
  {
    return actual_account_balance_4_dash == null;
  }


  /** 
   * <em>actual_account_balance_4_dash</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualAccountBalance4DashIsSet() {
    return actual_account_balance_4_dash_is_set;
  }


  /** 
   * <em>actual_account_balance_4_dash</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualAccountBalance4DashIsModified() {
    return actual_account_balance_4_dash_is_modified;
  }


  /** 
   * <em>actual_payment_balance_1</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getActualPaymentBalance1()
  {
    return ( actual_payment_balance_1==null? ((long)0): actual_payment_balance_1.longValue() );
  }


  /** 
   * <em>actual_payment_balance_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getActualPaymentBalance1IsNull()
  {
    return actual_payment_balance_1 == null;
  }


  /** 
   * <em>actual_payment_balance_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualPaymentBalance1IsSet() {
    return actual_payment_balance_1_is_set;
  }


  /** 
   * <em>actual_payment_balance_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualPaymentBalance1IsModified() {
    return actual_payment_balance_1_is_modified;
  }


  /** 
   * <em>actual_payment_balance_2</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getActualPaymentBalance2()
  {
    return ( actual_payment_balance_2==null? ((long)0): actual_payment_balance_2.longValue() );
  }


  /** 
   * <em>actual_payment_balance_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getActualPaymentBalance2IsNull()
  {
    return actual_payment_balance_2 == null;
  }


  /** 
   * <em>actual_payment_balance_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualPaymentBalance2IsSet() {
    return actual_payment_balance_2_is_set;
  }


  /** 
   * <em>actual_payment_balance_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualPaymentBalance2IsModified() {
    return actual_payment_balance_2_is_modified;
  }


  /** 
   * <em>actual_payment_balance_3</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getActualPaymentBalance3()
  {
    return ( actual_payment_balance_3==null? ((long)0): actual_payment_balance_3.longValue() );
  }


  /** 
   * <em>actual_payment_balance_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getActualPaymentBalance3IsNull()
  {
    return actual_payment_balance_3 == null;
  }


  /** 
   * <em>actual_payment_balance_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualPaymentBalance3IsSet() {
    return actual_payment_balance_3_is_set;
  }


  /** 
   * <em>actual_payment_balance_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualPaymentBalance3IsModified() {
    return actual_payment_balance_3_is_modified;
  }


  /** 
   * <em>actual_payment_balance_4</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getActualPaymentBalance4()
  {
    return ( actual_payment_balance_4==null? ((long)0): actual_payment_balance_4.longValue() );
  }


  /** 
   * <em>actual_payment_balance_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getActualPaymentBalance4IsNull()
  {
    return actual_payment_balance_4 == null;
  }


  /** 
   * <em>actual_payment_balance_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualPaymentBalance4IsSet() {
    return actual_payment_balance_4_is_set;
  }


  /** 
   * <em>actual_payment_balance_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getActualPaymentBalance4IsModified() {
    return actual_payment_balance_4_is_modified;
  }


  /** 
   * <em>payment_amount_designate_0</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getPaymentAmountDesignate0()
  {
    return ( payment_amount_designate_0==null? ((long)0): payment_amount_designate_0.longValue() );
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
   * @@return longの値 
   */
  public final long getPaymentAmountDesignate1()
  {
    return ( payment_amount_designate_1==null? ((long)0): payment_amount_designate_1.longValue() );
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
   * @@return longの値 
   */
  public final long getPaymentAmountDesignate2()
  {
    return ( payment_amount_designate_2==null? ((long)0): payment_amount_designate_2.longValue() );
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
    return new OrixTpCalcResultEquityPK(calc_result_equity_id);
  }


  /** 
   * <em>calc_result_equity_id</em>カラムの値を設定します。 
   *
   * @@param p_calcResultEquityId <em>calc_result_equity_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setCalcResultEquityId( long p_calcResultEquityId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_result_equity_id = p_calcResultEquityId;
    calc_result_equity_id_is_set = true;
    calc_result_equity_id_is_modified = true;
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
   * <em>trust_security_asset_3</em>カラムの値を設定します。 
   *
   * @@param p_trustSecurityAsset3 <em>trust_security_asset_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setTrustSecurityAsset3( long p_trustSecurityAsset3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trust_security_asset_3 = new Long(p_trustSecurityAsset3);
    trust_security_asset_3_is_set = true;
    trust_security_asset_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>trust_security_asset_3</em>カラムに値を設定します。 
   */
  public final void setTrustSecurityAsset3( Long p_trustSecurityAsset3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trust_security_asset_3 = p_trustSecurityAsset3;
    trust_security_asset_3_is_set = true;
    trust_security_asset_3_is_modified = true;
  }


  /** 
   * <em>trust_security_asset_4</em>カラムの値を設定します。 
   *
   * @@param p_trustSecurityAsset4 <em>trust_security_asset_4</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setTrustSecurityAsset4( long p_trustSecurityAsset4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trust_security_asset_4 = new Long(p_trustSecurityAsset4);
    trust_security_asset_4_is_set = true;
    trust_security_asset_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>trust_security_asset_4</em>カラムに値を設定します。 
   */
  public final void setTrustSecurityAsset4( Long p_trustSecurityAsset4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trust_security_asset_4 = p_trustSecurityAsset4;
    trust_security_asset_4_is_set = true;
    trust_security_asset_4_is_modified = true;
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
   * <em>actual_account_balance_3</em>カラムの値を設定します。 
   *
   * @@param p_actualAccountBalance3 <em>actual_account_balance_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setActualAccountBalance3( long p_actualAccountBalance3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    actual_account_balance_3 = new Long(p_actualAccountBalance3);
    actual_account_balance_3_is_set = true;
    actual_account_balance_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>actual_account_balance_3</em>カラムに値を設定します。 
   */
  public final void setActualAccountBalance3( Long p_actualAccountBalance3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    actual_account_balance_3 = p_actualAccountBalance3;
    actual_account_balance_3_is_set = true;
    actual_account_balance_3_is_modified = true;
  }


  /** 
   * <em>actual_account_balance_4</em>カラムの値を設定します。 
   *
   * @@param p_actualAccountBalance4 <em>actual_account_balance_4</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setActualAccountBalance4( long p_actualAccountBalance4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    actual_account_balance_4 = new Long(p_actualAccountBalance4);
    actual_account_balance_4_is_set = true;
    actual_account_balance_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>actual_account_balance_4</em>カラムに値を設定します。 
   */
  public final void setActualAccountBalance4( Long p_actualAccountBalance4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    actual_account_balance_4 = p_actualAccountBalance4;
    actual_account_balance_4_is_set = true;
    actual_account_balance_4_is_modified = true;
  }


  /** 
   * <em>actual_account_balance_4_dash</em>カラムの値を設定します。 
   *
   * @@param p_actualAccountBalance4Dash <em>actual_account_balance_4_dash</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setActualAccountBalance4Dash( long p_actualAccountBalance4Dash )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    actual_account_balance_4_dash = new Long(p_actualAccountBalance4Dash);
    actual_account_balance_4_dash_is_set = true;
    actual_account_balance_4_dash_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>actual_account_balance_4_dash</em>カラムに値を設定します。 
   */
  public final void setActualAccountBalance4Dash( Long p_actualAccountBalance4Dash )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    actual_account_balance_4_dash = p_actualAccountBalance4Dash;
    actual_account_balance_4_dash_is_set = true;
    actual_account_balance_4_dash_is_modified = true;
  }


  /** 
   * <em>actual_payment_balance_1</em>カラムの値を設定します。 
   *
   * @@param p_actualPaymentBalance1 <em>actual_payment_balance_1</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setActualPaymentBalance1( long p_actualPaymentBalance1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    actual_payment_balance_1 = new Long(p_actualPaymentBalance1);
    actual_payment_balance_1_is_set = true;
    actual_payment_balance_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>actual_payment_balance_1</em>カラムに値を設定します。 
   */
  public final void setActualPaymentBalance1( Long p_actualPaymentBalance1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    actual_payment_balance_1 = p_actualPaymentBalance1;
    actual_payment_balance_1_is_set = true;
    actual_payment_balance_1_is_modified = true;
  }


  /** 
   * <em>actual_payment_balance_2</em>カラムの値を設定します。 
   *
   * @@param p_actualPaymentBalance2 <em>actual_payment_balance_2</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setActualPaymentBalance2( long p_actualPaymentBalance2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    actual_payment_balance_2 = new Long(p_actualPaymentBalance2);
    actual_payment_balance_2_is_set = true;
    actual_payment_balance_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>actual_payment_balance_2</em>カラムに値を設定します。 
   */
  public final void setActualPaymentBalance2( Long p_actualPaymentBalance2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    actual_payment_balance_2 = p_actualPaymentBalance2;
    actual_payment_balance_2_is_set = true;
    actual_payment_balance_2_is_modified = true;
  }


  /** 
   * <em>actual_payment_balance_3</em>カラムの値を設定します。 
   *
   * @@param p_actualPaymentBalance3 <em>actual_payment_balance_3</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setActualPaymentBalance3( long p_actualPaymentBalance3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    actual_payment_balance_3 = new Long(p_actualPaymentBalance3);
    actual_payment_balance_3_is_set = true;
    actual_payment_balance_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>actual_payment_balance_3</em>カラムに値を設定します。 
   */
  public final void setActualPaymentBalance3( Long p_actualPaymentBalance3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    actual_payment_balance_3 = p_actualPaymentBalance3;
    actual_payment_balance_3_is_set = true;
    actual_payment_balance_3_is_modified = true;
  }


  /** 
   * <em>actual_payment_balance_4</em>カラムの値を設定します。 
   *
   * @@param p_actualPaymentBalance4 <em>actual_payment_balance_4</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setActualPaymentBalance4( long p_actualPaymentBalance4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    actual_payment_balance_4 = new Long(p_actualPaymentBalance4);
    actual_payment_balance_4_is_set = true;
    actual_payment_balance_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>actual_payment_balance_4</em>カラムに値を設定します。 
   */
  public final void setActualPaymentBalance4( Long p_actualPaymentBalance4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    actual_payment_balance_4 = p_actualPaymentBalance4;
    actual_payment_balance_4_is_set = true;
    actual_payment_balance_4_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate_0</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate0 <em>payment_amount_designate_0</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setPaymentAmountDesignate0( long p_paymentAmountDesignate0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_0 = new Long(p_paymentAmountDesignate0);
    payment_amount_designate_0_is_set = true;
    payment_amount_designate_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate_0</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate0( Long p_paymentAmountDesignate0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_0 = p_paymentAmountDesignate0;
    payment_amount_designate_0_is_set = true;
    payment_amount_designate_0_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate_1</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate1 <em>payment_amount_designate_1</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setPaymentAmountDesignate1( long p_paymentAmountDesignate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_1 = new Long(p_paymentAmountDesignate1);
    payment_amount_designate_1_is_set = true;
    payment_amount_designate_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate_1</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate1( Long p_paymentAmountDesignate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_1 = p_paymentAmountDesignate1;
    payment_amount_designate_1_is_set = true;
    payment_amount_designate_1_is_modified = true;
  }


  /** 
   * <em>payment_amount_designate_2</em>カラムの値を設定します。 
   *
   * @@param p_paymentAmountDesignate2 <em>payment_amount_designate_2</em>カラムの値をあらわす14桁以下のlong値 
   */
  public final void setPaymentAmountDesignate2( long p_paymentAmountDesignate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_2 = new Long(p_paymentAmountDesignate2);
    payment_amount_designate_2_is_set = true;
    payment_amount_designate_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_amount_designate_2</em>カラムに値を設定します。 
   */
  public final void setPaymentAmountDesignate2( Long p_paymentAmountDesignate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_amount_designate_2 = p_paymentAmountDesignate2;
    payment_amount_designate_2_is_set = true;
    payment_amount_designate_2_is_modified = true;
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
                else if ( name.equals("actual_account_balance_3") ) {
                    return this.actual_account_balance_3;
                }
                else if ( name.equals("actual_account_balance_4") ) {
                    return this.actual_account_balance_4;
                }
                else if ( name.equals("actual_account_balance_4_dash") ) {
                    return this.actual_account_balance_4_dash;
                }
                else if ( name.equals("actual_payment_balance_1") ) {
                    return this.actual_payment_balance_1;
                }
                else if ( name.equals("actual_payment_balance_2") ) {
                    return this.actual_payment_balance_2;
                }
                else if ( name.equals("actual_payment_balance_3") ) {
                    return this.actual_payment_balance_3;
                }
                else if ( name.equals("actual_payment_balance_4") ) {
                    return this.actual_payment_balance_4;
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
                if ( name.equals("calc_result_equity_id") ) {
                    return new Long( calc_result_equity_id );
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
                if ( name.equals("mutual_fund_asset_executed") ) {
                    return this.mutual_fund_asset_executed;
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
                else if ( name.equals("other_trading_stop") ) {
                    return this.other_trading_stop;
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
                else if ( name.equals("payment_stop") ) {
                    return this.payment_stop;
                }
                break;
            case 'r':
                if ( name.equals("ruito_asset_executed") ) {
                    return this.ruito_asset_executed;
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
                else if ( name.equals("trust_security_asset_3") ) {
                    return this.trust_security_asset_3;
                }
                else if ( name.equals("trust_security_asset_4") ) {
                    return this.trust_security_asset_4;
                }
                else if ( name.equals("trading_stop") ) {
                    return this.trading_stop;
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
                else if ( name.equals("actual_account_balance_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'actual_account_balance_3' must be Long: '"+value+"' is not." );
                    this.actual_account_balance_3 = (Long) value;
                    if (this.actual_account_balance_3_is_set)
                        this.actual_account_balance_3_is_modified = true;
                    this.actual_account_balance_3_is_set = true;
                    return;
                }
                else if ( name.equals("actual_account_balance_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'actual_account_balance_4' must be Long: '"+value+"' is not." );
                    this.actual_account_balance_4 = (Long) value;
                    if (this.actual_account_balance_4_is_set)
                        this.actual_account_balance_4_is_modified = true;
                    this.actual_account_balance_4_is_set = true;
                    return;
                }
                else if ( name.equals("actual_account_balance_4_dash") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'actual_account_balance_4_dash' must be Long: '"+value+"' is not." );
                    this.actual_account_balance_4_dash = (Long) value;
                    if (this.actual_account_balance_4_dash_is_set)
                        this.actual_account_balance_4_dash_is_modified = true;
                    this.actual_account_balance_4_dash_is_set = true;
                    return;
                }
                else if ( name.equals("actual_payment_balance_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'actual_payment_balance_1' must be Long: '"+value+"' is not." );
                    this.actual_payment_balance_1 = (Long) value;
                    if (this.actual_payment_balance_1_is_set)
                        this.actual_payment_balance_1_is_modified = true;
                    this.actual_payment_balance_1_is_set = true;
                    return;
                }
                else if ( name.equals("actual_payment_balance_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'actual_payment_balance_2' must be Long: '"+value+"' is not." );
                    this.actual_payment_balance_2 = (Long) value;
                    if (this.actual_payment_balance_2_is_set)
                        this.actual_payment_balance_2_is_modified = true;
                    this.actual_payment_balance_2_is_set = true;
                    return;
                }
                else if ( name.equals("actual_payment_balance_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'actual_payment_balance_3' must be Long: '"+value+"' is not." );
                    this.actual_payment_balance_3 = (Long) value;
                    if (this.actual_payment_balance_3_is_set)
                        this.actual_payment_balance_3_is_modified = true;
                    this.actual_payment_balance_3_is_set = true;
                    return;
                }
                else if ( name.equals("actual_payment_balance_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'actual_payment_balance_4' must be Long: '"+value+"' is not." );
                    this.actual_payment_balance_4 = (Long) value;
                    if (this.actual_payment_balance_4_is_set)
                        this.actual_payment_balance_4_is_modified = true;
                    this.actual_payment_balance_4_is_set = true;
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
                if ( name.equals("calc_result_equity_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'calc_result_equity_id' must be Long: '"+value+"' is not." );
                    this.calc_result_equity_id = ((Long) value).longValue();
                    if (this.calc_result_equity_id_is_set)
                        this.calc_result_equity_id_is_modified = true;
                    this.calc_result_equity_id_is_set = true;
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
                if ( name.equals("mutual_fund_asset_executed") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'mutual_fund_asset_executed' must be Long: '"+value+"' is not." );
                    this.mutual_fund_asset_executed = (Long) value;
                    if (this.mutual_fund_asset_executed_is_set)
                        this.mutual_fund_asset_executed_is_modified = true;
                    this.mutual_fund_asset_executed_is_set = true;
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
                if ( name.equals("payment_amount_designate_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate_0' must be Long: '"+value+"' is not." );
                    this.payment_amount_designate_0 = (Long) value;
                    if (this.payment_amount_designate_0_is_set)
                        this.payment_amount_designate_0_is_modified = true;
                    this.payment_amount_designate_0_is_set = true;
                    return;
                }
                else if ( name.equals("payment_amount_designate_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate_1' must be Long: '"+value+"' is not." );
                    this.payment_amount_designate_1 = (Long) value;
                    if (this.payment_amount_designate_1_is_set)
                        this.payment_amount_designate_1_is_modified = true;
                    this.payment_amount_designate_1_is_set = true;
                    return;
                }
                else if ( name.equals("payment_amount_designate_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'payment_amount_designate_2' must be Long: '"+value+"' is not." );
                    this.payment_amount_designate_2 = (Long) value;
                    if (this.payment_amount_designate_2_is_set)
                        this.payment_amount_designate_2_is_modified = true;
                    this.payment_amount_designate_2_is_set = true;
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
                else if ( name.equals("trust_security_asset_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'trust_security_asset_3' must be Long: '"+value+"' is not." );
                    this.trust_security_asset_3 = (Long) value;
                    if (this.trust_security_asset_3_is_set)
                        this.trust_security_asset_3_is_modified = true;
                    this.trust_security_asset_3_is_set = true;
                    return;
                }
                else if ( name.equals("trust_security_asset_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'trust_security_asset_4' must be Long: '"+value+"' is not." );
                    this.trust_security_asset_4 = (Long) value;
                    if (this.trust_security_asset_4_is_set)
                        this.trust_security_asset_4_is_modified = true;
                    this.trust_security_asset_4_is_set = true;
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
