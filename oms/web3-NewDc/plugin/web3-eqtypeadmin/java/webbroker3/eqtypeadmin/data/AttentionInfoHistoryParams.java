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
filename	AttentionInfoHistoryParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;

/** 
 * attention_info_historyテーブルのデータベースレコードの値変更に利用するパラメータを作成するユーティリティクラスです。 
 * <p> 
 * 通常、コンストラクタのうちの1つを利用して既存の{@@link AttentionInfoHistoryRow}オブジェクトからパラメータを作成します。 
 * 続いて、setterメソッドを利用して希望するフィールドの値を変更します。 
 * そして、{@@link AttentionInfoHistoryParams}オブジェクトを引数として渡し、新しいレコードを作成または存在するレコードを更新します。 
 * <p> 
 * {@@link AttentionInfoHistoryParams}が{@@link AttentionInfoHistoryRow}インタフェースを実装するので、このクラスでは各フィールドに対しgetterおよびsetterメソッドを提供しています。 
 * <p> 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AttentionInfoHistoryPK 
 * @@see AttentionInfoHistoryRow 
 */
public class AttentionInfoHistoryParams extends Params implements AttentionInfoHistoryRow {


  /** トップレベルメッセージの場合に利用されるタグ名 */
  public static final String TAGNAME = "row";


  /** 他のparamsと区別するためメッセージ内で利用されるポリモルフィックタイプ */
  public static final String PTYPE = "attention_info_history";


  /** クエリ内で利用される場合にrowをあらわすRowType */
  public static final RowType TYPE = AttentionInfoHistoryRow.TYPE;


  /** 
   * Paramsオブジェクトに紐付くテーブルのRowTypeオブジェクトを返します。 
   * @@return このParamsオブジェクトに紐付くテーブルのRowType 
   */
  public RowType getRowType() {
    return AttentionInfoHistoryRow.TYPE;
  }


  /** 
   * <em>attention_info_history_id</em>カラムの値 
   */
  public  long  attention_info_history_id;

  /** 
   * <em>attention_info_type</em>カラムの値 
   */
  public  String  attention_info_type;

  /** 
   * <em>institution_code</em>カラムの値 
   */
  public  String  institution_code;

  /** 
   * <em>product_id</em>カラムの値 
   */
  public  Long  product_id;

  /** 
   * <em>standard_name</em>カラムの値 
   */
  public  String  standard_name;

  /** 
   * <em>market_id</em>カラムの値 
   */
  public  Long  market_id;

  /** 
   * <em>valid_until_biz_date</em>カラムの値 
   */
  public  String  valid_until_biz_date;

  /** 
   * <em>attention_info_div_code</em>カラムの値 
   */
  public  String  attention_info_div_code;

  /** 
   * <em>old_estimation_price</em>カラムの値 
   */
  public  Double  old_estimation_price;

  /** 
   * <em>new_estimation_price</em>カラムの値 
   */
  public  Double  new_estimation_price;

  /** 
   * <em>old_last_closing_price</em>カラムの値 
   */
  public  Double  old_last_closing_price;

  /** 
   * <em>new_last_closing_price</em>カラムの値 
   */
  public  Double  new_last_closing_price;

  /** 
   * <em>old_base_price</em>カラムの値 
   */
  public  Double  old_base_price;

  /** 
   * <em>new_base_price</em>カラムの値 
   */
  public  Double  new_base_price;

  /** 
   * <em>old_price_range_type</em>カラムの値 
   */
  public  String  old_price_range_type;

  /** 
   * <em>new_price_range_type</em>カラムの値 
   */
  public  String  new_price_range_type;

  /** 
   * <em>old_price_range_unit_type</em>カラムの値 
   */
  public  String  old_price_range_unit_type;

  /** 
   * <em>new_price_range_unit_type</em>カラムの値 
   */
  public  String  new_price_range_unit_type;

  /** 
   * <em>old_high_comp_price_range</em>カラムの値 
   */
  public  Double  old_high_comp_price_range;

  /** 
   * <em>new_high_price_range</em>カラムの値 
   */
  public  Double  new_high_price_range;

  /** 
   * <em>old_low_comp_price_range</em>カラムの値 
   */
  public  Double  old_low_comp_price_range;

  /** 
   * <em>new_low_price_range</em>カラムの値 
   */
  public  Double  new_low_price_range;

  /** 
   * <em>old_last_closing_price_updq</em>カラムの値 
   */
  public  Double  old_last_closing_price_updq;

  /** 
   * <em>new_last_closing_price_updq</em>カラムの値 
   */
  public  Double  new_last_closing_price_updq;

  /** 
   * <em>old_base_price_updq</em>カラムの値 
   */
  public  Double  old_base_price_updq;

  /** 
   * <em>new_base_price_updq</em>カラムの値 
   */
  public  Double  new_base_price_updq;

  /** 
   * <em>free_format_title</em>カラムの値 
   */
  public  String  free_format_title;

  /** 
   * <em>free_format_text</em>カラムの値 
   */
  public  String  free_format_text;

  /** 
   * <em>info_generation_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  info_generation_timestamp;

  /** 
   * <em>ord_receipt_restart_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  ord_receipt_restart_timestamp;

  /** 
   * <em>trade_stop_restart_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  trade_stop_restart_timestamp;

  /** 
   * <em>process_result_div</em>カラムの値 
   */
  public  String  process_result_div;

