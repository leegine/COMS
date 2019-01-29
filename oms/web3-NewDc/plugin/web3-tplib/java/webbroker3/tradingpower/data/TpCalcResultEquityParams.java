head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.27.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpCalcResultEquityParams.java;


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
 * tp_calc_result_equityテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link TpCalcResultEquityRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link TpCalcResultEquityParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link TpCalcResultEquityParams}が{@@link TpCalcResultEquityRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see TpCalcResultEquityPK 
 * @@see TpCalcResultEquityRow 
 */
public class TpCalcResultEquityParams extends Params implements TpCalcResultEquityRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "tp_calc_result_equity";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = TpCalcResultEquityRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return TpCalcResultEquityRow.TYPE;
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
   * <em>trust_security_asset_0</em>カラムの値 
   */
  public  double  trust_security_asset_0;

  /** 
   * <em>trust_security_asset_1</em>カラムの値 
   */
  public  double  trust_security_asset_1;

  /** 
   * <em>trust_security_asset_2</em>カラムの値 
   */
  public  double  trust_security_asset_2;

  /** 
   * <em>trust_security_asset_3</em>カラムの値 
   */
  public  double  trust_security_asset_3;

  /** 
   * <em>trust_security_asset_4</em>カラムの値 
   */
  public  double  trust_security_asset_4;

  /** 
   * <em>trust_security_asset_5</em>カラムの値 
   */
  public  double  trust_security_asset_5;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>cash_deposit_restraint_0</em>カラムの値 
   */
  public  double  cash_deposit_restraint_0;

  /** 
   * <em>cash_deposit_restraint_1</em>カラムの値 
   */
  public  double  cash_deposit_restraint_1;

  /** 
   * <em>cash_deposit_restraint_2</em>カラムの値 
   */
  public  double  cash_deposit_restraint_2;

  /** 
   * <em>cash_deposit_restraint_3</em>カラムの値 
   */
  public  double  cash_deposit_restraint_3;

  /** 
   * <em>cash_deposit_restraint_4</em>カラムの値 
   */
  public  double  cash_deposit_restraint_4;

  /** 
   * <em>cash_deposit_restraint_5</em>カラムの値 
   */
  public  double  cash_deposit_restraint_5;

  /** 
   * <em>day_trade_restraint_5</em>カラムの値 
   */
  public  Double  day_trade_restraint_5;

  private boolean calc_result_equity_id_is_set = false;
  private boolean calc_result_equity_id_is_modified = false;
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
  private boolean trust_security_asset_0_is_set = false;
  private boolean trust_security_asset_0_is_modified = false;
  private boolean trust_security_asset_1_is_set = false;
  private boolean trust_security_asset_1_is_modified = false;
  private boolean trust_security_asset_2_is_set = false;
  private boolean trust_security_asset_2_is_modified = false;
  private boolean trust_security_asset_3_is_set = false;
  private boolean trust_security_asset_3_is_modified = false;
  private boolean trust_security_asset_4_is_set = false;
  private boolean trust_security_asset_4_is_modified = false;
  private boolean trust_security_asset_5_is_set = false;
  private boolean trust_security_asset_5_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean cash_deposit_restraint_0_is_set = false;
  private boolean cash_deposit_restraint_0_is_modified = false;
  private boolean cash_deposit_restraint_1_is_set = false;
  private boolean cash_deposit_restraint_1_is_modified = false;
  private boolean cash_deposit_restraint_2_is_set = false;
  private boolean cash_deposit_restraint_2_is_modified = false;
  private boolean cash_deposit_restraint_3_is_set = false;
  private boolean cash_deposit_restraint_3_is_modified = false;
  private boolean cash_deposit_restraint_4_is_set = false;
  private boolean cash_deposit_restraint_4_is_modified = false;
  private boolean cash_deposit_restraint_5_is_set = false;
  private boolean cash_deposit_restraint_5_is_modified = false;
  private boolean day_trade_restraint_5_is_set = false;
  private boolean day_trade_restraint_5_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "calc_result_equity_id=" + calc_result_equity_id
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
      + "," + "trust_security_asset_0=" +trust_security_asset_0
      + "," + "trust_security_asset_1=" +trust_security_asset_1
      + "," + "trust_security_asset_2=" +trust_security_asset_2
      + "," + "trust_security_asset_3=" +trust_security_asset_3
      + "," + "trust_security_asset_4=" +trust_security_asset_4
      + "," + "trust_security_asset_5=" +trust_security_asset_5
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "cash_deposit_restraint_0=" +cash_deposit_restraint_0
      + "," + "cash_deposit_restraint_1=" +cash_deposit_restraint_1
      + "," + "cash_deposit_restraint_2=" +cash_deposit_restraint_2
      + "," + "cash_deposit_restraint_3=" +cash_deposit_restraint_3
      + "," + "cash_deposit_restraint_4=" +cash_deposit_restraint_4
      + "," + "cash_deposit_restraint_5=" +cash_deposit_restraint_5
      + "," + "day_trade_restraint_5=" +day_trade_restraint_5
      + "}";
  }


  /** 
   * 値が未設定のTpCalcResultEquityParamsオブジェクトを作成します。 
   */
  public TpCalcResultEquityParams() {
    calc_result_equity_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のTpCalcResultEquityRowオブジェクトの値を利用してTpCalcResultEquityParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するTpCalcResultEquityRowオブジェクト 
   */
  public TpCalcResultEquityParams( TpCalcResultEquityRow p_row )
  {
    calc_result_equity_id = p_row.getCalcResultEquityId();
    calc_result_equity_id_is_set = p_row.getCalcResultEquityIdIsSet();
    calc_result_equity_id_is_modified = p_row.getCalcResultEquityIdIsModified();
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
    trust_security_asset_0 = p_row.getTrustSecurityAsset0();
    trust_security_asset_0_is_set = p_row.getTrustSecurityAsset0IsSet();
    trust_security_asset_0_is_modified = p_row.getTrustSecurityAsset0IsModified();
    trust_security_asset_1 = p_row.getTrustSecurityAsset1();
    trust_security_asset_1_is_set = p_row.getTrustSecurityAsset1IsSet();
    trust_security_asset_1_is_modified = p_row.getTrustSecurityAsset1IsModified();
    trust_security_asset_2 = p_row.getTrustSecurityAsset2();
    trust_security_asset_2_is_set = p_row.getTrustSecurityAsset2IsSet();
    trust_security_asset_2_is_modified = p_row.getTrustSecurityAsset2IsModified();
    trust_security_asset_3 = p_row.getTrustSecurityAsset3();
    trust_security_asset_3_is_set = p_row.getTrustSecurityAsset3IsSet();
    trust_security_asset_3_is_modified = p_row.getTrustSecurityAsset3IsModified();
    trust_security_asset_4 = p_row.getTrustSecurityAsset4();
    trust_security_asset_4_is_set = p_row.getTrustSecurityAsset4IsSet();
    trust_security_asset_4_is_modified = p_row.getTrustSecurityAsset4IsModified();
    trust_security_asset_5 = p_row.getTrustSecurityAsset5();
    trust_security_asset_5_is_set = p_row.getTrustSecurityAsset5IsSet();
    trust_security_asset_5_is_modified = p_row.getTrustSecurityAsset5IsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    cash_deposit_restraint_0 = p_row.getCashDepositRestraint0();
    cash_deposit_restraint_0_is_set = p_row.getCashDepositRestraint0IsSet();
    cash_deposit_restraint_0_is_modified = p_row.getCashDepositRestraint0IsModified();
    cash_deposit_restraint_1 = p_row.getCashDepositRestraint1();
    cash_deposit_restraint_1_is_set = p_row.getCashDepositRestraint1IsSet();
    cash_deposit_restraint_1_is_modified = p_row.getCashDepositRestraint1IsModified();
    cash_deposit_restraint_2 = p_row.getCashDepositRestraint2();
    cash_deposit_restraint_2_is_set = p_row.getCashDepositRestraint2IsSet();
    cash_deposit_restraint_2_is_modified = p_row.getCashDepositRestraint2IsModified();
    cash_deposit_restraint_3 = p_row.getCashDepositRestraint3();
    cash_deposit_restraint_3_is_set = p_row.getCashDepositRestraint3IsSet();
    cash_deposit_restraint_3_is_modified = p_row.getCashDepositRestraint3IsModified();
    cash_deposit_restraint_4 = p_row.getCashDepositRestraint4();
    cash_deposit_restraint_4_is_set = p_row.getCashDepositRestraint4IsSet();
    cash_deposit_restraint_4_is_modified = p_row.getCashDepositRestraint4IsModified();
    cash_deposit_restraint_5 = p_row.getCashDepositRestraint5();
    cash_deposit_restraint_5_is_set = p_row.getCashDepositRestraint5IsSet();
    cash_deposit_restraint_5_is_modified = p_row.getCashDepositRestraint5IsModified();
    if ( !p_row.getDayTradeRestraint5IsNull() )
      day_trade_restraint_5 = new Double( p_row.getDayTradeRestraint5() );
    day_trade_restraint_5_is_set = p_row.getDayTradeRestraint5IsSet();
    day_trade_restraint_5_is_modified = p_row.getDayTradeRestraint5IsModified();
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
      trust_security_asset_0_is_set = true;
      trust_security_asset_0_is_modified = true;
      trust_security_asset_1_is_set = true;
      trust_security_asset_1_is_modified = true;
      trust_security_asset_2_is_set = true;
      trust_security_asset_2_is_modified = true;
      trust_security_asset_3_is_set = true;
      trust_security_asset_3_is_modified = true;
      trust_security_asset_4_is_set = true;
      trust_security_asset_4_is_modified = true;
      trust_security_asset_5_is_set = true;
      trust_security_asset_5_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      cash_deposit_restraint_0_is_set = true;
      cash_deposit_restraint_0_is_modified = true;
      cash_deposit_restraint_1_is_set = true;
      cash_deposit_restraint_1_is_modified = true;
      cash_deposit_restraint_2_is_set = true;
      cash_deposit_restraint_2_is_modified = true;
      cash_deposit_restraint_3_is_set = true;
      cash_deposit_restraint_3_is_modified = true;
      cash_deposit_restraint_4_is_set = true;
      cash_deposit_restraint_4_is_modified = true;
      cash_deposit_restraint_5_is_set = true;
      cash_deposit_restraint_5_is_modified = true;
      day_trade_restraint_5_is_set = true;
      day_trade_restraint_5_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof TpCalcResultEquityRow ) )
       return false;
    return fieldsEqual( (TpCalcResultEquityRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のTpCalcResultEquityRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( TpCalcResultEquityRow row )
  {
    if ( calc_result_equity_id != row.getCalcResultEquityId() )
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
    if ( trust_security_asset_0 != row.getTrustSecurityAsset0() )
      return false;
    if ( trust_security_asset_1 != row.getTrustSecurityAsset1() )
      return false;
    if ( trust_security_asset_2 != row.getTrustSecurityAsset2() )
      return false;
    if ( trust_security_asset_3 != row.getTrustSecurityAsset3() )
      return false;
    if ( trust_security_asset_4 != row.getTrustSecurityAsset4() )
      return false;
    if ( trust_security_asset_5 != row.getTrustSecurityAsset5() )
      return false;
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
    if ( cash_deposit_restraint_0 != row.getCashDepositRestraint0() )
      return false;
    if ( cash_deposit_restraint_1 != row.getCashDepositRestraint1() )
      return false;
    if ( cash_deposit_restraint_2 != row.getCashDepositRestraint2() )
      return false;
    if ( cash_deposit_restraint_3 != row.getCashDepositRestraint3() )
      return false;
    if ( cash_deposit_restraint_4 != row.getCashDepositRestraint4() )
      return false;
    if ( cash_deposit_restraint_5 != row.getCashDepositRestraint5() )
      return false;
    if ( day_trade_restraint_5 == null ) {
      if ( !row.getDayTradeRestraint5IsNull() )
        return false;
    } else if ( row.getDayTradeRestraint5IsNull() || ( day_trade_restraint_5.doubleValue() != row.getDayTradeRestraint5() ) ) {
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
        + ((int) trust_security_asset_0)
        + ((int) trust_security_asset_1)
        + ((int) trust_security_asset_2)
        + ((int) trust_security_asset_3)
        + ((int) trust_security_asset_4)
        + ((int) trust_security_asset_5)
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + ((int) cash_deposit_restraint_0)
        + ((int) cash_deposit_restraint_1)
        + ((int) cash_deposit_restraint_2)
        + ((int) cash_deposit_restraint_3)
        + ((int) cash_deposit_restraint_4)
        + ((int) cash_deposit_restraint_5)
        + (day_trade_restraint_5!=null? day_trade_restraint_5.hashCode(): 0) 
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
		if ( !trust_security_asset_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'trust_security_asset_0' must be set before inserting.");
		if ( !trust_security_asset_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'trust_security_asset_1' must be set before inserting.");
		if ( !trust_security_asset_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'trust_security_asset_2' must be set before inserting.");
		if ( !trust_security_asset_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'trust_security_asset_3' must be set before inserting.");
		if ( !trust_security_asset_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'trust_security_asset_4' must be set before inserting.");
		if ( !trust_security_asset_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'trust_security_asset_5' must be set before inserting.");
		if ( !cash_deposit_restraint_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'cash_deposit_restraint_0' must be set before inserting.");
		if ( !cash_deposit_restraint_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'cash_deposit_restraint_1' must be set before inserting.");
		if ( !cash_deposit_restraint_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'cash_deposit_restraint_2' must be set before inserting.");
		if ( !cash_deposit_restraint_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'cash_deposit_restraint_3' must be set before inserting.");
		if ( !cash_deposit_restraint_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'cash_deposit_restraint_4' must be set before inserting.");
		if ( !cash_deposit_restraint_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'cash_deposit_restraint_5' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("calc_result_equity_id",new Long(calc_result_equity_id));
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
		map.put("trust_security_asset_0",new Double(trust_security_asset_0));
		map.put("trust_security_asset_1",new Double(trust_security_asset_1));
		map.put("trust_security_asset_2",new Double(trust_security_asset_2));
		map.put("trust_security_asset_3",new Double(trust_security_asset_3));
		map.put("trust_security_asset_4",new Double(trust_security_asset_4));
		map.put("trust_security_asset_5",new Double(trust_security_asset_5));
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		map.put("cash_deposit_restraint_0",new Double(cash_deposit_restraint_0));
		map.put("cash_deposit_restraint_1",new Double(cash_deposit_restraint_1));
		map.put("cash_deposit_restraint_2",new Double(cash_deposit_restraint_2));
		map.put("cash_deposit_restraint_3",new Double(cash_deposit_restraint_3));
		map.put("cash_deposit_restraint_4",new Double(cash_deposit_restraint_4));
		map.put("cash_deposit_restraint_5",new Double(cash_deposit_restraint_5));
		if ( day_trade_restraint_5 != null )
			map.put("day_trade_restraint_5",day_trade_restraint_5);
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
		if ( trust_security_asset_0_is_modified )
			map.put("trust_security_asset_0",new Double(trust_security_asset_0));
		if ( trust_security_asset_1_is_modified )
			map.put("trust_security_asset_1",new Double(trust_security_asset_1));
		if ( trust_security_asset_2_is_modified )
			map.put("trust_security_asset_2",new Double(trust_security_asset_2));
		if ( trust_security_asset_3_is_modified )
			map.put("trust_security_asset_3",new Double(trust_security_asset_3));
		if ( trust_security_asset_4_is_modified )
			map.put("trust_security_asset_4",new Double(trust_security_asset_4));
		if ( trust_security_asset_5_is_modified )
			map.put("trust_security_asset_5",new Double(trust_security_asset_5));
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( cash_deposit_restraint_0_is_modified )
			map.put("cash_deposit_restraint_0",new Double(cash_deposit_restraint_0));
		if ( cash_deposit_restraint_1_is_modified )
			map.put("cash_deposit_restraint_1",new Double(cash_deposit_restraint_1));
		if ( cash_deposit_restraint_2_is_modified )
			map.put("cash_deposit_restraint_2",new Double(cash_deposit_restraint_2));
		if ( cash_deposit_restraint_3_is_modified )
			map.put("cash_deposit_restraint_3",new Double(cash_deposit_restraint_3));
		if ( cash_deposit_restraint_4_is_modified )
			map.put("cash_deposit_restraint_4",new Double(cash_deposit_restraint_4));
		if ( cash_deposit_restraint_5_is_modified )
			map.put("cash_deposit_restraint_5",new Double(cash_deposit_restraint_5));
		if ( day_trade_restraint_5_is_modified )
			map.put("day_trade_restraint_5",day_trade_restraint_5);
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
			if ( trust_security_asset_0_is_set )
				map.put("trust_security_asset_0",new Double(trust_security_asset_0));
			if ( trust_security_asset_1_is_set )
				map.put("trust_security_asset_1",new Double(trust_security_asset_1));
			if ( trust_security_asset_2_is_set )
				map.put("trust_security_asset_2",new Double(trust_security_asset_2));
			if ( trust_security_asset_3_is_set )
				map.put("trust_security_asset_3",new Double(trust_security_asset_3));
			if ( trust_security_asset_4_is_set )
				map.put("trust_security_asset_4",new Double(trust_security_asset_4));
			if ( trust_security_asset_5_is_set )
				map.put("trust_security_asset_5",new Double(trust_security_asset_5));
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
			if ( cash_deposit_restraint_0_is_set )
				map.put("cash_deposit_restraint_0",new Double(cash_deposit_restraint_0));
			if ( cash_deposit_restraint_1_is_set )
				map.put("cash_deposit_restraint_1",new Double(cash_deposit_restraint_1));
			if ( cash_deposit_restraint_2_is_set )
				map.put("cash_deposit_restraint_2",new Double(cash_deposit_restraint_2));
			if ( cash_deposit_restraint_3_is_set )
				map.put("cash_deposit_restraint_3",new Double(cash_deposit_restraint_3));
			if ( cash_deposit_restraint_4_is_set )
				map.put("cash_deposit_restraint_4",new Double(cash_deposit_restraint_4));
			if ( cash_deposit_restraint_5_is_set )
				map.put("cash_deposit_restraint_5",new Double(cash_deposit_restraint_5));
			map.put("day_trade_restraint_5",day_trade_restraint_5);
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
   * <em>trust_security_asset_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTrustSecurityAsset0()
  {
    return trust_security_asset_0;
  }


  /** 
   * <em>trust_security_asset_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrustSecurityAsset0IsSet() {
    return trust_security_asset_0_is_set;
  }


  /** 
   * <em>trust_security_asset_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrustSecurityAsset0IsModified() {
    return trust_security_asset_0_is_modified;
  }


  /** 
   * <em>trust_security_asset_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTrustSecurityAsset1()
  {
    return trust_security_asset_1;
  }


  /** 
   * <em>trust_security_asset_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrustSecurityAsset1IsSet() {
    return trust_security_asset_1_is_set;
  }


  /** 
   * <em>trust_security_asset_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrustSecurityAsset1IsModified() {
    return trust_security_asset_1_is_modified;
  }


  /** 
   * <em>trust_security_asset_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTrustSecurityAsset2()
  {
    return trust_security_asset_2;
  }


  /** 
   * <em>trust_security_asset_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrustSecurityAsset2IsSet() {
    return trust_security_asset_2_is_set;
  }


  /** 
   * <em>trust_security_asset_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrustSecurityAsset2IsModified() {
    return trust_security_asset_2_is_modified;
  }


  /** 
   * <em>trust_security_asset_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTrustSecurityAsset3()
  {
    return trust_security_asset_3;
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
   * @@return doubleの値 
   */
  public final double getTrustSecurityAsset4()
  {
    return trust_security_asset_4;
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
   * <em>trust_security_asset_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTrustSecurityAsset5()
  {
    return trust_security_asset_5;
  }


  /** 
   * <em>trust_security_asset_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrustSecurityAsset5IsSet() {
    return trust_security_asset_5_is_set;
  }


  /** 
   * <em>trust_security_asset_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTrustSecurityAsset5IsModified() {
    return trust_security_asset_5_is_modified;
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
   * <em>cash_deposit_restraint_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashDepositRestraint0()
  {
    return cash_deposit_restraint_0;
  }


  /** 
   * <em>cash_deposit_restraint_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashDepositRestraint0IsSet() {
    return cash_deposit_restraint_0_is_set;
  }


  /** 
   * <em>cash_deposit_restraint_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashDepositRestraint0IsModified() {
    return cash_deposit_restraint_0_is_modified;
  }


  /** 
   * <em>cash_deposit_restraint_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashDepositRestraint1()
  {
    return cash_deposit_restraint_1;
  }


  /** 
   * <em>cash_deposit_restraint_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashDepositRestraint1IsSet() {
    return cash_deposit_restraint_1_is_set;
  }


  /** 
   * <em>cash_deposit_restraint_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashDepositRestraint1IsModified() {
    return cash_deposit_restraint_1_is_modified;
  }


  /** 
   * <em>cash_deposit_restraint_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashDepositRestraint2()
  {
    return cash_deposit_restraint_2;
  }


  /** 
   * <em>cash_deposit_restraint_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashDepositRestraint2IsSet() {
    return cash_deposit_restraint_2_is_set;
  }


  /** 
   * <em>cash_deposit_restraint_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashDepositRestraint2IsModified() {
    return cash_deposit_restraint_2_is_modified;
  }


  /** 
   * <em>cash_deposit_restraint_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashDepositRestraint3()
  {
    return cash_deposit_restraint_3;
  }


  /** 
   * <em>cash_deposit_restraint_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashDepositRestraint3IsSet() {
    return cash_deposit_restraint_3_is_set;
  }


  /** 
   * <em>cash_deposit_restraint_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashDepositRestraint3IsModified() {
    return cash_deposit_restraint_3_is_modified;
  }


  /** 
   * <em>cash_deposit_restraint_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashDepositRestraint4()
  {
    return cash_deposit_restraint_4;
  }


  /** 
   * <em>cash_deposit_restraint_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashDepositRestraint4IsSet() {
    return cash_deposit_restraint_4_is_set;
  }


  /** 
   * <em>cash_deposit_restraint_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashDepositRestraint4IsModified() {
    return cash_deposit_restraint_4_is_modified;
  }


  /** 
   * <em>cash_deposit_restraint_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashDepositRestraint5()
  {
    return cash_deposit_restraint_5;
  }


  /** 
   * <em>cash_deposit_restraint_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashDepositRestraint5IsSet() {
    return cash_deposit_restraint_5_is_set;
  }


  /** 
   * <em>cash_deposit_restraint_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashDepositRestraint5IsModified() {
    return cash_deposit_restraint_5_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new TpCalcResultEquityPK(calc_result_equity_id);
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
   * <em>trust_security_asset_0</em>カラムの値を設定します。 
   *
   * @@param p_trustSecurityAsset0 <em>trust_security_asset_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTrustSecurityAsset0( double p_trustSecurityAsset0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trust_security_asset_0 = p_trustSecurityAsset0;
    trust_security_asset_0_is_set = true;
    trust_security_asset_0_is_modified = true;
  }


  /** 
   * <em>trust_security_asset_1</em>カラムの値を設定します。 
   *
   * @@param p_trustSecurityAsset1 <em>trust_security_asset_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTrustSecurityAsset1( double p_trustSecurityAsset1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trust_security_asset_1 = p_trustSecurityAsset1;
    trust_security_asset_1_is_set = true;
    trust_security_asset_1_is_modified = true;
  }


  /** 
   * <em>trust_security_asset_2</em>カラムの値を設定します。 
   *
   * @@param p_trustSecurityAsset2 <em>trust_security_asset_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTrustSecurityAsset2( double p_trustSecurityAsset2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trust_security_asset_2 = p_trustSecurityAsset2;
    trust_security_asset_2_is_set = true;
    trust_security_asset_2_is_modified = true;
  }


  /** 
   * <em>trust_security_asset_3</em>カラムの値を設定します。 
   *
   * @@param p_trustSecurityAsset3 <em>trust_security_asset_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTrustSecurityAsset3( double p_trustSecurityAsset3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trust_security_asset_3 = p_trustSecurityAsset3;
    trust_security_asset_3_is_set = true;
    trust_security_asset_3_is_modified = true;
  }


  /** 
   * <em>trust_security_asset_4</em>カラムの値を設定します。 
   *
   * @@param p_trustSecurityAsset4 <em>trust_security_asset_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTrustSecurityAsset4( double p_trustSecurityAsset4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trust_security_asset_4 = p_trustSecurityAsset4;
    trust_security_asset_4_is_set = true;
    trust_security_asset_4_is_modified = true;
  }


  /** 
   * <em>trust_security_asset_5</em>カラムの値を設定します。 
   *
   * @@param p_trustSecurityAsset5 <em>trust_security_asset_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTrustSecurityAsset5( double p_trustSecurityAsset5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trust_security_asset_5 = p_trustSecurityAsset5;
    trust_security_asset_5_is_set = true;
    trust_security_asset_5_is_modified = true;
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
   * <em>cash_deposit_restraint_0</em>カラムの値を設定します。 
   *
   * @@param p_cashDepositRestraint0 <em>cash_deposit_restraint_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashDepositRestraint0( double p_cashDepositRestraint0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_deposit_restraint_0 = p_cashDepositRestraint0;
    cash_deposit_restraint_0_is_set = true;
    cash_deposit_restraint_0_is_modified = true;
  }


  /** 
   * <em>cash_deposit_restraint_1</em>カラムの値を設定します。 
   *
   * @@param p_cashDepositRestraint1 <em>cash_deposit_restraint_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashDepositRestraint1( double p_cashDepositRestraint1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_deposit_restraint_1 = p_cashDepositRestraint1;
    cash_deposit_restraint_1_is_set = true;
    cash_deposit_restraint_1_is_modified = true;
  }


  /** 
   * <em>cash_deposit_restraint_2</em>カラムの値を設定します。 
   *
   * @@param p_cashDepositRestraint2 <em>cash_deposit_restraint_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashDepositRestraint2( double p_cashDepositRestraint2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_deposit_restraint_2 = p_cashDepositRestraint2;
    cash_deposit_restraint_2_is_set = true;
    cash_deposit_restraint_2_is_modified = true;
  }


  /** 
   * <em>cash_deposit_restraint_3</em>カラムの値を設定します。 
   *
   * @@param p_cashDepositRestraint3 <em>cash_deposit_restraint_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashDepositRestraint3( double p_cashDepositRestraint3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_deposit_restraint_3 = p_cashDepositRestraint3;
    cash_deposit_restraint_3_is_set = true;
    cash_deposit_restraint_3_is_modified = true;
  }


  /** 
   * <em>cash_deposit_restraint_4</em>カラムの値を設定します。 
   *
   * @@param p_cashDepositRestraint4 <em>cash_deposit_restraint_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashDepositRestraint4( double p_cashDepositRestraint4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_deposit_restraint_4 = p_cashDepositRestraint4;
    cash_deposit_restraint_4_is_set = true;
    cash_deposit_restraint_4_is_modified = true;
  }


  /** 
   * <em>cash_deposit_restraint_5</em>カラムの値を設定します。 
   *
   * @@param p_cashDepositRestraint5 <em>cash_deposit_restraint_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashDepositRestraint5( double p_cashDepositRestraint5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_deposit_restraint_5 = p_cashDepositRestraint5;
    cash_deposit_restraint_5_is_set = true;
    cash_deposit_restraint_5_is_modified = true;
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
                if ( name.equals("calc_result_equity_id") ) {
                    return new Long( calc_result_equity_id );
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                else if ( name.equals("cash_deposit_restraint_0") ) {
                    return new Double( cash_deposit_restraint_0 );
                }
                else if ( name.equals("cash_deposit_restraint_1") ) {
                    return new Double( cash_deposit_restraint_1 );
                }
                else if ( name.equals("cash_deposit_restraint_2") ) {
                    return new Double( cash_deposit_restraint_2 );
                }
                else if ( name.equals("cash_deposit_restraint_3") ) {
                    return new Double( cash_deposit_restraint_3 );
                }
                else if ( name.equals("cash_deposit_restraint_4") ) {
                    return new Double( cash_deposit_restraint_4 );
                }
                else if ( name.equals("cash_deposit_restraint_5") ) {
                    return new Double( cash_deposit_restraint_5 );
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
                else if ( name.equals("day_trade_restraint_5") ) {
                    return this.day_trade_restraint_5;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
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
                else if ( name.equals("trust_security_asset_0") ) {
                    return new Double( trust_security_asset_0 );
                }
                else if ( name.equals("trust_security_asset_1") ) {
                    return new Double( trust_security_asset_1 );
                }
                else if ( name.equals("trust_security_asset_2") ) {
                    return new Double( trust_security_asset_2 );
                }
                else if ( name.equals("trust_security_asset_3") ) {
                    return new Double( trust_security_asset_3 );
                }
                else if ( name.equals("trust_security_asset_4") ) {
                    return new Double( trust_security_asset_4 );
                }
                else if ( name.equals("trust_security_asset_5") ) {
                    return new Double( trust_security_asset_5 );
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
                else if ( name.equals("cash_deposit_restraint_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_deposit_restraint_0' must be Double: '"+value+"' is not." );
                    this.cash_deposit_restraint_0 = ((Double) value).doubleValue();
                    if (this.cash_deposit_restraint_0_is_set)
                        this.cash_deposit_restraint_0_is_modified = true;
                    this.cash_deposit_restraint_0_is_set = true;
                    return;
                }
                else if ( name.equals("cash_deposit_restraint_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_deposit_restraint_1' must be Double: '"+value+"' is not." );
                    this.cash_deposit_restraint_1 = ((Double) value).doubleValue();
                    if (this.cash_deposit_restraint_1_is_set)
                        this.cash_deposit_restraint_1_is_modified = true;
                    this.cash_deposit_restraint_1_is_set = true;
                    return;
                }
                else if ( name.equals("cash_deposit_restraint_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_deposit_restraint_2' must be Double: '"+value+"' is not." );
                    this.cash_deposit_restraint_2 = ((Double) value).doubleValue();
                    if (this.cash_deposit_restraint_2_is_set)
                        this.cash_deposit_restraint_2_is_modified = true;
                    this.cash_deposit_restraint_2_is_set = true;
                    return;
                }
                else if ( name.equals("cash_deposit_restraint_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_deposit_restraint_3' must be Double: '"+value+"' is not." );
                    this.cash_deposit_restraint_3 = ((Double) value).doubleValue();
                    if (this.cash_deposit_restraint_3_is_set)
                        this.cash_deposit_restraint_3_is_modified = true;
                    this.cash_deposit_restraint_3_is_set = true;
                    return;
                }
                else if ( name.equals("cash_deposit_restraint_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_deposit_restraint_4' must be Double: '"+value+"' is not." );
                    this.cash_deposit_restraint_4 = ((Double) value).doubleValue();
                    if (this.cash_deposit_restraint_4_is_set)
                        this.cash_deposit_restraint_4_is_modified = true;
                    this.cash_deposit_restraint_4_is_set = true;
                    return;
                }
                else if ( name.equals("cash_deposit_restraint_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_deposit_restraint_5' must be Double: '"+value+"' is not." );
                    this.cash_deposit_restraint_5 = ((Double) value).doubleValue();
                    if (this.cash_deposit_restraint_5_is_set)
                        this.cash_deposit_restraint_5_is_modified = true;
                    this.cash_deposit_restraint_5_is_set = true;
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
                else if ( name.equals("trust_security_asset_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'trust_security_asset_0' must be Double: '"+value+"' is not." );
                    this.trust_security_asset_0 = ((Double) value).doubleValue();
                    if (this.trust_security_asset_0_is_set)
                        this.trust_security_asset_0_is_modified = true;
                    this.trust_security_asset_0_is_set = true;
                    return;
                }
                else if ( name.equals("trust_security_asset_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'trust_security_asset_1' must be Double: '"+value+"' is not." );
                    this.trust_security_asset_1 = ((Double) value).doubleValue();
                    if (this.trust_security_asset_1_is_set)
                        this.trust_security_asset_1_is_modified = true;
                    this.trust_security_asset_1_is_set = true;
                    return;
                }
                else if ( name.equals("trust_security_asset_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'trust_security_asset_2' must be Double: '"+value+"' is not." );
                    this.trust_security_asset_2 = ((Double) value).doubleValue();
                    if (this.trust_security_asset_2_is_set)
                        this.trust_security_asset_2_is_modified = true;
                    this.trust_security_asset_2_is_set = true;
                    return;
                }
                else if ( name.equals("trust_security_asset_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'trust_security_asset_3' must be Double: '"+value+"' is not." );
                    this.trust_security_asset_3 = ((Double) value).doubleValue();
                    if (this.trust_security_asset_3_is_set)
                        this.trust_security_asset_3_is_modified = true;
                    this.trust_security_asset_3_is_set = true;
                    return;
                }
                else if ( name.equals("trust_security_asset_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'trust_security_asset_4' must be Double: '"+value+"' is not." );
                    this.trust_security_asset_4 = ((Double) value).doubleValue();
                    if (this.trust_security_asset_4_is_set)
                        this.trust_security_asset_4_is_modified = true;
                    this.trust_security_asset_4_is_set = true;
                    return;
                }
                else if ( name.equals("trust_security_asset_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'trust_security_asset_5' must be Double: '"+value+"' is not." );
                    this.trust_security_asset_5 = ((Double) value).doubleValue();
                    if (this.trust_security_asset_5_is_set)
                        this.trust_security_asset_5_is_modified = true;
                    this.trust_security_asset_5_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
