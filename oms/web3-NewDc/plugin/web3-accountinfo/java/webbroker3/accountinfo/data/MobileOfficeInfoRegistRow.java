head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	MobileOfficeInfoRegistRow.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * MobileOfficeInfoRegistRowインタフェースは変更不可でリードオンリーである<em>mobile_office_info_regist</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link MobileOfficeInfoRegistRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see MobileOfficeInfoRegistPK 
 */
public interface MobileOfficeInfoRegistRow extends Row {


  /** 
   * この{@@link MobileOfficeInfoRegistRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "mobile_office_info_regist", "account" );


  /** 
   * <em>mobile_office_info_regist_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getMobileOfficeInfoRegistId();


  /** 
   * <em>mobile_office_info_regist_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMobileOfficeInfoRegistIdIsSet();


  /** 
   * <em>mobile_office_info_regist_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getMobileOfficeInfoRegistIdIsModified();


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
   * <em>regist_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRegistUpdater();


  /** 
   * <em>regist_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRegistUpdaterIsSet();


  /** 
   * <em>regist_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRegistUpdaterIsModified();


  /** 
   * <em>decision_flag</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum getDecisionFlag();


  /** 
   * <em>decision_flag</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDecisionFlagIsSet();


  /** 
   * <em>decision_flag</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDecisionFlagIsModified();


  /** 
   * <em>decision</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getDecision();


  /** 
   * <em>decision</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getDecisionIsNull();


  /** 
   * <em>decision</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDecisionIsSet();


  /** 
   * <em>decision</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDecisionIsModified();


  /** 
   * <em>decision_updater</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDecisionUpdater();


  /** 
   * <em>decision_updater</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDecisionUpdaterIsSet();


  /** 
   * <em>decision_updater</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDecisionUpdaterIsModified();


  /** 
   * <em>decision_timestamp</em>カラムの値を取得します。
   * @@return java.sql.Timestampの値 
   */
  public java.sql.Timestamp getDecisionTimestamp();