  /** 
   * <em>created_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>カラムの値 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean attention_info_history_id_is_set = false;
  private boolean attention_info_history_id_is_modified = false;
  private boolean attention_info_type_is_set = false;
  private boolean attention_info_type_is_modified = false;
  private boolean institution_code_is_set = false;
  private boolean institution_code_is_modified = false;
  private boolean product_id_is_set = false;
  private boolean product_id_is_modified = false;
  private boolean standard_name_is_set = false;
  private boolean standard_name_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;
  private boolean valid_until_biz_date_is_set = false;
  private boolean valid_until_biz_date_is_modified = false;
  private boolean attention_info_div_code_is_set = false;
  private boolean attention_info_div_code_is_modified = false;
  private boolean old_estimation_price_is_set = false;
  private boolean old_estimation_price_is_modified = false;
  private boolean new_estimation_price_is_set = false;
  private boolean new_estimation_price_is_modified = false;
  private boolean old_last_closing_price_is_set = false;
  private boolean old_last_closing_price_is_modified = false;
  private boolean new_last_closing_price_is_set = false;
  private boolean new_last_closing_price_is_modified = false;
  private boolean old_base_price_is_set = false;
  private boolean old_base_price_is_modified = false;
  private boolean new_base_price_is_set = false;
  private boolean new_base_price_is_modified = false;
  private boolean old_price_range_type_is_set = false;
  private boolean old_price_range_type_is_modified = false;
  private boolean new_price_range_type_is_set = false;
  private boolean new_price_range_type_is_modified = false;
  private boolean old_price_range_unit_type_is_set = false;
  private boolean old_price_range_unit_type_is_modified = false;
  private boolean new_price_range_unit_type_is_set = false;
  private boolean new_price_range_unit_type_is_modified = false;
  private boolean old_high_comp_price_range_is_set = false;
  private boolean old_high_comp_price_range_is_modified = false;
  private boolean new_high_price_range_is_set = false;
  private boolean new_high_price_range_is_modified = false;
  private boolean old_low_comp_price_range_is_set = false;
  private boolean old_low_comp_price_range_is_modified = false;
  private boolean new_low_price_range_is_set = false;
  private boolean new_low_price_range_is_modified = false;
  private boolean old_last_closing_price_updq_is_set = false;
  private boolean old_last_closing_price_updq_is_modified = false;
  private boolean new_last_closing_price_updq_is_set = false;
  private boolean new_last_closing_price_updq_is_modified = false;
  private boolean old_base_price_updq_is_set = false;
  private boolean old_base_price_updq_is_modified = false;
  private boolean new_base_price_updq_is_set = false;
  private boolean new_base_price_updq_is_modified = false;
  private boolean free_format_title_is_set = false;
  private boolean free_format_title_is_modified = false;
  private boolean free_format_text_is_set = false;
  private boolean free_format_text_is_modified = false;
  private boolean info_generation_timestamp_is_set = false;
  private boolean info_generation_timestamp_is_modified = false;
  private boolean ord_receipt_restart_timestamp_is_set = false;
  private boolean ord_receipt_restart_timestamp_is_modified = false;
  private boolean trade_stop_restart_timestamp_is_set = false;
  private boolean trade_stop_restart_timestamp_is_modified = false;
  private boolean process_result_div_is_set = false;
  private boolean process_result_div_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * このオブジェクトをカンマ区切りの文字列表現で返します。 
   */
  public String toString() {
    return "{"
       + "attention_info_history_id=" + attention_info_history_id
      + "," + "attention_info_type=" +attention_info_type
      + "," + "institution_code=" +institution_code
      + "," + "product_id=" +product_id
      + "," + "standard_name=" +standard_name
      + "," + "market_id=" +market_id
      + "," + "valid_until_biz_date=" +valid_until_biz_date
      + "," + "attention_info_div_code=" +attention_info_div_code
      + "," + "old_estimation_price=" +old_estimation_price
      + "," + "new_estimation_price=" +new_estimation_price
      + "," + "old_last_closing_price=" +old_last_closing_price
      + "," + "new_last_closing_price=" +new_last_closing_price
      + "," + "old_base_price=" +old_base_price
      + "," + "new_base_price=" +new_base_price
      + "," + "old_price_range_type=" +old_price_range_type
      + "," + "new_price_range_type=" +new_price_range_type
      + "," + "old_price_range_unit_type=" +old_price_range_unit_type
      + "," + "new_price_range_unit_type=" +new_price_range_unit_type
      + "," + "old_high_comp_price_range=" +old_high_comp_price_range
      + "," + "new_high_price_range=" +new_high_price_range
      + "," + "old_low_comp_price_range=" +old_low_comp_price_range
      + "," + "new_low_price_range=" +new_low_price_range
      + "," + "old_last_closing_price_updq=" +old_last_closing_price_updq
      + "," + "new_last_closing_price_updq=" +new_last_closing_price_updq
      + "," + "old_base_price_updq=" +old_base_price_updq
      + "," + "new_base_price_updq=" +new_base_price_updq
      + "," + "free_format_title=" +free_format_title
      + "," + "free_format_text=" +free_format_text
      + "," + "info_generation_timestamp=" +info_generation_timestamp
      + "," + "ord_receipt_restart_timestamp=" +ord_receipt_restart_timestamp
      + "," + "trade_stop_restart_timestamp=" +trade_stop_restart_timestamp
      + "," + "process_result_div=" +process_result_div
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * 値が未設定のAttentionInfoHistoryParamsオブジェクトを作成します。 
   */
  public AttentionInfoHistoryParams() {
    attention_info_history_id_is_modified = true;
  }


  /** 
   * 指定のサブクラスリンクの値でオブジェクトを作成します。 
   * @@param tableName サブクラスリンクで利用するサブクラステーブル名 
   */


  /** 
   * 指定のAttentionInfoHistoryRowオブジェクトの値を利用してAttentionInfoHistoryParamsオブジェクトを作成します。 
   * @@param p_row テンプレートとして利用するAttentionInfoHistoryRowオブジェクト 
   */
  public AttentionInfoHistoryParams( AttentionInfoHistoryRow p_row )
  {
    attention_info_history_id = p_row.getAttentionInfoHistoryId();
    attention_info_history_id_is_set = p_row.getAttentionInfoHistoryIdIsSet();
    attention_info_history_id_is_modified = p_row.getAttentionInfoHistoryIdIsModified();
    attention_info_type = p_row.getAttentionInfoType();
    attention_info_type_is_set = p_row.getAttentionInfoTypeIsSet();
    attention_info_type_is_modified = p_row.getAttentionInfoTypeIsModified();
    institution_code = p_row.getInstitutionCode();
    institution_code_is_set = p_row.getInstitutionCodeIsSet();
    institution_code_is_modified = p_row.getInstitutionCodeIsModified();
    if ( !p_row.getProductIdIsNull() )
      product_id = new Long( p_row.getProductId() );
    product_id_is_set = p_row.getProductIdIsSet();
    product_id_is_modified = p_row.getProductIdIsModified();
    standard_name = p_row.getStandardName();
    standard_name_is_set = p_row.getStandardNameIsSet();
    standard_name_is_modified = p_row.getStandardNameIsModified();
    if ( !p_row.getMarketIdIsNull() )
      market_id = new Long( p_row.getMarketId() );
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
    valid_until_biz_date = p_row.getValidUntilBizDate();
    valid_until_biz_date_is_set = p_row.getValidUntilBizDateIsSet();
    valid_until_biz_date_is_modified = p_row.getValidUntilBizDateIsModified();
    attention_info_div_code = p_row.getAttentionInfoDivCode();
    attention_info_div_code_is_set = p_row.getAttentionInfoDivCodeIsSet();
    attention_info_div_code_is_modified = p_row.getAttentionInfoDivCodeIsModified();
    if ( !p_row.getOldEstimationPriceIsNull() )
      old_estimation_price = new Double( p_row.getOldEstimationPrice() );
    old_estimation_price_is_set = p_row.getOldEstimationPriceIsSet();
    old_estimation_price_is_modified = p_row.getOldEstimationPriceIsModified();
    if ( !p_row.getNewEstimationPriceIsNull() )
      new_estimation_price = new Double( p_row.getNewEstimationPrice() );
    new_estimation_price_is_set = p_row.getNewEstimationPriceIsSet();
    new_estimation_price_is_modified = p_row.getNewEstimationPriceIsModified();
    if ( !p_row.getOldLastClosingPriceIsNull() )
      old_last_closing_price = new Double( p_row.getOldLastClosingPrice() );
    old_last_closing_price_is_set = p_row.getOldLastClosingPriceIsSet();
    old_last_closing_price_is_modified = p_row.getOldLastClosingPriceIsModified();
    if ( !p_row.getNewLastClosingPriceIsNull() )
      new_last_closing_price = new Double( p_row.getNewLastClosingPrice() );
    new_last_closing_price_is_set = p_row.getNewLastClosingPriceIsSet();
    new_last_closing_price_is_modified = p_row.getNewLastClosingPriceIsModified();
    if ( !p_row.getOldBasePriceIsNull() )
      old_base_price = new Double( p_row.getOldBasePrice() );
    old_base_price_is_set = p_row.getOldBasePriceIsSet();
    old_base_price_is_modified = p_row.getOldBasePriceIsModified();
    if ( !p_row.getNewBasePriceIsNull() )
      new_base_price = new Double( p_row.getNewBasePrice() );
    new_base_price_is_set = p_row.getNewBasePriceIsSet();
    new_base_price_is_modified = p_row.getNewBasePriceIsModified();
    old_price_range_type = p_row.getOldPriceRangeType();
    old_price_range_type_is_set = p_row.getOldPriceRangeTypeIsSet();
    old_price_range_type_is_modified = p_row.getOldPriceRangeTypeIsModified();
    new_price_range_type = p_row.getNewPriceRangeType();
    new_price_range_type_is_set = p_row.getNewPriceRangeTypeIsSet();
    new_price_range_type_is_modified = p_row.getNewPriceRangeTypeIsModified();
    old_price_range_unit_type = p_row.getOldPriceRangeUnitType();
    old_price_range_unit_type_is_set = p_row.getOldPriceRangeUnitTypeIsSet();
    old_price_range_unit_type_is_modified = p_row.getOldPriceRangeUnitTypeIsModified();
    new_price_range_unit_type = p_row.getNewPriceRangeUnitType();
    new_price_range_unit_type_is_set = p_row.getNewPriceRangeUnitTypeIsSet();
    new_price_range_unit_type_is_modified = p_row.getNewPriceRangeUnitTypeIsModified();
    if ( !p_row.getOldHighCompPriceRangeIsNull() )
      old_high_comp_price_range = new Double( p_row.getOldHighCompPriceRange() );
    old_high_comp_price_range_is_set = p_row.getOldHighCompPriceRangeIsSet();
    old_high_comp_price_range_is_modified = p_row.getOldHighCompPriceRangeIsModified();
    if ( !p_row.getNewHighPriceRangeIsNull() )
      new_high_price_range = new Double( p_row.getNewHighPriceRange() );
    new_high_price_range_is_set = p_row.getNewHighPriceRangeIsSet();
    new_high_price_range_is_modified = p_row.getNewHighPriceRangeIsModified();
    if ( !p_row.getOldLowCompPriceRangeIsNull() )
      old_low_comp_price_range = new Double( p_row.getOldLowCompPriceRange() );
    old_low_comp_price_range_is_set = p_row.getOldLowCompPriceRangeIsSet();
    old_low_comp_price_range_is_modified = p_row.getOldLowCompPriceRangeIsModified();
    if ( !p_row.getNewLowPriceRangeIsNull() )
      new_low_price_range = new Double( p_row.getNewLowPriceRange() );
    new_low_price_range_is_set = p_row.getNewLowPriceRangeIsSet();
    new_low_price_range_is_modified = p_row.getNewLowPriceRangeIsModified();
    if ( !p_row.getOldLastClosingPriceUpdqIsNull() )
      old_last_closing_price_updq = new Double( p_row.getOldLastClosingPriceUpdq() );
    old_last_closing_price_updq_is_set = p_row.getOldLastClosingPriceUpdqIsSet();
    old_last_closing_price_updq_is_modified = p_row.getOldLastClosingPriceUpdqIsModified();
    if ( !p_row.getNewLastClosingPriceUpdqIsNull() )
      new_last_closing_price_updq = new Double( p_row.getNewLastClosingPriceUpdq() );
    new_last_closing_price_updq_is_set = p_row.getNewLastClosingPriceUpdqIsSet();
    new_last_closing_price_updq_is_modified = p_row.getNewLastClosingPriceUpdqIsModified();
    if ( !p_row.getOldBasePriceUpdqIsNull() )
      old_base_price_updq = new Double( p_row.getOldBasePriceUpdq() );
    old_base_price_updq_is_set = p_row.getOldBasePriceUpdqIsSet();
    old_base_price_updq_is_modified = p_row.getOldBasePriceUpdqIsModified();
    if ( !p_row.getNewBasePriceUpdqIsNull() )
      new_base_price_updq = new Double( p_row.getNewBasePriceUpdq() );
    new_base_price_updq_is_set = p_row.getNewBasePriceUpdqIsSet();
    new_base_price_updq_is_modified = p_row.getNewBasePriceUpdqIsModified();
    free_format_title = p_row.getFreeFormatTitle();
    free_format_title_is_set = p_row.getFreeFormatTitleIsSet();
    free_format_title_is_modified = p_row.getFreeFormatTitleIsModified();
    free_format_text = p_row.getFreeFormatText();
    free_format_text_is_set = p_row.getFreeFormatTextIsSet();
    free_format_text_is_modified = p_row.getFreeFormatTextIsModified();
    info_generation_timestamp = p_row.getInfoGenerationTimestamp();
    info_generation_timestamp_is_set = p_row.getInfoGenerationTimestampIsSet();
    info_generation_timestamp_is_modified = p_row.getInfoGenerationTimestampIsModified();
    ord_receipt_restart_timestamp = p_row.getOrdReceiptRestartTimestamp();
    ord_receipt_restart_timestamp_is_set = p_row.getOrdReceiptRestartTimestampIsSet();
    ord_receipt_restart_timestamp_is_modified = p_row.getOrdReceiptRestartTimestampIsModified();
    trade_stop_restart_timestamp = p_row.getTradeStopRestartTimestamp();
    trade_stop_restart_timestamp_is_set = p_row.getTradeStopRestartTimestampIsSet();
    trade_stop_restart_timestamp_is_modified = p_row.getTradeStopRestartTimestampIsModified();
    process_result_div = p_row.getProcessResultDiv();
    process_result_div_is_set = p_row.getProcessResultDivIsSet();
    process_result_div_is_modified = p_row.getProcessResultDivIsModified();
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
      attention_info_type_is_set = true;
      attention_info_type_is_modified = true;
      institution_code_is_set = true;
      institution_code_is_modified = true;
      product_id_is_set = true;
      product_id_is_modified = true;
      standard_name_is_set = true;
      standard_name_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
      valid_until_biz_date_is_set = true;
      valid_until_biz_date_is_modified = true;
      attention_info_div_code_is_set = true;
      attention_info_div_code_is_modified = true;
      old_estimation_price_is_set = true;
      old_estimation_price_is_modified = true;
      new_estimation_price_is_set = true;
      new_estimation_price_is_modified = true;
      old_last_closing_price_is_set = true;
      old_last_closing_price_is_modified = true;
      new_last_closing_price_is_set = true;
      new_last_closing_price_is_modified = true;
      old_base_price_is_set = true;
      old_base_price_is_modified = true;
      new_base_price_is_set = true;
      new_base_price_is_modified = true;
      old_price_range_type_is_set = true;
      old_price_range_type_is_modified = true;
      new_price_range_type_is_set = true;
      new_price_range_type_is_modified = true;
      old_price_range_unit_type_is_set = true;
      old_price_range_unit_type_is_modified = true;
      new_price_range_unit_type_is_set = true;
      new_price_range_unit_type_is_modified = true;
      old_high_comp_price_range_is_set = true;
      old_high_comp_price_range_is_modified = true;
      new_high_price_range_is_set = true;
      new_high_price_range_is_modified = true;
      old_low_comp_price_range_is_set = true;
      old_low_comp_price_range_is_modified = true;
      new_low_price_range_is_set = true;
      new_low_price_range_is_modified = true;
      old_last_closing_price_updq_is_set = true;
      old_last_closing_price_updq_is_modified = true;
      new_last_closing_price_updq_is_set = true;
      new_last_closing_price_updq_is_modified = true;
      old_base_price_updq_is_set = true;
      old_base_price_updq_is_modified = true;
      new_base_price_updq_is_set = true;
      new_base_price_updq_is_modified = true;
      free_format_title_is_set = true;
      free_format_title_is_modified = true;
      free_format_text_is_set = true;
      free_format_text_is_modified = true;
      info_generation_timestamp_is_set = true;
      info_generation_timestamp_is_modified = true;
      ord_receipt_restart_timestamp_is_set = true;
      ord_receipt_restart_timestamp_is_modified = true;
      trade_stop_restart_timestamp_is_set = true;
      trade_stop_restart_timestamp_is_modified = true;
      process_result_div_is_set = true;
      process_result_div_is_modified = true;
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
    if ( !( other instanceof AttentionInfoHistoryRow ) )
       return false;
    return fieldsEqual( (AttentionInfoHistoryRow) other );
  }


  /** 
   * 指定のRowオブジェクトとフィールド値がすべて一致しているかどうか調べます。 
   * @@param row 比較対照のAttentionInfoHistoryRow 
   * @@return すべてのフィールド値が一致の場合true、そうでなければfalse 
   */
  public boolean fieldsEqual( AttentionInfoHistoryRow row )
  {
    if ( attention_info_history_id != row.getAttentionInfoHistoryId() )
      return false;
    if ( attention_info_type == null ) {
      if ( row.getAttentionInfoType() != null )
        return false;
    } else if ( !attention_info_type.equals( row.getAttentionInfoType() ) ) {
        return false;
    }
    if ( institution_code == null ) {
      if ( row.getInstitutionCode() != null )
        return false;
    } else if ( !institution_code.equals( row.getInstitutionCode() ) ) {
        return false;
    }
    if ( product_id == null ) {
      if ( !row.getProductIdIsNull() )
        return false;
    } else if ( row.getProductIdIsNull() || ( product_id.longValue() != row.getProductId() ) ) {
        return false;
    }
    if ( standard_name == null ) {
      if ( row.getStandardName() != null )
        return false;
    } else if ( !standard_name.equals( row.getStandardName() ) ) {
        return false;
    }
    if ( market_id == null ) {
      if ( !row.getMarketIdIsNull() )
        return false;
    } else if ( row.getMarketIdIsNull() || ( market_id.longValue() != row.getMarketId() ) ) {
        return false;
    }
    if ( valid_until_biz_date == null ) {
      if ( row.getValidUntilBizDate() != null )
        return false;
    } else if ( !valid_until_biz_date.equals( row.getValidUntilBizDate() ) ) {
        return false;
    }
    if ( attention_info_div_code == null ) {
      if ( row.getAttentionInfoDivCode() != null )
        return false;
    } else if ( !attention_info_div_code.equals( row.getAttentionInfoDivCode() ) ) {
        return false;
    }
    if ( old_estimation_price == null ) {
      if ( !row.getOldEstimationPriceIsNull() )
        return false;
    } else if ( row.getOldEstimationPriceIsNull() || ( old_estimation_price.doubleValue() != row.getOldEstimationPrice() ) ) {
        return false;
    }
    if ( new_estimation_price == null ) {
      if ( !row.getNewEstimationPriceIsNull() )
        return false;
    } else if ( row.getNewEstimationPriceIsNull() || ( new_estimation_price.doubleValue() != row.getNewEstimationPrice() ) ) {
        return false;
    }
    if ( old_last_closing_price == null ) {
      if ( !row.getOldLastClosingPriceIsNull() )
        return false;
    } else if ( row.getOldLastClosingPriceIsNull() || ( old_last_closing_price.doubleValue() != row.getOldLastClosingPrice() ) ) {
        return false;
    }
    if ( new_last_closing_price == null ) {
      if ( !row.getNewLastClosingPriceIsNull() )
        return false;
    } else if ( row.getNewLastClosingPriceIsNull() || ( new_last_closing_price.doubleValue() != row.getNewLastClosingPrice() ) ) {
        return false;
    }
    if ( old_base_price == null ) {
      if ( !row.getOldBasePriceIsNull() )
        return false;
    } else if ( row.getOldBasePriceIsNull() || ( old_base_price.doubleValue() != row.getOldBasePrice() ) ) {
        return false;
    }
    if ( new_base_price == null ) {
      if ( !row.getNewBasePriceIsNull() )
        return false;
    } else if ( row.getNewBasePriceIsNull() || ( new_base_price.doubleValue() != row.getNewBasePrice() ) ) {
        return false;
    }
    if ( old_price_range_type == null ) {
      if ( row.getOldPriceRangeType() != null )
        return false;
    } else if ( !old_price_range_type.equals( row.getOldPriceRangeType() ) ) {
        return false;
    }
    if ( new_price_range_type == null ) {
      if ( row.getNewPriceRangeType() != null )
        return false;
    } else if ( !new_price_range_type.equals( row.getNewPriceRangeType() ) ) {
        return false;
    }
    if ( old_price_range_unit_type == null ) {
      if ( row.getOldPriceRangeUnitType() != null )
        return false;
    } else if ( !old_price_range_unit_type.equals( row.getOldPriceRangeUnitType() ) ) {
        return false;
    }
    if ( new_price_range_unit_type == null ) {
      if ( row.getNewPriceRangeUnitType() != null )
        return false;
    } else if ( !new_price_range_unit_type.equals( row.getNewPriceRangeUnitType() ) ) {
        return false;
    }
    if ( old_high_comp_price_range == null ) {
      if ( !row.getOldHighCompPriceRangeIsNull() )
        return false;
    } else if ( row.getOldHighCompPriceRangeIsNull() || ( old_high_comp_price_range.doubleValue() != row.getOldHighCompPriceRange() ) ) {
        return false;
    }
    if ( new_high_price_range == null ) {
      if ( !row.getNewHighPriceRangeIsNull() )
        return false;
    } else if ( row.getNewHighPriceRangeIsNull() || ( new_high_price_range.doubleValue() != row.getNewHighPriceRange() ) ) {
        return false;
    }
    if ( old_low_comp_price_range == null ) {
      if ( !row.getOldLowCompPriceRangeIsNull() )
        return false;
    } else if ( row.getOldLowCompPriceRangeIsNull() || ( old_low_comp_price_range.doubleValue() != row.getOldLowCompPriceRange() ) ) {
        return false;
    }
    if ( new_low_price_range == null ) {
      if ( !row.getNewLowPriceRangeIsNull() )
        return false;
    } else if ( row.getNewLowPriceRangeIsNull() || ( new_low_price_range.doubleValue() != row.getNewLowPriceRange() ) ) {
        return false;
    }
    if ( old_last_closing_price_updq == null ) {
      if ( !row.getOldLastClosingPriceUpdqIsNull() )
        return false;
    } else if ( row.getOldLastClosingPriceUpdqIsNull() || ( old_last_closing_price_updq.doubleValue() != row.getOldLastClosingPriceUpdq() ) ) {
        return false;
    }
    if ( new_last_closing_price_updq == null ) {
      if ( !row.getNewLastClosingPriceUpdqIsNull() )
        return false;
    } else if ( row.getNewLastClosingPriceUpdqIsNull() || ( new_last_closing_price_updq.doubleValue() != row.getNewLastClosingPriceUpdq() ) ) {
        return false;
    }
    if ( old_base_price_updq == null ) {
      if ( !row.getOldBasePriceUpdqIsNull() )
        return false;
    } else if ( row.getOldBasePriceUpdqIsNull() || ( old_base_price_updq.doubleValue() != row.getOldBasePriceUpdq() ) ) {
        return false;
    }
    if ( new_base_price_updq == null ) {
      if ( !row.getNewBasePriceUpdqIsNull() )
        return false;
    } else if ( row.getNewBasePriceUpdqIsNull() || ( new_base_price_updq.doubleValue() != row.getNewBasePriceUpdq() ) ) {
        return false;
    }
    if ( free_format_title == null ) {
      if ( row.getFreeFormatTitle() != null )
        return false;
    } else if ( !free_format_title.equals( row.getFreeFormatTitle() ) ) {
        return false;
    }
    if ( free_format_text == null ) {
      if ( row.getFreeFormatText() != null )
        return false;
    } else if ( !free_format_text.equals( row.getFreeFormatText() ) ) {
        return false;
    }
    if ( info_generation_timestamp == null ) {
      if ( row.getInfoGenerationTimestamp() != null )
        return false;
    } else if ( !info_generation_timestamp.equals( row.getInfoGenerationTimestamp() ) ) {
        return false;
    }
    if ( ord_receipt_restart_timestamp == null ) {
      if ( row.getOrdReceiptRestartTimestamp() != null )
        return false;
    } else if ( !ord_receipt_restart_timestamp.equals( row.getOrdReceiptRestartTimestamp() ) ) {
        return false;
    }
    if ( trade_stop_restart_timestamp == null ) {
      if ( row.getTradeStopRestartTimestamp() != null )
        return false;
    } else if ( !trade_stop_restart_timestamp.equals( row.getTradeStopRestartTimestamp() ) ) {
        return false;
    }
    if ( process_result_div == null ) {
      if ( row.getProcessResultDiv() != null )
        return false;
    } else if ( !process_result_div.equals( row.getProcessResultDiv() ) ) {
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
      return  ((int) attention_info_history_id)
        + (attention_info_type!=null? attention_info_type.hashCode(): 0) 
        + (institution_code!=null? institution_code.hashCode(): 0) 
        + (product_id!=null? product_id.hashCode(): 0) 
        + (standard_name!=null? standard_name.hashCode(): 0) 
        + (market_id!=null? market_id.hashCode(): 0) 
        + (valid_until_biz_date!=null? valid_until_biz_date.hashCode(): 0) 
        + (attention_info_div_code!=null? attention_info_div_code.hashCode(): 0) 
        + (old_estimation_price!=null? old_estimation_price.hashCode(): 0) 
        + (new_estimation_price!=null? new_estimation_price.hashCode(): 0) 
        + (old_last_closing_price!=null? old_last_closing_price.hashCode(): 0) 
        + (new_last_closing_price!=null? new_last_closing_price.hashCode(): 0) 
        + (old_base_price!=null? old_base_price.hashCode(): 0) 
        + (new_base_price!=null? new_base_price.hashCode(): 0) 
        + (old_price_range_type!=null? old_price_range_type.hashCode(): 0) 
        + (new_price_range_type!=null? new_price_range_type.hashCode(): 0) 
        + (old_price_range_unit_type!=null? old_price_range_unit_type.hashCode(): 0) 
        + (new_price_range_unit_type!=null? new_price_range_unit_type.hashCode(): 0) 
        + (old_high_comp_price_range!=null? old_high_comp_price_range.hashCode(): 0) 
        + (new_high_price_range!=null? new_high_price_range.hashCode(): 0) 
        + (old_low_comp_price_range!=null? old_low_comp_price_range.hashCode(): 0) 
        + (new_low_price_range!=null? new_low_price_range.hashCode(): 0) 
        + (old_last_closing_price_updq!=null? old_last_closing_price_updq.hashCode(): 0) 
        + (new_last_closing_price_updq!=null? new_last_closing_price_updq.hashCode(): 0) 
        + (old_base_price_updq!=null? old_base_price_updq.hashCode(): 0) 
        + (new_base_price_updq!=null? new_base_price_updq.hashCode(): 0) 
        + (free_format_title!=null? free_format_title.hashCode(): 0) 
        + (free_format_text!=null? free_format_text.hashCode(): 0) 
        + (info_generation_timestamp!=null? info_generation_timestamp.hashCode(): 0) 
        + (ord_receipt_restart_timestamp!=null? ord_receipt_restart_timestamp.hashCode(): 0) 
        + (trade_stop_restart_timestamp!=null? trade_stop_restart_timestamp.hashCode(): 0) 
        + (process_result_div!=null? process_result_div.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !attention_info_type_is_set )
			throw new IllegalArgumentException("non-nullable field 'attention_info_type' must be set before inserting.");
		if ( !institution_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'institution_code' must be set before inserting.");
		if ( !valid_until_biz_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'valid_until_biz_date' must be set before inserting.");
		if ( !attention_info_div_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'attention_info_div_code' must be set before inserting.");
		if ( !info_generation_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'info_generation_timestamp' must be set before inserting.");
		if ( !process_result_div_is_set )
			throw new IllegalArgumentException("non-nullable field 'process_result_div' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("attention_info_history_id",new Long(attention_info_history_id));
		map.put("attention_info_type",attention_info_type);
		map.put("institution_code",institution_code);
		if ( product_id != null )
			map.put("product_id",product_id);
		if ( standard_name != null )
			map.put("standard_name",standard_name);
		if ( market_id != null )
			map.put("market_id",market_id);
		map.put("valid_until_biz_date",valid_until_biz_date);
		map.put("attention_info_div_code",attention_info_div_code);
		if ( old_estimation_price != null )
			map.put("old_estimation_price",old_estimation_price);
		if ( new_estimation_price != null )
			map.put("new_estimation_price",new_estimation_price);
		if ( old_last_closing_price != null )
			map.put("old_last_closing_price",old_last_closing_price);
		if ( new_last_closing_price != null )
			map.put("new_last_closing_price",new_last_closing_price);
		if ( old_base_price != null )
			map.put("old_base_price",old_base_price);
		if ( new_base_price != null )
			map.put("new_base_price",new_base_price);
		if ( old_price_range_type != null )
			map.put("old_price_range_type",old_price_range_type);
		if ( new_price_range_type != null )
			map.put("new_price_range_type",new_price_range_type);
		if ( old_price_range_unit_type != null )
			map.put("old_price_range_unit_type",old_price_range_unit_type);
		if ( new_price_range_unit_type != null )
			map.put("new_price_range_unit_type",new_price_range_unit_type);
		if ( old_high_comp_price_range != null )
			map.put("old_high_comp_price_range",old_high_comp_price_range);
		if ( new_high_price_range != null )
			map.put("new_high_price_range",new_high_price_range);
		if ( old_low_comp_price_range != null )
			map.put("old_low_comp_price_range",old_low_comp_price_range);
		if ( new_low_price_range != null )
			map.put("new_low_price_range",new_low_price_range);
		if ( old_last_closing_price_updq != null )
			map.put("old_last_closing_price_updq",old_last_closing_price_updq);
		if ( new_last_closing_price_updq != null )
			map.put("new_last_closing_price_updq",new_last_closing_price_updq);
		if ( old_base_price_updq != null )
			map.put("old_base_price_updq",old_base_price_updq);
		if ( new_base_price_updq != null )
			map.put("new_base_price_updq",new_base_price_updq);
		if ( free_format_title != null )
			map.put("free_format_title",free_format_title);
		if ( free_format_text != null )
			map.put("free_format_text",free_format_text);
		map.put("info_generation_timestamp",info_generation_timestamp);
		if ( ord_receipt_restart_timestamp != null )
			map.put("ord_receipt_restart_timestamp",ord_receipt_restart_timestamp);
		if ( trade_stop_restart_timestamp != null )
			map.put("trade_stop_restart_timestamp",trade_stop_restart_timestamp);
		map.put("process_result_div",process_result_div);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( attention_info_type_is_modified )
			map.put("attention_info_type",attention_info_type);
		if ( institution_code_is_modified )
			map.put("institution_code",institution_code);
		if ( product_id_is_modified )
			map.put("product_id",product_id);
		if ( standard_name_is_modified )
			map.put("standard_name",standard_name);
		if ( market_id_is_modified )
			map.put("market_id",market_id);
		if ( valid_until_biz_date_is_modified )
			map.put("valid_until_biz_date",valid_until_biz_date);
		if ( attention_info_div_code_is_modified )
			map.put("attention_info_div_code",attention_info_div_code);
		if ( old_estimation_price_is_modified )
			map.put("old_estimation_price",old_estimation_price);
		if ( new_estimation_price_is_modified )
			map.put("new_estimation_price",new_estimation_price);
		if ( old_last_closing_price_is_modified )
			map.put("old_last_closing_price",old_last_closing_price);
		if ( new_last_closing_price_is_modified )
			map.put("new_last_closing_price",new_last_closing_price);
		if ( old_base_price_is_modified )
			map.put("old_base_price",old_base_price);
		if ( new_base_price_is_modified )
			map.put("new_base_price",new_base_price);
		if ( old_price_range_type_is_modified )
			map.put("old_price_range_type",old_price_range_type);
		if ( new_price_range_type_is_modified )
			map.put("new_price_range_type",new_price_range_type);
		if ( old_price_range_unit_type_is_modified )
			map.put("old_price_range_unit_type",old_price_range_unit_type);
		if ( new_price_range_unit_type_is_modified )
			map.put("new_price_range_unit_type",new_price_range_unit_type);
		if ( old_high_comp_price_range_is_modified )
			map.put("old_high_comp_price_range",old_high_comp_price_range);
		if ( new_high_price_range_is_modified )
			map.put("new_high_price_range",new_high_price_range);
		if ( old_low_comp_price_range_is_modified )
			map.put("old_low_comp_price_range",old_low_comp_price_range);
		if ( new_low_price_range_is_modified )
			map.put("new_low_price_range",new_low_price_range);
		if ( old_last_closing_price_updq_is_modified )
			map.put("old_last_closing_price_updq",old_last_closing_price_updq);
		if ( new_last_closing_price_updq_is_modified )
			map.put("new_last_closing_price_updq",new_last_closing_price_updq);
		if ( old_base_price_updq_is_modified )
			map.put("old_base_price_updq",old_base_price_updq);
		if ( new_base_price_updq_is_modified )
			map.put("new_base_price_updq",new_base_price_updq);
		if ( free_format_title_is_modified )
			map.put("free_format_title",free_format_title);
		if ( free_format_text_is_modified )
			map.put("free_format_text",free_format_text);
		if ( info_generation_timestamp_is_modified )
			map.put("info_generation_timestamp",info_generation_timestamp);
		if ( ord_receipt_restart_timestamp_is_modified )
			map.put("ord_receipt_restart_timestamp",ord_receipt_restart_timestamp);
		if ( trade_stop_restart_timestamp_is_modified )
			map.put("trade_stop_restart_timestamp",trade_stop_restart_timestamp);
		if ( process_result_div_is_modified )
			map.put("process_result_div",process_result_div);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( attention_info_type_is_set )
				map.put("attention_info_type",attention_info_type);
			if ( institution_code_is_set )
				map.put("institution_code",institution_code);
			map.put("product_id",product_id);
			map.put("standard_name",standard_name);
			map.put("market_id",market_id);
			if ( valid_until_biz_date_is_set )
				map.put("valid_until_biz_date",valid_until_biz_date);
			if ( attention_info_div_code_is_set )
				map.put("attention_info_div_code",attention_info_div_code);
			map.put("old_estimation_price",old_estimation_price);
			map.put("new_estimation_price",new_estimation_price);
			map.put("old_last_closing_price",old_last_closing_price);
			map.put("new_last_closing_price",new_last_closing_price);
			map.put("old_base_price",old_base_price);
			map.put("new_base_price",new_base_price);
			map.put("old_price_range_type",old_price_range_type);
			map.put("new_price_range_type",new_price_range_type);
			map.put("old_price_range_unit_type",old_price_range_unit_type);
			map.put("new_price_range_unit_type",new_price_range_unit_type);
			map.put("old_high_comp_price_range",old_high_comp_price_range);
			map.put("new_high_price_range",new_high_price_range);
			map.put("old_low_comp_price_range",old_low_comp_price_range);
			map.put("new_low_price_range",new_low_price_range);
			map.put("old_last_closing_price_updq",old_last_closing_price_updq);
			map.put("new_last_closing_price_updq",new_last_closing_price_updq);
			map.put("old_base_price_updq",old_base_price_updq);
			map.put("new_base_price_updq",new_base_price_updq);
			map.put("free_format_title",free_format_title);
			map.put("free_format_text",free_format_text);
			if ( info_generation_timestamp_is_set )
				map.put("info_generation_timestamp",info_generation_timestamp);
			map.put("ord_receipt_restart_timestamp",ord_receipt_restart_timestamp);
			map.put("trade_stop_restart_timestamp",trade_stop_restart_timestamp);
			if ( process_result_div_is_set )
				map.put("process_result_div",process_result_div);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>attention_info_history_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getAttentionInfoHistoryId()
  {
    return attention_info_history_id;
  }


  /** 
   * <em>attention_info_history_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAttentionInfoHistoryIdIsSet() {
    return attention_info_history_id_is_set;
  }


  /** 
   * <em>attention_info_history_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAttentionInfoHistoryIdIsModified() {
    return attention_info_history_id_is_modified;
  }


  /** 
   * <em>attention_info_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAttentionInfoType()
  {
    return attention_info_type;
  }


  /** 
   * <em>attention_info_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAttentionInfoTypeIsSet() {
    return attention_info_type_is_set;
  }


  /** 
   * <em>attention_info_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAttentionInfoTypeIsModified() {
    return attention_info_type_is_modified;
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
   * <em>product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getProductId()
  {
    return ( product_id==null? ((long)0): product_id.longValue() );
  }


  /** 
   * <em>product_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getProductIdIsNull()
  {
    return product_id == null;
  }


  /** 
   * <em>product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsSet() {
    return product_id_is_set;
  }


  /** 
   * <em>product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProductIdIsModified() {
    return product_id_is_modified;
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
   * <em>market_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public final long getMarketId()
  {
    return ( market_id==null? ((long)0): market_id.longValue() );
  }


  /** 
   * <em>market_id</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getMarketIdIsNull()
  {
    return market_id == null;
  }


  /** 
   * <em>market_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketIdIsSet() {
    return market_id_is_set;
  }


  /** 
   * <em>market_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getMarketIdIsModified() {
    return market_id_is_modified;
  }


  /** 
   * <em>valid_until_biz_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getValidUntilBizDate()
  {
    return valid_until_biz_date;
  }


  /** 
   * <em>valid_until_biz_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidUntilBizDateIsSet() {
    return valid_until_biz_date_is_set;
  }


  /** 
   * <em>valid_until_biz_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getValidUntilBizDateIsModified() {
    return valid_until_biz_date_is_modified;
  }


  /** 
   * <em>attention_info_div_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getAttentionInfoDivCode()
  {
    return attention_info_div_code;
  }


  /** 
   * <em>attention_info_div_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAttentionInfoDivCodeIsSet() {
    return attention_info_div_code_is_set;
  }


  /** 
   * <em>attention_info_div_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getAttentionInfoDivCodeIsModified() {
    return attention_info_div_code_is_modified;
  }


  /** 
   * <em>old_estimation_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOldEstimationPrice()
  {
    return ( old_estimation_price==null? ((double)0): old_estimation_price.doubleValue() );
  }


  /** 
   * <em>old_estimation_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOldEstimationPriceIsNull()
  {
    return old_estimation_price == null;
  }


  /** 
   * <em>old_estimation_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldEstimationPriceIsSet() {
    return old_estimation_price_is_set;
  }


  /** 
   * <em>old_estimation_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldEstimationPriceIsModified() {
    return old_estimation_price_is_modified;
  }


  /** 
   * <em>new_estimation_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getNewEstimationPrice()
  {
    return ( new_estimation_price==null? ((double)0): new_estimation_price.doubleValue() );
  }


  /** 
   * <em>new_estimation_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNewEstimationPriceIsNull()
  {
    return new_estimation_price == null;
  }


  /** 
   * <em>new_estimation_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewEstimationPriceIsSet() {
    return new_estimation_price_is_set;
  }


  /** 
   * <em>new_estimation_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewEstimationPriceIsModified() {
    return new_estimation_price_is_modified;
  }


  /** 
   * <em>old_last_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOldLastClosingPrice()
  {
    return ( old_last_closing_price==null? ((double)0): old_last_closing_price.doubleValue() );
  }


  /** 
   * <em>old_last_closing_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOldLastClosingPriceIsNull()
  {
    return old_last_closing_price == null;
  }


  /** 
   * <em>old_last_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldLastClosingPriceIsSet() {
    return old_last_closing_price_is_set;
  }


  /** 
   * <em>old_last_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldLastClosingPriceIsModified() {
    return old_last_closing_price_is_modified;
  }


  /** 
   * <em>new_last_closing_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getNewLastClosingPrice()
  {
    return ( new_last_closing_price==null? ((double)0): new_last_closing_price.doubleValue() );
  }


  /** 
   * <em>new_last_closing_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNewLastClosingPriceIsNull()
  {
    return new_last_closing_price == null;
  }


  /** 
   * <em>new_last_closing_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewLastClosingPriceIsSet() {
    return new_last_closing_price_is_set;
  }


  /** 
   * <em>new_last_closing_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewLastClosingPriceIsModified() {
    return new_last_closing_price_is_modified;
  }


  /** 
   * <em>old_base_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOldBasePrice()
  {
    return ( old_base_price==null? ((double)0): old_base_price.doubleValue() );
  }


  /** 
   * <em>old_base_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOldBasePriceIsNull()
  {
    return old_base_price == null;
  }


  /** 
   * <em>old_base_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldBasePriceIsSet() {
    return old_base_price_is_set;
  }


  /** 
   * <em>old_base_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldBasePriceIsModified() {
    return old_base_price_is_modified;
  }


  /** 
   * <em>new_base_price</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getNewBasePrice()
  {
    return ( new_base_price==null? ((double)0): new_base_price.doubleValue() );
  }


  /** 
   * <em>new_base_price</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNewBasePriceIsNull()
  {
    return new_base_price == null;
  }


  /** 
   * <em>new_base_price</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewBasePriceIsSet() {
    return new_base_price_is_set;
  }


  /** 
   * <em>new_base_price</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewBasePriceIsModified() {
    return new_base_price_is_modified;
  }


  /** 
   * <em>old_price_range_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOldPriceRangeType()
  {
    return old_price_range_type;
  }


  /** 
   * <em>old_price_range_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldPriceRangeTypeIsSet() {
    return old_price_range_type_is_set;
  }


  /** 
   * <em>old_price_range_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldPriceRangeTypeIsModified() {
    return old_price_range_type_is_modified;
  }


  /** 
   * <em>new_price_range_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNewPriceRangeType()
  {
    return new_price_range_type;
  }


  /** 
   * <em>new_price_range_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewPriceRangeTypeIsSet() {
    return new_price_range_type_is_set;
  }


  /** 
   * <em>new_price_range_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewPriceRangeTypeIsModified() {
    return new_price_range_type_is_modified;
  }


  /** 
   * <em>old_price_range_unit_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getOldPriceRangeUnitType()
  {
    return old_price_range_unit_type;
  }


  /** 
   * <em>old_price_range_unit_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldPriceRangeUnitTypeIsSet() {
    return old_price_range_unit_type_is_set;
  }


  /** 
   * <em>old_price_range_unit_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldPriceRangeUnitTypeIsModified() {
    return old_price_range_unit_type_is_modified;
  }


  /** 
   * <em>new_price_range_unit_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getNewPriceRangeUnitType()
  {
    return new_price_range_unit_type;
  }


  /** 
   * <em>new_price_range_unit_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewPriceRangeUnitTypeIsSet() {
    return new_price_range_unit_type_is_set;
  }


  /** 
   * <em>new_price_range_unit_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewPriceRangeUnitTypeIsModified() {
    return new_price_range_unit_type_is_modified;
  }


  /** 
   * <em>old_high_comp_price_range</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOldHighCompPriceRange()
  {
    return ( old_high_comp_price_range==null? ((double)0): old_high_comp_price_range.doubleValue() );
  }


  /** 
   * <em>old_high_comp_price_range</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOldHighCompPriceRangeIsNull()
  {
    return old_high_comp_price_range == null;
  }


  /** 
   * <em>old_high_comp_price_range</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldHighCompPriceRangeIsSet() {
    return old_high_comp_price_range_is_set;
  }


  /** 
   * <em>old_high_comp_price_range</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldHighCompPriceRangeIsModified() {
    return old_high_comp_price_range_is_modified;
  }


  /** 
   * <em>new_high_price_range</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getNewHighPriceRange()
  {
    return ( new_high_price_range==null? ((double)0): new_high_price_range.doubleValue() );
  }


  /** 
   * <em>new_high_price_range</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNewHighPriceRangeIsNull()
  {
    return new_high_price_range == null;
  }


  /** 
   * <em>new_high_price_range</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewHighPriceRangeIsSet() {
    return new_high_price_range_is_set;
  }


  /** 
   * <em>new_high_price_range</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewHighPriceRangeIsModified() {
    return new_high_price_range_is_modified;
  }


  /** 
   * <em>old_low_comp_price_range</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOldLowCompPriceRange()
  {
    return ( old_low_comp_price_range==null? ((double)0): old_low_comp_price_range.doubleValue() );
  }


  /** 
   * <em>old_low_comp_price_range</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOldLowCompPriceRangeIsNull()
  {
    return old_low_comp_price_range == null;
  }


  /** 
   * <em>old_low_comp_price_range</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldLowCompPriceRangeIsSet() {
    return old_low_comp_price_range_is_set;
  }


  /** 
   * <em>old_low_comp_price_range</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldLowCompPriceRangeIsModified() {
    return old_low_comp_price_range_is_modified;
  }


  /** 
   * <em>new_low_price_range</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getNewLowPriceRange()
  {
    return ( new_low_price_range==null? ((double)0): new_low_price_range.doubleValue() );
  }


  /** 
   * <em>new_low_price_range</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNewLowPriceRangeIsNull()
  {
    return new_low_price_range == null;
  }


  /** 
   * <em>new_low_price_range</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewLowPriceRangeIsSet() {
    return new_low_price_range_is_set;
  }


  /** 
   * <em>new_low_price_range</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewLowPriceRangeIsModified() {
    return new_low_price_range_is_modified;
  }


  /** 
   * <em>old_last_closing_price_updq</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOldLastClosingPriceUpdq()
  {
    return ( old_last_closing_price_updq==null? ((double)0): old_last_closing_price_updq.doubleValue() );
  }


  /** 
   * <em>old_last_closing_price_updq</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOldLastClosingPriceUpdqIsNull()
  {
    return old_last_closing_price_updq == null;
  }


  /** 
   * <em>old_last_closing_price_updq</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldLastClosingPriceUpdqIsSet() {
    return old_last_closing_price_updq_is_set;
  }


  /** 
   * <em>old_last_closing_price_updq</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldLastClosingPriceUpdqIsModified() {
    return old_last_closing_price_updq_is_modified;
  }


  /** 
   * <em>new_last_closing_price_updq</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getNewLastClosingPriceUpdq()
  {
    return ( new_last_closing_price_updq==null? ((double)0): new_last_closing_price_updq.doubleValue() );
  }


  /** 
   * <em>new_last_closing_price_updq</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNewLastClosingPriceUpdqIsNull()
  {
    return new_last_closing_price_updq == null;
  }


  /** 
   * <em>new_last_closing_price_updq</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewLastClosingPriceUpdqIsSet() {
    return new_last_closing_price_updq_is_set;
  }


  /** 
   * <em>new_last_closing_price_updq</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewLastClosingPriceUpdqIsModified() {
    return new_last_closing_price_updq_is_modified;
  }


  /** 
   * <em>old_base_price_updq</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getOldBasePriceUpdq()
  {
    return ( old_base_price_updq==null? ((double)0): old_base_price_updq.doubleValue() );
  }


  /** 
   * <em>old_base_price_updq</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getOldBasePriceUpdqIsNull()
  {
    return old_base_price_updq == null;
  }


  /** 
   * <em>old_base_price_updq</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldBasePriceUpdqIsSet() {
    return old_base_price_updq_is_set;
  }


  /** 
   * <em>old_base_price_updq</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOldBasePriceUpdqIsModified() {
    return old_base_price_updq_is_modified;
  }


  /** 
   * <em>new_base_price_updq</em>カラムの値を取得します。
   * @@return doubleの値 
   */
  public final double getNewBasePriceUpdq()
  {
    return ( new_base_price_updq==null? ((double)0): new_base_price_updq.doubleValue() );
  }


  /** 
   * <em>new_base_price_updq</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public final boolean getNewBasePriceUpdqIsNull()
  {
    return new_base_price_updq == null;
  }


  /** 
   * <em>new_base_price_updq</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewBasePriceUpdqIsSet() {
    return new_base_price_updq_is_set;
  }


  /** 
   * <em>new_base_price_updq</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getNewBasePriceUpdqIsModified() {
    return new_base_price_updq_is_modified;
  }


  /** 
   * <em>free_format_title</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFreeFormatTitle()
  {
    return free_format_title;
  }


  /** 
   * <em>free_format_title</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFreeFormatTitleIsSet() {
    return free_format_title_is_set;
  }


  /** 
   * <em>free_format_title</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFreeFormatTitleIsModified() {
    return free_format_title_is_modified;
  }


  /** 
   * <em>free_format_text</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getFreeFormatText()
  {
    return free_format_text;
  }


  /** 
   * <em>free_format_text</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFreeFormatTextIsSet() {
    return free_format_text_is_set;
  }


  /** 
   * <em>free_format_text</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getFreeFormatTextIsModified() {
    return free_format_text_is_modified;
  }


  /** 
   * <em>info_generation_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getInfoGenerationTimestamp()
  {
    return info_generation_timestamp;
  }


  /** 
   * <em>info_generation_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInfoGenerationTimestampIsSet() {
    return info_generation_timestamp_is_set;
  }


  /** 
   * <em>info_generation_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getInfoGenerationTimestampIsModified() {
    return info_generation_timestamp_is_modified;
  }


  /** 
   * <em>ord_receipt_restart_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getOrdReceiptRestartTimestamp()
  {
    return ord_receipt_restart_timestamp;
  }


  /** 
   * <em>ord_receipt_restart_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrdReceiptRestartTimestampIsSet() {
    return ord_receipt_restart_timestamp_is_set;
  }


  /** 
   * <em>ord_receipt_restart_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getOrdReceiptRestartTimestampIsModified() {
    return ord_receipt_restart_timestamp_is_modified;
  }


  /** 
   * <em>trade_stop_restart_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public final java.sql.Timestamp getTradeStopRestartTimestamp()
  {
    return trade_stop_restart_timestamp;
  }


  /** 
   * <em>trade_stop_restart_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeStopRestartTimestampIsSet() {
    return trade_stop_restart_timestamp_is_set;
  }


  /** 
   * <em>trade_stop_restart_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getTradeStopRestartTimestampIsModified() {
    return trade_stop_restart_timestamp_is_modified;
  }


  /** 
   * <em>process_result_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public final String getProcessResultDiv()
  {
    return process_result_div;
  }


  /** 
   * <em>process_result_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProcessResultDivIsSet() {
    return process_result_div_is_set;
  }


  /** 
   * <em>process_result_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public final boolean getProcessResultDivIsModified() {
    return process_result_div_is_modified;
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
    return new AttentionInfoHistoryPK(attention_info_history_id);
  }


  /** 
   * <em>attention_info_history_id</em>カラムの値を設定します。 
   *
   * @@param p_attentionInfoHistoryId <em>attention_info_history_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setAttentionInfoHistoryId( long p_attentionInfoHistoryId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    attention_info_history_id = p_attentionInfoHistoryId;
    attention_info_history_id_is_set = true;
    attention_info_history_id_is_modified = true;
  }


  /** 
   * <em>attention_info_type</em>カラムの値を設定します。 
   *
   * @@param p_attentionInfoType <em>attention_info_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setAttentionInfoType( String p_attentionInfoType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    attention_info_type = p_attentionInfoType;
    attention_info_type_is_set = true;
    attention_info_type_is_modified = true;
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
   * <em>product_id</em>カラムの値を設定します。 
   *
   * @@param p_productId <em>product_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setProductId( long p_productId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_id = new Long(p_productId);
    product_id_is_set = true;
    product_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>product_id</em>カラムに値を設定します。 
   */
  public final void setProductId( Long p_productId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    product_id = p_productId;
    product_id_is_set = true;
    product_id_is_modified = true;
  }


  /** 
   * <em>standard_name</em>カラムの値を設定します。 
   *
   * @@param p_standardName <em>standard_name</em>カラムの値をあらわす50桁以下のString値 
   */
  public final void setStandardName( String p_standardName )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    standard_name = p_standardName;
    standard_name_is_set = true;
    standard_name_is_modified = true;
  }


  /** 
   * <em>market_id</em>カラムの値を設定します。 
   *
   * @@param p_marketId <em>market_id</em>カラムの値をあらわす18桁以下のlong値 
   */
  public final void setMarketId( long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = new Long(p_marketId);
    market_id_is_set = true;
    market_id_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>market_id</em>カラムに値を設定します。 
   */
  public final void setMarketId( Long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = p_marketId;
    market_id_is_set = true;
    market_id_is_modified = true;
  }


  /** 
   * <em>valid_until_biz_date</em>カラムの値を設定します。 
   *
   * @@param p_validUntilBizDate <em>valid_until_biz_date</em>カラムの値をあらわす8桁以下のString値 
   */
  public final void setValidUntilBizDate( String p_validUntilBizDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    valid_until_biz_date = p_validUntilBizDate;
    valid_until_biz_date_is_set = true;
    valid_until_biz_date_is_modified = true;
  }


  /** 
   * <em>attention_info_div_code</em>カラムの値を設定します。 
   *
   * @@param p_attentionInfoDivCode <em>attention_info_div_code</em>カラムの値をあらわす4桁以下のString値 
   */
  public final void setAttentionInfoDivCode( String p_attentionInfoDivCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    attention_info_div_code = p_attentionInfoDivCode;
    attention_info_div_code_is_set = true;
    attention_info_div_code_is_modified = true;
  }


  /** 
   * <em>old_estimation_price</em>カラムの値を設定します。 
   *
   * @@param p_oldEstimationPrice <em>old_estimation_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOldEstimationPrice( double p_oldEstimationPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    old_estimation_price = new Double(p_oldEstimationPrice);
    old_estimation_price_is_set = true;
    old_estimation_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>old_estimation_price</em>カラムに値を設定します。 
   */
  public final void setOldEstimationPrice( Double p_oldEstimationPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    old_estimation_price = p_oldEstimationPrice;
    old_estimation_price_is_set = true;
    old_estimation_price_is_modified = true;
  }


  /** 
   * <em>new_estimation_price</em>カラムの値を設定します。 
   *
   * @@param p_newEstimationPrice <em>new_estimation_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setNewEstimationPrice( double p_newEstimationPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_estimation_price = new Double(p_newEstimationPrice);
    new_estimation_price_is_set = true;
    new_estimation_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>new_estimation_price</em>カラムに値を設定します。 
   */
  public final void setNewEstimationPrice( Double p_newEstimationPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    new_estimation_price = p_newEstimationPrice;
    new_estimation_price_is_set = true;
    new_estimation_price_is_modified = true;
  }


  /** 
   * <em>old_last_closing_price</em>カラムの値を設定します。 
   *
   * @@param p_oldLastClosingPrice <em>old_last_closing_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOldLastClosingPrice( double p_oldLastClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    old_last_closing_price = new Double(p_oldLastClosingPrice);
    old_last_closing_price_is_set = true;
    old_last_closing_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>old_last_closing_price</em>カラムに値を設定します。 
   */
  public final void setOldLastClosingPrice( Double p_oldLastClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    old_last_closing_price = p_oldLastClosingPrice;
    old_last_closing_price_is_set = true;
    old_last_closing_price_is_modified = true;
  }


  /** 
   * <em>new_last_closing_price</em>カラムの値を設定します。 
   *
   * @@param p_newLastClosingPrice <em>new_last_closing_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setNewLastClosingPrice( double p_newLastClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_last_closing_price = new Double(p_newLastClosingPrice);
    new_last_closing_price_is_set = true;
    new_last_closing_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>new_last_closing_price</em>カラムに値を設定します。 
   */
  public final void setNewLastClosingPrice( Double p_newLastClosingPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    new_last_closing_price = p_newLastClosingPrice;
    new_last_closing_price_is_set = true;
    new_last_closing_price_is_modified = true;
  }


  /** 
   * <em>old_base_price</em>カラムの値を設定します。 
   *
   * @@param p_oldBasePrice <em>old_base_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOldBasePrice( double p_oldBasePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    old_base_price = new Double(p_oldBasePrice);
    old_base_price_is_set = true;
    old_base_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>old_base_price</em>カラムに値を設定します。 
   */
  public final void setOldBasePrice( Double p_oldBasePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    old_base_price = p_oldBasePrice;
    old_base_price_is_set = true;
    old_base_price_is_modified = true;
  }


  /** 
   * <em>new_base_price</em>カラムの値を設定します。 
   *
   * @@param p_newBasePrice <em>new_base_price</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setNewBasePrice( double p_newBasePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_base_price = new Double(p_newBasePrice);
    new_base_price_is_set = true;
    new_base_price_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>new_base_price</em>カラムに値を設定します。 
   */
  public final void setNewBasePrice( Double p_newBasePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    new_base_price = p_newBasePrice;
    new_base_price_is_set = true;
    new_base_price_is_modified = true;
  }


  /** 
   * <em>old_price_range_type</em>カラムの値を設定します。 
   *
   * @@param p_oldPriceRangeType <em>old_price_range_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOldPriceRangeType( String p_oldPriceRangeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    old_price_range_type = p_oldPriceRangeType;
    old_price_range_type_is_set = true;
    old_price_range_type_is_modified = true;
  }


  /** 
   * <em>new_price_range_type</em>カラムの値を設定します。 
   *
   * @@param p_newPriceRangeType <em>new_price_range_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setNewPriceRangeType( String p_newPriceRangeType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_price_range_type = p_newPriceRangeType;
    new_price_range_type_is_set = true;
    new_price_range_type_is_modified = true;
  }


  /** 
   * <em>old_price_range_unit_type</em>カラムの値を設定します。 
   *
   * @@param p_oldPriceRangeUnitType <em>old_price_range_unit_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setOldPriceRangeUnitType( String p_oldPriceRangeUnitType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    old_price_range_unit_type = p_oldPriceRangeUnitType;
    old_price_range_unit_type_is_set = true;
    old_price_range_unit_type_is_modified = true;
  }


  /** 
   * <em>new_price_range_unit_type</em>カラムの値を設定します。 
   *
   * @@param p_newPriceRangeUnitType <em>new_price_range_unit_type</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setNewPriceRangeUnitType( String p_newPriceRangeUnitType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_price_range_unit_type = p_newPriceRangeUnitType;
    new_price_range_unit_type_is_set = true;
    new_price_range_unit_type_is_modified = true;
  }


  /** 
   * <em>old_high_comp_price_range</em>カラムの値を設定します。 
   *
   * @@param p_oldHighCompPriceRange <em>old_high_comp_price_range</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOldHighCompPriceRange( double p_oldHighCompPriceRange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    old_high_comp_price_range = new Double(p_oldHighCompPriceRange);
    old_high_comp_price_range_is_set = true;
    old_high_comp_price_range_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>old_high_comp_price_range</em>カラムに値を設定します。 
   */
  public final void setOldHighCompPriceRange( Double p_oldHighCompPriceRange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    old_high_comp_price_range = p_oldHighCompPriceRange;
    old_high_comp_price_range_is_set = true;
    old_high_comp_price_range_is_modified = true;
  }


  /** 
   * <em>new_high_price_range</em>カラムの値を設定します。 
   *
   * @@param p_newHighPriceRange <em>new_high_price_range</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setNewHighPriceRange( double p_newHighPriceRange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_high_price_range = new Double(p_newHighPriceRange);
    new_high_price_range_is_set = true;
    new_high_price_range_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>new_high_price_range</em>カラムに値を設定します。 
   */
  public final void setNewHighPriceRange( Double p_newHighPriceRange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    new_high_price_range = p_newHighPriceRange;
    new_high_price_range_is_set = true;
    new_high_price_range_is_modified = true;
  }


  /** 
   * <em>old_low_comp_price_range</em>カラムの値を設定します。 
   *
   * @@param p_oldLowCompPriceRange <em>old_low_comp_price_range</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOldLowCompPriceRange( double p_oldLowCompPriceRange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    old_low_comp_price_range = new Double(p_oldLowCompPriceRange);
    old_low_comp_price_range_is_set = true;
    old_low_comp_price_range_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>old_low_comp_price_range</em>カラムに値を設定します。 
   */
  public final void setOldLowCompPriceRange( Double p_oldLowCompPriceRange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    old_low_comp_price_range = p_oldLowCompPriceRange;
    old_low_comp_price_range_is_set = true;
    old_low_comp_price_range_is_modified = true;
  }


  /** 
   * <em>new_low_price_range</em>カラムの値を設定します。 
   *
   * @@param p_newLowPriceRange <em>new_low_price_range</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setNewLowPriceRange( double p_newLowPriceRange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_low_price_range = new Double(p_newLowPriceRange);
    new_low_price_range_is_set = true;
    new_low_price_range_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>new_low_price_range</em>カラムに値を設定します。 
   */
  public final void setNewLowPriceRange( Double p_newLowPriceRange )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    new_low_price_range = p_newLowPriceRange;
    new_low_price_range_is_set = true;
    new_low_price_range_is_modified = true;
  }


  /** 
   * <em>old_last_closing_price_updq</em>カラムの値を設定します。 
   *
   * @@param p_oldLastClosingPriceUpdq <em>old_last_closing_price_updq</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOldLastClosingPriceUpdq( double p_oldLastClosingPriceUpdq )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    old_last_closing_price_updq = new Double(p_oldLastClosingPriceUpdq);
    old_last_closing_price_updq_is_set = true;
    old_last_closing_price_updq_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>old_last_closing_price_updq</em>カラムに値を設定します。 
   */
  public final void setOldLastClosingPriceUpdq( Double p_oldLastClosingPriceUpdq )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    old_last_closing_price_updq = p_oldLastClosingPriceUpdq;
    old_last_closing_price_updq_is_set = true;
    old_last_closing_price_updq_is_modified = true;
  }


  /** 
   * <em>new_last_closing_price_updq</em>カラムの値を設定します。 
   *
   * @@param p_newLastClosingPriceUpdq <em>new_last_closing_price_updq</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setNewLastClosingPriceUpdq( double p_newLastClosingPriceUpdq )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_last_closing_price_updq = new Double(p_newLastClosingPriceUpdq);
    new_last_closing_price_updq_is_set = true;
    new_last_closing_price_updq_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>new_last_closing_price_updq</em>カラムに値を設定します。 
   */
  public final void setNewLastClosingPriceUpdq( Double p_newLastClosingPriceUpdq )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    new_last_closing_price_updq = p_newLastClosingPriceUpdq;
    new_last_closing_price_updq_is_set = true;
    new_last_closing_price_updq_is_modified = true;
  }


  /** 
   * <em>old_base_price_updq</em>カラムの値を設定します。 
   *
   * @@param p_oldBasePriceUpdq <em>old_base_price_updq</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setOldBasePriceUpdq( double p_oldBasePriceUpdq )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    old_base_price_updq = new Double(p_oldBasePriceUpdq);
    old_base_price_updq_is_set = true;
    old_base_price_updq_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>old_base_price_updq</em>カラムに値を設定します。 
   */
  public final void setOldBasePriceUpdq( Double p_oldBasePriceUpdq )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    old_base_price_updq = p_oldBasePriceUpdq;
    old_base_price_updq_is_set = true;
    old_base_price_updq_is_modified = true;
  }


  /** 
   * <em>new_base_price_updq</em>カラムの値を設定します。 
   *
   * @@param p_newBasePriceUpdq <em>new_base_price_updq</em>カラムの値をあらわす18桁以下のdouble値で、その精度は6桁まで
   */
  public final void setNewBasePriceUpdq( double p_newBasePriceUpdq )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    new_base_price_updq = new Double(p_newBasePriceUpdq);
    new_base_price_updq_is_set = true;
    new_base_price_updq_is_modified = true;
  }


  /** 
   * 指定のオブジェクト（場合によってはnull）を使って<em>new_base_price_updq</em>カラムに値を設定します。 
   */
  public final void setNewBasePriceUpdq( Double p_newBasePriceUpdq )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    new_base_price_updq = p_newBasePriceUpdq;
    new_base_price_updq_is_set = true;
    new_base_price_updq_is_modified = true;
  }


  /** 
   * <em>free_format_title</em>カラムの値を設定します。 
   *
   * @@param p_freeFormatTitle <em>free_format_title</em>カラムの値をあらわす60桁以下のString値 
   */
  public final void setFreeFormatTitle( String p_freeFormatTitle )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    free_format_title = p_freeFormatTitle;
    free_format_title_is_set = true;
    free_format_title_is_modified = true;
  }


  /** 
   * <em>free_format_text</em>カラムの値を設定します。 
   *
   * @@param p_freeFormatText <em>free_format_text</em>カラムの値をあらわす600桁以下のString値 
   */
  public final void setFreeFormatText( String p_freeFormatText )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    free_format_text = p_freeFormatText;
    free_format_text_is_set = true;
    free_format_text_is_modified = true;
  }


