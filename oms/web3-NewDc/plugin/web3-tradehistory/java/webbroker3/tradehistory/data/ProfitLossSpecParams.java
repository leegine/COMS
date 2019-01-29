head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.47.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	ProfitLossSpecParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradehistory.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * profit_loss_specテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link ProfitLossSpecRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link ProfitLossSpecParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link ProfitLossSpecParams}が{@@link ProfitLossSpecRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see ProfitLossSpecPK 
 * @@see ProfitLossSpecRow 
 */
public class ProfitLossSpecParams extends Params implements ProfitLossSpecRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "profit_loss_spec";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = ProfitLossSpecRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return ProfitLossSpecRow.TYPE;
  }


  /** 
   * <em>profit_loss_spec_id</em>カラムの値 
   */
  public  long  profit_loss_spec_id;

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
   * <em>trader_code_sonar</em>カラムの値 
   */
  public  String  trader_code_sonar;

  /** 
   * <em>work_date</em>カラムの値 
   */
  public  java.sql.Timestamp  work_date;

  /** 
   * <em>tax_type</em>カラムの値 
   */
  public  String  tax_type;

  /** 
   * <em>rec_div</em>カラムの値 
   */
  public  String  rec_div;

  /** 
   * <em>sort_no</em>カラムの値 
   */
  public  String  sort_no;

  /** 
   * <em>calc_date</em>カラムの値 
   */
  public  java.sql.Timestamp  calc_date;

  /** 
   * <em>delivery_date</em>カラムの値 
   */
  public  java.sql.Timestamp  delivery_date;

  /** 
   * <em>commodity_div</em>カラムの値 
   */
  public  String  commodity_div;

  /** 
   * <em>application_code</em>カラムの値 
   */
  public  String  application_code;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>standard_name</em>カラムの値 
   */
  public  String  standard_name;

  /** 
   * <em>term_div</em>カラムの値 
   */
  public  String  term_div;

  /** 
   * <em>quantity</em>カラムの値 
   */
  public  Double  quantity;

  /** 
   * <em>pass_date</em>カラムの値 
   */
  public  java.sql.Timestamp  pass_date;

  /** 
   * <em>pass_amount</em>カラムの値 
   */
  public  Double  pass_amount;

  /** 
   * <em>get_date</em>カラムの値 
   */
  public  java.sql.Timestamp  get_date;

  /** 
   * <em>get_amount</em>カラムの値 
   */
  public  Double  get_amount;

  /** 
   * <em>proloss_amount</em>カラムの値 
   */
  public  Double  proloss_amount;

  /** 
   * <em>total_proloss_amount</em>カラムの値 
   */
  public  Double  total_proloss_amount;

  /** 
   * <em>taxable_amount</em>カラムの値 
   */
  public  Double  taxable_amount;

  /** 
   * <em>collect_tax_amount</em>カラムの値 
   */
  public  Double  collect_tax_amount;

  /** 
   * <em>collect_tax_n_amount</em>カラムの値 
   */
  public  Double  collect_tax_n_amount;

  /** 
   * <em>collect_tax_l_amount</em>カラムの値 
   */
  public  Double  collect_tax_l_amount;

  /** 
   * <em>return_div</em>カラムの値 
   */
  public  String  return_div;

  /** 
   * <em>colltax_amount_curr</em>カラムの値 
   */
  public  Double  colltax_amount_curr;

  /** 
   * <em>colltax_n_amount_curr</em>カラムの値 
   */
  public  Double  colltax_n_amount_curr;

  /** 
   * <em>colltax_l_amount_curr</em>カラムの値 
   */
  public  Double  colltax_l_amount_curr;

  /** 
   * <em>colltax_amount_nxt</em>カラムの値 
   */
  public  Double  colltax_amount_nxt;

  /** 
   * <em>colltax_n_amount_nxt</em>カラムの値 
   */
  public  Double  colltax_n_amount_nxt;

  /** 
   * <em>colltax_l_amount_nxt</em>カラムの値 
   */
  public  Double  colltax_l_amount_nxt;

  /** 
   * <em>remark</em>カラムの値 
   */
  public  String  remark;

  /** 
   * <em>proloss_amount_cps</em>カラムの値 
   */
  public  Double  proloss_amount_cps;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean profit_loss_spec_id_is_set = false;
  private boolean profit_loss_spec_id_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean branch_code_is_set = false;
  private boolean branch_code_is_modified = false;
  private boolean account_code_is_set = false;
  private boolean account_code_is_modified = false;
  private boolean trader_code_sonar_is_set = false;
  private boolean trader_code_sonar_is_modified = false;
  private boolean work_date_is_set = false;
  private boolean work_date_is_modified = false;
  private boolean tax_type_is_set = false;
  private boolean tax_type_is_modified = false;
  private boolean rec_div_is_set = false;
  private boolean rec_div_is_modified = false;
  private boolean sort_no_is_set = false;
  private boolean sort_no_is_modified = false;
  private boolean calc_date_is_set = false;
  private boolean calc_date_is_modified = false;
  private boolean delivery_date_is_set = false;
  private boolean delivery_date_is_modified = false;
  private boolean commodity_div_is_set = false;
  private boolean commodity_div_is_modified = false;
  private boolean application_code_is_set = false;
  private boolean application_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean standard_name_is_set = false;
  private boolean standard_name_is_modified = false;
  private boolean term_div_is_set = false;
  private boolean term_div_is_modified = false;
  private boolean quantity_is_set = false;
  private boolean quantity_is_modified = false;
  private boolean pass_date_is_set = false;
  private boolean pass_date_is_modified = false;
  private boolean pass_amount_is_set = false;
  private boolean pass_amount_is_modified = false;
  private boolean get_date_is_set = false;
  private boolean get_date_is_modified = false;
  private boolean get_amount_is_set = false;
  private boolean get_amount_is_modified = false;
  private boolean proloss_amount_is_set = false;
  private boolean proloss_amount_is_modified = false;
  private boolean total_proloss_amount_is_set = false;
  private boolean total_proloss_amount_is_modified = false;
  private boolean taxable_amount_is_set = false;
  private boolean taxable_amount_is_modified = false;
  private boolean collect_tax_amount_is_set = false;
  private boolean collect_tax_amount_is_modified = false;
  private boolean collect_tax_n_amount_is_set = false;
  private boolean collect_tax_n_amount_is_modified = false;
  private boolean collect_tax_l_amount_is_set = false;
  private boolean collect_tax_l_amount_is_modified = false;
  private boolean return_div_is_set = false;
  private boolean return_div_is_modified = false;
  private boolean colltax_amount_curr_is_set = false;
  private boolean colltax_amount_curr_is_modified = false;
  private boolean colltax_n_amount_curr_is_set = false;
  private boolean colltax_n_amount_curr_is_modified = false;
  private boolean colltax_l_amount_curr_is_set = false;
  private boolean colltax_l_amount_curr_is_modified = false;
  private boolean colltax_amount_nxt_is_set = false;
  private boolean colltax_amount_nxt_is_modified = false;
  private boolean colltax_n_amount_nxt_is_set = false;
  private boolean colltax_n_amount_nxt_is_modified = false;
  private boolean colltax_l_amount_nxt_is_set = false;
  private boolean colltax_l_amount_nxt_is_modified = false;
  private boolean remark_is_set = false;
  private boolean remark_is_modified = false;
  private boolean proloss_amount_cps_is_set = false;
  private boolean proloss_amount_cps_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "profit_loss_spec_id=" + profit_loss_spec_id
      + "," + "institution_code=" +institution_code
      + "," + "branch_code=" +branch_code
      + "," + "account_code=" +account_code
      + "," + "trader_code_sonar=" +trader_code_sonar
      + "," + "work_date=" +work_date
      + "," + "tax_type=" +tax_type
      + "," + "rec_div=" +rec_div
      + "," + "sort_no=" +sort_no
      + "," + "calc_date=" +calc_date
      + "," + "delivery_date=" +delivery_date
      + "," + "commodity_div=" +commodity_div
      + "," + "application_code=" +application_code
      + "," + "product_code=" +product_code
      + "," + "standard_name=" +standard_name
      + "," + "term_div=" +term_div
      + "," + "quantity=" +quantity
      + "," + "pass_date=" +pass_date
      + "," + "pass_amount=" +pass_amount
      + "," + "get_date=" +get_date
      + "," + "get_amount=" +get_amount
      + "," + "proloss_amount=" +proloss_amount
      + "," + "total_proloss_amount=" +total_proloss_amount
      + "," + "taxable_amount=" +taxable_amount
      + "," + "collect_tax_amount=" +collect_tax_amount
      + "," + "collect_tax_n_amount=" +collect_tax_n_amount
      + "," + "collect_tax_l_amount=" +collect_tax_l_amount
      + "," + "return_div=" +return_div
      + "," + "colltax_amount_curr=" +colltax_amount_curr
      + "," + "colltax_n_amount_curr=" +colltax_n_amount_curr
      + "," + "colltax_l_amount_curr=" +colltax_l_amount_curr
      + "," + "colltax_amount_nxt=" +colltax_amount_nxt
      + "," + "colltax_n_amount_nxt=" +colltax_n_amount_nxt
      + "," + "colltax_l_amount_nxt=" +colltax_l_amount_nxt
      + "," + "remark=" +remark
      + "," + "proloss_amount_cps=" +proloss_amount_cps
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のProfitLossSpecParamsオブジェクトを作成します。 
   */
  public ProfitLossSpecParams() {
    profit_loss_spec_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のProfitLossSpecRowオブジェクトの値を利用してProfitLossSpecParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するProfitLossSpecRowオブジェクト 
   */
  public ProfitLossSpecParams( ProfitLossSpecRow p_row )
  {
    profit_loss_spec_id = p_row.getProfitLossSpecId();
    profit_loss_spec_id_is_set = p_row.getProfitLossSpecIdIsSet();
    profit_loss_spec_id_is_modified = p_row.getProfitLossSpecIdIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    branch_code = p_row.getBranchCode();
    branch_code_is_set = p_row.getBranchCodeIsSet();
    branch_code_is_modified = p_row.getBranchCodeIsModified();
    account_code = p_row.getAccountCode();
    account_code_is_set = p_row.getAccountCodeIsSet();
    account_code_is_modified = p_row.getAccountCodeIsModified();
    trader_code_sonar = p_row.getTraderCodeSonar();
    trader_code_sonar_is_set = p_row.getTraderCodeSonarIsSet();
    trader_code_sonar_is_modified = p_row.getTraderCodeSonarIsModified();
    work_date = p_row.getWorkDate();
    work_date_is_set = p_row.getWorkDateIsSet();
    work_date_is_modified = p_row.getWorkDateIsModified();
    tax_type = p_row.getTaxType();
    tax_type_is_set = p_row.getTaxTypeIsSet();
    tax_type_is_modified = p_row.getTaxTypeIsModified();
    rec_div = p_row.getRecDiv();
    rec_div_is_set = p_row.getRecDivIsSet();
    rec_div_is_modified = p_row.getRecDivIsModified();
    sort_no = p_row.getSortNo();
    sort_no_is_set = p_row.getSortNoIsSet();
    sort_no_is_modified = p_row.getSortNoIsModified();
    calc_date = p_row.getCalcDate();
    calc_date_is_set = p_row.getCalcDateIsSet();
    calc_date_is_modified = p_row.getCalcDateIsModified();
    delivery_date = p_row.getDeliveryDate();
    delivery_date_is_set = p_row.getDeliveryDateIsSet();
    delivery_date_is_modified = p_row.getDeliveryDateIsModified();
    commodity_div = p_row.getCommodityDiv();
    commodity_div_is_set = p_row.getCommodityDivIsSet();
    commodity_div_is_modified = p_row.getCommodityDivIsModified();
    application_code = p_row.getApplicationCode();
    application_code_is_set = p_row.getApplicationCodeIsSet();
    application_code_is_modified = p_row.getApplicationCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    standard_name = p_row.getStandardName();
    standard_name_is_set = p_row.getStandardNameIsSet();
    standard_name_is_modified = p_row.getStandardNameIsModified();
    term_div = p_row.getTermDiv();
    term_div_is_set = p_row.getTermDivIsSet();
    term_div_is_modified = p_row.getTermDivIsModified();
    if ( !p_row.getQuantityIsNull() )
      quantity = new Double( p_row.getQuantity() );
    quantity_is_set = p_row.getQuantityIsSet();
    quantity_is_modified = p_row.getQuantityIsModified();
    pass_date = p_row.getPassDate();
    pass_date_is_set = p_row.getPassDateIsSet();
    pass_date_is_modified = p_row.getPassDateIsModified();
    if ( !p_row.getPassAmountIsNull() )
      pass_amount = new Double( p_row.getPassAmount() );
    pass_amount_is_set = p_row.getPassAmountIsSet();
    pass_amount_is_modified = p_row.getPassAmountIsModified();
    get_date = p_row.getGetDate();
    get_date_is_set = p_row.getGetDateIsSet();
    get_date_is_modified = p_row.getGetDateIsModified();
    if ( !p_row.getGetAmountIsNull() )
      get_amount = new Double( p_row.getGetAmount() );
    get_amount_is_set = p_row.getGetAmountIsSet();
    get_amount_is_modified = p_row.getGetAmountIsModified();
    if ( !p_row.getProlossAmountIsNull() )
      proloss_amount = new Double( p_row.getProlossAmount() );
    proloss_amount_is_set = p_row.getProlossAmountIsSet();
    proloss_amount_is_modified = p_row.getProlossAmountIsModified();
    if ( !p_row.getTotalProlossAmountIsNull() )
      total_proloss_amount = new Double( p_row.getTotalProlossAmount() );
    total_proloss_amount_is_set = p_row.getTotalProlossAmountIsSet();
    total_proloss_amount_is_modified = p_row.getTotalProlossAmountIsModified();
    if ( !p_row.getTaxableAmountIsNull() )
      taxable_amount = new Double( p_row.getTaxableAmount() );
    taxable_amount_is_set = p_row.getTaxableAmountIsSet();
    taxable_amount_is_modified = p_row.getTaxableAmountIsModified();
    if ( !p_row.getCollectTaxAmountIsNull() )
      collect_tax_amount = new Double( p_row.getCollectTaxAmount() );
    collect_tax_amount_is_set = p_row.getCollectTaxAmountIsSet();
    collect_tax_amount_is_modified = p_row.getCollectTaxAmountIsModified();
    if ( !p_row.getCollectTaxNAmountIsNull() )
      collect_tax_n_amount = new Double( p_row.getCollectTaxNAmount() );
    collect_tax_n_amount_is_set = p_row.getCollectTaxNAmountIsSet();
    collect_tax_n_amount_is_modified = p_row.getCollectTaxNAmountIsModified();
    if ( !p_row.getCollectTaxLAmountIsNull() )
      collect_tax_l_amount = new Double( p_row.getCollectTaxLAmount() );
    collect_tax_l_amount_is_set = p_row.getCollectTaxLAmountIsSet();
    collect_tax_l_amount_is_modified = p_row.getCollectTaxLAmountIsModified();
    return_div = p_row.getReturnDiv();
    return_div_is_set = p_row.getReturnDivIsSet();
    return_div_is_modified = p_row.getReturnDivIsModified();
    if ( !p_row.getColltaxAmountCurrIsNull() )
      colltax_amount_curr = new Double( p_row.getColltaxAmountCurr() );
    colltax_amount_curr_is_set = p_row.getColltaxAmountCurrIsSet();
    colltax_amount_curr_is_modified = p_row.getColltaxAmountCurrIsModified();
    if ( !p_row.getColltaxNAmountCurrIsNull() )
      colltax_n_amount_curr = new Double( p_row.getColltaxNAmountCurr() );
    colltax_n_amount_curr_is_set = p_row.getColltaxNAmountCurrIsSet();
    colltax_n_amount_curr_is_modified = p_row.getColltaxNAmountCurrIsModified();
    if ( !p_row.getColltaxLAmountCurrIsNull() )
      colltax_l_amount_curr = new Double( p_row.getColltaxLAmountCurr() );
    colltax_l_amount_curr_is_set = p_row.getColltaxLAmountCurrIsSet();
    colltax_l_amount_curr_is_modified = p_row.getColltaxLAmountCurrIsModified();
    if ( !p_row.getColltaxAmountNxtIsNull() )
      colltax_amount_nxt = new Double( p_row.getColltaxAmountNxt() );
    colltax_amount_nxt_is_set = p_row.getColltaxAmountNxtIsSet();
    colltax_amount_nxt_is_modified = p_row.getColltaxAmountNxtIsModified();
    if ( !p_row.getColltaxNAmountNxtIsNull() )
      colltax_n_amount_nxt = new Double( p_row.getColltaxNAmountNxt() );
    colltax_n_amount_nxt_is_set = p_row.getColltaxNAmountNxtIsSet();
    colltax_n_amount_nxt_is_modified = p_row.getColltaxNAmountNxtIsModified();
    if ( !p_row.getColltaxLAmountNxtIsNull() )
      colltax_l_amount_nxt = new Double( p_row.getColltaxLAmountNxt() );
    colltax_l_amount_nxt_is_set = p_row.getColltaxLAmountNxtIsSet();
    colltax_l_amount_nxt_is_modified = p_row.getColltaxLAmountNxtIsModified();
    remark = p_row.getRemark();
    remark_is_set = p_row.getRemarkIsSet();
    remark_is_modified = p_row.getRemarkIsModified();
    if ( !p_row.getProlossAmountCpsIsNull() )
      proloss_amount_cps = new Double( p_row.getProlossAmountCps() );
    proloss_amount_cps_is_set = p_row.getProlossAmountCpsIsSet();
    proloss_amount_cps_is_modified = p_row.getProlossAmountCpsIsModified();
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
      institution_code_is_set = true;
      institution_code_is_modified = true;
      branch_code_is_set = true;
      branch_code_is_modified = true;
      account_code_is_set = true;
      account_code_is_modified = true;
      trader_code_sonar_is_set = true;
      trader_code_sonar_is_modified = true;
      work_date_is_set = true;
      work_date_is_modified = true;
      tax_type_is_set = true;
      tax_type_is_modified = true;
      rec_div_is_set = true;
      rec_div_is_modified = true;
      sort_no_is_set = true;
      sort_no_is_modified = true;
      calc_date_is_set = true;
      calc_date_is_modified = true;
      delivery_date_is_set = true;
      delivery_date_is_modified = true;
      commodity_div_is_set = true;
      commodity_div_is_modified = true;
      application_code_is_set = true;
      application_code_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      standard_name_is_set = true;
      standard_name_is_modified = true;
      term_div_is_set = true;
      term_div_is_modified = true;
      quantity_is_set = true;
      quantity_is_modified = true;
      pass_date_is_set = true;
      pass_date_is_modified = true;
      pass_amount_is_set = true;
      pass_amount_is_modified = true;
      get_date_is_set = true;
      get_date_is_modified = true;
      get_amount_is_set = true;
      get_amount_is_modified = true;
      proloss_amount_is_set = true;
      proloss_amount_is_modified = true;
      total_proloss_amount_is_set = true;
      total_proloss_amount_is_modified = true;
      taxable_amount_is_set = true;
      taxable_amount_is_modified = true;
      collect_tax_amount_is_set = true;
      collect_tax_amount_is_modified = true;
      collect_tax_n_amount_is_set = true;
      collect_tax_n_amount_is_modified = true;
      collect_tax_l_amount_is_set = true;
      collect_tax_l_amount_is_modified = true;
      return_div_is_set = true;
      return_div_is_modified = true;
      colltax_amount_curr_is_set = true;
      colltax_amount_curr_is_modified = true;
      colltax_n_amount_curr_is_set = true;
      colltax_n_amount_curr_is_modified = true;
      colltax_l_amount_curr_is_set = true;
      colltax_l_amount_curr_is_modified = true;
      colltax_amount_nxt_is_set = true;
      colltax_amount_nxt_is_modified = true;
      colltax_n_amount_nxt_is_set = true;
      colltax_n_amount_nxt_is_modified = true;
      colltax_l_amount_nxt_is_set = true;
      colltax_l_amount_nxt_is_modified = true;
      remark_is_set = true;
      remark_is_modified = true;
      proloss_amount_cps_is_set = true;
      proloss_amount_cps_is_modified = true;
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
    if ( !( other instanceof ProfitLossSpecRow ) )
       return false;
    return fieldsEqual( (ProfitLossSpecRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のProfitLossSpecRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( ProfitLossSpecRow row )
  {
    if ( profit_loss_spec_id != row.getProfitLossSpecId() )
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
    if ( trader_code_sonar == null ) {
      if ( row.getTraderCodeSonar() != null )
        return false;
    } else if ( !trader_code_sonar.equals( row.getTraderCodeSonar() ) ) {
        return false;
    }
    if ( work_date == null ) {
      if ( row.getWorkDate() != null )
        return false;
    } else if ( !work_date.equals( row.getWorkDate() ) ) {
        return false;
    }
    if ( tax_type == null ) {
      if ( row.getTaxType() != null )
        return false;
    } else if ( !tax_type.equals( row.getTaxType() ) ) {
        return false;
    }
    if ( rec_div == null ) {
      if ( row.getRecDiv() != null )
        return false;
    } else if ( !rec_div.equals( row.getRecDiv() ) ) {
        return false;
    }
    if ( sort_no == null ) {
      if ( row.getSortNo() != null )
        return false;
    } else if ( !sort_no.equals( row.getSortNo() ) ) {
        return false;
    }
    if ( calc_date == null ) {
      if ( row.getCalcDate() != null )
        return false;
    } else if ( !calc_date.equals( row.getCalcDate() ) ) {
        return false;
    }
    if ( delivery_date == null ) {
      if ( row.getDeliveryDate() != null )
        return false;
    } else if ( !delivery_date.equals( row.getDeliveryDate() ) ) {
        return false;
    }
    if ( commodity_div == null ) {
      if ( row.getCommodityDiv() != null )
        return false;
    } else if ( !commodity_div.equals( row.getCommodityDiv() ) ) {
        return false;
    }
    if ( application_code == null ) {
      if ( row.getApplicationCode() != null )
        return false;
    } else if ( !application_code.equals( row.getApplicationCode() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( standard_name == null ) {
      if ( row.getStandardName() != null )
        return false;
    } else if ( !standard_name.equals( row.getStandardName() ) ) {
        return false;
    }
    if ( term_div == null ) {
      if ( row.getTermDiv() != null )
        return false;
    } else if ( !term_div.equals( row.getTermDiv() ) ) {
        return false;
    }
    if ( quantity == null ) {
      if ( !row.getQuantityIsNull() )
        return false;
    } else if ( row.getQuantityIsNull() || ( quantity.doubleValue() != row.getQuantity() ) ) {
        return false;
    }
    if ( pass_date == null ) {
      if ( row.getPassDate() != null )
        return false;
    } else if ( !pass_date.equals( row.getPassDate() ) ) {
        return false;
    }
    if ( pass_amount == null ) {
      if ( !row.getPassAmountIsNull() )
        return false;
    } else if ( row.getPassAmountIsNull() || ( pass_amount.doubleValue() != row.getPassAmount() ) ) {
        return false;
    }
    if ( get_date == null ) {
      if ( row.getGetDate() != null )
        return false;
    } else if ( !get_date.equals( row.getGetDate() ) ) {
        return false;
    }
    if ( get_amount == null ) {
      if ( !row.getGetAmountIsNull() )
        return false;
    } else if ( row.getGetAmountIsNull() || ( get_amount.doubleValue() != row.getGetAmount() ) ) {
        return false;
    }
    if ( proloss_amount == null ) {
      if ( !row.getProlossAmountIsNull() )
        return false;
    } else if ( row.getProlossAmountIsNull() || ( proloss_amount.doubleValue() != row.getProlossAmount() ) ) {
        return false;
    }
    if ( total_proloss_amount == null ) {
      if ( !row.getTotalProlossAmountIsNull() )
        return false;
    } else if ( row.getTotalProlossAmountIsNull() || ( total_proloss_amount.doubleValue() != row.getTotalProlossAmount() ) ) {
        return false;
    }
    if ( taxable_amount == null ) {
      if ( !row.getTaxableAmountIsNull() )
        return false;
    } else if ( row.getTaxableAmountIsNull() || ( taxable_amount.doubleValue() != row.getTaxableAmount() ) ) {
        return false;
    }
    if ( collect_tax_amount == null ) {
      if ( !row.getCollectTaxAmountIsNull() )
        return false;
    } else if ( row.getCollectTaxAmountIsNull() || ( collect_tax_amount.doubleValue() != row.getCollectTaxAmount() ) ) {
        return false;
    }
    if ( collect_tax_n_amount == null ) {
      if ( !row.getCollectTaxNAmountIsNull() )
        return false;
    } else if ( row.getCollectTaxNAmountIsNull() || ( collect_tax_n_amount.doubleValue() != row.getCollectTaxNAmount() ) ) {
        return false;
    }
    if ( collect_tax_l_amount == null ) {
      if ( !row.getCollectTaxLAmountIsNull() )
        return false;
    } else if ( row.getCollectTaxLAmountIsNull() || ( collect_tax_l_amount.doubleValue() != row.getCollectTaxLAmount() ) ) {
        return false;
    }
    if ( return_div == null ) {
      if ( row.getReturnDiv() != null )
        return false;
    } else if ( !return_div.equals( row.getReturnDiv() ) ) {
        return false;
    }
    if ( colltax_amount_curr == null ) {
      if ( !row.getColltaxAmountCurrIsNull() )
        return false;
    } else if ( row.getColltaxAmountCurrIsNull() || ( colltax_amount_curr.doubleValue() != row.getColltaxAmountCurr() ) ) {
        return false;
    }
    if ( colltax_n_amount_curr == null ) {
      if ( !row.getColltaxNAmountCurrIsNull() )
        return false;
    } else if ( row.getColltaxNAmountCurrIsNull() || ( colltax_n_amount_curr.doubleValue() != row.getColltaxNAmountCurr() ) ) {
        return false;
    }
    if ( colltax_l_amount_curr == null ) {
      if ( !row.getColltaxLAmountCurrIsNull() )
        return false;
    } else if ( row.getColltaxLAmountCurrIsNull() || ( colltax_l_amount_curr.doubleValue() != row.getColltaxLAmountCurr() ) ) {
        return false;
    }
    if ( colltax_amount_nxt == null ) {
      if ( !row.getColltaxAmountNxtIsNull() )
        return false;
    } else if ( row.getColltaxAmountNxtIsNull() || ( colltax_amount_nxt.doubleValue() != row.getColltaxAmountNxt() ) ) {
        return false;
    }
    if ( colltax_n_amount_nxt == null ) {
      if ( !row.getColltaxNAmountNxtIsNull() )
        return false;
    } else if ( row.getColltaxNAmountNxtIsNull() || ( colltax_n_amount_nxt.doubleValue() != row.getColltaxNAmountNxt() ) ) {
        return false;
    }
    if ( colltax_l_amount_nxt == null ) {
      if ( !row.getColltaxLAmountNxtIsNull() )
        return false;
    } else if ( row.getColltaxLAmountNxtIsNull() || ( colltax_l_amount_nxt.doubleValue() != row.getColltaxLAmountNxt() ) ) {
        return false;
    }
    if ( remark == null ) {
      if ( row.getRemark() != null )
        return false;
    } else if ( !remark.equals( row.getRemark() ) ) {
        return false;
    }
    if ( proloss_amount_cps == null ) {
      if ( !row.getProlossAmountCpsIsNull() )
        return false;
    } else if ( row.getProlossAmountCpsIsNull() || ( proloss_amount_cps.doubleValue() != row.getProlossAmountCps() ) ) {
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
      return  ((int) profit_loss_spec_id)
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (branch_code!=null? branch_code.hashCode(): 0) 
        + (account_code!=null? account_code.hashCode(): 0) 
        + (trader_code_sonar!=null? trader_code_sonar.hashCode(): 0) 
        + (work_date!=null? work_date.hashCode(): 0) 
        + (tax_type!=null? tax_type.hashCode(): 0) 
        + (rec_div!=null? rec_div.hashCode(): 0) 
        + (sort_no!=null? sort_no.hashCode(): 0) 
        + (calc_date!=null? calc_date.hashCode(): 0) 
        + (delivery_date!=null? delivery_date.hashCode(): 0) 
        + (commodity_div!=null? commodity_div.hashCode(): 0) 
        + (application_code!=null? application_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (standard_name!=null? standard_name.hashCode(): 0) 
        + (term_div!=null? term_div.hashCode(): 0) 
        + (quantity!=null? quantity.hashCode(): 0) 
        + (pass_date!=null? pass_date.hashCode(): 0) 
        + (pass_amount!=null? pass_amount.hashCode(): 0) 
        + (get_date!=null? get_date.hashCode(): 0) 
        + (get_amount!=null? get_amount.hashCode(): 0) 
        + (proloss_amount!=null? proloss_amount.hashCode(): 0) 
        + (total_proloss_amount!=null? total_proloss_amount.hashCode(): 0) 
        + (taxable_amount!=null? taxable_amount.hashCode(): 0) 
        + (collect_tax_amount!=null? collect_tax_amount.hashCode(): 0) 
        + (collect_tax_n_amount!=null? collect_tax_n_amount.hashCode(): 0) 
        + (collect_tax_l_amount!=null? collect_tax_l_amount.hashCode(): 0) 
        + (return_div!=null? return_div.hashCode(): 0) 
        + (colltax_amount_curr!=null? colltax_amount_curr.hashCode(): 0) 
        + (colltax_n_amount_curr!=null? colltax_n_amount_curr.hashCode(): 0) 
        + (colltax_l_amount_curr!=null? colltax_l_amount_curr.hashCode(): 0) 
        + (colltax_amount_nxt!=null? colltax_amount_nxt.hashCode(): 0) 
        + (colltax_n_amount_nxt!=null? colltax_n_amount_nxt.hashCode(): 0) 
        + (colltax_l_amount_nxt!=null? colltax_l_amount_nxt.hashCode(): 0) 
        + (remark!=null? remark.hashCode(): 0) 
        + (proloss_amount_cps!=null? proloss_amount_cps.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !created_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'created_timestamp' must be set before inserting.");
		if ( !last_updated_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'last_updated_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("profit_loss_spec_id",new Long(profit_loss_spec_id));
		if ( institution_code != null )
			map.put("institution_code",institution_code);
		if ( branch_code != null )
			map.put("branch_code",branch_code);
		if ( account_code != null )
			map.put("account_code",account_code);
		if ( trader_code_sonar != null )
			map.put("trader_code_sonar",trader_code_sonar);
		if ( work_date != null )
			map.put("work_date",work_date);
		if ( tax_type != null )
			map.put("tax_type",tax_type);
		if ( rec_div != null )
			map.put("rec_div",rec_div);
		if ( sort_no != null )
			map.put("sort_no",sort_no);
		if ( calc_date != null )
			map.put("calc_date",calc_date);
		if ( delivery_date != null )
			map.put("delivery_date",delivery_date);
		if ( commodity_div != null )
			map.put("commodity_div",commodity_div);
		if ( application_code != null )
			map.put("application_code",application_code);
		if ( product_code != null )
			map.put("product_code",product_code);
		if ( standard_name != null )
			map.put("standard_name",standard_name);
		if ( term_div != null )
			map.put("term_div",term_div);
		if ( quantity != null )
			map.put("quantity",quantity);
		if ( pass_date != null )
			map.put("pass_date",pass_date);
		if ( pass_amount != null )
			map.put("pass_amount",pass_amount);
		if ( get_date != null )
			map.put("get_date",get_date);
		if ( get_amount != null )
			map.put("get_amount",get_amount);
		if ( proloss_amount != null )
			map.put("proloss_amount",proloss_amount);
		if ( total_proloss_amount != null )
			map.put("total_proloss_amount",total_proloss_amount);
		if ( taxable_amount != null )
			map.put("taxable_amount",taxable_amount);
		if ( collect_tax_amount != null )
			map.put("collect_tax_amount",collect_tax_amount);
		if ( collect_tax_n_amount != null )
			map.put("collect_tax_n_amount",collect_tax_n_amount);
		if ( collect_tax_l_amount != null )
			map.put("collect_tax_l_amount",collect_tax_l_amount);
		if ( return_div != null )
			map.put("return_div",return_div);
		if ( colltax_amount_curr != null )
			map.put("colltax_amount_curr",colltax_amount_curr);
		if ( colltax_n_amount_curr != null )
			map.put("colltax_n_amount_curr",colltax_n_amount_curr);
		if ( colltax_l_amount_curr != null )
			map.put("colltax_l_amount_curr",colltax_l_amount_curr);
		if ( colltax_amount_nxt != null )
			map.put("colltax_amount_nxt",colltax_amount_nxt);
		if ( colltax_n_amount_nxt != null )
			map.put("colltax_n_amount_nxt",colltax_n_amount_nxt);
		if ( colltax_l_amount_nxt != null )
			map.put("colltax_l_amount_nxt",colltax_l_amount_nxt);
		if ( remark != null )
			map.put("remark",remark);
		if ( proloss_amount_cps != null )
			map.put("proloss_amount_cps",proloss_amount_cps);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( branch_code_is_modified )
			map.put("branch_code",branch_code);
		if ( account_code_is_modified )
			map.put("account_code",account_code);
		if ( trader_code_sonar_is_modified )
			map.put("trader_code_sonar",trader_code_sonar);
		if ( work_date_is_modified )
			map.put("work_date",work_date);
		if ( tax_type_is_modified )
			map.put("tax_type",tax_type);
		if ( rec_div_is_modified )
			map.put("rec_div",rec_div);
		if ( sort_no_is_modified )
			map.put("sort_no",sort_no);
		if ( calc_date_is_modified )
			map.put("calc_date",calc_date);
		if ( delivery_date_is_modified )
			map.put("delivery_date",delivery_date);
		if ( commodity_div_is_modified )
			map.put("commodity_div",commodity_div);
		if ( application_code_is_modified )
			map.put("application_code",application_code);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( standard_name_is_modified )
			map.put("standard_name",standard_name);
		if ( term_div_is_modified )
			map.put("term_div",term_div);
		if ( quantity_is_modified )
			map.put("quantity",quantity);
		if ( pass_date_is_modified )
			map.put("pass_date",pass_date);
		if ( pass_amount_is_modified )
			map.put("pass_amount",pass_amount);
		if ( get_date_is_modified )
			map.put("get_date",get_date);
		if ( get_amount_is_modified )
			map.put("get_amount",get_amount);
		if ( proloss_amount_is_modified )
			map.put("proloss_amount",proloss_amount);
		if ( total_proloss_amount_is_modified )
			map.put("total_proloss_amount",total_proloss_amount);
		if ( taxable_amount_is_modified )
			map.put("taxable_amount",taxable_amount);
		if ( collect_tax_amount_is_modified )
			map.put("collect_tax_amount",collect_tax_amount);
		if ( collect_tax_n_amount_is_modified )
			map.put("collect_tax_n_amount",collect_tax_n_amount);
		if ( collect_tax_l_amount_is_modified )
			map.put("collect_tax_l_amount",collect_tax_l_amount);
		if ( return_div_is_modified )
			map.put("return_div",return_div);
		if ( colltax_amount_curr_is_modified )
			map.put("colltax_amount_curr",colltax_amount_curr);
		if ( colltax_n_amount_curr_is_modified )
			map.put("colltax_n_amount_curr",colltax_n_amount_curr);
		if ( colltax_l_amount_curr_is_modified )
			map.put("colltax_l_amount_curr",colltax_l_amount_curr);
		if ( colltax_amount_nxt_is_modified )
			map.put("colltax_amount_nxt",colltax_amount_nxt);
		if ( colltax_n_amount_nxt_is_modified )
			map.put("colltax_n_amount_nxt",colltax_n_amount_nxt);
		if ( colltax_l_amount_nxt_is_modified )
			map.put("colltax_l_amount_nxt",colltax_l_amount_nxt);
		if ( remark_is_modified )
			map.put("remark",remark);
		if ( proloss_amount_cps_is_modified )
			map.put("proloss_amount_cps",proloss_amount_cps);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("institution_code",institution_code);
			map.put("branch_code",branch_code);
			map.put("account_code",account_code);
			map.put("trader_code_sonar",trader_code_sonar);
			map.put("work_date",work_date);
			map.put("tax_type",tax_type);
			map.put("rec_div",rec_div);
			map.put("sort_no",sort_no);
			map.put("calc_date",calc_date);
			map.put("delivery_date",delivery_date);
			map.put("commodity_div",commodity_div);
			map.put("application_code",application_code);
			map.put("product_code",product_code);
			map.put("standard_name",standard_name);
			map.put("term_div",term_div);
			map.put("quantity",quantity);
			map.put("pass_date",pass_date);
			map.put("pass_amount",pass_amount);
			map.put("get_date",get_date);
			map.put("get_amount",get_amount);
			map.put("proloss_amount",proloss_amount);
			map.put("total_proloss_amount",total_proloss_amount);
			map.put("taxable_amount",taxable_amount);
			map.put("collect_tax_amount",collect_tax_amount);
			map.put("collect_tax_n_amount",collect_tax_n_amount);
			map.put("collect_tax_l_amount",collect_tax_l_amount);
			map.put("return_div",return_div);
			map.put("colltax_amount_curr",colltax_amount_curr);
			map.put("colltax_n_amount_curr",colltax_n_amount_curr);
			map.put("colltax_l_amount_curr",colltax_l_amount_curr);
			map.put("colltax_amount_nxt",colltax_amount_nxt);
			map.put("colltax_n_amount_nxt",colltax_n_amount_nxt);
			map.put("colltax_l_amount_nxt",colltax_l_amount_nxt);
			map.put("remark",remark);
			map.put("proloss_amount_cps",proloss_amount_cps);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>profit_loss_spec_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getProfitLossSpecId()
  {
    return profit_loss_spec_id;
  }


  /** 
   * <em>profit_loss_spec_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProfitLossSpecIdIsSet() {
    return profit_loss_spec_id_is_set;
  }


  /** 
   * <em>profit_loss_spec_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProfitLossSpecIdIsModified() {
    return profit_loss_spec_id_is_modified;
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
   * <em>trader_code_sonar</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTraderCodeSonar()
  {
    return trader_code_sonar;
  }


  /** 
   * <em>trader_code_sonar</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderCodeSonarIsSet() {
    return trader_code_sonar_is_set;
  }


  /** 
   * <em>trader_code_sonar</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTraderCodeSonarIsModified() {
    return trader_code_sonar_is_modified;
  }


  /** 
   * <em>work_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getWorkDate()
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
   * <em>tax_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTaxType()
  {
    return tax_type;
  }


  /** 
   * <em>tax_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxTypeIsSet() {
    return tax_type_is_set;
  }


  /** 
   * <em>tax_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxTypeIsModified() {
    return tax_type_is_modified;
  }


  /** 
   * <em>rec_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRecDiv()
  {
    return rec_div;
  }


  /** 
   * <em>rec_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecDivIsSet() {
    return rec_div_is_set;
  }


  /** 
   * <em>rec_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRecDivIsModified() {
    return rec_div_is_modified;
  }


  /** 
   * <em>sort_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getSortNo()
  {
    return sort_no;
  }


  /** 
   * <em>sort_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSortNoIsSet() {
    return sort_no_is_set;
  }


  /** 
   * <em>sort_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getSortNoIsModified() {
    return sort_no_is_modified;
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
   * <em>delivery_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getDeliveryDate()
  {
    return delivery_date;
  }


  /** 
   * <em>delivery_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateIsSet() {
    return delivery_date_is_set;
  }


  /** 
   * <em>delivery_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDeliveryDateIsModified() {
    return delivery_date_is_modified;
  }


  /** 
   * <em>commodity_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommodityDiv()
  {
    return commodity_div;
  }


  /** 
   * <em>commodity_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommodityDivIsSet() {
    return commodity_div_is_set;
  }


  /** 
   * <em>commodity_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommodityDivIsModified() {
    return commodity_div_is_modified;
  }


  /** 
   * <em>application_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getApplicationCode()
  {
    return application_code;
  }


  /** 
   * <em>application_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApplicationCodeIsSet() {
    return application_code_is_set;
  }


  /** 
   * <em>application_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getApplicationCodeIsModified() {
    return application_code_is_modified;
  }


  /** 
   * <em>product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProductCode()
  {
    return product_code;
  }


  /** 
   * <em>product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductCodeIsSet() {
    return product_code_is_set;
  }


  /** 
   * <em>product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductCodeIsModified() {
    return product_code_is_modified;
  }


  /** 
   * <em>standard_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getStandardName()
  {
    return standard_name;
  }


  /** 
   * <em>standard_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStandardNameIsSet() {
    return standard_name_is_set;
  }


  /** 
   * <em>standard_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getStandardNameIsModified() {
    return standard_name_is_modified;
  }


  /** 
   * <em>term_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getTermDiv()
  {
    return term_div;
  }


  /** 
   * <em>term_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTermDivIsSet() {
    return term_div_is_set;
  }


  /** 
   * <em>term_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTermDivIsModified() {
    return term_div_is_modified;
  }


  /** 
   * <em>quantity</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getQuantity()
  {
    return ( quantity==null? ((double)0): quantity.doubleValue() );
  }


  /** 
   * <em>quantity</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getQuantityIsNull()
  {
    return quantity == null;
  }


  /** 
   * <em>quantity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuantityIsSet() {
    return quantity_is_set;
  }


  /** 
   * <em>quantity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getQuantityIsModified() {
    return quantity_is_modified;
  }


  /** 
   * <em>pass_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getPassDate()
  {
    return pass_date;
  }


  /** 
   * <em>pass_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPassDateIsSet() {
    return pass_date_is_set;
  }


  /** 
   * <em>pass_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPassDateIsModified() {
    return pass_date_is_modified;
  }


  /** 
   * <em>pass_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getPassAmount()
  {
    return ( pass_amount==null? ((double)0): pass_amount.doubleValue() );
  }


  /** 
   * <em>pass_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getPassAmountIsNull()
  {
    return pass_amount == null;
  }


  /** 
   * <em>pass_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPassAmountIsSet() {
    return pass_amount_is_set;
  }


  /** 
   * <em>pass_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getPassAmountIsModified() {
    return pass_amount_is_modified;
  }


  /** 
   * <em>get_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getGetDate()
  {
    return get_date;
  }


  /** 
   * <em>get_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGetDateIsSet() {
    return get_date_is_set;
  }


  /** 
   * <em>get_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGetDateIsModified() {
    return get_date_is_modified;
  }


  /** 
   * <em>get_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getGetAmount()
  {
    return ( get_amount==null? ((double)0): get_amount.doubleValue() );
  }


  /** 
   * <em>get_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getGetAmountIsNull()
  {
    return get_amount == null;
  }


  /** 
   * <em>get_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGetAmountIsSet() {
    return get_amount_is_set;
  }


  /** 
   * <em>get_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getGetAmountIsModified() {
    return get_amount_is_modified;
  }


  /** 
   * <em>proloss_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getProlossAmount()
  {
    return ( proloss_amount==null? ((double)0): proloss_amount.doubleValue() );
  }


  /** 
   * <em>proloss_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getProlossAmountIsNull()
  {
    return proloss_amount == null;
  }


  /** 
   * <em>proloss_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProlossAmountIsSet() {
    return proloss_amount_is_set;
  }


  /** 
   * <em>proloss_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProlossAmountIsModified() {
    return proloss_amount_is_modified;
  }


  /** 
   * <em>total_proloss_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTotalProlossAmount()
  {
    return ( total_proloss_amount==null? ((double)0): total_proloss_amount.doubleValue() );
  }


  /** 
   * <em>total_proloss_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTotalProlossAmountIsNull()
  {
    return total_proloss_amount == null;
  }


  /** 
   * <em>total_proloss_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTotalProlossAmountIsSet() {
    return total_proloss_amount_is_set;
  }


  /** 
   * <em>total_proloss_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTotalProlossAmountIsModified() {
    return total_proloss_amount_is_modified;
  }


  /** 
   * <em>taxable_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getTaxableAmount()
  {
    return ( taxable_amount==null? ((double)0): taxable_amount.doubleValue() );
  }


  /** 
   * <em>taxable_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getTaxableAmountIsNull()
  {
    return taxable_amount == null;
  }


  /** 
   * <em>taxable_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxableAmountIsSet() {
    return taxable_amount_is_set;
  }


  /** 
   * <em>taxable_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTaxableAmountIsModified() {
    return taxable_amount_is_modified;
  }


  /** 
   * <em>collect_tax_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCollectTaxAmount()
  {
    return ( collect_tax_amount==null? ((double)0): collect_tax_amount.doubleValue() );
  }


  /** 
   * <em>collect_tax_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCollectTaxAmountIsNull()
  {
    return collect_tax_amount == null;
  }


  /** 
   * <em>collect_tax_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCollectTaxAmountIsSet() {
    return collect_tax_amount_is_set;
  }


  /** 
   * <em>collect_tax_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCollectTaxAmountIsModified() {
    return collect_tax_amount_is_modified;
  }


  /** 
   * <em>collect_tax_n_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCollectTaxNAmount()
  {
    return ( collect_tax_n_amount==null? ((double)0): collect_tax_n_amount.doubleValue() );
  }


  /** 
   * <em>collect_tax_n_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCollectTaxNAmountIsNull()
  {
    return collect_tax_n_amount == null;
  }


  /** 
   * <em>collect_tax_n_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCollectTaxNAmountIsSet() {
    return collect_tax_n_amount_is_set;
  }


  /** 
   * <em>collect_tax_n_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCollectTaxNAmountIsModified() {
    return collect_tax_n_amount_is_modified;
  }


  /** 
   * <em>collect_tax_l_amount</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCollectTaxLAmount()
  {
    return ( collect_tax_l_amount==null? ((double)0): collect_tax_l_amount.doubleValue() );
  }


  /** 
   * <em>collect_tax_l_amount</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCollectTaxLAmountIsNull()
  {
    return collect_tax_l_amount == null;
  }


  /** 
   * <em>collect_tax_l_amount</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCollectTaxLAmountIsSet() {
    return collect_tax_l_amount_is_set;
  }


  /** 
   * <em>collect_tax_l_amount</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCollectTaxLAmountIsModified() {
    return collect_tax_l_amount_is_modified;
  }


  /** 
   * <em>return_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getReturnDiv()
  {
    return return_div;
  }


  /** 
   * <em>return_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReturnDivIsSet() {
    return return_div_is_set;
  }


  /** 
   * <em>return_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getReturnDivIsModified() {
    return return_div_is_modified;
  }


  /** 
   * <em>colltax_amount_curr</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getColltaxAmountCurr()
  {
    return ( colltax_amount_curr==null? ((double)0): colltax_amount_curr.doubleValue() );
  }


  /** 
   * <em>colltax_amount_curr</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getColltaxAmountCurrIsNull()
  {
    return colltax_amount_curr == null;
  }


  /** 
   * <em>colltax_amount_curr</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColltaxAmountCurrIsSet() {
    return colltax_amount_curr_is_set;
  }


  /** 
   * <em>colltax_amount_curr</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColltaxAmountCurrIsModified() {
    return colltax_amount_curr_is_modified;
  }


  /** 
   * <em>colltax_n_amount_curr</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getColltaxNAmountCurr()
  {
    return ( colltax_n_amount_curr==null? ((double)0): colltax_n_amount_curr.doubleValue() );
  }


  /** 
   * <em>colltax_n_amount_curr</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getColltaxNAmountCurrIsNull()
  {
    return colltax_n_amount_curr == null;
  }


  /** 
   * <em>colltax_n_amount_curr</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColltaxNAmountCurrIsSet() {
    return colltax_n_amount_curr_is_set;
  }


  /** 
   * <em>colltax_n_amount_curr</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColltaxNAmountCurrIsModified() {
    return colltax_n_amount_curr_is_modified;
  }


  /** 
   * <em>colltax_l_amount_curr</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getColltaxLAmountCurr()
  {
    return ( colltax_l_amount_curr==null? ((double)0): colltax_l_amount_curr.doubleValue() );
  }


  /** 
   * <em>colltax_l_amount_curr</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getColltaxLAmountCurrIsNull()
  {
    return colltax_l_amount_curr == null;
  }


  /** 
   * <em>colltax_l_amount_curr</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColltaxLAmountCurrIsSet() {
    return colltax_l_amount_curr_is_set;
  }


  /** 
   * <em>colltax_l_amount_curr</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColltaxLAmountCurrIsModified() {
    return colltax_l_amount_curr_is_modified;
  }


  /** 
   * <em>colltax_amount_nxt</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getColltaxAmountNxt()
  {
    return ( colltax_amount_nxt==null? ((double)0): colltax_amount_nxt.doubleValue() );
  }


  /** 
   * <em>colltax_amount_nxt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getColltaxAmountNxtIsNull()
  {
    return colltax_amount_nxt == null;
  }


  /** 
   * <em>colltax_amount_nxt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColltaxAmountNxtIsSet() {
    return colltax_amount_nxt_is_set;
  }


  /** 
   * <em>colltax_amount_nxt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColltaxAmountNxtIsModified() {
    return colltax_amount_nxt_is_modified;
  }


  /** 
   * <em>colltax_n_amount_nxt</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getColltaxNAmountNxt()
  {
    return ( colltax_n_amount_nxt==null? ((double)0): colltax_n_amount_nxt.doubleValue() );
  }


  /** 
   * <em>colltax_n_amount_nxt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getColltaxNAmountNxtIsNull()
  {
    return colltax_n_amount_nxt == null;
  }


  /** 
   * <em>colltax_n_amount_nxt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColltaxNAmountNxtIsSet() {
    return colltax_n_amount_nxt_is_set;
  }


  /** 
   * <em>colltax_n_amount_nxt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColltaxNAmountNxtIsModified() {
    return colltax_n_amount_nxt_is_modified;
  }


  /** 
   * <em>colltax_l_amount_nxt</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getColltaxLAmountNxt()
  {
    return ( colltax_l_amount_nxt==null? ((double)0): colltax_l_amount_nxt.doubleValue() );
  }


  /** 
   * <em>colltax_l_amount_nxt</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getColltaxLAmountNxtIsNull()
  {
    return colltax_l_amount_nxt == null;
  }


  /** 
   * <em>colltax_l_amount_nxt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColltaxLAmountNxtIsSet() {
    return colltax_l_amount_nxt_is_set;
  }


  /** 
   * <em>colltax_l_amount_nxt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getColltaxLAmountNxtIsModified() {
    return colltax_l_amount_nxt_is_modified;
  }


  /** 
   * <em>remark</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRemark()
  {
    return remark;
  }


  /** 
   * <em>remark</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarkIsSet() {
    return remark_is_set;
  }


  /** 
   * <em>remark</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRemarkIsModified() {
    return remark_is_modified;
  }


  /** 
   * <em>proloss_amount_cps</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getProlossAmountCps()
  {
    return ( proloss_amount_cps==null? ((double)0): proloss_amount_cps.doubleValue() );
  }


  /** 
   * <em>proloss_amount_cps</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getProlossAmountCpsIsNull()
  {
    return proloss_amount_cps == null;
  }


  /** 
   * <em>proloss_amount_cps</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProlossAmountCpsIsSet() {
    return proloss_amount_cps_is_set;
  }


  /** 
   * <em>proloss_amount_cps</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProlossAmountCpsIsModified() {
    return proloss_amount_cps_is_modified;
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
    return new ProfitLossSpecPK(profit_loss_spec_id);
  }


  /** 
   * <em>profit_loss_spec_id</em>カラムの値を設定します。 
   *
   * @@param p_profitLossSpecId <em>profit_loss_spec_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setProfitLossSpecId( long p_profitLossSpecId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    profit_loss_spec_id = p_profitLossSpecId;
    profit_loss_spec_id_is_set = true;
    profit_loss_spec_id_is_modified = true;
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
   * <em>trader_code_sonar</em>カラムの値を設定します。 
   *
   * @@param p_traderCodeSonar <em>trader_code_sonar</em>カラムの値をあらわす5桁以下のString値 
   */
  public final void setTraderCodeSonar( String p_traderCodeSonar )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trader_code_sonar = p_traderCodeSonar;
    trader_code_sonar_is_set = true;
    trader_code_sonar_is_modified = true;
  }


  /** 
   * <em>work_date</em>カラムの値を設定します。 
   *
   * @@param p_workDate <em>work_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setWorkDate( java.sql.Timestamp p_workDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    work_date = p_workDate;
    work_date_is_set = true;
    work_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>work_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setWorkDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    work_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    work_date_is_set = true;
    work_date_is_modified = true;
  }


  /** 
   * <em>tax_type</em>カラムの値を設定します。 
   *
   * @@param p_taxType <em>tax_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTaxType( String p_taxType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    tax_type = p_taxType;
    tax_type_is_set = true;
    tax_type_is_modified = true;
  }


  /** 
   * <em>rec_div</em>カラムの値を設定します。 
   *
   * @@param p_recDiv <em>rec_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setRecDiv( String p_recDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    rec_div = p_recDiv;
    rec_div_is_set = true;
    rec_div_is_modified = true;
  }


  /** 
   * <em>sort_no</em>カラムの値を設定します。 
   *
   * @@param p_sortNo <em>sort_no</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setSortNo( String p_sortNo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    sort_no = p_sortNo;
    sort_no_is_set = true;
    sort_no_is_modified = true;
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
   * <em>delivery_date</em>カラムの値を設定します。 
   *
   * @@param p_deliveryDate <em>delivery_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setDeliveryDate( java.sql.Timestamp p_deliveryDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = p_deliveryDate;
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>delivery_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setDeliveryDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    delivery_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    delivery_date_is_set = true;
    delivery_date_is_modified = true;
  }


  /** 
   * <em>commodity_div</em>カラムの値を設定します。 
   *
   * @@param p_commodityDiv <em>commodity_div</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setCommodityDiv( String p_commodityDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commodity_div = p_commodityDiv;
    commodity_div_is_set = true;
    commodity_div_is_modified = true;
  }


  /** 
   * <em>application_code</em>カラムの値を設定します。 
   *
   * @@param p_applicationCode <em>application_code</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setApplicationCode( String p_applicationCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    application_code = p_applicationCode;
    application_code_is_set = true;
    application_code_is_modified = true;
  }


  /** 
   * <em>product_code</em>カラムの値を設定します。 
   *
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす9桁以下のString値 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>standard_name</em>カラムの値を設定します。 
   *
   * @@param p_standardName <em>standard_name</em>カラムの値をあらわす26桁以下のString値 
   */
  public final void setStandardName( String p_standardName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    standard_name = p_standardName;
    standard_name_is_set = true;
    standard_name_is_modified = true;
  }


  /** 
   * <em>term_div</em>カラムの値を設定します。 
   *
   * @@param p_termDiv <em>term_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setTermDiv( String p_termDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    term_div = p_termDiv;
    term_div_is_set = true;
    term_div_is_modified = true;
  }


  /** 
   * <em>quantity</em>カラムの値を設定します。 
   *
   * @@param p_quantity <em>quantity</em>カラムの値をあらわす19桁以下のdouble値で、その精度は5桁まで
   */
  public final void setQuantity( double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = new Double(p_quantity);
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>quantity</em>カラムに値を設定します。 
   */
  public final void setQuantity( Double p_quantity )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    quantity = p_quantity;
    quantity_is_set = true;
    quantity_is_modified = true;
  }


  /** 
   * <em>pass_date</em>カラムの値を設定します。 
   *
   * @@param p_passDate <em>pass_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setPassDate( java.sql.Timestamp p_passDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pass_date = p_passDate;
    pass_date_is_set = true;
    pass_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>pass_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setPassDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    pass_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    pass_date_is_set = true;
    pass_date_is_modified = true;
  }


  /** 
   * <em>pass_amount</em>カラムの値を設定します。 
   *
   * @@param p_passAmount <em>pass_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setPassAmount( double p_passAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    pass_amount = new Double(p_passAmount);
    pass_amount_is_set = true;
    pass_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>pass_amount</em>カラムに値を設定します。 
   */
  public final void setPassAmount( Double p_passAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    pass_amount = p_passAmount;
    pass_amount_is_set = true;
    pass_amount_is_modified = true;
  }


  /** 
   * <em>get_date</em>カラムの値を設定します。 
   *
   * @@param p_getDate <em>get_date</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setGetDate( java.sql.Timestamp p_getDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    get_date = p_getDate;
    get_date_is_set = true;
    get_date_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>get_date</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setGetDate( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    get_date = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    get_date_is_set = true;
    get_date_is_modified = true;
  }


  /** 
   * <em>get_amount</em>カラムの値を設定します。 
   *
   * @@param p_getAmount <em>get_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setGetAmount( double p_getAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    get_amount = new Double(p_getAmount);
    get_amount_is_set = true;
    get_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>get_amount</em>カラムに値を設定します。 
   */
  public final void setGetAmount( Double p_getAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    get_amount = p_getAmount;
    get_amount_is_set = true;
    get_amount_is_modified = true;
  }


  /** 
   * <em>proloss_amount</em>カラムの値を設定します。 
   *
   * @@param p_prolossAmount <em>proloss_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setProlossAmount( double p_prolossAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    proloss_amount = new Double(p_prolossAmount);
    proloss_amount_is_set = true;
    proloss_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>proloss_amount</em>カラムに値を設定します。 
   */
  public final void setProlossAmount( Double p_prolossAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    proloss_amount = p_prolossAmount;
    proloss_amount_is_set = true;
    proloss_amount_is_modified = true;
  }


  /** 
   * <em>total_proloss_amount</em>カラムの値を設定します。 
   *
   * @@param p_totalProlossAmount <em>total_proloss_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTotalProlossAmount( double p_totalProlossAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    total_proloss_amount = new Double(p_totalProlossAmount);
    total_proloss_amount_is_set = true;
    total_proloss_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>total_proloss_amount</em>カラムに値を設定します。 
   */
  public final void setTotalProlossAmount( Double p_totalProlossAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    total_proloss_amount = p_totalProlossAmount;
    total_proloss_amount_is_set = true;
    total_proloss_amount_is_modified = true;
  }


  /** 
   * <em>taxable_amount</em>カラムの値を設定します。 
   *
   * @@param p_taxableAmount <em>taxable_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setTaxableAmount( double p_taxableAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    taxable_amount = new Double(p_taxableAmount);
    taxable_amount_is_set = true;
    taxable_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>taxable_amount</em>カラムに値を設定します。 
   */
  public final void setTaxableAmount( Double p_taxableAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    taxable_amount = p_taxableAmount;
    taxable_amount_is_set = true;
    taxable_amount_is_modified = true;
  }


  /** 
   * <em>collect_tax_amount</em>カラムの値を設定します。 
   *
   * @@param p_collectTaxAmount <em>collect_tax_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCollectTaxAmount( double p_collectTaxAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    collect_tax_amount = new Double(p_collectTaxAmount);
    collect_tax_amount_is_set = true;
    collect_tax_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>collect_tax_amount</em>カラムに値を設定します。 
   */
  public final void setCollectTaxAmount( Double p_collectTaxAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    collect_tax_amount = p_collectTaxAmount;
    collect_tax_amount_is_set = true;
    collect_tax_amount_is_modified = true;
  }


  /** 
   * <em>collect_tax_n_amount</em>カラムの値を設定します。 
   *
   * @@param p_collectTaxNAmount <em>collect_tax_n_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCollectTaxNAmount( double p_collectTaxNAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    collect_tax_n_amount = new Double(p_collectTaxNAmount);
    collect_tax_n_amount_is_set = true;
    collect_tax_n_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>collect_tax_n_amount</em>カラムに値を設定します。 
   */
  public final void setCollectTaxNAmount( Double p_collectTaxNAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    collect_tax_n_amount = p_collectTaxNAmount;
    collect_tax_n_amount_is_set = true;
    collect_tax_n_amount_is_modified = true;
  }


  /** 
   * <em>collect_tax_l_amount</em>カラムの値を設定します。 
   *
   * @@param p_collectTaxLAmount <em>collect_tax_l_amount</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCollectTaxLAmount( double p_collectTaxLAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    collect_tax_l_amount = new Double(p_collectTaxLAmount);
    collect_tax_l_amount_is_set = true;
    collect_tax_l_amount_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>collect_tax_l_amount</em>カラムに値を設定します。 
   */
  public final void setCollectTaxLAmount( Double p_collectTaxLAmount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    collect_tax_l_amount = p_collectTaxLAmount;
    collect_tax_l_amount_is_set = true;
    collect_tax_l_amount_is_modified = true;
  }


  /** 
   * <em>return_div</em>カラムの値を設定します。 
   *
   * @@param p_returnDiv <em>return_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setReturnDiv( String p_returnDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    return_div = p_returnDiv;
    return_div_is_set = true;
    return_div_is_modified = true;
  }


  /** 
   * <em>colltax_amount_curr</em>カラムの値を設定します。 
   *
   * @@param p_colltaxAmountCurr <em>colltax_amount_curr</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setColltaxAmountCurr( double p_colltaxAmountCurr )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    colltax_amount_curr = new Double(p_colltaxAmountCurr);
    colltax_amount_curr_is_set = true;
    colltax_amount_curr_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>colltax_amount_curr</em>カラムに値を設定します。 
   */
  public final void setColltaxAmountCurr( Double p_colltaxAmountCurr )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    colltax_amount_curr = p_colltaxAmountCurr;
    colltax_amount_curr_is_set = true;
    colltax_amount_curr_is_modified = true;
  }


  /** 
   * <em>colltax_n_amount_curr</em>カラムの値を設定します。 
   *
   * @@param p_colltaxNAmountCurr <em>colltax_n_amount_curr</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setColltaxNAmountCurr( double p_colltaxNAmountCurr )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    colltax_n_amount_curr = new Double(p_colltaxNAmountCurr);
    colltax_n_amount_curr_is_set = true;
    colltax_n_amount_curr_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>colltax_n_amount_curr</em>カラムに値を設定します。 
   */
  public final void setColltaxNAmountCurr( Double p_colltaxNAmountCurr )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    colltax_n_amount_curr = p_colltaxNAmountCurr;
    colltax_n_amount_curr_is_set = true;
    colltax_n_amount_curr_is_modified = true;
  }


  /** 
   * <em>colltax_l_amount_curr</em>カラムの値を設定します。 
   *
   * @@param p_colltaxLAmountCurr <em>colltax_l_amount_curr</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setColltaxLAmountCurr( double p_colltaxLAmountCurr )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    colltax_l_amount_curr = new Double(p_colltaxLAmountCurr);
    colltax_l_amount_curr_is_set = true;
    colltax_l_amount_curr_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>colltax_l_amount_curr</em>カラムに値を設定します。 
   */
  public final void setColltaxLAmountCurr( Double p_colltaxLAmountCurr )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    colltax_l_amount_curr = p_colltaxLAmountCurr;
    colltax_l_amount_curr_is_set = true;
    colltax_l_amount_curr_is_modified = true;
  }


  /** 
   * <em>colltax_amount_nxt</em>カラムの値を設定します。 
   *
   * @@param p_colltaxAmountNxt <em>colltax_amount_nxt</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setColltaxAmountNxt( double p_colltaxAmountNxt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    colltax_amount_nxt = new Double(p_colltaxAmountNxt);
    colltax_amount_nxt_is_set = true;
    colltax_amount_nxt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>colltax_amount_nxt</em>カラムに値を設定します。 
   */
  public final void setColltaxAmountNxt( Double p_colltaxAmountNxt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    colltax_amount_nxt = p_colltaxAmountNxt;
    colltax_amount_nxt_is_set = true;
    colltax_amount_nxt_is_modified = true;
  }


  /** 
   * <em>colltax_n_amount_nxt</em>カラムの値を設定します。 
   *
   * @@param p_colltaxNAmountNxt <em>colltax_n_amount_nxt</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setColltaxNAmountNxt( double p_colltaxNAmountNxt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    colltax_n_amount_nxt = new Double(p_colltaxNAmountNxt);
    colltax_n_amount_nxt_is_set = true;
    colltax_n_amount_nxt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>colltax_n_amount_nxt</em>カラムに値を設定します。 
   */
  public final void setColltaxNAmountNxt( Double p_colltaxNAmountNxt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    colltax_n_amount_nxt = p_colltaxNAmountNxt;
    colltax_n_amount_nxt_is_set = true;
    colltax_n_amount_nxt_is_modified = true;
  }


  /** 
   * <em>colltax_l_amount_nxt</em>カラムの値を設定します。 
   *
   * @@param p_colltaxLAmountNxt <em>colltax_l_amount_nxt</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setColltaxLAmountNxt( double p_colltaxLAmountNxt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    colltax_l_amount_nxt = new Double(p_colltaxLAmountNxt);
    colltax_l_amount_nxt_is_set = true;
    colltax_l_amount_nxt_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>colltax_l_amount_nxt</em>カラムに値を設定します。 
   */
  public final void setColltaxLAmountNxt( Double p_colltaxLAmountNxt )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    colltax_l_amount_nxt = p_colltaxLAmountNxt;
    colltax_l_amount_nxt_is_set = true;
    colltax_l_amount_nxt_is_modified = true;
  }


  /** 
   * <em>remark</em>カラムの値を設定します。 
   *
   * @@param p_remark <em>remark</em>カラムの値をあらわす2桁以下のString値 
   */
  public final void setRemark( String p_remark )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    remark = p_remark;
    remark_is_set = true;
    remark_is_modified = true;
  }


  /** 
   * <em>proloss_amount_cps</em>カラムの値を設定します。 
   *
   * @@param p_prolossAmountCps <em>proloss_amount_cps</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setProlossAmountCps( double p_prolossAmountCps )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    proloss_amount_cps = new Double(p_prolossAmountCps);
    proloss_amount_cps_is_set = true;
    proloss_amount_cps_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>proloss_amount_cps</em>カラムに値を設定します。 
   */
  public final void setProlossAmountCps( Double p_prolossAmountCps )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    proloss_amount_cps = p_prolossAmountCps;
    proloss_amount_cps_is_set = true;
    proloss_amount_cps_is_modified = true;
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
                if ( name.equals("account_code") ) {
                    return this.account_code;
                }
                else if ( name.equals("application_code") ) {
                    return this.application_code;
                }
                break;
            case 'b':
                if ( name.equals("branch_code") ) {
                    return this.branch_code;
                }
                break;
            case 'c':
                if ( name.equals("calc_date") ) {
                    return this.calc_date;
                }
                else if ( name.equals("commodity_div") ) {
                    return this.commodity_div;
                }
                else if ( name.equals("collect_tax_amount") ) {
                    return this.collect_tax_amount;
                }
                else if ( name.equals("collect_tax_n_amount") ) {
                    return this.collect_tax_n_amount;
                }
                else if ( name.equals("collect_tax_l_amount") ) {
                    return this.collect_tax_l_amount;
                }
                else if ( name.equals("colltax_amount_curr") ) {
                    return this.colltax_amount_curr;
                }
                else if ( name.equals("colltax_n_amount_curr") ) {
                    return this.colltax_n_amount_curr;
                }
                else if ( name.equals("colltax_l_amount_curr") ) {
                    return this.colltax_l_amount_curr;
                }
                else if ( name.equals("colltax_amount_nxt") ) {
                    return this.colltax_amount_nxt;
                }
                else if ( name.equals("colltax_n_amount_nxt") ) {
                    return this.colltax_n_amount_nxt;
                }
                else if ( name.equals("colltax_l_amount_nxt") ) {
                    return this.colltax_l_amount_nxt;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("delivery_date") ) {
                    return this.delivery_date;
                }
                break;
            case 'g':
                if ( name.equals("get_date") ) {
                    return this.get_date;
                }
                else if ( name.equals("get_amount") ) {
                    return this.get_amount;
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
            case 'p':
                if ( name.equals("profit_loss_spec_id") ) {
                    return new Long( profit_loss_spec_id );
                }
                else if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("pass_date") ) {
                    return this.pass_date;
                }
                else if ( name.equals("pass_amount") ) {
                    return this.pass_amount;
                }
                else if ( name.equals("proloss_amount") ) {
                    return this.proloss_amount;
                }
                else if ( name.equals("proloss_amount_cps") ) {
                    return this.proloss_amount_cps;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    return this.quantity;
                }
                break;
            case 'r':
                if ( name.equals("rec_div") ) {
                    return this.rec_div;
                }
                else if ( name.equals("return_div") ) {
                    return this.return_div;
                }
                else if ( name.equals("remark") ) {
                    return this.remark;
                }
                break;
            case 's':
                if ( name.equals("sort_no") ) {
                    return this.sort_no;
                }
                else if ( name.equals("standard_name") ) {
                    return this.standard_name;
                }
                break;
            case 't':
                if ( name.equals("trader_code_sonar") ) {
                    return this.trader_code_sonar;
                }
                else if ( name.equals("tax_type") ) {
                    return this.tax_type;
                }
                else if ( name.equals("term_div") ) {
                    return this.term_div;
                }
                else if ( name.equals("total_proloss_amount") ) {
                    return this.total_proloss_amount;
                }
                else if ( name.equals("taxable_amount") ) {
                    return this.taxable_amount;
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
                if ( name.equals("account_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'account_code' must be String: '"+value+"' is not." );
                    this.account_code = (String) value;
                    if (this.account_code_is_set)
                        this.account_code_is_modified = true;
                    this.account_code_is_set = true;
                    return;
                }
                else if ( name.equals("application_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'application_code' must be String: '"+value+"' is not." );
                    this.application_code = (String) value;
                    if (this.application_code_is_set)
                        this.application_code_is_modified = true;
                    this.application_code_is_set = true;
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
                if ( name.equals("calc_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'calc_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.calc_date = (java.sql.Timestamp) value;
                    if (this.calc_date_is_set)
                        this.calc_date_is_modified = true;
                    this.calc_date_is_set = true;
                    return;
                }
                else if ( name.equals("commodity_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commodity_div' must be String: '"+value+"' is not." );
                    this.commodity_div = (String) value;
                    if (this.commodity_div_is_set)
                        this.commodity_div_is_modified = true;
                    this.commodity_div_is_set = true;
                    return;
                }
                else if ( name.equals("collect_tax_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'collect_tax_amount' must be Double: '"+value+"' is not." );
                    this.collect_tax_amount = (Double) value;
                    if (this.collect_tax_amount_is_set)
                        this.collect_tax_amount_is_modified = true;
                    this.collect_tax_amount_is_set = true;
                    return;
                }
                else if ( name.equals("collect_tax_n_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'collect_tax_n_amount' must be Double: '"+value+"' is not." );
                    this.collect_tax_n_amount = (Double) value;
                    if (this.collect_tax_n_amount_is_set)
                        this.collect_tax_n_amount_is_modified = true;
                    this.collect_tax_n_amount_is_set = true;
                    return;
                }
                else if ( name.equals("collect_tax_l_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'collect_tax_l_amount' must be Double: '"+value+"' is not." );
                    this.collect_tax_l_amount = (Double) value;
                    if (this.collect_tax_l_amount_is_set)
                        this.collect_tax_l_amount_is_modified = true;
                    this.collect_tax_l_amount_is_set = true;
                    return;
                }
                else if ( name.equals("colltax_amount_curr") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'colltax_amount_curr' must be Double: '"+value+"' is not." );
                    this.colltax_amount_curr = (Double) value;
                    if (this.colltax_amount_curr_is_set)
                        this.colltax_amount_curr_is_modified = true;
                    this.colltax_amount_curr_is_set = true;
                    return;
                }
                else if ( name.equals("colltax_n_amount_curr") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'colltax_n_amount_curr' must be Double: '"+value+"' is not." );
                    this.colltax_n_amount_curr = (Double) value;
                    if (this.colltax_n_amount_curr_is_set)
                        this.colltax_n_amount_curr_is_modified = true;
                    this.colltax_n_amount_curr_is_set = true;
                    return;
                }
                else if ( name.equals("colltax_l_amount_curr") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'colltax_l_amount_curr' must be Double: '"+value+"' is not." );
                    this.colltax_l_amount_curr = (Double) value;
                    if (this.colltax_l_amount_curr_is_set)
                        this.colltax_l_amount_curr_is_modified = true;
                    this.colltax_l_amount_curr_is_set = true;
                    return;
                }
                else if ( name.equals("colltax_amount_nxt") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'colltax_amount_nxt' must be Double: '"+value+"' is not." );
                    this.colltax_amount_nxt = (Double) value;
                    if (this.colltax_amount_nxt_is_set)
                        this.colltax_amount_nxt_is_modified = true;
                    this.colltax_amount_nxt_is_set = true;
                    return;
                }
                else if ( name.equals("colltax_n_amount_nxt") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'colltax_n_amount_nxt' must be Double: '"+value+"' is not." );
                    this.colltax_n_amount_nxt = (Double) value;
                    if (this.colltax_n_amount_nxt_is_set)
                        this.colltax_n_amount_nxt_is_modified = true;
                    this.colltax_n_amount_nxt_is_set = true;
                    return;
                }
                else if ( name.equals("colltax_l_amount_nxt") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'colltax_l_amount_nxt' must be Double: '"+value+"' is not." );
                    this.colltax_l_amount_nxt = (Double) value;
                    if (this.colltax_l_amount_nxt_is_set)
                        this.colltax_l_amount_nxt_is_modified = true;
                    this.colltax_l_amount_nxt_is_set = true;
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
                if ( name.equals("delivery_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'delivery_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.delivery_date = (java.sql.Timestamp) value;
                    if (this.delivery_date_is_set)
                        this.delivery_date_is_modified = true;
                    this.delivery_date_is_set = true;
                    return;
                }
                break;
            case 'g':
                if ( name.equals("get_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'get_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.get_date = (java.sql.Timestamp) value;
                    if (this.get_date_is_set)
                        this.get_date_is_modified = true;
                    this.get_date_is_set = true;
                    return;
                }
                else if ( name.equals("get_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'get_amount' must be Double: '"+value+"' is not." );
                    this.get_amount = (Double) value;
                    if (this.get_amount_is_set)
                        this.get_amount_is_modified = true;
                    this.get_amount_is_set = true;
                    return;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
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
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("profit_loss_spec_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'profit_loss_spec_id' must be Long: '"+value+"' is not." );
                    this.profit_loss_spec_id = ((Long) value).longValue();
                    if (this.profit_loss_spec_id_is_set)
                        this.profit_loss_spec_id_is_modified = true;
                    this.profit_loss_spec_id_is_set = true;
                    return;
                }
                else if ( name.equals("product_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                else if ( name.equals("pass_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'pass_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.pass_date = (java.sql.Timestamp) value;
                    if (this.pass_date_is_set)
                        this.pass_date_is_modified = true;
                    this.pass_date_is_set = true;
                    return;
                }
                else if ( name.equals("pass_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'pass_amount' must be Double: '"+value+"' is not." );
                    this.pass_amount = (Double) value;
                    if (this.pass_amount_is_set)
                        this.pass_amount_is_modified = true;
                    this.pass_amount_is_set = true;
                    return;
                }
                else if ( name.equals("proloss_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'proloss_amount' must be Double: '"+value+"' is not." );
                    this.proloss_amount = (Double) value;
                    if (this.proloss_amount_is_set)
                        this.proloss_amount_is_modified = true;
                    this.proloss_amount_is_set = true;
                    return;
                }
                else if ( name.equals("proloss_amount_cps") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'proloss_amount_cps' must be Double: '"+value+"' is not." );
                    this.proloss_amount_cps = (Double) value;
                    if (this.proloss_amount_cps_is_set)
                        this.proloss_amount_cps_is_modified = true;
                    this.proloss_amount_cps_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("quantity") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'quantity' must be Double: '"+value+"' is not." );
                    this.quantity = (Double) value;
                    if (this.quantity_is_set)
                        this.quantity_is_modified = true;
                    this.quantity_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("rec_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'rec_div' must be String: '"+value+"' is not." );
                    this.rec_div = (String) value;
                    if (this.rec_div_is_set)
                        this.rec_div_is_modified = true;
                    this.rec_div_is_set = true;
                    return;
                }
                else if ( name.equals("return_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'return_div' must be String: '"+value+"' is not." );
                    this.return_div = (String) value;
                    if (this.return_div_is_set)
                        this.return_div_is_modified = true;
                    this.return_div_is_set = true;
                    return;
                }
                else if ( name.equals("remark") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'remark' must be String: '"+value+"' is not." );
                    this.remark = (String) value;
                    if (this.remark_is_set)
                        this.remark_is_modified = true;
                    this.remark_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("sort_no") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'sort_no' must be String: '"+value+"' is not." );
                    this.sort_no = (String) value;
                    if (this.sort_no_is_set)
                        this.sort_no_is_modified = true;
                    this.sort_no_is_set = true;
                    return;
                }
                else if ( name.equals("standard_name") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'standard_name' must be String: '"+value+"' is not." );
                    this.standard_name = (String) value;
                    if (this.standard_name_is_set)
                        this.standard_name_is_modified = true;
                    this.standard_name_is_set = true;
                    return;
                }
                break;
            case 't':
                if ( name.equals("trader_code_sonar") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'trader_code_sonar' must be String: '"+value+"' is not." );
                    this.trader_code_sonar = (String) value;
                    if (this.trader_code_sonar_is_set)
                        this.trader_code_sonar_is_modified = true;
                    this.trader_code_sonar_is_set = true;
                    return;
                }
                else if ( name.equals("tax_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'tax_type' must be String: '"+value+"' is not." );
                    this.tax_type = (String) value;
                    if (this.tax_type_is_set)
                        this.tax_type_is_modified = true;
                    this.tax_type_is_set = true;
                    return;
                }
                else if ( name.equals("term_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'term_div' must be String: '"+value+"' is not." );
                    this.term_div = (String) value;
                    if (this.term_div_is_set)
                        this.term_div_is_modified = true;
                    this.term_div_is_set = true;
                    return;
                }
                else if ( name.equals("total_proloss_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'total_proloss_amount' must be Double: '"+value+"' is not." );
                    this.total_proloss_amount = (Double) value;
                    if (this.total_proloss_amount_is_set)
                        this.total_proloss_amount_is_modified = true;
                    this.total_proloss_amount_is_set = true;
                    return;
                }
                else if ( name.equals("taxable_amount") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'taxable_amount' must be Double: '"+value+"' is not." );
                    this.taxable_amount = (Double) value;
                    if (this.taxable_amount_is_set)
                        this.taxable_amount_is_modified = true;
                    this.taxable_amount_is_set = true;
                    return;
                }
                break;
            case 'w':
                if ( name.equals("work_date") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'work_date' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.work_date = (java.sql.Timestamp) value;
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
