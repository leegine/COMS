head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.22.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	ExpAccountOpenTempRow.java;


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
 * ExpAccountOpenTempRowインタフェースは変更不可でリードオンリーである<em>exp_account_open_temp</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link ExpAccountOpenTempRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see ExpAccountOpenTempPK 
 */
public interface ExpAccountOpenTempRow extends Row {


  /** 
   * この{@@link ExpAccountOpenTempRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "exp_account_open_temp", "session" );


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
   * <em>institution_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getInstitutionId();


  /** 
   * <em>institution_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionIdIsSet();


  /** 
   * <em>institution_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInstitutionIdIsModified();


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
   * <em>sonar_trader_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSonarTraderCode();


  /** 
   * <em>sonar_trader_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarTraderCodeIsSet();


  /** 
   * <em>sonar_trader_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarTraderCodeIsModified();


  /** 
   * <em>ex_account_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExAccountFlag();


  /** 
   * <em>ex_account_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExAccountFlagIsSet();


  /** 
   * <em>ex_account_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExAccountFlagIsModified();


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
   * <em>order_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderDiv();


  /** 
   * <em>order_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderDivIsSet();


  /** 
   * <em>order_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderDivIsModified();


  /** 
   * <em>infomation_claim_datetime</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getInfomationClaimDatetime();


  /** 
   * <em>infomation_claim_datetime</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInfomationClaimDatetimeIsSet();


  /** 
   * <em>infomation_claim_datetime</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInfomationClaimDatetimeIsModified();


  /** 
   * <em>account_open_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getAccountOpenDate();


  /** 
   * <em>account_open_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDateIsSet();


  /** 
   * <em>account_open_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountOpenDateIsModified();


  /** 
   * <em>initial_password</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInitialPassword();


  /** 
   * <em>initial_password</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInitialPasswordIsSet();


  /** 
   * <em>initial_password</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInitialPasswordIsModified();


  /** 
   * <em>family_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFamilyName();


  /** 
   * <em>family_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyNameIsSet();


  /** 
   * <em>family_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyNameIsModified();


  /** 
   * <em>given_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGivenName();


  /** 
   * <em>given_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGivenNameIsSet();


  /** 
   * <em>given_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGivenNameIsModified();


  /** 
   * <em>family_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFamilyNameAlt1();


  /** 
   * <em>family_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyNameAlt1IsSet();


  /** 
   * <em>family_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyNameAlt1IsModified();


  /** 
   * <em>given_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGivenNameAlt1();


  /** 
   * <em>given_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGivenNameAlt1IsSet();


  /** 
   * <em>given_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGivenNameAlt1IsModified();


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
   * <em>email_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEmailAddress();


  /** 
   * <em>email_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEmailAddressIsSet();


  /** 
   * <em>email_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEmailAddressIsModified();


  /** 
   * <em>email_address_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEmailAddressAlt1();


  /** 
   * <em>email_address_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEmailAddressAlt1IsSet();


  /** 
   * <em>email_address_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEmailAddressAlt1IsModified();


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
   * <em>mobile</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMobile();


  /** 
   * <em>mobile</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMobileIsSet();


  /** 
   * <em>mobile</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMobileIsModified();


  /** 
   * <em>fax</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFax();


  /** 
   * <em>fax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFaxIsSet();


  /** 
   * <em>fax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFaxIsModified();


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
   * <em>office</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOffice();


  /** 
   * <em>office</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfficeIsSet();


  /** 
   * <em>office</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfficeIsModified();


  /** 
   * <em>office_zip_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOfficeZipCode();


  /** 
   * <em>office_zip_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfficeZipCodeIsSet();


  /** 
   * <em>office_zip_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfficeZipCodeIsModified();


  /** 
   * <em>office_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOfficeAddress();


  /** 
   * <em>office_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfficeAddressIsSet();


  /** 
   * <em>office_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfficeAddressIsModified();


  /** 
   * <em>office_telephone</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOfficeTelephone();


  /** 
   * <em>office_telephone</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfficeTelephoneIsSet();


  /** 
   * <em>office_telephone</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfficeTelephoneIsModified();


  /** 
   * <em>office_fax</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOfficeFax();


  /** 
   * <em>office_fax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfficeFaxIsSet();


  /** 
   * <em>office_fax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfficeFaxIsModified();


  /** 
   * <em>department</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDepartment();


  /** 
   * <em>department</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepartmentIsSet();


  /** 
   * <em>department</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDepartmentIsModified();


  /** 
   * <em>post</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPost();


  /** 
   * <em>post</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPostIsSet();


  /** 
   * <em>post</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPostIsModified();


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
   * <em>family_relationship</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFamilyRelationship();


  /** 
   * <em>family_relationship</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyRelationshipIsSet();


  /** 
   * <em>family_relationship</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyRelationshipIsModified();


  /** 
   * <em>family_relationship_etc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFamilyRelationshipEtc();


  /** 
   * <em>family_relationship_etc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyRelationshipEtcIsSet();


  /** 
   * <em>family_relationship_etc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyRelationshipEtcIsModified();


  /** 
   * <em>householder</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHouseholder();


  /** 
   * <em>householder</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderIsSet();


  /** 
   * <em>householder</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderIsModified();


  /** 
   * <em>householder_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHouseholderKana();


  /** 
   * <em>householder_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderKanaIsSet();


  /** 
   * <em>householder_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderKanaIsModified();


  /** 
   * <em>householder_occupation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHouseholderOccupationDiv();


  /** 
   * <em>householder_occupation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderOccupationDivIsSet();


  /** 
   * <em>householder_occupation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderOccupationDivIsModified();


  /** 
   * <em>householder_office</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHouseholderOffice();


  /** 
   * <em>householder_office</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderOfficeIsSet();


  /** 
   * <em>householder_office</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderOfficeIsModified();


  /** 
   * <em>householder_office_address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHouseholderOfficeAddress();


  /** 
   * <em>householder_office_address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderOfficeAddressIsSet();


  /** 
   * <em>householder_office_address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderOfficeAddressIsModified();


  /** 
   * <em>householder_department</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHouseholderDepartment();


  /** 
   * <em>householder_department</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderDepartmentIsSet();


  /** 
   * <em>householder_department</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderDepartmentIsModified();


  /** 
   * <em>householder_office_tel</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHouseholderOfficeTel();


  /** 
   * <em>householder_office_tel</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderOfficeTelIsSet();


  /** 
   * <em>householder_office_tel</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderOfficeTelIsModified();


  /** 
   * <em>householder_office_fax</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHouseholderOfficeFax();


  /** 
   * <em>householder_office_fax</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderOfficeFaxIsSet();


  /** 
   * <em>householder_office_fax</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderOfficeFaxIsModified();


  /** 
   * <em>householder_post</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHouseholderPost();


  /** 
   * <em>householder_post</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderPostIsSet();


  /** 
   * <em>householder_post</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHouseholderPostIsModified();


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
   * <em>transfer_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTransferDiv();


  /** 
   * <em>transfer_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferDivIsSet();


  /** 
   * <em>transfer_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferDivIsModified();


  /** 
   * <em>fin_institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFinInstitutionCode();


  /** 
   * <em>fin_institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinInstitutionCodeIsSet();


  /** 
   * <em>fin_institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinInstitutionCodeIsModified();


  /** 
   * <em>fin_institution_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFinInstitutionName();


  /** 
   * <em>fin_institution_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinInstitutionNameIsSet();


  /** 
   * <em>fin_institution_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinInstitutionNameIsModified();


  /** 
   * <em>fin_branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFinBranchCode();


  /** 
   * <em>fin_branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinBranchCodeIsSet();


  /** 
   * <em>fin_branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinBranchCodeIsModified();


  /** 
   * <em>fin_branch_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFinBranchName();


  /** 
   * <em>fin_branch_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinBranchNameIsSet();


  /** 
   * <em>fin_branch_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinBranchNameIsModified();


  /** 
   * <em>fin_save_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFinSaveDiv();


  /** 
   * <em>fin_save_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinSaveDivIsSet();


  /** 
   * <em>fin_save_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinSaveDivIsModified();


  /** 
   * <em>fin_account_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFinAccountNo();


  /** 
   * <em>fin_account_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinAccountNoIsSet();


  /** 
   * <em>fin_account_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinAccountNoIsModified();


  /** 
   * <em>postal_save_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPostalSaveCode();


  /** 
   * <em>postal_save_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPostalSaveCodeIsSet();


  /** 
   * <em>postal_save_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPostalSaveCodeIsModified();


  /** 
   * <em>postal_save_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPostalSaveNo();


  /** 
   * <em>postal_save_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPostalSaveNoIsSet();


  /** 
   * <em>postal_save_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPostalSaveNoIsModified();


  /** 
   * <em>fin_account_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFinAccountName();


  /** 
   * <em>fin_account_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinAccountNameIsSet();


  /** 
   * <em>fin_account_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFinAccountNameIsModified();


  /** 
   * <em>trans_commission</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTransCommission();


  /** 
   * <em>trans_commission</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransCommissionIsSet();


  /** 
   * <em>trans_commission</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransCommissionIsModified();


  /** 
   * <em>experience_div_equity</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDivEquity();


  /** 
   * <em>experience_div_equity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivEquityIsSet();


  /** 
   * <em>experience_div_equity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivEquityIsModified();


  /** 
   * <em>experience_div_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDivMargin();


  /** 
   * <em>experience_div_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivMarginIsSet();


  /** 
   * <em>experience_div_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivMarginIsModified();


  /** 
   * <em>experience_div_bond</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDivBond();


  /** 
   * <em>experience_div_bond</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivBondIsSet();


  /** 
   * <em>experience_div_bond</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivBondIsModified();


  /** 
   * <em>experience_div_wt</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDivWt();


  /** 
   * <em>experience_div_wt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivWtIsSet();


  /** 
   * <em>experience_div_wt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivWtIsModified();


  /** 
   * <em>experience_div_fund_sk</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDivFundSk();


  /** 
   * <em>experience_div_fund_sk</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivFundSkIsSet();


  /** 
   * <em>experience_div_fund_sk</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivFundSkIsModified();


  /** 
   * <em>experience_div_fund_bd</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDivFundBd();


  /** 
   * <em>experience_div_fund_bd</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivFundBdIsSet();


  /** 
   * <em>experience_div_fund_bd</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivFundBdIsModified();


  /** 
   * <em>experience_div_fo</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDivFo();


  /** 
   * <em>experience_div_fo</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivFoIsSet();


  /** 
   * <em>experience_div_fo</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivFoIsModified();


  /** 
   * <em>experience_div_f_equity</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDivFEquity();


  /** 
   * <em>experience_div_f_equity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivFEquityIsSet();


  /** 
   * <em>experience_div_f_equity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivFEquityIsModified();


  /** 
   * <em>experience_div_etc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDivEtc();


  /** 
   * <em>experience_div_etc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivEtcIsSet();


  /** 
   * <em>experience_div_etc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDivEtcIsModified();


  /** 
   * <em>experience_flag_equity</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagEquity();


  /** 
   * <em>experience_flag_equity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagEquityIsSet();


  /** 
   * <em>experience_flag_equity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagEquityIsModified();


  /** 
   * <em>experience_flag_margin</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagMargin();


  /** 
   * <em>experience_flag_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagMarginIsSet();


  /** 
   * <em>experience_flag_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagMarginIsModified();


  /** 
   * <em>experience_flag_bond</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagBond();


  /** 
   * <em>experience_flag_bond</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagBondIsSet();


  /** 
   * <em>experience_flag_bond</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagBondIsModified();


  /** 
   * <em>experience_flag_wt</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagWt();


  /** 
   * <em>experience_flag_wt</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagWtIsSet();


  /** 
   * <em>experience_flag_wt</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagWtIsModified();


  /** 
   * <em>experience_flag_fund_sk</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagFundSk();


  /** 
   * <em>experience_flag_fund_sk</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagFundSkIsSet();


  /** 
   * <em>experience_flag_fund_sk</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagFundSkIsModified();


  /** 
   * <em>experience_flag_fund_bd</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagFundBd();


  /** 
   * <em>experience_flag_fund_bd</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagFundBdIsSet();


  /** 
   * <em>experience_flag_fund_bd</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagFundBdIsModified();


  /** 
   * <em>experience_flag_fo</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagFo();


  /** 
   * <em>experience_flag_fo</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagFoIsSet();


  /** 
   * <em>experience_flag_fo</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagFoIsModified();


  /** 
   * <em>experience_flag_f_equity</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagFEquity();


  /** 
   * <em>experience_flag_f_equity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagFEquityIsSet();


  /** 
   * <em>experience_flag_f_equity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagFEquityIsModified();


  /** 
   * <em>experience_flag_etc</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExperienceFlagEtc();


  /** 
   * <em>experience_flag_etc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagEtcIsSet();


  /** 
   * <em>experience_flag_etc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlagEtcIsModified();


  /** 
   * <em>experience_from</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceFrom();


  /** 
   * <em>experience_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFromIsSet();


  /** 
   * <em>experience_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFromIsModified();


  /** 
   * <em>experience_to</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceTo();


  /** 
   * <em>experience_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceToIsSet();


  /** 
   * <em>experience_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceToIsModified();


  /** 
   * <em>interest_flag_equity</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagEquity();


  /** 
   * <em>interest_flag_equity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlagEquityIsSet();


  /** 
   * <em>interest_flag_equity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlagEquityIsModified();


  /** 
   * <em>interest_flag_ministock</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagMinistock();


  /** 
   * <em>interest_flag_ministock</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlagMinistockIsSet();


  /** 
   * <em>interest_flag_ministock</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlagMinistockIsModified();


  /** 
   * <em>interest_flag_margin</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagMargin();


  /** 
   * <em>interest_flag_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlagMarginIsSet();


  /** 
   * <em>interest_flag_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlagMarginIsModified();


  /** 
   * <em>interest_flag_bond</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagBond();


  /** 
   * <em>interest_flag_bond</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlagBondIsSet();


  /** 
   * <em>interest_flag_bond</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlagBondIsModified();


  /** 
   * <em>interest_flag_fund</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagFund();


  /** 
   * <em>interest_flag_fund</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlagFundIsSet();


  /** 
   * <em>interest_flag_fund</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlagFundIsModified();


  /** 
   * <em>interest_flag_fo</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagFo();


  /** 
   * <em>interest_flag_fo</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlagFoIsSet();


  /** 
   * <em>interest_flag_fo</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlagFoIsModified();


  /** 
   * <em>interest_flag_f_equity</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagFEquity();


  /** 
   * <em>interest_flag_f_equity</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlagFEquityIsSet();


  /** 
   * <em>interest_flag_f_equity</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlagFEquityIsModified();


  /** 
   * <em>interest_flag_etc</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInterestFlagEtc();


  /** 
   * <em>interest_flag_etc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlagEtcIsSet();


  /** 
   * <em>interest_flag_etc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlagEtcIsModified();


  /** 
   * <em>invest_purpose_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInvestPurposeDiv();


  /** 
   * <em>invest_purpose_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInvestPurposeDivIsSet();


  /** 
   * <em>invest_purpose_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInvestPurposeDivIsModified();


  /** 
   * <em>appli_motivat_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAppliMotivatDiv();


  /** 
   * <em>appli_motivat_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAppliMotivatDivIsSet();


  /** 
   * <em>appli_motivat_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAppliMotivatDivIsModified();


  /** 
   * <em>annual_income_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAnnualIncomeDiv();


  /** 
   * <em>annual_income_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAnnualIncomeDivIsSet();


  /** 
   * <em>annual_income_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAnnualIncomeDivIsModified();


  /** 
   * <em>annual_income_from</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAnnualIncomeFrom();


  /** 
   * <em>annual_income_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAnnualIncomeFromIsSet();


  /** 
   * <em>annual_income_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAnnualIncomeFromIsModified();


  /** 
   * <em>annual_income_to</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAnnualIncomeTo();


  /** 
   * <em>annual_income_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAnnualIncomeToIsSet();


  /** 
   * <em>annual_income_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAnnualIncomeToIsModified();


  /** 
   * <em>asset_value_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAssetValueDiv();


  /** 
   * <em>asset_value_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAssetValueDivIsSet();


  /** 
   * <em>asset_value_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAssetValueDivIsModified();


  /** 
   * <em>asset_value_from</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAssetValueFrom();


  /** 
   * <em>asset_value_from</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAssetValueFromIsSet();


  /** 
   * <em>asset_value_from</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAssetValueFromIsModified();


  /** 
   * <em>asset_value_to</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAssetValueTo();


  /** 
   * <em>asset_value_to</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAssetValueToIsSet();


  /** 
   * <em>asset_value_to</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAssetValueToIsModified();


  /** 
   * <em>fund_budget_amount_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFundBudgetAmountDiv();


  /** 
   * <em>fund_budget_amount_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFundBudgetAmountDivIsSet();


  /** 
   * <em>fund_budget_amount_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFundBudgetAmountDivIsModified();


  /** 
   * <em>fund_budget_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFundBudgetDiv();


  /** 
   * <em>fund_budget_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFundBudgetDivIsSet();


  /** 
   * <em>fund_budget_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFundBudgetDivIsModified();


  /** 
   * <em>fund_budget_etc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFundBudgetEtc();


  /** 
   * <em>fund_budget_etc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFundBudgetEtcIsSet();


  /** 
   * <em>fund_budget_etc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFundBudgetEtcIsModified();


  /** 
   * <em>id_confirm_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getIdConfirmFlag();


  /** 
   * <em>id_confirm_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIdConfirmFlagIsSet();


  /** 
   * <em>id_confirm_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIdConfirmFlagIsModified();


  /** 
   * <em>id_confirm_doc_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIdConfirmDocDiv();


  /** 
   * <em>id_confirm_doc_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIdConfirmDocDivIsSet();


  /** 
   * <em>id_confirm_doc_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIdConfirmDocDivIsModified();


  /** 
   * <em>id_confirm_doc_etc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIdConfirmDocEtc();


  /** 
   * <em>id_confirm_doc_etc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIdConfirmDocEtcIsSet();


  /** 
   * <em>id_confirm_doc_etc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIdConfirmDocEtcIsModified();


  /** 
   * <em>special_acc</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSpecialAcc();


  /** 
   * <em>special_acc</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialAccIsSet();


  /** 
   * <em>special_acc</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialAccIsModified();


  /** 
   * <em>special_acc_margin</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSpecialAccMargin();


  /** 
   * <em>special_acc_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialAccMarginIsSet();


  /** 
   * <em>special_acc_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpecialAccMarginIsModified();


  /** 
   * <em>insider_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInsiderFlag();


  /** 
   * <em>insider_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsiderFlagIsSet();


  /** 
   * <em>insider_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsiderFlagIsModified();


  /** 
   * <em>product_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getProductName();


  /** 
   * <em>product_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductNameIsSet();


  /** 
   * <em>product_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getProductNameIsModified();


  /** 
   * <em>send_zip_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSendZipCode();


  /** 
   * <em>send_zip_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendZipCodeIsSet();


  /** 
   * <em>send_zip_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendZipCodeIsModified();


  /** 
   * <em>send_address_line1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSendAddressLine1();


  /** 
   * <em>send_address_line1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendAddressLine1IsSet();


  /** 
   * <em>send_address_line1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendAddressLine1IsModified();


  /** 
   * <em>send_address_line2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSendAddressLine2();


  /** 
   * <em>send_address_line2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendAddressLine2IsSet();


  /** 
   * <em>send_address_line2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendAddressLine2IsModified();


  /** 
   * <em>send_address_line3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSendAddressLine3();


  /** 
   * <em>send_address_line3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendAddressLine3IsSet();


  /** 
   * <em>send_address_line3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSendAddressLine3IsModified();


  /** 
   * <em>ext_item_div1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemDiv1();


  /** 
   * <em>ext_item_div1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv1IsSet();


  /** 
   * <em>ext_item_div1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv1IsModified();


  /** 
   * <em>ext_item_div2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemDiv2();


  /** 
   * <em>ext_item_div2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv2IsSet();


  /** 
   * <em>ext_item_div2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv2IsModified();


  /** 
   * <em>ext_item_div3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemDiv3();


  /** 
   * <em>ext_item_div3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv3IsSet();


  /** 
   * <em>ext_item_div3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv3IsModified();


  /** 
   * <em>ext_item_div4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemDiv4();


  /** 
   * <em>ext_item_div4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv4IsSet();


  /** 
   * <em>ext_item_div4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv4IsModified();


  /** 
   * <em>ext_item_div5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemDiv5();


  /** 
   * <em>ext_item_div5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv5IsSet();


  /** 
   * <em>ext_item_div5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv5IsModified();


  /** 
   * <em>ext_item_div6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemDiv6();


  /** 
   * <em>ext_item_div6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv6IsSet();


  /** 
   * <em>ext_item_div6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv6IsModified();


  /** 
   * <em>ext_item_div7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemDiv7();


  /** 
   * <em>ext_item_div7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv7IsSet();


  /** 
   * <em>ext_item_div7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv7IsModified();


  /** 
   * <em>ext_item_div8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemDiv8();


  /** 
   * <em>ext_item_div8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv8IsSet();


  /** 
   * <em>ext_item_div8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv8IsModified();


  /** 
   * <em>ext_item_div9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemDiv9();


  /** 
   * <em>ext_item_div9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv9IsSet();


  /** 
   * <em>ext_item_div9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv9IsModified();


  /** 
   * <em>ext_item_div10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemDiv10();


  /** 
   * <em>ext_item_div10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv10IsSet();


  /** 
   * <em>ext_item_div10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv10IsModified();


  /** 
   * <em>ext_item_flag1</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag1();


  /** 
   * <em>ext_item_flag1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag1IsSet();


  /** 
   * <em>ext_item_flag1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag1IsModified();


  /** 
   * <em>ext_item_flag2</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag2();


  /** 
   * <em>ext_item_flag2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag2IsSet();


  /** 
   * <em>ext_item_flag2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag2IsModified();


  /** 
   * <em>ext_item_flag3</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag3();


  /** 
   * <em>ext_item_flag3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag3IsSet();


  /** 
   * <em>ext_item_flag3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag3IsModified();


  /** 
   * <em>ext_item_flag4</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag4();


  /** 
   * <em>ext_item_flag4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag4IsSet();


  /** 
   * <em>ext_item_flag4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag4IsModified();


  /** 
   * <em>ext_item_flag5</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag5();


  /** 
   * <em>ext_item_flag5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag5IsSet();


  /** 
   * <em>ext_item_flag5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag5IsModified();


  /** 
   * <em>ext_item_flag6</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag6();


  /** 
   * <em>ext_item_flag6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag6IsSet();


  /** 
   * <em>ext_item_flag6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag6IsModified();


  /** 
   * <em>ext_item_flag7</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag7();


  /** 
   * <em>ext_item_flag7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag7IsSet();


  /** 
   * <em>ext_item_flag7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag7IsModified();


  /** 
   * <em>ext_item_flag8</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag8();


  /** 
   * <em>ext_item_flag8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag8IsSet();


  /** 
   * <em>ext_item_flag8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag8IsModified();


  /** 
   * <em>ext_item_flag9</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag9();


  /** 
   * <em>ext_item_flag9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag9IsSet();


  /** 
   * <em>ext_item_flag9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag9IsModified();


  /** 
   * <em>ext_item_flag10</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getExtItemFlag10();


  /** 
   * <em>ext_item_flag10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag10IsSet();


  /** 
   * <em>ext_item_flag10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemFlag10IsModified();


  /** 
   * <em>ext_item_text1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemText1();


  /** 
   * <em>ext_item_text1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText1IsSet();


  /** 
   * <em>ext_item_text1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText1IsModified();


  /** 
   * <em>ext_item_text2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemText2();


  /** 
   * <em>ext_item_text2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText2IsSet();


  /** 
   * <em>ext_item_text2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText2IsModified();


  /** 
   * <em>ext_item_text3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemText3();


  /** 
   * <em>ext_item_text3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText3IsSet();


  /** 
   * <em>ext_item_text3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText3IsModified();


  /** 
   * <em>ext_item_text4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemText4();


  /** 
   * <em>ext_item_text4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText4IsSet();


  /** 
   * <em>ext_item_text4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText4IsModified();


  /** 
   * <em>ext_item_text5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemText5();


  /** 
   * <em>ext_item_text5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText5IsSet();


  /** 
   * <em>ext_item_text5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText5IsModified();


  /** 
   * <em>ext_item_text6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemText6();


  /** 
   * <em>ext_item_text6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText6IsSet();


  /** 
   * <em>ext_item_text6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText6IsModified();


  /** 
   * <em>ext_item_text7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemText7();


  /** 
   * <em>ext_item_text7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText7IsSet();


  /** 
   * <em>ext_item_text7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText7IsModified();


  /** 
   * <em>ext_item_text8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemText8();


  /** 
   * <em>ext_item_text8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText8IsSet();


  /** 
   * <em>ext_item_text8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText8IsModified();


  /** 
   * <em>ext_item_text9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemText9();


  /** 
   * <em>ext_item_text9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText9IsSet();


  /** 
   * <em>ext_item_text9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText9IsModified();


  /** 
   * <em>ext_item_text10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemText10();


  /** 
   * <em>ext_item_text10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText10IsSet();


  /** 
   * <em>ext_item_text10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemText10IsModified();


  /** 
   * <em>last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLastUpdater();


  /** 
   * <em>last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdaterIsSet();


  /** 
   * <em>last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLastUpdaterIsModified();


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
   * <em>creator</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCreator();


  /** 
   * <em>creator</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCreatorIsSet();


  /** 
   * <em>creator</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCreatorIsModified();


  /** 
   * <em>exclusive_use_account_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExclusiveUseAccountNo();


  /** 
   * <em>exclusive_use_account_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExclusiveUseAccountNoIsSet();


  /** 
   * <em>exclusive_use_account_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExclusiveUseAccountNoIsModified();


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
   * <em>real_name_voucher_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRealNameVoucherDiv();


  /** 
   * <em>real_name_voucher_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealNameVoucherDivIsSet();


  /** 
   * <em>real_name_voucher_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealNameVoucherDivIsModified();


  /** 
   * <em>real_name1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRealName1();


  /** 
   * <em>real_name1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealName1IsSet();


  /** 
   * <em>real_name1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealName1IsModified();


  /** 
   * <em>real_name2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRealName2();


  /** 
   * <em>real_name2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealName2IsSet();


  /** 
   * <em>real_name2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRealName2IsModified();


  /** 
   * <em>insider_voucher_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInsiderVoucherDiv();


  /** 
   * <em>insider_voucher_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsiderVoucherDivIsSet();


  /** 
   * <em>insider_voucher_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsiderVoucherDivIsModified();


  /** 
   * <em>insider_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInsiderProductCode();


  /** 
   * <em>insider_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsiderProductCodeIsSet();


  /** 
   * <em>insider_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsiderProductCodeIsModified();


  /** 
   * <em>insider_relation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInsiderRelationDiv();


  /** 
   * <em>insider_relation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsiderRelationDivIsSet();


  /** 
   * <em>insider_relation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsiderRelationDivIsModified();


  /** 
   * <em>insider_officer_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInsiderOfficerName();


  /** 
   * <em>insider_officer_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsiderOfficerNameIsSet();


  /** 
   * <em>insider_officer_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsiderOfficerNameIsModified();


  /** 
   * <em>insider_post_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInsiderPostCode();


  /** 
   * <em>insider_post_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsiderPostCodeIsSet();


  /** 
   * <em>insider_post_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsiderPostCodeIsModified();


  /** 
   * <em>insider_post_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInsiderPostName();


  /** 
   * <em>insider_post_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsiderPostNameIsSet();


  /** 
   * <em>insider_post_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInsiderPostNameIsModified();


  /** 
   * <em>gp_voucher_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpVoucherDiv();


  /** 
   * <em>gp_voucher_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpVoucherDivIsSet();


  /** 
   * <em>gp_voucher_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpVoucherDivIsModified();


  /** 
   * <em>gp_course</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpCourse();


  /** 
   * <em>gp_course</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpCourseIsSet();


  /** 
   * <em>gp_course</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpCourseIsModified();


  /** 
   * <em>gp_plan</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpPlan();


  /** 
   * <em>gp_plan</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpPlanIsSet();


  /** 
   * <em>gp_plan</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpPlanIsModified();


  /** 
   * <em>gp_target_figure</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpTargetFigure();


  /** 
   * <em>gp_target_figure</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpTargetFigureIsSet();


  /** 
   * <em>gp_target_figure</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpTargetFigureIsModified();


  /** 
   * <em>gp_target_year</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpTargetYear();


  /** 
   * <em>gp_target_year</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpTargetYearIsSet();


  /** 
   * <em>gp_target_year</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpTargetYearIsModified();


  /** 
   * <em>gp_target_month</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpTargetMonth();


  /** 
   * <em>gp_target_month</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpTargetMonthIsSet();


  /** 
   * <em>gp_target_month</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpTargetMonthIsModified();


  /** 
   * <em>gp_installment_figure</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpInstallmentFigure();


  /** 
   * <em>gp_installment_figure</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpInstallmentFigureIsSet();


  /** 
   * <em>gp_installment_figure</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpInstallmentFigureIsModified();


  /** 
   * <em>gp_deposit_cycle</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpDepositCycle();


  /** 
   * <em>gp_deposit_cycle</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpDepositCycleIsSet();


  /** 
   * <em>gp_deposit_cycle</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpDepositCycleIsModified();


  /** 
   * <em>gp_payment_root</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpPaymentRoot();


  /** 
   * <em>gp_payment_root</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpPaymentRootIsSet();


  /** 
   * <em>gp_payment_root</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpPaymentRootIsModified();


  /** 
   * <em>gp_reinvest_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpReinvestDiv();


  /** 
   * <em>gp_reinvest_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpReinvestDivIsSet();


  /** 
   * <em>gp_reinvest_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpReinvestDivIsModified();


  /** 
   * <em>gp_tax_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpTaxDiv();


  /** 
   * <em>gp_tax_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpTaxDivIsSet();


  /** 
   * <em>gp_tax_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpTaxDivIsModified();


  /** 
   * <em>gp_taxfree_limit</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpTaxfreeLimit();


  /** 
   * <em>gp_taxfree_limit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpTaxfreeLimitIsSet();


  /** 
   * <em>gp_taxfree_limit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpTaxfreeLimitIsModified();


  /** 
   * <em>gp_special_taxfree_limit</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpSpecialTaxfreeLimit();


  /** 
   * <em>gp_special_taxfree_limit</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpSpecialTaxfreeLimitIsSet();


  /** 
   * <em>gp_special_taxfree_limit</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpSpecialTaxfreeLimitIsModified();


  /** 
   * <em>gp_subscr_summary</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpSubscrSummary();


  /** 
   * <em>gp_subscr_summary</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpSubscrSummaryIsSet();


  /** 
   * <em>gp_subscr_summary</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpSubscrSummaryIsModified();


  /** 
   * <em>gp_product_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpProductCode();


  /** 
   * <em>gp_product_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpProductCodeIsSet();


  /** 
   * <em>gp_product_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpProductCodeIsModified();


  /** 
   * <em>gp_mortgage_customer</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpMortgageCustomer();


  /** 
   * <em>gp_mortgage_customer</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpMortgageCustomerIsSet();


  /** 
   * <em>gp_mortgage_customer</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpMortgageCustomerIsModified();


  /** 
   * <em>gp_mix_customer</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpMixCustomer();


  /** 
   * <em>gp_mix_customer</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpMixCustomerIsSet();


  /** 
   * <em>gp_mix_customer</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpMixCustomerIsModified();


  /** 
   * <em>gp_contract</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGpContract();


  /** 
   * <em>gp_contract</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpContractIsSet();


  /** 
   * <em>gp_contract</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGpContractIsModified();


  /** 
   * <em>stk_voucher_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStkVoucherDiv();


  /** 
   * <em>stk_voucher_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStkVoucherDivIsSet();


  /** 
   * <em>stk_voucher_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStkVoucherDivIsModified();


  /** 
   * <em>stk_taxation_tran_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStkTaxationTranDiv();


  /** 
   * <em>stk_taxation_tran_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStkTaxationTranDivIsSet();


  /** 
   * <em>stk_taxation_tran_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStkTaxationTranDivIsModified();


  /** 
   * <em>stk_address_line_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStkAddressLineKana();


  /** 
   * <em>stk_address_line_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStkAddressLineKanaIsSet();


  /** 
   * <em>stk_address_line_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStkAddressLineKanaIsModified();


  /** 
   * <em>stk_transfer_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStkTransferDiv();


  /** 
   * <em>stk_transfer_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStkTransferDivIsSet();


  /** 
   * <em>stk_transfer_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStkTransferDivIsModified();


  /** 
   * <em>stk_fin_institution_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStkFinInstitutionCode();


  /** 
   * <em>stk_fin_institution_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStkFinInstitutionCodeIsSet();


  /** 
   * <em>stk_fin_institution_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStkFinInstitutionCodeIsModified();


  /** 
   * <em>stk_fin_branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStkFinBranchCode();


  /** 
   * <em>stk_fin_branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStkFinBranchCodeIsSet();


  /** 
   * <em>stk_fin_branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStkFinBranchCodeIsModified();


  /** 
   * <em>stk_fin_save_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStkFinSaveDiv();


  /** 
   * <em>stk_fin_save_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStkFinSaveDivIsSet();


  /** 
   * <em>stk_fin_save_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStkFinSaveDivIsModified();


  /** 
   * <em>stk_fin_account_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStkFinAccountNo();


  /** 
   * <em>stk_fin_account_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStkFinAccountNoIsSet();


  /** 
   * <em>stk_fin_account_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStkFinAccountNoIsModified();


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
   * <em>ext_item_div11</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemDiv11();


  /** 
   * <em>ext_item_div11</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv11IsSet();


  /** 
   * <em>ext_item_div11</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv11IsModified();


  /** 
   * <em>ext_item_div12</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemDiv12();


  /** 
   * <em>ext_item_div12</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv12IsSet();


  /** 
   * <em>ext_item_div12</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv12IsModified();


  /** 
   * <em>ext_item_div13</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemDiv13();


  /** 
   * <em>ext_item_div13</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv13IsSet();


  /** 
   * <em>ext_item_div13</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv13IsModified();


  /** 
   * <em>ext_item_div14</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemDiv14();


  /** 
   * <em>ext_item_div14</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv14IsSet();


  /** 
   * <em>ext_item_div14</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv14IsModified();


  /** 
   * <em>ext_item_div15</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExtItemDiv15();


  /** 
   * <em>ext_item_div15</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv15IsSet();


  /** 
   * <em>ext_item_div15</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExtItemDiv15IsModified();


  /** 
   * <em>foreign_account_no</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getForeignAccountNo();


  /** 
   * <em>foreign_account_no</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignAccountNoIsSet();


  /** 
   * <em>foreign_account_no</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignAccountNoIsModified();


  /** 
   * <em>foreign_account_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getForeignAccountName();


  /** 
   * <em>foreign_account_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignAccountNameIsSet();


  /** 
   * <em>foreign_account_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignAccountNameIsModified();


  /** 
   * <em>foreign_account_name_eng</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getForeignAccountNameEng();


  /** 
   * <em>foreign_account_name_eng</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignAccountNameEngIsSet();


  /** 
   * <em>foreign_account_name_eng</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignAccountNameEngIsModified();


  /** 
   * <em>foreign_save_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getForeignSaveDiv();


  /** 
   * <em>foreign_save_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignSaveDivIsSet();


  /** 
   * <em>foreign_save_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignSaveDivIsModified();


  /** 
   * <em>delete_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDeleteFlag();


  /** 
   * <em>delete_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeleteFlagIsSet();


  /** 
   * <em>delete_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeleteFlagIsModified();


  /** 
   * <em>delete_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getDeleteTimestamp();


  /** 
   * <em>delete_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeleteTimestampIsSet();


  /** 
   * <em>delete_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDeleteTimestampIsModified();


  /** 
   * <em>print_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPrintFlag();


  /** 
   * <em>print_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrintFlagIsSet();


  /** 
   * <em>print_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPrintFlagIsModified();


  /** 
   * <em>receipt_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getReceiptFlag();


  /** 
   * <em>receipt_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceiptFlagIsSet();


  /** 
   * <em>receipt_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getReceiptFlagIsModified();


  /** 
   * <em>agreement_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getAgreementFlag();


  /** 
   * <em>agreement_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgreementFlagIsSet();


  /** 
   * <em>agreement_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgreementFlagIsModified();


  /** 
   * <em>foreign_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getForeignFlag();


  /** 
   * <em>foreign_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignFlagIsSet();


  /** 
   * <em>foreign_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignFlagIsModified();


  /** 
   * <em>agency_acc_name_kana1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAgencyAccNameKana1();


  /** 
   * <em>agency_acc_name_kana1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyAccNameKana1IsSet();


  /** 
   * <em>agency_acc_name_kana1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyAccNameKana1IsModified();


  /** 
   * <em>agency_acc_name_kana2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAgencyAccNameKana2();


  /** 
   * <em>agency_acc_name_kana2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyAccNameKana2IsSet();


  /** 
   * <em>agency_acc_name_kana2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyAccNameKana2IsModified();


  /** 
   * <em>agency_acc_name1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAgencyAccName1();


  /** 
   * <em>agency_acc_name1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyAccName1IsSet();


  /** 
   * <em>agency_acc_name1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyAccName1IsModified();


  /** 
   * <em>agency_acc_name2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAgencyAccName2();


  /** 
   * <em>agency_acc_name2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyAccName2IsSet();


  /** 
   * <em>agency_acc_name2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyAccName2IsModified();


  /** 
   * <em>agency_address_line1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAgencyAddressLine1();


  /** 
   * <em>agency_address_line1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyAddressLine1IsSet();


  /** 
   * <em>agency_address_line1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyAddressLine1IsModified();


  /** 
   * <em>agency_address_line2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAgencyAddressLine2();


  /** 
   * <em>agency_address_line2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyAddressLine2IsSet();


  /** 
   * <em>agency_address_line2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyAddressLine2IsModified();


  /** 
   * <em>agency_rep_post</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAgencyRepPost();


  /** 
   * <em>agency_rep_post</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyRepPostIsSet();


  /** 
   * <em>agency_rep_post</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyRepPostIsModified();


  /** 
   * <em>agency_rep_name_kana1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAgencyRepNameKana1();


  /** 
   * <em>agency_rep_name_kana1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyRepNameKana1IsSet();


  /** 
   * <em>agency_rep_name_kana1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyRepNameKana1IsModified();


  /** 
   * <em>agency_rep_name_kana2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAgencyRepNameKana2();


  /** 
   * <em>agency_rep_name_kana2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyRepNameKana2IsSet();


  /** 
   * <em>agency_rep_name_kana2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyRepNameKana2IsModified();


  /** 
   * <em>agency_rep_name1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAgencyRepName1();


  /** 
   * <em>agency_rep_name1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyRepName1IsSet();


  /** 
   * <em>agency_rep_name1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyRepName1IsModified();


  /** 
   * <em>agency_rep_name2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAgencyRepName2();


  /** 
   * <em>agency_rep_name2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyRepName2IsSet();


  /** 
   * <em>agency_rep_name2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyRepName2IsModified();


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
   * <em>error_info</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getErrorInfo();


  /** 
   * <em>error_info</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getErrorInfoIsSet();


  /** 
   * <em>error_info</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getErrorInfoIsModified();


}
@