  /** 
   * <em>info_generation_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_infoGenerationTimestamp <em>info_generation_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setInfoGenerationTimestamp( java.sql.Timestamp p_infoGenerationTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    info_generation_timestamp = p_infoGenerationTimestamp;
    info_generation_timestamp_is_set = true;
    info_generation_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>info_generation_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setInfoGenerationTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    info_generation_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    info_generation_timestamp_is_set = true;
    info_generation_timestamp_is_modified = true;
  }


  /** 
   * <em>ord_receipt_restart_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_ordReceiptRestartTimestamp <em>ord_receipt_restart_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setOrdReceiptRestartTimestamp( java.sql.Timestamp p_ordReceiptRestartTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ord_receipt_restart_timestamp = p_ordReceiptRestartTimestamp;
    ord_receipt_restart_timestamp_is_set = true;
    ord_receipt_restart_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>ord_receipt_restart_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setOrdReceiptRestartTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ord_receipt_restart_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    ord_receipt_restart_timestamp_is_set = true;
    ord_receipt_restart_timestamp_is_modified = true;
  }


  /** 
   * <em>trade_stop_restart_timestamp</em>カラムの値を設定します。 
   *
   * @@param p_tradeStopRestartTimestamp <em>trade_stop_restart_timestamp</em>カラムの値をあらわすjava.sql.Timestamp値
   */
  public final void setTradeStopRestartTimestamp( java.sql.Timestamp p_tradeStopRestartTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    trade_stop_restart_timestamp = p_tradeStopRestartTimestamp;
    trade_stop_restart_timestamp_is_set = true;
    trade_stop_restart_timestamp_is_modified = true;
  }


