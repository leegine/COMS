head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	MutualFundInstCondSonarRow.java;


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
 * MutualFundInstCondSonarRowインタフェースは変更不可でリードオンリーである<em>mutual_fund_inst_cond_sonar</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link MutualFundInstCondSonarRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MutualFundInstCondSonarPK 
 */
public interface MutualFundInstCondSonarRow extends Row {


  /** 
   * この{@@link MutualFundInstCondSonarRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "mutual_fund_inst_cond_sonar", "master" );


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
   * <em>reg_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRegType();


  /** 
   * <em>reg_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRegTypeIsSet();


  /** 
   * <em>reg_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRegTypeIsModified();


  /** 
   * <em>order_accept_limit_time_usual</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderAcceptLimitTimeUsual();


  /** 
   * <em>order_accept_limit_time_usual</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderAcceptLimitTimeUsualIsSet();


  /** 
   * <em>order_accept_limit_time_usual</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderAcceptLimitTimeUsualIsModified();


  /** 
   * <em>order_accept_limit_time_half</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderAcceptLimitTimeHalf();


  /** 
   * <em>order_accept_limit_time_half</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderAcceptLimitTimeHalfIsSet();


  /** 
   * <em>order_accept_limit_time_half</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderAcceptLimitTimeHalfIsModified();


  /** 
   * <em>buy_forbid_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuyForbidDiv();


  /** 
   * <em>buy_forbid_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyForbidDivIsSet();


  /** 
   * <em>buy_forbid_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyForbidDivIsModified();


  /** 
   * <em>buy_forbid_end_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBuyForbidEndDate();


  /** 
   * <em>buy_forbid_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyForbidEndDateIsSet();


  /** 
   * <em>buy_forbid_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBuyForbidEndDateIsModified();


  /** 
   * <em>sell_forbid_end_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSellForbidEndDate();


  /** 
   * <em>sell_forbid_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellForbidEndDateIsSet();


  /** 
   * <em>sell_forbid_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSellForbidEndDateIsModified();


}
@
