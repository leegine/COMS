head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.10.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	AccOpenDlFormMasterRow.java;


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
 * AccOpenDlFormMasterRowインタフェースは変更不可でリードオンリーである<em>acc_open_dl_form_master</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link AccOpenDlFormMasterRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see AccOpenDlFormMasterPK 
 */
public interface AccOpenDlFormMasterRow extends Row {


  /** 
   * この{@@link AccOpenDlFormMasterRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "acc_open_dl_form_master", "master" );


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
   * <em>column_number</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getColumnNumber();


  /** 
   * <em>column_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getColumnNumberIsSet();


  /** 
   * <em>column_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getColumnNumberIsModified();


  /** 
   * <em>column_label</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getColumnLabel();


  /** 
   * <em>column_label</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getColumnLabelIsSet();


  /** 
   * <em>column_label</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getColumnLabelIsModified();


  /** 
   * <em>column_type</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getColumnType();


  /** 
   * <em>column_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getColumnTypeIsSet();


  /** 
   * <em>column_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getColumnTypeIsModified();


  /** 
   * <em>date_format</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDateFormat();


  /** 
   * <em>date_format</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDateFormatIsSet();


  /** 
   * <em>date_format</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDateFormatIsModified();


  /** 
   * <em>input_item_symbol_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInputItemSymbolName();


  /** 
   * <em>input_item_symbol_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInputItemSymbolNameIsSet();


  /** 
   * <em>input_item_symbol_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInputItemSymbolNameIsModified();


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


  /** 
   * <em>section_number</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getSectionNumber();


  /** 
   * <em>section_number</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getSectionNumberIsNull();


  /** 
   * <em>section_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSectionNumberIsSet();


  /** 
   * <em>section_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSectionNumberIsModified();


}
@
