head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.21.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenVoucherItemRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * AccOpenVoucherItemRowインタフェースは変更不可でリードオンリーである<em>acc_open_voucher_item</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link AccOpenVoucherItemRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenVoucherItemPK 
 */
public interface AccOpenVoucherItemRow extends Row {


  /** 
   * この{@@link AccOpenVoucherItemRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "acc_open_voucher_item", "session" );


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
   * <em>account_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountDiv();


  /** 
   * <em>account_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountDivIsSet();


  /** 
   * <em>account_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountDivIsModified();


  /** 
   * <em>request_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRequestCode();


  /** 
   * <em>request_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRequestCodeIsSet();


  /** 
   * <em>request_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRequestCodeIsModified();


  /** 
   * <em>serial_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSerialNo();


  /** 
   * <em>serial_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSerialNoIsSet();


  /** 
   * <em>serial_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSerialNoIsModified();


  /** 
   * <em>output_item_symbol_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOutputItemSymbolName();


  /** 
   * <em>output_item_symbol_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOutputItemSymbolNameIsSet();


  /** 
   * <em>output_item_symbol_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOutputItemSymbolNameIsModified();


  /** 
   * <em>edit_way_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEditWayDiv();


  /** 
   * <em>edit_way_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEditWayDivIsSet();


  /** 
   * <em>edit_way_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEditWayDivIsModified();


  /** 
   * <em>fixed_value</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFixedValue();


  /** 
   * <em>fixed_value</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFixedValueIsSet();


  /** 
   * <em>fixed_value</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFixedValueIsModified();


  /** 
   * <em>input_item_symbol_name1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInputItemSymbolName1();


  /** 
   * <em>input_item_symbol_name1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInputItemSymbolName1IsSet();


  /** 
   * <em>input_item_symbol_name1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInputItemSymbolName1IsModified();


  /** 
   * <em>input_item_symbol_name2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInputItemSymbolName2();


  /** 
   * <em>input_item_symbol_name2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInputItemSymbolName2IsSet();


  /** 
   * <em>input_item_symbol_name2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInputItemSymbolName2IsModified();


  /** 
   * <em>input_item_symbol_name3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInputItemSymbolName3();


  /** 
   * <em>input_item_symbol_name3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInputItemSymbolName3IsSet();


  /** 
   * <em>input_item_symbol_name3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInputItemSymbolName3IsModified();


  /** 
   * <em>cat_delimitter</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCatDelimitter();


  /** 
   * <em>cat_delimitter</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCatDelimitterIsSet();


  /** 
   * <em>cat_delimitter</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCatDelimitterIsModified();


}
@
