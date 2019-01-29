head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.51.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8984d7efe084d3b;
filename	IpoBookbuildingRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * IpoBookbuildingRowインタフェースは変更不可でリードオンリーである<em>ipo_bookbuilding</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link IpoBookbuildingRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IpoBookbuildingPK 
 */
public interface IpoBookbuildingRow extends Row {


  /** 
   * この{@@link IpoBookbuildingRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "ipo_bookbuilding", "master" );


  /** 
   * <em>ipo_product_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getIpoProductId();


  /** 
   * <em>ipo_product_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIpoProductIdIsSet();


  /** 
   * <em>ipo_product_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIpoProductIdIsModified();


  /** 
   * <em>bb_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBbDiv();


  /** 
   * <em>bb_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBbDivIsSet();


  /** 
   * <em>bb_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBbDivIsModified();


  /** 
   * <em>bb_seq</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getBbSeq();


  /** 
   * <em>bb_seq</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBbSeqIsSet();


  /** 
   * <em>bb_seq</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBbSeqIsModified();


  /** 
   * <em>branch_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getBranchId();


  /** 
   * <em>branch_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchIdIsSet();


  /** 
   * <em>branch_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchIdIsModified();


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
   * <em>bb_quantity_all</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getBbQuantityAll();


  /** 
   * <em>bb_quantity_all</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBbQuantityAllIsSet();


  /** 
   * <em>bb_quantity_all</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBbQuantityAllIsModified();


  /** 
   * <em>bb_quantity_loop</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getBbQuantityLoop();


  /** 
   * <em>bb_quantity_loop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBbQuantityLoopIsSet();


  /** 
   * <em>bb_quantity_loop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBbQuantityLoopIsModified();


  /** 
   * <em>sub_bb_quantity_all</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getSubBbQuantityAll();


  /** 
   * <em>sub_bb_quantity_all</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSubBbQuantityAllIsNull();


  /** 
   * <em>sub_bb_quantity_all</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubBbQuantityAllIsSet();


  /** 
   * <em>sub_bb_quantity_all</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubBbQuantityAllIsModified();


  /** 
   * <em>sub_bb_quantity_loop</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getSubBbQuantityLoop();


  /** 
   * <em>sub_bb_quantity_loop</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSubBbQuantityLoopIsNull();


  /** 
   * <em>sub_bb_quantity_loop</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubBbQuantityLoopIsSet();


  /** 
   * <em>sub_bb_quantity_loop</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubBbQuantityLoopIsModified();


  /** 
   * <em>process</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProcess();


  /** 
   * <em>process</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProcessIsSet();


  /** 
   * <em>process</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProcessIsModified();


  /** 
   * <em>status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStatus();


  /** 
   * <em>status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatusIsSet();


  /** 
   * <em>status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatusIsModified();


  /** 
   * <em>bb_result_quantity_sum</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getBbResultQuantitySum();


  /** 
   * <em>bb_result_quantity_sum</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBbResultQuantitySumIsNull();


  /** 
   * <em>bb_result_quantity_sum</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBbResultQuantitySumIsSet();


  /** 
   * <em>bb_result_quantity_sum</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBbResultQuantitySumIsModified();


  /** 
   * <em>bb_result_acc_count</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getBbResultAccCount();


  /** 
   * <em>bb_result_acc_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBbResultAccCountIsNull();


  /** 
   * <em>bb_result_acc_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBbResultAccCountIsSet();


  /** 
   * <em>bb_result_acc_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBbResultAccCountIsModified();


  /** 
   * <em>bb_result_quantity_max</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getBbResultQuantityMax();


  /** 
   * <em>bb_result_quantity_max</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBbResultQuantityMaxIsNull();


  /** 
   * <em>bb_result_quantity_max</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBbResultQuantityMaxIsSet();


  /** 
   * <em>bb_result_quantity_max</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBbResultQuantityMaxIsModified();


  /** 
   * <em>bb_result_quantity_min</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getBbResultQuantityMin();


  /** 
   * <em>bb_result_quantity_min</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getBbResultQuantityMinIsNull();


  /** 
   * <em>bb_result_quantity_min</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBbResultQuantityMinIsSet();


  /** 
   * <em>bb_result_quantity_min</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBbResultQuantityMinIsModified();


  /** 
   * <em>sub_bb_result_quantity_sum</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getSubBbResultQuantitySum();


  /** 
   * <em>sub_bb_result_quantity_sum</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSubBbResultQuantitySumIsNull();


  /** 
   * <em>sub_bb_result_quantity_sum</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubBbResultQuantitySumIsSet();


  /** 
   * <em>sub_bb_result_quantity_sum</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubBbResultQuantitySumIsModified();


  /** 
   * <em>sub_bb_result_acc_count</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getSubBbResultAccCount();


  /** 
   * <em>sub_bb_result_acc_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSubBbResultAccCountIsNull();


  /** 
   * <em>sub_bb_result_acc_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubBbResultAccCountIsSet();


  /** 
   * <em>sub_bb_result_acc_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubBbResultAccCountIsModified();


  /** 
   * <em>sub_bb_result_quantity_max</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getSubBbResultQuantityMax();


  /** 
   * <em>sub_bb_result_quantity_max</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSubBbResultQuantityMaxIsNull();


  /** 
   * <em>sub_bb_result_quantity_max</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubBbResultQuantityMaxIsSet();


  /** 
   * <em>sub_bb_result_quantity_max</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubBbResultQuantityMaxIsModified();


  /** 
   * <em>sub_bb_result_quantity_min</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getSubBbResultQuantityMin();


  /** 
   * <em>sub_bb_result_quantity_min</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSubBbResultQuantityMinIsNull();


  /** 
   * <em>sub_bb_result_quantity_min</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubBbResultQuantityMinIsSet();


  /** 
   * <em>sub_bb_result_quantity_min</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubBbResultQuantityMinIsModified();


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