  /** 
   * <em>decision_timestamp</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDecisionTimestampIsSet();


  /** 
   * <em>decision_timestamp</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDecisionTimestampIsModified();


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
   * <em>contact_priority1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getContactPriority1();


  /** 
   * <em>contact_priority1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContactPriority1IsSet();


  /** 
   * <em>contact_priority1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContactPriority1IsModified();


  /** 
   * <em>contact_priority2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getContactPriority2();


  /** 
   * <em>contact_priority2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContactPriority2IsSet();


  /** 
   * <em>contact_priority2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContactPriority2IsModified();


  /** 
   * <em>contact_priority3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getContactPriority3();


  /** 
   * <em>contact_priority3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContactPriority3IsSet();


  /** 
   * <em>contact_priority3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getContactPriority3IsModified();


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
   * <em>nationality</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNationality();


  /** 
   * <em>nationality</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNationalityIsSet();


  /** 
   * <em>nationality</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNationalityIsModified();


  /** 
   * <em>nationality_other</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNationalityOther();


  /** 
   * <em>nationality_other</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNationalityOtherIsSet();


  /** 
   * <em>nationality_other</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNationalityOtherIsModified();


  /** 
   * <em>represent_family_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRepresentFamilyName();


  /** 
   * <em>represent_family_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepresentFamilyNameIsSet();


  /** 
   * <em>represent_family_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepresentFamilyNameIsModified();


  /** 
   * <em>represent_given_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRepresentGivenName();


  /** 
   * <em>represent_given_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepresentGivenNameIsSet();


  /** 
   * <em>represent_given_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepresentGivenNameIsModified();


  /** 
   * <em>represent_family_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRepresentFamilyNameAlt1();


  /** 
   * <em>represent_family_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepresentFamilyNameAlt1IsSet();


  /** 
   * <em>represent_family_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepresentFamilyNameAlt1IsModified();


  /** 
   * <em>represent_given_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRepresentGivenNameAlt1();


  /** 
   * <em>represent_given_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepresentGivenNameAlt1IsSet();


  /** 
   * <em>represent_given_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepresentGivenNameAlt1IsModified();


  /** 
   * <em>represent_power</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getRepresentPower();


  /** 
   * <em>represent_power</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepresentPowerIsSet();


  /** 
   * <em>represent_power</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getRepresentPowerIsModified();


  /** 
   * <em>director_family_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDirectorFamilyName();


  /** 
   * <em>director_family_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorFamilyNameIsSet();


  /** 
   * <em>director_family_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorFamilyNameIsModified();


  /** 
   * <em>director_given_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDirectorGivenName();


  /** 
   * <em>director_given_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorGivenNameIsSet();


  /** 
   * <em>director_given_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorGivenNameIsModified();


  /** 
   * <em>director_family_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDirectorFamilyNameAlt1();


  /** 
   * <em>director_family_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorFamilyNameAlt1IsSet();


  /** 
   * <em>director_family_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorFamilyNameAlt1IsModified();


  /** 
   * <em>director_given_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDirectorGivenNameAlt1();


  /** 
   * <em>director_given_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorGivenNameAlt1IsSet();


  /** 
   * <em>director_given_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorGivenNameAlt1IsModified();


  /** 
   * <em>director_department</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDirectorDepartment();


  /** 
   * <em>director_department</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorDepartmentIsSet();


  /** 
   * <em>director_department</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorDepartmentIsModified();


  /** 
   * <em>director_post</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDirectorPost();


  /** 
   * <em>director_post</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorPostIsSet();


  /** 
   * <em>director_post</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorPostIsModified();


  /** 
   * <em>director_zip_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDirectorZipCode();


  /** 
   * <em>director_zip_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorZipCodeIsSet();


  /** 
   * <em>director_zip_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorZipCodeIsModified();


  /** 
   * <em>director_address1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDirectorAddress1();


  /** 
   * <em>director_address1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorAddress1IsSet();


  /** 
   * <em>director_address1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorAddress1IsModified();


  /** 
   * <em>director_address2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDirectorAddress2();


  /** 
   * <em>director_address2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorAddress2IsSet();


  /** 
   * <em>director_address2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorAddress2IsModified();


  /** 
   * <em>director_address3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDirectorAddress3();


  /** 
   * <em>director_address3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorAddress3IsSet();


  /** 
   * <em>director_address3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorAddress3IsModified();


  /** 
   * <em>director_era_born</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDirectorEraBorn();


  /** 
   * <em>director_era_born</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorEraBornIsSet();


  /** 
   * <em>director_era_born</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorEraBornIsModified();


  /** 
   * <em>director_born_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDirectorBornDate();


  /** 
   * <em>director_born_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorBornDateIsSet();


  /** 
   * <em>director_born_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorBornDateIsModified();


  /** 
   * <em>director_corp_telephone</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDirectorCorpTelephone();


  /** 
   * <em>director_corp_telephone</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorCorpTelephoneIsSet();


  /** 
   * <em>director_corp_telephone</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDirectorCorpTelephoneIsModified();


  /** 
   * <em>other_contact</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOtherContact();


  /** 
   * <em>other_contact</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherContactIsSet();


  /** 
   * <em>other_contact</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOtherContactIsModified();


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
   * <em>invest_plan_period_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInvestPlanPeriodDiv();


  /** 
   * <em>invest_plan_period_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInvestPlanPeriodDivIsSet();


  /** 
   * <em>invest_plan_period_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInvestPlanPeriodDivIsModified();


  /** 
   * <em>experience_flag1</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getExperienceFlag1();


  /** 
   * <em>experience_flag1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExperienceFlag1IsNull();


  /** 
   * <em>experience_flag1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag1IsSet();


  /** 
   * <em>experience_flag1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag1IsModified();


  /** 
   * <em>experience_flag2</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getExperienceFlag2();


  /** 
   * <em>experience_flag2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExperienceFlag2IsNull();


  /** 
   * <em>experience_flag2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag2IsSet();


  /** 
   * <em>experience_flag2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag2IsModified();


  /** 
   * <em>experience_flag3</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getExperienceFlag3();


  /** 
   * <em>experience_flag3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExperienceFlag3IsNull();


  /** 
   * <em>experience_flag3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag3IsSet();


  /** 
   * <em>experience_flag3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag3IsModified();


  /** 
   * <em>experience_flag4</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getExperienceFlag4();


  /** 
   * <em>experience_flag4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExperienceFlag4IsNull();


  /** 
   * <em>experience_flag4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag4IsSet();


  /** 
   * <em>experience_flag4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag4IsModified();


  /** 
   * <em>experience_flag5</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getExperienceFlag5();


  /** 
   * <em>experience_flag5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExperienceFlag5IsNull();


  /** 
   * <em>experience_flag5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag5IsSet();


  /** 
   * <em>experience_flag5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag5IsModified();


  /** 
   * <em>experience_flag6</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getExperienceFlag6();


  /** 
   * <em>experience_flag6</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExperienceFlag6IsNull();


  /** 
   * <em>experience_flag6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag6IsSet();


  /** 
   * <em>experience_flag6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag6IsModified();


  /** 
   * <em>experience_flag7</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getExperienceFlag7();


  /** 
   * <em>experience_flag7</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExperienceFlag7IsNull();


  /** 
   * <em>experience_flag7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag7IsSet();


  /** 
   * <em>experience_flag7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag7IsModified();


  /** 
   * <em>experience_flag8</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getExperienceFlag8();


  /** 
   * <em>experience_flag8</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExperienceFlag8IsNull();


  /** 
   * <em>experience_flag8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag8IsSet();


  /** 
   * <em>experience_flag8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag8IsModified();


  /** 
   * <em>experience_flag9</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getExperienceFlag9();


  /** 
   * <em>experience_flag9</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExperienceFlag9IsNull();


  /** 
   * <em>experience_flag9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag9IsSet();


  /** 
   * <em>experience_flag9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag9IsModified();


  /** 
   * <em>experience_flag10</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getExperienceFlag10();


  /** 
   * <em>experience_flag10</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getExperienceFlag10IsNull();


  /** 
   * <em>experience_flag10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag10IsSet();


  /** 
   * <em>experience_flag10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceFlag10IsModified();


  /** 
   * <em>experience_div1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDiv1();


  /** 
   * <em>experience_div1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv1IsSet();


  /** 
   * <em>experience_div1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv1IsModified();


  /** 
   * <em>experience_div2</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDiv2();


  /** 
   * <em>experience_div2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv2IsSet();


  /** 
   * <em>experience_div2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv2IsModified();


  /** 
   * <em>experience_div3</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDiv3();


  /** 
   * <em>experience_div3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv3IsSet();


  /** 
   * <em>experience_div3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv3IsModified();


  /** 
   * <em>experience_div4</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDiv4();


  /** 
   * <em>experience_div4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv4IsSet();


  /** 
   * <em>experience_div4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv4IsModified();


  /** 
   * <em>experience_div5</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDiv5();


  /** 
   * <em>experience_div5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv5IsSet();


  /** 
   * <em>experience_div5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv5IsModified();


  /** 
   * <em>experience_div6</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDiv6();


  /** 
   * <em>experience_div6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv6IsSet();


  /** 
   * <em>experience_div6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv6IsModified();


  /** 
   * <em>experience_div7</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDiv7();


  /** 
   * <em>experience_div7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv7IsSet();


  /** 
   * <em>experience_div7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv7IsModified();


  /** 
   * <em>experience_div8</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDiv8();


  /** 
   * <em>experience_div8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv8IsSet();


  /** 
   * <em>experience_div8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv8IsModified();


  /** 
   * <em>experience_div9</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDiv9();


  /** 
   * <em>experience_div9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv9IsSet();


  /** 
   * <em>experience_div9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv9IsModified();


  /** 
   * <em>experience_div10</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getExperienceDiv10();


  /** 
   * <em>experience_div10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv10IsSet();


  /** 
   * <em>experience_div10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getExperienceDiv10IsModified();


  /** 
   * <em>interest_flag1</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getInterestFlag1();


  /** 
   * <em>interest_flag1</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getInterestFlag1IsNull();


  /** 
   * <em>interest_flag1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag1IsSet();


  /** 
   * <em>interest_flag1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag1IsModified();


  /** 
   * <em>interest_flag2</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getInterestFlag2();


  /** 
   * <em>interest_flag2</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getInterestFlag2IsNull();


  /** 
   * <em>interest_flag2</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag2IsSet();


  /** 
   * <em>interest_flag2</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag2IsModified();


  /** 
   * <em>interest_flag3</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getInterestFlag3();


  /** 
   * <em>interest_flag3</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getInterestFlag3IsNull();


  /** 
   * <em>interest_flag3</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag3IsSet();


  /** 
   * <em>interest_flag3</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag3IsModified();


  /** 
   * <em>interest_flag4</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getInterestFlag4();


  /** 
   * <em>interest_flag4</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getInterestFlag4IsNull();


  /** 
   * <em>interest_flag4</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag4IsSet();


  /** 
   * <em>interest_flag4</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag4IsModified();


  /** 
   * <em>interest_flag5</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getInterestFlag5();


  /** 
   * <em>interest_flag5</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getInterestFlag5IsNull();


  /** 
   * <em>interest_flag5</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag5IsSet();


  /** 
   * <em>interest_flag5</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag5IsModified();


  /** 
   * <em>interest_flag6</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getInterestFlag6();


  /** 
   * <em>interest_flag6</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getInterestFlag6IsNull();


  /** 
   * <em>interest_flag6</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag6IsSet();


  /** 
   * <em>interest_flag6</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag6IsModified();


  /** 
   * <em>interest_flag7</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getInterestFlag7();


  /** 
   * <em>interest_flag7</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getInterestFlag7IsNull();


  /** 
   * <em>interest_flag7</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag7IsSet();


  /** 
   * <em>interest_flag7</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag7IsModified();


  /** 
   * <em>interest_flag8</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getInterestFlag8();


  /** 
   * <em>interest_flag8</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getInterestFlag8IsNull();


  /** 
   * <em>interest_flag8</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag8IsSet();


  /** 
   * <em>interest_flag8</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag8IsModified();


  /** 
   * <em>interest_flag9</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getInterestFlag9();


  /** 
   * <em>interest_flag9</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getInterestFlag9IsNull();


  /** 
   * <em>interest_flag9</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag9IsSet();


  /** 
   * <em>interest_flag9</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag9IsModified();


  /** 
   * <em>interest_flag10</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getInterestFlag10();


  /** 
   * <em>interest_flag10</em>カラムの値がnullかどうか調べます。
   * @@return 対象のデータベースカラムの値がnullの場合はtrue 
   */
  public  boolean  getInterestFlag10IsNull();