   /** 
   * 一般のjava.util.Dateオブジェクトを使って<em>trade_stop_restart_timestamp</em>カラムに値を設定します。 
   * @@param date 設定する値を含むjava.util.Date 
    */
  public final void setTradeStopRestartTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    trade_stop_restart_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    trade_stop_restart_timestamp_is_set = true;
    trade_stop_restart_timestamp_is_modified = true;
  }


  /** 
   * <em>process_result_div</em>カラムの値を設定します。 
   *
   * @@param p_processResultDiv <em>process_result_div</em>カラムの値をあらわす1桁以下のString値 
   */
  public final void setProcessResultDiv( String p_processResultDiv )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    process_result_div = p_processResultDiv;
    process_result_div_is_set = true;
    process_result_div_is_modified = true;
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
                if ( name.equals("attention_info_history_id") ) {
                    return new Long( attention_info_history_id );
                }
                else if ( name.equals("attention_info_type") ) {
                    return this.attention_info_type;
                }
                else if ( name.equals("attention_info_div_code") ) {
                    return this.attention_info_div_code;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("free_format_title") ) {
                    return this.free_format_title;
                }
                else if ( name.equals("free_format_text") ) {
                    return this.free_format_text;
                }
                break;
            case 'i':
                if ( name.equals("institution_code") ) {
                    return this.institution_code;
                }
                else if ( name.equals("info_generation_timestamp") ) {
                    return this.info_generation_timestamp;
                }
                break;
            case 'l':
                if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return this.market_id;
                }
                break;
            case 'n':
                if ( name.equals("new_estimation_price") ) {
                    return this.new_estimation_price;
                }
                else if ( name.equals("new_last_closing_price") ) {
                    return this.new_last_closing_price;
                }
                else if ( name.equals("new_base_price") ) {
                    return this.new_base_price;
                }
                else if ( name.equals("new_price_range_type") ) {
                    return this.new_price_range_type;
                }
                else if ( name.equals("new_price_range_unit_type") ) {
                    return this.new_price_range_unit_type;
                }
                else if ( name.equals("new_high_price_range") ) {
                    return this.new_high_price_range;
                }
                else if ( name.equals("new_low_price_range") ) {
                    return this.new_low_price_range;
                }
                else if ( name.equals("new_last_closing_price_updq") ) {
                    return this.new_last_closing_price_updq;
                }
                else if ( name.equals("new_base_price_updq") ) {
                    return this.new_base_price_updq;
                }
                break;
            case 'o':
                if ( name.equals("old_estimation_price") ) {
                    return this.old_estimation_price;
                }
                else if ( name.equals("old_last_closing_price") ) {
                    return this.old_last_closing_price;
                }
                else if ( name.equals("old_base_price") ) {
                    return this.old_base_price;
                }
                else if ( name.equals("old_price_range_type") ) {
                    return this.old_price_range_type;
                }
                else if ( name.equals("old_price_range_unit_type") ) {
                    return this.old_price_range_unit_type;
                }
                else if ( name.equals("old_high_comp_price_range") ) {
                    return this.old_high_comp_price_range;
                }
                else if ( name.equals("old_low_comp_price_range") ) {
                    return this.old_low_comp_price_range;
                }
                else if ( name.equals("old_last_closing_price_updq") ) {
                    return this.old_last_closing_price_updq;
                }
                else if ( name.equals("old_base_price_updq") ) {
                    return this.old_base_price_updq;
                }
                else if ( name.equals("ord_receipt_restart_timestamp") ) {
                    return this.ord_receipt_restart_timestamp;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    return this.product_id;
                }
                else if ( name.equals("process_result_div") ) {
                    return this.process_result_div;
                }
                break;
            case 's':
                if ( name.equals("standard_name") ) {
                    return this.standard_name;
                }
                break;
            case 't':
                if ( name.equals("trade_stop_restart_timestamp") ) {
                    return this.trade_stop_restart_timestamp;
                }
                break;
            case 'v':
                if ( name.equals("valid_until_biz_date") ) {
                    return this.valid_until_biz_date;
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
                if ( name.equals("attention_info_history_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'attention_info_history_id' must be Long: '"+value+"' is not." );
                    this.attention_info_history_id = ((Long) value).longValue();
                    if (this.attention_info_history_id_is_set)
                        this.attention_info_history_id_is_modified = true;
                    this.attention_info_history_id_is_set = true;
                    return;
                }
                else if ( name.equals("attention_info_type") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'attention_info_type' must be String: '"+value+"' is not." );
                    this.attention_info_type = (String) value;
                    if (this.attention_info_type_is_set)
                        this.attention_info_type_is_modified = true;
                    this.attention_info_type_is_set = true;
                    return;
                }
                else if ( name.equals("attention_info_div_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'attention_info_div_code' must be String: '"+value+"' is not." );
                    this.attention_info_div_code = (String) value;
                    if (this.attention_info_div_code_is_set)
                        this.attention_info_div_code_is_modified = true;
                    this.attention_info_div_code_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("free_format_title") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'free_format_title' must be String: '"+value+"' is not." );
                    this.free_format_title = (String) value;
                    if (this.free_format_title_is_set)
                        this.free_format_title_is_modified = true;
                    this.free_format_title_is_set = true;
                    return;
                }
                else if ( name.equals("free_format_text") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'free_format_text' must be String: '"+value+"' is not." );
                    this.free_format_text = (String) value;
                    if (this.free_format_text_is_set)
                        this.free_format_text_is_modified = true;
                    this.free_format_text_is_set = true;
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
                else if ( name.equals("info_generation_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'info_generation_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.info_generation_timestamp = (java.sql.Timestamp) value;
                    if (this.info_generation_timestamp_is_set)
                        this.info_generation_timestamp_is_modified = true;
                    this.info_generation_timestamp_is_set = true;
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
            case 'm':
                if ( name.equals("market_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_id' must be Long: '"+value+"' is not." );
                    this.market_id = (Long) value;
                    if (this.market_id_is_set)
                        this.market_id_is_modified = true;
                    this.market_id_is_set = true;
                    return;
                }
                break;
            case 'n':
                if ( name.equals("new_estimation_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'new_estimation_price' must be Double: '"+value+"' is not." );
                    this.new_estimation_price = (Double) value;
                    if (this.new_estimation_price_is_set)
                        this.new_estimation_price_is_modified = true;
                    this.new_estimation_price_is_set = true;
                    return;
                }
                else if ( name.equals("new_last_closing_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'new_last_closing_price' must be Double: '"+value+"' is not." );
                    this.new_last_closing_price = (Double) value;
                    if (this.new_last_closing_price_is_set)
                        this.new_last_closing_price_is_modified = true;
                    this.new_last_closing_price_is_set = true;
                    return;
                }
                else if ( name.equals("new_base_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'new_base_price' must be Double: '"+value+"' is not." );
                    this.new_base_price = (Double) value;
                    if (this.new_base_price_is_set)
                        this.new_base_price_is_modified = true;
                    this.new_base_price_is_set = true;
                    return;
                }
                else if ( name.equals("new_price_range_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'new_price_range_type' must be String: '"+value+"' is not." );
                    this.new_price_range_type = (String) value;
                    if (this.new_price_range_type_is_set)
                        this.new_price_range_type_is_modified = true;
                    this.new_price_range_type_is_set = true;
                    return;
                }
                else if ( name.equals("new_price_range_unit_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'new_price_range_unit_type' must be String: '"+value+"' is not." );
                    this.new_price_range_unit_type = (String) value;
                    if (this.new_price_range_unit_type_is_set)
                        this.new_price_range_unit_type_is_modified = true;
                    this.new_price_range_unit_type_is_set = true;
                    return;
                }
                else if ( name.equals("new_high_price_range") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'new_high_price_range' must be Double: '"+value+"' is not." );
                    this.new_high_price_range = (Double) value;
                    if (this.new_high_price_range_is_set)
                        this.new_high_price_range_is_modified = true;
                    this.new_high_price_range_is_set = true;
                    return;
                }
                else if ( name.equals("new_low_price_range") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'new_low_price_range' must be Double: '"+value+"' is not." );
                    this.new_low_price_range = (Double) value;
                    if (this.new_low_price_range_is_set)
                        this.new_low_price_range_is_modified = true;
                    this.new_low_price_range_is_set = true;
                    return;
                }
                else if ( name.equals("new_last_closing_price_updq") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'new_last_closing_price_updq' must be Double: '"+value+"' is not." );
                    this.new_last_closing_price_updq = (Double) value;
                    if (this.new_last_closing_price_updq_is_set)
                        this.new_last_closing_price_updq_is_modified = true;
                    this.new_last_closing_price_updq_is_set = true;
                    return;
                }
                else if ( name.equals("new_base_price_updq") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'new_base_price_updq' must be Double: '"+value+"' is not." );
                    this.new_base_price_updq = (Double) value;
                    if (this.new_base_price_updq_is_set)
                        this.new_base_price_updq_is_modified = true;
                    this.new_base_price_updq_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("old_estimation_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'old_estimation_price' must be Double: '"+value+"' is not." );
                    this.old_estimation_price = (Double) value;
                    if (this.old_estimation_price_is_set)
                        this.old_estimation_price_is_modified = true;
                    this.old_estimation_price_is_set = true;
                    return;
                }
                else if ( name.equals("old_last_closing_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'old_last_closing_price' must be Double: '"+value+"' is not." );
                    this.old_last_closing_price = (Double) value;
                    if (this.old_last_closing_price_is_set)
                        this.old_last_closing_price_is_modified = true;
                    this.old_last_closing_price_is_set = true;
                    return;
                }
                else if ( name.equals("old_base_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'old_base_price' must be Double: '"+value+"' is not." );
                    this.old_base_price = (Double) value;
                    if (this.old_base_price_is_set)
                        this.old_base_price_is_modified = true;
                    this.old_base_price_is_set = true;
                    return;
                }
                else if ( name.equals("old_price_range_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'old_price_range_type' must be String: '"+value+"' is not." );
                    this.old_price_range_type = (String) value;
                    if (this.old_price_range_type_is_set)
                        this.old_price_range_type_is_modified = true;
                    this.old_price_range_type_is_set = true;
                    return;
                }
                else if ( name.equals("old_price_range_unit_type") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'old_price_range_unit_type' must be String: '"+value+"' is not." );
                    this.old_price_range_unit_type = (String) value;
                    if (this.old_price_range_unit_type_is_set)
                        this.old_price_range_unit_type_is_modified = true;
                    this.old_price_range_unit_type_is_set = true;
                    return;
                }
                else if ( name.equals("old_high_comp_price_range") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'old_high_comp_price_range' must be Double: '"+value+"' is not." );
                    this.old_high_comp_price_range = (Double) value;
                    if (this.old_high_comp_price_range_is_set)
                        this.old_high_comp_price_range_is_modified = true;
                    this.old_high_comp_price_range_is_set = true;
                    return;
                }
                else if ( name.equals("old_low_comp_price_range") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'old_low_comp_price_range' must be Double: '"+value+"' is not." );
                    this.old_low_comp_price_range = (Double) value;
                    if (this.old_low_comp_price_range_is_set)
                        this.old_low_comp_price_range_is_modified = true;
                    this.old_low_comp_price_range_is_set = true;
                    return;
                }
                else if ( name.equals("old_last_closing_price_updq") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'old_last_closing_price_updq' must be Double: '"+value+"' is not." );
                    this.old_last_closing_price_updq = (Double) value;
                    if (this.old_last_closing_price_updq_is_set)
                        this.old_last_closing_price_updq_is_modified = true;
                    this.old_last_closing_price_updq_is_set = true;
                    return;
                }
                else if ( name.equals("old_base_price_updq") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'old_base_price_updq' must be Double: '"+value+"' is not." );
                    this.old_base_price_updq = (Double) value;
                    if (this.old_base_price_updq_is_set)
                        this.old_base_price_updq_is_modified = true;
                    this.old_base_price_updq_is_set = true;
                    return;
                }
                else if ( name.equals("ord_receipt_restart_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'ord_receipt_restart_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.ord_receipt_restart_timestamp = (java.sql.Timestamp) value;
                    if (this.ord_receipt_restart_timestamp_is_set)
                        this.ord_receipt_restart_timestamp_is_modified = true;
                    this.ord_receipt_restart_timestamp_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_id") ) {
                    if ( value != null )
                      if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'product_id' must be Long: '"+value+"' is not." );
                    this.product_id = (Long) value;
                    if (this.product_id_is_set)
                        this.product_id_is_modified = true;
                    this.product_id_is_set = true;
                    return;
                }
                else if ( name.equals("process_result_div") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'process_result_div' must be String: '"+value+"' is not." );
                    this.process_result_div = (String) value;
                    if (this.process_result_div_is_set)
                        this.process_result_div_is_modified = true;
                    this.process_result_div_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("standard_name") ) {
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
                if ( name.equals("trade_stop_restart_timestamp") ) {
                    if ( value != null )
                      if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'trade_stop_restart_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.trade_stop_restart_timestamp = (java.sql.Timestamp) value;
                    if (this.trade_stop_restart_timestamp_is_set)
                        this.trade_stop_restart_timestamp_is_modified = true;
                    this.trade_stop_restart_timestamp_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("valid_until_biz_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'valid_until_biz_date' must be String: '"+value+"' is not." );
                    this.valid_until_biz_date = (String) value;
                    if (this.valid_until_biz_date_is_set)
                        this.valid_until_biz_date_is_modified = true;
                    this.valid_until_biz_date_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
