head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.21.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	YCustomerRow.java;


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
 * YCustomerRowインタフェースは変更不可でリードオンリーである<em>y_customer</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link YCustomerRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see YCustomerPK 
 */
public interface YCustomerRow extends Row {


  /** 
   * この{@@link YCustomerRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "y_customer", "session" );


  /** 
   * <em>y_customer_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getYCustomerId();


  /** 
   * <em>y_customer_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getYCustomerIdIsSet();


  /** 
   * <em>y_customer_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getYCustomerIdIsModified();


  /** 
   * <em>control_branch_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getControlBranchCode();


  /** 
   * <em>control_branch_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getControlBranchCodeIsSet();


  /** 
   * <em>control_branch_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getControlBranchCodeIsModified();


  /** 
   * <em>operation_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOperationDiv();


  /** 
   * <em>operation_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOperationDivIsSet();


  /** 
   * <em>operation_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOperationDivIsModified();


  /** 
   * <em>control_number</em>カラムの値を取得します。
   * @@return intの値 
   */
  public int getControlNumber();


  /** 
   * <em>control_number</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getControlNumberIsSet();


  /** 
   * <em>control_number</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getControlNumberIsModified();


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
   * <em>name_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getNameKana();


  /** 
   * <em>name_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNameKanaIsSet();


  /** 
   * <em>name_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNameKanaIsModified();


  /** 
   * <em>name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getName();


  /** 
   * <em>name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNameIsSet();


  /** 
   * <em>name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getNameIsModified();


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
   * <em>address_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddressKana();


  /** 
   * <em>address_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressKanaIsSet();


  /** 
   * <em>address_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressKanaIsModified();


  /** 
   * <em>address</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getAddress();


  /** 
   * <em>address</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressIsSet();


  /** 
   * <em>address</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getAddressIsModified();


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
   * <em>office_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getOfficeKana();


  /** 
   * <em>office_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfficeKanaIsSet();


  /** 
   * <em>office_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getOfficeKanaIsModified();


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
   * <em>post_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getPostKana();


  /** 
   * <em>post_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPostKanaIsSet();


  /** 
   * <em>post_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getPostKanaIsModified();


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
   * <em>comment_kana</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCommentKana();


  /** 
   * <em>comment_kana</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommentKanaIsSet();


  /** 
   * <em>comment_kana</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommentKanaIsModified();


  /** 
   * <em>comment_kanji</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getCommentKanji();


  /** 
   * <em>comment_kanji</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommentKanjiIsSet();


  /** 
   * <em>comment_kanji</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getCommentKanjiIsModified();


  /** 
   * <em>sonar_created_branch</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSonarCreatedBranch();


  /** 
   * <em>sonar_created_branch</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarCreatedBranchIsSet();


  /** 
   * <em>sonar_created_branch</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarCreatedBranchIsModified();


  /** 
   * <em>sonar_created_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSonarCreatedDate();


  /** 
   * <em>sonar_created_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarCreatedDateIsSet();


  /** 
   * <em>sonar_created_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarCreatedDateIsModified();


  /** 
   * <em>sonar_updated_branch</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSonarUpdatedBranch();


  /** 
   * <em>sonar_updated_branch</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarUpdatedBranchIsSet();


  /** 
   * <em>sonar_updated_branch</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarUpdatedBranchIsModified();


  /** 
   * <em>sonar_updated_date</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSonarUpdatedDate();


  /** 
   * <em>sonar_updated_date</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarUpdatedDateIsSet();


  /** 
   * <em>sonar_updated_date</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarUpdatedDateIsModified();


  /** 
   * <em>disclosure_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getDisclosureDiv();


  /** 
   * <em>disclosure_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDisclosureDivIsSet();


  /** 
   * <em>disclosure_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getDisclosureDivIsModified();


  /** 
   * <em>sonar_check_div</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getSonarCheckDiv();


  /** 
   * <em>sonar_check_div</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarCheckDivIsSet();


  /** 
   * <em>sonar_check_div</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getSonarCheckDivIsModified();


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
