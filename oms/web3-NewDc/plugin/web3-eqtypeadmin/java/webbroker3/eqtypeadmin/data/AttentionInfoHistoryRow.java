head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AttentionInfoHistoryRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;

/** 
 * AttentionInfoHistoryRowインタフェースは変更不可でリードオンリーである<em>attention_info_history</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link AttentionInfoHistoryRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AttentionInfoHistoryPK 
 */
public interface AttentionInfoHistoryRow extends Row {


  /** 
   * この{@@link AttentionInfoHistoryRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "attention_info_history", "session" );


  /** 
   * <em>attention_info_history_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAttentionInfoHistoryId();


  /** 
   * <em>attention_info_history_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAttentionInfoHistoryIdIsSet();


  /** 
   * <em>attention_info_history_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAttentionInfoHistoryIdIsModified();


  /** 
   * <em>attention_info_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAttentionInfoType();


  /** 
   * <em>attention_info_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAttentionInfoTypeIsSet();


  /** 
   * <em>attention_info_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAttentionInfoTypeIsModified();


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
   * <em>product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getProductId();


  /** 
   * <em>product_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getProductIdIsNull();


  /** 
   * <em>product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductIdIsSet();


  /** 
   * <em>product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductIdIsModified();


  /** 
   * <em>standard_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStandardName();


  /** 
   * <em>standard_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStandardNameIsSet();


  /** 
   * <em>standard_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStandardNameIsModified();


  /** 
   * <em>market_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMarketId();


  /** 
   * <em>market_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getMarketIdIsNull();


  /** 
   * <em>market_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketIdIsSet();


  /** 
   * <em>market_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarketIdIsModified();


  /** 
   * <em>valid_until_biz_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getValidUntilBizDate();


  /** 
   * <em>valid_until_biz_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getValidUntilBizDateIsSet();


  /** 
   * <em>valid_until_biz_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getValidUntilBizDateIsModified();


  /** 
   * <em>attention_info_div_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAttentionInfoDivCode();


  /** 
   * <em>attention_info_div_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAttentionInfoDivCodeIsSet();


  /** 
   * <em>attention_info_div_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAttentionInfoDivCodeIsModified();


  /** 
   * <em>old_estimation_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOldEstimationPrice();


  /** 
   * <em>old_estimation_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOldEstimationPriceIsNull();


  /** 
   * <em>old_estimation_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldEstimationPriceIsSet();


  /** 
   * <em>old_estimation_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldEstimationPriceIsModified();


  /** 
   * <em>new_estimation_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getNewEstimationPrice();


  /** 
   * <em>new_estimation_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getNewEstimationPriceIsNull();


  /** 
   * <em>new_estimation_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewEstimationPriceIsSet();


  /** 
   * <em>new_estimation_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewEstimationPriceIsModified();


  /** 
   * <em>old_last_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOldLastClosingPrice();


  /** 
   * <em>old_last_closing_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOldLastClosingPriceIsNull();


  /** 
   * <em>old_last_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldLastClosingPriceIsSet();


  /** 
   * <em>old_last_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldLastClosingPriceIsModified();


  /** 
   * <em>new_last_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getNewLastClosingPrice();


  /** 
   * <em>new_last_closing_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getNewLastClosingPriceIsNull();


  /** 
   * <em>new_last_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewLastClosingPriceIsSet();


  /** 
   * <em>new_last_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewLastClosingPriceIsModified();


  /** 
   * <em>old_base_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOldBasePrice();


  /** 
   * <em>old_base_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOldBasePriceIsNull();


  /** 
   * <em>old_base_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldBasePriceIsSet();


  /** 
   * <em>old_base_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldBasePriceIsModified();


  /** 
   * <em>new_base_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getNewBasePrice();


  /** 
   * <em>new_base_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getNewBasePriceIsNull();


  /** 
   * <em>new_base_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewBasePriceIsSet();


  /** 
   * <em>new_base_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewBasePriceIsModified();


  /** 
   * <em>old_price_range_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOldPriceRangeType();


  /** 
   * <em>old_price_range_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldPriceRangeTypeIsSet();


  /** 
   * <em>old_price_range_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldPriceRangeTypeIsModified();


  /** 
   * <em>new_price_range_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNewPriceRangeType();


  /** 
   * <em>new_price_range_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewPriceRangeTypeIsSet();


  /** 
   * <em>new_price_range_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewPriceRangeTypeIsModified();


  /** 
   * <em>old_price_range_unit_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOldPriceRangeUnitType();


  /** 
   * <em>old_price_range_unit_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldPriceRangeUnitTypeIsSet();


  /** 
   * <em>old_price_range_unit_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldPriceRangeUnitTypeIsModified();


  /** 
   * <em>new_price_range_unit_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNewPriceRangeUnitType();


  /** 
   * <em>new_price_range_unit_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewPriceRangeUnitTypeIsSet();


  /** 
   * <em>new_price_range_unit_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewPriceRangeUnitTypeIsModified();


  /** 
   * <em>old_high_comp_price_range</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOldHighCompPriceRange();


  /** 
   * <em>old_high_comp_price_range</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOldHighCompPriceRangeIsNull();


  /** 
   * <em>old_high_comp_price_range</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldHighCompPriceRangeIsSet();


  /** 
   * <em>old_high_comp_price_range</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldHighCompPriceRangeIsModified();


  /** 
   * <em>new_high_price_range</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getNewHighPriceRange();


  /** 
   * <em>new_high_price_range</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getNewHighPriceRangeIsNull();


  /** 
   * <em>new_high_price_range</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewHighPriceRangeIsSet();


  /** 
   * <em>new_high_price_range</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewHighPriceRangeIsModified();


  /** 
   * <em>old_low_comp_price_range</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOldLowCompPriceRange();


  /** 
   * <em>old_low_comp_price_range</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOldLowCompPriceRangeIsNull();


  /** 
   * <em>old_low_comp_price_range</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldLowCompPriceRangeIsSet();


  /** 
   * <em>old_low_comp_price_range</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldLowCompPriceRangeIsModified();


  /** 
   * <em>new_low_price_range</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getNewLowPriceRange();


  /** 
   * <em>new_low_price_range</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getNewLowPriceRangeIsNull();


  /** 
   * <em>new_low_price_range</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewLowPriceRangeIsSet();


  /** 
   * <em>new_low_price_range</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewLowPriceRangeIsModified();


  /** 
   * <em>old_last_closing_price_updq</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOldLastClosingPriceUpdq();


  /** 
   * <em>old_last_closing_price_updq</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOldLastClosingPriceUpdqIsNull();


  /** 
   * <em>old_last_closing_price_updq</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldLastClosingPriceUpdqIsSet();


  /** 
   * <em>old_last_closing_price_updq</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldLastClosingPriceUpdqIsModified();


  /** 
   * <em>new_last_closing_price_updq</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getNewLastClosingPriceUpdq();


  /** 
   * <em>new_last_closing_price_updq</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getNewLastClosingPriceUpdqIsNull();


  /** 
   * <em>new_last_closing_price_updq</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewLastClosingPriceUpdqIsSet();


  /** 
   * <em>new_last_closing_price_updq</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewLastClosingPriceUpdqIsModified();


  /** 
   * <em>old_base_price_updq</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getOldBasePriceUpdq();


  /** 
   * <em>old_base_price_updq</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOldBasePriceUpdqIsNull();


  /** 
   * <em>old_base_price_updq</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldBasePriceUpdqIsSet();


  /** 
   * <em>old_base_price_updq</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOldBasePriceUpdqIsModified();


  /** 
   * <em>new_base_price_updq</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public double getNewBasePriceUpdq();


  /** 
   * <em>new_base_price_updq</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getNewBasePriceUpdqIsNull();


  /** 
   * <em>new_base_price_updq</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewBasePriceUpdqIsSet();


  /** 
   * <em>new_base_price_updq</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewBasePriceUpdqIsModified();


  /** 
   * <em>free_format_title</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFreeFormatTitle();


  /** 
   * <em>free_format_title</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFreeFormatTitleIsSet();


  /** 
   * <em>free_format_title</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFreeFormatTitleIsModified();


  /** 
   * <em>free_format_text</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFreeFormatText();


  /** 
   * <em>free_format_text</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFreeFormatTextIsSet();


  /** 
   * <em>free_format_text</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFreeFormatTextIsModified();


  /** 
   * <em>info_generation_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getInfoGenerationTimestamp();


  /** 
   * <em>info_generation_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInfoGenerationTimestampIsSet();


  /** 
   * <em>info_generation_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInfoGenerationTimestampIsModified();


  /** 
   * <em>ord_receipt_restart_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getOrdReceiptRestartTimestamp();


  /** 
   * <em>ord_receipt_restart_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrdReceiptRestartTimestampIsSet();


  /** 
   * <em>ord_receipt_restart_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrdReceiptRestartTimestampIsModified();


  /** 
   * <em>trade_stop_restart_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getTradeStopRestartTimestamp();


  /** 
   * <em>trade_stop_restart_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeStopRestartTimestampIsSet();


  /** 
   * <em>trade_stop_restart_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradeStopRestartTimestampIsModified();


  /** 
   * <em>process_result_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProcessResultDiv();


  /** 
   * <em>process_result_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProcessResultDivIsSet();


  /** 
   * <em>process_result_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProcessResultDivIsModified();


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
