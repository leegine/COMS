head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFundInstCommissionRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * MutualFundInstCommissionRowインタフェースは変更不可でリードオンリーである<em>mutual_fund_inst_commission</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link MutualFundInstCommissionRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundInstCommissionPK 
 */
public interface MutualFundInstCommissionRow extends Row {


  /** 
   * この{@@link MutualFundInstCommissionRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "mutual_fund_inst_commission", "master" );


  /** 
   * <em>institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInstitutionCode();


  /** 
   * <em>institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionCodeIsSet();


  /** 
   * <em>institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionCodeIsModified();


  /** 
   * <em>product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProductCode();


  /** 
   * <em>product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductCodeIsSet();


  /** 
   * <em>product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductCodeIsModified();


  /** 
   * <em>deal_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDealDiv();


  /** 
   * <em>deal_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDealDivIsSet();


  /** 
   * <em>deal_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDealDivIsModified();


  /** 
   * <em>order_chanel</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderChanel();


  /** 
   * <em>order_chanel</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderChanelIsSet();


  /** 
   * <em>order_chanel</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderChanelIsModified();


  /** 
   * <em>valid_date_from</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getValidDateFrom();


  /** 
   * <em>valid_date_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getValidDateFromIsSet();


  /** 
   * <em>valid_date_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getValidDateFromIsModified();


  /** 
   * <em>valid_date_to</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getValidDateTo();


  /** 
   * <em>valid_date_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getValidDateToIsSet();


  /** 
   * <em>valid_date_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getValidDateToIsModified();


  /** 
   * <em>commission_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCommissionDiv();


  /** 
   * <em>commission_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionDivIsSet();


  /** 
   * <em>commission_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionDivIsModified();


  /** 
   * <em>unit_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getUnitCount();


  /** 
   * <em>unit_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getUnitCountIsNull();


  /** 
   * <em>unit_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnitCountIsSet();


  /** 
   * <em>unit_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnitCountIsModified();


  /** 
   * <em>amount_from_01</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountFrom01();


  /** 
   * <em>amount_from_01</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountFrom01IsNull();


  /** 
   * <em>amount_from_01</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom01IsSet();


  /** 
   * <em>amount_from_01</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom01IsModified();


  /** 
   * <em>amount_to_01</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountTo01();


  /** 
   * <em>amount_to_01</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountTo01IsNull();


  /** 
   * <em>amount_to_01</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo01IsSet();


  /** 
   * <em>amount_to_01</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo01IsModified();


  /** 
   * <em>commission_price_rate_01</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCommissionPriceRate01();


  /** 
   * <em>commission_price_rate_01</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCommissionPriceRate01IsNull();


  /** 
   * <em>commission_price_rate_01</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate01IsSet();


  /** 
   * <em>commission_price_rate_01</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate01IsModified();


  /** 
   * <em>amount_from_02</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountFrom02();


  /** 
   * <em>amount_from_02</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountFrom02IsNull();


  /** 
   * <em>amount_from_02</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom02IsSet();


  /** 
   * <em>amount_from_02</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom02IsModified();


  /** 
   * <em>amount_to_02</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountTo02();


  /** 
   * <em>amount_to_02</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountTo02IsNull();


  /** 
   * <em>amount_to_02</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo02IsSet();


  /** 
   * <em>amount_to_02</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo02IsModified();


  /** 
   * <em>commission_price_rate_02</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCommissionPriceRate02();


  /** 
   * <em>commission_price_rate_02</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCommissionPriceRate02IsNull();


  /** 
   * <em>commission_price_rate_02</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate02IsSet();


  /** 
   * <em>commission_price_rate_02</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate02IsModified();


  /** 
   * <em>amount_from_03</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountFrom03();


  /** 
   * <em>amount_from_03</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountFrom03IsNull();


  /** 
   * <em>amount_from_03</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom03IsSet();


  /** 
   * <em>amount_from_03</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom03IsModified();


  /** 
   * <em>amount_to_03</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountTo03();


  /** 
   * <em>amount_to_03</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountTo03IsNull();


  /** 
   * <em>amount_to_03</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo03IsSet();


  /** 
   * <em>amount_to_03</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo03IsModified();


  /** 
   * <em>commission_price_rate_03</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCommissionPriceRate03();


  /** 
   * <em>commission_price_rate_03</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCommissionPriceRate03IsNull();


  /** 
   * <em>commission_price_rate_03</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate03IsSet();


  /** 
   * <em>commission_price_rate_03</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate03IsModified();


  /** 
   * <em>amount_from_04</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountFrom04();


  /** 
   * <em>amount_from_04</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountFrom04IsNull();


  /** 
   * <em>amount_from_04</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom04IsSet();


  /** 
   * <em>amount_from_04</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom04IsModified();


  /** 
   * <em>amount_to_04</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountTo04();


  /** 
   * <em>amount_to_04</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountTo04IsNull();


  /** 
   * <em>amount_to_04</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo04IsSet();


  /** 
   * <em>amount_to_04</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo04IsModified();


  /** 
   * <em>commission_price_rate_04</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCommissionPriceRate04();


  /** 
   * <em>commission_price_rate_04</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCommissionPriceRate04IsNull();


  /** 
   * <em>commission_price_rate_04</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate04IsSet();


  /** 
   * <em>commission_price_rate_04</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate04IsModified();


  /** 
   * <em>amount_from_05</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountFrom05();


  /** 
   * <em>amount_from_05</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountFrom05IsNull();


  /** 
   * <em>amount_from_05</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom05IsSet();


  /** 
   * <em>amount_from_05</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom05IsModified();


  /** 
   * <em>amount_to_05</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountTo05();


  /** 
   * <em>amount_to_05</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountTo05IsNull();


  /** 
   * <em>amount_to_05</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo05IsSet();


  /** 
   * <em>amount_to_05</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo05IsModified();


  /** 
   * <em>commission_price_rate_05</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCommissionPriceRate05();


  /** 
   * <em>commission_price_rate_05</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCommissionPriceRate05IsNull();


  /** 
   * <em>commission_price_rate_05</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate05IsSet();


  /** 
   * <em>commission_price_rate_05</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate05IsModified();


  /** 
   * <em>amount_from_06</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountFrom06();


  /** 
   * <em>amount_from_06</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountFrom06IsNull();


  /** 
   * <em>amount_from_06</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom06IsSet();


  /** 
   * <em>amount_from_06</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom06IsModified();


  /** 
   * <em>amount_to_06</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountTo06();


  /** 
   * <em>amount_to_06</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountTo06IsNull();


  /** 
   * <em>amount_to_06</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo06IsSet();


  /** 
   * <em>amount_to_06</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo06IsModified();


  /** 
   * <em>commission_price_rate_06</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCommissionPriceRate06();


  /** 
   * <em>commission_price_rate_06</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCommissionPriceRate06IsNull();


  /** 
   * <em>commission_price_rate_06</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate06IsSet();


  /** 
   * <em>commission_price_rate_06</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate06IsModified();


  /** 
   * <em>amount_from_07</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountFrom07();


  /** 
   * <em>amount_from_07</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountFrom07IsNull();


  /** 
   * <em>amount_from_07</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom07IsSet();


  /** 
   * <em>amount_from_07</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom07IsModified();


  /** 
   * <em>amount_to_07</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountTo07();


  /** 
   * <em>amount_to_07</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountTo07IsNull();


  /** 
   * <em>amount_to_07</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo07IsSet();


  /** 
   * <em>amount_to_07</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo07IsModified();


  /** 
   * <em>commission_price_rate_07</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCommissionPriceRate07();


  /** 
   * <em>commission_price_rate_07</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCommissionPriceRate07IsNull();


  /** 
   * <em>commission_price_rate_07</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate07IsSet();


  /** 
   * <em>commission_price_rate_07</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate07IsModified();


  /** 
   * <em>amount_from_08</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountFrom08();


  /** 
   * <em>amount_from_08</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountFrom08IsNull();


  /** 
   * <em>amount_from_08</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom08IsSet();


  /** 
   * <em>amount_from_08</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom08IsModified();


  /** 
   * <em>amount_to_08</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountTo08();


  /** 
   * <em>amount_to_08</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountTo08IsNull();


  /** 
   * <em>amount_to_08</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo08IsSet();


  /** 
   * <em>amount_to_08</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo08IsModified();


  /** 
   * <em>commission_price_rate_08</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCommissionPriceRate08();


  /** 
   * <em>commission_price_rate_08</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCommissionPriceRate08IsNull();


  /** 
   * <em>commission_price_rate_08</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate08IsSet();


  /** 
   * <em>commission_price_rate_08</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate08IsModified();


  /** 
   * <em>amount_from_09</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountFrom09();


  /** 
   * <em>amount_from_09</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountFrom09IsNull();


  /** 
   * <em>amount_from_09</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom09IsSet();


  /** 
   * <em>amount_from_09</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom09IsModified();


  /** 
   * <em>amount_to_09</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountTo09();


  /** 
   * <em>amount_to_09</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountTo09IsNull();


  /** 
   * <em>amount_to_09</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo09IsSet();


  /** 
   * <em>amount_to_09</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo09IsModified();


  /** 
   * <em>commission_price_rate_09</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCommissionPriceRate09();


  /** 
   * <em>commission_price_rate_09</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCommissionPriceRate09IsNull();


  /** 
   * <em>commission_price_rate_09</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate09IsSet();


  /** 
   * <em>commission_price_rate_09</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate09IsModified();


  /** 
   * <em>amount_from_10</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountFrom10();


  /** 
   * <em>amount_from_10</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountFrom10IsNull();


  /** 
   * <em>amount_from_10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom10IsSet();


  /** 
   * <em>amount_from_10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountFrom10IsModified();


  /** 
   * <em>amount_to_10</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAmountTo10();


  /** 
   * <em>amount_to_10</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getAmountTo10IsNull();


  /** 
   * <em>amount_to_10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo10IsSet();


  /** 
   * <em>amount_to_10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAmountTo10IsModified();


  /** 
   * <em>commission_price_rate_10</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCommissionPriceRate10();


  /** 
   * <em>commission_price_rate_10</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCommissionPriceRate10IsNull();


  /** 
   * <em>commission_price_rate_10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate10IsSet();


  /** 
   * <em>commission_price_rate_10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommissionPriceRate10IsModified();


  /** 
   * <em>open_date_from</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getOpenDateFrom();


  /** 
   * <em>open_date_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOpenDateFromIsSet();


  /** 
   * <em>open_date_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOpenDateFromIsModified();


  /** 
   * <em>open_date_to</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getOpenDateTo();


  /** 
   * <em>open_date_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOpenDateToIsSet();


  /** 
   * <em>open_date_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOpenDateToIsModified();


  /** 
   * <em>collection_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getCollectionRate();


  /** 
   * <em>collection_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getCollectionRateIsNull();


  /** 
   * <em>collection_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCollectionRateIsSet();


  /** 
   * <em>collection_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCollectionRateIsModified();


  /** 
   * <em>regist_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRegistDiv();


  /** 
   * <em>regist_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRegistDivIsSet();


  /** 
   * <em>regist_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRegistDivIsModified();


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLastUpdater();


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdaterIsSet();


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdaterIsModified();


  /** 
   * <em>created_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getCreatedTimestamp();


  /** 
   * <em>created_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCreatedTimestampIsSet();


  /** 
   * <em>created_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCreatedTimestampIsModified();


  /** 
   * <em>last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getLastUpdatedTimestamp();


  /** 
   * <em>last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdatedTimestampIsSet();


  /** 
   * <em>last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdatedTimestampIsModified();


}
@
