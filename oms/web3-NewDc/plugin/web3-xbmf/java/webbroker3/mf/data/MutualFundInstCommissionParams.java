head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFundInstCommissionParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * mutual_fund_inst_commissionテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link MutualFundInstCommissionRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link MutualFundInstCommissionParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link MutualFundInstCommissionParams}が{@@link MutualFundInstCommissionRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundInstCommissionPK 
 * @@see MutualFundInstCommissionRow 
 */
public class MutualFundInstCommissionParams extends Params implements MutualFundInstCommissionRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "mutual_fund_inst_commission";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = MutualFundInstCommissionRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return MutualFundInstCommissionRow.TYPE;
  }


  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>product_code</em>カラムの値 
   */
  public  String  product_code;

  /** 
   * <em>deal_div</em>カラムの値 
   */
  public  String  deal_div;

  /** 
   * <em>order_chanel</em>カラムの値 
   */
  public  String  order_chanel;

  /** 
   * <em>valid_date_from</em>カラムの値 
   */
  public  java.sql.Timestamp  valid_date_from;

  /** 
   * <em>valid_date_to</em>カラムの値 
   */
  public  java.sql.Timestamp  valid_date_to;

  /** 
   * <em>commission_div</em>カラムの値 
   */
  public  String  commission_div;

  /** 
   * <em>unit_count</em>カラムの値 
   */
  public  Integer  unit_count;

  /** 
   * <em>amount_from_01</em>カラムの値 
   */
  public  Long  amount_from_01;

  /** 
   * <em>amount_to_01</em>カラムの値 
   */
  public  Long  amount_to_01;

  /** 
   * <em>commission_price_rate_01</em>カラムの値 
   */
  public  Double  commission_price_rate_01;

  /** 
   * <em>amount_from_02</em>カラムの値 
   */
  public  Long  amount_from_02;

  /** 
   * <em>amount_to_02</em>カラムの値 
   */
  public  Long  amount_to_02;

  /** 
   * <em>commission_price_rate_02</em>カラムの値 
   */
  public  Double  commission_price_rate_02;

  /** 
   * <em>amount_from_03</em>カラムの値 
   */
  public  Long  amount_from_03;

  /** 
   * <em>amount_to_03</em>カラムの値 
   */
  public  Long  amount_to_03;

  /** 
   * <em>commission_price_rate_03</em>カラムの値 
   */
  public  Double  commission_price_rate_03;

  /** 
   * <em>amount_from_04</em>カラムの値 
   */
  public  Long  amount_from_04;

  /** 
   * <em>amount_to_04</em>カラムの値 
   */
  public  Long  amount_to_04;

  /** 
   * <em>commission_price_rate_04</em>カラムの値 
   */
  public  Double  commission_price_rate_04;

  /** 
   * <em>amount_from_05</em>カラムの値 
   */
  public  Long  amount_from_05;

  /** 
   * <em>amount_to_05</em>カラムの値 
   */
  public  Long  amount_to_05;

  /** 
   * <em>commission_price_rate_05</em>カラムの値 
   */
  public  Double  commission_price_rate_05;

  /** 
   * <em>amount_from_06</em>カラムの値 
   */
  public  Long  amount_from_06;

  /** 
   * <em>amount_to_06</em>カラムの値 
   */
  public  Long  amount_to_06;

  /** 
   * <em>commission_price_rate_06</em>カラムの値 
   */
  public  Double  commission_price_rate_06;

  /** 
   * <em>amount_from_07</em>カラムの値 
   */
  public  Long  amount_from_07;

  /** 
   * <em>amount_to_07</em>カラムの値 
   */
  public  Long  amount_to_07;

  /** 
   * <em>commission_price_rate_07</em>カラムの値 
   */
  public  Double  commission_price_rate_07;

  /** 
   * <em>amount_from_08</em>カラムの値 
   */
  public  Long  amount_from_08;

  /** 
   * <em>amount_to_08</em>カラムの値 
   */
  public  Long  amount_to_08;

  /** 
   * <em>commission_price_rate_08</em>カラムの値 
   */
  public  Double  commission_price_rate_08;

  /** 
   * <em>amount_from_09</em>カラムの値 
   */
  public  Long  amount_from_09;

  /** 
   * <em>amount_to_09</em>カラムの値 
   */
  public  Long  amount_to_09;

  /** 
   * <em>commission_price_rate_09</em>カラムの値 
   */
  public  Double  commission_price_rate_09;

  /** 
   * <em>amount_from_10</em>カラムの値 
   */
  public  Long  amount_from_10;

  /** 
   * <em>amount_to_10</em>カラムの値 
   */
  public  Long  amount_to_10;

  /** 
   * <em>commission_price_rate_10</em>カラムの値 
   */
  public  Double  commission_price_rate_10;

  /** 
   * <em>open_date_from</em>カラムの値 
   */
  public  java.sql.Timestamp  open_date_from;

  /** 
   * <em>open_date_to</em>カラムの値 
   */
  public  java.sql.Timestamp  open_date_to;

  /** 
   * <em>collection_rate</em>カラムの値 
   */
  public  Double  collection_rate;

  /** 
   * <em>regist_div</em>カラムの値 
   */
  public  String  regist_div;

  /** 
   * <em>last_updater</em>カラムの値 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean deal_div_is_set = false;
  private boolean deal_div_is_modified = false;
  private boolean order_chanel_is_set = false;
  private boolean order_chanel_is_modified = false;
  private boolean valid_date_from_is_set = false;
  private boolean valid_date_from_is_modified = false;
  private boolean valid_date_to_is_set = false;
  private boolean valid_date_to_is_modified = false;
  private boolean commission_div_is_set = false;
  private boolean commission_div_is_modified = false;
  private boolean unit_count_is_set = false;
  private boolean unit_count_is_modified = false;
  private boolean amount_from_01_is_set = false;
  private boolean amount_from_01_is_modified = false;
  private boolean amount_to_01_is_set = false;
  private boolean amount_to_01_is_modified = false;
  private boolean commission_price_rate_01_is_set = false;
  private boolean commission_price_rate_01_is_modified = false;
  private boolean amount_from_02_is_set = false;
  private boolean amount_from_02_is_modified = false;
  private boolean amount_to_02_is_set = false;
  private boolean amount_to_02_is_modified = false;
  private boolean commission_price_rate_02_is_set = false;
  private boolean commission_price_rate_02_is_modified = false;
  private boolean amount_from_03_is_set = false;
  private boolean amount_from_03_is_modified = false;
  private boolean amount_to_03_is_set = false;
  private boolean amount_to_03_is_modified = false;
  private boolean commission_price_rate_03_is_set = false;
  private boolean commission_price_rate_03_is_modified = false;
  private boolean amount_from_04_is_set = false;
  private boolean amount_from_04_is_modified = false;
  private boolean amount_to_04_is_set = false;
  private boolean amount_to_04_is_modified = false;
  private boolean commission_price_rate_04_is_set = false;
  private boolean commission_price_rate_04_is_modified = false;
  private boolean amount_from_05_is_set = false;
  private boolean amount_from_05_is_modified = false;
  private boolean amount_to_05_is_set = false;
  private boolean amount_to_05_is_modified = false;
  private boolean commission_price_rate_05_is_set = false;
  private boolean commission_price_rate_05_is_modified = false;
  private boolean amount_from_06_is_set = false;
  private boolean amount_from_06_is_modified = false;
  private boolean amount_to_06_is_set = false;
  private boolean amount_to_06_is_modified = false;
  private boolean commission_price_rate_06_is_set = false;
  private boolean commission_price_rate_06_is_modified = false;
  private boolean amount_from_07_is_set = false;
  private boolean amount_from_07_is_modified = false;
  private boolean amount_to_07_is_set = false;
  private boolean amount_to_07_is_modified = false;
  private boolean commission_price_rate_07_is_set = false;
  private boolean commission_price_rate_07_is_modified = false;
  private boolean amount_from_08_is_set = false;
  private boolean amount_from_08_is_modified = false;
  private boolean amount_to_08_is_set = false;
  private boolean amount_to_08_is_modified = false;
  private boolean commission_price_rate_08_is_set = false;
  private boolean commission_price_rate_08_is_modified = false;
  private boolean amount_from_09_is_set = false;
  private boolean amount_from_09_is_modified = false;
  private boolean amount_to_09_is_set = false;
  private boolean amount_to_09_is_modified = false;
  private boolean commission_price_rate_09_is_set = false;
  private boolean commission_price_rate_09_is_modified = false;
  private boolean amount_from_10_is_set = false;
  private boolean amount_from_10_is_modified = false;
  private boolean amount_to_10_is_set = false;
  private boolean amount_to_10_is_modified = false;
  private boolean commission_price_rate_10_is_set = false;
  private boolean commission_price_rate_10_is_modified = false;
  private boolean open_date_from_is_set = false;
  private boolean open_date_from_is_modified = false;
  private boolean open_date_to_is_set = false;
  private boolean open_date_to_is_modified = false;
  private boolean collection_rate_is_set = false;
  private boolean collection_rate_is_modified = false;
  private boolean regist_div_is_set = false;
  private boolean regist_div_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "institution_code=" + institution_code
      + "," + "product_code=" + product_code
      + "," + "deal_div=" + deal_div
      + "," + "order_chanel=" + order_chanel
      + "," + "valid_date_from=" + valid_date_from
      + "," + "valid_date_to=" +valid_date_to
      + "," + "commission_div=" +commission_div
      + "," + "unit_count=" +unit_count
      + "," + "amount_from_01=" +amount_from_01
      + "," + "amount_to_01=" +amount_to_01
      + "," + "commission_price_rate_01=" +commission_price_rate_01
      + "," + "amount_from_02=" +amount_from_02
      + "," + "amount_to_02=" +amount_to_02
      + "," + "commission_price_rate_02=" +commission_price_rate_02
      + "," + "amount_from_03=" +amount_from_03
      + "," + "amount_to_03=" +amount_to_03
      + "," + "commission_price_rate_03=" +commission_price_rate_03
      + "," + "amount_from_04=" +amount_from_04
      + "," + "amount_to_04=" +amount_to_04
      + "," + "commission_price_rate_04=" +commission_price_rate_04
      + "," + "amount_from_05=" +amount_from_05
      + "," + "amount_to_05=" +amount_to_05
      + "," + "commission_price_rate_05=" +commission_price_rate_05
      + "," + "amount_from_06=" +amount_from_06
      + "," + "amount_to_06=" +amount_to_06
      + "," + "commission_price_rate_06=" +commission_price_rate_06
      + "," + "amount_from_07=" +amount_from_07
      + "," + "amount_to_07=" +amount_to_07
      + "," + "commission_price_rate_07=" +commission_price_rate_07
      + "," + "amount_from_08=" +amount_from_08
      + "," + "amount_to_08=" +amount_to_08
      + "," + "commission_price_rate_08=" +commission_price_rate_08
      + "," + "amount_from_09=" +amount_from_09
      + "," + "amount_to_09=" +amount_to_09
      + "," + "commission_price_rate_09=" +commission_price_rate_09
      + "," + "amount_from_10=" +amount_from_10
      + "," + "amount_to_10=" +amount_to_10
      + "," + "commission_price_rate_10=" +commission_price_rate_10
      + "," + "open_date_from=" +open_date_from
      + "," + "open_date_to=" +open_date_to
      + "," + "collection_rate=" +collection_rate
      + "," + "regist_div=" +regist_div
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のMutualFundInstCommissionParamsオブジェクトを作成します。 
   */
  public MutualFundInstCommissionParams() {
    institution_code_is_modified = true;
    product_code_is_modified = true;
    deal_div_is_modified = true;
    order_chanel_is_modified = true;
    valid_date_from_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のMutualFundInstCommissionRowオブジェクトの値を利用してMutualFundInstCommissionParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するMutualFundInstCommissionRowオブジェクト 
   */
  public MutualFundInstCommissionParams( MutualFundInstCommissionRow p_row )
  {
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    deal_div = p_row.getDealDiv();
    deal_div_is_set = p_row.getDealDivIsSet();
    deal_div_is_modified = p_row.getDealDivIsModified();
    order_chanel = p_row.getOrderChanel();
    order_chanel_is_set = p_row.getOrderChanelIsSet();
    order_chanel_is_modified = p_row.getOrderChanelIsModified();
    valid_date_from = p_row.getValidDateFrom();
    valid_date_from_is_set = p_row.getValidDateFromIsSet();
    valid_date_from_is_modified = p_row.getValidDateFromIsModified();
    valid_date_to = p_row.getValidDateTo();
    valid_date_to_is_set = p_row.getValidDateToIsSet();
    valid_date_to_is_modified = p_row.getValidDateToIsModified();
    commission_div = p_row.getCommissionDiv();
    commission_div_is_set = p_row.getCommissionDivIsSet();
    commission_div_is_modified = p_row.getCommissionDivIsModified();
    if ( !p_row.getUnitCountIsNull() )
      unit_count = new Integer( p_row.getUnitCount() );
    unit_count_is_set = p_row.getUnitCountIsSet();
    unit_count_is_modified = p_row.getUnitCountIsModified();
    if ( !p_row.getAmountFrom01IsNull() )
      amount_from_01 = new Long( p_row.getAmountFrom01() );
    amount_from_01_is_set = p_row.getAmountFrom01IsSet();
    amount_from_01_is_modified = p_row.getAmountFrom01IsModified();
    if ( !p_row.getAmountTo01IsNull() )
      amount_to_01 = new Long( p_row.getAmountTo01() );
    amount_to_01_is_set = p_row.getAmountTo01IsSet();
    amount_to_01_is_modified = p_row.getAmountTo01IsModified();
    if ( !p_row.getCommissionPriceRate01IsNull() )
      commission_price_rate_01 = new Double( p_row.getCommissionPriceRate01() );
    commission_price_rate_01_is_set = p_row.getCommissionPriceRate01IsSet();
    commission_price_rate_01_is_modified = p_row.getCommissionPriceRate01IsModified();
    if ( !p_row.getAmountFrom02IsNull() )
      amount_from_02 = new Long( p_row.getAmountFrom02() );
    amount_from_02_is_set = p_row.getAmountFrom02IsSet();
    amount_from_02_is_modified = p_row.getAmountFrom02IsModified();
    if ( !p_row.getAmountTo02IsNull() )
      amount_to_02 = new Long( p_row.getAmountTo02() );
    amount_to_02_is_set = p_row.getAmountTo02IsSet();
    amount_to_02_is_modified = p_row.getAmountTo02IsModified();
    if ( !p_row.getCommissionPriceRate02IsNull() )
      commission_price_rate_02 = new Double( p_row.getCommissionPriceRate02() );
    commission_price_rate_02_is_set = p_row.getCommissionPriceRate02IsSet();
    commission_price_rate_02_is_modified = p_row.getCommissionPriceRate02IsModified();
    if ( !p_row.getAmountFrom03IsNull() )
      amount_from_03 = new Long( p_row.getAmountFrom03() );
    amount_from_03_is_set = p_row.getAmountFrom03IsSet();
    amount_from_03_is_modified = p_row.getAmountFrom03IsModified();
    if ( !p_row.getAmountTo03IsNull() )
      amount_to_03 = new Long( p_row.getAmountTo03() );
    amount_to_03_is_set = p_row.getAmountTo03IsSet();
    amount_to_03_is_modified = p_row.getAmountTo03IsModified();
    if ( !p_row.getCommissionPriceRate03IsNull() )
      commission_price_rate_03 = new Double( p_row.getCommissionPriceRate03() );
    commission_price_rate_03_is_set = p_row.getCommissionPriceRate03IsSet();
    commission_price_rate_03_is_modified = p_row.getCommissionPriceRate03IsModified();
    if ( !p_row.getAmountFrom04IsNull() )
      amount_from_04 = new Long( p_row.getAmountFrom04() );
    amount_from_04_is_set = p_row.getAmountFrom04IsSet();
    amount_from_04_is_modified = p_row.getAmountFrom04IsModified();
    if ( !p_row.getAmountTo04IsNull() )
      amount_to_04 = new Long( p_row.getAmountTo04() );
    amount_to_04_is_set = p_row.getAmountTo04IsSet();
    amount_to_04_is_modified = p_row.getAmountTo04IsModified();
    if ( !p_row.getCommissionPriceRate04IsNull() )
      commission_price_rate_04 = new Double( p_row.getCommissionPriceRate04() );
    commission_price_rate_04_is_set = p_row.getCommissionPriceRate04IsSet();
    commission_price_rate_04_is_modified = p_row.getCommissionPriceRate04IsModified();
    if ( !p_row.getAmountFrom05IsNull() )
      amount_from_05 = new Long( p_row.getAmountFrom05() );
    amount_from_05_is_set = p_row.getAmountFrom05IsSet();
    amount_from_05_is_modified = p_row.getAmountFrom05IsModified();
    if ( !p_row.getAmountTo05IsNull() )
      amount_to_05 = new Long( p_row.getAmountTo05() );
    amount_to_05_is_set = p_row.getAmountTo05IsSet();
    amount_to_05_is_modified = p_row.getAmountTo05IsModified();
    if ( !p_row.getCommissionPriceRate05IsNull() )
      commission_price_rate_05 = new Double( p_row.getCommissionPriceRate05() );
    commission_price_rate_05_is_set = p_row.getCommissionPriceRate05IsSet();
    commission_price_rate_05_is_modified = p_row.getCommissionPriceRate05IsModified();
    if ( !p_row.getAmountFrom06IsNull() )
      amount_from_06 = new Long( p_row.getAmountFrom06() );
    amount_from_06_is_set = p_row.getAmountFrom06IsSet();
    amount_from_06_is_modified = p_row.getAmountFrom06IsModified();
    if ( !p_row.getAmountTo06IsNull() )
      amount_to_06 = new Long( p_row.getAmountTo06() );
    amount_to_06_is_set = p_row.getAmountTo06IsSet();
    amount_to_06_is_modified = p_row.getAmountTo06IsModified();
    if ( !p_row.getCommissionPriceRate06IsNull() )
      commission_price_rate_06 = new Double( p_row.getCommissionPriceRate06() );
    commission_price_rate_06_is_set = p_row.getCommissionPriceRate06IsSet();
    commission_price_rate_06_is_modified = p_row.getCommissionPriceRate06IsModified();
    if ( !p_row.getAmountFrom07IsNull() )
      amount_from_07 = new Long( p_row.getAmountFrom07() );
    amount_from_07_is_set = p_row.getAmountFrom07IsSet();
    amount_from_07_is_modified = p_row.getAmountFrom07IsModified();
    if ( !p_row.getAmountTo07IsNull() )
      amount_to_07 = new Long( p_row.getAmountTo07() );
    amount_to_07_is_set = p_row.getAmountTo07IsSet();
    amount_to_07_is_modified = p_row.getAmountTo07IsModified();
    if ( !p_row.getCommissionPriceRate07IsNull() )
      commission_price_rate_07 = new Double( p_row.getCommissionPriceRate07() );
    commission_price_rate_07_is_set = p_row.getCommissionPriceRate07IsSet();
    commission_price_rate_07_is_modified = p_row.getCommissionPriceRate07IsModified();
    if ( !p_row.getAmountFrom08IsNull() )
      amount_from_08 = new Long( p_row.getAmountFrom08() );
    amount_from_08_is_set = p_row.getAmountFrom08IsSet();
    amount_from_08_is_modified = p_row.getAmountFrom08IsModified();
    if ( !p_row.getAmountTo08IsNull() )
      amount_to_08 = new Long( p_row.getAmountTo08() );
    amount_to_08_is_set = p_row.getAmountTo08IsSet();
    amount_to_08_is_modified = p_row.getAmountTo08IsModified();
    if ( !p_row.getCommissionPriceRate08IsNull() )
      commission_price_rate_08 = new Double( p_row.getCommissionPriceRate08() );
    commission_price_rate_08_is_set = p_row.getCommissionPriceRate08IsSet();
    commission_price_rate_08_is_modified = p_row.getCommissionPriceRate08IsModified();
    if ( !p_row.getAmountFrom09IsNull() )
      amount_from_09 = new Long( p_row.getAmountFrom09() );
    amount_from_09_is_set = p_row.getAmountFrom09IsSet();
    amount_from_09_is_modified = p_row.getAmountFrom09IsModified();
    if ( !p_row.getAmountTo09IsNull() )
      amount_to_09 = new Long( p_row.getAmountTo09() );
    amount_to_09_is_set = p_row.getAmountTo09IsSet();
    amount_to_09_is_modified = p_row.getAmountTo09IsModified();
    if ( !p_row.getCommissionPriceRate09IsNull() )
      commission_price_rate_09 = new Double( p_row.getCommissionPriceRate09() );
    commission_price_rate_09_is_set = p_row.getCommissionPriceRate09IsSet();
    commission_price_rate_09_is_modified = p_row.getCommissionPriceRate09IsModified();
    if ( !p_row.getAmountFrom10IsNull() )
      amount_from_10 = new Long( p_row.getAmountFrom10() );
    amount_from_10_is_set = p_row.getAmountFrom10IsSet();
    amount_from_10_is_modified = p_row.getAmountFrom10IsModified();
    if ( !p_row.getAmountTo10IsNull() )
      amount_to_10 = new Long( p_row.getAmountTo10() );
    amount_to_10_is_set = p_row.getAmountTo10IsSet();
    amount_to_10_is_modified = p_row.getAmountTo10IsModified();
    if ( !p_row.getCommissionPriceRate10IsNull() )
      commission_price_rate_10 = new Double( p_row.getCommissionPriceRate10() );
    commission_price_rate_10_is_set = p_row.getCommissionPriceRate10IsSet();
    commission_price_rate_10_is_modified = p_row.getCommissionPriceRate10IsModified();
    open_date_from = p_row.getOpenDateFrom();
    open_date_from_is_set = p_row.getOpenDateFromIsSet();
    open_date_from_is_modified = p_row.getOpenDateFromIsModified();
    open_date_to = p_row.getOpenDateTo();
    open_date_to_is_set = p_row.getOpenDateToIsSet();
    open_date_to_is_modified = p_row.getOpenDateToIsModified();
    if ( !p_row.getCollectionRateIsNull() )
      collection_rate = new Double( p_row.getCollectionRate() );
    collection_rate_is_set = p_row.getCollectionRateIsSet();
    collection_rate_is_modified = p_row.getCollectionRateIsModified();
    regist_div = p_row.getRegistDiv();
    regist_div_is_set = p_row.getRegistDivIsSet();
    regist_div_is_modified = p_row.getRegistDivIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
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
      valid_date_to_is_set = true;
      valid_date_to_is_modified = true;
      commission_div_is_set = true;
      commission_div_is_modified = true;
      unit_count_is_set = true;
      unit_count_is_modified = true;
      amount_from_01_is_set = true;
      amount_from_01_is_modified = true;
      amount_to_01_is_set = true;
      amount_to_01_is_modified = true;
      commission_price_rate_01_is_set = true;
      commission_price_rate_01_is_modified = true;
      amount_from_02_is_set = true;
      amount_from_02_is_modified = true;
      amount_to_02_is_set = true;
      amount_to_02_is_modified = true;
      commission_price_rate_02_is_set = true;
      commission_price_rate_02_is_modified = true;
      amount_from_03_is_set = true;
      amount_from_03_is_modified = true;
      amount_to_03_is_set = true;
      amount_to_03_is_modified = true;
      commission_price_rate_03_is_set = true;
      commission_price_rate_03_is_modified = true;
      amount_from_04_is_set = true;
      amount_from_04_is_modified = true;
      amount_to_04_is_set = true;
      amount_to_04_is_modified = true;
      commission_price_rate_04_is_set = true;
      commission_price_rate_04_is_modified = true;
      amount_from_05_is_set = true;
      amount_from_05_is_modified = true;
      amount_to_05_is_set = true;
      amount_to_05_is_modified = true;
      commission_price_rate_05_is_set = true;
      commission_price_rate_05_is_modified = true;
      amount_from_06_is_set = true;
      amount_from_06_is_modified = true;
      amount_to_06_is_set = true;
      amount_to_06_is_modified = true;
      commission_price_rate_06_is_set = true;
      commission_price_rate_06_is_modified = true;
      amount_from_07_is_set = true;
      amount_from_07_is_modified = true;
      amount_to_07_is_set = true;
      amount_to_07_is_modified = true;
      commission_price_rate_07_is_set = true;
      commission_price_rate_07_is_modified = true;
      amount_from_08_is_set = true;
      amount_from_08_is_modified = true;
      amount_to_08_is_set = true;
      amount_to_08_is_modified = true;
      commission_price_rate_08_is_set = true;
      commission_price_rate_08_is_modified = true;
      amount_from_09_is_set = true;
      amount_from_09_is_modified = true;
      amount_to_09_is_set = true;
      amount_to_09_is_modified = true;
      commission_price_rate_09_is_set = true;
      commission_price_rate_09_is_modified = true;
      amount_from_10_is_set = true;
      amount_from_10_is_modified = true;
      amount_to_10_is_set = true;
      amount_to_10_is_modified = true;
      commission_price_rate_10_is_set = true;
      commission_price_rate_10_is_modified = true;
      open_date_from_is_set = true;
      open_date_from_is_modified = true;
      open_date_to_is_set = true;
      open_date_to_is_modified = true;
      collection_rate_is_set = true;
      collection_rate_is_modified = true;
      regist_div_is_set = true;
      regist_div_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
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
    if ( !( other instanceof MutualFundInstCommissionRow ) )
       return false;
    return fieldsEqual( (MutualFundInstCommissionRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のMutualFundInstCommissionRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( MutualFundInstCommissionRow row )
  {
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( deal_div == null ) {
      if ( row.getDealDiv() != null )
        return false;
    } else if ( !deal_div.equals( row.getDealDiv() ) ) {
        return false;
    }
    if ( order_chanel == null ) {
      if ( row.getOrderChanel() != null )
        return false;
    } else if ( !order_chanel.equals( row.getOrderChanel() ) ) {
        return false;
    }
    if ( valid_date_from == null ) {
      if ( row.getValidDateFrom() != null )
        return false;
    } else if ( !valid_date_from.equals( row.getValidDateFrom() ) ) {
        return false;
    }
    if ( valid_date_to == null ) {
      if ( row.getValidDateTo() != null )
        return false;
    } else if ( !valid_date_to.equals( row.getValidDateTo() ) ) {
        return false;
    }
    if ( commission_div == null ) {
      if ( row.getCommissionDiv() != null )
        return false;
    } else if ( !commission_div.equals( row.getCommissionDiv() ) ) {
        return false;
    }
    if ( unit_count == null ) {
      if ( !row.getUnitCountIsNull() )
        return false;
    } else if ( row.getUnitCountIsNull() || ( unit_count.intValue() != row.getUnitCount() ) ) {
        return false;
    }
    if ( amount_from_01 == null ) {
      if ( !row.getAmountFrom01IsNull() )
        return false;
    } else if ( row.getAmountFrom01IsNull() || ( amount_from_01.longValue() != row.getAmountFrom01() ) ) {
        return false;
    }
    if ( amount_to_01 == null ) {
      if ( !row.getAmountTo01IsNull() )
        return false;
    } else if ( row.getAmountTo01IsNull() || ( amount_to_01.longValue() != row.getAmountTo01() ) ) {
        return false;
    }
    if ( commission_price_rate_01 == null ) {
      if ( !row.getCommissionPriceRate01IsNull() )
        return false;
    } else if ( row.getCommissionPriceRate01IsNull() || ( commission_price_rate_01.doubleValue() != row.getCommissionPriceRate01() ) ) {
        return false;
    }
    if ( amount_from_02 == null ) {
      if ( !row.getAmountFrom02IsNull() )
        return false;
    } else if ( row.getAmountFrom02IsNull() || ( amount_from_02.longValue() != row.getAmountFrom02() ) ) {
        return false;
    }
    if ( amount_to_02 == null ) {
      if ( !row.getAmountTo02IsNull() )
        return false;
    } else if ( row.getAmountTo02IsNull() || ( amount_to_02.longValue() != row.getAmountTo02() ) ) {
        return false;
    }
    if ( commission_price_rate_02 == null ) {
      if ( !row.getCommissionPriceRate02IsNull() )
        return false;
    } else if ( row.getCommissionPriceRate02IsNull() || ( commission_price_rate_02.doubleValue() != row.getCommissionPriceRate02() ) ) {
        return false;
    }
    if ( amount_from_03 == null ) {
      if ( !row.getAmountFrom03IsNull() )
        return false;
    } else if ( row.getAmountFrom03IsNull() || ( amount_from_03.longValue() != row.getAmountFrom03() ) ) {
        return false;
    }
    if ( amount_to_03 == null ) {
      if ( !row.getAmountTo03IsNull() )
        return false;
    } else if ( row.getAmountTo03IsNull() || ( amount_to_03.longValue() != row.getAmountTo03() ) ) {
        return false;
    }
    if ( commission_price_rate_03 == null ) {
      if ( !row.getCommissionPriceRate03IsNull() )
        return false;
    } else if ( row.getCommissionPriceRate03IsNull() || ( commission_price_rate_03.doubleValue() != row.getCommissionPriceRate03() ) ) {
        return false;
    }
    if ( amount_from_04 == null ) {
      if ( !row.getAmountFrom04IsNull() )
        return false;
    } else if ( row.getAmountFrom04IsNull() || ( amount_from_04.longValue() != row.getAmountFrom04() ) ) {
        return false;
    }
    if ( amount_to_04 == null ) {
      if ( !row.getAmountTo04IsNull() )
        return false;
    } else if ( row.getAmountTo04IsNull() || ( amount_to_04.longValue() != row.getAmountTo04() ) ) {
        return false;
    }
    if ( commission_price_rate_04 == null ) {
      if ( !row.getCommissionPriceRate04IsNull() )
        return false;
    } else if ( row.getCommissionPriceRate04IsNull() || ( commission_price_rate_04.doubleValue() != row.getCommissionPriceRate04() ) ) {
        return false;
    }
    if ( amount_from_05 == null ) {
      if ( !row.getAmountFrom05IsNull() )
        return false;
    } else if ( row.getAmountFrom05IsNull() || ( amount_from_05.longValue() != row.getAmountFrom05() ) ) {
        return false;
    }
    if ( amount_to_05 == null ) {
      if ( !row.getAmountTo05IsNull() )
        return false;
    } else if ( row.getAmountTo05IsNull() || ( amount_to_05.longValue() != row.getAmountTo05() ) ) {
        return false;
    }
    if ( commission_price_rate_05 == null ) {
      if ( !row.getCommissionPriceRate05IsNull() )
        return false;
    } else if ( row.getCommissionPriceRate05IsNull() || ( commission_price_rate_05.doubleValue() != row.getCommissionPriceRate05() ) ) {
        return false;
    }
    if ( amount_from_06 == null ) {
      if ( !row.getAmountFrom06IsNull() )
        return false;
    } else if ( row.getAmountFrom06IsNull() || ( amount_from_06.longValue() != row.getAmountFrom06() ) ) {
        return false;
    }
    if ( amount_to_06 == null ) {
      if ( !row.getAmountTo06IsNull() )
        return false;
    } else if ( row.getAmountTo06IsNull() || ( amount_to_06.longValue() != row.getAmountTo06() ) ) {
        return false;
    }
    if ( commission_price_rate_06 == null ) {
      if ( !row.getCommissionPriceRate06IsNull() )
        return false;
    } else if ( row.getCommissionPriceRate06IsNull() || ( commission_price_rate_06.doubleValue() != row.getCommissionPriceRate06() ) ) {
        return false;
    }
    if ( amount_from_07 == null ) {
      if ( !row.getAmountFrom07IsNull() )
        return false;
    } else if ( row.getAmountFrom07IsNull() || ( amount_from_07.longValue() != row.getAmountFrom07() ) ) {
        return false;
    }
    if ( amount_to_07 == null ) {
      if ( !row.getAmountTo07IsNull() )
        return false;
    } else if ( row.getAmountTo07IsNull() || ( amount_to_07.longValue() != row.getAmountTo07() ) ) {
        return false;
    }
    if ( commission_price_rate_07 == null ) {
      if ( !row.getCommissionPriceRate07IsNull() )
        return false;
    } else if ( row.getCommissionPriceRate07IsNull() || ( commission_price_rate_07.doubleValue() != row.getCommissionPriceRate07() ) ) {
        return false;
    }
    if ( amount_from_08 == null ) {
      if ( !row.getAmountFrom08IsNull() )
        return false;
    } else if ( row.getAmountFrom08IsNull() || ( amount_from_08.longValue() != row.getAmountFrom08() ) ) {
        return false;
    }
    if ( amount_to_08 == null ) {
      if ( !row.getAmountTo08IsNull() )
        return false;
    } else if ( row.getAmountTo08IsNull() || ( amount_to_08.longValue() != row.getAmountTo08() ) ) {
        return false;
    }
    if ( commission_price_rate_08 == null ) {
      if ( !row.getCommissionPriceRate08IsNull() )
        return false;
    } else if ( row.getCommissionPriceRate08IsNull() || ( commission_price_rate_08.doubleValue() != row.getCommissionPriceRate08() ) ) {
        return false;
    }
    if ( amount_from_09 == null ) {
      if ( !row.getAmountFrom09IsNull() )
        return false;
    } else if ( row.getAmountFrom09IsNull() || ( amount_from_09.longValue() != row.getAmountFrom09() ) ) {
        return false;
    }
    if ( amount_to_09 == null ) {
      if ( !row.getAmountTo09IsNull() )
        return false;
    } else if ( row.getAmountTo09IsNull() || ( amount_to_09.longValue() != row.getAmountTo09() ) ) {
        return false;
    }
    if ( commission_price_rate_09 == null ) {
      if ( !row.getCommissionPriceRate09IsNull() )
        return false;
    } else if ( row.getCommissionPriceRate09IsNull() || ( commission_price_rate_09.doubleValue() != row.getCommissionPriceRate09() ) ) {
        return false;
    }
    if ( amount_from_10 == null ) {
      if ( !row.getAmountFrom10IsNull() )
        return false;
    } else if ( row.getAmountFrom10IsNull() || ( amount_from_10.longValue() != row.getAmountFrom10() ) ) {
        return false;
    }
    if ( amount_to_10 == null ) {
      if ( !row.getAmountTo10IsNull() )
        return false;
    } else if ( row.getAmountTo10IsNull() || ( amount_to_10.longValue() != row.getAmountTo10() ) ) {
        return false;
    }
    if ( commission_price_rate_10 == null ) {
      if ( !row.getCommissionPriceRate10IsNull() )
        return false;
    } else if ( row.getCommissionPriceRate10IsNull() || ( commission_price_rate_10.doubleValue() != row.getCommissionPriceRate10() ) ) {
        return false;
    }
    if ( open_date_from == null ) {
      if ( row.getOpenDateFrom() != null )
        return false;
    } else if ( !open_date_from.equals( row.getOpenDateFrom() ) ) {
        return false;
    }
    if ( open_date_to == null ) {
      if ( row.getOpenDateTo() != null )
        return false;
    } else if ( !open_date_to.equals( row.getOpenDateTo() ) ) {
        return false;
    }
    if ( collection_rate == null ) {
      if ( !row.getCollectionRateIsNull() )
        return false;
    } else if ( row.getCollectionRateIsNull() || ( collection_rate.doubleValue() != row.getCollectionRate() ) ) {
        return false;
    }
    if ( regist_div == null ) {
      if ( row.getRegistDiv() != null )
        return false;
    } else if ( !regist_div.equals( row.getRegistDiv() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
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
      return  (institution_code!=null? institution_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (deal_div!=null? deal_div.hashCode(): 0) 
        + (order_chanel!=null? order_chanel.hashCode(): 0) 
        + (valid_date_from!=null? valid_date_from.hashCode(): 0) 
        + (valid_date_to!=null? valid_date_to.hashCode(): 0) 
        + (commission_div!=null? commission_div.hashCode(): 0) 
        + (unit_count!=null? unit_count.hashCode(): 0) 
        + (amount_from_01!=null? amount_from_01.hashCode(): 0) 
        + (amount_to_01!=null? amount_to_01.hashCode(): 0) 
        + (commission_price_rate_01!=null? commission_price_rate_01.hashCode(): 0) 
        + (amount_from_02!=null? amount_from_02.hashCode(): 0) 
        + (amount_to_02!=null? amount_to_02.hashCode(): 0) 
        + (commission_price_rate_02!=null? commission_price_rate_02.hashCode(): 0) 
        + (amount_from_03!=null? amount_from_03.hashCode(): 0) 
        + (amount_to_03!=null? amount_to_03.hashCode(): 0) 
        + (commission_price_rate_03!=null? commission_price_rate_03.hashCode(): 0) 
        + (amount_from_04!=null? amount_from_04.hashCode(): 0) 
        + (amount_to_04!=null? amount_to_04.hashCode(): 0) 
        + (commission_price_rate_04!=null? commission_price_rate_04.hashCode(): 0) 
        + (amount_from_05!=null? amount_from_05.hashCode(): 0) 
        + (amount_to_05!=null? amount_to_05.hashCode(): 0) 
        + (commission_price_rate_05!=null? commission_price_rate_05.hashCode(): 0) 
        + (amount_from_06!=null? amount_from_06.hashCode(): 0) 
        + (amount_to_06!=null? amount_to_06.hashCode(): 0) 
        + (commission_price_rate_06!=null? commission_price_rate_06.hashCode(): 0) 
        + (amount_from_07!=null? amount_from_07.hashCode(): 0) 
        + (amount_to_07!=null? amount_to_07.hashCode(): 0) 
        + (commission_price_rate_07!=null? commission_price_rate_07.hashCode(): 0) 
        + (amount_from_08!=null? amount_from_08.hashCode(): 0) 
        + (amount_to_08!=null? amount_to_08.hashCode(): 0) 
        + (commission_price_rate_08!=null? commission_price_rate_08.hashCode(): 0) 
        + (amount_from_09!=null? amount_from_09.hashCode(): 0) 
        + (amount_to_09!=null? amount_to_09.hashCode(): 0) 
        + (commission_price_rate_09!=null? commission_price_rate_09.hashCode(): 0) 
        + (amount_from_10!=null? amount_from_10.hashCode(): 0) 
        + (amount_to_10!=null? amount_to_10.hashCode(): 0) 
        + (commission_price_rate_10!=null? commission_price_rate_10.hashCode(): 0) 
        + (open_date_from!=null? open_date_from.hashCode(): 0) 
        + (open_date_to!=null? open_date_to.hashCode(): 0) 
        + (collection_rate!=null? collection_rate.hashCode(): 0) 
        + (regist_div!=null? regist_div.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !regist_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'regist_div' must be set before inserting.");
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
		map.put("institution_code",institution_code);
		map.put("product_code",product_code);
		map.put("deal_div",deal_div);
		map.put("order_chanel",order_chanel);
		map.put("valid_date_from",valid_date_from);
		if ( valid_date_to != null )
			map.put("valid_date_to",valid_date_to);
		if ( commission_div != null )
			map.put("commission_div",commission_div);
		if ( unit_count != null )
			map.put("unit_count",unit_count);
		if ( amount_from_01 != null )
			map.put("amount_from_01",amount_from_01);
		if ( amount_to_01 != null )
			map.put("amount_to_01",amount_to_01);
		if ( commission_price_rate_01 != null )
			map.put("commission_price_rate_01",commission_price_rate_01);
		if ( amount_from_02 != null )
			map.put("amount_from_02",amount_from_02);
		if ( amount_to_02 != null )
			map.put("amount_to_02",amount_to_02);
		if ( commission_price_rate_02 != null )
			map.put("commission_price_rate_02",commission_price_rate_02);
		if ( amount_from_03 != null )
			map.put("amount_from_03",amount_from_03);
		if ( amount_to_03 != null )
			map.put("amount_to_03",amount_to_03);
		if ( commission_price_rate_03 != null )
			map.put("commission_price_rate_03",commission_price_rate_03);
		if ( amount_from_04 != null )
			map.put("amount_from_04",amount_from_04);
		if ( amount_to_04 != null )
			map.put("amount_to_04",amount_to_04);
		if ( commission_price_rate_04 != null )
			map.put("commission_price_rate_04",commission_price_rate_04);
		if ( amount_from_05 != null )
			map.put("amount_from_05",amount_from_05);
		if ( amount_to_05 != null )
			map.put("amount_to_05",amount_to_05);
		if ( commission_price_rate_05 != null )
			map.put("commission_price_rate_05",commission_price_rate_05);
		if ( amount_from_06 != null )
			map.put("amount_from_06",amount_from_06);
		if ( amount_to_06 != null )
			map.put("amount_to_06",amount_to_06);
		if ( commission_price_rate_06 != null )
			map.put("commission_price_rate_06",commission_price_rate_06);
		if ( amount_from_07 != null )
			map.put("amount_from_07",amount_from_07);
		if ( amount_to_07 != null )
			map.put("amount_to_07",amount_to_07);
		if ( commission_price_rate_07 != null )
			map.put("commission_price_rate_07",commission_price_rate_07);
		if ( amount_from_08 != null )
			map.put("amount_from_08",amount_from_08);
		if ( amount_to_08 != null )
			map.put("amount_to_08",amount_to_08);
		if ( commission_price_rate_08 != null )
			map.put("commission_price_rate_08",commission_price_rate_08);
		if ( amount_from_09 != null )
			map.put("amount_from_09",amount_from_09);
		if ( amount_to_09 != null )
			map.put("amount_to_09",amount_to_09);
		if ( commission_price_rate_09 != null )
			map.put("commission_price_rate_09",commission_price_rate_09);
		if ( amount_from_10 != null )
			map.put("amount_from_10",amount_from_10);
		if ( amount_to_10 != null )
			map.put("amount_to_10",amount_to_10);
		if ( commission_price_rate_10 != null )
			map.put("commission_price_rate_10",commission_price_rate_10);
		if ( open_date_from != null )
			map.put("open_date_from",open_date_from);
		if ( open_date_to != null )
			map.put("open_date_to",open_date_to);
		if ( collection_rate != null )
			map.put("collection_rate",collection_rate);
		map.put("regist_div",regist_div);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		map.put("created_timestamp",created_timestamp);
		map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( valid_date_to_is_modified )
			map.put("valid_date_to",valid_date_to);
		if ( commission_div_is_modified )
			map.put("commission_div",commission_div);
		if ( unit_count_is_modified )
			map.put("unit_count",unit_count);
		if ( amount_from_01_is_modified )
			map.put("amount_from_01",amount_from_01);
		if ( amount_to_01_is_modified )
			map.put("amount_to_01",amount_to_01);
		if ( commission_price_rate_01_is_modified )
			map.put("commission_price_rate_01",commission_price_rate_01);
		if ( amount_from_02_is_modified )
			map.put("amount_from_02",amount_from_02);
		if ( amount_to_02_is_modified )
			map.put("amount_to_02",amount_to_02);
		if ( commission_price_rate_02_is_modified )
			map.put("commission_price_rate_02",commission_price_rate_02);
		if ( amount_from_03_is_modified )
			map.put("amount_from_03",amount_from_03);
		if ( amount_to_03_is_modified )
			map.put("amount_to_03",amount_to_03);
		if ( commission_price_rate_03_is_modified )
			map.put("commission_price_rate_03",commission_price_rate_03);
		if ( amount_from_04_is_modified )
			map.put("amount_from_04",amount_from_04);
		if ( amount_to_04_is_modified )
			map.put("amount_to_04",amount_to_04);
		if ( commission_price_rate_04_is_modified )
			map.put("commission_price_rate_04",commission_price_rate_04);
		if ( amount_from_05_is_modified )
			map.put("amount_from_05",amount_from_05);
		if ( amount_to_05_is_modified )
			map.put("amount_to_05",amount_to_05);
		if ( commission_price_rate_05_is_modified )
			map.put("commission_price_rate_05",commission_price_rate_05);
		if ( amount_from_06_is_modified )
			map.put("amount_from_06",amount_from_06);
		if ( amount_to_06_is_modified )
			map.put("amount_to_06",amount_to_06);
		if ( commission_price_rate_06_is_modified )
			map.put("commission_price_rate_06",commission_price_rate_06);
		if ( amount_from_07_is_modified )
			map.put("amount_from_07",amount_from_07);
		if ( amount_to_07_is_modified )
			map.put("amount_to_07",amount_to_07);
		if ( commission_price_rate_07_is_modified )
			map.put("commission_price_rate_07",commission_price_rate_07);
		if ( amount_from_08_is_modified )
			map.put("amount_from_08",amount_from_08);
		if ( amount_to_08_is_modified )
			map.put("amount_to_08",amount_to_08);
		if ( commission_price_rate_08_is_modified )
			map.put("commission_price_rate_08",commission_price_rate_08);
		if ( amount_from_09_is_modified )
			map.put("amount_from_09",amount_from_09);
		if ( amount_to_09_is_modified )
			map.put("amount_to_09",amount_to_09);
		if ( commission_price_rate_09_is_modified )
			map.put("commission_price_rate_09",commission_price_rate_09);
		if ( amount_from_10_is_modified )
			map.put("amount_from_10",amount_from_10);
		if ( amount_to_10_is_modified )
			map.put("amount_to_10",amount_to_10);
		if ( commission_price_rate_10_is_modified )
			map.put("commission_price_rate_10",commission_price_rate_10);
		if ( open_date_from_is_modified )
			map.put("open_date_from",open_date_from);
		if ( open_date_to_is_modified )
			map.put("open_date_to",open_date_to);
		if ( collection_rate_is_modified )
			map.put("collection_rate",collection_rate);
		if ( regist_div_is_modified )
			map.put("regist_div",regist_div);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			map.put("valid_date_to",valid_date_to);
			map.put("commission_div",commission_div);
			map.put("unit_count",unit_count);
			map.put("amount_from_01",amount_from_01);
			map.put("amount_to_01",amount_to_01);
			map.put("commission_price_rate_01",commission_price_rate_01);
			map.put("amount_from_02",amount_from_02);
			map.put("amount_to_02",amount_to_02);
			map.put("commission_price_rate_02",commission_price_rate_02);
			map.put("amount_from_03",amount_from_03);
			map.put("amount_to_03",amount_to_03);
			map.put("commission_price_rate_03",commission_price_rate_03);
			map.put("amount_from_04",amount_from_04);
			map.put("amount_to_04",amount_to_04);
			map.put("commission_price_rate_04",commission_price_rate_04);
			map.put("amount_from_05",amount_from_05);
			map.put("amount_to_05",amount_to_05);
			map.put("commission_price_rate_05",commission_price_rate_05);
			map.put("amount_from_06",amount_from_06);
			map.put("amount_to_06",amount_to_06);
			map.put("commission_price_rate_06",commission_price_rate_06);
			map.put("amount_from_07",amount_from_07);
			map.put("amount_to_07",amount_to_07);
			map.put("commission_price_rate_07",commission_price_rate_07);
			map.put("amount_from_08",amount_from_08);
			map.put("amount_to_08",amount_to_08);
			map.put("commission_price_rate_08",commission_price_rate_08);
			map.put("amount_from_09",amount_from_09);
			map.put("amount_to_09",amount_to_09);
			map.put("commission_price_rate_09",commission_price_rate_09);
			map.put("amount_from_10",amount_from_10);
			map.put("amount_to_10",amount_to_10);
			map.put("commission_price_rate_10",commission_price_rate_10);
			map.put("open_date_from",open_date_from);
			map.put("open_date_to",open_date_to);
			map.put("collection_rate",collection_rate);
			if ( regist_div_is_set )
				map.put("regist_div",regist_div);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
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
   * <em>deal_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getDealDiv()
  {
    return deal_div;
  }


  /** 
   * <em>deal_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDealDivIsSet() {
    return deal_div_is_set;
  }


  /** 
   * <em>deal_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getDealDivIsModified() {
    return deal_div_is_modified;
  }


  /** 
   * <em>order_chanel</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOrderChanel()
  {
    return order_chanel;
  }


  /** 
   * <em>order_chanel</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderChanelIsSet() {
    return order_chanel_is_set;
  }


  /** 
   * <em>order_chanel</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrderChanelIsModified() {
    return order_chanel_is_modified;
  }


  /** 
   * <em>valid_date_from</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getValidDateFrom()
  {
    return valid_date_from;
  }


  /** 
   * <em>valid_date_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidDateFromIsSet() {
    return valid_date_from_is_set;
  }


  /** 
   * <em>valid_date_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidDateFromIsModified() {
    return valid_date_from_is_modified;
  }


  /** 
   * <em>valid_date_to</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getValidDateTo()
  {
    return valid_date_to;
  }


  /** 
   * <em>valid_date_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidDateToIsSet() {
    return valid_date_to_is_set;
  }


  /** 
   * <em>valid_date_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidDateToIsModified() {
    return valid_date_to_is_modified;
  }


  /** 
   * <em>commission_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getCommissionDiv()
  {
    return commission_div;
  }


  /** 
   * <em>commission_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionDivIsSet() {
    return commission_div_is_set;
  }


  /** 
   * <em>commission_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionDivIsModified() {
    return commission_div_is_modified;
  }


  /** 
   * <em>unit_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public final int getUnitCount()
  {
    return ( unit_count==null? ((int)0): unit_count.intValue() );
  }


  /** 
   * <em>unit_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getUnitCountIsNull()
  {
    return unit_count == null;
  }


  /** 
   * <em>unit_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnitCountIsSet() {
    return unit_count_is_set;
  }


  /** 
   * <em>unit_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getUnitCountIsModified() {
    return unit_count_is_modified;
  }


  /** 
   * <em>amount_from_01</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountFrom01()
  {
    return ( amount_from_01==null? ((long)0): amount_from_01.longValue() );
  }


  /** 
   * <em>amount_from_01</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountFrom01IsNull()
  {
    return amount_from_01 == null;
  }


  /** 
   * <em>amount_from_01</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom01IsSet() {
    return amount_from_01_is_set;
  }


  /** 
   * <em>amount_from_01</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom01IsModified() {
    return amount_from_01_is_modified;
  }


  /** 
   * <em>amount_to_01</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountTo01()
  {
    return ( amount_to_01==null? ((long)0): amount_to_01.longValue() );
  }


  /** 
   * <em>amount_to_01</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountTo01IsNull()
  {
    return amount_to_01 == null;
  }


  /** 
   * <em>amount_to_01</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo01IsSet() {
    return amount_to_01_is_set;
  }


  /** 
   * <em>amount_to_01</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo01IsModified() {
    return amount_to_01_is_modified;
  }


  /** 
   * <em>commission_price_rate_01</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionPriceRate01()
  {
    return ( commission_price_rate_01==null? ((double)0): commission_price_rate_01.doubleValue() );
  }


  /** 
   * <em>commission_price_rate_01</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCommissionPriceRate01IsNull()
  {
    return commission_price_rate_01 == null;
  }


  /** 
   * <em>commission_price_rate_01</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate01IsSet() {
    return commission_price_rate_01_is_set;
  }


  /** 
   * <em>commission_price_rate_01</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate01IsModified() {
    return commission_price_rate_01_is_modified;
  }


  /** 
   * <em>amount_from_02</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountFrom02()
  {
    return ( amount_from_02==null? ((long)0): amount_from_02.longValue() );
  }


  /** 
   * <em>amount_from_02</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountFrom02IsNull()
  {
    return amount_from_02 == null;
  }


  /** 
   * <em>amount_from_02</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom02IsSet() {
    return amount_from_02_is_set;
  }


  /** 
   * <em>amount_from_02</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom02IsModified() {
    return amount_from_02_is_modified;
  }


  /** 
   * <em>amount_to_02</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountTo02()
  {
    return ( amount_to_02==null? ((long)0): amount_to_02.longValue() );
  }


  /** 
   * <em>amount_to_02</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountTo02IsNull()
  {
    return amount_to_02 == null;
  }


  /** 
   * <em>amount_to_02</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo02IsSet() {
    return amount_to_02_is_set;
  }


  /** 
   * <em>amount_to_02</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo02IsModified() {
    return amount_to_02_is_modified;
  }


  /** 
   * <em>commission_price_rate_02</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionPriceRate02()
  {
    return ( commission_price_rate_02==null? ((double)0): commission_price_rate_02.doubleValue() );
  }


  /** 
   * <em>commission_price_rate_02</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCommissionPriceRate02IsNull()
  {
    return commission_price_rate_02 == null;
  }


  /** 
   * <em>commission_price_rate_02</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate02IsSet() {
    return commission_price_rate_02_is_set;
  }


  /** 
   * <em>commission_price_rate_02</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate02IsModified() {
    return commission_price_rate_02_is_modified;
  }


  /** 
   * <em>amount_from_03</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountFrom03()
  {
    return ( amount_from_03==null? ((long)0): amount_from_03.longValue() );
  }


  /** 
   * <em>amount_from_03</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountFrom03IsNull()
  {
    return amount_from_03 == null;
  }


  /** 
   * <em>amount_from_03</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom03IsSet() {
    return amount_from_03_is_set;
  }


  /** 
   * <em>amount_from_03</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom03IsModified() {
    return amount_from_03_is_modified;
  }


  /** 
   * <em>amount_to_03</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountTo03()
  {
    return ( amount_to_03==null? ((long)0): amount_to_03.longValue() );
  }


  /** 
   * <em>amount_to_03</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountTo03IsNull()
  {
    return amount_to_03 == null;
  }


  /** 
   * <em>amount_to_03</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo03IsSet() {
    return amount_to_03_is_set;
  }


  /** 
   * <em>amount_to_03</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo03IsModified() {
    return amount_to_03_is_modified;
  }


  /** 
   * <em>commission_price_rate_03</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionPriceRate03()
  {
    return ( commission_price_rate_03==null? ((double)0): commission_price_rate_03.doubleValue() );
  }


  /** 
   * <em>commission_price_rate_03</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCommissionPriceRate03IsNull()
  {
    return commission_price_rate_03 == null;
  }


  /** 
   * <em>commission_price_rate_03</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate03IsSet() {
    return commission_price_rate_03_is_set;
  }


  /** 
   * <em>commission_price_rate_03</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate03IsModified() {
    return commission_price_rate_03_is_modified;
  }


  /** 
   * <em>amount_from_04</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountFrom04()
  {
    return ( amount_from_04==null? ((long)0): amount_from_04.longValue() );
  }


  /** 
   * <em>amount_from_04</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountFrom04IsNull()
  {
    return amount_from_04 == null;
  }


  /** 
   * <em>amount_from_04</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom04IsSet() {
    return amount_from_04_is_set;
  }


  /** 
   * <em>amount_from_04</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom04IsModified() {
    return amount_from_04_is_modified;
  }


  /** 
   * <em>amount_to_04</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountTo04()
  {
    return ( amount_to_04==null? ((long)0): amount_to_04.longValue() );
  }


  /** 
   * <em>amount_to_04</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountTo04IsNull()
  {
    return amount_to_04 == null;
  }


  /** 
   * <em>amount_to_04</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo04IsSet() {
    return amount_to_04_is_set;
  }


  /** 
   * <em>amount_to_04</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo04IsModified() {
    return amount_to_04_is_modified;
  }


  /** 
   * <em>commission_price_rate_04</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionPriceRate04()
  {
    return ( commission_price_rate_04==null? ((double)0): commission_price_rate_04.doubleValue() );
  }


  /** 
   * <em>commission_price_rate_04</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCommissionPriceRate04IsNull()
  {
    return commission_price_rate_04 == null;
  }


  /** 
   * <em>commission_price_rate_04</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate04IsSet() {
    return commission_price_rate_04_is_set;
  }


  /** 
   * <em>commission_price_rate_04</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate04IsModified() {
    return commission_price_rate_04_is_modified;
  }


  /** 
   * <em>amount_from_05</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountFrom05()
  {
    return ( amount_from_05==null? ((long)0): amount_from_05.longValue() );
  }


  /** 
   * <em>amount_from_05</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountFrom05IsNull()
  {
    return amount_from_05 == null;
  }


  /** 
   * <em>amount_from_05</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom05IsSet() {
    return amount_from_05_is_set;
  }


  /** 
   * <em>amount_from_05</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom05IsModified() {
    return amount_from_05_is_modified;
  }


  /** 
   * <em>amount_to_05</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountTo05()
  {
    return ( amount_to_05==null? ((long)0): amount_to_05.longValue() );
  }


  /** 
   * <em>amount_to_05</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountTo05IsNull()
  {
    return amount_to_05 == null;
  }


  /** 
   * <em>amount_to_05</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo05IsSet() {
    return amount_to_05_is_set;
  }


  /** 
   * <em>amount_to_05</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo05IsModified() {
    return amount_to_05_is_modified;
  }


  /** 
   * <em>commission_price_rate_05</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionPriceRate05()
  {
    return ( commission_price_rate_05==null? ((double)0): commission_price_rate_05.doubleValue() );
  }


  /** 
   * <em>commission_price_rate_05</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCommissionPriceRate05IsNull()
  {
    return commission_price_rate_05 == null;
  }


  /** 
   * <em>commission_price_rate_05</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate05IsSet() {
    return commission_price_rate_05_is_set;
  }


  /** 
   * <em>commission_price_rate_05</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate05IsModified() {
    return commission_price_rate_05_is_modified;
  }


  /** 
   * <em>amount_from_06</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountFrom06()
  {
    return ( amount_from_06==null? ((long)0): amount_from_06.longValue() );
  }


  /** 
   * <em>amount_from_06</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountFrom06IsNull()
  {
    return amount_from_06 == null;
  }


  /** 
   * <em>amount_from_06</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom06IsSet() {
    return amount_from_06_is_set;
  }


  /** 
   * <em>amount_from_06</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom06IsModified() {
    return amount_from_06_is_modified;
  }


  /** 
   * <em>amount_to_06</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountTo06()
  {
    return ( amount_to_06==null? ((long)0): amount_to_06.longValue() );
  }


  /** 
   * <em>amount_to_06</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountTo06IsNull()
  {
    return amount_to_06 == null;
  }


  /** 
   * <em>amount_to_06</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo06IsSet() {
    return amount_to_06_is_set;
  }


  /** 
   * <em>amount_to_06</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo06IsModified() {
    return amount_to_06_is_modified;
  }


  /** 
   * <em>commission_price_rate_06</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionPriceRate06()
  {
    return ( commission_price_rate_06==null? ((double)0): commission_price_rate_06.doubleValue() );
  }


  /** 
   * <em>commission_price_rate_06</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCommissionPriceRate06IsNull()
  {
    return commission_price_rate_06 == null;
  }


  /** 
   * <em>commission_price_rate_06</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate06IsSet() {
    return commission_price_rate_06_is_set;
  }


  /** 
   * <em>commission_price_rate_06</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate06IsModified() {
    return commission_price_rate_06_is_modified;
  }


  /** 
   * <em>amount_from_07</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountFrom07()
  {
    return ( amount_from_07==null? ((long)0): amount_from_07.longValue() );
  }


  /** 
   * <em>amount_from_07</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountFrom07IsNull()
  {
    return amount_from_07 == null;
  }


  /** 
   * <em>amount_from_07</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom07IsSet() {
    return amount_from_07_is_set;
  }


  /** 
   * <em>amount_from_07</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom07IsModified() {
    return amount_from_07_is_modified;
  }


  /** 
   * <em>amount_to_07</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountTo07()
  {
    return ( amount_to_07==null? ((long)0): amount_to_07.longValue() );
  }


  /** 
   * <em>amount_to_07</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountTo07IsNull()
  {
    return amount_to_07 == null;
  }


  /** 
   * <em>amount_to_07</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo07IsSet() {
    return amount_to_07_is_set;
  }


  /** 
   * <em>amount_to_07</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo07IsModified() {
    return amount_to_07_is_modified;
  }


  /** 
   * <em>commission_price_rate_07</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionPriceRate07()
  {
    return ( commission_price_rate_07==null? ((double)0): commission_price_rate_07.doubleValue() );
  }


  /** 
   * <em>commission_price_rate_07</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCommissionPriceRate07IsNull()
  {
    return commission_price_rate_07 == null;
  }


  /** 
   * <em>commission_price_rate_07</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate07IsSet() {
    return commission_price_rate_07_is_set;
  }


  /** 
   * <em>commission_price_rate_07</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate07IsModified() {
    return commission_price_rate_07_is_modified;
  }


  /** 
   * <em>amount_from_08</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountFrom08()
  {
    return ( amount_from_08==null? ((long)0): amount_from_08.longValue() );
  }


  /** 
   * <em>amount_from_08</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountFrom08IsNull()
  {
    return amount_from_08 == null;
  }


  /** 
   * <em>amount_from_08</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom08IsSet() {
    return amount_from_08_is_set;
  }


  /** 
   * <em>amount_from_08</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom08IsModified() {
    return amount_from_08_is_modified;
  }


  /** 
   * <em>amount_to_08</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountTo08()
  {
    return ( amount_to_08==null? ((long)0): amount_to_08.longValue() );
  }


  /** 
   * <em>amount_to_08</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountTo08IsNull()
  {
    return amount_to_08 == null;
  }


  /** 
   * <em>amount_to_08</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo08IsSet() {
    return amount_to_08_is_set;
  }


  /** 
   * <em>amount_to_08</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo08IsModified() {
    return amount_to_08_is_modified;
  }


  /** 
   * <em>commission_price_rate_08</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionPriceRate08()
  {
    return ( commission_price_rate_08==null? ((double)0): commission_price_rate_08.doubleValue() );
  }


  /** 
   * <em>commission_price_rate_08</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCommissionPriceRate08IsNull()
  {
    return commission_price_rate_08 == null;
  }


  /** 
   * <em>commission_price_rate_08</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate08IsSet() {
    return commission_price_rate_08_is_set;
  }


  /** 
   * <em>commission_price_rate_08</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate08IsModified() {
    return commission_price_rate_08_is_modified;
  }


  /** 
   * <em>amount_from_09</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountFrom09()
  {
    return ( amount_from_09==null? ((long)0): amount_from_09.longValue() );
  }


  /** 
   * <em>amount_from_09</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountFrom09IsNull()
  {
    return amount_from_09 == null;
  }


  /** 
   * <em>amount_from_09</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom09IsSet() {
    return amount_from_09_is_set;
  }


  /** 
   * <em>amount_from_09</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom09IsModified() {
    return amount_from_09_is_modified;
  }


  /** 
   * <em>amount_to_09</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountTo09()
  {
    return ( amount_to_09==null? ((long)0): amount_to_09.longValue() );
  }


  /** 
   * <em>amount_to_09</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountTo09IsNull()
  {
    return amount_to_09 == null;
  }


  /** 
   * <em>amount_to_09</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo09IsSet() {
    return amount_to_09_is_set;
  }


  /** 
   * <em>amount_to_09</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo09IsModified() {
    return amount_to_09_is_modified;
  }


  /** 
   * <em>commission_price_rate_09</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionPriceRate09()
  {
    return ( commission_price_rate_09==null? ((double)0): commission_price_rate_09.doubleValue() );
  }


  /** 
   * <em>commission_price_rate_09</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCommissionPriceRate09IsNull()
  {
    return commission_price_rate_09 == null;
  }


  /** 
   * <em>commission_price_rate_09</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate09IsSet() {
    return commission_price_rate_09_is_set;
  }


  /** 
   * <em>commission_price_rate_09</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate09IsModified() {
    return commission_price_rate_09_is_modified;
  }


  /** 
   * <em>amount_from_10</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountFrom10()
  {
    return ( amount_from_10==null? ((long)0): amount_from_10.longValue() );
  }


  /** 
   * <em>amount_from_10</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountFrom10IsNull()
  {
    return amount_from_10 == null;
  }


  /** 
   * <em>amount_from_10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom10IsSet() {
    return amount_from_10_is_set;
  }


  /** 
   * <em>amount_from_10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountFrom10IsModified() {
    return amount_from_10_is_modified;
  }


  /** 
   * <em>amount_to_10</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAmountTo10()
  {
    return ( amount_to_10==null? ((long)0): amount_to_10.longValue() );
  }


  /** 
   * <em>amount_to_10</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getAmountTo10IsNull()
  {
    return amount_to_10 == null;
  }


  /** 
   * <em>amount_to_10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo10IsSet() {
    return amount_to_10_is_set;
  }


  /** 
   * <em>amount_to_10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAmountTo10IsModified() {
    return amount_to_10_is_modified;
  }


  /** 
   * <em>commission_price_rate_10</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCommissionPriceRate10()
  {
    return ( commission_price_rate_10==null? ((double)0): commission_price_rate_10.doubleValue() );
  }


  /** 
   * <em>commission_price_rate_10</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCommissionPriceRate10IsNull()
  {
    return commission_price_rate_10 == null;
  }


  /** 
   * <em>commission_price_rate_10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate10IsSet() {
    return commission_price_rate_10_is_set;
  }


  /** 
   * <em>commission_price_rate_10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCommissionPriceRate10IsModified() {
    return commission_price_rate_10_is_modified;
  }


  /** 
   * <em>open_date_from</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOpenDateFrom()
  {
    return open_date_from;
  }


  /** 
   * <em>open_date_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenDateFromIsSet() {
    return open_date_from_is_set;
  }


  /** 
   * <em>open_date_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenDateFromIsModified() {
    return open_date_from_is_modified;
  }


  /** 
   * <em>open_date_to</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOpenDateTo()
  {
    return open_date_to;
  }


  /** 
   * <em>open_date_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenDateToIsSet() {
    return open_date_to_is_set;
  }


  /** 
   * <em>open_date_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOpenDateToIsModified() {
    return open_date_to_is_modified;
  }


  /** 
   * <em>collection_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getCollectionRate()
  {
    return ( collection_rate==null? ((double)0): collection_rate.doubleValue() );
  }


  /** 
   * <em>collection_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getCollectionRateIsNull()
  {
    return collection_rate == null;
  }


  /** 
   * <em>collection_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCollectionRateIsSet() {
    return collection_rate_is_set;
  }


  /** 
   * <em>collection_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getCollectionRateIsModified() {
    return collection_rate_is_modified;
  }


  /** 
   * <em>regist_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getRegistDiv()
  {
    return regist_div;
  }


  /** 
   * <em>regist_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistDivIsSet() {
    return regist_div_is_set;
  }


  /** 
   * <em>regist_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getRegistDivIsModified() {
    return regist_div_is_modified;
  }


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
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
    return new MutualFundInstCommissionPK(institution_code, product_code, deal_div, order_chanel, valid_date_from);
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
   * <em>product_code</em>カラムの値を設定します。 
   *
   * @@param p_productCode <em>product_code</em>カラムの値をあらわす10桁以下のString値 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>deal_div</em>カラムの値を設定します。 
   *
   * @@param p_dealDiv <em>deal_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setDealDiv( String p_dealDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    deal_div = p_dealDiv;
    deal_div_is_set = true;
    deal_div_is_modified = true;
  }


  /** 
   * <em>order_chanel</em>カラムの値を設定します。 
   *
   * @@param p_orderChanel <em>order_chanel</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOrderChanel( String p_orderChanel )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    order_chanel = p_orderChanel;
    order_chanel_is_set = true;
    order_chanel_is_modified = true;
  }


  /** 
   * <em>valid_date_from</em>カラムの値を設定します。 
   *
   * @@param p_validDateFrom <em>valid_date_from</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setValidDateFrom( java.sql.Timestamp p_validDateFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    valid_date_from = p_validDateFrom;
    valid_date_from_is_set = true;
    valid_date_from_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>valid_date_from</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setValidDateFrom( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    valid_date_from = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    valid_date_from_is_set = true;
    valid_date_from_is_modified = true;
  }


  /** 
   * <em>valid_date_to</em>カラムの値を設定します。 
   *
   * @@param p_validDateTo <em>valid_date_to</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setValidDateTo( java.sql.Timestamp p_validDateTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    valid_date_to = p_validDateTo;
    valid_date_to_is_set = true;
    valid_date_to_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>valid_date_to</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setValidDateTo( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    valid_date_to = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    valid_date_to_is_set = true;
    valid_date_to_is_modified = true;
  }


  /** 
   * <em>commission_div</em>カラムの値を設定します。 
   *
   * @@param p_commissionDiv <em>commission_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setCommissionDiv( String p_commissionDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_div = p_commissionDiv;
    commission_div_is_set = true;
    commission_div_is_modified = true;
  }


  /** 
   * <em>unit_count</em>カラムの値を設定します。 
   *
   * @@param p_unitCount <em>unit_count</em>カラムの値をあらわす9桁以下のint値 
   */
  public final void setUnitCount( int p_unitCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    unit_count = new Integer(p_unitCount);
    unit_count_is_set = true;
    unit_count_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>unit_count</em>カラムに値を設定します。 
   */
  public final void setUnitCount( Integer p_unitCount )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    unit_count = p_unitCount;
    unit_count_is_set = true;
    unit_count_is_modified = true;
  }


  /** 
   * <em>amount_from_01</em>カラムの値を設定します。 
   *
   * @@param p_amountFrom01 <em>amount_from_01</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountFrom01( long p_amountFrom01 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_01 = new Long(p_amountFrom01);
    amount_from_01_is_set = true;
    amount_from_01_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_from_01</em>カラムに値を設定します。 
   */
  public final void setAmountFrom01( Long p_amountFrom01 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_01 = p_amountFrom01;
    amount_from_01_is_set = true;
    amount_from_01_is_modified = true;
  }


  /** 
   * <em>amount_to_01</em>カラムの値を設定します。 
   *
   * @@param p_amountTo01 <em>amount_to_01</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountTo01( long p_amountTo01 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_01 = new Long(p_amountTo01);
    amount_to_01_is_set = true;
    amount_to_01_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_to_01</em>カラムに値を設定します。 
   */
  public final void setAmountTo01( Long p_amountTo01 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_01 = p_amountTo01;
    amount_to_01_is_set = true;
    amount_to_01_is_modified = true;
  }


  /** 
   * <em>commission_price_rate_01</em>カラムの値を設定します。 
   *
   * @@param p_commissionPriceRate01 <em>commission_price_rate_01</em>カラムの値をあらわす7桁以下のdouble値で、その精度は2桁まで
   */
  public final void setCommissionPriceRate01( double p_commissionPriceRate01 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_01 = new Double(p_commissionPriceRate01);
    commission_price_rate_01_is_set = true;
    commission_price_rate_01_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>commission_price_rate_01</em>カラムに値を設定します。 
   */
  public final void setCommissionPriceRate01( Double p_commissionPriceRate01 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_01 = p_commissionPriceRate01;
    commission_price_rate_01_is_set = true;
    commission_price_rate_01_is_modified = true;
  }


  /** 
   * <em>amount_from_02</em>カラムの値を設定します。 
   *
   * @@param p_amountFrom02 <em>amount_from_02</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountFrom02( long p_amountFrom02 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_02 = new Long(p_amountFrom02);
    amount_from_02_is_set = true;
    amount_from_02_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_from_02</em>カラムに値を設定します。 
   */
  public final void setAmountFrom02( Long p_amountFrom02 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_02 = p_amountFrom02;
    amount_from_02_is_set = true;
    amount_from_02_is_modified = true;
  }


  /** 
   * <em>amount_to_02</em>カラムの値を設定します。 
   *
   * @@param p_amountTo02 <em>amount_to_02</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountTo02( long p_amountTo02 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_02 = new Long(p_amountTo02);
    amount_to_02_is_set = true;
    amount_to_02_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_to_02</em>カラムに値を設定します。 
   */
  public final void setAmountTo02( Long p_amountTo02 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_02 = p_amountTo02;
    amount_to_02_is_set = true;
    amount_to_02_is_modified = true;
  }


  /** 
   * <em>commission_price_rate_02</em>カラムの値を設定します。 
   *
   * @@param p_commissionPriceRate02 <em>commission_price_rate_02</em>カラムの値をあらわす7桁以下のdouble値で、その精度は2桁まで
   */
  public final void setCommissionPriceRate02( double p_commissionPriceRate02 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_02 = new Double(p_commissionPriceRate02);
    commission_price_rate_02_is_set = true;
    commission_price_rate_02_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>commission_price_rate_02</em>カラムに値を設定します。 
   */
  public final void setCommissionPriceRate02( Double p_commissionPriceRate02 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_02 = p_commissionPriceRate02;
    commission_price_rate_02_is_set = true;
    commission_price_rate_02_is_modified = true;
  }


  /** 
   * <em>amount_from_03</em>カラムの値を設定します。 
   *
   * @@param p_amountFrom03 <em>amount_from_03</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountFrom03( long p_amountFrom03 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_03 = new Long(p_amountFrom03);
    amount_from_03_is_set = true;
    amount_from_03_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_from_03</em>カラムに値を設定します。 
   */
  public final void setAmountFrom03( Long p_amountFrom03 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_03 = p_amountFrom03;
    amount_from_03_is_set = true;
    amount_from_03_is_modified = true;
  }


  /** 
   * <em>amount_to_03</em>カラムの値を設定します。 
   *
   * @@param p_amountTo03 <em>amount_to_03</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountTo03( long p_amountTo03 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_03 = new Long(p_amountTo03);
    amount_to_03_is_set = true;
    amount_to_03_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_to_03</em>カラムに値を設定します。 
   */
  public final void setAmountTo03( Long p_amountTo03 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_03 = p_amountTo03;
    amount_to_03_is_set = true;
    amount_to_03_is_modified = true;
  }


  /** 
   * <em>commission_price_rate_03</em>カラムの値を設定します。 
   *
   * @@param p_commissionPriceRate03 <em>commission_price_rate_03</em>カラムの値をあらわす7桁以下のdouble値で、その精度は2桁まで
   */
  public final void setCommissionPriceRate03( double p_commissionPriceRate03 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_03 = new Double(p_commissionPriceRate03);
    commission_price_rate_03_is_set = true;
    commission_price_rate_03_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>commission_price_rate_03</em>カラムに値を設定します。 
   */
  public final void setCommissionPriceRate03( Double p_commissionPriceRate03 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_03 = p_commissionPriceRate03;
    commission_price_rate_03_is_set = true;
    commission_price_rate_03_is_modified = true;
  }


  /** 
   * <em>amount_from_04</em>カラムの値を設定します。 
   *
   * @@param p_amountFrom04 <em>amount_from_04</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountFrom04( long p_amountFrom04 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_04 = new Long(p_amountFrom04);
    amount_from_04_is_set = true;
    amount_from_04_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_from_04</em>カラムに値を設定します。 
   */
  public final void setAmountFrom04( Long p_amountFrom04 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_04 = p_amountFrom04;
    amount_from_04_is_set = true;
    amount_from_04_is_modified = true;
  }


  /** 
   * <em>amount_to_04</em>カラムの値を設定します。 
   *
   * @@param p_amountTo04 <em>amount_to_04</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountTo04( long p_amountTo04 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_04 = new Long(p_amountTo04);
    amount_to_04_is_set = true;
    amount_to_04_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_to_04</em>カラムに値を設定します。 
   */
  public final void setAmountTo04( Long p_amountTo04 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_04 = p_amountTo04;
    amount_to_04_is_set = true;
    amount_to_04_is_modified = true;
  }


  /** 
   * <em>commission_price_rate_04</em>カラムの値を設定します。 
   *
   * @@param p_commissionPriceRate04 <em>commission_price_rate_04</em>カラムの値をあらわす7桁以下のdouble値で、その精度は2桁まで
   */
  public final void setCommissionPriceRate04( double p_commissionPriceRate04 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_04 = new Double(p_commissionPriceRate04);
    commission_price_rate_04_is_set = true;
    commission_price_rate_04_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>commission_price_rate_04</em>カラムに値を設定します。 
   */
  public final void setCommissionPriceRate04( Double p_commissionPriceRate04 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_04 = p_commissionPriceRate04;
    commission_price_rate_04_is_set = true;
    commission_price_rate_04_is_modified = true;
  }


  /** 
   * <em>amount_from_05</em>カラムの値を設定します。 
   *
   * @@param p_amountFrom05 <em>amount_from_05</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountFrom05( long p_amountFrom05 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_05 = new Long(p_amountFrom05);
    amount_from_05_is_set = true;
    amount_from_05_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_from_05</em>カラムに値を設定します。 
   */
  public final void setAmountFrom05( Long p_amountFrom05 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_05 = p_amountFrom05;
    amount_from_05_is_set = true;
    amount_from_05_is_modified = true;
  }


  /** 
   * <em>amount_to_05</em>カラムの値を設定します。 
   *
   * @@param p_amountTo05 <em>amount_to_05</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountTo05( long p_amountTo05 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_05 = new Long(p_amountTo05);
    amount_to_05_is_set = true;
    amount_to_05_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_to_05</em>カラムに値を設定します。 
   */
  public final void setAmountTo05( Long p_amountTo05 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_05 = p_amountTo05;
    amount_to_05_is_set = true;
    amount_to_05_is_modified = true;
  }


  /** 
   * <em>commission_price_rate_05</em>カラムの値を設定します。 
   *
   * @@param p_commissionPriceRate05 <em>commission_price_rate_05</em>カラムの値をあらわす7桁以下のdouble値で、その精度は2桁まで
   */
  public final void setCommissionPriceRate05( double p_commissionPriceRate05 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_05 = new Double(p_commissionPriceRate05);
    commission_price_rate_05_is_set = true;
    commission_price_rate_05_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>commission_price_rate_05</em>カラムに値を設定します。 
   */
  public final void setCommissionPriceRate05( Double p_commissionPriceRate05 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_05 = p_commissionPriceRate05;
    commission_price_rate_05_is_set = true;
    commission_price_rate_05_is_modified = true;
  }


  /** 
   * <em>amount_from_06</em>カラムの値を設定します。 
   *
   * @@param p_amountFrom06 <em>amount_from_06</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountFrom06( long p_amountFrom06 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_06 = new Long(p_amountFrom06);
    amount_from_06_is_set = true;
    amount_from_06_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_from_06</em>カラムに値を設定します。 
   */
  public final void setAmountFrom06( Long p_amountFrom06 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_06 = p_amountFrom06;
    amount_from_06_is_set = true;
    amount_from_06_is_modified = true;
  }


  /** 
   * <em>amount_to_06</em>カラムの値を設定します。 
   *
   * @@param p_amountTo06 <em>amount_to_06</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountTo06( long p_amountTo06 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_06 = new Long(p_amountTo06);
    amount_to_06_is_set = true;
    amount_to_06_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_to_06</em>カラムに値を設定します。 
   */
  public final void setAmountTo06( Long p_amountTo06 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_06 = p_amountTo06;
    amount_to_06_is_set = true;
    amount_to_06_is_modified = true;
  }


  /** 
   * <em>commission_price_rate_06</em>カラムの値を設定します。 
   *
   * @@param p_commissionPriceRate06 <em>commission_price_rate_06</em>カラムの値をあらわす7桁以下のdouble値で、その精度は2桁まで
   */
  public final void setCommissionPriceRate06( double p_commissionPriceRate06 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_06 = new Double(p_commissionPriceRate06);
    commission_price_rate_06_is_set = true;
    commission_price_rate_06_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>commission_price_rate_06</em>カラムに値を設定します。 
   */
  public final void setCommissionPriceRate06( Double p_commissionPriceRate06 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_06 = p_commissionPriceRate06;
    commission_price_rate_06_is_set = true;
    commission_price_rate_06_is_modified = true;
  }


  /** 
   * <em>amount_from_07</em>カラムの値を設定します。 
   *
   * @@param p_amountFrom07 <em>amount_from_07</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountFrom07( long p_amountFrom07 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_07 = new Long(p_amountFrom07);
    amount_from_07_is_set = true;
    amount_from_07_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_from_07</em>カラムに値を設定します。 
   */
  public final void setAmountFrom07( Long p_amountFrom07 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_07 = p_amountFrom07;
    amount_from_07_is_set = true;
    amount_from_07_is_modified = true;
  }


  /** 
   * <em>amount_to_07</em>カラムの値を設定します。 
   *
   * @@param p_amountTo07 <em>amount_to_07</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountTo07( long p_amountTo07 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_07 = new Long(p_amountTo07);
    amount_to_07_is_set = true;
    amount_to_07_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_to_07</em>カラムに値を設定します。 
   */
  public final void setAmountTo07( Long p_amountTo07 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_07 = p_amountTo07;
    amount_to_07_is_set = true;
    amount_to_07_is_modified = true;
  }


  /** 
   * <em>commission_price_rate_07</em>カラムの値を設定します。 
   *
   * @@param p_commissionPriceRate07 <em>commission_price_rate_07</em>カラムの値をあらわす7桁以下のdouble値で、その精度は2桁まで
   */
  public final void setCommissionPriceRate07( double p_commissionPriceRate07 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_07 = new Double(p_commissionPriceRate07);
    commission_price_rate_07_is_set = true;
    commission_price_rate_07_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>commission_price_rate_07</em>カラムに値を設定します。 
   */
  public final void setCommissionPriceRate07( Double p_commissionPriceRate07 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_07 = p_commissionPriceRate07;
    commission_price_rate_07_is_set = true;
    commission_price_rate_07_is_modified = true;
  }


  /** 
   * <em>amount_from_08</em>カラムの値を設定します。 
   *
   * @@param p_amountFrom08 <em>amount_from_08</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountFrom08( long p_amountFrom08 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_08 = new Long(p_amountFrom08);
    amount_from_08_is_set = true;
    amount_from_08_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_from_08</em>カラムに値を設定します。 
   */
  public final void setAmountFrom08( Long p_amountFrom08 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_08 = p_amountFrom08;
    amount_from_08_is_set = true;
    amount_from_08_is_modified = true;
  }


  /** 
   * <em>amount_to_08</em>カラムの値を設定します。 
   *
   * @@param p_amountTo08 <em>amount_to_08</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountTo08( long p_amountTo08 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_08 = new Long(p_amountTo08);
    amount_to_08_is_set = true;
    amount_to_08_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_to_08</em>カラムに値を設定します。 
   */
  public final void setAmountTo08( Long p_amountTo08 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_08 = p_amountTo08;
    amount_to_08_is_set = true;
    amount_to_08_is_modified = true;
  }


  /** 
   * <em>commission_price_rate_08</em>カラムの値を設定します。 
   *
   * @@param p_commissionPriceRate08 <em>commission_price_rate_08</em>カラムの値をあらわす7桁以下のdouble値で、その精度は2桁まで
   */
  public final void setCommissionPriceRate08( double p_commissionPriceRate08 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_08 = new Double(p_commissionPriceRate08);
    commission_price_rate_08_is_set = true;
    commission_price_rate_08_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>commission_price_rate_08</em>カラムに値を設定します。 
   */
  public final void setCommissionPriceRate08( Double p_commissionPriceRate08 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_08 = p_commissionPriceRate08;
    commission_price_rate_08_is_set = true;
    commission_price_rate_08_is_modified = true;
  }


  /** 
   * <em>amount_from_09</em>カラムの値を設定します。 
   *
   * @@param p_amountFrom09 <em>amount_from_09</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountFrom09( long p_amountFrom09 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_09 = new Long(p_amountFrom09);
    amount_from_09_is_set = true;
    amount_from_09_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_from_09</em>カラムに値を設定します。 
   */
  public final void setAmountFrom09( Long p_amountFrom09 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_09 = p_amountFrom09;
    amount_from_09_is_set = true;
    amount_from_09_is_modified = true;
  }


  /** 
   * <em>amount_to_09</em>カラムの値を設定します。 
   *
   * @@param p_amountTo09 <em>amount_to_09</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountTo09( long p_amountTo09 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_09 = new Long(p_amountTo09);
    amount_to_09_is_set = true;
    amount_to_09_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_to_09</em>カラムに値を設定します。 
   */
  public final void setAmountTo09( Long p_amountTo09 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_09 = p_amountTo09;
    amount_to_09_is_set = true;
    amount_to_09_is_modified = true;
  }


  /** 
   * <em>commission_price_rate_09</em>カラムの値を設定します。 
   *
   * @@param p_commissionPriceRate09 <em>commission_price_rate_09</em>カラムの値をあらわす7桁以下のdouble値で、その精度は2桁まで
   */
  public final void setCommissionPriceRate09( double p_commissionPriceRate09 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_09 = new Double(p_commissionPriceRate09);
    commission_price_rate_09_is_set = true;
    commission_price_rate_09_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>commission_price_rate_09</em>カラムに値を設定します。 
   */
  public final void setCommissionPriceRate09( Double p_commissionPriceRate09 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_09 = p_commissionPriceRate09;
    commission_price_rate_09_is_set = true;
    commission_price_rate_09_is_modified = true;
  }


  /** 
   * <em>amount_from_10</em>カラムの値を設定します。 
   *
   * @@param p_amountFrom10 <em>amount_from_10</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountFrom10( long p_amountFrom10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_10 = new Long(p_amountFrom10);
    amount_from_10_is_set = true;
    amount_from_10_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_from_10</em>カラムに値を設定します。 
   */
  public final void setAmountFrom10( Long p_amountFrom10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_from_10 = p_amountFrom10;
    amount_from_10_is_set = true;
    amount_from_10_is_modified = true;
  }


  /** 
   * <em>amount_to_10</em>カラムの値を設定します。 
   *
   * @@param p_amountTo10 <em>amount_to_10</em>カラムの値をあらわす15桁以下のlong値 
   */
  public final void setAmountTo10( long p_amountTo10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_10 = new Long(p_amountTo10);
    amount_to_10_is_set = true;
    amount_to_10_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>amount_to_10</em>カラムに値を設定します。 
   */
  public final void setAmountTo10( Long p_amountTo10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    amount_to_10 = p_amountTo10;
    amount_to_10_is_set = true;
    amount_to_10_is_modified = true;
  }


  /** 
   * <em>commission_price_rate_10</em>カラムの値を設定します。 
   *
   * @@param p_commissionPriceRate10 <em>commission_price_rate_10</em>カラムの値をあらわす7桁以下のdouble値で、その精度は2桁まで
   */
  public final void setCommissionPriceRate10( double p_commissionPriceRate10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_10 = new Double(p_commissionPriceRate10);
    commission_price_rate_10_is_set = true;
    commission_price_rate_10_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>commission_price_rate_10</em>カラムに値を設定します。 
   */
  public final void setCommissionPriceRate10( Double p_commissionPriceRate10 )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    commission_price_rate_10 = p_commissionPriceRate10;
    commission_price_rate_10_is_set = true;
    commission_price_rate_10_is_modified = true;
  }


  /** 
   * <em>open_date_from</em>カラムの値を設定します。 
   *
   * @@param p_openDateFrom <em>open_date_from</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOpenDateFrom( java.sql.Timestamp p_openDateFrom )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_date_from = p_openDateFrom;
    open_date_from_is_set = true;
    open_date_from_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>open_date_from</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOpenDateFrom( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    open_date_from = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    open_date_from_is_set = true;
    open_date_from_is_modified = true;
  }


  /** 
   * <em>open_date_to</em>カラムの値を設定します。 
   *
   * @@param p_openDateTo <em>open_date_to</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOpenDateTo( java.sql.Timestamp p_openDateTo )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_date_to = p_openDateTo;
    open_date_to_is_set = true;
    open_date_to_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>open_date_to</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOpenDateTo( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    open_date_to = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    open_date_to_is_set = true;
    open_date_to_is_modified = true;
  }


  /** 
   * <em>collection_rate</em>カラムの値を設定します。 
   *
   * @@param p_collectionRate <em>collection_rate</em>カラムの値をあらわす9桁以下のdouble値で、その精度は6桁まで
   */
  public final void setCollectionRate( double p_collectionRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    collection_rate = new Double(p_collectionRate);
    collection_rate_is_set = true;
    collection_rate_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>collection_rate</em>カラムに値を設定します。 
   */
  public final void setCollectionRate( Double p_collectionRate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    collection_rate = p_collectionRate;
    collection_rate_is_set = true;
    collection_rate_is_modified = true;
  }


  /** 
   * <em>regist_div</em>カラムの値を設定します。 
   *
   * @@param p_registDiv <em>regist_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setRegistDiv( String p_registDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    regist_div = p_registDiv;
    regist_div_is_set = true;
    regist_div_is_modified = true;
  }


  /** 
   * <em>last_updater</em>カラムの値を設定します。 
   *
   * @@param p_lastUpdater <em>last_updater</em>カラムの値をあらわす20桁以下のString値 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
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
                if ( name.equals("amount_from_01") ) {
                    return this.amount_from_01;
                }
                else if ( name.equals("amount_to_01") ) {
                    return this.amount_to_01;
                }
                else if ( name.equals("amount_from_02") ) {
                    return this.amount_from_02;
                }
                else if ( name.equals("amount_to_02") ) {
                    return this.amount_to_02;
                }
                else if ( name.equals("amount_from_03") ) {
                    return this.amount_from_03;
                }
                else if ( name.equals("amount_to_03") ) {
                    return this.amount_to_03;
                }
                else if ( name.equals("amount_from_04") ) {
                    return this.amount_from_04;
                }
                else if ( name.equals("amount_to_04") ) {
                    return this.amount_to_04;
                }
                else if ( name.equals("amount_from_05") ) {
                    return this.amount_from_05;
                }
                else if ( name.equals("amount_to_05") ) {
                    return this.amount_to_05;
                }
                else if ( name.equals("amount_from_06") ) {
                    return this.amount_from_06;
                }
                else if ( name.equals("amount_to_06") ) {
                    return this.amount_to_06;
                }
                else if ( name.equals("amount_from_07") ) {
                    return this.amount_from_07;
                }
                else if ( name.equals("amount_to_07") ) {
                    return this.amount_to_07;
                }
                else if ( name.equals("amount_from_08") ) {
                    return this.amount_from_08;
                }
                else if ( name.equals("amount_to_08") ) {
                    return this.amount_to_08;
                }
                else if ( name.equals("amount_from_09") ) {
                    return this.amount_from_09;
                }
                else if ( name.equals("amount_to_09") ) {
                    return this.amount_to_09;
                }
                else if ( name.equals("amount_from_10") ) {
                    return this.amount_from_10;
                }
                else if ( name.equals("amount_to_10") ) {
                    return this.amount_to_10;
                }
                break;
            case 'c':
                if ( name.equals("commission_div") ) {
                    return this.commission_div;
                }
                else if ( name.equals("commission_price_rate_01") ) {
                    return this.commission_price_rate_01;
                }
                else if ( name.equals("commission_price_rate_02") ) {
                    return this.commission_price_rate_02;
                }
                else if ( name.equals("commission_price_rate_03") ) {
                    return this.commission_price_rate_03;
                }
                else if ( name.equals("commission_price_rate_04") ) {
                    return this.commission_price_rate_04;
                }
                else if ( name.equals("commission_price_rate_05") ) {
                    return this.commission_price_rate_05;
                }
                else if ( name.equals("commission_price_rate_06") ) {
                    return this.commission_price_rate_06;
                }
                else if ( name.equals("commission_price_rate_07") ) {
                    return this.commission_price_rate_07;
                }
                else if ( name.equals("commission_price_rate_08") ) {
                    return this.commission_price_rate_08;
                }
                else if ( name.equals("commission_price_rate_09") ) {
                    return this.commission_price_rate_09;
                }
                else if ( name.equals("commission_price_rate_10") ) {
                    return this.commission_price_rate_10;
                }
                else if ( name.equals("collection_rate") ) {
                    return this.collection_rate;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("deal_div") ) {
                    return this.deal_div;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'o':
                if ( name.equals("order_chanel") ) {
                    return this.order_chanel;
                }
                else if ( name.equals("open_date_from") ) {
                    return this.open_date_from;
                }
                else if ( name.equals("open_date_to") ) {
                    return this.open_date_to;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                break;
            case 'r':
                if ( name.equals("regist_div") ) {
                    return this.regist_div;
                }
                break;
            case 'u':
                if ( name.equals("unit_count") ) {
                    return this.unit_count;
                }
                break;
            case 'v':
                if ( name.equals("valid_date_from") ) {
                    return this.valid_date_from;
                }
                else if ( name.equals("valid_date_to") ) {
                    return this.valid_date_to;
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
                if ( name.equals("amount_from_01") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_from_01' must be Long: '"+value+"' is not." );
                    this.amount_from_01 = (Long) value;
                    if (this.amount_from_01_is_set)
                        this.amount_from_01_is_modified = true;
                    this.amount_from_01_is_set = true;
                    return;
                }
                else if ( name.equals("amount_to_01") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_to_01' must be Long: '"+value+"' is not." );
                    this.amount_to_01 = (Long) value;
                    if (this.amount_to_01_is_set)
                        this.amount_to_01_is_modified = true;
                    this.amount_to_01_is_set = true;
                    return;
                }
                else if ( name.equals("amount_from_02") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_from_02' must be Long: '"+value+"' is not." );
                    this.amount_from_02 = (Long) value;
                    if (this.amount_from_02_is_set)
                        this.amount_from_02_is_modified = true;
                    this.amount_from_02_is_set = true;
                    return;
                }
                else if ( name.equals("amount_to_02") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_to_02' must be Long: '"+value+"' is not." );
                    this.amount_to_02 = (Long) value;
                    if (this.amount_to_02_is_set)
                        this.amount_to_02_is_modified = true;
                    this.amount_to_02_is_set = true;
                    return;
                }
                else if ( name.equals("amount_from_03") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_from_03' must be Long: '"+value+"' is not." );
                    this.amount_from_03 = (Long) value;
                    if (this.amount_from_03_is_set)
                        this.amount_from_03_is_modified = true;
                    this.amount_from_03_is_set = true;
                    return;
                }
                else if ( name.equals("amount_to_03") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_to_03' must be Long: '"+value+"' is not." );
                    this.amount_to_03 = (Long) value;
                    if (this.amount_to_03_is_set)
                        this.amount_to_03_is_modified = true;
                    this.amount_to_03_is_set = true;
                    return;
                }
                else if ( name.equals("amount_from_04") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_from_04' must be Long: '"+value+"' is not." );
                    this.amount_from_04 = (Long) value;
                    if (this.amount_from_04_is_set)
                        this.amount_from_04_is_modified = true;
                    this.amount_from_04_is_set = true;
                    return;
                }
                else if ( name.equals("amount_to_04") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_to_04' must be Long: '"+value+"' is not." );
                    this.amount_to_04 = (Long) value;
                    if (this.amount_to_04_is_set)
                        this.amount_to_04_is_modified = true;
                    this.amount_to_04_is_set = true;
                    return;
                }
                else if ( name.equals("amount_from_05") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_from_05' must be Long: '"+value+"' is not." );
                    this.amount_from_05 = (Long) value;
                    if (this.amount_from_05_is_set)
                        this.amount_from_05_is_modified = true;
                    this.amount_from_05_is_set = true;
                    return;
                }
                else if ( name.equals("amount_to_05") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_to_05' must be Long: '"+value+"' is not." );
                    this.amount_to_05 = (Long) value;
                    if (this.amount_to_05_is_set)
                        this.amount_to_05_is_modified = true;
                    this.amount_to_05_is_set = true;
                    return;
                }
                else if ( name.equals("amount_from_06") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_from_06' must be Long: '"+value+"' is not." );
                    this.amount_from_06 = (Long) value;
                    if (this.amount_from_06_is_set)
                        this.amount_from_06_is_modified = true;
                    this.amount_from_06_is_set = true;
                    return;
                }
                else if ( name.equals("amount_to_06") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_to_06' must be Long: '"+value+"' is not." );
                    this.amount_to_06 = (Long) value;
                    if (this.amount_to_06_is_set)
                        this.amount_to_06_is_modified = true;
                    this.amount_to_06_is_set = true;
                    return;
                }
                else if ( name.equals("amount_from_07") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_from_07' must be Long: '"+value+"' is not." );
                    this.amount_from_07 = (Long) value;
                    if (this.amount_from_07_is_set)
                        this.amount_from_07_is_modified = true;
                    this.amount_from_07_is_set = true;
                    return;
                }
                else if ( name.equals("amount_to_07") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_to_07' must be Long: '"+value+"' is not." );
                    this.amount_to_07 = (Long) value;
                    if (this.amount_to_07_is_set)
                        this.amount_to_07_is_modified = true;
                    this.amount_to_07_is_set = true;
                    return;
                }
                else if ( name.equals("amount_from_08") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_from_08' must be Long: '"+value+"' is not." );
                    this.amount_from_08 = (Long) value;
                    if (this.amount_from_08_is_set)
                        this.amount_from_08_is_modified = true;
                    this.amount_from_08_is_set = true;
                    return;
                }
                else if ( name.equals("amount_to_08") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_to_08' must be Long: '"+value+"' is not." );
                    this.amount_to_08 = (Long) value;
                    if (this.amount_to_08_is_set)
                        this.amount_to_08_is_modified = true;
                    this.amount_to_08_is_set = true;
                    return;
                }
                else if ( name.equals("amount_from_09") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_from_09' must be Long: '"+value+"' is not." );
                    this.amount_from_09 = (Long) value;
                    if (this.amount_from_09_is_set)
                        this.amount_from_09_is_modified = true;
                    this.amount_from_09_is_set = true;
                    return;
                }
                else if ( name.equals("amount_to_09") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_to_09' must be Long: '"+value+"' is not." );
                    this.amount_to_09 = (Long) value;
                    if (this.amount_to_09_is_set)
                        this.amount_to_09_is_modified = true;
                    this.amount_to_09_is_set = true;
                    return;
                }
                else if ( name.equals("amount_from_10") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_from_10' must be Long: '"+value+"' is not." );
                    this.amount_from_10 = (Long) value;
                    if (this.amount_from_10_is_set)
                        this.amount_from_10_is_modified = true;
                    this.amount_from_10_is_set = true;
                    return;
                }
                else if ( name.equals("amount_to_10") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'amount_to_10' must be Long: '"+value+"' is not." );
                    this.amount_to_10 = (Long) value;
                    if (this.amount_to_10_is_set)
                        this.amount_to_10_is_modified = true;
                    this.amount_to_10_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("commission_div") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'commission_div' must be String: '"+value+"' is not." );
                    this.commission_div = (String) value;
                    if (this.commission_div_is_set)
                        this.commission_div_is_modified = true;
                    this.commission_div_is_set = true;
                    return;
                }
                else if ( name.equals("commission_price_rate_01") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_price_rate_01' must be Double: '"+value+"' is not." );
                    this.commission_price_rate_01 = (Double) value;
                    if (this.commission_price_rate_01_is_set)
                        this.commission_price_rate_01_is_modified = true;
                    this.commission_price_rate_01_is_set = true;
                    return;
                }
                else if ( name.equals("commission_price_rate_02") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_price_rate_02' must be Double: '"+value+"' is not." );
                    this.commission_price_rate_02 = (Double) value;
                    if (this.commission_price_rate_02_is_set)
                        this.commission_price_rate_02_is_modified = true;
                    this.commission_price_rate_02_is_set = true;
                    return;
                }
                else if ( name.equals("commission_price_rate_03") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_price_rate_03' must be Double: '"+value+"' is not." );
                    this.commission_price_rate_03 = (Double) value;
                    if (this.commission_price_rate_03_is_set)
                        this.commission_price_rate_03_is_modified = true;
                    this.commission_price_rate_03_is_set = true;
                    return;
                }
                else if ( name.equals("commission_price_rate_04") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_price_rate_04' must be Double: '"+value+"' is not." );
                    this.commission_price_rate_04 = (Double) value;
                    if (this.commission_price_rate_04_is_set)
                        this.commission_price_rate_04_is_modified = true;
                    this.commission_price_rate_04_is_set = true;
                    return;
                }
                else if ( name.equals("commission_price_rate_05") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_price_rate_05' must be Double: '"+value+"' is not." );
                    this.commission_price_rate_05 = (Double) value;
                    if (this.commission_price_rate_05_is_set)
                        this.commission_price_rate_05_is_modified = true;
                    this.commission_price_rate_05_is_set = true;
                    return;
                }
                else if ( name.equals("commission_price_rate_06") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_price_rate_06' must be Double: '"+value+"' is not." );
                    this.commission_price_rate_06 = (Double) value;
                    if (this.commission_price_rate_06_is_set)
                        this.commission_price_rate_06_is_modified = true;
                    this.commission_price_rate_06_is_set = true;
                    return;
                }
                else if ( name.equals("commission_price_rate_07") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_price_rate_07' must be Double: '"+value+"' is not." );
                    this.commission_price_rate_07 = (Double) value;
                    if (this.commission_price_rate_07_is_set)
                        this.commission_price_rate_07_is_modified = true;
                    this.commission_price_rate_07_is_set = true;
                    return;
                }
                else if ( name.equals("commission_price_rate_08") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_price_rate_08' must be Double: '"+value+"' is not." );
                    this.commission_price_rate_08 = (Double) value;
                    if (this.commission_price_rate_08_is_set)
                        this.commission_price_rate_08_is_modified = true;
                    this.commission_price_rate_08_is_set = true;
                    return;
                }
                else if ( name.equals("commission_price_rate_09") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_price_rate_09' must be Double: '"+value+"' is not." );
                    this.commission_price_rate_09 = (Double) value;
                    if (this.commission_price_rate_09_is_set)
                        this.commission_price_rate_09_is_modified = true;
                    this.commission_price_rate_09_is_set = true;
                    return;
                }
                else if ( name.equals("commission_price_rate_10") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'commission_price_rate_10' must be Double: '"+value+"' is not." );
                    this.commission_price_rate_10 = (Double) value;
                    if (this.commission_price_rate_10_is_set)
                        this.commission_price_rate_10_is_modified = true;
                    this.commission_price_rate_10_is_set = true;
                    return;
                }
                else if ( name.equals("collection_rate") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'collection_rate' must be Double: '"+value+"' is not." );
                    this.collection_rate = (Double) value;
                    if (this.collection_rate_is_set)
                        this.collection_rate_is_modified = true;
                    this.collection_rate_is_set = true;
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
                if ( name.equals("deal_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'deal_div' must be String: '"+value+"' is not." );
                    this.deal_div = (String) value;
                    if (this.deal_div_is_set)
                        this.deal_div_is_modified = true;
                    this.deal_div_is_set = true;
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
                if ( name.equals("last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
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
                if ( name.equals("order_chanel") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'order_chanel' must be String: '"+value+"' is not." );
                    this.order_chanel = (String) value;
                    if (this.order_chanel_is_set)
                        this.order_chanel_is_modified = true;
                    this.order_chanel_is_set = true;
                    return;
                }
                else if ( name.equals("open_date_from") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'open_date_from' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.open_date_from = (java.sql.Timestamp) value;
                    if (this.open_date_from_is_set)
                        this.open_date_from_is_modified = true;
                    this.open_date_from_is_set = true;
                    return;
                }
                else if ( name.equals("open_date_to") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'open_date_to' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.open_date_to = (java.sql.Timestamp) value;
                    if (this.open_date_to_is_set)
                        this.open_date_to_is_modified = true;
                    this.open_date_to_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("regist_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'regist_div' must be String: '"+value+"' is not." );
                    this.regist_div = (String) value;
                    if (this.regist_div_is_set)
                        this.regist_div_is_modified = true;
                    this.regist_div_is_set = true;
                    return;
                }
                break;
            case 'u':
                if ( name.equals("unit_count") ) {
                    if ( value != null )
                      if ( !(value instanceof Integer) )
                        throw new IllegalArgumentException( "value for 'unit_count' must be Integer: '"+value+"' is not." );
                    this.unit_count = (Integer) value;
                    if (this.unit_count_is_set)
                        this.unit_count_is_modified = true;
                    this.unit_count_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("valid_date_from") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'valid_date_from' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.valid_date_from = (java.sql.Timestamp) value;
                    if (this.valid_date_from_is_set)
                        this.valid_date_from_is_modified = true;
                    this.valid_date_from_is_set = true;
                    return;
                }
                else if ( name.equals("valid_date_to") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'valid_date_to' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.valid_date_to = (java.sql.Timestamp) value;
                    if (this.valid_date_to_is_set)
                        this.valid_date_to_is_modified = true;
                    this.valid_date_to_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
