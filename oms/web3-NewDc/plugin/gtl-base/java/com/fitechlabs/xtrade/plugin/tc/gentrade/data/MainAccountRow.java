head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.36.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MainAccountRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;

/** 
 * MainAccountRowインタフェースは変更不可でリードオンリーである<em>main_account</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link MainAccountRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MainAccountPK 
 */
public interface MainAccountRow extends Row {


  /** 
   * この{@@link MainAccountRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "main_account", "master" );


  /** 
   * <em>account_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getAccountId();


  /** 
   * <em>account_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountIdIsSet();


  /** 
   * <em>account_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountIdIsModified();


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
   * <em>account_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum getAccountType();


  /** 
   * <em>account_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountTypeIsSet();


  /** 
   * <em>account_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountTypeIsModified();


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
   * <em>middle_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMiddleName();


  /** 
   * <em>middle_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMiddleNameIsSet();


  /** 
   * <em>middle_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMiddleNameIsModified();


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
   * <em>middle_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMiddleNameAlt1();


  /** 
   * <em>middle_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMiddleNameAlt1IsSet();


  /** 
   * <em>middle_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMiddleNameAlt1IsModified();


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
   * <em>family_name_alt2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFamilyNameAlt2();


  /** 
   * <em>family_name_alt2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyNameAlt2IsSet();


  /** 
   * <em>family_name_alt2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFamilyNameAlt2IsModified();


  /** 
   * <em>middle_name_alt2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMiddleNameAlt2();


  /** 
   * <em>middle_name_alt2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMiddleNameAlt2IsSet();


  /** 
   * <em>middle_name_alt2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMiddleNameAlt2IsModified();


  /** 
   * <em>given_name_alt2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getGivenNameAlt2();


  /** 
   * <em>given_name_alt2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGivenNameAlt2IsSet();


  /** 
   * <em>given_name_alt2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getGivenNameAlt2IsModified();


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
   * <em>sub_zip_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSubZipCode();


  /** 
   * <em>sub_zip_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubZipCodeIsSet();


  /** 
   * <em>sub_zip_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSubZipCodeIsModified();


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
   * <em>equity_order_exe_mail_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getEquityOrderExeMailFlag();


  /** 
   * <em>equity_order_exe_mail_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityOrderExeMailFlagIsSet();


  /** 
   * <em>equity_order_exe_mail_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityOrderExeMailFlagIsModified();


  /** 
   * <em>equity_order_unexec_mail_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getEquityOrderUnexecMailFlag();


  /** 
   * <em>equity_order_unexec_mail_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityOrderUnexecMailFlagIsSet();


  /** 
   * <em>equity_order_unexec_mail_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquityOrderUnexecMailFlagIsModified();


  /** 
   * <em>ifo_order_exec_mail_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getIfoOrderExecMailFlag();


  /** 
   * <em>ifo_order_exec_mail_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoOrderExecMailFlagIsSet();


  /** 
   * <em>ifo_order_exec_mail_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoOrderExecMailFlagIsModified();


  /** 
   * <em>ifo_order_unexec_mail_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getIfoOrderUnexecMailFlag();


  /** 
   * <em>ifo_order_unexec_mail_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoOrderUnexecMailFlagIsSet();


  /** 
   * <em>ifo_order_unexec_mail_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoOrderUnexecMailFlagIsModified();


  /** 
   * <em>information_mail_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getInformationMailFlag();


  /** 
   * <em>information_mail_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInformationMailFlagIsSet();


  /** 
   * <em>information_mail_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInformationMailFlagIsModified();


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
   * <em>new_account_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNewAccountDiv();


  /** 
   * <em>new_account_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewAccountDivIsSet();


  /** 
   * <em>new_account_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNewAccountDivIsModified();


  /** 
   * <em>via_trust_bank_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getViaTrustBankDiv();


  /** 
   * <em>via_trust_bank_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getViaTrustBankDivIsSet();


  /** 
   * <em>via_trust_bank_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getViaTrustBankDivIsModified();


  /** 
   * <em>class_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getClassDiv();


  /** 
   * <em>class_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClassDivIsSet();


  /** 
   * <em>class_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getClassDivIsModified();


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
   * <em>trading_password</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradingPassword();


  /** 
   * <em>trading_password</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingPasswordIsSet();


  /** 
   * <em>trading_password</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingPasswordIsModified();


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
   * <em>account_close_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getAccountCloseDate();


  /** 
   * <em>account_close_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCloseDateIsSet();


  /** 
   * <em>account_close_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountCloseDateIsModified();


  /** 
   * <em>acc_open_send_mail_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAccOpenSendMailStatus();


  /** 
   * <em>acc_open_send_mail_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccOpenSendMailStatusIsSet();


  /** 
   * <em>acc_open_send_mail_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccOpenSendMailStatusIsModified();


  /** 
   * <em>person_identify</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPersonIdentify();


  /** 
   * <em>person_identify</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPersonIdentifyIsSet();


  /** 
   * <em>person_identify</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPersonIdentifyIsModified();


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
   * <em>yellow_customer</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getYellowCustomer();


  /** 
   * <em>yellow_customer</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getYellowCustomerIsSet();


  /** 
   * <em>yellow_customer</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getYellowCustomerIsModified();


  /** 
   * <em>ht_settlement_way</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getHtSettlementWay();


  /** 
   * <em>ht_settlement_way</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHtSettlementWayIsSet();


  /** 
   * <em>ht_settlement_way</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getHtSettlementWayIsModified();


  /** 
   * <em>bank_account_regi</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBankAccountRegi();


  /** 
   * <em>bank_account_regi</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBankAccountRegiIsSet();


  /** 
   * <em>bank_account_regi</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBankAccountRegiIsModified();


  /** 
   * <em>account_status</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum getAccountStatus();


  /** 
   * <em>account_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountStatusIsSet();


  /** 
   * <em>account_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountStatusIsModified();


  /** 
   * <em>examin_lock_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExaminLockFlag();


  /** 
   * <em>examin_lock_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExaminLockFlagIsSet();


  /** 
   * <em>examin_lock_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExaminLockFlagIsModified();


  /** 
   * <em>mng_lock_flag</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMngLockFlag();


  /** 
   * <em>mng_lock_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMngLockFlagIsSet();


  /** 
   * <em>mng_lock_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMngLockFlagIsModified();


  /** 
   * <em>mng_lock_off_start_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getMngLockOffStartDate();


  /** 
   * <em>mng_lock_off_start_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMngLockOffStartDateIsSet();


  /** 
   * <em>mng_lock_off_start_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMngLockOffStartDateIsModified();


  /** 
   * <em>mng_lock_off_end_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getMngLockOffEndDate();


  /** 
   * <em>mng_lock_off_end_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMngLockOffEndDateIsSet();


  /** 
   * <em>mng_lock_off_end_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMngLockOffEndDateIsModified();


  /** 
   * <em>mng_lock_flag_advance</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMngLockFlagAdvance();


  /** 
   * <em>mng_lock_flag_advance</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMngLockFlagAdvanceIsSet();


  /** 
   * <em>mng_lock_flag_advance</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMngLockFlagAdvanceIsModified();


  /** 
   * <em>mng_lock_flag_unpay_margin</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMngLockFlagUnpayMargin();


  /** 
   * <em>mng_lock_flag_unpay_margin</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMngLockFlagUnpayMarginIsSet();


  /** 
   * <em>mng_lock_flag_unpay_margin</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMngLockFlagUnpayMarginIsModified();


  /** 
   * <em>mng_lock_flag_short_security</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMngLockFlagShortSecurity();


  /** 
   * <em>mng_lock_flag_short_security</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMngLockFlagShortSecurityIsSet();


  /** 
   * <em>mng_lock_flag_short_security</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMngLockFlagShortSecurityIsModified();


  /** 
   * <em>mng_lock_flag_unsubstit_depo</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getMngLockFlagUnsubstitDepo();


  /** 
   * <em>mng_lock_flag_unsubstit_depo</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMngLockFlagUnsubstitDepoIsSet();


  /** 
   * <em>mng_lock_flag_unsubstit_depo</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMngLockFlagUnsubstitDepoIsModified();


  /** 
   * <em>branch_lock</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBranchLock();


  /** 
   * <em>branch_lock</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchLockIsSet();


  /** 
   * <em>branch_lock</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBranchLockIsModified();


  /** 
   * <em>order_permission</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrderPermission();


  /** 
   * <em>order_permission</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderPermissionIsSet();


  /** 
   * <em>order_permission</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderPermissionIsModified();


  /** 
   * <em>tax_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getTaxType();


  /** 
   * <em>tax_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxTypeIsSet();


  /** 
   * <em>tax_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxTypeIsModified();


  /** 
   * <em>tax_type_next</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getTaxTypeNext();


  /** 
   * <em>tax_type_next</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxTypeNextIsSet();


  /** 
   * <em>tax_type_next</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTaxTypeNextIsModified();


  /** 
   * <em>margin_tax_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getMarginTaxType();


  /** 
   * <em>margin_tax_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTaxTypeIsSet();


  /** 
   * <em>margin_tax_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTaxTypeIsModified();


  /** 
   * <em>margin_tax_type_next</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum getMarginTaxTypeNext();


  /** 
   * <em>margin_tax_type_next</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTaxTypeNextIsSet();


  /** 
   * <em>margin_tax_type_next</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginTaxTypeNextIsModified();


  /** 
   * <em>qualified_inst_investor_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getQualifiedInstInvestorDiv();


  /** 
   * <em>qualified_inst_investor_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQualifiedInstInvestorDivIsSet();


  /** 
   * <em>qualified_inst_investor_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQualifiedInstInvestorDivIsModified();


  /** 
   * <em>margin_sys_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginSysAccOpenDiv();


  /** 
   * <em>margin_sys_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSysAccOpenDivIsSet();


  /** 
   * <em>margin_sys_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSysAccOpenDivIsModified();


  /** 
   * <em>margin_gen_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMarginGenAccOpenDiv();


  /** 
   * <em>margin_gen_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginGenAccOpenDivIsSet();


  /** 
   * <em>margin_gen_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginGenAccOpenDivIsModified();


  /** 
   * <em>equity_sp_acc_open_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getEquitySpAccOpenDate();


  /** 
   * <em>equity_sp_acc_open_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquitySpAccOpenDateIsSet();


  /** 
   * <em>equity_sp_acc_open_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEquitySpAccOpenDateIsModified();


  /** 
   * <em>margin_sp_acc_open_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getMarginSpAccOpenDate();


  /** 
   * <em>margin_sp_acc_open_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSpAccOpenDateIsSet();


  /** 
   * <em>margin_sp_acc_open_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginSpAccOpenDateIsModified();


  /** 
   * <em>transfer_count</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getTransferCount();


  /** 
   * <em>transfer_count</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getTransferCountIsNull();


  /** 
   * <em>transfer_count</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferCountIsSet();


  /** 
   * <em>transfer_count</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTransferCountIsModified();


  /** 
   * <em>foreign_sec_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getForeignSecAccOpenDiv();


  /** 
   * <em>foreign_sec_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignSecAccOpenDivIsSet();


  /** 
   * <em>foreign_sec_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getForeignSecAccOpenDivIsModified();


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
   * <em>ruito_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRuitoAccOpenDiv();


  /** 
   * <em>ruito_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRuitoAccOpenDivIsSet();


  /** 
   * <em>ruito_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRuitoAccOpenDivIsModified();


  /** 
   * <em>mrf_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMrfAccOpenDiv();


  /** 
   * <em>mrf_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMrfAccOpenDivIsSet();


  /** 
   * <em>mrf_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMrfAccOpenDivIsModified();


  /** 
   * <em>fx_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFxAccOpenDiv();


  /** 
   * <em>fx_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFxAccOpenDivIsSet();


  /** 
   * <em>fx_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFxAccOpenDivIsModified();


  /** 
   * <em>feq_con_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFeqConAccOpenDiv();


  /** 
   * <em>feq_con_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFeqConAccOpenDivIsSet();


  /** 
   * <em>feq_con_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFeqConAccOpenDivIsModified();


  /** 
   * <em>top_page_id</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTopPageId();


  /** 
   * <em>top_page_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTopPageIdIsSet();


  /** 
   * <em>top_page_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTopPageIdIsModified();


  /** 
   * <em>quoto_type</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getQuotoType();


  /** 
   * <em>quoto_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQuotoTypeIsSet();


  /** 
   * <em>quoto_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getQuotoTypeIsModified();


  /** 
   * <em>trading_report_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradingReportDiv();


  /** 
   * <em>trading_report_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingReportDivIsSet();


  /** 
   * <em>trading_report_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingReportDivIsModified();


  /** 
   * <em>position_report_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPositionReportDiv();


  /** 
   * <em>position_report_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPositionReportDivIsSet();


  /** 
   * <em>position_report_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPositionReportDivIsModified();


  /** 
   * <em>position_report_cycle_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPositionReportCycleDiv();


  /** 
   * <em>position_report_cycle_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPositionReportCycleDivIsSet();


  /** 
   * <em>position_report_cycle_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPositionReportCycleDivIsModified();


  /** 
   * <em>certificate_deposit_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getCertificateDepositFlag();


  /** 
   * <em>certificate_deposit_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCertificateDepositFlagIsSet();


  /** 
   * <em>certificate_deposit_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCertificateDepositFlagIsModified();


  /** 
   * <em>account_statement_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getAccountStatementFlag();


  /** 
   * <em>account_statement_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountStatementFlagIsSet();


  /** 
   * <em>account_statement_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAccountStatementFlagIsModified();


  /** 
   * <em>email_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEmailLastUpdater();


  /** 
   * <em>email_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEmailLastUpdaterIsSet();


  /** 
   * <em>email_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEmailLastUpdaterIsModified();


  /** 
   * <em>email_last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getEmailLastUpdatedTimestamp();


  /** 
   * <em>email_last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEmailLastUpdatedTimestampIsSet();


  /** 
   * <em>email_last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEmailLastUpdatedTimestampIsModified();


  /** 
   * <em>trading_password_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getTradingPasswordUpdater();


  /** 
   * <em>trading_password_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingPasswordUpdaterIsSet();


  /** 
   * <em>trading_password_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTradingPasswordUpdaterIsModified();


  /** 
   * <em>tr_pwd_last_update_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getTrPwdLastUpdateTimestamp();


  /** 
   * <em>tr_pwd_last_update_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrPwdLastUpdateTimestampIsSet();


  /** 
   * <em>tr_pwd_last_update_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getTrPwdLastUpdateTimestampIsModified();


  /** 
   * <em>mb_off_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMbOffLastUpdater();


  /** 
   * <em>mb_off_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMbOffLastUpdaterIsSet();


  /** 
   * <em>mb_off_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMbOffLastUpdaterIsModified();


  /** 
   * <em>mb_off_last_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getMbOffLastUpdatedTimestamp();


  /** 
   * <em>mb_off_last_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMbOffLastUpdatedTimestampIsSet();


  /** 
   * <em>mb_off_last_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMbOffLastUpdatedTimestampIsModified();


  /** 
   * <em>enable_order_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEnableOrderLastUpdater();


  /** 
   * <em>enable_order_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEnableOrderLastUpdaterIsSet();


  /** 
   * <em>enable_order_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEnableOrderLastUpdaterIsModified();


  /** 
   * <em>enable_order_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getEnableOrderUpdatedTimestamp();


  /** 
   * <em>enable_order_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEnableOrderUpdatedTimestampIsSet();


  /** 
   * <em>enable_order_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEnableOrderUpdatedTimestampIsModified();


  /** 
   * <em>fx_acc_open_div_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFxAccOpenDivLastUpdater();


  /** 
   * <em>fx_acc_open_div_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFxAccOpenDivLastUpdaterIsSet();


  /** 
   * <em>fx_acc_open_div_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFxAccOpenDivLastUpdaterIsModified();


  /** 
   * <em>fx_acc_open_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getFxAccOpenUpdatedTimestamp();


  /** 
   * <em>fx_acc_open_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFxAccOpenUpdatedTimestampIsSet();


  /** 
   * <em>fx_acc_open_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFxAccOpenUpdatedTimestampIsModified();


  /** 
   * <em>feq_con_acc_open_div_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getFeqConAccOpenDivUpdater();


  /** 
   * <em>feq_con_acc_open_div_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFeqConAccOpenDivUpdaterIsSet();


  /** 
   * <em>feq_con_acc_open_div_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFeqConAccOpenDivUpdaterIsModified();


  /** 
   * <em>feq_con_acc_open_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getFeqConAccOpenTimestamp();


  /** 
   * <em>feq_con_acc_open_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFeqConAccOpenTimestampIsSet();


  /** 
   * <em>feq_con_acc_open_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getFeqConAccOpenTimestampIsModified();


  /** 
   * <em>mrf_fund_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getMrfFundCode();


  /** 
   * <em>mrf_fund_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMrfFundCodeIsSet();


  /** 
   * <em>mrf_fund_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMrfFundCodeIsModified();


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
   * <em>sp_mng_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSpMngAccOpenDiv();


  /** 
   * <em>sp_mng_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpMngAccOpenDivIsSet();


  /** 
   * <em>sp_mng_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSpMngAccOpenDivIsModified();


  /** 
   * <em>lock_registration_reason</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getLockRegistrationReason();


  /** 
   * <em>lock_registration_reason</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLockRegistrationReasonIsSet();


  /** 
   * <em>lock_registration_reason</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getLockRegistrationReasonIsModified();


  /** 
   * <em>inf_mail_flg_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInfMailFlgLastUpdater();


  /** 
   * <em>inf_mail_flg_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInfMailFlgLastUpdaterIsSet();


  /** 
   * <em>inf_mail_flg_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInfMailFlgLastUpdaterIsModified();


  /** 
   * <em>inf_mail_flg_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getInfMailFlgUpdatedTimestamp();


  /** 
   * <em>inf_mail_flg_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInfMailFlgUpdatedTimestampIsSet();


  /** 
   * <em>inf_mail_flg_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInfMailFlgUpdatedTimestampIsModified();


  /** 
   * <em>eq_exe_ml_flg_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEqExeMlFlgLastUpdater();


  /** 
   * <em>eq_exe_ml_flg_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEqExeMlFlgLastUpdaterIsSet();


  /** 
   * <em>eq_exe_ml_flg_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEqExeMlFlgLastUpdaterIsModified();


  /** 
   * <em>eq_exe_ml_flg_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getEqExeMlFlgTimestamp();


  /** 
   * <em>eq_exe_ml_flg_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEqExeMlFlgTimestampIsSet();


  /** 
   * <em>eq_exe_ml_flg_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEqExeMlFlgTimestampIsModified();


  /** 
   * <em>eq_unexe_ml_flg_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEqUnexeMlFlgLastUpdater();


  /** 
   * <em>eq_unexe_ml_flg_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEqUnexeMlFlgLastUpdaterIsSet();


  /** 
   * <em>eq_unexe_ml_flg_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEqUnexeMlFlgLastUpdaterIsModified();


  /** 
   * <em>eq_unexe_ml_flg_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getEqUnexeMlFlgTimestamp();


  /** 
   * <em>eq_unexe_ml_flg_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEqUnexeMlFlgTimestampIsSet();


  /** 
   * <em>eq_unexe_ml_flg_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEqUnexeMlFlgTimestampIsModified();


  /** 
   * <em>ifo_exe_ml_flg_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoExeMlFlgLastUpdater();


  /** 
   * <em>ifo_exe_ml_flg_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoExeMlFlgLastUpdaterIsSet();


  /** 
   * <em>ifo_exe_ml_flg_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoExeMlFlgLastUpdaterIsModified();


  /** 
   * <em>ifo_exe_ml_flg_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getIfoExeMlFlgTimestamp();


  /** 
   * <em>ifo_exe_ml_flg_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoExeMlFlgTimestampIsSet();


  /** 
   * <em>ifo_exe_ml_flg_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoExeMlFlgTimestampIsModified();


  /** 
   * <em>ifo_unexe_ml_flg_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIfoUnexeMlFlgLastUpdater();


  /** 
   * <em>ifo_unexe_ml_flg_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoUnexeMlFlgLastUpdaterIsSet();


  /** 
   * <em>ifo_unexe_ml_flg_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoUnexeMlFlgLastUpdaterIsModified();


  /** 
   * <em>ifo_unexe_ml_flg_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getIfoUnexeMlFlgTimestamp();


  /** 
   * <em>ifo_unexe_ml_flg_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoUnexeMlFlgTimestampIsSet();


  /** 
   * <em>ifo_unexe_ml_flg_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoUnexeMlFlgTimestampIsModified();


  /** 
   * <em>stock_option_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStockOptionAccOpenDiv();


  /** 
   * <em>stock_option_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStockOptionAccOpenDivIsSet();


  /** 
   * <em>stock_option_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStockOptionAccOpenDivIsModified();


  /** 
   * <em>secured_loan_sec_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSecuredLoanSecAccOpenDiv();


  /** 
   * <em>secured_loan_sec_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecuredLoanSecAccOpenDivIsSet();


  /** 
   * <em>secured_loan_sec_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSecuredLoanSecAccOpenDivIsModified();


  /** 
   * <em>margin_acc_open_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getMarginAccOpenDate();


  /** 
   * <em>margin_acc_open_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginAccOpenDateIsSet();


  /** 
   * <em>margin_acc_open_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMarginAccOpenDateIsModified();


  /** 
   * <em>ifo_acc_open_date</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getIfoAccOpenDate();


  /** 
   * <em>ifo_acc_open_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoAccOpenDateIsSet();


  /** 
   * <em>ifo_acc_open_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIfoAccOpenDateIsModified();


  /** 
   * <em>only_mobile_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOnlyMobileOpenDiv();


  /** 
   * <em>only_mobile_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOnlyMobileOpenDivIsSet();


  /** 
   * <em>only_mobile_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOnlyMobileOpenDivIsModified();


  /** 
   * <em>only_mbl_opn_div_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOnlyMblOpnDivLastUpdater();


  /** 
   * <em>only_mbl_opn_div_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOnlyMblOpnDivLastUpdaterIsSet();


  /** 
   * <em>only_mbl_opn_div_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOnlyMblOpnDivLastUpdaterIsModified();


  /** 
   * <em>only_mbl_opn_div_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getOnlyMblOpnDivTimestamp();


  /** 
   * <em>only_mbl_opn_div_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOnlyMblOpnDivTimestampIsSet();


  /** 
   * <em>only_mbl_opn_div_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOnlyMblOpnDivTimestampIsModified();


  /** 
   * <em>email_address_alt2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEmailAddressAlt2();


  /** 
   * <em>email_address_alt2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEmailAddressAlt2IsSet();


  /** 
   * <em>email_address_alt2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEmailAddressAlt2IsModified();


  /** 
   * <em>order_exe_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getOrderExeFlag();


  /** 
   * <em>order_exe_flag</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOrderExeFlagIsNull();


  /** 
   * <em>order_exe_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderExeFlagIsSet();


  /** 
   * <em>order_exe_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderExeFlagIsModified();


  /** 
   * <em>order_unexe_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getOrderUnexeFlag();


  /** 
   * <em>order_unexe_flag</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getOrderUnexeFlagIsNull();


  /** 
   * <em>order_unexe_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderUnexeFlagIsSet();


  /** 
   * <em>order_unexe_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrderUnexeFlagIsModified();


  /** 
   * <em>important_connection_mail_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getImportantConnectionMailFlag();


  /** 
   * <em>important_connection_mail_flag</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getImportantConnectionMailFlagIsNull();


  /** 
   * <em>important_connection_mail_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getImportantConnectionMailFlagIsSet();


  /** 
   * <em>important_connection_mail_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getImportantConnectionMailFlagIsModified();


  /** 
   * <em>information_mail2_flag</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getInformationMail2Flag();


  /** 
   * <em>information_mail2_flag</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getInformationMail2FlagIsNull();


  /** 
   * <em>information_mail2_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInformationMail2FlagIsSet();


  /** 
   * <em>information_mail2_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInformationMail2FlagIsModified();


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
   * <em>org_deposit_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOrgDepositDiv();


  /** 
   * <em>org_deposit_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgDepositDivIsSet();


  /** 
   * <em>org_deposit_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOrgDepositDivIsModified();


  /** 
   * <em>eq_hold_rep_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getEqHoldRepDiv();


  /** 
   * <em>eq_hold_rep_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEqHoldRepDivIsSet();


  /** 
   * <em>eq_hold_rep_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getEqHoldRepDivIsModified();


  /** 
   * <em>chkup_rep_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getChkupRepDiv();


  /** 
   * <em>chkup_rep_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getChkupRepDivIsSet();


  /** 
   * <em>chkup_rep_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getChkupRepDivIsModified();


  /** 
   * <em>pts_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPtsAccOpenDiv();


  /** 
   * <em>pts_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPtsAccOpenDivIsSet();


  /** 
   * <em>pts_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPtsAccOpenDivIsModified();


  /** 
   * <em>pts_acc_open_div_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPtsAccOpenDivLastUpdater();


  /** 
   * <em>pts_acc_open_div_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPtsAccOpenDivLastUpdaterIsSet();


  /** 
   * <em>pts_acc_open_div_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPtsAccOpenDivLastUpdaterIsModified();


  /** 
   * <em>pts_acc_open_div_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getPtsAccOpenDivTimestamp();


  /** 
   * <em>pts_acc_open_div_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPtsAccOpenDivTimestampIsSet();


  /** 
   * <em>pts_acc_open_div_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPtsAccOpenDivTimestampIsModified();


  /** 
   * <em>broadcast_law</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getBroadcastLaw();


  /** 
   * <em>broadcast_law</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBroadcastLawIsSet();


  /** 
   * <em>broadcast_law</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getBroadcastLawIsModified();


  /** 
   * <em>aviation_law</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAviationLaw();


  /** 
   * <em>aviation_law</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAviationLawIsSet();


  /** 
   * <em>aviation_law</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAviationLawIsModified();


  /** 
   * <em>ntt_law</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNttLaw();


  /** 
   * <em>ntt_law</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNttLawIsSet();


  /** 
   * <em>ntt_law</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNttLawIsModified();


  /** 
   * <em>dividend_trans_designate</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDividendTransDesignate();


  /** 
   * <em>dividend_trans_designate</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDividendTransDesignateIsSet();


  /** 
   * <em>dividend_trans_designate</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDividendTransDesignateIsModified();


  /** 
   * <em>standing_proxy</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStandingProxy();


  /** 
   * <em>standing_proxy</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStandingProxyIsSet();


  /** 
   * <em>standing_proxy</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStandingProxyIsModified();


  /** 
   * <em>statutory_agent</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getStatutoryAgent();


  /** 
   * <em>statutory_agent</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatutoryAgentIsSet();


  /** 
   * <em>statutory_agent</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getStatutoryAgentIsModified();


  /** 
   * <em>affiliate_account_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAffiliateAccountCode();


  /** 
   * <em>affiliate_account_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAffiliateAccountCodeIsSet();


  /** 
   * <em>affiliate_account_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAffiliateAccountCodeIsModified();


  /** 
   * <em>agency_notify_cmp_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAgencyNotifyCmpDiv();


  /** 
   * <em>agency_notify_cmp_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyNotifyCmpDivIsSet();


  /** 
   * <em>agency_notify_cmp_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAgencyNotifyCmpDivIsModified();


  /** 
   * <em>cfd_acc_open_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCfdAccOpenDiv();


  /** 
   * <em>cfd_acc_open_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCfdAccOpenDivIsSet();


  /** 
   * <em>cfd_acc_open_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCfdAccOpenDivIsModified();


  /** 
   * <em>cfd_acc_open_div_last_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCfdAccOpenDivLastUpdater();


  /** 
   * <em>cfd_acc_open_div_last_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCfdAccOpenDivLastUpdaterIsSet();


  /** 
   * <em>cfd_acc_open_div_last_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCfdAccOpenDivLastUpdaterIsModified();


  /** 
   * <em>cfd_acc_open_updated_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getCfdAccOpenUpdatedTimestamp();


  /** 
   * <em>cfd_acc_open_updated_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCfdAccOpenUpdatedTimestampIsSet();


  /** 
   * <em>cfd_acc_open_updated_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCfdAccOpenUpdatedTimestampIsModified();


}
@
