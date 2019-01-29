head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	AffinityKeyBasedMapRow.java;


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
 * AffinityKeyBasedMapRowインタフェースは変更不可でリードオンリーである<em>affinity_key_based_map</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link AffinityKeyBasedMapRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AffinityKeyBasedMapPK 
 */
public interface AffinityKeyBasedMapRow extends Row {


  /** 
   * この{@@link AffinityKeyBasedMapRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "affinity_key_based_map", "rac-config" );


  /** 
   * <em>key</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getKey();


  /** 
   * <em>key</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getKeyIsSet();


  /** 
   * <em>key</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getKeyIsModified();


  /** 
   * <em>app_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAppId();


  /** 
   * <em>app_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAppIdIsSet();


  /** 
   * <em>app_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAppIdIsModified();


  /** 
   * <em>db_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDbId();


  /** 
   * <em>db_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDbIdIsSet();


  /** 
   * <em>db_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDbIdIsModified();


}
@
