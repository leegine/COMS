head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	AffinityRangeBasedMapRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.system.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;

/** 
 * AffinityRangeBasedMapRowインタフェースは変更不可でリードオンリーである<em>affinity_range_based_map</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link AffinityRangeBasedMapRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AffinityRangeBasedMapPK 
 */
public interface AffinityRangeBasedMapRow extends Row {


  /** 
   * この{@@link AffinityRangeBasedMapRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "affinity_range_based_map", "rac-config" );


  /** 
   * <em>key_start</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getKeyStart();


  /** 
   * <em>key_start</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getKeyStartIsSet();


  /** 
   * <em>key_start</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getKeyStartIsModified();


  /** 
   * <em>key_end</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getKeyEnd();


  /** 
   * <em>key_end</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getKeyEndIsSet();


  /** 
   * <em>key_end</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getKeyEndIsModified();


  /** 
   * <em>range_order_no</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getRangeOrderNo();


  /** 
   * <em>range_order_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRangeOrderNoIsSet();


  /** 
   * <em>range_order_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRangeOrderNoIsModified();


  /** 
   * <em>server_type</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getServerType();


  /** 
   * <em>server_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getServerTypeIsSet();


  /** 
   * <em>server_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getServerTypeIsModified();


  /** 
   * <em>server_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getServerId();


  /** 
   * <em>server_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getServerIdIsSet();


  /** 
   * <em>server_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getServerIdIsModified();


  /** 
   * <em>ctx_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCtxName();


  /** 
   * <em>ctx_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCtxNameIsSet();


  /** 
   * <em>ctx_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCtxNameIsModified();


}
@
