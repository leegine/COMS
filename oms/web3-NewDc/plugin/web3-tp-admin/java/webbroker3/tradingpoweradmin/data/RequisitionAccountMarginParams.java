head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.58.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	RequisitionAccountMarginParams.java;


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
 * requisition_account_marginテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link RequisitionAccountMarginRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link RequisitionAccountMarginParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link RequisitionAccountMarginParams}が{@@link RequisitionAccountMarginRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RequisitionAccountMarginPK 
 * @@see RequisitionAccountMarginRow 
 */
public class RequisitionAccountMarginParams extends Params implements RequisitionAccountMarginRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "requisition_account_margin";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = RequisitionAccountMarginRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return RequisitionAccountMarginRow.TYPE;
  }


  /** 
   * <em>calc_result_margin_id</em>カラムの値 
   */
  public  long  calc_result_margin_id;

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

  /** 
   * <em>family_name</em>カラムの値 
   */
  public  String  family_name;

  /** 
   * <em>sonar_trader_code</em>カラムの値 
   */
  public  String  sonar_trader_code;

  /** 
   * <em>mark_to_market_div</em>カラムの値 
   */
  public  String  mark_to_market_div;

  /** 
   * <em>debit_amount_0</em>カラムの値 
   */
  public  double  debit_amount_0;

  /** 
   * <em>debit_amount_1</em>カラムの値 
   */
  public  double  debit_amount_1;

  /** 
   * <em>debit_amount_2</em>カラムの値 
   */
  public  double  debit_amount_2;

  /** 
   * <em>debit_amount_3</em>カラムの値 
   */
  public  double  debit_amount_3;

  /** 
   * <em>debit_amount_4</em>カラムの値 
   */
  public  double  debit_amount_4;

  /** 
   * <em>debit_amount_5</em>カラムの値 
   */
  public  double  debit_amount_5;

  /** 
   * <em>special_debit_amount_0</em>カラムの値 
   */
  public  double  special_debit_amount_0;

  /** 
   * <em>special_debit_amount_1</em>カラムの値 
   */
  public  double  special_debit_amount_1;

  /** 
   * <em>special_debit_amount_2</em>カラムの値 
   */
  public  double  special_debit_amount_2;

  /** 
   * <em>special_debit_amount_3</em>カラムの値 
   */
  public  double  special_debit_amount_3;

  /** 
   * <em>special_debit_amount_4</em>カラムの値 
   */
  public  double  special_debit_amount_4;

  /** 
   * <em>special_debit_amount_5</em>カラムの値 
   */
  public  double  special_debit_amount_5;

  /** 
   * <em>receipt_margin_deposit_0</em>カラムの値 
   */
  public  double  receipt_margin_deposit_0;

  /** 
   * <em>receipt_margin_deposit_1</em>カラムの値 
   */
  public  double  receipt_margin_deposit_1;

  /** 
   * <em>receipt_margin_deposit_2</em>カラムの値 
   */
  public  double  receipt_margin_deposit_2;

  /** 
   * <em>receipt_margin_deposit_3</em>カラムの値 
   */
  public  double  receipt_margin_deposit_3;

  /** 
   * <em>receipt_margin_deposit_4</em>カラムの値 
   */
  public  double  receipt_margin_deposit_4;

  /** 
   * <em>receipt_margin_deposit_5</em>カラムの値 
   */
  public  double  receipt_margin_deposit_5;

  /** 
   * <em>margin_maintenance_amount_0</em>カラムの値 
   */
  public  double  margin_maintenance_amount_0;

  /** 
   * <em>margin_maintenance_amount_1</em>カラムの値 
   */
  public  double  margin_maintenance_amount_1;

  /** 
   * <em>margin_maintenance_amount_2</em>カラムの値 
   */
  public  double  margin_maintenance_amount_2;

  /** 
   * <em>margin_maintenance_amount_3</em>カラムの値 
   */
  public  double  margin_maintenance_amount_3;

  /** 
   * <em>margin_maintenance_amount_4</em>カラムの値 
   */
  public  double  margin_maintenance_amount_4;

  /** 
   * <em>margin_maintenance_amount_5</em>カラムの値 
   */
  public  double  margin_maintenance_amount_5;

  /** 
   * <em>margin_deposit_rate_0</em>カラムの値 
   */
  public  double  margin_deposit_rate_0;

  /** 
   * <em>margin_deposit_rate_1</em>カラムの値 
   */
  public  double  margin_deposit_rate_1;

  /** 
   * <em>margin_deposit_rate_2</em>カラムの値 
   */
  public  double  margin_deposit_rate_2;

  /** 
   * <em>margin_deposit_rate_3</em>カラムの値 
   */
  public  double  margin_deposit_rate_3;

  /** 
   * <em>margin_deposit_rate_4</em>カラムの値 
   */
  public  double  margin_deposit_rate_4;

  /** 
   * <em>margin_deposit_rate_5</em>カラムの値 
   */
  public  double  margin_deposit_rate_5;

  /** 
   * <em>margin_claimed_amount_0</em>カラムの値 
   */
  public  double  margin_claimed_amount_0;

  /** 
   * <em>margin_claimed_amount_1</em>カラムの値 
   */
  public  double  margin_claimed_amount_1;

  /** 
   * <em>margin_claimed_amount_2</em>カラムの値 
   */
  public  double  margin_claimed_amount_2;

  /** 
   * <em>margin_claimed_amount_3</em>カラムの値 
   */
  public  double  margin_claimed_amount_3;

  /** 
   * <em>margin_claimed_amount_4</em>カラムの値 
   */
  public  double  margin_claimed_amount_4;

  /** 
   * <em>margin_claimed_amount_5</em>カラムの値 
   */
  public  double  margin_claimed_amount_5;

  private boolean calc_result_margin_id_is_set = false;
  private boolean calc_result_margin_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean family_name_is_set = false;
  private boolean family_name_is_modified = false;
  private boolean sonar_trader_code_is_set = false;
  private boolean sonar_trader_code_is_modified = false;
  private boolean mark_to_market_div_is_set = false;
  private boolean mark_to_market_div_is_modified = false;
  private boolean debit_amount_0_is_set = false;
  private boolean debit_amount_0_is_modified = false;
  private boolean debit_amount_1_is_set = false;
  private boolean debit_amount_1_is_modified = false;
  private boolean debit_amount_2_is_set = false;
  private boolean debit_amount_2_is_modified = false;
  private boolean debit_amount_3_is_set = false;
  private boolean debit_amount_3_is_modified = false;
  private boolean debit_amount_4_is_set = false;
  private boolean debit_amount_4_is_modified = false;
  private boolean debit_amount_5_is_set = false;
  private boolean debit_amount_5_is_modified = false;
  private boolean special_debit_amount_0_is_set = false;
  private boolean special_debit_amount_0_is_modified = false;
  private boolean special_debit_amount_1_is_set = false;
  private boolean special_debit_amount_1_is_modified = false;
  private boolean special_debit_amount_2_is_set = false;
  private boolean special_debit_amount_2_is_modified = false;
  private boolean special_debit_amount_3_is_set = false;
  private boolean special_debit_amount_3_is_modified = false;
  private boolean special_debit_amount_4_is_set = false;
  private boolean special_debit_amount_4_is_modified = false;
  private boolean special_debit_amount_5_is_set = false;
  private boolean special_debit_amount_5_is_modified = false;
  private boolean receipt_margin_deposit_0_is_set = false;
  private boolean receipt_margin_deposit_0_is_modified = false;
  private boolean receipt_margin_deposit_1_is_set = false;
  private boolean receipt_margin_deposit_1_is_modified = false;
  private boolean receipt_margin_deposit_2_is_set = false;
  private boolean receipt_margin_deposit_2_is_modified = false;
  private boolean receipt_margin_deposit_3_is_set = false;
  private boolean receipt_margin_deposit_3_is_modified = false;
  private boolean receipt_margin_deposit_4_is_set = false;
  private boolean receipt_margin_deposit_4_is_modified = false;
  private boolean receipt_margin_deposit_5_is_set = false;
  private boolean receipt_margin_deposit_5_is_modified = false;
  private boolean margin_maintenance_amount_0_is_set = false;
  private boolean margin_maintenance_amount_0_is_modified = false;
  private boolean margin_maintenance_amount_1_is_set = false;
  private boolean margin_maintenance_amount_1_is_modified = false;
  private boolean margin_maintenance_amount_2_is_set = false;
  private boolean margin_maintenance_amount_2_is_modified = false;
  private boolean margin_maintenance_amount_3_is_set = false;
  private boolean margin_maintenance_amount_3_is_modified = false;
  private boolean margin_maintenance_amount_4_is_set = false;
  private boolean margin_maintenance_amount_4_is_modified = false;
  private boolean margin_maintenance_amount_5_is_set = false;
  private boolean margin_maintenance_amount_5_is_modified = false;
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
  private boolean margin_deposit_rate_5_is_set = false;
  private boolean margin_deposit_rate_5_is_modified = false;
  private boolean margin_claimed_amount_0_is_set = false;
  private boolean margin_claimed_amount_0_is_modified = false;
  private boolean margin_claimed_amount_1_is_set = false;
  private boolean margin_claimed_amount_1_is_modified = false;
  private boolean margin_claimed_amount_2_is_set = false;
  private boolean margin_claimed_amount_2_is_modified = false;
  private boolean margin_claimed_amount_3_is_set = false;
  private boolean margin_claimed_amount_3_is_modified = false;
  private boolean margin_claimed_amount_4_is_set = false;
  private boolean margin_claimed_amount_4_is_modified = false;
  private boolean margin_claimed_amount_5_is_set = false;
  private boolean margin_claimed_amount_5_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "calc_result_margin_id=" +calc_result_margin_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "family_name=" +family_name
      + "," + "sonar_trader_code=" +sonar_trader_code
      + "," + "mark_to_market_div=" +mark_to_market_div
      + "," + "debit_amount_0=" +debit_amount_0
      + "," + "debit_amount_1=" +debit_amount_1
      + "," + "debit_amount_2=" +debit_amount_2
      + "," + "debit_amount_3=" +debit_amount_3
      + "," + "debit_amount_4=" +debit_amount_4
      + "," + "debit_amount_5=" +debit_amount_5
      + "," + "special_debit_amount_0=" +special_debit_amount_0
      + "," + "special_debit_amount_1=" +special_debit_amount_1
      + "," + "special_debit_amount_2=" +special_debit_amount_2
      + "," + "special_debit_amount_3=" +special_debit_amount_3
      + "," + "special_debit_amount_4=" +special_debit_amount_4
      + "," + "special_debit_amount_5=" +special_debit_amount_5
      + "," + "receipt_margin_deposit_0=" +receipt_margin_deposit_0
      + "," + "receipt_margin_deposit_1=" +receipt_margin_deposit_1
      + "," + "receipt_margin_deposit_2=" +receipt_margin_deposit_2
      + "," + "receipt_margin_deposit_3=" +receipt_margin_deposit_3
      + "," + "receipt_margin_deposit_4=" +receipt_margin_deposit_4
      + "," + "receipt_margin_deposit_5=" +receipt_margin_deposit_5
      + "," + "margin_maintenance_amount_0=" +margin_maintenance_amount_0
      + "," + "margin_maintenance_amount_1=" +margin_maintenance_amount_1
      + "," + "margin_maintenance_amount_2=" +margin_maintenance_amount_2
      + "," + "margin_maintenance_amount_3=" +margin_maintenance_amount_3
      + "," + "margin_maintenance_amount_4=" +margin_maintenance_amount_4
      + "," + "margin_maintenance_amount_5=" +margin_maintenance_amount_5
      + "," + "margin_deposit_rate_0=" +margin_deposit_rate_0
      + "," + "margin_deposit_rate_1=" +margin_deposit_rate_1
      + "," + "margin_deposit_rate_2=" +margin_deposit_rate_2
      + "," + "margin_deposit_rate_3=" +margin_deposit_rate_3
      + "," + "margin_deposit_rate_4=" +margin_deposit_rate_4
      + "," + "margin_deposit_rate_5=" +margin_deposit_rate_5
      + "," + "margin_claimed_amount_0=" +margin_claimed_amount_0
      + "," + "margin_claimed_amount_1=" +margin_claimed_amount_1
      + "," + "margin_claimed_amount_2=" +margin_claimed_amount_2
      + "," + "margin_claimed_amount_3=" +margin_claimed_amount_3
      + "," + "margin_claimed_amount_4=" +margin_claimed_amount_4
      + "," + "margin_claimed_amount_5=" +margin_claimed_amount_5
      + "}";
  }


  /** 
   * 値が未設定のRequisitionAccountMarginParamsオブジェクトを作成します。 
   */
  public RequisitionAccountMarginParams() {
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のRequisitionAccountMarginRowオブジェクトの値を利用してRequisitionAccountMarginParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するRequisitionAccountMarginRowオブジェクト 
   */
  public RequisitionAccountMarginParams( RequisitionAccountMarginRow p_row )
  {
    calc_result_margin_id = p_row.getCalcResultMarginId();
    calc_result_margin_id_is_set = p_row.getCalcResultMarginIdIsSet();
    calc_result_margin_id_is_modified = p_row.getCalcResultMarginIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    family_name = p_row.getFamilyName();
    family_name_is_set = p_row.getFamilyNameIsSet();
    family_name_is_modified = p_row.getFamilyNameIsModified();
    sonar_trader_code = p_row.getSonarTraderCode();
    sonar_trader_code_is_set = p_row.getSonarTraderCodeIsSet();
    sonar_trader_code_is_modified = p_row.getSonarTraderCodeIsModified();
    mark_to_market_div = p_row.getMarkToMarketDiv();
    mark_to_market_div_is_set = p_row.getMarkToMarketDivIsSet();
    mark_to_market_div_is_modified = p_row.getMarkToMarketDivIsModified();
    debit_amount_0 = p_row.getDebitAmount0();
    debit_amount_0_is_set = p_row.getDebitAmount0IsSet();
    debit_amount_0_is_modified = p_row.getDebitAmount0IsModified();
    debit_amount_1 = p_row.getDebitAmount1();
    debit_amount_1_is_set = p_row.getDebitAmount1IsSet();
    debit_amount_1_is_modified = p_row.getDebitAmount1IsModified();
    debit_amount_2 = p_row.getDebitAmount2();
    debit_amount_2_is_set = p_row.getDebitAmount2IsSet();
    debit_amount_2_is_modified = p_row.getDebitAmount2IsModified();
    debit_amount_3 = p_row.getDebitAmount3();
    debit_amount_3_is_set = p_row.getDebitAmount3IsSet();
    debit_amount_3_is_modified = p_row.getDebitAmount3IsModified();
    debit_amount_4 = p_row.getDebitAmount4();
    debit_amount_4_is_set = p_row.getDebitAmount4IsSet();
    debit_amount_4_is_modified = p_row.getDebitAmount4IsModified();
    debit_amount_5 = p_row.getDebitAmount5();
    debit_amount_5_is_set = p_row.getDebitAmount5IsSet();
    debit_amount_5_is_modified = p_row.getDebitAmount5IsModified();
    special_debit_amount_0 = p_row.getSpecialDebitAmount0();
    special_debit_amount_0_is_set = p_row.getSpecialDebitAmount0IsSet();
    special_debit_amount_0_is_modified = p_row.getSpecialDebitAmount0IsModified();
    special_debit_amount_1 = p_row.getSpecialDebitAmount1();
    special_debit_amount_1_is_set = p_row.getSpecialDebitAmount1IsSet();
    special_debit_amount_1_is_modified = p_row.getSpecialDebitAmount1IsModified();
    special_debit_amount_2 = p_row.getSpecialDebitAmount2();
    special_debit_amount_2_is_set = p_row.getSpecialDebitAmount2IsSet();
    special_debit_amount_2_is_modified = p_row.getSpecialDebitAmount2IsModified();
    special_debit_amount_3 = p_row.getSpecialDebitAmount3();
    special_debit_amount_3_is_set = p_row.getSpecialDebitAmount3IsSet();
    special_debit_amount_3_is_modified = p_row.getSpecialDebitAmount3IsModified();
    special_debit_amount_4 = p_row.getSpecialDebitAmount4();
    special_debit_amount_4_is_set = p_row.getSpecialDebitAmount4IsSet();
    special_debit_amount_4_is_modified = p_row.getSpecialDebitAmount4IsModified();
    special_debit_amount_5 = p_row.getSpecialDebitAmount5();
    special_debit_amount_5_is_set = p_row.getSpecialDebitAmount5IsSet();
    special_debit_amount_5_is_modified = p_row.getSpecialDebitAmount5IsModified();
    receipt_margin_deposit_0 = p_row.getReceiptMarginDeposit0();
    receipt_margin_deposit_0_is_set = p_row.getReceiptMarginDeposit0IsSet();
    receipt_margin_deposit_0_is_modified = p_row.getReceiptMarginDeposit0IsModified();
    receipt_margin_deposit_1 = p_row.getReceiptMarginDeposit1();
    receipt_margin_deposit_1_is_set = p_row.getReceiptMarginDeposit1IsSet();
    receipt_margin_deposit_1_is_modified = p_row.getReceiptMarginDeposit1IsModified();
    receipt_margin_deposit_2 = p_row.getReceiptMarginDeposit2();
    receipt_margin_deposit_2_is_set = p_row.getReceiptMarginDeposit2IsSet();
    receipt_margin_deposit_2_is_modified = p_row.getReceiptMarginDeposit2IsModified();
    receipt_margin_deposit_3 = p_row.getReceiptMarginDeposit3();
    receipt_margin_deposit_3_is_set = p_row.getReceiptMarginDeposit3IsSet();
    receipt_margin_deposit_3_is_modified = p_row.getReceiptMarginDeposit3IsModified();
    receipt_margin_deposit_4 = p_row.getReceiptMarginDeposit4();
    receipt_margin_deposit_4_is_set = p_row.getReceiptMarginDeposit4IsSet();
    receipt_margin_deposit_4_is_modified = p_row.getReceiptMarginDeposit4IsModified();
    receipt_margin_deposit_5 = p_row.getReceiptMarginDeposit5();
    receipt_margin_deposit_5_is_set = p_row.getReceiptMarginDeposit5IsSet();
    receipt_margin_deposit_5_is_modified = p_row.getReceiptMarginDeposit5IsModified();
    margin_maintenance_amount_0 = p_row.getMarginMaintenanceAmount0();
    margin_maintenance_amount_0_is_set = p_row.getMarginMaintenanceAmount0IsSet();
    margin_maintenance_amount_0_is_modified = p_row.getMarginMaintenanceAmount0IsModified();
    margin_maintenance_amount_1 = p_row.getMarginMaintenanceAmount1();
    margin_maintenance_amount_1_is_set = p_row.getMarginMaintenanceAmount1IsSet();
    margin_maintenance_amount_1_is_modified = p_row.getMarginMaintenanceAmount1IsModified();
    margin_maintenance_amount_2 = p_row.getMarginMaintenanceAmount2();
    margin_maintenance_amount_2_is_set = p_row.getMarginMaintenanceAmount2IsSet();
    margin_maintenance_amount_2_is_modified = p_row.getMarginMaintenanceAmount2IsModified();
    margin_maintenance_amount_3 = p_row.getMarginMaintenanceAmount3();
    margin_maintenance_amount_3_is_set = p_row.getMarginMaintenanceAmount3IsSet();
    margin_maintenance_amount_3_is_modified = p_row.getMarginMaintenanceAmount3IsModified();
    margin_maintenance_amount_4 = p_row.getMarginMaintenanceAmount4();
    margin_maintenance_amount_4_is_set = p_row.getMarginMaintenanceAmount4IsSet();
    margin_maintenance_amount_4_is_modified = p_row.getMarginMaintenanceAmount4IsModified();
    margin_maintenance_amount_5 = p_row.getMarginMaintenanceAmount5();
    margin_maintenance_amount_5_is_set = p_row.getMarginMaintenanceAmount5IsSet();
    margin_maintenance_amount_5_is_modified = p_row.getMarginMaintenanceAmount5IsModified();
    margin_deposit_rate_0 = p_row.getMarginDepositRate0();
    margin_deposit_rate_0_is_set = p_row.getMarginDepositRate0IsSet();
    margin_deposit_rate_0_is_modified = p_row.getMarginDepositRate0IsModified();
    margin_deposit_rate_1 = p_row.getMarginDepositRate1();
    margin_deposit_rate_1_is_set = p_row.getMarginDepositRate1IsSet();
    margin_deposit_rate_1_is_modified = p_row.getMarginDepositRate1IsModified();
    margin_deposit_rate_2 = p_row.getMarginDepositRate2();
    margin_deposit_rate_2_is_set = p_row.getMarginDepositRate2IsSet();
    margin_deposit_rate_2_is_modified = p_row.getMarginDepositRate2IsModified();
    margin_deposit_rate_3 = p_row.getMarginDepositRate3();
    margin_deposit_rate_3_is_set = p_row.getMarginDepositRate3IsSet();
    margin_deposit_rate_3_is_modified = p_row.getMarginDepositRate3IsModified();
    margin_deposit_rate_4 = p_row.getMarginDepositRate4();
    margin_deposit_rate_4_is_set = p_row.getMarginDepositRate4IsSet();
    margin_deposit_rate_4_is_modified = p_row.getMarginDepositRate4IsModified();
    margin_deposit_rate_5 = p_row.getMarginDepositRate5();
    margin_deposit_rate_5_is_set = p_row.getMarginDepositRate5IsSet();
    margin_deposit_rate_5_is_modified = p_row.getMarginDepositRate5IsModified();
    margin_claimed_amount_0 = p_row.getMarginClaimedAmount0();
    margin_claimed_amount_0_is_set = p_row.getMarginClaimedAmount0IsSet();
    margin_claimed_amount_0_is_modified = p_row.getMarginClaimedAmount0IsModified();
    margin_claimed_amount_1 = p_row.getMarginClaimedAmount1();
    margin_claimed_amount_1_is_set = p_row.getMarginClaimedAmount1IsSet();
    margin_claimed_amount_1_is_modified = p_row.getMarginClaimedAmount1IsModified();
    margin_claimed_amount_2 = p_row.getMarginClaimedAmount2();
    margin_claimed_amount_2_is_set = p_row.getMarginClaimedAmount2IsSet();
    margin_claimed_amount_2_is_modified = p_row.getMarginClaimedAmount2IsModified();
    margin_claimed_amount_3 = p_row.getMarginClaimedAmount3();
    margin_claimed_amount_3_is_set = p_row.getMarginClaimedAmount3IsSet();
    margin_claimed_amount_3_is_modified = p_row.getMarginClaimedAmount3IsModified();
    margin_claimed_amount_4 = p_row.getMarginClaimedAmount4();
    margin_claimed_amount_4_is_set = p_row.getMarginClaimedAmount4IsSet();
    margin_claimed_amount_4_is_modified = p_row.getMarginClaimedAmount4IsModified();
    margin_claimed_amount_5 = p_row.getMarginClaimedAmount5();
    margin_claimed_amount_5_is_set = p_row.getMarginClaimedAmount5IsSet();
    margin_claimed_amount_5_is_modified = p_row.getMarginClaimedAmount5IsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      calc_result_margin_id_is_set = true;
      calc_result_margin_id_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      family_name_is_set = true;
      family_name_is_modified = true;
      sonar_trader_code_is_set = true;
      sonar_trader_code_is_modified = true;
      mark_to_market_div_is_set = true;
      mark_to_market_div_is_modified = true;
      debit_amount_0_is_set = true;
      debit_amount_0_is_modified = true;
      debit_amount_1_is_set = true;
      debit_amount_1_is_modified = true;
      debit_amount_2_is_set = true;
      debit_amount_2_is_modified = true;
      debit_amount_3_is_set = true;
      debit_amount_3_is_modified = true;
      debit_amount_4_is_set = true;
      debit_amount_4_is_modified = true;
      debit_amount_5_is_set = true;
      debit_amount_5_is_modified = true;
      special_debit_amount_0_is_set = true;
      special_debit_amount_0_is_modified = true;
      special_debit_amount_1_is_set = true;
      special_debit_amount_1_is_modified = true;
      special_debit_amount_2_is_set = true;
      special_debit_amount_2_is_modified = true;
      special_debit_amount_3_is_set = true;
      special_debit_amount_3_is_modified = true;
      special_debit_amount_4_is_set = true;
      special_debit_amount_4_is_modified = true;
      special_debit_amount_5_is_set = true;
      special_debit_amount_5_is_modified = true;
      receipt_margin_deposit_0_is_set = true;
      receipt_margin_deposit_0_is_modified = true;
      receipt_margin_deposit_1_is_set = true;
      receipt_margin_deposit_1_is_modified = true;
      receipt_margin_deposit_2_is_set = true;
      receipt_margin_deposit_2_is_modified = true;
      receipt_margin_deposit_3_is_set = true;
      receipt_margin_deposit_3_is_modified = true;
      receipt_margin_deposit_4_is_set = true;
      receipt_margin_deposit_4_is_modified = true;
      receipt_margin_deposit_5_is_set = true;
      receipt_margin_deposit_5_is_modified = true;
      margin_maintenance_amount_0_is_set = true;
      margin_maintenance_amount_0_is_modified = true;
      margin_maintenance_amount_1_is_set = true;
      margin_maintenance_amount_1_is_modified = true;
      margin_maintenance_amount_2_is_set = true;
      margin_maintenance_amount_2_is_modified = true;
      margin_maintenance_amount_3_is_set = true;
      margin_maintenance_amount_3_is_modified = true;
      margin_maintenance_amount_4_is_set = true;
      margin_maintenance_amount_4_is_modified = true;
      margin_maintenance_amount_5_is_set = true;
      margin_maintenance_amount_5_is_modified = true;
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
      margin_deposit_rate_5_is_set = true;
      margin_deposit_rate_5_is_modified = true;
      margin_claimed_amount_0_is_set = true;
      margin_claimed_amount_0_is_modified = true;
      margin_claimed_amount_1_is_set = true;
      margin_claimed_amount_1_is_modified = true;
      margin_claimed_amount_2_is_set = true;
      margin_claimed_amount_2_is_modified = true;
      margin_claimed_amount_3_is_set = true;
      margin_claimed_amount_3_is_modified = true;
      margin_claimed_amount_4_is_set = true;
      margin_claimed_amount_4_is_modified = true;
      margin_claimed_amount_5_is_set = true;
      margin_claimed_amount_5_is_modified = true;
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof RequisitionAccountMarginRow ) )
       return false;
    return fieldsEqual( (RequisitionAccountMarginRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のRequisitionAccountMarginRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( RequisitionAccountMarginRow row )
  {
    if ( calc_result_margin_id != row.getCalcResultMarginId() )
      return false;
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
    if ( family_name == null ) {
      if ( row.getFamilyName() != null )
        return false;
    } else if ( !family_name.equals( row.getFamilyName() ) ) {
        return false;
    }
    if ( sonar_trader_code == null ) {
      if ( row.getSonarTraderCode() != null )
        return false;
    } else if ( !sonar_trader_code.equals( row.getSonarTraderCode() ) ) {
        return false;
    }
    if ( mark_to_market_div == null ) {
      if ( row.getMarkToMarketDiv() != null )
        return false;
    } else if ( !mark_to_market_div.equals( row.getMarkToMarketDiv() ) ) {
        return false;
    }
    if ( debit_amount_0 != row.getDebitAmount0() )
      return false;
    if ( debit_amount_1 != row.getDebitAmount1() )
      return false;
    if ( debit_amount_2 != row.getDebitAmount2() )
      return false;
    if ( debit_amount_3 != row.getDebitAmount3() )
      return false;
    if ( debit_amount_4 != row.getDebitAmount4() )
      return false;
    if ( debit_amount_5 != row.getDebitAmount5() )
      return false;
    if ( special_debit_amount_0 != row.getSpecialDebitAmount0() )
      return false;
    if ( special_debit_amount_1 != row.getSpecialDebitAmount1() )
      return false;
    if ( special_debit_amount_2 != row.getSpecialDebitAmount2() )
      return false;
    if ( special_debit_amount_3 != row.getSpecialDebitAmount3() )
      return false;
    if ( special_debit_amount_4 != row.getSpecialDebitAmount4() )
      return false;
    if ( special_debit_amount_5 != row.getSpecialDebitAmount5() )
      return false;
    if ( receipt_margin_deposit_0 != row.getReceiptMarginDeposit0() )
      return false;
    if ( receipt_margin_deposit_1 != row.getReceiptMarginDeposit1() )
      return false;
    if ( receipt_margin_deposit_2 != row.getReceiptMarginDeposit2() )
      return false;
    if ( receipt_margin_deposit_3 != row.getReceiptMarginDeposit3() )
      return false;
    if ( receipt_margin_deposit_4 != row.getReceiptMarginDeposit4() )
      return false;
    if ( receipt_margin_deposit_5 != row.getReceiptMarginDeposit5() )
      return false;
    if ( margin_maintenance_amount_0 != row.getMarginMaintenanceAmount0() )
      return false;
    if ( margin_maintenance_amount_1 != row.getMarginMaintenanceAmount1() )
      return false;
    if ( margin_maintenance_amount_2 != row.getMarginMaintenanceAmount2() )
      return false;
    if ( margin_maintenance_amount_3 != row.getMarginMaintenanceAmount3() )
      return false;
    if ( margin_maintenance_amount_4 != row.getMarginMaintenanceAmount4() )
      return false;
    if ( margin_maintenance_amount_5 != row.getMarginMaintenanceAmount5() )
      return false;
    if ( margin_deposit_rate_0 != row.getMarginDepositRate0() )
      return false;
    if ( margin_deposit_rate_1 != row.getMarginDepositRate1() )
      return false;
    if ( margin_deposit_rate_2 != row.getMarginDepositRate2() )
      return false;
    if ( margin_deposit_rate_3 != row.getMarginDepositRate3() )
      return false;
    if ( margin_deposit_rate_4 != row.getMarginDepositRate4() )
      return false;
    if ( margin_deposit_rate_5 != row.getMarginDepositRate5() )
      return false;
    if ( margin_claimed_amount_0 != row.getMarginClaimedAmount0() )
      return false;
    if ( margin_claimed_amount_1 != row.getMarginClaimedAmount1() )
      return false;
    if ( margin_claimed_amount_2 != row.getMarginClaimedAmount2() )
      return false;
    if ( margin_claimed_amount_3 != row.getMarginClaimedAmount3() )
      return false;
    if ( margin_claimed_amount_4 != row.getMarginClaimedAmount4() )
      return false;
    if ( margin_claimed_amount_5 != row.getMarginClaimedAmount5() )
      return false;
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) calc_result_margin_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (family_name!=null? family_name.hashCode(): 0) 
        + (sonar_trader_code!=null? sonar_trader_code.hashCode(): 0) 
        + (mark_to_market_div!=null? mark_to_market_div.hashCode(): 0) 
        + ((int) debit_amount_0)
        + ((int) debit_amount_1)
        + ((int) debit_amount_2)
        + ((int) debit_amount_3)
        + ((int) debit_amount_4)
        + ((int) debit_amount_5)
        + ((int) special_debit_amount_0)
        + ((int) special_debit_amount_1)
        + ((int) special_debit_amount_2)
        + ((int) special_debit_amount_3)
        + ((int) special_debit_amount_4)
        + ((int) special_debit_amount_5)
        + ((int) receipt_margin_deposit_0)
        + ((int) receipt_margin_deposit_1)
        + ((int) receipt_margin_deposit_2)
        + ((int) receipt_margin_deposit_3)
        + ((int) receipt_margin_deposit_4)
        + ((int) receipt_margin_deposit_5)
        + ((int) margin_maintenance_amount_0)
        + ((int) margin_maintenance_amount_1)
        + ((int) margin_maintenance_amount_2)
        + ((int) margin_maintenance_amount_3)
        + ((int) margin_maintenance_amount_4)
        + ((int) margin_maintenance_amount_5)
        + ((int) margin_deposit_rate_0)
        + ((int) margin_deposit_rate_1)
        + ((int) margin_deposit_rate_2)
        + ((int) margin_deposit_rate_3)
        + ((int) margin_deposit_rate_4)
        + ((int) margin_deposit_rate_5)
        + ((int) margin_claimed_amount_0)
        + ((int) margin_claimed_amount_1)
        + ((int) margin_claimed_amount_2)
        + ((int) margin_claimed_amount_3)
        + ((int) margin_claimed_amount_4)
        + ((int) margin_claimed_amount_5)
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !calc_result_margin_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'calc_result_margin_id' must be set before inserting.");
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !family_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'family_name' must be set before inserting.");
		if ( !mark_to_market_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'mark_to_market_div' must be set before inserting.");
		if ( !debit_amount_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'debit_amount_0' must be set before inserting.");
		if ( !debit_amount_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'debit_amount_1' must be set before inserting.");
		if ( !debit_amount_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'debit_amount_2' must be set before inserting.");
		if ( !debit_amount_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'debit_amount_3' must be set before inserting.");
		if ( !debit_amount_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'debit_amount_4' must be set before inserting.");
		if ( !debit_amount_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'debit_amount_5' must be set before inserting.");
		if ( !special_debit_amount_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'special_debit_amount_0' must be set before inserting.");
		if ( !special_debit_amount_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'special_debit_amount_1' must be set before inserting.");
		if ( !special_debit_amount_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'special_debit_amount_2' must be set before inserting.");
		if ( !special_debit_amount_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'special_debit_amount_3' must be set before inserting.");
		if ( !special_debit_amount_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'special_debit_amount_4' must be set before inserting.");
		if ( !special_debit_amount_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'special_debit_amount_5' must be set before inserting.");
		if ( !receipt_margin_deposit_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'receipt_margin_deposit_0' must be set before inserting.");
		if ( !receipt_margin_deposit_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'receipt_margin_deposit_1' must be set before inserting.");
		if ( !receipt_margin_deposit_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'receipt_margin_deposit_2' must be set before inserting.");
		if ( !receipt_margin_deposit_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'receipt_margin_deposit_3' must be set before inserting.");
		if ( !receipt_margin_deposit_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'receipt_margin_deposit_4' must be set before inserting.");
		if ( !receipt_margin_deposit_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'receipt_margin_deposit_5' must be set before inserting.");
		if ( !margin_maintenance_amount_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_maintenance_amount_0' must be set before inserting.");
		if ( !margin_maintenance_amount_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_maintenance_amount_1' must be set before inserting.");
		if ( !margin_maintenance_amount_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_maintenance_amount_2' must be set before inserting.");
		if ( !margin_maintenance_amount_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_maintenance_amount_3' must be set before inserting.");
		if ( !margin_maintenance_amount_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_maintenance_amount_4' must be set before inserting.");
		if ( !margin_maintenance_amount_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_maintenance_amount_5' must be set before inserting.");
		if ( !margin_deposit_rate_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_deposit_rate_0' must be set before inserting.");
		if ( !margin_deposit_rate_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_deposit_rate_1' must be set before inserting.");
		if ( !margin_deposit_rate_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_deposit_rate_2' must be set before inserting.");
		if ( !margin_deposit_rate_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_deposit_rate_3' must be set before inserting.");
		if ( !margin_deposit_rate_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_deposit_rate_4' must be set before inserting.");
		if ( !margin_deposit_rate_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_deposit_rate_5' must be set before inserting.");
		if ( !margin_claimed_amount_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_claimed_amount_0' must be set before inserting.");
		if ( !margin_claimed_amount_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_claimed_amount_1' must be set before inserting.");
		if ( !margin_claimed_amount_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_claimed_amount_2' must be set before inserting.");
		if ( !margin_claimed_amount_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_claimed_amount_3' must be set before inserting.");
		if ( !margin_claimed_amount_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_claimed_amount_4' must be set before inserting.");
		if ( !margin_claimed_amount_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'margin_claimed_amount_5' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("calc_result_margin_id",new Long(calc_result_margin_id));
		map.put("institution_code",institution_code);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		map.put("family_name",family_name);
		if ( sonar_trader_code != null )
			map.put("sonar_trader_code",sonar_trader_code);
		map.put("mark_to_market_div",mark_to_market_div);
		map.put("debit_amount_0",new Double(debit_amount_0));
		map.put("debit_amount_1",new Double(debit_amount_1));
		map.put("debit_amount_2",new Double(debit_amount_2));
		map.put("debit_amount_3",new Double(debit_amount_3));
		map.put("debit_amount_4",new Double(debit_amount_4));
		map.put("debit_amount_5",new Double(debit_amount_5));
		map.put("special_debit_amount_0",new Double(special_debit_amount_0));
		map.put("special_debit_amount_1",new Double(special_debit_amount_1));
		map.put("special_debit_amount_2",new Double(special_debit_amount_2));
		map.put("special_debit_amount_3",new Double(special_debit_amount_3));
		map.put("special_debit_amount_4",new Double(special_debit_amount_4));
		map.put("special_debit_amount_5",new Double(special_debit_amount_5));
		map.put("receipt_margin_deposit_0",new Double(receipt_margin_deposit_0));
		map.put("receipt_margin_deposit_1",new Double(receipt_margin_deposit_1));
		map.put("receipt_margin_deposit_2",new Double(receipt_margin_deposit_2));
		map.put("receipt_margin_deposit_3",new Double(receipt_margin_deposit_3));
		map.put("receipt_margin_deposit_4",new Double(receipt_margin_deposit_4));
		map.put("receipt_margin_deposit_5",new Double(receipt_margin_deposit_5));
		map.put("margin_maintenance_amount_0",new Double(margin_maintenance_amount_0));
		map.put("margin_maintenance_amount_1",new Double(margin_maintenance_amount_1));
		map.put("margin_maintenance_amount_2",new Double(margin_maintenance_amount_2));
		map.put("margin_maintenance_amount_3",new Double(margin_maintenance_amount_3));
		map.put("margin_maintenance_amount_4",new Double(margin_maintenance_amount_4));
		map.put("margin_maintenance_amount_5",new Double(margin_maintenance_amount_5));
		map.put("margin_deposit_rate_0",new Double(margin_deposit_rate_0));
		map.put("margin_deposit_rate_1",new Double(margin_deposit_rate_1));
		map.put("margin_deposit_rate_2",new Double(margin_deposit_rate_2));
		map.put("margin_deposit_rate_3",new Double(margin_deposit_rate_3));
		map.put("margin_deposit_rate_4",new Double(margin_deposit_rate_4));
		map.put("margin_deposit_rate_5",new Double(margin_deposit_rate_5));
		map.put("margin_claimed_amount_0",new Double(margin_claimed_amount_0));
		map.put("margin_claimed_amount_1",new Double(margin_claimed_amount_1));
		map.put("margin_claimed_amount_2",new Double(margin_claimed_amount_2));
		map.put("margin_claimed_amount_3",new Double(margin_claimed_amount_3));
		map.put("margin_claimed_amount_4",new Double(margin_claimed_amount_4));
		map.put("margin_claimed_amount_5",new Double(margin_claimed_amount_5));
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( calc_result_margin_id_is_modified )
			map.put("calc_result_margin_id",new Long(calc_result_margin_id));
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( family_name_is_modified )
			map.put("family_name",family_name);
		if ( sonar_trader_code_is_modified )
			map.put("sonar_trader_code",sonar_trader_code);
		if ( mark_to_market_div_is_modified )
			map.put("mark_to_market_div",mark_to_market_div);
		if ( debit_amount_0_is_modified )
			map.put("debit_amount_0",new Double(debit_amount_0));
		if ( debit_amount_1_is_modified )
			map.put("debit_amount_1",new Double(debit_amount_1));
		if ( debit_amount_2_is_modified )
			map.put("debit_amount_2",new Double(debit_amount_2));
		if ( debit_amount_3_is_modified )
			map.put("debit_amount_3",new Double(debit_amount_3));
		if ( debit_amount_4_is_modified )
			map.put("debit_amount_4",new Double(debit_amount_4));
		if ( debit_amount_5_is_modified )
			map.put("debit_amount_5",new Double(debit_amount_5));
		if ( special_debit_amount_0_is_modified )
			map.put("special_debit_amount_0",new Double(special_debit_amount_0));
		if ( special_debit_amount_1_is_modified )
			map.put("special_debit_amount_1",new Double(special_debit_amount_1));
		if ( special_debit_amount_2_is_modified )
			map.put("special_debit_amount_2",new Double(special_debit_amount_2));
		if ( special_debit_amount_3_is_modified )
			map.put("special_debit_amount_3",new Double(special_debit_amount_3));
		if ( special_debit_amount_4_is_modified )
			map.put("special_debit_amount_4",new Double(special_debit_amount_4));
		if ( special_debit_amount_5_is_modified )
			map.put("special_debit_amount_5",new Double(special_debit_amount_5));
		if ( receipt_margin_deposit_0_is_modified )
			map.put("receipt_margin_deposit_0",new Double(receipt_margin_deposit_0));
		if ( receipt_margin_deposit_1_is_modified )
			map.put("receipt_margin_deposit_1",new Double(receipt_margin_deposit_1));
		if ( receipt_margin_deposit_2_is_modified )
			map.put("receipt_margin_deposit_2",new Double(receipt_margin_deposit_2));
		if ( receipt_margin_deposit_3_is_modified )
			map.put("receipt_margin_deposit_3",new Double(receipt_margin_deposit_3));
		if ( receipt_margin_deposit_4_is_modified )
			map.put("receipt_margin_deposit_4",new Double(receipt_margin_deposit_4));
		if ( receipt_margin_deposit_5_is_modified )
			map.put("receipt_margin_deposit_5",new Double(receipt_margin_deposit_5));
		if ( margin_maintenance_amount_0_is_modified )
			map.put("margin_maintenance_amount_0",new Double(margin_maintenance_amount_0));
		if ( margin_maintenance_amount_1_is_modified )
			map.put("margin_maintenance_amount_1",new Double(margin_maintenance_amount_1));
		if ( margin_maintenance_amount_2_is_modified )
			map.put("margin_maintenance_amount_2",new Double(margin_maintenance_amount_2));
		if ( margin_maintenance_amount_3_is_modified )
			map.put("margin_maintenance_amount_3",new Double(margin_maintenance_amount_3));
		if ( margin_maintenance_amount_4_is_modified )
			map.put("margin_maintenance_amount_4",new Double(margin_maintenance_amount_4));
		if ( margin_maintenance_amount_5_is_modified )
			map.put("margin_maintenance_amount_5",new Double(margin_maintenance_amount_5));
		if ( margin_deposit_rate_0_is_modified )
			map.put("margin_deposit_rate_0",new Double(margin_deposit_rate_0));
		if ( margin_deposit_rate_1_is_modified )
			map.put("margin_deposit_rate_1",new Double(margin_deposit_rate_1));
		if ( margin_deposit_rate_2_is_modified )
			map.put("margin_deposit_rate_2",new Double(margin_deposit_rate_2));
		if ( margin_deposit_rate_3_is_modified )
			map.put("margin_deposit_rate_3",new Double(margin_deposit_rate_3));
		if ( margin_deposit_rate_4_is_modified )
			map.put("margin_deposit_rate_4",new Double(margin_deposit_rate_4));
		if ( margin_deposit_rate_5_is_modified )
			map.put("margin_deposit_rate_5",new Double(margin_deposit_rate_5));
		if ( margin_claimed_amount_0_is_modified )
			map.put("margin_claimed_amount_0",new Double(margin_claimed_amount_0));
		if ( margin_claimed_amount_1_is_modified )
			map.put("margin_claimed_amount_1",new Double(margin_claimed_amount_1));
		if ( margin_claimed_amount_2_is_modified )
			map.put("margin_claimed_amount_2",new Double(margin_claimed_amount_2));
		if ( margin_claimed_amount_3_is_modified )
			map.put("margin_claimed_amount_3",new Double(margin_claimed_amount_3));
		if ( margin_claimed_amount_4_is_modified )
			map.put("margin_claimed_amount_4",new Double(margin_claimed_amount_4));
		if ( margin_claimed_amount_5_is_modified )
			map.put("margin_claimed_amount_5",new Double(margin_claimed_amount_5));
		if (map.size() == 0) {
			if ( calc_result_margin_id_is_set )
				map.put("calc_result_margin_id",new Long(calc_result_margin_id));
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			if ( family_name_is_set )
				map.put("family_name",family_name);
			map.put("sonar_trader_code",sonar_trader_code);
			if ( mark_to_market_div_is_set )
				map.put("mark_to_market_div",mark_to_market_div);
			if ( debit_amount_0_is_set )
				map.put("debit_amount_0",new Double(debit_amount_0));
			if ( debit_amount_1_is_set )
				map.put("debit_amount_1",new Double(debit_amount_1));
			if ( debit_amount_2_is_set )
				map.put("debit_amount_2",new Double(debit_amount_2));
			if ( debit_amount_3_is_set )
				map.put("debit_amount_3",new Double(debit_amount_3));
			if ( debit_amount_4_is_set )
				map.put("debit_amount_4",new Double(debit_amount_4));
			if ( debit_amount_5_is_set )
				map.put("debit_amount_5",new Double(debit_amount_5));
			if ( special_debit_amount_0_is_set )
				map.put("special_debit_amount_0",new Double(special_debit_amount_0));
			if ( special_debit_amount_1_is_set )
				map.put("special_debit_amount_1",new Double(special_debit_amount_1));
			if ( special_debit_amount_2_is_set )
				map.put("special_debit_amount_2",new Double(special_debit_amount_2));
			if ( special_debit_amount_3_is_set )
				map.put("special_debit_amount_3",new Double(special_debit_amount_3));
			if ( special_debit_amount_4_is_set )
				map.put("special_debit_amount_4",new Double(special_debit_amount_4));
			if ( special_debit_amount_5_is_set )
				map.put("special_debit_amount_5",new Double(special_debit_amount_5));
			if ( receipt_margin_deposit_0_is_set )
				map.put("receipt_margin_deposit_0",new Double(receipt_margin_deposit_0));
			if ( receipt_margin_deposit_1_is_set )
				map.put("receipt_margin_deposit_1",new Double(receipt_margin_deposit_1));
			if ( receipt_margin_deposit_2_is_set )
				map.put("receipt_margin_deposit_2",new Double(receipt_margin_deposit_2));
			if ( receipt_margin_deposit_3_is_set )
				map.put("receipt_margin_deposit_3",new Double(receipt_margin_deposit_3));
			if ( receipt_margin_deposit_4_is_set )
				map.put("receipt_margin_deposit_4",new Double(receipt_margin_deposit_4));
			if ( receipt_margin_deposit_5_is_set )
				map.put("receipt_margin_deposit_5",new Double(receipt_margin_deposit_5));
			if ( margin_maintenance_amount_0_is_set )
				map.put("margin_maintenance_amount_0",new Double(margin_maintenance_amount_0));
			if ( margin_maintenance_amount_1_is_set )
				map.put("margin_maintenance_amount_1",new Double(margin_maintenance_amount_1));
			if ( margin_maintenance_amount_2_is_set )
				map.put("margin_maintenance_amount_2",new Double(margin_maintenance_amount_2));
			if ( margin_maintenance_amount_3_is_set )
				map.put("margin_maintenance_amount_3",new Double(margin_maintenance_amount_3));
			if ( margin_maintenance_amount_4_is_set )
				map.put("margin_maintenance_amount_4",new Double(margin_maintenance_amount_4));
			if ( margin_maintenance_amount_5_is_set )
				map.put("margin_maintenance_amount_5",new Double(margin_maintenance_amount_5));
			if ( margin_deposit_rate_0_is_set )
				map.put("margin_deposit_rate_0",new Double(margin_deposit_rate_0));
			if ( margin_deposit_rate_1_is_set )
				map.put("margin_deposit_rate_1",new Double(margin_deposit_rate_1));
			if ( margin_deposit_rate_2_is_set )
				map.put("margin_deposit_rate_2",new Double(margin_deposit_rate_2));
			if ( margin_deposit_rate_3_is_set )
				map.put("margin_deposit_rate_3",new Double(margin_deposit_rate_3));
			if ( margin_deposit_rate_4_is_set )
				map.put("margin_deposit_rate_4",new Double(margin_deposit_rate_4));
			if ( margin_deposit_rate_5_is_set )
				map.put("margin_deposit_rate_5",new Double(margin_deposit_rate_5));
			if ( margin_claimed_amount_0_is_set )
				map.put("margin_claimed_amount_0",new Double(margin_claimed_amount_0));
			if ( margin_claimed_amount_1_is_set )
				map.put("margin_claimed_amount_1",new Double(margin_claimed_amount_1));
			if ( margin_claimed_amount_2_is_set )
				map.put("margin_claimed_amount_2",new Double(margin_claimed_amount_2));
			if ( margin_claimed_amount_3_is_set )
				map.put("margin_claimed_amount_3",new Double(margin_claimed_amount_3));
			if ( margin_claimed_amount_4_is_set )
				map.put("margin_claimed_amount_4",new Double(margin_claimed_amount_4));
			if ( margin_claimed_amount_5_is_set )
				map.put("margin_claimed_amount_5",new Double(margin_claimed_amount_5));
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
   * <em>debit_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDebitAmount0()
  {
    return debit_amount_0;
  }


  /** 
   * <em>debit_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitAmount0IsSet() {
    return debit_amount_0_is_set;
  }


  /** 
   * <em>debit_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitAmount0IsModified() {
    return debit_amount_0_is_modified;
  }


  /** 
   * <em>debit_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDebitAmount1()
  {
    return debit_amount_1;
  }


  /** 
   * <em>debit_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitAmount1IsSet() {
    return debit_amount_1_is_set;
  }


  /** 
   * <em>debit_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitAmount1IsModified() {
    return debit_amount_1_is_modified;
  }


  /** 
   * <em>debit_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDebitAmount2()
  {
    return debit_amount_2;
  }


  /** 
   * <em>debit_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitAmount2IsSet() {
    return debit_amount_2_is_set;
  }


  /** 
   * <em>debit_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitAmount2IsModified() {
    return debit_amount_2_is_modified;
  }


  /** 
   * <em>debit_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDebitAmount3()
  {
    return debit_amount_3;
  }


  /** 
   * <em>debit_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitAmount3IsSet() {
    return debit_amount_3_is_set;
  }


  /** 
   * <em>debit_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitAmount3IsModified() {
    return debit_amount_3_is_modified;
  }


  /** 
   * <em>debit_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDebitAmount4()
  {
    return debit_amount_4;
  }


  /** 
   * <em>debit_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitAmount4IsSet() {
    return debit_amount_4_is_set;
  }


  /** 
   * <em>debit_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitAmount4IsModified() {
    return debit_amount_4_is_modified;
  }


  /** 
   * <em>debit_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getDebitAmount5()
  {
    return debit_amount_5;
  }


  /** 
   * <em>debit_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitAmount5IsSet() {
    return debit_amount_5_is_set;
  }


  /** 
   * <em>debit_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDebitAmount5IsModified() {
    return debit_amount_5_is_modified;
  }


  /** 
   * <em>special_debit_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSpecialDebitAmount0()
  {
    return special_debit_amount_0;
  }


  /** 
   * <em>special_debit_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialDebitAmount0IsSet() {
    return special_debit_amount_0_is_set;
  }


  /** 
   * <em>special_debit_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialDebitAmount0IsModified() {
    return special_debit_amount_0_is_modified;
  }


  /** 
   * <em>special_debit_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSpecialDebitAmount1()
  {
    return special_debit_amount_1;
  }


  /** 
   * <em>special_debit_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialDebitAmount1IsSet() {
    return special_debit_amount_1_is_set;
  }


  /** 
   * <em>special_debit_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialDebitAmount1IsModified() {
    return special_debit_amount_1_is_modified;
  }


  /** 
   * <em>special_debit_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSpecialDebitAmount2()
  {
    return special_debit_amount_2;
  }


  /** 
   * <em>special_debit_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialDebitAmount2IsSet() {
    return special_debit_amount_2_is_set;
  }


  /** 
   * <em>special_debit_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialDebitAmount2IsModified() {
    return special_debit_amount_2_is_modified;
  }


  /** 
   * <em>special_debit_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSpecialDebitAmount3()
  {
    return special_debit_amount_3;
  }


  /** 
   * <em>special_debit_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialDebitAmount3IsSet() {
    return special_debit_amount_3_is_set;
  }


  /** 
   * <em>special_debit_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialDebitAmount3IsModified() {
    return special_debit_amount_3_is_modified;
  }


  /** 
   * <em>special_debit_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSpecialDebitAmount4()
  {
    return special_debit_amount_4;
  }


  /** 
   * <em>special_debit_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialDebitAmount4IsSet() {
    return special_debit_amount_4_is_set;
  }


  /** 
   * <em>special_debit_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialDebitAmount4IsModified() {
    return special_debit_amount_4_is_modified;
  }


  /** 
   * <em>special_debit_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSpecialDebitAmount5()
  {
    return special_debit_amount_5;
  }


  /** 
   * <em>special_debit_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialDebitAmount5IsSet() {
    return special_debit_amount_5_is_set;
  }


  /** 
   * <em>special_debit_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSpecialDebitAmount5IsModified() {
    return special_debit_amount_5_is_modified;
  }


  /** 
   * <em>receipt_margin_deposit_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getReceiptMarginDeposit0()
  {
    return receipt_margin_deposit_0;
  }


  /** 
   * <em>receipt_margin_deposit_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptMarginDeposit0IsSet() {
    return receipt_margin_deposit_0_is_set;
  }


  /** 
   * <em>receipt_margin_deposit_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptMarginDeposit0IsModified() {
    return receipt_margin_deposit_0_is_modified;
  }


  /** 
   * <em>receipt_margin_deposit_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getReceiptMarginDeposit1()
  {
    return receipt_margin_deposit_1;
  }


  /** 
   * <em>receipt_margin_deposit_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptMarginDeposit1IsSet() {
    return receipt_margin_deposit_1_is_set;
  }


  /** 
   * <em>receipt_margin_deposit_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptMarginDeposit1IsModified() {
    return receipt_margin_deposit_1_is_modified;
  }


  /** 
   * <em>receipt_margin_deposit_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getReceiptMarginDeposit2()
  {
    return receipt_margin_deposit_2;
  }


  /** 
   * <em>receipt_margin_deposit_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptMarginDeposit2IsSet() {
    return receipt_margin_deposit_2_is_set;
  }


  /** 
   * <em>receipt_margin_deposit_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptMarginDeposit2IsModified() {
    return receipt_margin_deposit_2_is_modified;
  }


  /** 
   * <em>receipt_margin_deposit_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getReceiptMarginDeposit3()
  {
    return receipt_margin_deposit_3;
  }


  /** 
   * <em>receipt_margin_deposit_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptMarginDeposit3IsSet() {
    return receipt_margin_deposit_3_is_set;
  }


  /** 
   * <em>receipt_margin_deposit_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptMarginDeposit3IsModified() {
    return receipt_margin_deposit_3_is_modified;
  }


  /** 
   * <em>receipt_margin_deposit_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getReceiptMarginDeposit4()
  {
    return receipt_margin_deposit_4;
  }


  /** 
   * <em>receipt_margin_deposit_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptMarginDeposit4IsSet() {
    return receipt_margin_deposit_4_is_set;
  }


  /** 
   * <em>receipt_margin_deposit_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptMarginDeposit4IsModified() {
    return receipt_margin_deposit_4_is_modified;
  }


  /** 
   * <em>receipt_margin_deposit_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getReceiptMarginDeposit5()
  {
    return receipt_margin_deposit_5;
  }


  /** 
   * <em>receipt_margin_deposit_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptMarginDeposit5IsSet() {
    return receipt_margin_deposit_5_is_set;
  }


  /** 
   * <em>receipt_margin_deposit_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReceiptMarginDeposit5IsModified() {
    return receipt_margin_deposit_5_is_modified;
  }


  /** 
   * <em>margin_maintenance_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginMaintenanceAmount0()
  {
    return margin_maintenance_amount_0;
  }


  /** 
   * <em>margin_maintenance_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginMaintenanceAmount0IsSet() {
    return margin_maintenance_amount_0_is_set;
  }


  /** 
   * <em>margin_maintenance_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginMaintenanceAmount0IsModified() {
    return margin_maintenance_amount_0_is_modified;
  }


  /** 
   * <em>margin_maintenance_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginMaintenanceAmount1()
  {
    return margin_maintenance_amount_1;
  }


  /** 
   * <em>margin_maintenance_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginMaintenanceAmount1IsSet() {
    return margin_maintenance_amount_1_is_set;
  }


  /** 
   * <em>margin_maintenance_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginMaintenanceAmount1IsModified() {
    return margin_maintenance_amount_1_is_modified;
  }


  /** 
   * <em>margin_maintenance_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginMaintenanceAmount2()
  {
    return margin_maintenance_amount_2;
  }


  /** 
   * <em>margin_maintenance_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginMaintenanceAmount2IsSet() {
    return margin_maintenance_amount_2_is_set;
  }


  /** 
   * <em>margin_maintenance_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginMaintenanceAmount2IsModified() {
    return margin_maintenance_amount_2_is_modified;
  }


  /** 
   * <em>margin_maintenance_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginMaintenanceAmount3()
  {
    return margin_maintenance_amount_3;
  }


  /** 
   * <em>margin_maintenance_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginMaintenanceAmount3IsSet() {
    return margin_maintenance_amount_3_is_set;
  }


  /** 
   * <em>margin_maintenance_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginMaintenanceAmount3IsModified() {
    return margin_maintenance_amount_3_is_modified;
  }


  /** 
   * <em>margin_maintenance_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginMaintenanceAmount4()
  {
    return margin_maintenance_amount_4;
  }


  /** 
   * <em>margin_maintenance_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginMaintenanceAmount4IsSet() {
    return margin_maintenance_amount_4_is_set;
  }


  /** 
   * <em>margin_maintenance_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginMaintenanceAmount4IsModified() {
    return margin_maintenance_amount_4_is_modified;
  }


  /** 
   * <em>margin_maintenance_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginMaintenanceAmount5()
  {
    return margin_maintenance_amount_5;
  }


  /** 
   * <em>margin_maintenance_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginMaintenanceAmount5IsSet() {
    return margin_maintenance_amount_5_is_set;
  }


  /** 
   * <em>margin_maintenance_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginMaintenanceAmount5IsModified() {
    return margin_maintenance_amount_5_is_modified;
  }


  /** 
   * <em>margin_deposit_rate_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginDepositRate0()
  {
    return margin_deposit_rate_0;
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
    return margin_deposit_rate_1;
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
    return margin_deposit_rate_2;
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
    return margin_deposit_rate_3;
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
    return margin_deposit_rate_4;
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
   * <em>margin_deposit_rate_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginDepositRate5()
  {
    return margin_deposit_rate_5;
  }


  /** 
   * <em>margin_deposit_rate_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRate5IsSet() {
    return margin_deposit_rate_5_is_set;
  }


  /** 
   * <em>margin_deposit_rate_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginDepositRate5IsModified() {
    return margin_deposit_rate_5_is_modified;
  }


  /** 
   * <em>margin_claimed_amount_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginClaimedAmount0()
  {
    return margin_claimed_amount_0;
  }


  /** 
   * <em>margin_claimed_amount_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginClaimedAmount0IsSet() {
    return margin_claimed_amount_0_is_set;
  }


  /** 
   * <em>margin_claimed_amount_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginClaimedAmount0IsModified() {
    return margin_claimed_amount_0_is_modified;
  }


  /** 
   * <em>margin_claimed_amount_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginClaimedAmount1()
  {
    return margin_claimed_amount_1;
  }


  /** 
   * <em>margin_claimed_amount_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginClaimedAmount1IsSet() {
    return margin_claimed_amount_1_is_set;
  }


  /** 
   * <em>margin_claimed_amount_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginClaimedAmount1IsModified() {
    return margin_claimed_amount_1_is_modified;
  }


  /** 
   * <em>margin_claimed_amount_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginClaimedAmount2()
  {
    return margin_claimed_amount_2;
  }


  /** 
   * <em>margin_claimed_amount_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginClaimedAmount2IsSet() {
    return margin_claimed_amount_2_is_set;
  }


  /** 
   * <em>margin_claimed_amount_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginClaimedAmount2IsModified() {
    return margin_claimed_amount_2_is_modified;
  }


  /** 
   * <em>margin_claimed_amount_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginClaimedAmount3()
  {
    return margin_claimed_amount_3;
  }


  /** 
   * <em>margin_claimed_amount_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginClaimedAmount3IsSet() {
    return margin_claimed_amount_3_is_set;
  }


  /** 
   * <em>margin_claimed_amount_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginClaimedAmount3IsModified() {
    return margin_claimed_amount_3_is_modified;
  }


  /** 
   * <em>margin_claimed_amount_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginClaimedAmount4()
  {
    return margin_claimed_amount_4;
  }


  /** 
   * <em>margin_claimed_amount_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginClaimedAmount4IsSet() {
    return margin_claimed_amount_4_is_set;
  }


  /** 
   * <em>margin_claimed_amount_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginClaimedAmount4IsModified() {
    return margin_claimed_amount_4_is_modified;
  }


  /** 
   * <em>margin_claimed_amount_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getMarginClaimedAmount5()
  {
    return margin_claimed_amount_5;
  }


  /** 
   * <em>margin_claimed_amount_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginClaimedAmount5IsSet() {
    return margin_claimed_amount_5_is_set;
  }


  /** 
   * <em>margin_claimed_amount_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarginClaimedAmount5IsModified() {
    return margin_claimed_amount_5_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    throw new UnsupportedOperationException("Table requisition_account_margin has no primary key.");
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
   * @@param p_branchCode <em>branch_code</em>カラムの値をあらわす20桁以下のString値 
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
   * <em>debit_amount_0</em>カラムの値を設定します。 
   *
   * @@param p_debitAmount0 <em>debit_amount_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDebitAmount0( double p_debitAmount0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    debit_amount_0 = p_debitAmount0;
    debit_amount_0_is_set = true;
    debit_amount_0_is_modified = true;
  }


  /** 
   * <em>debit_amount_1</em>カラムの値を設定します。 
   *
   * @@param p_debitAmount1 <em>debit_amount_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDebitAmount1( double p_debitAmount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    debit_amount_1 = p_debitAmount1;
    debit_amount_1_is_set = true;
    debit_amount_1_is_modified = true;
  }


  /** 
   * <em>debit_amount_2</em>カラムの値を設定します。 
   *
   * @@param p_debitAmount2 <em>debit_amount_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDebitAmount2( double p_debitAmount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    debit_amount_2 = p_debitAmount2;
    debit_amount_2_is_set = true;
    debit_amount_2_is_modified = true;
  }


  /** 
   * <em>debit_amount_3</em>カラムの値を設定します。 
   *
   * @@param p_debitAmount3 <em>debit_amount_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDebitAmount3( double p_debitAmount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    debit_amount_3 = p_debitAmount3;
    debit_amount_3_is_set = true;
    debit_amount_3_is_modified = true;
  }


  /** 
   * <em>debit_amount_4</em>カラムの値を設定します。 
   *
   * @@param p_debitAmount4 <em>debit_amount_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDebitAmount4( double p_debitAmount4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    debit_amount_4 = p_debitAmount4;
    debit_amount_4_is_set = true;
    debit_amount_4_is_modified = true;
  }


  /** 
   * <em>debit_amount_5</em>カラムの値を設定します。 
   *
   * @@param p_debitAmount5 <em>debit_amount_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setDebitAmount5( double p_debitAmount5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    debit_amount_5 = p_debitAmount5;
    debit_amount_5_is_set = true;
    debit_amount_5_is_modified = true;
  }


  /** 
   * <em>special_debit_amount_0</em>カラムの値を設定します。 
   *
   * @@param p_specialDebitAmount0 <em>special_debit_amount_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSpecialDebitAmount0( double p_specialDebitAmount0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_debit_amount_0 = p_specialDebitAmount0;
    special_debit_amount_0_is_set = true;
    special_debit_amount_0_is_modified = true;
  }


  /** 
   * <em>special_debit_amount_1</em>カラムの値を設定します。 
   *
   * @@param p_specialDebitAmount1 <em>special_debit_amount_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSpecialDebitAmount1( double p_specialDebitAmount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_debit_amount_1 = p_specialDebitAmount1;
    special_debit_amount_1_is_set = true;
    special_debit_amount_1_is_modified = true;
  }


  /** 
   * <em>special_debit_amount_2</em>カラムの値を設定します。 
   *
   * @@param p_specialDebitAmount2 <em>special_debit_amount_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSpecialDebitAmount2( double p_specialDebitAmount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_debit_amount_2 = p_specialDebitAmount2;
    special_debit_amount_2_is_set = true;
    special_debit_amount_2_is_modified = true;
  }


  /** 
   * <em>special_debit_amount_3</em>カラムの値を設定します。 
   *
   * @@param p_specialDebitAmount3 <em>special_debit_amount_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSpecialDebitAmount3( double p_specialDebitAmount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_debit_amount_3 = p_specialDebitAmount3;
    special_debit_amount_3_is_set = true;
    special_debit_amount_3_is_modified = true;
  }


  /** 
   * <em>special_debit_amount_4</em>カラムの値を設定します。 
   *
   * @@param p_specialDebitAmount4 <em>special_debit_amount_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSpecialDebitAmount4( double p_specialDebitAmount4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_debit_amount_4 = p_specialDebitAmount4;
    special_debit_amount_4_is_set = true;
    special_debit_amount_4_is_modified = true;
  }


  /** 
   * <em>special_debit_amount_5</em>カラムの値を設定します。 
   *
   * @@param p_specialDebitAmount5 <em>special_debit_amount_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSpecialDebitAmount5( double p_specialDebitAmount5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    special_debit_amount_5 = p_specialDebitAmount5;
    special_debit_amount_5_is_set = true;
    special_debit_amount_5_is_modified = true;
  }


  /** 
   * <em>receipt_margin_deposit_0</em>カラムの値を設定します。 
   *
   * @@param p_receiptMarginDeposit0 <em>receipt_margin_deposit_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setReceiptMarginDeposit0( double p_receiptMarginDeposit0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    receipt_margin_deposit_0 = p_receiptMarginDeposit0;
    receipt_margin_deposit_0_is_set = true;
    receipt_margin_deposit_0_is_modified = true;
  }


  /** 
   * <em>receipt_margin_deposit_1</em>カラムの値を設定します。 
   *
   * @@param p_receiptMarginDeposit1 <em>receipt_margin_deposit_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setReceiptMarginDeposit1( double p_receiptMarginDeposit1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    receipt_margin_deposit_1 = p_receiptMarginDeposit1;
    receipt_margin_deposit_1_is_set = true;
    receipt_margin_deposit_1_is_modified = true;
  }


  /** 
   * <em>receipt_margin_deposit_2</em>カラムの値を設定します。 
   *
   * @@param p_receiptMarginDeposit2 <em>receipt_margin_deposit_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setReceiptMarginDeposit2( double p_receiptMarginDeposit2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    receipt_margin_deposit_2 = p_receiptMarginDeposit2;
    receipt_margin_deposit_2_is_set = true;
    receipt_margin_deposit_2_is_modified = true;
  }


  /** 
   * <em>receipt_margin_deposit_3</em>カラムの値を設定します。 
   *
   * @@param p_receiptMarginDeposit3 <em>receipt_margin_deposit_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setReceiptMarginDeposit3( double p_receiptMarginDeposit3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    receipt_margin_deposit_3 = p_receiptMarginDeposit3;
    receipt_margin_deposit_3_is_set = true;
    receipt_margin_deposit_3_is_modified = true;
  }


  /** 
   * <em>receipt_margin_deposit_4</em>カラムの値を設定します。 
   *
   * @@param p_receiptMarginDeposit4 <em>receipt_margin_deposit_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setReceiptMarginDeposit4( double p_receiptMarginDeposit4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    receipt_margin_deposit_4 = p_receiptMarginDeposit4;
    receipt_margin_deposit_4_is_set = true;
    receipt_margin_deposit_4_is_modified = true;
  }


  /** 
   * <em>receipt_margin_deposit_5</em>カラムの値を設定します。 
   *
   * @@param p_receiptMarginDeposit5 <em>receipt_margin_deposit_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setReceiptMarginDeposit5( double p_receiptMarginDeposit5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    receipt_margin_deposit_5 = p_receiptMarginDeposit5;
    receipt_margin_deposit_5_is_set = true;
    receipt_margin_deposit_5_is_modified = true;
  }


  /** 
   * <em>margin_maintenance_amount_0</em>カラムの値を設定します。 
   *
   * @@param p_marginMaintenanceAmount0 <em>margin_maintenance_amount_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginMaintenanceAmount0( double p_marginMaintenanceAmount0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_maintenance_amount_0 = p_marginMaintenanceAmount0;
    margin_maintenance_amount_0_is_set = true;
    margin_maintenance_amount_0_is_modified = true;
  }


  /** 
   * <em>margin_maintenance_amount_1</em>カラムの値を設定します。 
   *
   * @@param p_marginMaintenanceAmount1 <em>margin_maintenance_amount_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginMaintenanceAmount1( double p_marginMaintenanceAmount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_maintenance_amount_1 = p_marginMaintenanceAmount1;
    margin_maintenance_amount_1_is_set = true;
    margin_maintenance_amount_1_is_modified = true;
  }


  /** 
   * <em>margin_maintenance_amount_2</em>カラムの値を設定します。 
   *
   * @@param p_marginMaintenanceAmount2 <em>margin_maintenance_amount_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginMaintenanceAmount2( double p_marginMaintenanceAmount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_maintenance_amount_2 = p_marginMaintenanceAmount2;
    margin_maintenance_amount_2_is_set = true;
    margin_maintenance_amount_2_is_modified = true;
  }


  /** 
   * <em>margin_maintenance_amount_3</em>カラムの値を設定します。 
   *
   * @@param p_marginMaintenanceAmount3 <em>margin_maintenance_amount_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginMaintenanceAmount3( double p_marginMaintenanceAmount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_maintenance_amount_3 = p_marginMaintenanceAmount3;
    margin_maintenance_amount_3_is_set = true;
    margin_maintenance_amount_3_is_modified = true;
  }


  /** 
   * <em>margin_maintenance_amount_4</em>カラムの値を設定します。 
   *
   * @@param p_marginMaintenanceAmount4 <em>margin_maintenance_amount_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginMaintenanceAmount4( double p_marginMaintenanceAmount4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_maintenance_amount_4 = p_marginMaintenanceAmount4;
    margin_maintenance_amount_4_is_set = true;
    margin_maintenance_amount_4_is_modified = true;
  }


  /** 
   * <em>margin_maintenance_amount_5</em>カラムの値を設定します。 
   *
   * @@param p_marginMaintenanceAmount5 <em>margin_maintenance_amount_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginMaintenanceAmount5( double p_marginMaintenanceAmount5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_maintenance_amount_5 = p_marginMaintenanceAmount5;
    margin_maintenance_amount_5_is_set = true;
    margin_maintenance_amount_5_is_modified = true;
  }


  /** 
   * <em>margin_deposit_rate_0</em>カラムの値を設定します。 
   *
   * @@param p_marginDepositRate0 <em>margin_deposit_rate_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginDepositRate0( double p_marginDepositRate0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_0 = p_marginDepositRate0;
    margin_deposit_rate_0_is_set = true;
    margin_deposit_rate_0_is_modified = true;
  }


  /** 
   * <em>margin_deposit_rate_1</em>カラムの値を設定します。 
   *
   * @@param p_marginDepositRate1 <em>margin_deposit_rate_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginDepositRate1( double p_marginDepositRate1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_1 = p_marginDepositRate1;
    margin_deposit_rate_1_is_set = true;
    margin_deposit_rate_1_is_modified = true;
  }


  /** 
   * <em>margin_deposit_rate_2</em>カラムの値を設定します。 
   *
   * @@param p_marginDepositRate2 <em>margin_deposit_rate_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginDepositRate2( double p_marginDepositRate2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_2 = p_marginDepositRate2;
    margin_deposit_rate_2_is_set = true;
    margin_deposit_rate_2_is_modified = true;
  }


  /** 
   * <em>margin_deposit_rate_3</em>カラムの値を設定します。 
   *
   * @@param p_marginDepositRate3 <em>margin_deposit_rate_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginDepositRate3( double p_marginDepositRate3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_3 = p_marginDepositRate3;
    margin_deposit_rate_3_is_set = true;
    margin_deposit_rate_3_is_modified = true;
  }


  /** 
   * <em>margin_deposit_rate_4</em>カラムの値を設定します。 
   *
   * @@param p_marginDepositRate4 <em>margin_deposit_rate_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginDepositRate4( double p_marginDepositRate4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_4 = p_marginDepositRate4;
    margin_deposit_rate_4_is_set = true;
    margin_deposit_rate_4_is_modified = true;
  }


  /** 
   * <em>margin_deposit_rate_5</em>カラムの値を設定します。 
   *
   * @@param p_marginDepositRate5 <em>margin_deposit_rate_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginDepositRate5( double p_marginDepositRate5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_deposit_rate_5 = p_marginDepositRate5;
    margin_deposit_rate_5_is_set = true;
    margin_deposit_rate_5_is_modified = true;
  }


  /** 
   * <em>margin_claimed_amount_0</em>カラムの値を設定します。 
   *
   * @@param p_marginClaimedAmount0 <em>margin_claimed_amount_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginClaimedAmount0( double p_marginClaimedAmount0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_claimed_amount_0 = p_marginClaimedAmount0;
    margin_claimed_amount_0_is_set = true;
    margin_claimed_amount_0_is_modified = true;
  }


  /** 
   * <em>margin_claimed_amount_1</em>カラムの値を設定します。 
   *
   * @@param p_marginClaimedAmount1 <em>margin_claimed_amount_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginClaimedAmount1( double p_marginClaimedAmount1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_claimed_amount_1 = p_marginClaimedAmount1;
    margin_claimed_amount_1_is_set = true;
    margin_claimed_amount_1_is_modified = true;
  }


  /** 
   * <em>margin_claimed_amount_2</em>カラムの値を設定します。 
   *
   * @@param p_marginClaimedAmount2 <em>margin_claimed_amount_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginClaimedAmount2( double p_marginClaimedAmount2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_claimed_amount_2 = p_marginClaimedAmount2;
    margin_claimed_amount_2_is_set = true;
    margin_claimed_amount_2_is_modified = true;
  }


  /** 
   * <em>margin_claimed_amount_3</em>カラムの値を設定します。 
   *
   * @@param p_marginClaimedAmount3 <em>margin_claimed_amount_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginClaimedAmount3( double p_marginClaimedAmount3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_claimed_amount_3 = p_marginClaimedAmount3;
    margin_claimed_amount_3_is_set = true;
    margin_claimed_amount_3_is_modified = true;
  }


  /** 
   * <em>margin_claimed_amount_4</em>カラムの値を設定します。 
   *
   * @@param p_marginClaimedAmount4 <em>margin_claimed_amount_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginClaimedAmount4( double p_marginClaimedAmount4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_claimed_amount_4 = p_marginClaimedAmount4;
    margin_claimed_amount_4_is_set = true;
    margin_claimed_amount_4_is_modified = true;
  }


  /** 
   * <em>margin_claimed_amount_5</em>カラムの値を設定します。 
   *
   * @@param p_marginClaimedAmount5 <em>margin_claimed_amount_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setMarginClaimedAmount5( double p_marginClaimedAmount5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    margin_claimed_amount_5 = p_marginClaimedAmount5;
    margin_claimed_amount_5_is_set = true;
    margin_claimed_amount_5_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("calc_result_margin_id") ) {
                    return new Long( calc_result_margin_id );
                }
                break;
            case 'd':
                if ( name.equals("debit_amount_0") ) {
                    return new Double( debit_amount_0 );
                }
                else if ( name.equals("debit_amount_1") ) {
                    return new Double( debit_amount_1 );
                }
                else if ( name.equals("debit_amount_2") ) {
                    return new Double( debit_amount_2 );
                }
                else if ( name.equals("debit_amount_3") ) {
                    return new Double( debit_amount_3 );
                }
                else if ( name.equals("debit_amount_4") ) {
                    return new Double( debit_amount_4 );
                }
                else if ( name.equals("debit_amount_5") ) {
                    return new Double( debit_amount_5 );
                }
                break;
            case 'f':
                if ( name.equals("family_name") ) {
                    return this.family_name;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'm':
                if ( name.equals("mark_to_market_div") ) {
                    return this.mark_to_market_div;
                }
                else if ( name.equals("margin_maintenance_amount_0") ) {
                    return new Double( margin_maintenance_amount_0 );
                }
                else if ( name.equals("margin_maintenance_amount_1") ) {
                    return new Double( margin_maintenance_amount_1 );
                }
                else if ( name.equals("margin_maintenance_amount_2") ) {
                    return new Double( margin_maintenance_amount_2 );
                }
                else if ( name.equals("margin_maintenance_amount_3") ) {
                    return new Double( margin_maintenance_amount_3 );
                }
                else if ( name.equals("margin_maintenance_amount_4") ) {
                    return new Double( margin_maintenance_amount_4 );
                }
                else if ( name.equals("margin_maintenance_amount_5") ) {
                    return new Double( margin_maintenance_amount_5 );
                }
                else if ( name.equals("margin_deposit_rate_0") ) {
                    return new Double( margin_deposit_rate_0 );
                }
                else if ( name.equals("margin_deposit_rate_1") ) {
                    return new Double( margin_deposit_rate_1 );
                }
                else if ( name.equals("margin_deposit_rate_2") ) {
                    return new Double( margin_deposit_rate_2 );
                }
                else if ( name.equals("margin_deposit_rate_3") ) {
                    return new Double( margin_deposit_rate_3 );
                }
                else if ( name.equals("margin_deposit_rate_4") ) {
                    return new Double( margin_deposit_rate_4 );
                }
                else if ( name.equals("margin_deposit_rate_5") ) {
                    return new Double( margin_deposit_rate_5 );
                }
                else if ( name.equals("margin_claimed_amount_0") ) {
                    return new Double( margin_claimed_amount_0 );
                }
                else if ( name.equals("margin_claimed_amount_1") ) {
                    return new Double( margin_claimed_amount_1 );
                }
                else if ( name.equals("margin_claimed_amount_2") ) {
                    return new Double( margin_claimed_amount_2 );
                }
                else if ( name.equals("margin_claimed_amount_3") ) {
                    return new Double( margin_claimed_amount_3 );
                }
                else if ( name.equals("margin_claimed_amount_4") ) {
                    return new Double( margin_claimed_amount_4 );
                }
                else if ( name.equals("margin_claimed_amount_5") ) {
                    return new Double( margin_claimed_amount_5 );
                }
                break;
            case 'r':
                if ( name.equals("receipt_margin_deposit_0") ) {
                    return new Double( receipt_margin_deposit_0 );
                }
                else if ( name.equals("receipt_margin_deposit_1") ) {
                    return new Double( receipt_margin_deposit_1 );
                }
                else if ( name.equals("receipt_margin_deposit_2") ) {
                    return new Double( receipt_margin_deposit_2 );
                }
                else if ( name.equals("receipt_margin_deposit_3") ) {
                    return new Double( receipt_margin_deposit_3 );
                }
                else if ( name.equals("receipt_margin_deposit_4") ) {
                    return new Double( receipt_margin_deposit_4 );
                }
                else if ( name.equals("receipt_margin_deposit_5") ) {
                    return new Double( receipt_margin_deposit_5 );
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code") ) {
                    return this.sonar_trader_code;
                }
                else if ( name.equals("special_debit_amount_0") ) {
                    return new Double( special_debit_amount_0 );
                }
                else if ( name.equals("special_debit_amount_1") ) {
                    return new Double( special_debit_amount_1 );
                }
                else if ( name.equals("special_debit_amount_2") ) {
                    return new Double( special_debit_amount_2 );
                }
                else if ( name.equals("special_debit_amount_3") ) {
                    return new Double( special_debit_amount_3 );
                }
                else if ( name.equals("special_debit_amount_4") ) {
                    return new Double( special_debit_amount_4 );
                }
                else if ( name.equals("special_debit_amount_5") ) {
                    return new Double( special_debit_amount_5 );
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
                if ( name.equals("account_code") ) {
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
                if ( name.equals("calc_result_margin_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'calc_result_margin_id' must be Long: '"+value+"' is not." );
                    this.calc_result_margin_id = ((Long) value).longValue();
                    if (this.calc_result_margin_id_is_set)
                        this.calc_result_margin_id_is_modified = true;
                    this.calc_result_margin_id_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("debit_amount_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'debit_amount_0' must be Double: '"+value+"' is not." );
                    this.debit_amount_0 = ((Double) value).doubleValue();
                    if (this.debit_amount_0_is_set)
                        this.debit_amount_0_is_modified = true;
                    this.debit_amount_0_is_set = true;
                    return;
                }
                else if ( name.equals("debit_amount_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'debit_amount_1' must be Double: '"+value+"' is not." );
                    this.debit_amount_1 = ((Double) value).doubleValue();
                    if (this.debit_amount_1_is_set)
                        this.debit_amount_1_is_modified = true;
                    this.debit_amount_1_is_set = true;
                    return;
                }
                else if ( name.equals("debit_amount_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'debit_amount_2' must be Double: '"+value+"' is not." );
                    this.debit_amount_2 = ((Double) value).doubleValue();
                    if (this.debit_amount_2_is_set)
                        this.debit_amount_2_is_modified = true;
                    this.debit_amount_2_is_set = true;
                    return;
                }
                else if ( name.equals("debit_amount_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'debit_amount_3' must be Double: '"+value+"' is not." );
                    this.debit_amount_3 = ((Double) value).doubleValue();
                    if (this.debit_amount_3_is_set)
                        this.debit_amount_3_is_modified = true;
                    this.debit_amount_3_is_set = true;
                    return;
                }
                else if ( name.equals("debit_amount_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'debit_amount_4' must be Double: '"+value+"' is not." );
                    this.debit_amount_4 = ((Double) value).doubleValue();
                    if (this.debit_amount_4_is_set)
                        this.debit_amount_4_is_modified = true;
                    this.debit_amount_4_is_set = true;
                    return;
                }
                else if ( name.equals("debit_amount_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'debit_amount_5' must be Double: '"+value+"' is not." );
                    this.debit_amount_5 = ((Double) value).doubleValue();
                    if (this.debit_amount_5_is_set)
                        this.debit_amount_5_is_modified = true;
                    this.debit_amount_5_is_set = true;
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
            case 'm':
                if ( name.equals("mark_to_market_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'mark_to_market_div' must be String: '"+value+"' is not." );
                    this.mark_to_market_div = (String) value;
                    if (this.mark_to_market_div_is_set)
                        this.mark_to_market_div_is_modified = true;
                    this.mark_to_market_div_is_set = true;
                    return;
                }
                else if ( name.equals("margin_maintenance_amount_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_maintenance_amount_0' must be Double: '"+value+"' is not." );
                    this.margin_maintenance_amount_0 = ((Double) value).doubleValue();
                    if (this.margin_maintenance_amount_0_is_set)
                        this.margin_maintenance_amount_0_is_modified = true;
                    this.margin_maintenance_amount_0_is_set = true;
                    return;
                }
                else if ( name.equals("margin_maintenance_amount_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_maintenance_amount_1' must be Double: '"+value+"' is not." );
                    this.margin_maintenance_amount_1 = ((Double) value).doubleValue();
                    if (this.margin_maintenance_amount_1_is_set)
                        this.margin_maintenance_amount_1_is_modified = true;
                    this.margin_maintenance_amount_1_is_set = true;
                    return;
                }
                else if ( name.equals("margin_maintenance_amount_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_maintenance_amount_2' must be Double: '"+value+"' is not." );
                    this.margin_maintenance_amount_2 = ((Double) value).doubleValue();
                    if (this.margin_maintenance_amount_2_is_set)
                        this.margin_maintenance_amount_2_is_modified = true;
                    this.margin_maintenance_amount_2_is_set = true;
                    return;
                }
                else if ( name.equals("margin_maintenance_amount_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_maintenance_amount_3' must be Double: '"+value+"' is not." );
                    this.margin_maintenance_amount_3 = ((Double) value).doubleValue();
                    if (this.margin_maintenance_amount_3_is_set)
                        this.margin_maintenance_amount_3_is_modified = true;
                    this.margin_maintenance_amount_3_is_set = true;
                    return;
                }
                else if ( name.equals("margin_maintenance_amount_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_maintenance_amount_4' must be Double: '"+value+"' is not." );
                    this.margin_maintenance_amount_4 = ((Double) value).doubleValue();
                    if (this.margin_maintenance_amount_4_is_set)
                        this.margin_maintenance_amount_4_is_modified = true;
                    this.margin_maintenance_amount_4_is_set = true;
                    return;
                }
                else if ( name.equals("margin_maintenance_amount_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_maintenance_amount_5' must be Double: '"+value+"' is not." );
                    this.margin_maintenance_amount_5 = ((Double) value).doubleValue();
                    if (this.margin_maintenance_amount_5_is_set)
                        this.margin_maintenance_amount_5_is_modified = true;
                    this.margin_maintenance_amount_5_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_rate_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_rate_0' must be Double: '"+value+"' is not." );
                    this.margin_deposit_rate_0 = ((Double) value).doubleValue();
                    if (this.margin_deposit_rate_0_is_set)
                        this.margin_deposit_rate_0_is_modified = true;
                    this.margin_deposit_rate_0_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_rate_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_rate_1' must be Double: '"+value+"' is not." );
                    this.margin_deposit_rate_1 = ((Double) value).doubleValue();
                    if (this.margin_deposit_rate_1_is_set)
                        this.margin_deposit_rate_1_is_modified = true;
                    this.margin_deposit_rate_1_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_rate_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_rate_2' must be Double: '"+value+"' is not." );
                    this.margin_deposit_rate_2 = ((Double) value).doubleValue();
                    if (this.margin_deposit_rate_2_is_set)
                        this.margin_deposit_rate_2_is_modified = true;
                    this.margin_deposit_rate_2_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_rate_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_rate_3' must be Double: '"+value+"' is not." );
                    this.margin_deposit_rate_3 = ((Double) value).doubleValue();
                    if (this.margin_deposit_rate_3_is_set)
                        this.margin_deposit_rate_3_is_modified = true;
                    this.margin_deposit_rate_3_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_rate_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_rate_4' must be Double: '"+value+"' is not." );
                    this.margin_deposit_rate_4 = ((Double) value).doubleValue();
                    if (this.margin_deposit_rate_4_is_set)
                        this.margin_deposit_rate_4_is_modified = true;
                    this.margin_deposit_rate_4_is_set = true;
                    return;
                }
                else if ( name.equals("margin_deposit_rate_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_deposit_rate_5' must be Double: '"+value+"' is not." );
                    this.margin_deposit_rate_5 = ((Double) value).doubleValue();
                    if (this.margin_deposit_rate_5_is_set)
                        this.margin_deposit_rate_5_is_modified = true;
                    this.margin_deposit_rate_5_is_set = true;
                    return;
                }
                else if ( name.equals("margin_claimed_amount_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_claimed_amount_0' must be Double: '"+value+"' is not." );
                    this.margin_claimed_amount_0 = ((Double) value).doubleValue();
                    if (this.margin_claimed_amount_0_is_set)
                        this.margin_claimed_amount_0_is_modified = true;
                    this.margin_claimed_amount_0_is_set = true;
                    return;
                }
                else if ( name.equals("margin_claimed_amount_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_claimed_amount_1' must be Double: '"+value+"' is not." );
                    this.margin_claimed_amount_1 = ((Double) value).doubleValue();
                    if (this.margin_claimed_amount_1_is_set)
                        this.margin_claimed_amount_1_is_modified = true;
                    this.margin_claimed_amount_1_is_set = true;
                    return;
                }
                else if ( name.equals("margin_claimed_amount_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_claimed_amount_2' must be Double: '"+value+"' is not." );
                    this.margin_claimed_amount_2 = ((Double) value).doubleValue();
                    if (this.margin_claimed_amount_2_is_set)
                        this.margin_claimed_amount_2_is_modified = true;
                    this.margin_claimed_amount_2_is_set = true;
                    return;
                }
                else if ( name.equals("margin_claimed_amount_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_claimed_amount_3' must be Double: '"+value+"' is not." );
                    this.margin_claimed_amount_3 = ((Double) value).doubleValue();
                    if (this.margin_claimed_amount_3_is_set)
                        this.margin_claimed_amount_3_is_modified = true;
                    this.margin_claimed_amount_3_is_set = true;
                    return;
                }
                else if ( name.equals("margin_claimed_amount_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_claimed_amount_4' must be Double: '"+value+"' is not." );
                    this.margin_claimed_amount_4 = ((Double) value).doubleValue();
                    if (this.margin_claimed_amount_4_is_set)
                        this.margin_claimed_amount_4_is_modified = true;
                    this.margin_claimed_amount_4_is_set = true;
                    return;
                }
                else if ( name.equals("margin_claimed_amount_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'margin_claimed_amount_5' must be Double: '"+value+"' is not." );
                    this.margin_claimed_amount_5 = ((Double) value).doubleValue();
                    if (this.margin_claimed_amount_5_is_set)
                        this.margin_claimed_amount_5_is_modified = true;
                    this.margin_claimed_amount_5_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("receipt_margin_deposit_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'receipt_margin_deposit_0' must be Double: '"+value+"' is not." );
                    this.receipt_margin_deposit_0 = ((Double) value).doubleValue();
                    if (this.receipt_margin_deposit_0_is_set)
                        this.receipt_margin_deposit_0_is_modified = true;
                    this.receipt_margin_deposit_0_is_set = true;
                    return;
                }
                else if ( name.equals("receipt_margin_deposit_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'receipt_margin_deposit_1' must be Double: '"+value+"' is not." );
                    this.receipt_margin_deposit_1 = ((Double) value).doubleValue();
                    if (this.receipt_margin_deposit_1_is_set)
                        this.receipt_margin_deposit_1_is_modified = true;
                    this.receipt_margin_deposit_1_is_set = true;
                    return;
                }
                else if ( name.equals("receipt_margin_deposit_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'receipt_margin_deposit_2' must be Double: '"+value+"' is not." );
                    this.receipt_margin_deposit_2 = ((Double) value).doubleValue();
                    if (this.receipt_margin_deposit_2_is_set)
                        this.receipt_margin_deposit_2_is_modified = true;
                    this.receipt_margin_deposit_2_is_set = true;
                    return;
                }
                else if ( name.equals("receipt_margin_deposit_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'receipt_margin_deposit_3' must be Double: '"+value+"' is not." );
                    this.receipt_margin_deposit_3 = ((Double) value).doubleValue();
                    if (this.receipt_margin_deposit_3_is_set)
                        this.receipt_margin_deposit_3_is_modified = true;
                    this.receipt_margin_deposit_3_is_set = true;
                    return;
                }
                else if ( name.equals("receipt_margin_deposit_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'receipt_margin_deposit_4' must be Double: '"+value+"' is not." );
                    this.receipt_margin_deposit_4 = ((Double) value).doubleValue();
                    if (this.receipt_margin_deposit_4_is_set)
                        this.receipt_margin_deposit_4_is_modified = true;
                    this.receipt_margin_deposit_4_is_set = true;
                    return;
                }
                else if ( name.equals("receipt_margin_deposit_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'receipt_margin_deposit_5' must be Double: '"+value+"' is not." );
                    this.receipt_margin_deposit_5 = ((Double) value).doubleValue();
                    if (this.receipt_margin_deposit_5_is_set)
                        this.receipt_margin_deposit_5_is_modified = true;
                    this.receipt_margin_deposit_5_is_set = true;
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
                else if ( name.equals("special_debit_amount_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'special_debit_amount_0' must be Double: '"+value+"' is not." );
                    this.special_debit_amount_0 = ((Double) value).doubleValue();
                    if (this.special_debit_amount_0_is_set)
                        this.special_debit_amount_0_is_modified = true;
                    this.special_debit_amount_0_is_set = true;
                    return;
                }
                else if ( name.equals("special_debit_amount_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'special_debit_amount_1' must be Double: '"+value+"' is not." );
                    this.special_debit_amount_1 = ((Double) value).doubleValue();
                    if (this.special_debit_amount_1_is_set)
                        this.special_debit_amount_1_is_modified = true;
                    this.special_debit_amount_1_is_set = true;
                    return;
                }
                else if ( name.equals("special_debit_amount_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'special_debit_amount_2' must be Double: '"+value+"' is not." );
                    this.special_debit_amount_2 = ((Double) value).doubleValue();
                    if (this.special_debit_amount_2_is_set)
                        this.special_debit_amount_2_is_modified = true;
                    this.special_debit_amount_2_is_set = true;
                    return;
                }
                else if ( name.equals("special_debit_amount_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'special_debit_amount_3' must be Double: '"+value+"' is not." );
                    this.special_debit_amount_3 = ((Double) value).doubleValue();
                    if (this.special_debit_amount_3_is_set)
                        this.special_debit_amount_3_is_modified = true;
                    this.special_debit_amount_3_is_set = true;
                    return;
                }
                else if ( name.equals("special_debit_amount_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'special_debit_amount_4' must be Double: '"+value+"' is not." );
                    this.special_debit_amount_4 = ((Double) value).doubleValue();
                    if (this.special_debit_amount_4_is_set)
                        this.special_debit_amount_4_is_modified = true;
                    this.special_debit_amount_4_is_set = true;
                    return;
                }
                else if ( name.equals("special_debit_amount_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'special_debit_amount_5' must be Double: '"+value+"' is not." );
                    this.special_debit_amount_5 = ((Double) value).doubleValue();
                    if (this.special_debit_amount_5_is_set)
                        this.special_debit_amount_5_is_modified = true;
                    this.special_debit_amount_5_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