  /** 
   * <em>interest_flag10</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag10IsSet();


  /** 
   * <em>interest_flag10</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInterestFlag10IsModified();


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
   * <em>appli_motivat_div_detail</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAppliMotivatDivDetail();


  /** 
   * <em>appli_motivat_div_detail</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAppliMotivatDivDetailIsSet();


  /** 
   * <em>appli_motivat_div_detail</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAppliMotivatDivDetailIsModified();


  /** 
   * <em>use_institution_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getUseInstitutionDiv();


  /** 
   * <em>use_institution_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUseInstitutionDivIsSet();


  /** 
   * <em>use_institution_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getUseInstitutionDivIsModified();


  /** 
   * <em>internet_trade_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getInternetTradeDiv();


  /** 
   * <em>internet_trade_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInternetTradeDivIsSet();


  /** 
   * <em>internet_trade_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getInternetTradeDivIsModified();


  /** 
   * <em>introduce_branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getIntroduceBranchCode();


  /** 
   * <em>introduce_branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIntroduceBranchCodeIsSet();


  /** 
   * <em>introduce_branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getIntroduceBranchCodeIsModified();


  /** 
   * <em>accept_status</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAcceptStatus();


  /** 
   * <em>accept_status</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAcceptStatusIsSet();


  /** 
   * <em>accept_status</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAcceptStatusIsModified();


}
@
