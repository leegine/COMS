head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.35.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	ParticipantRow.java;


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
 * ParticipantRowインタフェースは変更不可でリードオンリーである<em>participant</em>データベーステーブルレコードのスナップショットを表現します。 
 * <p> 
 * 通常、{@@link com.fitechlabs.xtrade.kernel.data.QueryProcessor}オブジェクトのメソッドを利用して{@@link ParticipantRow}インタフェースの実装オブジェクトを検索します。 
 * 
 * @@author xTradeコードジェネレータ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see ParticipantPK 
 */
public interface ParticipantRow extends Row {


  /** 
   * この{@@link ParticipantRow}クラスに紐付くテーブルをあらわす{@@link com.fitechlabs.dbind.RowType}オブジェクト 
   */
   public static final RowType TYPE = new RowType( "participant", "account" );


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
   * <em>participant_id</em>カラムの値を取得します。
   * @@return longの値 
   */
  public long getParticipantId();


  /** 
   * <em>participant_id</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantIdIsSet();


  /** 
   * <em>participant_id</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantIdIsModified();


  /** 
   * <em>participant_code</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getParticipantCode();


  /** 
   * <em>participant_code</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantCodeIsSet();


  /** 
   * <em>participant_code</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantCodeIsModified();


  /** 
   * <em>participant_middle_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getParticipantMiddleName();


  /** 
   * <em>participant_middle_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantMiddleNameIsSet();


  /** 
   * <em>participant_middle_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantMiddleNameIsModified();


  /** 
   * <em>participant_family_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getParticipantFamilyName();


  /** 
   * <em>participant_family_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantFamilyNameIsSet();


  /** 
   * <em>participant_family_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantFamilyNameIsModified();


  /** 
   * <em>participant_given_name</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getParticipantGivenName();


  /** 
   * <em>participant_given_name</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantGivenNameIsSet();


  /** 
   * <em>participant_given_name</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantGivenNameIsModified();


  /** 
   * <em>participant_middle_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getParticipantMiddleNameAlt1();


  /** 
   * <em>participant_middle_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantMiddleNameAlt1IsSet();


  /** 
   * <em>participant_middle_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantMiddleNameAlt1IsModified();


  /** 
   * <em>participant_family_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getParticipantFamilyNameAlt1();


  /** 
   * <em>participant_family_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantFamilyNameAlt1IsSet();


  /** 
   * <em>participant_family_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantFamilyNameAlt1IsModified();


  /** 
   * <em>participant_given_name_alt1</em>カラムの値を取得します。
   * @@return Stringの値 
   */
  public String getParticipantGivenNameAlt1();


  /** 
   * <em>participant_given_name_alt1</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantGivenNameAlt1IsSet();


  /** 
   * <em>participant_given_name_alt1</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantGivenNameAlt1IsModified();


  /** 
   * <em>participant_type</em>カラムの値を取得します。
   * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ParticipantTypeEnumの値 
   */
  public com.fitechlabs.xtrade.plugin.tc.gentrade.ParticipantTypeEnum getParticipantType();


  /** 
   * <em>participant_type</em>カラムに値が設定されているかどうか調べます。
   * @@return 値が既に設定されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantTypeIsSet();


  /** 
   * <em>participant_type</em>カラムの値が変更されているかどうか調べます。
   * @@return 値が既に変更されている場合はtrue、そうでなければfalse 
   */
  public  boolean  getParticipantTypeIsModified();


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
