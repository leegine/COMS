head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.24.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	BranchMarketRepayDealtCondRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * BranchMarketRepayDealtCondRowインタフェースは変更不可でリードオンリーである<em>branch_market_repay_dealt_cond</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link BranchMarketRepayDealtCondRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see BranchMarketRepayDealtCondPK 
 */
public interface BranchMarketRepayDealtCondRow extends Row {


  /** 
   * この{@@link BranchMarketRepayDealtCondRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "branch_market_repay_dealt_cond", "master" );


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
   * <em>branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBranchCode();


  /** 
   * <em>branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchCodeIsSet();


  /** 
   * <em>branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchCodeIsModified();


  /** 
   * <em>market_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarketCode();


  /** 
   * <em>market_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketCodeIsSet();


  /** 
   * <em>market_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketCodeIsModified();


  /** 
   * <em>repayment_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRepaymentDiv();


  /** 
   * <em>repayment_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepaymentDivIsSet();


  /** 
   * <em>repayment_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepaymentDivIsModified();


  /** 
   * <em>repayment_num</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getRepaymentNum();


  /** 
   * <em>repayment_num</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepaymentNumIsSet();


  /** 
   * <em>repayment_num</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepaymentNumIsModified();


  /** 
   * <em>sonar_repayment_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSonarRepaymentType();


  /** 
   * <em>sonar_repayment_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarRepaymentTypeIsSet();


  /** 
   * <em>sonar_repayment_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarRepaymentTypeIsModified();


  /** 
   * <em>mart_can_dealt</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMartCanDealt();


  /** 
   * <em>mart_can_dealt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMartCanDealtIsSet();


  /** 
   * <em>mart_can_dealt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMartCanDealtIsModified();


  /** 
   * <em>limited_unit_m_long_1st_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLimitedUnitMLong1stSec();


  /** 
   * <em>limited_unit_m_long_1st_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLimitedUnitMLong1stSecIsNull();


  /** 
   * <em>limited_unit_m_long_1st_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitedUnitMLong1stSecIsSet();


  /** 
   * <em>limited_unit_m_long_1st_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitedUnitMLong1stSecIsModified();


  /** 
   * <em>limited_unit_m_long_2nd_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLimitedUnitMLong2ndSec();


  /** 
   * <em>limited_unit_m_long_2nd_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLimitedUnitMLong2ndSecIsNull();


  /** 
   * <em>limited_unit_m_long_2nd_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitedUnitMLong2ndSecIsSet();


  /** 
   * <em>limited_unit_m_long_2nd_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitedUnitMLong2ndSecIsModified();


  /** 
   * <em>limited_unit_m_short_1st_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLimitedUnitMShort1stSec();


  /** 
   * <em>limited_unit_m_short_1st_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLimitedUnitMShort1stSecIsNull();


  /** 
   * <em>limited_unit_m_short_1st_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitedUnitMShort1stSecIsSet();


  /** 
   * <em>limited_unit_m_short_1st_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitedUnitMShort1stSecIsModified();


  /** 
   * <em>limited_unit_m_short_2nd_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLimitedUnitMShort2ndSec();


  /** 
   * <em>limited_unit_m_short_2nd_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLimitedUnitMShort2ndSecIsNull();


  /** 
   * <em>limited_unit_m_short_2nd_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitedUnitMShort2ndSecIsSet();


  /** 
   * <em>limited_unit_m_short_2nd_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitedUnitMShort2ndSecIsModified();


  /** 
   * <em>limited_unit_cm_long_1st_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLimitedUnitCmLong1stSec();


  /** 
   * <em>limited_unit_cm_long_1st_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLimitedUnitCmLong1stSecIsNull();


  /** 
   * <em>limited_unit_cm_long_1st_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitedUnitCmLong1stSecIsSet();


  /** 
   * <em>limited_unit_cm_long_1st_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitedUnitCmLong1stSecIsModified();


  /** 
   * <em>limited_unit_cm_long_2nd_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLimitedUnitCmLong2ndSec();


  /** 
   * <em>limited_unit_cm_long_2nd_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLimitedUnitCmLong2ndSecIsNull();


  /** 
   * <em>limited_unit_cm_long_2nd_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitedUnitCmLong2ndSecIsSet();


  /** 
   * <em>limited_unit_cm_long_2nd_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitedUnitCmLong2ndSecIsModified();


  /** 
   * <em>limited_unit_cm_short_1st_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLimitedUnitCmShort1stSec();


  /** 
   * <em>limited_unit_cm_short_1st_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLimitedUnitCmShort1stSecIsNull();


  /** 
   * <em>limited_unit_cm_short_1st_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitedUnitCmShort1stSecIsSet();


  /** 
   * <em>limited_unit_cm_short_1st_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitedUnitCmShort1stSecIsModified();


  /** 
   * <em>limited_unit_cm_short_2nd_sec</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getLimitedUnitCmShort2ndSec();


  /** 
   * <em>limited_unit_cm_short_2nd_sec</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getLimitedUnitCmShort2ndSecIsNull();


  /** 
   * <em>limited_unit_cm_short_2nd_sec</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitedUnitCmShort2ndSecIsSet();


  /** 
   * <em>limited_unit_cm_short_2nd_sec</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLimitedUnitCmShort2ndSecIsModified();


  /** 
   * <em>cont_liquidate_term</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getContLiquidateTerm();


  /** 
   * <em>cont_liquidate_term</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getContLiquidateTermIsNull();


  /** 
   * <em>cont_liquidate_term</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContLiquidateTermIsSet();


  /** 
   * <em>cont_liquidate_term</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContLiquidateTermIsModified();


  /** 
   * <em>buy_interest_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getBuyInterestRate();


  /** 
   * <em>buy_interest_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBuyInterestRateIsNull();


  /** 
   * <em>buy_interest_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyInterestRateIsSet();


  /** 
   * <em>buy_interest_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyInterestRateIsModified();


  /** 
   * <em>sell_interest_rate</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getSellInterestRate();


  /** 
   * <em>sell_interest_rate</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSellInterestRateIsNull();


  /** 
   * <em>sell_interest_rate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellInterestRateIsSet();


  /** 
   * <em>sell_interest_rate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellInterestRateIsModified();


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
