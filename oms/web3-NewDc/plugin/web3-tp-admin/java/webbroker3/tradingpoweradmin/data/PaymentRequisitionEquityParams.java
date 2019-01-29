head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.58.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	PaymentRequisitionEquityParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpoweradmin.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * payment_requisition_equityテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link PaymentRequisitionEquityRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link PaymentRequisitionEquityParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link PaymentRequisitionEquityParams}が{@@link PaymentRequisitionEquityRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PaymentRequisitionEquityPK 
 * @@see PaymentRequisitionEquityRow 
 */
public class PaymentRequisitionEquityParams extends Params implements PaymentRequisitionEquityRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "payment_requisition_equity";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = PaymentRequisitionEquityRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return PaymentRequisitionEquityRow.TYPE;
  }


  /** 
   * <em>account_id</em>カラムの値 
   */
  public  long  account_id;

  /** 
   * <em>calc_date</em>カラムの値 
   */
  public  java.sql.Timestamp  calc_date;

  /** 
   * <em>calc_result_equity_id</em>カラムの値 
   */
  public  long  calc_result_equity_id;

  /** 
   * <em>family_name</em>カラムの値 
   */
  public  String  family_name;

  /** 
   * <em>account_attribute</em>カラムの値 
   */
  public  String  account_attribute;

  /** 
   * <em>sonar_trader_code</em>カラムの値 
   */
  public  String  sonar_trader_code;

  /** 
   * <em>trading_stop</em>カラムの値 
   */
  public  String  trading_stop;

  /** 
   * <em>ifo_open_position_stop</em>カラムの値 
   */
  public  String  ifo_open_position_stop;

  /** 
   * <em>payment_stop</em>カラムの値 
   */
  public  String  payment_stop;

  /** 
   * <em>other_trading_stop</em>カラムの値 
   */
  public  String  other_trading_stop;

  /** 
   * <em>payment_requisition_amount_0</em>カラムの値 
   */
  public  Double  payment_requisition_amount_0;

  /** 
   * <em>payment_requisition_amount_1</em>カラムの値 
   */
  public  Double  payment_requisition_amount_1;

  /** 
   * <em>payment_requisition_amount_2</em>カラムの値 
   */
  public  Double  payment_requisition_amount_2;

  /** 
   * <em>payment_requisition_amount_3</em>カラムの値 
   */
  public  Double  payment_requisition_amount_3;

  /** 
   * <em>payment_requisition_amount_4</em>カラムの値 
   */
  public  Double  payment_requisition_amount_4;

  /** 
   * <em>payment_requisition_amount_5</em>カラムの値 
   */
  public  Double  payment_requisition_amount_5;

  /** 
   * <em>payment_requisition_division_0</em>カラムの値 
   */
  public  Double  payment_requisition_division_0;

  /** 
   * <em>payment_requisition_division_1</em>カラムの値 
   */
  public  Double  payment_requisition_division_1;

  /** 
   * <em>payment_requisition_division_2</em>カラムの値 
   */
  public  Double  payment_requisition_division_2;

  /** 
   * <em>payment_requisition_division_3</em>カラムの値 
   */
  public  Double  payment_requisition_division_3;

  /** 
   * <em>payment_requisition_division_4</em>カラムの値 
   */
  public  Double  payment_requisition_division_4;

  /** 
   * <em>payment_requisition_division_5</em>カラムの値 
   */
  public  Double  payment_requisition_division_5;

  /** 
   * <em>account_balance_0</em>カラムの値 
   */
  public  Double  account_balance_0;

  /** 
   * <em>account_balance_1</em>カラムの値 
   */
  public  Double  account_balance_1;

  /** 
   * <em>account_balance_2</em>カラムの値 
   */
  public  Double  account_balance_2;

  /** 
   * <em>account_balance_3</em>カラムの値 
   */
  public  Double  account_balance_3;

  /** 
   * <em>account_balance_4</em>カラムの値 
   */
  public  Double  account_balance_4;

  /** 
   * <em>account_balance_5</em>カラムの値 
   */
  public  Double  account_balance_5;

  /** 
   * <em>cash_balance0</em>カラムの値 
   */
  public  Double  cash_balance0;

  /** 
   * <em>cash_balance1</em>カラムの値 
   */
  public  Double  cash_balance1;

  /** 
   * <em>cash_balance2</em>カラムの値 
   */
  public  Double  cash_balance2;

  /** 
   * <em>cash_balance3</em>カラムの値 
   */
  public  Double  cash_balance3;

  /** 
   * <em>cash_balance4</em>カラムの値 
   */
  public  Double  cash_balance4;

  /** 
   * <em>cash_balance5</em>カラムの値 
   */
  public  Double  cash_balance5;

  /** 
   * <em>day_trade_restraint_0</em>カラムの値 
   */
  public  Double  day_trade_restraint_0;

  /** 
   * <em>day_trade_restraint_1</em>カラムの値 
   */
  public  Double  day_trade_restraint_1;

  /** 
   * <em>day_trade_restraint_2</em>カラムの値 
   */
  public  Double  day_trade_restraint_2;

  /** 
   * <em>day_trade_restraint_3</em>カラムの値 
   */
  public  Double  day_trade_restraint_3;

  /** 
   * <em>day_trade_restraint_4</em>カラムの値 
   */
  public  Double  day_trade_restraint_4;

  /** 
   * <em>other_restraint_0</em>カラムの値 
   */
  public  Double  other_restraint_0;

  /** 
   * <em>other_restraint_1</em>カラムの値 
   */
  public  Double  other_restraint_1;

  /** 
   * <em>other_restraint_2</em>カラムの値 
   */
  public  Double  other_restraint_2;

  /** 
   * <em>other_restraint_3</em>カラムの値 
   */
  public  Double  other_restraint_3;

  /** 
   * <em>other_restraint_4</em>カラムの値 
   */
  public  Double  other_restraint_4;

  /** 
   * <em>other_restraint_5</em>カラムの値 
   */
  public  Double  other_restraint_5;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>branch_code</em>カラムの値 
   */
  public  String  branch_code;

  /** 
   * <em>account_code</em>カラムの値 
   */
  public  String  account_code;

  private boolean calc_result_equity_id_is_set = false;
  private boolean calc_result_equity_id_is_modified = false;
  private boolean account_id_is_set = false;
  private boolean account_id_is_modified = false;
  private boolean family_name_is_set = false;
  private boolean family_name_is_modified = false;
  private boolean account_attribute_is_set = false;
  private boolean account_attribute_is_modified = false;
  private boolean sonar_trader_code_is_set = false;
  private boolean sonar_trader_code_is_modified = false;
  private boolean calc_date_is_set = false;
  private boolean calc_date_is_modified = false;
  private boolean trading_stop_is_set = false;
  private boolean trading_stop_is_modified = false;
  private boolean ifo_open_position_stop_is_set = false;
  private boolean ifo_open_position_stop_is_modified = false;
  private boolean payment_stop_is_set = false;
  private boolean payment_stop_is_modified = false;
  private boolean other_trading_stop_is_set = false;
  private boolean other_trading_stop_is_modified = false;
  private boolean payment_requisition_amount_0_is_set = false;
  private boolean payment_requisition_amount_0_is_modified = false;
  private boolean payment_requisition_amount_1_is_set = false;
  private boolean payment_requisition_amount_1_is_modified = false;
  private boolean payment_requisition_amount_2_is_set = false;
  private boolean payment_requisition_amount_2_is_modified = false;
  private boolean payment_requisition_amount_3_is_set = false;
  private boolean payment_requisition_amount_3_is_modified = false;
  private boolean payment_requisition_amount_4_is_set = false;
  private boolean payment_requisition_amount_4_is_modified = false;
  private boolean payment_requisition_amount_5_is_set = false;
  private boolean payment_requisition_amount_5_is_modified = false;
  private boolean payment_requisition_division_0_is_set = false;
  private boolean payment_requisition_division_0_is_modified = false;
  private boolean payment_requisition_division_1_is_set = false;
  private boolean payment_requisition_division_1_is_modified = false;
  private boolean payment_requisition_division_2_is_set = false;
  private boolean payment_requisition_division_2_is_modified = false;
  private boolean payment_requisition_division_3_is_set = false;
  private boolean payment_requisition_division_3_is_modified = false;
  private boolean payment_requisition_division_4_is_set = false;
  private boolean payment_requisition_division_4_is_modified = false;
  private boolean payment_requisition_division_5_is_set = false;
  private boolean payment_requisition_division_5_is_modified = false;
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
  private boolean cash_balance0_is_set = false;
  private boolean cash_balance0_is_modified = false;
  private boolean cash_balance1_is_set = false;
  private boolean cash_balance1_is_modified = false;
  private boolean cash_balance2_is_set = false;
  private boolean cash_balance2_is_modified = false;
  private boolean cash_balance3_is_set = false;
  private boolean cash_balance3_is_modified = false;
  private boolean cash_balance4_is_set = false;
  private boolean cash_balance4_is_modified = false;
  private boolean cash_balance5_is_set = false;
  private boolean cash_balance5_is_modified = false;
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
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "account_id=" + account_id
      + "," + "calc_date=" + calc_date
      + "," + "calc_result_equity_id=" +calc_result_equity_id
      + "," + "family_name=" +family_name
      + "," + "account_attribute=" +account_attribute
      + "," + "sonar_trader_code=" +sonar_trader_code
      + "," + "trading_stop=" +trading_stop
      + "," + "ifo_open_position_stop=" +ifo_open_position_stop
      + "," + "payment_stop=" +payment_stop
      + "," + "other_trading_stop=" +other_trading_stop
      + "," + "payment_requisition_amount_0=" +payment_requisition_amount_0
      + "," + "payment_requisition_amount_1=" +payment_requisition_amount_1
      + "," + "payment_requisition_amount_2=" +payment_requisition_amount_2
      + "," + "payment_requisition_amount_3=" +payment_requisition_amount_3
      + "," + "payment_requisition_amount_4=" +payment_requisition_amount_4
      + "," + "payment_requisition_amount_5=" +payment_requisition_amount_5
      + "," + "payment_requisition_division_0=" +payment_requisition_division_0
      + "," + "payment_requisition_division_1=" +payment_requisition_division_1
      + "," + "payment_requisition_division_2=" +payment_requisition_division_2
      + "," + "payment_requisition_division_3=" +payment_requisition_division_3
      + "," + "payment_requisition_division_4=" +payment_requisition_division_4
      + "," + "payment_requisition_division_5=" +payment_requisition_division_5
      + "," + "account_balance_0=" +account_balance_0
      + "," + "account_balance_1=" +account_balance_1
      + "," + "account_balance_2=" +account_balance_2
      + "," + "account_balance_3=" +account_balance_3
      + "," + "account_balance_4=" +account_balance_4
      + "," + "account_balance_5=" +account_balance_5
      + "," + "cash_balance0=" +cash_balance0
      + "," + "cash_balance1=" +cash_balance1
      + "," + "cash_balance2=" +cash_balance2
      + "," + "cash_balance3=" +cash_balance3
      + "," + "cash_balance4=" +cash_balance4
      + "," + "cash_balance5=" +cash_balance5
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
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "}";
  }


  /** 
   * 値が未設定のPaymentRequisitionEquityParamsオブジェクトを作成します。 
   */
  public PaymentRequisitionEquityParams() {
    account_id_is_modified = true;
    calc_date_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のPaymentRequisitionEquityRowオブジェクトの値を利用してPaymentRequisitionEquityParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するPaymentRequisitionEquityRowオブジェクト 
   */
  public PaymentRequisitionEquityParams( PaymentRequisitionEquityRow p_row )
  {
    account_id = p_row.getAccountId();
    account_id_is_set = p_row.getAccountIdIsSet();
    account_id_is_modified = p_row.getAccountIdIsModified();
    calc_date = p_row.getCalcDate();
    calc_date_is_set = p_row.getCalcDateIsSet();
    calc_date_is_modified = p_row.getCalcDateIsModified();
    calc_result_equity_id = p_row.getCalcResultEquityId();
    calc_result_equity_id_is_set = p_row.getCalcResultEquityIdIsSet();
    calc_result_equity_id_is_modified = p_row.getCalcResultEquityIdIsModified();
    family_name = p_row.getFamilyName();
    family_name_is_set = p_row.getFamilyNameIsSet();
    family_name_is_modified = p_row.getFamilyNameIsModified();
    account_attribute = p_row.getAccountAttribute();
    account_attribute_is_set = p_row.getAccountAttributeIsSet();
    account_attribute_is_modified = p_row.getAccountAttributeIsModified();
    sonar_trader_code = p_row.getSonarTraderCode();
    sonar_trader_code_is_set = p_row.getSonarTraderCodeIsSet();
    sonar_trader_code_is_modified = p_row.getSonarTraderCodeIsModified();
    trading_stop = p_row.getTradingStop();
    trading_stop_is_set = p_row.getTradingStopIsSet();
    trading_stop_is_modified = p_row.getTradingStopIsModified();
    ifo_open_position_stop = p_row.getIfoOpenPositionStop();
    ifo_open_position_stop_is_set = p_row.getIfoOpenPositionStopIsSet();
    ifo_open_position_stop_is_modified = p_row.getIfoOpenPositionStopIsModified();
    payment_stop = p_row.getPaymentStop();
    payment_stop_is_set = p_row.getPaymentStopIsSet();
    payment_stop_is_modified = p_row.getPaymentStopIsModified();
    other_trading_stop = p_row.getOtherTradingStop();
    other_trading_stop_is_set = p_row.getOtherTradingStopIsSet();
    other_trading_stop_is_modified = p_row.getOtherTradingStopIsModified();
    if ( !p_row.getPaymentRequisitionAmount0IsNull() )
      payment_requisition_amount_0 = new Double( p_row.getPaymentRequisitionAmount0() );
    payment_requisition_amount_0_is_set = p_row.getPaymentRequisitionAmount0IsSet();
    payment_requisition_amount_0_is_modified = p_row.getPaymentRequisitionAmount0IsModified();
    if ( !p_row.getPaymentRequisitionAmount1IsNull() )
      payment_requisition_amount_1 = new Double( p_row.getPaymentRequisitionAmount1() );
    payment_requisition_amount_1_is_set = p_row.getPaymentRequisitionAmount1IsSet();
    payment_requisition_amount_1_is_modified = p_row.getPaymentRequisitionAmount1IsModified();
    if ( !p_row.getPaymentRequisitionAmount2IsNull() )
      payment_requisition_amount_2 = new Double( p_row.getPaymentRequisitionAmount2() );
    payment_requisition_amount_2_is_set = p_row.getPaymentRequisitionAmount2IsSet();
    payment_requisition_amount_2_is_modified = p_row.getPaymentRequisitionAmount2IsModified();
    if ( !p_row.getPaymentRequisitionAmount3IsNull() )
      payment_requisition_amount_3 = new Double( p_row.getPaymentRequisitionAmount3() );
    payment_requisition_amount_3_is_set = p_row.getPaymentRequisitionAmount3IsSet();
    payment_requisition_amount_3_is_modified = p_row.getPaymentRequisitionAmount3IsModified();
    if ( !p_row.getPaymentRequisitionAmount4IsNull() )
      payment_requisition_amount_4 = new Double( p_row.getPaymentRequisitionAmount4() );
    payment_requisition_amount_4_is_set = p_row.getPaymentRequisitionAmount4IsSet();
    payment_requisition_amount_4_is_modified = p_row.getPaymentRequisitionAmount4IsModified();
    if ( !p_row.getPaymentRequisitionAmount5IsNull() )
      payment_requisition_amount_5 = new Double( p_row.getPaymentRequisitionAmount5() );
    payment_requisition_amount_5_is_set = p_row.getPaymentRequisitionAmount5IsSet();
    payment_requisition_amount_5_is_modified = p_row.getPaymentRequisitionAmount5IsModified();
    if ( !p_row.getPaymentRequisitionDivision0IsNull() )
      payment_requisition_division_0 = new Double( p_row.getPaymentRequisitionDivision0() );
    payment_requisition_division_0_is_set = p_row.getPaymentRequisitionDivision0IsSet();
    payment_requisition_division_0_is_modified = p_row.getPaymentRequisitionDivision0IsModified();
    if ( !p_row.getPaymentRequisitionDivision1IsNull() )
      payment_requisition_division_1 = new Double( p_row.getPaymentRequisitionDivision1() );
    payment_requisition_division_1_is_set = p_row.getPaymentRequisitionDivision1IsSet();
    payment_requisition_division_1_is_modified = p_row.getPaymentRequisitionDivision1IsModified();
    if ( !p_row.getPaymentRequisitionDivision2IsNull() )
      payment_requisition_division_2 = new Double( p_row.getPaymentRequisitionDivision2() );
    payment_requisition_division_2_is_set = p_row.getPaymentRequisitionDivision2IsSet();
    payment_requisition_division_2_is_modified = p_row.getPaymentRequisitionDivision2IsModified();
    if ( !p_row.getPaymentRequisitionDivision3IsNull() )
      payment_requisition_division_3 = new Double( p_row.getPaymentRequisitionDivision3() );
    payment_requisition_division_3_is_set = p_row.getPaymentRequisitionDivision3IsSet();
    payment_requisition_division_3_is_modified = p_row.getPaymentRequisitionDivision3IsModified();
    if ( !p_row.getPaymentRequisitionDivision4IsNull() )
      payment_requisition_division_4 = new Double( p_row.getPaymentRequisitionDivision4() );
    payment_requisition_division_4_is_set = p_row.getPaymentRequisitionDivision4IsSet();
    payment_requisition_division_4_is_modified = p_row.getPaymentRequisitionDivision4IsModified();
    if ( !p_row.getPaymentRequisitionDivision5IsNull() )
      payment_requisition_division_5 = new Double( p_row.getPaymentRequisitionDivision5() );
    payment_requisition_division_5_is_set = p_row.getPaymentRequisitionDivision5IsSet();
    payment_requisition_division_5_is_modified = p_row.getPaymentRequisitionDivision5IsModified();
    if ( !p_row.getAccountBalance0IsNull() )
      account_balance_0 = new Double( p_row.getAccountBalance0() );
    account_balance_0_is_set = p_row.getAccountBalance0IsSet();
    account_balance_0_is_modified = p_row.getAccountBalance0IsModified();
    if ( !p_row.getAccountBalance1IsNull() )
      account_balance_1 = new Double( p_row.getAccountBalance1() );
    account_balance_1_is_set = p_row.getAccountBalance1IsSet();
    account_balance_1_is_modified = p_row.getAccountBalance1IsModified();
    if ( !p_row.getAccountBalance2IsNull() )
      account_balance_2 = new Double( p_row.getAccountBalance2() );
    account_balance_2_is_set = p_row.getAccountBalance2IsSet();
    account_balance_2_is_modified = p_row.getAccountBalance2IsModified();
    if ( !p_row.getAccountBalance3IsNull() )
      account_balance_3 = new Double( p_row.getAccountBalance3() );
    account_balance_3_is_set = p_row.getAccountBalance3IsSet();
    account_balance_3_is_modified = p_row.getAccountBalance3IsModified();
    if ( !p_row.getAccountBalance4IsNull() )
      account_balance_4 = new Double( p_row.getAccountBalance4() );
    account_balance_4_is_set = p_row.getAccountBalance4IsSet();
    account_balance_4_is_modified = p_row.getAccountBalance4IsModified();
    if ( !p_row.getAccountBalance5IsNull() )
      account_balance_5 = new Double( p_row.getAccountBalance5() );
    account_balance_5_is_set = p_row.getAccountBalance5IsSet();
    account_balance_5_is_modified = p_row.getAccountBalance5IsModified();
    if ( !p_row.getCashBalance0IsNull() )
      cash_balance0 = new Double( p_row.getCashBalance0() );
    cash_balance0_is_set = p_row.getCashBalance0IsSet();
    cash_balance0_is_modified = p_row.getCashBalance0IsModified();
    if ( !p_row.getCashBalance1IsNull() )
      cash_balance1 = new Double( p_row.getCashBalance1() );
    cash_balance1_is_set = p_row.getCashBalance1IsSet();
    cash_balance1_is_modified = p_row.getCashBalance1IsModified();
    if ( !p_row.getCashBalance2IsNull() )
      cash_balance2 = new Double( p_row.getCashBalance2() );
    cash_balance2_is_set = p_row.getCashBalance2IsSet();
    cash_balance2_is_modified = p_row.getCashBalance2IsModified();
    if ( !p_row.getCashBalance3IsNull() )
      cash_balance3 = new Double( p_row.getCashBalance3() );
    cash_balance3_is_set = p_row.getCashBalance3IsSet();
    cash_balance3_is_modified = p_row.getCashBalance3IsModified();
    if ( !p_row.getCashBalance4IsNull() )
      cash_balance4 = new Double( p_row.getCashBalance4() );
    cash_balance4_is_set = p_row.getCashBalance4IsSet();
    cash_balance4_is_modified = p_row.getCashBalance4IsModified();
    if ( !p_row.getCashBalance5IsNull() )
      cash_balance5 = new Double( p_row.getCashBalance5() );
    cash_balance5_is_set = p_row.getCashBalance5IsSet();
    cash_balance5_is_modified = p_row.getCashBalance5IsModified();
    if ( !p_row.getDayTradeRestraint0IsNull() )
      day_trade_restraint_0 = new Double( p_row.getDayTradeRestraint0() );
    day_trade_restraint_0_is_set = p_row.getDayTradeRestraint0IsSet();
    day_trade_restraint_0_is_modified = p_row.getDayTradeRestraint0IsModified();
    if ( !p_row.getDayTradeRestraint1IsNull() )
      day_trade_restraint_1 = new Double( p_row.getDayTradeRestraint1() );
    day_trade_restraint_1_is_set = p_row.getDayTradeRestraint1IsSet();
    day_trade_restraint_1_is_modified = p_row.getDayTradeRestraint1IsModified();
    if ( !p_row.getDayTradeRestraint2IsNull() )
      day_trade_restraint_2 = new Double( p_row.getDayTradeRestraint2() );
    day_trade_restraint_2_is_set = p_row.getDayTradeRestraint2IsSet();
    day_trade_restraint_2_is_modified = p_row.getDayTradeRestraint2IsModified();
    if ( !p_row.getDayTradeRestraint3IsNull() )
      day_trade_restraint_3 = new Double( p_row.getDayTradeRestraint3() );
    day_trade_restraint_3_is_set = p_row.getDayTradeRestraint3IsSet();
    day_trade_restraint_3_is_modified = p_row.getDayTradeRestraint3IsModified();
    if ( !p_row.getDayTradeRestraint4IsNull() )
      day_trade_restraint_4 = new Double( p_row.getDayTradeRestraint4() );
    day_trade_restraint_4_is_set = p_row.getDayTradeRestraint4IsSet();
    day_trade_restraint_4_is_modified = p_row.getDayTradeRestraint4IsModified();
    if ( !p_row.getOtherRestraint0IsNull() )
      other_restraint_0 = new Double( p_row.getOtherRestraint0() );
    other_restraint_0_is_set = p_row.getOtherRestraint0IsSet();
    other_restraint_0_is_modified = p_row.getOtherRestraint0IsModified();
    if ( !p_row.getOtherRestraint1IsNull() )
      other_restraint_1 = new Double( p_row.getOtherRestraint1() );
    other_restraint_1_is_set = p_row.getOtherRestraint1IsSet();
    other_restraint_1_is_modified = p_row.getOtherRestraint1IsModified();
    if ( !p_row.getOtherRestraint2IsNull() )
      other_restraint_2 = new Double( p_row.getOtherRestraint2() );
    other_restraint_2_is_set = p_row.getOtherRestraint2IsSet();
    other_restraint_2_is_modified = p_row.getOtherRestraint2IsModified();
    if ( !p_row.getOtherRestraint3IsNull() )
      other_restraint_3 = new Double( p_row.getOtherRestraint3() );
    other_restraint_3_is_set = p_row.getOtherRestraint3IsSet();
    other_restraint_3_is_modified = p_row.getOtherRestraint3IsModified();
    if ( !p_row.getOtherRestraint4IsNull() )
      other_restraint_4 = new Double( p_row.getOtherRestraint4() );
    other_restraint_4_is_set = p_row.getOtherRestraint4IsSet();
    other_restraint_4_is_modified = p_row.getOtherRestraint4IsModified();
    if ( !p_row.getOtherRestraint5IsNull() )
      other_restraint_5 = new Double( p_row.getOtherRestraint5() );
    other_restraint_5_is_set = p_row.getOtherRestraint5IsSet();
    other_restraint_5_is_modified = p_row.getOtherRestraint5IsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      calc_result_equity_id_is_set = true;
      calc_result_equity_id_is_modified = true;
      family_name_is_set = true;
      family_name_is_modified = true;
      account_attribute_is_set = true;
      account_attribute_is_modified = true;
      sonar_trader_code_is_set = true;
      sonar_trader_code_is_modified = true;
      trading_stop_is_set = true;
      trading_stop_is_modified = true;
      ifo_open_position_stop_is_set = true;
      ifo_open_position_stop_is_modified = true;
      payment_stop_is_set = true;
      payment_stop_is_modified = true;
      other_trading_stop_is_set = true;
      other_trading_stop_is_modified = true;
      payment_requisition_amount_0_is_set = true;
      payment_requisition_amount_0_is_modified = true;
      payment_requisition_amount_1_is_set = true;
      payment_requisition_amount_1_is_modified = true;
      payment_requisition_amount_2_is_set = true;
      payment_requisition_amount_2_is_modified = true;
      payment_requisition_amount_3_is_set = true;
      payment_requisition_amount_3_is_modified = true;
      payment_requisition_amount_4_is_set = true;
      payment_requisition_amount_4_is_modified = true;
      payment_requisition_amount_5_is_set = true;
      payment_requisition_amount_5_is_modified = true;
      payment_requisition_division_0_is_set = true;
      payment_requisition_division_0_is_modified = true;
      payment_requisition_division_1_is_set = true;
      payment_requisition_division_1_is_modified = true;
      payment_requisition_division_2_is_set = true;
      payment_requisition_division_2_is_modified = true;
      payment_requisition_division_3_is_set = true;
      payment_requisition_division_3_is_modified = true;
      payment_requisition_division_4_is_set = true;
      payment_requisition_division_4_is_modified = true;
      payment_requisition_division_5_is_set = true;
      payment_requisition_division_5_is_modified = true;
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
      cash_balance0_is_set = true;
      cash_balance0_is_modified = true;
      cash_balance1_is_set = true;
      cash_balance1_is_modified = true;
      cash_balance2_is_set = true;
      cash_balance2_is_modified = true;
      cash_balance3_is_set = true;
      cash_balance3_is_modified = true;
      cash_balance4_is_set = true;
      cash_balance4_is_modified = true;
      cash_balance5_is_set = true;
      cash_balance5_is_modified = true;
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
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof PaymentRequisitionEquityRow ) )
       return false;
    return fieldsEqual( (PaymentRequisitionEquityRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のPaymentRequisitionEquityRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( PaymentRequisitionEquityRow row )
  {
    if ( calc_result_equity_id != row.getCalcResultEquityId() )
      return false;
    if ( account_id != row.getAccountId() )
      return false;
    if ( family_name == null ) {
      if ( row.getFamilyName() != null )
        return false;
    } else if ( !family_name.equals( row.getFamilyName() ) ) {
        return false;
    }
    if ( account_attribute == null ) {
      if ( row.getAccountAttribute() != null )
        return false;
    } else if ( !account_attribute.equals( row.getAccountAttribute() ) ) {
        return false;
    }
    if ( sonar_trader_code == null ) {
      if ( row.getSonarTraderCode() != null )
        return false;
    } else if ( !sonar_trader_code.equals( row.getSonarTraderCode() ) ) {
        return false;
    }
    if ( calc_date == null ) {
      if ( row.getCalcDate() != null )
        return false;
    } else if ( !calc_date.equals( row.getCalcDate() ) ) {
        return false;
    }
    if ( trading_stop == null ) {
      if ( row.getTradingStop() != null )
        return false;
    } else if ( !trading_stop.equals( row.getTradingStop() ) ) {
        return false;
    }
    if ( ifo_open_position_stop == null ) {
      if ( row.getIfoOpenPositionStop() != null )
        return false;
    } else if ( !ifo_open_position_stop.equals( row.getIfoOpenPositionStop() ) ) {
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
    if ( payment_requisition_amount_0 == null ) {
      if ( !row.getPaymentRequisitionAmount0IsNull() )
        return false;
    } else if ( row.getPaymentRequisitionAmount0IsNull() || ( payment_requisition_amount_0.doubleValue() != row.getPaymentRequisitionAmount0() ) ) {
        return false;
    }
    if ( payment_requisition_amount_1 == null ) {
      if ( !row.getPaymentRequisitionAmount1IsNull() )
        return false;
    } else if ( row.getPaymentRequisitionAmount1IsNull() || ( payment_requisition_amount_1.doubleValue() != row.getPaymentRequisitionAmount1() ) ) {
        return false;
    }
    if ( payment_requisition_amount_2 == null ) {
      if ( !row.getPaymentRequisitionAmount2IsNull() )
        return false;
    } else if ( row.getPaymentRequisitionAmount2IsNull() || ( payment_requisition_amount_2.doubleValue() != row.getPaymentRequisitionAmount2() ) ) {
        return false;
    }
    if ( payment_requisition_amount_3 == null ) {
      if ( !row.getPaymentRequisitionAmount3IsNull() )
        return false;
    } else if ( row.getPaymentRequisitionAmount3IsNull() || ( payment_requisition_amount_3.doubleValue() != row.getPaymentRequisitionAmount3() ) ) {
        return false;
    }
    if ( payment_requisition_amount_4 == null ) {
      if ( !row.getPaymentRequisitionAmount4IsNull() )
        return false;
    } else if ( row.getPaymentRequisitionAmount4IsNull() || ( payment_requisition_amount_4.doubleValue() != row.getPaymentRequisitionAmount4() ) ) {
        return false;
    }
    if ( payment_requisition_amount_5 == null ) {
      if ( !row.getPaymentRequisitionAmount5IsNull() )
        return false;
    } else if ( row.getPaymentRequisitionAmount5IsNull() || ( payment_requisition_amount_5.doubleValue() != row.getPaymentRequisitionAmount5() ) ) {
        return false;
    }
    if ( payment_requisition_division_0 == null ) {
      if ( !row.getPaymentRequisitionDivision0IsNull() )
        return false;
    } else if ( row.getPaymentRequisitionDivision0IsNull() || ( payment_requisition_division_0.doubleValue() != row.getPaymentRequisitionDivision0() ) ) {
        return false;
    }
    if ( payment_requisition_division_1 == null ) {
      if ( !row.getPaymentRequisitionDivision1IsNull() )
        return false;
    } else if ( row.getPaymentRequisitionDivision1IsNull() || ( payment_requisition_division_1.doubleValue() != row.getPaymentRequisitionDivision1() ) ) {
        return false;
    }
    if ( payment_requisition_division_2 == null ) {
      if ( !row.getPaymentRequisitionDivision2IsNull() )
        return false;
    } else if ( row.getPaymentRequisitionDivision2IsNull() || ( payment_requisition_division_2.doubleValue() != row.getPaymentRequisitionDivision2() ) ) {
        return false;
    }
    if ( payment_requisition_division_3 == null ) {
      if ( !row.getPaymentRequisitionDivision3IsNull() )
        return false;
    } else if ( row.getPaymentRequisitionDivision3IsNull() || ( payment_requisition_division_3.doubleValue() != row.getPaymentRequisitionDivision3() ) ) {
        return false;
    }
    if ( payment_requisition_division_4 == null ) {
      if ( !row.getPaymentRequisitionDivision4IsNull() )
        return false;
    } else if ( row.getPaymentRequisitionDivision4IsNull() || ( payment_requisition_division_4.doubleValue() != row.getPaymentRequisitionDivision4() ) ) {
        return false;
    }
    if ( payment_requisition_division_5 == null ) {
      if ( !row.getPaymentRequisitionDivision5IsNull() )
        return false;
    } else if ( row.getPaymentRequisitionDivision5IsNull() || ( payment_requisition_division_5.doubleValue() != row.getPaymentRequisitionDivision5() ) ) {
        return false;
    }
    if ( account_balance_0 == null ) {
      if ( !row.getAccountBalance0IsNull() )
        return false;
    } else if ( row.getAccountBalance0IsNull() || ( account_balance_0.doubleValue() != row.getAccountBalance0() ) ) {
        return false;
    }
    if ( account_balance_1 == null ) {
      if ( !row.getAccountBalance1IsNull() )
        return false;
    } else if ( row.getAccountBalance1IsNull() || ( account_balance_1.doubleValue() != row.getAccountBalance1() ) ) {
        return false;
    }
    if ( account_balance_2 == null ) {
      if ( !row.getAccountBalance2IsNull() )
        return false;
    } else if ( row.getAccountBalance2IsNull() || ( account_balance_2.doubleValue() != row.getAccountBalance2() ) ) {
        return false;
    }
    if ( account_balance_3 == null ) {
      if ( !row.getAccountBalance3IsNull() )
        return false;
    } else if ( row.getAccountBalance3IsNull() || ( account_balance_3.doubleValue() != row.getAccountBalance3() ) ) {
        return false;
    }
    if ( account_balance_4 == null ) {
      if ( !row.getAccountBalance4IsNull() )
        return false;
    } else if ( row.getAccountBalance4IsNull() || ( account_balance_4.doubleValue() != row.getAccountBalance4() ) ) {
        return false;
    }
    if ( account_balance_5 == null ) {
      if ( !row.getAccountBalance5IsNull() )
        return false;
    } else if ( row.getAccountBalance5IsNull() || ( account_balance_5.doubleValue() != row.getAccountBalance5() ) ) {
        return false;
    }
    if ( cash_balance0 == null ) {
      if ( !row.getCashBalance0IsNull() )
        return false;
    } else if ( row.getCashBalance0IsNull() || ( cash_balance0.doubleValue() != row.getCashBalance0() ) ) {
        return false;
    }
    if ( cash_balance1 == null ) {
      if ( !row.getCashBalance1IsNull() )
        return false;
    } else if ( row.getCashBalance1IsNull() || ( cash_balance1.doubleValue() != row.getCashBalance1() ) ) {
        return false;
    }
    if ( cash_balance2 == null ) {
      if ( !row.getCashBalance2IsNull() )
        return false;
    } else if ( row.getCashBalance2IsNull() || ( cash_balance2.doubleValue() != row.getCashBalance2() ) ) {
        return false;
    }
    if ( cash_balance3 == null ) {
      if ( !row.getCashBalance3IsNull() )
        return false;
    } else if ( row.getCashBalance3IsNull() || ( cash_balance3.doubleValue() != row.getCashBalance3() ) ) {
        return false;
    }
    if ( cash_balance4 == null ) {
      if ( !row.getCashBalance4IsNull() )
        return false;
    } else if ( row.getCashBalance4IsNull() || ( cash_balance4.doubleValue() != row.getCashBalance4() ) ) {
        return false;
    }
    if ( cash_balance5 == null ) {
      if ( !row.getCashBalance5IsNull() )
        return false;
    } else if ( row.getCashBalance5IsNull() || ( cash_balance5.doubleValue() != row.getCashBalance5() ) ) {
        return false;
    }
    if ( day_trade_restraint_0 == null ) {
      if ( !row.getDayTradeRestraint0IsNull() )
        return false;
    } else if ( row.getDayTradeRestraint0IsNull() || ( day_trade_restraint_0.doubleValue() != row.getDayTradeRestraint0() ) ) {
        return false;
    }
    if ( day_trade_restraint_1 == null ) {
      if ( !row.getDayTradeRestraint1IsNull() )
        return false;
    } else if ( row.getDayTradeRestraint1IsNull() || ( day_trade_restraint_1.doubleValue() != row.getDayTradeRestraint1() ) ) {
        return false;
    }
    if ( day_trade_restraint_2 == null ) {
      if ( !row.getDayTradeRestraint2IsNull() )
        return false;
    } else if ( row.getDayTradeRestraint2IsNull() || ( day_trade_restraint_2.doubleValue() != row.getDayTradeRestraint2() ) ) {
        return false;
    }
    if ( day_trade_restraint_3 == null ) {
      if ( !row.getDayTradeRestraint3IsNull() )
        return false;
    } else if ( row.getDayTradeRestraint3IsNull() || ( day_trade_restraint_3.doubleValue() != row.getDayTradeRestraint3() ) ) {
        return false;
    }
    if ( day_trade_restraint_4 == null ) {
      if ( !row.getDayTradeRestraint4IsNull() )
        return false;
    } else if ( row.getDayTradeRestraint4IsNull() || ( day_trade_restraint_4.doubleValue() != row.getDayTradeRestraint4() ) ) {
        return false;
    }
    if ( other_restraint_0 == null ) {
      if ( !row.getOtherRestraint0IsNull() )
        return false;
    } else if ( row.getOtherRestraint0IsNull() || ( other_restraint_0.doubleValue() != row.getOtherRestraint0() ) ) {
        return false;
    }
    if ( other_restraint_1 == null ) {
      if ( !row.getOtherRestraint1IsNull() )
        return false;
    } else if ( row.getOtherRestraint1IsNull() || ( other_restraint_1.doubleValue() != row.getOtherRestraint1() ) ) {
        return false;
    }
    if ( other_restraint_2 == null ) {
      if ( !row.getOtherRestraint2IsNull() )
        return false;
    } else if ( row.getOtherRestraint2IsNull() || ( other_restraint_2.doubleValue() != row.getOtherRestraint2() ) ) {
        return false;
    }
    if ( other_restraint_3 == null ) {
      if ( !row.getOtherRestraint3IsNull() )
        return false;
    } else if ( row.getOtherRestraint3IsNull() || ( other_restraint_3.doubleValue() != row.getOtherRestraint3() ) ) {
        return false;
    }
    if ( other_restraint_4 == null ) {
      if ( !row.getOtherRestraint4IsNull() )
        return false;
    } else if ( row.getOtherRestraint4IsNull() || ( other_restraint_4.doubleValue() != row.getOtherRestraint4() ) ) {
        return false;
    }
    if ( other_restraint_5 == null ) {
      if ( !row.getOtherRestraint5IsNull() )
        return false;
    } else if ( row.getOtherRestraint5IsNull() || ( other_restraint_5.doubleValue() != row.getOtherRestraint5() ) ) {
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
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
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
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) calc_result_equity_id)
        + ((int) account_id)
        + (family_name!=null? family_name.hashCode(): 0) 
        + (account_attribute!=null? account_attribute.hashCode(): 0) 
        + (sonar_trader_code!=null? sonar_trader_code.hashCode(): 0) 
        + (calc_date!=null? calc_date.hashCode(): 0) 
        + (trading_stop!=null? trading_stop.hashCode(): 0) 
        + (ifo_open_position_stop!=null? ifo_open_position_stop.hashCode(): 0) 
        + (payment_stop!=null? payment_stop.hashCode(): 0) 
        + (other_trading_stop!=null? other_trading_stop.hashCode(): 0) 
        + (payment_requisition_amount_0!=null? payment_requisition_amount_0.hashCode(): 0) 
        + (payment_requisition_amount_1!=null? payment_requisition_amount_1.hashCode(): 0) 
        + (payment_requisition_amount_2!=null? payment_requisition_amount_2.hashCode(): 0) 
        + (payment_requisition_amount_3!=null? payment_requisition_amount_3.hashCode(): 0) 
        + (payment_requisition_amount_4!=null? payment_requisition_amount_4.hashCode(): 0) 
        + (payment_requisition_amount_5!=null? payment_requisition_amount_5.hashCode(): 0) 
        + (payment_requisition_division_0!=null? payment_requisition_division_0.hashCode(): 0) 
        + (payment_requisition_division_1!=null? payment_requisition_division_1.hashCode(): 0) 
        + (payment_requisition_division_2!=null? payment_requisition_division_2.hashCode(): 0) 
        + (payment_requisition_division_3!=null? payment_requisition_division_3.hashCode(): 0) 
        + (payment_requisition_division_4!=null? payment_requisition_division_4.hashCode(): 0) 
        + (payment_requisition_division_5!=null? payment_requisition_division_5.hashCode(): 0) 
        + (account_balance_0!=null? account_balance_0.hashCode(): 0) 
        + (account_balance_1!=null? account_balance_1.hashCode(): 0) 
        + (account_balance_2!=null? account_balance_2.hashCode(): 0) 
        + (account_balance_3!=null? account_balance_3.hashCode(): 0) 
        + (account_balance_4!=null? account_balance_4.hashCode(): 0) 
        + (account_balance_5!=null? account_balance_5.hashCode(): 0) 
        + (cash_balance0!=null? cash_balance0.hashCode(): 0) 
        + (cash_balance1!=null? cash_balance1.hashCode(): 0) 
        + (cash_balance2!=null? cash_balance2.hashCode(): 0) 
        + (cash_balance3!=null? cash_balance3.hashCode(): 0) 
        + (cash_balance4!=null? cash_balance4.hashCode(): 0) 
        + (cash_balance5!=null? cash_balance5.hashCode(): 0) 
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
        + (other_restraint_5!=null? other_restraint_5.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !calc_result_equity_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'calc_result_equity_id' must be set before inserting.");
		if ( !family_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'family_name' must be set before inserting.");
		if ( !account_attribute_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_attribute' must be set before inserting.");
		if ( !trading_stop_is_set )
			throw new IllegalArgumentException("non-nullable field 'trading_stop' must be set before inserting.");
		if ( !ifo_open_position_stop_is_set )
			throw new IllegalArgumentException("non-nullable field 'ifo_open_position_stop' must be set before inserting.");
		if ( !payment_stop_is_set )
			throw new IllegalArgumentException("non-nullable field 'payment_stop' must be set before inserting.");
		if ( !other_trading_stop_is_set )
			throw new IllegalArgumentException("non-nullable field 'other_trading_stop' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("calc_result_equity_id",new Long(calc_result_equity_id));
		map.put("account_id",new Long(account_id));
		map.put("family_name",family_name);
		map.put("account_attribute",account_attribute);
		if ( sonar_trader_code != null )
			map.put("sonar_trader_code",sonar_trader_code);
		map.put("calc_date",calc_date);
		map.put("trading_stop",trading_stop);
		map.put("ifo_open_position_stop",ifo_open_position_stop);
		map.put("payment_stop",payment_stop);
		map.put("other_trading_stop",other_trading_stop);
		if ( payment_requisition_amount_0 != null )
			map.put("payment_requisition_amount_0",payment_requisition_amount_0);
		if ( payment_requisition_amount_1 != null )
			map.put("payment_requisition_amount_1",payment_requisition_amount_1);
		if ( payment_requisition_amount_2 != null )
			map.put("payment_requisition_amount_2",payment_requisition_amount_2);
		if ( payment_requisition_amount_3 != null )
			map.put("payment_requisition_amount_3",payment_requisition_amount_3);
		if ( payment_requisition_amount_4 != null )
			map.put("payment_requisition_amount_4",payment_requisition_amount_4);
		if ( payment_requisition_amount_5 != null )
			map.put("payment_requisition_amount_5",payment_requisition_amount_5);
		if ( payment_requisition_division_0 != null )
			map.put("payment_requisition_division_0",payment_requisition_division_0);
		if ( payment_requisition_division_1 != null )
			map.put("payment_requisition_division_1",payment_requisition_division_1);
		if ( payment_requisition_division_2 != null )
			map.put("payment_requisition_division_2",payment_requisition_division_2);
		if ( payment_requisition_division_3 != null )
			map.put("payment_requisition_division_3",payment_requisition_division_3);
		if ( payment_requisition_division_4 != null )
			map.put("payment_requisition_division_4",payment_requisition_division_4);
		if ( payment_requisition_division_5 != null )
			map.put("payment_requisition_division_5",payment_requisition_division_5);
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
		if ( account_balance_5 != null )
			map.put("account_balance_5",account_balance_5);
		if ( cash_balance0 != null )
			map.put("cash_balance0",cash_balance0);
		if ( cash_balance1 != null )
			map.put("cash_balance1",cash_balance1);
		if ( cash_balance2 != null )
			map.put("cash_balance2",cash_balance2);
		if ( cash_balance3 != null )
			map.put("cash_balance3",cash_balance3);
		if ( cash_balance4 != null )
			map.put("cash_balance4",cash_balance4);
		if ( cash_balance5 != null )
			map.put("cash_balance5",cash_balance5);
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
		if ( other_restraint_5 != null )
			map.put("other_restraint_5",other_restraint_5);
		if ( created_timestamp != null )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp != null )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( institution_code != null )
			map.put("institution_code",institution_code);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( calc_result_equity_id_is_modified )
			map.put("calc_result_equity_id",new Long(calc_result_equity_id));
		if ( family_name_is_modified )
			map.put("family_name",family_name);
		if ( account_attribute_is_modified )
			map.put("account_attribute",account_attribute);
		if ( sonar_trader_code_is_modified )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( trading_stop_is_modified )
			map.put("trading_stop",trading_stop);
		if ( ifo_open_position_stop_is_modified )
			map.put("ifo_open_position_stop",ifo_open_position_stop);
		if ( payment_stop_is_modified )
			map.put("payment_stop",payment_stop);
		if ( other_trading_stop_is_modified )
			map.put("other_trading_stop",other_trading_stop);
		if ( payment_requisition_amount_0_is_modified )
			map.put("payment_requisition_amount_0",payment_requisition_amount_0);
		if ( payment_requisition_amount_1_is_modified )
			map.put("payment_requisition_amount_1",payment_requisition_amount_1);
		if ( payment_requisition_amount_2_is_modified )
			map.put("payment_requisition_amount_2",payment_requisition_amount_2);
		if ( payment_requisition_amount_3_is_modified )
			map.put("payment_requisition_amount_3",payment_requisition_amount_3);
		if ( payment_requisition_amount_4_is_modified )
			map.put("payment_requisition_amount_4",payment_requisition_amount_4);
		if ( payment_requisition_amount_5_is_modified )
			map.put("payment_requisition_amount_5",payment_requisition_amount_5);
		if ( payment_requisition_division_0_is_modified )
			map.put("payment_requisition_division_0",payment_requisition_division_0);
		if ( payment_requisition_division_1_is_modified )
			map.put("payment_requisition_division_1",payment_requisition_division_1);
		if ( payment_requisition_division_2_is_modified )
			map.put("payment_requisition_division_2",payment_requisition_division_2);
		if ( payment_requisition_division_3_is_modified )
			map.put("payment_requisition_division_3",payment_requisition_division_3);
		if ( payment_requisition_division_4_is_modified )
			map.put("payment_requisition_division_4",payment_requisition_division_4);
		if ( payment_requisition_division_5_is_modified )
			map.put("payment_requisition_division_5",payment_requisition_division_5);
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
		if ( account_balance_5_is_modified )
			map.put("account_balance_5",account_balance_5);
		if ( cash_balance0_is_modified )
			map.put("cash_balance0",cash_balance0);
		if ( cash_balance1_is_modified )
			map.put("cash_balance1",cash_balance1);
		if ( cash_balance2_is_modified )
			map.put("cash_balance2",cash_balance2);
		if ( cash_balance3_is_modified )
			map.put("cash_balance3",cash_balance3);
		if ( cash_balance4_is_modified )
			map.put("cash_balance4",cash_balance4);
		if ( cash_balance5_is_modified )
			map.put("cash_balance5",cash_balance5);
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
		if ( other_restraint_5_is_modified )
			map.put("other_restraint_5",other_restraint_5);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if (map.size() == 0) {
			if ( calc_result_equity_id_is_set )
				map.put("calc_result_equity_id",new Long(calc_result_equity_id));
			if ( family_name_is_set )
				map.put("family_name",family_name);
			if ( account_attribute_is_set )
				map.put("account_attribute",account_attribute);
			map.put("sonar_trader_code",sonar_trader_code);
			if ( trading_stop_is_set )
				map.put("trading_stop",trading_stop);
			if ( ifo_open_position_stop_is_set )
				map.put("ifo_open_position_stop",ifo_open_position_stop);
			if ( payment_stop_is_set )
				map.put("payment_stop",payment_stop);
			if ( other_trading_stop_is_set )
				map.put("other_trading_stop",other_trading_stop);
			map.put("payment_requisition_amount_0",payment_requisition_amount_0);
			map.put("payment_requisition_amount_1",payment_requisition_amount_1);
			map.put("payment_requisition_amount_2",payment_requisition_amount_2);
			map.put("payment_requisition_amount_3",payment_requisition_amount_3);
			map.put("payment_requisition_amount_4",payment_requisition_amount_4);
			map.put("payment_requisition_amount_5",payment_requisition_amount_5);
			map.put("payment_requisition_division_0",payment_requisition_division_0);
			map.put("payment_requisition_division_1",payment_requisition_division_1);
			map.put("payment_requisition_division_2",payment_requisition_division_2);
			map.put("payment_requisition_division_3",payment_requisition_division_3);
			map.put("payment_requisition_division_4",payment_requisition_division_4);
			map.put("payment_requisition_division_5",payment_requisition_division_5);
			map.put("account_balance_0",account_balance_0);
			map.put("account_balance_1",account_balance_1);
			map.put("account_balance_2",account_balance_2);
			map.put("account_balance_3",account_balance_3);
			map.put("account_balance_4",account_balance_4);
			map.put("account_balance_5",account_balance_5);
			map.put("cash_balance0",cash_balance0);
			map.put("cash_balance1",cash_balance1);
			map.put("cash_balance2",cash_balance2);
			map.put("cash_balance3",cash_balance3);
			map.put("cash_balance4",cash_balance4);
			map.put("cash_balance5",cash_balance5);
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
			map.put("other_restraint_5",other_restraint_5);
			map.put("created_timestamp",created_timestamp);
			map.put("last_updated_timestamp",last_updated_timestamp);
			map.put("institution_code",institution_code);
			map.put("branch_code",branch_code);
			map.put("account_code",account_code);
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
   * <em>family_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFamilyName()
  {
    return family_name;
  }


  /** 
   * <em>family_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameIsSet() {
    return family_name_is_set;
  }


  /** 
   * <em>family_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFamilyNameIsModified() {
    return family_name_is_modified;
  }


  /** 
   * <em>account_attribute</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAccountAttribute()
  {
    return account_attribute;
  }


  /** 
   * <em>account_attribute</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountAttributeIsSet() {
    return account_attribute_is_set;
  }


  /** 
   * <em>account_attribute</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAccountAttributeIsModified() {
    return account_attribute_is_modified;
  }


  /** 
   * <em>sonar_trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSonarTraderCode()
  {
    return sonar_trader_code;
  }


  /** 
   * <em>sonar_trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTraderCodeIsSet() {
    return sonar_trader_code_is_set;
  }


  /** 
   * <em>sonar_trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSonarTraderCodeIsModified() {
    return sonar_trader_code_is_modified;
  }


  /** 
   * <em>calc_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getCalcDate()
  {
    return calc_date;
  }


  /** 
   * <em>calc_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcDateIsSet() {
    return calc_date_is_set;
  }


  /** 
   * <em>calc_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCalcDateIsModified() {
    return calc_date_is_modified;
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
   * <em>ifo_open_position_stop</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getIfoOpenPositionStop()
  {
    return ifo_open_position_stop;
  }


  /** 
   * <em>ifo_open_position_stop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoOpenPositionStopIsSet() {
    return ifo_open_position_stop_is_set;
  }


  /** 
   * <em>ifo_open_position_stop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getIfoOpenPositionStopIsModified() {
    return ifo_open_position_stop_is_modified;
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
   * <em>payment_requisition_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentRequisitionAmount0()
  {
    return ( payment_requisition_amount_0==null? ((double)0): payment_requisition_amount_0.doubleValue() );
  }


  /** 
   * <em>payment_requisition_amount_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentRequisitionAmount0IsNull()
  {
    return payment_requisition_amount_0 == null;
  }


  /** 
   * <em>payment_requisition_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionAmount0IsSet() {
    return payment_requisition_amount_0_is_set;
  }


  /** 
   * <em>payment_requisition_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionAmount0IsModified() {
    return payment_requisition_amount_0_is_modified;
  }


  /** 
   * <em>payment_requisition_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentRequisitionAmount1()
  {
    return ( payment_requisition_amount_1==null? ((double)0): payment_requisition_amount_1.doubleValue() );
  }


  /** 
   * <em>payment_requisition_amount_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentRequisitionAmount1IsNull()
  {
    return payment_requisition_amount_1 == null;
  }


  /** 
   * <em>payment_requisition_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionAmount1IsSet() {
    return payment_requisition_amount_1_is_set;
  }


  /** 
   * <em>payment_requisition_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionAmount1IsModified() {
    return payment_requisition_amount_1_is_modified;
  }


  /** 
   * <em>payment_requisition_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentRequisitionAmount2()
  {
    return ( payment_requisition_amount_2==null? ((double)0): payment_requisition_amount_2.doubleValue() );
  }


  /** 
   * <em>payment_requisition_amount_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentRequisitionAmount2IsNull()
  {
    return payment_requisition_amount_2 == null;
  }


  /** 
   * <em>payment_requisition_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionAmount2IsSet() {
    return payment_requisition_amount_2_is_set;
  }


  /** 
   * <em>payment_requisition_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionAmount2IsModified() {
    return payment_requisition_amount_2_is_modified;
  }


  /** 
   * <em>payment_requisition_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentRequisitionAmount3()
  {
    return ( payment_requisition_amount_3==null? ((double)0): payment_requisition_amount_3.doubleValue() );
  }


  /** 
   * <em>payment_requisition_amount_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentRequisitionAmount3IsNull()
  {
    return payment_requisition_amount_3 == null;
  }


  /** 
   * <em>payment_requisition_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionAmount3IsSet() {
    return payment_requisition_amount_3_is_set;
  }


  /** 
   * <em>payment_requisition_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionAmount3IsModified() {
    return payment_requisition_amount_3_is_modified;
  }


  /** 
   * <em>payment_requisition_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentRequisitionAmount4()
  {
    return ( payment_requisition_amount_4==null? ((double)0): payment_requisition_amount_4.doubleValue() );
  }


  /** 
   * <em>payment_requisition_amount_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentRequisitionAmount4IsNull()
  {
    return payment_requisition_amount_4 == null;
  }


  /** 
   * <em>payment_requisition_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionAmount4IsSet() {
    return payment_requisition_amount_4_is_set;
  }


  /** 
   * <em>payment_requisition_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionAmount4IsModified() {
    return payment_requisition_amount_4_is_modified;
  }


  /** 
   * <em>payment_requisition_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentRequisitionAmount5()
  {
    return ( payment_requisition_amount_5==null? ((double)0): payment_requisition_amount_5.doubleValue() );
  }


  /** 
   * <em>payment_requisition_amount_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentRequisitionAmount5IsNull()
  {
    return payment_requisition_amount_5 == null;
  }


  /** 
   * <em>payment_requisition_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionAmount5IsSet() {
    return payment_requisition_amount_5_is_set;
  }


  /** 
   * <em>payment_requisition_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionAmount5IsModified() {
    return payment_requisition_amount_5_is_modified;
  }


  /** 
   * <em>payment_requisition_division_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentRequisitionDivision0()
  {
    return ( payment_requisition_division_0==null? ((double)0): payment_requisition_division_0.doubleValue() );
  }


  /** 
   * <em>payment_requisition_division_0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentRequisitionDivision0IsNull()
  {
    return payment_requisition_division_0 == null;
  }


  /** 
   * <em>payment_requisition_division_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionDivision0IsSet() {
    return payment_requisition_division_0_is_set;
  }


  /** 
   * <em>payment_requisition_division_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionDivision0IsModified() {
    return payment_requisition_division_0_is_modified;
  }


  /** 
   * <em>payment_requisition_division_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentRequisitionDivision1()
  {
    return ( payment_requisition_division_1==null? ((double)0): payment_requisition_division_1.doubleValue() );
  }


  /** 
   * <em>payment_requisition_division_1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentRequisitionDivision1IsNull()
  {
    return payment_requisition_division_1 == null;
  }


  /** 
   * <em>payment_requisition_division_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionDivision1IsSet() {
    return payment_requisition_division_1_is_set;
  }


  /** 
   * <em>payment_requisition_division_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionDivision1IsModified() {
    return payment_requisition_division_1_is_modified;
  }


  /** 
   * <em>payment_requisition_division_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentRequisitionDivision2()
  {
    return ( payment_requisition_division_2==null? ((double)0): payment_requisition_division_2.doubleValue() );
  }


  /** 
   * <em>payment_requisition_division_2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentRequisitionDivision2IsNull()
  {
    return payment_requisition_division_2 == null;
  }


  /** 
   * <em>payment_requisition_division_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionDivision2IsSet() {
    return payment_requisition_division_2_is_set;
  }


  /** 
   * <em>payment_requisition_division_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionDivision2IsModified() {
    return payment_requisition_division_2_is_modified;
  }


  /** 
   * <em>payment_requisition_division_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentRequisitionDivision3()
  {
    return ( payment_requisition_division_3==null? ((double)0): payment_requisition_division_3.doubleValue() );
  }


  /** 
   * <em>payment_requisition_division_3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentRequisitionDivision3IsNull()
  {
    return payment_requisition_division_3 == null;
  }


  /** 
   * <em>payment_requisition_division_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionDivision3IsSet() {
    return payment_requisition_division_3_is_set;
  }


  /** 
   * <em>payment_requisition_division_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionDivision3IsModified() {
    return payment_requisition_division_3_is_modified;
  }


  /** 
   * <em>payment_requisition_division_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentRequisitionDivision4()
  {
    return ( payment_requisition_division_4==null? ((double)0): payment_requisition_division_4.doubleValue() );
  }


  /** 
   * <em>payment_requisition_division_4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentRequisitionDivision4IsNull()
  {
    return payment_requisition_division_4 == null;
  }


  /** 
   * <em>payment_requisition_division_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionDivision4IsSet() {
    return payment_requisition_division_4_is_set;
  }


  /** 
   * <em>payment_requisition_division_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionDivision4IsModified() {
    return payment_requisition_division_4_is_modified;
  }


  /** 
   * <em>payment_requisition_division_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPaymentRequisitionDivision5()
  {
    return ( payment_requisition_division_5==null? ((double)0): payment_requisition_division_5.doubleValue() );
  }


  /** 
   * <em>payment_requisition_division_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPaymentRequisitionDivision5IsNull()
  {
    return payment_requisition_division_5 == null;
  }


  /** 
   * <em>payment_requisition_division_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionDivision5IsSet() {
    return payment_requisition_division_5_is_set;
  }


  /** 
   * <em>payment_requisition_division_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPaymentRequisitionDivision5IsModified() {
    return payment_requisition_division_5_is_modified;
  }


  /** 
   * <em>account_balance_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAccountBalance0()
  {
    return ( account_balance_0==null? ((double)0): account_balance_0.doubleValue() );
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
   * @@return doubleの値 
   */
  public final double getAccountBalance1()
  {
    return ( account_balance_1==null? ((double)0): account_balance_1.doubleValue() );
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
   * @@return doubleの値 
   */
  public final double getAccountBalance2()
  {
    return ( account_balance_2==null? ((double)0): account_balance_2.doubleValue() );
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
   * @@return doubleの値 
   */
  public final double getAccountBalance3()
  {
    return ( account_balance_3==null? ((double)0): account_balance_3.doubleValue() );
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
   * @@return doubleの値 
   */
  public final double getAccountBalance4()
  {
    return ( account_balance_4==null? ((double)0): account_balance_4.doubleValue() );
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
   * <em>account_balance_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getAccountBalance5()
  {
    return ( account_balance_5==null? ((double)0): account_balance_5.doubleValue() );
  }


  /** 
   * <em>account_balance_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAccountBalance5IsNull()
  {
    return account_balance_5 == null;
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
   * <em>cash_balance0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashBalance0()
  {
    return ( cash_balance0==null? ((double)0): cash_balance0.doubleValue() );
  }


  /** 
   * <em>cash_balance0</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashBalance0IsNull()
  {
    return cash_balance0 == null;
  }


  /** 
   * <em>cash_balance0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance0IsSet() {
    return cash_balance0_is_set;
  }


  /** 
   * <em>cash_balance0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance0IsModified() {
    return cash_balance0_is_modified;
  }


  /** 
   * <em>cash_balance1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashBalance1()
  {
    return ( cash_balance1==null? ((double)0): cash_balance1.doubleValue() );
  }


  /** 
   * <em>cash_balance1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashBalance1IsNull()
  {
    return cash_balance1 == null;
  }


  /** 
   * <em>cash_balance1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance1IsSet() {
    return cash_balance1_is_set;
  }


  /** 
   * <em>cash_balance1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance1IsModified() {
    return cash_balance1_is_modified;
  }


  /** 
   * <em>cash_balance2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashBalance2()
  {
    return ( cash_balance2==null? ((double)0): cash_balance2.doubleValue() );
  }


  /** 
   * <em>cash_balance2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashBalance2IsNull()
  {
    return cash_balance2 == null;
  }


  /** 
   * <em>cash_balance2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance2IsSet() {
    return cash_balance2_is_set;
  }


  /** 
   * <em>cash_balance2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance2IsModified() {
    return cash_balance2_is_modified;
  }


  /** 
   * <em>cash_balance3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashBalance3()
  {
    return ( cash_balance3==null? ((double)0): cash_balance3.doubleValue() );
  }


  /** 
   * <em>cash_balance3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashBalance3IsNull()
  {
    return cash_balance3 == null;
  }


  /** 
   * <em>cash_balance3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance3IsSet() {
    return cash_balance3_is_set;
  }


  /** 
   * <em>cash_balance3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance3IsModified() {
    return cash_balance3_is_modified;
  }


  /** 
   * <em>cash_balance4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashBalance4()
  {
    return ( cash_balance4==null? ((double)0): cash_balance4.doubleValue() );
  }


  /** 
   * <em>cash_balance4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashBalance4IsNull()
  {
    return cash_balance4 == null;
  }


  /** 
   * <em>cash_balance4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance4IsSet() {
    return cash_balance4_is_set;
  }


  /** 
   * <em>cash_balance4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance4IsModified() {
    return cash_balance4_is_modified;
  }


  /** 
   * <em>cash_balance5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCashBalance5()
  {
    return ( cash_balance5==null? ((double)0): cash_balance5.doubleValue() );
  }


  /** 
   * <em>cash_balance5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCashBalance5IsNull()
  {
    return cash_balance5 == null;
  }


  /** 
   * <em>cash_balance5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance5IsSet() {
    return cash_balance5_is_set;
  }


  /** 
   * <em>cash_balance5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCashBalance5IsModified() {
    return cash_balance5_is_modified;
  }


  /** 
   * <em>day_trade_restraint_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDayTradeRestraint0()
  {
    return ( day_trade_restraint_0==null? ((double)0): day_trade_restraint_0.doubleValue() );
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
   * @@return doubleの値 
   */
  public final double getDayTradeRestraint1()
  {
    return ( day_trade_restraint_1==null? ((double)0): day_trade_restraint_1.doubleValue() );
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
   * @@return doubleの値 
   */
  public final double getDayTradeRestraint2()
  {
    return ( day_trade_restraint_2==null? ((double)0): day_trade_restraint_2.doubleValue() );
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
   * @@return doubleの値 
   */
  public final double getDayTradeRestraint3()
  {
    return ( day_trade_restraint_3==null? ((double)0): day_trade_restraint_3.doubleValue() );
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
   * @@return doubleの値 
   */
  public final double getDayTradeRestraint4()
  {
    return ( day_trade_restraint_4==null? ((double)0): day_trade_restraint_4.doubleValue() );
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
   * @@return doubleの値 
   */
  public final double getOtherRestraint0()
  {
    return ( other_restraint_0==null? ((double)0): other_restraint_0.doubleValue() );
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
   * @@return doubleの値 
   */
  public final double getOtherRestraint1()
  {
    return ( other_restraint_1==null? ((double)0): other_restraint_1.doubleValue() );
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
   * @@return doubleの値 
   */
  public final double getOtherRestraint2()
  {
    return ( other_restraint_2==null? ((double)0): other_restraint_2.doubleValue() );
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
   * @@return doubleの値 
   */
  public final double getOtherRestraint3()
  {
    return ( other_restraint_3==null? ((double)0): other_restraint_3.doubleValue() );
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
   * @@return doubleの値 
   */
  public final double getOtherRestraint4()
  {
    return ( other_restraint_4==null? ((double)0): other_restraint_4.doubleValue() );
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
   * <em>other_restraint_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOtherRestraint5()
  {
    return ( other_restraint_5==null? ((double)0): other_restraint_5.doubleValue() );
  }


  /** 
   * <em>other_restraint_5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOtherRestraint5IsNull()
  {
    return other_restraint_5 == null;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new PaymentRequisitionEquityPK(account_id, calc_date);
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
   * <em>family_name</em>カラムの値を設定します。 
   *
   * @@param p_familyName <em>family_name</em>カラムの値をあらわす40桁以下のString値 
   */
  public final void setFamilyName( String p_familyName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    family_name = p_familyName;
    family_name_is_set = true;
    family_name_is_modified = true;
  }


  /** 
   * <em>account_attribute</em>カラムの値を設定します。 
   *
   * @@param p_accountAttribute <em>account_attribute</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAccountAttribute( String p_accountAttribute )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_attribute = p_accountAttribute;
    account_attribute_is_set = true;
    account_attribute_is_modified = true;
  }


  /** 
   * <em>sonar_trader_code</em>カラムの値を設定します。 
   *
   * @@param p_sonarTraderCode <em>sonar_trader_code</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setSonarTraderCode( String p_sonarTraderCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sonar_trader_code = p_sonarTraderCode;
    sonar_trader_code_is_set = true;
    sonar_trader_code_is_modified = true;
  }


  /** 
   * <em>calc_date</em>カラムの値を設定します。 
   *
   * @@param p_calcDate <em>calc_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setCalcDate( java.sql.Timestamp p_calcDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    calc_date = p_calcDate;
    calc_date_is_set = true;
    calc_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>calc_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setCalcDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    calc_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    calc_date_is_set = true;
    calc_date_is_modified = true;
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
   * <em>ifo_open_position_stop</em>カラムの値を設定します。 
   *
   * @@param p_ifoOpenPositionStop <em>ifo_open_position_stop</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setIfoOpenPositionStop( String p_ifoOpenPositionStop )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ifo_open_position_stop = p_ifoOpenPositionStop;
    ifo_open_position_stop_is_set = true;
    ifo_open_position_stop_is_modified = true;
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
   * <em>payment_requisition_amount_0</em>カラムの値を設定します。 
   *
   * @@param p_paymentRequisitionAmount0 <em>payment_requisition_amount_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentRequisitionAmount0( double p_paymentRequisitionAmount0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_amount_0 = new Double(p_paymentRequisitionAmount0);
    payment_requisition_amount_0_is_set = true;
    payment_requisition_amount_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_requisition_amount_0</em>カラムに値を設定します。 
   */
  public final void setPaymentRequisitionAmount0( Double p_paymentRequisitionAmount0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_amount_0 = p_paymentRequisitionAmount0;
    payment_requisition_amount_0_is_set = true;
    payment_requisition_amount_0_is_modified = true;
  }


  /** 
   * <em>payment_requisition_amount_1</em>カラムの値を設定します。 
   *
   * @@param p_paymentRequisitionAmount1 <em>payment_requisition_amount_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentRequisitionAmount1( double p_paymentRequisitionAmount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_amount_1 = new Double(p_paymentRequisitionAmount1);
    payment_requisition_amount_1_is_set = true;
    payment_requisition_amount_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_requisition_amount_1</em>カラムに値を設定します。 
   */
  public final void setPaymentRequisitionAmount1( Double p_paymentRequisitionAmount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_amount_1 = p_paymentRequisitionAmount1;
    payment_requisition_amount_1_is_set = true;
    payment_requisition_amount_1_is_modified = true;
  }


  /** 
   * <em>payment_requisition_amount_2</em>カラムの値を設定します。 
   *
   * @@param p_paymentRequisitionAmount2 <em>payment_requisition_amount_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentRequisitionAmount2( double p_paymentRequisitionAmount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_amount_2 = new Double(p_paymentRequisitionAmount2);
    payment_requisition_amount_2_is_set = true;
    payment_requisition_amount_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_requisition_amount_2</em>カラムに値を設定します。 
   */
  public final void setPaymentRequisitionAmount2( Double p_paymentRequisitionAmount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_amount_2 = p_paymentRequisitionAmount2;
    payment_requisition_amount_2_is_set = true;
    payment_requisition_amount_2_is_modified = true;
  }


  /** 
   * <em>payment_requisition_amount_3</em>カラムの値を設定します。 
   *
   * @@param p_paymentRequisitionAmount3 <em>payment_requisition_amount_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentRequisitionAmount3( double p_paymentRequisitionAmount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_amount_3 = new Double(p_paymentRequisitionAmount3);
    payment_requisition_amount_3_is_set = true;
    payment_requisition_amount_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_requisition_amount_3</em>カラムに値を設定します。 
   */
  public final void setPaymentRequisitionAmount3( Double p_paymentRequisitionAmount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_amount_3 = p_paymentRequisitionAmount3;
    payment_requisition_amount_3_is_set = true;
    payment_requisition_amount_3_is_modified = true;
  }


  /** 
   * <em>payment_requisition_amount_4</em>カラムの値を設定します。 
   *
   * @@param p_paymentRequisitionAmount4 <em>payment_requisition_amount_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentRequisitionAmount4( double p_paymentRequisitionAmount4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_amount_4 = new Double(p_paymentRequisitionAmount4);
    payment_requisition_amount_4_is_set = true;
    payment_requisition_amount_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_requisition_amount_4</em>カラムに値を設定します。 
   */
  public final void setPaymentRequisitionAmount4( Double p_paymentRequisitionAmount4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_amount_4 = p_paymentRequisitionAmount4;
    payment_requisition_amount_4_is_set = true;
    payment_requisition_amount_4_is_modified = true;
  }


  /** 
   * <em>payment_requisition_amount_5</em>カラムの値を設定します。 
   *
   * @@param p_paymentRequisitionAmount5 <em>payment_requisition_amount_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentRequisitionAmount5( double p_paymentRequisitionAmount5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_amount_5 = new Double(p_paymentRequisitionAmount5);
    payment_requisition_amount_5_is_set = true;
    payment_requisition_amount_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_requisition_amount_5</em>カラムに値を設定します。 
   */
  public final void setPaymentRequisitionAmount5( Double p_paymentRequisitionAmount5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_amount_5 = p_paymentRequisitionAmount5;
    payment_requisition_amount_5_is_set = true;
    payment_requisition_amount_5_is_modified = true;
  }


  /** 
   * <em>payment_requisition_division_0</em>カラムの値を設定します。 
   *
   * @@param p_paymentRequisitionDivision0 <em>payment_requisition_division_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentRequisitionDivision0( double p_paymentRequisitionDivision0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_division_0 = new Double(p_paymentRequisitionDivision0);
    payment_requisition_division_0_is_set = true;
    payment_requisition_division_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_requisition_division_0</em>カラムに値を設定します。 
   */
  public final void setPaymentRequisitionDivision0( Double p_paymentRequisitionDivision0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_division_0 = p_paymentRequisitionDivision0;
    payment_requisition_division_0_is_set = true;
    payment_requisition_division_0_is_modified = true;
  }


  /** 
   * <em>payment_requisition_division_1</em>カラムの値を設定します。 
   *
   * @@param p_paymentRequisitionDivision1 <em>payment_requisition_division_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentRequisitionDivision1( double p_paymentRequisitionDivision1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_division_1 = new Double(p_paymentRequisitionDivision1);
    payment_requisition_division_1_is_set = true;
    payment_requisition_division_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_requisition_division_1</em>カラムに値を設定します。 
   */
  public final void setPaymentRequisitionDivision1( Double p_paymentRequisitionDivision1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_division_1 = p_paymentRequisitionDivision1;
    payment_requisition_division_1_is_set = true;
    payment_requisition_division_1_is_modified = true;
  }


  /** 
   * <em>payment_requisition_division_2</em>カラムの値を設定します。 
   *
   * @@param p_paymentRequisitionDivision2 <em>payment_requisition_division_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentRequisitionDivision2( double p_paymentRequisitionDivision2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_division_2 = new Double(p_paymentRequisitionDivision2);
    payment_requisition_division_2_is_set = true;
    payment_requisition_division_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_requisition_division_2</em>カラムに値を設定します。 
   */
  public final void setPaymentRequisitionDivision2( Double p_paymentRequisitionDivision2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_division_2 = p_paymentRequisitionDivision2;
    payment_requisition_division_2_is_set = true;
    payment_requisition_division_2_is_modified = true;
  }


  /** 
   * <em>payment_requisition_division_3</em>カラムの値を設定します。 
   *
   * @@param p_paymentRequisitionDivision3 <em>payment_requisition_division_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentRequisitionDivision3( double p_paymentRequisitionDivision3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_division_3 = new Double(p_paymentRequisitionDivision3);
    payment_requisition_division_3_is_set = true;
    payment_requisition_division_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_requisition_division_3</em>カラムに値を設定します。 
   */
  public final void setPaymentRequisitionDivision3( Double p_paymentRequisitionDivision3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_division_3 = p_paymentRequisitionDivision3;
    payment_requisition_division_3_is_set = true;
    payment_requisition_division_3_is_modified = true;
  }


  /** 
   * <em>payment_requisition_division_4</em>カラムの値を設定します。 
   *
   * @@param p_paymentRequisitionDivision4 <em>payment_requisition_division_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentRequisitionDivision4( double p_paymentRequisitionDivision4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_division_4 = new Double(p_paymentRequisitionDivision4);
    payment_requisition_division_4_is_set = true;
    payment_requisition_division_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_requisition_division_4</em>カラムに値を設定します。 
   */
  public final void setPaymentRequisitionDivision4( Double p_paymentRequisitionDivision4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_division_4 = p_paymentRequisitionDivision4;
    payment_requisition_division_4_is_set = true;
    payment_requisition_division_4_is_modified = true;
  }


  /** 
   * <em>payment_requisition_division_5</em>カラムの値を設定します。 
   *
   * @@param p_paymentRequisitionDivision5 <em>payment_requisition_division_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPaymentRequisitionDivision5( double p_paymentRequisitionDivision5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_division_5 = new Double(p_paymentRequisitionDivision5);
    payment_requisition_division_5_is_set = true;
    payment_requisition_division_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>payment_requisition_division_5</em>カラムに値を設定します。 
   */
  public final void setPaymentRequisitionDivision5( Double p_paymentRequisitionDivision5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    payment_requisition_division_5 = p_paymentRequisitionDivision5;
    payment_requisition_division_5_is_set = true;
    payment_requisition_division_5_is_modified = true;
  }


  /** 
   * <em>account_balance_0</em>カラムの値を設定します。 
   *
   * @@param p_accountBalance0 <em>account_balance_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setAccountBalance0( double p_accountBalance0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_0 = new Double(p_accountBalance0);
    account_balance_0_is_set = true;
    account_balance_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_balance_0</em>カラムに値を設定します。 
   */
  public final void setAccountBalance0( Double p_accountBalance0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
    account_balance_1 = new Double(p_accountBalance1);
    account_balance_1_is_set = true;
    account_balance_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_balance_1</em>カラムに値を設定します。 
   */
  public final void setAccountBalance1( Double p_accountBalance1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
    account_balance_2 = new Double(p_accountBalance2);
    account_balance_2_is_set = true;
    account_balance_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_balance_2</em>カラムに値を設定します。 
   */
  public final void setAccountBalance2( Double p_accountBalance2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
    account_balance_3 = new Double(p_accountBalance3);
    account_balance_3_is_set = true;
    account_balance_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_balance_3</em>カラムに値を設定します。 
   */
  public final void setAccountBalance3( Double p_accountBalance3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
    account_balance_4 = new Double(p_accountBalance4);
    account_balance_4_is_set = true;
    account_balance_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_balance_4</em>カラムに値を設定します。 
   */
  public final void setAccountBalance4( Double p_accountBalance4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
    account_balance_5 = new Double(p_accountBalance5);
    account_balance_5_is_set = true;
    account_balance_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>account_balance_5</em>カラムに値を設定します。 
   */
  public final void setAccountBalance5( Double p_accountBalance5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    account_balance_5 = p_accountBalance5;
    account_balance_5_is_set = true;
    account_balance_5_is_modified = true;
  }


  /** 
   * <em>cash_balance0</em>カラムの値を設定します。 
   *
   * @@param p_cashBalance0 <em>cash_balance0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashBalance0( double p_cashBalance0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance0 = new Double(p_cashBalance0);
    cash_balance0_is_set = true;
    cash_balance0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cash_balance0</em>カラムに値を設定します。 
   */
  public final void setCashBalance0( Double p_cashBalance0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance0 = p_cashBalance0;
    cash_balance0_is_set = true;
    cash_balance0_is_modified = true;
  }


  /** 
   * <em>cash_balance1</em>カラムの値を設定します。 
   *
   * @@param p_cashBalance1 <em>cash_balance1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashBalance1( double p_cashBalance1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance1 = new Double(p_cashBalance1);
    cash_balance1_is_set = true;
    cash_balance1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cash_balance1</em>カラムに値を設定します。 
   */
  public final void setCashBalance1( Double p_cashBalance1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance1 = p_cashBalance1;
    cash_balance1_is_set = true;
    cash_balance1_is_modified = true;
  }


  /** 
   * <em>cash_balance2</em>カラムの値を設定します。 
   *
   * @@param p_cashBalance2 <em>cash_balance2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashBalance2( double p_cashBalance2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance2 = new Double(p_cashBalance2);
    cash_balance2_is_set = true;
    cash_balance2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cash_balance2</em>カラムに値を設定します。 
   */
  public final void setCashBalance2( Double p_cashBalance2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance2 = p_cashBalance2;
    cash_balance2_is_set = true;
    cash_balance2_is_modified = true;
  }


  /** 
   * <em>cash_balance3</em>カラムの値を設定します。 
   *
   * @@param p_cashBalance3 <em>cash_balance3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashBalance3( double p_cashBalance3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance3 = new Double(p_cashBalance3);
    cash_balance3_is_set = true;
    cash_balance3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cash_balance3</em>カラムに値を設定します。 
   */
  public final void setCashBalance3( Double p_cashBalance3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance3 = p_cashBalance3;
    cash_balance3_is_set = true;
    cash_balance3_is_modified = true;
  }


  /** 
   * <em>cash_balance4</em>カラムの値を設定します。 
   *
   * @@param p_cashBalance4 <em>cash_balance4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashBalance4( double p_cashBalance4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance4 = new Double(p_cashBalance4);
    cash_balance4_is_set = true;
    cash_balance4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cash_balance4</em>カラムに値を設定します。 
   */
  public final void setCashBalance4( Double p_cashBalance4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance4 = p_cashBalance4;
    cash_balance4_is_set = true;
    cash_balance4_is_modified = true;
  }


  /** 
   * <em>cash_balance5</em>カラムの値を設定します。 
   *
   * @@param p_cashBalance5 <em>cash_balance5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCashBalance5( double p_cashBalance5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance5 = new Double(p_cashBalance5);
    cash_balance5_is_set = true;
    cash_balance5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>cash_balance5</em>カラムに値を設定します。 
   */
  public final void setCashBalance5( Double p_cashBalance5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    cash_balance5 = p_cashBalance5;
    cash_balance5_is_set = true;
    cash_balance5_is_modified = true;
  }


  /** 
   * <em>day_trade_restraint_0</em>カラムの値を設定します。 
   *
   * @@param p_dayTradeRestraint0 <em>day_trade_restraint_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDayTradeRestraint0( double p_dayTradeRestraint0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    day_trade_restraint_0 = new Double(p_dayTradeRestraint0);
    day_trade_restraint_0_is_set = true;
    day_trade_restraint_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_trade_restraint_0</em>カラムに値を設定します。 
   */
  public final void setDayTradeRestraint0( Double p_dayTradeRestraint0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
    day_trade_restraint_1 = new Double(p_dayTradeRestraint1);
    day_trade_restraint_1_is_set = true;
    day_trade_restraint_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_trade_restraint_1</em>カラムに値を設定します。 
   */
  public final void setDayTradeRestraint1( Double p_dayTradeRestraint1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
    day_trade_restraint_2 = new Double(p_dayTradeRestraint2);
    day_trade_restraint_2_is_set = true;
    day_trade_restraint_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_trade_restraint_2</em>カラムに値を設定します。 
   */
  public final void setDayTradeRestraint2( Double p_dayTradeRestraint2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
    day_trade_restraint_3 = new Double(p_dayTradeRestraint3);
    day_trade_restraint_3_is_set = true;
    day_trade_restraint_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_trade_restraint_3</em>カラムに値を設定します。 
   */
  public final void setDayTradeRestraint3( Double p_dayTradeRestraint3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
    day_trade_restraint_4 = new Double(p_dayTradeRestraint4);
    day_trade_restraint_4_is_set = true;
    day_trade_restraint_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>day_trade_restraint_4</em>カラムに値を設定します。 
   */
  public final void setDayTradeRestraint4( Double p_dayTradeRestraint4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
    other_restraint_0 = new Double(p_otherRestraint0);
    other_restraint_0_is_set = true;
    other_restraint_0_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other_restraint_0</em>カラムに値を設定します。 
   */
  public final void setOtherRestraint0( Double p_otherRestraint0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
    other_restraint_1 = new Double(p_otherRestraint1);
    other_restraint_1_is_set = true;
    other_restraint_1_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other_restraint_1</em>カラムに値を設定します。 
   */
  public final void setOtherRestraint1( Double p_otherRestraint1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
    other_restraint_2 = new Double(p_otherRestraint2);
    other_restraint_2_is_set = true;
    other_restraint_2_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other_restraint_2</em>カラムに値を設定します。 
   */
  public final void setOtherRestraint2( Double p_otherRestraint2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
    other_restraint_3 = new Double(p_otherRestraint3);
    other_restraint_3_is_set = true;
    other_restraint_3_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other_restraint_3</em>カラムに値を設定します。 
   */
  public final void setOtherRestraint3( Double p_otherRestraint3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
    other_restraint_4 = new Double(p_otherRestraint4);
    other_restraint_4_is_set = true;
    other_restraint_4_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other_restraint_4</em>カラムに値を設定します。 
   */
  public final void setOtherRestraint4( Double p_otherRestraint4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
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
    other_restraint_5 = new Double(p_otherRestraint5);
    other_restraint_5_is_set = true;
    other_restraint_5_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>other_restraint_5</em>カラムに値を設定します。 
   */
  public final void setOtherRestraint5( Double p_otherRestraint5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    other_restraint_5 = p_otherRestraint5;
    other_restraint_5_is_set = true;
    other_restraint_5_is_modified = true;
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
                else if ( name.equals("account_attribute") ) {
                    return this.account_attribute;
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
                else if ( name.equals("account_balance_5") ) {
                    return this.account_balance_5;
                }
                else if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("calc_result_equity_id") ) {
                    return new Long( calc_result_equity_id );
                }
                else if ( name.equals("calc_date") ) {
                    return this.calc_date;
                }
                else if ( name.equals("cash_balance0") ) {
                    return this.cash_balance0;
                }
                else if ( name.equals("cash_balance1") ) {
                    return this.cash_balance1;
                }
                else if ( name.equals("cash_balance2") ) {
                    return this.cash_balance2;
                }
                else if ( name.equals("cash_balance3") ) {
                    return this.cash_balance3;
                }
                else if ( name.equals("cash_balance4") ) {
                    return this.cash_balance4;
                }
                else if ( name.equals("cash_balance5") ) {
                    return this.cash_balance5;
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
            case 'f':
                if ( name.equals("family_name") ) {
                    return this.family_name;
                }
                break;
            case 'i':
                if ( name.equals("ifo_open_position_stop") ) {
                    return this.ifo_open_position_stop;
                }
                else if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("other_trading_stop") ) {
                    return this.other_trading_stop;
                }
                else if ( name.equals("other_restraint_0") ) {
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
                else if ( name.equals("other_restraint_5") ) {
                    return this.other_restraint_5;
                }
                break;
            case 'p':
                if ( name.equals("payment_stop") ) {
                    return this.payment_stop;
                }
                else if ( name.equals("payment_requisition_amount_0") ) {
                    return this.payment_requisition_amount_0;
                }
                else if ( name.equals("payment_requisition_amount_1") ) {
                    return this.payment_requisition_amount_1;
                }
                else if ( name.equals("payment_requisition_amount_2") ) {
                    return this.payment_requisition_amount_2;
                }
                else if ( name.equals("payment_requisition_amount_3") ) {
                    return this.payment_requisition_amount_3;
                }
                else if ( name.equals("payment_requisition_amount_4") ) {
                    return this.payment_requisition_amount_4;
                }
                else if ( name.equals("payment_requisition_amount_5") ) {
                    return this.payment_requisition_amount_5;
                }
                else if ( name.equals("payment_requisition_division_0") ) {
                    return this.payment_requisition_division_0;
                }
                else if ( name.equals("payment_requisition_division_1") ) {
                    return this.payment_requisition_division_1;
                }
                else if ( name.equals("payment_requisition_division_2") ) {
                    return this.payment_requisition_division_2;
                }
                else if ( name.equals("payment_requisition_division_3") ) {
                    return this.payment_requisition_division_3;
                }
                else if ( name.equals("payment_requisition_division_4") ) {
                    return this.payment_requisition_division_4;
                }
                else if ( name.equals("payment_requisition_division_5") ) {
                    return this.payment_requisition_division_5;
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code") ) {
                    return this.sonar_trader_code;
                }
                break;
            case 't':
                if ( name.equals("trading_stop") ) {
                    return this.trading_stop;
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
                else if ( name.equals("account_attribute") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_attribute' must be String: '"+value+"' is not." );
                    this.account_attribute = (String) value;
                    if (this.account_attribute_is_set)
                        this.account_attribute_is_modified = true;
                    this.account_attribute_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_balance_0' must be Double: '"+value+"' is not." );
                    this.account_balance_0 = (Double) value;
                    if (this.account_balance_0_is_set)
                        this.account_balance_0_is_modified = true;
                    this.account_balance_0_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_balance_1' must be Double: '"+value+"' is not." );
                    this.account_balance_1 = (Double) value;
                    if (this.account_balance_1_is_set)
                        this.account_balance_1_is_modified = true;
                    this.account_balance_1_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_balance_2' must be Double: '"+value+"' is not." );
                    this.account_balance_2 = (Double) value;
                    if (this.account_balance_2_is_set)
                        this.account_balance_2_is_modified = true;
                    this.account_balance_2_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_balance_3' must be Double: '"+value+"' is not." );
                    this.account_balance_3 = (Double) value;
                    if (this.account_balance_3_is_set)
                        this.account_balance_3_is_modified = true;
                    this.account_balance_3_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_balance_4' must be Double: '"+value+"' is not." );
                    this.account_balance_4 = (Double) value;
                    if (this.account_balance_4_is_set)
                        this.account_balance_4_is_modified = true;
                    this.account_balance_4_is_set = true;
                    return;
                }
                else if ( name.equals("account_balance_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'account_balance_5' must be Double: '"+value+"' is not." );
                    this.account_balance_5 = (Double) value;
                    if (this.account_balance_5_is_set)
                        this.account_balance_5_is_modified = true;
                    this.account_balance_5_is_set = true;
                    return;
                }
                else if ( name.equals("account_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'branch_code' must be String: '"+value+"' is not." );
                    this.branch_code = (String) value;
                    if (this.branch_code_is_set)
                        this.branch_code_is_modified = true;
                    this.branch_code_is_set = true;
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
                else if ( name.equals("calc_date") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'calc_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.calc_date = (java.sql.Timestamp) value;
                    if (this.calc_date_is_set)
                        this.calc_date_is_modified = true;
                    this.calc_date_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance0") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance0' must be Double: '"+value+"' is not." );
                    this.cash_balance0 = (Double) value;
                    if (this.cash_balance0_is_set)
                        this.cash_balance0_is_modified = true;
                    this.cash_balance0_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance1' must be Double: '"+value+"' is not." );
                    this.cash_balance1 = (Double) value;
                    if (this.cash_balance1_is_set)
                        this.cash_balance1_is_modified = true;
                    this.cash_balance1_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance2' must be Double: '"+value+"' is not." );
                    this.cash_balance2 = (Double) value;
                    if (this.cash_balance2_is_set)
                        this.cash_balance2_is_modified = true;
                    this.cash_balance2_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance3' must be Double: '"+value+"' is not." );
                    this.cash_balance3 = (Double) value;
                    if (this.cash_balance3_is_set)
                        this.cash_balance3_is_modified = true;
                    this.cash_balance3_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance4' must be Double: '"+value+"' is not." );
                    this.cash_balance4 = (Double) value;
                    if (this.cash_balance4_is_set)
                        this.cash_balance4_is_modified = true;
                    this.cash_balance4_is_set = true;
                    return;
                }
                else if ( name.equals("cash_balance5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'cash_balance5' must be Double: '"+value+"' is not." );
                    this.cash_balance5 = (Double) value;
                    if (this.cash_balance5_is_set)
                        this.cash_balance5_is_modified = true;
                    this.cash_balance5_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( value != null )
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
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_trade_restraint_0' must be Double: '"+value+"' is not." );
                    this.day_trade_restraint_0 = (Double) value;
                    if (this.day_trade_restraint_0_is_set)
                        this.day_trade_restraint_0_is_modified = true;
                    this.day_trade_restraint_0_is_set = true;
                    return;
                }
                else if ( name.equals("day_trade_restraint_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_trade_restraint_1' must be Double: '"+value+"' is not." );
                    this.day_trade_restraint_1 = (Double) value;
                    if (this.day_trade_restraint_1_is_set)
                        this.day_trade_restraint_1_is_modified = true;
                    this.day_trade_restraint_1_is_set = true;
                    return;
                }
                else if ( name.equals("day_trade_restraint_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_trade_restraint_2' must be Double: '"+value+"' is not." );
                    this.day_trade_restraint_2 = (Double) value;
                    if (this.day_trade_restraint_2_is_set)
                        this.day_trade_restraint_2_is_modified = true;
                    this.day_trade_restraint_2_is_set = true;
                    return;
                }
                else if ( name.equals("day_trade_restraint_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_trade_restraint_3' must be Double: '"+value+"' is not." );
                    this.day_trade_restraint_3 = (Double) value;
                    if (this.day_trade_restraint_3_is_set)
                        this.day_trade_restraint_3_is_modified = true;
                    this.day_trade_restraint_3_is_set = true;
                    return;
                }
                else if ( name.equals("day_trade_restraint_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'day_trade_restraint_4' must be Double: '"+value+"' is not." );
                    this.day_trade_restraint_4 = (Double) value;
                    if (this.day_trade_restraint_4_is_set)
                        this.day_trade_restraint_4_is_modified = true;
                    this.day_trade_restraint_4_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("family_name") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'family_name' must be String: '"+value+"' is not." );
                    this.family_name = (String) value;
                    if (this.family_name_is_set)
                        this.family_name_is_modified = true;
                    this.family_name_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("ifo_open_position_stop") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ifo_open_position_stop' must be String: '"+value+"' is not." );
                    this.ifo_open_position_stop = (String) value;
                    if (this.ifo_open_position_stop_is_set)
                        this.ifo_open_position_stop_is_modified = true;
                    this.ifo_open_position_stop_is_set = true;
                    return;
                }
                else if ( name.equals("institution_code") ) {
                    if ( value != null )
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
                    if ( value != null )
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
                if ( name.equals("other_trading_stop") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'other_trading_stop' must be String: '"+value+"' is not." );
                    this.other_trading_stop = (String) value;
                    if (this.other_trading_stop_is_set)
                        this.other_trading_stop_is_modified = true;
                    this.other_trading_stop_is_set = true;
                    return;
                }
                else if ( name.equals("other_restraint_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'other_restraint_0' must be Double: '"+value+"' is not." );
                    this.other_restraint_0 = (Double) value;
                    if (this.other_restraint_0_is_set)
                        this.other_restraint_0_is_modified = true;
                    this.other_restraint_0_is_set = true;
                    return;
                }
                else if ( name.equals("other_restraint_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'other_restraint_1' must be Double: '"+value+"' is not." );
                    this.other_restraint_1 = (Double) value;
                    if (this.other_restraint_1_is_set)
                        this.other_restraint_1_is_modified = true;
                    this.other_restraint_1_is_set = true;
                    return;
                }
                else if ( name.equals("other_restraint_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'other_restraint_2' must be Double: '"+value+"' is not." );
                    this.other_restraint_2 = (Double) value;
                    if (this.other_restraint_2_is_set)
                        this.other_restraint_2_is_modified = true;
                    this.other_restraint_2_is_set = true;
                    return;
                }
                else if ( name.equals("other_restraint_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'other_restraint_3' must be Double: '"+value+"' is not." );
                    this.other_restraint_3 = (Double) value;
                    if (this.other_restraint_3_is_set)
                        this.other_restraint_3_is_modified = true;
                    this.other_restraint_3_is_set = true;
                    return;
                }
                else if ( name.equals("other_restraint_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'other_restraint_4' must be Double: '"+value+"' is not." );
                    this.other_restraint_4 = (Double) value;
                    if (this.other_restraint_4_is_set)
                        this.other_restraint_4_is_modified = true;
                    this.other_restraint_4_is_set = true;
                    return;
                }
                else if ( name.equals("other_restraint_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'other_restraint_5' must be Double: '"+value+"' is not." );
                    this.other_restraint_5 = (Double) value;
                    if (this.other_restraint_5_is_set)
                        this.other_restraint_5_is_modified = true;
                    this.other_restraint_5_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("payment_stop") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'payment_stop' must be String: '"+value+"' is not." );
                    this.payment_stop = (String) value;
                    if (this.payment_stop_is_set)
                        this.payment_stop_is_modified = true;
                    this.payment_stop_is_set = true;
                    return;
                }
                else if ( name.equals("payment_requisition_amount_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_requisition_amount_0' must be Double: '"+value+"' is not." );
                    this.payment_requisition_amount_0 = (Double) value;
                    if (this.payment_requisition_amount_0_is_set)
                        this.payment_requisition_amount_0_is_modified = true;
                    this.payment_requisition_amount_0_is_set = true;
                    return;
                }
                else if ( name.equals("payment_requisition_amount_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_requisition_amount_1' must be Double: '"+value+"' is not." );
                    this.payment_requisition_amount_1 = (Double) value;
                    if (this.payment_requisition_amount_1_is_set)
                        this.payment_requisition_amount_1_is_modified = true;
                    this.payment_requisition_amount_1_is_set = true;
                    return;
                }
                else if ( name.equals("payment_requisition_amount_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_requisition_amount_2' must be Double: '"+value+"' is not." );
                    this.payment_requisition_amount_2 = (Double) value;
                    if (this.payment_requisition_amount_2_is_set)
                        this.payment_requisition_amount_2_is_modified = true;
                    this.payment_requisition_amount_2_is_set = true;
                    return;
                }
                else if ( name.equals("payment_requisition_amount_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_requisition_amount_3' must be Double: '"+value+"' is not." );
                    this.payment_requisition_amount_3 = (Double) value;
                    if (this.payment_requisition_amount_3_is_set)
                        this.payment_requisition_amount_3_is_modified = true;
                    this.payment_requisition_amount_3_is_set = true;
                    return;
                }
                else if ( name.equals("payment_requisition_amount_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_requisition_amount_4' must be Double: '"+value+"' is not." );
                    this.payment_requisition_amount_4 = (Double) value;
                    if (this.payment_requisition_amount_4_is_set)
                        this.payment_requisition_amount_4_is_modified = true;
                    this.payment_requisition_amount_4_is_set = true;
                    return;
                }
                else if ( name.equals("payment_requisition_amount_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_requisition_amount_5' must be Double: '"+value+"' is not." );
                    this.payment_requisition_amount_5 = (Double) value;
                    if (this.payment_requisition_amount_5_is_set)
                        this.payment_requisition_amount_5_is_modified = true;
                    this.payment_requisition_amount_5_is_set = true;
                    return;
                }
                else if ( name.equals("payment_requisition_division_0") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_requisition_division_0' must be Double: '"+value+"' is not." );
                    this.payment_requisition_division_0 = (Double) value;
                    if (this.payment_requisition_division_0_is_set)
                        this.payment_requisition_division_0_is_modified = true;
                    this.payment_requisition_division_0_is_set = true;
                    return;
                }
                else if ( name.equals("payment_requisition_division_1") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_requisition_division_1' must be Double: '"+value+"' is not." );
                    this.payment_requisition_division_1 = (Double) value;
                    if (this.payment_requisition_division_1_is_set)
                        this.payment_requisition_division_1_is_modified = true;
                    this.payment_requisition_division_1_is_set = true;
                    return;
                }
                else if ( name.equals("payment_requisition_division_2") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_requisition_division_2' must be Double: '"+value+"' is not." );
                    this.payment_requisition_division_2 = (Double) value;
                    if (this.payment_requisition_division_2_is_set)
                        this.payment_requisition_division_2_is_modified = true;
                    this.payment_requisition_division_2_is_set = true;
                    return;
                }
                else if ( name.equals("payment_requisition_division_3") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_requisition_division_3' must be Double: '"+value+"' is not." );
                    this.payment_requisition_division_3 = (Double) value;
                    if (this.payment_requisition_division_3_is_set)
                        this.payment_requisition_division_3_is_modified = true;
                    this.payment_requisition_division_3_is_set = true;
                    return;
                }
                else if ( name.equals("payment_requisition_division_4") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_requisition_division_4' must be Double: '"+value+"' is not." );
                    this.payment_requisition_division_4 = (Double) value;
                    if (this.payment_requisition_division_4_is_set)
                        this.payment_requisition_division_4_is_modified = true;
                    this.payment_requisition_division_4_is_set = true;
                    return;
                }
                else if ( name.equals("payment_requisition_division_5") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'payment_requisition_division_5' must be Double: '"+value+"' is not." );
                    this.payment_requisition_division_5 = (Double) value;
                    if (this.payment_requisition_division_5_is_set)
                        this.payment_requisition_division_5_is_modified = true;
                    this.payment_requisition_division_5_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sonar_trader_code' must be String: '"+value+"' is not." );
                    this.sonar_trader_code = (String) value;
                    if (this.sonar_trader_code_is_set)
                        this.sonar_trader_code_is_modified = true;
                    this.sonar_trader_code_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trading_stop") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trading_stop' must be String: '"+value+"' is not." );
                    this.trading_stop = (String) value;
                    if (this.trading_stop_is_set)
                        this.trading_stop_is_modified = true;
                    this.trading_stop_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
