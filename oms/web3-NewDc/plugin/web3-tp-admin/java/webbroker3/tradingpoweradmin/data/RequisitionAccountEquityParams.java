head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.58.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	RequisitionAccountEquityParams.java;


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
 * requisition_account_equityテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link RequisitionAccountEquityRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link RequisitionAccountEquityParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link RequisitionAccountEquityParams}が{@@link RequisitionAccountEquityRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RequisitionAccountEquityPK 
 * @@see RequisitionAccountEquityRow 
 */
public class RequisitionAccountEquityParams extends Params implements RequisitionAccountEquityRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "requisition_account_equity";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = RequisitionAccountEquityRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return RequisitionAccountEquityRow.TYPE;
  }


  /** 
   * <em>calc_result_equity_id</em>カラムの値 
   */
  public  long  calc_result_equity_id;

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
   * <em>asset_evaluation_div</em>カラムの値 
   */
  public  String  asset_evaluation_div;

  /** 
   * <em>real_account_balance_0</em>カラムの値 
   */
  public  double  real_account_balance_0;

  /** 
   * <em>real_account_balance_1</em>カラムの値 
   */
  public  double  real_account_balance_1;

  /** 
   * <em>real_account_balance_2</em>カラムの値 
   */
  public  double  real_account_balance_2;

  /** 
   * <em>real_account_balance_3</em>カラムの値 
   */
  public  double  real_account_balance_3;

  /** 
   * <em>real_account_balance_4</em>カラムの値 
   */
  public  double  real_account_balance_4;

  /** 
   * <em>real_account_balance_5</em>カラムの値 
   */
  public  double  real_account_balance_5;

  /** 
   * <em>security_asset_0</em>カラムの値 
   */
  public  double  security_asset_0;

  /** 
   * <em>security_asset_1</em>カラムの値 
   */
  public  double  security_asset_1;

  /** 
   * <em>security_asset_2</em>カラムの値 
   */
  public  double  security_asset_2;

  /** 
   * <em>security_asset_3</em>カラムの値 
   */
  public  double  security_asset_3;

  /** 
   * <em>security_asset_4</em>カラムの値 
   */
  public  double  security_asset_4;

  /** 
   * <em>security_asset_5</em>カラムの値 
   */
  public  double  security_asset_5;

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

  private boolean calc_result_equity_id_is_set = false;
  private boolean calc_result_equity_id_is_modified = false;
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
  private boolean asset_evaluation_div_is_set = false;
  private boolean asset_evaluation_div_is_modified = false;
  private boolean real_account_balance_0_is_set = false;
  private boolean real_account_balance_0_is_modified = false;
  private boolean real_account_balance_1_is_set = false;
  private boolean real_account_balance_1_is_modified = false;
  private boolean real_account_balance_2_is_set = false;
  private boolean real_account_balance_2_is_modified = false;
  private boolean real_account_balance_3_is_set = false;
  private boolean real_account_balance_3_is_modified = false;
  private boolean real_account_balance_4_is_set = false;
  private boolean real_account_balance_4_is_modified = false;
  private boolean real_account_balance_5_is_set = false;
  private boolean real_account_balance_5_is_modified = false;
  private boolean security_asset_0_is_set = false;
  private boolean security_asset_0_is_modified = false;
  private boolean security_asset_1_is_set = false;
  private boolean security_asset_1_is_modified = false;
  private boolean security_asset_2_is_set = false;
  private boolean security_asset_2_is_modified = false;
  private boolean security_asset_3_is_set = false;
  private boolean security_asset_3_is_modified = false;
  private boolean security_asset_4_is_set = false;
  private boolean security_asset_4_is_modified = false;
  private boolean security_asset_5_is_set = false;
  private boolean security_asset_5_is_modified = false;
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


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "calc_result_equity_id=" +calc_result_equity_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "family_name=" +family_name
      + "," + "sonar_trader_code=" +sonar_trader_code
      + "," + "asset_evaluation_div=" +asset_evaluation_div
      + "," + "real_account_balance_0=" +real_account_balance_0
      + "," + "real_account_balance_1=" +real_account_balance_1
      + "," + "real_account_balance_2=" +real_account_balance_2
      + "," + "real_account_balance_3=" +real_account_balance_3
      + "," + "real_account_balance_4=" +real_account_balance_4
      + "," + "real_account_balance_5=" +real_account_balance_5
      + "," + "security_asset_0=" +security_asset_0
      + "," + "security_asset_1=" +security_asset_1
      + "," + "security_asset_2=" +security_asset_2
      + "," + "security_asset_3=" +security_asset_3
      + "," + "security_asset_4=" +security_asset_4
      + "," + "security_asset_5=" +security_asset_5
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
      + "}";
  }


  /** 
   * 値が未設定のRequisitionAccountEquityParamsオブジェクトを作成します。 
   */
  public RequisitionAccountEquityParams() {
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のRequisitionAccountEquityRowオブジェクトの値を利用してRequisitionAccountEquityParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するRequisitionAccountEquityRowオブジェクト 
   */
  public RequisitionAccountEquityParams( RequisitionAccountEquityRow p_row )
  {
    calc_result_equity_id = p_row.getCalcResultEquityId();
    calc_result_equity_id_is_set = p_row.getCalcResultEquityIdIsSet();
    calc_result_equity_id_is_modified = p_row.getCalcResultEquityIdIsModified();
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
    asset_evaluation_div = p_row.getAssetEvaluationDiv();
    asset_evaluation_div_is_set = p_row.getAssetEvaluationDivIsSet();
    asset_evaluation_div_is_modified = p_row.getAssetEvaluationDivIsModified();
    real_account_balance_0 = p_row.getRealAccountBalance0();
    real_account_balance_0_is_set = p_row.getRealAccountBalance0IsSet();
    real_account_balance_0_is_modified = p_row.getRealAccountBalance0IsModified();
    real_account_balance_1 = p_row.getRealAccountBalance1();
    real_account_balance_1_is_set = p_row.getRealAccountBalance1IsSet();
    real_account_balance_1_is_modified = p_row.getRealAccountBalance1IsModified();
    real_account_balance_2 = p_row.getRealAccountBalance2();
    real_account_balance_2_is_set = p_row.getRealAccountBalance2IsSet();
    real_account_balance_2_is_modified = p_row.getRealAccountBalance2IsModified();
    real_account_balance_3 = p_row.getRealAccountBalance3();
    real_account_balance_3_is_set = p_row.getRealAccountBalance3IsSet();
    real_account_balance_3_is_modified = p_row.getRealAccountBalance3IsModified();
    real_account_balance_4 = p_row.getRealAccountBalance4();
    real_account_balance_4_is_set = p_row.getRealAccountBalance4IsSet();
    real_account_balance_4_is_modified = p_row.getRealAccountBalance4IsModified();
    real_account_balance_5 = p_row.getRealAccountBalance5();
    real_account_balance_5_is_set = p_row.getRealAccountBalance5IsSet();
    real_account_balance_5_is_modified = p_row.getRealAccountBalance5IsModified();
    security_asset_0 = p_row.getSecurityAsset0();
    security_asset_0_is_set = p_row.getSecurityAsset0IsSet();
    security_asset_0_is_modified = p_row.getSecurityAsset0IsModified();
    security_asset_1 = p_row.getSecurityAsset1();
    security_asset_1_is_set = p_row.getSecurityAsset1IsSet();
    security_asset_1_is_modified = p_row.getSecurityAsset1IsModified();
    security_asset_2 = p_row.getSecurityAsset2();
    security_asset_2_is_set = p_row.getSecurityAsset2IsSet();
    security_asset_2_is_modified = p_row.getSecurityAsset2IsModified();
    security_asset_3 = p_row.getSecurityAsset3();
    security_asset_3_is_set = p_row.getSecurityAsset3IsSet();
    security_asset_3_is_modified = p_row.getSecurityAsset3IsModified();
    security_asset_4 = p_row.getSecurityAsset4();
    security_asset_4_is_set = p_row.getSecurityAsset4IsSet();
    security_asset_4_is_modified = p_row.getSecurityAsset4IsModified();
    security_asset_5 = p_row.getSecurityAsset5();
    security_asset_5_is_set = p_row.getSecurityAsset5IsSet();
    security_asset_5_is_modified = p_row.getSecurityAsset5IsModified();
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
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      calc_result_equity_id_is_set = true;
      calc_result_equity_id_is_modified = true;
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
      asset_evaluation_div_is_set = true;
      asset_evaluation_div_is_modified = true;
      real_account_balance_0_is_set = true;
      real_account_balance_0_is_modified = true;
      real_account_balance_1_is_set = true;
      real_account_balance_1_is_modified = true;
      real_account_balance_2_is_set = true;
      real_account_balance_2_is_modified = true;
      real_account_balance_3_is_set = true;
      real_account_balance_3_is_modified = true;
      real_account_balance_4_is_set = true;
      real_account_balance_4_is_modified = true;
      real_account_balance_5_is_set = true;
      real_account_balance_5_is_modified = true;
      security_asset_0_is_set = true;
      security_asset_0_is_modified = true;
      security_asset_1_is_set = true;
      security_asset_1_is_modified = true;
      security_asset_2_is_set = true;
      security_asset_2_is_modified = true;
      security_asset_3_is_set = true;
      security_asset_3_is_modified = true;
      security_asset_4_is_set = true;
      security_asset_4_is_modified = true;
      security_asset_5_is_set = true;
      security_asset_5_is_modified = true;
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
    }


  /** 
   * 比較対照のオブジェクトと同一かどうか調べます。 
   * @@param other 比較対照のオブジェクト 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof RequisitionAccountEquityRow ) )
       return false;
    return fieldsEqual( (RequisitionAccountEquityRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のRequisitionAccountEquityRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( RequisitionAccountEquityRow row )
  {
    if ( calc_result_equity_id != row.getCalcResultEquityId() )
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
    if ( asset_evaluation_div == null ) {
      if ( row.getAssetEvaluationDiv() != null )
        return false;
    } else if ( !asset_evaluation_div.equals( row.getAssetEvaluationDiv() ) ) {
        return false;
    }
    if ( real_account_balance_0 != row.getRealAccountBalance0() )
      return false;
    if ( real_account_balance_1 != row.getRealAccountBalance1() )
      return false;
    if ( real_account_balance_2 != row.getRealAccountBalance2() )
      return false;
    if ( real_account_balance_3 != row.getRealAccountBalance3() )
      return false;
    if ( real_account_balance_4 != row.getRealAccountBalance4() )
      return false;
    if ( real_account_balance_5 != row.getRealAccountBalance5() )
      return false;
    if ( security_asset_0 != row.getSecurityAsset0() )
      return false;
    if ( security_asset_1 != row.getSecurityAsset1() )
      return false;
    if ( security_asset_2 != row.getSecurityAsset2() )
      return false;
    if ( security_asset_3 != row.getSecurityAsset3() )
      return false;
    if ( security_asset_4 != row.getSecurityAsset4() )
      return false;
    if ( security_asset_5 != row.getSecurityAsset5() )
      return false;
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
    return true;
  }


  /** 
   * このオブジェクトのハッシュコードを返します。 
   * @@return int。既に算出し保存されたものを返す場合あり 
   */
  public int hashCode() {
      return  ((int) calc_result_equity_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (family_name!=null? family_name.hashCode(): 0) 
        + (sonar_trader_code!=null? sonar_trader_code.hashCode(): 0) 
        + (asset_evaluation_div!=null? asset_evaluation_div.hashCode(): 0) 
        + ((int) real_account_balance_0)
        + ((int) real_account_balance_1)
        + ((int) real_account_balance_2)
        + ((int) real_account_balance_3)
        + ((int) real_account_balance_4)
        + ((int) real_account_balance_5)
        + ((int) security_asset_0)
        + ((int) security_asset_1)
        + ((int) security_asset_2)
        + ((int) security_asset_3)
        + ((int) security_asset_4)
        + ((int) security_asset_5)
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
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !calc_result_equity_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'calc_result_equity_id' must be set before inserting.");
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !account_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'account_code' must be set before inserting.");
		if ( !family_name_is_set )
			throw new IllegalArgumentException("non-nullable field 'family_name' must be set before inserting.");
		if ( !asset_evaluation_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'asset_evaluation_div' must be set before inserting.");
		if ( !real_account_balance_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'real_account_balance_0' must be set before inserting.");
		if ( !real_account_balance_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'real_account_balance_1' must be set before inserting.");
		if ( !real_account_balance_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'real_account_balance_2' must be set before inserting.");
		if ( !real_account_balance_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'real_account_balance_3' must be set before inserting.");
		if ( !real_account_balance_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'real_account_balance_4' must be set before inserting.");
		if ( !real_account_balance_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'real_account_balance_5' must be set before inserting.");
		if ( !security_asset_0_is_set )
			throw new IllegalArgumentException("non-nullable field 'security_asset_0' must be set before inserting.");
		if ( !security_asset_1_is_set )
			throw new IllegalArgumentException("non-nullable field 'security_asset_1' must be set before inserting.");
		if ( !security_asset_2_is_set )
			throw new IllegalArgumentException("non-nullable field 'security_asset_2' must be set before inserting.");
		if ( !security_asset_3_is_set )
			throw new IllegalArgumentException("non-nullable field 'security_asset_3' must be set before inserting.");
		if ( !security_asset_4_is_set )
			throw new IllegalArgumentException("non-nullable field 'security_asset_4' must be set before inserting.");
		if ( !security_asset_5_is_set )
			throw new IllegalArgumentException("non-nullable field 'security_asset_5' must be set before inserting.");
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
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("calc_result_equity_id",new Long(calc_result_equity_id));
		map.put("institution_code",institution_code);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		map.put("account_code",account_code);
		map.put("family_name",family_name);
		if ( sonar_trader_code != null )
			map.put("sonar_trader_code",sonar_trader_code);
		map.put("asset_evaluation_div",asset_evaluation_div);
		map.put("real_account_balance_0",new Double(real_account_balance_0));
		map.put("real_account_balance_1",new Double(real_account_balance_1));
		map.put("real_account_balance_2",new Double(real_account_balance_2));
		map.put("real_account_balance_3",new Double(real_account_balance_3));
		map.put("real_account_balance_4",new Double(real_account_balance_4));
		map.put("real_account_balance_5",new Double(real_account_balance_5));
		map.put("security_asset_0",new Double(security_asset_0));
		map.put("security_asset_1",new Double(security_asset_1));
		map.put("security_asset_2",new Double(security_asset_2));
		map.put("security_asset_3",new Double(security_asset_3));
		map.put("security_asset_4",new Double(security_asset_4));
		map.put("security_asset_5",new Double(security_asset_5));
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
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( calc_result_equity_id_is_modified )
			map.put("calc_result_equity_id",new Long(calc_result_equity_id));
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
		if ( asset_evaluation_div_is_modified )
			map.put("asset_evaluation_div",asset_evaluation_div);
		if ( real_account_balance_0_is_modified )
			map.put("real_account_balance_0",new Double(real_account_balance_0));
		if ( real_account_balance_1_is_modified )
			map.put("real_account_balance_1",new Double(real_account_balance_1));
		if ( real_account_balance_2_is_modified )
			map.put("real_account_balance_2",new Double(real_account_balance_2));
		if ( real_account_balance_3_is_modified )
			map.put("real_account_balance_3",new Double(real_account_balance_3));
		if ( real_account_balance_4_is_modified )
			map.put("real_account_balance_4",new Double(real_account_balance_4));
		if ( real_account_balance_5_is_modified )
			map.put("real_account_balance_5",new Double(real_account_balance_5));
		if ( security_asset_0_is_modified )
			map.put("security_asset_0",new Double(security_asset_0));
		if ( security_asset_1_is_modified )
			map.put("security_asset_1",new Double(security_asset_1));
		if ( security_asset_2_is_modified )
			map.put("security_asset_2",new Double(security_asset_2));
		if ( security_asset_3_is_modified )
			map.put("security_asset_3",new Double(security_asset_3));
		if ( security_asset_4_is_modified )
			map.put("security_asset_4",new Double(security_asset_4));
		if ( security_asset_5_is_modified )
			map.put("security_asset_5",new Double(security_asset_5));
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
		if (map.size() == 0) {
			if ( calc_result_equity_id_is_set )
				map.put("calc_result_equity_id",new Long(calc_result_equity_id));
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			map.put("branch_code",branch_code);
			if ( account_code_is_set )
				map.put("account_code",account_code);
			if ( family_name_is_set )
				map.put("family_name",family_name);
			map.put("sonar_trader_code",sonar_trader_code);
			if ( asset_evaluation_div_is_set )
				map.put("asset_evaluation_div",asset_evaluation_div);
			if ( real_account_balance_0_is_set )
				map.put("real_account_balance_0",new Double(real_account_balance_0));
			if ( real_account_balance_1_is_set )
				map.put("real_account_balance_1",new Double(real_account_balance_1));
			if ( real_account_balance_2_is_set )
				map.put("real_account_balance_2",new Double(real_account_balance_2));
			if ( real_account_balance_3_is_set )
				map.put("real_account_balance_3",new Double(real_account_balance_3));
			if ( real_account_balance_4_is_set )
				map.put("real_account_balance_4",new Double(real_account_balance_4));
			if ( real_account_balance_5_is_set )
				map.put("real_account_balance_5",new Double(real_account_balance_5));
			if ( security_asset_0_is_set )
				map.put("security_asset_0",new Double(security_asset_0));
			if ( security_asset_1_is_set )
				map.put("security_asset_1",new Double(security_asset_1));
			if ( security_asset_2_is_set )
				map.put("security_asset_2",new Double(security_asset_2));
			if ( security_asset_3_is_set )
				map.put("security_asset_3",new Double(security_asset_3));
			if ( security_asset_4_is_set )
				map.put("security_asset_4",new Double(security_asset_4));
			if ( security_asset_5_is_set )
				map.put("security_asset_5",new Double(security_asset_5));
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
   * <em>real_account_balance_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRealAccountBalance0()
  {
    return real_account_balance_0;
  }


  /** 
   * <em>real_account_balance_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealAccountBalance0IsSet() {
    return real_account_balance_0_is_set;
  }


  /** 
   * <em>real_account_balance_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealAccountBalance0IsModified() {
    return real_account_balance_0_is_modified;
  }


  /** 
   * <em>real_account_balance_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRealAccountBalance1()
  {
    return real_account_balance_1;
  }


  /** 
   * <em>real_account_balance_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealAccountBalance1IsSet() {
    return real_account_balance_1_is_set;
  }


  /** 
   * <em>real_account_balance_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealAccountBalance1IsModified() {
    return real_account_balance_1_is_modified;
  }


  /** 
   * <em>real_account_balance_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRealAccountBalance2()
  {
    return real_account_balance_2;
  }


  /** 
   * <em>real_account_balance_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealAccountBalance2IsSet() {
    return real_account_balance_2_is_set;
  }


  /** 
   * <em>real_account_balance_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealAccountBalance2IsModified() {
    return real_account_balance_2_is_modified;
  }


  /** 
   * <em>real_account_balance_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRealAccountBalance3()
  {
    return real_account_balance_3;
  }


  /** 
   * <em>real_account_balance_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealAccountBalance3IsSet() {
    return real_account_balance_3_is_set;
  }


  /** 
   * <em>real_account_balance_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealAccountBalance3IsModified() {
    return real_account_balance_3_is_modified;
  }


  /** 
   * <em>real_account_balance_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRealAccountBalance4()
  {
    return real_account_balance_4;
  }


  /** 
   * <em>real_account_balance_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealAccountBalance4IsSet() {
    return real_account_balance_4_is_set;
  }


  /** 
   * <em>real_account_balance_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealAccountBalance4IsModified() {
    return real_account_balance_4_is_modified;
  }


  /** 
   * <em>real_account_balance_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getRealAccountBalance5()
  {
    return real_account_balance_5;
  }


  /** 
   * <em>real_account_balance_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealAccountBalance5IsSet() {
    return real_account_balance_5_is_set;
  }


  /** 
   * <em>real_account_balance_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRealAccountBalance5IsModified() {
    return real_account_balance_5_is_modified;
  }


  /** 
   * <em>security_asset_0</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSecurityAsset0()
  {
    return security_asset_0;
  }


  /** 
   * <em>security_asset_0</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecurityAsset0IsSet() {
    return security_asset_0_is_set;
  }


  /** 
   * <em>security_asset_0</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecurityAsset0IsModified() {
    return security_asset_0_is_modified;
  }


  /** 
   * <em>security_asset_1</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSecurityAsset1()
  {
    return security_asset_1;
  }


  /** 
   * <em>security_asset_1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecurityAsset1IsSet() {
    return security_asset_1_is_set;
  }


  /** 
   * <em>security_asset_1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecurityAsset1IsModified() {
    return security_asset_1_is_modified;
  }


  /** 
   * <em>security_asset_2</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSecurityAsset2()
  {
    return security_asset_2;
  }


  /** 
   * <em>security_asset_2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecurityAsset2IsSet() {
    return security_asset_2_is_set;
  }


  /** 
   * <em>security_asset_2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecurityAsset2IsModified() {
    return security_asset_2_is_modified;
  }


  /** 
   * <em>security_asset_3</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSecurityAsset3()
  {
    return security_asset_3;
  }


  /** 
   * <em>security_asset_3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecurityAsset3IsSet() {
    return security_asset_3_is_set;
  }


  /** 
   * <em>security_asset_3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecurityAsset3IsModified() {
    return security_asset_3_is_modified;
  }


  /** 
   * <em>security_asset_4</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSecurityAsset4()
  {
    return security_asset_4;
  }


  /** 
   * <em>security_asset_4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecurityAsset4IsSet() {
    return security_asset_4_is_set;
  }


  /** 
   * <em>security_asset_4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecurityAsset4IsModified() {
    return security_asset_4_is_modified;
  }


  /** 
   * <em>security_asset_5</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getSecurityAsset5()
  {
    return security_asset_5;
  }


  /** 
   * <em>security_asset_5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecurityAsset5IsSet() {
    return security_asset_5_is_set;
  }


  /** 
   * <em>security_asset_5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSecurityAsset5IsModified() {
    return security_asset_5_is_modified;
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
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    throw new UnsupportedOperationException("Table requisition_account_equity has no primary key.");
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
   * <em>real_account_balance_0</em>カラムの値を設定します。 
   *
   * @@param p_realAccountBalance0 <em>real_account_balance_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRealAccountBalance0( double p_realAccountBalance0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    real_account_balance_0 = p_realAccountBalance0;
    real_account_balance_0_is_set = true;
    real_account_balance_0_is_modified = true;
  }


  /** 
   * <em>real_account_balance_1</em>カラムの値を設定します。 
   *
   * @@param p_realAccountBalance1 <em>real_account_balance_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRealAccountBalance1( double p_realAccountBalance1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    real_account_balance_1 = p_realAccountBalance1;
    real_account_balance_1_is_set = true;
    real_account_balance_1_is_modified = true;
  }


  /** 
   * <em>real_account_balance_2</em>カラムの値を設定します。 
   *
   * @@param p_realAccountBalance2 <em>real_account_balance_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRealAccountBalance2( double p_realAccountBalance2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    real_account_balance_2 = p_realAccountBalance2;
    real_account_balance_2_is_set = true;
    real_account_balance_2_is_modified = true;
  }


  /** 
   * <em>real_account_balance_3</em>カラムの値を設定します。 
   *
   * @@param p_realAccountBalance3 <em>real_account_balance_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRealAccountBalance3( double p_realAccountBalance3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    real_account_balance_3 = p_realAccountBalance3;
    real_account_balance_3_is_set = true;
    real_account_balance_3_is_modified = true;
  }


  /** 
   * <em>real_account_balance_4</em>カラムの値を設定します。 
   *
   * @@param p_realAccountBalance4 <em>real_account_balance_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRealAccountBalance4( double p_realAccountBalance4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    real_account_balance_4 = p_realAccountBalance4;
    real_account_balance_4_is_set = true;
    real_account_balance_4_is_modified = true;
  }


  /** 
   * <em>real_account_balance_5</em>カラムの値を設定します。 
   *
   * @@param p_realAccountBalance5 <em>real_account_balance_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setRealAccountBalance5( double p_realAccountBalance5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    real_account_balance_5 = p_realAccountBalance5;
    real_account_balance_5_is_set = true;
    real_account_balance_5_is_modified = true;
  }


  /** 
   * <em>security_asset_0</em>カラムの値を設定します。 
   *
   * @@param p_securityAsset0 <em>security_asset_0</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSecurityAsset0( double p_securityAsset0 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    security_asset_0 = p_securityAsset0;
    security_asset_0_is_set = true;
    security_asset_0_is_modified = true;
  }


  /** 
   * <em>security_asset_1</em>カラムの値を設定します。 
   *
   * @@param p_securityAsset1 <em>security_asset_1</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSecurityAsset1( double p_securityAsset1 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    security_asset_1 = p_securityAsset1;
    security_asset_1_is_set = true;
    security_asset_1_is_modified = true;
  }


  /** 
   * <em>security_asset_2</em>カラムの値を設定します。 
   *
   * @@param p_securityAsset2 <em>security_asset_2</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSecurityAsset2( double p_securityAsset2 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    security_asset_2 = p_securityAsset2;
    security_asset_2_is_set = true;
    security_asset_2_is_modified = true;
  }


  /** 
   * <em>security_asset_3</em>カラムの値を設定します。 
   *
   * @@param p_securityAsset3 <em>security_asset_3</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSecurityAsset3( double p_securityAsset3 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    security_asset_3 = p_securityAsset3;
    security_asset_3_is_set = true;
    security_asset_3_is_modified = true;
  }


  /** 
   * <em>security_asset_4</em>カラムの値を設定します。 
   *
   * @@param p_securityAsset4 <em>security_asset_4</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSecurityAsset4( double p_securityAsset4 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    security_asset_4 = p_securityAsset4;
    security_asset_4_is_set = true;
    security_asset_4_is_modified = true;
  }


  /** 
   * <em>security_asset_5</em>カラムの値を設定します。 
   *
   * @@param p_securityAsset5 <em>security_asset_5</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setSecurityAsset5( double p_securityAsset5 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    security_asset_5 = p_securityAsset5;
    security_asset_5_is_set = true;
    security_asset_5_is_modified = true;
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
                else if ( name.equals("asset_evaluation_div") ) {
                    return this.asset_evaluation_div;
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
            case 'r':
                if ( name.equals("real_account_balance_0") ) {
                    return new Double( real_account_balance_0 );
                }
                else if ( name.equals("real_account_balance_1") ) {
                    return new Double( real_account_balance_1 );
                }
                else if ( name.equals("real_account_balance_2") ) {
                    return new Double( real_account_balance_2 );
                }
                else if ( name.equals("real_account_balance_3") ) {
                    return new Double( real_account_balance_3 );
                }
                else if ( name.equals("real_account_balance_4") ) {
                    return new Double( real_account_balance_4 );
                }
                else if ( name.equals("real_account_balance_5") ) {
                    return new Double( real_account_balance_5 );
                }
                break;
            case 's':
                if ( name.equals("sonar_trader_code") ) {
                    return this.sonar_trader_code;
                }
                else if ( name.equals("security_asset_0") ) {
                    return new Double( security_asset_0 );
                }
                else if ( name.equals("security_asset_1") ) {
                    return new Double( security_asset_1 );
                }
                else if ( name.equals("security_asset_2") ) {
                    return new Double( security_asset_2 );
                }
                else if ( name.equals("security_asset_3") ) {
                    return new Double( security_asset_3 );
                }
                else if ( name.equals("security_asset_4") ) {
                    return new Double( security_asset_4 );
                }
                else if ( name.equals("security_asset_5") ) {
                    return new Double( security_asset_5 );
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
                else if ( name.equals("asset_evaluation_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'asset_evaluation_div' must be String: '"+value+"' is not." );
                    this.asset_evaluation_div = (String) value;
                    if (this.asset_evaluation_div_is_set)
                        this.asset_evaluation_div_is_modified = true;
                    this.asset_evaluation_div_is_set = true;
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
            case 'r':
                if ( name.equals("real_account_balance_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'real_account_balance_0' must be Double: '"+value+"' is not." );
                    this.real_account_balance_0 = ((Double) value).doubleValue();
                    if (this.real_account_balance_0_is_set)
                        this.real_account_balance_0_is_modified = true;
                    this.real_account_balance_0_is_set = true;
                    return;
                }
                else if ( name.equals("real_account_balance_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'real_account_balance_1' must be Double: '"+value+"' is not." );
                    this.real_account_balance_1 = ((Double) value).doubleValue();
                    if (this.real_account_balance_1_is_set)
                        this.real_account_balance_1_is_modified = true;
                    this.real_account_balance_1_is_set = true;
                    return;
                }
                else if ( name.equals("real_account_balance_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'real_account_balance_2' must be Double: '"+value+"' is not." );
                    this.real_account_balance_2 = ((Double) value).doubleValue();
                    if (this.real_account_balance_2_is_set)
                        this.real_account_balance_2_is_modified = true;
                    this.real_account_balance_2_is_set = true;
                    return;
                }
                else if ( name.equals("real_account_balance_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'real_account_balance_3' must be Double: '"+value+"' is not." );
                    this.real_account_balance_3 = ((Double) value).doubleValue();
                    if (this.real_account_balance_3_is_set)
                        this.real_account_balance_3_is_modified = true;
                    this.real_account_balance_3_is_set = true;
                    return;
                }
                else if ( name.equals("real_account_balance_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'real_account_balance_4' must be Double: '"+value+"' is not." );
                    this.real_account_balance_4 = ((Double) value).doubleValue();
                    if (this.real_account_balance_4_is_set)
                        this.real_account_balance_4_is_modified = true;
                    this.real_account_balance_4_is_set = true;
                    return;
                }
                else if ( name.equals("real_account_balance_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'real_account_balance_5' must be Double: '"+value+"' is not." );
                    this.real_account_balance_5 = ((Double) value).doubleValue();
                    if (this.real_account_balance_5_is_set)
                        this.real_account_balance_5_is_modified = true;
                    this.real_account_balance_5_is_set = true;
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
                else if ( name.equals("security_asset_0") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'security_asset_0' must be Double: '"+value+"' is not." );
                    this.security_asset_0 = ((Double) value).doubleValue();
                    if (this.security_asset_0_is_set)
                        this.security_asset_0_is_modified = true;
                    this.security_asset_0_is_set = true;
                    return;
                }
                else if ( name.equals("security_asset_1") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'security_asset_1' must be Double: '"+value+"' is not." );
                    this.security_asset_1 = ((Double) value).doubleValue();
                    if (this.security_asset_1_is_set)
                        this.security_asset_1_is_modified = true;
                    this.security_asset_1_is_set = true;
                    return;
                }
                else if ( name.equals("security_asset_2") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'security_asset_2' must be Double: '"+value+"' is not." );
                    this.security_asset_2 = ((Double) value).doubleValue();
                    if (this.security_asset_2_is_set)
                        this.security_asset_2_is_modified = true;
                    this.security_asset_2_is_set = true;
                    return;
                }
                else if ( name.equals("security_asset_3") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'security_asset_3' must be Double: '"+value+"' is not." );
                    this.security_asset_3 = ((Double) value).doubleValue();
                    if (this.security_asset_3_is_set)
                        this.security_asset_3_is_modified = true;
                    this.security_asset_3_is_set = true;
                    return;
                }
                else if ( name.equals("security_asset_4") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'security_asset_4' must be Double: '"+value+"' is not." );
                    this.security_asset_4 = ((Double) value).doubleValue();
                    if (this.security_asset_4_is_set)
                        this.security_asset_4_is_modified = true;
                    this.security_asset_4_is_set = true;
                    return;
                }
                else if ( name.equals("security_asset_5") ) {
                    if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'security_asset_5' must be Double: '"+value+"' is not." );
                    this.security_asset_5 = ((Double) value).doubleValue();
                    if (this.security_asset_5_is_set)
                        this.security_asset_5_is_modified = true;
                    this.security_asset_5_is_set = true;
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
