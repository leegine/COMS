head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.11.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	HostAccRegVoucherRow.java;


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
 * HostAccRegVoucherRowインタフェースは変更不可でリードオンリーである<em>host_acc_reg_voucher</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link HostAccRegVoucherRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see HostAccRegVoucherPK 
 */
public interface HostAccRegVoucherRow extends Row {


  /** 
   * この{@@link HostAccRegVoucherRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "host_acc_reg_voucher", "session" );


  /** 
   * <em>order_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderRequestNumber();


  /** 
   * <em>order_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderRequestNumberIsSet();


  /** 
   * <em>order_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderRequestNumberIsModified();


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
   * <em>account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountCode();


  /** 
   * <em>account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeIsSet();


  /** 
   * <em>account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCodeIsModified();


  /** 
   * <em>trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTraderCode();


  /** 
   * <em>trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTraderCodeIsSet();


  /** 
   * <em>trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTraderCodeIsModified();


  /** 
   * <em>acc_open_request_number</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccOpenRequestNumber();


  /** 
   * <em>acc_open_request_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccOpenRequestNumberIsSet();


  /** 
   * <em>acc_open_request_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccOpenRequestNumberIsModified();


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
   * <em>account_name_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountNameKana();


  /** 
   * <em>account_name_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountNameKanaIsSet();


  /** 
   * <em>account_name_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountNameKanaIsModified();


  /** 
   * <em>account_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountName();


  /** 
   * <em>account_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountNameIsSet();


  /** 
   * <em>account_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountNameIsModified();


  /** 
   * <em>zip_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getZipCode();


  /** 
   * <em>zip_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getZipCodeIsSet();


  /** 
   * <em>zip_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getZipCodeIsModified();


  /** 
   * <em>address_line1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine1();


  /** 
   * <em>address_line1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine1IsSet();


  /** 
   * <em>address_line1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine1IsModified();


  /** 
   * <em>address_line1_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine1Kana();


  /** 
   * <em>address_line1_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine1KanaIsSet();


  /** 
   * <em>address_line1_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine1KanaIsModified();


  /** 
   * <em>address_line2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine2();


  /** 
   * <em>address_line2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine2IsSet();


  /** 
   * <em>address_line2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine2IsModified();


  /** 
   * <em>address_line2_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine2Kana();


  /** 
   * <em>address_line2_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine2KanaIsSet();


  /** 
   * <em>address_line2_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine2KanaIsModified();


  /** 
   * <em>address_line3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine3();


  /** 
   * <em>address_line3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine3IsSet();


  /** 
   * <em>address_line3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine3IsModified();


  /** 
   * <em>address_line3_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressLine3Kana();


  /** 
   * <em>address_line3_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine3KanaIsSet();


  /** 
   * <em>address_line3_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressLine3KanaIsModified();


  /** 
   * <em>telephone</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTelephone();


  /** 
   * <em>telephone</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTelephoneIsSet();


  /** 
   * <em>telephone</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTelephoneIsModified();


  /** 
   * <em>contact_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getContactAddress();


  /** 
   * <em>contact_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContactAddressIsSet();


  /** 
   * <em>contact_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContactAddressIsModified();


  /** 
   * <em>contact_telephone</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getContactTelephone();


  /** 
   * <em>contact_telephone</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContactTelephoneIsSet();


  /** 
   * <em>contact_telephone</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContactTelephoneIsModified();


  /** 
   * <em>ex_branch_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExBranchName();


  /** 
   * <em>ex_branch_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExBranchNameIsSet();


  /** 
   * <em>ex_branch_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExBranchNameIsModified();


  /** 
   * <em>ex_account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExAccountCode();


  /** 
   * <em>ex_account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExAccountCodeIsSet();


  /** 
   * <em>ex_account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExAccountCodeIsModified();


  /** 
   * <em>occupation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOccupationDiv();


  /** 
   * <em>occupation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOccupationDivIsSet();


  /** 
   * <em>occupation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOccupationDivIsModified();


  /** 
   * <em>era_born</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEraBorn();


  /** 
   * <em>era_born</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEraBornIsSet();


  /** 
   * <em>era_born</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEraBornIsModified();


  /** 
   * <em>born_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBornDate();


  /** 
   * <em>born_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBornDateIsSet();


  /** 
   * <em>born_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBornDateIsModified();


  /** 
   * <em>sex</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSex();


  /** 
   * <em>sex</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSexIsSet();


  /** 
   * <em>sex</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSexIsModified();


  /** 
   * <em>document</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDocument();


  /** 
   * <em>document</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDocumentIsSet();


  /** 
   * <em>document</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDocumentIsModified();


  /** 
   * <em>unknown_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getUnknownAddress();


  /** 
   * <em>unknown_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnknownAddressIsSet();


  /** 
   * <em>unknown_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUnknownAddressIsModified();


  /** 
   * <em>report</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getReport();


  /** 
   * <em>report</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReportIsSet();


  /** 
   * <em>report</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReportIsModified();


  /** 
   * <em>resident</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getResident();


  /** 
   * <em>resident</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getResidentIsSet();


  /** 
   * <em>resident</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getResidentIsModified();


  /** 
   * <em>taxation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTaxationDiv();


  /** 
   * <em>taxation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxationDivIsSet();


  /** 
   * <em>taxation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxationDivIsModified();


  /** 
   * <em>forign_taxation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getForignTaxationDiv();


  /** 
   * <em>forign_taxation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForignTaxationDivIsSet();


  /** 
   * <em>forign_taxation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForignTaxationDivIsModified();


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
   * <em>account_open_div1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountOpenDiv1();


  /** 
   * <em>account_open_div1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv1IsSet();


  /** 
   * <em>account_open_div1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv1IsModified();


  /** 
   * <em>account_open_div2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountOpenDiv2();


  /** 
   * <em>account_open_div2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv2IsSet();


  /** 
   * <em>account_open_div2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv2IsModified();


  /** 
   * <em>account_open_div3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountOpenDiv3();


  /** 
   * <em>account_open_div3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv3IsSet();


  /** 
   * <em>account_open_div3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv3IsModified();


  /** 
   * <em>account_open_div4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountOpenDiv4();


  /** 
   * <em>account_open_div4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv4IsSet();


  /** 
   * <em>account_open_div4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv4IsModified();


  /** 
   * <em>account_open_div5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountOpenDiv5();


  /** 
   * <em>account_open_div5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv5IsSet();


  /** 
   * <em>account_open_div5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv5IsModified();


  /** 
   * <em>account_open_div6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountOpenDiv6();


  /** 
   * <em>account_open_div6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv6IsSet();


  /** 
   * <em>account_open_div6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv6IsModified();


  /** 
   * <em>account_open_div7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountOpenDiv7();


  /** 
   * <em>account_open_div7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv7IsSet();


  /** 
   * <em>account_open_div7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv7IsModified();


  /** 
   * <em>account_open_div8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountOpenDiv8();


  /** 
   * <em>account_open_div8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv8IsSet();


  /** 
   * <em>account_open_div8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv8IsModified();


  /** 
   * <em>account_open_div9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountOpenDiv9();


  /** 
   * <em>account_open_div9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv9IsSet();


  /** 
   * <em>account_open_div9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv9IsModified();


  /** 
   * <em>account_open_div10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountOpenDiv10();


  /** 
   * <em>account_open_div10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv10IsSet();


  /** 
   * <em>account_open_div10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv10IsModified();


  /** 
   * <em>account_open_div11</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountOpenDiv11();


  /** 
   * <em>account_open_div11</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv11IsSet();


  /** 
   * <em>account_open_div11</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv11IsModified();


  /** 
   * <em>account_open_div12</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountOpenDiv12();


  /** 
   * <em>account_open_div12</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv12IsSet();


  /** 
   * <em>account_open_div12</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv12IsModified();


  /** 
   * <em>account_open_div13</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccountOpenDiv13();


  /** 
   * <em>account_open_div13</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv13IsSet();


  /** 
   * <em>account_open_div13</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDiv13IsModified();


  /** 
   * <em>taxation_appl_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTaxationApplDiv();


  /** 
   * <em>taxation_appl_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxationApplDivIsSet();


  /** 
   * <em>taxation_appl_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxationApplDivIsModified();


  /** 
   * <em>taxation_tran_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTaxationTranDiv();


  /** 
   * <em>taxation_tran_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxationTranDivIsSet();


  /** 
   * <em>taxation_tran_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxationTranDivIsModified();


  /** 
   * <em>send_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSendDiv();


  /** 
   * <em>send_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendDivIsSet();


  /** 
   * <em>send_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendDivIsModified();


  /** 
   * <em>trust_via_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTrustViaDiv();


  /** 
   * <em>trust_via_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrustViaDivIsSet();


  /** 
   * <em>trust_via_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrustViaDivIsModified();


  /** 
   * <em>correct_div1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCorrectDiv1();


  /** 
   * <em>correct_div1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCorrectDiv1IsSet();


  /** 
   * <em>correct_div1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCorrectDiv1IsModified();


  /** 
   * <em>correct_div2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCorrectDiv2();


  /** 
   * <em>correct_div2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCorrectDiv2IsSet();


  /** 
   * <em>correct_div2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCorrectDiv2IsModified();


  /** 
   * <em>correct_div3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCorrectDiv3();


  /** 
   * <em>correct_div3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCorrectDiv3IsSet();


  /** 
   * <em>correct_div3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCorrectDiv3IsModified();


  /** 
   * <em>correct_div4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCorrectDiv4();


  /** 
   * <em>correct_div4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCorrectDiv4IsSet();


  /** 
   * <em>correct_div4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCorrectDiv4IsModified();


  /** 
   * <em>correct_div5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCorrectDiv5();


  /** 
   * <em>correct_div5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCorrectDiv5IsSet();


  /** 
   * <em>correct_div5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCorrectDiv5IsModified();


  /** 
   * <em>ifo_acc_open_div_tokyo</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoAccOpenDivTokyo();


  /** 
   * <em>ifo_acc_open_div_tokyo</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoAccOpenDivTokyoIsSet();


  /** 
   * <em>ifo_acc_open_div_tokyo</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoAccOpenDivTokyoIsModified();


  /** 
   * <em>ifo_acc_open_div_osaka</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoAccOpenDivOsaka();


  /** 
   * <em>ifo_acc_open_div_osaka</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoAccOpenDivOsakaIsSet();


  /** 
   * <em>ifo_acc_open_div_osaka</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoAccOpenDivOsakaIsModified();


  /** 
   * <em>ifo_acc_open_div_nagoya</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoAccOpenDivNagoya();


  /** 
   * <em>ifo_acc_open_div_nagoya</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoAccOpenDivNagoyaIsSet();


  /** 
   * <em>ifo_acc_open_div_nagoya</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoAccOpenDivNagoyaIsModified();


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
   * <em>send_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getSendTimestamp();


  /** 
   * <em>send_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendTimestampIsSet();


  /** 
   * <em>send_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendTimestampIsModified();


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


  /** 
   * <em>other_contact_telephone</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOtherContactTelephone();


  /** 
   * <em>other_contact_telephone</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherContactTelephoneIsSet();


  /** 
   * <em>other_contact_telephone</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherContactTelephoneIsModified();


  /** 
   * <em>brokerage_trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBrokerageTraderCode();


  /** 
   * <em>brokerage_trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBrokerageTraderCodeIsSet();


  /** 
   * <em>brokerage_trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBrokerageTraderCodeIsModified();


  /** 
   * <em>proam_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProamDiv();


  /** 
   * <em>proam_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProamDivIsSet();


  /** 
   * <em>proam_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProamDivIsModified();


  /** 
   * <em>foreigner_div_broadcast</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getForeignerDivBroadcast();


  /** 
   * <em>foreigner_div_broadcast</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignerDivBroadcastIsSet();


  /** 
   * <em>foreigner_div_broadcast</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignerDivBroadcastIsModified();


  /** 
   * <em>foreigner_div_aviation</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getForeignerDivAviation();


  /** 
   * <em>foreigner_div_aviation</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignerDivAviationIsSet();


  /** 
   * <em>foreigner_div_aviation</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignerDivAviationIsModified();


  /** 
   * <em>foreigner_div_ntt</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getForeignerDivNtt();


  /** 
   * <em>foreigner_div_ntt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignerDivNttIsSet();


  /** 
   * <em>foreigner_div_ntt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignerDivNttIsModified();


  /** 
   * <em>dividend_transfer_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDividendTransferDiv();


  /** 
   * <em>dividend_transfer_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDividendTransferDivIsSet();


  /** 
   * <em>dividend_transfer_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDividendTransferDivIsModified();


  /** 
   * <em>agent_div_permanent</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAgentDivPermanent();


  /** 
   * <em>agent_div_permanent</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgentDivPermanentIsSet();


  /** 
   * <em>agent_div_permanent</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgentDivPermanentIsModified();


  /** 
   * <em>agent_div_legal</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAgentDivLegal();


  /** 
   * <em>agent_div_legal</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgentDivLegalIsSet();


  /** 
   * <em>agent_div_legal</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgentDivLegalIsModified();


}
@
